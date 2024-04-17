create or replace PACKAGE CANON_RD143_CONT_MACH_PKG AS 

G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_RD143_CONT_MACH_PKG';

PROCEDURE MAIN( errbuf OUT  VARCHAR2
							,retcode OUT   NUMBER							);

END CANON_RD143_CONT_MACH_PKG;
/

create or replace PACKAGE BODY CANON_RD143_CONT_MACH_PKG
AS

PROCEDURE MAIN( errbuf OUT  VARCHAR2
							,retcode OUT   NUMBER
							)
AS
l_procedure_name VARCHAR2(100) := 'CONT_MACH_BRANCH';

BEGIN

	EXECUTE IMMEDIATE 'Truncate table CANON_RD143_CONT_MACH_TBL';
	dbms_output.put_line('CANON_RD143_CONT_MACH_TBL truncated.');
	
	-- contract header, contract details and machine details
	INSERT INTO CANON_RD143_CONT_MACH_TBL (contract_id
										   ,contract_number
										   ,legacy_contract_number
										   ,contract_number_modifier
										   ,legacy_cont_no_modifier
										   ,cont_start_date
										   ,cont_end_date
										   ,fleet
										   ,branch
										   ,po_number
										   ,invoice_comments
										   ,renewal_type
										   ,markup_pct
										   ,contract_status
										   ,cont_header_salesperson
										   ,bill_to_account
--										   ,bill_to_loc_num
										   ,contract_payment_terms
										   ,automatic_estimation_round
										   ,contract_type
										   ,edi_indicator
										   ,consolidation_by_po
										   ,contct_creation_date
										   ,contract_group
										   ,line_id
										   ,line_description
										   ,line_start_date
										   ,line_end_date
										   ,line_term_date
										   ,line_status
										   ,line_price
										   ,base_cycle
										   ,baseamount_percycle
										   ,usage_cycle
										   ,reading_cycle
										   ,billto_site
										   ,instance_id
										   ,invoice_rule
										   ,cont_term_date
										   ,order_number
										   ,last_billing_period
										   ,shipto_account
										   ,serial_number
										   ,service_branch
										   ,fin_service_branch
										   ,product_number
										   ,install_date
										   ,ib_po
										   ,shipto_site
										--   ,shipto_loc
										   ,current_loc
										   ,item_description
										   ,product_category
										   ,billing_cntr_id
--										   ,billing_cntr_desc
										   ) (SELECT dc.ds_contr_pk
															,dc.ds_contr_num
															,dc.svc_contr_ref_cmnt_txt
															,NULL
															,dc.svc_contr_ref_cmnt_txt
															,to_date(dc.contr_vrsn_eff_from_dt,'yyyymmdd')
															,to_date(dc.contr_vrsn_eff_thru_dt,'yyyymmdd')
															,(CASE WHEN UPPER(dcc.ds_contr_catg_nm) = 'FLEET' THEN 'Y' 
                                                                   WHEN UPPER(dcc.ds_contr_catg_nm) = 'REGULAR' THEN 'N'
                                                                   WHEN UPPER(dcc.ds_contr_catg_nm) = 'AGGREGATE' THEN 'A' 
                                                                   WHEN UPPER(dcc.ds_contr_catg_nm) = 'WARRANTY' THEN 'W' END) fleet
															,dc.svc_contr_br_cd
															,dc.cust_po_num
															,dc.contr_inv_cmnt_txt
															,dc.CONTR_AUTO_RNW_TP_cd
															,dc.base_prc_up_ratio
															,dcs.ds_contr_sts_nm
															,t.toc_nm
															--,dc.sell_to_cust_cd
															,smm.bill_to_acct_num
															,dc.pmt_term_cash_disc_cd
															,dc.mtr_est_tp_cd
															,dct.ds_contr_tp_nm
															,dc.ds_contr_edi_cd
															,dc.inv_print_grp_cd
															,to_date(dc.ds_contr_crat_dt,'yyyymmdd')
															,dc.ds_contr_rpt_grp_num
															,dcd.ds_contr_dtl_pk
														    --,dcd.svc_pgm_mdse_cd
															,dmi_dcd.mdse_nm
														    ,to_date(dcd.contr_eff_from_dt,'yyyymmdd')
														    ,to_date(dcd.contr_eff_thru_dt,'yyyymmdd')
														    ,to_date(dcd.contr_clo_dt,'yyyymmdd')
															,dcds.DS_CONTR_CTRL_STS_NM
														    ,dcd.base_prc_term_deal_amt_rate
														    ,dcd.base_bllg_cycle_cd
														    ,dcd.base_prc_deal_amt
														    ,dcd.mtr_bllg_cycle_cd
														    ,dcd.mtr_bllg_cycle_cd
														    ,dcd.base_bill_to_cust_cd
														    ,dcd.svc_mach_mstr_pk
														    ,dcd.base_bllg_tmg_cd
														    ,to_date(dcd.contr_clo_dt,'yyyymmdd')
														    ,dcd.cpo_ord_num
														    ,to_date(dcd.base_bllg_last_bllg_dt,'yyyymmdd')
														    ,smm.cur_loc_acct_num
														    ,smm.ser_num 
														    ,smm.fld_svc_br_cd 
														    ,smm.fin_br_cd 
														    ,smm.mdse_cd 
														    ,to_date(smm.istl_dt,'yyyymmdd') 
														    ,smm.cust_iss_po_num 
														    ,smm.cur_loc_num 
														   -- ,smm.ind_cur_loc_num 
														    ,smm.ind_cur_loc_num 
														    ,dmi.mdse_desc_short_txt
														    ,dmi.sw_prod_catg_cd
														    ,dcbm.ds_contr_bllg_mtr_pk
--														    ,pmpbm.mtr_lb_desc_txt
													  FROM ds_contr dc
														   ,ds_contr_sts dcs
														   ,ds_contr_catg dcc
														   ,svc_mach_mstr smm
														   ,ds_contr_dtl dcd
														   ,toc t
														   ,ds_contr_tp dct
														   ,mdse dmi -- DB Changes
														   ,mdse dmi_dcd
														   ,ds_contr_dtl_sts_v dcds
														   ,ds_contr_bllg_mtr dcbm
--														   ,prc_mtr_pkg_bllg_mtr pmpbm
													  WHERE dc.ezcancelflag = '0'
													    AND dc.glbl_cmpy_cd = 'ADB'
														AND dcs.ezcancelflag = '0'
														AND dcs.glbl_cmpy_cd = 'ADB'
														AND dcc.ezcancelflag = '0'
														AND dcc.glbl_cmpy_cd = 'ADB'
														AND t.ezcancelflag(+) = '0'
														AND t.glbl_cmpy_cd(+) = 'ADB'
														AND dcbm.ezcancelflag(+) = '0'
														AND dcbm.glbl_cmpy_cd(+) = 'ADB'
--														AND silm.ezcancelflag = '0'
--														AND silm.glbl_cmpy_cd = 'ADB'
														AND dc.svc_line_biz_cd = 'ESS'
														AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
--														AND dcs.ds_contr_sts_nm IN (SELECT status
--																						  FROM CANON_E404_CONTRACT_HEADER_V)
														--AND UPPER(dcs.ds_contr_sts_nm) IN ('ACTIVE')
														AND dc.ds_contr_catg_cd = dcc.ds_contr_catg_cd
														AND dc.ds_contr_tp_cd = dct.ds_contr_tp_cd
														AND dc.toc_cd = t.toc_cd(+)
														AND dc.ds_contr_pk = dcd.ds_contr_pk
														AND dcd.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
														AND dcd.ds_contr_dtl_pk = dcds.ds_contr_dtl_pk
--														AND dcds.DS_CONTR_CTRL_STS_NM NOT IN (SELECT status
--																							   FROM CANON_E404_INACTIVE_CONTRACT_V)
														AND smm.mdse_cd = dmi.mdse_cd(+)
														AND dcd.svc_pgm_mdse_cd = dmi_dcd.mdse_cd(+)
														AND dcd.ds_contr_dtl_pk = dcbm.ds_contr_dtl_pk(+)
														);
														--AND (smm.sld_by_line_biz_tp_cd = 'ESS' OR smm.svc_by_line_biz_tp_cd = 'ESS'));
	dbms_output.put_line(SQL%ROWCOUNT ||' inserted into CANON_RD143_CONT_MACH_TBL.');
	COMMIT;
	
	--update model details
	MERGE INTO canon_rd143_cont_mach_tbl rd143
	 USING (SELECT mn.t_mdl_nm
				   ,ss.svc_seg_nm 
				   ,dcd.ds_contr_dtl_pk
			  FROM ds_contr_dtl dcd
			       ,svc_config_mstr scm
				   ,mdl_nm mn
				   ,ds_mdl dm
				   ,svc_seg ss
			 WHERE dcd.ezcancelflag = '0'
			   AND dcd.glbl_cmpy_cd = 'ADB'
			   AND scm.ezcancelflag = '0'
			   AND scm.glbl_cmpy_cd = 'ADB'
			   AND mn.ezcancelflag = '0'
			   AND mn.t_glbl_cmpy_cd = 'ADB'
			   AND dm.ezcancelflag = '0'
			   AND dm.glbl_cmpy_cd = 'ADB'
			   AND ss.ezcancelflag = '0'
			   AND ss.glbl_cmpy_cd = 'ADB'
			   AND dcd.svc_config_mstr_pk = scm.svc_config_mstr_pk
			   AND scm.mdl_id = mn.t_mdl_id(+)
			   AND scm.mdl_id = dm.mdl_id(+)
			   AND dm.svc_seg_cd = ss.svc_seg_cd(+)
			) mdl
		ON(rd143.line_id = mdl.ds_contr_dtl_pk)
		WHEN MATCHED THEN UPDATE SET
			service_category = t_mdl_nm
			,model_segment = svc_seg_nm;
		
	dbms_output.put_line(SQL%ROWCOUNT ||' merged for model details into CANON_RD143_CONT_MACH_TBL.');	
    COMMIT;
    
	--update billto details
	MERGE INTO canon_rd143_cont_mach_tbl rd143
	USING(SELECT dac.sell_to_cust_cd ds_acct_num -- DB Changes
	             ,dac.ds_acct_dlr_cd
				 ,dac.ds_acct_nm
				 ,dac.first_line_addr
				 ,dac.scd_line_addr
				 ,dac.third_line_addr
				 ,dac.frth_line_addr
				 ,dac.cty_addr
				 ,dac.st_cd
				 ,dac.post_cd
		    FROM sell_to_cust dac -- DB Changes
		   WHERE dac.ezcancelflag = '0'
			 AND dac.glbl_cmpy_cd = 'ADB'
		  ) bt
	ON(rd143.bill_to_account = bt.ds_acct_num)
	WHEN MATCHED THEN UPDATE SET
		rd143.bill_to_dealer_code = bt.ds_acct_dlr_cd
		,bill_to_party = ds_acct_nm
		,billto_address1 = first_line_addr
		,billto_address2 = scd_line_addr
		,billto_address3 = third_line_addr
		,billto_address4 = frth_line_addr
		,billto_city = cty_addr
		,billto_state = st_cd
		,billto_postalcode = post_cd;
	
