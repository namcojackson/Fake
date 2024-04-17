CREATE OR REPLACE PACKAGE canon_e618_orders_rma_stg_pkg
AS
-- -------------------------------------------------------------------------------------------------------------------- --
--                                                                                                                      --
-- Name      :   canon_e618_orders_rma_stg_pkg.sql                                                                      --
-- Created On:   01-FEB-2017                                                                                            --
-- Created By:   Balaji Gowravaram                                                                                --
--                                                                                                                      --
-- Purpose   : This program extract the Sales Orders and RMA's from Oracle tables and insert it into staging tables.    --
--                                                                                                                      --
-- -------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                --
-- Version   By                       Date         Comments                                                             --
-- ------    ----------------------   -----------  -------------------------------------------------------------------- --
-- 1.0       Balaji Gowravaram        01-FEB-2017   Initial                                                             --
--                                                                                                                      --
-- -------------------------------------------------------------------------------------------------------------------- --


    PROCEDURE order_rma_extract(p_run_date_from       IN DATE,
                                p_run_date_to         IN DATE,
                                o_return_status     OUT VARCHAR2,
                                o_return_message    OUT VARCHAR2
                                );
                                
    PROCEDURE main (p_run_date_from     IN DATE DEFAULT '01-JAN-1900',
                    p_run_date_to       IN DATE DEFAULT '31-DEC-2199',
					p_err_msg           OUT VARCHAR2,
					p_err_code          OUT VARCHAR2
                    );
			
	PROCEDURE instance_update;

	PROCEDURE receipt_date_update;
                
	PROCEDURE mv_stage;

    PROCEDURE ret_subinv;
	
	PROCEDURE ret_orig_ref;

	PROCEDURE maint_contract;
				 
                    
END canon_e618_orders_rma_stg_pkg;
/

