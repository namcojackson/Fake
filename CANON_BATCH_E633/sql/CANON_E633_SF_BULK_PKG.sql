CREATE OR REPLACE PACKAGE canon_e633_sf_bulk_pkg
AS
--
PROCEDURE get_handler_setup(p_handler_name    IN VARCHAR2
                           ,x_cursor         OUT sys_refcursor
                           );

PROCEDURE get_column_mapping(p_handler_name IN VARCHAR2
                             ,x_cursor OUT sys_refcursor);
PROCEDURE update_sf_data(p_job_id IN VARCHAR2
                         ,p_handler_name IN VARCHAR2
                         ,p_operation IN VARCHAR2);

PROCEDURE update_req_rslt_data(p_result_tbl IN CANON_E633_SF_REQ_RSLT_TBL );

PROCEDURE update_qry_rslt_data(
            p_result_tbl    IN CANON_E633_SF_QRY_RSLT_TBL);

PROCEDURE add_job_details(p_job_id IN VARCHAR2
                          ,p_sobject_type IN VARCHAR2
                          ,p_operation  IN VARCHAR2
                          ,p_handler_name IN VARCHAR2
                          ,p_status IN VARCHAR2
                          ,p_created_date IN DATE);
PROCEDURE update_job_details(p_job_id IN VARCHAR2
                              ,p_status IN VARCHAR2
                              ,p_total_batches IN VARCHAR2
                              );
PROCEDURE add_batch_details(p_batch_id IN VARCHAR2
                            ,p_job_id IN VARCHAR2
                            ,p_req_sent_flag IN VARCHAR2
                            ,p_status   IN  VARCHAR2
                            ,p_created_date IN DATE
                            ,p_process IN VARCHAR2
                            );
PROCEDURE update_batch_details(p_batch_id IN VARCHAR2
                                ,p_job_id IN VARCHAR2
                                ,p_rsp_cnt IN VARCHAR2
                                ,p_rsp_recv_flag IN VARCHAR2
                                ,p_no_of_recs_failed IN VARCHAR2
                                ,p_no_of_recs_processed IN VARCHAR2
                                ,p_status IN VARCHAR2
                                ,p_status_message IN VARCHAR2
                                ,p_process IN VARCHAR2
                                );
PROCEDURE update_batch_e633_flag(p_batch_id IN VARCHAR2
                                 ,p_job_id IN VARCHAR2
                                 ,p_process IN VARCHAR2
                                 );

PROCEDURE get_data(p_handler_name    IN VARCHAR2
                  ,x_cursor         OUT sys_refcursor
                  );
PROCEDURE get_where_clause(p_handler_name IN VARCHAR2
                            ,x_cursor         OUT sys_refcursor
                          );

PROCEDURE add_job_id(p_request_id IN NUMBER, p_job_id IN VARCHAR2, p_program_name IN VARCHAR2, p_handler IN VARCHAR2);

PROCEDURE get_job_id(p_request_id IN NUMBER, p_handler IN VARCHAR2, x_job_id OUT VARCHAR2) ;
END canon_e633_sf_bulk_pkg;
/

CREATE OR REPLACE PACKAGE BODY canon_e633_sf_bulk_pkg
AS

G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E633_SF_BULK_PKG';


PROCEDURE get_handler_setup(p_handler_name    IN VARCHAR2
                  ,x_cursor         OUT sys_refcursor
                  )
IS
   l_procedure_name VARCHAR2(100) := 'get_handler_setup';

BEGIN

   OPEN x_cursor
    FOR
   SELECT handler_name
         ,extract_table_name
         ,id_update_table_name
         ,object_name
         ,operation
         ,external_id
         ,id_upd_proc
     FROM canon_e633_sf_bulk_setup_tbl
    WHERE lower(handler_name) = lower(p_handler_name);

EXCEPTION
   WHEN OTHERS THEN
   --x_msg_data      := 'Unexpected ERROR:'|| SQLERRM;
   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END get_handler_setup;

PROCEDURE get_column_mapping(p_handler_name IN VARCHAR2
                             ,x_cursor out sys_refcursor)
AS
    P_PROCEDURE_NAME VARCHAR2(100) := 'GET_COLUMN_MAPPING';
BEGIN
    OPEN x_cursor FOR SELECT *
                        FROM canon_e633_sf_req_col_map_tbl
                       WHERE handler_name = p_handler_name
                       ORDER BY seq_num;
