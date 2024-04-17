create or replace PACKAGE  CANON_E633_SF_ACCT_TEAM_PKG
AS

    TYPE RESULT_CURSOR IS REF CURSOR;


    G_PACKAGE_NAME    VARCHAR2(30) := 'CANON_E633_SF_ACCT_TEAM_PKG';
    l_limit    number := 1000;

    PROCEDURE update_errored_records(x_status OUT NUMBER, x_message OUT VARCHAR2);
    PROCEDURE mark_deleted_accounts (x_status OUT  NUMBER, x_message OUT VARCHAR2);
    PROCEDURE archive_deleted_records(x_status OUT NUMBER, x_message OUT VARCHAR2);
    PROCEDURE load_accounts(x_status OUT  NUMBER, x_message OUT VARCHAR2);
    PROCEDURE process_account_team ( X_ERRBUF OUT VARCHAR2, X_RETCODE OUT VARCHAR2);

end CANON_E633_SF_ACCT_TEAM_PKG;
/
create or replace PACKAGE BODY  CANON_E633_SF_ACCT_TEAM_PKG
AS

PROCEDURE update_errored_records(x_status OUT NUMBER, x_message OUT VARCHAR2)
AS
    l_procedure_name VARCHAR2(25) := 'update_errored_records';
BEGIN
      x_status:= 1;

      UPDATE canon_E633_sf_acct_team_tbl
         set STATUS_FLAG ='I'
      WHERE status_flag = 'IE'
        AND status_flag IS NOT NULL;
	Dbms_Output.Put_Line('updated ' || Sql%Rowcount || ' for flag I');
  canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for flag I',NULL,NULL,NULL,NULL);
    

      UPDATE canon_E633_sf_acct_team_tbl
         SET status_flag ='IP'
       WHERE status_flag = 'DE'
         AND SFDC_ID IS NOT NULL
         AND status_flag IS NOT NULL;
         Dbms_Output.Put_Line('updated ' || Sql%Rowcount || ' for flag IP');
         canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for flag IP',NULL,NULL,NULL,NULL);
         
      update canon_e633_sf_acct_team_tbl
        set as_status_flag = 'D'
        where as_status_flag = 'DE'
        and sf_as_id is not null;
        Dbms_Output.Put_Line('updated ' || Sql%Rowcount || ' for flag D for AccountShare');
        canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for flag D for AcctShare',NULL,NULL,NULL,NULL);

     COMMIT;
     

EXCEPTION
   WHEN OTHERS THEN
     x_status := 2;
     x_message := substr(SQLERRM,1,255);
     canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'ERROR:'||SQLERRM);

   END update_errored_records;

PROCEDURE mark_deleted_accounts(x_status OUT NUMBER, x_message OUT VARCHAR2)
AS
    l_procedure_name VARCHAR2(25) := 'mark_deleted_accounts';
    l_mgr_psn_cd VARCHAR2(25) := null;
    l_org_tier_cd VARCHAR2(25) := null;
    l_loop_ctr NUMBER := null;
    l_toc VARCHAR2(25) := null;
    l_mark_to_delete boolean := false;

