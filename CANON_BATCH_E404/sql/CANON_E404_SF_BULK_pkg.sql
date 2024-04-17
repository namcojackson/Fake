create or replace PACKAGE              canon_e404_sf_bulk_pkg
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

PROCEDURE update_req_rslt_data(p_result_tbl IN CANON_E404_SF_REQ_RSLT_TBL );

PROCEDURE update_qry_rslt_data(
            p_result_tbl    IN CANON_E404_SF_QRY_RSLT_TBL);

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
PROCEDURE update_batch_e404_flag(p_batch_id IN VARCHAR2
                                 ,p_job_id IN VARCHAR2
                                 ,p_process IN VARCHAR2
                                 );                                                                                                                    

PROCEDURE get_data(p_handler_name    IN VARCHAR2
                  ,x_cursor         OUT sys_refcursor
                  );
PROCEDURE get_where_clause(p_handler_name IN VARCHAR2
                            ,x_cursor         OUT sys_refcursor
                          );
END canon_e404_sf_bulk_pkg;
/
create or replace PACKAGE BODY              canon_e404_sf_bulk_pkg
AS

G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E404_SF_BULK_PKG';


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
     FROM canon_e404_sf_bulk_setup_tbl
    WHERE handler_name = p_handler_name;
    
EXCEPTION
   WHEN OTHERS THEN
   --x_msg_data      := 'Unexpected ERROR:'|| SQLERRM;    
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END get_handler_setup;

PROCEDURE get_column_mapping(p_handler_name IN VARCHAR2
                             ,x_cursor out sys_refcursor)
AS
    P_PROCEDURE_NAME VARCHAR2(100) := 'GET_COLUMN_MAPPING';
BEGIN
    OPEN x_cursor FOR SELECT * 
                        FROM canon_e404_sf_req_col_map_tbl
                       WHERE handler_name = p_handler_name
                       ORDER BY seq_num; 
EXCEPTION
 WHEN OTHERS THEN
    --x_msg_data      := 'Unexpected ERROR:'|| SQLERRM;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,P_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
   
END get_column_mapping;  

PROCEDURE update_req_rslt_data(
            p_result_tbl    IN CANON_E404_SF_REQ_RSLT_TBL
)
IS
   l_sql           VARCHAR2(3000);
    start_time       NUMBER ;
    end_time         NUMBER;
    l_count NUMBER := 0;
   l_procedure_name VARCHAR2(60) := 'UPDATE_REQ_RSLT_DATA';
  
BEGIN
    start_time := DBMS_UTILITY.get_time;
    
    --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'p_result_tbl:' ||p_result_tbl.count,NULL,NULL,NULL,NULL,NULL);

   -- Insert pl/sql table (collection) contents to oracle table

---EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E404_SFDC_RSLT_GTBL'; defect#21026

    EXECUTE IMMEDIATE
       'INSERT INTO CANON_E404_SFDC_RSLT_GTBL
        SELECT a.status
              ,a.sfdcId
              ,a.message
              ,a.instanceId
              ,SYSDATE
              ,a.jobId
          FROM TABLE(CAST(:1 AS CANON_E404_SF_REQ_RSLT_TBL)) a '
     USING p_result_tbl;
  
    --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'p_result_tbl2:' ||p_result_tbl.count,NULL,NULL,NULL,NULL,NULL);

    end_time := DBMS_UTILITY.get_time;
    
    BEGIN
        SELECT count(*)
          INTO l_count
          FROM CANON_E404_SFDC_RSLT_GTBL
          ;
    EXCEPTION
    WHEN OTHERS THEN
        l_count := 0;
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count: ' ||l_count,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    END;
    DBMS_OUTPUT.PUT_LINE('Update_Staged_Data::Bulk Insert: '||to_char(end_time-start_time));
    COMMIT;
--canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count: ' ||l_count,NULL,NULL,NULL,NULL,NULL);    

EXCEPTION
    WHEN OTHERS THEN
         canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END update_req_rslt_data;

PROCEDURE update_qry_rslt_data(
            p_result_tbl    IN CANON_E404_SF_QRY_RSLT_TBL
)
IS
   l_sql           VARCHAR2(3000);
    start_time       NUMBER ;
    end_time         NUMBER;
   l_procedure_name VARCHAR2(60) := 'UPDATE_QRY_RSLT_DATA';
   
