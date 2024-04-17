create or replace PACKAGE CANON_E404_SF_PROS_MERGE_PKG AS

PROCEDURE extract_prospects(x_errbuff OUT varchar2
                           ,x_retcode OUT varchar2);

END CANON_E404_SF_PROS_MERGE_PKG;
/

create or replace PACKAGE BODY   CANON_E404_SF_PROS_MERGE_PKG
AS

G_PACKAGE_NAME  VARCHAR2 (50) := 'CANON_E404_SF_PROS_MERGE_PKG';

PROCEDURE extract_prospects(x_errbuff OUT varchar2
                           ,x_retcode OUT varchar2)
AS
  PROCEDURE_NAME VARCHAR2(100) := 'extract_prospects';
  BEGIN
  
  --Reprocess error or not processed records
  DELETE from canon_e404_sf_pros_merge_tbl
  where status_flag IN ('E','I');
  dbms_output.put_line('Records deleted from CANON_E404_PROS_MERGE_TBL:' ||SQL%ROWCOUNT);
  COMMIT;
  
     
      INSERT INTO CANON_E404_SF_PROS_MERGE_TBL (
          sf_prospect_num 
          ,merge_sf_id 
          ,master_sf_id 
          ,merge_to 
          ,location_num 
          ,load_date
          ,status_flag 
          ,status_message 
          ,sf_update_date )  ( SELECT sf_prospect_num 
                                        ,merge_sf_id 
                                        ,master_sf_id 
                                        ,merge_to 
                                        ,location_num 
                                        ,load_date
                                        ,status_flag 
                                        ,status_message 
                                        ,sf_update_date
                                    FROM ( SELECT darp.rvw_pros_num sf_prospect_num
                                                  ,darp.pros_rvw_id merge_sf_id
                                                  ,e404.sf_account_id master_sf_id
                                                  ,e404.category_code merge_to
                                                  ,e404.party_site_number location_num
                                                  ,sysdate load_date
                                                  ,'I' status_flag
                                                  ,NULL status_message
                                                  ,NULL sf_update_date
                                            FROM ds_acct_rvw_pros darp, canon_e404_sf_acct_mapping_tbl e404
                                            WHERE darp.pros_rvw_sts_cd = 'D'
                                              AND darp.pros_rvw_id IS NOT NULL
                                              AND NVL (darp.xtrnl_pros_rvw_sts_cd, 'E') = 'E'
                                              AND darp.acct_src_txt = 'SFDC'
                                              AND darp.aft_loc_num = e404.party_site_number
                                              AND e404.sf_account_id IS NOT NULL
                                         UNION
                                           SELECT darp.rvw_pros_num sf_prospect_num
                                                  ,darp.pros_rvw_id merge_sf_id
                                                  ,darp2.pros_rvw_id master_sf_id
                                                  ,'PROSPECT' merge_to
                                                  ,darp2.loc_num location_num
                                                  ,sysdate load_date
                                                  ,'I' status_flag
                                                  ,NULL status_message
                                                  ,NULL sf_update_date
                                             FROM ds_acct_rvw_pros darp, ds_acct_rvw_pros darp2
                                            WHERE darp.pros_rvw_sts_cd = 'D'
                                              AND NVL (darp.xtrnl_pros_rvw_sts_cd, 'E') = 'E'
                                              AND darp.pros_rvw_id IS NOT NULL
                                              AND darp2.pros_rvw_id IS NOT NULL
                                              AND darp.aft_loc_num IS NULL
                                              AND darp.aft_ds_xref_acct_cd IS NOT NULL
                                              AND darp2.rvw_pros_num = darp.aft_ds_xref_acct_cd
                                              AND darp.acct_src_txt = 'SFDC'
                                              AND darp2.acct_src_txt = 'SFDC'
                                            )pids
                                        WHERE NOT EXISTS (SELECT 1
                                                            FROM canon_e404_sf_pros_merge_tbl m
                                                           WHERE m.sf_prospect_num = pids.sf_prospect_num
                                                             AND m.merge_sf_id = pids.merge_sf_id
                                                             AND m.master_sf_id = pids.master_sf_id
                                                             AND m.status_flag = 'P'));
        dbms_output.put_line('Records inserted into CANON_E404_PROS_MERGE_TBL:' ||SQL%ROWCOUNT); 
        COMMIT;
        x_retcode := 'S';
    
  EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    x_retcode := 'E';
    x_errbuff := SQLERRM;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END extract_prospects;                               

END CANON_E404_SF_PROS_MERGE_PKG;
/