begin
      x_status := 1;
      x_message := '';
      
    -- mark the records to delete if any change in acct team members -- ACCOUNT
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET status_flag = 'D'
         WHERE organization_type = 'CUSTOMER'
           AND SFDC_ID IS NOT NULL
           AND status_flag in ('IP')
           AND NOT EXISTS (SELECT 1
                             FROM acct_trty_role_asg atra
--                                  ,ds_acct_cust dac
                                --  ,sell_to_cust dac -- DB Change
                                  ,pty_loc_wrk plw
                          --  WHERE dac.sell_to_cust_cd = atra.ds_acct_num -- DB Change
                              WHERE plw.loc_num = atra.loc_num
--                              AND E633.party_id = dac.ds_acct_cust_pk
                              AND E633.party_id = plw.cmpy_pk -- DB Changes
                              AND E633.party_site_id = plw.pty_loc_pk
                              AND E633.terr_id = atra.org_cd
                              AND E633.resource_id = atra.psn_cd
                              AND E633.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                             -- AND dac.glbl_cmpy_cd = 'ADB'
                            --  AND dac.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
                 Dbms_Output.Put_Line('Cutomers marked delete  ' || Sql%Rowcount ); 
                 canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Cutomers marked delete  ' || Sql%Rowcount,NULL,NULL,NULL,NULL);

    -- mark the records to delete if any change in acct team members -- PROSPECT
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET status_flag = 'D'
         WHERE organization_type = 'PROSPECT'
           AND SFDC_ID IS NOT NULL
           AND status_flag in ('IP')
           AND NOT EXISTS (SELECT 1
                             --FROM acct_trty_role_asg atra
                             FROM pros_trty_role_asg atra
                             --     ,ds_acct_pros dac
                                  ,pros_pty_loc_wrk plw
                        --    WHERE dac.ds_acct_num = atra.ds_acct_num
                              WHERE plw.loc_num = atra.loc_num
                              AND E633.party_id = plw.cmpy_pk
                              AND E633.party_site_id = plw.pty_loc_pk
                              AND E633.terr_id = atra.org_cd
                              AND E633.resource_id = atra.psn_cd
                              AND E633.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                          --    AND dac.glbl_cmpy_cd = 'ADB'
                           --   AND dac.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
            Dbms_Output.Put_Line('Prospects marked delete ' || Sql%Rowcount );
            canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Prospects marked delete 2 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);

    --mark the LFS record to delete if the customer account owner got created in acct team share
    UPDATE canon_E633_sf_acct_team_tbl E633
        SET status_flag = 'D'
      WHERE SFDC_ID IS NOT NULL
        AND status_flag IN ('IP')
		AND lob = 'LFS'
        AND EXISTS ( SELECT 1
                       FROM canon_E633_cust_site_stg_tbl b
					        ,pty_loc_wrk plw
							--,sell_to_cust stc
                      WHERE b.lfs_owner_employee_number = nvl(E633.newrep_number, E633.salesrep_number)
					    AND b.lfsbu IS NOT NULL --LFS accounts
						AND b.location_number = plw.loc_num 
                        AND e633.party_id = plw.cmpy_pk
                        AND e633.party_site_id = plw.pty_loc_pk);
      Dbms_Output.Put_Line('LFS Customer Acct Share marked delete ' || Sql%Rowcount );
      canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','LFS Customer Acct Share marked delete ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
	  
	  --mark the LFS record to delete if the customer account owner got created in acct team share
    UPDATE canon_E633_sf_acct_team_tbl E633
        SET status_flag = 'D'
      WHERE SFDC_ID IS NOT NULL
        AND status_flag IN ('IP')
		AND lob = 'LFS'
        AND EXISTS ( SELECT 1
                       FROM canon_E633_cust_site_stg_tbl b
					        ,pros_pty_loc_wrk plw
							--,sell_to_cust stc
                      WHERE b.lfs_owner_employee_number = nvl(E633.newrep_number, E633.salesrep_number)
					    AND b.lfsbu IS NOT NULL --LFS accounts
						AND b.location_number = plw.loc_num 
                        AND e633.party_id = plw.cmpy_pk
                        AND e633.party_site_id = plw.pty_loc_pk);
      Dbms_Output.Put_Line('LFS Prospect Acct Share marked delete ' || Sql%Rowcount );
      canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','LFS Prospect Acct Share marked delete ' || Sql%Rowcount,NULL,NULL,NULL,NULL);

    --mark the PPS customer record to delete if the account owner got created in acct team share
    UPDATE canon_E633_sf_acct_team_tbl E633
        SET status_flag = 'D'
      WHERE SFDC_ID IS NOT NULL
        AND status_flag IN ('IP')
		AND lob = 'PPS'
        AND EXISTS ( SELECT 1
                       FROM canon_E633_cust_site_stg_tbl b
					        ,pty_loc_wrk plw
							--,sell_to_cust stc
                      WHERE b.pps_owner_employee_number = nvl(E633.newrep_number, E633.salesrep_number)
					    AND b.ppsbu IS NOT NULL --PPS accounts
						AND b.location_number = plw.loc_num 
                        AND e633.party_id = plw.cmpy_pk
                        AND e633.party_site_id = plw.pty_loc_pk);
      Dbms_Output.Put_Line('PPS customer Acct Share marked delete 3 ' || Sql%Rowcount );
      canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','PPS customer Acct Share marked delete 3 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);

		--mark the PPS prospect record to delete if the account owner got created in acct team share
    UPDATE canon_E633_sf_acct_team_tbl E633
        SET status_flag = 'D'
      WHERE SFDC_ID IS NOT NULL
        AND status_flag IN ('IP')
		AND lob = 'PPS'
        AND EXISTS ( SELECT 1
                       FROM canon_E633_cust_site_stg_tbl b
					        ,pros_pty_loc_wrk plw
							--,sell_to_cust stc
                      WHERE b.pps_owner_employee_number = nvl(E633.newrep_number, E633.salesrep_number)
					    AND b.ppsbu IS NOT NULL --PPS accounts
						AND b.location_number = plw.loc_num 
                        AND e633.party_id = plw.cmpy_pk
                        AND e633.party_site_id = plw.pty_loc_pk);
      Dbms_Output.Put_Line('PPS Prospect Acct Share marked delete 3 ' || Sql%Rowcount );
      canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','PPS Prospect Acct Share marked delete 3 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);

    --for ACCOUNT
        UPDATE canon_E633_sf_acct_team_tbl E633
           set STATUS_FLAG = 'DP'
               ,as_status_flag = 'DP'
         WHERE organization_type = 'CUSTOMER'
           AND SFDC_ID IS NULL
           AND status_flag IN ('I','IE')
           AND NOT EXISTS (SELECT 1
                             FROM acct_trty_role_asg atra
--                                  ,ds_acct_cust dac
                                  -- ,sell_to_cust dac -- DB Changes
                                  ,pty_loc_wrk plw
                          --  WHERE dac.sell_to_cust_cd = atra.ds_acct_num -- DB Change
                              WHERE plw.loc_num = atra.loc_num
--                              AND E633.party_id = dac.ds_acct_cust_pk
                              AND E633.party_id = plw.cmpy_pk -- DB Changes
                              AND E633.party_site_id = plw.pty_loc_pk
                              AND E633.terr_id = atra.org_cd
                              AND E633.resource_id = atra.psn_cd
                              AND E633.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                             -- AND dac.glbl_cmpy_cd = 'ADB'
                            --  AND dac.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
                Dbms_Output.Put_Line('marked delete 4 ' || Sql%Rowcount );
                canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','marked delete 4 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
				
    --for PROSPECT
        UPDATE canon_E633_sf_acct_team_tbl E633
           set STATUS_FLAG = 'DP'
               ,as_status_flag = 'DP'
         WHERE organization_type = 'PROSPECT'
           AND SFDC_ID IS NULL
           AND status_flag IN ('I','IE')
           AND NOT EXISTS (SELECT 1
                             --FROM acct_trty_role_asg atra
                             FROM pros_trty_role_asg atra
                                --  ,ds_acct_pros dac
                                  ,pros_pty_loc_wrk plw
                         --   WHERE dac.ds_acct_num = atra.ds_acct_num
                              WHERE plw.loc_num = atra.loc_num
                              AND E633.party_id = plw.cmpy_pk
                              AND E633.party_site_id = plw.pty_loc_pk
                              AND E633.terr_id = atra.org_cd
                              AND E633.resource_id = atra.psn_cd
                              AND E633.relationship_flag = atra.line_biz_role_tp_cd
                              AND atra.glbl_cmpy_cd = 'ADB'
                              AND atra.ezcancelflag = '0'
                             -- AND dac.glbl_cmpy_cd = 'ADB'
                             -- AND dac.ezcancelflag = '0'
                              AND plw.glbl_cmpy_cd = 'ADB'
                              AND plw.ezcancelflag = '0');
                Dbms_Output.Put_Line('marked delete 5 ' || Sql%Rowcount );
                canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','marked delete 5 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
				
	
   --QC57599 - mark the record for delete if the team member is a LFS IS rep -- LFS Accounts / Prospects only
      UPDATE canon_E633_sf_acct_team_tbl E633
        SET status_flag = 'D'
      WHERE SFDC_ID IS NOT NULL
        AND status_flag IN ('IP')
		AND lob = 'LFS'
        AND EXISTS ( SELECT 1
                       FROM ds_org_unit dou
                           ,ds_org_resrc_reln dorr
                           ,s21_psn psn
                      where 1 = 1
                      and dou.glbl_cmpy_cd = 'ADB'
                      and dou.ezcancelflag = '0'
                      and dorr.glbl_cmpy_cd = 'ADB'
                      and dorr.ezcancelflag = '0'
                      and psn.glbl_cmpy_cd = 'ADB'
                      and psn.ezcancelflag = '0'
                      and dou.trty_grp_tp_cd = 'IS'
                      and dou.org_cd = dorr.org_cd
                      and dorr.psn_cd = psn.psn_cd
                      AND trunc(sysdate) BETWEEN trunc(nvl(to_date(dou.eff_from_dt, 'yyyymmdd'), sysdate)) AND trunc(nvl(to_date(dou.eff_thru_dt, 'yyyymmdd'), sysdate))
                      AND trunc(sysdate) BETWEEN trunc(nvl(to_date(dorr.eff_from_dt, 'yyyymmdd'), sysdate)) AND trunc(nvl(to_date(dorr.eff_thru_dt, 'yyyymmdd'), sysdate))
                      AND dorr.acct_team_role_tp_cd is null
                      AND psn.psn_num = nvl(E633.newrep_number, E633.salesrep_number));

      Dbms_Output.Put_Line('PPS Prospect Acct Share marked delete 7 ' || Sql%Rowcount );
      canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','PPS Prospect Acct Share marked delete 7 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);	

    COMMIT;

    --If a manager changes for ADJ or TERRITORY and old mgr is successfully created as Team Member in SFDC, mark that for DELETE
        BEGIN
            l_mgr_psn_cd := null;
            l_org_tier_cd := null;
            l_loop_ctr := null;
            l_toc := null;
            l_mark_to_delete := false;

            FOR accts_to_del_rec IN (SELECT E633.party_id, E633.party_site_id, salesrep_number, psn_rep.psn_cd, E633.newrep_number, psn_mgr.psn_cd newrep_psn_cd, dou.org_tier_cd
                                       FROM canon_E633_sf_acct_team_tbl E633
                                            ,ds_org_unit dou
                                            ,s21_psn psn_mgr
                                            ,s21_psn psn_rep
                                      WHERE status_flag IN ('IP','I','IE')
                                        AND (salesrep_number LIKE '%ADJ%' OR salesrep_number LIKE '%TERRITORY%')
                                        AND newrep_number IS NOT NULL
                                        AND E633.terr_id = dou.org_cd
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
                          AND tomi.ezcancelflag = '0'
						  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, 'yyyymmdd'),SYSDATE)) 
                          ;
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
                    UPDATE canon_E633_sf_acct_team_tbl E633
                       SET status_flag = 'D'
                     WHERE party_id = accts_to_del_rec.party_id
                       AND party_site_id = accts_to_del_rec.party_site_id
