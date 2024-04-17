CREATE OR REPLACE PACKAGE CANON_E427_REV_FORECAST_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
--                                                                                                                                          --
-- Name      :   CANON_E427_REV_FORECAST_PKG.sql                                                                                        --
-- Created On:   10/27/2015                                                                                                                 --
-- Created By:   Balaji Gowravaram                                                                                                          --
--                                                                                                                                          --
-- Purpose   :   This package includes the program units pertaining to the population of data in the table CANON_E427_REV_FORECAST_TBL.     --
--                                                                                                                                          --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Balaji Gowravaram         17-Nov-2015    Initial                                                                             --
--                                                                                                                                          -- 
-- ---------------------------------------------------------------------------------------------------------------------------------------- --    
 
PROCEDURE fetch_revenue_data(p_error_mesg  OUT VARCHAR2);

FUNCTION get_cutoff_date  RETURN VARCHAR2    ;

FUNCTION get_eta_date (p_item VARCHAR2
                      ,p_org  VARCHAR2)
      RETURN VARCHAR2;
      
FUNCTION get_turnaround_time (p_org VARCHAR2) RETURN NUMBER;

PROCEDURE fetch_backorder_impact;

PROCEDURE calc_component_usp(p_order_num       VARCHAR2,
                               p_line_num       VARCHAR2,
                               p_line_sub_num VARCHAR2,
                               p_line_usp     OUT NUMBER,
                               p_weighted_avg OUT NUMBER,
                               p_kit_line_num OUT VARCHAR2,
                               p_kit_line_sub_num OUT VARCHAR2);
                               
PROCEDURE update_service_items;

PROCEDURE error_log ( p_program_name        VARCHAR2
                    , p_process_name          VARCHAR2
                    , p_value1              VARCHAR2  DEFAULT NULL
                    , p_value2              VARCHAR2  DEFAULT NULL
                    , p_value3              VARCHAR2  DEFAULT NULL
                    , p_value4              VARCHAR2  DEFAULT NULL
                    , p_value5              VARCHAR2  DEFAULT NULL
                    , p_error_message       VARCHAR2
                    , p_error_date            DATE
                   );

g_glbl_cmpy_cd VARCHAR2(10) := 'ADB';

END CANON_E427_REV_FORECAST_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E427_REV_FORECAST_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
--                                                                                                                                          --
-- Name      :   CANON_E427_REVENUE_FORECAST_PKG.sql                                                                                        --
-- Created On:   10/27/2015                                                                                                                 --
-- Created By:   Balaji Gowravaram                                                                                                          --
--                                                                                                                                          --
-- Purpose   :   This package includes the program units pertaining to the population of data in the table CANON_E427_REV_FORECAST_TBL.     --
--                                                                                                                                          --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Balaji Gowravaram         17-Nov-2015    Initial                                                                             --
--                                                                                                                                          -- 
-- ---------------------------------------------------------------------------------------------------------------------------------------- --  

PROCEDURE fetch_revenue_data(p_error_mesg  OUT VARCHAR2)
IS

CURSOR cur_so_headers 
IS
SELECT distinct c.cpo_ord_num sales_order,
        dosv.ord_hdr_sts_nm header_status,
    (SELECT dac.ds_acct_nm
	   FROM sell_to_cust dac,
		    cpo dc1
	  WHERE dc1.cpo_ord_num = c.cpo_ord_num
        AND c.ship_to_cust_acct_cd = dac.sell_to_cust_cd
		AND dac.ezcancelflag = '0') ship_to_customer,
    (SELECT dac.ds_acct_nm
	   FROM sell_to_cust dac,
		    cpo dc1
	  WHERE dc1.cpo_ord_num = c.cpo_ord_num
        AND c.bill_to_cust_acct_cd = dac.sell_to_cust_cd
		AND dac.ezcancelflag = '0') bill_to_customer
 FROM cpo c, 
      ds_ord_catg doc, 
      ds_ord_sts_v dosv
 WHERE c.glbl_cmpy_cd = g_glbl_cmpy_cd
  AND c.ezcancelflag = '0'
  AND c.ds_ord_catg_cd = doc.ds_ord_catg_cd
  AND c.glbl_cmpy_cd = doc.glbl_cmpy_cd
  AND doc.ezcancelflag = '0'
  AND c.cpo_ord_num = dosv.cpo_ord_num
  AND c.glbl_cmpy_cd = dosv.glbl_cmpy_cd
  AND dosv.ezcancelflag = '0'
  AND upper(dosv.ord_hdr_sts_nm) NOT IN ('CANCELLED')
  AND NOT EXISTS ( SELECT 1   
                FROM canon_s21_cd_tbl ct, 
                     canon_s21_cd_val_tbl cvt   
                WHERE ct.cd_id = cvt.cd_id 
                AND ct.cd_name = 'CANON_E427_EXCLUSIONS'
                AND UPPER(cvt.VAL1) like 'ORDER_TYPE%'
                AND cvt.VAL2 = doc.ds_ord_catg_nm)
  AND NOT EXISTS (SELECT 1
                 FROM canon_s21_cd_tbl ct, 
                      canon_s21_cd_val_tbl cvt, 
                      ds_ord_line_catg dolc, 
                      cpo_dtl dcd
                 WHERE ct.cd_id = cvt.cd_id 
                 AND dcd.ds_ord_line_catg_cd = dolc.ds_ord_line_catg_cd
                 AND dcd.cpo_ord_num = c.cpo_ord_num
                 AND dcd.glbl_cmpy_cd = c.glbl_cmpy_cd
                 AND dcd.glbl_cmpy_cd = dolc.glbl_cmpy_cd
                 AND dcd.ezcancelflag = '0'
                 AND ct.cd_name = 'CANON_E427_EXCLUSIONS'
                 AND UPPER(cvt.VAL1) like 'LINE_TYPE%'
                 AND cvt.VAL2 = dolc.ds_ord_line_catg_nm
                 );
 /*AND EXISTS (SELECT 1
             FROM cpo_dtl cd
             WHERE cd.cpo_ord_num = dc.cpo_ord_num
             AND NVL(cd.cpo_dtl_canc_flg, 'N') = 'N'
             AND to_date(substr(cd.ezuptime, 1, 8), 'YYYYMMDD') BETWEEN cp_from_date AND cp_to_date);*/


TYPE so_hdrs_tbl IS TABLE OF cur_so_headers%ROWTYPE INDEX BY BINARY_INTEGER;         
         l_so_hdrs_tbl   so_hdrs_tbl;
			 
			 
CURSOR cur_so_lines(p_ord_num VARCHAR2)
IS
SELECT 
    cd.cpo_ord_num,
    cd.cpo_dtl_line_num,
    cd.cpo_dtl_line_sub_num,
    cd.glbl_cmpy_cd,
    cd.ds_cpo_config_pk,
    cd.dply_line_ref_num line_reference,
    cd.ref_cpo_dtl_line_num,
    cd.ref_cpo_dtl_line_sub_num,
	dotpd.line_biz_tp_cd,
    cd.mdse_cd,
    m.back_ord_impct_tp_cd essential_item,
    upper(dosv.ord_line_sts_nm) line_status,
    (SELECT dac.ds_acct_nm
	   FROM sell_to_cust dac,
		    cpo dc1
	  WHERE dc1.cpo_ord_num = dc.cpo_ord_num
        AND dc.ship_to_cust_acct_cd = dac.sell_to_cust_cd
		AND dac.ezcancelflag = '0') ship_to_customer,
    (SELECT dac.ds_acct_nm
	   FROM sell_to_cust dac,
		    cpo dc1
	  WHERE dc1.cpo_ord_num = dc.cpo_ord_num
        AND dc.bill_to_cust_acct_cd = dac.sell_to_cust_cd
		AND dac.ezcancelflag = '0') bill_to_customer,
    m.mdse_nm item_description,
	CASE
		 WHEN NVL (dosv.ord_book_flg, 'N') = 'Y'
		 AND cast(to_timestamp(dc.ord_book_ts, 'RRRRMMDDHH24MISSFF3') as date)  > cast(to_timestamp(cd.ezintime, 'RRRRMMDDHH24MISSFF3') as date) 
			THEN cast(to_timestamp(dc.ord_book_ts, 'RRRRMMDDHH24MISSFF3') as date)
		 WHEN NVL (dosv.ord_book_flg, 'N') = 'Y'
		 AND cast(to_timestamp(dc.ord_book_ts, 'RRRRMMDDHH24MISSFF3') as date)  < cast(to_timestamp(cd.ezintime, 'RRRRMMDDHH24MISSFF3') as date)
			THEN cast(to_timestamp(cd.ezintime, 'RRRRMMDDHH24MISSFF3') as date)
		 WHEN NVL (dosv.ord_book_flg, 'N') = 'N'
			THEN NULL
	  END AS line_booked_date,
	cd.ent_deal_net_unit_prc_amt unit_selling_price,
	cd.ord_qty qty_ordered,
	dcsc.sls_rep_cr_pct salescredit_percent,
	dcsc.sls_rep_toc_cd salesrep_cd,
	so.toc_nm salesrep_nm,
	m.coa_mdse_tp_cd merchandise_type,
	cp.coa_proj_nm merchandise_type_description,
	cast(to_timestamp(cd.ezintime, 'RRRRMMDDHH24MISSFF3') as date) entered_date,
	cd.rtl_wh_cd ship_from_org_code
 FROM cpo_dtl cd,
      ds_ord_sts_v dosv,  
      cpo dc, 
	  ds_ord_tp_proc_dfn dotpd,
      ds_cpo_config dcc,
      mdse m, 
      --ds_mdse_info dmi,
      coa_proj cp,
      (select distinct cpo_ord_num, sls_rep_toc_cd, sls_rep_cr_pct 
	   from ds_cpo_sls_cr
       where glbl_cmpy_cd = 'ADB'
       and ezcancelflag = '0') dcsc,
      s21_org so
 WHERE cd.glbl_cmpy_cd = g_glbl_cmpy_cd
 AND cd.ezcancelflag = '0'
 AND cd.cpo_ord_num = dcc.cpo_ord_num
 AND cd.ds_ord_posn_num = dcc.ds_ord_posn_num
 AND cd.glbl_cmpy_cd = dcc.glbl_cmpy_cd
 AND dcc.config_catg_cd = 'O'
 AND dcc.ezcancelflag = '0'
 AND dcsc.cpo_ord_num = cd.cpo_ord_num
 --AND dcsc.ds_cpo_config_pk(+)= dcd.ds_cpo_config_pk
 AND so.toc_cd(+) = dcsc.sls_rep_toc_cd
 --AND dcsc.glbl_cmpy_cd = dcd.glbl_cmpy_cd
 AND dosv.cpo_ord_num = cd.cpo_ord_num
 AND dosv.cpo_dtl_line_num = cd.cpo_dtl_line_num
 AND dosv.cpo_dtl_line_sub_num = cd.cpo_dtl_line_sub_num
 AND dosv.glbl_cmpy_cd = cd.glbl_cmpy_cd
 AND cd.cpo_ord_num = dc.cpo_ord_num
 AND cd.glbl_cmpy_cd = dc.glbl_cmpy_cd
 AND dc.glbl_cmpy_cd = dotpd.glbl_cmpy_cd(+)
 AND dc.ds_ord_tp_cd = dotpd.ds_ord_tp_cd(+)
 AND cd.mdse_cd = substr(m.mdse_cd, 1, 8)
 AND m.glbl_cmpy_cd = cd.glbl_cmpy_cd
 AND m.coa_mdse_tp_cd = cp.coa_proj_cd
 AND cp.coa_proj_cd != '11'
 AND cp.glbl_cmpy_cd = m.glbl_cmpy_cd
 AND cd.cpo_ord_num = p_ord_num
 AND NOT EXISTS (SELECT 1
                 FROM canon_s21_cd_tbl ct, 
                      canon_s21_cd_val_tbl cvt, 
                      ds_ord_line_catg dolc
                 WHERE ct.cd_id = cvt.cd_id 
                 AND cd.ds_ord_line_catg_cd = dolc.ds_ord_line_catg_cd
                 AND ct.cd_name = 'CANON_E427_EXCLUSIONS'
                 AND UPPER(cvt.VAL1) like 'LINE_TYPE%'
                 AND cvt.VAL2 = dolc.ds_ord_line_catg_nm
                 );


