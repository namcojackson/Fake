/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NPZ.NPZC133001.constant;

/**
 * <pre>
 * constant Send PO API for Interface for AZERTY
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2015   CITS            T.Hakodate      Create          N/A
 * 07/26/2016   CITS            N.Akaishi       Update          N/A
 *</pre>
 */

public class NPZC133001Constant {

    /** message id */
    /** [@] is mandatory. */
    public static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** No PO Ord Dtl data found. [@] */
    public static final String MSG_ID_NPAM1236E = "NPAM1236E";

    /** "Global Company Code" is mandatory. */
    public static final String MSG_ID_NDAM0013E = "NDAM0013E";

    /** Interface ID is mandatory. */
    public static final String MSG_ID_NPAM1392E = "NPAM1392E";

    /** Interface Transaction ID is mandatory. */
    public static final String MSG_ID_NPAM1393E = "NPAM1393E";

    /** Interface Segment ID is mandatory. */
    public static final String MSG_ID_NPAM1394E = "NPAM1394E";

    /** Interface Unit ID is mandatory. */
    public static final String MSG_ID_NPAM1395E = "NPAM1395E";

    /** Interface Sequence Number ID is mandatory. */
    public static final String MSG_ID_NPAM1396E = "NPAM1396E";

    /** "PO Order Number" is mandatory. */
    public static final String MSG_ID_NPAM1384E = "NPAM1384E";

    /** MSG_ID_NPAM1391E. */
    public static final String MSG_ID_NPAM1391E = "NPAM1391E";

    /** error text */
    public static final String ERR_GLBL_CMPY_CD = "Global Company Code";

    /** Interface ID */
    public static final String ERR_INTERFACE_ID = "Interface ID";

    /** Interface Transaction ID */
    public static final String ERR_TRANSACTION_ID = "Interface Transaction ID";

    /** Interface Segment ID */
    public static final String ERR_SEGMENT_ID = "Interface Segment ID";

    /** Interface Unit ID */
    public static final String ERR_UNIT_ID = "Interface Unit ID";

    /** Interface Sequence Number ID */
    public static final String ERR_SEQ_NUMBER = "Interface Sequence Number ID";

    /** PO Order Number */
    public static final String ERR_PO_ORD_NUM = "PO Order Number";

    /** SQL Bind Name for SSM */
    /** glblCmpyCd */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** poOrdNum */
    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** poStsCdValidated */
    public static final String BIND_PO_STS_CD_VALIDATED = "poStsCdValidated";

    /** poMsgTpCdSi */
    public static final String BIND_PO_MSG_TP_CD_SI = "poMsgTpCdSi";

    /** poMsgTpCdRn */
    public static final String BIND_PO_MSG_TP_CD_RN = "poMsgTpCdRn";

    /** poMsgTpCdSn */
    public static final String BIND_PO_MSG_TP_CD_SN = "poMsgTpCdSn";

    /** poMsgSegId */
    public static final String BIND_PO_MSG_SEG_ID = "poMsgSegId";

    /** yyyyMMddHHmmssSSS */
    public static final String TIMESTANP = "yyyyMMddHHmmssSSS";

    /** PO Header RecordNumber : 35 */
    public static final int HEADER_REC_NUM_SIZE = 35;

    /** PO Detal RecordNumber : 35 */
    public static final int DETAIL_REC_NUM_SIZE = 35;

    /** TRANSACTION_ID_MAX_LENGTH */
    public static final int TRANSACTION_ID_MAX_LENGTH = 15;

    /** PO_QLFY_CUT_LENGTH */
    public static final int PO_QLFY_CUT_LENGTH = 9;

    /** " " */
    public static final String SPACE = " ";

    /** "," */
    public static final String COMMA = ",";

    /** "" */
    public static final String EMPTY = "";

    /** "*" */
    public static final String ASTAR = "*";

    /** CR */
    public static final String CR = "\r";

    /** LF */
    public static final String LF = "\n";
    
    //QC#26074 Add Start
    /** prchGrpCd. */
    public static final String BIND_PRCH_GRP_CD = "prchGrpCd";

    /** BIND_RTL_WH_CD. */
    public static final String BIND_RTL_WH_CD = "rtlWhCd";
    
    /** DEST_RTL_WH_CD. */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** STAR. */
    public static final String STAR = "*";

    /** MD_VND_LOC_CD*/
    public static final String MD_VND_LOC_CD = "999";
    
    /** vndXrefTpCd. */
    public static final String BIND_VND_XREF_TP_CD = "vndXrefTpCd";

    /** eftFromDt. */
    public static final String BIND_EFF_FROM_DT = "eftFromDt";

    /** eftThruDt. */
    public static final String BIND_EFF_THRU_DT = "eftThruDt";

    /** slsDate. */
    public static final String BIND_SLS_DT = "slsDate";
    
    /** 29001231. */
    public static final String EFF_THRU_DATE = "29001231";

    /** 19000101. */
    public static final String EFF_FROM_DATE = "19000101";

    /** VND_XREF_TP_CD_1. */
    public static final String VND_XREF_TP_CD_1 = "1";

    //QC#26074 Add End

}