CREATE OR REPLACE PACKAGE BODY canon_e618_orders_rma_stg_pkg
AS
   -- Declare common Variable
    c_package_name          CONSTANT VARCHAR2 (240) := 'CANON_E618_ORDERS_RMA_STG_PKG';
    v_error_msg             VARCHAR2 (4000) := 'No Error';
    
         
    PROCEDURE order_rma_extract(p_run_date_from       IN DATE,
                                p_run_date_to         IN DATE,
                                o_return_status       OUT VARCHAR2,
                                o_return_message      OUT VARCHAR2
                                )
    IS
    
    c_procedure_name    CONSTANT VARCHAR2(240) := 'ORDER_RMA_EXTRACT';
    v_return_status     VARCHAR2(240) := 'S';
    v_return_message    VARCHAR2(240) := 'No Error';
    l_counter           NUMBER := 0;

    v_run_date_from     DATE := p_run_date_from;
    v_run_date_to       DATE := p_run_date_to;            
    l_trx_date          DATE;
    l_batch_count       NUMBER;
    l_ret               NUMBER;
    l_program_completed VARCHAR2(1);
    l_running_programs  NUMBER;
    
    CURSOR cur_receipt_date
    IS
    SELECT line_id
      FROM canon_e618_order_lines_tbl
     WHERE line_type = 'RETURN';
     
     /*cursor c1 is 
                  select NVL (b.OPERAND, 0) floor_price , d.line_id from QP_PRICING_ATTRIBUTES a,
                                QP_LIST_LINES b,
                                QP_LIST_HEADERS c,
                                CANON_E618_ORDER_LINES_TBL d
                  where 1 = 1
                  -- and product_attr_value = '206'
                  and a.list_line_id = b.list_line_id
                  and b.list_header_id = c.list_header_id
                  and c.name = 'Floor Price List - CBS'
                  and product_attribute = 'PRICING_ATTRIBUTE1'
                  and product_attribute_context = 'ITEM'
                  and d.creation_date between b.start_date_active - 1 and nvl(b.end_date_active, d.creation_date + 1)
                  and product_uom_code = 'EA'
                  and to_char(d.inventory_item_id) = a.product_attr_value;*/

    
    BEGIN
    
    /*fnd_file.put_line (fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line (fnd_file.LOG, '+----------------------------------------------------------------+');
    ------------------------------------------------------------------------------------------------------
    
    fnd_file.put_line (fnd_file.log, 'Truncate Tables');*/
    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e618_order_headers_tbl';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e618_order_lines_tbl';
    
    /*fnd_file.put_line(fnd_file.log, '===================================================================');
    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');*/
    --====================================================================================================
    -- SO and RMA Headers staging
    BEGIN
    
    --fnd_file.put_line(fnd_file.log,  'Inserting into CANON_E618_ORDER_HEADERS_TBL');
    
    INSERT INTO canon_e618_order_headers_tbl (
			header_id,
            order_type_id,
            order_number,
            version_number,
            order_source_id,
            orig_sys_document_ref,
            source_document_id,
            ordered_date,
            request_date,
            pricing_date,
            price_list_id,
            cust_po_number,
            payment_term_id,
            freight_terms_code,
            sold_from_org_id,
            sold_to_org_id,
            ship_from_org_id,
            ship_to_org_id,
            invoice_to_org_id,
            creation_date,
            created_by,
            last_updated_by,
            last_update_date,
            attribute1,
            attribute2,
            attribute9,
            cancelled_flag,
            open_flag,
            booked_flag,
            salesrep_id,
			flow_status_code,
            booked_date,
            sold_to_customer,
            bill_to_customer,
            ship_to_customer,
            order_type,
            customer_number,
            customer_name,  
			bill_to_customer_number,
			bill_to_party_name,
			bill_to_party_id,
			bill_to_party_number,            
			bill_to_address1,
            bill_to_address2,
            bill_to_city,
            bill_to_state,
            bill_to_postal_code,
			ship_to_customer_number,
			ship_to_party_name,
			ship_to_party_id,
			ship_to_party_number,
            ship_to_address1,
            ship_to_address2,
            ship_to_city,
            ship_to_state,
            ship_to_postal_code,
			lease_terms                 
            )
        SELECT dc.cpo_ord_num header_id,
			   dc.ds_ord_catg_cd order_type_id,
			   dc.cpo_ord_num order_number,
			   dc.cpo_upd_vrsn_num version_number,
			   dc.cpo_src_tp_cd order_source_id,
			   dc.ord_src_ref_num orig_sys_document_ref,
			   dc.ord_src_ref_num source_document_id,
			   CAST (TO_TIMESTAMP (dc.cpo_ord_ts, 'RRRRMMDD') AS DATE) ordered_date,
			   NULL request_date,
			   CAST (TO_TIMESTAMP (dc.prc_base_dt, 'RRRRMMDD') AS DATE) pricing_date,
			   dc.prc_catg_cd price_list_id,
			   dc.cust_iss_po_num cust_po_number,
			   dc.add_pmt_term_cash_disc_cd payment_term_id, 
			   dc.frt_cond_cd freight_terms_code,
			   NULL sold_from_org_id, --needs revisit
			   dc.sell_to_cust_cd sold_to_org_id,
			   (SELECT distinct rtl_wh_cd
			     FROM cpo_dtl dcd1
				 WHERE dcd1.cpo_ord_num = dc.cpo_ord_num
				 AND dcd1.glbl_cmpy_cd = 'ADB'
				 AND dcd1.ezcancelflag = '0'
				 AND rownum = 1) ship_from_org_id, 
			   dc.add_ship_to_cust_cd ship_to_org_id,
			   dc.bill_to_cust_cd invoice_to_org_id,
			   CAST (TO_TIMESTAMP(dc.ezintime,'RRRRMMDDHH24MISSFF3') AS DATE) creation_date,
			   dc.ezinuserid created_by,
			   dc.ezupuserid last_updated_by,
			   CAST (TO_TIMESTAMP(dc.ezuptime,'RRRRMMDDHH24MISSFF3') AS DATE) last_update_date,
			   dc.aqu_num attribute1,  --needs revisit
			   dc.nego_deal_amt,
			   stac.coa_cc_cd attribute9,  --FM customer Dept Code
			   dc.cpo_canc_flg cancelled_flag,
			   dc.open_flg open_flag,
			   dc.ord_book_flg booked_flag,
			   dc.sls_rep_psn_num salesrep_id,
			   (select distinct ohs.ord_hdr_sts_nm
				from ord_hdr_sts ohs
				where ohs.ord_hdr_sts_cd = dc.ord_hdr_sts_cd ) flow_status_code,
			   TO_DATE(dc.ord_book_ts_dply_txt,'MM/DD/YYYY HH24:MI:SS') booked_date,
			   dc.sold_to_cust_acct_nm sold_to_customer,
			   dc.bill_to_cust_acct_nm bill_to_customer,
			   dc.ship_to_cust_acct_nm ship_to_customer,
			   dc.ds_ord_catg_desc_txt order_type,
			   shac.sell_to_cust_cd customer_number,
			   shac.ds_acct_nm customer_name,
			   btac.sell_to_cust_cd bill_account_number,
			   btac.ds_acct_nm bill_to_party_name,
			   btac.sell_to_cust_pk bill_to_party_id,
			   btac.sell_to_cust_cd party_number,
			   btc.first_line_addr bill_to_address1,
			   btc.scd_line_addr bill_to_address2,
			   btc.cty_addr bill_to_city,
			   btc.st_cd bill_to_state,
			   btc.post_cd bill_to_postal_code,
			   shac.sell_to_cust_cd ship_to_account_number,
			   shac.ds_acct_nm ship_to_party_name,
			   shac.sell_to_cust_pk ship_to_party_id,
			   shac.sell_to_cust_cd ship_to_party_number,
			   dc.add_ship_to_first_line_addr ship_to_address1,
			   dc.add_ship_to_scd_line_addr ship_to_address2,
			   dc.add_ship_to_cty_addr ship_to_city,
			   dc.add_ship_to_st_cd ship_to_state,
			   dc.add_ship_to_post_cd ship_to_postal_code,
			   dc.lease_term_mth_aot lease_terms
		  FROM 	cpo_v dc,
				sell_to_cust stac,
				sell_to_cust shac,
				sell_to_cust btac,
				bill_to_cust btc,
				canon_e618_customers_tbl e618
		 WHERE dc.ship_to_cust_acct_cd = shac.sell_to_cust_cd
		   AND dc.sell_to_cust_cd = stac.sell_to_cust_cd
		   AND dc.glbl_cmpy_cd = stac.glbl_cmpy_cd
		   AND dc.bill_to_cust_acct_cd = btac.sell_to_cust_cd
		   AND dc.glbl_cmpy_cd = btac.glbl_cmpy_cd
		   AND dc.bill_to_cust_cd = btc.bill_to_cust_cd
		   AND dc.glbl_cmpy_cd = btc.glbl_cmpy_cd
		   AND e618.cust_account_id = stac.sell_to_cust_pk
		   /*AND ooha.order_source_id NOT IN
				  (SELECT oos.order_source_id
					 FROM oe_order_sources oos
					WHERE oos.name IN ('CREDIT', 'REBILL'))*/
		   AND dc.ds_ord_catg_desc_txt IN (SELECT cvt.val2
											 FROM canon_s21_cd_tbl ct, 
												  canon_s21_cd_val_tbl cvt
											WHERE ct.cd_name = 'CANON_E618_ORDERS_RMA_STG'
											  AND ct.cd_id = cvt.cd_id
											  AND SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
		   AND dc.ds_ord_catg_desc_txt NOT IN
										  (SELECT cvt.val2
											 FROM canon_s21_cd_tbl ct, 
												  canon_s21_cd_val_tbl cvt
											WHERE ct.cd_name = 'CANON_E618_ORDERS_RMA_EXCL'
											  AND ct.cd_id = cvt.cd_id
											  AND SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
		   AND dc.ezcancelflag = '0'
		   AND shac.ezcancelflag = '0'
		   AND stac.ezcancelflag = '0'
		   AND btac.ezcancelflag = '0'
		   AND btc.ezcancelflag = '0';


           --AND TRUNC(ooha.creation_date) BETWEEN v_run_date_from AND v_run_date_to;
    	/*fnd_file.put_line(fnd_file.log,  'No. of rows Inserted: '||SQL%ROWCOUNT);
    	fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');*/
    
      COMMIT;
      
      EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_ORDER_HEADERS_TBL COMPUTE STATISTICS';
      
    EXCEPTION
     WHEN OTHERS
     THEN
        v_error_msg := c_package_name||'.'||c_procedure_name||' terminated during Headers staging insert due to '|| SQLCODE|| ' - '|| SQLERRM;
        o_return_status := 'E';
        o_return_message := v_error_msg;
        --fnd_file.put_line(fnd_file.log, v_error_msg);
		dbms_output.put_line(v_error_msg);
        
    END;
    
    COMMIT;
	
	mv_stage;
    
    /*fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Kicking off IB Stage Conc Program. ');
          
  
          l_ret :=
                            FND_REQUEST.submit_request ('CANON',
                                        'CANON_E618_SO_MV_STG',
                                        'Canon E618 SO MV Staging Program',
                                        NULL,
                                        FALSE
                                        );
     COMMIT;

    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Finshed Kicking off IB Stage Conc Program. with Request Id:' || l_ret);*/
  
    
    --====================================================================================================
    -- SO and RMA Lines staging
    BEGIN
    
   /* fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));    
    fnd_file.put_line(fnd_file.log,  'Inserting into CANON_E618_ORDER_LINES_TBL');*/
    
    
    INSERT INTO canon_e618_order_lines_tbl (
            header_id,
			line_id,
            line_type_id,
            line_number,
            ordered_item,
            request_date,
            promise_date,
            schedule_ship_date,
            order_quantity_uom,
            pricing_quantity,
            pricing_quantity_uom,
            cancelled_quantity,
            shipped_quantity,
            ordered_quantity,
            fulfilled_quantity,
            shipping_quantity,
            shipping_quantity_uom,
            tax_exempt_flag,
            tax_exempt_number,
            tax_exempt_reason_code,
            ship_from_org_id,
            ship_to_org_id,
            invoice_to_org_id,
            deliver_to_org_id,
            ship_to_contact_id,
            deliver_to_contact_id,
            invoice_to_contact_id,
            intmed_ship_to_org_id,
            intmed_ship_to_contact_id,
            sold_from_org_id,
            sold_to_org_id,
            cust_po_number,
            cust_model_serial_number,
            inventory_item_id,
            price_list_id,
            pricing_date,
            shipment_number,
            unit_selling_price,
            unit_list_price,
            attribute4,
            attribute17,
            creation_date,
            created_by,
            last_update_date,
            last_updated_by,
            last_update_login,
            item_type_code,
            option_number,
            option_flag,
            line_category_code,
            --actual_shipment_date,
            customer_trx_line_id,
            return_context,
            return_attribute1,
            return_attribute2,
            component_number,
            cancelled_flag,
            open_flag,
            booked_flag,
            salesrep_id,
            return_reason_code,
            order_source_id,
            drop_ship_flag,
            shipping_instructions,
            service_number,
            flow_status_code,
            subinventory,
            unit_cost,
			line_type,
			warehouse,
			warehouse_code,
			item_name,
			item_description,
			merch_type_code,
			product_code,
			equip_model,
			--serial_number,
			--instance_id,
			--install_date,
			--instance_status,
			lot_number,
			servicing_dealer_name,
			servicing_dealer_code,
			wholesale_price,
			warehouse_receipt_date,
			contract_number,
			contract_maint_amount,
			contract_ship_to,
			prior_source_line_id,
			sold_order_number,
			sold_order_header_id,
			sold_order_line_number,
			sold_line_id,
			sold_order_type,
			sold_to_price,
			sold_quantity,
			sold_customer_name,
			sold_account_number,
			sold_order_ship_from_org_id,
			sold_order_ship_from_warehouse,
			sold_order_shipping_inst,
			return_subinventory,
			floor_price
			)
       SELECT 
				dcd.cpo_ord_num header_id,
				(dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) line_id,
				dcd.ds_ord_line_catg_cd line_type_id,
				(dcd.cpo_dtl_line_num ||'.'|| dcd.cpo_dtl_line_sub_num) line_number,
				dcd.mdse_cd ordered_item,
				NULL request_date,
				NULL promise_date,
				NULL schedule_ship_date,
				dcd.cust_uom_cd order_quantity_uom,
				NULL pricing_quantity,
				dcd.cust_uom_cd pricing_quantity_uom,
				dcd.canc_qty cancelled_quantity,
				dcd.ship_qty shipped_quantity,
				dcd.ord_qty ordered_quantity,
				dcd.istl_qty fulfilled_quantity,
				dcd.ship_qty shipping_quantity,
				'EA' shipping_quantity_uom,
				NULL tax_exempt_flag,
				NULL tax_exempt_number,
				NULL tax_exempt_reason_code,
				dcd.rtl_wh_cd ship_from_org_id,
				(SELECT dcc.ship_to_cust_acct_cd
				   FROM ds_cpo_config_v dcc
				   WHERE dcc.cpo_ord_num = dcd.cpo_ord_num
				   AND dcc.ds_cpo_config_pk = dcd.ds_cpo_config_pk
				   AND dcc.glbl_cmpy_cd = 'ADB'
				   AND dcc.ezcancelflag = '0') ship_to_org_id,
				(SELECT dcc.bill_to_cust_acct_cd
				   FROM ds_cpo_config_v dcc
				   WHERE dcc.cpo_ord_num = dcd.cpo_ord_num
				   AND dcc.ds_cpo_config_pk = dcd.ds_cpo_config_pk
				   AND dcc.glbl_cmpy_cd = 'ADB'
				   AND dcc.ezcancelflag = '0') invoice_to_org_id,
				NULL deliver_to_org_id,
				(SELECT ctac_psn_pk
				   FROM ds_cpo_ctac_psn dccp
				   WHERE dccp.cpo_ord_num = dcd.cpo_ord_num
				     AND dccp.ctac_psn_tp_cd = 'SHIPTO'
					 AND dccp.ezcancelflag = '0'
					 AND dccp.glbl_cmpy_cd = 'ADB'
					 AND rownum=1) ship_to_contact_id,
				(SELECT ctac_psn_pk
				   FROM ds_cpo_ctac_psn dccp
				   WHERE dccp.cpo_ord_num = dcd.cpo_ord_num
				     AND dccp.ctac_psn_tp_cd = 'DELIV_INSTALL'
					 AND dccp.ezcancelflag = '0'
					 AND dccp.glbl_cmpy_cd = 'ADB'
					 AND rownum=1) deliver_to_contact_id,
				(SELECT ctac_psn_pk
				   FROM ds_cpo_ctac_psn dccp
				   WHERE dccp.cpo_ord_num = dcd.cpo_ord_num
				     AND dccp.ctac_psn_tp_cd = 'BILLTO'
					 AND dccp.ezcancelflag = '0'
					 AND dccp.glbl_cmpy_cd = 'ADB'
					 AND rownum=1) invoice_to_contact_id,
				NULL intmed_ship_to_org_id,
				NULL intmed_ship_to_contact_id,
				dcd.rtl_wh_cd sold_from_org_id,
				(SELECT c.sell_to_cust_cd 
				 FROM cpo c
				 WHERE c.cpo_ord_num = dcd.cpo_ord_num
				 AND c.glbl_cmpy_cd = 'ADB'
				 AND c.ezcancelflag = '0') sold_to_org_id,
				dcd.cust_iss_po_num cust_po_number,
				dcd.ser_num cust_model_serial_number,
				dcd.mdse_cd inventory_item_id,
				dcd.prc_catg_cd price_list_id,
				CAST (TO_TIMESTAMP(dcd.prc_base_dt,'RRRRMMDD') AS DATE) pricing_date,
				1 shipment_number,
				dcd.ent_func_net_unit_prc_amt unit_selling_price,
				dcd.func_prc_list_prc_amt unit_list_price,
				NULL attribute4,
				(SELECT smm.ser_num
				  FROM cpo_dtl dcd1,
				       svc_mach_mstr smm
				  WHERE smm.cpo_ord_num = dcd1.cpo_ord_num
				  AND smm.cpo_dtl_line_num = dcd1.cpo_dtl_line_num
				  AND smm.cpo_dtl_line_sub_num = dcd1.cpo_dtl_line_sub_num
		  		  AND dcd1.base_cmpt_flg = 'Y'
				  AND dcd1.ds_cpo_config_pk = dcd.ds_cpo_config_pk
				  AND smm.glbl_cmpy_cd = 'ADB'
				  AND smm.ezcancelflag = '0'
				  AND rownum = 1) attribute17,
				CAST (TO_TIMESTAMP(dcd.ezintime,'RRRRMMDDHH24MISSFF3') AS DATE) creation_date,
				dcd.ezinuserid created_by,
				CAST (TO_TIMESTAMP(dcd.ezuptime,'RRRRMMDDHH24MISSFF3') AS DATE) last_update_date,
				dcd.ezupuserid last_updated_by,
				dcd.ezupuserid last_update_login,
				(SELECT mdse_item_tp_nm
				   FROM mdse_item_tp mit
				  WHERE 1 = 1
					AND dmi.mdse_item_tp_cd = mit.mdse_item_tp_cd
					AND mit.glbl_cmpy_cd = 'ADB'
					AND mit.ezcancelflag = '0') item_type_code,
				NULL option_number,
				NULL option_flag,
				'ORDER' line_category_code,
				--sp.actl_ship_dt actual_shipment_date,
				NULL customer_trx_line_id,
				NULL return_context,
				NULL return_attribute1,
				NULL return_attribute2,
				NULL component_number,
				dcd.cpo_dtl_canc_flg cancelled_flag,
				dcd.open_flg open_flag,
				dosv.ord_book_flg booked_flag,
				(SELECT dc.sls_rep_psn_num
				 FROM cpo_v dc
				 WHERE dc.cpo_ord_num = dcd.cpo_ord_num
				 AND dc.glbl_cmpy_cd = 'ADB'
				 AND dc.ezcancelflag = '0') salesrep_id,
				NULL return_reason_code,
				NULL order_source_id,
				dcd.drop_ship_flg drop_ship_flag,
				NULL shipping_instructions,
				dcd.ds_cpo_line_sub_num service_number,
				dosv.ord_line_sts_nm flow_status_code,
				dcd.rtl_swh_cd subinventory,
				(SELECT this_mth_tot_std_cost_amt
				 FROM mdse m
				 WHERE m.mdse_cd = dcd.mdse_cd
				 AND m.glbl_cmpy_cd = 'ADB'
				 AND m.ezcancelflag = '0') unit_cost,
				NULL line_type,
				(SELECT rw.rtl_wh_nm
				   FROM rtl_wh rw
				  WHERE rw.rtl_wh_cd = dcd.rtl_wh_cd
					AND rw.glbl_cmpy_cd = 'ADB'
					AND rw.ezcancelflag = '0')	warehouse,
				dcd.rtl_wh_cd warehouse_code,
				dcd.mdse_cd item_name,
				dcd.mdse_nm item_description,
				dmi.coa_mdse_tp_cd merch_type_code,
				NULL, --dcd.coa_prod_cd product_code,
				/*(SELECT mkt_mdl_nm
				  FROM mkt_mdl mm
				  WHERE mm.mkt_mdl_cd = dmi.mkt_mdl_cd
				  AND mm.glbl_cmpy_cd = 'ADB'
				  AND mm.ezcancelflag = '0') equip_model,*/
				(SELECT t_mdl_nm
				   FROM ds_cpo_config_v dcc
				   WHERE dcc.ds_cpo_config_pk = dcd.ds_cpo_config_pk
				   AND dcc.glbl_cmpy_cd = 'ADB'
				   AND dcc.ezcancelflag = '0') equip_model,
				/*dcd.ser_num serial_number,
				smm.svc_mach_mstr_pk instance_id,
				CAST (TO_TIMESTAMP(smm.istl_dt,'RRRRMMDD') AS DATE) install_date ,
				(SELECT smms.svc_mach_mstr_sts_nm
				   FROM svc_mach_mstr_sts smms
				  WHERE smms.svc_mach_mstr_sts_cd = smm.svc_mach_mstr_sts_cd
					AND smms.glbl_cmpy_cd = 'ADB'
					AND smms.ezcancelflag = '0') instance_status,*/
				NULL lot_number,
				NULL servicing_dealer_name, 
				NULL servicing_dealer_code,
				NULL wholesale_price,
				NULL warehouse_receipt_date,
				dcd.ds_contr_num contract_number,
				NULL contract_maint_amount,
				NULL contract_ship_to,
				NULL prior_source_line_id ,
				NULL sold_order_number ,
				NULL sold_order_header_id ,
				NULL sold_order_line_number ,
				NULL sold_line_id ,
				NULL sold_order_type ,
				NULL sold_to_price,
				NULL sold_quantity, 
				NULL sold_customer_name ,
				NULL sold_account_number ,
				NULL sold_order_ship_from_org_id ,
				NULL sold_order_ship_from_warehouse ,
				NULL sold_order_shipping_inst,
				NULL return_subinventory,
				(SELECT ple.prc_list_equip_prc_amt
				  FROM prc_list_equip ple
				 WHERE ple.prc_catg_cd = dcd.fl_prc_list_cd
				   AND ple.prc_qlfy_val_txt = dcd.mdse_cd
				   AND ple.glbl_cmpy_cd = 'ADB'
				   AND ple.ezcancelflag = '0') floor_price
				FROM cpo_dtl_v dcd,
					 ds_ord_sts_v dosv,
					 all_mdse_v dmi
				WHERE 1 = 1
				  AND dmi.mdse_cd = dcd.mdse_cd
				  AND dosv.cpo_ord_num = dcd.cpo_ord_num
				  AND dosv.cpo_dtl_line_num = dcd.cpo_dtl_line_num
				  AND dosv.cpo_dtl_line_sub_num = dcd.cpo_dtl_line_sub_num
				  AND dosv.ezcancelflag = '0'
				  AND dmi.ezcancelflag = '0'
				  AND EXISTS (select 'x' from
											s21_csa_extn.canon_e618_order_headers_tbl e618_hdr        
											WHERE e618_hdr.header_id = dcd.cpo_ord_num)
				  UNION ALL
				  SELECT 
				dcd.cpo_ord_num header_id,
				(dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) line_id,
				dcd.ds_ord_line_catg_cd line_type_id,
				(dcd.ds_cpo_rtrn_line_num ||'.'|| dcd.ds_cpo_rtrn_line_sub_num) line_number,
				dcd.mdse_cd ordered_item,
				NULL request_date,
				NULL promise_date,
				NULL schedule_ship_date,
				dcd.cust_uom_cd order_quantity_uom,
				NULL pricing_quantity,
				dcd.cust_uom_cd pricing_quantity_uom,
				dcd.canc_qty cancelled_quantity,
				dcd.RTRN_QTY shipped_quantity,
				dcd.ord_qty ordered_quantity,
				NULL fulfilled_quantity,
				NULL shipping_quantity,
				'EA' shipping_quantity_uom,
				NULL tax_exempt_flag,
				NULL tax_exempt_number,
				NULL tax_exempt_reason_code,
				dcd.rtl_wh_cd ship_from_org_id,
				(SELECT dcc.ship_to_cust_acct_cd
				   FROM ds_cpo_config_v dcc
				   WHERE dcc.cpo_ord_num = dcd.cpo_ord_num
				   AND dcc.ds_cpo_config_pk = dcd.ds_cpo_config_pk
				   AND dcc.glbl_cmpy_cd = 'ADB'
				   AND dcc.ezcancelflag = '0') ship_to_org_id,
				(SELECT dcc.bill_to_cust_acct_cd
				   FROM ds_cpo_config_v dcc
				   WHERE dcc.cpo_ord_num = dcd.cpo_ord_num
				   AND dcc.ds_cpo_config_pk = dcd.ds_cpo_config_pk
				   AND dcc.glbl_cmpy_cd = 'ADB'
				   AND dcc.ezcancelflag = '0') invoice_to_org_id,
				NULL deliver_to_org_id,
				(SELECT ctac_psn_pk
				   FROM ds_cpo_ctac_psn dccp
				   WHERE dccp.cpo_ord_num = dcd.cpo_ord_num
				     AND dccp.ctac_psn_tp_cd = 'SHIPTO'
					 AND dccp.ezcancelflag = '0'
					 AND dccp.glbl_cmpy_cd = 'ADB'
					 AND rownum=1) ship_to_contact_id,
				(SELECT ctac_psn_pk
				   FROM ds_cpo_ctac_psn dccp
				   WHERE dccp.cpo_ord_num = dcd.cpo_ord_num
				     AND dccp.ctac_psn_tp_cd = 'DELIV_INSTALL'
					 AND dccp.ezcancelflag = '0'
					 AND dccp.glbl_cmpy_cd = 'ADB'
					 AND rownum=1) deliver_to_contact_id,
				(SELECT ctac_psn_pk
				   FROM ds_cpo_ctac_psn dccp
				   WHERE dccp.cpo_ord_num = dcd.cpo_ord_num
				     AND dccp.ctac_psn_tp_cd = 'BILLTO'
				     AND dccp.ezcancelflag = '0'
					 AND dccp.glbl_cmpy_cd = 'ADB'
					 AND rownum=1) invoice_to_contact_id,
				NULL intmed_ship_to_org_id,
				NULL intmed_ship_to_contact_id,
				dcd.rtl_wh_cd sold_from_org_id,
				(SELECT c.sell_to_cust_cd 
				 FROM cpo c
				 WHERE c.cpo_ord_num = dcd.cpo_ord_num
				 AND c.glbl_cmpy_cd = 'ADB'
				 AND c.ezcancelflag = '0') sold_to_org_id,
				dcd.cust_iss_po_num cust_po_number,
				dcd.ser_num cust_model_serial_number,
				dcd.mdse_cd inventory_item_id,
				dcd.prc_catg_cd price_list_id,
				CAST (TO_TIMESTAMP(dcd.prc_base_dt,'RRRRMMDD') AS DATE) pricing_date,
				1 shipment_number,
				dcd.ent_func_net_unit_prc_amt unit_selling_price,
				dcd.func_prc_list_prc_amt unit_list_price,
				NULL attribute4,
				NULL attribute17,
				CAST (TO_TIMESTAMP(dcd.ezintime,'RRRRMMDDHH24MISSFF3') AS DATE) creation_date,
				dcd.ezinuserid created_by,
				CAST (TO_TIMESTAMP(dcd.ezuptime,'RRRRMMDDHH24MISSFF3') AS DATE) last_update_date,
				dcd.ezupuserid last_updated_by,
				dcd.ezupuserid last_update_login,
				(SELECT mdse_item_tp_nm
				   FROM mdse_item_tp mit
				  WHERE 1 = 1
					AND dmi.mdse_item_tp_cd = mit.mdse_item_tp_cd
					AND mit.glbl_cmpy_cd = 'ADB'
					AND mit.ezcancelflag = '0') item_type_code,
				NULL option_number,
				NULL option_flag,
				'RETURN' line_category_code,
				--sp.actl_ship_dt actual_shipment_date,
				NULL customer_trx_line_id,
				'ORDER' return_context,
				NULL return_attribute1,
				NULL return_attribute2,
				NULL component_number,
				dcd.cpo_dtl_canc_flg cancelled_flag,
				dcd.open_flg open_flag,
				dosv.ord_book_flg booked_flag,
				(SELECT dc.sls_rep_psn_num
				 FROM cpo_v dc
				 WHERE dc.cpo_ord_num = dcd.cpo_ord_num
				 AND dc.glbl_cmpy_cd = 'ADB'
				 AND dc.ezcancelflag = '0') salesrep_id,
				NULL return_reason_code,
				NULL order_source_id,
				dcd.drop_ship_flg drop_ship_flag,
				NULL shipping_instructions,
				NULL service_number,
				dosv.ord_line_sts_nm flow_status_code,
				dcd.rtl_swh_cd subinventory,
				NULL unit_cost,
				NULL line_type,
				(SELECT rw.rtl_wh_nm
				   FROM rtl_wh rw
				  WHERE rw.rtl_wh_cd = dcd.rtl_wh_cd
					AND rw.glbl_cmpy_cd = 'ADB'
					AND rw.ezcancelflag = '0')	warehouse,
				dcd.rtl_wh_cd warehouse_code,
				dcd.mdse_cd item_name,
				dcd.mdse_nm item_description,
				dmi.coa_mdse_tp_cd merch_type_code,
				NULL, --dcd.coa_prod_cd product_code,
				/*(SELECT mkt_mdl_nm
				  FROM mkt_mdl mm
				  WHERE mm.mkt_mdl_cd = dmi.mkt_mdl_cd
				  AND mm.glbl_cmpy_cd = 'ADB'
				  AND mm.ezcancelflag = '0') equip_model,*/
				(SELECT t_mdl_nm
				   FROM ds_cpo_config_v dcc
				   WHERE dcc.ds_cpo_config_pk = dcd.ds_cpo_config_pk
				   AND dcc.glbl_cmpy_cd = 'ADB'
				   AND dcc.ezcancelflag = '0') equip_model,
				/*dcd.ser_num serial_number,
				smm.svc_mach_mstr_pk instance_id,
				CAST (TO_TIMESTAMP(smm.istl_dt,'RRRRMMDD') AS DATE) install_date ,
				(SELECT smms.svc_mach_mstr_sts_nm
				   FROM svc_mach_mstr_sts smms
				  WHERE smms.svc_mach_mstr_sts_cd = smm.svc_mach_mstr_sts_cd
					AND smms.glbl_cmpy_cd = 'ADB'
					AND smms.ezcancelflag = '0') instance_status,*/
				NULL lot_number,
				NULL servicing_dealer_name, 
				NULL servicing_dealer_code,
				NULL wholesale_price,
				NULL warehouse_receipt_date,
				dcd.ds_contr_num contract_number,
				NULL contract_maint_amount,
				NULL contract_ship_to,
				NULL prior_source_line_id ,
				NULL sold_order_number ,
				NULL sold_order_header_id ,
				NULL sold_order_line_number ,
				NULL sold_line_id ,
				NULL sold_order_type ,
				NULL sold_to_price,
				NULL sold_quantity, 
				NULL sold_customer_name ,
				NULL sold_account_number ,
				NULL sold_order_ship_from_org_id ,
				NULL sold_order_ship_from_warehouse ,
				NULL sold_order_shipping_inst,
				NULL return_subinventory,
				(SELECT ple.prc_list_equip_prc_amt
				  FROM prc_list_equip ple
				 WHERE ple.prc_catg_cd = dcd.fl_prc_list_cd
				   AND ple.prc_qlfy_val_txt = dcd.mdse_cd
				   AND ple.glbl_cmpy_cd = 'ADB'
				   AND ple.ezcancelflag = '0') floor_price
				FROM ds_cpo_rtrn_dtl_v dcd,
					 ds_ord_sts_v dosv,
					 all_mdse_v dmi
				WHERE 1 = 1
				  AND dmi.mdse_cd = dcd.mdse_cd
				  AND dosv.cpo_ord_num = dcd.cpo_ord_num
				  AND dosv.ds_cpo_rtrn_line_num = dcd.ds_cpo_rtrn_line_num
				  AND dosv.ds_cpo_rtrn_line_sub_num = dcd.ds_cpo_rtrn_line_sub_num
				  AND dosv.ezcancelflag = '0'
				  AND dmi.ezcancelflag = '0'
				  AND EXISTS (select 'x' from
											s21_csa_extn.canon_e618_order_headers_tbl e618_hdr        
											WHERE e618_hdr.header_id = dcd.cpo_ord_num)	;
											
							
        
       /* fnd_file.put_line(fnd_file.log,  'No. of rows Inserted: '||SQL%ROWCOUNT);
        fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');*/
        
        COMMIT;
        
   /* EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_ORDER_LINES_TBL COMPUTE STATISTICS';
     
    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Kicking off IB Stage Conc Program. ');
          
  
          l_ret :=
                            FND_REQUEST.submit_request ('CANON',
                                        'CANON_E618_SO_IB_PRG',
                                        'Canon E618 SO IB Staging Program',
                                        NULL,
                                        FALSE
                                        );
                            COMMIT;

    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Finshed Kicking off IB Stage Conc Program. with Request Id:' || l_ret);
    
    
    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Kicking off Receipt Date Stage Conc Program. ');
          
  
          l_ret :=
                            FND_REQUEST.submit_request ('CANON',
                                        'CANON_E618_SO_RECEIPT_DATE_PRG',
                                        'Canon E618 SO Receipt Date Staging Program',
                                        NULL,
                                        FALSE
                                        );
                            COMMIT;

    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Finshed Kicking off Receipt Date Stage Conc Program. with Request Id:' || l_ret);
   





    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Kicking off Return Line Return Sub Inv.. Columns.. Stage Conc Program. ');
          
  
          l_ret :=
                            FND_REQUEST.submit_request ('CANON',
                                        'CANON_E618_SO_RET_SUBINV_PRG',
                                        'Canon E618 SO Return Subinv Program',
                                        NULL,
                                        FALSE
                                        );
                            COMMIT;

    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Finshed Kicking off Return Line Return Sub Inv Update Stage Conc Program. with Request Id:' || l_ret);


    


    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Updating Line Type. ');
    */
    UPDATE canon_e618_order_lines_tbl a
       SET line_type = (SELECT DECODE(b.ds_ord_line_drctn_cd, 'I', 'RETURN', 'O', 'ORDER')
                         FROM ds_ord_line_catg b
                        WHERE a.line_type_id = b.ds_ord_line_catg_cd);

    --fnd_file.put_line(fnd_file.log,  'No. of rows Updated: '||SQL%ROWCOUNT);
    --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
    COMMIT;
/*
 ------------------------------------------------------------------------------------------------------------
   	---- -New Block
  	--- Update Line Type

    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Updating Org Code and Org Name Info. ');

    UPDATE canon_e618_order_lines_tbl a
	SET (WAREHOUSE, WAREHOUSE_CODE) = 
			(select organization_name , organization_code
				from org_organization_definitions b
			   where a.ship_from_org_id = b.organization_id);



    fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Organization: '||SQL%ROWCOUNT);
    fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');


    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Updating Item Number Info. ');


    UPDATE canon_e618_order_lines_tbl a
	set (ITEM_NAME, ITEM_DESCRIPTION) = 
			(select segment1, description 
				from mtl_system_items_b b
			   where a.inventory_item_id = b.inventory_item_id
			    and  b.organization_id = 83);



    fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Item: '||SQL%ROWCOUNT);
    fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');



    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log, 'Updating Salesrep Info. ');

*/
   UPDATE canon_e618_order_headers_tbl a
	SET retail_rep = 
			(select sp.psn_first_nm ||' '||sp.psn_last_nm
				from s21_psn sp
			   where sp.psn_num = a.salesrep_id
			    and rownum = 1);
			
commit;
/*
    fnd_file.put_line(fnd_file.log,  'No. of rows Updated for JTF RS Salesreps: '||SQL%ROWCOUNT);
    fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');



    
    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Updating Inventory Category Info. ');
    
    UPDATE canon_e618_order_lines_tbl a
       SET (merch_type_code, product_code)  = 
		(SELECT mc.segment1, 
			mc.segment2
                  FROM 	mtl_item_categories 	mic,
			mtl_categories 		mc,
			mtl_category_sets	mcs
		  WHERE mic.inventory_item_id 	= a.inventory_item_id
		   AND	mic.category_id		= mc.category_id
		   AND	mc.structure_id		= mcs.structure_id
		   AND  mcs.category_set_name	= 'Inventory'
		   AND  mic.organization_id	= 83
		   );

    fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Category: Inventory: '||SQL%ROWCOUNT);
    fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');


    COMMIT;




   fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
   fnd_file.put_line(fnd_file.log,  'Updating Service Category Model Info. ');
   
    
    UPDATE canon_e618_order_lines_tbl a
       SET (equip_model)  = 
		(SELECT mc.segment1
                  FROM 	mtl_item_categories 	mic,
			mtl_categories 		mc,
			mtl_category_sets	mcs
		  WHERE mic.inventory_item_id 	= a.inventory_item_id
		   AND	mic.category_id		= mc.category_id
		   AND	mc.structure_id		= mcs.structure_id
		   AND  mcs.category_set_name	= 'Service'
		   AND  mic.organization_id	= 83
		   );


    fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Category:Service '||SQL%ROWCOUNT);
    fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
    COMMIT;

*/

        
   EXCEPTION
         WHEN OTHERS
         THEN
            v_error_msg := c_package_name||'.'||c_procedure_name||' terminated during Lines staging insert due to ' || SQLCODE || ' - ' || SQLERRM;
            o_return_status := 'E';
            o_return_message := v_error_msg;
            --fnd_file.put_line(fnd_file.log, v_error_msg);
            
    END;    
        
        update  canon_e618_order_headers_tbl a
          set contract_term = (select max(pricing_quantity) from 
                                    canon_e618_order_lines_tbl b
                                    where a.header_id = b.header_id
                                    and b.pricing_quantity_uom = 'MTH');        
    COMMIT;
	
	BEGIN
		UPDATE canon_e618_order_headers_tbl a
		  SET cusa_po_number = (SELECT DISTINCT dp.po_ord_num
								  FROM po dp, ds_open_ord doo
								 WHERE 1 = 1    
								   AND doo.po_ord_num = dp.po_ord_num
								   AND dp.po_qlfy_cd = 'DS'
								   AND doo.src_ref_or_cpo_ord_num = a.header_id
								   AND doo.ezcancelflag = '0'
								   AND dp.ezcancelflag = '0'
								   AND doo.glbl_cmpy_cd = 'ADB');
	
		UPDATE canon_e618_order_headers_tbl a
		  SET cusa_po_number = (SELECT DISTINCT dp.po_ord_num
								  FROM po dp, ds_clo_ord dco
								 WHERE 1 = 1    
								   AND dco.po_ord_num = dp.po_ord_num
								   AND dp.po_qlfy_cd = 'DS'
								   AND dco.src_ref_or_cpo_ord_num = a.header_id
								   AND dco.ezcancelflag = '0'
								   AND dp.ezcancelflag = '0'
								   AND dco.glbl_cmpy_cd = 'ADB');
		  
	EXCEPTION
		WHEN OTHERS THEN
			NULL;

	END;
 /*
    
    fnd_file.put_line(fnd_file.log, '=========================================================================');
    
   -- SO and RMA Dealer Details
    BEGIN
    
    fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    fnd_file.put_line(fnd_file.log,  'Update instance details into CANON_E618_ORDER_LINES_TBL');
    
        
        EXCEPTION
         WHEN OTHERS
         THEN
            v_error_msg := c_package_name||'.'||c_procedure_name||' terminated during Lines staging insert due to ' || SQLCODE || ' - ' || SQLERRM;
            o_return_status := 'E';
            o_return_message := v_error_msg;
            fnd_file.put_line(fnd_file.log, v_error_msg);
            
        END; 
        
    fnd_file.put_line(fnd_file.log, '=========================================================================');
    
    ------------------------------------------------------------------------------------------------------------
   	---- -New Block
  	--- Update CUSA PO
    BEGIN
    
   
 fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
 fnd_file.put_line(fnd_file.log,  'Updating CUSA PO');


  UPDATE canon_e618_order_headers_tbl a
       SET (cusa_po_number)  =  
	(SELECT  pha.segment1 cusa_po_number -- CUSA PO Number
          FROM po_headers_all pha
         WHERE     1 = 1
               AND pha.attribute9 = to_char(a.order_number)
               AND pha.attribute_category = 'DS Fields'
               AND pha.attribute9 IS NOT NULL
               AND po_header_id in (SELECT max(po_header_id) 
                                         FROM po_headers_all pha2
                                                WHERE     1 = 1
                                                AND pha2.attribute9 = to_char(a.order_number)
                                                AND pha2.attribute_category = 'DS Fields'
                                                AND pha2.attribute9 IS NOT NULL))
                WHERE EXISTS
                    (SELECT 'x' from  po_headers_all pha2
                        WHERE pha2.attribute9 = to_char(a.order_number)
                        AND pha2.attribute_category = 'DS Fields');

 fnd_file.put_line(fnd_file.log,  'No. of rows Updated for CUSA PO NUmber '||SQL%ROWCOUNT);
 fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
 

 
 fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
 fnd_file.put_line(fnd_file.log,  'Updating Floor Price Amount');
                begin
                    l_counter := 0;
                    for c1rec in c1 loop
                        l_counter := l_counter + 1;
                        update CANON_E618_ORDER_LINES_TBL 
                            set floor_price = c1rec.floor_price
                          where line_id = c1rec.line_id;
                      end loop;
                 end;
                 commit;
     
  fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Floor Price:'|| l_counter);
  fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');

 
 
*/
BEGIN
 UPDATE canon_e618_order_headers_tbl a
       SET extract_type = 'ORDERS'
 WHERE EXISTS (SELECT 'x'
                     FROM canon_e618_order_lines_tbl b
                    WHERE a.header_id = b.header_id AND b.line_type = 'ORDER');
                    
    --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Extract Type: Orders: '||SQL%ROWCOUNT);
    --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
    COMMIT;
    
    --fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    --fnd_file.put_line(fnd_file.log,  'Updating Extract Type RMA');
    
    UPDATE canon_e618_order_headers_tbl a
       SET extract_type = 'RMA'
     WHERE     extract_type IS NULL
           AND EXISTS (SELECT 'x'
                         FROM canon_e618_order_lines_tbl c
                        WHERE a.header_id = c.header_id AND c.line_type = 'RETURN');
                        
    --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Extract Type:RMA: '||SQL%ROWCOUNT);
    --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    COMMIT;    
   -- fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));    
     EXCEPTION
     WHEN OTHERS
     THEN
        v_error_msg := c_package_name||'.'||c_procedure_name||' terminated during Lines type update due to ' || SQLCODE || ' - ' || SQLERRM;
        o_return_status := 'E';
        o_return_message := v_error_msg;
        --fnd_file.put_line(fnd_file.log, v_error_msg);
        
   END; 
 /*   
    
    ------------------------------------------------------------------------------------------------------------
    
    o_return_status := v_return_status;
    o_return_message := v_return_message;
    
    COMMIT;*/


  ---- E427 Delivery Schedule Insert..

  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_E427_SOL_TBL';
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_E427_SCH_TBL';
  
  INSERT INTO canon_e618_e427_sol_tbl
							(header_id,
							 line_id,
							 serial_number,
							 ship_from_org_id,
							 delivery_number,
							 delivery_status,
							 delivery_confirm_date,
							 scheduling_status,
							 ship_method,
							 shipped_date)
	SELECT dcd.cpo_ord_num header_id,
		  (dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) line_id,
		   dcd.ser_num serial_number,
		   dcd.rtl_wh_cd ship_from_org_id,
		   so.so_num delivery_number,
		  (SELECT ss.shpg_sts_nm
		     FROM shpg_sts ss
		    WHERE ss.shpg_sts_cd = so.shpg_sts_cd
		      AND ss.glbl_cmpy_cd = 'ADB'
		      AND ss.ezcancelflag = '0') delivery_status,
		  CAST (TO_TIMESTAMP(sod.actl_dely_dt,'RRRRMMDD') AS DATE) delivery_confirm_date,
		  (SELECT sst.schd_coord_sts_nm
			 FROM schd_coord_sts sst
			WHERE sst.schd_coord_sts_cd = so.schd_coord_sts_cd
			  AND sst.glbl_cmpy_cd = 'ADB'
			  AND sst.ezcancelflag = '0') scheduling_status,
		   (SELECT csl.carr_svc_lvl_nm
			  FROM carr_svc_lvl csl,
				   shpg_svc_lvl_carr_reln slcr,
				   shpg_ord sos1
			 WHERE slcr.shpg_svc_lvl_cd = sos1.shpg_svc_lvl_cd
			   AND slcr.carr_cd = sos1.carr_cd
			   AND sos1.so_num = so.so_num
			   AND csl.carr_svc_lvl_cd = slcr.carr_svc_lvl_cd
			   AND rownum < 2) ship_method,
		   CAST (TO_TIMESTAMP(sp.actl_ship_dt,'RRRRMMDD') AS DATE) shipped_date
	FROM cpo_dtl dcd,
	     shpg_ord so,
		 shpg_pln sp,
		 shpg_ord_dtl sod
	WHERE 1 = 1
	AND dcd.cpo_ord_num = sp.trx_hdr_num
    AND dcd.cpo_dtl_line_num = sp.trx_line_num
    AND dcd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
	AND sp.so_num = so.so_num
	AND sod.shpg_pln_num = sp.shpg_pln_num
	AND (dcd.cpo_ord_num,(dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0))) in 
	                                                                                         (SELECT header_id, line_id FROM canon_e618_order_lines_tbl)
    UNION
	SELECT dcrd.cpo_ord_num header_id,
		  (dcrd.cpo_ord_num || dcrd.ds_cpo_config_pk || dcrd.ds_cpo_line_num|| nvl(dcrd.ds_cpo_line_sub_num, 0)) line_id,
		   dcrd.ser_num serial_number,
		   rd.rtl_wh_cd ship_from_org_id,
		   rh.rws_num delivery_number,
		  (SELECT rs.rws_sts_nm
		     FROM rws_sts rs
		    WHERE rs.rws_sts_cd = rh.rws_sts_cd
		      AND rs.glbl_cmpy_cd = 'ADB'
		      AND rs.ezcancelflag = '0') delivery_status,
		  CAST (TO_TIMESTAMP(rd.actl_pick_up_dt,'RRRRMMDD') AS DATE) delivery_confirm_date,
		  (SELECT sst.schd_coord_sts_nm
			 FROM schd_coord_sts sst,
			      rws_dtl rsd1
			WHERE sst.schd_coord_sts_cd = rsd1.schd_coord_sts_cd
			  AND sst.glbl_cmpy_cd = 'ADB'
			  AND sst.ezcancelflag = '0'
			  AND rownum=1) scheduling_status,
		   (SELECT csl.carr_svc_lvl_nm
			  FROM carr_svc_lvl csl,
				   shpg_svc_lvl_carr_reln slcr,
				   rws_dtl rsd
			 WHERE slcr.shpg_svc_lvl_cd = rsd.shpg_svc_lvl_cd
			   AND slcr.carr_cd = rsd.carr_cd
			   AND rsd.rws_num = rd.rws_num
			   AND rsd.rws_dtl_line_num = rd.rws_dtl_line_num
			   AND csl.carr_svc_lvl_cd = slcr.carr_svc_lvl_cd
			   AND rownum < 2) ship_method,
		   CAST (TO_TIMESTAMP(rd.actl_pick_up_dt,'RRRRMMDD') AS DATE) shipped_date
	FROM ds_cpo_rtrn_dtl dcrd,
	     rws_hdr rh,
	     rws_dtl rd
	WHERE 1 = 1
	AND dcrd.cpo_ord_num = rh.trx_ord_num
    AND dcrd.ds_cpo_rtrn_line_num = rd.trx_line_num
    AND dcrd.ds_cpo_rtrn_line_sub_num = rd.trx_line_sub_num
    AND rh.rws_num = rd.rws_num
	AND (dcrd.cpo_ord_num,(dcrd.cpo_ord_num || dcrd.ds_cpo_config_pk || dcrd.ds_cpo_line_num|| nvl(dcrd.ds_cpo_line_sub_num, 0))) in 
	                                                                                         (SELECT header_id, line_id FROM canon_e618_order_lines_tbl);
  COMMIT;
  
  UPDATE canon_e618_order_lines_tbl a
  SET actual_shipment_date = (SELECT shipped_date
							    FROM canon_e618_e427_sol_tbl b
							   WHERE a.header_id = b.header_id
							     AND a.line_id = b.line_id);
  
  EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_E427_SOL_TBL COMPUTE STATISTICS';

  
   /*INSERT INTO canon_e618_e427_sch_tbl
							(line_id,
							 scheduled_delivery_date,
							 version_number,
							 schedule_code,
							 scheduled_by,
							 creation_date,
							 last_update_date)
	SELECT 
	  (dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) line_id,
	  CAST (TO_TIMESTAMP(sos.schd_dely_dt,'RRRRMMDD') AS DATE) scheduled_delivery_date,
	  NULL version_number,
	  sos.temp_schd_rsn_cd schedule_code,
      sos.schd_coord_psn_cd scheduled_by,
	  SYSDATE,
	  SYSDATE
	FROM ds_cpo_dtl dcd,
	     shpg_ord so,
		 shpg_pln sp,
		 shpg_ord_schd sos
	WHERE 1 = 1
	AND dcd.cpo_ord_num = sp.trx_hdr_num
    AND dcd.cpo_dtl_line_num = sp.trx_line_num
    AND dcd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
	AND sp.so_num = so.so_num
	AND so.so_num = sos.so_num
	AND (dcd.cpo_ord_num,(dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0))) 
				in (SELECT header_id, line_id FROM canon_e618_e427_sol_tbl);*/
				
				
   INSERT INTO canon_e618_e427_sch_tbl
							(line_id,
							 scheduled_delivery_date,
							 version_number,
							 schedule_code,
							 scheduled_by,
							 creation_date,
							 last_update_date)
	SELECT 
	  (dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) line_id,
	  CAST (TO_TIMESTAMP(sos.schd_coord_dt,'RRRRMMDD') AS DATE) scheduled_delivery_date,
	  NULL version_number,
	  sos.temp_schd_rsn_cd schedule_code,
      sos.schd_coord_psn_nm scheduled_by,
	  SYSDATE,
	  SYSDATE
	FROM cpo_dtl dcd,
	     ds_schd_coord_v sos,
		 shpg_pln sp
	WHERE 1 = 1
	AND dcd.cpo_ord_num = sp.trx_hdr_num
    AND dcd.cpo_dtl_line_num = sp.trx_line_num
    AND dcd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
	AND sp.so_num = sos.trx_ref_num
	AND (dcd.cpo_ord_num,(dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0))) 
				in (SELECT header_id, line_id FROM canon_e618_e427_sol_tbl)
	UNION
	SELECT 
	  (dcrd.cpo_ord_num || dcrd.ds_cpo_config_pk || dcrd.ds_cpo_line_num|| nvl(dcrd.ds_cpo_line_sub_num, 0)) line_id,
	  CAST (TO_TIMESTAMP(sos.schd_coord_dt,'RRRRMMDD') AS DATE) scheduled_delivery_date,
	  NULL version_number,
	  sos.temp_schd_rsn_cd schedule_code,
      sos.schd_coord_psn_nm scheduled_by,
	  SYSDATE,
	  SYSDATE
	FROM ds_cpo_rtrn_dtl dcrd,
	     ds_schd_coord_v sos,
	     rws_hdr rh,
		 rws_dtl rd
	WHERE 1 = 1
	AND dcrd.cpo_ord_num = rh.trx_ord_num
    AND dcrd.ds_cpo_rtrn_line_num = rd.trx_line_num
    AND dcrd.ds_cpo_rtrn_line_sub_num = rd.trx_line_sub_num
    AND rh.rws_num = rd.rws_num
	AND rd.rws_num = sos.trx_ref_num
    AND (dcrd.cpo_ord_num,(dcrd.cpo_ord_num || dcrd.ds_cpo_config_pk || dcrd.ds_cpo_line_num|| nvl(dcrd.ds_cpo_line_sub_num, 0))) 
				in (SELECT header_id, line_id FROM canon_e618_e427_sol_tbl);

  COMMIT;

	UPDATE canon_e618_order_lines_tbl a
    SET schedule_ship_date = (SELECT scheduled_delivery_date
							    FROM canon_e618_e427_sch_tbl b
							   WHERE a.line_id = b.line_id);

  /*DELETE FROM CANON_E618_E427_SCH_TBL a
	WHERE VERSION_NUMBER NOT IN (SELECT MAX(VERSION_NUMBER) FROM CANON_E618_E427_SCH_TBL b
		where a.line_id = b.line_id);*/

  COMMIT;

  
 
  
  ---- Check if the Sub Requests Completed and Update the Columns
 /*   l_program_completed := 'N';
    l_running_programs := 0;
    
    for i in  1..45
     loop
       select count(*) into l_running_programs from FND_CONCURRENT_REQUESTS fcr, FND_CONCURRENT_PROGRAMS fcp
        WHERE STATUS_CODE = 'R'
        AND fcr.concurrent_program_id = fcp.concurrent_program_id
        AND fcp.concurrent_program_name = 'CANON_E618_SO_IB_PRG';
      IF l_running_programs > 0
         THEN
            dbms_lock.sleep(60);
       ELSE
          l_program_completed := 'Y';
          EXIT;
      END IF;
    END LOOP;
    IF l_program_completed = 'Y'
       THEN
	DELETE FROM CANON_E618_SO_IB_TBL a
		where rowid not in (select max(rowid) from CANON_E618_SO_IB_TBL b
					WHERE a.line_id = b.line_id);
	COMMIT;

          UPDATE canon_e618_order_lines_tbl l
             set (SERIAL_NUMBER, INSTANCE_ID, INSTALL_DATE, CSI_TRANSACTION_ID, INSTANCE_STATUS, 
		  servicing_dealer_name, servicing_dealer_code, instance_party_id)
               = (SELECT SERIAL_NUMBER, INSTANCE_ID, INSTALL_DATE, CSI_TRANSACTION_ID, INSTANCE_STATUS,
			 servicing_dealer_name, servicing_dealer_code, instance_party_id
                        FROM CANON_E618_SO_IB_TBL ib
                            WHERE ib.line_id = l.line_id);
	*/						
		  instance_update;
		  
		   UPDATE canon_e618_order_lines_tbl l
             set (SERIAL_NUMBER, INSTANCE_ID, INSTALL_DATE, CSI_TRANSACTION_ID, INSTANCE_STATUS, 
		  servicing_dealer_name, servicing_dealer_code, instance_party_id)
               = (SELECT SERIAL_NUMBER, INSTANCE_ID, INSTALL_DATE, CSI_TRANSACTION_ID, INSTANCE_STATUS,
			 servicing_dealer_name, servicing_dealer_code, instance_party_id
                        FROM CANON_E618_SO_IB_TBL ib
                            WHERE ib.line_id = l.line_id);
		  
		  /*UPDATE canon_e618_order_lines_tbl l
             SET (SERIAL_NUMBER, INSTANCE_ID, INSTALL_DATE, INSTANCE_STATUS)
               = (SELECT smm.ser_num,
						 smm.svc_mach_mstr_pk instance_id,
						 CAST (TO_TIMESTAMP(smm.istl_dt,'RRRRMMDD') AS DATE) install_date ,
						(SELECT smms.svc_mach_mstr_sts_nm
						   FROM svc_mach_mstr_sts smms
						  WHERE smms.svc_mach_mstr_sts_cd = smm.svc_mach_mstr_sts_cd
							AND smms.glbl_cmpy_cd = 'ADB'
							AND smms.ezcancelflag = '0') instance_status
			   FROM svc_mach_mstr smm,
			        ds_cpo_dtl dcd
			   WHERE 1 = 1 
			   AND dcd.cpo_ord_num = l.header_id
			   AND (dcd.ds_ord_posn_num||'.'||dcd.ds_cpo_line_num || nvl2(dcd.ds_cpo_line_sub_num, '.'||dcd.ds_cpo_line_sub_num, null)) = l.line_id
			   AND smm.cpo_ord_num = dcd.cpo_ord_num
			   AND smm.cpo_dtl_line_num = dcd.cpo_dtl_line_num
			   AND smm.cpo_dtl_line_sub_num = dcd.cpo_dtl_line_sub_num
					   );*/
           --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for IB Columns back to Lines Stage: '||SQL%ROWCOUNT);
           --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
           COMMIT;
