/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFZ.NFZC504001.constant;

/**
 * <pre>
 * Hold Release API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/23   Fujitsu         S.Iidaka        Create
 * 2016/11/29   Fujitsu         W.Honda         Update          QC#16268
 * </pre>
 */
public final class NFZC504001Constant {

    /** Business App ID */
    public static final String BIZ_APP_ID = "NFZC5040";

    /** Business App Name */
    public static final String BIZ_APP_NM = "Hold Release API";

    /** Max Message Length */
    public static final int MAX_MSG_LEN = 300;

    /** Date Time Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** Date Format */
    public static final String YYYYMMDD = "yyyyMMdd";

    /** AP_VND_INV_SQ_NUM */
    public static final String AP_VND_INV_SQ_NUM_00 = "00";

    /** AP_INV_TP_CD 00 */
    public static final String AP_INV_TP_CD_00 = "00";

    /** ATT_DATA_GRP_TXT KEYWORD AP_VND_CD */
    public static final String PARAMS_AP_VND_CD_KEY = "AP_VND_CD";

    /** ATT_DATA_GRP_TXT KEYWORD AP_VND_INV_NUM */
    public static final String PARAMS_AP_VND_INV_NUM_KEY = "AP_VND_INV_NUM";

    /** Hold Release Comment Text */
    public static final String PMT_HLD_REL_CMNT_TXT_THEREFORE = "NFZC5040 Hold Release API";

    /** Hold Release Person Code */
    public static final String PMT_HLD_REL_PSN_CD_THEREFORE = "NFZC5040";

    /**
     * "Global Company Code" is required.
     */
    public static final String NFBM0243E = "NFBM0243E";

    /**
     * "Document ID" is required.
     */
    public static final String NFBM0244E = "NFBM0244E";

    /**
     * "Category Number" is required.
     */
    public static final String NFBM0245E = "NFBM0245E";

    /**
     * "Business Document Number" is required.
     */
    public static final String NFBM0256E = "NFBM0256E";

    /**
     * Specified Therefore Document ID does exist in S21.
     */
    public static final String NFBM0257E = "NFBM0257E";

    /**
     * Specified Vendor Invoice Number does not exist in S21.
     */
    public static final String NFBM0258E = "NFBM0258E";

    // Add Start 2016/11/29 QC#16268
    /**
     * It failed to update. [@]
     */
    public static final String NFZM0027E = "NFZM0027E";
    // Add End   2016/11/29 QC#16268

    /**
     * Specified PO Information is invalid or does not exist.
     */
    public static final String NPZM0021E = "NPZM0021E";
}
