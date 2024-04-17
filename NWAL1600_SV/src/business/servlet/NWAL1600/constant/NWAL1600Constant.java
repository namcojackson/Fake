/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600.constant;

/**
 *<pre>
 * NWAL1600Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/30   Fujitsu         T.Murai         Update          S21_NA#8393
 *</pre>
 */
public class NWAL1600Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1600";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1600Scrn00";

    // --------------------------------
    // button
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** Close Button for popup */
    public static final String BTN_CMN_CLOSE = "CMN_Close";

    /** Add Row for Quote */
    public static final String BTN_ADD_QUOTE = "AddRowSlsCr";

    /** Delete Row for Quote */
    public static final String BTN_DEL_QUOTE = "DeleteRowSlsCr";

    /** OpenWin_SlsRep for Quote */
    public static final String BTN_OPEN_SLS_REP = "OpenWin_SlsRep";

    /** Add Row for Non Quote */
    public static final String BTN_ADD_NON_QUOTE = "AddRowNonQuote";

    /** Delete Row for Non Quote */
    public static final String BTN_DEL_NON_QUOTE = "DeleteRowNonQuote";

    /** OpenWin_SlsRep for Quote */
    public static final String BTN_OPEN_NUQUOTE = "OpenWin_NoQuoteSlsRep";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Required parameter : Mode is not entered. */
    public static final String NWAM0674E = "NWAM0674E";

    /** Required parameter is not entered. */
    public static final String NWAM0675E = "NWAM0675E";

    /**
     * The number of detail lines has reached the maxium. Cannot add
     * anymore.
     */
    public static final String NWAM0599E = "NWAM0599E";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** The total of percentage does not equals to 100%. */
    public static final String NWAM0660E = "NWAM0660E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** SSales Rep TOC Code" is required. */
    public static final String NWZM0688E = "NWZM0688E";

    /** Sales Rep TOC Code" is required. */
    public static final String NWZM1344E = "NWZM1344E";

    /** Please enter "1" or a larger value. */
    public static final String NWAM0370E = "NWAM0370E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Variable
    // --------------------------------
    /** Mode Type : Reference. */
    public static final String MODE_REFERENCE = "0";

    /** Mode Type : Edit All. */
    public static final String MODE_EDIT_ALL = "1";

    /** Mode Type : Edit Quote Only. */
    public static final String MODE_QUOTE_ONLY = "2";

    /** Request Type : New. */
    public static final String REQ_NEW = "1";

    /** Request Type : Modify. */
    public static final String REQ_MOD = "2";

    /** Request Type : Delete. */
    public static final String REQ_DEL = "3";

    /** Popup Screen Title */
    public static final String SCRN_TITLE = "Lookup Salesrep";

    /** TOC Table Name */
    public static final String TOC = "TOC";

    /** Person Code Column Name */
    public static final String PSN_CD_COLUMN = "PSN_CD";

    /** Person Code Label */
    public static final String PSN_CD_LB = "Employee ID";

    // 2016/05/12 S21_NA#7861 Add Start
    /** Person Code Column Name */
    public static final String PSN_NUM_COLUMN = "PSN_NUM";

    /** Person Code Label */
    public static final String PSN_NUM_LB = "Resource Number";
    // 2016/05/12 S21_NA#7861 Add End

    /** ROLE_TP_NM Column Name */
    public static final String ROLE_TP_NM_COLUMN = "LINE_BIZ_ROLE_TP_DESC_TXT";

    /** Role Type Name Label */
    public static final String ROLE_TP_NM_LB = "Role Type Name";

    /** SLSREP_CD Column Name */
    public static final String SLSREP_CD_COLUMN = "TOC_CD";

    /** Salesrep Code Label */
    public static final String SLSREP_CD_LB = "Salesrep Number";

    /** SLSREP_NM Column Name */
    public static final String SLSREP_NM_COLUMN = "TOC_NM";

    /** Salesrep Name Label */
    public static final String SLSREP_NM_LB = "Name";

    /** LINE_BIZ_TP_CD Column Name */
    public static final String LINE_BIZ_TP_CD_COLUMN = "LINE_BIZ_TP_CD";

    /** LINE_BIZ_TP_CD Label */
    public static final String LINE_BIZ_TP_CD_LB = "Line of Bussines";

    /** ORG_FUNC_ROLE_TP_NM Column Name */
    public static final String ORG_FUNC_ROLE_TP_NM_COLUMN = "ORG_FUNC_ROLE_TP_NM";

    /** ORG_FUNC_ROLE_TP_NM Label */
    public static final String ORG_FUNC_ROLE_TP_NM_LB = "Role";

    /** COA_BR_NM Column Name */
    public static final String COA_BR_NM_COLUMN = "COA_BR_NM";

    /** COA_BR_NM Label */
    public static final String COA_BR_NM_LB = "Branch";

    /** TOC_CD Column Name */
    public static final String TOC_CD_COLUMN = "TOC_CD";

    /** TOC_CD Label */
    public static final String TOC_CD_LB = "";

    /** Percent */
    public static final String PERCENT = "%"; // S21_NA#8393 Add

    /** Organization Layer Number */
    public static final String ORG_LYR_NUM = "ORG_LAYER_NUM";

    /** Table ID for Quote */
    public static final String TBL_ID_QUOTE = "A_Top";

    /** Table ID for Non Quote */
    public static final String TBL_ID_NONQUOTE = "B_Top";

    // --------------------------------
    // Suffix
    // --------------------------------

    /** In/Out Param Suffix "O" */
    public static final String INOUT_SFX = "O";

    /** Quote Suffix "A" */
    public static final String QUOTE_SFX = "A";

    /** Non Quote Suffix "B" */
    public static final String NONQUOTE_SFX = "B";

    /** Delete Suffix "C" */
    public static final String DEL_SFX = "C";
}