/*
     ELSE
            fnd_file.put_line(fnd_file.log, 'IB Insert Program within the expected time... Update to IB did not occur..');
     END IF;


   --- Update Wholesale Amount

 	fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
 	fnd_file.put_line(fnd_file.log,  'Updating Wholesale Amount');

 	UPDATE canon_e618_order_lines_tbl a
	  SET wholesale_price = 
	(select unit_selling_price
	   from  oe_order_lines_all ool,
		 oe_transaction_types_tl ottl,
		 csi_item_instances cii,
		 oe_order_headers_all ooh
		where a.instance_id = cii.instance_id
		and   cii.last_oe_order_line_id = ool.line_id
		and   ool.header_id = ooh.header_id
		and   ooh.order_type_id = ottl.transaction_type_id
 		AND UPPER(ottl.name) = UPPER('WHOLESALE USA, CBS')
	);


  	fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Wholesale Amount '||SQL%ROWCOUNT);
  	fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');

 	COMMIT;

	

      
     ----- Receipts Update
     l_program_completed := 'N';
     l_running_programs := 0;
     for i in  1..30
      loop
       select count(*) into l_running_programs from FND_CONCURRENT_REQUESTS fcr, FND_CONCURRENT_PROGRAMS fcp
        WHERE STATUS_CODE = 'R'
        AND fcr.concurrent_program_id = fcp.concurrent_program_id
        AND fcp.concurrent_program_name = 'CANON_E618_SO_RECEIPT_DATE_PRG';
      IF l_running_programs > 0
         THEN
            dbms_lock.sleep(60);
       ELSE
          l_program_completed := 'Y';
          EXIT;
      END IF;
    END LOOP;
    IF l_program_completed = 'Y'
       THEN
	   */
	   
		  receipt_date_update;
		  
          UPDATE canon_e618_order_lines_tbl l
             set (warehouse_receipt_date)
               = (SELECT warehouse_receipt_date
                        FROM canon_e618_so_receipt_tbl rec
                            WHERE rec.line_id = l.line_id);
           --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Receipt Date Columns back to Lines Stage: '||SQL%ROWCOUNT);
           --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
           COMMIT;
    /* ELSE
            fnd_file.put_line(fnd_file.log, 'Receipt Insert Program within the expected time... Update to Receipt Date did not occur..');
     END IF;
      
    ---- Contract Update
    

     l_program_completed := 'N';
     l_running_programs := 0;
     for i in 1..30
      loop
       select count(*) into l_running_programs from FND_CONCURRENT_REQUESTS fcr, FND_CONCURRENT_PROGRAMS fcp
        WHERE STATUS_CODE = 'R'
        AND fcr.concurrent_program_id = fcp.concurrent_program_id
        AND fcp.concurrent_program_name = 'CANON_E618_SO_MAINT_CONT_PRG';
      IF l_running_programs > 0
         THEN
            dbms_lock.sleep(60);
       ELSE
          l_program_completed := 'Y';
          EXIT;
      END IF;
    END LOOP;
    IF l_program_completed = 'Y'
       THEN
       
	 COMMIT;*/
	 
	      maint_contract;
	 
		  /*DELETE FROM  canon_e618_oks_tbl a
            WHERE ROWID NOT IN (SELECT MAX(ROWID) FROM  canon_e618_oks_tbl b
					WHERE a.line_id = b.line_id);*/

          UPDATE canon_e618_order_lines_tbl l
             SET (contract_number, contract_maint_amount, sll_id, contract_ship_to )
               = (SELECT contract_number, amount, sll_id, contract_ship_to
                        FROM canon_e618_oks_tbl okc
                            WHERE okc.line_id = l.line_id);
							
			COMMIT;
			
           --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Contract Columns back to Lines Stage: '||SQL%ROWCOUNT);
           --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
           
    /* ELSE
            fnd_file.put_line(fnd_file.log, 'Contract Insert Program within the expected time... Update to Receipt Date did not occur..');
     END IF;
         
    ---- Return Original Order Info Update
    

     l_program_completed := 'N';
     l_running_programs := 0;
     for i in 1..30
      loop
       select count(*) into l_running_programs from FND_CONCURRENT_REQUESTS fcr, FND_CONCURRENT_PROGRAMS fcp
        WHERE STATUS_CODE = 'R'
        AND fcr.concurrent_program_id = fcp.concurrent_program_id
        AND fcp.concurrent_program_name = 'CANON_E618_SO_RET_ORIG_REF_PRG';
      IF l_running_programs > 0
         THEN
            dbms_lock.sleep(60);
       ELSE
          l_program_completed := 'Y';
          EXIT;
      END IF;
    END LOOP;
    IF l_program_completed = 'Y'
       THEN
       delete from  CANON_E618_RET_ORIGREF_TBL a
            where rowid not in (select max(rowid) from  CANON_E618_RET_ORIGREF_TBL b
					where a.line_id = b.line_id);*/
					
	---- Return Original Order Info Update
					
	EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_RET_ORIGREF_TBL';

	COMMIT;
		
	ret_orig_ref;

	UPDATE canon_e618_order_lines_tbl l
   SET (prior_source_line_id,
        sold_order_number,
        sold_order_header_id,
        sold_order_line_number,
        sold_line_id,
        sold_order_type,
        sold_to_price,
        sold_quantity,
        sold_customer_name,
        sold_account_number,
        sold_order_ship_from_org_id,
        sold_order_ship_from_warehouse,
        sold_order_shipping_inst) =
          (SELECT prior_source_line_id,
                  sold_order_number,
                  sold_order_header_id,
                  sold_order_line_number,
                  sold_line_id,
                  sold_order_type,
                  sold_to_price,
                  sold_quantity,
                  sold_customer_name,
                  sold_account_number,
                  sold_order_ship_from_org_id,
                  sold_order_ship_from_warehouse,
                  sold_order_shipping_inst
             FROM canon_e618_ret_origref_tbl REF
            WHERE l.line_id = REF.line_id);

           --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Return Ref Columns back to Lines Stage: '||SQL%ROWCOUNT);
           --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
          COMMIT;
    /* ELSE
	    NULL;
            --fnd_file.put_line(fnd_file.log, 'Contract Insert Program within the expected time... Update to Return Orig Order Info did not occur..');
     END IF;





---- Return Sub Inv Info Update
    

    l_program_completed := 'N';
     l_running_programs := 0;
     for i in 1..30
      loop
       select count(*) into l_running_programs from FND_CONCURRENT_REQUESTS fcr, FND_CONCURRENT_PROGRAMS fcp
        WHERE STATUS_CODE = 'R'
        AND fcr.concurrent_program_id = fcp.concurrent_program_id
        AND fcp.concurrent_program_name = 'CANON_E618_SO_RET_SUBINV_PRG';
      IF l_running_programs > 0
         THEN
            dbms_lock.sleep(60);
       ELSE
          l_program_completed := 'Y';
          EXIT;
      END IF;
    END LOOP;
    IF l_program_completed = 'Y'
       THEN*/
	
	---- Return Sub Inv Info Update
	
	ret_subinv;
	
	DELETE FROM canon_e618_ret_subinv_tbl a
	WHERE ROWID NOT IN (SELECT MAX(ROWID) 
						FROM canon_e618_ret_subinv_tbl b
						WHERE a.line_id = b.line_id);

	UPDATE canon_e618_order_lines_tbl l
	SET (return_subinventory, mtl_transaction_id)
		= (SELECT subinventory_code, mtl_trx_id
			 FROM canon_e618_ret_subinv_tbl ret
			WHERE l.line_id = ret.line_id);

           --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Ret Sub Inventory Columns back to Lines Stage: '||SQL%ROWCOUNT);
           --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
          COMMIT;
     /*ELSE
		NULL;
            --fnd_file.put_line(fnd_file.log, 'Contract Insert Program within the expected time... Update to Return Sub Inventory did not occur..');
     END IF;*/


   EXCEPTION
    WHEN OTHERS THEN
        v_error_msg := c_package_name||'.'||c_procedure_name||' terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        o_return_status := 'E';
        o_return_message := v_error_msg;    
        --fnd_file.put_line(fnd_file.log, v_error_msg);

