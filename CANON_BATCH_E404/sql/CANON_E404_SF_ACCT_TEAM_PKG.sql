create or replace PACKAGE              CANON_E404_SF_ACCT_TEAM_PKG
AS

    TYPE RESULT_CURSOR IS REF CURSOR;
    

    G_PACKAGE_NAME    VARCHAR2(30) := 'CANON_E404_SF_ACCT_TEAM_PKG';
    l_limit    number := 1000;
           
    PROCEDURE update_errored_records(x_status OUT NUMBER, x_message OUT VARCHAR2);
    PROCEDURE mark_deleted_accounts (x_status OUT  NUMBER, x_message OUT VARCHAR2);
    PROCEDURE archive_deleted_records(x_status OUT NUMBER, x_message OUT VARCHAR2);
    PROCEDURE load_accounts(x_status OUT  NUMBER, x_message OUT VARCHAR2);
    PROCEDURE process_account_team ( X_ERRBUF OUT VARCHAR2, X_RETCODE OUT VARCHAR2);           

END CANON_E404_SF_ACCT_TEAM_PKG;
/

create or replace PACKAGE BODY              CANON_E404_SF_ACCT_TEAM_PKG
AS

PROCEDURE update_errored_records(x_status OUT NUMBER, x_message OUT VARCHAR2)   
AS
    l_procedure_name VARCHAR2(25) := 'update_errored_records';
BEGIN
      x_status:= 1;
      
      UPDATE canon_e404_sf_acct_team_tbl 
         SET status_flag ='I'
      WHERE status_flag = 'IE'
        AND status_flag IS NOT NULL;
  dbms_output.put_line('updated ' || SQL%rowcount || ' for flag I');
  canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for flag I',NULL,NULL,NULL,NULL);
      
      UPDATE canon_e404_sf_acct_team_tbl 
         SET status_flag ='IP'
       WHERE status_flag = 'DE'
         AND SFDC_ID IS NOT NULL
         AND status_flag IS NOT NULL;  
    dbms_output.put_line('updated ' || SQL%rowcount || ' for flag IP');
    canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for flag IP',NULL,NULL,NULL,NULL);
      
     COMMIT;
          
EXCEPTION
   WHEN OTHERS THEN
     x_status := -1;
     x_message := substr(SQLERRM,1,255);   
     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);   
   
   END update_errored_records;
   
PROCEDURE mark_deleted_accounts(x_status OUT NUMBER, x_message OUT VARCHAR2)   
AS
    l_procedure_name VARCHAR2(25) := 'mark_deleted_accounts';
    l_mgr_psn_cd VARCHAR2(25) := null;
    l_org_tier_cd VARCHAR2(25) := null;
    l_loop_ctr NUMBER := null;
    l_toc VARCHAR2(25) := null;
    l_mark_to_delete boolean := false;
    