dbms_output.put_line(SQL%ROWCOUNT ||' merged for billto details into CANON_RD143_CONT_MACH_TBL.');		
COMMIT;


    MERGE INTO canon_rd143_cont_mach_tbl rd143
	  USING (SELECT loc_num
					,bill_to_cust_cd
			   FROM bill_to_cust
			   WHERE ezcancelflag = '0'
			     AND glbl_cmpy_cd = 'ADB'
			)bt
	   ON(rd143.billto_site = bt.bill_to_cust_cd)
	   WHEN MATCHED THEN UPDATE SET
			billto_loc = loc_num;
	dbms_output.put_line(SQL%ROWCOUNT ||' merged for billto loc details into CANON_RD143_CONT_MACH_TBL.');
	COMMIT;	
	
	--update shipto details
	MERGE INTO canon_rd143_cont_mach_tbl rd143
	 USING (SELECT rd.contract_id
				   ,rd.line_id
				   ,rd.billing_cntr_id
				   ,dac.ds_acct_dlr_cd
				   ,stc.loc_nm
				   ,stc.loc_num
				   ,stc.first_line_addr
				   ,stc.scd_line_addr
				   ,stc.third_line_addr
				   ,stc.frth_line_addr
				   ,stc.cty_addr
				   ,stc.st_cd
				   ,stc.post_cd
				   ,ds_acct_grp_nm
			  FROM ship_to_cust stc
				   ,svc_mach_mstr smm
				   ,sell_to_cust dac -- DB Changes
				   ,canon_rd143_cont_mach_tbl rd
				   ,ds_acct_grp_asg daga
				   ,ds_acct_grp dag
			 WHERE rd.instance_id = smm.svc_mach_mstr_pk
			   AND smm.cur_loc_num = stc.ship_to_cust_cd(+)
			   AND smm.cur_loc_acct_num = dac.sell_to_cust_cd(+) -- DB Changes
			   AND dac.sell_to_cust_cd = daga.ds_acct_num(+) -- DB Changes
			   AND daga.ds_acct_grp_cd = dag.ds_acct_grp_cd(+)
			   --QC23695 changes - start
			   and smm.ezcancelflag = '0'
			   and smm.glbl_cmpy_cd = 'ADB'
			   and stc.ezcancelflag(+) = '0'
			   and stc.glbl_cmpy_cd(+) = 'ADB'
			   and dac.ezcancelflag(+) = '0'
			   and dac.glbl_cmpy_cd(+) = 'ADB'
			   and daga.ezcancelflag(+) = '0'
			   and daga.glbl_cmpy_cd(+) = 'ADB'
			   and dag.ezcancelflag(+) = '0'
			   and dag.glbl_cmpy_cd(+) = 'ADB'
			   ----QC23695 changes - end
			)st
	  ON(rd143.contract_id = st.contract_id AND rd143.line_id = st.line_id AND NVL(rd143.billing_cntr_id,'-1') = NVL(st.billing_cntr_id,'-1'))
	  WHEN MATCHED THEN UPDATE SET
		rd143.shipto_dealercode = ds_acct_dlr_cd
		,rd143.shipto_party = loc_nm
		,shipto_address1 = first_line_addr
		,shipto_address2 = scd_line_addr
		,shipto_address3 = third_line_addr
		,shipto_address4 = frth_line_addr
		,shipto_city = cty_addr
		,shipto_state = st_cd
		,shipto_postalcode = post_cd
		,current_loc_party = loc_nm
		,current_loc_address1 = first_line_addr
		,current_loc_address2 = scd_line_addr
		,current_loc_address3 = third_line_addr
		,current_loc_address4 = frth_line_addr
		,current_loc_city = cty_addr
		,current_loc_state = st_cd
		,current_loc_postal_code = post_cd
		,shipto_cust_prof_name = st.ds_acct_grp_nm
		,shipto_cust_report_name = st.ds_acct_grp_nm
		,shipto_loc = loc_num
		;
	
