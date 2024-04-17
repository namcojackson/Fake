CREATE OR REPLACE PACKAGE CANON_e404_sf_roleprofile_pkg
AS
G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E404_SF_ROLEPROFILE_PKG';

PROCEDURE purge_roles;
PROCEDURE purge_profiles;

END CANON_e404_sf_roleprofile_pkg;
/


CREATE OR REPLACE PACKAGE BODY CANON_e404_sf_roleprofile_pkg
AS

PROCEDURE purge_roles
AS
l_procedure_name VARCHAR2(100) := 'purge_roles';

BEGIN

    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_sf_role_tbl';
    
EXCEPTION
WHEN OTHERS THEN
    ROLLBACK;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_roles;

PROCEDURE purge_profiles
AS
l_procedure_name VARCHAR2(100) := 'purge_profiles';
BEGIN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_sf_profile_tbl';
EXCEPTION
   WHEN OTHERS THEN
   ROLLBACK;
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,NULL,NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_profiles;                              

END  CANON_e404_sf_roleprofile_pkg;
