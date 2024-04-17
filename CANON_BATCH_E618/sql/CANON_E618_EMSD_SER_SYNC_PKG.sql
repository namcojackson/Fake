/*************************************************************************************************
   Program Name        : CANON_E618_EMSD_SER_SYNC_PKG.sql
   Author              : Lakshmi Majeti
   Functional Overview : S21 EMSD Service Impact Upload Program
   Comments            :
   Modification History:
   --------------------------------------------------------------------------------------------------
   Author              Version         Date                Comments
   --------------------------------------------------------------------------------------------------
   Lakshmi Majeti       1.0           28-Feb-2017          S21 EMSD Service Impact Upload Program.
														   S21 Changes on Revision 3.5 of the PVCS File. 
**************************************************************************************************/

CREATE OR REPLACE PACKAGE CANON_E618_EMSD_SER_SYNC_PKG
AS
   L_PKG   VARCHAR2 (200) := 'CANON_E618_EMSD_SER_SYNC_PKG';


   PROCEDURE LOG_ERROR (P_PROCESS_NAME   IN VARCHAR2,
                        P_IN_KEY1        IN VARCHAR2,
                        P_IN_KEY2        IN VARCHAR2,
                        P_IN_KEY3        IN VARCHAR2,
                        P_IN_KEY4        IN VARCHAR2,
                        P_IN_KEY5        IN VARCHAR2,
                        P_ERROR_MSG      IN VARCHAR2);

   PROCEDURE LOAD_IMPACT_DATA (P_IMPACT_TBL   IN     CANON_E618_SERV_IMPACT_TBL_TYP,
                               X_RESULT          OUT VARCHAR2);

   PROCEDURE TABLE_TRUNCATE;
END CANON_E618_EMSD_SER_SYNC_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E618_EMSD_SER_SYNC_PKG
AS
   PROCEDURE LOG_ERROR (P_PROCESS_NAME   IN VARCHAR2,
                        P_IN_KEY1        IN VARCHAR2,
                        P_IN_KEY2        IN VARCHAR2,
                        P_IN_KEY3        IN VARCHAR2,
                        P_IN_KEY4        IN VARCHAR2,
                        P_IN_KEY5        IN VARCHAR2,
                        P_ERROR_MSG      IN VARCHAR2)
   AS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO E618_EMSD_ERRORS_TBL (PROGRAM_NAME,
                                        PROCESS_NAME,
                                        KEY1,
                                        KEY2,
                                        KEY3,
                                        KEY4,
                                        KEY5,
                                        ERROR_MSG,
                                        ERROR_DATE)
           VALUES ('CANON_E618_EMSD_SER_SYNC_PKG',
                   P_PROCESS_NAME,
                   P_IN_KEY1,
                   P_IN_KEY2,
                   P_IN_KEY3,
                   P_IN_KEY4,
                   P_IN_KEY5,
                   P_ERROR_MSG,
                   SYSDATE);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.PUT_LINE (
               'Unexpected error occured while logging the errors in E618_EMSD_ERRORS_TBL. S21 Error Message:'
            || SUBSTR (SQLERRM, 1, 500));
   END LOG_ERROR;

   PROCEDURE LOAD_IMPACT_DATA (P_IMPACT_TBL   IN     CANON_E618_SERV_IMPACT_TBL_TYP,
                               X_RESULT          OUT VARCHAR2)
   AS
   BEGIN
      X_RESULT := 'Y';

      --EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_SERV_IMPACT_TBL';

      EXECUTE IMMEDIATE
         'INSERT INTO CANON_E618_SERV_IMPACT_TBL
        SELECT  INCIDENT_NUMBER,
                SERIAL_NUMBER,
                INCIDENT_TYPE,
                INCIDENT_STATUS,
                INCIDENT_SEVERETY,
                INC_CREATION_DATE,
                INC_CREATED_BY,
                ASSIGNED,
                INC_CLOSED_BY,
                INC_CLOSED_DATE,
                INC_PROBLEM_SUMMARY,
                CUSTOMER_CONTACT,
                ADDRESS,
                CITY,
                STATE,
                ZIP,
                TASK_NUMBER,
                TASK_PRIORITY,
                TASK_START_DATE,
                TASK_END_DATE,
                TASK_STATUS,
                TASK_PROBLEM_DESC,
                TRIM(S21_SR_NUMBER),
                INC_RESOLVED_DATE,
                RESOLUTION,
                TASK_TYPE,
                SOURCE
          FROM TABLE(CAST(:1 AS CANON_E618_SERV_IMPACT_TBL_TYP)) a'
         USING P_IMPACT_TBL;
   EXCEPTION
      WHEN OTHERS
      THEN
         X_RESULT := 'N';
         LOG_ERROR (
            'LOAD_IMPACT_DATA',
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
               'Error in loading data for Service Requests from Impact '
            || SUBSTR (SQLERRM, 1, 500));
   END LOAD_IMPACT_DATA;

   PROCEDURE TABLE_TRUNCATE
   IS
   BEGIN
      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_SERV_IMPACT_TBL';
   END TABLE_TRUNCATE;
END CANON_E618_EMSD_SER_SYNC_PKG;
/