dbms_output.put_line(SQL%ROWCOUNT ||' merged for shipto details into CANON_RD143_CONT_MACH_TBL.');			
	COMMIT;
	
	--update contact details
	MERGE INTO canon_rd143_cont_mach_tbl rd143
	  USING(SELECT distinct ds_contr_num, dcd.ds_contr_dtl_pk, ds_contr_bllg_mtr_pk
					,first_value(dcp_email.DS_CTAC_PNT_VAL_TXT) OVER (PARTITION BY ds_contr_num, dcd.ds_contr_dtl_pk, ds_contr_bllg_mtr_pk ORDER BY dcp_email.DS_CTAC_PNT_VAL_TXT) email
					,first_value(dcp_fax.DS_CTAC_PNT_VAL_TXT) OVER (PARTITION BY ds_contr_num, DCD.ds_contr_dtl_pk,ds_contr_bllg_mtr_pk ORDER BY dcp_fax.DS_CTAC_PNT_VAL_TXT) fax
					,first_value(dcp_phone.DS_CTAC_PNT_VAL_TXT) OVER (PARTITION BY ds_contr_num, dcd.ds_contr_dtl_pk,ds_contr_bllg_mtr_pk ORDER BY dcp_phone.DS_CTAC_PNT_VAL_TXT) phone
			 FROM ds_ctac_pnt dcp_phone
				  ,ds_ctac_pnt dcp_email
				  ,ds_ctac_pnt dcp_fax
				  ,ds_ctac_pnt_tp dcpt_email
				  ,ds_ctac_pnt_tp dcpt_phone
				  ,ds_ctac_pnt_tp dcpt_fax
				  ,ds_contr dc
				  ,ds_contr_dtl dcd
				  ,ds_contr_bllg_mtr dcbm
		   WHERE dc.glbl_cmpy_cd ='ADB'
		     AND dc.ezcancelflag = 'ADB'
			 AND dcd.glbl_cmpy_cd = 'ADB'
			 AND dcd.ezcancelflag = '0'
			 AND dcp_email.glbl_cmpy_cd(+) = 'ADB'
			 AND dcp_email.ezcancelflag(+) = '0'
			 AND dcp_phone.glbl_cmpy_cd(+) = 'ADB'
			 AND dcp_phone.ezcancelflag(+) = '0'
			 AND dcp_fax.glbl_cmpy_cd(+) = 'ADB'
			 AND dcp_fax.ezcancelflag(+) = '0'
			 AND dcpt_email.glbl_cmpy_cd(+) = 'ADB'
			 AND dcpt_email.ezcancelflag(+) = '0'
			 AND dcpt_phone.glbl_cmpy_cd(+) = 'ADB'
			 AND dcpt_phone.ezcancelflag(+) = '0'
			 AND dcpt_fax.glbl_cmpy_cd(+) = 'ADB'
			 AND dcpt_fax.ezcancelflag(+) = '0'
			 AND dcbm.ezcancelflag = '0'
			 AND dcbm.glbl_cmpy_cd = 'ADB'
			 AND dc.ctac_psn_pk = dcp_email.ctac_psn_pk(+)
			 AND dc.ctac_psn_pk = dcp_fax.ctac_psn_pk(+)
			 AND dc.ctac_psn_pk = dcp_phone.ctac_psn_pk(+)
			 AND dcp_email.ds_ctac_pnt_tp_cd(+) = dcpt_email.ds_ctac_pnt_tp_cd
			 AND dcp_fax.ds_ctac_pnt_tp_cd(+) = dcpt_fax.ds_ctac_pnt_tp_cd
			 AND dcp_phone.ds_ctac_pnt_tp_cd(+) = dcpt_phone.ds_ctac_pnt_tp_cd
			 AND UPPER(dcpt_email.ds_ctac_pnt_tp_nm(+)) IN ('EMAIL - WORK')
			 AND UPPER(dcpt_phone.ds_ctac_pnt_tp_nm(+)) IN ('PHONE - MOBILE','PHONE - WORK','PHONE - ASSISTANT')
			 AND UPPER(dcpt_fax.ds_ctac_pnt_tp_nm(+)) IN ('FAX - WORK')
			 AND dc.ds_contr_pk = dcd.ds_contr_pk
			 AND dcd.ds_contr_dtl_pk = dcbm.ds_contr_dtl_pk
			)ctac
	 ON(rd143.contract_number = ctac.ds_contr_num AND
	    rd143.line_id = ctac.ds_contr_dtl_pk AND
	    rd143.billing_cntr_id = ctac.ds_contr_bllg_mtr_pk)
	WHEN MATCHED THEN UPDATE SET
		attention_phone = ctac.phone
		,fax_attention = ctac.fax
		,email = ctac.email ;