END order_rma_extract;
    
   -----------------------------------------------------------------------------------------------------------
    PROCEDURE main (p_run_date_from     IN DATE DEFAULT '01-JAN-1900',
                    p_run_date_to       IN DATE DEFAULT '31-DEC-2199',
					p_err_msg           OUT VARCHAR2,
					p_err_code          OUT VARCHAR2
                    )
    IS
    c_procedure_name    CONSTANT VARCHAR2(240) := 'MAIN';
    v_return_status     VARCHAR2(240) := 'S';
    v_return_message    VARCHAR2(240) := 'No Error';
    
    v_run_date_from     DATE := p_run_date_from;
    v_run_date_to       DATE := p_run_date_to;    
    
    BEGIN
        ------------------------------------------------------------------------------------------------------    
       /* fnd_file.put_line(fnd_file.log, '===================================================================');
        fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
        fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
        fnd_file.put_line(fnd_file.log,  'Calling ORDER_RMA_EXTRACT');*/

        order_rma_extract(p_run_date_from   => v_run_date_from, --  IN DATE,
                          p_run_date_to     => v_run_date_to, --  IN DATE,
                          o_return_status   => v_return_status, --OUT VARCHAR2,
                          o_return_message  => v_return_message -- OUT VARCHAR2
                          );
                          
       /* fnd_file.put_line(fnd_file.log, v_return_status);
        fnd_file.put_line(fnd_file.log, v_return_message);
        fnd_file.put_line(fnd_file.log,  '+---------------------------------------------------+');
        fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));*/
		
	 p_err_msg := v_return_message;
	 p_err_code :=  v_return_status;
		
        EXCEPTION
          WHEN OTHERS THEN
            v_error_msg := c_package_name||'.'||c_procedure_name||' terminated due to ' || SQLCODE || ' - ' || SQLERRM;
            --fnd_file.put_line(fnd_file.log,  v_error_msg);
			p_err_msg := v_error_msg;
			p_err_code :=  'E';
                        
     END main;
     
     
