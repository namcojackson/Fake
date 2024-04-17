/************************************************************************************************
	*                                                                                          *
	* File NAME       : CANON_E479_UPGRADE_LOG.sql                                              *
	* Object Name     : CANON_E479_UPGRADE_LOG                                                  *
	* DESCRIPTION     :                                                                        *
	*                                                                                          *
	* REVISION HISTORY:                                                                        *
	*                                                                                          *
	* DEVELOPER         DATE           DESCRIPTION                                             *
	* -------------     -----------    ---------------------------                             *
	* Lakshmi Gopalsami 04/17/17       Initial Creation
	*************************************************************************************************/

CREATE TABLE CANON_E479_UPGRADE_LOG(
SCRIPT_NAME         VARCHAR2(4000 CHAR),
MESSAGE1             VARCHAR2(4000 CHAR),
MESSAGE2             VARCHAR2(4000 CHAR),
MESSAGE3             VARCHAR2(4000 CHAR),
MESSAGE4             VARCHAR2(4000 CHAR),
MESSAGE5             VARCHAR2(4000 CHAR),
MESSAGE6             VARCHAR2(4000 CHAR),
ERROR_MESSAGE        CLOB,
CREATION_DATE        DATE
);