l_ship_to_cust_id     VARCHAR2(50);             
l_ship_to_customer_name VARCHAR2(250);                 
l_cust_ship_address VARCHAR2(250);                 
l_cust_ship_city VARCHAR2(100);                 
l_cust_ship_state VARCHAR2(50);                 
l_cust_ship_zip    VARCHAR2(50);
l_cust_bill_address VARCHAR2(250);
l_cust_bill_city VARCHAR2(100);
l_cust_bill_state VARCHAR2(50);
l_cust_bill_zip VARCHAR2(50);
l_salesrep_name VARCHAR2(100);
l_branch VARCHAR2(50);
l_region VARCHAR2(50);
l_zone VARCHAR2(50);
l_salesrep_requested_dt DATE;
l_shipped_date DATE;
l_so_num VARCHAR2(50);
l_shpg_pln_num VARCHAR2(50);
l_delivery_conf_date DATE;         
l_del_reason_cd VARCHAR2(50);
l_del_comments VARCHAR2(4000);   
l_wh_reason_code VARCHAR2(100);
l_picked_date DATE;
l_hold VARCHAR2(100);        
l_total_order_value NUMBER := 0;         
l_invoice_date DATE;
l_installation_date DATE;
l_invty_available_date DATE;

l_booked_not_invoiced_rev  NUMBER := 0;
l_entered_not_booked_rev NUMBER := 0;
l_projected_future_month_rev NUMBER := 0;
l_projected_current_month_rev NUMBER := 0;
l_invoiced_revenue NUMBER := 0;
l_cutoff_projected_future_rev NUMBER := 0;
l_current_month_revenue NUMBER := 0;
l_wh_projected_future_rev NUMBER := 0;
l_slsrep_projected_future_rev NUMBER := 0;
l_future_inv_eta_bo_total NUMBER := 0;

l_component_price NUMBER;
l_weighted_avg NUMBER;
l_kit_line_num VARCHAR2(20);
l_kit_line_sub_num VARCHAR2(20);
l_error_order VARCHAR2(50) := NULL;
l_error_msg VARCHAR2(3000) := NULL;

BEGIN

DELETE FROM CANON_E427_REV_FORECAST_TBL;

OPEN cur_so_headers;
FETCH cur_so_headers BULK COLLECT INTO l_so_hdrs_tbl;
CLOSE cur_so_headers;

  IF (l_so_hdrs_tbl.COUNT = 0) THEN
	  l_error_msg := l_error_msg || '~' || 'No eligible sales orders~';
	  p_error_mesg := l_error_msg||'-------------------------------------------------------------------------';
	 RETURN;
  END IF;

