/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC107001.constant;

/**
 * <pre>
 * Business ID : NPZC107001：Planning Update by Supersede API
 * Constant Class
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/28/2015   CSAI       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPZC107001Constant {

    /**
     * Param Name : Global Company Code
     */
    public static final String PARAM_NAME_GLBL_CMPY_CD = "Global Company Code";

    /**
     * mrp info rgtn status cd : Available 'P20'
     */
    public static final String STATUS_CD_AVAILABLE = "P20";

    /**
     * Flag on Y
     */
    public static final String FLG_ON_Y = "Y";

    /**
     * Flag on N
     */
    public static final String FLG_ON_N = "N";

    // ==================================
    // SQL Param
    // ==================================

    /**
     * DB Param : glblCmpyCd
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param : supdFrmMdsCd
     */
    public static final String DB_PARAM_SUPD_FRM_MDSE_CD = "supdFrmMdsCd";

    /**
     * DB Param : supdToMdsCd
     */
    public static final String DB_PARAM_SUPD_TO_MDSE_CD = "supdToMdsCd";

    /**
     * DB Param : invtyLocCd
     */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    // ==================================
    // Message Code
    // ==================================

    /**
     * NPZM0001E : Global Company Code is required.
     */
    public static final String NPZM0001E = "NPZM0001E";
}