BEGIN

--    for i in 1..p_result_tbl.count
--      LOOP
--        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_result_tbl.count,p_result_tbl(i).sfdcId,NULL,NULL,NULL,NULL);
----        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--      END LOOP;
    start_time := DBMS_UTILITY.get_time;
    
     --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'p_result_tbl count: ' ||p_result_tbl.count,NULL,NULL,NULL,NULL,NULL);

   -- Insert pl/sql table (collection) contents to oracle table
    EXECUTE IMMEDIATE
       'INSERT INTO CANON_E404_SF_QRY_RSLT_GTBL
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
          FROM TABLE(CAST(:1 AS CANON_E404_SF_QRY_RSLT_TBL)) a'
     USING p_result_tbl;

    end_time := DBMS_UTILITY.get_time;

    DBMS_OUTPUT.PUT_LINE('Update_Staged_Data::Bulk Insert: '||to_char(end_time-start_time));
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
         canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END update_qry_rslt_data;

 PROCEDURE update_sf_data(p_job_id IN VARCHAR2
                          ,p_handler_name IN VARCHAR2
                          ,p_operation  IN  VARCHAR2)
 IS
    l_dyn_sql               VARCHAR2(32767) := null;
    l_dyn_sql_e             VARCHAR2(32767) := null;
    l_pb_dyn_sql             VARCHAR2(32767) := null;
    l_sql                   VARCHAR2(32767) := null;
    l_attribute             VARCHAR2(1000) := null;
    l_values                VARCHAR2(32767) := null;
    l_col_name              VARCHAR2(32767) := null;
   l_procedure_name         VARCHAR2(60) := 'UPDATE_SF_DATA';
   l_table_name             VARCHAR2(1000);
   l_oracle_col_name        VARCHAR2(1000);
   l_sf_id_col_name         VARCHAR2(1000);
   l_sf_stat_col_name       VARCHAR2(1000);
   l_sf_upd_dt_col_name     VARCHAR2(1000);
   l_sf_stat_msg_col_name   VARCHAR2(1000);
   l_count  NUMBER :=0;
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
      FROM canon_e404_sf_rsltupd_map_tbl
     WHERE handler_name = p_handler_name
       AND operation = p_operation;
       
--   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,l_oracle_col_name,l_sf_id_col_name,l_sf_stat_col_name,l_sf_stat_msg_col_name,l_sf_upd_dt_col_name,NULL);       
       
       BEGIN
        SELECT count(*)
          INTO l_count
          FROM CANON_E404_SF_QRY_RSLT_GTBL
          ;
    EXCEPTION
    WHEN OTHERS THEN
        l_count := 0;
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count:' ||l_count,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
    END;
     
