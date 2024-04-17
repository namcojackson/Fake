create or replace PACKAGE CANON_E633_LFSPPS_CONV_PKG AS

G_PACKAGE_NAME VARCHAR2 (50) := 'CANON_E633_LFSPPS_CONV_PKG';

PROCEDURE extract_prc(x_errbuff OUT VARCHAR2, x_retcode OUT VARCHAR2, p_extract IN VARCHAR2 );
PROCEDURE load_customers(retcode out VARCHAR2, errbuff out VARCHAR2);
PROCEDURE load_ib(retcode out VARCHAR2, errbuff out VARCHAR2);
PROCEDURE load_config(retcode out VARCHAR2, errbuff out VARCHAR2);
PROCEDURE load_ib_contacts(retcode out VARCHAR2, errbuff out VARCHAR2);
PROCEDURE load_meter_reads(retcode out VARCHAR2, errbuff out VARCHAR2);
PROCEDURE load_open_svc_calls(retcode out VARCHAR2, errbuff out VARCHAR2);
PROCEDURE load_close_svc_calls(retcode out VARCHAR2, errbuff out VARCHAR2);
PROCEDURE load_ar_aging(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_order_headers(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_order_details(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_order_comments(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_contracts(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);


END CANON_E633_LFSPPS_CONV_PKG;
/

create or replace PACKAGE BODY CANON_E633_LFSPPS_CONV_PKG
AS

PROCEDURE extract_prc(x_errbuff OUT VARCHAR2, x_retcode OUT VARCHAR2, p_extract IN VARCHAR2 )
AS
l_procedure_name VARCHAR2(100) := 'extract_prc';

BEGIN
	x_retcode := '0';
	
	CASE
  WHEN p_extract = 'Customers' THEN
	  load_customers(x_retcode, x_errbuff);
   WHEN p_extract = 'Install Base' THEN
	  load_ib(x_retcode, x_errbuff);
   WHEN p_extract = 'Configurations' THEN
	  load_config(x_retcode, x_errbuff);
   WHEN p_extract = 'Machine Contacts' THEN
	  load_ib_contacts(x_retcode, x_errbuff);
    WHEN p_extract = 'Meter Reads' THEN
	  load_meter_reads(x_retcode, x_errbuff);
   WHEN p_extract = 'Contracts' THEN
	  load_contracts(x_retcode, x_errbuff);
   WHEN p_extract = 'Open Service Calls' THEN
	  load_open_svc_calls(x_retcode, x_errbuff);
   WHEN p_extract = 'Closed Service Calls' THEN
	  load_close_svc_calls(x_retcode, x_errbuff);
   WHEN p_extract = 'AR Aging' THEN
	  load_ar_aging(x_retcode, x_errbuff);
--   WHEN p_extract = 'CFS Leases' THEN
--	  load_cfslease(x_retcode, x_errbuff);
   WHEN p_extract = 'Order Headers' THEN
	  load_order_headers(x_retcode, x_errbuff);
   WHEN p_extract = 'Order Details' THEN
	  load_order_details(x_retcode, x_errbuff);
   WHEN p_extract = 'Order Comments' THEN
	  load_order_comments(x_retcode, x_errbuff);
 /*   WHEN p_extract = 'Sales Comp' THEN
--	  load_sales_comp(x_retcode, x_errbuff); */
    END CASE;

EXCEPTION
	WHEN OTHERS THEN
		ROLLBACK;
		x_retcode := 2;
		x_errbuff := sqlerrm;
		canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,p_extract,NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END extract_prc;

PROCEDURE load_customers(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_customers';

BEGIN
	retcode := 0;
  
  --truncate table
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_cust_site_conv_tbl';
  
  -- extract data from CANMTH
--	INSERT INTO canon_e633_cust_site_conv_tbl (
  INSERT INTO canon_e633_cust_site_conv_tbl (
			LEGACY_REF_ID 
			,ACCOUNT_NAME 
			,ACCOUNT_TYPE 
			,LOCATION_NUMBER 
			,ADDRESS 
			,TOWN 
			,COUNTY 
			,COUNTRY 
			,POSTAL_CODE 
			,PO_BOX_NUMBER 
			,PO_BOX_POSTAL_CODE 
			,PO_BOX_TOWN 
			,FAX 
			,TELEPHONE 
			,EDIN 
			,CREDIT_STOP 
			,GEO_AREA 
			,IS_DELETED 
			,DOES_BUSINESS_AS_DBA 
			,COMMON_RESRV_IND 
			,VISIT_ADDR 
			,INSTALL_ADDR 
			,DELIVERY_ADDR 
			,INVOICE_ADDR 
			,CONTRACT 
			,METER_READ 
			,TECHNICAL 
			,INTERNAL 
			,SOL_1 
			,SOURCE 
			,LFSBU 
			,DPSBU 
			,PPSBU 
			,SUPBU 
			,DUNS_NUMBER 
			,DUNS_HQ_NUM 
			,DUNS_HQ_NAME 
			,ULT_DUNS 
			,ULT_DUNS_NAME 
			,SIC_CODE 
			,SIC_CODE_DESCRIPTION 
			,LAST_UPDATE_DATE 
			,LFS_ACCOUNT_OWNER_ID 
			,LFS_OWNER_EMPLOYEE_NUMBER 
			,LFS_SFDC_NUMBER 
			,LFS_DUNS_NUMBER 
			,LFS_DUNS_HQ_NUM 
			,LFS_DUNS_HQ_NAME 
			,LFS_ULT_DUNS 
			,LFS_ULT_DUNS_NAME 
			,LFS_SIC_CODE 
			,LFS_SIC_CODE_DESCRIPTION 
			,LFS_INDUSTRY_MKTG 
			,PPS_ACCOUNT_OWNER_ID 
			,PPS_OWNER_EMPLOYEE_NUMBER 
			,PPS_SFDC_NUMBER 
			,PPS_DUNS_NUMBER 
			,PPS_DUNS_HQ_NUM 
			,PPS_DUNS_HQ_NAME 
			,PPS_ULT_DUNS 
			,PPS_ULT_DUNS_NAME 
			,PPS_SIC_CODE 
			,PPS_SIC_CODE_DESCRIPTION 
			,PPS_INDUSTRY_MKTG 
			,VALIDATION_STATUS 
			,VALIDATION_STATUS_MESSAGE 
			,LFS_SF_ACCOUNT_ID 
			,LFS_SF_STATUS_FLAG 
			,LFS_SF_STATUS_MESSAGE 
			,LFS_SF_LAST_UPDATE_DATE 
			,PPS_SF_ACCOUNT_ID 
			,PPS_SF_STATUS_FLAG 
			,PPS_SF_STATUS_MESSAGE 
			,PPS_SF_LAST_UPDATE_DATE) SELECT LEGACY_REF_ID 
											,ACCOUNT_NAME 
											,ACCOUNT_TYPE 
											,LOCATION_NUMBER 
											,ADDRESS 
											,TOWN 
											,COUNTY 
											,COUNTRY 
											,POSTAL_CODE 
											,PO_BOX_NUMBER 
											,PO_BOX_POSTAL_CODE 
											,PO_BOX_TOWN 
											,FAX 
											,TELEPHONE 
											,EDIN 
											,CREDIT_STOP 
											,GEO_AREA 
											,IS_DELETED 
											,DOES_BUSINESS_AS_DBA 
											,COMMON_RESRV_IND 
											,VISIT_ADDR 
											,INSTALL_ADDR 
											,DELIVERY_ADDR 
											,INVOICE_ADDR 
											,CONTRACT 
											,METER_READ 
											,TECHNICAL 
											,INTERNAL 
											,SOL_1 
											,SOURCE 
											,LFSBU 
											,DPSBU 
											,PPSBU 
											,SUPBU 
											,DUNS_NUMBER 
											,DUNS_HQ_NUM 
											,DUNS_HQ_NAME 
											,ULT_DUNS 
											,ULT_DUNS_NAME 
											,SIC_CODE 
											,SIC_CODE_DESCRIPTION 
											,LAST_UPDATE_DATE 
											,LFS_ACCOUNT_OWNER_ID 
											,LFS_OWNER_EMPLOYEE_NUMBER 
											,LFS_SFDC_NUMBER 
											,LFS_DUNS_NUMBER 
											,LFS_DUNS_HQ_NUM 
											,LFS_DUNS_HQ_NAME 
											,LFS_ULT_DUNS 
											,LFS_ULT_DUNS_NAME 
											,LFS_SIC_CODE 
											,LFS_SIC_CODE_DESCRIPTION 
											,LFS_INDUSTRY_MKTG 
											,PPS_ACCOUNT_OWNER_ID 
											,PPS_OWNER_EMPLOYEE_NUMBER 
											,PPS_SFDC_NUMBER 
											,PPS_DUNS_NUMBER 
											,PPS_DUNS_HQ_NUM 
											,PPS_DUNS_HQ_NAME 
											,PPS_ULT_DUNS 
											,PPS_ULT_DUNS_NAME 
											,PPS_SIC_CODE 
											,PPS_SIC_CODE_DESCRIPTION 
											,PPS_INDUSTRY_MKTG 
											,VALIDATION_STATUS 
											,VALIDATION_STATUS_MESSAGE 
											,LFS_SF_ACCOUNT_ID 
											,LFS_SF_STATUS_FLAG 
											,LFS_SF_STATUS_MESSAGE 
											,LFS_SF_LAST_UPDATE_DATE 
											,PPS_SF_ACCOUNT_ID 
											,PPS_SF_STATUS_FLAG 
											,PPS_SF_STATUS_MESSAGE 
											,PPS_SF_LAST_UPDATE_DATE
--										FROM canon_e633_cust_site_stg_tbl@canmth.cusa.canon.com
                 --   FROM canon_e633_cust_site_N01_tbl
                  --  FROM canon_e633_cust_site_stg_tbl
				  	FROM canon_e633_cust_site_stg_tbl@EBS11I.CUSA.CANON.COM
										WHERE 1=1;
				dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_cust_site_conv_tbl from CANMTH');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_cust_site_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);						
        
--        UPDATE canon_e633_cust_site_conv_tbl
        UPDATE canon_e633_cust_site_conv_tbl
          SET old_location_number = location_number
              ,old_legacy_ref_id = legacy_ref_id;
        dbms_output.put_line('Backup of IDs completed');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','copied location_number and legacy_ref_id''s',NULL,NULL,NULL,NULL);      
              
										
				--map the Ids from S21 table
--				MERGE INTO canon_e633_cust_site_conv_tbl e633
/*        MERGE INTO canon_e633_cust_site_conv_tbl e633
				USING (
             SELECT ds_xref_acct_cd as s21Legacy_ref_id , 
                    loc_num as s21_loc_num
               From ds_xref_acct xref 
               WHERE xref.ds_xref_acct_tp_cd = '40' 
					   ) S21
				 ON (e633.legacy_ref_id = s21Legacy_ref_id)
				WHEN MATCHED THEN UPDATE
					SET location_number = s21.s21_loc_num
					    ,conv_load_date = SYSDATE
              ,process_flag = 'P'
              ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
              ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
              ,process_comments = 'Updated mappings from S21.' ;
            --  ,conversion_id = s21.conversion_id
            -- ,database = s21.database_name;
             -- ,source_name = s21.SOURCE   ;*/
        MERGE INTO canon_e633_cust_site_conv_tbl e633
          USING (SELECT database_name, 
                        conversion_id, 
                        SOURCE, 
                        old_location_id, 
                        old_acct_num, 
                        s21_acct_num, 
                        s21_loc_num, 
                        s21_bill_to_cust_cd, 
                        s21_ship_to_cust_cd
                  FROM S21_CSA_REP.TMP_C182_CONV_CUST_ACCT_XREF
                  WHERE database_name = 'S21-LFSPPS'
                    AND SOURCE = 'SAP'
                    AND s21_ship_to_cust_cd IS NOT NULL
                  UNION
                    SELECT database_name, 
                           conversion_id, 
                           SOURCE, 
                           old_location_id, 
                           old_acct_num, 
                           s21_acct_num, 
                           s21_loc_num, 
                           s21_bill_to_cust_cd, 
                           s21_ship_to_cust_cd
                      FROM S21_CSA_REP.tmp_c182_conv_pros_xref
                    WHERE database_name = 'S21-LFSPPS'
                      AND SOURCE = 'SAP')s21
                ON(e633.old_location_number = s21.old_location_id)
               WHEN MATCHED THEN UPDATE
                  SET location_number = s21.s21_loc_num
                      ,conv_load_date = SYSDATE
                      ,process_flag = 'P'
                      ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,process_comments = 'Updated mappings from S21.' 
                      ,conversion_id = s21.conversion_id
                      ,database = s21.database_name
                      ,source_name = s21.SOURCE   ; 
			    dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);
  
				--once IDs updated update the legacy_ref_id
--        UPDATE canon_e633_cust_site_conv_tbl e633
				UPDATE canon_e633_cust_site_conv_tbl e633
				   SET legacy_ref_id = ltrim(legacy_ref_id, '0')
           WHERE process_flag = 'P'    ;
			dbms_output.put_line('Updated '||SQL%rowcount||' legacy_ref_id''s');
			canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Updated '||SQL%ROWCOUNT||' legacy_ref_id''s',NULL,NULL,NULL,NULL);
			
			-- records where the LFS and PPS location_number are not mapped.
--				UPDATE canon_e633_cust_site_conv_tbl e633
        UPDATE canon_e633_cust_site_conv_tbl e633
				   SET process_flag = 'E' 
--              ,location_number = location_number || 'SAPNOTCONVTD' -- as of Aug conversions no need of appending as these are not uploaded to SFDC
--              ,location_number = location_number || 'Z' -- due to column length changed SAPCUSTNOTCONVTD to Z
--               ,legacy_ref_id = legacy_ref_id || 'SAPNOTCONVTD'
--               ,legacy_ref_id = legacy_ref_id || 'Z' -- due to column length 
--               ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_account_id IS NOT NULL THEN 'U' END
--               ,pps_sf_id_upd_flg = CASE WHEN pps_sf_account_id IS NOT NULL THEN 'U' END
               ,process_comments = 'Could not find a mapping in S21.' 
               ,conversion_id = 6000
               ,DATABASE = 'S21-LFSPPS'
               ,source_name = 'SAP'   
				  WHERE process_flag IS NULL ;            
			dbms_output.put_line('could not find mappings for '||SQL%rowcount);
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find LFS/PPS mappings in S21',NULL,NULL,NULL,NULL);
      
      -- copy the converted data to staging table for S21 to copy.
      EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_cust_site_stg_tbl';
      dbms_output.put_line('Table canon_e633_cust_site_stg Truncated.');
			canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Table canon_e633_cust_site_stg Truncated.',NULL,NULL,NULL,NULL);
      
      INSERT INTO canon_e633_cust_site_stg_tbl (
			LEGACY_REF_ID 
			,ACCOUNT_NAME 
			,ACCOUNT_TYPE 
			,LOCATION_NUMBER 
			,ADDRESS 
			,TOWN 
			,COUNTY 
			,COUNTRY 
			,POSTAL_CODE 
			,PO_BOX_NUMBER 
			,PO_BOX_POSTAL_CODE 
			,PO_BOX_TOWN 
			,FAX 
			,TELEPHONE 
			,EDIN 
			,CREDIT_STOP 
			,GEO_AREA 
			,IS_DELETED 
			,DOES_BUSINESS_AS_DBA 
			,COMMON_RESRV_IND 
			,VISIT_ADDR 
			,INSTALL_ADDR 
			,DELIVERY_ADDR 
			,INVOICE_ADDR 
			,CONTRACT 
			,METER_READ 
			,TECHNICAL 
			,INTERNAL 
			,SOL_1 
			,SOURCE 
			,LFSBU 
			,DPSBU 
			,PPSBU 
			,SUPBU 
			,DUNS_NUMBER 
			,DUNS_HQ_NUM 
			,DUNS_HQ_NAME 
			,ULT_DUNS 
			,ULT_DUNS_NAME 
			,SIC_CODE 
			,SIC_CODE_DESCRIPTION 
			,LAST_UPDATE_DATE 
			,LFS_ACCOUNT_OWNER_ID 
			,LFS_OWNER_EMPLOYEE_NUMBER 
			,LFS_SFDC_NUMBER 
			,LFS_DUNS_NUMBER 
			,LFS_DUNS_HQ_NUM 
			,LFS_DUNS_HQ_NAME 
			,LFS_ULT_DUNS 
			,LFS_ULT_DUNS_NAME 
			,LFS_SIC_CODE 
			,LFS_SIC_CODE_DESCRIPTION 
			,LFS_INDUSTRY_MKTG 
			,PPS_ACCOUNT_OWNER_ID 
			,PPS_OWNER_EMPLOYEE_NUMBER 
			,PPS_SFDC_NUMBER 
			,PPS_DUNS_NUMBER 
			,PPS_DUNS_HQ_NUM 
			,PPS_DUNS_HQ_NAME 
			,PPS_ULT_DUNS 
			,PPS_ULT_DUNS_NAME 
			,PPS_SIC_CODE 
			,PPS_SIC_CODE_DESCRIPTION 
			,PPS_INDUSTRY_MKTG 
			,VALIDATION_STATUS 
			,VALIDATION_STATUS_MESSAGE 
			,LFS_SF_ACCOUNT_ID 
			,LFS_SF_STATUS_FLAG 
			,LFS_SF_STATUS_MESSAGE 
			,LFS_SF_LAST_UPDATE_DATE 
			,PPS_SF_ACCOUNT_ID 
			,PPS_SF_STATUS_FLAG 
			,PPS_SF_STATUS_MESSAGE 
			,PPS_SF_LAST_UPDATE_DATE) SELECT LEGACY_REF_ID 
											,ACCOUNT_NAME 
											,ACCOUNT_TYPE 
											,LOCATION_NUMBER 
											,ADDRESS 
											,TOWN 
											,COUNTY 
											,COUNTRY 
											,POSTAL_CODE 
											,PO_BOX_NUMBER 
											,PO_BOX_POSTAL_CODE 
											,PO_BOX_TOWN 
											,FAX 
											,TELEPHONE 
											,EDIN 
											,CREDIT_STOP 
											,GEO_AREA 
											,IS_DELETED 
											,DOES_BUSINESS_AS_DBA 
											,COMMON_RESRV_IND 
											,VISIT_ADDR 
											,INSTALL_ADDR 
											,DELIVERY_ADDR 
											,INVOICE_ADDR 
											,CONTRACT 
											,METER_READ 
											,TECHNICAL 
											,INTERNAL 
											,SOL_1 
											,SOURCE 
											,LFSBU 
											,DPSBU 
											,PPSBU 
											,SUPBU 
											,DUNS_NUMBER 
											,DUNS_HQ_NUM 
											,DUNS_HQ_NAME 
											,ULT_DUNS 
											,ULT_DUNS_NAME 
											,SIC_CODE 
											,SIC_CODE_DESCRIPTION 
											,LAST_UPDATE_DATE 
											,LFS_ACCOUNT_OWNER_ID 
											,LFS_OWNER_EMPLOYEE_NUMBER 
											,LFS_SFDC_NUMBER 
											,LFS_DUNS_NUMBER 
											,LFS_DUNS_HQ_NUM 
											,LFS_DUNS_HQ_NAME 
											,LFS_ULT_DUNS 
											,LFS_ULT_DUNS_NAME 
											,LFS_SIC_CODE 
											,LFS_SIC_CODE_DESCRIPTION 
											,LFS_INDUSTRY_MKTG 
											,PPS_ACCOUNT_OWNER_ID 
											,PPS_OWNER_EMPLOYEE_NUMBER 
											,PPS_SFDC_NUMBER 
											,PPS_DUNS_NUMBER 
											,PPS_DUNS_HQ_NUM 
											,PPS_DUNS_HQ_NAME 
											,PPS_ULT_DUNS 
											,PPS_ULT_DUNS_NAME 
											,PPS_SIC_CODE 
											,PPS_SIC_CODE_DESCRIPTION 
											,PPS_INDUSTRY_MKTG 
											,VALIDATION_STATUS 
											,VALIDATION_STATUS_MESSAGE 
											,LFS_SF_ACCOUNT_ID 
											,LFS_SF_STATUS_FLAG 
											,LFS_SF_STATUS_MESSAGE 
											,LFS_SF_LAST_UPDATE_DATE 
											,PPS_SF_ACCOUNT_ID 
											,PPS_SF_STATUS_FLAG 
											,PPS_SF_STATUS_MESSAGE 
											,pps_sf_last_update_date
--										FROM canon_e633_cust_site_conv_tbl
                    FROM canon_e633_cust_site_conv_tbl
                    WHERE process_flag = 'P';
         dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_cust_site_stg_tbl from conversion table');
				canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_cust_site_stg_tbl from conversion table',NULL,NULL,NULL,NULL);	
        
        COMMIT;        
EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		errbuff := sqlerrm;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
END load_customers;

PROCEDURE load_ib(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_ib';

BEGIN
	retcode := 0;
 
 EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_ib_conv_tbl';
 
  --extract data from CANMTH
  INSERT INTO canon_e633_ib_conv_tbl (SVC_MACH_MSTR_PK , 
                  INSTALL_CUSTOMER,
                  CONFIG_NBR,
                  CONFIG_TYPE,
                  COMMERCIAL_NAME,
                  MACHINE_BUS_UNIT,
                  MODEL_SERIES,
                  MODEL_GROUP,
                  MODEL_GROUP_DESC,
                  TECHNOLOGY_CLASS,
                  VOLUME_CLASS, 
                  MARKETING_PROD_GP,
                  SERIAL_NUMBER,
                  INSTALL_DATE,
                  LIFE_CYCLE_STAT,
                  INSTAL,
                  INSTALL_CODE,
                  TYPE_OF_SALE,
                  HW_CONT_DURATION,
                  HW_CONT_STR_DATE,
                  HW_CONT_EXP_DATE,
                  HW_CONT_END_DATE,
                  FS_CONT_DURATION,
                  FS_CONT_STR_DATE,
                  FS_CONT_EXP_DATE,
                  FS_CONT_END_DATE,
                  WARR_END_DATE,
                  WARRANTY_DURATN,
                  WARR_START_DATE,
                  DATE_LAST_PRD_MAN,
                  CONFIG_INV_REF,
                  COST_ALLOCATE_REF,
                  SERVICE_PO_NBR,
                  AVG_CPY_VOL_PER_M,
                  HRDWARE_CNT_NAME,
                  SUPPLY_TYPE,
                  PROMO_CODE_1,
                  PROMO_CODE_2,
                  PROMO_CODE_3,
                  PROMO_CODE_4,
                  PROMO_CODE_5,
                  PROMO_CODE_6,
                  PROMO_CODE_7,
                  HW_CONT_EXP_FLAG,
                  MARKETING_ORDER_T,
                  ORDER_TYPE,
                  LAST_METER_READ,
                  LST_MTR_READ_DT,
                  WARRANTY_TYPE,
                  REMOVAL_DATE,
                  ORDER_NUMBER,
                  INVOICE_ADDRESS,
                  OFSI_FLAG,
                  INACTIVE_DATE,
                  SOURCE,
                  SELLING_BU,
                  SOLD_TO_ADDRESS,
                  FREE_TO_USE_1,
                  FREE_TO_USE_2,
                  FREE_TO_USE_3,
                  FREE_TO_USE_4,
                  FREE_TO_USE_5,
                  FREE_TO_USE_6,
                  LFS_SF_ACCOUNT_ID,
                  LFS_SF_SOLDTO_ACCOUNT_ID,
                  LFS_SF_IB_ID,
                  LFS_SF_STATUS_FLAG,
                  LFS_SF_STATUS_MESSAGE,
                  LFS_LAST_UPDATE_DATE, 
                  PPS_SF_IB_ID,
                  PPS_SF_STATUS_FLAG,
                  PPS_SF_STATUS_MESSAGE,
                  PPS_LAST_UPDATE_DATE, 
                  LFSBU,
                  PPSBU,
                  DPSBU,
                  PPS_SF_ACCOUNT_ID,
                  PPS_SF_SOLDTO_ACCOUNT_ID,
                  insert_flag,
                  LOAD_DATE) SELECT NULL , 
                                      INSTALL_CUSTOMER,
                                      CONFIG_NBR,
                                      CONFIG_TYPE,
                                      COMMERCIAL_NAME,
                                      MACHINE_BUS_UNIT,
                                      MODEL_SERIES,
                                      MODEL_GROUP,
                                      MODEL_GROUP_DESC,
                                      TECHNOLOGY_CLASS,
                                      VOLUME_CLASS, 
                                      MARKETING_PROD_GP,
                                      SERIAL_NUMBER,
                                      INSTALL_DATE,
                                      LIFE_CYCLE_STAT,
                                      instal,
                                      INSTALL_CODE,     
                                      TYPE_OF_SALE,
                                      HW_CONT_DURATION,
                                      HW_CONT_STR_DATE,
                                      HW_CONT_EXP_DATE,
                                      HW_CONT_END_DATE,
                                      FS_CONT_DURATION,
                                      FS_CONT_STR_DATE,
                                      FS_CONT_EXP_DATE,
                                      FS_CONT_END_DATE,
                                      WARR_END_DATE,
                                      WARRANTY_DURATN,
                                      WARR_START_DATE,
                                      DATE_LAST_PRD_MAN,
                                      CONFIG_INV_REF,
                                      COST_ALLOCATE_REF,
                                      SERVICE_PO_NBR,
                                      AVG_CPY_VOL_PER_M,
                                      HRDWARE_CNT_NAME,
                                      SUPPLY_TYPE,
                                      PROMO_CODE_1,
                                      PROMO_CODE_2,
                                      PROMO_CODE_3,
                                      PROMO_CODE_4,
                                      PROMO_CODE_5,
                                      PROMO_CODE_6,
                                      PROMO_CODE_7,
                                      HW_CONT_EXP_FLAG,
                                      MARKETING_ORDER_T,
                                      ORDER_TYPE,
                                      LAST_METER_READ,
                                      LST_MTR_READ_DT,
                                      WARRANTY_TYPE,
                                      REMOVAL_DATE,
                                      ORDER_NUMBER,
                                      INVOICE_ADDRESS,
                                      OFSI_FLAG,
                                      INACTIVE_DATE,
                                      SOURCE,
                                      SELLING_BU,
                                      SOLD_TO_ADDRESS,
                                      FREE_TO_USE_1,
                                      FREE_TO_USE_2,
                                      FREE_TO_USE_3,
                                      FREE_TO_USE_4,
                                      FREE_TO_USE_5,
                                      FREE_TO_USE_6,
                                      LFS_SF_ACCOUNT_ID,
                                      LFS_SF_SOLDTO_ACCOUNT_ID,
                                      LFS_SF_IB_ID,
                                      LFS_SF_STATUS_FLAG,
                                      LFS_SF_STATUS_MESSAGE,
                                      LFS_LAST_UPDATE_DATE, 
                                      PPS_SF_IB_ID,
                                      PPS_SF_STATUS_FLAG,
                                      PPS_SF_STATUS_MESSAGE,
                                      PPS_LAST_UPDATE_DATE, 
                                      LFSBU,
                                      PPSBU,
                                      DPSBU,
                                      PPS_SF_ACCOUNT_ID,
                                      PPS_SF_SOLDTO_ACCOUNT_ID,
                                      insert_flag,
                                      load_date
--                                FROM canon_e633_ib_stg_tbl@canmth.cusa.canon.com
                    --            FROM canon_e633_ib_N01_tbl
								   FROM canon_e633_ib_stg_tbl@EBS11I.CUSA.CANON.COM
                               WHERE 1=1 ;
    dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_ib_conv_tbl from CANMTH');
--    dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_ib_conv_it from CANMTH');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_ib_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);						                               
        commit;
        --update the config#
        --this is temporary till S21 add old serial number to the mapping table
        --this is because the leading zeros are truncated in S21 conversions and serial# has leading 0's
--        UPDATE canon_e633_ib_conv_tbl
        UPDATE canon_e633_ib_conv_tbl
           SET old_config_nbr = config_nbr
               ,old_serial_number = serial_number;
        COMMIT;
        dbms_output.put_line('Backup of IDs completed');
        
      /*  -- update records whose customer not converted
--        UPDATE canon_e633_ib_conv_tbl e633
        UPDATE canon_e633_ib_conv_tbl e633
           SET process_flag =  'E' 
               ,process_comments = 'Corresponding SAP Customer not converted.' 
--              no need of these in Aug conv. as we are not uploading these to SFDC               
--               ,lfs_sf_id_upd_flg = CASE WHEN e633.lfs_sf_ib_id IS NOT NULL THEN 'U' END
--               ,pps_sf_id_upd_flg = CASE WHEN e633.pps_sf_ib_id IS NOT NULL THEN 'U' END
--               ,config_nbr = config_nbr || 'SAPNOTCONVTD'
--               ,svc_mach_mstr_pk = config_nbr ||'SAPNOTCONVTD'
               ,conversion_id = '6000'
              ,DATABASE = 'S21-LFSPPS'
              ,source_name = 'OMSI' 
          WHERE process_flag IS NULL
            AND (lfs_sf_account_id IN (SELECT lfs_sf_account_id
                                        FROM canon_e633_cust_site_conv_tbl
                                        WHERE process_flag = 'E')
            OR
                pps_sf_account_id IN (SELECT pps_sf_account_id
                                        FROM canon_e633_cust_site_conv_tbl
                                        WHERE process_flag = 'E'));
                                        
    dbms_output.put_line('Updated '||SQL%rowcount||' records for Customer not converted');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Updated '||SQL%rowcount||' records for Customer not converted',NULL,NULL,NULL,NULL);                                                
        COMMIT;
        */
--        MERGE INTO s21_csa_extn.canon_e633_ib_conv_tbl e633
        MERGE INTO S21_CSA_EXTN.CANON_E633_IB_CONV_tbl E633
           USING (SELECT /*+ no_merge */  C183.SERIAL_NUMBER,
                         C183.SVC_MACH_MSTR_PK,
                         C183.CONFIG_NUMBER,
                         SMM.IND_CUR_LOC_NUM INSTALL_CUSTOMER,
                         SMM.SVC_CONFIG_MSTR_PK NEW_CONFIG_NBR,
                         C183.ORIG_SERIAL_NUMBER
--                    FROM S21_CSA_REP.CANON_C183_SVC_MACH_EXT_TBL@CSAQA.CUSA.CANON.COM C183,
--                         S21_CSA_EXTN.svc_mach_mstr smm
                    FROM S21_CSA_REP.canon_c183_svc_mach_ext_tbl c183,
                         S21_CSA_APPS.SVC_MACH_MSTR SMM
                   WHERE     DATA_SOURCE = 'OMSI'
				      AND smm.ezcancelflag = '0'
					  AND smm.glbl_cmpy_cd = 'ADB'
                         AND C183.SVC_MACH_MSTR_PK = SMM.SVC_MACH_MSTR_PK
                         AND c183.seq_no = 1) s21
            ON (E633.OLD_CONFIG_NBR = S21.CONFIG_NUMBER and procesS_flag IS NULL)
          WHEN MATCHED THEN UPDATE 
             SET  E633.SVC_MACH_MSTR_PK = S21.SVC_MACH_MSTR_PK,
                E633.CONFIG_NBR = S21.NEW_CONFIG_NBR,
                E633.SERIAL_NUMBER = S21.SERIAL_NUMBER,
                E633.INSTALL_CUSTOMER = S21.INSTALL_CUSTOMER,
                CONV_LOAD_DATE = SYSDATE,
--                PROCESS_FLAG = 'P',
                PPS_SF_ID_UPD_FLG = CASE WHEN E633.PPS_SF_IB_ID IS NOT NULL THEN 'U' ELSE NULL END,
                LFS_SF_ID_UPD_FLG = CASE WHEN E633.LFS_SF_IB_ID IS NOT NULL THEN 'U' ELSE NULL END,
                PROCESS_COMMENTS = 'Updated mappings from S21.',
                CONVERSION_ID = '6000',
                DATABASE = 'S21-LFSPPS',
                SOURCE_NAME = 'OMSI';
        
      dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);        
        COMMIT;
        
        UPDATE canon_e633_ib_conv_tbl
--        UPDATE canon_e633_ib_conv_it
          SET process_flag = 'P'
          where PROCESS_COMMENTS = 'Updated mappings from S21.';
        COMMIT;
        
			-- records where the LFS/PPS serial_number mapping is not updated.
				UPDATE canon_e633_ib_conv_tbl e633
--        UPDATE canon_e633_ib_conv_it e633
				   SET process_flag =  'E'             
				       ,process_comments = 'Could not find a mapping in S21.' 
--                Aug conversions - no need of these as we are not uploading to SFDC
--               ,svc_mach_mstr_pk = config_nbr || 'SAPNOTCONVTD'
--               ,config_nbr = config_nbr || 'SAPNOTCONVTD'
--               ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_ib_id IS NOT NULL THEN 'U' END
--               ,pps_sf_id_upd_flg = CASE WHEN pps_sf_ib_id IS NOT NULL THEN 'U' END
               ,conversion_id = '6000'
              ,DATABASE = 'S21-LFSPPS'
              ,source_name = 'OMSI'  
				  WHERE process_flag IS NULL;

			dbms_output.put_line('No mapping found in S21 for '||SQL%rowcount||' LFS/PPS Serials''s');
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find LFS/PPS mapping in S21',NULL,NULL,NULL,NULL);
      commit;
      
       
      --copy data to staging table for S21 to copy to pre-prod
      EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_ib_stg_tbl' ;
      dbms_output.put_line('Table canon_e633_ib_stg Truncated.');
			canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Table canon_e633_cust_site_stg Truncated.',NULL,NULL,NULL,NULL);
      
      INSERT INTO canon_e633_ib_stg_tbl (SVC_MACH_MSTR_PK , 
                  INSTALL_CUSTOMER,
                  CONFIG_NBR,
                  CONFIG_TYPE,
                  COMMERCIAL_NAME,
                  MACHINE_BUS_UNIT,
                  MODEL_SERIES,
                  MODEL_GROUP,
                  MODEL_GROUP_DESC,
                  TECHNOLOGY_CLASS,
                  VOLUME_CLASS, 
                  MARKETING_PROD_GP,
                  SERIAL_NUMBER,
                  INSTALL_DATE,
                  LIFE_CYCLE_STAT,
                  INSTAL,
                  INSTALL_CODE,
                  TYPE_OF_SALE,
                  HW_CONT_DURATION,
                  HW_CONT_STR_DATE,
                  HW_CONT_EXP_DATE,
                  HW_CONT_END_DATE,
                  FS_CONT_DURATION,
                  FS_CONT_STR_DATE,
                  FS_CONT_EXP_DATE,
                  FS_CONT_END_DATE,
                  WARR_END_DATE,
                  WARRANTY_DURATN,
                  WARR_START_DATE,
                  DATE_LAST_PRD_MAN,
                  CONFIG_INV_REF,
                  COST_ALLOCATE_REF,
                  SERVICE_PO_NBR,
                  AVG_CPY_VOL_PER_M,
                  HRDWARE_CNT_NAME,
                  SUPPLY_TYPE,
                  PROMO_CODE_1,
                  PROMO_CODE_2,
                  PROMO_CODE_3,
                  PROMO_CODE_4,
                  PROMO_CODE_5,
                  PROMO_CODE_6,
                  PROMO_CODE_7,
                  HW_CONT_EXP_FLAG,
                  MARKETING_ORDER_T,
                  ORDER_TYPE,
                  LAST_METER_READ,
                  LST_MTR_READ_DT,
                  WARRANTY_TYPE,
                  REMOVAL_DATE,
                  ORDER_NUMBER,
                  INVOICE_ADDRESS,
                  OFSI_FLAG,
                  INACTIVE_DATE,
                  SOURCE,
                  SELLING_BU,
                  SOLD_TO_ADDRESS,
                  FREE_TO_USE_1,
                  FREE_TO_USE_2,
                  FREE_TO_USE_3,
                  FREE_TO_USE_4,
                  FREE_TO_USE_5,
                  FREE_TO_USE_6,
                  LFS_SF_ACCOUNT_ID,
                  LFS_SF_SOLDTO_ACCOUNT_ID,
                  LFS_SF_IB_ID,
                  LFS_SF_STATUS_FLAG,
                  LFS_SF_STATUS_MESSAGE,
                  LFS_LAST_UPDATE_DATE, 
                  PPS_SF_IB_ID,
                  PPS_SF_STATUS_FLAG,
                  PPS_SF_STATUS_MESSAGE,
                  PPS_LAST_UPDATE_DATE, 
                  LFSBU,
                  PPSBU,
                  DPSBU,
                  PPS_SF_ACCOUNT_ID,
                  PPS_SF_SOLDTO_ACCOUNT_ID,
                  insert_flag,
                  LOAD_DATE) SELECT SVC_MACH_MSTR_PK , 
                                      INSTALL_CUSTOMER,
                                      CONFIG_NBR,
                                      CONFIG_TYPE,
                                      COMMERCIAL_NAME,
                                      MACHINE_BUS_UNIT,
                                      MODEL_SERIES,
                                      MODEL_GROUP,
                                      MODEL_GROUP_DESC,
                                      TECHNOLOGY_CLASS,
                                      VOLUME_CLASS, 
                                      MARKETING_PROD_GP,
                                      SERIAL_NUMBER,
                                      INSTALL_DATE,
                                      LIFE_CYCLE_STAT,
                                      instal,
                                      INSTALL_CODE,     
                                      TYPE_OF_SALE,
                                      HW_CONT_DURATION,
                                      HW_CONT_STR_DATE,
                                      HW_CONT_EXP_DATE,
                                      HW_CONT_END_DATE,
                                      FS_CONT_DURATION,
                                      FS_CONT_STR_DATE,
                                      FS_CONT_EXP_DATE,
                                      FS_CONT_END_DATE,
                                      WARR_END_DATE,
                                      WARRANTY_DURATN,
                                      WARR_START_DATE,
                                      DATE_LAST_PRD_MAN,
                                      CONFIG_INV_REF,
                                      COST_ALLOCATE_REF,
                                      SERVICE_PO_NBR,
                                      AVG_CPY_VOL_PER_M,
                                      HRDWARE_CNT_NAME,
                                      SUPPLY_TYPE,
                                      PROMO_CODE_1,
                                      PROMO_CODE_2,
                                      PROMO_CODE_3,
                                      PROMO_CODE_4,
                                      PROMO_CODE_5,
                                      PROMO_CODE_6,
                                      PROMO_CODE_7,
                                      HW_CONT_EXP_FLAG,
                                      MARKETING_ORDER_T,
                                      ORDER_TYPE,
                                      LAST_METER_READ,
                                      LST_MTR_READ_DT,
                                      WARRANTY_TYPE,
                                      REMOVAL_DATE,
                                      ORDER_NUMBER,
                                      INVOICE_ADDRESS,
                                      OFSI_FLAG,
                                      INACTIVE_DATE,
                                      SOURCE,
                                      SELLING_BU,
                                      SOLD_TO_ADDRESS,
                                      FREE_TO_USE_1,
                                      FREE_TO_USE_2,
                                      FREE_TO_USE_3,
                                      FREE_TO_USE_4,
                                      FREE_TO_USE_5,
                                      FREE_TO_USE_6,
                                      LFS_SF_ACCOUNT_ID,
                                      LFS_SF_SOLDTO_ACCOUNT_ID,
                                      LFS_SF_IB_ID,
                                      LFS_SF_STATUS_FLAG,
                                      LFS_SF_STATUS_MESSAGE,
                                      LFS_LAST_UPDATE_DATE, 
                                      PPS_SF_IB_ID,
                                      PPS_SF_STATUS_FLAG,
                                      PPS_SF_STATUS_MESSAGE,
                                      PPS_LAST_UPDATE_DATE, 
                                      LFSBU,
                                      PPSBU,
                                      DPSBU,
                                      PPS_SF_ACCOUNT_ID,
                                      PPS_SF_SOLDTO_ACCOUNT_ID,
                                      insert_flag,
                                      load_date
--                                FROM canon_e633_ib_conv_tbl
                                FROM canon_e633_ib_conv_tbl
                               WHERE process_flag = 'P' ;
              dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_ib_stg_tbl from conversion table');
				canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_ib_stg_tbl from conversion table',NULL,NULL,NULL,NULL);	
        
        COMMIT;                
  EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		errbuff := sqlerrm;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
END load_ib;

PROCEDURE load_config(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_config';

BEGIN
	retcode := 0;
  
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_config_conv_tbl' ;
      dbms_output.put_line('Table canon_e633_config_conv_tbl Truncated.');
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Table canon_e633_config_conv_tbl Truncated.',NULL,NULL,NULL,NULL);
      
  --extract data from CANMTH
  INSERT INTO canon_e633_config_conv_tbl(CONFIG_NUMBER, 
                                SVC_MACH_MSTR_PK, 
                                COMPONENT_SEQ_NUMBER, 
                                SERIAL_NO, 
                                LIFECYCLE_STATUS, 
                                COMPONENT_TYPE, 
                                ITEM_TYPE_CODE, 
                                INSDAT, 
                                AMSDAT, 
                                LAST_AMEND_USER_ID, 
                                TECHNICAL_EXCH_SEQ_NUMBER, 
                                RMVDAT, 
                                INSTALLATION_CUSTOMER_NO, 
                                COMMERCIAL_NAME_LANG_1, 
                                SOURCE, 
                                UNIQUE_KEY, 
                                LFS_SF_IB_ID, 
                                LFS_SF_ACCOUNT_ID, 
                                LFS_SF_CONFIG_ID, 
                                LFS_SF_STATUS_FLAG, 
                                LFS_SF_STATUS_MESSAGE, 
                                LFS_LAST_UPDATE_DATE, 
                                PPS_SF_IB_ID, 
                                PPS_SF_ACCOUNT_ID, 
                                PPS_SF_CONFIG_ID, 
                                PPS_SF_STATUS_FLAG, 
                                PPS_SF_STATUS_MESSAGE, 
                                PPS_LAST_UPDATE_DATE, 
                                lfsbu, 
                                dpsbu, 
                                ppsbu) SELECT config_number, 
                                            NULL, 
                                            COMPONENT_SEQ_NUMBER, 
                                            SERIAL_NO, 
                                            LIFECYCLE_STATUS, 
                                            COMPONENT_TYPE, 
                                            ITEM_TYPE_CODE, 
                                            INSDAT, 
                                            AMSDAT, 
                                            LAST_AMEND_USER_ID, 
                                            TECHNICAL_EXCH_SEQ_NUMBER, 
                                            RMVDAT, 
                                            INSTALLATION_CUSTOMER_NO, 
                                            COMMERCIAL_NAME_LANG_1, 
                                            SOURCE, 
                                            UNIQUE_KEY, 
                                            LFS_SF_IB_ID, 
                                            LFS_SF_ACCOUNT_ID, 
                                            LFS_SF_CONFIG_ID, 
                                            LFS_SF_STATUS_FLAG, 
                                            LFS_SF_STATUS_MESSAGE, 
                                            LFS_LAST_UPDATE_DATE, 
                                            PPS_SF_IB_ID, 
                                            PPS_SF_ACCOUNT_ID, 
                                            PPS_SF_CONFIG_ID, 
                                            PPS_SF_STATUS_FLAG, 
                                            PPS_SF_STATUS_MESSAGE, 
                                            PPS_LAST_UPDATE_DATE, 
                                            LFSBU, 
                                            dpsbu, 
                                            ppsbu 
--                                    FROM canon_e633_config_stg_tbl@canmth.cusa.canon.com;
                             --       FROM canon_e633_config_N01_tbl;
							          FROM canon_e633_config_stg_tbl@EBS11I.CUSA.CANON.COM;
      dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_config_conv_tbl from CANMTH');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_config_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);
        
       UPDATE canon_e633_config_conv_tbl
          SET old_config_number = config_number
              ,old_seq_number = component_seq_number;
      dbms_output.put_line('backup of IDs completed');
              
       UPDATE canon_e633_config_conv_tbl
          SET process_flag = 'E'
--              ,config_number = config_number ||'SAPIBNOTCONVTD'
--              ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_config_id IS NOT NULL THEN 'U' END
--              ,pps_sf_id_upd_flg = CASE WHEN pps_sf_config_id IS NOT NULL THEN 'U' END
              ,process_comments = 'Corresponding IB is not converted.'
--              ,UNIQUE_KEY = unique_key || 'SAPIBNOTCONVTD'
              ,conversion_id = '6000'
                ,DATABASE = 'S21-LFSPPS'
                ,source_name = 'OMSI' 
        WHERE process_flag IS NULL        
          AND old_config_number IN (SELECT old_config_nbr 
                                  FROM canon_e633_ib_conv_tbl
                                WHERE process_flag = 'E');
        dbms_output.put_line('Updated '||SQL%rowcount||' records for SAPIBNOTCONVTD');                                
            
        --map from S21 QA
        MERGE INTO canon_e633_config_conv_tbl e633
          USING(SELECT /*+ no_merge */ DISTINCT cm.cmcfno config_nbr
                       ,cm.cmcsqn seq_num
                       ,cm.cmsern cm_serial_number
                       ,cm.cmmtyp component_type
                       ,c183.old_config_number
                       ,smm.svc_mach_mstr_pk
                       ,smm.svc_config_mstr_pk
                       ,smm.ser_num
                       ,smm.mdse_cd
                       ,smm.svc_mach_sq_num
                       ,c183.old_config_number || smm.svc_mach_sq_num mapping_key
                  FROM S21_CSA_REP.cm
                       ,S21_CSA_REP.tmp_c183_config_pk_map c183
                       ,S21_CSA_APPS.svc_mach_mstr smm
                 WHERE 1=1
				    AND smm.ezcancelflag = '0'
					AND smm.glbl_cmpy_cd = 'ADB'
                    AND cm.cmcfno = c183.old_config_number(+)
                   AND c183.s21_svc_config_mstr_pk = smm.svc_config_mstr_pk(+)
                   AND cm.cmcsqn = smm.svc_mach_sq_num(+)
                   AND c183.database_name='S21-LFSPPS'
                   AND cm.cmlsta <> 'R'
				   --following is as suggested by Shutaro
				   AND cm.cmseq3 = (SELECT MIN(cmseq3)
                                     FROM s21_csa_rep.cm cm2
                                     WHERE cm2.cmcfno = cm.cmcfno
                                       and cm2.cmcsqn = cm.cmcsqn)  -- as suggested by Shutaro - QC29817
                   AND smm.SVC_MACH_MSTR_PK is not null
--                   and cm.CMCFNO=533036
                  ORDER BY cm.cmcfno,cm.cmcsqn ) s21
              ON(e633.unique_key = s21.mapping_key AND process_flag IS NULL)
          WHEN MATCHED THEN UPDATE
            SET e633.config_number = s21.svc_config_mstr_pk
                ,component_seq_number = s21.svc_mach_sq_num
                ,svc_mach_mstr_pk = s21.svc_mach_mstr_pk
--                ,unique_key = s21.svc_config_mstr_pk || s21.svc_mach_sq_num
                ,conv_load_date = SYSDATE
--                ,process_flag =  'P'
                ,pps_sf_id_upd_flg = CASE WHEN e633.pps_sf_config_id IS NOT NULL THEN 'U' END
                ,lfs_sf_id_upd_flg = CASE WHEN e633.lfs_sf_config_id IS NOT NULL THEN 'U' END
                ,process_comments =  'Updated mappings from S21.'
                ,conversion_id = '6000'
                ,DATABASE = 'S21-LFSPPS'
                ,source_name = 'OMSI' 
              ;

      dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);        
        UPDATE canon_e633_config_conv_tbl
          SET process_flag = 'P'
          WHERE process_comments =  'Updated mappings from S21.';
          
    -- records which cound not be mapped
        UPDATE canon_e633_config_conv_tbl e633
           SET process_flag = 'E' 
               ,process_comments =  'Could not find mapping in S21.' 
--               ,lfs_sf_id_upd_flg = CASE WHEN e633.lfs_sf_config_id IS NOT NULL THEN 'U' END
--               ,pps_sf_id_upd_flg = CASE WHEN e633.pps_sf_config_id IS NOT NULL THEN 'U' END
--               ,unique_key = unique_key || 'SAPNOTCONVTD'
               ,conversion_id = '6000'
              ,DATABASE = 'S21-LFSPPS'
              ,source_name = 'OMSI' 
          WHERE process_flag IS NULL;
            
    dbms_output.put_line('No mapping found in S21 for '||SQL%rowcount||' Config''s');
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find mapping in S21',NULL,NULL,NULL,NULL);
      
       --update unique_key values with new config_number for processed/mapped records
       UPDATE canon_e633_config_conv_tbl
         SET unique_key = config_number || component_seq_number
         WHERE process_flag = 'P';
        dbms_output.put_line('updated '||SQL%rowcount||' unique_key values');
				canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Updated '||SQL%ROWCOUNT||' unique_key values',NULL,NULL,NULL,NULL);        
        
			
			COMMIT;              
      
      
      EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_config_stg_tbl';
      dbms_output.put_line('canon_e633_config_stg_tbl truncated');
      
      INSERT INTO canon_e633_config_stg_tbl(CONFIG_NUMBER, 
                                SVC_MACH_MSTR_PK, 
                                COMPONENT_SEQ_NUMBER, 
                                SERIAL_NO, 
                                LIFECYCLE_STATUS, 
                                COMPONENT_TYPE, 
                                ITEM_TYPE_CODE, 
                                INSDAT, 
                                AMSDAT, 
                                LAST_AMEND_USER_ID, 
                                TECHNICAL_EXCH_SEQ_NUMBER, 
                                RMVDAT, 
                                INSTALLATION_CUSTOMER_NO, 
                                COMMERCIAL_NAME_LANG_1, 
                                SOURCE, 
                                UNIQUE_KEY, 
                                LFS_SF_IB_ID, 
                                LFS_SF_ACCOUNT_ID, 
                                LFS_SF_CONFIG_ID, 
                                LFS_SF_STATUS_FLAG, 
                                LFS_SF_STATUS_MESSAGE, 
                                LFS_LAST_UPDATE_DATE, 
                                PPS_SF_IB_ID, 
                                PPS_SF_ACCOUNT_ID, 
                                PPS_SF_CONFIG_ID, 
                                PPS_SF_STATUS_FLAG, 
                                PPS_SF_STATUS_MESSAGE, 
                                PPS_LAST_UPDATE_DATE, 
                                lfsbu, 
                                dpsbu, 
                                ppsbu) SELECT config_number, 
                                            svc_mach_mstr_pk, 
                                            COMPONENT_SEQ_NUMBER, 
                                            SERIAL_NO, 
                                            LIFECYCLE_STATUS, 
                                            COMPONENT_TYPE, 
                                            ITEM_TYPE_CODE, 
                                            INSDAT, 
                                            AMSDAT, 
                                            LAST_AMEND_USER_ID, 
                                            TECHNICAL_EXCH_SEQ_NUMBER, 
                                            RMVDAT, 
                                            INSTALLATION_CUSTOMER_NO, 
                                            COMMERCIAL_NAME_LANG_1, 
                                            SOURCE, 
                                            UNIQUE_KEY, 
                                            LFS_SF_IB_ID, 
                                            LFS_SF_ACCOUNT_ID, 
                                            LFS_SF_CONFIG_ID, 
                                            LFS_SF_STATUS_FLAG, 
                                            LFS_SF_STATUS_MESSAGE, 
                                            LFS_LAST_UPDATE_DATE, 
                                            PPS_SF_IB_ID, 
                                            PPS_SF_ACCOUNT_ID, 
                                            PPS_SF_CONFIG_ID, 
                                            PPS_SF_STATUS_FLAG, 
                                            PPS_SF_STATUS_MESSAGE, 
                                            PPS_LAST_UPDATE_DATE, 
                                            LFSBU, 
                                            dpsbu, 
                                            ppsbu 
                                    FROM canon_e633_config_conv_tbl
                                    where process_flag = 'P'; 
    dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_config_stg_tbl from conversion table');
    canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_config_stg_tbl from conversion table',NULL,NULL,NULL,NULL);
        commit; 
      
  EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		errbuff := sqlerrm;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
END load_config;

PROCEDURE load_ib_contacts(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_ib_contacts';

BEGIN
	retcode := 0;
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_ib_cont_conv_tbl';
      dbms_output.put_line('canon_e633_ib_cont_conv_tbl truncated');
      
  --extract data from CANMTH
  INSERT INTO canon_e633_ib_cont_conv_tbl(CONFIGURATION_NUMBER, 
                                          CONTACT_PERSON_FUNC_CODE, 
                                          ADDRESS_NUMBER, 
                                          MAILING_NAME, 
                                          TELEFONE_NO, 
                                          SOURCE, 
                                          UNIQUE_KEY, 
                                          LFS_SF_CONT_ACCT_ID, 
                                          LFS_SF_IB_ID, 
                                          LFS_SF_ACCOUNT_ID, 
                                          LFS_SF_IB_CONTACT_ID, 
                                          LFS_SF_STATUS_FLAG, 
                                          LFS_SF_STATUS_MESSAGE, 
                                          LFS_LAST_UPDATE_DATE , 
                                          PPS_SF_CONT_ACCT_ID, 
                                          PPS_SF_ACCOUNT_ID, 
                                          PPS_SF_IB_ID, 
                                          PPS_SF_STATUS_FLAG, 
                                          PPS_SF_IB_CONTACT_ID, 
                                          PPS_SF_STATUS_MESSAGE, 
                                          PPS_LAST_UPDATE_DATE , 
                                          LFSBU, 
                                          DPSBU, 
                                          PPSBU, 
                                          CONTACT_PERSON_FUNC_NAME, 
                                          SVC_MACH_MSTR_PK ) SELECT CONFIGURATION_NUMBER, 
                                                                      CONTACT_PERSON_FUNC_CODE, 
                                                                      ADDRESS_NUMBER, 
                                                                      MAILING_NAME, 
                                                                      TELEFONE_NO, 
                                                                      SOURCE, 
                                                                      UNIQUE_KEY, 
                                                                      LFS_SF_CONT_ACCT_ID, 
                                                                      LFS_SF_IB_ID, 
                                                                      LFS_SF_ACCOUNT_ID, 
                                                                      LFS_SF_IB_CONTACT_ID, 
                                                                      LFS_SF_STATUS_FLAG, 
                                                                      LFS_SF_STATUS_MESSAGE, 
                                                                      LFS_LAST_UPDATE_DATE , 
                                                                      PPS_SF_CONT_ACCT_ID, 
                                                                      PPS_SF_ACCOUNT_ID, 
                                                                      PPS_SF_IB_ID, 
                                                                      PPS_SF_STATUS_FLAG, 
                                                                      PPS_SF_IB_CONTACT_ID, 
                                                                      PPS_SF_STATUS_MESSAGE, 
                                                                      PPS_LAST_UPDATE_DATE , 
                                                                      LFSBU, 
                                                                      DPSBU, 
                                                                      ppsbu, 
                                                                      null, 
                                                                      NULL 
--                                                              FROM canon_e633_ib_cont_stg_tbl@canmth.cusa.canon.com;
                                                  --            FROM canon_e633_ib_cont_N01_tbl;
																FROM canon_e633_ib_cont_stg_tbl@EBS11I.CUSA.CANON.COM;
      dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_ib_cont_conv_tbl from CANMTH');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_ib_cont_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);
       
       UPDATE canon_e633_ib_cont_conv_tbl
          SET old_configuration_number = configuration_number
              ,old_contact_person_func_code = contact_person_func_code;
        dbms_output.put_line('Backup of IDs completed');
          COMMIT;
          
       UPDATE canon_e633_ib_cont_conv_tbl
          SET process_flag = 'E'
--              ,configuration_number = configuration_number ||'SAPIBNOTCONVTD'
--              ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_ib_contact_id IS NOT NULL THEN 'U' END
--              ,pps_sf_id_upd_flg = CASE WHEN pps_sf_ib_contact_id IS NOT NULL THEN 'U' END
              ,process_comments = 'Corresponding IB is not converted.'
              ,unique_key = unique_key || 'SAPIBNOTCONVTD'
              ,conversion_id = '6000'
                ,DATABASE = 'S21-LFSPPS'
                ,source_name = 'OMSI' 
        WHERE process_flag IS NULL
          AND old_configuration_number IN (SELECT old_config_nbr 
                                  FROM canon_e633_ib_conv_tbl
                                WHERE process_flag = 'E');
      dbms_output.put_line('Updated '||SQL%rowcount||' records for SAPIBNOTCONVTD');                                  
                                
         /* UPDATE canon_e633_ib_cont_conv_tbl
          SET process_flag = 'E'
              ,configuration_number = configuration_number ||'SAPCUSTNOTCONVTD'
              ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_ib_contact_id IS NOT NULL THEN 'U' END
              ,pps_sf_id_upd_flg = CASE WHEN pps_sf_ib_contact_id IS NOT NULL THEN 'U' END
              ,process_comments = 'Corresponding Customer is not converted.'
              ,unique_key = unique_key || 'SAPCUSTNOTCONVTD'
              ,conversion_id = '6000'
                ,DATABASE = 'S21-LFSPPS'
                ,source_name = 'OMSI' 
        WHERE nvl(process_flag, 'E') = 'E'
          AND (lfs_sf_account_id IN (SELECT lfs_sf_account_id 
                                       FROM canon_e633_cust_site_conv_tbl
                                      WHERE process_flag = 'E')
              OR
              pps_sf_account_id IN (SELECT pps_sf_account_id 
                                       FROM canon_e633_cust_site_conv_tbl
                                      WHERE process_flag = 'E'));
          commit;*/
          
        --map from S21 QA
        MERGE INTO canon_e633_ib_cont_conv_tbl e633
         USING(SELECT /*+ no_merge */ DISTINCT c183.old_config_number
                              ,c183.s21_svc_config_mstr_pk
                              ,c183.svc_mach_mstr_pk
                              ,scmp.svc_ctac_tp_cd
                              ,(c183.old_config_number || (CASE WHEN scmp.svc_ctac_tp_cd = '30' THEN 'H'
                                                                WHEN scmp.svc_ctac_tp_cd = '10' THEN 'M'
                                                                WHEN scmp.svc_ctac_tp_cd = '50' THEN 'S' END)) mapping_key
                          --as per Taizo
                          -- M(Meter Read) = 10
                          -- H(Hardware) = 30
                          -- S(Software) = 50
                          -- G(Government) is not converted in S21
                        FROM S21_CSA_REP.tmp_c183_config_pk_map c183
                             ,S21_CSA_APPS.svc_mach_mstr smm
                             ,S21_CSA_APPS.svc_mach_ctac_psn scmp
                       WHERE 1 =1                                  
                         and c183.database_name = 'S21-LFSPPS'
                         AND scmp.svc_mach_mstr_pk = c183.svc_mach_mstr_pk
                         AND scmp.glbl_cmpy_cd = 'ADB'
                         AND scmp.ezcancelflag = '0'
                         AND smm.glbl_cmpy_cd = 'ADB'
                         and smm.ezcancelflag = '0'
                         AND c183.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                         AND smm.svc_mach_tp_cd = 1) s21
                    ON(e633.unique_key = s21.mapping_key AND process_flag IS NULL)
                    WHEN MATCHED THEN UPDATE
                      SET e633.configuration_number = s21.s21_svc_config_mstr_pk
                          ,e633.contact_person_func_code = s21.svc_ctac_tp_cd
                          ,e633.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
--                          ,e633.unique_key = s21.s21_svc_config_mstr_pk || s21.svc_ctac_tp_cd
                          ,conv_load_date = SYSDATE
--                          ,process_flag = 'P'
                          ,pps_sf_id_upd_flg = CASE WHEN e633.ppsbu IS NOT NULL THEN 'U' ELSE NULL END
                          ,lfs_sf_id_upd_flg = CASE WHEN e633.lfsbu IS NOT NULL THEN 'U' ELSE NULL END
                          ,process_comments = 'Updated mappings from S21.'
                          ,conversion_id = '6000'
                          ,DATABASE = 'S21-LFSPPS'
                          ,source_name = 'OMSI' ;
           dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Merged '||SQL%ROWCOUNT||' mappings from S21',NULL,NULL,NULL,NULL);        
       
        UPDATE canon_e633_ib_cont_conv_tbl
        SET process_flag = 'P'
        WHERE process_comments = 'Updated mappings from S21.';
        COMMIT;
        
        UPDATE canon_e633_ib_cont_conv_tbl
          SET unique_key = configuration_number || contact_person_func_code
          WHERE process_flag = 'P';
        dbms_output.put_line('Updated '||SQL%rowcount||' unique_key values');
        COMMIT;
        
			-- records where the contacts are not updated.
				UPDATE canon_e633_ib_cont_conv_tbl e633
				   SET process_flag = 'E'
               ,old_configuration_number = configuration_number
               ,e633.old_contact_person_func_code = e633.contact_person_func_code
--               ,configuration_number = configuration_number || 'SAPNOTCONVTD'
--               ,unique_key = unique_key || 'SAPNOTCONVTD'
				       ,process_comments = 'Could not find a mapping in S21.'
--               ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_ib_contact_id IS NOT NULL THEN 'U' END
--               ,pps_sf_id_upd_flg = CASE WHEN pps_sf_ib_contact_id IS NOT NULL THEN 'U' END
               ,conversion_id = '6000'
               ,DATABASE = 'S21-LFSPPS'
               ,source_name = 'OMSI' 
				  WHERE process_flag IS NULL;
			dbms_output.put_line('No mapping found in S21 for '||SQL%rowcount||' Contact''s');
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find mapping in S21',NULL,NULL,NULL,NULL);
			COMMIT;                   
     
      
      EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_ib_cont_stg_tbl';
      dbms_output.put_line('canon_e633_ib_cont_stg_tbl truncated');
      
      INSERT INTO canon_e633_ib_cont_stg_tbl(CONFIGURATION_NUMBER, 
                                          CONTACT_PERSON_FUNC_CODE, 
                                          ADDRESS_NUMBER, 
                                          MAILING_NAME, 
                                          TELEFONE_NO, 
                                          SOURCE, 
                                          UNIQUE_KEY, 
                                          LFS_SF_CONT_ACCT_ID, 
                                          LFS_SF_IB_ID, 
                                          LFS_SF_ACCOUNT_ID, 
                                          LFS_SF_IB_CONTACT_ID, 
                                          LFS_SF_STATUS_FLAG, 
                                          LFS_SF_STATUS_MESSAGE, 
                                          LFS_LAST_UPDATE_DATE , 
                                          PPS_SF_CONT_ACCT_ID, 
                                          PPS_SF_ACCOUNT_ID, 
                                          PPS_SF_IB_ID, 
                                          PPS_SF_STATUS_FLAG, 
                                          PPS_SF_IB_CONTACT_ID, 
                                          PPS_SF_STATUS_MESSAGE, 
                                          PPS_LAST_UPDATE_DATE , 
                                          LFSBU, 
                                          DPSBU, 
                                          PPSBU, 
                                          CONTACT_PERSON_FUNC_NAME, 
                                          SVC_MACH_MSTR_PK ) SELECT CONFIGURATION_NUMBER, 
                                                                      CONTACT_PERSON_FUNC_CODE, 
                                                                      ADDRESS_NUMBER, 
                                                                      MAILING_NAME, 
                                                                      TELEFONE_NO, 
                                                                      SOURCE, 
                                                                      UNIQUE_KEY, 
                                                                      LFS_SF_CONT_ACCT_ID, 
                                                                      LFS_SF_IB_ID, 
                                                                      LFS_SF_ACCOUNT_ID, 
                                                                      LFS_SF_IB_CONTACT_ID, 
                                                                      LFS_SF_STATUS_FLAG, 
                                                                      LFS_SF_STATUS_MESSAGE, 
                                                                      LFS_LAST_UPDATE_DATE , 
                                                                      PPS_SF_CONT_ACCT_ID, 
                                                                      PPS_SF_ACCOUNT_ID, 
                                                                      PPS_SF_IB_ID, 
                                                                      PPS_SF_STATUS_FLAG, 
                                                                      PPS_SF_IB_CONTACT_ID, 
                                                                      PPS_SF_STATUS_MESSAGE, 
                                                                      PPS_LAST_UPDATE_DATE , 
                                                                      LFSBU, 
                                                                      DPSBU, 
                                                                      ppsbu, 
                                                                      NULL, 
                                                                      svc_mach_mstr_pk
                                                              FROM canon_e633_ib_cont_conv_tbl
                                                              where process_flag = 'P';
      dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_ib_cont_stg_tbl from conversion table');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_ib_cont_stg_tbl from conversion table',NULL,NULL,NULL,NULL);
      COMMIT; 
      
  EXCEPTION 
	WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		errbuff := sqlerrm;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
END load_ib_contacts;

PROCEDURE load_meter_reads(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_meter_reads';
BEGIN
  retcode := 0;
  
   EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_mtrrd_conv_tbl';
   dbms_output.put_line('canon_e633_mtrrd_conv_tbl truncated');
   canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_mtrrd_conv_tbl truncated',NULL,NULL,NULL,NULL);
        
  --extract data from CANMTH
  INSERT INTO canon_e633_mtrrd_conv_tbl(configuration_number
                                          ,serial_no 
                                          ,LABEL 
                                          ,label_descrip 
                                          ,METER_TYPE 
                                          ,meter_read_type 
                                          ,meter_reading
                                          ,REGISTRATION_DATE 
                                          ,creation_date 
                                          ,invoice_period 
                                          ,meter_source 
                                          ,meter_read_type_1 
                                          ,METER_READ_SYSTEM_NUMBER 
                                          ,LOGICAL_DELETE_INDICATOR 
                                          ,last_change_date 
                                          ,SVC_MACH_MSTR_PK 
                                          ,lfs_sf_ib_id 
                                          ,lfs_sf_account_id 
                                          ,LFS_SF_MTR_READ_ID 
                                          ,lfs_sf_status_flag 
                                          ,lfs_sf_status_message 
                                          ,lfs_last_update_date 
                                          ,PPS_SF_IB_ID 
                                          ,pps_sf_account_id 
                                          ,pps_sf_mtr_read_id 
                                          ,pps_sf_status_flag 
                                          ,pps_sf_status_message 
                                          ,pps_last_update_date 
                                          ,lfsbu 
                                          ,dpsbu 
                                          ,PPSBU ) SELECT configuration_number
                                                        ,serial_no 
                                                        ,LABEL 
                                                        ,label_descrip 
                                                        ,METER_TYPE 
                                                        ,meter_read_type 
                                                        ,meter_reading
                                                        ,REGISTRATION_DATE 
                                                        ,creation_date 
                                                        ,invoice_period 
                                                        ,meter_source 
                                                        ,meter_read_type_1 
                                                        ,METER_READ_SYSTEM_NUMBER 
                                                        ,LOGICAL_DELETE_INDICATOR 
                                                        ,last_change_date 
                                                        ,NULL 
                                                        ,lfs_sf_ib_id 
                                                        ,lfs_sf_account_id 
                                                        ,LFS_SF_MTR_READ_ID 
                                                        ,lfs_sf_status_flag 
                                                        ,lfs_sf_status_message 
                                                        ,lfs_last_update_date 
                                                        ,PPS_SF_IB_ID 
                                                        ,pps_sf_account_id 
                                                        ,pps_sf_mtr_read_id 
                                                        ,pps_sf_status_flag 
                                                        ,pps_sf_status_message 
                                                        ,pps_last_update_date 
                                                        ,lfsbu 
                                                        ,dpsbu 
                                                        ,ppsbu
--                                                  FROM canon_e633_meter_reads_stg_tbl@canmth.cusa.canon.com;
                                                  --  FROM CANON_E633_METER_READS_N01_TBL;
												     FROM canon_e633_meter_reads_stg_tbl@EBS11I.CUSA.CANON.COM; 
      dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_mtrrd_conv_tbl from CANMTH');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_mtrrd_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);
        
        UPDATE canon_e633_mtrrd_conv_tbl
          SET old_config_number = configuration_number
              ,old_serial_no = serial_no
              ,old_meter_read_system_num = meter_read_system_number;
        dbms_output.put_line('Backup of IDs completed');
        COMMIT;
        
        MERGE INTO canon_e633_mtrrd_conv_tbl conv
        USING (SELECT DISTINCT c183.serial_number
                               ,c183.svc_mach_mstr_pk
                               ,s21_svc_config_mstr_pk
                               ,spmr.svc_phys_mtr_read_pk
                               ,c183_mtr.memrno old_mtr_read_system_num
                 FROM S21_CSA_REP.tmp_c183_config_pk_map c183
--                         ,canon_e633_mtrrd_conv_tbl e633
                         ,S21_CSA_APPS.svc_phys_mtr_read spmr
                         ,S21_CSA_REP.TMP_C183_MTR_READ c183_mtr
                WHERE  1=1
				  and spmr.ezcancelflag = '0'
				  and spmr.glbl_cmpy_cd = 'ADB'
				  and c183.database_name = 'S21-LFSPPS'
--                  AND e633.serial_no = c183.serial_number
                  AND c183.svc_mach_mstr_pk = spmr.svc_mach_mstr_pk
                  AND spmr.svc_phys_mtr_read_pk = c183_mtr.svc_phys_mtr_read_pk  
                  AND c183_mtr.ds_mtr_read_tp_grp_cd='S' 
                  AND c183_mtr.ds_test_copy_cls_cd = 'O' 
                  AND c183_mtr.memrno <> '14745848' --added this as it has duplicates
                  )s21
          ON (conv.old_meter_read_system_num = s21.old_mtr_read_system_num)
          WHEN MATCHED THEN UPDATE
              SET conv.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
                  ,conv.meter_read_system_number = s21.svc_phys_mtr_read_pk
                  ,conv.configuration_number = s21.s21_svc_config_mstr_pk
                  ,conv.serial_no = s21.serial_number
                  ,conv_load_date = SYSDATE
                  ,process_flag = 'P'
                  ,pps_sf_id_upd_flg = CASE WHEN conv.ppsbu IS NOT NULL THEN 'U' ELSE NULL END
                  ,lfs_sf_id_upd_flg = CASE WHEN conv.lfsbu IS NOT NULL THEN 'U' ELSE NULL END
                  ,process_comments = 'Updated mappings from S21.'
                  ,conversion_id = '6000'
                  ,DATABASE = 'S21-LFSPPS'
                  ,source_name = 'OMSI' ;
                  
        dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Merged '||SQL%ROWCOUNT||' mappings from S21',NULL,NULL,NULL,NULL);        
        COMMIT;
        
			-- records that could not be mapped.
				UPDATE canon_e633_mtrrd_conv_tbl e633
				   SET process_flag = 'E'
--               ,configuration_number = configuration_number || 'SAPNOTCONVTD'
--               ,meter_read_system_number = meter_read_system_number || 'SAPNOTCONVTD'
				       ,process_comments = 'Could not find a mapping in S21.'
--               ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_mtr_read_id IS NOT NULL THEN 'U' END
--               ,pps_sf_id_upd_flg = CASE WHEN pps_sf_mtr_read_id IS NOT NULL THEN 'U' END
               ,conversion_id = '6000'
               ,DATABASE = 'S21-LFSPPS'
               ,source_name = 'OMSI' 
				  WHERE process_flag IS NULL;
			dbms_output.put_line(SQL%rowcount||' could not be mapped');
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not be mapped in S21',NULL,NULL,NULL,NULL);
			commit;       
      
      -- duplicate record that could not be mapped.
				UPDATE canon_e633_mtrrd_conv_tbl e633
				   SET process_flag = 'E'
--               ,configuration_number = configuration_number || 'SAPNOTCONVTD'
--               ,meter_read_system_number = meter_read_system_number || 'SAPNOTCONVTD'
				       ,process_comments = 'Could not find a mapping in S21.'
--               ,lfs_sf_id_upd_flg = CASE WHEN lfs_sf_mtr_read_id IS NOT NULL THEN 'U' END
--               ,pps_sf_id_upd_flg = CASE WHEN pps_sf_mtr_read_id IS NOT NULL THEN 'U' END
               ,conversion_id = '6000'
               ,DATABASE = 'S21-LFSPPS'
               ,source_name = 'OMSI' 
				  WHERE process_flag IS NULL
            AND old_meter_read_system_num = '14745848' ;
			dbms_output.put_line(SQL%rowcount||' could not be mapped - for 14745848 meterread system number');
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not be mapped in S21 - for 14745848 meterread system number',NULL,NULL,NULL,NULL);
			commit;        
              
       EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_meter_reads_stg_tbl';
       dbms_output.put_line('canon_e633_meter_reads_stg_tbl truncated');
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_meter_reads_stg_tbl truncated',NULL,NULL,NULL,NULL);
       
      INSERT INTO canon_e633_meter_reads_stg_tbl(configuration_number
                                                  ,serial_no 
                                                  ,LABEL 
                                                  ,label_descrip 
                                                  ,METER_TYPE 
                                                  ,meter_read_type 
                                                  ,meter_reading
                                                  ,REGISTRATION_DATE 
                                                  ,creation_date 
                                                  ,invoice_period 
                                                  ,meter_source 
                                                  ,meter_read_type_1 
                                                  ,METER_READ_SYSTEM_NUMBER 
                                                  ,LOGICAL_DELETE_INDICATOR 
                                                  ,last_change_date 
--                                                        ,NULL 
                                                  ,lfs_sf_ib_id 
                                                  ,lfs_sf_account_id 
                                                  ,LFS_SF_MTR_READ_ID 
                                                  ,lfs_sf_status_flag 
                                                  ,lfs_sf_status_message 
                                                  ,lfs_last_update_date 
                                                  ,PPS_SF_IB_ID 
                                                  ,pps_sf_account_id 
                                                  ,pps_sf_mtr_read_id 
                                                  ,pps_sf_status_flag 
                                                  ,pps_sf_status_message 
                                                  ,pps_last_update_date 
                                                  ,lfsbu 
                                                  ,dpsbu 
                                                  ,ppsbu) SELECT configuration_number
                                                                ,serial_no 
                                                                ,LABEL 
                                                                ,label_descrip 
                                                                ,METER_TYPE 
                                                                ,meter_read_type 
                                                                ,meter_reading
                                                                ,REGISTRATION_DATE 
                                                                ,creation_date 
                                                                ,invoice_period 
                                                                ,meter_source 
                                                                ,meter_read_type_1 
                                                                ,METER_READ_SYSTEM_NUMBER 
                                                                ,LOGICAL_DELETE_INDICATOR 
                                                                ,last_change_date 
        --                                                        ,NULL 
                                                                ,lfs_sf_ib_id 
                                                                ,lfs_sf_account_id 
                                                                ,LFS_SF_MTR_READ_ID 
                                                                ,lfs_sf_status_flag 
                                                                ,lfs_sf_status_message 
                                                                ,lfs_last_update_date 
                                                                ,PPS_SF_IB_ID 
                                                                ,pps_sf_account_id 
                                                                ,pps_sf_mtr_read_id 
                                                                ,pps_sf_status_flag 
                                                                ,pps_sf_status_message 
                                                                ,pps_last_update_date 
                                                                ,lfsbu 
                                                                ,dpsbu 
                                                                ,ppsbu
                                                  from canon_e633_mtrrd_conv_tbl
                                                  where process_flag = 'P'; 
  dbms_output.put_line(SQL%rowcount||' inserted into canon_e633_meter_reads_stg_tbl');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' inserted into canon_e633_meter_reads_stg_tbl',NULL,NULL,NULL,NULL);
       
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		errbuff := sqlerrm;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
END load_meter_reads;

PROCEDURE load_open_svc_calls(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_open_svc_calls';
BEGIN
  retcode := 0;
 
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_opnsvc_conv_tbl';
  dbms_output.put_line('canon_e633_opnsvc_conv_tbl truncated');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_opnsvc_conv_tbl truncated',NULL,NULL,NULL,NULL);
  
  INSERT INTO canon_e633_opnsvc_conv_tbl(legacy_ref_id 
                                          ,configuration_number 
                                          ,jobnumber 
                                          ,arrival_date 
                                          ,ACTION_CODE 
                                          ,cause_code 
                                          ,module_code 
                                          ,CLOSE 
                                          ,CARD_CODE 
                                          ,address_number 
                                          ,SOURCE 
                                          ,FREE_TO_USE_1 
                                          ,free_to_use_2 
                                          ,FREE_TO_USE_3 
                                          ,free_to_use_4 
                                          ,free_to_use_5 
                                          ,free_to_use_6 
                                          ,CALL_CREATION_DATE 
                                          ,unique_key 
                                          ,lfs_sf_ib_id 
                                          ,lfs_sf_account_id 
                                          ,lfs_sf_open_service_id 
                                          ,lfs_sf_status_flag 
                                          ,lfs_sf_status_message 
                                          ,lfs_last_update_date 
                                          ,pps_sf_ib_id 
                                          ,pps_sf_account_id 
                                          ,PPS_SF_OPEN_SERVICE_ID 
                                          ,pps_sf_status_flag 
                                          ,pps_sf_status_message 
                                          ,pps_last_update_date 
                                          ,lfsbu 
                                          ,dpsbu 
                                          ,ppsbu 
                                          ,account_name
                                          ,ADDRESS) SELECT legacy_ref_id 
                                                            ,configuration_number 
                                                            ,jobnumber 
                                                            ,arrival_date 
                                                            ,ACTION_CODE 
                                                            ,cause_code 
                                                            ,module_code 
                                                            ,CLOSE 
                                                            ,CARD_CODE 
                                                            ,address_number 
                                                            ,SOURCE 
                                                            ,FREE_TO_USE_1 
                                                            ,free_to_use_2 
                                                            ,FREE_TO_USE_3 
                                                            ,free_to_use_4 
                                                            ,free_to_use_5 
                                                            ,free_to_use_6 
                                                            ,CALL_CREATION_DATE 
                                                            ,unique_key 
                                                            ,lfs_sf_ib_id 
                                                            ,lfs_sf_account_id 
                                                            ,lfs_sf_open_service_id 
                                                            ,lfs_sf_status_flag 
                                                            ,lfs_sf_status_message 
                                                            ,lfs_last_update_date 
                                                            ,pps_sf_ib_id 
                                                            ,pps_sf_account_id 
                                                            ,PPS_SF_OPEN_SERVICE_ID 
                                                            ,pps_sf_status_flag 
                                                            ,pps_sf_status_message 
                                                            ,pps_last_update_date 
                                                            ,lfsbu 
                                                            ,dpsbu 
                                                            ,ppsbu 
                                                            ,account_name
                                                            ,address
                                                 --   FROM canon_e633_opencalls_731_tbl;
												 FROM CANON_E633_OPENCALLS_STG_TBL@EBS11I.CUSA.CANON.COM;
    dbms_output.put_line(SQL%rowcount||' inserted into canon_e633_opnsvc_conv_tbl');
    canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' inserted into canon_e633_opnsvc_conv_tbl',NULL,NULL,NULL,NULL);
    
    UPDATE canon_e633_opnsvc_conv_tbl
       SET old_legacy_ref_id = legacy_ref_id
          ,old_config_num = configuration_number
          ,old_job_number = jobnumber;
    dbms_output.put_line('Backup of the IDs completed');
    canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Backup of the IDs completed',NULL,NULL,NULL,NULL);
        
    MERGE INTO canon_e633_opnsvc_conv_tbl e633
      USING (SELECT DISTINCT c183.old_config_number
                             ,c183.s21_svc_config_mstr_pk
                             ,c183.serial_number
                             ,c183.svc_mach_mstr_pk
                             ,c611.fsr_num
							 ,c611.job_no job_number
--                             ,fv.svc_task_num
--                             ,sm.svc_cmnt_txt
                            -- ,substr(substr(svc_cmnt_txt,instr(svc_cmnt_txt,'Job#')),6,(instr(svc_cmnt_txt,',')-13)) job_number
--                             ,substr(substr(svc_cmnt_txt,instr(svc_cmnt_txt,'Task#')),7) legacy_task_number
                FROM S21_CSA_REP.tmp_c183_config_pk_map c183
				     ,s21_csa_rep.TMP_C611_SVC_TASK_LOAD c611
--                   ,S21_CSA_APPS.fsr f
--                   ,S21_CSA_APPS.fsr_visit fv
--                   ,S21_CSA_APPS.svc_task st
--                   ,S21_CSA_APPS.svc_task_sts sts
--                   ,S21_CSA_APPS.svc_call_src_tp scst
--                   ,S21_CSA_APPS.svc_memo sm
                WHERE c183.database_name = 'S21-LFSPPS'
--				  AND c611.database_name = 'S21-LFSPPS'
                  AND c611.svc_mach_mstr_pk = c183.svc_mach_mstr_pk
				  --AND c611.job_no = e633.old_job_number
                  /*AND f.fsr_num = fv.fsr_num
                  and fv.fsr_visit_ltst_flg = 'Y'
                  AND fv.fsr_num = st.fsr_num
                  AND fv.svc_task_num = st.svc_task_num
                  AND sts.svc_task_sts_cd = st.svc_task_sts_cd
                  AND sts.open_task_flg = 'Y'
                  AND scst.svc_call_src_tp_cd = f.svc_call_src_tp_cd
                  AND sm.fsr_num = f.fsr_num
                  AND sm.svc_cmnt_txt LIKE '%Legacy%'
                  and substr(substr(svc_cmnt_txt,instr(svc_cmnt_txt,'Job#')),6,(instr(svc_cmnt_txt,',')-13)) <> '0'
                  AND f.glbl_cmpy_cd = 'ADB'
                  AND f.ezcancelflag = '0'
                  AND fv.glbl_cmpy_cd = 'ADB'
                  AND fv.ezcancelflag = '0'
                  AND st.glbl_cmpy_cd = 'ADB'
                  AND st.ezcancelflag = '0'
                  AND sts.glbl_cmpy_cd = 'ADB'
                  AND sts.ezcancelflag = '0'
                  AND scst.glbl_cmpy_cd = 'ADB'
                  AND scst.ezcancelflag = '0'
                  AND sm.glbl_cmpy_cd = 'ADB'
                  AND sm.ezcancelflag = '0'*/
              )s21
          ON (e633.old_job_number = s21.job_number)
        WHEN MATCHED THEN UPDATE SET
             configuration_number = s21_svc_config_mstr_pk
             ,svc_mach_mstr_pk = s21.svc_mach_mstr_pk
             ,fsr_num = s21.fsr_num
             ,jobnumber = s21.fsr_num
             ,conv_load_date = SYSDATE
             ,process_flag = 'P'
             ,pps_sf_id_upd_flg = CASE WHEN e633.ppsbu IS NOT NULL THEN 'U' ELSE NULL END
             ,lfs_sf_id_upd_flg = CASE WHEN e633.lfsbu IS NOT NULL THEN 'U' ELSE NULL END
             ,process_comments = 'Updated mappings from S21.'
             ,conversion_id = '6000'
             ,DATABASE = 'S21-LFSPPS'
             ,source_name = 'OMSI' ;
  dbms_output.put_line(SQL%rowcount||' merged into canon_e633_opnsvc_conv_tbl');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' merged into canon_e633_opnsvc_conv_tbl',NULL,NULL,NULL,NULL);            
  
  UPDATE canon_e633_opnsvc_conv_tbl
     SET process_flag = 'E'
         ,process_comments = 'Could not find a mapping in S21.'
         ,conv_load_date = SYSDATE
         ,conversion_id = '6000'
         ,DATABASE = 'S21-LFSPPS'
         ,source_name = 'OMSI'
      where process_flag IS NULL;
 dbms_output.put_line(SQL%rowcount||' could not be mapped');
 canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not be mapped in S21',NULL,NULL,NULL,NULL);
 commit;
  
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_opencalls_stg_tbl';
  dbms_output.put_line('canon_e633_opencalls_stg_tbl truncated');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_opencalls_stg_tbl truncated',NULL,NULL,NULL,NULL);
      
  INSERT INTO canon_e633_opencalls_stg_tbl(legacy_ref_id 
                                          ,configuration_number 
                                          ,jobnumber 
                                          ,arrival_date 
                                          ,ACTION_CODE 
                                          ,cause_code 
                                          ,module_code 
                                          ,CLOSE 
                                          ,CARD_CODE 
                                          ,address_number 
                                          ,SOURCE 
                                          ,FREE_TO_USE_1 
                                          ,free_to_use_2 
                                          ,FREE_TO_USE_3 
                                          ,free_to_use_4 
                                          ,free_to_use_5 
                                          ,free_to_use_6 
                                          ,CALL_CREATION_DATE 
                                          ,unique_key 
                                          ,lfs_sf_ib_id 
                                          ,lfs_sf_account_id 
                                          ,lfs_sf_open_service_id 
                                          ,lfs_sf_status_flag 
                                          ,lfs_sf_status_message 
                                          ,lfs_last_update_date 
                                          ,pps_sf_ib_id 
                                          ,pps_sf_account_id 
                                          ,PPS_SF_OPEN_SERVICE_ID 
                                          ,pps_sf_status_flag 
                                          ,pps_sf_status_message 
                                          ,pps_last_update_date 
                                          ,lfsbu 
                                          ,dpsbu 
                                          ,ppsbu 
                                          ,account_name
                                          ,ADDRESS) SELECT legacy_ref_id 
                                                          ,configuration_number 
                                                          ,jobnumber 
                                                          ,arrival_date 
                                                          ,ACTION_CODE 
                                                          ,cause_code 
                                                          ,module_code 
                                                          ,CLOSE 
                                                          ,CARD_CODE 
                                                          ,address_number 
                                                          ,SOURCE 
                                                          ,FREE_TO_USE_1 
                                                          ,free_to_use_2 
                                                          ,FREE_TO_USE_3 
                                                          ,free_to_use_4 
                                                          ,free_to_use_5 
                                                          ,free_to_use_6 
                                                          ,CALL_CREATION_DATE 
                                                          ,unique_key 
                                                          ,lfs_sf_ib_id 
                                                          ,lfs_sf_account_id 
                                                          ,lfs_sf_open_service_id 
                                                          ,lfs_sf_status_flag 
                                                          ,lfs_sf_status_message 
                                                          ,lfs_last_update_date 
                                                          ,pps_sf_ib_id 
                                                          ,pps_sf_account_id 
                                                          ,PPS_SF_OPEN_SERVICE_ID 
                                                          ,pps_sf_status_flag 
                                                          ,pps_sf_status_message 
                                                          ,pps_last_update_date 
                                                          ,lfsbu 
                                                          ,dpsbu 
                                                          ,ppsbu 
                                                          ,account_name
                                                          ,address
                                                    FROM canon_e633_opnsvc_conv_tbl
                                                   WHERE process_flag = 'P';
  dbms_output.put_line(SQL%rowcount||' inserted into canon_e633_opencalls_stg_tbl');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' inserted into canon_e633_opencalls_stg_tbl',NULL,NULL,NULL,NULL);
  commit;                 
  
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		errbuff := sqlerrm;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
END load_open_svc_calls;


PROCEDURE load_close_svc_calls(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_closed_svc_calls';
BEGIN
  retcode := 0;
 
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_clssvc_conv_tbl';
  dbms_output.put_line('canon_e633_clssvc_conv_tbl truncated');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_clssvc_conv_tbl truncated',NULL,NULL,NULL,NULL);
  
  INSERT INTO canon_e633_clssvc_conv_tbl(  LEGACY_REF_ID  
                                            ,CONFIGURATION_NUMBER    
                                            ,JOBNUMBER  
                                            ,ARRIVAL_DATE  
                                            ,ACTION_CODE    
                                            ,CAUSE_CODE  
                                            ,MODULE_CODE   
                                            ,TIME_BETWEEN_JOBS   
                                            ,TIME_BETWEEN_FAILURE   
                                            ,COPIES_BETWEEN_JOBS  
                                            ,COPIES_BETWEEN_FAILURE  
                                            ,DE_INSTALLATION_JOB    
                                            ,INSTALLATION_JOB
                                            ,CM_JOB    
                                            ,PM_JOB    
                                            ,LAST_VISIT_TECHNICIAN   
                                            ,CLOSE
                                            ,CARD_CODE  
                                            ,CLOSED_DATE   
                                            ,REASON_CANCEL_CODE
                                            ,JOB_STATUS 
                                            ,ADDRESS_NUMBER 
                                            ,SOURCE
                                            ,FREE_TO_USE_1   
                                            ,FREE_TO_USE_2
                                            ,FREE_TO_USE_3   
                                            ,FREE_TO_USE_4    
                                            ,FREE_TO_USE_5  
                                            ,FREE_TO_USE_6
                                            ,CALL_CREATION_DATE   
                                            ,LAST_VISIT_TECH   
                                            ,ACCOUNT_NAME
                                            ,ADDRESS
                                            ,UNIQUE_KEY   
                                            ,LFS_SF_IB_ID  
                                            ,LFS_SF_ACCOUNT_ID 
                                            ,LFS_SF_CLOSE_SERVICE_ID 
                                            ,LFS_SF_STATUS_FLAG  
                                            ,LFS_SF_STATUS_MESSAGE
                                            ,LFS_LAST_UPDATE_DATE         
                                            ,PPS_SF_IB_ID
                                            ,PPS_SF_ACCOUNT_ID
                                            ,PPS_SF_CLOSE_SERVICE_ID 
                                            ,PPS_SF_STATUS_FLAG  
                                            ,PPS_SF_STATUS_MESSAGE 
                                            ,PPS_LAST_UPDATE_DATE       
                                            ,LFSBU  
                                            ,DPSBU 
                                            ,PPSBU) SELECT   LEGACY_REF_ID  
                                                          ,CONFIGURATION_NUMBER    
                                                          ,JOBNUMBER  
                                                          ,ARRIVAL_DATE  
                                                          ,ACTION_CODE    
                                                          ,CAUSE_CODE  
                                                          ,MODULE_CODE   
                                                          ,TIME_BETWEEN_JOBS   
                                                          ,TIME_BETWEEN_FAILURE   
                                                          ,COPIES_BETWEEN_JOBS  
                                                          ,COPIES_BETWEEN_FAILURE  
                                                          ,DE_INSTALLATION_JOB    
                                                          ,INSTALLATION_JOB
                                                          ,CM_JOB    
                                                          ,PM_JOB    
                                                          ,LAST_VISIT_TECHNICIAN   
                                                          ,CLOSE
                                                          ,CARD_CODE  
                                                          ,CLOSED_DATE   
                                                          ,REASON_CANCEL_CODE
                                                          ,JOB_STATUS 
                                                          ,ADDRESS_NUMBER 
                                                          ,SOURCE
                                                          ,FREE_TO_USE_1   
                                                          ,FREE_TO_USE_2
                                                          ,FREE_TO_USE_3   
                                                          ,FREE_TO_USE_4    
                                                          ,FREE_TO_USE_5  
                                                          ,FREE_TO_USE_6
                                                          ,CALL_CREATION_DATE   
                                                          ,LAST_VISIT_TECH   
                                                          ,ACCOUNT_NAME
                                                          ,ADDRESS
                                                          ,UNIQUE_KEY   
                                                          ,LFS_SF_IB_ID  
                                                          ,LFS_SF_ACCOUNT_ID 
                                                          ,LFS_SF_CLOSE_SERVICE_ID 
                                                          ,LFS_SF_STATUS_FLAG  
                                                          ,LFS_SF_STATUS_MESSAGE
                                                          ,LFS_LAST_UPDATE_DATE         
                                                          ,PPS_SF_IB_ID
                                                          ,PPS_SF_ACCOUNT_ID
                                                          ,PPS_SF_CLOSE_SERVICE_ID 
                                                          ,PPS_SF_STATUS_FLAG  
                                                          ,PPS_SF_STATUS_MESSAGE 
                                                          ,PPS_LAST_UPDATE_DATE       
                                                          ,LFSBU  
                                                          ,DPSBU 
                                                          ,ppsbu
                                                    FROM canon_e633_closecalls_stg_tbl@ebs11i.cusa.canon.com; --QC30403
    dbms_output.put_line(SQL%rowcount||' inserted into canon_e633_clssvc_conv_tbl');
    canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' inserted into canon_e633_clssvc_conv_tbl',NULL,NULL,NULL,NULL);
    
    UPDATE canon_e633_clssvc_conv_tbl
       SET old_legacy_ref_id = legacy_ref_id
          ,old_config_num = configuration_number
          ,old_job_number = jobnumber;
    dbms_output.put_line('Backup of the IDs completed');
    canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Backup of the IDs completed',NULL,NULL,NULL,NULL);
        
    MERGE INTO canon_e633_clssvc_conv_tbl e633
      USING (SELECT DISTINCT c183.old_config_number
                             ,c183.s21_svc_config_mstr_pk
                             ,c183.serial_number
                             ,c183.svc_mach_mstr_pk
                             ,c612.fsr_num
							 ,c612.job_no job_number
--                             ,fv.svc_task_num
--                             ,sm.svc_cmnt_txt
--                             ,substr(substr(svc_cmnt_txt,instr(svc_cmnt_txt,'Job#')),6,(instr(svc_cmnt_txt,',')-13)) job_number
--                             ,substr(substr(svc_cmnt_txt,instr(svc_cmnt_txt,'Task#')),7) legacy_task_number
                FROM S21_CSA_REP.tmp_c183_config_pk_map c183
					 ,s21_csa_rep.TMP_C612_SVC_TASK_LOAD c612
--                   ,S21_CSA_EXTN.fsr f
--                   ,S21_CSA_EXTN.fsr_visit fv
--                   ,S21_CSA_EXTN.svc_task st
--                   ,S21_CSA_EXTN.svc_task_sts sts
--                   ,S21_CSA_EXTN.svc_call_src_tp scst
--                   ,S21_CSA_EXTN.svc_memo sm                     
                WHERE c183.database_name = 'S21-LFSPPS'
				  --AND c612.database_name = 'S21-LFSPPS'
                  AND c612.svc_mach_mstr_pk = c183.svc_mach_mstr_pk
				  --AND c612.job_no = e633.old_job_number
                 /* AND f.fsr_num = fv.fsr_num
                  and fv.fsr_visit_ltst_flg = 'Y'
                  AND fv.fsr_num = st.fsr_num
                  AND fv.svc_task_num = st.svc_task_num
                  AND sts.svc_task_sts_cd = st.svc_task_sts_cd
                  AND sts.open_task_flg = 'Y'
                  AND scst.svc_call_src_tp_cd = f.svc_call_src_tp_cd
                  AND sm.fsr_num = f.fsr_num
                  AND sm.svc_cmnt_txt LIKE '%Legacy%'
                  and substr(substr(svc_cmnt_txt,instr(svc_cmnt_txt,'Job#')),6,(instr(svc_cmnt_txt,',')-13)) <> '0'
                  AND f.glbl_cmpy_cd = 'ADB'
                  AND f.ezcancelflag = '0'
                  AND fv.glbl_cmpy_cd = 'ADB'
                  AND fv.ezcancelflag = '0'
                  AND st.glbl_cmpy_cd = 'ADB'
                  AND st.ezcancelflag = '0'
                  AND sts.glbl_cmpy_cd = 'ADB'
                  AND sts.ezcancelflag = '0'
                  AND scst.glbl_cmpy_cd = 'ADB'
                  AND scst.ezcancelflag = '0'
                  AND sm.glbl_cmpy_cd = 'ADB'
                  AND sm.ezcancelflag = '0'*/
              )s21
          ON (e633.old_job_number = s21.job_number)
        WHEN MATCHED THEN UPDATE SET
             configuration_number = s21_svc_config_mstr_pk
             ,svc_mach_mstr_pk = s21.svc_mach_mstr_pk
             ,fsr_num = s21.fsr_num
             ,jobnumber = s21.fsr_num
             ,conv_load_date = SYSDATE
             ,process_flag = 'P'
             ,pps_sf_id_upd_flg = CASE WHEN e633.ppsbu IS NOT NULL THEN 'U' ELSE NULL END
             ,lfs_sf_id_upd_flg = CASE WHEN e633.lfsbu IS NOT NULL THEN 'U' ELSE NULL END
             ,process_comments = 'Updated mappings from S21.'
             ,conversion_id = '6000'
             ,DATABASE = 'S21-LFSPPS'
             ,source_name = 'OMSI' ;
  dbms_output.put_line(SQL%rowcount||' merged into canon_e633_clssvc_conv_tbl');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' merged into canon_e633_clssvc_conv_tbl',NULL,NULL,NULL,NULL);            
  
  UPDATE canon_e633_clssvc_conv_tbl
     SET process_flag = 'E'
         ,process_comments = 'Could not find a mapping in S21.'
         ,conv_load_date = SYSDATE
         ,conversion_id = '6000'
         ,DATABASE = 'S21-LFSPPS'
         ,source_name = 'OMSI'
      where process_flag IS NULL;
 dbms_output.put_line(SQL%rowcount||' could not be mapped');
 canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not be mapped in S21',NULL,NULL,NULL,NULL);
 commit;
  
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_closecalls_stg_tbl';
  dbms_output.put_line('canon_e633_closecalls_stg_tbl truncated');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_closecalls_stg_tbl truncated',NULL,NULL,NULL,NULL);
      
  INSERT INTO canon_e633_closecalls_stg_tbl(LEGACY_REF_ID  
                                            ,CONFIGURATION_NUMBER    
                                            ,JOBNUMBER  
                                            ,ARRIVAL_DATE  
                                            ,ACTION_CODE    
                                            ,CAUSE_CODE  
                                            ,MODULE_CODE   
                                            ,TIME_BETWEEN_JOBS   
                                            ,TIME_BETWEEN_FAILURE   
                                            ,COPIES_BETWEEN_JOBS  
                                            ,COPIES_BETWEEN_FAILURE  
                                            ,DE_INSTALLATION_JOB    
                                            ,INSTALLATION_JOB
                                            ,CM_JOB    
                                            ,PM_JOB    
                                            ,LAST_VISIT_TECHNICIAN   
                                            ,CLOSE
                                            ,CARD_CODE  
                                            ,CLOSED_DATE   
                                            ,REASON_CANCEL_CODE
                                            ,JOB_STATUS 
                                            ,ADDRESS_NUMBER 
                                            ,SOURCE
                                            ,FREE_TO_USE_1   
                                            ,FREE_TO_USE_2
                                            ,FREE_TO_USE_3   
                                            ,FREE_TO_USE_4    
                                            ,FREE_TO_USE_5  
                                            ,FREE_TO_USE_6
                                            ,CALL_CREATION_DATE   
                                            ,LAST_VISIT_TECH   
                                            ,ACCOUNT_NAME
                                            ,ADDRESS
                                            ,UNIQUE_KEY   
                                            ,LFS_SF_IB_ID  
                                            ,LFS_SF_ACCOUNT_ID 
                                            ,LFS_SF_CLOSE_SERVICE_ID 
                                            ,LFS_SF_STATUS_FLAG  
                                            ,LFS_SF_STATUS_MESSAGE
                                            ,LFS_LAST_UPDATE_DATE         
                                            ,PPS_SF_IB_ID
                                            ,PPS_SF_ACCOUNT_ID
                                            ,PPS_SF_CLOSE_SERVICE_ID 
                                            ,PPS_SF_STATUS_FLAG  
                                            ,PPS_SF_STATUS_MESSAGE 
                                            ,PPS_LAST_UPDATE_DATE       
                                            ,LFSBU  
                                            ,DPSBU 
                                            ,PPSBU) SELECT LEGACY_REF_ID  
                                                          ,CONFIGURATION_NUMBER    
                                                          ,JOBNUMBER  
                                                          ,ARRIVAL_DATE  
                                                          ,ACTION_CODE    
                                                          ,CAUSE_CODE  
                                                          ,MODULE_CODE   
                                                          ,TIME_BETWEEN_JOBS   
                                                          ,TIME_BETWEEN_FAILURE   
                                                          ,COPIES_BETWEEN_JOBS  
                                                          ,COPIES_BETWEEN_FAILURE  
                                                          ,DE_INSTALLATION_JOB    
                                                          ,INSTALLATION_JOB
                                                          ,CM_JOB    
                                                          ,PM_JOB    
                                                          ,LAST_VISIT_TECHNICIAN   
                                                          ,CLOSE
                                                          ,CARD_CODE  
                                                          ,CLOSED_DATE   
                                                          ,REASON_CANCEL_CODE
                                                          ,JOB_STATUS 
                                                          ,ADDRESS_NUMBER 
                                                          ,SOURCE
                                                          ,FREE_TO_USE_1   
                                                          ,FREE_TO_USE_2
                                                          ,FREE_TO_USE_3   
                                                          ,FREE_TO_USE_4    
                                                          ,FREE_TO_USE_5  
                                                          ,FREE_TO_USE_6
                                                          ,CALL_CREATION_DATE   
                                                          ,LAST_VISIT_TECH   
                                                          ,ACCOUNT_NAME
                                                          ,ADDRESS
                                                          ,UNIQUE_KEY   
                                                          ,LFS_SF_IB_ID  
                                                          ,LFS_SF_ACCOUNT_ID 
                                                          ,LFS_SF_CLOSE_SERVICE_ID 
                                                          ,LFS_SF_STATUS_FLAG  
                                                          ,LFS_SF_STATUS_MESSAGE
                                                          ,LFS_LAST_UPDATE_DATE         
                                                          ,PPS_SF_IB_ID
                                                          ,PPS_SF_ACCOUNT_ID
                                                          ,PPS_SF_CLOSE_SERVICE_ID 
                                                          ,PPS_SF_STATUS_FLAG  
                                                          ,PPS_SF_STATUS_MESSAGE 
                                                          ,PPS_LAST_UPDATE_DATE       
                                                          ,LFSBU  
                                                          ,DPSBU 
                                                          ,PPSBU
                                                    FROM canon_e633_clssvc_conv_tbl
                                                   WHERE process_flag = 'P';
  dbms_output.put_line(SQL%rowcount||' inserted into canon_e633_closecalls_stg_tbl');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' inserted into canon_e633_closecalls_stg_tbl',NULL,NULL,NULL,NULL);
  commit;                 
  
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
END load_close_svc_calls;

PROCEDURE load_ar_aging(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_ar_aging';
BEGIN
  retcode := 0;
 
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_ar_conv_tbl';
  dbms_output.put_line('canon_e633_ar_conv_tbl truncated');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_ar_conv_tbl truncated',NULL,NULL,NULL,NULL);
  
  INSERT INTO canon_e633_ar_conv_tbl(LEGACY_REF_ID   
                                    ,CURRENT_C   
                                    ,ONE_TO_30  
                                    ,THIRTYONE_TO_60 
                                    ,SIXTYONE_TO_90
                                    ,NINETYONE_TO_120 
                                    ,ONETWENTY_PLUS
                                    ,LFS_SF_ACCOUNT_ID
                                    ,LFS_SF_AR_ID
                                    ,LFS_SF_STATUS_FLAG
                                    ,LFS_SF_STATUS_MESSAGE
                                    ,LFS_LAST_UPDATE_DATE     
                                    ,PPS_SF_ACCOUNT_ID
                                    ,PPS_SF_AR_ID 
                                    ,PPS_SF_STATUS_FLAG
                                    ,PPS_SF_STATUS_MESSAGE
                                    ,PPS_LAST_UPDATE_DATE     
                                    ,LFSBU 
                                    ,DPSBU    
                                    ,PPSBU) SELECT LEGACY_REF_ID   
                                                    ,CURRENT_C   
                                                    ,ONE_TO_30  
                                                    ,THIRTYONE_TO_60 
                                                    ,SIXTYONE_TO_90
                                                    ,NINETYONE_TO_120 
                                                    ,ONETWENTY_PLUS
                                                    ,LFS_SF_ACCOUNT_ID
                                                    ,LFS_SF_AR_ID
                                                    ,LFS_SF_STATUS_FLAG
                                                    ,LFS_SF_STATUS_MESSAGE
                                                    ,LFS_LAST_UPDATE_DATE     
                                                    ,PPS_SF_ACCOUNT_ID
                                                    ,PPS_SF_AR_ID 
                                                    ,PPS_SF_STATUS_FLAG
                                                    ,PPS_SF_STATUS_MESSAGE
                                                    ,PPS_LAST_UPDATE_DATE     
                                                    ,LFSBU 
                                                    ,DPSBU    
                                                    ,ppsbu
                                                FROM canon_e633_ar_aging_stg_tbl@ebs11i.cusa.canon.com;
    dbms_output.put_line(SQL%rowcount||' inserted into canon_e633_ar_conv_tbl');
    canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' inserted into canon_e633_ar_conv_tbl',NULL,NULL,NULL,NULL);
    
    UPDATE canon_e633_ar_conv_tbl
       SET old_legacy_ref_id = legacy_ref_id;
    dbms_output.put_line('Backup of the IDs completed');
    canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Backup of the IDs completed',NULL,NULL,NULL,NULL);
        
    MERGE INTO canon_e633_ar_conv_tbl e633
      USING (SELECT database_name, 
                        conversion_id, 
                        SOURCE, 
                        lpad(old_location_id,10,'0') old_location_id, 
                        old_acct_num, 
                        s21_acct_num, 
                        s21_loc_num, 
                        s21_bill_to_cust_cd, 
                        s21_ship_to_cust_cd
                  FROM S21_CSA_REP.TMP_C182_CONV_CUST_ACCT_XREF
                  WHERE database_name = 'S21-LFSPPS'
                    AND SOURCE = 'SAP'
                    AND s21_ship_to_cust_cd IS NOT NULL)s21
          ON (e633.old_legacy_ref_id = s21.old_location_id)
        WHEN MATCHED THEN UPDATE SET
             legacy_ref_id = s21.s21_loc_num
            ,conv_load_date = SYSDATE
            ,process_flag = 'P'
            ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_ar_id IS NOT NULL THEN 'U' ELSE NULL END
            ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_ar_id IS NOT NULL THEN 'U' ELSE NULL END
            ,process_comments = 'Updated mappings from S21.' 
            ,conversion_id = s21.conversion_id
            ,database = s21.database_name
            ,source_name = s21.SOURCE  ;
  dbms_output.put_line(SQL%rowcount||' merged into canon_e633_ar_conv_tbl');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' merged into canon_e633_ar_conv_tbl',NULL,NULL,NULL,NULL);            
  
  UPDATE canon_e633_ar_conv_tbl
     SET process_flag = 'E'
         ,process_comments = 'Could not find a mapping in S21.'
         ,conv_load_date = SYSDATE
         ,conversion_id = '6000'
         ,DATABASE = 'S21-LFSPPS'
         ,source_name = 'OMSI'
      where process_flag IS NULL;
 dbms_output.put_line(SQL%rowcount||' could not be mapped');
 canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not be mapped in S21',NULL,NULL,NULL,NULL);
 commit;
  
  EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_ar_aging_stg_tbl';
  dbms_output.put_line('canon_e633_ar_aging_stg_tbl truncated');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','canon_e633_ar_aging_stg_tbl truncated',NULL,NULL,NULL,NULL);
      
  INSERT INTO canon_e633_ar_aging_stg_tbl(LEGACY_REF_ID   
                                          ,CURRENT_C   
                                          ,ONE_TO_30  
                                          ,THIRTYONE_TO_60 
                                          ,SIXTYONE_TO_90
                                          ,NINETYONE_TO_120 
                                          ,ONETWENTY_PLUS
                                          ,LFS_SF_ACCOUNT_ID
                                          ,LFS_SF_AR_ID
                                          ,LFS_SF_STATUS_FLAG
                                          ,LFS_SF_STATUS_MESSAGE
                                          ,LFS_LAST_UPDATE_DATE     
                                          ,PPS_SF_ACCOUNT_ID
                                          ,PPS_SF_AR_ID 
                                          ,PPS_SF_STATUS_FLAG
                                          ,PPS_SF_STATUS_MESSAGE
                                          ,PPS_LAST_UPDATE_DATE     
                                          ,LFSBU 
                                          ,DPSBU    
                                          ,ppsbu) SELECT LEGACY_REF_ID   
                                                        ,CURRENT_C   
                                                        ,ONE_TO_30  
                                                        ,THIRTYONE_TO_60 
                                                        ,SIXTYONE_TO_90
                                                        ,NINETYONE_TO_120 
                                                        ,ONETWENTY_PLUS
                                                        ,LFS_SF_ACCOUNT_ID
                                                        ,LFS_SF_AR_ID
                                                        ,LFS_SF_STATUS_FLAG
                                                        ,LFS_SF_STATUS_MESSAGE
                                                        ,LFS_LAST_UPDATE_DATE     
                                                        ,PPS_SF_ACCOUNT_ID
                                                        ,PPS_SF_AR_ID 
                                                        ,PPS_SF_STATUS_FLAG
                                                        ,PPS_SF_STATUS_MESSAGE
                                                        ,PPS_LAST_UPDATE_DATE     
                                                        ,LFSBU 
                                                        ,DPSBU    
                                                        ,ppsbu
                                                    FROM canon_e633_ar_conv_tbl
                                                   WHERE process_flag = 'P';
  dbms_output.put_line(SQL%rowcount||' inserted into canon_e633_ar_aging_stg_tbl');
  canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' inserted into canon_e633_ar_aging_stg_tbl',NULL,NULL,NULL,NULL);
  commit;                 
  
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
END load_ar_aging; 

