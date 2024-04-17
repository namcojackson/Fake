/************************************************************************************************
*                                                                                         	 	*
* File NAME       : CANON_RD159_GEO_TERR_PKG.sql              	                         	 		*
* Object Name     : CANON_RD159_GEO_TERR_PKG															*
* DESCRIPTION     :                                                                   	     	*
*                                                                            				 	*
*                                                                              	            	*
* REVISION HISTORY:                                                                        		*
*                                                                                          		*
* DEVELOPER         DATE           DESCRIPTION                                             		*
* -------------     -----------    ---------------------------                             		*
* Radhika Gorthy    06/06/2016     Initial Creation												*
*************************************************************************************************/
create or replace PACKAGE  CANON_RD159_GEO_TERR_PKG
AS
PROCEDURE populate_geo_terr (errbuf    OUT   VARCHAR2
                             ,retcode   OUT   VARCHAR2
                            );
FUNCTION get_tier(p_psn_cd IN varchar2
                   ,p_toc_cd IN VARCHAR2
                   ,p_org_cd IN VARCHAR2
                   ,p_org_stru_tp_cd IN VARCHAR2
                   ,p_stru_dfn IN VARCHAR2) RETURN VARCHAR2;

PROCEDURE get_E404_acc_geo_terr(x_cursor OUT sys_refcursor);

PROCEDURE update_E404_acc_geo_terr;

FUNCTION get_terr_soql RETURN VARCHAR2;

PROCEDURE reset_flags;

END CANON_RD159_GEO_TERR_PKG;
/

create or replace PACKAGE BODY               CANON_RD159_GEO_TERR_PKG
AS
G_PACKAGE_NAME VARCHAR2(200) := 'CANON_RD159_GEO_TERR_PKG';
PROCEDURE populate_geo_terr(errbuf OUT VARCHAR2
                            ,retcode OUT VARCHAR2)
AS

   CURSOR GEO_TERR_csr1
   IS
    SELECT tr.org_cd terr_id
           ,dou.org_nm territory
           ,tr.org_stru_tp_cd
           ,nvl(substr(tr.trty_rule_from_val_txt,1,5),00000) low_value_char_5
           ,nvl(substr(tr.trty_rule_from_val_txt,7,10),0000)  low_value_char_4
           ,nvl(substr(tr.trty_rule_thru_val_txt,1,5),99999) high_value_char_5
           ,nvl(substr(tr.trty_rule_thru_val_txt,7,10),9999) high_value_char_4
      FROM trty_rule tr
           ,trty_rule_tp trt
           ,biz_area_org bao
           ,org_stru_tp ost
           ,ds_org_unit dou
     WHERE tr.trty_rule_tp_cd = trt.trty_rule_tp_cd
       AND trt.trty_rule_tp_nm = 'Postal Code'
       AND tr.org_stru_tp_cd = ost.org_stru_tp_cd
       AND ost.trty_stru_flg = 'Y'
       AND tr.org_stru_tp_cd = bao.org_stru_tp_cd
       AND bao.sls_flg = 'Y'
       AND tr.glbl_cmpy_cd = 'ADB'
       AND trt.glbl_cmpy_cd = 'ADB'
       AND bao.glbl_cmpy_cd = 'ADB'
       AND dou.org_cd = tr.org_cd
       AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tr.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
       AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE));

   -- PARTY CURSOR

   CURSOR GEO_TERR_csr2
   IS
         SELECT /*+ USE_MERGE(TEMP0,E4041) ORDERED */ E4041.*
           FROM (SELECT E4042.party_number account_number,
                       E4042.party_name party_name,
                       DECODE(E4042.category_code, 'ACCOUNT', 'A','PROSPECT','P') P_A,
                       E4042.party_site_number party_site_number,
                       E4042.writing_territory territory,
                       E4042.s_street address1,
                       E4042.s_city city,
                       E4042.s_state state,
                       E4042.s_postal_code postal_code
                  FROM canon_e404_sf_acct_mapping_tbl E4042
                WHERE  E4042.status_flag = 'P'
                   AND E4042.sf_Account_id IS NOT NULL
                   AND E4042.writing_rep_name <> 'CSA INTERFACE'
                ) E4041,
               (SELECT DISTINCT dou.org_nm terr
                  FROM ds_org_unit dou
                       ,trty_rule tr
                       ,trty_rule_tp trt
                       ,biz_area_org bao
                       ,org_stru_tp ost
                 WHERE trt.trty_rule_tp_nm = 'Account/Site Number'
                   AND trt.trty_rule_tp_cd = tr.trty_rule_tp_cd
                   AND tr.org_cd = dou.org_cd
                   AND tr.org_stru_tp_cd = ost.org_stru_tp_cd
                   AND ost.trty_stru_flg = 'Y'
                   AND tr.org_stru_tp_cd = bao.org_stru_tp_cd
                   AND bao.sls_flg = 'Y'
                   AND tr.glbl_cmpy_cd = 'ADB'
                   AND trt.glbl_cmpy_cd = 'ADB'
                   AND bao.glbl_cmpy_cd = 'ADB'
                   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tr.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                  ) trty
         WHERE E4041.territory = trty.terr;

