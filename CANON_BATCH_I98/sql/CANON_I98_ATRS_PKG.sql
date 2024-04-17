/************************************************************************************************
*                                                                                         	 	*
* File NAME       : CANON_I98_ATRS_PKG.sql              	                         	 		*
* Object Name     : CANON_I98_ATRS_PKG															*
* DESCRIPTION     :                                                                   	     	*
*                                                                            				 	*
*                                                                              	            	*
* REVISION HISTORY:                                                                        		*
*                                                                                          		*
* DEVELOPER         DATE           DESCRIPTION                                             		*
* -------------     -----------    ---------------------------                             		*
* Radhika Gorthy    06/06/2016     Initial Creation												*
*************************************************************************************************/
create or replace PACKAGE CANON_I98_ATRS_PKG
AS
   PROCEDURE terr_tbl_populate (
      errbuf         IN OUT   VARCHAR2,
      retcode        IN OUT   VARCHAR2
   );
PROCEDURE get_file_path(
    x_file_path OUT VARCHAR2
);

PROCEDURE get_table_data(
    p_table_name IN VARCHAR2
    ,x_table_data OUT sys_refcursor);

END CANON_I98_ATRS_PKG;
/

create or replace PACKAGE BODY              CANON_I98_ATRS_PKG
AS

    G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_I98_ATRS_PKG';
