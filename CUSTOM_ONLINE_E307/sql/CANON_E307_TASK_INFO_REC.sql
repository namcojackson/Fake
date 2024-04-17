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

DROP TYPE S21_CSA_EXTN5.CANON_E307_TASK_INFO_TBL
/

DROP TYPE S21_CSA_EXTN5.CANON_E307_TASK_INFO_REC
/

WHENEVER SQLERROR EXIT;

CREATE OR REPLACE TYPE CANON_E307_TASK_INFO_REC IS OBJECT(
  TASK_NUM        VARCHAR2 (10),
  TASK_TYPE_CD    VARCHAR2 (100),
  TASK_TYPE_NM    VARCHAR2 (100),
  TASK_STATUS      VARCHAR2 (100),
  TASK_STATUS_CD    VARCHAR2(100),
  ASSIGNEE_CD      VARCHAR2 (100),
  ASSIGNEE      VARCHAR2 (100),
  ASSIGNEE_TP      VARCHAR2 (100),
  ASSIGNEE_TP_CD  VARCHAR2(30),
  LAST_UPT_BY     VARCHAR2 (100),
  CREAT_DT      VARCHAR2 (100),
  SCHD_START      VARCHAR2 (100),
  SCHD_END      VARCHAR2 (100),
  ACTUAL_START      VARCHAR2 (100),
  ACTUAL_END      VARCHAR2 (100),
  EARLY_START      VARCHAR2 (100),
  LATE_START      VARCHAR2 (100),
  RES_MANAGER      VARCHAR2 (100),
  BRANCH      VARCHAR2 (100),
  VISIT_NUM      VARCHAR2 (100)
);
/

CREATE OR REPLACE TYPE S21_CSA_EXTN5.CANON_E307_TASK_INFO_TBL IS TABLE OF S21_CSA_EXTN5.CANON_E307_TASK_INFO_REC;
/

SHOW ERRORS