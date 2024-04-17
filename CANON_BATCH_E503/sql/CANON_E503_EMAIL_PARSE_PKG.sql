CREATE OR REPLACE PACKAGE CANON_E503_EMAIL_PARSE_PKG
AS

   g_glbl_cmpy_cd       VARCHAR2 (10):= 'ADB';
   g_cancel_flg         VARCHAR2 (10) := '0';
   g_ascc_source_name   VARCHAR2 (30) := 'ASCC';
   g_ascc_merchant_id   VARCHAR2 (30) := '066029';


   PROCEDURE INSERT_DATA (
                          p_model                  IN VARCHAR,
                          p_serial_number      IN VARCHAR,                         
                          p_reading_date       IN VARCHAR,
                          p_total_reading       IN VARCHAR,                        
                          p_bw_reading          IN VARCHAR,
                          p_source                  IN VARCHAR    
                          );
                          
                          
    	PROCEDURE mail_data_load_proc;
      	
	                    
    PROCEDURE GET_NEW_REQUESTS(
                  new_requests         OUT sys_refcursor
                  );
                  
                  
   PROCEDURE UPDATE_REQUEST (
                          p_status                     IN VARCHAR,
                          p_errcode                 IN VARCHAR,
                          p_serial_number                 IN VARCHAR,
                          p_creation_date                 IN DATE);  
                            
                          
  
   PROCEDURE DEBUG_MSG (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);
                        
   PROCEDURE EMAIL_ARCH ( p_from_email IN VARCHAR2
			       ,p_subject IN VARCHAR2
			       ,p_body IN CLOB
			       ,p_message_id IN VARCHAR2
			       ,p_received_date IN DATE
			       ,o_id OUT NUMBER
			       ,o_status OUT VARCHAR2);			       			   	           
                       
END CANON_E503_EMAIL_PARSE_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E503_EMAIL_PARSE_PKG
AS
G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E503_EMAIL_PARSE_PKG';

   PROCEDURE INSERT_DATA (
                          p_model                  IN VARCHAR,
                          p_serial_number      IN VARCHAR,                         
                          p_reading_date       IN VARCHAR,
                          p_total_reading       IN VARCHAR,                        
                          p_bw_reading          IN VARCHAR,
                          p_source                  IN VARCHAR                                                      
                          )
   IS
      l_procedure_name VARCHAR2(100) := 'INSERT_DATA';
   BEGIN
    debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,p_serial_number,p_model,p_reading_date,p_total_reading,' p_bw_reading: '||p_bw_reading||' p_source: '||p_source);   