PROCEDURE load_order_headers(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_order_Headers';

CURSOR c_order_headers
IS
SELECT c.cpo_ord_num ord_num,
       c.ord_src_ref_num,
	   e633.old_document_number
  FROM s21_csa_rep.cpo c, 
       canon_e633_ordhdr_conv_tbl e633
 WHERE 1 = 1
   AND LTRIM(e633.old_document_number, '0') = c.ord_src_ref_num
   AND c.ezcancelflag = '0'
   AND c.glbl_cmpy_cd = 'ADB';

TYPE c_ord_hdr_rec_tbl IS TABLE OF c_order_headers%ROWTYPE;
c_ord_hdr_tbl c_ord_hdr_rec_tbl;

BEGIN
	retcode := 0;
	
	  --truncate table
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_ORDHDR_CONV_TBL';
  
  INSERT INTO canon_e633_ordhdr_conv_tbl
	(client,							
	document_number,		
	creation_date,				
	creation_time,					
	creation_user,					
	interface_flag,					
	interface_date,				
	interface_time,				
	requested_delivery,
	sold_to,			
	order_type,
	order_reason,
	delivery_block,					
	billing_block,				
	collective_number,				
	last_comment_date,				
	last_comment_time,				
	last_comment_sequence,
	order_delete_flag,		
	order_header_changes_flag,
	name,	
	fast_track_id,
	fast_track_number,
	document_date,			
	order_signed_date,				
	original_paperwork,				
	original_requested_delivery_dt,
	lfs_sf_account_id,
	lfs_sf_order_id,			
	lfs_sf_status_flag,
	lfs_sf_status_message,			
	lfs_last_update_date,		
	pps_sf_account_id,			
	pps_sf_order_id,				
	pps_sf_status_flag,
	pps_sf_status_message,
	pps_last_update_date,		
	lfsbu,			
	ppsbu,							
	dpsbu)				
	SELECT 
		client,							
		document_number,		
		creation_date,				
		creation_time,					
		creation_user,					
		interface_flag,					
		interface_date,				
		interface_time,				
		requested_delivery,
		sold_to,			
		order_type,
		order_reason,
		delivery_block,					
		billing_block,				
		collective_number,				
		last_comment_date,				
		last_comment_time,				
		last_comment_sequence,
		order_delete_flag,		
		order_header_changes_flag,
		name,	
		fast_track_id,
		fast_track_number,
		document_date,			
		order_signed_date,				
		original_paperwork,				
		original_requested_delivery_dt,
		lfs_sf_account_id,
		lfs_sf_order_id,			
		lfs_sf_status_flag,
		lfs_sf_status_message,			
		lfs_last_update_date,		
		pps_sf_account_id,			
		pps_sf_order_id,				
		pps_sf_status_flag,
		pps_sf_status_message,
		pps_last_update_date,		
		lfsbu,			
		ppsbu,							
		dpsbu
	FROM CANON_E633_ORDHDR_STG_TBL@ebs11i.cusa.canon.com
	WHERE 1 = 1;
		
	dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_ordhdr_conv_tbl from CANMTH');
	canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_ordhdr_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);
	
	UPDATE canon_e633_ordhdr_conv_tbl
       SET old_document_number = document_number;
			  
        dbms_output.put_line('Backup of IDs completed');
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','copied old id''s',NULL,NULL,NULL,NULL);
		
		OPEN c_order_headers;
        FETCH c_order_headers BULK COLLECT INTO c_ord_hdr_tbl;
 
