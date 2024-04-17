/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1500.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          N/A
 * 2016/03/29   CITS            T.Tokutomi      Update          QC#5774
 * 2016/03/30   CITS            T.Tokutomi      Update          QC#6293
 * 2016/05/03   CSAI            K.Lee           Update          QC#7300
 * 2016/09/23   CITS            S.Endo          Update          N/A(Refactoring)
 * 2016/10/05   CITS            S.Endo          Update          QC#12768
 * 2016/11/24   CITS            R.Shimamoto     Update          QC#16080
 * 2016/11/28   CITS            Y.Fujii         Update          R350
 * 2016/12/27   CITS            S.Endo          Update          QC#14882
 * 2017/02/21   CITS            Y.IWASAKI       Update          QC#17487
 * 2017/02/27   CITS            Y.Fujii         Update          QC#16915
 * 2017/08/03   CITS            R.Shimamoto     Update          QC#18671
 * 2017/11/07   Hitachi         Y.Takeno        Update          QC#21849
 * 2017/12/08   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 2018/01/10   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 2018/02/08   CITS            K.Ogino         Update          QC#21169
 * 2018/04/06   CITS            K.Fukumura      Update          QC#21170
 * 06/11/2018   CITS            K.Kameoka       Update          QC#25714
 * 06/20/2018   CITS            K.Kameoka       Update          QC#18420
 * 07/09/2018   CITS            K.Ogino         Update          QC#24918
 * 07/12/2018   CITS            S.Katsuma       Update          QC#26867
 * 09/25/2018   CITS            K.Kameoka       Update          QC#28226
 * 10/01/2018   CITS            K.Kameoka       Update          QC#28460/27770
 * 11/16/2018   CITS            K.Ogino         Update          QC#29155
 * 12/13/2018   CITS            M.Naito         Update          QC#29027
 * 2019/04/17   CITS            K.Ogino         Update          QC#31087
 * 2019/07/17   CITS            M.Naito         Update          QC#51695
 * 2019/10/30   CITS            M.Naito         Update          QC#53811
 * 2021/04/21   CITS            J.Evangelista   Update          QC#57102
 * 2021/04/23   CITS            M.Furugoori     Update          QC#58645
 * 2022/05/16   Hitachi         A.Kohinata      Update          QC#57934
 * 2022/09/12   Hitachi         T.NEMA          Update          QC#60413
 * 2022/12/09   Hitachi         M.Kikushima     Update          QC#60604
 * 2023/02/14   Hitachi         S.Dong          Update          QC#60966
 * 2023/04/03   Hitachi         TZ.Win          Update          QC#60966
 * 2023/04/13   Hitachi         TZ.Win          Update          QC#61299
 * 2024/1/4     CITS            K.Iwamoto       Update          QC#62443
 * 2024/3/1     CITS            S.Okamoto       Update          QC#62443
 *</pre>
 */
public class NPAL1500Constant {

    /** Business ID */
    public static final String BIZ_ID = "NPAL1500";

    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";

    // =================================================
    // Event Name
    // =================================================

    /** Tab anchor Additional Header */
    public static final String TAB_ADDL_HEADER = "TAB_AddlHeader";

    /** No Selected Detail Line */
    public static final int NO_SLCT_DTL = -1;

    /** Period */
    public static final String PERIOD = ".";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** 100 Percentage */
    public static final BigDecimal PCT_100 = new BigDecimal(100);

    /**
     * IDX_1
     */
    public static final int IDX_1 = 1;

    /**
     * IDX_2
     */
    public static final int IDX_2 = 2;

    /**
     * IDX_3
     */
    public static final int IDX_3 = 3;

    /**
     * IDX_4
     */
    public static final int IDX_4 = 4;

    /**
     * IDX_5
     */
    public static final int IDX_5 = 5;

    /**
     * IDX_6
     */
    public static final int IDX_6 = 6;

    /**
     * IDX_7
     */
    public static final int IDX_7 = 7;

    /**
     * IDX_8
     */
    public static final int IDX_8 = 8;

    /**
     * IDX_9
     */
    public static final int IDX_9 = 9;

    /** ORD_CATG_BIZ_CTX : EQUIPMENT_ORDER */
    public static final String EQUIPMENT_ORDER = "EQUIPMENT_ORDER";

    /** ORD_CATG_BIZ_CTX : SUPPLIES_ORDER */
    public static final String SUPPLIES_ORDER = "SUPPLIES_ORDER";

    /** Pricing Package UOM Varchar Const Name */
    public static final String PKG_UOM_FOR_PRC = "PKG_UOM_FOR_PRC";

    /** Request Type : New. */
    public static final String REQ_NEW = "1";

    /** Request Type : Modify. */
    public static final String REQ_MOD = "2";

    /** Request Type : Delete. */
    public static final String REQ_DEL = "3";

    /** Sub System ID */
    public static final String SUB_SYS_ID = "NWA";

    /** Process ID */
    public static final String PROCESS_ID = "OM";

    /** Document Type */
    public static final String DOCUMENT_TYPE = "OM";

    /** Event Id : Order Cancel */
    public static final String EVENT_ID_ORDER_CANCEL = "Order Cancel";

    /** Event Id : Order Cancel */
    public static final String EVENT_ID_CHNG_ORD_MODIFY = "Change Order Modification";

    /** Order Category Context Type Code : CHANGE_ORDER_EXCLUSIONS */
    public static final String ORD_CATG_BIZ_CTX_CHANGE_ORDER_EXCLUSIONS = "CHANGE_ORDER_EXCLUSIONS";

    /** BUSINESS_APPLICATION_ID */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1500";

    /** APP_FUNC_ID_FOR_VA */
    public static final String APP_FUNC_ID_FOR_VA = "NPAL150003";

    /** AM/PM Pulldown Code */
    public static final String AM_PM_PULLDOWN_CD_AM = "0";

    /** AM/PM Pulldown Code */
    public static final String AM_PM_PULLDOWN_CD_PM = "1";

    /** AM/PM Pulldown Name */
    public static final String AM_PM_PULLDOWN_NM_AM = "AM";

    /** AM/PM Pulldown Code */
    public static final String AM_PM_PULLDOWN_NM_PM = "PM";

    // =================================================
    // Varchar Const
    // =================================================
    /** VAR_CHAR_CONST: Approval Amount. */
    public static final String KEY_NPAL1500_PO_APLV_AMT = "NPAL1500_PO_APLV_AMT";

    /** VAR_CHAR_CONST: Approval Flg. */
    public static final String KEY_NPAL1500_PO_APLV_FLG = "NPAL1500_PO_APLV_FLG";

    /** VAR_CHAR_CONST: Approval Flg. */
    public static final String KEY_NPAL1500_WH_DS_LIST = "NPAL1500_WH_DS_LIST";

    /** VAR_CHAR_CONST: CUSA S21 Parts. */
    public static final String KEY_NPAL1500_CUSA_S21_PARTS = "S21 CUSA Parts Vendor Code";

    /** VAR_CHAR_CONST: CUSA Global Company Code. */
    public static final String VAR_CHAR_CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** VAR_CHAR_CONST: Po Detal Line Max Count. */
    public static final String VAR_CHAR_PO_LINE_MAX_CNT = "PO_LINE_MAX_CNT";

    /** VAR_CHAR_CONST: Po Qlfy Cd for Customer Drop Ship. */
    public static final String VAR_CHAR_PO_CUST_DROP_SHIP_QLFY_CD = "PO_CUST_DROP_SHIP_QLFY_CD";

    /** MANUAL_DIRECT_SHIP_CUST_CD */
    public static final String MANUAL_DIRECT_SHIP_CUST_CD = "MD";

    /** CUSA_STAND_ALONE */
    public static final String CUSA_STAND_ALONE = "SA";

    /** VAR_CHAR_NPAL1500_BILL_TO_CD */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_CD = "NPAL1500_BILL_TO_CD";

    /** VAR_CHAR_NPAL1500_BILL_TO_NM */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_NM = "NPAL1500_BILL_TO_NM";

    /** VAR_CHAR_NPAL1500_BILL_TO_ADDL_LOC_NM */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_ADDL_LOC_NM = "NPAL1500_BILL_TO_ADDL_LOC_NM";

    /** VAR_CHAR_NPAL1500_BILL_TO_ADDR1 */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_ADDR1 = "NPAL1500_BILL_TO_ADDR1";

    /** VAR_CHAR_NPAL1500_BILL_TO_ADDR2 */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_ADDR2 = "NPAL1500_BILL_TO_ADDR2";

    /** VAR_CHAR_NPAL1500_BILL_TO_POST_CD */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_POST_CD = "NPAL1500_BILL_TO_POST_CD";

    /** VAR_CHAR_NPAL1500_BILL_TO_CTY_ADDR */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_CTY_ADDR = "NPAL1500_BILL_TO_CTY_ADDR";

    /** VAR_CHAR_NPAL1500_BILL_TO_CNTY_NM */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_CNTY_NM = "NPAL1500_BILL_TO_CNTY_NM";

    /** VAR_CHAR_NPAL1500_BILL_TO_ST_CD */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_ST_CD = "NPAL1500_BILL_TO_ST_CD";

    /** VAR_CHAR_NPAL1500_BILL_TO_PROV_NM */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_PROV_NM = "NPAL1500_BILL_TO_PROV_NM";

    /** VAR_CHAR_NPAL1500_BILL_TO_CTRY_CD */
    public static final String VAR_CHAR_NPAL1500_BILL_TO_CTRY_CD = "NPAL1500_BILL_TO_CTRY_CD";

    /** VAR_CHAR_PO_ACRL_RTL_WH_ACCT_TP_CD */
    public static final String VAR_CHAR_PO_ACRL_RTL_WH_ACCT_TP_CD = "PO_ACRL_RTL_WH_ACCT_TP_CD";

    /** VAR_CHAR_PO_VAR_RTL_WH_ACCT_TP_CD */
    public static final String VAR_CHAR_PO_VAR_RTL_WH_ACCT_TP_CD = "PO_VAR_RTL_WH_ACCT_TP_CD";

    /** VAR_CHAR_PO_VAR_COA_PROJ_ACCT_TP_CD */
    public static final String VAR_CHAR_PO_VAR_COA_PROJ_ACCT_TP_CD = "PO_VAR_COA_PROJ_ACCT_TP_CD";

    //QC#16080
    /** VAR_CHAR_NPAL1500_EXCLUDE_WH */
    public static final String VAR_CHAR_NPAL1500_EXCLUDE_WH = "NPAL1500_EXCLUDE_WH";

    /** VAR_CHAR_PO_PREAPVL_WH_OWNR_CD */
    public static final String VAR_CHAR_PO_PREAPVL_WH_OWNR_CD = "PO_PREAPVL_WH_OWNR_CD";

    /** VAR_CHAR_PO_SVC_LVL_WH_OWNR_CD */
    public static final String VAR_CHAR_PO_SVC_LVL_WH_OWNR_CD = "PO_SVC_LVL_WH_OWNR_CD";

    //QC#28460 Add 
    /** VAR_CHAR_PO_PREAPVL_WH_OWNR_CD */
    public static final String VAR_CHAR_PO_SC_WH_OWNR_CD = "PO_SC_WH_OWNR_CD";

    // START 2019/07/17 M.Naito [QC#51695,ADD]
    /** VAR_CONST_NOT_CHANGE_FOR_ROSS_PO_TP */
    public static final String VAR_CONST_NOT_CHANGE_FOR_ROSS_PO_TP = "NOT_CHANGE_FOR_ROSS_PO_TP";
    // END 2019/07/17 M.Naito [QC#51695,ADD]

    // START 2021/04/23 [QC#58645,ADD]
    /** VAR_CHAR_AUTO_APPROVE_AMT */
    public static final String VAR_CHAR_NPAL1500_AUTO_APPROVE_AMT = "NPAL1500_AUTO_APPROVE_AMT";
    // END 2021/04/23 [QC#58645,ADD]

    // QC#21170 Add
    /** Please fill out/select the required field. */
    public static final String RS_VND_LT_DAYS_NUM = "VND_LT_DAYS_NUM";
    // =================================================
    // DB Columns
    // =================================================
    /** ORIG_GOODS_ORD_QTY */
    public static final String DB_ORIG_GOODS_ORD_QTY = "ORIG_GOODS_ORD_QTY";

