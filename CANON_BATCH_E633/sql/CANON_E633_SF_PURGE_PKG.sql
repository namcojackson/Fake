CREATE OR REPLACE PACKAGE S21_CSA_EXTN.CANON_E633_SF_PURGE_PKG AS 

G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E633_SF_PURGE_PKG';

PROCEDURE purge_roles(p_handler_name   IN  VARCHAR2 );
PROCEDURE purge_profiles(p_handler_name   IN  VARCHAR2 );

END CANON_E633_SF_PURGE_PKG;
/

CREATE OR REPLACE PACKAGE BODY S21_CSA_EXTN.CANON_E633_SF_PURGE_PKG
AS

PROCEDURE purge_roles(p_handler_name   IN  VARCHAR2 )
AS
l_procedure_name VARCHAR2(100) := 'purge_roles';

BEGIN
	IF(p_handler_name LIKE '%PPS%') THEN
		EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_pps_sf_role_tbl';
	ELSIF(p_handler_name LIKE '%LFS%') THEN
		EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_lfs_sf_role_tbl';
	END IF;
EXCEPTION
WHEN OTHERS THEN
    ROLLBACK;
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,p_handler_name,NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_roles;

PROCEDURE purge_profiles(p_handler_name   IN  VARCHAR2 )
AS
l_procedure_name VARCHAR2(100) := 'purge_profiles';
BEGIN
	IF(p_handler_name LIKE '%PPS%') THEN
		EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_PPS_SF_PROFILE_TBL';
	ELSIF(p_handler_name LIKE '%LFS%') THEN
		EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_LFS_SF_PROFILE_TBL';
	END IF;
EXCEPTION
   WHEN OTHERS THEN
   ROLLBACK;
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,p_handler_name,NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_profiles;


END CANON_E633_SF_PURGE_PKG;
/
