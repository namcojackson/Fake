/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.NYEL0010.constant;

public interface NYEL0010Constant {

    // Common button
    String[] CMN_BTN1 = {"btn1", "", "" };
    String[] CMN_BTN2 = {"btn2", "", "" };
    String[] CMN_BTN3 = {"btn3", "", "" };
    String[] CMN_BTN4 = {"btn4", "", "" };
    String[] CMN_BTN5 = {"btn5", "", "" };
    String[] CMN_BTN6 = {"btn6", "", "" };
    String[] CMN_BTN7 = {"btn7", "", "" };
    String[] CMN_BTN8 = {"btn8", "", "" };
    String[] CMN_BTN9 = {"btn9", "", "" };
    String[] CMN_BTN10 = {"btn10", "Quit", "Quit" };

    final String SCREEN_NAME = "NYEL0010Scrn00";
    
    final String S21_PROFILE = "S21Menu";
    // Order Process
    final String A_PROCESS_GROUP_ID    = "s21.menu.order_process.group";
    final String A_PROCESS_PROCESS_ID1 = "s21.menu.order_process.allocation_planning";
    final String A_PROCESS_PROCESS_ID2 = "s21.menu.order_process.edi";
    final String A_PROCESS_PROCESS_ID3 = "s21.menu.order_process.order_capture";
    final String A_PROCESS_PROCESS_ID4 = "s21.menu.order_process.loan_trial";
    // SCE
    final String B_PROCESS_GROUP_ID    = "s21.menu.sce.group";
    final String B_PROCESS_PROCESS_ID1 = "s21.menu.sce.procurement";
    final String B_PROCESS_PROCESS_ID2 = "s21.menu.sce.receiving_mgmt";
    final String B_PROCESS_PROCESS_ID3 = "s21.menu.sce.shipping_mgmt";
    final String B_PROCESS_PROCESS_ID4 = "s21.menu.sce.inventory_mgmt";
    final String B_PROCESS_PROCESS_ID5 = "s21.menu.sce.serial_mgmt";
    // Invoicing
    final String C_PROCESS_GROUP_ID    = "s21.menu.invoicing.group";
    final String C_PROCESS_PROCESS_ID1 = "s21.menu.invoicing.invoice";
    final String C_PROCESS_PROCESS_ID2 = "s21.menu.invoicing.credit_debit";
    // AR
    final String D_PROCESS_GROUP_ID    = "s21.menu.ar.group";
    final String D_PROCESS_PROCESS_ID1 = "s21.menu.ar.cash_application";
    final String D_PROCESS_PROCESS_ID2 = "s21.menu.ar.collection";
    // Service parts
    final String E_PROCESS_GROUP_ID    = "s21.menu.service_parts.group";
    final String E_PROCESS_PROCESS_ID1 = "s21.menu.service_parts.service_parts";
    final String E_PROCESS_PROCESS_ID2 = "s21.menu.service_parts.reporting";
    // Marketing
    final String F_PROCESS_GROUP_ID    = "s21.menu.marketing.group";
    final String F_PROCESS_PROCESS_ID1 = "s21.menu.marketing.promotion";
    // Export
    final String G_PROCESS_GROUP_ID    = "s21.menu.export.group";
    final String G_PROCESS_PROCESS_ID1 = "s21.menu.export.order_capture";
    final String G_PROCESS_PROCESS_ID2 = "s21.menu.export.order_fulfill";
    final String G_PROCESS_PROCESS_ID3 = "s21.menu.export.export_doc";
    // Post-Sales
    final String H_PROCESS_GROUP_ID1   = "s21.menu.post_sales.group1";
    final String H_PROCESS_PROCESS_ID1 = "s21.menu.post_sales.service_repair";
    final String H_PROCESS_GROUP_ID0   = "s21.menu.post_sales.group0";
    final String H_PROCESS_PROCESS_ID3 = "s21.menu.post_sales.dispatch";
    final String H_PROCESS_PROCESS_ID4 = "s21.menu.post_sales.machine_asset";
    final String H_PROCESS_PROCESS_ID5 = "s21.menu.post_sales.contract_billing";
    final String H_PROCESS_GROUP_ID2   = "s21.menu.post_sales.group2";
    final String H_PROCESS_PROCESS_ID2 = "s21.menu.post_sales.return";
    // CPPD
    final String I_PROCESS_GROUP_ID    = "s21.menu.cppd.group";
    final String I_PROCESS_PROCESS_ID1 = "s21.menu.cppd.cppd_operation";
    // Financial Link
    final String J_PROCESS_GROUP_ID    = "s21.menu.financial_link.group";
    final String J_PROCESS_PROCESS_ID1 = "s21.menu.financial_link.aje";
    final String J_PROCESS_PROCESS_ID2 = "s21.menu.financial_link.cost";
    // My Process
    final String K_PROCESS_GROUP_ID    = "s21.menu.my_process.group";
    final String K_PROCESS_PROCESS_ID1 = "s21.menu.my_process.goto_my_process";
    final String K_PROCESS_PROCESS_ID2 = "s21.menu.my_process.setup_my_process";
    // Online Inquiry
    final String L_PROCESS_GROUP_ID    = "s21.menu.online_inquiry.group";
    final String L_PROCESS_PROCESS_ID1 = "s21.menu.online_inquiry.sales_order_inventory";
    // DWH
    final String M_PROCESS_GROUP_ID    = "s21.menu.dwh.group";
    final String M_PROCESS_PROCESS_ID1 = "s21.menu.dwh.data_warehouse";
    final String M_PROCESS_PROCESS_ID2 = "s21.menu.dwh.data_reporting";
    // IDS
    final String P_PROCESS_GROUP_ID    = "s21.menu.ids.group";
    final String P_PROCESS_PROCESS_ID1 = "s21.menu.ids.ids_link";    
    // Master
    final String N_PROCESS_GROUP_ID    = "s21.menu.master.group";
    final String N_PROCESS_PROCESS_ID1 = "s21.menu.master.merchandise";
    final String N_PROCESS_PROCESS_ID2 = "s21.menu.master.organization";
    final String N_PROCESS_PROCESS_ID3 = "s21.menu.master.partner";
    final String N_PROCESS_PROCESS_ID4 = "s21.menu.master.salescondition";
    final String N_PROCESS_PROCESS_ID5 = "s21.menu.master.code_maintenance";
    // Admin Menu
    final String O_PROCESS_GROUP_ID    = "s21.menu.admin_menu.group";
    final String O_PROCESS_PROCESS_ID1 = "s21.menu.admin_menu.menu";
    // CUSA Retail (ROSS)
    final String Q_PROCESS_GROUP_ID    = "s21.menu.cusaretail.group";
    final String Q_PROCESS_PROCESS_ID1 = "s21.menu.cusaretail.master";
    final String Q_PROCESS_PROCESS_ID2 = "s21.menu.cusaretail.order_capture";
    final String Q_PROCESS_PROCESS_ID3 = "s21.menu.cusaretail.install_billing";
    final String Q_PROCESS_PROCESS_ID4 = "s21.menu.cusaretail.return_asset";
    // Workflow
    final String Z_PROCESS_GROUP_ID    = "s21.menu.workflow.group";
    final String Z_PROCESS_PROCESS_ID1 = "s21.menu.workflow.workflow";
    // ASCC (CustomApp)
    final String R_PROCESS_GROUP_ID    = "s21.menu.ascc.group";
    final String R_PROCESS_PROCESS_ID1 = "s21.menu.ascc.ascc";
}
