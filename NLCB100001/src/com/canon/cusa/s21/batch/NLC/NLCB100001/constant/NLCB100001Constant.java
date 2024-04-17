/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB100001.constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;

/**
 * <pre>
 * S-CUBE Inv. Master Info to CINC (WWABF304/312)<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/18/2013   Hitachi         K.Kishimoto     Created         Created
 * 11/20/2013   Hitachi         K.Kishimoto     Update          QC3148
 * 05/16/2016   CITS            R.Shimamoto     Update          V2.0
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 01/30/2020   Fujitsu         R.Nakamura      Update          QC#55572
 * </pre>
 */
public class NLCB100001Constant {

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Data delete failure. (table name is [@]) */
    public static final String NLCM0131E = "NLCM0131E";

    /** It failed to register [@]. */
    public static final String NLAM1295E = "NLAM1295E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: CommitUnit */
    public static final String MSG_ITEM_COMMIT_UNIT = "Number of Commit Unit";

    /** KEY:INTFC_CCY_CD */
    public static final String KEY_INTFC_CCY_CD = "INTFC_CCY_CD";

    /** VALUE_CINC_GLBL_WH_CD:N/A */
    public static final String VALUE_CINC_GLBL_WH_CD = "N/A";

    // START 11/20/2013 K.Kishimoto [QC3148, MOD]
    /** VALUE_PRT_INVTY_CATG_CD:A1 */
    public static final String VALUE_PRT_INVTY_CATG_CD_A1 = "A1";

    /** VALUE_PRT_INVTY_CATG_CD:C */
    public static final String VALUE_PRT_INVTY_CATG_CD_C = "C";

    // QC#26966 Add.
    /** VALUE_PRT_INVTY_CATG_CD:AZ */
    public static final String VALUE_PRT_INVTY_CATG_CD_AZ = "AZ";

    // END 11/20/2013 K.Kishimoto [QC3148, MOD]

    /** VALUE_PRT_CHRG_IND:C */
    public static final String VALUE_PRT_CHRG_IND = "C";

    /** VALUE_INTFC_QTY_SGN_TXT_PLUS:+ */
    public static final String VALUE_INTFC_QTY_SGN_TXT_PLUS = "+";

    /** VALUE_INTFC_QTY_SGN_TXT_MINUS:- */
    public static final String VALUE_INTFC_QTY_SGN_TXT_MINUS = "-";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    // START 11/20/2013 K.Kishimoto [QC3148, ADD]
    /** LOC_STS_CD_LST */
    public static final String[] LOC_STS_CD_LST = new String[] {LOC_STS.IN_SHED, LOC_STS.DC_STOCK, LOC_STS.LOSS_ON_RECEIVING, LOC_STS.LOSS_ON_SHIPMENT, LOC_STS.INSURANCE_CLAIM_DAMAGED, LOC_STS.WORK_IN_PROCESS_COMPONENT,
            LOC_STS.WORK_IN_PROCESS_KIT, LOC_STS.WORK_IN_PROCESS_ORIGINAL, LOC_STS.WORK_IN_PROCESS_REFURBISHED, LOC_STS.IN_TRANSIT_WH };

    /** LOC_TP_CD_LST_TRNST_FLG_Y_DEFAULT */
    public static final String[] LOC_TP_CD_LST_TRNST_FLG_Y_DEFAULT = new String[] {LOC_TP.WAREHOUSE, LOC_TP.VENDOR, LOC_TP.INBOUND_CONSIGNEE };

    /** LOC_TP_CD_LST_TRNST_FLG_Y_INCL_TECH */
    public static final String[] LOC_TP_CD_LST_TRNST_FLG_Y_INCL_TECH = new String[] {LOC_TP.WAREHOUSE, LOC_TP.VENDOR, LOC_TP.INBOUND_CONSIGNEE, LOC_TP.TECHNICIAN };

    /** LOC_TP_CD_LST_TRNST_FLG_N_DEFAULT */
    public static final String[] LOC_TP_CD_LST_TRNST_FLG_N_DEFAULT = new String[] {LOC_TP.WAREHOUSE, LOC_TP.VENDOR };

    /** LOC_TP_CD_LST_TRNST_FLG_N_INCL_TECH */
    public static final String[] LOC_TP_CD_LST_TRNST_FLG_N_INCL_TECH = new String[] {LOC_TP.WAREHOUSE, LOC_TP.VENDOR, LOC_TP.TECHNICIAN };

    /** [@] is mandatory. */
    public static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Entered @ is invalid. */
    public static final String MSG_ID_NLCM0065E = "NLCM0065E";

    // END 11/20/2013 K.Kishimoto [QC3148, ADD]
    // STR 05/16/2016 R.Shimamoto [V2.0 ADD]
    /** EFF_FROM_DT_DEFALUT */
    public static final String EFF_FROM_DT_DEFALUT = "99991231";

    /** RTL_WH_CD_AST */
    public static final String RTL_WH_CD_AST = "*";

    /** WH_OWNR_CD_AST */
    public static final String WH_OWNR_CD_AST = "*";

    /** LOC_TP_CD_AST */
    public static final String LOC_TP_CD_AST = "*";

    /** Variable Character Const Key (SCUBE_IF_CUSA_VND_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD = "SCUBE_IF_CUSA_VND_CD";

    /** Variable Character Const Key (SCUBE_VND_SYS_TP_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD = "SCUBE_VND_SYS_TP_CD";

    /** In-Transit (WH) SCE Order Type Code (SCUBE_IF_IN_TRNST) */
    public static final String DS_COND_CONST_GRP_ID_SCUBE_IF_IN_TRNST = "SCUBE_IF_IN_TRNST";

    // QC#26966 Add.
    /** Variable Character Const Key (CINC_GLBL_WH_CD_CLMBUS) */
    public static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS = "CINC_GLBL_WH_CD_CLMBUS";

    /** NUM_ONE:1 */
    public static final int NUM_ONE = 1;
    // END 05/16/2016 R.Shimamoto [V2.0 ADD]

    // QC#30964 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST = "SCUBE_EXCL_MDSE_CD_LIST";

    // Add Start 2020/01/30 QC#55572
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST = "SCUBE_EXCL_SWH_CD_LIST";
    // Add End 2020/01/30 QC#55572
}
