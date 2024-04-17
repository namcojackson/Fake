CREATE OR REPLACE PACKAGE CANON_E618_ASSETS_STG_PKG
AS
   /*************************************************************************************************
      Program Name        : CANON_E618_ASSETS_STG_PKG.sql
      Author              : Lakshmi Majeti
      Functional Overview : S21 EMSD Asset Extract Program
      Comments            :
      Modification History:
      --------------------------------------------------------------------------------------------------
      Author              Version         Date                Comments
      --------------------------------------------------------------------------------------------------
      Lakshmi Majeti       1.0           06-Mar-2017    S21 EMSD Asset Extract Program
              S21 Changes on Revision 3.4 of the PVCS File.
   **************************************************************************************************/

   PROCEDURE assets_extract (p_run_date_from    IN     DATE,
                             p_run_date_to      IN     DATE,
                             o_return_status       OUT VARCHAR2,
                             o_return_message      OUT VARCHAR2);

   PROCEDURE run_updates;

   PROCEDURE main (p_errbuf             OUT VARCHAR2,
                   p_retcode            OUT VARCHAR2,
                   p_run_date_from   IN     DATE DEFAULT '01-JAN-1900',
                   p_run_date_to     IN     DATE DEFAULT '31-DEC-2199');

   PROCEDURE log_error (p_process_name   IN VARCHAR2,
                        p_in_key1        IN VARCHAR2,
                        p_in_key2        IN VARCHAR2,
                        p_in_key3        IN VARCHAR2,
                        p_in_key4        IN VARCHAR2,
                        p_in_key5        IN VARCHAR2,
                        p_error_msg      IN VARCHAR2);
