/********************************************************************************************
  * TYPE/PURPOSE      :CANON_E471_SR_WEB_PKG                                       *
  * NAME              :                                                                                             *
  * INPUT PARAMETERS  : None                                                                          *
  * OUTPUT PARAMETERS : None                                                                        *
  * Assumptions       : None                                                                                   *
  * Pending Issues    : None                                                                                   *
  * Author                      Date          Version     Description                                     *
  * ----------------------------------------------------------------------------------------        *
  * Ajay Gurram        02-13-2017      1.0     INITIAL REVISION                           *
  ********************************************************************************************/
  
  
CREATE OR REPLACE PACKAGE CANON_E471_SR_WEB_PKG
AS

   g_glbl_cmpy_cd       VARCHAR2 (10):= 'ADB';
   g_cancel_flg         VARCHAR2 (10) := '0';
   g_ascc_source_name   VARCHAR2 (30) := 'ASCC';
   g_ascc_merchant_id   VARCHAR2 (30) := '066029';

  PROCEDURE INSERT_DATA (
                          p_company_value           IN VARCHAR,
                          p_requested_by_value          IN VARCHAR,
                          
                          p_address1_value       IN VARCHAR,
                          p_address2_value          IN VARCHAR,
                          p_city_value       IN VARCHAR,
                          p_state_value   IN VARCHAR,
                          
                          p_postal_code_value       IN VARCHAR,
                          p_email_address_value          IN VARCHAR,
                          p_phone_value       IN VARCHAR,
                          p_serial_value   IN VARCHAR,
                                          
                          p_sr_issue_value       IN VARCHAR,
                          p_service_type       IN VARCHAR,
                          p_sp_instruct_value       IN VARCHAR,
                          p_incident_value   IN VARCHAR,
                          
                          p_content_process_status       IN VARCHAR,
                          p_content_process_message   IN VARCHAR,
                          p_contact_name              IN  VARCHAR2,
                          p_contact_phone             IN  VARCHAR2,
                          p_contact_email             IN VARCHAR2,
                          p_source                    IN VARCHAR2
                          );
                          
    PROCEDURE GET_NEW_REQUESTS(
                  new_requests         OUT sys_refcursor
                  );
                  
     PROCEDURE GET_REQUESTS_FOR_NOTIFICATION(
                  requests         OUT sys_refcursor
                  );
           

   PROCEDURE UPDATE_REQUEST (
                          p_incident_id             IN VARCHAR,
                          p_status                     IN VARCHAR,
                          p_errcode                 IN VARCHAR,
                          p_serial_number                 IN VARCHAR,
                          p_creation_date                 IN DATE);     
                          
    PROCEDURE UPDATE_EMAIL_FLAG (
                          p_serial_number                 IN VARCHAR,
                          p_creation_date                 IN DATE);     
                          
    PROCEDURE VALIDATE_IS_BILLABLE (
                          p_serial_number                 IN VARCHAR,
                          p_bill_cd                            IN VARCHAR,
                          p_creation_date                 IN DATE,
                          o_bllbl_flg                          OUT VARCHAR);
  
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
			       
			       
			       PROCEDURE VALIDATE_CALL;

   PROCEDURE SR_STATE_EMAIL (
                          p_state                 IN VARCHAR,
                          P_source                IN VARCHAR2,
                          o_email                 OUT VARCHAR,
                          o_contact_phone         OUT VARCHAR2
                          );	           
                       