--     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'l_count:' ||l_count,NULL,NULL,NULL,NULL,NULL);
       
    IF (upper(p_operation) = upper('query')) THEN
            
       l_values := ' VALUES ( req.sfdcid ';
       
       l_dyn_sql := 'MERGE INTO ' || l_table_name || ' e404 ' ||
                    'USING (SELECT sfdcid ' ;
       
       --to get the first column name as it is the sfdcID and not the attributes
       BEGIN
             SELECT oracle_column_name
               INTO l_id_col_name
               FROM (SELECT oracle_column_name, row_number() over(order by seq_num) rno
                      FROM canon_e404_sf_req_col_map_tbl
                     WHERE handler_name = p_handler_name
                  ORDER BY seq_num
                   ) 
             WHERE rno = 1;
             
             SELECT max(rno)
               INTO l_no_of_attributes
               FROM (SELECT row_number() over(order by seq_num) rno
                       FROM canon_e404_sf_req_col_map_tbl
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
                    -- canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,i,l_attribute,NULL,NULL,NULL,l_sql);
                        BEGIN 
                            SELECT oracle_column_name
                              INTO l_col_name
                              FROM (SELECT oracle_column_name, row_number() over(order by seq_num) rno
                                      FROM canon_e404_sf_req_col_map_tbl
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
                            l_values := l_values || ', req.attribute' || i;
                         END IF;
                        
                       -- canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,i,l_attribute,NULL,NULL,NULL,l_sql);
                END LOOP;                
                
       END IF;
       
       l_dyn_sql := l_dyn_sql || l_col_sql ||',jobId  FROM canon_e404_sf_qry_rslt_gtbl) req ON (req.sfdcid = e404.' || l_oracle_col_name || ' AND req.jobId = ''' || p_job_id || ''') ';
                    
       IF (upper(p_handler_name) = upper('CFSAuditDownload')) THEN
            l_dyn_sql := l_dyn_sql || 'WHEN MATCHED THEN UPDATE ' ||
                         'SET no_of_times_accessed_that_day = no_of_times_accessed_that_day + 1' ;
       END IF;
       
       IF(upper(p_handler_name) = upper('PBIDDownload')) THEN
            l_pb_dyn_sql := 'UPDATE ' || l_table_name || ' SET ' || l_oracle_col_name || ' = (SELECT sfdcid FROM canon_e404_sf_qry_rslt_gtbl WHERE jobId = ''' || p_job_id || '''), ' || l_sf_upd_dt_col_name || ' = SYSDATE' ;
        END IF;
       
       l_dyn_sql := l_dyn_sql || ' WHEN NOT MATCHED THEN INSERT ( ' ;
--       l_pb_dyn_sql := l_pb_dyn_sql || ' WHEN NOT MATCHED THEN UPDATE SET e404.sf_pbook_id = req.sfdcid';
       
       if (l_sf_upd_dt_col_name IS NOT NULL) THEN
            l_dyn_sql := l_dyn_sql || l_id_col_name || l_col_name_sql || ', ' || l_sf_upd_dt_col_name  || ') ';
            l_values := l_values || ',SYSDATE)';
--            l_pb_dyn_sql := l_pb_dyn_sql || ', ' || l_sf_upd_dt_col_name || ' = SYSDATE ';
       ELSE 
            l_dyn_sql := l_dyn_sql || l_id_col_name || l_col_name_sql || ') ';
            l_values := l_values || ' )' ;
       END IF;
       
       l_dyn_sql := l_dyn_sql || l_values ;
       
        IF(upper(p_handler_name) = upper('PBIDDownload')) THEN
            l_dyn_sql := l_pb_dyn_sql;
        END IF;
        
    ELSIF(  upper(p_operation) = upper('hard_delete') OR
              upper(p_operation) = upper('delete')  ) THEN
              
         l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO '|| l_table_name || ' e404 ' ||
                  'USING CANON_E404_SFDC_RSLT_GTBL req ON (req.sfdcid = e404.' || l_oracle_col_name ||
                  ' AND req.jobId = ''' || p_job_id || ''')' ||
                  ' WHEN MATCHED THEN UPDATE SET ' ;
                  IF (upper(p_handler_name) = upper('OwnerATDelete') ) THEN
                     l_dyn_sql := l_dyn_sql || ' e404.' || l_sf_stat_col_name || ' = DECODE(req.status,''E'',''DE'',''P'',''DP'') '; 
				  ELSIF (upper(p_handler_name) = upper('IBDelete') OR
				         upper(p_handler_name) = upper('ContractDelete')) THEN
                    l_dyn_sql := l_dyn_sql || ' e404.' || l_sf_stat_col_name || ' = SUBSTR(e404.' || l_sf_stat_col_name || ',1,2) || req.status ' ;
				  ELSIF (upper(p_handler_name) = upper('StackRankDelete')) THEN
					L_DYN_SQL := L_DYN_SQL || ' e404.' || L_SF_STAT_COL_NAME || ' = req.status ' ;
                  ELSE
                  l_dyn_sql := l_dyn_sql || ' e404.' || l_sf_stat_col_name || ' = SUBSTR(e404.' || l_sf_stat_col_name || ',1,1) || req.status ' ;
                  END IF;
                 -- ' ,e404.load_status = ''P'' '||
                 -- ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                  l_dyn_sql := l_dyn_sql || ' ,e404.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                  ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;  
                     
--    ELSIF ( upper(p_handler_name) = upper('TerrInsert') ) THEN
    ELSE
          IF (upper(p_handler_name) = upper('AccountInactivate')) THEN
          
              -- in case of success first save the current employee_number and territory 
              MERGE /*+ use_hash(req e404) */ INTO CANON_E404_SF_ACCT_MAPPING_TBL e404
               USING CANON_E404_SFDC_RSLT_GTBL req
                  ON (req.instanceid = e404.sf_account_id 
                       AND req.jobId = p_job_id
                       AND req.status = 'P')
                WHEN MATCHED THEN UPDATE 
                    SET E404.OLD_WRITING_EMPLOYEE_NUMBER = e404.writing_employee_number
                        ,E404.OLD_WRITING_TERRITORY = e404.writing_territory;
                COMMIT;                        
                    
               -- after saving the employee_number and territory,update the columns to be in consistent to salesforce
              l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO ' || l_table_name || ' e404 ' ||
                           ' USING CANON_E404_SFDC_RSLT_GTBL req ON (req.instanceId = e404.' || l_oracle_col_name ||
                           ' AND req.jobId = ''' || p_job_id || ''' AND req.status = ''P'') ' ||
                           ' WHEN MATCHED THEN UPDATE SET ' ||
                           ' e404.' || l_sf_stat_col_name || ' = req.status ' ||
                           ' ,e404.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                           ' ,e404.' || l_sf_upd_dt_col_name || ' =  SYSDATE ' ||
                           ' ,e404.writing_resource_id = NULL ' ||
                           ' ,e404.writing_rep_name = ''CBS INTERFACE'' '||
                           ' ,e404.writing_territory = NULL ' ||
                           ' ,e404.writing_employee_number = ''CBSINTERFACE'' '||
                           ' ,e404.is_deleted_in_sfdc = ''Y'' ' ;
                
              --in case of error just update the status and message so that these records can be picked up in the next run
                l_dyn_sql_e := 'MERGE /*+ use_hash(req e404) */ INTO ' || l_table_name || ' e404 ' ||
                           ' USING CANON_E404_SFDC_RSLT_GTBL req ON (req.instanceId = e404.' || l_oracle_col_name ||
                           ' AND req.jobId = ''' || p_job_id || ''' AND req.status = ''E'')' ||
                           ' WHEN MATCHED THEN UPDATE SET ' ||
                           ' e404.' || l_sf_stat_col_name || ' = req.status ' ||
                           ' ,e404.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                           ' ,e404.' || l_sf_upd_dt_col_name || ' = SYSDATE ' ;   
          ELSIF( upper(p_handler_name) = upper('AccountShare')) THEN
            l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO ' || l_table_name || ' e404 ' ||
                         'USING CANON_E404_SF_QRY_RSLT_GTBL req ON (req.attribute1 = e404.site_sfdc_id ' ||
                         ' AND req.attribute2 = e404. user_sfdc_id AND req.jobId = ''' || p_job_id || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                         ' e404. ' || l_sf_stat_col_name || ' = req.attribute3 ' ||
                         ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                          ' ,e404.' || l_sf_stat_msg_col_name || ' = req.attribute4 ' ||
                          ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
          ELSIF (upper(p_handler_name) = upper('AccountTeam')) THEN
            l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO ' || l_table_name || ' e404 ' ||
                         'USING CANON_E404_SF_QRY_RSLT_GTBL req ON (req.attribute1 = e404.site_sfdc_id ' ||
                         ' AND req.attribute2 = e404. user_sfdc_id AND req.attribute3 = e404.role_name AND req.jobId = ''' || p_job_id || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                          ' e404. ' || l_sf_stat_col_name || ' = SUBSTR(e404.' || l_sf_stat_col_name || ',1,1)|| req.attribute4 ' ||
                         ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                          ' ,e404.' || l_sf_stat_msg_col_name || ' = req.attribute5 ' ||
                          ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
          ELSIF (upper(p_handler_name) = upper('ProspectMerge')) THEN
             l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO ' || l_table_name || ' e404 ' ||
                         'USING CANON_E404_SFDC_RSLT_GTBL req ON (req.sfdcid = e404.' || l_oracle_col_name || ')' ||
                        -- ' AND req.attribute2 = e404. user_sfdc_id AND req.attribute3 = e404.role_name AND req.jobId = ''' || p_job_id || ''')' ||
                         ' WHEN MATCHED THEN UPDATE SET ' ||
                         ' e404.' || l_sf_stat_col_name || ' = req.status ' ||
                         -- ' ,e404.load_status = ''P'' '||
--                          ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                          ' ,e404.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                          ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;      
          ELSIF (upper(p_handler_name) = upper('OwnerATUpload')) THEN
              l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO '|| l_table_name || ' e404 ' ||
                  'USING CANON_E404_SFDC_RSLT_GTBL req ON (req.instanceId = e404.' || l_oracle_col_name ||
                  ' AND req.jobId = ''' || p_job_id || ''')' ||--|| ''' AND e404.status_flag IN (''I'',''U''))' ||
                  ' WHEN MATCHED THEN UPDATE SET ' ||
                  ' e404.' || l_sf_stat_col_name || ' = SUBSTR(e404.' || l_sf_stat_col_name || ',1,1) || req.status ' ||
                 -- ' ,e404.load_status = ''P'' '||
                  ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                  ' ,e404.' || l_sf_stat_msg_col_name || ' = req.message ' ||
                  ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
		  ELSIF (upper(p_handler_name) = upper('ProspectUpload')) THEN
              l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO '|| l_table_name || ' e404 ' ||
                  'USING CANON_E404_SFDC_RSLT_GTBL req ON (req.instanceId = e404.' || l_oracle_col_name ||
                  ' AND req.jobId = ''' || p_job_id || ''')' ||--|| ''' AND e404.status_flag IN (''I'',''U''))' ||
                  ' WHEN MATCHED THEN UPDATE SET ' ||
                  ' e404.' || l_sf_stat_col_name || ' = req.status ' ||
                 -- ' ,e404.load_status = ''P'' '||
                --  ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                  ' ,e404.' || l_sf_stat_msg_col_name || ' = SUBSTR(req.message,1,4000) ' ||
                  ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
          ELSE
                --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'TEST');

            l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO '|| l_table_name || ' e404 ' ||
                  'USING CANON_E404_SFDC_RSLT_GTBL req ON (req.instanceId = e404.' || l_oracle_col_name ||
                  ' AND req.jobId = ''' || p_job_id || ''')' ||--|| ''' AND e404.status_flag IN (''I'',''U''))' ||
                  ' WHEN MATCHED THEN UPDATE SET ' ||
                  ' e404.' || l_sf_stat_col_name || ' = req.status ' ||
                 -- ' ,e404.load_status = ''P'' '||
                  ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
                  ' ,e404.' || l_sf_stat_msg_col_name || ' = SUBSTR(req.message,1,4000) ' ||
                  ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
		  
          END IF;
       