    /** DB_ORIG_GOODS_HARD_ALLOC_QTY */
    public static final String DB_ORIG_GOODS_HARD_ALLOC_QTY = "ORIG_GOODS_HARD_ALLOC_QTY";
    /** DB_PO_ORD_NUM */
    public static final String DB_PO_ORD_NUM = "PO_ORD_NUM";
    /** DB_PO_ACK_PROC_STS_CD */
    public static final String DB_PO_ACK_PROC_STS_CD = "PO_ACK_PROC_STS_CD";
    /** DB_EDI_PO_LINE_NUM */
    public static final String DB_EDI_PO_LINE_NUM = "EDI_PO_LINE_NUM";
    /** DB_EDI_PO_SUB_LINE_NUM */
    public static final String DB_EDI_PO_SUB_LINE_NUM = "EDI_PO_SUB_LINE_NUM";
    /** DB_CUSA_MDSE_CD */
    public static final String DB_CUSA_MDSE_CD = "CUSA_MDSE_CD";
    /** DB_CUSA_MDSE_NM */
    public static final String DB_CUSA_MDSE_NM = "CUSA_MDSE_NM";
    /** DB_ORIG_ORD_QTY */
    public static final String DB_ORIG_ORD_QTY = "ORIG_ORD_QTY";
    /** DB_PO_ACK_LINE_STS_CD */
    public static final String DB_PO_ACK_LINE_STS_CD = "PO_ACK_LINE_STS_CD";
    /** DB_PO_ACK_LINE_STS_NM */
    public static final String DB_PO_ACK_LINE_STS_NM = "PO_ACK_LINE_STS_NM";
    /** DB_VND_PO_ACK_LINE_STS_TXT */
    public static final String DB_VND_PO_ACK_LINE_STS_TXT = "VND_PO_ACK_LINE_STS_TXT";
    /** DB_PO_ACK_CMNT_TXT */
    public static final String DB_PO_ACK_CMNT_TXT = "PO_ACK_CMNT_TXT";
    /** DB_SHPG_PLN_DPLY_LINE_NUM */
    public static final String DB_SHPG_PLN_DPLY_LINE_NUM = "SHPG_PLN_DPLY_LINE_NUM";
    /** DB_WH_IN_ETA_DT */
    public static final String DB_WH_IN_ETA_DT = "WH_IN_ETA_DT";
    /** DB_RWS_STS_CD */
    public static final String DB_RWS_STS_CD = "RWS_STS_CD";
    /** DB_RWS_STS_NM */
    public static final String DB_RWS_STS_NM = "RWS_STS_NM";
    /** DB_ORD_LAST_UPD_TS */
    public static final String DB_ORD_LAST_UPD_TS = "ORD_LAST_UPD_TS";
    /** DB_ORD_QTY */
    public static final String DB_ORD_QTY = "ORD_QTY";
    /** DB_PO_ORD_DTL_LINE_NUM */
    public static final String DB_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
    /** DB_PO_ORD_DTL_SUB_LINE_NUM */
    public static final String DB_PO_ORD_DTL_SUB_LINE_NUM = "PO_ORD_DTL_SUB_LINE_NUM";
    /** DB_VND_ISS_PO_ORD_NUM */
    public static final String DB_VND_ISS_PO_ORD_NUM = "VND_ISS_PO_ORD_NUM";
    /** DB_PO_STS_CD */
    public static final String DB_PO_STS_CD = "PO_STS_CD";
    /** DB_PO_STS_NM */
    public static final String DB_PO_STS_NM = "PO_STS_NM";
    /** DB_PD_PO_STS_CD */
    public static final String DB_PD_PO_STS_CD = "PD_PO_STS_CD";
    /** DB_PD_STS_NM */
    public static final String DB_PD_STS_NM = "PD_STS_NM";
    /** DB_MDSE_CD */
    public static final String DB_MDSE_CD = "MDSE_CD";
    /** DB_MDSE_NM */
    public static final String DB_MDSE_NM = "MDSE_NM";
    /** DB_MDSE_NM */
    public static final String DB_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** DB_PO_QTY */
    public static final String DB_PO_QTY = "PO_QTY";
    /** DB_PO_RCV_QTY */
    public static final String DB_PO_RCV_QTY = "PO_RCV_QTY";
    /** DB_PO_CANC_QTY */
    public static final String DB_PO_CANC_QTY = "PO_CANC_QTY";
    /** DB_PO_BAL_QTY */
    public static final String DB_PO_BAL_QTY = "PO_BAL_QTY";
    /** DB_THIS_MTH_FOB_COST_AMT */
    public static final String DB_THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";
    /** DB_THIS_MTH_FOB_AMT */
    public static final String DB_THIS_MTH_FOB_AMT = "THIS_MTH_FOB_AMT";
    /** DB_THIS_MTH_FOB_AMT */
    public static final String DB_PO_CCY_CD = "PO_CCY_CD";
    /** DB_COST_CCY_CD */
    public static final String DB_COST_CCY_CD = "COST_CCY_CD";
    /** DB_CCY_NM */
    public static final String DB_CCY_NM = "CCY_NM";
    /** DB_PO_ORD_DTL_CMNT_TXT */
    public static final String DB_PO_ORD_DTL_CMNT_TXT = "PO_ORD_DTL_CMNT_TXT";
    /** DB_VND_CD */
    public static final String DB_VND_CD = "VND_CD";
    /** DB_VND_NM */
    public static final String DB_VND_NM = "VND_NM";
    /** DB_APVL_DT */
    public static final String DB_APVL_DT = "APVL_DT";
    /** DB_ETA_DT */
    public static final String DB_ETA_DT = "ETA_DT";
    /** DB_INVTY_LOC_CD */
    public static final String DB_INVTY_LOC_CD = "INVTY_LOC_CD";
    /** DB_FIRST_PROD_CTRL_CD */
    public static final String DB_FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";
    /** DB_TOC_CD */
    public static final String DB_TOC_CD = "TOC_CD";
    /** DB_TOC_NM */
    public static final String DB_TOC_NM = "TOC_NM";
    /** DB_PO_ORD_CMNT_TXT */
    public static final String DB_PO_ORD_CMNT_TXT = "PO_ORD_CMNT_TXT";
    /** DB_PO_CHRG_CD */
    public static final String DB_PO_CHRG_CD = "PO_CHRG_CD";
    /** DB_PO_EZUPTIMEZONE */
    public static final String DB_PO_EZUPTIMEZONE = "PO_EZUPTIMEZONE";
    /** DB_PO_EZUPTIME */
    public static final String DB_PO_EZUPTIME = "PO_EZUPTIME";
    /** DB_PD_EZUPTIMEZONE */
    public static final String DB_PD_EZUPTIMEZONE = "PD_EZUPTIMEZONE";
    /** DB_PD_EZUPTIME */
    public static final String DB_PD_EZUPTIME = "PD_EZUPTIME";
    /** DB_DP_EZUPTIMEZONE */
    public static final String DB_DP_EZUPTIMEZONE = "DP_EZUPTIMEZONE";
    /** DB_DP_EZUPTIME */
    public static final String DB_DP_EZUPTIME = "DP_EZUPTIME";
    /** DB_DD_EZUPTIMEZONE */
    public static final String DB_DD_EZUPTIMEZONE = "DD_EZUPTIMEZONE";
    /** DB_DD_EZUPTIME */
    public static final String DB_DD_EZUPTIME = "DD_EZUPTIME";
    /** DB_PO_PRCH_ORD_TP_CD */
    public static final String DB_PO_PRCH_ORD_TP_CD = "PO_PRCH_ORD_TP_CD";
    /** DB_VND_ISS_ORD_NUM */
    public static final String DB_VND_ISS_ORD_NUM = "VND_ISS_ORD_NUM";
    /** DB_PO_SHPG_METH_CD */
    public static final String DB_PO_SHPG_METH_CD = "PO_SHPG_METH_CD";
    /** DB_PO_DELY_PRTY_CD */
    public static final String DB_PO_DELY_PRTY_CD = "PO_DELY_PRTY_CD";
    /** DB_PO_CPLT_DELY_FLG */
    public static final String DB_PO_CPLT_DELY_FLG = "PO_CPLT_DELY_FLG";
    /** DB_PO_TOT_AMT */
    public static final String DB_PO_TOT_AMT = "PO_TOT_AMT";
    /** DB_FSR_NUM */
    public static final String DB_FSR_NUM = "FSR_NUM";
    /** DB_PO_TOT_PRC_AMT */
    public static final String DB_PO_TOT_PRC_AMT = "PO_TOT_PRC_AMT";
    /** DB_SHIP_TO_LOC_NM */
    public static final String DB_SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";
    /** DB_SHIP_TO_ADDL_LOC_NM */
    public static final String DB_SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";
    /** DB_SHIP_TO_FIRST_LINE_ADDR */
    public static final String DB_SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";
    /** DB_SHIP_TO_SCD_LINE_ADDR */
    public static final String DB_SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";
    /** DB_SHIP_TO_THIRD_LINE_ADDR */
    public static final String DB_SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";
    /** DB_SHIP_TO_FRTH_LINE_ADDR */
    public static final String DB_SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";
    /** DB_SHIP_TO_FIRST_REF_CMNT_TXT */
    public static final String DB_SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";
    /** DB_SHIP_TO_SCD_REF_CMNT_TXT */
    public static final String DB_SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";
    /** DB_SHIP_TO_CTY_ADDR */
    public static final String DB_SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";
    /** DB_SHIP_TO_ST_CD */
    public static final String DB_SHIP_TO_ST_CD = "SHIP_TO_ST_CD";
    /** DB_SHIP_TO_PROV_NM */
    public static final String DB_SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";
    /** DB_SHIP_TO_CNTY_NM */
    public static final String DB_SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";
    /** DB_SHIP_TO_POST_CD */
    public static final String DB_SHIP_TO_POST_CD = "SHIP_TO_POST_CD";
    /** DB_SHIP_TO_CTRY_CD */
    public static final String DB_SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";
    /** DB_CTRY_NM */
    public static final String DB_CTRY_NM = "CTRY_NM";
    /** DB_TRD_PTNR_LOC_CD */
    public static final String DB_TRD_PTNR_LOC_CD = "TRD_PTNR_LOC_CD";
    /** DB_PO_ORD_DTL_LINE_SUB_NUM */
    public static final String DB_PO_ORD_DTL_LINE_SUB_NUM = "PO_ORD_DTL_LINE_SUB_NUM";
    /** DB_PO_CONF_QTY */
    public static final String DB_PO_CONF_QTY = "PO_CONF_QTY";
    /** DB_PO_SCHD_QTY */
    public static final String DB_PO_SCHD_QTY = "PO_SCHD_QTY";
    /** DB_ETD_DT */
    public static final String DB_ETD_DT = "ETD_DT";
    /** DB_PO_ERR_CD */
    public static final String DB_PO_ERR_CD = "PO_ERR_CD";
    /** DB_PO_ERR_MSG_TXT */
    public static final String DB_PO_ERR_MSG_TXT = "PO_ERR_MSG_TXT";
    /** DB_PO_ORD_SRC_NM */
    public static final String DB_PO_ORD_SRC_NM = "PO_ORD_SRC_NM";
    /** DB_CPO_ORD_NUM */
    public static final String DB_CPO_ORD_NUM = "CPO_ORD_NUM";
    /** DB_CPO_DTL_LINE_NUM */
    public static final String DB_CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    /** DB_CPO_DTL_LINE_SUB_NUM */
    public static final String DB_CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";
    /** DB_PO_SUBMT_PSN_CD */
    public static final String DB_PO_SUBMT_PSN_CD = "PO_SUBMT_PSN_CD";
    /** DB_PO_ACK_STS_CD */
    public static final String DB_PO_ACK_STS_CD = "PO_ACK_STS_CD";
    /** DB_PO_CONF_DT */
    public static final String DB_PO_CONF_DT = "PO_CONF_DT";
    /** DB_VND_DROP_SHIP_FLG */
    public static final String DB_VND_DROP_SHIP_FLG = "VND_DROP_SHIP_FLG";
    /** DB_PO_ORD_SRC_CD */
    public static final String DB_PO_ORD_SRC_CD = "PO_ORD_SRC_CD";
    /** DB_CMBN_PO_LOC_CD */
    public static final String DB_CMBN_PO_LOC_CD = "CMBN_PO_LOC_CD";
    /** DB_IMPT_INV_NUM */
    public static final String DB_IMPT_INV_NUM = "IMPT_INV_NUM";
    /** DB_IMPT_INV_DO_NUM */
    public static final String DB_IMPT_INV_DO_NUM = "IMPT_INV_DO_NUM";
    /** DB_RWS_NUM */
    public static final String DB_RWS_NUM = "RWS_NUM";
    /** DB_RWS_REF_NUM */
    public static final String DB_RWS_REF_NUM = "RWS_REF_NUM";
    /** DB_RWS_DTL_LINE_NUM */
    public static final String DB_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";
    /** DB_WH_CD */
    public static final String DB_WH_CD = "WH_CD";
    /** DB_RWS_QTY */
    public static final String DB_RWS_QTY = "RWS_QTY";
    /** DB_RWS_PUT_AWAY_QTY */
    public static final String DB_RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";
    /** DB_IMPT_INV_DEST_ETA_DT */
    public static final String DB_IMPT_INV_DEST_ETA_DT = "IMPT_INV_DEST_ETA_DT";
    /** DB_IMPT_INV_STS_DESC_TXT */
    public static final String DB_IMPT_INV_STS_DESC_TXT = "IMPT_INV_STS_DESC_TXT";
    /** DB_RWS_STK_DT_TM_TS */
    public static final String DB_RWS_STK_DT_TM_TS = "RWS_STK_DT_TM_TS";
    /** DB_TRSMT_METH_TP_CD */
    public static final String DB_TRSMT_METH_TP_CD = "TRSMT_METH_TP_CD";
    /** DB_FAX_NUM */
    public static final String DB_FAX_NUM = "FAX_NUM";
    /** DB_SEND_PO_FAX_NUM */
    public static final String DB_SEND_PO_FAX_NUM = "SEND_PO_FAX_NUM";
    /** DB_SEND_PO_EML_ADDR */
    public static final String DB_SEND_PO_EML_ADDR = "SEND_PO_EML_ADDR";
    /** DB_AP_VND_INV_NUM */
    public static final String DB_AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB_VND_INV_NUM */
    public static final String DB_VND_INV_NUM = "VND_INV_NUM";
    /** DB_DELY_ORD_NUM */
    public static final String DB_DELY_ORD_NUM = "DELY_ORD_NUM";
    /** DB_INV_DT */
    public static final String DB_INV_DT = "INV_DT";
    /** DB_PMT_DUE_DT */
    public static final String DB_PMT_DUE_DT = "PMT_DUE_DT";
    /** DB_AC_OC_FOB_COST_AMT */
    public static final String DB_AC_OC_FOB_COST_AMT = "AC_OC_FOB_COST_AMT";
    /** DB_CCY_CD */
    public static final String DB_CCY_CD = "CCY_CD";
    /** DB_ACCT_AUTH_DT */
    public static final String DB_ACCT_AUTH_DT = "ACCT_AUTH_DT";
    /** DB_PAY_CCY_CD */
    public static final String DB_PAY_CCY_CD = "PAY_CCY_CD";
    /** DB_ACCT_INV_STS_CD */
    public static final String DB_ACCT_INV_STS_CD = "ACCT_INV_STS_CD";
    /** DB_PO_SEND_FLG */
    public static final String DB_PO_SEND_FLG = "PO_SEND_FLG";

