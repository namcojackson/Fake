/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NAZ.NAZC006001;

/**
 * <pre>
 * Constant Class For Retail Validation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/02/2011   Fujitsu         N.Yamaguchi     Create          N/A
 *</pre>
 */
public interface NAZC006001Constant {

    // **********************
    // Message
    // **********************
    /** Data Company Code is not entered. */
    String NWZM0001E = "NWZM0001E";

    /** Sales Date is not entered. */
    String NWZM0445E = "NWZM0445E";

    /** Order Number is not entered. */
    String NWZM0002E = "NWZM0002E";

    /** Specified value for Current Process is incorrect. */
    String NWZM0723E = "NWZM0723E";

    /** Process Mode" is not set. */
    String NWZM0580E = "NWZM0580E";

    /** Process Mode is not entered correctly. */
    String NAZM0152E = "NAZM0152E";

    /** Order Line Number is not entered. */
    String NWZM0003E = "NWZM0003E";

    /** Order Line Sub Number is not entered. */
    String NWZM0004E = "NWZM0004E";

    /** The data does not exist in CPO. */
    String NWZM0073E = "NWZM0073E";

    /** The data does not exist in CPO_DTL. */
    String NWZM0074E = "NWZM0074E";

    /** The data does not exist in SHPG_PLN. */
    String NWZM0075E = "NWZM0075E";

    /** The data does not exist in HLD. */
    String NWZM0253E = "NWZM0253E";

    /** It failed to update the SHPG_PLN. */
    String NWZM0078E = "NWZM0078E";

    /** DB error occurred. */
    String NWDM0007E = "NWDM0007E";

    /** Could not get the item. (CNTY) */
    String NWZM0757E = "NWZM0757E";

    /** Could not get the item. (BIZ_TP) */
    String NAZM0009E = "NAZM0009E";

    /** It failed to update the HLD. */
    String NWZM0080E = "NWZM0080E";

    /** Cannot get "Hold" or "Not Hold". */
    String NWZM0240E = "NWZM0240E";

    /** The "Hold Reason Code" does not exist in the Master. */
    String NWZM0441E = "NWZM0441E";

    /** It failed to update the CPO_DTL. */
    String NWZM0081E = "NWZM0081E";

    /** Retail CPO Position Number is not entered. */
    String NAZM0153E = "NAZM0153E";

    // **********************
    // Auto Seq Key(Extend)
    // **********************
    /** Auto Seq Key (INSTL_CD). */
    String INSTL_CD = "INSTL_CD";

    /** Auto Seq Key (INSTL_SUB_LOC_CD). */
    String INSTL_SUB_LOC_CD = "INSTL_SUB_LOC_CD";

    // **********************
    // Parameter
    // **********************
    /** Process Mode : 1.Validation */
    String PROC_MODE_VALID = "1";

    /** Process Mode : 2.Recalculation */
    String PROC_MODE_RECALC = "2";

    /** Validation Point : 1.OrderValidation */
    String VALID_POINT_OV = "1";
    
    /** Validation Point : 4.ManagerValidation (Defect#1173) */
    String VALID_POINT_MA = "4";

    /** Validation Point : 1.CreditCheckValidation */
    String VALID_POINT_CV = "2";

    /** Validation Point : 1.ShippingPlanValidation */
    String VALID_POINT_SV = "3";

    /** Release Point : 1.ReleasePointCreditChk */
    String REL_POINT_CC = "1";

    /** Release Point : 2.ReleasePointShippingPlan */
    String REL_POINT_SP = "2";

    /** Release Point : 3.ReleaseSoCreation */
    String REL_POINT_SO = "3";

    // TODO change CodeTable class?
    /** Credit CheckRequired Type :3 CreditLimit */
    String CC_REQ_TP_CRDIT_LIMIT = "3";

    /** relFuncTp:07 OrderChange */
    // TODO change Code Constants Class
    String REL_FUNC_TP_ORD_CHANG = "07";

    // **********************
    // Process Log Param
    // **********************
    /** SUB_SYS_ID */
    String SUB_SYS_ID = "NAZ";

    /** PROCESS_ID */
    String PROCESS_ID = "OM";

    /** DOCUMENT_TYPE */
    String DOCUMENT_TYPE = "OM";

    /** EVENT_ID_HOLD */
    String EVENT_ID_HOLD = "Hold";

    /** blank */
    String BLANK = "";
    
    /** Set Mdse Sub Line Number. */
    String SET_MDSE_SUB_LINE_NUM = "000";
}
