create or replace PACKAGE              canon_e404_sf_account_pkg
AS
TYPE RESULT_CURSOR IS REF CURSOR;

PROCEDURE extract_accounts(x_return_status   OUT VARCHAR2
                          ,x_msg_data        OUT VARCHAR2
                          );
PROCEDURE insert_accounts(x_return_status   OUT VARCHAR2
                         ,x_msg_data        OUT VARCHAR2
                         );

PROCEDURE get_csmp_level(v_party_id     IN  VARCHAR2
                        ,x_csmp_level  OUT  VARCHAR2
                        );

PROCEDURE update_accounts(x_return_status   OUT VARCHAR2
                         ,x_msg_data        OUT VARCHAR2
                         );

PROCEDURE get_load_data(x_cursor OUT sys_refcursor);
--PROCEDURES to extract and process all accounts that needs to be uploaded to SFDC.
--will wait till WMB decisions made
PROCEDURE get_bill_to_location(v_psn     IN   VARCHAR2
							   ,v_acct_tp_cd IN VARCHAR2
                              ,x_b_street     OUT   VARCHAR2
                              ,x_b_city       OUT   VARCHAR2
                              ,x_b_state      OUT   VARCHAR2
                              ,x_b_country    OUT   VARCHAR2
                              ,x_b_postal_code OUT   VARCHAR2
                              );

--PROCEDURE get_accounts(x_cursor    OUT    sys_refcursor);

PROCEDURE get_fm_dept_name(v_casid IN VARCHAR2
						   ,x_fm_dept_name	OUT VARCHAR2
						   );
 

END canon_e404_sf_account_pkg;
/

create or replace PACKAGE BODY              canon_e404_sf_account_pkg
AS

G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E404_SF_ACCOUNT_PKG';

/*------------------------------------------------------------------------------
-- this procedure
   1.Inserts data into CANON_E404_RSCTERR_TBL which holds Territory, its resource and resource manager. 
   2. Update flags of any errored records to U
   3. call Insert_accounts and update_accounts procedures for corresponding actions on the account. Insert an account or update and account.
 **/
PROCEDURE extract_accounts(x_return_status   OUT VARCHAR2
                          ,x_msg_data        OUT VARCHAR2
                          )
AS
  l_i_ret_sts     VARCHAR2(1); --insert retun status
  l_i_msg_data    VARCHAR2(2000); --insert message
  l_u_ret_sts     VARCHAR2(1) := 'S'; --update return status
  l_u_msg_data    VARCHAR2(2000); --update message
  l_tier_mgr_psn_col VARCHAR2(100) := NULL;
  l_sql             VARCHAR2(32767) := NULL;
  l_tier_mgr_psn_cd VARCHAR2(100) := NULL;
  l_tier_mgr_nm VARCHAR2(500) := NULL;
  l_is_mgr_active VARCHAR2(10) := 'FALSE';
  l_procedure_name VARCHAR2(20) := 'EXTRACT_ACCOUNTS';
 
