/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010.constant;

/**
 *<pre>
 * NMAL7010Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/09   SRA             E.Inada         Update          QC#2174
 * 2016/05/27   Fujitsu         W.Honda         Update          QC#8505
 * 2017/02/23   Fujitsu         R.Nakamura      Update          QC#16011
 * 2018/05/08   Fujitsu         T.Noguchi       Update          QC#20269
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/08/22   Fujitsu         T.Noguchi       Update          QC#26664
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 *</pre>
 */
public class NMAL7010Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7010";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7010Scrn00";

    // Add Start 2017/02/23 QC#16011
    /** Update Authority */
    public static final String UPDATE_AUTHORITY = "NMAL7010T020";
    // Add End 2017/02/23 QC#16011

    /** String COMMA */
    public static final String COMMA = ",";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    /** PRC_CUST_AUDC_01 */
    public static final String BTN_PRC_CUST_AUDC_01 = "OpenWin_CustAudcVal_01";

    /** PRC_CUST_AUDC_02 */
    public static final String BTN_PRC_CUST_AUDC_02 = "OpenWin_CustAudcVal_02";

    /** PRC_CUST_AUDC_03 */
    public static final String BTN_PRC_CUST_AUDC_03 = "OpenWin_CustAudcVal_03";

    /** PRC_TRX_AUDC_01 */
    public static final String BTN_PRC_TRX_AUDC_01 = "OpenWin_TrxAudcVal_01";

    /** PRC_TRX_AUDC_02 */
    public static final String BTN_PRC_TRX_AUDC_02 = "OpenWin_TrxAudcVal_02";

    /** PRC_QLFY_TP */
    public static final String BTN_PRC_QLFY_VAL = "OpenWin_QualifierValue";

    /** BTN_PRC_MODEL */
    public static final String BTN_PRC_MODEL = "OpenWin_Model";

    /** PRC_MTR_PKG */
    public static final String BTN_PRC_MTR_PKG = "OpenWin_MtrPkg";

    /** MTR_LB */
    public static final String BTN_MTR_LB = "OpenWin_MtrLb";

    // 2018/11/17 QC#29147 Add Start
    /** MTR_LB */
    public static final String BTN_PRC_LIST_BAND = "OpenWin_PrcListBand";
    // 2018/11/17 QC#29147 Add Start

    /** MTR_LB */
    public static final String BTN_PRC_LIST_MDSE = "OpenWin_PrcListMdse";

    // Add Start 2017/02/23 QC#16011
    /** BTN_ADD_SUB_PRC */
    public static final String BTN_ADD_SUB_PRC = "Add_SubPrc";

    /** BTN_DEL_SUB_PRC */
    public static final String BTN_DEL_SUB_PRC = "Del_SubPrc";

    /** BTN_UPLOAD_CUST_AUDC */
    public static final String BTN_UPLOAD_CUST_AUDC = "MoveWin_UploadCustAudc";

    /** BTN_ADD_CUST_AUDC */
    public static final String BTN_ADD_CUST_AUDC = "Add_CustAudc";

    /** BTN_DEL_CUST_AUDC */
    public static final String BTN_DEL_CUST_AUDC = "Del_CustAudc";

    /** BTN_ADD_TRX_AUDC */
    public static final String BTN_ADD_TRX_AUDC = "Add_TrxAudc";

    /** BTN_DEL_TRX_AUDC */
    public static final String BTN_DEL_TRX_AUDC = "Del_TrxAudc";

    /** BTN_SUB_PRC_LIST */
    public static final String BTN_SUB_PRC_LIST = "OpenWin_SubPrcList";

    /** BTN_LEASE_RATE_PRC_LIST */
    public static final String BTN_LEASE_RATE_PRC_LIST = "OpenWin_LeaseRatePrcList";

    /** BTN_MOVE_MASS_UPLOAD */
    public static final String BTN_MOVE_MASS_UPLOAD = "MoveWin_UploadPrcList";

    /** BTN_MASS_UPLOAD */
    public static final String BTN_MASS_UPLOAD = "MassUpd_PrcList";

    /** BTN_ADD_PRC_LIST */
    public static final String BTN_ADD_PRC_LIST = "Add_PrcList";

    /** BTN_DEL_PRC_LIST */
    public static final String BTN_DEL_PRC_LIST = "Del_PrcList";

    /** BTN_PRC_FORMULA */
    public static final String BTN_PRC_FORMULA = "OpenWin_PrcFormula";

    /** BTN_MDSE */
    public static final String BTN_MDSE = "OpenWin_Mdse";

    /** BTN_SUPPLY_PLAN */
    public static final String BTN_SUPPLY_PLAN = "OpenWin_SupplyPlan";
    
    
    public static final String BTN_DS_ACCT_CUST = "OpenWin_DsAcctCust";
    // Add End 2017/02/23 QC#16011

    // 2018/05/08 QC#20269 Add Start
    /** DwldAsTemplate_PrcList */
    public static final String BTN_DOWNLOAD_AS_TEMPLATE = "DwldAsTemplate_PrcList";
    // 2018/05/08 QC#20269 Add End

    /** Common */
    public static final String BTN_CMN = "_Cmn";

    /** General */
    public static final String BTN_GEN = "_Gen";

    /** Account# */
    public static final String BTN_ACC = "_Acc";

    /** Item */
    public static final String BTN_ITEM = "Item";

    /** General */
    public static final String BTN_PROD = "Prod";

    /** Common(MDSE_TP) */
    public static final String BTN_MDSE_TP = "MdseTp";

    /** PRC_LIST_STD_COST*/
    public static final String STD_C_PRC = "STD_C_PRC";

    /** TAB : CustAudc (X) */
    public static final String TAB_CUST_AUDC = "CustAudc";

    /** TAB : TrxAudc (Y) */
    public static final String TAB_TRX_AUDC = "TrxAudc";

    /** TAB : PrcListValEquip (A) */
    public static final String TAB_PRC_LIST_VAL_EQUIP = "PrcListValEquip";

    /** TAB : PrcListValService (B) */
    public static final String TAB_PRC_LIST_VAL_SVC = "PrcListValService";

    /** TAB : PrcListValServiceTiers (C) */
    public static final String TAB_PRC_LIST_VAL_SVC_TIER = "PrcListValServiceTiers";

    /** TAB : PrcListValServiceSpecialCharges (D) */
    public static final String TAB_PRC_LIST_VAL_SVC_SPEC_CHRG = "PrcListValServiceSpecialCharges";

    /** TAB : PrcListValSupplyProgram (E) */
    public static final String TAB_PRC_LIST_VAL_SPLY_PGM = "PrcListValSupplyProgram";

    /** TAB : PrcListValLeaseRate (F) */
    public static final String TAB_PRC_LIST_VAL_LEASE_RATE = "PrcListValLeaseRate";

    /** TAB : PrcListValLeaseReturnFees (G) */
    public static final String TAB_PRC_LIST_VAL_LEASE_RTRN_FEE = "PrcListValLeaseReturnFees";

    /** TAB : PrcListValTradeIn (H) */
    public static final String TAB_PRC_LIST_VAL_TI = "PrcListValTradeIn";

    /** TAB : PrcListValQtyDiscount (I) */
    public static final String TAB_PRC_LIST_VAL_QTY_DISC = "PrcListValQtyDiscount";

    /** Check Box PA */
    public static final String CHK_PA = "xxChkBox_PA";

    /** Check Box PB */
    public static final String CHK_PB = "xxChkBox_PB";

    /** Check Box PC */
    public static final String CHK_PC = "xxChkBox_PC";

    /** Check Box PD */
    public static final String CHK_PD = "xxChkBox_PD";

    /** Check Box PE */
    public static final String CHK_PE = "xxChkBox_PE";

    /** Check Box PF */
    public static final String CHK_PF = "xxChkBox_PF";

    /** Check Box PG */
    public static final String CHK_PG = "xxChkBox_PG";

    /** Check Box PH */
    public static final String CHK_PH = "xxChkBox_PH";

    /** Check Box PI */
    public static final String CHK_PI = "xxChkBox_PI";

    /** Check Box PJ */
    public static final String CHK_PJ = "xxChkBox_PJ";

    /** Check Box CX */
    public static final String CHK_CX = "xxChkBox_CX";

    /** Check Box TY */
    public static final String CHK_TY = "xxChkBox_TY";

    // QC#8505 2016/05/26 Add start
    /** Upload CSV Id. Price List Equipment Work */
    public static final String UPLD_CSV_ID_EQUIP_WRK = "NMA7010001";

    /** Upload CSV Id. Price List Service Work */
    public static final String UPLD_CSV_ID_SVC_WRK = "NMA7010002";

    /** Upload CSV Id. Price List Service Tier Work */
    public static final String UPLD_CSV_ID_SVC_TIER_WRK = "NMA7010003";

    /** Upload CSV Id. Price List Additional Charge Work */
    public static final String UPLD_CSV_ID_ADDL_CHRG_WRK = "NMA7010004";

    /** Upload CSV Id. Price List Supply Program Work */
    public static final String UPLD_CSV_ID_SPLY_PGM_WRK = "NMA7010005";

    /** Upload CSV Id. Price List Lease Rate Work */
    public static final String UPLD_CSV_ID_LEASE_RATE_WRK = "NMA7010006";

    /** Upload CSV Id. Price List Lease Return Work */
    public static final String UPLD_CSV_ID_LEASE_RTRN_WRK = "NMA7010007";

    /** Upload CSV Id. Price List Trade In Work */
    public static final String UPLD_CSV_ID_TI_VAL_WRK = "NMA7010008";

    /** Upload CSV Id. Price Customer Audience Work */
    public static final String UPLD_CSV_ID_CUST_AUDC_WRK = "NMA7020001";
    // QC#8505 2016/05/26 Add end

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** The file with other than the file extension "csv" "txt" can't be uploaded. */
    public static final String ZYEM0096E = "ZYEM0096E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Detail requires at least one line.  Please enter. */
    public static final String NMAM8190E = "NMAM8190E";

    /** Please enter Price or Formula. */
    public static final String NMAM8217E = "NMAM8217E";

    /** Cannot set Formula not when Qualify Type is Item Code. */
    public static final String NMAM8225E = "NMAM8225E";

    /** Changing Price List Type will remove the Detail Information. */
    public static final String NMAM8233I = "NMAM8233I";

    /** Remove the selected records. is it OK? */
    public static final String NMAM8234I = "NMAM8234I";

    /** Please submit before entering new Qty Discount. */
    public static final String NMAM8355E = "NMAM8355E";

    /** Please search for Price List first. */
    public static final String NMAM8367E = "NMAM8367E";

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_COLUMN_NUM = 4;

    public interface NMAL7010ConstantIf {

        /*-------------------------------
         * Table Name
         *------------------------------*/
        /** Table Name */
        String TBL_A = "A";

        /** Table Name */
        String TBL_B = "B";

        /** Table Name */
        String TBL_C = "C";

        /** Table Name */
        String TBL_D = "D";

        /** Table Name */
        String TBL_E = "E";

        /** Table Name */
        String TBL_F = "F";

        /** Table Name */
        String TBL_G = "G";

        /** Table Name */
        String TBL_H = "H";

        /** Table Name */
        String TBL_W = "W";

        /** Table Name */
        String TBL_X_HC1 = "HC1";

        /** Table Name */
        String TBL_X_HC2 = "HC2";

        /** Table Name */
        String TBL_Y_HT1 = "HT1";

        /** Table Name */
        String TBL_Y_HT2 = "HT2";

        /*-------------------------------
         * Qty Discount Popup Parameters
         *------------------------------*/
        /* ------ Parameter Number ------ */
        int INPUT_AFT_DECL_PNT_DIGIT_NUM = 0;

        int INPUT_PRC_QLFY_TP_CD_LIST = 1;

        int INPUT_PRC_QLFY_TP_DESC_TXT_LIST = 2;

        int INPUT_PRC_QLFY_TP_CD = 3;

        int INPUT_PRC_QLFY_VAL_TXT = 4;

        int INPUT_PROD_CTRL_NM = 5;

        int INPUT_PRC_LIST_EQUIP_PK = 6;

        int INPUT_LIST = 7;

        int MAX_INPUT_PARAM_NUM = INPUT_LIST;

        /*-------------------------------
         * Filter Popup Parameters
         *------------------------------*/
        /* ------ Parameter Number ------ */
        int FILTER_PRC_LIST_STRU_TP_CD = 0;

        int FILTER_PRC_CATG_CD = 1;

        int FILTER_PRC_CATG_NM = 2;

        int FILTER_PRC_QLFY_TP_CD = 3;

        int FILTER_PRC_QLFY_VAL_TXT = 4;

        int FILTER_MDL_NM = 5;

        int FILTER_PRC_MTR_PKG_NM = 6;

        int FILTER_PRC_PGM_TP_CD = 7;

        int FILTER_PRC_EQUIP_TP_CD = 8;

        int FILTER_SVC_SEG_CD = 9;

        int FILTER_PRC_IN_OUT_RG_CD = 10;

        int FILTER_PRC_LIST_EQUIP_CONFIG_NUM = 11;

        int FILTER_PRC_LIST_EQUIP_CONFIG_NM = 12;

        int FILTER_PKG_UOM_CD = 13;

        int FILTER_PRC_RATE_TP_CD = 14;

        int FILTER_PRC_SVC_PLN_TP_CD = 15;

        int FILTER_PRC_SVC_CONTR_TP_CD = 16;

        int FILTER_MTR_LB_NM = 17;

        // 2018/11/17 QC#29147 Mod Start
        // int FILTER_PRC_LIST_BAND_CD = 18;
        int FILTER_PRC_LIST_BAND_DESC_TXT = 18;
        // 2018/11/17 QC#29147 Mod End

        int FILTER_PRC_SVC_TIER_TP_CD = 19;

        int FILTER_SPLY_AGMT_PLN_NM = 20;

        int FILTER_MDSE_CD = 21;

        int FILTER_PRC_ADDL_CHRG_TP_CD = 22;

        int FILTER_MKT_MDL_SEG_CD = 23;

        int FILTER_PRC_LEASE_CMPY_ABBR_NM = 24;

        int FILTER_DS_ACCT_NM = 25;

        // Mod Start 2018/07/17 QC#20267
        int FILTER_MNF_ITEM_CD = 26;

        int FILTER_MDSE_DESC_SHORT_TXT = 27;

        int FILTER_COA_MDSE_TP_NM = 28;

        int FILTER_MDSE_ITEM_TP_NM = 29;

        int FILTER_COA_PROD_NM = 30;

        int FILTER_T_MDL_NM = 31;

        int FILTER_ZEROTH_PROD_CTRL_CD = 32;

        int FILTER_FIRST_PROD_CTRL_CD = 33;

        int FILTER_SCD_PROD_CTRL_CD = 34;

        int FILTER_THIRD_PROD_CTRL_CD = 35;

        int FILTER_FRTH_PROD_CTRL_CD = 36;

        int FILTER_PRC_TERM_AOT = 37;

        int FILTER_PRC_TERM_UOM_CD = 38;

        int FILTER_CUST_BID_QTY = 39;

        int FILTER_PRC_FMLA_NM = 40;

        int FILTER_OPEN_MKT_FLG = 41;

        int FILTER_COMP_CD = 42;

        int FILTER_TERM_FROM_MTH_AOT = 43;

        int FILTER_TERM_THRU_MTH_AOT = 44;

        int FILTER_PRC_SVC_ZONE_CD = 45;

        int FILTER_MDSE_NM = 46;

        int FILTER_XX_YES_NO_CD = 47;

        int FILTER_PRC_LIST_MDSE_CD = 48;

        int FILTER_EFF_FROM_DT_H1 = 49;

        int FILTER_EFF_FROM_DT_H2 = 50;

        int FILTER_EFF_THRU_DT_H1 = 51;

        int FILTER_EFF_THRU_DT_H2 = 52;

        int FILTER_XX_FULL_NM_H1 = 53;

        int FILTER_XX_FULL_NM_H2 = 54;

        int FILTER_CRAT_DT_H1 = 55;

        int FILTER_CRAT_DT_H2 = 56;

        int FILTER_LAST_UPD_DT_H1 = 57;

        int FILTER_LAST_UPD_DT_H2 = 58;

        int FILTER_MAX_PARAM_NUM = 59;
        // Mod End 2018/07/17 QC#20267
    }

    // 2018/08/22 QC#26664 Add Start
    /** Digit On */
    public static int DIGIT_ON = 1;

    /** Digit Off */
    public static int DIGIT_OFF = 0;

    /** Excel Column Type : Equipment for Download */
    public static final int[][] EXCEL_COLUMNTYPE_EQUIP_DOWNLOAD = new int[][] {
         {29, DIGIT_ON}  // Price
        ,{30, DIGIT_ON}  // Min Price
        ,{31, DIGIT_ON}  // Max Price
        ,{34, DIGIT_OFF} // Bid Qty
        ,{35, DIGIT_ON}  // Monthly Payment Amount
        ,{36, DIGIT_ON}  // Lease Payment Min
        ,{37, DIGIT_ON}  // Lease Payment Max
        ,{42, DIGIT_ON}  // Quota Rev
    };

    /** Excel Column Type : Equipment for Download As Template */
    public static final int[][] EXCEL_COLUMNTYPE_EQUIP_DWLDTEMPLATE = new int[][] {
         { 7, DIGIT_ON}  // Price
        ,{ 8, DIGIT_ON}  // Min Price
        ,{ 9, DIGIT_ON}  // Max Price
        ,{12, DIGIT_OFF} // Bid Qty
        ,{13, DIGIT_ON}  // Monthly Payment Amount
        ,{14, DIGIT_ON}  // Lease Payment Min
        ,{15, DIGIT_ON}  // Lease Payment Max
        ,{18, DIGIT_ON}  // Quota Rev
    };

    /** Excel Column Type : Service for Download */
    public static final int[][] EXCEL_COLUMNTYPE_SVC_DOWNLOAD = new int[][] {
         {21, DIGIT_OFF} // Min Vol
        ,{22, DIGIT_OFF} // Max Vol
        ,{24, DIGIT_ON}  // Base Amount
        ,{25, DIGIT_ON}  // Min Base
        ,{26, DIGIT_ON}  // Max Base
        ,{36, DIGIT_ON}  // Quota Rev
    };

    /** Excel Column Type : Service for Download As Template */
    public static final int[][] EXCEL_COLUMNTYPE_SVC_DWLDTEMPLATE = new int[][] {
         { 8, DIGIT_OFF} // Min Vol
        ,{ 9, DIGIT_OFF} // Max Vol
        ,{11, DIGIT_ON}  // Base Amount
        ,{12, DIGIT_ON}  // Min Base
        ,{13, DIGIT_ON}  // Max Base
        ,{23, DIGIT_ON}  // Quota Rev
    };

    /** Excel Column Type : Service Tiers for Download */
    public static final int[][] EXCEL_COLUMNTYYPE_SVC_TIER_DOWNLOAD = new int[][] {
         {19, DIGIT_OFF} // Avg Copy Volume
        ,{20, DIGIT_OFF} // Min Vol
        ,{21, DIGIT_OFF} // Max Vol
        ,{23, DIGIT_ON}  // Base Amount
        ,{24, DIGIT_ON}  // Min Base
        ,{25, DIGIT_ON}  // Max Base
    };

    /** Excel Column Type : Service Tiers for Download As Template */
    public static final int[][] EXCEL_COLUMNTYPE_SVC_TIER_DWLDTEMPLATE = new int[][] {
         { 8, DIGIT_OFF} // Avg Copy Volume
        ,{ 9, DIGIT_OFF} // Min Vol
        ,{10, DIGIT_OFF} // Max Vol
        ,{12, DIGIT_ON}  // Base Amount
        ,{13, DIGIT_ON}  // Min Base
        ,{14, DIGIT_ON}  // Max Base
    };

    /** Excel Column Type : Service Special Changes for Download */
    public static final int[][] EXCEL_COLUMNTYPE_SVC_SPEC_CHRG_DOWNLOAD = new int[][] {
         {18, DIGIT_ON}  // Price
    };

    /** Excel Column Type : Service Special Changes for Download As Template */
    public static final int[][] EXCEL_COLUMNTYPE_SVC_SPEC_CHRG_DWLDTEMPLATE = new int[][] {
         { 6, DIGIT_ON}  // Price
    };

    /** Excel Column Type : Supply Program for Download */
    public static final int[][] EXCEL_COLUMNTYPE_SPLY_PGM_DOWNLOAD = new int[][] {
         {18, DIGIT_OFF} // Min Vol
        ,{19, DIGIT_OFF} // Max Vol
        ,{21, DIGIT_ON}  // Total Base Amount
        ,{22, DIGIT_ON}  // Supply Base Amount
    };

    /** Excel Column Type : Supply Program for Download As Template*/
    public static final int[][] EXCEL_COLUMNTYPE_SPLY_PGM_DWLDTEMPLATE = new int[][] {
         { 7, DIGIT_OFF} // Min Vol
        ,{ 8, DIGIT_OFF} // Max Vol
        ,{10, DIGIT_ON}  // Total Base Amount
        ,{11, DIGIT_ON}  // Supply Base Amount
    };

    /** Excel Column Type : Lease Rates for Download */
    public static final int[][] EXCEL_COLUMNTYPE_LEASE_RATE_DOWNLOAD = new int[][] {
         {18, DIGIT_ON}  // Total Financed Min
        ,{19, DIGIT_ON}  // Total Financed Max
    };

    /** Excel Column Type : Lease Rates for Download As Template */
    public static final int[][] EXCEL_COLUMNTYPE_LEASE_RATE_DWLDTEMPLATE = new int[][] {
         { 7, DIGIT_ON}  // Total Financed Min
        ,{ 8, DIGIT_ON}  // Total Financed Max
    };

    /** Excel Column Type : Lease Return Fees for Download */
    public static final int[][] EXCEL_COLUMNTYPE_LEASE_RTRN_FEES_DOWNLOAD = new int[][] {
         {16, DIGIT_ON}  // Distance (Miles)
        ,{17, DIGIT_ON}  // Return Fee
    };

    /** Excel Column Type : Lease Return Fees for Download As Template */
    public static final int[][] EXCEL_COLUMNTYPE_LEASE_RTRN_FEES_DWLDTEMPLATE = new int[][] {
         { 5, DIGIT_ON}  // Distance (Miles)
        ,{ 6, DIGIT_ON}  // Return Fee
    };

    /** Excel Column Type : Trade Ins for Download */
    public static final int[][] EXCEL_COLUMNTYPE_TRADE_IN_DOWNLOAD = new int[][] {
         {14, DIGIT_ON}  // Trade In Value
        ,{16, DIGIT_OFF} // Meter From
        ,{17, DIGIT_OFF} // Meter To
    };

    /** Excel Column Type : Trade Ins for Download As Template */
    public static final int[][] EXCEL_COLUMNTYPE_TRADE_IN_DWLDTEMPLATE = new int[][] {
         { 3, DIGIT_ON}  // Trade In Value
        ,{ 5, DIGIT_OFF} // Meter From
        ,{ 6, DIGIT_OFF} // Meter To
    };
    // 2018/08/22 QC#26664 Add End
}