dbms_output.put_line(SQL%ROWCOUNT ||' merged for contact details into CANON_RD143_CONT_MACH_TBL.');		
COMMIT;			
	
	--update tier rate and allowance
	MERGE INTO canon_rd143_cont_mach_tbl rd143
	 USING(SELECT DISTINCT  
					dcbm.ds_contr_dtl_pk,
					FIRST_VALUE(pmpbm.mtr_lb_desc_txt) OVER (PARTITION BY dcbm.ds_contr_bllg_mtr_pk ORDER BY pmpbm.mtr_lb_cd NULLS LAST) mtr_lb_desc_txt,
					dcbm.ds_contr_bllg_mtr_pk, 
					tier1.tier1_mtr_copy_qty, 
					tier1.tier1_mtr_amt_rate,
					FIRST_VALUE(tier2.tier2_mtr_copy_qty) OVER (PARTITION BY a.ds_contr_bllg_mtr_pk ORDER BY tier2.tier2_mtr_copy_qty NULLS LAST) tier2_mtr_copy_qty,
					FIRST_VALUE(tier2.tier2_mtr_amt_rate) OVER (PARTITION BY a.ds_contr_bllg_mtr_pk ORDER BY tier2.tier2_mtr_amt_rate NULLS LAST) tier2_mtr_amt_rate, 
					FIRST_VALUE(tier3.tier3_mtr_copy_qty) OVER (PARTITION BY a.ds_contr_bllg_mtr_pk ORDER BY tier3.tier3_mtr_copy_qty NULLS LAST) tier3_mtr_copy_qty, 
					FIRST_VALUE(tier3.tier3_mtr_amt_rate) OVER (PARTITION BY a.ds_contr_bllg_mtr_pk ORDER BY tier3.tier3_mtr_amt_rate NULLS LAST) tier3_mtr_amt_rate,
					FIRST_VALUE(tier4.tier4_mtr_copy_qty) OVER (PARTITION BY a.ds_contr_bllg_mtr_pk ORDER BY tier4.tier4_mtr_copy_qty NULLS LAST) tier4_mtr_copy_qty, 
					FIRST_VALUE(tier4.tier4_mtr_amt_rate) OVER (PARTITION BY a.ds_contr_bllg_mtr_pk ORDER BY tier4.tier4_mtr_amt_rate NULLS LAST) tier4_mtr_amt_rate
			 FROM contr_xs_copy a   
				  ,ds_contr_bllg_mtr dcbm
				  ,prc_mtr_pkg_bllg_mtr pmpbm       
				  ,(SELECT DISTINCT cxc.contr_xs_copy_pk
							  ,cxc.ds_contr_bllg_mtr_pk
							  ,FIRST_VALUE(xs_mtr_copy_qty) OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum) tier1_mtr_copy_qty
							  ,FIRST_VALUE(xs_mtr_amt_rate) OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum) tier1_mtr_amt_rate
					 FROM contr_xs_copy cxc
					WHERE cxc.xs_mtr_first_flg = 'Y'
					  AND cxc.ezcancelflag = '0'
					  AND cxc.glbl_cmpy_cd = 'ADB'
				   )tier1
				 ,(SELECT DISTINCT contr_xs_copy_pk, ds_contr_bllg_mtr_pk, tier2_mtr_copy_qty, tier2_mtr_amt_rate
					 FROM( SELECT rownum rn
								  ,cxc.contr_xs_copy_pk
								  ,cxc.ds_contr_bllg_mtr_pk
								   ,xs_mtr_copy_qty tier2_mtr_copy_qty
								  ,xs_mtr_amt_rate tier2_mtr_amt_rate
								  ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum) mtr_copy_rn
								  ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum ) mtr_amt_rn
							FROM contr_xs_copy cxc
							WHERE xs_mtr_first_flg = 'N'
							  AND ezcancelflag = '0'
							  AND glbl_cmpy_cd = 'ADB'
						   ) mt2
					WHERE mt2.mtr_copy_rn = 1
					  AND mt2.mtr_amt_rn = 1 
				   )tier2
				   ,(SELECT DISTINCT contr_xs_copy_pk, ds_contr_bllg_mtr_pk, tier3_mtr_copy_qty, tier3_mtr_amt_rate
					 FROM( SELECT rownum rn
								  ,cxc.contr_xs_copy_pk
								  ,cxc.ds_contr_bllg_mtr_pk
								   ,xs_mtr_copy_qty tier3_mtr_copy_qty
								  ,xs_mtr_amt_rate tier3_mtr_amt_rate
								  ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum) mtr_copy_rn
								  ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum) mtr_amt_rn
							FROM contr_xs_copy cxc
							WHERE xs_mtr_first_flg = 'N'
							  AND ezcancelflag = '0'
							  AND glbl_cmpy_cd = 'ADB'
						   ) mt2
					WHERE mt2.mtr_copy_rn = 2
					  AND mt2.mtr_amt_rn = 2
				   )tier3
				   ,(SELECT DISTINCT contr_xs_copy_pk, ds_contr_bllg_mtr_pk, tier4_mtr_copy_qty, tier4_mtr_amt_rate
					 FROM( SELECT rownum rn
								  ,cxc.contr_xs_copy_pk
								  ,cxc.ds_contr_bllg_mtr_pk
								  ,xs_mtr_copy_qty tier4_mtr_copy_qty
								  ,xs_mtr_amt_rate tier4_mtr_amt_rate
								  ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum) mtr_copy_rn
								  ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY rownum) mtr_amt_rn
							FROM contr_xs_copy cxc
							WHERE xs_mtr_first_flg = 'N'
							  AND ezcancelflag = '0'
							  AND glbl_cmpy_cd = 'ADB'
						   ) mt2
					WHERE mt2.mtr_copy_rn = 3
					  AND mt2.mtr_amt_rn = 3
				   )tier4
			   WHERE a.ds_contr_bllg_mtr_pk = tier1.ds_contr_bllg_mtr_pk(+)
				 AND a.ds_contr_bllg_mtr_pk = tier2.ds_contr_bllg_mtr_pk(+)
				 AND a.ds_contr_bllg_mtr_pk = tier3.ds_contr_bllg_mtr_pk(+)
				 AND a.ds_contr_bllg_mtr_pk = tier4.ds_contr_bllg_mtr_pk(+)
			--	 AND a.ds_contr_bllg_mtr_pk = '1003308'
				 AND a.ds_contr_bllg_mtr_pk(+) = dcbm.ds_contr_bllg_mtr_pk
				 AND dcbm.bllg_mtr_lb_cd = pmpbm.mtr_lb_cd(+)
				 AND a.ezcancelflag(+) = '0'
				 AND a.glbl_cmpy_cd(+) = 'ADB'
				 AND dcbm.ezcancelflag = '0'
				 AND dcbm.glbl_cmpy_cd = 'ADB'