FOR rec1 IN 1..l_so_hdrs_tbl.COUNT
LOOP
    l_error_order := l_so_hdrs_tbl(rec1).sales_order;
    dbms_output.put_line('processing order: '||l_so_hdrs_tbl(rec1).sales_order);
    FOR rec2 in cur_so_lines(l_so_hdrs_tbl(rec1).sales_order)
    LOOP
     BEGIN
        SELECT dac.sell_to_cust_cd,
               dac.ds_acct_nm,
               stc.first_line_addr || ', ' || stc.scd_line_addr,
               stc.cty_addr,
               stc.st_cd,
               stc.post_cd
          INTO l_ship_to_cust_id,
               l_ship_to_customer_name,
               l_cust_ship_address,
               l_cust_ship_city,
               l_cust_ship_state,
               l_cust_ship_zip
          FROM sell_to_cust dac,
               ship_to_cust stc,
               ds_cpo_config dcc
        WHERE dcc.cpo_ord_num = rec2.cpo_ord_num
          AND dcc.ds_cpo_config_pk = rec2.ds_cpo_config_pk
          AND dcc.ship_to_cust_loc_cd = stc.ship_to_cust_cd
          AND stc.sell_to_cust_cd = dac.sell_to_cust_cd
		  AND dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND dcc.glbl_cmpy_cd = stc.glbl_cmpy_cd
		  AND dac.glbl_cmpy_cd = stc.glbl_cmpy_cd
		  AND dac.ezcancelflag = '0'
		  AND dcc.ezcancelflag = '0';
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
        l_ship_to_cust_id := NULL;
        l_ship_to_customer_name    := NULL;    
        l_cust_ship_address := NULL;     
        l_cust_ship_city := NULL;     
        l_cust_ship_state := NULL;     
        l_cust_ship_zip    := NULL;
     WHEN OTHERS THEN
        l_ship_to_cust_id := NULL;
        l_ship_to_customer_name    := NULL;    
        l_cust_ship_address := NULL;     
        l_cust_ship_city := NULL;     
        l_cust_ship_state := NULL;     
        l_cust_ship_zip    := NULL; 
        l_error_msg := l_error_msg || '~' || 'Error while fetching Ship to Address details: '||SQLERRM;
        
    error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'SHIP_TO Address'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.ds_cpo_config_pk
                , p_value3        =>  NULL
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
        
     END;
        
     BEGIN
        SELECT btc.first_line_addr || ', ' || btc.scd_line_addr,
               btc.cty_addr,
               btc.st_cd,
               btc.post_cd
          INTO l_cust_bill_address,
               l_cust_bill_city,
               l_cust_bill_state,
               l_cust_bill_zip
          FROM sell_to_cust dac,
               bill_to_cust btc,
               ds_cpo_config dcc
        WHERE dcc.cpo_ord_num = rec2.cpo_ord_num
          AND dcc.ds_cpo_config_pk = rec2.ds_cpo_config_pk
          AND dcc.bill_to_cust_loc_cd = btc.bill_to_cust_cd
          AND dcc.bill_to_cust_acct_cd = dac.sell_to_cust_cd
		  AND dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND dcc.glbl_cmpy_cd = btc.glbl_cmpy_cd
		  AND dcc.glbl_cmpy_cd = dac.glbl_cmpy_cd
		  AND dac.ezcancelflag = '0'
		  AND dcc.ezcancelflag = '0';
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
        l_cust_bill_address := NULL;
        l_cust_bill_city := NULL;
        l_cust_bill_state := NULL;
        l_cust_bill_zip := NULL;
     WHEN OTHERS THEN
        l_cust_bill_address := NULL;
        l_cust_bill_city := NULL;
        l_cust_bill_state := NULL;
        l_cust_bill_zip := NULL;
        l_error_msg := l_error_msg || '~' || 'Error while fetching Bill to Address details: '||SQLERRM;
        
        error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'BILL_TO Address'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.ds_cpo_config_pk
                , p_value3        =>  NULL
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
     END;
          
     --dbms_output.put_line('3');
	 
	 BEGIN
	 
	 WITH zn_tier
           AS (SELECT hrch.org_tier_cd attr
                 FROM org_hrch_stru_dfn hrch, stru_dfn dfn
                WHERE     1 = 1
                      AND dfn.zn_flg = 'Y'
                      AND hrch.stru_dfn_cd = dfn.stru_dfn_cd
                      AND org_stru_tp_cd = 'BOS'
                      AND hrch.biz_area_org_cd = '1'
                      AND hrch.eff_from_dt(+) <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                      AND NVL (hrch.eff_thru_dt(+), '99991231') >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                      AND dfn.ezcancelflag = hrch.ezcancelflag
                      AND hrch.ezcancelflag = '0'
                      AND hrch.glbl_cmpy_cd = dfn.glbl_cmpy_cd
                      AND dfn.glbl_cmpy_cd = 'ADB')
		SELECT
		CASE
             WHEN zn_tier.attr = a.first_org_tier_cd THEN a.first_org_nm
             WHEN zn_tier.attr = a.scd_org_tier_cd THEN a.scd_org_nm
             WHEN zn_tier.attr = a.third_org_tier_cd THEN a.third_org_nm
             WHEN zn_tier.attr = a.frth_org_tier_cd THEN a.frth_org_nm
             WHEN zn_tier.attr = a.fifth_org_tier_cd THEN a.fifth_org_nm
             WHEN zn_tier.attr = a.sixth_org_tier_cd THEN a.sixth_org_nm
             WHEN zn_tier.attr = a.svnth_org_tier_cd THEN a.svnth_org_nm
             WHEN zn_tier.attr = a.eighth_org_tier_cd THEN a.eighth_org_nm
             WHEN zn_tier.attr = a.ninth_org_tier_cd THEN a.ninth_org_nm
             WHEN zn_tier.attr = a.tenth_org_tier_cd THEN a.tenth_org_nm
             WHEN zn_tier.attr = a.elvth_org_tier_cd THEN a.elvth_org_nm
          END
          INTO
		  l_zone
		FROM s21_org a,
             zn_tier
		WHERE a.toc_cd = nvl(rec2.salesrep_cd, 'XXX');
		
   
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
          l_zone := NULL;
      WHEN OTHERS THEN
          l_zone := NULL;
       
          error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Salesrep Zone'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.salesrep_cd
                , p_value3        =>  NULL
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
      END;
	 
      BEGIN
          SELECT so.toc_nm salesrep_name,
                 cb.coa_br_nm branch
          INTO  l_salesrep_name,
                l_branch
          FROM s21_org so, coa_br cb
          WHERE so.toc_cd = nvl(rec2.salesrep_cd, 'XXX')
          AND   so.coa_br_cd = cb.coa_br_cd;
      
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
          l_salesrep_name := NULL;
          l_branch := NULL;
      WHEN OTHERS THEN
          l_salesrep_name := NULL;
          l_branch := NULL;
          l_error_msg := l_error_msg || '~' || 'Error while fetching Branch name: '||SQLERRM;
          
          error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Salesrep Name and Branch'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.salesrep_cd
                , p_value3        =>  NULL
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
      END;
      --dbms_output.put_line('4');
          
      BEGIN
          SELECT ce.coa_extn_nm
          INTO    l_region
          FROM toc t, coa_extn ce
          WHERE t.toc_cd = nvl(rec2.salesrep_cd, 'XXX')
          AND   t.coa_extn_cd = ce.coa_extn_cd;
    
      EXCEPTION
      WHEN NO_DATA_FOUND THEN 
        l_region := NULL;
      WHEN OTHERS THEN 
        l_region := NULL;
        l_error_msg := l_error_msg || '~' || 'Error while fetching Region name: '||SQLERRM;
        
        error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Salesrep Region'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.salesrep_cd
                , p_value3        =>  NULL
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
      END;
      --dbms_output.put_line('5');
                
      BEGIN
          SELECT to_date(sp.rdd_dt, 'RRRRMMDD'), to_date(sp.actl_ship_dt, 'RRRRMMDD'), sp.so_num, sp.shpg_pln_num
          INTO l_salesrep_requested_dt, l_shipped_date, l_so_num, l_shpg_pln_num
          FROM shpg_pln sp
          WHERE 1 =1 
          AND sp.trx_hdr_num = rec2.cpo_ord_num
          AND sp.trx_line_num = rec2.cpo_dtl_line_num
          AND sp.trx_line_sub_num = rec2.cpo_dtl_line_sub_num
          AND sp.glbl_cmpy_cd = rec2.glbl_cmpy_cd
		  AND sp.ezcancelflag = '0';
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        l_salesrep_requested_dt := NULL;
        l_shipped_date := NULL;
        l_so_num := NULL;
      WHEN OTHERS THEN
        l_salesrep_requested_dt := NULL;
        l_shipped_date := NULL;
        l_so_num := NULL;
        --l_error_msg := l_error_msg || '~' || 'Fetching Salesrep Requested Date, Shipped Date: '||SQLERRM;
        
        error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Salesrep req dt, ship date etc'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.cpo_dtl_line_num
                , p_value3        =>  rec2.cpo_dtl_line_sub_num
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
      END;
         --dbms_output.put_line('6'); 
          IF l_so_num IS NOT NULL THEN
                BEGIN
              
                  select cast(to_timestamp(actl_dely_dt||actl_dely_tm, 'RRRRMMDDHH24MISS') as date), 
				  (SELECT cr.carr_rsn_nm
				   FROM carr_rsn cr 
				   WHERE cr.carr_rsn_cd = nvl(sod.carr_rsn_cd, 'XX')) del_reason_cd,
				  sod.carr_cmnt_txt
                  into l_delivery_conf_date, l_del_reason_cd, l_del_comments
                  from shpg_ord so, shpg_ord_dtl sod
                  where so.so_num = sod.so_num
				  AND sod.glbl_cmpy_cd = so.glbl_cmpy_cd
                  AND sod.trx_hdr_num = rec2.cpo_ord_num
                  AND sod.trx_line_num = rec2.cpo_dtl_line_num
                  AND sod.trx_line_sub_num = rec2.cpo_dtl_line_sub_num
				  AND sod.glbl_cmpy_cd = rec2.glbl_cmpy_cd
				  AND so.so_num = l_so_num
				  AND so.ezcancelflag = '0'
				  AND sod.ezcancelflag = '0';
              EXCEPTION
              WHEN NO_DATA_FOUND THEN
                l_delivery_conf_date := NULL;
              WHEN OTHERS THEN
                l_delivery_conf_date := NULL;
                l_error_msg := l_error_msg || '~' || 'Fetching Delivery confirmation Date: '||SQLERRM;
                
                error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                        , p_process_name  => 'Delivery Conf date'
                        , p_value1        =>  rec2.cpo_ord_num
                        , p_value2        =>  rec2.cpo_dtl_line_num
                        , p_value3        =>  rec2.cpo_dtl_line_sub_num
                        , p_value4        =>  l_so_num
                        , p_value5        =>  NULL
                        , p_error_message =>  SQLERRM
                        , p_error_date      =>  SYSDATE
                      );
              END;
              --dbms_output.put_line('7');
              BEGIN
                  SELECT scs.schd_coord_sts_nm
                  INTO l_wh_reason_code
                  FROM shpg_ord sos, 
				       schd_coord_sts scs
                  WHERE sos.schd_coord_sts_cd = scs.schd_coord_sts_cd
				  AND sos.glbl_cmpy_cd = scs.glbl_cmpy_cd
				  AND sos.glbl_cmpy_cd = g_glbl_cmpy_cd
				  AND sos.ezcancelflag = '0'
                  AND sos.so_num = l_so_num;
				  
              EXCEPTION
              WHEN NO_DATA_FOUND THEN
                l_wh_reason_code := NULL;
              WHEN OTHERS THEN
                l_wh_reason_code := NULL;
                l_error_msg := l_error_msg || '~' || 'Fetching WH Reason code: '||SQLERRM;
				error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Fetching WH Reason code'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.cpo_dtl_line_num
                , p_value3        =>  rec2.cpo_dtl_line_sub_num
                , p_value4        =>  l_so_num
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
              END;
              
              BEGIN
                SELECT cast(to_timestamp(p.pkt_sts_ts, 'RRRRMMDDHH24MISSFF3') as date)
                INTO l_picked_date
                FROM pkt p, pkt_sts ps, shpg_ord so
                WHERE p.pkt_sts_cd = ps.pkt_sts_cd
                AND so.pick_tkt_num = p.so_num
                AND upper(ps.pkt_sts_nm) = 'PICKED'
				AND p.glbl_cmpy_cd = ps.glbl_cmpy_cd
				AND p.glbl_cmpy_cd = so.glbl_cmpy_cd
				AND p.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND p.ezcancelflag = '0'
				AND so.ezcancelflag = '0'
                AND so.so_num = l_so_num;
              EXCEPTION
              WHEN NO_DATA_FOUND THEN
                l_picked_date := NULL;
              WHEN OTHERS THEN
                l_picked_date := NULL;
                l_error_msg := l_error_msg || '~' || 'Fetching Picked Date: '||SQLERRM;
				error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Fetching Picked Date'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.cpo_dtl_line_num
                , p_value3        =>  rec2.cpo_dtl_line_sub_num
                , p_value4        =>  l_so_num
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
              END;
                        
          END IF;
          
          
          
          BEGIN
             SELECT hr.hld_rsn_nm 
              INTO l_hold
              FROM hld h, hld_rsn hr
              WHERE h.hld_rsn_cd = hr.hld_rsn_cd
              AND h.cpo_ord_num = rec2.cpo_ord_num
              AND h.cpo_dtl_line_num = rec2.cpo_dtl_line_num
              AND h.cpo_dtl_line_sub_num = rec2.cpo_dtl_line_sub_num
              AND h.shpg_pln_num = l_shpg_pln_num
			  AND h.glbl_cmpy_cd = hr.glbl_cmpy_cd
			  AND h.glbl_cmpy_cd = rec2.glbl_cmpy_cd
			  AND h.ezcancelflag = '0'
              AND nvl(h.rel_flg, 'N') = 'N'
			  AND rownum = 1;
              
          EXCEPTION
          WHEN OTHERS THEN
          BEGIN
          SELECT hr.hld_rsn_nm 
              INTO l_hold
              FROM hld h, hld_rsn hr
              WHERE h.hld_rsn_cd = hr.hld_rsn_cd
              AND h.cpo_ord_num = rec2.cpo_ord_num
              AND nvl(h.rel_flg, 'N') = 'N'
			  AND h.glbl_cmpy_cd = hr.glbl_cmpy_cd
			  AND h.glbl_cmpy_cd = rec2.glbl_cmpy_cd
			  AND h.ezcancelflag = '0'
			  AND rownum = 1;
			  
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
          l_hold := NULL;
          WHEN OTHERS THEN
          l_hold := NULL;
          l_error_msg := l_error_msg || '~' || 'Fetching Hold Information: '||SQLERRM;
		  error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Fetching Hold Information'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.cpo_dtl_line_num
                , p_value3        =>  rec2.cpo_dtl_line_sub_num
                , p_value4        =>  l_shpg_pln_num
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
          END;
          END;
          
          BEGIN
            SELECT to_date(i.acct_dt, 'RRRRMMDD')
            INTO l_invoice_date
            FROM inv i, inv_line il
            WHERE i.inv_num = il.inv_num
            AND i.cpo_ord_num = il.cpo_ord_num
            AND il. cpo_ord_num = l_so_hdrs_tbl(rec1).sales_order
            AND il.cpo_dtl_line_num = rec2.cpo_dtl_line_num
            AND il.cpo_dtl_line_sub_num = rec2.cpo_dtl_line_sub_num
			AND i.glbl_cmpy_cd = il.glbl_cmpy_cd
			AND i.glbl_cmpy_cd = rec2.glbl_cmpy_cd
			AND i.ezcancelflag = '0'
			AND il.ezcancelflag = '0';
            
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            l_invoice_date := NULL;
          WHEN OTHERS THEN
            l_invoice_date := NULL;
            l_error_msg := l_error_msg || '~' || 'Fetching Invoice Date: '||SQLERRM;
			error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Fetching Invoice Date'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.cpo_dtl_line_num
                , p_value3        =>  rec2.cpo_dtl_line_sub_num
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
          END;
          
          BEGIN
            SELECT to_date(istl_dt, 'RRRRMMDD')
            INTO l_installation_date
            FROM svc_mach_mstr smm
            WHERE smm.cpo_ord_num = l_so_hdrs_tbl(rec1).sales_order
            AND smm.cpo_dtl_line_num = rec2.cpo_dtl_line_num
            AND smm.cpo_dtl_line_sub_num = rec2.cpo_dtl_line_sub_num
			AND smm.glbl_cmpy_cd = rec2.glbl_cmpy_cd;
            
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            l_installation_date := NULL;
          WHEN OTHERS THEN
            l_installation_date := NULL;
            l_error_msg := l_error_msg || '~' || 'Fetching Installation Date: '||SQLERRM;
			error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'Fetching Installation Date'
                , p_value1        =>  rec2.cpo_ord_num
                , p_value2        =>  rec2.cpo_dtl_line_num
                , p_value3        =>  rec2.cpo_dtl_line_sub_num
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
          END;
          
          l_invty_available_date := NULL;
          
          IF upper(l_so_hdrs_tbl(rec1).header_status) NOT IN ('CLOSED', 'CANCELLED') AND UPPER(rec2.line_status) = 'BACK ORDER'
          THEN
                
                  l_invty_available_date := TO_DATE (get_eta_date (rec2.mdse_cd, rec2.ship_from_org_code),'YYYY/MM/DD') ;
                
                /*IF rec2.line_reference IS NOT NULL
                THEN
                    l_essential_items_count := 0;
                    
                    BEGIN
                        SELECT COUNT (1)
                          INTO l_essential_items_count
                          FROM ds_mdse_info dmi, ds_cpo_dtl dcd, ds_ord_sts_v dosv, cpo_dtl cd
                         WHERE 1 = 1
                           AND cd.cpo_ord_num = dcd.cpo_ord_num
                           AND cd.cpo_dtl_line_num = dcd.cpo_dtl_line_num
                           AND cd.cpo_dtl_line_sub_num = dcd.cpo_dtl_line_sub_num
                           AND dosv.cpo_ord_num = dcd.cpo_ord_num
                           AND dosv.cpo_dtl_line_num = dcd.cpo_dtl_line_num
                           AND dosv.cpo_dtl_line_sub_num = dcd.cpo_dtl_line_sub_num
                           AND cd.mdse_cd = dmi.mdse_cd
                           AND dcd.cpo_ord_num = l_so_hdrs_tbl(rec1).sales_order
                           AND dcd.dply_line_ref_num = rec2.line_reference
                           AND dmi.coa_mdse_tp_cd != '11'
                           AND NVL (dmi.back_ord_impct_tp_cd, '-Z') IN ('C', 'E')
                           AND NVL (upper(dosv.ord_line_sts_nm), '-ZZZZ') = 'BACK ORDER'
                           AND NOT EXISTS (SELECT 1
                                             FROM canon_s21_cd_tbl ct, 
                                                  canon_s21_cd_val_tbl cvt, 
                                                  ds_ord_line_catg dolc
                                             WHERE ct.cd_id = cvt.cd_id 
                                             AND dcd.ds_ord_line_catg_cd = dolc.ds_ord_line_catg_cd
                                             AND ct.cd_name = 'CANON_E427_EXCLUSIONS'
                                             AND UPPER(cvt.VAL1) like '%LINE_TYPE%'
                                             AND cvt.VAL2 = dolc.ds_ord_line_catg_nm
                                             );
                     EXCEPTION
                        WHEN OTHERS THEN
                           l_essential_items_count := 0;
                     END;
                     
                IF l_essential_items_count > 0 THEN
                
                    l_totalorder_backorder_amt := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
                    l_critical_config_bo_amt := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
                    l_non_essential_config_bo_amt := 0;
                    
                            
                  IF TRUNC (NVL (delivery_confirm_date, salesrep_requested_date)) <= TRUNC (LAST_DAY (SYSDATE))
                     AND NVL (rec2.essential_item, '-Z') IN ('C', 'E')
                     AND TRUNC(NVL (ld_max_ess_inv_avail_date ,SYSDATE)) > TRUNC (LAST_DAY (SYSDATE))
                  THEN
                    l_future_inv_eta_bo_total := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
                  ELSIF NVL (rec2.essential_item, '-Z') IN ('C', 'E')
                     AND TRUNC(NVL (ld_max_ess_inv_avail_date ,SYSDATE)) <= TRUNC (LAST_DAY (SYSDATE))
                  THEN
                    l_future_inv_eta_bo_total := 0;
                  END IF;
                  
                  IF (TRUNC(NVL (l_delivery_conf_date, l_salesrep_requested_dt)) >  TRUNC (LAST_DAY (SYSDATE)))
                  THEN
                    l_future_mth_backorder_total := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
                    l_current_mth_backorder_total := 0;
                  ELSE
                    l_future_mth_backorder_total := 0;
                    l_current_mth_backorder_total := NVL (l_totalorder_backorder_amt, 0) 
                                                    - NVL (l_future_mth_backorder_total ,0) 
                                                    - NVL(l_future_inv_eta_bo_total ,0)
                  END IF;
                  
                ELSIF l_essential_items_count = 0 THEN  
                    
                    l_totalorder_backorder_amt := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
                    l_critical_config_bo_amt := 0;
                    l_non_essential_config_bo_amt := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);;
                    
                    IF (TRUNC(NVL (l_delivery_conf_date, l_salesrep_requested_dt)) >  TRUNC (LAST_DAY (SYSDATE)))
                       AND NVL (l_invty_available_date, SYSDATE) <= TRUNC (LAST_DAY (SYSDATE))
                    THEN
                        l_future_mth_backorder_total := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
                    END IF;
                    
                    IF TRUNC (NVL (delivery_confirm_date, salesrep_requested_date)) <= TRUNC (LAST_DAY (SYSDATE))
                       AND NVL (l_invty_available_date, SYSDATE) > TRUNC (LAST_DAY (SYSDATE))
                    THEN
                        l_future_inv_eta_bo_total := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
                    ELSE
                        l_future_inv_eta_bo_total := 0;
                    END IF;
                    
                    IF (TRUNC(NVL (l_delivery_conf_date, l_salesrep_requested_dt)) >  TRUNC (LAST_DAY (SYSDATE)))
                    THEN
                        l_current_mth_backorder_total := 0;
                    ELSE
                        l_current_mth_backorder_total := NVL (l_totalorder_backorder_amt, 0)
                                                    - (  NVL (l_future_mth_backorder_total ,0)
                                                        + NVL(l_future_inv_eta_bo_total,0));
                    END IF;
                END IF;
          ELSE
            
            l_totalorder_backorder_amt := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
            
            IF (TRUNC(NVL (l_delivery_conf_date, l_salesrep_requested_dt)) >  TRUNC (LAST_DAY (SYSDATE)))
               AND NVL (l_invty_available_date, SYSDATE) <= TRUNC (LAST_DAY (SYSDATE))
            THEN
                l_future_mth_backorder_total := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
            ELSE
                l_future_mth_backorder_total := 0;
            END IF;
            
            IF TRUNC (NVL (delivery_confirm_date, salesrep_requested_date)) <= TRUNC (LAST_DAY (SYSDATE))
               AND NVL (l_invty_available_date, SYSDATE) > TRUNC (LAST_DAY (SYSDATE))
            THEN
                l_future_inv_eta_bo_total := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * rec2.salescredit_percent/100), 2);
            ELSE
                l_future_inv_eta_bo_total := 0;
            END IF;
            
            IF (TRUNC(NVL (l_delivery_conf_date, l_salesrep_requested_dt)) >  TRUNC (LAST_DAY (SYSDATE)))
            THEN
                l_current_mth_backorder_total := 0;
            ELSE
                l_current_mth_backorder_total := NVL (l_totalorder_backorder_amt, 0)
                                            - (  NVL (l_future_mth_backorder_total ,0)
                                                + NVL(l_future_inv_eta_bo_total,0));
            END IF;
            
           END IF;
          
        ELSE
            l_totalorder_backorder_amt = 0;
            l_critical_config_bo_amt = 0;
            l_non_essential_config_bo_amt = 0;
            l_current_mth_backorder_total = 0;
            l_future_mth_backorder_total = 0;
            l_future_inv_eta_bo_total = 0;
            line_backorder_amt = 0;*/
        
        END IF;
        
