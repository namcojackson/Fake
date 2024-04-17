/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6184
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2018/08/01   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 *</pre>
 */
public class NMAL6730Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_BLANK6 = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Apply Template */
    public static final String[] BTN_TEMPLATE = {"ApplyTemplate", "ApplyTemplate" };

// QC#6382
//    /** Button OpenWin_Coa */
//    public static final String[] BTN_OPEN_WIN_COA = {"OpenWin_Coa", "OpenWin_Coa" };
    
    /** Button Get CoA Channel Name */
    public static final String[] BTN_GET_COA_CH_NM = {"GetCoaChNm", "GetCoaChNm" };
    
    /** Button Get CoA Affiliate Name */
    public static final String[] BTN_GET_COA_AFFL_NM = {"GetInterCompanyNm", "GetInterCompanyNm" };

    /** Button Add Invoice */
    public static final String[] BTN_ADD_INV_SRC = {"AddInvoiceSource", "AddInvoiceSource" };

    /** Button Delete Invoice */
    public static final String[] BTN_DEL_INV_SRC = {"DeleteInvoiceSource", "DeleteInvoiceSource" };

    /** Button Add Attribute */
    public static final String[] BTN_ADD_ATTRIBUTE = {"AddAttribute", "AddAttribute" };

    /** Button Delete Attribute */
    public static final String[] BTN_DEL_ATTRIBUTE = {"DeleteAttribute", "DeleteAttribute" };

    /** Button GetCltCustTpNm */
    public static final String[] BTN_GET_CLT_CUST = {"GetCltCustTpNm", "GetCltCustTpNm" };

    /** Button GetCltPtfoNm */
    public static final String[] BTN_GET_CLT_PTFO = {"GetCltPtfoNm", "GetCltPtfoNm" };

    /** Button OpenWin_Resrc */
    public static final String[] BTN_OPEN_WIN_RESRC = {"OpenWin_Resrc", "OpenWin_Resrc" };

    /** Button OpenWin_CtacPsn */
    public static final String[] BTN_OPEN_WIN_CTAC_PSN = {"OpenWin_CtacPsn", "OpenWin_CtacPsn" };
    /** Screen ID */
    public static final String SCREEN_ID = "NMAL6730Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL6730";

    /** Function ID1 */
    public static final String FUNC_ID1 = "NMAL6730001";

    /** Function ID2 */
    public static final String FUNC_ID2 = "NMAL6730002";

    /** Close Button Name */
    public static final String POPUP_CLOSE = "CMN_Close";

    /** TAB_FINANCIAL */
    public static final String TAB_FINANCIAL = "Financial";

    /** TAB_INV_BLLG */
    public static final String TAB_INV_BLLG = "InvBllg";

    // Add Start 2018/08/01 QC#27222
    /** TAB_TAXING */
    public static final String TAB_TAXING = "Taxing";
    // Add End 2018/08/01 QC#27222

    /** CLOSE */
    public static final String CLOSE = "CLOSE";

    /** @ cannot be added because it is exceeding the maximum number of row [@] */
    public static final String NMAM8187E = "NMAM8187E";

    /** Error Message (Mandatory Key Violation) */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** The selected record has already been registered and cannot be deleted. */
    public static final String NMAM8255E = "NMAM8255E";

    /** If [@] is entered, please enter [@], too. */
    public static final String NMAM0049E = "NMAM0049E";

    /** Please enter [@] to set up [@]. */
    public static final String NAMM0016E = "NAMM0016E";
    
    // QC#7781
    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";
    
    /** [@] field has too many digits entered. */
    public static final String ZZM9001E = "ZZM9001E";

    /** READ */
    public static final String FUNC_ID_BILL_GENERAL_INQUIRY = "NMAL6730T010";

    /** UPDATE */
    public static final String FUNC_ID_BILL_GENERAL_UPDATE = "NMAL6730T020";

    /** READ */
    public static final String FUNC_ID_FINANCIAL_INQUIRY = "NMAL6730T030";

    /** UPDATE */
    public static final String FUNC_ID_FINANCIAL_UPDATE = "NMAL6730T040";

    /** READ */
    public static final String FUNC_ID_INV_INQUIRY = "NMAL6730T050";

    /** UPDATE */
    public static final String FUNC_ID_INV_UPDATE = "NMAL6730T060";

    // Add Start 2018/08/01 QC#27222
    /** READ */
    public static final String FUNC_ID_TAXING_INQUIRY = "NMAL6730T070";

    /** UPDATE */
    public static final String FUNC_ID_TAXING_UPDATE = "NMAL6730T080";
    // Add End 2018/08/01 QC#27222

    // Add Start 2018/08/21 QC#27222
    /** READ */
    public static final String FUNC_ID_DI_CHECK_ITEM_INQUIRY = "NMAL6730T090";

    /** UPDATE */
    public static final String FUNC_ID_DI_CHECK_ITEM_UPDATE = "NMAL6730T100";
    // Add End 2018/08/21 QC#27222

    /** error message item: Credit Profile Information */
    public static final String MSG_CR_PRFL_INFO = "Credit Profile Information";

    // QC#6184
    /** Default Invoice Group Number */
    public static final String DEF_INV_GRP_NUM = "1";
    
    // QC#7781
    /** Char: Comma */
    public static final String CHAR_COMMA = ",";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";
}