PROCEDURE terr_tbl_populate (
      errbuf         IN OUT   VARCHAR2,
      retcode        IN OUT   VARCHAR2
   )
   IS
    l_e59_lastchecked DATE;
    P_PROCESS_NAME VARCHAR2(400) := 'TERR_TBL_POPULATE';

   BEGIN

   BEGIN
        SELECT cscv.val76
          INTO l_e59_lastchecked
          FROM canon_s21_cd_tbl csc
               ,canon_s21_cd_val_tbl cscv
         WHERE cscv.cd_id = csc.cd_id
           AND cscv.val1 = 'CANON_E59_LASTCHECKED'
           AND csc.cd_name = 'PROFILES_BY_DATE';
   EXCEPTION
        WHEN NO_DATA_FOUND THEN
            l_e59_lastchecked := SYSDATE - 1;
            dbms_output.put_line('Defaulted E59 profile date to ');
        WHEN OTHERS THEN
          canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);  
   END;

   dbms_output.put_line('l_e59_lastchecked : ' ||l_e59_lastchecked);


   -- TERRITORY START----------------------------------------------

      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I98_TERRITORIES_TBL';

      BEGIN
      INSERT INTO CANON_I98_TERRITORIES_TBL (TERRITORYID_PK
                                             ,TERRITORYNAME
                                             ,CREATEDBY_FK
                                             ,CREATEDON
                                             ,ORGID
                                            )  (SELECT dou.org_cd
                                                             ,dou.org_nm
                                                            -- ,DECODE(psn.psn_last_nm, NULL, 'No Match Found', psn.psn_last_nm|| ', ' || psn.psn_first_nm)
--                                                             ,'TEST' --psn.psn_last_nm || ', ' || psn.psn_first_nm
                                                             ,dou.ezinuserid
                                                             ,NVL(to_date(substr(dou.ezintime,1,14),'yyyymmddhh24miss'),sysdate) 
                                                             ,'81'
                                                        FROM ds_org_unit dou
                                                             ,org_stru_tp ost
                                                             ,biz_area_org bao
                                                             ,ds_org_resrc_reln dorr
                                                        WHERE dou.org_stru_tp_cd = ost.org_stru_tp_cd
                                                          AND dou.glbl_cmpy_cd = 'ADB'
                                                          AND dou.ezcancelflag = '0'
                                                          AND dou.org_cd = dorr.org_cd(+)
                                                          AND dorr.glbl_cmpy_cd(+) = 'ADB'
                                                          AND dorr.ezcancelflag(+) = '0'
                                                          AND dorr.acct_team_role_tp_cd IS NULL
                                                        --  AND dou.ezinuserid = psn.psn_cd(+)
                                                          AND ost.trty_stru_flg = 'Y'
                                                          AND ost.glbl_cmpy_cd = 'ADB'
                                                          AND ost.org_stru_tp_cd = bao.org_Stru_tp_cd
                                                          AND bao.glbl_cmpy_cd = 'ADB'
                                                          AND bao.ezcancelflag = '0'
                                                          AND bao.sls_flg = 'Y'
                                                          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                                          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorr.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                                      );
        dbms_output.put_line('# of Records inserted into CANON_I98_TERRITORIES_TBL :' ||SQL%ROWCOUNT);
        COMMIT;
        EXCEPTION
            WHEN OTHERS THEN
                dbms_output.put_line('Inserted into CANON_I98_TERRITORIES_TBL :' ||SQLERRM);
                canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
        END;
      -- TERRITORY END----------------------------------------------


     /* -- TERRITORY START----------------------------------------------
      execute immediate 'truncate table S21_CSA_EXTN5.CANON_I98_TERR_LOOKUP_TBL';

      BEGIN
      INSERT INTO CANON_I98_TERR_LOOKUP_TBL (TERRITORYID_PK
                                             ,NAME
                                             ,DESCRIPTION
                                             ,RANK
                                             ,ORG_CD) (SELECT dou.org_cd
                                                              ,dou.org_nm
                                                              ,dou.org_desc_txt
                                                              ,dou.org_tier_cd
                                                              ,'81'
                                                         FROM ds_org_unit dou
                                                              ,org_stru_tp ost
                                                              ,biz_area_org bao
                                                        WHERE dou.org_stru_tp_cd = ost.org_stru_tp_cd
                                                          AND ost.trty_stru_flg = 'Y'
                                                          AND ost.org_Stru_tp_cd = bao.org_stru_tp_cd
                                                          AND bao.sls_flg = 'Y'
                                                          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE)))
                                                        ;
        dbms_output.put_line('# of Records inserted into CANON_I98_TERR_LOOKUP_TBL :' ||SQL%ROWCOUNT);
        COMMIT;
       EXCEPTION
            WHEN OTHERS THEN
                dbms_output.put_line('Inserted into CANON_I98_TERR_LOOKUP_TBL :' ||SQL%ROWCOUNT);
                canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
        END;
      -- TERRITORY END----------------------------------------------*/


      -- TERRITORY ZIP START----------------------------------------------
      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I98_TERR_ZIP_TBL';

      BEGIN
      INSERT INTO CANON_I98_TERR_ZIP_TBL (TERRITORYID_FK
                                          ,CREATEDBY_FK
                                          ,CREATEDON
                                          ,ZIPFROMID_FK
                                          ,ZIPTOID_FK
                                          ,ORGID
                                          ) (SELECT tr.org_cd
                                                    ,tr.ezinuserid
                                                   -- ,DECODE(psn.psn_last_nm, NULL, 'No Match Found', psn.psn_last_nm|| ', ' || psn.psn_first_nm)
--                                                    ,'TEST' --psn.psn_last_nm || ', ' || psn.psn_first_nm
                                                    ,NVL(to_date(substr(tr.ezintime,1,14),'yyyymmddhh24miss'),sysdate) 
                                                    ,tr.trty_rule_from_val_txt
                                                    ,tr.trty_rule_thru_val_txt
                                                    ,NULL
                                               FROM trty_rule tr
                                                    ,trty_rule_tp trt
                                                    --,s21_psn psn
                                                    ,trty_rule_oprd_tp trot
                                                    ,org_stru_tp ost
                                                    ,biz_area_org bao
                                              WHERE tr.trty_rule_tp_cd = trt.trty_rule_tp_cd
                                                AND tr.glbl_cmpy_cd = 'ADB'
                                                AND tr.ezcancelflag = '0'
                                                AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tr.EFF_THRU_DT, 'yyyymmdd'),SYSDATE))
                                                AND UPPER(trt.trty_rule_tp_nm) = UPPER('Postal Code')
                                                AND tr.trty_rule_oprd_tp_Cd = trot.trty_rule_oprd_tp_cd
                                                AND (UPPER(trot.trty_rule_oprd_tp_nm) = UPPER('BETWEEN') OR
												     UPPER(trot.trty_rule_oprd_tp_nm) = UPPER('EQUAL'))
                                                --AND psn.psn_cd(+) = tr.ezinuserid
                                                AND tr.org_Stru_tp_cd = ost.org_stru_tp_cd
                                                AND ost.trty_stru_flg = 'Y'
                                                AND ost.glbl_cmpy_cd = 'ADB'
                                                AND ost.org_stru_tp_cd = bao.org_stru_tp_cd
                                                AND bao.sls_flg = 'Y'
                                                AND bao.glbl_cmpy_cd = 'ADB'
                                                AND bao.ezcancelflag = '0'
                                              );
        dbms_output.put_line('# of Records inserted into CANON_I98_TERR_ZIP_TBL :' ||SQL%ROWCOUNT);
        COMMIT;
       EXCEPTION
            WHEN OTHERS THEN
                dbms_output.put_line('Inserted into CANON_I98_TERR_ZIP_TBL :' ||SQLERRM);
                canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
        END;
      -- TERRITORY ZIP END----------------------------------------------


      ----PROSPECTS  START----------------------------------------------
      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I98_PROSPECTS_TBL';

      BEGIN
        INSERT INTO canon_i98_prospects_tbl (PARTYID
                                              ,PARTYNUMBER
                                              ,PARTYSITENUMBER_PK
                                              ,PARTYSITEID_PK
                                              ,NAME
                                              ,ADDR1
                                              ,ADDR2
                                              ,CITY
                                              ,STATE_FK
                                              ,ZIP_FK
                                              ,ZIPPLUS_FK
                                              ,PERSON_FIRST_NAME
                                              ,PERSON_MIDDLE_NAME
                                              ,PERSON_LAST_NAME
                                              ,PERSON_NAME_SUFFIX
                                              ,PHONE
                                              ,DNB_FK
                                              ,EMAILADDRESS
                                              ,ASSTERRID_FK
                                              ,OWNER
                                              ,CREATEDBY_FK
                                              ,CREATEDON
                                              ,ORGID
                                              ,TERRITORYNAME
                                              ,LASTMODIFIED
                                              ,SFDC# ) (SELECT DISTINCT
                                                            plw.cmpy_pk
                                                            ,dac.ds_acct_num
                                                            ,plw.loc_num
                                                            ,plw.pty_loc_pk 
                                                            ,dac.ds_acct_nm
                                                            ,NVL(plw.first_line_addr,'-1')
                                                            ,plw.scd_line_addr
                                                            ,plw.cty_addr
                                                            ,plw.st_cd
                                                            ,substr(plw.post_cd,1,5)
                                                            ,SUBSTR(plw.post_cd, 7)
                                                            ,NULL
                                                            ,NULL
                                                            ,NULL
                                                            ,NULL
                                                            ,dac.tel_num
                                                            ,plw.duns_num
                                                            ,NULL
                                                            ,atra.org_cd
                                                            ,atra.line_biz_role_tp_cd
                                                            ,dac.ezinuserid
                                                            ,NVL(to_date(substr(dac.ezintime,1,14),'yyyymmddhh24miss'),sysdate) 
                                                            ,'81'
                                                            ,dou.org_nm
                                                            ,NVL(to_date(substr(dac.ezuptime,1,14),'yyyymmddhh24miss'),sysdate) 
                                                            ,xref.acct_cd
                                                         FROM ds_acct_pros dac
                                                              ,pros_pty_loc_wrk plw --per S21 suggestion
                                                            ,pros_trty_role_asg atra
															,trty_grp_tp tgt
															,ds_org_unit dou
                                                             ,(SELECT max(dxa.ds_xref_acct_cd) acct_cd, dxa.loc_num loc_num
                                                                FROM ds_xref_acct dxa
                                                                     ,ds_xref_acct_tp dxat
                                                              WHERE dxa.glbl_cmpy_cd = 'ADB'
                                                                    AND dxa.ezcancelflag = '0'
                                                                    AND dxat.glbl_cmpy_cd(+) = 'ADB'
                                                                    AND dxat.ezcancelflag(+) = '0'
                                                                    AND dxa.ds_xref_acct_tp_cd = dxat.ds_xref_acct_tp_cd(+)
                                                                    AND dxat.ds_xref_acct_tp_nm(+) = 'SFDC'
                                                                    group by dxa.loc_num
                                                             )xref
                                                       WHERE dac.loc_num = plw.loc_num
                                                         AND dac.glbl_cmpy_cd = 'ADB' 
                                                         AND dac.ezcancelflag = '0'
                                                         AND atra.ezcancelflag = '0'
														 AND atra.glbl_cmpy_cd = 'ADB'
														 AND tgt.ezcancelflag = '0'
														 AND tgt.glbl_cmpy_cd = 'ADB'
														 AND dou.ezcancelflag = '0'
														 AND dou.glbl_cmpy_cd = 'ADB'
                                                         AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, 'yyyymmdd'),SYSDATE)) ---commented for testing ONLY
