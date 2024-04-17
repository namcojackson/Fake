create or replace
PACKAGE CANON_E404_SF_APXJOB_PKG
AS
PROCEDURE get_apxjob_setup(p_batch_program    IN VARCHAR2
                          ,x_cursor         OUT sys_refcursor
                           );
                           
                    
PROCEDURE update_finished_date(p_batch_program IN VARCHAR2); 
                         
                         
END CANON_E404_SF_APXJOB_PKG;
/
create or replace
PACKAGE BODY CANON_E404_SF_APXJOB_PKG
AS

G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E404_SF_APXJOB_PKG';

PROCEDURE get_apxjob_setup(p_batch_program    IN VARCHAR2
                          ,x_cursor         OUT sys_refcursor
                           )
   IS
     l_procedure_name VARCHAR2(100) := 'get_apxjob_setup';
     
   BEGIN
   
     OPEN x_cursor 
    FOR 
   SELECT source_name
         ,apex_class_name
         ,soql
         ,use_last_finished_date
         ,last_finished_date
     FROM canon_e404_sf_apxjob_setup_tbl
    WHERE batch_program = p_batch_program;
    
    
   EXCEPTION
      WHEN OTHERS THEN
         canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
   END get_apxjob_setup;
   
   
   
   
   PROCEDURE update_finished_date(p_batch_program IN VARCHAR2)
   IS
   l_procedure_name VARCHAR2(100) := 'update_finished_date';
   
   BEGIN
   UPDATE canon_e404_sf_apxjob_setup_tbl
        SET last_finished_date = SYSDATE
        WHERE batch_program = p_batch_program;
   
   COMMIT;
   
   EXCEPTION
     WHEN OTHERS THEN 
     rollback;
         canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
         
   END update_finished_date;
   
END CANON_E404_SF_APXJOB_PKG;
/
SHOW ERRORS;