-- EXIT WHEN c_ord_cmnt_tbl.COUNT = 0;

         FOR indx IN 1 .. c_ord_hdr_tbl.COUNT
         LOOP
            BEGIN
               UPDATE CANON_E633_ORDHDR_CONV_TBL
                  SET document_number = c_ord_hdr_tbl(indx).ord_num
                      ,conv_load_date = SYSDATE
                      ,process_flag = 'P'
                      ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,process_comments = 'Updated mappings from S21.'
                      ,conversion_id = 6000
                      ,database = 'S21-LFSPPS'
                      ,source_name = 'SAP'
                WHERE old_document_number = c_ord_hdr_tbl(indx).old_document_number;

       
            EXCEPTION
               WHEN OTHERS
               THEN
                 dbms_output.put_line('Error: '||SQLERRM);
				 canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR', 'Error: '||SQLERRM, NULL,NULL,NULL,NULL);
            END;
         END LOOP;
         COMMIT;	
		
	/*MERGE INTO canon_e633_ordhdr_conv_tbl e633
          USING (SELECT c.cpo_ord_num ord_num,
					    c.ord_src_ref_num
                  FROM s21_csa_rep.cpo c
                  WHERE 1 = 1
				    AND c.ezcancelflag = '0'
					AND c.glbl_cmpy_cd = 'ADB'
					) s21
                ON(LTRIM(e633.old_document_number, '0') = s21.ord_src_ref_num)
               WHEN MATCHED THEN UPDATE
                  SET document_number = s21.ord_num
                      ,conv_load_date = SYSDATE
                      ,process_flag = 'P'
                      ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_account_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,process_comments = 'Updated mappings from S21.'
                      ,conversion_id = 6000
                      ,database = 'S21-LFSPPS'
                      ,source_name = 'SAP';
					  
			    dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);*/
				
	UPDATE canon_e633_ordhdr_conv_tbl e633
	   SET process_flag = 'E'
          ,process_comments = 'Could not find a mapping in S21.'
          ,conversion_id = 6000
          ,database = 'S21-LFSPPS'
          ,source_name = 'SAP'
     WHERE process_flag IS NULL ;
	 
			dbms_output.put_line('could not find mappings for '||SQL%rowcount);
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find LFS/PPS mappings in S21',NULL,NULL,NULL,NULL);
			
	
	-- copy the converted data to staging table for S21 to copy.
      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_ORDHDR_STG_TBL';
      dbms_output.put_line('Table CANON_E633_ORDHDR_STG_TBL Truncated.');
			canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Table canon_e633_cust_site_stg Truncated.',NULL,NULL,NULL,NULL);

      INSERT INTO CANON_E633_ORDHDR_STG_TBL (
			client,
			document_number,
			creation_date,
			creation_time,
			creation_user,
			interface_flag,
			interface_date,
			interface_time,
			requested_delivery,
			sold_to,
			order_type,
			order_reason,
			delivery_block,
			billing_block,
			collective_number,
			last_comment_date,
			last_comment_time,
			last_comment_sequence,
			order_delete_flag,
			order_header_changes_flag,
			name,
			fast_track_id,
			fast_track_number,
			document_date,
			order_signed_date,
			original_paperwork,
			original_requested_delivery_dt,
			lfs_sf_account_id,
			lfs_sf_order_id,
			lfs_sf_status_flag,
			lfs_sf_status_message,
			lfs_last_update_date,
			pps_sf_account_id,
			pps_sf_order_id,
			pps_sf_status_flag,
			pps_sf_status_message,
			pps_last_update_date,
			lfsbu,
			ppsbu,
			dpsbu
			) 
			SELECT client,
					document_number,
					creation_date,
					creation_time,
					creation_user,
					interface_flag,
					interface_date,
					interface_time,
					requested_delivery,
					sold_to,
					order_type,
					order_reason,
					delivery_block,
					billing_block,
					collective_number,
					last_comment_date,
					last_comment_time,
					last_comment_sequence,
					order_delete_flag,
					order_header_changes_flag,
					name,
					fast_track_id,
					fast_track_number,
					document_date,
					order_signed_date,
					original_paperwork,
					original_requested_delivery_dt,
					lfs_sf_account_id,
					lfs_sf_order_id,
					lfs_sf_status_flag,
					lfs_sf_status_message,
					lfs_last_update_date,
					pps_sf_account_id,
					pps_sf_order_id,
					pps_sf_status_flag,
					pps_sf_status_message,
					pps_last_update_date,
					lfsbu,
					ppsbu,
					dpsbu
			FROM canon_e633_ordhdr_conv_tbl
			WHERE process_flag = 'P';
					
         dbms_output.put_line('Inserted '||SQL%rowcount||' into CANON_E633_ORDHDR_STG_TBL from conversion table');
		 canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into CANON_E633_ORDHDR_STG_TBL from conversion table',NULL,NULL,NULL,NULL);

        COMMIT;
		
	
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
		
