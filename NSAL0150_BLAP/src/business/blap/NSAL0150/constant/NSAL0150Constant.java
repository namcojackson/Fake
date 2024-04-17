/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0150.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/11/26   Hitachi         K.Kasai         Update          Unit Test #71
 * 2015/12/16   Hitachi         K.Kasai         Update          QC#2072
 * 2016/03/31   Hitachi         T.Tomita        Update          QC#4394
 * 2016/10/01   Hitachi         K.Kishimoto     Update          QC#12271
 * 2016/10/25   Hitachi         Y.Takeno        Update          QC#15525
 * 2016/11/24   Hitachi         Y.Takeno        Update          QC#14992
 * 2016/12/07   Hitachi         Y.Takeno        Update          QC#15200
 * 2017/09/07   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/09/15   Hitachi         K.Kojima        Update          QC#21125
 * 2017/12/13   Hitachi         M.Kidokoro      Update          QC#21681
 * 2017/12/20   Hitachi         M.Kidokoro      Update          QC#21127
 * 2018/06/11   Hitachi         U.Kim           Update          QC#22480
 * 2018/06/25   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/06   Hitachi         T.Tomita        Update          QC#26972
 * 2018/07/09   Hitachi         A.Kohinata      Update          QC#26923
 * 2018/07/17   Hitachi         K.Kitachi       Update          QC#26764
 * 2018/07/20   Hitachi         A.Kohinata      Update          QC#26974
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31089
 * 2019/09/05   Hitachi         K.Fujimoto      Update          QC#53294
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2022/08/12   Hitachi         H.Watanabe      Update          QC#60046
 * 2023/04/12   CITS            R.Avelino       Update          QC#61357
 * 2024/03/08   Hitachi         K.Watanabe      Update          QC#63539
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 * 2024/04/08   Hitachi         S.Moriai        Update          QC#63540
 *</pre>
 */
public class NSAL0150Constant {

    /**
     * Business ID
     */
    public static final String BIZ_ID = "NSAL0150";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * 
     */
    public static final String NSZM0006E = "NSZM0006E";

    /**
     * [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Failed to update @ table.[@]
     */
    public static final String NSAM0001E = "NSAM0001E";

    /**
     * @ is future date.
     */
    public static final String NSAM0075E = "NSAM0075E";

    /**
     * The value for [@] must be bigger than [@].
     */
    public static final String NSAM0076E = "NSAM0076E";

    /**
     * The value for [@] must be smaller than [@].
     */
    public static final String NSAM0077E = "NSAM0077E";

    /**
     * @ exceeded Max Monthly Copy Volume Count defined by the model.
     */
    public static final String NSAM0078W = "NSAM0078W";

    /**
     * @ has been already registered.
     */
    public static final String NSAM0079E = "NSAM0079E";

    /**
     * There are too much data updated. Please submit once.
     */
    public static final String NSAM0080E = "NSAM0080E";

    /**
     * The data cannot be deleted because Meter Reading Type is not
     * Final.
     */
    public static final String NSAM0289E = "NSAM0289E";

    /**
     * This data cannot be deleted because the Meter is already
     * invoiced.
     */
    public static final String NSAM0290E = "NSAM0290E";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * If @ is not input, can not input @.
     */
    public static final String NSAM0147E = "NSAM0147E";

    /**
     * The current meter reading should be within +/- 75% of the
     * average volume for this meter.
     */
    public static final String NSZM0542E = "NSZM0542E";

    // ADD START 2015/11/26 K.Kasai [Unit Test #71]
    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";
    // ADD END 2015/11/26 K.Kasai [Unit Test #71]

    // START 2019/04/09 K.Kitachi [QC#31089, ADD]
    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";
    // END 2019/04/09 K.Kitachi [QC#31089, ADD]

    // Add Start 2016/10/03 <QC#12271>
    /**
     * @ does not exist in @.
     */
    public static final String NSAM0063E = "NSAM0063E";
    // Add End  2016/10/03 <QC#12271>

    // START 2016/10/25 [QC#15525, ADD]
    /** You cannot go to "Supply Order" screen, since the meter is not entered within @ days. */
    public static final String NSAM0615E = "NSAM0615E";
    // END 2016/10/25 [QC#15525, ADD]

    // START 2017/09/15 K.Kojima [QC#21125,ADD]
    /** The billing meter read can not be Invalid, because there are some future billable meter reads. */
    public static final String NSAM0699E = "NSAM0699E";
    // END 2017/09/15 K.Kojima [QC#21125,ADD]

    // START 2017/12/13 M.Kidokoro [QC#21681, ADD]
    /** You can not register a new meter read within the invoiced period. */
    public static final String NSAM0709E = "NSAM0709E";
    // END 2017/12/13 M.Kidokoro [QC#21681, ADD]

    // START 2017/12/20 M.Kidokoro [QC#21127, ADD]
    /**
     * Current meter reading can not be higher than the next actual
     * read. Please enter lower reads than the next actual read.
     */
    public static final String NSZM1312E = "NSZM1312E";
    // END 2017/12/20 M.Kidokoro [QC#21127, ADD]