-- SALES REP CURSOR

   CURSOR GEO_SALESREP_csr
   IS
    SELECT dorr.org_cd terr_id
           ,psn.psn_first_nm || ', ' || psn_last_nm salesrep_name
           ,psn.psn_num salesrep_number
           ,psn.psn_cd salesrep_id
           ,ofrt.org_func_role_tp_cd role
  FROM s21_psn psn
        ,biz_area_org bao
        ,org_stru_tp ost
        ,ds_org_resrc_reln dorr
        ,org_func_role_tp ofrt
        ,(SELECT val1 role
                 ,val76 eff_from_dt
                 ,val77 eff_to_dt
            FROM canon_s21_cd_val_tbl val
                 ,canon_s21_cd_tbl cd
           WHERE cd.cd_id = val.cd_id
               AND cd.cd_name = 'CANON_E404_RD159_RSC_ROLES') rd159_roles
  WHERE dorr.psn_cd = psn.psn_cd
   AND dorr.org_stru_tp_cd = ost.org_stru_tp_cd
   AND dorr.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
   AND ost.trty_stru_flg = 'Y'
   AND bao.org_Stru_tp_cd = ost.org_stru_tp_cd
   AND bao.sls_flg = 'Y'
   AND bao.glbl_cmpy_cd = 'ADB'
   --AND dorr.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
--   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tr.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorr.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(rd159_roles.eff_FROM_DT, SYSDATE)) AND TRUNC(NVL(rd159_roles.eff_To_DT,SYSDATE))
   AND ofrt.org_func_role_tp_cd = rd159_roles.role;

    l_terr_id ds_org_unit.org_cd%TYPE;
    l_terr_name ds_org_unit.org_nm%TYPE;


-- Errored postal codes cursor
   CURSOR CUR_NACCT_ERR_POSTALCODE
   IS
        SELECT party_name
         ,account_number
         ,P_A
         ,party_site_number
         ,territory
         ,address1
         ,city
         ,state
         ,postal_code
     FROM CANON_RD159_ACC_GEO_TERR_TBL2 CRAG
     WHERE length(translate(substr(CRAG.postal_code,1,5),'A0123456789','A'))>0
     OR    length(translate(substr(CRAG.postal_code,7),'A0123456789','A'))>0;

    --ITG#468788
    TYPE ins_GEO_TERR_csr2_tbl_typ IS TABLE OF GEO_TERR_csr2%ROWTYPE INDEX BY PLS_INTEGER;
    l_ins_geo_terr_csr2_tbl ins_GEO_TERR_csr2_tbl_typ;
    
    TYPE geo_sales_rep_typ IS TABLE OF GEO_SALESREP_csr%ROWTYPE INDEX BY PLS_INTEGER;
    l_geo_sales_rep geo_sales_rep_typ ;

    l_psn_cd s21_psn.psn_cd%TYPE;
    l_toc_cd toc_org_mgr_info.toc_cd%TYPE;
    l_procedure_name varchar2(100) := 'populate_geo';