END CANON_E471_SR_WEB_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E471_SR_WEB_PKG
AS
G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E471_SR_WEB_PKG';

   PROCEDURE INSERT_DATA (
                          p_company_value           IN VARCHAR,
                          p_requested_by_value          IN VARCHAR,
                          
                          p_address1_value       IN VARCHAR,
                          p_address2_value          IN VARCHAR,
                          p_city_value       IN VARCHAR,
                          p_state_value   IN VARCHAR,
                          
                          p_postal_code_value       IN VARCHAR,
                          p_email_address_value          IN VARCHAR,
                          p_phone_value       IN VARCHAR,
                          p_serial_value   IN VARCHAR,
                                          
                          p_sr_issue_value       IN VARCHAR,
                          p_service_type       IN VARCHAR,
                          p_sp_instruct_value       IN VARCHAR,
                          p_incident_value   IN VARCHAR,
                          
                          p_content_process_status       IN VARCHAR,
                          p_content_process_message   IN VARCHAR,
                          p_contact_name              IN  VARCHAR2,
                          p_contact_phone             IN  VARCHAR2,
                          p_contact_email             IN VARCHAR2,
                          p_source                    IN VARCHAR2
                          )
   IS
      l_procedure_name                  VARCHAR2(100) := 'INSERT_DATA';
      lv_mdl_id                         VARCHAR2(50);
       x_call_type                      VARCHAR2 (100);
       x_call_type_id                   VARCHAR2 (100);     
       l_call_type_id                   VARCHAR2(20);
       l_call_type                      VARCHAR2(50);    
   BEGIN
        BEGIN
         SELECT MODEL 
         INTO lv_mdl_id
         FROM CANON_E307_MACH_DTLS_V v, MDL_NM mdl
         WHERE v.ser_num = UPPER(p_serial_value)
         AND mdl.t_mdl_nm = v.model
         AND rownum = 1;
       EXCEPTION
               WHEN OTHERS
               THEN
                  lv_mdl_id := NULL;
        END;
          BEGIN
              SELECT ds_svc_call_tp_cd, XTRNL_CALL_TP_REF_TXT
                  INTO l_call_type_id, l_call_type
              FROM ds_svc_call_tp citv
              WHERE glbl_cmpy_cd = g_glbl_cmpy_cd
              AND ezcancelflag = g_cancel_flg
               AND citv.XTRNL_CALL_TP_REF_TXT = p_service_type;
      EXCEPTION
          WHEN OTHERS
              THEN
                  l_call_type_id := NULL;
                  l_call_type := NULL;
                 debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
      END; 
        BEGIN 
             CANON_E307_CREATE_SR_PKG.check_call_type (p_in_serial      => UPPER(p_serial_value),
                 p_in_mdl_id      => lv_mdl_id,
                 p_call_type_id   => l_call_type_id,
                 p_call_type      => l_call_type,
                 p_source         => 'SR',
                 x_call_type      => x_call_type,
                 x_call_type_id   => x_call_type_id);
        EXCEPTION
              WHEN OTHERS
                  THEN
                      x_call_type_id := NULL;
                      x_call_type := NULL;
                      debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
        END;   
   
   --DBMS_OUTPUT.put_line('In INSERT_DATA');
INSERT INTO CANON_E471_SR_WEB_TBL
                        (COMPANY,
                         REQUESTED_BY,
                         ADDRESS,
                         ADDRESS2,
                         CITY,
                         STATE,
                         ZIP_CODE,
                         EMAIL_ADDRESS,
                         PHONE_NUMBER,
                         FAX_NUMBER,
                         SERIAL_NUMBER,
                         METER_READ,
                         PROBLEM,
                         CREATION_DATE,
                         PROCESSED_FLAG,
                         ERROR_MESSAGE,
                         SOURCE,
                         SOURCE_EMAIL,
                         SERVICE_TYPE,
                         SPECIAL_INSTRUCTIONS,
                         CASE_NUMBER,
                         EMAIL_SOURCE_SEQUENCE_ID,
                         LAST_UPDATE_DATE,
                         CONTACT_NAME,
                         CONTACT_PHONE,
                         CONTACT_EMAIL)
                         VALUES
                           (
                            p_company_value,
                            p_requested_by_value,
                            p_address1_value,
                            p_address2_value,
                            p_city_value,
                            p_state_value,
                            p_postal_code_value,
                            p_email_address_value,
                            p_phone_value,
                            null, --- Fax Number
                            UPPER(p_serial_value),  --- 28-MAY-2013 : Make everything upper Serial Number
                            null, --- Meter Read
                            p_sr_issue_value, --- Problem
                            SYSDATE,
                            p_content_process_status,
                            p_content_process_message, --- Process Message
                            p_source,
                            p_email_address_value,
                            x_call_type,
                            SUBSTR(p_sp_instruct_value,1,500), --- ITG 534867 Special Instructions Restricted to 500                           
                            p_incident_value, --- Case Number
                            null,
                            SYSDATE,
                            p_contact_name,
                            p_contact_phone,
                            p_contact_email
                            );    
      COMMIT;  
      EXCEPTION
   WHEN OTHERS THEN
   debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);                       
   END INSERT_DATA;
   
 
  PROCEDURE GET_NEW_REQUESTS(
                  new_requests         OUT sys_refcursor
                  )
