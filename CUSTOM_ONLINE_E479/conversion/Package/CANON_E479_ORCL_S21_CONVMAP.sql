/************************************************************************************************
	*                                                                                          *
	* File NAME       : CANON_E479_ORCL_S21_CONVMAP.sql                                              *
	* Object Name     : CANON_E479_ORCL_S21_CONVMAP                                                  *
	* DESCRIPTION     :                                                                        *
	*                                                                                          *
	* REVISION HISTORY:                                                                        *
	*                                                                                          *
	* DEVELOPER         DATE           DESCRIPTION                                             *
	* -------------     -----------    ---------------------------                             *
	* Lakshmi Gopalsami 04/20/17       Initial Creation
	*************************************************************************************************/

CREATE TABLE CANON_E479_ORCL_S21_CONVMAP(
ORCL_ACCOUNT_NUMBER     VARCHAR2(30 CHAR),
ORCL_ACCOUNT_NAME       VARCHAR2(360 CHAR),
S21_ACCOUNT_NUMBER      VARCHAR2(20 CHAR),
S21_ACCOUNT_NAME        VARCHAR2(360 CHAR),
ORCL_PSN                VARCHAR2(30 CHAR),
S21_PSN                 VARCHAR2(30 CHAR),
ORCL_BILL_TO_USE        VARCHAR2(40 CHAR),
S21_BILL_TO_CUST_CD     VARCHAR2(20 CHAR),
CREATION_DATE     DATE,
CREATED_BY        NUMBER,
LAST_UPDATE_DATE  DATE,
LAST_UPDATED_BY   NUMBER,
COMMENTS          VARCHAR2(4000 CHAR),
REMARKS           VARCHAR2(4000 CHAR)
);