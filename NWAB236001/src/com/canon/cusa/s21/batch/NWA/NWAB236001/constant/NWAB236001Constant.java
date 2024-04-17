/**
 *  <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB236001.constant;

/**
 * <pre>
 * NWAB236001:Install Status Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2013/08/23   Fujitsu         A.Shinohara     Create          R-OM057
 * 2013/09/11   Fujitsu         A.Shinohara     Update          Defect#2226,2242
 * 2017/03/22   Fujitsu         H.Nagashima     Update          QC#16639
 * 2019/11/11   Fujitsu         S.Kosaka        Update          QC#54200
 *</pre>
 */
public class NWAB236001Constant {
    /* Internal Constants */
    /** Business ID */
    public static final String BIZ_ID = "NWAB236001";
    /** Collection Size : 100 */
    public static final int SIZE_100 = 100;
    /** Collection Size : 50 */
    public static final int SIZE_50 = 50;
    /** Collection Size : 10 */
    public static final int SIZE_10 = 10;
    /** Collection Size (MAX Shipping Number List Count) : 1000  */
    public static final int MAX_1000 = 1000;
    /** Shipping Plan Update Mode : Installed */
    public static final String MODE_INSTALLED = "installed";
    /** Shipping Plan Update Mode : Removed */
    public static final String MODE_REMOVED = "removed";
    /** Shipping Plan Update Mode : No Update */
    public static final String MODE_NONE = "none";
    /** Order Line Sub Number of Set Parent */
    public static final String SET_LINE_SUB_NUM = "000";

    /* Messages */
    /** "Global Company Code" is not set. */
    public static final String NWZM0473E = "NWZM0473E";
    /** Sales date cannot be obtained. */
    public static final String NWAM0446E = "NWAM0446E";
    /** No search results found. (Target table:[@],  Search key:[@]) */
    public static final String NWAM0373E = "NWAM0373E";
    /** It failed to update the [@]. PK [@] */
    public static final String NWZM1024E = "NWZM1024E";
    /** Due to an error, Order process is terminated. (Order# : [@]) */
    public static final String NWAM0635E = "NWAM0635E";
    /** Machine Master exist for item that is not for creating Machine Master. (Shipping Plan# : [@]) */
    public static final String NWAM0650E = "NWAM0650E";
    /** E-mail address <Type: [@], Group: [@], Key: [@]>  cannot be obtained. */
    public static final String NWAM0637E = "NWAM0637E";
    /** The mail template cannot be acquired.  <Template ID: [@]> */
    public static final String NWAM0319E = "NWAM0319E";

    /* BusinessProcessLog */
    /** SubSysId : NWA */
    public static final String SUB_SYS_ID = "NWA";
    /** ProcId : OM */
    public static final String PROCESS_ID = "OM";
    /** DocTpCd : OM */
    public static final String DOCUMENT_TYPE = "OM";
    /** EventId : Install */
    public static final String EVENT_ID_INSL = "Install";
    /** EventId : Invoice */
    public static final String EVENT_ID_INV = "Invoiced";
    /** EventId : Cancel */
    public static final String EVENT_ID_CANC = "Cancel";
    /** EventId : Close */
    public static final String EVENT_ID_CLO = "Close";

    /* SQL Name */
    /** getShpgPlnInfo */
    public static final String SQL_GET_SHPG_PLN_INFO = "getShpgPlnInfo";
    /** getMainUnitInfo */
    public static final String SQL_GET_MAIN_UNIT_INFO = "getMainUnitInfo";
    /** getFnshShpgPlnInfo */
    public static final String SQL_GET_FNSH_SHPG_PLN_INFO = "getFnshShpgPlnInfo";
    /** getCpoDtlSts */
    public static final String SQL_GET_CPO_DTL_STS = "getCpoDtlSts";
    /** getPrntShpgPln */
    public static final String SQL_GET_PRNT_SHPG_PLN = "getPrntShpgPln";
    /** getMachMstr */
    public static final String SQL_GET_MACH_MSTR = "getMachMstr";
    // 2019/11/11 QC#54200 Add Start
    /** searchHoldReleaseTarget */
    public static final String SQL_SRCH_HOLDS = "searchHoldReleaseTarget";
    /** getShippingStatusInConfig */
    public static final String SQL_GET_SHPG_STS_IN_CONFIG = "getShippingStatusInConfig";
    // 2019/11/11 QC#54200 Add End

