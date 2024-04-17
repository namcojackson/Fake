/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7190.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NMAL7190Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2018/08/01   Fujitsu         M.Ishii         Update          QC#26297
 * 2018/12/05   Fujitsu         T.Noguchi       Update          QC#29324
 * 2018/12/18   Fujitsu         T.Noguchi       Update          QC#29661
 * 2019/03/13   Fujitsu         S.Kosaka        Update          QC#30725
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190Constant {

    /**  Check Box Name */
    public static final int PRC_GRP_OP_COUNT = 4;

    /**  Date format length */
    public static final int YYYYMMDD_LENGTH = 8;

    /**  Convert Type : Code To Name */
    public static final int CONVTYPE_CODETONAME = 0;

    /**  Upload error code : reading error */
    public static final int UPLOAD_READ_ERROR = 1000;

    /**  Upload error code : format error */
    public static final int UPLOAD_FORMAT_ERROR = 2000;

    /**  Convert Type : Name To Code */
    public static final int CONVTYPE_NAMETOCODE = 1;

    /**  Check Box Name */
    public static final String XX_CHKBOX_NAME = "xxChkBox_A1";

    /**  Logic Mode : Search */
    public static final String LOGIC_MODE_SEARCH = "02";

    /**  Logic Mode : Submit */
    public static final String LOGIC_MODE_SUBMIT = "06";

    /**  Max value of Precedence# : 999 */
    public static final BigDecimal MAX_PRCD_NUM = new BigDecimal("999");

    // 2018/12/05 QC#29324 Add Start
    /** String Date High Value */
    public static final String HIGH_VAL_TM = "999999999";

    /** String COMMA */
    public static final String COMMA = ",";
    // 2018/12/05 QC#29324 Add End

    // 2019/03/13 QC#30725 Add Start
    /** Slash */
    public static final String SLASH = "/";

    /** Slash */
    public static final String DATE_FORMAT_PADDING_ZERO = "0";
    // 2019/03/13 QC#30725 Add End

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /**  The entered [@] does not exist.  */
    public static final String NMAM0009E = "NMAM0009E";

    /**  The value for [@] must be over [@].  */
    public static final String NMAM0042E = "NMAM0042E";

    /**  The value for [@] must be smaller than [@].  */
    public static final String NMAM0043E = "NMAM0043E";

    /**  The format of [@] is incorrect.  */
    public static final String NMAM0052E = "NMAM0052E";

    /**  Invalid combination.  @ & @  */
    public static final String NMAM0070E = "NMAM0070E";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /**  The entered [@] does not exist in [@].  */
    public static final String NMAM0163E = "NMAM0163E";

    /**  It failed to register [@].  */
    public static final String NMAM0176E = "NMAM0176E";

    /**  It failed to update [@].  */
    public static final String NMAM0177E = "NMAM0177E";

    /**  @ cannot be added because it is exceeding the maximum number of row [@]  */
    public static final String NMAM8187E = "NMAM8187E";

    /** Error has occurred while uploading. Please verify the upload file format. (@) */
    public static final String NMAM8191E = "NMAM8191E";

    /** The Upload File is empty. Please confirm the content of the Upload file. */
    public static final String NMAM8193E = "NMAM8193E";

    /** Please entry 8 digits merchandise code. */
    public static final String NMAM8216E = "NMAM8216E";

    /**  [@] should be unique.  */
    public static final String NMAM8296E = "NMAM8296E";

    // 2018/08/01 Add Start QC#26297
    /**  The effective periods are overlapped.   Please correct it. */
    public static final String NMAM8113E = "NMAM8113E";
    // 2018/08/01 Add End QC#26297

    // 2018/12/18 QC#29661 Add Start
    /**  Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";
    // 2018/12/18 QC#29661 Add End

    // 2023/04/20 QC#61200 Add Start
    /**  Existing line can’t be deleted. Use End-dating instead. */
    public static final String NMAM8723E = "NMAM8723E";
    // 2023/04/20 QC#61200 Add End

    //--------------------------------
    // String For Message
    //--------------------------------

    /** Label Name : Effective Date From  */
    public static final String HDR_EFF_FROM_DT = "Effective Date From";

    /** Label Name : Effective Date To  */
    public static final String HDR_EFF_THRU_DT = "Effective Date To";

    /**  Label Name : Target Context */
    public static final String PRC_GRP_TRGT_TP_CD = "Target Context";

    /**  Label Name : Target Attribute Name */
    public static final String PRC_GRP_TRGT_ATTRB_CD = "Target Attribute NameAttribute Name";

    /**  Label Name : Operator */
    public static final String PRC_GRP_OP_CD = "Operator";

    /**  Label Name : Target From */
    public static final String PRC_GRP_FROM_TXT = "Target From";

    /**  Label Name : Date From */
    public static final String DTL_EFF_FROM_DT = "Date From";

    /**  Operator = Between */
    public static final String OPEATOR_BETWEEN = "Operator = BETWEEN";

    /**  When Operator is Between, Target To */
    public static final String TARGET_BLANK = "Target To = Blank";

    /**  Combination of start date and End date */
    public static final String COMBI_START_END = "Combination of start date and End date";

    // 2018/08/01 Add Start QC#26297
    /**  Uploaded data duplication */
    public static final String UPLD_DATA_DUP = "Uploaded data duplication";
    // 2018/08/01 Add End QC#26297

    /** Price Group Detail  */
    public static final String PRICE_GROUP_DETAIL = "Price Group Detail";

    /** effFromDt_A1  */
    public static final String DTL_EFF_FROM_DT_A1 = "effFromDt_A1";

    /** effThruDt_A1  */
    public static final String DTL_EFF_THRU_DT_A1 = "effThruDt_A1";

    // 2023/04/20 QC#61200 Add Start
    /** Date From */
    public static final String DATE_FROM = "Date From";

    /** Date To */
    public static final String DATE_TO = "Date To";
    // 2023/04/20 QC#61200 Add End

    /** xxOrigQty  */
    public static final String XX_ORIG_QTY_A1 = "xxOrigQty_A1";

    /** Sort : ASC  */
    public static final String SORT_BY_ASC = "ASC";

    /** Sort : DESC  */
    public static final String SORT_BY_DESC = "DESC";

    /** PRC_GRP  */
    public static final String PRICE_GROUP_TABLE_NAME = "PRC_GRP";

    /** PRC_GRP  */
    public static final String PRICE_GROUP_DETAIL_TABLE_NAME = "PRC_GRP_DTL";

    /** Number Pattern  */
    public static final String NUMBER_PATTERN = "\\d+";

    /** Price Group CSV ID  */
    public static final String PRICE_GROUP_CSV_ID = "NMA7190001";

    /** Check Target Name List */
    public static final String[] CHK_ATTRB_NM_LIST = new String[] {
                "prcGrpTrgtTpCd_A1",
                "prcGrpTrgtAttrbCd_A1",
                "prcGrpOpCd_A1",
                "prcGrpFromTxt_A1",
                "prcGrpThruTxt_A1",
                "effFromDt_A1",
                "effThruDt_A1"
    };

    /** Check Target Name List */
    public static final String[] CHK_ATTRB_NM_LIST_UPLOAD = new String[] {
                "prcGrpTrgtTpCd_A1",
                "prcGrpTrgtAttrbCd_A1",
                "prcGrpOpCd_A1",
                "prcGrpFromTxt_A1",
                "prcGrpThruTxt_A1",
                "prcGrpPrcdNum_A1",
                "effFromDt_A1"
    };

    /** Check Target Name List */
    public static final String[] GRP_ATTRB_NM_LIST = new String[] {
                "prcGrpTrgtTpCd_A1",
                "prcGrpTrgtAttrbCd_A1",
                "prcGrpOpCd_A1",
                "prcGrpFromTxt_A1",
                "prcGrpThruTxt_A1"
    };

    /** File extension */
    public static final String CSV_EXTENSION = ".csv";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NMAL7190 Price Group Setup";

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {
        "Target Context"
        , "Target Attribute Name"
        , "Operator"
        , "Target From"
        // 2023/04/20 QC#61200 Add Start
        , "Description"
        // 2023/04/20 QC#61200 Add End
        , "Target To"
        , "Precedence#"
        , "Date From"
        , "Date To"};
}