EXCEPTION
 WHEN OTHERS THEN
    --x_msg_data      := 'Unexpected ERROR:'|| SQLERRM;
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,P_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);

END get_column_mapping;

PROCEDURE update_req_rslt_data(
            p_result_tbl    IN CANON_E633_SF_REQ_RSLT_TBL
)
IS
   l_sql           VARCHAR2(3000);
    start_time       NUMBER ;
    end_time         NUMBER;
    l_count VARCHAR2(60) ;
   l_procedure_name VARCHAR2(60) := 'UPDATE_REQ_RSLT_DATA';

BEGIN
--    for i in 1..p_result_tbl.count
--      LOOP
--        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_result_tbl.count,p_result_tbl(i).instanceid,NULL,NULL,NULL,NULL);
----        --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--      END LOOP;
    start_time := DBMS_UTILITY.get_time;

    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','p_result_tbl:' ||p_result_tbl.count,NULL,NULL,NULL,NULL);
   -- Insert pl/sql table (collection) contents to oracle table
    EXECUTE IMMEDIATE
       'INSERT INTO CANON_e633_SFDC_RSLT_GTBL
        SELECT a.status
              ,a.sfdcId
              ,a.message
              ,a.instanceId
              ,SYSDATE
              ,a.jobId
          FROM TABLE(CAST(:1 AS CANON_e633_SF_REQ_RSLT_TBL) ) a'
     USING p_result_tbl;

    end_time := DBMS_UTILITY.get_time;

--    BEGIN
--        SELECT instanceId
--          INTO l_count
--          FROM CANON_e633_SFDC_RSLT_GTBL
--          ;
--    EXCEPTION
--    WHEN OTHERS THEN
--        l_count := 0;
--        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count: ' ||l_count,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--    END;
--    DBMS_OUTPUT.PUT_LINE('Update_Staged_Data::Bulk Insert: '||to_char(end_time-start_time));
    COMMIT;
	--canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count: ' ||l_count,NULL,NULL,NULL,NULL,NULL);

EXCEPTION
    WHEN OTHERS THEN
         canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END update_req_rslt_data;

PROCEDURE update_qry_rslt_data(
            p_result_tbl    IN CANON_e633_SF_QRY_RSLT_TBL
)
IS
   l_sql           VARCHAR2(3000);
    start_time       NUMBER ;
    end_time         NUMBER;
   l_procedure_name VARCHAR2(60) := 'UPDATE_QRY_RSLT_DATA';

BEGIN

--    for i in 1..p_result_tbl.count
--      LOOP
--        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_result_tbl.count,p_result_tbl(i).attribute1,p_result_tbl(i).sfdcid,NULL,NULL,NULL);
----        --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--      END LOOP;
    start_time := DBMS_UTILITY.get_time;

     canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','p_result_tbl count: ' ||p_result_tbl.count,NULL,NULL,NULL,NULL);

   -- Insert pl/sql table (collection) contents to oracle table
    EXECUTE IMMEDIATE
       'INSERT INTO CANON_e633_SF_QRY_RSLT_GTBL
        SELECT a.sfdcId
              ,a.attribute1
              ,a.attribute2
              ,a.attribute3
              ,a.attribute4
              ,a.attribute5
              ,a.attribute6
              ,a.attribute7
              ,a.attribute8
              ,a.attribute9
              ,a.attribute10
              ,a.attribute11
              ,a.attribute12
              ,a.attribute13
              ,a.attribute14
              ,a.attribute15
              ,a.attribute16
              ,a.attribute17
              ,a.attribute18
              ,a.attribute19
              ,a.attribute20
              ,SYSDATE
              ,a.jobId
          FROM TABLE(CAST(:1 AS CANON_e633_SF_QRY_RSLT_TBL)) a'
     USING p_result_tbl;

    end_time := DBMS_UTILITY.get_time;

    DBMS_OUTPUT.PUT_LINE('Update_Staged_Data::Bulk Insert: '||to_char(end_time-start_time));
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
         canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END update_qry_rslt_data;

 PROCEDURE update_sf_data(p_job_id IN VARCHAR2
                          ,p_handler_name IN VARCHAR2
                          ,p_operation  IN  VARCHAR2)
 IS
    l_dyn_sql               VARCHAR2(32767) := null;
    l_dyn_sql_e             VARCHAR2(32767) := null;
    l_sql                   VARCHAR2(32767) := null;
    l_attribute             VARCHAR2(1000) := null;
    l_values                VARCHAR2(32767) := null;
    l_col_name              VARCHAR2(32767) := null;
    l_ins_sql               VARCHAR2(32767) := null;
   l_procedure_name         VARCHAR2(60) := 'UPDATE_SF_DATA';
   l_table_name             VARCHAR2(1000);
   l_oracle_col_name        VARCHAR2(1000);
   l_sf_id_col_name         VARCHAR2(1000);
   l_sf_stat_col_name       VARCHAR2(1000);
   l_sf_upd_dt_col_name     VARCHAR2(1000);
   l_sf_stat_msg_col_name   VARCHAR2(1000);
   l_count  varchar2(60) ;
   l_jobid varchar2(60);
   l_no_of_attributes NUMBER :=0;
   l_id_col_name VARCHAR2(1000);
   l_col_sql VARCHAR2(1000);
   l_col_name_sql VARCHAR2(1000);

 BEGIN
    SELECT oracle_table_name
            ,source_mapping_column
           ,sf_id_column
           ,sf_status_column
           ,sf_status_msg_column
           ,sf_upd_dt_column
      INTO l_table_name
           ,l_oracle_col_name
           ,l_sf_id_col_name
           ,l_sf_stat_col_name
           ,l_sf_stat_msg_col_name
           ,l_sf_upd_dt_col_name
      FROM canon_e633_sf_rsltupd_map_tbl
     WHERE upper(handler_name) = upper(p_handler_name)
       AND lower(operation) = lower(p_operation);

  --  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,l_oracle_col_name,l_sf_id_col_name,l_sf_stat_col_name,l_sf_stat_msg_col_name,l_sf_upd_dt_col_name,NULL);

