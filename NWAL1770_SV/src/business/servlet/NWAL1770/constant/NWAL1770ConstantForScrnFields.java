/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/01   Hitachi         E.Kameishi      Update          QC#20295
 * 2018/09/27   Fujitsu         S.Kosaka        Update          QC#28517
 *</pre>
 */
public class NWAL1770ConstantForScrnFields {

    // ----------- Event Button -----------
    /** Search Button Event Name */
    public static final String BTN_QUOTE_SRCH_EVENT_NM = "Quote_Search";

    /** Copy Button Event Name */
    public static final String BTN_QUOTE_COPY_EVENT_NM = "Quote_Copy";

    /** Sales Credit Button Event Name */
    public static final String BTN_SLS_CREDIT_EVENT_NM = "OpenWin_SalesCredit";

    /** Shipping Detail Button Event Name */
    public static final String BTN_SHPG_DTL_EVENT_NM = "OpenWin_ShippingDetail";

    /** Attachment Button Event Name */
    public static final String BTN_ATT_EVENT_NM = "OpenWin_Attachments";

    /** Calculation Quote Amount Button Event Name */
    public static final String BTN_CALC_QUOTE_AMT_EVENT_NM = "Calculation_QuoteAmount";

    /** Special Instruction Button Event Name */
    public static final String BTN_SPCL_INSTN_EVENT_NM = "OpenWin_SpecialInstruction";

    /** Confirmations Button Event Name */
    public static final String BTN_CONF_EVENT_NM = "OpenWin_Confirmation";

    /** Tracking Button Event Name */
    public static final String BTN_TRK_EVENT_NM = "OpenWin_Tracking";

    /** Add Line Button Event Name (Contact) */
    public static final String BTN_ADD_CTAC_EVENT_NM = "Add_ContactLine";

    /** Delete Line Button Event Name */
    public static final String BTN_DEL_CTAC_EVENT_NM = "Delete_ContactLine";

    /** Credit Card Button Event Name */
    public static final String BTN_CREDIT_CARD_EVENT_NM = "MoveWin_CreditCard";

    /** Add Line Button Event Name (Item) */
    public static final String BTN_ADD_ITEM_EVENT_NM = "Add_ItemLine";

    /** Cancel Button Event Name */
    public static final String BTN_CANC_EVENT_NM = "Cancel_ItemLine";

    /** Profitability Button Event Name */
    public static final String BTN_PRFT_EVENT_NM = "OpenWin_Profitability";

    /** Price Changes Button Event Name */
    public static final String BTN_PRC_DTL_EVENT_NM = "OpenWin_PriceChanges";

    /** Invty. Inq Button Event Name */
    public static final String BTN_INVTY_INQ_EVENT_NM = "OpenWin_StockOverview";

    /** Check Contract Button Event Name */
    public static final String BTN_CHK_CONTR_EVENT_NM = "OpenWin_CheckContract";

    /** View Change Log Button Event Name */
    public static final String BTN_VIEW_CHNG_LOG_EVENT_NM = "OpenWin_ViewChangeLog";

    // ----------- Authority -----------
    /** Reference Authority */
    public static final String REF_AUTH = "NWAL1770T010";

    /** Registration Authority (No Price Authority) */
    public static final String NO_PRC_RGTN_AUTH = "NWAL1770T020";

    /** Registration Authority */
    public static final String ALL_RGTN_AUTH = "NWAL1770T030";

    //START 2017/08/01 E.Kameishi [QC#20295,ADD]
    /** Function Button 12 Button Name */
    public static final String BTN_CMN_ADD_BTN_NM = "btn12";

    /** Function Button 12 Event Name */
    public static final String BTN_CMN_ADD_EVENT_NM = "Add_ItemLine";

    /** Function Button 12 Label */
    public static final String BTN_CMN_ADD_LABEL = "Add Line";
    //END 2017/08/01 E.Kameishi [QC#20295,ADD]

    // QC#28517 2018/09/27 Add Start
    /** Digit size xxTotUnitNetWt_C */
    public static final int DIGIT_TOT_UNIT_NET_WT = 4;
    // QC#28517 2018/09/27 Add End
}