    /**
     * Indicates whether there is a PO message attached to a PO
     */
    public static final String DB_XX_YES_NO_CD = "XX_YES_NO_CD";
    /** DB_INVTY_LOC_NM */
    public static final String DB_INVTY_LOC_NM = "INVTY_LOC_NM";
    /** DB_VND_INVTY_LOC_CD */
    public static final String DB_VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";
    /** DB_DS_PO_TP_CD */
    public static final String DB_DS_PO_TP_CD = "DS_PO_TP_CD";
    /** DB_CARR_ACCT_NUM */
    public static final String DB_CARR_ACCT_NUM = "CARR_ACCT_NUM";
    /** DB_CUST_TECH_PSN_NM */
    public static final String DB_CUST_TECH_PSN_NM = "CUST_TECH_PSN_NM";
    /** DB_CTAC_PSN_NM */
    public static final String DB_CTAC_PSN_NM = "CTAC_PSN_NM";
    /** DB_PO_HDR_TXT */
    public static final String DB_PO_HDR_TXT = "PO_HDR_TXT";
    /** DB_PO_HDR_NOTE_TXT */
    public static final String DB_PO_HDR_NOTE_TXT = "PO_HDR_NOTE_TXT";
    /** DB_PO_ITEM_TXT */
    public static final String DB_PO_ITEM_TXT = "PO_ITEM_TXT";
    /** DB_MDL_SER_NUM */
    public static final String DB_MDL_SER_NUM = "MDL_SER_NUM";
    /** DB_PRCH_REQ_NUM */
    public static final String DB_PRCH_REQ_NUM = "PRCH_REQ_NUM";
    /** DB_PRCH_REQ_LINE_NUM */
    public static final String DB_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";
    /** DB_DETAIL_AMOUNT */
    public static final String DB_DETAIL_AMOUNT = "DETAIL_AMOUNT";
    /** DB_CUST_ISS_PO_NUM */
    public static final String DB_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    /** DB_CUST_ISS_PO_DT */
    public static final String DB_CUST_ISS_PO_DT = "CUST_ISS_PO_DT";
    /** DB_CPO_ORD_TP_CD */
    public static final String DB_CPO_ORD_TP_CD = "CPO_ORD_TP_CD";
    /** DB_ADMIN_PSN_CD */
    public static final String DB_ADMIN_PSN_CD = "ADMIN_PSN_CD";
    /** DB_BILL_TO_CUST_CD */
    public static final String DB_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    /** DB_SELL_TO_CUST_CD */
    public static final String DB_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";
    /** DB_FRT_CHRG_TO_CD */
    public static final String DB_FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";
    /** DB_FRT_CHRG_METH_CD */
    public static final String DB_FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";
    /** DB_SHPG_SVC_LVL_CD */
    public static final String DB_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";
    /** DB_CUST_UOM_CD */
    public static final String DB_CUST_UOM_CD = "CUST_UOM_CD";
    /** DB_CARR_CD */
    public static final String DB_CARR_CD = "CARR_CD";
    /** DB_SHPG_PLN_NUM */
    public static final String DB_SHPG_PLN_NUM = "SHPG_PLN_NUM";
    /** DB_EZUPTIME_SP */
    public static final String DB_EZUPTIME_SP = "EZUPTIME_SP";
    /** DB_EZUPTIMEZONE_SP */
    public static final String DB_EZUPTIMEZONE_SP = "EZUPTIMEZONE_SP";
    /** DB_INTL_VND_FLG */
    public static final String DB_INTL_VND_FLG = "INTL_VND_FLG";
    /** DB_VND_SYS_TP_CD */
    public static final String DB_VND_SYS_TP_CD = "VND_SYS_TP_CD";
    /** DB_VND_SHPG_TP_CD */
    public static final String DB_VND_SHPG_TP_CD = "VND_SHPG_TP_CD";
    /** DB_FIRST_ORD_MSG_TXT */
    public static final String DB_FIRST_ORD_MSG_TXT = "FIRST_ORD_MSG_TXT";
    /** DB_SCD_ORD_MSG_TXT */
    public static final String DB_SCD_ORD_MSG_TXT = "SCD_ORD_MSG_TXT";
    /** DB_THIRD_ORD_MSG_TXT */
    public static final String DB_THIRD_ORD_MSG_TXT = "THIRD_ORD_MSG_TXT";
    /** DB_DEAL_CCY_CD */
    public static final String DB_DEAL_CCY_CD = "DEAL_CCY_CD";
    /** DB_VND_PMT_TERM_DESC_TXT */
    public static final String DB_VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";
    /** DB_ETA_ETD_DT */
    public static final String DB_ETA_ETD_DT = "ETA_ETD_DT";
    /** SHPG_MODE_OD_PO_CANCELLED */
    public static final String SHPG_MODE_OD_PO_CANCELLED = "24";

    /** DB column: SHIP_TO_CUST_CD */
    public static final String DB_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB column: SHIP_CPLT_CD */
    public static final String DB_SHIP_CPLT_CD = "SHIP_CPLT_CD";

    /** DB column: SHPG_INVTY_LOC_CD */
    public static final String DB_SHPG_INVTY_LOC_CD = "SHPG_INVTY_LOC_CD";

    /** DB column: INVTY_CTRL_FLG */
    public static final String DB_INVTY_CTRL_FLG = "INVTY_CTRL_FLG";

    /** DB column: WH_WH_CD */
    public static final String DB_WH_WH_CD = "WH_WH_CD";

    /** DB column: WH_LOC_NM */
    public static final String DB_WH_LOC_NM = "WH_LOC_NM";

    /** DB column: WH_ADDL_LOC_NM */
    public static final String DB_WH_ADDL_LOC_NM = "WH_ADDL_LOC_NM";

    /** DB column: WH_FIRST_LINE_ADDR */
    public static final String DB_WH_FIRST_LINE_ADDR = "WH_FIRST_LINE_ADDR";

    /** DB column: WH_SCD_LINE_ADDR */
    public static final String DB_WH_SCD_LINE_ADDR = "WH_SCD_LINE_ADDR";

    /** DB column: WH_THIRD_LINE_ADDR */
    public static final String DB_WH_THIRD_LINE_ADDR = "WH_THIRD_LINE_ADDR";

    /** DB column: WH_FRTH_LINE_ADDR */
    public static final String DB_WH_FRTH_LINE_ADDR = "WH_FRTH_LINE_ADDR";

    /** DB column: WH_FIRST_REF_CMNT_TXT */
    public static final String DB_WH_FIRST_REF_CMNT_TXT = "WH_FIRST_REF_CMNT_TXT";

    /** DB column: WH_SCD_REF_CMNT_TXT */
    public static final String DB_WH_SCD_REF_CMNT_TXT = "WH_SCD_REF_CMNT_TXT";

    /** DB column: WH_CTY_ADDR */
    public static final String DB_WH_CTY_ADDR = "WH_CTY_ADDR";

    /** DB column: WH_ST_CD */
    public static final String DB_WH_ST_CD = "WH_ST_CD";

    /** DB column: WH_PROV_NM */
    public static final String DB_WH_PROV_NM = "WH_PROV_NM";

    /** DB column: WH_POST_CD */
    public static final String DB_WH_POST_CD = "WH_POST_CD";

    /** DB column: WH_CTRY_CD */
    public static final String DB_WH_CTRY_CD = "WH_CTRY_CD";

    /** DB column: WH_CNTY_NM */
    public static final String DB_WH_CNTY_NM = "WH_CNTY_NM";

    /**
     * DB Column Name: COA Company Dply Flag
     */
    public static final String DB_COLUMN_COA_CMPY_DPLY_FLG = "COA_CMPY_DPLY_FLG";

    /**
     * DB Column Name: COA Affiliate Dply Flag
     */
    public static final String DB_COLUMN_COA_AFFL_DPLY_FLG = "COA_AFFL_DPLY_FLG";

    /**
     * DB Column Name: COA Branch Dply Flag
     */
    public static final String DB_COLUMN_COA_BR_DPLY_FLG = "COA_BR_DPLY_FLG";

    /**
     * DB Column Name: COA CostCentor Dply Flag
     */
    public static final String DB_COLUMN_COA_CC_DPLY_FLG = "COA_CC_DPLY_FLG";

    /**
     * DB Column Name: COA Account Dply Flag
     */
    public static final String DB_COLUMN_COA_ACCT_DPLY_FLG = "COA_ACCT_DPLY_FLG";

    /**
     * DB Column Name: COA Product Dply Flag
     */
    public static final String DB_COLUMN_COA_PROD_DPLY_FLG = "COA_PROD_DPLY_FLG";

    /**
     * DB Column Name: COA Channel Dply Flag
     */
    public static final String DB_COLUMN_COA_CH_DPLY_FLG = "COA_CH_DPLY_FLG";

    /**
     * DB Column Name: COA Project Dply Flag
     */
    public static final String DB_COLUMN_COA_PROJ_DPLY_FLG = "COA_PROJ_DPLY_FLG";