l_booked_not_invoiced_rev  := 0;
l_entered_not_booked_rev := 0;
l_projected_future_month_rev := 0;
l_projected_current_month_rev := 0;
l_invoiced_revenue := 0;
l_cutoff_projected_future_rev := 0;
l_current_month_revenue := 0;
l_wh_projected_future_rev := 0;
l_slsrep_projected_future_rev := 0;

          IF upper(rec2.line_status) = 'ENTERED' AND TRUNC (NVL (l_invoice_date, SYSDATE)) <= get_cutoff_date 
            AND upper(l_so_hdrs_tbl(rec1).header_status) NOT IN ('CLOSED', 'CANCELLED', 'BOOKED')
                THEN
                    l_entered_not_booked_rev := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * nvl(rec2.salescredit_percent, 100)/100), 2);
                ELSE
                    l_entered_not_booked_rev := 0;
                END IF;

            IF upper(rec2.line_status) <> 'CLOSED' AND upper(l_so_hdrs_tbl(rec1).header_status) = 'BOOKED'
            THEN
                
                IF (NVL (l_delivery_conf_date, NVL(l_salesrep_requested_dt,SYSDATE))) > TRUNC (LAST_DAY (SYSDATE))
                THEN
                    l_booked_not_invoiced_rev := 0;
                    
                ELSIF l_invty_available_date > LAST_DAY(SYSDATE) OR NVL(l_future_inv_eta_bo_total,0) <> 0
                THEN
                    l_booked_not_invoiced_rev := 0;
                
                ELSIF l_invoice_date IS NULL  AND NVL (l_delivery_conf_date, NVL(l_salesrep_requested_dt,SYSDATE))  <=  LAST_DAY (SYSDATE)
                THEN
                    l_booked_not_invoiced_rev := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * nvl(rec2.salescredit_percent, 100)/100), 2);
                
                END IF;
            END IF;
          
          
          
          IF TRUNC (NVL (l_invoice_date, SYSDATE)) >  ADD_MONTHS (TRUNC (LAST_DAY(SYSDATE)) ,-1)
          THEN
          
                l_total_order_value := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * nvl(rec2.salescredit_percent, 100)/100), 2);
                
                IF (TRUNC (nvl(l_salesrep_requested_dt, SYSDATE)) > TRUNC (LAST_DAY(SYSDATE))) 
                  AND l_delivery_conf_date IS NOT NULL
                THEN
                    l_slsrep_projected_future_rev := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * nvl(rec2.salescredit_percent, 100)/100), 2);
                ELSE
                    l_slsrep_projected_future_rev := 0;
                END IF;
                
                
                IF l_delivery_conf_date IS NOT NULL
                  AND (l_delivery_conf_date) > LAST_DAY (SYSDATE)
                THEN
                    l_wh_projected_future_rev := ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.qty_ordered, 0) * nvl(rec2.salescredit_percent, 100)/100), 2);
                ELSE
                    l_wh_projected_future_rev := 0;
                END IF;
            
        
            
            IF upper(rec2.line_status) <> 'ENTERED'
            THEN
                l_current_month_revenue :=   NVL (l_total_order_value, 0)
                                          - (  NVL (l_slsrep_projected_future_rev, 0)
                                             + NVL (l_wh_projected_future_rev, 0)
                                             + NVL (l_future_inv_eta_bo_total, 0));
                                             
            ELSIF upper(rec2.line_status) = 'ENTERED' AND TRUNC (NVL (l_invoice_date, SYSDATE)) <= get_cutoff_date
            THEN
                l_current_month_revenue := NVL (l_total_order_value, 0)
                                         - ( NVL (l_slsrep_projected_future_rev, 0)
                                           + NVL (l_wh_projected_future_rev, 0));
                                
            ELSIF upper(rec2.line_status) = 'ENTERED' AND TRUNC (NVL (l_invoice_date, SYSDATE)) > get_cutoff_date
            THEN
                l_cutoff_projected_future_rev := NVL (l_total_order_value, 0) 
                                            - (  NVL (l_slsrep_projected_future_rev, 0)
                                                + NVL (l_wh_projected_future_rev, 0));
                                                
            ELSE
                l_current_month_revenue := 0;
                l_cutoff_projected_future_rev := 0;
                
            END IF;
            
            IF upper(rec2.line_status) like '%CLOSED%' 
            THEN
                l_entered_not_booked_rev := 0;
                l_booked_not_invoiced_rev := 0;
                
                IF l_current_month_revenue <> 0
                THEN
                    l_invoiced_revenue :=  NVL(l_current_month_revenue ,0)
                                         - NVL(l_entered_not_booked_rev,0)
                                         - NVL(l_booked_not_invoiced_rev,0);
                                         
                ELSE
                    l_invoiced_revenue := 0;
                END IF;
            
            END IF;
            
            l_projected_current_month_rev := NVL (l_entered_not_booked_rev, 0)
                                            + NVL (l_booked_not_invoiced_rev, 0)
                                            + NVL (l_invoiced_revenue, 0);
                                            
            l_projected_future_month_rev := NVL (l_slsrep_projected_future_rev, 0)
                                           + NVL (l_wh_projected_future_rev, 0)
                                           + NVL (l_future_inv_eta_bo_total, 0)
                                           + NVL (l_cutoff_projected_future_rev,0);
            
            
    END IF;
    
    
    
    IF rec2.ref_cpo_dtl_line_num IS NOT NULL 
    THEN
        calc_component_usp(p_order_num         => l_so_hdrs_tbl(rec1).sales_order,
                           p_line_num         => rec2.cpo_dtl_line_num,
                           p_line_sub_num     => rec2.cpo_dtl_line_sub_num,
                           p_line_usp       => l_component_price,
                           p_weighted_avg   => l_weighted_avg,
                           p_kit_line_num     => l_kit_line_num,
                           p_kit_line_sub_num    => l_kit_line_sub_num);
                           
    END IF;
                         
    
     --dbms_output.put_line('8');
     INSERT INTO CANON_E427_REV_FORECAST_TBL
                            (channel,
							 line_of_business,
							 sales_zone, 
                             region, 
                             branch, 
                             resource_id, 
                             salesrep_name, 
                             order_number, 
                             header_status, 
                             line_number, 
                             unique_id, 
                             party_id, 
                             --party_site_id, 
                             customer_name, 
                             customer_address, 
                             customer_city, 
                             customer_state, 
                             customer_zip, 
                             line_reference, 
                             ordered_item, 
                             item_description, 
                             merchandise_type, 
                             merchandise_type_description, 
                             essential_item, 
                             unit_selling_price, 
                             quantity_ordered,
                             total_unit_count, 
                             line_status, 
                             entered_date, 
                             booked_date, 
                             picked_date, 
                             salesrep_requested_date, 
                             warehouse_scheduled_date,
                             warehouse_reason_code,
                             shipped_date, 
                             invoice_hold,
                             installation_date, 
                             invoice_date,
                             inventory_available_date,
                             total_order_value, 
                             salesrep_projected_future_rev, 
                             wh_projected_future_rev, 
                             current_month_revenue, 
                             curr_mth_proj_rev_unbooked, 
                             curr_mth_proj_rev_uninvoiced, 
                             curr_mth_revenue_invoiced, 
                             --totalorder_backorder_impact, 
                             --currunt_mth_backorder_impact, 
                             --future_mth_backorder_impact,
                             --eta_based_future_mth_bo_impact, 
                             --line_backorder_amount, 
                             projected_current_month_rev, 
                             projected_future_month_rev, 
                             --non_essential_config_bo_impact, 
                             --critical_config_bo_impact, 
                             --non_essential_items_bo_value, 
                             --critical_lines_bo_value, 
                             --total_backorder_impact, 
                             salescredit_percent, 
                             comments,
                             delivery_reason_code, 
                             --oe_line_id, 
                             --oe_sales_credit_id, 
                             --oe_salesrep_id, 
                             --adj_salesrep_id,
                             --conc_request_id, 
                             ship_from_org_code,
                             cutoff_projected_future_rev)
 VALUES
          (NULL,
		   rec2.line_biz_tp_cd,
		   l_zone,
           l_region,
           l_branch,
           rec2.salesrep_cd,
           l_salesrep_name,
           l_so_hdrs_tbl(rec1).sales_order,
           l_so_hdrs_tbl(rec1).header_status,
           rec2.cpo_dtl_line_num || '.' || rec2.cpo_dtl_line_sub_num,
           l_so_hdrs_tbl(rec1).sales_order||'-'||rec2.line_reference,
           l_ship_to_cust_id,
           l_ship_to_customer_name,
           l_cust_ship_address,
           l_cust_ship_city,
           l_cust_ship_state,
           l_cust_ship_zip,
           rec2.line_reference,
           rec2.mdse_cd,
           rec2.item_description,
           rec2.merchandise_type,
           rec2.merchandise_type_description,
           rec2.essential_item,
           --decode(rec2.ref_cpo_dtl_line_num, NULL, rec2.unit_selling_price, l_component_price),
           ROUND((nvl(rec2.unit_selling_price, 0) * nvl(rec2.salescredit_percent, 100)/100), 2),
           rec2.qty_ordered,
           NULL,
           rec2.line_status,
           rec2.entered_date,
           rec2.line_booked_date, 
           l_picked_date, 
           l_salesrep_requested_dt,
           l_delivery_conf_date, 
           l_wh_reason_code,
           l_shipped_date,
           l_hold,
           l_installation_date,
           l_invoice_date,
           l_invty_available_date,
           l_total_order_value,
           l_slsrep_projected_future_rev,
           l_wh_projected_future_rev,
           l_current_month_revenue,
           l_entered_not_booked_rev,
           l_booked_not_invoiced_rev,
           l_invoiced_revenue,
           l_projected_current_month_rev,
           l_projected_future_month_rev,
           rec2.salescredit_percent,
		   l_del_comments,
		   l_del_reason_cd,
           rec2.ship_from_org_code,
           l_cutoff_projected_future_rev);
          --dbms_output.put_line('9'); 
        
    END LOOP;
        
