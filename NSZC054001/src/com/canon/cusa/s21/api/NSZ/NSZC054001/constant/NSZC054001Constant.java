/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC054001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Preview Billing API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Hitachi         A.Kohinata      Create          N/A
 * 03/30/2016   Hitachi         T.Aoyagi        Update          QC#1467
 * 12/15/2016   Hitachi         Y.Takeno        Update          QC#16285
 * 02/22/2017   Hitachi         Y.Takeno        Update          QC#16285-1
 * 2017/08/15   Hitachi         A.Kohinata      Update          QC#18799
 * 2018/03/13   Hitachi         U.Kim           Update          QC#18884(Sol#337)
 * 2019/09/05   Hitachi         K.Kim           Update          QC#52820
 * 2022/09/20   Hitachi         D.Yoshida       Update          QC#60070
 * 2022/12/19   Hitachi         H.Watanabe      Update          QC#60886
 *</pre>
 */
public class NSZC054001Constant {

    /** Format Date Time */
    public static final String FORMAT_DT = "yyyyMMdd";

    /** Scale of Divide */
    public static final int SCALE_DIVIDE = 4;

    /** Scale of Ratio */
    public static final BigDecimal SCALE_RATIO = new BigDecimal(100);

    // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
    /** Workflow process name */
    public static final String WF_PROCESS_NM = "NSWP0002";

    // START 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
    /** Workflow process name to compare to meter */
    public static final String WF_PROCESS_NM_COMP = "NSWP0005";
    // END 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]

    /** Prefix Document Id : Regular */
    public static final String PREFIX_DOC_ID_REG = "M";

    /** Prefix Document Id : Fleet */
    public static final String PREFIX_DOC_ID_FLT = "C";

    // add start 2016/10/03 CSA Defect#10729
    /** Prefix Document Id : Aggregate */
    public static final String PREFIX_DOC_ID_AGG = "A";
    // add end 2016/10/03 CSA Defect#10729

    /** Max Date */
    public static final String MAX_DATE = "29991231";
    // END 03/30/2016 T.Aoyagi [QC#1467, ADD]

    // del start 2017/08/15 QC#18799
//    /** Const Key BLLG_PRVW_AVAL_MAX_PRC */
//    public static final String CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC = "BLLG_PRVW_AVAL_MAX_PRC";
    // del end 2017/08/15 QC#18799

    // START 2019/09/05 [QC#52820, DEL]
    // /** Const Key BLLG_PRVW_AVAL_UPDOWN_RATIO */
    // public static final String CONST_KEY_BLLG_PRVW_AVAL_UPDOWN_RATIO = "BLLG_PRVW_AVAL_UPDOWN_RATIO";
    // END 2019/09/05 [QC#52820, DEL]

    // START 2019/09/05 [QC#52820, ADD]
    /** LFS **/
    /** Const Key BLLG_PRVW_AVAL_MAX_PRC_LFS */
    public static final String CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC_LFS = "BLLG_PRVW_AVAL_MAX_PRC_LFS";

    /** Const Key BLLG_PRVW_MIN_AMT_LFS */
    public static final String CONST_KEY_BLLG_PRVW_MIN_AMT_LFS = "BLLG_PRVW_MIN_AMT_LFS";

    /** Const Key BLLG_PRVW_UPDOWN_RATIO_LFS */
    public static final String CONST_KEY_BLLG_PRVW_UPDOWN_RATIO_LFS = "BLLG_PRVW_UPDOWN_RATIO_LFS";

    /** Const Key BLLG_PRVW_1ST_MAX_PRC_LFS */
    public static final String CONST_KEY_BLLG_PRVW_1ST_MAX_PRC_LFS = "BLLG_PRVW_1ST_MAX_PRC_LFS";

    /** PPS **/
    /** Const Key BLLG_PRVW_AVAL_MAX_PRC_PPS */
    public static final String CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC_PPS = "BLLG_PRVW_AVAL_MAX_PRC_PPS";

    /** Const Key BLLG_PRVW_MIN_AMT_PPS */
    public static final String CONST_KEY_BLLG_PRVW_MIN_AMT_PPS = "BLLG_PRVW_MIN_AMT_PPS";

    /** Const Key BLLG_PRVW_UPDOWN_RATIO_PPS */
    public static final String CONST_KEY_BLLG_PRVW_UPDOWN_RATIO_PPS = "BLLG_PRVW_UPDOWN_RATIO_PPS";

    /** Const Key BLLG_PRVW_1ST_MAX_PRC_PPS */
    public static final String CONST_KEY_BLLG_PRVW_1ST_MAX_PRC_PPS = "BLLG_PRVW_1ST_MAX_PRC_PPS";

    /** ESS **/
    /** Const Key BLLG_PRVW_AVAL_MAX_PRC_ESS */
    public static final String CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC_ESS = "BLLG_PRVW_AVAL_MAX_PRC_ESS";
    
