CREATE OR REPLACE PACKAGE CANON_E479_CUST_BILL_UTIL_PKG
AS
   /*********************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_CUST_BILL_UTIL_PKG.sql                                      *
     * DESCRIPTION     : Util Package for Custom Billing                                        *
	 *                                                                                          *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/16/2015     Initial Creation                                        *
	 * Lakshmi Gopalsami 09/21/2017     GSD Special Billing changes                             *
   *************************************************************************************************/
   TYPE RESULT_CURSOR IS REF CURSOR;

   l_package_name   VARCHAR2 (30) := 'CANON_E479_CUST_BILL_UTIL_PKG';

   PROCEDURE log_error (p_package_class_name      IN VARCHAR2,
                        p_procedure_method_name   IN VARCHAR2,
                        p_error_type              IN VARCHAR2,
                        p_key1                    IN VARCHAR2,
                        p_key2                    IN VARCHAR2,
                        p_key3                    IN VARCHAR2,
                        p_key4                    IN VARCHAR2,
                        p_key5                    IN VARCHAR2,
                        p_key6                    IN VARCHAR2,
                        p_error_message           IN LONG);

   PROCEDURE log_dynamic_sql (p_package_name      VARCHAR2,
                              p_procedure_name    VARCHAR2,
                              p_dynsql            LONG,
                              p_value1            VARCHAR2,
                              p_value2            VARCHAR2,
                              p_value3            VARCHAR2,
                              p_value4            VARCHAR2,
                              p_value5            VARCHAR2,
                              p_value6            VARCHAR2);

   PROCEDURE log_message (application_code        IN VARCHAR2 DEFAULT NULL,
                          program_name            IN VARCHAR2,
                          subroutine_name         IN VARCHAR2,
                          concurrent_request_id   IN NUMBER,
                          parent_request_id       IN NUMBER DEFAULT NULL,
                          status                  IN NUMBER,
                          log_flag                IN VARCHAR2,
                          table_flag              IN VARCHAR2,
                          ERROR_CODE              IN VARCHAR2 DEFAULT NULL,
                          MESSAGE                 IN VARCHAR2 DEFAULT NULL,
                          label1                  IN VARCHAR2 DEFAULT NULL,
                          reference1              IN VARCHAR2 DEFAULT NULL,
                          label2                  IN VARCHAR2 DEFAULT NULL,
                          reference2              IN VARCHAR2 DEFAULT NULL,
                          label3                  IN VARCHAR2 DEFAULT NULL,
                          reference3              IN VARCHAR2 DEFAULT NULL,
                          label4                  IN VARCHAR2 DEFAULT NULL,
                          reference4              IN VARCHAR2 DEFAULT NULL,
                          label5                  IN VARCHAR2 DEFAULT NULL,
                          reference5              IN VARCHAR2 DEFAULT NULL,
                          label6                  IN VARCHAR2 DEFAULT NULL,
                          reference6              IN VARCHAR2 DEFAULT NULL,
                          label7                  IN VARCHAR2 DEFAULT NULL,
                          reference7              IN VARCHAR2 DEFAULT NULL,
                          label8                  IN VARCHAR2 DEFAULT NULL,
                          reference8              IN VARCHAR2 DEFAULT NULL,
                          label9                  IN VARCHAR2 DEFAULT NULL,
                          reference9              IN VARCHAR2 DEFAULT NULL,
                          label10                 IN VARCHAR2 DEFAULT NULL,
                          reference10             IN VARCHAR2 DEFAULT NULL,
                          label11                 IN VARCHAR2 DEFAULT NULL,
                          reference11             IN VARCHAR2 DEFAULT NULL,
                          label12                 IN VARCHAR2 DEFAULT NULL,
                          reference12             IN VARCHAR2 DEFAULT NULL,
                          label13                 IN VARCHAR2 DEFAULT NULL,
                          reference13             IN VARCHAR2 DEFAULT NULL,
                          label14                 IN VARCHAR2 DEFAULT NULL,
                          reference14             IN VARCHAR2 DEFAULT NULL,
                          label15                 IN VARCHAR2 DEFAULT NULL,
                          reference15             IN VARCHAR2 DEFAULT NULL);

   PROCEDURE request_logs (p_job_id                 IN NUMBER,
                           p_program_name           IN VARCHAR2 DEFAULT NULL,
                           p_sub_program            IN VARCHAR2 DEFAULT NULL,
                           p_type                   IN VARCHAR2 DEFAULT NULL,
                           p_template_level         IN VARCHAR2 DEFAULT NULL,
                           p_template_level_value   IN VARCHAR2 DEFAULT NULL,
                           p_message1               IN VARCHAR2 DEFAULT NULL,
                           p_message2               IN VARCHAR2 DEFAULT NULL,
                           p_message3               IN VARCHAR2 DEFAULT NULL,
                           p_message4               IN VARCHAR2 DEFAULT NULL,
                           p_message5               IN VARCHAR2 DEFAULT NULL,
						   p_error_message           IN LONG DEFAULT NULL );

    PROCEDURE get_profile_value(
      p_user_id               IN        NUMBER, 
      p_user_name             IN        VARCHAR2,
	  p_role_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_profile_value              OUT  VARCHAR2,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
	);
	
	
 FUNCTION is_model_in_exclusion_list(
   p_model_num  IN VARCHAR2
 ) RETURN VARCHAR2;
 
 TYPE model_exclusion_list_rec IS RECORD(
   is_excluded   VARCHAR2(1)
 );
 
 TYPE model_exclusion_list_tab IS TABLE OF model_exclusion_list_rec
      INDEX BY VARCHAR2 (240);

  g_model_exclusion_list_tab   model_exclusion_list_tab;
  