--                       AND newrep_number = accts_to_del_rec.newrep_number
                       AND salesrep_number = accts_to_del_rec.salesrep_number;
                       COMMIT;
                END IF;

            END LOOP;
        EXCEPTION
            When Others Then
                canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'ERROR:'||SQLERRM);
        End;
        canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','marked delete 6 ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
        update canon_e633_sf_acct_team_tbl
          set as_status_flag = 'D'
          where status_flag = 'D'
          and sf_as_id is not null
          And Sfdc_Id Is Not Null;
          canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Updated Acct Share flag for Delete ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
        COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        x_status := 2;
        x_message := substr(SQLERRM,1,255);
        canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'ERROR:'||SQLERRM);
END mark_deleted_accounts;

PROCEDURE archive_deleted_records(x_status OUT NUMBER, x_message OUT VARCHAR2)
   AS
     l_procedure_name VARCHAR2(25) := 'archive_records';

   BEGIN

       x_status := 1;
      x_message := '';

       INSERT INTO canon_E633_sf_acct_team_arc
       SELECT * FROM canon_E633_sf_acct_team_tbl
       where status_flag = 'DP' 
        AND as_status_flag = 'DP';
		Dbms_Output.Put_Line('archiving ' || Sql%Rowcount );
    canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','archiving ' || Sql%Rowcount,NULL,NULL,NULL,NULL);

       delete from canon_e633_sf_acct_team_tbl
       WHERE status_flag = 'DP' and as_status_flag = 'DP';
       
       Dbms_Output.Put_Line('deleting ' || Sql%Rowcount || ' from the table' );
       canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','deleting ' || Sql%Rowcount || ' from the table',NULL,NULL,NULL,NULL);

       COMMIT;

   EXCEPTION
   WHEN OTHERS THEN
          x_status := 2;
          x_message := SUBSTR(SQLERRM,1,255);
          canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'ERROR:'||SQLERRM);
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
    INSERT INTO canon_E633_sf_acct_team_tbl(
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
                    )   select actv_accts.party_id
                               ,actv_accts.party_site_id
                               ,dou.org_nm territory
                               ,atra.org_cd terr_id
                               ,atra.psn_cd resource_id
                               ,psn.psn_num salesrep_number
                               ,CASE WHEN psn.psn_last_nm <> null THEN psn.psn_last_nm || ', ' || psn.psn_first_nm ELSE null END
                               ,atra.asg_trty_attrb_cd attribute_id
                               ,tgt.line_biz_tp_cd LOB
                               ,ATRA.LINE_BIZ_ROLE_TP_Cd relationship_flag
                               ,'CUSTOMER' organization_type
                               ,lbrt.line_biz_role_tp_nm role_name
                               ,NULL
                               ,NULL
                               ,NULL
                               ,'I'
                               ,sysdate
                               ,sysdate
                          from acct_trty_role_asg atra
                               ,trty_grp_tp tgt
                               ,(select dac.cmpy_pk party_id
                                        ,plw.loc_num
                                        ,plw.pty_loc_pk party_site_id
--                                        ,ds_acct_cust_pk cust_acct_id -- DB Chanegs
                                         ,dac.sell_to_cust_pk cust_acct_id -- DB Changes
                                        ,dac.sell_to_cust_cd ds_acct_num
--                                   FROM ds_acct_cust dac
                                   from sell_to_cust dac -- DB Changes
                                        ,acct_loc al
                                        ,pty_loc_wrk plw
                                  WHERE NVL(dac.rgtn_sts_cd,'XX') NOT IN ('P99')
                                    AND dac.glbl_cmpy_cd = 'ADB'
                                    and dac.ezcancelflag = '0'
                                    and plw.ezcancelflag = '0'
                                    and plw.glbl_cmpy_cd = 'ADB'
                                    and al.glbl_cmpy_cd = dac.glbl_cmpy_cd
                                    and al.ezcancelflag = dac.ezcancelflag
                                    and dac.sell_to_cust_cd = al.ds_acct_num
                                    and al.loc_num = plw.loc_num
                                    and nvl(al.rgtn_sts_cd,'XX') not in ('P99')
                                    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                    and nvl(plw.rgtn_sts_cd,'XX') not in ('P99')
                                    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                )actv_accts