PROCEDURE maint_contract
IS
  resource_busy EXCEPTION;
  PRAGMA EXCEPTION_INIT(resource_busy,-54);
	
  CURSOR c1 IS
	SELECT TO_CHAR(l.instance_id) instance_id, 
			TO_DATE(NVL(l.install_date, line.creation_date), 'DD-MON-RRRR') install_date,
			h.lease_terms,
			h.contract_term,
			TO_CHAR(line.ship_to_org_id) ship_to_org_id,
			l.batch_id,
			l.line_id
	FROM canon_e618_so_ib_tbl l,
		 canon_e618_order_headers_tbl h,
		 canon_e618_order_lines_tbl line
	WHERE 1 = 1
	AND l.line_id = line.line_id
	AND l.instance_id is not null 
	AND h.header_id = l.header_id
	AND merch_type_code = '10';
	
	l_contract_number       VARCHAR2(30);
	l_sll_id                NUMBER;
	l_contract_ship_to      VARCHAR2(30);
	l_amount                NUMBER;
	l_ole_id                NUMBER;
	l_method                VARCHAR2(30);
	
  BEGIN
  
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_OKS_TBL';
   
	FOR c1rec in c1 LOOP
        l_contract_number   :=      NULL;
        l_sll_id            :=      NULL;
        l_contract_ship_to  :=      NULL;
        l_amount            :=      NULL;
        l_ole_id            :=      NULL;
        l_method            :=      NULL;
		
    BEGIN
        
		SELECT contract_number, contract_ship_to, amount
		into l_contract_number, l_contract_ship_to, l_amount
		FROM (
			SELECT h.ds_contr_pk,
				   h.ds_contr_num contract_number,
				   d.ds_contr_dtl_pk,
				   h.ds_acct_num  contract_ship_to,
				   d.prnt_ds_contr_dtl_pk,
				   smm.svc_mach_mstr_pk,
				   d.ds_contr_dtl_tp_cd,
				   d.base_prc_term_deal_amt_rate,
				   d.base_prc_deal_amt amount,
				   smm.cur_loc_num,
				   smm.cur_loc_acct_num
			  FROM ds_contr h,
				   ds_contr_dtl d,
				   svc_mach_mstr smm
			 WHERE 1 = 1
				   AND h.ds_contr_pk = d.ds_contr_pk
				   AND smm.svc_mach_mstr_pk(+) = d.svc_mach_mstr_pk
				   AND smm.svc_mach_mstr_pk = c1rec.instance_id
				   AND CAST (TO_TIMESTAMP(h.contr_vrsn_eff_from_dt,'RRRRMMDD') AS DATE)  >= c1rec.install_date                
				   AND CAST (TO_TIMESTAMP(h.contr_vrsn_eff_thru_dt,'RRRRMMDD') AS DATE)  <=  add_months(c1rec.install_date, nvl(nvl(c1rec.lease_terms, c1rec.contract_term), 1200))
				   AND h.ezcancelflag = '0'
				   AND h.glbl_cmpy_cd = 'ADB'
				   AND d.ezcancelflag = '0'
				   AND d.glbl_cmpy_cd = 'ADB'
				   AND smm.ezcancelflag = '0'
				   AND smm.glbl_cmpy_cd = 'ADB');
				   
				   l_method := 'PASS_1';
		
		/*select contract_number, sll_id,contract_ship_to, amount, ole_id
        into l_contract_number, l_sll_id, l_contract_ship_to, l_amount, l_ole_id
        from
        (
            SELECT 
                    contract_number, 
                     sll.id sll_id, 
                     sto.object1_id1 contract_ship_to, 
                     amount , 
                     ole.id  ole_id --  , ole.sequence_number
                FROM    okc_rules_b sll,
                        okc_rule_groups_b grp,
                        okc_k_lines_b okl,
                        okc_k_headers_b okhb,
                        okc_k_lines_b okl_9,
                        okc_k_items oki,
                        okc_rules_b sto,
                        oks_level_elements ole
                WHERE     1 = 1
                AND okhb.id = okl.dnz_chr_id
                AND okl.lse_id = 1
                AND okl.id = okl_9.cle_id
                AND okl.id = grp.cle_id
                AND grp.id = sll.rgp_id
                AND okl_9.id = oki.cle_id
                AND oki.object1_id1 = c1rec.instance_id -- c1rec.instance_id
                AND oki.jtot_object1_code = 'OKX_CUSTPROD'
                AND okhb.start_date  > c1rec.install_date                
                AND okhb.end_date  <=  add_months(c1rec.install_date, nvl(nvl(c1rec.lease_terms, c1rec.contract_term), 1200))
                AND sto.object1_id1 = c1rec.ship_to_org_id
                AND sto.rule_information_category = 'STO'
                AND sto.rgp_id = grp.id
                AND okl_9.sts_code NOT IN ('CANCELLED')
                AND sll.rule_information_category = 'SLL'
                AND ole.rul_id = sll.id
                AND ole.sequence_number = 1
                AND ole.amount > 0
                order by ole.id)
                where rownum = 1;
                l_method := 'PASS_1';
            EXCEPTION
             WHEN NO_DATA_FOUND
                THEN
                  BEGIN
                   select contract_number, sll_id,contract_ship_to, amount, ole_id
                    into l_contract_number, l_sll_id, l_contract_ship_to, l_amount, l_ole_id
                        from
                        (
                        SELECT 
                            contract_number, 
                             sll.id sll_id, 
                             sto.object1_id1 contract_ship_to, 
                             amount , 
                             ole.id  ole_id --  , ole.sequence_number
                        FROM    okc_rules_b sll,
                                okc_rule_groups_b grp,
                                okc_k_lines_b okl,
                                okc_k_headers_b okhb,
                                okc_k_lines_b okl_9,
                                okc_k_items oki,
                                okc_rules_b sto,
                                oks_level_elements ole
                        WHERE     1 = 1
                        AND okhb.id = okl.dnz_chr_id
                        AND okl.lse_id = 1
                        AND okl.id = okl_9.cle_id
                        AND okl.id = grp.cle_id
                        AND grp.id = sll.rgp_id
                        AND okl_9.id = oki.cle_id
                        AND oki.object1_id1 = c1rec.instance_id -- c1rec.instance_id
                        AND oki.jtot_object1_code = 'OKX_CUSTPROD'
                        AND okhb.start_date  > c1rec.install_date                
                        AND okhb.end_date  <=  add_months(c1rec.install_date, nvl(nvl(c1rec.lease_terms, c1rec.contract_term), 1200))
                        AND sto.rule_information_category = 'STO'
                        AND sto.rgp_id = grp.id
                        AND okl_9.sts_code NOT IN ('CANCELLED')
                        AND sll.rule_information_category = 'SLL'
                        AND ole.rul_id = sll.id
                        AND ole.sequence_number = 1
                        AND ole.amount > 0
                        order by ole.id)
                        where rownum = 1;
                        l_method := 'PASS_2';            
                 EXCEPTION
                    WHEN NO_DATA_FOUND
                     THEN
                      BEGIN
                            select contract_number, sll_id,contract_ship_to, amount, ole_id
                                into l_contract_number, l_sll_id, l_contract_ship_to, l_amount, l_ole_id
                        from
                        (
                        SELECT 
                            contract_number, 
                             sll.id sll_id, 
                             sto.object1_id1 contract_ship_to, 
                             amount , 
                             ole.id  ole_id --  , ole.sequence_number
                        FROM    okc_rules_b sll,
                                okc_rule_groups_b grp,
                                okc_k_lines_b okl,
                                okc_k_headers_b okhb,
                                okc_k_lines_b okl_9,
                                okc_k_items oki,
                                okc_rules_b sto,
                                oks_level_elements ole
                        WHERE     1 = 1
                        AND okhb.id = okl.dnz_chr_id
                        AND okl.lse_id = 1
                        AND okl.id = okl_9.cle_id
                        AND okl.id = grp.cle_id
                        AND grp.id = sll.rgp_id
                        AND okl_9.id = oki.cle_id
                        AND oki.object1_id1 = c1rec.instance_id -- c1rec.instance_id
                        AND oki.jtot_object1_code = 'OKX_CUSTPROD'
                        AND okhb.start_date  > c1rec.install_date                
                        AND sto.rule_information_category = 'STO'
                        AND sto.rgp_id = grp.id
                        AND okl_9.sts_code NOT IN ('CANCELLED')
                        AND sll.rule_information_category = 'SLL'
                        AND ole.rul_id = sll.id
                        AND ole.sequence_number = 1
                        AND ole.amount > 0
                        order by ole.id)
                        where rownum = 1;
                        l_method := 'PASS_3';            
                    EXCEPTION
                       WHEN NO_DATA_FOUND
                          THEN                
                                l_contract_number   :=      NULL;
                                l_sll_id            :=      0;
                                l_contract_ship_to  :=      NULL;
                                l_amount            :=      NULL;
                                l_method := 'NOT_FOUND';            
                    END;
                  END;*/
		EXCEPTION
	    WHEN NO_DATA_FOUND
		  THEN                
				l_contract_number   :=      NULL;
				l_sll_id            :=      0;
				l_contract_ship_to  :=      NULL;
				l_amount            :=      NULL;
				l_method := 'NOT_FOUND';            

		END;
         
		BEGIN
            INSERT INTO canon_e618_oks_tbl
                (line_id,
                 contract_number, 
                 sll_id, 
                 contract_ship_to, 
                 amount, 
                 ole_id,
                 batch_id,
                 logic_method)
			VALUES
				(c1rec.line_id,
				l_contract_number, 
				l_sll_id, 
				l_contract_ship_to, 
				l_amount, 
				l_ole_id,
				c1rec.batch_id,
				l_method);
				
            COMMIT;
           EXCEPTION
              WHEN  resource_busy THEN
			  --dbms_lock.sleep(15);
              INSERT INTO CANON_E618_OKS_TBL
					(line_id,
					 contract_number, 
					 sll_id, 
					 contract_ship_to, 
					 amount, 
					 ole_id,
					 batch_id,
					 logic_method)
                 VALUES
                    (c1rec.line_id,
                    l_contract_number, 
                    l_sll_id, 
                    l_contract_ship_to, 
                    l_amount, 
                    l_ole_id,
                    c1rec.batch_id,
                    l_method);
                COMMIT;
				
		END;
		
	END LOOP;
        EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_OKS_TBL COMPUTE STATISTICS';
 END;
 
