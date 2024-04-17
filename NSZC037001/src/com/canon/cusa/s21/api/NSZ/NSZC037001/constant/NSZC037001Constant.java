/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC037001.constant;

/**
 * <pre>
 * Service Credit Check API ( <_ FSR Update API ).
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/09/2015   Hitachi         Y.Tsuchimoto    Create          NA
 * 03/22/2016   Hitachi         T.Tomita        Update          CSA QC#895
 * 04/13/2016   Hitachi         A.Kohinata      Update          CSA QC#6866
 * 05/23/2018   Fujitsu         S.Ohki          Update          CSA QC#26177
 * 06/04/2018   Fujitsu         S.Ohki          Update          CSA QC#26177-2
 * 2018/08/30   Hitachi         K.Kitachi       Update          CSA QC#22665
 * 2021/08/03   CITS            L.Mandanas      Update          CSA QC#59066
 * 2022/01/27   Hitachi         R.Onozuka       Update          CSA QC#56182
 * 2022/02/11   CITS            L.Mandanas      Update          CSA QC#59066
 * 2023/06/02   Hitachi         S.Fujita        Update          CSA QC#60923
 * </pre>
 */
public final class NSZC037001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0124E = "NSZM0124E";

    /**
     * Input parameter "User Id" is a mandatory field.
     */
    public static final String NSZM0708E = "NSZM0708E";

    /**
     * The process cannot be executed because the @ is not "@".
     */
    public static final String NSZM0709E = "NSZM0709E";

    /**
     * Service Task is not found.
     */
    public static final String NSZM0710E = "NSZM0710E";

    /**
     * Business Application Name does not exist.
     */
    public static final String NSZM0711E = "NSZM0711E";

    /**
     * '@ API is not found.
     */
    public static final String NSZM0712E = "NSZM0712E";

    /**
     * Failed to update the @.
     */
    public static final String NSZM0625E = "NSZM0625E";

    /**
     * Failed to insert the @.
     */
    public static final String NSZM0626E = "NSZM0626E";

    /**
     * FSR is not found.
     */
    public static final String NSZM0498E = "NSZM0498E";

    // add start 2016/04/13 CSA Defect#6866
   /**
     * Service Task Hold Control is not found.
     */
    public static final String NSZM0963E = "NSZM0963E";
    // add end 2016/04/13 CSA Defect#6866

    /**
     * Message parameter FSR
     */
    public static final String MSG_PARAM_FSR = "FSR Status";
    /**
     * Message parameter SERVICE
     */
    public static final String MSG_PARAM_SERVICE = "Service Task Status";

    /**
     * Parameter name : glblCmpyCd
     */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * Parameter name : bizAppId
     */
    public static final String PARAM_BIZ_APP_ID = "bizAppId";

    /**
     * Parameter name : slsDt
     */
    public static final String PARAM_SLS_DT = "slsDt";

    /**
     * Parameter name : usrId
     */
    public static final String PARAM_USR_ID = "usrId";

    /**
     * Parameter name : fsrNum
     */
    public static final String PARAM_FSR_NUM = "fsrNum";

    /**
     * Parameter name : svcTaskNum
     */
    public static final String PARAM_SVC_TASK_NUM = "svcTaskNum";

    /**
     * Parameter name : dsSvcCallTpCd
     */
    public static final String PARAM_DS_SVC_CALL_TP_CD = "dsSvcCallTpCd";

    /**
     * Parameter name : svcBillTpCd
     */
    public static final String PARAM_SVC_BILL_TP_CD = "svcBillTpCd";

    /**
     * Parameter name : billToAcctNum
     */
    public static final String PARAM_BILL_TO_ACCT_NUM = "billToAcctNum";

    /**
     * Parameter name : billToCustCd
     */
    public static final String PARAM_BILL_TO_CUST_CD = "billToCustCd";

    /**
     * ReturnCode (Normal):
     */
    public static final String RETURN_CD_NORMAL = "0000";

    /**
     * Column name : BIZ_APP_ID
     */
    public static final String COLUMN_BIZ_APP_ID = "BIZ_APP_ID";

    /**
     * Column name : BIZ_APP_NM
     */
    public static final String COLUMN_BIZ_APP_NM = "BIZ_APP_NM";

    // add start 2016/03/22 CSA Defect#895
    /**
     * NSZC0370_WF_MEMO_TXT
     */
    public static final String NSZC0370_WF_MEMO_TXT = "NSZC0370_WF_MEMO_TXT";

    /**
     * Work Flow process name
     */
    public static final String WF_PROCESS_NM = "NSWP0004";

    /**
     * An error occure in Creation of Workflow Process.
     */
    public static final String NSZM0921E = "NSZM0921E";

    /**
     * System error has occurred.
     */
    public static final String NFDM0008E = "NFDM0008E";
    // add end 2016/03/22 CSA Defect#895

    // add start 2018/05/23 CSA Defect#26177
    /**
     * Function ID(EXTNE307T020)
     */
    public static final String FUNCTION_ID_EXTNE307T020 = "EXTNE307T020";  // Mod 2018/06/04 CSA Defect#26177-2
    // add end 2018/05/23 CSA Defect#26177

    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    /**
     * Could not get Mailtemplate.
     */
    public static final String NSZM0185E = "NSZM0185E";

    /**
     * The After Hours Service Call was created, but the failed Credit Review because the task failed credit review.
     */
    public static final String NSZM1347I = "NSZM1347I";

    /**
     * Key of VarCharConst : AR_CLT_DEF_EML_ADDR
     */
    public static final String AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";

    // START 2023/06/02 S.Fujita [QC#60923, ADD]
    /**
     * Key of VarCharConst : NSZC0370_SVC_MEMO_CMNT_TXT_1
     */
    public static final String CMNT_TXT_1 = "NSZC0370_SVC_MEMO_CMNT_TXT_1";

    /**
     * Key of VarCharConst : NSZC0370_SVC_MEMO_CMNT_TXT_2
     */
    public static final String CMNT_TXT_2 = "NSZC0370_SVC_MEMO_CMNT_TXT_2";

    /**
     * Key of VarCharConst : NSZC0370_SVC_MEMO_CMNT_TXT_3
     */
    public static final String CMNT_TXT_3 = "NSZC0370_SVC_MEMO_CMNT_TXT_3";

    /**
     * Key of VarCharConst : NSZC0370_SVC_MEMO_CMNT_TXT_4
     */
    public static final String CMNT_TXT_4 = "NSZC0370_SVC_MEMO_CMNT_TXT_4";

    /**
     * SPACE
     */
    public static final String SPACE = " ";

    // END 2023/06/02 S.Fujita [QC#60923, ADD]
    /**
     * mail group id for From
     */
    // START 2022/02/11 L.Mandanas [QC#59066, MOD]
      //public static final String MAIL_GROUP_ID_FROM = "FROM0002";
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";
    // END 2022/02/11 L.Mandanas [QC#59066, MOD]

    /**
     * template ID
     */
    public static final String MAIL_TEMPLATE_ID = "NSZC0370M001";
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

    // START 2021/08/03 L.Mandanas [QC#59066, ADD]
    /**
     * mail group mail key for From
     */
    // START 2022/02/11 L.Mandanas [QC#59066, MOD]
      //public static final String MAIL_GROUP_MAIL_KEY_FROM = "NS";
    public static final String MAIL_GROUP_MAIL_KEY_FROM = "NSZ";
    // END 2022/02/11 L.Mandanas [QC#59066, MOD]
    // END 2021/08/03 L.Mandanas [QC#59066, ADD]
    // START 2022/1/27 R.Onozuka [QC#56182, ADD]
    /** Format Time stamp **/
    public static final String FORMAT_TS = "yyyyMMddHHmmss";

    /** Date length */
    public static final int LEN_DT = 8;
    // END   2022/1/27 R.Onozuka [QC#56182, ADD]
}
