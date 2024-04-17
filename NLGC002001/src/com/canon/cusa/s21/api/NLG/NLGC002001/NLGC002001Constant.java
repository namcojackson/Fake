/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLG.NLGC002001;

import java.math.BigDecimal;

/**
 * <pre>
 * Mdse Common Program of ALG (MW Replace)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/13/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 02/07/2017   CITS            Y.Fujii         Update          QC#17422
 *</pre>
 */
public interface NLGC002001Constant {

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    String NLGM0044E = "NLGM0044E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0046E = "NLGM0046E";

    /** DB Table: MDSE */
    String TBL_MDSE = "MDSE";

    /** DB Table: INTG_PROD_CATG */
    String TBL_INTG_PROD_CATG = "INTG_PROD_CATG";

    /** DB Table: FRT_CLS */
    String TBL_FRT_CLS = "FRT_CLS";

    /** DB Table: WMS_INBD_ITEM_WRK */
    String TBL_WMS_INBD_ITEM_WRK = "WMS_INBD_ITEM_WRK";

    /** DB Table: WMS_INBD_ITEM_UPC_WRK */
    String TBL_WMS_INBD_ITEM_UPC_WRK = "WMS_INBD_ITEM_UPC_WRK";

    /** DB Table: WMS_INBD_ITEM_SER_WRK */
    String TBL_WMS_INBD_ITEM_SER_WRK = "WMS_INBD_ITEM_SER_WRK";

    /** DB Table: WMS_MDSE_LIST */
    String TBL_WMS_MDSE_LIST = "WMS_MDSE_LIST";

    /** DB Column: GLBL_CMPY_CD */
    String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Column: WMS_ITEM_CD */
    String COL_WMS_ITEM_CD = "WMS_ITEM_CD";

    /** DB Column: WMS_UPC_CD */
    String COL_WMS_UPC_CD = "WMS_UPC_CD";

    /** DB Column: WH_CD */
    String COL_WH_CD = "WH_CD";

    /** DB Column: MDSE_CD */
    String COL_MDSE_CD = "MDSE_CD";

    /** DB Column: WMS_MDSE_CD */
    String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** DB Column: EZUPTIME */
    String COL_EZUPTIME = "EZUPTIME";

    /** DB Column: INTG_PROD_CATG_CD */
    String COL_INTG_PROD_CATG_CD = "INTG_PROD_CATG_CD";

    /** DB Column: FRT_CLS_CD */
    String COL_FRT_CLS_CD = "FRT_CLS_CD";

    /** DB Column: FMT_LG_SER_NUM */
    String COL_FMT_LG_SER_NUM = "FMT_LG_SER_NUM";

    /** DB Column: LG_SER_NUM */
    String COL_LG_SER_NUM = "LG_SER_NUM";

    /** DB Column: NUM_RNG */
    String COL_NUM_RNG = "NUM_RNG";

    /** DB Column: MIN_LEN */
    String COL_MIN_LEN = "MIN_LEN";

    /** DB Column: MAX_LEN */
    String COL_MAX_LEN = "MAX_LEN";

    /** DB Column: SER_NUM_DEF_FLG */
    String COL_SER_NUM_DEF_FLG = "SER_NUM_DEF_FLG";

    /** DB Column: FROM_SER_NUM */
    String COL_FROM_SER_NUM = "FROM_SER_NUM";

    /** DB Column: THRU_SER_NUM */
    String COL_THRU_SER_NUM = "THRU_SER_NUM";

    /** DB Column: MDSE_SER_NUM_RNG_PK */
    String COL_MDSE_SER_NUM_RNG_PK = "MDSE_SER_NUM_RNG_PK";

    /** DB Column: FMT_MIN_LEN */
    String COL_FMT_MIN_LEN = "FMT_MIN_LEN";

    /** DB Column: FMT_MAX_LEN */
    String COL_FMT_MAX_LEN = "FMT_MAX_LEN";

    /** DB Column: WML_MDSE_CD */
    String COL_WML_MDSE_CD = "WML_MDSE_CD";

    /** DB Column: WML_WH_CD */
    String COL_WML_WH_CD = "WML_WH_CD";

    /** DB Column: WML_EZUPTIME */
    String COL_WML_EZUPTIME = "WML_EZUPTIME";

    /** DB Column: M_MDSE_CD */
    String COL_M_MDSE_CD = "M_MDSE_CD";

    /** DB Column: UPC_CD */
    String COL_UPC_CD = "UPC_CD";

    /** DB Column: MDSE_NM */
    String COL_MDSE_NM = "MDSE_NM";

    /** DB Column: VND_CD */
    String COL_VND_CD = "VND_CD";

