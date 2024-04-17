/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0860.constant;

/**
 *<pre>
 * NFCL0860Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/22    Fujitsu         S.Fujita        Create          N/A
 * 2016/5/17    Fujitsu         S.Fujita        Update          QC#8431
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2022/06/04   CITS            K.Suzuki        Update          QC#59333-1
 *</pre>
 */
public class NFCL0860Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFCL0860";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NFCL0860Scrn00";

    /** Table Name */
    public static final String DS_INV_MTR_DTL = "DS_INV_MTR_DTL";

    /** INV_TP_CD(Credit Memo) */
    public static final String INV_TP_CD = "2";

    /** ViewScale("2") */
    public static final int VIEW_SCALE = 2;

    /** ApplyBatchAPI RtnCd-UnProcces("0") */
    public static final String APPLY_RTNCD_UN_PROCCES = "0";

    /** ApplyBatchAPI RtnCd-Normal("1") */
    public static final  String APPLY_RTNCD_NORMAL = "1";

    /** ApplyBatchAPI RtnCd-CashappError("2") */
    public static final String APPLY_RTNCD_CASHAPP_ERROR = "2";

    /** ApplyBatchAPI RtnCd-ExclusionError("3") */
    public static final String APPLY_RTNCD_EXCLUSION_ERROR = "3";

    /** ApplyBatchAPI RtnCd-OthersError("4") */
    public static final String APPLY_RTNCD_OTHERS_ERROR = "4";

 // --------------------------------
    // INV_TP_CD
    // --------------------------------
    /** INV */
    public static final String INV = "INV";

    /** CRM */
    public static final String CRM = "CRM";

    /** DEM */
    public static final String DEM = "DEM";

    /** ACC */
    public static final String ACC = "ACC";

    /** DED */
    public static final String DED = "DED";

    // --------------------------------
    // INV_TP_NM
    // --------------------------------

    /** Payment */
    public static final String PAYMENT = "Payment";

    /** Invoice */
    public static final String INVOICE = "Invoice";

    /** Credit Memo */
    public static final String CREDITMEMO = "Credit Memo";

    /** Debit Memo */
    public static final String DEBITMEMO = "Debit Memo";

    /** On Account */
    public static final String ONACCOUNT = "On Account";

    /** Late Fee */
    public static final String LATEFEE = "Late Fee";

    /** Adjustment */
    public static final String ADJUSTMENT = "Adjustment";

    // --------------------------------
    // Message Parameter
    // --------------------------------
    /** Selected Transaction */
    public static final String ERRMSG_DETAIL = "Selected Transaction";

    /** details */
    public static final String DETAILS = "details";

    /** header */
    public static final String HEADER = "header";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** No search results found. */
    public static final String ZZZM9005W = "ZZZM9005W";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** System error has occurred.  Please contact IT Support. */
    public static final String NFCM0041E = "NFCM0041E";

    /** Please select @. */
    public static final String NFDM0002E = "NFDM0002E";

 // --------------------------------
    // CSV File Download
    // --------------------------------
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NFCL0860_ARActivityInquiry";

    /** CSV Header For Download */
    // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
    // START 2016/05/17 S.Fujita [QC#8431,MOD]
//    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {"ChkBox", "Type", "C", "Activity Name", "Number", "Activity Date", "Amount", "Amt Applied", "Appl Date", "GL Date", "Comments" };
//    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {"ChkBox", "Type", "Activity Name", "Number", "Activity Date", "Amount", "Amt Applied", "Appl Date", "GL Date", "Status", "Comments" };
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {"ChkBox", "Type", "Activity Name", "Number", "Activity Date", "Amount", "Amt Applied", "Appl Date", "GL Date", "Status", "Comments", "Note" };
    // END 2016/05/17 S.Fujita [QC#8431,MOD]
    // END 2017/08/21 T.Tsuchida [QC#19573,MOD]

    /** RcptChkNum */
    public static final String AR_RCPT_CHK_NUM = "0000000000";
}