    /**
     * DB Column Name: COA Extension Dply Flag
     */
    public static final String DB_COLUMN_COA_EXTN_DPLY_FLG  = "COA_EXTN_DPLY_FLG";

    /**
     * DB Column Name: COA Company Code
     */
    public static final String DB_COLUMN_COA_CMPY_CD = "COA_CMPY_CD";

    /**
     * DB Column Name: COA Affiliate Code
     */
    public static final String DB_COLUMN_COA_AFFL_CD = "COA_AFFL_CD";

    /**
     * DB Column Name: COA Branch Code
     */
    public static final String DB_COLUMN_COA_BR_CD = "COA_BR_CD";

    /**
     * DB Column Name: COA CostCentor Code
     */
    public static final String DB_COLUMN_COA_CC_CD = "COA_CC_CD";

    /**
     * DB Column Name: COA Account Code
     */
    public static final String DB_COLUMN_COA_ACCT_CD = "COA_ACCT_CD";

    /**
     * DB Column Name: COA Product Code
     */
    public static final String DB_COLUMN_COA_PROD_CD = "COA_PROD_CD";

    /**
     * DB Column Name: COA Channel Code
     */
    public static final String DB_COLUMN_COA_CH_CD = "COA_CH_CD";

    /**
     * DB Column Name: COA Project Code
     */
    public static final String DB_COLUMN_COA_PROJ_CD = "COA_PROJ_CD";

    /**
     * DB Column Name: COA Extension Code
     */
    public static final String DB_COLUMN_COA_EXTN_CD = "COA_EXTN_CD";

    // ** for Report **/
    /** REPRINT */
    public static final String REPRINT = "REPRINT";
    /** TEL */
    public static final String TEL = "TEL:";
    /** FAX */
    public static final String FAX = "FAX:";
    /** BLANK_TEL */
    public static final String BLANK_TEL = "                 ";
    /** SCE_ORD_TP_CD_DP */
    public static final String SCE_ORD_TP_CD_DP = "DP";
    /** SCE_ORD_TP_CD_DG */
    public static final String SCE_ORD_TP_CD_DG = "DG";
    /** COLON */
    public static final String COLON = ":";
    /** ASTERISK */
    public static final String ASTERISK = "*";

    /** String: COMMA "," */
    public static final String COMMA = ",";

    /** Error Message Kind */
    public static final String ERR_MSG_KIND = "E";

    /** yyyyMMddHHmmssSSS */
    public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Mail From Group ID */
    public static final String MAIL_FROM_GROUP_ID = "FROM0003";

    /** Mail From Group Key */
    public static final String MAIL_FROM_GROUP_KEY = "NPA";

    /** Mail Approval Group ID */
    public static final String MAIL_APLV_GROUP_ID = "NPAL1500APL";

    /** Mail Error Group ID */
    public static final String MAIL_ERR_GROUP_ID = "NPAL1500ERR";

    /** Mail Approval Template ID */
    public static final String MAIL_TMPL_ID_SUBMIT = "NPAL1500M01";

    /** Mail Approval Template ID */
    public static final String MAIL_TMPL_ID_APPROVE = "NPAL1500M02";

    /** Mail Approval Template ID */
    public static final String MAIL_TMPL_ID_REJECT = "NPAL1500M03";

    /** Mail Error Template ID */
    public static final String MAIL_TMPL_ID_ERROR = "NPAL1500M04";

    /** TAB Name: Detail */
    public static final String TAB_DETAIL = "TAB_Detail";

    // QC#25714 Add Start
    /** TAB Name: AP Invoice */
    public static final String TAB_AP_INVOICE = "TAB_APInvoice";
    // QC#25714 Add End
    
    /** for Error message */
    public static final String MAIL_GRP_TYPE = "System common";

    /** for Error message */
    public static final String MAIL_GRP_KEY = "";

    /** Serial Validation API Mode: InBound */
    public static final String SER_VAL_API_MODE_INBOUND = "02";

    /** Serial Validation API Mode: BuyBack */
    public static final String SER_VAL_API_MODE_BUYBACK = "04";

    // =================================================
    // Message Code
    // =================================================
    /** Error Message: The process abended. */
    public static final String NPAM0027E = "NPAM0027E";

    /** This data has been updated by another user. */
    public static final String NPAM0006E = "NPAM0006E";
    /** NPAM0002E */
    public static final String NPAM0002E = "NPAM0002E";
    /** NPAM1202E */
    public static final String NPAM1202E = "NPAM1202E";
    /** NPAM1203E */
    public static final String NPAM1203E = "NPAM1203E";
    /** NPAM1204E */
    public static final String NPAM1204E = "NPAM1204E";
    /** NPAM1205E */
    public static final String NPAM1205E = "NPAM1205E";
    /** NPAM1206E */
    public static final String NPAM1206E = "NPAM1206E";
    /** NPAM1207E */
    public static final String NPAM1207E = "NPAM1207E";
    /** NPAM1208E */
    public static final String NPAM1208E = "NPAM1208E";
    /** NPAM1209E */
    public static final String NPAM1209E = "NPAM1209E";
    /** NPAM1209E */
    public static final String NPAM1210E = "NPAM1210E";
    /** NPAM1209E */
    public static final String NPAM1211E = "NPAM1211E";
    /** NPAM1217E */
    public static final String NPAM1217E = "NPAM1217E";
    /** NPAM1218E */
    public static final String NPAM1218E = "NPAM1218E";
    /** NPAM1219E */
    public static final String NPAM1219E = "NPAM1219E";
    /** NPAM1220E */
    public static final String NPAM1220E = "NPAM1220E";
    /** NPAM1221E */
    public static final String NPAM1221E = "NPAM1221E";
    /** NPAM1222E */
    public static final String NPAM1222E = "NPAM1222E";
    /** NPAM1223E */
    public static final String NPAM1223E = "NPAM1223E";
    /** NPAM1277W */
    public static final String NPAM1277W = "NPAM1277W";
    /** NPAM1278W */
    public static final String NPAM1278W = "NPAM1278W";
    /** NPAM1279E */
    public static final String NPAM1279E = "NPAM1279E";
    /** NPAM1283E */
    public static final String NPAM1283E = "NPAM1283E";
    /** NPAM0073E */
    public static final String NPAM0073E = "NPAM0073E";
    /** NPAM1511E */
    public static final String NPAM1511E = "NPAM1511E";
    /** NPAM1510E */
    public static final String NPAM1510E = "NPAM1510E";
    /** NPAM1578E */
    public static final String NPAM1578E = "NPAM1578E";

    //QC#27770 Add Start
    /** NLAM0077E */
    public static final String NLAM0077E = "NLAM0077E";
    //QC#27770 Add End
    
    /** There is a PO Detail for which the status cannot be closed. */
    public static final String NPAM1311E = "NPAM1311E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /**
     * Error Message: The mail template cannot be acquired. <Template
     * ID: [@]>
     */
    public static final String NWAM0319E = "NWAM0319E";

    /**
     * Error Message: Valid CONSTANT data <Type: [@], Table
     * [VAR_CHAR_CONST] Key: [@]> is not registered.
     */
    public static final String NMAM8027E = "NMAM8027E";

    /**
     * Error Message: E-mail address <Type: [@], Group: [@], Key: [@]>
     * cannot be obtained.
     */
    public static final String NMAM8028E = "NMAM8028E";

    /** Error Message: Entered Vendor Code is not a domestic vendor. */
    public static final String NLCM0072E = "NLCM0072E";

    /** Error Message: [@] does not exist in Master. */
    public static final String NPAM0076E = "NPAM0076E";

    /**
     * Error Message: It cannot be processed since there is no detail.
     */
    public static final String NPAM1351E = "NPAM1351E";

    /** Error Message: Please enter Ship To. */
    public static final String NPAM0109E = "NPAM0109E";

    /**
     * Error Message: Please enter Cancel Qty less than "PO Qty -
     * Received Qty".
     */
    public static final String NPAM0110E = "NPAM0110E";

    /**
     * Error Message: The process cannot be executed because the "PO
     * Type" is not "Int'l Vendor PO".
     */
    public static final String NPAM0111E = "NPAM0111E";

    /** Error Message: PO cannot be canceled. RWS already exists. */
    public static final String NPAM0112E = "NPAM0112E";

    /**
     * Error Message: Validation error. Please check combination of
     * Vendor Code, Shipping Method and Delivery Priority.
     */
    public static final String NPAM0114E = "NPAM0114E";

    /** Error Message: Ship To is required when the WH Code is [@]. */
    public static final String NPAM0115E = "NPAM0115E";

    /**
     * Error Message: Process ended normally. [Warning] It failed to
     * send email to PO submit user.
     */
    public static final String NPAM0116W = "NPAM0116W";

    /** This is not a purchased mdse. Cannot process. */
    public static final String NPAM0117E = "NPAM0117E";

    /** Error Message: PO Qty cannot be changed. RWS already exists. */
    public static final String NPAM1197E = "NPAM1197E";

    /**
     * The process cannot be executed because the PO is not submitted.
     */
    public static final String NPAM1268E = "NPAM1268E";

    /** 'Ship To Address Line 1 has not been entered. */
    public static final String NWAM0083E = "NWAM0083E";

    /** 'Ship To Country Code' has not been entered. */
    public static final String NWAM0089E = "NWAM0089E";

    /** This is not a domestically purchased mdse. Cannot process. */
    public static final String NPAM0074E = "NPAM0074E";

    /** Vendor for the merchandise code is different. Is this OK? */
    public static final String NPAM1223W = "NPAM1223W";

    /** The format of [@] is incorrect. */
    public static final String NPAM1225E = "NPAM1225E";

    /**
     * Cannot make order for same MDSE by separated line for order to
     * specified vendor.
     */
    public static final String NPAM1228E = "NPAM1228E";

    /**
     * If MDSE vendor is different from vendor on the header, enter
     * email address.
     */
    public static final String NPAM1229E = "NPAM1229E";

    /**
     * Too many search results. Please narrow your search criteria and
     * retry.
     */
    public static final String NPAM0015W = "NPAM0015W";

    /** No search results found of [@]. */
    public static final String NPAM0020E = "NPAM0020E";

    /** There is a PO Detail for which the status cannot be cancelled. */
    public static final String NPAM1319E = "NPAM1319E";

    /**
     * Since the target MDSE CD is set as "DISCONTINUED", purchase
     * order cannot be created.
     */
    public static final String NPZM0140E = "NPZM0140E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** */
    public static final String NPAM1301E = "NPAM1301E";

    /** */
    public static final String NPAM1302W = "NPAM1302W";

    /** Since RWS has already been issued, Loc To can not be changed. */
    public static final String NPAM1340E = "NPAM1340E";

    /** The total amount exceeds the maximum limit. */
    public static final String NPAM0057E = "NPAM0057E";

    /** . */
    public static final String NPAM0120E = "NPAM0120E";

    /** The details of the process target have not been selected. */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * "Receiving Work Sheet" status is other than "Cancel". Cannot
     * process.
     */
    public static final String NPAM0067E = "NPAM0067E";

    /** Result Flag : Error */
    public static final String RSLT_FLG_ERROR = "E";

    /**
     * To enter more than 200 + Serial Numbers, contact the system
     * administrator.
     */
    public static final String NPAM0038I = "NPAM0038I";

    /** Error occurred in [@] API.[@] */
    public static final String NPAM1323E = "NPAM1323E";

    /**
     * All detail lines must have Inventory Request in case of entered
     * PO Type.
     */
    public static final String NPAM1369E = "NPAM1369E";

    /** The entered @ does not exist in Master. */
    public static final String NPAM1361E = "NPAM1361E";

    /** The machine of entered Config# is allocated with other order. */
    public static final String NPAM1370E = "NPAM1370E";

    /** NPAM1488E */
    public static final String NPAM1488E = "NPAM1488E";

    /** NPAM1178E */
    public static final String NPAM1178E = "NPAM1178E";

    /** ZZZM9003I */
    public static final String ZZZM9003I = "ZZZM9003I";
    /** NLAM1232E */
    public static final String NLAM1232E = "NLAM1232E";

    /**
     * Ship-To Code should be same as Destination WH Code. Please
     * click ">>" button next to Destination WH Code to set Ship-To
     * Code same as Destination WH Code.
     */
    public static final String NPAM1506E = "NPAM1506E";