END CANON_E479_CUST_BILL_UTIL_PKG;
/

SHOW ERRORS;

CREATE OR REPLACE PACKAGE BODY CANON_E479_CUST_BILL_UTIL_PKG
AS
    /*********************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_CUST_BILL_UTIL_PKG.sql                                      *
     * DESCRIPTION     : Util Package for Custom Billing                                        *
	 *                                                                                          *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/16/2015     Initial Creation                                        *
	 * Lakshmi Gopalsami 09/21/2017     GSD Special Billing changes                             *
   *************************************************************************************************/
   PROCEDURE log_error (p_package_class_name      IN VARCHAR2,
                        p_procedure_method_name   IN VARCHAR2,
                        p_error_type              IN VARCHAR2,
                        p_key1                    IN VARCHAR2,
                        p_key2                    IN VARCHAR2,
                        p_key3                    IN VARCHAR2,
                        p_key4                    IN VARCHAR2,
                        p_key5                    IN VARCHAR2,
                        p_key6                    IN VARCHAR2,
                        p_error_message           IN LONG)
   IS
          PRAGMA AUTONOMOUS_TRANSACTION;						
   BEGIN
      INSERT INTO canon_e479_errors_tbl (PACKAGE_CLASS_NAME,
                                         PROCEDURE_METHOD_NAME,
                                         key1,
                                         key2,
                                         key3,
                                         key4,
                                         key5,
                                         key6,
                                         error_date,
                                         ERROR_TYPE,
                                         error_message)
           VALUES (p_package_class_name,
                   p_procedure_method_name,
                   p_key1,
                   p_key2,
                   p_key3,
                   p_key4,
                   p_key5,
                   p_key6,
                   SYSDATE,
                   p_error_type,
                   p_error_message);


      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END log_error;

   PROCEDURE log_dynamic_sql (p_package_name      VARCHAR2,
                              p_procedure_name    VARCHAR2,
                              p_dynsql            LONG,
                              p_value1            VARCHAR2,
                              p_value2            VARCHAR2,
                              p_value3            VARCHAR2,
                              p_value4            VARCHAR2,
                              p_value5            VARCHAR2,
                              p_value6            VARCHAR2)
   IS
   BEGIN
      INSERT INTO canon_e479_dynsql_tbl (package_name,
                                         procedure_name,
                                         dynamic_sql,
                                         last_update_date,
                                         value1,
                                         value2,
                                         value3,
                                         value4,
                                         value5,
                                         value6)
           VALUES (p_package_name,
                   p_procedure_name,
                   p_dynsql,
                   SYSDATE,
                   p_value1,
                   p_value2,
                   p_value3,
                   p_value4,
                   p_value5,
                   p_value6);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         log_error ('CANON_E479_CUST_BILL_UTIL_PKG',
                    'LOG_DYNAMIC_SQL',
                    'SQL',
                    p_package_name,
                    p_procedure_name,
                    NULL,
                    NULL,
                    NULL,
                    NULL,
                    SQLERRM);
   END log_dynamic_sql;

   PROCEDURE log_message (application_code        IN VARCHAR2 DEFAULT NULL,
                          program_name            IN VARCHAR2,
                          subroutine_name         IN VARCHAR2,
                          concurrent_request_id   IN NUMBER,
                          parent_request_id       IN NUMBER DEFAULT NULL,
                          status                  IN NUMBER,
                          log_flag                IN VARCHAR2,
                          table_flag              IN VARCHAR2,
                          ERROR_CODE              IN VARCHAR2 DEFAULT NULL,
                          MESSAGE                 IN VARCHAR2 DEFAULT NULL,
                          label1                  IN VARCHAR2 DEFAULT NULL,
                          reference1              IN VARCHAR2 DEFAULT NULL,
                          label2                  IN VARCHAR2 DEFAULT NULL,
                          reference2              IN VARCHAR2 DEFAULT NULL,
                          label3                  IN VARCHAR2 DEFAULT NULL,
                          reference3              IN VARCHAR2 DEFAULT NULL,
                          label4                  IN VARCHAR2 DEFAULT NULL,
                          reference4              IN VARCHAR2 DEFAULT NULL,
                          label5                  IN VARCHAR2 DEFAULT NULL,
                          reference5              IN VARCHAR2 DEFAULT NULL,
                          label6                  IN VARCHAR2 DEFAULT NULL,
                          reference6              IN VARCHAR2 DEFAULT NULL,
                          label7                  IN VARCHAR2 DEFAULT NULL,
                          reference7              IN VARCHAR2 DEFAULT NULL,
                          label8                  IN VARCHAR2 DEFAULT NULL,
                          reference8              IN VARCHAR2 DEFAULT NULL,
                          label9                  IN VARCHAR2 DEFAULT NULL,
                          reference9              IN VARCHAR2 DEFAULT NULL,
                          label10                 IN VARCHAR2 DEFAULT NULL,
                          reference10             IN VARCHAR2 DEFAULT NULL,
                          label11                 IN VARCHAR2 DEFAULT NULL,
                          reference11             IN VARCHAR2 DEFAULT NULL,
                          label12                 IN VARCHAR2 DEFAULT NULL,
                          reference12             IN VARCHAR2 DEFAULT NULL,
                          label13                 IN VARCHAR2 DEFAULT NULL,
                          reference13             IN VARCHAR2 DEFAULT NULL,
                          label14                 IN VARCHAR2 DEFAULT NULL,
                          reference14             IN VARCHAR2 DEFAULT NULL,
                          label15                 IN VARCHAR2 DEFAULT NULL,
                          reference15             IN VARCHAR2 DEFAULT NULL)
   IS
      v_sequence_id         NUMBER (15);
      v_last_update_date    DATE := SYSDATE;
      v_last_updated_by     NUMBER (15) := 0;
      v_last_update_login   NUMBER (15) := 0;
      v_execution_date      DATE := SYSDATE;
      v_status              VARCHAR2 (8);
      i_message             VARCHAR2 (500);
      PRAGMA AUTONOMOUS_TRANSACTION;
      l_procedure_name      VARCHAR2 (20) := 'log_message';
   BEGIN
      IF table_flag = 'Y'
      THEN
         BEGIN
            SELECT CANON_E479_MESSAGE_HANDLING_S.NEXTVAL
              INTO v_sequence_id
              FROM DUAL;
         EXCEPTION
            WHEN OTHERS
            THEN
               CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                        l_procedure_name,
                                                        'SQL',
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        SQLERRM);
         END;

         BEGIN
            INSERT INTO CANON_E479_MESSAGE_HANDLING (SEQUENCE_ID,
                                                     APPLICATION_CODE,
                                                     PROGRAM_NAME,
                                                     SUBROUTINE_NAME,
                                                     CONCURRENT_REQUEST_ID,
                                                     PARENT_REQUEST_ID,
                                                     STATUS,
                                                     MESSAGE_CODE,
                                                     MESSAGE,
                                                     LABEL1,
                                                     REFERENCE1,
                                                     LABEL2,
                                                     REFERENCE2,
                                                     LABEL3,
                                                     REFERENCE3,
                                                     LABEL4,
                                                     REFERENCE4,
                                                     LABEL5,
                                                     REFERENCE5,
                                                     LABEL6,
                                                     REFERENCE6,
                                                     LABEL7,
                                                     REFERENCE7,
                                                     LABEL8,
                                                     REFERENCE8,
                                                     LABEL9,
                                                     REFERENCE9,
                                                     LABEL10,
                                                     REFERENCE10,
                                                     LABEL11,
                                                     REFERENCE11,
                                                     LABEL12,
                                                     REFERENCE12,
                                                     LABEL13,
                                                     REFERENCE13,
                                                     LABEL14,
                                                     REFERENCE14,
                                                     LABEL15,
                                                     REFERENCE15,
                                                     LAST_UPDATE_DATE,
                                                     LAST_UPDATED_BY,
                                                     LAST_UPDATE_LOGIN,
                                                     CREATION_DATE,
                                                     CREATED_BY)
                 VALUES (v_sequence_id,
                         application_code,
                         SUBSTR (program_name, 1, 30),
                         SUBSTR (subroutine_name, 1, 50),
                         concurrent_request_id,
                         parent_request_id,
                         status,
                         SUBSTR (ERROR_CODE, 1, 20),
                         SUBSTR (MESSAGE, 1, 500),
                         SUBSTR (label1, 1, 50),
                         SUBSTR (reference1, 1, 50),
                         SUBSTR (label2, 1, 50),
                         SUBSTR (reference2, 1, 50),
                         SUBSTR (label3, 1, 50),
                         SUBSTR (reference3, 1, 50),
                         SUBSTR (label4, 1, 50),
                         SUBSTR (reference4, 1, 50),
                         SUBSTR (label5, 1, 50),
                         SUBSTR (reference5, 1, 50),
                         SUBSTR (label6, 1, 50),
                         SUBSTR (reference6, 1, 50),
                         SUBSTR (label7, 1, 50),
                         SUBSTR (reference7, 1, 50),
                         SUBSTR (label8, 1, 50),
                         SUBSTR (reference8, 1, 50),
                         SUBSTR (label9, 1, 50),
                         SUBSTR (reference9, 1, 50),
                         SUBSTR (label10, 1, 50),
                         SUBSTR (reference10, 1, 50),
                         SUBSTR (label11, 1, 50),
                         SUBSTR (reference11, 1, 50),
                         SUBSTR (label12, 1, 50),
                         SUBSTR (reference12, 1, 50),
                         SUBSTR (label13, 1, 50),
                         SUBSTR (reference13, 1, 50),
                         SUBSTR (label14, 1, 50),
                         SUBSTR (reference14, 1, 50),
                         SUBSTR (label15, 1, 50),
                         SUBSTR (reference15, 1, 50),
                         v_last_update_date,
                         v_last_updated_by,
                         v_last_update_login,
                         v_last_update_date,
                         v_last_updated_by);
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE_APPLICATION_ERROR (-20901, SUBSTR (SQLERRM, 1, 100));
         END;

         COMMIT;

         -- if job is complete (status 99), make table update for Oracle Alert
         IF status = 99
         THEN
            BEGIN
               UPDATE CANON_E479_MESSAGE_HANDLING
                  SET label15 = 'Timestamp',
                      reference15 = TO_CHAR (SYSDATE, 'MM-DD-RR HH24MISS')
                WHERE sequence_id = v_sequence_id;
            EXCEPTION
               WHEN OTHERS
               THEN
                  RAISE_APPLICATION_ERROR (-20901, SUBSTR (SQLERRM, 1, 100));
            END;

            COMMIT;
         END IF;
      END IF;
   -- write to concurrent log file if flag is set -----------------------
   /*
   IF log_flag = 'Y' then
       FND_FILE.PUT_LINE(FND_FILE.LOG, 'Request Id ' || ' ' || concurrent_request_id);
       FND_FILE.PUT_LINE(FND_FILE.LOG, 'Parent Request Id ' || ' ' || parent_request_id);
       FND_FILE.PUT_LINE(FND_FILE.LOG, label1 || ' ' || reference1);
       FND_FILE.PUT_LINE(FND_FILE.LOG,error_code);
       FND_FILE.PUT_LINE(FND_FILE.LOG,i_message);
     IF label2 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label2 || ' ' || reference2);
     END IF;
     IF label3 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label3 || ' ' || reference3);
     END IF;
     IF label4 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label4 || ' ' || reference4);
     END IF;
     IF label5 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label5 || ' ' || reference5);
     END IF;
     IF label6 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label6 || ' ' || reference6);
     END IF;
     IF label7 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label7 || ' ' || reference7);
     END IF;
     IF label8 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label8 || ' ' || reference8);
     END IF;
     IF label9 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label9 || ' ' || reference9);
     END IF;
     IF label10 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label10 || ' ' || reference10);
     END IF;
     IF label11 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label11 || ' ' || reference11);
     END IF;
     IF label12 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label12 || ' ' || reference12);
     END IF;
     IF label13 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label13 || ' ' || reference13);
     END IF;
     IF label14 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label14 || ' ' || reference14);
     END IF;
     IF label15 is not null then
       FND_FILE.PUT_LINE(FND_FILE.LOG, label15 || ' ' || reference15);
     END IF;
   END IF;
  */
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END log_message;

   PROCEDURE request_logs (p_job_id                 IN NUMBER,
                           p_program_name           IN VARCHAR2 DEFAULT NULL,
                           p_sub_program            IN VARCHAR2 DEFAULT NULL,
                           p_type                   IN VARCHAR2 DEFAULT NULL,
                           p_template_level         IN VARCHAR2 DEFAULT NULL,
                           p_template_level_value   IN VARCHAR2 DEFAULT NULL,
                           p_message1               IN VARCHAR2 DEFAULT NULL,
                           p_message2               IN VARCHAR2 DEFAULT NULL,
                           p_message3               IN VARCHAR2 DEFAULT NULL,
                           p_message4               IN VARCHAR2 DEFAULT NULL,
                           p_message5               IN VARCHAR2 DEFAULT NULL,
						   p_error_message           IN LONG DEFAULT NULL)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
      l_procedure_name   VARCHAR2 (20) := 'request_logs';
   BEGIN
      INSERT INTO canon_e479_request_log (job_id,
                                          program_name,
                                          sub_program,
                                          TYPE,
                                          template_level,
                                          template_level_value,
                                          message1,
                                          message2,
                                          message3,
                                          message4,
                                          message5,
										  error_message,
                                          creation_date)
           VALUES (p_job_id,
                   p_program_name,
                   p_sub_program,
                   p_type,
                   p_template_level,
                   p_template_level_value,
                   p_message1,
                   p_message2,
                   p_message3,
                   p_message4,
                   p_message5,
				   p_error_message,
                   SYSDATE);
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END request_logs;
   
   PROCEDURE get_profile_value(
      p_user_id               IN        NUMBER, 
      p_user_name             IN        VARCHAR2,
	  p_role_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_profile_value              OUT  VARCHAR2,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
	)
	IS 
	 l_procedure_name      VARCHAR2 (20) := 'get_profile_value';
	 lv_validation_status  VARCHAR2 (1);
	 lv_error_msg          VARCHAR2 (4000);
	 lv_user_name          AUTH_PSN.USR_NM%TYPE;
	 lv_role_name          ROLE.ROLE_NM%TYPE;
	 
	BEGIN 
	
	  IF p_user_id IS NOT NULL THEN 
	     BEGIN 
		     SELECT USR_NM
			   INTO lv_user_name
			   FROM AUTH_PSN
			  WHERE AUTH_PSN_ID = p_user_id;
		 EXCEPTION 
		   WHEN OTHERS THEN 
		      lv_user_name := NULL;
		 END;
	  ELSE 
	   lv_user_name := p_user_name;
	  END IF;
	   lv_role_name := p_role_name;
	 
	  CANON_E001_CODETABLE_UI_PKG.retrieve_profile_values(
      p_user_name             => lv_user_name,
	  p_role_name             => lv_role_name,
	  p_profile_name          => p_profile_name,
	  p_profile_value         => p_profile_value,
	  p_validation_status     => lv_validation_status,
	  p_error_msg             => lv_error_msg);
	 
	EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_profile_value;
   
   
   FUNCTION is_model_in_exclusion_list(
       p_model_num  IN VARCHAR2
   ) RETURN VARCHAR2
   IS
     lv_is_excluded VARCHAR2(1) := 'N';
   BEGIN
      
	  IF g_model_exclusion_list_tab.EXISTS(p_model_num) THEN 
	     lv_is_excluded := g_model_exclusion_list_tab(p_model_num).is_excluded;
	  ELSE 
		BEGIN
		  SELECT 'Y'
		    INTO  lv_is_excluded
		    FROM CANON_E479_ITM_MDL_EXCL_LST_V
		   WHERE 1=1 
		      AND item_model= p_model_num
			  AND enabled_flag = 'Y'
			  AND rownum =1;
		EXCEPTION
		WHEN OTHERS THEN
		  lv_is_excluded   := 'N';
		END;
		g_model_exclusion_list_tab(p_model_num).is_excluded := NVL(lv_is_excluded,'N');
	  END IF;   
     
      RETURN lv_is_excluded;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN 'N';
   END is_model_in_exclusion_list;                               
                                      
	
END CANON_E479_CUST_BILL_UTIL_PKG;
/

SHOW ERRORS;