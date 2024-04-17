CREATE OR REPLACE PACKAGE CANON_E404_SF_ERROR_LOG_PKG
AS
PROCEDURE log_error
     (p_in_package_name IN VARCHAR2
        ,p_in_process_name IN VARCHAR2
         ,p_in_key1         IN VARCHAR2
         ,p_in_key2         IN VARCHAR2
         ,p_in_key3         IN VARCHAR2
         ,p_in_key4         IN VARCHAR2
         ,p_in_key5         IN VARCHAR2
         ,p_error_msg       IN VARCHAR2
         );
END canon_e404_sf_error_log_pkg;
/

CREATE OR REPLACE PACKAGE BODY CANON_E404_SF_ERROR_LOG_PKG
AS
PROCEDURE log_error(p_in_package_name IN VARCHAR2
                    ,p_in_process_name IN VARCHAR2
                   ,p_in_key1         IN VARCHAR2
                   ,p_in_key2         IN VARCHAR2
                   ,p_in_key3         IN VARCHAR2
                   ,p_in_key4         IN VARCHAR2
                   ,p_in_key5         IN VARCHAR2
                   ,p_error_msg       IN VARCHAR2
                   )
IS
   --l_debug BOOLEAN := fnd_msg_pub.Check_Msg_Level(fnd_msg_pub.G_MSG_LVL_DEBUG_LOW);
   BEGIN
      --IF l_debug = TRUE THEN
         INSERT INTO CANON_E404_SF_ERRORS_TBL
                     (package_name,
                      process_name,
                      key1,
                      key2,
                      key3,
                      key4,
                      key5,
                      error_msg,
                      error_date
                     )
         VALUES      (p_in_package_name,
                      p_in_process_name,
                      p_in_key1,
                      p_in_key2,
                      p_in_key3,
                      p_in_key4,
                      p_in_key5,
                      p_error_msg,
                      SYSDATE
                     );

         COMMIT;
     --END IF;

EXCEPTION
   WHEN OTHERS THEN
      dbms_output.put_line('Unexpected error occured while logging the errors in CANON_E404_SF_ERRORS_TBL. Oracle Error Message:' || SQLERRM);
END log_error;
END canon_e404_sf_error_log_pkg;
/