--       BEGIN
--        SELECT sfdcid, jobid
--          INTO l_count, l_jobid
--          FROM CANON_e633_SF_QRY_RSLT_GTBL
--          ;
--    EXCEPTION
--    WHEN OTHERS THEN
--        l_count := 0;
--        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count:' ||l_count,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--    END;

     --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count:' ||l_count,'l_jobid: ' ||l_jobid,NULL,NULL,NULL,NULL);

    IF (upper(p_operation) = upper('query')) THEN

--      l_dyn_sql := 'MERGE INTO .' || l_table_name || ' e633 ' ||
--                   'USING .canon_e633_sf_qry_rslt_gtbl req ON (req.sfdcid = e633.' || l_oracle_col_name || ' AND req.jobId = ''' || p_job_id || ''') ' ||
--                   'WHEN NOT MATCHED THEN INSERT (' ;

       l_dyn_sql := 'MERGE INTO ' || l_table_name || ' e633 ' ||
                    'USING (SELECT sfdcid ' ;

       --to get the first column name as it is the sfdcID and not the attributes
       BEGIN
             SELECT oracle_column_name
               INTO l_id_col_name
               FROM (SELECT oracle_column_name, row_number() over(order by seq_num) rno
                      FROM canon_e633_sf_req_col_map_tbl
                     WHERE handler_name = p_handler_name
                  ORDER BY seq_num
                   )
             WHERE rno = 1;

             SELECT max(rno)
               INTO l_no_of_attributes
               FROM (SELECT row_number() over(order by seq_num) rno
                       FROM canon_e633_sf_req_col_map_tbl
                      WHERE handler_name = p_handler_name
                     ORDER BY seq_num
                     );
       EXCEPTION
                    WHEN OTHERS THEN
                        l_id_col_name := null;
                        l_no_of_attributes := 0;
       END;

       --l_dyn_sql := l_dyn_sql || l_col_name;

       IF (l_no_of_attributes > 0) THEN --meaning there are some attributes to pull
             FOR i IN 1 .. l_no_of_attributes
                LOOP
                    -- canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,i,l_attribute,NULL,NULL,NULL,l_sql);
                        BEGIN
                            SELECT oracle_column_name
                              INTO l_col_name
                              FROM (SELECT oracle_column_name, row_number() over(order by seq_num) rno
                                      FROM canon_e633_sf_req_col_map_tbl
                                     WHERE handler_name = p_handler_name
                                  ORDER BY seq_num
                                   )
                             WHERE rno = i+1;
                         EXCEPTION
                            WHEN OTHERS THEN
                                l_col_name := null;
                         END;

                         IF (l_col_name IS NOT NULL) THEN
                            l_col_sql := l_col_sql || ', attribute' || i;
                            l_col_name_sql := l_col_name_sql || ', ' || l_col_name;
                            --l_dyn_sql := l_dyn_sql || ', ' || l_col_name;

                            IF (l_values IS NOT NULL) THEN
                                l_values := l_values || ','|| l_col_name || ' = req.attribute' || i ;
                                l_ins_sql := l_ins_sql || ', req.attribute' ||i;
                            ELSE l_values := 'SET ' || l_col_name || ' = req.attribute' || i;
                                l_ins_sql := 'VALUES (sfdcid,' || ' req.attribute' || i;
                            END IF;
                         END IF;

                       -- canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,i,l_attribute,NULL,NULL,NULL,l_sql);
                END LOOP;

       END IF;
       l_values := l_values || ', ' || l_sf_upd_dt_col_name || ' = SYSDATE ';
       l_ins_sql := l_ins_sql || ', SYSDATE ';

       l_dyn_sql := l_dyn_sql || l_col_sql ||',jobId  FROM canon_e633_sf_qry_rslt_gtbl) req ON (req.sfdcid = e633.' || l_oracle_col_name || ' AND req.jobId = ''' || p_job_id || ''') ';

       l_dyn_sql := l_dyn_sql || ' WHEN MATCHED THEN UPDATE ' ;


       l_dyn_sql := l_dyn_sql || l_values || '   WHEN NOT MATCHED THEN INSERT ('||l_id_col_name ||l_col_name_sql || ', ' || l_sf_upd_dt_col_name || ')' || l_ins_sql || ')' ;


    ELSIF(  upper(p_operation) = upper('hard_delete') OR
              upper(p_operation) = upper('delete')  ) THEN

         l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO '|| l_table_name || ' e633 ' ||
                  'USING CANON_e633_SFDC_RSLT_GTBL req ON (req.sfdcid = e633.' || l_oracle_col_name ||
                  ' AND req.jobId = ''' || p_job_id || ''')' ||
                  ' WHEN MATCHED THEN UPDATE SET ' ||
                  ' e633.' || l_sf_stat_col_name || ' = SUBSTR(e633.' || l_sf_stat_col_name || ',1,1) || req.status ' ||
                 -- ' ,e633.load_status = ''P'' '||
                 -- ' ,e633.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                  ' ,e633.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                  ' ,e633.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;

--    ELSIF ( upper(p_handler_name) = upper('TerrInsert') ) THEN
    ELSE
		IF (upper(p_handler_name) like upper('%ProspectMerge')) then
             l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO ' || l_table_name || ' e633 ' ||
                         'USING CANON_E633_SFDC_RSLT_GTBL req ON (req.sfdcid = e633.' || l_oracle_col_name || ')' ||
                        -- ' AND req.attribute2 = e404. user_sfdc_id AND req.attribute3 = e404.role_name AND req.jobId = ''' || p_job_id || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                         ' e633.' || l_sf_stat_col_name || ' = req.status ' ||
                          ' ,e633.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                          ' ,e633.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
		 ELSIF (upper(p_handler_name) = upper('PPSATMAcctShare'))then
            l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO ' || l_table_name || ' e633 ' ||
                         'USING CANON_E633_SF_QRY_RSLT_GTBL req ON (req.attribute1 = e633.site_sfdc_id ' ||
                         ' AND req.attribute2 = e633.user_sfdc_id AND e633.lob = ''PPS'' AND req.jobId = ''' || p_job_id || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                         ' e633. ' || l_sf_stat_col_name || ' = req.attribute3 ' ||
                         ' ,e633.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                          ' ,e633.' || l_sf_stat_msg_col_name || ' = req.attribute4 ' ||
                          ' ,e633.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
		ELSIF (upper(p_handler_name) = upper('PPSATMUpload')) then
          l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO ' || l_table_name || ' e633 ' ||
                         'USING CANON_E633_SF_QRY_RSLT_GTBL req ON (req.attribute1 = e633.site_sfdc_id ' ||
                         ' AND req.attribute2 = e633.user_sfdc_id AND req.attribute3 = e633.role_name AND e633.lob = ''PPS'' AND req.jobId = ''' || P_JOB_ID || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                          ' e633. ' || L_SF_STAT_COL_NAME || ' = SUBSTR(e633.' || L_SF_STAT_COL_NAME || ',1,1)|| req.attribute4 ' ||
                         ' ,e633.' || L_SF_ID_COL_NAME || ' = req.sfdcid ' ||
                          ' ,e633.' || L_SF_STAT_MSG_COL_NAME || ' = req.attribute5 ' ||
                          ' ,e633.' || L_SF_UPD_DT_COL_NAME ||' = SYSDATE ' ;
		ELSIF (upper(p_handler_name) = upper('LFSATMAcctShare'))then
            l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO ' || l_table_name || ' e633 ' ||
                         'USING CANON_E633_SF_QRY_RSLT_GTBL req ON (req.attribute1 = e633.site_sfdc_id ' ||
                         ' AND req.attribute2 = e633.user_sfdc_id AND e633.lob = ''LFS'' AND req.jobId = ''' || p_job_id || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                         ' e633. ' || l_sf_stat_col_name || ' = req.attribute3 ' ||
                         ' ,e633.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                          ' ,e633.' || l_sf_stat_msg_col_name || ' = req.attribute4 ' ||
                          ' ,e633.' || L_SF_UPD_DT_COL_NAME ||' = SYSDATE ' ;
		ELSIF (upper(p_handler_name) = upper('LFSATMUpload')) then
          l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO ' || l_table_name || ' e633 ' ||
                         'USING CANON_E633_SF_QRY_RSLT_GTBL req ON (req.attribute1 = e633.site_sfdc_id ' ||
                         ' AND req.attribute2 = e633.user_sfdc_id AND req.attribute3 = e633.role_name AND e633.lob = ''LFS'' AND req.jobId = ''' || P_JOB_ID || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                          ' e633. ' || L_SF_STAT_COL_NAME || ' = SUBSTR(e633.' || L_SF_STAT_COL_NAME || ',1,1)|| req.attribute4 ' ||
                         ' ,e633.' || L_SF_ID_COL_NAME || ' = req.sfdcid ' ||
                          ' ,e633.' || L_SF_STAT_MSG_COL_NAME || ' = req.attribute5 ' ||
                          ' ,e633.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
		ELSIF (upper(p_handler_name) like upper('%ProsUpload')) then
          l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO '|| l_table_name || ' e633 ' ||
                  'USING CANON_E633_SFDC_RSLT_GTBL req ON (req.instanceId = e633.' || l_oracle_col_name ||
                  ' AND req.jobId = ''' || p_job_id || ''')' ||--|| ''' AND e404.status_flag IN (''I'',''U''))' ||
                  ' WHEN MATCHED THEN UPDATE SET ' ||
                  ' e633.' || l_sf_stat_col_name || ' = req.status ' ||
                 -- ' ,e404.load_status = ''P'' '||
                --  ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                  ' ,e633.' || L_SF_STAT_MSG_COL_NAME || ' = SUBSTR(req.message,1,4000) ' ||
                  ' ,e633.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
          ELSE
            l_dyn_sql := 'MERGE /*+ use_hash(req e633) */ INTO '|| l_table_name || ' e633 ' ||
                  'USING CANON_e633_SFDC_RSLT_GTBL req ON (req.instanceId = e633.' || l_oracle_col_name ||
                  ' AND req.jobId = ''' || p_job_id || ''')' ||--|| ''' AND e633.status_flag IN (''I'',''U''))' ||
                  ' WHEN MATCHED THEN UPDATE SET ' ||
                  ' e633.' || l_sf_stat_col_name || ' = req.status ' ||
                 -- ' ,e633.load_status = ''P'' '||
                  ' ,e633.' || l_sf_id_col_name || ' = (CASE req.status WHEN ''P'' THEN req.sfdcid ELSE ' || l_sf_id_col_name || ' END)' ||
                  ' ,e633.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                  ' ,e633.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
		 END IF;
      END IF;



      DBMS_OUTPUT.PUT_LINE('l_dyn_sql : ' ||l_dyn_sql);
      DBMS_OUTPUT.PUT_LINE('l_dyn_sql_e : ' ||l_dyn_sql_e);
      canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Dyn SQL: ' ||l_dyn_sql,NULL,NULL,NULL,NULL);
      canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Dyn SQL e: ' ||l_dyn_sql_e,NULL,NULL,NULL,NULL);

      EXECUTE IMMEDIATE l_dyn_sql;
      IF (l_dyn_sql_e IS NOT NULL) THEN
          EXECUTE IMMEDIATE l_dyn_sql_e;
      END IF;
      COMMIT;

     --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,sql%rowcount);
    EXCEPTION
      WHEN OTHERS THEN
         canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
    END update_sf_data;