IS
   l_procedure_name VARCHAR2(100) := 'GET_NEW_REQUESTS';
       
BEGIN
   OPEN new_requests 
    FOR 
   SELECT COMPANY,
                         REQUESTED_BY,
                         ADDRESS,
                         ADDRESS2,
                         CITY,
                         STATE,
                         ZIP_CODE,
                         EMAIL_ADDRESS,
                         PHONE_NUMBER,
                         FAX_NUMBER,
                         SERIAL_NUMBER,
                         METER_READ,
                         PROBLEM,
                         CREATION_DATE,
                         PROCESSED_FLAG,
                         ERROR_MESSAGE,
                         SOURCE,
                         SOURCE_EMAIL,
                         SERVICE_TYPE,
                         DS_SVC_CALL_TP_CD,
                         SPECIAL_INSTRUCTIONS,
                         CASE_NUMBER,
                         SVC_MACH_MSTR_PK,
                         CONTACT_NAME,
                         CONTACT_PHONE,
                         CONTACT_EMAIL
     FROM CANON_E471_SR_WEB_TBL, SVC_MACH_MSTR ib,ds_svc_call_tp ctp
    WHERE CANON_E471_SR_WEB_TBL.SERIAL_NUMBER =ib.SER_NUM
    AND CANON_E471_SR_WEB_TBL.SERVICE_TYPE = ctp.XTRNL_CALL_TP_REF_TXT
    AND ctp.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND ctp.ezcancelflag = g_cancel_flg
    AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND ib.ezcancelflag = g_cancel_flg
    AND PROCESSED_FLAG = 'N';
EXCEPTION
   WHEN OTHERS THEN
   debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END GET_NEW_REQUESTS;


  PROCEDURE GET_REQUESTS_FOR_NOTIFICATION(
                  requests         OUT sys_refcursor
                  )
IS
   l_procedure_name VARCHAR2(100) := 'GET_REQUESTS_TO_SEND_NOTIFICATION';
       
BEGIN
   OPEN requests 
    FOR 
   SELECT COMPANY,
                         REQUESTED_BY,
                         ADDRESS,
                         ADDRESS2,
                         CITY,
                         STATE,
                         ZIP_CODE,
                         EMAIL_ADDRESS,
                         PHONE_NUMBER,
                         FAX_NUMBER,
                         SERIAL_NUMBER,
                         METER_READ,
                         PROBLEM,
                         CREATION_DATE,
                         PROCESSED_FLAG,
                         ERROR_MESSAGE,
                         SOURCE,
                         SOURCE_EMAIL,
                         SERVICE_TYPE,
                         SPECIAL_INSTRUCTIONS,
                         CASE_NUMBER,
                         EMAIL_SENT_FLAG,
                         INCIDENT_ID
     FROM CANON_E471_SR_WEB_TBL
    WHERE PROCESSED_FLAG <> 'N' AND NVL(EMAIL_SENT_FLAG,'N') <> 'Y';
    