    /** Const Key BLLG_PRVW_MIN_AMT_ESS */
    public static final String CONST_KEY_BLLG_PRVW_MIN_AMT_ESS = "BLLG_PRVW_MIN_AMT_ESS";

    /** Const Key BLLG_PRVW_UPDOWN_RATIO_ESS */
    public static final String CONST_KEY_BLLG_PRVW_UPDOWN_RATIO_ESS = "BLLG_PRVW_UPDOWN_RATIO_ESS";

    /** Const Key BLLG_PRVW_1ST_MAX_PRC_ESS */
    public static final String CONST_KEY_BLLG_PRVW_1ST_MAX_PRC_ESS = "BLLG_PRVW_1ST_MAX_PRC_ESS";
    // END 2019/09/05 [QC#52820, ADD]

    // del start 2017/08/15 QC#18799
//    /** Const Key BLLG_MTR_READ_WINDOW_BEF_DAYS */
//    public static final String CONST_KEY_BLLG_MTR_READ_WINDOW_BEF_DAYS = "BLLG_MTR_READ_WINDOW_BEF_DAYS";
    // del end 2017/08/15 QC#18799

    /** Const Key BLLG_MTR_READ_WINDOW_AFT_DAYS */
    public static final String CONST_KEY_BLLG_MTR_READ_WINDOW_AFT_DAYS = "BLLG_MTR_READ_WINDOW_AFT_DAYS";

    /** Const Key BLLG_PRVW_CHK_BTW_MTH_NUM */
    public static final String CONST_KEY_BLLG_PRVW_CHK_BTW_MTH_NUM = "BLLG_PRVW_CHK_BTW_MTH_NUM";

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    /** Input parameter "Meter Read Date" is a mandatory field. */
    public static final String NSZM0268E = "NSZM0268E";

    // Add Start 12/15/2016 <QC#16285>
    /** Failed to insert the SVC_MEMO. (System Error) */
    public static final String NSZM0475E = "NSZM0475E";
    // Add End   12/15/2016 <QC#16285>

    /** The corresponding data does not exist in "DS_CONTR". */
    public static final String NSZM0639E = "NSZM0639E";

    /** SVC_CONTR_BLLG is not found. */
    public static final String NSZM0786E = "NSZM0786E";

    /** Failed to update a SVC_CONTR_BLLG record. */
    public static final String NSZM0787E = "NSZM0787E";

    /** Failed to update a DS_CONTR record. */
    public static final String NSZM0789E = "NSZM0789E";

    /** Please set Machine PK with a regular contract. */
    public static final String NSZM0790E = "NSZM0790E";

    // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
    /** It failed to cancel workflow. */
    public static final String NSZM0866E = "NSZM0866E";

    /** An error occurred in Workflow Process. */
    public static final String NSZM0926E = "NSZM0926E";
    // END 03/30/2016 T.Aoyagi [QC#1467, ADD]

    // Add Start 12/14/2016 <QC#16285>
    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** SVC Memo (SVC_CMNT_TXT, Header Level) */
    // Mod Start 02/22/2017 <QC#16285-1>
    // public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR = "Manual update - Contract# [%s]";
    public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR = "Invalid on Preview Billing - Workflow# [%s] on Contract# [%s]";
    // Mod End   02/22/2017 <QC#16285-1>
    // Add End   12/14/2016 <QC#16285>

    /** CONTR_BR_FIRST_ORG_CD */
    public static final String CONTR_BR_FIRST_ORG_CD = "CONTR_BR_FIRST_ORG_CD";

    // add start 2017/08/15 QC#18799
    /** DATE FORMAT: yyyymmdd */
    public static final String DATE_FORMAT = "yyyymmdd";

    /** DEF_WINDOW_BEF_DAYS */
    // START 2022/09/20 [QC#60070,MOD]
//    public static final String DEF_WINDOW_BEF_DAYS = "0";
    public static final int DEF_WINDOW_BEF_DAYS = 0;
    // END   2022/09/20 [QC#60070,MOD]
    // add end 2017/08/15 QC#18799
    // START 2018/03/13 U.Kim [QC#18884(Sol#337, ADD]
    /** SVC_ORG_FUNC_ROLE_TP_SUPERVR */
    public static final String SVC_ORG_FUNC_ROLE_TP_SUPERVR = "SVC_ORG_FUNC_ROLE_TP_SUPERVR";

    // EST 2018/03/13 U.Kim [QC#18884(Sol#337, ADD]
    // 2022/12/19 QC#60886 Add Start
    /** BILL_PRVW_AVAL_BILL_LIST */
    public static final String[][] BILL_PRVW_AVAL_BILL_LIST = {{"LFS", "PPS", "ESS"}, {"BILL_PRVW_AVAL_BILL_LFS", "BILL_PRVW_AVAL_BILL_PPS", "BILL_PRVW_AVAL_BILL_ESS"}};
    // 2022/12/19 QC#60886 Add End
}
