CREATE OR REPLACE PACKAGE canon_e404_sf_cfs_lease_pkg
AS

PROCEDURE load_cfs_leases
(
   p_errbuf          IN OUT   VARCHAR2
  ,p_retcode         IN OUT   VARCHAR2
);

end CANON_E404_SF_CFS_LEASE_PKG;
/
CREATE OR REPLACE PACKAGE BODY canon_e404_sf_cfs_lease_pkg
AS
    G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E404_SF_CFS_LEASE_PKG';
PROCEDURE load_cfs_leases
(
   p_errbuf  IN OUT   VARCHAR2
  ,p_retcode IN OUT   VARCHAR2
)
IS
    l_procedure_name        VARCHAR2 (30) := 'load_cfs_leases';

   CURSOR lease_cur(l_max_date DATE)
       IS
   SELECT e215.*
     FROM canon_e215_cfs_upg_dtl_tbl e215
    WHERE last_update_date >= l_max_date
    ;


  TYPE upd_lease_cur_tbl_typ IS TABLE OF lease_cur%ROWTYPE INDEX BY PLS_INTEGER;
  l_upd_lease_cur_tbl upd_lease_cur_tbl_typ;

  l_max_last_update_date DATE := TO_DATE(NULL);
  l_updt_cnt NUMBER := 0;

BEGIN

  dbms_output.put_line('Program Starts...'||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Program Starts...'||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

  --Archive Deleted Records for reference. --- ITG#424237
  dbms_output.put_line('Begin Archive Deleted Records.');
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Begin Archive Deleted Records.');

   INSERT INTO canon_e404_sf_cfs_lease_arc
   SELECT *
     FROM canon_e404_sf_cfs_lease_tbl
    WHERE status_flag = 'DP';
--    WHERE load_status = 'P'
--      AND sf_status = 'D';

   COMMIT;

   DELETE
     FROM canon_e404_sf_cfs_lease_tbl
    WHERE status_flag = 'DP';
--    WHERE load_status = 'P'
--      AND sf_status = 'D';

   dbms_output.put_line('Archived '||SQL%ROWCOUNT||' Deleted Assets.');
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Archived '||SQL%ROWCOUNT||' Deleted Assets.');

   COMMIT;

   dbms_output.put_line('End Archive Deleted Records.'); --- ITG#424237
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'End Archive Deleted Records.');

  SELECT MAX(e404.last_update_date)
    INTO l_max_last_update_date
    FROM canon_e404_sf_cfs_lease_tbl e404
   ;

  IF l_max_last_update_date IS NULL THEN
    l_max_last_update_date := TO_DATE('01-JAN-1753 00:00:00', 'DD-MON-YYYY HH24:MI:SS');
  END IF;

  dbms_output.put_line('Maximum LAST_UPDATE_DATE: '||TO_CHAR(l_max_last_update_date, 'DD-MON-YYYY HH24:MI:SS'));
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Maximum LAST_UPDATE_DATE: '||TO_CHAR(l_max_last_update_date, 'DD-MON-YYYY HH24:MI:SS'));

  -- delete un-processed records
  DELETE FROM canon_e404_sf_cfs_lease_tbl
  WHERE sf_id IS NULL;

  COMMIT;

  -- reset error records
--  UPDATE canon_e404_sf_cfs_lease_tbl
--     SET load_status = 'D'
--        ,oracle_date = SYSDATE
--   WHERE sf_status = 'H'
--     AND sf_id IS NOT NULL
--   ;
   
   UPDATE canon_e404_sf_cfs_lease_tbl
      SET status_flag = 'D'
          ,oracle_date = SYSDATE
    WHERE status_flag = 'DE'
      AND sf_id IS NOT NULL;

--  UPDATE canon_e404_sf_cfs_lease_tbl
--     SET load_status = DECODE(sf_id, NULL, 'I', 'U')
--        ,oracle_date = SYSDATE
--   WHERE sf_status = 'E'
--   ;
   
   UPDATE canon_e404_sf_cfs_lease_tbl
     SET status_flag = DECODE(sf_id, NULL, 'I', 'U')
        ,oracle_date = SYSDATE
   WHERE status_flag = 'E'
   ;
  COMMIT;

  -- mark the records for delete
  UPDATE canon_e404_sf_cfs_lease_tbl e404
--     SET load_status = 'D'
     SET status_flag = 'D'
        ,oracle_date = SYSDATE
