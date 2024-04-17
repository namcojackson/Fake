CREATE OR REPLACE PACKAGE S21_CSA_EXTN.canon_e215_cfs_lease_upg_pkg
IS

G_PACKAGE_NAME VARCHAR2(28) := 'canon_e215_cfs_lease_upg_pkg';

PROCEDURE lease_data_incr_refresh
(x_return_status   OUT VARCHAR2
,x_msg_data        OUT VARCHAR2                          
);

END canon_e215_cfs_lease_upg_pkg;
/
CREATE OR REPLACE PACKAGE BODY S21_CSA_EXTN.canon_e215_cfs_lease_upg_pkg
IS

PROCEDURE upsert_valid_lease_records
IS
   l_procedure_name VARCHAR2(28) := 'upsert_valid_lease_records';
   l_msg_data       VARCHAR2(4000);
   
BEGIN

   DELETE
     FROM canon_e215_cfs_upg_dtl_tbl
    WHERE 1 = 1
      AND(asset_id, NVL(meter_type, '-1'), NVL(overage_tier_key, '-1')) IN
             (SELECT asset_id
                    ,NVL(meter_type, '-1')
                    ,NVL(overage_tier_key, '-1')
                FROM canon_e215_cfs_upg_dtl_stg cecuds
               WHERE 1 = 1
                 AND cecuds.status = 'V'
             ); 

   dbms_output.put_line('No. of existing lease/asset records deleted for repopulation: '||SQL%ROWCOUNT);
   COMMIT;

   INSERT INTO canon_e215_cfs_upg_dtl_tbl
         (lease_number
         ,customer_number
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
         ,equip_desc
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
         ,application_no
         ,cfs_po_number
         ,customer_po_number
         ,asset_id
         ,new_serial_number
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
         )
   SELECT lease_number
         ,customer_number
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
         ,equip_desc
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
         ,application_no
         ,cfs_po_number
         ,customer_po_number
         ,asset_id
         ,new_serial_number
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
     FROM canon_e215_cfs_upg_dtl_stg cecuds
    WHERE 1 = 1
      AND cecuds.status = 'V' ;

   dbms_output.put_line('No. of valid records inserted into canon_e215_cfs_upg_dtl_tbl: '||SQL%ROWCOUNT);
   COMMIT;

   UPDATE canon_e215_cfs_upg_dtl_stg
      SET status = 'P'
    WHERE 1 = 1 
      AND status = 'V';

   dbms_output.put_line('No. of valid processed successfully: '||SQL%ROWCOUNT);
   COMMIT;

EXCEPTION
   WHEN OTHERS THEN
    ROLLBACK;
    l_msg_data      := 'Unexpected Error:'|| SUBSTR(SQLERRM,2000);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,l_msg_data);
END upsert_valid_lease_records;

PROCEDURE lease_data_incr_refresh
(x_return_status   OUT VARCHAR2
,x_msg_data        OUT VARCHAR2                          
)
IS
   l_procedure_name VARCHAR2(28) := 'lease_data_incr_refresh';

   l_max_last_update_date        DATE           := TO_DATE(NULL);
   l_request_id                  NUMBER         := TO_NUMBER(NULL);
   l_request_date                DATE           := TO_DATE(NULL);
   l_valid_serial_count          NUMBER         := TO_NUMBER(NULL);
   l_matching_serial_number      VARCHAR2(30)   := TO_CHAR(NULL);
   /*l_inventory_item_id           csi_item_instances.inventory_item_id%TYPE;
   l_product_code_desc           fnd_flex_values_vl.description%TYPE;
   l_serial_on_lease             canon_e215_cfs_upg_dtl_tbl.new_serial_number%TYPE;
   l_product_code                canon_e215_cfs_upg_dtl_tbl.product_code%TYPE;
   l_contract_number             canon_e215_cfs_upg_dtl_tbl.contract_number%TYPE;
   l_ship_to_party_name          canon_e215_cfs_upg_dtl_tbl.ship_to_party_name%TYPE;
   l_ship_to_party_id            canon_e215_cfs_upg_dtl_tbl.ship_to_party_id%TYPE;
   l_ship_to_site_use_id         canon_e215_cfs_upg_dtl_tbl.ship_to_site_use_id%TYPE;
   l_ship_to_party_site_id       canon_e215_cfs_upg_dtl_tbl.ship_to_party_site_id%TYPE;
   l_ship_to_address1            canon_e215_cfs_upg_dtl_tbl.ship_to_address1%TYPE;
   l_ship_to_address2            canon_e215_cfs_upg_dtl_tbl.ship_to_address2%TYPE;
   l_ship_to_city                canon_e215_cfs_upg_dtl_tbl.ship_to_city%TYPE;
   l_ship_to_state               canon_e215_cfs_upg_dtl_tbl.ship_to_state%TYPE;
   l_ship_to_postal_code         canon_e215_cfs_upg_dtl_tbl.ship_to_postal_code%TYPE;
   l_bill_to_party_name          canon_e215_cfs_upg_dtl_tbl.bill_to_party_name%TYPE;
   l_bill_to_party_id            canon_e215_cfs_upg_dtl_tbl.bill_to_party_id%TYPE;
   l_bill_to_site_use_id         canon_e215_cfs_upg_dtl_tbl.bill_to_site_use_id%TYPE;
   l_bill_to_party_site_id       canon_e215_cfs_upg_dtl_tbl.bill_to_party_site_id%TYPE;
   l_bill_to_address1            canon_e215_cfs_upg_dtl_tbl.bill_to_address1%TYPE;
   l_bill_to_address2            canon_e215_cfs_upg_dtl_tbl.bill_to_address2%TYPE;
   l_bill_to_city                canon_e215_cfs_upg_dtl_tbl.bill_to_city%TYPE;
   l_bill_to_state               canon_e215_cfs_upg_dtl_tbl.bill_to_state%TYPE;
   l_bill_to_postal_code         canon_e215_cfs_upg_dtl_tbl.bill_to_postal_code%TYPE;*/

   l_set_request_set             BOOLEAN;
   l_request_set_submitted1      BOOLEAN;
   l_rs_request_id               NUMBER := TO_NUMBER(NULL);

   
   CURSOR invalid_lease_cur
       IS
   SELECT (SELECT NVL(tbl.new_serial_number, tbl.serial_number)
             FROM canon_e215_cfs_upg_dtl_tbl tbl
            WHERE stg.serial_number LIKE SUBSTR(tbl.serial_number, 1, INSTR(tbl.serial_number, ' ', 1, 1)-1) || '%'
              AND stg.customer_number = tbl.customer_number
              AND ROWNUM < 1
          )machine_serial_number
         ,stg.*
         ,stg.rowid row_id
     FROM canon_e215_cfs_upg_dtl_stg stg
    WHERE NVL(stg.status, 'E') = 'E'
      AND EXISTS(SELECT 1
                   FROM canon_e215_cfs_upg_dtl_tbl tbl
                  WHERE stg.serial_number LIKE SUBSTR(tbl.serial_number, 1, INSTR(tbl.serial_number, ' ', 1, 1)-1) || '%'
                    AND stg.customer_number = tbl.customer_number
                 );
                 
   CURSOR missed_out_records_cur
       IS
   SELECT A.rowid row_id
         ,A.*
     FROM canon_e215_cfs_upg_dtl_stg A
    WHERE NVL(status, 'E') = 'E'
      AND EXISTS(SELECT 1
                   FROM canon_e215_cfs_upg_dtl_tbl b
                  WHERE A.lease_number = b.lease_number
                );