PROCEDURE instance_update
    IS
    l_ret                   NUMBER;
    l_batch_count           NUMBER;
    l_merch_type_exists     VARCHAR2(15);
     BEGIN
        --fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
        --fnd_file.put_line(fnd_file.log,  'Updating Instance Info');
        EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_SO_IB_TBL';

        -- SO and RMA Lines Instance Details
        INSERT INTO CANON_E618_SO_IB_TBL A
              (SERIAL_NUMBER,
                INSTANCE_ID,
                INSTALL_DATE,
                CSI_TRANSACTION_ID,
                INSTANCE_STATUS,
                LINE_ID,
                HEADER_ID,
                LOT_NUMBER,
                CREATION_DATE)              
         SELECT smm.ser_num,
			   smm.svc_mach_mstr_pk instance_id,
			   CAST (TO_TIMESTAMP(smm.istl_dt,'RRRRMMDD') AS DATE) install_date ,
			   NULL csi_transaction_id,
			  (SELECT smms.svc_mach_mstr_sts_nm
				 FROM svc_mach_mstr_sts smms
				WHERE smms.svc_mach_mstr_sts_cd = smm.svc_mach_mstr_sts_cd
				  AND smms.glbl_cmpy_cd = 'ADB'
				  AND smms.ezcancelflag = '0') instance_status,
				oe_line.line_id,
                oe_line.header_id,
				(select substr(otm.mdse_cd, 9, 2) 
				from ord_take_mdse otm
                where substr(oe_line.ordered_item,1,8) = otm.ord_take_mdse_cd) lot_number,
                SYSDATE
		   FROM svc_mach_mstr smm,
				canon_e618_order_lines_tbl oe_line,
				cpo_dtl dcd
		   WHERE 1 = 1 
		   AND smm.cpo_ord_num = oe_line.header_id
		   --AND smm.cpo_dtl_line_num ||'.'||smm.cpo_dtl_line_sub_num = oe_line.line_number
		   AND smm.svc_mach_mstr_pk = (SELECT MAX(smm.svc_mach_mstr_pk)
									     FROM svc_mach_mstr smm1
										WHERE smm1.cpo_ord_num = smm.cpo_ord_num
                                          AND smm1.cpo_dtl_line_num = smm.cpo_dtl_line_num
										  AND smm1.cpo_dtl_line_sub_num = smm.cpo_dtl_line_sub_num)
		   AND (dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) = oe_line.line_id
		   AND smm.cpo_ord_num = dcd.cpo_ord_num
		   AND smm.cpo_dtl_line_num = dcd.cpo_dtl_line_num
		   AND smm.cpo_dtl_line_sub_num = dcd.cpo_dtl_line_sub_num;
        
            --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Instance Id using Last OE Order Line Id: '||SQL%ROWCOUNT);
            --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
            COMMIT;
      
     INSERT INTO CANON_E618_SO_IB_TBL A
              (SERIAL_NUMBER,
                INSTANCE_ID,
                INSTALL_DATE,
                CSI_TRANSACTION_ID,
                INSTANCE_STATUS,
                LINE_ID,
                HEADER_ID,
                LOT_NUMBER,
                CREATION_DATE)              
           SELECT smm.ser_num,
			   smm.svc_mach_mstr_pk instance_id,
			   CAST (TO_TIMESTAMP(smm.istl_dt,'RRRRMMDD') AS DATE) install_date ,
			   NULL csi_transaction_id,
			  (SELECT smms.svc_mach_mstr_sts_nm
				 FROM svc_mach_mstr_sts smms
				WHERE smms.svc_mach_mstr_sts_cd = smm.svc_mach_mstr_sts_cd
				  AND smms.glbl_cmpy_cd = 'ADB'
				  AND smms.ezcancelflag = '0') instance_status,
				oe_line.line_id,
                oe_line.header_id,
				(select substr(otm.mdse_cd, 9, 2) 
				from ord_take_mdse otm
                where substr(oe_line.ordered_item,1,8) = otm.ord_take_mdse_cd) lot_number,
                SYSDATE
		   FROM svc_mach_mstr smm,
				canon_e618_order_lines_tbl oe_line,
				ds_cpo_rtrn_dtl_v dcrd
		   WHERE 1 = 1 
		   AND smm.rma_num = oe_line.header_id
		   --AND smm.cpo_dtl_line_num ||'.'||smm.cpo_dtl_line_sub_num = oe_line.line_number
		   AND smm.svc_mach_mstr_pk = (SELECT MAX(smm.svc_mach_mstr_pk)
									     FROM svc_mach_mstr smm1
										WHERE smm1.rma_num = smm.rma_num
                                          AND smm1.rma_line_num = smm.rma_line_num
										  AND smm1.rma_line_sub_num = smm.rma_line_sub_num)
		   AND (dcrd.cpo_ord_num || dcrd.ds_cpo_config_pk || dcrd.ds_cpo_line_num|| nvl(dcrd.ds_cpo_line_sub_num, 0)) = oe_line.line_id
		   AND smm.rma_num = dcrd.cpo_ord_num
		   AND smm.rma_line_num = dcrd.ds_cpo_rtrn_line_num
		   AND smm.rma_line_sub_num = dcrd.ds_cpo_rtrn_line_sub_num;
			
			
			
       
            --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Instance Id using Last RMA Line Id: '||SQL%ROWCOUNT);
            --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
   
            COMMIT;


 	   /*for i in 1..8
              loop
                BEGIN
                    SELECT 'Yes' into l_merch_type_exists
                       FROM canon_e618_order_lines_tbl
                     WHERE MERCH_TYPE_CODE = '10'
                     AND ROWNUM = 1;
                     EXIT;
                 EXCEPTION
                    WHEN NO_DATA_FOUND
                        THEN
                            DBMS_LOCK.SLEEP(60);
                 END;
             end loop;
                     
           
            
            INSERT INTO CANON_E618_SO_IB_TBL
              (SERIAL_NUMBER,
                INSTANCE_ID,
                INSTALL_DATE,
                CSI_TRANSACTION_ID,
                INSTANCE_STATUS,
                LINE_ID,
                HEADER_ID,
                LOT_NUMBER,
                CREATION_DATE)              
            SELECT CII.SERIAL_NUMBER,
                  CII.INSTANCE_ID,
                  CT.TRANSACTION_DATE,
                  CT.TRANSACTION_ID,
                  CIS.NAME,
                  a.line_id,
                  a.header_id,
                  CII.LOT_NUMBER,
                  SYSDATE
             FROM CSI_ITEM_INSTANCES_H CII_H,
                  CSI_TRANSACTIONS CT,
                  CSI_ITEM_INSTANCES CII,
                  CSI_INSTANCE_STATUSES CIS,
                  canon_e618_order_lines_tbl a
            WHERE     1 = 1
                  AND CII_H.TRANSACTION_ID = CT.TRANSACTION_ID
                  AND CT.TRANSACTION_TYPE_ID IN (51, 53)
                  AND CT.SOURCE_LINE_REF_ID = A.LINE_ID
                  AND CT.SOURCE_HEADER_REF_ID = A.HEADER_ID
                  AND CIS.INSTANCE_STATUS_ID = CII.INSTANCE_STATUS_ID
                  AND CII.INSTANCE_ID = CII_H.INSTANCE_ID
                  AND  a.merch_type_code = '10'
                  AND not exists (select 'x' from CANON_E618_SO_IB_TBL b
                                    WHERE a.line_id = b.line_id
                                    AND   a.header_id = b.header_id);



           --     fnd_file.put_line(fnd_file.log,  'No. of rows Inserted for Instance Info... using CSI_TRANSACTIONS '||SQL%ROWCOUNT);
           --     fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
    
                COMMIT;*/
          /*      
                select round(count(*)/5) into l_batch_count
                    from   CANON_E618_SO_IB_TBL;
        
                for i in 1..5
                    loop
                    update CANON_E618_SO_IB_TBL
                        set batch_id = i
                        where batch_id is null
                        and rownum <= l_batch_count;
                end loop;
         
                update CANON_E618_SO_IB_TBL
                set batch_id = 5
                where batch_id is null;
             
                commit;
                
                
                
                EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_SO_IB_TBL COMPUTE STATISTICS';
        
		EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_OKS_TBL';

                
                      for i in 1..5
                            loop  
                            l_ret :=
                            FND_REQUEST.submit_request ('CANON',
                                        'CANON_E618_SO_MAINT_CONT_PRG',
                                        'Canon E618 Maintenance Contract Staging Program',
                                        NULL,
                                        FALSE,
                                        i);
                            COMMIT;
                        end loop; 


		select round(count(*)/5) into l_batch_count
                    from   CANON_E618_SO_IB_TBL a, canon_e618_order_lines_tbl b
			where a.line_id = b.line_id
			and b.line_category_code = 'RETURN';
      
                for i in 1..5
                    loop
                    update CANON_E618_SO_IB_TBL
                        set ret_batch_id = i
                        where ret_batch_id is null
			and line_id in (select line_id from canon_e618_order_lines_tbl where line_category_code = 'RETURN')
                        and rownum <= l_batch_count;
                end loop;
         
                update CANON_E618_SO_IB_TBL
                set ret_batch_id = 5
                where ret_batch_id is null
	       and line_id in (select line_id from canon_e618_order_lines_tbl where line_category_code = 'RETURN');
        
             
                commit;
		
		EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_RET_ORIGREF_TBL';

		COMMIT;
		
		ret_orig_ref;
        
		     for i in 1..5
                            loop  
    			fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
			    fnd_file.put_line(fnd_file.log,  'Kicking off Return Line Orig Ref Columns.. Stage Conc Program. ');  
          		 l_ret :=
                            FND_REQUEST.submit_request ('CANON',
                                        'CANON_E618_SO_RET_ORIG_REF_PRG',
                                        'Canon E618 SO Return Orig Reference',
                                        NULL,
                                        FALSE,
					i
                                        );
                            COMMIT;
		  	end loop;

    			fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    			fnd_file.put_line(fnd_file.log,  'Finshed Kicking off Return Line Orig Ref Stage Conc Program. with Request Id:' || l_ret);
*/

    		/*UPDATE CANON_E618_SO_IB_TBL a
       		SET (servicing_dealer_name, servicing_dealer_code, instance_party_id)  =   
     		( SELECT  pv.vendor_name,
                          pv.segment1, 
			  cip.instance_party_id
          	FROM 	csi_i_parties cip,
               		jtf_rs_resource_extns jtf,
               		po_vendor_contacts poc,
               		po_vendor_sites_all sites,
               		po_vendors pv
         	WHERE     1 = 1
               AND a.instance_id = cip.instance_id
               AND cip.party_source_table = 'PO_VENDORS'
               AND cip.relationship_type_code = 'TECHNICAL'
               AND NVL (cip.active_end_date, a.creation_date) >= a.creation_date + 1
               AND cip.party_id = jtf.source_id
               AND jtf.CATEGORY = 'SUPPLIER_CONTACT'
               AND jtf.source_id = poc.vendor_contact_id
               AND poc.vendor_site_id = sites.vendor_site_id
               AND NVL (sites.inactive_date, SYSDATE) >= a.creation_date
               AND sites.vendor_id = pv.vendor_id
               AND cip.instance_party_id IN  (select MIN(instance_party_id) 
						from csi_i_parties cip2 where
							cip2.instance_id = cip.instance_id
						    	AND cip2.party_source_table = 'PO_VENDORS'
               						AND cip2.relationship_type_code = 'TECHNICAL')
	       );
	*/
	