--   WHERE NVL(SF_STATUS,'X') <> 'D' --- ITG#424237 --ITG#457977 - Added NVL
--     AND load_status <> 'D'
    WHERE NVL(status_flag,'X') <> 'D'
     AND sf_id IS NOT NULL
     AND (NOT EXISTS (SELECT '1'
                        FROM canon_e215_cfs_upg_dtl_tbl a2
                       WHERE a2.asset_id = e404.asset_id
                         AND NVL(a2.meter_type, '-1') = NVL(e404.meter_type, '-1')
                         AND NVL(a2.overage_tier_key, -1) = NVL(e404.overage_tier_key, -1)
                         --AND a2.lease_number = e404.lease_number --- ITG#424237
                      )
           OR NVL(inventory_date,SYSDATE+1) <= SYSDATE
           OR NVL(disposal_date,SYSDATE+1) <= SYSDATE
         )
     ;

  dbms_output.put_line('No. of records marked for delete in SFDC: ' || SQL%ROWCOUNT);
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'No. of records marked for delete in SFDC: ' || SQL%ROWCOUNT);
  COMMIT;

  -- mark the records for insert
  INSERT INTO canon_e404_sf_cfs_lease_tbl
            (lease_number
            ,customer_number
            ,asset_id
            ,serial_number
            ,meter_type
            ,overage_tier_key
            ,advantage
            ,allowable_copies
            ,amount_current_due
            ,amount_past_due
            ,available_line
            ,billing_address1
            ,billing_address2
            ,billing_city
            ,billing_state
            ,billing_zip
            ,branch_name
            ,cfs_billing_maint
            ,credit_line
            ,customer_name
            ,delinquency_status
            ,due_date
            ,equip_billing_frequency
            ,fleet
            ,freq_of_overage_charges
            ,insurance_desc
            ,invoice_code
            ,invoice_lead_days
            ,invoice_method
            ,is_prop_tax_inc_in_paymt
            ,last_payment_date
            ,last_update_date
            ,lease_contact_name
            ,lease_contact_phone
            ,lease_signer_name
            ,lease_signer_phone
            ,lessor
            ,LOCATION
            ,next_payment_date
            ,no_of_paymt_made
            ,original_lease_payment
            ,overage_rate
            ,pg
            ,po_customer
            ,priority_service
            ,service_dealer
            ,special_reference_info
            ,taxable
            ,term
            ,term_date
            ,UPGRADE
            ,upgrade_to_return
            ,buyout
            ,cpc_machine_ref1
            ,cpc_machine_ref2
            ,dealer
            ,dealer_name
            ,disposal_date
            ,equip_code
            ,equip_cost_pct
            ,freq_of_maint_charges
            ,inventory_date
            ,invoice_special_ref2
            ,is_legal
            ,is_sub_sub
            ,is_workout
            ,MODEL
            ,num_of_active_assets
            ,purchase_opt_code
            ,quote_90_days
            ,region
            ,equip_desc
            ,cfs_po_number
            ,application_no
            ,customer_po_number
            ,equip_addr1
            ,equip_addr2
            ,equip_city
            ,equip_state
            ,equip_zip
            ,payment_amount
            ,disposition_type
            ,service_only
            ,in_renewal
            ,net_terms
            ,aggr_allowable_copies
            ,payment_option
            ,commencement_date
            ,is_cpc
            ,credit_line_expire_date
            ,quote_expiry_date
            ,new_serial_number
            ,salesrep_id
            ,new_model
            ,product_code
            ,contract_number
            ,ship_to_party_name
            ,ship_to_party_id
            ,ship_to_site_use_id
            ,ship_to_party_site_id
            ,ship_to_address1
            ,ship_to_address2
            ,ship_to_city
            ,ship_to_state
            ,ship_to_postal_code
            ,bill_to_party_name
            ,bill_to_party_id
            ,bill_to_site_use_id
            ,bill_to_party_site_id
            ,bill_to_address1
            ,bill_to_address2
            ,bill_to_city
            ,bill_to_state
            ,bill_to_postal_code
            ,comments
            ,parent_serial
            ,final_asset_usage
            ,final_asset_usage_tax
            ,final_maintenance
            ,final_au_err_flg
            ,final_quote_msg
            ,final_maintenance_tax
            ,cust_legal_name
            ,cust_dba
            ,late_chrg_exmpt
            ,is_tiered
            ,is_recur_credit_card
            ,is_recur_ach
            ,total_final_asset_usage
            ,total_final_asset_usage_tax
            ,srg_lease_number
--            ,load_status
            ,oracle_date
--            ,sf_status
            ,sf_date
            ,sf_comments
            ,unique_oracle_id
            ,status_flag
            )
      SELECT lease_number
            ,customer_number
            ,asset_id
            ,serial_number
            ,meter_type
            ,overage_tier_key
            ,advantage
            ,allowable_copies
            ,amount_current_due
            ,amount_past_due
            ,available_line
            ,billing_address1
            ,billing_address2
            ,billing_city
            ,billing_state
            ,billing_zip
            ,branch_name
            ,cfs_billing_maint
            ,credit_line
            ,customer_name
            ,delinquency_status
            ,due_date
            ,equip_billing_frequency
            ,fleet
            ,freq_of_overage_charges
            ,insurance_desc
            ,invoice_code
            ,invoice_lead_days
            ,invoice_method
            ,is_prop_tax_inc_in_paymt
            ,last_payment_date
            ,last_update_date
            ,lease_contact_name
            ,lease_contact_phone
            ,lease_signer_name
            ,lease_signer_phone
            ,lessor
            ,LOCATION
            ,next_payment_date
            ,no_of_paymt_made
            ,original_lease_payment
            ,overage_rate
            ,pg
            ,po_customer
            ,priority_service
            ,service_dealer
            ,special_reference_info
            ,taxable
            ,term
            ,term_date
            ,UPGRADE
            ,upgrade_to_return
            ,buyout
            ,cpc_machine_ref1
            ,cpc_machine_ref2
            ,dealer
            ,dealer_name
            ,disposal_date
            ,equip_code
            ,equip_cost_pct
            ,freq_of_maint_charges
            ,inventory_date
            ,invoice_special_ref2
            ,is_legal
            ,is_sub_sub
            ,is_workout
            ,MODEL
            ,num_of_active_assets
            ,purchase_opt_code
            ,quote_90_days
            ,region
            ,equip_desc
            ,cfs_po_number
            ,application_no
            ,customer_po_number
            ,equip_addr1
            ,equip_addr2
            ,equip_city
            ,equip_state
            ,equip_zip
            ,payment_amount
            ,disposition_type
            ,service_only
            ,in_renewal
            ,net_terms
            ,aggr_allowable_copies
            ,payment_option
            ,commencement_date
            ,is_cpc
            ,credit_line_expire_date
            ,quote_expiry_date
            ,new_serial_number
            ,salesrep_id
            ,new_model
            ,product_code
            ,contract_number
            ,ship_to_party_name
            ,ship_to_party_id
            ,ship_to_site_use_id
            ,ship_to_party_site_id
            ,ship_to_address1
            ,ship_to_address2
            ,ship_to_city
            ,ship_to_state
            ,ship_to_postal_code
            ,bill_to_party_name
            ,bill_to_party_id
            ,bill_to_site_use_id
            ,bill_to_party_site_id
            ,bill_to_address1
            ,bill_to_address2
            ,bill_to_city
            ,bill_to_state
            ,bill_to_postal_code
            ,comments
            ,parent_serial
            ,final_asset_usage
            ,final_asset_usage_tax
            ,final_maintenance
            ,final_au_err_flg
            ,final_quote_msg
            ,final_maintenance_tax
            ,cust_legal_name
            ,cust_dba
            ,late_chrg_exmpt
            ,is_tiered
            ,is_recur_credit_card
            ,is_recur_ach
            ,total_final_asset_usage
            ,total_final_asset_usage_tax
            ,srg_lease_number