BEGIN
   dbms_output.put_line('Program Starts...'||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   l_request_date := SYSDATE;
   dbms_output.put_line('REQUEST DATE: '||TO_CHAR(l_request_date, 'DD-MON-YYYY HH24:MI:SS'));

   SELECT MAX(cfdv.last_update_date)
     INTO l_max_last_update_date
     FROM canon_e215_cfs_upg_dtl_tbl cfdv;

   dbms_output.put_line('Maximum LAST_UPDATE_DATE: '||TO_CHAR(l_max_last_update_date, 'DD-MON-YYYY HH24:MI:SS'));

   DELETE FROM canon_e215_cfs_upg_dtl_arc
    WHERE request_date < TRUNC(SYSDATE-20);
    
   dbms_output.put_line('Purged Archive Table (canon_e215_cfs_upg_dtl_arc) records older than 20 days :'||SQL%ROWCOUNT);

   BEGIN
      INSERT INTO canon_e215_cfs_upg_dtl_arc
      SELECT *
        FROM canon_e215_cfs_upg_dtl_stg;
      dbms_output.put_line('No. of records Archived (canon_e215_cfs_upg_dtl_arc) :'||SQL%ROWCOUNT);
   EXCEPTION
      WHEN OTHERS THEN
         dbms_output.put_line('Error while archiving. :'||SQLERRM);
   END;

   EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON.CANON_E215_CFS_UPG_DTL_STG';

   dbms_output.put_line('CANON.CANON_E215_CFS_UPG_DTL_STG TRUNCATED.');


   INSERT INTO canon_e215_cfs_upg_dtl_stg
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
         ,request_id
         ,request_date
         )
   SELECT cudv.cfs_lease_num
         ,cudv.cfs_cust_num
         ,cudv.cfs_asset_id
         ,cudv.ser_num
         ,cudv.mtr_tp_cd
         ,cudv.ovag_tier_key_num
         ,cudv.cfs_advtg_txt
         ,cudv.allw_copy_num
         ,cudv.cur_due_amt
         ,cudv.past_due_amt
         ,cudv.aval_line_num
         ,cudv.first_bllg_addr
         ,cudv.scd_bllg_addr
         ,cudv.bllg_cty_addr
         ,cudv.bllg_st_addr
         ,cudv.bllg_zip_addr
         ,cudv.cfs_br_nm
         ,cudv.cfs_bllg_maint_cd
         ,cudv.cr_line_num
         ,cudv.cfs_cust_nm
         ,cudv.DLNQ_STS_NUM
         ,cudv.CFS_DUE_DAYS_CNT
         ,cudv.EQUIP_BLLG_FREQ_CD
         ,cudv.CFS_FLEET_FLG
         ,cudv.FREQ_OVAG_CHRG_CD
         ,cudv.INS_DESC_TXT
         ,cudv.CFS_INV_CD
         ,cudv.INV_LEAD_DAYS_NUM
         ,cudv.INV_METH_CD
         ,cudv.PROP_TAX_INC_IN_PMT_CD
         ,cudv.LAST_PMT_TS
         ,cudv.LAST_UPD_TS
         ,cudv.LEASE_CTAC_NM
         ,cudv.LEASE_CTAC_PHO_NUM
         ,cudv.LEASE_SGN_NM
         ,cudv.LEASE_SGN_PHO_NUM
         ,cudv.CFS_LSSR_CD
         ,cudv.CFS_LOC_CD
         ,cudv.NEXT_PMT_TS
         ,cudv.PMT_MADE_NUM
         ,cudv.ORIG_LEASE_PMT_AMT
         ,cudv.CFS_OVAG_RATE
         ,cudv.CFS_PG_FLG
         ,cudv.PO_CUST_FLG
         ,cudv.PRTY_SVC_FLG
         ,cudv.CFS_SVC_DLR_NM
         ,cudv.SPCL_REF_INFO_TXT
         ,cudv.CFS_TAX_CD
         ,cudv.CFS_TERM_CNT
         ,cudv.CFS_TERM_TS
         ,cudv.CFS_UPG_AMT
         ,cudv.UPG_TO_RTRN_AMT
         ,cudv.CFS_BYOT_AMT
         ,cudv.FIRST_CPC_MACH_REF_TXT
         ,cudv.SCD_CPC_MACH_REF_TXT
         ,cudv.CFS_DLR_CD
         ,cudv.CFS_DLR_NM
         ,cudv.CFS_DSPL_TS
         ,cudv.CFS_EQUIP_NUM
         ,cudv.EQUIP_COST_PCT
         ,cudv.FREQ_MAINT_CHRG_CD
         ,cudv.CFS_INVTY_TS
         ,cudv.INV_SPCL_REF_TXT
         ,cudv.CFS_LEGAL_FLG
         ,cudv.SUB_SUB_FLG
         ,cudv.WRK_OUT_FLG
         ,cudv.CFS_MDL_CD
         ,cudv.ACTV_ASSET_NUM
         ,cudv.PRCH_OPT_CD
         ,cudv.QUOTE_DAYS_FLG
         ,cudv.CFS_RG_CD
         ,cudv.EQUIP_DESC_TXT
         ,cudv.CFS_IF_PO_NUM
         ,cudv.CFS_UPG_APP_NUM
         ,cudv.CFS_CUST_PO_NUM
         ,cudv.FIRST_EQUIP_ADDR
         ,cudv.SCD_EQUIP_ADDR
         ,cudv.equip_cty_addr
         ,cudv.equip_st_addr
         ,cudv.equip_zip_addr
         ,cudv.cfs_pmt_txt
         ,cudv.dsp_tp_cd
         ,cudv.svc_only_txt
         ,cudv.in_rnw_cd
         ,cudv.net_terms_txt
         ,cudv.aggr_allw_copy_num
         ,cudv.pmt_opt_txt
         ,cudv.cfs_cmnc_ts
         ,cudv.cfs_cpc_flg
         ,cudv.cr_line_expr_ts
         ,cudv.quote_expr_ts
         ,cudv.final_asset_usg_amt
         ,cudv.final_asset_usg_tax_amt
         ,cudv.final_maint_amt
         ,cudv.final_au_err_flg
         ,cudv.final_quote_msg_txt
         ,cudv.final_maint_tax_amt
         ,cudv.cust_legal_nm
         ,cudv.cust_dba_txt
         ,cudv.late_chrg_exem_flg
         ,cudv.cfs_tier_flg
         ,cudv.recur_cr_card_flg
         ,cudv.recur_ach_flg
         ,cudv.tot_final_asset_usg_amt
         ,cudv.tot_final_asset_usg_tax_amt
         ,cudv.srg_lease_num
         ,l_request_id
         ,l_request_date
