/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB004001.constant;

/**
 * <pre>
 * NSAB004001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/06/2015   Hitachi         T.Harada        Create
 * 2022/06/21   CITS            R.jin           Update          QC#60216
 * 2024/01/11   Hitachi         S.Moriai        Update          QC#62117
 * </pre>
 */
public final class NSAB004001Constant {
    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** A value is not set in the required parameter field. Field Name:  [@] */
    public static final String NSZM0589E = "NSZM0589E";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** MAX_COMMIT_NUMBER:1000 */
    // QC#60216 Mod Start
    //public static final int MAX_COMMIT_NUMBER = 1000;
    public static final int MAX_COMMIT_NUMBER = 1;
    // QC#60216 Mod End

    /*****************************************************************
     * SSM statement id
     ****************************************************************/
    /** SSM statement id: getDsContrPK4Active */
    public static final String DS_CONTR_ACTIVE = "getDsContrPK4Active";

    /** SSM statement id: getDsContrDtlPK4Active */
    public static final String DS_CONTR_DTL_ACTIVE = "getDsContrDtl4Active";

    /** SSM statement id: getDsContrBllgMtrPK4Active */
    public static final String DS_CONTR_BLLG_MTR_ACTIVE = "getDsContrBllgMtr4Active";

    /** SSM statement id: getDsContrBllgMtrPK4Active */
    public static final String DS_CONTR_PRC_EFF_ACTIVE = "getDsContrPrcEff4Active";

    /** SSM statement id: getDsContrPK4Expired */
    public static final String DS_CONTR_EXPIRED = "getDsContrPK4Expired";

    /** SSM statement id: getDsContrDtlPK4Expired */
    public static final String DS_CONTR_DTL_EXPIRED = "getDsContrDtl4Expired";

    /** SSM statement id: getDsContrBllgMtrPK4Expired */
    public static final String DS_CONTR_BLLG_MTR_EXPIRED = "getDsContrBllgMtr4Expired";

    /** SSM statement id: getDsContrPrcEffPK4Expired */
    public static final String DS_CONTR_PRC_EFF_EXPIRED = "getDsContrPrcEff4Expired";

    /** SSM statement id: getDsContrPK4Terminated */
    public static final String DS_CONTR_TERMINATED = "getDsContrPK4Terminated";

    /** SSM statement id: getDsContrDtlPK4Terminated */
    public static final String DS_CONTR_DTL_TERMINATED = "getDsContrDtl4Terminated";

    /** SSM statement id: getDsContrBllgMtrPK4Terminated */
    public static final String DS_CONTR_BLLG_MTR_TERMINATED = "getDsContrBllgMtr4Terminated";

    /** SSM statement id: getDsContrPrcEffPK4Terminated */
    public static final String DS_CONTR_PRC_EFF_TERMINATED = "getDsContrPrcEff4Terminated";
    
    // START 2022/06/21 R.jin [QC#60216,ADD]
    /** CFS_CONTR_PRC_UPLFT_FLG */
    public static final String CFS_CONTR_PRC_UPLFT_FLG = "CFS_CONTR_PRC_UPLFT_FLG";

    /** SSM statement id: getUpdateBasePrcDealAmtTarget */
    public static final String UPDATE_BASE_PRC_DEALAMT_TARGET = "getUpdateBasePrcDealAmtTarget";
    
    /** SSM statement id: getUpdateXsMtrAmtRateTarget */
    public static final String UPDATE_XS_MTRAMT_RATE_TARGET = "getUpdateXsMtrAmtRateTarget";
    // END 2022/06/21 R.jin [QC#60216,ADD]
    
    // START 2024/01/11 S.Moriai [QC#62117,ADD]
    /**
     * Please execute again after checking the warning field.
     */
    public static final String NSAM0686W = "NSAM0686W";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    public static final String START_MTR_READ_WINDOW_BEF_DAYS = "START_MTR_READ_WINDOW_BEF_DAYS";

    /** CarryOver judgment */
    public static final String NG_ROW = "Y";

    /** Batch Id */
    public static final String BATCH_ID = "NSAB004001";

    /** QA Hold Reason */
    public static final String QA_HOLD_REASON_MSG = "missing start meter";

    // ---------------------------------------
    // Mail Template
    // ---------------------------------------

    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    public static final String MAIL_GROUP_ID_TO = "NSAB0040";

    public static final String MAIL_TEMPLATE_ID = "NSAB0040M001";

    public static final String MAIL_TEMPLATE_SET_KEY_DATE = "salesDate";

    public static final String MAIL_TEMPLATE_SET_KEY_MESSAGE = "missingStartReadTable";
    // END 2024/01/11 S.Moriai [QC#62117,ADD]

}