PROCEDURE add_job_details(p_job_id IN VARCHAR2
                          ,p_sobject_type IN VARCHAR2
                          ,p_operation  IN VARCHAR2
                          ,p_handler_name IN VARCHAR2
                          ,p_status IN VARCHAR2
                          ,p_created_date IN DATE)
IS
   l_procedure_name         VARCHAR2(60) := 'ADD_JOB_DETAILS';
   l_dyn_sql                VARCHAR2(32767);
   BEGIN
        INSERT INTO canon_e633_sf_job_tbl(JOB_ID
                                          ,HANDLER_NAME
                                          ,SOBJECT_NAME
                                          ,OPERATION
                                          ,STATUS
                                          ,START_DATE
                                          ) VALUES (p_job_id
                                                  ,p_handler_name
                                                  ,p_sobject_type
                                                  ,p_operation
                                                  ,p_status
                                                  ,p_created_date);
       COMMIT;
   EXCEPTION
    WHEN OTHERS THEN
        rollback;
         canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
    END add_job_details;
PROCEDURE update_job_details(p_job_id IN VARCHAR2
                              ,p_status IN VARCHAR2
                              ,p_total_batches IN VARCHAR2
                              )
                               IS
    l_dyn_sql               VARCHAR2(32767);
   l_procedure_name         VARCHAR2(60) := 'UPDATE_JOB_DETAILS';
   p1   VARCHAR2(1000) := null;
   p2   VARCHAR2(1000) := null;
   p3   VARCHAR2(1000) := null;
   BEGIN

     UPDATE canon_e633_sf_job_tbl e633
        SET status = p_status
            ,no_of_batches = p_total_batches
            ,end_date = SYSDATE
        WHERE job_id = p_job_id;