BEGIN    

    -- mark the records to delete if any change in acct team members -- ACCOUNT
        UPDATE canon_e404_sf_acct_team_tbl e404
           SET status_flag = 'D'
         WHERE organization_type = 'ACCOUNT'
           AND SFDC_ID IS NOT NULL
           AND status_flag in ('IP')
           AND NOT EXISTS (SELECT 1
                             FROM acct_trty_role_asg atra
--                                  ,ds_acct_cust dac
                                --  ,sell_to_cust dac -- DB Change
                                  ,pty_loc_wrk plw
                                  ,trty_grp_tp tgt
                           -- WHERE dac.sell_to_cust_cd = atra.ds_acct_num -- DB Change
                              WHERE plw.loc_num = atra.loc_num
--                              AND e404.party_id = dac.ds_acct_cust_pk
                              AND e404.party_id = plw.cmpy_pk -- DB Changes
                              AND e404.party_site_id = plw.pty_loc_pk
                              AND e404.terr_id = atra.org_cd
                              AND e404.resource_id = atra.psn_cd
                              AND e404.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.line_biz_role_tp_cd IS NOT NULL
                              AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                              AND tgt.line_biz_tp_cd = 'ESS'
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                              AND tgt.ezcancelflag = '0'
                              AND tgt.glbl_cmpy_cd = 'ADB'
                             -- AND dac.glbl_cmpy_cd = 'ADB'
                             -- AND dac.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
        dbms_output.put_line('Cutomers marked delete  ' || SQL%rowcount );
        canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Cutomers marked delete  ' || Sql%Rowcount,NULL,NULL,NULL,NULL);                              
                              
    -- mark the records to delete if any change in acct team members -- PROSPECT
        UPDATE canon_e404_sf_acct_team_tbl e404
           SET status_flag = 'D'
         WHERE organization_type = 'PROSPECT'
           AND SFDC_ID IS NOT NULL
           AND status_flag in ('IP')
           AND NOT EXISTS (SELECT 1
                             FROM pros_trty_role_asg atra
                                  ,pros_pty_loc_wrk plw
                                  ,trty_grp_tp tgt
                              WHERE plw.loc_num = atra.loc_num
                              AND e404.party_id = plw.cmpy_pk
                              AND e404.party_site_id = plw.pty_loc_pk
                              AND e404.terr_id = atra.org_cd
                              AND e404.resource_id = atra.psn_cd
                              AND e404.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.line_biz_role_tp_cd IS NOT NULL
                              AND tgt.trty_grp_tp_cd = atra.trty_grp_tp_cd
                              AND tgt.line_biz_tp_cd = 'ESS'
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                              AND tgt.glbl_cmpy_cd = 'ADB'
                              AND tgt.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
      dbms_output.put_line('Prospects marked delete ' || SQL%rowcount );
      canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Prospects marked delete 2 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);                            

    --mark the record to delete if the account owner got created in acct team share
    UPDATE canon_e404_sf_acct_team_tbl e404
        SET status_flag = 'D'
      WHERE SFDC_ID IS NOT NULL
        AND status_flag IN ('IP')
        AND EXISTS ( SELECT 1
                       FROM canon_e404_sf_acct_mapping_tbl b
                      WHERE b.writing_employee_number = nvl(e404.newrep_number, e404.salesrep_number)
                        AND b.party_id = e404.party_id
                        AND b.party_site_id = e404.party_site_id);   
    dbms_output.put_line('Customer Acct Share marked delete ' || SQL%rowcount );
    canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','LFS Customer Acct Share marked delete ' || Sql%Rowcount,NULL,NULL,NULL,NULL);                       
                          
    --for ACCOUNT
        UPDATE canon_e404_sf_acct_team_tbl e404
           SET status_flag = 'DP'
               ,as_status_flag = 'DP'
         WHERE organization_type = 'ACCOUNT'
           AND SFDC_ID IS NULL
           AND status_flag IN ('I','IE')
           AND NOT EXISTS (SELECT 1
                             FROM acct_trty_role_asg atra
--                                  ,ds_acct_cust dac
                                 --  ,sell_to_cust dac -- DB Changes
                                  ,pty_loc_wrk plw
                                  ,trty_grp_tp tgt
                          --  WHERE dac.sell_to_cust_cd = atra.ds_acct_num -- DB Change
                              WHERE plw.loc_num = atra.loc_num
--                              AND e404.party_id = dac.ds_acct_cust_pk
                              AND e404.party_id = plw.cmpy_pk -- DB Changes
                              AND e404.party_site_id = plw.pty_loc_pk
                              AND e404.terr_id = atra.org_cd
                              AND e404.resource_id = atra.psn_cd
                              AND e404.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.line_biz_tp_cd IS NOT NULL
                              AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                              AND tgt.line_biz_tp_cd = 'ESS'
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                              AND tgt.glbl_cmpy_cd = 'ADB'
                              AND tgt.ezcancelflag = '0'
                              --AND dac.glbl_cmpy_cd = 'ADB'
                             -- AND dac.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
      dbms_output.put_line('marked delete 4 ' || SQL%rowcount );
      canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','marked delete 4 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);                              
                              
    --for PROSPECT
        UPDATE canon_e404_sf_acct_team_tbl e404
           SET status_flag = 'DP'
               ,as_status_flag = 'DP'
         WHERE organization_type = 'PROSPECT'
           AND SFDC_ID IS NULL
           AND status_flag IN ('I','IE')
           AND NOT EXISTS (SELECT 1
                             --FROM acct_trty_role_asg atra
                             FROM pros_trty_role_asg atra
                                 -- ,ds_acct_pros dac
                                  ,pros_pty_loc_wrk plw
                                  ,trty_grp_tp tgt
                            --WHERE dac.ds_acct_num = atra.ds_acct_num
                              WHERE plw.loc_num = atra.loc_num
                              AND e404.party_id = plw.cmpy_pk
                              AND e404.party_site_id = plw.pty_loc_pk
                              AND e404.terr_id = atra.org_cd
                              AND e404.resource_id = atra.psn_cd
                              AND e404.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.line_biz_role_tp_cd IS NOT NULL
                              AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                              AND tgt.line_biz_tp_cd = 'ESS'
                              AND tgt.ezcancelflag = '0'
                              AND tgt.glbl_cmpy_cd = 'ADB'
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                             -- AND dac.glbl_cmpy_cd = 'ADB'
                             -- AND dac.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
       dbms_output.put_line('marked delete 5 ' || SQL%rowcount );
       canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','marked delete 5 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);                             
    
    COMMIT;                          
             
    --If a manager changes for ADJ or TERRITORY and old mgr is successfully created as Team Member in SFDC, mark that for DELETE
        BEGIN
            l_mgr_psn_cd := null;
            l_org_tier_cd := null;
            l_loop_ctr := null;
            l_toc := null;
            l_mark_to_delete := false;
            
            FOR accts_to_del_rec IN (SELECT e404.party_id, e404.party_site_id, salesrep_number, psn_rep.psn_cd, e404.newrep_number, psn_mgr.psn_cd newrep_psn_cd, dou.org_tier_cd
                                       FROM canon_e404_sf_acct_team_tbl e404
                                            ,ds_org_unit dou
                                            ,s21_psn psn_mgr
                                            ,s21_psn psn_rep
                                      WHERE status_flag IN ('IP','I','IE')
                                        AND (salesrep_number LIKE '%ADJ%' OR salesrep_number LIKE '%TERRITORY%')
                                        AND newrep_number IS NOT NULL
                                        AND e404.terr_id = dou.org_cd
                                        AND newrep_number = psn_mgr.psn_num
                                        AND salesrep_number = psn_rep.psn_num
                                        AND dou.glbl_cmpy_cd = 'ADB'
                                        AND dou.ezcancelflag = '0'
                                        AND psn_mgr.glbl_cmpy_cd = 'ADB'
                                        AND psn_mgr.ezcancelflag = '0'
                                        AND psn_rep.glbl_cmpy_cd = 'ADB'
                                        AND psn_rep.ezcancelflag = '0')
            LOOP
                FOR l_loop_ctr IN REVERSE 1..to_number(accts_to_del_rec.org_tier_cd)
                LOOP
                    BEGIN
                        SELECT toc_cd
                          INTO l_toc
                          FROM org_func_asg ofa
                         WHERE psn_cd = accts_to_del_rec.psn_cd
                           AND ofa.glbl_cmpy_cd ='ADB'
                           AND ofa.ezcancelflag = '0';
                    EXCEPTION
                        WHEN OTHERS THEN
                            l_toc := null;
                    END;
                    
                    BEGIN
                        SELECT (CASE accts_to_del_rec.org_tier_cd
                                  WHEN '1' THEN 'FIRST_ORG_MGR_PSN_CD'
                                  WHEN '2' THEN 'SCD_ORG_MGR_PSN_CD'
                                  WHEN '3' THEN 'THIRD_ORG_MGR_PSN_CD'
                                  WHEN '4' THEN 'FRTH_ORG_MGR_PSN_CD'
                                  WHEN '5' THEN 'FIFTH_ORG_MGR_PSN_CD'
                                  WHEN '6' THEN 'SIXTH_ORG_MGR_PSN_CD'
                                  WHEN '7' THEN 'SVTH_ORG_MGR_PSN_CD'
                                  WHEN '8' THEN 'EIGHTH_ORG_MGR_PSN_CD'
                                  WHEN '9' THEN 'NINTH_ORG_MGR_PSN_CD'
                                  WHEN '10' THEN 'TENTH_ORG_MGR_PSN_CD'
                                  WHEN '11' THEN 'ELVTH_ORG_MGR_PSN_CD'
                                  END)
                        INTO l_mgr_psn_cd  
                        FROM toc_org_mgr_info tomi
                        WHERE psn_cd = accts_to_del_rec.psn_cd
                          AND toc_cd = l_toc
                          AND tomi.glbl_cmpy_cd = 'ADB'
                          AND tomi.ezcancelflag = '0';
                       EXCEPTION
                        WHEN OTHERS THEN
                            l_mgr_psn_cd := null;
                       END;
                        
                        IF l_mgr_psn_cd IS NOT NULL AND l_mgr_psn_cd <> accts_to_del_rec.newrep_psn_cd THEN
                            l_mark_to_delete := true;
                        END iF;
                        
                        EXIT WHEN l_mark_to_delete = true;
                        
                END LOOP;
                
                IF l_mark_to_delete = true THEN
                    UPDATE canon_e404_sf_acct_team_tbl e404
                       SET status_flag = 'D'
                     WHERE party_id = accts_to_del_rec.party_id
                       AND party_site_id = accts_to_del_rec.party_site_id
--                       AND newrep_number = accts_to_del_rec.newrep_number
                       AND salesrep_number = accts_to_del_rec.salesrep_number;
                       COMMIT;
                END IF;
                
            END LOOP;
        EXCEPTION
            WHEN OTHERS THEN
                canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);  
        END;
        canon_E404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','marked delete 6 ' || SQL%rowcount,NULL,NULL,NULL,NULL);
        
         update canon_E404_sf_acct_team_tbl
          set as_status_flag = 'D'
          where status_flag = 'D'
          and sf_as_id is not null
          AND sfdc_id IS NOT NULL;
          canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Updated Acct Share flag for Delete ' || SQL%rowcount,NULL,NULL,NULL,NULL);
        COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        x_status := -1;
        x_message := substr(SQLERRM,1,255);   
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);  
END mark_deleted_accounts;

