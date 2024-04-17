CREATE OR REPLACE PACKAGE CANON_E580_ITT_PROCESS_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_ITT_PROCESS_PKG.sql                                                                                            --
-- Created On :   08/11/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to extract the eligible Drop ship sales order lines and load into     --
--                ITT workbench custom tables.  Modified all the existing program units as required to port ITT workbench from Oracle to S21--
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            11-Aug-2015    Initial   																			--
-- 2.0        Balaji Gowravaram			 09-Mar-2017	Multiple changes for S21 functionalities                                            --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
  
   g_glbl_cmpy_cd VARCHAR2(10):='ADB';
   
   PROCEDURE itt_exists (  p_so_number         IN  VARCHAR2,
                           p_ship_to_cust_cd   IN  VARCHAR2,
                           p_itt_number        OUT VARCHAR2,
                           p_itt_exists        OUT VARCHAR2,
                           p_same_ship_to      OUT VARCHAR2,
                           p_po_exists         OUT VARCHAR2,
                           p_data_mode         OUT VARCHAR2
                        );
                                               
   PROCEDURE sales_orders_extract ( p_order_number   IN  VARCHAR2,
                                    p_from_date      IN  VARCHAR2,
                                    p_to_date        IN  VARCHAR2,
                                    p_debug_msgs    OUT  VARCHAR2
                                  );
                                  
   PROCEDURE dealer_install_confirm( p_itt_number   IN VARCHAR2,
                                     p_so_number    IN VARCHAR2,
                                     p_line_number  IN VARCHAR2,
                                     p_username     IN VARCHAR2,
                                     p_err_msg      IN VARCHAR2,
									 x_ret_flag     OUT VARCHAR2,
									 x_ret_message  OUT VARCHAR2
                                   );
								   
   PROCEDURE derive_po_cancel_lines (  p_ord_num        IN  VARCHAR2
									 , x_create_po_tbl  OUT CANON_E580_CREATE_PO_TBL_TYP
									 , x_ret_flag       OUT VARCHAR2
									 , x_ret_message    OUT VARCHAR2
									);
									
   PROCEDURE add_append_lines_note (p_itt_number   IN  VARCHAR2,
									p_order_number IN  VARCHAR2);

