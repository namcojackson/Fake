/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB302001.constant;

/**
 * <pre>
 * Receive Invoice Information for CUSA Domestic Batch 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2012   Fujitsu         Y.Mori          Create          N/A
 * 02/05/2013   Fujitsu         M.Takahashi     Update          WDS Defect#347
 * 02/12/2013   Fujitsu         S.Iidaka        Update          WDS Defect#693
 * 03/28/2013   Hitachi         T.Aoyagi        Update          QC817
 * 04/15/2013   Hitachi         K.Kishimoto     Update          QC1033,QC1057
 * 05/02/2013   CSAI            K.Lee           Update          QC1180
 * 05/14/2013   Hitachi         K.Kishimoto     Update          QC1202
 * 05/21/2013   Hitachi         K.Kishimoto     Update          QC1217
 * 06/18/2013   Hitachi         K.Kishimoto     Update          QC1233,QC1261
 * 03/25/2014   Hitachi         K.Kishimoto     Update          CSA-028
 * 03/14/2016   CITS            R.Shimamoto     Update          V2.0
 * 08/22/2016   CITS            T.Wada          Update          QC#8660
 * 09/27/2016   CITS            R.Shimamoto     Update          QC#14714
 * 01/10/2020   Fujitsu         M.Ishii         Update          QC#55137
</pre>
 */
public class NLAB302001Constant {

    // ssm parameter map
    /** map key : glblCmpyCd. */
    public static final String MAP_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    // db column
    /** db column : VND_INV_NUM. */
    public static final String VND_INV_NUM = "VND_INV_NUM";

    /** db column : VND_INV_BOL_LINE_NUM. */
    public static final String VND_INV_BOL_LINE_NUM = "VND_INV_BOL_LINE_NUM";

    /** db column : VND_INV_LINE_NUM. */
    public static final String VND_INV_LINE_NUM = "VND_INV_LINE_NUM";

    /** db column : VND_INV_LINE_SUB_NUM. */
    public static final String VND_INV_LINE_SUB_NUM = "VND_INV_LINE_SUB_NUM";

    /** db column : VND_INV_LINE_SUB_TRX_NUM. */
    public static final String VND_INV_LINE_SUB_TRX_NUM = "VND_INV_LINE_SUB_TRX_NUM";

    /** db column : ITRL_INTFC_ID. */
    public static final String ITRL_INTFC_ID = "ITRL_INTFC_ID";

    /** db column : ITRL_TRX_SQ. */
    public static final String ITRL_TRX_SQ = "ITRL_TRX_SQ";

    /** db column : INV_NUM. */
    public static final String INV_NUM = "INV_NUM";

    /** db column : INV_DT. */
    public static final String INV_DT = "INV_DT";

    /** db column : INV_TP_CD. */
    public static final String INV_TP_CD = "INV_TP_CD";

    /** db column : NET_DUE_DT. */
    public static final String NET_DUE_DT = "NET_DUE_DT";

    /** db column : BILL_TO_CUST_CD. */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** db column : BILL_TO_CUST_CD. */
    public static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";

    /** db column : INV_TOT_DEAL_NET_AMT. */
    public static final String INV_TOT_DEAL_NET_AMT = "INV_TOT_DEAL_NET_AMT";

    /** db column : INV_TOT_DEAL_SLS_AMT. */
    public static final String INV_TOT_DEAL_SLS_AMT = "INV_TOT_DEAL_SLS_AMT";

    /** db column : INV_TOT_DEAL_FRT_AMT. */
    public static final String INV_TOT_DEAL_FRT_AMT = "INV_TOT_DEAL_FRT_AMT";

    /** db column : INV_TOT_DEAL_TAX_AMT. */
    public static final String INV_TOT_DEAL_TAX_AMT = "INV_TOT_DEAL_TAX_AMT";

    /** db column : INV_TOT_DEAL_DISC_AMT. */
    public static final String INV_TOT_DEAL_DISC_AMT = "INV_TOT_DEAL_DISC_AMT";

    /** db column : DEAL_CCY_CD. */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** db column : INV_TOT_DEAL_INS_AMT. */
    public static final String INV_TOT_DEAL_INS_AMT = "INV_TOT_DEAL_INS_AMT";

    /** db column : INV_BOL_LINE_NUM. */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** db column : INV_LINE_NUM. */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** db column : INV_LINE_SUB_NUM. */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** db column : INV_LINE_SUB_TRX_NUM. */
    public static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";

    /** db column : ORD_QTY. */
    public static final String ORD_QTY = "ORD_QTY";

    /** db column : SHIP_QTY. */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** db column : DEAL_NET_UNIT_PRC_AMT. */
    public static final String DEAL_NET_UNIT_PRC_AMT = "DEAL_NET_UNIT_PRC_AMT";

    /** db column : VND_INV_LINE_DEAL_TAX_AMT. */
    public static final String VND_INV_LINE_DEAL_TAX_AMT = "VNDINV_LINE_DEAL_TAX_AMT";

    /** db column : VND_INV_LINE_DEAL_NET_AMT. */
    public static final String VND_INV_LINE_DEAL_NET_AMT = "VND_INV_LINE_DEAL_NET_AMT";