BEGIN


  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_RD159_ACC_GEO_TERR_TBL1';

  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_RD159_ACC_GEO_TERR_TBL2';
 
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_RD159_ACC_GEO_SALREP_TBL';
  
  dbms_output.put_line('Tables truncated');

  -- Territory Loop
        FOR GEO_TERR_rec1 IN GEO_TERR_csr1
         LOOP
             l_terr_id := GEO_TERR_rec1.terr_id;
             l_psn_cd := null;
             l_toc_cd := null;

             BEGIN
               SELECT psn_cd
               INTO l_psn_cd
               FROM ds_org_resrc_reln dorr
                    ,(SELECT val1 role
                             ,val76 eff_from_dt
                             ,val77 eff_to_dt
                        FROM canon_s21_cd_val_tbl val
                             ,canon_s21_cd_tbl cd
                       WHERE cd.cd_id = val.cd_id
                           AND cd.cd_name = 'CANON_E404_RD159_RSC_ROLES') rd159_roles
              WHERE org_cd = l_terr_id
                AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorr.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorr.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                AND dorr.org_func_role_tp_cd = rd159_roles.role
                AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(rd159_roles.eff_FROM_DT, SYSDATE)) AND TRUNC(NVL(rd159_roles.eff_To_DT,SYSDATE));
             EXCEPTION
                WHEN OTHERS THEN
                    l_psn_cd := null;
             END;
            dbms_output.put_line('l_psn_cd: ' ||l_psn_cd);  

            BEGIN
                SELECT toc_cd
                  INTO l_toc_cd
                  FROM org_func_asg ofa
                 WHERE psn_cd = l_psn_cd
                   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, 'yyyymmdd'),SYSDATE));
            EXCEPTION
                WHEN OTHERS THEN
                    l_toc_cd := null;
            END;
                dbms_output.put_line('l_toc_cd: ' ||l_toc_cd);

            BEGIN

                INSERT INTO CANON_RD159_ACC_GEO_TERR_TBL1
                            ( company
                              ,region
                              ,branch
                              ,territory
                              ,terr_id
                              ,low_value_char_5
                              ,low_value_char_4
                              ,high_value_char_5
                              ,high_value_char_4
                            )
                     VALUES (
                        get_tier(l_psn_cd, l_toc_cd, l_terr_id, GEO_TERR_rec1.org_stru_tp_cd, 'COMPANY')
                        ,NULL --get_tier(l_psn_cd, l_toc_cd, l_terr_id, GEO_TERR_rec1.org_stru_tp_cd, 'REGION')
                        ,NULL --get_tier(l_psn_cd, l_toc_cd, l_terr_id, GEO_TERR_rec1.org_stru_tp_cd, 'BRANCH')
                        ,GEO_TERR_rec1.territory
                        ,GEO_TERR_rec1.terr_id
                        ,GEO_TERR_rec1.low_value_char_5
                        ,GEO_TERR_rec1.low_value_char_4
                        ,GEO_TERR_rec1.high_value_char_5
                        ,GEO_TERR_rec1.high_value_char_4
                       );
            EXCEPTION
                WHEN OTHERS THEN