--            ,'I'
            ,SYSDATE
--            ,NULL
            ,NULL
            ,'Loaded Successfully'
            ,asset_id || meter_type || overage_tier_key
            ,'I'
        FROM canon_e215_cfs_upg_dtl_tbl e215_1
       WHERE 1=1
         AND NVL(inventory_date,SYSDATE+1) > SYSDATE  --- ITG#424237
         AND NVL(disposal_date,SYSDATE+1) > SYSDATE  --- ITG#424237
         AND NOT EXISTS (SELECT '1'
                           FROM canon_e404_sf_cfs_lease_tbl e404
                          WHERE e404.asset_id = e215_1.asset_id
                            AND NVL(e404.meter_type, '-1') = NVL(e215_1.meter_type, '-1')
                            AND NVL(e404.overage_tier_key, -1) = NVL(e215_1.overage_tier_key, -1)
                            --AND e404.lease_number = e215_1.lease_number
                        );

  dbms_output.put_line('No. of records picked up for insert (From e215 into e404.): ' || SQL%ROWCOUNT);
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'No. of records picked up for insert (From e215 into e404.): ' || SQL%ROWCOUNT);
  COMMIT;

  -- mark the records for update
  OPEN lease_cur(l_max_last_update_date);
  LOOP
    FETCH lease_cur BULK COLLECT INTO l_upd_lease_cur_tbl LIMIT 10000;
    FOR i IN 1 .. l_upd_lease_cur_tbl.COUNT
    LOOP
        UPDATE canon_e404_sf_cfs_lease_tbl
           SET lease_number                =    l_upd_lease_cur_tbl(i).lease_number
              ,customer_number            =    l_upd_lease_cur_tbl(i).customer_number
              ,asset_id                    =    l_upd_lease_cur_tbl(i).asset_id
              ,serial_number             =    l_upd_lease_cur_tbl(i).serial_number
              ,meter_type                =    l_upd_lease_cur_tbl(i).meter_type
              ,overage_tier_key            =    l_upd_lease_cur_tbl(i).overage_tier_key
              ,advantage                =    l_upd_lease_cur_tbl(i).advantage
              ,allowable_copies            =    l_upd_lease_cur_tbl(i).allowable_copies
              ,amount_current_due        =    l_upd_lease_cur_tbl(i).amount_current_due
              ,amount_past_due            =    l_upd_lease_cur_tbl(i).amount_past_due
              ,available_line            =    l_upd_lease_cur_tbl(i).available_line
              ,billing_address1            =    l_upd_lease_cur_tbl(i).billing_address1
              ,billing_address2            =    l_upd_lease_cur_tbl(i).billing_address2
              ,billing_city                =    l_upd_lease_cur_tbl(i).billing_city
              ,billing_state            =    l_upd_lease_cur_tbl(i).billing_state
              ,billing_zip                =    l_upd_lease_cur_tbl(i).billing_zip
              ,branch_name                =    l_upd_lease_cur_tbl(i).branch_name
              ,cfs_billing_maint        =    l_upd_lease_cur_tbl(i).cfs_billing_maint
              ,credit_line                =    l_upd_lease_cur_tbl(i).credit_line
              ,customer_name            =    l_upd_lease_cur_tbl(i).customer_name
              ,delinquency_status        =    l_upd_lease_cur_tbl(i).delinquency_status
              ,due_date                    =    l_upd_lease_cur_tbl(i).due_date
              ,equip_billing_frequency    =    l_upd_lease_cur_tbl(i).equip_billing_frequency
              ,fleet                    =    l_upd_lease_cur_tbl(i).fleet
              ,freq_of_overage_charges    =    l_upd_lease_cur_tbl(i).freq_of_overage_charges
              ,insurance_desc            =    l_upd_lease_cur_tbl(i).insurance_desc
              ,invoice_code                =    l_upd_lease_cur_tbl(i).invoice_code
              ,invoice_lead_days        =    l_upd_lease_cur_tbl(i).invoice_lead_days
              ,invoice_method            =    l_upd_lease_cur_tbl(i).invoice_method
              ,is_prop_tax_inc_in_paymt    =    l_upd_lease_cur_tbl(i).is_prop_tax_inc_in_paymt
              ,last_payment_date        =    l_upd_lease_cur_tbl(i).last_payment_date
              ,last_update_date            =    l_upd_lease_cur_tbl(i).last_update_date
              ,lease_contact_name        =    l_upd_lease_cur_tbl(i).lease_contact_name
              ,lease_contact_phone        =    l_upd_lease_cur_tbl(i).lease_contact_phone
              ,lease_signer_name        =    l_upd_lease_cur_tbl(i).lease_signer_name
              ,lease_signer_phone        =    l_upd_lease_cur_tbl(i).lease_signer_phone
              ,lessor                    =    l_upd_lease_cur_tbl(i).lessor
              ,LOCATION                    =    l_upd_lease_cur_tbl(i).LOCATION
              ,next_payment_date        =    l_upd_lease_cur_tbl(i).next_payment_date
              ,no_of_paymt_made            =    l_upd_lease_cur_tbl(i).no_of_paymt_made
              ,original_lease_payment    =    l_upd_lease_cur_tbl(i).original_lease_payment
              ,overage_rate                =    l_upd_lease_cur_tbl(i).overage_rate
              ,pg                        =    l_upd_lease_cur_tbl(i).pg
              ,po_customer                =    l_upd_lease_cur_tbl(i).po_customer
              ,priority_service            =    l_upd_lease_cur_tbl(i).priority_service
              ,service_dealer            =    l_upd_lease_cur_tbl(i).service_dealer
              ,special_reference_info    =    l_upd_lease_cur_tbl(i).special_reference_info
              ,taxable                    =    l_upd_lease_cur_tbl(i).taxable
              ,term                        =    l_upd_lease_cur_tbl(i).term
              ,term_date                =    l_upd_lease_cur_tbl(i).term_date
              ,UPGRADE                    =    l_upd_lease_cur_tbl(i).UPGRADE
              ,upgrade_to_return        =    l_upd_lease_cur_tbl(i).upgrade_to_return
              ,buyout                    =    l_upd_lease_cur_tbl(i).buyout
              ,cpc_machine_ref1            =    l_upd_lease_cur_tbl(i).cpc_machine_ref1
              ,cpc_machine_ref2            =    l_upd_lease_cur_tbl(i).cpc_machine_ref2
              ,dealer                    =    l_upd_lease_cur_tbl(i).dealer
              ,dealer_name                =    l_upd_lease_cur_tbl(i).dealer_name
              ,disposal_date            =    l_upd_lease_cur_tbl(i).disposal_date
              ,equip_code                =    l_upd_lease_cur_tbl(i).equip_code
              ,equip_cost_pct            =    l_upd_lease_cur_tbl(i).equip_cost_pct
              ,freq_of_maint_charges    =    l_upd_lease_cur_tbl(i).freq_of_maint_charges
              ,inventory_date            =    l_upd_lease_cur_tbl(i).inventory_date
              ,invoice_special_ref2        =    l_upd_lease_cur_tbl(i).invoice_special_ref2
              ,is_legal                    =    l_upd_lease_cur_tbl(i).is_legal
              ,is_sub_sub                =    l_upd_lease_cur_tbl(i).is_sub_sub
              ,is_workout                =    l_upd_lease_cur_tbl(i).is_workout
              ,MODEL                    =    l_upd_lease_cur_tbl(i).MODEL
              ,num_of_active_assets        =    l_upd_lease_cur_tbl(i).num_of_active_assets
              ,purchase_opt_code        =    l_upd_lease_cur_tbl(i).purchase_opt_code
              ,quote_90_days            =    l_upd_lease_cur_tbl(i).quote_90_days
              ,region                    =    l_upd_lease_cur_tbl(i).region
              ,equip_desc                =    l_upd_lease_cur_tbl(i).equip_desc
              ,cfs_po_number            =    l_upd_lease_cur_tbl(i).cfs_po_number
              ,application_no            =    l_upd_lease_cur_tbl(i).application_no
              ,customer_po_number        =    l_upd_lease_cur_tbl(i).customer_po_number
              ,equip_addr1                =    l_upd_lease_cur_tbl(i).equip_addr1
              ,equip_addr2                =    l_upd_lease_cur_tbl(i).equip_addr2
              ,equip_city                =    l_upd_lease_cur_tbl(i).equip_city
              ,equip_state                =    l_upd_lease_cur_tbl(i).equip_state
              ,equip_zip                =    l_upd_lease_cur_tbl(i).equip_zip
              ,payment_amount            =    l_upd_lease_cur_tbl(i).payment_amount
              ,disposition_type            =    l_upd_lease_cur_tbl(i).disposition_type
              ,service_only                =    l_upd_lease_cur_tbl(i).service_only
              ,in_renewal                =    l_upd_lease_cur_tbl(i).in_renewal
              ,net_terms                =    l_upd_lease_cur_tbl(i).net_terms
              ,aggr_allowable_copies    =    l_upd_lease_cur_tbl(i).aggr_allowable_copies
              ,payment_option            =    l_upd_lease_cur_tbl(i).payment_option
              ,commencement_date        =    l_upd_lease_cur_tbl(i).commencement_date
              ,is_cpc                    =    l_upd_lease_cur_tbl(i).is_cpc
              ,credit_line_expire_date    =    l_upd_lease_cur_tbl(i).credit_line_expire_date
              ,quote_expiry_date        =    l_upd_lease_cur_tbl(i).quote_expiry_date
              ,new_serial_number        =    l_upd_lease_cur_tbl(i).new_serial_number
              ,salesrep_id                =    l_upd_lease_cur_tbl(i).salesrep_id
              ,new_model                =    l_upd_lease_cur_tbl(i).new_model
              ,product_code                =    l_upd_lease_cur_tbl(i).product_code
              ,contract_number            =    l_upd_lease_cur_tbl(i).contract_number
              ,ship_to_party_name        =    l_upd_lease_cur_tbl(i).ship_to_party_name
              ,ship_to_party_id            =    l_upd_lease_cur_tbl(i).ship_to_party_id
              ,ship_to_site_use_id        =    l_upd_lease_cur_tbl(i).ship_to_site_use_id
              ,ship_to_party_site_id    =    l_upd_lease_cur_tbl(i).ship_to_party_site_id
              ,ship_to_address1            =    l_upd_lease_cur_tbl(i).ship_to_address1
              ,ship_to_address2            =    l_upd_lease_cur_tbl(i).ship_to_address2
              ,ship_to_city                =    l_upd_lease_cur_tbl(i).ship_to_city
              ,ship_to_state            =    l_upd_lease_cur_tbl(i).ship_to_state
              ,ship_to_postal_code        =    l_upd_lease_cur_tbl(i).ship_to_postal_code
              ,bill_to_party_name        =    l_upd_lease_cur_tbl(i).bill_to_party_name
              ,bill_to_party_id            =    l_upd_lease_cur_tbl(i).bill_to_party_id
              ,bill_to_site_use_id        =    l_upd_lease_cur_tbl(i).bill_to_site_use_id
              ,bill_to_party_site_id    =    l_upd_lease_cur_tbl(i).bill_to_party_site_id
              ,bill_to_address1            =    l_upd_lease_cur_tbl(i).bill_to_address1
              ,bill_to_address2            =    l_upd_lease_cur_tbl(i).bill_to_address2
              ,bill_to_city                =    l_upd_lease_cur_tbl(i).bill_to_city
              ,bill_to_state            =    l_upd_lease_cur_tbl(i).bill_to_state
              ,bill_to_postal_code        =    l_upd_lease_cur_tbl(i).bill_to_postal_code
              ,comments                    =    l_upd_lease_cur_tbl(i).comments
              ,parent_serial            =    l_upd_lease_cur_tbl(i).parent_serial
              ,final_asset_usage        =    l_upd_lease_cur_tbl(i).final_asset_usage
              ,final_asset_usage_tax    =    l_upd_lease_cur_tbl(i).final_asset_usage_tax
              ,final_maintenance        =    l_upd_lease_cur_tbl(i).final_maintenance
              ,final_au_err_flg            =    l_upd_lease_cur_tbl(i).final_au_err_flg
              ,final_quote_msg            =    l_upd_lease_cur_tbl(i).final_quote_msg
              ,final_maintenance_tax    =    l_upd_lease_cur_tbl(i).final_maintenance_tax
              ,cust_legal_name            =    l_upd_lease_cur_tbl(i).cust_legal_name
              ,cust_dba                    =    l_upd_lease_cur_tbl(i).cust_dba
              ,late_chrg_exmpt            =    l_upd_lease_cur_tbl(i).late_chrg_exmpt
              ,is_tiered                =    l_upd_lease_cur_tbl(i).is_tiered
              ,is_recur_credit_card        =    l_upd_lease_cur_tbl(i).is_recur_credit_card
              ,is_recur_ach                =    l_upd_lease_cur_tbl(i).is_recur_ach
              ,total_final_asset_usage    =    l_upd_lease_cur_tbl(i).total_final_asset_usage
              ,total_final_asset_usage_tax    =    l_upd_lease_cur_tbl(i).total_final_asset_usage_tax
              ,srg_lease_number            =    l_upd_lease_cur_tbl(i).srg_lease_number