--                                ,pty_loc_wrk plw
--                                ,ds_pty_loc_wrk dplw -- DB Change
                                ,ds_org_unit dou
                                ,s21_psn psn
                                ,line_biz_role_tp lbrt
                         WHERE 1=1
                           AND dou.ezcancelflag = '0'
                           AND psn.glbl_cmpy_cd = 'ADB'
                           AND psn.ezcancelflag = '0'
                           AND lbrt.glbl_cmpy_cd = 'ADB'
                           AND lbrt.ezcancelflag = '0'
                           and tgt.glbl_cmpy_cd = 'ADB'
                           and tgt.ezcancelflag = '0'
                           AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                           and tgt.line_biz_tp_cd in ('LFS','PPS')
						   and tgt.trty_grp_tp_cd <> 'IS' -- QC57599 - to exclude IS reps
                           AND atra.line_biz_role_tp_cd is not null
                           AND atra.ds_acct_num = actv_accts.ds_acct_num
                           AND atra.loc_num = actv_accts.loc_num
                           AND dou.org_cd = atra.org_cd
                           AND psn.psn_cd = atra.psn_cd
                           --AND psn.EZINUSERID = 'Q05381' --for testing only
                           AND lbrt.line_biz_role_tp_cd = atra.line_biz_role_tp_cd
