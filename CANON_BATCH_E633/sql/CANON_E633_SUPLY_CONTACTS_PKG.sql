create or replace PACKAGE  CANON_E633_SUPLY_CONTACTS_PKG
AS
G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E633_SUPLY_CONTACTS_PKG';
PROCEDURE EXTRACT_LFS_SUPPLY_CONTACTS(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);


END CANON_E633_SUPLY_CONTACTS_PKG;
/ 
create or replace PACKAGE BODY CANON_E633_SUPLY_CONTACTS_PKG
AS

PROCEDURE EXTRACT_LFS_SUPPLY_CONTACTS(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
	l_procedure_name VARCHAR2(60) := 'EXTRACT_LFS_SUPPLY_CONTACTS';
    l_run_date  DATE    := SYSDATE;
    l_last_run_date DATE;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);

BEGIN
	retcode := '0';

	 BEGIN
           SELECT last_run_dt
             INTO l_last_run_date
             FROM canon_e633_incrdt_tracker_v
            WHERE extract_prg = 'LFS_SUPLY_CONTACTS';
           IF l_last_run_date IS NULL THEN
              l_last_run_date := TO_DATE('01-JUL-2019 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
           END IF;
           l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
      EXCEPTION WHEN OTHERS THEN 
        l_last_run_date := TO_DATE('01-JUL-2019 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
        END;

		--to reprocess the error records
		UPDATE canon_e633_lfs_suply_ctac_tbl stg
           SET sf_status_flag = CASE WHEN sf_suply_contact_id  IS NOT NULL THEN 'U' ELSE 'I' END
              ,sf_status_message = NULL
          WHERE sf_status_flag = 'E';
        DBMS_OUTPUT.PUT_LINE('updated status of error records to reprocess : ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated status of error records to reprocess: ' ||sql%rowcount,NULL,NULL,NULL,NULL);

		 UPDATE canon_e633_lfs_suply_ctac_tbl stg
            SET sf_status_flag = 'I'
            WHERE sf_status_flag IS NULL
              AND sf_suply_contact_id IS NULL
              AND sf_account_id IS NULL;
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' unprocessed records in canon_e633_lfs_suply_ctac_tbl' ,NULL,NULL,NULL,NULL );--QC54684
          COMMIT;

		MERGE INTO CANON_E633_LFS_SUPLY_CTAC_TBL stg
			USING (SELECT c.account_number
							,c.loc_num location_number
							,c.loc_nm location_name
--							,sqcp.ctac_psn_pk s21_contact_pk
							,sqcp.ctac_psn_first_nm first_name
							,sqcp.ctac_psn_last_nm last_name
							,ct.ctac_tp_nm role
							,sqcp.ctac_psn_eml_addr email
							,sqcp.ctac_psn_tel_num phone_number
							,sqcp.ctac_psn_extn_num phone_number_ext
--							,c.account_number || c.loc_num || sqcp.ctac_psn_pk || sqcp.ctac_psn_eml_addr unique_id
                            ,c.account_number || c.loc_num || sqcp.ctac_psn_last_nm || sqcp.ctac_psn_eml_addr unique_id
                            ,ezuptime_sqcp
					 FROM (select distinct a.ship_to_cust_cd
--                                                    ,b.ctac_psn_pk
                                                    ,a.ds_ord_catg_cd
                                                    ,a.ds_ord_tp_cd
                                                    ,b.ctac_psn_first_nm
                                                    ,b.ctac_psn_last_nm
                                                    ,b.ctac_psn_tp_cd
                                                    ,b.ctac_psn_eml_addr
                                                    ,b.ctac_psn_tel_num
                                                    ,b.ctac_psn_extn_num
                                                    ,FIRST_VALUE(b.ezuptime) OVER(PARTITION BY ship_to_cust_cd,b.ctac_psn_eml_addr ORDER BY b.ezuptime DESC) ezuptime_sqcp
                                        from sply_quote a
                                          ,sply_quote_ctac_psn b
                                        where a.sply_quote_num = b.sply_quote_num 
                                        and a.ezcancelflag = '0'
                                          AND a.glbl_cmpy_cd = 'ADB'
--                                          AND a.ship_to_cust_cd = 2000030
                                        order by a.ship_to_cust_cd
--                                                ,b.ctac_psn_pk
                                                ,b.ctac_psn_first_nm
                                                , b.ctac_psn_last_nm
                                                ,b.ctac_psn_eml_addr)sqcp
						 ,ctac_tp ct
						 ,(SELECT ship_to_cust_cd cust_cd
								  ,loc_nm
								  ,loc_num
								  ,ds_acct_nm
								  ,sell_to_cust_cd account_number
							FROM ship_to_cust
						   WHERE 1 = 1
							 AND ezcancelflag = '0'
							 AND glbl_cmpy_cd = 'ADB'
							 AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(eff_THRU_DT, 'yyyymmdd'),SYSDATE))) c
						 ,s21_csa_apps.ds_ord_tp dot
						 ,s21_csa_apps.ds_ord_catg doc
					WHERE 1 = 1
					  AND ct.ezcancelflag = '0'
					  AND ct.glbl_cmpy_cd = 'ADB'
					  AND dot.glbl_cmpy_cd = 'ADB'
					  AND dot.ezcancelflag = '0'
					  AND doc.glbl_cmpy_cd = 'ADB'
					  AND doc.ezcancelflag = '0'