    /** db column : DEAL_DISC_UNIT_PRC_AMT. */
    public static final String DEAL_DISC_UNIT_PRC_AMT = "DEAL_DISC_UNIT_PRC_AMT";

    /** db column : DEAL_GRS_UNIT_PRC_AMT. */
    public static final String DEAL_GRS_UNIT_PRC_AMT = "DEAL_GRS_UNIT_PRC_AMT";

    /** db column : DEAL_GRS_TOT_PRC_AMT. */
    public static final String DEAL_GRS_TOT_PRC_AMT = "DEAL_GRS_TOT_PRC_AMT";

    /** db column : EDI_*PO_ORD_NUM. */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** db column : PO_ORD_NUM. */
    public static final String EDI_PO_ORD_NUM = "EDI_PO_ORD_NUM";

    /** db column : PO_ORD_DTL_LINE_NUM. */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    // START 03/24/2016 R.Shimamoto [V2.0 ADD]
    /** db column : ORIG_INV_NUM. */
    public static final String ORIG_INV_NUM = "ORIG_INV_NUM";

    /** db column : CPO_ORD_NUM. */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** db column : ORD_DT. */
    public static final String ORD_DT = "ORD_DT";

    /** db column : CPO_ORD_TP_CD. */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** db column : OFC_NM. */
    public static final String OFC_NM = "OFC_NM";

    /** db column : OFC_BR_CD. */
    public static final String OFC_BR_CD = "OFC_BR_CD";

    /** db column : OFC_FIRST_LINE_ADDR. */
    public static final String OFC_FIRST_LINE_ADDR = "OFC_FIRST_LINE_ADDR";

    /** db column : OFC_SCD_LINE_ADDR. */
    public static final String OFC_SCD_LINE_ADDR = "OFC_SCD_LINE_ADDR";

    /** db column : OFC_THIRD_LINE_ADDR. */
    public static final String OFC_THIRD_LINE_ADDR = "OFC_THIRD_LINE_ADDR";

    /** db column : OFC_FRTH_LINE_ADDR. */
    public static final String OFC_FRTH_LINE_ADDR = "OFC_FRTH_LINE_ADDR";

    /** db column : OFC_CTY_ADDR. */
    public static final String OFC_CTY_ADDR = "OFC_CTY_ADDR";

    /** db column : OFC_ST_CD. */
    public static final String OFC_ST_CD = "OFC_ST_CD";

    /** db column : OFC_PROV_NM. */
    public static final String OFC_PROV_NM = "OFC_PROV_NM";

    /** db column : OFC_CNTY_NM. */
    public static final String OFC_CNTY_NM = "OFC_CNTY_NM";

    /** db column : OFC_POST_CD. */
    public static final String OFC_POST_CD = "OFC_POST_CD";

    /** db column : OFC_CTRY_CD. */
    public static final String OFC_CTRY_CD = "OFC_CTRY_CD";

    /** db column : OFC_TEL_NUM. */
    public static final String OFC_TEL_NUM = "OFC_TEL_NUM";

    /** db column : OFC_FAX_NUM. */
    public static final String OFC_FAX_NUM = "OFC_FAX_NUM";

    /** db column : OFC_LOC_NM. */
    public static final String OFC_LOC_NM = "OFC_LOC_NM";

    /** db column : OFC_ADDL_LOC_NM. */
    public static final String OFC_ADDL_LOC_NM = "OFC_ADDL_LOC_NM";

    /** db column : REMIT_TO_LOC_NM. */
    public static final String REMIT_TO_LOC_NM = "REMIT_TO_LOC_NM";

    /** db column : REMIT_TO_ADDL_LOC_NM. */
    public static final String REMIT_TO_ADDL_LOC_NM = "REMIT_TO_ADDL_LOC_NM";

    /** db column : REM_ID. */
    public static final String REM_ID = "REM_ID";

    /** db column : DUNS_NUM. */
    public static final String DUNS_NUM = "DUNS_NUM";

    /** db column : SELL_TO_CUST_CD. */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** db column : SELL_TO_LOC_NM. */
    public static final String SELL_TO_LOC_NM = "SELL_TO_LOC_NM";

    /** db column : SELL_TO_ADDL_LOC_NM. */
    public static final String SELL_TO_ADDL_LOC_NM = "SELL_TO_ADDL_LOC_NM";

    /** db column : SELL_TO_FIRST_LINE_ADDR. */
    public static final String SELL_TO_FIRST_LINE_ADDR = "SELL_TO_FIRST_LINE_ADDR";

    /** db column : SELL_TO_SCD_LINE_ADDR. */
    public static final String SELL_TO_SCD_LINE_ADDR = "SELL_TO_SCD_LINE_ADDR";

    /** db column : SELL_TO_THIRD_LINE_ADDR. */
    public static final String SELL_TO_THIRD_LINE_ADDR = "SELL_TO_THIRD_LINE_ADDR";

    /** db column : SELL_TO_FRTH_LINE_ADDR. */
    public static final String SELL_TO_FRTH_LINE_ADDR = "SELL_TO_FRTH_LINE_ADDR";

    /** db column : SELL_TO_CTY_ADDR. */
    public static final String SELL_TO_CTY_ADDR = "SELL_TO_CTY_ADDR";

    /** db column : SELL_TO_ST_CD. */
    public static final String SELL_TO_ST_CD = "SELL_TO_ST_CD";

