/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2710.constant;

/**
 *<pre>
 * NMAL2710Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2016/06/29   Fujitsu         W.Honda         Update          QC#11091
 * 2016/06/29   Fujitsu         W.Honda         Update          QC#11319
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 *</pre>
 */
public class NMAL2710Constant {

    /** Replaced target character : % */
    public static final String PERCENT_REPLACE_TARGET_CHAR = "%";

    /** Replaced target character : - */
    public static final String HYPHEN_REPLACE_TARGET_CHAR = "-";

    /** Replace character :  (Blank) */
    public static final String REPLACE_CHAR_BLANK = "";

    /** Postal Code Length : 9  */
    public static final int POSTAL_LENGTH = 9;

    /** From Postal Code Padding String : 0  */
    public static final String FROM_POST_PAD_STR = "0";

    /** Thru Postal Code Padding String : 0  */
    public static final String THRU_POST_PAD_STR = "9";

    /** Fetch Size : 1000  */
    public static final int FETCH_SIZE = 1000;

    /** Date Length YYYYMMDD : 8  */
    public static final int YYYYMMDD_LENGTH = 8;

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** No search result were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** [@] cannot be entered [@]*/
    public static final String NMAM0086E = "NMAM0086E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    // QC#11940 2016/07/20 Mod start
    // // QC#11091 2016/06/29 Add start
    // /** The value for [@] must be within [@]. */
    // public static final String NMAM8082E = "NMAM8082E";
    // // QC#11091 2016/06/29 Add end
    /** Move To Territory is not active Territory.*/
    public static final String NMAM8634E = "NMAM8634E";
    // QC#11940 2016/07/20 Mod end

    /** [@] process ended normally. */
    public static final String NMAM8182I = "NMAM8182I";

    /** Could not get the "@". */
    public static final String NMAM8433E = "NMAM8433E";

    /** [@] can not be obtained. */
    public static final String NMAM8489E = "NMAM8489E";

    /** [@] is not Active. */
    public static final String NMAM8497E = "NMAM8497E";

    /** Too many search results. Please narrow your search criteria and retry. */
    public static final String NMAM8518I = "NMAM8518I";

    // QC#11319 2016/07/05 Add start
    /** [Old territory rule effective date thru] cannot be updated 
     *  because of [Move Effective Date From is before old territory rule's effective date From]. */
    public static final String NMAM8600E = "NMAM8600E";
    // QC#11319 2016/07/05 Add end

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    // 2017/11/16 CSA-QC#20597 Add Start
    /** Message Kind Warning */
    public static final String MESSAGE_KIND_WARN = "W";
    // 2017/11/16 CSA-QC#20597 Add End

    /** CSV File Name Upload */
    public static final String CSV_FILE_NAME = "Postal Rule Move";

    /** CSV File Name Upload */
    public static final String CSV = ".csv";

    /** CSV File Name Upload */
    public static final int CSV_HEADER_NUM = 8;

    /** Move Postal Code To  */
    public static final String MOVE_POST_CD_TO = "Move Postal Code To";

    /** Constant Name of CSV Header Name : Operand  */
    public static final String CSV_HEADER_CONST_NM_OPRD = "NMAL2710_CSV_HDR_OPRD_TP";

    /** Constant Name of CSV Header Name : From Value Text Postal  */
    public static final String CSV_HEADER_CONST_NM_POST_FROM = "NMAL2710_CSV_HDR_POST_FROM";

    /** Constant Name of CSV Header Name : Thru Value Text Postal  */
    public static final String CSV_HEADER_CONST_NM_POST_TO = "NMAL2710_CSV_HDR_POST_TO";

    /** Constant Name of CSV Header Name : Old Territory Name  */
    public static final String CSV_HEADER_CONST_NM_OLD_ORG_NM = "NMAL2710_CSV_HDR_OLD_ORG_NM";

    /** Constant Name of CSV Header Name : New Territory Name  */
    public static final String CSV_HEADER_CONST_NM_NEW_ORG_NM = "NMAL2710_CSV_HDR_NEW_ORG_NM";

    /** Constant Name of CSV Header Name : Move Effective From Date  */
    public static final String CSV_HEADER_CONST_NM_MOVE_EFF_FROM = "NMAL2710_CSV_HDR_MOVE_EFF_FROM";

    /** Constant Name of CSV Header Name : Move Effective Thru Date  */
    public static final String CSV_HEADER_CONST_NM_MOVE_EFF_TO = "NMAL2710_CSV_HDR_MOVE_EFF_TO";

    /** Constant Name of CSV Header Name : Mass Update Reason  */
    public static final String CSV_HEADER_CONST_NM_CMNT = "NMAL2710_CSV_HDR_CMNT";

    /** Check Box Name : xxChkBox_A1 */
    public static final String CHK_BOX_A1 = "xxChkBox_A1";

    /** HIGH_VAL_DT : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    /** Selected Territory */
    public static final String SELCETED_TRTY = "Selected Territory";

    /** Selected Territory Rule */
    public static final String SELCETED_RULE = "Selected Territory Rule";

    /** Only Record Count : 1 */
    public static final int ONLY_REC_CNT = 1;

    /** Selected Territory */
    public static final String TRTY_LOGIC_TYPE = "Territory Logic Type";

    /** Selected Territory Rule */
    public static final String BETWEEN_SAME = "Territory Logic Type should be same between selected territory and move to territory";

    // QC#11091 2016/06/29 Mod start
//    /** Effective Date Of Territory Rule */
//    public static final String TRTY_RULE_EFF = "Effective Date Of Territory Rule";
//
//    /** Out of Territory Effective Date */
//    public static final String OUT_OF_EFF = "Out of Territory Effective Date";
    /** Effective Date Of Territory Rule */
    public static final String MOVE_EFF = "Move effective date";

    /** Out of Territory Effective Date */
    public static final String MOVE_POST_EFF = "Move postal to territory's effective date";
    // QC#11091 2016/06/29 Mod end

    // QC#11370 2016/07/04 Add start
    /** Effective Date Of Territory Rule */
    public static final String MOVE_EFF_FROM = "Move effective from date";

    /** Out of Territory Effective Date */
    public static final String MOVE_FROM_POST_EFF = "Move postal from territory's effective date";
    // QC#11091 2016/06/29 Mod end

}
