/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0690.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1659
 * 2015/12/22   Hitachi         T.Tsuchida      Update          QC#2341
 * 2015/12/23   Hitachi         T.Tsuchida      Update          QC#2383
 * 2016/04/08   Hitachi         T.Tomita        Update          QC#6729
 * 2016/05/24   Hitachi         K.Kasai         Update          QC#447
 * 2017/02/13   Hitachi         K.Kitachi       Update          QC#17410
 * 2017/10/02   Hitachi         M.Kidokoro      Update          QC#18290
 * 2018/05/23   Hitachi         K.Kitachi       Update          QC#26210
 * 2018/05/18   Hitachi         K.Kojima        Update          QC#26187
 * 2018/06/15   Hitachi         K.Kojima        Update          QC#26702
 * 2024/03/29   Hitachi         K.Ogasawara     Update          QC#63550
 * 2024/04/02   Hitachi         T.Nagae         Update          QC#63552
 *</pre>
 */
public final class NSAL0690Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0690";

    /**
     * SEARCH_LIMIT_CNT
     */
    public static final int SEARCH_LIMIT_CNT = 200;

    /**
     * DATE_FORMAT
     */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** No search results found. */
    public static final String NSAM0013E = "NSAM0013E";

    /** Please select at least 1 checkbox. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Contract detail cannnot be updated becuase of the contract detail status. */
    public static final String NSAM0392E = "NSAM0392E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** [@] is earlier than [@]. */
    public static final String NSAM0284E = "NSAM0284E";

    // START 2018/05/18 K.Kojima [QC#26187,ADD]
    /** [@] is later than [@]. */
    public static final String NSAM0285E = "NSAM0285E";
    // END 2018/05/18 K.Kojima [QC#26187,ADD]

    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /** The process has been successfully completed.  */
    public static final String NSAM0200I = "NSAM0200I";

    // add start 2016/05/24 CSA Defect#447
    /** This Serial# {@} is the end of life for No-Contract.  */
    public static final String NSAM0480E = "NSAM0480E";
    // add end 2016/05/24 CSA Defect#447

    // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
    /**
     * The number of contract renewal times has reached the upper
     * limit. Contract cannot be renewed.
     */
    public static final String NSZM0844E = "NSZM0844E";

    // START 2018/05/22 K.Kitachi [QC#26210, DEL]
//    /** Expiration date exceeds "New Period End". */
//    public static final String NSAM0706E = "NSAM0706E";
    // END 2018/05/22 K.Kitachi [QC#26210, DEL]
    // END 2017/10/02 M.Kidokoro [QC#18290, ADD]

    /** Detail Line : Base */
    public static final String DETAIL_BASE_NM = "Base";

    /** Detail Line : Overage */
    public static final String DETAIL_OVERAGE_NM = "Overage";

    /** DS_CONTR_BASE_USG_NM : BASE */
    public static final String BASE = "BASE";

    /** DS_CONTR_BASE_USG_NM : OVERAGE */
    public static final String OVERAGE = "OVERAGE";

    /** DS_CONTR_MACH_LVL_NUM : MachLvlNum1 */
    public static final String LVL_NUM_1 = "1";

    /** DS_CONTR_MACH_LVL_NUM : MachLvlNum2 */
    public static final String LVL_NUM_2 = "2";

    /** DS_CONTR_MACH_LVL_NUM : MachLvlNum3 */
    public static final String LVL_NUM_3 = "3";

    /** Return Message : Success */
    public static final String RTRN_MSG_SUCCESS = "Success";

    /** Return Message : Failed to renew Contract */
    public static final String RTRN_MSG_FAILED = "Failed to renew Contract";

    // add start 2016/04/08 CSA Defect#6729
    /** BigDecimal Value 100 */
    public static final BigDecimal BIGDECIMAL_100 = new BigDecimal(100);
    // add end 2016/04/08 CSA Defect#6729

    // START 2017/02/13 K.Kitachi [QC#17410, ADD]
    /** Constant name */
    public static final String CFS_DLR_CD = "CFS_DLR_CD";

    /** Constant name */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** CFS Contract Price Upliftment */
    public static final String MSG_INFO_CFS_CONTR_PRC_UPLFT = "CFS Contract Price Upliftment";

    /** Format : DS Contract Modification Text */
    public static final String FORMAT_DS_CONTR_MOD_TXT = "yyyyMMddHHmmss";

    /** Suffix : Fleet Serial Number */
    public static final String SFX_FLT_SER_NUM = "FLT_";
    // END 2017/02/13 K.Kitachi [QC#17410, ADD]

    // START 2018/06/15 K.Kojima [QC#26702,DEL]
    // // START 2018/05/22 K.Kitachi [QC#26210, ADD]
    // /** Failed to update "@". */
    // public static final String NSAM0031E = "NSAM0031E";
    // // END 2018/05/22 K.Kitachi [QC#26210, ADD]
    // // END 2018/06/15 K.Kojima [QC#26702,DEL]

    // START 2024/03/29 K.Ogasawara [QC#63550, ADD]
    /** Duration */
    public static final String RENEW_NOW_DURATION = "RENEW_NOW_DURATION";

    /** Period */
    public static final String RENEW_NOW_PERIOD = "RENEW_NOW_PERIOD";
    // END 2024/03/29 K.Ogasawara [QC#63550, ADD]

    // START 2024/04/02 T.Nagae [QC#63552, ADD]
    /** Report ID : NSAF0030 */
    public static final String RPT_ID_NSAF0030 = "NSAF0030";

    /** Report ID : NSAF0040 */
    public static final String RPT_ID_NSAF0040 = "NSAF0040";

    /** Report ID : NSAF0050 */
    public static final String RPT_ID_NSAF0050 = "NSAF0050";

    /** Report ID : NSAF0060 */
    public static final String RPT_ID_NSAF0060 = "NSAF0060";

    /** OTPT_OP_CD : Online */
    public static final String OTPT_OP_CD = "Online";

    /** NSZC100001 bizAppId Renewal : NSAB0030 */
    public static final String NSZC100001_BIZ_APP_ID_RNW = "NSAB0030";
    // END 2024/04/02 T.Nagae [QC#63552, ADD]
}