    /** db column : SELL_TO_PROV_NM. */
    public static final String SELL_TO_PROV_NM = "SELL_TO_PROV_NM";

    /** db column : SELL_TO_CNTY_NM. */
    public static final String SELL_TO_CNTY_NM = "SELL_TO_CNTY_NM";

    /** db column : SELL_TO_POST_CD. */
    public static final String SELL_TO_POST_CD = "SELL_TO_POST_CD";

    /** db column : SELL_TO_CTRY_CD. */
    public static final String SELL_TO_CTRY_CD = "SELL_TO_CTRY_CD";

    /** db column : SELL_TO_FIRST_REF_CMNT_TXT. */
    public static final String SELL_TO_FIRST_REF_CMNT_TXT = "SELL_TO_FIRST_REF_CMNT_TXT";

    /** db column : SELL_TO_SCD_REF_CMNT_TXT. */
    public static final String SELL_TO_SCD_REF_CMNT_TXT = "SELL_TO_SCD_REF_CMNT_TXT";

    /** db column : RCPNT_FIRST_LINE_ADDR. */
    public static final String RCPNT_FIRST_LINE_ADDR = "RCPNT_FIRST_LINE_ADDR";

    /** db column : RCPNT_SCD_LINE_ADDR. */
    public static final String RCPNT_SCD_LINE_ADDR = "RCPNT_SCD_LINE_ADDR";

    /** db column : RCPNT_THIRD_LINE_ADDR. */
    public static final String RCPNT_THIRD_LINE_ADDR = "RCPNT_THIRD_LINE_ADDR";

    /** db column : RCPNT_FRTH_LINE_ADDR. */
    public static final String RCPNT_FRTH_LINE_ADDR = "RCPNT_FRTH_LINE_ADDR";

    /** db column : RCPNT_CTY_ADDR. */
    public static final String RCPNT_CTY_ADDR = "RCPNT_CTY_ADDR";

    /** db column : RCPNT_ST_CD. */
    public static final String RCPNT_ST_CD = "RCPNT_ST_CD";

    /** db column : RCPNT_PROV_NM. */
    public static final String RCPNT_PROV_NM = "RCPNT_PROV_NM";

    /** db column : RCPNT_CNTY_NM. */
    public static final String RCPNT_CNTY_NM = "RCPNT_CNTY_NM";

    /** db column : RCPNT_POST_CD. */
    public static final String RCPNT_POST_CD = "RCPNT_POST_CD";

    /** db column : RCPNT_CTRY_CD. */
    public static final String RCPNT_CTRY_CD = "RCPNT_CTRY_CD";

    /** db column : RCPNT_LOC_NM. */
    public static final String RCPNT_LOC_NM = "RCPNT_LOC_NM";

    /** db column : RCPNT_ADDL_LOC_NM. */
    public static final String RCPNT_ADDL_LOC_NM = "RCPNT_ADDL_LOC_NM";

    /** db column : INV_RCPNT_FAX_NUM. */
    public static final String INV_RCPNT_FAX_NUM = "INV_RCPNT_FAX_NUM";

    /** db column : PMT_TERM_START_DT. */
    public static final String PMT_TERM_START_DT = "PMT_TERM_START_DT";

    /** db column : PMT_TERM_CD. */
    public static final String PMT_TERM_CD = "PMT_TERM_CD";

    /** db column : PMT_TERM_NM. */
    public static final String PMT_TERM_NM = "PMT_TERM_NM";

    /** db column : CASH_DISC_TERM_CD. */
    public static final String CASH_DISC_TERM_CD = "CASH_DISC_TERM_CD";

    /** db column : PMT_TERM_CASH_DISC_CD. */
    public static final String PMT_TERM_CASH_DISC_CD = "PMT_TERM_CASH_DISC_CD";

    /** db column : SLS_ADMIN_PSN_CD. */
    public static final String SLS_ADMIN_PSN_CD = "SLS_ADMIN_PSN_CD";

    /** db column : CR_ANLST_PSN_CD. */
    public static final String CR_ANLST_PSN_CD = "CR_ANLST_PSN_CD";

    /** db column : CR_ANLST_PSN_NM. */
    public static final String CR_ANLST_PSN_NM = "CR_ANLST_PSN_NM";

    /** db column : PAYER_CUST_CD. */
    public static final String PAYER_CUST_CD = "PAYER_CUST_CD";

    /** db column : INV_FIRST_CMNT_TXT. */
    public static final String INV_FIRST_CMNT_TXT = "INV_FIRST_CMNT_TXT";

    /** db column : INV_SCD_CMNT_TXT. */
    public static final String INV_SCD_CMNT_TXT = "INV_SCD_CMNT_TXT";

    /** db column : INV_THIRD_CMNT_TXT. */
    public static final String INV_THIRD_CMNT_TXT = "INV_THIRD_CMNT_TXT";

    /** db column : INV_FRTH_CMNT_TXT. */
    public static final String INV_FRTH_CMNT_TXT = "INV_FRTH_CMNT_TXT";

    /** db column : ACCT_DT. */
    public static final String ACCT_DT = "ACCT_DT";

    /** db column : SO_NUM. */
    public static final String SO_NUM = "SO_NUM";

