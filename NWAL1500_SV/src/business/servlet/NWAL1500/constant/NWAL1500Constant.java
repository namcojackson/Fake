/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NWAL1500.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         S.Takami        Create          N/A
 * 2015/10/01   Fujitsu         T.Yoshida       Create          N/A
 * 2016/01/20   Fujitsu         S.Takami        Update          S21_NA#3396
 * 2016/02/22   Fujitsu         Y.Taoka         Update          QC#1694
 * 2016/02/26   Fujitsu         M.Suzuki        Update          S21_NA#966
 * 2016/05/09   Fujitsu         S.Takami        Update          (NMAL6760 Calling Mode)
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#8393
 * 2016/08/23   Hitachi         Y.Takeno        Update          S21_NA#12024
 * 2016/09/21   Fujitsu         S.Iidaka        Update          UnitTest#201
 * 2016/10/03   Fujitsu         S.Iidaka        Update          S21_NA#13958
 * 2016/12/08   Fujitsu         S.Ohki          Update          S21_NA#15934
 * 2018/06/14   Fujitsu         A.Kosai         Update          S21_NA#25227
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/12/12   Fujitsu         S.Kosaka        Update          QC#29315
 * 2019/05/09   Fujitsu         R.Nakamura      Update          S21_NA#50015
 * 2024/02/21   CITS            T.Miki          Update          CSA QC#63577
 * </pre>
 */
