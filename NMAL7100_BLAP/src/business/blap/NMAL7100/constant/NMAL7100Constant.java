/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7100.constant;

/**
 *<pre>
 * NMAL7100Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/15   Fujitsu         M.Hara          Create          CSA
 * 2016/11/10   Fujitsu         W.Honda         Update          QC#15842
 * 2019/03/13   Fujitsu         S.Kosaka        Update          QC#30725
*</pre>
 */
public class NMAL7100Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7100";

    /** TAB : Marketing Program Audidence */
    public static final String TAB_MKT_AUDC = "MktAud";

    /** TAB : CanNotBeUsed */
    public static final String TAB_CAN_NOT_BE_USED = "CanNotBeUsed";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** [@] should be unique. */
    public static final String NMAM8296E = "NMAM8296E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** Effective date is out of range. Please check the Effective date. */
    public static final String NMAM8213E = "NMAM8213E";

    /** '@ must have at least one row.  Please enter data. */
    public static final String NMAM8214E = "NMAM8214E";

    /** Cannot enter the other audience in case of Public. */
    public static final String NMAM8215E = "NMAM8215E";

    /** Please entry 8 digits merchandise code. */
    public static final String NMAM8216E = "NMAM8216E";

    /** The entered [@] does not exist in [@].*/
    public static final String NMAM0163E = "NMAM0163E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** There are duplicate of matched transactions. */
    public static final String NMAM0265E = "NMAM0265E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** The Upload File is empty. Please confirm the content of the Upload file. */
    public static final String NMAM8193E = "NMAM8193E";

    // START QC#15842 11/10/2016 ADD
    /** Detail requires at least one line.  Please enter. */
    public static final String NMAM8190E = "NMAM8190E";
    // END QC#15842 11/10/2016 ADD

    /** Error has occurred while uploading. Please verify the upload file format. (@) */
    public static final String NMAM8191E = "NMAM8191E";

    /** There are too many search results, there is data that cannot be uploaded. */
    public static final String NMAM8197E = "NMAM8197E";

    /** Please input @. */
    public static final String NMAM8368E = "NMAM8368E";

    // --------------------------------
    // Others
    // --------------------------------

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** CSV File Name Download */
    public static final String CSV_DOWNLOAD_FILE_NAME = "NMAL7100";

    /** CSV Header For Download */
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {
                                                "Marketing Program Code",
                                                "Promotion Qualifier",
                                                "Qualifier Value",
                                                "Promotion Item Code",
                                                "Promotion Value",
                                                "Date From",
                                                "Date To"};

    /** CSV Header For Detail Download */
    public static final String[] CSV_DOWNLOAD_DETAIL_HEADER = new String[] {
        "Marketing Program Code",
        "Promotion Qualifier",
        "Qualifier Value",
        "Promotion Item Code",
        "Item Description",
        "Promotion Value",
        "Date From",
        "Date To",
        "Created By",
        "Created Date",
        "Last Update By",
        "Last Update Date"};

    /** String Date High Value */
    public static final String HIGH_VAL_DT = "99991231";

    /** PRC_MKT_PRMO Oracle Sequence */
    public static final String PRC_MKT_PRMO_SQ = "PRC_MKT_PRMO_SQ";

    /** PRC_MKT_PRMO_AUDC_CTRL_SQ Oracle Sequence */
    public static final String PRC_MKT_PRMO_AUDC_CTRL_SQ = "PRC_MKT_PRMO_AUDC_CTRL_SQ";

    /** PRC_MKT_PRMO_EXCL_SQ Oracle Sequence */
    public static final String PRC_MKT_PRMO_EXCL_SQ = "PRC_MKT_PRMO_EXCL_SQ";

    /** Check Box MX */
    public static final String CHK_MX = "xxChkBox_MX";

    /** Check Box CX */
    public static final String CHK_CX = "xxChkBox_CX";

    /** Check Box DA */
    public static final String CHK_DA = "xxChkBox_DA";

    /** same promotion value */
    public static final String SAME_PRMO_ITEM = "same Promotion Item Code";

    /** same promotion value */
    public static final String SAME_EFF_DATE = "same Effctive Date";

    /** same promotion value */
    public static final String SAME_PRMO_VAL = "same Promotion Value";

    /** Date format length */
    public static final int YYYYMMDD_LENGTH = 8;

    // 2019/03/13 QC#30725 Add Start
    /** Slash */
    public static final String SLASH = "/";

    /** Slash */
    public static final String DATE_FORMAT_PADDING_ZERO = "0";
    // 2019/03/13 QC#30725 Add End
}
