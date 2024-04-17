/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC108001.constant;

/**
 * <pre>
 * Get Procurement Source API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/01   Hitachi         T.Kanasaka      Create          QC1325
 * 2014/05/22   Hitachi         T.Kawazu        Update          CSA-279_288
 * </pre>
 */
public class NPZC108001Constant {

    /** Search condition default value */
    public static final String SEARCH_DEFAULT_VALUE = "*";

    /** Search result PROCR_TP_CD */
    public static final String PROCR_TP_CD = "PROCR_TP_CD";

    /** Search result FROM_INVTY_LOC_CD */
    public static final String FROM_INVTY_LOC_CD = "FROM_INVTY_LOC_CD";

    // START 2014/05/22 T.Kawazu [CSA-279_288 ADD]
    /** Search result FROM_PROCR_SRC */
    public static final String PROCR_SRC_PK = "PROCR_SRC_PK";

    /** Search result FROM_PROCR_SRC */
    public static final String AUTO_REL_FLG = "AUTO_REL_FLG";
    // END 2014/05/22 T.Kawazu [CSA-279_288 ADD]

    /** Global Company Code is required. */
    public static final String NPZM0001E = "NPZM0001E";

    /** Merchandise Code is required. */
    public static final String NPZM0020E = "NPZM0020E";

    /** Inventory Location Code is required. */
    public static final String NPZM0053E = "NPZM0053E";

    /** Could not retrieve Location Type Code or Location Role Type Code. */
    public static final String NPZM0135E = "NPZM0135E";

    /** The specified PROCR_SRC information does not exist. */
    public static final String NPZM0136E = "NPZM0136E";
}