public class NWAL1500Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1500";

    /** ScreenID */
    public static final String SCREEN_ID = "NWAL1500Scrn00";

    /** Common button 1 */
    public static final String BTN_CMN_SAV_BTN_NM = "btn1";

    /** Function Button 1 Event Name */
    public static final String BTN_CMN_SAV_EVENT_NM = "CMN_Save";

    /** Function Button 1 Label */
    public static final String BTN_CMN_SAV_LABEL = "Save";

    /** Function Button 2 Button Name */
    public static final String BTN_CMN_SUB_BTN_NM = "btn2";

    /** Function Button 2 Event Name */
    public static final String BTN_CMN_SUB_EVENT_NM = "CMN_Submit";

    /** Function Button 2 Label */
    public static final String BTN_CMN_SUB_LABEL = "Submit";

    /** Function Button 3 Button Name */
    public static final String BTN_CMN_APL_BTN_NM = "btn3";

    /** Function Button 3 Event Name */
    public static final String BTN_CMN_APL_EVENT_NM = "CMN_Apply";

    /** Function Button 3 Label */
    public static final String BTN_CMN_APL_LABEL = "Apply";

    /** Function Button 4 Button Name */
    public static final String BTN_CMN_APR_BTN_NM = "btn4";

    /** Function Button 4 Event Name */
    public static final String BTN_CMN_APR_EVENT_NM = "CMN_Approve";

    /** Function Button 4 Label */
    public static final String BTN_CMN_APR_LABEL = "Approve";

    /** Function Button 5 Button Name */
    public static final String BTN_CMN_RJT_BTN_NM = "btn5";

    /** Function Button 5 Event Name */
    public static final String BTN_CMN_RJT_EVENT_NM = "CMN_Reject";

    /** Function Button 5 Label */
    public static final String BTN_CMN_RJT_LABEL = "Reject";

    /** Function Button 6 Button Name */
    public static final String BTN_CMN_DWL_BTN_NM = "btn6";

    /** Function Button 6 Event Name */
    public static final String BTN_CMN_DWL_EVENT_NM = "CMN_Download";

    /** Function Button 6 Label */
    public static final String BTN_CMN_DWL_LABEL = "Download";

    /** Function Button 7 Button Name */
    public static final String BTN_CMN_DEL_BTN_NM = "btn7";

    /** Function Button 7 Event Name */
    public static final String BTN_CMN_DEL_EVENT_NM = "CMN_Delete";

    /** Function Button 7 Label */
    public static final String BTN_CMN_DEL_LABEL = "Delete";

    /** Function Button 8 Button Name */
    public static final String BTN_CMN_CLR_BTN_NM = "btn8";

    /** Function Button 8 Event Name */
    public static final String BTN_CMN_CLR_EVENT_NM = "CMN_Clear";

    /** Function Button 8 Label */
    public static final String BTN_CMN_CLR_LABEL = "Clear";

    /** Function Button 9 Button Name */
    public static final String BTN_CMN_RST_BTN_NM = "btn9";

    /** Function Button 9 Event Name */
    public static final String BTN_CMN_RST_EVENT_NM = "CMN_Reset";

    /** Function Button 9 Label */
    public static final String BTN_CMN_RST_LABEL = "Reset";

    /** Function Button 10 Button Name */
    public static final String BTN_CMN_RTN_BTN_NM = "btn10";

    /** Function Button 10 Event Name */
    public static final String BTN_CMN_RTN_EVENT_NM = "CMN_Return";

    /** Function Button 10 Label */
    public static final String BTN_CMN_RTN_LABEL = "Return";

    /** Function Button 11 Button Name */
    public static final String BTN_CMN_CFG_BTN_NM = "btn11"; // S21_NA#6306

    /** Function Button 11 Event Name */
    public static final String BTN_CMN_CFG_EVENT_NM = "Line_Config_Add";

    /** Function Button 11 Label */
    public static final String BTN_CMN_CFG_LABEL = "Add Config";

    /** Function Button 12 Button Name */
    public static final String BTN_CMN_ADD_BTN_NM = "btn12"; // S21_NA#6306

    /** Function Button 12 Event Name */
    public static final String BTN_CMN_ADD_EVENT_NM = "Line_Add";

    /** Function Button 12 Label */
    public static final String BTN_CMN_ADD_LABEL = "Add Line";

    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";

    /** Tab anchor Additional Header */
    public static final String TAB_ADDL_HEADER = "Addl_Header";

    /** Tab anchor Line Config */
    public static final String TAB_LINE_CONFIG = "Line_Config";

    /** Tab anchor RMA */
    public static final String TAB_RMA = "RMA";

    /** Therefore Index Update API Business ID */
    public static final String BIZ_ID_THEREFORE_API = "NWZC224001";

    // ----------- Header ** Event Button -----------
    /** Button Search Button Event Name */
    public static final String BTN_ORDER_SRCH_EVENT_NM = "Order_Search";

    // 2024/02/21 CSA QC#63577 Add Start
    /** Button Book Button Event Name */
    public static final String BTN_PRICING_EVENT_NM = "Pricing";
    // 2024/02/21 CSA QC#63577 Add End

    /** Button Book Button Event Name */
    public static final String BTN_BOOK_EVENT_NM = "Book";

    /** Button Copy Button Event Name */
    public static final String BTN_ORDER_COPY_EVENT_NM = "OpenWin_Copy";

    /** Button OrderCancel Button Event Name */
    public static final String BTN_ORDER_CANCEL_EVENT_NM = "Order_Cancel";

    /** Button Holds Button Event Name */
    public static final String BTN_HOLDS_EVENT_NM = "OpenWin_Holds";

    /** Button DeliveryInfo Button Event Name */
    public static final String BTN_DELIVERYINFO_EVENT_NM = "OpenWin_DeliveryInfo";

    /** Button ShippingDetail Button Event Name */
    public static final String BTN_SHIPPINGDETAIL_EVENT_NM = "OpenWin_ShippingDetail";

    /** Button Profitability Button Event Name */
    public static final String BTN_PROFITABILITY_EVENT_NM = "OpenWin_Profitability";

    /** Button DICheck Button Event Name */
    public static final String BTN_DICHECK_EVENT_NM = "OpenWin_DICheck";

    /** Button ServicePricing Button Event Name */
    public static final String BTN_SERVICEPRIC_EVENT_NM = "OpenWin_ServicePricing";

    /** Button CreditAndRebill Button Event Name */
    public static final String BTN_CREDITANDREBILL_EVENT_NM = "OpenWin_CreditAndRebill";

    /** Button ChangeOrderModification Button Event Name */
    public static final String BTN_CHANGEORDERMOD_EVENT_NM = "OpenWin_ChangeOrderModification";

    /** Button CalculationOrderAmount Button Event Name */
    public static final String BTN_ORDERAMTCALC_EVENT_NM = "Calculation_Order_Amount";

    /** Button Attachments Button Event Name */
    public static final String BTN_ATTACHMENTS_EVENT_NM = "MoveWin_Attach";

    /** Button Workflow Button Event Name */
    public static final String BTN_WORKFLOW_EVENT_NM = "MoveWin_WorkFlow";

    // ----------- Line Config Tab ** Event Button -----------
    /** Button SpecialInstruction Button Event Name */
    public static final String BTN_CREDITCARD_EVENT_NM = "MoveWin_CreditCard";

    // ----------- Line Config Tab ** Event Button -----------
    /** Button Add NewConfig Button Event Name */
    public static final String BTN_ADD_NEWCONFIG_EVENT_NM = "Line_Config_Add";

    /** Button Add NewLine Button Event Name */
    public static final String BTN_ADD_NEWLINE_NM = "Line_Add";

    /** Button LineCancel Button Event Name */
    public static final String BTN_LINE_CANCEL_EVENT_NM = "OpenWin_LineCancel";

    /** Button CopyLine Button Event Name */
    public static final String BTN_COPYLINE_EVENT_NM = "OpenWin_CopyLine";

    /** Button CopyFrom Button Event Name */
    public static final String BTN_COPYFROM_EVENT_NM = "OpenWin_CopyFrom";

    /** Button AutoAddSupply Button Event Name */
    public static final String BTN_AUTO_ADD_SUPPLY_EVENT_NM = "Auto_AddSupply";

    /** Button AddRMA Button Event Name */
    public static final String BTN_AUTO_ADD_RMA_EVENT_NM = "Auto_AddRMA";

    /** Button Buyout Button Name */
    public static final String BTN_BUYOUT_BTN_NM = "OpenWin_Buyout";

    /** Button Buyout Button Event Name */
    public static final String BTN_BUYOUT_EVENT_NM = "OpenWin_Buyout";

    /** Button Buyout Button Label */
    public static final String BTN_BUYOUT_LABEL = "Buyout";

    /** Button PriceChanges Button Event Name */
    public static final String BTN_PRC_CHG_EVENT_NM = "OpenWin_PriceChanges";

    /** Button SalesCredit Button Event Name */
    public static final String BTN_SLS_CR_EVENT_NM = "OpenWin_SalesCredit";

    /** Button MassUpdate Button Event Name */
    public static final String BTN_MASSUPDATE_EVENT_NM = "OpenWin_MassUpdate";

    /** Button AddlLineDetails Button Event Name */
    public static final String BTN_ADDL_DTL_EVENT_NM = "OpenWin_AddlLineDetails";

    /** Button StockOverview Button Event Name */
    public static final String BTN_STC_OVER_VIEW_EVENT_NM = "OpenWin_StockOverview";

    /** Button ControlFields Button Event Name */
    public static final String BTN_CTR_FLD_EVENT_NM = "OpenWin_ControlFields";

    /** Button MeterEntry Button Event Name */
    public static final String BTN_METERENTRY_EVENT_NM = "OpenWin_MeterEntry";

    /** Button SpecialInstruction Button Event Name */
    public static final String BTN_SPESIAL_INS_EVENT_NM = "OpenWin_SpecialInstruction";

    // ----------- Common ** OnChange Event-----------
    /** OnChange Order Entry Action Event Name */
    public static final String ON_CHANGE_ORD_ENTRY_ACTION_EVENT_NM = "OnChange_OrderEntryAction";

    /** OnChange Order Entry Action Addl Information Event Name */
    public static final String ON_CHANGE_ORD_ENTRY_ACTION_ADDL_INFO_EVENT_NM = "OnChange_OrderEntryActionAddlInfo";

    /** OnChange Order Entry Action Delivery Event Name */
    public static final String ON_CHANGE_ORD_ENTRY_ACTION_DELY_EVENT_NM = "OnChange_OrderEntryActionDelivery";

    /** OnChange Order Entry Action Tool Event Name */
    public static final String ON_CHANGE_ORD_ENTRY_ACTION_TOOL_EVENT_NM = "OnChange_OrderEntryActionTool";

    /** No Selected Detail Line */
    public static final int NO_SLCT_DTL = -1;

    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 12 */
    public static final int IDX_12 = 12; // 2015/16/11 S21_NA#7861 Add

    /** Index Number 13 */
    public static final int IDX_13 = 13;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Index Number 33 */
    public static final int IDX_33 = 33;

    /** Index Number 50 */
    public static final int IDX_50 = 50;

    /** Index Number 100 */
    public static final int IDX_100 = 100;

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** Line break */
    public static final String ENTER = "\r\n";

    /** 8 Digit Mode */
    public static final String EIGHT_DIGIT_MODE = "08";

    /** 10 Digit Mode */
    public static final String TEN_DIGIT_MODE = "10";

    /** Percent */
    public static final String PERCENT = "%"; // S21_NA#8393 Add

    // 2018/06/14 S21_NA#25227 Add Start
    /** Period */
    public static final String PERIOD = ".";
    // 2018/06/14 S21_NA#25227 Add End

    // 2016/01/20 S21_NA#3396 Add Start
    /** WorkFlow Popup Mode: 1: Group Name */
    public static final String WF_CALL_MODE_GRP_NM = "1";
    // 2016/01/20 S21_NA#3396 Add End

    // 2016/10/03 S21_NA#13958 ADD Start
    /** Credit Card Popup Mode : 0 READ ONLY */
    public static final String CR_CARD_MODE_READ_ONLY = "0";
    // 2016/10/03 S21_NA#13958 ADD End

    // 2016/01/20 S21_NA#966 Add Start
    /** Attach Business Name */
    public static final String ATTACH_BUSINESS_NM = "Order Entry";

    /** Attach Data Nm */
    public static final String ATTACH_DATA_ORD_NUM = "Order Number";

    /** Attachment Limit Num Constant: NWAL1500_ATT_LIMIT */
    public static final String NWAL1500_ATT_LIMIT = "NWAL1500_ATT_LIMIT";

    /** Attachment Limit Num Constant: NWAL1500_AUTHORIZE_FILE_EXT */
    public static final String NWAL1500_AUTHORIZE_FILE_EXT = "NWAL1500_AUTHORIZE_FILE_EXT";

    /** Attachment Limit Num Constant: NWAL1500_AUTHORIZE_FILE_VOL */
    public static final String NWAL1500_AUTHORIZE_FILE_VOL = "NWAL1500_AUTHORIZE_FILE_VOL";
    // 2016/01/20 S21_NA#966 Add End

    // 2016/01/20 S21_NA#12024 Add Start
    public static final String NLCL1000_DROP_SHIP_RTL_WH_CD = "NLCL1000_DROP_SHIP_RTL_WH_CD";

    public static final String DEF_DROP_SHIP_RTL_WH_CD = "DS";
    // 2016/01/20 S21_NA#12024 Add End

    // Add Start 2019/05/09 QC#50015
    /** Document Type Code for Logistics: NWAL1500_DOC_TP_CD_AUTH_LGSC */
    public static final String NWAL1500_DOC_TP_CD_AUTH_LGSC = "NWAL1500_DOC_TP_CD_AUTH_LGSC";

    /** Document Type Code for OTC: NWAL1500_DOC_TP_CD_AUTH_OTC */
    public static final String NWAL1500_DOC_TP_CD_AUTH_OTC = "NWAL1500_DOC_TP_CD_AUTH_OTC";
    // Add End 2019/05/09 QC#50015

    // 2016/12/07 S21_NA#15934 Add Start
    public static final String CPO_DTL_LINE_SUB_NUM_SET_PRNT = "000";
    // 2016/12/07 S21_NA#15934 Add End

    // 2018/07/27 S21_NA#14307 Add Start
    /** Result Parameter(toSpecialInstruction) */
    public static final String RESULT_PARAM_SPECIAL_INSTRUCTION = "toSpecialInstruction";
    // 2018/07/27 S21_NA#14307 Add End

    // 2018/12/12 QC#29315 Add Start
    /** Freight Term COLLECT */
    public static final String FRT_COND_CD_COLLECT = "F04";
    // 2018/12/12 QC#29315 Add End

    /** Bill Event List */
    public static final List<String> BILL_EVENT_LIST;
    static {
        List<String> billEventList = new ArrayList<String>();
        billEventList.add("OpenWin_BillTo");
        billEventList.add("OnBlur_DeriveFromBillToName");
        billEventList.add("OnBlur_DeriveFromBillToAccount");
        billEventList.add("OnBlur_DeriveFromBillToLocation");
        BILL_EVENT_LIST = Collections.unmodifiableList(billEventList);
    }

    /** Ship Event List */
    public static final List<String> SHIP_EVENT_LIST;
    static {
        List<String> shipEventList = new ArrayList<String>();
        shipEventList.add("OpenWin_ShipTo");
        shipEventList.add("OnBlur_DeriveFromShipToName");
        shipEventList.add("OnBlur_DeriveFromShipToAccount");
        shipEventList.add("OnBlur_DeriveFromShipToLocation");
        SHIP_EVENT_LIST = Collections.unmodifiableList(shipEventList);
    }

    /** Sold Event List */
    public static final List<String> SOLD_EVENT_LIST;
    static {
        List<String> soldEventList = new ArrayList<String>();
        soldEventList.add("OpenWin_SoldTo");
        soldEventList.add("OnBlur_DeriveFromSoldToName");
        soldEventList.add("OnBlur_DeriveFromSoldToAccount");
        soldEventList.add("OnBlur_DeriveFromSoldToLocation");
        SOLD_EVENT_LIST = Collections.unmodifiableList(soldEventList);
    }
}