    /**
     * The Process cannot be executed because Inventory request
     * exists.
     */
    public static final String NPAM1514E = "NPAM1514E";

    /**
     * Message is too long [@ characters]. It must be less than or
     * equal to @ characters.
     */
    public static final String NPAM1264E = "NPAM1264E";

    /** You can not use the entered Destination Retail WH. */
    public static final String NPAM1431E = "NPAM1431E";

    /** The value for [@] must be over [@]. */
    public static final String NPAM1183E = "NPAM1183E";

    /** For [@], '0' or less than '0' cannot be specified. */
    public static final String NPAM0046E = "NPAM0046E";
    /** NPAM1365E */
    public static final String NPAM1365E = "NPAM1365E";
    /** NPAM1366E */
    public static final String NPAM1366E = "NPAM1366E";
    /** NPAM1367E */
    public static final String NPAM1367E = "NPAM1367E";

    /** for print **/
    public static final String NPZM0081E = "NPZM0081E";
    /** NPAM1329E */
    public static final String NPAM1329E = "NPAM1329E";

    /** Please select Ship To Customer Code from popup. **/
    public static final String NPAL1496W = "NPAL1496W";

    /**
     * "Receiving Work Sheet" status is other than "Cancel". Cannot
     * reduce Order Qty.
     **/
    public static final String NPAM1509E = "NPAM1509E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and cannot proceed.
     **/
    public static final String NPAM1199E = "NPAM1199E";

    /** The entered "Serial Number" is duplicated. **/
    public static final String NPAM0039E = "NPAM0039E";

    /** Please enter today's or later date. **/
    public static final String NPZM0041E = "NPZM0041E";

    /**
     * The process cannot be executed because the "PO Status" is not
     * "Saved".
     **/
    public static final String NPAM0032E = "NPAM0032E";

    /** You can not select Asset PO line with this PO type. **/
    public static final String NPAM1429E = "NPAM1429E";

    /** The entered Serial# is incorrect. **/
    public static final String NPAM1345E = "NPAM1345E";

    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The format of [@] is incorrect. */
    public static final String NPAM1193E = "NPAM1193E";

    /** Maximum number of digits exceeded.[@] */
    public static final String NPAM1320E = "NPAM1320E";

    /** [@] is invalid code. */
    public static final String NPAM0080E = "NPAM0080E";

    /** button next to Item# to set Item Description.  */
    public static final String NPAM1585E = "NPAM1585E";

    /** For SET items, Please include the Parent Details. */
    public static final String NPAM1588E = "NPAM1588E";

    /** message id : NLAM1118E */
    public static final String MSG_ID_NLAM1118E = "NLAM1118E";

    /** Since Open RWS is existed, selected lines cannot be @. */
    public static final String NPAM1598E = "NPAM1598E";

    /**QC#18671 Add.
     * Suppier Item Code is duplicated,
     * Please select another item or review ASL.
     */
    public static final String NPAM1602E = "NPAM1602E";

// START 2017/11/07 [QC#21849, ADD]
    /** The value you entered is incorrect. */
    public static final String NPAM0023E = "NPAM0023E";

    /** You can not select multiple PO line type. */
    public static final String NPAM1430E = "NPAM1430E";

    /** The combination of specified input parameters [@] and [@] is incorrect. */
    public static final String NPAM1363E = "NPAM1363E";
// END   2017/11/07 [QC#21849, ADD]
    
// START 2017/12/12 [QC#14858, ADD]
    /** Enter a value between '0' and 'Order Qty'.  */
    public static final String NPAM1610E = "NPAM1610E";
    
    /** Blank Item# have exceeded TEXT ITEM in Item Master.  */
    public static final String NPAM1611E = "NPAM1611E";
// END 2017/12/12 [QC#14858, ADD]

 // START [QC#18602(Sol#102), ADD]
    /** You can not select this PO line type when PO Type is Blanket PO. */
    public static final String NPAM1612E = "NPAM1612E";
    
    /** The combination of specified input parameters 'PO Type' and 'Document Source Type' cannot be allowed to add line after PO Status has been Closed.  */
    public static final String NPAM1613E = "NPAM1613E";
// END [QC#18602(Sol#102), ADD]

    // START 2018/07/12 S.Katsuma [QC#26867,ADD]
    /** The item of serial# is the specified configuration included item. Please add from Add Config button instead of Add button. */
    public static final String NPAM1620E = "NPAM1620E";
    // END 2018/07/12 S.Katsuma [QC#26867,ADD]

    // START 2018/12/13 M.Naito [QC#29027,ADD]
    /** The Set Merchandise does not exist in the Vender. Please enter child item. */
    public static final String NPAM1639E = "NPAM1639E";
    // END 2018/12/13 M.Naito [QC#29027,ADD]

    // START 2019/10/30 M.Naito [QC#53811,ADD]
    /** Changed quantity is less than RWS quantity. Please change the quantity after executing RWS Cancel. */
    public static final String NPAM1645E = "NPAM1645E";
    // END 2019/10/30 M.Naito [QC#53811,ADD]
    
    // START 2022/09/12 T.NEMA [QC#60413, ADD]
    /** There are open transactions. Please check the AP Invoice TAB and confirm to AP team. */
    public static final String NPAM1654E = "NPAM1654E";
    // END   2022/09/12 T.NEMA [QC#60413, ADD]

    // START 2022/12/09 M.Kikushima[QC#60604, ADD]
    /**
     * The specified "Destination Warehouse" is Manual Drop Ship and the "Ship to Custormer" is CSA WH. Please check if it is correct.
     */
    public static final String NPAM1655W = "NPAM1655W";
    // END 2022/12/09 M.Kikushima[QC#60604, ADD]

    // START 2023/04/03 TZ.Win[QC#60966, ADD]
    /**
     * Vendor Ship Date should be later than Create Date.
     */
    public static final String NPAM1657E = "NPAM1657E";
    /**
     * Vendor Ship Date should be earlier than Date & Time Needed.
     */
    public static final String NPAM1658E = "NPAM1658E";
    // END 2023/04/03 TZ.Win[QC#60966, ADD]

    /** WH */
    public static final String WH = "WH";
    /** MSG_SEND_PO_MAIL */
    public static final String MSG_SEND_PO_MAIL = "MSG_SEND_PO_MAIL";

    /** Business Process Log: Subsystem ID */
    public static final String BIZ_PROC_LOG_SUB_SYS_ID = "NPA";

    /** Business Process Log: Process ID */
    public static final String BIZ_PROC_LOG_PROC_ID = "PROCUREMENT";

    /** Business Process Log: document Type */
    public static final String BIZ_PROC_LOG_DOC_TP_CD = "PROCR";

    /** Business Process Log: Event ID = SUBMIT */
    public static final String BIZ_PROC_LOG_EVENT_ID_SUBMIT = "SUBMIT";

    /** Business Process Log: Event ID = CANCEL */
    public static final String BIZ_PROC_LOG_EVENT_ID_CANCEL = "CANCEL";

    /** Business Process Log: Event ID = APPROVE */
    public static final String BIZ_PROC_LOG_EVENT_ID_APPROVE = "APPROVE";

    /** Business Process Log: Event ID = REJECT */
    public static final String BIZ_PROC_LOG_EVENT_ID_REJECT = "REJECT";

    /** Business Process Log: Event ID = CLOSE */
    public static final String BIZ_PROC_LOG_EVENT_ID_CLOSE = "CLOSE";

    /** Business Process Log: Event ID = UPDATE */
    public static final String BIZ_PROC_LOG_EVENT_ID_UPDATE = "UPDATE";

    /** Vendor System Type Code = P */
    public static final String VND_SYS_TP_CD_P = "P";

    /** SSM Parameter: shpgStsCd */
    public static final String SSM_SHPG_STS_CD = "shpgStsCd";

    /** SSM Parameter: trxSrcTpCd */
    public static final String SSM_TRX_SRC_TP_CD = "trxSrcTpCd";

    /** SSM Parameter: whCd */
    public static final String SSM_WH_CD = "whCd";

    /** SSM Parameter: mdseTpCd1 */
    public static final String SSM_MDSE_TP_CD1 = "mdseTpCd1";

    /** SSM Parameter: mdseTpCd2 */
    public static final String SSM_MDSE_TP_CD2 = "mdseTpCd2";

    /** TRSMT_METH_TP_SCRN */
    public static final String TRSMT_METH_TP_SCRN = "TRSMT_METH_TP_SCRN";

    /** DB column: AVAL_PO_QTY */
    public static final String SSM_AVAL_PO_QTY = "avalPoQty";

    /** DB column: RTE_STS_CD */
    public static final String SSM_RTE_STS_CD = "rteStsCd";

    /**
     * SSM_MAX_DIGIT
     */
    public static final String SSM_MAX_DIGIT = "999999999999999.99";
    /** BROKER_INVOICE */
    public static final String BROKER_INVOICE = "02";
    /** FIRST_DTL_LINE_NUM */
    public static final String FIRST_DTL_LINE_NUM = "001";
    /** PO_DTL_LINE_NUM_000 */
    public static final String PO_DTL_LINE_NUM_000 = "000";

    // PO_MSG
    /** Internal Message */
    public static final String PO_MSG_TP_INTERNAL_MESSAGE = "02";

    /** Special Instructions */
    public static final String PO_MSG_TP_SPECIAL_INSTRUCTION = "03";

    /** Receiver Note */
    public static final String PO_MSG_TP_RECEIVER_NOTE = "04";

    /** Shipper Note */
    public static final String PO_MSG_TP_SHIPPER_NOTE = "05";

    /** PO_TYPE SUB_CONTRACT */
    public static final String PO_TYPE_SUB_CONTRACT = "SC";

    // For Address Validation Check(NMZC0030)
    /** FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "Address1";

    /** FIRST_LINE_ADDR */
    public static final String SCD_LINE_ADDR = "Address2";

    /** FIRST_LINE_ADDR */
    public static final String THIRD_LINE_ADDR = "Address3";

    /** FIRST_LINE_ADDR */
    public static final String FRTH_LINE_ADDR = "Address4";

    /** FIRST_LINE_ADDR */
    public static final String CTY_ADDR = "City";

    /** FIRST_LINE_ADDR */
    public static final String ST_CD = "State";

    /** FIRST_LINE_ADDR */
    public static final String PROV_NM = "Province";

    /** FIRST_LINE_ADDR */
    public static final String CNTY_NM = "County";

    /** FIRST_LINE_ADDR */
    public static final String POST_CD = "Postal Code";

    /** FIRST_LINE_ADDR */
    public static final String CTRY_CD = "Country";

    /** NO_ERROR */
    public static final String NMZC0030_NO_ERROR = "0";

    /** NO_ERROR */
    public static final String NMZC0030_SUGGESTED = "1";

    /** NO_ERROR */
    public static final String NMZC0030_ERROR = "9";
    /** DB_COLUMN_PRNT_VND_CD */
    public static final String DB_COLUMN_PRNT_VND_CD = "PRNT_VND_CD";
    /** DB_COLUMN_PRNT_VND_NM */
    public static final String DB_COLUMN_PRNT_VND_NM = "PRNT_VND_NM";
    /** DB_COLUMN_VND_CD */
    public static final String DB_COLUMN_VND_CD = "VND_CD";
    /** DB_COLUMN_LOC_NM */
    public static final String DB_COLUMN_LOC_NM = "LOC_NM";
    /** DB_COLUMN_PRF_CARR_CD */
    public static final String DB_COLUMN_PRF_CARR_CD = "PRF_CARR_CD";
    /** DB_COLUMN_CARR_NM */
    public static final String DB_COLUMN_CARR_NM = "CARR_NM";
    /** DB_COLUMN_GLBL_CMPY_CD */
    public static final String DB_COLUMN_GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** DB_COLUMN_PO_ORD_NUM */
    public static final String DB_COLUMN_PO_ORD_NUM = "PO_ORD_NUM";
    /** DB_COLUMN_DS_PO_TP_CD */
    public static final String DB_COLUMN_DS_PO_TP_CD = "DS_PO_TP_CD";
    /** DB_COLUMN_DS_PO_TP_DESC_TXT */
    public static final String DB_COLUMN_DS_PO_TP_DESC_TXT = "DS_PO_TP_DESC_TXT";
    /** DB_COLUMN_RTL_WH_CD */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";
    /** DB_COLUMN_RTL_SWH_CD */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";
    /** DB_COLUMN_RTL_WH_NM */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";
    /** DB_COLUMN_MAX_LINE_NUM */
    public static final String DB_COLUMN_MAX_LINE_NUM = "MAX_LINE_NUM";