    /** db column : BOL_NUM. */
    public static final String BOL_NUM = "BOL_NUM";

    /** db column : CARR_CD. */
    public static final String CARR_CD = "CARR_CD";

    /** db column : CARR_NMPRO_NUM. */
    public static final String CARR_NM = "CARR_NM";

    /** db column : PRO_NUM. */
    public static final String PRO_NUM = "PRO_NUM";

    /** db column : SHIP_FROM_INVTY_LOC_CD. */
    public static final String SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";

    /** db column : SHIP_TO_CUST_CD. */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** db column : DROP_SHIP_FLG. */
    public static final String DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** db column : SHIP_TO_LOC_NM. */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** db column : SHIP_TO_ADDL_LOC_NM. */
    public static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    /** db column : SHIP_TO_FIRST_LINE_ADDR. */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** db column : SHIP_TO_SCD_LINE_ADDR. */
    public static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /** db column : SHIP_TO_THIRD_LINE_ADDR. */
    public static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /** db column : SHIP_TO_FRTH_LINE_ADDR. */
    public static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /** db column : SHIP_TO_ST_CD. */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** db column : SHIP_TO_PROV_NM. */
    public static final String SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";

    /** db column : SHIP_TO_CNTY_NM. */
    public static final String SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";

    /** db column : SHIP_TO_FIRST_REF_CMNT_TXT. */
    public static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";

    /** db column : SHIP_TO_SCD_REF_CMNT_TXT. */
    public static final String SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";

    /** db column : SHIP_TO_POST_CD. */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** db column : SHIP_TO_CTY_ADDR. */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** db column : SHIP_TO_CTRY_CD. */
    public static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /** db column : SHIP_DEAL_SLS_AMT. */
    public static final String SHIP_DEAL_SLS_AMT = "SHIP_DEAL_SLS_AMT";

    /** db column : SHIP_DEAL_NET_AMT. */
    public static final String SHIP_DEAL_NET_AMT = "SHIP_DEAL_NET_AMT";

    /** db column : SHIP_DEAL_FRT_AMT. */
    public static final String SHIP_DEAL_FRT_AMT = "SHIP_DEAL_FRT_AMT";

    /** db column : SHIP_DEAL_DISC_AMT. */
    public static final String SHIP_DEAL_DISC_AMT = "SHIP_DEAL_DISC_AMT";

    /** db column : SHIP_DEAL_HDLG_CHRG_AMT. */
    public static final String SHIP_DEAL_HDLG_CHRG_AMT = "SHIP_DEAL_HDLG_CHRG_AMT";

    /** db column : SHIP_DT. */
    public static final String SHIP_DT = "SHIP_DT";

    /** db column : ARV_DT. */
    public static final String ARV_DT = "ARV_DT";

    /** db column : TOT_BOL_DEAL_TAX_AMT. */
    public static final String TOT_BOL_DEAL_TAX_AMT = "TOT_BOL_DEAL_TAX_AMT";

    /** db column : SHPG_SVC_LVL_CD. */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** db column : FRT_CHRG_TO_CD. */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** db column : FRT_CHRG_TO_NM. */
    public static final String FRT_CHRG_TO_NM = "FRT_CHRG_TO_NM";

    /** db column : FRT_CHRG_METH_CD. */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** db column : FRT_CHRG_METH_NM. */
    public static final String FRT_CHRG_METH_NM = "FRT_CHRG_METH_NM";

    /** db column : FRT_TAX_PCT. */
    public static final String FRT_TAX_PCT = "FRT_TAX_PCT";

    /** db column : FRT_DEAL_TAX_AMT. */
    public static final String FRT_DEAL_TAX_AMT = "FRT_DEAL_TAX_AMT";

    /** db column : EDI_NUM. */
    public static final String EDI_NUM = "EDI_NUM";

    /** db column : CPO_DTL_LINE_NUM. */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** db column : CPO_DTL_LINE_SUB_NUM. */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** db column : STK_STS_CD. */
    public static final String STK_STS_CD = "STK_STS_CD";

    /** db column : MDSE_CD. */
    public static final String MDSE_CD = "MDSE_CD";

    /** db column : MDSE_NM. */
    public static final String MDSE_NM = "MDSE_NM";

    /** db column : BO_QTY. */
    public static final String BO_QTY = "BO_QTY";

    /** db column : MAN_PRC_FLG. */
    public static final String MAN_PRC_FLG = "MAN_PRC_FLG";

    /** db column : INV_LINE_DEAL_TAX_AMT. */
    public static final String INV_LINE_DEAL_TAX_AMT = "INV_LINE_DEAL_TAX_AMT";

    /** db column : INV_LINE_DEAL_NET_AMT. */
    public static final String INV_LINE_DEAL_NET_AMT = "INV_LINE_DEAL_NET_AMT";

    /** db column : TAX_PCT. */
    public static final String TAX_PCT = "TAX_PCT";

    /** db column : MERC_STMT_ON_INV_TXT. */
    public static final String MERC_STMT_ON_INV_TXT = "MERC_STMT_ON_INV_TXT";

    /** db column : SET_MDSE_CD. */
    public static final String SET_MDSE_CD = "SET_MDSE_CD";

    /** db column : CUST_UOM_CD. */
    public static final String CUST_UOM_CD = "CUST_UOM_CD";

