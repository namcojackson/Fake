--=====================================================================
-- Script Name: CANON_E307_ASCC_RELEASE_SCRIPT
-- Description: One Time DB script to create oracle objects for ASCC
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : One Time DB script to create oracle objects for ASCC
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
-- 11/09/2015 Mangala Shenoy	  Modified to add new scripts
--=====================================================================

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch branch details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
-- 03/11/2016 Hema Doniparthi	  Modified to add scripts
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_BRANCH_CODE_TBL
/

DROP TYPE CANON_E307_BRANCH_CODE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_BRANCH_CODE_REC IS OBJECT
(
   CODE VARCHAR2 (50),
   DESCRIPTION VARCHAR2 (300)
);
/

CREATE OR REPLACE TYPE CANON_E307_BRANCH_CODE_TBL
   IS TABLE OF CANON_E307_BRANCH_CODE_REC;
/

SHOW ERRORS


--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch call avoidance details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CALL_AVOID_TBL
/

DROP TYPE CANON_E307_CALL_AVOID_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CALL_AVOID_REC IS OBJECT
(
   SVC_CALL_AVOID_CD VARCHAR2 (10),
   SVC_CALL_AVOID_NM VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_CALL_AVOID_TBL
   IS TABLE OF CANON_E307_CALL_AVOID_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch call details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CALL_INFO_TBL
/

DROP TYPE CANON_E307_CALL_INFO_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CALL_INFO_REC IS OBJECT
(
   CREATION_CHANNEL VARCHAR2 (100),
   CREATION_CHANNEL_CD VARCHAR2 (100),
   TASK_TYPE_NAME VARCHAR2 (100),
   TASK_TYPE_CODE VARCHAR2 (100),
   BILL_CODE VARCHAR2 (100),
   BILL_CODE_NAME VARCHAR2 (100),
   CUSTOMER_PO VARCHAR2 (100),
   LINE_OF_BUSINESS VARCHAR2 (100),
   BRANCH VARCHAR2 (100),
   BRANCH_CD VARCHAR2 (100),
   AH_TASK_TYPE VARCHAR2 (100),
   AH_TASK_CODE VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_CALL_INFO_TBL
   IS TABLE OF CANON_E307_CALL_INFO_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to display contract details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CONTRACT_TBL
/

DROP TYPE CANON_E307_CONTRACT_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CONTRACT_REC IS OBJECT
(
   CUST_NAME VARCHAR2 (100),
   CONTRACT_NUMBER VARCHAR2 (100),
   CONTRACT_TYPE VARCHAR2 (100),
   HEADER_START_DATE VARCHAR2 (100),
   HEADER_END_DATE VARCHAR2 (100),
   HEADER_EFF_STRING VARCHAR2 (100),
   LINE_START_DATE VARCHAR2 (100),
   LINE_END_DATE VARCHAR2 (100),
   LINE_EFF_STRING VARCHAR2 (100),
   HEADER_STATUS VARCHAR2 (100),
   LINE_STATUS VARCHAR2 (100),
   RESP_TIME VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_CONTRACT_TBL
   IS TABLE OF CANON_E307_CONTRACT_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch location details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CUST_LOC_TBL
/

DROP TYPE CANON_E307_CUST_LOC_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CUST_LOC_REC IS OBJECT
(
   CUST_CODE VARCHAR2 (100),
   CUST_NAME VARCHAR2 (1000),
   ADDRESS VARCHAR2 (300),
   CITY VARCHAR2 (100),
   STATE VARCHAR2 (100),
   POSTAL_CODE VARCHAR2 (100),
   COUNTRY VARCHAR2 (100),
   PAYMENT_TERM VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_CUST_LOC_TBL
   IS TABLE OF CANON_E307_CUST_LOC_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate customer name LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CUST_NAME_LOV_TBL
/

DROP TYPE CANON_E307_CUST_NAME_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CUST_NAME_LOV_REC IS OBJECT
(
   CUST_NAME VARCHAR2 (1000),
   ACCOUNT_NUMBER VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_CUST_NAME_LOV_TBL
   IS TABLE OF CANON_E307_CUST_NAME_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC  Screen to fetch Note details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_DEBRIEF_NOTE_TBL
/

DROP TYPE CANON_E307_DEBRIEF_NOTE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_NOTE_REC IS OBJECT
(
   NOTE_SOURCE_ID VARCHAR2 (30),
   NOTE_ID NUMBER,
   NOTE_TYPE VARCHAR2 (50),
   NOTE_DATE VARCHAR2 (50),
   NOTE_TEXT VARCHAR2 (3000),
   CREATED_BY VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_NOTE_TBL
   IS TABLE OF CANON_E307_DEBRIEF_NOTE_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch Problem LOV details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_LOV_TBL
/

DROP TYPE CANON_E307_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_LOV_REC IS OBJECT (VALUE VARCHAR2 (500));
/

CREATE OR REPLACE TYPE CANON_E307_LOV_TBL IS TABLE OF CANON_E307_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch Serial Number details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_MAC_SER_TBL
/

DROP TYPE CANON_E307_MAC_SER_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_MAC_SER_REC IS OBJECT
(
   SVC_MACH_MSTR_PK 		VARCHAR2 (50),
   MODEL 					VARCHAR2 (100),
   SER_NUM 					VARCHAR2 (50),
   CUST_MACH_CTRL_NUM 		VARCHAR2 (50),
   SOLUTION_NAME 			VARCHAR2 (100),
   SHIP_TO_ACCT_NO 			VARCHAR2 (50),
   SHIP_TO_CUST_NAME 		VARCHAR2 (100),
   SHIP_TO_ADDRESS1 		VARCHAR2 (100),
   SHIP_TO_ADDRESS2 		VARCHAR2 (100),
   SHIP_TO_CITY 			VARCHAR2 (50),
   SHIP_TO_STATE 			VARCHAR2 (50),
   SHIP_TO_POSTAL_CD 		VARCHAR2 (50),
   ADDRESS 					VARCHAR2 (1000),
   OWNER_ACCT_NO 			VARCHAR2 (50),
   BILL_TO_CUST_NO 			VARCHAR2 (50),
   SELL_TO_CUST_NO 			VARCHAR2 (50),
   CURR_LOC_NO 				VARCHAR2 (50),
   CURR_LOC_ACCT_NO 		VARCHAR2 (50),
   BIZ_HRS_WEEKDAYS 		VARCHAR2 (50),
   BIZ_HRS_SAT 				VARCHAR2 (50),
   BIZ_HRS_SUN 				VARCHAR2 (50),
   LAST_SERVICE_CALL_DT 	VARCHAR2 (50),
   TOTAL_SVC_VISIT_COUNT 	VARCHAR2 (50),
   LAST_TECH_VISIT_DT 		VARCHAR2 (50),
   PREF_TECH_CD 			VARCHAR2 (50),
   REQ_TECH_CD 				VARCHAR2 (50),
   FLD_SVC_BR_CD 			VARCHAR2 (50),
   EMAIL_ADDRESS 			VARCHAR2 (50),
   CUST_TEL_NUMBER 			VARCHAR2 (50),
   CUST_TEL_EXTN 			VARCHAR2 (50),
   CUST_TEL_NUM1 			VARCHAR2(10),
   CUST_TEL_NUM2 			VARCHAR2(10),
   CUST_TEL_NUM3 			VARCHAR2(10),
   CALLER 					VARCHAR2 (50),
   SER_SPECIAL_MSG 			VARCHAR2 (100),
   SER_SPECIAL_MSG_TYP 		VARCHAR2 (30),
   CONTACT 					VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_MAC_SER_TBL
   IS TABLE OF CANON_E307_MAC_SER_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch Problem code details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_PROB_CODE_TBL
/

DROP TYPE CANON_E307_PROB_CODE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_PROB_CODE_REC IS OBJECT
(
   TYPE VARCHAR2 (100),
   DESCRIPTION VARCHAR2 (100),
   PROBLEM_CODE VARCHAR2 (100),
   OTHER_FLAG VARCHAR2 (10),
   MACHINE_STATUS VARCHAR2 (30)
);
/

CREATE OR REPLACE TYPE CANON_E307_PROB_CODE_TBL
   IS TABLE OF CANON_E307_PROB_CODE_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch Remedy details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_REMEDY_TBL
/

DROP TYPE CANON_E307_REMEDY_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_REMEDY_REC IS OBJECT
(
   SVC_PBLM_NARR_TXT VARCHAR2 (3000),
   SVC_RMD_TXT VARCHAR2 (1000)
);
/

CREATE OR REPLACE TYPE CANON_E307_REMEDY_TBL
   IS TABLE OF CANON_E307_REMEDY_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch SR Channel details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_SR_CHANNEL_TBL
/

DROP TYPE CANON_E307_SR_CHANNEL_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_SR_CHANNEL_REC IS OBJECT
(
   CALL_SRC_CODE VARCHAR2 (10),
   CALL_SRC_NAME VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_SR_CHANNEL_TBL
   IS TABLE OF CANON_E307_SR_CHANNEL_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch SR details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_SR_INFO_TBL
/

DROP TYPE CANON_E307_SR_INFO_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_SR_INFO_REC IS OBJECT
(
    FSR_NUM                 VARCHAR2 (100),
    ITT_NUM                 VARCHAR2 (100),  
    SVC_BILL_TP_CD          VARCHAR2 (100),
    SVC_BILL_TP_NM          VARCHAR2 (100),
    FSR_CRAT_DT             VARCHAR2 (30),
    RSPND_BY_DT             VARCHAR2 (30),
    LAST_UPT_DT             VARCHAR2 (30),
    LAST_UPDT_BY            VARCHAR2 (100),
    CREATED_BY              VARCHAR2 (100),
    MACH_MNGR_ID            VARCHAR2 (30),
    MACH_MNGR_NM            VARCHAR2 (100),
    PBLM_TP_CD              VARCHAR2 (100),
    PBLM_TP_NM              VARCHAR2 (100),
    MACH_STATUS             VARCHAR2 (100),
    CALL_SRC_TP_NM          VARCHAR2 (100),
    LINE_BIZ_TP_CD          VARCHAR2 (100),
    BR_DESC_TXT             VARCHAR2 (100),
    CUST_PO_NUM             VARCHAR2 (100),
    CUR_ADDR_LINE           VARCHAR2(100) ,
    CUR_CITY                VARCHAR2(100),
    CUR_ST_CD               VARCHAR2(100),
    CUR_POST_CD             VARCHAR2(100),
    CUR_CTRY_CD             VARCHAR2(100),
    CUR_LOC_DISP_STRING     VARCHAR2(100),
    BILL_ADDR_LINE          VARCHAR2(100),
    BILL_CITY               VARCHAR2(100),
    BILL_ST_CD              VARCHAR2(100),
    BILL_POST_CD            VARCHAR2(100),
    BILL_CTRY_CD            VARCHAR2(100),
    BILL_TO_DISP_STRING     VARCHAR2(100),
    PMT_TERM                VARCHAR2 (100),
    SVC_WINDOW_FROM         VARCHAR2 (100),
    SVC_WINDOW_TO           VARCHAR2 (100),
    ETA                     VARCHAR2(100),
    FUTURE_SERV_DATE        VARCHAR2(100),
    FUTURE_SERV_TM          VARCHAR2(100),
    SR_STATUS               VARCHAR2(100),
    CUST_CASE_NO            VARCHAR2(100),
    PO_VER_FLAG             VARCHAR2(100),
    FSR_TP_CD               VARCHAR2 (30),
    FSR_TP_NM               VARCHAR2(100),
    CUR_CUST_CD             VARCHAR2 (30),
    CUR_CUST_NM             VARCHAR2(100),
    BILL_CUST_CD            VARCHAR2 (30),
    BILL_CUST_NM            VARCHAR2(100),
    CUST_PO_DATE            VARCHAR2(50),
    UPDATE_FLAG             VARCHAR2 (1),
    BILLABLE_FLAG           VARCHAR2 (1),
    PO_REQ_FLG              VARCHAR2 (1),
    PO_FILE_NAME            VARCHAR2 (1000),
    PO_ATT_PK               VARCHAR2 (50)
);
/

CREATE OR REPLACE TYPE CANON_E307_SR_INFO_TBL
   IS TABLE OF CANON_E307_SR_INFO_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch Task details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_TASK_INFO_TBL
/

DROP TYPE CANON_E307_TASK_INFO_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_TASK_INFO_REC IS OBJECT
(
  TASK_NUM          VARCHAR2 (10),
  TASK_TYPE_CD      VARCHAR2 (100),
  TASK_TYPE_NM      VARCHAR2 (100),
  TASK_STATUS       VARCHAR2 (100),
  TASK_STATUS_CD    VARCHAR2(100),
  ASSIGNEE_CD       VARCHAR2 (100),
  ASSIGNEE          VARCHAR2 (100),
  ASSIGNEE_TP       VARCHAR2 (100),
  ASSIGNEE_TP_CD    VARCHAR2(30),
  LAST_UPT_BY       VARCHAR2 (100),
  CREAT_DT          VARCHAR2 (100),
  SCHD_START        VARCHAR2 (100),
  SCHD_END          VARCHAR2 (100),
  ACTUAL_START      VARCHAR2 (100),
  ACTUAL_END        VARCHAR2 (100),
  EARLY_START       VARCHAR2 (100),
  LATE_START        VARCHAR2 (100),
  RES_MANAGER       VARCHAR2 (100),
  BRANCH            VARCHAR2 (100),
  VISIT_NUM         VARCHAR2 (100),
  UPDATE_FLAG       VARCHAR2(1),
  MDL_SKILL        VARCHAR2 (2000)
);
/

CREATE OR REPLACE TYPE CANON_E307_TASK_INFO_TBL
   IS TABLE OF CANON_E307_TASK_INFO_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch UGW details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_UGW_ERR_CODE_TBL
/

DROP TYPE CANON_E307_UGW_ERR_CODE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_UGW_ERR_CODE_REC
   IS OBJECT (UGW_ERR_CODE VARCHAR2 (100));
/

CREATE OR REPLACE TYPE CANON_E307_UGW_ERR_CODE_TBL
   IS TABLE OF CANON_E307_UGW_ERR_CODE_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create Table
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Table CANON_E307_SMART_DISP_DTLS_TBL
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================
WHENEVER SQLERROR CONTINUE;

DROP TABLE CANON_E307_SMART_DISP_DTLS_TBL CASCADE CONSTRAINTS;
/

CREATE TABLE CANON_E307_SMART_DISP_DTLS_TBL
(
   INSTANCE_ID         NUMBER,
   PARTY_ID            NUMBER,
   PARTY_SITE_ID       NUMBER,
   PARTY_NUMBER        VARCHAR2 (30 BYTE),
   PARTY_NAME          VARCHAR2 (360 BYTE),
   BRANCH              VARCHAR2 (240 BYTE),
   CONTACT_PERSON      VARCHAR2 (240 BYTE),
   TELEPHONE           VARCHAR2 (240 BYTE),
   FAX                 VARCHAR2 (240 BYTE),
   MODEL               VARCHAR2 (240 BYTE),
   INSTALL_DATE        DATE,
   INSTALL_LOCATION    VARCHAR2 (1000 BYTE),
   SERIAL_NUMBER       VARCHAR2 (30 BYTE),
   ERROR_CODE          VARCHAR2 (100 BYTE),
   ERROR_DATE          DATE,
   ERROR_DESCRIPTION   VARCHAR2 (4000 BYTE),
   PRIORITY            VARCHAR2 (100 BYTE),
   CONTRACT_NUMBER     VARCHAR2 (240 BYTE),
   START_DATE          DATE,
   END_DATE            DATE,
   CONTRACT_ID         NUMBER,
   OPEN_CALL           VARCHAR2 (100 BYTE),
   ORG_ID              NUMBER,
   COMPLETE_FLAG       VARCHAR2 (10 BYTE),
   LAST_UPDATE_DATE    DATE,
   CONTRACT_STATUS     VARCHAR2 (50 BYTE),
   TAG_NUMBER          VARCHAR2 (30 BYTE),
   BRANCH_CODE_DESC    VARCHAR2 (240 BYTE),
   CATEGORY_ID         NUMBER,
   CREATED_BY          NUMBER,
   CREATION_DATE       DATE,
   LAST_UPDATED_BY     NUMBER,
   COMMENTS            VARCHAR2 (4000 BYTE),
   ATTRIBUTE1          VARCHAR2 (250 BYTE),
   ATTRIBUTE2          VARCHAR2 (250 BYTE),
   ATTRIBUTE3          VARCHAR2 (250 BYTE),
   ATTRIBUTE4          VARCHAR2 (250 BYTE),
   ATTRIBUTE5          VARCHAR2 (250 BYTE),
   LAST_LOCKED_DATE    VARCHAR2 (50 BYTE),
   LAST_LOCKED_BY      NUMBER
)
TABLESPACE TS_S21_CSA_STBL_01
PCTUSED 0
PCTFREE 10
INITRANS 1
MAXTRANS 255
STORAGE (INITIAL 1 M
         NEXT 1 M
         MINEXTENTS 1
         MAXEXTENTS UNLIMITED
         PCTINCREASE 0
         BUFFER_POOL DEFAULT)
LOGGING
NOCOMPRESS
NOCACHE
/

--=====================================================================
-- Description: DB script to create View
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : View CANON_E307_SMART_DISP_DTLS_VL
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================
WHENEVER SQLERROR CONTINUE;

CREATE OR REPLACE FORCE VIEW CANON_E307_SMART_DISP_DTLS_VL
(
   INSTANCE_ID,
   SERIAL_NUMBER,
   TAG_NUMBER,
   PARTY_ID,
   PARTY_NUMBER,
   PARTY_NAME,
   PARTY_SITE_ID,
   BRANCH,
   BRANCH_CODE_DESC,
   CATEGORY_ID,
   MODEL,
   ERROR_CODE,
   ERROR_DATE,
   ERROR_DESCRIPTION,
   PRIORITY,
   CONTRACT_ID,
   CONTRACT_NUMBER,
   OPEN_CALL,
   ORG_ID,
   COMPLETE_FLAG,
   CREATED_BY,
   CREATION_DATE,
   LAST_UPDATED_BY,
   LAST_UPDATE_DATE
)
AS
   SELECT instance_id,
          serial_number,
          tag_number,
          party_id,
          party_number,
          party_name,
          party_site_id,
          branch,
          branch_code_desc,
          category_id,
          model,
          ERROR_CODE,
          error_date,
          error_description,
          priority,
          contract_id,
          contract_number,
          open_call,
          org_id,
          complete_flag,
          created_by,
          creation_date,
          last_updated_by,
          last_update_date
     FROM canon_e307_smart_disp_dtls_tbl
/

--=====================================================================
-- Description: DB script to create Table
--
-- $Header$
-- Author     : Sesh Ragavachari
--
-- Notes      : Table CANON_OBJECT_MAPPING
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Sesh Ragavachari      Initial Version
--=====================================================================
WHENEVER SQLERROR CONTINUE;

DROP TABLE CANON_OBJECT_MAPPING CASCADE CONSTRAINTS;
/

CREATE TABLE CANON_OBJECT_MAPPING
(
   OBJECT_TYPE   VARCHAR2 (100 BYTE),
   JAVA_OBJECT   VARCHAR2 (100 BYTE),
   DB_OBJECT     VARCHAR2 (100 BYTE)
)
TABLESPACE TS_S21_CSA_STBL_01
PCTUSED 0
PCTFREE 10
INITRANS 1
MAXTRANS 255
STORAGE (INITIAL 1 M
         NEXT 1 M
         MINEXTENTS 1
         MAXEXTENTS UNLIMITED
         PCTINCREASE 0
         BUFFER_POOL DEFAULT)
LOGGING
NOCOMPRESS
NOCACHE;
/

--=====================================================================
-- Description: DB script to create Table
--
-- $Header$
-- Author     : Sesh Ragavachari
--
-- Notes      : Table DYANMIC_LOOKUP_REQUEST
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Sesh Ragavachari      Initial Version
--=====================================================================
WHENEVER SQLERROR CONTINUE;

DROP TABLE DYANMIC_LOOKUP_REQUEST CASCADE CONSTRAINTS;
/

CREATE TABLE DYANMIC_LOOKUP_REQUEST
(
   MESSAGE_ID        NUMBER,
   REQUEST_MESSAGE   SYS.XMLTYPE
)
XMLTYPE REQUEST_MESSAGE
   STORE AS CLOB
   (TABLESPACE TS_S21_CSA_STBL_01
    ENABLE STORAGE IN ROW
    CHUNK 8192
    PCTVERSION 10
    NOCACHE LOGGING
    STORAGE (INITIAL 1 M
             NEXT 1 M
             MINEXTENTS 1
             MAXEXTENTS UNLIMITED
             PCTINCREASE 0
             BUFFER_POOL DEFAULT))
TABLESPACE TS_S21_CSA_STBL_01
RESULT_CACHE (MODE DEFAULT)
PCTUSED 0
PCTFREE 10
INITRANS 1
MAXTRANS 255
STORAGE (INITIAL 1 M
         NEXT 1 M
         MINEXTENTS 1
         MAXEXTENTS UNLIMITED
         PCTINCREASE 0
         BUFFER_POOL DEFAULT
         FLASH_CACHE DEFAULT
         CELL_FLASH_CACHE DEFAULT)
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING
/

--=====================================================================
-- Description: DB script to create Sequence
--
-- $Header$
-- Author     : Sesh Ragavachari
--
-- Notes      : Sequence DYNAMIC_REQUEST_SEQ
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Sesh Ragavachari      Initial Version
--=====================================================================
WHENEVER SQLERROR CONTINUE;

DROP SEQUENCE DYNAMIC_REQUEST_SEQ;

CREATE SEQUENCE DYNAMIC_REQUEST_SEQ
   START WITH 1000000
   MAXVALUE 1000000000000000000
   MINVALUE 1
   CYCLE
   CACHE 20
   NOORDER;
/

--Start Version 2.0

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Assignee LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_RES_LOV_TBL
/

DROP TYPE CANON_E307_RES_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_RES_LOV_REC IS OBJECT(
  RES_NAME      VARCHAR2 (1000),
  RES_CODE      VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_RES_LOV_TBL IS TABLE OF CANON_E307_RES_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to display contract details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CONTRACT_TBL
/

DROP TYPE CANON_E307_CONTRACT_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CONTRACT_REC IS OBJECT(
  CUST_CD	 VARCHAR2 (100),
  CUST_NAME    VARCHAR2 (100),
  CONTRACT_NUMBER VARCHAR2 (100),
  CONTRACT_TYPE VARCHAR2 (100),
  HEADER_START_DATE VARCHAR2 (100),
  HEADER_END_DATE VARCHAR2 (100),
  HEADER_EFF_STRING VARCHAR2(100),
  LINE_START_DATE VARCHAR2 (100),
  LINE_END_DATE VARCHAR2 (100),
  LINE_EFF_STRING VARCHAR2(100),
  HEADER_STATUS VARCHAR2 (100),
  LINE_STATUS VARCHAR2 (100),
  RESP_TIME VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_CONTRACT_TBL IS TABLE OF CANON_E307_CONTRACT_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Assignee LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_DEBRIEF_DATA_TBL
/

DROP TYPE CANON_E307_DEBRIEF_DATA_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_DATA_REC IS OBJECT(
             TASK_NUMBER     VARCHAR2 (30),
	     SR_NUMBER       VARCHAR2 (30),
	     TASK_STATUS     VARCHAR2 (50),
	     SERIAL_NUMBER   VARCHAR2 (50),
	     TAG_NUMBER      VARCHAR2 (50),
	     SOLUTION_NAME   VARCHAR2 (100),
	     CUST_CASE_NO    VARCHAR2 (30),
	     ITT_NO          VARCHAR2 (30),
   	     PROBLEM_CODE       VARCHAR2 (30),
	     CORRECTION_CODE     VARCHAR2 (30),
	     REASON_CODE   VARCHAR2 (30),
	     LOCATION_CODE      VARCHAR2 (30),
	     IW_CHECK   VARCHAR2 (100),
             MACHINE_STATUS    VARCHAR2 (10),
             DEBRIEF_NUMBER     VARCHAR2 (30),
             DEBRIEF_STATUS	VARCHAR2 (30),
	     CREATION_DATE      VARCHAR2 (30),
	     CREATED_BY         VARCHAR2 (30),
	     LAST_UPDATE_DATE   VARCHAR2 (30),
   	     LAST_UPDATED_BY    VARCHAR2 (30),
            ATTRIBUTE1        VARCHAR2 (50),
            ATTRIBUTE2        VARCHAR2 (50),
            ATTRIBUTE3        VARCHAR2 (100),
            ATTRIBUTE4        VARCHAR2 (100),
            ATTRIBUTE5        VARCHAR2 (500),
            ATTRIBUTE6        VARCHAR2 (500),
            ATTRIBUTE7        VARCHAR2 (1000),
            ATTRIBUTE8        VARCHAR2 (1000),
            ATTRIBUTE9        VARCHAR2 (3000),
            ATTRIBUTE10    VARCHAR2 (3000)
   	     
);
/

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_DATA_TBL IS TABLE OF CANON_E307_DEBRIEF_DATA_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Debrief Screen to fetch Expense line details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 07/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_DEBRIEF_EXPENSE_TBL
/

DROP TYPE CANON_E307_DEBRIEF_EXPENSE_REC
/


WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_EXPENSE_REC IS OBJECT(
   FSR_EXP_PK			VARCHAR2 (30),
   FSR_NUM            		VARCHAR2 (30),
   FSR_VISIT_NUM             	VARCHAR2 (30),
   SVC_TASK_NUM         	VARCHAR2 (30),
   EXP_ITEM_NUMBER        	VARCHAR2 (30),
   EXP_ITEM_DESCRIPTION   	VARCHAR2 (100),
   SERVICE_DATE            	VARCHAR2 (30),
   QUANTITY                	NUMBER,
   UOM                     	VARCHAR2 (30),
   SVC_PRT_CHRG_FLG		VARCHAR2 (30),
   COMMENTS                	VARCHAR2 (3000),
   ATTRIBUTE1		VARCHAR2 (50),
   ATTRIBUTE2		VARCHAR2 (50),
   ATTRIBUTE3		VARCHAR2 (100),
   ATTRIBUTE4		VARCHAR2 (100),
   ATTRIBUTE5		VARCHAR2 (100),
   ATTRIBUTE6		VARCHAR2 (500),
   ATTRIBUTE7		VARCHAR2 (500),
   ATTRIBUTE8		VARCHAR2 (500),
   ATTRIBUTE9		VARCHAR2 (1000),
   ATTRIBUTE10	        VARCHAR2 (3000)                       
);
/

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_EXPENSE_TBL IS TABLE OF CANON_E307_DEBRIEF_EXPENSE_REC;
/


SHOW ERRORS


--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Debrief Screen to fetch Labor line details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 07/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_DEBRIEF_LABOR_TBL
/

DROP TYPE CANON_E307_DEBRIEF_LABOR_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_LABOR_REC IS OBJECT(
   FSR_VISIT_TM_EVENT_PK	VARCHAR2 (30),
   FSR_NUM   		VARCHAR2 (30),
   FSR_VISIT_NUM	 VARCHAR2 (30),
   SVC_TM_EVENT_CD	 VARCHAR2 (30),
   SVC_TASK_NUM     VARCHAR2 (30),
   SVC_LBOR_CHRG_FLG	 VARCHAR2 (30),
   LABOR_ITEM_NUMBER  VARCHAR2 (30),
   LABOR_ITEM_DESCRIPTION  VARCHAR2 (100),
   START_DATE       VARCHAR2 (30),
   START_TIME VARCHAR2 (30),
   END_DATE  VARCHAR2 (30),
   END_TIME VARCHAR2 (30),
   DURATION NUMBER,
   MOD_NUMBER VARCHAR2 (30),
   SERIAL_NUMBER VARCHAR2 (30),
   MOD_ITEM VARCHAR2 (30),
   COMMENTS VARCHAR2 (1000),
   ATTRIBUTE1		VARCHAR2 (50),
   ATTRIBUTE2		VARCHAR2 (50),
   ATTRIBUTE3		VARCHAR2 (100),
   ATTRIBUTE4		VARCHAR2 (100),
   ATTRIBUTE5		VARCHAR2 (100),
   ATTRIBUTE6		VARCHAR2 (500),
   ATTRIBUTE7		VARCHAR2 (500),
   ATTRIBUTE8		VARCHAR2 (500),
   ATTRIBUTE9		VARCHAR2 (1000),
   ATTRIBUTE10	        VARCHAR2 (3000)   
   --,MACH_DOWN_FLG 	 VARCHAR2 (30)
);
/

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_LABOR_TBL IS TABLE OF CANON_E307_DEBRIEF_LABOR_REC;
/


SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Debrief Screen to fetch Part line details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 07/01/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_DEBRIEF_PART_TBL
/

DROP TYPE CANON_E307_DEBRIEF_PART_REC
/


WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_PART_REC IS OBJECT(
   FSR_NUM            		VARCHAR2 (30),
   FSR_VISIT_NUM             	VARCHAR2 (30),
   SVC_TASK_NUM         	VARCHAR2 (30),
   FSR_USG_PK			VARCHAR2 (30),
   SVC_PRT_CHRG_FLG		VARCHAR2 (30),
   PRT_USED_BY_TECH_CD		VARCHAR2 (30),
   PART_ITEM_NUMBER        	VARCHAR2 (30),
   PART_ITEM_DESCRIPTION   	VARCHAR2 (100),
   SERVICE_DATE            	VARCHAR2 (30),
   QUANTITY                	NUMBER,
   UOM                     	VARCHAR2 (30),
   MOD                     	VARCHAR2 (30),
   SERIAL_NUMBER           	VARCHAR2 (30),
   MOD_ITEM                	VARCHAR2 (50),
   COMMENTS                	VARCHAR2 (3000),
   ATTRIBUTE1		VARCHAR2 (50),
   ATTRIBUTE2		VARCHAR2 (50),
   ATTRIBUTE3		VARCHAR2 (100),
   ATTRIBUTE4		VARCHAR2 (100),
   ATTRIBUTE5		VARCHAR2 (100),
   ATTRIBUTE6		VARCHAR2 (500),
   ATTRIBUTE7		VARCHAR2 (500),
   ATTRIBUTE8		VARCHAR2 (500),
   ATTRIBUTE9		VARCHAR2 (1000),
   ATTRIBUTE10	        VARCHAR2 (3000)              
);
/

CREATE OR REPLACE TYPE CANON_E307_DEBRIEF_PART_TBL IS TABLE OF CANON_E307_DEBRIEF_PART_REC;
/


SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Assignee LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_INSTALL_TBL
/

DROP TYPE CANON_E307_INSTALL_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_INSTALL_REC IS OBJECT(
  CONFIG            VARCHAR2 (100),
  ITEM              VARCHAR2 (100),
  MODEL             VARCHAR2 (100),
  MODEL_ID          VARCHAR2 (100),
  SER_NUM           VARCHAR2 (100),
  INSTL_CHKLST_PK   VARCHAR2 (100)
  );
/

CREATE OR REPLACE TYPE CANON_E307_INSTALL_TBL IS TABLE OF CANON_E307_INSTALL_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Item LOV in Debrief screen
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_ITEM_LOV_TBL
/

DROP TYPE CANON_E307_ITEM_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_ITEM_LOV_REC	 IS OBJECT(
  ITEM_NAME      VARCHAR2 (100),
  ITEM_DESC      VARCHAR2 (2000),
  UOM        VARCHAR2 (100),
  START_DT    VARCHAR2 (30),
  START_TM    VARCHAR2 (30),
  END_DT    VARCHAR2 (30),
  END_TM    VARCHAR2 (30)
);
/

CREATE OR REPLACE TYPE CANON_E307_ITEM_LOV_TBL IS TABLE OF CANON_E307_ITEM_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate IWR LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_IWR_LOV_TBL
/

DROP TYPE CANON_E307_IWR_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_IWR_LOV_REC IS OBJECT(
  ID VARCHAR2 (30),
   IW_CHECK          VARCHAR2 (500)
);
/

CREATE OR REPLACE TYPE CANON_E307_IWR_LOV_TBL IS TABLE OF CANON_E307_IWR_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Assignee LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_LOV_VAL_TBL
/

DROP TYPE CANON_E307_LOV_VAL_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_LOV_VAL_REC IS OBJECT(
  VAL      VARCHAR2 (1000)
);
/

CREATE OR REPLACE TYPE CANON_E307_LOV_VAL_TBL IS TABLE OF CANON_E307_LOV_VAL_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Future/Vendor 
--		SR details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 10/30/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_SR_DETL_TBL
/

DROP TYPE CANON_E307_SR_DETL_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_SR_DETL_REC IS OBJECT(
  SER_NUM 	VARCHAR2 (30),
  TAG_NUM       VARCHAR2 (30),
  CUST_NM	VARCHAR2 (500),
  SR_NUM	VARCHAR2 (30),
  TASK_NUM      VARCHAR2 (30),
  V_DATE	VARCHAR2 (30),
  TASK_TYPE	VARCHAR2 (100),
  ASSIGNEE	VARCHAR2 (100),
  MODEL		VARCHAR2 (500),	
  BRANCH	VARCHAR2 (500),
  STATUS	VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_SR_DETL_TBL IS TABLE OF CANON_E307_SR_DETL_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Assignee LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_SR_HIST_TBL
/

DROP TYPE CANON_E307_SR_HIST_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_SR_HIST_REC IS OBJECT(
     FSR      			VARCHAR2 (100),
     FSR_STS_CD			VARCHAR2 (100),
     FSR_STS			VARCHAR2 (100),
     FSR_TYPE			VARCHAR2 (100),
     SVC_MACH_MSTR_PK		VARCHAR2 (100),
     SER_NUM			VARCHAR2 (100),
     CUST_MACH_CTRL_NUM		VARCHAR2 (100),
     SVC_SLN_NM			VARCHAR2 (500),
     T_MDL_NM			VARCHAR2 (100),
     TECH_CD			VARCHAR2 (100),
     FSR_CREATION_DATE		VARCHAR2 (100),
     FSR_CPLT_DATE		VARCHAR2 (100),
     BILL_TO_CUST_CD		VARCHAR2 (100),
     SELL_TO_CUST_CD		VARCHAR2 (100),
     SHIP_TO_CUST_CD		VARCHAR2 (100),
     OWNR_ACCT_NUM		VARCHAR2 (100),
     CUR_LOC_ACCT_NUM		VARCHAR2 (100),
     CUSTOMER_NAME		VARCHAR2 (100),
     PMT_TERM_CASH_DISC_CD	VARCHAR2 (100),
     ISTL_STS_UPD_CPLT_FLG	VARCHAR2 (100),
     SVC_CALL_SRC_TP_CD		VARCHAR2 (100),
     SVC_PBLM_TP_CD		VARCHAR2 (100),
     PROBLEM_TYPE_NAME		VARCHAR2 (500),
     SVC_CALL_AVOID_CD		VARCHAR2 (100),
     SVC_CALL_RQST_OWNR_TOC_CD	VARCHAR2 (100),
     OWNER_NAME			VARCHAR2 (100),
     INCIDENT_DATE		VARCHAR2 (100),
     PO_VER_FLG			VARCHAR2 (100),
     CUST_CSE_NUM		VARCHAR2 (100),
     ITT_NUM			VARCHAR2 (100),
     BILL_TO_CUST_UPD_FLG	VARCHAR2 (100),
     SHIP_TO_CUST_UPD_FLG	VARCHAR2 (100),
     BILL_TO_UPD_CUST_CD	VARCHAR2 (100),
     SHIP_TO_UPD_CUST_CD	VARCHAR2 (100),
     BILL_TO_CUST_ACCT_CD	VARCHAR2 (100),
     SHIP_TO_CUST_ACCT_CD	VARCHAR2 (100),
     FSR_TP_CD			VARCHAR2 (100),
     FSR_CLO_DT			VARCHAR2 (100),
     LAST_METER			VARCHAR2 (100),
     BRANCH			VARCHAR2 (100),
     DISPATCHER			VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_SR_HIST_TBL IS TABLE OF CANON_E307_SR_HIST_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Task/SR LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_STS_LOV_TBL
/

DROP TYPE CANON_E307_STS_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_STS_LOV_REC IS OBJECT(
  CD 	VARCHAR2 (30),
  VAL      VARCHAR2 (1000)
);
/

CREATE OR REPLACE TYPE CANON_E307_STS_LOV_TBL IS TABLE OF CANON_E307_STS_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Assignee LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_TSK_HIST_TBL
/

DROP TYPE CANON_E307_TSK_HIST_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_TSK_HIST_REC IS OBJECT(
     SVC_TASK_NUM		VARCHAR2 (30),
       FSR_NUM			VARCHAR2 (30),
       SVC_TASK_STS_CD		VARCHAR2 (30),
       TASK_CRAT_DT		VARCHAR2 (30),
       SVC_MACH_MSTR_PK		VARCHAR2 (30),
       CUST_MACH_CTRL_NUM	VARCHAR2 (30),
       SER_NUM			VARCHAR2 (30),
       MDL_NM			VARCHAR2 (100),
       MDL_GRP_NM		VARCHAR2 (100),
       MDSE_CD			VARCHAR2 (30),
       SHIP_TO_CUST_CD		VARCHAR2 (30),
       CUST_TEL_NUM		VARCHAR2 (30),
       CUST_TEL_EXTN_NUM	VARCHAR2 (30),
       SVC_CUST_ATTN_TXT	VARCHAR2 (500),
       CUST_EML_ADDR		VARCHAR2 (100),
       CUST_PO_NUM		VARCHAR2 (30),
       CUST_PO_DT		VARCHAR2 (30),
       DS_SVC_CALL_TP_CD	VARCHAR2 (30),
       TASK_TYPE_NM		VARCHAR2 (50),
       SVC_BILL_TP_CD		VARCHAR2 (30),
       SVC_PBLM_SYMP_TP_CD	VARCHAR2 (30),
       TECH_CD			VARCHAR2 (30),
       CUST_AVAL_FROM_HOUR_MN	VARCHAR2 (30),
       CUST_AVAL_TO_HOUR_MN	VARCHAR2 (30),
       SVC_TASK_RCV_DT		VARCHAR2 (30),
       SVC_TASK_SCHD_DT		VARCHAR2 (30),
       SVC_TASK_CPLT_DT		VARCHAR2 (30),
       SVC_TASK_CLO_DT		VARCHAR2 (30),
       SVC_TASK_SCHD_BY_USR_ID	VARCHAR2 (30),
       SVC_TASK_CLO_BY_USR_ID	VARCHAR2 (30),
       SVC_RSP_TM_MN_AOT	VARCHAR2 (30),
       RESTORE_TM		VARCHAR2 (30),
       MACH_DOWN_FLG		VARCHAR2 (30),
       ERL_START_TS		VARCHAR2 (30),
       LATE_START_TS		VARCHAR2 (30),
       SVC_RG_CD		VARCHAR2 (30),
       SVC_BR_CD		VARCHAR2 (30),
       SVC_TEAM_CD		VARCHAR2 (30),
       SVC_BR_MGR_PSN_CD	VARCHAR2 (30),
       SVC_TRTY_MGR_PSN_CD	VARCHAR2 (30),
       SVC_TEAM_MGR_PSN_CD	VARCHAR2 (30),
       FSR_VISIT_NUM		VARCHAR2 (30),
       FSR_VISIT_STS_CD		VARCHAR2 (30),
       VISIT_TECH_CD		VARCHAR2 (30),
       ASSIGNEE_NAME		VARCHAR2 (100),
       TECH_SCHD_FROM_DT	VARCHAR2 (30),
       TECH_SCHD_TO_DT		VARCHAR2 (30),
       TASK_ACTUAL_START	VARCHAR2 (30),
       TASK_ACTUAL_END		VARCHAR2 (30),
       SVC_ASG_TP_CD		VARCHAR2 (30)
);
/

CREATE OR REPLACE TYPE CANON_E307_TSK_HIST_TBL IS TABLE OF CANON_E307_TSK_HIST_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to fetch UGW details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 07/20/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CALL_INFO_TBL
/

DROP TYPE CANON_E307_CALL_INFO_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CALL_INFO_REC IS OBJECT(
  CREATION_CHANNEL    	VARCHAR2 (100),
  CREATION_CHANNEL_CD   VARCHAR2 (100),
  TASK_TYPE_NAME        VARCHAR2 (100),
  TASK_TYPE_CODE	VARCHAR2 (100),
  BILL_CODE		VARCHAR2 (100),
  BILL_CODE_NAME	VARCHAR2 (100),
  CUST_PO_FLG		VARCHAR2 (100),
  CUST_PO_NUM		VARCHAR2 (100),
  LINE_OF_BUSINESS	VARCHAR2 (100),
  BRANCH		VARCHAR2 (100),
  BRANCH_CD 		VARCHAR2 (100),
  AH_TASK_TYPE		VARCHAR2 (100),
  AH_TASK_CODE		VARCHAR2 (100)
);	
/

CREATE OR REPLACE TYPE CANON_E307_CALL_INFO_TBL IS TABLE OF CANON_E307_CALL_INFO_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate Item LOV in Debrief screen
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_TYPE_LOV_TBL
/

DROP TYPE CANON_E307_TYPE_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_TYPE_LOV_REC	 IS OBJECT(
  TYPE_CD      VARCHAR2 (100),
  TYPE_NM      VARCHAR2 (2000),
  CD		VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE CANON_E307_TYPE_LOV_TBL IS TABLE OF CANON_E307_TYPE_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate UGW details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 10/30/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_UGW_TBL
/

DROP TYPE CANON_E307_UGW_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_UGW_REC IS OBJECT(
  SER_NUM 	VARCHAR2 (30),
  TAG_NUM       VARCHAR2 (30),
  INST_ID	VARCHAR2 (30),
  CUST_NM	VARCHAR2 (500),
  ERROR_CODE	VARCHAR2 (30),
  ERROR_DATE	VARCHAR2 (30),
  MODEL		VARCHAR2 (500),	
  BRANCH	VARCHAR2 (500)
);
/

CREATE OR REPLACE TYPE CANON_E307_UGW_TBL IS TABLE OF CANON_E307_UGW_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Used by ASCC Search Screen to populate UOM LOV
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/02/2015 Mangala Shenoy      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_UOM_LOV_TBL
/

DROP TYPE CANON_E307_UOM_LOV_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_UOM_LOV_REC IS OBJECT(
 V_CD          VARCHAR2 (30),
 V_NM          VARCHAR2 (500),
 V_DESC	     VARCHAR2 (2000)
);
/

CREATE OR REPLACE TYPE CANON_E307_UOM_LOV_TBL IS TABLE OF CANON_E307_UOM_LOV_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get attachments
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_ATT_FILE_TBL
/

DROP TYPE CANON_E307_ATT_FILE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_ATT_FILE_REC IS OBJECT(
  business_id       VARCHAR2 (30),
  att_group_txt     VARCHAR2 (30),
  file_id           NUMBER,
  file_name         VARCHAR2(30)
);
/

CREATE OR REPLACE TYPE CANON_E307_ATT_FILE_TBL IS TABLE OF CANON_E307_ATT_FILE_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get Branch Details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_BRANCH_CODE_TBL
/

DROP TYPE CANON_E307_BRANCH_CODE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_BRANCH_CODE_REC IS OBJECT(
   CODE VARCHAR2 (50),
   DESCRIPTION VARCHAR2 (300)
);
/

CREATE OR REPLACE TYPE CANON_E307_BRANCH_CODE_TBL IS TABLE OF CANON_E307_BRANCH_CODE_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get Charge details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CHG_DTL_TBL
/

DROP TYPE CANON_E307_CHG_DTL_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CHG_DTL_REC IS OBJECT(
		CHARGE_NUM              VARCHAR2(50),
		LINE_NUM                VARCHAR2(30),
		BILLING_TYPE            VARCHAR2(30),
		ITEM_NUMBER             VARCHAR2(30),
		ITEM_DESCRIPTION        VARCHAR2(100),
		QTY                     NUMBER,
		UOM_CODE                VARCHAR2(30),
		UNIT_LIST_PRICE         NUMBER,
		UNIT_OVERRIDE_PRICE     NUMBER,
		EXTENDED_AMOUNT         NUMBER,
		NO_CHARGE_FLAG          VARCHAR2(1),
		TRX_TYPE                VARCHAR2(50),
		TRX_PRICE_LIST          VARCHAR2(50),
		TRX_SOURCE              VARCHAR2(30),
		TRX_SOURCE_REF          VARCHAR2(30),
		CREATION_DATE           VARCHAR2(30),
		UPDATE_ALLOWED_FLAG     VARCHAR2(1),
		FSR_NUMBER              VARCHAR2(30),
		SVC_TASK_NUM            VARCHAR2(30),
		CHANGE_REASON           VARCHAR2(500),
		CONTRACT_PRICE          NUMBER,
		UPDATED_BY              VARCHAR2(30),
		NET_PRICE               NUMBER,
		FSR_CHRG_DTL_PK         NUMBER(28),
		FSR_VISIT_NUM           VARCHAR2(10),
		SVC_CHRG_TRX_TP_CD      VARCHAR2(1),
		SVC_CHRG_DEAL_AMT       VARCHAR2(25),
		PRC_CATG_CD             VARCHAR2(10),
		SVC_INV_CHRG_TP_CD      VARCHAR2(10),
		SVC_CHRG_DISC_RATE      VARCHAR2(10)
);
/

CREATE OR REPLACE TYPE CANON_E307_CHG_DTL_TBL IS TABLE OF CANON_E307_CHG_DTL_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get Charges header details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;


DROP TYPE CANON_E307_CHG_HDR_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_CHG_HDR_REC IS OBJECT(
		FSR_NUM                 VARCHAR2(100),
		SERIAL_NUM              VARCHAR2(150),
		CHARGE_NUM              VARCHAR2(50),
		CHARGE_TOTAL            NUMBER,
		INVOICE_NUM             VARCHAR2(50),
		INVOICE_DATE            VARCHAR2(50),
		INVOICE_STATUS          VARCHAR2(50),
		INVOICE_AMT             NUMBER,
		INVOICE_CURRENCY        VARCHAR2(50),
		SVC_TASK_NUM            VARCHAR2(50),
		MDL_NM                  VARCHAR2(50),
		CONTRACT_NUM            VARCHAR2(50),
		COVERAGE_TYPE           VARCHAR2(50),
		BILL_TO_CUST_CD         VARCHAR2(30),
		BILL_TO_CUST_NM         VARCHAR2(100),
		BILL_TO_ADDR1           VARCHAR2(100),
		BILL_TO_ADDR2           VARCHAR2(100),
		BILL_TO_CITY            VARCHAR2(50),
		BILL_TO_ST_NAME         VARCHAR2(50),
		BILL_TO_ZIP_CD          VARCHAR2(50),
		SHIP_TO_CUST_CD         VARCHAR2(30),
		SHIP_TO_CUST_NM         VARCHAR2(100),
		SHIP_TO_ADDR1           VARCHAR2(100),
		SHIP_TO_ADDR2           VARCHAR2(100),
		SHIP_TO_CITY            VARCHAR2(50),
		SHIP_TO_ST_NAME         VARCHAR2(50),
		SHIP_TO_ZIP_CD          VARCHAR2(50),
		SVC_MACH_MSTR_PK        NUMBER(28),
		FSR_VISIT_NUM           VARCHAR2(10),
		FSR_STS                 VARCHAR2(50),
		STS_UPDATE_FLG          VARCHAR2(1)
);
/


SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_EST_HDR_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_EST_HDR_REC IS OBJECT(
	CUSTOMER_NAME           VARCHAR2(100),
	CUSTOMER_ADDRESS        VARCHAR2(150),
	CUSTOMER_CITY           VARCHAR2(50),
	CUSTOMER_STATE          VARCHAR2(50),
	CUSTOMER_ZIP_CODE       VARCHAR2(50),
	CUSTOMER_ACCT_NUM       VARCHAR2(50),
	CUSTOMER_CONTACT        VARCHAR2(50),
	CUSTOMER_EMAIL          VARCHAR2(50),
	SR_NUMBER               VARCHAR2(50),
	TAG_NUMBER              VARCHAR2(50),
	MODEL                   VARCHAR2(100),
	SERIAL                  VARCHAR2(30),
	SOLUTION_NAME           VARCHAR2(30)
);
/


SHOW ERRORS


--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get estimate price details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_EST_PRICE_TBL
/

DROP TYPE CANON_E307_EST_PRICE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_EST_PRICE_REC IS OBJECT(
		LINE_NUM                VARCHAR2(100),
		ITEM_NUMBER             VARCHAR2(50),
		DESCRIPTION             VARCHAR2(50),
		QTY                     NUMBER,
		UOM                     VARCHAR2(30),
		LIST_PRICE              NUMBER,
		NET_PRICE               NUMBER
);
/

CREATE OR REPLACE TYPE CANON_E307_EST_PRICE_TBL IS TABLE OF CANON_E307_EST_PRICE_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get Task Details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_EST_TASK_TBL
/

DROP TYPE CANON_E307_EST_TASK_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_EST_TASK_REC IS OBJECT(
		TASK_NUM                VARCHAR2(100),
		ACTUAL_START_DATE       VARCHAR2(50),
		ACTUAL_END_DATE         VARCHAR2(50),
		TECH_NAME               VARCHAR2(50)
);
/

CREATE OR REPLACE TYPE CANON_E307_EST_TASK_TBL IS TABLE OF CANON_E307_EST_TASK_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get Charge Reason Codes
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CHNG_REASON_TBL
/

DROP TYPE CANON_E307_REASON_CODE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_REASON_CODE_REC IS OBJECT(
 RSN_CD          VARCHAR2 (30),
 RSNV_NM          VARCHAR2 (500),
 RSN_DESC         VARCHAR2 (2000)
);
/

CREATE OR REPLACE TYPE CANON_E307_CHNG_REASON_TBL IS TABLE OF CANON_E307_REASON_CODE_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC to get Transaction Details
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/11/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_TRX_DTL_TBL
/

DROP TYPE CANON_E307_TRX_DTL_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_TRX_DTL_REC IS OBJECT(
		TRX_TYPE                VARCHAR2(50),
		CHARGE_SOURCE 			VARCHAR2(500),
		CREATION_DATE   		VARCHAR2(30),
		TRX_PRICE_LIST          VARCHAR2(50),
		TRX_SOURCE_NUM       	VARCHAR2(30),
		CONTRACT_PRICE     		NUMBER
);
/


CREATE OR REPLACE TYPE CANON_E307_TRX_DTL_TBL IS TABLE OF CANON_E307_TRX_DTL_REC;
/

SHOW ERRORS

--=====================================================================
-- Description: DB script to create type
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Used by ASCC Charges Screen to fetch call change reasons
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/14/2016 Hema Doniparthi      Initial Version
--=====================================================================

WHENEVER SQLERROR CONTINUE;

DROP TYPE CANON_E307_CHNG_REASON_TBL
/

DROP TYPE CANON_E307_REASON_CODE_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_REASON_CODE_REC IS OBJECT(
 RSN_CD          VARCHAR2 (30),
 RSNV_NM          VARCHAR2 (500),
 RSN_DESC         VARCHAR2 (2000)
);
/

CREATE OR REPLACE TYPE CANON_E307_CHNG_REASON_TBL IS TABLE OF CANON_E307_REASON_CODE_REC;
/

SHOW ERRORS
          
CREATE OR REPLACE FORCE VIEW CANON_E307_MACH_DTLS_V
(
   SVC_MACH_MSTR_PK,
   MODEL,
   SER_NUM,
   CUST_MACH_CTRL_NUM,
   SOLUTION_NAME,
   SHIP_TO_ACCT_NO,
   SHIP_TO_CUST_NAME,
   ADDRESS_1,
   ADDRESS_2,
   CITY,
   STATE,
   POST_CD,
   ADDRESS,
   OWNR_ACCT_NUM,
   BILL_TO_CUST_CD,
   SELL_TO_CUST_CD,
   CUR_LOC_NUM,
   CUR_LOC_ACCT_NUM,
   BIZ_HRS_MON_FRI_FROM_TM,
   BIZ_HRS_MON_FRI_TO_TM,
   BIZ_HRS_SAT_FROM_TM,
   BIZ_HRS_SAT_TO_TM,
   BIZ_HRS_SUN_FROM_TM,
   BIZ_HRS_SUN_TO_TM,
   LASTSERVICE,
   TOT_SVC_VISIT_CNT,
   LAST_TECH_VISIT_DT,
   PRF_TECH_CD,
   REQ_TECH_CD,
   FLD_SVC_BR_CD,
   CTAC_PSN_EML_ADDR,
   CTAC_PSN_TEL_NUM,
   CTAC_PSN_TEL_EXTN_NUM,
   CALLER,
   CTAC_PSN_CMNT_TXT,
   MSG,
   CONTACTNAME,
   SVC_CONFIG_MSTR_PK
)
AS
   SELECT DISTINCT
          smm.svc_mach_mstr_pk,
          mdl.T_MDL_NM model,
          smm.SER_NUM ser_num,
          smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
          config.svc_sln_nm solution_name,
          smm.cur_loc_acct_num ship_to_acct_no,
          ship_to.loc_nm ship_to_cust_name,
          ship_to.first_line_addr address_1,
          ship_to.scd_line_addr address_2,
          ship_to.cty_addr city,
          ship_to.st_cd state,
          ship_to.post_cd,
             ship_to.first_line_addr
          || ', '
          || ship_to.cty_addr
          || ', '
          || ship_to.st_cd
          || ', '
          || ship_to.post_cd
             Address,
          smm.ownr_acct_num,
          smm.ownr_loc_num bill_to_cust_cd,
          ship_to.sell_to_cust_cd,
          smm.cur_loc_num,
          smm.cur_loc_acct_num,
          smm.biz_hrs_mon_fri_from_tm,
          smm.biz_hrs_mon_fri_to_tm,
          smm.biz_hrs_sat_from_tm,
          smm.biz_hrs_sat_to_tm,
          smm.biz_hrs_sun_from_tm,
          smm.biz_hrs_sun_to_tm,
          smm.last_svc_call_dt LastService,
          smm.tot_svc_visit_cnt,
          smm.last_tech_visit_dt,
          smm.prf_tech_cd,
          smm.req_tech_cd,
          smm.fld_svc_br_cd,
          contact.ctac_psn_eml_addr,
          contact.ctac_psn_tel_num,
          contact.ctac_psn_tel_extn_num,
          '',
          contact.ctac_psn_cmnt_txt,
          '' msg,
          contact.CTAC_PSN_FIRST_NM || '  ' || contact.CTAC_PSN_LAST_NM
             ContactName,
             smm.svc_config_mstr_pk
     FROM svc_mach_mstr smm,
          svc_config_mstr config,
          mdl_nm mdl,
          svc_mach_ctac_psn contact,
          ship_to_cust ship_to
    WHERE     1 = 1              --st.fsr_num                  =   fsr.fsr_num
          AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
          AND smm.SVC_MACH_MSTR_PK = contact.SVC_MACH_MSTR_PK(+)
         -- TBD
        AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
        AND NVL (ship_to.eff_thru_dt,  TO_CHAR (SYSDATE+ 1, 'YYYYMMDD') ) >=TO_CHAR (SYSDATE, 'YYYYMMDD')
        AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                 TO_CHAR (SYSDATE, 'YYYYMMDD')
          AND NVL (config.glbl_cmpy_cd, 'ADB') = 'ADB'
          AND NVL (smm.glbl_cmpy_cd, 'ADB') = 'ADB'
          AND NVL (contact.glbl_cmpy_cd, 'ADB') = 'ADB'
          AND NVL (ship_to.glbl_cmpy_cd, 'ADB') = 'ADB'
          AND mdl.T_MDL_ID(+) = config.MDL_ID;
          
        /
          
CREATE OR REPLACE FORCE VIEW CANON_E307_RESOURCE_V
(
   RESOURCE_ID,
   RESOURCE_NAME,
   TYPE
)
AS
   SELECT PSN_CD,PSN_LAST_NM||', '||  PSN_FIRST_NM,PSN_TP_CD FROM S21_PSN
   WHERE GLBL_CMPY_CD='ADB';
   
/

WHENEVER SQLERROR CONTINUE;

          
          DROP TABLE CANON_E307_SETUP_TBL CASCADE CONSTRAINTS;
	  
	  CREATE TABLE CANON_E307_SETUP_TBL
	  (
	    SETUP_NAME   VARCHAR2(50 BYTE),
	    SETUP_VALUE  VARCHAR2(100 BYTE)
	  )
	  TABLESPACE TS_S21_CSA_STBL_01
	  RESULT_CACHE (MODE DEFAULT)
	  PCTUSED    0
	  PCTFREE    10
	  INITRANS   1
	  MAXTRANS   255
	  STORAGE    (
	              INITIAL          1M
	              NEXT             1M
	              MINEXTENTS       1
	              MAXEXTENTS       UNLIMITED
	              PCTINCREASE      0
	              BUFFER_POOL      DEFAULT
	              FLASH_CACHE      DEFAULT
	              CELL_FLASH_CACHE DEFAULT
	             )
	  LOGGING 
	  NOCOMPRESS 
	  NOCACHE
	  NOPARALLEL
	MONITORING;

/
          
          --=====================================================================
	  -- Description: CANON_INSERT_E307_SETUP_TBL.sql
	  --		  DB script to insert into Table
	  --
	  -- $Header$
	  -- Author     : Mangala Shenoy
	  --
	  -- Notes      : Table CANON_E307_SETUP_TBL
	  --
	  -- Date       Who                Why
	  -- ---------- ------------------ --------------------------------------
	  -- 10/30/2015 Mangala Shenoy      Initial Version
	  --=====================================================================
	  WHENEVER SQLERROR CONTINUE;
	  
	  TRUNCATE TABLE CANON_E307_SETUP_TBL;
	  /
	  
	  INSERT INTO CANON_E307_SETUP_TBL
	       VALUES ('DEFAULT_COUNTER', 'REG TOTAL AGG');
	  
	  INSERT INTO CANON_E307_SETUP_TBL
	       VALUES ('VENDOR_TECH_TYPE', '3rd Party Rep');
	       
	  INSERT INTO CANON_E307_SETUP_TBL
	       VALUES ('VENDOR_CALL_STATUS', 'Open');
	       
	  INSERT INTO CANON_E307_SETUP_TBL
	       VALUES ('VENDOR_CALL_STATUS', 'Assigned');   
	       
	  INSERT INTO CANON_E307_SETUP_TBL
	       VALUES ('INST_CALL_SHIP_STS', 'Shipped');     
	  
	  /
	  
--=====================================================================
-- Description: DB script to insert into Table
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Table CANON_OBJECT_MAPPING
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 09/01/2015 Mangala Shenoy      Initial Version
--=====================================================================
WHENEVER SQLERROR CONTINUE;

TRUNCATE TABLE CANON_OBJECT_MAPPING;
/

Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'pblmType', 'SVC_PBLM_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'pblmCode', 'SVC_PBLM_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'svcMod', 'SVC_MOD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'modplanId', 'SVC_MOD_PLN_ID');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'billtpCode', 'MDSE_ITEM_BILL_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'billtpNm', 'MDSE_ITEM_BILL_TP_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'uomType', 'PKG_UOM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'uomCode', 'PKG_UOM_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'uomNm', 'PKG_UOM_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'uomDesc', 'PKG_UOM_DESC_TXT');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'creationCode', 'SVC_CALL_SRC_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'creationChannel', 'SVC_CALL_SRC_TP_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'channelLookup', 'SVC_CALL_SRC_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'taskType', 'DS_SVC_CALL_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'typeCode', 'DS_SVC_CALL_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'typeName', 'DS_SVC_CALL_TP_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'changeReason', 'SVC_BILL_CHNG_RSN');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'changeReasonname', 'SVC_BILL_CHNG_RSN_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'billCode', 'SVC_BILL_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'billCodename', 'SVC_BILL_TP_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'billCodeid', 'SVC_BILL_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'noteType', 'SVC_MEMO_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'noteTypename', 'SVC_MEMO_TP_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'noteTypeCd', 'SVC_MEMO_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'branchDet', 'SVC_CONTR_BR');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'branchCode', 'SVC_CONTR_BR_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'branchName', 'SVC_CONTR_BR_DESC_TXT');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'taskStatus', 'SVC_TASK_STS');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'taskStatusName', 'SVC_TASK_STS_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'taskStatusCode', 'SVC_TASK_STS_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'assigneeType', 'SVC_ASG_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'assigneeTypeName', 'SVC_ASG_TP_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'assigneeTypeCode', 'SVC_ASG_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'trsnTpe', 'SVC_CHRG_TRX_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'trsnTpCd', 'SVC_CHRG_TRX_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'trsnTpNm', 'SVC_CHRG_TRX_TP_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'prcList', 'PRC_CATG');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'prcLstCd', 'PRC_CATG_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'prcLstNm', 'PRC_CATG_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'iwrRgtnSts', 'IWR_RGTN_STS');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'iwrRgtnStsCd', 'IWR_RGTN_STS_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'iwrRgtnStsNm', 'IWR_RGTN_STS_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'iwrStsNm', 'IWR_STS_NM');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'correctionType', 'SVC_PBLM_CRCT_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'correctionCode', 'SVC_PBLM_CRCT_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'reasonType', 'SVC_PBLM_RSN_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'reasonCode', 'SVC_PBLM_RSN_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'pblmlocType', 'SVC_PBLM_LOC_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'pblmlocCode', 'SVC_PBLM_LOC_TP_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'iwrStatus', 'IWR_STS');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'iwrStsCode', 'IWR_STS_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'hourPicker', 'CANON_E307_HOUR_LOV_LIST_V');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'hourList', 'HOUR_VALUE');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'minutePicker', 'CANON_E307_MIN_LOV_LIST_V');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'minList', 'MIN_VALUE');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'taskTypeSelFlag', 'TASK_ADD_ENBL_FLG');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'taskASCCFlag', 'ASCC_SEL_FLG');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'glbCmpy', 'glbl_cmpy_cd');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'ezCancel', 'ezcancelflag');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('ENTITY_OBJECT', 'billType', 'MDSE_ITEM_BILL_TP');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'typePrtyCd', 'SVC_CALL_PRTY_CD');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'lbrChrgFlg', 'LBOR_CHRG_FLG');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'prtChrgFlg', 'PRT_CHRG_FLG');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'billbleFlg', 'BLLBL_FLG');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'hourValOrd', 'HOUR_VALUE');
   
Insert into CANON_OBJECT_MAPPING
   (OBJECT_TYPE, JAVA_OBJECT, DB_OBJECT)
 Values
   ('COLUMN_OBJECT', 'minValOrd', 'MIN_VALUE');
/

set define off 
--=====================================================================
-- Description: DB script to create value set
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Lookup Name - CANON_E307_HOUR_LOV_LIST
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/14/2016 Mangala Shenoy      Initial Version
--=====================================================================
DECLARE
   TYPE valueset_list_cols IS RECORD
   (
      col_format      VARCHAR2 (150),
      col_prompt      VARCHAR2 (240),
      col_seq         NUMBER,
      DEFAULT_VALUE   VARCHAR2 (100),
      is_active       CHAR (1),
      is_mandatory    CHAR (1),
      is_unique       CHAR (1),
      result_col      VARCHAR2 (100)
   );

   TYPE valueset_list_cols_tab IS TABLE OF valueset_list_cols
      INDEX BY BINARY_INTEGER;

   g_valueset_list_cols_tab   valueset_list_cols_tab;

   TYPE valueset_list IS RECORD
   (
      name          VARCHAR2 (150),
      description   VARCHAR2 (500),
      col_list      valueset_list_cols_tab
   );

   TYPE valueset_list_tab IS TABLE OF valueset_list
      INDEX BY BINARY_INTEGER;

   g_valueset_list_tab        valueset_list_tab;


   ln_cd_id                   NUMBER;
BEGIN
   -- Template View
   g_valueset_list_tab (1).name := 'CANON_E307_HOUR_LOV_LIST';
   g_valueset_list_tab (1).description :=
      'Look up to store Hours';
   g_valueset_list_tab (1).col_list (1).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (1).col_prompt := 'IDENTIFY_TYPE';
   g_valueset_list_tab (1).col_list (1).col_seq := 1;
   g_valueset_list_tab (1).col_list (1).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (1).is_active := 'Y';
   g_valueset_list_tab (1).col_list (1).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (1).is_unique := 'N';
   g_valueset_list_tab (1).col_list (1).result_col := 'VAL1';


   g_valueset_list_tab (1).col_list (2).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (2).col_prompt := 'BILL_FLAG';
   g_valueset_list_tab (1).col_list (2).col_seq := 2;
   g_valueset_list_tab (1).col_list (2).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (2).is_active := 'Y';
   g_valueset_list_tab (1).col_list (2).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (2).is_unique := 'N';
   g_valueset_list_tab (1).col_list (2).result_col := 'VAL2';
   
   g_valueset_list_tab (1).col_list (3).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (3).col_prompt := 'BILL_FLAG';
   g_valueset_list_tab (1).col_list (3).col_seq := 2;
   g_valueset_list_tab (1).col_list (3).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (3).is_active := 'Y';
   g_valueset_list_tab (1).col_list (3).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (3).is_unique := 'N';
   g_valueset_list_tab (1).col_list (3).result_col := 'VAL3';   
   

   FOR i IN 1 .. g_valueset_list_tab.COUNT
   LOOP
      ln_cd_id := NULL;

      /* Code Table Definition */
      INSERT INTO CANON_S21_CD_TBL (CD_ID,
                                    CD_NAME,
                                    CD_DESCRIPTION,
                                    CD_VIEW_NAME,
                                    CREATION_DATE,
                                    CREATED_BY,
                                    LAST_UPDATE_DATE,
                                    LAST_UPDATED_BY)
           VALUES (CANON_S21_CD_ID_SEQ.NEXTVAL,
                   g_valueset_list_tab (i).name,    -- Valueset/QP/Lookup Name
                   g_valueset_list_tab (i).description,
                   NULL,
                   SYSDATE,
                   -1,
                   SYSDATE,
                   -1)
        RETURNING cd_id
             INTO ln_cd_id;

      FOR j IN g_valueset_list_tab (i).col_list.FIRST ..
               g_valueset_list_tab (i).col_list.LAST
      LOOP
         INSERT INTO CANON_S21_CD_COL_TBL (CD_ID,
                                           COL_ID,
                                           COL_FORMAT,
                                           COL_PROMPT,
                                           COL_SEQ,
                                           DEFAULT_VALUE,
                                           IS_ACTIVE,
                                           IS_MANDATORY,
                                           IS_UNIQUE,
                                           RESULT_COL,
                                           CREATION_DATE,
                                           CREATED_BY,
                                           LAST_UPDATE_DATE,
                                           LAST_UPDATED_BY)
              VALUES (ln_cd_id,
                      CANON_S21_CD_COL_ID_SEQ.NEXTVAL,
                      g_valueset_list_tab (i).col_list (j).col_format,
                      g_valueset_list_tab (i).col_list (j).col_prompt,
                      g_valueset_list_tab (i).col_list (j).col_seq,
                      g_valueset_list_tab (i).col_list (j).DEFAULT_VALUE,
                      g_valueset_list_tab (i).col_list (j).is_active,
                      g_valueset_list_tab (i).col_list (j).is_mandatory,
                      g_valueset_list_tab (i).col_list (j).is_unique,
                      g_valueset_list_tab (i).col_list (j).result_col,
                      SYSDATE,
                      -1,
                      SYSDATE,
                      -1);
      END LOOP;
   END LOOP;
END;
/


/* code Table column Values */

PROMPT CREATE TEMPLATE VIEW LIST VALUES

DECLARE
   TYPE view_list IS RECORD
   (
      HOUR_CD     	VARCHAR2 (30),
      GLBL_CMPY_CD	VARCHAR2 (30),
      EZCANCELFLAG	VARCHAR2 (30)
   );

   TYPE view_list_tab IS TABLE OF view_list
      INDEX BY BINARY_INTEGER;

   g_view_list_tab   view_list_tab;
BEGIN



g_view_list_tab (1).hour_cd := '08' ;
g_view_list_tab (1).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (1).ezcancelflag := '0';
 
g_view_list_tab (2).hour_cd := '09' ;
g_view_list_tab (2).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (2).ezcancelflag := '0';
 
g_view_list_tab (3).hour_cd := '10' ;
g_view_list_tab (3).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (3).ezcancelflag := '0';
 
g_view_list_tab (4).hour_cd := '11' ;
g_view_list_tab (4).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (4).ezcancelflag := '0';
 
g_view_list_tab (5).hour_cd := '12' ;
g_view_list_tab (5).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (5).ezcancelflag := '0';
 
g_view_list_tab (6).hour_cd := '13' ;
g_view_list_tab (6).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (6).ezcancelflag := '0';
 
g_view_list_tab (7).hour_cd := '14' ;
g_view_list_tab (7).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (7).ezcancelflag := '0';
 
g_view_list_tab (8).hour_cd := '15' ;
g_view_list_tab (8).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (8).ezcancelflag := '0';
 
g_view_list_tab (9).hour_cd := '16' ;
g_view_list_tab (9).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (9).ezcancelflag := '0';
 
g_view_list_tab (10).hour_cd := '17' ;
g_view_list_tab (10).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (10).ezcancelflag := '0';
 
g_view_list_tab (11).hour_cd := '18' ;
g_view_list_tab (11).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (11).ezcancelflag := '0';
 
g_view_list_tab (12).hour_cd := '19' ;
g_view_list_tab (12).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (12).ezcancelflag := '0';
 
g_view_list_tab (13).hour_cd := '20' ;
g_view_list_tab (13).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (13).ezcancelflag := '0';
 
      
   
   FOR view_list IN (SELECT cd_id
                       FROM CANON_S21_CD_TBL
                      WHERE cd_name = 'CANON_E307_HOUR_LOV_LIST')
   LOOP
      FOR j IN 1 .. g_view_list_tab.COUNT
      LOOP
         INSERT INTO CANON_S21_CD_VAL_TBL (CD_ID,
                                           VAL_ID,
                                           val1,
                                           val2,
                                           val3,
                                           val51,
                                           START_DATE_ACTIVE,
                                           END_DATE_ACTIVE,
                                           CREATION_DATE,
                                           CREATED_BY,
                                           LAST_UPDATE_DATE,
                                           LAST_UPDATED_BY)
              VALUES (view_list.cd_id,
                      CANON_S21_CD_VAL_ID_SEQ.NEXTVAL,
                      g_view_list_tab (j).hour_cd,
                      g_view_list_tab (j).glbl_cmpy_cd,
                      g_view_list_tab (j).ezcancelflag,
                      j,
                      SYSDATE,
                      NULL,
                      SYSDATE - 1,
                      -1,
                      SYSDATE,
                      -1);
      END LOOP;

      COMMIT;
   END LOOP;
END;
/

set define off 
--=====================================================================
-- Description: DB script to create value set
--
-- $Header$
-- Author     : Mangala Shenoy
--
-- Notes      : Lookup Name - CANON_E307_MIN_LOV_LIST
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 03/14/2016 Mangala Shenoy      Initial Version
--=====================================================================
DECLARE
   TYPE valueset_list_cols IS RECORD
   (
      col_format      VARCHAR2 (150),
      col_prompt      VARCHAR2 (240),
      col_seq         NUMBER,
      DEFAULT_VALUE   VARCHAR2 (100),
      is_active       CHAR (1),
      is_mandatory    CHAR (1),
      is_unique       CHAR (1),
      result_col      VARCHAR2 (100)
   );

   TYPE valueset_list_cols_tab IS TABLE OF valueset_list_cols
      INDEX BY BINARY_INTEGER;

   g_valueset_list_cols_tab   valueset_list_cols_tab;

   TYPE valueset_list IS RECORD
   (
      name          VARCHAR2 (150),
      description   VARCHAR2 (500),
      col_list      valueset_list_cols_tab
   );

   TYPE valueset_list_tab IS TABLE OF valueset_list
      INDEX BY BINARY_INTEGER;

   g_valueset_list_tab        valueset_list_tab;


   ln_cd_id                   NUMBER;
BEGIN
   -- Template View
   g_valueset_list_tab (1).name := 'CANON_E307_MIN_LOV_LIST';
   g_valueset_list_tab (1).description :=
      'Look up to store MINs';
   g_valueset_list_tab (1).col_list (1).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (1).col_prompt := 'IDENTIFY_TYPE';
   g_valueset_list_tab (1).col_list (1).col_seq := 1;
   g_valueset_list_tab (1).col_list (1).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (1).is_active := 'Y';
   g_valueset_list_tab (1).col_list (1).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (1).is_unique := 'N';
   g_valueset_list_tab (1).col_list (1).result_col := 'VAL1';


   g_valueset_list_tab (1).col_list (2).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (2).col_prompt := 'BILL_FLAG';
   g_valueset_list_tab (1).col_list (2).col_seq := 2;
   g_valueset_list_tab (1).col_list (2).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (2).is_active := 'Y';
   g_valueset_list_tab (1).col_list (2).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (2).is_unique := 'N';
   g_valueset_list_tab (1).col_list (2).result_col := 'VAL2';
   
   g_valueset_list_tab (1).col_list (3).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (3).col_prompt := 'BILL_FLAG';
   g_valueset_list_tab (1).col_list (3).col_seq := 2;
   g_valueset_list_tab (1).col_list (3).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (3).is_active := 'Y';
   g_valueset_list_tab (1).col_list (3).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (3).is_unique := 'N';
   g_valueset_list_tab (1).col_list (3).result_col := 'VAL3';   
   

   FOR i IN 1 .. g_valueset_list_tab.COUNT
   LOOP
      ln_cd_id := NULL;

      /* Code Table Definition */
      INSERT INTO CANON_S21_CD_TBL (CD_ID,
                                    CD_NAME,
                                    CD_DESCRIPTION,
                                    CD_VIEW_NAME,
                                    CREATION_DATE,
                                    CREATED_BY,
                                    LAST_UPDATE_DATE,
                                    LAST_UPDATED_BY)
           VALUES (CANON_S21_CD_ID_SEQ.NEXTVAL,
                   g_valueset_list_tab (i).name,    -- Valueset/QP/Lookup Name
                   g_valueset_list_tab (i).description,
                   NULL,
                   SYSDATE,
                   -1,
                   SYSDATE,
                   -1)
        RETURNING cd_id
             INTO ln_cd_id;

      FOR j IN g_valueset_list_tab (i).col_list.FIRST ..
               g_valueset_list_tab (i).col_list.LAST
      LOOP
         INSERT INTO CANON_S21_CD_COL_TBL (CD_ID,
                                           COL_ID,
                                           COL_FORMAT,
                                           COL_PROMPT,
                                           COL_SEQ,
                                           DEFAULT_VALUE,
                                           IS_ACTIVE,
                                           IS_MANDATORY,
                                           IS_UNIQUE,
                                           RESULT_COL,
                                           CREATION_DATE,
                                           CREATED_BY,
                                           LAST_UPDATE_DATE,
                                           LAST_UPDATED_BY)
              VALUES (ln_cd_id,
                      CANON_S21_CD_COL_ID_SEQ.NEXTVAL,
                      g_valueset_list_tab (i).col_list (j).col_format,
                      g_valueset_list_tab (i).col_list (j).col_prompt,
                      g_valueset_list_tab (i).col_list (j).col_seq,
                      g_valueset_list_tab (i).col_list (j).DEFAULT_VALUE,
                      g_valueset_list_tab (i).col_list (j).is_active,
                      g_valueset_list_tab (i).col_list (j).is_mandatory,
                      g_valueset_list_tab (i).col_list (j).is_unique,
                      g_valueset_list_tab (i).col_list (j).result_col,
                      SYSDATE,
                      -1,
                      SYSDATE,
                      -1);
      END LOOP;
   END LOOP;
END;
/


/* code Table column Values */

PROMPT CREATE TEMPLATE VIEW LIST VALUES

DECLARE
   TYPE view_list IS RECORD
   (
      MIN_CD     	VARCHAR2 (30),
      GLBL_CMPY_CD	VARCHAR2 (30),
      EZCANCELFLAG	VARCHAR2 (30)
   );

   TYPE view_list_tab IS TABLE OF view_list
      INDEX BY BINARY_INTEGER;

   g_view_list_tab   view_list_tab;
BEGIN




g_view_list_tab (1).min_cd := '00' ;
g_view_list_tab (1).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (1).ezcancelflag := '0';
 
g_view_list_tab (2).min_cd := '01' ;
g_view_list_tab (2).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (2).ezcancelflag := '0';
 
g_view_list_tab (3).min_cd := '02' ;
g_view_list_tab (3).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (3).ezcancelflag := '0';
 
g_view_list_tab (4).min_cd := '03' ;
g_view_list_tab (4).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (4).ezcancelflag := '0';
 
g_view_list_tab (5).min_cd := '04' ;
g_view_list_tab (5).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (5).ezcancelflag := '0';
 
g_view_list_tab (6).min_cd := '05' ;
g_view_list_tab (6).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (6).ezcancelflag := '0';
 
g_view_list_tab (7).min_cd := '06' ;
g_view_list_tab (7).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (7).ezcancelflag := '0';
 
g_view_list_tab (8).min_cd := '07' ;
g_view_list_tab (8).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (8).ezcancelflag := '0';
 
g_view_list_tab (9).min_cd := '08' ;
g_view_list_tab (9).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (9).ezcancelflag := '0';
 
g_view_list_tab (10).min_cd := '09' ;
g_view_list_tab (10).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (10).ezcancelflag := '0';
 
g_view_list_tab (11).min_cd := '10' ;
g_view_list_tab (11).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (11).ezcancelflag := '0';
 
g_view_list_tab (12).min_cd := '11' ;
g_view_list_tab (12).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (12).ezcancelflag := '0';
 
g_view_list_tab (13).min_cd := '12' ;
g_view_list_tab (13).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (13).ezcancelflag := '0';
 
g_view_list_tab (14).min_cd := '13' ;
g_view_list_tab (14).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (14).ezcancelflag := '0';
 
g_view_list_tab (15).min_cd := '14' ;
g_view_list_tab (15).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (15).ezcancelflag := '0';
 
g_view_list_tab (16).min_cd := '15' ;
g_view_list_tab (16).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (16).ezcancelflag := '0';
 
g_view_list_tab (17).min_cd := '16' ;
g_view_list_tab (17).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (17).ezcancelflag := '0';
 
g_view_list_tab (18).min_cd := '17' ;
g_view_list_tab (18).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (18).ezcancelflag := '0';
 
g_view_list_tab (19).min_cd := '18' ;
g_view_list_tab (19).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (19).ezcancelflag := '0';
 
g_view_list_tab (20).min_cd := '19' ;
g_view_list_tab (20).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (20).ezcancelflag := '0';
 
g_view_list_tab (21).min_cd := '20' ;
g_view_list_tab (21).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (21).ezcancelflag := '0';
 
g_view_list_tab (22).min_cd := '21' ;
g_view_list_tab (22).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (22).ezcancelflag := '0';
 
g_view_list_tab (23).min_cd := '22' ;
g_view_list_tab (23).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (23).ezcancelflag := '0';
 
g_view_list_tab (24).min_cd := '23' ;
g_view_list_tab (24).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (24).ezcancelflag := '0';
 
g_view_list_tab (25).min_cd := '24' ;
g_view_list_tab (25).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (25).ezcancelflag := '0';
 
g_view_list_tab (26).min_cd := '25' ;
g_view_list_tab (26).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (26).ezcancelflag := '0';
 
g_view_list_tab (27).min_cd := '26' ;
g_view_list_tab (27).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (27).ezcancelflag := '0';
 
g_view_list_tab (28).min_cd := '27' ;
g_view_list_tab (28).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (28).ezcancelflag := '0';
 
g_view_list_tab (29).min_cd := '28' ;
g_view_list_tab (29).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (29).ezcancelflag := '0';
 
g_view_list_tab (30).min_cd := '29' ;
g_view_list_tab (30).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (30).ezcancelflag := '0';
 
g_view_list_tab (31).min_cd := '30' ;
g_view_list_tab (31).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (31).ezcancelflag := '0';
 
g_view_list_tab (32).min_cd := '31' ;
g_view_list_tab (32).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (32).ezcancelflag := '0';
 
g_view_list_tab (33).min_cd := '32' ;
g_view_list_tab (33).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (33).ezcancelflag := '0';
 
g_view_list_tab (34).min_cd := '33' ;
g_view_list_tab (34).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (34).ezcancelflag := '0';
 
g_view_list_tab (35).min_cd := '34' ;
g_view_list_tab (35).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (35).ezcancelflag := '0';
 
g_view_list_tab (36).min_cd := '35' ;
g_view_list_tab (36).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (36).ezcancelflag := '0';
 
g_view_list_tab (37).min_cd := '36' ;
g_view_list_tab (37).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (37).ezcancelflag := '0';
 
g_view_list_tab (38).min_cd := '37' ;
g_view_list_tab (38).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (38).ezcancelflag := '0';
 
g_view_list_tab (39).min_cd := '38' ;
g_view_list_tab (39).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (39).ezcancelflag := '0';
 
g_view_list_tab (40).min_cd := '39' ;
g_view_list_tab (40).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (40).ezcancelflag := '0';
 
g_view_list_tab (41).min_cd := '40' ;
g_view_list_tab (41).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (41).ezcancelflag := '0';
 
g_view_list_tab (42).min_cd := '41' ;
g_view_list_tab (42).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (42).ezcancelflag := '0';
 
g_view_list_tab (43).min_cd := '42' ;
g_view_list_tab (43).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (43).ezcancelflag := '0';
 
g_view_list_tab (44).min_cd := '43' ;
g_view_list_tab (44).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (44).ezcancelflag := '0';
 
g_view_list_tab (45).min_cd := '44' ;
g_view_list_tab (45).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (45).ezcancelflag := '0';
 
g_view_list_tab (46).min_cd := '45' ;
g_view_list_tab (46).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (46).ezcancelflag := '0';
 
g_view_list_tab (47).min_cd := '46' ;
g_view_list_tab (47).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (47).ezcancelflag := '0';
 
g_view_list_tab (48).min_cd := '47' ;
g_view_list_tab (48).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (48).ezcancelflag := '0';
 
g_view_list_tab (49).min_cd := '48' ;
g_view_list_tab (49).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (49).ezcancelflag := '0';
 
g_view_list_tab (50).min_cd := '49' ;
g_view_list_tab (50).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (50).ezcancelflag := '0';
 
g_view_list_tab (51).min_cd := '50' ;
g_view_list_tab (51).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (51).ezcancelflag := '0';
 
g_view_list_tab (52).min_cd := '51' ;
g_view_list_tab (52).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (52).ezcancelflag := '0';
 
g_view_list_tab (53).min_cd := '52' ;
g_view_list_tab (53).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (53).ezcancelflag := '0';
 
g_view_list_tab (54).min_cd := '53' ;
g_view_list_tab (54).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (54).ezcancelflag := '0';
 
g_view_list_tab (55).min_cd := '54' ;
g_view_list_tab (55).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (55).ezcancelflag := '0';
 
g_view_list_tab (56).min_cd := '55' ;
g_view_list_tab (56).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (56).ezcancelflag := '0';
 
g_view_list_tab (57).min_cd := '56' ;
g_view_list_tab (57).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (57).ezcancelflag := '0';
 
g_view_list_tab (58).min_cd := '57' ;
g_view_list_tab (58).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (58).ezcancelflag := '0';
 
g_view_list_tab (59).min_cd := '58' ;
g_view_list_tab (59).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (59).ezcancelflag := '0';
 
g_view_list_tab (60).min_cd := '59' ;
g_view_list_tab (60).glbl_cmpy_cd := 'ADB' ;
g_view_list_tab (60).ezcancelflag := '0';
      
   
   FOR view_list IN (SELECT cd_id
                       FROM CANON_S21_CD_TBL
                      WHERE cd_name = 'CANON_E307_MIN_LOV_LIST')
   LOOP
      FOR j IN 1 .. g_view_list_tab.COUNT
      LOOP
         INSERT INTO CANON_S21_CD_VAL_TBL (CD_ID,
                                           VAL_ID,
                                           val1,
                                           val2,
                                           val3,
                                           val51,
                                           START_DATE_ACTIVE,
                                           END_DATE_ACTIVE,
                                           CREATION_DATE,
                                           CREATED_BY,
                                           LAST_UPDATE_DATE,
                                           LAST_UPDATED_BY)
              VALUES (view_list.cd_id,
                      CANON_S21_CD_VAL_ID_SEQ.NEXTVAL,
                      g_view_list_tab (j).min_cd,
                      g_view_list_tab (j).glbl_cmpy_cd,
                      g_view_list_tab (j).ezcancelflag,
                      j,
                      SYSDATE,
                      NULL,
                      SYSDATE - 1,
                      -1,
                      SYSDATE,
                      -1);
      END LOOP;

      COMMIT;
   END LOOP;
END;
/

set define off 
--=====================================================================
-- Description: DB script to create value set
--
-- $Header$
-- Author     : Hema Doniparthi
--
-- Notes      : Create the valuesets required for E307 
--
-- Date       Who                Why
-- ---------- ------------------ --------------------------------------
-- 02/01/2016 Hema Doniparthi     Initial Version
--=====================================================================
DECLARE
   TYPE valueset_list_cols IS RECORD
   (
      col_format      VARCHAR2 (150),
      col_prompt      VARCHAR2 (240),
      col_seq         NUMBER,
      DEFAULT_VALUE   VARCHAR2 (100),
      is_active       CHAR (1),
      is_mandatory    CHAR (1),
      is_unique       CHAR (1),
      result_col      VARCHAR2 (100)
   );

   TYPE valueset_list_cols_tab IS TABLE OF valueset_list_cols
      INDEX BY BINARY_INTEGER;

   g_valueset_list_cols_tab   valueset_list_cols_tab;

   TYPE valueset_list IS RECORD
   (
      name          VARCHAR2 (150),
      description   VARCHAR2 (500),
      col_list      valueset_list_cols_tab
   );

   TYPE valueset_list_tab IS TABLE OF valueset_list
      INDEX BY BINARY_INTEGER;

   g_valueset_list_tab        valueset_list_tab;


   ln_cd_id                   NUMBER;
BEGIN
   -- Template View
   g_valueset_list_tab (1).name := 'CANON_E307_TASK_STAT_VALUES';
   g_valueset_list_tab (1).description :=
      'Task Status Values';
   g_valueset_list_tab (1).col_list (1).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (1).col_prompt := 'Code';
   g_valueset_list_tab (1).col_list (1).col_seq := 1;
   g_valueset_list_tab (1).col_list (1).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (1).is_active := 'Y';
   g_valueset_list_tab (1).col_list (1).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (1).is_unique := 'N';
   g_valueset_list_tab (1).col_list (1).result_col := 'VAL1';

   g_valueset_list_tab (1).col_list (2).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (2).col_prompt := 'Status';
   g_valueset_list_tab (1).col_list (2).col_seq := 2;
   g_valueset_list_tab (1).col_list (2).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (2).is_active := 'Y';
   g_valueset_list_tab (1).col_list (2).is_mandatory := 'Y';
   g_valueset_list_tab (1).col_list (2).is_unique := 'N';
   g_valueset_list_tab (1).col_list (2).result_col := 'VAL2';

   g_valueset_list_tab (1).col_list (3).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (3).col_prompt := 'FSR';
   g_valueset_list_tab (1).col_list (3).col_seq := 3;
   g_valueset_list_tab (1).col_list (3).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (3).is_active := 'Y';
   g_valueset_list_tab (1).col_list (3).is_mandatory := 'N';
   g_valueset_list_tab (1).col_list (3).is_unique := 'N';
   g_valueset_list_tab (1).col_list (3).result_col := 'VAL3';
   
   g_valueset_list_tab (1).col_list (4).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (4).col_prompt := 'FSR_Task';
   g_valueset_list_tab (1).col_list (4).col_seq := 4;
   g_valueset_list_tab (1).col_list (4).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (4).is_active := 'Y';
   g_valueset_list_tab (1).col_list (4).is_mandatory := 'N';
   g_valueset_list_tab (1).col_list (4).is_unique := 'N';
   g_valueset_list_tab (1).col_list (4).result_col := 'VAL4';   
   
   g_valueset_list_tab (1).col_list (5).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (5).col_prompt := 'Debrief_Task';
   g_valueset_list_tab (1).col_list (5).col_seq := 5;
   g_valueset_list_tab (1).col_list (5).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (5).is_active := 'Y';
   g_valueset_list_tab (1).col_list (5).is_mandatory := 'N';
   g_valueset_list_tab (1).col_list (5).is_unique := 'N';
   g_valueset_list_tab (1).col_list (5).result_col := 'VAL5';   

   g_valueset_list_tab (1).col_list (6).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (6).col_prompt := 'Charges_FSR';
   g_valueset_list_tab (1).col_list (6).col_seq := 6;
   g_valueset_list_tab (1).col_list (6).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (6).is_active := 'Y';
   g_valueset_list_tab (1).col_list (6).is_mandatory := 'N';
   g_valueset_list_tab (1).col_list (6).is_unique := 'N';
   g_valueset_list_tab (1).col_list (6).result_col := 'VAL6';     

   g_valueset_list_tab (1).col_list (7).col_format := 'CHAR';
   g_valueset_list_tab (1).col_list (7).col_prompt := 'Remarks';
   g_valueset_list_tab (1).col_list (7).col_seq := 7;
   g_valueset_list_tab (1).col_list (7).DEFAULT_VALUE := NULL;
   g_valueset_list_tab (1).col_list (7).is_active := 'Y';
   g_valueset_list_tab (1).col_list (7).is_mandatory := 'N';
   g_valueset_list_tab (1).col_list (7).is_unique := 'N';
   g_valueset_list_tab (1).col_list (7).result_col := 'VAL7';     


   FOR i IN 1 .. g_valueset_list_tab.COUNT
   LOOP
      ln_cd_id := NULL;

      /* Code Table Definition */
      INSERT INTO CANON_S21_CD_TBL (CD_ID,
                                    CD_NAME,
                                    CD_DESCRIPTION,
                                    CD_VIEW_NAME,
                                    CREATION_DATE,
                                    CREATED_BY,
                                    LAST_UPDATE_DATE,
                                    LAST_UPDATED_BY)
           VALUES (CANON_S21_CD_ID_SEQ.NEXTVAL,
                   g_valueset_list_tab (i).name,    -- Valueset/QP/Lookup Name
                   g_valueset_list_tab (i).description,
                   NULL,
                   SYSDATE,
                   -1,
                   SYSDATE,
                   -1)
        RETURNING cd_id
             INTO ln_cd_id;

      FOR j IN g_valueset_list_tab (i).col_list.FIRST ..
               g_valueset_list_tab (i).col_list.LAST
      LOOP
         INSERT INTO CANON_S21_CD_COL_TBL (CD_ID,
                                           COL_ID,
                                           COL_FORMAT,
                                           COL_PROMPT,
                                           COL_SEQ,
                                           DEFAULT_VALUE,
                                           IS_ACTIVE,
                                           IS_MANDATORY,
                                           IS_UNIQUE,
                                           RESULT_COL,
                                           CREATION_DATE,
                                           CREATED_BY,
                                           LAST_UPDATE_DATE,
                                           LAST_UPDATED_BY)
              VALUES (ln_cd_id,
                      CANON_S21_CD_COL_ID_SEQ.NEXTVAL,
                      g_valueset_list_tab (i).col_list (j).col_format,
                      g_valueset_list_tab (i).col_list (j).col_prompt,
                      g_valueset_list_tab (i).col_list (j).col_seq,
                      g_valueset_list_tab (i).col_list (j).DEFAULT_VALUE,
                      g_valueset_list_tab (i).col_list (j).is_active,
                      g_valueset_list_tab (i).col_list (j).is_mandatory,
                      g_valueset_list_tab (i).col_list (j).is_unique,
                      g_valueset_list_tab (i).col_list (j).result_col,
                      SYSDATE,
                      -1,
                      SYSDATE,
                      -1);
      END LOOP;
   END LOOP;
END;
/


/* code Table column Values */

PROMPT CREATE TEMPLATE VIEW LIST VALUES

DECLARE
   TYPE view_list IS RECORD
   (
      Code        		VARCHAR2 (150),
      Status        	VARCHAR2 (150),
      FSR		  		VARCHAR2 (10),
	  FSR_Task	  		VARCHAR2 (10),
	  Debrief_Task	 	VARCHAR2 (10),
	  Charges_FSR		VARCHAR2 (10),
	  Remarks			VARCHAR2 (1000)
   );

   TYPE view_list_tab IS TABLE OF view_list
      INDEX BY BINARY_INTEGER;

   g_view_list_tab   view_list_tab;
BEGIN
   g_view_list_tab (1).Code := '10';
   g_view_list_tab (1).Status := 'Open';
   g_view_list_tab (1).FSR := 'Y';
   g_view_list_tab (1).FSR_Task := 'Y';
   g_view_list_tab (1).Debrief_Task := 'Y';
   g_view_list_tab (1).Charges_FSR := 'N';
   g_view_list_tab (1).Remarks := '';
   
   g_view_list_tab (2).Code := '12';
   g_view_list_tab (2).Status := 'Reopen';
   g_view_list_tab (2).FSR := 'Y';
   g_view_list_tab (2).FSR_Task := 'Y';
   g_view_list_tab (2).Debrief_Task := 'Y';
   g_view_list_tab (2).Charges_FSR := 'N';
   g_view_list_tab (2).Remarks := '';
   
   g_view_list_tab (3).Code := '18';
   g_view_list_tab (3).Status := 'Pending PO';
   g_view_list_tab (3).FSR := 'Y';
   g_view_list_tab (3).FSR_Task := 'Y';
   g_view_list_tab (3).Debrief_Task := 'N';
   g_view_list_tab (3).Charges_FSR := 'N';
   g_view_list_tab (3).Remarks := '';

   g_view_list_tab (4).Code := '19';
   g_view_list_tab (4).Status := 'Credit Hold';
   g_view_list_tab (4).FSR := 'Y';
   g_view_list_tab (4).FSR_Task := 'Y';
   g_view_list_tab (4).Debrief_Task := 'N';
   g_view_list_tab (4).Charges_FSR := 'N';
   g_view_list_tab (4).Remarks := '';

   g_view_list_tab (5).Code := '20';
   g_view_list_tab (5).Status := 'TBO';
   g_view_list_tab (5).FSR := 'Y';
   g_view_list_tab (5).FSR_Task := 'Y';
   g_view_list_tab (5).Debrief_Task := 'Y';
   g_view_list_tab (5).Charges_FSR := 'N';
   g_view_list_tab (5).Remarks := '';

   g_view_list_tab (6).Code := '25';
   g_view_list_tab (6).Status := 'Scheduled';
   g_view_list_tab (6).FSR := 'Y';
   g_view_list_tab (6).FSR_Task := 'Y';
   g_view_list_tab (6).Debrief_Task := 'Y';
   g_view_list_tab (6).Charges_FSR := 'N';
   g_view_list_tab (6).Remarks := '';

   g_view_list_tab (7).Code := '30';
   g_view_list_tab (7).Status := 'Assigned';
   g_view_list_tab (7).FSR := 'Y';
   g_view_list_tab (7).FSR_Task := 'Y';
   g_view_list_tab (7).Debrief_Task := 'Y';
   g_view_list_tab (7).Charges_FSR := 'N';
   g_view_list_tab (7).Remarks := '';

   g_view_list_tab (8).Code := '40';
   g_view_list_tab (8).Status := 'In Route';
   g_view_list_tab (8).FSR := 'Y';
   g_view_list_tab (8).FSR_Task := 'Y';
   g_view_list_tab (8).Debrief_Task := 'Y';
   g_view_list_tab (8).Charges_FSR := 'N';
   g_view_list_tab (8).Remarks := '';

   g_view_list_tab (9).Code := '45';
   g_view_list_tab (9).Status := 'Arrived';
   g_view_list_tab (9).FSR := 'Y';
   g_view_list_tab (9).FSR_Task := 'Y';
   g_view_list_tab (9).Debrief_Task := 'Y';
   g_view_list_tab (9).Charges_FSR := 'N';
   g_view_list_tab (9).Remarks := '';

   g_view_list_tab (10).Code := '90';
   g_view_list_tab (10).Status := 'Completed';
   g_view_list_tab (10).FSR := 'N';
   g_view_list_tab (10).FSR_Task := 'N';
   g_view_list_tab (10).Debrief_Task := 'N';
   g_view_list_tab (10).Charges_FSR := 'N';
   g_view_list_tab (10).Remarks := '';

   g_view_list_tab (11).Code := '91';
   g_view_list_tab (11).Status := 'Pending Charge';
   g_view_list_tab (11).FSR := 'N';
   g_view_list_tab (11).FSR_Task := 'N';
   g_view_list_tab (11).Debrief_Task := 'Y';
   g_view_list_tab (11).Charges_FSR := 'Y';
   g_view_list_tab (11).Remarks := '';

   g_view_list_tab (12).Code := '95';
   g_view_list_tab (12).Status := 'Closed';
   g_view_list_tab (12).FSR := 'N';
   g_view_list_tab (12).FSR_Task := 'N';
   g_view_list_tab (12).Debrief_Task := 'N';
   g_view_list_tab (12).Charges_FSR := 'N';
   g_view_list_tab (12).Remarks := '';   
   
   g_view_list_tab (13).Code := '99';
   g_view_list_tab (13).Status := 'Cancelled';
   g_view_list_tab (13).FSR := 'N';
   g_view_list_tab (13).FSR_Task := 'N';
   g_view_list_tab (13).Debrief_Task := 'N';
   g_view_list_tab (13).Charges_FSR := 'N';
   g_view_list_tab (13).Remarks := '';  

   g_view_list_tab (14).Code := 'E1';
   g_view_list_tab (14).Status := 'Debrief Error';
   g_view_list_tab (14).FSR := 'N';
   g_view_list_tab (14).FSR_Task := 'N';
   g_view_list_tab (14).Debrief_Task := 'Y';
   g_view_list_tab (14).Charges_FSR := 'N';
   g_view_list_tab (14).Remarks := '';     
   
   
   FOR view_list IN (SELECT cd_id
                       FROM CANON_S21_CD_TBL
                      WHERE cd_name = 'CANON_E307_TASK_STAT_VALUES')
   LOOP
      FOR i IN 1 .. g_view_list_tab.COUNT
      LOOP
         INSERT INTO CANON_S21_CD_VAL_TBL (CD_ID,
                                           VAL_ID,
                                           val1,
                                           val2,
                                           val3,
										   val4,
										   val5,
										   val6,
										   val7,
                                           val51,
                                           START_DATE_ACTIVE,
                                           END_DATE_ACTIVE,
                                           CREATION_DATE,
                                           CREATED_BY,
                                           LAST_UPDATE_DATE,
                                           LAST_UPDATED_BY)
              VALUES (view_list.cd_id,
                      CANON_S21_CD_VAL_ID_SEQ.NEXTVAL,
                      g_view_list_tab (i).Code,
                      g_view_list_tab (i).Status,
                      g_view_list_tab (i).FSR,
					  g_view_list_tab (i).FSR_Task,
					  g_view_list_tab (i).Debrief_Task,
					  g_view_list_tab (i).Charges_FSR,
					  g_view_list_tab (i).Remarks,
                      i,
                      SYSDATE,
                      NULL,
                      SYSDATE - 1,
                      -1,
                      SYSDATE,
                      -1);
      END LOOP;

      COMMIT;
   END LOOP;
END;
/

CREATE OR REPLACE PACKAGE CANON_E307_UTILS
AS
/*******************************************************************************************
   Package Name: CANON_E307_UTILS_SPEC
   Description:  Package to be used by ASCC
   Dependencies: NA
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Sesh Ragavachari      1.0                  01-Sep-2015              Inital Version
 *****************************************************************************************/

FUNCTION format_date_func (p_date     IN VARCHAR2,
                           p_time     IN VARCHAR2,
                           p_format   IN VARCHAR2)
   RETURN VARCHAR2;
FUNCTION format_timerange_func(p_prefix      IN VARCHAR2,
                                   p_time1       IN VARCHAR2,
                                   p_time2       IN VARCHAR2,
                                   p_separator   IN VARCHAR2)
       RETURN VARCHAR2; 
       
FUNCTION format_date_range_string(p_date_from          IN VARCHAR2,
                                   p_date_to            IN VARCHAR2,
                                   p_format             IN VARCHAR2)
       RETURN VARCHAR2;  
       
 FUNCTION add_time_and_format_datetime(p_date                IN      VARCHAR2, 
                                          p_time                IN      VARCHAR2, 
                                          p_add_time_in_mins    IN      VARCHAR2,
                                          p_format              IN      VARCHAR2)
        RETURN VARCHAR2; 
 FUNCTION date_diff(p_from_date           IN      VARCHAR2, 
                          p_from_time           IN      VARCHAR2, 
                          p_to_date    		IN      VARCHAR2,
                          p_to_time             IN      VARCHAR2)
         RETURN NUMBER;   
FUNCTION date_diff_format1(p_from_date           IN      VARCHAR2, 
	                              p_from_time           IN      VARCHAR2, 
	                              p_to_date    		IN      VARCHAR2,
	                              p_to_time             IN      VARCHAR2)
	             RETURN NUMBER;         
 FUNCTION format_date(p_date       IN VARCHAR2,
                      p_format     IN VARCHAR2)
         RETURN VARCHAR2; 
 FUNCTION format_time(p_time       IN VARCHAR2)
          RETURN VARCHAR2;         
 FUNCTION format_time(p_time                IN      VARCHAR2, 
                         p_time_uom            IN      VARCHAR2, 
                         p_format              IN      VARCHAR2)
        RETURN VARCHAR2;  
       
FUNCTION format_address
               (p_cur_addr_line IN VARCHAR2,
                p_cur_city IN VARCHAR2,
                p_cur_st_cd IN VARCHAR2,
                p_cur_post_cd IN VARCHAR2,
                p_cur_ctry_cd IN VARCHAR2)
               RETURN VARCHAR2;
               
 FUNCTION format_date2_func(p_date       IN VARCHAR2,
                                 p_format     IN VARCHAR2)
       RETURN VARCHAR2;
FUNCTION format_date3_func(p_date       IN VARCHAR2,
                                       p_format     IN VARCHAR2)
             RETURN VARCHAR2;       
               
END CANON_E307_UTILS;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_UTILS
AS
/*******************************************************************************************
 Package Name: CANON_E307_UTILS_BODY
 Description:  Package to be used by ASCC
 Dependencies: NA
 Action History:
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Sesh Ragavachari      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
/*******************************************************************************************
 Function Name: FORMAT_DATE_FUNC
 Description: Combine and format the date and time.
 Input Parameters: p_date,
  		  p_time,
  		  p_format
             
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
FUNCTION FORMAT_DATE_FUNC (p_date     IN VARCHAR2,
                           p_time     IN VARCHAR2,
                           p_format   IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_date_time_formatted   VARCHAR2 (50) := NULL;
BEGIN
   IF p_date IS NOT NULL AND p_time IS NOT NULL
   THEN
      IF p_format = 'FORMAT1'
      THEN
         BEGIN
            l_date_time_formatted :=
                  TO_CHAR (TO_DATE (p_date, 'YYYYMMDD'), 'DD-MON-YYYY')
               || ' '
               || SUBSTR (p_time, 1, 2)
               || ':'
               || SUBSTR (p_time, 3, 2)
               || ':'
               || SUBSTR (p_time, 5, 2);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_time_formatted := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT2'
      THEN
         BEGIN
            l_date_time_formatted :=
                  TO_CHAR (TO_DATE (p_date, 'YYYYMMDD'), 'MM/DD/YYYY')
               || ' '
               || SUBSTR (p_time, 1, 2)
               || ':'
               || SUBSTR (p_time, 3, 2);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_time_formatted := NULL;
         END;
      END IF;
   END IF;

   RETURN l_date_time_formatted;
END FORMAT_DATE_FUNC;
     
/*******************************************************************************************
 Function Name: FORMAT_TIMERANGE_FUNC
 Description: Combine and format the time.
 Input Parameters: p_prefix,
  		   p_time1,
  		   p_time2,
  		   p_separator
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/      
     
FUNCTION FORMAT_TIMERANGE_FUNC (p_prefix      IN VARCHAR2,
                                p_time1       IN VARCHAR2,
                                p_time2       IN VARCHAR2,
                                p_separator   IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_timerange_formatted   VARCHAR2 (50);
BEGIN
   IF p_time1 IS NOT NULL AND p_time2 IS NOT NULL
   THEN
      BEGIN
         l_timerange_formatted :=
               p_prefix
            || ' '
            || SUBSTR (p_time1, 1, 2)
            || ':'
            || SUBSTR (p_time1, 3, 2)
            || p_separator
            || SUBSTR (p_time2, 1, 2)
            || ':'
            || SUBSTR (p_time2, 3, 2);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_timerange_formatted := NULL;
      END;
   ELSE
      l_timerange_formatted := NULL;
   END IF;

   RETURN l_timerange_formatted;
END FORMAT_TIMERANGE_FUNC;

/*******************************************************************************************
 Function Name: ADD_TIME_AND_FORMAT_DATETIME
 Description: Combine and format the time.
 Input Parameters: p_prefix,
  		   p_time1,
  		   p_time2,
  		   p_separator
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/          

FUNCTION ADD_TIME_AND_FORMAT_DATETIME (p_date               IN VARCHAR2,
                                       p_time               IN VARCHAR2,
                                       p_add_time_in_mins   IN VARCHAR2,
                                       p_format             IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_date                 DATE;
   l_new_date             DATE;
   l_new_date_formatted   VARCHAR2 (50);
   l_add_time_in_days     NUMBER;
BEGIN
   DBMS_OUTPUT.put_line (p_date);
   DBMS_OUTPUT.put_line (p_time);
   DBMS_OUTPUT.put_line (p_add_time_in_mins);
   l_date := TO_DATE (p_date || p_time, 'YYYYMMDDHH24MISS');

   --- Add Time in Days
   BEGIN
      l_add_time_in_days := TO_NUMBER (p_add_time_in_mins) / (60 * 24);
   EXCEPTION
      WHEN OTHERS
      THEN
         -- If some issue just add 4 Hours by Default
         l_add_time_in_days := 4 / 24;
   END;

   l_new_date := l_date + l_add_time_in_days;

   IF p_format = 'FORMAT1'
   THEN
      l_new_date_formatted := TO_CHAR (l_new_date, 'DD-MON-YYYY HH24:MI');
   END IF;

   RETURN l_new_date_formatted;
END ADD_TIME_AND_FORMAT_DATETIME;

/*******************************************************************************************
  Function Name: DATE_DIFF
  Description: Get difference between 2 dates which are in YYYYMMDDHH24MISS format.
  Input Parameters: p_from_date,
  		    p_from_time,
  		    p_to_date,
  		    p_to_time
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/         
       
FUNCTION DATE_DIFF (p_from_date   IN VARCHAR2,
                    p_from_time   IN VARCHAR2,
                    p_to_date     IN VARCHAR2,
                    p_to_time     IN VARCHAR2)
   RETURN NUMBER
IS
   l_from_date            DATE;
   l_to_date              DATE;
   l_new_date             DATE;
   l_new_date_formatted   VARCHAR2 (50);
   l_diff                 NUMBER;
BEGIN
   DBMS_OUTPUT.put_line (p_from_date);
   DBMS_OUTPUT.put_line (p_from_time);
   DBMS_OUTPUT.put_line (p_to_date);
   DBMS_OUTPUT.put_line (p_to_time);
   l_from_date := TO_DATE (p_from_date || p_from_time, 'YYYYMMDDHH24MISS');
   l_to_date := TO_DATE (p_to_date || p_to_time, 'YYYYMMDDHH24MISS');

   --- Add Time in Days
   BEGIN
      l_diff := ROUND ( (l_to_date - l_from_date) * 24, 2);
   EXCEPTION
      WHEN OTHERS
      THEN
         l_diff := 0;
   END;

   RETURN l_diff;
END DATE_DIFF;
/*******************************************************************************************
  Function Name: DATE_DIFF_FORMAT1
  Description: Get difference between 2 dates which are in in DD-MON-YYYY HH24:MI format.
  Input Parameters: p_from_date,
  		    p_from_time,
  		    p_to_date,
  		    p_to_time
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/    
FUNCTION DATE_DIFF_FORMAT1 (p_from_date   IN VARCHAR2,
                            p_from_time   IN VARCHAR2,
                            p_to_date     IN VARCHAR2,
                            p_to_time     IN VARCHAR2)
   RETURN NUMBER
IS
   l_from_date            DATE;
   l_to_date              DATE;
   l_new_date             DATE;
   l_new_date_formatted   VARCHAR2 (50);
   l_diff                 NUMBER;
BEGIN
   l_from_date := TO_DATE (p_from_date || p_from_time, 'DD-MON-YYYY HH24:MI');
   l_to_date := TO_DATE (p_to_date || p_to_time, 'DD-MON-YYYY HH24:MI');

   --- Add Time in Days
   BEGIN
      l_diff := ROUND ( (l_to_date - l_from_date) * 24, 2);
   EXCEPTION
      WHEN OTHERS
      THEN
         l_diff := 0;
   END;

   RETURN l_diff;
END DATE_DIFF_FORMAT1;
                 
/*******************************************************************************************
  Function Name: FORMAT_ADDRESS
  Description: Format the address
  Input Parameters: p_cur_addr_line,
  		    p_cur_city,
  		    p_cur_st_cd,
  		    p_cur_post_cd,
  		    p_cur_ctry_cd
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/            
FUNCTION FORMAT_ADDRESS (p_cur_addr_line   IN VARCHAR2,
                         p_cur_city        IN VARCHAR2,
                         p_cur_st_cd       IN VARCHAR2,
                         p_cur_post_cd     IN VARCHAR2,
                         p_cur_ctry_cd     IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_return_string   VARCHAR2 (100);
BEGIN
   l_return_string :=
         p_cur_addr_line
      || ' '
      || p_cur_city
      || ' '
      || p_cur_st_cd
      || ' '
      || p_cur_post_cd;
   RETURN l_return_string;
END FORMAT_ADDRESS;
             
/*******************************************************************************************
  Function Name: FORMAT_DATE2_FUNC
  Description: Format the date which is in YYYYMMDDHHMISS format
  Input Parameters: p_date,
  		    p_format
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/        
FUNCTION FORMAT_DATE2_FUNC (p_date IN VARCHAR2, p_format IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_date_time_formatted   VARCHAR2 (50) := NULL;
BEGIN
   IF p_date IS NOT NULL
   THEN
      IF p_format = 'FORMAT1'
      THEN
         BEGIN
            l_date_time_formatted :=
                  TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                           'DD-MON-YYYY')
               || ' '
               || SUBSTR (p_date, 9, 2)
               || ':'
               || SUBSTR (p_date, 11, 2);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_time_formatted := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT2'
      THEN
         BEGIN
            l_date_time_formatted :=
                  TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                           'MM/DD/YYYY')
               || ' '
               || SUBSTR (p_date, 9, 2)
               || ':'
               || SUBSTR (p_date, 11, 2);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_time_formatted := NULL;
         END;
      END IF;
   END IF;

   RETURN l_date_time_formatted;
END FORMAT_DATE2_FUNC;    

/*******************************************************************************************
  Function Name: FORMAT_DATE3_FUNC
  Description: Format the date which is in YYYYMMDD format
  Input Parameters: p_date,
  		    p_format
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/      
FUNCTION FORMAT_DATE3_FUNC (p_date IN VARCHAR2, p_format IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_date_time_formatted   VARCHAR2 (50);
BEGIN
   IF p_format = 'FORMAT1'
   THEN
      BEGIN
         l_date_time_formatted :=
            TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                     'DD-MON-YYYY');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_date_time_formatted := NULL;
      END;
   END IF;

   IF p_format = 'FORMAT2'
   THEN
      BEGIN
         l_date_time_formatted :=
            TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                     'MM/DD/YYYY');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_date_time_formatted := NULL;
      END;
   END IF;

   RETURN l_date_time_formatted;
END FORMAT_DATE3_FUNC;

/*******************************************************************************************
  Function Name: FORMAT_DATE_RANGE_STRING
  Description: Combine and Format the date from and date to
  Input Parameters: p_date_from,
  		    p_date_to,
  		    p_format
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/         
     
FUNCTION FORMAT_DATE_RANGE_STRING (p_date_from   IN VARCHAR2,
                                   p_date_to     IN VARCHAR2,
                                   p_format      IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_date_range_string   VARCHAR2 (100);
BEGIN
   IF p_format = 'FORMAT1'
   THEN
      BEGIN
         l_date_range_string :=
               TO_CHAR (TO_DATE (p_date_from, 'YYYYMMDD'), 'DD-MON-YYYY')
            || '/'
            || TO_CHAR (TO_DATE (p_date_to, 'YYYYMMDD'), 'DD-MON-YYYY');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_date_range_string := NULL;
      END;
   END IF;

   IF p_format = 'FORMAT2'
   THEN
      BEGIN
         l_date_range_string :=
               TO_CHAR (TO_DATE (p_date_from, 'YYYYMMDD'), 'MM/DD/YYYY')
            || '/'
            || TO_CHAR (TO_DATE (p_date_to, 'YYYYMMDD'), 'MM/DD/YYYY');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_date_range_string := NULL;
      END;
   END IF;

   RETURN l_date_range_string;
END FORMAT_DATE_RANGE_STRING;  

/*******************************************************************************************
  Function Name: FORMAT_DATE
  Description: Format Date
  Input Parameters: p_date,
  		    p_format
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/           
 FUNCTION FORMAT_DATE(p_date       IN VARCHAR2,
                      p_format     IN VARCHAR2)
         RETURN VARCHAR2 IS
         l_date_formatted VARCHAR2(50);
       BEGIN
         IF p_format = 'FORMAT1'
           THEN
           BEGIN
              l_date_formatted :=    
                      to_char(to_date(substr(p_date,1,8), 'YYYYMMDD'), 'DD-MON-YYYY') ; 
           EXCEPTION
             WHEN OTHERS
               THEN
               l_date_formatted := NULL;
           END;
         END IF;
         
         IF p_format = 'FORMAT2'
           THEN
           BEGIN
              l_date_formatted :=    
                      to_char(to_date(substr(p_date,1,8), 'YYYYMMDD'), 'MM/DD/YYYY') ; 
           EXCEPTION
             WHEN OTHERS
               THEN
               l_date_formatted := NULL;
           END;
         END IF;    
         
                  IF p_format = 'FORMAT3'
	            THEN
	            BEGIN
	               l_date_formatted :=    
	                       to_char(to_date(substr(p_date,1,8), 'YYYYMMDD'), 'DD-Mon-YYYY') ; 
	            EXCEPTION
	              WHEN OTHERS
	                THEN
	                l_date_formatted := NULL;
	            END;
         END IF;
         
         RETURN l_date_formatted;
     END FORMAT_DATE; 
     
/*******************************************************************************************
  Function Name: FORMAT_TIME
  Description: Format Time
  Input Parameters: p_time
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
   Sesh Ragavachari    1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/  

FUNCTION FORMAT_TIME (p_time IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_time_formatted   VARCHAR2 (50);
BEGIN
   BEGIN
      l_time_formatted :=
         SUBSTR (p_time, 1, 2) || ':' || SUBSTR (p_time, 3, 2);
   EXCEPTION
      WHEN OTHERS
      THEN
         l_time_formatted := NULL;
   END;

   RETURN l_time_formatted;
END FORMAT_TIME;

/*******************************************************************************************
  Function Name: FORMAT_TIME
  Description: Format Time
  Input Parameters: p_time,
  	            p_time_uom,
  	            p_format
             
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
 Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/  
     
FUNCTION FORMAT_TIME (p_time       IN VARCHAR2,
                      p_time_uom   IN VARCHAR2,
                      p_format     IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_formatted_time   VARCHAR2 (100);
   l_minutes_part     NUMBER;
   l_hour_part        NUMBER;
BEGIN
   DBMS_OUTPUT.put_line (p_time);

   IF p_format = 'FORMAT1'
   THEN
      BEGIN
         IF p_time_uom = 'MIN'
         THEN
            l_minutes_part := MOD (TO_NUMBER (p_time), 60);
            l_hour_part :=
               ROUND ( (TO_NUMBER (p_time) - (l_minutes_part)) / 60);
            l_formatted_time := l_hour_part || ' HRS';

            IF l_minutes_part > 0
            THEN
               l_formatted_time :=
                  l_formatted_time || ' ' || l_minutes_part || ' MINS';
            END IF;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            -- If some issue just add 4 Hours by Default
            l_formatted_time := p_time;
      END;
   END IF;

   RETURN l_formatted_time;
END FORMAT_TIME;           
     
 END CANON_E307_UTILS;
 /


CREATE OR REPLACE PACKAGE CANON_E307_CONSTANTS
AS
/*******************************************************************************************
   Package Name: CANON_E307_CONSTANTS_SPEC
   Description:  Package to store global constants to be used by ASCC
   Dependencies: NA
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Sesh Ragavachari      1.0                  01-Sep-2015              Inital Version
   Hema Doniparthi		 2.0                  14-Mar-2016			   Modified to add path for attachment
*****************************************************************************************/
g_special_message_name    VARCHAR2(100)       :=      'Memo';
  g_global_company_code     VARCHAR2(100)       :=      'ADB';
  g_cancel_flg VARCHAR2(10):='0';
  g_file_upload_path        VARCHAR2(100)        := '///WebSphere/apps/s21/csawds/updownfiles/upload/';
  g_nar_txt VARCHAR2(1000)    :=  'Our experience shows that we may immediately reduce your downtime by providing you few quick and simple instruction.May I proceed? If no:Will you review the suggested steps first before making a final decision? I am confident you will find them very easy and user friendly.';
  g_rmd_txt VARCHAR2(1000)    :='htpps://emanage.csa.canon.com/OA_HTML/11123_002JAM.pdf';

END CANON_E307_CONSTANTS;
/

CREATE OR REPLACE FORCE VIEW CANON_E307_HOUR_LOV_LIST_V
(HOUR_VALUE, GLBL_CMPY_CD, EZCANCELFLAG)
BEQUEATH DEFINER
AS
  SELECT DISTINCT val1 HOUR_VALUE, val2 GLBL_CMPY_CD, val3 EZCANCELFLAG
    FROM CANON_S21_CD_TBL cd, CANON_S21_CD_VAL_TBL val
   WHERE cd.cd_id = val.cd_id AND cd.cd_name = 'CANON_E307_HOUR_LOV_LIST'
ORDER BY val1;
/

CREATE OR REPLACE FORCE VIEW CANON_E307_MIN_LOV_LIST_V
(MIN_VALUE, GLBL_CMPY_CD, EZCANCELFLAG)
BEQUEATH DEFINER
AS
  SELECT DISTINCT val1 MIN_VALUE, val2 GLBL_CMPY_CD, val3 EZCANCELFLAG
    FROM CANON_S21_CD_TBL cd, CANON_S21_CD_VAL_TBL val
   WHERE cd.cd_id = val.cd_id AND cd.cd_name = 'CANON_E307_MIN_LOV_LIST'
ORDER BY val1;

/

CREATE OR REPLACE FORCE VIEW CANON_E307_FUTURE_CALLS_V
(
   SER_NUM,
   TAG_NUM,
   CUST_NM,
   FSR_NUM,
   SVC_TASK_NUM,
   FUTURE_DT,
   TASK_TYPE,
   ASSIGNEE_NAME,
   MODEL,
   BRANCH,
   FUT_SVC_DT
)
AS
   SELECT SER_NUM,
       CUST_MACH_CTRL_NUM Tag_Num,
       (SELECT DISTINCT LOC_NM
          FROM fsr, ship_to_cust--sell_to_cust
         WHERE     fsr.ship_to_cust_cd = ship_to_cust.ship_to_cust_cd
               AND fsr.fsr_num = task.fsr_num
               AND ROWNUM = 1)
          cust_nm,
       task.fsr_num,
       task.SVC_TASK_NUM,
       CANON_E307_UTILS.format_date_func (visit.fut_svc_dt,
                                          visit.FUT_SVC_TM,
                                          'FORMAT1')
          Future_dt,
       (SELECT DS_SVC_CALL_TP_NM
          FROM DS_SVC_CALL_TP TYPE
         WHERE     TYPE.DS_SVC_CALL_TP_CD = task.DS_SVC_CALL_TP_CD
               AND EZCANCELFLAG = 0
               AND GLBL_CMPY_CD = 'ADB')
          task_type,
       (SELECT CASE
                  WHEN (psn_last_nm || ',' || psn_first_nm) <> ','
                  THEN
                     psn_last_nm || ', ' || psn_first_nm
                  ELSE
                     NULL
               END
          FROM s21_psn psn
         WHERE     psn.psn_cd = visit.tech_cd
               AND psn.glbl_cmpy_cd = 'ADB'
               AND psn.EZCANCELFLAG = 0)
          ASSIGNEE_NAME,
       task.MDL_NM model,
       (SELECT task.SVC_BR_CD || '-' || branch.svc_contr_br_desc_txt
          FROM svc_contr_br branch
         WHERE     task.SVC_BR_CD = branch.svc_contr_br_cd
               AND branch.glbl_cmpy_cd = 'ADB'
               AND branch.ezcancelflag = '0')
          Branch ,visit.fut_svc_dt
  FROM svc_task task, FSR_VISIT visit
 WHERE     1 = 1
       AND visit.fsr_num = task.fsr_num
       AND fsr_visit_num =(SELECT MAX (fsr_visit_num)
     			 FROM fsr_visit visit
    		         WHERE visit.FSR_NUM = task.fsr_num
    		         AND visit.glbl_cmpy_cd = 'ADB')
       AND visit.GLBL_CMPY_CD = 'ADB'
       AND fut_svc_dt || FUT_SVC_TM > TO_CHAR (SYSDATE, 'YYYYMMDDHH24MISS');
          
          /
CREATE OR REPLACE FORCE VIEW CANON_E307_SR_HIST_V
(
   FSR_NUM,
   FSR_STS_CD,
   FSR_STS,
   FSR_TYPE,
   SVC_MACH_MSTR_PK,
   SER_NUM,
   CUST_MACH_CTRL_NUM,
   SVC_SLN_NM,
   T_MDL_NM,
   TECH_CD,
   FSR_CREATION_DATE,
   FSR_CPLT_DATE,
   BILL_TO_CUST_CD,
   SELL_TO_CUST_CD,
   SHIP_TO_CUST_CD,
   OWNR_ACCT_NUM,
   CUR_LOC_ACCT_NUM,
   CUSTOMER_NAME,
   PMT_TERM_CASH_DISC_CD,
   ISTL_STS_UPD_CPLT_FLG,
   SVC_CALL_SRC_TP_CD,
   SVC_PBLM_TP_CD,
   PROBLEM_TYPE_NAME,
   SVC_CALL_AVOID_CD,
   SVC_CALL_RQST_OWNR_TOC_CD,
   OWNER_NAME,
   INCIDENT_DATE,
   PO_VER_FLG,
   CUST_CSE_NUM,
   ITT_NUM,
   BILL_TO_CUST_UPD_FLG,
   SHIP_TO_CUST_UPD_FLG,
   BILL_TO_UPD_CUST_CD,
   SHIP_TO_UPD_CUST_CD,
   BILL_TO_CUST_ACCT_CD,
   SHIP_TO_CUST_ACCT_CD,
   FSR_TP_CD,
   FSR_CLO_DT,
   LAST_METER,
   BRANCH,
   DISPATCHER
)
AS
   SELECT DISTINCT
          fsr.fsr_num,
          fsr.fsr_sts_cd,
          (   select sts.svc_task_sts_nm
                 from svc_task_sts sts
                where svc_task_sts_cd=fsr.fsr_sts_cd
                AND sts.ezcancelflag=0
                and sts.glbl_cmpy_cd='ADB')fsr_sts,
          (CASE
              WHEN (TYPE.DS_SVC_CALL_TP_CD || '-' || TYPE.DS_SVC_CALL_TP_NM) <>
                      '-'
              THEN
                 TYPE.DS_SVC_CALL_TP_CD || '-' || TYPE.DS_SVC_CALL_TP_NM
              ELSE
                 NULL
           END)
             FSR_TYPE,
          fsr.svc_mach_mstr_pk,
          ib.Ser_num,
          ib.cust_mach_ctrl_num,
          config.svc_sln_nm,
          mdl.T_MDL_NM,
          fsr.tech_cd,
          canon_e307_utils.format_date_func (fsr.fsr_crat_dt,
                                             fsr.fsr_crat_tm,
                                             'FORMAT2')
             fsr_creation_date,
          canon_e307_utils.format_date_func (fsr.fsr_cplt_dt,
                                             fsr.fsr_cplt_tm,
                                             'FORMAT2')
             fsr_cplt_date,
          fsr.bill_to_cust_cd,
          fsr.sell_to_cust_cd,
          fsr.ship_to_cust_cd,
          ib.ownr_acct_num,
          ib.cur_loc_acct_num,
          sell_to.loc_nm Customer_Name,
          fsr.pmt_term_cash_disc_cd,
          fsr.istl_sts_upd_cplt_flg,
          fsr.svc_call_src_tp_cd,
          fsr.svc_pblm_tp_cd,
          (select SVC_PBLM_TP_DESC_TXT from SVC_PBLM_TP
           where SVC_PBLM_TP.svc_pblm_tp_cd=fsr.svc_pblm_tp_cd
           and glbl_cmpy_cd='ADB'
            and EZCANCELFLAG='0') Problem_Type_Name,
          fsr.svc_call_avoid_cd,
          fsr.svc_call_rqst_ownr_toc_cd,
          (select case when (psn_last_nm || ',' || psn_first_nm)<>','
          			then psn_last_nm || ',' || psn_first_nm 
   			 else null end 
	               from s21_psn psn
            where fsr.svc_call_rqst_ownr_toc_cd = psn.psn_cd
            and glbl_cmpy_cd='ADB'
            and EZCANCELFLAG='0') owner_name,
          canon_e307_utils.format_date_func (fsr.svc_call_incdt_dt,
                                             fsr.svc_call_incdt_tm,
                                             'FORMAT2')
             incident_date,
          fsr.po_ver_flg,
          fsr.cust_cse_num,
          fsr.itt_num,
          fsr.bill_to_cust_upd_flg,
          fsr.ship_to_cust_upd_flg,
          fsr.bill_to_upd_cust_cd,
          fsr.ship_to_upd_cust_cd,
          fsr.bill_to_cust_acct_cd,
          fsr.ship_to_cust_acct_cd,
          fsr.fsr_tp_cd,
          canon_e307_utils.format_date_func (fsr.fsr_clo_dt,
                                             fsr.fsr_clo_tm,
                                             'FORMAT2')
             fsr_clo_dt,
             --TBD
          (select READ_MTR_CNT 
          	from SVC_PHYS_MTR_READ meter,CANON_E307_SETUP_TBL setup,
                   MTR_LB meter_label,
                   svc_mach_mstr ib
                where meter.SVC_MACH_MSTR_PK=ib.SVC_MACH_MSTR_PK
                AND meter.MTR_READ_DT=(select max(MTR_READ_DT)
                			FROM SVC_PHYS_MTR_READ meter1,
                			     CANON_E307_SETUP_TBL setup,
					     MTR_LB meter_label,
                   			     svc_mach_mstr ib
                			where  meter.SVC_MACH_MSTR_PK=meter1.SVC_MACH_MSTR_PK
                			AND meter.MTR_LB_CD = meter_label.mtr_lb_cd
                			AND meter_label.mtr_lb_nm = setup.setup_value
                			AND setup.setup_name = 'DEFAULT_COUNTER')
                			AND meter.MTR_LB_CD = meter_label.mtr_lb_cd
                AND meter_label.mtr_lb_nm = setup.setup_value
                AND setup.setup_name = 'DEFAULT_COUNTER'
                AND rownum=1 			) LAST_METER,
          (SELECT branch.svc_contr_br_desc_txt
             FROM svc_task task, svc_contr_br branch
            WHERE     task.svc_task_num = (SELECT MAX (svc_task_num)
                                             FROM svc_task task1
                                            WHERE task1.fsr_num = fsr.fsr_num)
                  AND task.SVC_BR_CD = branch.svc_contr_br_cd
                  AND branch.glbl_cmpy_cd='ADB'
           	  AND branch.ezcancelflag='0')
             Branch,
          (SELECT psn.PSN_LAST_NM || ',' || PSN_FIRST_NM
             FROM S21_PSN psn
            WHERE fsr.EZINUSERID = psn.PSN_CD
            AND psn.glbl_cmpy_cd='ADB'
            AND psn.ezcancelflag='0')
             DISPATCHER
     FROM fsr,
          svc_mach_mstr ib,
          sell_to_cust sell_to,
          svc_config_mstr config,
          mdl_nm mdl,
          ds_svc_call_tp TYPE
    WHERE     1 = 1
          AND fsr.sell_to_cust_cd = sell_to.sell_to_cust_cd(+)
          AND ib.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK(+)
          AND mdl.T_MDL_ID(+) = config.MDL_ID
          AND ib.ser_num (+)= fsr.ser_num
          AND fsr.FSR_TP_CD = TYPE.DS_SVC_CALL_TP_CD
          AND nvl(ib.glbl_cmpy_cd,'ADB') = 'ADB'
          AND nvl(config.glbl_cmpy_cd,'ADB') = 'ADB'
          AND nvl(mdl.t_glbl_cmpy_cd,'ADB') = 'ADB'
          AND nvl(fsr.glbl_cmpy_cd,'ADB') = 'ADB'
          AND nvl(TYPE.glbl_cmpy_cd,'ADB') = 'ADB'
          AND nvl(ib.EZCANCELFLAG,'0') = '0'
          AND nvl(config.EZCANCELFLAG,'0') = '0'
          AND nvl(mdl.EZCANCELFLAG,'0') = '0'
          AND nvl(fsr.EZCANCELFLAG,'0') = '0'
       AND nvl(TYPE.EZCANCELFLAG,'0') = '0';
       
       /
       
       CREATE OR REPLACE FORCE VIEW CANON_E307_TASK_HIST_V
       (
         SVC_TASK_NUM,
         FSR_NUM,
         SVC_TASK_STS_CD,
         TASK_CRAT_DT,
         SVC_MACH_MSTR_PK,
         CUST_MACH_CTRL_NUM,
         SER_NUM,
         MDL_NM,
         MDL_GRP_NM,
         MDSE_CD,
         SHIP_TO_CUST_CD,
         CUST_TEL_NUM,
         CUST_TEL_EXTN_NUM,
         SVC_CUST_ATTN_TXT,
         CUST_EML_ADDR,
         CUST_PO_NUM,
         CUST_PO_DT,
         DS_SVC_CALL_TP_CD,
         TASK_TYPE_NM,
         SVC_BILL_TP_CD,
         SVC_PBLM_SYMP_TP_CD,
         TECH_CD,
         CUST_AVAL_FROM_HOUR_MN,
         CUST_AVAL_TO_HOUR_MN,
         SVC_TASK_RCV_DT,
         SVC_TASK_SCHD_DT,
         SVC_TASK_CPLT_DT,
         SVC_TASK_CLO_DT,
         SVC_TASK_SCHD_BY_USR_ID,
         SVC_TASK_CLO_BY_USR_ID,
         SVC_RSP_TM_MN_AOT,
         RESTORE_TM,
         MACH_DOWN_FLG,
         ERL_START_TS,
         LATE_START_TS,
         SVC_RG_CD,
         SVC_BR_CD,
         SVC_TEAM_CD,
         SVC_BR_MGR_PSN_CD,
         SVC_TRTY_MGR_PSN_CD,
         SVC_TEAM_MGR_PSN_CD,
         FSR_VISIT_NUM,
         FSR_VISIT_STS_CD,
         VISIT_TECH_CD,
         ASSIGNEE_NAME,
         TECH_SCHD_FROM_DT,
         TECH_SCHD_TO_DT,
         TASK_ACTUAL_START,
         TASK_ACTUAL_END,
         SVC_ASG_TP_CD
       )
       AS
          SELECT task.SVC_TASK_NUM,
              task.FSR_NUM,
              task.SVC_TASK_STS_CD,
              CANON_E307_UTILS.format_date2_func (task.EZINTIME, 'FORMAT2')
                 task_crat_dt,
              task.SVC_MACH_MSTR_PK,
              task.Cust_mach_ctrl_num,
              task.ser_num,
              task.mdl_nm,
              task.mdl_grp_nm,
              task.mdse_cd,
              task.ship_to_cust_cd,
              task.cust_tel_num,
              task.cust_tel_extn_num,
              task.svc_cust_attn_txt,
              task.cust_eml_addr,
              task.cust_po_num,
              task.cust_po_dt,
              task.ds_svc_call_tp_cd,
              (select case when (ds_svc_call_tp_cd || '-' || DS_SVC_CALL_TP_NM)<>'-'
                  THEN ds_svc_call_tp_cd || '-' || DS_SVC_CALL_TP_NM 
                  ELSE NULL END 
                  FROM ds_svc_call_tp TYPE
                  WHERE TYPE.ds_svc_call_tp_cd=task.DS_SVC_CALL_TP_CD
                  and glbl_cmpy_cd='ADB'
                   and EZCANCELFLAG='0') Task_type_nm,
              task.svc_bill_tp_cd,
              task.svc_pblm_symp_tp_cd,
              task.tech_cd,
              task.cust_aval_from_hour_mn,
              task.cust_aval_to_hour_mn,
              CANON_E307_UTILS.format_date_func (task.SVC_TASK_RCV_DT,
                                                 task.SVC_TASK_RCV_TM,
                                                 'FORMAT2')
                 SVC_TASK_RCV_DT,
              CANON_E307_UTILS.format_date_func (task.SVC_TASK_SCHD_DT,
                                                 task.SVC_TASK_SCHD_TM,
                                                 'FORMAT2')
                 SVC_TASK_SCHD_DT,
              CANON_E307_UTILS.format_date_func (task.SVC_TASK_CPLT_DT,
                                                 task.SVC_TASK_CPLT_TM,
                                                 'FORMAT2')
                 SVC_TASK_CPLT_DT,
              CANON_E307_UTILS.format_date_func (task.SVC_TASK_CLO_DT,
                                                 task.SVC_TASK_CLO_TM,
                                                 'FORMAT2')
                 SVC_TASK_CLO_DT,
              task.svc_task_schd_by_usr_id,
              svc_task_clo_by_usr_id,
              task.svc_rsp_tm_mn_aot,
              task.svc_rpr_tm_num,
              task.mach_down_flg,
              CANON_E307_UTILS.format_date2_func (task.ERL_START_TS, 'FORMAT2')
                 ERL_START_TS,
              CANON_E307_UTILS.format_date2_func (task.LATE_START_TS, 'FORMAT2')
                 LATE_START_TS,
              task.svc_rg_cd,
              task.svc_br_cd,
              '',
              task.svc_br_mgr_psn_cd,
              task.svc_trty_mgr_psn_cd,
              task.svc_team_mgr_psn_cd,
              visit.fsr_visit_num,
              fsr_visit_sts_cd,
              visit.tech_cd visit_tech_cd,
              (select case when (psn_last_nm || ',' || psn_first_nm)<>','
                             then psn_last_nm || ',' || psn_first_nm 
                       else null end 
                          from s21_psn psn
                   where visit.TECH_CD = psn.psn_cd
                   and glbl_cmpy_cd='ADB'
                   and EZCANCELFLAG='0') ASSIGNEE_NAME,
              CANON_E307_UTILS.format_date_func (visit.TECH_SCHD_FROM_DT,
                                                 visit.TECH_SCHD_FROM_TM,
                                                 'FORMAT2')
                 TECH_SCHD_FROM_DT,
              CANON_E307_UTILS.format_date_func (visit.TECH_SCHD_TO_DT,
                                                 visit.TECH_SCHD_TO_TM,
                                                 'FORMAT2')
                 TECH_SCHD_TO_DT,
                 CANON_E307_UTILS.format_date_func (fsr_visit_arv_dt,fsr_visit_arv_tm,'FORMAT2')task_actual_start,
                 CANON_E307_UTILS.format_date_func (fsr_visit_cplt_dt,fsr_visit_cplt_tm,'FORMAT2')task_actual_end,
              visit.SVC_ASG_TP_CD
         FROM svc_task task, fsr_visit visit
 WHERE visit.svc_task_num(+) = task.svc_task_num;
 
 /
 
 CREATE OR REPLACE FORCE VIEW CANON_E307_VENDOR_CALLS_V
 (
    SER_NUM,
    TAG_NUM,
    CUST_NM,
    FSR_NUM,
    SVC_TASK_NUM,
    CREATION_DT,
    TASK_TYPE,
    ASSIGNEE_NAME,
    MODEL,
    BRANCH,
    STATUS,
    EZINTIME
 )
 AS
    SELECT task.ser_num,
           CUST_MACH_CTRL_NUM Tag_Num,
           (SELECT DISTINCT LOC_NM
              FROM SHIP_TO_CUST
             WHERE     fsr.SHIP_TO_CUST_CD = SHIP_TO_CUST.SHIP_TO_CUST_CD
                   AND ROWNUM = 1)
              cust_nm,
           task.fsr_num,
           task.svc_task_num,
           CANON_E307_UTILS.format_date2_func (task.ezintime, 'FORMAT2')
              creation_dt,
           (SELECT DS_SVC_CALL_TP_NM
              FROM DS_SVC_CALL_TP TYPE
             WHERE     TYPE.DS_SVC_CALL_TP_CD = task.DS_SVC_CALL_TP_CD
                   AND EZCANCELFLAG = 0
                   AND GLBL_CMPY_CD = 'ADB')
              task_type,
           (SELECT psn_last_nm || ', ' || psn_first_nm
              FROM s21_psn psn
             WHERE     psn.psn_cd = visit.tech_cd
                   AND psn.glbl_cmpy_cd = 'ADB'
                   AND psn.EZCANCELFLAG = 0)
              ASSIGNEE_NAME,
           task.MDL_NM model,
           (SELECT task.SVC_BR_CD || '-' || branch.svc_contr_br_desc_txt
              FROM svc_contr_br branch
             WHERE     task.SVC_BR_CD = branch.svc_contr_br_cd
                   AND branch.glbl_cmpy_cd = 'ADB'
                   AND branch.ezcancelflag = '0')
              Branch,
           (SELECT SVC_TASK_STS_NM
              FROM SVC_TASK_STS sts
             WHERE     task.SVC_TASK_STS_CD = sts.SVC_TASK_STS_CD
                   AND sts.EZCANCELFLAG = 0
                   AND sts.GLBL_CMPY_CD = 'ADB')
              status,task.ezintime
      FROM fsr,
           svc_task task,
           FSR_VISIT visit,
           SVC_TASK_STS sts
     WHERE     1 = 1
           AND visit.fsr_num = task.fsr_num
           AND task.fsr_num = fsr.fsr_num
           AND task.svc_task_sts_cd = sts.svc_task_sts_cd
           --TBD confirm the status with BPR
           --AND sts.svc_task_sts_nm IN ('Open', 'Assigned')
           AND EXISTS
                  (SELECT 1
                     FROM s21_psn psn, psn_tp, canon_e307_setup_tbl setup
                    WHERE psn.psn_cd = visit.tech_cd
                          AND psn.psn_tp_cd = psn_tp.psn_tp_cd
                          AND psn_tp.psn_tp_nm = setup.setup_value
                          AND setup.setup_name = 'VENDOR_TECH_TYPE'
                          AND psn.glbl_cmpy_cd = 'ADB'
                          AND psn.ezcancelflag = '0'
                          AND psn_tp.glbl_cmpy_cd = 'ADB'
                          AND psn_tp.ezcancelflag = '0')
           AND fsr_visit_num =
                  (SELECT MAX (fsr_visit_num)
                     FROM fsr_visit visit
                    WHERE     visit.FSR_NUM = task.fsr_num
                          AND visit.glbl_cmpy_cd = 'ADB')
           AND visit.GLBL_CMPY_CD = 'ADB'
           AND sts.GLBL_CMPY_CD = 'ADB';
           
          /
CREATE OR REPLACE PACKAGE CANON_E307_CALL_SUPPORT_PKG
AS
/*******************************************************************************************
  Package Name: CANON_E307_CALL_SUPPORT_PKG_SPEC
  Description:  Package to be used by Canon Smart Dispatch Application
  Dependencies: Canon Smart Dispatch Application JSP pages
  Action History:
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
  Hema Doniparthi	  2.0                  14-Mar-2016				Modified for changes
*****************************************************************************************/
g_glbl_cmpy_cd   VARCHAR2 (10) := canon_e307_constants.g_global_company_code;
g_cancel_flg     VARCHAR2 (10) := canon_e307_constants.g_cancel_flg;
g_file_upload_path  VARCHAR2(50) := canon_e307_constants.g_file_upload_path;

TYPE cur_typ
   IS REF CURSOR;

PROCEDURE GET_CONTRACT_INFO (
   p_in_serial              IN     VARCHAR2,
   x_ds_contr_pk               OUT VARCHAR2,
   x_ds_contr_dtl_pk           OUT VARCHAR2,
   x_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL);

PROCEDURE GET_UGW_ERR_CODE (p_in_serial   IN     VARCHAR2,
                            x_ugw_tbl        OUT CANON_E307_UGW_ERR_CODE_TBL);

PROCEDURE GET_CUR_LOCATION (p_in_serial      IN     VARCHAR2,
                            x_cust_loc_tbl      OUT CANON_E307_CUST_LOC_TBL);

PROCEDURE GET_BILL_TO_LOCATION (
   p_in_serial     IN     VARCHAR2,
   x_bill_to_tbl      OUT CANON_E307_CUST_LOC_TBL);

PROCEDURE GET_EQUIP_BRANCH (p_in_serial   IN     VARCHAR2,
                            o_br_cd          OUT VARCHAR2,
                            o_br_desc        OUT VARCHAR2);

/*FUNCTION GET_TASK_DET( p_in_sr_num IN VARCHAR2,
               p_in_col    IN VARCHAR2,
               p_in_num     IN VARCHAR2)
               RETURN VARCHAR2  ;*/
PROCEDURE GET_ATTACH_FILE_INFO (  p_in_business_id        IN     VARCHAR2,
                                p_in_att_group_txt        IN     VARCHAR2,
                                p_in_file_name            IN     VARCHAR2,
                                p_ignore_dup_file_name    IN     VARCHAR2 DEFAULT 'Y',
                                o_file_id_tbl             OUT CANON_E307_ATT_FILE_TBL);
                                

FUNCTION GET_MACH_SLN (p_in_serial IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_PO_REQ_FLG (p_in_serial            IN VARCHAR2,
                         p_in_sell_to_cust_cd   IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_MAX_VISIT (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_MAX_TASK (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_PSN_NM (p_in_usr_cd IN VARCHAR2)
   RETURN VARCHAR2;
PROCEDURE GET_FILE_UPLOAD_PATH(o_file_upload_path OUT VARCHAR2);
    
END CANON_E307_CALL_SUPPORT_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_CALL_SUPPORT_PKG
IS
/*******************************************************************************************
 Package Name: CANON_E307_CALL_SUPPORT_PKG_BODY
 Description:  Package to be used by Canon Smart Dispatch Application
 Dependencies: Canon Smart Dispatch Application JSP pages
 Action History:
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
 *******************************************************************************************/

/*******************************************************************************************
 Procedure Name: GET_CONTRACT_INFO
 Description: Get contract details
 Input Parameters: p_in_serial
            
 Output Parameters: x_ds_contr_pk
             x_ds_contr_dtl_pk
             x_contract_details_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_CONTRACT_INFO (
   p_in_serial              IN     VARCHAR2,
   x_ds_contr_pk               OUT VARCHAR2,
   x_ds_contr_dtl_pk           OUT VARCHAR2,
   x_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL)
IS
   l_rec_contract_details   CANON_E307_CONTRACT_REC;
   lv_ds_contr_pk           VARCHAR2 (100);
   lv_ds_contr_dtl_pk       VARCHAR2 (100);
   l_weekday_hours          VARCHAR2 (100);
   l_sat_hours              VARCHAR2 (100);
   l_sun_hours              VARCHAR2 (100);
   ln_contract_rec_cnt      NUMBER := 1;
   l_cust_cd            VARCHAR2 (100);
   l_cust_name              VARCHAR2 (100);
   l_header_eff_string      VARCHAR2 (100);
   l_line_eff_string        VARCHAR2 (100);
   l_sla_converted          VARCHAR2 (50);

   CURSOR cur_contract_details
   IS
      SELECT ib.sell_to_cust_cd,
             cont_header.ds_contr_num contract_number,
             cont_type.ds_contr_tp_nm contract_type,
             cont_header.ds_contr_pk,
             cont_header.contr_vrsn_eff_from_dt header_start_date,
             cont_header.contr_vrsn_eff_thru_dt header_end_date,
             cont_detail.contr_eff_from_dt line_start_date,
             cont_detail.contr_eff_thru_dt line_end_date,
             cont_hdr_sts.ds_contr_sts_nm header_status,
             cont_det_sts.ds_contr_dtl_sts_nm line_status,
             cont_detail.rsp_tm_up_mn_aot,
             cont_detail.ds_contr_dtl_pk
        FROM svc_mach_mstr ib,
             ds_contr_dtl cont_detail,
             ds_contr cont_header,
             ds_contr_tp cont_type,
             ds_contr_sts cont_hdr_sts,
             ds_contr_dtl_sts cont_det_sts
       WHERE     1 = 1
             AND ib.svc_mach_mstr_pk = cont_detail.svc_mach_mstr_pk
             AND cont_detail.DS_CONTR_PK = cont_header.DS_CONTR_PK
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ser_num = p_in_serial
             /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                 TO_CHAR (SYSDATE, 'YYYYMMDD')
                          AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                        TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND ib.ezcancelflag = g_cancel_flg
             AND cont_detail.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.DS_CONTR_TP_CD = cont_type.DS_CONTR_TP_CD
             AND cont_header.DS_CONTR_STS_CD = cont_hdr_sts.DS_CONTR_STS_CD
             AND cont_detail.DS_CONTR_DTL_STS_CD =
                    cont_det_sts.DS_CONTR_DTL_STS_CD
             AND cont_type.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_hdr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_det_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_detail.ezcancelflag = g_cancel_flg
             AND cont_header.ezcancelflag = g_cancel_flg
             AND cont_type.ezcancelflag = g_cancel_flg
             AND cont_hdr_sts.ezcancelflag = g_cancel_flg
             AND cont_det_sts.ezcancelflag = g_cancel_flg;

BEGIN
   x_contract_details_tbl := CANON_E307_CONTRACT_TBL ();

   -- debug_pkg.debug_proc('Inside  Package ');
   FOR fr_cur_contract_details IN cur_contract_details
   LOOP
      x_ds_contr_dtl_pk := fr_cur_contract_details.ds_contr_dtl_pk;
      x_ds_contr_pk := fr_cur_contract_details.ds_contr_pk;

      BEGIN
         SELECT sell_to.sell_to_cust_cd,sell_to.loc_nm cust_name
           INTO l_cust_cd,l_cust_name
           FROM sell_to_cust sell_to
          WHERE     sell_to.SELL_TO_CUST_CD =
                       fr_cur_contract_details.SELL_TO_CUST_CD
                AND sell_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND NVL (sell_to.eff_thru_dt, SYSDATE + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_cd   := NULL;
            l_cust_name := NULL;
      END;

      l_sla_converted :=
         CANON_E307_UTILS.format_time (
            p_time       => fr_cur_contract_details.rsp_tm_up_mn_aot,
            p_time_uom   => 'MIN',
            p_format     => 'FORMAT1');

      l_header_eff_string :=
         CANON_E307_UTILS.format_date_range_string (
            fr_cur_contract_details.header_start_date,
            fr_cur_contract_details.header_end_date,
            'FORMAT1');
      l_line_eff_string :=
         CANON_E307_UTILS.format_date_range_string (
            fr_cur_contract_details.line_start_date,
            fr_cur_contract_details.line_end_date,
            'FORMAT1');
      l_rec_contract_details :=
         CANON_E307_CONTRACT_REC (l_cust_cd,
                        l_cust_name,
                                  fr_cur_contract_details.contract_number,
                                  fr_cur_contract_details.contract_type,
                                  fr_cur_contract_details.header_start_date,
                                  fr_cur_contract_details.header_end_date,
                                  l_header_eff_string,
                                  fr_cur_contract_details.line_start_date,
                                  fr_cur_contract_details.line_end_date,
                                  l_line_eff_string,
                                  fr_cur_contract_details.header_status,
                                  fr_cur_contract_details.line_status,
                                  l_sla_converted);
      x_contract_details_tbl.EXTEND ();
      x_contract_details_tbl (ln_contract_rec_cnt) := l_rec_contract_details;
      ln_contract_rec_cnt := ln_contract_rec_cnt + 1;
      NULL;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_CONTRACT_INFO;

/*******************************************************************************************
 Procedure Name: GET_UGW_ERR_CODE
 Description: Get last four UGW error codes for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: x_ugw_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_UGW_ERR_CODE (p_in_serial   IN     VARCHAR2,
                            x_ugw_tbl        OUT CANON_E307_UGW_ERR_CODE_TBL)
IS
   l_rec_ugw        CANON_E307_UGW_ERR_CODE_REC;
   ln_ugw_rec_cnt   NUMBER := 1;

   CURSOR cur_ugw_err_code
   IS
      SELECT UGW_ERR_CODE
        FROM (  SELECT DISTINCT
                       ERROR_CODE || ' / ' || error_date UGW_ERR_CODE,
                       error_date
                  FROM canon_e307_smart_disp_dtls_vl
                 WHERE serial_number = p_in_serial
              ORDER BY error_date DESC)
       WHERE ROWNUM < 5;

BEGIN
   x_ugw_tbl := CANON_E307_UGW_ERR_CODE_TBL ();

   --debug_pkg.debug_proc('Inside New Package CANON_E307_CALL_SUPPORT_PKG.GET_UGW_ERR_CODE');
   --Start Fetching UGW error Codes
   FOR fr_cur_ugw_err_code IN cur_ugw_err_code
   LOOP
      l_rec_ugw :=
         CANON_E307_UGW_ERR_CODE_REC (fr_cur_ugw_err_code.UGW_ERR_CODE);
      x_ugw_tbl.EXTEND ();
      x_ugw_tbl (ln_ugw_rec_cnt) := l_rec_ugw;
      ln_ugw_rec_cnt := ln_ugw_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_UGW_ERR_CODE;

/*******************************************************************************************
 Procedure Name: GET_CUR_LOCATION
 Description: Get current location for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: x_cust_loc_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

PROCEDURE GET_CUR_LOCATION (p_in_serial      IN     VARCHAR2,
                            x_cust_loc_tbl      OUT CANON_E307_CUST_LOC_TBL)
IS
   l_rec_cust_loc_details   CANON_E307_CUST_LOC_REC;
   ln_cust_loc_rec_cnt      NUMBER := 1;

   CURSOR cur_cust_loc
   IS
      SELECT ship_to.sell_to_cust_cd CUST_CODE,
             ship_to.loc_nm CUST_NAME,
             ship_to.FIRST_LINE_ADDR ADDRESS,
             ship_to.CTY_ADDR CITY,
             ship_to.ST_CD STATE,
             ship_to.POST_CD POSTAL_CODE,
             ship_to.CTRY_CD COUNTRY,
             '' PAYMENT_TERM
        FROM svc_mach_mstr ib, ship_to_cust ship_to
       WHERE     ib.ser_num = p_in_serial                       --'HHOZDYYHSH'
             AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND ship_to.ship_to_cust_cd = ib.cur_loc_num --ib.ship_to_cust_cd
             AND ROWNUM = 1;

BEGIN
   x_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();

   -- debug_pkg.debug_proc('Inside New Package CANON_E307_CALL_SUPPORT_PKG.GET_CUR_LOCATION');
   --Start Fetching Current location details
   FOR fr_cur_cust_loc IN cur_cust_loc
   LOOP
      l_rec_cust_loc_details :=
         CANON_E307_CUST_LOC_REC (fr_cur_cust_loc.CUST_CODE,
                                  fr_cur_cust_loc.CUST_NAME,
                                  fr_cur_cust_loc.ADDRESS,
                                  fr_cur_cust_loc.CITY,
                                  fr_cur_cust_loc.STATE,
                                  fr_cur_cust_loc.POSTAL_CODE,
                                  fr_cur_cust_loc.COUNTRY,
                                  fr_cur_cust_loc.PAYMENT_TERM);
      x_cust_loc_tbl.EXTEND ();
      x_cust_loc_tbl (ln_cust_loc_rec_cnt) := l_rec_cust_loc_details;
      -- debug_pkg.debug_proc('fr_cur_cust_loc.CUST_CODE = '||fr_cur_cust_loc.CUST_CODE);
       -- debug_pkg.debug_proc('fr_cur_cust_loc.CUST_Address ='||fr_cur_cust_loc.ADDRESS);
      ln_cust_loc_rec_cnt := ln_cust_loc_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
-- debug_pkg.debug_proc('Inside Exception '||sqlerrm);
END GET_CUR_LOCATION;

/*******************************************************************************************
 Procedure Name: GET_BILL_TO_LOCATION
 Description: Get bill to location for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: x_bill_to_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_BILL_TO_LOCATION (
   p_in_serial     IN     VARCHAR2,
   x_bill_to_tbl      OUT CANON_E307_CUST_LOC_TBL)
IS
   l_rec_bill_to_details   CANON_E307_CUST_LOC_REC;
   ln_bill_to_rec_cnt      NUMBER := 1;

   CURSOR cur_bill_to
   IS
      SELECT bill_to.bill_to_cust_cd CUST_CODE,
             bill_to.loc_nm CUST_NAME,
             bill_to.first_line_addr ADDRESS,
             bill_to.cty_addr CITY,
             bill_to.st_cd STATE,
             bill_to.post_cd POSTAL_CODE,
             bill_to.ctry_cd COUNTRY,
             (SELECT pt.pmt_term_nm
                FROM cust_pmt_term cpt, pmt_term pt
               WHERE     bill_to.bill_to_cust_cd = cpt.bill_to_cust_cd
                     AND cpt.pmt_term_cd = pt.pmt_term_cd(+)
                     AND cpt.glbl_cmpy_cd = g_glbl_cmpy_cd
                     AND pt.glbl_cmpy_cd = g_glbl_cmpy_cd
                     AND ROWNUM = 1)
                PAYMENT_TERM
        FROM svc_mach_mstr ib, bill_to_cust bill_to
       WHERE                   -- ib.BILL_TO_CUST_CD = bill_to.BILL_TO_CUST_CD
            ib   .bill_to_loc_num = bill_to.BILL_TO_CUST_CD
             AND ib.ser_num = p_in_serial
             AND NVL (bill_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')               --'HHOZDYYHSH'
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND bill_to.ezcancelflag = g_cancel_flg
             AND ROWNUM = 1;

BEGIN
   x_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();

   --debug_pkg.debug_proc('Inside New Package CANON_E307_CALL_SUPPORT_PKG.GET_BILL_TO_LOCATION');
   --Start Fetching bill to location details
   FOR fr_cur_bill_to IN cur_bill_to
   LOOP
      l_rec_bill_to_details :=
         CANON_E307_CUST_LOC_REC (fr_cur_bill_to.cust_code,
                                  fr_cur_bill_to.cust_name,
                                  fr_cur_bill_to.address,
                                  fr_cur_bill_to.city,
                                  fr_cur_bill_to.state,
                                  fr_cur_bill_to.postal_code,
                                  fr_cur_bill_to.country,
                                  fr_cur_bill_to.payment_term);
      x_bill_to_tbl.EXTEND ();
      x_bill_to_tbl (ln_bill_to_rec_cnt) := l_rec_bill_to_details;
      ln_bill_to_rec_cnt := ln_bill_to_rec_cnt + 1;
      --debug_pkg.debug_proc('Bill To ADDRESS = '||fr_cur_bill_to.address);
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_BILL_TO_LOCATION;

/*******************************************************************************************
 Procedure Name: GET_EQUIP_BRANCH
 Description: Get bill to location for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: o_br_cd
             o_br_desc
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_EQUIP_BRANCH (p_in_serial   IN     VARCHAR2,
                            o_br_cd          OUT VARCHAR2,
                            o_br_desc        OUT VARCHAR2)
IS
BEGIN
   SELECT fld_svc_br_cd, svc_contr_br_desc_txt
     INTO o_br_cd, o_br_desc
     FROM svc_contr_br branch, svc_mach_mstr ib
    WHERE     branch.svc_contr_br_cd = ib.fld_svc_br_cd
          AND ib.ser_num = p_in_serial
          /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                                   AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')*/
          AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND ib.ezcancelflag = g_cancel_flg
          AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND branch.ezcancelflag = g_cancel_flg;
EXCEPTION
   WHEN OTHERS
   THEN
      o_br_cd := NULL;
      o_br_desc := NULL;
END GET_EQUIP_BRANCH;

/*******************************************************************************************
 Function Name: GET_TASK_DET
 Description: Get Task details
 Input Parameters: p_in_serial
            p_in_col
            p_in_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

/*FUNCTION GET_TASK_DET (p_in_sr_num   IN VARCHAR2,
                       p_in_col      IN VARCHAR2,
                       p_in_num      IN VARCHAR2)
   RETURN VARCHAR2
IS
   lv_count         NUMBER := 0;
   lv_count1        NUMBER := 0;
   lv_task_number   VARCHAR2 (100);
   lv_resp_tm       VARCHAR2 (100);
   lv_task1         VARCHAR2 (100);
   lv_resp_tm1      VARCHAR2 (100);
   lv_task2         VARCHAR2 (100);
   lv_resp_tm2      VARCHAR2 (100);
   lv_task3         VARCHAR2 (100);
   lv_resp_tm3      VARCHAR2 (100);
   o_val            VARCHAR2 (100);
   i                NUMBER := 1;
   lv_task_type      VARCHAR2 (100);
   lv_return_col VARCHAR2 (1000);
   v_sql        VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
    
  
BEGIN
--debug_pkg.debug_proc('Inside GET_TASK_DET');
 IF p_in_col = 'TASK_NUM'
   THEN
      lv_return_col:='SVC_TASK_NUM';
   ELSIF p_in_col = 'RESP_TM'
   THEN
      lv_return_col:='SVC_RSP_TM_MN_AOT';
   ELSIF p_in_col = 'TSK_CR_TM'
   THEN
      lv_return_col:='CANON_E307_UTILS.format_date2_func (EZINTIME, ''FORMAT2'') EZINTIME';
   ELSIF p_in_col = 'EARLY_START'
   THEN
      lv_return_col:='CANON_E307_UTILS.format_date2_func (ERL_START_TS, ''FORMAT2'')ERL_START_TS';
   ELSIF p_in_col = 'LATE_START'
   THEN
       lv_return_col:='CANON_E307_UTILS.format_date2_func (LATE_START_TS, ''FORMAT2'') LATE_START_TS';
    ELSIF p_in_col = 'TASK_TYPE'
   THEN
   lv_return_col:=' case when (ds_svc_call_tp_cd || ''-'' || DS_SVC_CALL_TP_NM)<>''-'''
               || ' THEN ds_svc_call_tp_cd || ''-'' || DS_SVC_CALL_TP_NM '
               || ' ELSE NULL END';
   --lv_return_col:='DS_SVC_CALL_TP_NM';
   ELSIF p_in_col = 'ASSIGNEE'
   THEN
       lv_return_col:=' case when (PSN_LAST_NM || '','' || PSN_FIRST_NM)<>'','''
               || ' THEN PSN_LAST_NM || '','' || PSN_FIRST_NM '
               || ' ELSE NULL END';
       --lv_return_col:= 'PSN_LAST_NM || '','' || PSN_FIRST_NM';
      
   ELSIF p_in_col = 'TSK_SCH_START'
   THEN
        lv_return_col:= 'CANON_E307_UTILS.format_date_func (tech_schd_from_dt,tech_schd_from_tm,''FORMAT2'')sch_start_date'; 
   ELSIF p_in_col = 'TSK_SCH_END'
   THEN
       lv_return_col:= 'CANON_E307_UTILS.format_date_func (tech_schd_to_dt,tech_schd_to_tm,''FORMAT2'')sch_end_date';
   ELSIF p_in_col = 'TSK_ACT_START'
      THEN
       lv_return_col:= 'CANON_E307_UTILS.format_date_func (fsr_visit_arv_dt,fsr_visit_arv_tm,''FORMAT2'')actual_start_date'; 
   ELSIF p_in_col = 'TSK_ACT_END'
         THEN
        lv_return_col:='CANON_E307_UTILS.format_date_func (fsr_visit_cplt_dt,fsr_visit_cplt_tm,''FORMAT2'')actual_end_date'; 
   END IF;
l_default_from := ' FROM (select rownum row_num,task.*,TYPE.DS_SVC_CALL_TP_NM,visit.tech_schd_from_dt,visit.tech_schd_from_tm, '
            || ' visit.tech_schd_to_dt,visit.tech_schd_to_tm,visit.fsr_visit_arv_dt,visit.fsr_visit_arv_tm, '   
            || ' visit.fsr_visit_cplt_dt,visit.fsr_visit_cplt_tm,psn.PSN_LAST_NM, psn.PSN_FIRST_NM '
            || ' FROM svc_task task,ds_svc_call_tp TYPE,fsr_visit visit, S21_PSN psn '
            || ' where 1=1 '
            || ' AND task.fsr_num = '''||p_in_sr_num ||''''
            || ' AND TYPE.ds_svc_call_tp_cd = task.ds_svc_call_tp_cd '
            || ' AND visit.svc_task_num(+)=task.svc_task_num '
            || ' AND visit.tech_cd = psn.psn_cd(+)' 
            || ' AND TYPE.GLBL_CMPY_CD=''ADB''  )';
v_sql :='select '
      || lv_return_col
      || l_default_from
      || '  WHERE row_num= '
      || p_in_num;
      
EXECUTE IMMEDIATE v_sql INTO o_val;    
  RETURN o_val;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_TASK_DET;*/

/*******************************************************************************************
 Function Name: GET_TASK_DET
 Description: Get Task details
 Input Parameters: p_in_serial
            p_in_col
            p_in_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_MACH_SLN (p_in_serial   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_sol    svc_config_mstr.svc_sln_nm%TYPE;
    
  
BEGIN

SELECT config.svc_sln_nm
INTO lv_sol
FROM svc_mach_mstr smm, svc_config_mstr config
WHERE     1 = 1
AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
/*and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd;

    
  RETURN lv_sol;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_MACH_SLN;

/*******************************************************************************************
 Function Name: GET_PO_REQ_FLG
 Description: Get PO required flag
 Input Parameters: p_in_serial
            p_in_col
            p_in_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_PO_REQ_FLG (p_in_serial            IN VARCHAR2,
                         p_in_sell_to_cust_cd   IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_rec_bill_to_details   CANON_E307_CUST_LOC_REC;
   ln_bill_to_rec_cnt      NUMBER := 1;
   lv_po_flag              ds_cust_trx_rule.ds_po_req_flg%TYPE;
BEGIN
   SELECT po.ds_po_req_flg
     INTO lv_po_flag
     FROM SVC_MACH_MSTR ib, BILL_TO_CUST bill_to, ds_cust_trx_rule po
    WHERE     ib.bill_to_loc_num = bill_to.BILL_TO_CUST_CD
          AND ib.ser_num = p_in_serial
          /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')
                       AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                     TO_CHAR (SYSDATE, 'YYYYMMDD')*/
          --'HHOZDYYHSH'
          AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND ib.ezcancelflag = g_cancel_flg
          AND bill_to.ezcancelflag = g_cancel_flg
          AND po.ds_acct_num = p_in_sell_to_cust_cd
          AND po.loc_num = bill_to.loc_nm
          AND svc_by_line_biz_tp_cd IN ('PPS', 'LFS')
          AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND po.ezcancelflag = g_cancel_flg
          AND ROWNUM = 1;

   RETURN lv_po_flag;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      SELECT po.ds_po_req_flg
        INTO lv_po_flag
        FROM ds_cust_trx_rule po
       WHERE     1 = 1
             AND po.ds_acct_num = p_in_sell_to_cust_cd
             AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND po.ezcancelflag = g_cancel_flg
             AND ROWNUM = 1;

      RETURN lv_po_flag;
   WHEN OTHERS
   THEN
      RETURN 'N';
END GET_PO_REQ_FLG;

/*******************************************************************************************
 Function Name: GET_MAX_VISIT
 Description: Get the latest visit for a sr
 Input Parameters: p_in_sr_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_MAX_VISIT (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2
IS
   lv_visit_num   fsr_visit.fsr_visit_num%TYPE;
BEGIN
   SELECT MAX (fsr_visit_num)
     INTO lv_visit_num
     FROM fsr_visit visit
    WHERE visit.FSR_NUM = p_in_sr_num AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd;


   RETURN lv_visit_num;
EXCEPTION
   WHEN OTHERS
   THEN
   --debug_pkg.debug_proc('GET_MAX_VISIT ='||SQLERRM);
      RETURN NULL;
END GET_MAX_VISIT;

/*******************************************************************************************
 Function Name: GET_MAX_TASK
 Description: Get the latest task for a sr
 Input Parameters: p_in_sr_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_MAX_TASK (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2
IS
   lv_task_num   svc_task.svc_task_num%TYPE;
BEGIN
   SELECT MAX (SVC_TASK_NUM)
     INTO lv_task_num
     FROM SVC_TASK
    WHERE FSR_NUM = p_in_sr_num AND GLBL_CMPY_CD = g_glbl_cmpy_cd;


   RETURN lv_task_num;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_MAX_TASK;

/*******************************************************************************************
 Function Name: GET_PSN_NM
 Description: Get the user name for user id
 Input Parameters: p_in_usr_cd

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_PSN_NM (p_in_usr_cd   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_usr_nm   VARCHAR2(500);
    
  
BEGIN

 SELECT psn_last_nm || ',' || psn_first_nm
   INTO lv_usr_nm
                   from s21_psn psn
               where psn.psn_cd=p_in_usr_cd
                and glbl_cmpy_cd=g_glbl_cmpy_cd
                and EZCANCELFLAG=g_cancel_flg;

    
  RETURN lv_usr_nm;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_PSN_NM;


/*******************************************************************************************
 Procedure Name: TASK_STS_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: p_in_attr
            
 Output Parameters: o_lov_tbl  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE TASK_STS_LOV (p_in_from_sts   IN     VARCHAR2,
                p_in_usr_id     IN     VARCHAR2,
                       o_lov_tbl      OUT CANON_E307_STS_LOV_TBL)
IS
   l_rec_lov_cd   CANON_E307_STS_LOV_REC;
   ln_rec_cnt     NUMBER := 1;


   CURSOR cur_task_sts
   IS
      SELECT status2.svc_task_sts_cd,
               status2.svc_task_sts_nm
        FROM DS_ORG_RESRC_RELN usr,
             SVC_STS_TRNST_RULE_ASG rule,
             SVC_TASK_STS_TRNST TRNST,
             SVC_TASK_STS status1,
             SVC_TASK_STS status2
       WHERE     usr.ORG_FUNC_ROLE_TP_CD = rule.ORG_FUNC_ROLE_TP_CD
             AND rule.SVC_STS_TRNST_RULE_CD = TRNST.SVC_STS_TRNST_RULE_CD
             AND TRNST.SVC_TASK_FROM_STS_CD = status1.SVC_TASK_STS_CD
             AND upper(status1.SVC_TASK_STS_NM) = upper(p_in_from_sts)
             AND usr.psn_cd =p_in_usr_id
             AND trnst.svc_task_to_sts_cd = status2.svc_task_sts_cd
             AND usr.EZCANCELFLAG = g_cancel_flg
             AND rule.EZCANCELFLAG = g_cancel_flg
             AND TRNST.EZCANCELFLAG = g_cancel_flg
             AND status1.EZCANCELFLAG = g_cancel_flg
             AND status2.EZCANCELFLAG = g_cancel_flg
             AND usr.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND rule.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND TRNST.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND status1.GLBL_CMPY_CD = g_glbl_cmpy_cd
               AND status2.GLBL_CMPY_CD = g_glbl_cmpy_cd;
       

BEGIN
   o_lov_tbl := CANON_E307_STS_LOV_TBL ();
   ln_rec_cnt := 1;

      FOR fr_cur_task_sts IN cur_task_sts
      LOOP
         l_rec_lov_cd :=
            CANON_E307_STS_LOV_REC ( fr_cur_task_sts.svc_task_sts_cd,
                             fr_cur_task_sts.svc_task_sts_nm);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END TASK_STS_LOV;

/*******************************************************************************************
 Procedure Name: SR_STS_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: p_in_from_sts,
            p_in_usr_id
            
 Output Parameters: o_lov_tbl  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE SR_STS_LOV (p_in_from_sts   IN     VARCHAR2,
                p_in_usr_id     IN     VARCHAR2,
                       o_lov_tbl      OUT CANON_E307_STS_LOV_TBL)
IS
   l_rec_lov_cd   CANON_E307_STS_LOV_REC;
   ln_rec_cnt     NUMBER := 1;


   CURSOR cur_sr_sts
   IS
      SELECT status2.svc_task_sts_cd,
               status2.svc_task_sts_nm
        FROM DS_ORG_RESRC_RELN usr,
             SVC_STS_TRNST_RULE_ASG rule,
             FSR_STS_TRNST TRNST,
             SVC_TASK_STS status1,
             SVC_TASK_STS status2
       WHERE     usr.ORG_FUNC_ROLE_TP_CD = rule.ORG_FUNC_ROLE_TP_CD
             AND rule.SVC_STS_TRNST_RULE_CD = TRNST.SVC_STS_TRNST_RULE_CD
             AND trnst.fsr_from_sts_cd = status1.SVC_TASK_STS_CD
             AND upper(status1.SVC_TASK_STS_NM) = upper(p_in_from_sts)
             AND usr.psn_cd =p_in_usr_id
             AND trnst.fsr_to_sts_cd = status2.svc_task_sts_cd
             AND usr.EZCANCELFLAG = g_cancel_flg
             AND rule.EZCANCELFLAG = g_cancel_flg
             AND TRNST.EZCANCELFLAG = g_cancel_flg
             AND status1.EZCANCELFLAG = g_cancel_flg
             AND status2.EZCANCELFLAG = g_cancel_flg
             AND usr.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND rule.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND TRNST.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND status1.GLBL_CMPY_CD = g_glbl_cmpy_cd
               AND status2.GLBL_CMPY_CD = g_glbl_cmpy_cd;
       

BEGIN
   o_lov_tbl := CANON_E307_STS_LOV_TBL ();
   ln_rec_cnt := 1;

      FOR fr_cur_sr_sts IN cur_sr_sts
      LOOP
         l_rec_lov_cd :=
            CANON_E307_STS_LOV_REC ( fr_cur_sr_sts.svc_task_sts_cd,
                             fr_cur_sr_sts.svc_task_sts_nm);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END SR_STS_LOV;
PROCEDURE GET_ATTACH_FILE_INFO (  p_in_business_id        IN     VARCHAR2,
                                p_in_att_group_txt        IN     VARCHAR2,
                                p_in_file_name            IN     VARCHAR2,
                                p_ignore_dup_file_name    IN     VARCHAR2 DEFAULT 'Y',
                                o_file_id_tbl             OUT CANON_E307_ATT_FILE_TBL)
IS
   l_file_id NUMBER;
   g_e307_appl_id VARCHAR2(30) := 'S21EXTN_E307';
   ln_rec_cnt NUMBER := 1;
   l_rec_att_file  CANON_E307_ATT_FILE_REC;
  CURSOR C1 IS
    SELECT ATT_DATA_PK, EZBUSINESSID, ATT_DATA_GRP_TXT, ATT_DATA_NM
     FROM ATT_DATA a
     WHERE EZBUSINESSID             =   p_in_business_id
     AND NVL(ATT_DATA_GRP_TXT, 'X') = NVL(p_in_att_group_txt, NVL(ATT_DATA_GRP_TXT, 'X'))
     AND ATT_DATA_NM                = NVL(p_in_file_name, ATT_DATA_NM)
     AND GLBL_CMPY_CD               = g_glbl_cmpy_cd
 --    AND EZUPAPLID                  = g_e307_appl_id
     AND ( (p_ignore_dup_file_name = 'Y' AND 
                ATT_DATA_PK IN 
                        (SELECT MAX(ATT_DATA_PK)
                                FROM ATT_DATA b
                                  WHERE a.EZBUSINESSID = b.EZBUSINESSID
                                  AND NVL(a.ATT_DATA_GRP_TXT, 'X') = NVL(b.ATT_DATA_GRP_TXT, 'X')
                                  AND a.GLBL_CMPY_CD = b.GLBL_CMPY_CD
                                  AND a.EZUPAPLID = b.EZUPAPLID)
            ) OR (p_ignore_dup_file_name = 'N')
           );
    BEGIN
        o_file_id_tbl :=  CANON_E307_ATT_FILE_TBL();
        FOR c1rec in c1 loop
         l_rec_att_file :=
            CANON_E307_ATT_FILE_REC 
                    (c1rec.EZBUSINESSID,
                     c1rec.ATT_DATA_GRP_TXT,
                     c1rec.ATT_DATA_PK,
                     c1rec.ATT_DATA_NM);
            o_file_id_tbl.EXTEND ();
            o_file_id_tbl (ln_rec_cnt) := l_rec_att_file;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
    EXCEPTION
      WHEN OTHERS
         THEN
            NULL;
    END GET_ATTACH_FILE_INFO; 

PROCEDURE GET_FILE_UPLOAD_PATH (o_file_upload_path OUT VARCHAR2)
IS
BEGIN

   SELECT g_file_upload_path
   INTO o_file_upload_path from dual;
   
EXCEPTION
   WHEN OTHERS
   THEN
      o_file_upload_path := NULL;
END GET_FILE_UPLOAD_PATH;  
        
END CANON_E307_CALL_SUPPORT_PKG;
/
SHOW ERRORS;

CREATE OR REPLACE PACKAGE CANON_E307_DSPTCH_PKG
AS
/*******************************************************************************************
   Package Name: CANON_E307_DSPTCH_PKG_SPEC
   Description:  Package to be used by Canon Smart Dispatch Application
   Dependencies: Canon Smart Dispatch Application JSP pages
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*****************************************************************************************/
g_glbl_cmpy_cd VARCHAR2(10):=canon_e307_constants.g_global_company_code;
g_cancel_flg VARCHAR2(10):=canon_e307_constants.g_cancel_flg;
TYPE cur_typ IS REF CURSOR;
PROCEDURE GET_SRL_INFO (p_in_ser_num      IN     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        p_start           IN       NUMBER,
                  p_end              IN       NUMBER,
                        p_in_sort_by      IN     VARCHAR2,
                        p_in_sort_order   IN     VARCHAR2,
                        x_count              OUT NUMBER,
                        o_ser_tbl            OUT CANON_E307_MAC_SER_TBL) ; 

PROCEDURE GET_MACHINE_COUNT
                 (p_io_ser_num     IN OUT     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        x_count           OUT    NUMBER,
                        x_mach_mstr_pk      OUT     VARCHAR2,
                        x_model          OUT     VARCHAR2
         );                        
PROCEDURE GET_UGW_DETAILS ( p_cust_in IN VARCHAR2
                ,p_in_ser_eid IN VARCHAR2
                ,p_model_in  IN VARCHAR2
                ,p_branch_in IN VARCHAR2
                    ,p_start     IN       NUMBER
                      ,p_end        IN       NUMBER
                ,p_in_sort_by      IN     VARCHAR2
                            ,p_in_sort_order   IN     VARCHAR2
                            ,x_count              OUT NUMBER
                            ,o_ugw_tbl            OUT CANON_E307_UGW_TBL);  
PROCEDURE CHECK_UGW_LOCKOUT (
      p_srl_in          IN       VARCHAR2,
      p_usr_id_in       IN       VARCHAR2,
      p_locked_by_out   OUT      VARCHAR2
   );                            
PROCEDURE BRANCH_CODE_LOV (
       o_branch_code_tbl         OUT CANON_E307_BRANCH_CODE_TBL); 
PROCEDURE GET_FUTURE_SR_DTLS (
   p_in_Serial       IN     VARCHAR2,
   p_in_date         IN     VARCHAR2,
   p_in_branch         IN     VARCHAR2,
   p_in_cust_nm      IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_sr_tbl             OUT CANON_E307_SR_DETL_TBL);      
PROCEDURE GET_VENDOR_SR_DTLS (
   p_in_Serial       IN     VARCHAR2,
   p_in_date         IN     VARCHAR2,
   p_in_cust         IN     VARCHAR2,
   p_in_sts         IN     VARCHAR2,
   p_in_assignee     IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_sr_tbl             OUT CANON_E307_SR_DETL_TBL);    
FUNCTION GET_SR_COUNT (p_in_sr_num   IN VARCHAR2
               ,p_in_tsk_num  IN VARCHAR2)
   RETURN NUMBER;                            
END CANON_E307_DSPTCH_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_DSPTCH_PKG
IS
/*******************************************************************************************
Package Name: CANON_E307_DSPTCH_PKG_BODY
Description:  Package to be used by Canon Smart Dispatch Application
Dependencies: Canon Smart Dispatch Application JSP pages
Action History:
-----------------------------------------------------------------------------------------
Author          Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/

/*******************************************************************************************
Procedure Name: GET_SRL_INFO
Description: Procedure called by jsp from ASCC Search Screen
Input Parameters: p_in_ser_num 
                  p_in_sol_nm  
                  p_in_acct_no 
                  p_in_cust_nm 
                  p_in_address 
                  p_in_city   
                  p_in_state   
                  p_in_post_cd  
                  p_in_ph_num       
                  p_in_sort_by      
                  p_in_sort_order   
Output Parameters: o_ser_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015             Inital Version
*******************************************************************************************/
PROCEDURE GET_SRL_INFO (p_in_ser_num      IN     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        p_start           IN       NUMBER,
                  p_end              IN       NUMBER,
                        p_in_sort_by      IN     VARCHAR2,
                        p_in_sort_order   IN     VARCHAR2,
                        x_count              OUT NUMBER,
                        o_ser_tbl            OUT CANON_E307_MAC_SER_TBL)
IS
   l_rec_ser                  CANON_E307_MAC_SER_REC;
   default_where              VARCHAR2 (5000);
   lv_sql                     VARCHAR2 (32000);
   l_default_from               VARCHAR2 (32000);
   l_sql_count_qry          VARCHAR2 (32000);
   lv_ser_num                 VARCHAR2 (50);
   lv_Solution_Name           VARCHAR2 (100);
   lv_Acct_No                 VARCHAR2 (30);
   lv_Customer_name           VARCHAR2 (100);
   lv_Address                 VARCHAR2 (1000);
   lv_City                    VARCHAR2 (100);
   lv_State                   VARCHAR2 (100);
   lv_Postal_Code             VARCHAR2 (100);
   lv_Phone_Num               VARCHAR2 (10);
   lv_mach_mstr_pk            VARCHAR2 (50);
   lv_sales_date              VARCHAR2 (50);
   lv_model1                  VARCHAR2 (100);
   lv_ser_num1                VARCHAR2 (50);
   lv_mach_ctrl_num1          VARCHAR2 (50);
   lv_Solution_Name1          VARCHAR2 (100);
   lv_Acct_No1                VARCHAR2 (30);
   lv_Customer_name1          VARCHAR2 (100);
   lv_address1                VARCHAR2 (5000);
   lv_contact1                VARCHAR2 (100);
   lv_incident_date1          VARCHAR2 (50);
   lv_Customer_Case_No        VARCHAR2 (100);
   lv_Email_Address           VARCHAR2 (100);
   lv_Caller                  VARCHAR2 (100);
   lv_Phone1                  VARCHAR2 (10);
   lv_Phone2                  VARCHAR2 (10);
   lv_Phone3                  VARCHAR2 (10);
   lv_extn                    VARCHAR2 (10);
   lv_Special_Message         VARCHAR2 (300);
   lv_special_msg_typ         VARCHAR2 (100);
   l_order_by                 VARCHAR2 (100);
   l_asc_desc_order           VARCHAR2 (100);
   lv_address2                VARCHAR2 (100);
   lv_city1                   VARCHAR2 (50);
   lv_state1                  VARCHAR2 (50);
   lv_postal_cd1              VARCHAR2 (50);
   lv_owner_acct_no           VARCHAR2 (50);
   lv_bill_to_cust_no         VARCHAR2 (50);
   lv_sell_to_cust_no         VARCHAR2 (50);
   lv_curr_loc_no             VARCHAR2 (50);
   lv_curr_loc_acct_no        VARCHAR2 (50);
   lv_biz_hrs_weekdays        VARCHAR2 (50);
   lv_biz_hrs_frm             VARCHAR2 (50);
   lv_biz_hrs_to              VARCHAR2 (50);
   lv_biz_hrs_sat_frm         VARCHAR2 (50);
   lv_biz_hrs_sat_to          VARCHAR2 (50);
   lv_biz_hrs_sun_frm         VARCHAR2 (50);
   lv_biz_hrs_sun_to          VARCHAR2 (50);
   lv_biz_hrs_sat             VARCHAR2 (50);
   lv_biz_hrs_sun             VARCHAR2 (50);
   lv_last_service_call_dt    VARCHAR2 (50);
   lv_total_svc_visit_count   VARCHAR2 (50);
   lv_last_tech_visit_dt      VARCHAR2 (50);
   lv_pref_tech_cd            VARCHAR2 (50);
   lv_req_tech_cd             VARCHAR2 (50);
   lv_fld_svc_br_cd           VARCHAR2 (50);
   lv_tel_number              VARCHAR2 (50);
  
   TYPE cur_typ IS REF CURSOR;

   v_ser_cursor               cur_typ;
   ln_rec_cnt1                NUMBER := 1;
BEGIN
   o_ser_tbl := CANON_E307_MAC_SER_TBL ();
   lv_ser_num := trim(p_in_ser_num);
   lv_solution_name := trim(p_in_sol_nm);
   lv_acct_no := p_in_acct_no;
   lv_customer_name := replace(p_in_cust_nm,'''','');
   lv_address := p_in_address;
   lv_city := p_in_city;
   lv_state := p_in_state;
   lv_postal_code := p_in_post_cd;
   lv_phone_num := p_in_ph_num;
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;
  
   --Fetch machine details
   l_default_from :=' FROM (SELECT ser_dtls.* FROM CANON_E307_MACH_DTLS_V ser_dtls
                  WHERE 1=1 '
         || ' AND UPPER(nvl(ser_dtls.ADDRESS_1,''X'')) like upper(  '
         || '''%'
         || TRIM (p_in_address)
         || '%'')'
         || ' AND UPPER(nvl( ser_dtls.sell_to_cust_cd,''X'')) like upper(  '
         || '''%'
         || TRIM (p_in_acct_no)
         || '%'')'
         || ' AND (UPPER(nvl(ser_dtls.ser_num,''X'')) like upper(  '
         || '''%'
         || TRIM (p_in_ser_num)
         || '%'') '
         || ' OR UPPER(nvl(ser_dtls.cust_mach_ctrl_num,''X'')) like upper(  '
         || '''%'
         || TRIM (p_in_ser_num)
         || '%'')) '
         || ' AND upper(nvl(ser_dtls.solution_name,''X'')) like  '
         || 'upper(''%'
         || TRIM (p_in_sol_nm)
         || '%'')'
         || ' AND UPPER(REPLACE(nvl(ser_dtls.ship_to_cust_name,''X''),'''''''','''')) like upper(  '
         || '''%'
         || TRIM (lv_customer_name)
         || '%'')'
         || ' AND UPPER(nvl(ser_dtls.CITY,''X'')) like upper(  '
         || '''%'
         || TRIM (p_in_city)
         || '%'')'
         || ' AND UPPER(nvl(ser_dtls.STATE,''X'')) like upper(  '
         || '''%'
         || TRIM (p_in_state)
         || '%'')'
         || ' AND UPPER(nvl(ser_dtls.post_cd,''X'')) like upper(  '
         || '''%'
         || TRIM (p_in_post_cd)
         || '%'')'
         || ' AND REPLACE(REPLACE(nvl(ser_dtls.ctac_psn_tel_num,''X''), ''-'', ''''),'' '','''') like  '
         || 'REPLACE(REPLACE('
         || '''%'
         || p_in_ph_num
         || '%'''
         || ', ''-'', ''''),'' '','''')'
      || '||''%'' ';
      
    /*lv_sql :=
            'SELECT svc_mach_mstr_pk,
                   model,
                   ser_num,
                   cust_mach_ctrl_num,
                   solution_name,                        
                   ship_to_acct_no,
                   ship_to_cust_name,
                   address_1,
                   address_2,
                   city,
                   state,
                   post_cd,
                   Address, 
                   ownr_acct_num,
                   bill_to_cust_cd,
                   sell_to_cust_cd,
                   cur_loc_num,
                   cur_loc_acct_num,
                   biz_hrs_mon_fri_from_tm,
                   biz_hrs_mon_fri_to_tm,
                   biz_hrs_sat_from_tm,
                   biz_hrs_sat_to_tm,
                   biz_hrs_sun_from_tm,
                   biz_hrs_sun_to_tm,
                   LastService,
                   tot_svc_visit_cnt,
                   last_tech_visit_dt,
                   prf_tech_cd,
                   req_tech_cd,
                   fld_svc_br_cd,ctac_psn_eml_addr,
                   ctac_psn_tel_num, ctac_psn_tel_extn_num,'''',
                   ctac_psn_cmnt_txt,'''',ContactName,rownum row_num '
         || l_default_from ;  */
         
    lv_sql :=
            'SELECT dtls.*,rownum row_num '
         || l_default_from ;     

   IF l_order_by IS NOT NULL
   THEN
      lv_sql :=
         lv_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||' )dtls ';
   ELSE
      lv_sql := lv_sql || ' ORDER BY ser_num'||' ) dtls ';
   END IF;
   
      lv_sql := 'SELECT svc_mach_mstr_pk,
                   model,
                   ser_num,
                   cust_mach_ctrl_num,
                   solution_name,                        
                   ship_to_acct_no,
                   ship_to_cust_name,
                   address_1,
                   address_2,
                   city,
                   state,
                   post_cd,
                   Address, 
                   ownr_acct_num,
                   bill_to_cust_cd,
                   sell_to_cust_cd,
                   cur_loc_num,
                   cur_loc_acct_num,
                   biz_hrs_mon_fri_from_tm,
                   biz_hrs_mon_fri_to_tm,
                   biz_hrs_sat_from_tm,
                   biz_hrs_sat_to_tm,
                   biz_hrs_sun_from_tm,
                   biz_hrs_sun_to_tm,
                   LastService,
                   tot_svc_visit_cnt,
                   last_tech_visit_dt,
                   prf_tech_cd,
                   req_tech_cd,
                   fld_svc_br_cd,ctac_psn_eml_addr,
                   ctac_psn_tel_num, ctac_psn_tel_extn_num,'''',
                   ctac_psn_cmnt_txt,'''',ContactName FROM( '
           ||    lv_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
      || p_end;
 --  debug_pkg.debug_proc (lv_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from||')';
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count; 
  -- debug_pkg.debug_proc (l_sql_count_qry);
  -- debug_pkg.debug_proc ('Count2= '||x_count);

   OPEN v_ser_cursor FOR lv_sql;

   LOOP
      FETCH v_ser_cursor
         INTO lv_mach_mstr_pk,
              lv_model1,
              lv_ser_num1,
              lv_mach_ctrl_num1,
              lv_solution_name1,
              lv_acct_no1,
              lv_customer_name1,
              lv_address1,
              lv_address2,
              lv_city1,
              lv_state1,
              lv_postal_cd1,
              lv_address,
              lv_owner_acct_no,
              lv_bill_to_cust_no,
              lv_sell_to_cust_no,
              lv_curr_loc_no,
              lv_curr_loc_acct_no,
              lv_biz_hrs_frm,
              lv_biz_hrs_to,
              lv_biz_hrs_sat_frm,
              lv_biz_hrs_sat_to,
              lv_biz_hrs_sun_frm,
              lv_biz_hrs_sun_to,
              lv_last_service_call_dt,
              lv_total_svc_visit_count,
              lv_last_tech_visit_dt,
              lv_pref_tech_cd,
              lv_req_tech_cd,
              lv_fld_svc_br_cd,
              lv_email_address,
              lv_tel_number,
              lv_extn,
              lv_caller,
              lv_special_message,
              lv_special_msg_typ,
              lv_contact1;

      EXIT WHEN v_ser_cursor%NOTFOUND;

      IF lv_biz_hrs_frm IS NOT NULL AND lv_biz_hrs_to IS NOT NULL
      THEN
         lv_biz_hrs_weekdays :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'MON-FRI:',
               p_time1       => lv_biz_hrs_frm,
               p_time2       => lv_biz_hrs_to,
               p_separator   => '-');
      ELSE
         lv_biz_hrs_weekdays := '';
      END IF;

      IF lv_biz_hrs_sat_frm IS NOT NULL AND lv_biz_hrs_sat_to IS NOT NULL
      THEN
         lv_biz_hrs_sat :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'SAT:',
               p_time1       => lv_biz_hrs_sat_frm,
               p_time2       => lv_biz_hrs_sat_to,
               p_separator   => '-');
      ELSE
         lv_biz_hrs_sat := '';
      END IF;

      IF lv_biz_hrs_sun_frm IS NOT NULL AND lv_biz_hrs_sun_to IS NOT NULL
      THEN
         lv_biz_hrs_sun :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'SAT:',
               p_time1       => lv_biz_hrs_sun_frm,
               p_time2       => lv_biz_hrs_sun_to,
               p_separator   => '-');
      ELSE
         lv_biz_hrs_sun := '';
      END IF;

      l_rec_ser :=
         CANON_E307_MAC_SER_REC (lv_mach_mstr_pk,
                                 lv_model1,
                                 lv_ser_num1,
                                 lv_mach_ctrl_num1,
                                 lv_solution_name1,
                                 lv_acct_no1,
                                 lv_customer_name1,
                                 lv_address1,
                                 lv_address2,
                                 lv_city1,
                                 lv_state1,
                                 lv_postal_cd1,
                                 lv_address,
                                 lv_owner_acct_no,
                                 lv_bill_to_cust_no,
                                 lv_sell_to_cust_no,
                                 lv_curr_loc_no,
                                 lv_curr_loc_acct_no,
                                 lv_biz_hrs_weekdays,
                                 lv_biz_hrs_sat,
                                 lv_biz_hrs_sun,
                                 lv_last_service_call_dt,
                                 lv_total_svc_visit_count,
                                 lv_last_tech_visit_dt,
                                 lv_pref_tech_cd,
                                 lv_req_tech_cd,
                                 lv_fld_svc_br_cd,
                                 lv_email_address,
                                 lv_tel_number,
                                 lv_extn,
                                 lv_Phone1,
                                 lv_Phone2,
                                 lv_Phone3,
                                 lv_caller,
                                 lv_special_message,
                                 lv_special_msg_typ,
                                 lv_contact1);

      o_ser_tbl.EXTEND ();
      o_ser_tbl (ln_rec_cnt1) := l_rec_ser;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
      --debug_pkg.debug_proc ('After End Loop');
   END LOOP;

   CLOSE v_ser_cursor;
EXCEPTION
   WHEN OTHERS
   THEN
       -- debug_pkg.debug_proc ('Inside Exception ' || SQLERRM);
      NULL;
END GET_SRL_INFO;

/*******************************************************************************************
 Procedure Name: GET MACHINE COUNT
 Description: Get debrief details of SR to be displayed in ASCC
 Input Parameters: p_in_sol_nm
            p_in_acct_no
            p_in_cust_nm
            p_in_address
            p_in_city
            p_in_state
            p_in_post_cd
            p_in_ph_num
            
 Output Parameters: x_count 
             x_mach_mstr_pk
             x_model
             
InOut Parameter   : p_io_ser_num             
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_MACHINE_COUNT
                 (p_io_ser_num     IN OUT     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        x_count           OUT    NUMBER,
                        x_mach_mstr_pk      OUT     VARCHAR2,
                        x_model          OUT     VARCHAR2
         )
IS         
lv_count NUMBER:=0;
l_default_from     VARCHAR2 (32000);
lv_sql   VARCHAR2 (32000);
l_sql_count_qry    VARCHAR2 (32000);
lv_Customer_name          VARCHAR2 (500);
lv_serial VARCHAR2 (100);
lv_acct_num VARCHAR2 (100);
lv_sol_nm  VARCHAR2 (100);
lv_city  VARCHAR2 (100);
lv_state  VARCHAR2 (100);
lv_post_cd  VARCHAR2 (100);
BEGIN
lv_customer_name := replace(trim(p_in_cust_nm),'''','');
lv_serial :=trim(p_io_ser_num);
lv_acct_num :=trim(p_in_acct_no);
lv_sol_nm :=trim(p_in_sol_nm);
lv_city :=trim(p_in_city);
lv_state :=trim(p_in_state);
lv_post_cd :=trim(p_in_post_cd);
--debug_pkg.debug_proc ('p_in_cust_nm=  '||p_in_cust_nm);
--debug_pkg.debug_proc ('p_in_address=  '||p_in_address);
l_default_from :=
             'FROM (SELECT  distinct ser_dtls.ser_num,ser_dtls.SVC_MACH_MSTR_PK, ser_dtls.MODEL
                  FROM CANON_E307_MACH_DTLS_V ser_dtls
                  WHERE 1=1
                 AND UPPER(nvl(ser_dtls.ADDRESS_1,''X'')) like upper(  '
                                     || '''%''||  TRIM( '
                                     || ''''|| p_in_address||''''
                       || ') || ''%'') '
                  || ' AND UPPER(nvl( ser_dtls.sell_to_cust_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_acct_num
                       || '%'')'
                       || ' AND (upper(nvl(ser_dtls.ser_num,''X'')) like  '
                       || 'upper(''%'
                       || lv_serial
                       || '%'')'
                       || ' OR upper(nvl(ser_dtls.cust_mach_ctrl_num,''X'')) like  '
                       || 'upper(''%'
                       || lv_serial
                       || '%''))'
                       || ' AND upper(nvl(ser_dtls.solution_name,''X'')) like  '
                       || 'upper(''%'
                       || lv_sol_nm
                       || '%'')'
                       || ' AND UPPER(REPLACE(nvl(ser_dtls.ship_to_cust_name,''X''),'''''''','''')) like upper(  '
                       || '''%'
                       || lv_customer_name
                       || '%'')'
                       || ' AND UPPER(nvl(ser_dtls.CITY,''X'')) like upper(  '
                       || '''%'
                       || lv_city
                       || '%'')'
                       || ' AND UPPER(nvl(ser_dtls.STATE,''X'')) like upper(  '
                       || '''%'
                       || lv_state
                       || '%'')'
                       || ' AND UPPER(nvl(ser_dtls.post_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_post_cd
                       || '%'')'
                       || ' AND REPLACE(REPLACE(nvl(ser_dtls.ctac_psn_tel_num,''X''), ''-'', ''''),'' '','''') like  '
                            || 'REPLACE(REPLACE('
                            || '''%'
                            || p_in_ph_num
                            || '%'''
                            || ', ''-'', ''''),'' '','''')'
      || '||''%'') ';
                  
--debug_pkg.debug_proc ('l_default_from '||l_default_from);
l_sql_count_qry := ' select count(*) ' || l_default_from;

--debug_pkg.debug_proc ('l_sql_count_qry '||l_sql_count_qry);

EXECUTE IMMEDIATE l_sql_count_qry INTO lv_count; 
  
x_count:=lv_count;

--debug_pkg.debug_proc ('lv_count= '||lv_count);
--debug_pkg.debug_proc ('x_count= '||x_count);

IF lv_count =1 THEN
    lv_sql :=
                ' SELECT ser_num, svc_mach_mstr_pk, model '
         || l_default_from;
--   debug_pkg.debug_proc ('lv_sql= '||lv_sql);      

EXECUTE IMMEDIATE lv_sql INTO p_io_ser_num,x_mach_mstr_pk,x_model ;         
END IF;

EXCEPTION
    WHEN OTHERS THEN
       NULL;
 End GET_MACHINE_COUNT;         

/*******************************************************************************************
 Procedure Name: GET_UGW_DETAILS
 Description: To fetch all the active Serial numbers from IB which matches the partial   
           Serial/EID entered by user from ASCC screen
 Input Parameters: None
 Output Parameters: pc_ascc_cur_out  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  03-Jun-2015              Inital Version
*******************************************************************************************/   
PROCEDURE GET_UGW_DETAILS ( p_cust_in IN VARCHAR2
                ,p_in_ser_eid IN VARCHAR2
                ,p_model_in  IN VARCHAR2
                ,p_branch_in IN VARCHAR2
                    ,p_start     IN       NUMBER
                      ,p_end        IN       NUMBER
                ,p_in_sort_by      IN     VARCHAR2
                            ,p_in_sort_order   IN     VARCHAR2
                            ,x_count              OUT NUMBER
                            ,o_ugw_tbl            OUT CANON_E307_UGW_TBL)
       IS
       l_rec_ugw                  CANON_E307_UGW_REC;
       v_ugw_cursor      cur_typ;
       l_order_by         VARCHAR2 (100);
       l_asc_desc_order   VARCHAR2 (100);
       v_sql              VARCHAR2 (32000);
       l_default_from     VARCHAR2 (32000);
       l_sql_count_qry    VARCHAR2 (32000);
       lv_ser_num      VARCHAR2 (100);
       lv_tag_num      VARCHAR2 (100);
       lv_inst_id       VARCHAR2 (100);
       lv_cust_nm      VARCHAR2 (1000);
       lv_err_cd      VARCHAR2 (100);
       lv_err_dt     VARCHAR2 (30);
       lv_model         VARCHAR2 (500);
       lv_branch     VARCHAR2 (500);
       lv_branch_in    VARCHAR2 (500);
       lv_cust_in    VARCHAR2 (1000);
       lv_model_in    VARCHAR2 (500);
       lv_ser_eid_in    VARCHAR2 (100);
      /* CURSOR cur_ugw
       IS
         SELECT DISTINCT serial_number,
                         tag_number,
                         party_name,
                         ERROR_CODE,
                         TO_CHAR (error_date, 'DD-Mon-YYYY HH24:MI:SS') error_date,
                         model,
                         BRANCH || '-' || branch_code_desc branch
           FROM canon_e307_smart_disp_dtls_vl t1
          WHERE     1 = 1
                AND UPPER (BRANCH || '-' || branch_code_desc ) LIKE
                       UPPER ('%' || TRIM (p_branch_in) || '%')
                AND UPPER(nvl(UPPER(party_name),'X')) LIKE UPPER ('%'||TRIM (p_cust_in)||'%')
                AND UPPER (NVL (model, 'X')) LIKE
                       UPPER ('%' || TRIM (p_model_in) || '%')
                AND (   UPPER (NVL (serial_number, 'X')) LIKE
                           UPPER ('%' || TRIM (p_in_ser_eid) || '%')
                     OR UPPER (NVL (tag_number, 'X')) LIKE
                           UPPER ('%' || TRIM (p_in_ser_eid) || '%'))
                AND complete_flag = 'N'
                AND open_call IS NULL
                AND error_date IN
                       (SELECT MAX (error_date)
                          FROM canon_e307_smart_disp_dtls_vl
                         WHERE     serial_number = t1.serial_number
                               AND complete_flag = 'N'
                               AND open_call IS NULL)
    ORDER BY ERROR_DATE ASC, SERIAL_NUMBER ASC;*/
     ln_rec_cnt        NUMBER := 1;     
   BEGIN
   o_ugw_tbl := CANON_E307_UGW_TBL ();
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;
   --Handle Java/jsp null value
   BEGIN
   
   SELECT decode(p_branch_in,'null','',p_branch_in),
         decode(p_cust_in,'null','',p_cust_in),
         decode(p_model_in,'null','',p_model_in),
         decode(p_in_ser_eid,'null','',p_in_ser_eid)
   INTO lv_branch_in,lv_cust_in,lv_model_in,lv_ser_eid_in
   FROM dual;
   
   EXCEPTION WHEN OTHERS THEN
   lv_branch_in:=NULL;
   lv_cust_in :=NULL;
   lv_model_in :=NULL;
   lv_ser_eid_in :=NULL;
   
   END;
   
   l_default_from :=
            ' FROM (SELECT DISTINCT serial_number, tag_number,instance_id,party_name, 
            ERROR_CODE, TO_CHAR (error_date, ''DD-Mon-YYYY HH24:MI:SS'') error_date, 
            model, BRANCH || ''-'' || branch_code_desc branch 
            FROM canon_e307_smart_disp_dtls_vl t1
             WHERE     1 = 1 
              AND (UPPER (BRANCH || ''-'' || branch_code_desc ) LIKE 
                    UPPER (''%'' || TRIM ('''||lv_branch_in||''') || ''%'')
                    OR UPPER (branch) like UPPER (''%'' || TRIM ('''||lv_branch_in||''') || ''%'')
                    OR UPPER (branch_code_desc) like UPPER (''%'' || TRIM ('''||lv_branch_in||''') || ''%''))
                AND UPPER(nvl(UPPER(party_name),''X'')) LIKE UPPER (''%''||TRIM ('''||lv_cust_in||''')||''%'')
                AND UPPER (NVL (model, ''X'')) LIKE
                       UPPER (''%'' || TRIM ('''||lv_model_in||''') || ''%'')
                AND (   UPPER (NVL (serial_number, ''X'')) LIKE
                           UPPER (''%'' || TRIM ('''||lv_ser_eid_in||''') || ''%'')
                     OR UPPER (NVL (tag_number, ''X'')) LIKE
                           UPPER (''%'' || TRIM ('''||lv_ser_eid_in||''') || ''%''))
                AND complete_flag = ''N''
                AND open_call IS NULL
                AND error_date IN
                       (SELECT MAX (error_date)
                          FROM canon_e307_smart_disp_dtls_vl
                         WHERE     serial_number = t1.serial_number
                               AND complete_flag = ''N''
                               AND open_call IS NULL) ';
   
      v_sql :=
            'SELECT ugw.*,rownum row_num '
         || l_default_from;
   
      IF l_order_by IS NOT NULL
      THEN
         v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||')ugw';
      ELSE
         v_sql := v_sql || ' ORDER BY ERROR_DATE ASC )ugw ';
      END IF;
      
      v_sql := 'SELECT serial_number,tag_number,instance_id,party_name, ERROR_CODE,error_date, 
            model,branch  FROM( '
           ||    v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
         || p_end;
     -- debug_pkg.debug_proc ('v_sql '||v_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from||')';
  -- debug_pkg.debug_proc ('l_sql_count_qry '||l_sql_count_qry);
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
   
   OPEN v_ugw_cursor FOR v_sql;
   
      LOOP
         FETCH v_ugw_cursor
            INTO lv_ser_num, lv_tag_num,lv_inst_id,lv_cust_nm,lv_err_cd,lv_err_dt,lv_model,lv_branch;
   
         EXIT WHEN v_ugw_cursor%NOTFOUND;
         l_rec_ugw :=
            CANON_E307_UGW_REC (lv_ser_num, lv_tag_num,lv_inst_id,lv_cust_nm,lv_err_cd,lv_err_dt,lv_model,lv_branch);
         o_ugw_tbl.EXTEND ();
         o_ugw_tbl (ln_rec_cnt) := l_rec_ugw;
         ln_rec_cnt := ln_rec_cnt + 1;
        -- debug_pkg.debug_proc ('lv_ser_num ='||lv_ser_num);
   END LOOP;
       
   EXCEPTION
            WHEN OTHERS
            THEN
               NULL;
   END GET_UGW_DETAILS;
   
/*******************************************************************************************
 Procedure Name: CHECK_UGW_LOCKOUT
 Description: Check if the UGW record is locked by another user
 Input Parameters: p_srl_in
 Output Parameters: p_exist_flg_out  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  03-Jun-2015              Inital Version
*******************************************************************************************/    
PROCEDURE CHECK_UGW_LOCKOUT (
      p_srl_in          IN       VARCHAR2,
      p_usr_id_in       IN       VARCHAR2,
      p_locked_by_out   OUT      VARCHAR2
   )
   IS
      v_ugw_lockout_window   NUMBER := NULL;
      v_locked_by            NUMBER := NULL;
   BEGIN
      p_locked_by_out := 'N';
-- Get value for UGW Lockout Window
      BEGIN
      --Change needed to fetch the lockout value from DB 
         v_ugw_lockout_window:=30;
      EXCEPTION
         WHEN OTHERS
         THEN
            v_ugw_lockout_window := 30;
      END;

      -- check if record is worked upon by any user
      BEGIN
         SELECT last_locked_by
           INTO v_locked_by
           FROM canon_e307_smart_disp_dtls_tbl
          WHERE 1 = 1
            AND serial_number LIKE UPPER (TRIM (p_srl_in))
            AND (complete_flag = 'N' AND open_call IS NULL)
            AND TO_DATE (DECODE (last_locked_date,
                                 NULL, TO_CHAR (SYSDATE - 1,
                                                'DD-Mon-YYYY HH24:MI:SS'
                                               ),
                                 TO_CHAR (  TO_DATE (last_locked_date,
                                                     'DD-Mon-YYYY HH24:MI:SS'
                                                    )
                                          + v_ugw_lockout_window / (24 * 60),
                                          'DD-Mon-YYYY HH24:MI:SS'
                                         )
                                ),
                         'DD-Mon-YYYY HH24:MI:SS'
                        ) >= SYSDATE
            AND ROWNUM = 1;
      EXCEPTION
         WHEN OTHERS
         THEN
            v_locked_by := NULL;
      END;

      -- If yes Then
      IF v_locked_by IS NOT NULL
      THEN
         -- check if record is worked upon by passed in user
         IF (v_locked_by = TO_NUMBER (p_usr_id_in))
         THEN
            -- If yes Then
              -- Assign NULL value to p_locked_by_out
            p_locked_by_out := 'N';
         -- Else
         ELSE
            -- Get the user who is working on the record
            -- Assign above resource name to p_locked_by_out
            BEGIN
               SELECT canon_e307_call_support_pkg.get_psn_nm(v_locked_by)
                 INTO p_locked_by_out
                FROM dual;
            EXCEPTION
               WHEN OTHERS
               THEN
                  p_locked_by_out := 'N';
            END;
         END IF;
      ELSE
         -- Assign NULL value to x_locked_by_res_name
         UPDATE canon_e307_smart_disp_dtls_tbl
            SET last_locked_by = p_usr_id_in,
                last_locked_date = TO_CHAR (SYSDATE, 'DD-Mon-YYYY HH24:MI:SS')
          WHERE 1 = 1
            AND serial_number LIKE UPPER (TRIM (p_srl_in))
            AND (complete_flag = 'N' AND open_call IS NULL);

         p_locked_by_out := 'N';
         COMMIT;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_locked_by_out := 'N';
   END CHECK_UGW_LOCKOUT;   
   
/*******************************************************************************************
Procedure Name: BRANCH_CODE_LOV
Description: Get branch Code LOV details to be displayed in ASCC
Input Parameters: None
   
Output Parameters: o_branch_code_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE BRANCH_CODE_LOV (o_branch_code_tbl OUT CANON_E307_BRANCH_CODE_TBL)
IS
   l_rec_branch_code   CANON_E307_BRANCH_CODE_REC;

   CURSOR cur_branch_code
   IS
      SELECT distinct svc_contr_br_cd code,
             svc_contr_br_cd || '-' || svc_contr_br_desc_txt description
        FROM svc_contr_br branch
       WHERE     branch.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND branch.ezcancelflag = g_cancel_flg;

   ln_rec_cnt_branch   NUMBER := 1;
BEGIN
   o_branch_code_tbl := CANON_E307_BRANCH_CODE_TBL ();

   FOR fr_cur_branch_code IN cur_branch_code
   LOOP
      l_rec_branch_code :=
         CANON_E307_BRANCH_CODE_REC (fr_cur_branch_code.code,
                                     fr_cur_branch_code.description);
      o_branch_code_tbl.EXTEND ();
      o_branch_code_tbl (ln_rec_cnt_branch) := l_rec_branch_code;
      ln_rec_cnt_branch := ln_rec_cnt_branch + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END BRANCH_CODE_LOV;
/*******************************************************************************************
 Procedure Name: GET_FUTURE_SR_DTS
 Description: To fetch future Service Requests
 Input Parameters: None
 Output Parameters: pc_ascc_cur_out  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/   
PROCEDURE GET_FUTURE_SR_DTLS (
   p_in_Serial       IN     VARCHAR2,
   p_in_date         IN     VARCHAR2,
   p_in_branch         IN     VARCHAR2,
   p_in_cust_nm      IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_sr_tbl             OUT CANON_E307_SR_DETL_TBL)
IS
   l_rec_sr           CANON_E307_SR_DETL_REC;
   v_sr_cursor        cur_typ;
   l_order_by         VARCHAR2 (100);
   l_asc_desc_order   VARCHAR2 (100);
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   lv_ser_num         VARCHAR2 (100);
   lv_tag_num         VARCHAR2 (100);
   lv_cust_nm         VARCHAR2 (1000);
   lv_SR_NUM          VARCHAR2 (30);
   lv_task_num        VARCHAR2 (30);
   lv_FUTURE_DATE     VARCHAR2 (30);
   lv_task_TYPE       VARCHAR2 (100);
   lv_ASSIGNEE        VARCHAR2 (100);
   lv_model           VARCHAR2 (500);
   lv_branch          VARCHAR2 (500);

   ln_rec_cnt         NUMBER := 1;
BEGIN
--debug_pkg.debug_proc ('p_in_date= '||p_in_date);
   o_sr_tbl := CANON_E307_SR_DETL_TBL ();
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;

    l_default_from :=
            ' FROM (SELECT * 
                    FROM canon_e307_future_calls_v calls
                    WHERE 1=1
             AND UPPER(nvl(calls.ser_num,''X'')) LIKE upper(''%''||TRIM ('''
               || p_in_Serial
         || ''')||''%'')
         AND UPPER(nvl(calls.branch,''X'')) LIKE upper(''%''||TRIM ('''
                    || p_in_branch
         || ''')||''%'')
         AND UPPER(nvl(calls.cust_nm,''X'')) LIKE upper(''%''||TRIM ('''
                    || p_in_cust_nm
         || ''')||''%'')
         AND UPPER(nvl(canon_e307_utils.format_date(calls.fut_svc_dt,''FORMAT3''),''X''))LIKE  upper(''%''||TRIM ('''
               || p_in_date
         || ''')||''%'')';
   

      v_sql :=
            'SELECT dtls.*,rownum row_num '
         || l_default_from;
   
      IF l_order_by IS NOT NULL
      THEN
         v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||') dtls';
      ELSE
         v_sql := v_sql || ' ORDER BY task_type ASC )dtls';
      END IF;
   
/*   IF p_start IS NOT NULL AND p_end IS NOT NULL
   THEN
         v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,Future_dt,task_type,assignee_name,model,Branch FROM( '
              ||    v_sql
               || ' )  WHERE row_num BETWEEN '
               || p_start
               || ' AND '
            || p_end;
  ELSE
   v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,Future_dt,task_type,assignee_name,model,Branch FROM( '
              ||    v_sql
               || ' ) ';         
         
   END IF;*/
   
   v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,Future_dt,task_type,assignee_name,model,Branch FROM( '
                 ||    v_sql
               || ' ) '; 
  --debug_pkg.debug_proc ('v_sql '||v_sql);
      l_sql_count_qry := ' select count(*) ' || l_default_from||')';
   
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_sr_cursor FOR v_sql;

   LOOP
      FETCH v_sr_cursor
         INTO lv_ser_num,
              lv_tag_num,
              lv_cust_nm,
              lv_sr_num,
              lv_task_num,
              lv_future_date,
              lv_task_type,
              lv_assignee,
              lv_model,
              lv_branch;

      EXIT WHEN v_sr_cursor%NOTFOUND;
      l_rec_sr :=
         CANON_E307_SR_DETL_REC (lv_ser_num,
                                 lv_tag_num,
                                 lv_cust_nm,
                                 lv_sr_num,
                                 lv_task_num,
                                 lv_future_date,
                                 lv_task_type,
                                 lv_assignee,
                                 lv_model,
                                 lv_branch,
                                 '');
      o_sr_tbl.EXTEND ();
      o_sr_tbl (ln_rec_cnt) := l_rec_sr;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_FUTURE_SR_DTLS;
   
/*******************************************************************************************
 Procedure Name: GET_VENDOR_SR_DTLS
 Description: To fetch 3rd party Service Requests
 Input Parameters: None
 Output Parameters: pc_ascc_cur_out  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/   
PROCEDURE GET_VENDOR_SR_DTLS (
   p_in_Serial       IN     VARCHAR2,
   p_in_date         IN     VARCHAR2,
   p_in_cust         IN     VARCHAR2,
   p_in_sts         IN     VARCHAR2,
   p_in_assignee     IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_sr_tbl             OUT CANON_E307_SR_DETL_TBL)
IS
   l_rec_sr           CANON_E307_SR_DETL_REC;
   v_sr_cursor        cur_typ;
   l_order_by         VARCHAR2 (100);
   l_asc_desc_order   VARCHAR2 (100);
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   lv_ser_num         VARCHAR2 (100);
   lv_tag_num         VARCHAR2 (100);
   lv_cust_nm         VARCHAR2 (1000);
   lv_SR_NUM          VARCHAR2 (30);
   lv_task_num        VARCHAR2 (30);
   lv_creation_dt     VARCHAR2 (30);
   lv_task_TYPE       VARCHAR2 (100);
   lv_ASSIGNEE        VARCHAR2 (100);
   lv_model           VARCHAR2 (500);
   lv_branch          VARCHAR2 (500);
   lv_status          VARCHAR2 (100);
   ln_rec_cnt         NUMBER := 1;
BEGIN
   o_sr_tbl := CANON_E307_SR_DETL_TBL ();
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;

   l_default_from :=
         ' FROM (SELECT    *
              FROM CANON_E307_VENDOR_CALLS_V calls
              WHERE 1=1
     AND upper(calls.status) IN (SELECT upper(SETUP_VALUE)
                         FROM CANON_E307_SETUP_TBL
                         WHERE SETUP_NAME=''VENDOR_CALL_STATUS'')
     AND UPPER(nvl(calls.ser_num,''X'')) LIKE upper(''%''||TRIM ('''
                  || p_in_Serial
      || ''')||''%'')
      AND UPPER(nvl(calls.cust_nm,''X'')) LIKE upper(''%''||TRIM ('''
                        || p_in_cust
      || ''')||''%'')
      AND UPPER(nvl(calls.status,''X'')) LIKE upper(''%''||TRIM ('''
                              || p_in_sts
      || ''')||''%'')
      AND UPPER(nvl(calls.assignee_name,''X'')) LIKE upper(''%''||TRIM ('''
                                    || p_in_assignee
      || ''')||''%'')
      AND UPPER(nvl(canon_e307_utils.format_date(calls.ezintime,''FORMAT3''),''X''))LIKE  upper(''%''||TRIM ('''
            || p_in_date
      || ''')||''%'')';
      -- || '  AND fsr.itt_num is not null --As per S21 team the vendor calls tech cd <>'1' to be checked
     -- || ') ';

   /* v_sql :=
          'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status '
       || l_default_from
       || '  WHERE row_num BETWEEN '
       || p_start
       || ' AND '
       || p_end;*/
   v_sql :=
         'SELECT dtls.*,rownum row_num '
      || l_default_from;

   IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||' )dtls ';
   ELSE
      v_sql := v_sql || ' ORDER BY SER_NUM ASC ) dtls';
   END IF;
   
 /*  IF p_start IS NOT NULL AND p_end IS NOT NULL
   THEN
         v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status FROM( '
              ||    v_sql
               || ' )  WHERE row_num BETWEEN '
               || p_start
               || ' AND '
            || p_end;
   ELSE
   v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status FROM( '
              ||    v_sql
               || ' ) ';        
         
   END IF;*/
    v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status FROM( '
              ||    v_sql
               || ' ) ';   
  -- debug_pkg.debug_proc ('v_sql ' || v_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from||' ) ';

   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_sr_cursor FOR v_sql;

   LOOP
      FETCH v_sr_cursor
         INTO lv_ser_num,
              lv_tag_num,
              lv_cust_nm,
              lv_sr_num,
              lv_task_num,
              lv_creation_dt,
              lv_task_type,
              lv_assignee,
              lv_model,
              lv_branch,
              lv_status;

      EXIT WHEN v_sr_cursor%NOTFOUND;
      l_rec_sr :=
         CANON_E307_SR_DETL_REC (lv_ser_num,
                                 lv_tag_num,
                                 lv_cust_nm,
                                 lv_sr_num,
                                 lv_task_num,
                                 lv_creation_dt,
                                 lv_task_type,
                                 lv_assignee,
                                 lv_model,
                                 lv_branch,
                                 lv_status);
      o_sr_tbl.EXTEND ();
      o_sr_tbl (ln_rec_cnt) := l_rec_sr;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_VENDOR_SR_DTLS;
   
/*******************************************************************************************
 Function Name: GET_SR_COUNT
 Description: Get the count of SR numbers entered by user
 Input Parameters: p_in_usr_cd

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_SR_COUNT (p_in_sr_num IN VARCHAR2, 
               p_in_tsk_num IN VARCHAR2)
   RETURN NUMBER
IS
   lv_count   NUMBER := 0;
BEGIN
   IF p_in_sr_num IS NOT NULL
   THEN
      SELECT COUNT (fsr_num)
        INTO lv_count
        FROM fsr
       WHERE     fsr.fsr_num LIKE '%' || TRIM (p_in_sr_num) || '%'
             AND glbl_cmpy_cd = g_glbl_cmpy_cd;
   ELSIF p_in_tsk_num IS NOT NULL
   THEN
      SELECT COUNT (DISTINCT fsr_num)
        INTO lv_count
        FROM svc_task task
       WHERE     task.svc_task_num LIKE '%' || TRIM (p_in_tsk_num) || '%'
             AND glbl_cmpy_cd = g_glbl_cmpy_cd;
   END IF;

   RETURN lv_count;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_SR_COUNT;   
END CANON_E307_DSPTCH_PKG;
/

CREATE OR REPLACE PACKAGE CANON_E307_CREATE_SR_PKG
AS
/*******************************************************************************************
   Package Name: CANON_E307_CREATE_SR_PKG_SPEC
   Description:  Package to be used by Canon Smart Dispatch Application
   Dependencies: Canon Smart Dispatch Application JSP pages
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   Hema Doniparthi	   2.0					14-Mar-2016				 Modified for other changes
*****************************************************************************************/
g_glbl_cmpy_cd VARCHAR2(10):=canon_e307_constants.g_global_company_code;
g_cancel_flg VARCHAR2(10):=canon_e307_constants.g_cancel_flg;
g_ascc_source_name  VARCHAR2(30)    :=  'ASCC';
g_ascc_merchant_id  VARCHAR2(30)    :=  '066029';
TYPE cur_typ IS REF CURSOR;
PROCEDURE LOOKUP_BY_XML_MESSAGE (p_xml_message   IN     VARCHAR2,
                                 o_xml_string       OUT VARCHAR2);
PROCEDURE CHECK_CALL_TYPE
  (p_in_serial IN  VARCHAR2
    ,p_in_mdl_id  IN  VARCHAR2
    , p_call_type_id IN  VARCHAR2
    , p_call_type    IN  VARCHAR2
    , x_call_type    OUT VARCHAR2
    , x_call_type_id OUT VARCHAR2
  );                                 
PROCEDURE GLOBAL_LEVEL_RECALL (
      p_in_serial     IN       VARCHAR2,
      p_in_model      IN       VARCHAR2,
      x_call_type      OUT      VARCHAR2,
      x_call_type_id   OUT      VARCHAR2
   );
PROCEDURE GET_EQUIP_BRANCH (p_in_serial   IN     VARCHAR2,
                            o_br_cd          OUT VARCHAR2,
                            o_br_desc        OUT VARCHAR2);   
PROCEDURE PROBLEM_CODE_LOV (p_in_attr          IN     VARCHAR2,
                            p_in_model         IN     VARCHAR2,
                            p_in_type        IN     VARCHAR2,
                            p_in_desc        IN     VARCHAR2,
                            o_prob_code_tbl      OUT CANON_E307_LOV_TBL);   
FUNCTION GET_PSN_NM (p_in_usr_cd IN VARCHAR2)
   RETURN VARCHAR2;                            
PROCEDURE GET_CALL_DETAILS (
   p_in_serial                  VARCHAR2,
   p_in_model                   VARCHAR2,
   o_resp_time              OUT VARCHAR2,
   o_vip_flag               OUT VARCHAR2,
   o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
   o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
   o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
   o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
   o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
   o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
   o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
   o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL,
   o_mdl_dur            OUT VARCHAR2);
PROCEDURE CUST_NAME_LOV (
   p_in_cust_name    IN     VARCHAR2,
   p_in_acct_num     IN     VARCHAR2,    
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_cust_name_tbl      OUT CANON_E307_CUST_NAME_LOV_TBL);   
PROCEDURE CUST_ADDR_LOV (p_in_type        IN     VARCHAR2,
             p_in_cust_code    IN     VARCHAR2,
             p_in_address       IN     VARCHAR2,
                         p_start           IN     NUMBER,
                         p_end             IN     NUMBER,
                         p_in_sort_by      IN     VARCHAR2,
                         p_in_sort_order   IN     VARCHAR2,
                         x_count              OUT NUMBER,
                         o_cust_addr_tbl      OUT CANON_E307_CUST_LOC_TBL);    
PROCEDURE SR_CHANNEL_LOV (o_sr_channel_tbl OUT CANON_E307_SR_CHANNEL_TBL);  
PROCEDURE REMEDY_DETAILS (p_in_model         IN     VARCHAR2,
                          p_in_pblm_code     IN     VARCHAR2,
              o_remedy_tbl OUT CANON_E307_REMEDY_TBL);
PROCEDURE GET_OPEN_SR_NUM (p_in_serial         IN     VARCHAR2,
                          o_sr_num      OUT    VARCHAR2);    
PROCEDURE GET_CALL_AVOIDANCE (o_call_avoid_tbl OUT CANON_E307_CALL_AVOID_TBL);
PROCEDURE GET_CALL_SUMMARY (
   p_in_sr_num              IN     VARCHAR2,
   p_in_task_num        IN     VARCHAR2,
   o_mach_tbl                  OUT CANON_E307_MAC_SER_TBL,
   o_sr_info_tbl               OUT CANON_E307_SR_INFO_TBL,
   o_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL,
   o_task_info_tbl             OUT CANON_E307_TASK_INFO_TBL,
   o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL);
      
PROCEDURE RESOURCE_LOV (
   p_in_name    IN     VARCHAR2,
   p_in_lov_name     IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_assignee_tbl      OUT CANON_E307_RES_LOV_TBL);  
PROCEDURE COPY_FSR (
   p_in_sr_num                  VARCHAR2,
   o_resp_time              OUT VARCHAR2,
   o_vip_flag               OUT VARCHAR2,
   o_mdl_dur            OUT VARCHAR2,
   o_sr_owner            OUT VARCHAR2,
   o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
   o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
   o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
   o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
   o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
   o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
   o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
   o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL);   
   
/*PROCEDURE GET_NOTE_DETAILS (  p_in_sr_num  VARCHAR2,  
                  o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL);   */
/*PROCEDURE GET_MACH_HIST (
   p_in_serial       IN            VARCHAR2,
   p_in_tag         IN VARCHAR2,
   p_in_sol     IN    VARCHAR2,
   p_in_model     IN    VARCHAR2,
   p_in_acct_num  IN    VARCHAR2,
   p_in_cust_name  IN    VARCHAR2,
   o_mach_hist_tbl     OUT  CANON_E307_MACH_HIST_TBL);*/
PROCEDURE GET_SR_HIST (  p_in_sr_num       IN     VARCHAR2,
             p_in_task_num       IN     VARCHAR2,
             p_in_serial       IN     VARCHAR2,
                         p_in_tag          IN     VARCHAR2,
                         p_in_sol          IN     VARCHAR2,
                         p_in_model        IN     VARCHAR2,
                         p_in_acct_num     IN     VARCHAR2,
                         p_in_cust_name    IN     VARCHAR2,
                         p_start           IN     NUMBER,
             p_end             IN     NUMBER,
             p_in_sort_by      IN     VARCHAR2,
                p_in_sort_order   IN     VARCHAR2,
                x_count              OUT NUMBER,
                         o_sr_hist_tbl      OUT CANON_E307_SR_HIST_TBL); 
PROCEDURE GET_TASK_HIST (p_in_sr_num       IN     VARCHAR2,
                         p_start           IN     NUMBER,
             p_end             IN     NUMBER,
             p_in_sort_by      IN     VARCHAR2,
                p_in_sort_order   IN     VARCHAR2,
                x_count              OUT NUMBER,
                         o_tsk_hist_tbl      OUT CANON_E307_TSK_HIST_TBL);
PROCEDURE SR_HIST_LOV (
   p_in_attr   IN     VARCHAR2,
   p_in_val    IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
 --  p_in_sort_by      IN     VARCHAR2,
   --p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL);
PROCEDURE TYPE_LOV (
   x_type_tbl      OUT CANON_E307_TYPE_LOV_TBL);
FUNCTION INSTALL_CALL_CHECK (p_in_tsk_num   IN VARCHAR2)
   RETURN VARCHAR2;   
PROCEDURE INSTALL_DTLS (p_in_serial         IN     VARCHAR2
                        ,p_in_task_num      IN    VARCHAR2
                        ,o_install_tbl      OUT     CANON_E307_INSTALL_TBL);   
PROCEDURE GET_TASK_STATUS (
   p_in_status     IN       VARCHAR2, 
   p_in_user_id    IN       VARCHAR2, 
   x_type_tbl      OUT      CANON_E307_TYPE_LOV_TBL);   
  PROCEDURE GET_CREDIT_CUST_INFO(
    P_SERIAL_NUM                  IN       VARCHAR2,  
    P_MERCHANT_ID                 OUT      VARCHAR2,
    P_CUST_EMAIL                  OUT      VARCHAR2,
    P_CUST_PHONE                  OUT      VARCHAR2,
    P_EXTN_NUM                    OUT      VARCHAR2,
    P_CONTACT_NAME                OUT      VARCHAR2,
    p_credit_cust_cur             OUT       cur_typ
  ); 
 FUNCTION GET_BILLABLE_FLAG (p_in_fsr IN VARCHAR2)
   RETURN NUMBER;                          
END CANON_E307_CREATE_SR_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_CREATE_SR_PKG
IS
/*******************************************************************************************
Package Name: CANON_E307_CREATE_SR_PKG_BODY
Description:  Package to be used by Canon Smart Dispatch Application
Dependencies: Canon Smart Dispatch Application JSP pages
Action History:
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
Hema Doniparthi		2.0                  14-Mar-2016			   Modified for other changes
*******************************************************************************************/
/*******************************************************************************************
 Procedure Name: GET_CONTRACT_INFO
 Description: Get contract details
 Input Parameters: p_in_serial
            
 Output Parameters: x_ds_contr_pk
             x_ds_contr_dtl_pk
             x_contract_details_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_CONTRACT_INFO (
   p_in_serial              IN     VARCHAR2,
   x_ds_contr_pk               OUT VARCHAR2,
   x_ds_contr_dtl_pk           OUT VARCHAR2,
   x_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL)
IS
   l_rec_contract_details   CANON_E307_CONTRACT_REC;
   lv_ds_contr_pk           VARCHAR2 (100);
   lv_ds_contr_dtl_pk       VARCHAR2 (100);
   l_weekday_hours          VARCHAR2 (100);
   l_sat_hours              VARCHAR2 (100);
   l_sun_hours              VARCHAR2 (100);
   ln_contract_rec_cnt      NUMBER := 1;
   l_cust_cd            VARCHAR2 (100);
   l_cust_name              VARCHAR2 (100);
   l_header_eff_string      VARCHAR2 (100);
   l_line_eff_string        VARCHAR2 (100);
   l_sla_converted          VARCHAR2 (50);
   l_resp_tm                VARCHAR2(10);

   CURSOR cur_contract_details
   IS
      SELECT ib.sell_to_cust_cd,
             cont_header.ds_contr_num contract_number,
             cont_type.ds_contr_tp_nm contract_type,
             cont_header.ds_contr_pk,
             cont_header.contr_vrsn_eff_from_dt header_start_date,
             cont_header.contr_vrsn_eff_thru_dt header_end_date,
             cont_detail.contr_eff_from_dt line_start_date,
             cont_detail.contr_eff_thru_dt line_end_date,
             cont_hdr_sts.ds_contr_sts_nm header_status,
             cont_det_sts.ds_contr_dtl_sts_nm line_status,
             cont_detail.rsp_tm_up_mn_aot,
             cont_detail.ds_contr_dtl_pk, ib.svc_mach_mstr_pk
        FROM svc_mach_mstr ib,
             ds_contr_dtl cont_detail,
             ds_contr cont_header,
             ds_contr_tp cont_type,
             ds_contr_sts cont_hdr_sts,
             ds_contr_dtl_sts cont_det_sts
       WHERE     1 = 1
             AND ib.svc_mach_mstr_pk = cont_detail.svc_mach_mstr_pk
             AND cont_detail.DS_CONTR_PK = cont_header.DS_CONTR_PK
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ser_num = p_in_serial
             /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                 TO_CHAR (SYSDATE, 'YYYYMMDD')
                          AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                        TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND ib.ezcancelflag = g_cancel_flg
             AND cont_detail.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.DS_CONTR_TP_CD = cont_type.DS_CONTR_TP_CD
             AND cont_header.DS_CONTR_STS_CD = cont_hdr_sts.DS_CONTR_STS_CD
             AND cont_detail.DS_CONTR_DTL_STS_CD =
                    cont_det_sts.DS_CONTR_DTL_STS_CD
             AND cont_type.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_hdr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_det_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_detail.ezcancelflag = g_cancel_flg
             AND cont_header.ezcancelflag = g_cancel_flg
             AND cont_type.ezcancelflag = g_cancel_flg
             AND cont_hdr_sts.ezcancelflag = g_cancel_flg
             AND cont_det_sts.ezcancelflag = g_cancel_flg;

BEGIN
   x_contract_details_tbl := CANON_E307_CONTRACT_TBL ();

   -- debug_pkg.debug_proc('Inside  Package ');
   FOR fr_cur_contract_details IN cur_contract_details
   LOOP
      x_ds_contr_dtl_pk := fr_cur_contract_details.ds_contr_dtl_pk;
      x_ds_contr_pk := fr_cur_contract_details.ds_contr_pk;

      BEGIN
         SELECT sell_to.sell_to_cust_cd,sell_to.loc_nm cust_name
           INTO l_cust_cd,l_cust_name
           FROM sell_to_cust sell_to
          WHERE     sell_to.SELL_TO_CUST_CD =
                       fr_cur_contract_details.SELL_TO_CUST_CD
                AND sell_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND NVL (sell_to.eff_thru_dt, SYSDATE + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_cd   := NULL;
            l_cust_name := NULL;
      END;
      IF fr_cur_contract_details.rsp_tm_up_mn_aot IS NULL
      THEN
          BEGIN
          SELECT distinct ds.RSP_TM_UP_MN_AOT
          INTO l_resp_tm
              FROM DS_MDL ds, SVC_CONFIG_MSTR scm, SVC_MACH_MSTR smm
            WHERE  smm.SVC_MACH_MSTR_PK =fr_cur_contract_details.SVC_MACH_MSTR_PK
           AND scm.SVC_CONFIG_MSTR_PK = smm.SVC_CONFIG_MSTR_PK
           AND scm.MDL_ID = ds.MDL_ID
           AND smm.GLBL_CMPY_CD = g_glbl_cmpy_cd
           AND scm.GLBL_CMPY_CD = g_glbl_cmpy_cd
           AND ds.GLBL_CMPY_CD = g_glbl_cmpy_cd
           AND ds.ezcancelflag = g_cancel_flg
           AND scm.ezcancelflag = g_cancel_flg
           AND smm.ezcancelflag = g_cancel_flg
           AND rownum<2;
          EXCEPTION WHEN OTHERS
          THEN
          l_resp_tm:=NULL;
          END;
      ELSE
      l_resp_tm:=fr_cur_contract_details.rsp_tm_up_mn_aot;
      END IF;

      l_sla_converted :=
         CANON_E307_UTILS.format_time (
            p_time       => l_resp_tm,
            p_time_uom   => 'MIN',
            p_format     => 'FORMAT1');

      l_header_eff_string :=
         CANON_E307_UTILS.format_date_range_string (
            fr_cur_contract_details.header_start_date,
            fr_cur_contract_details.header_end_date,
            'FORMAT1');
      l_line_eff_string :=
         CANON_E307_UTILS.format_date_range_string (
            fr_cur_contract_details.line_start_date,
            fr_cur_contract_details.line_end_date,
            'FORMAT1');
      l_rec_contract_details :=
         CANON_E307_CONTRACT_REC (l_cust_cd,
                        l_cust_name,
                                  fr_cur_contract_details.contract_number,
                                  fr_cur_contract_details.contract_type,
                                  fr_cur_contract_details.header_start_date,
                                  fr_cur_contract_details.header_end_date,
                                  l_header_eff_string,
                                  fr_cur_contract_details.line_start_date,
                                  fr_cur_contract_details.line_end_date,
                                  l_line_eff_string,
                                  fr_cur_contract_details.header_status,
                                  fr_cur_contract_details.line_status,
                                  l_sla_converted);
      x_contract_details_tbl.EXTEND ();
      x_contract_details_tbl (ln_contract_rec_cnt) := l_rec_contract_details;
      ln_contract_rec_cnt := ln_contract_rec_cnt + 1;
      NULL;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_CONTRACT_INFO;

/*******************************************************************************************
 Procedure Name: GET_UGW_ERR_CODE
 Description: Get last four UGW error codes for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: x_ugw_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_UGW_ERR_CODE (p_in_serial   IN     VARCHAR2,
                            x_ugw_tbl        OUT CANON_E307_UGW_ERR_CODE_TBL)
IS
   l_rec_ugw        CANON_E307_UGW_ERR_CODE_REC;
   ln_ugw_rec_cnt   NUMBER := 1;

   CURSOR cur_ugw_err_code
   IS
      SELECT UGW_ERR_CODE
        FROM (  SELECT DISTINCT
                       ERROR_CODE || ' / ' || error_date UGW_ERR_CODE,
                       error_date
                  FROM canon_e307_smart_disp_dtls_vl
                 WHERE serial_number = p_in_serial
              ORDER BY error_date DESC)
       WHERE ROWNUM < 5;

BEGIN
   x_ugw_tbl := CANON_E307_UGW_ERR_CODE_TBL ();

  -- debug_pkg.debug_proc('Inside New Package GET_UGW_ERR_CODE');
   --Start Fetching UGW error Codes
   FOR fr_cur_ugw_err_code IN cur_ugw_err_code
   LOOP
      l_rec_ugw :=
         CANON_E307_UGW_ERR_CODE_REC (fr_cur_ugw_err_code.UGW_ERR_CODE);
      x_ugw_tbl.EXTEND ();
      x_ugw_tbl (ln_ugw_rec_cnt) := l_rec_ugw;
      ln_ugw_rec_cnt := ln_ugw_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
   --  debug_pkg.debug_proc('Inside GET_UGW_ERR_CODE Exception');
END GET_UGW_ERR_CODE;

/*******************************************************************************************
 Procedure Name: GET_EQUIP_BRANCH
 Description: Get bill to location for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: o_br_cd
             o_br_desc
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_EQUIP_BRANCH (p_in_serial   IN     VARCHAR2,
                            o_br_cd          OUT VARCHAR2,
                            o_br_desc        OUT VARCHAR2)
IS
BEGIN
   SELECT DISTINCT fld_svc_br_cd, svc_contr_br_desc_txt
     INTO o_br_cd, o_br_desc
     FROM svc_contr_br branch, svc_mach_mstr ib
    WHERE     branch.svc_contr_br_cd = ib.fld_svc_br_cd
          AND ib.ser_num = p_in_serial
          /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                                   AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')*/
          AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND ib.ezcancelflag = g_cancel_flg
          AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND branch.ezcancelflag = g_cancel_flg;
--debug_pkg.debug_proc('o_br_cd:= '||o_br_cd);
--debug_pkg.debug_proc('o_br_desc:= '||o_br_desc);          
EXCEPTION
   WHEN OTHERS
   THEN
  -- debug_pkg.debug_proc('Inside Brach code exception'); 
      o_br_cd := NULL;
      o_br_desc := NULL;
END GET_EQUIP_BRANCH;

/*******************************************************************************************
 Function Name: GET_PO_REQ_FLG
 Description: Get PO required flag
 Input Parameters: p_in_serial
            p_in_col
            p_in_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_PO_REQ_FLG (p_in_serial            IN VARCHAR2,
                         p_in_sell_to_cust_cd   IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_rec_bill_to_details   CANON_E307_CUST_LOC_REC;
   ln_bill_to_rec_cnt      NUMBER := 1;
   lv_po_flag              ds_cust_trx_rule.ds_po_req_flg%TYPE;
   lv_biz_count        NUMBER:=0;
BEGIN
    BEGIN
        SELECT count(1)
        INTO lv_biz_count
        FROM svc_mach_mstr ib
        WHERE ib.ser_num = p_in_serial
        AND svc_by_line_biz_tp_cd IN ('PPS', 'LFS')
        AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND ib.ezcancelflag = g_cancel_flg;
    EXCEPTION WHEN OTHERS
    THEN
    lv_biz_count:=0;
    END;
-- As per FD Return PO Flag='Y' if PO is required and the Line of Business is either PPS or LFS 
  IF lv_biz_count>0
  THEN
  BEGIN
 --  debug_pkg.debug_proc('In Exception PO Flag 0');
   SELECT po.ds_po_req_flg
     INTO lv_po_flag
     FROM SVC_MACH_MSTR ib, BILL_TO_CUST bill_to, ds_cust_trx_rule po
    WHERE     ib.bill_to_loc_num = bill_to.BILL_TO_CUST_CD
    AND po.loc_num = bill_to.loc_nm
          AND ib.ser_num = p_in_serial
          /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')
                       AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                     TO_CHAR (SYSDATE, 'YYYYMMDD')*/
          --'HHOZDYYHSH'
          AND po.ds_acct_num = p_in_sell_to_cust_cd
          AND svc_by_line_biz_tp_cd IN ('PPS', 'LFS')
          AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND po.ezcancelflag = g_cancel_flg
          AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND ib.ezcancelflag = g_cancel_flg
      AND bill_to.ezcancelflag = g_cancel_flg
          AND ROWNUM = 1;

   RETURN lv_po_flag;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
  -- debug_pkg.debug_proc('In Exception PO Flag 1');
      SELECT po.ds_po_req_flg
        INTO lv_po_flag
        FROM ds_cust_trx_rule po
       WHERE     1 = 1
             AND po.ds_acct_num = p_in_sell_to_cust_cd
             AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND po.ezcancelflag = g_cancel_flg
             AND ROWNUM = 1;

      RETURN lv_po_flag;
   WHEN OTHERS
   THEN
      RETURN 'N';
  END;
  ELSE
   RETURN 'N';
 END IF; 
EXCEPTION  
WHEN OTHERS
   THEN
      RETURN 'N';
    --   debug_pkg.debug_proc('In Exception PO Flag2');
END GET_PO_REQ_FLG;

/*******************************************************************************************
 Function Name: GET_PSN_NM
 Description: Get the user name for user id
 Input Parameters: p_in_usr_cd

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_PSN_NM (p_in_usr_cd   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_usr_nm   VARCHAR2(500);
    
  
BEGIN

 SELECT psn_last_nm || ', ' || psn_first_nm
   INTO lv_usr_nm
                   from s21_psn psn
               where psn.psn_cd=p_in_usr_cd
                and glbl_cmpy_cd=g_glbl_cmpy_cd
                and EZCANCELFLAG=g_cancel_flg;

    
  RETURN lv_usr_nm;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_PSN_NM;

/*******************************************************************************************
 Procedure Name: GET_CUR_LOCATION
 Description: Get current location for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: x_cust_loc_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

PROCEDURE GET_CUR_LOCATION (p_in_serial      IN     VARCHAR2,
                            x_cust_loc_tbl      OUT CANON_E307_CUST_LOC_TBL)
IS
   l_rec_cust_loc_details   CANON_E307_CUST_LOC_REC;
   ln_cust_loc_rec_cnt      NUMBER := 1;

   CURSOR cur_cust_loc
   IS
      SELECT ship_to.sell_to_cust_cd CUST_CODE,
             ship_to.loc_nm CUST_NAME,
             ship_to.FIRST_LINE_ADDR ADDRESS,
             ship_to.CTY_ADDR CITY,
             ship_to.ST_CD STATE,
             ship_to.POST_CD POSTAL_CODE,
             ship_to.CTRY_CD COUNTRY,
             '' PAYMENT_TERM
        FROM svc_mach_mstr ib, ship_to_cust ship_to
       WHERE     ib.ser_num = p_in_serial                       --'HHOZDYYHSH'
             AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND ship_to.ship_to_cust_cd = ib.cur_loc_num --ib.ship_to_cust_cd
             AND ROWNUM = 1;

BEGIN
   x_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();

   -- debug_pkg.debug_proc('Inside New Package GET_CUR_LOCATION'||p_in_serial);
   --Start Fetching Current location details
   FOR fr_cur_cust_loc IN cur_cust_loc
   LOOP
      l_rec_cust_loc_details :=
         CANON_E307_CUST_LOC_REC (fr_cur_cust_loc.CUST_CODE,
                                  fr_cur_cust_loc.CUST_NAME,
                                  fr_cur_cust_loc.ADDRESS,
                                  fr_cur_cust_loc.CITY,
                                  fr_cur_cust_loc.STATE,
                                  fr_cur_cust_loc.POSTAL_CODE,
                                  fr_cur_cust_loc.COUNTRY,
                                  fr_cur_cust_loc.PAYMENT_TERM);
      x_cust_loc_tbl.EXTEND ();
      x_cust_loc_tbl (ln_cust_loc_rec_cnt) := l_rec_cust_loc_details;
        -- debug_pkg.debug_proc('fr_cur_cust_loc.CUST_Address ='||fr_cur_cust_loc.ADDRESS);
      ln_cust_loc_rec_cnt := ln_cust_loc_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
    -- debug_pkg.debug_proc('Inside Exception GET_CUR_LOCATION: '||sqlerrm);
END GET_CUR_LOCATION;

/*******************************************************************************************
 Procedure Name: GET_BILL_TO_LOCATION
 Description: Get bill to location for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: x_bill_to_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_BILL_TO_LOCATION (
   p_in_serial     IN     VARCHAR2,
   x_bill_to_tbl      OUT CANON_E307_CUST_LOC_TBL)
IS
   l_rec_bill_to_details   CANON_E307_CUST_LOC_REC;
   ln_bill_to_rec_cnt      NUMBER := 1;

   CURSOR cur_bill_to
   IS
      SELECT bill_to.bill_to_cust_cd CUST_CODE,
             bill_to.loc_nm CUST_NAME,
             bill_to.first_line_addr ADDRESS,
             bill_to.cty_addr CITY,
             bill_to.st_cd STATE,
             bill_to.post_cd POSTAL_CODE,
             bill_to.ctry_cd COUNTRY,
             (SELECT pt.pmt_term_nm
                FROM cust_pmt_term cpt, pmt_term pt
               WHERE     bill_to.bill_to_cust_cd = cpt.bill_to_cust_cd
                     AND cpt.pmt_term_cd = pt.pmt_term_cd(+)
                     AND cpt.glbl_cmpy_cd = g_glbl_cmpy_cd
                     AND pt.glbl_cmpy_cd = g_glbl_cmpy_cd
                     AND ROWNUM = 1)
                PAYMENT_TERM
        FROM svc_mach_mstr ib, bill_to_cust bill_to
       WHERE                   -- ib.BILL_TO_CUST_CD = bill_to.BILL_TO_CUST_CD
            ib   .bill_to_loc_num = bill_to.BILL_TO_CUST_CD
             AND ib.ser_num = p_in_serial
             AND NVL (bill_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')               --'HHOZDYYHSH'
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND bill_to.ezcancelflag = g_cancel_flg
             AND ROWNUM = 1;

BEGIN
   x_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();

   --Start Fetching bill to location details
   FOR fr_cur_bill_to IN cur_bill_to
   LOOP
      l_rec_bill_to_details :=
         CANON_E307_CUST_LOC_REC (fr_cur_bill_to.cust_code,
                                  fr_cur_bill_to.cust_name,
                                  fr_cur_bill_to.address,
                                  fr_cur_bill_to.city,
                                  fr_cur_bill_to.state,
                                  fr_cur_bill_to.postal_code,
                                  fr_cur_bill_to.country,
                                  fr_cur_bill_to.payment_term);
      x_bill_to_tbl.EXTEND ();
      x_bill_to_tbl (ln_bill_to_rec_cnt) := l_rec_bill_to_details;
      ln_bill_to_rec_cnt := ln_bill_to_rec_cnt + 1;
 
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
  --  debug_pkg.debug_proc('Inside Exception GET_BILL_TO_LOCATION: '||sqlerrm);      
END GET_BILL_TO_LOCATION;

/*******************************************************************************************
Procedure Name: PROBLEM_CODE_LOV
Description: Get problem type LOV details to be displayed in ASCC
Input Parameters: p_in_attr 
                  p_in_model     
                  p_in_type  
                  p_in_desc  

Output Parameters: o_prob_code_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy   1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE PROBLEM_CODE_LOV (p_in_attr         IN     VARCHAR2,
                            p_in_model        IN     VARCHAR2,
                            p_in_type         IN     VARCHAR2,
                            p_in_desc         IN     VARCHAR2,
                            o_prob_code_tbl      OUT CANON_E307_LOV_TBL)
IS
   lv_index1         NUMBER;
   lv_index2         NUMBER;
   lv_return_col     VARCHAR2 (100);
   v_sql             VARCHAR2 (32000);
   default_where     VARCHAR2 (10000);
   lv_val1           VARCHAR2 (500);
   v_prob_cursor     cur_typ;
   l_rec_prob_code   CANON_E307_LOV_REC;
   ln_rec_cnt_code   NUMBER := 1;
   lv_val11          VARCHAR2 (500);
BEGIN
   o_prob_code_tbl := CANON_E307_LOV_TBL ();
   lv_return_col := p_in_attr;

   IF p_in_type IS NOT NULL
   THEN
      default_where :=
            default_where
         || ' AND spt.svc_pblm_tp_nm= trim('''
         || p_in_type
         || ''') ';
   END IF;

   IF p_in_desc IS NOT NULL
   THEN
      default_where :=
            default_where
         || ' AND  spt.svc_pblm_grp_txt= trim('''
         || p_in_desc
         || ''') ';
   END IF;

   IF UPPER (lv_return_col) = 'PROBLEMTYPE'
   THEN
      lv_return_col := 'spt.svc_pblm_tp_nm';
   --debug_pkg.debug_proc('lv_return_col ='||lv_return_col);
   ELSIF UPPER (lv_return_col) = 'PROBLEMDESC'
   THEN
      lv_return_col := 'spt.svc_pblm_grp_txt';
   ELSIF UPPER (lv_return_col) = 'PROBLEMCODE'
   THEN
      lv_return_col := 'spt.svc_pblm_tp_cd';
   END IF;

   v_sql :=
         'select distinct '
      || lv_return_col
      || ' FROM  MDL_NM mn,DS_MDL model, SVC_PBLM_MDL_GRP_RELN grp_rel, '
      || 'SVC_PBLM_TP spt '
      || 'WHERE 1=1 '
      || ' AND mn.T_MDL_ID =model.MDL_ID '
  --    || ' AND model.mdl_grp_id = grp_rel.mdl_grp_id ' 
      || ' AND grp_rel.svc_pblm_catg_cd =spt.svc_pblm_catg_cd '
      || ' AND nvl(model.mdl_actv_flg,''Y'')=''Y'' '
      || ' AND  mn.t_mdl_nm = trim('''
      || p_in_model
      || ''') '
      || ' AND model.glbl_cmpy_cd = '''
      || g_glbl_cmpy_cd
      || ''''
      || ' AND grp_rel.glbl_cmpy_cd = '''
      || g_glbl_cmpy_cd
      || ''''
      || ' AND spt.glbl_cmpy_cd = '''
      || g_glbl_cmpy_cd
      || ''''
      || ' AND model.ezcancelflag = '''
      || g_cancel_flg
      || ''''
      || ' AND grp_rel.ezcancelflag = '''
            || g_cancel_flg
      || ''''
      || ' AND spt.ezcancelflag = '''
      || g_cancel_flg
      || ''''
      || default_where;

  -- debug_pkg.debug_proc('v_sql ='||v_sql);
   OPEN v_prob_cursor FOR v_sql;

   LOOP
      FETCH v_prob_cursor INTO lv_val11;

      EXIT WHEN v_prob_cursor%NOTFOUND;
      l_rec_prob_code := CANON_E307_LOV_REC (lv_val11);
      o_prob_code_tbl.EXTEND ();
      o_prob_code_tbl (ln_rec_cnt_code) := l_rec_prob_code;
      ln_rec_cnt_code := ln_rec_cnt_code + 1;
   END LOOP;

   CLOSE v_prob_cursor;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END PROBLEM_CODE_LOV;

/*******************************************************************************************
Procedure Name: LOOKUP_BY_XML_MESSAGE
Description: Generic xml to return LOV details
Input Parameters: p_xml_message

Output Parameters: o_xml_string
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Sesh Ragavachari   1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/

PROCEDURE LOOKUP_BY_XML_MESSAGE (p_xml_message   IN     VARCHAR2,
                                 o_xml_string       OUT VARCHAR2)
IS
 
/*CURSOR select_criteria
   IS
                      SELECT xt.*
                        FROM DYANMIC_LOOKUP_REQUEST d,
                             XMLTABLE (
                                'AllClauses/SelectClause'
                                PASSING d.REQUEST_MESSAGE
                                COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column') xt;

   CURSOR where_criteria
   IS
                      SELECT xt.*
                        FROM DYANMIC_LOOKUP_REQUEST d,
                             XMLTABLE (
                                'AllClauses/WhereClauses/WhereClause'
                                PASSING d.REQUEST_MESSAGE
                                COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column',
                                        "ROW_VALUE"           VARCHAR2 (100)
                                              PATH 'ColumnValue') xt;

   CURSOR from_criteria
   IS
                      SELECT xt.*
                        FROM DYANMIC_LOOKUP_REQUEST d,
                             XMLTABLE (
                                'AllClauses/entityObject'
                                PASSING d.REQUEST_MESSAGE
                                COLUMNS "ENTITY_IDENTIFIER"   VARCHAR2 (100) PATH 'Column') xt;*/
  CURSOR select_criteria
   IS
                      SELECT xt.*
                         FROM
                             XMLTABLE (
                                'AllClauses/SelectClause'
                                PASSING XMLTYPE(p_xml_message)
                                COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column') xt;

   CURSOR where_criteria
   IS
                      SELECT xt.*
                        FROM 
                             XMLTABLE (
                                'AllClauses/WhereClauses/WhereClause'
                                PASSING XMLTYPE(p_xml_message)
                                COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column',
                                        "ROW_VALUE"           VARCHAR2 (100)
                                              PATH 'ColumnValue') xt;

   CURSOR from_criteria
   IS
                      SELECT xt.*
                        FROM
                             XMLTABLE (
                                'AllClauses/entityObject'
                                PASSING XMLTYPE(p_xml_message)
                                COLUMNS "ENTITY_IDENTIFIER"   VARCHAR2 (100) PATH 'Column') xt;  
                                
   CURSOR order_criteria
   IS
                      SELECT xt.*
                         FROM
                             XMLTABLE (
                                'AllClauses/OrderByClause'
                                PASSING XMLTYPE(p_xml_message)
                                COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column') xt;                                
                                
   l_counter               NUMBER := 0;
   l_table_name            VARCHAR2 (100);
   l_select_column         VARCHAR2 (2000);
   l_order_column       VARCHAR2 (2000);
   l_where_column          VARCHAR2 (2000);
   l_select_string         VARCHAR2 (10000);
   l_order_string       VARCHAR2 (10000);
   l_where_string          VARCHAR2 (10000);
   l_table_string          VARCHAR2 (10000);
   l_final_string          VARCHAR2 (32000);
   l_root_begintag         VARCHAR2 (50) := '<ReturnList>';
   l_list_entry_begintag   VARCHAR2 (50) := '<ListEntry>';
   l_left_begintag         VARCHAR2 (50) := '<';
   l_right_tag             VARCHAR2 (50) := '>';
   l_left_endtag           VARCHAR2 (50) := '</';
   l_list_entry_endtag     VARCHAR2 (50) := '</ListEntry>';
   l_root_endtag           VARCHAR2 (50) := '</ReturnList>';
   l_dyna_sql              VARCHAR2 (32000);
   l_semi_colon            VARCHAR2 (1) := ';';
   l_quote                 VARCHAR2 (5) := '''';
   l_xml_string            VARCHAR2 (32000);
   l_line_brake_char       VARCHAR2 (30) := 'chr(10)';
   l_pipe                  VARCHAR2 (10) := '||';
BEGIN
--debug_pkg.debug_proc('inside xml proc: '||p_xml_message);
   /*INSERT INTO DYANMIC_LOOKUP_REQUEST
        VALUES (
                  DYNAMIC_REQUEST_SEQ.NEXTVAL,
                  XMLType (p_xml_message));*/
  --  debug_pkg.debug_proc('inside xml proc: '||p_xml_message);

   --- Start Building the Dymanic Select Statement
   FOR select_rec IN select_criteria
   LOOP
      l_counter := l_counter + 1;

      --- Get the Column from
      BEGIN
         SELECT DB_OBJECT
           INTO l_select_column
           FROM CANON_OBJECT_MAPPING
          WHERE     JAVA_OBJECT = select_rec.COLUMN_IDENTIFIER
                AND OBJECT_TYPE = 'COLUMN_OBJECT';
      END;

     -- debug_pkg.debug_proc('l_select_column ='||l_select_column);
      IF l_counter = 1
      THEN
         l_select_string := 'SELECT DISTINCT ' || l_select_column;
      ELSE
         l_select_string := l_select_string || ',' || l_select_column;
      END IF;
   END LOOP;

  --debug_pkg.debug_proc('l_select_string ='||l_select_string);
   l_counter := 0;
   l_where_string := 'WHERE 1=1';

   FOR where_rec IN where_criteria
   LOOP
      l_counter := l_counter + 1;
--debug_pkg.debug_proc('where_rec.COLUMN_IDENTIFIER ='||where_rec.COLUMN_IDENTIFIER);
      --- Get the Column from
      BEGIN
         SELECT DB_OBJECT
           INTO l_where_column
           FROM CANON_OBJECT_MAPPING
          WHERE     JAVA_OBJECT = where_rec.COLUMN_IDENTIFIER
                AND OBJECT_TYPE = 'COLUMN_OBJECT';
      END;

      IF l_counter = 1
      THEN
      --debug_pkg.debug_proc('Inside l_counter = 1');
         l_where_string := l_where_string ||
               ' AND '
            || l_where_column
            || ' = '
            || ''''
            || where_rec.ROW_VALUE
            || '''';
       --     debug_pkg.debug_proc('l_where_string1.0 ='||l_where_string);
      ELSE
    -- debug_pkg.debug_proc('Inside l_counter = 1 ELSE');
         l_where_string :=
               l_where_string
            || ' AND '
            || l_where_column
            || ' = '
            || ''''
            || where_rec.ROW_VALUE
            || '''';
      END IF;
     -- debug_pkg.debug_proc('l_where_string2.0 ='||l_where_string);
   END LOOP;

   l_where_string :=
         l_where_string
      || ' AND glbl_cmpy_cd = '''
      || g_glbl_cmpy_cd
      || ''''
      || ' AND ezcancelflag = '''
      || g_cancel_flg
      || '''';
      
   l_counter := 0;

   -- debug_pkg.debug_proc('l_where_string1 ='||l_where_string);
   FOR from_rec IN from_criteria
   LOOP
      l_counter := l_counter + 1;

      --- Get the Column from
      BEGIN
         SELECT DB_OBJECT
           INTO l_table_name
           FROM CANON_OBJECT_MAPPING
          WHERE     JAVA_OBJECT = from_rec.ENTITY_IDENTIFIER
                AND OBJECT_TYPE = 'ENTITY_OBJECT';
      END;

      IF l_counter = 1
      THEN
         l_table_string := 'FROM ' || l_table_name;
      ELSE
         l_table_string := l_table_string || ',' || l_table_name;
      END IF;
   -- debug_pkg.debug_proc('l_table_string ='||l_table_string);
   END LOOP;
   /* IF l_table_name = 'DS_SVC_CALL_TP'
   THEN
      l_where_string := l_where_string || ' AND ascc_sel_flg = ''Y''';
   END IF; */
   l_counter := 0;
FOR order_rec IN order_criteria
   LOOP
      l_counter := l_counter + 1;

      --- Get the Column from
      BEGIN
         SELECT DB_OBJECT
           INTO l_order_column
           FROM CANON_OBJECT_MAPPING
          WHERE     JAVA_OBJECT = order_rec.COLUMN_IDENTIFIER
                AND OBJECT_TYPE = 'COLUMN_OBJECT';
      END;

     -- debug_pkg.debug_proc('l_select_column ='||l_select_column);
      IF l_counter = 1
      THEN
         l_order_string := 'ORDER BY ' || l_order_column;
      ELSE
         l_order_string := l_order_string || ',' || l_order_column;
      END IF;
   END LOOP;
   l_final_string :=
         l_select_string
      || ' '
      || l_table_string
      || ' '
      || l_where_string
      || ' '
      || l_order_string
      || ';';
--  debug_pkg.debug_proc('l_final_string ='||l_final_string);
   l_dyna_sql := 'DECLARE CURSOR C1 IS' || CHR (10);
   l_dyna_sql := l_dyna_sql || l_final_string || CHR (10);
   l_dyna_sql := l_dyna_sql || 'l_xml_string VARCHAR2(32000);' || CHR (10);
   l_dyna_sql := l_dyna_sql || ' BEGIN ' || CHR (10);
   l_dyna_sql :=
         l_dyna_sql
      || 'l_xml_string :='
      || l_quote
      || l_root_begintag
      || l_quote
      || l_pipe
      || l_line_brake_char
      || l_semi_colon
      || CHR (10);
   l_dyna_sql := l_dyna_sql || ' FOR C1REC IN C1  ' || CHR (10);
   l_dyna_sql := l_dyna_sql || ' LOOP  ' || CHR (10);
   l_dyna_sql :=
         l_dyna_sql
      || 'l_xml_string    :=  l_xml_string  ||'
      || l_quote
      || l_list_entry_begintag
      || l_quote
      || l_pipe
      || l_line_brake_char
      || l_semi_colon
      || CHR (10);

   FOR select_rec IN select_criteria
   LOOP
      BEGIN
         SELECT DB_OBJECT
           INTO l_select_column
           FROM CANON_OBJECT_MAPPING
          WHERE     JAVA_OBJECT = select_rec.column_identifier
                AND OBJECT_TYPE = 'COLUMN_OBJECT';
      END;

      l_dyna_sql :=
            l_dyna_sql
         || 'l_xml_string    :=  l_xml_string  ||'
         || l_quote
         || l_left_begintag
         || select_rec.column_identifier
         || l_right_tag
         || l_quote
         || l_semi_colon
         || CHR (10);
      l_dyna_sql :=
            l_dyna_sql
         || 'l_xml_string    :=  l_xml_string  ||'
         || 'C1REC.'
         || l_select_column
         || l_semi_colon
         || CHR (10);
      l_dyna_sql :=
            l_dyna_sql
         || 'l_xml_string    :=  l_xml_string  ||'
         || l_quote
         || l_left_endtag
         || select_rec.column_identifier
         || l_right_tag
         || l_quote
         || l_pipe
         || l_line_brake_char
         || l_semi_colon
         || CHR (10);
   END LOOP;

   l_dyna_sql :=
         l_dyna_sql
      || 'l_xml_string    :=  l_xml_string  ||'
      || l_quote
      || l_list_entry_endtag
      || l_quote
      || l_pipe
      || l_line_brake_char
      || l_semi_colon
      || CHR (10);
   l_dyna_sql := l_dyna_sql || ' END LOOP;' || CHR (10);
   l_dyna_sql :=
         l_dyna_sql
      || 'l_xml_string := l_xml_string ||'
      || l_quote
      || l_root_endtag
      || l_quote
      || l_pipe
      || l_line_brake_char
      || l_semi_colon
      || CHR (10);
   l_dyna_sql := l_dyna_sql || ':x_output_string := l_xml_string;' || CHR (10);
   l_dyna_sql := l_dyna_sql || ' END;';
   --ROLLBACK;
--  DELETE FROM DYANMIC_LOOKUP_REQUEST;
--debug_pkg.debug_proc('l_dyna_sql='||l_dyna_sql);
    EXECUTE IMMEDIATE l_dyna_sql USING OUT l_xml_string;

   o_xml_string := l_xml_string;

   EXCEPTION
    WHEN OTHERS
      THEN
    NULL;
    --ROLLBACK;
 -- DELETE FROM DYANMIC_LOOKUP_REQUEST;
-- debug_pkg.debug_proc('Error'||SQLERRM);
END LOOKUP_BY_XML_MESSAGE;

/*******************************************************************************************
 Function Name: GET_RECALL_DAYS
 Description: Get recall days
 Input Parameters: None

 Output Parameters: pi_type
             p_in_mdl_id
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
FUNCTION GET_RECALL_DAYS (pi_type IN VARCHAR2, p_in_mdl_id IN VARCHAR2)
   RETURN NUMBER
IS
   l_recall_days     NUMBER;
   l_error_message   VARCHAR2 (500);
BEGIN
   IF pi_type = 'GLOBAL'
   THEN
      SELECT rcll_glbl_intvl_days_aot
        INTO l_recall_days
        FROM DS_MDL
       WHERE     MDL_ID = p_in_mdl_id
             AND NVL (mdl_actv_flg, 'Y') = 'Y'
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg;
   ELSIF pi_type = 'LOCAL'
   THEN
      SELECT rcll_intvl_days_aot
        INTO l_recall_days
        FROM DS_MDL
       WHERE     MDL_ID = p_in_mdl_id
             AND NVL (mdl_actv_flg, 'Y') = 'Y'
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg;
   END IF;

   RETURN l_recall_days;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      RETURN -1;
   WHEN OTHERS
   THEN
      RETURN -2;
END GET_RECALL_DAYS;

/*******************************************************************************************
 Procedure Name: CHECK_CALL_TYPE
 Description: Check call type
 Input Parameters:  p_in_serial 
                    p_in_mdl_id    
                    p_call_type_id  
                    p_call_type

 Output Parameters: x_call_type 
                    x_call_type_id    
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE CHECK_CALL_TYPE (p_in_serial      IN     VARCHAR2,
                           p_in_mdl_id      IN     VARCHAR2,
                           p_call_type_id   IN     VARCHAR2,
                           p_call_type      IN     VARCHAR2,
                           x_call_type         OUT VARCHAR2,
                           x_call_type_id      OUT VARCHAR2)
IS
   l_creation_date_prev   SVC_TASK.SVC_TASK_CPLT_DT%TYPE;
   l_creation_date_pres   SVC_TASK.SVC_TASK_CPLT_DT%TYPE
                             := TO_CHAR (SYSDATE, 'YYYYMMDD');
   l_recall_days          NUMBER := 0;
BEGIN
   -- Check when the task was last closed
   BEGIN
      SELECT MAX (task.svc_task_cplt_dt)
        INTO l_creation_date_prev
        FROM SVC_TASK task
       WHERE     task.ser_num = p_in_serial                       --'DFL08260'
             AND task.fsr_num IS NOT NULL
             AND task.SVC_TASK_CPLT_DT IS NOT NULL;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         l_creation_date_prev := NULL;
      WHEN OTHERS
      THEN
         l_creation_date_prev := NULL;
   END;

   IF l_creation_date_prev IS NOT NULL
   THEN
      -- Get the recall days for global
      l_recall_days := get_recall_days ('GLOBAL', p_in_mdl_id);

      IF l_recall_days NOT IN (-1, -2) AND l_recall_days IS NOT NULL
      THEN
         -- Decide if it is a recall or not
         IF (l_creation_date_pres) - (l_creation_date_prev) <= l_recall_days
         THEN
            -- Changing the call type to 'Recall'
            --TBD
            SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm
              INTO x_call_type_id, x_call_type
              FROM ds_svc_call_tp
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND ds_svc_call_tp_nm = 'RECALL';
         ELSE
            -- Call is not a recall
            x_call_type_id := p_call_type_id;
            x_call_type := p_call_type;
         END IF;
      ELSE
         x_call_type_id := p_call_type_id;
         x_call_type := p_call_type;
      END IF;
   ELSE
      x_call_type_id := p_call_type_id;
      x_call_type := p_call_type;
   END IF;
END check_call_type;

/*******************************************************************************************
 Procedure Name: GLOBAL_LEVEL_RECALL
 Description: Check if call has to updated to Recall
 Input Parameters: p_in_serial
                   p_in_model

 Output Parameters: x_call_type
                    x_call_type_id
 -----------------------------------------------------------------------------------------
 Author        Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE GLOBAL_LEVEL_RECALL (p_in_serial      IN     VARCHAR2,
                               p_in_model       IN     VARCHAR2,
                               x_call_type         OUT VARCHAR2,
                               x_call_type_id      OUT VARCHAR2)
IS
   l_def_call_type      VARCHAR2 (100);
   l_def_call_type_id   VARCHAR2 (100);
   lv_mdl_id            VARCHAR2 (30);
BEGIN
   BEGIN
      SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm
        INTO l_def_call_type_id, l_def_call_type
        FROM ds_svc_call_tp
       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg
             AND ds_svc_call_tp_nm = 'SERV CALL';
   EXCEPTION
      WHEN OTHERS
      THEN
         l_def_call_type_id := NULL;
         l_def_call_type := NULL;
   END;

   BEGIN
      SELECT T_MDL_ID
        INTO lv_mdl_id
        FROM MDL_NM mn
       WHERE 1 = 1 AND mn.t_mdl_nm = TRIM (p_in_model);
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_mdl_id := NULL;
   END;

   check_call_type (p_in_serial      => p_in_serial,
                    p_in_mdl_id      => lv_mdl_id,
                    p_call_type_id   => l_def_call_type_id,
                    p_call_type      => l_def_call_type,
                    x_call_type      => x_call_type,
                    x_call_type_id   => x_call_type_id);
EXCEPTION
   WHEN OTHERS
   THEN
      x_call_type := NULL;
      x_call_type_id := NULL;
END GLOBAL_LEVEL_RECALL;

/*******************************************************************************************
 Procedure Name: GET_CALL_DETAILS
 Description: Get SR details to be displayed in ASCC
 Input Parameters: p_in_serial
            p_in_model

 Output Parameters: o_resp_time  
              o_vip_flag  
               o_mach_tbl  
               o_ugw_tbl   
               o_prob_tbl      
               o_call_info_tbl    
               o_contract_details_tbl  
               o_cust_loc_tbl 
               o_bill_to_tbl           
               o_notes_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_CALL_DETAILS (
   p_in_serial                  VARCHAR2,
   p_in_model                   VARCHAR2,
   o_resp_time              OUT VARCHAR2,
   o_vip_flag               OUT VARCHAR2,
   o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
   o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
   o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
   o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
   o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
   o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
   o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
   o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL,
   o_mdl_dur            OUT VARCHAR2)
IS
   l_rec_mach               CANON_E307_MAC_SER_REC;
   l_rec_ugw                CANON_E307_UGW_ERR_CODE_REC;
   l_rec_prob               CANON_E307_PROB_CODE_REC;
   l_rec_call_info          CANON_E307_CALL_INFO_REC;
   l_rec_contract_details   CANON_E307_CONTRACT_REC;
   l_rec_cust_loc_details   CANON_E307_CUST_LOC_REC;
   l_rec_bill_to_details    CANON_E307_CUST_LOC_REC;
   l_rec_notes              CANON_E307_DEBRIEF_NOTE_REC;
   l_resp_time              DS_CONTR_DTL.RSP_TM_UP_MN_AOT%TYPE;
   x_call_type              VARCHAR2 (100);
   x_call_type_id           VARCHAR2 (100);
   l_special_message_type   SVC_MEMO_TP.SVC_MEMO_TP_CD%TYPE;
   x_contract_details_tbl CANON_E307_CONTRACT_TBL;
   x_ugw_tbl          CANON_E307_UGW_ERR_CODE_TBL;
   x_cust_loc_tbl      CANON_E307_CUST_LOC_TBL;
   x_bill_to_tbl      CANON_E307_CUST_LOC_TBL;

   CURSOR cur_mach_details
   IS
      SELECT smm.svc_mach_mstr_pk,                                
             mdl.T_MDL_NM model,
             smm.SER_NUM ser_num,
             smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
             config.svc_sln_nm solution_name,
             smm.ship_to_cust_cd ship_to_acct_no,
             ship_to.loc_nm ship_to_cust_name,
         ship_to.first_line_addr address_1,
             ship_to.scd_line_addr address_2,
             ship_to.cty_addr city,
         ship_to.st_cd state,
             ship_to.post_cd,
             smm.ownr_acct_num,
             smm.bill_to_cust_cd,
             ship_to.sell_to_cust_cd,
             smm.cur_loc_num,
             smm.cur_loc_acct_num,
             smm.biz_hrs_mon_fri_from_tm,
             smm.biz_hrs_mon_fri_to_tm,
             smm.biz_hrs_sat_from_tm,
             smm.biz_hrs_sat_to_tm,
             smm.biz_hrs_sun_from_tm,
             smm.biz_hrs_sun_to_tm,
             smm.last_svc_call_dt,
             smm.tot_svc_visit_cnt,
             smm.last_tech_visit_dt,
             smm.prf_tech_cd,
             smm.req_tech_cd,
             smm.fld_svc_br_cd
        FROM svc_mach_mstr smm, SVC_CONFIG_MSTR config, MDL_NM mdl,SHIP_TO_CUST ship_to
       WHERE     1 = 1
             AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
             /*and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND mdl.T_MDL_ID = config.MDL_ID
             AND mdl.t_mdl_nm = p_in_model
             AND smm.ser_num = p_in_serial
             AND ship_to.ship_to_cust_cd =smm.cur_loc_num
             AND NVL (ship_to.eff_thru_dt, SYSDATE + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd;

 

   CURSOR cur_prob_code
   IS
      SELECT spt.svc_pblm_tp_nm TYPE,
             spt.svc_pblm_grp_txt Description,
             spt.svc_pblm_tp_cd Code,
             '' Other,
             '' Machine_Status
        FROM MDL_NM mn,
             DS_MDL model,
             SVC_PBLM_MDL_GRP_RELN grp_rel,
             SVC_PBLM_TP spt
       WHERE     1 = 1
             AND mn.T_MDL_ID = model.MDL_ID
             AND model.mdl_grp_id = grp_rel.mdl_grp_id
             AND grp_rel.svc_pblm_catg_cd = spt.svc_pblm_catg_cd
             AND NVL (model.mdl_actv_flg, 'Y') = 'Y'
             AND mn.t_mdl_nm = TRIM (p_in_model)
             AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND grp_rel.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND model.ezcancelflag = g_cancel_flg
             AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND spt.ezcancelflag = g_cancel_flg
             AND grp_rel.ezcancelflag = g_cancel_flg
             AND ROWNUM = 1;

   CURSOR cur_call_info
   IS
      SELECT creation_channel,
             creation_channel_cd,
             task_type_name,
             task_type_code,
             line_of_business,
            -- branch,
            -- branch_cd,
             ah_task_type,
             ah_task_code
        FROM (SELECT 'ASCC' creation_channel,
                     (SELECT SVC_CALL_SRC_TP_CD
                        FROM svc_call_src_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND svc_call_src_tp_sort_num = '1'
                             AND svc_call_src_tp_nm = g_ascc_source_name
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        creation_channel_cd,
                     (SELECT ds_svc_call_tp_nm
                        FROM ds_svc_call_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ascc_sel_flg = 'Y'
                             AND ds_svc_call_tp_cd = 1
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Task_Type_Name,
                     (SELECT DS_SVC_CALL_TP_CD
                        FROM DS_SVC_CALL_TP
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ASCC_SEL_FLG = 'Y'
                             AND DS_SVC_CALL_TP_CD = 1
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Task_Type_Code,
                     (SELECT svc_by_line_biz_tp_cd
                        FROM svc_mach_mstr ib
                       WHERE     ib.ser_num = p_in_serial
                             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ib.ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Line_of_business,
                    (SELECT ds_svc_call_tp_nm
                        FROM ds_svc_call_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ascc_sel_flg = 'Y'
                             AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                             AND ezcancelflag = g_cancel_flg)
                        ah_task_type,
                     (SELECT ds_svc_call_tp_cd
                        FROM ds_svc_call_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ascc_sel_flg = 'Y'
                             AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                             AND ezcancelflag = g_cancel_flg)
                        ah_task_code
                FROM DUAL);

   
   CURSOR cur_notes(lv_ds_contr_pk IN VARCHAR2,lv_ds_contr_dtl_pk IN VARCHAR2)
   IS
      SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                GET_PSN_NM(note.ezinuserid) Created_By
           FROM SVC_MACH_MSTR ib, SVC_MEMO note, SVC_MEMO_TP note_type,SVC_MEMO_CATG CATEGORY
          WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
                AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm='Machine Memo'
                AND ib.ser_num = p_in_serial
               /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                               TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND note.svc_task_num is null
                and note.fsr_num is null
                and note.ds_contr_pk is null
                and note.ds_contr_dtl_pk is null
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                GET_PSN_NM(note.ezinuserid) Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
          WHERE  note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm='Contract Memo'
                AND note.ds_contr_pk = lv_ds_contr_pk
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_DTL_PK IS NULL
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                GET_PSN_NM(note.ezinuserid) Created_By
           FROM  SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
          WHERE note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND category.svc_memo_catg_nm='Contract Memo'
                AND note.ds_contr_dtl_pk  = lv_ds_contr_dtl_pk
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                   AND note.DS_CONTR_PK IS NULL;

   ln_mach_rec_cnt          NUMBER := 1;
   ln_prob_rec_cnt          NUMBER := 1;
   ln_call_rec_cnt          NUMBER := 1;
   ln_note_rec_cnt          NUMBER := 1;
   l_cust_tel_num           SVC_MACH_CTAC_PSN.CTAC_PSN_TEL_NUM%TYPE := NULL;
   l_cust_tel_ext           SVC_MACH_CTAC_PSN.CTAC_PSN_TEL_EXTN_NUM%TYPE := NULL;
   l_email_address          SVC_MACH_CTAC_PSN.CTAC_PSN_EML_ADDR%TYPE := NULL;
   l_special_message        SVC_MACH_CTAC_PSN.CTAC_PSN_CMNT_TXT%TYPE := NULL;
   lv_contact             VARCHAR2 (300);
   l_weekday_hours          VARCHAR2 (100);
   l_sat_hours              VARCHAR2 (100);
   l_sun_hours              VARCHAR2 (100);
   l_cust_name              VARCHAR2 (100);
   l_header_eff_string      VARCHAR2 (100);
   l_line_eff_string        VARCHAR2 (100);
   l_sla_converted          VARCHAR2(50);
   lv_ds_contr_pk        VARCHAR2 (100);
   lv_ds_contr_dtl_pk       VARCHAR2 (100);
   lv_br_cd           VARCHAR2 (100);
   lv_br_desc         VARCHAR2 (500);
   lv_sell_to_cust_cd   VARCHAR2 (100);
   lv_po_flag    ds_cust_trx_rule.ds_po_req_flg%TYPE;
   l_cust_phone1       VARCHAR2(3);
   l_cust_phone2       VARCHAR2(3);
   l_cust_phone3       VARCHAR2(4);
BEGIN
   BEGIN
      SELECT RSP_TM_UP_MN_AOT
        INTO l_resp_time
        FROM SVC_MACH_MSTR ib, DS_CONTR_DTL cont
       WHERE     ser_num = p_in_serial                          --'HHOZDYYHSH'
             AND ib.SVC_MACH_MSTR_PK = cont.SVC_MACH_MSTR_PK
             /*AND NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND cont.ezcancelflag = g_cancel_flg;

      o_resp_time := l_resp_time;
   EXCEPTION
      WHEN OTHERS
      THEN
         o_resp_time := NULL;
   END;

   BEGIN
      --SELECT 'N' INTO o_vip_flag FROM DUAL;
      SELECT DS_KEY_ACCT_FLG
        INTO o_vip_flag
        FROM SVC_MACH_MSTR ib
       WHERE     ib.ser_num = p_in_serial                       --'TC00000001'
       /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')
                       AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd;
   EXCEPTION
      WHEN OTHERS
      THEN
         o_vip_flag := 'N';
   END;
   
   BEGIN
   
   SELECT MDL_DURN_TM_NUM
   INTO o_mdl_dur
     FROM MDL_NM mn, DS_MDL model
    WHERE     1 = 1
          AND mn.T_MDL_ID = model.MDL_ID
          AND mn.t_mdl_nm = TRIM (p_in_model)
          AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND mn.T_GLBL_CMPY_CD = g_glbl_cmpy_cd
          AND model.ezcancelflag = g_cancel_flg
       AND mn.ezcancelflag = g_cancel_flg;
    EXCEPTION
      WHEN OTHERS
      THEN
         o_mdl_dur := NULL;
   END;      
       

   o_mach_tbl := CANON_E307_MAC_SER_TBL ();
   o_ugw_tbl := CANON_E307_UGW_ERR_CODE_TBL ();
   o_prob_tbl := CANON_E307_PROB_CODE_TBL ();
   o_call_info_tbl := CANON_E307_CALL_INFO_TBL ();
   o_contract_details_tbl := CANON_E307_CONTRACT_TBL ();
   o_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();
   o_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();
   o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();

   BEGIN
      SELECT SVC_MEMO_TP_CD
        INTO l_special_message_type
        FROM SVC_MEMO_TP
       WHERE     SVC_MEMO_TP_NM = CANON_E307_CONSTANTS.g_special_message_name
             AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
   END;

   --Start fetching Customer Machine Details
   FOR fr_cur_mach_details IN cur_mach_details
   LOOP
      --- Get Contact Info for Special Message Population
      BEGIN
   /*      SELECT CTAC_PSN_TEL_NUM,
                CTAC_PSN_TEL_EXTN_NUM,
                CTAC_PSN_EML_ADDR,
                CTAC_PSN_CMNT_TXT MESSAGE,
                CTAC_PSN_LAST_NM||', '||CTAC_PSN_FIRST_NM
           INTO l_cust_tel_num,
                l_cust_tel_ext,
                l_email_address,
                l_special_message,
                lv_contact
           FROM SVC_MACH_CTAC_PSN
          WHERE SVC_MACH_MSTR_PK = fr_cur_mach_details.svc_mach_mstr_pk; */
   /*     SELECT distinct CTAC_PSN_TEL_NUM,
               CTAC_PSN_TEL_EXTN_NUM,
               CTAC_PSN_EML_ADDR,
               CTAC_PSN_CMNT_TXT MESSAGE,
               CTAC_PSN_LAST_NM || ', ' || CTAC_PSN_FIRST_NM,
               SUBSTR (CTAC_PSN_TEL_NUM, 1, 3) Phone1,
               SUBSTR (CTAC_PSN_TEL_NUM, 5, 3) Phone2,
               SUBSTR (CTAC_PSN_TEL_NUM, 9, 4) Phone3
               INTO l_cust_tel_num,
                l_cust_tel_ext,
                l_email_address,
                l_special_message,
                lv_contact,
                l_cust_phone1,
                l_cust_phone2,
                l_cust_phone3
          FROM SVC_MACH_CTAC_PSN mcp, DS_CTAC_PNT dcp
         WHERE     mcp.SVC_MACH_MSTR_PK = fr_cur_mach_details.svc_mach_mstr_pk
               AND mcp.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
               AND NVL (mcp.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                      TO_CHAR (SYSDATE, 'YYYYMMDD')
               AND NVL (mcp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                      TO_CHAR (SYSDATE, 'YYYYMMDD')
               AND DS_CTAC_PNT_ACTV_FLG = 'Y' 
               AND rownum<2;    */
SELECT cp.CTAC_PSN_FIRST_NM || ', ' || cp.CTAC_PSN_LAST_NM,
       (SELECT DS_CTAC_PNT_VAL_TXT
          FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
         WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
               AND dcp1.DS_CTAC_PNT_TP_CD = dcpt.DS_CTAC_PNT_TP_CD
               AND dcpt.DS_CTAC_PNT_TP_NM = 'EMAIL - WORK'
               AND EML_FLG = 'Y')
          email,
      SUBSTR ( (SELECT DS_CTAC_PNT_VAL_TXT
          FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
         WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
               AND dcp1.DS_CTAC_PNT_TP_CD = dcpt.DS_CTAC_PNT_TP_CD
               AND DS_CTAC_PNT_ACTV_FLG = 'Y'
               AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK'), 1, 3)
          phone1,
      SUBSTR ( (SELECT DS_CTAC_PNT_VAL_TXT
          FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
         WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
               AND dcp1.DS_CTAC_PNT_TP_CD = dcpt.DS_CTAC_PNT_TP_CD
               AND DS_CTAC_PNT_ACTV_FLG = 'Y'
               AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK'), 5, 3)
          phone2 ,
      SUBSTR ( (SELECT DS_CTAC_PNT_VAL_TXT
          FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
         WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
               AND dcp1.DS_CTAC_PNT_TP_CD = dcpt.DS_CTAC_PNT_TP_CD
               AND DS_CTAC_PNT_ACTV_FLG = 'Y'
               AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK'), 9, 4)
          phone3,
       (SELECT DS_CTAC_PSN_EXTN_NUM
          FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
         WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
               AND dcp1.DS_CTAC_PNT_TP_CD = dcpt.DS_CTAC_PNT_TP_CD
               AND DS_CTAC_PNT_ACTV_FLG = 'Y'
               AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK') extn,          
          smcp.CTAC_PSN_CMNT_TXT MESSAGE
          INTO
                lv_contact,                
                l_email_address,
                l_cust_phone1,
                l_cust_phone2,
                l_cust_phone3,
                l_cust_tel_ext,                
                l_special_message               
  FROM SVC_MACH_CTAC_PSN smcp,
       SVC_CTAC_TP sct,
       DS_CTAC_PNT dcp,
       CTAC_PSN cp
 WHERE     SVC_MACH_MSTR_PK = fr_cur_mach_details.svc_mach_mstr_pk
       AND sct.SVC_CTAC_TP_CD = smcp.SVC_CTAC_TP_CD
       AND SVC_CTAC_TP_NM = 'Svc-Key Operator'
       AND smcp.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
       AND cp.CTAC_PSN_PK = dcp.CTAC_PSN_PK
       AND dcp.DS_CTAC_PNT_ACTV_FLG = 'Y'
       AND smcp.GLBL_CMPY_CD = 'ADB'
       AND cp.GLBL_CMPY_CD = 'ADB'
       AND dcp.GLBL_CMPY_CD = 'ADB'
       AND smcp.GLBL_CMPY_CD = 'ADB'
       AND cp.EZCANCELFLAG = 0
       AND dcp.EZCANCELFLAG = 0
       AND smcp.EZCANCELFLAG = 0
       AND NVL (smcp.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
              TO_CHAR (SYSDATE, 'YYYYMMDD')
       AND NVL (smcp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
              TO_CHAR (SYSDATE, 'YYYYMMDD')
       AND rownum<2;    
               
      EXCEPTION
         WHEN OTHERS
         THEN
         --   l_cust_tel_num      := NULL;
            l_cust_tel_ext      := NULL;
            l_email_address     := NULL;
            l_special_message   := NULL;
            lv_contact          := NULL;
            l_cust_phone1       :=NULL;
            l_cust_phone2       :=NULL;
            l_cust_phone3       :=NULL;
      END;

      l_weekday_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'MON-FRI:',
            p_time1       => fr_cur_mach_details.biz_hrs_mon_fri_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_mon_fri_to_tm,
            p_separator   => '-');

      l_sat_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'SAT:',
            p_time1       => fr_cur_mach_details.biz_hrs_sat_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_sat_to_tm,
            p_separator   => '-');

      l_sun_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'SUN:',
            p_time1       => fr_cur_mach_details.biz_hrs_sun_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_sun_to_tm,
            p_separator   => '-');
  lv_sell_to_cust_cd:=fr_cur_mach_details.sell_to_cust_cd;
      l_rec_mach :=
         CANON_E307_MAC_SER_REC (fr_cur_mach_details.svc_mach_mstr_pk,
                                 fr_cur_mach_details.model,
                                 fr_cur_mach_details.ser_num,
                                 fr_cur_mach_details.cust_mach_ctrl_num,
                                 fr_cur_mach_details.solution_name,
                                 fr_cur_mach_details.ship_to_acct_no,
                                 fr_cur_mach_details.ship_to_cust_name,
                                 fr_cur_mach_details.address_1,
                                 fr_cur_mach_details.address_2,
                                 NULL,
                                 fr_cur_mach_details.city,
                                 fr_cur_mach_details.state,
                                 fr_cur_mach_details.post_cd,
                                 fr_cur_mach_details.ownr_acct_num,
                                 fr_cur_mach_details.bill_to_cust_cd,
                                 fr_cur_mach_details.sell_to_cust_cd,
                                 fr_cur_mach_details.cur_loc_num,
                                 fr_cur_mach_details.cur_loc_acct_num,
                                 l_weekday_hours,
                                 l_sat_hours,
                                 l_sun_hours,
                                 fr_cur_mach_details.last_svc_call_dt,
                                 fr_cur_mach_details.tot_svc_visit_cnt,
                                 fr_cur_mach_details.last_tech_visit_dt,
                                 fr_cur_mach_details.prf_tech_cd,
                                 fr_cur_mach_details.req_tech_cd,
                                 fr_cur_mach_details.fld_svc_br_cd,
                                 l_email_address,
                                 l_cust_tel_num,
                                 l_cust_tel_ext,
                                 l_cust_phone1,
                                 l_cust_phone2,
                                 l_cust_phone3,
                                  NULL,                      --- l_caller     ,
                                 l_special_message,
                                 l_special_message_type,
                                 lv_contact                                --Contact 
                                   );
      o_mach_tbl.EXTEND ();
      o_mach_tbl (ln_mach_rec_cnt) := l_rec_mach;
      ln_mach_rec_cnt := ln_mach_rec_cnt + 1;
   END LOOP;

   --Start Fetching UGW error Codes
 /*  FOR fr_cur_ugw_err_code IN cur_ugw_err_code
   LOOP
      l_rec_ugw :=
         CANON_E307_UGW_ERR_CODE_REC (fr_cur_ugw_err_code.UGW_ERR_CODE);
      o_ugw_tbl.EXTEND ();
      o_ugw_tbl (ln_ugw_rec_cnt) := l_rec_ugw;
      ln_ugw_rec_cnt := ln_ugw_rec_cnt + 1;
   END LOOP;*/
   BEGIN
   GET_UGW_ERR_CODE(p_in_serial,x_ugw_tbl);
   o_ugw_tbl:=x_ugw_tbl;
   EXCEPTION WHEN OTHERS THEN
   NULL;
   END;
   --Start fetching Problem Details
   FOR fr_cur_prob_code IN cur_prob_code
   LOOP
      l_rec_prob :=
         CANON_E307_PROB_CODE_REC (fr_cur_prob_code.TYPE,
                                   fr_cur_prob_code.Description,
                                   fr_cur_prob_code.Code,
                                   fr_cur_prob_code.Other,
                                   fr_cur_prob_code.Machine_Status);
      o_prob_tbl.EXTEND ();
      o_prob_tbl (ln_prob_rec_cnt) := l_rec_prob;
      ln_prob_rec_cnt := ln_prob_rec_cnt + 1;
   END LOOP;


   ----Start fetching call information Details
   FOR fr_cur_call_info IN cur_call_info
   LOOP
  -- debug_pkg.debug_proc('Inside cur_call_info');
   BEGIN
      global_level_recall (p_in_serial,
                           p_in_model,
                           x_call_type,
                           x_call_type_id);
    EXCEPTION WHEN OTHERS
    THEN
    x_call_type:=NULL;
    x_call_type_id:=NULL;
    END;                           
  --  debug_pkg.debug_proc('After global recall');
    BEGIN
   get_equip_branch(p_in_serial,lv_br_cd,lv_br_desc);
   EXCEPTION WHEN OTHERS
       THEN
       lv_br_cd:=NULL;
       lv_br_desc:=NULL;
    END; 
    BEGIN
   lv_po_flag:=GET_PO_REQ_FLG(p_in_serial,lv_sell_to_cust_cd);
  -- debug_pkg.debug_proc('lv_po_flag= '||lv_po_flag);
   EXCEPTION WHEN OTHERS
          THEN
          lv_po_flag:=NULL;
    END;
      l_rec_call_info :=
         CANON_E307_CALL_INFO_REC (fr_cur_call_info.creation_channel,
                                   fr_cur_call_info.creation_channel_cd,
                                   x_call_type, -- fr_cur_call_info.task_type_name,
                                   x_call_type_id, --fr_cur_call_info.task_type_code,
                                   '', --fr_cur_call_info.bill_code, not needed
                                   '', --fr_cur_call_info.bill_code_name, not needed
                                   lv_po_flag,
                                   '',
                                   fr_cur_call_info.line_of_business,
                                  -- fr_cur_call_info.branch,
                                  -- fr_cur_call_info.branch_cd,
                                   lv_br_desc,
                                   lv_br_cd,
                                   fr_cur_call_info.ah_task_type,
                                   fr_cur_call_info.ah_task_code);
      o_call_info_tbl.EXTEND ();
      o_call_info_tbl (ln_call_rec_cnt) := l_rec_call_info;
      ln_call_rec_cnt := ln_call_rec_cnt + 1;
    --  debug_pkg.debug_proc('line_of_business = '||fr_cur_call_info.line_of_business);
   END LOOP;

   
   --Get Contract Details
   BEGIN
   GET_CONTRACT_INFO(p_in_serial,lv_ds_contr_pk,lv_ds_contr_dtl_pk,x_contract_details_tbl);
   o_contract_details_tbl:=x_contract_details_tbl;
   EXCEPTION WHEN OTHERS THEN
   NULL;
   END;

  
   BEGIN
   --debug_pkg.debug_proc('Calling GET_CUR_LOCATION');
      GET_CUR_LOCATION(p_in_serial,x_cust_loc_tbl);
      o_cust_loc_tbl:=x_cust_loc_tbl;
   EXCEPTION WHEN OTHERS THEN
   --   debug_pkg.debug_proc('Inside Exp1 '||sqlerrm);
      NULL;
   END;
 
   BEGIN
         GET_BILL_TO_LOCATION(p_in_serial,x_bill_to_tbl);
         o_bill_to_tbl:=x_bill_to_tbl;
   EXCEPTION WHEN OTHERS THEN
    -- debug_pkg.debug_proc('Inside Exp2 '||sqlerrm);
         NULL;
   END;
   FOR fr_cur_notes IN cur_notes (lv_ds_contr_pk,lv_ds_contr_dtl_pk)
   LOOP
      l_rec_notes :=
         CANON_E307_DEBRIEF_NOTE_REC (fr_cur_notes.Note_Source_Id,
                                      fr_cur_notes.Note_Id,
                                      fr_cur_notes.Note_Type,
                                      CANON_E307_UTILS.format_date2_func(fr_cur_notes.Note_Date, 'FORMAT2'),
                                      fr_cur_notes.Note_Text,
                                      fr_cur_notes.Created_By);
      o_notes_tbl.EXTEND ();
      o_notes_tbl (ln_note_rec_cnt) := l_rec_notes;
      ln_note_rec_cnt := ln_note_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_CALL_DETAILS;

/*******************************************************************************************
 Procedure Name: CUST_NAME_LOV
 Description: Get debrief labor details of Task to be displayed in ASCC
 Input Parameters: p_in_cust_name
            p_start
           p_end
           p_in_sort_by
           p_in_sort_order
           
 Output Parameters: x_count
             o_cust_name_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE CUST_NAME_LOV (
   p_in_cust_name    IN     VARCHAR2,
   p_in_acct_num     IN     VARCHAR2,    
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_cust_name_tbl      OUT CANON_E307_CUST_NAME_LOV_TBL)
IS
   l_rec_cust_name    CANON_E307_CUST_NAME_LOV_REC;
   v_sql              VARCHAR2 (32000);
   v_sql1              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   v_cust_cursor      cur_typ;
   lv_acct_num        VARCHAR2 (30);
   lv_acct_name       VARCHAR2 (500);
   ln_rec_cnt1        NUMBER := 1;
   l_order_by         VARCHAR2 (100);
   l_asc_desc_order   VARCHAR2 (100);
   lv_cust_nm          VARCHAR2 (500);
   lv_account          VARCHAR2 (30);
BEGIN
--debug_pkg.debug_proc ('p_in_sort_by '||p_in_sort_by);
--debug_pkg.debug_proc ('p_in_sort_order '||p_in_sort_order);
   o_cust_name_tbl := CANON_E307_CUST_NAME_LOV_TBL ();
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;
   lv_cust_nm :=trim(p_in_cust_name);
   lv_account :=trim(p_in_acct_num);
   l_default_from :=
         ' FROM (SELECT sell_to.* '
      || 'FROM sell_to_cust sell_to '
      || 'where upper(LOC_NM) like upper( '
      || '''%'
      || lv_cust_nm
      || '%'' ) '
      || ' AND SELL_TO_CUST_CD like '
      || '''%'
      ||lv_account
      || '%'''
      || ' AND NVL (sell_to.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || ' AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
      || 'AND sell_to.EZCANCELFLAG= '
      || g_cancel_flg
      || ' AND sell_to.glbl_cmpy_cd ='''
      || g_glbl_cmpy_cd
      || '''  ';

   v_sql1 :=
         'SELECT loc_nm, sell_to_cust_cd,rownum row_num '
      || l_default_from ;

   IF l_order_by IS NOT NULL
   THEN
      v_sql1 := v_sql1 || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||')';
   ELSE
      v_sql1 := v_sql1 || ' ORDER BY LOC_NM'||')';
   END IF;
   
   v_sql := 'SELECT LOC_NM, SELL_TO_CUST_CD FROM( '
        ||    v_sql1
         || ' )  WHERE row_num BETWEEN '
         || p_start
         || ' AND '
      || p_end;
      
    
   
--debug_pkg.debug_proc ('v_sql '||v_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from||')';
--debug_pkg.debug_proc ('l_sql_count_qry '||l_sql_count_qry);
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_cust_cursor FOR v_sql;

   LOOP
       FETCH v_cust_cursor
         INTO lv_acct_name, lv_acct_num;
--debug_pkg.debug_proc ('lv_acct_name '||lv_acct_name);
--debug_pkg.debug_proc ('lv_acct_num '||lv_acct_num);
      EXIT WHEN v_cust_cursor%NOTFOUND;
      l_rec_cust_name :=
         CANON_E307_CUST_NAME_LOV_REC (lv_acct_name, lv_acct_num);
      o_cust_name_tbl.EXTEND ();
      o_cust_name_tbl (ln_rec_cnt1) := l_rec_cust_name;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END CUST_NAME_LOV;

/*******************************************************************************************
 Procedure Name: CUST_ADDR_LOV
 Description: Get debrief labor details of Task to be displayed in ASCC
 Input Parameters: p_in_type
            p_in_cust_code
            p_in_address
            p_start
           p_end
           p_in_sort_by
           p_in_sort_order

 Output Parameters: x_count
             o_cust_addr_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE CUST_ADDR_LOV (p_in_type         IN     VARCHAR2,
                         p_in_cust_code    IN     VARCHAR2,
                         p_in_address      IN     VARCHAR2,
                         p_start           IN     NUMBER,
                         p_end             IN     NUMBER,
                         p_in_sort_by      IN     VARCHAR2,
                         p_in_sort_order   IN     VARCHAR2,
                         x_count              OUT NUMBER,
                         o_cust_addr_tbl      OUT CANON_E307_CUST_LOC_TBL)
IS
   l_rec_cust_addr    CANON_E307_CUST_LOC_REC;
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   v_addr_cursor      cur_typ;
   lv_cust_cd         VARCHAR2 (100);
   lv_cust_name       VARCHAR2 (1000);
   lv_address         VARCHAR2 (300);
   lv_city            VARCHAR2 (100);
   lv_state           VARCHAR2 (100);
   lv_postal_code     VARCHAR2 (100);
   lv_country         VARCHAR2 (100);
   lv_payment_term    VARCHAR2 (100);
   ln_rec_cnt1        NUMBER := 1;
   l_order_by         VARCHAR2 (100);
   l_asc_desc_order   VARCHAR2 (100);
   lv_address1        VARCHAR2 (1000);
BEGIN
   o_cust_addr_tbl := CANON_E307_CUST_LOC_TBL ();
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;
   lv_address1 := TRIM (p_in_address);

   IF UPPER (p_in_type) = 'BILLTO'
   THEN
      IF lv_address1 IS NOT NULL
      THEN
         l_default_from :=
               ' FROM (SELECT bill_to.* '
            || ' FROM ds_bill_to_cust bill_cust,bill_to_cust bill_to '
            || ' where 1=1 '
            || ' AND bill_cust.bill_to_cust_pk = bill_to.bill_to_cust_pk '
            || ' AND upper(bill_cust.sell_to_cust_cd) = '''
            || p_in_cust_code
            || ''' AND (upper(first_line_addr) like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(CTY_ADDR)like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(ST_CD)like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(POST_CD)like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(CTRY_CD)like upper( '
            || '''%'
            || lv_address1
            || '%'')) '
            || ' AND NVL (bill_to.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || ' AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || 'AND bill_cust.EZCANCELFLAG= '
            || g_cancel_flg
            || 'AND bill_to.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND bill_cust.glbl_cmpy_cd = '''
            || g_glbl_cmpy_cd
            || ''' AND bill_to.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || '''  ';
      ELSE
         l_default_from :=
               ' FROM (SELECT  bill_to.* '
            || ' FROM ds_bill_to_cust bill_cust,bill_to_cust bill_to '
            || ' where 1=1 '
            || ' AND bill_cust.bill_to_cust_pk = bill_to.bill_to_cust_pk '
            || ' AND upper(bill_cust.sell_to_cust_cd) = '''
            || p_in_cust_code
            || ''' AND NVL (eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || ' AND NVL (eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || 'AND bill_cust.EZCANCELFLAG= '
            || g_cancel_flg
            || 'AND bill_to.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND bill_cust.glbl_cmpy_cd = '''
            || g_glbl_cmpy_cd
            || ''' AND bill_to.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || '''  ';
      END IF;

      v_sql :=
            ' SELECT bill_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
         || ' POST_CD, CTRY_CD, '''' PAYMENT_TERM ,rownum row_num '
         || l_default_from;


      IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ')';
      ELSE
         v_sql := v_sql || ' ORDER BY LOC_NM' || ')';
      END IF;

      v_sql :=
            'SELECT bill_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
         || ' POST_CD, CTRY_CD, '''' PAYMENT_TERM FROM( '
         || v_sql
         || ' )  WHERE row_num BETWEEN '
         || p_start
         || ' AND '
         || p_end;
    --  debug_pkg.debug_proc ('v_sql1= ' || v_sql);
      l_sql_count_qry := ' select count(*) ' || l_default_from || ')';
    --  debug_pkg.debug_proc ('l_sql_count_qry ' || l_sql_count_qry);
      --  EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
    --  debug_pkg.debug_proc ('x_count ' || x_count);
   ELSIF UPPER (p_in_type) = 'CURLOC'
   THEN
      IF lv_address1 IS NOT NULL
      THEN
         l_default_from :=
               ' FROM (SELECT ship_to.* '
            || ' FROM SHIP_TO_CUST ship_to '
            || ' where upper(ship_to.sell_to_cust_cd) = '''
            || p_in_cust_code
            || ''' AND (upper(first_line_addr) like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(CTY_ADDR)like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(ST_CD)like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(POST_CD)like upper( '
            || '''%'
            || lv_address1
            || '%'') '
            || ' OR upper(CTRY_CD)like upper( '
            || '''%'
            || lv_address1
            || '%'')) '
            || ' AND NVL (eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || ' AND NVL (eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || 'AND ship_to.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ship_to.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || '''  ';
      ELSE
         l_default_from :=
               ' FROM (SELECT ship_to.* '
            || ' FROM SHIP_TO_CUST ship_to '
            || ' where upper(ship_to.sell_to_cust_cd) = '''
            || p_in_cust_code
            || ''' AND NVL (eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || ' AND NVL (eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || ' AND ship_to.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ship_to.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || '''  ';
      END IF;

      v_sql :=
            ' SELECT ship_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
         || ' POST_CD, CTRY_CD, '''' PAYMENT_TERM ,rownum row_num '
         || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ')';
      ELSE
         v_sql := v_sql || ' ORDER BY LOC_NM' || ')';
      END IF;

     v_sql := 
            ' SELECT ship_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
         || ' POST_CD, CTRY_CD, '''' PAYMENT_TERM FROM( '
         || v_sql
         || ' ) WHERE row_num BETWEEN '
         || p_start
         || ' AND '
         || p_end;
    --  debug_pkg.debug_proc ('v_sql1= ' || v_sql);
      l_sql_count_qry := ' select count(*) ' || l_default_from || ')';
    --  debug_pkg.debug_proc ('l_sql_count_qry ' || l_sql_count_qry);
   
   --debug_pkg.debug_proc('v_sql= '||v_sql);
   END IF;

   /* IF l_order_by IS NOT NULL
    THEN
       v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
    ELSE
       v_sql := v_sql || ' ORDER BY first_line_addr';
    END IF;*/

   --l_sql_count_qry := ' select count(*) ' || l_default_from;

   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

  -- debug_pkg.debug_proc ('x_count ' || x_count);

   OPEN v_addr_cursor FOR v_sql;

   LOOP
   --   debug_pkg.debug_proc ('Inside Loop');

      FETCH v_addr_cursor
         INTO lv_cust_cd,
              lv_cust_name,
              lv_address,
              lv_city,
              lv_state,
              lv_postal_code,
              lv_country,
              lv_payment_term;

      EXIT WHEN v_addr_cursor%NOTFOUND;
      l_rec_cust_addr :=
         CANON_E307_CUST_LOC_REC (lv_cust_cd,
                                  lv_cust_name,
                                  lv_address,
                                  lv_city,
                                  lv_state,
                                  lv_postal_code,
                                  lv_country,
                                  lv_payment_term);
      o_cust_addr_tbl.EXTEND ();
      o_cust_addr_tbl (ln_rec_cnt1) := l_rec_cust_addr;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END CUST_ADDR_LOV;

/*******************************************************************************************
Procedure Name: SR_CHANNEL_LOV
Description: Get SR Channel LOV details to be displayed in ASCC
Input Parameters: None

Output Parameters: o_sr_channel_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE SR_CHANNEL_LOV (o_sr_channel_tbl OUT CANON_E307_SR_CHANNEL_TBL)
IS
   l_rec_sr_channel   CANON_E307_SR_CHANNEL_REC;

   CURSOR cur_sr_channel
   IS
        SELECT DISTINCT
               svc_call_src_tp_cd call_src_code,
               svc_call_src_tp_nm call_src_name
          FROM svc_call_src_tp
         WHERE glbl_cmpy_cd = g_glbl_cmpy_cd AND ezcancelflag = g_cancel_flg
      ORDER BY SVC_CALL_SRC_TP_SORT_NUM;

   ln_rec_cnt         NUMBER := 1;
BEGIN
   o_sr_channel_tbl := CANON_E307_SR_CHANNEL_TBL ();

   FOR fr_cur_sr_channel IN cur_sr_channel
   LOOP
      l_rec_sr_channel :=
         CANON_E307_SR_CHANNEL_REC (fr_cur_sr_channel.CALL_SRC_CODE,
                                    fr_cur_sr_channel.CALL_SRC_NAME);
      o_sr_channel_tbl.EXTEND ();
      o_sr_channel_tbl (ln_rec_cnt) := l_rec_sr_channel;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END SR_CHANNEL_LOV;

/*******************************************************************************************
Procedure Name: REMEDY_DETAILS
Description: Get remedy details to be displayed in ASCC
Input Parameters: p_in_model
          p_in_pblm_code

Output Parameters: o_remedy_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE REMEDY_DETAILS (p_in_model       IN     VARCHAR2,
                          p_in_pblm_code   IN     VARCHAR2,
                          o_remedy_tbl        OUT CANON_E307_REMEDY_TBL)
IS
   l_rec_remedy   CANON_E307_REMEDY_REC;

   CURSOR cur_remedy
   IS
      SELECT DISTINCT
             NVL (svc_pblm_narr_txt, canon_e307_constants.g_nar_txt) SVC_PBLM_NARR_TXT,
             NVL (svc_rmd_txt, canon_e307_constants.g_rmd_txt) SVC_RMD_TXT
        FROM svc_rmd
       WHERE     mdl_nm = p_in_model
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND SVC_PBLM_TP_CD = p_in_pblm_code;

   ln_rec_cnt     NUMBER := 1;
BEGIN
   o_remedy_tbl := CANON_E307_REMEDY_TBL ();

   FOR fr_cur_remedy IN cur_remedy
   LOOP
      l_rec_remedy :=
         CANON_E307_REMEDY_REC (fr_cur_remedy.SVC_PBLM_NARR_TXT,
                                fr_cur_remedy.SVC_RMD_TXT);
      o_remedy_tbl.EXTEND ();
      o_remedy_tbl (ln_rec_cnt) := l_rec_remedy;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END REMEDY_DETAILS;

/*******************************************************************************************
Procedure Name: GET_OPEN_SR_NUM
Description: Get number of open SRs for the machine
Input Parameters: p_in_serial

Output Parameters: o_sr_num
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_OPEN_SR_NUM (p_in_serial IN VARCHAR2, o_sr_num OUT VARCHAR2)
IS
BEGIN
   SELECT MAX (fsr_num)
     INTO o_sr_num
     FROM SVC_TASK st, SVC_TASK_STS sts
    WHERE     st.ser_num = p_in_serial                            --'DFL08260'
          AND st.SVC_TASK_STS_CD = sts.SVC_TASK_STS_CD
          AND UPPER (SVC_TASK_STS_NM) NOT IN
                 ('CANCELLED', 'COMPLETED', 'CUSTOMER CANCELLED', 'CLOSED');
EXCEPTION
   WHEN OTHERS
   THEN
      o_sr_num := NULL;
END GET_OPEN_SR_NUM;

/*******************************************************************************************
Procedure Name: GET_CALL_AVOIDANCE
Description: Get Call Avoidance details to be displayed in ASCC
Input Parameters: None

Output Parameters: o_call_avoid_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_CALL_AVOIDANCE (o_call_avoid_tbl OUT CANON_E307_CALL_AVOID_TBL)
IS
   l_rec_callavoid   CANON_E307_CALL_AVOID_REC;

   CURSOR cur_callavoid
   IS
      SELECT DISTINCT svc_call_avoid_cd, svc_call_avoid_nm
        FROM svc_call_avoid
       WHERE SVC_CALL_AVOID_CD <> '10' AND glbl_cmpy_cd = g_glbl_cmpy_cd;

   ln_rec_cnt        NUMBER := 1;
BEGIN
   o_call_avoid_tbl := CANON_E307_CALL_AVOID_TBL ();

   FOR fr_cur_callavoid IN cur_callavoid
   LOOP
      l_rec_callavoid :=
         CANON_E307_CALL_AVOID_REC (fr_cur_callavoid.svc_call_avoid_cd,
                                    fr_cur_callavoid.svc_call_avoid_nm);
      o_call_avoid_tbl.EXTEND ();
      o_call_avoid_tbl (ln_rec_cnt) := l_rec_callavoid;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_CALL_AVOIDANCE;

/*******************************************************************************************
Procedure Name: GET_CALL_SUMMARY
Description: Get SR summary details to be displayed in ASCC
Input Parameters: p_in_sr_num

Output Parameters: o_mach_tbl
           o_sr_info_tbl
           o_contract_details_tbl
           o_task_info_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_CALL_SUMMARY (
   p_in_sr_num                  IN     VARCHAR2,
   p_in_task_num                IN     VARCHAR2,
   o_mach_tbl                   OUT CANON_E307_MAC_SER_TBL,
   o_sr_info_tbl                OUT CANON_E307_SR_INFO_TBL,
   o_contract_details_tbl       OUT CANON_E307_CONTRACT_TBL,
   o_task_info_tbl              OUT CANON_E307_TASK_INFO_TBL,
   o_notes_tbl                  OUT CANON_E307_DEBRIEF_NOTE_TBL)
IS
   l_rec_mach                       CANON_E307_MAC_SER_REC;
   l_rec_sr                         CANON_E307_SR_INFO_REC;
   l_rec_contract_details           CANON_E307_CONTRACT_REC;
   l_rec_task                       CANON_E307_TASK_INFO_REC;
   l_rec_notes                      CANON_E307_DEBRIEF_NOTE_REC;
   x_contract_details_tbl CANON_E307_CONTRACT_TBL;
   CURSOR cur_mach_details(lv_sr_num IN VARCHAR2)
   IS
      SELECT smm.svc_mach_mstr_pk,                                
             fsr_num,
             mdl.T_MDL_NM model,
             fsr.SER_NUM ser_num,
             smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
             config.svc_sln_nm Solution_Name,
             smm.cur_loc_acct_num ship_to_acct_no,
             ship_to.loc_nm ship_to_cust_name,
             ship_to.first_line_addr address_1,
             ship_to.scd_line_addr address_2,
             ship_to.cty_addr city,
             ship_to.st_cd state,
             ship_to.post_cd,
             smm.ownr_acct_num,
             smm.bill_to_cust_cd,
             ship_to.sell_to_cust_cd,
             smm.cur_loc_num,
             smm.cur_loc_acct_num,
             fsr.fsr_crat_dt,
             fsr.fsr_crat_tm,
             fsr.svc_call_incdt_dt,
             fsr.svc_call_incdt_tm,
             fsr.CUST_CSE_NUM customer_case_no,
             smm.biz_hrs_mon_fri_from_tm,
             smm.biz_hrs_mon_fri_to_tm,
             smm.biz_hrs_sat_from_tm,
             smm.biz_hrs_sat_to_tm,
             smm.biz_hrs_sun_from_tm,
             smm.biz_hrs_sun_to_tm,
             smm.last_svc_call_dt,
             smm.tot_svc_visit_cnt,
             smm.last_tech_visit_dt,
             smm.prf_tech_cd,
             smm.req_tech_cd,
             smm.fld_svc_br_cd
        FROM FSR fsr,
             svc_mach_mstr smm,
             SVC_CONFIG_MSTR config,
             MDL_NM mdl,SHIP_TO_CUST ship_to
       WHERE     1 = 1           --st.fsr_num                  =   fsr.fsr_num
             AND fsr.svc_mach_mstr_pk = smm.svc_mach_mstr_pk(+)
             AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK(+)
             AND NVL (FSR_NUM, 'X') LIKE '%' || lv_sr_num ||'%' 
            /* and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND nvl(config.glbl_cmpy_cd,g_glbl_cmpy_cd) = g_glbl_cmpy_cd
             AND nvl(smm.glbl_cmpy_cd,g_glbl_cmpy_cd) = g_glbl_cmpy_cd
             AND ship_to.ship_to_cust_cd(+) =smm.cur_loc_num
                            AND NVL (ship_to.eff_thru_dt, SYSDATE + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND mdl.T_MDL_ID(+) = config.MDL_ID;

   CURSOR cur_sr_details(lv_sr_num IN VARCHAR2)
   IS
      SELECT fsr_num,
             fsr_sts_cd,
             tech_cd,
             fsr_crat_dt,
             fsr_crat_tm,
             ezuptime last_upt_dt,
             get_psn_nm(ezinuserid) ezinusernm,
             fsr_cplt_dt,
             fsr_cplt_tm,
             bill_to_cust_cd,
             sell_to_cust_cd,
             ship_to_cust_cd,
             pmt_term_cash_disc_cd pmt_term,
             svc_call_src_tp_cd,
             svc_pblm_tp_cd,
             po_ver_flg,
             cust_cse_num,
             itt_num,
             fsr_tp_cd,
             fsr_clo_dt,
             fsR_clo_tm,
             svc_mach_mstr_pk,
             ser_num,
             svc_call_rqst_ownr_toc_cd,
             ship_to_cust_upd_flg,
             ship_to_upd_cust_cd,
             bill_to_cust_upd_flg,
             bill_to_upd_cust_cd
        FROM FSR
       WHERE fsr.fsr_num = lv_sr_num AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd;

  CURSOR cur_skill(lv_model IN VARCHAR2)
   IS
        select SVC_SKILL_NM from ds_mdl mdl, SVC_SKILL sk
        where mdl_desc_txt = lv_model
        AND sk.SVC_SKILL_NUM = mdl.SVC_SKILL_NUM
        AND mdl.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mdl.ezcancelflag = g_cancel_flg
        AND sk.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND sk.ezcancelflag = g_cancel_flg;

 /*  CURSOR cur_contract_details
   IS
      SELECT fsr.sell_to_cust_cd,
             cont_header.ds_contr_num contract_number,
             cont_type.ds_contr_tp_nm contract_type,
             cont_header.ds_contr_pk,
             cont_header.contr_vrsn_eff_from_dt header_start_date,
             cont_header.contr_vrsn_eff_thru_dt header_end_date,
             cont_detail.contr_eff_from_dt line_start_date,
             cont_detail.contr_eff_thru_dt line_end_date,
             cont_hdr_sts.ds_contr_sts_nm header_status,
             cont_det_sts.ds_contr_dtl_sts_nm line_status,
             cont_detail.rsp_tm_up_mn_aot,
             cont_detail.ds_contr_dtl_pk
        FROM fsr,
             svc_mach_mstr ib,
             ds_contr_dtl cont_detail,
             ds_contr cont_header,
             ds_contr_tp cont_type,
             ds_contr_sts cont_hdr_sts,
             ds_contr_dtl_sts cont_det_sts
       WHERE fsr.svc_mach_mstr_pk = ib.svc_mach_mstr_pk                       --'HHOZDYYHSH'
             AND ib.svc_mach_mstr_pk = cont_detail.svc_mach_mstr_pk
             AND cont_detail.DS_CONTR_PK = cont_header.DS_CONTR_PK
             AND fsr.fsr_num = p_in_sr_num
            /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             /*AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND cont_detail.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.DS_CONTR_TP_CD = cont_type.DS_CONTR_TP_CD
             AND cont_header.DS_CONTR_STS_CD = cont_hdr_sts.DS_CONTR_STS_CD
             AND cont_detail.DS_CONTR_DTL_STS_CD =
                    cont_det_sts.DS_CONTR_DTL_STS_CD
             AND cont_type.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_hdr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_det_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_detail.ezcancelflag = g_cancel_flg
             AND cont_header.ezcancelflag = g_cancel_flg
             AND cont_type.ezcancelflag = g_cancel_flg
             AND cont_hdr_sts.ezcancelflag = g_cancel_flg
             AND cont_det_sts.ezcancelflag = g_cancel_flg;*/

   CURSOR cur_task_details(lv_sr_num IN VARCHAR2)
   IS
      SELECT task.svc_task_num,
             task.fsr_num,
             task.ds_svc_call_tp_cd,
             task.svc_task_sts_cd,
             task.ezintime creat_dt,
             --CANON_E307_UTILS.format_date2_func(task.ezintime, 'FORMAT2') creat_dt,
             task.erl_start_ts early_start,
             task.late_start_ts late_start
        FROM svc_task task
       WHERE     task.fsr_num = lv_sr_num
             AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
             ORDER BY task.svc_task_num desc;

 CURSOR cur_notes(lv_sr_num IN VARCHAR2,lv_ser_num IN VARCHAR2,lv_ds_contr_pk IN VARCHAR2,lv_ds_contr_dtl_pk IN VARCHAR2)
       IS
          SELECT '' Note_Source_Id,
       '' Note_Id,
       note_type.svc_memo_tp_nm Note_Type,
       CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
       note.SVC_CMNT_TXT Note_Text,
       GET_PSN_NM(note.ezinuserid) Created_By
  FROM SVC_MACH_MSTR ib, SVC_MEMO note, SVC_MEMO_TP note_type,SVC_MEMO_CATG CATEGORY
 WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
       AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
       AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
       AND category.svc_memo_catg_nm='Machine Memo'
       AND ib.ser_num = lv_ser_num
       /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')
                       AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
       AND note.svc_task_num is null
       and note.fsr_num is null
       and note.ds_contr_pk is null
       and note.ds_contr_dtl_pk is null
UNION
SELECT '' Note_Source_Id,
       '' Note_Id,
       note_type.svc_memo_tp_nm Note_Type,
       CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
       note.SVC_CMNT_TXT Note_Text,
       GET_PSN_NM(note.ezinuserid) Created_By
  FROM SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
 WHERE  note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
       AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
       AND category.svc_memo_catg_nm='Contract Memo'
       AND note.ds_contr_pk = lv_ds_contr_pk
       AND note.SVC_TASK_NUM IS NULL
       AND note.FSR_NUM IS NULL
       AND note.SVC_MACH_MSTR_PK IS NULL
       AND note.DS_CONTR_DTL_PK IS NULL
UNION
SELECT '' Note_Source_Id,
       '' Note_Id,
       note_type.svc_memo_tp_nm Note_Type,
       CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
       note.SVC_CMNT_TXT Note_Text,
       GET_PSN_NM(note.ezinuserid) Created_By
  FROM  SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
 WHERE note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
       AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
       AND category.svc_memo_catg_nm='Contract Memo'
       AND note.ds_contr_dtl_pk  = lv_ds_contr_dtl_pk
       AND note.SVC_TASK_NUM IS NULL
       AND note.FSR_NUM IS NULL
       AND note.SVC_MACH_MSTR_PK IS NULL
       AND note.DS_CONTR_PK IS NULL
UNION
SELECT '' Note_Source_Id,
       '' Note_Id,
       note_type.svc_memo_tp_nm Note_Type,
       CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
       note.SVC_CMNT_TXT Note_Text,
       GET_PSN_NM(note.ezinuserid) Created_By
  FROM SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
 WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
       AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
       AND category.svc_memo_catg_nm='Dispatch Memo'
       AND  note.fsr_num=lv_sr_num
       AND note.SVC_MACH_MSTR_PK IS NULL
       AND note.DS_CONTR_PK IS NULL
       AND note.DS_CONTR_DTL_PK IS NULL;
 
    ln_mach_rec_cnt                  NUMBER := 1;
   ln_sr_rec_cnt                    NUMBER := 1;
   ln_cust_rec_cnt                  NUMBER := 1;
   ln_contract_rec_cnt              NUMBER := 1;
   ln_task_rec_cnt                  NUMBER := 1;
   ln_note_rec_cnt          NUMBER := 1;
   l_latest_task_num                VARCHAR2 (30);
   lv_sr_num                VARCHAR2 (30);
   lv_task_num1                VARCHAR2 (30);
   l_email_address                  VARCHAR2 (50);
   l_contact                    VARCHAR2 (50);
   l_caller                         VARCHAR2 (50);
   l_cust_tel_num                   VARCHAR2 (30);
   l_cust_tel_extn                  VARCHAR2 (30);
   l_incident_date_time_formatted   VARCHAR2 (30);
   l_weekday_hours                  VARCHAR2 (30);
   l_sat_hours                      VARCHAR2 (30);
   l_sun_hours                      VARCHAR2 (30);
   l_resp_time                      VARCHAR2 (30);
   l_resp_by_date                   VARCHAR2 (30);
   l_header_eff_string              VARCHAR2 (100);
   l_line_eff_string                VARCHAR2 (100);
   l_cur_loc_string                 VARCHAR2 (100);
   l_bill_string                    VARCHAR2 (100);
   l_sr_creation_date               VARCHAR2 (100);
   l_sr_update_date                 VARCHAR2 (100);
   l_early_start                    VARCHAR2(100);
   l_late_start                     VARCHAR2(100);
   l_creation_date                  VARCHAR2(100);
   l_sla_converted                  VARCHAR2(100);
   l_sr_sts                    VARCHAR2(100);
   lv_fsr_tp_nm                VARCHAR2(100);
   l_fsr_upd_flag              VARCHAR2(1);
   l_tsk_upd_flag              VARCHAR2(1);
   l_cust_phone1               VARCHAR2(10);
   l_cust_phone2               VARCHAR2(10);
   l_cust_phone3               VARCHAR2(10);
   l_billable_count             NUMBER;
   l_billable_flag             VARCHAR2(1);
   
   l_bill_tp_cd                     svc_task.svc_bill_tp_cd%TYPE;
   l_bill_tp_nm                     svc_bill_tp.svc_bill_tp_nm%TYPE;
   l_sr_last_upd_by                 fsr_event.FSR_EVENT_EXE_USR_ID%TYPE;
   l_pblm_tp_nm                     svc_pblm_tp.svc_pblm_tp_nm%TYPE;
   l_pblm_tp_cd                     svc_task.svc_pblm_symp_tp_cd%TYPE;
   l_call_src_tp_nm                 svc_call_src_tp.svc_call_src_tp_nm%TYPE;
   l_line_biz_tp_cd                 svc_mach_mstr.svc_by_line_biz_tp_cd%TYPE;
   l_br_desc_txt                    svc_contr_br.svc_contr_br_desc_txt%TYPE;
   l_mach_status                    svc_task.mach_down_flg%TYPE;
   l_cust_po_num                    svc_task.cust_po_num%TYPE;
   l_mach_mgr                       svc_task.svc_trty_mgr_psn_cd%TYPE;
   l_mach_mgr1                VARCHAR2(100);
   l_cur_addr_line                  ship_to_cust.FIRST_LINE_ADDR%TYPE;
   l_cust_cd                ship_to_cust.ship_to_cust_cd%TYPE;
   l_loc_nm                ship_to_cust.loc_nm%TYPE;
   l_cur_city                       ship_to_cust.cty_addr%TYPE;
   l_cur_st_cd                      ship_to_cust.st_cd%TYPE;
   l_cur_post_cd                    ship_to_cust.post_cd%TYPE;
   l_cur_ctry_cd                    ship_to_cust.ctry_cD%TYPE;
   l_bill_addr_line                 bill_to_cust.first_line_addr%TYPE;
   l_bill_cust_cd            bill_to_cust.bill_to_cust_cd%TYPE;
   l_bill_loc_nm            bill_to_cust.loc_nm%TYPE;    
   l_bill_city                      bill_to_cust.cty_addr%TYPE;
   l_bill_st_cd                     bill_to_cust.st_cd%TYPE;
   l_bill_post_cd                   bill_to_cust.post_cd%TYPE;
   l_bill_ctry_cd                   bill_to_cust.ctry_cd%TYPE;
   l_call_tp_nm                     ds_svc_call_tp.ds_svc_call_tp_nm%TYPE;
   l_svc_task_sts_nm                svc_task_sts.svc_task_sts_nm%TYPE;
   l_assignee                       VARCHAR2(300);
   l_assignee_cd            fsr_visit.tech_cd%TYPE;
   l_assignee_tp_cd                 fsr_visit.svc_asg_tp_cd%TYPE;
   l_asg_tp_nm                      svc_asg_tp.svc_asg_tp_cd%TYPE;
   l_schd_start                     VARCHAR2(300);--fsr_visit.tech_schd_from_dt%TYPE;
   l_schd_end                       VARCHAR2(300);--fsr_visit.tech_schd_to_dt%TYPE;
   l_actual_start                   VARCHAR2(300);--fsr_visit.fsr_visit_arv_dt%TYPE;
   l_actual_end                     VARCHAR2(300);--fsr_visit.fsr_visit_cplt_dt%TYPE;
   l_eta                            VARCHAR2 (300);
   lv_sts_cd                VARCHAR2 (100);
   lv_future_dt                VARCHAR2 (100);
   lv_future_tm                VARCHAR2 (100);
   l_tsk_last_upd_by                fsr_event.FSR_EVENT_EXE_USR_ID%TYPE;
   l_visit_num                      fsr_visit.fsr_visit_num%TYPE;
   l_cust_name                      sell_to_cust.loc_nm%TYPE;
   l_svc_window_from                svc_task.erl_start_ts%TYPE;
   l_svc_window_to                  svc_task.late_start_ts%TYPE;
   l_cust_po_dt                     svc_task.cust_po_dt%TYPE;
   lv_ser_num                svc_mach_mstr.ser_num%TYPE;
   lv_ds_contr_pk            VARCHAR2(100);
   lv_ds_contr_dtl_pk            VARCHAR2(100);
   lv_br_cd            VARCHAR2(100);
   lv_br_desc            VARCHAR2(500);
   l_msg_tp_cd              VARCHAR2(20);
   l_msg_cmnt_txt           VARCHAR2(30000);
   lv_po_flag               VARCHAR2(1);
   l_po_file_name           VARCHAR2(500);
   l_att_data_pk            VARCHAR2(50);
   l_skills                 VARCHAR2(1000);
BEGIN
   o_mach_tbl := CANON_E307_MAC_SER_TBL ();
   o_sr_info_tbl := CANON_E307_SR_INFO_TBL ();
   o_contract_details_tbl := CANON_E307_CONTRACT_TBL ();
   o_task_info_tbl := CANON_E307_TASK_INFO_TBL ();
   o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();
   lv_sr_num:=trim(p_in_sr_num);
   --lv_task_num1:=trim(p_in_task_num);

   IF lv_sr_num is NULL and p_in_task_num is not null
   THEN
   SELECT distinct fsr_num
   into lv_sr_num
   from svc_task
   where SVC_TASK_NUM like '%'||trim (p_in_task_num)||'%'
    AND glbl_cmpy_cd = g_glbl_cmpy_cd;
   END IF;
      
   FOR fr_cur_mach_details IN cur_mach_details(lv_sr_num)
   LOOP
      --- Get Latest Task to get EMail, Caller, Telephone
      BEGIN
         SELECT MAX (SVC_TASK_NUM)
           INTO l_latest_task_num
           FROM SVC_TASK
          WHERE     FSR_NUM = fr_cur_mach_details.FSR_NUM
                AND SVC_MACH_MSTR_PK = fr_cur_mach_details.SVC_MACH_MSTR_PK
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            --- Cannot get Phone, EMail, and Caller
            --- This is hard exception
            --- As Task will always be there
            NULL;
      END;

      --- Now get the EMail, Caller, and Phone
      BEGIN
         SELECT st.CUST_EML_ADDR email_address,
                st.SVC_CUST_ATTN_TXT caller,
                st.SVC_CUST_CLLR_TXT caller,
                st.CUST_TEL_NUM,
                st.CUST_TEL_EXTN_NUM extnl_email_address,
                SUBSTR (st.CUST_TEL_NUM, 1, 3) Phone1,
                SUBSTR (st.CUST_TEL_NUM, 5, 3) Phone2,
                SUBSTR (st.CUST_TEL_NUM, 9, 4) Phone3
           INTO l_email_address,
                l_contact,
                l_caller,
                l_cust_tel_num,
                l_cust_tel_extn,
                l_cust_phone1,
                l_cust_phone2,
                l_cust_phone3
           FROM SVC_TASK st
          WHERE     SVC_TASK_NUM = nvl(p_in_task_num,l_latest_task_num)
                AND FSR_NUM = fr_cur_mach_details.FSR_NUM
                AND SVC_MACH_MSTR_PK = fr_cur_mach_details.SVC_MACH_MSTR_PK
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_email_address := NULL;
            l_caller := NULL;
            l_cust_tel_num  := NULL;
            l_cust_tel_extn := NULL;
            l_cust_phone1   := NULL;
            l_cust_phone2   := NULL;
            l_cust_phone3   := NULL;
      END;
      BEGIN
          SELECT sm.SVC_MEMO_TP_CD, SVC_CMNT_TXT
          INTO l_msg_tp_cd, l_msg_cmnt_txt
      FROM svc_memo sm, svc_memo_catg category
     WHERE     sm.svc_memo_catg_cd = category.svc_memo_catg_cd
           AND sm.glbl_cmpy_cd = g_glbl_cmpy_cd                          --g_glbl_cmpy_cd
           AND category.svc_memo_catg_nm = 'Dispatch Memo'
           --AND SM.SVC_MEMO_TP_CD = '01'
           AND sm.fsr_num = fr_cur_mach_details.FSR_NUM              --fr_cur_mach_details.FSR_NUM
           AND sm.ezcancelflag = g_cancel_flg
           AND ROWNUM = 1;
      EXCEPTION WHEN OTHERS
      THEN
        l_msg_tp_cd:=NULL;
        l_msg_cmnt_txt:=NULL;
      END;
      lv_ser_num:=fr_cur_mach_details.ser_num;
      --- Next Format Incident Date
      l_incident_date_time_formatted :=
         CANON_E307_UTILS.format_date_func (
            fr_cur_mach_details.svc_call_incdt_dt,
            fr_cur_mach_details.svc_call_incdt_tm,
            'FORMAT1');
      l_weekday_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'MON-FRI:',
            p_time1       => fr_cur_mach_details.biz_hrs_mon_fri_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_mon_fri_to_tm,
            p_separator   => '-');
      l_sat_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'SAT:',
            p_time1       => fr_cur_mach_details.biz_hrs_sat_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_sat_to_tm,
            p_separator   => '-');
      l_sun_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'SAT:',
            p_time1       => fr_cur_mach_details.biz_hrs_sun_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_sun_to_tm,
            p_separator   => '-');

    FOR fr_cur_skill IN cur_skill(fr_cur_mach_details.model)
    LOOP
        IF l_skills IS NULL
        THEN
            l_skills := fr_cur_skill.SVC_SKILL_NM;
        ELSE
            l_skills := l_skills||', '||fr_cur_skill.SVC_SKILL_NM;
        END IF;
    END LOOP;


      l_rec_mach :=
         CANON_E307_MAC_SER_REC (fr_cur_mach_details.svc_mach_mstr_pk,
                                 fr_cur_mach_details.model,
                                 fr_cur_mach_details.ser_num,
                                 fr_cur_mach_details.cust_mach_ctrl_num,
                                 fr_cur_mach_details.solution_name,
                                 fr_cur_mach_details.ship_to_acct_no,
                                 fr_cur_mach_details.ship_to_cust_name,
                                 fr_cur_mach_details.address_1,
                                 fr_cur_mach_details.address_2,
                                 fr_cur_mach_details.city,
                                 fr_cur_mach_details.state,
                                 fr_cur_mach_details.post_cd,
                                 NULL,
                                 fr_cur_mach_details.ownr_acct_num,
                                 fr_cur_mach_details.bill_to_cust_cd,
                                 fr_cur_mach_details.sell_to_cust_cd,
                                 fr_cur_mach_details.cur_loc_num,
                                 fr_cur_mach_details.cur_loc_acct_num,
                                 l_weekday_hours,
                                 l_sat_hours,
                                 l_sun_hours,
                                 fr_cur_mach_details.last_svc_call_dt,
                                 fr_cur_mach_details.tot_svc_visit_cnt,
                                 fr_cur_mach_details.last_tech_visit_dt,
                                 fr_cur_mach_details.prf_tech_cd,
                                 fr_cur_mach_details.req_tech_cd,
                                 fr_cur_mach_details.fld_svc_br_cd,
                                 l_email_address,
                                 l_cust_tel_num,
                                 l_cust_tel_extn,
                                 l_cust_phone1,
                                 l_cust_phone2,
                                 l_cust_phone3,
                                 l_caller,
                                 l_msg_cmnt_txt,
                                 l_msg_tp_cd,
                                 l_contact
                                 );
      o_mach_tbl.EXTEND ();
      o_mach_tbl (ln_mach_rec_cnt) := l_rec_mach;
      ln_mach_rec_cnt := ln_mach_rec_cnt + 1;
   END LOOP;



   FOR fr_cur_sr_details IN cur_sr_details(lv_sr_num)
   LOOP
      --- Get Respond By Date
      DBMS_OUTPUT.put_line ('Inside FSR');

      BEGIN
         SELECT RSP_TM_UP_MN_AOT
           INTO l_resp_time
           FROM DS_CONTR_DTL cont
          WHERE     cont.SVC_MACH_MSTR_PK =
                       fr_cur_sr_details.SVC_MACH_MSTR_PK
                AND cont.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND TO_DATE (CONTR_EFF_FROM_DT, 'YYYYMMDD') <=
                       TO_DATE (fr_cur_sr_details.fsr_crat_dt, 'YYYYMMDD')
                AND TO_DATE (CONTR_EFF_THRU_DT, 'YYYYMMDD') >=
                       TO_DATE (fr_cur_sr_details.fsr_crat_dt, 'YYYYMMDD')
                       AND RSP_TM_UP_MN_AOT IS NOT NULL
                       AND rownum=1;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_resp_time := NULL;
      END;

      DBMS_OUTPUT.put_line ('Inside FSR -- 001');

      IF l_resp_time IS NOT NULL
      THEN
         l_resp_by_date :=
            CANON_E307_UTILS.add_time_and_format_datetime (
               fr_cur_sr_details.fsr_crat_dt,
               fr_cur_sr_details.fsr_crat_tm,
               l_resp_time,
               'FORMAT1');
         -- l_resp_by_date := NULL;
      END IF;

      DBMS_OUTPUT.put_line ('Inside FSR -- 002');

     /* BEGIN
         SELECT MAX  (SVC_TASK_NUM)
           INTO l_latest_task_num
           FROM SVC_TASK
          WHERE     FSR_NUM = fr_cur_sr_details.FSR_NUM
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;*/

    BEGIN
        l_billable_count := GET_BILLABLE_FLAG(fr_cur_sr_details.fsr_num);
        IF l_billable_count>0
        THEN
            l_billable_flag:='Y';
        END IF;
    --    debug_pkg.debug_proc('l_billable_flag= '||l_billable_flag);
   EXCEPTION WHEN OTHERS
          THEN
          l_billable_flag:=NULL;
    END;

      DBMS_OUTPUT.put_line ('Inside FSR -- 003');

      BEGIN
         SELECT st.svc_bill_tp_cd,
            --    st.svc_pblm_symp_tp_cd pblm_tp_cd,
                st.mach_down_flg mach_status,
                st.cust_po_num,
                st.svc_trty_mgr_psn_cd,
                st.erl_start_ts,
                st.late_start_ts,
                CANON_E307_UTILS.format_date3_func (st.cust_po_dt,
                                             'FORMAT1')
           INTO l_bill_tp_cd,
              --  l_pblm_tp_cd,
                l_mach_status,
                l_cust_po_num,
                l_mach_mgr,--TBD
                l_svc_window_from,
                l_svc_window_to,
                l_cust_po_dt
           FROM SVC_TASK st
          WHERE     SVC_TASK_NUM = nvl(p_in_task_num,l_latest_task_num)
                AND FSR_NUM = fr_cur_sr_details.FSR_NUM
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_bill_tp_cd := NULL;
            l_pblm_tp_cd := NULL;
            l_mach_status := NULL;
            l_cust_po_num := NULL;
            l_cust_po_dt := NULL;
      END;

      DBMS_OUTPUT.put_line ('Inside FSR -- 004');

      --Fetch Bill Type Name
      BEGIN
         SELECT svc_bill_tp_nm
           INTO l_bill_tp_nm
           FROM svc_bill_tp
          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg
                AND SVC_BILL_TP_CD = l_bill_tp_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_bill_tp_cd := NULL;
      END;

      DBMS_OUTPUT.put_line ('Inside FSR -- 005');

      --Fetch SR Last Updated By
      BEGIN
         SELECT GET_PSN_NM(fe.FSR_EVENT_EXE_USR_ID)
           INTO l_sr_last_upd_by
           FROM fsr_event fe
          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg
                AND fe.FSR_EVENT_EXE_TS =
                       (SELECT MAX (fe1.FSR_EVENT_EXE_TS)
                          FROM FSR_EVENT fe1
                         WHERE fe1.FSR_NUM = fr_cur_sr_details.fsr_num);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_sr_last_upd_by := NULL;
      END;

      DBMS_OUTPUT.put_line ('Inside FSR -- 006');

      --Fetch SR Problem Type Name
      BEGIN
         SELECT svc_pblm_tp_nm
           INTO l_pblm_tp_nm
           FROM svc_pblm_tp
       --   WHERE svc_pblm_tp_cd = l_pblm_tp_cd
            WHERE svc_pblm_tp_cd = fr_cur_sr_details.svc_pblm_tp_cd
          AND ezcancelflag = g_cancel_flg
             AND glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_pblm_tp_nm := NULL;
      END;

      --Fetch SR Source Type Name
      BEGIN
         SELECT svc_call_src_tp_nm
           INTO l_call_src_tp_nm
           FROM svc_call_src_tp src
          WHERE src.svc_call_src_tp_cd = fr_cur_sr_details.svc_call_src_tp_cd
          AND ezcancelflag = g_cancel_flg
             AND glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_call_src_tp_nm := NULL;
      END;

      --Get Line of Business
      BEGIN
         SELECT svc_by_line_biz_tp_cd
           INTO l_line_biz_tp_cd
           FROM svc_mach_mstr ib
          WHERE ib.ser_num = fr_cur_sr_details.ser_num;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_line_biz_tp_cd := NULL;
      END;

      --Get Branch Name
      BEGIN
         /*SELECT branch.svc_contr_br_desc_txt
           INTO l_br_desc_txt
           FROM svc_contr_br branch, svc_mach_mstr ib
          WHERE     ib.ser_num = fr_cur_sr_details.ser_num
                AND branch.svc_contr_br_cd = ib.fld_svc_br_cd;*/
           get_equip_branch(fr_cur_sr_details.ser_num,lv_br_cd,l_br_desc_txt);     
      EXCEPTION
         WHEN OTHERS
         THEN
            l_br_desc_txt := NULL;
      END;

      --Get Current Location Details
      BEGIN
         SELECT ship.ship_to_cust_cd,
             ship.loc_nm,
             ship.FIRST_Line_addr cur_addr_line,
                ship.cty_addr cur_city,
                ship.st_cd cur_st_cd,
                ship.post_cd cur_post_cd,
                ship.ctry_cd cur_ctry_cd
           INTO l_cust_cd,
               l_loc_nm,
               l_cur_addr_line,
                l_cur_city,
                l_cur_st_cd,
                l_cur_post_cd,
                l_cur_ctry_cd
           FROM ship_to_cust ship
          WHERE     ship.ship_to_cust_cd = decode(fr_cur_sr_details.ship_to_cust_upd_flg,'Y',fr_cur_sr_details.ship_to_upd_cust_cd,fr_cur_sr_details.ship_to_cust_cd)
                AND ship.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ship.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND NVL (ship.eff_thru_dt, SYSDATE + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship.eff_from_dt, SYSDATE) <=
                       TO_CHAR (SYSDATE + 1, 'YYYYMMDD');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_cd := NULL;
            l_loc_nm := NULL;         
            l_cur_addr_line := NULL;
            l_cur_city := NULL;
            l_cur_st_cd := NULL;
            l_cur_post_cd := NULL;
            l_cur_ctry_cd := NULL;
      END;

      DBMS_OUTPUT.put_line ('Inside FSR -- 007');

      --Get Bill To Location Details
      BEGIN
         SELECT bill.bill_to_cust_cd,
             bill.loc_nm,
             bill.FIRST_Line_addr bill_addr_line,
                bill.cty_addr bill_city,
                bill.st_cd bill_st_cd,
                bill.post_cd bill_post_cd,
                bill.ctry_cd bill_ctry_cd
           INTO l_bill_cust_cd,
               l_bill_loc_nm,
               l_bill_addr_line,
                l_bill_city,
                l_bill_st_cd,
                l_bill_post_cd,
                l_bill_ctry_cd
           FROM bill_to_cust bill
          WHERE     bill.bill_to_cust_cd = decode(fr_cur_sr_details.bill_to_cust_upd_flg,'Y',
                                fr_cur_sr_details.bill_to_upd_cust_cd,fr_cur_sr_details.bill_to_cust_cd)
                AND bill.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND bill.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND NVL (bill.eff_thru_dt, SYSDATE + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (bill.eff_from_dt, SYSDATE) <=
                       TO_CHAR (SYSDATE + 1, 'YYYYMMDD');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_bill_cust_cd := NULL;
            l_bill_loc_nm := NULL;         
            l_bill_addr_line := NULL;
            l_bill_city := NULL;
            l_bill_st_cd := NULL;
            l_bill_post_cd := NULL;
            l_bill_ctry_cd := NULL;
      END;

-- debug_pkg.debug_proc('Before l_visit_num ='||l_visit_num);    

l_visit_num:=canon_e307_call_support_pkg.get_max_visit(fr_cur_sr_details.fsr_num);
      /*BEGIN
         SELECT MAX (fsr_visit_num)
           INTO l_visit_num
           FROM fsr_visit visit
          WHERE     visit.FSR_NUM = fr_cur_sr_details.FSR_NUM
                AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_visit_num := NULL;
      END;*/

--debug_pkg.debug_proc('l_visit_num ='||l_visit_num);
      --Get FSR Visit Details
      BEGIN
         SELECT CANON_E307_UTILS.format_date_func (visit.fsr_visit_eta_dt,
                                                   visit.fsr_visit_eta_tm,
                                                   'FORMAT1')
                   --TBD
                  -- sch_date,CANON_E307_UTILS.format_date2_func(fut_svc_dt, 'FORMAT1')
                  ,/*CANON_E307_UTILS.format_date_func (visit.fut_svc_dt,
                                                   visit.fut_svc_tm,
                                                   'FORMAT1')*/
        CANON_E307_UTILS.format_date3_func (visit.fut_svc_dt,
                                             'FORMAT1'),
                CANON_E307_UTILS.format_time (fut_svc_tm)  
           INTO l_eta,lv_future_dt,lv_future_tm
           FROM fsr_visit visit
          WHERE     visit.FSR_NUM = fr_cur_sr_details.FSR_NUM
                AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND visit.fsr_visit_num = l_visit_num;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_eta := NULL;
            lv_future_dt := NULL;
            lv_future_tm := NULL;
            
      END;

      DBMS_OUTPUT.put_line ('Inside FSR -- 008');

      l_sr_creation_date :=
         CANON_E307_UTILS.format_date_func (fr_cur_sr_details.fsr_crat_dt,
                                            fr_cur_sr_details.fsr_crat_tm,
                                            'FORMAT1');


      l_sr_update_date :=
         CANON_E307_UTILS.format_date2_func (fr_cur_sr_details.last_upt_dt,
                                             'FORMAT1');

      DBMS_OUTPUT.put_line ('Inside FSR -- 6');

      l_cur_loc_string :=
         CANON_E307_UTILS.format_address (l_cur_addr_line,
                                          l_cur_city,
                                          l_cur_st_cd,
                                          l_cur_post_cd,
                                          l_cur_ctry_cd);

      l_bill_string :=
         CANON_E307_UTILS.format_address (l_bill_addr_line,
                                          l_bill_city,
                                          l_bill_st_cd,
                                          l_bill_post_cd,
                                          l_bill_ctry_cd);

      DBMS_OUTPUT.put_line ('Inside FSR -- 3');

      SELECT sts.SVC_TASK_STS_NM
      INTO l_sr_sts
        FROM SVC_TASK_STS sts
       WHERE     SVC_TASK_STS_CD = fr_cur_sr_details.fsr_sts_cd
             AND sts.ezcancelflag =g_cancel_flg
       AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd;
       
    --   debug_pkg.debug_proc('l_sr_sts ='||l_sr_sts);
      
      BEGIN 
        SELECT val.val3
         into l_fsr_upd_flag
         FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
         WHERE     cd.cd_id = val.cd_id
         AND cd_name = 'CANON_E307_TASK_STAT_VALUES'
         AND VAL.VAL1 = fr_cur_sr_details.fsr_sts_cd
         and rownum<2;
      EXCEPTION 
       WHEN OTHERS THEN
            l_fsr_upd_flag:='Y';
      END;

       l_mach_mgr1:=get_psn_nm(fr_cur_sr_details.svc_call_rqst_ownr_toc_cd);
       
    --   debug_pkg.debug_proc('l_mach_mgr1 ='||l_mach_mgr1);
       
       BEGIN
               SELECT fsr_tp_nm
               INTO   lv_fsr_tp_nm
               FROM fsr_tp
               WHERE fsr_tp_cd=fr_cur_sr_details.fsr_tp_cd
               AND fsr_tp.EZCANCELFLAG = g_cancel_flg
               AND fsr_tp.GLBL_CMPY_CD = g_glbl_cmpy_cd;
       EXCEPTION 
       WHEN OTHERS THEN
       lv_fsr_tp_nm:=NULL;
       
       END;
 
  --  lv_po_flag:= GET_PO_REQ_FLG(fr_cur_sr_details.ser_num,fr_cur_sr_details.ship_to_cust_cd);
    

    BEGIN
         select ATT_DATA_NM, ATT_DATA_PK 
         INTO l_po_file_name, l_att_data_pk
         from   (SELECT  DISTINCT ATT_DATA_NM, ATT_DATA_PK
         FROM ATT_DATA a
         WHERE  EZBUSINESSID        =   fr_cur_sr_details.fsr_num
         AND GLBL_CMPY_CD               = g_glbl_cmpy_cd
         AND EZCANCELFLAG               = g_cancel_flg
     --    AND EZUPAPLID                  = g_e307_appl_id
            order by a.ATT_DATA_PK desc)
        where rownum =1;

    EXCEPTION
    WHEN OTHERS THEN
    l_po_file_name:='';
    l_att_data_pk:='';
    END;
       
--debug_pkg.debug_proc('Before Rec FSRNUM ='||fr_cur_sr_details.fsr_num);
      l_rec_sr :=
         CANON_E307_SR_INFO_REC (fr_cur_sr_details.fsr_num,
                                 fr_cur_sr_details.itt_num,
                                 l_bill_tp_cd,
                                 l_bill_tp_nm,
                                 l_sr_creation_date,
                                 l_resp_by_date,
                                 l_sr_update_date,
                                 l_sr_last_upd_by,
                                 fr_cur_sr_details.ezinusernm,
                                 fr_cur_sr_details.svc_call_rqst_ownr_toc_cd,--mach_mgr_cd
                                 l_mach_mgr1,
                                 fr_cur_sr_details.svc_pblm_tp_cd,
                                 l_pblm_tp_nm,
                                 l_mach_status,
                                 l_call_src_tp_nm,
                                 l_line_biz_tp_cd,
                                 l_br_desc_txt,
                                 l_cust_po_num,
                                 l_cur_addr_line,
                                 l_cur_city,
                                 l_cur_st_cd,
                                 l_cur_post_cd,
                                 l_cur_ctry_cd,
                                 l_cur_loc_string,
                                 l_bill_addr_line,
                                 l_bill_city,
                                 l_bill_st_cd,
                                 l_bill_post_cd,
                                 l_bill_ctry_cd,
                                 l_bill_string,
                                 fr_cur_sr_details.pmt_term,
                                 l_svc_window_from,
                                 l_svc_window_to,
                                 l_eta,
                                 lv_future_dt,
                                 lv_future_tm,
                                 l_sr_sts,
                                 fr_cur_sr_details.cust_cse_num,
                                 fr_cur_sr_details.po_ver_flg,
                                 fr_cur_sr_details.fsr_tp_cd,
                                 lv_fsr_tp_nm,
                                 l_cust_cd,
                                 l_loc_nm,
                                 l_bill_cust_cd,
                                 l_bill_loc_nm,
                                 l_cust_po_dt,
                                 l_fsr_upd_flag,
                                 l_billable_flag,
                                 lv_po_flag,
                                 l_po_file_name,
                                 l_att_data_pk);

      DBMS_OUTPUT.put_line ('Inside FSR -- 2');
      o_sr_info_tbl.EXTEND ();
      o_sr_info_tbl (ln_sr_rec_cnt) := l_rec_sr;
      ln_sr_rec_cnt := ln_sr_rec_cnt + 1;
   END LOOP;

   BEGIN
      GET_CONTRACT_INFO(lv_ser_num,lv_ds_contr_pk,lv_ds_contr_dtl_pk,x_contract_details_tbl);
      o_contract_details_tbl:=x_contract_details_tbl;
      EXCEPTION WHEN OTHERS THEN
      NULL;
   END;

   FOR fr_cur_task_details IN cur_task_details(lv_sr_num)
   LOOP
   --debug_pkg.debug_proc('Task Number ='||fr_cur_task_details.svc_task_num);
      --Get Task Type Name
      BEGIN
         SELECT ds_svc_call_tp_nm
           INTO l_call_tp_nm
           FROM ds_svc_call_tp TYPE
          WHERE     TYPE.ds_svc_call_tp_cd =
                       fr_cur_task_details.ds_svc_call_tp_cd
                AND TYPE.glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_call_tp_nm := NULL;
      END;

      

      --Get FSR Visit Number

      BEGIN
         SELECT MAX (fsr_visit_num)
           INTO l_visit_num
           FROM fsr_visit visit
          WHERE     visit.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
                AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND visit.fsr_visit_num =
                       (SELECT MAX (visit1.fsr_visit_num)
                          FROM fsr_visit visit1
                         WHERE visit.svc_task_num = visit1.svc_task_num);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_visit_num := NULL;
      END;

--debug_pkg.debug_proc('l_visit_num ='||l_visit_num);

      --Get FSR Visit Details
      BEGIN
         SELECT  visit.tech_cd,get_psn_nm(visit.tech_cd) assignee_name,
                visit.svc_asg_tp_cd,
                CANON_E307_UTILS.format_date (visit.tech_schd_from_dt,
                                                   'FORMAT1') schd_start,
                CANON_E307_UTILS.format_date (visit.tech_schd_to_dt,
                                                   'FORMAT1') schd_end,
                CANON_E307_UTILS.format_date (visit.fsr_visit_arv_dt,
                                                   'FORMAT1')actual_start,
                CANON_E307_UTILS.format_date (visit.fsr_visit_cplt_dt,
                                                   'FORMAT1')actual_end,
                CANON_E307_UTILS.format_date_func (visit.tech_schd_from_dt,
                                                   visit.tech_schd_from_tm,
                                                   'FORMAT1')
                   sch_date,
                   fsr_visit_sts_cd
           INTO l_assignee_cd,
               l_assignee,
                l_assignee_tp_cd,
                l_schd_start,
                l_schd_end,
                l_actual_start,
                l_actual_end,
                l_eta,
                lv_sts_cd
           FROM fsr_visit visit
          WHERE     visit.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
                AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND visit.fsr_visit_num = l_visit_num;
      EXCEPTION
         WHEN OTHERS
         THEN
        -- debug_pkg.debug_proc('In Exception ' ||sqlerrm);

            l_assignee_cd:= NULL;
            l_assignee := NULL;
            l_assignee_tp_cd := NULL;
            l_schd_start := NULL;
            l_schd_end := NULL;
            l_actual_start := NULL;
            l_actual_end := NULL;
            l_eta := NULL;
            lv_sts_cd := NULL;
      END;
      
      --debug_pkg.debug_proc('l_assignee_cd= ' ||l_assignee_cd);
      --debug_pkg.debug_proc('lv_sts_cd= ' ||lv_sts_cd);
    --Get Task Status
    --As per S21 team the Task status to be fetched from fsr_visit table
      BEGIN
         SELECT sts.svc_task_sts_nm
           INTO l_svc_task_sts_nm
           FROM svc_task_sts sts
          WHERE     sts.SVC_TASK_STS_CD = lv_sts_cd--fr_cur_task_details.svc_task_sts_cd
                AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
          -- debug_pkg.debug_proc('In Exception1 ' ||sqlerrm);
            l_svc_task_sts_nm := NULL;
      END;
       --debug_pkg.debug_proc('l_svc_task_sts_nm= ' ||l_svc_task_sts_nm);
      BEGIN
       SELECT val.val4
       INTO l_tsk_upd_flag
        FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
        WHERE     cd.cd_id = val.cd_id
         AND cd_name = 'CANON_E307_TASK_STAT_VALUES'
         AND VAL.VAL1 = lv_sts_cd
         and rownum<2;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_tsk_upd_flag:='Y';
      END;       
      --Get Assignee type name
      BEGIN
         SELECT sat.svc_asg_tp_nm
           INTO l_asg_tp_nm
           FROM svc_asg_tp sat
          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg
                AND sat.svc_asg_tp_cd = l_assignee_tp_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_tsk_last_upd_by := NULL;
      END;

      --Fetch Task Last Updated By
      BEGIN
         SELECT get_psn_nm(fe.FSR_EVENT_EXE_USR_ID)
           INTO l_tsk_last_upd_by
           FROM fsr_event fe
          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg
                AND fe.FSR_EVENT_EXE_TS =
                       (SELECT MAX (fe1.FSR_EVENT_EXE_TS)
                          FROM FSR_EVENT fe1
                         WHERE fe1.FSR_NUM = fr_cur_task_details.fsr_num);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_tsk_last_upd_by := NULL;
      END;

     l_early_start := CANON_E307_UTILS.format_date2_func(fr_cur_task_details.early_start, 'FORMAT1');
     l_late_start := CANON_E307_UTILS.format_date2_func(fr_cur_task_details.late_start, 'FORMAT1');
     l_creation_date := CANON_E307_UTILS.format_date2_func(fr_cur_task_details.creat_dt, 'FORMAT1');
   --  dbms_output.put_line('Early Start:' || l_early_start);
   --  dbms_output.put_line('Late Start:' || l_late_start);
   --  debug_pkg.debug_proc('l_assignee= '||l_assignee);
     
        l_rec_task := CANON_E307_TASK_INFO_REC (fr_cur_task_details.svc_task_num,
                                   fr_cur_task_details.ds_svc_call_tp_cd,
                                   l_call_tp_nm,
                                   l_svc_task_sts_nm,
                                   lv_sts_cd,--fr_cur_task_details.svc_task_sts_cd,
                                   l_assignee_cd,
                                   l_assignee,
                                   l_asg_tp_nm,
                                   l_assignee_tp_cd,
                                   l_tsk_last_upd_by,
                                   l_creation_date,
                                   l_schd_start,
                                   l_schd_end,
                                   l_actual_start,
                                   l_actual_end,
                                   l_early_start,
                                   l_late_start,
                                   l_assignee, --TBD fr_cur_task_details.res_manager,
                                   l_br_desc_txt,
                                   l_visit_num,
                                   l_tsk_upd_flag,
                                   l_skills);
      o_task_info_tbl.EXTEND ();
      o_task_info_tbl (ln_task_rec_cnt) := l_rec_task;
      ln_task_rec_cnt := ln_task_rec_cnt + 1;
   END LOOP;
     FOR fr_cur_notes IN cur_notes (lv_sr_num,lv_ser_num,lv_ds_contr_pk,lv_ds_contr_dtl_pk)
         LOOP
            l_rec_notes :=
               CANON_E307_DEBRIEF_NOTE_REC (fr_cur_notes.Note_Source_Id,
                                            fr_cur_notes.Note_Id,
                                            fr_cur_notes.Note_Type,
                                            fr_cur_notes.Note_Date,
                                            fr_cur_notes.Note_Text,
                                            fr_cur_notes.Created_By);
            o_notes_tbl.EXTEND ();
            o_notes_tbl (ln_note_rec_cnt) := l_rec_notes;
            ln_note_rec_cnt := ln_note_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;

END GET_CALL_SUMMARY;
/*******************************************************************************************
 Procedure Name: RESOURCE_LOV
 Description: Get details for Assignee LOV
 Input Parameters: p_in_task_no

 Output Parameters: Email Address
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE RESOURCE_LOV (
   p_in_name    IN     VARCHAR2,
   p_in_lov_name     IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_assignee_tbl      OUT CANON_E307_RES_LOV_TBL)
IS
   l_rec_assignee_name    CANON_E307_RES_LOV_REC;
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   v_assignee_cursor      cur_typ;
   lv_tech_id        VARCHAR2 (30);
   lv_name       VARCHAR2 (500);
   ln_rec_cnt1        NUMBER := 1;
   l_order_by         VARCHAR2 (100);
   l_asc_desc_order   VARCHAR2 (100);
   lv_nm       VARCHAR2 (500);
BEGIN
--debug_pkg.debug_proc('Inside RESOURCE_LOV');
   o_assignee_tbl := CANON_E307_RES_LOV_TBL ();
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;
   lv_nm :=trim(p_in_name);
  /* l_default_from :=
         ' FROM (SELECT rownum row_num,assignee.* '
      || 'FROM CANON_E307_TASK_ASSIGNEE_V assignee,PSN_TP type '
      || 'where upper(tech_name) like upper( '
      || '''%'
      || p_in_name
      || '%'' ) '
      || ' AND assignee.type=type.psn_tp_cd '
      || ' AND type.psn_tp_nm = ''Employee'' '
      || ' AND TYPE.EZCANCELFLAG= '
      || g_cancel_flg
      || ' AND TYPE.glbl_cmpy_cd ='''
      || g_glbl_cmpy_cd
      || ''' ) ';*/
   IF upper(p_in_lov_name)='ASSIGNEE'
   THEN
      l_default_from :=
              'FROM ( Select * 
                FROM tech_mstr assignee '
            || 'where upper(tech_nm) like upper( '
            || '''%'
            || lv_nm
            || '%'' ) '
            || ' AND assignee.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND assignee.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
      || '''  ';

   v_sql :=
         'SELECT tech_nm, tech_toc_cd,rownum row_num '
      || l_default_from;

   IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||')';
   ELSE
      v_sql := v_sql || ' ORDER BY tech_nm ) ';
   END IF;
   
            v_sql := 'SELECT tech_nm, tech_toc_cd FROM( '
                 ||    v_sql
                  || ' )  WHERE row_num BETWEEN '
                  || p_start
                  || ' AND '
            || p_end;
   
   ELSIF upper(p_in_lov_name)='MANAGER'
   THEN
   l_default_from :=
            'FROM ( Select * 
                FROM CANON_E307_RESOURCE_V res,PSN_TP type '
         || 'where upper(resource_name) like upper( '
         || '''%'
         || lv_nm
         || '%'' ) '
         || ' AND res.type=type.psn_tp_cd '
         || ' AND type.psn_tp_nm = ''Employee'' '
         || ' AND TYPE.EZCANCELFLAG= '
         || g_cancel_flg
         || ' AND TYPE.glbl_cmpy_cd ='''
         || g_glbl_cmpy_cd
      || '''  ';

    v_sql :=
         'SELECT resource_name, resource_id,rownum row_num '
      || l_default_from; 
      
   IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||' ) ';
   ELSE
      v_sql := v_sql || ' ORDER BY resource_name ) ';
   END IF;   
   
               v_sql := 'SELECT resource_name, resource_id FROM( '
                    ||    v_sql
                     || ' )  WHERE row_num BETWEEN '
                     || p_start
                     || ' AND '
            || p_end;
 --  debug_pkg.debug_proc('v_sql='||v_sql);
   END IF;
--debug_pkg.debug_proc('v_sql ='||v_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from|| ') ';

   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_assignee_cursor FOR v_sql;

   LOOP
      FETCH v_assignee_cursor
         INTO lv_name, lv_tech_id;

      EXIT WHEN v_assignee_cursor%NOTFOUND;
      l_rec_assignee_name :=
         CANON_E307_RES_LOV_REC (lv_name, lv_tech_id);
      o_assignee_tbl.EXTEND ();
      o_assignee_tbl (ln_rec_cnt1) := l_rec_assignee_name;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END RESOURCE_LOV;

/*******************************************************************************************
 Procedure Name: COPY FSR
 Description: Get SR details to be displayed in ASCC
 Input Parameters: p_in_serial
            p_in_model

 Output Parameters: o_resp_time  
              o_vip_flag  
               o_mach_tbl  
               o_ugw_tbl   
               o_prob_tbl      
               o_call_info_tbl    
               o_contract_details_tbl  
               o_cust_loc_tbl 
               o_bill_to_tbl           
               o_notes_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE COPY_FSR (
   p_in_sr_num                  VARCHAR2,
   o_resp_time              OUT VARCHAR2,
   o_vip_flag               OUT VARCHAR2,
   o_mdl_dur            OUT VARCHAR2,
   o_sr_owner            OUT VARCHAR2,
   o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
   o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
   o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
   o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
   o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
   o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
   o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
   o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL)
IS
   l_rec_mach               CANON_E307_MAC_SER_REC;
   l_rec_ugw                CANON_E307_UGW_ERR_CODE_REC;
   l_rec_prob               CANON_E307_PROB_CODE_REC;
   l_rec_call_info          CANON_E307_CALL_INFO_REC;
   l_rec_contract_details   CANON_E307_CONTRACT_REC;
   l_rec_cust_loc_details   CANON_E307_CUST_LOC_REC;
   l_rec_bill_to_details    CANON_E307_CUST_LOC_REC;
   l_rec_notes              CANON_E307_DEBRIEF_NOTE_REC;
   l_resp_time              DS_CONTR_DTL.RSP_TM_UP_MN_AOT%TYPE;
   x_call_type              VARCHAR2 (100);
   x_call_type_id           VARCHAR2 (30);
   l_special_message_type   VARCHAR2 (30);
   x_contract_details_tbl CANON_E307_CONTRACT_TBL;
   x_ugw_tbl          CANON_E307_UGW_ERR_CODE_TBL;
   x_cust_loc_tbl      CANON_E307_CUST_LOC_TBL;
   x_bill_to_tbl      CANON_E307_CUST_LOC_TBL;
   lv_br_desc        VARCHAR2 (500);
   lv_br_cd        VARCHAR2 (100);
   lv_sell_to_cust_cd   VARCHAR2 (100);
   lv_po_flag    ds_cust_trx_rule.ds_po_req_flg%TYPE;
   

   CURSOR cur_mach_details(lv_ser_num IN VARCHAR2)
   IS
      SELECT smm.svc_mach_mstr_pk,                                
             mdl.T_MDL_NM model,
             smm.SER_NUM ser_num,
             smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
             config.svc_sln_nm solution_name,
             smm.ship_to_cust_cd ship_to_acct_no,
             ship_to.loc_nm ship_to_cust_name,
             ship_to.first_line_addr address_1,
             ship_to.scd_line_addr address_2,
             ship_to.cty_addr city,
             ship_to.st_cd state,
             ship_to.post_cd,
             smm.ownr_acct_num,
             smm.bill_to_cust_cd,
             ship_to.sell_to_cust_cd,
             smm.cur_loc_num,
             smm.cur_loc_acct_num,
             smm.biz_hrs_mon_fri_from_tm,
             smm.biz_hrs_mon_fri_to_tm,
             smm.biz_hrs_sat_from_tm,
             smm.biz_hrs_sat_to_tm,
             smm.biz_hrs_sun_from_tm,
             smm.biz_hrs_sun_to_tm,
             smm.last_svc_call_dt,
             smm.tot_svc_visit_cnt,
             smm.last_tech_visit_dt,
             smm.prf_tech_cd,
             smm.req_tech_cd,
             smm.fld_svc_br_cd
        FROM svc_mach_mstr smm, svc_config_mstr config, mdl_nm mdl,ship_to_cust ship_to
       WHERE     1 = 1
             AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
             /*AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND mdl.T_MDL_ID = config.MDL_ID
             AND ship_to.ship_to_cust_cd =smm.cur_loc_num
                            AND NVL (ship_to.eff_thru_dt, SYSDATE + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
              AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd         
              AND smm.ser_num = lv_ser_num;


   CURSOR cur_prob_code(lv_task_num IN VARCHAR2)
   IS
      SELECT st.svc_pblm_symp_tp_cd pblm_tp_cd
        FROM SVC_TASK st
       WHERE     SVC_TASK_NUM =lv_task_num
            AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
       
      

   CURSOR cur_call_info(lv_ser_num IN VARCHAR2)
   IS
      SELECT creation_channel,
             creation_channel_cd,
             task_type_name,
             task_type_code,
             bill_code,
             bill_code_name,
            line_of_business,
             --branch,
             --branch_cd,
             ah_task_type,
             ah_task_code
        FROM (SELECT 'ASCC' creation_channel,
                     (SELECT SVC_CALL_SRC_TP_CD
                        FROM svc_call_src_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND svc_call_src_tp_sort_num = '1'
                             AND svc_call_src_tp_nm = g_ascc_source_name
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        creation_channel_cd,
                     (SELECT ds_svc_call_tp_nm
                        FROM ds_svc_call_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ascc_sel_flg = 'Y'
                             AND ds_svc_call_tp_cd = 1
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Task_Type_Name,
                     (SELECT DS_SVC_CALL_TP_CD
                        FROM DS_SVC_CALL_TP
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ASCC_SEL_FLG = 'Y'
                             AND DS_SVC_CALL_TP_CD = 1
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Task_Type_Code,
                     (SELECT SVC_BILL_TP_CD
                        FROM SVC_BILL_TP
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND SVC_BILL_TP_CD = 1
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Bill_Code,
                     (SELECT SVC_BILL_TP_NM
                        FROM SVC_BILL_TP
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND SVC_BILL_TP_CD = 1
                             AND ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Bill_Code_Name,
                     (SELECT svc_by_line_biz_tp_cd
                        FROM svc_mach_mstr ib
                       WHERE     ib.ser_num = lv_ser_num
                             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ib.ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Line_of_business,
                     /*(SELECT svc_contr_br_desc_txt
                        FROM svc_contr_br branch, svc_mach_mstr ib
                       WHERE     branch.svc_contr_br_cd = ib.fld_svc_br_cd
                             AND ib.ser_num = lv_ser_num
                             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ib.ezcancelflag = g_cancel_flg
                             AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND branch.ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Branch,
                     (SELECT fld_svc_br_cd
                        FROM svc_mach_mstr ib
                       WHERE     ib.ser_num = lv_ser_num
                             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ib.ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        Branch_Cd,*/
                     (SELECT ds_svc_call_tp_nm
                        FROM ds_svc_call_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ascc_sel_flg = 'Y'
                             AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                             AND ezcancelflag = g_cancel_flg)
                        ah_task_type,
                     (SELECT ds_svc_call_tp_cd
                        FROM ds_svc_call_tp
                       WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND ascc_sel_flg = 'Y'
                             AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                             AND ezcancelflag = g_cancel_flg)
                        ah_task_code
                FROM DUAL);

   
   CURSOR cur_notes(lv_ser_num IN VARCHAR2,lv_ds_contr_pk IN VARCHAR2,lv_ds_contr_dtl_pk IN VARCHAR2)
   IS
      /*SELECT '' Note_Source_Id,
             '' Note_Id,
             note_type.svc_memo_tp_nm Note_Type,
             note.EZINTIME Note_Date,
             note.SVC_CMNT_TXT Note_Text,
             note.EZINUSERID Created_By
        FROM SVC_MACH_MSTR ib, SVC_MEMO note, SVC_MEMO_TP note_type
       WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
             AND note.SVC_MEMO_CATG_CD = note_type.SVC_MEMO_CATG_CD
             AND ib.ser_num = p_in_serial                       --'TC00000018'
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND note.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND note.ezcancelflag = g_cancel_flg
             AND note_type.ezcancelflag = g_cancel_flg;*/
             SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM SVC_MACH_MSTR ib, SVC_MEMO note, SVC_MEMO_TP note_type,SVC_MEMO_CATG CATEGORY
          WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
                AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm='Machine Memo'
                AND ib.ser_num = lv_ser_num
                /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                               TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND note.svc_task_num is null
                and note.fsr_num is null
                and note.ds_contr_pk is null
                and note.ds_contr_dtl_pk is null
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
          WHERE  note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm='Contract Memo'
                AND note.ds_contr_pk = lv_ds_contr_pk
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_DTL_PK IS NULL
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM  SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
          WHERE note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm='Contract Memo'
                AND note.ds_contr_dtl_pk  = lv_ds_contr_dtl_pk
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                   AND note.DS_CONTR_PK IS NULL
         /*UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                note.EZINTIME Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type
          WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.SVC_MEMO_CATG_CD = '03'
                AND  note.fsr_num=p_in_sr_num
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_PK IS NULL
       AND note.DS_CONTR_DTL_PK IS NULL*/;

   ln_mach_rec_cnt          NUMBER := 1;
   ln_prob_rec_cnt          NUMBER := 1;
   ln_call_rec_cnt          NUMBER := 1;
  ln_note_rec_cnt          NUMBER := 1;
   l_cust_tel_num           VARCHAR2 (100) := NULL;
   l_cust_tel_ext           VARCHAR2 (100) := NULL;
   l_email_address          VARCHAR2 (100) := NULL;
   l_special_message        VARCHAR2 (100) := NULL;
   l_weekday_hours          VARCHAR2 (100);
   l_sat_hours              VARCHAR2 (100);
   l_sun_hours              VARCHAR2 (100);
   l_cust_name              VARCHAR2 (100);
   l_header_eff_string      VARCHAR2 (100);
   l_line_eff_string        VARCHAR2 (100);
   l_sla_converted          VARCHAR2(50);
   lv_ds_contr_pk        VARCHAR2 (100);
   lv_ds_contr_dtl_pk       VARCHAR2 (100);
   lv_pblm_tp_cd        VARCHAR2 (100);
   lv_mach_mstr_pk        VARCHAR2 (100);
   lv_ser_num            VARCHAR2 (100);
   lv_task_num            VARCHAR2 (100);
   lv_pblm_tp_nm            VARCHAR2 (100);
   lv_pblm_grp_txt  VARCHAR2 (100);
   lv_mdl_nm             VARCHAR2 (100); 
   lv_po_num           VARCHAR2 (100); 
   lv_sr_owner          VARCHAR2 (100); 
   l_cust_phone1        VARCHAR2(10);
   l_cust_phone2        VARCHAR2(10);
   l_cust_phone3        VARCHAR2(10);      
BEGIN

BEGIN
--debug_pkg.debug_proc('Inside COPY FSR');
 o_mach_tbl := CANON_E307_MAC_SER_TBL ();
   o_ugw_tbl := CANON_E307_UGW_ERR_CODE_TBL ();
   o_prob_tbl := CANON_E307_PROB_CODE_TBL ();
   o_call_info_tbl := CANON_E307_CALL_INFO_TBL ();
   o_contract_details_tbl := CANON_E307_CONTRACT_TBL ();
   o_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();
   o_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();
   o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();
SELECT --fsr_num,
                --fsr_sts_cd,
                --tech_cd,
                --fsr_crat_dt,
                --fsr_crat_tm,
                --ezuptime last_upt_dt,
                --ezinuserid,
               -- fsr_cplt_dt,
               -- fsr_cplt_tm,
                --bill_to_cust_cd,
                --sell_to_cust_cd,
                --ship_to_cust_cd,
                --pmt_term_cash_disc_cd pmt_term,
                --svc_call_src_tp_cd,
                svc_pblm_tp_cd,
                --po_ver_flg,
                --cust_cse_num,
                --itt_num,
              --  fsr_tp_cd,
                --fsr_clo_dt,
                --fsR_clo_tm,
                svc_mach_mstr_pk,
                ser_num,
                svc_call_rqst_ownr_toc_cd
           INTO lv_pblm_tp_cd,lv_mach_mstr_pk,lv_ser_num,lv_sr_owner
         FROM FSR
       WHERE fsr.fsr_num = p_in_sr_num AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd;
       EXCEPTION
       WHEN OTHERS THEN
       lv_pblm_tp_cd:=NULL;
       lv_mach_mstr_pk :=NULL;
       lv_ser_num :=NULL;
       lv_sr_owner :=NULL;
    END;
 --   debug_pkg.debug_proc('lv_pblm_tp_cd:= '||lv_pblm_tp_cd);
    o_sr_owner :=lv_sr_owner;
   /*BEGIN
   SELECT MAX (SVC_TASK_NUM)
   INTO lv_task_num
                         FROM SVC_TASK
                         WHERE     FSR_NUM = p_in_sr_num
                            AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
   EXCEPTION
          WHEN OTHERS THEN
          lv_task_num:=NULL;
    END;*/
    lv_task_num:=CANON_E307_CALL_SUPPORT_PKG.GET_MAX_TASK(p_in_sr_num);
  --  debug_pkg.debug_proc('lv_pblm_tp_cd:= '||lv_pblm_tp_cd);
    BEGIN
    SELECT mdl_nm,CUST_EML_ADDR,CUST_PO_NUM
    INTO lv_mdl_nm,l_email_address,lv_po_num
     FROM SVC_TASK
    WHERE     --FSR_NUM = p_in_sr_num
        SVC_TASK_NUM=lv_task_num
    AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
   EXCEPTION
          WHEN OTHERS THEN
          lv_mdl_nm:=NULL;
          l_email_address:=NULL;
          lv_po_num:=NULL;
    END; 

   BEGIN
      SELECT RSP_TM_UP_MN_AOT
        INTO l_resp_time
        FROM SVC_MACH_MSTR ib, DS_CONTR_DTL cont
       WHERE     ser_num = lv_ser_num                         --'HHOZDYYHSH'
             AND ib.SVC_MACH_MSTR_PK = cont.SVC_MACH_MSTR_PK
             /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND cont.ezcancelflag = g_cancel_flg;

      o_resp_time := l_resp_time;
   EXCEPTION
      WHEN OTHERS
      THEN
         o_resp_time := NULL;
   END;

   BEGIN
      --SELECT 'N' INTO o_vip_flag FROM DUAL;
      SELECT DS_KEY_ACCT_FLG
        INTO o_vip_flag
        FROM SVC_MACH_MSTR ib
       WHERE     ib.ser_num = lv_ser_num                       --'TC00000001'
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/;
   EXCEPTION
      WHEN OTHERS
      THEN
         o_vip_flag := 'N';
   END;
   BEGIN
   SELECT MDL_DURN_TM_NUM
   INTO o_mdl_dur
     FROM MDL_NM mn, DS_MDL model
    WHERE     1 = 1
          AND mn.T_MDL_ID = model.MDL_ID
          AND mn.t_mdl_nm = TRIM (lv_mdl_nm)
          AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND mn.T_GLBL_CMPY_CD = g_glbl_cmpy_cd
          AND model.ezcancelflag = g_cancel_flg
       AND mn.ezcancelflag = g_cancel_flg;
    EXCEPTION
      WHEN OTHERS
      THEN
         o_mdl_dur := NULL;
   END;  
  

   BEGIN
      SELECT SVC_MEMO_TP_CD
        INTO l_special_message_type
        FROM SVC_MEMO_TP
       WHERE     SVC_MEMO_TP_NM = CANON_E307_CONSTANTS.g_special_message_name
             AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
   END;
  

   --Start fetching Customer Machine Details
   FOR fr_cur_mach_details IN cur_mach_details(lv_ser_num)
   LOOP
      --- Get Contact Info for Special Message Population
      BEGIN
         SELECT CTAC_PSN_TEL_NUM,
                CTAC_PSN_TEL_EXTN_NUM,
                CTAC_PSN_CMNT_TXT MESSAGE
           INTO l_cust_tel_num,
                l_cust_tel_ext,
                 l_special_message
           FROM SVC_MACH_CTAC_PSN
          WHERE SVC_MACH_MSTR_PK = fr_cur_mach_details.svc_mach_mstr_pk;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_tel_num := NULL;
            l_cust_tel_ext := NULL;
            l_special_message := NULL;
      END;

      l_weekday_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'MON-FRI:',
            p_time1       => fr_cur_mach_details.biz_hrs_mon_fri_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_mon_fri_to_tm,
            p_separator   => '-');

      l_sat_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'SAT:',
            p_time1       => fr_cur_mach_details.biz_hrs_sat_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_sat_to_tm,
            p_separator   => '-');

      l_sun_hours :=
         CANON_E307_UTILS.format_timerange_func (
            p_prefix      => 'SUN:',
            p_time1       => fr_cur_mach_details.biz_hrs_sun_from_tm,
            p_time2       => fr_cur_mach_details.biz_hrs_sun_to_tm,
            p_separator   => '-');
lv_sell_to_cust_cd:=fr_cur_mach_details.sell_to_cust_cd;
      l_rec_mach :=
         CANON_E307_MAC_SER_REC (fr_cur_mach_details.svc_mach_mstr_pk,
                                 fr_cur_mach_details.model,
                                 fr_cur_mach_details.ser_num,
                                 fr_cur_mach_details.cust_mach_ctrl_num,
                                 fr_cur_mach_details.solution_name,
                                 fr_cur_mach_details.ship_to_acct_no,
                                 fr_cur_mach_details.ship_to_cust_name,
                                 fr_cur_mach_details.address_1,
                                 fr_cur_mach_details.address_2,
                                 NULL,
                                 fr_cur_mach_details.city,
                                 fr_cur_mach_details.state,
                                 fr_cur_mach_details.post_cd,
                                 fr_cur_mach_details.ownr_acct_num,
                                 fr_cur_mach_details.bill_to_cust_cd,
                                 fr_cur_mach_details.sell_to_cust_cd,
                                 fr_cur_mach_details.cur_loc_num,
                                 fr_cur_mach_details.cur_loc_acct_num,
                                 l_weekday_hours,
                                 l_sat_hours,
                                 l_sun_hours,
                                 fr_cur_mach_details.last_svc_call_dt,
                                 fr_cur_mach_details.tot_svc_visit_cnt,
                                 fr_cur_mach_details.last_tech_visit_dt,
                                 fr_cur_mach_details.prf_tech_cd,
                                 fr_cur_mach_details.req_tech_cd,
                                 fr_cur_mach_details.fld_svc_br_cd,
                                 l_email_address,
                                 l_cust_tel_num,
                                 l_cust_tel_ext,
                                 l_cust_phone1,
                                 l_cust_phone2,
                                 l_cust_phone3,
                                 NULL,                      --- l_caller
                                 l_special_message,
                                 l_special_message_type,
                                 ''                                  --Contact
                                   );
      o_mach_tbl.EXTEND ();
      o_mach_tbl (ln_mach_rec_cnt) := l_rec_mach;
      ln_mach_rec_cnt := ln_mach_rec_cnt + 1;
   END LOOP;

   --Start Fetching UGW error Codes
 /*  FOR fr_cur_ugw_err_code IN cur_ugw_err_code
   LOOP
      l_rec_ugw :=
         CANON_E307_UGW_ERR_CODE_REC (fr_cur_ugw_err_code.UGW_ERR_CODE);
      o_ugw_tbl.EXTEND ();
      o_ugw_tbl (ln_ugw_rec_cnt) := l_rec_ugw;
      ln_ugw_rec_cnt := ln_ugw_rec_cnt + 1;
   END LOOP;*/
   BEGIN
   GET_UGW_ERR_CODE(lv_ser_num,x_ugw_tbl);
   o_ugw_tbl:=x_ugw_tbl;
   EXCEPTION WHEN OTHERS THEN
   NULL;
   END;
   --Start fetching Problem Details
   FOR fr_cur_prob_code IN cur_prob_code(lv_task_num)
   LOOP
   BEGIN
   SELECT spt.svc_pblm_tp_nm TYPE,
                spt.svc_pblm_grp_txt Description
                INTO lv_pblm_tp_nm,
               lv_pblm_grp_txt 
           FROM SVC_PBLM_TP spt
          WHERE     1 = 1
                AND spt.svc_pblm_tp_cd=fr_cur_prob_code.pblm_tp_cd
                AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND spt.ezcancelflag = g_cancel_flg
                 AND ROWNUM = 1;
    EXCEPTION
    WHEN OTHERS THEN
        lv_pblm_tp_nm:='';
        lv_pblm_grp_txt:='';
    END;                 
      l_rec_prob :=
         CANON_E307_PROB_CODE_REC (lv_pblm_tp_nm,
                                   lv_pblm_grp_txt,
                                   fr_cur_prob_code.pblm_tp_cd,
                                   '',
                                   '');
      o_prob_tbl.EXTEND ();
      o_prob_tbl (ln_prob_rec_cnt) := l_rec_prob;
      ln_prob_rec_cnt := ln_prob_rec_cnt + 1;
   END LOOP;
   ----Start fetching call information Details
   FOR fr_cur_call_info IN cur_call_info(lv_ser_num)
   LOOP
      global_level_recall (lv_ser_num,
                           lv_mdl_nm,
                           x_call_type,
                           x_call_type_id);
 --   debug_pkg.debug_proc('After recall:= '||lv_ser_num);                     
    get_equip_branch(lv_ser_num,lv_br_cd,lv_br_desc);

 --   debug_pkg.debug_proc('After Branch:'||lv_sell_to_cust_cd);  
    lv_po_flag:=GET_PO_REQ_FLG(lv_ser_num,lv_sell_to_cust_cd);
  
 --   debug_pkg.debug_proc('After PO REQ Flag:');  
      l_rec_call_info :=
         CANON_E307_CALL_INFO_REC (fr_cur_call_info.creation_channel,
                                   fr_cur_call_info.creation_channel_cd,
                                   x_call_type, -- fr_cur_call_info.task_type_name,
                                   x_call_type_id, --fr_cur_call_info.task_type_code,
                                   fr_cur_call_info.bill_code,
                                   fr_cur_call_info.bill_code_name,
                                   lv_po_flag,
                                   lv_po_num,
                                   fr_cur_call_info.line_of_business,
                                   lv_br_desc,
                                   lv_br_cd,
                                   fr_cur_call_info.ah_task_type,
                                   fr_cur_call_info.ah_task_code);
      o_call_info_tbl.EXTEND ();
      o_call_info_tbl (ln_call_rec_cnt) := l_rec_call_info;
      ln_call_rec_cnt := ln_call_rec_cnt + 1;
   END LOOP;
--debug_pkg.debug_proc('After Call information:= '||lv_ser_num);
   BEGIN
   GET_CONTRACT_INFO(lv_ser_num,lv_ds_contr_pk,lv_ds_contr_dtl_pk,x_contract_details_tbl);
  -- lv_ds_contr_pk:=x_ds_contr_pk;
   --lv_ds_contr_dtl_pk:=x_ds_contr_dtl_pk;
   o_contract_details_tbl:=x_contract_details_tbl;
   EXCEPTION WHEN OTHERS THEN
   NULL;
   END;
    BEGIN
      GET_CUR_LOCATION(lv_ser_num,x_cust_loc_tbl);
      o_cust_loc_tbl:=x_cust_loc_tbl;
   EXCEPTION WHEN OTHERS THEN
      NULL;
   END;
 --  debug_pkg.debug_proc('After CUR Location:= '||lv_ser_num);
   
   BEGIN
         GET_BILL_TO_LOCATION(lv_ser_num,x_bill_to_tbl);
         o_bill_to_tbl:=x_bill_to_tbl;
   EXCEPTION WHEN OTHERS THEN
         NULL;
   END;

   FOR fr_cur_notes IN cur_notes (lv_ser_num,lv_ds_contr_pk,lv_ds_contr_dtl_pk)
   LOOP
      l_rec_notes :=
         CANON_E307_DEBRIEF_NOTE_REC (fr_cur_notes.Note_Source_Id,
                                      fr_cur_notes.Note_Id,
                                      fr_cur_notes.Note_Type,
                                      fr_cur_notes.Note_Date,
                                      fr_cur_notes.Note_Text,
                                      fr_cur_notes.Created_By);
      o_notes_tbl.EXTEND ();
      o_notes_tbl (ln_note_rec_cnt) := l_rec_notes;
      ln_note_rec_cnt := ln_note_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
    --  debug_pkg.debug_proc('Inside Exception');
END COPY_FSR;

/*******************************************************************************************
 Procedure Name: GET_MACH_HIST
 Description: Get SR details to be displayed in ASCC
 Input Parameters: p_in_serial
            p_in_model

 Output Parameters: o_resp_time  
              o_vip_flag  
               o_mach_tbl  
               o_ugw_tbl   
               o_prob_tbl      
               o_call_info_tbl    
               o_contract_details_tbl  
               o_cust_loc_tbl 
               o_bill_to_tbl           
               o_notes_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
/*PROCEDURE GET_MACH_HIST (p_in_serial       IN     VARCHAR2,
                         p_in_tag          IN     VARCHAR2,
                         p_in_sol          IN     VARCHAR2,
                         p_in_model        IN     VARCHAR2,
                         p_in_acct_num     IN     VARCHAR2,
                         p_in_cust_name    IN     VARCHAR2,
                         o_mach_hist_tbl      OUT CANON_E307_MACH_HIST_TBL)
IS
   l_rec_mach_hist        CANON_E307_MACH_HIST_REC;
   ln_mach_hist_rec_cnt   NUMBER := 1;
   
   CURSOR cur_mach_hist
   IS
      SELECT FSR_NUM,
                                   CREATION_DATE,
                                   FSR_TYPE,
                                   STATUS,
                                   CUSTOMER_NAME,
                                   PROBLEM_CODE,
                                   RESPONSE_TIME,
                                   RESTORE_TIME,
                                   LAST_METER,
                                   BRANCH,
                                   OWNER,
                                   ASSIGNEE1,
                                   ASSIGNEE2,
                                   DISPATCHER,
                                   TASK_NUM1,
                                   TASK_NUM2,
                                   TASK_NUM3,
                                   RESP_TIME2,
                                   RESP_TIME3,
                                   RESTORE_TIME2,
                                   RESTORE_TIME3,
                                   ASSIGNEE3,
                                   TASK_CREATE_DATE1,
                                   TASK_CREATE_DATE2,
                                   TASK_CREATE_DATE3,
                                   EARLY_START1,
                                   EARLY_START12,
                                   EARLY_START3,
                                   LATE_START1,
                                   LATE_START2,
                                   LATE_START3,
                                   TASK_SCHEDULE_START1,
                                   TASK_SCHEDULE_START2,
                                   TASK_SCHEDULE_START3,
                                   TASK_SCHEDULE_END1,
                                   TASK_SCHEDULE_END2,
                                   TASK_SCHEDULE_END3,
                                   TASK_ACTUAL_START1,
                                   TASK_ACTUAL_START2,
                                   TASK_ACTUAL_START3,
                                   TASK_ACTUAL_END1,
                                   TASK_ACTUAL_END2,
                                   TASK_ACTUAL_END3,
                                   TASK_TYPE1,
                                   TASK_TYPE2,
                                   TASK_TYPE3
        FROM CANON_E307_MACH_HIST_V
       WHERE   1=1
       AND  NVL (SER_NUM, 'X') LIKE '%' || p_in_serial || '%'
          AND NVL (CUST_MACH_CTRL_NUM, 'X') LIKE '%' || p_in_tag || '%'
           AND NVL (SOLUTION_NAME, 'X') LIKE '%' || p_in_sol || '%'
            AND NVL (MDL_NM, 'X') LIKE '%' || p_in_model || '%'
            AND NVL (OWNR_ACCT_NUM, 'X') LIKE '%' || p_in_acct_num || '%'
             AND NVL (LOC_NM, 'X') LIKE '%' || p_in_cust_name ||'%';
            

BEGIN
debug_pkg.debug_proc('Inside GET_MACH_HIST');
   o_mach_hist_tbl := CANON_E307_MACH_HIST_TBL ();

   FOR fr_cur_mach_hist IN cur_mach_hist
   LOOP
   debug_pkg.debug_proc('Inside Loop');
      l_rec_mach_hist :=
         CANON_E307_MACH_HIST_REC (fr_cur_mach_hist.FSR_NUM,
                                   fr_cur_mach_hist.CREATION_DATE,
                                   fr_cur_mach_hist.FSR_TYPE,
                                   fr_cur_mach_hist.STATUS,
                                   fr_cur_mach_hist.CUSTOMER_NAME,
                                   fr_cur_mach_hist.PROBLEM_CODE,
                                   fr_cur_mach_hist.RESPONSE_TIME,
                                   fr_cur_mach_hist.RESTORE_TIME,
                                   fr_cur_mach_hist.LAST_METER,
                                   fr_cur_mach_hist.BRANCH,
                                   fr_cur_mach_hist.OWNER,
                                   fr_cur_mach_hist.ASSIGNEE1,
                                   fr_cur_mach_hist.ASSIGNEE2,
                                   fr_cur_mach_hist.DISPATCHER,
                                   fr_cur_mach_hist.TASK_NUM1,
                                   fr_cur_mach_hist.TASK_NUM2,
                                   fr_cur_mach_hist.TASK_NUM3,
                                   fr_cur_mach_hist.RESP_TIME2,
                                   fr_cur_mach_hist.RESP_TIME3,
                                   fr_cur_mach_hist.RESTORE_TIME2,
                                   fr_cur_mach_hist.RESTORE_TIME3,
                                   fr_cur_mach_hist.ASSIGNEE3,
                                   fr_cur_mach_hist.TASK_CREATE_DATE1,
                                   fr_cur_mach_hist.TASK_CREATE_DATE2,
                                   fr_cur_mach_hist.TASK_CREATE_DATE3,
                                   fr_cur_mach_hist.EARLY_START1,
                                   fr_cur_mach_hist.EARLY_START12,
                                   fr_cur_mach_hist.EARLY_START3,
                                   fr_cur_mach_hist.LATE_START1,
                                   fr_cur_mach_hist.LATE_START2,
                                   fr_cur_mach_hist.LATE_START3,
                                   fr_cur_mach_hist.TASK_SCHEDULE_START1,
                                   fr_cur_mach_hist.TASK_SCHEDULE_START2,
                                   fr_cur_mach_hist.TASK_SCHEDULE_START3,
                                   fr_cur_mach_hist.TASK_SCHEDULE_END1,
                                   fr_cur_mach_hist.TASK_SCHEDULE_END2,
                                   fr_cur_mach_hist.TASK_SCHEDULE_END3,
                                   fr_cur_mach_hist.TASK_ACTUAL_START1,
                                   fr_cur_mach_hist.TASK_ACTUAL_START2,
                                   fr_cur_mach_hist.TASK_ACTUAL_START3,
                                   fr_cur_mach_hist.TASK_ACTUAL_END1,
                                   fr_cur_mach_hist.TASK_ACTUAL_END2,
                                   fr_cur_mach_hist.TASK_ACTUAL_END3,
                                   fr_cur_mach_hist.TASK_TYPE1,
                                   fr_cur_mach_hist.TASK_TYPE2,
                                   fr_cur_mach_hist.TASK_TYPE3);
      debug_pkg.debug_proc('fr_cur_mach_hist.FSR_NUM ='||fr_cur_mach_hist.FSR_NUM);
      o_mach_hist_tbl.EXTEND ();
      o_mach_hist_tbl (ln_mach_hist_rec_cnt) := l_rec_mach_hist;
      ln_mach_hist_rec_cnt := ln_mach_hist_rec_cnt + 1;
   END LOOP;
   
/*CANON_E307_CALL_SUPPORT_PKG.GET_TASK_DET( '50001692',
            '1',
            lv_task_num1,
            lv_resp_tm1 );
     l_rec_mach_hist :=
         CANON_E307_MACH_HIST_REC ('36401277',
                         '11/22/2014 15:59',
                         'X-AHS',
                         'OPEN',
                         'BIG Y FOODS INC',
                         'E580',
                         lv_resp_tm1,
                         '',
                         '12423',
                         'HARTFORD',
                         'AFTER HOURS N',
                         'VANDALE, JEFFR',
                         '',
                         lv_task_num1,
                         '1222',
  '1223',
  '1224',
  '60',
  '60',
  '60',
  '60',
  'TEST',
  '11/22/2014 15:59',
  '11/23/2014 15:59',
  '11/24/2014 15:59',
  '',
  '',
  '',
 '',
  '',
  '',
  '11/22/2014 15:59',
  '11/23/2014 15:59',
  '11/24/2014 15:59',
  '11/22/2014 17:59',
  '11/23/2014 17:59',
  '11/24/2014 17:59',
  '11/22/2014 15:59',
  '11/23/2014 15:59',
  '11/24/2014 15:59',
  '11/22/2014 17:59',
  '11/23/2014 17:59',
  '11/24/2014 17:59',
  'AFTER HOURS',
  'AFTER HOURS',
  'AFTER HOURS');
      o_mach_hist_tbl.EXTEND ();
      o_mach_hist_tbl (ln_mach_hist_rec_cnt) := l_rec_mach_hist;
     -- ln_mach_hist_rec_cnt := ln_mach_hist_rec_cnt + 1;
   --END LOOP;*/
   /*EXCEPTION WHEN OTHERS
   THEN
   debug_pkg.debug_proc('Inside GET_MACH_HIST EXCEPTION');
   NULL;
END GET_MACH_HIST;*/
/*******************************************************************************************
 Procedure Name: GET_SR_HIST
 Description: Get SR details to be displayed in ASCC
 Input Parameters: p_in_serial
            p_in_model

 Output Parameters: o_resp_time  
              o_vip_flag  
               o_mach_tbl  
               o_ugw_tbl   
               o_prob_tbl      
               o_call_info_tbl    
               o_contract_details_tbl  
               o_cust_loc_tbl 
               o_bill_to_tbl           
               o_notes_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_SR_HIST (  p_in_sr_num       IN     VARCHAR2,
             p_in_task_num       IN     VARCHAR2,
             p_in_serial       IN     VARCHAR2,
                         p_in_tag          IN     VARCHAR2,
                         p_in_sol          IN     VARCHAR2,
                         p_in_model        IN     VARCHAR2,
                         p_in_acct_num     IN     VARCHAR2,
                         p_in_cust_name    IN     VARCHAR2,
                         p_start           IN     NUMBER,
             p_end             IN     NUMBER,
             p_in_sort_by      IN     VARCHAR2,
                p_in_sort_order   IN     VARCHAR2,
                x_count              OUT NUMBER,
                         o_sr_hist_tbl      OUT CANON_E307_SR_HIST_TBL)
IS
   l_rec_sr_hist        CANON_E307_SR_HIST_REC;
   ln_sr_hist_rec_cnt   NUMBER := 1;
   v_sr_hist_cursor      cur_typ;
   l_default_from     varchar2(32000);
   v_sql         varchar2(32000);
   lv_fsr_num           varchar2(100);
      lv_fsr_sts_cd         varchar2(100);
      lv_fsr_sts        varchar2(100);
      lv_fsr_type         varchar2(100);
      lv_svc_mach_mstr_pk     varchar2(100);
      lv_ser_num        varchar2(100);
      lv_cust_mach_ctrl_num    varchar2(100);
      lv_svc_sln_nm        varchar2(500);
      lv_t_mdl_nm        varchar2(100);
      lv_tech_cd        varchar2(100);
      lv_fsr_creation_date    varchar2(100);
      lv_fsr_cplt_date    varchar2(100);
      lv_bill_to_cust_cd    varchar2(100);
      lv_sell_to_cust_cd    varchar2(100);
      lv_ship_to_cust_cd    varchar2(100);
      lv_ownr_acct_num    varchar2(100);
      lv_cur_loc_acct_num    varchar2(100);
      lv_customer_name    varchar2(100);
      lv_pmt_term_cash_disc_cd    varchar2(100);
      lv_istl_sts_upd_cplt_flg    varchar2(100);
      lv_svc_call_src_tp_cd    varchar2(100);
      lv_svc_pblm_tp_cd    varchar2(100);
      lv_pblm_tp_nm    varchar2(500);
      lv_svc_call_avoid_cd    varchar2(100);
      lv_svc_call_rqst_ownr_toc_cd    varchar2(100);
      lv_sr_owner_nm varchar2(500);
      lv_incident_date    varchar2(100);
      lv_po_ver_flg    varchar2(100);
      lv_cust_cse_num    varchar2(100);
      lv_itt_num    varchar2(100);
      lv_bill_to_cust_upd_flg    varchar2(100);
      lv_ship_to_cust_upd_flg    varchar2(100);
      lv_bill_to_upd_cust_cd    varchar2(100);
      lv_ship_to_upd_cust_cd    varchar2(100);
      lv_bill_to_cust_acct_cd    varchar2(100);
      lv_ship_to_cust_acct_cd    varchar2(100);
      lv_fsr_tp_cd    varchar2(100);
      lv_fsr_clo_dt    varchar2(100);
      lv_last_meter    varchar2(100);
      lv_branch    VARCHAR2(100);
   lv_dispatcher         varchar2(100);
    l_order_by         VARCHAR2 (100);
   l_asc_desc_order   VARCHAR2 (100);    
   l_sql_count_qry    VARCHAR2 (32000);
   lv_sr_num          VARCHAR2(100);
   lv_task_num          VARCHAR2(100);
    lv_serial        VARCHAR2(100);
    lv_eid    varchar2(100);
    lv_sol        varchar2(500);
    lv_model    varchar2(100);
    lv_acct_num    varchar2(100);
    lv_cust_nm    varchar2(100);

BEGIN
l_order_by := p_in_sort_by;
l_asc_desc_order := p_in_sort_order;
--debug_pkg.debug_proc('Inside GET_SR_HIST');
o_sr_hist_tbl := CANON_E307_SR_HIST_TBL ();
lv_serial:=trim(p_in_serial); 
lv_eid :=trim(p_in_tag);
lv_sol    :=trim(p_in_sol);
lv_model    :=trim(p_in_model);
lv_acct_num    :=trim(p_in_acct_num);
lv_cust_nm    :=trim(p_in_cust_name);
lv_sr_num    :=trim(p_in_sr_num);
lv_task_num    :=trim(p_in_task_num);

/*IF lv_sr_num IS NULL and lv_task_num IS NOT NULL
   THEN
   SELECT distinct fsr_num
   into lv_sr_num
   from svc_task
   where SVC_TASK_NUM=lv_task_num
    AND glbl_cmpy_cd = g_glbl_cmpy_cd;
   END IF;*/
l_default_from :=
               'FROM (SELECT * FROM canon_e307_sr_hist_v sr '
            || 'where 1=1 AND UPPER(NVL (SER_NUM, ''X'')) LIKE  UPPER( ''%' || lv_serial || '%'' )'
            || ' AND UPPER(NVL (CUST_MACH_CTRL_NUM, ''X'')) LIKE UPPER(''%' || lv_eid || '%'') '
            || ' AND UPPER(NVL (SVC_SLN_NM, ''X'')) LIKE UPPER(''%' || lv_sol || '%'' )'
        || ' AND UPPER(NVL (T_MDL_NM, ''X'')) LIKE UPPER(''%' || lv_model || '%'' )'
        || ' AND UPPER(NVL (OWNR_ACCT_NUM, ''X'')) LIKE UPPER(''%' || lv_acct_num || '%'' )'
            || ' AND UPPER(NVL (CUSTOMER_NAME, ''X'')) LIKE UPPER(''%' || lv_cust_nm ||'%'' )'
            || ' AND NVL (FSR_NUM, ''X'') LIKE ''%' || lv_sr_num ||'%'' '
            || ' AND NVL (FSR_NUM, ''X'') IN (SELECT distinct fsr_num 
                                FROM svc_task
                           WHERE SVC_TASK_NUM like ''%' || lv_task_num ||'%'' '
                           || ' AND nvl(glbl_cmpy_cd,''ADB'') = '''
                                  || g_glbl_cmpy_cd
                              || ''''
                           || ' ) '
            ;
            
 v_sql :=
         'SELECT dtls.*,rownum row_num '
      || l_default_from;    
   IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||' )dtls ';
   ELSE
      v_sql := v_sql || ' ORDER BY fsr_creation_date desc, fsr_num desc)dtls ';
   END IF;
   
            v_sql := 'SELECT fsr_num,fsr_sts_cd,fsr_sts,fsr_type,svc_mach_mstr_pk,ser_num,cust_mach_ctrl_num,
               svc_sln_nm,t_mdl_nm,tech_cd,fsr_creation_date,fsr_cplt_date,bill_to_cust_cd,sell_to_cust_cd,
               ship_to_cust_cd,ownr_acct_num,cur_loc_acct_num,customer_name,pmt_term_cash_disc_cd,
               istl_sts_upd_cplt_flg,svc_call_src_tp_cd,svc_pblm_tp_cd,problem_type_name,svc_call_avoid_cd,
               svc_call_rqst_ownr_toc_cd,owner_name,incident_date,po_ver_flg,cust_cse_num,itt_num,
               bill_to_cust_upd_flg,ship_to_cust_upd_flg,bill_to_upd_cust_cd,ship_to_upd_cust_cd,
               bill_to_cust_acct_cd,ship_to_cust_acct_cd,fsr_tp_cd,fsr_clo_dt,last_meter,branch,
               dispatcher FROM( '
                 ||    v_sql
                  || ' )  WHERE row_num BETWEEN '
                  || p_start
                  || ' AND '
            || p_end;
   --debug_pkg.debug_proc('v_sql= '||v_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from||' ) ';
--debug_pkg.debug_proc('l_sql_count_qry= '||l_sql_count_qry);
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
  OPEN v_sr_hist_cursor FOR v_sql;
  
     LOOP
     -- debug_pkg.debug_proc('Inside Loop');
        FETCH v_sr_hist_cursor
           INTO lv_fsr_num,
   lv_fsr_sts_cd,
   lv_fsr_sts,
   lv_fsr_type,
   lv_svc_mach_mstr_pk,
   lv_ser_num,
   lv_cust_mach_ctrl_num,
   lv_svc_sln_nm,
   lv_t_mdl_nm,
   lv_tech_cd,
   lv_fsr_creation_date,
   lv_fsr_cplt_date,
   lv_bill_to_cust_cd,
   lv_sell_to_cust_cd,
   lv_ship_to_cust_cd,
   lv_ownr_acct_num,
   lv_cur_loc_acct_num,
   lv_customer_name,
   lv_pmt_term_cash_disc_cd,
   lv_istl_sts_upd_cplt_flg,
   lv_svc_call_src_tp_cd,
   lv_svc_pblm_tp_cd,
   lv_pblm_tp_nm,
   lv_svc_call_avoid_cd,
   lv_svc_call_rqst_ownr_toc_cd,
   lv_sr_owner_nm,
   lv_incident_date,
   lv_po_ver_flg,
   lv_cust_cse_num,
   lv_itt_num,
   lv_bill_to_cust_upd_flg,
   lv_ship_to_cust_upd_flg,
   lv_bill_to_upd_cust_cd,
   lv_ship_to_upd_cust_cd,
   lv_bill_to_cust_acct_cd,
   lv_ship_to_cust_acct_cd,
   lv_fsr_tp_cd,
   lv_fsr_clo_dt,
   lv_last_meter,
   lv_branch,
   lv_dispatcher;
       EXIT WHEN v_sr_hist_cursor%NOTFOUND;
   
      l_rec_sr_hist :=
         CANON_E307_SR_HIST_REC (lv_fsr_num,
   lv_fsr_sts_cd,
   lv_fsr_sts,
   lv_fsr_type,
   lv_svc_mach_mstr_pk,
   lv_ser_num,
   lv_cust_mach_ctrl_num,
   lv_svc_sln_nm,
   lv_t_mdl_nm,
   lv_tech_cd,
   lv_fsr_creation_date,
   lv_fsr_cplt_date,
   lv_bill_to_cust_cd,
   lv_sell_to_cust_cd,
   lv_ship_to_cust_cd,
   lv_ownr_acct_num,
   lv_cur_loc_acct_num,
   lv_customer_name,
   lv_pmt_term_cash_disc_cd,
   lv_istl_sts_upd_cplt_flg,
   lv_svc_call_src_tp_cd,
   lv_svc_pblm_tp_cd,
   lv_pblm_tp_nm,
   lv_svc_call_avoid_cd,
   lv_svc_call_rqst_ownr_toc_cd,
   lv_sr_owner_nm,
   lv_incident_date,
   lv_po_ver_flg,
   lv_cust_cse_num,
   lv_itt_num,
   lv_bill_to_cust_upd_flg,
   lv_ship_to_cust_upd_flg,
   lv_bill_to_upd_cust_cd,
   lv_ship_to_upd_cust_cd,
   lv_bill_to_cust_acct_cd,
   lv_ship_to_cust_acct_cd,
   lv_fsr_tp_cd,
   lv_fsr_clo_dt,
   lv_last_meter,
   lv_branch,
   lv_dispatcher);
      --debug_pkg.debug_proc('fr_cur_sr_hist.FSR_NUM ='||lv_fsr_num);
      o_sr_hist_tbl.EXTEND ();
      o_sr_hist_tbl (ln_sr_hist_rec_cnt) := l_rec_sr_hist;
      ln_sr_hist_rec_cnt := ln_sr_hist_rec_cnt + 1;
   END LOOP;
   EXCEPTION WHEN OTHERS
   THEN
  -- debug_pkg.debug_proc('Inside GET_MACH_HIST EXCEPTION');
   NULL;
END GET_SR_HIST;

/*******************************************************************************************
 Procedure Name: GET_SR_HIST
 Description: Get SR details to be displayed in ASCC
 Input Parameters: p_in_serial
            p_in_model

 Output Parameters: o_resp_time  
              o_vip_flag  
               o_mach_tbl  
               o_ugw_tbl   
               o_prob_tbl      
               o_call_info_tbl    
               o_contract_details_tbl  
               o_cust_loc_tbl 
               o_bill_to_tbl           
               o_notes_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_TASK_HIST (p_in_sr_num       IN     VARCHAR2,
                         p_start           IN     NUMBER,
             p_end             IN     NUMBER,
             p_in_sort_by      IN     VARCHAR2,
                p_in_sort_order   IN     VARCHAR2,
                x_count              OUT NUMBER,
                         o_tsk_hist_tbl      OUT CANON_E307_TSK_HIST_TBL)
IS
   l_rec_tsk_hist        CANON_E307_TSK_HIST_REC;
   ln_tsk_hist_rec_cnt   NUMBER := 1;
   v_tsk_hist_cursor      cur_typ;
   l_default_from     varchar2(32000);
   v_sql         varchar2(32000);
   l_order_by         VARCHAR2 (100);
   l_asc_desc_order   VARCHAR2 (100);    
   l_sql_count_qry    VARCHAR2 (32000);
    lv_svc_task_num        svc_task.svc_task_num%TYPE;
       lv_fsr_num    fsr.fsr_num%TYPE;
       lv_svc_task_sts_cd    svc_task.svc_task_sts_cd%TYPE;
       lv_task_crat_dt        varchar2 (30);
       lv_svc_mach_mstr_pk    svc_task.svc_mach_mstr_pk%TYPE;
       lv_cust_mach_ctrl_num    svc_task.cust_mach_ctrl_num%TYPE;
       lv_ser_num        svc_task.ser_num%TYPE;
       lv_mdl_nm        svc_task.mdl_nm%TYPE;
       lv_mdl_grp_nm        svc_task.mdl_grp_nm%TYPE;
       lv_mdse_cd        svc_task.mdse_cd%TYPE;
       lv_ship_to_cust_cd    svc_task.ship_to_cust_cd%TYPE;
       lv_cust_tel_num        svc_task.cust_tel_num%TYPE;
       lv_cust_tel_extn_num    svc_task.cust_tel_extn_num%TYPE;
       lv_svc_cust_attn_txt    svc_task.svc_cust_attn_txt%TYPE;
       lv_cust_eml_addr        svc_task.cust_eml_addr%TYPE;
       lv_cust_po_num        svc_task.cust_po_num%TYPE;
       lv_cust_po_dt        svc_task.cust_po_dt%TYPE;
       lv_ds_svc_call_tp_cd    svc_task.ds_svc_call_tp_cd%TYPE;
       lv_task_type_nm        VARCHAR2 (50);
       lv_svc_bill_tp_cd    svc_task.svc_bill_tp_cd%TYPE;
       lv_svc_pblm_symp_tp_cd    svc_task.svc_pblm_symp_tp_cd%TYPE;
       lv_tech_cd        svc_task.tech_cd%TYPE;
       lv_cust_aval_from_hour_mn    svc_task.cust_aval_from_hour_mn%TYPE;
       lv_cust_aval_to_hour_mn    svc_task.cust_aval_to_hour_mn%TYPE;
       lv_svc_task_rcv_dt        VARCHAR2 (30);
       lv_svc_task_schd_dt        VARCHAR2 (30);
       lv_svc_task_cplt_dt        VARCHAR2 (30);
       lv_svc_task_clo_dt        VARCHAR2 (30);
       lv_svc_task_schd_by_usr_id    svc_task.svc_task_schd_by_usr_id%TYPE;
       lv_svc_task_clo_by_usr_id    svc_task.svc_task_clo_by_usr_id%TYPE;
       lv_response_time        svc_task.svc_rsp_tm_mn_aot%TYPE;
       lv_restore_time        varchar2 (30);
       lv_mach_down_flg        varchar2 (30);
       lv_erl_start_ts        varchar2 (30);
       lv_late_start_ts        varchar2 (30);
       lv_svc_rg_cd        varchar2 (30);
       lv_svc_br_cd        varchar2 (30);
       lv_svc_team_cd        varchar2 (30);
       lv_svc_br_mgr_psn_cd    varchar2 (30);
       lv_svc_trty_mgr_psn_cd    varchar2 (30);
       lv_svc_team_mgr_psn_cd    varchar2 (30);
       lv_fsr_visit_num        varchar2 (30);
       lv_fsr_visit_sts_cd    varchar2 (30);
       lv_visit_tech_cd        varchar2 (30);
       lv_assignee        varchar2 (100);
       lv_tech_schd_from_dt    varchar2 (30);
       lv_tech_schd_to_dt    varchar2 (30);
       lv_actual_start_date    varchar2 (30);
       lv_actual_end_date    varchar2 (30);
       lv_svc_asg_tp_cd        varchar2 (30);
BEGIN
l_order_by := p_in_sort_by;
l_asc_desc_order := p_in_sort_order;
  o_tsk_hist_tbl := CANON_E307_TSK_HIST_TBL ();
l_default_from :=
               ' FROM (SELECT task.* '
            || 'FROM canon_e307_task_hist_v task '
            || 'where 1=1 AND FSR_NUM = ''' || p_in_sr_num || ''' '
            ;
            
 v_sql :=
         'SELECT dtls.*,rownum row_num '
      || l_default_from;    
   IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||' )dtls ';
   ELSE
      v_sql := v_sql || ' ORDER BY svc_task_num) dtls ';
   END IF;
   
      IF p_start IS NOT NULL AND p_end IS NOT NULL
      THEN
              v_sql := 'SELECT svc_task_num, fsr_num, svc_task_sts_cd, task_crat_dt, svc_mach_mstr_pk, 
                    cust_mach_ctrl_num, ser_num, mdl_nm, mdl_grp_nm, mdse_cd, ship_to_cust_cd, 
                    cust_tel_num, cust_tel_extn_num, svc_cust_attn_txt, cust_eml_addr, cust_po_num, 
                    cust_po_dt, ds_svc_call_tp_cd, task_type_nm, svc_bill_tp_cd, svc_pblm_symp_tp_cd, 
                    tech_cd, cust_aval_from_hour_mn, cust_aval_to_hour_mn, svc_task_rcv_dt, 
                    svc_task_schd_dt, svc_task_cplt_dt, svc_task_clo_dt, svc_task_schd_by_usr_id, 
                    svc_task_clo_by_usr_id, svc_rsp_tm_mn_aot, restore_tm, mach_down_flg, erl_start_ts, 
                    late_start_ts, svc_rg_cd, svc_br_cd, svc_team_cd, svc_br_mgr_psn_cd, svc_trty_mgr_psn_cd, 
                    svc_team_mgr_psn_cd, fsr_visit_num, fsr_visit_sts_cd, visit_tech_cd, assignee_name, 
                    tech_schd_from_dt, tech_schd_to_dt, task_actual_start, task_actual_end, svc_asg_tp_cd FROM( '
                 ||    v_sql
                  || ' )  WHERE row_num BETWEEN '
                  || p_start
                  || ' AND '
            || p_end;
            
      ELSE
            v_sql := 'SELECT svc_task_num, fsr_num, svc_task_sts_cd, task_crat_dt, svc_mach_mstr_pk, 
                    cust_mach_ctrl_num, ser_num, mdl_nm, mdl_grp_nm, mdse_cd, ship_to_cust_cd, 
                    cust_tel_num, cust_tel_extn_num, svc_cust_attn_txt, cust_eml_addr, cust_po_num, 
                    cust_po_dt, ds_svc_call_tp_cd, task_type_nm, svc_bill_tp_cd, svc_pblm_symp_tp_cd, 
                    tech_cd, cust_aval_from_hour_mn, cust_aval_to_hour_mn, svc_task_rcv_dt, 
                    svc_task_schd_dt, svc_task_cplt_dt, svc_task_clo_dt, svc_task_schd_by_usr_id, 
                    svc_task_clo_by_usr_id, svc_rsp_tm_mn_aot, restore_tm, mach_down_flg, erl_start_ts, 
                    late_start_ts, svc_rg_cd, svc_br_cd, svc_team_cd, svc_br_mgr_psn_cd, svc_trty_mgr_psn_cd, 
                    svc_team_mgr_psn_cd, fsr_visit_num, fsr_visit_sts_cd, visit_tech_cd, assignee_name, 
                    tech_schd_from_dt, tech_schd_to_dt, task_actual_start, task_actual_end, svc_asg_tp_cd FROM( '
              ||    v_sql
               || ' ) ';        
         
   END IF;      
  -- debug_pkg.debug_proc('v_sql = '||v_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from||' ) ';
--debug_pkg.debug_proc('l_sql_count_qry='||l_sql_count_qry);
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
  OPEN v_tsk_hist_cursor FOR v_sql;
  
     LOOP
    --  debug_pkg.debug_proc('Inside Loop');
  FETCH v_tsk_hist_cursor
  INTO lv_svc_task_num,
  lv_fsr_num,
  lv_svc_task_sts_cd,
  lv_task_crat_dt,
  lv_svc_mach_mstr_pk,
  lv_cust_mach_ctrl_num,
  lv_ser_num,
  lv_mdl_nm,
  lv_mdl_grp_nm,
  lv_mdse_cd,
  lv_ship_to_cust_cd,
  lv_cust_tel_num,
  lv_cust_tel_extn_num,
  lv_svc_cust_attn_txt,
  lv_cust_eml_addr,
  lv_cust_po_num,
  lv_cust_po_dt,
  lv_ds_svc_call_tp_cd,
  lv_task_type_nm,
  lv_svc_bill_tp_cd,
  lv_svc_pblm_symp_tp_cd,
  lv_tech_cd,
  lv_cust_aval_from_hour_mn,
  lv_cust_aval_to_hour_mn,
  lv_svc_task_rcv_dt,
  lv_svc_task_schd_dt,
  lv_svc_task_cplt_dt,
  lv_svc_task_clo_dt,
  lv_svc_task_schd_by_usr_id,
  lv_svc_task_clo_by_usr_id,
  lv_response_time,
  lv_restore_time,
  lv_mach_down_flg,
  lv_erl_start_ts,
  lv_late_start_ts,
  lv_svc_rg_cd,
  lv_svc_br_cd,
  lv_svc_team_cd,
  lv_svc_br_mgr_psn_cd,
  lv_svc_trty_mgr_psn_cd,
  lv_svc_team_mgr_psn_cd,
  lv_fsr_visit_num,
  lv_fsr_visit_sts_cd,
  lv_visit_tech_cd,
  lv_assignee,
  lv_tech_schd_from_dt,
  lv_tech_schd_to_dt,
  lv_actual_start_date,
  lv_actual_end_date,
  lv_svc_asg_tp_cd;
  EXIT WHEN v_tsk_hist_cursor%NOTFOUND;
   
  l_rec_tsk_hist :=
         CANON_E307_TSK_HIST_REC (lv_svc_task_num,
  lv_fsr_num,
  lv_svc_task_sts_cd,
  lv_task_crat_dt,
  lv_svc_mach_mstr_pk,
  lv_cust_mach_ctrl_num,
  lv_ser_num,
  lv_mdl_nm,
  lv_mdl_grp_nm,
  lv_mdse_cd,
  lv_ship_to_cust_cd,
  lv_cust_tel_num,
  lv_cust_tel_extn_num,
  lv_svc_cust_attn_txt,
  lv_cust_eml_addr,
  lv_cust_po_num,
  lv_cust_po_dt,
  lv_ds_svc_call_tp_cd,
  lv_task_type_nm,
  lv_svc_bill_tp_cd,
  lv_svc_pblm_symp_tp_cd,
  lv_tech_cd,
  lv_cust_aval_from_hour_mn,
  lv_cust_aval_to_hour_mn,
  lv_svc_task_rcv_dt,
  lv_svc_task_schd_dt,
  lv_svc_task_cplt_dt,
  lv_svc_task_clo_dt,
  lv_svc_task_schd_by_usr_id,
  lv_svc_task_clo_by_usr_id,
  lv_response_time,
  lv_restore_time,
  lv_mach_down_flg,
  lv_erl_start_ts,
  lv_late_start_ts,
  lv_svc_rg_cd,
  lv_svc_br_cd,
  lv_svc_team_cd,
  lv_svc_br_mgr_psn_cd,
  lv_svc_trty_mgr_psn_cd,
  lv_svc_team_mgr_psn_cd,
  lv_fsr_visit_num,
  lv_fsr_visit_sts_cd,
  lv_visit_tech_cd,
  lv_assignee,
  lv_tech_schd_from_dt,
  lv_tech_schd_to_dt,
  lv_actual_start_date,
  lv_actual_end_date,
  lv_svc_asg_tp_cd);
      --debug_pkg.debug_proc('fr_cur_sr_hist.FSR_NUM ='||lv_fsr_num);
  o_tsk_hist_tbl.EXTEND ();
  o_tsk_hist_tbl (ln_tsk_hist_rec_cnt) := l_rec_tsk_hist;
  ln_tsk_hist_rec_cnt := ln_tsk_hist_rec_cnt + 1;
   END LOOP;
   EXCEPTION WHEN OTHERS
   THEN
  -- debug_pkg.debug_proc('Inside GET_MACH_HIST EXCEPTION');
   NULL;
END GET_TASK_HIST;
/*******************************************************************************************
 Procedure Name: SERIAL_LOV
 Description: Get details for Assignee LOV
 Input Parameters: p_in_task_no

 Output Parameters: Email Address
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE SERIAL_LOV (
   p_in_serial    IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
 --  p_in_sort_by      IN     VARCHAR2,
   --p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_ser_tbl      OUT CANON_E307_LOV_VAL_TBL)
IS
   l_rec_serial    CANON_E307_LOV_VAL_REC;
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   v_ser_cursor      cur_typ;
   ln_rec_cnt1        NUMBER := 1;
   lv_serial        VARCHAR2 (100);
  -- l_order_by         VARCHAR2 (100);
  -- l_asc_desc_order   VARCHAR2 (100);
BEGIN
   o_ser_tbl := CANON_E307_LOV_VAL_TBL ();
  -- l_order_by := p_in_sort_by;
 --  l_asc_desc_order := p_in_sort_order;
 l_default_from :=
               ' FROM (SELECT ib.* '
            || 'FROM svc_mach_mstr ib '
            || 'where upper(ser_num) like upper( '
            || '''%'
            || p_in_serial
            || '%'' ) '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
      || ''' ) ';

   v_sql :=
         'SELECT ser_num,rownum row_num '
      || l_default_from;
      

  /*IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||') ';
   ELSE
      v_sql := v_sql || ' ORDER BY tech_nm) ';
   END IF;*/
   
   IF p_start IS NOT NULL AND p_end IS NOT NULL
   THEN
         v_sql := 'SELECT ser_num FROM( '
              ||    v_sql
               || ' )  WHERE row_num BETWEEN '
               || p_start
               || ' AND '
            || p_end;
   ELSE
   v_sql := 'SELECT ser_num FROM( '
              ||    v_sql
               || ' ) ';        
         
   END IF;    
   
   l_sql_count_qry := ' select count(*) ' || l_default_from||')';

   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_ser_cursor FOR v_sql;

   LOOP
      FETCH v_ser_cursor
         INTO lv_serial;

      EXIT WHEN v_ser_cursor%NOTFOUND;
      l_rec_serial :=
         CANON_E307_LOV_VAL_REC (lv_serial);
      o_ser_tbl.EXTEND ();
      o_ser_tbl (ln_rec_cnt1) := l_rec_serial;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END SERIAL_LOV;
/*******************************************************************************************
 Procedure Name: TAG_LOV
 Description: Get details for Assignee LOV
 Input Parameters: p_in_task_no

 Output Parameters: Email Address
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE TAG_LOV (
   p_in_tag    IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
 --  p_in_sort_by      IN     VARCHAR2,
   --p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_tag_tbl      OUT CANON_E307_LOV_VAL_TBL)
IS
   l_rec_tag    CANON_E307_LOV_VAL_REC;
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   v_tag_cursor      cur_typ;
   ln_rec_cnt1        NUMBER := 1;
   lv_tag        svc_mach_mstr.cust_mach_ctrl_num%TYPE;
  -- l_order_by         VARCHAR2 (100);
  -- l_asc_desc_order   VARCHAR2 (100);
BEGIN
   o_tag_tbl := CANON_E307_LOV_VAL_TBL ();
  -- l_order_by := p_in_sort_by;
 --  l_asc_desc_order := p_in_sort_order;
 l_default_from :=
               ' FROM (SELECT rownum row_num,ib.* '
            || 'FROM svc_mach_mstr ib '
            || 'where upper(cust_mach_ctrl_num) like upper( '
            || '''%'
            || p_in_tag
            || '%'' ) '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
      || ''' ) ';

   v_sql :=
         'SELECT cust_mach_ctrl_num '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;

 /*  IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
   ELSE
      v_sql := v_sql || ' ORDER BY tech_nm';
   END IF;*/
   
   l_sql_count_qry := ' select count(*) ' || l_default_from;

   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_tag_cursor FOR v_sql;

   LOOP
      FETCH v_tag_cursor
         INTO lv_tag;

      EXIT WHEN v_tag_cursor%NOTFOUND;
      l_rec_tag :=
         CANON_E307_LOV_VAL_REC (lv_tag);
      o_tag_tbl.EXTEND ();
      o_tag_tbl (ln_rec_cnt1) := l_rec_tag;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END TAG_LOV;
/*******************************************************************************************
 Procedure Name: SLN_LOV
 Description: Get details for Assignee LOV
 Input Parameters: p_in_task_no

 Output Parameters: Email Address
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE SLN_LOV (
   p_in_sln    IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
 --  p_in_sort_by      IN     VARCHAR2,
   --p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_sln_tbl      OUT CANON_E307_LOV_VAL_TBL)
IS
   l_rec_sln    CANON_E307_LOV_VAL_REC;
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   v_sln_cursor      cur_typ;
   ln_rec_cnt1        NUMBER := 1;
   lv_sln        svc_config_mstr.svc_sln_nm%TYPE;
  -- l_order_by         VARCHAR2 (100);
  -- l_asc_desc_order   VARCHAR2 (100);
BEGIN
   o_sln_tbl := CANON_E307_LOV_VAL_TBL ();
  -- l_order_by := p_in_sort_by;
 --  l_asc_desc_order := p_in_sort_order;
 l_default_from :=
               ' FROM (SELECT rownum row_num,config.* '
            || 'FROM svc_mach_mstr ib, svc_config_mstr config '
            || 'where upper(svc_sln_nm) like upper( '
            || '''%'
            || p_in_sln
            || '%'' ) '
            || ' AND ib.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND config.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND config.glbl_cmpy_cd ='''
                    || g_glbl_cmpy_cd
              || ''
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
              || ''' ) ';

   v_sql :=
         'SELECT svc_sln_nm '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;

 /*  IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
   ELSE
      v_sql := v_sql || ' ORDER BY tech_nm';
   END IF;*/
   
   l_sql_count_qry := ' select count(*) ' || l_default_from;

   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_sln_cursor FOR v_sql;

   LOOP
      FETCH v_sln_cursor
         INTO lv_sln;

      EXIT WHEN v_sln_cursor%NOTFOUND;
      l_rec_sln :=
         CANON_E307_LOV_VAL_REC (lv_sln);
      o_sln_tbl.EXTEND ();
      o_sln_tbl (ln_rec_cnt1) := l_rec_sln;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END SLN_LOV;
/*******************************************************************************************
 Procedure Name: SERIAL_LOV
 Description: Get details for Assignee LOV
 Input Parameters: p_in_task_no

 Output Parameters: Email Address
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
PROCEDURE SR_HIST_LOV (
   p_in_attr   IN     VARCHAR2,
   p_in_val    IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
 --  p_in_sort_by      IN     VARCHAR2,
   --p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL)
IS
   l_rec_val    CANON_E307_LOV_VAL_REC;
   v_sql              VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
   l_sql_count_qry    VARCHAR2 (32000);
   v_val_cursor      cur_typ;
   ln_rec_cnt1        NUMBER := 1;
   lv_val        VARCHAR2 (1000);
  -- l_order_by         VARCHAR2 (100);
  -- l_asc_desc_order   VARCHAR2 (100);
  lv_value     VARCHAR2 (1000);
BEGIN
   o_lov_tbl := CANON_E307_LOV_VAL_TBL ();
  -- l_order_by := p_in_sort_by;
 --  l_asc_desc_order := p_in_sort_order;
 lv_value:=trim(p_in_val);
 IF upper(p_in_attr) = 'SERIAL'
 THEN
 l_default_from :=
               ' FROM (SELECT rownum row_num,SER_NUM '
            || 'FROM (SELECT DISTINCT SER_NUM FROM svc_mach_mstr ib '
            || 'where upper(ser_num) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
      || ''' )) ';

   v_sql :=
         'SELECT  ser_num '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;
ELSIF   upper(p_in_attr) = 'TAG'  
THEN
l_default_from :=
               ' FROM (SELECT rownum row_num,ib.* '
               ||' FROM (SELECT distinct ib.cust_mach_ctrl_num '
            || 'FROM svc_mach_mstr ib '
            || 'where upper(cust_mach_ctrl_num) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
      || ''' )ib ) ';

   v_sql :=
         'SELECT  cust_mach_ctrl_num '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;
ELSIF  upper(p_in_attr) = 'SOLUTION'  
THEN
l_default_from :=
               ' FROM (SELECT rownum row_num,config.* '
            || 'FROM svc_mach_mstr ib, svc_config_mstr config '
            || 'where upper(svc_sln_nm) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND config.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND config.glbl_cmpy_cd ='''
                    || g_glbl_cmpy_cd
              || ''''
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
              || ''' ) ';

   v_sql :=
         'SELECT  svc_sln_nm '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;
ELSIF  upper(p_in_attr) = 'MODEL'  
THEN
l_default_from :=
               ' FROM (SELECT rownum row_num,mdl.* '
            || 'FROM svc_mach_mstr ib, svc_config_mstr config,mdl_nm mdl '
            || 'where upper(svc_sln_nm) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.svc_config_mstr_pk = config.svc_config_mstr_pk '
            || ' AND config.mdl_id = mdl.t_mdl_id  '
            || ' AND ib.ezcancelflag= '
            || g_cancel_flg
            || ' AND config.ezcancelflag= '
            || g_cancel_flg
            || ' AND mdl.ezcancelflag= '
            || g_cancel_flg
            || ' AND config.glbl_cmpy_cd ='''
                    || g_glbl_cmpy_cd
              || ''''
              || ' AND mdl.t_glbl_cmpy_cd ='''
                            || g_glbl_cmpy_cd
              || ''''
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
              || ''' ) ';

   v_sql :=
         'SELECT  t_mdl_nm '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;
ELSIF upper(p_in_attr) = 'ACCTNO'
 THEN
 l_default_from :=
               ' FROM (SELECT rownum row_num,ib.* '
            || 'FROM svc_mach_mstr ib '
            || 'where upper(ownr_acct_num) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
             || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
      || ''' ) ';

   v_sql :=
         'SELECT ownr_acct_num '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;
ELSIF upper(p_in_attr) = 'CUSTNM'
 THEN
/* l_default_from :=
               ' FROM (SELECT rownum row_num,sell_to.* '
            || 'FROM svc_mach_mstr ib, sell_to_cust sell_to '
            || 'where upper(sell_to.loc_nm) like upper( '
            || '''%'
            || p_in_val
            || '%'' ) '
            || ' AND ib.sell_to_cust_cd = sell_to.sell_to_cust_cd' 
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
      || ''' ) ';

   v_sql :=
         'SELECT loc_nm '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;   */
      
   l_default_from :=
         ' FROM (SELECT rownum row_num,loc_nm '
      || 'FROM (SELECT distinct loc_nm FROM sell_to_cust sell_to '
      || 'where upper(LOC_NM) like upper( '
      || '''%'
      || lv_value
      || '%'' ) '
      || ' AND NVL (sell_to.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
      || ' AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
      || 'AND sell_to.EZCANCELFLAG= '
      || g_cancel_flg
      || ' AND sell_to.glbl_cmpy_cd ='''
      || g_glbl_cmpy_cd
      || ''' )) ';

   v_sql :=
         'SELECT LOC_NM '
      || l_default_from
      || '  WHERE row_num BETWEEN '
      || p_start
      || ' AND '
      || p_end;      
END IF;
 /*  IF l_order_by IS NOT NULL
   THEN
      v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
   ELSE
      v_sql := v_sql || ' ORDER BY tech_nm';
   END IF;*/
   
   l_sql_count_qry := ' select count(*) ' || l_default_from;

   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_val_cursor FOR v_sql;

   LOOP
      FETCH v_val_cursor
         INTO lv_val;

      EXIT WHEN v_val_cursor%NOTFOUND;
      l_rec_val :=
         CANON_E307_LOV_VAL_REC (lv_val);
      o_lov_tbl.EXTEND ();
      o_lov_tbl (ln_rec_cnt1) := l_rec_val;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END SR_HIST_LOV;
/*******************************************************************************************
 Procedure Name: GET_BILL_TO_LOCATION
 Description: Get bill to location for a serial
 Input Parameters: p_in_serial
            
 Output Parameters: x_bill_to_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE TYPE_LOV (
   x_type_tbl      OUT CANON_E307_TYPE_LOV_TBL)
IS
   l_rec_task_type   CANON_E307_TYPE_LOV_REC;
   ln_type_rec_cnt      NUMBER := 1;

   CURSOR cur_task_type
   IS
      SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm, svc_call_prty_cd
      FROM ds_svc_call_tp
      WHERE 1=1
      AND ascc_sel_flg = 'Y'
      AND glbl_cmpy_cd =g_glbl_cmpy_cd
      AND ezcancelflag=g_cancel_flg;

BEGIN
   x_type_tbl := CANON_E307_TYPE_LOV_TBL ();

   --debug_pkg.debug_proc('Inside  type_lov');
   --Start Fetching Task Type LOV
   FOR fr_cur_task_type IN cur_task_type
   LOOP
      l_rec_task_type :=
         CANON_E307_TYPE_LOV_REC (fr_cur_task_type.ds_svc_call_tp_cd,
                                  fr_cur_task_type.ds_svc_call_tp_nm,
                                  fr_cur_task_type.svc_call_prty_cd);
      x_type_tbl.EXTEND ();
      x_type_tbl (ln_type_rec_cnt) := l_rec_task_type;
      ln_type_rec_cnt := ln_type_rec_cnt + 1;
      --debug_pkg.debug_proc('ln_type_rec_cnt = '||ln_type_rec_cnt);
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END TYPE_LOV;

/*******************************************************************************************
 Procedure Name: INSTALL_CALL_CHECK
 Description: Check if call is of type 'Install'
 Input Parameters: p_in_serial
                   p_in_model

 Output Parameters: x_call_type
                    x_call_type_id
 -----------------------------------------------------------------------------------------
 Author        Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/

FUNCTION INSTALL_CALL_CHECK (p_in_tsk_num   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_inst_flg   svc_task.svc_task_num%TYPE;
  
BEGIN

 /*SELECT 'Y'
 INTO lv_inst_flg
   FROM fsr, fsr_tp
  WHERE fsr.FSR_TP_CD = fsr_tp.FSR_TP_CD 
  AND fsr.fsr_num = p_in_sr_num
 AND fsr_tp.FSR_TP_NM='Install Call'
 AND fsr_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
 AND fsr_tp.EZCANCELFLAG =g_cancel_flg ; */
 
  select 'Y' 
  INTO lv_inst_flg
  from SVC_TASK
    where ds_svc_call_tp_cd in ('A', 'XA', 'B')
    AND svc_task_num = p_in_tsk_num
    AND glbl_cmpy_cd = g_glbl_cmpy_cd
    AND EZCANCELFLAG =g_cancel_flg ;
    
  RETURN lv_inst_flg;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN 'N';
END INSTALL_CALL_CHECK;

/*******************************************************************************************
 Procedure Name: INSTALL_DTLS
 Description: Get Install Call details
 Input Parameters: p_in_serial
                   p_in_model

 Output Parameters: x_call_type
                    x_call_type_id
 -----------------------------------------------------------------------------------------
 Author        Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  01-Nov-2015              Inital Version
*******************************************************************************************/
--TBD
PROCEDURE INSTALL_DTLS (p_in_serial         IN      VARCHAR2
                        ,p_in_task_num      IN      VARCHAR2
                        ,o_install_tbl      OUT     CANON_E307_INSTALL_TBL)
IS
 --TBD
   CURSOR cur_inst_dtls
   IS
   SELECT cpo_config.ds_cpo_config_pk,
         cpo_dtl.cpo_ord_num,
         cpo_dtl.cpo_dtl_line_num,
         cpo_dtl.cpo_dtl_line_sub_num,
          cpo_config.MDL_ID
          --cpo_dtl.ser_num
          FROM svc_mach_mstr ib,ds_cpo_config cpo_config, ds_cpo_dtl cpo_dtl
          WHERE ib.cpo_ord_num=cpo_config.cpo_ord_num
          AND cpo_config.ds_cpo_config_pk = cpo_dtl.ds_cpo_config_pk
          AND ib.ser_num=p_in_serial
          AND cpo_config.ezcancelflag=g_cancel_flg
      AND cpo_config.glbl_cmpy_cd=g_glbl_cmpy_cd
      AND cpo_dtl.ezcancelflag=g_cancel_flg
      AND cpo_dtl.glbl_cmpy_cd=g_glbl_cmpy_cd;
    /*SELECT     cpo_dtl.cpo_ord_num,
               cpo_dtl.cpo_dtl_line_num,
               cpo_dtl.cpo_dtl_line_sub_num
      FROM svc_mach_mstr ib, ds_cpo_dtl cpo_dtl
     WHERE ib.svc_config_mstr_pk = cpo_dtl.svc_config_mstr_pk
     AND ib.ser_num=p_in_serial
     AND cpo_dtl.ezcancelflag=g_cancel_flg
    AND cpo_dtl.glbl_cmpy_cd=g_glbl_cmpy_cd;*/
 
lv_mdse_cd      varchar2(100);
lv_model        varchar2(100);
lv_pln_num      varchar2(100);
lv_ser_num      varchar2(100);
lv_model_id     varchar2(100);
lv_chklst_pk     VARCHAR2(100);
ln_call_rec_cnt          NUMBER := 1;
l_rec_install_info          CANON_E307_INSTALL_REC; 
BEGIN

 o_install_tbl := CANON_E307_INSTALL_TBL ();
     FOR fr_cur_inst_dtls IN cur_inst_dtls
       LOOP
          BEGIN
          /*SELECT shpg_pln.mdse_cd
          INTO lv_mdse_cd--Item
          FROM cpo_dtl cd,shpg_pln
          WHERE 1=1
          AND cd.cpo_ord_num =fr_cur_inst_dtls.cpo_ord_num
          AND cd.cpo_dtl_line_num =fr_cur_inst_dtls.cpo_dtl_line_num
          AND cd.cpo_dtl_line_sub_num=fr_cur_inst_dtls.cpo_dtl_line_sub_num
          AND cd.cpo_ord_num=shpg_pln.trx_hdr_num
          AND cd.cpo_dtl_line_num=shpg_pln.trx_line_num
          AND cd.cpo_dtl_line_sub_num=shpg_pln.trx_line_sub_num
          AND cd.ezcancelflag=g_cancel_flg
          AND cd.glbl_cmpy_cd=g_glbl_cmpy_cd
          AND shpg_pln.ezcancelflag=g_cancel_flg
          AND shpg_pln.glbl_cmpy_cd=g_glbl_cmpy_cd;*/
          SELECT shpg_pln.mdse_cd,shpg_pln_num
          INTO lv_mdse_cd,lv_pln_num--Item
          FROM shpg_pln,shpg_sts,canon_e307_setup_tbl set_up
          WHERE 1=1
          AND shpg_pln.shpg_sts_cd =shpg_sts.shpg_sts_cd
          AND upper(shpg_sts.shpg_sts_nm) =upper(set_up.setup_value)
          AND set_up.setup_name='INST_CALL_SHIP_STS'
          AND shpg_pln.trx_hdr_num =fr_cur_inst_dtls.cpo_ord_num
          AND shpg_pln.trx_line_num =fr_cur_inst_dtls.cpo_dtl_line_num
              AND shpg_pln.trx_line_sub_num =fr_cur_inst_dtls.cpo_dtl_line_sub_num;
          EXCEPTION 
          WHEN OTHERS THEN
          lv_mdse_cd:=NULL;
          lv_pln_num:=NULL;
          END;
          BEGIN
          SELECT t_mdl_nm, t_mdl_id
          INTO lv_model, lv_model_id
          FROM mdl_nm
          WHERE t_mdl_id=fr_cur_inst_dtls.mdl_id
          AND ezcancelflag=g_cancel_flg
          AND t_glbl_cmpy_cd=g_glbl_cmpy_cd;
          EXCEPTION 
                    WHEN OTHERS THEN
                    lv_model:=NULL;
          END;
          BEGIN
                 SELECT ser.ser_num 
                      into lv_ser_num
                      FROM shpg_ord_dtl ord,ship_ser_num ser
                  WHERE ord.shpg_pln_num=lv_pln_num
                  AND ord.so_num=ser.so_num
                  AND ord.so_slp_num=ser.so_slp_num; 
          EXCEPTION 
          WHEN OTHERS THEN
          lv_ser_num:=NULL;             
          END;
          BEGIN
           SELECT
                FSR_ISTL_CHK_LIST_PK
                INTO lv_chklst_pk
            FROM
                FSR_ISTL_CHK_LIST 
            WHERE
                GLBL_CMPY_CD  = g_glbl_cmpy_cd
            AND EZCANCELFLAG  = g_cancel_flg
            AND SVC_TASK_NUM  = p_in_task_num;
          EXCEPTION
          WHEN OTHERS THEN
            lv_chklst_pk:='';
          END;
          
          l_rec_install_info :=
             CANON_E307_INSTALL_REC (fr_cur_inst_dtls.ds_cpo_config_pk, 
                          lv_mdse_cd,
                          lv_model,--TBD
                          lv_model_id,
                          lv_ser_num,
                          lv_chklst_pk);
          o_install_tbl.EXTEND ();
          o_install_tbl (ln_call_rec_cnt) := l_rec_install_info;
          ln_call_rec_cnt := ln_call_rec_cnt + 1;
       END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END INSTALL_DTLS;
/*******************************************************************************************
 Procedure Name: GET_TASK_STATUS
 Description: Getpossible task statuses based on existing status
 Input Parameters: p_in_status
            
 Output Parameters: x_bill_to_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
Hema Doniparthi      1.0                  13-Jan-2016              Inital Version
*******************************************************************************************/ 
PROCEDURE GET_TASK_STATUS (
   p_in_status     IN       VARCHAR2,  
   p_in_user_id    IN       VARCHAR2,   
   x_type_tbl      OUT      CANON_E307_TYPE_LOV_TBL)
IS
   l_rec_task_type   CANON_E307_TYPE_LOV_REC;
   ln_type_rec_cnt      NUMBER := 1;

   CURSOR cur_task_sts
   IS
     select sts.SVC_TASK_STS_CD, SVC_TASK_STS_NM from 
        svc_task_sts sts,SVC_TASK_STS_TRNST stst
        WHERE 1=1
    -- AND  sts.FSR_UPD_ENBL_FLG = 'Y'
       AND sts.svc_task_sts_cd = stst.svc_task_to_sts_cd
     --  AND stst.svc_task_from_sts_cd = p_in_status
       AND sts.glbl_cmpy_cd =g_glbl_cmpy_cd
       AND sts.ezcancelflag=g_cancel_flg;
       
   /* 
    SELECT distinct sts.svc_task_sts_cd, sts.svc_task_sts_nm
  FROM DS_ORG_RESRC_RELN dor,
       SVC_STS_TRNST_RULE_ASG stra,
       SVC_TASK_STS_TRNST stst,
       svc_task_sts sts
 WHERE   dor.ORG_FUNC_ROLE_TP_CD = stra.ORG_FUNC_ROLE_TP_CD
       AND stra.svc_sts_trnst_rule_cd = stst.SVC_STS_TRNST_RULE_CD
       AND sts.svc_task_sts_cd = stst.svc_task_to_sts_cd
       AND stst.svc_task_from_sts_cd = '20'
       AND dor.psn_cd = 'D09651'
       AND NVL(to_date(dor.eff_thru_dt), SYSDATE) >= SYSDATE
       AND dor.EZCANCELFLAG = g_cancel_flg
       AND stra.EZCANCELFLAG = g_cancel_flg
       AND stst.EZCANCELFLAG = g_cancel_flg
       AND sts.EZCANCELFLAG = g_cancel_flg
       AND dor.GLBL_CMPY_CD = g_glbl_cmpy_cd
       AND stra.GLBL_CMPY_CD = g_glbl_cmpy_cd
       AND stst.GLBL_CMPY_CD = g_glbl_cmpy_cd
       AND sts.GLBL_CMPY_CD = g_glbl_cmpy_cd;
     */
       
BEGIN
   x_type_tbl := CANON_E307_TYPE_LOV_TBL ();

   --Start Fetching Task Type LOV
   FOR fr_cur_task_sts IN cur_task_sts
   LOOP
   -- debug_pkg.debug_proc('STS CD : '||+fr_cur_task_sts.svc_task_sts_cd||'Name: '||fr_cur_task_sts.svc_task_sts_nm);
      l_rec_task_type :=
         CANON_E307_TYPE_LOV_REC (fr_cur_task_sts.svc_task_sts_cd,
                                  fr_cur_task_sts.svc_task_sts_nm,
                                  '');
      x_type_tbl.EXTEND ();
      x_type_tbl (ln_type_rec_cnt) := l_rec_task_type;
      ln_type_rec_cnt := ln_type_rec_cnt + 1;
      --debug_pkg.debug_proc('ln_type_rec_cnt = '||ln_type_rec_cnt);
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
    --  debug_pkg.debug_proc('Inside  Exception');
END GET_TASK_STATUS;
/*******************************************************************************************
 Procedure Name: GET_CREDIT_CUST_INFO
 Description: Get Bill To Customer Details for Credit Card Authorization
 Input Parameters: P_SERIAL_NUM
            
 Output Parameters: x_ugw_tbl
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
HemaDoniparthi      1.0                  17-Feb-2016              Inital Version
*******************************************************************************************/ 
  PROCEDURE GET_CREDIT_CUST_INFO(
    P_SERIAL_NUM                  IN       VARCHAR2,  
    P_MERCHANT_ID                 OUT      VARCHAR2,
    P_CUST_EMAIL                  OUT      VARCHAR2,
    P_CUST_PHONE                  OUT      VARCHAR2,
    P_EXTN_NUM                    OUT      VARCHAR2,
    P_CONTACT_NAME                OUT      VARCHAR2,    
    p_credit_cust_cur             OUT      cur_typ
  )AS
  l_merchant_id         VARCHAR2(100);
  l_cust_email          VARCHAR2(100);
  l_cust_phone          VARCHAR2(50);
  l_extn_num            VARCHAR2(30);
  l_contact_name        VARCHAR2(100);
  
  BEGIN
    OPEN p_credit_cust_cur  FOR 
        SELECT distinct bill_to.bill_to_cust_cd CUST_CODE,
             bill_to.loc_nm CUST_NAME,
             bill_to.first_line_addr ADDRESS,
             bill_to.scd_line_addr ADDRESS2,
             bill_to.cty_addr CITY,
             bill_to.st_cd STATE,
             bill_to.post_cd POSTAL_CODE,
             bill_to.ctry_cd COUNTRY
         FROM svc_mach_mstr ib, bill_to_cust bill_to
       WHERE  ib.bill_to_loc_num = bill_to.BILL_TO_CUST_CD
             AND ib.ser_num = P_SERIAL_NUM
             AND NVL (bill_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD') 
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND bill_to.ezcancelflag = g_cancel_flg
            AND ROWNUM = 1;
         
    BEGIN 
    SELECT CTAC_PSN_TEL_NUM,
           CTAC_PSN_TEL_EXTN_NUM,
           CTAC_PSN_EML_ADDR,
           CTAC_PSN_LAST_NM || ', ' || CTAC_PSN_FIRST_NM
      INTO l_cust_phone,
           l_extn_num,
           l_cust_email,
           l_contact_name
      FROM SVC_MACH_CTAC_PSN
     WHERE SVC_MACH_MSTR_PK =
              (SELECT SVC_MACH_MSTR_PK
                 FROM svc_mach_mstr
                WHERE     ser_num = P_SERIAL_NUM
                      AND glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND ROWNUM = 1); 
    EXCEPTION WHEN OTHERS 
    THEN
        l_cust_phone:='';
        l_extn_num:='';
        l_cust_email:='';
        l_contact_name:='';
    END;
    BEGIN
        P_MERCHANT_ID:=g_ascc_merchant_id;
    END;
   
    P_CUST_EMAIL   := l_cust_email;
    P_CUST_PHONE   := l_cust_phone;
    P_CONTACT_NAME := l_contact_name;
    P_EXTN_NUM     := l_extn_num;
    
  EXCEPTION WHEN OTHERS
  THEN
   OPEN p_credit_cust_cur FOR
    SELECT 1, '1'
      FROM DUAL
     WHERE 1 = -1;
    -- debug_pkg.debug_proc('Inside Exception');
  END;
FUNCTION GET_BILLABLE_FLAG (p_in_fsr           IN VARCHAR2)
   RETURN NUMBER
IS
    l_billable_flag   NUMBER;
BEGIN
    BEGIN
     SELECT count(*)
     INTO l_billable_flag
      FROM svc_task st, SVC_BILL_TP sbt
     WHERE st.fsr_num = p_in_fsr 
     AND st.SVC_BILL_TP_CD = sbt.SVC_BILL_TP_CD
     AND sbt.BLLBL_FLG ='Y'
     AND st.glbl_cmpy_cd = g_glbl_cmpy_cd
     AND st.ezcancelflag = g_cancel_flg;
    EXCEPTION WHEN OTHERS
    THEN
    l_billable_flag:=0;
    END;
    RETURN l_billable_flag;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN 0;
END GET_BILLABLE_FLAG;  
END CANON_E307_CREATE_SR_PKG;
/
SHOW ERRORS;

CREATE OR REPLACE PACKAGE CANON_E307_DEBRIEF_PKG
AS
/*******************************************************************************************
   Package Name: CANON_E307_DEBRIEF_PKG_SPEC
   Description:  Package to be used by Canon Smart Dispatch Application
   Dependencies: Canon Smart Dispatch Application JSP pages
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   Hema Doniparthi	   2.0                  14-Mar-2016              Modified for other changes
*****************************************************************************************/
TYPE cur_typ IS REF CURSOR;
g_glbl_cmpy_cd VARCHAR2(10):=canon_e307_constants.g_global_company_code;
g_cancel_flg VARCHAR2(10):=canon_e307_constants.g_cancel_flg;
FUNCTION GET_MACH_SLN (p_in_serial IN VARCHAR2)
   RETURN VARCHAR2;
FUNCTION GET_PSN_NM (p_in_usr_cd IN VARCHAR2)
   RETURN VARCHAR2;    
PROCEDURE DEBRIEF_HDR_INFO(
   p_in_task_no IN VARCHAR2
  ,p_out_debrief_rec OUT CANON_E307_DEBRIEF_DATA_REC
);
PROCEDURE DEBRIEF_LABOR (
      p_in_task_no   IN     VARCHAR2,
      o_manual_labor_flag OUT VARCHAR2,
      o_labor_tbl       OUT CANON_E307_DEBRIEF_LABOR_TBL);
PROCEDURE DEBRIEF_PARTS (
      p_in_task_no   IN     VARCHAR2,
       o_part_tbl       OUT CANON_E307_DEBRIEF_PART_TBL);
       
PROCEDURE DEBRIEF_EXPENSE(
   p_in_task_no   IN  VARCHAR2
  ,o_expense_tbl OUT CANON_E307_DEBRIEF_EXPENSE_TBL
);
 PROCEDURE ADD_DEBRIEF_LINE (p_in_type        IN     VARCHAR2,
                           p_in_item_cd     IN     VARCHAR2,
                           p_start          IN     NUMBER,
                           p_end            IN     NUMBER,
                           --p_in_sort_by      IN     VARCHAR2,
                           --p_in_sort_order   IN     VARCHAR2,
                            x_count             OUT NUMBER,
                           o_item_lov_tbl      OUT CANON_E307_ITEM_LOV_TBL);
PROCEDURE DEBRIEF_LOV (p_in_attr   IN     VARCHAR2,
               p_in_val       IN     VARCHAR2,
                       o_lov_tbl   OUT CANON_E307_LOV_VAL_TBL);
PROCEDURE IWR_LOV (o_iwr_tbl      OUT CANON_E307_IWR_LOV_TBL);
PROCEDURE DEBRIEF_NOTES (p_in_task_num        IN     VARCHAR2,
             o_notes_tbl OUT CANON_E307_DEBRIEF_NOTE_TBL);
END CANON_E307_DEBRIEF_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_DEBRIEF_PKG
IS
/*******************************************************************************************
 Package Name: CANON_E307_DEBRIEF_PKG_BODY
 Description:  Package to be used by Canon Smart Dispatch Application
 Dependencies: Canon Smart Dispatch Application JSP pages
 Action History:
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
 *******************************************************************************************/
/*******************************************************************************************
 Function Name: GET_TASK_DET
 Description: Get Task details
 Input Parameters: p_in_serial
            p_in_col
            p_in_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_MACH_SLN (p_in_serial   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_sol    svc_config_mstr.svc_sln_nm%TYPE;
    
  
BEGIN

SELECT config.svc_sln_nm
INTO lv_sol
FROM svc_mach_mstr smm, svc_config_mstr config
WHERE     1 = 1
AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
/*and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd;

    
  RETURN lv_sol;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_MACH_SLN;

/*******************************************************************************************
 Function Name: GET_PSN_NM
 Description: Get the user name for user id
 Input Parameters: p_in_usr_cd

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_PSN_NM (p_in_usr_cd   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_usr_nm   VARCHAR2(500);
    
  
BEGIN

 SELECT psn_last_nm || ', ' || psn_first_nm
   INTO lv_usr_nm
                   from s21_psn psn
               where psn.psn_cd=p_in_usr_cd
                and glbl_cmpy_cd=g_glbl_cmpy_cd
                and EZCANCELFLAG=g_cancel_flg;

    
  RETURN lv_usr_nm;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_PSN_NM;

/*******************************************************************************************
 Procedure Name: CALL_DEBRIEF_INFO
 Description: Get debrief details of Task to be displayed in ASCC
 Input Parameters: p_in_task_no
            
 Output Parameters: p_out_debrief_rec  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE DEBRIEF_HDR_INFO (
   p_in_task_no        IN     VARCHAR2,
   p_out_debrief_rec      OUT CANON_E307_DEBRIEF_DATA_REC)
IS
   l_svc_task_sts_nm   svc_task_sts.svc_task_sts_nm%TYPE;
   lv_solution         svc_config_mstr.svc_sln_nm%TYPE;
   lv_cust_cse_num     fsr.cust_cse_num%TYPE;
   lv_itt_num          fsr.itt_num%TYPE;
   lv_pblm_tp_cd       fsr.svc_pblm_tp_cd%TYPE;
   lv_cor_cd           VARCHAR2 (100);
   lv_rsn_cd           VARCHAR2 (100);
   lv_loc_cd           VARCHAR2 (100);
   lv_iwr_sts_cd       iwr_sts.iwr_sts_cd%TYPE;
   lv_iwr_sts          VARCHAR2 (100);
   lv_dbf_num          VARCHAR2 (100);
   lv_crat_dt          VARCHAR2 (100);
   lv_crat_by          VARCHAR2 (100);
   lv_upd_dt           VARCHAR2 (100);
   lv_upd_by           VARCHAR2 (100);
   l_deb_upd_flg       VARCHAR2 (1);
   l_iwr_flag          VARCHAR2(1);

   CURSOR cur_call_info
   IS
      SELECT task.svc_task_num,
             task.fsr_num,
             task.ser_num,
             task.cust_mach_ctrl_num,
             task.ds_svc_call_tp_cd,
             task.svc_task_sts_cd,
             task.ezintime creat_dt,
             task.erl_start_ts early_start,
             task.late_start_ts late_start,
             task.mach_down_flg,
             task.svc_mach_mstr_pk,
             task.svc_task_dbrf_sq
        FROM svc_task task
       WHERE     task.svc_task_num = p_in_task_no
             AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd;

BEGIN
   FOR fr_cur_call_info IN cur_call_info
   LOOP
      BEGIN
         SELECT sts.svc_task_sts_nm
           INTO l_svc_task_sts_nm
           FROM svc_task_sts sts
          WHERE sts.SVC_TASK_STS_CD = fr_cur_call_info.svc_task_sts_cd
                AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_svc_task_sts_nm := NULL;
      END;

      lv_solution :=
         GET_MACH_SLN (fr_cur_call_info.ser_num);

      BEGIN
         SELECT cust_cse_num, itt_num
           INTO lv_cust_cse_num, lv_itt_num
           FROM FSR
          WHERE fsr_num = fr_cur_call_info.fsr_num;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_cust_cse_num := NULL;
            lv_itt_num := NULL;
      END;

      BEGIN
         SELECT svc_pblm_tp_cd,
                svc_pblm_crct_tp_cd,
                svc_pblm_rsn_tp_cd,
                svc_pblm_loc_tp_cd
           INTO lv_pblm_tp_cd,
                lv_cor_cd,
                lv_rsn_cd,
                lv_loc_cd
           FROM FSR_VISIT_TASK
          WHERE     1 = 1
                AND svc_task_num = fr_cur_call_info.svc_task_num
                AND fsr_visit_num=( SELECT MAX (fsr_visit_num)
                         FROM fsr_visit_task visit
                       WHERE visit.svc_task_num = fr_cur_call_info.svc_task_num 
                       AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd)
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_pblm_tp_cd := NULL;
            lv_cor_cd := NULL;
            lv_rsn_cd := NULL;
            lv_loc_cd := NULL;
      END;

      BEGIN
         SELECT iwr_sts.iwr_sts_cd,
                iwr_sts.iwr_sts_cd || '-' || iwr_sts.iwr_sts_nm
           INTO lv_iwr_sts_cd, lv_iwr_sts
           FROM fsr_visit visit, iwr_sts
          WHERE     1 = 1
                AND visit.iwr_sts_cd = iwr_sts.iwr_sts_cd
                AND visit.svc_task_num = fr_cur_call_info.svc_task_num;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_iwr_sts_cd := NULL;
            lv_iwr_sts := NULL;
      END;
      
      BEGIN
            SELECT val.val5
            INTO l_deb_upd_flg
              FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
             WHERE     cd.cd_id = val.cd_id
                   AND cd_name = 'CANON_E307_TASK_STAT_VALUES'
                   AND VAL.VAL1 = fr_cur_call_info.svc_task_sts_cd
                   and rownum<2;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_deb_upd_flg:='Y';
      END;      

     BEGIN
     SELECT 'Y'
     INTO l_iwr_flag
         FROM svc_mach_mstr smm, IWR_RGTN_STS irs
         WHERE     smm.IWR_RGTN_STS_CD = irs.IWR_RGTN_STS_CD
         AND iwr_rgtn_sts_nm = 'Registered';
     EXCEPTION
      WHEN OTHERS THEN
         l_iwr_flag:='N';
     END;
     /* BEGIN
         --TBD
         SELECT Debrief_Number,
                Creation_Date,
                Created_by,
                Last_Update_date,
                Last_updated_by
           INTO lv_dbf_num,
                lv_crat_dt,
                lv_crat_by,
                lv_upd_dt,
                lv_upd_by
           FROM csf_debrief_headers
          WHERE 1 = 1 AND ROWNUM = 1;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_iwr_sts_cd := NULL;
            lv_iwr_sts := NULL;
      END;*/

      p_out_debrief_rec :=
         CANON_E307_DEBRIEF_DATA_REC (fr_cur_call_info.svc_task_num,
                                      fr_cur_call_info.fsr_num,
                                      l_svc_task_sts_nm,
                                      fr_cur_call_info.ser_num,
                                      fr_cur_call_info.cust_mach_ctrl_num,
                                      lv_solution,
                                      lv_cust_cse_num,
                                      lv_itt_num,
                                      lv_pblm_tp_cd,
                                      lv_cor_cd,
                                      lv_rsn_cd,
                                      lv_loc_cd,
                                      lv_iwr_sts,
                                      fr_cur_call_info.mach_down_flg,
                                      fr_cur_call_info.svc_task_dbrf_sq,
                                      l_svc_task_sts_nm,-- Debrief_Status
                                      lv_crat_dt,
                                      lv_crat_by,
                                      lv_upd_dt,
                                      lv_upd_by,
                                      fr_cur_call_info.svc_mach_mstr_pk,
                                      lv_iwr_sts_cd,
                                      fr_cur_call_info.svc_task_sts_cd,l_deb_upd_flg,l_iwr_flag,'','','','',''--Additional Attriutes for future
                                      );
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END DEBRIEF_HDR_INFO;
 
/*******************************************************************************************
    Procedure Name: DEBRIEF_LABOR
    Description: Get debrief labor details of Task to be displayed in ASCC
    Input Parameters: p_in_task_no

    Output Parameters: o_manual_labor_flag
                   o_labor_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
 *******************************************************************************************/
PROCEDURE DEBRIEF_LABOR (
   p_in_task_no          IN     VARCHAR2,
   o_manual_labor_flag      OUT VARCHAR2,
   o_labor_tbl              OUT CANON_E307_DEBRIEF_LABOR_TBL)
IS
   l_rec_lab            CANON_E307_DEBRIEF_LABOR_REC;

   --TBD
   CURSOR cur_labor
   IS
      SELECT fsr_visit_tm_event_pk,
             fsr_num,
             fsr_visit_num,
             svc_tm_event_cd,
             svc_task_num,
             svc_lbor_chrg_flg,
             mdse_cd,                                             --labor_item
             canon_e307_utils.format_date (svc_tm_event_from_dt, 'FORMAT1')
                start_date,
             canon_e307_utils.format_time (svc_tm_event_from_tm) start_time,
             canon_e307_utils.format_date (svc_tm_event_to_dt, 'FORMAT1')
                end_date,
             canon_e307_utils.format_time (svc_tm_event_to_tm) end_time,
             canon_e307_utils.date_diff (svc_tm_event_from_dt,
                                         svc_tm_event_from_tm,
                                         svc_tm_event_to_dt,
                                         svc_tm_event_to_tm)
             duration,
             svc_mod_pln_id,                                        -- mod_num
             svc_lbor_cmnt_txt,
             ser_num_txt,
             mod_item_txt
            -- ,mach_down_flg
        FROM FSR_VISIT_TM_EVENT fvte
       WHERE     svc_task_num = p_in_task_no
             AND GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND EZCANCELFLAG = g_cancel_flg;

   ln_rec_cnt1          NUMBER := 1;
   lv_debrief_line_id   VARCHAR2 (100);
   lv_Item_num          VARCHAR2 (100);
   lv_item_desc         VARCHAR2 (100);
   lv_start             VARCHAR2 (100);
   lv_end               VARCHAR2 (100);
   lv_duration          VARCHAR2 (100);
   lv_mod_num           VARCHAR2 (100);
   lv_ser_num           VARCHAR2 (100);
   lv_mod_itm           VARCHAR2 (100);
   lv_comments          VARCHAR2 (1000);
   lv_count1            NUMBER := 0;
   lv_count2            NUMBER := 0;
   lv_count3            NUMBER := 0;
   lv_bill_tp_cd    VARCHAR2 (100);
BEGIN
   o_labor_tbl := CANON_E307_DEBRIEF_LABOR_TBL ();
   o_manual_labor_flag := 'N';

   BEGIN
      SELECT COUNT (1)
        INTO lv_count1
        FROM svc_task task, svc_lbor_tm_pmit sltp, SVC_PMIT_LVL_TP TYPE
       WHERE     1 = 1
             AND svc_task_num = p_in_task_no
             AND task.ser_num = sltp.svc_lbor_tm_pmit_val_txt
             AND sltp.svc_pmit_lvl_tp_cd = TYPE.svc_pmit_lvl_tp_cd
             AND UPPER (TYPE.svc_pmit_lvl_tp_nm) = 'SERIAL'
             AND NVL (sltp.eff_to_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (sltp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND task.EZCANCELFLAG = g_cancel_flg
             AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND sltp.EZCANCELFLAG = g_cancel_flg
             AND sltp.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND TYPE.EZCANCELFLAG = g_cancel_flg
             AND TYPE.GLBL_CMPY_CD = g_glbl_cmpy_cd;

      SELECT COUNT (1)
        INTO lv_count2
        FROM svc_task task,
             svc_lbor_tm_pmit sltp,
             svc_pmit_lvl_tp TYPE,
             fsr
       WHERE     1 = 1
             AND task.fsr_num = fsr.fsr_num
             AND svc_task_num = p_in_task_no
             AND fsr.ship_to_cust_cd = sltp.svc_lbor_tm_pmit_val_txt
             AND sltp.svc_pmit_lvl_tp_cd = TYPE.svc_pmit_lvl_tp_cd
             AND UPPER (TYPE.svc_pmit_lvl_tp_nm) = 'SITE'
             AND NVL (sltp.eff_to_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (sltp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND task.EZCANCELFLAG = g_cancel_flg
             AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND sltp.EZCANCELFLAG = g_cancel_flg
             AND sltp.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND TYPE.EZCANCELFLAG = g_cancel_flg
             AND TYPE.GLBL_CMPY_CD = g_glbl_cmpy_cd;

      SELECT COUNT (1)
        INTO lv_count3
        FROM svc_task task,
             svc_lbor_tm_pmit sltp,
             svc_pmit_lvl_tp TYPE,
             fsr
       WHERE     1 = 1
             AND task.fsr_num = fsr.fsr_num
             AND svc_task_num = p_in_task_no
             AND fsr.sell_to_cust_cd = sltp.svc_lbor_tm_pmit_val_txt
             AND sltp.svc_pmit_lvl_tp_cd = TYPE.svc_pmit_lvl_tp_cd
             AND UPPER (TYPE.svc_pmit_lvl_tp_nm) = 'PARTY'
             AND NVL (sltp.eff_to_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (sltp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND task.EZCANCELFLAG = g_cancel_flg
             AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND sltp.EZCANCELFLAG = g_cancel_flg
             AND sltp.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND TYPE.EZCANCELFLAG = g_cancel_flg
             AND TYPE.GLBL_CMPY_CD = g_glbl_cmpy_cd;



      IF lv_count1 > 0 OR lv_count2 > 0 OR lv_count3 > 0
      THEN
         o_manual_labor_flag := 'Y';
      ELSE
         o_manual_labor_flag := 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         o_manual_labor_flag := 'N';
   END;

   FOR fr_cur_labor IN cur_labor
   LOOP
      BEGIN
         SELECT MDSE_NM
           INTO lv_item_desc
           FROM MDSE
          WHERE     mdse.mdse_cd = fr_cur_labor.mdse_cd
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_item_desc := NULL;
      END;
      
      BEGIN
      select mdse_item_bill_tp_cd 
      INTO lv_bill_tp_cd
      from DS_MDSE_INFO
      WHERE mdse_cd = fr_cur_labor.mdse_cd
      AND GLBL_CMPY_CD = g_glbl_cmpy_cd
      AND EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
               WHEN OTHERS
               THEN
            lv_bill_tp_cd := NULL;
      END;

     /* BEGIN
      --TBD Mod 
         SELECT ser_num
           INTO lv_ser_num
           FROM svc_task
          WHERE     svc_task_num = fr_cur_labor.svc_task_num
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_ser_num := NULL;
      END;

      BEGIN
      --TBD Mod
         SELECT fsr_usg.mdse_cd
           INTO lv_mod_itm
           FROM fsr_usg
          WHERE     fsr_usg.svc_mod_pln_id = fr_cur_labor.SVC_MOD_PLN_ID
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_mod_itm := NULL;
      END;*/

      l_rec_lab :=
         CANON_E307_DEBRIEF_LABOR_REC (fr_cur_labor.fsr_visit_tm_event_pk,
                                       fr_cur_labor.fsr_num,
                                       fr_cur_labor.fsr_visit_num,
                                       fr_cur_labor.svc_tm_event_cd,
                                       fr_cur_labor.svc_task_num,
                                       fr_cur_labor.svc_lbor_chrg_flg,
                                       fr_cur_labor.mdse_cd,
                                       lv_item_desc,
                                       fr_cur_labor.start_date,
                                       fr_cur_labor.start_time,
                                       fr_cur_labor.end_date,
                                       fr_cur_labor.end_time,
                                       fr_cur_labor.duration,
                                       fr_cur_labor.svc_mod_pln_id,
                                       fr_cur_labor.ser_num_txt,
                                       fr_cur_labor.mod_item_txt,
                                       fr_cur_labor.svc_lbor_cmnt_txt,
                                       --,fr_cur_labor.mach_down_flg
                                       lv_bill_tp_cd,
                                       '','','','','','','','',''--Additional Attriutes for future
                                       );
      o_labor_tbl.EXTEND ();
      o_labor_tbl (ln_rec_cnt1) := l_rec_lab;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END DEBRIEF_LABOR; 
/*******************************************************************************************
    Procedure Name: DEBRIEF_PARTS
    Description: Get debrief labor details of Task to be displayed in ASCC
    Input Parameters: p_in_task_no

    Output Parameters: o_part_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/
PROCEDURE DEBRIEF_PARTS (p_in_task_no   IN     VARCHAR2,
                         o_part_tbl        OUT CANON_E307_DEBRIEF_PART_TBL)
IS
   l_rec_part    CANON_E307_DEBRIEF_PART_REC;

   CURSOR cur_part
   IS
      SELECT fsr_num,
             fsr_visit_num,
             svc_task_num,
             FSR_USG_PK,
             svc_prt_chrg_flg,
             prt_used_by_tech_cd,
             mdse_cd part_item,
             mdse_nm,
             --TBD format the service date
             canon_e307_utils.format_date (svc_exec_dt, 'FORMAT1') svc_exec_dt,
             --svc_exec_dt,
             svc_prt_qty,
             uom_cd,
             Svc_mod_pln_id,
             Svc_prt_cmnt_txt,
             ser_num_txt,
             mod_item_txt
        FROM FSR_USG usg
       WHERE     svc_task_num = p_in_task_no
             AND GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND EZCANCELFLAG = g_cancel_flg;

   ln_rec_cnt1   NUMBER := 1;
   lv_ser_num    VARCHAR2 (100);
   lv_mod_itm    VARCHAR2 (100);
   lv_bill_tp_cd    VARCHAR2 (100);
BEGIN
   o_part_tbl := CANON_E307_DEBRIEF_PART_TBL ();

   FOR fr_cur_part IN cur_part
   LOOP
      BEGIN
         SELECT ser_num, mdse_cd
           INTO lv_ser_num, lv_mod_itm
           FROM svc_task
          WHERE     svc_task_num = fr_cur_part.svc_task_num
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_ser_num := NULL;
      END;
            BEGIN
            select mdse_item_bill_tp_cd 
            INTO lv_bill_tp_cd
            from DS_MDSE_INFO
            WHERE mdse_cd = fr_cur_part.part_item
            AND GLBL_CMPY_CD = g_glbl_cmpy_cd
            AND EZCANCELFLAG = g_cancel_flg;
            EXCEPTION
                     WHEN OTHERS
                     THEN
                  lv_bill_tp_cd := NULL;
            END;


      l_rec_part :=
         CANON_E307_DEBRIEF_PART_REC (fr_cur_part.fsr_num,
                                      fr_cur_part.fsr_visit_num,
                                      fr_cur_part.svc_task_num,
                                      fr_cur_part.fsr_usg_pk,
                                      fr_cur_part.svc_prt_chrg_flg,
                                      fr_cur_part.prt_used_by_tech_cd,
                                      fr_cur_part.part_item,
                                      fr_cur_part.mdse_nm,
                                      fr_cur_part.svc_exec_dt,
                                      fr_cur_part.svc_prt_qty,
                                      fr_cur_part.uom_cd,
                                      fr_cur_part.Svc_mod_pln_id,
                                      fr_cur_part.ser_num_txt,
                               fr_cur_part.mod_item_txt,
                                      fr_cur_part.Svc_prt_cmnt_txt,
                                      lv_bill_tp_cd,
                                      '','','','','','','','',''--Additional Attriutes for future
                                      );
      o_part_tbl.EXTEND ();
      o_part_tbl (ln_rec_cnt1) := l_rec_part;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END DEBRIEF_PARTS;
   
/*******************************************************************************************
 Procedure Name: DEBRIEF_EXPENSE
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: p_in_task_no
            
 Output Parameters: o_expense_tbl  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE DEBRIEF_EXPENSE (
   p_in_task_no    IN     VARCHAR2,
   o_expense_tbl      OUT CANON_E307_DEBRIEF_EXPENSE_TBL)
IS
   l_rec_expense   CANON_E307_DEBRIEF_EXPENSE_REC;
   lv_mdse_nm      mdse.mdse_nm%TYPE;
   lv_bill_tp_cd    VARCHAR2 (100);
   CURSOR cur_expense
   IS
      SELECT fsr_exp_pk,
             fsr_num,
             fsr_visit_num,
             svc_task_num,
             mdse_cd expense_item,
             --TBD Format the Service Date
             canon_e307_utils.format_date (svc_exec_dt, 'FORMAT1') Service_Date,
             --svc_exec_dt Service_Date,
             svc_exp_qty,
             uom_cd,
             svc_exp_chrg_flg,
             svc_exp_cmnt_txt
        FROM fsr_exp EXP
       WHERE     svc_task_num = p_in_task_no
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg;

   ln_rec_cnt3     NUMBER := 1;
BEGIN
   o_expense_tbl := CANON_E307_DEBRIEF_EXPENSE_TBL ();

   FOR fr_cur_expense IN cur_expense
   LOOP
      BEGIN
         SELECT mdse.mdse_nm
           INTO lv_mdse_nm
           FROM mdse
          WHERE     mdse.mdse_cd = fr_cur_expense.expense_item
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_mdse_nm := NULL;
      END;
            BEGIN
            select mdse_item_bill_tp_cd 
            INTO lv_bill_tp_cd
            from DS_MDSE_INFO
            WHERE mdse_cd = fr_cur_expense.expense_item
            AND GLBL_CMPY_CD = g_glbl_cmpy_cd
            AND EZCANCELFLAG = g_cancel_flg;
            EXCEPTION
                     WHEN OTHERS
                     THEN
                  lv_bill_tp_cd := NULL;
            END;


      l_rec_expense :=
         CANON_E307_DEBRIEF_EXPENSE_REC (fr_cur_expense.fsr_exp_pk,
                                         fr_cur_expense.fsr_num,
                                         fr_cur_expense.fsr_visit_num,
                                         fr_cur_expense.svc_task_num,
                                         fr_cur_expense.expense_item,
                                         lv_mdse_nm,
                                         fr_cur_expense.Service_Date,
                                         fr_cur_expense.svc_exp_qty,
                                         fr_cur_expense.uom_cd,
                                         fr_cur_expense.svc_exp_chrg_flg,
                                         fr_cur_expense.svc_exp_cmnt_txt,
                                         lv_bill_tp_cd,
                                         '','','','','','','','',''--Additional Attriutes for future
                                         );
      o_expense_tbl.EXTEND ();
      o_expense_tbl (ln_rec_cnt3) := l_rec_expense;
      ln_rec_cnt3 := ln_rec_cnt3 + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END DEBRIEF_EXPENSE;
 
 /*******************************************************************************************
  Procedure Name: ADD_DEBRIEF_LINE
  Description: Get debrief labor details of Task to be displayed in ASCC
  Input Parameters: p_in_type
                     p_in_item_cd
                     p_start
                     p_end
                      
  Output Parameters: o_start_date
                       o_start_time
                       o_end_date
                       o_end_time
                       x_count
                       o_item_lov_tbl
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
 *******************************************************************************************/
 PROCEDURE ADD_DEBRIEF_LINE (p_in_type        IN     VARCHAR2,
                           p_in_item_cd     IN     VARCHAR2,
                           p_start          IN     NUMBER,
                           p_end            IN     NUMBER,
                           --p_in_sort_by      IN     VARCHAR2,
                           --p_in_sort_order   IN     VARCHAR2,
                            x_count             OUT NUMBER,
                           o_item_lov_tbl      OUT CANON_E307_ITEM_LOV_TBL)
 IS
    l_rec_item_lov     CANON_E307_ITEM_LOV_REC;
    v_sql              VARCHAR2 (32000);
    l_default_from     VARCHAR2 (32000);
    l_sql_count_qry    VARCHAR2 (32000);
    v_item_cursor      cur_typ;
    ln_rec_cnt1        NUMBER := 1;
    l_order_by         VARCHAR2 (100);
    l_asc_desc_order   VARCHAR2 (100);
    lv_mdse_cd         VARCHAR2 (100);
    lv_mdse_name       VARCHAR2 (2000);
    lv_uom             VARCHAR2 (100);
    lv_date            VARCHAR2 (100);
    lv_time            VARCHAR2 (100);
 BEGIN
 --debug_pkg.debug_proc('Inside proc ADD_DEBRIEF_LINE');
    o_item_lov_tbl := CANON_E307_ITEM_LOV_TBL ();
 
    -- l_order_by := p_in_sort_by;
    -- l_asc_desc_order := p_in_sort_order;
    BEGIN
       SELECT TO_CHAR (SYSDATE, 'DD-MON-YYYY'), TO_CHAR (SYSDATE, 'HH24:MI')
         INTO lv_date, lv_time
         FROM DUAL;
    EXCEPTION
       WHEN OTHERS
       THEN
          lv_date := NULL;
          lv_time := NULL;
    END;
 
   /* o_start_date := lv_date;
    o_start_time := lv_time;
    o_end_date := lv_date;
    o_end_time := lv_time;*/
 
 
    --TBR
    IF UPPER (p_in_type) = 'LABOR'
    THEN
   -- debug_pkg.debug_proc('p_in_type= '||p_in_type);
       l_default_from :=
            ' FROM (SELECT rownum row_num, mdse.*, '''' pkg_uom_cd '
          || ' FROM ds_mdse_info dmi,mdse,mdse_item_bill_tp type '
          || ' where 1=1 '
          || ' AND dmi.mdse_cd = mdse.mdse_cd '
          || ' AND upper(dmi.mdse_cd) like upper( '
          || '''%'
          || p_in_item_cd
          || '%'') '
          || ' AND type.mdse_item_bill_tp_nm =''Labor'' '
          || ' AND dmi.mdse_item_bill_tp_cd=type.mdse_item_bill_tp_cd '
          || ' AND dmi.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND dmi.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''''
          || ' AND type.ezcancelflag= '''
      || g_cancel_flg
      || ''' AND type.glbl_cmpy_cd ='''
      || g_glbl_cmpy_cd
          || ''''
          || ' AND mdse.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND mdse.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''' ) ';
 
 
      
    ELSIF UPPER (p_in_type) = 'PARTS'
    THEN
    --debug_pkg.debug_proc('p_in_type= '||p_in_type);
       l_default_from :=
             ' FROM (SELECT rownum row_num, a.* '
          || ' FROM (select distinct mdse.*, uom.pkg_uom_cd  '
          || ' FROM ds_mdse_info dmi,mdse, ds_mdse_store_pkg uom,mdse_tp_val_set type '
          || ' where 1=1 '
          || ' AND dmi.mdse_cd = mdse.mdse_cd '
          || ' AND mdse.mdse_cd = uom.mdse_cd '
          || ' AND upper(dmi.mdse_cd) like upper( '
          || '''%'
          || p_in_item_cd
          || '%'') '
          || ' AND dmi.coa_mdse_tp_cd=type.coa_mdse_tp_cd '
          || '  AND mdse_tp_ctx_tp_cd=''PARTS_ITEM'' '
        || ' AND first_biz_ctx_attrb_txt=''PARTS'' '
          || ' AND dmi.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND dmi.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''''
          || ' AND type.ezcancelflag= '''
      || g_cancel_flg
      || ''' AND type.glbl_cmpy_cd ='''
      || g_glbl_cmpy_cd
          || ''''
          || ' AND uom.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND uom.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''''
          || ' AND mdse.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND mdse.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''' ) a) ';
 
 
      
    ELSIF UPPER (p_in_type) = 'EXPENSE'
    THEN
    --debug_pkg.debug_proc('p_in_type= '||p_in_type);
       l_default_from :=
             ' FROM (SELECT rownum row_num, a.* '
          || ' FROM (select distinct mdse.*, uom.pkg_uom_cd  '
          || ' FROM ds_mdse_info dmi,mdse, ds_mdse_store_pkg uom,mdse_item_bill_tp type '
          || ' where 1=1 '
          || ' AND dmi.mdse_cd = mdse.mdse_cd '
          || ' AND mdse.mdse_cd = uom.mdse_cd '
          || ' AND upper(dmi.mdse_cd) like upper( '
          || '''%'
          || p_in_item_cd
          || '%'') '
          || ' AND type.mdse_item_bill_tp_nm =''Expense'' '
          || ' AND dmi.mdse_item_bill_tp_cd=type.mdse_item_bill_tp_cd '
          || ' AND dmi.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND dmi.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''''
          || ' AND type.ezcancelflag= '''
      || g_cancel_flg
      || ''' AND type.glbl_cmpy_cd ='''
      || g_glbl_cmpy_cd
          || ''''          
          || ' AND uom.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND uom.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''''
          || ' AND mdse.ezcancelflag= '''
          || g_cancel_flg
          || ''' AND mdse.glbl_cmpy_cd ='''
          || g_glbl_cmpy_cd
          || ''' ) a) ';
 
     
    END IF;
    
 --debug_pkg.debug_proc('l_default_from = '||l_default_from);    
  v_sql :=
              ' SELECT mdse_cd, mdse_nm, pkg_uom_cd '
           || l_default_from
           || '  WHERE row_num BETWEEN '
           || p_start
           || ' AND '
          || p_end;
    /* IF l_order_by IS NOT NULL
     THEN
        v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
     ELSE
        v_sql := v_sql || ' ORDER BY first_line_addr';
     END IF;*/
    l_sql_count_qry := ' SELECT COUNT(*) ' || l_default_from;
-- debug_pkg.debug_proc('l_sql_count_qry= '||l_sql_count_qry);
    EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
 --debug_pkg.debug_proc('x_count= '||x_count);
    OPEN v_item_cursor FOR v_sql;
 
    LOOP
       FETCH v_item_cursor
          INTO lv_mdse_cd, lv_mdse_name, lv_uom;
 
       EXIT WHEN v_item_cursor%NOTFOUND;
       l_rec_item_lov :=
          CANON_E307_ITEM_LOV_REC (lv_mdse_cd, lv_mdse_name, lv_uom,lv_date,lv_time,lv_date,lv_time);
       o_item_lov_tbl.EXTEND ();
       o_item_lov_tbl (ln_rec_cnt1) := l_rec_item_lov;
       ln_rec_cnt1 := ln_rec_cnt1 + 1;
       --debug_pkg.debug_proc('lv_mdse_cd= '||lv_mdse_cd);
    END LOOP;
 EXCEPTION
    WHEN OTHERS
    THEN
       NULL;
END ADD_DEBRIEF_LINE;

/*******************************************************************************************
 Procedure Name: DEBRIEF_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: p_in_attr
            
 Output Parameters: o_lov_tbl  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE DEBRIEF_LOV (p_in_attr   IN     VARCHAR2,
               p_in_val       IN     VARCHAR2,
                       o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL)
IS
   l_rec_lov_cd   CANON_E307_LOV_VAL_REC;
   ln_rec_cnt     NUMBER := 1;


   CURSOR cur_pblm_cd
   IS
      SELECT DISTINCT svc_pblm_tp_cd
        FROM svc_pblm_tp spt
       WHERE     1 = 1
             AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND spt.ezcancelflag = g_cancel_flg;

   CURSOR cur_crct_cd
   IS
      SELECT DISTINCT svc_pblm_crct_tp_cd
        FROM svc_pblm_crct_tp crct_tp
       WHERE     crct_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND crct_tp.ezcancelflag = g_cancel_flg;

   CURSOR cur_rsn_cd
   IS
      SELECT DISTINCT svc_pblm_rsn_tp_cd
        FROM SVC_PBLM_RSN_TP rsn_tp
       WHERE     rsn_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND rsn_tp.ezcancelflag = g_cancel_flg;

   CURSOR cur_loc_cd
   IS
      SELECT DISTINCT svc_pblm_loc_tp_cd
        FROM svc_pblm_loc_tp loc_tp
       WHERE     loc_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND loc_tp.ezcancelflag = g_cancel_flg;
             
  CURSOR cur_mod_num
   IS
      SELECT DISTINCT svc_mod_pln_id
        FROM svc_mod
       WHERE  1=1
                AND svc_mod_pln_id like '%'||trim(p_in_val)||'%'
                AND svc_mod.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND svc_mod.ezcancelflag = g_cancel_flg;             

/* CURSOR cur_pblm_cd
 IS
    SELECT DISTINCT svc_pblm_tp_cd
      FROM mdl_nm mn,
           ds_mdl model,
           svc_pblm_mdl_grp_reln grp_rel,
           svc_pblm_tp spt
     WHERE     1 = 1
           AND mn.t_mdl_id = model.mdl_id
           AND model.mdl_grp_id = grp_rel.mdl_grp_id
           AND grp_rel.svc_pblm_catg_cd = spt.svc_pblm_catg_cd
           AND NVL (model.mdl_actv_flg, 'Y') = 'Y'
           AND mn.t_mdl_nm = TRIM (p_in_val)
           AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND spt.ezcancelflag = g_cancel_flg
         AND mn.t_glbl_cmpy_cd = g_glbl_cmpy_cd
         AND mn.ezcancelflag = g_cancel_flg
         AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND model.ezcancelflag = g_cancel_flg
         AND grp_rel.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND grp_rel.ezcancelflag = g_cancel_flg;

  CURSOR cur_crct_cd
 IS
    SELECT DISTINCT svc_pblm_crct_tp_cd
    FROM svc_pblm_crct_tp crct_tp,svc_pblm_tp spt
    WHERE crct_tp.svc_pblm_catg_cd=spt.svc_pblm_catg_cd
    AND spt.svc_pblm_tp_cd=p_in_val
    AND crct_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND crct_tp.ezcancelflag = g_cancel_flg
    AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND spt.ezcancelflag = g_cancel_flg;

   CURSOR cur_rsn_cd
 IS
    SELECT DISTINCT svc_pblm_rsn_tp_cd
    FROM SVC_PBLM_RSN_TP rsn_tp,svc_pblm_tp spt
    WHERE rsn_tp.svc_pblm_catg_cd=spt.svc_pblm_catg_cd
    AND spt.svc_pblm_tp_cd=p_in_val
    AND rsn_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND rsn_tp.ezcancelflag = g_cancel_flg
    AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND spt.ezcancelflag = g_cancel_flg;

  CURSOR cur_loc_cd
 IS
    SELECT DISTINCT svc_pblm_loc_tp_cd
    FROM svc_pblm_loc_tp loc_tp,svc_pblm_tp spt
    WHERE loc_tp.svc_pblm_catg_cd=spt.svc_pblm_catg_cd
    AND spt.svc_pblm_tp_cd=p_in_val
    AND loc_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND loc_tp.ezcancelflag = g_cancel_flg
    AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND spt.ezcancelflag = g_cancel_flg;  */

BEGIN
   o_lov_tbl := CANON_E307_LOV_VAL_TBL ();

   IF UPPER (p_in_attr) = 'PROBLEMCODE'
   THEN
      ln_rec_cnt := 1;

      FOR fr_cur_pblm_cd IN cur_pblm_cd
      LOOP
         l_rec_lov_cd :=
            CANON_E307_LOV_VAL_REC (fr_cur_pblm_cd.svc_pblm_tp_cd);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   ELSIF UPPER (p_in_attr) = 'CRCTCODE'
   THEN
      FOR fr_cur_crct_cd IN cur_crct_cd
      LOOP
         l_rec_lov_cd :=
            CANON_E307_LOV_VAL_REC (fr_cur_crct_cd.svc_pblm_crct_tp_cd);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   ELSIF UPPER (p_in_attr) = 'RSNCODE'
   THEN
      FOR fr_cur_rsn_cd IN cur_rsn_cd
      LOOP
         l_rec_lov_cd :=
            CANON_E307_LOV_VAL_REC (fr_cur_rsn_cd.svc_pblm_rsn_tp_cd);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   ELSIF UPPER (p_in_attr) = 'LOCCODE'
   THEN
      FOR fr_cur_loc_cd IN cur_loc_cd
      LOOP
         l_rec_lov_cd :=
            CANON_E307_LOV_VAL_REC (fr_cur_loc_cd.svc_pblm_loc_tp_cd);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   ELSIF UPPER (p_in_attr) = 'MODNUM'
   THEN
      FOR fr_cur_mod_num IN cur_mod_num
      LOOP
         l_rec_lov_cd :=
            CANON_E307_LOV_VAL_REC (fr_cur_mod_num.svc_mod_pln_id);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;  
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END DEBRIEF_LOV;

/*******************************************************************************************
 Procedure Name: IWR_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: None
            
 Output Parameters: o_iwr_tbl Address  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE IWR_LOV (o_iwr_tbl OUT CANON_E307_IWR_LOV_TBL)
IS
   l_rec_iwr_cd   CANON_E307_IWR_LOV_REC;
   ln_rec_cnt     NUMBER := 1;

   CURSOR cur_iwr_lov
   IS
      SELECT DISTINCT iwr_sts_cd, IWR_STS_CD || '-' || IWR_STS_NM IWR_Status
        FROM iwr_sts
       WHERE     1 = 1
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg;

BEGIN
   o_iwr_tbl := CANON_E307_IWR_LOV_TBL ();

   FOR fr_cur_iwr_lov IN cur_iwr_lov
   LOOP
      l_rec_iwr_cd :=
         CANON_E307_IWR_LOV_REC (fr_cur_iwr_lov.iwr_sts_cd,
                                 fr_cur_iwr_lov.iwr_status);
      o_iwr_tbl.EXTEND ();
      o_iwr_tbl (ln_rec_cnt) := l_rec_iwr_cd;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END IWR_LOV; 

/*******************************************************************************************
 Procedure Name: UOM_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: None
            
 Output Parameters: o_iwr_tbl Address  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE UOM_LOV (o_uom_tbl OUT CANON_E307_UOM_LOV_TBL)
IS
   l_rec_uom_cd   CANON_E307_UOM_LOV_REC;
   ln_rec_cnt     NUMBER := 1;

   CURSOR cur_uom_lov
   IS
   --TBD
      SELECT pkg_uom_cd,pkg_uom_nm,pkg_uom_desc_txt
        FROM pkg_uom
       WHERE 1=1
       --AND PKG_UOM_CLS_CD IN ()
       AND glbl_cmpy_cd = g_glbl_cmpy_cd 
       AND ezcancelflag = g_cancel_flg;
BEGIN
   o_uom_tbl := CANON_E307_UOM_LOV_TBL ();

   FOR fr_cur_uom_lov IN cur_uom_lov
   LOOP
      l_rec_uom_cd :=
         CANON_E307_UOM_LOV_REC (fr_cur_uom_lov.pkg_uom_cd,
                                 fr_cur_uom_lov.pkg_uom_nm,
                                 fr_cur_uom_lov.pkg_uom_desc_txt);
      o_uom_tbl.EXTEND ();
      o_uom_tbl (ln_rec_cnt) := l_rec_uom_cd;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END UOM_LOV;

/*******************************************************************************************
 Procedure Name: UOM_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: None
            
 Output Parameters: o_iwr_tbl Address  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE DEBRIEF_NOTES (p_in_task_num        IN     VARCHAR2,
             o_notes_tbl OUT CANON_E307_DEBRIEF_NOTE_TBL)
IS
   ln_note_rec_cnt     NUMBER := 1;
   l_rec_notes              CANON_E307_DEBRIEF_NOTE_REC;
   
   CURSOR cur_notes
          IS
          SELECT '' Note_Source_Id,
               '' Note_Id,
               note_type.svc_memo_tp_nm Note_Type,
               CANON_E307_UTILS.format_date2_func(note.ezintime, 'FORMAT2') Note_Date,
               note.SVC_CMNT_TXT Note_Text,
                GET_PSN_NM(note.ezinuserid) Created_By
           FROM  SVC_MEMO note, SVC_MEMO_TP note_type,svc_memo_catg category
          WHERE note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
          AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
          AND category.svc_memo_catg_nm='Dispatch Memo'
          AND  note.svc_task_num=p_in_task_num
          AND note.svc_mach_mstr_pk IS NULL
          AND note.ds_contr_pk IS NULL
       AND note.ds_contr_dtl_pk IS NULL;
BEGIN
   o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();
   FOR fr_cur_notes IN cur_notes 
            LOOP
               l_rec_notes :=
                  CANON_E307_DEBRIEF_NOTE_REC (fr_cur_notes.Note_Source_Id,
                                               fr_cur_notes.Note_Id,
                                               fr_cur_notes.Note_Type,
                                               fr_cur_notes.Note_Date,
                                               fr_cur_notes.Note_Text,
                                               fr_cur_notes.Created_By);
               o_notes_tbl.EXTEND ();
               o_notes_tbl (ln_note_rec_cnt) := l_rec_notes;
               ln_note_rec_cnt := ln_note_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END DEBRIEF_NOTES;

END CANON_E307_DEBRIEF_PKG;
/
SHOW ERRORS;

CREATE OR REPLACE PACKAGE CANON_E307_CHARGES_PKG
AS
/*******************************************************************************************
   Package Name: CANON_E307_CHARGES_PKG_SPEC
   Description:  Package to be used by Canon Charges Application
   Dependencies: Canon Charges Application JSP pages
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Hari Mukkoti        1.0                  30-Dec-2015              Inital Version
   Hema Doniparthi	   2.0                  14-Mar-2016              Modified for other changes
*****************************************************************************************/
g_glbl_cmpy_cd VARCHAR2(10):=canon_e307_constants.g_global_company_code;
g_cancel_flg VARCHAR2(10):=canon_e307_constants.g_cancel_flg;
  PROCEDURE ESTIMATE_HDR_INFO(
   p_in_fsr_no IN VARCHAR2
  ,o_est_hdr_rec OUT CANON_E307_EST_HDR_REC
  );
PROCEDURE ESTIMATE_TASK_DETAIL (
      p_in_fsr_no               IN     VARCHAR2,
      o_est_task_dtl_tbl        OUT CANON_E307_EST_TASK_TBL);
      
PROCEDURE ESTIMATE_PRICE_DETAIL (
      p_in_fsr_no               IN     VARCHAR2,
      o_price_tbl                OUT CANON_E307_EST_PRICE_TBL);
PROCEDURE CHARGE_HDR_INFO(
   p_in_fsr_no IN VARCHAR2,
   p_task_no IN VARCHAR2
  ,o_charge_hdr_rec OUT CANON_E307_CHG_HDR_REC
  );
PROCEDURE CHARGE_DETAILS_INFO(
   p_in_fsr_no IN VARCHAR2,
   p_task_no IN VARCHAR2
  ,o_charge_dtl_tbl OUT CANON_E307_CHG_DTL_TBL
  );
PROCEDURE TRANSACTION_DETAILS(p_in_fsr_no IN VARCHAR2,
                                                             p_task_no    IN VARCHAR2,
                                                             p_billing_type IN VARCHAR2,
                                                             o_trx_dtl_tbl OUT CANON_E307_TRX_DTL_REC);    
PROCEDURE CHANGE_REASON_LOV (o_rsn_tbl OUT CANON_E307_CHNG_REASON_TBL);                                                               
END CANON_E307_CHARGES_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_CHARGES_PKG
 AS
/*******************************************************************************************
   Package Name: CANON_E307_CHARGES_PKG_BODY
   Description:  Package to be used by Canon Smart Dispatch Application
   Dependencies: Canon Smart Dispatch Application JSP pages
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Hari Mukkoti        1.0                  30-Dec-2015              Inital Version
   Hema Doniparthi	   2.0                  14-Mar-2016              Modified for other changes
*****************************************************************************************/ 
   PROCEDURE ESTIMATE_HDR_INFO (
                     p_in_fsr_no     IN VARCHAR2
                    ,o_est_hdr_rec   OUT CANON_E307_EST_HDR_REC
  )
     IS
        CURSOR cr_fsr_dtl(crp_fsr_num   fsr.fsr_num%TYPE) IS 
        SELECT ship_to.loc_nm customer_name,
                      ship_to.first_line_addr||' '||ship_to.scd_line_addr customer_address,
                      ship_to.cty_addr customer_city,
                      ship_to.st_cd customer_state,
                      ship_to.post_cd customer_zip_code,
                      --ship_to.ctry_cd country,
                      cust_acct.ds_acct_num customer_acct_num,
                      Null customer_contact,
                      Null customer_email,
                      f_fsr.fsr_num sr_number,
                      Null tag_number,
                      mach_mstr.mdse_cd Model,
                      mach_mstr.ser_num serial,
                      Null solution_name
        FROM fsr f_fsr,
                   ship_to_cust ship_to,
                   ds_acct_cust cust_acct,
                   svc_mach_mstr mach_mstr 
        WHERE 1=1
        AND f_fsr.ship_to_cust_cd = ship_to.ship_to_cust_cd
        AND f_fsr.ezcancelflag = ship_to.ezcancelflag
        AND f_fsr.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
        AND f_fsr.ship_to_cust_acct_cd  = cust_acct.ds_acct_num
        AND f_fsr.ezcancelflag = cust_acct.ezcancelflag
        AND f_fsr.glbl_cmpy_cd = cust_acct.glbl_cmpy_cd
        AND f_fsr.svc_mach_mstr_pk = mach_mstr.svc_mach_mstr_pk
        AND f_fsr.ezcancelflag = mach_mstr.ezcancelflag
        AND f_fsr.glbl_cmpy_cd = mach_mstr.glbl_cmpy_cd
        AND f_fsr.ezcancelflag = '0'
        AND f_fsr.glbl_cmpy_cd = 'ADB'
        AND f_fsr.fsr_num = NVL(crp_fsr_num,f_fsr.fsr_num);
 
            l_customer_name           VARCHAR2(100);
            l_CUSTOMER_ADDRESS        VARCHAR2(150);
            l_CUSTOMER_CITY           VARCHAR2(50);
            l_CUSTOMER_STATE          VARCHAR2(50);
            l_CUSTOMER_ZIP_CODE       VARCHAR2(50);
            l_CUSTOMER_ACCT_NUM       VARCHAR2(50);
            l_CUSTOMER_CONTACT        VARCHAR2(50);
            l_CUSTOMER_EMAIL          VARCHAR2(50);
            l_SR_NUMBER               VARCHAR2(50);
            l_TAG_NUMBER              VARCHAR2(50);
            l_MODEL                   VARCHAR2(100);
            l_SERIAL                  VARCHAR2(30);
            l_SOLUTION_NAME           VARCHAR2(30);
      BEGIN
            FOR fr_cr_fsr_dtl IN cr_fsr_dtl(p_in_fsr_no) LOOP
                    l_customer_name        := fr_cr_fsr_dtl.customer_name;
                    l_customer_address     := fr_cr_fsr_dtl.customer_address;
                    l_customer_city        :=  fr_cr_fsr_dtl.customer_city;
                    l_customer_state       :=  fr_cr_fsr_dtl.customer_state;
                    l_customer_zip_code    :=  fr_cr_fsr_dtl.customer_zip_code;
                    l_customer_acct_num    :=  fr_cr_fsr_dtl.customer_acct_num;
                    l_customer_contact     :=  fr_cr_fsr_dtl.customer_contact;
                    l_customer_email       :=  fr_cr_fsr_dtl.customer_email;
                    l_sr_number            :=  fr_cr_fsr_dtl.sr_number;
                    l_model                :=  fr_cr_fsr_dtl.Model;
                    l_serial               :=  fr_cr_fsr_dtl.serial;
                    l_tag_number           :=  fr_cr_fsr_dtl.tag_number;
                    l_solution_name        :=  fr_cr_fsr_dtl.solution_name; 
                    
                    o_est_hdr_rec          :=   CANON_E307_EST_HDR_REC (
                                                l_CUSTOMER_NAME          ,
                                                l_CUSTOMER_ADDRESS       ,
                                                l_CUSTOMER_CITY          ,
                                                l_CUSTOMER_STATE         ,
                                                l_CUSTOMER_ZIP_CODE      ,
                                                l_CUSTOMER_ACCT_NUM      ,
                                                l_CUSTOMER_CONTACT       ,
                                                l_CUSTOMER_EMAIL         ,
                                                l_SR_NUMBER              ,
                                                l_TAG_NUMBER             ,
                                                l_MODEL                  ,
                                                l_SERIAL                 ,
                                                l_SOLUTION_NAME);
            END LOOP;                                           
  END ESTIMATE_HDR_INFO;
  
 PROCEDURE ESTIMATE_TASK_DETAIL (
      p_in_fsr_no               IN     VARCHAR2,
      o_est_task_dtl_tbl        OUT CANON_E307_EST_TASK_TBL)
     IS
       l_task_num                VARCHAR2(100):=Null;
       l_actual_start_date     VARCHAR2(50):=Null;
       l_actual_end_date       VARCHAR2(50):=Null;
       l_tech_name               VARCHAR2(50):=Null;
       l_task_rec                CANON_E307_EST_TASK_REC;
       
       CURSOR cr_task(crp_fsr_num   fsr.fsr_num%type) IS
       SELECT s_task.svc_task_num task_num,
                    TO_CHAR(TO_DATE(s_task.svc_task_schd_dt,'YYYYMMDD'),'DD-MON-RRRR')||' '||
                    CANON_E307_UTILS.format_time (p_time => s_task.svc_task_schd_tm) actual_start_date,
                    TO_CHAR(TO_DATE(s_task.svc_task_cplt_dt,'YYYYMMDD'),'DD-MON-RRRR')||' '||
                    CANON_E307_UTILS.format_time (p_time => s_task.svc_task_clo_tm) actual_end_date,
                    s_task.tech_cd tech_name 
       FROM svc_task s_task
       WHERE 1=1
       AND s_task.fsr_num = NVL(crp_fsr_num,s_task.fsr_num)
       AND s_task.ezcancelflag = '0'
       AND s_task.glbl_cmpy_cd = 'ADB';
       
       ln_counter      NUMBER(5):=0;
        
    BEGIN
    --    debug_pkg.debug_proc('Inside proc ESTIMATE_TASK_DETAIL');
        o_est_task_dtl_tbl := CANON_E307_EST_TASK_TBL();
        FOR fr_cr_task IN cr_task(p_in_fsr_no) LOOP
           
            l_task_num                :=Null;
            l_actual_start_date       :=Null;
            l_actual_end_date         :=Null;
            l_tech_name               :=Null;
       
           ln_counter:=ln_counter+1;
            
           l_task_num := fr_cr_task.task_num;
           l_actual_start_date := fr_cr_task.actual_start_date;
           l_actual_end_date := fr_cr_task.actual_end_date;
           l_tech_name := fr_cr_task.tech_name;
           l_task_rec := CANON_E307_EST_TASK_REC 
                                (l_task_num,
                                 l_actual_start_date,
                                 l_actual_end_date,
                                 l_tech_name);  
           o_est_task_dtl_tbl.EXTEND ();
           o_est_task_dtl_tbl(ln_counter) := l_task_rec;           
        END LOOP;
    EXCEPTION
       WHEN OTHERS
       THEN
      NULL;
    END ESTIMATE_TASK_DETAIL; 
 PROCEDURE ESTIMATE_PRICE_DETAIL (
      p_in_fsr_no               IN     VARCHAR2,
      o_price_tbl                OUT CANON_E307_EST_PRICE_TBL)
    IS
     l_price_rec                CANON_E307_EST_PRICE_REC;
     CURSOR c1(crp_fsr_num  fsr.fsr_num%type) is 
        SELECT fsr_chrg.fsr_visit_num line_num,
                      fsr_chrg.mdse_cd item_number,
                      mdse_info.mdse_desc_long_txt description,
                      fsr_chrg.svc_chrg_qty qty,
                      fsr_chrg.uom_cd uom,
                      fsr_chrg.svc_chrg_unit_amt list_price,
                      (fsr_chrg.svc_chrg_deal_amt - fsr_chrg.svc_chrg_deal_disc_amt) net_price 
        FROM fsr f_fsr,
                   fsr_chrg_dtl fsr_chrg,
                   ds_mdse_info mdse_info        
        WHERE 1=1
        AND f_fsr.fsr_num = fsr_chrg.fsr_num(+)
        AND f_fsr.ezcancelflag = fsr_chrg.ezcancelflag(+)
        AND f_fsr.glbl_cmpy_cd = fsr_chrg.glbl_cmpy_cd(+)
        AND fsr_chrg.mdse_cd = mdse_info.mdse_cd(+)
        AND fsr_chrg.ezcancelflag = mdse_info.ezcancelflag(+)
        AND fsr_chrg.glbl_cmpy_cd = mdse_info.glbl_cmpy_cd(+)
        AND f_fsr.ezcancelflag = '0'
        AND f_fsr.glbl_cmpy_cd = 'ADB'
        AND f_fsr.fsr_num = NVL(crp_fsr_num,f_fsr.fsr_num);
        
        --SELECT * from CANON_E307_CHG_TBL;
        l_counter NUMBER := 0;
      BEGIN
       o_price_tbl  :=  CANON_E307_EST_PRICE_TBL();
         FOR c1rec in c1(p_in_fsr_no) LOOP
          l_counter := l_counter + 1;
          l_price_rec := CANON_E307_EST_PRICE_REC (
                                    c1rec.line_num,
                                    c1rec.item_number,
                                    c1rec.description,
                                    c1rec.qty,
                                    c1rec.uom,
                                    c1rec.list_price,
                                    c1rec.net_price);
            o_price_tbl .EXTEND ();
            o_price_tbl (l_counter) := l_price_rec;
         END LOOP;
    EXCEPTION
       WHEN OTHERS
       THEN
      NULL;
    END ESTIMATE_PRICE_DETAIL; 
 
PROCEDURE CHARGE_HDR_INFO (
                     p_in_fsr_no IN VARCHAR2,
                     p_task_no IN VARCHAR2
                    ,o_charge_hdr_rec OUT CANON_E307_CHG_HDR_REC
  )
   IS
    l_chrg_upd_flg VARCHAR2(1);
   
    CURSOR chg_hdr_cur(crp_fsr_no    fsr.fsr_num%type,
                                      crp_task_no  svc_task.svc_task_num%type) IS
    SELECT f_fsr.fsr_num,
                  f_fsr.ser_num serial_num,
                  chrgs.fsr_chrg_sq charge_num,
                  Null charge_total,
                  chrgs.svc_inv_num invoice_num,
                  chrgs.inv_dt invoice_date,
                  Null invoice_status,
                  chrgs.svc_prt_deal_amt invoice_amt,
                  chrgs.inv_ccy_cd invoice_currency,
                  s_task.svc_task_num svc_task_num,
                  s_task.mdl_nm mdl_nm,
                  contr.ds_contr_num contract_num,
                  svc_tp.svc_cov_tmpl_tp_desc_txt coverage_type,
                  bill_to.bill_to_cust_cd,
                  bill_to.loc_nm bill_to_cust_nm,
                  bill_to.first_line_addr bill_to_addr1,
                  bill_to.scd_line_addr bill_to_addr2,
                  bill_to.cty_addr bill_to_city,
                  bill_to.st_cd bill_to_st_name,
                  bill_to.post_cd bill_to_zip_cd,
                  ship_to.ship_to_cust_cd,
                  ship_to.loc_nm ship_to_cust_nm,
                  ship_to.first_line_addr ship_to_addr1,
                  ship_to.scd_line_addr ship_to_addr2,
                  ship_to.cty_addr ship_to_city,
                  ship_to.st_cd ship_to_st_name,
                  ship_to.post_cd ship_to_zip_cd,
                  f_fsr.svc_mach_mstr_pk,
                  s_visit.fsr_visit_num,
                  f_fsr.fsr_sts_cd
    FROM fsr f_fsr,
               fsr_chrg chrgs,
               svc_task s_task,
               fsr_visit s_visit,
               ds_contr_dtl contr_dtl,
               ds_contr contr,
               ds_mdse_info mdse_info,
               svc_cov_tmpl_tp svc_tp,
               bill_to_cust bill_to,
               ship_to_cust ship_to        
    WHERE 1=1
    AND f_fsr.fsr_num = chrgs.fsr_num(+)
    AND f_fsr.ezcancelflag = chrgs.ezcancelflag(+)
    AND f_fsr.glbl_cmpy_cd = chrgs.glbl_cmpy_cd(+)
    AND f_fsr.fsr_num = s_task.fsr_num
    AND f_fsr.ezcancelflag = s_task.ezcancelflag
    AND f_fsr.glbl_cmpy_cd = s_task.glbl_cmpy_cd
    AND f_fsr.svc_mach_mstr_pk = contr_dtl.svc_mach_mstr_pk(+)
    AND f_fsr.ezcancelflag = contr_dtl.ezcancelflag(+)
    AND f_fsr.glbl_cmpy_cd = contr_dtl.glbl_cmpy_cd(+)
    AND s_task.svc_task_num = s_visit.svc_task_num(+)
    AND s_task.ezcancelflag = s_visit.ezcancelflag(+)
    AND s_task.glbl_cmpy_cd = s_visit.glbl_cmpy_cd(+)
    AND contr_dtl.ds_contr_pk = contr.ds_contr_pk(+)
    AND contr_dtl.ezcancelflag = contr.ezcancelflag(+)
    AND contr_dtl.glbl_cmpy_cd = contr.glbl_cmpy_cd(+)
    AND contr_dtl.svc_pgm_mdse_cd = mdse_info.mdse_cd(+)
    AND contr_dtl.ezcancelflag = mdse_info.ezcancelflag(+)
    AND contr_dtl.glbl_cmpy_cd = mdse_info.glbl_cmpy_cd(+)
    AND mdse_info.svc_cov_tmpl_tp_cd = svc_tp.svc_cov_tmpl_tp_cd(+)
    AND mdse_info.ezcancelflag = svc_tp.ezcancelflag(+)
    AND mdse_info.glbl_cmpy_cd = svc_tp.glbl_cmpy_cd(+)
    AND f_fsr.bill_to_cust_cd = bill_to.bill_to_cust_cd
    AND f_fsr.ezcancelflag = bill_to.ezcancelflag
    AND f_fsr.glbl_cmpy_cd = bill_to.glbl_cmpy_cd
    AND f_fsr.ship_to_cust_cd = ship_to.ship_to_cust_cd
    AND f_fsr.ezcancelflag = ship_to.ezcancelflag
    AND f_fsr.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
    AND s_task.svc_task_num = (SELECT MAX(s_task1.svc_task_num) 
                                                FROM svc_task s_task1
                                                WHERE 1=1
                                                AND s_task1.ezcancelflag = s_task.ezcancelflag
                                                AND s_task1.glbl_cmpy_cd = s_task.glbl_cmpy_cd
                                                AND s_task1.svc_task_num = s_task.svc_task_num
                                                AND s_task1.fsr_num = s_task.fsr_num)
    AND f_fsr.ezcancelflag = '0'
    AND f_fsr.glbl_cmpy_cd = 'ADB'
    AND f_fsr.fsr_num = NVL(crp_fsr_no,f_fsr.fsr_num) --'50001767'
    AND s_task.svc_task_num = NVL(crp_task_no,s_task.svc_task_num) --'00001797'
    ; 
    --SELECT * FROM CANON_E307_CHG_HDR_TBL;
  BEGIN
  
       FOR chg_hdr_rec in chg_hdr_cur(p_in_fsr_no,p_task_no) LOOP
       
       BEGIN
        SELECT val.val6
            INTO l_chrg_upd_flg
              FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
             WHERE     cd.cd_id = val.cd_id
                   AND cd_name = 'CANON_E307_TASK_STAT_VALUES'
                   AND VAL.VAL1 = chg_hdr_rec.FSR_STS_CD
                   and rownum<2;
       EXCEPTION
       WHEN OTHERS THEN
        l_chrg_upd_flg:='Y';
       END;
       
         o_charge_hdr_rec          :=   CANON_E307_CHG_HDR_REC (
                                                chg_hdr_rec.FSR_NUM                 ,
                                                chg_hdr_rec.SERIAL_NUM              ,
                                                chg_hdr_rec.CHARGE_NUM              ,
                                                chg_hdr_rec.CHARGE_TOTAL            ,
                                                chg_hdr_rec.INVOICE_NUM             ,
                                                chg_hdr_rec.INVOICE_DATE            ,
                                                chg_hdr_rec.INVOICE_STATUS          ,
                                                chg_hdr_rec.INVOICE_AMT             ,
                                                chg_hdr_rec.INVOICE_CURRENCY        ,
                                                chg_hdr_rec.SVC_TASK_NUM            ,
                                                chg_hdr_rec.MDL_NM                  ,
                                                chg_hdr_rec.CONTRACT_NUM            ,
                                                chg_hdr_rec.COVERAGE_TYPE           ,
                                                chg_hdr_rec.BILL_TO_CUST_CD         ,
                                                chg_hdr_rec.BILL_TO_CUST_NM         ,
                                                chg_hdr_rec.BILL_TO_ADDR1           ,
                                                chg_hdr_rec.BILL_TO_ADDR2           ,
                                                chg_hdr_rec.BILL_TO_CITY            ,
                                                chg_hdr_rec.BILL_TO_ST_NAME         ,
                                                chg_hdr_rec.BILL_TO_ZIP_CD          ,
                                                chg_hdr_rec.SHIP_TO_CUST_CD         ,
                                                chg_hdr_rec.SHIP_TO_CUST_NM         ,
                                                chg_hdr_rec.SHIP_TO_ADDR1           ,
                                                chg_hdr_rec.SHIP_TO_ADDR2           ,
                                                chg_hdr_rec.SHIP_TO_CITY            ,
                                                chg_hdr_rec.SHIP_TO_ST_NAME         ,
                                                chg_hdr_rec.SHIP_TO_ZIP_CD          ,
                                                chg_hdr_rec.SVC_MACH_MSTR_PK        ,
                                                chg_hdr_rec.FSR_VISIT_NUM           ,
                                                chg_hdr_rec.FSR_STS_CD              ,
                                                l_chrg_upd_flg        );   
       END LOOP;
  EXCEPTION
       WHEN OTHERS
       THEN
        NULL;
     --   debug_pkg.debug_proc('Inside Exception CHARGE_HDR_INFO');     
  END CHARGE_HDR_INFO; 
  PROCEDURE CHARGE_DETAILS_INFO(
                        p_in_fsr_no          IN VARCHAR2,
                        p_task_no            IN VARCHAR2,
                        o_charge_dtl_tbl OUT CANON_E307_CHG_DTL_TBL
                    )
    IS 
     CURSOR charge_cur(crp_fsr_no   fsr.fsr_num%type,
                                     crp_task_no  svc_task.svc_task_num%type
                                     ) is 
        SELECT chrgs.fsr_chrg_sq charge_num,
                      chrg_dtl.fsr_chrg_dtl_pk line_num,
                      chrg_tp.svc_inv_chrg_tp_desc_txt billing_type,
                      chrg_dtl.mdse_cd item_number,
                      mdse_info.mdse_desc_long_txt item_description,
                      chrg_dtl.svc_chrg_qty qty,
                      chrg_dtl.uom_cd uom_code,
                      chrg_dtl.svc_chrg_unit_amt unit_list_price, 
                      chrg_dtl.ovrd_svc_chrg_unit_amt unit_override_price,
                      chrg_dtl.svc_chrg_deal_amt extended_amount,
                      DECODE(chrg_trx_tp.svc_chrg_trx_tp_nm,'Charge','N','No Charge','Y') no_charge_flag,
                      chrg_trx_tp.svc_chrg_trx_tp_nm trx_type,
                      pcatg.prc_catg_desc_txt trx_price_list,
                      'Service Request' trx_source,
                      f_fsr.fsr_num trx_source_ref,
                      TO_CHAR(TO_DATE(substr(chrg_dtl.ezintime,1,8),'YYYYMMDD'),'DD-MON-RRRR') creation_date,
                      --'N' update_allowed_flag,
                      f_fsr.fsr_num fsr_num,
                      s_task.svc_task_num task_num,
                      chrg_dtl.ovrd_chng_rsn_cd change_reason,
                      chrg_dtl.svc_chrg_deal_disc_amt contract_price,
                      chrg_dtl.ovrd_chng_usr_id updated_by,
                      (chrg_dtl.svc_chrg_deal_amt - chrg_dtl.svc_chrg_deal_disc_amt) net_price,
                      chrg_dtl.fsr_chrg_dtl_pk,
                      chrg_dtl.fsr_visit_num,
                      chrg_dtl.svc_chrg_trx_tp_cd,
                      chrg_dtl.svc_chrg_deal_amt,
                      chrg_dtl.prc_catg_cd,
                      chrg_dtl.svc_inv_chrg_tp_cd,
                      chrg_dtl.svc_chrg_disc_rate
        FROM fsr f_fsr,
                   fsr_chrg chrgs,
                   fsr_chrg_dtl chrg_dtl,
                   svc_inv_chrg_tp chrg_tp,
                   svc_chrg_trx_tp chrg_trx_tp,
                   svc_task s_task,
                   ds_mdse_info mdse_info,
                   prc_catg pcatg      
        WHERE 1=1
        AND f_fsr.fsr_num = chrgs.fsr_num(+)
        AND f_fsr.ezcancelflag = chrgs.ezcancelflag(+)
        AND f_fsr.glbl_cmpy_cd = chrgs.glbl_cmpy_cd(+)
        AND chrgs.fsr_num = chrg_dtl.fsr_num
        AND chrgs.ezcancelflag = chrg_dtl.ezcancelflag
        AND chrgs.glbl_cmpy_cd = chrg_dtl.glbl_cmpy_cd
        AND chrg_dtl.svc_inv_chrg_tp_cd = chrg_tp.svc_inv_chrg_tp_cd
        AND chrg_dtl.ezcancelflag = chrg_tp.ezcancelflag
        AND chrg_dtl.glbl_cmpy_cd = chrg_tp.glbl_cmpy_cd
        AND chrg_dtl.svc_chrg_trx_tp_cd = chrg_trx_tp.svc_chrg_trx_tp_cd
        AND chrg_dtl.ezcancelflag = chrg_trx_tp.ezcancelflag
        AND chrg_dtl.glbl_cmpy_cd = chrg_trx_tp.glbl_cmpy_cd
        AND f_fsr.fsr_num = s_task.fsr_num
        AND f_fsr.ezcancelflag = s_task.ezcancelflag
        AND f_fsr.glbl_cmpy_cd = s_task.glbl_cmpy_cd
        AND chrg_dtl.mdse_cd = mdse_info.mdse_cd(+)
        AND chrg_dtl.ezcancelflag = mdse_info.ezcancelflag(+)
        AND chrg_dtl.glbl_cmpy_cd = mdse_info.glbl_cmpy_cd(+)
        AND chrg_dtl.prc_catg_cd = pcatg.prc_catg_cd(+)
        AND chrg_dtl.ezcancelflag = pcatg.ezcancelflag(+)
        AND chrg_dtl.glbl_cmpy_cd = pcatg.glbl_cmpy_cd(+)
        AND f_fsr.ezcancelflag = '0'
        AND f_fsr.glbl_cmpy_cd = 'ADB'
        AND f_fsr.fsr_num = NVL(crp_fsr_no,f_fsr.fsr_num) -- '50001767'
        AND s_task.svc_task_num = NVL(crp_task_no,s_task.svc_task_num) --'00001797'
        ;
        
        --SELECT distinct * from CANON_E307_CHG_LINES_TBL
        --where FSR_NUM= p_in_fsr_no;
        --AND task_num = p_task_no;
        
        l_counter NUMBER := 0;
        l_charge_dtl_rec                CANON_E307_CHG_DTL_REC;
    BEGIN
 --       debug_pkg.debug_proc('Inside Proc CHG_DETAIL p_task_no: '||p_task_no||' p_in_fsr_no: '||p_in_fsr_no);
        o_charge_dtl_tbl  :=  CANON_E307_CHG_DTL_TBL();
         FOR charge_rec in charge_cur(p_in_fsr_no,p_task_no) LOOP
          l_counter := l_counter + 1;
          l_charge_dtl_rec := CANON_E307_CHG_DTL_REC (
                                    charge_rec.CHARGE_NUM              ,
                                    charge_rec.LINE_NUM                ,
                                    charge_rec.BILLING_TYPE            ,
                                    charge_rec.ITEM_NUMBER             ,
                                    charge_rec.ITEM_DESCRIPTION        ,
                                    charge_rec.QTY                     ,
                                    charge_rec.UOM_CODE                ,
                                    charge_rec.UNIT_LIST_PRICE         ,
                                    charge_rec.UNIT_OVERRIDE_PRICE     ,
                                    charge_rec.EXTENDED_AMOUNT         ,
                                    charge_rec.NO_CHARGE_FLAG          ,
                                    charge_rec.TRX_TYPE                ,
                                    charge_rec.TRX_PRICE_LIST          ,
                                    charge_rec.TRX_SOURCE              ,
                                    charge_rec.TRX_SOURCE_REF          ,
                                    charge_rec.CREATION_DATE           ,
                                    'N'                                ,
                                    charge_rec.FSR_NUM                 ,
                                    charge_rec.task_num,
                                    charge_rec.change_reason,
                                    charge_rec.contract_price,
                                    charge_rec.updated_by,
                                    charge_rec.net_price,
                                    charge_rec.fsr_chrg_dtl_pk,
                                    charge_rec.fsr_visit_num,
                                    charge_rec.svc_chrg_trx_tp_cd,
                                    charge_rec.svc_chrg_deal_amt,
                                    charge_rec.prc_catg_cd,
                                    charge_rec.svc_inv_chrg_tp_cd,
                                    charge_rec.svc_chrg_disc_rate
                                     );
            o_charge_dtl_tbl.EXTEND ();
            o_charge_dtl_tbl(l_counter) := l_charge_dtl_rec;
         END LOOP;
     EXCEPTION
       WHEN OTHERS
       THEN
        NULL;
    --    debug_pkg.debug_proc('Inside Exception CHARGE_DTL_TBL');
     END CHARGE_DETAILS_INFO;
    PROCEDURE TRANSACTION_DETAILS(p_in_fsr_no IN VARCHAR2,
                                                                 p_task_no    IN VARCHAR2,
                                                                 p_billing_type IN VARCHAR2,
                                                                 o_trx_dtl_tbl OUT CANON_E307_TRX_DTL_REC) IS
        CURSOR cr_trx_dtl(crp_fsr_num  fsr.fsr_num%type,
                                      crp_task_num svc_task.svc_task_num%type) IS
        SELECT chrg_tp.svc_chrg_trx_tp_nm trx_type,
                     'Task' charge_source,
                      TO_CHAR(TO_DATE(s_task.svc_task_rcv_dt,'YYYYMMDD'),'DD-MON-RRRR') creation_date,
                      prc_catg_nm trx_price_list,
                      s_task.svc_task_num trx_source_num,
                      chrg_dtl.svc_chrg_deal_disc_amt contract_price
        FROM fsr_chrg_dtl chrg_dtl,
                   svc_task s_task,
                   svc_chrg_trx_tp chrg_tp,
                   prc_catg p_catg
        WHERE 1=1 
        AND chrg_dtl.fsr_num = s_task.fsr_num
        AND chrg_dtl.ezcancelflag = s_task.ezcancelflag
        AND chrg_dtl.glbl_cmpy_cd = s_task.glbl_cmpy_cd
        AND chrg_dtl.svc_chrg_trx_tp_cd = chrg_tp.svc_chrg_trx_tp_cd
        AND chrg_dtl.ezcancelflag = chrg_tp.ezcancelflag
        AND chrg_dtl.glbl_cmpy_cd = chrg_tp.glbl_cmpy_cd
        AND chrg_dtl.prc_catg_cd = p_catg.prc_catg_cd 
        AND chrg_dtl.ezcancelflag = p_catg.ezcancelflag
        AND chrg_dtl.glbl_cmpy_cd = p_catg.glbl_cmpy_cd
        AND chrg_dtl.ezcancelflag = '0'
        AND chrg_dtl.glbl_cmpy_cd = 'ADB'
        AND chrg_dtl.fsr_num = NVL(crp_fsr_num,chrg_dtl.fsr_num) --'50001767'
        AND s_task.svc_task_num = NVL(crp_task_num,s_task.svc_task_num);
        
        --l_counter NUMBER := 0;
        --l_trx_dtl_rec  CANON_E307_TRX_DTL_REC;
                                      
    BEGIN
    
        --o_trx_dtl_tbl  :=  CANON_E307_TRX_DTL_TBL();
        FOR fr_cr_trx_dtl IN cr_trx_dtl(p_in_fsr_no,p_task_no) LOOP
               --l_counter := l_counter + 1;
               o_trx_dtl_tbl := CANON_E307_TRX_DTL_REC (
                                   fr_cr_trx_dtl.trx_type||'-'||p_billing_type,
                                   fr_cr_trx_dtl.charge_source,
                                   fr_cr_trx_dtl.creation_date,
                                   fr_cr_trx_dtl.trx_price_list,
                                   fr_cr_trx_dtl.trx_source_num,
                                   fr_cr_trx_dtl.contract_price
                                    );
            --o_trx_dtl_tbl.EXTEND ();
            --o_trx_dtl_tbl(l_counter) := l_trx_dtl_rec;
        
        END LOOP;
        
    EXCEPTION
       WHEN OTHERS
       THEN
        NULL;
    --    debug_pkg.debug_proc('Inside Exception TRANSACTION_DETAILS');
    END TRANSACTION_DETAILS;  
PROCEDURE CHANGE_REASON_LOV (o_rsn_tbl OUT CANON_E307_CHNG_REASON_TBL)
IS
   l_rec_rsn_cd   CANON_E307_REASON_CODE_REC;
   ln_rec_cnt     NUMBER := 1;

   CURSOR cur_rsn_chng
   IS
   --TBD
      SELECT SVC_CHRG_CHNG_RSN_CD, SVC_CHRG_CHNG_RSN_NM, 
             SVC_CHRG_CHNG_RSN_DESC_TXT
        FROM SVC_CHRG_CHNG_RSN
        WHERE 1=1
       --AND PKG_UOM_CLS_CD IN ()
       AND glbl_cmpy_cd = g_glbl_cmpy_cd
       AND ezcancelflag = g_cancel_flg;
BEGIN
   o_rsn_tbl := CANON_E307_CHNG_REASON_TBL ();

   FOR fr_cur_rsn IN cur_rsn_chng
   LOOP
      l_rec_rsn_cd :=
         CANON_E307_REASON_CODE_REC (fr_cur_rsn.SVC_CHRG_CHNG_RSN_CD,
                                 fr_cur_rsn.SVC_CHRG_CHNG_RSN_NM,
                                 fr_cur_rsn.SVC_CHRG_CHNG_RSN_DESC_TXT);
      o_rsn_tbl.EXTEND ();
      o_rsn_tbl (ln_rec_cnt) := l_rec_rsn_cd;
      ln_rec_cnt := ln_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END CHANGE_REASON_LOV;
                                                                               
 END CANON_E307_CHARGES_PKG;
/
SHOW ERRORS;