END LOOP;
--dbms_output.put_line('1');
fetch_backorder_impact;

p_error_mesg := l_error_msg || '~' || 'Complete.';
commit;

EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line('Error in Order: '||l_error_order);
dbms_output.put_line('Error Message: '||SQLERRM);
p_error_mesg := l_error_msg || '~' || 'Procedure fetch_revenue_data failed due to : '||SQLERRM;
error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
			, p_process_name  => 'Main'
			, p_value1        =>  l_error_order
			, p_value2        =>  NULL
			, p_value3        =>  NULL
			, p_value4        =>  NULL
			, p_value5        =>  NULL
			, p_error_message =>  SQLERRM
			, p_error_date    =>  SYSDATE
		  );

END fetch_revenue_data;

FUNCTION get_cutoff_date
      RETURN VARCHAR2 IS
      l_cutoff_date           VARCHAR2(20) := NULL;
      l_flex_value            VARCHAR2(30) := NULL;
      l_description_value     VARCHAR2(10) := NULL;
      l_default_cutoff_date VARCHAR2(20) := NULL;
   BEGIN
      l_cutoff_date  := NULL;
      l_default_cutoff_date := NULL;

      SELECT TO_CHAR(TRUNC (LAST_DAY (SYSDATE)),'DD-MON-RRRR')
      INTO l_default_cutoff_date
      FROM DUAL;

      BEGIN
         
         SELECT cvt.VAL1, cvt.VAL2
         INTO l_flex_value, l_description_value
         FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt   
         WHERE ct.cd_id = cvt.cd_id 
         AND ct.cd_name = 'OM_REV_FORECASTING_CUTOFF_DATES' 
         AND cvt.VAL1 = (SELECT TO_CHAR(SYSDATE, 'MON-RRRR') FROM DUAL);
                                 
      EXCEPTION
          WHEN NO_DATA_FOUND  THEN
            l_cutoff_date  := l_default_cutoff_date;
       WHEN OTHERS THEN
            l_cutoff_date  := NULL;
      END;

      IF l_description_value IS NULL THEN
    l_cutoff_date  := l_default_cutoff_date;
      ELSE
      l_cutoff_date := l_description_value||'-'||l_flex_value;
      END IF;

      RETURN l_cutoff_date;
   END get_cutoff_date;
   
   FUNCTION get_eta_date (p_item VARCHAR2
                         ,p_org  VARCHAR2)
      RETURN VARCHAR2 IS
      l_eta_date     VARCHAR2 (30) := NULL;
   BEGIN
      l_eta_date       := NULL;

      BEGIN
       
         SELECT cvt.VAL3
         INTO l_eta_date
         FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt   
         WHERE ct.cd_id = cvt.cd_id 
         AND ct.cd_name = 'OM_REV_FORECASTING_CUSA_ETA' 
         AND cvt.VAL1 = nvl(p_item, 'XXXX')
         AND cvt.VAL2 = nvl(p_org, 'XXXX')
         AND ROWNUM < 2;
         
      EXCEPTION
         WHEN OTHERS THEN
          BEGIN
                 SELECT cvt.VAL3
                 INTO l_eta_date
                 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt   
                 WHERE ct.cd_id = cvt.cd_id 
                 AND ct.cd_name = 'OM_REV_FORECASTING_CUSA_ETA' 
                 AND cvt.VAL1 = nvl(p_item, 'XXXX')
                 AND ROWNUM < 2;
                 
          EXCEPTION
             WHEN OTHERS THEN
                l_eta_date       := NULL;
          END;
      END;

      RETURN l_eta_date;
   END;
   
 PROCEDURE fetch_backorder_impact
 IS
 
 l_bo_lines                        NUMBER := 0;
 l_essential_items_count           NUMBER := 0;
 l_totalorder_backorder_amt        NUMBER := 0;
 l_total_backorder_impact          NUMBER := 0;
 l_max_ess_ivn_avail_date         DATE;
 
 CURSOR cur_orders_info IS
         SELECT distinct order_number, header_status
           FROM canon_E427_rev_forecast_tbl a
          WHERE 1 = 1
            AND upper(header_status) NOT IN ('CLOSED', 'CANCELLED');
            
 CURSOR cur_backorder_lineref_items (x_order_num VARCHAR2) IS
         SELECT DISTINCT order_number
                        ,line_reference
                    FROM canon_E427_rev_forecast_tbl
                   WHERE 1 = 1
                     AND NVL (upper(line_status), '-ZZZZ') = 'BACK ORDER'
                     AND line_reference IS NOT NULL
                     AND order_number = x_order_num;
                     
 CURSOR cur_backorder_noref_items (x_order_num VARCHAR2) IS
         SELECT DISTINCT order_number
                        ,line_number
                    FROM canon_E427_rev_forecast_tbl
                   WHERE 1 = 1
                     AND NVL (upper(line_status), '-ZZZZ') = 'BACK ORDER'
                     AND line_reference IS NULL
                     AND order_number = x_order_num;
            
            
 BEGIN
  FOR i IN cur_orders_info
  LOOP
    l_bo_lines       := 0;
    
    BEGIN
       SELECT COUNT (1)
         INTO l_bo_lines
         FROM canon_E427_rev_forecast_tbl a
        WHERE order_number = i.order_number
          AND upper(NVL(line_status, '-ZZZ')) = 'BACK ORDER';
          --AND TRUNC (backordered_date) + get_turnaround_time (a.ship_from_org_id) <= TRUNC (SYSDATE);
    EXCEPTION
       WHEN OTHERS THEN
          l_bo_lines := 0;
    END;
    
    l_totalorder_backorder_amt := 0;
    l_total_backorder_impact := 0;
    l_max_ess_ivn_avail_date := NULL;

    IF l_bo_lines > 0 THEN
    BEGIN
    
        FOR j IN cur_backorder_lineref_items (i.order_number)
        LOOP
            l_essential_items_count := 0;
            
            BEGIN
                SELECT COUNT (1)
                  INTO l_essential_items_count
                  FROM canon_E427_rev_forecast_tbl
                 WHERE 1 = 1
                   AND order_number = j.order_number
                   AND line_reference = j.line_reference
                   AND merchandise_type != '11'
                   AND NVL (essential_item, '-Z') IN ('C', 'E')
                   AND NVL (upper(line_status), '-ZZZZ') = 'BACK ORDER';
            EXCEPTION
                WHEN OTHERS THEN
                   l_essential_items_count := 0;
            END;
            
            IF l_essential_items_count > 0 THEN
                
                BEGIN
                   SELECT MAX (inventory_available_date)
                     INTO l_max_ess_ivn_avail_date
                     FROM canon_E427_rev_forecast_tbl
                    WHERE 1 =1 
                      AND order_number = j.order_number
                      AND line_reference = j.line_reference
                      AND merchandise_type != '11'
                      AND NVL (essential_item, '-Z') IN ('C', 'E')
                      AND NVL (upper(line_status), '-ZZZZ') = 'BACK ORDER';
                END;

                BEGIN
                
                    UPDATE canon_E427_rev_forecast_tbl tmp
                      SET totalorder_backorder_impact =  ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                         ,critical_config_bo_impact =   ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                         ,non_essential_config_bo_impact = 0
						 ,total_backorder_impact = ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                    WHERE 1 = 1
                      AND order_number = j.order_number
                      AND line_reference = j.line_reference
                      AND merchandise_type != '11'
                      AND EXISTS (
                             SELECT 1
                               FROM canon_E427_rev_forecast_tbl
                              WHERE order_number = tmp.order_number
                                AND line_reference = tmp.line_reference
                                AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER');
                                
                
                    UPDATE canon_E427_rev_forecast_tbl tmp
                      SET future_mth_backorder_impact =
                         CASE
                            WHEN (TRUNC(NVL (warehouse_scheduled_date, salesrep_requested_date)) >  TRUNC (LAST_DAY(SYSDATE))) 
                             THEN   ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                            ELSE 0
                         END
                    WHERE 1 = 1
                      AND order_number = j.order_number
                      AND line_reference = j.line_reference
                      AND merchandise_type != '11'
                         AND EXISTS (
                             SELECT 1
                               FROM canon_E427_rev_forecast_tbl
                              WHERE order_number = tmp.order_number
                                AND line_reference = tmp.line_reference
                                AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER');
                                
                                
                    UPDATE canon_E427_rev_forecast_tbl tmp
                      SET eta_based_future_mth_bo_impact = ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                    WHERE 1 = 1
                      AND order_number = j.order_number
                      AND line_reference = j.line_reference
                      AND merchandise_type != '11'
                      AND TRUNC (NVL (warehouse_scheduled_date, salesrep_requested_date)) <=  TRUNC (LAST_DAY (SYSDATE))
                      AND EXISTS (
                             SELECT 1
                               FROM canon_E427_rev_forecast_tbl
                              WHERE order_number = tmp.order_number
                                AND line_reference = tmp.line_reference
                                AND NVL (essential_item, '-Z') IN ('C', 'E')
                                AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER'
                                AND TRUNC (NVL (l_max_ess_ivn_avail_date, SYSDATE)) > TRUNC (LAST_DAY (SYSDATE)));
                                
                    UPDATE canon_E427_rev_forecast_tbl tmp
                      SET eta_based_future_mth_bo_impact = 0
                    WHERE 1 = 1
                      AND order_number = j.order_number
                      AND line_reference = j.line_reference
                      AND merchandise_type != '11'
                      AND EXISTS (
                             SELECT 1
                               FROM canon_E427_rev_forecast_tbl
                              WHERE order_number = tmp.order_number
                                AND line_reference = tmp.line_reference
                                AND NVL (essential_item, '-Z') IN ('C', 'E')
                                AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER'
                                AND TRUNC(NVL (l_max_ess_ivn_avail_date, SYSDATE)) <=  TRUNC (LAST_DAY (SYSDATE)));
					
                    UPDATE canon_E427_rev_forecast_tbl tmp
                      SET currunt_mth_backorder_impact =
                         CASE
                            WHEN (TRUNC(NVL (warehouse_scheduled_date, salesrep_requested_date)) >  TRUNC(LAST_DAY (SYSDATE))) 
                             THEN 0
                            ELSE   
                             NVL (totalorder_backorder_impact, 0) - NVL (future_mth_backorder_impact ,0) - NVL(eta_based_future_mth_bo_impact, 0)
                         END
                    WHERE 1 = 1
                      AND order_number = j.order_number
                      AND line_reference = j.line_reference
                      AND merchandise_type != '11'
                      AND EXISTS (
                             SELECT 1
                               FROM canon_E427_rev_forecast_tbl
                              WHERE order_number = tmp.order_number
                                AND line_reference = tmp.line_reference
                                AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER');
                                
                
                    UPDATE canon_E427_rev_forecast_tbl tmp
                              SET currunt_mth_backorder_impact = 0
                                 ,future_mth_backorder_impact = 0
                            WHERE 1 = 1
                              AND order_number = j.order_number
                              AND line_reference = j.line_reference
                              AND merchandise_type != '11'
                              AND NOT EXISTS (
                                     SELECT 1
                                       FROM canon_E427_rev_forecast_tbl
                                      WHERE order_number = tmp.order_number
                                        AND line_reference = tmp.line_reference
                                       AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER');
                                       
                EXCEPTION
                   WHEN OTHERS THEN
                      null;
                END;
                        
            ELSIF l_essential_items_count = 0 THEN
                       

                    UPDATE canon_E427_rev_forecast_tbl tmp
                       SET totalorder_backorder_impact = ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                          ,critical_config_bo_impact = 0
                          ,non_essential_config_bo_impact = ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
						  ,total_backorder_impact = ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                    WHERE 1 = 1
                       AND order_number = j.order_number
                       AND line_reference = j.line_reference
                       AND merchandise_type != '11'
                       AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER';    
                       

                    UPDATE canon_E427_rev_forecast_tbl tmp
                    SET future_mth_backorder_impact =
                      CASE
                         WHEN (TRUNC (NVL (warehouse_scheduled_date, salesrep_requested_date)) >  TRUNC (LAST_DAY (SYSDATE))) 
                          THEN ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                         ELSE 0
                      END
                    WHERE 1 = 1
                       AND order_number = j.order_number
                       AND line_reference = j.line_reference
                       AND merchandise_type != '11'
                       AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER'
                       AND NVL (inventory_available_date, SYSDATE) <=  TRUNC(LAST_DAY (SYSDATE));
                       
                       
                    UPDATE canon_E427_rev_forecast_tbl tmp
                    SET eta_based_future_mth_bo_impact =
                      CASE
                         WHEN (TRUNC (NVL (warehouse_scheduled_date, salesrep_requested_date)) <= TRUNC (LAST_DAY (SYSDATE)))
                                AND (TRUNC (NVL (inventory_available_date, SYSDATE)) > TRUNC (LAST_DAY (SYSDATE))) 
                          THEN ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                         ELSE 0
                      END
                    WHERE 1 = 1
                     AND 1 = 1
                     AND order_number = j.order_number
                     AND line_reference = j.line_reference
                     AND merchandise_type != '11'
                     AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER';
                     
                     
                     
                    UPDATE canon_E427_rev_forecast_tbl tmp
                    SET currunt_mth_backorder_impact =
                      CASE
                         WHEN (TRUNC (NVL (warehouse_scheduled_date, salesrep_requested_date)) >  TRUNC (LAST_DAY(SYSDATE))) 
                          THEN 0
                         ELSE  
                          NVL (totalorder_backorder_impact, 0) - (  NVL (future_mth_backorder_impact, 0) + NVL(eta_based_future_mth_bo_impact, 0))
                      END
                    WHERE 1 = 1
                       AND order_number = j.order_number
                       AND line_reference = j.line_reference
                       AND merchandise_type != '11'
                       AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER';

                    UPDATE canon_E427_rev_forecast_tbl tmp
                       SET totalorder_backorder_impact = 0
                          ,currunt_mth_backorder_impact = 0
                          ,future_mth_backorder_impact = 0
                          ,eta_based_future_mth_bo_impact = 0
						  ,total_backorder_impact = 0
                     WHERE 1 = 1
                       AND order_number = j.order_number
                       AND line_reference = j.line_reference
                       AND merchandise_type != '11'
                       AND NVL (upper(line_status), '-ZZZZ') !=  'BACK ORDER';
                    
                        
            END IF; --l_essential_items_count > 0

        END LOOP;
        
  
        FOR k IN cur_backorder_noref_items (i.order_number)
        LOOP
            UPDATE canon_E427_rev_forecast_tbl
            SET totalorder_backorder_impact =    ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
			,total_backorder_impact = ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
            WHERE order_number = k.order_number
            AND line_number = k.line_number
            AND merchandise_type != '11';
            
                                    
            UPDATE canon_E427_rev_forecast_tbl tmp
            SET future_mth_backorder_impact =
               CASE
                  WHEN (TRUNC (NVL (warehouse_scheduled_date, salesrep_requested_date)) >  TRUNC (LAST_DAY (SYSDATE))) 
                   THEN ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                  ELSE 0
               END
            WHERE order_number = k.order_number
            AND line_number = k.line_number
            AND merchandise_type != '11'
            AND NVL (inventory_available_date, SYSDATE) <=  TRUNC (LAST_DAY (SYSDATE));        

            
            UPDATE canon_E427_rev_forecast_tbl tmp
            SET eta_based_future_mth_bo_impact =
               CASE
                  WHEN (TRUNC (NVL (warehouse_scheduled_date, salesrep_requested_date)) <=  TRUNC (LAST_DAY (SYSDATE)))
                    AND (TRUNC (NVL (inventory_available_date, SYSDATE)) > TRUNC (LAST_DAY (SYSDATE))) 
                   THEN ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
                  ELSE 0
               END
            WHERE order_number = k.order_number
            AND line_number = k.line_number
            AND merchandise_type != '11';
            
            
            UPDATE canon_E427_rev_forecast_tbl tmp
            SET currunt_mth_backorder_impact =
                   CASE
                      WHEN (TRUNC (NVL (warehouse_scheduled_date, salesrep_requested_date)) >  TRUNC (LAST_DAY (SYSDATE))) 
                       THEN 0
                      ELSE NVL(totalorder_backorder_impact, 0) - (  NVL (future_mth_backorder_impact, 0) + NVL (eta_based_future_mth_bo_impact ,0))
                   END
            WHERE order_number = k.order_number
            AND line_number = k.line_number
            AND merchandise_type != '11';
                        
        END LOOP;
        
    END;

       l_total_backorder_impact := 0;

        BEGIN
          SELECT SUM (NVL (totalorder_backorder_impact, 0))
            INTO l_total_backorder_impact
            FROM canon_E427_rev_forecast_tbl
           WHERE order_number = i.order_number
             AND merchandise_type != '11';
        EXCEPTION
          WHEN OTHERS THEN
             l_total_backorder_impact := 0;
        END;


    END IF;
    
    IF l_bo_lines = 0 THEN
               BEGIN
                  UPDATE canon_E427_rev_forecast_tbl a
                     SET totalorder_backorder_impact = 0
                        ,critical_config_bo_impact = 0
                        ,non_essential_config_bo_impact = 0
                        ,currunt_mth_backorder_impact = 0
                        ,future_mth_backorder_impact = 0
                        ,eta_based_future_mth_bo_impact = 0
                        ,line_backorder_amount = 0
						,total_backorder_impact = 0
						,critical_lines_bo_value = 0
						,non_essential_items_bo_value = 0
                   WHERE 1 = 1
                     AND order_number = i.order_number
                     AND merchandise_type != '11';

               END;
    END IF;

 END LOOP; 

    UPDATE canon_E427_rev_forecast_tbl a
    SET line_backorder_amount =
       CASE
          WHEN  NVL(upper(line_status), '-ZZZZ') =  'BACK ORDER' 
           THEN ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * salescredit_percent/100), 2)
          ELSE 0
       END
    WHERE 1 = 1
    AND merchandise_type != '11'
    AND upper(header_status) = 'BOOKED'; 
	
	                                
	UPDATE canon_E427_rev_forecast_tbl tmp
	  SET critical_lines_bo_value =
		 CASE
		 WHEN essential_item IS NOT NULL
		 THEN
		   ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
		 ELSE
		   0
		END
	WHERE 1 = 1 
	AND merchandise_type != '11'
    AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER';
	
	UPDATE canon_E427_rev_forecast_tbl tmp
	  SET non_essential_items_bo_value =
		 CASE
		 WHEN essential_item IS NULL
		 THEN
		   ROUND((NVL (quantity_ordered, 0) * NVL (unit_selling_price, 0) * nvl(salescredit_percent, 100)/100), 2)
		 ELSE
		   0
		END
	WHERE 1 = 1 
	AND merchandise_type != '11'
    AND NVL (upper(line_status), '-ZZZZ') =  'BACK ORDER';
	

        
END fetch_backorder_impact;
 
 FUNCTION get_turnaround_time (p_org VARCHAR2)
      RETURN NUMBER IS
      l_turnaround_days     NUMBER := 0;
   BEGIN
      l_turnaround_days := 0;

      BEGIN
      
         SELECT TO_NUMBER(cvt.VAL2)
         INTO l_turnaround_days
         FROM canon_s21_cd_tbl ct, 
              canon_s21_cd_val_tbl cvt   
         WHERE ct.cd_id = cvt.cd_id 
         AND ct.cd_name = 'OM_REV_FORECASTING_INTRANSIT_TIMES' 
         AND cvt.VAL1 = p_org;
     
      EXCEPTION
         WHEN OTHERS THEN
            l_turnaround_days := 0;
      END;

      RETURN l_turnaround_days;
   END;
   
PROCEDURE calc_component_usp(p_order_num       VARCHAR2,
                               p_line_num       VARCHAR2,
                               p_line_sub_num VARCHAR2,
                               p_line_usp     OUT NUMBER,
                               p_weighted_avg OUT NUMBER,
                               p_kit_line_num OUT VARCHAR2,
                               p_kit_line_sub_num OUT VARCHAR2)
                               
IS

CURSOR cur_kit_lines (x_cpo_ord_num VARCHAR2, x_line_num VARCHAR2, x_line_sub_num VARCHAR2)
IS
SELECT DISTINCT cd.cpo_dtl_line_num,
       cd.cpo_dtl_line_sub_num,
       cd.cpo_ord_num,
       cd.mdse_cd ordered_item
FROM cpo_dtl cd, mdse m, coa_proj cp
WHERE cd.mdse_cd = m.mdse_cd
AND m.glbl_cmpy_cd = g_glbl_cmpy_cd
AND m.coa_mdse_tp_cd = cp.coa_proj_cd
AND cp.coa_proj_cd in (11, 31)
AND m.mdse_tp_cd = 3
AND cd.cpo_ord_num = x_cpo_ord_num
AND cd.cpo_dtl_line_num = x_line_num
AND cd.cpo_dtl_line_sub_num = x_line_sub_num;
     
l_merch_type NUMBER;
l_accy_lines NUMBER;
l_kit_usp NUMBER;     
l_ref_line_num VARCHAR2(20);
l_ref_line_sub_num VARCHAR2(20);
l_equipment_line NUMBER;

BEGIN
    
    BEGIN
        SELECT ref_cpo_dtl_line_num, ref_cpo_dtl_line_sub_num
        INTO l_ref_line_num, l_ref_line_sub_num
        FROM cpo_dtl
        WHERE cpo_ord_num = p_order_num
        AND cpo_dtl_line_num = p_line_num
        AND cpo_dtl_line_sub_num = p_line_sub_num;
    EXCEPTION
    WHEN NO_DATA_FOUND 
    THEN 
        l_ref_line_num:= NULL;
        l_ref_line_sub_num := NULL;
    WHEN OTHERS THEN
        l_ref_line_num:= NULL;
        l_ref_line_sub_num := NULL;
        error_log (   p_program_name  => 'CANON_E427_REV_FORECAST_PKG'
                , p_process_name  => 'CUR_KIT_LINES'
                , p_value1        =>  p_order_num
                , p_value2        =>  p_line_num
                , p_value3        =>  p_line_sub_num
                , p_value4        =>  NULL
                , p_value5        =>  NULL
                , p_error_message =>  SQLERRM
                , p_error_date      =>  SYSDATE
              );
    END;
    
    l_kit_usp := 0;
    
    BEGIN
        SELECT cd.ent_deal_net_unit_prc_amt
        INTO l_kit_usp
        FROM cpo_dtl cd, mdse m, mdse_tp mt
        WHERE cd.mdse_cd = m.mdse_cd
        AND m.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND mt.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND cd.cpo_ord_num = p_order_num
        AND cd.cpo_dtl_line_num = nvl(l_ref_line_num, 'XXX')
        AND cd.cpo_dtl_line_sub_num = nvl(l_ref_line_sub_num, 'XXX')
        AND m.mdse_tp_cd = mt.mdse_tp_cd
        AND mt.mdse_tp_nm = 'Kit';
    EXCEPTION
         WHEN OTHERS
         THEN
            l_kit_usp := 0;
    END;

    IF l_kit_usp = 0
      THEN
         p_weighted_avg := 0;
         p_line_usp := 0;
         p_kit_line_num := NULL;
         p_kit_line_sub_num := NULL;

    ELSIF l_kit_usp <> 0
      THEN
      p_kit_line_num := l_ref_line_num;
      p_kit_line_sub_num := l_ref_line_sub_num;
      FOR rec1 IN cur_kit_lines (p_order_num, l_ref_line_num, l_ref_line_sub_num)
      LOOP
      l_accy_lines := 0;
      
      BEGIN
        SELECT count(1)
        INTO l_accy_lines
        FROM cpo_dtl cd,
             mdse m,
             coa_proj cp
        WHERE cd.mdse_cd = m.mdse_cd
        AND m.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND m.coa_mdse_tp_cd = cp.coa_proj_cd
        AND cd.cpo_ord_num = p_order_num
        AND cd.ref_cpo_dtl_line_num = l_ref_line_num
        AND cd.ref_cpo_dtl_line_sub_num = l_ref_line_sub_num
        AND cp.coa_proj_cd != 10;
        
        EXCEPTION
          WHEN OTHERS
          THEN
             l_accy_lines := 0;
        END;
        
        l_equipment_line := 0;
        
        BEGIN
          SELECT count(1)
            INTO l_equipment_line
            FROM cpo_dtl cd,
                 mdse m,
                 coa_proj cp
            WHERE cd.mdse_cd = m.mdse_cd
            AND m.glbl_cmpy_cd = g_glbl_cmpy_cd
            AND m.coa_mdse_tp_cd = cp.coa_proj_cd
            AND cd.cpo_ord_num = p_order_num
            AND cd.ref_cpo_dtl_line_num = l_ref_line_num
            AND cd.ref_cpo_dtl_line_sub_num = l_ref_line_sub_num
            AND cp.coa_proj_cd = 10;
       EXCEPTION
          WHEN OTHERS
          THEN
             l_equipment_line := 0;
       END;
       
       BEGIN
           SELECT m.coa_mdse_tp_cd
           INTO l_merch_type
           FROM cpo_dtl cd, mdse m
           WHERE cd.mdse_cd = m.mdse_cd
           AND m.glbl_cmpy_cd = g_glbl_cmpy_cd
           AND cd.glbl_cmpy_cd = m.glbl_cmpy_cd
           AND cd.cpo_ord_num = p_order_num
           AND cd.cpo_dtl_line_num = p_line_num
           AND cd.cpo_dtl_line_sub_num = p_line_sub_num;
       EXCEPTION
        WHEN OTHERS THEN
        NULL;        
       END;
       
       IF l_accy_lines = 1 AND l_equipment_line != 0
               THEN
------------------------------------------------------------------------
-- IF KIT more than one accessory item split equal amount within 30%
------------------------------------------------------------------------
                  IF l_merch_type = '10'
                  THEN
                     p_line_usp := ROUND ((NVL (l_kit_usp, 0) * 0.70), 2);
                  ELSIF l_merch_type != '10'
                  THEN
                     p_line_usp := ROUND ((NVL (l_kit_usp, 0) * 0.30), 2);
                  END IF;
------------------------------------------------------------------------
-- IF KIT more than one accessory item split equal amount within 30%
------------------------------------------------------------------------
               ELSIF l_accy_lines > 1 AND l_equipment_line != 0
               THEN
                  IF l_merch_type = '10'
                  THEN
                     p_line_usp := ROUND ((NVL (l_kit_usp, 0) * 0.70), 2);
                  ELSIF l_merch_type != '10'
                  THEN
                     p_line_usp :=
                        ROUND ((  NVL (l_kit_usp, 0)
                                * ROUND ((0.30 / l_accy_lines), 4)
                               ),
                               2
                              );
                  END IF;
------------------------------------------------------------------------
-- IF KIT  doesn't have any Equipment line split equal amount within 100%
------------------------------------------------------------------------
               ELSIF l_accy_lines > 1 AND l_equipment_line = 0
               THEN
                  p_line_usp :=
                     ROUND ((  NVL (l_kit_usp, 0)
                             * ROUND ((1.00 / l_accy_lines), 4)
                            ),
                            2
                           );
               END IF;
            
      END LOOP;
    END IF;
    
END calc_component_usp;

PROCEDURE update_service_items
IS
BEGIN

    BEGIN
         UPDATE canon_E427_rev_forecast_tbl cl
            SET warehouse_scheduled_date = NULL
          WHERE warehouse_scheduled_date IS NOT NULL
            AND upper(line_status) NOT IN ('ENTERED', 'CANCELLED')
            AND EXISTS (
                        SELECT 1
                         FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, mdse_tp mt, mdse m  
                         WHERE ct.cd_id = cvt.cd_id 
                         AND m.mdse_cd = cl.ordered_item
                         AND m.mdse_tp_cd = mt.mdse_tp_cd
                         AND mt.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND ct.cd_name = 'OM_REV_FORECASTING_DLVY_DATE_EXCL_RULES' 
                         AND cvt.VAL1 LIKE 'ITEM_TYPE%'
                         AND cvt.VAL2 = upper(mt.mdse_tp_nm));
                   
         /*IF SQL%ROWCOUNT <> 0 THEN
            p_error_mesg := p_error_mesg || '~' || '(ITEM TYPE)Scheduled delivery Date was made NULL for rows '|| SQL%ROWCOUNT;
         END IF;*/
      EXCEPTION
         WHEN OTHERS THEN
            NULL;
      END;

      BEGIN
         UPDATE canon_E427_rev_forecast_tbl cl
            SET warehouse_scheduled_date = NULL
          WHERE warehouse_scheduled_date IS NOT NULL
            AND line_status NOT IN ('ENTERED', 'CANCELLED')
            AND EXISTS (
                    SELECT 1
                         FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, coa_proj cp, mdse m  
                         WHERE ct.cd_id = cvt.cd_id 
                         AND m.mdse_cd = cl.ordered_item
                         AND m.coa_mdse_tp_cd = cp.coa_proj_cd
                         AND cp.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND ct.cd_name = 'OM_REV_FORECASTING_DLVY_DATE_EXCL_RULES' 
                         AND cvt.VAL1 LIKE 'MERCH_TYPE%'
                         AND cvt.VAL2 = upper(cp.coa_proj_cd));

         /*IF SQL%ROWCOUNT <> 0 THEN
            p_error_mesg := p_error_mesg || '~' || '(MERCHANDISE TYPE)Scheduled delivery Date was made NULL for rows '|| SQL%ROWCOUNT;
         END IF;*/
      EXCEPTION
         WHEN OTHERS THEN
            NULL;
      END;


END update_service_items;

PROCEDURE error_log ( p_program_name        VARCHAR2
                    , p_process_name          VARCHAR2
                    , p_value1              VARCHAR2  DEFAULT NULL
                    , p_value2              VARCHAR2  DEFAULT NULL
                    , p_value3              VARCHAR2  DEFAULT NULL
                    , p_value4              VARCHAR2  DEFAULT NULL
                    , p_value5              VARCHAR2  DEFAULT NULL
                    , p_error_message       VARCHAR2
                    , p_error_date            DATE
                    )
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO canon_e427_errors (  program_name
									, process_name
									, key1
									, key2
									, key3
									, key4
									, key5
									, error_msg
									, error_date
									)
                     VALUES    (
                                   p_program_name
                                 , p_process_name
                                 , p_value1
                                 , p_value2
                                 , p_value3
                                 , p_value4
                                 , p_value5
                                 , p_error_message
                                 , p_error_date
                                );
      COMMIT;
   EXCEPTION
     WHEN OTHERS
     THEN
        null;
 END error_log;
 
END CANON_E427_REV_FORECAST_PKG;
/