PROCEDURE archive_deleted_records(x_status OUT NUMBER, x_message OUT VARCHAR2)
   AS
     l_procedure_name VARCHAR2(25) := 'archive_records';
   
   BEGIN
   
       x_status := 1;
      
       INSERT INTO canon_e404_sf_acct_team_arc 
       SELECT * FROM canon_e404_sf_acct_team_tbl
       WHERE status_flag = 'DP' AND as_status_flag = 'DP';
  dbms_output.put_line('archiving ' || SQL%rowcount );
  canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','archiving ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
       
       DELETE FROM canon_e404_sf_acct_team_tbl
       WHERE status_flag = 'DP' AND as_status_flag = 'DP';
    dbms_output.put_line('deleting ' || SQL%rowcount || ' from the table' );
    canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','deleting ' || Sql%Rowcount || ' from the table',NULL,NULL,NULL,NULL);
       COMMIT;
       
   EXCEPTION
   WHEN OTHERS THEN
      x_status := -1;
      x_message := SUBSTR(SQLERRM,1,255);
      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);   
   END archive_deleted_records;
   
 PROCEDURE load_accounts( x_status OUT  NUMBER, x_message OUT VARCHAR2)
 AS
    l_procedure_name  VARCHAR2(30)    := 'load_accounts';  
    l_counter NUMBER := 0;
    l_status VARCHAR2(10) :='N';
    l_mgr VARCHAR2(25) := NULL;
 
 BEGIN    
          x_status := 1;       
          x_message:= '';
                     
          --Load delta records
    INSERT INTO canon_e404_sf_acct_team_tbl(
                     PARTY_ID         
                    ,PARTY_SITE_ID    
                    ,TERRITORY        
                    ,TERR_ID          
                    ,RESOURCE_ID      
                    ,SALESREP_NUMBER  
                    ,SALESREP_NAME    
                    ,ATTRIBUTE_ID     
                    ,LOB              
                    ,RELATIONSHIP_FLAG
                    ,ORGANIZATION_TYPE
                    ,ROLE_NAME
                    ,SITE_SFDC_ID     
                    ,USER_SFDC_ID     
                    ,SFDC_ID          
                    ,STATUS_FLAG
                    , creation_date
                    ,last_update_date                    
                    )   SELECT actv_accts.party_id
                               ,actv_accts.party_site_id
                               ,dou.org_nm territory
                               ,atra.org_cd terr_id
                               ,atra.psn_cd resource_id
                               ,psn.psn_num salesrep_number
                               ,psn.psn_last_nm || ', ' || psn.psn_first_nm salesrep_name
                               ,atra.asg_trty_attrb_cd attribute_id
                               ,dou.trty_grp_tp_cd LOB
                               ,ATRA.LINE_BIZ_ROLE_TP_Cd relationship_flag
                               ,'ACCOUNT' organization_type
                               ,lbrt.line_biz_role_tp_nm role_name
                               ,NULL
                               ,NULL
                               ,NULL
                               ,'I'
                               ,sysdate
                               ,sysdate
                          FROM acct_trty_role_asg atra
							   ,trty_grp_tp tgt
                               ,(SELECT dac.cmpy_pk party_id
                                        ,plw.loc_num
                                        ,plw.pty_loc_pk party_site_id
--                                      ,ds_acct_cust_pk cust_acct_id -- DB Chanegs
                                        ,dac.sell_to_cust_pk cust_acct_id -- DB Changes
                                        ,dac.sell_to_cust_cd ds_acct_num
--                                   FROM ds_acct_cust dac
                                   FROM sell_to_cust dac -- db changes
                                        ,acct_loc al
                                        ,pty_loc_wrk plw
                                  WHERE NVL(dac.rgtn_sts_cd,'XX') NOT IN ('P99')
                                    AND dac.glbl_cmpy_cd = 'ADB'
                                    AND dac.ezcancelflag = '0'
                                    AND plw.ezcancelflag = '0'
                                    AND plw.glbl_cmpy_cd = 'ADB'
                                    AND al.ezcancelflag = '0'
                                    AND al.glbl_cmpy_cd = 'ADB'
                                    and dac.sell_to_cust_cd = al.ds_acct_num
                                    and al.loc_num = plw.loc_num
                                    and nvl(al.rgtn_sts_cd,'XX') not in ('P99')
                                    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                    and nvl(plw.rgtn_sts_cd,'XX') not in ('P99')
                                    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(TO_DATE(PLW.EFF_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(TO_DATE(PLW.EFF_THRU_DT, 'yyyymmdd'),SYSDATE))           
                                )actv_accts
                              --  ,pty_loc_wrk plw
--                                ,ds_pty_loc_wrk dplw -- DB Change
                                ,ds_org_unit dou
                                ,s21_psn psn
                                ,line_biz_role_tp lbrt
                         WHERE atra.glbl_cmpy_cd ='ADB'
                           AND atra.ezcancelflag = '0'
						   AND tgt.glbl_cmpy_cd = 'ADB'
						   AND tgt.ezcancelflag = '0'
                           AND dou.glbl_cmpy_cd = 'ADB'
                           AND dou.ezcancelflag = '0'
                           AND psn.glbl_cmpy_cd = 'ADB'
                           AND psn.ezcancelflag = '0'
                           AND lbrt.glbl_cmpy_cd = 'ADB'
                           AND lbrt.ezcancelflag = '0'
                           AND atra.ds_acct_num = actv_accts.ds_acct_num
                           AND actv_accts.loc_num = atra.loc_num
                           AND dou.org_cd = atra.org_cd
                           AND psn.psn_cd = atra.psn_cd
                           --AND psn.EZINUSERID = 'Q05381' --for testing only
                           AND lbrt.line_biz_role_tp_cd = atra.line_biz_role_tp_cd
                           AND atra.line_biz_role_tp_cd IS NOT NULL
						   AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
						   AND tgt.line_biz_tp_cd = 'ESS'
                           AND NOT EXISTS (
                                   SELECT /*+ INDEX(b CANON_E404_ACCT_PROS_RES_N2) */ 1 
                                     FROM canon_e404_sf_acct_team_tbl b
                                    WHERE actv_accts.party_id = b.party_id
                                      AND actv_accts.party_site_id = b.party_site_id
                                      AND atra.org_cd = b.terr_id
                                      AND atra.psn_cd = b.RESOURCE_ID
                                      AND atra.asg_trty_attrb_cd = b.attribute_id                                   
                                      AND atra.line_biz_role_tp_cd = b.RELATIONSHIP_FLAG                                    
                                      AND b.organization_type = 'ACCOUNT'
                                      AND b.status_flag  in ('I', 'IP', 'IE')
                                      )
                           UNION
                           SELECT actv_accts.party_id
                               ,actv_accts.party_site_id
                               ,dou.org_nm territory
                               ,atra.org_cd terr_id
                               ,atra.psn_cd resource_id
                               ,psn.psn_num salesrep_number
                               ,psn.psn_last_nm || ', ' || psn.psn_first_nm salesrep_name
                               ,atra.asg_trty_attrb_cd attribute_id
                               ,dou.trty_grp_tp_cd LOB
                               ,ATRA.LINE_BIZ_ROLE_TP_Cd relationship_flag
                               ,'PROSPECT' organization_type
                               ,lbrt.line_biz_role_tp_nm role_name
                               ,NULL
                               ,NULL
                               ,NULL
                               ,'I'
                               ,sysdate
                               ,sysdate
                          FROM pros_trty_role_asg atra
							   ,trty_grp_tp tgt
                               ,(SELECT dac.cmpy_pk party_id
                                        ,plw.loc_num
                                        ,plw.pty_loc_pk party_site_id
                                        ,dac.ds_acct_pros_pk cust_acct_id
                                        ,dac.ds_acct_num
                                    FROM ds_acct_pros dac
                                         ,pros_pty_loc_wrk plw
                                   WHERE NVL(dac.rgtn_sts_cd,'XX') NOT IN ('P99')
                                     AND dac.glbl_cmpy_cd = 'ADB'
                                     AND dac.ezcancelflag = '0'
                                     AND plw.ezcancelflag = '0'
                                     AND plw.glbl_cmpy_cd = 'ADB'
                                     AND dac.loc_num = plw.loc_num
                                     AND NVL(plw.rgtn_sts_cd,'XX') NOT IN ('P99')
                                     AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                )ACTV_ACCTS
                                ,ds_org_unit dou
                                ,s21_psn psn
                                ,line_biz_role_tp lbrt
                         WHERE atra.glbl_cmpy_cd ='ADB'
                           AND atra.ezcancelflag = '0'
                           AND dou.glbl_cmpy_cd = 'ADB'
                           AND dou.ezcancelflag = '0'
                           AND psn.glbl_cmpy_cd = 'ADB'
                           AND psn.ezcancelflag = '0'
                           AND lbrt.glbl_cmpy_cd = 'ADB'
                           AND lbrt.ezcancelflag = '0'
                           AND atra.ds_acct_num = actv_accts.ds_acct_num
                           AND actv_accts.loc_num = atra.loc_num
                           AND dou.org_cd = atra.org_cd
                           AND psn.psn_cd = atra.psn_cd
                           --AND psn.EZINUSERID = 'Q05381' --for testing only
                           AND lbrt.line_biz_role_tp_cd = atra.line_biz_role_tp_cd
						   AND atra.line_biz_role_tp_cd IS NOT NULL
						   AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
						   AND tgt.line_biz_tp_cd = 'ESS'
                           AND NOT EXISTS (
                                   SELECT /*+ INDEX(b CANON_E404_ACCT_PROS_RES_N2) */ 1 
                                     FROM canon_e404_sf_acct_team_tbl b
                                    WHERE actv_accts.party_id = b.party_id
                                      AND actv_accts.party_site_id = b.party_site_id
                                      AND atra.org_cd = b.terr_id
                                      AND atra.psn_cd = b.RESOURCE_ID
                                      AND atra.asg_trty_attrb_cd = b.attribute_id                                   
                                      AND atra.line_biz_role_tp_cd = b.RELATIONSHIP_FLAG                                    
                                      --AND b.organization_type = DECODE(atra.ds_acct_tp_cd, '10', 'ACCOUNT', '0','PROSPECT') 
                                      AND b.organization_type = 'PROSPECT'
                                      AND b.status_flag  in ('I', 'IP', 'IE')
                                    );
        dbms_output.put_line('inserted ' || SQL%rowcount );
        canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','inserted ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
     COMMIT;                 
                  

        --load manager for ADJ/Admins
       FOR adj_cur_rec IN (SELECT e404.party_id, e404.party_site_id, e404.salesrep_number, atra.psn_cd, atra.toc_cd, dou.org_tier_cd
                                  FROM acct_trty_role_asg atra
										,trty_grp_tp tgt
                                       ,toc_org_mgr_info tomi
                                       ,pty_loc_wrk plw                                       
--                                       ,ds_pty_loc_wrk dplw -- DB Changes
--                                       ,ds_acct_cust dac 
                                       ,sell_to_cust dac    --DB Change                                    
                                       ,canon_e404_sf_acct_team_tbl e404
                                       ,ds_org_unit dou
                                 --WHERE e404.party_id = dac.ds_acct_cust_pk
                                 WHERE atra.glbl_cmpy_cd = 'ADB'
                                   AND atra.ezcancelflag = '0'
								   AND tgt.glbl_cmpy_cd = 'ADB'
								   AND tgt.ezcancelflag = '0'
                                   AND tomi.glbl_cmpy_cd = 'ADB'
                                   AND tomi.ezcancelflag = '0'
                                   AND plw.glbl_cmpy_cd = 'ADB'
                                   AND plw.ezcancelflag = '0'
                                   AND dou.glbl_cmpy_cd = 'ADB'
                                   AND dou.ezcancelflag = '0'
                                   AND e404.party_id = dac.cmpy_pk
                                   AND e404.party_site_id = plw.pty_loc_pk
                                   AND e404.status_flag = 'I'
                                   AND e404.organization_type= 'ACCOUNT'
                                   AND e404.newrep_number IS NULL
                                   AND (e404.salesrep_number LIKE '%ADJ%' OR e404.salesrep_number LIKE '%TERRITORY%')
                                   AND atra.ds_acct_num = dac.sell_to_cust_cd --DB Change
                                   AND atra.loc_num = plw.loc_num
								   AND atra.line_biz_role_tp_cd IS NOT NULL
								   AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
								   AND tgt.line_biz_tp_cd = 'ESS'
                                   AND atra.psn_cd = tomi.psn_cd
                                   AND atra.toc_cd = tomi.toc_cd
                                   AND atra.org_cd = dou.org_cd
                                 --  AND plw.line_biz_tp_cd = 'ESS' -- DB Change
--                                   AND plw.pty_loc_pk = dplw.pty_loc_pk 
                                   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                             UNION
                             SELECT e404.party_id, e404.party_site_id, e404.salesrep_number, atra.psn_cd, atra.toc_cd, dou.org_tier_cd
                                      FROM  toc_org_mgr_info tomi
                                           ,pros_trty_role_asg atra
										   ,trty_grp_tp tgt
                                           ,pros_pty_loc_wrk plw                                       
--                                           ,ds_pty_loc_wrk dplw -- DB Change
                                           ,ds_acct_pros dac 
                                           ,canon_e404_sf_acct_team_tbl e404
                                           ,ds_org_unit dou
                                     --WHERE e404.party_id = dac.ds_acct_pros_pk -- DB Change
                                     WHERE atra.glbl_cmpy_cd = 'ADB'
                                       AND atra.ezcancelflag = '0'
                                       AND tomi.glbl_cmpy_cd = 'ADB'
                                       AND tomi.ezcancelflag = '0'
                                       AND tgt.ezcancelflag = '0'
                                       AND tgt.glbl_cmpy_cd = 'ADB'
                                       AND plw.glbl_cmpy_cd = 'ADB'
                                       AND plw.ezcancelflag = '0'
                                       AND dou.glbl_cmpy_cd = 'ADB'
                                       AND dou.ezcancelflag = '0'
                                       AND e404.party_id = dac.cmpy_pk
                                       AND e404.party_site_id = plw.pty_loc_pk
                                       AND e404.status_flag = 'I'
                                       AND e404.organization_type= 'PROSPECT'
                                       AND e404.newrep_number IS NULL
                                       AND (e404.salesrep_number LIKE '%ADJ%' OR e404.salesrep_number LIKE '%TERRITORY%')
                                       AND atra.ds_acct_num = dac.ds_acct_num
                                       AND atra.loc_num = plw.loc_num
--                                       AND dac.loc_num = plw.loc_num 
                                       AND atra.psn_cd = tomi.psn_cd
                                       AND atra.toc_cd = tomi.toc_cd
                                       AND atra.org_cd = dou.org_cd
                                       AND atra.line_biz_role_tp_cd IS NOT NULL
                                       AND tgt.trty_grp_tp_cd = atra.trty_grp_tp_cd
                                       AND tgt.line_biz_tp_cd = 'ESS'
                                       AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                            )
        LOOP
         canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,adj_cur_rec.org_tier_cd,NULL,NULL,NULL,NULL,NULL); 
            FOR l_counter IN REVERSE 1..adj_cur_rec.org_tier_cd
            LOOP
                SELECT (CASE adj_cur_rec.org_tier_cd
                              WHEN '1' THEN tomi.FIRST_ORG_MGR_PSN_CD
                              WHEN '2' THEN tomi.SCD_ORG_MGR_PSN_CD
                              WHEN '3' THEN tomi.THIRD_ORG_MGR_PSN_CD
                              WHEN '4' THEN tomi.FRTH_ORG_MGR_PSN_CD
                              WHEN '5' THEN tomi.FIFTH_ORG_MGR_PSN_CD
                              WHEN '6' THEN tomi.SIXTH_ORG_MGR_PSN_CD
                              WHEN '7' THEN tomi.SVNTH_ORG_MGR_PSN_CD
                              WHEN '8' THEN tomi.EIGHTH_ORG_MGR_PSN_CD
                              WHEN '9' THEN tomi.NINTH_ORG_MGR_PSN_CD
                              WHEN '10' THEN tomi.TENTH_ORG_MGR_PSN_CD
                              WHEN '11' THEN tomi.ELVTH_ORG_MGR_PSN_CD
                              END) mgr_psn_cd
                    INTO l_mgr
                    FROM toc_org_mgr_info tomi
                    WHERE tomi.psn_cd = adj_cur_rec.psn_cd
                      AND tomi.toc_cd = adj_cur_rec.toc_cd
                      AND tomi.glbl_cmpy_cd = 'ADB'
                      AND tomi.ezcancelflag = '0';
                      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,l_mgr,NULL,NULL,NULL,NULL,NULL); 
                IF l_mgr IS NOT NULL THEN
                    UPDATE canon_e404_sf_acct_team_tbl e404
                       SET newrep_number = (SELECT psn_num
                                              FROM s21_psn
                                              WHERE psn_cd = l_mgr)
                        WHERE party_id = adj_cur_rec.party_id
                          AND party_site_id = adj_cur_rec.party_site_id
                          AND salesrep_number = adj_cur_rec.salesrep_number;
                    COMMIT;
                    EXIT;
                END IF;
            END LOOP;
        END LOOP;
        
        
        --load SF ID of users
        UPDATE canon_e404_sf_acct_team_tbl e404
           SET USER_SFDC_ID = (SELECT sf_user_id
                                 FROM canon_e404_sf_user_mapping_tbl usr
                                WHERE usr.employee_number = e404.salesrep_number
                                  AND sf_user_id IS NOT NULL
                                  AND is_active = 'TRUE'
                              )
          WHERE user_sfdc_id IS NULL
            AND status_flag = 'I'
            AND (salesrep_number NOT LIKE '%ADJ' AND salesrep_number NOT LIKE '%TERRITORY%')
            AND newrep_number IS NULL;
        dbms_output.put_line('updated ' || SQL%rowcount || ' for user_sfdc_id');
        canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for LFS user_sfdc_id',NULL,NULL,NULL,NULL);
            
      --load SF ID for ADJs
      UPDATE canon_e404_sf_acct_team_tbl e404
         SET USER_SFDC_ID = (SELECT sf_user_id
                               FROM canon_e404_sf_user_mapping_tbl usr
                              WHERE usr.employee_number = e404.newrep_number
                                AND sf_user_id IS NOT NULL
                                AND is_active = 'TRUE'
                            )
       WHERE user_sfdc_id IS NULL
         AND status_flag = 'I'
         AND newrep_number IS NOT NULL;
        dbms_output.put_line('updated user sf ids for ADJs ' || SQL%rowcount );
        canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated user sf ids for ADJs' || Sql%Rowcount,NULL,NULL,NULL,NULL);                          
              
        --load site SF ID
        UPDATE canon_e404_sf_acct_team_tbl e404
           SET site_sfdc_id = (SELECT sf_account_id
                                 FROM canon_e404_sf_acct_mapping_tbl acct
                                WHERE acct.party_id = e404.party_id
                                  AND acct.party_site_id = e404.party_site_id
                                  AND sf_account_id IS NOT NULL
                              )
        WHERE status_flag = 'I'
          AND site_sfdc_id IS NULL;
        dbms_output.put_line('updated customer site sfdc ids ' || SQL%rowcount );
        canon_E404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated customer site sfdc ids ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
              
        --update account sharing access
        UPDATE canon_e404_sf_acct_team_tbl e404
           SET as_acct_access = 'Read'
               ,as_case_access = 'Read'
               ,as_contact_access = 'Read'
               ,as_opp_access = 'Read'
               ,unique_oracle_as_id = site_sfdc_id || user_sfdc_id
         WHERE NOT EXISTS ( SELECT 1
                              FROM canon_e404_sf_acct_mapping_tbl acct
                             WHERE acct.party_id = e404.party_id
                               AND acct.party_site_id = e404.party_site_id
                               AND acct.writing_employee_number = NVL(e404.newrep_number, e404.salesrep_number)
                           )
            AND status_flag IN ('I','IP','IE')
            AND nvl(as_status_flag,'E') = 'E' -- IS NULL;
            and site_sfdc_id is not null
            and USER_SFDC_ID is not null
            AND as_status_flag IS NULL;
        dbms_output.put_line('updated account sharing status ' || SQL%rowcount );
        Canon_E404_SF_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated LFS account sharing status ' || Sql%Rowcount,Null,Null,Null,Null);
              
        COMMIT;
     
 EXCEPTION
    WHEN OTHERS THEN
      x_status := -1;
      x_message := substr(SQLERRM,1,250);  
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);   
 END load_accounts;
 
 PROCEDURE process_account_team(x_errbuf  OUT VARCHAR2
                                ,x_retcode OUT VARCHAR2)
 AS
     l_procedure_name VARCHAR2(25) := 'process_account_team';
     l_status NUMBER;
     l_message VARCHAR2(255);
     
 BEGIN
     x_errbuf     := 'SUCCESS';
     x_retcode    := '0';   
     
     update_errored_records(l_status,l_message);
     mark_deleted_accounts(l_status,l_message);  
     archive_deleted_records(l_status,l_message);      
     load_accounts(l_status,l_message);
     
 EXCEPTION
   WHEN OTHERS THEN   
     x_errbuf     := SQLERRM;
     x_retcode    := '2'; 
     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);       
 END process_account_team;
   
END CANON_E404_SF_ACCT_TEAM_PKG;
/