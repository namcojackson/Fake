CREATE OR REPLACE PACKAGE CANON_E618_S21_FLASH_PKG
AS
   /*************************************************************************************************
   Program Name        : CANON_E618_S21_FLASH_PKG.sql
   Author              : Lakshmi Majeti
   Functional Overview : S21 Customer, Locations and Departments Extract Program
   Comments            :
   Modification History:
   --------------------------------------------------------------------------------------------------
   Author              Version         Date                Comments
   --------------------------------------------------------------------------------------------------
   Lakshmi Majeti       1.0           11-Apr-2017    Populates Customer, Locations and Departments info into S21 EXTN from WMB.
   Balaji Gowravaram	2.0			  11-Dec-2017    Modified
   
     EMSD                      WMB                     S21_EXTN
    DB_CUST_CUSTOMERS    ===>   NMAI7500_01    ===>  CANON_E618_CUSTOMERS_TBL
    DB_CUST_LOCATIONS    ===>   NMAI7500_02    ===>  CANON_E618_LOCATIONS_TBL
    DB_CUST_DEPT_CODES   ===>   NMAI7500_03    ===>  CANON_E618_DEPT_CODES_TBL

   **************************************************************************************************/

   PROCEDURE main(p_debug_messages OUT VARCHAR2,
				  p_status_code OUT VARCHAR2);
   
