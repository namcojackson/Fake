create or replace PACKAGE CANON_E404_SF_INSTALL_BASE_PKG
AS

PROCEDURE populate_req_data ;

PROCEDURE populate_ib_data ;

PROCEDURE insert_contract_data ;

PROCEDURE populate_contract_data(p_batch_number      IN  NUMBER) ;

PROCEDURE insert_meter_reads_data ;

--PROCEDURE populate_ibcontact_data(p_batch_number     IN  NUMBER) ;      -- inserted only 1 record check query once

PROCEDURE populate_servicecall_data(p_batch_number   IN  NUMBER) ;

PROCEDURE populate_ugw_data(p_batch_number           IN  NUMBER) ;

PROCEDURE populate_crxsrv_serial(p_batch_number      IN  NUMBER) ;

PROCEDURE populate_meterreads_data(p_batch_number    IN  NUMBER) ;

PROCEDURE populate_lease_data(p_batch_number         IN  NUMBER) ;

PROCEDURE insert_ctr_terms_data ;

PROCEDURE populate_ctr_terms_data(p_batch_number     IN  NUMBER) ;

PROCEDURE serials_to_delete ;

PROCEDURE serials_to_update ;

PROCEDURE serials_to_load ;

PROCEDURE submit_program (x_errbuf                   OUT  VARCHAR2,
                          x_retcode                  OUT  VARCHAR2);

PROCEDURE process_batch (p_batch_number              IN  NUMBER) ;

PROCEDURE batch_ib_data ;

PROCEDURE synch_ids ;

PROCEDURE populate_service_status;
                          
PROCEDURE audit_log(p_start_time            IN VARCHAR2
                    ,p_end_time             IN VARCHAR2
                    ,p_sequence_id          IN NUMBER
                    ,p_argument_text        IN VARCHAR2
                    ,p_procedure_name       IN VARCHAR2);                          

END CANON_E404_SF_INSTALL_BASE_PKG;
/

create or replace PACKAGE BODY              CANON_E404_SF_INSTALL_BASE_PKG
AS
  G_PACKAGE_NAME          VARCHAR2(5000) := 'CANON_E404_SF_S21_IB_PKG';
  G_CREATED_BY            NUMBER := NULL;      --fnd_global.user_id;
  G_LAST_UPDATED_BY       NUMBER := NULL;      --fnd_global.user_id;
  G_LAST_UPDATE_LOGIN     NUMBER := NULL;      --fnd_global.login_id;
  G_LAST_RUN_DATE         DATE;
  G_LEASE_NO_OF_DAYS      VARCHAR2(50);
  G_ERRBUFF               VARCHAR2(5000) := 'SUCCESS';
  G_RETCODE               VARCHAR2(5000) := '0';
-- E_USER_EXCEPTION       EXCEPTION;
  G_ERROR_MESSAGE         VARCHAR2(20000);
-- G_RESET_DATA           VARCHAR2(60);
-- G_START_TIME           DATE;
-- G_END_TIME             DATE;
  G_REQUEST_ID            NUMBER := NULL;    --fnd_global.conc_request_id;
  G_RUN_MODE              VARCHAR2(25);
  G_NO_OF_BATCHES         NUMBER;

PROCEDURE submit_program (x_errbuf    OUT VARCHAR2,
                          x_retcode   OUT VARCHAR2)
AS
  l_program_start_date    VARCHAR2(60);
  l_program_date          DATE;
  l_start_time            VARCHAR2(250);
  l_end_time              VARCHAR2(250);
  l_argument_text         VARCHAR2(4000);
  l_procedure_name        VARCHAR2(30);
  l_test                  DATE;
  l_seq_currval           NUMBER;

  BEGIN
   -- dbms_output.put_line('p_batch_number :' || p_batch_number);
--raise_application_error(-20000,'TEST ERROR');
   -- x_errbuf             := 'SUCCESS';
   -- x_retcode            := '0';
      l_procedure_name     := 'submit_program';
      l_start_time         := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');
   -- p_no_of_batches      := 1;

      l_program_start_date := TO_CHAR (SYSDATE, 'DD-MON-RR HH24:MI');

   audit_log(l_start_time
             ,l_start_time
             ,canon_s21_ib_s.NEXTVAL      -- Sequence created
             ,l_argument_text
             ,l_procedure_name
             );

    BEGIN
      SELECT TO_DATE(profile_value,'YYYY-MM-DD')
        INTO g_last_run_date
        FROM canon_e001_profiles_tbl cp
             ,canon_e001_profile_values_tbl cpv
       WHERE 1 = 1
         AND cp.profile_name = 'CANON_E404_SF_IB_INT_RUN_DATE'
         AND cp.profile_id = cpv.profile_id
         AND SYSDATE BETWEEN NVL(cpv.start_date,SYSDATE-1) AND NVL(cpv.end_date,SYSDATE+1);

         dbms_output.put_line('S21 profile check ' || g_last_run_date);
		 canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','S21 profile check ' || g_last_run_date,NULL,NULL,NULL,NULL);

    EXCEPTION
      WHEN OTHERS
      THEN
        dbms_output.put_line('S21 profile exception ' || SQLERRM);
		canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'S21 profile exception ' || SQLERRM);
        g_last_run_date := NULL;
    END;

 --   g_run_mode := p_run_mode;

    l_program_date := SYSDATE ;

    populate_req_data;

    serials_to_delete;

    serials_to_update;

   -- dbms_output.put_line('batch number before serails to procedure' || p_batch_number);

    serials_to_load;

    dbms_output.put_line('Updating Ib table lease details to NULL values ' || l_program_start_date);
	canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Updating Ib table lease details to NULL values ' || l_program_start_date,NULL,NULL,NULL,NULL);

    UPDATE canon_e404_install_base_ob_tbl
       SET lease_number              = NULL
           ,leassor                  = NULL
           ,lease_expiration_date    = NULL
           ,lease_upgrade_amount     = NULL
           ,lease_term               = NULL
           ,lease_payment_amount     = NULL
           ,lease_source             = NULL
           ,lease_disposal_date      = NULL
           ,lease_inventory_date     = NULL
           ,last_update_date         = SYSDATE
           ,status_flag              = 'I'
     WHERE last_update_date > TO_DATE (l_program_start_date, 'DD-MON-RR HH24:MI')
       AND cbs_party_site_number <> psn_when_on_lease;

    COMMIT;

    -- Updating the last run date value in the code tables

    dbms_output.put_line('Updating run by date value in code table to : ' || l_program_start_date);
	canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Updating run by date value in code table to : ' || l_program_start_date,NULL,NULL,NULL,NULL);

   BEGIN
    UPDATE canon_e001_profile_values_tbl
       SET profile_value = TO_CHAR(l_program_date,'RRRRMMDDhh24miss')
     WHERE 1 = 1
       AND profile_id IN (SELECT profile_id
                            FROM canon_e001_profiles_tbl
                           WHERE 1 = 1
                             AND profile_name = 'CANON_E404_SF_IB_INT_RUN_DATE') ;
   EXCEPTION
     WHEN OTHERS
     THEN
       dbms_output.put_line('in update S21 profiles code table exception '|| SQLERRM);
	   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'in update S21 profiles code table exception '|| SQLERRM);
   END;

   COMMIT;

   dbms_output.put_line('Canon E404 Sales Force Install Base Last Run Date'' date set to :' || l_program_date);
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Canon E404 Sales Force Install Base Last Run Date'' date set to :' || l_program_date,NULL,NULL,NULL,NULL);

   l_end_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

   EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

    audit_log(l_start_time
              ,l_end_time
              ,l_seq_currval            -- Sequence current value
              ,NULL
              ,l_procedure_name
             );

EXCEPTION
  WHEN OTHERS
  THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    x_errbuf := SQLERRM;
    x_retcode := '2';
    dbms_output.put_line ('Exception occured in bacth procedure: ' || x_errbuf);
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END submit_program;


PROCEDURE audit_log(p_start_time         IN VARCHAR2
                    ,p_end_time          IN VARCHAR2
                    ,p_sequence_id       IN NUMBER
                    ,p_argument_text     IN VARCHAR2
                    ,p_procedure_name    IN VARCHAR2
                    )