--                                                         AND al.loc_num = plw.loc_num
                                                         AND plw.glbl_cmpy_cd = 'ADB' 
                                                         AND plw.ezcancelflag = '0'
                                                         AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE)) ---commented for testing ONLY
                                                         --AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE)) ---added as per Satoshi
                                                        -- AND dac.ezinuserid = psn.psn_cd(+)
                                                         AND atra.loc_num = plw.loc_num
														 AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
														 AND atra.line_biz_role_tp_cd is not null
														 AND tgt.line_biz_tp_cd IN ('ESS','LFS','PPS')
                                                         AND atra.org_cd = dou.org_cd
                                                         AND xref.loc_num(+) = plw.loc_num
--                                                         AND dxa.glbl_cmpy_cd(+) = 'ADB'
--                                                         AND dxa.ezcancelflag(+) = '0'
--                                                         --AND dxat.glbl_cmpy_cd(+) = 'ADB'
--                                                         --AND dxat.ezcancelflag(+) = '0'
--                                                         AND dxa.ds_xref_acct_tp_cd = dxat.ds_xref_acct_tp_cd(+)
--                                                         AND dxat.ds_xref_acct_tp_nm(+) = 'SFDC'
                                                         );

            dbms_output.put_line('# of Records inserted into CANON_I98_PROSPECTS_TBL : ' ||SQL%ROWCOUNT);
            COMMIT;
      EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('Error inserting into CANON_I98_PROSPECTS_TBL :' ||SQLERRM);
            canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
        END;
  ------------------------PROSPECTS END -----------------------------------

