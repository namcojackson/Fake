CREATE OR REPLACE PACKAGE CANON_E580_ITT_UTIL_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_ITT_UTIL_PKG.sql                                                                                            --
-- Created On :   08/11/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to all the Search actions in ITT workbench application (E580)         --
--                Modified all the program units to port the application from Oracle to S21.                                                --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            11-Aug-2015    Initial                                                                             --
-- 2.0        Balaji Gowravaram			 09-Mar-2017	Multiple changes for S21 functionalities                                            --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --

   TYPE ref_order_details IS REF CURSOR;
   TYPE ref_header_details IS REF CURSOR;
   TYPE ref_line_details IS REF CURSOR;
   TYPE itt_err_tbl_typ IS TABLE OF canon_e580_itt_errors_tbl%ROWTYPE INDEX BY BINARY_INTEGER;
   TYPE itt_dsql_tbl_typ IS TABLE OF canon_e580_dynsql_tbl%ROWTYPE INDEX BY BINARY_INTEGER;
    
   g_glbl_cmpy_cd    VARCHAR2(10):='ADB';
   l_itt_err_tbl     CANON_E580_ITT_UTIL_PKG.itt_err_tbl_typ;
   l_itt_dsql_tbl    CANON_E580_ITT_UTIL_PKG.itt_dsql_tbl_typ;
   l_debug           NUMBER := 0;
   
   PROCEDURE log_dynamic_sql( p_dsql_tbl IN itt_dsql_tbl_typ );
   
   PROCEDURE itt_error_log( p_err_tbl IN itt_err_tbl_typ );
    
   PROCEDURE itt_header_details( p_itt_number         IN VARCHAR2, 
                                 p_so_number          IN VARCHAR2, 
                                 p_customer_name      IN VARCHAR2, 
                                 p_ship_to_account    IN VARCHAR2, 
                                 p_itt_status         IN VARCHAR2,
                                 p_itt_processor      IN VARCHAR2,
                                 p_sales_rep          IN VARCHAR2,
                                 p_sales_zone         IN VARCHAR2,
                                 p_sales_branch       IN VARCHAR2,
                                 p_cusa_po_number     IN VARCHAR2,
                                 p_transaction_type   IN VARCHAR2,
                                 p_dealer_name        IN VARCHAR2,
                                 p_delivery_date_from IN DATE,
                                 p_delivery_date_to   IN DATE,
                                 p_include_closed_itt IN VARCHAR2,
                                 p_from_record        IN NUMBER,
                                 p_to_record          IN NUMBER,
                                 p_order_by           IN VARCHAR2,
                                 p_asc_desc           IN VARCHAR2,
                                 x_order_details      OUT ref_order_details,
                                 x_count              OUT NUMBER
                               );
                                     
   PROCEDURE itt_line_details( p_itt_number    IN   VARCHAR2,
                               p_so_number     IN   VARCHAR2,
                               p_from_record   IN   NUMBER,
                               p_to_record     IN   NUMBER,
                               p_order_by      IN   VARCHAR2,
                               p_asc_desc      IN   VARCHAR2,
                               x_line_details1 OUT  ref_order_details,
                               x_line_details2 OUT  ref_order_details,
                               x_count         OUT  NUMBER
                             );
                              
   PROCEDURE itt_timestamp_details(  p_itt_number        IN  VARCHAR2
                                    ,x_timestamp_details OUT ref_order_details
                                  );

   PROCEDURE itt_notes_details (  p_itt_number    IN  VARCHAR2
                                 ,p_from_record   IN  NUMBER
                                 ,p_to_record     IN  NUMBER
                                 ,p_order_by      IN  VARCHAR2
                                 ,p_asc_desc      IN  VARCHAR2
                                 ,x_notes_details OUT ref_order_details
                                 ,x_count         OUT NUMBER
                              );

   PROCEDURE itt_pricing_details( p_itt_number      IN  VARCHAR2,
                                  x_pricing_details OUT ref_order_details,
                                  x_count           OUT NUMBER
                                );
                                    
   PROCEDURE itt_po_pdf_hist_details( p_itt_number          IN  VARCHAR2, 
                                      x_po_pdf_hist_details OUT ref_order_details
                                    );
                                     
   PROCEDURE itt_sub_inv_lov(  p_inv_org_code IN  VARCHAR2
                              ,x_sub_inv_lov  OUT ref_order_details 
                            );

   --LOV procedures
   PROCEDURE itt_cust_account_lov( p_cust_name IN VARCHAR2, 
                                   p_cust_number IN  VARCHAR2, 
                                   x_cust_details OUT ref_order_details
                                 );                                           
   PROCEDURE itt_status_lov(x_status_name OUT ref_order_details);
   PROCEDURE itt_user_name(  p_user_name   IN  VARCHAR2
                            ,p_person_name OUT VARCHAR2);

   PROCEDURE itt_order_processor_lov(  p_processor_name IN VARCHAR2
                                      ,x_resources_name OUT ref_order_details);

   PROCEDURE itt_dealer_lov( p_dealer_name  IN  VARCHAR2,
                             x_dealers_name OUT ref_order_details);
   PROCEDURE itt_line_types( x_line_types OUT ref_order_details );
   PROCEDURE itt_cmap_lov( x_cmap OUT ref_order_details );
   PROCEDURE itt_product_source_lov(x_product_sourced_by OUT ref_order_details);
   PROCEDURE itt_bill_cycle_lov(x_bill_cycle OUT ref_order_details);
   PROCEDURE itt_contract_type_lov(x_contract_type OUT ref_order_details);
   PROCEDURE itt_plan_type_lov(x_plan_type OUT ref_order_details);
   PROCEDURE itt_salesrep_lov( p_sales_rep IN VARCHAR2, 
                               x_salesrep_lov OUT ref_order_details);
   PROCEDURE itt_sales_branch_lov( p_sales_branch IN VARCHAR2, 
                                   x_sales_branch OUT ref_order_details);
   PROCEDURE itt_sales_zone_lov(x_sales_zone OUT ref_order_details);
   PROCEDURE itt_mail_invoices_to_lov(x_mail_invoices_to OUT ref_order_details);
   
   PROCEDURE itt_dealer_ship_to_cna_code (p_dealer_ship_to_cna_code IN VARCHAR2, x_dealer_ship_to_cna_code OUT ref_order_details);
   
   PROCEDURE itt_schdl_deliv_dt_reason_code(x_schdl_deliv_dt_reason_code OUT ref_order_details);
   PROCEDURE itt_edit_mode_check( p_user_name IN VARCHAR2, 
                                  p_edit_mode OUT VARCHAR2);
   PROCEDURE itt_lines_on_hold( p_itt_number        IN  VARCHAR2
                               ,x_lines_on_hold_lov OUT ref_order_details
							   ,x_svc_mach_config   OUT ref_order_details);

   PROCEDURE itt_markview_attached_docs(  p_itt_number IN VARCHAR2
                                         ,p_user_name IN VARCHAR2
                                         ,x_markview_attached_docs OUT ref_order_details
                                       );
   PROCEDURE itt_deliv_dt_reschdl_history( p_itt_number               IN  VARCHAR2
                                          ,p_line_number              IN  VARCHAR2
                                          ,x_deliv_dt_reschdl_history OUT ref_order_details
                                         );
   ---Update/Save procedures...
   PROCEDURE itt_header_update (   p_so_number               IN VARCHAR2
                                 , p_itt_number              IN VARCHAR2
                                 , p_user_name               IN VARCHAR2
                                 , p_itt_status              IN VARCHAR2
                                 , p_itt_admin_owner         IN VARCHAR2
                                 , p_dealer                  IN VARCHAR2
                                 , p_dealer_whse_code        IN VARCHAR2
                                 , p_dealer_address_line1    IN VARCHAR2
                                 , p_dealer_address_line2    IN VARCHAR2
                                 , p_dealer_address_line3    IN VARCHAR2
                                 , p_dealer_city             IN VARCHAR2
                                 , p_dealer_state            IN VARCHAR2
                                 , p_dealer_zip              IN VARCHAR2
                                 , p_dealer_country          IN VARCHAR2
                                 , p_dealer_contact_number   IN VARCHAR2
                                 , p_dealer_email            IN VARCHAR2
                                 , p_cmap_yes_no             IN VARCHAR2
                                 , p_product_sourced_by      IN VARCHAR2
                                 , p_dealer_contact          IN VARCHAR2
                                 , p_mail_invoices_to        IN VARCHAR2
                                 , p_cust_contact_name       IN VARCHAR2
                                 , p_cust_contact_phone      IN VARCHAR2
                                 , p_cust_contact_email      IN VARCHAR2
                                 , p_cust_contact_fax        IN VARCHAR2
                                 , p_dealer_ship_to_cna_code IN VARCHAR2
                                 , p_vendor_id               IN NUMBER
                                 , p_vendor_site_id          IN NUMBER
                                 , p_vendor_contact_id       IN NUMBER
                                 , p_prnt_vnd_cd             IN VARCHAR2
                               );
                               
   PROCEDURE itt_delivery_conf_date_update (  p_itt_number              IN  VARCHAR2
                                            , p_line_number             IN  VARCHAR2
                                            , p_user_name               IN  VARCHAR2
                                            , p_delivery_scheduled_date IN  DATE
                                            , p_schedule_code           IN  VARCHAR2
                                            , p_return_code             OUT VARCHAR2
                                            , p_return_message          OUT VARCHAR2
                                           );

   PROCEDURE itt_goods_line_update (  p_itt_number              IN  VARCHAR2
                                    , p_line_number             IN  VARCHAR2
                                    , p_user_name               IN  VARCHAR2
                                    , p_itt_status              IN  VARCHAR2
                                    , p_delivery_scheduled_date IN  DATE
                                    , p_install_credit          IN  NUMBER
                                    , p_finder_fee              IN  NUMBER
                                    , p_serial_number           IN  VARCHAR2
                                    , p_reason_code             IN  VARCHAR2
                                    , p_po_number               IN  VARCHAR2
                                    , p_product_sourced_by      IN  VARCHAR2
                                    , p_item_purchase_price     IN  NUMBER
                                    , p_return_code             OUT VARCHAR2
                                    , p_return_message          OUT VARCHAR2
                                   );
                                   
   PROCEDURE itt_expense_line_insert (  p_so_number           IN VARCHAR2
                                      , p_itt_number          IN VARCHAR2
                                      , p_user_name           IN VARCHAR2
                                      , p_item                IN VARCHAR2
                                      , p_item_description    IN VARCHAR2
                                      , p_ordered_quantity    IN NUMBER
                                      , p_item_purchase_price IN NUMBER 
                                      , p_install_credit      IN NUMBER
                                      , p_finder_fee          IN NUMBER
                                     );

   PROCEDURE itt_expense_line_update(  p_itt_number          IN VARCHAR2
                                     , p_line_number         IN VARCHAR2
                                     , p_user_name           IN VARCHAR2
                                     , p_item                IN VARCHAR2
                                     , p_item_description    IN VARCHAR2
                                     , p_ordered_quantity    IN NUMBER
                                     , p_item_purchase_price IN NUMBER 
                                     , p_install_credit      IN NUMBER
                                     , p_finder_fee          IN NUMBER
                                     , p_po_number           IN VARCHAR2
                                    );
                                    
   PROCEDURE itt_notes_insert (   p_so_number    IN VARCHAR2
                                , p_itt_number   IN VARCHAR2
                                , p_user_name    IN VARCHAR2
                                , p_notes        IN VARCHAR2
                                , p_add_to_popdf IN VARCHAR2
                              );

   PROCEDURE itt_notes_update (   p_so_number      IN VARCHAR2
                                , p_itt_number     IN VARCHAR2
                                , p_user_name      IN VARCHAR2
                                , p_seq_number     IN NUMBER
                                , p_add_to_popdf   IN VARCHAR2
                               );
                               
   PROCEDURE itt_timestamp_update (   p_itt_number               IN VARCHAR2
                                    , p_user_name                IN VARCHAR2
                                    , p_po_date                  IN DATE 
                                    , p_po_accepted_date_by_dlr  IN DATE
                                    , p_po_sent_date_to_cusa     IN DATE 
                                    , p_shipped_date_from_cusa   IN DATE 
                                    , p_equip_arrive_at_dlr_date IN DATE 
                                    , p_pod_rcvd_from_dlr_date   IN DATE
                                    , p_cna_po_aprvd_by_dlr_date IN DATE
                                  );
                                  
   PROCEDURE itt_update_pricing (  p_itt_number         IN VARCHAR2,
                                   p_user_name          IN VARCHAR2,
                                   p_equip_model        IN VARCHAR2,
                                   p_meter_type         IN VARCHAR2,
                                   p_contract_type      IN VARCHAR2,
                                   p_overage_rate       IN NUMBER,
                                   p_plan_type          IN VARCHAR2,
                                   p_term               IN NUMBER,
                                   p_base_price         IN NUMBER,
                                   p_base_bill_cycle    IN VARCHAR2,
                                   p_overage_bill_cycle IN VARCHAR2,
                                   p_copy_inclusion     IN NUMBER,
                                   p_multiplier         IN NUMBER
								   --p_svc_meter_pkg      IN VARCHAR2,
								   --p_hard_meter_counter IN VARCHAR2,
								   --p_order_number		IN VARCHAR2
                                );
                                
   PROCEDURE itt_po_pdf_history ( p_package_name     VARCHAR2,
                                  p_procedure_name   VARCHAR2,
                                  p_itt_number       VARCHAR2,
                                  p_user_name        VARCHAR2,
                                  p_value1           VARCHAR2,
                                  p_value2           VARCHAR2,
                                  p_value3           VARCHAR2,
                                  p_value4           VARCHAR2,
                                  p_value5           VARCHAR2,
                                  p_value6           VARCHAR2,
                                  p_status_message   VARCHAR2
                                );

   PROCEDURE itt_create_po_pdf (  p_itt_number          IN  VARCHAR2,
								  p_line_number         IN  VARCHAR2,
                                  p_user_name           IN  VARCHAR2,
                                  x_po_header_details   OUT ref_order_details,
                                  x_po_line_details     OUT ref_order_details,
                                  x_po_maint_details    OUT ref_order_details,
                                  x_dlr_instl_comp      OUT NUMBER,
                                  x_addnl_fee           OUT NUMBER,
                                  x_sub_total           OUT NUMBER,
                                  x_addnl_instr         OUT VARCHAR2
                                );
								
   PROCEDURE get_expense_item (p_expense_item OUT VARCHAR2);
                                
   FUNCTION get_code_tbl_value( p_cd_tbl IN VARCHAR2, p_code IN VARCHAR2) RETURN VARCHAR2;
   
   FUNCTION asl_setup_exists(  p_prnt_vnd_cd IN  VARCHAR2
                              ,p_vnd_cd      IN  VARCHAR2
                              ,p_mdse_cd     IN  VARCHAR2
                              ,p_error_msg   OUT VARCHAR2
                            )
   RETURN VARCHAR2;
   
   FUNCTION get_salesrep_attr(  p_salesrep_cd  IN VARCHAR2
                               ,p_attribute    IN VARCHAR2
                             ) RETURN VARCHAR2;
                             
   FUNCTION get_salesrep_zn(  p_salesrep_cd  IN VARCHAR2) RETURN VARCHAR2;
   
   PROCEDURE itt_svc_mtr_pkg_lov(p_model_name  IN VARCHAR2,
								 x_svc_mtr_pkg OUT ref_order_details);
   
    PROCEDURE itt_meter_type_dtls (p_itt_number    IN  VARCHAR2,
								p_order_number  IN  VARCHAR2,
								p_model_name    IN  VARCHAR2,
								p_svc_pkg_name  IN  VARCHAR2);
								--x_mtr_type_dtl  OUT ref_order_details);