    /* SQL Parameters : Key */
    /** glblCmpyCd */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** trxSrcTpCd */
    public static final String PARAM_TRX_SRC_TP_CD = "trxSrcTpCd";
    /** revRecogFlg */
    public static final String PARAM_REV_RECOG_FLG = "revRecogFlg";
    /** trxHdrNum */
    public static final String PARAM_TRX_HDR_NUM = "trxHdrNum";
    /** trxLineNum */
    public static final String PARAM_TRX_LINE_NUM = "trxLineNum";
    /** trxLineSubNum */
    public static final String PARAM_TRX_LINE_SUB_NUM = "trxLineSubNum";
    /** dsOrdCatgCd */
    public static final String PARAM_DS_ORD_CATG_CD = "dsOrdCatgCd";
    // 2013/09/11 Defect#2242 Add Start
    /** machMstrCratFlg */
    public static final String PARAM_MACH_MSTR_CRAT_FLG = "machMstrCratFlg";
    // 2013/09/11 Defect#2242 Add End
    // 2013/09/11 Defect#2226 Add Start
    /** mdseTpCd */
    public static final String PARAM_MDSE_TP_CD = "mdseTpCd";
    // 2013/09/11 Defect#2226 Add End
    /** soNum */
    public static final String PARAM_SO_NUM = "soNum";
    /** dsOrdPosnNum */
    public static final String PARAM_DS_ORD_POSN_NUM = "dsOrdPosnNum";
    /** baseCmptFlg */
    public static final String PARAM_BASE_CMPT_FLG = "baseCmptFlg";
    /** shpgStsInvoiced */
    public static final String PARAM_SHPG_STS_INVOICED = "shpgStsInvoiced";
    /** shpgStsCancelled */
    public static final String PARAM_SHPG_STS_CANCELLED = "shpgStsCancelled";
    /** shpgStsShipped */
    public static final String PARAM_SHPG_STS_SHIPPED = "shpgStsShipped";
    /** shpgStsArrived */
    public static final String PARAM_SHPG_STS_ARRIVED = "shpgStsArrived";
    /** shpgStsInstalled */
    public static final String PARAM_SHPG_STS_INSTALLED = "shpgStsInstalled";
    /** shpgPlnNumListNotTarget */
    public static final String PARAM_SHPG_PLN_NUM_LIST_NOT_TARGET = "shpgPlnNumListNotTarget";
    /** shpgPlnNumListTarget */
    public static final String PARAM_SHPG_PLN_NUM_LIST_TARGET = "shpgPlnNumListTarget";
    /** cpoOrdNum */
    public static final String PARAM_CPO_ORD_NUM = "cpoOrdNum";
    /** shpgPlnNum */
    public static final String PARAM_SHPG_PLN_NUM = "shpgPlnNum";
    /** rowNumFlg */
    public static final String PARAM_ROW_NUM_FLG = "rowNumFlg";
    /** rowNum */
    public static final String PARAM_ROW_NUM = "rowNum";
    // 2015/09/17 CSA Add Start
    /** rowNum */
    public static final String PARAM_REV_RECOG_METH_CD = "revRecogMethCd";
    // 2015/09/17 CSA Add End
    // 2019/11/11 QC#54200 Add Start
    /** stsIntaled */
    public static final String PARAM_STS_INSTL = "stsIntaled";
    /** dsCpoConfigPk */
    public static final String PARAM_DS_CPO_CONFIG_PK = "dsCpoConfigPk";
    /** svcConfigMstrPk */
    public static final String PARAM_SVC_CONFIG_MSTR_PK = "svcConfigMstrPk";
    /** hldRsnS03 */
    public static final String PARAM_HLD_RSN_S03 = "hldRsnS03";
    /** relflgN */
    public static final String PARAM_REL_FLG_N = "relflgN";
    // 2019/11/11 QC#54200 Add End