BEGIN
	UPDATE 	canon_e618_so_ib_tbl a
	SET (servicing_dealer_name, servicing_dealer_code, instance_party_id)	= 
		   (SELECT  pv.prnt_vnd_nm, pv.prnt_vnd_pk, smm.req_tech_cd
				   FROM vnd vd,
						prnt_vnd pv,
                        vnd_tp_reln vtr,
                        s21_psn sp,
                        ctac_psn cp,
                        ds_ctac_psn_reln dcpr,
                        svc_mach_mstr smm
                  WHERE     1 = 1
                        and sp.psn_tp_cd = '2'                                 --(3rd Party Rep)
                        and sp.ctac_psn_pk = cp.ctac_psn_pk
                        and dcpr.ctac_psn_pk = cp.ctac_psn_pk
                        and vd.loc_num = dcpr.loc_num
                        and vd.vnd_cd = vtr.vnd_cd
                        and vtr.vnd_tp_cd = '15'                              --(Service Dealer)
                        and smm.req_tech_cd = sp.psn_cd
                        and smm.glbl_cmpy_cd = vd.glbl_cmpy_cd
                        and smm.ezcancelflag = vd.ezcancelflag
                        and smm.glbl_cmpy_cd = pv.glbl_cmpy_cd
                        and smm.ezcancelflag = pv.ezcancelflag
                        and smm.glbl_cmpy_cd = vtr.glbl_cmpy_cd
                        and smm.ezcancelflag = vtr.ezcancelflag
                        and smm.glbl_cmpy_cd = sp.glbl_cmpy_cd
                        and smm.ezcancelflag = sp.ezcancelflag
                        and smm.glbl_cmpy_cd = cp.glbl_cmpy_cd
                        and smm.ezcancelflag = cp.ezcancelflag
                        and smm.glbl_cmpy_cd = dcpr.glbl_cmpy_cd
                        and smm.ezcancelflag = dcpr.ezcancelflag
                        and smm.glbl_cmpy_cd = 'ADB'
                        and smm.ezcancelflag = '0'
                        and smm.svc_mach_mstr_pk = a.instance_id);
             
EXCEPTION
WHEN OTHERS THEN
 NULL;
END;
 --fnd_file.put_line(fnd_file.log,  'No. of rows Updated for Sercicing Dealer '||SQL%ROWCOUNT);
 --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');

 COMMIT;


      
END instance_update; 
  
PROCEDURE receipt_date_update
 
IS
	l_ret           NUMBER;
	l_batch_count   NUMBER;
	l_trx_date      DATE;
	l_trx_cd VARCHAR2(10);
	l_trx_rsn_cd VARCHAR2(10);
	
    CURSOR cur_receipt_date
    IS
    SELECT header_id, line_number, line_id
      FROM canon_e618_order_lines_tbl
     WHERE line_type = 'RETURN';    
	 
     l_count NUMBER := 0;
     l_line_type_exists VARCHAR2(30);
	 
 BEGIN
       -- fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
       -- fnd_file.put_line(fnd_file.log,  'Updating Instance Info');
        
	EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_SO_RECEIPT_TBL';
      
      /*for i in 1..5
        loop
        BEGIN
            SELECT 'Yes' into l_line_type_exists
               FROM canon_e618_order_lines_tbl
              WHERE line_type = 'RETURN'
              AND ROWNUM = 1;
             EXIT;
         EXCEPTION
             WHEN NO_DATA_FOUND
                THEN
                    DBMS_LOCK.SLEEP(30);
          END;
        end loop;*/
        
	FOR rec_receipt_date IN cur_receipt_date LOOP       
		BEGIN  
			
			SELECT a.trx_cd, a.trx_rsn_cd
			INTO l_trx_cd, l_trx_rsn_cd
			FROM cpo dc, 
			     ds_cpo_rtrn_dtl dcd,
				 ds_ord_line_proc_dfn l, 
				 aje_acct_bat a
		   WHERE l.aje_acct_bat_cd = a.aje_acct_bat_cd
		     AND dc.cpo_ord_num = dcd.cpo_ord_num
			 AND dc.ds_ord_tp_cd = l.ds_ord_tp_cd
			 AND dcd.ds_ord_line_catg_cd = l.ds_ord_line_catg_cd
		     AND dc.cpo_ord_num = rec_receipt_date.header_id
		     AND (dcd.ds_cpo_rtrn_line_num ||'.'|| dcd.ds_cpo_rtrn_line_sub_num) = rec_receipt_date.line_number;
			
			dbms_output.put_line('l_trx_cd: '||l_trx_cd);
			dbms_output.put_line('l_trx_rsn_cd: '||l_trx_rsn_cd);
			
			SELECT CAST (TO_TIMESTAMP(invty_trx_dt,'RRRRMMDD') AS DATE)
			INTO l_trx_date
			FROM invty_trx
			WHERE rma_num = rec_receipt_date.header_id
			  AND (rma_line_num||'.'||rma_line_sub_num) = rec_receipt_date.line_number
			  AND trx_cd = l_trx_cd
			  AND trx_rsn_cd = l_trx_rsn_cd
			  AND ROWNUM = 1;
			  
			l_count := l_count + 1;
			dbms_output.put_line('l_trx_date: '||l_trx_date);
			dbms_output.put_line('l_count: '||l_count);
			
		EXCEPTION
			WHEN OTHERS
			THEN
			dbms_output.put_line('Error: '|| sqlerrm);
			l_trx_date := NULL;
		END;

		INSERT INTO canon_e618_so_receipt_tbl
					(line_id,
					 warehouse_receipt_date,
					 creation_date)
		VALUES
			 (rec_receipt_date.line_id,
			  l_trx_date,
			  SYSDATE);    
	  
		COMMIT;          
	END LOOP;  
	
	EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_SO_RECEIPT_TBL COMPUTE STATISTICS';
	
         --fnd_file.put_line(fnd_file.log,  'No. of rows Inserted for Receipt Date Info... '||l_count);
         --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
         
END receipt_date_update; 