--                      AND c.loc_num = '3011091'
--                      AND c.loc_num = '3016702'
					  AND sqcp.ctac_psn_tp_cd = ct.ctac_tp_cd
					  AND ct.ctac_tp_nm = 'ORDER CONTACT'
					  AND sqcp.ds_ord_catg_cd = doc.ds_ord_catg_cd
					  AND doc.ds_ord_catg_nm in (SELECT Distinct ORDER_CATEGORY
                                                         FROM s21_csa_extn.CSA_SFDC_LFS_SUPPLY_CONTACTS_V)
					  AND sqcp.ds_ord_tp_cd = dot.ds_ord_tp_cd
					  AND dot.ds_ord_tp_nm in (SELECT DISTINCT ORDER_REASON_CODE
                                                    FROM s21_csa_extn.CSA_SFDC_LFS_SUPPLY_CONTACTS_V)
					  AND c.cust_cd = sqcp.ship_to_cust_cd
					  AND CAST(to_timestamp (ezuptime_sqcp, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date 
					)s21
			   ON (stg.unique_id = s21.unique_id)
			 WHEN MATCHED THEN UPDATE
			    SET stg.role = s21.role
				  ,stg.first_name = s21.first_name
				  ,last_name = s21.last_name
				  ,phone_number = s21.phone_number
				  ,phone_number_ext = s21.phone_number_ext
				  ,email= s21.email
				  ,s21_load_date = sysdate
				  ,sf_status_flag = 'U'
				  ,sf_status_message = 'ready for update'
			 WHEN NOT MATCHED THEN 
				INSERT(location_number
						  ,location_name
						  ,account_number
--						  ,s21_contact_pk
						  ,role
						  ,first_name
						  ,last_name
						  ,phone_number
						  ,phone_number_ext
						  ,email
						  ,unique_id
						  ,s21_load_date
						  ,sf_suply_contact_id	
						  ,sf_status_flag
						  ,sf_status_message
						  ,sf_account_id
						  ,sf_last_update_date) VALUES (s21.location_number
													  ,s21.location_name
													  ,s21.account_number
--													  ,s21.s21_contact_pk
													  ,s21.role
													  ,s21.first_name
													  ,s21.last_name
													  ,s21.phone_number
													  ,s21.phone_number_ext
													  ,s21.email
													  ,s21.unique_id
													  ,sysdate
													  ,null	
													  ,'I'
													  ,'ready for insert'
													  ,null
													  ,null);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records merged into canon_e633_lfs_suply_ctac_tbl : ' ||sql%rowcount,NULL,NULL,NULL,NULL);													  
	COMMIT;

	--update the accountIDs by the location NUMBER
	MERGE INTO canon_e633_lfs_suply_ctac_tbl stg
	  USING canon_e633_cust_site_stg_tbl cust
	 ON ( cust.location_number = stg.location_number
	      AND cust.account_number = stg.account_number
		  AND cust.lfsbu IS NOT NULL
		  AND stg.sf_status_flag IN ('I','U')
		 )
	 WHEN MATCHED THEN UPDATE
		SET stg.sf_account_id = cust.lfs_sf_account_id,
             sf_status_message = 'Processed',
             sf_last_update_date = SYSDATE;
        
        
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records merged for sf account ID: ' ||sql%rowcount,NULL,NULL,NULL,NULL);													  
	COMMIT;		

	-- mark the record as error in case if the location's salesforce ID is not found.
	-- Contacts are tied to Account and without account we cannot load the contacts
	UPDATE canon_e633_lfs_suply_ctac_tbl
	  SET sf_status_flag = 'E'
	  WHERE sf_status_flag IN ('I','U')
	   AND sf_account_id IS NULL;

	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records marked error for no salesforce account ID: ' ||sql%rowcount,NULL,NULL,NULL,NULL);													  
	COMMIT;	

	BEGIN	
	 UPDATE canon_s21_cd_val_tbl v
		   SET v.val76 = l_program_start_date
		 WHERE v.val1 = 'LFS_SUPLY_CONTACTS'
		   AND v.cd_id = (SELECT cd_id
						   FROM CANON_S21_CD_TBL
						   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
		CANON_E633_SF_ERROR_LOG_PKG.LOG_ERROR(G_PACKAGE_NAME,L_PROCEDURE_NAME,'INFO','Updated S21 profiles code table',NULL,NULL,NULL,NULL);
		COMMIT;
	EXCEPTION WHEN OTHERS THEN
		   dbms_output.put_line('in update S21 profiles code table exception '|| SQLERRM);
		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR','update s21 profile code table',NULL,NULL,NULL,SQLERRM);
	END;	

	EXCEPTION WHEN OTHERS THEN
        retcode := 2;
        errbuff := SQLERRM;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
	END EXTRACT_LFS_SUPPLY_CONTACTS;	
    END CANON_E633_SUPLY_CONTACTS_PKG;
	/