--              ,load_status                =    'U'
              ,oracle_date                =    SYSDATE
--              ,sf_status                =    NULL
              ,sf_date                    =    NULL
              ,sf_comments                =    'Loaded Successfully'
              ,unique_oracle_id           =  l_upd_lease_cur_tbl(i).asset_id || l_upd_lease_cur_tbl(i).meter_type || l_upd_lease_cur_tbl(i).overage_tier_key
              ,status_flag = 'U'
--       WHERE load_status NOT IN ('D','I')
       WHERE status_flag NOT IN ('D','I')
         AND sf_id IS NOT NULL
--         AND sf_status <> 'D'
         AND asset_id = l_upd_lease_cur_tbl(i).asset_id
         AND NVL(meter_type, '-1') = NVL(l_upd_lease_cur_tbl(i).meter_type, '-1')
         AND NVL(overage_tier_key, -1) = NVL(l_upd_lease_cur_tbl(i).overage_tier_key, -1);

    END LOOP;
        COMMIT;
        EXIT WHEN l_upd_lease_cur_tbl.COUNT < 10000;
  END LOOP;
  CLOSE lease_cur;


  SELECT count(*)
    INTO l_updt_cnt
    FROM canon_e404_sf_cfs_lease_tbl
--   WHERE load_status = 'U'
    WHERE status_flag = 'U'
     AND oracle_date >= l_max_last_update_date
     ;
  dbms_output.put_line('No. of records picked up for update (From e215 into e404.): ' || l_updt_cnt);
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'No. of records picked up for update (From e215 into e404.): ' || l_updt_cnt);

  -- mark the records for delete
  UPDATE canon_e404_sf_cfs_lease_tbl e404