END CANON_E618_S21_FLASH_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E618_S21_FLASH_PKG
AS
   PROCEDURE main(p_debug_messages OUT VARCHAR2,
				  p_status_code OUT VARCHAR2)
   IS
      l_err_msg          VARCHAR2 (1000);
      l_transaction_id   NUMBER;
      l_loc_cnt          NUMBER := 0;
      l_dept_cnt         NUMBER := 0;
   BEGIN
      SELECT MAX (transaction_id) 
	  INTO l_transaction_id 
	  FROM NMAI7500_01;

      SELECT COUNT (transaction_id)
        INTO l_loc_cnt
        FROM NMAI7500_02
       WHERE transaction_id = l_transaction_id;

      SELECT COUNT (transaction_id)
        INTO l_dept_cnt
        FROM NMAI7500_03
       WHERE transaction_id = l_transaction_id;

      IF l_loc_cnt > 0 AND l_dept_cnt > 0
      THEN
         DELETE FROM canon_e618_customers_tbl;

         DELETE FROM canon_e618_locations_tbl;

         DELETE FROM canon_e618_dept_codes_tbl;

         DELETE FROM e618_emsd_errors_tbl
               WHERE     program_name = 'CANON_E618_S21_FLASH_PKG'
                     AND process_name = 'MAIN'
                     AND error_date < SYSDATE - 30;

         /*
         EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_CUSTOMERS_TBL';
         EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_LOCATIONS_TBL';
         EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_E618_DEPT_CODES_TBL';

         INSERT INTO CANON_E618_CUSTOMERS_TBL
         SELECT * FROM DB_CUST_CUSTOMERS@EMSD_DB;
         INSERT INTO CANON_E618_LOCATIONS_TBL
         SELECT * FROM DB_CUST_LOCATIONS@EMSD_DB;
         INSERT INTO CANON_E618_DEPT_CODES_TBL
         SELECT * FROM DB_CUST_DEPT_CODES@EMSD_DB;
         */
         INSERT INTO canon_e618_customers_tbl (cust_account_id,
                                               customer_name,
                                               status_code,
                                               source,
                                               source_party_number,
                                               source_account_number,
                                               source_customer_name,
                                               source_customer_status,
                                               source_account_entered_date,
                                               source_account_end_date,
                                               source_account_lastupdate_date,
                                               account_start_date,
                                               account_end_date,
                                               account_classification_id,
                                               account_classification,
                                               account_ranking_id,
                                               account_ranking,
                                               rollup_id,
                                               rollup,
                                               discount_type_id,
                                               discount_type,
                                               industry_id,
                                               industry,
                                               hc_type_id,
                                               hc_type,
                                               tax_exemption_flag,
                                               duns_number,
                                               sic_code,
                                               bemfab_code,
                                               ultimate_duns,
                                               reporting_customer_name,
                                               alternate_customer,
                                               acronym_name,
                                               contract_cust_name,
                                               arcs_cost_center,
                                               arcs_customer_number,
                                               primary_account_flag,
                                               created_by,
                                               creation_date,
                                               last_updated_by,
                                               last_update_date)
            SELECT cust_acct_id,
                   cust_desc_txt,
                   cust_sts_cd,
                   cust_src_txt,
                   src_pty_num,
                   src_acct_num,
                   src_cust_nm,
                   src_cust_sts_cd,
                   CAST (TO_TIMESTAMP (src_acct_ent_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   CAST (TO_TIMESTAMP (src_acct_end_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   CAST (TO_TIMESTAMP (src_acct_last_upd_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   CAST (TO_TIMESTAMP (acct_start_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   CAST (TO_TIMESTAMP (acct_end_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   acct_cls_id,
                   acct_cls_txt,
                   acct_rnk_id,
                   acct_rnk_txt,
                   roll_up_id,
                   roll_up_txt,
                   disc_tp_id,
                   disc_tp_txt,
                   cust_indy_id,
                   cust_indy_txt,
                   hc_tp_id,
                   hc_tp_txt,
                   tax_exem_cd,
                   duns_num,
                   cust_sic_cd,
                   cust_bem_fab_cd,
                   ult_duns_txt,
                   rpt_cust_nm,
                   alt_cust_nm,
                   cust_acron_nm,
                   contr_cust_nm,
                   arcs_cc_nm,
                   arcs_cust_num,
                   prim_acct_cd,
                   '-1',
                   SYSDATE,
                   'TRANSACTION_ID: ' || l_transaction_id,
                   SYSDATE
              FROM NMAI7500_01
             WHERE transaction_id = l_transaction_id;

         INSERT INTO canon_e618_locations_tbl (cust_account_id,
                                               party_site_id,
                                               party_site_number,
                                               party_site_status_id,
                                               party_site_status,
                                               source_party_site_status,
                                               party_site_name,
                                               address1,
                                               address2,
                                               address3,
                                               address4,
                                               city,
                                               state,
                                               postal_code,
                                               timezone_code,
                                               duns_number,
                                               sic_code,
                                               bemfab_code,
                                               loc_creation_date,
                                               loc_end_date,
                                               site_directory_flag,
                                               headcount,
                                               hq_flag,
                                               tax_exemption_flag,
                                               bill_to_flag,
                                               ship_to_flag,
                                               identifying_address_flag,
                                               bill_primary_site_flag,
                                               ship_primary_site_flag,
                                               party_site_last_update,
                                               site_use_last_update,
                                               fm_shipto_location,
                                               location_status_change_date,
                                               created_by,
                                               creation_date,
                                               last_updated_by,
                                               last_update_date)
            SELECT cust_acct_id,
                   pty_site_id,
                   pty_site_num,
                   pty_site_sts_id,
                   pty_site_sts_txt,
                   src_pty_site_sts_txt,
                   pty_site_nm,
                   emsd_cust_first_line_addr,
                   emsd_cust_scd_line_addr,
                   emsd_cust_third_line_addr,
                   emsd_cust_forth_line_addr,
                   cust_cty_txt,
                   cust_st_txt,
                   loc_post_cd,
                   emsd_cust_tm_zn_cd,
                   duns_num,
                   loc_sic_cd,
                   loc_bem_fab_cd,
                   CAST (TO_TIMESTAMP (loc_crat_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   CAST (TO_TIMESTAMP (loc_end_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   site_dir_flg,
                   cust_head_cnt,
                   cust_hq_flg,
                   tax_exem_flg,
                   bill_to_flg,
                   ship_to_flg,
                   addr_prim_flg,
                   bill_prim_site_flg,
                   ship_prim_site_flg,
                   CAST (TO_TIMESTAMP (pty_site_last_upd_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   CAST (TO_TIMESTAMP (site_use_last_upd_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   fm_ship_to_loc_cd,
                   CAST (TO_TIMESTAMP (loc_sts_chng_dt, 'RRRRMMDDHH24MISSFF3') AS DATE),
                   '-1',
                   SYSDATE,
                   'TRANSACTION_ID: ' || l_transaction_id,
                   SYSDATE
              FROM NMAI7500_02
             WHERE transaction_id = l_transaction_id;

         INSERT INTO canon_e618_dept_codes_tbl (cust_account_id,
                                                source_account_number,
                                                dept_code)
            SELECT cust_acct_id, src_acct_num, cust_dept_cd
              FROM NMAI7500_03
             WHERE transaction_id = l_transaction_id;

         INSERT INTO e618_emsd_errors_tbl (program_name,
                                           process_name,
                                           key1,
                                           key2,
                                           key3,
                                           key4,
                                           key5,
                                           error_msg,
                                           error_date)
              VALUES ('CANON_E618_S21_FLASH_PKG',
                      'MAIN',
                      'TRANSACTION_ID: ' || l_transaction_id,
                      NULL,
                      NULL,
                      NULL,
                      NULL,
                      'SUCCESS',
                      SYSDATE);

         COMMIT;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
	     p_status_code:='E';
         l_err_msg := SUBSTR (SQLERRM, 1, 500);
         dbms_output.put_line ('Error Message ---> ' || l_err_msg);
		 p_debug_messages := l_err_msg;
         ROLLBACK;

         INSERT INTO e618_emsd_errors_tbl (program_name,
                                           process_name,
                                           key1,
                                           key2,
                                           key3,
                                           key4,
                                           key5,
                                           error_msg,
                                           error_date)
              VALUES ('CANON_E618_S21_FLASH_PKG',
                      'MAIN',
                      'TRANSACTION_ID: ' || l_transaction_id,
                      NULL,
                      NULL,
                      NULL,
                      NULL,
                      l_err_msg,
                      SYSDATE);

         COMMIT;
   END main;
END CANON_E618_S21_FLASH_PKG;
/