    /** DB Column: M_FRT_CLS_CD */
    String COL_M_FRT_CLS_CD = "M_FRT_CLS_CD";

    /** DB Column: SHPG_SER_TAKE_FLG */
    String COL_SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";

    /** DB Column: THIS_MTH_TOT_STD_COST_AMT */
    String COL_THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** DB Column: NEXT_MTH_TOT_STD_COST_AMT */
    String COL_NEXT_MTH_TOT_STD_COST_AMT = "NEXT_MTH_TOT_STD_COST_AMT";

    /** DB Column: SCD_PROD_CTRL_CD */
    String COL_SCD_PROD_CTRL_CD = "SCD_PROD_CTRL_CD";

    /** DB Column: THIRD_PROD_CTRL_CD */
    String COL_THIRD_PROD_CTRL_CD = "THIRD_PROD_CTRL_CD";

    /** DB Column: FRTH_PROD_CTRL_CD */
    String COL_FRTH_PROD_CTRL_CD = "FRTH_PROD_CTRL_CD";

    /** DB Column: FIFTH_PROD_CTRL_CD */
    String COL_FIFTH_PROD_CTRL_CD = "FIFTH_PROD_CTRL_CD";

    /** DB Column: COA_PROD_CD */
    String COL_COA_PROD_CD = "COA_PROD_CD";

    /** DB Column: ORIG_CTRY_CD */
    String COL_ORIG_CTRY_CD = "ORIG_CTRY_CD";

    /** DB Column: INVTY_CTRL_FLG */
    String COL_INVTY_CTRL_FLG = "INVTY_CTRL_FLG";

    /** DB Column: MDSE_FML_NM */
    String COL_MDSE_FML_NM = "MDSE_FML_NM";

    /** DB Column: M_INTG_PROD_CATG_CD */
    String COL_M_INTG_PROD_CATG_CD = "M_INTG_PROD_CATG_CD";

    /** DB Column: TRF_CD */
    String COL_TRF_CD = "TRF_CD";

    /** DB Column: IPC_INTG_PROD_CATG_CD */
    String COL_IPC_INTG_PROD_CATG_CD = "IPC_INTG_PROD_CATG_CD";

    /** DB Column: INTG_PROD_CATG_NM */
    String COL_INTG_PROD_CATG_NM = "INTG_PROD_CATG_NM";

    /** DB Column: FC_FRT_CLS_CD */
    String COL_FC_FRT_CLS_CD = "FC_FRT_CLS_CD";

    /** DB Column: NMFC_CLS_NUM */
    String COL_NMFC_CLS_NUM = "NMFC_CLS_NUM";

    /** DB Column: NMFC_ITEM_NUM */
    String COL_NMFC_ITEM_NUM = "NMFC_ITEM_NUM";

    /** DB Column: NMFC_ITEM_SUB_NUM */
    String COL_NMFC_ITEM_SUB_NUM = "NMFC_ITEM_SUB_NUM";

    /** DB Column: NMFC_ITEM_DESC_TXT */
    String COL_NMFC_ITEM_DESC_TXT = "NMFC_ITEM_DESC_TXT";

    /** DB Column: MSI_MDSE_CD */
    String COL_MSI_MDSE_CD = "MSI_MDSE_CD";

    /** DB Column: CRTN_PER_LAYER_QTY */
    String COL_CRTN_PER_LAYER_QTY = "CRTN_PER_LAYER_QTY";

    /** DB Column: CRTN_TIER_PER_PLT_QTY */
    String COL_CRTN_TIER_PER_PLT_QTY = "CRTN_TIER_PER_PLT_QTY";

    /** DB Column: MI_MDSE_CD */
    String COL_MI_MDSE_CD = "MI_MDSE_CD";

    /** DB Column: BAT_NUM_CTRL_FLG */
    String COL_BAT_NUM_CTRL_FLG = "BAT_NUM_CTRL_FLG";

    /** DB Column: HAZ_MAT_FLG */
    String COL_HAZ_MAT_FLG = "HAZ_MAT_FLG";

    /** DB Column: HAZ_MAT_CD */
    String COL_HAZ_MAT_CD = "HAZ_MAT_CD";

    /** DB Column: HAZ_CLS_NM */
    String COL_HAZ_CLS_NM = "HAZ_CLS_NM";

    /** DB Column: SFTY_HAZ_ID_NUM */
    String COL_SFTY_HAZ_ID_NUM = "SFTY_HAZ_ID_NUM";

    /** DB Column: LOT_CTRL_FLG */
    String COL_LOT_CTRL_FLG = "LOT_CTRL_FLG";

    /** Value of INTFC_TP_ID : 03 */
    String VAL_INTFC_TP_ID_03 = "03";