--                           AND plw.line_biz_tp_cd IN ('LFS','PPS') -- DB Change --LOB changes
--                           AND plw.pty_loc_pk = dplw.pty_loc_pk
                           and not exists (
                                   SELECT /*+ INDEX(b CANON_E633_SF_ACCT_TEAM_N2) */ 1
                                     FROM canon_E633_sf_acct_team_tbl b
                                    where actv_accts.party_id = b.party_id
                                      AND actv_accts.party_site_id = b.party_site_id
                                      AND atra.org_cd = b.terr_id
                                      AND atra.psn_cd = b.RESOURCE_ID
                                      AND atra.asg_trty_attrb_cd = b.attribute_id
                                      AND atra.line_biz_role_tp_cd = b.RELATIONSHIP_FLAG
                                      AND b.organization_type = 'CUSTOMER'
                                      AND b.status_flag  in ('I', 'IP', 'IE')
                                      )
                           UNION
                           select actv_accts.party_id
                               ,actv_accts.party_site_id
                               ,dou.org_nm territory
                               ,atra.org_cd terr_id
                               ,atra.psn_cd resource_id
                               ,psn.psn_num salesrep_number
                               ,CASE WHEN psn.psn_last_nm <> null THEN psn.psn_last_nm || ', ' || psn.psn_first_nm ELSE null END  salesrep_name
                               ,atra.asg_trty_attrb_cd attribute_id
                               ,tgt.line_biz_tp_cd LOB
                               ,ATRA.LINE_BIZ_ROLE_TP_Cd relationship_flag
                               ,'PROSPECT' organization_type
                               ,lbrt.line_biz_role_tp_nm role_name
                               ,NULL
                               ,NULL
                               ,NULL
                               ,'I'
                               ,sysdate
                               ,sysdate
                          from pros_trty_role_asg atra
                               ,trty_grp_tp tgt
                               ,(select dac.cmpy_pk party_id
                                        ,plw.loc_num
                                        ,plw.pty_loc_pk party_site_id
--                                        ,ds_acct_cust_pk cust_acct_id -- DB Chanegs
                                         ,dac.ds_acct_pros_pk cust_acct_id -- DB Changes
                                        ,dac.ds_acct_num
--                                   FROM ds_acct_cust dac
                                   from ds_acct_pros dac -- DB Changes
--                                        ,acct_loc al
                                        ,pros_pty_loc_wrk plw
                                  WHERE NVL(dac.rgtn_sts_cd,'XX') NOT IN ('P99')
                                    AND dac.glbl_cmpy_cd = 'ADB'
                                    and dac.ezcancelflag = '0'
                                    and plw.ezcancelflag = '0'
                                    and plw.glbl_cmpy_cd = 'ADB'
--                                    and al.glbl_cmpy_cd = dac.glbl_cmpy_cd
--                                    and al.ezcancelflag = dac.ezcancelflag
--                                    and dac.ds_acct_num = al.ds_acct_num
                                    and dac.loc_num = plw.loc_num
--                                    and nvl(al.rgtn_sts_cd,'XX') not in ('P99')
--                                    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                    and nvl(plw.rgtn_sts_cd,'XX') not in ('P99')
                                    AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                )actv_accts
--                                ,pros_pty_loc_wrk plw
--                                ,ds_pty_loc_wrk dplw - DB Changes
                                ,ds_org_unit dou
                                ,s21_psn psn
                                ,line_biz_role_tp lbrt
                         WHERE 1=1
--                           AND plw.glbl_cmpy_cd ='ADB'
--                           AND plw.ezcancelflag = '0'
--                           AND dplw.ezcancelflag = '0' -- DB Changes
--                           AND dplw.glbl_cmpy_cd ='ADB' -- DB Changes
                           AND dou.glbl_cmpy_cd = 'ADB'
                           AND dou.ezcancelflag = '0'
                           AND psn.glbl_cmpy_cd = 'ADB'
                           AND psn.ezcancelflag = '0'
                           AND lbrt.glbl_cmpy_cd = 'ADB'
                           AND lbrt.ezcancelflag = '0'
                           and tgt.glbl_cmpy_cd = 'ADB'
                           and tgt.ezcancelflag = '0'
                           AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                           and tgt.line_biz_tp_cd in ('LFS','PPS')
						   and tgt.trty_grp_tp_cd <> 'IS' -- QC57599 to exclude IS reps
                           AND atra.line_biz_role_tp_cd is not null
                           AND atra.ds_acct_num = actv_accts.ds_acct_num
                           AND atra.loc_num = actv_accts.loc_num
                           AND dou.org_cd = atra.org_cd
                           AND psn.psn_cd = atra.psn_cd
                           --AND psn.EZINUSERID = 'Q05381' --for testing only
                           AND lbrt.line_biz_role_tp_cd = atra.line_biz_role_tp_cd
