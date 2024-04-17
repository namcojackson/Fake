/**
 *  <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB234001.constant;

/**
 * <pre>
 * NWAB234001:Conversion Order Completion Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2013/08/08   Fujitsu         A.Shinohara     Create          R-OM050
 * 2016/04/27   Fujitsu         H.Nagashima     Update          QC#7606
 * 2017/10/24   Fujitsu         H.Ikeda         Update          QC#21550
 * 2019/07/01   Fujitsu         Hd.Sugawara     Update          QC#50984
 *</pre>
 */
public class NWAB234001Constant {
    /* Internal Constants */
    /** Business ID */
    public static final String BIZ_ID = "NWAB234001";
    /** Message Kind Error */
    public static final String MESSAGE_KIND_ERROR = "E";
    /** Collection Size : 100 */
    public static final int SIZE_100 = 100;
    /** Collection Size : 50 */
    public static final int SIZE_50 = 50;
    /** Collection Size : 10 */
    public static final int SIZE_10 = 10;
    /** Collection Size : 100 */
    public static final int MAX_LIST_SIZE_1000 = 1000;

    /* Messages */
    /** "Global Company Code" is not set. */
    public static final String NWZM0473E = "NWZM0473E";
    /** Sales date cannot be obtained. */
    public static final String NWAM0446E = "NWAM0446E";
    /** No search results found. (Target table:[@],  Search key:[@]) */
    public static final String NWAM0373E = "NWAM0373E";
    /** It failed to update the [@]. PK [@] */
    public static final String NWZM1024E = "NWZM1024E";
    /** An error has occurred in the called API [@]. */
    public static final String NWAM0189E = "NWAM0189E";
    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";
    /** The mail template cannot be acquired.  <Template ID: [@]> */
    public static final String NWAM0319E = "NWAM0319E";
    /** Due to an error, Order process is terminated.(Order# : [@]) */
    public static final String NWAM0635E = "NWAM0635E";
    /** Due to Invoice Up To Date is later (future date) than Conversion Date, Contract Close Date not be updated. (Contract# : [@], Contract SQ# : [@]) */
    public static final String NWAM0636E = "NWAM0636E";
    /** E-mail address <Type: [@], Group: [@], Key: [@]>  cannot be obtained. */
    public static final String NWAM0637E = "NWAM0637E";
    /** Order Detail without Machine Master is found.(Order# : [@]) */
    public static final String NWAM0638E = "NWAM0638E";

    /* API Name */
    /** Machine Master Update API */
    public static final String API_MACH_MSTR_UPD = "Machine Master Update API";
    /** Asset Update API */
    public static final String API_ASSET_UPD = "Asset Update API";
//CSA Add Start
    /** Asset Staging Entry API */
    public static final String API_ASSET_STG = "Asset Staging Entry API";
//CSA Add End

//CSA Add Start
    /** getConvOrdInfoForSales */
    public static final String SQL_GET_SLS_AND_RTRN_CONV_ORD_INFO = "getSalesAndReturnConvOrdInfo";
//CSA Add End
    /** SQL ID : 001 */
    public static final String SQL_001 = "001";

    /* SQL Parameters : Key */
    /** glblCmpyCd */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** ordHdrStsCd */
    public static final String PARAM_ORD_HDR_STS_CD = "ordHdrStsCd";
    /** convCompFlg */
    public static final String PARAM_CONV_COMP_FLG = "convCompFlg";
    /** bizAppId */
    public static final String PARAM_BIZ_APP_ID = "bizAppId";
    /** glblCmpyCd01 */
    public static final String PARAM_GLBL_CMPY_CD_01 = "glblCmpyCd01";
    /** cpoOrdNum01 */
    public static final String PARAM_CPO_ORD_NUM_01 = "cpoOrdNum01";
//CSA Add Start
    /** ordLineStsCd */
    public static final String PARAM_ORD_LINE_STS_CD = "ordLineStsCd";
    /** dsOrdLineCatgCdListSls */
    public static final String PARAM_ORD_LINE_CATG_CD_SLS = "dsOrdLineCatgCdSls";
    /** dsOrdLineCatgCdRtrn */
    public static final String PARAM_ORD_LINE_CATG_CD_RTRN = "dsOrdLineCatgCdRtrn";
    /** svMachMaintAvalFlg */
    public static final String PARAM_SVC_MACH_MAINT_AVAL_FLG = "svMachMaintAvalFlg";
//CSA Add End

    /* SQL Parameters : Value */
    /** Online Business ID */
    public static final String ONLINE_BIZ_ID = "NWAL1170";