--    MERGE INTO canon_e633_sf_job_tbl e633
--      USING (SELECT p1 as job_id, p2 as stat, p3 as totBatch FROM dual) args
--       ON (e633.job_id = args.job_id)
--    WHEN MATCHED THEN UPDATE SET
--        e633.status = args.stat
--        ,e633.no_of_batches = args.totBatch
--        ,e633.END_DATE = sysdate;
   COMMIT;
   EXCEPTION
    WHEN OTHERS THEN
     rollback;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
   END update_job_details;
PROCEDURE add_batch_details(p_batch_id IN VARCHAR2
                            ,p_job_id IN VARCHAR2
                            ,p_req_sent_flag IN VARCHAR2
                            ,p_status   IN  VARCHAR2
                            ,p_created_date IN DATE
                            ,p_process IN VARCHAR2
                            )
IS
   l_dynsql               VARCHAR2(32767);
   l_procedure_name         VARCHAR2(60) := 'ADD_BATCH_DETAILS';
   l_attempt NUMBER;

   BEGIN

        IF (p_process = 'DEFAULT') THEN
            INSERT INTO canon_e633_sf_batch_tbl(BATCH_ID
                                                ,JOB_ID
                                                ,REQ_SENT_FLAG
                                                ,STATUS
                                                ,START_DATE
                                                ) VALUES (p_batch_id
                                                          ,p_job_id
                                                          ,p_req_sent_flag
                                                          ,p_status
                                                          ,p_created_date );
        END IF;

        IF (p_process = 'REPROCESS') THEN
            BEGIN
                SELECT reprocess_attempt
                  INTO l_attempt
                  FROM canon_e633_sf_reprs_batch_tbl
                 WHERE batch_id = p_batch_id
                   AND job_id = p_job_id;
             EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    l_attempt := 0;
                WHEN OTHERS THEN
                    l_attempt := 0;
              END;

            IF (l_attempt > 0 ) THEN --meaning already an attempt made for this batch, job ID
                UPDATE canon_e633_sf_reprs_batch_tbl
                   SET reprocess_attempt = (l_attempt + 1)
                       ,reprocess_attempt_date = sysdate
                       ,status = p_status
                       ,req_sent_flag = p_req_sent_flag
                 WHERE batch_id = p_batch_id
                   AND job_id = p_job_id;
            ELSE
                INSERT INTO canon_e633_sf_reprs_batch_tbl (reprocess_attempt
                                                            ,batch_id
                                                            ,job_id
                                                            ,req_sent_flag
                                                            ,status
                                                            ,reprocess_attempt_date ) values ( 1
                                                                                               ,p_batch_id
                                                                                               ,p_job_id
                                                                                               ,p_req_sent_flag
                                                                                               ,p_status
                                                                                               ,p_created_date);
           END IF;
         END IF;


    COMMIT;

   EXCEPTION
    WHEN OTHERS THEN
    rollback;
     canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
   END add_batch_details;
   
