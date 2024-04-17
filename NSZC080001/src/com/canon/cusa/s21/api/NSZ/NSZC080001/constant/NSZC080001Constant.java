/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC080001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Contract Revenue Distribution API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/22/2015   Hitachi         T.Nishimura       Create          NA
 * </pre>
 */
public final class NSZC080001Constant {

    /**
     * @ is not set.[Input Parameters]
     */
    public static final String NSZM0401E = "NSZM0401E";
    /** Fixed message param : "Input Parameters" */
    public static final String NSZM0401E_02 = "Input Parameters";
    /** @ doesn't exist. */
    public static final String NSZM0627E = "NSZM0627E";
    /** Failed to register to the @ table. */
    public static final String NSZM0398E = "NSZM0398E";
    /** Failed to delete the @ table. */
    public static final String NSZM0637E = "NSZM0637E";
    /** GLBL_CMPY_CD */
    public static final String ITEM_NAME_GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** SLS_DT */
    public static final String ITEM_NAME_SLS_DT = "SLS_DT";
    /** DS_CONTR_PK */
    public static final String ITEM_NAME_DS_CONTR_PK = "DS_CONTR_PK";
    /** DS_CONTR_DTL_PK */
    public static final String ITEM_NAME_DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    /** SVC_INV_CHRG_TP_CD */
    public static final String ITEM_NAME_SVC_INV_CHRG_TP_CD = "SVC_INV_CHRG_TP_CD";
    /** dsContrPk */
    public static final String KEY_DS_CONTR_PK = "dsContrPk";
    /** dsContrDtlPk */
    public static final String KEY_DS_CONTR_DTL_PK = "dsContrDtlPk";
    /** svcInvChrgTpCd */
    public static final String KEY_SVC_INV_CHRG_TP_CD = "svcInvChrgTpCd";
    /** PRC_ALLOC_RATE : 100 % */
    public static final BigDecimal PRC_ALLOC_RATE_DIVISOR_100 = new BigDecimal("100");
    /** PRC_ALLOC_RATE SCALE = 2 */
    public static final int PRC_ALLOC_RATE_SCALE = 2;
    /** Table : DS_CONTR_PRC_ALLOC */
    public static final String DS_CONTR_PRC_ALLOC = "DS_CONTR_PRC_ALLOC";
    /** Table : DS_CONTR_BR_ALLOC */
    public static final String DS_CONTR_BR_ALLOC = "DS_CONTR_BR_ALLOC";
    /** Table : DS_CONTR_BR_ALLOC */
    public static final String DS_CONTR_SEG_ALLOC = "DS_CONTR_SEG_ALLOC";

}
