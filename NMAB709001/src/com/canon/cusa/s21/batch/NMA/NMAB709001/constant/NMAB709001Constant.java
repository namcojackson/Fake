/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB709001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Item Supersessions Mass Add Price List Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public final class NMAB709001Constant {

    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** [@] is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** The value for [@] must be smaller than [@] */
    public static final String NMAM0043E = "NMAM0043E";

    /** MAX_AMT VALUE */
    public static final BigDecimal MAX_AMT = new BigDecimal("1000000000000000");

    // ///////////////////////////////////////////////////////
    // DB COLUMN
    // ///////////////////////////////////////////////////////
    /** DB COLUMN : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN : RQST_STS_TP_CD */
    public static final String COL_RQST_STS_TP_CD = "RQST_STS_TP_CD";

    /** DB COLUMN : PRC_QLFY_TP_CD */
    public static final String COL_PRC_QLFY_TP_CD = "PRC_QLFY_TP_CD";

    /** DB COLUMN : PRC_QLFY_VAL_TXT */
    public static final String COL_PRC_QLFY_VAL_TXT = "PRC_QLFY_VAL_TXT";

    /** DB COLUMN : PROC_DATE */
    public static final String COL_PROC_DATE = "PROC_DATE";

    /** DB COLUMN : PKG_UOM_CD */
    public static final String COL_PKG_UOM_CD = "PKG_UOM_CD";

    /** DB COLUMN : DEL_FLG */
    public static final String COL_DEL_FLG = "DEL_FLG";

    /** DB COLUMN : ACTV_FLG */
    public static final String COL_ACTV_FLG = "ACTV_FLG";

    /** DB COLUMN : BASE_PKG_UOM_CD */
    public static final String COL_BASE_PKG_UOM_CD = "BASE_PKG_UOM_CD";

    /** DB COLUMN : PRC_CATG_CD */
    public static final String COL_PRC_CATG_CD_EXCEPT = "PRC_CATG_CD_EXCEPT";

    /** DB COLUMN : PRC_CATG_CD */
    public static final String COL_PRC_CATG_CD_SPECIFIC = "PRC_CATG_CD_SPECIFIC";

    /** DB COLUMN : PRC_CATG_CD */
    public static final String COL_PRC_CATG_CD = "PRC_CATG_CD";

    /** DB COLUMN : PRC_LIST_EQUIP_CONFIG_NUM */
    public static final String COL_PRC_LIST_EQUIP_CONFIG_NUM = "PRC_LIST_EQUIP_CONFIG_NUM";

    /** DB COLUMN : PRC_LIST_EQUIP_CONFIG_NM */
    public static final String COL_PRC_LIST_EQUIP_CONFIG_NM = "PRC_LIST_EQUIP_CONFIG_NM";

    /** DB COLUMN : PRC_TERM_UOM_CD */
    public static final String COL_PRC_TERM_UOM_CD = "PRC_TERM_UOM_CD";

    /** DB COLUMN : PRC_TERM_AOT */
    public static final String COL_PRC_TERM_AOT = "PRC_TERM_AOT";

    /** DB COLUMN : EFF_FROM_DT */
    public static final String COL_EFF_FROM_DT = "EFF_FROM_DT";

    /** DB COLUMN : EFF_THRU_DT */
    public static final String COL_EFF_THRU_DT = "EFF_THRU_DT";
}