    /** db column : CUST_MDSE_CD. */
    public static final String CUST_MDSE_CD = "CUST_MDSE_CD";

    /** db column : ZEROTH_PROD_CTRL_CD. */
    public static final String ZEROTH_PROD_CTRL_CD = "ZEROTH_PROD_CTRL_CD";

    /** db column : ZEROTH_PROD_CTRL_NM. */
    public static final String ZEROTH_PROD_CTRL_NM = "ZEROTH_PROD_CTRL_NM";

    /** db column : FIRST_PROD_CTRL_CD. */
    public static final String FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /** db column : FIRST_PROD_CTRL_NM. */
    public static final String FIRST_PROD_CTRL_NM = "FIRST_PROD_CTRL_NM";

    /** db column : SCD_PROD_CTRL_CD. */
    public static final String SCD_PROD_CTRL_CD = "SCD_PROD_CTRL_CD";

    /** db column : SCD_PROD_CTRL_NM. */
    public static final String SCD_PROD_CTRL_NM = "SCD_PROD_CTRL_NM";

    /** db column : THIRD_PROD_CTRL_CD. */
    public static final String THIRD_PROD_CTRL_CD = "THIRD_PROD_CTRL_CD";

    /** db column : THIRD_PROD_CTRL_NM. */
    public static final String THIRD_PROD_CTRL_NM = "THIRD_PROD_CTRL_NM";

    /** db column : FRTH_PROD_CTRL_CD. */
    public static final String FRTH_PROD_CTRL_CD = "FRTH_PROD_CTRL_CD";

    /** db column : FRTH_PROD_CTRL_NM. */
    public static final String FRTH_PROD_CTRL_NM = "FRTH_PROD_CTRL_NM";

    /** db column : FIFTH_PROD_CTRL_CD. */
    public static final String FIFTH_PROD_CTRL_CD = "FIFTH_PROD_CTRL_CD";

    /** db column : FIFTH_PROD_CTRL_NM. */
    public static final String FIFTH_PROD_CTRL_NM = "FIFTH_PROD_CTRL_NM";

    /** db column : SIXTH_PROD_CTRL_CD. */
    public static final String SIXTH_PROD_CTRL_CD = "SIXTH_PROD_CTRL_CD";

    /** db column : SIXTH_PROD_CTRL_NM. */
    public static final String SIXTH_PROD_CTRL_NM = "SIXTH_PROD_CTRL_NM";

    /** db column : SVNTH_PROD_CTRL_CD. */
    public static final String SVNTH_PROD_CTRL_CD = "SVNTH_PROD_CTRL_CD";

    /** db column : SVNTH_PROD_CTRL_NM. */
    public static final String SVNTH_PROD_CTRL_NM = "SVNTH_PROD_CTRL_NM";

    /** db column : PO_REQ_FLG. */
    public static final String PO_REQ_FLG = "PO_REQ_FLG";

    /** db column : INV_CASH_DISC_TERM_DTL_NUM. */
    public static final String INV_CASH_DISC_TERM_DTL_NUM = "INV_CASH_DISC_TERM_DTL_NUM";

    /** db column : INV_CASH_DISC_DUE_DT. */
    public static final String INV_CASH_DISC_DUE_DT = "INV_CASH_DISC_DUE_DT";

    /** db column : INV_CASH_DISC_RATIO_PCT. */
    public static final String INV_CASH_DISC_RATIO_PCT = "INV_CASH_DISC_RATIO_PCT";

    /** db column : INV_CASH_DISC_AMT. */
    public static final String INV_CASH_DISC_AMT = "INV_CASH_DISC_AMT";

    /** db column : P_NUM_INVOICE_CPI. */
    public static final String P_NUM_INVOICE_CPI = "P_NUM_INVOICE_CPI";

    /** db column : P_NUM_INVOICE_CPSH. */
    public static final String P_NUM_INVOICE_CPSH = "P_NUM_INVOICE_CPSH";

    /** db column : P_DATE_INVO. */
    public static final String P_DATE_INVO = "P_DATE_INVO";

    /** db column : P_CODE_ORDR_TYPE_PART. */
    public static final String P_CODE_ORDR_TYPE_PART = "P_CODE_ORDR_TYPE_PART";

    /** db column : P_NET_DAYS. */
    public static final String P_NET_DAYS = "P_NET_DAYS";

    /** db column : P_NUM_ORDR. */
    public static final String P_NUM_ORDR = "P_NUM_ORDR";

    /** db column : P_DATE_ORDR_RCV. */
    public static final String P_DATE_ORDR_RCV = "P_DATE_ORDR_RCV";

    /** db column : P_CODE_CUST_CHRG_TO. */
    public static final String P_CODE_CUST_CHRG_TO = "P_CODE_CUST_CHRG_TO";

    /** db column : P_CODE_CUST_SOLD_TO. */
    public static final String P_CODE_CUST_SOLD_TO = "P_CODE_CUST_SOLD_TO";

    /** db column : P_CODE_TERM_S80. */
    public static final String P_CODE_TERM_S80 = "P_CODE_TERM_S80";

    /** db column : P_AMT_INVO_TOT_CPI. */
    public static final String P_AMT_INVO_TOT_CPI = "P_AMT_INVO_TOT_CPI";