END load_order_headers;

PROCEDURE load_order_details(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_order_details';

CURSOR c_order_details
IS
SELECT c.cpo_ord_num ord_num,
       c.ord_src_ref_num,
       cd.cpo_dtl_line_num,
       cd.cpo_dtl_line_sub_num,
       cd.ord_src_ref_line_num,
       e633.old_sales_document,
       e633.old_sales_document_item
  FROM s21_csa_rep.cpo c,
       s21_csa_rep.cpo_dtl cd,
       canon_e633_orddtl_conv_tbl e633
 WHERE 1 = 1
   AND c.cpo_ord_num = cd.cpo_ord_num
   AND LTRIM(e633.old_sales_document, '0') = c.ord_src_ref_num
   AND e633.old_sales_document_item = cd.ord_src_ref_line_num
   AND c.ezcancelflag = '0'
   AND c.glbl_cmpy_cd = 'ADB';

TYPE c_ord_dtl_rec_tbl IS TABLE OF c_order_details%ROWTYPE;
c_ord_dtl_tbl c_ord_dtl_rec_tbl;

BEGIN
	retcode := 0;
	
	  --truncate table
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_ORDDTL_CONV_TBL';
  
  INSERT INTO canon_e633_orddtl_conv_tbl
	(
	client,
	sales_document,
	sales_document_item,
	creation_date,
	entry_time,
	creation_name,
	material_number,
	product_hierarchy,
	model_series,
	cumulative_order_quantity,
	current_line_status,
	last_status_date,
	last_status_time,
	interface_flag,
	interface_date,
	interface_time,
	serial_number,
	bill_of_lading,
	carrier_from,
	sales_rep_id,
	sales_rep_name,
	install_a_rep_id,
	install_a_rep_name,
	sales_office,
	sales_group,
	sales_district,
	generated_delivery_number,
	delivery_item,
	planned_goods_movement_date,
	actual_goods_movement_date,
	actual_ship_date,
	billing_document,
	billing_document_item,
	schd_ln_blocked_for_delv,
	billing_block_for_item,
	reason_for_rejection,
	order_is_gen_incomplete,
	overall_credit_status,
	net_price,
	order_line_delete_status,
	total_price,
	delivery_date,
	omsi_configuration_number,
	start_time_of_transaction,
	start_time_zone_of_transaction,
	alias_for_the_transaction,
	original_schedule_line_date,
	delivery_priority,
	ship_to_party,
	name,
	schedule_line_date,
	unique_key,
	lfs_sf_order_id,
	lfs_sf_ord_detail_id,
	lfs_sf_status_flag,
	lfs_sf_status_message,
	lfs_last_update_date,
	pps_sf_order_id,
	pps_sf_ord_detail_id,
	pps_sf_status_flag,
	pps_sf_status_message,
	pps_last_update_date,
	lfsbu,
	ppsbu,
	dpsbu
	)				
	SELECT 
		client,
		sales_document,
		sales_document_item,
		creation_date,
		entry_time,
		creation_name,
		material_number,
		product_hierarchy,
		model_series,
		cumulative_order_quantity,
		current_line_status,
		last_status_date,
		last_status_time,
		interface_flag,
		interface_date,
		interface_time,
		serial_number,
		bill_of_lading,
		carrier_from,
		sales_rep_id,
		sales_rep_name,
		install_a_rep_id,
		install_a_rep_name,
		sales_office,
		sales_group,
		sales_district,
		generated_delivery_number,
		delivery_item,
		planned_goods_movement_date,
		actual_goods_movement_date,
		actual_ship_date,
		billing_document,
		billing_document_item,
		schd_ln_blocked_for_delv,
		billing_block_for_item,
		reason_for_rejection,
		order_is_gen_incomplete,
		overall_credit_status,
		net_price,
		order_line_delete_status,
		total_price,
		delivery_date,
		omsi_configuration_number,
		start_time_of_transaction,
		start_time_zone_of_transaction,
		alias_for_the_transaction,
		original_schedule_line_date,
		delivery_priority,
		ship_to_party,
		name,
		schedule_line_date,
		unique_key,
		lfs_sf_order_id,
		lfs_sf_ord_detail_id,
		lfs_sf_status_flag,
		lfs_sf_status_message,
		lfs_last_update_date,
		pps_sf_order_id,
		pps_sf_ord_detail_id,
		pps_sf_status_flag,
		pps_sf_status_message,
		pps_last_update_date,
		lfsbu,
		ppsbu,
		dpsbu
	FROM CANON_E633_ORDDTL_STG_TBL@ebs11i.cusa.canon.com
	WHERE 1 = 1;
		
	dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_orddtl_conv_tbl from CANMTH');
	canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_orddtl_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);
	
	UPDATE canon_e633_orddtl_conv_tbl
       SET old_sales_document = sales_document,
		   old_sales_document_item = sales_document_item,
		   old_unique_key = unique_key;
			  
        dbms_output.put_line('Backup of IDs completed');
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','copied old id''s',NULL,NULL,NULL,NULL);
		
	OPEN c_order_details;
 FETCH c_order_details BULK COLLECT INTO c_ord_dtl_tbl;
 
