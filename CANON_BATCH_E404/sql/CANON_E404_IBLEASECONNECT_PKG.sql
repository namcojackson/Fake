create or replace PACKAGE CANON_E404_IBLEASECONNECT_PKG AS

  g_package_name varchar2(50) := 'CANON_E404_IBLEASECONNECT_PKG';
   PROCEDURE extract
      (x_errbuf          OUT VARCHAR2
      ,x_retcode         OUT VARCHAR2
      );

END CANON_E404_IBLEASECONNECT_PKG;
/

create or replace PACKAGE BODY CANON_E404_IBLEASECONNECT_PKG AS

PROCEDURE extract
      (x_errbuf          OUT VARCHAR2
      ,x_retcode         OUT VARCHAR2
      )
AS
      l_start_time            VARCHAR2(250);
      l_end_time              VARCHAR2(250);
      l_argument_text         VARCHAR2(4000);
      l_procedure_name        VARCHAR2 (30);
BEGIN
      x_errbuf := 'SUCCESS';
      x_retcode := '0';
      l_procedure_name := 'EXTRACT';

      l_start_time := to_char(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');
      --fnd_file.put_line(fnd_file.log, 'Start Time: '||l_start_time);

      /*DELETE
        FROM CANON_E404_IBLEASECONN_DEL_TBL
       WHERE status = 'DP';*/

       MERGE
        INTO CANON_E404_IBLEASECONN_DEL_TBL a
       USING (SELECT c.*
                FROM CANON_E404_IBLEASECONNECT_TBL c
               WHERE 1 = 1
                 AND c.sf_ibleaseconnect_id IS NOT NULL
                 AND c.status_flag = 'P'
             ) b
          ON(a.unique_key = b.unique_key)
        WHEN MATCHED THEN
      UPDATE
         SET a.status_message = NULL
            ,a.status_flag = 'D'
            ,a.sf_ibleaseconnect_id = (CASE WHEN b.sf_ibleaseconnect_id IS NOT NULL THEN
                                                 b.sf_ibleaseconnect_id
                                            ELSE
                                                 a.sf_ibleaseconnect_id
                                        END
                                      )
        WHEN NOT MATCHED THEN
      INSERT(serial_number
            ,new_serial_number
            ,lease_number
            ,asset_id
            ,overage_tier_key
            ,meter_type
            ,sf_ib_id
            ,sf_lease_id
            ,sf_ibleaseconnect_id
            ,unique_key
            ,status_flag
            ,status_message
            ,creation_date
            ,created_by
            ,last_update_date
            ,last_updated_by
            )
      VALUES(b.serial_number
            ,b.new_serial_number
            ,b.lease_number
            ,b.asset_id
            ,b.overage_tier_key
            ,b.meter_type
            ,b.sf_ib_id
            ,b.sf_lease_id
            ,b.sf_ibleaseconnect_id
            ,b.unique_key
            ,'D'
            ,NULL
            ,b.creation_date
            ,b.created_by
            ,b.last_update_date
            ,b.last_updated_by
            );

      --fnd_file.put_line(fnd_file.LOG, 'Merged '||SQL%rowcount||' CANON_E404_IBLEASECONNECT_TBL P records with CANON_E404_IBLEASECONN_DEL_TBL (marked for delete)');
      DBMS_OUTPUT.PUT_LINE('Merged '||SQL%rowcount||' CANON_E404_IBLEASECONNECT_TBL P records with CANON_E404_IBLEASECONN_DEL_TBL (marked for delete)');
      canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' CANON_E404_IBLEASECONNECT_TBL P records with CANON_E404_IBLEASECONN_DEL_TBL (marked for delete)',NULL,NULL,NULL,NULL);

      DELETE
        FROM CANON_E404_IBLEASECONNECT_TBL
       WHERE(status_flag = 'E'
           OR (status_flag = 'P'
         AND unique_key IN (SELECT b.unique_key
                              FROM CANON_E404_IBLEASECONN_DEL_TBL b
                             WHERE 1 = 1
                               AND b.status_flag = 'D'
                           )));