BEGIN

  EXECUTE IMMEDIATE  'TRUNCATE TABLE CANON_E404_RSCTERR_TBL';
  dbms_output.put_line('Truncated Table CANON_E404_RSCTERR_TBL');
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Truncated CANON_E404_RSCTERR_TBL',NULL,NULL,NULL,NULL);
  
  FOR rscterr_rec IN (SELECT distinct org.org_cd terr_id
                              ,org.org_nm territory
                              ,org_tier_cd
                              ,org.psn_cd resource_id
                              ,(case WHEN org.psn_cd IS NOT NULL THEN psn.psn_last_nm || ', ' || psn.psn_first_nm 
                                            ELSE '' END) resource_name
                              ,(case WHEN org.psn_cd IS NOT NULL THEN psn.psn_num ELSE '' END) employee_number
                              ,org.comments
                           FROM (SELECT DISTINCT
                                        org_cd
                                        ,org_nm
                                        ,org_tier_cd
                                        ,(CASE WHEN rep_count = 1 THEN psn_cd ELSE NULL END) psn_cd
                                        ,(CASE WHEN rep_count = 0 THEN 'No salesreps assigned to the territory.'
                                               WHEN rep_count > 1 THEN 'Multiple salesreps assigned to the territory.'
                                            ELSE NULL
                                           END
                                          ) comments
                                    FROM( SELECT dorr.org_cd
                                                ,dorr.psn_cd
                                                ,dou.org_nm
                                                ,dou.org_tier_cd
                                                ,(CASE WHEN dorr.acct_team_role_tp_cd IS NULL THEN 'Y' ELSE 'N' END) rep 
                                                ,SUM((CASE WHEN dorr.acct_team_role_tp_cd IS NULL AND dorr.psn_cd IS NOT NULL THEN 1 ELSE 0 END)) OVER(PARTITION BY dorr.org_cd) rep_count
                                            FROM ds_org_unit          dou
                                                ,ds_org_resrc_reln    dorr
                                                ,biz_area_org bao
                                                ,org_stru_tp ost
                                           WHERE dorr.org_cd(+) = dou.org_cd
                                             AND dorr.glbl_cmpy_cd = 'ADB'
                                             AND dou.glbl_cmpy_cd = 'ADB'
                                             AND bao.glbl_cmpy_cd = 'ADB'
                                             AND dorr.glbl_cmpy_cd = dou.glbl_cmpy_cd
                                             AND dorr.org_stru_tp_cd = ost.org_stru_tp_cd
                                             AND dorr.org_stru_tp_cd = bao.org_stru_tp_cd
                                             AND dorr.acct_team_role_tp_cd IS NULL
                                             AND bao.sls_flg = 'Y'
                                             AND ost.glbl_cmpy_cd = 'ADB'
                                             AND ost.trty_stru_flg = 'Y'
                                             AND ost.glbl_cmpy_cd = 'ADB'
                                             AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorr.eff_FROM_DT(+),'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorr.eff_THRU_DT(+), 'yyyymmdd'),SYSDATE))
                                             AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                         ) 
                                         ) org
                                        ,s21_psn psn
                                        ,canon_e404_sf_user_mapping_tbl ceum
                                       WHERE psn.psn_cd = org.psn_cd  
                                         AND ceum.resource_id = psn.psn_cd
                                         AND ( ceum.is_active = 'TRUE' OR
                                                (ceum.is_active = 'FALSE' AND
                                                    (ceum.last_name like '%TERRITORY%' OR
                                                    ceum.last_name like '%ADJ%')
                                                 )
                                              )
                                         AND ceum.sf_user_id IS NOT NULL
                                        AND ceum.status_flag = 'P'
                                       
                      ) LOOP     
                        --dbms_output.put_line('org_tier_cd : ' ||rscterr_rec.org_tier_cd);
                        
                            FOR tier_cntr IN REVERSE 1..rscterr_rec.org_tier_cd
                              LOOP
                                l_tier_mgr_psn_col := NULL;
                                l_sql := NULL;
                                l_tier_mgr_psn_cd := NULL;
                                l_tier_mgr_nm := NULL;
                                
                                --dbms_output.put_line('org_tier_cd : ' ||rscterr_rec.org_tier_cd);
                                
                                l_tier_mgr_psn_col := (CASE tier_cntr WHEN '1' THEN 'first_org_mgr_'
                                                                          WHEN '2' THEN 'scd_org_mgr_'
                                                                          WHEN '3' THEN 'third_org_mgr_'
                                                                          WHEN '4' THEN 'frth_org_mgr_'
                                                                          WHEN '5' THEN 'fifth_org_mgr_'
                                                                          WHEN '6' THEN 'sixth_org_mgr_'
                                                                          WHEN '7' THEN 'svnth_org_mgr_'
                                                                          WHEN '8' THEN 'eighth_org_mgr_'
                                                                          WHEN '9' THEN 'ninth_org_mgr_'
                                                                          WHEN '10' THEN 'tenth_org_mgr_'
                                                                          WHEN '11' THEN 'elvth_org_mgr_'
                                                                          ELSE '' END);
                                --dbms_output.put_line('l_tier_mgr_psn_col : ' || l_tier_mgr_psn_col);
                                
                                                                                                          
                                l_sql := 'SELECT distinct ' || l_tier_mgr_psn_col || 'psn_cd,' || l_tier_mgr_psn_col || 'first_nm || ' || l_tier_mgr_psn_col || 'last_nm' ||

                                 ' FROM toc_org_mgr_info tomi' ||
                                 '     ,org_func_asg ofa ' ||
                                 ' WHERE ofa.psn_cd = ''' || rscterr_rec.resource_id || '''' ||
                                 ' AND ofa.toc_cd = tomi.toc_cd '  ||
								 ' AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, ''yyyymmdd''),SYSDATE)) ' ||
                                 ' AND tomi.psn_cd = ofa.psn_cd ' ||
                                 ' AND tomi.' || l_tier_mgr_psn_col || 'psn_cd' || ' IS NOT NULL' ;
                                 
                                 --dbms_output.put_line('l_sql: ' ||l_sql);
                                 
                                 BEGIN
                                 EXECUTE IMMEDIATE l_sql INTO l_tier_mgr_psn_cd,l_tier_mgr_nm;
                                 EXCEPTION 
                                    WHEN OTHERS THEN
                                         l_tier_mgr_psn_cd := NULL;
                                         l_tier_mgr_nm := NULL;
                                        --dbms_output.put_line(sqlerrm);
                                  END;
                                 
                                 
                                EXIT WHEN l_tier_mgr_psn_cd IS NOT NULL AND l_tier_mgr_nm IS NOT NULL;
                              END LOOP; 
                              
                              --dbms_output.put_line('l_tier_mgr_psn_cd : ' || l_tier_mgr_psn_cd);
                              --dbms_output.put_line('l_tier_mgr_nm : '|| l_tier_mgr_nm);
                              
                              --l_is_mgr_active := 'TRUE';
                              ---commented for testing
                              l_is_mgr_active := 'FALSE';
                              
                              BEGIN
                              SELECT 'TRUE'
                                 INTO l_is_mgr_active
                                 FROM canon_e404_sf_user_mapping_tbl 
                                WHERE employee_number = l_tier_mgr_psn_cd
                                  AND is_active = 'TRUE'
                                  AND status_flag = 'P'
--                                  AND run_status = 'S'
                                  AND sf_user_id IS NOT NULL;
                              EXCEPTION
                                WHEN OTHERS THEN
                                    l_is_mgr_active := 'FALSE';
                                 --   dbms_output.put_line('l_is_mgr_active : ' || sqlerrm);
                              END;
                                  
                              
                             BEGIN
                             dbms_output.put_line('!!!!!l_is_mgr_active : '|| l_is_mgr_active);
                             dbms_output.put_line('rscterr_rec.territory : ' || rscterr_rec.territory);
                             dbms_output.put_line('rscterr_rec.terr_id : ' || rscterr_rec.terr_id);
                             dbms_output.put_line('rscterr_rec.resource_id : ' || rscterr_rec.resource_id);
                             dbms_output.put_line('rscterr_rec.resource_name : ' || rscterr_rec.resource_name);
                             dbms_output.put_line('rscterr_rec.employee_number : ' || rscterr_rec.employee_number);
                             dbms_output.put_line('l_tier_mgr_psn_cd  : ' || l_tier_mgr_psn_cd );
                             dbms_output.put_line('l_tier_mgr_nm  : ' || l_tier_mgr_nm );
                             dbms_output.put_line('l_tier_mgr_psn_cd  : ' || l_tier_mgr_psn_cd );
                             dbms_output.put_line('rscterr_rec.comments  : ' || rscterr_rec.comments );
                             IF l_is_mgr_active = 'TRUE' THEN
                                INSERT INTO canon_e404_rscterr_tbl(
                                                    territory_name
                                                    ,terr_id
                                                    ,resource_id
                                                    ,resource_name
                                                    ,employee_number
                                                    ,mgr_resource_id
                                                    ,mgr_resource_name
                                                    ,mgr_employee_number
                                                    ,comments
                                                  ) values ( rscterr_rec.territory
                                                             ,rscterr_rec.terr_id
                                                             ,rscterr_rec.resource_id
                                                             ,rscterr_rec.resource_name
                                                             ,rscterr_rec.employee_number
                                                             ,l_tier_mgr_psn_cd 
                                                             ,l_tier_mgr_nm 
                                                             ,l_tier_mgr_psn_cd 
                                                             ,rscterr_rec.comments);

                             ELSE
                                INSERT INTO canon_e404_rscterr_tbl(
                                                    territory_name
                                                    ,terr_id
                                                    ,resource_id
                                                    ,resource_name
                                                    ,employee_number
                                                    ,mgr_resource_id
                                                    ,mgr_resource_name
                                                    ,mgr_employee_number
                                                    ,comments
                                                  ) values ( rscterr_rec.territory
                                                             ,rscterr_rec.terr_id
                                                             ,rscterr_rec.resource_id
                                                             ,rscterr_rec.resource_name
                                                             ,rscterr_rec.employee_number
                                                             ,NULL
                                                             ,NULL
                                                             ,NULL
                                                             ,'Manager resource is inactive / no Salesforce User ID in mapping table');
                            END IF;
                            EXCEPTION
                                WHEN OTHERS THEN
                                    dbms_output.put_line('Inserted CANON_E404_RSCTERR_TBL : ' ||sqlerrm);
                            END;
                            
                      END LOOP;
                      
      dbms_output.put_line('Inserted into CANON_E404_RSCTERR_TBL:' ||SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Inserted into CANON_E404_RSCTERR_TBL: ' || sql%rowcount,NULL,NULL,NULL,NULL);
      COMMIT;
      
BEGIN
   --to reprocess the error records from the previous run        
   UPDATE canon_e404_sf_acct_mapping_tbl e404_acct
      SET status_flag = 'U'
         ,oracle_load_date = SYSDATE
    WHERE sf_account_id IS NOT NULL
      AND status_flag = 'E'
      AND writing_employee_number IS NOT NULL ;
      dbms_output.put_line('Updated CANON_E404_SF_ACCT_MAPPING_TBL to reprocess:' ||SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Records Updated to reprocess: ' || sql%rowcount,NULL,NULL,NULL,NULL);
      
--   DELETE canon_e404_sf_acct_mapping_tbl
--   WHERE sf_account_id IS NULL
--     AND status_flag = 'I';
      
--    dbms_output.put_line(SQL%ROWCOUNT || 'deleted from CANON_E404_SF_ACCT_MAPPING_TBL' );
      
   COMMIT;
   
    END;


dbms_output.put_line('invoking insert_accounts....');
canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','invoking insert_accounts....',NULL,NULL,NULL,NULL);
 insert_accounts(l_i_ret_sts,l_i_msg_data);


dbms_output.put_line('invoking update_accounts....');
canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','invoking update_accounts....',NULL,NULL,NULL,NULL);
 update_accounts(l_u_ret_sts,l_u_msg_data);

  --set the return status
  IF l_i_ret_sts = 'S' AND l_u_ret_sts = 'S' THEN
    x_return_status := 'S';
  ELSE
    x_return_status := 'E';
  END IF;

  x_msg_data := NULL;

  IF l_i_msg_data IS NOT NULL THEN
    x_msg_data := x_msg_data || ' INSERT ACCOUNT Message data:' || l_i_msg_data;
  END IF;

  IF l_u_msg_data IS NOT NULL THEN
    x_msg_data := x_msg_data || ' UPDATE ACCOUNT Message data:' || l_u_msg_data;
  END IF;

   UPDATE canon_e404_sf_acct_mapping_tbl e404_acct
      SET writing_employee_number = 'CBSINTERFACE' 
         ,writing_territory = NULL
         ,status_flag = 'U'
         ,oracle_load_date = SYSDATE
    WHERE EXISTS(SELECT 1
                   FROM canon_e404_sf_user_mapping_tbl e404_user
                  WHERE e404_acct.writing_employee_number = e404_user.employee_number
                    AND e404_user.is_active = 'FALSE'
                    AND e404_user.status_flag = 'P'
                )
	  AND e404_acct.sf_account_id IS NOT NULL
      AND e404_acct.status_flag <> 'E';
	  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Records updated for inactive users: ' || sql%rowcount,NULL,NULL,NULL,NULL);      
	  
	 --for SFDC prospects get the sf_prospect_id as these will be updated based on the id and not based on location#
  MERGE INTO canon_e404_sf_acct_mapping_tbl e404
   USING(SELECT pros_rvw_id, rvw_pros_num
          FROM ds_acct_rvw_pros) pros
    ON(e404.sf_prospect_number = pros.rvw_pros_num AND e404.status_flag = 'I')
  WHEN MATCHED THEN
    UPDATE SET e404.sf_account_id = pros.pros_rvw_id ;
   dbms_output.put_line('Updated CANON_E404_SF_ACCT_MAPPING_TBL with prospect id : ' ||SQL%ROWCOUNT);
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Records updated for prospect id: ' || sql%rowcount,NULL,NULL,NULL,NULL);
  
   COMMIT;

  dbms_output.put_line('*************************************');
  dbms_output.put_line('Salesforce.com ACCOUNT Extraction');
  dbms_output.put_line('*************************************');

  dbms_output.put_line('Headers');
  dbms_output.put_line('-------');

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    x_return_status := 'E';
    x_msg_data      := 'Unexpected Error:'|| SUBSTR(SQLERRM,2000);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END extract_accounts;    


PROCEDURE insert_accounts(x_return_status   OUT VARCHAR2
                         ,x_msg_data        OUT VARCHAR2)
AS

  CURSOR ins_accts_cur
  IS
  SELECT *
    FROM canon_e404_sf_acct_temp_tbl;
    
    CURSOR dynamic_cols_cur (Customer_OR_Prospect_Value VARCHAR2)
       IS
       SELECT lbrrd.asg_trty_attrb_cd attribute_id
              ,lbrrd.asg_trty_attrb_nm attribute_value
        FROM line_biz_role_rank_decn lbrrd
             ,trty_grp_tp tgt
       WHERE lbrrd.ds_acct_tp_cd = Customer_OR_Prospect_Value
         AND lbrrd.trty_grp_tp_cd = tgt.trty_grp_tp_cd
         AND tgt.line_biz_tp_cd = 'ESS'
         AND lbrrd.src_line_biz_tp_cd = 'ESS'
    ORDER BY tgt.trty_grp_rank_num
             ,lbrrd.line_biz_rank_num; 
    
  TYPE ins_accts_cur_tbl_typ IS TABLE OF ins_accts_cur%ROWTYPE INDEX BY PLS_INTEGER;
  l_ins_accts_cur_tbl ins_accts_cur_tbl_typ;

    l_procedure_name VARCHAR2(20) := 'insert_accounts';
  l_acct_delete_count NUMBER := 0;
  l_acct_insert_count NUMBER := 0;

  l_w_terr_res_id                      s21_psn.psn_cd%TYPE; --S21
  l_w_terr_res_name                    s21_psn.psn_first_nm%TYPE; --S21
  l_w_employee_number                  s21_psn.psn_cd%TYPE; --S21
--  l_b_party_name                       ds_acct_cust.ds_acct_nm%TYPE;  --S21 changes
  l_b_party_name                       sell_to_cust.ds_acct_nm%TYPE;  --S21 changes --DB change
  l_b_location_id                     pty_loc_wrk.pty_loc_pk%TYPE; --S21
  l_b_street                          VARCHAR2(500);
  l_b_city                          bill_to_cust.cty_addr%TYPE; --S21
  l_b_state                          bill_to_cust.st_cd%TYPE; --S21
  l_b_country                          bill_to_cust.ctry_cd%TYPE; --S21
  l_b_postal_code                      bill_to_cust.post_cd%TYPE; --S21
  l_csmp_level                      prc_catg.prc_catg_nm%TYPE; --S21
  l_duns_number                       VARCHAR2(1000);
  l_sic_code                          VARCHAR2(1000);
  l_ult_duns_number                   VARCHAR2(1000);
  l_line_of_business                  VARCHAR2(1000);
  l_employees_total                  VARCHAR2(1000);
  l_annual_us_sales                   VARCHAR2(1000);
  l_customer_status                   VARCHAR2(10);
  l_error_flag                        VARCHAR2(1);
  l_cbs_customer_profile_name        VARCHAR2(2000);  
  l_reporting_name                  VARCHAR2(2000); 
  l_fm_dept_name                      VARCHAR2(20); 
  
  l_sql VARCHAr2(32767) :='';
  l_request_id        NUMBER;     
  
BEGIN


  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E404_SF_ACCT_TEMP_TBL';
  dbms_output.put_line('Truncated CANON_E404_SF_ACCT_TEMP_TBL');
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Truncated CANON_E404_SF_ACCT_TEMP_TBL',NULL,NULL,NULL,NULL);
  
  l_sql := NULL;
  
  BEGIN
  
   l_sql := 'INSERT INTO CANON_E404_SF_ACCT_TEMP_TBL (PARTY_NAME ' ||
            '                                     ,CATEGORY_CODE ' ||
            '                                     ,PARTY_ID  ' ||  
            '                                    ,PARTY_NUMBER ' || 
            '                                    ,PARTY_SITE_ID ' ||
            '                                   ,PARTY_SITE_NUMBER ' || 
            '                                     ,CUST_ACCOUNT_ID ' ||
            '                                     ,ACCOUNT_NUMBER ' ||
             '                                    ,CUST_ACCT_SITE_ID ' ||
             '                                    ,S_STREET ' ||
             '                                    ,S_CITY ' ||
             '                                    ,S_STATE ' ||
              '                                   ,S_COUNTRY ' ||
              '                                   ,S_POSTAL_CODE ' ||
              '                                   ,WRITING_TERRITORY ' ||
              '                                   ,WRITING_RESOURCE_ID '||
             '                                    ,WRITING_REP_NAME '||
             '                                    ,WRITING_EMPLOYEE_NUMBER '||
             '                                    ,DUNS_NUMBER '||
             '                                    ,PHONE '||
             '                                    ,FAX '||
             '                                    ,SIC_CODE '||
             '                                    ,ULT_DUNS_NUMBER '||
             '                                    ,LINE_OF_BUSINESS '||
             '                                    ,EMPLOYEES_TOTAL '||
             '                                    ,ANNUAL_US_SALES '||
             '                                    ,B_STREET '||
             '                                    ,B_CITY '||
             '                                    ,B_STATE '||
             '                                    ,B_COUNTRY '||
             '                                    ,B_POSTAL_CODE '||
             '                                    ,CUSTOMER_STATUS '||
             '                                    ,FM_DEPT_NAME '||
             '                                    ,DBA_NAME '||--new
             '                                    ,DNB_NAME '||--new
             '                                    ,COMPANY_SIC '||--new
             '                                    ,COMPANY_SIC_DESC '||--new
             '                                    ,GLN '||--new
             '                                    ,HQ_DUNS# '||--new
             '                                    ,PARENT_DUNS# '||--new
             '                                    ,ACCT_LEGAL_NAME '||--new
             '                                    ,SF_PROSPECT_NUMBER '||--new
             '                                    ,S_LOCATION_ID ' || -- added for FIN
             '                                    ,B_LOCATION_ID ' || -- added for FIN
             '                                    )    '||                     
                --Customer Accounts                                        
              '  SELECT distinct dac.ds_acct_nm ' ||
            -- '            ,dat.ds_acct_tp_nm '||--category_code 
			 '			  ,''CUSTOMER'' ' || --- changed on 11032017 for performance reasons
             '            ,dac.cmpy_pk '||--party_id
--             '            ,dac.ds_acct_num '||--party_number
             '            ,dac.sell_to_cust_cd '||--party_number -- DB Change
             '            ,plw.pty_loc_pk '||--party_site_id
             '            ,plw.loc_num '||--party_site_number
--             '            ,dac.ds_acct_cust_pk '||-- cust_account_id
              '            ,dac.sell_to_cust_pk '||-- cust_account_id -- DB Change
--             '            ,dac.ds_acct_num '||-- account_number
               '            ,dac.sell_to_cust_cd '||-- account_number -- DB Change
             '            ,plw.pty_loc_pk '||--cust_acct_site_id
             '            ,(plw.first_line_addr || '||
             '               (DECODE(plw.scd_line_addr, NULL, '''', '','')|| plw.scd_line_addr) || '||
             '               (DECODE(plw.third_line_addr, NULL, '''', '','')|| plw.third_line_addr) || '||
             '               (DECODE(plw.frth_line_addr, NULL, '''', '','')|| plw.frth_line_addr)) '|| --s_street --s_street
             '            ,plw.cty_addr '|| --s_city
             '            ,plw.st_cd '||--s_state
              '           ,plw.ctry_cd '||--s_country
             '            ,plw.post_cd '||--s_postal_code
             '            ,cert.territory_name '||-- writing_territory
             '            ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  ) '|| --writing_resource_id  
             '            ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  ) '||--writing_rep_name 
             '            ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  ) '||--writing_employee_number        
             '            ,plw.duns_num '||--duns_num
             '            ,dac.tel_num '|| --phone
             '            ,dac.fax_num '||--fax
             '            ,plw.indy_tp_cd '||--sic_code -- DB Change
             '            ,plw.ds_ult_duns_num '||--ult_duns_number -- Db Change
             '            ,NULL '||-- line_of_business ????
              '           ,plw.ds_loc_emp_num '||--employee_total -- Db Change
              '           ,plw.ds_loc_rev_amt'|| --annual_us_sales -- Db Change
             '            ,(btc.first_line_addr || '||
             '               (DECODE(btc.scd_line_addr,NULL,'''','','') || btc.scd_line_addr) || '||
             '               (DECODE(btc.third_line_addr,NULL,'''','','') || btc.third_line_addr) || '||
             '               (DECODE(btc.frth_line_addr, NULL,'''','','') || btc.frth_line_addr)) '||--b_street
             '            ,btc.cty_addr '||--b_city
             '            ,btc.st_cd '||--b_state
             '            ,btc.ctry_cd '||--b_country
             '            ,btc.post_cd '||--b_postal_code
             '            ,dac.ds_acct_actv_cust_flg '||--customer_status
--          '               ,dac.coa_cc_cd '||--FM_Dept_Name --not available in EXTN5
             '            ,NULL '||
             '            ,dac.dba_nm '||--dba_name
             '            ,dac.ds_acct_duns_nm '||-- dnb_name
             '            ,dac.ds_cust_sic_cd '|| --company_sic
             '            ,it.indy_tp_nm '||--company_sic_desc
             '            ,plw.gln_num  '||-- GLN
             '            ,plw.hq_duns_num '||--HQ_DUNS -- DB Change
            '             ,plw.ds_prnt_duns_num '||--parent_duns# -- DB Change
             '            ,dac.ds_acct_legal_nm '||--account legal name
             '            ,(CASE WHEN dxat.ds_xref_acct_tp_nm = ''SFDC'' THEN dxa.ds_xref_acct_cd ELSE NULL END) '|| --SF_PROSPECT_NUMBER
             '            ,stc.ship_to_cust_cd ' || --s_location_id
             '            ,btc.bill_to_cust_cd ' || --b_location_id
--             '   FROM ds_acct_cust dac '||
                '   FROM sell_to_cust dac '|| -- DB Change
             '        ,cmpy c '||
             '        ,acct_loc al '||
             '        ,bill_to_cust btc '||
             '        ,ship_to_cust stc '||
--             '        ,ds_ship_to_cust dstc '|| -- DB Change
             '        ,pty_loc_wrk  plw '||
--             '        ,ds_pty_loc_wrk dplw '|| -- DB Change
           --  '        ,ds_acct_tp dat '|| -- for performance reasons
             '        ,canon_e404_rscterr_tbl cert '|| 
             '        ,indy_tp it '||
             '        ,ds_xref_acct dxa '||
             '        ,ds_xref_acct_tp dxat '||
             '        ,acct_trty_resrc_asg atra ' ||
             '  WHERE c.cmpy_pk = dac.cmpy_pk '||
--             '    AND dac.ds_acct_num IN (''1000124'',''1277399'',''1002098'') ' ||
             '    AND dac.sell_to_cust_cd = al.ds_acct_num '|| -- DB Change
           --  '    AND dac.ds_acct_tp_cd = dat.ds_acct_tp_cd '||  commented for Performance reasons
             '    AND dac.glbl_cmpy_cd = ''ADB'' '||
             '    AND dac.ezcancelflag = ''0'' '||
             '    AND al.glbl_cmpy_cd = ''ADB'' ' ||
             '    AND al.ezcancelflag = ''0'' ' ||
             '    AND plw.glbl_cmpy_cd = ''ADB'' '||
             '    AND plw.ezcancelflag = ''0'' '||
             '    AND al.loc_num = plw.loc_num '||
             '    AND plw.loc_num = stc.loc_num '||
--             '    AND stc.ship_to_cust_pk = dstc.ship_to_cust_pk '|| -- DB Change
             '    AND stc.reln_bill_to_cust_cd = btc.bill_to_cust_cd(+) '|| -- DB Change
--             '    AND plw.pty_loc_pk = dplw.pty_loc_pk '|| -- DB Change
        --     '    AND plw.line_biz_tp_cd = ''ESS'' ' ||  -- DB Change
             '    AND dac.indy_tp_cd = it.indy_tp_cd(+) '||
          --   '    AND cert.terr_id = get_org_cd('10',dac.ds_acct_num,plw.loc_num) --10 for Customer  and 0 for prospect   
--        --         AND cert.terr_id = '20005'
          --   '   -- AND dac.ds_acct_sts_cd = '0'  --Account active
             '    AND plw.loc_num = dxa.loc_num(+) '||
             '    AND dxa.ds_xref_acct_tp_cd(+) = dxat.ds_xref_acct_tp_cd '||
             '    AND dxat.ds_xref_acct_tp_nm(+) = ''SFDC'' '||
             '    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, ''yyyymmdd''),SYSDATE)) '|| -- location is active 
             '    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, ''yyyymmdd''),SYSDATE)) '|| -- location is active
             '    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(stc.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(stc.eff_THRU_DT, ''yyyymmdd''),SYSDATE)) '|| -- shipto is active
             '    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(btc.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(btc.eff_THRU_DT, ''yyyymmdd''),SYSDATE)) '||-- billto is active
             '    AND NOT EXISTS (SELECT 1     '||                   
             '                     FROM canon_e404_sf_acct_mapping_tbl e404 '||                     
             '                     WHERE e404.party_site_number = plw.loc_num  '||                      
              '                      AND sf_account_id IS NOT NULL ) '||
			  '     AND EXISTS (SELECT 1  '||
            '                   FROM acct_trty_role_asg atra ' ||
            '                        ,trty_grp_tp tgt ' ||
              '                WHERE atra.loc_num = plw.loc_num ' ||
              '                  AND atra.line_biz_role_tp_cd IS NOT NULL ' ||
              '                  AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd '||
              '                  AND tgt.line_biz_tp_cd = ''ESS'' ) ' ||
--              '    AND atra.ds_acct_num = dac.ds_acct_num ' ||
              '    AND atra.ds_acct_num = dac.sell_to_cust_cd ' || -- DB Change
           '    AND atra.loc_num = plw.loc_num ' ;
    l_sql := l_sql || 'AND (CASE';

    FOR dynamic_cols_rec IN dynamic_cols_cur('10')
    Loop
            l_sql := l_sql ||
           ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL ' || 
            ' THEN atra.' ||dynamic_cols_rec.attribute_value  || ''; 
    END LOOP ;
    l_sql := l_sql ||' ELSE '''' END ) = cert.terr_id(+) ';
    l_sql := l_sql ||
              '  UNION ' ||
                --Prospect Accounts
              '  SELECT distinct dac.ds_acct_nm '|| --party_name
             -- '           ,dat.ds_acct_tp_nm '|| --category_code
			   '           ,''PROSPECT'' '|| --category_code  --updated on 11032017 for performance reasons
              '           ,dac.cmpy_pk '||  --party_id
              '           ,dac.ds_acct_num '|| --party_number 
              '           ,plw.pty_loc_pk '|| --party_site_id
              '           ,plw.loc_num '|| --party_site_number
              '           ,dac.ds_acct_pros_pk '|| -- cust_account_id
               '          ,dac.ds_acct_num '|| -- account_number
               '          ,plw.pty_loc_pk '|| --cust_acct_site_id
               '          ,(plw.first_line_addr ||  '||
                '            (DECODE(plw.scd_line_addr, NULL, '''', '','')|| plw.scd_line_addr) || '||
                '            (DECODE(plw.third_line_addr, NULL, '''', '','')|| plw.third_line_addr) || '||
                '            (DECODE(plw.frth_line_addr, NULL, '''', '','')|| plw.frth_line_addr)) '||-- s_street --s_street
                '         ,plw.cty_addr '|| --s_city
                '         ,plw.st_cd '|| --s_state
                '         ,plw.ctry_cd '|| --s_country
                '         ,plw.post_cd '|| --s_postal_code
                '         ,cert.territory_name '|| -- writing_territory
                '         ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  )  writing_resource_id  '||
                '         ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  )  writing_rep_name '||
                '         ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  )  writing_employee_number '||       
                '         ,plw.duns_num '|| --duns_num
                '         ,dac.tel_num '|| --phone
                '         ,dac.fax_num '|| --fax
                '         ,plw.indy_tp_cd '||--sic_code -- DB Change
                '         ,plw.ds_ult_duns_num '||--ult_duns_number -- DB Change
                '         ,NULL '||-- line_of_business ????
                '         ,plw.ds_loc_emp_num '|| --employee_total -- DB Change
                '         ,plw.ds_loc_rev_amt '|| --annual_us_sales -- DB Change
                '         ,(plw.first_line_addr ||  '||
                '            (DECODE(plw.scd_line_addr, NULL, '''', '','')|| plw.scd_line_addr) || '||
                '            (DECODE(plw.third_line_addr, NULL, '''', '','')|| plw.third_line_addr) || '||
                '            (DECODE(plw.frth_line_addr, NULL, '''', '','')|| plw.frth_line_addr)) '||--(btc.first_line_addr||btc.scd_line_addr||btc.third_line_addr||btc.frth_line_addr) --b_street
                '         ,plw.cty_addr '||--btc.cty_addr --b_city
                '         ,plw.st_cd '||--btc.st_cd --b_state
                '         ,plw.ctry_cd '||--btc.ctry_cd --b_country
                '         ,plw.post_cd '||--btc.post_cd --b_postal_code
                '         ,dac.ds_acct_actv_cust_flg '||--customer_status