INSERT INTO CANON_E503_MAIL_DATA_TBL
                        (MODEL,
                         SERIAL_NUMBER,
                         READING_DATE,
                         TOTAL_READING,
                         BW_READING,
                         SOURCE)
                         VALUES
                           (
                            p_model,
                            p_serial_number,
                            p_reading_date,
                            p_total_reading,
                            p_bw_reading,
                            p_source
                            );    
      debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'After insert:'); 
      COMMIT;  
      EXCEPTION
   WHEN OTHERS THEN
   debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);                       
   END INSERT_DATA;
   
   
   
   
   
   
   
   
   PROCEDURE mail_data_load_proc 
   IS   
      l_procedure_name VARCHAR2(100) := 'mail_data_load_proc';
      l_msg_data        VARCHAR2 (100);
      v_source          VARCHAR2 (100) := NULL;
      v_instance_id     NUMBER;
      l_count           NUMBER;
      v_read_date       DATE;
      v_schedule_date   DATE;
      v_subline_id      NUMBER;
      lv_error_flag      VARCHAR2(1):='N';
      lv_err_msg         VARCHAR2(2000):=Null;

      CURSOR cur_lines
      IS
         SELECT aa.model, aa.ser_num serial_number, max(NVL(aa.reading_date,trunc(sysdate))) reading_date,
                max(aa.total_reading) total_reading, max(bw_reading) bw_reading, number_of_meters
           FROM
           
            (SELECT DISTINCT fv.description model, cmd.serial_number ser_num,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,
                                       canon_e503_mail_data_tbl cmd
                           WHERE fv.value = cmd.model
                             AND fv.enabled_flag = 'Y'
                             
                 UNION
                 SELECT DISTINCT fv.description model, csi.ser_num ,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,

                                 canon_e503_mail_data_tbl cmd,
                                 svc_mach_mstr csi
                           WHERE  fv.value = cmd.model
                             AND fv.enabled_flag = 'Y'
                             AND fv.description <> 'FX3000'
                             AND csi.ser_num LIKE
                                       (SUBSTR (cmd.serial_number, 0, 7)
                                       )
                                    || '%'
                                    || fv.description
			AND NVL (csi.eff_thru_dt,TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
			AND GLBL_CMPY_CD = g_glbl_cmpy_cd AND EZCANCELFLAG = g_cancel_flg
                             
                 UNION
                 SELECT DISTINCT fv.description model, csi.ser_num,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,

                                 canon_e503_mail_data_tbl cmd,
                                 svc_mach_mstr csi
                           WHERE  fv.value = cmd.model

                             AND fv.enabled_flag = 'Y'
                             AND fv.description <> 'FX3000'
                             
                             AND csi.ser_num LIKE
                                           (SUBSTR (cmd.serial_number, 0, 7)
                                           )
			AND NVL (csi.eff_thru_dt,TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
			AND GLBL_CMPY_CD = g_glbl_cmpy_cd AND EZCANCELFLAG = g_cancel_flg
			
                 UNION
                   SELECT DISTINCT fv.description model, csi.ser_num,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,

                                 canon_e503_mail_data_tbl cmd,
                                 svc_mach_mstr csi
                           WHERE  fv.value = cmd.model

                             AND fv.enabled_flag = 'Y'
                             AND fv.description = 'FX3000'
                             
                             AND csi.ser_num LIKE (SUBSTR (cmd.serial_number, 3, 10)
                                       )
                                    || '%'
                                    || fv.description
			AND NVL (csi.eff_thru_dt,TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
			AND GLBL_CMPY_CD = g_glbl_cmpy_cd AND EZCANCELFLAG = g_cancel_flg
			
                 UNION
                 SELECT DISTINCT fv.description model, csi.ser_num,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,

                                 canon_e503_mail_data_tbl cmd,
                                 svc_mach_mstr csi
                           WHERE  fv.value = cmd.model

                             AND fv.enabled_flag = 'Y'
                             AND fv.description = 'FX3000'
                             
                             AND csi.ser_num like '%'||SUBSTR (cmd.serial_number, 1, 7)
			AND NVL (csi.eff_thru_dt,TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
			AND GLBL_CMPY_CD = g_glbl_cmpy_cd AND EZCANCELFLAG = g_cancel_flg
                 
                 UNION
                 SELECT DISTINCT fv.description model, csi.ser_num,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,

                                 canon_e503_mail_data_tbl cmd,
                                 svc_mach_mstr csi
                            WHERE  fv.value = cmd.model

                             AND fv.enabled_flag = 'Y'
                             
                             AND cmd.serial_number like 'A%'
                             AND csi.ser_num like substr(cmd.serial_number,7,14)
			AND NVL (csi.eff_thru_dt,TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
			AND GLBL_CMPY_CD = g_glbl_cmpy_cd AND EZCANCELFLAG = g_cancel_flg
			
                 UNION
                 SELECT DISTINCT fv.description model, csi.ser_num,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,

                                 canon_e503_mail_data_tbl cmd,
                                 svc_mach_mstr csi
                            WHERE  fv.value = cmd.model

                             AND fv.enabled_flag = 'Y'
                             
                             AND cmd.serial_number like 'A%'
                             AND csi.ser_num like substr(cmd.serial_number,7,14)||'%'||fv.description
			AND NVL (csi.eff_thru_dt,TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
			AND GLBL_CMPY_CD = g_glbl_cmpy_cd AND EZCANCELFLAG = g_cancel_flg
			
                 UNION
                 SELECT DISTINCT fv.description model, csi.ser_num,
                                 cmd.reading_date, cmd.total_reading,
                                 cmd.bw_reading,
                                 fv.attribute1 number_of_meters
                            FROM CANON_E503_MODEL_SCAN_FORMAT_V fv,

                                 canon_e503_mail_data_tbl cmd,
                                 svc_mach_mstr csi
                           WHERE  fv.value = cmd.model

                             AND fv.enabled_flag = 'Y'
                             AND fv.description = 'FX3000'
                             
                             AND csi.ser_num LIKE
                                           (SUBSTR (cmd.serial_number, 3, 7)
                                           )
			AND NVL (csi.eff_thru_dt,TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
			AND GLBL_CMPY_CD = g_glbl_cmpy_cd AND EZCANCELFLAG = g_cancel_flg              
                             ) aa,
                svc_mach_mstr csi
          WHERE csi.ser_num = aa.ser_num
          GROUP BY aa.model, aa.ser_num, number_of_meters;
          
          
          
   BEGIN
      v_source  := NULL;
      
      FOR rec_lines IN cur_lines
      LOOP
                         debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Log-1',rec_lines.serial_number,NULL,NULL,NULL);                       

      -- 1 v_instance_id
         BEGIN
            SELECT SVC_MACH_MSTR_PK
              INTO v_instance_id
              FROM SVC_MACH_MSTR
             WHERE ser_num = rec_lines.serial_number;
         EXCEPTION
            WHEN TOO_MANY_ROWS
            THEN
               SELECT SVC_MACH_MSTR_PK
                 INTO v_instance_id
                 FROM SVC_MACH_MSTR
                WHERE ser_num LIKE
                            SUBSTR (ser_num,
                                    0,
                                    (LENGTH (rec_lines.serial_number) - 3)
                                   )
                         || '-SHP'
                         || UPPER (rec_lines.model);
            WHEN OTHERS
            THEN
               v_instance_id := NULL;
               debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'v_instance_id is null',rec_lines.serial_number,NULL,NULL,'Unexpected ERROR:'||lv_err_msg);                       

         END;

                         debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Log-2',rec_lines.serial_number,v_instance_id,NULL,NULL);                       


         v_read_date         := NULL;
         v_schedule_date   := NULL;
         v_subline_id          := NULL;
         lv_error_flag         :='N';
         lv_err_msg            :=Null;
         
         
-- 2  v_schedule_date/v_read_date

 v_read_date := SYSDATE;

      /*   BEGIN
          SELECT scheduled_date,
                 subline_id
           INTO  v_schedule_date,
                 v_subline_id
           FROM canon_e31_csi_product_search_v
           WHERE serial_number = TRIM (rec_lines.serial_number)
           AND EXISTS (
                    SELECT 1
                      FROM fnd_flex_value_sets ffvs, fnd_flex_values_vl ffv
                     WHERE ffvs.flex_value_set_name(+) = 'CANON_RD158_USG_STS'
                       AND ffv.flex_value_set_id = ffvs.flex_value_set_id
                       AND ffv.enabled_flag = 'Y'
               AND TRUNC (SYSDATE) BETWEEN TRUNC (NVL (ffv.start_date_active,
                                                       SYSDATE
                                                      )
                                                 )
                                       AND TRUNC (NVL (ffv.end_date_active,
                                                       SYSDATE
                                                      )
                                                 )
               AND flex_value = status);
         EXCEPTION
          WHEN TOO_MANY_ROWS
           THEN
               BEGIN
                  SELECT scheduled_date,
                         subline_id
                  INTO  v_schedule_date,
                     v_subline_id
                   FROM canon_e31_csi_product_search_v
                   WHERE serial_number = TRIM (rec_lines.serial_number)
                   AND EXISTS (
                        SELECT 1
                          FROM fnd_flex_value_sets ffvs, fnd_flex_values_vl ffv
                         WHERE ffvs.flex_value_set_name(+) = 'CANON_RD158_USG_STS'
                           AND ffv.flex_value_set_id = ffvs.flex_value_set_id
                           AND ffv.enabled_flag = 'Y'
                   AND TRUNC (SYSDATE) BETWEEN TRUNC (NVL (ffv.start_date_active,
                                                           SYSDATE
                                                          )
                                                     )
                                           AND TRUNC (NVL (ffv.end_date_active,
                                                           SYSDATE
                                                          )
                                                     )
                   AND flex_value = status)
                   AND ROWNUM = 1;
               EXCEPTION
                WHEN OTHERS
                 THEN
                  v_schedule_date := NULL;
               END;
           WHEN OTHERS
           THEN
            v_schedule_date := NULL;
         END;





        IF  v_schedule_date IS NOT NULL
        THEN
             BEGIN
              SELECT CANON_E31_METERREAD_ENTRY_PKG.READ_DATE_AUTO_POPUP
                     (v_schedule_date,
                     v_subline_id
                     )
              INTO v_read_date
              FROM DUAL;
             EXCEPTION
              WHEN OTHERS
               THEN
                v_read_date := SYSDATE;
             END;
        ELSE
            v_read_date := SYSDATE;
        END IF;  */





       IF rec_lines.bw_reading IS NOT NULL
       THEN
          IF rec_lines.number_of_meters <> 1 THEN 
            --- First Check if the data already exists in Stg Table

            BEGIN
                SELECT count(*)
                INTO l_count
                FROM canon_e503_mail_stg
                WHERE instance_id = v_instance_id
                AND model = TRIM (rec_lines.model)
                AND serial_number = TRIM (rec_lines.serial_number)
                AND no_of_meters = rec_lines.number_of_meters
                AND counter_type = 'BW'
                AND reading = TO_NUMBER (rec_lines.bw_reading)
                AND source = v_source
                AND trunc(reading_date) = trunc(v_read_date);
            EXCEPTION
                WHEN OTHERS THEN
                lv_error_flag:='Y';
                lv_err_msg:=Null;
                lv_err_msg:=SUBSTR(SQLERRM,1,2000);            
                   debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'2',rec_lines.serial_number,NULL,NULL,'Unexpected ERROR:'||lv_err_msg);                       
            END;
            IF lv_error_flag ='N' THEN -- Hari for ITG 481356
                 IF l_count = 0
                 THEN
                      BEGIN
                          INSERT INTO canon_e503_mail_stg
                           (user_id,
                            sequence_id,
                            instance_id, model,
                            serial_number,
                            no_of_meters, counter_type, reading_date,
                            reading, SOURCE,
                            extract_date, process_flag, ERROR_CODE,
                            error_desc, creation_date, created_by,
                            last_update_date, last_updated_by
                           )
                         VALUES ('-1',--fnd_global.user_id,
                            canon_e503_sequence_id_seq.NEXTVAL,
                            v_instance_id, TRIM (rec_lines.model),
                            TRIM (rec_lines.serial_number),
                            rec_lines.number_of_meters, 'BW', TRUNC(v_read_date),
                            TO_NUMBER (rec_lines.bw_reading), v_source,
                            SYSDATE, NULL, NULL,
                            NULL, SYSDATE, '-1',
                            SYSDATE, '-1'
                           );
                      EXCEPTION
                         WHEN OTHERS THEN
                          lv_error_flag:='Y';
                          lv_err_msg:=Null;
                          lv_err_msg:=SUBSTR(SQLERRM,1,2000);
                          debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'3',rec_lines.serial_number,NULL,NULL,'Error at the time of inserting BW Reading data in E503 Mail STG:'||lv_err_msg);                       
                      END;
                 ELSE
                    debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'4',rec_lines.serial_number,NULL,NULL,'Reading for this Date Already exists in Stage');                                           
                 END IF;
            END IF;-- Hari for ITG 481356
          END IF; --Added by Pradeep Gurumurthy for ITG #469229 on 09-17-2013
       END IF;

       COMMIT;


                                     debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Log-3',rec_lines.total_reading,NULL,NULL,NULL);                       

        IF rec_lines.total_reading IS NOT NULL
        THEN
            lv_error_flag:='N';
                                     debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'Log-4',rec_lines.serial_number,NULL,NULL,NULL);                       

             --- First Check if the data already exists in Stg Table for Total
             l_count := 0;
             BEGIN
                 SELECT count(*)
                 INTO l_count
                 FROM canon_e503_mail_stg
                 WHERE instance_id = v_instance_id
                 AND model = TRIM (rec_lines.model)
                 AND serial_number = TRIM (rec_lines.serial_number)
                 AND no_of_meters = rec_lines.number_of_meters
                 AND counter_type = 'TOTAL'
                 AND reading = TO_NUMBER (rec_lines.total_reading)
                 AND source = v_source
                 AND trunc(reading_date) = trunc(v_read_date);
             EXCEPTION
                WHEN OTHERS THEN
                lv_error_flag:='Y';
                lv_err_msg:=Null;
                lv_err_msg:=SUBSTR(SQLERRM,1,2000);
               debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'5',rec_lines.serial_number,NULL,NULL,'Unexpected ERROR:'||lv_err_msg);                       
             END;

           IF lv_error_flag = 'N' THEN
               IF l_count = 0
               THEN
                    BEGIN
                         INSERT INTO canon_e503_mail_stg
                             (user_id,
                              sequence_id,
                              instance_id, model,
                              serial_number,
                              no_of_meters, counter_type, reading_date,
                              reading, SOURCE,
                              extract_date, process_flag, ERROR_CODE,
                              error_desc, creation_date, created_by,
                              last_update_date, last_updated_by
                              )
                         VALUES ('-1',
                            canon_e503_sequence_id_seq.NEXTVAL,
                            v_instance_id, rec_lines.model,
                            rec_lines.serial_number,
                            rec_lines.number_of_meters, 'TOTAL', TRUNC(v_read_date),
                            TO_NUMBER (rec_lines.total_reading), v_source,
                            SYSDATE, NULL, NULL,
                            NULL, SYSDATE, '-1',
                            SYSDATE, '-1'
                           );
                    EXCEPTION
                         WHEN OTHERS THEN
                          lv_error_flag:='Y';
                          lv_err_msg:=Null;
                          lv_err_msg:=SUBSTR(SQLERRM,1,2000);
                          debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'6',rec_lines.serial_number,NULL,NULL,'Error at the time of inserting BW Reading data in E503 Mail STG:'||lv_err_msg);                       
                    END;
               ELSE
                     debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'7',rec_lines.serial_number,NULL,NULL,'Reading for this Date Already exists in Stage');                                           
               END IF;
           END IF; 
        END IF;
      END LOOP;

     BEGIN
         DELETE FROM canon_e503_mail_data_tbl;
         COMMIT;
      END; 
     

   EXCEPTION
      WHEN OTHERS
      THEN
               debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,'8',NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);                       
   END mail_data_load_proc;   
   
   
       
       
       PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO CANON_E503_ERRORS
           VALUES (l_program_name,
                   SUBSTR (l_attribute1, 200),
                   l_attribute2,
                   l_attribute3,
                   l_attribute4,
                   l_attribute5,
                   l_error_msg,
                   SYSDATE);

      COMMIT;
   END debug_msg;
   
   