--				 AND dcbm.DS_CONTR_BLLG_MTR_STS_CD IN ('ACTV')
		 )tier
		ON(rd143.line_id = tier.ds_contr_dtl_pk AND rd143.billing_cntr_id = ds_contr_bllg_mtr_pk )
		WHEN MATCHED THEN UPDATE SET
--			billing_cntr_id = tier.ds_contr_bllg_mtr_pk
			billing_cntr_desc = mtr_lb_desc_txt
			,tier1_allowance = tier1_mtr_copy_qty
			,tier1_rate = tier1_mtr_amt_rate
			,tier2_allowance = tier2_mtr_copy_qty
			,tier2_rate = tier2_mtr_amt_rate
			,tier3_allowance = tier3_mtr_copy_qty
			,tier3_rate = tier3_mtr_amt_rate
			,tier4_allowance = tier4_mtr_copy_qty
			,tier4_rate = tier4_mtr_amt_rate ;
	dbms_output.put_line(SQL%ROWCOUNT ||' merged for tier allowance and rate into CANON_RD143_CONT_MACH_TBL.');							
			
	COMMIT;
	retcode := 0;
EXCEPTION
WHEN OTHERS THEN
    ROLLBACK;
    dbms_output.put_line('Exception in CANON_RD143_CONT_MACH_PKG: ' || SQLERRM);
	retcode := 2;
    errbuf := sqlerrm;
END MAIN;


END CANON_RD143_CONT_MACH_PKG;
/