    /** db column : P_AMT_INVO_TOT_CPSH. */
    public static final String P_AMT_INVO_TOT_CPSH = "P_AMT_INVO_TOT_CPSH";

    /** db column : P_AMT_SHIP_CPI. */
    public static final String P_AMT_SHIP_CPI = "P_AMT_SHIP_CPI";

    /** db column : P_AMT_SHIP_CPSH. */
    public static final String P_AMT_SHIP_CPSH = "P_AMT_SHIP_CPSH";

    /** db column : P_AMT_INVO_FRT_CPI. */
    public static final String P_AMT_INVO_FRT_CPI = "P_AMT_INVO_FRT_CPI";

    /** db column : P_AMT_INVO_FRT_CPSH. */
    public static final String P_AMT_INVO_FRT_CPSH = "P_AMT_INVO_FRT_CPSH";

    /** db column : P_AMT_SO_TAX_CPI. */
    public static final String P_AMT_SO_TAX_CPI = "P_AMT_SO_TAX_CPI";

    /** db column : P_AMT_SO_TAX_CPSH. */
    public static final String P_AMT_SO_TAX_CPSH = "P_AMT_SO_TAX_CPSH";

    /** db column : P_AMT_DISCOUNT_CPI. */
    public static final String P_AMT_DISCOUNT_CPI = "P_AMT_DISCOUNT_CPI";

    /** db column : P_AMT_DISCOUNT_CPSH. */
    public static final String P_AMT_DISCOUNT_CPSH = "P_AMT_DISCOUNT_CPSH";

    /** db column : P_MSG_SO_1. */
    public static final String P_MSG_SO_1 = "P_MSG_SO_1";

    /** db column : P_MSG_SO_2. */
    public static final String P_MSG_SO_2 = "P_MSG_SO_2";

    /** db column : P_NUM_SO. */
    public static final String P_NUM_SO = "P_NUM_SO";

    /** db column : P_NUM_CUST_PO. */
    public static final String P_NUM_CUST_PO = "P_NUM_CUST_PO";

    /** db column : P_CODE_WH_SHIP. */
    public static final String P_CODE_WH_SHIP = "P_CODE_WH_SHIP";

    /** db column : P_CODE_CUST_SHIP_TO. */
    public static final String P_CODE_CUST_SHIP_TO = "P_CODE_CUST_SHIP_TO";

    /** db column : P_NAME_CUST_SHIP_TO_1. */
    public static final String P_NAME_CUST_SHIP_TO_1 = "P_NAME_CUST_SHIP_TO_1";

    /** db column : P_NAME_CUST_SHIP_TO_2. */
    public static final String P_NAME_CUST_SHIP_TO_2 = "P_NAME_CUST_SHIP_TO_2";

    /** db column : P_SHIP_TO_EXT_ADDR_01. */
    public static final String P_SHIP_TO_EXT_ADDR_01 = "P_SHIP_TO_EXT_ADDR_01";

    /** db column : P_SHIP_TO_EXT_ADDR_02. */
    public static final String P_SHIP_TO_EXT_ADDR_02 = "P_SHIP_TO_EXT_ADDR_02";

    /** db column : P_SHIP_TO_EXT_ADDR_03. */
    public static final String P_SHIP_TO_EXT_ADDR_03 = "P_SHIP_TO_EXT_ADDR_03";

    /** db column : P_SHIP_TO_EXT_ADDR_04. */
    public static final String P_SHIP_TO_EXT_ADDR_04 = "P_SHIP_TO_EXT_ADDR_04";

    /** db column : P_CODE_SHIP_TO_STA. */
    public static final String P_CODE_SHIP_TO_STA = "P_CODE_SHIP_TO_STA";

    /** db column : P_ADDR_SHIP_TO_ZIP. */
    public static final String P_ADDR_SHIP_TO_ZIP = "P_ADDR_SHIP_TO_ZIP";

    /** db column : P_ADDR_SHIP_TO_CITY. */
    public static final String P_ADDR_SHIP_TO_CITY = "P_ADDR_SHIP_TO_CITY";

    /** db column : P_AMT_HANDLG_CHRG. */
    public static final String P_AMT_HANDLG_CHRG = "P_AMT_HANDLG_CHRG";

    /** db column : P_DATE_SHIP_PRSS. */
    public static final String P_DATE_SHIP_PRSS = "P_DATE_SHIP_PRSS";

    /** db column : P_NUM_LINE. */
    public static final String P_NUM_LINE = "P_NUM_LINE";

    /** db column : P_PARTS_NUM_FLIP. */
    public static final String P_PARTS_NUM_FLIP = "P_PARTS_NUM_FLIP";

    /** db column : P_PARTS_NUM_SO. */
    public static final String P_PARTS_NUM_SO = "P_PARTS_NUM_SO";

    /** db column : P_QTY_SHIP_SCHED. */
    public static final String P_QTY_SHIP_SCHED = "P_QTY_SHIP_SCHED";

    /** db column : P_QTY_SHIP. */
    public static final String P_QTY_SHIP = "P_QTY_SHIP";

    /** db column : P_PRC_SALE. */
    public static final String P_PRC_SALE = "P_PRC_SALE";

    /** db column : P_PRC_DISCOUNT. */
    public static final String P_PRC_DISCOUNT = "P_PRC_DISCOUNT";

