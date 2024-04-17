/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7040.constant;

/**
 *<pre>
 * Price List Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRA             E.Inada         Create          N/A
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/11/17   Fujitsu         N.Sugiura       Create          QC#29147
 * 2018/11/30   Fujitsu         K.Kato          Update          QC#29319
 *</pre>
 */
public interface NMAL7040Constant {

    /** Business ID */
    String BIZ_ID = "NMAL7040";

    /** Message Kind error */
    String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /* ------ Parameter Number ------ */
    int FILTER_PRC_LIST_STRU_TP_CD = 0;

    int FILTER_PRC_CATG_CD = 1;

    int FILTER_PRC_CATG_NM = 2;

    int FILTER_PRC_QLFY_TP_CD = 3;

    int FILTER_PRC_QLFY_VAL_TXT = 4;

    int FILTER_MDL_NM = 5;

    int FILTER_PRC_MTR_PKG_NM = 6;

    int FILTER_PRC_PGM_TP_CD = 7;

    int FILTER_PRC_EQUIP_TP_CD = 8;

    int FILTER_SVC_SEG_CD = 9;

    int FILTER_PRC_IN_OUT_RG_CD = 10;

    int FILTER_PRC_LIST_EQUIP_CONFIG_NUM = 11;

    int FILTER_PRC_LIST_EQUIP_CONFIG_NM = 12;

    int FILTER_PKG_UOM_CD = 13;

    int FILTER_PRC_RATE_TP_CD = 14;

    int FILTER_PRC_SVC_PLN_TP_CD = 15;

    int FILTER_PRC_SVC_CONTR_TP_CD = 16;

    int FILTER_MTR_LB_NM = 17;
    // 2018/11/17 QC#29147 Mod Start
    // int FILTER_PRC_LIST_BAND_CD = 18;
    int FILTER_PRC_LIST_BAND_DESC_TXT = 18;
    // 2018/11/17 QC#29147 Mod End

    int FILTER_PRC_SVC_TIER_TP_CD = 19;

    int FILTER_SPLY_AGMT_PLN_NM = 20;

    int FILTER_MDSE_CD = 21;

    int FILTER_PRC_ADDL_CHRG_TP_CD = 22;

    int FILTER_MKT_MDL_SEG_CD = 23;

    int FILTER_PRC_LEASE_CMPY_ABBR_NM = 24;

    int FILTER_DS_ACCT_NM = 25;

    // Mod Start 2018/07/17 QC#20267
    int FILTER_MNF_ITEM_CD = 26;

    int FILTER_MDSE_DESC_SHORT_TXT = 27;

    int FILTER_COA_MDSE_TP_NM = 28;

    int FILTER_MDSE_ITEM_TP_NM = 29;

    int FILTER_COA_PROD_NM = 30;

    int FILTER_T_MDL_NM = 31;

    int FILTER_ZEROTH_PROD_CTRL_CD = 32;

    int FILTER_FIRST_PROD_CTRL_CD = 33;

    int FILTER_SCD_PROD_CTRL_CD = 34;

    int FILTER_THIRD_PROD_CTRL_CD = 35;

    int FILTER_FRTH_PROD_CTRL_CD = 36;

    int FILTER_PRC_TERM_AOT = 37;

    int FILTER_PRC_TERM_UOM_CD = 38;

    int FILTER_CUST_BID_QTY = 39;

    int FILTER_PRC_FMLA_NM = 40;

    int FILTER_OPEN_MKT_FLG = 41;

    int FILTER_COMP_CD = 42;

    int FILTER_TERM_FROM_MTH_AOT = 43;

    int FILTER_TERM_THRU_MTH_AOT = 44;

    int FILTER_PRC_SVC_ZONE_CD = 45;

    int FILTER_MDSE_NM = 46;

    int FILTER_XX_YES_NO_CD = 47;

    int FILTER_PRC_LIST_MDSE_CD = 48;

    int FILTER_EFF_FROM_DT_H1 = 49;

    int FILTER_EFF_FROM_DT_H2 = 50;

    int FILTER_EFF_THRU_DT_H1 = 51;

    int FILTER_EFF_THRU_DT_H2 = 52;

    int FILTER_XX_FULL_NM_H1 = 53;

    int FILTER_XX_FULL_NM_H2 = 54;

    int FILTER_CRAT_DT_H1 = 55;

    int FILTER_CRAT_DT_H2 = 56;

    int FILTER_LAST_UPD_DT_H1 = 57;

    int FILTER_LAST_UPD_DT_H2 = 58;

    int FILTER_MAX_PARAM_NUM = 59;
    // Mod End 2018/07/17 QC#20267

    /* ------ Popup ------- */
    /** Common Pop-up Parameter/Where */
    int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Where */
    int CMN_PRM_COLUMN_NUM = 4;

    // 2018/11/30 QC#29319 Add Start
    /** The number of data for @ exceeded the maximum[@]. */
    String NMAM8696E = "NMAM8696E";
    
    /** @ exceed the maximum length. */
    String NMAM8579E = "NMAM8579E";

    /** String COMMA */
    String COMMA = ",";

    /** Max Input Data Count */
    int MAX_INPUT_DATA_CNT = 99;

    /** Max Input Data Count */
    int MAX_INPUT_DATA_LENGTH = 4000;

    /** Common Pop-up Parameter/Where */
    int MAX_LENGTH_NMAL6050_QLFY_VAL = 20;

    /** Common Pop-up Parameter/Where */
    int MAX_LENGTH_NMAL6800_QLFY_VAL = 16;

    /** Common Pop-up Parameter/Where */
    int MAX_LENGTH_NWAL1130_QLFY_VAL = 50;

    // 2018/11/30 QC#29319 Add End

}