-- EXIT WHEN c_ord_cmnt_tbl.COUNT = 0;

         FOR indx IN 1 .. c_ord_dtl_tbl.COUNT
         LOOP
            BEGIN
               UPDATE CANON_E633_ORDDTL_CONV_TBL
                  SET unique_key = c_ord_dtl_tbl(indx).ord_num || c_ord_dtl_tbl(indx).cpo_dtl_line_num || c_ord_dtl_tbl(indx).cpo_dtl_line_sub_num,
                      sales_document = c_ord_dtl_tbl(indx).ord_num,
                      conv_load_date = SYSDATE,
                      process_flag = 'P',
                      lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_ord_detail_id IS NOT NULL THEN 'U' ELSE NULL END,
                      pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_ord_detail_id IS NOT NULL THEN 'U' ELSE NULL END,
                      process_comments = 'Updated mappings from S21.',
                      conversion_id = 6000,
                      database = 'S21-LFSPPS',
                      source_name = 'SAP'
                WHERE old_sales_document = c_ord_dtl_tbl(indx).old_sales_document
                  AND old_sales_document_item = c_ord_dtl_tbl(indx).old_sales_document_item;

       
            EXCEPTION
               WHEN OTHERS
               THEN
                 dbms_output.put_line('Error: '||SQLERRM);
				 canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR', 'Error: '||SQLERRM, NULL,NULL,NULL,NULL);
            END;
         END LOOP;
         COMMIT;	
		 
		 --canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);
		
	/*MERGE INTO canon_e633_orddtl_conv_tbl e633
          USING (SELECT c.cpo_ord_num ord_num,
					    c.ord_src_ref_num,
						cd.cpo_dtl_line_num,
						cd.cpo_dtl_line_sub_num,
						cd.ord_src_ref_line_num
                  FROM s21_csa_rep.cpo c,
					   s21_csa_rep.cpo_dtl cd
                  WHERE 1 = 1
				    AND c.cpo_ord_num = cd.cpo_ord_num
				    AND c.ezcancelflag = '0'
					AND c.glbl_cmpy_cd = 'ADB'
					) s21
                ON((e633.old_sales_document||e633.old_sales_document_item) = (s21.ord_src_ref_num||s21.ord_src_ref_line_num))
               WHEN MATCHED THEN UPDATE
                  SET sales_document = s21.ord_num
				      ,unique_key = s21.ord_num || s21.cpo_dtl_line_num || s21.cpo_dtl_line_sub_num
                      ,conv_load_date = SYSDATE
                      ,process_flag = 'P'
                      ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_ord_detail_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_ord_detail_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,process_comments = 'Updated mappings from S21.'
                      ,conversion_id = 6000
                      ,database = 'S21-LFSPPS'
                      ,source_name = 'SAP';
					  
			    dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);*/
				
	UPDATE canon_e633_orddtl_conv_tbl e633
	   SET process_flag = 'E'
          ,process_comments = 'Could not find a mapping in S21.'
          ,conversion_id = 6000
          ,database = 'S21-LFSPPS'
          ,source_name = 'SAP'
     WHERE process_flag IS NULL ;
	 
			dbms_output.put_line('could not find mappings for '||SQL%rowcount);
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find LFS/PPS mappings in S21',NULL,NULL,NULL,NULL);
			
	
	-- copy the converted data to staging table for S21 to copy.
      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_ORDDTL_STG_TBL';
      dbms_output.put_line('Table CANON_E633_ORDDTL_STG_TBL Truncated.');
			canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Table canon_e633_cust_site_stg Truncated.',NULL,NULL,NULL,NULL);

      INSERT INTO CANON_E633_ORDDTL_STG_TBL (
			client,
			sales_document,
			sales_document_item,
			creation_date,
			entry_time,
			creation_name,
			material_number,
			product_hierarchy,
			model_series,
			cumulative_order_quantity,
			current_line_status,
			last_status_date,
			last_status_time,
			interface_flag,
			interface_date,
			interface_time,
			serial_number,
			bill_of_lading,
			carrier_from,
			sales_rep_id,
			sales_rep_name,
			install_a_rep_id,
			install_a_rep_name,
			sales_office,
			sales_group,
			sales_district,
			generated_delivery_number,
			delivery_item,
			planned_goods_movement_date,
			actual_goods_movement_date,
			actual_ship_date,
			billing_document,
			billing_document_item,
			schd_ln_blocked_for_delv,
			billing_block_for_item,
			reason_for_rejection,
			order_is_gen_incomplete,
			overall_credit_status,
			net_price,
			order_line_delete_status,
			total_price,
			delivery_date,
			omsi_configuration_number,
			start_time_of_transaction,
			start_time_zone_of_transaction,
			alias_for_the_transaction,
			original_schedule_line_date,
			delivery_priority,
			ship_to_party,
			name,
			schedule_line_date,
			unique_key,
			lfs_sf_order_id,
			lfs_sf_ord_detail_id,
			lfs_sf_status_flag,
			lfs_sf_status_message,
			lfs_last_update_date,
			pps_sf_order_id,
			pps_sf_ord_detail_id,
			pps_sf_status_flag,
			pps_sf_status_message,
			pps_last_update_date,
			lfsbu,
			ppsbu,
			dpsbu
			) 
			SELECT client,
					sales_document,
					sales_document_item,
					creation_date,
					entry_time,
					creation_name,
					material_number,
					product_hierarchy,
					model_series,
					cumulative_order_quantity,
					current_line_status,
					last_status_date,
					last_status_time,
					interface_flag,
					interface_date,
					interface_time,
					serial_number,
					bill_of_lading,
					carrier_from,
					sales_rep_id,
					sales_rep_name,
					install_a_rep_id,
					install_a_rep_name,
					sales_office,
					sales_group,
					sales_district,
					generated_delivery_number,
					delivery_item,
					planned_goods_movement_date,
					actual_goods_movement_date,
					actual_ship_date,
					billing_document,
					billing_document_item,
					schd_ln_blocked_for_delv,
					billing_block_for_item,
					reason_for_rejection,
					order_is_gen_incomplete,
					overall_credit_status,
					net_price,
					order_line_delete_status,
					total_price,
					delivery_date,
					omsi_configuration_number,
					start_time_of_transaction,
					start_time_zone_of_transaction,
					alias_for_the_transaction,
					original_schedule_line_date,
					delivery_priority,
					ship_to_party,
					name,
					schedule_line_date,
					unique_key,
					lfs_sf_order_id,
					lfs_sf_ord_detail_id,
					lfs_sf_status_flag,
					lfs_sf_status_message,
					lfs_last_update_date,
					pps_sf_order_id,
					pps_sf_ord_detail_id,
					pps_sf_status_flag,
					pps_sf_status_message,
					pps_last_update_date,
					lfsbu,
					ppsbu,
					dpsbu
			FROM canon_e633_orddtl_conv_tbl
			WHERE process_flag = 'P';
					
         dbms_output.put_line('Inserted '||SQL%rowcount||' into CANON_E633_ORDDTL_STG_TBL from conversion table');
		 canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into CANON_E633_ORDDTL_STG_TBL from conversion table',NULL,NULL,NULL,NULL);

        COMMIT;
		
	
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
		