    // START 2018/06/11 U.Kim [QC#22480, ADD]
    /** Customer needs to call dealer to order toner. Dealer Name : @, Dealer Number : @ */
    public static final String NSZM1337I = "NSZM1337I";
    // END 2018/06/11 U.Kim [QC#22480, ADD]
    

    // START 2019/09/05 K.Fujimoto [QC#53294, ADD]
    /**
     * The reads will be registered as meter rollover because of lower reads than previous one.
     */
    public static final String NSZM1335W = "NSZM1335W";
    // END 2019/09/05 K.Fujimoto [QC#53294, ADD]

    // START 2020/03/03 [QC#56123, ADD]
    /**
     * Please entry meter read lower than the previous read if the meter reading type is selected rollover.
     */
    public static final String NSZM1370E = "NSZM1370E";

    /**
     * Current meter reading can not be lower than the previous read. Please select the meter reading type as Adjustment to enter lower reads.
     */
    public static final String NSAM0516E = "NSAM0516E";

    /**
     * Please execute it again after correcting the error field.
     */
    public static final String ZZM9037E = "ZZM9037E";
    // END   2020/03/03 [QC#56123, ADD]

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /**
     * There is not any billing schedule that requires final read. Please check the meter type.
     */
    public static final String NSAM0786W = "NSAM0786W";

    /**
     * There is billing schedule that requires final read. Please check the meter type.
     */
    public static final String NSAM0787W = "NSAM0787W";
    // END 2024/03/26 K.Watanabe [QC#63549, ADD]

    /**
     * SQL parameter<br>
     * SQL ID = chkSvcPhysMtrRead<br>
     * Mode01-bigger
     */
    public static final int CHK_MODE_01 = 1;

    /**
     * SQL parameter<br>
     * SQL ID = chkSvcPhysMtrRead<br>
     * Mode02-smaller
     */
    public static final int CHK_MODE_02 = 2;

    /**
     * SQL parameter<br>
     * SQL ID = chkSvcPhysMtrRead<br>
     * Mode03-Max Monthly Copy Volume Count
     */
    public static final int CHK_MODE_03 = 3;

    /**
     * SQL parameter<br>
     * SQL ID = chkSvcPhysMtrRead<br>
     * Mode04-Meter Reading Type
     */
    public static final int CHK_MODE_04 = 4;

    public static final String PSEUDO_MTR_LB_CD_ALL = "*";

    public static final String PSEUDO_MTR_LB_DESC_TXT_ALL = "All";

    public static final String PSEUDO_DS_MTR_READ_TP_GRP_CD_ALL = "*";

    public static final String PSEUDO_DS_MTR_READ_TP_GRP_DESC_TXT_ALL = "All";

    public static final String DEF_DS_MTR_READ_TP_CD = DS_MTR_READ_TP.ACTUAL;

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /**
     * SQL parameter<br>
     * SQL ID = getFinalReadPeriod<br>
     */
    /** DS_WIN_DAYS_TRGT_PER_TXT */
    public static final String DS_WIN_DAYS_TRGT_PER_TXT = "*";

    /** DS_WIN_DAYS_SVC_LINE_BIZ_CD_ALL */
    public static final String DS_WIN_DAYS_SVC_LINE_BIZ_CD_ALL = "*";
    // END 2024/03/26 K.Watanabe [QC#63549, ADD]

    // ADD START 2022/08/12 H.Watanabe [QC#60046]
    public static final String MTR_ENTRY_CMNT_TXT = "Inserted as Actual from Service read of {0}, with Meter Read Date {1}";
    // ADD END 2022/08/12 H.Watanabe [QC#60046]

    /**
     * Invalid SVC_PHYS_MTR_READ_GRP_SQ
     */
    public static final BigDecimal INVLD_SVC_PHYS_MTR_READ_GRP_SQ = BigDecimal.ONE.negate();

    public static boolean INPUT_IS_VALID = true;

    public static boolean INPUT_IS_INVALID = false;

    // ADD START 2015/11/26 K.Kasai [Unit Test #71]
    /** BILLING REP */
    public static final String FUNC_ID_BILL = BIZ_ID + "T010";

    /** SERVICE REP */
    public static final String FUNC_ID_SVC = BIZ_ID + "T020";

    /** SUPPLY REP */
    public static final String FUNC_ID_SUPPLY = BIZ_ID + "T030";

    /** ORDER DESK */
    public static final String FUNC_ID_ODR = BIZ_ID + "T040";

    /** READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T050";

    /** Slash */
    public static final String SLASH = "/";

    /** TERM_CONDITION_STAPLES_INCL_NAME */
    public static final String TERM_CONDITION_STAPLES_INCL_NAME = "TERM_COND_STAPLES_INCL_NAME";

    // ADD END 2015/11/26 K.Kasai [Unit Test #71]

    // START 2016/03/31 T.Tomita [QC#4394, ADD]
    /** num const : NEXT_SCHD_DT_WINDOW_DAYS */
    public static final String NEXT_SCHD_DT_WINDOW_DAYS = "NEXT_SCHD_DT_WINDOW_DAYS";