END CANON_E618_ASSETS_STG_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E618_ASSETS_STG_PKG
AS
   -- Declare common Variable
   c_package_name   CONSTANT VARCHAR2 (240) := 'CANON_E618_ASSETS_STG_PKG';
   v_error_msg               VARCHAR2 (4000) := 'No Error';

   PROCEDURE assets_extract (p_run_date_from    IN     DATE,
                             p_run_date_to      IN     DATE,
                             o_return_status       OUT VARCHAR2,
                             o_return_message      OUT VARCHAR2)
   IS
      c_procedure_name   CONSTANT VARCHAR2 (240) := 'ASSETS_EXTRACT';
      v_return_status             VARCHAR2 (240) := 'S';
      v_return_message            VARCHAR2 (240) := 'No Error';

   BEGIN
      DBMS_OUTPUT.PUT_LINE ('Current system time is ' || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
      DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');

      DBMS_OUTPUT.PUT_LINE ('Truncate Tables');

      EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e618_asset_extract_tbl';

      DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');
      DBMS_OUTPUT.PUT_LINE ('Current system time is ' || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
      DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');

      --====================================================================================================
      -- SO and RMA Headers staging
      BEGIN
         DBMS_OUTPUT.PUT_LINE ('Inserting into CANON_E618_ASSET_EXTRACT_TBL');

         INSERT INTO canon_e618_asset_extract_tbl (instance_id,
                                                   serial_number,
                                                   install_date,
                                                   last_oe_order_number,
                                                   last_oe_order_line_id,
                                                   last_oe_rma_line_id,
                                                   last_oe_order_type, --added for s21 change
                                                   last_rma_order_type,
                                                   inventory_item_id,
                                                   inv_master_organization_id,
                                                   location_id,
                                                   install_location_id,
                                                   install_status,
                                                   inv_organization_name,
                                                   organization_code,
                                                   sub_inventory,
                                                   location_type_code,
                                                   owner_party_id,
                                                   owner_party_name,
                                                   creation_date,
                                                   created_by,
                                                   last_update_date,
                                                   last_updated_by)
            SELECT smm.svc_mach_mstr_pk instance_id,
                   smm.ser_num serial_number,
                   CAST (TO_TIMESTAMP (smm.istl_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) install_date,
                   smm.cpo_ord_num last_oe_order_number,
                   (SELECT    cpo_ord_num
                           || ds_cpo_config_pk
                           || cpo_dtl_line_num
                           || cpo_dtl_line_sub_num
                      FROM cpo_dtl_v dcd
                     WHERE 1 = 1
					   AND dcd.cpo_ord_num = smm.cpo_ord_num
					   AND dcd.cpo_dtl_line_num = smm.cpo_dtl_line_num
					   AND SUBSTR (dcd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
					   AND dcd.glbl_cmpy_cd = smm.glbl_cmpy_cd
					   AND dcd.ezcancelflag = smm.ezcancelflag) last_oe_order_line_id,
                   (SELECT    cpo_ord_num
                           || ds_cpo_config_pk
                           || ds_cpo_rtrn_line_num
                           || ds_cpo_rtrn_line_sub_num
                      FROM ds_cpo_rtrn_dtl_v dcrd
                     WHERE     1 = 1
                           AND dcrd.cpo_ord_num = smm.cpo_ord_num
                           AND dcrd.ds_cpo_rtrn_line_num = smm.rma_line_num
                           AND SUBSTR (dcrd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND dcrd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcrd.ezcancelflag = smm.ezcancelflag) last_oe_rma_line_id,
                   (SELECT doc.ds_ord_catg_desc_txt
                      FROM cpo dc, ds_ord_catg doc
                     WHERE     1 = 1
                           AND dc.cpo_ord_num = smm.cpo_ord_num
                           AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                           AND dc.ezcancelflag = doc.ezcancelflag
                           AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                           AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dc.ezcancelflag = smm.ezcancelflag)  last_oe_order_type,
                   (SELECT doc.ds_ord_catg_desc_txt
                      FROM cpo dc, ds_ord_catg doc
                     WHERE     1 = 1
                           AND dc.cpo_ord_num = smm.rma_num
                           AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                           AND dc.ezcancelflag = doc.ezcancelflag
                           AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                           AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dc.ezcancelflag = smm.ezcancelflag) last_rma_order_type,
                   smm.mdse_cd inventory_item_id,
                   NULL inv_master_organization_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --smm.bill_to_loc_num
                           AND ROWNUM = 1) location_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --SMM.BILL_TO_LOC_NUM
                           AND ROWNUM = 1) install_location_id,
                   'INSTALLED' install_status,
                   NULL inv_organization_name,
                   NULL organization_code,
                   NULL sub_inventory,
                   UPPER (smus.svc_mach_usg_sts_nm) location_type_code,
                   smm.ownr_acct_num owner_party_id,
                   (SELECT stc.ds_acct_nm
                      FROM sell_to_cust stc
                     WHERE     1 = 1
                           AND stc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND stc.ezcancelflag = smm.ezcancelflag
                           AND stc.sell_to_cust_cd = smm.ownr_acct_num
                           AND ROWNUM = 1)
                      OWNER_PARTY_NAME,
                   SYSDATE,
                   '-1',
                   SYSDATE,
                   '-1'
              FROM svc_mach_mstr smm, svc_mach_mstr_sts smms, svc_mach_usg_sts smus
             WHERE     1 = 1
                   AND smm.glbl_cmpy_cd = smms.glbl_cmpy_cd
                   AND smm.ezcancelflag = smms.ezcancelflag
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER (smms.svc_mach_mstr_sts_desc_txt) IN ('WAITING FOR INSTALLATION', 'INSTALLED')            --'CREATED'
                   AND smm.glbl_cmpy_cd = smus.glbl_cmpy_cd
                   AND smm.ezcancelflag = smus.ezcancelflag
                   AND smm.svc_mach_usg_sts_cd = smus.svc_mach_usg_sts_cd
                   --AND SMUS.SVC_MACH_USG_STS_CD IN (10, 20, 30) --  10  In Inventory   20   In Transit   30  At Customer     40  Returned
                   AND UPPER (smus.svc_mach_usg_sts_nm) IN ('IN TRANSIT', 'AT CUSTOMER') --'IN INVENTORY'
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = '0'
                   AND EXISTS
                          (SELECT ship_to_cust_cd
                             FROM ship_to_cust stc,
                                  pty_loc_wrk plw,
                                  canon_e618_locations_tbl db_loc
                            WHERE     stc.ship_to_cust_cd = smm.ship_to_cust_cd
                                  AND stc.loc_num = plw.loc_num
                                  AND plw.pty_loc_pk = db_loc.party_site_id
                                  AND stc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                  AND stc.ezcancelflag = smm.ezcancelflag
                                  AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                  AND plw.ezcancelflag = smm.ezcancelflag)
                   AND EXISTS
                          (SELECT coa_mdse_tp_cd
                             FROM mdse dmi
                            WHERE     1 = 1
                                  AND SUBSTR (dmi.mdse_cd, 1, 8) =  SUBSTR (smm.mdse_cd, 1, 8)
                                  AND dmi.coa_mdse_tp_cd = '10'                 -- Machine
                                  AND dmi.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                  AND dmi.ezcancelflag = smm.ezcancelflag)      
            UNION
            --- Get Machines that are in INVENTORY
            SELECT smm.svc_mach_mstr_pk instance_id,
                   smm.ser_num serial_number,
                   CAST (TO_TIMESTAMP (smm.istl_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) install_date,
                   smm.cpo_ord_num last_oe_order_number,
                   (SELECT    cpo_ord_num
                           || ds_cpo_config_pk
                           || cpo_dtl_line_num
                           || cpo_dtl_line_sub_num
                      FROM cpo_dtl_v dcd
                     WHERE     1 = 1
                           AND dcd.cpo_ord_num = smm.cpo_ord_num
                           AND dcd.cpo_dtl_line_num = smm.cpo_dtl_line_num
                           AND SUBSTR (dcd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND dcd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcd.ezcancelflag = smm.ezcancelflag) last_oe_order_line_id,
                   (SELECT    cpo_ord_num
                           || ds_cpo_config_pk
                           || ds_cpo_rtrn_line_num
                           || ds_cpo_rtrn_line_sub_num
                      FROM ds_cpo_rtrn_dtl_v dcrd
                     WHERE     1 = 1
                           AND dcrd.cpo_ord_num = smm.cpo_ord_num
                           AND dcrd.ds_cpo_rtrn_line_num = smm.rma_line_num
                           AND SUBSTR (dcrd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND dcrd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcrd.ezcancelflag = smm.ezcancelflag) last_oe_rma_line_id,
                   (SELECT doc.ds_ord_catg_desc_txt
                      FROM cpo dc, ds_ord_catg doc
                     WHERE     1 = 1
                           AND dc.cpo_ord_num = smm.cpo_ord_num
                           AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                           AND dc.ezcancelflag = doc.ezcancelflag
                           AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                           AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dc.ezcancelflag = smm.ezcancelflag) last_oe_order_type,
                   (SELECT doc.ds_ord_catg_desc_txt
                      FROM cpo dc, ds_ord_catg doc
                     WHERE     1 = 1
                           AND dc.cpo_ord_num = smm.rma_num
                           AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                           AND dc.ezcancelflag = doc.ezcancelflag
                           AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                           AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dc.ezcancelflag = smm.ezcancelflag) last_rma_order_type,
                   smm.mdse_cd inventory_item_id,
                   NULL inv_master_organization_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --smm.bill_to_loc_num
                           AND ROWNUM = 1) location_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --smm.bill_to_loc_num
                           AND ROWNUM = 1) install_location_id,
                   'CANON RETURNED' install_status,
                   (SELECT rw.rtl_wh_nm
                      FROM ds_cpo_rtrn_dtl_v dcrd, rtl_wh rw
                     WHERE     dcrd.glbl_cmpy_cd = rw.glbl_cmpy_cd
                           AND dcrd.ezcancelflag = rw.ezcancelflag
                           AND dcrd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcrd.ezcancelflag = smm.ezcancelflag
                           AND dcrd.cpo_ord_num = smm.cpo_ord_num
                           AND dcrd.rtl_wh_cd = rw.rtl_wh_cd
                           AND dcrd.ds_cpo_rtrn_line_num = smm.rma_line_num
                           AND SUBSTR (dcrd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND ROWNUM = 1) inv_organization_name,
                   (SELECT rw.rtl_wh_cd
                      FROM ds_cpo_rtrn_dtl_v dcrd, rtl_wh rw
                     WHERE     dcrd.glbl_cmpy_cd = rw.glbl_cmpy_cd
                           AND dcrd.ezcancelflag = rw.ezcancelflag
                           AND dcrd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcrd.ezcancelflag = smm.ezcancelflag
                           AND dcrd.cpo_ord_num = smm.cpo_ord_num
                           AND dcrd.rtl_wh_cd = rw.rtl_wh_cd
                           AND dcrd.ds_cpo_rtrn_line_num = smm.rma_line_num
                           AND SUBSTR (dcrd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND ROWNUM = 1) organization_code,
                   NULL sub_inventory,
                   UPPER (smus.svc_mach_usg_sts_nm) location_type_code,
                   smm.ownr_acct_num owner_party_id,
                   (SELECT stc.ds_acct_nm
                      FROM sell_to_cust stc
                     WHERE     1 = 1
                           AND stc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND stc.ezcancelflag = smm.ezcancelflag
                           AND stc.sell_to_cust_cd = smm.ownr_acct_num
                           AND ROWNUM = 1) owner_party_name,
                   SYSDATE,
                   '-1',
                   SYSDATE,
                   '-1'
              FROM svc_mach_mstr smm, svc_mach_mstr_sts smms, svc_mach_usg_sts smus
             WHERE     1 = 1
                   AND smm.glbl_cmpy_cd = smms.glbl_cmpy_cd
                   AND smm.ezcancelflag = smms.ezcancelflag
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER (smms.svc_mach_mstr_sts_desc_txt) IN  ('WAITING FOR REMOVAL', 'REMOVED') --S21  IB status for 'CANON RETURNED'
                   AND smm.glbl_cmpy_cd = smus.glbl_cmpy_cd
                   AND smm.ezcancelflag = smus.ezcancelflag
                   AND smm.svc_mach_usg_sts_cd = smus.svc_mach_usg_sts_cd
                   --AND SMUS.SVC_MACH_USG_STS_CD = 10 --  10  In Inventory   20   In Transit   30  At Customer     40  Returned
                   AND UPPER (smus.svc_mach_usg_sts_nm) IN ('IN INVENTORY', 'IN TRANSIT', 'RETURNED')
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = '0'
                   AND EXISTS
                          (SELECT coa_mdse_tp_cd
                             FROM mdse dmi
                            WHERE     1 = 1
                                  AND SUBSTR (dmi.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                                  AND dmi.coa_mdse_tp_cd = '10'                 -- Machine
                                  AND dmi.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                  AND dmi.ezcancelflag = smm.ezcancelflag)
                   AND EXISTS -- If we do not have S21 table equalant to material transactions just check with S21 SO tables
                          (SELECT dc.cpo_ord_num
                             FROM cpo dc, cpo_dtl_v dcd, rtl_swh rs
                            WHERE     dc.cpo_ord_num = dcd.cpo_ord_num
                                  AND dc.cpo_ord_num = smm.rma_num
                                  AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                  AND dc.ezcancelflag = smm.ezcancelflag
                                  AND dc.glbl_cmpy_cd = dcd.glbl_cmpy_cd
                                  AND dc.ezcancelflag = dcd.ezcancelflag
                                  AND SUBSTR (dcd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                                  AND dc.glbl_cmpy_cd = rs.glbl_cmpy_cd
                                  AND dc.ezcancelflag = rs.ezcancelflag
                                  AND dcd.rtl_swh_cd = rs.rtl_swh_cd
                                  AND UPPER (rs.rtl_swh_desc_txt) IN
                                         (SELECT UPPER (sub_warehouse_name)
                                            FROM canon_e618_emsd_sub_inv_v))     
            UNION
            --- Get Machines that are Terminated / Expired and Sold off
            SELECT smm.svc_mach_mstr_pk instance_id,
                   smm.ser_num serial_number,
                   CAST (TO_TIMESTAMP (smm.istl_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) install_date,
                   smm.cpo_ord_num last_oe_order_number,
                   (SELECT    cpo_ord_num
                           || ds_cpo_config_pk
                           || cpo_dtl_line_num
                           || cpo_dtl_line_sub_num
                      FROM cpo_dtl_v dcd
                     WHERE     1 = 1
                           AND dcd.cpo_ord_num = smm.cpo_ord_num
                           AND dcd.cpo_dtl_line_num = smm.cpo_dtl_line_num
                           AND SUBSTR (dcd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND dcd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcd.ezcancelflag = smm.ezcancelflag) last_oe_order_line_id,
                   (SELECT    cpo_ord_num
                           || ds_cpo_config_pk
                           || ds_cpo_rtrn_line_num
                           || ds_cpo_rtrn_line_sub_num
                      FROM ds_cpo_rtrn_dtl_v dcrd
                     WHERE     1 = 1
                           AND dcrd.cpo_ord_num = smm.cpo_ord_num
                           AND dcrd.ds_cpo_rtrn_line_num = smm.rma_line_num
                           AND SUBSTR (dcrd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND dcrd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcrd.ezcancelflag = smm.ezcancelflag) last_oe_rma_line_id,
                   (SELECT doc.ds_ord_catg_desc_txt
                      FROM cpo dc, ds_ord_catg doc
                     WHERE     1 = 1
                           AND dc.cpo_ord_num = smm.cpo_ord_num
                           AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                           AND dc.ezcancelflag = doc.ezcancelflag
                           AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                           AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dc.ezcancelflag = smm.ezcancelflag) last_oe_order_type,
                   (SELECT doc.ds_ord_catg_desc_txt
                      FROM cpo dc, ds_ord_catg doc
                     WHERE     1 = 1
                           AND dc.cpo_ord_num = smm.rma_num
                           AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                           AND dc.ezcancelflag = doc.ezcancelflag
                           AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                           AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dc.ezcancelflag = smm.ezcancelflag) last_rma_order_type,
                   smm.mdse_cd inventory_item_id,
                   NULL inv_master_organization_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --smm.bill_to_loc_num
                           AND ROWNUM = 1) location_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --SMM.BILL_TO_LOC_NUM
                           AND ROWNUM = 1) install_location_id,
                   'RETIRED' install_status,
                   NULL inv_organization_name,
                   NULL organization_code,
                   NULL sub_inventory,
                   UPPER (smus.svc_mach_usg_sts_nm) location_type_code,
                   smm.ownr_acct_num owner_party_id,
                   (SELECT stc.ds_acct_nm
                      FROM sell_to_cust stc
                     WHERE     1 = 1
                           AND stc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND stc.ezcancelflag = smm.ezcancelflag
                           AND stc.sell_to_cust_cd = smm.ownr_acct_num
                           AND ROWNUM = 1) owner_party_name,
                   SYSDATE,
                   '-1',
                   SYSDATE,
                   '-1'
              FROM svc_mach_mstr smm, svc_mach_mstr_sts smms, svc_mach_usg_sts smus
             WHERE     1 = 1
                   AND smm.glbl_cmpy_cd = smms.glbl_cmpy_cd
                   AND smm.ezcancelflag = smms.ezcancelflag
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER (smms.svc_mach_mstr_sts_desc_txt) = 'TERMINATED' -- S21 does not have IB status  'EXPIRED'
                   AND smm.glbl_cmpy_cd = smus.glbl_cmpy_cd
                   AND smm.ezcancelflag = smus.ezcancelflag
                   AND smm.svc_mach_usg_sts_cd = smus.svc_mach_usg_sts_cd
                   --AND SMUS.SVC_MACH_USG_STS_CD IN (10, 20, 30) --  10  In Inventory   20   In Transit   30  At Customer     40  Returned
                   AND UPPER (smus.svc_mach_usg_sts_nm) IN ('IN INVENTORY', 'RETURNED')
                   --IN ('IN INVENTORY', 'IN TRANSIT', 'RETURNED')
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = '0'
                   AND EXISTS
                          (SELECT coa_mdse_tp_cd
                             FROM mdse dmi
                            WHERE     1 = 1
                                  AND SUBSTR (dmi.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                                  AND dmi.coa_mdse_tp_cd = '10'                 -- Machine
                                  AND dmi.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                  AND dmi.ezcancelflag = smm.ezcancelflag)
                   AND (   EXISTS
                              (SELECT ship_to_cust_cd
                                 FROM ship_to_cust stc,
                                      pty_loc_wrk plw,
                                      canon_e618_locations_tbl db_loc
                                WHERE     stc.ship_to_cust_cd = smm.ship_to_cust_cd
                                      AND stc.loc_num = plw.loc_num
                                      AND plw.pty_loc_pk = db_loc.party_site_id
                                      AND stc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                      AND stc.ezcancelflag = smm.ezcancelflag
                                      AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                      AND plw.ezcancelflag = smm.ezcancelflag)
                        OR EXISTS
                              (SELECT dcdv.cpo_ord_num
                                 FROM cpo_dtl_v dcdv,
                                      canon_e618_locations_tbl db_loc,
                                      ship_to_cust stc,
                                      pty_loc_wrk plw
                                WHERE     dcdv.cpo_ord_num = smm.rma_num
                                      AND dcdv.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                      AND dcdv.ezcancelflag = smm.ezcancelflag
                                      AND stc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                      AND stc.ezcancelflag = smm.ezcancelflag
                                      AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                      AND plw.ezcancelflag = smm.ezcancelflag
                                      AND dcdv.mdse_cd = smm.mdse_cd
                                      AND dcdv.ship_to_cust_cd = stc.ship_to_cust_cd
                                      AND stc.loc_num = plw.loc_num
                                      AND plw.pty_loc_pk = db_loc.party_site_id))       
            UNION
            SELECT DISTINCT
                   smm.svc_mach_mstr_pk instance_id,
                   smm.ser_num serial_number,
                   CAST (TO_TIMESTAMP (smm.istl_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) install_date,
                   smm.cpo_ord_num last_oe_order_number,
                   (SELECT    cpo_ord_num
                           || ds_cpo_config_pk
                           || cpo_dtl_line_num
                           || cpo_dtl_line_sub_num
                      FROM cpo_dtl_v dcd
                     WHERE     1 = 1
                           AND dcd.cpo_ord_num = smm.cpo_ord_num
                           AND dcd.cpo_dtl_line_num = smm.cpo_dtl_line_num
                           AND SUBSTR (dcd.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND dcd.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcd.ezcancelflag = smm.ezcancelflag) last_oe_order_line_id,
                   NULL last_oe_rma_line_id,
                   (SELECT doc.ds_ord_catg_desc_txt
                      FROM cpo dc, ds_ord_catg doc
                     WHERE     1 = 1
                           AND dc.cpo_ord_num = smm.cpo_ord_num
                           AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                           AND dc.ezcancelflag = doc.ezcancelflag
                           AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                           AND dc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dc.ezcancelflag = smm.ezcancelflag)  last_oe_order_type,
                   NULL last_rma_order_type,
                   smm.mdse_cd inventory_item_id,
                   NULL inv_master_organization_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --smm.bill_to_loc_num
                           AND ROWNUM = 1) location_id,
                   (SELECT pty_loc_pk
                      FROM pty_loc_wrk plw
                     WHERE     1 = 1
                           AND plw.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND plw.ezcancelflag = smm.ezcancelflag
                           AND plw.loc_num = smm.cur_loc_num         --smm.bill_to_loc_num
                           AND ROWNUM = 1) install_location_id,
                   'PRE-FLIGHT' install_status,
                   (SELECT rw.rtl_wh_nm
                      FROM cpo_dtl_v dcdv, rtl_wh rw
                     WHERE     dcdv.glbl_cmpy_cd = rw.glbl_cmpy_cd
                           AND dcdv.ezcancelflag = rw.ezcancelflag
                           AND dcdv.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcdv.ezcancelflag = smm.ezcancelflag
                           AND dcdv.cpo_ord_num = smm.cpo_ord_num
                           AND dcdv.rtl_wh_cd = rw.rtl_wh_cd
                           AND dcdv.cpo_dtl_line_num = smm.cpo_dtl_line_num
                           AND SUBSTR (dcdv.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND ROWNUM = 1) inv_organization_name,
                   (SELECT rw.rtl_wh_cd
                      FROM cpo_dtl_v dcdv, rtl_wh rw
                     WHERE     dcdv.glbl_cmpy_cd = rw.glbl_cmpy_cd
                           AND dcdv.ezcancelflag = rw.ezcancelflag
                           AND dcdv.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND dcdv.ezcancelflag = smm.ezcancelflag
                           AND dcdv.cpo_ord_num = smm.cpo_ord_num
                           AND dcdv.rtl_wh_cd = rw.rtl_wh_cd
                           AND dcdv.cpo_dtl_line_num = smm.cpo_dtl_line_num
                           AND SUBSTR (dcdv.mdse_cd, 1, 8) = SUBSTR (smm.mdse_cd, 1, 8)
                           AND ROWNUM = 1) organization_code,
                   NULL sub_inventory,
                   UPPER (smus.svc_mach_usg_sts_nm) location_type_code,
                   smm.ownr_acct_num owner_party_id,
                   (SELECT stc.ds_acct_nm
                      FROM sell_to_cust stc
                     WHERE     1 = 1
                           AND stc.glbl_cmpy_cd = smm.glbl_cmpy_cd
                           AND stc.ezcancelflag = smm.ezcancelflag
                           AND stc.sell_to_cust_cd = smm.ownr_acct_num
                           AND ROWNUM = 1) owner_party_name,
                   SYSDATE,
                   '-1',
                   SYSDATE,
                   '-1'
              FROM svc_mach_mstr smm,
                   svc_mach_mstr_sts smms,
                   svc_mach_usg_sts smus,
                   shpg_pln sp,
                   invty_trx it
             WHERE     1 = 1
                   AND smm.glbl_cmpy_cd = smms.glbl_cmpy_cd
                   AND smm.ezcancelflag = smms.ezcancelflag
                   AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
                   AND UPPER (smms.svc_mach_mstr_sts_desc_txt) = 'CREATED'
                   AND smm.glbl_cmpy_cd = smus.glbl_cmpy_cd
                   AND smm.ezcancelflag = smus.ezcancelflag
                   AND smm.svc_mach_usg_sts_cd = smus.svc_mach_usg_sts_cd
                   AND UPPER (Smus.svc_mach_usg_sts_nm) = 'IN INVENTORY' --AND SMUS.SVC_MACH_USG_STS_CD IN (10, 20, 30) --  10  In Inventory   20   In Transit   30  At Customer     40  Returned
                   AND smm.glbl_cmpy_cd = sp.glbl_cmpy_cd
                   AND smm.ezcancelflag = sp.ezcancelflag
                   AND smm.cpo_ord_num = sp.trx_hdr_num
                   AND smm.cpo_dtl_line_num = sp.trx_line_num
                   AND sp.shpg_sts_cd = 36  -- 32-picked  34-packed  36-staged  38-Shipped
                   AND smm.glbl_cmpy_cd = it.glbl_cmpy_cd
                   AND smm.ezcancelflag = it.ezcancelflag
                   AND smm.cpo_ord_num = it.cpo_ord_num
                   AND smm.cpo_dtl_line_num = it.cpo_dtl_line_num
                   AND EXISTS
                          (SELECT db_loc.party_site_number
                             FROM canon_e618_locations_tbl db_loc
                            WHERE db_loc.party_site_number = smm.ship_to_cust_cd)
                   AND EXISTS
                          (SELECT coa_mdse_tp_cd
                             FROM mdse dmi
                            WHERE     1 = 1
                                  AND SUBSTR (dmi.mdse_cd, 1, 8) =  SUBSTR (smm.mdse_cd, 1, 8)
                                  AND dmi.coa_mdse_tp_cd = '10'                 -- Machine
                                  AND dmi.glbl_cmpy_cd = smm.glbl_cmpy_cd
                                  AND dmi.ezcancelflag = smm.ezcancelflag)
                   AND smm.glbl_cmpy_cd = 'ADB'
                   AND smm.ezcancelflag = '0';


         DBMS_OUTPUT.PUT_LINE ('No. of rows Inserted: ' || SQL%ROWCOUNT);
         DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');
		 
		 EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_E618_ASSET_EXTRACT_TBL COMPUTE STATISTICS';
		 
      EXCEPTION
         WHEN OTHERS
         THEN
            v_error_msg :=
                  c_package_name
               || '.'
               || c_procedure_name
               || ' terminated during Headers staging insert due to '
               || SQLCODE
               || ' - '
               || SQLERRM;
			   
            o_return_status := 'e';
            o_return_message := v_error_msg;
			
            DBMS_OUTPUT.PUT_LINE (v_error_msg);
      END;

      COMMIT;

      DBMS_OUTPUT.PUT_LINE ('=========================================================================');

      o_return_status := v_return_status;
      o_return_message := v_return_message;
	  
   EXCEPTION
      WHEN OTHERS
      THEN
         v_error_msg :=
               c_package_name
            || '.'
            || c_procedure_name
            || ' terminated due to '
            || SQLCODE
            || ' - '
            || SQLERRM;
			
         o_return_status := 'E';
         o_return_message := v_error_msg;
         dbms_output.put_line (v_error_msg);
		 
   END assets_extract;

   -----------------------------------------------------------------------------------------------------------
   PROCEDURE main (p_errbuf             OUT VARCHAR2,
                   p_retcode            OUT VARCHAR2,
                   p_run_date_from   IN     DATE DEFAULT '01-JAN-1900',
                   p_run_date_to     IN     DATE DEFAULT '31-DEC-2199')
   IS
      c_procedure_name   CONSTANT VARCHAR2 (240) := 'MAIN';
      v_return_status             VARCHAR2 (240) := 'S';
      v_return_message            VARCHAR2 (240) := 'No Error';

      v_run_date_from             DATE := p_run_date_from;
      v_run_date_to               DATE := p_run_date_to;
   BEGIN
      ------------------------------------------------------------------------------------------------------
      DBMS_OUTPUT.PUT_LINE ('Current system time is ' || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
      DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');
      ------------------------------------------------------------------------------------------------------
      DBMS_OUTPUT.PUT_LINE ('Calling ASSETS_EXTRACT');

      assets_extract (p_run_date_from    => v_run_date_from,                  --  in date,
                      p_run_date_to      => v_run_date_to,                    --  in date,
                      o_return_status    => v_return_status,               --out varchar2,
                      o_return_message   => v_return_message               -- OUT VARCHAR2
                                                            );

      run_updates;

      DBMS_OUTPUT.PUT_LINE ('+---------------------------------------------------+');
      ------------------------------------------------------------------------------------------------------
      DBMS_OUTPUT.PUT_LINE ('Current system time is ' || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	p_retcode :=   v_return_status;
	p_errbuf := v_return_message;
	  
   EXCEPTION
      WHEN OTHERS
      THEN
         v_error_msg :=  C_PACKAGE_NAME|| '.' || C_PROCEDURE_NAME || ' terminated due to '  || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE (V_ERROR_MSG);
		 p_retcode :=   'E';
		 p_errbuf := v_error_msg;
   END MAIN;

   PROCEDURE run_updates
   IS
      CURSOR cur_asset_ext_det
      IS
         SELECT * FROM canon_e618_asset_extract_tbl;
		 
   BEGIN
   
      FOR rec_asset IN cur_asset_ext_det
      LOOP
         BEGIN
            UPDATE canon_e618_asset_extract_tbl eat
               SET (maint_contract_number,
                    maint_cont_term_start_date,
                    maint_cont_term_end_date,
                    maint_contract_terms) =
                      (SELECT dc.ds_contr_num,
                              CAST (TO_TIMESTAMP (dc.contr_vrsn_eff_from_dt,'RRRRMMDDHH24MISSFF3') AS DATE) start_date,
                              CAST (TO_TIMESTAMP (dc.contr_vrsn_eff_thru_dt,'RRRRMMDDHH24MISSFF3') AS DATE) end_date,
                              ROUND (MONTHS_BETWEEN (CAST (TO_TIMESTAMP (dc.contr_vrsn_eff_thru_dt,'RRRRMMDDHH24MISSFF3') AS DATE),
													 CAST (TO_TIMESTAMP (dc.contr_vrsn_eff_from_dt,'RRRRMMDDHH24MISSFF3') AS DATE))) term
                         FROM ds_contr dc, ds_contr_sts dcs, ds_contr_catg dcc
                        WHERE     1 = 1
                              AND dc.glbl_cmpy_cd = dcc.glbl_cmpy_cd
                              AND dc.ezcancelflag = dcc.ezcancelflag
                              AND dc.ds_contr_catg_cd = dcc.ds_contr_catg_cd
                              AND dc.glbl_cmpy_cd = dcs.glbl_cmpy_cd
                              AND dc.ezcancelflag = dcs.ezcancelflag
                              AND dc.ds_contr_sts_cd = dcs.ds_contr_sts_cd
                              AND dc.glbl_cmpy_cd = 'ADB'
                              AND dc.ezcancelflag = '0'
                              AND EXISTS
                                     (SELECT svc_mach_mstr_pk
                                        FROM ds_contr_dtl dcd
                                       WHERE     dcd.glbl_cmpy_cd = dc.glbl_cmpy_cd
                                             AND dcd.ezcancelflag = dc.ezcancelflag
                                             AND dcd.ds_contr_pk = dc.ds_contr_pk
                                             AND dcd.svc_mach_mstr_pk = rec_asset.instance_id)
                              AND UPPER (dcc.ds_contr_catg_nm) IN ('WARRANTY', 'REGULAR') --('SERVICE', 'WARRANTY')
                              AND UPPER (dcs.ds_contr_sts_desc_txt) IN ('ACTIVE',
                                                                        'BILLING HOLD',
                                                                        'ENTERED',
                                                                        'QA HOLD',
                                                                        'SIGNED',
                                                                        'RENEWED',
                                                                        'ENTERED',
                                                                        'APPROVED',
                                                                        'EFFECTIVE'))
             WHERE instance_id = rec_asset.instance_id
               AND serial_number = rec_asset.serial_number;
				   
         EXCEPTION
            WHEN OTHERS
            THEN
               log_error (
                  'RUN_UPDATES',
                  rec_asset.instance_id,
                  rec_asset.serial_number,
                  NULL,
                  NULL,
                  NULL,
                     'Error while updating Maintenance Contract details on CANON_E618_ASSET_EXTRACT_TBL: '
                  || SQLCODE
                  || ' - '
                  || SUBSTR (SQLERRM, 1, 500));
				  
         END;

         /*
                 update CANON_E618_ASSET_EXTRACT_TBL a
                     set (MAINT_CONTRACT_NUMBER, MAINT_CONT_TERM_START_DATE, MAINT_CONT_TERM_END_DATE, MAINT_CONTRACT_TERMS) =
                     (  SELECT        okh.contract_number
                        , okh.start_date
                        , okh.end_date
                        , ROUND( MONTHS_BETWEEN( okh.end_date, okh.start_date ) )
                   FROM   okc_k_items oki
                        , csi_item_instances cii
                        , okc_k_headers_b okh
                        , okc_k_lines_b okl
                   WHERE  TO_NUMBER( oki.object1_id1 ) = cii.instance_id
                   AND    oki.object1_id1 = TO_CHAR( cii.instance_id )
                   AND    cii.instance_id = a.instance_id
                   AND    okh.id = oki.dnz_chr_id
                   AND    okl.id = oki.cle_id
                   AND    oki.jtot_object1_code = 'OKX_CUSTPROD'
                   AND    okh.scs_code IN ('SERVICE', 'WARRANTY')
                   AND ROWNUM = 1
                   AND    okh.sts_code IN
                            ( 'ACTIVE'
                           , 'BILL_HOLD'
                           , 'BILLING_HOLD'
                           , 'ENTERED'
                           , 'QA_HOLD'
                           , 'SIGNED' )
                           );
         COMMIT;
         */



         --Taken Care in the above insert into canon_e618_asset_extract_tbl
         /*
         update CANON_E618_ASSET_EXTRACT_TBL a
         set owner_party_name =
         (select party_name from hz_parties b
         where a.owner_party_id = b.party_id);

         COMMIT;

         update CANON_E618_ASSET_EXTRACT_TBL a
            set last_rma_order_type =
                (
                select tl.name from oe_transaction_types_tl tl, oe_order_headers_all ooh, oe_order_lines_all ool
                     where ool.line_id = a.last_oe_rma_line_id
                     and ool.header_id = ooh.header_id
                     and ooh.order_type_id = tl.transaction_type_id);

         COMMIT;

         update CANON_E618_ASSET_EXTRACT_TBL a
            set (last_oe_order_number, last_oe_order_type ) =
                (
                select ooh.order_number, tl.name from oe_transaction_types_tl tl, oe_order_headers_all ooh, oe_order_lines_all ool
                     where ool.line_id = a.last_oe_order_line_id
                     and ool.header_id = ooh.header_id
                     and ooh.order_type_id = tl.transaction_type_id);

         COMMIT;
         */
         BEGIN
            UPDATE canon_e618_asset_extract_tbl eat
               SET delivery_date =
                      (SELECT CAST (TO_TIMESTAMP (sod.actl_dely_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) delivery_date
                         FROM cpo_dtl cd,
                              shpg_ord so,
                              shpg_pln sp,
                              shpg_ord_dtl sod
                        WHERE     1 = 1
                              AND cd.cpo_ord_num = sp.trx_hdr_num
                              AND cd.cpo_dtl_line_num = sp.trx_line_num
                              AND cd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
                              AND sp.so_num = so.so_num
                              AND sod.shpg_pln_num = sp.shpg_pln_num
                              AND cd.cpo_ord_num = rec_asset.last_oe_order_number --'20001713'
                              AND    cd.cpo_ord_num
                                  || cd.ds_cpo_config_pk
                                  || cd.cpo_dtl_line_num
                                  || cd.cpo_dtl_line_sub_num =
                                     rec_asset.last_oe_order_line_id)
             WHERE     instance_id = rec_asset.instance_id
                   AND last_oe_order_number = rec_asset.last_oe_order_number;
         EXCEPTION
            WHEN OTHERS
            THEN
               LOG_ERROR (
                  'RUN_UPDATES',
                  rec_asset.instance_id,
                  rec_asset.serial_number,
                  rec_asset.last_oe_order_number,
                  rec_asset.last_oe_order_line_id,
                  NULL,
                     'Error while updating DELIVERY_DATE on CANON_E618_ASSET_EXTRACT_TBL: '
                  || SQLCODE
                  || ' - '
                  || SUBSTR (SQLERRM, 1, 500));
         END;

         /*
               UPDATE CANON_E618_ASSET_EXTRACT_TBL A
                  SET DELIVERY_DATE =
                         (SELECT DISTINCT ULTIMATE_DROPOFF_DATE
                            --    INTO l_delivery_date
                            FROM WSH_DELIVERY_DETAILS WDD,
                                 WSH_NEW_DELIVERIES WND,
                                 WSH_DELIVERY_ASSIGNMENTS WDA
                           WHERE     WDD.DELIVERY_DETAIL_ID = WDA.DELIVERY_DETAIL_ID
                                 AND WDA.DELIVERY_ID = WND.DELIVERY_ID
                                 AND WDD.SOURCE_LINE_ID = A.LAST_OE_ORDER_LINE_ID);
         COMMIT;
         */

         BEGIN
            UPDATE canon_e618_asset_extract_tbl eat
               SET service_dealer =
                      (SELECT pv.prnt_vnd_nm
						FROM vnd dv,
							 vnd_tp_reln vtr,
							 s21_psn sp,
							 ctac_psn cp,
							 ds_ctac_psn_reln dcpr,
							 svc_mach_mstr smm,
							 prnt_vnd pv
					   WHERE     1 = 1
							 AND sp.psn_tp_cd = '2'          --(3rd party rep)
							 AND sp.ctac_psn_pk = cp.ctac_psn_pk
							 AND dcpr.ctac_psn_pk = cp.ctac_psn_pk
							 AND dv.loc_num = dcpr.loc_num
							 AND vtr.vnd_cd = dv.vnd_cd
							 AND vtr.vnd_tp_cd = '15'       --(service dealer)
							 AND dv.prnt_vnd_pk = pv.prnt_vnd_pk
							 AND smm.req_tech_cd = sp.psn_cd
							 AND smm.glbl_cmpy_cd = dv.glbl_cmpy_cd
							 AND smm.ezcancelflag = dv.ezcancelflag
							 AND smm.glbl_cmpy_cd = pv.glbl_cmpy_cd
							 AND smm.ezcancelflag = pv.ezcancelflag
							 AND smm.glbl_cmpy_cd = vtr.glbl_cmpy_cd
							 AND smm.ezcancelflag = vtr.ezcancelflag
							 AND smm.glbl_cmpy_cd = sp.glbl_cmpy_cd
							 AND smm.ezcancelflag = sp.ezcancelflag
							 AND smm.glbl_cmpy_cd = cp.glbl_cmpy_cd
							 AND smm.ezcancelflag = cp.ezcancelflag
							 AND smm.glbl_cmpy_cd = dcpr.glbl_cmpy_cd
							 AND smm.ezcancelflag = dcpr.ezcancelflag
							 AND smm.glbl_cmpy_cd = 'ADB'
							 AND smm.ezcancelflag = '0'
							 AND smm.svc_mach_mstr_pk = eat.instance_id)
				WHERE eat.instance_id = rec_asset.instance_id;
         EXCEPTION
            WHEN OTHERS
            THEN
               LOG_ERROR (
                  'RUN_UPDATES',
                  rec_asset.instance_id,
                  rec_asset.serial_number,
                  NULL,
                  NULL,
                  NULL,
                     'Error while updating SERVICE_DEALER on CANON_E618_ASSET_EXTRACT_TBL: '
                  || SQLCODE
                  || ' - '
                  || SUBSTR (SQLERRM, 1, 500));
         END;
      END LOOP;

      /*
            UPDATE CANON_E618_ASSET_EXTRACT_TBL A
               SET SERVICE_DEALER =
                      (SELECT B.SOURCE_NAME
                         FROM CSI_ITEM_INSTANCES CSI,
                              CSI_I_PARTIES A,
                              JTF_RS_RESOURCE_EXTNS B,
                              PO_VENDOR_CONTACTS C,
                              PO_VENDOR_SITES_ALL D,
                              PO_VENDORS E
                        WHERE     CSI.SERIAL_NUMBER = A.SERIAL_NUMBER
                              AND A.INSTANCE_ID = CSI.INSTANCE_ID
                              AND A.PARTY_SOURCE_TABLE = 'PO_VENDORS'
                              AND A.RELATIONSHIP_TYPE_CODE = 'TECHNICAL'
                              AND NVL (A.ACTIVE_END_DATE, SYSDATE) >= SYSDATE
                              AND A.PARTY_ID = B.SOURCE_ID
                              AND B.CATEGORY = 'SUPPLIER_CONTACT'
                              AND B.SOURCE_ID = C.VENDOR_CONTACT_ID
                              AND C.VENDOR_SITE_ID = D.VENDOR_SITE_ID
                              AND NVL (D.INACTIVE_DATE, SYSDATE) >= SYSDATE
                              AND D.VENDOR_ID = E.VENDOR_ID
                              AND ROWNUM < 2);
      */

      COMMIT;
   END RUN_UPDATES;

   PROCEDURE log_error (p_process_name   IN VARCHAR2,
                        p_in_key1        IN VARCHAR2,
                        p_in_key2        IN VARCHAR2,
                        p_in_key3        IN VARCHAR2,
                        p_in_key4        IN VARCHAR2,
                        p_in_key5        IN VARCHAR2,
                        p_error_msg      IN VARCHAR2)
   AS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO e618_emsd_errors_tbl (program_name,
                                        process_name,
                                        key1,
                                        key2,
                                        key3,
                                        key4,
                                        key5,
                                        error_msg,
                                        error_date)
           VALUES ('CANON_E618_ASSETS_STG_PKG',
                   p_process_name,
                   p_in_key1,
                   p_in_key2,
                   p_in_key3,
                   p_in_key4,
                   p_in_key5,
                   p_error_msg,
                   SYSDATE);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.PUT_LINE (
               'Unexpected error occured while logging the errors in E618_EMSD_ERRORS_TBL. Oracle Error Message:'
            || SUBSTR (SQLERRM, 1, 500));
   END log_error;
   
END CANON_E618_ASSETS_STG_PKG;
/
