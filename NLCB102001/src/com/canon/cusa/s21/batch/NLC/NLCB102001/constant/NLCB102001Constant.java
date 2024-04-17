/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB102001.constant;

/**
 * <pre>
 * Stock Out Data of the Day<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 10/22/2013   Hitachi         T.Tomita        Created         MEX-IF017
 * 11/22/2013   Hitachi         T.Tomita        Update          QC3159
 * 05/23/2016   CITS            R.Shimamoto     Update          V2.0
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 02/04/2020   Fujitsu         S.Iidaka        Update          QC#55572
 * </pre>
 */
public class NLCB102001Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLCB1020";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** message id : NLCM0125E Data insert failure. @ */
    public static final String NLCM0125E = "NLCM0125E";

    /** message id : ZZZM9026E [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** @ could not be obtained .[@] */
    public static final String NLCM0127W = "NLCM0127W";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Shipping Method */
    public static final String MSG_ITEM_SHIP_METHODE = "Shipping Method";

    /** CARR_CD: * */
    public static final String DEFULT_CARR_CD = "*";

    /** Split Item : Comma */
    public static final String COMMA = ",";

    /** VAR_CHAR_CONST Key : SCUBE_IF_CINC_VND_CD */
    public static final String SCUBE_IF_CINC_VND_CD = "SCUBE_IF_CINC_VND_CD";

    /** VAR_CHAR_CONST Key : PRT_INCL_TECH_INVTY_CINC_FLG */
    public static final String PRT_INCL_TECH_INVTY_CINC_FLG = "PRT_INCL_TECH_INVTY_CINC_FLG";

    /** VAR_CHAR_CONST Key : PRT_EXCL_INVTY_LOC_CD_CINC */
    public static final String PRT_EXCL_INVTY_LOC_CD_CINC = "PRT_EXCL_INVTY_LOC_CD_CINC";

    /** CINC_GLBL_CMPY_CATG_CD : 01(INTERNAL) */
    public static final String CINC_GLBL_CMPY_CATG_CD_INTERNAL = "01";

    /** CINC_GLBL_CMPY_CATG_CD : 02(SUBSIDIAＲY) */
    public static final String CINC_GLBL_CMPY_CATG_CD_SUBSIDIARY = "02";

    /** CINC_GLBL_CMPY_CATG_CD : 99(OTHER) */
    public static final String CINC_GLBL_CMPY_CATG_CD_OTHER = "99";

    /** CINC_GLBL_SHPG_METH_CD : 99(OTHER) */
    public static final String CINC_GLBL_SHPG_METH_CD_OTHER = "99";

    /** CINC_GLBL_SHPG_CATG_CD : A */
    public static final String CINC_GLBL_SHPG_CATG_CD_A = "A";

    /** CINC_GLBL_SHPG_CATG_CD : B */
    public static final String CINC_GLBL_SHPG_CATG_CD_B = "B";

    /** CINC_GLBL_SHPG_CATG_CD : F */
    public static final String CINC_GLBL_SHPG_CATG_CD_F = "F";

    /** CINC_GLBL_SHPG_CATG_CD : Z1 */
    public static final String CINC_GLBL_SHPG_CATG_CD_Z1 = "Z1";

    /** CINC_GLBL_SHPG_CATG_CD : Z3 */
    public static final String CINC_GLBL_SHPG_CATG_CD_Z3 = "Z3";

    /** CINC_GLBL_SHPG_CATG_CD : Z4 */
    public static final String CINC_GLBL_SHPG_CATG_CD_Z4 = "Z4";

    /** CINC_GLBL_SHPG_CATG_CD : ZZ */
    public static final String CINC_GLBL_SHPG_CATG_CD_ZZ = "ZZ";

    /** CINC_GLBL_ORD_CATG_CD : A1 */
    public static final String CINC_GLBL_ORD_CATG_CD_A1 = "A1";

    /** CINC_GLBL_ORD_CATG_CD : ZZ */
    public static final String CINC_GLBL_ORD_CATG_CD_ZZ = "ZZ";

    // QC#26966 Add.
    /** CINC_GLBL_ORD_CATG_CD : BZ */
    public static final String CINC_GLBL_ORD_CATG_CD_BZ = "BZ";

    /** CINC_DSPL_RSN_CD : 01 */
    public static final String CINC_DSPL_RSN_CD_01 = "01";

    /** PRT_CHRG_IND : C */
    public static final String PRT_CHRG_IND_C = "C";

    /** MAX_COMMIT_NUMBER : 1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    // START 05/25/2016 R.Shimamoto [V2.0]

    /** SCUBE_IF_CINC_VND_CD_DEF:* */
    public static final String SCUBE_IF_CINC_VND_CD_DEF = "CANON9";

    /** PRT_INCL_TECH_INVTY_CINC_FLG_DEF:* */
    public static final String PRT_INCL_TECH_INVTY_CINC_FLG_DEF = "Y";

    /** Asterisk:* */
    public static final String ASTERISK = "*";

    /** db column : TRX_CD. */
    public static final String TRX_CD = "TRX_CD";

    /** db column : TRX_RSN_CD. */
    public static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** db column : MDSE_CD. */
    public static final String MDSE_CD = "MDSE_CD";

    /** db column : CUSA_VND_CD. */
    public static final String CUSA_VND_CD = "CUSA_VND_CD";

    /** db column : INVTY_LOC_CD. */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** db column : LOC_TP_CD. */
    public static final String LOC_TP_CD = "LOC_TP_CD";

    /** db column : SHIP_TO_LOC_CUST_CD. */
    public static final String SHIP_TO_LOC_CUST_CD = "SHIP_TO_LOC_CUST_CD";

    /** db column : SHIP_TO_CUST_CD. */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** db column : SHIP_TO_LOC_TP_CD. */
    public static final String SHIP_TO_LOC_TP_CD = "SHIP_TO_LOC_TP_CD";

    /** db column : STK_OUT_RTL_WH_CD. */
    public static final String STK_OUT_RTL_WH_CD = "STK_OUT_RTL_WH_CD";

    /** db column : STK_OUT_WH_OWNR_CD. */
    public static final String STK_OUT_WH_OWNR_CD = "STK_OUT_WH_OWNR_CD";

    /** db column : STK_OUT_LOC_TP_CD. */
    public static final String STK_OUT_LOC_TP_CD = "STK_OUT_LOC_TP_CD";

    /** db column : WHT_TO_RTL_WH_CD. */
    public static final String WHT_TO_RTL_WH_CD = "WHT_TO_RTL_WH_CD";

    /** db column : WHT_TO_WH_OWNR_CD. */
    public static final String WHT_TO_WH_OWNR_CD = "WHT_TO_WH_OWNR_CD";

    /** db column : WHT_TO_LOC_TP_CD. */
    public static final String WHT_TO_LOC_TP_CD = "WHT_TO_LOC_TP_CD";

    /** db column : WHT_TO_INVTY_LOC_CD. */
    public static final String WHT_TO_INVTY_LOC_CD = "WHT_TO_INVTY_LOC_CD";

    /** db column : WHT_TO_INVTY_LOC_TP_CD. */
    public static final String WHT_TO_INVTY_LOC_TP_CD = "WHT_TO_INVTY_LOC_TP_CD";

    /** db column : SHIP_FROM_LOC_CUST_CD. */
    public static final String SHIP_FROM_LOC_CUST_CD = "SHIP_FROM_LOC_CUST_CD";

    /** db column : SHIP_FROM_LOC_TP_CD. */
    public static final String SHIP_FROM_LOC_TP_CD = "SHIP_FROM_LOC_TP_CD";

    /** db column : SHIP_TO_RTL_WH_CD. */
    public static final String SHIP_TO_RTL_WH_CD = "SHIP_TO_RTL_WH_CD";

    /** db column : SHIP_TO_WH_OWNR_CD. */
    public static final String SHIP_TO_WH_OWNR_CD = "SHIP_TO_WH_OWNR_CD";

    /** db column : INVTY_TRX_PK. */
    public static final String INVTY_TRX_PK = "INVTY_TRX_PK";

    /** db column : INVTY_TRX_DT. */
    public static final String INVTY_TRX_DT = "INVTY_TRX_DT";

    /** db column : INVTY_TRX_QTY. */
    public static final String INVTY_TRX_QTY = "INVTY_TRX_QTY";

    /** db column : SHIP_TO_GLBL_CMPY_CD. */
    public static final String SHIP_TO_GLBL_CMPY_CD = "SHIP_TO_GLBL_CMPY_CD";

    /** db column : INVTY_GLBL_CMPY_CD. */
    public static final String INVTY_GLBL_CMPY_CD = "INVTY_GLBL_CMPY_CD";

    /** db column : FSR_GLBL_CMPY_CD. */
    public static final String FSR_GLBL_CMPY_CD = "FSR_GLBL_CMPY_CD";

    /** db column : DS_COND_CONST_VAL_TXT_01. */
    public static final String DS_COND_CONST_VAL_TXT_01 = "DS_COND_CONST_VAL_TXT_01";

    /** db column : DS_COND_CONST_VAL_TXT_02. */
    public static final String DS_COND_CONST_VAL_TXT_02 = "DS_COND_CONST_VAL_TXT_02";

    /** db column : DS_COND_CONST_VAL_TXT_03. */
    public static final String DS_COND_CONST_VAL_TXT_03 = "DS_COND_CONST_VAL_TXT_03";

    /** db column : DS_COND_CONST_VAL_TXT_04. */
    public static final String DS_COND_CONST_VAL_TXT_04 = "DS_COND_CONST_VAL_TXT_04";

    /** db column : DS_COND_CONST_VAL_TXT_05. */
    public static final String DS_COND_CONST_VAL_TXT_05 = "DS_COND_CONST_VAL_TXT_05";

    /** db column : WO_INVTY_LOC_TP_CD. */
    public static final String WO_INVTY_LOC_TP_CD = "WO_INVTY_LOC_TP_CD";

    /** db column : WO_INVTY_RTL_WH_CD. */
    public static final String WO_INVTY_RTL_WH_CD = "WO_INVTY_RTL_WH_CD";

    /** db column : WO_INVTY_WH_OWNR_CD. */
    public static final String WO_INVTY_WH_OWNR_CD = "WO_INVTY_WH_OWNR_CD";

    /** db column : CARR_FRT_CHRG_BU_CD. */
    public static final String CARR_FRT_CHRG_BU_CD = "CARR_FRT_CHRG_BU_CD";

    /** db column : INVTY_SHPG_SVC_LVL_CD. */
    public static final String INVTY_SHPG_SVC_LVL_CD = "INVTY_SHPG_SVC_LVL_CD";

    /** db column : CPO_SHPG_SVC_LVL_CD. */
    public static final String CPO_SHPG_SVC_LVL_CD = "CPO_SHPG_SVC_LVL_CD";

    /** db column : CPO_CARR_CD. */
    public static final String CPO_CARR_CD = "CPO_CARR_CD";

    /** db column : SHPG_SHPG_SVC_LVL_CD. */
    public static final String SHPG_SHPG_SVC_LVL_CD = "SHPG_SHPG_SVC_LVL_CD";

    /** db column : SHPG_TRD_PTNR_SHPG_METH_CD. */
    public static final String SHPG_TRD_PTNR_SHPG_METH_CD = "SHPG_TRD_PTNR_SHPG_METH_CD";

    /** db column : WO_SHPG_SVC_LVL_CD. */
    public static final String WO_SHPG_SVC_LVL_CD = "WO_SHPG_SVC_LVL_CD";

    /** db column : WO_TRD_PTNR_SHPG_METH_CD. */
    public static final String WO_TRD_PTNR_SHPG_METH_CD = "WO_TRD_PTNR_SHPG_METH_CD";

    /** db column : WO_RCV_INVTY_LOC_CD. */
    public static final String WO_RCV_INVTY_LOC_CD = "WO_RCV_INVTY_LOC_CD";

    /** db column : WO_RCV_INVTY_LOC_TP_CD. */
    public static final String WO_RCV_INVTY_LOC_TP_CD = "WO_RCV_INVTY_LOC_TP_CD";

    /** db column : GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** db column : CINC_GLBL_CMPY_CATG_CD. */
    public static final String CINC_GLBL_CMPY_CATG_CD = "CINC_GLBL_CMPY_CATG_CD";

    /** STring : 1 */
    public static final String STR_1 = "1";

    /** STring : 2 */
    public static final String STR_2 = "2";

    /** INDEX : 0 */
    public static final int IDX_0 = 0;

    /** INDEX : 1 */
    public static final int IDX_1 = 1;

    /** INDEX : 2 */
    public static final int IDX_2 = 2;

    /** INDEX : 3 */
    public static final int IDX_3 = 3;

    /** INDEX : 4 */
    public static final int IDX_4 = 4;

    /** INDEX : 5 */
    public static final int IDX_5 = 5;

    /** INDEX : 6 */
    public static final int IDX_6 = 6;

    /** INDEX : 7 */
    public static final int IDX_7 = 7;

    /** INDEX : 8 */
    public static final int IDX_8 = 8;

    /** INDEX : 9 */
    public static final int IDX_9 = 9;

    /** INDEX : 10 */
    public static final int IDX_10 = 10;

    /** EFF_FROM_DT_DEFALUT */
    public static final String EFF_FROM_DT_DEFALUT = "99991231";

    /** NUM:1 */
    public static final int NUM_ONE = 1;

    /** Variable Character Const Key (SCUBE_IF_CUSA_VND_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD = "SCUBE_IF_CUSA_VND_CD";

    /** Variable Character Const Key (SCUBE_IF_STK_OUT) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_IF_STK_OUT = "SCUBE_IF_STK_OUT";

    /** Variable Character Const Key (SCUBE_VND_SYS_TP_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD = "SCUBE_VND_SYS_TP_CD";

    // QC#26966 Add.
    /** Variable Character Const Key (CINC_GLBL_WH_CD_ITASC) */
    public static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_ITASC = "CINC_GLBL_WH_CD_ITASC";
    // END 05/25/2016 R.Shimamoto [V2.0]

    // QC#30964 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST = "SCUBE_EXCL_MDSE_CD_LIST";

    // QC#55572 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST = "SCUBE_EXCL_SWH_CD_LIST";
}