    /** db column : P_DESC_PART. */
    public static final String P_DESC_PART = "P_DESC_PART";

    /** db column : P_PARTS_NUM_ORIG. */
    public static final String P_PARTS_NUM_ORIG = "P_PARTS_NUM_ORIG";

    /** db column : CUST_ISS_PO_NUM. */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** db column : LIC_ACCS_NUM. */
    public static final String LIC_ACCS_NUM = "LIC_ACCS_NUM";

    /** db column : ILMS_LIC_ACCS_NUM. */
    public static final String ILMS_LIC_ACCS_NUM = "ILMS_LIC_ACCS_NUM";

    /** db column : INVTY_CTRL_FLG. */
    public static final String INVTY_CTRL_FLG = "INVTY_CTRL_FLG";

    /** db column : P_PARTS_NUM. */
    public static final String P_PARTS_NUM = "P_PARTS_NUM";

    /** db column : P_CUST_PART_NUM. */
    public static final String P_CUST_PART_NUM = "P_CUST_PART_NUM";

    // END 03/24/2016 R.Shimamoto [V2.0 ADD]

    /** db table : VND_INV_WRK */
    public static final String VND_INV_WRK = "VND_INV_WRK";

    /** db table : VND_INV_BOL_WRK */
    public static final String VND_INV_BOL_WRK = "VND_INV_BOL_WRK";

    /** db table : VND_INV_LINE_WRK */
    public static final String VND_INV_LINE_WRK = "VND_INV_LINE_WRK";

    /** db table : VND_INV_DISC_TERM_WRK */
    public static final String VND_INV_DISC_TERM_WRK = "VND_INV_DISC_TERM_WRK";

    /** db table : VND_INV_LIC_ACCS_WRK */
    public static final String VND_INV_LIC_ACCS_WRK = "VND_INV_LIC_ACCS_WRK";

    /** db table : PO_DTL */
    public static final String PO_DTL = "PO_DTL";

    // error message code
    /** The Constant [@] was not found on Constant table. */
    public static final String NPAM1010E = "NPAM1010E";

    /** message id : NLAM1101E */
    public static final String MSG_ID_NLAM1101E = "NLAM1101E";

    /** message id : NLAM1118E */
    public static final String MSG_ID_NLAM1118E = "NLAM1118E";

    /** message id : NLAM1226E */
    public static final String MSG_ID_NLAM1226E = "NLAM1226E";

    /** message id : NLAM1270E */
    public static final String MSG_ID_NLAM1270E = "NLAM1270E";

    /** message id : NLAM1272E */
    public static final String MSG_ID_NLAM1272E = "NLAM1272E";

    /** message id : NLAM1273E */
    public static final String MSG_ID_NLAM1273E = "NLAM1273E";

    // START 03/14/2016 R.Shimamoto [V2.0 ADD]
    /** message id : NWZM0650E */
    public static final String MSG_ID_NWZM0650E = "NWZM0650E";

    // END 03/14/2016 R.Shimamoto [V2.0 ADD]

    // mail
    // START 03/25/2014 K.Kishimoto [CSA-028,DEL]
    // /** Mail Template ID for Invoice */
    // public static final String MAIL_TEMPLATE_INV =
    // "Mail Template ID for Receive Invoice processing";
    // END 03/25/2014 K.Kishimoto [CSA-028,DEL]

    /** Mail Gropu Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "NLAB300001";

    /** System Error Mail group id for To. */
    public static final String MAIL_GROUP_ID_TO_SYSTEM = "NLAB300001";

    /** Business Error Mail group id for To. */
    public static final String MAIL_GROUP_ID_TO_BUSINESS = "NLAB300002";

    /** Mail group id for To. */
    public static final String MAIL_TEMPLATE_ID_M001 = "NLAB3020M001";

    /** Mail String Field : TIMESTAMP */
    public static final String MAIL_FIELD_TIMESTAMP = "TIMESTAMP";

    /** Mail String Field : MESSAGE */
    public static final String MAIL_FIELD_MESSAGE = "MAIL_MESSAGE";

    /** Mail Timestamp Format */
    public static final String MAIL_TS_FMT = "MM/dd/yyyy HH:mm:ss.SSS";

    // other constants
    /** initial size: Data Select */
    public static final int SELECT_SIZE = 1000;

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    // START 03/14/2016 R.Shimamoto [V2.0 ADD]
    /** Error Message Text: CUSA Global Company Code */
    public static final String MSG_TXT_CUSA_GLBL_CMPY_CD = "CUSA Global Company Code";

    /** Error Message Text: CUSA EDI Trading Partner Code */
    public static final String MSG_TXT_CUSA_EDI_TRADING_PARTNER_CD = "CUSA EDI Trading Partner Code";

    /** Error Message Text: CUSA Excluded System Source Code */
    public static final String MSG_TXT_CUSA_EXCLUDED_SYSTEM_SOURCE_CD = "CUSA Excluded System Source Code";

    /** Error Message Text: CUSA Parts EDI Trading Partner Code */
    public static final String MSG_TXT_CUSA_PARTS_EDI_TRADING_PARTNER_CD = "CUSA Parts EDI Trading Partner Code";

    // END 03/14/2016 R.Shimamoto [V2.0 ADD]

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_CONFIG_ID = "Configure Id";

