CREATE OR REPLACE PACKAGE CANON_E404_SF_PURGE_PKG AS 

G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E404_SF_PURGE_PKG';

PROCEDURE purge_roles;
PROCEDURE purge_profiles;
PROCEDURE purge_mass_share;
PROCEDURE purge_opportunities;
PROCEDURE purge_sf_period;
PROCEDURE PURGE_IB;

END CANON_E404_SF_PURGE_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E404_SF_PURGE_PKG
AS

PROCEDURE purge_roles
AS
l_procedure_name VARCHAR2(100) := 'purge_roles';

BEGIN

    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_sf_role_tbl';
    
EXCEPTION
WHEN OTHERS THEN
    ROLLBACK;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_roles;

PROCEDURE purge_profiles
AS
l_procedure_name VARCHAR2(100) := 'purge_profiles';
BEGIN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_sf_profile_tbl';
EXCEPTION
   WHEN OTHERS THEN
   ROLLBACK;
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_profiles;


PROCEDURE purge_mass_share
AS
l_procedure_name VARCHAR2(100) := 'purge_mass_share';

BEGIN

    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_mass_share_req_tbl';
    
EXCEPTION
WHEN OTHERS THEN
    ROLLBACK;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_mass_share;

PROCEDURE purge_opportunities
AS
l_procedure_name VARCHAR2(100) := 'purge_opportunities';

BEGIN

    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_sf_oppr_dwld_tbl';
    
EXCEPTION
WHEN OTHERS THEN
    ROLLBACK;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_opportunities;

PROCEDURE purge_sf_period
AS
l_procedure_name VARCHAR2(100) := 'purge_sf_period';

BEGIN
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_sforce_prd_tbl';
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END purge_sf_period;

PROCEDURE purge_ib
AS
l_procedure_name VARCHAR2(100) := 'purge_ib';

BEGIN
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_ib_ids_to_synch_tbl';
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    CANON_E404_SF_ERROR_LOG_PKG.LOG_ERROR(G_PACKAGE_NAME,L_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END PURGE_ib;



END CANON_E404_SF_PURGE_PKG;
/