--              '           ,dac.coa_cc_cd -- FM_Dept_Name
                '         ,NULL '||
                 '        ,dac.dba_nm '|| --dba_name
                '         ,dac.ds_acct_duns_nm '|| -- dnb_name
                '         ,dac.ds_cust_sic_cd '|| --company_sic
                '         ,it.indy_tp_nm '||--company_sic_desc
                '         ,plw.gln_num '|| -- GLN
                 '        ,plw.hq_duns_num '|| --HQ_DUNS -- DB Change
--               '          ,NULL
                 '        ,plw.ds_prnt_duns_num '|| --parent_duns# -- DB Change
--               '          ,NULL
--              '           ,dac.ds_acct_legal_name --account legal name
                  '       ,NULL '||
                 '        ,(CASE WHEN dxat.ds_xref_acct_tp_nm = ''SFDC'' THEN dxa.ds_xref_acct_cd ELSE NULL END) '||--sf_prospect_number 
                 '        ,NULL ' || --s_location_id -- Prospects are not tied to ship_to
                 '        ,NULL ' || --b_location_id -- Prospects are not tied to ship_to
               ' FROM ds_acct_pros dac '||
         --       '     ,pty_loc_wrk  plw '|| using pros_pty_loc_wrk as suggested by S21
		          '     ,pros_pty_loc_wrk plw ' ||
--                 '    ,ds_pty_loc_wrk dplw '|| -- DB Change
            --      '   ,ds_acct_tp dat '|| commented for performance reasons
                '     ,cmpy c '||
               -- '     ,acct_loc al '||  no need of this as all the prospect loc will be in pros_pty_loc_wrk
                '     ,canon_e404_rscterr_tbl cert  '||
                '     ,INDY_TP it '||
                '     ,ds_xref_acct dxa '||
                '     ,ds_xref_acct_tp dxat '||
                '     ,pros_trty_resrc_asg atra '||
           --     '     ,ship_to_cust stc ' || -- Prospects are not tied to ship_to
               ' where c.cmpy_pk = dac.cmpy_pk '||
--               '    AND dac.ds_acct_num IN (''1000124'',''1277399'',''1002098'') ' ||
            --   '  and dac.ds_acct_num = al.ds_acct_num '||
               '  and dac.loc_num = plw.loc_num '||
               '  and dac.glbl_cmpy_cd = ''ADB'' '||
               '  AND dac.ezcancelflag = ''0'' ' ||
               '  AND plw.glbl_cmpy_cd = ''ADB'' ' ||
               '  AND plw.ezcancelflag = ''0'' ' ||
--               '  AND dplw.glbl_cmpy_cd = ''ADB'' ' || -- DB Change
--               '  AND dplw.ezcancelflag = ''0'' ' || -- DB Change
            --   '  AND al.glbl_cmpy_cd = ''ADB'' ' ||
            --   '  AND al.ezcancelflag = ''0'' ' ||
               '  AND c.glbl_cmpy_cd = ''ADB'' ' ||
               '  AND c.ezcancelflag = ''0'' ' ||
              -- '  AND stc.ezcancelflag = ''0'' ' ||
              -- '  AND stc.glbl_cmpy_cd = ''ADB'' ' ||
             --  '       and dac.ds_acct_tp_cd = dat.ds_acct_tp_cd '|| commented for performance reasons
--               '       AND plw.pty_loc_pk = dplw.pty_loc_pk '|| -- DB Change
      --         '       AND plw.line_biz_tp_cd = ''ESS'' ' ||
               '       AND dac.indy_tp_cd = it.indy_tp_cd(+) '||
               '       AND plw.loc_num = dxa.loc_num(+) '||
          --     '       AND plw.loc_num = stc.loc_num ' ||
               '       AND dxa.ds_xref_acct_tp_cd(+) = dxat.ds_xref_acct_tp_cd '||
               '       AND dxat.ds_xref_acct_tp_nm(+) = ''SFDC'' '||
--             '          AND cert.terr_id = '20005'
              -- '        AND cert.terr_id = get_org_cd('0',dac.ds_acct_num,plw.loc_num) '||
                -- AND dac.ds_acct_sts_cd = '0' --Account Active
               '  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, ''yyyymmdd''),SYSDATE)) '||-- location is active
               '  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, ''yyyymmdd''),SYSDATE)) '||