END load_order_details;

PROCEDURE load_order_comments(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_order_comments';

CURSOR c_order_comments
IS
SELECT
  c.cpo_ord_num ord_num,
  c.ord_src_ref_num,
  ad.att_data_pk,
  ad.att_data_cmnt_clod,
  e633.text,
  e633.sales_document,
  e633.sequence_number,
  e633.old_unique_key
FROM
  s21_csa_rep.cpo_v c,
  s21_csa_rep.att_data ad,
  canon_e633_ordcmt_conv_tbl e633
WHERE
  1                   = 1
AND ad.att_data_tp_cd = 'NT'
AND ad.ezbusinessid   ='NWAL1500'
AND ad.glbl_cmpy_cd   = 'ADB'
AND ad.ezcancelflag   = '0'
AND c.ezcancelflag    = '0'
AND c.glbl_cmpy_cd    = 'ADB'
AND c.cpo_ord_num     = ad.att_data_grp_txt
AND c.line_biz_tp_cd IN ('LFS','PPS')
AND c.ord_src_ref_num = LTRIM(e633.old_sales_document, '0')
AND ad.att_data_cmnt_clod LIKE '%'||NVL(e633.text, 'XXX')||'%';

TYPE c_ord_cmnt_rec_tbl IS TABLE OF c_order_comments%ROWTYPE;
c_ord_cmnt_tbl c_ord_cmnt_rec_tbl;

BEGIN
	retcode := 0;
	
	  --truncate table
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_ORDCMT_CONV_TBL';
  
  INSERT INTO canon_e633_ordcmt_conv_tbl
	(
		client,
		sales_document,
		sequence_number,
		interface_flag,
		interface_date,
		interface_time,
		text,
		creation_date,
		entry_time,
		created_by,
		unique_key,
		lfs_sf_order_id,
		lfs_sf_ord_comment_id,
		lfs_sf_status_flag,
		lfs_sf_status_message,
		lfs_last_update_date,
		pps_sf_order_id,
		pps_sf_ord_comment_id,
		pps_sf_status_flag,
		pps_sf_status_message,
		pps_last_update_date,
		lfsbu,
		ppsbu,
		dpsbu
	)				
	SELECT 
		client,
		sales_document,
		sequence_number,
		interface_flag,
		interface_date,
		interface_time,
		text,
		creation_date,
		entry_time,
		created_by,
		unique_key,
		lfs_sf_order_id,
		lfs_sf_ord_comment_id,
		lfs_sf_status_flag,
		lfs_sf_status_message,
		lfs_last_update_date,
		pps_sf_order_id,
		pps_sf_ord_comment_id,
		pps_sf_status_flag,
		pps_sf_status_message,
		pps_last_update_date,
		lfsbu,
		ppsbu,
		dpsbu
	FROM CANON_E633_ORDCMT_STG_TBL@ebs11i.cusa.canon.com
	WHERE 1 = 1;
		
	dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_ordcmt_conv_tbl from CANMTH');
	canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_ordcmt_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);
	
	UPDATE canon_e633_ordcmt_conv_tbl
       SET old_sales_document = sales_document,
		   old_unique_key = unique_key;
			  
        dbms_output.put_line('Backup of IDs completed');
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','copied old id''s',NULL,NULL,NULL,NULL);
		
		OPEN c_order_comments;
		FETCH c_order_comments BULK COLLECT INTO c_ord_cmnt_tbl;
		
		FOR indx IN 1 .. c_ord_cmnt_tbl.COUNT
         LOOP
            BEGIN
               UPDATE canon_e633_ordcmt_conv_tbl
                  SET unique_key = c_ord_cmnt_tbl(indx).att_data_pk || c_ord_cmnt_tbl(indx).sequence_number,
                      sales_document = c_ord_cmnt_tbl(indx).ord_num,
                      att_data_pk = c_ord_cmnt_tbl(indx).att_data_pk,
                      conv_load_date = SYSDATE,
                      process_flag = 'P',
                      lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_ord_comment_id IS NOT NULL THEN 'U' ELSE NULL END,
                      pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_ord_comment_id IS NOT NULL THEN 'U' ELSE NULL END,
                      process_comments = 'Updated mappings from S21.',
                      conversion_id = 6000,
                      database = 'S21-LFSPPS',
                      source_name = 'SAP'
                WHERE old_unique_key = c_ord_cmnt_tbl(indx).old_unique_key;
      
            EXCEPTION
               WHEN OTHERS
               THEN
                 dbms_output.put_line('Error: '||SQLERRM);
				 canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR', 'Error: '||SQLERRM, NULL,NULL,NULL,NULL);
            END;
         END LOOP;
         COMMIT;
		 
		 --canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);
		
	/*MERGE INTO canon_e633_ordcmt_conv_tbl e633
          USING (SELECT c.cpo_ord_num ord_num,
					    c.ord_src_ref_num,
						ad.att_data_pk,
						ad.att_data_cmnt_clod
                  FROM s21_csa_rep.cpo_v c,
					   s21_csa_rep.att_data ad
                  WHERE 1 = 1
				     AND ad.att_data_tp_cd = 'NT'
                     AND ad.ezbusinessid='NWAL1500'
                     AND ad.glbl_cmpy_cd = 'ADB'
                     AND ad.ezcancelflag = '0'
                     AND c.ezcancelflag = '0'
                     AND c.glbl_cmpy_cd = 'ADB'
                     AND c.cpo_ord_num = ad.att_data_grp_txt
                     AND c.line_biz_tp_cd IN ('LFS','PPS')
					) s21
                ON(e633.old_sales_document = s21.ord_src_ref_num
				AND s21.att_data_cmnt_clod LIKE '%'||NVL(e633.text, 'XXX')||'%')
               WHEN MATCHED THEN UPDATE
                  SET sales_document = s21.ord_num
					  ,att_data_pk = s21.att_data_pk
				      ,unique_key = s21.att_data_pk || sequence_number
                      ,conv_load_date = SYSDATE
                      ,process_flag = 'P'
                      ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_ord_comment_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_ord_comment_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,process_comments = 'Updated mappings from S21.'
                      ,conversion_id = 6000
                      ,database = 'S21-LFSPPS'
                      ,source_name = 'SAP';
					  
			    dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);*/
				
	UPDATE canon_e633_ordcmt_conv_tbl e633
	   SET process_flag = 'E'
          ,process_comments = 'Could not find a mapping in S21.'
          ,conversion_id = 6000
          ,database = 'S21-LFSPPS'
          ,source_name = 'SAP'
     WHERE process_flag IS NULL ;
	 
			dbms_output.put_line('could not find mappings for '||SQL%rowcount);
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find LFS/PPS mappings in S21',NULL,NULL,NULL,NULL);
			
	
	-- copy the converted data to staging table for S21 to copy.
      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_ORDCMT_STG_TBL';
      dbms_output.put_line('Table CANON_E633_ORDCMT_STG_TBL Truncated.');
			canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Table canon_e633_cust_site_stg Truncated.',NULL,NULL,NULL,NULL);

      INSERT INTO CANON_E633_ORDCMT_STG_TBL (
			client,
			sales_document,
			sequence_number,
			interface_flag,
			interface_date,
			interface_time,
			text,
			creation_date,
			entry_time,
			created_by,
			unique_key,
			lfs_sf_order_id,
			lfs_sf_ord_comment_id,
			lfs_sf_status_flag,
			lfs_sf_status_message,
			lfs_last_update_date,
			pps_sf_order_id,
			pps_sf_ord_comment_id,
			pps_sf_status_flag,
			pps_sf_status_message,
			pps_last_update_date,
			lfsbu,
			ppsbu,
			dpsbu,
			att_data_pk
			) 
			SELECT client,
					sales_document,
					sequence_number,
					interface_flag,
					interface_date,
					interface_time,
					text,
					creation_date,
					entry_time,
					created_by,
					unique_key,
					lfs_sf_order_id,
					lfs_sf_ord_comment_id,
					lfs_sf_status_flag,
					lfs_sf_status_message,
					lfs_last_update_date,
					pps_sf_order_id,
					pps_sf_ord_comment_id,
					pps_sf_status_flag,
					pps_sf_status_message,
					pps_last_update_date,
					lfsbu,
					ppsbu,
					dpsbu,
					att_data_pk
			FROM canon_e633_ordcmt_conv_tbl
			WHERE process_flag = 'P';
					
         dbms_output.put_line('Inserted '||SQL%rowcount||' into CANON_E633_ORDCMT_STG_TBL from conversion table');
		 canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into CANON_E633_ORDCMT_STG_TBL from conversion table',NULL,NULL,NULL,NULL);

        COMMIT;
		
	
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
		
