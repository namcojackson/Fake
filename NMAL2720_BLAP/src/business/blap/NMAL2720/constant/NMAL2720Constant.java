/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2720.constant;

/**
 *<pre>
 * NMAL2720Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/09/28   Fujitsu         Mz.Takahashi    Update          QC#12175
 * 2016/09/28   Fujitsu         Mz.Takahashi    Update          QC#15492
 *</pre>
 */
public class NMAL2720Constant {

    /** Fetch Size : 1000  */
    public static final int FETCH_SIZE = 1000;

    /** CSV File Name Upload */
    public static final String CSV_FILE_NAME = "Move Organization";

    /** CSV File Name Upload */
    public static final String CSV = ".csv";

    /** CSV File Name Upload Number */
    public static final int CSV_HEADER_NUM = 6;

    /** HIGH Date Value */
    public static final String HIGH_DT = "99991231";

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

    /** The entered [@] does not exist. */
    public static final String NMAM0009E = "NMAM0009E";

    /** [@] is not registered. */
    public static final String NMAM0011E = "NMAM0011E";

    /** [@] cannot be updated because of [@]. */
    public static final String NMAM0073E = "NMAM0073E";

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** Could not get the "@". */
    public static final String NMAM8433E = "NMAM8433E";

    /** [@] is existed in master. */
    public static final String NMAM8440E = "NMAM8440E";

    /** [@] can not be obtained. */
    public static final String NMAM8489E = "NMAM8489E";

    /** Revenue information is not effective. */
    public static final String NMAM8594E = "NMAM8594E";

    /** Please input Effective Date From/To within Resource Effective. */
    public static final String NMAM8595E = "NMAM8595E";

    // 2016/11/01 CSA-QC#15492 Add Start
    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";
    // 2016/11/01 CSA-QC#15492 Add End

    /** Constant Name of CSV Header Name : Current Org Name  */
    public static final String CSV_HEADER_CONST_NM_CUR_ORG_NM = "NMAL2720_CSV_HDR_CUR_ORG_NM";

    /** Constant Name of CSV Header Name : Current Resource#  */
    public static final String CSV_HEADER_CONST_NM_CUR_PSN_NUM = "NMAL2720_CSV_HDR_CUR_PSN_NUM";

    /** Constant Name of CSV Header Name : Move Resource#  */
    public static final String CSV_HEADER_CONST_NM_MOVE_PSN_NUM = "NMAL2720_CSV_HDR_MOVE_PSN_NUM";

    /** Constant Name of CSV Header Name : Move Effective From Date  */
    public static final String CSV_HEADER_CONST_NM_MOVE_EFF_FROM = "NMAL2720_CSV_HDR_MOVE_EFF_FROM";

    /** Constant Name of CSV Header Name : Move Effective Thru Date  */
    public static final String CSV_HEADER_CONST_NM_MOVE_EFF_TO = "NMAL2720_CSV_HDR_MOVE_EFF_TO";

    /** Constant Name of CSV Header Name : Mass Update Reason  */
    public static final String CSV_HEADER_CONST_NM_CMNT = "NMAL2720_CSV_HDR_CMNT";
}