--             '    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(stc.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(stc.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
               '  AND NOT EXISTS (SELECT 1  '||                      
               '                   FROM canon_e404_sf_acct_mapping_tbl e404   '||                    
               '                   WHERE e404.party_site_number = plw.loc_num '||                       
               '                     AND sf_account_id IS NOT NULL ) ' ||
			    '     AND EXISTS (SELECT 1  '||
                '                   FROM pros_trty_role_asg atra ' ||
                '                        ,trty_grp_tp tgt ' ||
                  '                WHERE atra.loc_num = plw.loc_num ' ||
                  '                  AND atra.line_biz_role_tp_cd IS NOT NULL ' ||
                  '                  AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd '||
                  '                  AND tgt.line_biz_tp_cd = ''ESS'' ) ' ||
               '  AND atra.ds_acct_num = dac.ds_acct_num ' ||
               '    AND atra.loc_num = plw.loc_num ' ;
    l_sql := l_sql || 'AND (CASE';

    FOR dynamic_cols_rec IN dynamic_cols_cur('0')
    Loop
            l_sql := l_sql ||
           ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL ' || 
            ' THEN atra.' ||dynamic_cols_rec.attribute_value  || ''; 
    END LOOP ;
    l_sql := l_sql ||' ELSE '''' END ) = cert.terr_id(+) ';
               
    dbms_output.put_line('sql for insert Account: ' ||l_sql);
    --canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','l_sql: '|| l_sql,null,null,null,null);           
    
    EXECUTE IMMEDIATE l_sql;
    
    dbms_output.put_line('Inserted into CANON_E404_SF_ACCT_TEMP_TBL: ' ||SQL%ROWCOUNT);
canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Records inserted into CANON_E404_SF_ACCT_TEMP_TBL: ' ||sql%rowcount,NULL,NULL,NULL,NULL);	

  COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
        dbms_output.put_line('Insert into Temp: ' || sqlerrm);
  END;
  
   dbms_stats.gather_table_stats('S21_CSA_EXTN', 'CANON_E404_SF_ACCT_TEMP_TBL', options=>'GATHER'); --as per DBAs suggestion 7/18/2018
   
  BEGIN
  
  --customer_profile and reporting_name -- account groups
  MERGE INTO canon_e404_sf_acct_temp_tbl e404
  USING (SELECT dag.ds_acct_grp_nm group_name
                ,daga.ds_acct_num party_number
           FROM ds_acct_grp_asg daga
                ,ds_acct_grp dag
           WHERE daga.glbl_cmpy_cd = 'ADB'
             AND daga.ds_acct_grp_cd = dag.ds_acct_grp_cd
             AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(daga.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(daga.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
        )acct_grp
    ON(e404.party_number = acct_grp.party_number)
   WHEN MATCHED THEN
   UPDATE SET e404.cbs_customer_profile_name = DECODE(e404.cbs_customer_profile_name, NULL, acct_grp.group_name, (e404.cbs_customer_profile_name || ',' || acct_grp.group_name))
             ,e404.reporting_name = DECODE(e404.reporting_name, NULL, acct_grp.group_name, (e404.reporting_name || ',' || acct_grp.group_name));
  dbms_output.put_line('Updated CANON_E404_SF_ACCT_TEMP_TBL with customer_profile and reporting names: ' ||SQL%ROWCOUNT);
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Updated CANON_E404_SF_ACCT_TEMP_TBL with customer_profile and reporting names: ' ||sql%rowcount,NULL,NULL,NULL,NULL);
  EXCEPTION
    WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,SQLERRM);
	canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
        dbms_output.put_line('Insert into Temp: ' || sqlerrm);
  END;

  DELETE
    FROM canon_e404_sf_acct_mapping_tbl e404
   WHERE e404.sf_account_id IS NULL;

  l_acct_delete_count := SQL%ROWCOUNT;

  COMMIT;
  --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,'INSERT_ACCOUNTS','5',NULL,NULL,NULL,NULL,NULL);
  
  OPEN ins_accts_cur;
  LOOP
    FETCH ins_accts_cur BULK COLLECT INTO l_ins_accts_cur_tbl LIMIT 10000;

    FOR i IN 1 .. l_ins_accts_cur_tbl.COUNT 
    LOOP
      l_w_terr_res_id     := NULL; 
      l_w_terr_res_name   := NULL; 
      l_w_employee_number := NULL; 
      l_b_party_name      := NULL;
      l_b_location_id     := NULL;
      l_b_street          := NULL;
      l_b_city            := NULL;
      l_b_state           := NULL;
      l_b_country         := NULL;
      l_b_postal_code     := NULL;
      l_csmp_level        := NULL;
      l_duns_number       := NULL;
      l_sic_code          := NULL;
      l_ult_duns_number   := NULL;
      l_line_of_business  := NULL;
      l_employees_total   := NULL;
      l_annual_us_sales   := NULL;
      l_customer_status   := NULL;
      l_error_flag        := 'N';
      l_cbs_customer_profile_name := NULL; 
      l_fm_dept_name := NULL; 
    
      
       IF (l_ins_accts_cur_tbl(i).writing_employee_number IS NULL )THEN 
        l_error_flag := 'Y';
        --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,'EXTRACT_ACCOUNTS:INSERT_ACCOUNTS','Party ID:'||l_ins_accts_cur_tbl(i).party_id,'Party Site ID:'||l_ins_accts_cur_tbl(i).party_site_id,'Writing Territory:'||l_ins_accts_cur_tbl(i).writing_territory,NULL,NULL,'Owner (Writing Rep) is missing');
      END IF;

      -- get CSMP data
      get_csmp_level(l_ins_accts_cur_tbl(i).account_number,l_csmp_level);  --updated to account_number on 8/18
	  get_fm_dept_name(L_INS_ACCTS_CUR_TBL(I).ACCOUNT_NUMBER,l_fm_dept_name);
      

    --INSERT INTO
    BEGIN
    
    INSERT
      INTO canon_e404_sf_acct_mapping_tbl
          (party_name
           ,category_code
           ,party_id
           ,party_number
           ,party_site_id
           ,party_site_number
           ,cust_account_id
           ,account_number
           ,cust_acct_site_id
           ,s_street
           ,s_city
           ,s_state
           ,s_country
           ,s_postal_code
           ,b_street
           ,b_city
           ,b_state
           ,b_country
           ,b_postal_code
           ,writing_territory
           ,writing_resource_id
           ,writing_rep_name
           ,writing_employee_number
           ,phone
           ,fax
           ,csmp_level
           ,duns_number
           ,sic_code
           ,ult_duns_number
           ,line_of_business
           ,employees_total
           ,annual_us_sales
           ,customer_status
--           ,load_status
           ,status_flag
           ,oracle_load_date
           ,sf_account_id
--           ,run_status
           ,sf_update_date
           ,comments
           ,cbs_customer_profile_name
           ,reporting_name
           ,fm_dept_name
           ,dba_name --new in S21
           ,dnb_name --new
           ,company_sic --new 
           ,company_sic_desc --new 
           ,gln --new
           ,hq_duns# --new
           ,parent_duns# --new
           ,acct_legal_name --new 
           ,sf_prospect_number --new
           ,sf_link_status --new 
           ,sf_link_date --new 
           ,sf_link_comments --new 
           ,s_location_id
           ,b_location_id
          )
    VALUES(l_ins_accts_cur_tbl(i).party_name
           ,l_ins_accts_cur_tbl(i).category_code
           ,l_ins_accts_cur_tbl(i).party_id
           ,l_ins_accts_cur_tbl(i).party_number
           ,l_ins_accts_cur_tbl(i).party_site_id
           ,l_ins_accts_cur_tbl(i).party_site_number
           ,l_ins_accts_cur_tbl(i).cust_account_id
           ,l_ins_accts_cur_tbl(i).account_number
           ,l_ins_accts_cur_tbl(i).cust_acct_site_id
           ,l_ins_accts_cur_tbl(i).s_street
           ,l_ins_accts_cur_tbl(i).s_city
           ,l_ins_accts_cur_tbl(i).s_state
           ,l_ins_accts_cur_tbl(i).s_country
           ,l_ins_accts_cur_tbl(i).s_postal_code
           ,l_ins_accts_cur_tbl(i).b_street
           ,l_ins_accts_cur_tbl(i).b_city
           ,l_ins_accts_cur_tbl(i).b_state
           ,l_ins_accts_cur_tbl(i).b_country
           ,l_ins_accts_cur_tbl(i).b_postal_code
           ,l_ins_accts_cur_tbl(i).writing_territory
           ,l_ins_accts_cur_tbl(i).writing_resource_id
           ,l_ins_accts_cur_tbl(i).writing_rep_name
           ,l_ins_accts_cur_tbl(i).writing_employee_number
           ,l_ins_accts_cur_tbl(i).phone
           ,l_ins_accts_cur_tbl(i).fax
           ,l_csmp_level 
           ,l_ins_accts_cur_tbl(i).duns_number
           ,l_ins_accts_cur_tbl(i).sic_code
           ,l_ins_accts_cur_tbl(i).ult_duns_number
           ,l_ins_accts_cur_tbl(i).line_of_business
           ,l_ins_accts_cur_tbl(i).employees_total
           ,l_ins_accts_cur_tbl(i).annual_us_sales
           ,l_ins_accts_cur_tbl(i).customer_status
           ,DECODE(l_error_flag,'Y','E','I') --status_flag
           ,SYSDATE --oracle_load_date
           ,NULL --sf_account_id
--           ,DECODE(l_error_flag,'Y','E',NULL) --run_status
           ,DECODE(l_error_flag,'Y',SYSDATE,NULL) --sf_update_date
           ,DECODE(l_error_flag,'Y','Owner (Writing Rep) is missing',NULL) --comments
           ,l_ins_accts_cur_tbl(i).cbs_customer_profile_name
           ,l_ins_accts_cur_tbl(i).reporting_name
           ,l_fm_dept_name
           ,l_ins_accts_cur_tbl(i).dba_name
           ,l_ins_accts_cur_tbl(i).dnb_name
           ,l_ins_accts_cur_tbl(i).company_sic
           ,l_ins_accts_cur_tbl(i).company_sic_desc
           ,l_ins_accts_cur_tbl(i).gln
           ,l_ins_accts_cur_tbl(i).hq_duns#
           ,l_ins_accts_cur_tbl(i).parent_duns#
           ,l_ins_accts_cur_tbl(i).acct_legal_name
           ,l_ins_accts_cur_tbl(i).sf_prospect_number
           ,NULL --sf_link_status
           ,NULL --sf_link_date
           ,NULL -- sf_link_comments
           ,l_ins_accts_cur_tbl(i).s_location_id
           ,l_ins_accts_cur_tbl(i).b_location_id
          );
    EXCEPTION
    WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR','PSN: ' ||l_ins_accts_cur_tbl(i).party_site_number,NULL,NULL,NULL,SQLERRM);
        dbms_output.put_line('Insert into Temp: ' || sqlerrm);
    END;
    END LOOP;

    COMMIT;

    EXIT WHEN l_ins_accts_cur_tbl.COUNT < 10000;

  END LOOP;

  CLOSE ins_accts_cur;

   
  /*****************  -- Lease Expiration depends on E404 IB table, uncomment after IB tables are created ************
    MERGE
     INTO canon_e404_sf_acct_mapping_tbl e404_acct
    USING(SELECT e404_acct.party_site_id
                ,e404_ib.lease_expiration_date
            FROM canon_e404_sf_acct_mapping_tbl e404_acct
                ,(SELECT cbs_party_site_number
                        ,MIN(lease_expiration_date) lease_expiration_date
                    FROM canon_e404_install_base_ob_tbl
                   WHERE lease_expiration_date IS NOT NULL
                   GROUP BY cbs_party_site_number
                 ) e404_ib
           WHERE e404_acct.party_site_number = e404_ib.cbs_party_site_number
             AND e404_acct.writing_employee_number IS NOT NULL
             AND e404_acct.load_status = 'I'
             AND NVL(e404_acct.lease_expiration_date, TRUNC(SYSDATE-1000)) <> e404_ib.lease_expiration_date
          ) e404_ib
       ON(e404_acct.party_site_id = e404_ib.party_site_id)
     WHEN MATCHED THEN
   UPDATE
      SET lease_expiration_date = e404_ib.lease_expiration_date;

   --fnd_file.put_line(fnd_file.log, 'Closest Lease Expiration Date - No. of Records Updated - '||SQL%ROWCOUNT);      
      
   COMMIT;

  -- fnd_file.put_line(fnd_file.log, 'Closest Lease Expiration Date - END - '||TO_CHAR('MM/DD/YYYY HH:MI:SS AM'));
 ***********************************************************************************************************************/
      
  SELECT COUNT(*)
    INTO l_acct_insert_count
    FROM canon_e404_sf_acct_mapping_tbl e404
--   WHERE e404.load_status = 'I'
   WHERE e404.status_flag = 'I'
--     AND run_status IS NULL
     AND sf_account_id IS NULL;

  x_return_status := 'S';

  dbms_output.put_line('Records Deleted  : '|| l_acct_delete_count);
  dbms_output.put_line('Records Extracted FOR INSERT: '|| l_acct_insert_count);
  dbms_output.put_line(' ');
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Records Deleted: ' ||l_acct_delete_count,NULL,NULL,NULL,NULL);
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Records Extracted FOR INSERT: ' ||l_acct_insert_count,NULL,NULL,NULL,NULL);

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    x_return_status := 'E';
    x_msg_data      := 'Unexpected ERROR:'|| SUBSTR(SQLERRM,2000);
    dbms_output.put_line(x_msg_data);
	canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SUBSTR(SQLERRM,2000));

END insert_accounts;



    
                      
PROCEDURE get_csmp_level(v_party_id     IN  VARCHAR2
                        ,x_csmp_level  OUT  VARCHAR2
                        )
AS
	l_procedure_name VARCHAR2(25) := 'GET_CSMP_LEVEL';
BEGIN
  SELECT pc.prc_catg_nm
    INTO x_csmp_level
    FROM csmp_contr cc
         ,prc_catg pc
   WHERE v_party_id = cc.ds_acct_num
     AND cc.glbl_cmpy_cd = 'ADB'
     AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(cc.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(cc.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
     AND pc.prc_catg_cd = cc.prc_catg_cd
     AND pc.glbl_cmpy_cd = 'ADB';
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    x_csmp_level    := NULL;
    --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure,'v_party_id:'||v_party_id,NULL,NULL,NULL,NULL,'No Data Found Error:'||SQLERRM);
  WHEN OTHERS THEN
    x_csmp_level    := NULL;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR','v_party_id:'||v_party_id,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END get_csmp_level;

PROCEDURE update_accounts(x_return_status   OUT VARCHAR2
                         ,x_msg_data        OUT VARCHAR2
                         )
AS
 
  /*CURSOR dynamic_cols_cur (Customer_OR_Prospect_Value VARCHAR2)
       IS
       SELECT lbrrd.asg_trty_attrb_cd attribute_id
              ,lbrrd.asg_trty_attrb_nm attribute_value
        FROM line_biz_role_rank_decn lbrrd
             ,trty_grp_tp tgt
       WHERE lbrrd.ds_acct_tp_cd = Customer_OR_Prospect_Value
         AND lbrrd.trty_grp_tp_cd = tgt.trty_grp_tp_cd
    ORDER BY tgt.trty_grp_rank_num
             ,lbrrd.line_biz_rank_num; */

  CURSOR dynamic_cols_cur (Customer_OR_Prospect_Value VARCHAR2)
       IS
       SELECT lbrrd.asg_trty_attrb_cd attribute_id
              ,lbrrd.asg_trty_attrb_nm attribute_value
        FROM line_biz_role_rank_decn lbrrd
             ,trty_grp_tp tgt
       WHERE lbrrd.ds_acct_tp_cd = Customer_OR_Prospect_Value
         AND lbrrd.trty_grp_tp_cd = tgt.trty_grp_tp_cd
         AND tgt.line_biz_tp_cd = 'ESS'
         AND lbrrd.src_line_biz_tp_cd = 'ESS'
    ORDER BY tgt.trty_grp_rank_num
             ,lbrrd.line_biz_rank_num;			 
			 
  CURSOR upd_accts_cur
  IS
  SELECT *
    FROM canon_e404_sf_acct_temp_tbl;


  TYPE upd_accts_cur_tbl_typ IS TABLE OF upd_accts_cur%ROWTYPE INDEX BY PLS_INTEGER;
  l_upd_accts_cur_tbl upd_accts_cur_tbl_typ;

  l_acct_update_count NUMBER := 0;
  l_update_flag       NUMBER := 0;

  l_w_terr_res_id     s21_psn.psn_cd%TYPE;
  l_w_terr_res_name   VARCHAR2(500);
  l_w_employee_number s21_psn.psn_num%TYPE;
  
  --l_b_party_name      hz_parties.party_name%TYPE; --not needed
  --l_b_location_id     hz_locations.location_id%TYPE; --not needed
  l_b_street          VARCHAR2(1000);
  l_b_city            bill_to_cust.cty_addr%TYPE;
  l_b_state           bill_to_cust.st_cd%TYPE;
  l_b_country         bill_to_cust.ctry_cd%TYPE;
  l_b_postal_code     bill_to_cust.post_cd%TYPE;
  l_csmp_level        prc_catg.prc_catg_nm%TYPE;
  --l_csmp_level        varchar2(200) := NULL; 
  l_duns_number       VARCHAR2(1000);
  l_sic_code          VARCHAR2(1000);
  l_ult_duns_number   VARCHAR2(1000);
  l_line_of_business  VARCHAR2(1000);
  l_employees_total   VARCHAR2(1000);
  l_annual_us_sales   VARCHAR2(1000);
  l_customer_status   VARCHAR2(50);
  l_error_flag        VARCHAR2(1);
  l_cbs_customer_profile_name VARCHAR2(3999); 
  l_reporting_name        VARCHAR2(3999); 
  l_fm_dept_name        VARCHAR2(1000); 
  
  l_sql varchar2(32767) := '';
  l_request_id    NUMBER;    
  
  l_procedure_name  VARCHAR2(50) ; 
  
BEGIN
  --l_request_id    := fnd_global.conc_request_id;  --CL ???
  l_procedure_name := 'update_accounts' ;
   

  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E404_SF_ACCT_TEMP_TBL';
  dbms_output.put_line('Truncated CANON_E404_SF_ACCT_TEMP_TBL');
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Truncated CANON_E404_SF_ACCT_TEMP_TBL',NULL,NULL,NULL,NULL);

  l_sql := NULL;

  l_sql := 'INSERT INTO CANON_E404_SF_ACCT_TEMP_TBL (PARTY_NAME ' 
                                            || '    ,CATEGORY_CODE ' 
                                            || '   ,PARTY_ID ' 
                                            ||    ',PARTY_NUMBER ' ||
                                                ' ,PARTY_SITE_ID ' ||
                                                ' ,PARTY_SITE_NUMBER ' ||
                                                ' ,CUST_ACCOUNT_ID ' ||
                                                ' ,ACCOUNT_NUMBER ' ||
                                                ' ,CUST_ACCT_SITE_ID ' ||
                                                ' ,s_location_id ' ||
                                                ' ,S_STREET ' ||
                                                ' ,S_CITY ' ||
                                                ' ,S_STATE ' ||
                                                ' ,S_COUNTRY ' ||
                                                ' ,S_POSTAL_CODE ' ||
                                                ' ,b_location_id ' ||
                                                '  ,B_STREET ' ||
                                                ' ,B_CITY ' ||
                                                ' ,B_STATE ' ||
                                                ' ,B_COUNTRY  ' ||
                                                ' ,B_POSTAL_CODE ' ||
                                                ' ,WRITING_TERRITORY ' ||
                                                ' ,WRITING_RESOURCE_ID ' ||
                                                ' ,WRITING_REP_NAME ' ||
                                                ' ,WRITING_EMPLOYEE_NUMBER ' ||
                                                ' ,PHONE ' ||
                                                ' ,FAX ' ||
                                                ' ,CSMP_LEVEL ' ||
                                                ' ,DUNS_NUMBER ' ||
                                                ' ,SIC_CODE ' ||
                                                ' ,ULT_DUNS_NUMBER ' ||
                                                ' ,LINE_OF_BUSINESS ' ||
                                                ' ,EMPLOYEES_TOTAL ' ||
                                                ' ,ANNUAL_US_SALES ' ||
                                                ' ,CUSTOMER_STATUS ' ||
                                                ' ,STATUS_FLAG ' ||
                                                ' ,ORACLE_LOAD_DATE ' ||
                                                ' ,SF_ACCOUNT_ID ' ||
                                                ' ,SF_UPDATE_DATE ' ||
                                                ' ,CBS_CUSTOMER_PROFILE_NAME ' ||
                                                ' ,REPORTING_NAME ' ||
                                                ' ,FM_DEPT_NAME ' || 
                                                ' ,DBA_NAME ' || --new
                                                ' ,DNB_NAME ' || --new
                                                ' ,COMPANY_SIC ' || --new
                                                ' ,COMPANY_SIC_DESC ' || --new
                                                ' ,GLN  ' ||--new
                                                ' ,HQ_DUNS#  ' ||--new
                                                ' ,PARENT_DUNS#  ' ||--new
                                                ' ,ACCT_LEGAL_NAME ' || --new
                                                ' ,SF_PROSPECT_NUMBER ' ||
                                                ' ,PNAME ' ||
                                                ' ,CC ' ||
                                                ' ,PID ' ||
                                                ' ,PNUM ' ||
                                                ' ,PSID ' ||
                                                ' ,PSNUM ' ||
                                                ' ,CAID ' ||
                                                ' ,ANUM ' ||
                                                ' ,CASID ' ||
                                                ' ,WT ' ||
                                                ' ,DN ' ||
                                                ' ,LOCATION_ID ' ||
                                                ' ,STREET ' ||
                                                ' ,CITY ' ||
                                                ' ,STATE ' ||
                                                ' ,COUNTRY ' ||
                                                ' ,POSTAL_CODE ' ||
                                                ' ,PH_NUM ' ||
                                                ' ,FAX_NUM ' ||
                                                ' ,WT_RESOURCE_ID ' ||
                                                ' ,WT_REP_NAME ' ||
                                                ' ,WT_EMPLOYEE_NUMBER ' ||
                                                ' ,NEW_SIC1 ' ||
                                                ' ,NEW_ULT_DUNS_NUMBER ' ||
                                                ' ,NEW_LINE_OF_BUSINESS ' ||
                                                ' ,NEW_EMPLOYEES_TOTAL ' ||
                                                ' ,NEW_ANNUAL_US_SALES ' ||
                                                ' ,CUSTSTATUS ' ||
                                                ' ,CUSTPROFNAME ' || -- customer_profile_name
                                                ' ,REPRTNGNAME ' ||-- reporting_name
                                                ' ,FMDEPTNAME ' ||
                                                ' ,DBANM ' ||--dba_name
                                                ' ,DUNSNM ' ||-- dnb_name
                                                ' ,CUSTSICCD ' ||--company_sic
                                                ' ,CUSTSICNM ' ||--company_sic_desc
                                                ' ,GLNNUM  ' ||-- GLN
                                                ' ,HQDUNS ' || --HQ_DUNS
                                                ' ,PRNTDUNS ' ||--parent_duns#
                                                ' ,LEGALNAME ' ||--account legal name
                                                ' ,sfProsNum ' ||
                                                ' )  ' ||
           ' SELECT e404.party_name  ' ||
            '      ,e404.category_code ' ||
            '      ,e404.party_id  ' ||
            '      ,e404.party_number ' || 
            '      ,e404.party_site_id  ' ||
            '      ,e404.party_site_number ' || 
            '      ,e404.cust_account_id ' || 
            '      ,e404.account_number  ' ||
            '      ,e404.cust_acct_site_id ' ||
             '     ,e404.s_location_id '||
             '     ,e404.s_street ' ||
             '     ,e404.s_city ' ||
             '     ,e404.s_state ' ||
             '     ,e404.s_country  ' ||
             '     ,e404.s_postal_code  ' ||
              '    ,e404.b_location_id ' ||
              '    ,e404.b_street ' ||
             '     ,e404.b_city ' ||
             '     ,e404.b_state ' ||
            '      ,e404.b_country ' ||
             '     ,e404.b_postal_code' || 
                  --,e404.b_party_name ' ||           
             '     ,e404.writing_territory' ||
             '     ,e404.writing_resource_id' ||
             '     ,e404.writing_rep_name ' ||
             '     ,e404.writing_employee_number' || 
             '     ,e404.phone ' ||
             '     ,e404.fax ' ||
             '     ,e404.csmp_level ' ||
             '     ,e404.duns_number ' ||
             '     ,e404.sic_code ' ||
             '     ,e404.ult_duns_number ' || 
             '     ,e404.line_of_business ' ||
             '     ,e404.employees_total ' ||
             '     ,e404.annual_us_sales ' ||
             '     ,e404.customer_status ' ||
--             '     ,e404.load_status ' ||
             '     ,e404.status_flag ' ||             
             '     ,e404.oracle_load_date ' ||
             '     ,e404.sf_account_id ' ||
--             '     ,e404.run_status ' ||
             '     ,e404.sf_update_date ' ||
             '     ,e404.cbs_customer_profile_name ' ||
             '     ,e404.reporting_name  ' ||
             '     ,e404.fm_dept_name ' || 
             '     ,e404.dba_name ' ||
             '     ,e404.dnb_name ' ||
             '     ,e404.company_sic ' ||
             '     ,e404.company_sic_desc ' ||
             '     ,e404.gln ' ||
             '     ,e404.hq_duns# ' ||
             '     ,e404.parent_duns# ' ||
              '    ,e404.acct_legal_name ' ||
              '    ,e404.sf_prospect_number ' ||
              '    ,dac.ds_acct_nm pname ' ||  -- acct name
            -- '     ,dat.ds_acct_tp_nm cc ' || -- category code commented for performance reasons
			  '     ,''CUSTOMER'' cc ' || -- category code
             '     ,dac.cmpy_pk pid ' ||--party_id
--             '     ,dac.ds_acct_num pnum ' ||--party_number
             '     ,dac.sell_to_cust_cd pnum ' ||--party_number -- DB Change
              '    ,plw.pty_loc_pk psid ' ||--party_site_id
             '     ,plw.loc_num psnum ' ||--party_site_number
              '    ,dac.sell_to_cust_pk caid ' ||--cust_acct_id  --DB Change
              '    ,dac.sell_to_cust_cd anum ' || --account_number --DB Change
          --    '    ,dac.sell_to_cust_cd anum ' || --account_number --DB Change
             '     ,plw.pty_loc_pk casid   ' ||--cust_acct_site_id
                 -- ,dou.org_nm wt  ' ||--writing_territory
             '     ,cert.territory_name wt' ||
             '     ,plw.duns_num dn ' ||--duns_number
             '     ,stc.ship_to_cust_cd location_id ' ||--s_location_id  
             '     ,(plw.first_line_addr || ' || 
             '               (DECODE(plw.scd_line_addr, NULL, '''', '','')|| plw.scd_line_addr) || ' ||
             '               (DECODE(plw.third_line_addr, NULL, '''', '','')|| plw.third_line_addr) || ' ||
             '               (DECODE(plw.frth_line_addr, NULL, '''', '','')|| plw.frth_line_addr)) street ' || --s_street
             '     ,plw.cty_addr city  ' || --s_city
             '     ,plw.st_cd state ' || --s_state
             '     ,plw.ctry_cd country ' || --s_country
             '     ,plw.post_cd postal_code ' || --s_postal_code
             '     ,dac.tel_num  ph_num ' ||
             '     ,dac.fax_num fax_num ' ||
             '     ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  )  wt_resource_id  '||
                '  ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  )  wt_rep_name '||
                '  ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  )  wt_employee_number '||     
             '     ,it_pty.indy_tp_nm new_sic1 ' ||
             '     ,plw.ds_ult_duns_num new_ult_duns_number  ' ||-- Not available in EXTN5  --DB Changes
             '     ,NULL ' ||--new_line_of_business 
             '     ,plw.ds_loc_emp_num new_employees_total ' || -- Not available in EXTN5 -- DB Changes
             '     ,plw.ds_loc_rev_amt new_annual_us_sales ' || --  Not available in EXTN5 --DB Changes
             '     ,dac.ds_acct_actv_cust_flg custStatus ' ||
             '     ,dag.ds_acct_grp_nm custProfName ' || -- customer_profile_name
             '     ,dag.ds_acct_grp_nm reprtngName ' || --reporting_name
             '     ,dac.coa_cc_cd fmDeptName  ' || -- --fm_dept_name
             '     ,dac.dba_nm dbaNm ' ||--dba_name
             '     ,dac.ds_acct_duns_nm dunsNm ' ||-- dnb_name
             '     ,dac.ds_cust_sic_cd custSicCd ' ||--company_sic
             '     ,it_cmpy.indy_tp_nm custSicNm ' ||--company_sic_desc
             '     ,plw.gln_num  glnNum ' || -- GLN
             '     ,NULL ' || --dplw.ds_hq_duns_num hqduns --HQ_DUNS
                 -- , --parent_duns#
              '    ,plw.ds_prnt_duns_num prntDuns ' || --DB Changes
              '    ,dac.ds_acct_legal_nm legalName  ' ||--account legal name
              '    ,dxa.ds_xref_acct_cd ' ||
           ' FROM  sell_to_cust dac ' || -- DB Changes
           -- '      ,ds_acct_tp dat ' || --commented for performance reasons
            '      ,pty_loc_wrk plw ' ||