---------------------CUSTOMERS START ------------------------------------
EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I98_CUSTOMERS_TBL';
      BEGIN
        INSERT INTO CANON_I98_CUSTOMERS_TBL (
                                        PARTYID
                                      ,PARTYNUMBER
                                      ,ACCOUNTNUMBER
                                      ,PARTYSITENUMBER_PK
                                      ,PARTYSITEID_PK
                                      ,BILLTOID_FK
                                      ,SHIPTOID_FK
                                      ,NAME
                                      ,ADDR1
                                      ,ADDR2
                                      ,CITY
                                      ,STATE_FK
                                      ,ZIP_FK
                                      ,ZIPPLUS_FK
                                      ,PERSON_FIRST_NAME
                                      ,PERSON_MIDDLE_NAME
                                      ,PERSON_LAST_NAME
                                      ,PERSON_NAME_SUFFIX
                                      ,PHONE
                                      ,FAX
                                      ,DNB_FK
                                      ,SIC_FK
                                      ,EMAILADDRESS
                                      ,ASSTERRID_FK
                                      ,OWNER
                                      ,CREATEDBY_FK
                                      ,CREATEDON
                                      ,ORGID
                                      ,ACTIVE
                                      ,LASTMODIFIED
                                      ,SFDC#) (SELECT DISTINCT plw.cmpy_pk
                                                      ,dac.sell_to_cust_cd -- DB Changes
                                                      ,dac.sell_to_cust_cd  -- DB Changes
                                                      ,plw.loc_num
                                                      ,plw.pty_loc_pk --NVL(plw.first_ref_cmnt_txt,'-1') --plw.pty_loc_pk
                                                      ,NVL(btc.bill_to_cust_pk,btc2.bill_to_cust_pk) --dstc.reln_bill_to_cust_cd --??
                                                      ,stc.ship_to_cust_pk
                                                      ,dac.ds_acct_nm
                                                      ,NVL(plw.first_line_addr,'-1')
                                                      ,plw.scd_line_addr
                                                      ,plw.cty_addr
                                                      ,plw.st_cd
                                                      ,SUBSTR(plw.post_cd, 1, 5)
                                                      ,SUBSTR(plw.post_cd,7)
                                                      ,NULL
                                                      ,NULL
                                                      ,NULL
                                                      ,NULL
                                                      ,dac.tel_num
                                                      ,dac.fax_num
                                                      ,plw.duns_num
                                                      ,plw.ds_cust_sic_cd -- DB Changes
                                                      ,NULL --email_address ??
                                                      ,atra.org_cd
                                                      ,atra.line_biz_role_tp_cd
                                                      ,dac.ezinuserid
                                                     -- ,DECODE(psn.psn_last_nm, NULL, 'No Match Found', psn.psn_last_nm|| ', ' || psn.psn_first_nm)
--                                                      ,'TEST' --psn.psn_last_nm || ', ' || psn.psn_first_nm
                                                      ,NVL(to_date(substr(dac.ezintime,1,14),'yyyymmddhh24miss'),sysdate) ---for testing ONLY
                                                      ,'81' --ORGID
                                                      ,dac.ds_acct_actv_cust_flg
                                                      ,NVL(to_date(substr(dac.ezuptime,1,14),'yyyymmddhh24miss'),sysdate) ---for testing ONLY --lastmodifieddt
                                                      ,xref.acct_cd
                                                  FROM sell_to_cust dac -- DB Changes
                                                      ,pty_loc_wrk plw
                                                      ,acct_loc al
                                                      ,ship_to_cust stc
                                                      ,bill_to_cust btc
                                                      ,bill_to_cust btc2
													  ,acct_trty_role_asg atra
													  ,trty_grp_tp tgt
                                                      ,(SELECT max(dxa.ds_xref_acct_cd) acct_cd, dxa.loc_num loc_num
                                                                FROM ds_xref_acct dxa
                                                                     ,ds_xref_acct_tp dxat
                                                              WHERE dxa.glbl_cmpy_cd = 'ADB'
                                                                    AND dxa.ezcancelflag = '0'
                                                                    AND dxat.glbl_cmpy_cd(+) = 'ADB'
                                                                    AND dxat.ezcancelflag(+) = '0'
                                                                    AND dxa.ds_xref_acct_tp_cd = dxat.ds_xref_acct_tp_cd(+)
                                                                    AND dxat.ds_xref_acct_tp_nm(+) = 'SFDC'
                                                                    group by dxa.loc_num
                                                             )xref
                                                WHERE dac.sell_to_cust_cd = al.ds_acct_num -- DB Changes
                                                  AND dac.glbl_cmpy_cd = 'ADB'
                                                  AND dac.ezcancelflag = '0'
                                                  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                                  AND al.glbl_cmpy_cd = 'ADB'
                                                  AND al.ezcancelflag = '0'
                                                  AND al.loc_num = plw.loc_num
                                                  AND plw.glbl_cmpy_cd = 'ADB'
                                                  AND plw.ezcancelflag = '0'
												  AND atra.glbl_cmpy_cd = 'ADB'
												  AND atra.ezcancelflag = '0'
												  AND tgt.glbl_cmpy_cd = 'ADB'
												  AND tgt.ezcancelflag = '0'
                                                  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                                  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE)) ---added as per Satoshi
