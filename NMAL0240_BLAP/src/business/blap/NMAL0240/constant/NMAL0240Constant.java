/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0240.constant;

/**
 *<pre>
 * NMAL0240Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 * 2016/06/16   SRAA            K.Aratani       Update          QC#10313
 *</pre>
 */
public class NMAL0240Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    // QC#9451
    /**
     * CUSA View can not connect. Please confirm the connection to
     * CUSA DB Link.
     */
    public static final String NMAM8560E = "NMAM8560E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * [@] exists in CUSA components and must be registered to CSA
     * Item Master as Active. Please verify CSA Item master status .
     */
    public static final String NMAM8390E = "NMAM8390E";

    /** This components are already imported from CUSA to CSA. */
    public static final String NMAM8402E = "NMAM8402E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * This SET item code exists as CUSA SET but set up is not
     * completed by CUSA . Please confirm item status to CUSA
     * Marketing dept .
     */
    public static final String NMAM8493E = "NMAM8493E";

    // QC#10313
    /**
     * Displayed composition information is comes from CUSA Item
     * Master ,when you need to register it , please click submit.
     */
    public static final String NMAM8598I = "NMAM8598I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** It failed to delete [@]. */
    public static final String NMAM0208E = "NMAM0208E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** You cannot set 8 length Kit component. */
    public static final String NMAM8283E = "NMAM8283E";

    /** Please register Merchandise for CUSA set. */
    public static final String NMAM8284E = "NMAM8284E";

    /** It exceeds the maximum hierarchy layers allowed. */
    public static final String NMAM0137E = "NMAM0137E";

    /** CMPSN */
    public static final String CMPSN = "CMPSN";

    /** CMPSN_CHNG_REQ */
    public static final String CMPSN_CHNG_REQ = "CMPSN_CHNG_REQ";

    /** MDSE_ITEM_STS */
    public static final String MDSE_ITEM_STS = "MDSE_ITEM_STS";

    /** MDSE */
    public static final String MDSE = "MDSE";

    /** Component */
    public static final String COMPONENT = "Component";

    /** BOM Item # */
    public static final String BOM_ITEM = "BOM Item#";

    /** Active */
    public static final String ACTIVE = "Active";

    /** Inactive */
    public static final String INACTIVE = "Inactive";

    /** UPDATE */
    public static final String UPDATE = "UPDATE";

    // --------------------------------
    // CSV
    // --------------------------------
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NMAL0240_BOMMain";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";

    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;

    /** CR LF */
    public static final String CRLF = "\r\n";

    /** Comma */
    public static final String COMMA = ",";

    /** BOM */
    public static final String BOM = "BOM";

    /** CSV: BOM Item# */
    public static final String CSV_BOM_ITEM = BOM_ITEM;

    /** CSV: Component */
    public static final String CSV_CMP = COMPONENT;

    /** CSV: Description */
    public static final String CSV_DESC = "Description";

    /** CSV: BOM Type */
    public static final String CSV_BOM_TP = "BOM Type";

    /** CSV: Merch Type */
    public static final String CSV_MERCH_TP = "Merch Type";

    /** CSV: Prod Code */
    public static final String CSV_PROD_CD = "Prod Code";

    /** CSV Header: Cusa Set */
    public static final String CSV_CUSA_SET = "CUSA Set";

    /** CSV: Active */
    public static final String CSV_ACTIVE = ACTIVE;

    /** CSV: Revision */
    public static final String CSV_REV = "Revision";

    /** CSV: Revision Comment */
    public static final String CSV_REV_CMNT = "Revision Comment";

    /** CSV: Effective Start */
    public static final String CSV_EFF_FROM = "Effective Start";

    /** CSV: Effective End */
    public static final String CSV_EFF_THRU = "Effective End";

    /** CSV: Sequence1 */
    public static final String CSV_SEQ1 = "Sequence1";

    /** CSV: Sequence2 */
    public static final String CSV_SEQ2 = "Sequence2";

    /** CSV: Quantity */
    public static final String CSV_QTY = "Quantity";

    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;

    // --------------------------------
    // Other
    // --------------------------------
    /** CUSA MDSE Const Name */
    public static final String CUSA_MDSE = "CUSA_MDSE";

    /** CUSA CMPSN Const Name */
    public static final String CUSA_CMPSN = "CUSA_CMPSN";

    /** CUSA ALL_MDSE_V Const Name */
    public static final String CSA_ALL_MDSE_V = "CSA_ALL_MDSE_V";

    /** xxChkBox_A1 */
    public static final String CHECKBOX_A = "xxChkBox_A1";

    /** xxChkBox_B1 */
    public static final String CHECKBOX_B = "xxChkBox_B1";

    /** MAIN_MACH */
    public static final String MAIN_MACH = "MAIN_MACH";

    /** CUSA Global Company Code */
    public static final String CUSA_GLBL_CMPY_CD = "ABR";

    /** Order Take Merchandise length */
    public static final int ORD_TAKE_LEN = 8;

    /** Referenced Entity Item */
    public static final String REF_ENTITY_ITEM = "ITEM";

    /** MDSE Code key Name */
    public static final String MODE_CD_KEY_NAME = "MDSE_CD";

}