END CANON_E580_ITT_UTIL_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E580_ITT_UTIL_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_ITT_UTIL_PKG.sql                                                                                               --
-- Created On :   08/11/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to all the Search actions in ITT workbench application (E580)         --
--                Modified all the program units to port the application from Oracle to S21.                                                --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            11-Aug-2015    Initial                                                                             --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --

   l_canon_e580_itt_valueset       VARCHAR2(240) := 'CANON_E580_ITT_WORKBENCH';
   l_canon_e580_pricing_valueset   VARCHAR2(240) := 'CANON_E580_SRV_PRICING_VS';
   l_canon_usa_gl_branch_valueset  VARCHAR2(240) := 'USA_GL_BRANCH';
   l_error_msg                     VARCHAR2(3999) := 'No Error';
   l_username                      VARCHAR2(20)   := -1;
   l_debug_step                    NUMBER := 0;

   ------------------------------------------------------------------------------------------
   --This procedure gets invoked by other programs of ITT workbench actions and used to track
   --all the Dynamic SQL's generated in custom table canon_e580_dynsql_tbl
   ------------------------------------------------------------------------------------------   
   PROCEDURE log_dynamic_sql ( p_dsql_tbl  IN  itt_dsql_tbl_typ )
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      FORALL ins IN 1..p_dsql_tbl.COUNT
      INSERT INTO canon_e580_dynsql_tbl values p_dsql_tbl(ins);
      
      COMMIT;
   EXCEPTION
     WHEN OTHERS THEN
        l_error_msg := 'This Program terminated due to '|| SQLCODE|| ' - '|| SQLERRM;
        dbms_output.put_line(l_error_msg);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'LOG_DYNAMIC_SQL';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := 1;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := 1;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   -------------------------------------------------------------------------
   --This procedure used to track all the error messages encountered during
   --the program execution in error custom table canon_e580_itt_errors_tbl.
   -------------------------------------------------------------------------   
   PROCEDURE itt_error_log ( p_err_tbl  IN  itt_err_tbl_typ )
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      FORALL ins IN 1..p_err_tbl.COUNT
      INSERT INTO canon_e580_itt_errors_tbl values p_err_tbl(ins);

      COMMIT;
   EXCEPTION
     WHEN OTHERS THEN
        dbms_output.put_line('This Program terminated due to '|| SQLCODE|| ' - '|| SQLERRM);
   END;

   ------------------------------------------------------------------------------------------------
   --This procedure gets invoked by the Search action in ITT workbench search screen
   --Purpose: Extract all the ITT header details (SO/Customer/SalesRep/etc details) and return back
   ------------------------------------------------------------------------------------------------
   PROCEDURE itt_header_details (    p_itt_number         IN VARCHAR2,
                                     p_so_number          IN VARCHAR2,
                                     p_customer_name      IN VARCHAR2,
                                     p_ship_to_account    IN VARCHAR2,
                                     p_itt_status         IN VARCHAR2,
                                     p_itt_processor      IN VARCHAR2,
                                     p_sales_rep          IN VARCHAR2,
                                     p_sales_zone         IN VARCHAR2,
                                     p_sales_branch       IN VARCHAR2,
                                     p_cusa_po_number     IN VARCHAR2,
                                     p_transaction_type   IN VARCHAR2,
                                     p_dealer_name        IN VARCHAR2,
                                     p_delivery_date_from IN DATE,
                                     p_delivery_date_to   IN DATE,
                                     p_include_closed_itt IN VARCHAR2,
                                     p_from_record        IN NUMBER,
                                     p_to_record          IN NUMBER,
                                     p_order_by           IN VARCHAR2,
                                     p_asc_desc           IN VARCHAR2,
                                     x_order_details      OUT ref_order_details,
                                     x_count              OUT NUMBER
                                     )
   AS
      l_itt_number           VARCHAR2(80)   := p_itt_number;
      l_so_number            VARCHAR2(100)  := p_so_number;
      l_cutomer_name         VARCHAR2(240)  := p_customer_name;
      l_ship_to_account      VARCHAR2(80)   := p_ship_to_account;
      l_itt_status           VARCHAR2(32000):= p_itt_status;
      l_itt_processor        VARCHAR2(240)  := p_itt_processor;
      l_sales_rep            VARCHAR2(240)  := p_sales_rep;
      l_sales_zone           VARCHAR2(240)  := p_sales_zone;
      l_sales_branch         VARCHAR2(240)  := p_sales_branch;
      l_cusa_po_number       VARCHAR2(240)  := p_cusa_po_number;
      l_transaction_type     VARCHAR2(3000) := p_transaction_type;
      l_dealer_name          VARCHAR2(240)  := p_dealer_name;
      l_delivery_date_from   DATE           := TRUNC(p_delivery_date_from);
      l_delivery_date_to     DATE           := TRUNC(p_delivery_date_to);
      l_delivery_date        VARCHAR2(4000) := NULL;
      l_include_closed_itt   VARCHAR2(1000) := NVL(p_include_closed_itt, 'N');
      l_from_record          NUMBER         := p_from_record;
      l_to_record            NUMBER         := p_to_record;
      l_order_by             VARCHAR2(4000) := p_order_by;
      l_asc_desc             VARCHAR2(4000) := p_asc_desc;
      l_itt_header_sql       VARCHAR2(32000):= NULL;
      l_itt_header_count_sql VARCHAR2(32000):= NULL;
      l_count                NUMBER         := 0;
      l_employee_number      VARCHAR2(240)  := NULL;
   BEGIN
      IF l_itt_number IS NULL THEN
        l_itt_number := ' AND 1 = 1 ';
      ELSE
        l_itt_number := ' AND UPPER(cih.itt_number) LIKE UPPER('''||l_itt_number||''''||') ';
      END IF;

      IF l_cutomer_name IS NULL THEN
         l_cutomer_name := ' AND 1 = 1 ';
      ELSE
         l_cutomer_name := ' AND UPPER(cih.ship_to_customer) LIKE UPPER('''||l_cutomer_name||''''||')';
      END IF;

      IF l_ship_to_account IS NULL THEN
         l_ship_to_account := ' AND 1 = 1 ';
      ELSE
         l_ship_to_account := ' AND cih.ship_to_customer_cd = '''||l_ship_to_account||'''';
      END IF;

      IF (NVL(l_include_closed_itt, 'N') = 'Y')
      THEN
         l_include_closed_itt := ' AND 1 = 1 ';
      ELSE
         l_include_closed_itt := ' AND cih.itt_status <> ( SELECT cvt.val1 from canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                                           WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = ''CANON_E580_HDR_STATUS'' AND val51 = 3
                                                           AND SYSDATE BETWEEN NVL(start_date_active,sysdate-1) AND nvl(end_date_active,sysdate+1)
                                                          ) ';
      END IF;

      IF (l_itt_status IS NULL)
      THEN
         l_itt_status:=  ' AND 1 = 1 ';
      ELSE
         l_itt_status:= ' AND EXISTS( SELECT 1 FROM canon_e580_itt_lines_tbl cil, canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                      WHERE cil.itt_number = cih.itt_number
                                      AND   ct.cd_name = ''CANON_E580_LINE_STATUS'' 
                                      AND   SYSDATE BETWEEN NVL(start_date_active,sysdate-1) AND nvl(end_date_active,sysdate+1)
                                      AND   cil.itt_status = cvt.val1
                                      AND   cvt.val2 = '''||l_itt_status||''')';
      END IF;

      IF (l_itt_processor IS NULL)
      THEN
         l_itt_processor:= ' AND 1 = 1 ';
      ELSE
         BEGIN
            /**SELECT psn_cd
            INTO  l_employee_number
            FROM  s21_psn psn
            WHERE psn.glbl_cmpy_cd = g_glbl_cmpy_cd
            AND   NVL(psn.eff_from_dt, TO_CHAR(SYSDATE-1,'YYYYMMDD')) <= TO_CHAR(SYSDATE,'YYYYMMDD')
            AND   NVL(psn.eff_thru_dt, TO_CHAR(SYSDATE+1,'YYYYMMDD')) >= TO_CHAR(SYSDATE,'YYYYMMDD')
            AND   UPPER((psn.psn_last_nm||', '||psn.psn_first_nm)) = UPPER(l_itt_processor);**/
            
            SELECT psn.usr_nm
            INTO   l_employee_number
            FROM   auth_psn psn
            WHERE  UPPER((psn.last_nm||', '||psn.first_nm)) = UPPER(l_itt_processor)
            AND    actv_flg = 'Y'
            AND    glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_error_msg := 'Error while getting employee_number for  '||l_itt_processor||' - '|| SQLCODE || ' - ' || SQLERRM;
               DBMS_OUTPUT.PUT_LINE(l_error_msg);
         END;
         l_itt_processor:= ' AND UPPER(cih.itt_admin_owner) LIKE UPPER('''||l_employee_number||''''||') ';
      END IF;

      IF (l_sales_rep IS NULL)
      THEN
         l_sales_rep := ' AND 1 = 1 ';
      ELSE
         l_sales_rep := ' AND upper(cih.salesrep_nm) = UPPER('''||l_sales_rep||''''||') ';
      END IF;

      IF (l_sales_zone IS NULL)
      THEN
         l_sales_zone:= ' AND 1 = 1 ';
      ELSE
         l_sales_zone:= ' AND UPPER(cih.sales_zone) =  UPPER('''||l_sales_zone||''''||') ';
      END IF;

      IF (l_sales_branch IS NULL)
      THEN
         l_sales_branch := ' AND 1 = 1 ';
      ELSE
         l_sales_branch := ' AND UPPER(cih.sales_branch) =  UPPER('''||l_sales_branch||''''||') ';
      END IF;

      IF (l_cusa_po_number IS NULL)
      THEN
         l_cusa_po_number := ' AND 1 = 1 ';
      ELSE
         l_cusa_po_number := ' AND EXISTS (SELECT 1 FROM canon_e580_itt_lines_tbl cil
                                           WHERE cil.itt_number = cih.itt_number
                                           AND   UPPER(cil.cusa_po_number) LIKE '||''''||UPPER(l_cusa_po_number)||''''||')';
      END IF;

      IF (l_transaction_type IS NULL)
      THEN
         l_transaction_type := ' AND 1 = 1 ';
      ELSE
         l_transaction_type := ' AND EXISTS ( SELECT 1 FROM cpo_dtl l, ord_line_src src
                                              WHERE l.ord_line_src_cd = src.ord_line_src_cd
                                              AND   l.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                              AND src.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                              AND   l.ezcancelflag = ''0''
                                              AND src.ezcancelflag = ''0''
                                              AND src.ord_line_src_nm = '''||l_transaction_type||''')';
         /* *
         l_transaction_type := ' AND EXISTS (SELECT 1 FROM ds_cpo_dtl l, ds_ord_line_catg t, canon_e580_cd_tbl ct, canon_e580_cd_val_tbl cvt,
                                                           canon_e580_itt_lines_tbl cil
                                             WHERE l.ds_ord_line_catg_cd = t.ds_ord_line_catg_cd
                                             AND   l.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                             AND   t.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                             AND   t.ds_ord_line_catg_nm = '''||l_transaction_type||'''
                                             AND   ct.cd_name = ''CANON_E580_LINE_TYPE''
                                             AND   ct.cd_id = cvt.cd_id
                                             AND   t.ds_ord_line_catg_nm = cvt.val2
                                             AND   SYSDATE BETWEEN NVL(start_date_active,SYSDATE-1) AND nvl(end_date_active,SYSDATE+1)
                                             AND   cil.order_number = cih.order_number
                                             AND   l.cpo_ord_num = cih.order_number
                                             AND   l.cpo_dtl_line_num||''.''||l.cpo_dtl_line_sub_num = cil.line_number)';* */
      END IF;

      IF (l_so_number IS NULL)
      THEN
         l_so_number := ' AND 1 = 1 ';
      ELSE
         l_so_number := ' AND UPPER(cih.order_number) LIKE UPPER('''||l_so_number||''''||') ';
      END IF;

      IF (l_order_by IS NULL)
      THEN
         l_order_by := ' itt_number ';
      ELSIF l_order_by = 'ORDER_NUMBER'
      THEN
         l_order_by := ' order_number ';
      END IF;

      IF l_dealer_name IS NULL
      THEN
         l_dealer_name:= ' AND 1 = 1  ';
      ELSE
         l_dealer_name := ' AND UPPER(cih.dealer) LIKE UPPER('''||l_dealer_name||''''||')';
      END IF;

      IF (l_delivery_date_from IS NOT NULL AND l_delivery_date_to IS NOT NULL)
      THEN
         l_delivery_date :=  ' AND EXISTS(SELECT 1 FROM canon_e580_itt_lines_tbl cil
                                          WHERE cil.itt_number = cih.itt_number
                                          AND   TRUNC(cil.delivery_scheduled_date) BETWEEN '''||l_delivery_date_from||''' AND '''||l_delivery_date_to||''')
                             ';
      ELSE
         l_delivery_date:= ' AND 1 = 1 ';
      END IF;

      l_itt_header_sql := 'SELECT
                                row_num,
                                itt_status,
                                itt_admin_owner,
                                order_number,
                                itt_number,
                                party_name,
                                city,
                                state,
                                postal_code,
                                cust_ship_to_address,
                                name,
                                sales_zone,
                                csa_sales_branch,
                                dealer,
                                dealer_whse_code,
                                dealer_address_line1,
                                dealer_address_line2,
                                dealer_address_line3,
                                dealer_city,
                                dealer_state,
                                dealer_zip,
                                dealer_country,
                                dealer_contact,
                                dealer_contact_number,
                                dealer_email,
                                dealer_ship_to_cna_code,
                                cmap_yes_no,
                                product_sourced_by,
                                cusa_po_number,
                                scheduled_delivery_date,
                                last_update_date,
                                mail_invoices_to,
                                vendor_id,
                                vendor_site_id
                         FROM (
                                SELECT
                                   DISTINCT
                                   ROWNUM row_num,
                                 ( SELECT cvt.val2 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                   WHERE  ct.cd_id = cvt.cd_id
                                   AND    ct.cd_name = ''CANON_E580_HDR_STATUS'' AND (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
                                   AND    cvt.val1 = cih.itt_status
                                 )         itt_status,
                                 ( SELECT UPPER((psn.last_nm||'', ''||psn.first_nm))
                                   FROM  auth_psn psn
                                   WHERE psn.usr_nm = cih.itt_admin_owner
                                   AND   psn.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                   AND   psn.actv_flg = ''Y''
                                 )         itt_admin_owner,
                                   cih.order_number,
                                   cih.itt_number,
                                   sh.loc_nm     party_name,
                                   sh.cty_addr   city,
                                   sh.st_cd      state,
                                   sh.post_cd    postal_code,
                                   sh.first_line_addr||'', ''||sh.scd_line_addr||'', ''||sh.third_line_addr||'', ''||sh.frth_line_addr
                                                              cust_ship_to_address,
                                   cih.salesrep_nm name,
                                   cih.sales_zone,
                                   cih.sales_branch csa_sales_branch,
                                   cih.dealer dealer,
                                   cih.dealer_whse_code,
                                   cih.dealer_address_line1,
                                   cih.dealer_address_line2,
                                   cih.dealer_address_line3,
                                   cih.dealer_city,
                                   cih.dealer_state,
                                   cih.dealer_zip,
                                   cih.dealer_country,
                                   cih.dealer_contact,
                                   cih.dealer_contact_number,
                                   cih.dealer_email,
                                   cih.dealer_ship_to_cna_code,
                                   cih.cmap_yes_no,
                                   cih.product_sourced_by,
                                   cih.cusa_po_number,
                                   cih.scheduled_delivery_date,
                                   cih.last_update_date,
                                   cih.mail_invoices_to,
                                   cih.vendor_id,
                                   cih.vendor_site_id
                                FROM  canon_e580_itt_headers_tbl cih
                                    , cpo oh
                                    , ship_to_cust sh
                                WHERE 1=1
                                AND  NVL (cih.cancelled_flag, ''N'') = ''N''
                                AND  cih.order_number = oh.cpo_ord_num
                                AND  sh.ship_to_cust_cd = cih.ship_to_customer_cd
                                AND  oh.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                AND  sh.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                AND  oh.ezcancelflag = ''0''
                                AND  sh.ezcancelflag = ''0''
                                '
                                ||l_itt_number
                                ||CHR(10)
                                ||l_cutomer_name
                                ||CHR(10)
                                ||l_ship_to_account
                                ||CHR(10)
                                ||l_itt_status
                                ||CHR(10)
                                ||l_itt_processor
                                ||CHR(10)
                                ||l_sales_rep
                                ||CHR(10)
                                ||l_sales_zone
                                ||CHR(10)
                                ||l_sales_branch
                                ||CHR(10)
                                ||l_transaction_type
                                ||CHR(10)
                                ||l_dealer_name
                                ||CHR(10)
                                ||l_delivery_date
                                ||CHR(10)
                                ||l_include_closed_itt
                                ||CHR(10)
                                ||l_cusa_po_number
                                ||CHR(10)
                                ||l_so_number
                                ||CHR(10)
                                ||' ORDER BY '
                                ||l_order_by
                                || ' '
                                || l_asc_desc
                                ||CHR(10)
                              ||')
                         WHERE ROW_NUM >= '||NVL(l_from_record, 0)
                         ||' AND ROW_NUM <= '||NVL(l_to_record, 999999999999)
                         ||' ORDER BY  '
                         ||l_order_by
                         ||' '
                         ||l_asc_desc;

      dbms_output.put_line(l_itt_header_sql);

      --Any change to above query criteria would be applicable to below query.
      l_itt_header_count_sql :=  'SELECT COUNT(distinct cih.order_number)
                                  FROM canon_e580_itt_headers_tbl cih
                                     , cpo oh
                                  WHERE 1=1
                                  AND  NVL (cih.cancelled_flag, ''N'') <> ''Y''
                                  AND  NVL (cih.cancelled_flag, ''N'') = ''N''
                                  AND  cih.order_number = oh.cpo_ord_num
                                  AND  oh.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                  AND  oh.ezcancelflag = ''0''
                                 '
                                  ||l_itt_number
                                  ||CHR(10)
                                  ||l_cutomer_name
                                  ||CHR(10)
                                  ||l_ship_to_account
                                  ||CHR(10)
                                  ||l_itt_status
                                  ||CHR(10)
                                  ||l_itt_processor
                                  ||CHR(10)
                                  ||l_sales_rep
                                  ||CHR(10)
                                  ||l_sales_zone
                                  ||CHR(10)
                                  ||l_sales_branch
                                  ||CHR(10)
                                  ||l_transaction_type
                                  ||CHR(10)
                                  ||l_dealer_name
                                  ||CHR(10)
                                  ||l_delivery_date
                                  ||CHR(10)
                                  ||l_include_closed_itt
                                  ||CHR(10)
                                  ||l_cusa_po_number
                                  ||CHR(10)
                                  ||l_so_number
                                  ||CHR(10);

      dbms_output.put_line(l_itt_header_count_sql);
      l_itt_dsql_tbl.DELETE;
      l_itt_dsql_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
      l_itt_dsql_tbl(1).procedure_name   := 'ITT_HEADER_DETAILS';
      l_itt_dsql_tbl(1).dynamic_sql      := l_itt_header_sql;      
      l_itt_dsql_tbl(1).value1           := l_count;
      l_itt_dsql_tbl(1).status_message   := 'No Error';
      l_itt_dsql_tbl(1).created_by       := l_username;
      l_itt_dsql_tbl(1).creation_date    := SYSDATE;
      l_itt_dsql_tbl(1).last_updated_by  := l_username;
      l_itt_dsql_tbl(1).last_update_date := SYSDATE;
      canon_e580_itt_util_pkg.log_dynamic_sql(p_dsql_tbl => l_itt_dsql_tbl);
      COMMIT;

      EXECUTE IMMEDIATE l_itt_header_count_sql INTO l_count;
      x_count := l_count;
      OPEN x_order_details FOR l_itt_header_sql;
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_header_details terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        dbms_output.put_line('Error...'||l_error_msg);
        l_itt_dsql_tbl.DELETE;
        l_itt_dsql_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_dsql_tbl(1).procedure_name   := 'ITT_HEADER_DETAILS';
        l_itt_dsql_tbl(1).dynamic_sql      := l_itt_header_sql;      
        l_itt_dsql_tbl(1).value1           := l_count;
        l_itt_dsql_tbl(1).status_message   := 'Error - '||l_error_msg;
        l_itt_dsql_tbl(1).created_by       := l_username;
        l_itt_dsql_tbl(1).creation_date    := SYSDATE;
        l_itt_dsql_tbl(1).last_updated_by  := l_username;
        l_itt_dsql_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.log_dynamic_sql(p_dsql_tbl => l_itt_dsql_tbl);
   END itt_header_details;


   -----------------------------------------------------------------------------------------------------
   --This procedure is invoked from ITT workbench search results when clicked on ITT Order #.
   --Purpose: Extract all the ITT header/line details against the ITT # and return back to UI to display
   -----------------------------------------------------------------------------------------------------
   PROCEDURE itt_line_details (  p_itt_number    IN   VARCHAR2,
                                 p_so_number     IN   VARCHAR2,
                                 p_from_record   IN   NUMBER,
                                 p_to_record     IN   NUMBER,
                                 p_order_by      IN   VARCHAR2,
                                 p_asc_desc      IN   VARCHAR2,
                                 x_line_details1 OUT  ref_order_details,
                                 x_line_details2 OUT  ref_order_details,
                                 x_count         OUT  NUMBER
                              )
   AS
       l_itt_number            VARCHAR2(80)   := p_itt_number;
       l_so_number             VARCHAR2(100)  := p_so_number;
       l_from_record           NUMBER         := p_from_record;
       l_to_record             NUMBER         := p_to_record;
       l_order_by              VARCHAR2(4000) := p_order_by;
       l_asc_desc              VARCHAR2(4000) := p_asc_desc;
       l_itt_line_sql1         VARCHAR2(32000):= NULL;
       l_itt_line_sql2         VARCHAR2(32000):= NULL;
       l_itt_line_count_sql    VARCHAR2(32000):= NULL;
       l_count                 NUMBER         := 0;
       l_cust_contact_name     VARCHAR2(240 BYTE) := NULL;
       l_cust_contact_phone    VARCHAR2(240 BYTE) := NULL;
       l_cust_contact_email    VARCHAR2(240 BYTE) := NULL;
       l_cust_contact_fax      VARCHAR2(240 BYTE) := NULL;
       l_vendor_id             NUMBER := -1;
       l_vendor_site_id        NUMBER := -1;
       l_vendor_contact_id     NUMBER := -1;
   BEGIN
      l_debug := 1;
      --Derive customer contact details.
      BEGIN
         SELECT DISTINCT
                cil.customer_contact_name
              , cil.customer_contact_phone
              , cil.customer_contact_email
              , cil.customer_contact_fax
         INTO   l_cust_contact_name,
                l_cust_contact_phone,
                l_cust_contact_email,
                l_cust_contact_fax
         FROM   canon_e580_itt_lines_tbl cil
         WHERE 1=1
         AND   cil.itt_number = l_itt_number
         AND   NVL(cil.cancelled_flag,'N') = 'N'
         AND   cil.customer_contact_name IS NOT NULL
         AND   ROWNUM = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
             BEGIN
                SELECT DISTINCT
                     cih.customer_contact_name
                   , cih.customer_contact_phone
                   , cih.customer_contact_email
                   , cih.customer_contact_fax
                INTO l_cust_contact_name,
                     l_cust_contact_phone,
                     l_cust_contact_email,
                     l_cust_contact_fax
                FROM  canon_e580_itt_headers_tbl cih
                WHERE cih.itt_number = l_itt_number;
             EXCEPTION
             WHEN NO_DATA_FOUND
             THEN
                l_cust_contact_name:= ' ';
                l_cust_contact_phone:= ' ';
                l_cust_contact_email:= ' ';
                l_cust_contact_fax:= ' ';
             END;
          WHEN OTHERS
          THEN
             l_error_msg := 'Error while getting Customer contact information ' ||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
             DBMS_OUTPUT.PUT_LINE(l_error_msg);
             l_itt_err_tbl.DELETE;
             l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
             l_itt_err_tbl(1).procedure_name   := 'ITT_LINE_DETAILS';
             l_itt_err_tbl(1).itt_number       := l_itt_number;
             l_itt_err_tbl(1).error_message    := l_error_msg;
             l_itt_err_tbl(1).created_by       := l_username;
             l_itt_err_tbl(1).creation_date    := SYSDATE;
             l_itt_err_tbl(1).last_updated_by  := l_username;
             l_itt_err_tbl(1).last_update_date := SYSDATE;
             canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
      END;
      
      l_debug := 2;
      IF l_itt_number IS NULL THEN
         l_itt_number:= ' AND 1=1 ';
      ELSE
         l_itt_number := ' AND cil.itt_number = '''||l_itt_number||'''';
      END IF;

      l_debug := 3;
      IF l_so_number IS NULL THEN
         l_so_number := ' AND 1=1 ';
      ELSE
         l_so_number := ' AND cil.order_number = '''||l_so_number||'''';
      END IF;

      l_debug := 4;
      IF l_order_by IS NULL THEN
         l_order_by:= ' itt_number ';
      ELSIF l_order_by = 'SO_NUMBER' THEN
         l_order_by := ' order_number ';
      END IF;

      l_debug := 5;
      l_itt_line_sql1 :=
                        ' SELECT DISTINCT
                                    itt_number
                                  , order_number
                                  , order_status
                                  , order_booked_date
                                  , ship_to_customer
                                  , itt_admin_owner
                                  , itt_admin_owner_name
                                  , itt_status
                                  , sales_rep
                                  , sales_zone
                                  , sales_branch
                                  , shop_to_address
                                  , city_state
                                  , postal_code
                                  , country
                                  , dealer
                                  , dealer_whse_code
                                  , dealer_address_line1
                                  , dealer_address_line2
                                  , dealer_address_line3
                                  , dealer_city
                                  , dealer_state
                                  , dealer_zip
                                  , dealer_country
                                  , dealer_contact
                                  , dealer_contact_number
                                  , dealer_email
                                  , dealer_ship_to_cna_code
                                  , cmap_yes_no
                                  , product_sourced_by
                                  , mail_invoices_to
                                  , cust_contact_name
                                  , cust_contact_phone
                                  , cust_contact_email
                                  , cust_contact_fax
                                  , dealer_po_number
                                  , vendor_id
                                  , vendor_site_id
                                  , prnt_vnd_cd
                          FROM (
                                  SELECT DISTINCT
                                         cil.itt_number
                                       , cil.order_number
                                       , cih.order_status
                                       , cih.booked_date   order_booked_date
                                       , cih.itt_admin_owner
                                       ,(SELECT UPPER((psn.last_nm||'', ''||psn.first_nm))
                                         FROM  auth_psn psn
                                         WHERE usr_nm = cih.itt_admin_owner
                                         AND   psn.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                         AND   psn.actv_flg = ''Y''
                                         AND   psn.ezcancelflag = ''0''
                                        )          itt_admin_owner_name
                                       , cih.ship_to_customer
                                       ,(SELECT cvt.val2 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                         WHERE  ct.cd_id = cvt.cd_id
                                         AND    ct.cd_name = ''CANON_E580_HDR_STATUS'' 
                                         AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
                                         AND    cvt.val1 = cih.itt_status
                                        )          itt_status
                                       , cih.salesrep_nm sales_rep
                                       , cil.sales_zone
                                       , cil.sales_branch  sales_branch
                                       , sh.first_line_addr||'', ''||sh.scd_line_addr||'', ''||sh.third_line_addr||'', ''||sh.frth_line_addr
                                                          shop_to_address
                                       , sh.cty_addr||'', ''||sh.st_cd  city_state
                                       , sh.post_cd  postal_code
                                       , sh.ctry_cd  country
                                       , cih.dealer
                                       , cih.dealer_whse_code
                                       , cih.dealer_address_line1
                                       , cih.dealer_address_line2
                                       , cih.dealer_address_line3
                                       , cih.dealer_city
                                       , cih.dealer_state
                                       , cih.dealer_zip
                                       , cih.dealer_country
                                       , cih.dealer_contact
                                       , cih.dealer_contact_number
                                       , cih.dealer_email
                                       , cih.dealer_ship_to_cna_code
                                       , cih.cmap_yes_no
                                       , cih.product_sourced_by
                                       , cih.mail_invoices_to
                                       , Q''['
                                       ||l_cust_contact_name||']'' cust_contact_name
                                       , Q''['||l_cust_contact_phone||']'' cust_contact_phone
                                       , Q''['||l_cust_contact_email||']'' cust_contact_email
                                       , Q''['||l_cust_contact_fax||']'' cust_contact_fax
                                       , (SELECT rtrim (xmlagg (xmlelement (e, a.po_ord_num || '', '')).extract (''//text()''), '', '') 
										  FROM (
										  SELECT DISTINCT p.po_ord_num 
                                          FROM   po p, po_dtl pd
                                          WHERE  p.po_ord_num = pd.po_ord_num
                                          AND    pd.trx_ref_num = cih.itt_number
                                          AND    p.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                          AND   pd.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                          AND    p.ezcancelflag = ''0''
                                          AND   pd.ezcancelflag = ''0''
                                          AND    p.vnd_cd <> (SELECT v.vnd_cd
                                                              FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
                                                              WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = ''CANON_E580_SITE_CSA_SOURCED'' 
                                                              AND    cvt.val2 = v.loc_nm
                                                              AND    v.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                                              AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))) a
                                        ) dealer_po_number
                                       , NVL(cih.vendor_id, -1) vendor_id
                                       , NVL(cih.vendor_site_id, -1) vendor_site_id
                                       , cih.prnt_vnd_cd
                                  FROM canon_e580_itt_lines_tbl cil
                                     , canon_e580_itt_headers_tbl cih
                                     , cpo h
                                     , cpo_dtl l
                                     , ship_to_cust sh
                                  WHERE cih.itt_number = cil.itt_number
                                  AND cih.order_number = cil.order_number
                                  AND cil.ship_to_customer_cd = sh.ship_to_cust_cd
                                  AND cih.order_number = h.cpo_ord_num
                                  AND   h.cpo_ord_num  = l.cpo_ord_num
                                  AND cil.line_number = l.cpo_dtl_line_num||''.''||l.cpo_dtl_line_sub_num
                                  AND  h.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                  AND  l.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                  AND sh.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                  AND NVL(cil.cancelled_flag, ''N'') = ''N'' '
                                  || CHR(10)
                                  ||l_itt_number
                                  ||CHR(10)
                                  ||l_so_number
                                  ||CHR(10)
                                  ||' ORDER BY '
                                  ||l_order_by
                                  ||' '
                                  ||l_asc_desc
                               ||') '
                          ||' ORDER BY  '
                          ||CHR(10)
                          ||l_order_by
                          ||' '
                          ||l_asc_desc;

      dbms_output.put_line(l_itt_line_sql1);
      dbms_output.put_line(CHR(10)||'--------------------------------------------------'||CHR(10));

      l_debug := 6;
      l_itt_line_sql2 :=
                'SELECT  row_num
                       , line_id
                       , merchandise
                       , itt_number
                       , line_number
                       , so_line_type
                       , itt_status
                       , item
                       , item_description
                       , ordered_quantity
                       , item_purchase_price
                       , finder_fee
                       , install_credit
                       , line_status_code
                       , delivery_scheduled_date
                       , reason_code
                       , serial_number
                       , lot_number
                       , cusa_po_number
                       , itt_line_type
                       , product_sourced_by
                 FROM (
                       SELECT rownum row_num
                            , cil.line_id
                            , cil.merchandise
                            , cil.itt_number
                            , cil.line_number
                            , cil.transaction_type_nm so_line_type
                            ,(SELECT cvt.val2 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                              WHERE  ct.cd_id = cvt.cd_id
                              AND    ct.cd_name = ''CANON_E580_LINE_STATUS'' AND (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
                              AND    cvt.val1 = cil.itt_status
                             )         itt_status
                            , SUBSTR(cil.item, 1,8) item
                            , cil.item_description
                            , cil.ordered_quantity
                            , cil.item_purchase_price    item_purchase_price   --Pending:Derive 0 value if this line is child line (link_to_line_id)
                            , cil.finder_fee
                            , cil.install_credit
                            , cil.line_status_code
                            , cil.delivery_scheduled_date
                            , cil.reason_code
                            , NVL(cil.serial_number, cil.license_number) serial_number
                            , NULL lot_number --Pending:Derive lot_number based on item. (mtl_lot_numbers)
                            , cil.cusa_po_number
                            , cil.itt_line_type
                            , cil.product_sourced_by
                       FROM canon_e580_itt_lines_tbl cil
                       WHERE 1=1
                       AND NVL(cil.cancelled_flag, ''N'') = ''N'''
                       || CHR(10)
                       ||l_itt_number
                       ||CHR(10)
                       ||l_so_number
                       ||CHR(10)
                       ||' ORDER BY '
                       ||l_order_by
                       || l_asc_desc
                       ||CHR(10)
                     ||')
                 WHERE row_num >= '||NVL(l_from_record, 0)
                 ||' and row_num <= '||NVL(l_to_record, 999999999999)
                 ||' ORDER BY  '
                 ||l_order_by
                 ||', itt_line_type DESC '
                 ||', to_char(substr(line_number, 1,3))'
				 ||', to_char(substr(line_number, 5,3))'
                 ||' '
                 ||l_asc_desc
                 ||', itt_line_type DESC, rownum ASC ';

      dbms_output.put_line(l_itt_line_sql2);
      dbms_output.put_line('--------------------------------------------------'||CHR(12));

      l_debug := 7;
      l_itt_line_count_sql :=
            'SELECT count(*)
             FROM canon_e580_itt_lines_tbl cil
             WHERE 1=1
             AND NVL(cil.cancelled_flag, ''N'') = ''N'' '
             || CHR(10)
             ||l_itt_number
             ||CHR(10)
             ||l_so_number
             ||CHR(10)
             ||' ORDER BY '
             ||l_order_by
             || ' '
             ||l_asc_desc;

      dbms_output.put_line(l_itt_line_count_sql);

      l_debug := 8;
      EXECUTE IMMEDIATE l_itt_line_count_sql INTO l_count;
      x_count := l_count;

      l_debug := 9;
      l_itt_dsql_tbl.DELETE;
      FOR i in 1..2
      LOOP
         l_itt_dsql_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_dsql_tbl(1).procedure_name   := 'ITT_LINE_DETAILS';
         l_itt_dsql_tbl(1).value1           := l_count;
         l_itt_dsql_tbl(1).status_message   := 'No Error';
         l_itt_dsql_tbl(1).created_by       := l_username;
         l_itt_dsql_tbl(1).creation_date    := SYSDATE;
         l_itt_dsql_tbl(1).last_updated_by  := l_username;
         l_itt_dsql_tbl(1).last_update_date := SYSDATE;
         IF (i = 1) THEN
            l_itt_dsql_tbl(1).dynamic_sql := l_itt_line_sql1;
         ELSE
            l_itt_dsql_tbl(1).dynamic_sql := l_itt_line_sql2;
         END IF;
      END LOOP;
      canon_e580_itt_util_pkg.log_dynamic_sql(p_dsql_tbl => l_itt_dsql_tbl);

      l_debug := 10;
      OPEN x_line_details1 FOR l_itt_line_sql1;
      OPEN x_line_details2 FOR l_itt_line_sql2;

      COMMIT;
   EXCEPTION
   WHEN OTHERS
   THEN
      l_error_msg := 'Procedure itt_line_details terminated due to ' || SQLCODE || ' - ' || SQLERRM;
      dbms_output.put_line(l_error_msg);
      l_itt_err_tbl.DELETE;
      l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
      l_itt_err_tbl(1).procedure_name   := 'ITT_LINE_DETAILS';
      l_itt_err_tbl(1).itt_number       := l_itt_number;
      l_itt_err_tbl(1).error_message    := l_error_msg;
      l_itt_err_tbl(1).created_by       := 1;
      l_itt_err_tbl(1).creation_date    := SYSDATE;
      l_itt_err_tbl(1).last_updated_by  := 1;
      l_itt_err_tbl(1).last_update_date := SYSDATE;
      canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_line_details;

   -----------------------------------------------------------------------------------------------------
   --This procedure is invoked from ITT workbench search results when clicked on ITT Order #.
   --Purpose: Extract all the ITT line timestamp details against the ITT # and return back to UI to display
   -----------------------------------------------------------------------------------------------------
   PROCEDURE itt_timestamp_details (  p_itt_number        IN  VARCHAR2
                                    , x_timestamp_details OUT ref_order_details
                                   )
   AS
   BEGIN
      OPEN x_timestamp_details
      FOR
      SELECT DISTINCT
                cih.itt_number,
                cih.order_creation_date,
                cih.booked_date,
                cil.po_date,
                cil.po_accepted_date_by_dlr,
                cil.po_sent_date_to_cusa,
                cil.shipped_date_from_cusa,
                cil.equip_arrive_at_dlr_date,
                cil.pod_rcvd_from_dlr_date,
                cil.cna_po_aprvd_by_dlr_date,
               (SELECT cph.creation_date
                FROM  canon_e580_popdf_history_tbl cph
                WHERE cph.itt_number = cih.itt_number
                AND   cph.seq_number = (SELECT MIN(cph1.seq_number)
                                        FROM canon_e580_popdf_history_tbl cph1
                                        WHERE cph1.itt_number = cph.itt_number)) pdf_creation_date,
               (SELECT cph.creation_date
                FROM canon_e580_popdf_history_tbl cph
                WHERE cph.itt_number = cih.itt_number
                AND cph.seq_number = (SELECT MAX(cph1.seq_number)
                                      FROM canon_e580_popdf_history_tbl cph1
                                      WHERE cph1.itt_number = cph.itt_number)) pdf_last_update_date
      FROM  canon_e580_itt_headers_tbl cih
          , canon_e580_itt_lines_tbl cil
      WHERE 1=1
      AND cil.itt_number    = cih.itt_number
      AND cil.order_number  = cih.order_number
      AND cil.itt_line_type = 'GOODS'
      AND NVL(cil.cancelled_flag,'N') = 'N'
      AND cih.itt_number = p_itt_number;

   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred in query extracting Timestamp details, Error Message :'||SQLERRM;
         DBMS_OUTPUT.PUT_LINE(l_error_msg);
   END itt_timestamp_details;

   ---------------------------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Search line details to populate notes section
   --Logic to extract ITT line notes details from custom table canon_e580_notes_tbl
   ---------------------------------------------------------------------------------------------
   PROCEDURE itt_notes_details (  p_itt_number    IN  VARCHAR2
                                , p_from_record   IN  NUMBER
                                , p_to_record     IN  NUMBER
                                , p_order_by      IN  VARCHAR2
                                , p_asc_desc      IN  VARCHAR2
                                , x_notes_details OUT ref_order_details
                                , x_count         OUT NUMBER
                              )
   IS
      l_itt_number          VARCHAR2(200)  := p_itt_number;
      l_order_by            VARCHAR2(4000) := p_order_by;
      l_asc_desc            VARCHAR2(4000) := p_asc_desc;
      l_itt_notes_sql       VARCHAR2(4000);
      l_itt_notes_count_sql VARCHAR2(4000);
      l_count               NUMBER;
   BEGIN
      l_debug_step := 1;
      IF (l_itt_number IS NULL)
      THEN
         l_itt_number := ' AND 1 = 1 ';
      ELSE
         l_itt_number := ' AND cnt.itt_number = '''||l_itt_number||'''';
      END IF;

      l_debug_step := 2;
      IF UPPER(l_order_by) = UPPER('seq_number')
      THEN
         l_order_by:= ' seq_number ';
      ELSIF UPPER(l_order_by) = UPPER('creation_date')
      THEN
         l_order_by := ' creation_date ';
      ELSE
         l_order_by := ' seq_number ';
      END IF;

      l_debug_step := 3;
      IF (l_asc_desc IS NULL)
      THEN
         l_asc_desc := ' ASC ';
      ELSE
         l_asc_desc := l_asc_desc;
      END IF;

      l_debug_step := 4;
      l_itt_notes_sql := '
                      SELECT
                            row_num
                          , itt_number
                          , order_number
                          , seq_number
                          , notes
                          , created_by_name
                          , creation_date
                          , add_to_po_pdf
                     FROM (SELECT
                            rownum row_num
                          , itt_number
                          , order_number
                          , seq_number
                          , notes
                          , add_to_po_pdf
                          ,( SELECT UPPER((psn.psn_last_nm||'', ''||psn.psn_first_nm))
                             FROM  s21_psn psn
                             WHERE psn_cd = cnt.created_by
                             AND   psn.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                             AND   NVL(psn.eff_from_dt, TO_CHAR(SYSDATE-1,''YYYYMMDD'')) <= TO_CHAR(SYSDATE,''YYYYMMDD'')
                             AND   NVL(psn.eff_thru_dt, TO_CHAR(SYSDATE+1,''YYYYMMDD'')) >= TO_CHAR(SYSDATE,''YYYYMMDD'')
                            )  created_by_name
                          , creation_date
                          FROM canon_e580_notes_tbl cnt
                          WHERE 1=1 '
                          || l_itt_number
                          || CHR(10)
                          || ' ORDER BY '
                          || l_order_by
                          || ' '
                          || l_asc_desc
                          || CHR(10)||
                         ') '
                  ||' WHERE row_num >= '||NVL(p_from_record, 0)
                  ||' AND   row_num <= '||NVL(p_to_record, 999999999999)
                  ||' ORDER BY  '
                  ||l_order_by
                  ||' '
                  ||l_asc_desc;

      l_debug_step := 5;
      l_itt_notes_count_sql :=
               'SELECT count(*)
                FROM canon_e580_notes_tbl cnt
                WHERE 1=1 '
                ||l_itt_number
                ||CHR(10)
                ||' ORDER BY '
                ||l_order_by
                || ' '
                || l_asc_desc;

      dbms_output.put_line(l_itt_notes_sql);
      dbms_output.put_line(CHR(10)||'-------------------...'||CHR(10));
      dbms_output.put_line(l_itt_notes_count_sql);

      l_debug_step := 6;
      l_itt_dsql_tbl.DELETE;
      l_itt_dsql_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
      l_itt_dsql_tbl(1).procedure_name   := 'ITT_NOTES_DETAILS';
      l_itt_dsql_tbl(1).dynamic_sql      := l_itt_notes_sql;      
      l_itt_dsql_tbl(1).status_message   := 'No Error';
      l_itt_dsql_tbl(1).created_by       := l_username;
      l_itt_dsql_tbl(1).creation_date    := SYSDATE;
      l_itt_dsql_tbl(1).last_updated_by  := l_username;
      l_itt_dsql_tbl(1).last_update_date := SYSDATE;
      canon_e580_itt_util_pkg.log_dynamic_sql(p_dsql_tbl => l_itt_dsql_tbl);

      l_debug_step := 7;
      OPEN x_notes_details FOR l_itt_notes_sql;

      l_debug_step := 8;
      EXECUTE IMMEDIATE l_itt_notes_count_sql INTO l_count;
      x_count := l_count;
      dbms_output.put_line(' Record Count - '||l_count);

   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred at debug step #'||l_debug_step||', Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
   END;

   -----------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Search Line Pricing Details.
   --Logic to extract the Pricing Details against the ITT Line
   -----------------------------------------------------------------------------
   PROCEDURE itt_pricing_details ( p_itt_number       IN  VARCHAR2,
                                   x_pricing_details  OUT ref_order_details,
                                   x_count            OUT NUMBER
                                 )
   AS
      l_from_record  NUMBER := NULL;
      l_to_record    NUMBER := NULL;
      l_count        NUMBER := 0;
      l_itt_pricing_details_sql      VARCHAR2(4000) := NULL;
      l_itt_pricing_datil_count_sql  VARCHAR2(4000) := NULL;
   BEGIN
      l_itt_pricing_details_sql :=
            ' SELECT
                    row_num,
                    equip_model,
                    quantity,
                    contract_type,
                    meter_type,
                    overage_rate,
                    plan_type,
                    term,
                    base_price,
                    base_bill_cycle,
                    overage_bill_cycle,
                    copy_inclusion,
                    multiplier,
					svc_meter_pkg,
					hard_meter_counter
              FROM (      SELECT
                                 rownum row_num,
                                 csp.equip_model,
                                 csp.quantity,
                                 csp.contract_type,
                                 csp.meter_type,
                                 csp.overage_rate,
                                 csp.plan_type,
                                 csp.term,
                                 csp.base_price,
                                 csp.base_bill_cycle,
                                 csp.overage_bill_cycle,
                                 csp.copy_inclusion,
                                 csp.multiplier,
								 csp.svc_meter_pkg,
								 csp.hard_meter_counter
                          FROM canon_e580_service_pricing_tbl csp
                            , (SELECT DISTINCT cil1.itt_number, cil1.merchandise, cil1.cancelled_flag
                               FROM canon_e580_itt_lines_tbl cil1
                               WHERE cil1.itt_number = '''||p_itt_number
                               ||''' AND NVL(cil1.cancelled_flag, ''N'') <> ''Y''
                               AND cil1.merchandise IN (''11'')
                               UNION
                               SELECT DISTINCT cil1.itt_number, cil1.merchandise, cil1.cancelled_flag
                               FROM canon_e580_itt_lines_tbl cil1
                               WHERE cil1.itt_number = '''||p_itt_number
                               ||''' AND NVL(cil1.cancelled_flag, ''N'') <> ''Y''
                               AND cil1.merchandise IN (''10'')
                               AND NOT EXISTS (SELECT *
                                               FROM canon_e580_itt_lines_tbl cil2
                                               WHERE cil2.itt_number = cil1.itt_number
                                               AND NVL(cil1.cancelled_flag, ''N'') <> ''Y''
                                               AND cil2.merchandise IN (''11''))
                              ) cil
                          WHERE 1=1
                          AND cil.itt_number = csp.itt_number
                          AND csp.itt_number = '''||p_itt_number
                          ||''' ORDER BY csp.equip_model, csp.meter_type, row_num )
              WHERE row_num >= '||NVL(l_from_record, 0)
              ||' and row_num <= '||NVL(l_to_record, 999999999999)
              ||' ORDER BY  equip_model, meter_type, row_num ';

      DBMS_OUTPUT.PUT_LINE(l_itt_pricing_details_sql);
      DBMS_OUTPUT.PUT_LINE(CHR(10)||' --------------------------------------'||CHR(10));

      l_itt_pricing_datil_count_sql := '
      SELECT
      count(*)
      FROM canon_e580_service_pricing_tbl csp
      WHERE csp.itt_number = '''||p_itt_number
      ||''' ORDER BY csp.equip_model, csp.meter_type';

      DBMS_OUTPUT.PUT_LINE(l_itt_pricing_datil_count_sql);

      OPEN x_pricing_details FOR l_itt_pricing_details_sql;
      EXECUTE IMMEDIATE l_itt_pricing_datil_count_sql INTO l_count;
      x_count := l_count;

      l_itt_dsql_tbl.DELETE;
      l_itt_dsql_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
      l_itt_dsql_tbl(1).procedure_name   := 'ITT_PRICING_DETAILS';
      l_itt_dsql_tbl(1).dynamic_sql      := l_itt_pricing_details_sql;      
      l_itt_dsql_tbl(1).status_message   := 'No Error';
      l_itt_dsql_tbl(1).created_by       := l_username;
      l_itt_dsql_tbl(1).creation_date    := SYSDATE;
      l_itt_dsql_tbl(1).last_updated_by  := l_username;
      l_itt_dsql_tbl(1).last_update_date := SYSDATE;
      canon_e580_itt_util_pkg.log_dynamic_sql(p_dsql_tbl => l_itt_dsql_tbl);
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_pricing_details terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_PRICING_DETAILS';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := 1;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := 1;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_pricing_details;

   PROCEDURE itt_po_pdf_hist_details ( p_itt_number IN VARCHAR2,
                                       x_po_pdf_hist_details OUT ref_order_details
                                     )
   AS
   BEGIN
      OPEN x_po_pdf_hist_details
      FOR
        SELECT  cph.itt_number
              , cph.seq_number
              , cph.user_name
              ,(SELECT UPPER((psn.psn_first_nm||' '||psn.psn_last_nm)) full_name 
                FROM s21_psn psn
                WHERE  psn.glbl_cmpy_cd = g_glbl_cmpy_cd AND psn.psn_cd = UPPER (cph.user_name)
                AND   NVL(psn.eff_from_dt, TO_CHAR(SYSDATE-1,'YYYYMMDD')) <= TO_CHAR(SYSDATE,'YYYYMMDD')
                AND   NVL(psn.eff_thru_dt, TO_CHAR(SYSDATE+1,'YYYYMMDD')) >= TO_CHAR(SYSDATE,'YYYYMMDD')
                ) full_name
              , cph.creation_date
        FROM  canon_e580_popdf_history_tbl cph
        WHERE cph.itt_number = p_itt_number
        ORDER BY cph.seq_number ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_po_pdf_hist_details terminated due to ' ||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
         dbms_output.put_line(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_PO_PDF_HIST_DETAILS';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_po_pdf_hist_details;
   
   PROCEDURE itt_sub_inv_lov (   p_inv_org_code IN  VARCHAR2
                               , x_sub_inv_lov  OUT ref_order_details 
                             )
   IS
   BEGIN
      OPEN x_sub_inv_lov 
      FOR
      SELECT rtl_swh_cd  "SECONDARY_INVENTORY_NAME"
      FROM   rtl_swh
      WHERE  rtl_wh_cd = p_inv_org_code
      AND    glbl_cmpy_cd = g_glbl_cmpy_cd
      AND    ezcancelflag = '0'
      ORDER BY 1;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Procedure itt_sub_inv_lov terminated due to ' ||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_SUB_INV_LOV';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   /**********LOV Field API's*************/   
   --------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible customer/details in Customer LOV field
   --------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_cust_account_lov ( p_cust_name IN VARCHAR2,
                                    p_cust_number IN  VARCHAR2,
                                    x_cust_details OUT ref_order_details
                                   )
   AS
      l_cust_name     VARCHAR2(240) := TRIM(p_cust_name);
      l_cust_number   VARCHAR2(50) := TRIM(p_cust_number);
   BEGIN
      IF l_cust_name IS NULL and l_cust_number IS NOT NULL
      THEN
        OPEN x_cust_details FOR
           SELECT DISTINCT cih.ship_to_customer, cih.ship_to_customer_cd account_number
           FROM  canon_e580_itt_headers_tbl cih
           WHERE cih.ship_to_customer_cd LIKE l_cust_number||'%'
           AND cih.ship_to_customer_cd IS NOT NULL
           ORDER BY 1;
      ELSIF l_cust_number IS NULL and l_cust_name IS NOT NULL
      THEN
         OPEN x_cust_details FOR
            SELECT DISTINCT cih.ship_to_customer, cih.ship_to_customer_cd account_number
            FROM  canon_e580_itt_headers_tbl cih
            WHERE UPPER(cih.ship_to_customer) LIKE UPPER(l_cust_name)||'%'
            AND cih.ship_to_customer_cd IS NOT NULL
            ORDER BY 1;
      ELSIF l_cust_number IS NOT NULL and l_cust_name IS NOT NULL
      THEN
         OPEN x_cust_details FOR
         SELECT DISTINCT cih.ship_to_customer, cih.ship_to_customer_cd account_number
         FROM   canon_e580_itt_headers_tbl cih
         WHERE (     (cih.ship_to_customer_cd LIKE l_cust_number||'%')
                 AND (UPPER(cih.ship_to_customer) LIKE UPPER(l_cust_name)||'%')
                 AND cih.ship_to_customer_cd IS NOT NULL
               )
         ORDER BY 1;
      ELSIF l_cust_number IS NULL AND l_cust_name IS NULL
      THEN
         OPEN x_cust_details FOR
         SELECT DISTINCT cih.ship_to_customer, cih.ship_to_customer_cd account_number
         FROM   canon_e580_itt_headers_tbl cih
         WHERE cih.ship_to_customer_cd IS NOT NULL
         ORDER BY 1;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_cust_account_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_CUST_ACCOUNT_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_cust_account_lov;

   ------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible ITT statuses in ITT Status LOV field
   ------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_status_lov (x_status_name OUT ref_order_details)
   AS
   BEGIN
      OPEN x_status_name FOR
         SELECT cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_LINE_STATUS'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_status_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_STATUS_LOV';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_status_lov;

   ------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench UI to derive the ITT user full name and return back.
   ------------------------------------------------------------------------------------------------
   PROCEDURE itt_user_name (  p_user_name   IN  VARCHAR2
                            , p_person_name OUT VARCHAR2)
   IS
   BEGIN
      SELECT UPPER((psn.psn_last_nm||', '||psn.psn_first_nm))
      INTO   l_username
      FROM   s21_psn psn
      WHERE  psn.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND    ezcancelflag = '0'
      AND    NVL(psn.eff_from_dt, TO_CHAR(SYSDATE-1,'YYYYMMDD')) <= TO_CHAR(SYSDATE,'YYYYMMDD')
      AND    NVL(psn.eff_thru_dt, TO_CHAR(SYSDATE+1,'YYYYMMDD')) >= TO_CHAR(SYSDATE,'YYYYMMDD')
      AND    psn.psn_cd = p_user_name;

      p_person_name := l_username;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Procedure itt_user_name terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_USER_NAME';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   --------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible ITT Admin users in Order processor LOV field
   --------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_order_processor_lov (p_processor_name IN VARCHAR2, x_resources_name OUT ref_order_details)
   AS
    l_processor_name VARCHAR2(240) := NVL(p_processor_name, '%');
    l_person_name VARCHAR2(240) := NULL;
   BEGIN
        /*OPEN x_resources_name
        FOR
        SELECT UPPER((u.last_nm||', '||u.first_nm)) source_name
              ,u.usr_nm                   source_number
              ,'Canon ITT Administrator'  role_name
        FROM   role r
              ,usr_role ur
              ,auth_psn u
        WHERE  r.role_nm = 'E580_ADMN'
        AND    r.actv_flg = 'Y'
        AND    r.role_id = ur.role_id
        AND    ur.auth_psn_id = u.auth_psn_id
        AND    u.actv_flg = 'Y'
        AND    UPPER(u.usr_nm) LIKE UPPER(l_processor_name)
        AND    r.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND    u.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND    r.ezcancelflag = '0'
        AND    u.ezcancelflag = '0'
        ORDER BY 1;*/
	
	OPEN x_resources_name
	FOR	
	SELECT UPPER((AUTH.last_nm||', '||AUTH.first_nm)) source_name
              ,AUTH.usr_nm                   source_number
              ,'Canon ITT Administrator'  role_name
	  FROM AUTH_PSN AUTH,
		   USR_ROLE US_R,
		   ROLE R,
		   RESRC_ROLE RR,
		   PROT_RESRC PR,
		   PROT_RESRC_OBJ PRO,
		   RESRC_OBJ RO,
		   RESRC_CLS RCLS
	WHERE 1=1   
       AND R.ACTV_FLG = 'Y'	
       AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
       AND US_R.ROLE_ID = R.ROLE_ID
       AND R.ROLE_ID = RR.ROLE_ID
       AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
       AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
       AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
       AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
       AND AUTH.EZCANCELFLAG = '0'
       AND US_R.EZCANCELFLAG = '0'
       AND R.EZCANCELFLAG = '0'
       AND RR.EZCANCELFLAG = '0'
       AND PR.EZCANCELFLAG = '0'
       AND RO.EZCANCELFLAG = '0'
       AND RCLS.EZCANCELFLAG = '0'
       AND RCLS.RESRC_CLS_NM = 'FUNCTION'
       AND UPPER((AUTH.last_nm||', '||AUTH.first_nm)) LIKE UPPER(l_processor_name)  --modified by bgowravaram for search using name
       AND RESRC_OBJ_NM ='EXTNE580T020';
	
   EXCEPTION
   WHEN OTHERS
   THEN
      l_error_msg := 'Procedure itt_order_processor_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
      DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
      l_itt_err_tbl.DELETE;
      l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
      l_itt_err_tbl(1).procedure_name   := 'ITT_ORDER_PROCESSOR_LOV';
      l_itt_err_tbl(1).error_message    := l_error_msg;
      l_itt_err_tbl(1).created_by       := l_username;
      l_itt_err_tbl(1).creation_date    := SYSDATE;
      l_itt_err_tbl(1).last_updated_by  := l_username;
      l_itt_err_tbl(1).last_update_date := SYSDATE;
      canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_order_processor_lov;

   --------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible ITT Dealers/contact dtls in Dealer LOV field
   --------------------------------------------------------------------------------------------------------------------
   procedure itt_dealer_lov (p_dealer_name IN VARCHAR2, x_dealers_name OUT ref_order_details)
   AS
    l_dealer_name VARCHAR2(240) := NVL(p_dealer_name, '%');
   BEGIN		   
        OPEN x_dealers_name
        FOR
        SELECT  DISTINCT  
                NULL              vendor_id
               ,pv.prnt_vnd_nm    dealer_name
               ,pv.prnt_vnd_cd    supplier_code
               ,v.vnd_cd          dealer_code
               ,NULL              vendor_site_id
               ,v.first_line_addr address_line1
               ,v.scd_line_addr   address_line2
               ,v.third_line_addr address_line3
               ,v.cty_addr        city
               ,v.st_cd           state
               ,v.post_cd         zip
               ,v.ctry_cd         country
               ,NULL              vendor_contact_id
               ,cp.ctac_psn_first_nm  first_name
               ,cp.ctac_psn_mid_nm    middle_name
               ,cp.ctac_psn_last_nm   last_name
               ,NULL                  area_code
               ,phn.ds_ctac_pnt_val_txt   phone
               ,ml.ds_ctac_pnt_val_txt  email_address 
        FROM     prnt_vnd pv
               , vnd v
			   , vnd_tp_reln vr
			   , ds_ctac_psn_reln cpr
               , ctac_psn cp
               --, ds_ctac_psn dcp
			   , ds_ctac_pnt phn
               , ds_ctac_pnt ml
        WHERE   pv.prnt_vnd_pk = v.prnt_vnd_pk
		AND		pv.glbl_cmpy_cd = v.glbl_cmpy_cd
        --AND     v.pty_loc_pk = cp.pty_loc_pk(+)
       -- AND     cp.ctac_psn_pk = dcp.ctac_psn_pk(+)
	    AND     v.vnd_cd = vr.vnd_cd
        AND     vr.vnd_tp_cd = '15'
		AND     v.glbl_cmpy_cd = cpr.glbl_cmpy_cd(+)
        AND     v.loc_num = cpr.loc_num(+)
        AND     cpr.glbl_cmpy_cd = cp.glbl_cmpy_cd(+)
        AND     cpr.ctac_psn_pk = cp.ctac_psn_pk(+)
        AND     cpr.ctac_tp_cd(+) = 'BILL_SPLY'			--- Bill to Supply
        AND     cp.glbl_cmpy_cd = phn.glbl_cmpy_cd(+)
        AND     cp.ctac_psn_pk = phn.ctac_psn_pk(+)
        AND     phn.ds_ctac_pnt_tp_cd(+) = '02'   --- Phone
        AND     phn.ds_ctac_pnt_actv_flg(+) = 'Y'   --- Active Phone
        AND     cp.glbl_cmpy_cd = ml.glbl_cmpy_cd(+)
        AND     cp.ctac_psn_pk = ml.ctac_psn_pk(+)
        AND     ml.ds_ctac_pnt_tp_cd(+) = '04'   --Mail
        AND     ml.ds_ctac_pnt_actv_flg(+) = 'Y'
        AND     pv.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND     v.glbl_cmpy_cd  = g_glbl_cmpy_cd
        AND     cp.glbl_cmpy_cd(+)  = g_glbl_cmpy_cd
        AND     pv.ezcancelflag = '0'
        AND      v.ezcancelflag = '0'
		AND     vr.ezcancelflag = '0'
        AND     cp.ezcancelflag(+) = '0'
		AND     cpr.ezcancelflag(+) = '0'
        AND     phn.ezcancelflag(+) = '0'
        AND     ml.ezcancelflag(+) = '0'
        AND     pv.prnt_vnd_nm IS NOT NULL
        AND     UPPER (pv.prnt_vnd_nm) LIKE UPPER (l_dealer_name)
        AND  TO_CHAR(SYSDATE, 'YYYYMMDD') BETWEEN v.eff_from_dt AND nvl(v.eff_thru_dt,'99999999')
		/*AND  EXISTS (SELECT 'X' FROM vnd_tp_reln vr 
                  WHERE vr.vnd_tp_cd IN ('15') AND vr.vnd_cd = v.vnd_cd
                  AND   vr.ezcancelflag = '0' AND vr.glbl_cmpy_cd = g_glbl_cmpy_cd)*/
        ORDER BY pv.prnt_vnd_nm;

   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_dealer_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_DEALER_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_dealer_lov;

   --------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible ITT Line types in Line Type LOV field
   --------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_line_types (x_line_types OUT ref_order_details)
   AS
   BEGIN
      OPEN x_line_types
      FOR
         SELECT 'ITT DROP SHIP' description
         FROM DUAL;
         
         /**SELECT cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_LINE_TYPE'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;**/
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_cust_account_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_LINE_TYPES';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_line_types;

   ---------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible ITT CMAP values in CMAP LOV field
   ---------------------------------------------------------------------------------------------------------
   PROCEDURE itt_cmap_lov (x_cmap OUT ref_order_details)
   AS
   BEGIN
      OPEN x_cmap
      FOR
         SELECT cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_CMAP'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_cmap_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_CMAP_LOV';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_cmap_lov;

   ---------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT wkbn detail screen to populate eligible ITT product sourced in Sourced By LOV field
   ---------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_product_source_lov (x_product_sourced_by OUT ref_order_details)
   AS
   BEGIN
      OPEN x_product_sourced_by
      FOR
         SELECT cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_ITT_SOURCED'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_product_source_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_PRODUCT_SOURCE_LOV';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_product_source_lov;

   ----------------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT wkbn detail/Maintenance screen to populate eligible ITT bill cycle in Bill Cycle LOV field
   ----------------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_bill_cycle_lov (x_bill_cycle OUT ref_order_details)
   AS
   BEGIN
      OPEN x_bill_cycle
      FOR
         SELECT cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_BILL_CYCLE'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;
   EXCEPTION
     WHEN OTHERS
     THEN
        l_error_msg := 'Procedure itt_bill_cycle_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_BILL_CYCLE_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_bill_cycle_lov;

   -------------------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT wkbn detail/Maintenance screen to populate eligible Contract Types in Contract Type LOV field
   -------------------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_contract_type_lov (x_contract_type OUT ref_order_details)
   AS
   BEGIN
      OPEN x_contract_type
      FOR
         SELECT cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_CONTRACT_TYPE'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_contract_type_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_CONTRACT_TYPE_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_contract_type_lov;

   -----------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT wkbn detail/Maintenance screen to populate eligible Plan Types in Plan Type LOV field
   -----------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_plan_type_lov (x_plan_type OUT ref_order_details)
   AS
   BEGIN
      OPEN x_plan_type
      FOR
         SELECT cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_PLAN_TYPE'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_plan_type_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_PLAN_TYPE_LOV';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_plan_type_lov;

   --------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible Salesreps in Sales Rep LOV field
   --------------------------------------------------------------------------------------------------------
   PROCEDURE itt_salesrep_lov (p_sales_rep IN VARCHAR2, x_salesrep_lov OUT ref_order_details)
   AS
      l_sales_rep VARCHAR2(240) := NVL(p_sales_rep, '%');
   BEGIN
      OPEN x_salesrep_lov
      FOR
         SELECT DISTINCT toc_nm name
         FROM  toc
         WHERE glbl_cmpy_cd = g_glbl_cmpy_cd
         AND UPPER(toc_nm) LIKE UPPER(l_sales_rep)
         AND ezcancelflag = '0'
         ORDER BY 1;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_salesrep_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_SALESREP_LOV';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_salesrep_lov;

   ----------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible Sales Branches in Sales Branch LOV field
   ----------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_sales_branch_lov (p_sales_branch IN VARCHAR2, x_sales_branch OUT ref_order_details)
   AS
      l_sales_branch VARCHAR2(240) := NVL(p_sales_branch, '%');
   BEGIN
      OPEN x_sales_branch
      FOR
         SELECT DISTINCT br.coa_br_nm
         FROM   coa_br br,
                toc sr
         WHERE  br.coa_br_cd = sr.coa_br_cd
         AND br.glbl_cmpy_cd = 'ADB'
         AND sr.glbl_cmpy_cd = 'ADB'
         AND br.ezcancelflag = '0'
         AND sr.ezcancelflag = '0'
         AND UPPER(br.coa_br_nm) LIKE UPPER(l_sales_branch)
         ORDER BY 1;
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_sales_branch_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_SALES_BRANCH_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_sales_branch_lov;

   ----------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench screen to populate eligible Sales Zones in Sales Zone Dropdown field
   ----------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_sales_zone_lov (x_sales_zone OUT ref_order_details)
   AS
   BEGIN
      OPEN x_sales_zone
      FOR
         SELECT DISTINCT sales_zone ATTRIBUTE1
         FROM canon_e580_itt_headers_tbl
         WHERE sales_zone IS NOT NULL
         ORDER BY 1;         
         
         /**SELECT cvt.val2  ATTRIBUTE1
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_SALES_ZONE'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;**/
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_sales_zone_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_SALES_ZONE_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_sales_zone_lov;

   -------------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT workbench detail screen to populate eligible values in Mail Invoices To dropdown field
   -------------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_mail_invoices_to_lov (x_mail_invoices_to OUT ref_order_details)
   AS
   BEGIN
      OPEN x_mail_invoices_to
      FOR
         SELECT cvt.val2  flex_value
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_MAIL_INV_TO'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1;
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_mail_invoices_to_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_MAIL_INVOICES_TO_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_mail_invoices_to_lov;
   
   
   
   PROCEDURE itt_dealer_ship_to_cna_code (p_dealer_ship_to_cna_code IN VARCHAR2
                                         , x_dealer_ship_to_cna_code OUT ref_order_details)
    AS
    v_dealer_ship_to_cna_code VARCHAR2(240) := NVL(p_dealer_ship_to_cna_code, '%');
    BEGIN

        OPEN x_dealer_ship_to_cna_code
        FOR
		SELECT cvt.val1  ship_to_cna_code,
			   cvt.val2  ship_to_cna_address,
			   cvt.val3  ship_to_cna_address2,
			   cvt.val4  ship_to_cna_city,
			   cvt.val5  ship_to_cna_state,
			   cvt.val6  ship_to_cna_zip_code
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'E580_SHIP_TO_CODE_MAPPING'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
		 AND UPPER(cvt.val1) LIKE UPPER(v_dealer_ship_to_cna_code);
        
    EXCEPTION
	  WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_dealer_ship_to_cna_code terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_DEALER_SHIP_TO_CNA_CODE';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
      
    END itt_dealer_ship_to_cna_code;

   -----------------------------------------------------------------------------
   --This procedure invoked from ITT workbench UI to fetch list of reason codes
   -----------------------------------------------------------------------------
   PROCEDURE itt_schdl_deliv_dt_reason_code (x_schdl_deliv_dt_reason_code OUT ref_order_details)
   IS
   BEGIN
      OPEN x_schdl_deliv_dt_reason_code
      FOR
         SELECT DISTINCT 
                cvt.val1  flex_value_meaning
               ,cvt.val2  description
         FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
         WHERE  ct.cd_name = 'CANON_E580_REASON_CODES'
         AND    ct.cd_id = cvt.cd_id
         AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)
         ORDER BY cvt.val1, cvt.val2;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Procedure itt_schdl_deliv_dt_reason_code terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_SCHDL_DELIV_DT_REASON_CODE';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   PROCEDURE itt_edit_mode_check (p_user_name IN VARCHAR2, p_edit_mode OUT VARCHAR2)
   AS
      l_edit_mode VARCHAR2(240) := 'N';
   BEGIN
      /*SELECT DISTINCT 'Y'
      INTO   l_edit_mode
      FROM   role r
            ,usr_role ur
            ,auth_psn u
      WHERE  r.role_nm = 'E580_ADMN'
      AND    r.actv_flg = 'Y'
      AND    r.role_id = ur.role_id
      AND    ur.auth_psn_id = u.auth_psn_id
      AND    u.actv_flg = 'Y'
      AND    UPPER(u.usr_nm) = UPPER(p_user_name)
      AND    r.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND    u.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND    r.ezcancelflag = '0'
      AND    u.ezcancelflag = '0';*/
	  
	  SELECT DISTINCT 'Y'
      INTO   l_edit_mode
	  FROM AUTH_PSN AUTH,
       USR_ROLE US_R,
       ROLE R,
       RESRC_ROLE RR,
       PROT_RESRC PR,
       PROT_RESRC_OBJ PRO,
       RESRC_OBJ RO,
       RESRC_CLS RCLS
   WHERE 1=1   
       AND R.ACTV_FLG = 'Y'	
       AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
       AND US_R.ROLE_ID = R.ROLE_ID
       AND R.ROLE_ID = RR.ROLE_ID
       AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
       AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
       AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
       AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
       AND AUTH.EZCANCELFLAG = '0'
       AND US_R.EZCANCELFLAG = '0'
       AND R.EZCANCELFLAG = '0'
       AND RR.EZCANCELFLAG = '0'
       AND PR.EZCANCELFLAG = '0'
       AND RO.EZCANCELFLAG = '0'
       AND RCLS.EZCANCELFLAG = '0'
       AND RCLS.RESRC_CLS_NM = 'FUNCTION'
       AND UPPER(AUTH.USR_NM) = UPPER(p_user_name)
       AND RESRC_OBJ_NM ='EXTNE580T020';
	
      IF (l_edit_mode IS NULL)
      THEN
         l_edit_mode := 'N';
      END IF;

      p_edit_mode := l_edit_mode;
   EXCEPTION
      WHEN no_data_found
      THEN
         l_edit_mode := 'N';
         p_edit_mode := l_edit_mode;
      WHEN OTHERS
      THEN
         l_edit_mode := 'N';
         p_edit_mode := l_edit_mode;
         l_error_msg := 'Procedure itt_edit_mode_check terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_EDIT_MODE_CHECK';
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_edit_mode_check;

   PROCEDURE itt_lines_on_hold (  p_itt_number        IN  VARCHAR2
                                , x_lines_on_hold_lov OUT ref_order_details
								, x_svc_mach_config   OUT ref_order_details)
   IS
   BEGIN
      OPEN x_lines_on_hold_lov
      FOR
        SELECT
             cil.itt_number
           , cil.order_number         so_number
           , substr(cil.line_number,1,instr(cil.line_number,'.')-1)          
                                      so_line_number
           , ph.po_ord_num            po_number
           , pl.po_ord_dtl_line_num   po_line_number
           , cil.item
           , cil.item_description
           , NVL(h.rel_flg, 'N')      released_flag
           , (DECODE(NVL(h.rel_flg, 'N'), 'N','Y','N')) hold_flag
           , h.hld_pk       hold_pk
           , h.hld_rsn_cd   hold_rsn_cd
           , substr(cil.line_number,instr(cil.line_number,'.')+1)
                                      so_sub_line_number
		   , cil.line_id disp_line_number
		   , SUBSTR(cil.item, 1, 8) disp_item
		   , DECODE(ol.coa_mdse_tp_cd, '11', 'N', 'Y') deliv_conf_flag
		  -- , ol.svc_config_mstr_pk
          -- , smm.ser_num
          -- , smm.svc_mach_mstr_pk
          -- , smm.stk_sts_cd
          -- , smm.svc_mach_usg_sts_cd
          -- , smm.svc_mach_mstr_loc_sts_cd
        FROM canon_e580_itt_lines_tbl cil
           , cpo oh
           , cpo_dtl_v ol
           , po ph
           , po_dtl pl
           , hld h
		  -- , svc_mach_mstr smm
        WHERE cil.itt_number  = p_itt_number
        AND   cil.order_number = oh.cpo_ord_num
        AND   oh.cpo_ord_num   = ol.cpo_ord_num
        AND   cil.line_number  = ol.cpo_dtl_line_num||'.'||ol.cpo_dtl_line_sub_num
		--AND   ol.cpo_ord_num = smm.cpo_ord_num(+)
        --AND   ol.cpo_dtl_line_num = smm.cpo_dtl_line_num(+)
        --AND   ol.cpo_dtl_line_sub_num = smm.cpo_dtl_line_sub_num(+)
        --AND   ol.svc_config_mstr_pk = smm.svc_config_mstr_pk(+)
        AND   ph.po_ord_num = pl.po_ord_num
        AND   ph.po_ord_num = cil.cusa_po_number
        AND   ol.cpo_ord_num = pl.cpo_ord_num
        AND   ol.cpo_dtl_line_num  = pl.cpo_dtl_line_num
        AND   ol.cpo_dtl_line_sub_num = pl.cpo_dtl_line_sub_num
        AND   ol.cpo_ord_num = h.cpo_ord_num
        AND   ol.cpo_dtl_line_num = h.cpo_dtl_line_num
        AND   ol.cpo_dtl_line_sub_num = h.cpo_dtl_line_sub_num
		--AND   ol.coa_mdse_tp_cd NOT IN ('11', '97')
        AND   NVL(h.rel_flg ,'N') <> 'Y'
        AND   NVL(cil.cancelled_flag,'N') = 'N'
        AND   h.ezcancelflag  = 0
        AND   oh.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   ol.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   ph.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   pl.glbl_cmpy_cd = g_glbl_cmpy_cd
        AND   h.glbl_cmpy_cd  = g_glbl_cmpy_cd
		--AND   smm.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
        AND  oh.ezcancelflag = '0'
		--AND  smm.ezcancelflag(+) = '0'
        AND  ol.ezcancelflag = '0'
        AND  ph.ezcancelflag = '0'
        AND  pl.ezcancelflag = '0'
        ORDER BY 1,2,13;
		
	OPEN x_svc_mach_config
	FOR
	SELECT ol.svc_config_mstr_pk
		 , smm.ser_num
		 , smm.svc_mach_mstr_pk
		 , smm.stk_sts_cd
		 , smm.svc_mach_usg_sts_cd
		 , smm.svc_mach_mstr_loc_sts_cd
	  FROM canon_e580_itt_lines_tbl cil,
		   cpo_dtl ol,
		   svc_mach_mstr smm,
		   svc_config_mstr scm
	 WHERE cil.itt_number  = p_itt_number
       AND cil.order_number = ol.cpo_ord_num
	   AND cil.line_number  = ol.cpo_dtl_line_num||'.'||ol.cpo_dtl_line_sub_num
	   AND ol.cpo_dtl_line_num = smm.cpo_dtl_line_num
       AND ol.cpo_dtl_line_sub_num = smm.cpo_dtl_line_sub_num
       AND ol.svc_config_mstr_pk = smm.svc_config_mstr_pk
	   AND smm.svc_config_mstr_pk = scm.svc_config_mstr_pk
	   AND smm.svc_mach_mstr_pk = scm.svc_mach_mstr_pk
	   AND ol.ezcancelflag = '0'
	   AND smm.ezcancelflag = '0'
	   AND scm.ezcancelflag = '0'
	   AND ol.glbl_cmpy_cd = g_glbl_cmpy_cd
	   AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
	   AND scm.glbl_cmpy_cd = g_glbl_cmpy_cd;
		
      /**
      OPEN x_lines_on_hold_lov
      FOR
       SELECT 
          'IT-101188-01'  itt_number
         ,'101188'   so_number
         ,'001'        so_line_number
         ,null         po_number
         ,null         po_line_number
         ,'223ZZ110'  item
         ,'NIPA REBATE'  item_description
         ,'N'         released_flag
         ,'Y'         hold_flag
         ,361532        hold_pk
         ,'053'       hold_rsn_cd
         ,'001'       so_sub_line_number
        FROM DUAL;
        ***/

   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Procedure itt_lines_on_hold_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_LINES_ON_HOLD_LOV';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_username;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_username;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   PROCEDURE itt_markview_attached_docs (    p_itt_number IN VARCHAR2
                                           , p_user_name IN VARCHAR2
                                           , x_markview_attached_docs OUT ref_order_details
                                        )
   AS
      l_itt_number    VARCHAR2(240) := p_itt_number;
      l_user_name     VARCHAR2(240) := p_user_name;
      l_so_number     VARCHAR2(240) := NULL;
      l_so_header_id  NUMBER := -1;
   BEGIN
      BEGIN
         SELECT DISTINCT order_number, header_id
         INTO l_so_number, l_so_header_id
         FROM  canon_e580_itt_headers_tbl
         WHERE itt_number = l_itt_number;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_error_msg := 'Error while querying sales order number ' ||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
            DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
            l_itt_err_tbl.DELETE;
            l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
            l_itt_err_tbl(1).procedure_name   := 'ITT_MARKVIEW_ATTACHED_DOCS';
            l_itt_err_tbl(1).itt_number       := l_itt_number;
            l_itt_err_tbl(1).error_message    := l_error_msg;
            l_itt_err_tbl(1).created_by       := l_user_name;
            l_itt_err_tbl(1).creation_date    := SYSDATE;
            l_itt_err_tbl(1).last_updated_by  := l_user_name;
            l_itt_err_tbl(1).last_update_date := SYSDATE;
            canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
      END;

      OPEN x_markview_attached_docs
      FOR
		SELECT CAST (TO_TIMESTAMP (a.ezintime, 'RRRRMMDDHH24MISSFF3') AS DATE) attachment_date,
		    a.att_data_desc_txt description,
			a.att_data_nm document_id,
			a.oth_sys_url markview_doc_link
		FROM att_data a
		WHERE att_doc_tp_cd='70' 
		AND att_data_tp_cd='TF'
		AND att_data_grp_txt = l_so_number;

         
         /***SELECT adoc.creation_date attachment_date,
                docs_tl.description    description,
                docs_tl.file_name  document_id,
                (SELECT mvp.DEFAULT_VALUE || '/MVW_Client.MVSD?D='
                 FROM markview.mv_preference@inidev mvp
                 WHERE mvp.preference_name = 'WEBCLNT_HTTP_BASE_URL')||docs_tl.file_name  markview_doc_link
         FROM fnd_attached_documents@inidev adoc,
              fnd_documents@inidev docs,
              fnd_documents_tl@inidev docs_tl
         WHERE     1 = 1
         AND adoc.document_id = docs.document_id
         AND docs.document_id = docs_tl.document_id
         AND docs_tl.LANGUAGE = USERENV ('LANG')
         AND adoc.entity_name = 'OE_ORDER_HEADERS'
         AND docs.datatype_id = 5
         AND pk1_value = l_so_header_id
         ORDER BY adoc.creation_date ASC;**/
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_markview_attached_docs terminated due to ' ||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_MARKVIEW_ATTACHED_DOCS';
         l_itt_err_tbl(1).itt_number       := l_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := l_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_markview_attached_docs;

   PROCEDURE itt_deliv_dt_reschdl_history (  p_itt_number               IN  VARCHAR2
                                           , p_line_number              IN  VARCHAR2
                                           , x_deliv_dt_reschdl_history OUT ref_order_details
                                          )
   IS
   BEGIN
      OPEN x_deliv_dt_reschdl_history
      FOR
         SELECT cds.version_number version_number,
                cds.last_updated_by last_update_by,
                cds.scheduled_delivery_date scheduled_delivery_date,
                cds.schedule_code reason_code,
                cds.schedule_status schedule_status,
                cds.last_update_date last_update_date
         FROM canon_e580_dely_schedule_hist cds,
			  canon_e580_itt_lines_tbl cil
		 WHERE 1 = 1
		   AND cil.itt_number = cds.itt_number
		   AND cil.line_number = cds.line_number
		   AND cil.itt_number = p_itt_number
		   AND cil.line_number = p_line_number
		   AND NVL(cil.cancelled_flag,'N') = 'N'
		   ORDER BY cds.version_number DESC;

   END;

   ---Update/Save procedures...
   PROCEDURE itt_header_update (   p_so_number               IN VARCHAR2
                                 , p_itt_number              IN VARCHAR2
                                 , p_user_name               IN VARCHAR2
                                 , p_itt_status              IN VARCHAR2
                                 , p_itt_admin_owner         IN VARCHAR2
                                 , p_dealer                  IN VARCHAR2
                                 , p_dealer_whse_code        IN VARCHAR2
                                 , p_dealer_address_line1    IN VARCHAR2
                                 , p_dealer_address_line2    IN VARCHAR2
                                 , p_dealer_address_line3    IN VARCHAR2
                                 , p_dealer_city             IN VARCHAR2
                                 , p_dealer_state            IN VARCHAR2
                                 , p_dealer_zip              IN VARCHAR2
                                 , p_dealer_country          IN VARCHAR2
                                 , p_dealer_contact_number   IN VARCHAR2
                                 , p_dealer_email            IN VARCHAR2
                                 , p_cmap_yes_no             IN VARCHAR2
                                 , p_product_sourced_by      IN VARCHAR2
                                 , p_dealer_contact          IN VARCHAR2
                                 , p_mail_invoices_to        IN VARCHAR2
                                 , p_cust_contact_name       IN VARCHAR2
                                 , p_cust_contact_phone      IN VARCHAR2
                                 , p_cust_contact_email      IN VARCHAR2
                                 , p_cust_contact_fax        IN VARCHAR2
                                 , p_dealer_ship_to_cna_code IN VARCHAR2
                                 , p_vendor_id               IN NUMBER
                                 , p_vendor_site_id          IN NUMBER
                                 , p_vendor_contact_id       IN NUMBER
                                 , p_prnt_vnd_cd             IN VARCHAR2
                               )
   AS
      l_so_number             VARCHAR2(240) := p_so_number;
      l_itt_number            VARCHAR2(240) := p_itt_number;
      l_itt_admin_owner       VARCHAR2(80)  := p_itt_admin_owner;
      l_dealer                VARCHAR2(240) := p_dealer;
      l_dealer_whse_code      VARCHAR2(100) := p_dealer_whse_code;
      l_dealer_address_line1  VARCHAR2(240) := p_dealer_address_line1;
      l_dealer_address_line2  VARCHAR2(240) := p_dealer_address_line2;
      l_dealer_address_line3  VARCHAR2(240) := p_dealer_address_line3;
      l_dealer_city           VARCHAR2(240) := p_dealer_city;
      l_dealer_state          VARCHAR2(240) := p_dealer_state;
      l_dealer_zip            VARCHAR2(240) := p_dealer_zip;
      l_dealer_country        VARCHAR2(240) := p_dealer_country;
      l_dealer_contact        VARCHAR2(240) := p_dealer_contact;
      l_dealer_contact_number VARCHAR2(240) := p_dealer_contact_number;
      l_dealer_email          VARCHAR2(240) := p_dealer_email;
      l_cmap_yes_no           VARCHAR2(80)  := p_cmap_yes_no;
      l_product_sourced_by    VARCHAR2(240) := p_product_sourced_by;
      l_mail_invoices_to      VARCHAR2(240) := p_mail_invoices_to;
      l_cust_contact_name     VARCHAR2(240) := p_cust_contact_name;
      l_cust_contact_phone    VARCHAR2(240) := p_cust_contact_phone;
      l_cust_contact_email    VARCHAR2(240) := p_cust_contact_email;
      l_cust_contact_fax      VARCHAR2(240) := p_cust_contact_fax;
	  l_dealer_ship_to_cna_code      VARCHAR2(240) := p_dealer_ship_to_cna_code;
      l_user_name             VARCHAR2(240) := p_user_name;
      l_user_id               VARCHAR2(20);
      l_vendor_id             NUMBER := NVL(p_vendor_id, -1);
      l_vendor_site_id        NUMBER := NVL(p_vendor_site_id, -1);
      l_vendor_contact_id     NUMBER := NULL; --NVL(p_vendor_contact_id, -1);
   BEGIN
      IF l_dealer IS NULL
      THEN
         l_vendor_id := -1;
         l_vendor_site_id := -1;
         l_dealer_whse_code := NULL;
         l_dealer_address_line1 := NULL;
         l_dealer_address_line2 := NULL;
         l_dealer_address_line3 := NULL;
         l_dealer_city := NULL;
         l_dealer_state := NULL;
         l_dealer_zip := NULL;
         l_dealer_country := NULL;
         l_dealer_contact := NULL;
         l_dealer_contact_number := NULL;
         l_dealer_email := NULL;
      END IF;

      BEGIN
         UPDATE  canon_e580_itt_headers_tbl
         SET     itt_admin_owner         = l_itt_admin_owner
               , dealer                  = l_dealer
               , dealer_whse_code        = l_dealer_whse_code
               , vnd_cd                  = l_dealer_whse_code
               , dealer_address_line1    = l_dealer_address_line1
               , dealer_address_line2    = l_dealer_address_line2
               , dealer_address_line3    = l_dealer_address_line3
               , dealer_city             = l_dealer_city
               , dealer_state            = l_dealer_state
               , dealer_zip              = l_dealer_zip
               , dealer_country          = l_dealer_country
               , dealer_contact_number   = l_dealer_contact_number
               , dealer_email            = l_dealer_email
               , cmap_yes_no             = l_cmap_yes_no
               , product_sourced_by      = l_product_sourced_by
               , dealer_contact          = l_dealer_contact
               , mail_invoices_to        = l_mail_invoices_to
               , customer_contact_name   = l_cust_contact_name
               , customer_contact_phone  = l_cust_contact_phone
               , customer_contact_email  = l_cust_contact_email
               , customer_contact_fax    = l_cust_contact_fax
			   , dealer_ship_to_cna_code = l_dealer_ship_to_cna_code
               , vendor_id               = l_vendor_id
               , vendor_site_id          = l_vendor_site_id
               , last_updated_by         = l_user_name
               , last_update_date        = SYSDATE
               , prnt_vnd_cd             = p_prnt_vnd_cd
         WHERE order_number = l_so_number
         AND   itt_number = l_itt_number;

         COMMIT;

         UPDATE canon_e580_itt_headers_tbl cih
         SET cih.itt_status = ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                WHERE  ct.cd_name = 'CANON_E580_HDR_STATUS'
                                AND    ct.cd_id = cvt.cd_id
                                AND    cvt.val51 = 2  ---In process
                                AND    (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
         WHERE cih.order_number = l_so_number
         AND   cih.itt_number = l_itt_number
         AND   cih.itt_status = (SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                 WHERE  ct.cd_name = 'CANON_E580_HDR_STATUS'
                                 AND    ct.cd_id = cvt.cd_id
                                 AND    cvt.val51 = 1
                                 AND    (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
                                );

        UPDATE canon_e580_itt_lines_tbl cil
        SET    cil.customer_contact_name = l_cust_contact_name
             , cil.customer_contact_phone = l_cust_contact_phone
             , cil.customer_contact_email = l_cust_contact_email
             , cil.customer_contact_fax = l_cust_contact_fax
        WHERE  order_number = l_so_number
        AND    itt_number = l_itt_number;

        COMMIT;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_error_msg := 'Error while updating canon_e580_itt_headers_tbl. ' || SQLCODE || ' - ' || SQLERRM;
            DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
            l_itt_err_tbl.DELETE;
            l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
            l_itt_err_tbl(1).procedure_name   := 'ITT_HEADER_UPDATE';
            l_itt_err_tbl(1).itt_number       := l_itt_number;
            l_itt_err_tbl(1).error_message    := l_error_msg;
            l_itt_err_tbl(1).value1           := 'order_number = '||l_so_number;
            l_itt_err_tbl(1).created_by       := l_user_name;
            l_itt_err_tbl(1).creation_date    := SYSDATE;
            l_itt_err_tbl(1).last_updated_by  := l_user_name;
            l_itt_err_tbl(1).last_update_date := SYSDATE;
            canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
      END;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_error_msg := 'Procedure itt_header_update terminated due to ' || SQLCODE || ' - ' || SQLERRM;
         DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_HEADER_UPDATE';
         l_itt_err_tbl(1).itt_number       := l_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := 'order_number = '||l_so_number;
         l_itt_err_tbl(1).created_by       := l_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := l_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END itt_header_update;

   PROCEDURE itt_delivery_conf_date_update (  p_itt_number              IN  VARCHAR2
                                            , p_line_number             IN  VARCHAR2
                                            , p_user_name               IN  VARCHAR2
                                            , p_delivery_scheduled_date IN  DATE
                                            , p_schedule_code           IN  VARCHAR2
                                            , p_return_code             OUT VARCHAR2
                                            , p_return_message          OUT VARCHAR2
                                           )
   IS
      l_itt_number        VARCHAR2(80) := p_itt_number;
      l_line_number       VARCHAR2(80)  := p_line_number;
      l_delivery_sch_date DATE          := p_delivery_scheduled_date;
      l_schedule_code     VARCHAR2(240) := p_schedule_code;
      l_user_name         VARCHAR2(240) := p_user_name;
      l_return_code       VARCHAR2(24)  := 'SUCCESS';
      l_return_message    VARCHAR2(4000):= 'Success. ';
      l_so_header_id      NUMBER := -1;
      l_so_line_id        NUMBER := -1;
      l_version_number    NUMBER := 0;
   BEGIN
   
     BEGIN

      DBMS_OUTPUT.PUT_LINE('Insert into canon_e580_dely_schedule_hist. ');

	  BEGIN

	  SELECT NVL((MAX(version_number) + 1), 1)
	  INTO l_version_number
	  FROM canon_e580_dely_schedule_hist  cds
	  WHERE cds.itt_number = l_itt_number
	    AND cds.line_number = l_line_number;

	  EXCEPTION
	  WHEN NO_DATA_FOUND THEN
	  l_version_number := 1;

	  WHEN OTHERS THEN
	  l_version_number := 1;
	  END;

   INSERT INTO canon_e580_dely_schedule_hist(itt_number,
											 line_number,
											 version_number,
											 scheduled_delivery_date,
											 schedule_code,
											 schedule_status,
											 scheduled_by,
											 created_by,
											 creation_date,
											 last_updated_by,
											 last_update_date)
									 VALUES (
										     l_itt_number,
										     l_line_number,
										     l_version_number,
										     l_delivery_sch_date,
										     CASE l_version_number
											   WHEN 0 THEN 'INITIAL INSERT'
											   WHEN 1 THEN NVL(l_schedule_code, 'INITIAL UPDATE')
											   ELSE l_schedule_code
										     END,
										    'SCHEDULED',
										    l_user_name,
										    l_user_name,
										    SYSDATE,
										    l_user_name,
										    SYSDATE
										  );
										  
    COMMIT;
	
	p_return_code    := l_return_code;
    p_return_message := l_return_message;

  EXCEPTION
  WHEN OTHERS
  THEN

	l_error_msg := 'Error while inserting values into canon_e580_dely_schedule_hist  :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_DELIVERY_CONF_DATE_UPDATE';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).line_number      := l_line_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := NULL;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);

	 p_return_code    := 'ERROR';
     p_return_message := l_error_msg;

  END;

END;

   -------------------------------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Save action when the selected Item is not EXPENSE
   --Updates the latest UI passed values in ITT lines table canon_e580_itt_lines_tbl
   -------------------------------------------------------------------------------------------------
   PROCEDURE itt_goods_line_update (  p_itt_number              IN  VARCHAR2
                                    , p_line_number             IN  VARCHAR2
                                    , p_user_name               IN  VARCHAR2
                                    , p_itt_status              IN  VARCHAR2
                                    , p_delivery_scheduled_date IN  DATE
                                    , p_install_credit          IN  NUMBER
                                    , p_finder_fee              IN  NUMBER
                                    , p_serial_number           IN  VARCHAR2
                                    , p_reason_code             IN  VARCHAR2
                                    , p_po_number               IN  VARCHAR2
                                    , p_product_sourced_by      IN  VARCHAR2
                                    , p_item_purchase_price     IN  NUMBER
                                    , p_return_code             OUT VARCHAR2
                                    , p_return_message          OUT VARCHAR2
                                   )
   IS
      l_product_code      VARCHAR2(240);
      l_return_code       VARCHAR2(24)   := 'SUCCESS';
      l_return_mesg       VARCHAR2(4000) := 'Success';
      l_itt_line_type     VARCHAR2(80);
      l_old_dlv_sch_dt    DATE;
      l_old_reason_cd     VARCHAR2(240);
   BEGIN   
      l_debug_step := 1;
      BEGIN
         SELECT cil.product_code, cil.itt_line_type, cil.delivery_scheduled_date, cil.reason_code
         INTO   l_product_code, l_itt_line_type, l_old_dlv_sch_dt, l_old_reason_cd
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_number = p_itt_number
         AND    NVL(cil.cancelled_flag,'N') = 'N'
         AND    cil.line_number = p_line_number;

         IF (l_product_code = '99' AND p_product_sourced_by = 'CSA')
         THEN
            l_return_code := 'ERROR';
            l_return_mesg := 'Line# '||p_line_number||' - Third party items cannot be sourced by CSA. ';
         END IF;
      EXCEPTION
         WHEN OTHERS THEN
            l_error_msg := 'ITT number : '||p_itt_number||' Line Number : '||p_line_number||' does not exists, Error :'||SQLERRM;
      END;

      l_debug_step := 2;
      IF (l_return_code = 'SUCCESS')
      THEN
         IF (NVL(l_old_dlv_sch_dt, '01-JAN-1900') <> p_delivery_scheduled_date) OR (NVL(l_old_reason_cd, 'XXX') <> p_reason_code)
         THEN
			itt_delivery_conf_date_update (  p_itt_number               => p_itt_number
                                            , p_line_number             => p_line_number
                                            , p_user_name               => p_user_name
                                            , p_delivery_scheduled_date => p_delivery_scheduled_date
                                            , p_schedule_code           => p_reason_code  
                                            , p_return_code             => l_return_code
                                            , p_return_message          => l_return_mesg
                                           );

         ELSE
            dbms_output.put_line('No change in Delivery_scheduled_date or Reason code.. ');
         END IF;

         l_debug_step := 3;
         UPDATE canon_e580_itt_lines_tbl
         SET    install_credit          = NVL(p_install_credit, 0)
              , finder_fee              = NVL(p_finder_fee, 0)
              , delivery_scheduled_date = p_delivery_scheduled_date
              , itt_status = ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                               WHERE  ct.cd_name = 'CANON_E580_LINE_STATUS'
                               AND    ct.cd_id = cvt.cd_id
                               AND    cvt.val2 = p_itt_status
                               AND    (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
                              )
              , product_sourced_by  = p_product_sourced_by
              , item_purchase_price = p_item_purchase_price
              , reason_code         = NVL2(p_delivery_scheduled_date, p_reason_code, NULL)
              , last_updated_by     = p_user_name
              , last_update_date    = SYSDATE
         WHERE  itt_number    = p_itt_number
         AND    line_number   = p_line_number
         AND    itt_line_type = 'GOODS';

         COMMIT;
      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred at debug step #'||l_debug_step||', Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_GOODS_LINE_UPDATE';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).line_number      := p_line_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := ' itt_line_type = GOODS, itt_status = '||p_itt_status;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   ---------------------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Save action when Expense line is added.
   --Logic to insert the Expense line details in ITT lines table.
   ---------------------------------------------------------------------------------------
   PROCEDURE itt_expense_line_insert (  p_so_number           IN VARCHAR2
                                      , p_itt_number          IN VARCHAR2
                                      , p_user_name           IN VARCHAR2
                                      , p_item                IN VARCHAR2
                                      , p_item_description    IN VARCHAR2
                                      , p_ordered_quantity    IN NUMBER
                                      , p_item_purchase_price IN NUMBER
                                      , p_install_credit      IN NUMBER
                                      , p_finder_fee          IN NUMBER
                                     )
   IS
      l_line_number   VARCHAR2(80);
   BEGIN
      l_debug_step := 1;
      SELECT LPAD((NVL(MAX(line_number),0)+1),3,'0')
      INTO   l_line_number
      FROM   canon_e580_itt_lines_tbl cil
      WHERE  itt_number    = p_itt_number
      AND    order_number  = p_so_number
      AND    itt_line_type = 'EXPENSE';

      l_debug_step := 2;
      INSERT INTO canon_e580_itt_lines_tbl
            (  order_number
             , itt_number
             , line_number
			 , line_id
             , item
             , item_description
             , ordered_quantity
             , item_purchase_price
             , install_credit
             , finder_fee
             , itt_line_type
             , product_sourced_by
             , created_by
             , creation_date
             , last_updated_by
             , last_update_date
            )
      VALUES
            (  p_so_number
             , p_itt_number
             , l_line_number
			 , l_line_number
             , p_item
             , p_item_description
             , p_ordered_quantity
             , p_item_purchase_price
             , p_install_credit
             , p_finder_fee
             , 'EXPENSE'
             , 'DEALER'
             , p_user_name
             , SYSDATE
             , p_user_name
             , SYSDATE
            );
      COMMIT;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred at debug step #'||l_debug_step||', Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_EXPENSE_LINE_INSERT';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).line_number      := l_line_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := 'Line Number = '||l_line_number||', item = '||p_item||', itt_line_type = EXPENSE';
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   --------------------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Save action when Expense line updated.
   --Logic to update the Expense line details in ITT lines table.
   --------------------------------------------------------------------------------------
   PROCEDURE itt_expense_line_update(  p_itt_number          IN VARCHAR2
                                     , p_line_number         IN VARCHAR2
                                     , p_user_name           IN VARCHAR2
                                     , p_item                IN VARCHAR2
                                     , p_item_description    IN VARCHAR2
                                     , p_ordered_quantity    IN NUMBER
                                     , p_item_purchase_price IN NUMBER
                                     , p_install_credit      IN NUMBER
                                     , p_finder_fee          IN NUMBER
                                     , p_po_number           IN VARCHAR2
                                    )
   IS
   BEGIN
      l_debug_step := 1;
      UPDATE canon_e580_itt_lines_tbl
      SET    item                = p_item
            ,item_description    = p_item_description
            ,ordered_quantity    = p_ordered_quantity
            ,item_purchase_price = p_item_purchase_price
            ,cusa_po_number      = p_po_number
            ,last_updated_by     = p_user_name
            ,last_update_date    = SYSDATE
      WHERE  itt_number  = p_itt_number
      AND    line_number = p_line_number
      AND    itt_line_type = 'EXPENSE';

      l_debug_step := 2;
      COMMIT;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred at debug step #'||l_debug_step||', Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_EXPENSE_LINE_UPDATE';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).line_number      := p_line_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := 'itt_line_type = EXPENSE';
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   ------------------------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Save action when notes details to be saved
   --Logic to insert the notes sectiond details in ITT notes table canon_e580_notes_tbl
   ------------------------------------------------------------------------------------------
   PROCEDURE itt_notes_insert (   p_so_number    IN VARCHAR2
                                , p_itt_number   IN VARCHAR2
                                , p_user_name    IN VARCHAR2
                                , p_notes        IN VARCHAR2
                                , p_add_to_popdf IN VARCHAR2
                              )
   IS
      l_seq_no NUMBER := 0;
   BEGIN
      l_debug_step := 1;
      BEGIN
         SELECT NVL(MAX(seq_number),0)+1
         INTO   l_seq_no
         FROM   canon_e580_notes_tbl
         WHERE  itt_number =  p_itt_number
         AND    order_number = p_so_number;
      EXCEPTION
         WHEN OTHERS THEN
            l_seq_no := 1;
      END;

      INSERT INTO canon_e580_notes_tbl
                             (    itt_number
                                 ,order_number
                                 ,seq_number
                                 ,notes
                                 ,add_to_po_pdf
                                 ,created_by
                                 ,creation_date
                                 ,last_updated_by
                                 ,last_update_date
                             )
                     VALUES (     p_itt_number
                                 ,p_so_number
                                 ,l_seq_no
                                 ,p_notes
                                 ,NVL(p_add_to_popdf, 'N')
                                 ,p_user_name
                                 ,SYSDATE
                                 ,p_user_name
                                 ,SYSDATE
                            );
      COMMIT;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred at debug step #'||l_debug_step||', Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_NOTES_INSERT';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := ' Order_number = '||p_so_number;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   ------------------------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Save action when notes details to be saved
   --Logic to update the notes sectiond details in ITT notes table canon_e580_notes_tbl
   ------------------------------------------------------------------------------------------
   PROCEDURE itt_notes_update (   p_so_number      IN VARCHAR2
                                , p_itt_number     IN VARCHAR2
                                , p_user_name      IN VARCHAR2
                                , p_seq_number     IN NUMBER
                                , p_add_to_popdf   IN VARCHAR2
                               )
   IS
   BEGIN
      UPDATE canon_e580_notes_tbl cnt
      SET    cnt.add_to_po_pdf    = p_add_to_popdf
           , cnt.last_update_date = SYSDATE
           , cnt.last_updated_by  = p_user_name
      WHERE  cnt.itt_number       = p_itt_number
      AND    cnt.seq_number       = p_seq_number;

      COMMIT;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred at debug step #'||l_debug_step||', Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_NOTES_UPDATE';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := ' Seq_number = '||p_seq_number;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   ----------------------------------------------------------------------------------------------
   --This procedure invoked by ITT workbench UI on Save action when timestamp details to be saved
   --Logic to update the timestamp section details in ITT lines table canon_e580_itt_lines_tbl
   ----------------------------------------------------------------------------------------------
   PROCEDURE itt_timestamp_update (   p_itt_number               IN VARCHAR2
                                    , p_user_name                IN VARCHAR2
                                    , p_po_date                  IN DATE
                                    , p_po_accepted_date_by_dlr  IN DATE
                                    , p_po_sent_date_to_cusa     IN DATE
                                    , p_shipped_date_from_cusa   IN DATE
                                    , p_equip_arrive_at_dlr_date IN DATE
                                    , p_pod_rcvd_from_dlr_date   IN DATE
                                    , p_cna_po_aprvd_by_dlr_date IN DATE
                                   )
   IS
   BEGIN
      UPDATE canon_e580_itt_lines_tbl cil
      SET    cil.po_accepted_date_by_dlr  = p_po_accepted_date_by_dlr
           , cil.po_sent_date_to_cusa     = p_po_sent_date_to_cusa
           , cil.shipped_date_from_cusa   = p_shipped_date_from_cusa
           , cil.equip_arrive_at_dlr_date = p_equip_arrive_at_dlr_date
           , cil.pod_rcvd_from_dlr_date   = p_pod_rcvd_from_dlr_date
           , cil.cna_po_aprvd_by_dlr_date = p_cna_po_aprvd_by_dlr_date
           , cil.last_updated_by          = p_user_name
           , cil.last_update_date         = SYSDATE
      WHERE  cil.itt_line_type            = 'GOODS'
      AND    cil.itt_number = p_itt_number;

      COMMIT;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Error occurred at debug step #'||l_debug_step||', Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_TIMESTAMP_UPDATE';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).value1           := ' itt_line_type = GOODS ';
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   PROCEDURE itt_update_pricing (  p_itt_number         IN VARCHAR2,
                                   p_user_name          IN VARCHAR2,
                                   p_equip_model        IN VARCHAR2,
                                   p_meter_type         IN VARCHAR2,
                                   p_contract_type      IN VARCHAR2,
                                   p_overage_rate       IN NUMBER,
                                   p_plan_type          IN VARCHAR2,
                                   p_term               IN NUMBER,
                                   p_base_price         IN NUMBER,
                                   p_base_bill_cycle    IN VARCHAR2,
                                   p_overage_bill_cycle IN VARCHAR2,
                                   p_copy_inclusion     IN NUMBER,
                                   p_multiplier         IN NUMBER
								   --p_svc_meter_pkg      IN VARCHAR2,
								   --p_hard_meter_counter IN VARCHAR2,
								   --p_order_number		IN VARCHAR2
                                )
   IS
   
  --l_pricing_mode VARCHAR2(10);
  -- l_pricing_exists VARCHAR2(10);
   
   BEGIN
   
	/*BEGIN
		SELECT count(*)
		INTO l_pricing_exists
		FROM canon_e580_service_pricing_tbl
		WHERE itt_number = p_itt_number
		  AND meter_type = p_meter_type ;
		
	EXCEPTION
	WHEN OTHERS THEN
		l_pricing_exists := 0;
	END;
	
    IF l_pricing_exists = 0 THEN
	  BEGIN
	   INSERT INTO canon_e580_service_pricing_tbl
					(header_id,
					itt_number,
					equip_model,
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
					created_by,
					creation_date,
					last_updated_by,
					last_update_date)
		VALUES
					(
					p_order_number,
					p_itt_number,
					p_equip_model,
					p_svc_meter_pkg,
					p_contract_type,
					p_plan_type,
					p_term,
					p_base_price,
					p_base_bill_cycle,
					p_overage_bill_cycle,
					p_meter_type,
					p_hard_meter_counter,
					p_overage_rate,
					p_copy_inclusion,
					p_multiplier,
					p_user_name,
					sysdate,
					p_user_name,
					sysdate
					);
					
		COMMIT;
		
		EXCEPTION
		WHEN OTHERS THEN
			 l_error_msg := 'Error occurred while Inserting Service pricing details, Error Message :'||SQLERRM;
			 dbms_output.put_line(l_error_msg);
			 l_itt_err_tbl.DELETE;
			 l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
			 l_itt_err_tbl(1).procedure_name   := 'ITT_UPDATE_PRICING';
			 l_itt_err_tbl(1).itt_number       := p_itt_number;
			 l_itt_err_tbl(1).error_message    := l_error_msg;
			 l_itt_err_tbl(1).value1           := ' equip_model = '||p_equip_model||' AND meter_type = '||p_meter_type;
			 l_itt_err_tbl(1).created_by       := p_user_name;
			 l_itt_err_tbl(1).creation_date    := SYSDATE;
			 l_itt_err_tbl(1).last_updated_by  := p_user_name;
			 l_itt_err_tbl(1).last_update_date := SYSDATE;
			 canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
		END;
		
	ELSE*/
	
	   BEGIN
		  UPDATE canon_e580_service_pricing_tbl csp
		  SET    csp.contract_type      = p_contract_type,
				 csp.overage_rate       = p_overage_rate,
				 csp.plan_type          = p_plan_type,
				 csp.term               = p_term,
				 csp.base_price         = p_base_price,
				 csp.base_bill_cycle    = p_base_bill_cycle,
				 csp.overage_bill_cycle = p_overage_bill_cycle,
				 csp.copy_inclusion     = p_copy_inclusion,
				 csp.multiplier         = p_multiplier,
				 csp.last_updated_by    = p_user_name,
				 csp.last_update_date   = SYSDATE
		  WHERE csp.itt_number  = p_itt_number
		  AND   csp.equip_model = p_equip_model
		  AND   csp.meter_type  = p_meter_type;

		  COMMIT;
	   EXCEPTION
		  WHEN OTHERS THEN
			 l_error_msg := 'Error occurred while updating Service pricing details, Error Message :'||SQLERRM;
			 dbms_output.put_line(l_error_msg);
			 l_itt_err_tbl.DELETE;
			 l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
			 l_itt_err_tbl(1).procedure_name   := 'ITT_UPDATE_PRICING';
			 l_itt_err_tbl(1).itt_number       := p_itt_number;
			 l_itt_err_tbl(1).error_message    := l_error_msg;
			 l_itt_err_tbl(1).value1           := ' equip_model = '||p_equip_model||' AND meter_type = '||p_meter_type;
			 l_itt_err_tbl(1).created_by       := p_user_name;
			 l_itt_err_tbl(1).creation_date    := SYSDATE;
			 l_itt_err_tbl(1).last_updated_by  := p_user_name;
			 l_itt_err_tbl(1).last_update_date := SYSDATE;
			 canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
		END;
	--END IF;
	
   END;

   PROCEDURE itt_po_pdf_history ( p_package_name     VARCHAR2,
                                  p_procedure_name   VARCHAR2,
                                  p_itt_number       VARCHAR2,
                                  p_user_name        VARCHAR2,
                                  p_value1           VARCHAR2,
                                  p_value2           VARCHAR2,
                                  p_value3           VARCHAR2,
                                  p_value4           VARCHAR2,
                                  p_value5           VARCHAR2,
                                  p_value6           VARCHAR2,
                                  p_status_message   VARCHAR2
                                )
   IS
      l_seq_number   NUMBER := 0;
   BEGIN
      SELECT NVL(MAX(seq_number), 0) + 1
      INTO   l_seq_number
      FROM   canon_e580_popdf_history_tbl
      WHERE  itt_number = p_itt_number;

      INSERT INTO canon_e580_popdf_history_tbl
                  (    package_name
                     , procedure_name
                     , itt_number
                     , seq_number
                     , user_name
                     , value1
                     , value2
                     , value3
                     , value4
                     , value5
                     , value6
                     , status_message
                     , created_by
                     , creation_date
                     , last_updated_by
                     , last_update_date )
          VALUES (     p_package_name
                     , p_procedure_name
                     , p_itt_number
                     , l_seq_number
                     , p_user_name
                     , p_value1
                     , p_value2
                     , p_value3
                     , p_value4
                     , p_value5
                     , p_value6
                     , p_status_message
                     , p_user_name
                     , SYSDATE
                     , p_user_name
                     , SYSDATE
                 );

      IF (l_seq_number = 1) THEN
         UPDATE canon_e580_itt_lines_tbl cil
         SET cil.itt_status = (SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                               WHERE  ct.cd_name = 'CANON_E580_LINE_STATUS'
                               AND    ct.cd_id = cvt.cd_id
                               AND    cvt.val51 = 3  --Pending Dealer PO Approval
                               AND    (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
                              )
         WHERE cil.itt_number  = p_itt_number
         AND cil.itt_line_type = 'GOODS';
      END IF;

      COMMIT;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Procedure itt_po_pdf_history terminated due to ' ||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
         dbms_output.put_line(l_error_msg);
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ITT_PO_PDF_HISTORY';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;

   PROCEDURE itt_create_po_pdf (  p_itt_number          IN  VARCHAR2,
								  p_line_number         IN  VARCHAR2,
                                  p_user_name           IN  VARCHAR2,
                                  x_po_header_details   OUT ref_order_details,
                                  x_po_line_details     OUT ref_order_details,
                                  x_po_maint_details    OUT ref_order_details,
                                  x_dlr_instl_comp      OUT NUMBER,
                                  x_addnl_fee           OUT NUMBER,
                                  x_sub_total           OUT NUMBER,
                                  x_addnl_instr         OUT VARCHAR2
                                )
   IS
      l_notes_clob            CLOB;
      l_itt_admin             VARCHAR2(240);
      l_itt_admin_name        VARCHAR2(240);
      l_itt_admin_phone       VARCHAR2(240);
      l_itt_admin_fax         VARCHAR2(240);
      l_itt_admin_email       VARCHAR2(240);
      l_dealer_location       VARCHAR2(240);
      l_cust_contact_name     VARCHAR2(240 BYTE);
      l_cust_contact_phone    VARCHAR2(240 BYTE);
      l_cust_contact_email    VARCHAR2(240 BYTE);
      l_cust_contact_fax      VARCHAR2(240 BYTE);
      l_dlr_fax_area_code     VARCHAR2(240);
      l_dlr_fax               VARCHAR2(240);
      l_dlr_instl_comp        NUMBER := 0;
      l_addnl_fee             NUMBER := 0;
      l_po_header_sql         VARCHAR2(4000);
	  l_po_lines_sql         VARCHAR2(4000);
	  l_maint_sql         VARCHAR2(4000);
      l_sub_total             NUMBER := 0;
      l_dealer_cd             VARCHAR2(20);
      l_cnt                   INTEGER := 0;
	  V_SQL1_WHERE            VARCHAR2 (32000) := '';
   BEGIN
      l_itt_err_tbl.DELETE;
	  
	  IF p_line_number IS NOT NULL
	  THEN
	  V_SQL1_WHERE := V_SQL1_WHERE || ' AND cil.line_number IN (SELECT regexp_substr('''||p_line_number||''',''[^,]+'', 1, LEVEL) FROM dual
								CONNECT BY regexp_substr('''||p_line_number||''', ''[^,]+'', 1, LEVEL) IS NOT NULL) ';
	  ELSE
	  
	   V_SQL1_WHERE := V_SQL1_WHERE || ' AND cil.line_number = cil.line_number ';
	  
	  END IF;
	  
      BEGIN
         SELECT
         RTRIM(dbms_xmlgen.convert(XMLAGG(XMLELEMENT(E, notes || CHR(10) )).EXTRACT('//text()').getClobVal(), 1),', ')
         INTO  l_notes_clob
         FROM  canon_e580_notes_tbl cnt
         WHERE NVL(cnt.add_to_po_pdf, 'N') = 'Y'
         AND   cnt.itt_number = p_itt_number
         ORDER BY seq_number ASC;

         x_addnl_instr := TO_CHAR(l_notes_clob);
         dbms_output.put_line(x_addnl_instr);
      EXCEPTION
         WHEN OTHERS THEN
           l_error_msg := 'Error while aggregating notes, Error Message: ' || SQLCODE || ' - ' || SQLERRM;
           dbms_output.put_line(l_error_msg);
           l_cnt := l_itt_err_tbl.COUNT + 1;
           l_itt_err_tbl(l_cnt).error_message    := l_error_msg;
      END;

      BEGIN
         SELECT DISTINCT
                NVL(cih.itt_admin_owner, 'N')
               ,cih.dealer_address_line1||', '||cih.dealer_address_line2||', '||cih.dealer_address_line3||', '||cih.dealer_city||', '||cih.dealer_state||', '||cih.dealer_zip||', '||cih.dealer_country
               ,cih.dealer_whse_code dealer_cd
         INTO   l_itt_admin
               ,l_dealer_location
               ,l_dealer_cd
         FROM canon_e580_itt_headers_tbl cih
         WHERE cih.itt_number = p_itt_number;

         IF (NVL(l_itt_admin, 'N') <> 'N' )
         THEN
            SELECT UPPER((psn.last_nm||', '||psn.first_nm)), eml_addr, wrk_tel_num, fax_phn_num
            INTO   l_itt_admin_name, l_itt_admin_email, l_itt_admin_phone, l_itt_admin_fax
            FROM   auth_psn psn
            WHERE  actv_flg = 'Y'
            AND    glbl_cmpy_cd = g_glbl_cmpy_cd
            AND    psn.usr_nm = l_itt_admin;
         
            /**SELECT UPPER((psn.psn_last_nm||', '||psn.psn_first_nm)), psn.eml_addr, psn.admin_tel_num, psn.admin_fax_num
            INTO   l_itt_admin_name, l_itt_admin_email, l_itt_admin_phone, l_itt_admin_fax
            FROM   s21_psn psn
            WHERE  psn.glbl_cmpy_cd = g_glbl_cmpy_cd
            AND    NVL(psn.eff_from_dt, TO_CHAR(SYSDATE-1,'YYYYMMDD')) <= TO_CHAR(SYSDATE,'YYYYMMDD')
            AND    NVL(psn.eff_thru_dt, TO_CHAR(SYSDATE+1,'YYYYMMDD')) >= TO_CHAR(SYSDATE,'YYYYMMDD')
            AND    psn.psn_cd = l_itt_admin;**/
         END IF;
      EXCEPTION
         WHEN OTHERS THEN
           l_error_msg := 'Error while deriving ITT processor contact details from s21_psn, Error Message: ' || SQLCODE || ' - ' || SQLERRM;
           dbms_output.put_line(l_error_msg);
           l_cnt := l_itt_err_tbl.COUNT + 1;
           l_itt_err_tbl(l_cnt).error_message    := l_error_msg;
      END;

      --Derive customer contact dtls
      BEGIN
         SELECT  cih.customer_contact_name
               , cih.customer_contact_phone
               , cih.customer_contact_email
               , cih.customer_contact_fax
         INTO    l_cust_contact_name
               , l_cust_contact_phone
               , l_cust_contact_email
               , l_cust_contact_fax
         FROM    canon_e580_itt_headers_tbl cih
         WHERE   cih.itt_number = p_itt_number
         AND     cih.customer_contact_name IS NOT NULL
         AND     rownum = 1;
      EXCEPTION
         WHEN OTHERS THEN
           BEGIN
              SELECT  cil.customer_contact_name
                    , cil.customer_contact_phone
                    , cil.customer_contact_email
                    , cil.customer_contact_fax
              INTO    l_cust_contact_name
                    , l_cust_contact_phone
                    , l_cust_contact_email
                    , l_cust_contact_fax
              FROM    canon_e580_itt_lines_tbl cil
              WHERE   cil.itt_number = p_itt_number
			  AND     rownum = 1;
           EXCEPTION
              WHEN OTHERS THEN
                 l_error_msg := 'Error while getting Customer contact information, Error Message: ' || SQLCODE || ' - ' || SQLERRM;
                 dbms_output.put_line(l_error_msg);
                 l_cnt := l_itt_err_tbl.COUNT + 1;
                 l_itt_err_tbl(l_cnt).error_message := l_error_msg;
           END;
      END;

      --Derive Dealer Fax Number associated to ITT Number : Pending confirmation on mapping from S21.
      BEGIN
         SELECT cp.ctac_psn_tel_num
         INTO   l_dlr_fax
         FROM   vnd v, ds_ctac_psn_reln cpr, ctac_psn cp
         WHERE  v.vnd_cd = l_dealer_cd
         AND    v.loc_num = cpr.loc_num
         AND    cpr.ctac_psn_pk = cp.ctac_psn_pk
         --AND    v.loc_role_tp_cd = 'DLR'    Pending: Waiting for inputs on identifying Dealer.
         AND    v.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND  cpr.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND   cp.glbl_cmpy_cd = g_glbl_cmpy_cd
         AND    v.ezcancelflag = '0'
         AND  cpr.ezcancelflag = '0'
         AND   cp.ezcancelflag = '0';
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            l_dlr_fax           := ' ';
         WHEN OTHERS THEN
            l_error_msg := 'Error while deriving Dealer Fax Number for ITT number '||p_itt_number||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
            dbms_output.put_line(l_error_msg);
            l_cnt := l_itt_err_tbl.COUNT + 1;
            l_itt_err_tbl(l_cnt).error_message := l_error_msg;
      END;

	 IF p_line_number IS NOT NULL THEN
      BEGIN
	  
         SELECT (SUM(NVL(cil.install_credit, 0)) + SUM(NVL(cil.finder_fee, 0)))
         INTO  l_dlr_instl_comp
         FROM  canon_e580_itt_lines_tbl cil
         WHERE cil.itt_line_type = 'GOODS'
         AND   NVL(cil.cancelled_flag,'N') = 'N'
         AND   cil.itt_number = p_itt_number
		 AND   cil.line_number IN (SELECT regexp_substr(p_line_number,'[^,]+', 1, LEVEL) FROM dual
								CONNECT BY regexp_substr(p_line_number, '[^,]+', 1, LEVEL) IS NOT NULL);
								
         x_dlr_instl_comp := l_dlr_instl_comp;
      EXCEPTION
         WHEN OTHERS THEN
            l_error_msg := 'Error while calculating install credit/comp for ITT number '||p_itt_number||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
            dbms_output.put_line(l_error_msg);
            l_cnt := l_itt_err_tbl.COUNT + 1;
            l_itt_err_tbl(l_cnt).error_message := l_error_msg;
      END;

      BEGIN
         SELECT SUM(NVL(cil.item_purchase_price, 0))
         INTO  l_addnl_fee
         FROM  canon_e580_itt_lines_tbl cil
         WHERE cil.itt_line_type = 'EXPENSE'
         AND   NVL(cil.cancelled_flag,'N') = 'N'
         AND   cil.itt_number = p_itt_number
		 AND   cil.line_number IN (SELECT regexp_substr(p_line_number,'[^,]+', 1, LEVEL) FROM dual
								CONNECT BY regexp_substr(p_line_number, '[^,]+', 1, LEVEL) IS NOT NULL);

         x_addnl_fee := l_addnl_fee;
      EXCEPTION
         WHEN OTHERS THEN
            l_error_msg := 'Error while calculating additional fee for ITT number '||p_itt_number||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
            dbms_output.put_line(l_error_msg);
            l_cnt := l_itt_err_tbl.COUNT + 1;
            l_itt_err_tbl(l_cnt).error_message := l_error_msg;
      END;

	 ELSE
		BEGIN
	  
         SELECT (SUM(NVL(cil.install_credit, 0)) + SUM(NVL(cil.finder_fee, 0)))
         INTO  l_dlr_instl_comp
         FROM  canon_e580_itt_lines_tbl cil
         WHERE cil.itt_line_type = 'GOODS'
         AND   NVL(cil.cancelled_flag,'N') = 'N'
         AND   cil.itt_number = p_itt_number;
								
         x_dlr_instl_comp := l_dlr_instl_comp;
      EXCEPTION
         WHEN OTHERS THEN
            l_error_msg := 'Error while calculating install credit/comp for ITT number '||p_itt_number||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
            dbms_output.put_line(l_error_msg);
            l_cnt := l_itt_err_tbl.COUNT + 1;
            l_itt_err_tbl(l_cnt).error_message := l_error_msg;
      END;

      BEGIN
         SELECT SUM(NVL(cil.item_purchase_price, 0))
         INTO  l_addnl_fee
         FROM  canon_e580_itt_lines_tbl cil
         WHERE cil.itt_line_type = 'EXPENSE'
         AND   NVL(cil.cancelled_flag,'N') = 'N'
         AND   cil.itt_number = p_itt_number;

         x_addnl_fee := l_addnl_fee;
      EXCEPTION
         WHEN OTHERS THEN
            l_error_msg := 'Error while calculating additional fee for ITT number '||p_itt_number||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
            dbms_output.put_line(l_error_msg);
            l_cnt := l_itt_err_tbl.COUNT + 1;
            l_itt_err_tbl(l_cnt).error_message := l_error_msg;
      END;
	 
	 END IF;
	 
      l_po_header_sql :=
               ' SELECT DISTINCT
                         cih.itt_number
                       , cih.order_number
                       , cih.dealer
                       , cih.dealer_whse_code
                       ,Q''['||l_dealer_location||']'''
                       ||' location
                       , cih.dealer_contact
                       , cih.dealer_contact_number
                       , cih.dealer_email
                       , Q''['||NVL(l_dlr_fax_area_code, ' ')||']'' dlr_fax_area_code'
                       ||', Q''['||NVL(l_dlr_fax, ' ')||']'' dlr_fax'
                       ||' , cih.dealer_ship_to_cna_code
                       , cih.ship_to_customer
                       , sh.first_line_addr address1
                       , sh.scd_line_addr   address2
                       , sh.cty_addr        city
                       , sh.st_cd           state
                       , sh.post_cd         postal_code
                       , sh.prov_nm         county
                       , Q''['||NVL(l_cust_contact_name, ' ')||']'' customer_contact'
                       ||', Q''['||NVL(l_cust_contact_phone, ' ')||']'' customer_contact_phone'
                       ||', Q''['||NVL(l_cust_contact_email, ' ')||']'' customer_contact_email'
                       ||', Q''['||NVL(l_cust_contact_fax, ' ')||']'' customer_contact_fax'
                       ||', Q''['||NVL(l_itt_admin_name, ' ')||']'' itt_admin_name'
                       ||', Q''['||NVL(l_itt_admin_phone, ' ')||']'' itt_admin_phone'
                       ||', Q''['||NVL(l_itt_admin_fax, ' ')||']'' itt_admin_fax'
                       ||', Q''['||NVL(l_itt_admin_email, ' ')||']'' itt_admin_email'
                       ||' , cih.scheduled_delivery_date
                       , cih.mail_invoices_to
                       , cih.scheduled_delivery_date request_date
                       , cih.sales_branch
                 FROM   canon_e580_itt_headers_tbl cih
                       ,ship_to_cust sh
                 WHERE  cih.ship_to_customer_cd = sh.ship_to_cust_cd
                 AND    sh.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                 AND    sh.ezcancelflag = ''0''
                 AND    cih.itt_number = '''||p_itt_number||'''';

      dbms_output.put_line(l_po_header_sql);
      OPEN x_po_header_details FOR l_po_header_sql;

      
      l_po_lines_sql := 'SELECT substr(cil.item, 1, 8) item
							 , cil.item_description
							 , cil.ordered_quantity
							 , cil.itt_line_type
							 , CASE cil.itt_line_type
								WHEN  ''GOODS'' THEN
								  CASE NVL(cil.product_sourced_by, ''CSA'')
									WHEN ''CSA'' THEN
									   CASE cil.product_code
											WHEN ''99'' THEN TRIM(TO_CHAR(cil.item_purchase_price, ''999,999,999,999,999,990.99''))
											ELSE ''Shipping to Dealer'' END
									ELSE TRIM(TO_CHAR(cil.item_purchase_price, ''999,999,999,999,999,990.99''))
								  END
								ELSE TRIM(TO_CHAR(cil.item_purchase_price,''999,999,999,999,999,990.99''))
							   END   item_purchase_price
							 , CASE cil.itt_line_type
								WHEN  ''GOODS'' THEN
								   CASE NVL(cil.product_sourced_by, ''CSA'')
									  WHEN ''CSA'' THEN
										 CASE cil.product_code
											WHEN ''99'' THEN TRIM(TO_CHAR((cil.ordered_quantity * cil.item_purchase_price),''999,999,999,999,999,990.99''))
											ELSE ''Shipping to Dealer''
										 END
									  ELSE TRIM(TO_CHAR((cil.ordered_quantity * cil.item_purchase_price),''999,999,999,999,999,990.99''))
								   END
								ELSE
								   TRIM(TO_CHAR((cil.ordered_quantity * cil.item_purchase_price), ''999,999,999,999,999,990.99''))
								END   extended_cost
							 , cil.install_credit
							 , cil.finder_fee
							 , cil.equip_model   --Pending:Derive DISTINCT equip_model based on cil.industry_attribute need to identify in s21
							 , cil.merchandise
							 , CASE
								  WHEN cil.merchandise = ''11'' THEN ''Y''
								  WHEN cil.merchandise = ''10'' AND NVL((SELECT COUNT(cil1.merchandise)
																	   FROM canon_e580_itt_lines_tbl cil1
																	   WHERE cil1.itt_number = cil.itt_number
																	   AND cil1.merchandise = ''11''
																	   AND NVL(cil1.cancelled_flag,''N'') = ''N''
																	   AND cil1.equip_model = cil.equip_model   --Pending: If cil1.equip_model IS NULL, then derive equip_model of parent/top line cil1.industry_attribute
																	   GROUP BY cil1.equip_model, cil1.merchandise), 0) = 0 THEN ''Y''
								  ELSE ''N''
								END count_flag
							 , NVL(cil.cancelled_flag, ''N'') cancelled_flag
					  FROM   canon_e580_itt_lines_tbl cil
						   , canon_e580_itt_headers_tbl cih
					  WHERE  cih.itt_number = cil.itt_number
					  AND cih.order_number = cil.order_number
					  AND NVL(cil.cancelled_flag,''N'') = ''N''
					  AND cil.itt_number = '''||p_itt_number||''''
					  || V_SQL1_WHERE ||
					  'ORDER BY cil.line_number';
	  
	  dbms_output.put_line(l_po_lines_sql);
	  
	  OPEN x_po_line_details FOR l_po_lines_sql;

	  
      BEGIN
	  IF p_line_number IS NOT NULL THEN
         SELECT NVL(SUM((NVL(cil.ordered_quantity, 0) * NVL(cil.item_purchase_price, 0))), 0)
         INTO l_sub_total
         FROM canon_e580_itt_lines_tbl cil
            , canon_e580_itt_headers_tbl cih
         WHERE cih.itt_number = cil.itt_number
         AND cih.order_number = cil.order_number
         AND NVL(cil.cancelled_flag,'N') = 'N'
         AND cil.itt_number = p_itt_number
		 AND cil.line_number IN (SELECT regexp_substr(p_line_number,'[^,]+', 1, LEVEL) FROM dual
								CONNECT BY regexp_substr(p_line_number, '[^,]+', 1, LEVEL) IS NOT NULL)
         AND cil.itt_line_type = 'GOODS'
         AND NVL(cil.product_sourced_by, 'CSA') = 'DEALER';
		 
	  ELSE
	     SELECT NVL(SUM((NVL(cil.ordered_quantity, 0) * NVL(cil.item_purchase_price, 0))), 0)
         INTO l_sub_total
         FROM canon_e580_itt_lines_tbl cil
            , canon_e580_itt_headers_tbl cih
         WHERE cih.itt_number = cil.itt_number
         AND cih.order_number = cil.order_number
         AND NVL(cil.cancelled_flag,'N') = 'N'
         AND cil.itt_number = p_itt_number
         AND cil.itt_line_type = 'GOODS'
         AND NVL(cil.product_sourced_by, 'CSA') = 'DEALER';
	  END IF;

         x_sub_total := l_sub_total;
      EXCEPTION
         WHEN OTHERS THEN
            x_sub_total := 0;
            l_error_msg := 'Error while calculating sub total for ITT Number '||p_itt_number||CHR(10)|| SQLCODE || ' - ' || SQLERRM;
            l_cnt := l_itt_err_tbl.COUNT + 1;
            l_itt_err_tbl(l_cnt).error_message := l_error_msg;
      END;

    
     l_maint_sql := 'SELECT DISTINCT
						  csp.equip_model
						--, DECODE(substr(mt.intg_mdse_cd, 1,2), ''BW'', ''BLACK'', ''CL'', ''COLOR'', ''TOTAL'') meter_type
						, mt.mtr_lb_nm meter_type
						, csp.quantity
						, csp.base_price
						, csp.copy_inclusion
						, csp.overage_rate
						, csp.term
						, csp.base_bill_cycle
						, csp.overage_bill_cycle
						, csp.contract_type
						, csp.multiplier
						, NVL(csp.line_of_business, ''ESS'') lob
					FROM  canon_e580_service_pricing_tbl csp
						, (SELECT DISTINCT cil.itt_number, cil.merchandise, cil.cancelled_flag, cil.equip_model
							FROM canon_e580_itt_lines_tbl cil
							WHERE cil.itt_number = '''||p_itt_number||''''
							|| V_SQL1_WHERE ||
							'AND NVL(cil.cancelled_flag, ''N'') <> ''Y''
							AND cil.merchandise IN (''11'')
					   UNION
					   SELECT DISTINCT cil.itt_number, cil.merchandise, cil.cancelled_flag, cil.equip_model
							from canon_e580_itt_lines_tbl cil
							WHERE cil.itt_number = '''||p_itt_number||''''
							|| V_SQL1_WHERE ||
							'AND NVL(cil.cancelled_flag, ''N'') <> ''Y''
							AND cil.merchandise IN (''10'')
							AND NOT EXISTS (select *
											from canon_e580_itt_lines_tbl cil2
											WHERE cil2.itt_number = cil.itt_number
											AND NVL(cil.cancelled_flag, ''N'') <> ''Y''
											AND cil2.merchandise IN (''11'')
											)
							) cil1,
						mtr_lb mt	
					where 1=1
					AND cil1.itt_number = csp.itt_number
					AND cil1.equip_model = csp.equip_model
					AND NVL(cil1.cancelled_flag, ''N'') <> ''Y''						
					AND csp.itt_number = '''||p_itt_number||'''
					AND csp.meter_type = mt.mtr_lb_desc_txt
					AND mt.actv_flg = ''Y''';
					
					
		dbms_output.put_line(l_maint_sql);
		
		OPEN x_po_maint_details FOR l_maint_sql;
		
        itt_po_pdf_history  (   p_package_name        => 'CANON_E580_ITT_UTIL_PKG' -- VARCHAR2,
                              , p_procedure_name      => 'ITT_CREATE_PO_PDF' -- VARCHAR2
                              , p_itt_number          => p_itt_number -- VARCHAR2
                              , p_user_name           => p_user_name -- VARCHAR2
                              , p_value1              => NULL -- VARCHAR2
                              , p_value2              => NULL -- VARCHAR2
                              , p_value3              => NULL -- VARCHAR2
                              , p_value4              => NULL -- VARCHAR2
                              , p_value5              => NULL -- VARCHAR2
                              , p_value6              => NULL -- VARCHAR2
                              , p_status_message      => 'PO pdf created'
                            );

      IF (l_itt_err_tbl.COUNT > 0) THEN
         FOR i IN 1..l_itt_err_tbl.COUNT
         LOOP
            l_itt_err_tbl(i).package_name     := 'CANON_E580_ITT_UTIL_PKG';
            l_itt_err_tbl(i).procedure_name   := 'ITT_CREATE_PO_PDF';
            l_itt_err_tbl(i).itt_number       := p_itt_number;
            l_itt_err_tbl(i).created_by       := p_user_name;
            l_itt_err_tbl(i).creation_date    := SYSDATE;
            l_itt_err_tbl(i).last_updated_by  := p_user_name;
            l_itt_err_tbl(i).last_update_date := SYSDATE;
         END LOOP;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'Procedure itt_create_po_pdf terminated with Error Message :'||SQLERRM;
         dbms_output.put_line(l_error_msg);
         FOR i IN 1..(l_itt_err_tbl.COUNT+1)
         LOOP
            l_itt_err_tbl(i).package_name     := 'CANON_E580_ITT_UTIL_PKG';
            l_itt_err_tbl(i).procedure_name   := 'ITT_CREATE_PO_PDF';
            l_itt_err_tbl(i).itt_number       := p_itt_number;
            l_itt_err_tbl(i).created_by       := p_user_name;
            l_itt_err_tbl(i).creation_date    := SYSDATE;
            l_itt_err_tbl(i).last_updated_by  := p_user_name;
            l_itt_err_tbl(i).last_update_date := SYSDATE;
            IF (i = l_itt_err_tbl.COUNT+1) THEN
               l_itt_err_tbl(i).error_message := l_error_msg;
            END IF;
         END LOOP;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;
   
   PROCEDURE get_expense_item (p_expense_item OUT VARCHAR2)
   IS
   l_expense_item VARCHAR2(50);
   BEGIN
	  
	  SELECT cvt.val2
      INTO  l_expense_item
      FROM  canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
      WHERE ct.cd_name = 'CANON_E580_EXP_ITEM'
      AND   ct.cd_id = cvt.cd_id
      AND   cvt.val1 = 'EXPENSE'
      AND  (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1));
   
   p_expense_item := l_expense_item;
   
   EXCEPTION
	WHEN OTHERS THEN
	  l_expense_item := 'EXPENSE';
   END;
   
   FUNCTION get_code_tbl_value( p_cd_tbl IN VARCHAR2, p_code IN VARCHAR2) RETURN VARCHAR2
   IS
      l_vnd_code   VARCHAR2(50);
   BEGIN
      SELECT cvt.val2
      INTO  l_vnd_code
      FROM  canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
      WHERE ct.cd_name = p_cd_tbl
      AND   ct.cd_id = cvt.cd_id
      AND   cvt.val1 = NVL(p_code,cvt.val1)
      AND  (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1));
      
      RETURN (l_vnd_code);
   EXCEPTION
      WHEN OTHERS THEN
         RETURN (l_vnd_code);
   END;
   
   -----------------------------------------------------------------------------------
   --This function used to check if the ASL setup is defined for provided Item/Vendor
   --Returns Y if provided, else Returns N along with derived error message.
   -----------------------------------------------------------------------------------
   FUNCTION asl_setup_exists(  p_prnt_vnd_cd IN  VARCHAR2
                              ,p_vnd_cd      IN  VARCHAR2
                              ,p_mdse_cd     IN  VARCHAR2
                              ,p_error_msg   OUT VARCHAR2
                            )
   RETURN VARCHAR2
   IS
      l_vnd_uom_qty      NUMBER;
      l_vnd_min_ord_qty  NUMBER;
      l_vnd_incr_ord_qty NUMBER;
      l_base_ord_qty     NUMBER;
      l_asl_exists       VARCHAR2(1) := 'N';
   BEGIN
      SELECT 'Y', ad.vnd_min_ord_qty, ad.vnd_incr_ord_qty, ad.base_ord_qty, ad.vnd_uom_qty
      INTO   l_asl_exists, l_vnd_min_ord_qty, l_vnd_incr_ord_qty, l_base_ord_qty, l_vnd_uom_qty
      FROM   asl_hdr ah, asl_dtl ad
      WHERE  ah.asl_hdr_pk = ad.asl_hdr_pk
      AND NVL(ah.asl_start_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND NVL(ah.asl_end_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND NVL(ad.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND NVL(ad.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND ad.ezcancelflag = 0
      AND ah.prnt_vnd_cd = p_prnt_vnd_cd
      AND ad.vnd_cd  = p_vnd_cd
      AND ad.mdse_cd = p_mdse_cd;
      
      ---S21 API fails with division by zero if vnd_uom_qty = 0 and Also if l_vnd_min_ord_qty/l_vnd_incr_ord_qty/l_base_ord_qty = 0, creates PO with qty = 0 
      IF (  NVL(l_asl_exists,'N') = 'Y' AND  (l_vnd_uom_qty < 1 OR l_vnd_min_ord_qty < 1 OR l_vnd_incr_ord_qty < 1 OR l_base_ord_qty < 1))   
      THEN
         l_asl_exists := 'N';
         p_error_msg  := 'Incorrect ASL setup attributes for Vendor code:'||p_vnd_cd||', Item : '||p_mdse_cd;
      END IF;
      RETURN (l_asl_exists);
   EXCEPTION
      WHEN OTHERS THEN
         p_error_msg := 'ASL setup is not defined for Dealer code:'||p_vnd_cd||', Item : '||p_mdse_cd;
         RETURN (l_asl_exists);
   END;
   
   FUNCTION get_salesrep_attr(  p_salesrep_cd  IN VARCHAR2
                               ,p_attribute    IN VARCHAR2
                             ) RETURN VARCHAR2
   IS
      l_sql        VARCHAR2(3999);
      l_sales_attr VARCHAR2(50);
   BEGIN
      l_sql := '  SELECT case 
                             when b.attr = a.first_org_tier_cd  then a.first_org_nm
                             when b.attr = a.scd_org_tier_cd    then a.scd_org_nm
                             when b.attr = a.third_org_tier_cd  then a.third_org_nm
                             when b.attr = a.frth_org_tier_cd   then a.frth_org_nm
                             when b.attr = a.fifth_org_tier_cd  then a.fifth_org_nm
                             when b.attr = a.sixth_org_tier_cd  then a.sixth_org_nm
                             when b.attr = a.svnth_org_tier_cd  then a.svnth_org_nm
                             when b.attr = a.eighth_org_tier_cd then a.eighth_org_nm
                             when b.attr = a.ninth_org_tier_cd  then a.ninth_org_nm
                             when b.attr = a.tenth_org_tier_cd  then a.tenth_org_nm
                             when b.attr = a.elvth_org_tier_cd  then a.elvth_org_nm
                         end as sales_attr
                  FROM s21_org a, 
                      (SELECT org_tier_cd attr
                       FROM   stru_dfn sd, org_hrch_stru_dfn ohsd, biz_area_org ba
                       WHERE  1=1
                       AND    ohsd.org_stru_tp_cd = ''BOS''
                       AND    ohsd.stru_dfn_cd = sd.stru_dfn_cd
                       AND    ohsd.biz_area_org_cd = ba.biz_area_org_cd
                       AND    ba.sls_flg = ''Y''
                       AND    ohsd.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                       AND    sd.glbl_cmpy_cd =   '''||g_glbl_cmpy_cd||'''
                       AND    ba.glbl_cmpy_cd =   '''||g_glbl_cmpy_cd||'''
                       AND    ohsd.ezcancelflag = ''0''
                       AND    sd.ezcancelflag   = ''0''
                       AND    ba.ezcancelflag   = ''0''';
      IF (p_attribute = 'ZONE') 
      THEN
         l_sql := l_sql ||' AND    sd.zn_flg = ''Y''';
      ELSIF (p_attribute = 'BRANCH')
      THEN
         l_sql := l_sql ||' AND    sd.br_flg = ''Y''';
      END IF;      
         
         l_sql := l_sql||
                  ') b
                  WHERE 1=1
                  AND  a.toc_cd = '''||p_salesrep_cd||'''
                  AND  a.toc_cd is not null
                  AND  a.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                  AND  a.ezcancelflag = ''0''
               ';

       execute immediate l_sql INTO l_sales_attr;
       dbms_output.put_line('l_sql :'||l_sql);
       RETURN (l_sales_attr);
   EXCEPTION
      WHEN OTHERS THEN
         RETURN (l_sales_attr);
   END;
   
   FUNCTION get_salesrep_zn(  p_salesrep_cd  IN VARCHAR2) RETURN VARCHAR2
   IS
      l_sql        VARCHAR2(3999);
      l_sales_attr VARCHAR2(50);
   BEGIN
      l_sql := '  SELECT DECODE(dou.csr_rg_tp_cd,''E'',''EAST'',''W'',''WEST'',''CE'',''CENTRAL'')
                  FROM  s21_org so,
                        ds_org_unit dou
                  WHERE 1 = 1
                  AND  so.toc_cd = '''||p_salesrep_cd||'''
                  AND  so.toc_cd IS NOT NULL
                  AND  so.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                  AND dou.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                  AND  so.ezcancelflag = ''0''
                  AND dou.ezcancelflag = ''0''                  
                  AND dou.org_cd = 
                     ( SELECT 
                          case 
                             when ohsd.org_tier_cd = so.first_org_tier_cd  then so.first_org_cd
                             when ohsd.org_tier_cd = so.scd_org_tier_cd    then so.scd_org_cd
                             when ohsd.org_tier_cd = so.third_org_tier_cd  then so.third_org_cd
                             when ohsd.org_tier_cd = so.frth_org_tier_cd   then so.frth_org_cd
                             when ohsd.org_tier_cd = so.fifth_org_tier_cd  then so.fifth_org_cd
                             when ohsd.org_tier_cd = so.sixth_org_tier_cd  then so.sixth_org_cd
                             when ohsd.org_tier_cd = so.svnth_org_tier_cd  then so.svnth_org_cd
                             when ohsd.org_tier_cd = so.eighth_org_tier_cd then so.eighth_org_cd
                             when ohsd.org_tier_cd = so.ninth_org_tier_cd  then so.ninth_org_cd
                             when ohsd.org_tier_cd = so.tenth_org_tier_cd  then so.tenth_org_cd
                             when ohsd.org_tier_cd = so.elvth_org_tier_cd  then so.elvth_org_cd
                          end org_cd
                       FROM   stru_dfn sd, org_hrch_stru_dfn ohsd, biz_area_org ba
                       WHERE  1=1
                       AND    ohsd.org_stru_tp_cd = ''BOS''
                       AND    ohsd.stru_dfn_cd = sd.stru_dfn_cd
                       AND    ohsd.biz_area_org_cd = ba.biz_area_org_cd
                       AND    sd.rg_flg = ''Y''
                       AND    ba.sls_flg = ''Y''
                       AND    ohsd.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                       AND    sd.glbl_cmpy_cd =   '''||g_glbl_cmpy_cd||'''
                       AND    ba.glbl_cmpy_cd =   '''||g_glbl_cmpy_cd||'''
                       AND    ohsd.ezcancelflag = ''0''
                       AND    sd.ezcancelflag   = ''0''
                       AND    ba.ezcancelflag   = ''0''
                     )
                ';

       execute immediate l_sql INTO l_sales_attr;
       dbms_output.put_line('l_sql :'||l_sql);
       RETURN (l_sales_attr);
   EXCEPTION
      WHEN OTHERS THEN
         RETURN (l_sales_attr);
   END;
   
  -------------------------------------------------------------------------------------------------------------------------------
   --This procedure invoked from ITT WB detail/Maintenance screen to populate eligible Service Meter packages
   -------------------------------------------------------------------------------------------------------------------------------
   PROCEDURE itt_svc_mtr_pkg_lov(p_model_name  IN VARCHAR2,
								 x_svc_mtr_pkg OUT ref_order_details)
   AS
   BEGIN
      OPEN x_svc_mtr_pkg
      FOR
      SELECT pmp.prc_mtr_pkg_nm svc_meter_pkg
		FROM prc_mtr_pkg pmp,
			 prc_mtr_pkg_mdl pmpm
	   WHERE pmpm.prc_mtr_pkg_pk = pmp.prc_mtr_pkg_pk
		 AND pmpm.mdl_nm         = p_model_name
		 AND SYSDATE BETWEEN NVL(CAST (TO_TIMESTAMP (pmpm.eff_from_dt, 'RRRRMMDD') AS DATE), SYSDATE-1) 
		      AND NVL(CAST (TO_TIMESTAMP (pmpm.eff_thru_dt, 'RRRRMMDD') AS DATE),SYSDATE+1);
		 
   EXCEPTION
      WHEN OTHERS
      THEN
        l_error_msg := 'Procedure itt_svc_mtr_pkg_lov terminated due to ' || SQLCODE || ' - ' || SQLERRM;
        DBMS_OUTPUT.PUT_LINE(SQLCODE || ' - ' || SQLERRM);
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_SVC_MTR_PKG_LOV';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
		
   END itt_svc_mtr_pkg_lov;
   
 
 PROCEDURE itt_meter_type_dtls (p_itt_number    IN  VARCHAR2,
								p_order_number  IN  VARCHAR2,
								p_model_name    IN  VARCHAR2,
								p_svc_pkg_name  IN  VARCHAR2)
								--x_mtr_type_dtl  OUT ref_order_details)
 AS
 l_line_of_business VARCHAR2(100);
 
 BEGIN
   
	BEGIN
	
		SELECT DISTINCT line_of_business
		INTO l_line_of_business
		FROM canon_e580_service_pricing_tbl
		WHERE itt_number = p_itt_number;
	
	EXCEPTION
	WHEN OTHERS THEN
		l_line_of_business :=  NULL;
	END;
	
    BEGIN
		DELETE FROM canon_e580_service_pricing_tbl
		WHERE itt_number = p_itt_number
		  AND equip_model = p_model_name;
		
	EXCEPTION
	WHEN OTHERS THEN
	   NULL;
	END;
	
	COMMIT;
	
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
	SELECT p_order_number,
		   p_itt_number,
		   p_model_name,
		   (SELECT COUNT (l.mdse_cd) 
		    FROM cpo_dtl l,
				 ds_cpo_config_v dcc,
				 mdse dmi
			WHERE dcc.cpo_ord_num = p_order_number
			AND dcc.t_mdl_nm = p_model_name
			AND l.ds_cpo_config_pk = dcc.ds_cpo_config_pk
            AND l.svc_config_mstr_pk = dcc.svc_config_mstr_pk
			AND l.mdse_cd = dmi.mdse_cd
			AND dmi.coa_mdse_tp_cd = '10'
			AND NVL (l.cpo_dtl_canc_flg, 'N') = 'N' ) quantity,
	      pmp.prc_mtr_pkg_nm meter_type_pkg,
		  (SELECT prc_svc_contr_tp_nm
		  FROM s21_csa_apps.prc_svc_contr_tp psc
		  WHERE psc.prc_svc_contr_tp_cd = pls.prc_svc_contr_tp_cd
		  ) contract_type,
		  (SELECT prc_svc_pln_tp_nm
		  FROM s21_csa_apps.prc_svc_pln_tp psp
		  WHERE psp.prc_svc_pln_tp_cd = pls.prc_svc_pln_tp_cd
		  ) plan_type,
		  NULL term,
		  pls.base_amt base_amount,
		  NULL base_bill_cycle,
		  NULL overage_bill_cycle,
		  mt.mtr_lb_desc_txt meter_type,
		  --mt1.mtr_lb_desc_txt hard_counter,
		  (select mt1.mtr_lb_desc_txt
              from s21_csa_apps.mtr_lb mt1
              where mt1.mtr_lb_cd = pmpm.mtr_lb_cd) hard_counter,
		  pls.cpc_amt_rate overage_rate,
		  pls.max_copy_vol_cnt copy_inclusion,
		  pmpm.mtr_mult_rate multiplier,
		  l_line_of_business,
		  l_username,
		  SYSDATE,
		  l_username,
		  SYSDATE
		FROM prc_mtr_pkg pmp,
			 prc_list_svc pls,
			 prc_catg plc,
			 prc_mtr_pkg_bllg_mtr pmpb,
			 prc_mtr_pkg_phys_mtr pmpm,
			 mtr_lb mt
			 --mtr_lb mt1
	  WHERE  1 = 1
   		AND pmp.prc_mtr_pkg_nm     = p_svc_pkg_name
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
		AND pls.prc_mtr_pkg_pk(+) = pmp.prc_mtr_pkg_pk
		AND pls.mdl_nm (+)        = p_model_name
		AND pls.del_flg (+)       = 'N'
		AND pmpm.prc_mtr_pkg_bllg_mtr_pk(+) = pmpb.prc_mtr_pkg_bllg_mtr_pk
		AND plc.prc_catg_cd       = pls.prc_catg_cd (+)
		AND plc.prc_catg_nm       =  (SELECT cvt.val2
									  FROM s21_csa_apps.canon_s21_cd_tbl ct,
										s21_csa_apps.canon_s21_cd_val_tbl cvt
									  WHERE ct.cd_name = 'CANON_E580_PRICELIST'
									  AND ct.cd_id     = cvt.cd_id
									  AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
									  AND cvt.val1 = 'MAINTENANCE'
									  );
									  
    EXCEPTION
	WHEN OTHERS THEN
	
		l_error_msg := 'Insert into canon_e580_service_pricing_tbl failed due to ' || SQLCODE || ' - ' || SQLERRM;
        l_itt_err_tbl.DELETE;
        l_itt_err_tbl(1).package_name     := 'CANON_E580_ITT_UTIL_PKG';
        l_itt_err_tbl(1).procedure_name   := 'ITT_METER_TYPE_DTLS';
        l_itt_err_tbl(1).error_message    := l_error_msg;
        l_itt_err_tbl(1).created_by       := l_username;
        l_itt_err_tbl(1).creation_date    := SYSDATE;
        l_itt_err_tbl(1).last_updated_by  := l_username;
        l_itt_err_tbl(1).last_update_date := SYSDATE;
        canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
	
	END;
   
   COMMIT;
   
  /* OPEN x_mtr_type_dtl
   FOR
   SELECT p_order_number,
		   p_itt_number,
		   p_model_name,
	      pmp.prc_mtr_pkg_nm meter_type_pkg,
		  (SELECT prc_svc_contr_tp_nm
		  FROM s21_csa_apps.prc_svc_contr_tp psc
		  WHERE psc.prc_svc_contr_tp_cd = pls.prc_svc_contr_tp_cd
		  ) contract_type,
		  (SELECT prc_svc_pln_tp_nm
		  FROM s21_csa_apps.prc_svc_pln_tp psp
		  WHERE psp.prc_svc_pln_tp_cd = pls.prc_svc_pln_tp_cd
		  ) plan_type,
		  NULL term,
		  pls.base_amt base_amount,
		  NULL base_bill_cycle,
		  NULL overage_bill_cycle,
		  mt.mtr_lb_desc_txt meter_type,
		  (select mt1.mtr_lb_desc_txt
              from s21_csa_apps.mtr_lb mt1
              where mt1.mtr_lb_cd = pmpm.mtr_lb_cd) hard_counter,
		  pls.cpc_amt_rate overage_rate,
		  pls.max_copy_vol_cnt copy_inclusion,
		  pmpm.mtr_mult_rate multiplier
		FROM prc_mtr_pkg pmp,
			 prc_list_svc pls,
			 prc_catg plc,
			 prc_mtr_pkg_bllg_mtr pmpb,
			 prc_mtr_pkg_phys_mtr pmpm,
			 mtr_lb mt
	  WHERE  1 = 1
   		AND pmp.prc_mtr_pkg_nm     = p_svc_pkg_name
		AND pmp.prc_mtr_pkg_pk     = pmpb.prc_mtr_pkg_pk
		AND pmpb.mtr_lb_cd         = mt.mtr_lb_cd
		AND mt.actv_flg            = 'Y'
		AND pls.bllg_mtr_lb_cd(+)     = pmpb.mtr_lb_cd
		AND (SYSDATE BETWEEN NVL(to_date(pls.eff_from_dt(+),'YYYYMMDD'),SYSDATE-1) AND NVL(to_date(pls.eff_thru_dt(+),'YYYYMMDD'),SYSDATE+1))
		AND (SYSDATE BETWEEN NVL(to_date(mt.eff_from_dt,'YYYYMMDD'),SYSDATE    -1) AND NVL(to_date(mt.eff_thru_dt,'YYYYMMDD'),SYSDATE+1))
		AND pls.prc_mtr_pkg_pk(+) = pmp.prc_mtr_pkg_pk
		AND pls.mdl_nm (+)        = p_model_name
		AND pmpm.prc_mtr_pkg_bllg_mtr_pk(+) = pmpb.prc_mtr_pkg_bllg_mtr_pk
		AND plc.prc_catg_cd       = pls.prc_catg_cd (+)
		AND plc.prc_catg_nm       =  (SELECT cvt.val2
									  FROM s21_csa_apps.canon_s21_cd_tbl ct,
										s21_csa_apps.canon_s21_cd_val_tbl cvt
									  WHERE ct.cd_name = 'CANON_E580_PRICELIST'
									  AND ct.cd_id     = cvt.cd_id
									  AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
									  AND cvt.val1 = 'MAINTENANCE'
									  );*/

		
 END itt_meter_type_dtls; 

END CANON_E580_ITT_UTIL_PKG;
/