--                    FND_FILE.PUT_LINE(FND_FILE.LOG,'Error in retrieving the company/region/branch/territory name for terr_id: '||l_terr_id||', '||SQLERRM);
                    dbms_output.put_line('Error in retrieving the company/region/branch/territory name for terr_id: '||l_terr_id||', '||SQLERRM);
                    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Cursor 1 error:',NULL,NULL,NULL,NULL,SQLERRM);
            END;

         END LOOP;

         COMMIT;

      OPEN GEO_TERR_csr2;
      LOOP
        FETCH GEO_TERR_csr2 BULK COLLECT INTO l_ins_geo_terr_csr2_tbl LIMIT 10000;

        FOR i IN 1 .. l_ins_geo_terr_csr2_tbl.COUNT
        LOOP
            BEGIN
                    l_terr_name := l_ins_geo_terr_csr2_tbl(i).territory;
                    INSERT INTO CANON_RD159_ACC_GEO_TERR_TBL2
                    ( party_name,
                      account_number,
                      P_A ,
                      PARTY_SITE_NUMBER ,
                      territory,
                      Address1,
                      city,
                      state,
                      postal_code
                    )
                      VALUES
                      (
                      l_ins_geo_terr_csr2_tbl(i).party_name,
                      l_ins_geo_terr_csr2_tbl(i).account_number,
                      l_ins_geo_terr_csr2_tbl(i).P_A ,
                      l_ins_geo_terr_csr2_tbl(i).PARTY_SITE_NUMBER ,
                      l_ins_geo_terr_csr2_tbl(i).territory,
                      l_ins_geo_terr_csr2_tbl(i).Address1,
                      l_ins_geo_terr_csr2_tbl(i).city,
                      l_ins_geo_terr_csr2_tbl(i).state,
                      l_ins_geo_terr_csr2_tbl(i).postal_code
                     );
            EXCEPTION when others THEN
                  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Cursor 2 error:',NULL,NULL,NULL,NULL,SQLERRM);
            END;
        END LOOP ;
        COMMIT;
        EXIT WHEN l_ins_geo_terr_csr2_tbl.COUNT < 10000;
     END LOOP ;
     CLOSE GEO_TERR_csr2;
     

      -- logging/deleting named account site locations with incorrect postal code format
      

           DBMS_OUTPUT.PUT_LINE('-----------------------------------------------------------------------------------------');
         DBMS_OUTPUT.PUT_LINE('Named account party site locations with postal code format other than NNNNN-NNNN or NNNNN');
           DBMS_OUTPUT.PUT_LINE('-----------------------------------------------------------------------------------------');