PROCEDURE update_batch_details(p_batch_id IN VARCHAR2
                                ,p_job_id IN VARCHAR2
                                ,p_rsp_cnt IN VARCHAR2
                                ,p_rsp_recv_flag IN VARCHAR2
                                ,p_no_of_recs_failed IN VARCHAR2
                                ,p_no_of_recs_processed IN VARCHAR2
                                ,p_status IN VARCHAR2
                                ,p_status_message IN VARCHAR2
                                ,p_process IN VARCHAR2
                                )
IS
    l_dyn_sql               VARCHAR2(32767);
   l_procedure_name         VARCHAR2(60) := 'UPDATE_BATCH_DETAILS';
      p1   VARCHAR2(1000) := null;
   p2   VARCHAR2(1000) := null;
   p3    VARCHAR2(1000) := null;
   p4    VARCHAR2(1000) := null;
   p5    VARCHAR2(1000) := null;
   p6    VARCHAR2(1000) := null;
   p7    VARCHAR2(1000) := null;
   p8    VARCHAR2(1000) := null;

   BEGIN

   --fnd_file.put_line(fnd_file.log, 'p_batch_id : ' ||p_batch_id);
   --fnd_file.put_line(fnd_file.log, 'p_job_id : ' ||p_job_id);


   IF (p_process = 'DEFAULT') THEN
    MERGE INTO canon_e633_sf_batch_tbl e633
       USING (SELECT p_batch_id as batch_id
                    ,p_job_id as job_id
                     ,p_rsp_cnt as rsp_cnt
                     ,p_rsp_recv_flag as rsp_recv_flag
                     ,p_no_of_recs_failed as recs_failed
                     ,p_no_of_recs_processed as recs_processed
                     ,p_status as stat
                    ,p_status_message as stat_msg FROM dual) args
           ON (e633.batch_id = args.batch_id AND
               e633.job_id = args.job_id)
        WHEN MATCHED THEN UPDATE SET
            e633.rsp_count = args.rsp_cnt
            ,e633.rsp_rcvd_flag = args.rsp_recv_flag
            ,e633.no_of_rec_failed = args.recs_failed
            ,e633.no_of_rec_processed = args.recs_processed
           ,e633.status = args.stat
            ,e633.status_message = args.stat_msg
            --,e633.END_DATE = TO_CHAR(SYSDATE, 'MM/DD/YYYY HH24:MI:SS');
            ,e633.END_DATE = sysdate;
            COMMIT;
   END IF;

   IF (p_process = 'REPROCESS') THEN
   --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_batch_id,p_job_id,p_status,p_no_of_recs_processed,p_process,NULL);
    MERGE INTO canon_e633_sf_reprs_batch_tbl e633
        USING (SELECT p_batch_id as batch_id
                    ,p_job_id as job_id
                     ,p_rsp_cnt as rsp_cnt
                     ,p_rsp_recv_flag as rsp_recv_flag
                     ,p_no_of_recs_failed as recs_failed
                     ,p_no_of_recs_processed as recs_processed
                     ,p_status as stat
                    ,p_status_message as stat_msg FROM dual) args
           ON (e633.batch_id = args.batch_id AND
               e633.job_id = args.job_id)
        WHEN MATCHED THEN UPDATE SET
            e633.rsp_count = args.rsp_cnt
            ,e633.rsp_rcvd_flag = args.rsp_recv_flag
            ,e633.no_of_rec_failed = args.recs_failed
            ,e633.no_of_rec_processed = args.recs_processed
           ,e633.status = args.stat
            ,e633.status_message = args.stat_msg
            --,e633.END_DATE = TO_CHAR(SYSDATE, 'MM/DD/YYYY HH24:MI:SS');
            ,e633.END_DATE = sysdate;
     --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_batch_id,p_job_id,p_status,p_no_of_recs_processed,p_process,sql%rowcount);
            COMMIT;
   END IF;

   /*
   IF (p_process = 'REPROCESS') THEN
        MERGE INTO canon_e633_sf_reprs_batch_tbl e633
         USING (SELECT p1 as batch_id
                      ,p2 as job_id
                      ,p3 as rsp_cnt
                      ,p4 as rsp_recv_flag
                      ,p5 as recs_failed
                      ,p6 as recs_processed
                      ,p7 as stat
                      ,p8 as stat_msg FROM dual) args
            ON (e633.batch_id = args.batch_id AND
                 e633.job_id = args.job_id)
          WHEN MATCHED THEN UPDATE SET
            e633.rsp_cnt = args.
   END IF;
   COMMIT;*/
   EXCEPTION
    WHEN OTHERS THEN
     rollback;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
   END update_batch_details;