EXCEPTION
   WHEN OTHERS THEN   
   debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END GET_REQUESTS_FOR_NOTIFICATION;



   PROCEDURE UPDATE_REQUEST (
                          p_incident_id             IN VARCHAR,
                          p_status                     IN VARCHAR,
                          p_errcode                 IN VARCHAR,
                          p_serial_number                 IN VARCHAR,
                          p_creation_date                 IN DATE)
                   
       IS    
       l_procedure_name VARCHAR2(100) := 'UPDATE_REQUEST';
       
       BEGIN
       update CANON_E471_SR_WEB_TBL
                    set processed_flag = p_status,
                    error_message = p_errcode,
                    last_update_date = sysdate , 
                    INCIDENT_NUMBER = p_incident_id, 
                    INCIDENT_ID = p_incident_id
                    where serial_number = p_serial_number
                    AND creation_date = p_creation_date;
                     COMMIT; 
           EXCEPTION
           WHEN OTHERS THEN   
              debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
       
       END UPDATE_REQUEST;
       
       
          PROCEDURE UPDATE_EMAIL_FLAG (
                          p_serial_number                 IN VARCHAR,
                          p_creation_date                 IN DATE)
                          
       IS 
       l_procedure_name VARCHAR2(100) := 'UPDATE_EMAIL_FLAG';
       BEGIN
       
       update CANON_E471_SR_WEB_TBL
                    set EMAIL_SENT_FLAG = 'Y',
                     last_update_date = sysdate 
                    where serial_number = p_serial_number
                    AND creation_date = p_creation_date;
                     COMMIT; 
           EXCEPTION
           WHEN OTHERS THEN   
              debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
       
       END UPDATE_EMAIL_FLAG;
       
       
                 PROCEDURE VALIDATE_IS_BILLABLE (
                          p_serial_number                 IN VARCHAR,
                          p_bill_cd                            IN VARCHAR,
                          p_creation_date                 IN DATE,
                          o_bllbl_flg                          OUT VARCHAR)                       
       IS 
       l_procedure_name VARCHAR2(100) := 'VALIDATE_IS_BILLABLE';
       BEGIN
       
         SELECT BLLBL_FLG
                          INTO o_bllbl_flg
                          FROM SVC_BILL_TP 
                          WHERE 1 = 1
                           AND ezcancelflag = g_cancel_flg
                           AND glbl_cmpy_cd = g_glbl_cmpy_cd
                           AND  SVC_BILL_TP_CD =   p_bill_cd                       
                           AND BLLBL_FLG = 'N';
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    o_bllbl_flg := 'Y';
                    update CANON_E471_SR_WEB_TBL
                    set processed_flag = 'E',
                    last_update_date = sysdate , 
                    error_message = 'The Customer is trying to create a call against a machine that is billable'
                    where serial_number = p_serial_number
                    AND creation_date = p_creation_date;
                    COMMIT; 
           WHEN OTHERS THEN   
              debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
       END VALIDATE_IS_BILLABLE;

   