--         FND_FILE.PUT_LINE(FND_FILE.LOG,RPAD('Party Name',30)||
         DBMS_OUTPUT.PUT_LINE(RPAD('Party Name',30)||' '||
                                            RPAD('Party Site Number',20)||' '||
                                            RPAD('Address1',40)||' '||
                                            RPAD('City',20)||' '||
                                            RPAD('State',20)||' '||
                                            RPAD('Postal Code',12)
                                            );
--         FND_FILE.PUT_LINE(FND_FILE.LOG,
         DBMS_OUTPUT.PUT_LINE(
                                            RPAD('-',30,'-')||' '||
                                            RPAD('-',20,'-')||' '||
                                            RPAD('-',40,'-')||' '||
                                            RPAD('-',20,'-')||' '||
                                            RPAD('-',20,'-')||' '||
                                            RPAD('-',12,'-')
                                            );

        BEGIN
         FOR cur_nacct_err_postalcode_rec IN cur_nacct_err_postalcode
         LOOP
                  DBMS_OUTPUT.PUT_LINE(RPAD(NVL(cur_nacct_err_postalcode_rec.party_name,' '),30)||' '||
--             FND_FILE.PUT_LINE(FND_FILE.LOG,RPAD(NVL(cur_nacct_err_postalcode_rec.party_name,' '),30)||' '||
                                            RPAD(NVL(cur_nacct_err_postalcode_rec.party_site_number,' '),20)||' '||
                                            RPAD(NVL(cur_nacct_err_postalcode_rec.Address1,' '),40)||' '||
                                            RPAD(NVL(cur_nacct_err_postalcode_rec.City,' '),20)||' '||
                                            RPAD(NVL(cur_nacct_err_postalcode_rec.State,' '),20)||' '||
                                            RPAD(NVL(cur_nacct_err_postalcode_rec.Postal_Code,' '),12)
                                             );

             --dbms_output.PUT_LINE(rpad(cur_nacct_err_postalcode_rec.party_name,30,' '));
             IF LENGTH(NVL(cur_nacct_err_postalcode_rec.party_name,'a'))>30 THEN
--                 FND_FILE.PUT_LINE(FND_FILE.LOG,substr(cur_nacct_err_postalcode_rec.party_name,31));
                 dbms_output.PUT_LINE(substr(cur_nacct_err_postalcode_rec.party_name,31));
             END IF;

         END LOOP;
         EXCEPTION
            WHEN OTHERS THEN
                dbms_output.put_line('Exception 2 : ' ||sqlerrm);
                 canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'EXCEPTION 2:',NULL,NULL,NULL,NULL,SQLERRM);
          END;

         --delete the incorrect records
        BEGIN
         DELETE
         FROM   CANON_RD159_ACC_GEO_TERR_TBL2 CRAG
         WHERE  length(translate(substr(CRAG.postal_code,1,5),'A0123456789','A'))>0
           OR    length(translate(substr(CRAG.postal_code,7),'A0123456789','A'))>0;
        EXCEPTION
            WHEN OTHERS THEN
                dbms_output.put_line('Exception 3 : ' ||sqlerrm);
                 canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'EXCEPTION 3:',NULL,NULL,NULL,NULL,SQLERRM);
        END;
                

--         FND_FILE.PUT_LINE(FND_FILE.LOG,'Deleted '||SQL%ROWCOUNT||' records from CANON_RD159_ACC_GEO_TERR_TBL2 with non-numeric postal code format.');

         IF SQL%ROWCOUNT >0 THEN
           retcode := 1;
           errbuf := 'Warning! Deleted the Named accounts with postal code format different from NNNNN-NNNN or NNNNN and thus may not be listed in the report.';
         END IF;

         COMMIT;

         BEGIN
            UPDATE CANON_RD159_ACC_GEO_TERR_TBL1
            SET    low_value_num_5 =to_number(nvl(low_value_char_5,0))
            ,      low_value_num_4 =to_number(nvl(low_value_char_4,0))
            ,      high_value_num_5=to_number(nvl(high_value_char_5,99999))
            ,      high_value_num_4=to_number(nvl(high_value_char_4,99999));


            UPDATE CANON_RD159_ACC_GEO_TERR_TBL2
            SET    postal_code_num_5 = to_number(nvl(substr(postal_code,1,5),0))
            ,      postal_code_num_4 = to_number(nvl(substr(postal_code,7,10),0));
            COMMIT;
         EXCEPTION
            WHEN OTHERS THEN
                retcode := 2;
                errbuf := 'Error! Exception while populating the postal code segments in Number form: '||SQLERRM;
                 canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Error! Exception while populating the postal code segments in Number form: ',NULL,NULL,NULL,NULL,SQLERRM);
                RAISE;
         END;
      
        
      -- Sales Representative Cursor

      BEGIN
         FOR GEO_SALESREP_rec IN GEO_SALESREP_csr
         LOOP
            INSERT INTO CANON_RD159_ACC_GEO_SALREP_TBL
                        ( terr_id,
                          salesrep_name ,
                          SALESREP_NUMBER,
                          salesrep_id,
                          role
                        )
                 VALUES (
                         GEO_SALESREP_rec.terr_id,
                         GEO_SALESREP_rec.salesrep_name ,
                         GEO_SALESREP_rec.SALESREP_NUMBER,
                         GEO_SALESREP_rec.salesrep_id,
                         GEO_SALESREP_rec.role
            );
         END LOOP;
         COMMIT;
      EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            dbms_output.put_line('Exception 4 : ' ||sqlerrm);
            canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Exception 4: ',NULL,NULL,NULL,NULL,SQLERRM);
      END;
      --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Salesrep cursor completed',NULL,NULL,NULL,NULL,NULL);
      retcode := 0;
      errbuf := NULL;
EXCEPTION
    WHEN OTHERS THEN
    ROLLBACK;
      retcode := 2;
      errbuf := SQLERRM;   
      dbms_output.PUT_LINE('Exception pkg: ' ||SQLERRM);
      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Exception pkg: ',NULL,NULL,NULL,NULL,SQLERRM);
END populate_geo_terr;

FUNCTION get_tier(p_psn_cd IN varchar2
                   ,p_toc_cd IN VARCHAR2
                   ,p_org_cd IN VARCHAR2
                   ,p_org_stru_tp_cd IN VARCHAR2
                   ,p_stru_dfn IN VARCHAR2) RETURN VARCHAR2

AS
    l_sql VARCHAR2(32000) := null;
    l_tier_cd VARCHAR2(20) := null;
    x_org_nm VARCHAR2(2000) := null;
	P_FUNC_NAME VARCHAR2(100) := 'get_tier';
BEGIN

   BEGIN
    SELECT ohsd.org_tier_cd tier_cd
      INTO l_tier_cd
      FROM org_hrch_stru_dfn    ohsd
           ,stru_dfn sd
     WHERE 1 = 1
       AND ohsd.org_stru_tp_cd = p_org_stru_tp_cd
       AND UPPER(ohsd.org_hrch_stru_dfn_nm) = p_stru_dfn
       AND ohsd.stru_dfn_cd = sd.stru_dfn_cd
       AND (CASE p_stru_dfn WHEN 'COMPANY' THEN sd.cmpy_flg
                           WHEN 'BRANCH' THEN sd.bu_flg
                           WHEN 'ZONE' THEN sd.zn_flg
                           WHEN 'REGION' THEN rg_flg ELSE''END) = 'Y';
   EXCEPTION
    WHEN OTHERS THEN
        l_tier_cd := null;
   END;
                           
     l_sql := null;
     l_sql := 'SELECT '  ||
             (CASE l_tier_cd WHEN 1 THEN 'FIRST_'
                                           WHEN 2 THEN 'SCND_'
                                           WHEN 3 THEN 'THIRD_'
                                           WHEN 4 THEN 'FRTH_'
                                           WHEN 5 THEN 'FIFTH_'
                                           WHEN 6 THEN 'SIXTH_'
                                           WHEN 7 THEN 'SVNTH_'
                                           WHEN 8 THEN 'EIGHTH_'
                                           WHEN 9 THEN 'NINTH_'
                                           WHEN 10 THEN 'TENTH_'
                                           WHEN 11 THEN 'ELVNTH_'
                                         ELSE '' END) || 'ORG_NM ' ;
--            l_sql := l_sql || ' INTO x_org_nm ' ||
            l_sql := l_sql ||
                              ' FROM toc_org_mgr_info tomi' ||
                              ' WHERE psn_cd = ''' || p_psn_cd  || '''' ||
                              '   AND toc_cd = ''' || p_toc_cd  || '''' ||
                              '   AND org_stru_tp_cd = ''' || p_org_stru_tp_cd || '''' ||
                              '   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, ''yyyymmdd''),sysdate))' ||
                              '  AND ' ||
                        (CASE l_tier_cd WHEN 1 THEN 'FIRST_'
                                           WHEN 2 THEN 'SCND_'
                                           WHEN 3 THEN 'THIRD_'
                                           WHEN 4 THEN 'FRTH_'
                                           WHEN 5 THEN 'FIFTH_'
                                           WHEN 6 THEN 'SIXTH_'
                                           WHEN 7 THEN 'SVNTH_'
                                           WHEN 8 THEN 'EIGHTH_'
                                           WHEN 9 THEN 'NINTH_'
                                           WHEN 10 THEN 'TENTH_'
                                           WHEN 11 THEN 'ELVNTH_'
                                         ELSE '' END) || 'ORG_CD = ''' || p_org_cd || '''';
            DBMS_OUTPUT.PUT_LINE('l_sql: ' ||l_sql);
            EXECUTE IMMEDIATE l_sql INTO x_org_nm;
            
            dbms_output.put_line('x_org_nm: ' ||x_org_nm);
            RETURN x_org_nm;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error - get_tier: ' ||SQLERRM);
		canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_FUNC_NAME, NULL, NULL, NULL, NULL, NULL, substr(SQLERRM,1,3999));
        x_org_nm := null;
        RETURN x_org_nm;
END get_tier;

PROCEDURE get_E404_acc_geo_terr(x_cursor OUT sys_refcursor)
IS
P_PROCEDURE_NAME VARCHAR2(400) := 'get_E404_acc_geo_terr';
BEGIN

    --RESET the flags
    UPDATE canon_rd159_acc_geo_terr_tbl1
       SET status_flag = 'U'
           ,salesforce_terr_id = NULL
           ,status_message = NULL
     WHERE status_flag in ('P','E');

     COMMIT;

     OPEN x_cursor FOR SELECT DISTINCT
                                rd159_rep.salesrep_number
                                ,rd159_rep.salesrep_name
                                ,rd159_rep.role
                                ,rd159_terr.territory
                                ,rd159_terr.terr_id
                                ,rd159_terr.low_value_char_5
                                ,rd159_terr.low_value_char_4
                                ,rd159_terr.high_value_char_5
                                ,rd159_terr.high_value_char_4
                                ,rd159_terr.low_value_num_5
                                ,rd159_terr.low_value_num_4
                                ,rd159_terr.high_value_num_5
                                ,rd159_terr.high_value_num_4
                                ,rd159_terr.rowid
                                ,ceum.sf_user_id
                          FROM canon_rd159_acc_geo_terr_tbl1 rd159_terr
                               ,canon_rd159_acc_geo_salrep_tbl rd159_rep
                               ,canon_e404_sf_user_mapping_tbl ceum
                          WHERE rd159_terr.terr_id = rd159_rep.terr_id
                            AND ceum.employee_number = rd159_rep.salesrep_number
                            AND rd159_terr.status_flag = 'U'
                            AND ceum.sf_user_id IS NOT NULL
                       ORDER BY rd159_rep.salesrep_number, rd159_terr.territory, rd159_terr.low_value_char_5;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error - get_e404_acc_geo_terr: ' ||SQLERRM);
		canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCEDURE_NAME, NULL, NULL, NULL, NULL, NULL, substr(SQLERRM,1,3999));
END get_E404_ACC_GEO_TERR;

PROCEDURE update_E404_acc_geo_terr
IS
P_PROCEDURE_NAME VARCHAR2(400) := 'update_E404_acc_geo_terr';
BEGIN
    UPDATE canon_rd159_acc_geo_terr_tbl1
       SET status_flag = 'result from salesforce'
           ,salesforce_terr_id = 'id from salesforce'
           ,status_message = 'message from salesforce'
      WHERE status_flag = 'U'
        AND rowid = 'rowid from the request';

EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error - update_e404_acc_geo_terr: ' ||SQLERRM);
		canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCEDURE_NAME, NULL, NULL, NULL, NULL, NULL, substr(SQLERRM,1,3999));
END update_e404_acc_geo_terr;

FUNCTION get_terr_soql RETURN VARCHAR2
IS
    x_soql    VARCHAR2(3000) := NULL;
BEGIN
    x_soql := 'SELECT ID FROM CBS_User_Territory_Tbl__c';
    RETURN x_soql;
EXCEPTION
    WHEN OTHERS THEN
        x_soql := NULL;
        dbms_output.put_line('Error - get_terr_soql: ' ||SQLERRM);
		--canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCEDURE_NAME, NULL, NULL, NULL, NULL, NULL, substr(SQLERRM,1,3999));
        RETURN x_soql;
END;

PROCEDURE reset_flags
IS 
    P_PROCEDURE_NAME VARCHAR2(400) := 'RESET_FLAGS';
BEGIN
    UPDATE canon_rd159_acc_geo_terr_tbl1 
       SET status_flag = 'U' 
           ,salesforce_terr_id = null
           ,sf_update_date = null
           ,status_message = null
     WHERE status_flag in ('P','E') OR status_flag IS NULL
     ;
     COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        dbms_output.put_line('Error in rest_flags: ' || SQLERRM);
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCEDURE_NAME, NULL, NULL, NULL, NULL, NULL, substr(SQLERRM,1,3999));
END reset_flags;
END CANON_RD159_GEO_TERR_PKG;
/