--            '      ,ds_org_unit dou ' ||
            '     ,acct_trty_resrc_asg atra ' ||
            '     ,canon_e404_sf_acct_mapping_tbl e404  ' ||
            '     ,canon_e404_rscterr_tbl cert ' ||
            '      ,ds_acct_grp_asg daga ' ||
            '      ,ds_acct_grp dag ' ||
            '      ,indy_tp it_cmpy ' ||
            '      ,indy_tp it_pty ' ||
--             '     ,ds_pty_loc_wrk dplw ' || --DB Changes
             '      ,cmpy c ' ||
              '     ,ds_xref_acct dxa '||
                '     ,ds_xref_acct_tp dxat '||
             '      ,acct_loc al '||
             '      ,ship_to_cust stc ' ||
           ' WHERE e404.party_id = c.cmpy_pk '||
           '   AND c.cmpy_pk = dac.cmpy_pk '||
--           '    AND dac.ds_acct_num IN (''1000124'',''1277399'',''1002098'') ' ||
--           '   AND e404.account_number = dac.ds_acct_num ' ||
           '   AND e404.account_number = dac.sell_to_cust_cd ' ||    -- DB Changes        
           '   AND e404.party_site_number = al.loc_num ' ||
--           '   AND dac.loc_num = plw.loc_num ' ||
         --  '   AND dac.ds_acct_tp_cd = dat.ds_acct_tp_cd  ' || --commented for performance reasons
           '   AND al.loc_num = plw.loc_num ' ||
           '   AND stc.loc_num = plw.loc_num ' ||
           '  AND al.ds_acct_num(+) = atra.ds_acct_num  ' ||
           '  AND plw.loc_num(+) = atra.loc_num ' ||