PROCEDURE VALIDATE_CALL IS

      l_procedure_name VARCHAR2(100) := 'VALIDATE_CALL';
      l_errbuf                          varchar2(1000);
      l_errcode                         VARCHAR2(1000);
      l_incident                        varchar2(100);
      l_status                          VARCHAR2(100);
      l_state                           varchar2(100);
      l_postal_code                     varchar2(100);
      l_validation_msg                  varchar2(1000);
      l_serial_number                   VARCHAR2(240);
       l_serial_prefix_count            NUMBER;
       lv_dps_part_cont_flag            VARCHAR2(1):=NULL;
       lv_dps_part_srno_flag            VARCHAR2(1):=NULL;
       l_category_id                    NUMBER;
       l_call_type_id                   VARCHAR2(50);
       l_call_type                      VARCHAR2(50);
       l_incident_id                    NUMBER;
       l_bill_code                      VARCHAR2(30);
       l_osi_serial_count               NUMBER;
       v_planned_sdate                  DATE;
       v_sla_respone_time               NUMBER := 0;
       v_respond_by                     DATE;
       v_dest_date                      DATE;
       v_msg_count                      NUMBER;
       v_msg_data                       VARCHAR2 (100);
       v_return_status                  VARCHAR2 (10);
       l_dest_date                      DATE;
       l_msg_count                      NUMBER;
       l_msg_data                       VARCHAR2 (100);
       l_return_status                  VARCHAR2 (10);
       lv_mdl_id                        VARCHAR2 (20);
       x_call_type                      VARCHAR2 (100);
       x_call_type_id                   VARCHAR2 (100);

     CURSOR machines IS
      select * from CANON_E471_SR_WEB_TBL
      where processed_flag =  'N';

      
   BEGIN
      --- Service Type is NULL then set it to  '1-SERV CALL'
      UPDATE CANON_E471_SR_WEB_TBL
        SET SERVICE_TYPE = '1-SERV CALL'
      WHERE SERVICE_TYPE IS NULL;
       COMMIT; 
      
      FOR rec_machines IN machines
      LOOP
          l_errbuf         := NULL;
          l_errcode        := NULL;
          l_incident       := NULL;
          l_status         := NULL;
          l_state          := NULL;
          l_postal_code    := NULL;
          l_validation_msg := NULL;
          l_serial_number  := NULL;
          l_serial_prefix_count := NULL;
          l_serial_number := NULL;
          lv_dps_part_cont_flag:=NULL;
          lv_dps_part_srno_flag:=NULL;
          l_incident_id := NULL;
          l_bill_code := NULL;
          l_osi_serial_count := 0;
          
             --- Match based on Substring of Serial Number for S21 Side
            SELECT count(*)
            into l_serial_prefix_count
            FROM SVC_MACH_MSTR
            WHERE 1 = 1
            AND (SUBSTR(upper(SER_NUM),1, decode(INSTR(upper(SER_NUM),'-',1,1),0,100,
                                                     INSTR(upper(SER_NUM),'-',1,1) - 1)) = UPPER(SUBSTR(upper(rec_machines.serial_number),1,
                                                     decode(INSTR(upper(rec_machines.serial_number),'-',1,1),0,100,
                                                     INSTR(upper(rec_machines.serial_number),'-',1,1) - 1)
                                                     ))
                     OR upper(CUST_MACH_CTRL_NUM) =UPPER(SUBSTR(upper(rec_machines.serial_number),1,
                                                     decode(INSTR(upper(rec_machines.serial_number),'-',1,1),0,100,
                                                     INSTR(upper(rec_machines.serial_number),'-',1,1) - 1)
                                                     ))) ;
                                                     
            --- If there is one and only serial number in that match then
            IF l_serial_prefix_count = 1 THEN
                BEGIN
                  SELECT SER_NUM
                          INTO l_serial_number
                          FROM SVC_MACH_MSTR 
                          WHERE 1 = 1
                            AND (SUBSTR(upper(SER_NUM),1, decode(INSTR(upper(SER_NUM),'-',1,1),0,100,
                                                     INSTR(upper(SER_NUM),'-',1,1) - 1)) = UPPER(SUBSTR(upper(rec_machines.serial_number),1,
                                                     decode(INSTR(upper(rec_machines.serial_number),'-',1,1),0,100,
                                                     INSTR(upper(rec_machines.serial_number),'-',1,1) - 1)
                                                     ))
                          OR  upper(CUST_MACH_CTRL_NUM) =UPPER(SUBSTR(upper(rec_machines.serial_number),1,
                                                     decode(INSTR(upper(rec_machines.serial_number),'-',1,1),0,100,
                                                     INSTR(upper(rec_machines.serial_number),'-',1,1) - 1)
                                                     )))
                          AND ROWNUM = 1;
                EXCEPTION
                       WHEN OTHERS THEN
                       l_serial_number := NULL;
                       l_validation_msg :=  'Serial doesnt exists in s21';
                END;
                
             --- If there are Multiple Serial Numbers then get the one that matches based on Postal Code    
            ELSIF l_serial_prefix_count > 1 THEN
                BEGIN
                    SELECT SER_NUM
                          INTO l_serial_number
                          FROM SVC_MACH_MSTR 
                          WHERE 1 = 1
                           AND (SUBSTR(upper(SER_NUM),1, decode(INSTR(upper(SER_NUM),'-',1,1),0,100,
                                                     INSTR(upper(SER_NUM),'-',1,1) - 1)) = UPPER(SUBSTR(rec_machines.serial_number,1,
                                                     decode(INSTR(upper(rec_machines.serial_number),'-',1,1),0,100,
                                                     INSTR(upper(rec_machines.serial_number),'-',1,1) - 1)
                                                     ))
                                  OR  upper(CUST_MACH_CTRL_NUM) = UPPER(SUBSTR(rec_machines.serial_number,1,
                                                     decode(INSTR(upper(rec_machines.serial_number),'-',1,1),0,100,
                                                     INSTR(upper(rec_machines.serial_number),'-',1,1) - 1)
                                                     )))                            
                                  AND SUBSTR(POST_CD,1,5) = SUBSTR(rec_machines.zip_code,1,5);
 
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                        l_validation_msg :=  'Postal Code Mismatch';
                  WHEN TOO_MANY_ROWS THEN
                        l_validation_msg :=  'Postal Code Mismatch';
                END;
            ELSIF l_serial_prefix_count = 0 THEN
                    l_serial_number := NULL;
                    l_validation_msg :=  'Serial doesnt exists in s21';
            END IF;
            
            -- Validation 2 
            -- Check to see if the serail Number has  SVC_MACH_MAINT_AVAL_FLG enabled (Should be disabled if its OSI serial number)
            IF l_serial_number IS NOT NULL
            THEN
              BEGIN
                    SELECT SER_NUM
                          INTO l_serial_number
                          FROM SVC_MACH_MSTR 
                          WHERE 1 = 1
                           AND ezcancelflag = g_cancel_flg
                           AND glbl_cmpy_cd = g_glbl_cmpy_cd
                           AND  SER_NUM =   l_serial_number                       
                           AND SVC_MACH_MAINT_AVAL_FLG = 'Y'
                           AND SVC_MACH_TP_CD = 1;
 
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    l_serial_number := NULL;
                    l_validation_msg :=  'SVC_MACH_MAINT_AVAL_FLG is not enabled';
                  END;
                END IF;
            
            
            
            
       debug_msg(G_PACKAGE_NAME,l_procedure_name,l_serial_number,'Status:'||l_status,'SVC_MACH_MAINT_AVAL_FLG',NULL,'Debug1:'||l_validation_msg);
     
           
            IF l_serial_number IS NOT NULL
            THEN
            
            -- Validation for state and Postal Code mismatch
              /*     BEGIN
                         SELECT ST_CD
                         INTO l_state
                         FROM SVC_MACH_MSTR
                         WHERE SER_NUM = l_serial_number;
                   END;
                   
                   BEGIN
                         SELECT POST_CD
                         INTO l_postal_code
                         FROM SVC_MACH_MSTR
                         WHERE SER_NUM = l_serial_number;
                   END;*/
				   
					BEGIN
					  SELECT  ship_to.ST_CD STATE,
							ship_to.POST_CD POSTAL_CODE
							 INTO l_state, l_postal_code
					   FROM svc_mach_mstr ib, ship_to_cust ship_to
					  WHERE     ib.ser_num = l_serial_number                 
							AND ib.svc_mach_tp_cd = '1'
							AND NVL (ship_to.eff_thru_dt,
									 TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
								   TO_CHAR (SYSDATE, 'YYYYMMDD')
							AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
								   TO_CHAR (SYSDATE, 'YYYYMMDD')
							AND ib.glbl_cmpy_cd = 'ADB'
							AND ib.ezcancelflag = '0'
							AND ship_to.ship_to_cust_cd = ib.cur_loc_num 
							AND ROWNUM = 1;
					EXCEPTION WHEN OTHERS THEN
						l_state:='';
						l_postal_code:='';
					END;				   

                   IF NVL(l_state, 'PQR') <> NVL(rec_machines.state, 'PQR') THEN
                        l_validation_msg := 'State Mismatch';
                   END IF;

                   IF NVL(SUBSTR(l_postal_code,1,5), '99999') <> NVL(SUBSTR(rec_machines.zip_code,1,5), '99999') THEN
                        l_validation_msg := 'Postal Code Mismatch';
                   END IF;
                   
       


                    --- 4: Get Call Type Id for service type
                    BEGIN
                            SELECT ds_svc_call_tp_cd, XTRNL_CALL_TP_REF_TXT
                                INTO l_call_type_id, l_call_type
                            FROM ds_svc_call_tp citv
                            WHERE glbl_cmpy_cd = g_glbl_cmpy_cd
                            AND ezcancelflag = g_cancel_flg
                             AND citv.XTRNL_CALL_TP_REF_TXT = rec_machines.SERVICE_TYPE;
                    EXCEPTION
                        WHEN OTHERS
                            THEN
                                l_call_type_id := NULL;
                                l_call_type := NULL;
                                l_validation_msg := 'Could not get Call Type Id for Service Type - '||rec_machines.SERVICE_TYPE;
                                debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
                    END; 
        
   -- 5: Get category
                    BEGIN
                        SELECT MDSE_ITEM_TP_CD        
                        INTO l_category_id               
                        FROM SVC_MACH_MSTR ib, MDSE
                        WHERE MDSE.MDSE_CD = ib.MDSE_CD
                         AND ib.ser_num = l_serial_number
                         AND MDSE.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND MDSE.ezcancelflag = g_cancel_flg
                         AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND ib.ezcancelflag = g_cancel_flg                 
                         AND ROWNUM = 1;
                    EXCEPTION
                    WHEN OTHERS
                        THEN
                            l_validation_msg := 'Could not get category Id';
                            debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,l_serial_number,'Get category',NULL,NULL,'Unexpected ERROR:'||SQLERRM);

                    END;
          END IF;