PROCEDURE EMAIL_ARCH ( p_from_email IN VARCHAR2
			       ,p_subject IN VARCHAR2
			       ,p_body IN CLOB
			       ,p_message_id IN VARCHAR2
			       ,p_received_date IN DATE
			       ,o_id OUT NUMBER
			       ,o_status OUT VARCHAR2)
IS

lv_id  NUMBER;
  
BEGIN

	lv_id:=CANON_E503_EMAIL_ARCH_S.NEXTVAL;
	
	INSERT INTO CANON_E503_EMAIL_ARCH
	(id,subject ,body ,from_email ,creation_date,process_flag,message_id,received_date)
	VALUES(lv_id,p_subject,p_body,p_from_email,SYSDATE,'N',p_message_id,p_received_date);
	COMMIT;
o_id:=	lv_id;
o_status:='S';

delete from CANON_E503_EMAIL_ARCH where CREATION_DATE < sysdate - 30;
	COMMIT;
	
EXCEPTION
WHEN OTHERS THEN
o_id:=NULL;
o_status:='E';
          debug_msg (
                  l_program_name   => 'EMAIL_ARCH',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
   
END EMAIL_ARCH;




PROCEDURE GET_NEW_REQUESTS(
                  new_requests         OUT sys_refcursor
                  )
IS
   l_procedure_name VARCHAR2(100) := 'GET_NEW_REQUESTS';
       
BEGIN
   OPEN new_requests 
    FOR 
SELECT DISTINCT total.SERIAL_NUMBER,
       csi.SVC_MACH_MSTR_PK,
       (SELECT COUNT (*) FROM CANON_E503_MAIL_STG WHERE SERIAL_NUMBER = TOTAL.SERIAL_NUMBER AND CREATION_DATE = TOTAL.CREATION_DATE and process_flag is null) NO_OF_METERS,
       total.TOTAL_READS,
       TOTAL.BW_READS,
        TOTAL.READING_DATE,
       tp.svc_phys_mtr_pk TOTAL_PK,
       bp.svc_phys_mtr_pk BW_PK
  FROM 
  (  SELECT SERIAL_NUMBER,CREATION_DATE, READING_DATE,
                 TRIM (
                    LISTAGG (DECODE (COUNTER_TYPE, 'BW', READING, NULL), ' ')
                       WITHIN GROUP (ORDER BY SERIAL_NUMBER,TRUNC(CREATION_DATE)))
                    BW_READS,
                 TRIM (
                    LISTAGG (DECODE (COUNTER_TYPE, 'TOTAL', READING, NULL), ' ')
                    WITHIN GROUP (ORDER BY SERIAL_NUMBER,TRUNC(CREATION_DATE)))
                    TOTAL_READS
            FROM CANON_E503_MAIL_STG
           WHERE 1 = 1 AND process_flag IS NULL
        GROUP BY serial_number,CREATION_DATE,READING_DATE   ) TOTAL,     
       svc_mach_mstr csi,
       svc_phys_mtr tp,
       svc_phys_mtr bp
      WHERE 1 = 1
       AND csi.ser_num = TOTAL.SERIAL_NUMBER 
       AND tp.MDL_MTR_LB_NOTE_TXT ='Total Copies' 
       AND tp.SVC_MACH_MSTR_PK = csi.SVC_MACH_MSTR_PK 
       AND bp.MDL_MTR_LB_NOTE_TXT = 'BW Copies' 
       AND bp.SVC_MACH_MSTR_PK =csi.SVC_MACH_MSTR_PK 
      AND csi.GLBL_CMPY_CD = g_glbl_cmpy_cd AND csi.EZCANCELFLAG = g_cancel_flg
      AND tp.GLBL_CMPY_CD = g_glbl_cmpy_cd AND tp.EZCANCELFLAG = g_cancel_flg
      AND bp.GLBL_CMPY_CD = g_glbl_cmpy_cd AND bp.EZCANCELFLAG = g_cancel_flg
	   AND TOTAL_READS IS NOT NULL;
      
EXCEPTION
   WHEN OTHERS THEN
   debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END GET_NEW_REQUESTS;



 PROCEDURE UPDATE_REQUEST (
                          p_status                     IN VARCHAR,
                          p_errcode                 IN VARCHAR,
                          p_serial_number                 IN VARCHAR,
                          p_creation_date                 IN DATE)
                   
       IS    
       l_procedure_name VARCHAR2(100) := 'UPDATE_REQUEST';
       
       BEGIN
       update CANON_E503_MAIL_STG
                    set process_flag = p_status,
                    ERROR_DESC = p_errcode
                    where serial_number = p_serial_number
                    AND TRUNC(READING_DATE) = TRUNC(p_creation_date)
                    AND process_flag is null;
                     COMMIT; 
           EXCEPTION
           WHEN OTHERS THEN   
              debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
       
       END UPDATE_REQUEST;


END CANON_E503_EMAIL_PARSE_PKG;
/
SHOW ERRORS