    /** Value of INTFC_REC_TP_ID : 1 */
    String VAL_INTFC_REC_TP_ID_1 = "1";

    /** Value of INTFC_REC_TP_ID : 2 */
    String VAL_INTFC_REC_TP_ID_2 = "2";

    /** Value of INTFC_REC_TP_ID : 3 */
    String VAL_INTFC_REC_TP_ID_3 = "3";

    /** Value of WMS_CMPY_CD : 01 */
    String VAL_WMS_CMPY_CD_01 = "01";

    /** Value of PKG_UOM_CD : EA */
    String VAL_PKG_UOM_CD_EA = "EA";

    /** Value of PKG_UOM_CD : CA */
    String VAL_PKG_UOM_CD_CA = "CA";

    /** Value of PKG_UOM_CD : PL */
    String VAL_PKG_UOM_CD_PL = "PL";

    /** Value of INTFC_REC_ACT_CD : A */
    String VAL_INTFC_REC_ACT_CD_ADD = "A";

    /** Value of INTFC_REC_ACT_CD : C */
    String VAL_INTFC_REC_ACT_CD_CHNG = "C";

    /** Value of HYPHEN */
    String VAL_HYPHEN = "-";

    /** Value of Serial Length : 00 */
    String VAL_SER_LEN_00 = "00";

    /** Value of Serial Type : S */
    String VAL_SER_TP_S = "S";

    /** Value of format : 000 */
    String FMT_000 = "000";

    /** Value of QTY_PKG_APVL_STS_CD : SA */
    String VAL_QTY_PKG_APVL_STS_CD_SA = "SA";

    /** Value of QTY_PKG_APVL_STS_CD : SU */
    String VAL_QTY_PKG_APVL_STS_CD_SU = "SU";

    /** Format for LG_SER_NUM : FM09 */
    String FMT_LG_SER_NUM_FM09 = "FM09";

    /** Size of FROM_SER_NUM */
    int LG_CUT_FROM_SER_NUM_TO = 15;

    /** Size of TO_SER_NUM */
    int LG_CUT_TO_SER_NUM_TO = 15;

    /** Size of substring from number for FIFTH_PROD_CTRL_CD */
    int LG_CUT_FIFTH_PROD_CTRL_CD_FROM = 0;

    /** Size of substring to number for FIFTH_PROD_CTRL_CD */
    int LG_CUT_FIFTH_PROD_CTRL_CD_TO = 1;

    /** Size of substring from number for MNF_ITEM_DESC_TXT_01 */
    int LG_CUT_MNF_ITEM_DESC_TXT_01_FROM = 0;

    /** Size of substring to number for MNF_ITEM_DESC_TXT_01 */
    int LG_CUT_MNF_ITEM_DESC_TXT_01_TO = 33;

    /** Size of substring from number for MNF_ITEM_DESC_TXT_02 */
    int LG_CUT_MNF_ITEM_DESC_TXT_02_FROM = 33;

    /** Size of substring to number for MNF_ITEM_DESC_TXT_02 */
    int LG_CUT_MNF_ITEM_DESC_TXT_02_TO = 66;

    /** Size of substring from number for MDSE_DESC_TXT_02 */
    int LG_CUT_MDSE_DESC_TXT_02_FROM = 0;

    /** Size of substring to number for MDSE_DESC_TXT_02 */
    int LG_CUT_MDSE_DESC_TXT_02_TO = 34;

    /** Size of Serial Type judge */
    int LG_SER_TP_JUDGE = 4;

    /** Size of IN_INCH_VOL devide num : 1728 */
    BigDecimal IN_INCH_VOL_DEVIDE_NUM = new BigDecimal(1728);

    /** Size of IN_INCH_VOL number of decimal places : 4 */
    int IN_INCH_VOL_NUM_OF_DECL_PLACE = 4;

    /** Size of IN_INCH number of decimal places : 2 */
    String IN_INCH_NUM_OF_DECL_PLACE = "2";

    /** Default WMS_MDSE_DESC_TXT_01 Length : 35 */
    int DEF_WMS_MDSE_DESC_TXT_01_LEN = 35;

    /** Default WMS_MDSE_DESC_TXT_02 Length : 35 */
    int DEF_WMS_MDSE_DESC_TXT_02_LEN = 35;

    /** DB Column Attribute: curStdCostNum */
    String ATT_CUR_STD_COST_NUM = "curStdCostNum";

    /** DB Column Attribute: futStdCostNum */
    String ATT_FUT_STD_COST_NUM = "futStdCostNum";

    /** Value PADING */
    String VAL_PADING = "9";

    /** Value PADING_SCALE */
    String VAL_PADING_SCALE = ".99";

    /** Value . */
    String VAL_ESC_PERIOD = "\\.";
}