DBMS_OUTPUT.PUT_LINE('Deleted '||SQL%rowcount||' CANON_E404_IBLEASECONNECT_TBL P records with CANON_E404_IBLEASECONN_DEL_TBL (marked for delete)');                           
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Deleted '||SQL%rowcount||' from CANON_E404_IBLEASECONNECT_TBL',NULL,NULL,NULL,NULL);                           
                           
      COMMIT;

       MERGE
        INTO CANON_E404_IBLEASECONNECT_TBL a
       USING(SELECT DISTINCT
                    lease.lease_number
                   ,lease.new_serial_number
                   ,lease.serial_number
                   ,lease.asset_id
                   ,lease.meter_type
                   ,lease.overage_tier_key
                   ,ib.salesforce_ib_id   
                   ,lease.sf_id
                   ,ib.serial_number||lease.lease_number||lease.asset_id||lease.meter_type||lease.overage_tier_key  unique_key
               FROM canon_e404_sf_cfs_lease_tbl lease
                   ,canon_e404_install_base_ob_tbl ib
              WHERE 1 = 1
                --AND lease.lease_number = '001-0056783-014'
                AND lease.status_flag = 'P'
                AND lease.sf_id IS NOT NULL
                AND lease.sf_status = 'S'
                AND ib.serial_number = lease.new_serial_number
                AND ib.salesforce_ib_id IS NOT NULL
                AND ib.status_flag = 'P') b
                 ON(a.unique_key = b.unique_key)
               WHEN MATCHED THEN
             UPDATE
                SET lease_number =  b.lease_number
                   ,new_serial_number = b.new_serial_number
                   ,serial_number = b.serial_number
                   ,asset_id = b.asset_id
                   ,meter_type = b.meter_type
                   ,overage_tier_key = b.overage_tier_key
                   ,sf_ib_id = b.salesforce_ib_id   
                   ,sf_lease_id = b.sf_id
                   ,status_flag = 'U'
                   ,status_message = NULL
                   ,last_update_date = SYSDATE
                   ,last_updated_by = NULL --fnd_global.user_id
               WHEN NOT MATCHED THEN
             INSERT(serial_number
                   ,new_serial_number
                   ,lease_number
                   ,asset_id
                   ,overage_tier_key
                   ,meter_type
                   ,sf_ib_id
                   ,sf_lease_id
                   ,unique_key
                   ,status_flag
                   ,status_message
                   ,creation_date
                   ,created_by
                   ,last_update_date
                   ,last_updated_by
                   )
             VALUES(b.serial_number
                   ,b.new_serial_number
                   ,b.lease_number
                   ,b.asset_id
                   ,b.overage_tier_key
                   ,b.meter_type
                   ,b.salesforce_ib_id
                   ,b.sf_id
                   ,b.unique_key
                   ,'U'
                   ,NULL
                   ,SYSDATE
                   ,NULL --fnd_global.user_id
                   ,SYSDATE
                   ,NULL --fnd_global.user_id
                   );

      --fnd_file.put_line(fnd_file.LOG, 'Merged '||SQL%rowcount||' CANON_E404_IBLEASECONNECT_TBL U records (marked for creation)');
      DBMS_OUTPUT.PUT_LINE('Merged '||SQL%rowcount||' CANON_E404_IBLEASECONNECT_TBL U records (marked for creation)');
      canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' CANON_E404_IBLEASECONNECT_TBL U records (marked for creation)',NULL,NULL,NULL,NULL);        
      COMMIT;
      
      l_end_time := to_char(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');
      --fnd_file.put_line(fnd_file.log, 'End Time: '||l_end_time);

EXCEPTION
   WHEN OTHERS THEN
--         log_error_message (p_in_package_name   => 'CANON_E404_IBLEASECONNECT_PKG',
--                            p_in_process_name   => l_procedure_name,
--                            p_in_key1           => NULL,
--                            p_in_key2           => NULL,
--                            p_in_key3           => NULL,
--                            p_in_key4           => NULL,
--                            p_in_key5           => '',
--                            p_in_error_msg      => SQLERRM,
--                            p_in_error_date     => SYSDATE);
         x_errbuf := SQLERRM;
         x_retcode := '2';
         canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,sqlerrm);
END extract;

END CANON_E404_IBLEASECONNECT_PKG;
/