--     SET load_status = 'D'
     SET status_flag = 'D'
        ,oracle_date = SYSDATE
--   WHERE NVL(SF_STATUS,'X') <> 'D' --- ITG#424237 --ITG#457977 - Added NVL
--     AND load_status <> 'D'
     WHERE NVL(status_flag,'X') <> 'D'
     AND sf_id IS NOT NULL
     AND (NOT EXISTS (SELECT '1'
                        FROM canon_e215_cfs_upg_dtl_tbl a2
                       WHERE a2.asset_id = e404.asset_id
                         AND NVL(a2.meter_type, '-1') = NVL(e404.meter_type, '-1')
                         AND NVL(a2.overage_tier_key, -1) = NVL(e404.overage_tier_key, -1)
                         --AND a2.lease_number = e404.lease_number --- ITG#424237
                      )
           OR NVL(inventory_date,SYSDATE+1) <= SYSDATE
           OR NVL(disposal_date,SYSDATE+1) <= SYSDATE
         )
     ;

  dbms_output.put_line('No. of records marked for delete in SFDC: ' || SQL%ROWCOUNT);
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'No. of records marked for delete in SFDC: ' || SQL%ROWCOUNT);
  COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    p_retcode := 'E';
    p_errbuf  := 'Unexpected Error:'|| SUBSTR(SQLERRM,2000);

   -- dbms_output.put_line(p_errbuf);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected Error: ' || sqlerrm);