PROCEDURE update_batch_e633_flag(p_batch_id IN VARCHAR2
                                 ,p_job_id IN VARCHAR2
                                 ,p_process IN VARCHAR2
                                 )
IS
   l_procedure_name         VARCHAR2(60) := 'UPDATE_BATCH_e633_FLAG';
 BEGIN
 IF (p_process = 'DEFAULT') THEN
    UPDATE canon_e633_sf_batch_tbl
       SET IS_ID_UPDTD_IN_e633 = 'Y'
       WHERE batch_id = p_batch_id
         AND job_id = p_job_id;
  END IF;

  IF (p_process = 'REPROCESS') THEN
    UPDATE canon_e633_sf_reprs_batch_tbl
       SET is_id_updtd_in_e633 = 'Y'
     WHERE batch_id = p_batch_id
       AND job_id = p_job_id;
  END IF;

     COMMIT;
 EXCEPTION
   WHEN OTHERS THEN
     rollback;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
   END update_batch_e633_flag;

PROCEDURE get_data(p_handler_name    IN VARCHAR2
                  ,x_cursor         OUT sys_refcursor
                  )
IS
   l_procedure_name VARCHAR2(100) := 'get_data';
   l_dynsql         VARCHAR2(32000);

   CURSOR bulk_setup_cur
         (l_handler_name IN VARCHAR2
         )
       IS
   SELECT *
     FROM canon_e633_sf_bulk_setup_tbl
    WHERE upper(handler_name) = upper(l_handler_name);

   CURSOR req_col_cur
         (p_handlder_name IN VARCHAR2
         )
       IS
   SELECT *
     FROM canon_e633_sf_req_col_map_tbl
    WHERE upper(handler_name) = upper(p_handler_name)
    ORDER BY seq_num;