--                                                  AND plw.pty_loc_pk = dplw.pty_loc_pk -- DB Changes
--                                                  AND dplw.glbl_cmpy_cd = 'ADB'
--                                                  AND dplw.ezcancelflag = '0'
                                                  AND plw.loc_num = stc.loc_num
                                                  AND stc.glbl_cmpy_cd = 'ADB'
                                                  AND stc.ezcancelflag = '0'
                                                  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(stc.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(stc.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
--                                                  AND stc.ship_to_cust_pk = dstc.ship_to_cust_pk -- DB Changes
                                                  AND btc.glbl_cmpy_cd(+) = 'ADB'
                                                  AND btc2.glbl_cmpy_cd(+) = 'ADB'
                                                  AND btc.ezcancelflag(+) = '0'
                                                  AND btc2.ezcancelflag(+) = '0'
                                                  AND stc.reln_bill_to_cust_cd = btc.bill_to_cust_cd(+) -- DB Changes
                                                  AND btc2.loc_num(+) = plw.loc_num
                                                  AND atra.loc_num = plw.loc_num
												  AND atra.line_biz_role_tp_cd IS NOT NULL
												  AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
												  AND tgt.line_biz_tp_cd IN ('ESS','LFS','PPS')
                                                  AND plw.loc_num = xref.loc_num(+)
--                                                  AND dxa.glbl_cmpy_cd = 'ADB'
--                                                  AND dxa.ezcancelflag = '0'
--                                                  AND dxat.glbl_cmpy_cd = 'ADB'
--                                                  AND dxat.ezcancelflag = '0'
--                                                  AND dxa.ds_xref_acct_tp_cd = dxat.ds_xref_acct_tp_cd(+)
--                                                  AND dxat.ds_xref_acct_tp_nm(+) = 'SFDC'
                                                 -- AND trunc(to_date(substr(plw.ezintime,1,14),'yyyymmddhh24miss')) <= l_e59_lastchecked --commented for testing
                                                 );

        dbms_output.put_line('# of Records inserted into CANON_I98_CUSTOMERS_TBL : ' ||SQL%ROWCOUNT);
            COMMIT;
      EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('Error inserting into CANON_I98_CUSTOMERS_TBL :' ||SQLERRM);
            canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
        END;
  ------------------------CUSTOMERS END -----------------------------------

  --------SALESREPS START----------------------------------------------
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I98_SALESREPS_TBL';
  BEGIN
    INSERT INTO CANON_I98_SALESREPS_TBL (
                                TERRITORYID_FK
                                ,SALESREPID_FK
                                ,ORGID ) (SELECT dou.org_cd
                                                 ,psn.psn_num
                                                 ,'81'
                                            FROM ds_org_unit dou
                                                 ,ds_org_resrc_reln dorr
                                                 ,org_stru_tp ost
                                                 ,biz_area_org bao
                                                 ,s21_psn psn
                                            WHERE dou.org_stru_tp_cd = ost.org_stru_tp_cd
                                              AND ost.trty_stru_flg = 'Y'
                                              AND bao.org_stru_tp_cd = ost.org_stru_tp_cd
                                              AND bao.sls_flg = 'Y'
                                              AND dorr.acct_team_role_tp_cd IS NULL
                                              AND dou.org_cd = dorr.org_cd
                                              AND dorr.psn_cd = psn.psn_cd
                                              AND dou.glbl_cmpy_cd = 'ADB'
                                              AND dorr.glbl_cmpy_cd = 'ADB'
                                              AND ost.glbl_cmpy_cd = 'ADB'
                                              AND bao.glbl_cmpy_cd = 'ADB'
                                              AND bao.ezcancelflag = '0'
                                              AND dou.ezcancelflag = '0'
                                              AND dorr.ezcancelflag = '0'
                                              AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                              AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorr.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                              AND EXISTS (SELECT 1
                                                            FROM toc t
                                                                 ,org_func_asg ofa
                                                                 ,org_func_role_tp ofrt
                                                            WHERE ofa.glbl_cmpy_cd = 'ADB'
                                                              AND ofa.ezcancelflag = '0'
                                                              AND t.glbl_cmpy_cd = 'ADB'
                                                              AND t.ezcancelflag = '0'
                                                              AND ofrt.glbl_cmpy_cd = 'ADB'
                                                              AND ofrt.ezcancelflag = '0'
                                                              AND ofa.psn_cd = psn.psn_cd
                                                              AND ofa.toc_cd = t.toc_cd
                                                              AND t.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                                                              AND ofrt.first_org_cd = '1')
                                                              
                                        );
    dbms_output.put_line('# of Records inserted into CANON_I98_SALESREPS_TBL : ' ||SQL%ROWCOUNT);
    COMMIT;
	
	MERGE INTO syn_tbl_process_stage stg
    USING (SELECT 'I98_TO_SIDEKICK' process_name
           FROM dual
          )syn
      ON(stg.process_name = syn.process_name)
    WHEN MATCHED THEN UPDATE
     SET stg.LASTUPDATE = sysdate
    WHEN NOT MATCHED THEN INSERT (process_name, LASTUPDATE) values ('I98_TO_SIDEKICK',SYSDATE);
    dbms_output.put_line('# of Records updated in syn_tbl_process_stage : ' ||SQL%ROWCOUNT);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, 'Process table updated');
    COMMIT;
    
    retcode := 'S';
	
  EXCEPTION
    WHEN OTHERS THEN
		retcode := 'E';
		errbuf := sqlerrm;
        dbms_output.put_line('Error inserting into CANON_I98_SALESREPS_TBL :' ||SQLERRM);
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
  END;