    /* DB Table Name */
    /** SHPG_PLN */
    public static final String TBL_SHPG_PLN = "SHPG_PLN";
    /** CPO_DTL */
    public static final String TBL_CPO_DTL = "CPO_DTL";
    /** CPO */
    public static final String TBL_CPO = "CPO";

    /* DB Columns */
    /** SHPG_PLN_NUM */
    public static final String DB_SHPG_PLN_NUM = "SHPG_PLN_NUM";
    /** TRX_HDR_NUM */
    public static final String DB_TRX_HDR_NUM = "TRX_HDR_NUM";
    /** TRX_LINE_NUM */
    public static final String DB_TRX_LINE_NUM = "TRX_LINE_NUM";
    /** TRX_LINE_SUB_NUM */
    public static final String DB_TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";
    /** SO_NUM */
    public static final String DB_SO_NUM = "SO_NUM";
    /** SHPG_STS_CD */
    public static final String DB_SHPG_STS_CD = "SHPG_STS_CD";
    /** ORD_QTY */
    public static final String DB_ORD_QTY = "ORD_QTY";
    /** ORD_QTY_SP */
    public static final String DB_ORD_QTY_SP = "ORD_QTY_SP";
    /** ORD_QTY_CD */
    public static final String DB_ORD_QTY_CD = "ORD_QTY_CD";
    /** DS_ORD_POSN_NUM */
    public static final String DB_DS_ORD_POSN_NUM = "DS_ORD_POSN_NUM";
    /** SVC_CONFIG_MSTR_PK */
    public static final String DB_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    // 2013/09/11 Defect#2226 Add Start
    /** MDSE_TP_CD */
    public static final String DB_MDSE_TP_CD = "MDSE_TP_CD";
    // 2013/09/11 Defect#2226 Add End
    // 2013/09/11 Defect#2242 Mod Start
//    /** MACH_MSTR_CRAT_FLG_DOT */
//    public static final String DB_MACH_MSTR_CRAT_FLG_DOT = "MACH_MSTR_CRAT_FLG_DOT";
//    /** MACH_MSTR_CRAT_FLG_MC */
//    public static final String DB_MACH_MSTR_CRAT_FLG_MC = "MACH_MSTR_CRAT_FLG_MC";
    /** MACH_MSTR_CRAT_FLG */
    public static final String DB_MACH_MSTR_CRAT_FLG = "MACH_MSTR_CRAT_FLG";
    // 2013/09/11 Defect#2242 Mod End
    /** TRX_SRC_TP_CD */
    public static final String DB_TRX_SRC_TP_CD = "TRX_SRC_TP_CD";
    /** SVC_MACH_MSTR_PK */
    public static final String DB_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** SVC_MACH_MSTR_STS_CD */
    public static final String DB_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";
    /** ISTL_DT */
    public static final String DB_ISTL_DT = "ISTL_DT";
    /** SET_SHPG_PLN_NUM */
    public static final String DB_SET_SHPG_PLN_NUM = "SET_SHPG_PLN_NUM";
    /** REV_RECOG_FLG */
    public static final String DB_REV_RECOG_FLG = "REV_RECOG_FLG";
    /** CPO_ORD_NUM */
    public static final String DB_CPO_ORD_NUM = "CPO_ORD_NUM";
    /** CPO_DTL_LINE_NUM */
    public static final String DB_CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    /** ORD_LINE_STS_CD */
    public static final String DB_ORD_LINE_STS_CD = "ORD_LINE_STS_CD";
    /** INSTL_BASE_CTRL_FLG */
    public static final String DB_INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";
    /** DS_CPO_CONFIG_PK */
    public static final String DB_DS_CPO_CONFIG_PK = "DS_CPO_CONFIG_PK";

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
    public static final String ML_GRP_ID_TO = "NWAB2360";
    /** Mail group id for From */
    public static final String ML_GRP_ID_FROM = "FROM0005";
    /** Mail Template ID */
    public static final String ML_TMPL_ID = "NWAB2360M001";
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
}