--           '   AND dac.ds_acct_num = daga.ds_acct_num(+)  ' ||
          '   AND dac.sell_to_cust_cd = daga.ds_acct_num(+)  ' ||  -- DB Changes         
           '   AND daga.ds_acct_grp_cd = dag.ds_acct_grp_cd(+)  ' ||
            '  AND dac.indy_tp_cd = it_cmpy.indy_tp_cd(+) ' ||
--           '   AND dplw.pty_loc_pk = plw.pty_loc_pk ' ||
           '   AND plw.indy_tp_cd = it_pty.indy_tp_cd(+) ' ||
            '       AND plw.loc_num = dxa.loc_num(+) '||
               '       AND dxa.ds_xref_acct_tp_cd(+) = dxat.ds_xref_acct_tp_cd '||
               '       AND dxat.ds_xref_acct_tp_nm(+) = ''SFDC'' ';
    l_sql := l_sql || 'AND (CASE';

    FOR dynamic_cols_rec IN dynamic_cols_cur('10')
    Loop
            l_sql := l_sql ||
           ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL ' || 
            ' THEN atra.' ||dynamic_cols_rec.attribute_value  || ''; 
    END LOOP ;
    l_sql := l_sql ||' ELSE '''' END ) = cert.terr_id(+) ' ;
    
    l_sql := l_sql || ' UNION ' 
                    ||'SELECT e404.party_name,  ' ||
           '       e404.category_code, ' ||
           '       e404.party_id,  ' ||
           '       e404.party_number, ' || 
           '       e404.party_site_id, ' ||
           '       e404.party_site_number, ' || 
           '       e404.cust_account_id, ' ||
           '       e404.account_number, ' ||
           '       e404.cust_acct_site_id, ' || 
           '       e404.s_location_id,' ||
           '       e404.s_street,' || 
           '       e404.s_city,' || 
           '       e404.s_state, ' ||
           '       e404.s_country, ' ||
           '       e404.s_postal_code, ' ||
           '       e404.b_location_id,' ||
           '       e404.b_street, ' ||
           '       e404.b_city, ' ||
           '       e404.b_state, ' ||
           '       e404.b_country, ' ||
           '       e404.b_postal_code,' ||            
           '       e404.writing_territory,' ||
           '       e404.writing_resource_id,' ||
           '       e404.writing_rep_name, ' ||
           '       e404.writing_employee_number, ' ||
           '       e404.phone, ' ||
           '       e404.fax, ' ||
           '       e404.csmp_level,' ||
           '       e404.duns_number, ' ||
           '       e404.sic_code, ' ||
            '      e404.ult_duns_number, ' ||
            '      e404.line_of_business,' || 
            '      e404.employees_total,' || 
            '      e404.annual_us_sales,' ||
            '      e404.customer_status, ' ||
--            '      e404.load_status,' ||
            '      e404.status_flag,' ||            
            '      e404.oracle_load_date, ' ||
            '      e404.sf_account_id, ' ||
--            '      e404.run_status,' ||
            '      e404.sf_update_date,' ||
            '      e404.cbs_customer_profile_name,' ||
            '      e404.reporting_name, ' ||
            '     e404.fm_dept_name ' ||
            '      ,e404.dba_name' ||
            '      ,e404.dnb_name' ||
             '     ,e404.company_sic' ||
            '      ,e404.company_sic_desc ' ||
            '      ,e404.gln ' ||
            '      ,e404.hq_duns# ' ||
            '      ,e404.parent_duns#' ||
            '      ,e404.acct_legal_name ' ||
            '      ,e404.sf_prospect_number, ' ||
            '      dac.ds_acct_nm pname, ' ||-- acct name
        --    '      dat.ds_acct_tp_nm cc, ' ||-- category code --commented for performance reasons
			'      ''PROSPECT'' cc, ' ||-- category code
             '     dac.cmpy_pk pid, ' ||--party_id
             '     dac.ds_acct_num pnum, ' || --party_number          
            '      plw.pty_loc_pk psid, ' || --party_site_id
            '      plw.loc_num psnum, ' || --party_site_number
            '      dac.ds_acct_pros_pk caid, ' || --cust_acct_id
            '      dac.ds_acct_num anum, ' || --account_number
            '      plw.pty_loc_pk casid,  ' || --cust_acct_site_id
--                  dou.org_nm wt ' ||, --writing_territory
            '      cert.territory_name wt, ' ||
             '     plw.duns_num dn, ' || --duns_number
             '     NULL location_id ' || --s_location_id ---- Prospects are not tied to ship_to 
             '     ,(plw.first_line_addr||plw.scd_line_addr||plw.third_line_addr||plw.frth_line_addr) street ' || --s_street
              '   ,plw.cty_addr city  ' || --s_city
              '   ,plw.st_cd state ' || --s_state
              '   ,plw.ctry_cd country  ' ||--s_country
             '    ,plw.post_cd postal_code  ' || --s_postal_code
              '    ,dac.tel_num ph_num ' || 
             '     ,dac.fax_num fax_num ' ||
             '   ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  )  wt_resource_id  '||
             '    ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  )  wt_rep_name '||
             '    ,(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  )  wt_employee ' ||
            '      ,it_pty.indy_tp_nm new_sic1 ' ||
            '      ,plw.ds_ult_duns_num new_ult_duns_number ' || -- Not available in EXTN5 --DB Changes
            '      ,NULL ' || --new_line_of_business ????
            '      ,plw.ds_loc_emp_num new_employees_total ' || -- Not available in EXTN5 --DB CHanges
            '      ,plw.ds_loc_rev_amt new_annual_us_sales ' || --  Not available in EXTN5 --DB Changes
             '     ,dac.ds_acct_actv_cust_flg custStatus ' ||
            '      ,dag.ds_acct_grp_nm custProfName ' ||-- customer_profile_name
            '      ,dag.ds_acct_grp_nm reprtngName ' || --reporting_name
             --    ,dac.coa_cc_cd fmDeptName ' ||--fm_dept_name
             '     ,dac.coa_cc_cd ' || --fm_dept_name
             '     ,dac.dba_nm dbaNm ' ||--dba_name
             '    ,dac.ds_acct_duns_nm dunsNm ' ||-- dnb_name
             '    ,dac.ds_cust_sic_cd  custSicCd ' ||--company_sic
             '    ,it_cmpy.indy_tp_nm  custSicNm ' ||--company_sic_desc
             '    ,plw.gln_num  glnNum ' || -- GLN
             '    ,NULL ' || --dplw.ds_hq_duns_num hqduns --HQ_DUNS
              '   ,plw.ds_prnt_duns_num prntDuns  ' ||-- --parent_duns# --DB Changes
             '    ,dac.ds_acct_legal_nm legalName ' || --account legal name
             '    ,dxa.ds_xref_acct_cd ' ||
          '  FROM  ds_acct_pros dac ' ||  --Prospects
        --      '    ,ds_acct_tp dat ' || --Commented for performance reasons
              '    ,pros_pty_loc_wrk plw ' || --DB Changes
--              '    ,ds_org_unit dou ' ||
--              '    ,ds_pty_loc_wrk dplw ' || -- DB Changes
              '   ,canon_e404_sf_acct_mapping_tbl e404 ' ||
              '    ,ds_acct_grp_asg daga ' ||
              '    ,ds_acct_grp dag ' ||
               '   ,indy_tp it_cmpy ' ||
              '   ,indy_tp it_pty ' ||
              '   ,pros_trty_resrc_asg atra ' ||
              '   ,canon_e404_rscterr_tbl cert ' ||
               '     ,ds_xref_acct dxa '||
                '     ,ds_xref_acct_tp dxat '||
           --   '   ,acct_loc al ' ||  DB Changes
           --   '   ,ship_to_cust stc' || -- Prospects are not tied to ship_to
          '  WHERE e404.party_number = dac.ds_acct_num ' ||  --DB Change
--          '    AND dac.ds_acct_num IN (''1000124'',''1277399'',''1002098'') ' ||
           '   AND e404.party_site_number = plw.loc_num  ' ||
        --   '   AND dac.ds_acct_num = al.ds_acct_num ' ||     --DB Changes     
        --   '   AND plw.loc_num = al.loc_num ' ||  --DB Changes
         --  '   AND stc.loc_num = plw.loc_num ' || -- Prospects are not tied to ship_to
         --  '   AND dac.ds_acct_tp_cd = dat.ds_acct_tp_cd ' || --commented for performance reasons
           '   AND dac.loc_num = plw.loc_num ' ||
           '   AND dac.ds_acct_num = daga.ds_acct_num(+)  ' ||         
           '   AND daga.ds_acct_grp_cd = dag.ds_acct_grp_cd(+)  ' ||
           '   AND dac.indy_tp_cd = it_cmpy.indy_tp_cd(+) ' ||
--           '   AND dplw.pty_loc_pk = plw.pty_loc_pk ' ||
           '   AND plw.indy_tp_cd = it_pty.indy_tp_cd(+) ' || -- DB Changes
        --   '   AND dou.org_cd = get_org_cd(''0'',e404.party_number,e404.party_site_number)' ||
           ' AND dac.ds_acct_num(+) = atra.ds_acct_num ' ||
           '    AND plw.loc_num(+) = atra.loc_num ' ||
            '       AND plw.loc_num = dxa.loc_num(+) '||
               '       AND dxa.ds_xref_acct_tp_cd(+) = dxat.ds_xref_acct_tp_cd '||
               '       AND dxat.ds_xref_acct_tp_nm(+) = ''SFDC'' ';
    l_sql := l_sql || 'AND (CASE';

    FOR dynamic_cols_rec IN dynamic_cols_cur('0')
    Loop
            l_sql := l_sql ||
           ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL ' || 
            ' THEN atra.' ||dynamic_cols_rec.attribute_value  || ''; 
    END LOOP ;
    l_sql := l_sql ||' ELSE '''' END ) = cert.terr_id(+) ';
    
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','update dynamic l_sql: ' || l_sql,NULL,NULL,NULL,NULL);
     --dbms_output.put_line('update accounts: ' ||substr(l_sql,250));         
     
    BEGIN
        EXECUTE IMMEDIATE l_sql;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('update accounts:execute imm error:' ||sqlerrm);
     END;             