BEGIN

   FOR bulk_setup_rec IN bulk_setup_cur(p_handler_name) LOOP

      l_dynsql := 'SELECT ';

      FOR req_col_rec IN req_col_cur(p_handler_name) LOOP

         l_dynsql := l_dynsql ||' '|| req_col_rec.oracle_column_name || ',';

      END LOOP;

      l_dynsql := SUBSTR(l_dynsql, 1, LENGTH(l_dynsql)-1) || ' FROM '||bulk_setup_rec.extract_table_name; -- || ' WHERE rownum < 3';

   END LOOP;

   dbms_output.put_line(l_dynsql);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,l_dynsql,NULL,NULL,NULL,NULL,NULL);

   OPEN x_cursor FOR l_dynsql;

EXCEPTION
   WHEN OTHERS THEN
   --x_msg_data      := 'Unexpected ERROR:'|| SQLERRM;
   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END get_data;

PROCEDURE get_where_clause(p_handler_name IN VARCHAR2
                            ,x_cursor         OUT sys_refcursor
                          )

IS
l_procedure_name VARCHAR2(100) := 'get_where_clause';

BEGIN
    OPEN x_cursor for SELECT where_clause
                      FROM canon_e633_sf_bulk_setup_tbl
                      WHERE upper(handler_name) = upper(p_handler_name) ;

EXCEPTION
WHEN OTHERS THEN
   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END get_where_clause;

PROCEDURE add_job_id(p_request_id         IN NUMBER
                         ,p_job_id             IN VARCHAR2
                         ,p_program_name     IN VARCHAR2
                         ,p_handler         IN VARCHAR2)
    AS
        l_procedure_name varchar2(50) := 'add_job_id';
    BEGIN
        INSERT INTO canon_E633_sf_bulk_job_req_tbl(request_id, sfdc_job_id, program_name, handler_name, created_date) values(p_request_id, p_job_id, p_program_name, p_handler, sysdate);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
         canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
    END add_job_id;

    PROCEDURE get_job_id(p_request_id     IN NUMBER
                         ,p_handler     IN VARCHAR2
                         ,x_job_id         OUT VARCHAR2)
    AS
        l_procedure_name varchar2(50) := 'get_job_id';
    BEGIN
        SELECT sfdc_job_id
          INTO x_job_id
          FROM canon_e633_sf_bulk_job_req_tbl
         WHERE request_id = p_request_id
           AND handler_name = p_handler;
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        x_job_id := null;
    WHEN OTHERS THEN
         canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
    END get_job_id;

END canon_e633_sf_bulk_pkg;
/