    /* DB Table Name */
    /** DS_CONTR_DTL */
    public static final String TBL_DS_CONTR_DTL = "DS_CONTR_DTL";
    /** DS_ASSE_MSTR */
    public static final String TBL_DS_ASSE_MSTR = "DS_ASSE_MSTR";
    /** CPO */
    public static final String TBL_CPO = "CPO";
    /** CPO_DTL */
    public static final String TBL_CPO_DTL = "CPO_DTL";
//CSA Add Start
    /** DS_CPO_RTRN_DTL */
    public static final String TBL_DS_CPO_RTRN_DTL = "DS_CPO_RTRN_DTL";
//CSA Add End

    /* DB Columns */
    /** INV_NUM */
    public static final String DB_INV_NUM = "INV_NUM";
    /** CPO_ORD_NUM */
    public static final String DB_CPO_ORD_NUM = "CPO_ORD_NUM";
    /** BILL_TO_CUST_CD */
    public static final String DB_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    /** CUST_ISS_PO_NUM */
    public static final String DB_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    /** CPO_ORD_TP_CD */
    public static final String DB_CPO_ORD_TP_CD = "CPO_ORD_TP_CD";
    /** DS_ORD_TP_CD */
    public static final String DB_DS_ORD_TP_CD = "DS_ORD_TP_CD";
    /** DS_ORD_RSN_CD */
    public static final String DB_DS_ORD_RSN_CD = "DS_ORD_RSN_CD";
// QC#21550 add Start
    /** DS_ORD_CATG_CD */
    public static final String DB_DS_ORD_CATG_CD = "DS_ORD_CATG_CD";
// QC#21550 add End
    /** CPO_DTL_LINE_NUM */
    public static final String DB_CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    /** CPO_DTL_LINE_SUB_NUM */
    public static final String DB_CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";
    /** SHIP_TO_CUST_CD */
    public static final String DB_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    /** SHIP_TO_LOC_NM */
    public static final String DB_SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";
    /** SHIP_TO_ADDL_LOC_NM */
    public static final String DB_SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";
    /** SHIP_TO_FIRST_LINE_ADDR */
    public static final String DB_SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";
    /** SHIP_TO_SCD_LINE_ADDR */
    public static final String DB_SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";
    /** SHIP_TO_THIRD_LINE_ADDR */
    public static final String DB_SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";
    /** SHIP_TO_FRTH_LINE_ADDR */
    public static final String DB_SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";
    /** SHIP_TO_CTY_ADDR */
    public static final String DB_SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";
    /** SHIP_TO_ST_CD */
    public static final String DB_SHIP_TO_ST_CD = "SHIP_TO_ST_CD";
    /** SHIP_TO_PROV_NM */
    public static final String DB_SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";
    /** SHIP_TO_CNTY_NM */
    public static final String DB_SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";
    /** SHIP_TO_POST_CD */
    public static final String DB_SHIP_TO_POST_CD = "SHIP_TO_POST_CD";
    /** SHIP_TO_CTRY_CD */
    public static final String DB_SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";
    /** SLS_REP_OR_SLS_TEAM_TOC_CD */
    public static final String DB_SLS_REP_OR_SLS_TEAM_TOC_CD = "SLS_REP_OR_SLS_TEAM_TOC_CD";
    /** SVC_CONFIG_MSTR_PK */
    public static final String DB_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** SVC_MACH_MSTR_PK */
    public static final String DB_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** DS_ASSET_MSTR_PK */
    public static final String DB_DS_ASSET_MSTR_PK = "DS_ASSET_MSTR_PK";
//CSA Add Start
    /** DS_ORD_POSN_NUM */
    public static final String DB_DS_ORD_POSN_NUM = "DS_ORD_POSN_NUM";
    /** BASE_CMPT_FLG */
    public static final String DB_BASE_CMPT_FLG = "BASE_CMPT_FLG";
//QC#7606 add Start
    /** ORIG_CPO_ORD_NUM */
    public static final String DB_ORIG_CPO_ORD_NUM = "ORIG_CPO_ORD_NUM";
    /** ORIG_CPO_DTL_LINE_NUM */
    public static final String DB_ORIG_CPO_DTL_LINE_NUM = "ORIG_CPO_DTL_LINE_NUM";
    /** ORIG_CPO_DTL_LINE_SUB_NUM */
    public static final String DB_ORIG_CPO_DTL_LINE_SUB_NUM = "ORIG_CPO_DTL_LINE_SUB_NUM";
//QC#7606 add End
    /** ORD_SRC_REF_NUM */
    public static final String DB_ORD_SRC_REF_NUM = "ORD_SRC_REF_NUM";
    /** ORD_SRC_REF_LINE_NUM */
    public static final String DB_ORD_SRC_REF_LINE_NUM = "ORD_SRC_REF_LINE_NUM";
    /** ORD_SRC_REF_LINE_SUB_NUM */
    public static final String DB_ORD_SRC_REF_LINE_SUB_NUM = "ORD_SRC_REF_LINE_SUB_NUM";
    /** SELL_TO_CUST_CD */
    public static final String DB_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";
    /** SOLD_TO_CUST_LOC_CD */
    public static final String DB_SOLD_TO_CUST_LOC_CD = "SOLD_TO_CUST_LOC_CD";
    /** BILL_TO_CUST_ACCT_CD */
    public static final String DB_BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";
    /** BILL_TO_CUST_LOC_CD */
    public static final String DB_BILL_TO_CUST_LOC_CD = "BILL_TO_CUST_LOC_CD";
    /** SHIP_TO_CUST_ACCT_CD */
    public static final String DB_SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";
    /** SHIP_TO_CUST_LOC_CD */
    public static final String DB_SHIP_TO_CUST_LOC_CD = "SHIP_TO_CUST_LOC_CD";
    /** DS_ORD_LINE_CATG_CD */
    public static final String DB_DS_ORD_LINE_CATG_CD = "DS_ORD_LINE_CATG_CD";
    /** MDSE_CD */
    public static final String DB_MDSE_CD = "MDSE_CD";
    /** ORD_QTY */
    public static final String DB_ORD_QTY = "ORD_QTY";
    /** INV_DT */
    public static final String DB_INV_DT = "INV_DT";
    /** DS_CPO_RTRN_LINE_NUM */
    public static final String DB_DS_CPO_RTRN_LINE_NUM = "DS_CPO_RTRN_LINE_NUM";
    /** DS_CPO_RTRN_LINE_SUB_NUM */
    public static final String DB_DS_CPO_RTRN_LINE_SUB_NUM = "DS_CPO_RTRN_LINE_SUB_NUM";

//QC#8630 add Start
    /** BILL_TO_LOC_NUM */
    public static final String DB_BILL_TO_LOC_NUM = "BILL_TO_LOC_NUM";
    /** SHIP_TO_LOC_NUM */
    public static final String DB_SHIP_TO_LOC_NUM = "SHIP_TO_LOC_NUM";
//QC#8630 add End
//QC#9594 add Start
    /** SET_MDSE_CD */
    public static final String DB_SET_MDSE_CD = "SET_MDSE_CD";
//QC#9594 add End
//QC#16425 add Start
    public static final String CONV_PROC_STS_CD_PROC_TRGT = "1";
//QC#16425 add End
//CSA Add End