dbms_output.put_line('Inserted into CANON_E404_SF_ACCT_TEMP_TBL for update:' ||SQL%ROWCOUNT);
canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Inserted into CANON_E404_SF_ACCT_TEMP_TBL for update:' ||SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
  COMMIT;
  
  OPEN upd_accts_cur;
  LOOP
    FETCH upd_accts_cur BULK COLLECT INTO l_upd_accts_cur_tbl LIMIT 10000;

    FOR i IN 1 .. l_upd_accts_cur_tbl.COUNT 
    LOOP
      l_w_terr_res_id     := NULL;
      l_w_terr_res_name   := NULL;
      l_w_employee_number := NULL;
      
      
      l_b_street          := NULL;
      l_b_city            := NULL;
      l_b_state           := NULL;
      l_b_country         := NULL;
      l_b_postal_code     := NULL;
      l_csmp_level        := NULL;
      l_duns_number       := NULL;
      l_sic_code          := NULL;
      l_ult_duns_number   := NULL;
      l_line_of_business  := NULL;
      l_employees_total   := NULL;
      l_annual_us_sales   := NULL;
      l_customer_status   := NULL;

      l_error_flag        := 'N';
      l_cbs_customer_profile_name := NULL ; 
      l_reporting_name := null; 
      l_fm_dept_name   := null; 

      l_update_flag       := 0;

      IF nvl(l_upd_accts_cur_tbl(i).party_name,'-1') <> nvl(l_upd_accts_cur_tbl(i).pname,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).category_code,'-1') <> nvl(l_upd_accts_cur_tbl(i).cc,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).party_number,'-1') <> nvl(l_upd_accts_cur_tbl(i).pnum,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).party_site_number,'-1') <> nvl(l_upd_accts_cur_tbl(i).psnum,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).account_number,'-1') <> nvl(l_upd_accts_cur_tbl(i).anum,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).writing_territory,'-1') <> nvl(l_upd_accts_cur_tbl(i).wt,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).s_street,'-1') <> nvl(l_upd_accts_cur_tbl(i).street,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).s_city,'-1') <> nvl(l_upd_accts_cur_tbl(i).city,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).s_state,'-1') <> nvl(l_upd_accts_cur_tbl(i).state,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).s_country,'-1') <> nvl(l_upd_accts_cur_tbl(i).country,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).s_postal_code,'-1') <> nvl(l_upd_accts_cur_tbl(i).postal_code,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      -- get billing company and billing site
      get_bill_to_location(l_upd_accts_cur_tbl(i).psnum, l_upd_accts_cur_tbl(i).cc, l_b_street,l_b_city,l_b_state,l_b_country,l_b_postal_code);
      /*IF nvl(l_upd_accts_cur_tbl(i).b_party_name,'-1') <> nvl(l_b_party_name,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;*/

      -- no b_location_id -- confirm
      /*IF nvl(l_upd_accts_cur_tbl(i).b_location_id,-9999) <> nvl(l_b_location_id,-9999) THEN
        l_update_flag := l_update_flag + 1;
      END IF;*/

      IF nvl(l_upd_accts_cur_tbl(i).b_street,'-1') <> nvl(l_b_street,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).b_city,'-1') <> nvl(l_b_city,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).b_state,'-1') <> nvl(l_b_state,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).b_country,'-1') <> nvl(l_b_country,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).b_postal_code,'-1') <> nvl(l_b_postal_code,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      -- get writing terr resource
      --do we still need to check for BPC ?? confirm
      IF l_upd_accts_cur_tbl(i).wt IS NOT NULL AND 
         l_upd_accts_cur_tbl(i).wt NOT LIKE 'CSA%' AND
         l_upd_accts_cur_tbl(i).wt NOT LIKE '%BPC%'THEN 
        --get_terr_resource(l_upd_accts_cur_tbl(i).wt,l_upd_accts_cur_tbl(i).pid,l_upd_accts_cur_tbl(i).psid,l_w_terr_res_id,l_w_terr_res_name,l_w_employee_number);
        l_w_terr_res_id := l_upd_accts_cur_tbl(i).wt_resource_id;
        l_w_terr_res_name := l_upd_accts_cur_tbl(i).wt_rep_name;
        l_w_employee_number := l_upd_accts_cur_tbl(i).wt_employee_number;
      ELSIF (l_upd_accts_cur_tbl(i).wt IS NOT NULL AND (l_upd_accts_cur_tbl(i).wt LIKE 'CBS%' OR l_upd_accts_cur_tbl(i).wt LIKE '%BPC%')) THEN --ITG#447028
        l_w_terr_res_id := NULL;
        l_w_terr_res_name := 'CSA INTERFACE';
        l_w_employee_number := 'CBSINTERFACE';        
      ELSE
        l_w_terr_res_id := NULL;
        l_w_terr_res_name := 'CSA INTERFACE';
        l_w_employee_number := 'CBSINTERFACE';        
      END IF;

      IF NVL(l_upd_accts_cur_tbl(i).writing_resource_id,-9999) <> NVL(l_w_terr_res_id,-9999) THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF NVL(l_upd_accts_cur_tbl(i).writing_rep_name,'-1') <> NVL(l_w_terr_res_name,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF NVL(l_upd_accts_cur_tbl(i).writing_employee_number,'-1') <> NVL(l_w_employee_number,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF l_w_employee_number IS NULL THEN
        l_error_flag := 'Y';
        --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,'EXTRACT_ACCOUNTS:UPDATE_ACCOUNTS','Party ID:'||l_upd_accts_cur_tbl(i).pid,'Party Site ID:'||l_upd_accts_cur_tbl(i).psid,'Writing Territory:'||l_upd_accts_cur_tbl(i).wt,NULL,NULL,'Owner (Writing Rep) is missing');
      END IF;      
      
      IF nvl(l_upd_accts_cur_tbl(i).phone,'-1') <> nvl(l_upd_accts_cur_tbl(i).ph_num,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).fax,'-1') <> nvl(l_upd_accts_cur_tbl(i).fax_num,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      -- get CSMP data
      get_csmp_level(l_upd_accts_cur_tbl(i).anum,l_csmp_level);  --updated on 8/18
      IF nvl(l_upd_accts_cur_tbl(i).csmp_level,'-1') <> nvl(l_csmp_level,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      -- get dnb_data
      IF nvl(l_upd_accts_cur_tbl(i).duns_number,'-1') <> nvl(to_char(l_upd_accts_cur_tbl(i).dn),'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).sic_code,'-1') <> nvl(l_upd_accts_cur_tbl(i).new_sic1,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).ult_duns_number,'-1') <> nvl(l_upd_accts_cur_tbl(i).new_ult_duns_number,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      /* -- confirm mapping for line_of_business
      IF nvl(l_upd_accts_cur_tbl(i).line_of_business,'-1') <> nvl(l_line_of_business,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF; **/

      IF nvl(l_upd_accts_cur_tbl(i).employees_total,'-1') <> nvl(l_upd_accts_cur_tbl(i).new_employees_total,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

      IF nvl(l_upd_accts_cur_tbl(i).annual_us_sales,'-1') <> nvl(l_upd_accts_cur_tbl(i).new_annual_us_sales,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;

     
      IF nvl(l_upd_accts_cur_tbl(i).customer_status,'-1') <> nvl(l_upd_accts_cur_tbl(i).custStatus,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      --customer_profile_name
      IF (INSTR(NVL(l_upd_accts_cur_tbl(i).cbs_customer_profile_name,'-1'),nvl(l_upd_accts_cur_tbl(i).custProfName,'-1')) = 0) THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
     /* IF nvl(l_upd_accts_cur_tbl(i).cbs_customer_profile_name,'-1') <> nvl(l_upd_accts_cur_tbl(i).custProfName,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;*/
      
      --reporting_name
      IF (INSTR(NVL(l_upd_accts_cur_tbl(i).reporting_name,'-1'),nvl(l_upd_accts_cur_tbl(i).reprtngName,'-1')) = 0) THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
       /* IF nvl(l_upd_accts_cur_tbl(i).reporting_name,'-1') <> nvl(l_upd_accts_cur_tbl(i).reprtngName,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;*/
      
      get_fm_dept_name(l_upd_accts_cur_tbl(i).anum,l_fm_dept_name);
      IF nvl(l_upd_accts_cur_tbl(i).fm_dept_name,'-1') <> nvl(l_fm_dept_name,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      --DBA_NAME
      IF nvl(l_upd_accts_cur_tbl(i).dba_name,'-1') <> nvl(l_upd_accts_cur_tbl(i).dbaNm,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF nvl(l_upd_accts_cur_tbl(i).dnb_name,'-1') <> nvl(l_upd_accts_cur_tbl(i).dnbNm,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF nvl(l_upd_accts_cur_tbl(i).company_sic,'-1') <> nvl(l_upd_accts_cur_tbl(i).custSicCd,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF nvl(l_upd_accts_cur_tbl(i).company_sic_desc,'-1') <> nvl(l_upd_accts_cur_tbl(i).custSicNm,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF nvl(l_upd_accts_cur_tbl(i).gln,'-1') <> nvl(l_upd_accts_cur_tbl(i).glnNum,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF nvl(l_upd_accts_cur_tbl(i).hq_duns#,'-1') <> nvl(l_upd_accts_cur_tbl(i).hqduns,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF nvl(l_upd_accts_cur_tbl(i).parent_duns#,'-1') <> nvl(l_upd_accts_cur_tbl(i).prntDuns,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      
      IF nvl(l_upd_accts_cur_tbl(i).acct_legal_name,'-1') <> nvl(l_upd_accts_cur_tbl(i).legalName,'-1') THEN
        l_update_flag := l_update_flag + 1;
      END IF;
      

      IF l_update_flag > 0 THEN
      BEGIN
        
        UPDATE canon_e404_sf_acct_mapping_tbl e404
           SET party_name = l_upd_accts_cur_tbl(i).pname,
               category_code = l_upd_accts_cur_tbl(i).cc,
               party_id = l_upd_accts_cur_tbl(i).pid,
               party_number = l_upd_accts_cur_tbl(i).pnum,
               party_site_id = l_upd_accts_cur_tbl(i).psid,
               party_site_number = l_upd_accts_cur_tbl(i).psnum,
               cust_account_id = l_upd_accts_cur_tbl(i).caid,
               account_number = l_upd_accts_cur_tbl(i).anum,
               cust_acct_site_id = l_upd_accts_cur_tbl(i).casid,
               s_street = l_upd_accts_cur_tbl(i).street,
               s_city = l_upd_accts_cur_tbl(i).city,
               s_state = l_upd_accts_cur_tbl(i).state,
               s_country = l_upd_accts_cur_tbl(i).country,
               s_postal_code = l_upd_accts_cur_tbl(i).postal_code,
               b_street = l_b_street,
               b_city = l_b_city,
               b_state = l_b_state,
               b_country = l_b_country,
               b_postal_code = l_b_postal_code,
               writing_territory = l_upd_accts_cur_tbl(i).wt,
               writing_resource_id = l_w_terr_res_id,
               writing_rep_name = l_w_terr_res_name,
               writing_employee_number = l_w_employee_number,
               phone = l_upd_accts_cur_tbl(i).ph_num,
               fax = l_upd_accts_cur_tbl(i).fax_num,
               csmp_level = l_csmp_level,
               duns_number = l_upd_accts_cur_tbl(i).dn,
               sic_code = l_upd_accts_cur_tbl(i).new_sic1,
               ult_duns_number = l_upd_accts_cur_tbl(i).new_ult_duns_number,
               --line_of_business = l_line_of_business,
               employees_total = l_upd_accts_cur_tbl(i).new_employees_total,
               annual_us_sales = l_upd_accts_cur_tbl(i).new_annual_us_sales,
               customer_status = l_upd_accts_cur_tbl(i).custStatus,
--               load_status = DECODE(l_error_flag,'Y','P','U'),
               status_flag = DECODE(l_error_flag,'Y','P','U'),               
               oracle_load_date = SYSDATE,
--               run_status = DECODE(l_error_flag,'Y','E',NULL),
               sf_update_date = DECODE(l_error_flag,'Y',SYSDATE,NULL),
               comments = DECODE(l_error_flag,'Y','Owner (Writing Rep) is missing',NULL),
               cbs_customer_profile_name = DECODE(cbs_customer_profile_name, NULL, l_upd_accts_cur_tbl(i).custProfName, substr(cbs_customer_profile_name || ',' || l_upd_accts_cur_tbl(i).custProfName,3999))
               ,reporting_name = DECODE(reporting_name, l_upd_accts_cur_tbl(i).reprtngName, NULL, substr(reporting_name || ',' || l_upd_accts_cur_tbl(i).reprtngName,3999))
               ,fm_dept_name = l_fm_dept_name
               ,dba_name = l_upd_accts_cur_tbl(i).dbaNm --new S21
               ,dnb_name = l_upd_accts_cur_tbl(i).dnbNm --new S21
               ,company_sic = l_upd_accts_cur_tbl(i).custSicCd --new S21
               ,company_sic_desc = l_upd_accts_cur_tbl(i).custSicNm --new S21
               , gln = l_upd_accts_cur_tbl(i).glnNum --new S21
               ,hq_duns# = l_upd_accts_cur_tbl(i).hqDuns --new S21
               ,parent_duns# = l_upd_accts_cur_tbl(i).prntDuns --new S21
               ,acct_legal_name = l_upd_accts_cur_tbl(i).legalName --new S21
               ,s_location_id = l_upd_accts_cur_tbl(i).location_id
               ,b_location_id = l_upd_accts_cur_tbl(i).location_id
         WHERE e404.sf_account_id = l_upd_accts_cur_tbl(i).sf_account_id 
          ;
          EXCEPTION
            WHEN OTHERS THEN
            canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,'UPDATE_ACCOUNTS',l_upd_accts_cur_tbl(i).psnum,NULL,NULL,NULL,NULL,'Update ERROR:'||SQLERRM);
                dbms_output.put_line('UPDATE_ACCOUTS : Error: ' ||sqlerrm);
          END;

      END IF;
       
    END LOOP;

    COMMIT;

    EXIT WHEN l_upd_accts_cur_tbl.COUNT < 10000;

  END LOOP;

  CLOSE upd_accts_cur;
/********** Lease Expiration depends on E404 IB, uncomment after IB tables are created***********************  
   fnd_file.put_line(fnd_file.log,'Closest Lease Expiration Date - START - '||TO_CHAR('MM/DD/YYYY HH:MI:SS AM'));  
   
    MERGE
     INTO canon_e404_sf_acct_mapping_tbl e404_acct
    USING(SELECT e404_acct.party_site_id
                ,e404_ib.lease_expiration_date
            FROM canon_e404_sf_acct_mapping_tbl e404_acct
                ,(SELECT cbs_party_site_number
                        ,MIN(lease_expiration_date) lease_expiration_date
                    FROM canon_e404_install_base_ob_tbl
                   WHERE lease_expiration_date IS NOT NULL
                   GROUP BY cbs_party_site_number
                 ) e404_ib
           WHERE e404_acct.party_site_number = e404_ib.cbs_party_site_number
             AND e404_acct.writing_employee_number IS NOT NULL
             AND e404_acct.load_status != 'I'
             AND NVL(e404_acct.lease_expiration_date, TRUNC(SYSDATE-1000)) <> e404_ib.lease_expiration_date
          ) e404_ib
       ON(e404_acct.party_site_id = e404_ib.party_site_id)
     WHEN MATCHED THEN
   UPDATE
      SET lease_expiration_date = e404_ib.lease_expiration_date   
         ,load_status = 'U'
         ,oracle_load_date = SYSDATE
         ,run_status = NULL
         ,sf_update_date = NULL;   

   fnd_file.put_line(fnd_file.log,'Closest Lease Expiration Date - No of recs updated - '||SQL%ROWCOUNT);

   COMMIT;
   
   fnd_file.put_line(fnd_file.log,'Closest Lease Expiration Date - END - '||TO_CHAR('MM/DD/YYYY HH:MI:SS AM'));
*****************************************************************************************************************************/
      
  SELECT COUNT(*)
    INTO l_acct_update_count
    FROM canon_e404_sf_acct_mapping_tbl e404
--   WHERE e404.load_status = 'U'
   WHERE e404.status_flag = 'U'
--     AND run_status IS NULL
     AND sf_account_id IS NOT NULL;

  x_return_status := 'S';

  dbms_output.put_line('Records Extracted FOR UPDATE: '|| l_acct_update_count);
  dbms_output.put_line(' ');
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Records Extracted FOR UPDATE: '|| l_acct_update_count,NULL,NULL,NULL,NULL);

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    x_return_status := 'E';
    x_msg_data      := 'Unexpected ERROR:'|| SUBSTR(SQLERRM,2000);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);

END update_accounts;

PROCEDURE get_bill_to_location(v_psn     IN   VARCHAR2
							   ,v_acct_tp_cd	IN VARCHAR2
                              ,x_b_street             OUT   VARCHAR2
                              ,x_b_city               OUT   VARCHAR2
                              ,x_b_state              OUT   VARCHAR2
                              ,x_b_country            OUT   VARCHAR2
                              ,x_b_postal_code        OUT   VARCHAR2
                              )
AS
 l_acct_tp VARCHAR2(5) := null;
 l_procedure_name VARCHAR2(25) := 'GET_BILL_TO_LOCATION';
BEGIN

--commented for performance reasons
/*SELECT ds_acct_tp_cd
   INTO l_acct_tp
  FROM (
        SELECT ds_acct_tp_cd
          FROM sell_to_cust stc
               ,acct_loc al
          WHERE al.loc_num = v_psn
            AND al.ds_acct_num = stc.sell_to_cust_cd
          UNION
            SELECT ds_acct_tp_cd
              FROM ds_acct_pros
              WHERE loc_num = v_psn
        );*/ 
       
       IF(v_acct_tp_cd = 'PROSPECT') THEN -- Prospect 
          SELECT first_line_addr || scd_line_addr || third_line_addr || frth_line_addr street
                ,cty_addr
                ,st_cd
                ,post_cd
                ,ctry_cd
            INTO x_b_street
                 ,x_b_city
                 ,x_b_state
                 ,x_b_postal_code
                 ,x_b_country
            FROM pros_pty_loc_wrk
            WHERE loc_num = v_psn;
       END IF;
       
       IF(v_acct_tp_cd = 'CUSTOMER') THEN -- Customer
          select (NVL(btc.first_line_addr,stc.first_line_addr) || NVL(btc.scd_line_addr,stc.scd_line_addr) || NVL(btc.third_line_addr,stc.third_line_addr) || NVL(btc.frth_line_addr, stc.frth_line_addr)) b_street
                 ,NVL(btc.cty_addr,stc.cty_addr)
                 ,NVL(btc.st_cd,stc.st_cd)
                 ,NVL(btc.post_cd,stc.post_cd)
                 ,NVL(btc.ctry_cd,stc.ctry_cd) 
            into x_b_street
                 ,x_b_city
                 ,x_b_state
                 ,x_b_postal_code
                 ,x_b_country
            from ship_to_cust stc
                 ,bill_to_cust btc
            where stc.glbl_cmpy_cd = 'ADB'
              and stc.ezcancelflag = '0'
              and btc.glbl_cmpy_cd(+) = 'ADB'
              and btc.ezcancelflag(+) = '0'
              and TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(stc.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(stc.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
              and stc.reln_bill_to_cust_cd = btc.bill_to_cust_pk(+)
              and stc.loc_num = v_psn;
       END IF;
EXCEPTION
WHEN TOO_MANY_ROWS THEN
      BEGIN
        select (NVL(btc.first_line_addr,stc.first_line_addr) || NVL(btc.scd_line_addr,stc.scd_line_addr) || NVL(btc.third_line_addr,stc.third_line_addr) || NVL(btc.frth_line_addr, stc.frth_line_addr)) b_street
                 ,NVL(btc.cty_addr,stc.cty_addr)
                 ,NVL(btc.st_cd,stc.st_cd)
                 ,NVL(btc.post_cd,stc.post_cd)
                 ,NVL(btc.ctry_cd,stc.ctry_cd) 
            into x_b_street
                 ,x_b_city
                 ,x_b_state
                 ,x_b_postal_code
                 ,x_b_country
            from ship_to_cust stc
                 ,bill_to_cust btc
            where stc.glbl_cmpy_cd = 'ADB'
              and stc.ezcancelflag = '0'
              and btc.glbl_cmpy_cd(+) = 'ADB'
              and btc.ezcancelflag(+) = '0'
              and TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(stc.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(stc.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
              and stc.reln_bill_to_cust_cd = btc.bill_to_cust_pk(+)
              and stc.loc_num = v_psn;
        EXCEPTION WHEN OTHERS THEN
          x_b_street              := NULL;
          x_b_city                := NULL;
          x_b_state               := NULL;
          x_b_country             := NULL;
          x_b_postal_code         := NULL;
      END;
   WHEN NO_DATA_FOUND THEN
      
      x_b_street              := NULL;
      x_b_city                := NULL;
      x_b_state               := NULL;
      x_b_country             := NULL;
      x_b_postal_code         := NULL;
	  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'No Data Found ERROR:'||SQLERRM);
   WHEN OTHERS THEN
      
      x_b_street              := NULL;
      x_b_city                := NULL;
      x_b_state               := NULL;
      x_b_country             := NULL;
      x_b_postal_code         := NULL;
	 canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END get_bill_to_location;


PROCEDURE get_load_data(x_cursor OUT sys_refcursor)
AS
    P_PROCEDURE_NAME VARCHAR2(100) := 'GET_LOAD_DATA';
BEGIN
    OPEN x_cursor FOR SELECT PARTY_NAME
                            ,CATEGORY_CODE
                            ,PARTY_NUMBER
                            ,PARTY_SITE_NUMBER
                            ,ACCOUNT_NUMBER
                            --,B_PARTY_NAME
                            ,WRITING_TERRITORY
                            ,WRITING_EMPLOYEE_NUMBER
                            --,INSTALLING_TERRITORY
                            ,PHONE
                            ,FAX
                            ,CSMP_LEVEL
                            ,DUNS_NUMBER
                            ,SIC_CODE
                            ,ULT_DUNS_NUMBER
                            ,LINE_OF_BUSINESS
                            ,EMPLOYEES_TOTAL
                            ,ANNUAL_US_SALES
                            ,CUSTOMER_STATUS
                            ,SF_ACCOUNT_ID
                            ,CBS_CUSTOMER_PROFILE_NAME
                            ,FM_DEPT_NAME
                            ,LEASE_EXPIRATION_DATE
                         FROM  canon_e404_sf_acct_mapping_tbl
--                       WHERE load_status in ('I','U')
                       WHERE status_flag in ('I','U')
                         AND oracle_load_date >= NVL(sf_update_date,oracle_load_date);
EXCEPTION
WHEN OTHERS THEN
    --x_msg_data      := 'Unexpected ERROR:'|| SUBSTR(SQLERRM,2000);
	canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,P_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SUBSTR(SQLERRM,2000));
END get_load_data;    

               
PROCEDURE get_merge_data(x_cursor OUT sys_refcursor)
AS
    P_PROCEDURE_NAME VARCHAR2(100) := 'GET_MERGE_DATA';
BEGIN
    OPEN x_cursor FOR SELECT darp.pros_rvw_id merge_pid
                             ,e404.sf_account_id master_pid
                        FROM ds_acct_rvw_pros darp
                             ,canon_e404_sf_acct_mapping_tbl e404
                       WHERE darp.pros_rvw_sts_cd = 'D'
                         AND darp.pros_rvw_id IS NOT NULL
                         AND NVL(darp.xtrnl_pros_rvw_sts_cd,'E') = 'E'
                         AND darp.acct_src_txt = 'SFDC'
                         AND darp.aft_loc_num = e404.party_site_number
                         AND e404.sf_account_id IS NOT NULL
                      UNION
                         SELECT darp.pros_rvw_id merge_pid
                                ,darp2.pros_rvw_id master_pid
                           FROM ds_acct_rvw_pros darp
                                ,ds_acct_rvw_pros darp2
                         WHERE darp.pros_rvw_sts_cd = 'D'
                           AND NVL(darp.xtrnl_pros_rvw_sts_cd,'E') = 'E'
                           AND darp.pros_rvw_id IS NOT NULL
                           AND darp2.pros_rvw_id IS NOT NULL
                           AND darp.aft_loc_num IS NULL
                           AND darp.aft_ds_xref_acct_cd IS NOT NULL
                           AND darp2.rvw_pros_num = darp.aft_ds_xref_acct_cd
                           AND darp.acct_src_txt = 'SFDC'
                           AND darp2.acct_src_txt = 'SFDC'
                           ;
EXCEPTION
WHEN OTHERS THEN
    --x_msg_data      := 'Unexpected ERROR:'|| SUBSTR(SQLERRM,2000);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,P_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END get_merge_data;                   


PROCEDURE get_fm_dept_name(v_casid IN VARCHAR2
						   ,x_fm_dept_name OUT VARCHAR2
						   )
AS
  l_procedure_name VARCHAR2(60) := 'GET_FM_DEPT_NAME';
BEGIN

select salesrep_number
  INTO x_fm_dept_name
  FROM CANON_E473_BSD_ACCT_REP_VS_V
  where account_number = v_casid;
EXCEPTION
        WHEN NO_DATA_FOUND THEN
            x_fm_dept_name := NULL;
        WHEN OTHERS THEN
            x_fm_dept_name := NULL;
            canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END get_fm_dept_name;

/*
PROCEDURE get_accounts(x_cursor OUT sys_refcursor)
    AS
        l_procedure_name VARCHAR2(60) := 'GET_ACCOUNTS';
BEGIN
    OPEN x_cursor FOR SELECT party_name
                             ,category_code
                             ,party_number
                             ,party_site_number
                             ,account_number
                             ,s_street
                             ,s_city
                             ,s_state
                             ,s_country
                             ,s_postal_code
                             ,b_street
                             ,b_city
                             ,b_state
                             ,b_country
                             ,b_postal_code
                             ,b_party_name
                             ,writing_territory
                             ,writing_employee_number
                             ,installing_territory
                             ,installing_rep_name
                             ,phone
                             ,fax
                             ,csmp_level
                             ,duns_number
                             ,sic_code
                             ,ult_duns_number
                             ,line_of_business
                             ,employees_total
                             ,annual_us_sales
                             ,cbs_customer_profile_name
                             ,fm_dept_name
                             ,sf_account_id
                             ,lease_expiration_date
                             ,customer_status
                        FROM canon_e404_sf_acct_mapping_tbl
                       WHERE load_status in ('I','U')
                         AND oracle_load_date >= NVL(sf_update_date,oracle_load_date);
EXCEPTION
    WHEN OTHERS THEN
      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,'EXTRACT_ACCOUNTS : GET_ACCOUNTS',NULL,NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END get_accounts;

PROCEDURE update_req_results(p_result_tbl IN canon.canon_e404_sf_ib_req_rslt_tbl)
IS 
    l_sql    VARCHAR2(3000);
BEGIN
    EXECUTE IMMEDIATE
        'INSERT INTO CANON_E404_IB_REQ_RSLT_TBL
        SELECT a.status
               ,a.sfdcID
               ,a.message
               ,a.instanceId
               ,SYSDATE
               ,NULL
          FROM TABLE(CAST(:1 AS CANON_E404_SF_IB_REQ_RSLT_TBL)) a'
    USING p_result_tbl;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,'EXTRACT_ACCOUNTS : UPDATE_REQ_RESULTS',NULL,NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END update_req_results;    

PROCEDURE update_accounts
IS
    l_sql     VARCHAR2(3000);
BEGIN
    MERGE INTO canon_e404_sf_acct_mapping_tbl e404
     USING canon_e404_ib_req_rslt_tbl req
        ON (req.instance_id = e404.party_site_number AND trunc(req.created_date) = trunc(sysdate))
    WHEN MATCHED THEN UPDATE SET
        e404.salesforce_ib_id = req.sfdcid
        e404.load_status = 'P'
        e404.run_status = req.status
        e404.sf_update_date = sysdate
        e404.comments = req.message;
  COMMIT;
****************************************/

END CANON_E404_SF_ACCOUNT_PKG;
/