--     FROM cbsupg.cfs_upgrade_detail_v@cfs_view cudv
     FROM NWCI0140_01 cudv
--    WHERE cudv.last_update_date > l_max_last_update_date;
   -- WHERE cudv.LAST_UPD_TS > l_max_last_update_date;
	WHERE to_date(SUBSTR(cudv.LAST_UPD_TS,1,14), 'RRRRMMDDHH24MISS') > l_max_last_update_date;


   dbms_output.put_line('No. of records picked up for processing(From View into Staging Table.): '||SQL%ROWCOUNT);
   COMMIT;

   MERGE 
    INTO canon_e215_cfs_upg_dtl_stg cecuds
   USING(SELECT ser_num
           FROM svc_mach_mstr smm
          WHERE 1=1
            AND smm.glbl_cmpy_cd = 'ADB'
            AND smm.ezcancelflag = '0'
            AND(   smm.svc_by_line_biz_tp_cd = 'ESS'
                OR smm.sld_by_line_biz_tp_cd = 'ESS'
               )
            --AND TRUNC(SYSDATE) BETWEEN to_date(smm.eff_from_dt,'YYYY-MM-DD')  AND to_date(smm.eff_thru_dt,'YYYY-MM-DD')
        ) s21
      ON(cecuds.serial_number = s21.ser_num)
    WHEN MATCHED THEN
  UPDATE
      SET new_serial_number          = s21.ser_num
         ,serial_number_exists_in_ib = 'Y'
         ,status                     = 'V';

   dbms_output.put_line( 'No of Serials found in S21 IB: '|| SQL%ROWCOUNT);
   COMMIT;

   /*MERGE
    INTO canon_e215_cfs_upg_dtl_stg cecuds
   USING(SELECT DISTINCT
                cecuds1.lease_number
           FROM canon_e215_cfs_upg_dtl_stg cecuds1
          WHERE 1 = 1
            AND cecuds1.status IS NULL
            AND cecuds1.serial_number_exists_in_ib = 'Y'
        ) cecuds1
      ON(cecuds.lease_number = cecuds1.lease_number)
    WHEN MATCHED THEN
  UPDATE
     SET status = 'V';*/

   dbms_output.put_line( 'No of Serials found in S21 IB: '|| SQL%ROWCOUNT);
   COMMIT;

   /*Update all records for that lease as valid
   UPDATE canon_e215_cfs_upg_dtl_stg
      SET status = 'V'
    WHERE 1 = 1
      AND status = 'E'
      AND lease_number IN(SELECT cecuds.lease_number
                            FROM canon_e215_cfs_upg_dtl_stg cecuds
                           WHERE 1 = 1
                             AND cecuds.status = 'V'
                         );*/

   UPDATE canon_e215_cfs_upg_dtl_stg
      SET status = 'E'
         ,error_message = 'No valid Serial on the Lease.'
    WHERE 1 = 1
      AND status IS NULL;

   dbms_output.put_line( 'No of records marked as error because of no valid serial on lease: '|| SQL%ROWCOUNT);
   COMMIT;
   
   MERGE
    INTO canon_e215_cfs_upg_dtl_stg cecuds
   USING(SELECT DISTINCT
                cecuds1.rowid
           FROM mdse
               ,canon_e215_cfs_upg_dtl_stg cecuds1
          WHERE 1 = 1
            AND glbl_cmpy_cd = 'ADB'
            AND ezcancelflag = '0'
            AND cecuds1.status = 'E' 
            AND cecuds1.serial_number = mdse_cd 
        ) cecuds1
      ON(cecuds.rowid = cecuds1.rowid)
    WHEN MATCHED THEN
  UPDATE
     SET cfs_serial_is_item_code = 'Y'
        ,error_message           = error_message || '->' || 'Serials are ITEM CODES in Oracle. They are all Accessories.';

   dbms_output.put_line( 'No of Invalid Leases with Serials as Item Codes: '|| SQL%ROWCOUNT);
   COMMIT;

   upsert_valid_lease_records;
 
    MERGE 
     INTO canon_e215_cfs_upg_dtl_stg cecuds
    USING (SELECT DISTINCT
                  cecuds1.rowid
                 ,FIRST_VALUE(celd.serial_number) OVER(PARTITION BY cecuds1.lease_number) new_serial_number --If we find multiple, pick one of the serials in that lease
                 ,celd.lease_number
             FROM canon_e215_cfs_upg_dtl_stg cecuds1
                 ,canon_e244_lease_detail    celd
            WHERE 1 = 1
              AND cecuds1.status = 'E'
              AND cecuds1.lease_number = celd.lease_number 
              AND cecuds1.serial_number LIKE celd.serial_number || '%'
          ) cecuds1
       ON(cecuds.rowid = cecuds1.rowid)
     WHEN MATCHED THEN
   UPDATE
      SET available_in_contracts_view   = 'Y'
         ,new_serial_number             = cecuds1.new_serial_number
         ,status                        = 'V';

   dbms_output.put_line( 'No. of Invalid Lease Records that were found in E244 with Partial Serial (on the lease) search: '||SQL%ROWCOUNT);
   COMMIT;

   upsert_valid_lease_records;

   EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e215_lease_frm_oeoh_tbl';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e215_err_cfs_tbl';

   INSERT INTO canon_e215_lease_frm_oeoh_tbl
		(attribute1
		 ,order_number
		 ,order_type_id
		 ,header_id
		 ,cust_po_number
		 )
   SELECT NULL                  attribute1
         ,c.cpo_ord_num       order_number
		 ,c.ds_ord_tp_cd	    order_type_id
         ,c.cpo_ord_num       header_id
	     ,c.lease_cmpy_po_num cust_po_number
     FROM cpo c
         ,cpo_ord_tp cot  
    WHERE 1= 1
      AND c.glbl_cmpy_cd = 'ADB'
      AND c.ezcancelflag = '0'
      AND cot.glbl_cmpy_cd = 'ADB'
      AND cot.ezcancelflag = '0'
      AND c.ds_ord_tp_cd = cot.cpo_ord_tp_cd
      AND cot.cpo_ord_tp_nm = 'Lease'
      AND c.lease_cmpy_po_num IS NOT NULL;

   dbms_output.put_line( 'No. of lease orders inserted into temporary table: '||SQL%ROWCOUNT);
   COMMIT;
   
	INSERT INTO canon_e215_err_cfs_tbl
        (lease_number
         ,serial_number
         ,customer_po_number
		 ,cfs_po_number
         ,application_no
         ,billing_address1
         ,billing_city
         ,customer_name
         ,LOCATION
         )
    SELECT lease_number          cfs_lease_number
          ,serial_number         cfs_serial_number
          ,customer_po_number    cfs_cust_po_number
		  ,cfs_po_number         cfs_cfs_po_number
          ,application_no        cfs_application_no
          ,billing_address1      cfs_billing_address1
          ,billing_city          cfs_billing_city
          ,customer_name         cfs_customer_name
          ,LOCATION              cfs_location
      FROM canon_e215_cfs_upg_dtl_stg
	  WHERE status = 'E'
	    AND customer_po_number IS NOT NULL
		AND application_no IS NOT NULL;

   dbms_output.put_line( 'No. of lease records that are yet to be validated; inserted into temporary table: '||SQL%ROWCOUNT);
   COMMIT;

	INSERT INTO canon_e215_invalid_lease_tbl
		(cfs_lease_number
         ,cfs_serial_number
         ,cbs_po_agreement_number
         ,cfs_cust_po_number
         ,cfs_cfs_po_number
         ,cfs_application_no
         ,cbs_cust_po_number
         ,cbs_order_number
         ,cbs_order_header_id
         ,cfs_billing_address1
         ,cfs_billing_city
         ,cfs_customer_name
         ,cfs_location
		 )
	 SELECT lease_number          cfs_lease_number
					,serial_number         cfs_serial_number
					,ooha.attribute1       cbs_po_agreement_number
					,customer_po_number    cfs_cust_po_number
					,cfs_po_number         cfs_cfs_po_number
					,application_no        cfs_application_no
					,cust_po_number        cbs_cust_po_number
					,ooha.order_number     cbs_order_number
					,ooha.header_id        cbs_order_header_id
					,billing_address1      cfs_billing_address1
					,billing_city          cfs_billing_city
					,customer_name         cfs_customer_name
					,LOCATION              cfs_location
			 FROM canon_e215_lease_frm_oeoh_tbl ooha
				  ,canon_e215_err_cfs_tbl ceec
			  WHERE 1 = 1
			    AND ooha.cust_po_number LIKE to_char(application_no)||'%'||to_char(cfs_po_number);
			    --AND ooha.cust_po_number LIKE  '%'||to_char(application_no)||'%'
				--AND ooha.cust_po_number LIKE  '%'||to_char(cfs_po_number)||'%';


   dbms_output.put_line( 'No. of lease records validated based on CFS Application Number and Cust PO Number: '||SQL%ROWCOUNT);
   COMMIT;

   MERGE
    INTO canon_e215_cfs_upg_dtl_stg e215
   USING(select DISTINCT
                e215a.cfs_lease_number
               ,e215a.cfs_serial_number
               ,dcd.cpo_ord_num
               ,FIRST_VALUE(dcd.ser_num) OVER(PARTITION BY dcd.cpo_ord_num) matching_serial_number
           from canon_e215_invalid_lease_tbl e215a
               ,cpo_dtl dcd  
          where 1 = 1
            and e215a.cbs_order_number = dcd.cpo_ord_num
            and dcd.ser_num is not null
            and dcd.glbl_cmpy_cd = 'ADB'
            AND dcd.ezcancelflag = '0'
            AND dcd.ser_num LIKE DECODE(INSTR(e215a.cfs_Serial_number, ' '), 0 ,e215a.cfs_Serial_number||'%' ,SUBSTR(e215a.cfs_serial_number, 1, INSTR(e215a.cfs_Serial_number, ' ')-1)||'%')
        ) e215a
     ON(    e215.lease_number = e215a.cfs_lease_number
        AND e215.serial_number = e215a.cfs_serial_number
       )
   WHEN MATCHED THEN
 UPDATE
    SET new_serial_number = e215a.matching_serial_number
       ,status = 'V';
 
   dbms_output.put_line( 'No. of lease records validated and accepted based on CFS Application Number and Cust PO Number: '||SQL%ROWCOUNT);
   COMMIT;

   upsert_valid_lease_records;

   FOR invalid_lease_rec IN invalid_lease_cur LOOP

      DELETE
        FROM canon_e215_cfs_upg_dtl_tbl
       WHERE asset_id = invalid_lease_rec.asset_id
         AND NVL(meter_type, '-1') = NVL(invalid_lease_rec.meter_type, '-1')
         AND NVL(overage_tier_key, '-1') = NVL(invalid_lease_rec.overage_tier_key, '-1');

     INSERT INTO canon_e215_cfs_upg_dtl_tbl
           (lease_number
           ,customer_number
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
           ,equip_desc
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
           ,cfs_po_number
           ,application_no
           ,customer_po_number
           ,asset_id
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
           )
     VALUES(invalid_lease_rec.lease_number
           ,invalid_lease_rec.customer_number
           ,invalid_lease_rec.serial_number
           ,invalid_lease_rec.meter_type
           ,invalid_lease_rec.overage_tier_key
           ,invalid_lease_rec.advantage
           ,invalid_lease_rec.allowable_copies
           ,invalid_lease_rec.amount_current_due
           ,invalid_lease_rec.amount_past_due
           ,invalid_lease_rec.available_line
           ,invalid_lease_rec.billing_address1
           ,invalid_lease_rec.billing_address2
           ,invalid_lease_rec.billing_city
           ,invalid_lease_rec.billing_state
           ,invalid_lease_rec.billing_zip
           ,invalid_lease_rec.branch_name
           ,invalid_lease_rec.cfs_billing_maint
           ,invalid_lease_rec.credit_line
           ,invalid_lease_rec.customer_name
           ,invalid_lease_rec.delinquency_status
           ,invalid_lease_rec.due_date
           ,invalid_lease_rec.equip_billing_frequency
           ,invalid_lease_rec.fleet
           ,invalid_lease_rec.freq_of_overage_charges
           ,invalid_lease_rec.insurance_desc
           ,invalid_lease_rec.invoice_code
           ,invalid_lease_rec.invoice_lead_days
           ,invalid_lease_rec.invoice_method
           ,invalid_lease_rec.is_prop_tax_inc_in_paymt
           ,invalid_lease_rec.last_payment_date
           ,invalid_lease_rec.last_update_date
           ,invalid_lease_rec.lease_contact_name
           ,invalid_lease_rec.lease_contact_phone
           ,invalid_lease_rec.lease_signer_name
           ,invalid_lease_rec.lease_signer_phone
           ,invalid_lease_rec.lessor
           ,invalid_lease_rec.LOCATION
           ,invalid_lease_rec.next_payment_date
           ,invalid_lease_rec.no_of_paymt_made
           ,invalid_lease_rec.original_lease_payment
           ,invalid_lease_rec.overage_rate
           ,invalid_lease_rec.pg
           ,invalid_lease_rec.po_customer
           ,invalid_lease_rec.priority_service
           ,invalid_lease_rec.service_dealer
           ,invalid_lease_rec.special_reference_info
           ,invalid_lease_rec.taxable
           ,invalid_lease_rec.term
           ,invalid_lease_rec.term_date
           ,invalid_lease_rec.UPGRADE
           ,invalid_lease_rec.upgrade_to_return
           ,invalid_lease_rec.buyout
           ,invalid_lease_rec.cpc_machine_ref1
           ,invalid_lease_rec.cpc_machine_ref2
           ,invalid_lease_rec.dealer
           ,invalid_lease_rec.dealer_name
           ,invalid_lease_rec.disposal_date
           ,invalid_lease_rec.equip_code
           ,invalid_lease_rec.equip_desc
           ,invalid_lease_rec.equip_cost_pct
           ,invalid_lease_rec.freq_of_maint_charges
           ,invalid_lease_rec.inventory_date
           ,invalid_lease_rec.invoice_special_ref2
           ,invalid_lease_rec.is_legal
           ,invalid_lease_rec.is_sub_sub
           ,invalid_lease_rec.is_workout
           ,invalid_lease_rec.MODEL
           ,invalid_lease_rec.num_of_active_assets
           ,invalid_lease_rec.purchase_opt_code
           ,invalid_lease_rec.quote_90_days
           ,invalid_lease_rec.region
           ,invalid_lease_rec.cfs_po_number
           ,invalid_lease_rec.application_no
           ,invalid_lease_rec.customer_po_number
           ,invalid_lease_rec.asset_id
           ,invalid_lease_rec.equip_addr1
           ,invalid_lease_rec.equip_addr2
           ,invalid_lease_rec.equip_city
           ,invalid_lease_rec.equip_state
           ,invalid_lease_rec.equip_zip
           ,invalid_lease_rec.payment_amount
           ,invalid_lease_rec.disposition_type
           ,invalid_lease_rec.service_only
           ,invalid_lease_rec.in_renewal
           ,invalid_lease_rec.net_terms
           ,invalid_lease_rec.aggr_allowable_copies
           ,invalid_lease_rec.payment_option
           ,invalid_lease_rec.commencement_date
           ,invalid_lease_rec.is_cpc
           ,invalid_lease_rec.credit_line_expire_date
           ,invalid_lease_rec.quote_expiry_date
           ,invalid_lease_rec.final_asset_usage
           ,invalid_lease_rec.final_asset_usage_tax
           ,invalid_lease_rec.final_maintenance
           ,invalid_lease_rec.final_au_err_flg
           ,invalid_lease_rec.final_quote_msg
           ,invalid_lease_rec.final_maintenance_tax
           ,invalid_lease_rec.cust_legal_name
           ,invalid_lease_rec.cust_dba
           ,invalid_lease_rec.late_chrg_exmpt
           ,invalid_lease_rec.is_tiered
           ,invalid_lease_rec.is_recur_credit_card
           ,invalid_lease_rec.is_recur_ach
           ,invalid_lease_rec.total_final_asset_usage
           ,invalid_lease_rec.total_final_asset_usage_tax
           ,invalid_lease_rec.srg_lease_number
           );

       UPDATE canon_e215_cfs_upg_dtl_stg
          SET status = 'P'
             ,error_message = error_message || '->Partial Serial and Customer Number matches a Valid Lease Record.'
        WHERE rowid = invalid_lease_rec.row_id;

   END LOOP;
   COMMIT;


   FOR missed_out_records_rec IN missed_out_records_cur LOOP

      DELETE
        FROM canon_e215_cfs_upg_dtl_tbl
       WHERE asset_id = missed_out_records_rec.asset_id
         AND NVL(meter_type, '-1') = NVL(missed_out_records_rec.meter_type, '-1')
         AND NVL(overage_tier_key, '-1') = NVL(missed_out_records_rec.overage_tier_key, '-1');

      INSERT INTO canon_e215_cfs_upg_dtl_tbl
            (lease_number
            ,customer_number
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
            ,equip_desc
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
            ,application_no
            ,cfs_po_number
            ,customer_po_number
            ,asset_id
            ,new_serial_number
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
            )
      VALUES(missed_out_records_rec.lease_number
            ,missed_out_records_rec.customer_number
            ,missed_out_records_rec.serial_number
            ,missed_out_records_rec.meter_type
            ,missed_out_records_rec.overage_tier_key
            ,missed_out_records_rec.advantage
            ,missed_out_records_rec.allowable_copies
            ,missed_out_records_rec.amount_current_due
            ,missed_out_records_rec.amount_past_due
            ,missed_out_records_rec.available_line
            ,missed_out_records_rec.billing_address1
            ,missed_out_records_rec.billing_address2
            ,missed_out_records_rec.billing_city
            ,missed_out_records_rec.billing_state
            ,missed_out_records_rec.billing_zip
            ,missed_out_records_rec.branch_name
            ,missed_out_records_rec.cfs_billing_maint
            ,missed_out_records_rec.credit_line
            ,missed_out_records_rec.customer_name
            ,missed_out_records_rec.delinquency_status
            ,missed_out_records_rec.due_date
            ,missed_out_records_rec.equip_billing_frequency
            ,missed_out_records_rec.fleet
            ,missed_out_records_rec.freq_of_overage_charges
            ,missed_out_records_rec.insurance_desc
            ,missed_out_records_rec.invoice_code
            ,missed_out_records_rec.invoice_lead_days
            ,missed_out_records_rec.invoice_method
            ,missed_out_records_rec.is_prop_tax_inc_in_paymt
            ,missed_out_records_rec.last_payment_date
            ,missed_out_records_rec.last_update_date
            ,missed_out_records_rec.lease_contact_name
            ,missed_out_records_rec.lease_contact_phone
            ,missed_out_records_rec.lease_signer_name
            ,missed_out_records_rec.lease_signer_phone
            ,missed_out_records_rec.lessor
            ,missed_out_records_rec.LOCATION
            ,missed_out_records_rec.next_payment_date
            ,missed_out_records_rec.no_of_paymt_made
            ,missed_out_records_rec.original_lease_payment
            ,missed_out_records_rec.overage_rate
            ,missed_out_records_rec.pg
            ,missed_out_records_rec.po_customer
            ,missed_out_records_rec.priority_service
            ,missed_out_records_rec.service_dealer
            ,missed_out_records_rec.special_reference_info
            ,missed_out_records_rec.taxable
            ,missed_out_records_rec.term
            ,missed_out_records_rec.term_date
            ,missed_out_records_rec.UPGRADE
            ,missed_out_records_rec.upgrade_to_return
            ,missed_out_records_rec.buyout
            ,missed_out_records_rec.cpc_machine_ref1
            ,missed_out_records_rec.cpc_machine_ref2
            ,missed_out_records_rec.dealer
            ,missed_out_records_rec.dealer_name
            ,missed_out_records_rec.disposal_date
            ,missed_out_records_rec.equip_code
            ,missed_out_records_rec.equip_desc
            ,missed_out_records_rec.equip_cost_pct
            ,missed_out_records_rec.freq_of_maint_charges
            ,missed_out_records_rec.inventory_date
            ,missed_out_records_rec.invoice_special_ref2
            ,missed_out_records_rec.is_legal
            ,missed_out_records_rec.is_sub_sub
            ,missed_out_records_rec.is_workout
            ,missed_out_records_rec.MODEL
            ,missed_out_records_rec.num_of_active_assets
            ,missed_out_records_rec.purchase_opt_code
            ,missed_out_records_rec.quote_90_days
            ,missed_out_records_rec.region
            ,missed_out_records_rec.application_no
            ,missed_out_records_rec.cfs_po_number
            ,missed_out_records_rec.customer_po_number
            ,missed_out_records_rec.asset_id
            ,missed_out_records_rec.new_serial_number
            ,missed_out_records_rec.equip_addr1
            ,missed_out_records_rec.equip_addr2
            ,missed_out_records_rec.equip_city
            ,missed_out_records_rec.equip_state
            ,missed_out_records_rec.equip_zip
            ,missed_out_records_rec.payment_amount
            ,missed_out_records_rec.disposition_type
            ,missed_out_records_rec.service_only
            ,missed_out_records_rec.in_renewal
            ,missed_out_records_rec.net_terms
            ,missed_out_records_rec.aggr_allowable_copies
            ,missed_out_records_rec.payment_option
            ,missed_out_records_rec.commencement_date
            ,missed_out_records_rec.is_cpc
            ,missed_out_records_rec.credit_line_expire_date
            ,missed_out_records_rec.quote_expiry_date
            ,missed_out_records_rec.final_asset_usage
            ,missed_out_records_rec.final_asset_usage_tax
            ,missed_out_records_rec.final_maintenance
            ,missed_out_records_rec.final_au_err_flg
            ,missed_out_records_rec.final_quote_msg
            ,missed_out_records_rec.final_maintenance_tax
            ,missed_out_records_rec.cust_legal_name
            ,missed_out_records_rec.cust_dba
            ,missed_out_records_rec.late_chrg_exmpt
            ,missed_out_records_rec.is_tiered
            ,missed_out_records_rec.is_recur_credit_card
            ,missed_out_records_rec.is_recur_ach
            ,missed_out_records_rec.total_final_asset_usage
            ,missed_out_records_rec.total_final_asset_usage_tax
            ,missed_out_records_rec.srg_lease_number
            );

      UPDATE canon_e215_cfs_upg_dtl_stg
         SET status = 'P'
            ,error_message = error_message ||'->Part of this Lease is present in CANON_E215_CFS_UPG_DTL_TBL.'
       WHERE rowid = missed_out_records_rec.row_id;

      IF MOD(missed_out_records_cur%ROWCOUNT, 1000) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;
   COMMIT;

   MERGE
    INTO canon_e215_cfs_upg_dtl_tbl a
   USING(SELECT DISTINCT
                cecudt.lease_number
               ,cecudt.serial_number
               ,cecudt.asset_id
               ,cecudt.meter_type
               ,cecudt.overage_tier_key
               ,cecudt.new_serial_number
               ,smm.svc_mach_mstr_pk instance_id
               ,NULL instance_party_id
               ,NULL cip_relationship_type_code
               ,NULL cia_relationship_type_code
               ,btc.first_line_addr || btc.scd_line_addr || btc.cty_addr || btc.cnty_pk || BTC.prov_nm || btc.st_cd || BTC.post_cd  bill_to_address
               ,btc.bill_to_cust_pk bill_cust_acct_site_id
               ,btc.pty_loc_pk bill_party_site_id
               ,btc.cmpy_pk bill_party_id
               ,btc.bill_to_cust_cd bill_location_id
               ,btc.loc_nm bill_party_name
               ,btc.first_line_addr bill_address1
               ,btc.scd_line_addr bill_address2
               ,btc.cty_addr bill_city
               ,btc.st_cd bill_state
               ,btc.post_cd bill_postal_code
               ,stc.first_line_addr || stc.scd_line_addr || stc.cty_addr || stc.cnty_pk || stc.prov_nm || STC.st_cd || stc.post_cd  ship_to_address
               ,stc.ship_to_cust_pk ship_cust_acct_site_id
               ,stc.pty_loc_pk ship_party_site_id
               ,stc.cmpy_pk ship_party_id
               ,stc.ship_to_cust_cd ship_location_id
               ,stc.loc_nm ship_party_name
               ,stc.first_line_addr ship_address1
               ,stc.scd_line_addr ship_address2
               ,stc.cty_addr ship_city
               ,stc.st_cd ship_state
               ,stc.post_cd ship_postal_code
           FROM canon_e215_cfs_upg_dtl_stg cecudt
                   ,svc_mach_mstr smm
                   ,ship_to_cust stc
                   ,bill_to_cust btc
                   --,pty_loc_wrk plw
                   --,ds_acct_cust dac
             WHERE 1 = 1
               AND cecudt.status = 'P'
               AND cecudt.new_serial_number is not null
               AND cecudt.new_serial_number = smm.ser_num
               AND smm.ship_to_cust_cd = stc.ship_to_cust_cd
               AND smm.bill_to_cust_cd = btc.bill_to_cust_cd
               --AND stc.pty_loc_pk = plw.pty_loc_pk
               --AND btc.pty_loc_pk = plw.pty_loc_pk
               and trunc(sysdate) between trunc(to_date(smm.EFF_FROM_DT,'RRRRMMDD')) AND trunc(NVL(to_date(smm.EFF_THRU_DT,'RRRRMMDD'), sysdate))
               and smm.ezcancelflag = '0'
               and smm.glbl_cmpy_cd = 'ADB'
               and stc.ezcancelflag = '0'
               and stc.glbl_cmpy_cd = 'ADB'
               and btc.ezcancelflag = '0'
               and btc.glbl_cmpy_cd = 'ADB'
               ) b
             ON(    a.asset_id = b.asset_id
                AND NVL(a.meter_type, '-1') = NVL(b.meter_type, '-1')
                AND NVL(a.overage_tier_key, -1) = NVL(b.overage_tier_key, -1)
                AND a.serial_number = b.serial_number
               ) 
          WHEN MATCHED THEN
        UPDATE
           SET --product_code            = NVL(l_product_code, product_code)
                --,contract_number         = NVL(l_contract_number, contract_number)
                --,
                 ship_to_party_name      = b.ship_party_name
                ,ship_to_party_id        = b.ship_party_id
                ,ship_to_site_use_id     = b.ship_location_id
                ,ship_to_party_site_id   = b.ship_party_site_id
                ,ship_to_address1        = b.ship_address1
                ,ship_to_address2        = b.ship_address2
                ,ship_to_city            = b.ship_city
                ,ship_to_state           = b.ship_state
                ,ship_to_postal_code     = b.ship_postal_code
                ,bill_to_party_name      = b.bill_party_name
                ,bill_to_party_id        = b.bill_party_id
                ,bill_to_site_use_id     = b.bill_location_id
                ,bill_to_party_site_id   = b.bill_party_site_id
                ,bill_to_address1        = b.bill_address1
                ,bill_to_address2        = b.bill_address2
                ,bill_to_city            = b.bill_city
                ,bill_to_state           = b.bill_state
                ,bill_to_postal_code     = b.bill_postal_code;

        

EXCEPTION
   WHEN OTHERS THEN
    ROLLBACK;
    x_return_status := 'E';
    x_msg_data      := 'Unexpected Error:'|| SUBSTR(SQLERRM,2000);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,x_msg_data);
      
END;

END canon_e215_cfs_lease_upg_pkg;
/