--                           AND plw.line_biz_tp_cd IN ('LFS','PPS') -- DB Changes -- LOB changes
--                           AND plw.pty_loc_pk = dplw.pty_loc_pk
                           AND NOT EXISTS (
                                   SELECT /*+ INDEX(b CANON_E633_SF_ACCT_TEAM_N2) */ 1
                                     FROM canon_E633_sf_acct_team_tbl b
                                    where actv_accts.party_id = b.party_id
                                      AND actv_accts.party_site_id = b.party_site_id
                                      AND atra.org_cd = b.terr_id
                                      AND atra.psn_cd = b.RESOURCE_ID
                                      AND atra.asg_trty_attrb_cd = b.attribute_id
                                      AND atra.line_biz_role_tp_cd = b.RELATIONSHIP_FLAG
                                      --AND b.organization_type = DECODE(atra.ds_acct_tp_cd, '10', 'ACCOUNT', '0','PROSPECT')
                                      AND b.organization_type = 'PROSPECT'
                                      AND b.status_flag  in ('I', 'IP', 'IE')
                                    );
        --canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,SQL%ROWCOUNT,NULL,NULL,NULL,NULL,NULL);
        Dbms_Output.Put_Line('inserted ' || Sql%Rowcount );
        canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','inserted ' || Sql%Rowcount,NULL,NULL,NULL,NULL);
     COMMIT;


        --load manager for ADJ/Admins
       for adj_cur_rec in (select e633.party_id
                                  ,e633.party_site_id
                                  ,e633.salesrep_number
                                  ,atra.psn_cd
                                  ,atra.toc_cd
                                  ,dou.org_tier_cd
                            FROM (SELECT ta.loc_num
                                        ,ta.ds_acct_num
                                        ,ta.org_cd
                                        ,ta.psn_cd
                                        ,ta.toc_cd
                                        ,ta.asg_trty_attrb_cd
                                        ,ta.line_biz_role_tp_cd
                                   FROM acct_trty_role_asg ta
                                        ,trty_grp_tp tgt
                                  WHERE 1=1
                                    AND ta.glbl_cmpy_cd = 'ADB'
                                    AND ta.ezcancelflag = '0'
                                    AND tgt.glbl_cmpy_cd = 'ADB'
                                    AND tgt.ezcancelflag = '0'
                                    AND ta.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                                    and tgt.line_biz_tp_cd in ('LFS','PPS')
									and tgt.trty_grp_tp_cd <> 'IS' --QC57599 - to exclude IS reps
                                    and ta.line_biz_role_tp_cd is not null
                                    ) atra
                                    ,toc_org_mgr_info tomi
                                    ,canon_E633_sf_acct_team_tbl E633
                                    ,ds_org_unit dou
                                 WHERE 1 = 1
                                   AND tomi.glbl_cmpy_cd = 'ADB'
                                   AND tomi.ezcancelflag = '0'
                                   AND E633.status_flag = 'I'
                                   AND E633.organization_type= 'CUSTOMER'
                                   AND E633.newrep_number IS NULL
                                   AND (E633.salesrep_number LIKE '%ADJ%' OR E633.salesrep_number LIKE '%TERRITORY%')
                                   AND atra.psn_cd = tomi.psn_cd
                                   AND atra.toc_cd = tomi.toc_cd
                                   AND atra.org_cd = dou.org_cd
                                   and trunc(sysdate) between trunc(nvl(to_date(tomi.eff_from_dt,'yyyymmdd'),sysdate)) and trunc(nvl(to_date(tomi.eff_thru_dt, 'yyyymmdd'),sysdate))
                                   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                             UNION
                               SELECT e633.party_id
                                      ,e633.party_site_id
                                      ,e633.salesrep_number
                                      ,atra.psn_cd
                                      ,atra.toc_cd
                                      ,dou.org_tier_cd
                                FROM  toc_org_mgr_info tomi
                                       ,(SELECT ta.loc_num
                                               ,ta.ds_acct_num
                                               ,ta.org_cd
                                               ,ta.psn_cd
                                               ,ta.toc_cd
                                               ,ta.asg_trty_attrb_cd
                                               ,ta.line_biz_role_tp_cd
                                           FROM pros_trty_role_asg ta
                                                ,trty_grp_tp tgt
                                          WHERE 1=1
                                            AND ta.glbl_cmpy_cd = 'ADB'
                                            AND ta.ezcancelflag = '0'
                                            AND tgt.glbl_cmpy_cd = 'ADB'
                                            AND tgt.ezcancelflag = '0'
                                            AND ta.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                                            and tgt.line_biz_tp_cd in ('LFS','PPS')
											and tgt.trty_grp_tp_cd <> 'IS' --QC57599 to exclude IS reps
                                            AND ta.line_biz_role_tp_cd is not null) atra
                                         ,canon_E633_sf_acct_team_tbl E633
                                         ,ds_org_unit dou
                                  WHERE 1 = 1
                                         AND tomi.glbl_cmpy_cd = 'ADB'
                                         AND tomi.ezcancelflag = '0'
                                         AND E633.status_flag = 'I'
                                         AND E633.organization_type= 'PROSPECT'
                                         AND E633.newrep_number IS NULL
                                         AND (E633.salesrep_number LIKE '%ADJ%' OR E633.salesrep_number LIKE '%TERRITORY%')
                                         AND atra.psn_cd = tomi.psn_cd
                                         AND atra.toc_cd = tomi.toc_cd
                                         AND atra.org_cd = dou.org_cd
                                         and trunc(sysdate) between trunc(nvl(to_date(tomi.eff_from_dt,'yyyymmdd'),sysdate)) and trunc(nvl(to_date(tomi.eff_thru_dt, 'yyyymmdd'),sysdate))
                                         AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                            )
        LOOP
         canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,adj_cur_rec.org_tier_cd,NULL,NULL,NULL,NULL,NULL);
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
					  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                      AND tomi.glbl_cmpy_cd = 'ADB'
                      AND tomi.ezcancelflag = '0';
                      canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,l_mgr,NULL,NULL,NULL,NULL,NULL);
                IF l_mgr IS NOT NULL THEN
                    UPDATE canon_E633_sf_acct_team_tbl E633
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


        --load LFS SF ID of users
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET USER_SFDC_ID = (SELECT sf_user_id
                                 FROM canon_E633_LFS_user_map_tbl usr
                                WHERE usr.employee_number = E633.salesrep_number
                                  AND sf_user_id IS NOT NULL
                                  AND is_active = 'TRUE'
                              )
          WHERE user_sfdc_id IS NULL
		    AND LOB IN ('LFS')
            AND status_flag = 'I'
            AND (salesrep_number NOT LIKE '%ADJ' AND salesrep_number NOT LIKE '%TERRITORY%')
            AND newrep_number IS NULL;
		Dbms_Output.Put_Line('updated ' || Sql%Rowcount || ' for LFS user_sfdc_id');
    canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for LFS user_sfdc_id',NULL,NULL,NULL,NULL);

		--load PPS SF ID of users
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET USER_SFDC_ID = (SELECT sf_user_id
                                 FROM canon_E633_PPS_user_map_tbl usr
                                WHERE usr.employee_number = E633.salesrep_number
                                  AND sf_user_id IS NOT NULL
                                  AND is_active = 'TRUE'
                              )
          WHERE user_sfdc_id IS NULL
		    AND LOB IN ('PPS')
            AND status_flag = 'I'
            AND (salesrep_number NOT LIKE '%ADJ' AND salesrep_number NOT LIKE '%TERRITORY%')
            AND newrep_number IS NULL;
		Dbms_Output.Put_Line('updated ' || Sql%Rowcount || ' for PPS user_sfdc_id');
    canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated ' || Sql%Rowcount || ' for PPS user_sfdc_id',NULL,NULL,NULL,NULL);

      --load LFS SF ID for ADJs
      UPDATE canon_E633_sf_acct_team_tbl E633
         SET USER_SFDC_ID = (SELECT sf_user_id
                               FROM canon_E633_LFS_user_map_tbl usr
                              WHERE usr.employee_number = E633.newrep_number
                                AND sf_user_id IS NOT NULL
                                AND is_active = 'TRUE'
                            )
       WHERE user_sfdc_id IS NULL
	     AND LOB IN ('LFS')
         AND status_flag = 'I'
         And Newrep_Number Is Not Null;
         Dbms_Output.Put_Line('updated user sf ids for ADJ-LFS ' || Sql%Rowcount );
         canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated user sf ids for ADJ-LFS' || Sql%Rowcount,NULL,NULL,NULL,NULL);

		 --load PPS SF ID for ADJs
      UPDATE canon_E633_sf_acct_team_tbl E633
         SET USER_SFDC_ID = (SELECT sf_user_id
                               FROM canon_E633_PPS_user_map_tbl usr
                              WHERE usr.employee_number = E633.newrep_number
                                AND sf_user_id IS NOT NULL
                                AND is_active = 'TRUE'
                            )
       WHERE user_sfdc_id IS NULL
	     AND LOB IN ('PPS')
         AND status_flag = 'I'
         And Newrep_Number Is Not Null;
         Dbms_Output.Put_Line('updated user sf ids for ADJ-PPS ' || Sql%Rowcount );
        canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated user sf ids for ADJ-PPS' || Sql%Rowcount,NULL,NULL,NULL,NULL);

        --load LFS customer site SF ID
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET site_sfdc_id = (SELECT lfs_sf_account_id
                                 FROM canon_E633_cust_site_stg_tbl acct
                                      ,pty_loc_wrk plw
                                WHERE plw.loc_num = acct.location_number
                                  AND plw.cmpy_pk = E633.party_id
                                  and plw.pty_loc_pk = e633.party_site_id
                                  and acct.lfsbu is not null
                                  AND acct.lfs_sf_account_id IS NOT NULL
                              )
        WHERE status_flag = 'I'
		  AND lob = 'LFS'
		  AND organization_type= 'CUSTOMER'
          AND site_sfdc_id IS NULL;
		Dbms_Output.Put_Line('updated LFS customer site sfdc ids ' || Sql%Rowcount );
    canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated LFS customer site sfdc ids ' || Sql%Rowcount,NULL,NULL,NULL,NULL);

		--load LFS prospect site SF ID
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET site_sfdc_id = (SELECT lfs_sf_account_id
                                 FROM canon_E633_cust_site_stg_tbl acct
                                      ,pros_pty_loc_wrk plw
                                WHERE plw.loc_num = acct.location_number
                                  AND plw.cmpy_pk = E633.party_id
                                  AND plw.pty_loc_pk = E633.party_site_id
                                  AND lfsbu IS NOT NULL
                                  AND lfs_sf_account_id IS NOT NULL
                              )
        WHERE status_flag = 'I'
		  AND LOB = 'LFS'
		  AND organization_type= 'PROSPECT'
          AND site_sfdc_id IS NULL;
		Dbms_Output.Put_Line('updated LFS prospect site sfdc ids ' || Sql%Rowcount );
		Canon_E633_Sf_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated LFS prospect site sfdc ids ' || Sql%Rowcount,Null,Null,Null,Null);

		--load PPS customer site SF ID
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET site_sfdc_id = (SELECT pps_sf_account_id
                                 from canon_e633_cust_site_stg_tbl acct
                                      ,pty_loc_wrk plw
                                WHERE plw.loc_num = acct.location_number
                                  AND plw.cmpy_pk = E633.party_id
                                  AND plw.pty_loc_pk = E633.party_site_id
                                  AND ppsbu IS NOT NULL
                                  AND pps_sf_account_id IS NOT NULL
                              )
        WHERE status_flag = 'I'
		  AND lob = 'PPS'
		  AND organization_type= 'CUSTOMER'
          AND site_sfdc_id IS NULL;
		Dbms_Output.Put_Line('updated PPS customer site sfdc ids ' || Sql%Rowcount );
    Canon_E633_Sf_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated PPS customer site sfdc ids ' || Sql%Rowcount,Null,Null,Null,Null);

		--load LFS prospect site SF ID
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET site_sfdc_id = (SELECT pps_sf_account_id
                                 FROM canon_E633_cust_site_stg_tbl acct
                                      ,pros_pty_loc_wrk plw
                                WHERE plw.loc_num = acct.location_number
                                  AND plw.cmpy_pk = E633.party_id
                                  AND plw.pty_loc_pk = E633.party_site_id
                                  AND ppsbu IS NOT NULL
                                  AND pps_sf_account_id IS NOT NULL
                              )
        WHERE status_flag = 'I'
		  AND LOB = 'PPS'
		  AND organization_type= 'PROSPECT'
          AND site_sfdc_id IS NULL;
		Dbms_Output.Put_Line('updated PPS prospect site sfdc ids ' || Sql%Rowcount );
    Canon_E633_Sf_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated PPS prospect site sfdc ids ' || Sql%Rowcount,Null,Null,Null,Null);

        --update account sharing access
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET as_acct_access = 'Read'
               ,as_case_access = 'Read'
               ,as_contact_access = 'Read'
               ,as_opp_access = 'Read'
               ,unique_oracle_as_id = site_sfdc_id || user_sfdc_id
         WHERE NOT EXISTS ( SELECT 1
                              FROM canon_E633_cust_site_stg_tbl acct
                                    ,pty_loc_wrk plw
                             WHERE acct.location_number = plw.loc_num
                                AND plw.cmpy_pk = E633.party_id
                                AND plw.pty_loc_pk = E633.party_site_id
                                AND acct.lfs_owner_employee_number = NVL(E633.newrep_number, E633.salesrep_number)
                           )
            and status_flag in ('I','IP','IE')
            and NVL(as_status_flag, 'E') = 'E'
            AND organization_type = 'CUSTOMER'
            and lob = 'LFS'
            and site_sfdc_id is not null
            and USER_SFDC_ID is not null
            AND as_status_flag IS NULL;
            Dbms_Output.Put_Line('updated LFS customer sharing status ' || Sql%Rowcount );
            Canon_E633_Sf_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated LFS customer sharing status ' || Sql%Rowcount,Null,Null,Null,Null);

		--update account sharing access
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET as_acct_access = 'Read'
               ,as_case_access = 'Read'
               ,as_contact_access = 'Read'
               ,as_opp_access = 'Read'
               ,unique_oracle_as_id = site_sfdc_id || user_sfdc_id
         WHERE NOT EXISTS ( SELECT 1
                              FROM canon_E633_cust_site_stg_tbl acct
                                    ,pros_pty_loc_wrk plw
                             WHERE acct.location_number = plw.loc_num
                                AND plw.cmpy_pk = E633.party_id
                                AND plw.pty_loc_pk = E633.party_site_id
                                AND acct.lfs_owner_employee_number = NVL(E633.newrep_number, E633.salesrep_number)
                           )
            AND status_flag IN ('I','IP','IE')
            and organization_type = 'PROSPECT'
            and NVL(as_status_flag, 'E') = 'E'
            and lob = 'LFS'
            AND site_sfdc_id is not null
            and USER_SFDC_ID is not null
            AND as_status_flag IS NULL;
            Dbms_Output.Put_Line('updated LFS prospect sharing status ' || Sql%Rowcount );
            Canon_E633_Sf_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated LFS prospect sharing status ' || Sql%Rowcount,Null,Null,Null,Null);

		--update account sharing access
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET as_acct_access = 'Read'
               ,as_case_access = 'Read'
               ,as_contact_access = 'Read'
               ,as_opp_access = 'Read'
               ,unique_oracle_as_id = site_sfdc_id || user_sfdc_id
         WHERE NOT EXISTS ( SELECT 1
                              FROM canon_E633_cust_site_stg_tbl acct
                                    ,pty_loc_wrk plw
                             WHERE acct.location_number = plw.loc_num
                                AND plw.cmpy_pk = E633.party_id
                                AND plw.pty_loc_pk = E633.party_site_id
                                AND acct.pps_owner_employee_number = NVL(E633.newrep_number, E633.salesrep_number)
                           )
            and status_flag in ('I','IP','IE')
            and NVL(as_status_flag, 'E') = 'E'
            AND organization_type = 'CUSTOMER'
            and lob = 'PPS'
            AND site_sfdc_id is not null
            and USER_SFDC_ID is not null
            And As_Status_Flag Is Null;
            Dbms_Output.Put_Line('updated PPS customer account sharing status ' || Sql%Rowcount );
            Canon_E633_Sf_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated PPS customer account sharing status ' || Sql%Rowcount,Null,Null,Null,Null);

		--update account sharing access
        UPDATE canon_E633_sf_acct_team_tbl E633
           SET as_acct_access = 'Read'
               ,as_case_access = 'Read'
               ,as_contact_access = 'Read'
               ,as_opp_access = 'Read'
               ,unique_oracle_as_id = site_sfdc_id || user_sfdc_id
         WHERE NOT EXISTS ( SELECT 1
                              FROM canon_E633_cust_site_stg_tbl acct
                                    ,pros_pty_loc_wrk plw
                             WHERE acct.location_number = plw.loc_num
                                AND plw.cmpy_pk = E633.party_id
                                AND plw.pty_loc_pk = E633.party_site_id
                                AND acct.pps_owner_employee_number = NVL(E633.newrep_number, E633.salesrep_number)
                           )
            and status_flag in ('I','IP','IE')
            and NVL(as_status_flag, 'E') = 'E'
            AND organization_type = 'PROSPECT'
            and lob = 'PPS'
            AND site_sfdc_id is not null
            and USER_SFDC_ID is not null
            AND as_status_flag IS NULL;
            Dbms_Output.Put_Line('updated PPS prospect sharing status ' || Sql%Rowcount );
            Canon_E633_Sf_Error_Log_Pkg.Log_Error(G_Package_Name,L_Procedure_Name,'INFO','updated PPS prospect sharing status ' || Sql%Rowcount,Null,Null,Null,Null);
        commit;


 EXCEPTION
    when others then
      x_status := 2;
      x_message := substr(sqlerrm,1,250);
      dbms_output.put_line(sqlerrm);

    canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'ERROR:'||SQLERRM);
 END load_accounts;

 PROCEDURE process_account_team(x_errbuf  OUT VARCHAR2
                                ,x_retcode OUT VARCHAR2)
 AS
     l_procedure_name VARCHAR2(25) := 'process_account_team';
     l_status NUMBER;
     l_message VARCHAR2(255);

 BEGIN
     x_errbuf     := 'SUCCESS';
     x_retcode    := 'S';

     update_errored_records(l_status,l_message);
     mark_deleted_accounts(l_status,l_message);
     archive_deleted_records(l_status,l_message);
     Load_Accounts(L_Status,L_Message);
dbms_output.put_line('THE END');
 EXCEPTION
   WHEN OTHERS THEN
     x_errbuf     := SQLERRM;
     x_retcode    := 'E';
     canon_E633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'ERROR:'||SQLERRM);
 END process_account_team;

END CANON_E633_SF_ACCT_TEAM_PKG;
/