    /** ZERO */
    public static final int ZERO = 0;
    public static final String ZERO_STRING = "0";

    /**  */
    public static final String PO_STS_CANCELED = "";

    /**  */
    public static final String PO_STS_CLOSED = "";

    /** TRSMT_METH_TP */
    public static final String TRSMT_METH_TP_PDF = "00";
    /** TRSMT_METH_TP_EMAIL */
    public static final String TRSMT_METH_TP_EMAIL = "05";
    /** TRSMT_METH_TP_FAX */
    public static final String TRSMT_METH_TP_FAX = "06";
    /** TRSMT_METH_TP_PRINTER */
    public static final String TRSMT_METH_TP_PRINTER = "07";

    /** CUSA Global Company Code.(Default Value) */
    public static final String DEF_CUSA_GLBL_CMPY_CD = "ABR";

    /** Po Detal Line Max Count.(Default Value) */
    public static final String DEF_PO_LINE_MAX_CNT = "999";

    /** Po Qlfy Cd for Customer Drop Ship.(Default Value) */
    public static final String DEF_PO_CUST_DROP_SHIP_QLFY_CD = "DS";

    /** DB Item Length : MAIL_ADDRESS_LENGTH */
    public static final int MAIL_ADDRESS_LENGTH = 700;

    /** VarCharConst : TECH_INSRC_PO_QLFY_CD */
    public static final String TECH_INSRC_PO_QLFY_CD = "TECH_INSRC_PO_QLFY_CD";

    /** EVENT_NM */
    /** for BL02(Search) */
    public static final String EVENT_NM_NPAL1500_INIT = "NPAL1500_INIT";
    /** EVENT_NM_NPAL1500_ADD_NEWLINE */
    public static final String EVENT_NM_NPAL1500_ADD_NEWLINE = "NPAL1500Scrn00_Add_NewLine";
    /** EVENT_NM_NPAL1500_SEARCH */
    public static final String EVENT_NM_NPAL1500_SEARCH = "NPAL1500Scrn00_Search";
    /** EVENT_NM_NPAL1500_CMN_CLEAR */
    public static final String EVENT_NM_NPAL1500_CMN_CLEAR = "NPAL1500Scrn00_CMN_Clear";
    /** EVENT_NM_NPAL1500_CMN_RESET */
    public static final String EVENT_NM_NPAL1500_CMN_RESET = "NPAL1500Scrn00_CMN_Reset";
    /** EVENT_NM_NPAL1500_PAGE_NEXT */
    public static final String EVENT_NM_NPAL1500_PAGE_NEXT = "NPAL1500Scrn00_PageNext";
    /** EVENT_NM_NPAL1500_PAGE_PREV */
    public static final String EVENT_NM_NPAL1500_PAGE_PREV = "NPAL1500Scrn00_PagePrev";
    /** EVENT_NM_NPAL1500_NWAL1130 */
    public static final String EVENT_NM_NPAL1500_NWAL1130 = "NPAL1500_NWAL1130";
    /** EVENT_NM_NPAL1500_NPAL1010 */
    public static final String EVENT_NM_NPAL1500_NPAL1010 = "NPAL1500_NPAL1010";
    /** EVENT_NM_NPAL1500_NMAL6800 */
    public static final String EVENT_NM_NPAL1500_NMAL6800 = "NPAL1500_NMAL6800";
    /** EVENT_NM_NPAL1500_GET_MDSE_INFO */
    public static final String EVENT_NM_NPAL1500_GET_MDSE_INFO = "NPAL1500Scrn00_Get_MdseInfo";
    /** EVENT_NM_NPAL1500_ONCHNG_PO_TYPE */
    public static final String EVENT_NM_NPAL1500_ONCHNG_PO_TYPE = "NPAL1500Scrn00_OnChange_PoType";
    /** EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG */
    public static final String EVENT_NM_NPAL1500_OPENWIN_ACCT_CHRG = "NPAL1500Scrn00_OpenWin_AccountChrg";
    /** EVENT_NM_NPAL1500_OPENWIN_ACCT_ACRL */
    public static final String EVENT_NM_NPAL1500_OPENWIN_ACCT_ACRL = "NPAL1500Scrn00_OpenWin_AccountAcrl";
    /** EVENT_NM_NPAL1500_OPENWIN_ACCT_VAR */
    public static final String EVENT_NM_NPAL1500_OPENWIN_ACCT_VAR = "NPAL1500Scrn00_OpenWin_AccountVar";
    /** EVENT_NM_NPAL1500_OPENWIN_ACCT_VAR */
    public static final String EVENT_NM_NPAL1500_ONCHANGE_TRSMT_METH = "NPAL1500Scrn00_OnChange_TrsmtMethod";
    /** EVENT_NM_NPAL1500_GET_SHIP_TO_INFO */
    public static final String EVENT_NM_NPAL1500_GET_SHIP_TO_INFO = "NPAL1500Scrn00_Get_ShipToInfo";
    /** EVENT_NM_NPAL1500_OPENWIN_CONFIGID */
    public static final String EVENT_NM_NPAL1500_OPENWIN_CONFIGID = "NPAL1500Scrn00_OpenWin_ConfigID";
    /** EVENT_NM_NPAL1500_ADD_CONFIG */
    public static final String EVENT_NM_NPAL1500_ADD_CONFIG = "NPAL1500Scrn00_AddConfig";
    /** EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_WH */
    public static final String EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_WH = "NPAL1500Scrn00_OpenWin_LocationFromWH";
    /** EVENT_NM_NPAL1500_OPENWIN_LOC_TO_WH */
    public static final String EVENT_NM_NPAL1500_OPENWIN_LOC_TO_WH = "NPAL1500Scrn00_OpenWin_LocationToWH";
    /** EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_SWH */
    public static final String EVENT_NM_NPAL1500_OPENWIN_LOC_FROM_SWH = "NPAL1500Scrn00_OpenWin_LocationFromSWH";
    /** EVENT_NM_NPAL1500_OPENWIN_LOC_TO_SWH */
    public static final String EVENT_NM_NPAL1500_OPENWIN_LOC_TO_SWH = "NPAL1500Scrn00_OpenWin_LocationToSWH";
    /** EVENT_NM_NPAL1500_OPENWIN_SER_ENT */
    public static final String EVENT_NM_NPAL1500_OPENWIN_SER_ENT = "NPAL1500Scrn00_OpenWin_SerEnt";
    /** EVENT_NM_NPAL1500_COPY */
    public static final String EVENT_NM_NPAL1500_COPY = "NPAL1500Scrn00_Copy";
    /** EVENT_NM_NPAL1500_NPAL0170 */
    public static final String EVENT_NM_NPAL1500_NPAL0170 = "NPAL1500Scrn00_NPAL1500_NPAL0170";
    /** EVENT_NM_GET_SUPPLIER_NAME */
    public static final String EVENT_NM_GET_SUPPLIER_NAME = "NPAL1500Scrn00_Get_SupplierName";
    /** EVENT_NM_GET_SUPPLIER_SITE_NAME */
    public static final String EVENT_NM_GET_SUPPLIER_SITE_NAME = "NPAL1500Scrn00_Get_SupplierSiteName";
    /** EVENT_NM_GET_ADDRESS */
    public static final String EVENT_NM_GET_ADDRESS = "NPAL1500Scrn00_GetAddress";
    /** EVENT_NM_RETURN_INVTY_REQ_SCRN */
    public static final String EVENT_NM_RETURN_INVTY_REQ_SCRN = "NPAL1500_NPBL0020";
    //QC#28226 Add Start
    /** EVENT_NM_NPAL1500_OPENWIN_PO_TERM_ENT */
    public static final String EVENT_NM_NPAL1500_OPENWIN_PO_TERM_ENT = "NPAL1500Scrn00_OpenWin_PoTerm";
    //QC#28226 Add End

    /** for BL06(Update) */
    public static final String EVENT_NM_NPAL1500_CANCEL = "NPAL1500Scrn00_Cancel";
    /** EVENT_NM_NPAL1500_PO_CLOSE */
    public static final String EVENT_NM_NPAL1500_PO_CLOSE = "NPAL1500Scrn00_PoClose";
    /** EVENT_NM_NPAL1500_CMN_SAVE */
    public static final String EVENT_NM_NPAL1500_CMN_SAVE = "NPAL1500Scrn00_CMN_Save";
    /** EVENT_NM_NPAL1500_CMN_SUBMIT */
    public static final String EVENT_NM_NPAL1500_CMN_SUBMIT = "NPAL1500Scrn00_CMN_Submit";
    /** EVENT_NM_NPAL1500_MOVE_TO_COMPONET */
    public static final String EVENT_NM_NPAL1500_MOVE_TO_COMPONET = "NPAL1500Scrn00_MoveTo_Componet";
    /** EVENT_NM_NPAL1500_NSAL1240 */
    public static final String EVENT_NM_NPAL1500_NSAL1240 = "NPAL1500_NSAL1240";
    /** EVENT_NM_NPAL1500_NMAL2550 */
    public static final String EVENT_NM_NPAL1500_NMAL2550 = "NPAL1500_NMAL2550";

// START 2017/11/08 [QC#21849, ADD]
    /** EVENT_NM_NPAL1500_NMAL2550 */
    public static final String EVENT_NM_NPAL1500_NMAL6760 = "NPAL1500_NMAL6760";

    /** EVENT_NM_NPAL1500_NMAL2550 */
    public static final String EVENT_NM_NPAL1500_GET_SHIP_TO_INFO_CUSTOMER = "NPAL1500Scrn00_Get_ShipToInfoCustomer";
// END   2017/11/08 [QC#21849, ADD]
    
    /** for BL09(Print) */
    public static final String EVENT_NM_NPAL1500_PRINT = "NPAL1500Scrn00_Print";
    /** PO_LINE_STS_TXT_OPEN */
    public static final String PO_LINE_STS_TXT_OPEN = "Open";

    /** Button OpenWin_LocationFromWH Button Event Name */
    public static final String SCR_EVENT_NM_LOCATION_FROM_WH = "OpenWin_LocationFromWH";

    /** Button OpenWin_LocationToWH Button Event Name */
    public static final String SCR_EVENT_NM_LOCATION_TO_WH = "OpenWin_LocationToWH";

    /** Button OpenWin_LocationFromSWH Button Event Name */
    public static final String SCR_EVENT_NM_LOCATION_FROM_SWH = "OpenWin_LocationFromDetailSWH";

    /** Button OpenWin_LocationToSWH Button Event Name */
    public static final String SCR_EVENT_NM_LOCATION_TO_SWH = "OpenWin_LocationToDetailSWH";
    
    //QC#18420 Add Start 
    /** EVENT_NM_NPAL1500_GET_LINE_PRICE_INFO */
    public static final String EVENT_NM_NPAL1500_GET_LINE_PRICE_INFO = "NPAL1500Scrn00_Get_LinePriceInfo";

    /** EVENT_NM_NPAL1500_APPLY */
    public static final String EVENT_NM_NPAL1500_APPLY_DISC = "NPAL1500Scrn00_Apply_Disc";
    //QC#18420 Add End 

    // START 2021/04/21 J.Evangelista [QC#57102,ADD]
    /** EVENT_NM_NPAL1500_TBL_COLUMN_SORT */
    public static final String EVENT_NM_NPAL1500_TBL_COLUMN_SORT = "NPAL1500Scrn00_TBLColumnSort";
    //   END 2021/04/21 J.Evangelista [QC#57102,ADD]

    // START 2023/04/13 TZ.Win [QC#61299,ADD]
    /** EVENT_NM_NPAL1500_APPLY_ASL */
    public static final String EVENT_NM_NPAL1500_APPLY_ASL = "NPAL1500Scrn00_Apply_ASL";
    // END 2023/04/13 TZ.Win [QC#61299,ADD]