END load_cfs_leases;

PROCEDURE get_cfs_load_data(
   x_cursor OUT sys_refcursor
)
AS
    l_procedure_name VARCHAR2(60) := 'get_cfs_load_data';
BEGIN
        OPEN x_cursor
            FOR
                SELECT NVL(lease_number,' ') lease_number
                        ,NVL(TO_CHAR(customer_number),' ') customer_number
                        ,NVL(TO_CHAR(asset_id),' ') asset_id
                        ,NVL(serial_number,' ') serial_number
                        ,NVL(meter_type,' ') meter_type
                        ,NVL(TO_CHAR(overage_tier_key),' ') overage_tier_key
                        ,NVL(advantage,' ') advantage
                        ,NVL(TO_CHAR(allowable_copies),' ') allowable_copies
                        ,NVL(TO_CHAR(amount_current_due,'$999,999,999,990.00'),' ') amount_current_due
                        ,NVL(TO_CHAR(amount_past_due,'$999,999,999,990.00'),' ')  amount_past_due
                        ,NVL(TO_CHAR(available_line,'$999,999,999,990.00'),' ')  available_line
                        ,NVL(billing_address1,' ') billing_address1
                        ,NVL(billing_address2,' ') billing_address2
                        ,NVL(billing_city,' ') billing_city
                        ,NVL(billing_state,' ') billing_state
                        ,NVL(billing_zip,' ') billing_zip
                        ,NVL(branch_name,' ') branch_name
                        ,NVL(cfs_billing_maint,' ') cfs_billing_maint
                        ,NVL(TO_CHAR(credit_line,'$999,999,999,990.00'),' ') credit_line
                        ,NVL(customer_name,' ') customer_name
                        ,NVL(TO_CHAR(delinquency_status),' ') delinquency_status
                        ,NVL(TO_CHAR(due_date),' ') due_date
                        ,NVL(equip_billing_frequency,' ') equip_billing_frequency
                        ,NVL(fleet,' ') fleet
                        ,NVL(freq_of_overage_charges,' ') freq_of_overage_charges
                        ,NVL(insurance_desc,' ') insurance_desc
                        ,NVL(invoice_code,' ') invoice_code
                        ,NVL(TO_CHAR(invoice_lead_days),' ') invoice_lead_days
                        ,NVL(invoice_method,' ') invoice_method
                        ,NVL(is_prop_tax_inc_in_paymt,' ') is_prop_tax_inc_in_paymt
                        ,NVL(TO_CHAR(last_payment_date,'Mon DD, YYYY'),' ') last_payment_date
                        ,NVL(TO_CHAR(last_update_date,'Mon DD, YYYY'),' ') last_update_date
                        ,NVL(lease_contact_name,' ') lease_contact_name
                        ,NVL(lease_contact_phone,' ') lease_contact_phone
                        ,NVL(lease_signer_name,' ') lease_signer_name
                        ,NVL(lease_signer_phone,' ') lease_signer_phone
                        ,NVL(lessor,' ') lessor
                        ,NVL(location,' ') location
                        ,NVL(TO_CHAR(next_payment_date,'Mon DD, YYYY'),' ') next_payment_date
                        ,NVL(TO_CHAR(no_of_paymt_made),' ') no_of_paymt_made
                        ,NVL(TO_CHAR(original_lease_payment),' ') original_lease_payment
                        ,NVL(TO_CHAR(overage_rate),' ') overage_rate
                        ,NVL(pg,' ') pg
                        ,NVL(po_customer,' ') po_customer
                        ,NVL(priority_service,' ') priority_service
                        ,NVL(service_dealer,' ') service_dealer
                        ,NVL(special_reference_info,' ') special_reference_info
                        ,NVL(taxable,' ') taxable
                        ,NVL(TO_CHAR(term),' ') term
                        ,NVL(TO_CHAR(term_date,'Mon DD, YYYY'),' ') term_date
                        ,NVL(TO_CHAR(upgrade,'$999,999,999,990.00'),' ') upgrade
                        ,NVL(TO_CHAR(upgrade_to_return,'$999,999,999,990.00'),' ') upgrade_to_return
                        ,NVL(TO_CHAR(buyout,'$999,999,999,990.00'),' ') buyout
                        ,NVL(cpc_machine_ref1,' ') cpc_machine_ref1
                        ,NVL(cpc_machine_ref2,' ') cpc_machine_ref2
                        ,NVL(dealer,' ') dealer
                        ,NVL(dealer_name,' ') dealer_name
                        ,NVL(TO_CHAR(disposal_date,'Mon DD, YYYY'),' ') disposal_date
                        ,NVL(TO_CHAR(equip_code),' ') equip_code
                        ,NVL(TO_CHAR(equip_cost_pct),' ') equip_cost_pct
                        ,NVL(freq_of_maint_charges,' ') freq_of_maint_charges
                        ,NVL(TO_CHAR(inventory_date,'Mon DD, YYYY'),' ') inventory_date
                        ,NVL(invoice_special_ref2,' ') invoice_special_ref2
                        ,NVL(is_legal,' ') is_legal
                        ,NVL(is_sub_sub,' ') is_sub_sub
                        ,NVL(is_workout,' ') is_workout
                        ,NVL(model,' ') model
                        ,NVL(TO_CHAR(num_of_active_assets),' ') num_of_active_assets
                        ,NVL(purchase_opt_code,' ') purchase_opt_code
                        ,NVL(quote_90_days ,' ') quote_90_days
                        ,NVL(region,' ') region
                        ,NVL(equip_desc,' ') equip_desc
                        ,NVL(cfs_po_number,' ') cfs_po_number
                        ,NVL(TO_CHAR(application_no),' ') application_no
                        ,NVL(customer_po_number,' ') customer_po_number
                        ,NVL(equip_addr1,' ') equip_addr1
                        ,NVL(equip_addr2,' ') equip_addr2
                        ,NVL(equip_city,' ') equip_city
                        ,NVL(equip_state,' ') equip_state
                        ,NVL(equip_zip,' ') equip_zip
                        ,NVL(payment_amount,' ') payment_amount
                        ,NVL(disposition_type,' ') disposition_type
                        ,NVL(service_only,' ') service_only
                        ,NVL(in_renewal,' ') in_renewal
                        ,NVL(net_terms,' ') net_terms
                        ,NVL(TO_CHAR(aggr_allowable_copies),' ') aggr_allowable_copies
                        ,NVL(payment_option,' ') payment_option
                        ,NVL(TO_CHAR(commencement_date,'Mon DD, YYYY'),' ') commencement_date
                        ,NVL(is_cpc,' ') is_cpc
                        ,NVL(TO_CHAR(credit_line_expire_date,'Mon DD, YYYY'),' ') credit_line_expire_date
                        ,NVL(TO_CHAR(quote_expiry_date,'Mon DD, YYYY'),' ') quote_expiry_date
                        ,NVL(new_serial_number,' ') new_serial_number
                        ,NVL(TO_CHAR(salesrep_id),' ') salesrep_id
                        ,NVL(new_model,' ') new_model
                        ,NVL(product_code,' ') product_code
                        ,NVL(contract_number,' ') contract_number
                        ,NVL(SUBSTR(ship_to_party_name,1,250),' ') ship_to_party_name
                        ,NVL(TO_CHAR(ship_to_party_id),' ') ship_to_party_id
                        ,NVL(TO_CHAR(ship_to_site_use_id),' ') ship_to_site_use_id
                        ,NVL(TO_CHAR(ship_to_party_site_id),' ') ship_to_party_site_id
                        ,NVL(ship_to_address1,' ') ship_to_address1
                        ,NVL(ship_to_address2,' ') ship_to_address2
                        ,NVL(ship_to_city,' ') ship_to_city
                        ,NVL(ship_to_state,' ') ship_to_state
                        ,NVL(ship_to_postal_code,' ') ship_to_postal_code
                        ,NVL(SUBSTR(bill_to_party_name,1,250),' ') bill_to_party_name
                        ,NVL(TO_CHAR(bill_to_party_id),' ') bill_to_party_id
                        ,NVL(TO_CHAR(bill_to_site_use_id),' ') bill_to_site_use_id
                        ,NVL(TO_CHAR(bill_to_party_site_id),' ') bill_to_party_site_id
                        ,NVL(bill_to_address1,' ') bill_to_address1
                        ,NVL(bill_to_address2,' ') bill_to_address2
                        ,NVL(bill_to_city,' ') bill_to_city
                        ,NVL(bill_to_state,' ') bill_to_state
                        ,NVL(bill_to_postal_code,' ') bill_to_postal_code
                        ,NVL(SUBSTR(comments,1,255),' ') comments
                        ,NVL(parent_serial,' ') parent_serial
                        ,NVL(TO_CHAR(final_asset_usage,'$999,999,999,990.00'),' ') final_asset_usage
                        ,NVL(TO_CHAR(final_asset_usage_tax,'$999,999,999,990.00'),' ') final_asset_usage_tax
                        ,NVL(TO_CHAR(final_maintenance,'$999,999,999,990.00'),' ') final_maintenance
                        ,NVL(final_au_err_flg,' ') final_au_err_flg
                        ,NVL(final_quote_msg,' ') final_quote_msg
                        ,NVL(TO_CHAR(final_maintenance_tax,'$999,999,999,990.00'),' ') final_maintenance_tax
                        ,NVL(cust_legal_name,' ') cust_legal_name
                        ,NVL(cust_dba,' ') cust_dba
                        ,NVL(late_chrg_exmpt,' ') late_chrg_exmpt
                        ,NVL(is_tiered,' ') is_tiered
                        ,NVL(is_recur_credit_card,' ') is_recur_credit_card
                        ,NVL(is_recur_ach,' ') is_recur_ach
                        ,NVL(TO_CHAR(total_final_asset_usage,'$999,999,999,990.00'),' ') total_final_asset_usage
                        ,NVL(TO_CHAR(total_final_asset_usage_tax,'$999,999,999,990.00'),' ') total_final_asset_usage_tax
                        ,NVL(srg_lease_number,' ') srg_lease_number
                        ,sf_id
                FROM canon_e404_sf_cfs_lease_tbl
                WHERE load_status IN ('I','U')
                AND oracle_date >= NVL(sf_date, oracle_date)
                AND NVL(inventory_date,SYSDATE+1) > SYSDATE
                AND NVL(disposal_date,SYSDATE+1) > SYSDATE;

EXCEPTION 
    WHEN OTHERS THEN
    OPEN x_cursor FOR SELECT 1
                        FROM canon_e404_sf_cfs_lease_tbl
                       WHERE 1 = 2;
     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END get_cfs_load_data;


PROCEDURE get_cfs_delete_data(
   x_cursor       OUT      sys_refcursor
)
AS
BEGIN
    OPEN x_cursor FOR SELECT sf_id, asset_id, meter_type, overage_tier_key
                        FROM canon_e404_sf_cfs_lease_tbl
                       WHERE load_status = 'D' 
                         AND sf_id IS NOT NULL ;
EXCEPTION 
    WHEN OTHERS THEN
        OPEN x_cursor FOR SELECT 1
                            FROM canon_e404_sf_cfs_lease_tbl
                           WHERE 1 = 2;
END get_cfs_delete_data;

end CANON_E404_SF_CFS_LEASE_PKG;
/