AS
  PRAGMA AUTONOMOUS_TRANSACTION;

  l_dynsql             VARCHAR2(32767);
  l_procedure_name     VARCHAR2(30);
  l_audit_exists       VARCHAR2(1) := 'N';
  BEGIN
    l_procedure_name := 'audit_log' ;

    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);

    BEGIN
      SELECT 'Y'
        INTO l_audit_exists
        FROM canon_e404_s21_audit_log_tbl
       WHERE sequence_id = p_sequence_id;
    EXCEPTION
      WHEN OTHERS THEN
        l_audit_exists := 'N';
    END;

    IF l_audit_exists = 'N'
    THEN
      l_dynsql := ' INSERT INTO CANON_E404_S21_AUDIT_LOG_TBL a '
                    ||'   (a.sequence_id '
                    ||'   ,a.argument_text '
                    ||'   ,a.creation_date '
                    ||'   ) '
                  ||' VALUES('||p_sequence_id||' '
                    ||'   ,'''||p_argument_text||''' '
                    ||'   ,SYSDATE '
                    ||'   ) ';
    ELSE
      l_dynsql := ' UPDATE CANON_E404_S21_AUDIT_LOG_TBL a '
                     ||' SET '|| p_procedure_name||'_st = TO_DATE('''||p_start_time||''', ''MM/DD/YYYY HH:MI:SS AM''), '
                               || p_procedure_name||'_et = TO_DATE('''||p_end_time||''', ''MM/DD/YYYY HH:MI:SS AM''), '
                               || p_procedure_name||'_dur = '||ROUND((TO_DATE(p_end_time, 'MM/DD/YYYY HH:MI:SS AM') - TO_DATE(p_start_time, 'MM/DD/YYYY HH:MI:SS AM'))*24*60, 2)||' '
                     || ' WHERE a.sequence_id = '||p_sequence_id||' ';
    END IF;

 /*   INSERT INTO canon_e200_dynsql_tbl
    VALUES(fnd_global.conc_request_id
           ,l_dynsql
           ,SYSDATE
           );

    COMMIT;  */
dbms_output.put_line('audit_log dyn_sql: ' || l_dynsql);
canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','audit_log dyn_sql: ' || l_dynsql,NULL,NULL,NULL,NULL);
    EXECUTE IMMEDIATE l_dynsql;

    COMMIT;
 EXCEPTION
   WHEN OTHERS THEN
     ROLLBACK;
     g_errbuff := SQLERRM;
     g_retcode := '2';
     dbms_output.put_line('Exception occured in audit_log procedure: ' || g_errbuff);

 END audit_log;


PROCEDURE populate_req_data
AS
  l_start_time          VARCHAR2(50);
  l_end_time            VARCHAR2(50);
  l_procedure_name      VARCHAR2(30);
  l_seq_currval         NUMBER ;
  BEGIN
    l_procedure_name    := 'populate_req_data';

    l_start_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_ib_ser_to_upd_tbl' ;
    dbms_output.put_line('Truncated canon_e404_ib_ser_to_upd_tbl');
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Truncated canon_e404_ib_ser_to_upd_tbl',NULL,NULL,NULL,NULL);

    --Delete Update Processed - status_flag DUP which means are processed
    DELETE
      FROM canon_e404_ib_ser_to_del_tbl
     WHERE status_flag IN ('DUP') ;

    dbms_output.put_line('Deleted ' || sql%rowcount || ' DUP''s from  canon_e404_ib_ser_to_del_tbl');
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Deleted ' || sql%rowcount || ' DUP''s from  canon_e404_ib_ser_to_del_tbl',NULL,NULL,NULL,NULL);

    COMMIT;

    -- for Contract details
    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_s21_ctr_terms_tbl';
    dbms_output.put_line('Truncated canon_e404_s21_ctr_terms_tbl');

    insert_contract_data ;

    insert_ctr_terms_data ;

    dbms_output.put_line('Inserted ' || sql%ROWCOUNT || ' INTO canon_e404_s21_ctr_terms_tbl');
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted ' || sql%ROWCOUNT || ' INTO canon_e404_s21_ctr_terms_tbl',NULL,NULL,NULL,NULL);

    COMMIT;

    -- for Meter Reads details
    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_s21_meter_reads_tbl';
    dbms_output.put_line('Truncated canon_e404_s21_meter_reads_tbl');
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Truncated canon_e404_s21_meter_reads_tbl',NULL,NULL,NULL,NULL);

    insert_meter_reads_data ;

--    dbms_output.put_line('Inserted ' || sql%ROWCOUNT || ' INTO canon_e404_s21_meter_reads_tbl');

    COMMIT;

    l_end_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

    EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

    audit_log(l_start_time
              ,l_end_time
              ,l_seq_currval             -- Sequence current value
              ,NULL
              ,l_procedure_name
             );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line('Exception occured in populate_req_data procedure: ' || g_errbuff);
END populate_req_data;

PROCEDURE batch_ib_data
IS
  l_start_time               VARCHAR2(250);
  l_end_time                 VARCHAR2(250);
  l_no_of_recs_per_batch     NUMBER := 0;
  l_message                  VARCHAR2(4000);
  l_request_status           BOOLEAN;
  l_procedure_name           VARCHAR2 (30);
  l_seq_currval              NUMBER;
  l_batch_test               NUMBER;

  BEGIN
    l_start_time       := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');
    l_procedure_name   := 'BATCH_IB_DATA';
    BEGIN
      g_no_of_batches      := 1;
      -- Changing the no_of_batches to 1 as we are having deadlock issues with more than 1 batch.
      -- Even though serials are independent in each batch we were having deadloack issues with more than 1 batch.
      SELECT COUNT(*)/NVL(g_no_of_batches, 1)
        INTO l_no_of_recs_per_batch
        FROM canon_e404_ib_ser_to_upd_tbl;
    EXCEPTION
      WHEN OTHERS
      THEN
        l_no_of_recs_per_batch := 0;
    END;

      dbms_output.put_line(l_procedure_name || ':g_no_of_batches=' || g_no_of_batches);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',l_procedure_name || ':g_no_of_batches=' || g_no_of_batches,NULL,NULL,NULL,NULL);

      dbms_output.put_line(l_procedure_name || ':l_no_of_recs_per_batch=' || l_no_of_recs_per_batch);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',l_procedure_name || ':l_no_of_recs_per_batch=' || l_no_of_recs_per_batch,NULL,NULL,NULL,NULL);

      FOR i IN 1..g_no_of_batches
      LOOP
        --dbms_output.put_line('test1 inside loop: ');
        UPDATE canon_e404_ib_ser_to_upd_tbl
           SET batch_number = i
         WHERE batch_number IS NULL
           AND rownum <= l_no_of_recs_per_batch;
        COMMIT;

        --dbms_output.put_line('after update in loop ');

      END LOOP;

      UPDATE canon_e404_ib_ser_to_upd_tbl
         SET batch_number = NVL(g_no_of_batches, 1)
       WHERE batch_number IS NULL;

      COMMIT;

      BEGIN
        --dbms_output.put_line('test1 batch number: '||l_batch_test);
        SELECT batch_number
          INTO l_batch_test
          FROM canon_e404_ib_ser_to_upd_tbl
         WHERE 1 = 1
           AND ROWNUM <= 1 ;

         --dbms_output.put_line('test2 batch number after: '||l_batch_test);

      EXCEPTION
        WHEN OTHERS
        THEN
          --dbms_output.put_line('test3 batch exception: '||l_batch_test);
          l_batch_test := NULL ;
      END;

      FOR batch_rec IN(SELECT DISTINCT
                              batch_number
                         FROM canon_e404_ib_ser_to_upd_tbl
                      )
      LOOP
        dbms_output.put_line('Batch Number: '||batch_rec.batch_number);
        process_batch(batch_rec.batch_number) ;
        COMMIT;
      END LOOP;

    /*  FOR check_batch_complete_rec IN
         (SELECT request_id
            FROM fnd_conc_req_summary_v
           WHERE parent_request_id = g_request_id
         )
      LOOP

         l_request_status := fnd_concurrent.wait_for_request(request_id      => check_batch_complete_rec.request_id
                                                            ,interval        => 30
                                                            ,max_wait        => (24*60*60)
                                                            ,phase           => l_phase
                                                            ,status          => l_status
                                                            ,dev_phase       => l_dev_phase
                                                            ,dev_status      => l_dev_status
                                                            ,message         => l_message
                                                            );

      END LOOP;  */

      l_end_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

      EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

      audit_log(l_start_time
                ,l_end_time
                ,l_seq_currval           -- Sequence current value
                ,NULL
                ,l_procedure_name
               );
EXCEPTION
  WHEN OTHERS
  THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    --g_errbuff := SQLERRM;
    --g_retcode := '2';
   --  x_errbuf := SQLERRM;
   --  x_retcode := '2';
   -- dbms_output.put_line ('Exception occured in bacth procedure: ' || g_errbuff);
END batch_ib_data;

PROCEDURE process_batch(p_batch_number IN NUMBER
                       )
IS
  l_start_time          VARCHAR2(250);
  l_end_time            VARCHAR2(250);
  l_request_id          NUMBER ;
  l_argument_text       VARCHAR2(4000);
  l_procedure_name      VARCHAR2(30);
  l_seq_currval         NUMBER;
  BEGIN
    l_Start_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');
    l_procedure_name := 'process_batch';
  --  l_request_id := fnd_global.conc_request_id;
    dbms_output.put_line(l_procedure_name || ':p_batch_number=' || p_batch_number);
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',l_procedure_name || ':p_batch_number=' || p_batch_number,NULL,NULL,NULL,NULL);

     populate_contract_data(p_batch_number);
    -- populate_ibcontact_data(p_batch_number); its not their in Oracle
     populate_servicecall_data(p_batch_number);
     populate_ugw_data(p_batch_number);
     populate_crxsrv_serial(p_batch_number);
     populate_meterreads_data(p_batch_number);
     populate_lease_data(p_batch_number);
     populate_ctr_terms_data(p_batch_number);

     l_end_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

     EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

     audit_log(l_start_time
               ,l_end_time
               ,l_seq_currval           -- Sequence current value
               ,NULL
               ,l_procedure_name
               );

EXCEPTION
  WHEN OTHERS
  THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Exception occured in process bacth procedure: ' || g_errbuff);
END process_batch;


PROCEDURE serials_to_load
IS
  l_procedure_name        VARCHAR2(30);
  l_start_time            VARCHAR2(250);
  l_end_time              VARCHAR2(250);
  l_seq_currval           NUMBER;
  BEGIN
    l_start_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');
    l_procedure_name := 'serials_to_load';

   -- dbms_output.put_line ( 'Procedure Name '|| l_procedure_name || ':p_batch_number=' || p_batch_number);

    populate_ib_data;

    batch_ib_data ;

    l_end_time := to_char(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');
    
    populate_service_status;

    EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

    audit_log(l_start_time
              ,l_end_time
              ,l_seq_currval           -- Sequence current value
              ,NULL
              ,l_procedure_name
             );

EXCEPTION
  WHEN OTHERS
  THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
END serials_to_load;


PROCEDURE populate_ib_data
IS
  l_start_time      VARCHAR2(50);
  l_end_time        VARCHAR2(50);
  l_procedure_name  VARCHAR2(30);
  l_seq_currval     NUMBER;

  BEGIN
    l_procedure_name  := 'populate_ib_data';
    l_start_time      := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting data into ib table');

      INSERT
        INTO canon_e404_install_base_ob_tbl (cbs_party_site_number
                                             ,customer_class
                                             ,configuration_id
                                             ,name
                                             ,serial_number
                                             ,instance_id
                                             ,external_reference_id
                                             ,item_code
                                             ,item_description
                                             ,install_date
                                             ,active_end_date
                                             ,ib_status
                                             ,instance_usage_code
                                             ,model_name
                                             ,pd364_segment_name
                                             ,control1
                                             ,control2
                                             ,control3
                                             ,control4
                                             ,sold_by
                                             ,serviced_by
                                             ,status_flag
                                             ,status_message
                                             ,creation_date
                                             ,created_by
                                             ,last_update_date
                                             ,last_updated_by
                                             )
                                      SELECT DISTINCT smm.ind_cur_loc_num cbs_party_site_number
                                             ,dacl.ds_acct_cls_nm  customer_class
                                             ,smm.svc_config_mstr_pk configuration_id
                                             ,smm.ser_num  name
                                             ,smm.ser_num serial_number
                                             ,smm.svc_mach_mstr_pk instance_id
                                             ,smm.cust_mach_ctrl_num external_reference_id
                                             ,smm.mdse_cd item_code
                                             ,dmi.mdse_desc_short_txt item_description
                                             ,to_date(smm.istl_dt,'YYYY-MM-DD') install_date
                                             ,to_date(smm.eff_thru_dt,'YYYY-MM-DD') active_end_date
                                             ,smms.svc_mach_mstr_sts_nm ib_status
                                             ,smus.svc_mach_usg_sts_nm  instance_usage_code
                                             ,dm.mdl_desc_txt     model_name
                                             ,ss.svc_seg_cd pd364_segment_name
                                             ,smm.ctrl_fld_txt_01 control1
                                             ,smm.ctrl_fld_txt_02 control2
                                             ,smm.ctrl_fld_txt_03 control3
                                             ,smm.ctrl_fld_txt_04 control4
                                             ,smm.sld_by_line_biz_tp_cd sold_by
                                             ,smm.svc_by_line_biz_tp_cd serviced_by
                                             ,'I'
                                             ,'Record is Inserted'
                                             ,SYSDATE
                                             ,NULL
                                             ,SYSDATE
                                             ,NULL
--                                        FROM ds_acct_cust dac
                                         FROM sell_to_cust dac -- DB Change
                                             ,svc_mach_mstr smm
--                                             ,ds_mdse_info dmi
                                             ,mdse dmi -- DB Changes
                                             ,svc_mach_mstr_sts  smms
                                             ,svc_config_mstr scm
                                             ,ds_mdl  dm
                                             ,svc_seg ss
                                             ,ds_acct_cls dacl
                                             ,svc_mach_usg_sts smus
                                             ,canon_e404_ib_ser_to_upd_tbl e404
                                             ,pty_loc_wrk plw
                                             ,acct_loc al
                                       WHERE 1=1
                                         AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                                              smm.sld_by_line_biz_tp_cd = 'ESS')
                                         AND smm.svc_mach_mstr_pk = e404.instance_id
                                         AND plw.loc_num = smm.ind_cur_loc_num
                                         AND plw.loc_num = al.loc_num
                                         AND al.ds_acct_num = dac.sell_to_cust_cd -- DB Change
                                         AND dac.ds_acct_cls_cd = dacl.ds_acct_cls_cd
                                         AND smm.mdse_cd = dmi.mdse_cd(+)
                                         AND smm.svc_mach_mstr_pk = scm.svc_mach_mstr_pk(+)
                                         AND scm.mdl_id = dm.mdl_id(+)
                                         AND dm.svc_seg_cd = ss.svc_seg_cd(+)
                                         AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                                         AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                                                    FROM CANON_E404_INSTANCE_STATUS_V )
                                         AND dac.rgtn_sts_cd NOT IN ('P99')
                                         AND dac.ezcancelflag = 0
                                         AND smm.ezcancelflag = 0
                                         AND dmi.ezcancelflag(+) = 0
                                         AND smms.ezcancelflag = 0
                                         AND scm.ezcancelflag(+) = 0
                                         AND dm.ezcancelflag(+) = 0
                                         AND ss.ezcancelflag(+) = 0
                                         AND dacl.ezcancelflag = 0
                                         AND smm.svc_mach_usg_sts_cd = smus.svc_mach_usg_sts_cd
                                         AND UPPER(smus.svc_mach_usg_sts_nm) = UPPER('At Customer')
                                         AND dac.glbl_cmpy_cd = 'ADB'
                                         AND smm.glbl_cmpy_cd = 'ADB'
                                         AND dmi.glbl_cmpy_cd(+) = 'ADB'
                                         AND smms.glbl_cmpy_cd = 'ADB'
                                         AND scm.glbl_cmpy_cd(+) = 'ADB'
                                         AND dm.glbl_cmpy_cd(+) = 'ADB'
                                         AND ss.glbl_cmpy_cd(+) = 'ADB'
                                         AND dacl.glbl_cmpy_cd = 'ADB';

    dbms_output.put_line ('Records inserted into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Records inserted into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
    COMMIT;

    l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

    audit_log(l_start_time
              ,l_end_time
              ,l_seq_currval            -- Sequence current value
              ,NULL
              ,l_procedure_name
              );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
END populate_ib_data;

PROCEDURE insert_contract_data
IS
  l_start_time         VARCHAR2(50);
  l_end_time           VARCHAR2(50);
  l_procedure_name     VARCHAR2(30);
  l_seq_currval        NUMBER;
  BEGIN
    l_procedure_name  := 'insert_contract_data' ;
    l_start_time      := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting contract details into contracts table');

 /*   INSERT
      INTO canon_e404_s21_ctr_terms_tbl (svc_mach_mstr_pk
                                         ,serial_number
                                         ,creation_date
                                          )
                                           SELECT instance_id
                                                  ,serial_number
                                                  ,SYSDATE
                                             FROM canon_e404_install_base_ob_tbl
                                            ;
    dbms_output.put_line ('Instance Id column inserted into canon_e404_s21_ctr_terms_tbl from S21 tables: ' || SQL%ROWCOUNT);
    COMMIT;   */


    INSERT
      INTO canon_e404_s21_ctr_terms_tbl (svc_mach_mstr_pk
                                         ,serial_number
                                         ,contract_number
                                         ,contract_header_id
                                         ,contract_expiration_date
                                         ,base_price
                                         ,rental_type
                                         ,creation_date
                                        )
                                 (SELECT DISTINCT
                                         smm.svc_mach_mstr_pk
                                         ,smm.ser_num
                                         ,FIRST_VALUE(dc.ds_contr_num) OVER(PARTITION BY  smm.svc_mach_mstr_pk ORDER BY dc.ds_contr_last_upd_dt DESC NULLS FIRST ) contract_number
                                         ,FIRST_VALUE(dc.ds_contr_pk) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dc.ds_contr_last_upd_dt DESC NULLS FIRST ) contract_header_id
                                         ,FIRST_VALUE(to_date(dc.contr_vrsn_eff_thru_dt,'YYYY-MM-DD')) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dc.ds_contr_last_upd_dt DESC NULLS FIRST ) contract_expiration_date
                                         ,FIRST_VALUE(dcd.base_prc_deal_amt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dc.ds_contr_last_upd_dt DESC NULLS FIRST ) base_price            -- check this
                                         ,CASE WHEN UPPER(dct.ds_contr_tp_nm) LIKE '%RENTAL%'
                                               THEN
                                                 'Regular Rental'
                                               ELSE
                                                 NULL
                                               END rental_type
                                         ,SYSDATE
                                    FROM svc_mach_mstr smm
                                         ,ds_contr_dtl dcd
                                         ,ds_contr dc
                                         ,ds_contr_tp dct
                                         ,ds_contr_sts dcs
                                         ,ds_contr_dtl_sts dcds
                                   WHERE 1=1
                                     AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                                     AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
                                     AND dc.ds_contr_tp_cd = dct.ds_contr_tp_cd(+)
                                     AND smm.ezcancelflag = '0'
                                     AND dcd.ezcancelflag(+) = '0'
                                     AND dc.ezcancelflag (+)= '0'
                                     AND dct.ezcancelflag(+) = '0'
                                     AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                                         smm.sld_by_line_biz_tp_cd = 'ESS' )
                                     AND dc.ds_contr_tp_cd(+) = 'S'
                                     AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd    -- check this outer join for this also
                                     AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                                   FROM CANON_E404_INACTIVE_CONTRACT_V)
                                     AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
                                     AND dcs.ds_contr_sts_nm IN( SELECT status   -- check this outer join for this also
                                                                   FROM CANON_E404_CONTRACT_HEADER_V)
                                     AND smm.glbl_cmpy_cd = 'ADB'
                                     AND dcd.glbl_cmpy_cd(+) = 'ADB'
                                     AND dc.glbl_cmpy_cd(+) = 'ADB'
                                     AND dct.glbl_cmpy_cd(+) = 'ADB'
                                   );
                            COMMIT;

  MERGE INTO canon_e404_s21_ctr_terms_tbl e404
       USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                 --  ,cxcp.xs_mtr_copy_qty base_volume
                     ,FIRST_VALUE(cxcp.xs_mtr_copy_qty) OVER(PARTITION BY  smm.svc_mach_mstr_pk ORDER BY dcd.base_inv_up_to_dt DESC NULLS FIRST ) base_volume   -- check the date column
                FROM contr_xs_copy cxcp
                     ,ds_contr_bllg_mtr dcbm
                     ,mtr_lb mtlb
                     ,ds_contr_dtl dcd
                     ,ds_contr_dtl_sts dcds
                     ,svc_mach_mstr smm
               WHERE 1=1
                 AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                 AND dcbm.ds_contr_dtl_pk = dcd.ds_contr_dtl_pk(+)
                 AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                 AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                      smm.sld_by_line_biz_tp_cd = 'ESS')
                 AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                               FROM CANON_E404_INACTIVE_CONTRACT_V)
                 AND cxcp.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                 AND cxcp.ds_contr_bllg_mtr_pk(+) = dcbm.ds_contr_bllg_mtr_pk
                 AND cxcp.xs_mtr_first_flg(+) = 'Y'
                 AND mtlb.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                 AND mtlb.mtr_lb_cd(+) = dcbm.bllg_mtr_lb_cd
                 AND mtlb.bw_mtr_flg(+) = 'Y'
                 AND mtlb.ezcancelflag(+) = '0'
                 AND dcbm.ezcancelflag = '0'
                 AND cxcp.ezcancelflag(+) = '0'
                 AND cxcp.glbl_cmpy_cd(+) = 'ADB'
                 AND dcbm.glbl_cmpy_cd = 'ADB'
                 AND dcd.ezcancelflag(+) = '0'
                 AND dcd.glbl_cmpy_cd(+) = 'ADB'
                 AND smm.glbl_cmpy_cd = 'ADB'
                 AND smm.ezcancelflag = '0'
             )b
          ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
        WHEN MATCHED THEN
      UPDATE
         SET base_volume = b.base_volume
           ;
   COMMIT;

   MERGE INTO canon_e404_s21_ctr_terms_tbl e404
        USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                  --  ,cxcp.xs_mtr_amt_rate overage
                      ,FIRST_VALUE(cxcp.xs_mtr_amt_rate) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dcd.base_inv_up_to_dt DESC NULLS FIRST ) overage  -- check the date column
                 FROM contr_xs_copy cxcp
                      ,ds_contr_bllg_mtr dcbm
                      ,mtr_lb mtlb
                      ,ds_contr_dtl dcd
                      ,ds_contr_dtl_sts dcds
                      ,svc_mach_mstr smm
                WHERE 1=1
                  AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                  AND dcbm.ds_contr_dtl_pk = dcd.ds_contr_dtl_pk(+)
                  AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                  AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                FROM CANON_E404_INACTIVE_CONTRACT_V)
                  AND cxcp.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                  AND cxcp.ds_contr_bllg_mtr_pk(+) = dcbm.ds_contr_bllg_mtr_pk
                  AND cxcp.xs_mtr_first_flg(+) = 'Y'
                  AND mtlb.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                  AND mtlb.mtr_lb_cd(+) = dcbm.bllg_mtr_lb_cd
                  AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS')
                  AND mtlb.bw_mtr_flg(+) = 'Y'
                  AND smm.glbl_cmpy_cd = 'ADB'
                  AND smm.ezcancelflag = '0'
                  AND dcbm.glbl_cmpy_cd = 'ADB'
                  AND mtlb.ezcancelflag(+) = '0'
                  AND dcbm.ezcancelflag = '0'
                  AND cxcp.ezcancelflag(+) = '0'
                )b
           ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
         WHEN MATCHED THEN
       UPDATE
          SET overage = b.overage
              ;
        COMMIT;

   MERGE INTO canon_e404_s21_ctr_terms_tbl e404
        USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                  --  ,cxcp.xs_mtr_copy_qty base_volume_clr
                      ,FIRST_VALUE(cxcp.xs_mtr_copy_qty) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dcd.base_inv_up_to_dt DESC NULLS FIRST ) base_volume_clr
                 FROM contr_xs_copy cxcp
                      ,ds_contr_bllg_mtr dcbm
                      ,mtr_lb mtlb
                      ,ds_contr_dtl dcd
                      ,ds_contr_dtl_sts dcds
                      ,svc_mach_mstr smm
                WHERE 1=1
                  AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                  AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                  AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                FROM CANON_E404_INACTIVE_CONTRACT_V)
                  AND dcbm.ds_contr_dtl_pk = dcd.ds_contr_dtl_pk(+)
                  AND cxcp.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                  AND cxcp.ds_contr_bllg_mtr_pk(+) = dcbm.ds_contr_bllg_mtr_pk
                  AND cxcp.xs_mtr_first_flg(+) = 'Y'
                  AND mtlb.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                  AND mtlb.mtr_lb_cd(+) = dcbm.bllg_mtr_lb_cd
                  AND mtlb.color_mtr_flg(+) = 'Y'
                  AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                  AND dcbm.glbl_cmpy_cd = 'ADB'
                  AND smm.glbl_cmpy_cd = 'ADB'
                  AND smm.ezcancelflag = '0'
                  AND dcbm.ezcancelflag = '0'
                  AND mtlb.ezcancelflag(+) = '0'
                  AND cxcp.ezcancelflag(+) = '0'
               )b
           ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
         WHEN MATCHED THEN
       UPDATE
          SET base_volume_clr = b.base_volume_clr
           ;
    COMMIT ;

   MERGE INTO canon_e404_s21_ctr_terms_tbl e404
        USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                  --  ,cxcp.xs_mtr_amt_rate overage_clr
                      ,FIRST_VALUE(cxcp.xs_mtr_amt_rate) OVER(PARTITION BY  smm.svc_mach_mstr_pk ORDER BY dcd.base_inv_up_to_dt DESC NULLS FIRST ) overage_clr
                 FROM contr_xs_copy cxcp
                      ,ds_contr_bllg_mtr dcbm
                      ,mtr_lb mtlb
                      ,ds_contr_dtl dcd
                      ,ds_contr_dtl_sts dcds
                      ,svc_mach_mstr smm
                WHERE 1=1
                  AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                  AND dcbm.ds_contr_dtl_pk = dcd.ds_contr_dtl_pk(+)
                  AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                  AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                FROM CANON_E404_INACTIVE_CONTRACT_V)
                  AND cxcp.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                  AND cxcp.ds_contr_bllg_mtr_pk(+) = dcbm.ds_contr_bllg_mtr_pk
                  AND cxcp.xs_mtr_first_flg(+) = 'Y'
                  AND mtlb.glbl_cmpy_cd(+) = dcbm.glbl_cmpy_cd
                  AND mtlb.mtr_lb_cd(+) = dcbm.bllg_mtr_lb_cd
                  AND mtlb.color_mtr_flg(+) = 'Y'
                  AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                  AND dcbm.glbl_cmpy_cd = 'ADB'
                  AND smm.glbl_cmpy_cd = 'ADB'
                  AND mtlb.ezcancelflag(+) = '0'
                  AND dcbm.ezcancelflag = '0'
                  AND cxcp.ezcancelflag(+) = '0'
               )b
           ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
         WHEN MATCHED THEN
       UPDATE
          SET overage_clr = b.overage_clr
             ;
       COMMIT;

   MERGE INTO canon_e404_s21_ctr_terms_tbl e404
        USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                      ,NULL  aggregate_contract_number      --check this
                   -- ,h.base_bllg_cycle_cd base_cycle
                      ,FIRST_VALUE(dcd.base_bllg_cycle_cd) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dc.ds_contr_last_upd_dt DESC NULLS FIRST ) base_cycle            -- check this
                   -- ,l.ds_contr_catg_nm contract_indicator
                      ,FIRST_VALUE(dcc.ds_contr_catg_nm) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dc.ds_contr_last_upd_dt DESC NULLS FIRST ) contract_indicator      -- check this
                 FROM svc_mach_mstr smm
                      ,ds_contr_dtl dcd
                      ,ds_contr_dtl_sts dcds
                      ,ds_contr dc
                      ,ds_contr_sts dcs
                      ,ds_contr_catg dcc
                WHERE 1 = 1
                  AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                  AND dc.ds_contr_tp_cd(+) = 'S'
                  AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
                  AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd(+)
                  AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                FROM CANON_E404_INACTIVE_CONTRACT_V)
                  AND dc.ds_contr_sts_cd(+) = dcs.ds_contr_sts_cd
                  AND dcs.ds_contr_sts_nm IN( SELECT status
                                                FROM CANON_E404_CONTRACT_HEADER_V)
                  AND dc.ds_contr_catg_cd(+) = dcc.ds_contr_catg_cd
                  AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                  AND smm.glbl_cmpy_cd = 'ADB'
                  AND dcd.glbl_cmpy_cd(+) = 'ADB'
                  AND dc.glbl_cmpy_cd(+) = 'ADB'
                  AND smm.ezcancelflag = 0
                  AND dcd.ezcancelflag(+) = 0
                  AND dc.ezcancelflag(+) = 0
               )b
           ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
         WHEN MATCHED THEN
       UPDATE
          SET aggregate_contract_number = b.aggregate_contract_number
              ,base_cycle = b.base_cycle
              ,contract_indicator = b.contract_indicator
            ;
      
      dbms_output.put_line ('Contracts information inserted into canon_e404_s21_ctr_terms_tbl from S21 tables: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Contracts information inserted into canon_e404_s21_ctr_terms_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;
      
    l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

    audit_log(l_start_time
              ,l_end_time
              ,l_seq_currval         -- Sequence current value
              ,NULL
              ,l_procedure_name
              );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
END insert_contract_data;

PROCEDURE populate_contract_data( p_batch_number IN NUMBER)
IS
  l_start_time         VARCHAR2(50);
  l_end_time           VARCHAR2(50);
  l_procedure_name     VARCHAR2(30);
  l_seq_currval        NUMBER;
  BEGIN
    l_procedure_name  := 'populate_contract_data' ;
    l_start_time      := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting contract terms and conditions data into ib table');
	

    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT distinct svc_mach_mstr_pk
                       ,contract_number
                       ,contract_header_id
                       ,contract_expiration_date
                       ,base_price
                       ,rental_type
                       ,base_volume
                       ,overage
                       ,base_volume_clr
                       ,overage_clr
                       ,aggregate_contract_number
                       ,base_cycle
                       ,contract_indicator
                  FROM canon_e404_s21_ctr_terms_tbl e404_ctr
                       ,canon_e404_ib_ser_to_upd_tbl e404_upd
                 WHERE 1 = 1
                   AND e404_ctr.svc_mach_mstr_pk = e404_upd.instance_id
                   AND e404_upd.batch_number = p_batch_number
                 )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET contract_number = b.contract_number
               ,contract_header_id = b.contract_header_id
               ,contract_expiration_date = b.contract_expiration_date
               ,base_price = b.base_price
               ,rental_type = b.rental_type
               ,base_volume = b.base_volume
               ,overage = b.overage
               ,base_volume_clr = b.base_volume_clr
               ,overage_clr = b.overage_clr
               ,aggregate_contract_number = b.aggregate_contract_number
               ,base_cycle = b.base_cycle
               ,contract_indicator = b.contract_indicator
             ;
      
      dbms_output.put_line ('Contract details merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Contract details merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

      l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

      EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

      audit_log(l_start_time
                ,l_end_time
                ,l_seq_currval         -- Sequence current value
                ,NULL
                ,l_procedure_name
              );

EXCEPTION
  WHEN OTHERS
  THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Contract terms and conditions details are not merged into ib table exception occured: ' || g_errbuff);
END populate_contract_data;

/* ****commented as it is not in ESS Oracle
PROCEDURE populate_ibcontact_data( p_batch_number IN NUMBER)  -- check this outer joins with Radhika as query took long time
IS
  l_start_time         VARCHAR2(50);
  l_end_time           VARCHAR2(50);
  l_procedure_name     VARCHAR2(30);
  l_seq_currval        NUMBER;
  BEGIN
    l_procedure_name  := 'populate_ibcontact_data' ;
    l_start_time      := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting ib contact details into ib table');   -- only 1 record is merged check it

    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT distinct smm.svc_mach_mstr_pk
                       ,CASE WHEN smcp.svc_ctac_tp_cd = '30'
                             THEN ctpn.ctac_psn_first_nm
                                  ||' '|| CASE WHEN ctpn.ctac_psn_mid_nm IS NOT NULL THEN ctpn.ctac_psn_mid_nm ELSE NULL END
                                  ||' '|| CASE WHEN ctpn.ctac_psn_last_nm IS NOT NULL THEN ctpn.ctac_psn_last_nm ELSE NULL END
                             ELSE
                               NULL
                             END  ib_contact_name
                       ,CASE WHEN smcp.svc_ctac_tp_cd = '30'
                             THEN dcpt.ds_ctac_pnt_val_txt
                             ELSE
                               NULL
                             END  ib_contact_phone
                      -- ,CASE WHEN smcp.svc_ctac_tp_cd = '20'
                    --          THEN ctpn.ctac_psn_fax_num
--                             THEN ctpn.ctac_psn_first_nm
--                                  ||' '||CASE WHEN ctpn.ctac_psn_mid_nm IS NOT NULL THEN ctpn.ctac_psn_mid_nm ELSE NULL END
--                                  ||' '|| CASE WHEN ctpn.ctac_psn_last_nm IS NOT NULL THEN ctpn.ctac_psn_last_nm ELSE NULL END
                      --       ELSE
                       --        NULL
                        --     END  fax_contact
                  FROM ctac_psn ctpn
                       ,ds_ctac_pnt dcpt
                       ,svc_mach_ctac_psn smcp
                       ,svc_mach_mstr smm
                       ,svc_mach_mstr_sts smms
                       ,canon_e404_ib_ser_to_upd_tbl e404_upd
                       ,ds_ctac_psn_reln dcpr -- DB Changes
                 WHERE 1=1
                   AND dcpt.ds_ctac_pnt_tp_cd IN ('01','02','03')
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V )
                   AND smcp.ds_ctac_pnt_pk = dcpt.ds_ctac_pnt_pk
                   AND smcp.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
				   AND smcp.svc_ctac_tp_cd = '30' -- Phone
                   AND smm.svc_mach_mstr_pk = e404_upd.instance_id
                   AND e404_upd.batch_number = p_batch_number
                   AND dcpt.ctac_psn_pk = ctpn.ctac_psn_pk
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND ctpn.ctac_psn_pk = dcpr.ctac_psn_pk
                   AND dcpr.ctac_tp_cd = 'DELIV_INSTALL'
                   AND smm.ezcancelflag = '0'
                   AND ctpn.ezcancelflag = '0'
                   AND dcpt.ezcancelflag = '0'
                   AND smcp.ezcancelflag = '0'
                   AND dcpr.ezcancelflag = '0'
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND dcpt.glbl_cmpy_cd = 'ADB'
                   AND ctpn.glbl_cmpy_cd = 'ADB'
                   AND smcp.glbl_cmpy_cd = 'ADB'
                   AND dcpr.glbl_cmpy_cd = 'ADB'
                )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET ib_contact_name = b.ib_contact_name
               ,ib_contact_phone = b.ib_contact_phone
              -- ,fax_contact = b.fax_contact
             ;
       dbms_output.put_line ('Phone Contacts information merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	   
	   MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT distinct smm.svc_mach_mstr_pk
                      -- ,CASE WHEN smcp.svc_ctac_tp_cd = '30'
                      --       THEN ctpn.ctac_psn_first_nm
                       --           ||' '|| CASE WHEN ctpn.ctac_psn_mid_nm IS NOT NULL THEN ctpn.ctac_psn_mid_nm ELSE NULL END
                      --            ||' '|| CASE WHEN ctpn.ctac_psn_last_nm IS NOT NULL THEN ctpn.ctac_psn_last_nm ELSE NULL END
                      --       ELSE
                      --         NULL
                      --       END  ib_contact_name
                     --  ,CASE WHEN smcp.svc_ctac_tp_cd = '30'
                     --        THEN dcpt.ds_ctac_pnt_val_txt
                     --        ELSE
                     --          NULL
                     --        END  ib_contact_phone
                       ,CASE WHEN smcp.svc_ctac_tp_cd = '20'
                              THEN ctpn.ctac_psn_fax_num
--                             THEN ctpn.ctac_psn_first_nm
--                                  ||' '||CASE WHEN ctpn.ctac_psn_mid_nm IS NOT NULL THEN ctpn.ctac_psn_mid_nm ELSE NULL END
--                                  ||' '|| CASE WHEN ctpn.ctac_psn_last_nm IS NOT NULL THEN ctpn.ctac_psn_last_nm ELSE NULL END
                             ELSE
                               NULL
                             END  fax_contact
                  FROM ctac_psn ctpn
                       ,ds_ctac_pnt dcpt
                       ,svc_mach_ctac_psn smcp
                       ,svc_mach_mstr smm
                       ,svc_mach_mstr_sts smms
                       ,canon_e404_ib_ser_to_upd_tbl e404_upd
                       ,ds_ctac_psn_reln dcpr -- DB Changes
                 WHERE 1=1
                   AND dcpt.ds_ctac_pnt_tp_cd IN ('01','02','03')
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V )
                   AND smcp.ds_ctac_pnt_pk = dcpt.ds_ctac_pnt_pk
                   AND smcp.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
				   AND smcp.svc_ctac_tp_cd = '20' --Fax
                   AND smm.svc_mach_mstr_pk = e404_upd.instance_id
                   AND e404_upd.batch_number = p_batch_number
                   AND dcpt.ctac_psn_pk = ctpn.ctac_psn_pk
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND ctpn.ctac_psn_pk = dcpr.ctac_psn_pk
                   AND dcpr.ctac_tp_cd = 'DELIV_INSTALL'
                   AND smm.ezcancelflag = '0'
                   AND ctpn.ezcancelflag = '0'
                   AND dcpt.ezcancelflag = '0'
                   AND smcp.ezcancelflag = '0'
                   AND dcpr.ezcancelflag = '0'
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND dcpt.glbl_cmpy_cd = 'ADB'
                   AND ctpn.glbl_cmpy_cd = 'ADB'
                   AND smcp.glbl_cmpy_cd = 'ADB'
                   AND dcpr.glbl_cmpy_cd = 'ADB'
                )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           --SET ib_contact_name = b.ib_contact_name
             --  ,ib_contact_phone = b.ib_contact_phone
               SET fax_contact = b.fax_contact
             ;
       dbms_output.put_line ('Fax Contacts information merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
       COMMIT;

       l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

       EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

       audit_log(l_start_time
                 ,l_end_time
                 ,l_seq_currval           -- Sequence current value
                 ,NULL
                 ,l_procedure_name
                );

 EXCEPTION
   WHEN OTHERS THEN
     canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
     g_errbuff := SQLERRM;
     g_retcode := '2';
     dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
 END populate_ibcontact_data;
*****/

PROCEDURE populate_servicecall_data( p_batch_number IN NUMBER)
IS
  l_start_time         VARCHAR2(50);
  l_end_time           VARCHAR2(50);
  l_procedure_name     VARCHAR2(30);
  l_seq_currval        NUMBER;
  BEGIN
    l_procedure_name  := 'populate_servicecall_data' ;
    l_start_time      := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting ib service call details into ib table');

    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT distinct smm.svc_mach_mstr_pk
                    -- ,smm.last_svc_call_dt last_service_request_date
                       ,TO_DATE(smm.last_svc_call_dt,'YYYY-MM-DD') last_service_request_date
                       ,svc_call.svc_calls_last_30_days
                       ,smm.last_svc_call_mtr_cnt last_service_read
                   --  ,smm.last_svc_call_visit_dt last_service_read_date
                       ,TO_DATE(smm.last_svc_call_visit_dt,'YYYY-MM-DD') last_service_read_date
                  FROM svc_mach_mstr smm
                       ,svc_mach_mstr_sts smms
                       ,canon_e404_ib_ser_to_upd_tbl e404_upd
                       ,(SELECT COUNT(1) svc_calls_last_30_days
                                ,svc_mach_mstr_pk
                           FROM fsr d
                          WHERE d.ezcancelflag = '0'
                            AND d.ezintime >= TO_CHAR(TRUNC(SYSDATE - 30), 'yyyymmddhh24miss') || '000'
                          GROUP BY svc_mach_mstr_pk) svc_call
                 WHERE 1=1
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V )
                   AND smm.svc_mach_mstr_pk = e404_upd.instance_id
                   AND svc_call.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                   AND e404_upd.batch_number = p_batch_number
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = 0
                  )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET last_service_request_date = b.last_service_request_date
               ,svc_calls_last_30_days = b.svc_calls_last_30_days
               ,last_service_read = b.last_service_read
               ,last_service_read_date = b.last_service_read_date
              ;
        
       dbms_output.put_line ('Service call data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Service call data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
	   
										
      COMMIT;
       l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

       EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

       audit_log(l_start_time
                 ,l_end_time
                 ,l_seq_currval            -- Sequence current value
                 ,NULL
                 ,l_procedure_name
                );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Records are not inserted into table exception occured: ' || SQLERRM);
END populate_servicecall_data;

PROCEDURE populate_ugw_data(p_batch_number IN NUMBER)
IS
  l_start_time         VARCHAR2(50);
  l_end_time           VARCHAR2(50);
  l_procedure_name     VARCHAR2(30);
  l_seq_currval        NUMBER;
  BEGIN
    l_procedure_name  := 'populate_ugw_data' ;
    l_start_time        := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting UGW data details into ib table');   -- only 1 record merged check once again

    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT distinct smm.svc_mach_mstr_pk
                       ,DMI.IWR_ENBL_FLG IMAGEWARE_REMOTE_ENABLED
                       ,CASE WHEN (ic.iwr_cond_cd is not null and ic.iwr_cond_desc_txt is not null) THEN (ic.iwr_cond_cd || ' - ' || ic.iwr_cond_desc_txt) ELSE NULL END imageware_remote
                       ,irc.iwr_last_comm_ts ugw_last_comm_date  -- check this
                       ,smm.shr_dlr_flg  ugw_shared_flag
                       ,irc.iwr_rgtn_dt ugw_registration_date
                       ,IRC.IWR_DEINS_DT UGW_DEREGISTRATION_DATE
                       ,FIRST_VALUE(IMRDW_T.READ_MTR_CNT) OVER(PARTITION BY IMRDW_T.svc_mach_mstr_pk ORDER BY IMRDW_T.MTR_RCV_DT_TM_TS DESC) UGW_LAST_TOTAL_READING
                       ,FIRST_VALUE(imrdw_bw.read_mtr_cnt) OVER(PARTITION BY imrdw_bw.svc_mach_mstr_pk ORDER BY imrdw_bw.mtr_rcv_dt_tm_ts DESC) ugw_last_bw_reading
                  FROM mdse dmi --DB Changes
                       ,svc_mach_mstr smm
                       ,iwr_rgtn_cond irc
                       ,iwr_cond ic
                       ,CANON_E404_IB_SER_TO_UPD_TBL E404_UPD
                       ,MTR_LB MTLB_T
                       ,IWR_MTR_READ_DTL_WRK IMRDW_T
                       ,MTR_LB MTLB_BW
                       ,IWR_MTR_READ_DTL_WRK IMRDW_bw
                  --   ,svc_mach_mstr_sts e
                 WHERE 1=1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND dmi.mdse_cd(+) = smm.mdse_cd
                   AND ic.iwr_cond_cd(+) = smm.iwr_cond_cd
                   AND smm.svc_mach_mstr_pk = irc.svc_mach_mstr_pk(+)
                   AND smm.svc_mach_mstr_pk = e404_upd.instance_id
                   AND E404_UPD.BATCH_NUMBER = P_BATCH_NUMBER
                   AND MTLB_BW.MTR_CNTR_ID(+) = IMRDW_BW.MTR_CNTR_ID
                   AND IMRDW_BW.SVC_MACH_MSTR_PK(+) = SMM.SVC_MACH_MSTR_PK
                   AND MTLB_BW.BW_MTR_FLG(+) = 'Y'
                   AND MTLB_T.MTR_CNTR_ID(+) = IMRDW_T.MTR_CNTR_ID
                   AND IMRDW_T.SVC_MACH_MSTR_PK(+) = SMM.SVC_MACH_MSTR_PK
                   AND mtlb_t.tot_mtr_flg(+) = 'Y'
               /*  AND b.svc_mach_mstr_sts_cd = e.svc_mach_mstr_sts_cd
                   AND UPPER(e.svc_mach_mstr_sts_nm) IN (SELECT val1
                                                           FROM canon_s21_cd_val_tbl val
                                                                ,canon_s21_cd_tbl code
                                                          WHERE 1 = 1
                                                            AND val.cd_id = code.cd_id
                                                            AND code.cd_name = 'CANON_E404_INSTANCE_STATUS' )  */
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND dmi.glbl_cmpy_cd(+) = 'ADB'
                   AND irc.glbl_cmpy_cd(+) = 'ADB'
                   AND ic.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.ezcancelflag = 0
                   AND dmi.ezcancelflag(+) = 0
                   AND irc.ezcancelflag(+) = 0
                   AND IC.EZCANCELFLAG(+)  = 0
                   AND MTLB_BW.GLBL_CMPY_CD(+) = 'ADB'
                   AND MTLB_BW.EZCANCELFLAG(+) = 0
                   AND IMRDW_BW.GLBL_CMPY_CD(+) = 'ADB'
                   AND IMRDW_BW.EZCANCELFLAG(+) = 0
                   AND MTLB_T.GLBL_CMPY_CD(+) = 'ADB'
                   AND MTLB_t.EZCANCELFLAG(+) = 0
                   AND IMRDW_T.GLBL_CMPY_CD(+) = 'ADB'
                   AND imrdw_t.ezcancelflag(+) = 0
                )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET imageware_remote_enabled = b.imageware_remote_enabled
               ,imageware_remote = b.imageware_remote
               ,ugw_last_comm_date = b.ugw_last_comm_date
               ,ugw_shared_flag = b.ugw_shared_flag
               ,ugw_registration_date = b.ugw_registration_date
               ,ugw_deregistration_date = b.ugw_deregistration_date
               ,ugw_last_total_reading = b.ugw_last_total_reading
               ,ugw_last_bw_reading = b.ugw_last_bw_reading
               ;
       
       dbms_output.put_line ('UGW data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','UGW data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
	   
        COMMIT;
       l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

       EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

       audit_log(l_start_time
                 ,l_end_time
                 ,l_seq_currval           -- Sequence current value
                 ,NULL
                 ,l_procedure_name
                );

 EXCEPTION
   WHEN OTHERS THEN
     canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
     g_errbuff := SQLERRM;
     g_retcode := '2';
     dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
 END populate_ugw_data;

PROCEDURE populate_crxsrv_serial( p_batch_number IN NUMBER)
IS
  l_start_time         VARCHAR2(50);
  l_end_time           VARCHAR2(50);
  l_procedure_name     VARCHAR2(30);
  l_seq_currval        NUMBER;
  BEGIN
    l_procedure_name   := 'populate_crxsrv_serial' ;
     l_start_time      := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting cross service details into ib table');   -- only 1 record merged check once again

    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT distinct c.osi_serial_number osi_serial_number
                       ,e404_upd.instance_id
                  FROM CANON_E504_CRXSRV_SERIAL_MAP_V c
                       ,canon_e404_ib_ser_to_upd_tbl e404_upd
                 WHERE 1 = 1
                   AND e404_upd.serial_number = c.osi_serial_number
                   AND e404_upd.batch_number = p_batch_number
               )b
            ON ( e404.instance_id = b.instance_id)
          WHEN MATCHED THEN
        UPDATE
           SET e404.osi_serial_number = b.osi_serial_number
            ;
      
      dbms_output.put_line ('CRXSRV data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','UGW data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT;
      l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

      EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

      audit_log(l_start_time
                ,l_end_time
                ,l_seq_currval           -- Sequence current value
                ,NULL
                ,l_procedure_name
               );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
END populate_crxsrv_serial;

PROCEDURE insert_meter_reads_data
IS
  l_start_time          VARCHAR2(50);
  l_end_time            VARCHAR2(50);
  l_procedure_name      VARCHAR2(30);
  l_seq_currval         NUMBER;
  l_test_value          VARCHAR2(60);
  BEGIN
    l_procedure_name := 'insert_meter_reads_data' ;
    l_start_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting meter reads data into meter reads table');

   /*INSERT
      INTO canon_e404_s21_meter_reads_tbl (svc_mach_mstr_pk
                                           ,serial_number
                                           ,creation_date
                                           )
                                    SELECT instance_id
                                           ,serial_number
                                           ,SYSDATE
                                     FROM canon_e404_install_base_ob_tbl
                                       ;
    dbms_output.put_line ('Records inserted into canon_e404_s21_meter_reads_tbl from S21 tables: ' || SQL%ROWCOUNT);
    COMMIT; */


       INSERT
         INTO canon_e404_s21_meter_reads_tbl (svc_mach_mstr_pk
                                              ,serial_number
                                              ,initial_total_hard_read
                                              ,creation_date
                                              )
                                      (SELECT DISTINCT
                                              smm.svc_mach_mstr_pk
                                              ,smm.ser_num
                                          --  ,spmr.read_mtr_cnt initial_total_hard_read
                                              ,FIRST_VALUE(spmr.read_mtr_cnt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt
                                                            ,spmr.svc_phys_mtr_read_grp_sq ) initial_total_hard_read                         -- check the date columns
                                              ,SYSDATE
                                         FROM svc_phys_mtr_read spmr
                                              ,mtr_lb mtlb
                                              ,ds_contr_dtl dcd
                                              ,ds_contr_dtl_sts dcds
                                          --  ,ds_contr dc
                                          --  ,ds_contr_sts dcs
                                              ,svc_mach_mstr smm
                                        WHERE 1 =1
                                          AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                                                smm.sld_by_line_biz_tp_cd = 'ESS' )
                                      --  AND dc.ds_contr_tp_cd(+) = 'S'
                                      --  AND dc.ds_contr_sts_cd(+) = dcs.ds_contr_sts_cd
                                      --  AND dcs.ds_contr_sts_nm IN( SELECT val1
                                      --                                FROM canon_s21_cd_val_tbl val
                                      --                                     ,canon_s21_cd_tbl code
                                      --                               WHERE 1 = 1
                                      --                                 AND val.cd_id = code.cd_id
                                      --                                 AND code.cd_name = 'CANON_E404_CONTRACT_HEADER')
                                          AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd(+)
                                          AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                                        FROM CANON_E404_INACTIVE_CONTRACT_V)
                                          AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                                      --  AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
                                          AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                                      --  AND spmr.ds_contr_dtl_pk(+) = dcd.ds_contr_dtl_pk     --check this condition
                                          AND NVL(spmr.ds_contr_dtl_pk,dcd.ds_contr_dtl_pk) = dcd.ds_contr_dtl_pk
                                          AND spmr.ezcancelflag(+) = '0'
                                          AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
                                          AND mtlb.glbl_cmpy_cd(+) = spmr.glbl_cmpy_cd
                                          AND mtlb.mtr_lb_cd(+) = spmr.mtr_lb_cd
                                          AND mtlb.tot_mtr_flg(+)  = 'Y'
                                          AND spmr.glbl_cmpy_cd(+) = 'ADB'
                                          AND dcd.glbl_cmpy_cd(+)  = 'ADB'
                                      --  AND dc.glbl_cmpy_cd(+)   = 'ADB'
                                          AND smm.glbl_cmpy_cd  = 'ADB'
                                      --  AND dcs.glbl_cmpy_cd(+)  = 'ADB'
                                          AND dcds.glbl_cmpy_cd(+) = 'ADB'
                                          AND mtlb.ezcancelflag(+) = '0'
                                          AND dcd.ezcancelflag(+)    = '0'
                                      --  AND dc.ezcancelflag      = '0'
                                          AND smm.ezcancelflag     = '0'
                                      --  AND dcs.ezcancelflag     = '0'
                                          AND dcds.ezcancelflag    = '0'
                                        );

      dbms_output.put_line ('Intial total hard read data merged into meter reads table: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Intial total hard read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;


   MERGE INTO canon_e404_s21_meter_reads_tbl e404
        USING (SELECT smm.svc_mach_mstr_pk
                      ,SUM(spmr.read_mtr_cnt) initial_total_hard_read
                 FROM svc_phys_mtr_read spmr
                      ,mtr_lb mtlb
                      ,ds_contr_dtl dcd
                  --  ,ds_contr dc
                      ,svc_mach_mstr smm
                  --  ,ds_contr_sts dcs
                      ,ds_contr_dtl_sts dcds
                WHERE 1 = 1
                  AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                  AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
              --  AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
                  AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
              --  AND dc.ds_contr_tp_cd(+) = 'S'
              --  AND spmr.ds_contr_dtl_pk(+) = dcd.ds_contr_dtl_pk
                  AND NVL(spmr.ds_contr_dtl_pk,dcd.ds_contr_dtl_pk) = dcd.ds_contr_dtl_pk
                  AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd(+)
                  AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                FROM CANON_E404_INACTIVE_CONTRACT_V)
              --  AND dc.ds_contr_sts_cd(+) = dcs.ds_contr_sts_cd
              --  AND dcs.ds_contr_sts_nm IN( SELECT val1
              --                                FROM canon_s21_cd_val_tbl val
              --                                     ,canon_s21_cd_tbl code
              --                               WHERE 1 = 1
              --                                 AND val.cd_id = code.cd_id
              --                                 AND code.cd_name = 'CANON_E404_CONTRACT_HEADER')
                  AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
                  AND mtlb.glbl_cmpy_cd(+)  = spmr.glbl_cmpy_cd
                  AND mtlb.mtr_lb_cd(+)     = spmr.mtr_lb_cd
                  AND mtlb.tot_mtr_flg(+)   = 'N'
                  AND spmr.glbl_cmpy_cd(+)  = 'ADB'
                  AND dcd.glbl_cmpy_cd(+)   = 'ADB'
              --  AND dc.glbl_cmpy_cd       = 'ADB'
                  AND smm.glbl_cmpy_cd   = 'ADB'
              --  AND dcs.glbl_cmpy_cd      = 'ADB'
                  AND dcds.glbl_cmpy_cd(+)  = 'ADB'
                  AND mtlb.ezcancelflag(+)  = '0'
                  AND dcd.ezcancelflag(+)     = '0'
              --  AND dc.ezcancelflag       = '0'
                  AND smm.ezcancelflag      = '0'
              --  AND dcs.ezcancelflag      = '0'
                  AND dcds.ezcancelflag     = '0'
                  GROUP BY smm.svc_mach_mstr_pk
                )b
           ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
         WHEN MATCHED THEN
       UPDATE
          SET initial_total_hard_read = CASE WHEN initial_total_hard_read IS NULL
                                             THEN b.initial_total_hard_read
                                             ELSE initial_total_hard_read
                                             END
              ;

       dbms_output.put_line ('Intial total hard read data merged into meter reads table: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Intial total hard read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT ;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                       ,FIRST_VALUE(spmr.read_mtr_cnt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt DESC
                                                            ,spmr.svc_phys_mtr_read_grp_sq DESC ) latest_total_hard_read
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
               --  AND spmr.inv_proc_flg(+)  = 'Y'   --If you want to get the meter counts for the machine, you can except the flag -- from shutaro 10/06      -- this flag is having 'N' value
                   AND mtlb.glbl_cmpy_cd(+)  = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = spmr.mtr_lb_cd
                   AND mtlb.tot_mtr_flg(+)   = 'Y'
                   AND spmr.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd(+)   = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND smm.ezcancelflag      = '0'
                   AND spmr.ezcancelflag     = '0'
                )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET latest_total_hard_read = b.latest_total_hard_read
             ;

       dbms_output.put_line ('Latest total hard read data merged into meter reads table: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Latest total hard read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT distinct smm.svc_mach_mstr_pk
                       ,SUM(spmr.read_mtr_cnt) latest_total_hard_read
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
               --  AND spmr.inv_proc_flg(+) = 'Y'  --If you want to get the meter counts for the machine, you can except the flag -- from shutaro 10/06              -- this flag is having 'N' value
                   AND mtlb.glbl_cmpy_cd(+) = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)    = spmr.mtr_lb_cd
                   AND mtlb.tot_mtr_flg(+ ) = 'N'
                   AND mtlb.ezcancelflag(+) = '0'
                   AND smm.ezcancelflag     = '0'
                   AND spmr.ezcancelflag(+)    = '0'
                   AND spmr.glbl_cmpy_cd(+) = 'ADB'
                   AND smm.glbl_cmpy_cd  = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+) = 'ADB'
                   GROUP BY smm.svc_mach_mstr_pk
                 )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET latest_total_hard_read = CASE WHEN latest_total_hard_read IS NULL
                                             THEN b.latest_total_hard_read
                                             ELSE latest_total_hard_read
                                             END
              ;

       dbms_output.put_line ('Intial total hard read data merged into meter reads table: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Intial total hard read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
  COMMIT ;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                   --  ,spmr.mtr_read_dt initial_meter_read_date
                       ,FIRST_VALUE(spmr.mtr_read_dt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt
                                                      ,spmr.svc_phys_mtr_read_grp_sq ) initial_meter_read_date
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,ds_contr_dtl dcd
                   --  ,ds_contr dc
                       ,svc_mach_mstr smm
                   --  ,ds_contr_sts dcs
                       ,ds_contr_dtl_sts dcds
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
               --  AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
               --  AND dc.ds_contr_tp_cd(+) = 'S'
               --  AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
               --  AND dcs.ds_contr_sts_nm IN( SELECT val1
               --                                FROM canon_s21_cd_val_tbl val
               --                                     ,canon_s21_cd_tbl code
               --                               WHERE 1 = 1
               --                                 AND val.cd_id = code.cd_id
               --                                 AND code.cd_name = 'CANON_E404_CONTRACT_HEADER')
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
               --  AND spmr.ds_contr_dtl_pk(+) = dcd.ds_contr_dtl_pk
                   AND NVL(spmr.ds_contr_dtl_pk,dcd.ds_contr_dtl_pk) = dcd.ds_contr_dtl_pk
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
                   AND mtlb.glbl_cmpy_cd(+)   = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)      = spmr.mtr_lb_cd
                   AND mtlb.tot_mtr_flg(+)    = 'Y'
                   AND spmr.glbl_cmpy_cd(+)   = 'ADB'
                   AND dcd.glbl_cmpy_cd    = 'ADB'
               --  AND dc.glbl_cmpy_cd        = 'ADB'
                   AND smm.glbl_cmpy_cd(+)    = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)   = 'ADB'
                   AND mtlb.ezcancelflag(+)   = '0'
                   AND spmr.ezcancelflag(+)     = '0'
                   AND dcd.ezcancelflag       = '0'
               --  AND dc.ezcancelflag        = '0'
                   AND smm.ezcancelflag       = '0'
                 )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET initial_meter_read_date = TO_DATE(b.initial_meter_read_date,'RRRRMMDD')
           ;

    dbms_output.put_line ('initial_meter_read_date data merged into meter reads table: ' || SQL%ROWCOUNT);
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Initial_meter_read_date data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
         COMMIT ;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                       ,FIRST_VALUE(spmr.mtr_read_dt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt DESC
                                                           ,spmr.svc_phys_mtr_read_grp_sq DESC ) latest_meter_read_date
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
               --  AND spmr.inv_proc_flg(+)  = 'Y'    --If you want to get the meter counts for the machine, you can except the flag -- from shutaro 10/06             -- this flag has N value
                   AND mtlb.glbl_cmpy_cd(+)  = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = spmr.mtr_lb_cd
                   AND mtlb.tot_mtr_flg(+)   = 'Y'
                   AND spmr.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND spmr.ezcancelflag(+)     = '0'
                   AND smm.ezcancelflag      = '0'
                ) b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET latest_meter_read_date = TO_DATE(b.latest_meter_read_date ,'RRRRMMDD')
          ;

    dbms_output.put_line ('latest_meter_read_date data merged into meter reads table: ' || SQL%ROWCOUNT);
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','latest_meter_read_date data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                   --  ,spmr.read_mtr_cnt initial_bw_billed_read
                       ,FIRST_VALUE(spmr.read_mtr_cnt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt
                                                           ,spmr.svc_phys_mtr_read_grp_sq ) initial_bw_billed_read   -- check the dates column
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,ds_contr_dtl dcd
                   --  ,ds_contr dc
                       ,svc_mach_mstr smm
                   --  ,ds_contr_sts dcs
                       ,ds_contr_dtl_sts dcds
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk (+)
               --  AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
               --  AND dcs.ds_contr_sts_nm IN ( SELECT val1
               --                                 FROM canon_s21_cd_val_tbl val
               --                                      ,canon_s21_cd_tbl code
               --                                WHERE 1 = 1
               --                                  AND val.cd_id = code.cd_id
               --                                  AND code.cd_name = 'CANON_E404_CONTRACT_HEADER')
               --  AND dcd.ds_contr_pk = dc.ds_contr_pk (+)
               --  AND dc.ds_contr_tp_cd(+) = 'S'
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
               --  AND spmr.ds_contr_dtl_pk(+) = dcd.ds_contr_dtl_pk
                   AND NVL(spmr.ds_contr_dtl_pk,dcd.ds_contr_dtl_pk) = dcd.ds_contr_dtl_pk
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
                   AND mtlb.glbl_cmpy_cd(+)  = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = spmr.mtr_lb_cd
                   AND mtlb.bw_mtr_flg(+)    = 'Y'                 -- Value is present if we put outer join but have to remove outer join as this condiiton should be present to get bw reads
                   AND spmr.glbl_cmpy_cd(+)  = 'ADB'
                   AND dcd.glbl_cmpy_cd(+)   = 'ADB'
               --  AND dc.glbl_cmpy_cd       = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
               --  AND dcs.glbl_cmpy_cd      = 'ADB'
                   AND dcds.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND spmr.ezcancelflag(+)     = '0'
                   AND dcd.ezcancelflag      = '0'
               --  AND dc.ezcancelflag       = '0'
                   AND smm.ezcancelflag      = '0'
               --  AND dcs.ezcancelflag      = '0'
                   AND dcds.ezcancelflag     = '0'
                 ) b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET initial_bw_billed_read = b.initial_bw_billed_read
           ;

     dbms_output.put_line ('initial_bw_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT);
	 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','initial_bw_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                   --  ,spmr.read_mtr_cnt initial_color_billed_read
                       ,FIRST_VALUE(spmr.read_mtr_cnt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt
                                                           ,spmr.svc_phys_mtr_read_grp_sq ) initial_color_billed_read   -- check the dates column
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,ds_contr_dtl dcd
                   --  ,ds_contr dc
                       ,svc_mach_mstr smm
                   --  ,ds_contr_sts dcs
                       ,ds_contr_dtl_sts dcds
                 WHERE 1= 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk (+)
               --  AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
               --  AND dcs.ds_contr_sts_nm IN ( SELECT val1
               --                                 FROM canon_s21_cd_val_tbl val
               --                                      ,canon_s21_cd_tbl code
               --                                WHERE 1 = 1
               --                                  AND val.cd_id = code.cd_id
               --                                  AND code.cd_name = 'CANON_E404_CONTRACT_HEADER')
               --  AND dcd.ds_contr_pk = dc.ds_contr_pk (+)
               --  AND dc.ds_contr_tp_cd(+) = 'S'
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
               --  AND spmr.ds_contr_dtl_pk(+) = dcd.ds_contr_dtl_pk
                   AND NVL(spmr.ds_contr_dtl_pk,dcd.ds_contr_dtl_pk) = dcd.ds_contr_dtl_pk
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
                   AND mtlb.glbl_cmpy_cd(+)  = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = spmr.mtr_lb_cd
                   AND mtlb.color_mtr_flg(+) = 'Y'               -- IF we remove outer join as we dont get value for intial color reads , as we have added
                                                                  -- outer join we are getting same value for color also as bw read data
                   AND spmr.glbl_cmpy_cd(+)  = 'ADB'
                   AND dcd.glbl_cmpy_cd(+)   = 'ADB'
               --  AND dc.glbl_cmpy_cd       = 'ADB'
                   AND smm.glbl_cmpy_cd(+)   = 'ADB'
               --  AND dcs.glbl_cmpy_cd      = 'ADB'
                   AND dcds.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND spmr.ezcancelflag(+)     = '0'
                   AND dcd.ezcancelflag      = '0'
               --  AND dc.ezcancelflag       = '0'
                   AND smm.ezcancelflag      = '0'
               --  AND dcs.ezcancelflag      = '0'
                   AND dcds.ezcancelflag     = '0'
                 ) b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET initial_color_billed_read = b.initial_color_billed_read
            ;

      dbms_output.put_line ('initial_color_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','initial_color_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                       ,FIRST_VALUE(spmr.read_mtr_cnt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt DESC
                                                        ,spmr.svc_phys_mtr_read_grp_sq DESC ) latest_bw_billed_read
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND spmr.ezcancelflag = '0'
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
               --  AND spmr.inv_proc_flg(+)  = 'Y'   --If you want to get the meter counts for the machine, you can except the flag -- from shutaro 10/06           -- this flag is havig N value
                   AND mtlb.glbl_cmpy_cd(+)  = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = spmr.mtr_lb_cd
                   AND mtlb.bw_mtr_flg(+)    = 'Y'               -- If we remove outer join we dont get value of latest bw reads
                   AND spmr.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND spmr.ezcancelflag(+)     = '0'
                   AND smm.ezcancelflag      = '0'
                 ) b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET latest_bw_billed_read = b.latest_bw_billed_read
           ;

     dbms_output.put_line ('latest_bw_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT);
	 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','latest_bw_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
     COMMIT;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                       ,FIRST_VALUE(spmr.read_mtr_cnt) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY spmr.mtr_read_dt DESC
                                                        ,spmr.svc_phys_mtr_read_grp_sq DESC ) latest_color_billed_read
                  FROM svc_phys_mtr_read spmr
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND spmr.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND spmr.ds_mtr_read_tp_grp_cd(+) ='B'
               --  AND spmr.inv_proc_flg(+)  = 'Y'  --If you want to get the meter counts for the machine, you can except the flag -- from shutaro 10/06                    -- this flag is havig N value
                   AND mtlb.glbl_cmpy_cd(+)  = spmr.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = spmr.mtr_lb_cd
                   AND mtlb.color_mtr_flg(+) = 'Y'
                   AND spmr.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND spmr.ezcancelflag(+)     = '0'
                   AND smm.ezcancelflag      = '0'
                )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET latest_color_billed_read = b.latest_color_billed_read
             ;

       dbms_output.put_line ('latest_color_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','latest_color_billed_read data merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                   --  ,sabs.avg_mtr_read_cnt*30.42 avg_monthly_cpy_vol_total
                       ,FIRST_VALUE(sabs.avg_mtr_read_cnt*30.42) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY smm.last_svc_call_dt DESC
                                                       ) avg_monthly_cpy_vol_total
                  FROM svc_adcv_by_ser sabs
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND sabs.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND mtlb.glbl_cmpy_cd(+)  = sabs.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = sabs.mtr_lb_cd
                   AND mtlb.tot_mtr_flg(+)   = 'Y'
                   AND sabs.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND sabs.ezcancelflag(+)     = '0'
                   AND smm.ezcancelflag      = '0'
                )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET avg_monthly_cpy_vol_total = b.avg_monthly_cpy_vol_total
            ;

      dbms_output.put_line ('avg_monthly_cpy_vol_total merged into meter reads table: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','avg_monthly_cpy_vol_total merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT distinct smm.svc_mach_mstr_pk
                       ,SUM(sabs.avg_mtr_read_cnt*30.42) avg_monthly_cpy_vol_total
                  FROM svc_adcv_by_ser sabs
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND sabs.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND sabs.ezcancelflag(+) = '0'
                   AND mtlb.glbl_cmpy_cd(+)  = sabs.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = sabs.mtr_lb_cd
                   AND mtlb.tot_mtr_flg(+)   = 'N'
                   AND sabs.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND sabs.ezcancelflag(+)     = '0'
                   AND smm.ezcancelflag      = '0'
                   GROUP BY smm.svc_mach_mstr_pk
                 )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET avg_monthly_cpy_vol_total = CASE WHEN avg_monthly_cpy_vol_total IS NULL
                                                THEN b.avg_monthly_cpy_vol_total
                                                ELSE avg_monthly_cpy_vol_total
                                                END
            ;

      dbms_output.put_line ('avg_monthly_cpy_vol_total merged into meter reads table: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','avg_monthly_cpy_vol_total merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;


    MERGE INTO canon_e404_s21_meter_reads_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                   --  ,sabs.avg_mtr_read_cnt*30.42 avg_monthly_cpy_vol_bw
                       ,FIRST_VALUE(sabs.avg_mtr_read_cnt*30.42) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY smm.last_svc_call_dt DESC
                                                       ) avg_monthly_cpy_vol_bw
                  FROM svc_adcv_by_ser sabs
                       ,mtr_lb mtlb
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND sabs.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND mtlb.glbl_cmpy_cd(+)  = sabs.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd(+)     = sabs.mtr_lb_cd
                   AND mtlb.bw_mtr_flg(+)    = 'Y'
                   AND sabs.glbl_cmpy_cd(+)  = 'ADB'
                   AND mtlb.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND mtlb.ezcancelflag(+)  = '0'
                   AND sabs.ezcancelflag(+)     = '0'
                   AND smm.ezcancelflag      = '0'
               )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET avg_monthly_cpy_vol_bw = b.avg_monthly_cpy_vol_bw
             ;

      dbms_output.put_line ('avg_monthly_cpy_vol_bw merged into meter reads table: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','avg_monthly_cpy_vol_bw merged into meter reads table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT ;

      l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

      EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

      audit_log(l_start_time
                ,l_end_time
                ,l_seq_currval           -- Sequence current value
                ,NULL
                ,l_procedure_name
               );

 EXCEPTION
    WHEN OTHERS THEN
      canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
      g_errbuff := SQLERRM;
      g_retcode := '2';
      dbms_output.put_line ('Records are not inserted into table exception occured: ' || g_errbuff);
 END insert_meter_reads_data;

PROCEDURE populate_meterreads_data( p_batch_number IN NUMBER)
IS
  l_start_time          VARCHAR2(50);
  l_end_time            VARCHAR2(50);
  l_procedure_name      VARCHAR2(30);
  l_seq_currval         NUMBER;
  BEGIN
    l_procedure_name := 'populate_meterreads_data' ;
    l_start_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting meter reads details into ib table');
    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT distinct a.svc_mach_mstr_pk
                       ,initial_total_hard_read
                       ,latest_total_hard_read
                       ,initial_meter_read_date
                       ,latest_meter_read_date
                       ,initial_bw_billed_read
                       ,initial_color_billed_read
                       ,latest_bw_billed_read
                       ,latest_color_billed_read
                       ,avg_monthly_cpy_vol_total
                       ,avg_monthly_cpy_vol_bw
                  FROM canon_e404_s21_meter_reads_tbl a
                       ,canon_e404_ib_ser_to_upd_tbl e404_upd
                 WHERE 1 = 1
                   AND a.svc_mach_mstr_pk = e404_upd.instance_id
                   AND e404_upd.batch_number = p_batch_number
               )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET initial_total_hard_read = b.initial_total_hard_read
               ,latest_total_hard_read = b.latest_total_hard_read
               ,initial_meter_read_date = b.initial_meter_read_date
               ,latest_meter_read_date = b.latest_meter_read_date
               ,initial_bw_billed_read = b.initial_bw_billed_read
               ,initial_color_billed_read = b.initial_color_billed_read
               ,latest_bw_billed_read = b.latest_bw_billed_read
               ,latest_color_billed_read = b.latest_color_billed_read
               ,avg_monthly_cpy_vol_total = b.avg_monthly_cpy_vol_total
               ,avg_monthly_cpy_vol_bw = b.avg_monthly_cpy_vol_bw
               ;
       dbms_output.put_line ('Meter reads data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Meter reads data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

       l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

       EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

       audit_log(l_start_time
                 ,l_end_time
                 ,l_seq_currval           -- Sequence current value
                 ,NULL
                 ,l_procedure_name
              );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Meter reads details are not inserted into table exception occured: ' || g_errbuff);
END populate_meterreads_data;

PROCEDURE populate_lease_data( p_batch_number IN NUMBER)
IS
  l_start_time           VARCHAR2(50);
  l_end_time             VARCHAR2(50);
  l_procedure_name       VARCHAR2(30);
  l_seq_currval          NUMBER;
  BEGIN
    l_procedure_name := 'populate_lease_data' ;
    l_start_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting lease details into ib table');

    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                       ,e215.serial_number
                       ,FIRST_VALUE(e215.lease_number) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC ) lease_number
                       ,FIRST_VALUE(e215.lessor) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC) leassor
                       ,FIRST_VALUE(e215.term_date) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC) lease_expiration_date
                       ,FIRST_VALUE(e215.UPGRADE) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC) lease_upgrade_amount
                       ,FIRST_VALUE(e215.term) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC) lease_term
                       ,FIRST_VALUE(e215.payment_amount) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC) lease_payment_amount
                       ,'CFS' lease_source
                       ,FIRST_VALUE(e215.disposal_date) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC) lease_disposal_date
                       ,FIRST_VALUE(e215.inventory_date) OVER(PARTITION BY e215.serial_number ORDER BY e215.disposal_date DESC NULLS FIRST, e215.inventory_date DESC NULLS FIRST,e215.last_update_date DESC) lease_inventory_date
                       ,SYSDATE last_update_date
                       ,e404_del.psn_when_on_lease
                  FROM canon_e215_cfs_upg_dtl_tbl e215
                       ,svc_mach_mstr smm
                       ,canon_e404_ib_ser_to_upd_tbl e404_upd
                       ,svc_mach_mstr_sts smms
                       ,(SELECT DISTINCT
                                 e404_del.serial_number
                                 ,FIRST_VALUE(e404_del.psn_when_on_lease) OVER(PARTITION BY e404_del.serial_number ORDER BY e404_del.last_update_date DESC)  psn_when_on_lease
                           FROM canon_e404_ib_ser_to_del_tbl e404_del
                         ) e404_del
                 WHERE e215.serial_number != 'NA'
                   AND e215.serial_number != 'N/A'
                   AND e215.serial_number = smm.ser_num
                   AND smm.svc_mach_mstr_pk = e404_upd.instance_id
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND e404_upd.batch_number = p_batch_number
                   AND e404_upd.serial_number = e404_del.serial_number(+)
                   AND NVL(overage_tier_key, 1) = 1
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V )
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = 0
               )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET lease_source = b.lease_source
               ,lease_number = b.lease_number
               ,leassor = b.leassor
               ,lease_expiration_date = b.lease_expiration_date
               ,lease_upgrade_amount = b.lease_upgrade_amount
               ,lease_term = b.lease_term
               ,lease_payment_amount = b.lease_payment_amount
               ,lease_disposal_date = b.lease_disposal_date
               ,lease_inventory_date = b.lease_inventory_date
               ,psn_when_on_lease = (CASE WHEN (TRUNC(NVL(b.lease_disposal_date, SYSDATE+1)) > TRUNC(SYSDATE) AND
                                                TRUNC(NVL(b.lease_inventory_date, SYSDATE+1)) > TRUNC(SYSDATE) AND
                                                TRUNC(NVL(b.lease_expiration_date, SYSDATE+1)) > TRUNC(SYSDATE)
                                                )
                                          THEN
                                            e404.cbs_party_site_number
                                          ELSE
                                            b.psn_when_on_lease
                                          END
                                    )
              ;
       
       dbms_output.put_line ('Lease details data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Lease details data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT ;
       l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

       EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

       audit_log(l_start_time
                 ,l_end_time
                 ,l_seq_currval            -- Sequence current value
                 ,NULL
                 ,l_procedure_name
              );

 EXCEPTION
   WHEN OTHERS THEN
     canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
     g_errbuff := SQLERRM;
     g_retcode := '2';
     dbms_output.put_line ('Lease details are not merged into ib table exception occured: ' || g_errbuff);
 END populate_lease_data;

PROCEDURE insert_ctr_terms_data
IS
  l_start_time            VARCHAR2(50);
  l_end_time              VARCHAR2(50);
  l_procedure_name        VARCHAR2(30);
  l_up_mn_aot             VARCHAR2(25);
  l_seq_currval           NUMBER;
  BEGIN
    l_procedure_name := 'insert_ctr_terms_data' ;
    l_start_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting contract terms data into terms table');

    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                       ,term.svc_term_cond_pk
                       ,term.svc_term_attrb_map_val_cd  sla_response_time
                  FROM svc_term_cond term
                       ,svc_term_cond_attrb attr
                       ,ds_contr_dtl dcd
                       ,ds_contr dc
                       ,svc_mach_mstr smm
                       ,ds_contr_sts dcs
                       ,ds_contr_dtl_sts dcds
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk (+)
                   AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
                   AND dcs.ds_contr_sts_nm IN ( SELECT status
                                                  FROM CANON_E404_CONTRACT_HEADER_V)
                   AND dc.ds_contr_tp_cd(+) = 'S'
                   AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
                   AND term.ds_contr_pk(+) = dc.ds_contr_pk
                   AND term.ds_contr_dtl_pk(+) = dcd.ds_contr_dtl_pk
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND term.svc_term_attrb_map_val_cd(+) IS NOT NULL
                   AND UPPER(attr.svc_term_cond_attrb_nm) = UPPER('Rsp Tm Comit')   -- check
                   AND term.glbl_cmpy_cd(+) = attr.glbl_cmpy_cd
                   AND term.svc_term_cond_attrb_pk(+) = attr.svc_term_cond_attrb_pk
                   AND term.glbl_cmpy_cd(+)  = 'ADB'
                   AND attr.glbl_cmpy_cd  = 'ADB'
                   AND dcd.glbl_cmpy_cd(+)   = 'ADB'
                   AND dc.glbl_cmpy_cd(+)    = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND dcs.glbl_cmpy_cd   = 'ADB'
                   AND dcds.glbl_cmpy_cd  = 'ADB'
                   AND term.ezcancelflag(+)  = '0'
                   AND attr.ezcancelflag  = '0'
                   AND dcd.ezcancelflag(+)   = '0'
                   AND dc.ezcancelflag(+)    = '0'
                   AND smm.ezcancelflag   = '0'
                   AND dcs.ezcancelflag   = '0'
                   AND dcds.ezcancelflag  = '0'
                   AND TO_DATE(attr.eff_from_dt,'YYYY-MM-DD') <= TRUNC(SYSDATE)
                   AND (TO_DATE(attr.eff_thru_dt,'YYYY-MM-DD') >= TRUNC(SYSDATE)
                        OR attr.eff_thru_dt IS NULL)
                 )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET sla_response_time = b.sla_response_time
               ;
       
       dbms_output.put_line ('sla_response_time 1 data merged into contract terms table: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','sla_response_time 1 data merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT;
        
    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT distinct smm.svc_mach_mstr_pk
                       ,term.svc_term_cond_pk
                       ,term.svc_term_attrb_map_val_cd  sla_response_time
                  FROM svc_term_cond term
                       ,svc_term_cond_attrb attr
                       ,ds_contr_dtl dcd
                       ,ds_contr dc
                       ,svc_mach_mstr smm
                       ,ds_contr_sts dcs
                       ,ds_contr_dtl_sts dcds
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                   AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
                   AND dcs.ds_contr_sts_nm IN (SELECT status
                                                 FROM CANON_E404_CONTRACT_HEADER_V)
                   AND dc.ds_contr_tp_cd(+) = 'S'
                   AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
                   AND term.ds_contr_pk(+) = dc.ds_contr_pk
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND term.ds_contr_dtl_pk IS NULL
                   AND term.svc_term_attrb_map_val_cd IS NOT NULL
                   AND UPPER(attr.svc_term_cond_attrb_nm) = UPPER('Rsp Tm Comit')
                   AND term.glbl_cmpy_cd(+) = attr.glbl_cmpy_cd
                   AND term.svc_term_cond_attrb_pk(+) = attr.svc_term_cond_attrb_pk
                   AND term.glbl_cmpy_cd(+)   = 'ADB'
                   AND attr.glbl_cmpy_cd   = 'ADB'
                   AND dcd.glbl_cmpy_cd(+)    = 'ADB'
                   AND dc.glbl_cmpy_cd(+)     = 'ADB'
                   AND smm.glbl_cmpy_cd    = 'ADB'
                   AND dcs.glbl_cmpy_cd    = 'ADB'
                   AND dcds.glbl_cmpy_cd   = 'ADB'
                   AND term.ezcancelflag(+)   = '0'
                   AND attr.ezcancelflag   = '0'
                   AND dcd.ezcancelflag(+)    = '0'
                   AND dc.ezcancelflag(+)     = '0'
                   AND smm.ezcancelflag    = '0'
                   AND dcs.ezcancelflag    = '0'
                   AND dcds.ezcancelflag   = '0'
                   AND TO_DATE(attr.eff_from_dt,'YYYY-MM-DD') <= TRUNC(SYSDATE)
                   AND (TO_DATE(attr.eff_thru_dt,'YYYY-MM-DD') >= TRUNC(SYSDATE)
                        OR attr.eff_thru_dt IS NULL)
                 )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET sla_response_time = CASE WHEN sla_response_time IS NULL
                                        THEN b.sla_response_time
                                        ELSE sla_response_time
                                        END
            ;
            dbms_output.put_line ('sla_response_time 2 data merged into contract terms table: ' || SQL%ROWCOUNT);
			canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','sla_response_time 2 data merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT DISTINCT smm.svc_mach_mstr_pk
                       ,dm.rsp_tm_up_mn_aot  sla_response_time
                       ,dm.rsp_tm_down_mn_aot
                  FROM mdl_nm mn
                       ,ds_mdl dm
                       ,svc_config_mstr scm
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND mn.t_glbl_cmpy_cd(+) = dm.glbl_cmpy_cd
                   AND mn.t_mdl_id(+) = dm.mdl_id
                   AND scm.svc_config_mstr_pk = smm.svc_config_mstr_pk
                   AND scm.mdl_id = dm.mdl_id(+)
                   AND dm.mdl_actv_flg(+)   = 'Y'
                   AND mn.t_glbl_cmpy_cd(+) = 'ADB'
                   AND dm.glbl_cmpy_cd(+)   = 'ADB'
                   AND scm.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd     = 'ADB'
                   AND mn.ezcancelflag(+)   = '0'
                   AND dm.ezcancelflag(+)   = '0'
                   AND scm.ezcancelflag(+)  = '0'
                   AND smm.ezcancelflag     = '0'
                -- AND ROWNUM = 1
               )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET sla_response_time = CASE WHEN sla_response_time IS NULL
                                        THEN TO_CHAR(b.sla_response_time)
                                        ELSE sla_response_time
                                        END ;
             dbms_output.put_line ('sla_response_time 3 data merged into contract terms table: ' || SQL%ROWCOUNT);
			 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','sla_response_time 3 data merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT ;

      BEGIN
        SELECT var_char_const_val
          INTO l_up_mn_aot
          FROM var_char_const
         WHERE 1 = 1
           AND UPPER(var_char_const_nm) = UPPER('RSP_TM_UP_MN_AOT');
      EXCEPTION
        WHEN OTHERS
        THEN
          l_up_mn_aot := 0;
      END;

      UPDATE canon_e404_s21_ctr_terms_tbl
         SET sla_response_time = CASE WHEN sla_response_time IS NULL
                                      THEN
                                        l_up_mn_aot
                                      ELSE
                                        sla_response_time
                                      END
             ;
      
      dbms_output.put_line ('SLA response time value merged into contract terms table: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','SLA response time value merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;
 /* Add logic for service coverage hours */

/* MERGE INTO canon_e404_s21_ctr_terms_tbl e404
       USING (
               )b
           ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
         WHEN MATCHED THEN
       UPDATE
          SET service_coverage_hours = CASE WHEN service_coverage_hours IS NULL THEN b.service_coverage_hours
                                       ELSE service_coverage_hours END
             ;
      COMMIT;  */

    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT DISTINCT
                   --  b.bllg_mtr_bllg_cycle_cd  reading_cycle
                       FIRST_VALUE(dcbm.bllg_mtr_bllg_cycle_cd) OVER(PARTITION BY smm.svc_mach_mstr_pk ORDER BY dcd.mtr_bllg_last_bllg_dt DESC NULLS FIRST ) reading_cycle
                       ,smm.svc_mach_mstr_pk
                  FROM ds_contr_dtl dcd
                       ,ds_contr_bllg_mtr dcbm
                       ,svc_mach_mstr smm
                       ,ds_contr_dtl_sts dcds
                 WHERE 1=1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND dcd.ds_contr_dtl_pk = dcbm.ds_contr_dtl_pk(+)
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND dcd.glbl_cmpy_cd(+)   = 'ADB'
                   AND dcbm.glbl_cmpy_cd(+)  = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND dcds.glbl_cmpy_cd  = 'ADB'
                   AND dcd.ezcancelflag(+)   = '0'
                   AND dcbm.ezcancelflag(+)  = '0'
                   AND smm.ezcancelflag   = '0'
                   AND dcds.ezcancelflag  = '0'
                )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET reading_cycle = b.reading_cycle            ;
            dbms_output.put_line ('reading_cycle data merged into contract terms table: ' || SQL%ROWCOUNT);
			canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','reading_cycle data merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT DISTINCT
                       dc.inv_sept_base_usg_flg  consolidated_flag
                       ,smm.svc_mach_mstr_pk
                  FROM ds_contr_dtl dcd
                       ,ds_contr dc
                       ,svc_mach_mstr smm
                       ,ds_contr_dtl_sts dcds
                       ,ds_contr_sts dcs
                 WHERE 1=1
                   AND (smm.svc_by_line_biz_tp_cd = 'ESS' OR
                       smm.sld_by_line_biz_tp_cd = 'ESS' )
                   AND dcd.ds_contr_pk = dc.ds_contr_pk(+)
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                   AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
                   AND dcs.ds_contr_sts_nm IN (SELECT status
                                                 FROM CANON_E404_CONTRACT_HEADER_V)
                   AND dc.ds_contr_tp_cd(+) = 'S'
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND dcd.glbl_cmpy_cd(+)   = 'ADB'
                   AND dc.glbl_cmpy_cd(+)    = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND dcds.glbl_cmpy_cd  = 'ADB'
                   AND dcs.glbl_cmpy_cd   = 'ADB'
                   AND dcd.ezcancelflag(+)   = '0'
                   AND dc.ezcancelflag(+)    = '0'
                   AND smm.ezcancelflag   = '0'
                   AND dcds.ezcancelflag  = '0'
                   AND dcs.ezcancelflag   = '0'
                )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET consolidated_flag = b.consolidated_flag             ;
             dbms_output.put_line ('consolidated_flag data merged into contract terms table: ' || SQL%ROWCOUNT);
			 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','consolidated_flag data merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

      -- check this query for mach_mastr_pk

    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT DISTINCT NVL(dtl_cond.svc_term_cond_data_disp_txt,contr_cond.svc_term_cond_data_disp_txt)
                       AS svc_term_cond_data_disp_txt
                       ,dtl_cond.svc_mach_mstr_pk
                  FROM svc_term_cond_attrb tca
                       ,svc_mach_mstr smm
                       ,(SELECT tcn.svc_term_cond_attrb_pk
                                ,tcd.svc_term_cond_data_src_cd
                                ,tcd.svc_term_cond_data_disp_txt
                                ,b.svc_mach_mstr_pk
                           FROM svc_term_cond tcn
                                ,svc_term_cond_data_src tcd
                                ,ds_contr_dtl b
                          WHERE 1 = 1
                            AND tcn.glbl_cmpy_cd = 'ADB'
                            AND tcn.ds_contr_dtl_pk = b.ds_contr_dtl_pk
                            AND tcn.ezcancelflag = '0'
                            AND tcn.glbl_cmpy_cd = tcd.glbl_cmpy_cd
                            AND tcn.svc_term_attrb_map_val_cd = tcd.svc_term_cond_data_val_cd
                            AND tcd.ezcancelflag = '0'
                          ) dtl_cond
                       ,(SELECT tcn.svc_term_cond_attrb_pk
                                ,tcd.svc_term_cond_data_src_cd
                                ,tcd.svc_term_cond_data_disp_txt
                           FROM svc_term_cond tcn
                                ,svc_term_cond_data_src tcd
                                ,ds_contr a
                          WHERE 1 = 1
                            AND tcn.glbl_cmpy_cd = 'ADB'
                            AND tcn.ds_contr_pk = a.ds_contr_pk
                            AND tcn.ds_contr_dtl_pk IS NULL
                            AND tcn.ezcancelflag = '0'
                            AND tcn.glbl_cmpy_cd = tcd.glbl_cmpy_cd
                            AND tcn.svc_term_attrb_map_val_cd = tcd.svc_term_cond_data_val_cd
                            AND tcd.ezcancelflag = '0'
                         )contr_cond
                 WHERE 1 = 1
                   AND tca.glbl_cmpy_cd(+) = 'ADB'
                   AND tca.svc_term_cond_attrb_nm = 'Stpl Incl'
                   AND TO_DATE(tca.eff_from_dt,'YYYY-MM-DD') <= TRUNC(SYSDATE)
                   AND (TO_DATE(tca.eff_thru_dt,'YYYY-MM-DD') >= TRUNC(SYSDATE)
                        OR  TO_DATE(tca.eff_thru_dt,'YYYY-MM-DD') IS NULL)
                   AND tca.ezcancelflag(+) = '0'
                   AND tca.svc_term_cond_attrb_pk = dtl_cond.svc_term_cond_attrb_pk(+)
                   AND tca.svc_term_cond_data_src_cd = dtl_cond.svc_term_cond_data_src_cd(+)
                   AND tca.svc_term_cond_attrb_pk = contr_cond.svc_term_cond_attrb_pk(+)
                   AND tca.svc_term_cond_data_src_cd = contr_cond.svc_term_cond_data_src_cd(+)
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = '0'
                   AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                   AND smm.svc_mach_mstr_pk = dtl_cond.svc_mach_mstr_pk  -- added by RG
                  )b
            ON (e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET staples_included_flag = b.svc_term_cond_data_disp_txt
            ;
            dbms_output.put_line ('staples_included_flag data merged into contract terms table: ' || SQL%ROWCOUNT);
			canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','staples_included_flag data merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT DISTINCT
                       mrm.mtr_read_meth_nm  meter_read_method
                       ,smm.svc_mach_mstr_pk
                  FROM ds_contr_dtl dcd
                       ,mtr_read_meth mrm
                       ,svc_mach_mstr smm
                       ,ds_contr_dtl_sts dcds
                 WHERE 1=1
                 AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                   AND smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk(+)
                   AND dcd.mtr_read_meth_cd = mrm.mtr_read_meth_cd
                   AND dcd.ds_contr_dtl_sts_cd = dcds.ds_contr_dtl_sts_cd
                   AND UPPER(dcds.ds_contr_dtl_sts_nm) NOT IN (SELECT status
                                                                 FROM CANON_E404_INACTIVE_CONTRACT_V)
                   AND dcd.glbl_cmpy_cd(+)  = 'ADB'
                   AND mrm.glbl_cmpy_cd  = 'ADB'
                   AND smm.glbl_cmpy_cd  = 'ADB'
                   AND dcds.glbl_cmpy_cd = 'ADB'
                   AND dcd.ezcancelflag(+)  = '0'
                   AND mrm.ezcancelflag  = '0'
                   AND smm.ezcancelflag  = '0'
                   AND dcds.ezcancelflag = '0'
                )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET meter_read_method = b.meter_read_method
            ;
            dbms_output.put_line ('meter_read_method data merged into contract terms table: ' || SQL%ROWCOUNT);
			canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','meter_read_method data merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

      -- Start from here for table aliases -- Manjeera

    MERGE INTO canon_e404_s21_ctr_terms_tbl e404
         USING (SELECT DISTINCT
                       smm.svc_mach_mstr_pk
                       ,FIRST_VALUE( st.svc_cust_attn_txt||' '||st.cust_tel_num ||' '||
                                  st.cust_tel_extn_num||'  '||st.cust_eml_addr) OVER (PARTITION BY smm.svc_mach_mstr_pk
                        ORDER BY st.svc_task_rcv_dt DESC NULLS LAST , st.svc_task_rcv_tm DESC NULLS LAST) last_service_contact
                  FROM svc_task  st
                       ,svc_mach_mstr smm
                 WHERE 1 = 1
                   AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                   AND st.glbl_cmpy_cd(+)   = 'ADB'
                   AND smm.glbl_cmpy_cd  = 'ADB'
                   AND st.ezcancelflag(+)   = '0'
                   AND smm.ezcancelflag  = '0'
                   AND st.svc_mach_mstr_pk(+) = smm.svc_mach_mstr_pk
                   AND st.cust_eml_addr IS NOT NULL
                   AND st.svc_task_sts_cd <> '99'
                )b
            ON ( e404.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET last_service_contact = b.last_service_contact ;
           dbms_output.put_line ('last_service_contact merged into contract terms table: ' || SQL%ROWCOUNT);
		   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','last_service_contact merged into contract terms table: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);

      COMMIT;

      l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

      EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

      audit_log(l_start_time
                ,l_end_time
                ,l_seq_currval              -- Sequence current value
                ,NULL
                ,l_procedure_name
               );

EXCEPTION
   WHEN OTHERS THEN
     canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
     g_errbuff := SQLERRM;
     g_retcode := '2';
     dbms_output.put_line ('Contract terms and conditions are not merged into ib table exception occured: ' || g_errbuff);
 END insert_ctr_terms_data;

PROCEDURE populate_ctr_terms_data(p_batch_number IN NUMBER)
IS
  l_start_time            VARCHAR2(50);
  l_end_time              VARCHAR2(50);
  l_procedure_name        VARCHAR2(30);
  l_seq_currval           NUMBER;
  BEGIN
    l_procedure_name := 'populate_ctr_terms_data' ;
    l_start_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

    dbms_output.put_line('Inserting contract terms and conditions data into ib table');

    MERGE INTO canon_e404_install_base_ob_tbl e404
         USING ( SELECT distinct a.svc_mach_mstr_pk
                        ,sla_response_time
                        ,service_coverage_hours
                        ,reading_cycle
                        ,consolidated_flag
                        ,staples_included_flag
                        ,meter_read_method
                        ,last_service_contact
                   FROM canon_e404_s21_ctr_terms_tbl a
                        ,canon_e404_ib_ser_to_upd_tbl e404_upd
                  WHERE 1 = 1
                    AND a.svc_mach_mstr_pk = e404_upd.instance_id
                    AND e404_upd.batch_number = p_batch_number
               )b
            ON ( e404.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET sla_response_time = b.sla_response_time
               ,service_coverage_hours = b.service_coverage_hours
               ,reading_cycle = b.reading_cycle
               ,consolidated_flag = b.consolidated_flag
               ,staples_included_flag = b.staples_included_flag
               ,meter_read_method = b.meter_read_method
               ,last_service_contact = b.last_service_contact
             ;
       
       dbms_output.put_line ('Contract terms and conditions data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT);
	   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Contract terms and conditions data merged into canon_e404_install_base_ob_tbl from S21 tables: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT;
        
       l_end_time := TO_CHAR(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');

       EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

       audit_log(l_start_time
                 ,l_end_time
                 ,l_seq_currval            -- Sequence current value
                 ,NULL
                 ,l_procedure_name
                );

 EXCEPTION
   WHEN OTHERS THEN
     canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
     g_errbuff := SQLERRM;
     g_retcode := '2';
     dbms_output.put_line ('Contract terms and conditions details are not merged into ib table exception occured: ' || g_errbuff);
END populate_ctr_terms_data;

PROCEDURE populate_service_status
   IS

      l_start_time VARCHAR2(50);
      l_end_time   VARCHAR2(50);
      l_request_id NUMBER;
      l_procedure_name VARCHAR2(60) := 'POPULATE_SERVICE_STATUS';
      l_seq_currval           NUMBER;


   BEGIN

      l_start_time := to_char(SYSDATE,'MM/DD/YYYY HH:MI:SS AM');
      --l_request_id := fnd_global.conc_request_id;

	  MERGE INTO canon_e404_install_base_ob_tbl A
	    USING(SELECT e404.instance_id, status
				FROM canon_e404_install_base_ob_tbl e404
				     ,(SELECT ser_num
							 ,svc_mach_mstr_pk instance_id
							 ,(CASE WHEN to_date(last_svc_call_dt,'YYYY-MM-DD') >= add_months(sysdate, -12) THEN 'Active' ELSE 'Inactive' END) status				
					   FROM svc_mach_mstr smm
					   WHERE smm.ezcancelflag = '0'
						 AND smm.glbl_cmpy_cd = 'ADB'
						 AND smm.svc_by_line_biz_tp_cd = 'ESS' OR
							 smm.sld_by_line_biz_tp_cd = 'ESS' ) stat
                WHERE stat.instance_id = e404.instance_id
				  AND e404.instance_usage_code = 'OUT_OF_ENTERPRISE'
                  AND NVL(stat.status,'-1') <> NVL(e404.service_is_active,'-1')
				   ) b
		ON(a.instance_id = b.instance_id )
	WHEN MATCHED THEN UPDATE
	 SET A.service_is_active = b.status 
        ,a.status_flag = (CASE WHEN salesforce_ib_id IS NULL THEN 'I' ELSE 'U' END)
        ,a.last_update_date = sysdate  ;


      --fnd_file.put_line(fnd_file.log, 'Serials updated for Service status: '||SQL%ROWCOUNT);

      COMMIT;

      l_end_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

      EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

       audit_log(l_start_time
                 ,l_end_time
                 ,l_seq_currval            -- Sequence current value
                 ,NULL
                 ,l_procedure_name
                );

   EXCEPTION
      WHEN OTHERS THEN
         ROLLBACK;
         g_error_message := sqlerrm;
         canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
   END populate_service_status;

PROCEDURE serials_to_delete
IS
  l_procedure_name   VARCHAR2(30);
  l_start_time       VARCHAR2(50);
  l_end_time         VARCHAR2(50);
  l_seq_currval      NUMBER;
  BEGIN
    l_procedure_name := 'serials_to_delete' ;
    dbms_output.put_line('Inside serials to delete procedure');
    l_start_time    := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

    INSERT INTO canon_e404_ib_ser_to_del_tbl (cbs_party_site_number
                                           ,customer_class
                                           ,configuration_id
                                           ,name
                                           ,serial_number
                                           ,instance_id
                                           ,external_reference_id
                                           ,item_code
                                           ,item_description
                                           ,install_date
                                           ,active_end_date
                                           ,ib_status
                                           ,instance_usage_code
                                           ,model_name
                                           ,pd364_segment_name
                                           ,control1
                                           ,control2
                                           ,control3
                                           ,control4
                                           ,sold_by
                                           ,serviced_by
                                           ,contract_number
                                           ,contract_header_id
                                           ,contract_expiration_date
                                           ,base_price
                                           ,base_volume
                                           ,overage
                                           ,base_volume_clr
                                           ,overage_clr
                                           ,aggregate_contract_number
                                           ,base_cycle
                                           ,contract_indicator
                                           ,rental_type
                                           ,sla_response_time
                                           ,service_coverage_hours
                                           ,reading_cycle
                                           ,consolidated_flag
                                           ,staples_included_flag
                                           ,meter_read_method
                                           ,last_service_contact
                                          -- ,ib_contact_name
                                          -- ,ib_contact_phone
                                          -- ,fax_contact
                                           ,lease_source
                                           ,lease_number
                                           ,leassor
                                           ,lease_expiration_date
                                           ,lease_upgrade_amount
                                           ,lease_term
                                           ,lease_payment_amount
                                           ,lease_disposal_date
                                           ,lease_inventory_date
                                           ,last_service_request_date
                                           ,svc_calls_last_30_days
                                           ,last_service_read
                                           ,last_service_read_date
                                           ,imageware_remote_enabled
                                           ,imageware_remote
                                           ,ugw_last_comm_date
                                           ,ugw_shared_flag
                                           ,ugw_registration_date
                                           ,ugw_deregistration_date
                                           ,ugw_last_total_reading
                                           ,ugw_last_bw_reading
                                           ,initial_total_hard_read
                                           ,latest_total_hard_read
                                           ,initial_meter_read_date
                                           ,latest_meter_read_date
                                           ,initial_bw_billed_read
                                           ,initial_color_billed_read
                                           ,latest_bw_billed_read
                                           ,latest_color_billed_read
                                           ,avg_monthly_cpy_vol_total
                                           ,avg_monthly_cpy_vol_bw
                                           ,osi_serial_number
                                           ,active_flag
                                           ,psn_when_on_lease
                                           ,status_flag
                                           ,status_message
                                           ,error_message
                                           ,salesforce_ib_id
                                           ,creation_date
                                           ,created_by
                                           ,last_update_date
                                           ,last_updated_by
										   ,service_is_active
										   ,id_synch_date
                                          )
                                   (SELECT cbs_party_site_number
                                           ,customer_class
                                           ,configuration_id
                                           ,name
                                           ,e404.serial_number
                                           ,instance_id
                                           ,external_reference_id
                                           ,item_code
                                           ,item_description
                                           ,install_date
                                           ,active_end_date
                                           ,ib_status
                                           ,instance_usage_code
                                           ,model_name
                                           ,pd364_segment_name
                                           ,control1
                                           ,control2
                                           ,control3
                                           ,control4
                                           ,sold_by
                                           ,serviced_by
                                           ,contract_number
                                           ,contract_header_id
                                           ,contract_expiration_date
                                           ,base_price
                                           ,base_volume
                                           ,overage
                                           ,base_volume_clr
                                           ,overage_clr
                                           ,aggregate_contract_number
                                           ,base_cycle
                                           ,contract_indicator
                                           ,rental_type
                                           ,sla_response_time
                                           ,service_coverage_hours
                                           ,reading_cycle
                                           ,consolidated_flag
                                           ,staples_included_flag
                                           ,meter_read_method
                                           ,last_service_contact
                                          -- ,ib_contact_name
                                          -- ,ib_contact_phone
                                          -- ,fax_contact
                                           ,lease_source
                                           ,lease_number
                                           ,leassor
                                           ,lease_expiration_date
                                           ,lease_upgrade_amount
                                           ,lease_term
                                           ,lease_payment_amount
                                           ,lease_disposal_date
                                           ,lease_inventory_date
                                           ,last_service_request_date
                                           ,svc_calls_last_30_days
                                           ,last_service_read
                                           ,last_service_read_date
                                           ,imageware_remote_enabled
                                           ,imageware_remote
                                           ,ugw_last_comm_date
                                           ,ugw_shared_flag
                                           ,ugw_registration_date
                                           ,ugw_deregistration_date
                                           ,ugw_last_total_reading
                                           ,ugw_last_bw_reading
                                           ,initial_total_hard_read
                                           ,latest_total_hard_read
                                           ,initial_meter_read_date
                                           ,latest_meter_read_date
                                           ,initial_bw_billed_read
                                           ,initial_color_billed_read
                                           ,latest_bw_billed_read
                                           ,latest_color_billed_read
                                           ,avg_monthly_cpy_vol_total
                                           ,avg_monthly_cpy_vol_bw
                                           ,e404.osi_serial_number
                                           ,active_flag
                                           ,psn_when_on_lease
                                           ,DECODE(salesforce_ib_id, NULL, 'DIP','DI')
                                           ,'Delete Inactive'
                                           ,NULL
                                           ,e404.salesforce_ib_id
                                           ,SYSDATE
                                           ,NULL
                                           ,SYSDATE
                                           ,NULL
										   ,service_is_active
										   ,id_synch_date
                                      FROM canon_e404_install_base_ob_tbl e404
                                           ,svc_mach_mstr smm
                                           ,svc_mach_mstr_sts sms
										   ,(SELECT a.serial_number, a.id
                                               FROM canon_e404_ib_ids_to_synch_tbl a
                                              WHERE a.serial_number IN(SELECT b.serial_number
                                                                         FROM canon_e404_ib_ids_to_synch_tbl b
                                                                        GROUP BY b.serial_number
                                                                       HAVING count(*) = 1
                                                                      )
                                              ) sfdc
                                     WHERE 1 = 1
                                       AND e404.serial_number = sfdc.serial_number(+)
                                       AND e404.instance_id = smm.svc_mach_mstr_pk
                                       AND smm.svc_mach_mstr_sts_cd = sms.svc_mach_mstr_sts_cd
                                       AND ( NVL(TO_DATE(smm.eff_thru_dt,'YYYY-MM-DD'), TRUNC(SYSDATE)) < TRUNC(SYSDATE)
                                            OR sms.svc_mach_mstr_sts_nm NOT IN (SELECT status                     -- ,'DUPLICATE')  -- check which statuses should be considered for deletion
                                                                                  FROM CANON_E404_INSTANCE_STATUS_V )
                                            OR UPPER(smm.ser_num) LIKE '%BAD%')
                                       )
                                  UNION
                                   (SELECT DISTINCT
                                         cbs_party_site_number
                                         ,customer_class
                                         ,configuration_id
                                         ,name
                                         ,e404.serial_number
                                         ,instance_id
                                         ,external_reference_id
                                         ,item_code
                                         ,item_description
                                         ,install_date
                                         ,active_end_date
                                         ,ib_status
                                         ,instance_usage_code
                                         ,model_name
                                         ,pd364_segment_name
                                         ,control1
                                         ,control2
                                         ,control3
                                         ,control4
                                         ,sold_by
                                         ,serviced_by
                                         ,contract_number
                                         ,contract_header_id
                                         ,contract_expiration_date
                                         ,base_price
                                         ,base_volume
                                         ,overage
                                         ,base_volume_clr
                                         ,overage_clr
                                         ,aggregate_contract_number
                                         ,base_cycle
                                         ,contract_indicator
                                         ,rental_type
                                         ,sla_response_time
                                         ,service_coverage_hours
                                         ,reading_cycle
                                         ,consolidated_flag
                                         ,staples_included_flag
                                         ,meter_read_method
                                         ,last_service_contact
                                         --,ib_contact_name
                                        -- ,ib_contact_phone
                                        -- ,fax_contact
                                         ,lease_source
                                         ,lease_number
                                         ,leassor
                                         ,lease_expiration_date
                                         ,lease_upgrade_amount
                                         ,lease_term
                                         ,lease_payment_amount
                                         ,lease_disposal_date
                                         ,lease_inventory_date
                                         ,last_service_request_date
                                         ,svc_calls_last_30_days
                                         ,last_service_read
                                         ,last_service_read_date
                                         ,imageware_remote_enabled
                                         ,imageware_remote
                                         ,ugw_last_comm_date
                                         ,ugw_shared_flag
                                         ,ugw_registration_date
                                         ,ugw_deregistration_date
                                         ,ugw_last_total_reading
                                         ,ugw_last_bw_reading
                                         ,initial_total_hard_read
                                         ,latest_total_hard_read
                                         ,initial_meter_read_date
                                         ,latest_meter_read_date
                                         ,initial_bw_billed_read
                                         ,initial_color_billed_read
                                         ,latest_bw_billed_read
                                         ,latest_color_billed_read
                                         ,avg_monthly_cpy_vol_total
                                         ,avg_monthly_cpy_vol_bw
                                         ,e404.osi_serial_number
                                         ,active_flag
                                         ,psn_when_on_lease
                                         ,DECODE(salesforce_ib_id, NULL, 'DIP','DI')
                                         ,'Delete Inactive'
                                         ,NULL
                                           ,e404.salesforce_ib_id
                                         ,SYSDATE
                                         ,NULL
                                         ,SYSDATE
                                         ,NULL
										 ,service_is_active
										 ,id_synch_date
                                    FROM canon_e404_install_base_ob_tbl e404
									     ,(SELECT a.serial_number, a.id
                                               FROM canon_e404_ib_ids_to_synch_tbl a
                                              WHERE a.serial_number IN(SELECT b.serial_number
                                                                         FROM canon_e404_ib_ids_to_synch_tbl b
                                                                        GROUP BY b.serial_number
                                                                       HAVING count(*) = 1
                                                                      )
                                              ) sfdc
                                     WHERE 1 = 1
                                       AND e404.serial_number = sfdc.serial_number(+)
                                       AND e404.serial_number IN (SELECT osi_serial_number
                                                                  FROM CANON_E504_CRXSRV_SERIAL_MAP_V)
                                   );

      dbms_output.put_line ('# of instances: ' || SQL%ROWCOUNT );

      COMMIT;

      --delete them from E404
      --DELETE canon_e404_new_ib_tbl e404

      DELETE canon_e404_install_base_ob_tbl e404
       WHERE instance_id IN(SELECT e404_del.instance_id
                              FROM canon_e404_ib_ser_to_del_tbl e404_del
                             WHERE e404_del.creation_date >= TO_DATE(l_start_time, 'MM/DD/YYYY HH:MI:SS AM')
                           );

      dbms_output.put_line ('# of record deleted from E404 Tbl based on instance id status and active end date: ' || SQL%ROWCOUNT );
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of record deleted from E404 Tbl based on instance id status and active end date: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

     -- mark the records as delete if any PSN changes
     INSERT INTO canon_e404_ib_ser_to_del_tbl (cbs_party_site_number
                                              ,customer_class
                                              ,configuration_id
                                              ,name
                                              ,serial_number
                                              ,instance_id
                                              ,external_reference_id
                                              ,item_code
                                              ,item_description
                                              ,install_date
                                              ,active_end_date
                                              ,ib_status
                                              ,instance_usage_code
                                              ,model_name
                                              ,pd364_segment_name
                                              ,control1
                                              ,control2
                                              ,control3
                                              ,control4
                                              ,sold_by
                                              ,serviced_by
                                              ,contract_number
                                              ,contract_header_id
                                              ,contract_expiration_date
                                              ,base_price
                                              ,base_volume
                                              ,overage
                                              ,base_volume_clr
                                              ,overage_clr
                                              ,aggregate_contract_number
                                              ,base_cycle
                                              ,contract_indicator
                                              ,rental_type
                                              ,sla_response_time
                                              ,service_coverage_hours
                                              ,reading_cycle
                                              ,consolidated_flag
                                              ,staples_included_flag
                                              ,meter_read_method
                                              ,last_service_contact
                                              --,ib_contact_name
                                              --,ib_contact_phone
                                             -- ,fax_contact
                                              ,lease_source
                                              ,lease_number
                                              ,leassor
                                              ,lease_expiration_date
                                              ,lease_upgrade_amount
                                              ,lease_term
                                              ,lease_payment_amount
                                              ,lease_disposal_date
                                              ,lease_inventory_date
                                              ,last_service_request_date
                                              ,svc_calls_last_30_days
                                              ,last_service_read
                                              ,last_service_read_date
                                              ,imageware_remote_enabled
                                              ,imageware_remote
                                              ,ugw_last_comm_date
                                              ,ugw_shared_flag
                                              ,ugw_registration_date
                                              ,ugw_deregistration_date
                                              ,ugw_last_total_reading
                                              ,ugw_last_bw_reading
                                              ,initial_total_hard_read
                                              ,latest_total_hard_read
                                              ,initial_meter_read_date
                                              ,latest_meter_read_date
                                              ,initial_bw_billed_read
                                              ,initial_color_billed_read
                                              ,latest_bw_billed_read
                                              ,latest_color_billed_read
                                              ,avg_monthly_cpy_vol_total
                                              ,avg_monthly_cpy_vol_bw
                                              ,active_flag
                                              ,psn_when_on_lease
                                              ,status_flag
                                              ,status_message
                                              ,error_message
                                              ,salesforce_ib_id
                                              ,creation_date
                                              ,created_by
                                              ,last_update_date
                                              ,last_updated_by
											  ,service_is_active
											  ,id_synch_date
                                             )
                                      (SELECT cbs_party_site_number
                                              ,customer_class
                                              ,configuration_id
                                              ,name
                                              ,e404.serial_number
                                              ,instance_id
                                              ,external_reference_id
                                              ,item_code
                                              ,item_description
                                              ,install_date
                                              ,active_end_date
                                              ,ib_status
                                              ,instance_usage_code
                                              ,model_name
                                              ,pd364_segment_name
                                              ,control1
                                              ,control2
                                              ,control3
                                              ,control4
                                              ,sold_by
                                              ,serviced_by
                                              ,contract_number
                                              ,contract_header_id
                                              ,contract_expiration_date
                                              ,base_price
                                              ,base_volume
                                              ,overage
                                              ,base_volume_clr
                                              ,overage_clr
                                              ,aggregate_contract_number
                                              ,base_cycle
                                              ,contract_indicator
                                              ,rental_type
                                              ,sla_response_time
                                              ,service_coverage_hours
                                              ,reading_cycle
                                              ,consolidated_flag
                                              ,staples_included_flag
                                              ,meter_read_method
                                              ,last_service_contact
                                             -- ,ib_contact_name
                                            --  ,ib_contact_phone
                                             -- ,fax_contact
                                              ,lease_source
                                              ,lease_number
                                              ,leassor
                                              ,lease_expiration_date
                                              ,lease_upgrade_amount
                                              ,lease_term
                                              ,lease_payment_amount
                                              ,lease_disposal_date
                                              ,lease_inventory_date
                                              ,last_service_request_date
                                              ,svc_calls_last_30_days
                                              ,last_service_read
                                              ,last_service_read_date
                                              ,imageware_remote_enabled
                                              ,imageware_remote
                                              ,ugw_last_comm_date
                                              ,ugw_shared_flag
                                              ,ugw_registration_date
                                              ,ugw_deregistration_date
                                              ,ugw_last_total_reading
                                              ,ugw_last_bw_reading
                                              ,initial_total_hard_read
                                              ,latest_total_hard_read
                                              ,initial_meter_read_date
                                              ,latest_meter_read_date
                                              ,initial_bw_billed_read
                                              ,initial_color_billed_read
                                              ,latest_bw_billed_read
                                              ,latest_color_billed_read
                                              ,avg_monthly_cpy_vol_total
                                              ,avg_monthly_cpy_vol_bw
                                              ,active_flag
                                              ,e404.psn_when_on_lease
                                              ,DECODE(salesforce_ib_id, NULL, 'DPP','DPP') 
                                              ,'Delete for PSN' 
                                              ,NULL
                                              ,e404.salesforce_ib_id
                                              ,SYSDATE
                                              ,NULL
                                              ,SYSDATE
                                              ,NULL
											  ,service_is_active
											  ,id_synch_date
                                         FROM canon_e404_install_base_ob_tbl e404
                                              ,svc_mach_mstr smm
											  ,(SELECT a.serial_number, a.id
                                               FROM canon_e404_ib_ids_to_synch_tbl a
                                              WHERE a.serial_number IN(SELECT b.serial_number
                                                                         FROM canon_e404_ib_ids_to_synch_tbl b
                                                                        GROUP BY b.serial_number
                                                                       HAVING count(*) = 1
                                                                      )
                                              ) sfdc
                                     WHERE 1 = 1
                                       AND e404.serial_number = sfdc.serial_number(+)
                                        AND e404.instance_id = smm.svc_mach_mstr_pk
                                          AND e404.cbs_party_site_number <> smm.ind_cur_loc_num);

    dbms_output.put_line('# of DP serials : ' || SQL%ROWCOUNT );
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of DP serials : ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
    COMMIT;

    --mark the records to delete for any 'CBS'(virtual) writing territory PSN changes

    UPDATE canon_e404_ib_ser_to_del_tbl e404_del
       SET status_flag = 'DP'
     WHERE salesforce_ib_id IS NOT NULL
       AND e404_del.creation_date >= TO_DATE(l_start_time, 'MM/DD/YYYY HH:MI:SS AM')
       AND e404_del.instance_id IN (SELECT instance_id
                                      FROM svc_mach_mstr smm
                                           ,canon_e404_sf_acct_mapping_tbl e404
                                     WHERE smm.svc_mach_mstr_pk = e404_del.instance_id
                                       AND e404.party_site_number = smm.ind_cur_loc_num
                                       AND (e404.writing_territory LIKE 'CBS%'
                                            OR e404.writing_territory LIKE 'CSA%'));

    dbms_output.put_line('# of DP serials for PSN changes with virtual territory : ' || SQL%ROWCOUNT );
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of DP serials for PSN changes with virtual territory : ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
    COMMIT;

   --delete them from E404

   DELETE canon_e404_install_base_ob_tbl e404
    WHERE instance_id IN(SELECT e404_del.instance_id
                           FROM canon_e404_ib_ser_to_del_tbl e404_del
                          WHERE e404_del.creation_date >= TO_DATE(l_start_time, 'MM/DD/YYYY HH:MI:SS AM')
                           );

    dbms_output.put_line('# of record deleted from E404 Tbl for PSN not matching: ' || SQL%ROWCOUNT );
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of record deleted from E404 Tbl for PSN not matching: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
    COMMIT;

    -- mark the records as delete if virtual

    INSERT INTO canon_e404_ib_ser_to_del_tbl (cbs_party_site_number
                                           ,customer_class
                                           ,configuration_id
                                           ,name
                                           ,serial_number
                                           ,instance_id
                                           ,external_reference_id
                                           ,item_code
                                           ,item_description
                                           ,install_date
                                           ,active_end_date
                                           ,ib_status
                                           ,instance_usage_code
                                           ,model_name
                                           ,pd364_segment_name
                                           ,control1
                                           ,control2
                                           ,control3
                                           ,control4
                                           ,sold_by
                                           ,serviced_by
                                           ,contract_number
                                           ,contract_header_id
                                           ,contract_expiration_date
                                           ,base_price
                                           ,base_volume
                                           ,overage
                                           ,base_volume_clr
                                           ,overage_clr
                                           ,aggregate_contract_number
                                           ,base_cycle
                                           ,contract_indicator
                                           ,rental_type
                                           ,sla_response_time
                                           ,service_coverage_hours
                                           ,reading_cycle
                                           ,consolidated_flag
                                           ,staples_included_flag
                                           ,meter_read_method
                                           ,last_service_contact
                                          -- ,ib_contact_name
                                          -- ,ib_contact_phone
                                          -- ,fax_contact
                                           ,lease_source
                                           ,lease_number
                                           ,leassor
                                           ,lease_expiration_date
                                           ,lease_upgrade_amount
                                           ,lease_term
                                           ,lease_payment_amount
                                           ,lease_disposal_date
                                           ,lease_inventory_date
                                           ,last_service_request_date
                                           ,svc_calls_last_30_days
                                           ,last_service_read
                                           ,last_service_read_date
                                           ,imageware_remote_enabled
                                           ,imageware_remote
                                           ,ugw_last_comm_date
                                           ,ugw_shared_flag
                                           ,ugw_registration_date
                                           ,ugw_deregistration_date
                                           ,ugw_last_total_reading
                                           ,ugw_last_bw_reading
                                           ,initial_total_hard_read
                                           ,latest_total_hard_read
                                           ,initial_meter_read_date
                                           ,latest_meter_read_date
                                           ,initial_bw_billed_read
                                           ,initial_color_billed_read
                                           ,latest_bw_billed_read
                                           ,latest_color_billed_read
                                           ,avg_monthly_cpy_vol_total
                                           ,avg_monthly_cpy_vol_bw
                                           ,active_flag
                                           ,psn_when_on_lease
                                           ,status_flag
                                           ,status_message
                                           ,error_message
                                           ,salesforce_ib_id
                                           ,creation_date
                                           ,created_by
                                           ,last_update_date
                                           ,last_updated_by
										   ,service_is_active
										   ,id_synch_date
                                          )
                                   (SELECT cbs_party_site_number
                                           ,customer_class
                                           ,configuration_id
                                           ,name
                                           ,e404.serial_number
                                           ,instance_id
                                           ,external_reference_id
                                           ,item_code
                                           ,item_description
                                           ,install_date
                                           ,active_end_date
                                           ,ib_status
                                           ,instance_usage_code
                                           ,model_name
                                           ,pd364_segment_name
                                           ,control1
                                           ,control2
                                           ,control3
                                           ,control4
                                           ,sold_by
                                           ,serviced_by
                                           ,contract_number
                                           ,contract_header_id
                                           ,contract_expiration_date
                                           ,base_price
                                           ,base_volume
                                           ,overage
                                           ,base_volume_clr
                                           ,overage_clr
                                           ,aggregate_contract_number
                                           ,base_cycle
                                           ,contract_indicator
                                           ,rental_type
                                           ,sla_response_time
                                           ,service_coverage_hours
                                           ,reading_cycle
                                           ,consolidated_flag
                                           ,staples_included_flag
                                           ,meter_read_method
                                           ,last_service_contact
                                          -- ,ib_contact_name
                                          -- ,ib_contact_phone
                                          -- ,fax_contact
                                           ,lease_source
                                           ,lease_number
                                           ,leassor
                                           ,e404.lease_expiration_date
                                           ,lease_upgrade_amount
                                           ,lease_term
                                           ,lease_payment_amount
                                           ,lease_disposal_date
                                           ,lease_inventory_date
                                           ,last_service_request_date
                                           ,svc_calls_last_30_days
                                           ,last_service_read
                                           ,last_service_read_date
                                           ,imageware_remote_enabled
                                           ,imageware_remote
                                           ,ugw_last_comm_date
                                           ,ugw_shared_flag
                                           ,ugw_registration_date
                                           ,ugw_deregistration_date
                                           ,ugw_last_total_reading
                                           ,ugw_last_bw_reading
                                           ,initial_total_hard_read
                                           ,latest_total_hard_read
                                           ,initial_meter_read_date
                                           ,latest_meter_read_date
                                           ,initial_bw_billed_read
                                           ,initial_color_billed_read
                                           ,latest_bw_billed_read
                                           ,latest_color_billed_read
                                           ,avg_monthly_cpy_vol_total
                                           ,avg_monthly_cpy_vol_bw
                                           ,active_flag
                                           ,psn_when_on_lease
                                           ,DECODE(salesforce_ib_id, NULL, 'DVP','DV')
                                           ,'Delete for Virtual'
                                           ,NULL
                                           ,e404.salesforce_ib_id
                                           ,SYSDATE
                                           ,NULL
                                           ,SYSDATE
                                           ,NULL
										   ,service_is_active
										   ,id_synch_date
                                     FROM canon_e404_install_base_ob_tbl e404
                                          ,canon_e404_sf_acct_mapping_tbl acct
										  ,(SELECT a.serial_number, a.id
                                               FROM canon_e404_ib_ids_to_synch_tbl a
                                              WHERE a.serial_number IN(SELECT b.serial_number
                                                                         FROM canon_e404_ib_ids_to_synch_tbl b
                                                                        GROUP BY b.serial_number
                                                                       HAVING count(*) = 1
                                                                      )
                                              ) sfdc
                                     WHERE 1 = 1
                                       AND e404.serial_number = sfdc.serial_number(+)
                                       AND e404.cbs_party_site_number = acct.party_site_number
                                       AND ( acct.writing_territory like 'CBS%'
                                         OR acct.writing_territory like 'CSA%')) ;

     dbms_output.put_line('# of DV serials : ' || SQL%ROWCOUNT );
	 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of DV serials : ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
     COMMIT;

     --delete them from E404

     DELETE canon_e404_install_base_ob_tbl e404
      WHERE instance_id IN(SELECT e404_del.instance_id
                             FROM canon_e404_ib_ser_to_del_tbl e404_del
                            WHERE e404_del.creation_date >= TO_DATE(l_start_time, 'MM/DD/YYYY HH:MI:SS AM')
                           );

      dbms_output.put_line('# of record deleted from E404 Tbl for Virtual Accounts: ' || SQL%ROWCOUNT );
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of record deleted from E404 Tbl for Virtual Accounts: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;

      l_end_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

      EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

      audit_log(l_start_time
                ,l_end_time
                ,l_seq_currval          -- Sequence current value
                ,NULL
                ,l_procedure_name
               );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line('Exception occured in Serials to delete procedure: ' || g_errbuff);
END serials_to_delete;

PROCEDURE serials_to_update
IS
 l_dynsql           VARCHAR2(32767);
 l_start_time       VARCHAR2(50);
 l_end_time         VARCHAR2(50);
 l_last_run_date    DATE;
 l_procedure_name   VARCHAR2(30);
 l_seq_currval      NUMBER;

BEGIN
  l_procedure_name  := 'serials_to_update' ;
  l_start_time      := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

/*  BEGIN
    SELECT val76
      INTO l_last_run_date
      FROM canon_s21_cd_val_tbl val
           ,canon_s21_cd_tbl code
     WHERE 1 = 1
       AND val.val1 = 'CANON_E404_SF_IB_INT_RUN_DATE'
       AND val.cd_id = code.cd_id
       AND code.cd_name = 'PROFILES_BY_DATE'
       AND SYSDATE BETWEEN NVL(val.start_date_active,SYSDATE-1) AND NVL(val.end_date_active,SYSDATE+1);
  EXCEPTION
    WHEN OTHERS
    THEN
      l_last_run_date := NULL;
  END; */

  BEGIN
    SELECT TO_DATE(profile_value,'YYYYMMDDhh24miss')
      INTO l_last_run_date
      FROM canon_e001_profiles_tbl cp
           ,canon_e001_profile_values_tbl cpv
     WHERE 1 = 1
       AND cp.profile_name = 'CANON_E404_SF_IB_INT_RUN_DATE'
       AND cp.profile_id = cpv.profile_id
       AND cpv.is_active = 'Y'
       AND SYSDATE BETWEEN NVL(cpv.start_date,SYSDATE-1) AND NVL(cpv.end_date,SYSDATE+1);

       dbms_output.put_line(' Profile date value in S21 ' || l_last_run_date);

  EXCEPTION
    WHEN OTHERS
    THEN
      dbms_output.put_line(' Exception occured in serials to update procedure to get profile date value ' || SQLERRM);
      l_last_run_date := NULL;
  END;

  -- for new serials

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a
--         USING (SELECT smm.svc_mach_mstr_pk
--                       ,smm.ser_num
--                       ,'INS OR HPSUPD' status_message
--                  FROM ds_acct_cust dac
--                       ,svc_mach_mstr smm
--                       ,ds_mdse_info dmi
--                       ,svc_mach_mstr_sts smms
--                       ,svc_config_mstr scm
--                       ,ds_mdl dm
--                       ,svc_seg ss
--                       ,ds_acct_cls dacl
--                       ,canon_e404_install_base_ob_tbl  e404
--                 WHERE 1=1
--                   AND (svc_by_line_biz_tp_cd = 'ESS' OR
--                        sld_by_line_biz_tp_cd = 'ESS')
--                   AND dac.loc_num = smm.cur_loc_num
--                   AND e404.instance_id(+) = smm.svc_mach_mstr_pk
--                   AND e404.instance_id IS NULL
--                   AND UPPER(smm.ser_num) NOT LIKE '%BAD%'
--                   AND smm.ser_num IS NOT NULL
--                   AND dac.ds_acct_cls_cd = dacl.ds_acct_cls_cd
--                   AND smm.mdse_cd = dmi.mdse_cd
--                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
--                   AND smm.svc_mach_mstr_pk = scm.svc_mach_mstr_pk
--                   AND scm.mdl_id = dm.mdl_id
--                   AND dm.svc_seg_cd = ss.svc_seg_cd
--                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT val1
--                                                              FROM canon_s21_cd_val_tbl val
--                                                                   ,canon_s21_cd_tbl code
--                                                             WHERE 1 = 1
--                                                               AND val.cd_id = code.cd_id
--                                                               AND code.cd_name = 'CANON_E404_INSTANCE_STATUS' )
--                   AND dac.rgtn_sts_cd NOT IN ('P99')
--                   AND dac.ezcancelflag   = 0
--                   AND smm.ezcancelflag   = 0
--                   AND dmi.ezcancelflag   = 0
--                   AND smms.ezcancelflag  = 0
--                   AND scm.ezcancelflag   = 0
--                   AND dm.ezcancelflag    = 0
--                   AND ss.ezcancelflag    = 0
--                   AND dacl.ezcancelflag  = 0
--                   AND dac.glbl_cmpy_cd   = 'ADB'
--                   AND smm.glbl_cmpy_cd   = 'ADB'
--                   AND dmi.glbl_cmpy_cd   = 'ADB'
--                   AND smms.glbl_cmpy_cd  = 'ADB'
--                   AND scm.glbl_cmpy_cd   = 'ADB'
--                   AND dm.glbl_cmpy_cd    = 'ADB'
--                   AND ss.glbl_cmpy_cd    = 'ADB'
--                   AND dacl.glbl_cmpy_cd  = 'ADB'
--                  ) b
         USING (SELECT distinct smm.svc_mach_mstr_pk
                       ,smm.ser_num
                       ,'INS OR HPSUPD' status_message
--                  FROM ds_acct_cust dac -- DB Change
                  FROM sell_to_cust dac
                       ,svc_mach_mstr smm
                       ,mdse dmi   -- DB CHanges
                       ,svc_mach_mstr_sts smms
                       ,svc_config_mstr scm
                       ,ds_mdl dm
                       ,svc_seg ss
                       ,ds_acct_cls dacl
                       ,canon_e404_install_base_ob_tbl  e404
                       ,pty_loc_wrk plw
                       ,acct_loc al
                 WHERE 1=1
                   AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                   AND smm.ind_cur_loc_num = plw.loc_num
                   AND plw.loc_num = al.loc_num
                   AND al.ds_acct_num = dac.sell_to_cust_cd -- DB Change
--                   AND dac.loc_num = smm.cur_loc_num
                   AND e404.instance_id(+) = smm.svc_mach_mstr_pk
                   AND e404.instance_id IS NULL
                   AND UPPER(smm.ser_num) NOT LIKE '%BAD%'
                   AND smm.ser_num IS NOT NULL
                   AND dac.ds_acct_cls_cd = dacl.ds_acct_cls_cd
                   AND smm.mdse_cd = dmi.mdse_cd(+)
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND smm.svc_mach_mstr_pk = scm.svc_mach_mstr_pk(+)
                   AND scm.mdl_id = dm.mdl_id(+)
                   AND dm.svc_seg_cd = ss.svc_seg_cd(+)
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V
                                                            )
                   AND dac.rgtn_sts_cd NOT IN ('P99')
                   AND dac.ezcancelflag   = 0
                   AND smm.ezcancelflag   = 0
                   AND dmi.ezcancelflag(+)   = 0
                   AND smms.ezcancelflag  = 0
                   AND scm.ezcancelflag(+)   = 0
                   AND dm.ezcancelflag(+)    = 0
                   AND ss.ezcancelflag(+)   = 0
                   AND dacl.ezcancelflag  = 0
                   AND dac.glbl_cmpy_cd   = 'ADB'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND dmi.glbl_cmpy_cd(+)   = 'ADB'
                   AND smms.glbl_cmpy_cd  = 'ADB'
                   AND scm.glbl_cmpy_cd(+)  = 'ADB'
                   AND dm.glbl_cmpy_cd(+)    = 'ADB'
                   AND ss.glbl_cmpy_cd(+)   = 'ADB'
                   AND dacl.glbl_cmpy_cd  = 'ADB'
                   AND plw.glbl_cmpy_cd = 'ADB'
                   AND plw.ezcancelflag = '0'
                   AND al.glbl_cmpy_cd = 'ADB'
                   AND al.ezcancelflag = '0') b
            ON (a.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED
          THEN
        UPDATE SET a.status_message = status_message || '|INS OR HPSUPD'
          WHEN NOT MATCHED
          THEN
            INSERT (a.serial_number, a.instance_id, status_message)
               VALUES (b.ser_num, b.svc_mach_mstr_pk, 'INS OR HPSUPD');

       dbms_output.put_line('Records for INS OR HPSUPD changes: ' || SQL%ROWCOUNT);
	    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for INS OR HPSUPD changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

   -- IB change serials

   INSERT INTO canon_e404_ib_ser_to_upd_tbl (serial_number
                                             ,instance_id
                                             ,status_message)
                                     (SELECT distinct e404.serial_number
                                             ,e404.instance_id
                                             ,'IB'
                                        FROM svc_mach_mstr smm
                                             ,canon_e404_install_base_ob_tbl e404
                                       WHERE TO_DATE(substr(smm.EZUPTIME,1,14),'RRRRMMDDhh24miss') >= l_last_run_date   -- check this date
                                         AND smm.ser_num IS NOT NULL
                                         AND smm.svc_mach_mstr_pk = e404.instance_id
                                      ) ;

         dbms_output.put_line('Records for IB changes: ' || SQL%ROWCOUNT);
		  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for IB changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
         COMMIT;

    -- Contract Changes

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a1
         USING (SELECT distinct a.serial_number
                       ,a.svc_mach_mstr_pk
                  FROM canon_e404_s21_ctr_terms_tbl a
                       ,canon_e404_install_base_ob_tbl b
                 WHERE a.svc_mach_mstr_pk = b.instance_id
                   AND (NVL (a.contract_header_id, -999) <> NVL(b.contract_header_id, -999)
                     OR NVL (a.contract_number, '-999') <>  NVL (b.contract_number, '-999')
                     OR NVL (a.contract_expiration_date, SYSDATE) <> NVL (b.contract_expiration_date, SYSDATE)
                     OR NVL (a.base_price, -999) <> NVL (b.base_price, -999)
                     OR NVL (a.base_cycle, '-9') <> NVL (b.base_cycle, '-9')
                     OR NVL (a.rental_type, '-999') <> NVL (b.rental_type, '-999')
                 --  OR NVL (a.aggregate_contract_number, '-999') <> NVL (b.aggregate_contract_number, '-999')
                     OR NVL (a.base_volume, -999) <> NVL (b.base_volume, -999)
                     OR NVL (a.overage, -999) <> NVL (b.overage, -999)
                     OR NVL (a.base_volume_clr, -999) <> NVL (b.base_volume_clr, -999)
                     OR NVL (a.overage_clr, -999) <> NVL (b.overage_clr, -999))
                   AND a.creation_date > l_last_run_date
                  ) b
            ON (a1.instance_id = b.svc_mach_mstr_pk)
          WHEN MATCHED
          THEN
             UPDATE SET a1.status_message = a1.status_message || '|Contract'
          WHEN NOT MATCHED
          THEN
             INSERT (a1.serial_number
                     ,a1.instance_id
                     ,a1.status_message)
             VALUES (b.serial_number
                     ,b.svc_mach_mstr_pk
                     ,'Contract ');

       dbms_output.put_line('Records for contract changes: ' || SQL%ROWCOUNT);
	    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for contract changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

    -- Contracts not in CTR table

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a1
         USING (SELECT distinct serial_number, instance_id
                  FROM canon_e404_install_base_ob_tbl e404
                 WHERE NOT EXISTS (SELECT 1
                                     FROM canon_e404_s21_ctr_terms_tbl ctr
                                    WHERE e404.instance_id = ctr.svc_mach_mstr_pk
                                   )
                   AND E404.contract_number IS NOT NULL
                ) b
            ON (a1.instance_id = b.instance_id)
          WHEN MATCHED
          THEN
            UPDATE
               SET a1.status_message = a1.status_message || '|Contracts NOT IN CTR table'
          WHEN NOT MATCHED
          THEN
            INSERT (a1.serial_number
                    ,a1.instance_id
                    ,a1.status_message)
            VALUES (b.serial_number
                    ,b.instance_id
                    ,'Contracts NOT IN CTR table');

     dbms_output.put_line('Records for contract changes(2): ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for contract changes(2): ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
     COMMIT;

    -- lease serial changes

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a
         USING (SELECT distinct e215.serial_number
                       ,e404.instance_id
                  FROM canon_e215_cfs_upg_dtl_tbl e215
                       ,canon_e404_install_base_ob_tbl e404
                 WHERE e404.serial_number = e215.serial_number
                   AND e215.serial_number != 'NA'
                   AND e215.serial_number != 'N/A'
                   AND NVL(e215.overage_tier_key,1) = 1
                   AND e215.last_update_date >= l_last_run_date
              /*  UNION
                SELECT e215.serial_no
                       ,e404.instance_id
                  FROM canon_e215b_citg_lease_tbl e215   -- check this table with Radhika
                       ,canon_e404_install_base_ob_tbl e404
                 WHERE e404.serial_number = e215.serial_no
                   AND e215.last_update_date >= l_last_run_date */
                ) b
            ON (a.instance_id = b.instance_id)
          WHEN MATCHED
          THEN
            UPDATE SET a.status_message = status_message || '|Lease'
          WHEN NOT MATCHED
          THEN
            INSERT (a.serial_number
                    ,a.instance_id
                    ,a.status_message)
            VALUES (b.serial_number
                    ,b.instance_id
                    ,'Lease');

        dbms_output.put_line('Records for Lease data changes: ' || SQL%ROWCOUNT);
		 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for Lease data changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT;

    -- UGW serial changes
	
	MERGE INTO canon_e404_ib_ser_to_upd_tbl a
         USING (SELECT distinct smm.svc_mach_mstr_pk instance_id
                       ,SMM.SER_NUM SERIAL_NUMBER
                       ,(CASE WHEN (ic.iwr_cond_cd IS NOT NULL AND ic.IWR_COND_DESC_TXT IS NOT NULL) THEN ic.iwr_cond_cd || ' - ' || ic.IWR_COND_DESC_TXT ELSE NULL END)imageware_remote
                  FROM  mdse dmi
                        ,svc_mach_mstr smm
--                        ,iwr_rgtn_cond irc
                        ,iwr_cond ic
                        ,canon_e404_install_base_ob_tbl e404
                        ,svc_mach_mstr_sts smms
                 WHERE 1=1
                   AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                   AND dmi.mdse_cd(+) = smm.mdse_cd
                   AND e404.serial_number = smm.ser_num
--                   AND NVL(e404.imageware_remote_enabled, 'N') <> 'Y'
                   AND ic.iwr_cond_cd(+) = smm.iwr_cond_cd
--                   AND smm.svc_mach_mstr_pk = irc.svc_mach_mstr_pk(+)
                   AND NVL(e404.imageware_remote,'-1') <> NVL((ic.iwr_cond_cd || ic.IWR_COND_DESC_TXT),'-1')
                   AND NVL(TO_DATE(smm.eff_thru_dt,'YYYY-MM-DD'), TRUNC(SYSDATE-1)) < TRUNC(SYSDATE)
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V                               )
                   AND smm.glbl_cmpy_cd  = 'ADB'
                   AND dmi.glbl_cmpy_cd(+)  = 'ADB'
--                   AND irc.glbl_cmpy_cd(+)  = 'ADB'
                   AND ic.glbl_cmpy_cd(+)   = 'ADB'
                   AND smm.ezcancelflag  = 0
                   AND dmi.ezcancelflag(+)  = 0
--                   AND irc.ezcancelflag(+)  = 0
                   AND ic.ezcancelflag(+)   = 0
                 ) b
            ON (a.instance_id = b.instance_id)
          WHEN MATCHED
          THEN
            UPDATE SET a.status_message = status_message || '|UGW'
          WHEN NOT MATCHED
          THEN
            INSERT (serial_number
                    ,instance_id
                    ,status_message)
            VALUES (b.serial_number
                    ,b.instance_id
                    ,'UGW');

       dbms_output.put_line('Records for UGW data changes: ' || SQL%ROWCOUNT);
       canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Records for UGW data changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;
	   
     -- Service call changes

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a
         USING (SELECT distinct smm.svc_mach_mstr_pk instance_id
                       ,smm.ser_num serial_number
                       ,smm.last_svc_call_dt last_service_request_date
                       ,svc_call.svc_calls_last_30_days
                       ,smm.last_svc_call_mtr_cnt last_service_read
                       ,smm.last_svc_call_visit_dt last_service_read_date
                  FROM svc_mach_mstr smm
                       ,canon_e404_install_base_ob_tbl e404
                       ,svc_mach_mstr_sts smms
                       ,(SELECT COUNT(1) svc_calls_last_30_days
                                ,svc_mach_mstr_pk
                           FROM fsr d
                          WHERE d.ezcancelflag = '0'
                            AND d.ezintime >= TO_CHAR(TRUNC(SYSDATE - 30), 'yyyymmddhh24miss') || '000'
                          GROUP BY svc_mach_mstr_pk) svc_call
                 WHERE 1=1
                   AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                   AND e404.serial_number = smm.ser_num
                   AND svc_call.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                   
                   AND ( TO_DATE(smm.last_svc_call_dt,'YYYYMMDD') <>  NVL ( e404.last_service_request_date, TO_DATE(smm.last_svc_call_dt,'YYYYMMDD')-1)
                         OR svc_call.svc_calls_last_30_days <> e404.svc_calls_last_30_days)
                   AND NVL(TO_DATE(smm.eff_thru_dt,'YYYYMMDD'), TRUNC(SYSDATE-1)) < TRUNC(SYSDATE)
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V
                                                           )
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = 0
                ) b
            ON (a.instance_id = b.instance_id)
          WHEN MATCHED
          THEN
        UPDATE SET a.status_message = status_message || '|SVC Date'
          WHEN NOT MATCHED
          THEN
        INSERT (serial_number
                ,instance_id
                ,status_message)
        VALUES (b.serial_number
                ,b.instance_id
                ,'SVC Date');

       dbms_output.put_line('Records for SVC Date data changes: ' || SQL%ROWCOUNT);
	    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for SVC Date data changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

   -- crxsvc serial changes

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a
         USING (SELECT DISTINCT
                       c.osi_serial_number serial_number
                       ,e404.instance_id
                  FROM CANON_E504_CRXSRV_SERIAL_MAP_V c
                       ,canon_e404_install_base_ob_tbl e404
                 WHERE 1 = 1
                   AND c.osi_serial_number = e404.serial_number
                   AND c.end_date > l_last_run_date
                ) b
            ON (a.instance_id = b.instance_id)
          WHEN MATCHED
          THEN
        UPDATE SET a.status_message = status_message || '|CRX_SVC'
          WHEN NOT MATCHED
          THEN
        INSERT (serial_number
                ,instance_id
                ,status_message)
        VALUES (b.serial_number
                ,b.instance_id
                ,'CRX_SVC');

       dbms_output.put_line('Records for CRX_SVC data changes: ' || SQL%ROWCOUNT);
	    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for CRX_SVC data changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
       COMMIT;

   -- segment serial changes
   MERGE INTO canon_e404_ib_ser_to_upd_tbl a
        USING (SELECT DISTINCT e404.serial_number
                             , e404.instance_id
                    FROM svc_mach_mstr smm
                         ,svc_seg ss
                         ,ds_mdl  dm
                         ,svc_config_mstr scm
                         ,svc_mach_mstr_sts smms
                         ,canon_e404_install_base_ob_tbl e404
                   WHERE e404.serial_number = smm.ser_num
                     AND e404.pd364_segment_name <> ss.svc_seg_cd
                     AND smm.svc_mach_mstr_pk = scm.svc_mach_mstr_pk
                     AND dm.svc_seg_cd = ss.svc_seg_cd(+)
                     AND scm.mdl_id = dm.mdl_id
                     AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                     AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                     AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                                FROM CANON_E404_INSTANCE_STATUS_V
                                                              )
                     AND scm.ezcancelflag = 0
                     AND smm.ezcancelflag = 0
                     AND dm.ezcancelflag = 0
                     AND ss.ezcancelflag(+) = 0
                     AND ss.glbl_cmpy_cd(+) = 'ADB'
               ) b
           ON (a.instance_id = b.instance_id)
         WHEN MATCHED
         THEN
         UPDATE SET a.status_message = status_message || '|Segment'
           WHEN NOT MATCHED
           THEN
             INSERT (a.serial_number
                     ,a.instance_id
                     ,status_message)
             VALUES (b.serial_number
                     ,b.instance_id
                     ,'Segment');

        dbms_output.put_line('Records for segment data changes: ' || SQL%ROWCOUNT);
		 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for segment data changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT;

/*
    -- Ib Contact Chnages

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a
         USING (SELECT distinct smm.svc_mach_mstr_pk instance_id
                       ,smm.ser_num serial_number
                  FROM ctac_psn ctpn
                       ,ds_ctac_pnt dcpt
                       ,svc_mach_ctac_psn smcp
                       ,svc_mach_mstr smm
                       ,canon_e404_install_base_ob_tbl e404
                       ,svc_mach_mstr_sts smms
                       ,ds_ctac_psn_reln dcpr
                 WHERE 1=1
                   AND (svc_by_line_biz_tp_cd = 'ESS' OR
                        sld_by_line_biz_tp_cd = 'ESS')
                   AND e404.instance_id = smm.svc_mach_mstr_pk
                   AND (NVL((ctpn.ctac_psn_first_nm || ' ' || ctpn.ctac_psn_last_nm),'-99') <> NVL(e404.ib_contact_name,'-99') OR
						NVL(dcpt.ds_ctac_pnt_val_txt, '-99') <> NVL(ib_contact_phone,'-99') OR
                        NVL(ctpn.ctac_psn_fax_num,'-99') <> NVL(fax_contact,'-99'))-- check this
                   AND dcpt.ds_ctac_pnt_tp_cd IN ('01','02','03')
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER(smms.svc_mach_mstr_sts_nm) IN (SELECT status
                                                              FROM CANON_E404_INSTANCE_STATUS_V)
                   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(smm.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(smm.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                   AND smcp.ds_ctac_pnt_pk = dcpt.ds_ctac_pnt_pk
                   AND smcp.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                   AND dcpt.ctac_psn_pk   = ctpn.ctac_psn_pk
                   AND ctpn.ctac_psn_pk = dcpr.ctac_psn_pk
                   AND dcpr.ctac_tp_cd    = 'DELIV_INSTALL'
                   AND smm.ezcancelflag   = '0'
                   AND ctpn.ezcancelflag  = '0'
                   AND dcpt.ezcancelflag  = '0'
                   AND smcp.ezcancelflag  = '0'
                   AND dcpr.ezcancelflag = '0'
                   AND smm.glbl_cmpy_cd   = 'ADB'
                   AND dcpt.glbl_cmpy_cd  = 'ADB'
                   AND ctpn.glbl_cmpy_cd  = 'ADB'
                   AND smcp.glbl_cmpy_cd  = 'ADB'
                   AND dcpr.glbl_cmpy_cd = 'ADB'
                 ) b
            ON (a.instance_id = b.instance_id)
          WHEN MATCHED
          THEN
        UPDATE SET a.status_message = status_message || '|IB Contact'
          WHEN NOT MATCHED
          THEN
        INSERT (serial_number
                ,instance_id
                ,status_message)
        VALUES (b.serial_number
                ,b.instance_id
                ,'IB Contact');

       dbms_output.put_line('Records for IB Contact changes: ' || SQL%ROWCOUNT);
       COMMIT;
****/

    -- Meter Reads changes

    MERGE INTO canon_e404_ib_ser_to_upd_tbl a
         USING (SELECT DISTINCT
                       e404.serial_number
                       ,e404.instance_id
                  FROM canon_e404_s21_meter_reads_tbl a
                       ,canon_e404_install_base_ob_tbl e404
                 WHERE a.svc_mach_mstr_pk = e404.instance_id
                   AND (NVL(a.initial_total_hard_read, -999) <> NVL(e404.initial_total_hard_read, -999)
                        OR NVL(a.latest_total_hard_read, -999) <> NVL(e404.latest_total_hard_read, -999)
                        OR NVL(a.initial_meter_read_date, SYSDATE) <> NVL(e404.initial_meter_read_date, SYSDATE)
                        OR NVL(a.latest_meter_read_date, SYSDATE) <> NVL(e404.latest_meter_read_date, SYSDATE)
                        OR NVL(a.initial_bw_billed_read, -999) <> NVL(e404.initial_bw_billed_read, -999)
                        OR NVL(a.initial_color_billed_read, -999) <> NVL(e404.initial_color_billed_read, -999)
                        OR NVL(a.latest_bw_billed_read, -999) <> NVL(e404.latest_bw_billed_read, -999)
                        OR NVL(a.latest_color_billed_read, -999) <> NVL(e404.latest_color_billed_read, -999)
                        OR NVL(a.avg_monthly_cpy_vol_total, -999) <> NVL(e404.avg_monthly_cpy_vol_total, -999)
                        OR NVL(a.avg_monthly_cpy_vol_bw, -999) <> NVL(e404.avg_monthly_cpy_vol_bw, -999))
                   AND a.creation_date > l_last_run_date
                ) b
            ON (a.instance_id = b.instance_id)
          WHEN MATCHED
          THEN
        UPDATE SET a.status_message = status_message || '|Meter Read'
          WHEN NOT MATCHED
          THEN
        INSERT (serial_number
                ,instance_id
                ,status_message)
        VALUES (b.serial_number
                ,b.instance_id
                ,'Meter Read');

        dbms_output.put_line('Records for Meter Read changes: ' || SQL%ROWCOUNT);
		 canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for Meter Read changes: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        COMMIT;

   --move these to delete table

   INSERT INTO canon_e404_ib_ser_to_del_tbl (cbs_party_site_number
                                              ,customer_class
                                              ,configuration_id
                                              ,name
                                              ,serial_number
                                              ,instance_id
                                              ,external_reference_id
                                              ,item_code
                                              ,item_description
                                              ,install_date
                                              ,active_end_date
                                              ,ib_status
                                              ,instance_usage_code
                                              ,model_name
                                              ,pd364_segment_name
                                              ,control1
                                              ,control2
                                              ,control3
                                              ,control4
                                              ,sold_by
                                              ,serviced_by
                                              ,contract_number
                                              ,contract_header_id
                                              ,contract_expiration_date
                                              ,base_price
                                              ,base_volume
                                              ,overage
                                              ,base_volume_clr
                                              ,overage_clr
                                              ,aggregate_contract_number
                                              ,base_cycle
                                              ,contract_indicator
                                              ,rental_type
                                              ,sla_response_time
                                              ,service_coverage_hours
                                              ,reading_cycle
                                              ,consolidated_flag
                                              ,staples_included_flag
                                              ,meter_read_method
                                              ,last_service_contact
                                             -- ,ib_contact_name
                                           --   ,ib_contact_phone
                                            --  ,fax_contact
                                              ,lease_source
                                              ,lease_number
                                              ,leassor
                                              ,lease_expiration_date
                                              ,lease_upgrade_amount
                                              ,lease_term
                                              ,lease_payment_amount
                                              ,lease_disposal_date
                                              ,lease_inventory_date
                                              ,last_service_request_date
                                              ,svc_calls_last_30_days
                                              ,last_service_read
                                              ,last_service_read_date
                                              ,imageware_remote_enabled
                                              ,imageware_remote
                                              ,ugw_last_comm_date
                                              ,ugw_shared_flag
                                              ,ugw_registration_date
                                              ,ugw_deregistration_date
                                              ,ugw_last_total_reading
                                              ,ugw_last_bw_reading
                                              ,initial_total_hard_read
                                              ,latest_total_hard_read
                                              ,initial_meter_read_date
                                              ,latest_meter_read_date
                                              ,initial_bw_billed_read
                                              ,initial_color_billed_read
                                              ,latest_bw_billed_read
                                              ,latest_color_billed_read
                                              ,avg_monthly_cpy_vol_total
                                              ,avg_monthly_cpy_vol_bw
                                              ,active_flag
                                              ,psn_when_on_lease
                                              ,status_flag
                                              ,status_message
                                              ,error_message
                                              ,salesforce_ib_id
                                              ,creation_date
                                              ,created_by
                                              ,last_update_date
                                              ,last_updated_by
											  ,service_is_active
											  ,id_synch_date
                                             )
                                      (SELECT cbs_party_site_number
                                              ,customer_class
                                              ,configuration_id
                                              ,name
                                              ,a.serial_number
                                              ,a.instance_id
                                              ,external_reference_id
                                              ,item_code
                                              ,item_description
                                              ,install_date
                                              ,active_end_date
                                              ,ib_status
                                              ,instance_usage_code
                                              ,model_name
                                              ,pd364_segment_name
                                              ,control1
                                              ,control2
                                              ,control3
                                              ,control4
                                              ,sold_by
                                              ,serviced_by
                                              ,contract_number
                                              ,contract_header_id
                                              ,contract_expiration_date
                                              ,base_price
                                              ,base_volume
                                              ,overage
                                              ,base_volume_clr
                                              ,overage_clr
                                              ,aggregate_contract_number
                                              ,base_cycle
                                              ,contract_indicator
                                              ,rental_type
                                              ,sla_response_time
                                              ,service_coverage_hours
                                              ,reading_cycle
                                              ,consolidated_flag
                                              ,staples_included_flag
                                              ,meter_read_method
                                              ,last_service_contact
                                            --  ,ib_contact_name
                                            --  ,ib_contact_phone
                                            --  ,fax_contact
                                              ,lease_source
                                              ,lease_number
                                              ,leassor
                                              ,lease_expiration_date
                                              ,lease_upgrade_amount
                                              ,lease_term
                                              ,lease_payment_amount
                                              ,lease_disposal_date
                                              ,lease_inventory_date
                                              ,last_service_request_date
                                              ,svc_calls_last_30_days
                                              ,last_service_read
                                              ,last_service_read_date
                                              ,imageware_remote_enabled
                                              ,imageware_remote
                                              ,ugw_last_comm_date
                                              ,ugw_shared_flag
                                              ,ugw_registration_date
                                              ,ugw_deregistration_date
                                              ,ugw_last_total_reading
                                              ,ugw_last_bw_reading
                                              ,initial_total_hard_read
                                              ,latest_total_hard_read
                                              ,initial_meter_read_date
                                              ,latest_meter_read_date
                                              ,initial_bw_billed_read
                                              ,initial_color_billed_read
                                              ,latest_bw_billed_read
                                              ,latest_color_billed_read
                                              ,avg_monthly_cpy_vol_total
                                              ,avg_monthly_cpy_vol_bw
                                              ,active_flag
                                              ,psn_when_on_lease
                                              ,DECODE(salesforce_ib_id, NULL, 'DUP','DUP')
                                              ,'Marked for Update'
                                              ,NULL
                                              ,salesforce_ib_id
                                              ,SYSDATE
                                              ,NULL
                                              ,SYSDATE
                                              ,NULL
											  ,service_is_active
											  ,id_synch_date
                                         FROM canon_e404_install_base_ob_tbl a
                                              ,canon_e404_ib_ser_to_upd_tbl b
                                        WHERE a.instance_id = b.instance_id
                                          );
     dbms_output.put_line('Records for delete update: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records for delete update: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
     COMMIT;

     DELETE
       FROM canon_e404_install_base_ob_tbl
      WHERE (instance_id) IN (SELECT instance_id
                                FROM canon_e404_ib_ser_to_upd_tbl
                              );

     dbms_output.put_line('Records deleted from E404_IB for delete update: ' || SQL%ROWCOUNT);
	  canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records deleted from E404_IB for delete update: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
     COMMIT;

     l_end_time := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');

     EXECUTE IMMEDIATE 'SELECT canon_s21_ib_s.CURRVAL FROM DUAL' INTO l_seq_currval;

     audit_log(l_start_time
               ,l_end_time
               ,l_seq_currval        -- Sequence current value
               ,NULL
               ,l_procedure_name
              );

EXCEPTION
  WHEN OTHERS THEN
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    g_errbuff := SQLERRM;
    g_retcode := '2';
    dbms_output.put_line ('Exception occured in Serials to update procedure: ' || g_errbuff);
END serials_to_update;

PROCEDURE synch_ids
IS
  l_start_time               VARCHAR2(250);
  l_end_time                 VARCHAR2(250);
  l_message                  VARCHAR2(4000);
  l_request_status           BOOLEAN;
  l_procedure_name           VARCHAR2 (30);

  BEGIN
    l_start_time       := TO_CHAR(SYSDATE, 'MM/DD/YYYY HH:MI:SS AM');
    l_procedure_name   := 'SYNCH_IDS';
    
      MERGE
       INTO canon_e404_install_base_ob_tbl e404
      USING (SELECT a.*
               FROM canon_e404_ib_ids_to_synch_tbl a
              WHERE a.serial_number IN(SELECT b.serial_number 
                                         FROM canon_e404_ib_ids_to_synch_tbl b
                                        GROUP BY b.serial_number
                                       HAVING count(*) = 1
                                      )
            ) ids
         ON(e404.serial_number = ids.serial_number)
       WHEN MATCHED THEN
     UPDATE
        SET salesforce_ib_id = ids.id
           ,id_synch_date = SYSDATE;

    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of Records synched: ' || SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
        
      COMMIT;
    
EXCEPTION
  WHEN OTHERS THEN
  ROLLBACK;
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END synch_ids;


END CANON_E404_SF_INSTALL_BASE_PKG;
/