    // =================================================
    // Display Name
    // =================================================
    /** DISPLAY_PO_NUM */
    public static final String DISPLAY_PO_NUM = "PONumber";
    /** DISPLAY_DS_PO_TP_CD */
    public static final String DISPLAY_DS_PO_TP_CD = "POtype";
    /** DISPLAY_PO_HDR_STS_CD */
    public static final String DISPLAY_PO_HDR_STS_CD = "HeaderStatus";
    /** DISPLAY_PO_APVL_STS_CD */
    public static final String DISPLAY_PO_APVL_STS_CD = "ApprovalStatus";
    /** DISPLAY_PO_APVL_DT */
    public static final String DISPLAY_PO_APVL_DT = "InitialApprovalDate";
    /** DISPLAY_PO_SUBMIT_TS */
    public static final String DISPLAY_PO_SUBMIT_TS = "DateRequired";
    /** DISPLAY_RQST_RCV_DT */
    public static final String DISPLAY_RQST_RCV_DT = "Date&TimeNeeded(Date)";
    /** DISPLAY_RQST_RCV_DT */
    public static final String DISPLAY_RQST_RCV_TM = "Date&TimeNeeded(Time)";
    // START 2023/02/14 S.Dong [QC#60966, ADD]
    /** DISPLAY_RQST_SHIP_DT */
    public static final String DISPLAY_RQST_SHIP_DT = "VendorShipDate";
    // END 2023/02/14 S.Dong [QC#60966, ADD]
    /** DISPLAY_PO_ORD_SRC_CD */
    public static final String DISPLAY_PO_ORD_SRC_CD = "DocumentSourcetype";
    /** DISPLAY_TRX_REF_NUM */
    public static final String DISPLAY_TRX_REF_NUM = "SourceDoc#";
    /** DISPLAY_PO_QLFY_CD */
    public static final String DISPLAY_PO_QLFY_CD = "Qualifier";
    /** DISPLAY_PO_SUBMIT_PSN_CD */
    public static final String DISPLAY_PO_SUBMIT_PSN_CD = "Buyer";
    /** DISPLAY_PRCH_GRP_CD */
    public static final String DISPLAY_PRCH_GRP_CD = "BusinessUnit";
    /** DISPLAY_PO_ORD_CMNT_TXT */
    public static final String DISPLAY_PO_ORD_CMNT_TXT = "Description";
    /** DISPLAY_PRNT_VND_CD */
    public static final String DISPLAY_PRNT_VND_CD = "Supplier";
    /** DISPLAY_PRNT_VND_NM */
    public static final String DISPLAY_PRNT_VND_NM = "Supplier(Name)";
    /** DISPLAY_VND_CD */
    public static final String DISPLAY_VND_CD = "SupplierSite";
    /** DISPLAY_VND_NM */
    public static final String DISPLAY_VND_NM = "SupplierSite(Name)";
    /** DISPLAY_SRC_RTL_WH_CD */
    public static final String DISPLAY_SRC_RTL_WH_CD = "SourceWH";
    /** DISPLAY_SRC_RTL_WH_NM */
    public static final String DISPLAY_SRC_RTL_WH_NM = "SourceWH(Name)";
    /** DISPLAY_DEST_RTL_WH_CD */
    public static final String DISPLAY_DEST_RTL_WH_CD = "DestionationWH";
    /** DISPLAY_DEST_RTL_WH_NM */
    public static final String DISPLAY_DEST_RTL_WH_NM = "DestionationWH(Name)";
    /** DISPLAY_SHIP_TO_CUST_CD */
    public static final String DISPLAY_SHIP_TO_CUST_CD = "ShipToCustomer";
    /** DISPLAY_SHIP_TO_CUST_NM */
    public static final String DISPLAY_SHIP_TO_CUST_NM = "ShipToCustomer(Name)";
    /** DISPLAY_ADD_SHIP_TO_LOC_NM */
    public static final String DISPLAY_ADD_SHIP_TO_LOC_NM = "AdditionalName(ShipTo)";
    /** DISPLAY_ADD_SHIP_TO_ADDR_LINE */
    public static final String DISPLAY_ADD_SHIP_TO_ADDR_LINE = "AddressLine(ShipTo)";
    /** DISPLAY_SHIP_TO_POST_CD */
    public static final String DISPLAY_SHIP_TO_POST_CD = "PostCode(ShipTo)";
    /** DISPLAY_SHIP_TO_CTY_ADDR */
    public static final String DISPLAY_SHIP_TO_CTY_ADDR = "City(ShipTo)";
    /** DISPLAY_SHIP_TO_CNTY_NM */
    public static final String DISPLAY_SHIP_TO_CNTY_NM = "County(ShipTo)";
    /** DISPLAY_SHIP_TO_ST_CD */
    public static final String DISPLAY_SHIP_TO_ST_CD = "State(ShipTo)";
    /** DISPLAY_SHIP_TO_PROV_NM */
    public static final String DISPLAY_SHIP_TO_PROV_NM = "Province(ShipTo)";
    /** DISPLAY_SHIP_TO_CTRY_CD */
    public static final String DISPLAY_SHIP_TO_CTRY_CD = "Country(ShipTo)";
    /** DISPLAY_SHIP_TO_CTRY_DESC_TXT */
    public static final String DISPLAY_SHIP_TO_CTRY_DESC_TXT = "Country(name)(ShipTo)";
    /** DISPLAY_BILL_TO_CD */
    public static final String DISPLAY_BILL_TO_CD = "Code(BillTo)";
    /** DISPLAY_BILL_TO_NM */
    public static final String DISPLAY_BILL_TO_NM = "Name(BillTo)";
    /** DISPLAY_BILL_TO_LOC_NM */
    public static final String DISPLAY_BILL_TO_LOC_NM = "AdditionalName(BillTo)";
    /** DISPLAY_BILL_TO_ADDR_LINE */
    public static final String DISPLAY_BILL_TO_ADDR_LINE = "AddressLine(BillTo)";
    /** DISPLAY_BILL_TO_POST_CD */
    public static final String DISPLAY_BILL_TO_POST_CD = "PostCode(BillTo)";
    /** DISPLAY_BILL_TO_CTY_ADDR */
    public static final String DISPLAY_BILL_TO_CTY_ADDR = "City(BillTo)";
    /** DISPLAY_BILL_TO_CNTY_NM */
    public static final String DISPLAY_BILL_TO_CNTY_NM = "County(BillTo)";
    /** DISPLAY_BILL_TO_ST_CD */
    public static final String DISPLAY_BILL_TO_ST_CD = "State(BillTo)";
    /** DISPLAY_BILL_TO_PROV_NM */
    public static final String DISPLAY_BILL_TO_PROV_NM = "Province(BillTo)";
    /** DISPLAY_BILL_TO_CTRY_CD */
    public static final String DISPLAY_BILL_TO_CTRY_CD = "Country(BillTo)";
    /** DISPLAY_BILL_TO_CTRY_DESC_TXT */
    public static final String DISPLAY_BILL_TO_CTRY_DESC_TXT = "Country(name)(BillTo)";
    /** DISPLAY_CSA_RTN_CD */
    public static final String DISPLAY_CSA_RTN_CD = "Code(CSA)";
    /** DISPLAY_CSA_RTN_NM */
    public static final String DISPLAY_CSA_RTN_NM = "Name(CSA)";
    /** DISPLAY_CSA_RTN_LOC_NM */
    public static final String DISPLAY_CSA_RTN_LOC_NM = "AdditionalName(CSA)";
    /** DISPLAY_CSA_RTN_ADDR_LINE */
    public static final String DISPLAY_CSA_RTN_ADDR_LINE = "AddressLine(CSA)";
    /** DISPLAY_CSA_RTN_POST_CD */
    public static final String DISPLAY_CSA_RTN_POST_CD = "PostCode(CSA)";
    /** DISPLAY_CSA_RTN_CTY_ADDR */
    public static final String DISPLAY_CSA_RTN_CTY_ADDR = "City(CSA)";
    /** DISPLAY_CSA_RTN_CNTY_NM */
    public static final String DISPLAY_CSA_RTN_CNTY_NM = "County(CSA)";
    /** DISPLAY_CSA_RTN_ST_CD */
    public static final String DISPLAY_CSA_RTN_ST_CD = "State(CSA)";
    /** DISPLAY_CSA_RTN_PROV_NM */
    public static final String DISPLAY_CSA_RTN_PROV_NM = "Province(CSA)";
    /** DISPLAY_CSA_RTN_CTRY_CD */
    public static final String DISPLAY_CSA_RTN_CTRY_CD = "Country(CSA)";
    /** DISPLAY_CSA_RTN_CTRY_DESC_TXT */
    public static final String DISPLAY_CSA_RTN_CTRY_DESC_TXT = "Country(name)(CSA)";
    /** DISPLAY_FRT_COND_CD */
    public static final String DISPLAY_FRT_COND_CD = "FreightTerm";
    /** DISPLAY_SHPG_SVC_LVL_CD */
    public static final String DISPLAY_SHPG_SVC_LVL_CD = "ShipMethod";
    /** DISPLAY_CARR_CD */
    public static final String DISPLAY_CARR_CD = "Carrier";
    /** DISPLAY_CARR_NM */
    public static final String DISPLAY_CARR_NM = "Carrier(Name)";
    /** DISPLAY_CARR_ACCT_NUM */
    public static final String DISPLAY_CARR_ACCT_NUM = "CarrierAccount";
    /** DISPLAY_PO_TERM */
    public static final String DISPLAY_PO_TERM = "POTerm";
    /** DISPLAY_PO_MSG_SPECIAL_INSTRUCTIONS */
    public static final String DISPLAY_PO_MSG_SPECIAL_INSTRUCTIONS = "SpecialInstructions";
    /** DISPLAY_PO_MSG_RECEIVER_NOTE */
    public static final String DISPLAY_PO_MSG_RECEIVER_NOTE = "ReceiverNote";
    /** DISPLAY_PO_MSG_SHIPPER_NOTE */
    public static final String DISPLAY_PO_MSG_SHIPPER_NOTE = "ShipperNote";
    /** DISPLAY_TOTAL_AMOUNT */
    public static final String DISPLAY_TOTAL_AMOUNT = "TotalAmount";
    /** DISPLAY_CCY_CD */
    public static final String DISPLAY_CCY_CD = "Currency";
    /** DISPLAY_SVC_CONFIG_MSTR_PK */
    public static final String DISPLAY_SVC_CONFIG_MSTR_PK = "ConfigId";
    /** DISPLAY_PO_LINE_NUM */
    public static final String DISPLAY_PO_LINE_NUM = "Line#";
    /** DISPLAY_PO_LINE_TP */
    public static final String DISPLAY_PO_LINE_TP = "LineType";
    /** DISPLAY_MDSE_CD */
    public static final String DISPLAY_MDSE_CD = "Item#";
    /** DISPLAY_ASL_MDSE_CD */
    public static final String DISPLAY_ASL_MDSE_CD = "SupplierItem#";
    /** DISPLAY_MDSE_DESC_SHORT_TXT */
    public static final String DISPLAY_MDSE_DESC_SHORT_TXT = "ItemDescription";
    /** DISPLAY_LINE_PRICE */
    public static final String DISPLAY_LINE_PRICE = "LinePrice";
    /** DISPLAY_ORDER_QTY */
    public static final String DISPLAY_ORDER_QTY = "OrderQty";
    /** DISPLAY_UOM */
    public static final String DISPLAY_UOM = "UOM";
    /** DISPLAY_RQST_RCV_DT_DTL */
    public static final String DISPLAY_RQST_RCV_DT_DTL = "DateNeeded";
    // START 2023/02/14 S.Dong [QC#60966, ADD]
    /** DISPLAY_RQST_SHIP_DT_DTL */
    public static final String DISPLAY_RQST_SHIP_DT_DTL = "VendorShipDate";
    // END 2023/02/14 S.Dong [QC#60966, ADD]
    /** DISPLAY_DEST_SWH_CD */
    public static final String DISPLAY_DEST_SWH_CD = "DestSWH";
    /** DISPLAY_PO_MATCH_TP_CD */
    public static final String DISPLAY_PO_MATCH_TP_CD = "MatchOpt";
    /** DISPLAY_PO_LINE_STS_CD */
    public static final String DISPLAY_PO_LINE_STS_CD = "LineStatus";
    /** DISPLAY_EXT_TOTAL */
    public static final String DISPLAY_EXT_TOTAL = "Ext.Total";
    /** DISPLAY_PO_RCV_QTY */
    public static final String DISPLAY_PO_RCV_QTY = "ReceivedQty";
    /** DISPLAY_PO_CANCEL_QTY */
    public static final String DISPLAY_PO_CANCEL_QTY = "CancelledQty";
    /** DISPLAY_PO_INV_QTY */
    public static final String DISPLAY_PO_INV_QTY = "InvoicedQty";
    /** DISPLAY_VND_INVTY_LOC_CD */
    public static final String DISPLAY_VND_INVTY_LOC_CD = "VendorWH";
    /** DISPLAY_SRC_RTL_SWH_CD */
    public static final String DISPLAY_SRC_RTL_SWH_CD = "SourceSWH";
    /** DISPLAY_FROM_STK_STS_CD */
    public static final String DISPLAY_FROM_STK_STS_CD = "StockStatus";
    /** DISPLAY_SER_NUM */
    public static final String DISPLAY_SER_NUM = "Serial";
    /** DISPLAY_SVC_CONFIG_MSTR_PK_DTL */
    public static final String DISPLAY_SVC_CONFIG_MSTR_PK_DTL = "ConfigID";
    /** DISPLAY_CHARGE_ACCOUNT */
    public static final String DISPLAY_CHARGE_ACCOUNT = "ChargeACC";
    /** DISPLAY_ACCRUAL_ACCOUNT */
    public static final String DISPLAY_ACCRUAL_ACCOUNT = "AccrualACC";
    /** DISPLAY_VALIANCE_ACCOUNT */
    public static final String DISPLAY_VALIANCE_ACCOUNT = "VarianceACC";
    /** DISPLAY_PO_ORD_DTL_CMNT */
    public static final String DISPLAY_PO_ORD_DTL_CMNT = "Text";
    /** DISPLAY_PRCH_REQ_NUM */
    public static final String DISPLAY_PRCH_REQ_NUM = "POReq#";
    /** DISPLAY_PRCH_REQ_LINE_NUM */
    public static final String DISPLAY_PRCH_REQ_LINE_NUM = "POReqLine#";
    /** DISPLAY_TRSMT_METH_TP_CD */
    public static final String DISPLAY_TRSMT_METH_TP_CD = "TransmitMethodType";
    /** DISPLAY_SEND_PO_FAX_NUM */
    public static final String DISPLAY_SEND_PO_FAX_NUM = "TransmitMethodText(FAX)";
    /** DISPLAY_SEND_PO_EMAIL_NUM */
    public static final String DISPLAY_SEND_PO_EMAIL_NUM = "TransmitMethodText(E-Mail)";
    /** SQL_FROM_MIN_DT */
    public static final String SQL_FROM_MIN_DT = "10000101";
    /** SQL_TO_MAX_DT */
    public static final String SQL_TO_MAX_DT = "99991231";
    /** SQL_LIKE_PERCENT */
    public static final String SQL_LIKE_PERCENT = "%";
    /** MAXIMUM_AMT */
    public static final BigDecimal MAXIMUM_AMT = new BigDecimal("999999999999999.99");