END CANON_E580_ITT_PROCESS_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E580_ITT_PROCESS_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_ITT_PROCESS_PKG.sql                                                                                            --
-- Created On :   08/11/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to extract the eligible Drop ship sales order lines and load into     --
--                ITT workbench custom tables.  Modified all the existing program units as required to port ITT workbench from Oracle to S21--
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            11-Aug-2015    Initial                                                                             --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --

   -- Declare common Variable
   l_error_msg      VARCHAR2 (4000) := 'No Error';
   l_itt_err_tbl    canon_e580_itt_util_pkg.itt_err_tbl_typ;

   -----------------------------------------------------------------------------------------------------------------------------------
   --This procedure is invoked from procedure "sales_orders_extract", to derive Insert/Update mode based on ITT order for SO/Customer
   --Logic: If ITT order already exists for SO/Customer and also PO created, then Insert mode with adding lines to new ITT order num.
   --If ITT order already exists for SO/Customer and PO not created, then Update mode to append lines for same existing ITT order
   --If ITT order already exists for SO but for different customer, then Insert mode with adding lines to new ITT order num.
   --If ITT order already does not exists for SO, then Insert mode with adding lines to new ITT order num.
   -----------------------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_exists( p_so_number        IN   VARCHAR2,
                         p_ship_to_cust_cd  IN   VARCHAR2,
                         p_itt_number       OUT  VARCHAR2,
                         p_itt_exists       OUT  VARCHAR2,
                         p_same_ship_to     OUT  VARCHAR2,
                         p_po_exists        OUT  VARCHAR2,
                         p_data_mode        OUT  VARCHAR2
                       )
   AS
      TYPE itt_typ IS RECORD(   itt_number       VARCHAR2(80)
                              , ship_to_cust_cd  VARCHAR2(30)
                             );

      TYPE itt_tbl IS TABLE OF itt_typ INDEX BY BINARY_INTEGER;
      l_itt_tbl          itt_tbl;
      l_itt_exists       VARCHAR2 (1) := 'N';
      l_same_ship_to     VARCHAR2 (1) := 'N';
      l_po_exists        VARCHAR2 (1) := 'N';
      l_data_mode        VARCHAR2 (3) := 'I';
      l_itt_number       VARCHAR2 (240);
   BEGIN
      BEGIN
         SELECT DISTINCT
                itt_number
               ,ship_to_customer_cd  ship_to_cust_cd
         BULK COLLECT INTO l_itt_tbl
         FROM   canon_e580_itt_headers_tbl cih
         WHERE  cih.order_number = p_so_number
         AND    NVL(cih.cancelled_flag, 'N') <> 'Y';
      EXCEPTION
         WHEN OTHERS
         THEN
           l_error_msg := 'Error while querying for ITT number '|| SQLCODE || ' - ' || SQLERRM;
           dbms_output.put_line(l_error_msg);
      END;
      IF (l_itt_tbl.COUNT > 0) THEN
         l_itt_exists := 'Y';
         
         --Derive l_same_ship_to, l_itt_number
         FOR i IN 1..l_itt_tbl.COUNT
         LOOP
            IF (l_itt_tbl(i).ship_to_cust_cd = p_ship_to_cust_cd)
            THEN
               l_same_ship_to := 'Y';
               l_itt_number   := l_itt_tbl(i).itt_number;
            END IF;
            
            BEGIN
               SELECT DISTINCT 'Y'
               INTO  l_po_exists
               FROM  canon_e580_itt_lines_tbl cil
               WHERE itt_number = l_itt_tbl(i).itt_number
               AND cil.cusa_po_number IS NOT NULL
               AND NVL(cil.cancelled_flag, 'N') <> 'Y';
            EXCEPTION
               WHEN OTHERS THEN
                  l_po_exists := 'N';
            END;
         END LOOP;
      END IF;
      p_itt_number   := l_itt_number;
      p_itt_exists   := l_itt_exists;
      p_same_ship_to := l_same_ship_to;
      p_po_exists    := l_po_exists;

      IF NVL(l_itt_exists, 'N') = 'Y'
      THEN
         IF NVL(l_same_ship_to, 'N') = 'Y'
         THEN
            IF NVL(l_po_exists, 'N') = 'Y'
            THEN
               l_data_mode := 'A';  --bgowravaram
            ELSE
               l_data_mode := 'U';
            END IF;
         ELSE
            l_data_mode := 'I';
         END IF;
      ELSE
         l_data_mode := 'I';
      END IF;

      p_data_mode := l_data_mode;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Error in procedure itt_exists, Error Message: ' || SQLCODE || ' - ' || SQLERRM;
         dbms_output.put_line(l_error_msg);
   END itt_exists;

   -------------------------------------------------------------------------------------------------------------
   --This procedure is the independent program used for E580-ITT Workbench orders, scheduled as batch program
   --Logic : Identifies all the eligible ITT Drop ship sales orders/lines and populates in ITT custom tables.
   -------------------------------------------------------------------------------------------------------------
   PROCEDURE sales_orders_extract( p_order_number   IN   VARCHAR2,
                                   p_from_date      IN   VARCHAR2,
                                   p_to_date        IN   VARCHAR2,
                                   p_debug_msgs    OUT   VARCHAR2
                                 )
   IS
      TYPE itt_rec_typ IS RECORD(itt_number VARCHAR2(80), order_number VARCHAR2(30));
      TYPE itt_tbl_typ IS TABLE OF itt_rec_typ INDEX BY BINARY_INTEGER;
      TYPE itt_hdr_tbl_typ IS TABLE OF canon_e580_itt_headers_tbl%ROWTYPE INDEX BY BINARY_INTEGER;
      TYPE itt_line_tbl_typ IS TABLE OF canon_e580_itt_lines_tbl%ROWTYPE INDEX BY BINARY_INTEGER;
      TYPE itt_svc_tbl_typ IS TABLE OF canon_e580_service_pricing_tbl%ROWTYPE INDEX BY BINARY_INTEGER;
      l_itt_hdr_tbl          itt_hdr_tbl_typ;
      l_itt_line_tbl         itt_line_tbl_typ;
      l_itt_svc_tbl          itt_svc_tbl_typ;
      l_itt_tbl              itt_tbl_typ;
	  l_itt_tbl_append       itt_tbl_typ;
      l_from_date            VARCHAR2(10);
      l_to_date              VARCHAR2(10);
      l_itt_number           VARCHAR2(80);
      l_meters_count         NUMBER := 1;
      l_itt_num              NUMBER := 0;
      l_contract_type        VARCHAR2(240) := NULL;
      l_srv_plan_type        VARCHAR2(240) := NULL;
      l_term                 NUMBER := 0;
      l_base_price           NUMBER := 0;
      l_bill_cycle           VARCHAR2(240) := NULL;
      l_meter_type           VARCHAR2(240) := NULL;
      l_avg_copy_volume      NUMBER := 0;
      l_copy_inclusion       NUMBER := 0;
      l_multiplier           NUMBER := 0;
      l_itt_num_exist        VARCHAR2(240) := NULL;
	  l_svc_itt_number       VARCHAR2(240) := NULL;
      l_itt_exists           VARCHAR2(1)   := 'N';
      l_po_exists            VARCHAR2(1)   := 'N';
      l_same_ship_to         VARCHAR2(1)   := 'N';
      l_data_mode            VARCHAR2(2)   := NULL;
      l_mail_invoices_to     VARCHAR2(240) := 'CANON SOLUTIONS AMERICA';
      l_user_id              VARCHAR2(20)  := 'S21EXTN_E580';  --Derive generic user id;
      l_debug_step           NUMBER := 0;
      l_itt_hdr_cnt          NUMBER := 0;
      l_itt_line_cnt         NUMBER := 0;
      l_itt_svc_cnt          NUMBER := 0;
	  l_append_mode          VARCHAR2(10);
	  l_add_to_note          VARCHAR2(1);
      l_debug_msgs           VARCHAR2(32767);

      ---This is main cursor to fetch all ITT eligible sales orders.
      CURSOR c_elig_so(p_fdate IN VARCHAR2, p_tdate IN VARCHAR2) 
      IS
        (SELECT DISTINCT
               c.cpo_ord_num             so_number
              ,dcc.ship_to_cust_loc_cd   ship_to_cust_cd
			  ,dcc.ds_ord_posn_num
        FROM   cpo c
              ,cpo_dtl cd
              ,ds_ord_line_catg cat
              ,ds_ord_sts_v sts
              ,mdse mdse
              ,toc sr
              ,ds_cpo_config dcc
              ,shpg_pln sp
			  ,ord_line_src ols
        WHERE  1=1
        AND    c.cpo_ord_num = NVL(p_order_number, c.cpo_ord_num)
        AND    TO_DATE(SUBSTR(c.EZUPTIME,1,8),'YYYYMMDD') BETWEEN TO_DATE(p_fdate,'YYYYMMDD') AND TO_DATE(p_tdate,'YYYYMMDD')
        AND    c.cpo_ord_num = cd.cpo_ord_num
        AND    c.cpo_ord_num = sts.cpo_ord_num
        AND    c.cpo_ord_num =  sp.trx_hdr_num
        AND   cd.cpo_dtl_line_num     = sts.cpo_dtl_line_num
        AND   cd.cpo_dtl_line_sub_num = sts.cpo_dtl_line_sub_num
        AND   cd.cpo_dtl_line_num     = sp.trx_line_num
        AND   cd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
        AND   cd.ds_cpo_config_pk     = dcc.ds_cpo_config_pk
        AND   cd.ds_ord_line_catg_cd  = cat.ds_ord_line_catg_cd
        AND   cd.mdse_cd = mdse.mdse_cd        
		AND   (mdse.invty_ctrl_flg <> 'N' OR ( mdse.invty_ctrl_flg = 'N' AND mdse.coa_mdse_tp_cd = '11'))
        AND   cd.sls_rep_or_sls_team_toc_cd = sr.toc_cd
        AND   sp.aval_po_qty > 0    --Sufficient qty for PO creation
        AND   sp.po_req_flg = 'Y'   --PO Required flag
        AND   sp.so_close_flg = 'N' 
        AND   sp.rte_sts_cd = 0     --Un-Routed
        AND   sp.shpg_sts_cd = 10   --Validated
        AND    c.ezcancelflag = '0'   --Not Cancelled
        AND   cd.ezcancelflag = '0'
        AND  cat.ezcancelflag = '0'
        AND  sts.ezcancelflag = '0'
        AND   sr.ezcancelflag = '0'
        AND mdse.ezcancelflag = '0'
        AND  dcc.ezcancelflag = '0'
        AND   sp.ezcancelflag = '0'        
        AND    c.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cat.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  sts.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sr.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mdse.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cd.ord_line_src_cd = ols.ord_line_src_cd
		AND  ols.ord_line_src_nm in (SELECT cvt.VAL2
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
									 WHERE     ct.cd_name = 'CANON_E580_LINE_TYPE'
										   AND ct.cd_id = cvt.cd_id
										   AND cvt.val1 <> 'LINE_TYPE_02'
										   AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
        AND    NVL(sts.ord_book_flg, 'N')   = 'Y'       --Booked Flag
        AND    NVL(sts.cpo_open_flg, 'N')   = 'Y'       --Open Flag
        AND    NVL(c.cpo_canc_flg,'N')      = 'N'       --Cancel Flag
        AND    NVL(cd.cpo_dtl_canc_flg,'N') = 'N'       --Cancel Flag
        AND    sts.ord_hdr_sts_cd  NOT IN ('90','99')   --Not Closed/Cancelled
        AND    sts.ord_line_sts_cd NOT IN ('90','99')   --Not Closed/Cancelled
        AND    NOT EXISTS( SELECT 1 FROM canon_e580_itt_lines_tbl
                           WHERE order_number = cd.cpo_ord_num
                           AND   line_number  = cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num)
		UNION ALL
		SELECT DISTINCT
               c.cpo_ord_num             so_number
              ,dcc.ship_to_cust_loc_cd   ship_to_cust_cd
			  ,dcc.ds_ord_posn_num
        FROM   cpo c
              ,cpo_dtl cd
              ,ds_ord_line_catg cat
              ,ds_ord_sts_v sts
              ,mdse mdse
              ,toc sr
              ,ds_cpo_config dcc
              ,shpg_pln sp
			  ,ord_line_src ols
        WHERE  1=1
        AND    c.cpo_ord_num = NVL(p_order_number, c.cpo_ord_num)
        AND    TO_DATE(SUBSTR(c.EZUPTIME,1,8),'YYYYMMDD') BETWEEN TO_DATE(p_fdate,'YYYYMMDD') AND TO_DATE(p_tdate,'YYYYMMDD')
        AND    c.cpo_ord_num = cd.cpo_ord_num
        AND    c.cpo_ord_num = sts.cpo_ord_num
        AND    c.cpo_ord_num =  sp.trx_hdr_num
        AND   cd.cpo_dtl_line_num     = sts.cpo_dtl_line_num
        AND   cd.cpo_dtl_line_sub_num = sts.cpo_dtl_line_sub_num
        AND   cd.cpo_dtl_line_num     = sp.trx_line_num
        AND   cd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
        AND   cd.ds_cpo_config_pk     = dcc.ds_cpo_config_pk
        AND   cd.ds_ord_line_catg_cd  = cat.ds_ord_line_catg_cd
        AND   cd.mdse_cd = mdse.mdse_cd 
		AND   (mdse.invty_ctrl_flg <> 'N' OR ( mdse.invty_ctrl_flg = 'N' AND mdse.coa_mdse_tp_cd = '11'))		
        AND   cd.sls_rep_or_sls_team_toc_cd = sr.toc_cd
        AND   sp.so_close_flg = 'N' 
        AND    c.ezcancelflag = '0'   --Not Cancelled
        AND   cd.ezcancelflag = '0'
        AND  cat.ezcancelflag = '0'
        AND  sts.ezcancelflag = '0'
        AND   sr.ezcancelflag = '0'
        AND mdse.ezcancelflag = '0'
        AND  dcc.ezcancelflag = '0'
        AND   sp.ezcancelflag = '0'        
        AND    c.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cat.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  sts.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sr.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mdse.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cd.ord_line_src_cd = ols.ord_line_src_cd
		AND  ols.ord_line_src_nm in (SELECT cvt.VAL2
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
									 WHERE     ct.cd_name = 'CANON_E580_LINE_TYPE'
										   AND ct.cd_id = cvt.cd_id
										   AND cvt.val1 = 'LINE_TYPE_02'
										   AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
        AND    NVL(sts.ord_book_flg, 'N')   = 'Y'       --Booked Flag
        AND    NVL(sts.cpo_open_flg, 'N')   = 'Y'       --Open Flag
        AND    NVL(c.cpo_canc_flg,'N')      = 'N'       --Cancel Flag
        AND    NVL(cd.cpo_dtl_canc_flg,'N') = 'N'       --Cancel Flag
        AND    sts.ord_hdr_sts_cd  NOT IN ('90','99')   --Not Closed/Cancelled
        AND    sts.ord_line_sts_cd NOT IN ('90','99')   --Not Closed/Cancelled
        AND    NOT EXISTS( SELECT 1 FROM canon_e580_itt_lines_tbl
                           WHERE order_number = cd.cpo_ord_num
                           AND   line_number  = cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num))
        ORDER BY 1, 3;

         TYPE elig_so_tbl IS TABLE OF c_elig_so%ROWTYPE INDEX BY BINARY_INTEGER;         
         l_elig_so_tbl   elig_so_tbl;
     
     --This cursor is fetch ITT eligible sales order header details and inserts into canon_e580_itt_headers_tbl
     CURSOR c_itt_hdr_dtls(   p_so_num     VARCHAR2
                            , p_shiptocd   VARCHAR2
                          )
     IS
        (SELECT  DISTINCT * FROM
			(SELECT 
                 c.cpo_ord_num   order_number
               ,(SELECT cvt.val1 
                 FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                 WHERE  ct.cd_name = 'CANON_E580_HDR_STATUS'
                 AND    ct.cd_id = cvt.cd_id
                 AND   cvt.val51 = DECODE(UPPER(sts.ord_hdr_sts_nm),'CLOSED',3,1)
                 AND  (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                )                itt_status
               , sr.toc_cd       salesrep_cd
               , sr.toc_nm       salesrep_nm
               , c.prc_catg_cd  price_list_cd
               /*,(SELECT zn.coa_br_zn_nm FROM coa_br br, coa_br_zn zn WHERE br.coa_br_cd = sr.coa_br_cd AND br.coa_br_zn_cd = zn.coa_br_zn_cd
                 AND br.glbl_cmpy_cd = g_glbl_cmpy_cd AND zn.glbl_cmpy_cd = g_glbl_cmpy_cd
                )*/
               , canon_e580_itt_util_pkg.get_salesrep_attr(sr.toc_cd,'ZONE')  sales_zone
               ,(SELECT rg.coa_extn_nm FROM coa_extn rg WHERE rg.coa_extn_cd = sr.coa_extn_cd AND rg.glbl_cmpy_cd = g_glbl_cmpy_cd) sales_region
               ,(SELECT br.coa_br_nm   FROM coa_br br   WHERE br.coa_br_cd = sr.coa_br_cd AND br.glbl_cmpy_cd = g_glbl_cmpy_cd) sales_branch
               ,(SELECT cvt.val2       FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                 WHERE  ct.cd_name = 'CANON_E580_CMAP'
                 AND    ct.cd_id = cvt.cd_id
                 AND   (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                 AND    cvt.val1 = 'CMAP_01')  cmap_yes_no
               , TO_DATE(c.add_rdd_dt,'YYYYMMDD') scheduled_delivery_date
               , dcc.ship_to_cust_loc_cd          ship_to_customer_cd
               , dcc.ship_to_loc_nm               ship_to_customer
               , NULL                             site_use_id
               , NULL                             cust_acct_site_id
               , NULL                             party_id
               , CAST ( TO_TIMESTAMP (c.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE) order_creation_date
               , TO_DATE(SUBSTR(c.ord_book_ts,1,14),'YYYYMMDDHH24MISS')          booked_date
               , sts.ord_hdr_sts_nm order_status
               , c.cpo_canc_flg    cancelled_flag
               , CASE sts.ord_hdr_sts_nm
                 WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (c.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
                 ELSE NULL
                 END  itt_closed_date
               , CASE sts.ord_hdr_sts_nm
                 WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (c.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE)
                 ELSE SYSDATE
                 END  itt_creation_date
               , CASE sts.ord_hdr_sts_nm
                 WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (c.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
                 ELSE SYSDATE
                 END  itt_update_date
        FROM   cpo c
              ,cpo_dtl cd
              ,ds_ord_line_catg cat
              ,ds_ord_sts_v sts
              ,mdse mdse
              ,toc sr
              ,ds_cpo_config dcc
              ,shpg_pln sp
			  ,ord_line_src ols
        WHERE  1=1
        AND    c.cpo_ord_num           = p_so_num
        AND    dcc.ship_to_cust_loc_cd = p_shiptocd
        AND    c.cpo_ord_num = cd.cpo_ord_num
        AND    c.cpo_ord_num = sts.cpo_ord_num
        AND    c.cpo_ord_num =  sp.trx_hdr_num
        AND   cd.cpo_dtl_line_num     = sts.cpo_dtl_line_num
        AND   cd.cpo_dtl_line_sub_num = sts.cpo_dtl_line_sub_num
        AND   cd.cpo_dtl_line_num     = sp.trx_line_num
        AND   cd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
        AND   cd.ds_cpo_config_pk     = dcc.ds_cpo_config_pk
        AND   cd.ds_ord_line_catg_cd  = cat.ds_ord_line_catg_cd
        AND   cd.mdse_cd = mdse.mdse_cd  
		AND   (mdse.invty_ctrl_flg <> 'N' OR ( mdse.invty_ctrl_flg = 'N' AND mdse.coa_mdse_tp_cd = '11'))
        AND   cd.sls_rep_or_sls_team_toc_cd = sr.toc_cd
        AND   sp.aval_po_qty > 0    --Sufficient qty for PO creation
        AND   sp.po_req_flg = 'Y'   --PO Required flag
        AND   sp.so_close_flg = 'N' 
        AND   sp.rte_sts_cd = 0     --Un-Routed
        AND   sp.shpg_sts_cd = 10   --Validated
        AND    c.ezcancelflag = '0'   --Not Cancelled
        AND   cd.ezcancelflag = '0'
        AND  cat.ezcancelflag = '0'
        AND  sts.ezcancelflag = '0'
        AND   sr.ezcancelflag = '0'
        AND mdse.ezcancelflag = '0'
        AND  dcc.ezcancelflag = '0'
        AND   sp.ezcancelflag = '0'        
        AND    c.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cat.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  sts.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sr.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mdse.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.ord_line_src_cd = ols.ord_line_src_cd
		AND  ols.ord_line_src_nm in (SELECT cvt.VAL2
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
									 WHERE     ct.cd_name = 'CANON_E580_LINE_TYPE'
										   AND ct.cd_id = cvt.cd_id
										   AND cvt.val1 <> 'LINE_TYPE_02'
										   AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
        AND    NVL(sts.ord_book_flg, 'N')   = 'Y'       --Booked Flag
        AND    NVL(sts.cpo_open_flg, 'N')   = 'Y'       --Open Flag
        AND    NVL(c.cpo_canc_flg,'N')      = 'N'       --Cancel Flag
        AND    NVL(cd.cpo_dtl_canc_flg,'N') = 'N'       --Cancel Flag
        AND    sts.ord_hdr_sts_cd  NOT IN ('90','99')   --Not Closed/Cancelled
        AND    sts.ord_line_sts_cd NOT IN ('90','99')   --Not Closed/Cancelled
        AND    NOT EXISTS( SELECT 1 FROM canon_e580_itt_lines_tbl
                           WHERE order_number = cd.cpo_ord_num
                           AND   line_number  = cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num)
		UNION ALL
		SELECT  
                 c.cpo_ord_num   order_number
               ,(SELECT cvt.val1 
                 FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                 WHERE  ct.cd_name = 'CANON_E580_HDR_STATUS'
                 AND    ct.cd_id = cvt.cd_id
                 AND   cvt.val51 = DECODE(UPPER(sts.ord_hdr_sts_nm),'CLOSED',3,1)
                 AND  (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                )                itt_status
               , sr.toc_cd       salesrep_cd
               , sr.toc_nm       salesrep_nm
               , c.prc_catg_cd  price_list_cd
               /*,(SELECT zn.coa_br_zn_nm FROM coa_br br, coa_br_zn zn WHERE br.coa_br_cd = sr.coa_br_cd AND br.coa_br_zn_cd = zn.coa_br_zn_cd
                 AND br.glbl_cmpy_cd = g_glbl_cmpy_cd AND zn.glbl_cmpy_cd = g_glbl_cmpy_cd
                )*/
               , canon_e580_itt_util_pkg.get_salesrep_attr(sr.toc_cd,'ZONE')  sales_zone
               ,(SELECT rg.coa_extn_nm FROM coa_extn rg WHERE rg.coa_extn_cd = sr.coa_extn_cd AND rg.glbl_cmpy_cd = g_glbl_cmpy_cd) sales_region
               ,(SELECT br.coa_br_nm   FROM coa_br br   WHERE br.coa_br_cd = sr.coa_br_cd AND br.glbl_cmpy_cd = g_glbl_cmpy_cd) sales_branch
               ,(SELECT cvt.val2       FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                 WHERE  ct.cd_name = 'CANON_E580_CMAP'
                 AND    ct.cd_id = cvt.cd_id
                 AND   (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                 AND    cvt.val1 = 'CMAP_01')  cmap_yes_no
               , TO_DATE(c.add_rdd_dt,'YYYYMMDD') scheduled_delivery_date
               , dcc.ship_to_cust_loc_cd          ship_to_customer_cd
               , dcc.ship_to_loc_nm               ship_to_customer
               , NULL                             site_use_id
               , NULL                             cust_acct_site_id
               , NULL                             party_id
               , CAST ( TO_TIMESTAMP (c.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE) order_creation_date
               , TO_DATE(SUBSTR(c.ord_book_ts,1,14),'YYYYMMDDHH24MISS')          booked_date
               , sts.ord_hdr_sts_nm order_status
               , c.cpo_canc_flg    cancelled_flag
               , CASE sts.ord_hdr_sts_nm
                 WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (c.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
                 ELSE NULL
                 END  itt_closed_date
               , CASE sts.ord_hdr_sts_nm
                 WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (c.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE)
                 ELSE SYSDATE
                 END  itt_creation_date
               , CASE sts.ord_hdr_sts_nm
                 WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (c.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
                 ELSE SYSDATE
                 END  itt_update_date
        FROM   cpo c
              ,cpo_dtl cd
              ,ds_ord_line_catg cat
              ,ds_ord_sts_v sts
              ,mdse mdse
              ,toc sr
              ,ds_cpo_config dcc
              ,shpg_pln sp
			  ,ord_line_src ols
        WHERE  1=1
        AND    c.cpo_ord_num           = p_so_num
        AND    dcc.ship_to_cust_loc_cd = p_shiptocd
        AND    c.cpo_ord_num = cd.cpo_ord_num
        AND    c.cpo_ord_num = sts.cpo_ord_num
        AND    c.cpo_ord_num =  sp.trx_hdr_num
        AND   cd.cpo_dtl_line_num     = sts.cpo_dtl_line_num
        AND   cd.cpo_dtl_line_sub_num = sts.cpo_dtl_line_sub_num
        AND   cd.cpo_dtl_line_num     = sp.trx_line_num
        AND   cd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
        AND   cd.ds_cpo_config_pk     = dcc.ds_cpo_config_pk
        AND   cd.ds_ord_line_catg_cd  = cat.ds_ord_line_catg_cd
        AND   cd.mdse_cd = mdse.mdse_cd    
		AND   (mdse.invty_ctrl_flg <> 'N' OR ( mdse.invty_ctrl_flg = 'N' AND mdse.coa_mdse_tp_cd = '11'))
        AND   cd.sls_rep_or_sls_team_toc_cd = sr.toc_cd
        AND   sp.so_close_flg = 'N' 
        AND    c.ezcancelflag = '0'   --Not Cancelled
        AND   cd.ezcancelflag = '0'
        AND  cat.ezcancelflag = '0'
        AND  sts.ezcancelflag = '0'
        AND   sr.ezcancelflag = '0'
        AND mdse.ezcancelflag = '0'
        AND  dcc.ezcancelflag = '0'
        AND   sp.ezcancelflag = '0'        
        AND    c.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cat.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  sts.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sr.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mdse.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cd.ord_line_src_cd = ols.ord_line_src_cd
		AND  ols.ord_line_src_nm in (SELECT cvt.VAL2
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
									 WHERE     ct.cd_name = 'CANON_E580_LINE_TYPE'
										   AND ct.cd_id = cvt.cd_id
										   AND cvt.val1 = 'LINE_TYPE_02'
										   AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
        AND    NVL(sts.ord_book_flg, 'N')   = 'Y'       --Booked Flag
        AND    NVL(sts.cpo_open_flg, 'N')   = 'Y'       --Open Flag
        AND    NVL(c.cpo_canc_flg,'N')      = 'N'       --Cancel Flag
        AND    NVL(cd.cpo_dtl_canc_flg,'N') = 'N'       --Cancel Flag
        AND    sts.ord_hdr_sts_cd  NOT IN ('90','99')   --Not Closed/Cancelled
        AND    sts.ord_line_sts_cd NOT IN ('90','99')   --Not Closed/Cancelled
        AND    NOT EXISTS( SELECT 1 FROM canon_e580_itt_lines_tbl
                           WHERE order_number = cd.cpo_ord_num
                           AND   line_number  = cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num)))
        ORDER BY 1;
        
     --This cursor to fetch ITT eligible sales order line details to insert into canon_e580_itt_lines_tbl
     CURSOR c_itt_line_dtls(   p_so_num     VARCHAR2
                             , p_shiptocd   VARCHAR2
                           )
     IS
        (SELECT  DISTINCT
                c.cpo_ord_num       order_number
              ,(SELECT cvt.val1 
                FROM  canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                WHERE ct.cd_name = 'CANON_E580_LINE_STATUS'
                AND   ct.cd_id = cvt.cd_id
                AND   cvt.val51 = DECODE(UPPER(sts.ord_line_sts_nm),'CLOSED',4,1)
                AND   (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
               )                   itt_line_status
              ,(cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num) line_number
			  ,(cd.ds_ord_posn_num||'.'||cd.ds_cpo_line_num || nvl2(cd.ds_cpo_line_sub_num, '.'||cd.ds_cpo_line_sub_num, null)) line_id
              , cd.mdse_cd  item
              , mdse.mdse_desc_short_txt   item_description
              , cd.ord_qty     ordered_quantity
              ,(SELECT TO_DATE(sos.schd_dely_dt,'YYYYMMDD') 
                FROM   shpg_ord sos, shpg_ord_dtl so
                WHERE sos.so_num       = so.so_num 
                AND so.trx_hdr_num  = c.cpo_ord_num 
                AND so.trx_line_num = cd.cpo_dtl_line_num
                AND so.trx_line_sub_num = cd.cpo_dtl_line_sub_num
                AND sos.glbl_cmpy_cd = g_glbl_cmpy_cd 
				AND sos.ezcancelflag = '0'
                AND so.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND so.ezcancelflag = '0'				
                AND    ROWNUM = 1
               )             delivery_scheduled_date
              ,CASE WHEN cd.set_mdse_cd IS NOT NULL THEN 0 
                    ELSE NVL((SELECT unit_prc_amt
                            FROM asl_dtl ad
                            WHERE ad.mdse_cd = cd.mdse_cd
                            AND ad.prim_sply_flg = 'Y'
							AND ad.ezcancelflag = '0'
                            AND SYSDATE BETWEEN NVL(TO_DATE (ad.eff_from_dt, 'RRRRMMDD'),SYSDATE-1) AND nvl(TO_DATE (ad.eff_thru_dt, 'RRRRMMDD'),SYSDATE+1)), 0)
               END  item_purchase_price
              ,'GOODS' itt_line_type
              ,CASE WHEN cd.set_mdse_cd IS NOT NULL THEN 0
               ELSE (SELECT plv.prc_list_equip_prc_amt 
                     FROM   prc_catg plc, prc_list_equip plv  
                     WHERE  plc.prc_catg_cd      = plv.prc_catg_cd
                     AND    plc.prc_list_dply_nm = (SELECT cvt.val2 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                                    WHERE ct.cd_name = 'CANON_E580_PRICELIST' AND   ct.cd_id = cvt.cd_id
                                                    AND   cvt.val1 = 'FINDER_FEE'
                                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1)))
                     AND plv.del_flg = 'N'
                     AND plv.prc_qlfy_tp_cd   = '01'   --Item Code
                     AND plv.prc_qlfy_val_txt = substr(cd.mdse_cd, 1, 8)
                     AND SYSDATE BETWEEN NVL(TO_DATE (plv.eff_from_dt, 'RRRRMMDD'),SYSDATE-1) AND nvl(TO_DATE (plv.eff_thru_dt, 'RRRRMMDD'),SYSDATE+1)
                     AND plc.glbl_cmpy_cd = g_glbl_cmpy_cd
					 AND plc.ezcancelflag = '0'					 
                     AND plv.glbl_cmpy_cd = g_glbl_cmpy_cd
					 AND plv.ezcancelflag = '0')
               END   finder_fee
              ,CASE WHEN cd.set_mdse_cd IS NOT NULL THEN 0 
               ELSE (SELECT plv.prc_list_equip_prc_amt 
                     FROM   prc_catg plc, prc_list_equip plv
                     WHERE  plc.prc_catg_cd      = plv.prc_catg_cd
                     AND    plc.prc_list_dply_nm = (SELECT cvt.val2 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                                    WHERE  ct.cd_name = 'CANON_E580_PRICELIST' AND ct.cd_id = cvt.cd_id
                                                    AND  cvt.val1 = 'INSTALL_CREDIT'
                                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                                   )
                     AND plv.del_flg = 'N' AND plv.prc_qlfy_tp_cd = '01'   --Item Code
                     AND plv.prc_qlfy_val_txt = substr(cd.mdse_cd, 1, 8)
                     AND SYSDATE BETWEEN NVL(TO_DATE (plv.eff_from_dt, 'RRRRMMDD'),SYSDATE-1) AND nvl(TO_DATE (plv.eff_thru_dt, 'RRRRMMDD'),SYSDATE+1)
                     AND plc.glbl_cmpy_cd = g_glbl_cmpy_cd 
					 AND plc.ezcancelflag = '0'
                     AND plv.glbl_cmpy_cd = g_glbl_cmpy_cd
					 AND plv.ezcancelflag = '0')
               END   install_comp
              ,sr.toc_cd  salesrep_cd
              ,sr.toc_nm  salesrep_nm
              /*,(SELECT zn.coa_br_zn_nm 
                FROM  coa_br br, coa_br_zn zn 
                WHERE br.coa_br_cd = sr.coa_br_cd AND br.coa_br_zn_cd = zn.coa_br_zn_cd
                AND   br.glbl_cmpy_cd = g_glbl_cmpy_cd AND zn.glbl_cmpy_cd = g_glbl_cmpy_cd)   sales_zone*/
              , canon_e580_itt_util_pkg.get_salesrep_attr(sr.toc_cd,'ZONE')  sales_zone
              ,(SELECT rg.coa_extn_nm 
				FROM coa_extn rg 
				WHERE rg.coa_extn_cd = sr.coa_extn_cd 
				AND rg.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND rg.ezcancelflag = '0') sales_region
              ,(SELECT br.coa_br_nm 
			    FROM coa_br br 
				WHERE br.coa_br_cd = sr.coa_br_cd 
				AND br.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND br.ezcancelflag = '0') sales_branch
              ,dcc.ship_to_cust_loc_cd   ship_to_customer_cd
              ,dcc.ship_to_loc_nm        ship_to_customer
              ,NULL party_id
			  ,cd.ord_line_src_cd line_source_cd
			  ,ols.ord_line_src_nm line_source
              ,cat.ds_ord_line_catg_cd     line_type_cd
              ,cat.ds_ord_line_catg_nm     line_type_nm
              ,sts.ord_line_sts_nm         line_status_code
              ,cd.cpo_dtl_canc_flg         cancelled_flag
              ,mdse.coa_mdse_tp_cd         merchandise
              ,sr.coa_prod_cd              product_code
              ,( SELECT m.t_mdl_nm
                 from   mdl_nm m
                 where  m.t_mdl_id       = dcc.mdl_id
                 AND    m.t_glbl_cmpy_cd = g_glbl_cmpy_cd
				 AND m.ezcancelflag = '0'
               )                           equip_model
              ,CASE sr.coa_prod_cd
               WHEN '99' THEN 'DEALER'
               ELSE 'CSA'
               END  product_sourced_by
              ,NULL industry_attribute  --TBC
              ,NULL ATTRIBUTE10         --TBD set_item_weighted_average for unit price in PO creation
              ,CASE sts.ord_line_sts_nm
               WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (cd.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
               ELSE NULL
               END  itt_line_closed_date
              ,CASE sts.ord_line_sts_nm 
               WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (cd.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE)
               ELSE SYSDATE
               END  itt_line_creation_date
              ,CASE sts.ord_line_sts_nm
               WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (cd.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
               ELSE SYSDATE
               END  itt_line_update_date
        FROM   cpo c
              ,cpo_dtl cd
              ,ds_ord_line_catg cat
              ,ds_ord_sts_v sts
              ,mdse mdse
              ,toc sr
              ,ds_cpo_config dcc
              ,shpg_pln sp
			  ,ord_line_src ols
        WHERE  1=1
        AND    c.cpo_ord_num           = p_so_num
        AND    dcc.ship_to_cust_loc_cd = p_shiptocd
        AND    c.cpo_ord_num = cd.cpo_ord_num
        AND    c.cpo_ord_num = sts.cpo_ord_num
        AND    c.cpo_ord_num =  sp.trx_hdr_num
        AND   cd.cpo_dtl_line_num     = sts.cpo_dtl_line_num
        AND   cd.cpo_dtl_line_sub_num = sts.cpo_dtl_line_sub_num
        AND   cd.cpo_dtl_line_num     = sp.trx_line_num
        AND   cd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
        AND   cd.ds_cpo_config_pk     = dcc.ds_cpo_config_pk
        AND   cd.ds_ord_line_catg_cd  = cat.ds_ord_line_catg_cd
        AND   cd.mdse_cd = mdse.mdse_cd   
		AND   (mdse.invty_ctrl_flg <> 'N' OR ( mdse.invty_ctrl_flg = 'N' AND mdse.coa_mdse_tp_cd = '11'))
        AND   cd.sls_rep_or_sls_team_toc_cd = sr.toc_cd
        --AND   sp.aval_po_qty > 0    --Sufficient qty for PO creation
        AND   sp.po_req_flg = 'Y'   --PO Required flag
        AND   sp.so_close_flg = 'N' 
        AND   sp.rte_sts_cd = 0     --Un-Routed
        AND   sp.shpg_sts_cd = 10   --Validated
        AND    c.ezcancelflag = '0'   --Not Cancelled
        AND   cd.ezcancelflag = '0'
        AND  cat.ezcancelflag = '0'
        AND  sts.ezcancelflag = '0'
        AND   sr.ezcancelflag = '0'
        AND mdse.ezcancelflag = '0'
        AND  dcc.ezcancelflag = '0'
        AND   sp.ezcancelflag = '0'        
        AND    c.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cat.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  sts.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sr.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mdse.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.ord_line_src_cd = ols.ord_line_src_cd
		AND  ols.ord_line_src_nm in (SELECT cvt.VAL2
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
									 WHERE     ct.cd_name = 'CANON_E580_LINE_TYPE'
										   AND ct.cd_id = cvt.cd_id
										   AND cvt.val1 <> 'LINE_TYPE_02'
										   AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
        AND    NVL(sts.ord_book_flg, 'N')   = 'Y'       --Booked Flag
        AND    NVL(sts.cpo_open_flg, 'N')   = 'Y'       --Open Flag
        AND    NVL(c.cpo_canc_flg,'N')      = 'N'       --Cancel Flag
        AND    NVL(cd.cpo_dtl_canc_flg,'N') = 'N'       --Cancel Flag
        AND    sts.ord_hdr_sts_cd  NOT IN ('90','99')   --Not Closed/Cancelled
        AND    sts.ord_line_sts_cd NOT IN ('90','99')   --Not Closed/Cancelled
        AND    NOT EXISTS( SELECT 1 FROM canon_e580_itt_lines_tbl
                           WHERE order_number = cd.cpo_ord_num
                           AND   line_number  = cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num)
		UNION ALL
		SELECT  DISTINCT
                c.cpo_ord_num       order_number
              ,(SELECT cvt.val1 
                FROM  canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                WHERE ct.cd_name = 'CANON_E580_LINE_STATUS'
                AND   ct.cd_id = cvt.cd_id
                AND   cvt.val51 = DECODE(UPPER(sts.ord_line_sts_nm),'CLOSED',4,1)
                AND   (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
               )                   itt_line_status
              ,(cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num) line_number
			  ,(cd.ds_ord_posn_num||'.'||cd.ds_cpo_line_num || nvl2(cd.ds_cpo_line_sub_num, '.'||cd.ds_cpo_line_sub_num, null)) line_id
              , cd.mdse_cd   item
              , mdse.mdse_desc_short_txt   item_description
              , cd.ord_qty     ordered_quantity
              ,(SELECT TO_DATE(sos.schd_dely_dt,'YYYYMMDD') 
                FROM   shpg_ord sos, shpg_ord_dtl so
                WHERE sos.so_num       = so.so_num 
                AND so.trx_hdr_num  = c.cpo_ord_num 
                AND so.trx_line_num = cd.cpo_dtl_line_num
                AND so.trx_line_sub_num = cd.cpo_dtl_line_sub_num
                AND sos.glbl_cmpy_cd = g_glbl_cmpy_cd 
				AND sos.ezcancelflag = '0'
                AND so.glbl_cmpy_cd = g_glbl_cmpy_cd 
				AND so.ezcancelflag = '0'
                AND    ROWNUM = 1
               )             delivery_scheduled_date
              /*,CASE WHEN cd.set_mdse_cd IS NOT NULL THEN 0 
                    ELSE cd.ent_deal_net_unit_prc_amt
               END  item_purchase_price*/
			   , 0 item_purchase_price
              ,'GOODS' itt_line_type
              ,CASE WHEN cd.set_mdse_cd IS NOT NULL THEN 0
               ELSE (SELECT plv.prc_list_equip_prc_amt 
                     FROM   prc_catg plc, prc_list_equip plv  
                     WHERE  plc.prc_catg_cd      = plv.prc_catg_cd
                     AND    plc.prc_list_dply_nm = (SELECT cvt.val2 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                                    WHERE ct.cd_name = 'CANON_E580_PRICELIST' AND   ct.cd_id = cvt.cd_id
                                                    AND   cvt.val1 = 'FINDER_FEE'
                                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1)))
                     AND plv.del_flg = 'N'
                     AND plv.prc_qlfy_tp_cd   = '01'   --Item Code
                     AND plv.prc_qlfy_val_txt = substr(cd.mdse_cd, 1, 8)
                     AND SYSDATE BETWEEN NVL(TO_DATE (plv.eff_from_dt, 'RRRRMMDD'),SYSDATE-1) AND nvl(TO_DATE (plv.eff_thru_dt, 'RRRRMMDD'),SYSDATE+1)
                     AND plc.glbl_cmpy_cd = g_glbl_cmpy_cd 
					 AND plc.ezcancelflag = '0'
                     AND plv.glbl_cmpy_cd = g_glbl_cmpy_cd
					 AND plv.ezcancelflag = '0')
               END   finder_fee
              ,CASE WHEN cd.set_mdse_cd IS NOT NULL THEN 0 
               ELSE (SELECT plv.prc_list_equip_prc_amt 
                     FROM   prc_catg plc, prc_list_equip plv
                     WHERE  plc.prc_catg_cd      = plv.prc_catg_cd
                     AND    plc.prc_list_dply_nm = (SELECT cvt.val2 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                                    WHERE  ct.cd_name = 'CANON_E580_PRICELIST' AND ct.cd_id = cvt.cd_id
                                                    AND  cvt.val1 = 'INSTALL_CREDIT'
                                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                                   )
                     AND plv.del_flg = 'N' AND plv.prc_qlfy_tp_cd = '01'   --Item Code
                     AND plv.prc_qlfy_val_txt = substr(cd.mdse_cd, 1, 8)
                     AND SYSDATE BETWEEN NVL(TO_DATE (plv.eff_from_dt, 'RRRRMMDD'),SYSDATE-1) AND nvl(TO_DATE (plv.eff_thru_dt, 'RRRRMMDD'),SYSDATE+1)
                     AND plc.glbl_cmpy_cd = g_glbl_cmpy_cd
					 AND plc.ezcancelflag = '0'					 
                     AND plv.glbl_cmpy_cd = g_glbl_cmpy_cd
					 AND plv.ezcancelflag = '0')
               END   install_comp
              ,sr.toc_cd  salesrep_cd
              ,sr.toc_nm  salesrep_nm
              /*,(SELECT zn.coa_br_zn_nm 
                FROM  coa_br br, coa_br_zn zn 
                WHERE br.coa_br_cd = sr.coa_br_cd AND br.coa_br_zn_cd = zn.coa_br_zn_cd
                AND   br.glbl_cmpy_cd = g_glbl_cmpy_cd AND zn.glbl_cmpy_cd = g_glbl_cmpy_cd)   sales_zone*/
              , canon_e580_itt_util_pkg.get_salesrep_attr(sr.toc_cd,'ZONE')  sales_zone
              ,(SELECT rg.coa_extn_nm 
			    FROM coa_extn rg 
				WHERE rg.coa_extn_cd = sr.coa_extn_cd 
				AND rg.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND rg.ezcancelflag = '0') sales_region
              ,(SELECT br.coa_br_nm 
			    FROM coa_br br 
				WHERE br.coa_br_cd = sr.coa_br_cd 
				AND br.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND br.ezcancelflag = '0') sales_branch
              ,dcc.ship_to_cust_loc_cd   ship_to_customer_cd
              ,dcc.ship_to_loc_nm        ship_to_customer
              ,NULL party_id
			  ,cd.ord_line_src_cd line_source_cd
			  ,ols.ord_line_src_nm line_source
              ,cat.ds_ord_line_catg_cd     line_type_cd
              ,cat.ds_ord_line_catg_nm     line_type_nm
              ,sts.ord_line_sts_nm         line_status_code
              ,cd.cpo_dtl_canc_flg         cancelled_flag
              ,mdse.coa_mdse_tp_cd         merchandise
              ,sr.coa_prod_cd              product_code
              ,( SELECT m.t_mdl_nm
                 from   mdl_nm m
                 where  m.t_mdl_id       = dcc.mdl_id
                 AND    m.t_glbl_cmpy_cd = g_glbl_cmpy_cd
				 AND m.ezcancelflag = '0'
               )                           equip_model
              ,CASE sr.coa_prod_cd
               WHEN '99' THEN 'DEALER'
               ELSE 'CSA'
               END  product_sourced_by
              ,NULL industry_attribute  --TBC
              ,NULL ATTRIBUTE10         --TBD set_item_weighted_average for unit price in PO creation
              ,CASE sts.ord_line_sts_nm
               WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (cd.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
               ELSE NULL
               END  itt_line_closed_date
              ,CASE sts.ord_line_sts_nm 
               WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (cd.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE)
               ELSE SYSDATE
               END  itt_line_creation_date
              ,CASE sts.ord_line_sts_nm
               WHEN 'Closed' THEN CAST ( TO_TIMESTAMP (cd.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE)
               ELSE SYSDATE
               END  itt_line_update_date
        FROM   cpo c
              ,cpo_dtl cd
              ,ds_ord_line_catg cat
              ,ds_ord_sts_v sts
              ,mdse mdse
              ,toc sr
              ,ds_cpo_config dcc
              ,shpg_pln sp
			  ,ord_line_src ols
        WHERE  1=1
        AND    c.cpo_ord_num           = p_so_num
        AND    dcc.ship_to_cust_loc_cd = p_shiptocd
        AND    c.cpo_ord_num = cd.cpo_ord_num
        AND    c.cpo_ord_num = sts.cpo_ord_num
        AND    c.cpo_ord_num =  sp.trx_hdr_num
        AND   cd.cpo_dtl_line_num     = sts.cpo_dtl_line_num
        AND   cd.cpo_dtl_line_sub_num = sts.cpo_dtl_line_sub_num
        AND   cd.cpo_dtl_line_num     = sp.trx_line_num
        AND   cd.cpo_dtl_line_sub_num = sp.trx_line_sub_num
        AND   cd.ds_cpo_config_pk     = dcc.ds_cpo_config_pk
        AND   cd.ds_ord_line_catg_cd  = cat.ds_ord_line_catg_cd
        AND   cd.mdse_cd = mdse.mdse_cd
		AND   (mdse.invty_ctrl_flg <> 'N' OR ( mdse.invty_ctrl_flg = 'N' AND mdse.coa_mdse_tp_cd = '11'))
        AND   cd.sls_rep_or_sls_team_toc_cd = sr.toc_cd
        AND   sp.so_close_flg = 'N' 
        AND    c.ezcancelflag = '0'   --Not Cancelled
        AND   cd.ezcancelflag = '0'
        AND  cat.ezcancelflag = '0'
        AND  sts.ezcancelflag = '0'
        AND   sr.ezcancelflag = '0'
        AND mdse.ezcancelflag = '0'
        AND  dcc.ezcancelflag = '0'
        AND   sp.ezcancelflag = '0'        
        AND    c.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  cat.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  sts.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sr.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mdse.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND  dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   cd.ord_line_src_cd = ols.ord_line_src_cd
		AND  ols.ord_line_src_nm in (SELECT cvt.VAL2
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
									 WHERE     ct.cd_name = 'CANON_E580_LINE_TYPE'
										   AND ct.cd_id = cvt.cd_id
										   AND cvt.val1 = 'LINE_TYPE_02'
										   AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
        AND    NVL(sts.ord_book_flg, 'N')   = 'Y'       --Booked Flag
        AND    NVL(sts.cpo_open_flg, 'N')   = 'Y'       --Open Flag
        AND    NVL(c.cpo_canc_flg,'N')      = 'N'       --Cancel Flag
        AND    NVL(cd.cpo_dtl_canc_flg,'N') = 'N'       --Cancel Flag
        AND    sts.ord_hdr_sts_cd  NOT IN ('90','99')   --Not Closed/Cancelled
        AND    sts.ord_line_sts_cd NOT IN ('90','99')   --Not Closed/Cancelled
        AND    NOT EXISTS( SELECT 1 FROM canon_e580_itt_lines_tbl
                           WHERE order_number = cd.cpo_ord_num
                           AND   line_number  = cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num))
        ORDER BY 1;

      --This cursor to fetch ITT eligible Sales Order (Service) line details to insert into canon_e580_service_pricing_tbl
      CURSOR c_itt_svc_prc_dtls( p_order_num       VARCHAR2,
                                 p_ship_to         VARCHAR2 )
      IS
      SELECT 
            DISTINCT 
            h.cpo_ord_num order_number
          , l.ship_to_cust_cd
          , mn.t_mdl_nm equip_model
		  --, dcc.ds_cpo_config_pk
          , COUNT (l.mdse_cd) quantity
      FROM  cpo h
          , cpo_dtl l
          , ds_cpo_config dcc
          , mdse dmi
          , ds_ord_sts_v sv
          , mdl_nm mn
          , ord_line_src os
      WHERE  1=1
         AND h.cpo_ord_num = l.cpo_ord_num
         AND h.cpo_ord_num = sv.cpo_ord_num
         AND l.cpo_dtl_line_num = sv.cpo_dtl_line_num
         AND l.cpo_dtl_line_sub_num = sv.cpo_dtl_line_sub_num
         AND l.ds_cpo_config_pk = dcc.ds_cpo_config_pk
         AND l.svc_config_mstr_pk = dcc.svc_config_mstr_pk
         AND l.ord_line_src_cd = os.ord_line_src_cd
         AND dcc.mdl_id IS NOT NULL
         AND dcc.mdl_id = mn.t_mdl_id
         AND l.mdse_cd = dmi.mdse_cd
         AND dmi.coa_mdse_tp_cd = '10'  --Machine
         AND sv.ord_hdr_sts_nm NOT IN ('Closed', 'Cancelled')
         AND sv.ord_line_sts_nm NOT IN ('Closed', 'Cancelled')
         AND NVL(sv.ord_book_flg, 'N') = 'Y'       --Booked Flag
         AND NVL(sv.cpo_open_flg, 'N') = 'Y'       --Open Flag
         AND NVL (h.cpo_canc_flg, 'N') = 'N'       --Cancelled Flag
         AND NVL (l.cpo_dtl_canc_flg, 'N') = 'N'   --Cancelled Flag
         AND h.ezcancelflag   = '0'  --Not Cancelled
         AND l.ezcancelflag   = '0'
         AND sv.ezcancelflag  = '0'
         AND dcc.ezcancelflag = '0'
         AND dmi.ezcancelflag = '0'
         AND os.ezcancelflag  = '0'
         AND h.glbl_cmpy_cd   = g_glbl_cmpy_cd
         AND l.glbl_cmpy_cd   = g_glbl_cmpy_cd
         AND sv.glbl_cmpy_cd  = g_glbl_cmpy_cd
         AND dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND dmi.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND os.glbl_cmpy_cd  = g_glbl_cmpy_cd
         AND os.ord_line_src_nm in (SELECT cvt.VAL2
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
									 WHERE     ct.cd_name = 'CANON_E580_LINE_TYPE'
										   AND ct.cd_id = cvt.cd_id
										   AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
         AND h.cpo_ord_num = p_order_num
         AND l.ship_to_cust_cd = p_ship_to
      GROUP BY h.cpo_ord_num, l.ship_to_cust_cd, mn.t_mdl_nm;
	  --, dcc.ds_cpo_config_pk;
	  
	  CURSOR c_svc_prc_details(p_order_number VARCHAR2,
							   --p_order_config_pk NUMBER,
							   p_equip_model VARCHAR2)
	  IS
		SELECT DISTINCT	pmp.prc_mtr_pkg_nm meter_type_pkg,
		  (SELECT prc_svc_contr_tp_nm
		  FROM s21_csa_apps.prc_svc_contr_tp psc
		  WHERE psc.prc_svc_contr_tp_cd = pls.prc_svc_contr_tp_cd
		  ) contract_type,
		  (SELECT prc_svc_pln_tp_nm
		  FROM s21_csa_apps.prc_svc_pln_tp psp
		  WHERE psp.prc_svc_pln_tp_cd = pls.prc_svc_pln_tp_cd
		  ) plan_type,
		  ((dcd.to_per_mth_num - dcd.from_per_mth_num)+1) term,
		  pls.base_amt base_amount,
		  DECODE(dc.base_bllg_cycle_cd, 'Q', 'Quarterly', 'M', 'Monthly', dc.base_bllg_cycle_cd) base_bill_cycle,
		  DECODE(dc.mtr_bllg_cycle_cd, 'Q', 'Quarterly', 'M', 'Monthly', dc.mtr_bllg_cycle_cd) overage_bill_cycle,
		  mt.mtr_lb_desc_txt meter_type,
		  --mt1.mtr_lb_desc_txt hard_counter,
		  (select mt1.mtr_lb_desc_txt
              from s21_csa_apps.mtr_lb mt1
              where mt1.mtr_lb_cd = pmpm.mtr_lb_cd) hard_counter,
		  pls.cpc_amt_rate overage_rate,
		  pls.max_copy_vol_cnt copy_inclusion,
		  pmpm.mtr_mult_rate multiplier,
		  dc.svc_line_biz_cd line_of_business
		FROM ds_cpo_config_v dcc,
			ds_contr dc,
			ds_contr_dtl dcd,
			prc_mtr_pkg pmp,
			prc_list_svc pls,
			prc_catg plc,
			prc_mtr_pkg_bllg_mtr pmpb,
			prc_mtr_pkg_phys_mtr pmpm,
			mtr_lb mt
			--mtr_lb mt1
		WHERE dcc.cpo_ord_num      = dc.cpo_ord_num
		AND dc.ds_contr_pk         = dcd.ds_contr_pk
		AND dcc.svc_config_mstr_pk = dcd.svc_config_mstr_pk
		AND dcc.mdl_id             = dcd.mdl_id
		AND pmp.prc_mtr_pkg_pk     = dcd.prc_mtr_pkg_pk
		AND pmp.prc_mtr_pkg_pk     = pmpb.prc_mtr_pkg_pk
		AND pmpb.mtr_lb_cd         = mt.mtr_lb_cd
		AND mt.actv_flg            = 'Y'
		/*AND mt1.actv_flg           = 'Y'
		AND mt1.mtr_lb_cd(+) = pmpm.mtr_lb_cd
		AND mt1.bw_mtr_flg(+) = mt.bw_mtr_flg
		AND mt1.color_mtr_flg(+) = mt.color_mtr_flg
		AND mt1.tot_mtr_flg(+) = mt.tot_mtr_flg
		AND mt1.corp_advtg_flg(+) = mt.corp_advtg_flg*/
		AND pls.bllg_mtr_lb_cd(+)     = pmpb.mtr_lb_cd
		AND (SYSDATE BETWEEN NVL(to_date(pls.eff_from_dt(+),'YYYYMMDD'),SYSDATE-1) AND NVL(to_date(pls.eff_thru_dt(+),'YYYYMMDD'),SYSDATE+1))
		AND (SYSDATE BETWEEN NVL(to_date(mt.eff_from_dt,'YYYYMMDD'),SYSDATE    -1) AND NVL(to_date(mt.eff_thru_dt,'YYYYMMDD'),SYSDATE+1))
		--AND (SYSDATE BETWEEN NVL(to_date(mt1.eff_from_dt(+),'YYYYMMDD'),SYSDATE    -1) AND NVL(to_date(mt1.eff_thru_dt(+),'YYYYMMDD'),SYSDATE+1))
		AND pls.prc_mtr_pkg_pk(+) = dcd.prc_mtr_pkg_pk
		AND pls.mdl_nm (+)        = dcc.t_mdl_nm
		AND pls.del_flg (+)       = 'N'
		AND pmpm.prc_mtr_pkg_bllg_mtr_pk(+) = pmpb.prc_mtr_pkg_bllg_mtr_pk
		AND dcc.cpo_ord_num       = p_order_number
		--AND dcc.ds_cpo_config_pk  = p_order_config_pk
		AND plc.prc_catg_cd       = pls.prc_catg_cd (+)
		AND plc.prc_catg_nm       =  (SELECT cvt.val2
									  FROM canon_s21_cd_tbl ct,
										   canon_s21_cd_val_tbl cvt
									  WHERE ct.cd_name = 'CANON_E580_PRICELIST'
									  AND ct.cd_id     = cvt.cd_id
									  AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
									  AND cvt.val1 = 'MAINTENANCE'
									  )
		AND dcc.t_mdl_nm = p_equip_model;
	  
	  CURSOR c_po_receipt_dtls
	  IS
	  SELECT cil.itt_number, cil.order_number, cil.line_number, pd.po_ord_num po_number, rd.rws_num, rd.rws_dtl_line_num, rd.mdse_cd, psn.ser_num
		FROM rws_hdr rh,
		rws_dtl rd,
		po_dtl pd,
		po_ser_num psn,
		canon_e580_itt_lines_tbl cil
		WHERE rh.rws_num = rd.rws_num
		AND cil.cusa_po_number IS NOT NULL
		AND cil.po_receipt_number IS NULL
		AND cil.order_number = pd.cpo_ord_num
		AND cil.line_number = (pd.cpo_dtl_line_num||'.'||pd.cpo_dtl_line_sub_num)
		AND pd.po_ord_num = rh.po_rcv_trx_hdr_num
		AND pd.po_ord_dtl_line_num = rd.po_rcv_trx_line_num
		AND psn.key_info_cd_04(+) = pd.po_ord_num
		AND psn.key_info_cd_05(+) = pd.po_ord_dtl_line_num
		AND psn.ezcancelflag(+) = '0'
		AND pd.ezcancelflag = '0'
		AND rh.ezcancelflag = '0'
		AND rd.ezcancelflag = '0'
		AND psn.glbl_cmpy_cd(+) = 'ADB'
		AND pd.glbl_cmpy_cd = 'ADB'
		AND rd.glbl_cmpy_cd = 'ADB'
		AND rh.glbl_cmpy_cd = 'ADB';

   BEGIN
      l_debug_msgs := '-------------------------------------------------------------------------';
      l_debug_msgs := l_debug_msgs||'~ Program Start Time :'||TO_CHAR(SYSDATE,'mm/dd/yyyy hh24:mi:ss');
      
      l_debug_step := 1;
      --Convert From/To date parameter values to Date format
      BEGIN
         SELECT DECODE (p_from_date, NULL, TO_CHAR(SYSDATE-10,'YYYYMMDD'), TO_CHAR(TO_DATE (p_from_date, 'YYYYMMDD'),'YYYYMMDD'))
               ,DECODE (p_to_date, NULL, TO_CHAR(SYSDATE,'YYYYMMDD'), TO_CHAR(TO_DATE (p_to_date, 'YYYYMMDD'),'YYYYMMDD'))
         INTO   l_from_date
               ,l_to_date
         FROM dual;
      EXCEPTION
         WHEN OTHERS THEN
            l_debug_msgs := l_debug_msgs||'~ Invalid Date format provided, please input date in YYYYMMDD format and resubmit';
      END;      
      l_debug_msgs := l_debug_msgs||'~ ****************Input Parameters*****************';
      l_debug_msgs := l_debug_msgs||'~ Order # : '||p_order_number||'; From Date : '||l_from_date||'; To Date : '||l_to_date;
      l_debug_msgs := l_debug_msgs||'~ ****************Input Parameters*****************';
      
      --Derive Mail Invoices To from value set
      --l_mail_invoices_to := '';
      
      l_debug_step := 2;
      OPEN c_elig_so(l_from_date, l_to_date);
      FETCH c_elig_so BULK COLLECT INTO l_elig_so_tbl;
      CLOSE c_elig_so;

      IF (l_elig_so_tbl.COUNT = 0) THEN
         l_debug_msgs := l_debug_msgs||'~ No eligible sales orders to load into ITT workbench ~';
         p_debug_msgs := l_debug_msgs||'-------------------------------------------------------------------------';
         --RETURN;
      END IF;

      l_debug_msgs := l_debug_msgs||'~ No. of Eligible sales orders to load into ITT workbench :'||l_elig_so_tbl.COUNT;
      l_debug_step := 3;
      FOR rec IN 1..l_elig_so_tbl.COUNT
      LOOP
         BEGIN
            l_debug_msgs := l_debug_msgs||'~ *************************************';
            l_debug_msgs := l_debug_msgs||'~ Sales Order Number :'||l_elig_so_tbl(rec).so_number||', Ship to Cust Cd :'||l_elig_so_tbl(rec).ship_to_cust_cd;
            l_itt_num_exist := NULL;
            l_itt_exists    := NULL;
            l_same_ship_to  := NULL;
            l_po_exists     := NULL;
            l_data_mode     := NULL;
            l_itt_num       := NULL;
            itt_exists ( p_so_number       => l_elig_so_tbl(rec).so_number,
                         p_ship_to_cust_cd => l_elig_so_tbl(rec).ship_to_cust_cd,
                         p_itt_number      => l_itt_num_exist,
                         p_itt_exists      => l_itt_exists,
                         p_same_ship_to    => l_same_ship_to,
                         p_po_exists       => l_po_exists,
                         p_data_mode       => l_data_mode
                       );
         
            l_debug_step := 4;
            IF (NVL(l_data_mode, 'I') = 'I' AND NVL(l_itt_exists, 'N') = 'Y')
            THEN
               BEGIN
                  SELECT MAX (TO_NUMBER (SUBSTR (cih.itt_number, -2, 2)))
                  INTO  l_itt_num
                  FROM  canon_e580_itt_headers_tbl cih
                  WHERE  cih.order_number = l_elig_so_tbl(rec).so_number
                  GROUP BY cih.header_id;

                  l_itt_num := l_itt_num + 1;
               EXCEPTION
                  WHEN OTHERS THEN
                     NULL;
               END;
            ELSE
               l_itt_num := 1;
            END IF;

            l_debug_step := 5;
            --Insert into ITT headers table
            IF ( NVL(l_data_mode, 'I') = 'I') 
            THEN
               l_itt_hdr_tbl.DELETE;
               FOR r2 IN c_itt_hdr_dtls( l_elig_so_tbl(rec).so_number
                                        ,l_elig_so_tbl(rec).ship_to_cust_cd
                                       )
               LOOP
                   l_itt_number := 'IT-'||l_elig_so_tbl(rec).so_number||'-'||LPAD (l_itt_num, 2, '0');
                   l_debug_step := 6;
                   l_itt_hdr_cnt := l_itt_hdr_tbl.COUNT + 1;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).itt_number              := l_itt_number;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).order_number            := r2.order_number;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).itt_status              := r2.itt_status;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).salesrep_cd             := r2.salesrep_cd;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).salesrep_nm             := r2.salesrep_nm;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).pricelist_cd            := r2.price_list_cd;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).sales_zone              := r2.sales_zone;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).sales_region            := r2.sales_region;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).sales_branch            := r2.sales_branch;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).cmap_yes_no             := r2.cmap_yes_no;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).booked_date             := r2.booked_date;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).order_status            := r2.order_status;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).ship_to_customer_cd     := r2.ship_to_customer_cd;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).ship_to_customer        := r2.ship_to_customer;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).order_creation_date     := r2.order_creation_date;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).cancelled_flag          := r2.cancelled_flag;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).itt_closed_date         := r2.itt_closed_date;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).creation_date           := r2.itt_creation_date;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).created_by              := l_user_id;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).last_update_date        := r2.itt_update_date;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).last_updated_by         := l_user_id;
                   l_itt_hdr_tbl(l_itt_hdr_cnt).scheduled_delivery_date := r2.scheduled_delivery_date;
                
                   --Derive customer contact person details
                   BEGIN
                      SELECT DISTINCT
                             ccp.ctac_psn_first_nm || ' ' || ccp.ctac_psn_last_nm contact,
                             ccp.ctac_psn_tel_num || NVL2(ccp.ctac_psn_extn_num, ' * ' || ccp.ctac_psn_extn_num, NULL),
                             ccp.ctac_psn_eml_addr,
                             ccp.ctac_psn_fax_num
                      INTO   l_itt_hdr_tbl(l_itt_hdr_cnt).customer_contact_name,
                             l_itt_hdr_tbl(l_itt_hdr_cnt).customer_contact_phone,
                             l_itt_hdr_tbl(l_itt_hdr_cnt).customer_contact_email,
                             l_itt_hdr_tbl(l_itt_hdr_cnt).customer_contact_fax
                      FROM  ds_cpo_ctac_psn ccp
                      WHERE ccp.cpo_ord_num = l_elig_so_tbl(rec).so_number
                      AND   ccp.ctac_psn_tp_cd = 'SHIPTO'
                      AND   ccp.ezcancelflag = '0'
                      AND   ccp.glbl_cmpy_cd = 'ADB'
                      AND   rownum = 1;
                   EXCEPTION
                      WHEN OTHERS THEN
                         l_debug_msgs := l_debug_msgs||'~ Unable to get customer contact details, Error :'||SQLERRM;
                   END;
               END LOOP;
               --Bulk insert ITT headers
               IF (l_itt_hdr_tbl.COUNT > 0) THEN
                  BEGIN
                     FORALL ins IN 1..l_itt_hdr_tbl.COUNT
                     INSERT INTO canon_e580_itt_headers_tbl values l_itt_hdr_tbl(ins);
                  EXCEPTION
                     WHEN OTHERS THEN
                        l_debug_msgs := l_debug_msgs||'~ Error while insert ITT header details into canon_e580_itt_headers_tbl, '||SQLERRM;
                  END;
               END IF;
            END IF;
         
            l_debug_step := 7;
            --Insert into canon_e580_itt_lines_tbl
            l_itt_line_tbl.DELETE;
            FOR r3 IN c_itt_line_dtls( l_elig_so_tbl(rec).so_number
                                      ,l_elig_so_tbl(rec).ship_to_cust_cd
                                     )
            LOOP
               l_debug_step := 8;
               l_itt_line_cnt := l_itt_line_tbl.COUNT + 1;
               IF (NVL(l_data_mode, 'I') = 'U') OR (NVL(l_data_mode, 'I') = 'A')
               THEN
                  l_itt_line_tbl(l_itt_line_cnt).itt_number := l_itt_num_exist;
				  
				  SELECT DECODE(l_data_mode, 'A', 'A', NULL), DECODE(l_data_mode, 'A', 'Y', NULL)
				  INTO l_append_mode, l_add_to_note
				  FROM DUAL;
				  
               ELSE
                  l_itt_line_tbl(l_itt_line_cnt).itt_number := l_itt_number;
               END IF;
               l_itt_line_tbl(l_itt_line_cnt).order_number            := r3.order_number;
               l_itt_line_tbl(l_itt_line_cnt).itt_status              := r3.itt_line_status;
               l_itt_line_tbl(l_itt_line_cnt).line_number             := r3.line_number;
			   l_itt_line_tbl(l_itt_line_cnt).line_id                 := r3.line_id;
               l_itt_line_tbl(l_itt_line_cnt).item                    := r3.item;
               l_itt_line_tbl(l_itt_line_cnt).item_description        := r3.item_description;
               l_itt_line_tbl(l_itt_line_cnt).ordered_quantity        := r3.ordered_quantity;
               l_itt_line_tbl(l_itt_line_cnt).install_credit          := r3.install_comp;
               l_itt_line_tbl(l_itt_line_cnt).itt_line_type           := r3.itt_line_type;
               l_itt_line_tbl(l_itt_line_cnt).finder_fee              := r3.finder_fee;
               l_itt_line_tbl(l_itt_line_cnt).install_comp            := r3.install_comp;
               l_itt_line_tbl(l_itt_line_cnt).salesrep_cd             := r3.salesrep_cd;
               l_itt_line_tbl(l_itt_line_cnt).salesrep_nm             := r3.salesrep_nm;
               l_itt_line_tbl(l_itt_line_cnt).sales_zone              := r3.sales_zone;
               l_itt_line_tbl(l_itt_line_cnt).sales_region            := r3.sales_region;
               l_itt_line_tbl(l_itt_line_cnt).sales_branch            := r3.sales_branch;
               l_itt_line_tbl(l_itt_line_cnt).merchandise             := r3.merchandise;
               l_itt_line_tbl(l_itt_line_cnt).product_code            := r3.product_code;
               l_itt_line_tbl(l_itt_line_cnt).equip_model             := r3.equip_model;
               l_itt_line_tbl(l_itt_line_cnt).ship_to_customer_cd     := r3.ship_to_customer_cd;
               l_itt_line_tbl(l_itt_line_cnt).ship_to_customer        := r3.ship_to_customer;
               l_itt_line_tbl(l_itt_line_cnt).transaction_type_cd     := r3.line_source_cd;
               l_itt_line_tbl(l_itt_line_cnt).transaction_type_nm     := r3.line_source;
               l_itt_line_tbl(l_itt_line_cnt).line_status_code        := r3.line_status_code;
               l_itt_line_tbl(l_itt_line_cnt).cancelled_flag          := r3.cancelled_flag;
               l_itt_line_tbl(l_itt_line_cnt).industry_attribute      := r3.industry_attribute;
               l_itt_line_tbl(l_itt_line_cnt).product_sourced_by      := r3.product_sourced_by;
               l_itt_line_tbl(l_itt_line_cnt).item_purchase_price     := r3.item_purchase_price;
               l_itt_line_tbl(l_itt_line_cnt).itt_line_closed_date    := r3.itt_line_closed_date;
               l_itt_line_tbl(l_itt_line_cnt).creation_date           := r3.itt_line_creation_date;
               l_itt_line_tbl(l_itt_line_cnt).created_by              := l_user_id;
               l_itt_line_tbl(l_itt_line_cnt).last_update_date        := r3.itt_line_update_date;
               l_itt_line_tbl(l_itt_line_cnt).last_updated_by         := l_user_id;
               l_itt_line_tbl(l_itt_line_cnt).delivery_scheduled_date := r3.delivery_scheduled_date;
			   l_itt_line_tbl(l_itt_line_cnt).attribute1			  := l_append_mode;
			   l_itt_line_tbl(l_itt_line_cnt).attribute2			  := l_add_to_note;
            
               --Derive customer contact person details
               BEGIN
                  SELECT DISTINCT
                         ccp.ctac_psn_first_nm || ' ' || ccp.ctac_psn_last_nm contact
                        ,ccp.ctac_psn_tel_num || NVL2(ccp.ctac_psn_extn_num, ' * ' || ccp.ctac_psn_extn_num, NULL)
                        ,ccp.ctac_psn_eml_addr
                        ,ccp.ctac_psn_fax_num
                  INTO   l_itt_line_tbl(l_itt_line_cnt).customer_contact_name
                        ,l_itt_line_tbl(l_itt_line_cnt).customer_contact_phone
                        ,l_itt_line_tbl(l_itt_line_cnt).customer_contact_email
                        ,l_itt_line_tbl(l_itt_line_cnt).customer_contact_fax
                  FROM   ds_cpo_ctac_psn ccp
                  WHERE  ccp.cpo_ord_num = l_elig_so_tbl(rec).so_number
                  AND   (ccp.cpo_dtl_line_num||'.'|| ccp.cpo_dtl_line_sub_num) = r3.line_number
                  AND   ccp.ctac_psn_tp_cd = 'SHIPTO'
                  AND   ccp.ezcancelflag = '0'
                  AND   ccp.glbl_cmpy_cd = 'ADB'
                  AND   rownum = 1;
               EXCEPTION
                  WHEN OTHERS THEN
                     l_debug_msgs := l_debug_msgs||'~ Unable to get customer contact details, '||SQLERRM;
               END;
               --Apply Hold on Sales Order Line : Not required since the hold gets created automatically at the order creation time in S21.
            END LOOP;         
            --Bulk insert ITT lines
            IF (l_itt_line_tbl.COUNT > 0) THEN
               BEGIN
                  FORALL ins IN 1..l_itt_line_tbl.COUNT
                  INSERT INTO canon_e580_itt_lines_tbl values l_itt_line_tbl(ins);
               EXCEPTION
                  WHEN OTHERS THEN
                     l_debug_msgs := l_debug_msgs||'~ Error while insert ITT line details into canon_e580_itt_lines_tbl, '||SQLERRM;
               END;
            END IF;
            l_debug_msgs := l_debug_msgs||'~ No. of ITT headers inserted :'||l_itt_hdr_tbl.COUNT||' for Sales Order Number :'||l_elig_so_tbl(rec).so_number;
            l_debug_msgs := l_debug_msgs||'~ No. of ITT lines inserted   :'||l_itt_line_tbl.COUNT||' for Sales Order Number :'||l_elig_so_tbl(rec).so_number;

            IF (NVL(l_data_mode, 'I') = 'U') OR (NVL(l_data_mode, 'I') = 'A') THEN
               DELETE FROM canon_e580_service_pricing_tbl csp
               WHERE itt_number = l_itt_num_exist;
               l_debug_msgs := l_debug_msgs||'~ No.of rows deleted :'||SQL%ROWCOUNT;
            END IF;

            --Insert logic into canon_e580_service_pricing_tbl
            l_debug_step := 8.1;
            l_itt_svc_tbl.DELETE;
            FOR r4 IN c_itt_svc_prc_dtls (l_elig_so_tbl(rec).so_number, l_elig_so_tbl(rec).ship_to_cust_cd)
            LOOP
               l_debug_step := 8.2;
			   
			   IF (NVL(l_data_mode, 'I') = 'U') OR (NVL(l_data_mode, 'I') = 'A')
               THEN
                  l_svc_itt_number := l_itt_num_exist;
               ELSE
                  l_svc_itt_number := l_itt_number;
               END IF;
			   
				
				FOR r_svc_prc_dtls IN c_svc_prc_details(r4.order_number, 
														--r4.ds_cpo_config_pk, 
														r4.equip_model)
				LOOP
				BEGIN
					INSERT INTO canon_e580_service_pricing_tbl
							(header_id,
							itt_number,
							equip_model,
							quantity,
							svc_meter_pkg,
							contract_type,
							plan_type,
							term,
							base_price,
							base_bill_cycle,
							overage_bill_cycle,
							meter_type,
							hard_meter_counter,
							overage_rate,
							copy_inclusion,
							multiplier,
							line_of_business,
							created_by,
							creation_date,
							last_updated_by,
							last_update_date)
					VALUES
							(r4.order_number,
							 l_svc_itt_number,
							 r4.equip_model,
							 r4.quantity,
							 r_svc_prc_dtls.meter_type_pkg,
							 r_svc_prc_dtls.contract_type,
							 r_svc_prc_dtls.plan_type,
							 r_svc_prc_dtls.term,
							 r_svc_prc_dtls.base_amount,
							 r_svc_prc_dtls.base_bill_cycle,
							 r_svc_prc_dtls.overage_bill_cycle,
							 r_svc_prc_dtls.meter_type,
							 r_svc_prc_dtls.hard_counter,
							 r_svc_prc_dtls.overage_rate,
							 r_svc_prc_dtls.copy_inclusion,
							 r_svc_prc_dtls.multiplier,
							 r_svc_prc_dtls.line_of_business,
							 l_user_id,
							 SYSDATE,
							 l_user_id,
							 SYSDATE
							 );
			 EXCEPTION
                  WHEN OTHERS THEN
                     l_debug_msgs := l_debug_msgs||'~ Error while insert ITT service pricing details into canon_e580_service_pricing_tbl, '||SQLERRM;
             END;		
               
			END LOOP;
			
			COMMIT;
			
        END LOOP;
		
         EXCEPTION
           WHEN OTHERS THEN
              l_debug_msgs  := l_debug_msgs||'~ Error at debug step :'||l_debug_step||' while extract/insert into ITT custom tables, Error Message : '||SQLCODE||'-'||SQLERRM;
         END;
      END LOOP;
      l_debug_msgs := l_debug_msgs||'~ *************************************';

      COMMIT;

      l_debug_step := 9;
      -->Logic to set the ITT line as Cancelled, when the CPO line is cancelled
      MERGE INTO canon_e580_itt_lines_tbl cil
      USING cpo_dtl l
      ON (     cil.order_number = l.cpo_ord_num
           AND cil.line_number = l.cpo_dtl_line_num||'.'||l.cpo_dtl_line_sub_num
           AND NVL(l.cpo_dtl_canc_flg,'N') = 'Y'
         )
      WHEN MATCHED THEN
      UPDATE SET    
             cil.cancelled_flag = NVL(l.cpo_dtl_canc_flg,'N')
            ,cil.line_status_code = (SELECT ord_line_sts_nm FROM ord_line_sts 
                                     WHERE ord_line_sts_cd = l.ord_line_sts_cd AND glbl_cmpy_cd = l.glbl_cmpy_cd)
            ,cil.itt_status = ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                WHERE ct.cd_name = 'CANON_E580_LINE_STATUS' AND   ct.cd_id = cvt.cd_id
                                AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                AND cvt.val51 = 12  --Cancelled
                              )
      WHERE (cil.cancelled_flag = 'N' AND cil.cancelled_flag <> NVL(l.cpo_dtl_canc_flg,'N'));

      l_debug_msgs := l_debug_msgs||'~ No.of updated ITT lines rows :'||SQL%ROWCOUNT||', to sync up the cancellation flag with S21';
      
      l_debug_step := 10;
      -->Logic to update the ITT header as Cancelled, when the corresponding order is already cancelled in S21.
         MERGE INTO canon_e580_itt_headers_tbl cih
         USING cpo h
         ON (     cih.order_number = h.cpo_ord_num
              AND NVL(h.cpo_canc_flg,'N') = 'Y'
            )
         WHEN MATCHED THEN
         UPDATE SET    
                cih.cancelled_flag = NVL(h.cpo_canc_flg,'N')
               ,cih.itt_status = 'Cancelled'
         WHERE (cih.cancelled_flag = 'N' AND cih.cancelled_flag <> NVL(h.cpo_canc_flg,'N'));
         
         l_debug_msgs := l_debug_msgs||'~ No.of updated ITT header rows :'||SQL%ROWCOUNT||', to sync up the cancellation flag with S21';
      
      l_debug_step := 11;
      -->Logic to update the PO details created against ITT lines.
         UPDATE  canon_e580_itt_lines_tbl cil
         SET    (cil.cusa_po_number, cil.cusa_po_line_number, cil.po_date) = 
                       (SELECT pd.po_ord_num,
                               pd.po_ord_dtl_line_num, 
                               TO_DATE(SUBSTR(p.ezintime,1,8),'YYYYMMDD')
                        FROM   po p, po_dtl pd
                        WHERE  p.po_ord_num = pd.po_ord_num
                        AND    p.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND   pd.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND   pd.cpo_ord_num = cil.order_number
                        AND  (pd.cpo_dtl_line_num||'.'||pd.cpo_dtl_line_sub_num) = cil.line_number
                        AND   pd.trx_ref_num = cil.itt_number
                       )
         WHERE  cil.cusa_po_number IS NULL
         AND    TRUNC(cil.last_update_date) >= TRUNC(SYSDATE);
		 
		 l_debug_step := 111;
		 
		FOR rec_po_receipt_dtls IN c_po_receipt_dtls
		LOOP
			
			UPDATE canon_e580_itt_lines_tbl cil
			SET po_receipt_number = rec_po_receipt_dtls.rws_num,
            po_receipt_line_number = rec_po_receipt_dtls.rws_dtl_line_num,
            serial_number = rec_po_receipt_dtls.ser_num
			WHERE 1 = 1
			AND cil.itt_number = rec_po_receipt_dtls.itt_number
            AND cil.line_number = rec_po_receipt_dtls.line_number
            AND cil.cusa_po_number = rec_po_receipt_dtls.po_number;
			
			COMMIT;
		
		END LOOP;

         l_debug_msgs := l_debug_msgs||'~ No.of updated ITT header rows :'||SQL%ROWCOUNT||', to update the PO details';

      l_debug_step := 12;
      ----Logic Pending : This might not be required since every S21 order creates with Hold applied.
      
      l_debug_step := 13;
         -->Logic to update the ITT line as Closed, when the corresponding order line is already closed in S21.
         UPDATE canon_e580_itt_lines_tbl cil
         SET    cil.line_status_code = 'Closed'
               ,cil.itt_status = 'Closed'
         WHERE  EXISTS (SELECT 1 FROM cpo_dtl o, ord_line_sts s
                        WHERE o.ord_line_sts_cd = s.ord_line_sts_cd
                        AND   s.ord_line_sts_nm = 'Closed'
                        AND   o.cpo_ord_num = cil.order_number
                        AND   (o.cpo_dtl_line_num||'.'||o.cpo_dtl_line_sub_num) = cil.line_number
                        AND   s.ord_line_sts_nm <> cil.line_status_code
                       );
         l_debug_msgs := l_debug_msgs||'~ No.of updated ITT lines rows :'||SQL%ROWCOUNT||', to sync up the closed flag with S21';
      
      l_debug_step := 14;
      -->Logic to update the ITT header as Closed, when the corresponding order is already closed in S21.
         UPDATE canon_e580_itt_headers_tbl cih
         SET    cih.order_status = 'Closed'
               ,cih.itt_status   = ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                     WHERE ct.cd_name = 'CANON_E580_HDR_STATUS' AND   ct.cd_id = cvt.cd_id
                                     AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                     AND cvt.val51 = 3) -- CLOSED
         WHERE  EXISTS ( SELECT 1 FROM cpo o, ord_hdr_sts s
                         WHERE o.ord_hdr_sts_cd = s.ord_hdr_sts_cd
                         AND   s.ord_hdr_sts_nm = 'Closed'
                         AND   o.cpo_ord_num = cih.order_number
                         AND   s.ord_hdr_sts_nm <> cih.order_status
                       );
         l_debug_msgs := l_debug_msgs||'~ No.of updated ITT lines rows :'||SQL%ROWCOUNT||', to sync up the closed flags with S21';
      
      l_debug_step := 15;
      -->Logic to update the ITT header as Closed, when all the child ITT lines are closed within ITT workbench.
         UPDATE canon_e580_itt_headers_tbl cih
         SET    cih.itt_status =  ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                    WHERE ct.cd_name = 'CANON_E580_HDR_STATUS' AND   ct.cd_id = cvt.cd_id
                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                    AND cvt.val51 = 3) -- CLOSED
         WHERE  cih.itt_status <> ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                    WHERE ct.cd_name = 'CANON_E580_HDR_STATUS' AND   ct.cd_id = cvt.cd_id
                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                    AND cvt.val51 = 3) -- CLOSED
         AND    0 < (SELECT COUNT(1) FROM canon_e580_itt_lines_tbl cil
                     WHERE cil.itt_number = cih.itt_number
                     AND   cil.order_number = cil.order_number)
         AND    NOT EXISTS( SELECT  1
                            FROM    canon_e580_itt_lines_tbl cil
                            WHERE   cil.itt_number = cih.itt_number
                            AND     cil.itt_status NOT IN ('Closed','Cancelled')  --Pending derive the closed/cancelled statuses from code table.
                          );
         l_debug_msgs := l_debug_msgs||'~ No.of updated ITT header rows :'||SQL%ROWCOUNT||', to sync up the closed flag';
      
      l_debug_step := 16;
      -->Logic to update the ITT header as Cancelled, when all the child ITT lines are cancelled within ITT workbench.
         SELECT DISTINCT 
                cih.itt_number, cih.order_number
         BULK COLLECT INTO l_itt_tbl
         FROM   canon_e580_itt_headers_tbl cih,
                canon_e580_itt_lines_tbl cil
         WHERE 1=1
         AND cil.itt_number = cih.itt_number
         AND cil.order_number = cih.order_number
         AND NVL(UPPER(cih.cancelled_flag), 'N') <> 'Y'
         AND NOT EXISTS (SELECT cil1.itt_number, cil1.line_id, cil1.cancelled_flag
                         FROM  canon_e580_itt_lines_tbl cil1
                         WHERE 1 = 1
                         AND cil1.itt_number = cil.itt_number 
                         AND NVL(UPPER(cil1.cancelled_flag), 'N') = 'N')
         ORDER BY 1;

         IF (l_itt_tbl.COUNT > 0) THEN
            FOR rec IN l_itt_tbl.FIRST..l_itt_tbl.LAST
            LOOP
               UPDATE canon_e580_itt_headers_tbl cih
               SET    cih.itt_status = 'CANCELLED'
                     ,cih.cancelled_flag = 'Y'
               WHERE  cih.itt_number = l_itt_tbl(rec).itt_number
               AND    cih.order_number = l_itt_tbl(rec).order_number;
            END LOOP;
         END IF;
         
         l_debug_msgs := l_debug_msgs||'~ No.of updated ITT header rows :'||l_itt_tbl.COUNT||', to sync up the cancelled flag';

      l_debug_step := 17;
	  
	  -->Logic to update the ITT header as 'Line Added, when new lines have been added to the ITT order after PO creation.
	  UPDATE canon_e580_itt_headers_tbl cih
	  SET  cih.itt_status = ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
								WHERE ct.cd_name = 'CANON_E580_HDR_STATUS' AND   ct.cd_id = cvt.cd_id
								AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
								AND cvt.val51 = 4)
	  WHERE  cih.itt_status <> ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                    WHERE ct.cd_name = 'CANON_E580_HDR_STATUS' AND   ct.cd_id = cvt.cd_id
                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                    AND cvt.val51 = 4)
	   AND EXISTS (SELECT 1 
					FROM canon_e580_itt_lines_tbl cil
					WHERE cih.itt_number = cil.itt_number
					AND cih.order_number = cil.order_number
					AND NVL(cil.attribute1, 'N') = 'A'
					AND NVL(UPPER(cil.cancelled_flag), 'N') = 'N')
		AND NVL(UPPER(cih.cancelled_flag), 'N') <> 'Y';
		
		 l_debug_msgs := l_debug_msgs||'~ No.of updated ITT headers updated to status (line Added) :'||SQL%ROWCOUNT;
		
  
	  l_debug_step := 18;
	  
	  
         BEGIN
            UPDATE canon_e580_itt_lines_tbl cil
            SET cil.line_status_code = 'CANCELLED'
            WHERE cil.cancelled_flag = 'Y'
            AND cil.line_status_code <> 'CANCELLED';

            UPDATE canon_e580_itt_lines_tbl cil
            SET cil.itt_status = 'Cancelled'  --Derive from Value Set.
            WHERE cil.cancelled_flag = 'Y'
            AND cil.itt_status <> (SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                   WHERE ct.cd_name = 'CANON_E580_LINE_STATUS' AND   ct.cd_id = cvt.cd_id
                                   AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                   AND cvt.val51 = 12);  --Cancelled

            UPDATE canon_e580_itt_lines_tbl cil
            SET cil.line_status_code = 'CLOSED'
            WHERE cil.itt_status = (SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                    WHERE ct.cd_name = 'CANON_E580_LINE_STATUS' AND   ct.cd_id = cvt.cd_id
                                    AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                                    AND cvt.val51 = 11);  --Closed

            UPDATE canon_e580_itt_headers_tbl cih
            SET  cih.itt_status = 'CANCELLED'
            WHERE cih.cancelled_flag = 'Y'
            AND itt_status <> 'CANCELLED';
         EXCEPTION
            WHEN OTHERS THEN
               l_debug_msgs := l_debug_msgs||'~ Error occurred while updating ITT Lines cancelled and Closed flag '||CHR(10)|| SQLCODE ||' - '|| SQLERRM;
         END;
       
	l_debug_step := 19;
	
         --TBC : Purge older error records created 30 days before
         DELETE FROM canon_e580_itt_errors_tbl
         WHERE creation_date < SYSDATE-30
         AND package_name NOT IN ('CANON_E580_AUTOCREATE_PO_PKG');
         
         DELETE FROM canon_e580_itt_errors_tbl err
         WHERE err.creation_date < SYSDATE-30
         AND   err.package_name = 'CANON_E580_AUTOCREATE_PO_PKG'
         AND EXISTS(SELECT 1 FROM canon_e580_itt_lines_tbl 
                    WHERE itt_number = err.itt_number
                    AND   line_number = err.line_number
                    AND   cusa_po_number IS NOT NULL
                   );

      COMMIT;
	  
	  l_debug_step := 20;
	  
		SELECT DISTINCT  cih.itt_number, cih.order_number
         BULK COLLECT INTO l_itt_tbl_append
         FROM   canon_e580_itt_headers_tbl cih,
                canon_e580_itt_lines_tbl cil
         WHERE 1=1
         AND cil.itt_number = cih.itt_number
         AND cil.order_number = cih.order_number
         AND NVL(UPPER(cih.cancelled_flag), 'N') <> 'Y'
         AND EXISTS (SELECT 1
                         FROM  canon_e580_itt_lines_tbl cil1
                         WHERE 1 = 1
                         AND cil1.itt_number = cil.itt_number 
                         AND NVL(UPPER(cil1.cancelled_flag), 'N') = 'N'
						 AND NVL(cil1.attribute1, 'N') = 'A'
						 AND NVL(cil1.attribute2, 'N') = 'Y'
						 )
         ORDER BY 1;

		 l_debug_step := 21;
		 
         IF (l_itt_tbl_append.COUNT > 0) THEN
            FOR rec IN l_itt_tbl_append.FIRST..l_itt_tbl_append.LAST
            LOOP
			
              add_append_lines_note(l_itt_tbl_append(rec).itt_number, l_itt_tbl_append(rec).order_number);

            END LOOP;
         END IF;  
		 
	  l_debug_step := 22;
	  
      l_debug_msgs := l_debug_msgs||'~ Program Complete Time :'||TO_CHAR(SYSDATE,'mm/dd/yyyy hh24:mi:ss')||'~';
      l_debug_msgs := l_debug_msgs||'-------------------------------------------------------------------------';
      p_debug_msgs := l_debug_msgs;
	  
   EXCEPTION
      WHEN OTHERS THEN
         ROLLBACK;
         l_error_msg  := 'This program errored out at debug step :'||l_debug_step||', Error Message : '||SQLCODE||'-'||SQLERRM;
         l_debug_msgs := l_debug_msgs||'~ '||l_error_msg||'~';
         l_debug_msgs := l_debug_msgs||'-------------------------------------------------------------------------';
         p_debug_msgs := l_debug_msgs;
         
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_PROCESS_PKG';
         l_itt_err_tbl(1).procedure_name   := 'SALES_ORDERS_EXTRACT';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_user_id;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_user_id;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END sales_orders_extract;
   
   ---------------------------------------------------------------------------------------------------------------------------
   --This procedure gets called from ITT workbench --> Dealer Install Confirm --> Confirm button
   --Purpose to update the hold release flag in ITT custom table(if success) or Capture errors in ITT error table (if failure)
   ---------------------------------------------------------------------------------------------------------------------------
   PROCEDURE dealer_install_confirm( p_itt_number   IN VARCHAR2,
                                     p_so_number    IN VARCHAR2,
                                     p_line_number  IN VARCHAR2,
                                     p_username     IN VARCHAR2,
                                     p_err_msg      IN VARCHAR2,
									 x_ret_flag     OUT VARCHAR2,
									 x_ret_message  OUT VARCHAR2
                                   )
   IS
   BEGIN
      IF (p_err_msg IS NOT NULL)
      THEN
         l_itt_err_tbl.DELETE;
         l_error_msg  := 'Hold Release API failed, Error Message :'||p_err_msg;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_PROCESS_PKG';
         l_itt_err_tbl(1).procedure_name   := 'DEALER_INSTALL_CONFIRM';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).line_number      := p_line_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := p_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);         
      ELSE
         UPDATE canon_e580_itt_lines_tbl cil
         SET    cil.line_on_hold = 'N'
               ,cil.last_update_date = SYSDATE
               ,cil.last_updated_by = p_username
         WHERE  cil.itt_number   = p_itt_number
         AND    cil.order_number = p_so_number
         AND    cil.line_number  = NVL(p_line_number, cil.line_number)
         AND    NVL(cil.cancelled_flag,'N') = 'N'
         AND    cil.line_on_hold = 'Y'
         AND    EXISTS (SELECT 1 FROM hld
                        WHERE cpo_ord_num = cil.order_number 
                        AND  cpo_dtl_line_num||'.'||cpo_dtl_line_sub_num = cil.line_number
                        AND  NVL(rel_flg ,'N') = 'Y'
                        AND  glbl_cmpy_cd = g_glbl_cmpy_cd);
      END IF;

      COMMIT;
	  x_ret_flag       := 'S';
      x_ret_message    := '';
   EXCEPTION
      WHEN OTHERS THEN
		 x_ret_flag    := 'E';
         x_ret_message := 'Error updating line hold flag: '||SQLERRM;
         ROLLBACK;
   END;
   
   PROCEDURE derive_po_cancel_lines (  p_ord_num        IN  VARCHAR2
									 , x_create_po_tbl  OUT CANON_E580_CREATE_PO_TBL_TYP
									 , x_ret_flag       OUT VARCHAR2
									 , x_ret_message    OUT VARCHAR2
									)
   IS         
      CURSOR po_cancel_hdr_dtls(p_order_num VARCHAR2)
      IS
	   SELECT DISTINCT pd.po_ord_num po_number
	   FROM canon_e580_itt_lines_tbl cil, 
		    po_dtl pd, 
			cpo_dtl cd
	  WHERE 1 = 1
	  AND cil.order_number = NVL(p_order_num, cil.order_number)
	  AND cil.cusa_po_number = pd.po_ord_num
	  AND cil.cusa_po_line_number = pd.po_ord_dtl_line_num
	  AND cil.item = pd.mdse_cd
	  AND pd.po_line_sts_cd NOT IN ('99', '90')
	  AND cil.order_number = cd.cpo_ord_num
	  AND cil.line_number = (cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num)
	  AND cil.cancelled_flag = 'Y'
	  AND cd.ord_line_sts_cd = '99';
		   
      CURSOR po_cancel_lines_cur (p_po_number IN VARCHAR2)
      IS
      SELECT pd.po_ord_num po_number,  pd.po_ord_dtl_line_num po_line_number, pd.mdse_cd
	   FROM canon_e580_itt_lines_tbl cil, 
		    po_dtl pd, 
			cpo_dtl cd
	  WHERE 1 = 1
	  AND pd.po_ord_num = p_po_number
	  AND cil.cusa_po_number = pd.po_ord_num
	  AND cil.cusa_po_line_number = pd.po_ord_dtl_line_num
	  AND cil.item = pd.mdse_cd
	  AND pd.po_line_sts_cd NOT IN ('99', '90')
	  AND cil.order_number = cd.cpo_ord_num
	  AND cil.line_number = (cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num)
	  AND cil.cancelled_flag = 'Y'
	  AND cd.ord_line_sts_cd = '99';
	  
	  TYPE po_hdr_typ IS TABLE OF po_cancel_hdr_dtls%ROWTYPE
      INDEX BY BINARY_INTEGER;
      
      TYPE po_line_typ IS TABLE OF po_cancel_lines_cur%ROWTYPE
      INDEX BY BINARY_INTEGER;
      
      l_ord_num        VARCHAR2(80) := p_ord_num;
	  l_po_hdr_tbl     po_hdr_typ;
      l_po_line_tbl    po_line_typ;
      h_cnt            INTEGER;
      l_cnt            INTEGER;
      l_create_po_tbl  CANON_E580_CREATE_PO_TBL_TYP := CANON_E580_CREATE_PO_TBL_TYP();
      l_poh_msg_tbl    CANON_E580_POH_MSG_TBL_TYP := CANON_E580_POH_MSG_TBL_TYP();
      l_ser_info_tbl   CANON_E580_POS_TBL_TYP := CANON_E580_POS_TBL_TYP();
      l_act_info_tbl   CANON_E580_POA_TBL_TYP := CANON_E580_POA_TBL_TYP();
      l_pol_info_tbl   CANON_E580_POL_TBL_TYP := CANON_E580_POL_TBL_TYP();
      l_act_info_rec   CANON_E580_POA_TYP_REC;
      l_line_num       VARCHAR2(10);
      l_date           VARCHAR2(10) := TO_CHAR(SYSDATE,'YYYYMMDD');
      l_datetime       VARCHAR2(10) := TO_CHAR(SYSDATE,'YYYYMMDD');
      l_step_no        NUMBER := 0;
	  l_disp_po_dtl_line_num VARCHAR2(50);
	  
   BEGIN
      l_step_no := 1;
    
	  OPEN  po_cancel_hdr_dtls( p_ord_num );
      FETCH po_cancel_hdr_dtls BULK COLLECT INTO l_po_hdr_tbl;
      CLOSE po_cancel_hdr_dtls;
      IF (l_po_hdr_tbl.COUNT = 0)
      THEN
         x_ret_flag    := 'E';
         x_ret_message := 'No eligible PO lines to Cancel';
         RETURN;
      END IF;
	
	FOR rec IN 1..l_po_hdr_tbl.COUNT
      LOOP
	  
      OPEN  po_cancel_lines_cur( l_po_hdr_tbl(rec).po_number );
      FETCH po_cancel_lines_cur BULK COLLECT INTO l_po_line_tbl;
      CLOSE po_cancel_lines_cur;
	  
      
      l_step_no := 2;
      
         IF (l_po_line_tbl.COUNT > 0) 
         THEN

			l_create_po_tbl.EXTEND;
            h_cnt := l_create_po_tbl.COUNT;
            l_create_po_tbl(h_cnt) := CANON_E580_CREATE_PO_TYP_REC( 
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL
                                                              );
            --l_create_po_tbl(h_cnt).GLBL_CMPY_CD      := 'ADB'; 
			l_create_po_tbl(h_cnt).DML_MODE          := '3';                                    --Mode    1-Create, 2-Update, 5-PO Merge.
            l_create_po_tbl(h_cnt).EVENT             := '2';                                    --Event   1-Save, 2-Submit
            l_create_po_tbl(h_cnt).PROC_DT           := TO_CHAR(SYSDATE,'YYYYMMDD');            --Process Date
            l_create_po_tbl(h_cnt).XX_RQST_TS        := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');   --Request timestamp
			l_create_po_tbl(h_cnt).PO_ORD_NUM        := l_po_hdr_tbl(rec).po_number;
			--l_create_po_tbl(h_cnt).GLBL_CMPY_CD        := 'ADB';
            --l_create_po_tbl(h_cnt).PO_SUBMT_PSN_CD   := p_user;                                 --Buyer
            --l_create_po_tbl(h_cnt).PO_SUBMT_TS       := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');
           
            l_pol_info_tbl.DELETE;
            l_act_info_tbl.DELETE;
            FOR rec1 IN 1..l_po_line_tbl.COUNT
            LOOP            
               
			   l_step_no := 3;
              
               l_pol_info_tbl.EXTEND;
               l_cnt := l_pol_info_tbl.COUNT;
               l_pol_info_tbl(l_cnt) := CANON_E580_POL_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

               l_line_num := l_po_line_tbl(rec1).po_line_number;
			   l_pol_info_tbl(l_cnt).PO_ORD_DTL_LINE_NUM       := l_line_num;
			   l_pol_info_tbl(l_cnt).MDSE_CD                   := l_po_line_tbl(rec1).mdse_cd;
			   
            END LOOP;
            l_create_po_tbl(h_cnt).PO_LINE_INFO_TBL := l_pol_info_tbl;
            l_create_po_tbl(h_cnt).PO_ACCT_INFO_TBL := l_act_info_tbl;
           END IF;
		   
      END LOOP;
	  
      l_step_no := 4;

      x_create_po_tbl  := l_create_po_tbl;
      x_ret_flag       := 'S';
      x_ret_message    := '';
   EXCEPTION
      WHEN OTHERS THEN
         x_ret_flag    := 'E';
         x_ret_message := l_step_no||', Please retry this action, Proc derive_po_cancel_lines terminated due to Error: '||SUBSTR(SQLERRM,1,100);
   END derive_po_cancel_lines;   
   
   /*This Procedure is called when new lines have been added to the Order after the initial sync of the ITT order to the ITT WB.
	 The purpose of this procedure is to add a note to the ITT oreder mentioning the details about new lines added  */
	 
   PROCEDURE add_append_lines_note (p_itt_number   IN  VARCHAR2,
									p_order_number IN  VARCHAR2)
   IS
   
   CURSOR c_derive_append_lines(p_itt_number1 VARCHAR2, p_order_number1 VARCHAR2)
   IS
   SELECT dcc.ds_ord_posn_num config_number,
		  dcc.mdl_desc_txt model,
		  cil.line_id line_number,
		  cil.item item_number,
		  cil.item_description item_desc,
		  cd.ds_cpo_dtl_crat_usr_id created_by,
		  CAST( TO_TIMESTAMP(cd.ds_cpo_dtl_crat_ts, 'RRRRMMDDHH24MISSFF3') AS DATE) creation_date 		  
   FROM canon_e580_itt_lines_tbl cil, cpo_dtl cd, ds_cpo_config dcc
   WHERE cil.itt_number = p_itt_number1
     AND cil.order_number = p_order_number1
     AND cil.order_number = cd.cpo_ord_num
	 AND cil.line_number = (cd.cpo_dtl_line_num||'.'||cd.cpo_dtl_line_sub_num)
	 AND cd.ds_cpo_config_pk = dcc.ds_cpo_config_pk
	 AND dcc.glbl_cmpy_cd = 'ADB'
	 AND dcc.ezcancelflag = '0'
	 AND cd.glbl_cmpy_cd = 'ADB'
	 AND cd.ezcancelflag = '0'
	 AND NVL(UPPER(cil.cancelled_flag), 'N') = 'N'
	 AND NVL(cil.attribute1, 'N') = 'A'
	 AND NVL(cil.attribute2, 'N') = 'Y';
   
   l_note VARCHAR2(4000);
   l_seq_number NUMBER;
   
   BEGIN
   l_note := NULL;
   l_note := 'The below New lines were added to the ITT# '|| p_itt_number;
   l_note := l_note || CHR(10)||'--------------------------------------------------------------------';
   
   FOR rec IN c_derive_append_lines(p_itt_number, p_order_number)
   LOOP
	l_note := l_note ||CHR(10)|| 'Config No: '||rec.config_number||' ('||rec.model||')'||', '||'Line No: '||rec.line_number||', '||
					 'Item No: '||rec.item_number||', '||'Item Desc: '||rec.item_desc||', '|| 'Added By: '||rec.created_by
					 ||', '|| 'Date Added: '||rec.creation_date||CHR(10);
					 
   END LOOP;
   l_note := l_note || CHR(10)||'--------------------------------------------------------------------';
   
   SELECT (NVL(MAX(seq_number), 0)+1)
   INTO l_seq_number
   FROM CANON_E580_NOTES_TBL 
   WHERE itt_number = p_itt_number
   AND order_number = p_order_number;
   
   INSERT INTO CANON_E580_NOTES_TBL(ITT_NUMBER,
									ORDER_NUMBER,
									SEQ_NUMBER,
									NOTES,
									CREATED_BY,
									CREATION_DATE,
									LAST_UPDATED_BY,
									LAST_UPDATE_DATE)
			VALUES (p_itt_number,
				    p_order_number,
					l_seq_number,
					l_note,
					'S21EXTN_E580',
					SYSDATE,
					'S21EXTN_E580',
					SYSDATE
					);
	
	UPDATE canon_e580_itt_lines_tbl cil
	SET attribute2 = 'N'
	WHERE cil.itt_number = p_itt_number
    AND cil.order_number = p_order_number
	AND NVL(cil.attribute1, 'N') = 'A'
	AND NVL(cil.attribute2, 'N') = 'Y';
	
	COMMIT;
   
   EXCEPTION
   WHEN OTHERS THEN
		
		ROLLBACK;
   END add_append_lines_note;

END CANON_E580_ITT_PROCESS_PKG;
/