    // START 03/25/2014 K.Kishimoto [CSA-028,ADD]
    /** Error Message Text: Invoice Vendor Code */
    public static final String MSG_TXT_INV_VND_CD = "Invoice Vendor Code";

    // END 03/25/2014 K.Kishimoto [CSA-028,ADD]

    /** Error Message Text: delimiter */
    public static final String MSG_TXT_DELIMITER = ",";

    /** Error Message Text: equals */
    public static final String MSG_TXT_EQUALS = "=";

    /** Error Message Text: space */
    public static final String MSG_TXT_SPACE = " ";

    /** Error Message Text: EDI PO # */
    public static final String MSG_TXT_EDI_PO_NUM = "EDI PO#";

    /** Error Message Text: EDI LINE# */
    public static final String MSG_TXT_EDI_LINE_NUM = "EDI LINE#";

    /** Error Message Text: PO# */
    public static final String MSG_TXT_PO_NUM = "PO#";

    /** Error Message Text: LINE# */
    public static final String MSG_TXT_LINE_NUM = "PO LINE#";

    /** Error Message Text: MdseCD */
    public static final String MSG_TXT_MDSE_CD = "Mdse Cd";

    /** Error Message Text: hyphen */
    public static final String MSG_TXT_HYPHEN = "-";

    /** Configure ID: ITRL_CONFIG_CUSA_WS_CSA */
    public static final String CONFIG_ID_ITRL_CONFIG_CUSA_WS_CSA = "ITRL_CONFIG_CUSA_WS_CSA";

    /** Configure ID: ITRL_CONFIG_CUSA_WS_CSA */
    public static final String CONFIG_ID_ITRL_CONFIG_CUSA_PRT_CSA = "ITRL_CONFIG_CUSA_PRT_CSA";

    /** Error Massage: IMPORT ERROR */
    public static final String ERR_MSG_IMPORT = "IMPORT ERROR";

    /** Error Massage: ITEM ERROR */
    public static final String ERR_MSG_ITEM = "ITEM ERROR";

    /** Error Massage: CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** STRING_LENGTH_2 */
    public static final int STRING_LENGTH_2 = 2;

    /** STRING_LENGTH_4 */
    public static final int STRING_LENGTH_4 = 4;

    /** STRING_LENGTH_15 */
    public static final int STRING_LENGTH_15 = 15;

    /** STRING_LENGTH_30 */
    public static final int STRING_LENGTH_30 = 30;

    /** STRING_LENGTH_30 */
    public static final int STRING_LENGTH_100 = 100;

    /** STRING_LENGTH_8 */
    public static final int STRING_LENGTH_8 = 8;

    /** Error Message : Failed to get Interface ID. */
    public static final String NPAM1168E = "NPAM1168E";

    /** CUSA Vendor Code Get Key WS */
    public static final String CUSA_VND_CD = "CUSA_VND_CD";

    /** CUSA Vendor Code Get Key PARTS */
    public static final String CUSA_PARTS_VND_CD = "CUSA_PARTS_VND_CD";

    // START 03/25/2014 K.Kishimoto [CSA-028,ADD]
    /** Interface ID: AWCA0010 */
    public static final String INTERFACE_ID_CUSA_WS = "AWCA0010";

    // END 03/25/2014 K.Kishimoto [CSA-028,ADD]

    // START 03/14/2016 R.Shimamoto [V2.0 ADD]
    /** Interface ID: USDA0300 */
    public static final String INTERFACE_ID_CUSA_PARTS = "USDA0300";

    /** STR_0 */
    public static final String STR_0 = "0";

    /** STR_1 */
    public static final String STR_1 = "1";

    /** STR_2 */
    public static final String STR_2 = "2";

    /** STR_20 */
    public static final String STR_20 = "20";

    /** STR_21 */
    public static final String STR_21 = "21";

    /** STR_22 */
    public static final String STR_22 = "22";

    /** EA */
    public static final String EA = "EA";

    /** blank */
    public static final String BLANK = "";

    /** commit count default */
    public static final int COMMIT_COUNT_DEFAULT = 100;

    /** CUSA EDI Trading Partner Code Name */
    public static final String CUSA_EDI_TRD_PTNR_CD = "CUSA_EDI_TRD_PTNR_CD";

    /** CUSA Excluded System Source Code Name */
    public static final String CUSA_EXCL_SYS_SRC_CD = "CUSA_EXCL_SYS_SRC_CD";

    /** CUSA Parts EDI Trading Partner Code Name */
    public static final String CUSA_PRT_EDI_TRD_PTNR_CD = "CUSA_PRT_EDI_TRD_PTNR_CD";

    /** CUSA Global Company Code */
    public static final String CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** EFF_THRU_DT_DEFAULT */
    public static final String EFF_THRU_DT_DEFAULT = "99991231";
    
    // END 03/14/2016 R.Shimamoto [V2.0 ADD

    /** db column : CUST_ISS_PO_NUM. */
    public static final String CR_DR_RSN_CD = "CR_DR_RSN_CD";

    // START 2020/01/10 M.Ishii [QC#55137, ADD]
    public static final int TWO_MONTH_BEFORE = -2;
    // END 2020/01/10 M.Ishii [QC#55137, ADD]

}