debug_msg(G_PACKAGE_NAME,l_procedure_name,l_serial_number,'Status:'||l_status,'Get category',NULL,'Debug2: '||l_validation_msg);
          IF (l_serial_number IS NOT NULL
                and l_state = rec_machines.state
                and SUBSTR(l_postal_code,1,5) = SUBSTR(rec_machines.zip_code,1,5)
                and l_validation_msg IS NULL )
          THEN
          
          -- Check for duplicate calls 
           BEGIN         
                    select  FSR_NUM   
                     INTO l_incident_id     
                     from FSR
                    where 1=1
                    AND FSR_STS_CD = '10' -- open
                    AND SER_NUM = l_serial_number
                    AND FSR_TP_CD = l_call_type_id
                    AND glbl_cmpy_cd = g_glbl_cmpy_cd
                    AND ezcancelflag = g_cancel_flg                 
                    AND ROWNUM = 1;              
                    EXCEPTION
                    WHEN OTHERS
                        THEN
                            l_incident_id := null;
                    END;
                    
                    IF l_incident_id is NOT NULL
                    THEN
                    l_status := 'E';
                     l_validation_msg := 'Service Request ' ||  l_incident_id || ' already exists for the Serial Number ' ||  l_serial_number;
                   END IF;
debug_msg(G_PACKAGE_NAME,l_procedure_name,l_serial_number,'Status:'||l_status,'Check for duplicate calls ',NULL,'Debug3:'||l_validation_msg);
    
               
                    IF l_status = 'E' THEN
                            update CANON_E471_SR_WEB_TBL
                            set processed_flag = 'E',
                            last_update_date = sysdate , 
                            error_message = l_validation_msg
                            where serial_number = rec_machines.serial_number
                            AND creation_date = rec_machines.creation_date;
                            COMMIT; 
                    END IF;
          ELSE
                    update CANON_E471_SR_WEB_TBL
                    set processed_flag = 'M',
                    last_update_date = sysdate , 
                    error_message = l_validation_msg
                    where serial_number = rec_machines.serial_number
                    AND creation_date = rec_machines.creation_date;
                    COMMIT; 
          END IF;
      END LOOP;

   EXCEPTION
      WHEN OTHERS THEN
         debug_msg(G_PACKAGE_NAME,l_procedure_name,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
   END VALIDATE_CALL;






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
      INSERT INTO CANON_E471_ERRORS
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

	lv_id:=CANON_E471_EMAIL_ARCH_S.NEXTVAL;
	
	INSERT INTO CANON_E471_EMAIL_ARCH
	(id,subject ,body ,from_email ,creation_date,process_flag,message_id,received_date)
	VALUES(lv_id,p_subject,p_body,p_from_email,SYSDATE,'N',p_message_id,p_received_date);
	COMMIT;
o_id:=	lv_id;
o_status:='S';

delete from CANON_E471_EMAIL_ARCH where CREATION_DATE < sysdate - 30;
	COMMIT;
	
EXCEPTION
WHEN OTHERS THEN
o_id:=NULL;
o_status:='E';
          debug_msg (
                  l_program_name   => 'EMAIL_ARCH',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
   
END EMAIL_ARCH;



    PROCEDURE SR_STATE_EMAIL (
                          p_state                 IN VARCHAR,
                          P_source                IN VARCHAR2,
                          o_email                 OUT VARCHAR,
                          o_contact_phone         OUT VARCHAR2
                          )
      is
        l_procedure_name VARCHAR2(100) := 'SR_STATE_EMAIL';
        v_recepient VARCHAR2(100) := Null;
       BEGIN
       BEGIN
         SELECT EMAIL_ID
                          INTO o_email
                          FROM CANON_E471_SR_STATE_EMAIL_V 
                          WHERE 1 = 1               
                           AND STATE = p_state;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN 
                  BEGIN
                          SELECT EMAIL_ID
                          INTO o_email
                          FROM CANON_E471_SR_STATE_EMAIL_V 
                          WHERE 1 = 1               
                           AND STATE IS NULL;
                  EXCEPTION WHEN OTHERS THEN
                    o_email:='';
                  END;
                  WHEN OTHERS THEN   
                   debug_msg(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);   
        END;
         IF NVL(o_email,'X') = 'X' THEN
             BEGIN
               SELECT VALUE 
               INTO  o_email
               FROM CANON_E471_SR_EMAIL_INFO_V
              WHERE ENBL_FLG = 'Y'
              AND SOURCE = 'CANON_E471_SR_WEB_EMAIL'
               AND ROWNUM =1;
              EXCEPTION
                 WHEN OTHERS THEN
                 o_email:='';
             END;  
        END IF;
       IF P_source = 'EMAIL_FROM_CC'
               THEN
               BEGIN
                   SELECT VALUE 
                   INTO o_email
                   FROM CANON_E471_SR_EMAIL_INFO_V
                    WHERE ENBL_FLG ='Y'
                    AND SOURCE = 'CANON_I165_SR_EXCEP_EMAIL'
                   AND rownum =1;
                 EXCEPTION
                    WHEN OTHERS THEN
                      o_email := Null;
                 END;        
         END IF;
        BEGIN
          SELECT VALUE 
          INTO o_contact_phone
          FROM CANON_E471_SR_EMAIL_INFO_V
          WHERE ENBL_FLG = 'Y'
          AND SOURCE = 'CANON_E471_SR_PHONE_NUM'
          AND rownum =1;
        EXCEPTION WHEN OTHERS
        THEN
          o_contact_phone:='';
        END;

       END SR_STATE_EMAIL;  
                       

END CANON_E471_SR_WEB_PKG;
/
SHOW ERRORS