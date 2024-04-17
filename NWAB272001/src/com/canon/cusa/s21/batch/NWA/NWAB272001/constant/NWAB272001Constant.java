/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB272001.constant;

/**
 * <pre>
 * Collection Invoice Email Request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/09/07   Hitachi         N.Takatsu       Create          QC#60256
 * </pre>
 */
public class NWAB272001Constant {

    /**
     * [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * APP_BATCH
     */
    public static final String APP_BATCH = "NWAB272001";

    /**
     * ERR_PRM_GLBL_CMPY_CD : Global Company Code
     */
    public static final String ERR_PRM_GLBL_CMPY_CD = "Global Company Code";

    /** REPORT_ID. */
    public static final String REPORT_ID = "NLBF0010";

    /** REPORT_TITLE. */
    public static final String REPORT_TITLE = "S/O Report";

    /** From Mail Address Group Id. */
    public static final String FROM_GRP_ID ="FROM0014";
    /**
     * FORMAT_TIMESTAMP
     */
    public static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";

    /** @ is not found. */
    public static final String NWAM8466E = "NWAM8466E";

    /** Failed to PDF Create. [@]. */
    public static final String NWAM8467E = "NWAM8467E";

    /** An error occurred at @. */
    public static final String NWAM8469E = "NWAM8469E";

    /** It failed to update @. */
    public static final String NWAM0741E = "NWAM0741E";

    /** REPORT_TITLE_SEPARATOR. */
    public static final String REPORT_TITLE_SEPARATOR = " ";

    /** INTL_LANG_VAL_COL_NM. */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** SO_NUM. */
    public static final String SO_NUM = "SO_NUM";

    /** SHPG_ORD_RPT_PRINT_RQST_SQ. */
    public static final String SHPG_ORD_RPT_PRINT_RQST_SQ = "SHPG_ORD_RPT_PRINT_RQST_SQ";

    /** EXPT_DOC_PRINT_RQST_SQ. */
    public static final String EXPT_DOC_PRINT_RQST_SQ = "EXPT_DOC_PRINT_RQST_SQ";

    public static final String SHIPPING_ORDER_PRINT_API = "Shipping Order Print API SO#";

    public static final String EIP_REQ_EXCEPTION = "EIP Request Exception SO#";

    public static final String EIP_REQ_ERROR = "EIP Request Error SO#";

    public static final String FROM_ADDR_ERROR = "From Address GrpID:";

    public static final String TO_ADDR_ERROR = "To Address GrpID:";

    /** SHIPPING_ORDER */
    public static final String SHIPPING_ORDER = "Shipping Order";

    /** SHIPPING_ORDER_NUMBER */
    public static final String SHIPPING_ORDER_NUMBER = "SO#:";

    /** EXTENSION PDF */
    public static final String PDF = ".pdf";

    /** UNDER_BAR */
    public static final String UNDER_BAR = "_";

}
