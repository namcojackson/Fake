/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB108001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * S-Cube Parts Master Info to CINC (WWABF301/311) (Work Create)<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/18/2013   Hitachi         K.Kasai         Created         Created
 * 06/23/2016   CITS            N.Akaishi       Update          V1.0
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * </pre>
 */
public class NMAB108001Constant {

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: CommitUnit */
    public static final String MSG_ITEM_COMMIT_UNIT = "Number of Commit Unit";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** Data delete failure. (table name is [@]) */
    public static final String NPAM1342E = "NPAM1342E";

    /** VALUE:PRT_ASSET_CD */
    public static final String VALUE_PRT_ASSET_CD = "B";

    /** KEY:INTFC_CCY_CD */
    public static final String KEY_INTFC_CCY_CD = "INTFC_CCY_CD";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** LENGTH:INTFC_PRT_MDSE_CD */
    public static final int INTFC_PRT_MDSE_CD_LENGTH = 12;

    /** LENGTH:INTFC_MDSE_CD */
    public static final int INTFC_MDSE_CD_LENGTH = 15;

    /** LENGTH:INTFC_PRT_SALE_DT */
    public static final int INTFC_PRT_SALE_DT_LENGTH = 8;

    /** */
    public static final String VALUE_PRT_SIZE_TXT = "000";

    /** */
    public static final String VALUE_CINC_PROD_CD = "Z9";

    /** VAR_CHAR_CONST KEY : SCUBE_VND_SYS_TP_CD */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD = "SCUBE_VND_SYS_TP_CD";

    /** DS_COND_CONST CLASS ID : SCUBE_IF_CUSA_VND_CD */
    public static final String DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD = "SCUBE_IF_CUSA_VND_CD";

    // QC#30964 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST = "SCUBE_EXCL_MDSE_CD_LIST";

    /** EFF_FROM_DT_DEFALUT */
    public static final String EFF_FROM_DT_DEFALUT = "99991231";

    /** INDEX : 1 */
    public static final int IDX_1 = 1;

    /** INTFC_CCY_CD DEFALUT */
    public static final String INTFC_CCY_CD_DEFALUT = "USD";

    /** PRT_MAIN_PROD_CD DEFALUT */
    public static final BigDecimal PRT_MAIN_PROD_CD_DEFALUT = new BigDecimal(2);

    /** NUM_CONST Key : WH_CONV_PROD_CTRL_NUM */
    public static final String PRT_MAIN_PROD_CTRL_LAYER_NUM = "PRT_MAIN_PROD_CTRL_LAYER_NUM";

    /** Minimum PRT_MAIN_PROD_CTRL_LAYER_NUM Value */
    public static final BigDecimal MIN_PRT_MAIN_PROD_CTRL_LAYER_NUM = BigDecimal.ZERO;

    /** Maximum PRT_MAIN_PROD_CTRL_LAYER_NUM Value */
    public static final BigDecimal MAX_PRT_MAIN_PROD_CTRL_LAYER_NUM = new BigDecimal(7);

    /** PROD_CTRL_CD Array */
    public static final String[] PROD_CTRL_CD_ARRY = new String[] {"ZEROTH_PROD_CTRL_CD", "FIRST_PROD_CTRL_CD", "SCD_PROD_CTRL_CD", "THIRD_PROD_CTRL_CD", "FRTH_PROD_CTRL_CD", "FIFTH_PROD_CTRL_CD", "SIXTH_PROD_CTRL_CD", "SVNTH_PROD_CTRL_CD" };

    /** Table Name : PRT_MSTR_WRK */
    public static final String TBL_PRT_MSTR_WRK = "PRT_MSTR_WRK";

    /** Constant value GET_MDSE_DATA_FIND_BY_INTFC_CRAT_DT */
    public static final String STMT_GET_MDSE_DATA_FIND_BY_INTFC_CRAT_DT = "getMdseDataFindByIntfcCratDt";

    /** Constant value GET_PAST_DATA */
    public static final String STMT_GET_PAST_DATA = "getPastData";

    /** Constant value GET_PRE_INTFC_CRAT_DT */
    public static final String STMT_GET_PRE_INTFC_CRAT_DT = "getPreIntfcCratDt";

    /** Constant value GET_MDSE_INFO */
    public static final String STMT_GET_MDSE_INFO = "getMdseInfo";

    /** Constant value INTFC_CRAT_DT */
    public static final String BIND_INTFC_CRAT_DT = "intfcCratDt";

    /** Constant value GLBL_CMPY_CD */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** Constant value PRE_INTFC_CRAT_DT */
    public static final String BIND_PRE_INTFC_CRAT_DT = "preIntfcCratDt";

    /** Constant value RGTN_STS_CD_LIST */
    public static final String BIND_RGTN_STS_CD_LIST = "rgtnStsCdList";

    /** Constant value VND_SYS_TP_CD */
    public static final String BIND_VND_SYS_TP_CD = "vndSysTpCdList";

    /** Constant value EFF_FROM_DT_DEFAULT */
    public static final String BIND_EFF_FROM_DT_DEFAULT = "effFromDtDefalut";

    /** Constant value IDX_1 */
    public static final String BIND_IDX_1 = "IDX_1";

    /** Constant value DS_COND_CONST_GRP_ID_CUSA_VND_CD */
    public static final String BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD = "dsCondConstGrpIdCusaVndCd";

    /** Constant value P_CODE_PROD_LINE_VENDOR */
    public static final String P_CODE_PROD_LINE_VENDOR = "P_CODE_PROD_LINE_VENDOR";

    /** Constant value MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Constant value MDSE_NM */
    public static final String MDSE_NM = "MDSE_NM";

    /** Constant value DSCTN_FLG */
    public static final String DSCTN_FLG = "DSCTN_FLG";

    /** Constant value THIS_MTH_TOT_STD_COST_AMT */
    public static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** Constant value VND_CD */
    public static final String VND_CD = "VND_CD";

    /** Constant value GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Constant value VND_INCR_ORD_QTY */
    public static final String VND_INCR_ORD_QTY = "VND_INCR_ORD_QTY";

    /** Constant value VND_MIN_ORD_QTY */
    public static final String VND_MIN_ORD_QTY = "VND_MIN_ORD_QTY";

    /** Constant value EZINTIME */
    public static final String EZINTIME = "EZINTIME";

    /** Constant value INTFC_PRT_MDSE_CD */
    public static final String INTFC_PRT_MDSE_CD = "INTFC_PRT_MDSE_CD";

    /** Constant value PRT_SIZE_TXT */
    public static final String PRT_SIZE_TXT = "PRT_SIZE_TXT";

    /** Constant value INTFC_CRAT_DT */
    public static final String INTFC_CRAT_DT = "INTFC_CRAT_DT";

    /** Constant value CUSA_VND_CD */
    public static final String CUSA_VND_CD = "CUSA_VND_CD";
}