    /**
     * REPORT_ID.
     */
    public static final String REPORT_ID = "NPAF0010";

    /**
     * GLBL_CMPY_CD.
     */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * USR_ID.
     */
    public static final String USR_ID = "USR_ID";

    /**
     * PO_ORD_NUM.
     */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /**
     * RCV_RPT_TS.
     */
    public static final String RCV_RPT_TS = "RCV_RPT_TS";

    /**
     * PO_RPT_PRINT_RQST_SQ.
     */
    public static final String PO_RPT_PRINT_RQST_SQ = "PO_RPT_PRINT_RQST_SQ";

    /**
     * INTL_LANG_VAL_COL_NM.
     */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** abnormal end message : failed get report. */
    public static final String ABEND_MSG_FAILED_GET_REPORT = "get report bytes failure";

    /** Message Id: [@] */
    public static final String ZZXM0001E = "ZZXM0001E";

    /** VarcharConst Key */
    public static final String NPAL1500_ADDR_CHK_FLG = "NPAL1500_ADDR_CHK_FLG";

    /** Message type is E */
    public static final String MESSAGE_KIND_E = "E";

    /** Message type is W */
    public static final String MESSAGE_KIND_W = "W";

    // QC#13985 2016/09/09 Add Start
    /** Screen Name for Account Type Charge */
    public static final String SCR_NM_ACCOUNT_TYPE_CH = "CH";

    /** Screen Name for Account Type Accrual */
    public static final String SCR_NM_ACCOUNT_TYPE_AC = "AC";

    /** Screen Name for Account Type Variance */
    public static final String SCR_NM_ACCOUNT_TYPE_VA = "VA";
    // QC#13985 2016/09/09 Add End


    /** delimiter string */
    public static final String VAR_CHAR_KEY_DELIMITER = "CONT_DELIMITER";
    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;

    /** Var char key :DROP_SHIP_RTL_WH_CD string */
    public static final String VAR_CHAR_KEY_DROP_SHIP_RTL_WH_CD = "DROP_SHIP_RTL_WH_CD";

// START 2017/11/07 [QC#21849, ADD]
    /** Var char key : MANUAL_DROPSHIP_WAREHOUSE_CD string */
    public static final String VAR_CHAR_KEY_MANUAL_DROPSHIP_WAREHOUSE_CD = "MANUAL_DROPSHIP_WAREHOUSE_CD";
// END   2017/11/07 [QC#21849, ADD]

    // =================================================
    // 9seg
    // =================================================
    /** message parameter : Segment */
    public static final String MSG_PARAM_SEGMENT = "Segment";

    /** message parameter : Company */
    public static final String MSG_PARAM_CMPY = "Company";

    /** message parameter : Extension */
    public static final String MSG_PARAM_EXTN = "Extension";

    /** message parameter : Cost Center */
    public static final String MSG_PARAM_CC = "Cost Center";

    /** message parameter : Account */
    public static final String MSG_PARAM_ACCT = "Account";

    /** message parameter : Company */
    public static final String MSG_PARAM_PROJ = "Project";

    /** message parameter : Product */
    public static final String MSG_PARAM_PROD = "Product";

    /** message parameter : Affiliate */
    public static final String MSG_PARAM_AFFL = "Affiliate";

    /** message parameter : Channel */
    public static final String MSG_PARAM_CH = "Channel";

    /** message parameter : Branch */
    public static final String MSG_PARAM_BR = "Branch";

    /** segment token list index : COA_CMPY_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD = 0;

    /** segment token list index : COA_BR_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD = 1;

    /** segment token list index : COA_CC_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD = 2;

    /** segment token list index : COA_ACCT_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD = 3;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD = 4;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD = 5;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD = 6;

    /** segment token list index : COA_PROJ_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD = 7;

    /** segment token list index : COA_EXTN_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD = 8;

    /** segment element length : COA_CMPY_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CMPY = 3;

    /** segment element length : COA_EXTN_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_EXTN = 3;

    /** segment element length : COA_CC_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CC = 6;

    /** segment element length : COA_ACCT_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_ACCT = 8;

    /** segment element length : COA_PROJ_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROJ = 4;

    /** segment element length : COA_PROD_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROD = 8;

    /** segment element length : COA_AFFL_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_AFFL = 3;

    /** segment element length : COA_CH_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CH = 3;

    /** segment element length : COA_BR_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_BR = 3;

    /** NPZC610001 Mode code */
    public static final String PROCESS_DEFAULT_BILL_SHIP = "04";

    /** EVENT_NM_NPAL1500_ON_CHANGE_LINE_TYPE */
    public static final String EVENT_NM_NPAL1500_ON_CHANGE_LINE_TYPE = "NPAL1500Scrn00_OnChange_LineType";

    /** EVENT_NM_NPAL1500_APPLY Add QC#21169 */
    public static final String EVENT_NM_NPAL1500_APPLY = "NPAL1500Scrn00_Apply";
    // START 2023/02/14 S.Dong [QC#60966, ADD]
    /**
     * EVENT_NM_NPAL1500_RQSTSHIPDT_APPLY
     */
    public static final String EVENT_NM_NPAL1500_RQSTSHIPDT_APPLY = "NPAL1500Scrn00_RqstShipDt_Apply";
    // END 2023/02/14 S.Dong [QC#60966, ADD]

    /** Please fill out/select the required field. Add QC#21169 */
    public static final String NPAM0009E = "NPAM0009E";
    
    /** Default thru date */
    public static final String DEF_THRU_DT = "99991231";

    /**
     * Please enter the details of the customized parts. QC#24918
     */
    public static final String NPAM1579E = "NPAM1579E";

    /**
     * VAR_CONST: CREATE_MATERIAL_PARTS. QC#24918
     */
    public static final String VAR_CONST_CREATE_MATERIAL_PARTS = "CREATE_MATERIAL_PARTS";

    //QC#29155 Add.
    /**
     * MSG_ERR_CD : 0(normal)
     */
    public static final int MSG_ERR_CD_NORMAL = 0;

    //QC#29155 Add.
    /**
     * NPAM1636W : The relationship between 'Carrier' and 'Shipping Service Level Code' is incorrect.
     */
    public static final String NPAM1636W = "NPAM1636W";

    /** Add QC#32087
     * The Destination WH is GMD WH. then Supplier site must be "non EDI vendor site" 
     * */
    public static final String NPAM1643E = "NPAM1643E";

    // add start 2022/05/16 QC#57934
    /** DR_CR_TP_CREDIT */
    public static final String DR_CR_TP_CREDIT = "C";

    /** DR_CR_TP_DEBIT */
    public static final String DR_CR_TP_DEBIT = "D";

    /** VAR_CHAR_CONST: NFBL1130_COA_ACCT_CD */
    public static final String VAR_CHAR_NFBL1130_COA_ACCT_CD = "NFBL1130_COA_ACCT_CD";

    /** Default COA_ACCT_CD value */
    public static final String DEFAULT_COA_ACCT_CD = "21431001";
   // add end 2022/05/16 QC#57934
    /** Column _CMPY_CD */
    public static final String CMPY_CD = "_CMPY_CD";

    /** Column _BR_CD */
    public static final String BR_CD = "_BR_CD";

    /** Column _CC_CD */
    public static final String CC_CD = "_CC_CD";

    /** Column _ACCT_CD */
    public static final String ACCT_CD = "_ACCT_CD";

    /** Column _PROD_CD */
    public static final String PROD_CD = "_PROD_CD";

    /** Column _CH_CD */
    public static final String CH_CD = "_CH_CD";

    /** Column _AFFL_CD */
    public static final String AFFL_CD = "_AFFL_CD";

    /** Column PROJ_CD */
    public static final String PROJ_CD = "_PROJ_CD";

    /** Column _EXTN_CD */
    public static final String EXTN_CD = "_EXTN_CD";
    
    /** Comma */
    public static final String STR_COMMA = ".";
    
    /** DEF_ACRL_WRT_OFF_COA */
    public static final String DEF_ACRL_WRT_OFF_COA = "DEF_ACRL_WRT_OFF_COA";
    
    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
    // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
    public static final String DOT = ".";
    
    public static final String MDSE_TP_SET = "2";
    // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
    //QC#62443 2024/3/1 Add Start
    /** VAR_CHAR_EXP_SPLY_COA_CMPY_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_CMPY_CD = "EXP_SPLY_COA_CMPY_CD";

    /** VAR_CHAR_EXP_SPLY_COA_BR_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_BR_CD = "EXP_SPLY_COA_BR_CD";

    /** VAR_CHAR_EXP_SPLY_COA_ACCT_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_ACCT_CD = "EXP_SPLY_COA_ACCT_CD";

    /** VAR_CHAR_EXP_SPLY_COA_CH_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_CH_CD = "EXP_SPLY_COA_CH_CD";

    /** VAR_CHAR_EXP_SPLY_COA_AFFL_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_AFFL_CD = "EXP_SPLY_COA_AFFL_CD";

    /** VAR_CHAR_EXP_SPLY_COA_PROJ_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_PROJ_CD = "EXP_SPLY_COA_PROJ_CD";

    /** VAR_CHAR_EXP_SPLY_COA_EXTN_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_EXTN_CD = "EXP_SPLY_COA_EXTN_CD";
    //QC#62443 2024/3/1 Add End
}