END load_order_comments;


PROCEDURE load_contracts(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
l_procedure_name         VARCHAR2(60) := 'load_contracts';

CURSOR c_contract_details
IS
select e633.old_contract_no,
       e633.old_config_nbr,
       e633.old_component_seq,
       e633.old_condition_set_version,
	   dc.ds_contr_pk,
       tcc.s21_contr_num,
       dcd.svc_config_mstr_pk,
       dcd.ds_contr_dtl_pk,
       NULL ds_contr_bllg_mtr_pk, 
       c185.component_seq_num,
       NULL mnf_item_cd,
       e633.old_unique_key,
       (tcc.s21_contr_num||dcd.ds_contr_dtl_pk) new_unique_key
from canon_e633_contracts_conv_tbl e633,
     s21_csa_rep.tmp_c185_contr_pk_map tcc,
     s21_csa_rep.tmp_c183_config_pk_map tcco,
     s21_csa_rep.canon_c185_contr_dtl_ext c185,
     svc_mach_mstr smm,
     ds_contr dc,
     ds_contr_dtl dcd
    -- mdse m
where 1 = 1
  AND e633.old_contract_no = tcc.contract_number
  AND e633.old_config_nbr = tcco.old_config_number
  AND dc.ds_contr_num = to_char(tcc.s21_contr_num)
  AND dc.ds_contr_pk = dcd.ds_contr_pk
  --AND smm.mdse_cd = m.mdse_cd
  --AND e633.component_type like m.mnf_item_cd||'%' --AND m.mnf_item_cd = e633.component_type
  AND dcd.svc_config_mstr_pk = tcco.s21_svc_config_mstr_pk
  AND dcd.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
  AND dc.ezcancelflag = '0'
  AND dc.glbl_cmpy_cd = 'ADB'
  AND dcd.ezcancelflag = '0'
  AND dcd.glbl_cmpy_cd = 'ADB'
  AND smm.ezcancelflag = '0'
  AND smm.glbl_cmpy_cd = 'ADB'
  --AND m.ezcancelflag = '0'
  --AND m.glbl_cmpy_cd = 'ADB'
  AND DECODE(e633.component_seq, 0, 1,
                                 900, 1, 
                                 950, 1, 
                                 951, 1,
                                 952, 1,
                                 953, 1,
                                 954, 1,
                                 955, 1,
                                 956, 1,
                                 957, 1,
                                 958, 1,
                                 e633.component_seq) = smm.svc_mach_sq_num
  AND e633.component_seq = c185.component_seq_num(+)
  AND e633.old_contract_no = c185.contract_id(+)
  AND e633.old_config_nbr = c185.config_id(+)
  AND e633.end_date IS NULL
  AND e633.meter_type IS NULL
  AND e633.component_type = c185.item_code --RG changes May62019
 -- AND e633.old_contract_no IN ('832584', '832640', '832208', '832579', '832332','831420', '832637')
  UNION
     select e633.old_contract_no,
       e633.old_config_nbr,
       e633.old_component_seq,
       e633.old_condition_set_version,
	   dc.ds_contr_pk,
       tcc.s21_contr_num,
       dcd.svc_config_mstr_pk,
       dcd.ds_contr_dtl_pk,
       dcbm.ds_contr_bllg_mtr_pk,
       c185.component_seq_num,
       NULL mnf_item_cd,
       e633.old_unique_key,
       (tcc.s21_contr_num||dcd.ds_contr_dtl_pk||dcbm.ds_contr_bllg_mtr_pk||spm.svc_phys_mtr_pk) new_unique_key
from canon_e633_contracts_conv_tbl e633,
     s21_csa_rep.tmp_c185_contr_pk_map tcc,
     s21_csa_rep.tmp_c183_config_pk_map tcco,
     s21_csa_rep.canon_c185_contr_dtl_ext c185,
     svc_mach_mstr smm,
     ds_contr dc,
     ds_contr_dtl dcd,
     svc_config_mstr scm,
	 mdl_nm mn,
     ds_contr_bllg_mtr dcbm,
	 s21_csa_rep.tmp_ocs_contr_config_map tocc,
     --mdse m,
	 contr_phys_bllg_mtr_reln cpb,
     svc_phys_mtr spm
where 1 = 1
  AND e633.old_contract_no = tcc.contract_number
  AND e633.old_config_nbr = tcco.old_config_number
  AND dc.ds_contr_num = to_char(tcc.s21_contr_num)
  AND dc.ds_contr_pk = dcd.ds_contr_pk
  --AND smm.mdse_cd = m.mdse_cd
  --AND e633.component_type like m.mnf_item_cd||'%' --AND m.mnf_item_cd = e633.component_type
  AND dcd.svc_config_mstr_pk = tcco.s21_svc_config_mstr_pk
  AND dcd.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
  AND dcd.svc_config_mstr_pk = scm.svc_config_mstr_pk
  AND dc.ezcancelflag = '0'
  AND dc.glbl_cmpy_cd = 'ADB'
  AND dcd.ezcancelflag = '0'
  AND dcd.glbl_cmpy_cd = 'ADB'
  AND smm.ezcancelflag = '0'
  AND smm.glbl_cmpy_cd = 'ADB'
  AND scm.ezcancelflag = '0'
  AND scm.glbl_cmpy_cd = 'ADB'
  --AND m.ezcancelflag = '0'
  --AND m.glbl_cmpy_cd = 'ADB'
  AND scm.mdl_id = mn.t_mdl_id
  AND DECODE(e633.component_seq, 0, 1,
                                 900, 1, 
                                 950, 1, 
                                 951, 1,
                                 952, 1,
                                 953, 1,
                                 954, 1,
                                 955, 1,
                                 956, 1,
                                 957, 1,
                                 958, 1,
                                 e633.component_seq) = smm.svc_mach_sq_num
  AND e633.component_seq = c185.component_seq_num(+)
  AND e633.old_contract_no = c185.contract_id(+)
  AND e633.old_config_nbr = c185.config_id(+)
  AND dcd.ds_contr_dtl_pk      = dcbm.ds_contr_dtl_pk
  AND e633.end_date IS NULL
  AND e633.meter_type IS NOT NULL
  AND e633.component_type = c185.item_code --RG changes May62019
  AND tocc.contract_number = e633.old_contract_no
  AND tocc.config_number = e633.old_config_nbr
  AND tocc.seq_number = e633.component_seq
  AND tocc.bllg_s21_meter_lb_cd = dcbm.bllg_mtr_lb_cd
  AND dcbm.ds_contr_bllg_mtr_pk = cpb.ds_contr_bllg_mtr_pk
  AND cpb.svc_phys_mtr_pk = spm.svc_phys_mtr_pk
  AND spm.mdl_mtr_lb_cd = tocc.phys_s21_meter_lb_cd
  AND tocc.meter_type = e633.meter_type
  AND mn.t_mdl_nm = tocc.config_type;
  --AND e633.old_contract_no IN ('832584', '832640', '832208', '832579', '832332','831420', '832637');
  
  /*CURSOR c_contract_details
IS
  select e633.old_contract_no,
       e633.old_config_nbr,
       e633.old_component_seq,
       e633.old_condition_set_version,
	   dc.ds_contr_pk,
       tcc.s21_contr_num,
       dcd.svc_config_mstr_pk,
       dcd.ds_contr_dtl_pk,
       dcbm.ds_contr_bllg_mtr_pk,
       c185.component_seq_num,
       m.mnf_item_cd,
       e633.old_unique_key,
       (tcc.s21_contr_num||dcd.ds_contr_dtl_pk||dcbm.ds_contr_bllg_mtr_pk) new_unique_key
from canon_e633_contracts_conv_tbl e633,
     s21_csa_rep.tmp_c185_contr_pk_map tcc,
     s21_csa_rep.tmp_c183_config_pk_map tcco,
     s21_csa_rep.canon_c185_contr_dtl_ext c185,
     svc_mach_mstr smm,
     ds_contr dc,
     ds_contr_dtl dcd,
     svc_config_mstr scm,
	   mdl_nm mn,
     ds_contr_bllg_mtr dcbm,
	   s21_csa_rep.tmp_meter_mapping tmm,
     mdse m
where 1 = 1
  AND e633.old_contract_no = tcc.contract_number
  AND e633.old_config_nbr = tcco.old_config_number
  AND dc.ds_contr_num = to_char(tcc.s21_contr_num)
  AND dc.ds_contr_pk = dcd.ds_contr_pk
  AND smm.mdse_cd = m.mdse_cd
  AND m.mnf_item_cd = e633.component_type
  AND dcd.svc_config_mstr_pk = tcco.s21_svc_config_mstr_pk
  AND dcd.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
  AND dcd.svc_config_mstr_pk = scm.svc_config_mstr_pk
  AND dc.ezcancelflag = '0'
  AND dc.glbl_cmpy_cd = 'ADB'
  AND dcd.ezcancelflag = '0'
  AND dcd.glbl_cmpy_cd = 'ADB'
  AND smm.ezcancelflag = '0'
  AND smm.glbl_cmpy_cd = 'ADB'
  AND scm.ezcancelflag = '0'
  AND scm.glbl_cmpy_cd = 'ADB'
  AND m.ezcancelflag = '0'
  AND m.glbl_cmpy_cd = 'ADB'
  AND scm.mdl_id = mn.t_mdl_id
  AND mn.t_mdl_nm = tmm.config_type(+)
  AND tmm.meter_type(+) = e633.meter_type
  AND e633.component_seq = c185.component_seq_num(+)
  AND e633.old_contract_no = c185.contract_id(+)
  AND e633.old_config_nbr = c185.config_id(+)
  AND dcd.ds_contr_dtl_pk      = dcbm.ds_contr_dtl_pk(+)
  AND e633.end_date IS NULL
  --AND e633.meter_type IS NOT NULL
  AND dcbm.bllg_mtr_lb_cd(+) = tmm.s21_meter_lb_cd 
  AND tmm.s21_mtr_lb_tp_cd(+) = 'B'
  AND tmm.pl1_flg(+) = 'N';*/

TYPE c_contract_rec_tbl IS TABLE OF c_contract_details%ROWTYPE;
c_cont_det_tbl c_contract_rec_tbl;

BEGIN
	retcode := 0;
	
	  --truncate table
  EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_CONTRACTS_CONV_TBL';
  
  INSERT INTO canon_e633_contracts_conv_tbl
	(
	id,
	contract_no,
	contract,
	contract_type,
	billing_type,
	pool_comp_free_co,
	config_nbr,
	start_date,
	end_date,
	boundary_1_busine,
	boundary_2_busine,
	boundary_3_busine,
	first_copy_price_1,
	first_copy_price_2,
	first_copy_price_3,
	first_copy_price_4,
	min_vol_amt_bus,
	min_vol_amt_ind,
	first_involved_bu,
	scnd_involved_bu,
	first_fixed_amount,
	scnd_fixed_amount,
	adv_arrear_ind_1,
	adv_arrear_ind_2,
	adv_arrear_ind_3,
	first_fixed_bill_fr,
	scnd_fixed_bill_fr,
	var_bill_frequenc,
	first_1,
	second_1,
	variable_1,
	ref_field_1,
	sevice_type,
	coverage,
	fixed_price_end_1,
	price_chg_code_1,
	fixed_price_prd_1,
	max_price_uplft_1,
	fixed_price_end_2,
	price_chg_code_2,
	fixed_price_prd_2,
	max_price_uplft_2,
	var_prc_end_dt_1,
	var_price_chg_cd1,
	var_prc_period_2,
	var_max_prc_perc1,
	var_prc_end_dt_2,
	var_price_chg_cd2,
	var_prc_period_2_2,
	var_max_prc_perc2,
	currency_code,
	component_seq,
	condition_set_version,
	purchase_option,
	address_number,
	expiration_date,
	source,
	free_to_use_1,
	free_to_use_2,
	free_to_use_3,
	free_to_use_4,
	free_to_use_5,
	free_to_use_6,
	meter_type,
	label,
	label_descrip,
	description,
	component_type,
	commercial_name_language_1,
	unique_key,
	lfs_sf_ib_id,
	lfs_sf_account_id,
	lfs_sf_contract_id,
	lfs_sf_status_flag,
	lfs_sf_status_message,
	lfs_last_update_date,
	pps_sf_ib_id,
	pps_sf_account_id,
	pps_sf_contract_id,
	pps_sf_status_flag,
	pps_sf_status_message,
	pps_last_update_date,
	lfsbu,
	dpsbu,
	ppsbu,
	bill_to_acct_name,
	bill_to_address
	)				
	SELECT 
		id,
		contract_no,
		contract,
		contract_type,
		billing_type,
		pool_comp_free_co,
		config_nbr,
		start_date,
		end_date,
		boundary_1_busine,
		boundary_2_busine,
		boundary_3_busine,
		first_copy_price_1,
		first_copy_price_2,
		first_copy_price_3,
		first_copy_price_4,
		min_vol_amt_bus,
		min_vol_amt_ind,
		first_involved_bu,
		scnd_involved_bu,
		first_fixed_amount,
		scnd_fixed_amount,
		adv_arrear_ind_1,
		adv_arrear_ind_2,
		adv_arrear_ind_3,
		first_fixed_bill_fr,
		scnd_fixed_bill_fr,
		var_bill_frequenc,
		first_1,
		second_1,
		variable_1,
		ref_field_1,
		sevice_type,
		coverage,
		fixed_price_end_1,
		price_chg_code_1,
		fixed_price_prd_1,
		max_price_uplft_1,
		fixed_price_end_2,
		price_chg_code_2,
		fixed_price_prd_2,
		max_price_uplft_2,
		var_prc_end_dt_1,
		var_price_chg_cd1,
		var_prc_period_2,
		var_max_prc_perc1,
		var_prc_end_dt_2,
		var_price_chg_cd2,
		var_prc_period_2_2,
		var_max_prc_perc2,
		currency_code,
		component_seq,
		condition_set_version,
		purchase_option,
		address_number,
		expiration_date,
		source,
		free_to_use_1,
		free_to_use_2,
		free_to_use_3,
		free_to_use_4,
		free_to_use_5,
		free_to_use_6,
		meter_type,
		label,
		label_descrip,
		description,
		component_type,
		commercial_name_language_1,
		unique_key,
		lfs_sf_ib_id,
		lfs_sf_account_id,
		lfs_sf_contract_id,
		lfs_sf_status_flag,
		lfs_sf_status_message,
		lfs_last_update_date,
		pps_sf_ib_id,
		pps_sf_account_id,
		pps_sf_contract_id,
		pps_sf_status_flag,
		pps_sf_status_message,
		pps_last_update_date,
		lfsbu,
		dpsbu,
		ppsbu,
		bill_to_acct_name,
		bill_to_address
	FROM CANON_E633_CONTRACTS_STG_TBL@ebs11i.cusa.canon.com 
	WHERE 1 = 1
    AND (contract_no, config_nbr, component_type, condition_set_version) IN
                (SELECT contract_no, config_nbr, component_type, condition_set_version 
                   FROM
                      (SELECT contract_no, 
                              config_nbr, 
                              component_type, 
                              condition_set_version, 
                              ROW_NUMBER() OVER (PARTITION BY contract_no, config_nbr, component_type order by condition_set_version desc) rn
                         FROM CANON_E633_CONTRACTS_STG_TBL@ebs11i.cusa.canon.com 
                      )
                  WHERE rn = 1)
    AND end_date IS NULL
    AND nvl(substr(expiration_date, 9, 2), '99') <> '00'
    AND NVL(TO_DATE(expiration_date, 'YYYY/MM/DD'), SYSDATE) >= SYSDATE;
	
		
	dbms_output.put_line('Inserted '||SQL%rowcount||' into canon_e633_contracts_conv_tbl from CANMTH');
	canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into canon_e633_contracts_conv_tbl from CANMTH',NULL,NULL,NULL,NULL);
	
	UPDATE canon_e633_contracts_conv_tbl
       SET old_contract_no = contract_no,
		   old_contract = contract,
		   old_config_nbr = config_nbr,
		   old_component_seq = component_seq,
		   old_condition_set_version = condition_set_version,
		   old_unique_key = unique_key;
			  
        dbms_output.put_line('Backup of IDs completed');
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','copied old id''s',NULL,NULL,NULL,NULL);
	
	COMMIT;
	
	EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E633_CONTRACTS_CONV_TBL COMPUTE STATISTICS';
		
		OPEN c_contract_details;
		FETCH c_contract_details BULK COLLECT INTO c_cont_det_tbl;
		
		BEGIN
		FORALL indx IN 1 .. c_cont_det_tbl.COUNT
        -- LOOP
                UPDATE canon_e633_contracts_conv_tbl
                  SET unique_key = c_cont_det_tbl(indx).new_unique_key ,
                      contract_no = c_cont_det_tbl(indx).s21_contr_num,
					  config_nbr = c_cont_det_tbl(indx).svc_config_mstr_pk,
					  contract = c_cont_det_tbl(indx).ds_contr_pk,
                      conv_load_date = SYSDATE,
                      process_flag = 'P',
                      lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_contract_id IS NOT NULL THEN 'U' ELSE NULL END,
                      pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_contract_id IS NOT NULL THEN 'U' ELSE NULL END,
                      process_comments = 'Updated mappings from S21.',
                      conversion_id = 6000,
                      database = 'S21-LFSPPS',
                      source_name = 'SAP'
                WHERE old_unique_key = c_cont_det_tbl(indx).old_unique_key;
				

      
            EXCEPTION
               WHEN OTHERS
               THEN
                 dbms_output.put_line('Error: '||SQLERRM);
				 canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR', 'Error: '||SQLERRM, NULL,NULL,NULL,NULL);
            END;
         --END LOOP;
         COMMIT;
		 
	/*MERGE INTO canon_e633_contracts_conv_tbl e633
          USING (SELECT DISTINCT 
						tcc.contract_number,
						tcco.old_config_number,
						dc.ds_contr_pk contract,
                        dc.ds_contr_num contract_no,
						dcd.svc_config_mstr_pk,
						dcd.svc_mach_mstr_pk,
						dcbm.ds_contr_bllg_mtr_pk,
						dcd.ds_contr_dtl_pk component_seq
                  FROM s21_csa_rep.tmp_c185_contr_pk_map tcc,
					   s21_csa_rep.tmp_c183_config_pk_map tcco,
					   ds_contr dc,
                       ds_contr_dtl dcd,
                       ds_contr_bllg_mtr dcbm,
                       --mdse dmi,
                       --ahs a
                       --svc_term_cond stc
                       mtr_lb ml
                       --bill_to_cust btc
				  WHERE dc.ezcancelflag = '0'
					AND dc.glbl_cmpy_cd = 'ADB'
					AND dcd.ezcancelflag = '0'
					AND dcd.glbl_cmpy_cd = 'ADB'
					AND dcbm.ezcancelflag(+) = '0'
					AND dcbm.glbl_cmpy_cd(+) = 'ADB'
					--AND dmi.ezcancelflag = '0'
					--AND dmi.glbl_cmpy_cd = 'ADB'
					--AND a.ezcancelflag(+) = '0'
					--AND a.glbl_cmpy_cd(+) = 'ADB'
					--AND stc.ezcancelflag(+) = '0'
					--AND stc.glbl_cmpy_cd(+) = 'ADB'
					AND ml.ezcancelflag(+) = '0'
					AND ml.glbl_cmpy_cd(+) = 'ADB'
					--AND btc.ezcancelflag = '0'
					--AND btc.glbl_cmpy_cd = 'ADB'
					AND dc.ds_contr_sts_cd IN ('3','4') -- Approved, effective
					AND dcd.ds_contr_dtl_sts_cd IN ('ACTV') -- active
					AND dc.ds_contr_num = tcc.s21_contr_num
					AND dc.ds_contr_pk = dcd.ds_contr_pk
					AND dcd.svc_config_mstr_pk = tcco.s21_svc_config_mstr_pk
					AND dcd.ds_contr_dtl_pk = dcbm.ds_contr_dtl_pk(+)
					--AND dcd.svc_pgm_mdse_cd = dmi.mdse_cd
					--AND dcd.ds_contr_dtl_pk = stc.ds_contr_dtl_pk(+)
				    --AND stc.svc_term_cond_attrb_pk = '18' --for AHS
				    --AND stc.svc_term_attrb_map_val_cd = a.ahs_cd(+)
					AND dcbm.bllg_mtr_lb_cd = ml.mtr_lb_cd(+)
					--AND dcd.base_bill_to_cust_cd = btc.bill_to_cust_cd
					) s21
                ON(e633.old_contract_no = s21.contract_number
				  AND e633.old_config_nbr = s21.old_config_number)
               WHEN MATCHED THEN UPDATE
                  SET contract_no = s21.contract_no
				      ,contract = s21.contract,
					  ,config_nbr = s21.svc_config_mstr_pk
					  ,component_seq = s21.component_seq
				      ,unique_key = s21.contract_no || s21.svc_mach_mstr_pk || s21.component_seq || s21.ds_contr_bllg_mtr_pk
                      ,conv_load_date = SYSDATE
                      ,process_flag = 'P'
                      ,lfs_sf_id_upd_flg = CASE WHEN lfsbu IS NOT NULL AND lfs_sf_contract_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,pps_sf_id_upd_flg = CASE WHEN ppsbu IS NOT NULL AND pps_sf_contract_id IS NOT NULL THEN 'U' ELSE NULL END
                      ,process_comments = 'Updated mappings from S21.'
                      ,conversion_id = 6000
                      ,database = 'S21-LFSPPS'
                      ,source_name = 'SAP';
					  
			    dbms_output.put_line('Merged '||SQL%rowcount||' mappings from S21');
				canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','Merged '||SQL%rowcount||' mappings from S21',NULL,NULL,NULL,NULL);*/
				
	UPDATE canon_e633_contracts_conv_tbl e633
	   SET process_flag = 'E'
          ,process_comments = 'Could not find a mapping in S21.'
          ,conversion_id = 6000
          ,database = 'S21-LFSPPS'
          ,source_name = 'SAP'
     WHERE process_flag IS NULL ;
	 
			dbms_output.put_line('could not find mappings for '||SQL%rowcount);
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' could not find LFS/PPS mappings in S21',NULL,NULL,NULL,NULL);
	
	UPDATE canon_e633_contracts_conv_tbl
	   SET process_flag='E'
	 WHERE (contract_no, config_nbr, component_type, component_seq) IN
				(SELECT contract_no, config_nbr, component_type, component_seq 
					   FROM
						  (SELECT contract_no, 
								  config_nbr, 
								  component_type, 
								  component_seq, 
								  ROW_NUMBER() OVER (PARTITION BY contract_no, config_nbr, component_type order by component_seq ) rn
							 FROM canon_e633_contracts_conv_tbl
							 where unique_key in (select unique_key
												  from canon_e633_contracts_conv_tbl --RG changes May62019
												  group by unique_key
												  having count(*) > 1)
						  )
						WHERE rn = 1);
						
			dbms_output.put_line('Eliminated Duplicate Records: '||SQL%rowcount);
			canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' Duplicate records eliminated',NULL,NULL,NULL,NULL);
	
	-- copy the converted data to staging table for S21 to copy.
      EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E633_CONTRACTS_STG_TBL';
      dbms_output.put_line('Table CANON_E633_CONTRACTS_STG_TBL Truncated.');
			canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Table canon_e633_cust_site_stg Truncated.',NULL,NULL,NULL,NULL);

      INSERT INTO CANON_E633_CONTRACTS_STG_TBL (
				id,
				contract_no,
				contract,
				contract_type,
				billing_type,
				pool_comp_free_co,
				config_nbr,
				start_date,
				end_date,
				boundary_1_busine,
				boundary_2_busine,
				boundary_3_busine,
				first_copy_price_1,
				first_copy_price_2,
				first_copy_price_3,
				first_copy_price_4,
				min_vol_amt_bus,
				min_vol_amt_ind,
				first_involved_bu,
				scnd_involved_bu,
				first_fixed_amount,
				scnd_fixed_amount,
				adv_arrear_ind_1,
				adv_arrear_ind_2,
				adv_arrear_ind_3,
				first_fixed_bill_fr,
				scnd_fixed_bill_fr,
				var_bill_frequenc,
				first_1,
				second_1,
				variable_1,
				ref_field_1,
				sevice_type,
				coverage,
				fixed_price_end_1,
				price_chg_code_1,
				fixed_price_prd_1,
				max_price_uplft_1,
				fixed_price_end_2,
				price_chg_code_2,
				fixed_price_prd_2,
				max_price_uplft_2,
				var_prc_end_dt_1,
				var_price_chg_cd1,
				var_prc_period_2,
				var_max_prc_perc1,
				var_prc_end_dt_2,
				var_price_chg_cd2,
				var_prc_period_2_2,
				var_max_prc_perc2,
				currency_code,
				component_seq,
				condition_set_version,
				purchase_option,
				address_number,
				expiration_date,
				source,
				free_to_use_1,
				free_to_use_2,
				free_to_use_3,
				free_to_use_4,
				free_to_use_5,
				free_to_use_6,
				meter_type,
				label,
				label_descrip,
				description,
				component_type,
				commercial_name_language_1,
				unique_key,
				lfs_sf_ib_id,
				lfs_sf_account_id,
				lfs_sf_contract_id,
				lfs_sf_status_flag,
				lfs_sf_status_message,
				lfs_last_update_date,
				pps_sf_ib_id,
				pps_sf_account_id,
				pps_sf_contract_id,
				pps_sf_status_flag,
				pps_sf_status_message,
				pps_last_update_date,
				lfsbu,
				dpsbu,
				ppsbu,
				bill_to_acct_name,
				bill_to_address
			) 
			SELECT id,
				contract_no,
				contract,
				contract_type,
				billing_type,
				pool_comp_free_co,
				config_nbr,
				start_date,
				end_date,
				boundary_1_busine,
				boundary_2_busine,
				boundary_3_busine,
				first_copy_price_1,
				first_copy_price_2,
				first_copy_price_3,
				first_copy_price_4,
				min_vol_amt_bus,
				min_vol_amt_ind,
				first_involved_bu,
				scnd_involved_bu,
				first_fixed_amount,
				scnd_fixed_amount,
				adv_arrear_ind_1,
				adv_arrear_ind_2,
				adv_arrear_ind_3,
				first_fixed_bill_fr,
				scnd_fixed_bill_fr,
				var_bill_frequenc,
				first_1,
				second_1,
				variable_1,
				ref_field_1,
				sevice_type,
				coverage,
				fixed_price_end_1,
				price_chg_code_1,
				fixed_price_prd_1,
				max_price_uplft_1,
				fixed_price_end_2,
				price_chg_code_2,
				fixed_price_prd_2,
				max_price_uplft_2,
				var_prc_end_dt_1,
				var_price_chg_cd1,
				var_prc_period_2,
				var_max_prc_perc1,
				var_prc_end_dt_2,
				var_price_chg_cd2,
				var_prc_period_2_2,
				var_max_prc_perc2,
				currency_code,
				component_seq,
				condition_set_version,
				purchase_option,
				address_number,
				expiration_date,
				source,
				free_to_use_1,
				free_to_use_2,
				free_to_use_3,
				free_to_use_4,
				free_to_use_5,
				free_to_use_6,
				meter_type,
				label,
				label_descrip,
				description,
				component_type,
				commercial_name_language_1,
				unique_key,
				lfs_sf_ib_id,
				lfs_sf_account_id,
				lfs_sf_contract_id,
				lfs_sf_status_flag,
				lfs_sf_status_message,
				lfs_last_update_date,
				pps_sf_ib_id,
				pps_sf_account_id,
				pps_sf_contract_id,
				pps_sf_status_flag,
				pps_sf_status_message,
				pps_last_update_date,
				lfsbu,
				dpsbu,
				ppsbu,
				bill_to_acct_name,
				bill_to_address
			FROM canon_e633_contracts_conv_tbl
			WHERE process_flag = 'P';
					
         dbms_output.put_line('Inserted '||SQL%rowcount||' into CANON_E633_CONTRACTS_STG_TBL from conversion table');
		 canon_e633_conv_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Inserted '||SQL%rowcount||' into CANON_E633_CONTRACTS_STG_TBL from conversion table',NULL,NULL,NULL,NULL);

        COMMIT;
		
	
EXCEPTION
  WHEN OTHERS THEN
		ROLLBACK;
		retcode := 2;
		dbms_output.put_line('Exception: ' || sqlerrm);
		canon_e633_conv_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||sqlerrm);
		
END load_contracts;

END CANON_E633_LFSPPS_CONV_PKG;
/