--    ELSIF ( upper(p_handler_name) != upper('TerrInsert') AND
--           (upper(p_operation) != upper('hard_delete') OR
--              upper(p_operation) != upper('delete')  )  AND
--              upper(p_operation) != upper('query') ) THEN
--            
--        l_dyn_sql := 'MERGE /*+ use_hash(req e404) */ INTO '|| l_table_name || ' e404 ' ||
--                  'USING CANON_E404_SFDC_RSLT_GTBL req ON (req.instanceId = e404.' || l_oracle_col_name ||
--                  ' AND req.jobId = ''' || p_job_id || ''' AND e404.load_status IN (''I'',''U''))' ||
--                  ' WHEN MATCHED THEN UPDATE SET ' ||
--                  ' e404.' || l_sf_stat_col_name || ' = req.status ' ||
--                  ' ,e404.load_status = ''P'' '||
--                  ' ,e404.' || l_sf_id_col_name || ' = req.sfdcid ' ||
--                  ' ,e404.' || l_sf_stat_msg_col_name || ' = req.message ' ||
--                  ' ,e404.' || l_sf_upd_dt_col_name ||' = SYSDATE ' ;
      END IF;
                  
                 
                  
      DBMS_OUTPUT.PUT_LINE('l_dyn_sql : ' ||l_dyn_sql);
      DBMS_OUTPUT.PUT_LINE('l_dyn_sql_e : ' ||l_dyn_sql_e);
      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,l_dyn_sql);
      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,l_dyn_sql_e);
      
      EXECUTE IMMEDIATE l_dyn_sql;
      IF (l_dyn_sql_e IS NOT NULL) THEN
          EXECUTE IMMEDIATE l_dyn_sql_e;
      END IF;
       COMMIT;
     --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,sql%rowcount);           
    EXCEPTION
      WHEN OTHERS THEN
         canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
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
        INSERT INTO canon_e404_sf_job_tbl(JOB_ID
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
         canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
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
   
     UPDATE canon_e404_sf_job_tbl e404
        SET status = p_status
            ,no_of_batches = p_total_batches
            ,end_date = SYSDATE
        WHERE job_id = p_job_id;
        
--    MERGE INTO canon_e404_sf_job_tbl e404
--      USING (SELECT p1 as job_id, p2 as stat, p3 as totBatch FROM dual) args
--       ON (e404.job_id = args.job_id)
--    WHEN MATCHED THEN UPDATE SET
--        e404.status = args.stat
--        ,e404.no_of_batches = args.totBatch
--        ,e404.END_DATE = sysdate;
   COMMIT;
   EXCEPTION
    WHEN OTHERS THEN
     rollback;
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
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
            INSERT INTO canon_e404_sf_batch_tbl(BATCH_ID
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
                  FROM canon_e404_sf_reprs_batch_tbl
                 WHERE batch_id = p_batch_id
                   AND job_id = p_job_id;
             EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    l_attempt := 0;
                WHEN OTHERS THEN 
                    l_attempt := 0;
              END; 
            
            IF (l_attempt > 0 ) THEN --meaning already an attempt made for this batch, job ID
                UPDATE canon_e404_sf_reprs_batch_tbl 
                   SET reprocess_attempt = (l_attempt + 1)
                       ,reprocess_attempt_date = sysdate
                       ,status = p_status
                       ,req_sent_flag = p_req_sent_flag
                 WHERE batch_id = p_batch_id
                   AND job_id = p_job_id;
            ELSE 
                INSERT INTO canon_e404_sf_reprs_batch_tbl (reprocess_attempt
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
     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
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
    MERGE INTO canon_e404_sf_batch_tbl e404 
       USING (SELECT p_batch_id as batch_id 
                    ,p_job_id as job_id 
                     ,p_rsp_cnt as rsp_cnt 
                     ,p_rsp_recv_flag as rsp_recv_flag 
                     ,p_no_of_recs_failed as recs_failed 
                     ,p_no_of_recs_processed as recs_processed 
                     ,p_status as stat 
                    ,p_status_message as stat_msg FROM dual) args 
           ON (e404.batch_id = args.batch_id AND 
               e404.job_id = args.job_id) 
        WHEN MATCHED THEN UPDATE SET 
            e404.rsp_count = args.rsp_cnt 
            ,e404.rsp_rcvd_flag = args.rsp_recv_flag 
            ,e404.no_of_rec_failed = args.recs_failed 
            ,e404.no_of_rec_processed = args.recs_processed 
           ,e404.status = args.stat 
            ,e404.status_message = args.stat_msg 
            --,E404.END_DATE = TO_CHAR(SYSDATE, 'MM/DD/YYYY HH24:MI:SS');
            ,E404.END_DATE = sysdate;
            COMMIT;
   END IF;
   
   IF (p_process = 'REPROCESS') THEN
   --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_batch_id,p_job_id,p_status,p_no_of_recs_processed,p_process,NULL);
    MERGE INTO canon_e404_sf_reprs_batch_tbl e404 
        USING (SELECT p_batch_id as batch_id 
                    ,p_job_id as job_id 
                     ,p_rsp_cnt as rsp_cnt 
                     ,p_rsp_recv_flag as rsp_recv_flag 
                     ,p_no_of_recs_failed as recs_failed 
                     ,p_no_of_recs_processed as recs_processed 
                     ,p_status as stat 
                    ,p_status_message as stat_msg FROM dual) args 
           ON (e404.batch_id = args.batch_id AND 
               e404.job_id = args.job_id) 
        WHEN MATCHED THEN UPDATE SET 
            e404.rsp_count = args.rsp_cnt 
            ,e404.rsp_rcvd_flag = args.rsp_recv_flag 
            ,e404.no_of_rec_failed = args.recs_failed 
            ,e404.no_of_rec_processed = args.recs_processed 
           ,e404.status = args.stat 
            ,e404.status_message = args.stat_msg 
            --,E404.END_DATE = TO_CHAR(SYSDATE, 'MM/DD/YYYY HH24:MI:SS');
            ,E404.END_DATE = sysdate;
     --canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_batch_id,p_job_id,p_status,p_no_of_recs_processed,p_process,sql%rowcount);       
            COMMIT;
   END IF;
   
   
   
   
   
   /*
   IF (p_process = 'REPROCESS') THEN
        MERGE INTO canon_e404_sf_reprs_batch_tbl e404
         USING (SELECT p1 as batch_id
                      ,p2 as job_id
                      ,p3 as rsp_cnt
                      ,p4 as rsp_recv_flag
                      ,p5 as recs_failed
                      ,p6 as recs_processed
                      ,p7 as stat
                      ,p8 as stat_msg FROM dual) args
            ON (e404.batch_id = args.batch_id AND
                 e404.job_id = args.job_id)
          WHEN MATCHED THEN UPDATE SET
            e404.rsp_cnt = args.
   END IF;
   COMMIT;*/
   EXCEPTION
    WHEN OTHERS THEN
     rollback;
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
   END update_batch_details;     
                              
PROCEDURE update_batch_e404_flag(p_batch_id IN VARCHAR2
                                 ,p_job_id IN VARCHAR2
                                 ,p_process IN VARCHAR2
                                 )
IS                                 
   l_procedure_name         VARCHAR2(60) := 'UPDATE_BATCH_E404_FLAG';
 BEGIN
 IF (p_process = 'DEFAULT') THEN
    UPDATE canon_e404_sf_batch_tbl
       SET IS_ID_UPDTD_IN_E404 = 'Y'
       WHERE batch_id = p_batch_id
         AND job_id = p_job_id;
  END IF;
  
  IF (p_process = 'REPROCESS') THEN
    UPDATE canon_e404_sf_reprs_batch_tbl
       SET is_id_updtd_in_e404 = 'Y'
     WHERE batch_id = p_batch_id
       AND job_id = p_job_id;
  END IF;
           
     COMMIT;
 EXCEPTION
   WHEN OTHERS THEN
     rollback;
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
   END update_batch_e404_flag;
   
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
     FROM canon_e404_sf_bulk_setup_tbl
    WHERE handler_name = l_handler_name;

   CURSOR req_col_cur
         (p_handlder_name IN VARCHAR2
         )
       IS
   SELECT *
     FROM canon_e404_sf_req_col_map_tbl
    WHERE handler_name = p_handler_name
    ORDER BY seq_num; 
       
BEGIN

   FOR bulk_setup_rec IN bulk_setup_cur(p_handler_name) LOOP

      l_dynsql := 'SELECT ';
    
      FOR req_col_rec IN req_col_cur(p_handler_name) LOOP
      
         l_dynsql := l_dynsql ||' '|| req_col_rec.oracle_column_name || ',';
      
      END LOOP; 
      
      l_dynsql := SUBSTR(l_dynsql, 1, LENGTH(l_dynsql)-1) || ' FROM '||bulk_setup_rec.extract_table_name;
    
   END LOOP;

   dbms_output.put_line(l_dynsql);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,l_dynsql,NULL,NULL,NULL,NULL,NULL);

   OPEN x_cursor FOR l_dynsql;
    
EXCEPTION
   WHEN OTHERS THEN
   --x_msg_data      := 'Unexpected ERROR:'|| SQLERRM;    
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END get_data;

PROCEDURE get_where_clause(p_handler_name IN VARCHAR2
                            ,x_cursor         OUT sys_refcursor
                          )
                          
IS
l_procedure_name VARCHAR2(100) := 'get_where_clause';
    
BEGIN
    OPEN x_cursor for SELECT external_id
                      FROM canon_e404_sf_bulk_setup_tbl
                      WHERE handler_name = p_handler_name ;
      
EXCEPTION
WHEN OTHERS THEN 
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END get_where_clause;                          
                                         
END canon_e404_sf_bulk_pkg;
/