    /* Mail Constants */
    /** comma */
    public static final String COMMA = ",";
    /** hyphen */
    public static final String HYPHEN = "-";
    /** Line Feed Code */
    public static final String LINE_FEED_CODE = "\r\n";
    /** Tab */
    public static final String TAB = "    ";
    /** Type : From */
    public static final String FROM = "From";
    /** Type : To */
    public static final String TO = "To";
    /** Date Time Format */
    public static final String ML_DT_TM_FMT = "MM/dd/yyyy HH:mm:ss";
    /** Mail Group ID */
    public static final String ML_GRP_ID_TO = "NWAB2340";
    /** Mail group id for From */
    public static final String ML_GRP_ID_FROM = "FROM0005";
    /** Mail Template ID */
    public static final String ML_TMPL_ID = "NWAB2340M001";
    /** Mail Template Key: Batch ID */
    public static final String ML_TMPL_KEY_ID = "batchId";
    /** Mail Template Key: errDate */
    public static final String ML_TMPL_KEY_DT = "errDate";
    /** Mail Template Key: Error Data */
    public static final String ML_TMPL_KEY_MSG = "message";
    /** Mail Language */
    public static final String ML_LANG = "en";
    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    // Add Start 2019/07/01 QC#50984
    /* BusinessProcessLog */
    /** SubSysId : NWZ */
    public static final String SUB_SYS_ID = "NWZ";
    /** ProcId : OM */
    public static final String PROCESS_ID = "OM";
    /** DocTpCd : OM */
    public static final String DOCUMENT_TYPE = "OM";
    /** EventId : Close */
    public static final String EVENT_ID_CLOSE = "Close";
    // Add End 2019/07/01 QC#50984
}
