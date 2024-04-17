CREATE OR REPLACE PACKAGE CANON_E663_SERVICE_EMAIL_PKG AS

TYPE REFCURSOR IS REF CURSOR;

  PROCEDURE GET_SR_CALL_INFO(X_EMAIL_INFO_CUR   OUT  REFCURSOR,
                            X_PHONE_NUMBER     OUT  VARCHAR2
                             );

  PROCEDURE GET_EMAIL_ADDRESSES(P_PARTY_NUMBER   IN   VARCHAR2,
                                 X_EMAIL_ADRS_CUR OUT REFCURSOR
                                 );
  PROCEDURE INSERT_EMAIL_INFO(P_SERIAL_NUMBER   IN    VARCHAR2,
                              P_MODEL           IN    VARCHAR2,
                              P_INCIDENT_NUMBER IN    VARCHAR2,
                              P_INCIDENT_STATUS IN    VARCHAR2,
                              P_INCIDENT_DATE   IN    VARCHAR2,
                              P_TASK_NUMBER     IN    VARCHAR2,
                              P_TASK_STATUS     IN    VARCHAR2,
                              P_EMAIL_SENT      IN    VARCHAR2,
                              x_retcode         OUT   NUMBER);
  PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);
END CANON_E663_SERVICE_EMAIL_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E663_SERVICE_EMAIL_PKG AS

  PROCEDURE GET_SR_CALL_INFO(X_EMAIL_INFO_CUR   OUT  REFCURSOR,
                             X_PHONE_NUMBER     OUT  VARCHAR2
                             )
  AS
  l_party_number    VARCHAR2(100);
  l_email_address   VARCHAR2(100);
  l_contact         VARCHAR2(100);

  BEGIN
   OPEN X_EMAIL_INFO_CUR FOR
          SELECT * from CANON_E663_SERVICE_EMAIL_V V
          WHERE 1=1
          AND EXISTS(
                     SELECT 1
                       FROM svc_mach_mstr ib1, ship_to_cust ship_to
                      WHERE 1=1
                        AND ib1.ser_num = v.SERIAL_NUMBER                 --'HHOZDYYHSH'
                        AND ib1.svc_mach_tp_cd = '1'
                        AND NVL (ship_to.eff_thru_dt,
                                     TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND ib1.glbl_cmpy_cd = 'ADB'
                        AND ib1.ezcancelflag = '0'
                        AND ship_to.ship_to_cust_cd = ib1.cur_loc_num
                        AND ib1.OWNR_ACCT_NUM in (SELECT ACCOUNT_NUMBER FROM CANON_E663_CUSTOMER_DTLS_V) --'3076107'
                      )
         AND NOT EXISTS (SELECT 1
                           FROM CANON_E663_EXCL_TASK_STATUS_V cd
                            WHERE 1=1
                           AND cd.TASK_STATUS_CD = v.TASK_STS_CD
                           )
         AND EXISTS (SELECT 1
                      FROM CANON_E663_INCL_CALL_TYPE_V cd
                        WHERE 1=1
                        AND cd.CALL_TYPE = V.INCIDENT_CALL_TYPE
                      )
         AND NOT EXISTS (SELECT 1 FROM CANON_E663_EXCL_CR_CHANNEL_V cd
                            WHERE 1=1
                            AND cd.CR_CHANNEL_CD = V.SR_CREATION_CHANNEL_CD
                          )
         AND NOT EXISTS (SELECT 1
                          FROM CANON_E663_SR_EMAIL_TBL e663
                          WHERE e663.incident_id = v.INCIDENT_NUMBER
                          AND e663.serial_number = v.serial_number
                          );


    BEGIN
         SELECT PHONE_NUMBER
          INTO X_PHONE_NUMBER
          FROM CANON_E663_PHONE_NUM_V
          WHERE TYPE = 'CONTACT';
       EXCEPTION WHEN OTHERS THEN
          X_PHONE_NUMBER:='';
       END;


     DBMS_OUTPUT.put_line ('phone_number: '||X_PHONE_NUMBER);

  EXCEPTION WHEN OTHERS THEN
        debug_msg (
			  l_program_name   => 'CANON_E663_SERVICE_EMAIL_PKG: GET_SR_CALL_INFO:',
			  l_attribute3     => 'X_PHONE_NUMBER: ' || X_PHONE_NUMBER,
			  l_error_msg      => SUBSTR (SQLERRM, 2000));
    NULL;
  END GET_SR_CALL_INFO;
  PROCEDURE GET_EMAIL_ADDRESSES(P_PARTY_NUMBER   IN   VARCHAR2,
                                 X_EMAIL_ADRS_CUR OUT REFCURSOR
                                )
  AS
  BEGIN
    OPEN X_EMAIL_ADRS_CUR FOR
      SELECT EMAIL_ADDRESS FROM CANON_E663_CUSTOMER_DTLS_V
      WHERE ACCOUNT_NUMBER = P_PARTY_NUMBER;


  EXCEPTION WHEN OTHERS THEN
      debug_msg (
			  l_program_name   => 'CANON_E663_SERVICE_EMAIL_PKG: GET_EMAIL_ADDRESSES:',
			  l_attribute3     => 'P_PARTY_NUMBER: ' || P_PARTY_NUMBER,
			  l_error_msg      => SUBSTR (SQLERRM, 2000));
    NULL;
  END GET_EMAIL_ADDRESSES;

  PROCEDURE INSERT_EMAIL_INFO(P_SERIAL_NUMBER   IN    VARCHAR2,
                              P_MODEL           IN    VARCHAR2,
                              P_INCIDENT_NUMBER IN    VARCHAR2,
                              P_INCIDENT_STATUS IN    VARCHAR2,
                              P_INCIDENT_DATE   IN    VARCHAR2,
                              P_TASK_NUMBER     IN    VARCHAR2,
                              P_TASK_STATUS     IN    VARCHAR2,
                              P_EMAIL_SENT      IN    VARCHAR2,
                              x_retcode         OUT   NUMBER)
  AS
  BEGIN
           INSERT INTO CANON_E663_SR_EMAIL_TBL
                    (SERIAL_NUMBER,
                     MODEL,
                     INCIDENT_ID,
                     INCIDENT_NUMBER,
                     INCIDENT_STATUS,
                     INCIDENT_DATE,
                     TASK_ID,
                     TASK_NUMBER,
                     TASK_STATUS,
                     EMAIL_SENT_FLAG,
                     CREATED_BY,
                     CREATION_DATE,
                     LAST_UPDATED_BY,
                     LAST_UPDATE_DATE)
                VALUES(
                     P_SERIAL_NUMBER,
                     P_MODEL,
                     P_INCIDENT_NUMBER,
                     P_INCIDENT_NUMBER,
                     P_INCIDENT_STATUS,
                     P_INCIDENT_DATE,
                     P_TASK_NUMBER,
                     P_TASK_NUMBER,
                     P_TASK_STATUS,
                     P_EMAIL_SENT,
                     -1,
                     SYSDATE,
                     -1,
                     SYSDATE);
              COMMIT;
              x_retcode:=0;
  EXCEPTION WHEN OTHERS THEN
     debug_msg (
			  l_program_name   => 'CANON_E663_SERVICE_EMAIL_PKG: INSERT_EMAIL_INFO:',
			  l_attribute3     => 'P_INCIDENT_NUMBER: ' || P_INCIDENT_NUMBER,
			  l_error_msg      => SUBSTR (SQLERRM, 2000));
    x_retcode:=1;
  END INSERT_EMAIL_INFO;
  
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
      INSERT INTO CANON_E307_SR_ERRORS_TBL
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
END  CANON_E663_SERVICE_EMAIL_PKG;
/
SHOW ERRORS