    /** DEF_NEXT_SCHD_DT_WINDOW_DAYS : 15 */
    public static final BigDecimal DEF_NEXT_SCHD_DT_WINDOW_DAYS = new BigDecimal(15);
    // END 2016/03/31 T.Tomita [QC#4394, ADD]
    
    // START 2016/10/25 [QC#15525, ADD]
    /** num const : NSAL0150_MTR_ENTRY_INTVL_DAYS */
    public static final String NSAL0150_MTR_ENTRY_INTVL_DAYS = "NSAL0150_MTR_ENTRY_INTVL_DAYS";

    /** DEF_METER_ENTRY_PERIOD : 11 */
    public static final BigDecimal DEF_SPLY_ODR_MTR_ENTRY_INTVL_DAYS = new BigDecimal(11);
    // END 2016/10/25 [QC#15525, ADD]

    // Add Start 2016/11/24 <QC#14992>
    /** num const : MTR_HIST_AVG_DAYS */ 
    public static final String MTR_HIST_AVG_DAYS = "MTR_HIST_AVG_DAYS";

    /** DEF_MTR_HIST_AVG_DAYS : 30.5 */ 
    public static final BigDecimal DEF_MTR_HIST_AVG_DAYS = new BigDecimal("30.5");
    // Add End   2016/11/24 <QC#14992>

    // Add Start 2016/12/07 <QC#15200>
    /** DEF_LINE_BIZ_CD */
    public static final String DEF_LINE_BIZ_CD = "ALL";
    // Add End   2016/12/07 <QC#15200>

    // add start 2017/09/07 QC#15134
    /** ROLL_OVER_COMMENT */
    public static final String ROLL_OVER_COMMENT = "Rollover:";

    /** EXCHANGE_COMMENT */
    public static final String EXCHANGE_COMMENT = "Exchange:";

    /** SPACE */
    public static final String SPACE = " ";

    /** COMMENT_LENGTH */
    public static final int COMMENT_LENGTH = 400;
    // add end 2017/09/07 QC#15134

    // START 2018/06/25 T.Ogura [QC#26336,ADD]
    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;
    // END   2018/06/25 T.Ogura [QC#26336,ADD]

    // Add Start 2018/07/06 QC#26972
    /** varchar const : SVC_TERM_COND_CAP_INFO_ATTRB */
    public static final String SVC_TERM_COND_CAP_ATTRB = "SVC_TERM_COND_CAP_ATTRB";

    /** Line feed */
    public static final String LINE_FEED = "\n";

    /** Colon */
    public static final String COLON = ":";

    /** An open order exists for this serial number. Please check Order History. */
    public static final String NSAM0730W = "NSAM0730W";
    // Add End 2018/07/06 QC#26972

    // add start 2018/07/09 QC#26923
    /** SEMICOLON */
    public static final String SEMICOLON = ";";

    /** Supply Reads not required. */
    public static final String NSAM0731W = "NSAM0731W";
    // add end 2018/07/09 QC#26923

    // START 2018/07/17 K.Kitachi [QC#26764, ADD]
    /** DEF_WINDOW_BEF_DAYS */
    public static final int DEF_WINDOW_BEF_DAYS = 0;
    // END 2018/07/17 K.Kitachi [QC#26764, ADD]

    // add start 2018/07/20 QC#26974
    /** Hyphen */
    public static final String HYPHEN = "-";
    // add end 2018/07/20 QC#26974

    // START 2023/04/12 R.Avelino [QC#61357, ADD]
    /** MTR_LB_FLG */
    public static final String MTR_LB_FLG = "MTR_LB_FLG";

    /** DS_MTR_READ_TP_GRP_FLG */
    public static final String DS_MTR_READ_TP_GRP_FLG = "DS_MTR_READ_TP_GRP_FLG";

    /** TOTAL_FLTR_CNT */
    public static final String TOTAL_FLTR_CNT = "TOTAL_FLTR_CNT";
    // END 2023/04/12 R.Avelino [QC#61357, ADD]
    
    // START 2024/04/08 S.Moriai [QC#63540, ADD]
    /** FROM_DATE_FLG */
    public static final String FROM_DATE_FLG = "FROM_DATE_FLG";
    
    /** THRU_DATE_FLG */
    public static final String THRU_DATE_FLG = "THRU_DATE_FLG";
    // END 2024/04/08 S.Moriai [QC#63540, ADD]

    // START 2024/03/08 K.Watanabe [QC#63539, ADD]
    /** num const : NSAL0150_INTVL_DAYS_LFS */
    public static final String NSAL0150_INTVL_DAYS_LFS = "NSAL0150_INTVL_DAYS_LFS";

    /** num const : NSAL0150_INTVL_DAYS_PPS */
    public static final String NSAL0150_INTVL_DAYS_PPS = "NSAL0150_INTVL_DAYS_PPS";

    /** num const : NSAL0150_INTVL_DAYS_WTS */
    public static final String NSAL0150_INTVL_DAYS_WTS = "NSAL0150_INTVL_DAYS_WTS";
    // END 2024/03/08 K.Watanabe [QC#63539, ADD]
}
