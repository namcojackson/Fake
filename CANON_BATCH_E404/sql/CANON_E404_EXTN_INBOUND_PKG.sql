CREATE OR REPLACE PACKAGE CANON_E404_EXTN_INBOUND_PKG 
AS
PROCEDURE update_E404_Acct_assoc_grp1 (p_psn IN VARCHAR2
                                       ,p_acct_assoc_grp1 IN VARCHAR2
                                       ,x_return OUT Boolean);

END CANON_E404_EXTN_INBOUND_PKG ;
/

CREATE OR REPLACE PACKAGE BODY CANON_E404_EXTN_INBOUND_PKG 
AS
G_PACKAGE_NAME VARCHAR2(200) := 'CANON_E404_EXTN_INBOUND_PKG ';
PROCEDURE update_E404_acct_assoc_grp1(p_psn IN VARCHAR2
                                      ,p_acct_assoc_grp1 IN VARCHAR2
                                      ,x_return OUT Boolean)
IS
    p_procedure_name VARCHAR2(100) := 'UPDATE_E404_ACCT_ASSOC_GRP1';
BEGIN
    UPDATE canon_e404_sf_acct_mapping_tbl
       SET acct_association_grp1 = p_acct_assoc_grp1
           ,status_flag = DECODE(sf_account_id, NULL, 'I', 'U')
           ,oracle_load_date = SYSDATE
     WHERE party_site_number = p_psn ;
     
     COMMIT;
     x_return := TRUE;
     
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        x_return := FALSE ;
        canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME, P_PROCEDURE_NAME, NULL, NULL, NULL, NULL, NULL, SQLERRM);
    
END update_e404_acct_assoc_grp1;

END CANON_E404_EXTN_INBOUND_PKG;
/