---------------SALESREPS END-------------------------------------------------------------

   EXCEPTION
      WHEN OTHERS THEN
            dbms_output.put_line('Error in terr_tbl_populate: ' || SQLERRM);
            canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCESS_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
   END terr_tbl_populate;

   PROCEDURE get_file_path(
    x_file_path OUT VARCHAR2
) IS
    P_PROCEDURE_NAME VARCHAR2(400) := 'GET_FILE_PATH';
BEGIN
     SELECT val2
      INTO x_file_path
      FROM canon_s21_cd_val_tbl
     WHERE cd_id = 1
       AND val1 = 'CANON_I98_FILE_PATH';
EXCEPTION
WHEN OTHERS THEN
     x_file_path := '/WebSphere/apps/custbill/i98';
    dbms_output.put_line('Error in get_file_path: ' || SQLERRM);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCEDURE_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);

END;

PROCEDURE get_table_data(
    p_table_name IN VARCHAR2
    ,x_table_data OUT sys_refcursor
) IS
    l_dyn_sql VARCHAR2(500) := NULL;
    P_PROCEDURE_NAME VARCHAR2(400) := 'GET_TABLE_DATA';
BEGIN
    l_dyn_sql := 'SELECT * FROM ' || p_table_name ;
    OPEN x_table_data FOR l_dyn_sql;
--    OPEN x_table_data FOR SELECT * FROM CANON_I98_TERRITORIES_TBL;
    
EXCEPTION
    WHEN OTHERS THEN
dbms_output.put_line('Error in get_table_data: ' || SQLERRM);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCEDURE_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);

END;    


END CANON_I98_ATRS_PKG;
/