PROCEDURE mv_stage 
    IS
     c_procedure_name VARCHAR2(50) := 'MV_STAGE';
      BEGIN
        --====================================================================================================
        BEGIN
           -- fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));    
           -- fnd_file.put_line(fnd_file.log,  'Create Markview FND ATTACHED DOCUMENT EXTRACT into CANON_E618_FAD_TBL');
    
            EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_FAD_TBL';
    
            INSERT INTO canon_e618_fad_tbl (document_id, pk1_value, entity_name)
                SELECT att_data_pk, att_data_grp_txt, att_data_key_txt
                        FROM att_data
                            WHERE  att_data_grp_txt IN (SELECT TO_CHAR(header_Id)
                                    FROM canon_e618_order_headers_tbl)
                        AND att_data_key_txt = 'Order Number'
						AND att_doc_tp_cd = '70' ;
            
                    --fnd_file.put_line(fnd_file.log,  'No. of rows Inserted into CANON_E618_FAD_TBL: '||SQL%ROWCOUNT);
                    --fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
      
                COMMIT;
                      
            EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_FAD_TBL COMPUTE STATISTICS';        
        EXCEPTION
         WHEN OTHERS
         THEN
            v_error_msg := c_package_name||'.'||c_procedure_name||' terminated during  insert into CANON_E618_FAD_TBL due to ' || SQLCODE || ' - ' || SQLERRM;
            --fnd_file.put_line(fnd_file.log, v_error_msg);            
        END; 
        
    --fnd_file.put_line(fnd_file.log, '=========================================================================');
    
    --====================================================================================================
    -- Insert Markview Details
    BEGIN
    
            --fnd_file.put_line(fnd_file.log, 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));    
            --fnd_file.put_line(fnd_file.log,  'Create Markview FND ATTACHED DOCUMENT EXTRACT into CANON_E618_ORDER_LINES_TBL');
    
            EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_MV_TBL';
     
            INSERT INTO canon_e618_mv_tbl (header_id,
                                         mv_description,
                                         file_name,
                                         document_id,
                                         document_type_name,
                                         creation_timestamp)
            SELECT fad.pk1_value header_id,
              ad.att_data_desc_txt,
              ad.att_data_nm,
              ad.att_data_pk,
              ad.att_doc_tp_cd,
              CAST (TO_TIMESTAMP (ad.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE) date_scanned
         FROM att_data ad,
              canon_e618_fad_tbl fad
        WHERE     1 = 1
		  AND ad.att_data_key_txt = 'Order Number'
		  AND fad.document_id = ad.att_data_pk;
            
        --fnd_file.put_line(fnd_file.log,  'No. of rows Inserted into CANON_E618_MV_TBL: '||SQL%ROWCOUNT);
       -- fnd_file.put_line(fnd_file.log,  '+----------------------------------------------------------------+');
        
        COMMIT;
        
        EXCEPTION
         WHEN OTHERS
         THEN
            v_error_msg := c_package_name||'.'||c_procedure_name||
                        ' terminated during  insert into CANON_E618_MV_TBL due to ' || SQLCODE || ' - ' || SQLERRM;
            --fnd_file.put_line(fnd_file.log, v_error_msg);
            
        END; 
        
            --fnd_file.put_line(fnd_file.log, '=========================================================================');
    
        
            --fnd_file.put_line(fnd_file.log, '=========================================================================');  
     END; 

	 
PROCEDURE ret_subinv
  IS
   CURSOR c1 IS 
	SELECT header_id, line_number, line_id 
	  FROM canon_e618_order_lines_tbl
	 WHERE line_category_code = 'RETURN';
		
	l_subinventory_code VARCHAR2(100);
	l_transaction_id NUMBER;
		
BEGIN
	
	EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_RET_SUBINV_TBL';

	FOR c1rec in c1 LOOP
		BEGIN
			l_transaction_id := NULL;
			l_subinventory_code := NULL;

			SELECT rs.rtl_swh_cd, it.invty_trx_pk
			into l_subinventory_code, l_transaction_id
			FROM invty_trx it, rtl_swh rs
			WHERE it.invty_loc_cd = rs.invty_loc_cd
			  AND rma_num = c1rec.header_id
			  AND (rma_line_num||'.'||rma_line_sub_num) = c1rec.line_number
			  AND trx_cd = '010'
			  AND trx_rsn_cd = 'A7'
			  AND ROWNUM = 1;
			  
			/*select subinventory_code, transaction_id
				   into l_subinventory_code, l_transaction_id
			   from mtl_material_transactions
				where transaction_type_id = 15
				and trx_source_line_id = c1rec.line_id
			  and rownum = 1;*/
		EXCEPTION
		  WHEN OTHERS THEN 
			 NULL;
		END;
		
		INSERT INTO canon_e618_ret_subinv_tbl
			(line_id,
			 subinventory_code,
			 mtl_trx_id)
		  VALUES
			(c1rec.line_id,
			 l_subinventory_code,
			 l_transaction_id);
			 
		COMMIT;
	END LOOP;
			 
END ret_subinv;


PROCEDURE ret_orig_ref
IS
	
  CURSOR C1
  IS
  SELECT l.header_id, l.line_id
  FROM canon_e618_order_lines_tbl l,
	   canon_e618_so_ib_tbl ib
  WHERE l.line_category_code = 'RETURN'
  AND ib.instance_id IS NOT NULL
  AND ib.line_id = l.line_id;
	
/*	
	  CURSOR C1 IS
	    SELECT ib.LINE_ID, 
		   l.HEADER_ID, 
		   ib.INSTANCE_ID
    	    FROM CANON_E618_ORDER_LINES_TBL l,
		 CANON_E618_SO_IB_TBL ib
    	    WHERE l.line_category_code = 'RETURN'
    	    AND ib.INSTANCE_ID 		IS NOT NULL
	    AND ib.line_id 		= 	l.line_id
	    AND ib.ret_batch_id		=	p_batch_id; */

  	 l_prior_source_line_id VARCHAR2(100);
	 l_message VARCHAR2(4000);
	 l_order_number			VARCHAR2(30);
	 l_header_id			NUMBER;
	 l_line_number			VARCHAR2(15);
	 l_order_type			VARCHAR2(250);
	 l_unit_selling_price		NUMBER;
	 l_ordered_quantity		NUMBER;
	 l_party_name			VARCHAR2(250);
	 l_account_number		VARCHAR2(30);
	 l_ship_from_org_id		NUMBER;
	 l_ship_from_warehouse_name	VARCHAR2(250);
	 l_shipping_instructions	VARCHAR2(4000);

	BEGIN
	
	FOR c1rec in c1 LOOP
		BEGIN
		  
		   l_prior_source_line_id := NULL;
		   l_message 		:= NULL;
		   l_order_number	:=	NULL;
		   l_header_id		:=	NULL;
		   l_line_number	:=	NULL;
		   l_order_type		:=	NULL;
		   l_unit_selling_price	:=	NULL;
		   l_ordered_quantity	:=	NULL;
		   l_party_name		:=	NULL;
		   l_account_number	:=	NULL;
		   l_ship_from_org_id	:=	NULL;
		   l_ship_from_warehouse_name	:=	NULL;
		   l_shipping_instructions	:=	NULL;
		   
			SELECT (dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) line_id
			  INTO l_prior_source_line_id
			  FROM ds_cpo_config dcc,
				   ds_cpo_rtrn_dtl_v dcrd,
				   svc_config_mstr scm,
				   svc_mach_mstr smm,
				   cpo_dtl dcd
			 WHERE     dcrd.ds_cpo_config_pk = dcc.ds_cpo_config_pk
				   AND dcrd.cpo_ord_num = dcc.cpo_ord_num
				   AND dcc.svc_config_mstr_pk = scm.svc_config_mstr_pk
				   AND scm.svc_config_mstr_pk = smm.svc_config_mstr_pk
				   AND dcrd.mdse_cd = smm.mdse_cd
				   AND smm.cpo_ord_num = dcd.cpo_ord_num
				   AND smm.cpo_dtl_line_num = dcd.cpo_dtl_line_num
				   AND smm.cpo_dtl_line_sub_num = dcd.cpo_dtl_line_sub_num
				   AND (  (dcrd.cpo_ord_num || dcrd.ds_cpo_config_pk || dcrd.ds_cpo_line_num|| nvl(dcrd.ds_cpo_line_sub_num, 0))) = c1rec.line_id
				   AND dcrd.cpo_ord_num = c1rec.header_id;

				/*select source_line_ref_id into l_prior_source_line_id from
						(
							select t.source_line_ref_id, source_header_ref_id, transaction_date, transaction_type_id, t.transaction_id
							from csi_transactions t, csi_item_instances_h h                
							where 1 = 1
							and h.transaction_id = t.transaction_id
							and instance_id =c1rec.instance_id
							and transaction_type_id in (51, 53)
							and t.transaction_id < (select transaction_id from csi_transactions ct
												where transaction_type_id = 53
												and source_line_ref_id = c1rec.line_id
												and source_header_ref_id = c1rec.header_id
												) 
							order by t.transaction_date desc)
						where rownum = 1;*/
		EXCEPTION
		WHEN NO_DATA_FOUND THEN
		   l_message := 'NO_DATA';
		WHEN TOO_MANY_ROWS THEN
		   l_message := 'TOO_MANY_ROWS';
		END;

		IF l_prior_source_line_id IS NOT NULL
		   THEN
		    BEGIN
			
				SELECT dcd.cpo_ord_num order_number,
					   dcd.cpo_ord_num header_id,
					   (dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num) line_number,
					   doc.ds_ord_catg_nm name,
					   dcd.ent_func_net_unit_prc_amt unit_selling_price,
					   dcd.ord_qty ordered_quantity,
					   dac.ds_acct_nm party_name,
					   dac.sell_to_cust_cd account_number,
					   dcd.rtl_wh_cd ship_from_org_id,
					   dcd.rtl_wh_nm organization_name,
					   (SELECT sodi.shpg_instn_cmnt_txt
						 FROM shpg_ord_dely_instn sodi,
							  shpg_ord so
						 WHERE sodi.so_num = so.so_num
						 AND so.trx_hdr_num = dcd.cpo_ord_num) shipping_instructions
				  INTO l_order_number,
					   l_header_id,
					   l_line_number,
					   l_order_type,
					   l_unit_selling_price,
					   l_ordered_quantity,
					   l_party_name,
					   l_account_number,
					   l_ship_from_org_id,
					   l_ship_from_warehouse_name,
					   l_shipping_instructions
				  FROM cpo_dtl_v dcd,
					   ds_ord_catg doc,
					   cpo dc,
					   sell_to_cust dac,
					   ds_cpo_config dcc
				 WHERE dcd.ds_cpo_config_pk = dcc.ds_cpo_config_pk
				   AND dcc.ship_to_cust_acct_cd = dac.sell_to_cust_cd
				   AND dcd.cpo_ord_num = dc.cpo_ord_num
				   AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
				   AND (dcd.cpo_ord_num || dcd.ds_cpo_config_pk || dcd.ds_cpo_line_num|| nvl(dcd.ds_cpo_line_sub_num, 0)) = l_prior_source_line_id;
			 
			
					/*SELECT 
					 H.ORDER_NUMBER, H.HEADER_ID, L.LINE_NUMBER, OOTL.NAME, L.UNIT_SELLING_PRICE, L.ORDERED_QUANTITY, HP.PARTY_NAME, HCA.ACCOUNT_NUMBER,
					 L.SHIP_FROM_ORG_ID, OOD.ORGANIZATION_NAME, L.SHIPPING_INSTRUCTIONS
					INTO l_order_number, l_header_id, l_line_number, l_order_type, l_unit_selling_price, l_ordered_quantity, l_party_name, l_account_number,
						 l_ship_from_org_id, l_ship_from_warehouse_name, l_shipping_instructions
					FROM OE_ORDER_LINES_ALL l, OE_ORDER_HEADERS_ALL h, OE_TRANSACTION_TYPES_TL ootl, HZ_CUST_SITE_USES_ALL hcsu, HZ_CUST_ACCT_SITES_ALL hcas,
						 HZ_CUST_ACCOUNTS hca, HZ_PARTIES hp, ORG_ORGANIZATION_DEFINITIONS ood
					WHERE l.line_id = l_prior_source_line_id
					AND l.header_id = h.header_id
					AND h.order_type_id = ootl.transaction_type_id
					AND l.ship_to_org_id = hcsu.site_use_id
					AND hcsu.cust_acct_site_id = hcas.cust_acct_site_id
					AND hcas.cust_account_id = hca.cust_account_id
					AND hca.party_id = hp.party_id
					AND l.ship_from_org_id = ood.organization_id;*/
			
		    EXCEPTION
			WHEN NO_DATA_FOUND
			   THEN
				l_message := 'SOLD_TO_DETAILS_NOT_FOUND_FOR_PRIOR_SOURCE_LINE_ID';
			WHEN OTHERS
   			   THEN
				l_message := 'SOLD_TO_DETAILS_QUERY_OTHER_ISSUES_FOR_PRIOR_SOURCE_LINE_ID';			
		     END;
			 
			 
		     INSERT INTO canon_e618_ret_origref_tbl
										(line_id,
										 prior_source_line_id,
										 sold_order_number,
										 sold_order_header_id,
										 sold_order_line_number,
										 sold_line_id,
										 sold_order_type,
										 sold_to_price,
										 sold_quantity,
										 sold_customer_name,
										 sold_account_number,
										 sold_order_ship_from_org_id,
										 sold_order_ship_from_warehouse,
										 sold_order_shipping_inst
										)
			VALUES
						  (c1rec.line_id,
						  l_prior_source_line_id,
						  l_order_number,
						  l_header_id,
						  l_line_number, 
						  l_prior_source_line_id,
						  l_order_type, 
						  l_unit_selling_price, 
						  l_ordered_quantity, 
						  l_party_name, 
						  l_account_number,
						  l_ship_from_org_id, 
						  l_ship_from_warehouse_name, 
						  l_shipping_instructions);	
						  
         	  COMMIT;
		END IF;
	END LOOP;
END;


END canon_e618_orders_rma_stg_pkg;
/
