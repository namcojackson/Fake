/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB172001.constant;
/**
 * <pre>    
 * Min Max Planning Shut-Off - Technicians
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 07/13/2016   CITS            S.Endo      Create          
 * 
 *</pre>
 */
public class NPAB172001Constant {
    /** Date Format:yyyyMMdd */
    public static final String YYYYMMDD = "yyyyMMdd";
    /** Date Format(DB):YYYYMMDD */
    public static final String DB_YYYYMMDD = "YYYYMMDD";
    /** Date Format:MM/dd/yyyy */
    public static final String MMDDYYYY = "MM/dd/yyyy";
    /** Date Format:MM/dd/yyyy HH:mm */
    public static final String MMDDYYYYHHMM = "MM/dd/yyyy HH:mm";
    /** Date Format:yyyyMMddHHmmssSSS */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    /** SubString Date Value Start Index */
    public static final String DT_SUBSTR_START = "1";
    /** SubString Date Value End Index */
    public static final String DT_SUBSTR_END = "8";
    /** SQL Like String '%' */
    public static final String LIKE_PREFIX = "%";
    /** SQL String:DT_SUBSTR_START */
    public static final String STR_DT_SUBSTR_START = "dtSubstrStart";
    /** SQL String:DT_SUBSTR_END */
    public static final String STR_DT_SUBSTR_END = "dtSubstEnd";
    /** SQL String:DT_LIKE_PREFIX */
    public static final String STR_LIKE_PREFIX = "likePrefix";
    /** Column:PRCH_REQ_REC_TP_CD */
    public static final String COL_PRCH_REQ_REC_TP_CD = "PRCH_REQ_REC_TP_CD";
    /** Column:GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** Column:PSN_CD */
    public static final String COL_PSN_CD = "PSN_CD";
    /** Column:INVTY_LOC_CD */
    public static final String COL_INVTY_LOC_CD = "INVTY_LOC_CD";
    /** Column:LOC_STS_CD */
    public static final String COL_LOC_STS_CD = "LOC_STS_CD";
    /** Column:STK_STS_CD */
    public static final String COL_STK_STS_CD = "STK_STS_CD";
    /** Column:TECH_TOC_CD */
    public static final String COL_TECH_TOC_CD = "TECH_TOC_CD";
    /** Column:MRP_INFO_PK */
    public static final String  COL_MRP_INFO_PK = "MRP_INFO_PK";
    /** Column:TECH_NM */
    public static final String  COL_TECH_NM = "TECH_NM";
    /** Column:RTL_WH_CD */
    public static final String  COL_RTL_WH_CD = "RTL_WH_CD";
    /** Column:RTL_SWH_CD */
    public static final String  COL_RTL_SWH_CD = "RTL_SWH_CD";
    /** Column:MDSE_CD */
    public static final String  COL_MDSE_CD = "MDSE_CD";
    /** Column:MDSE_DESC_SHORT_TXT */
    public static final String  COL_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** Column:ROP_QTY */
    public static final String  COL_ROP_QTY = "ROP_QTY";
    /** Column:MAX_INVTY_QTY */
    public static final String  COL_MAX_INVTY_QTY = "MAX_INVTY_QTY";
    /** Column:INVTY_QTY */
    public static final String  COL_INVTY_QTY = "INVTY_QTY";
    /** Column:LAST_INVTY_RCV_DT */
    public static final String  COL_LAST_INVTY_RCV_DT = "LAST_INVTY_RCV_DT";
    /** Column:TMTH_TOT_STD_COST_AMT */
    public static final String  COL_THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";
    /** Column:MRP_ENBL_FLG */
    public static final String  COL_MRP_ENBL_FLG = "MRP_ENBL_FLG";
    /** Column:CRAT_TS */
    public static final String  COL_CRAT_TS = "CRAT_TS";
    /** Column:MAIL */
    public static final String  COL_MAIL = "MAIL";
    /** Column:SUPV_MAIL */
    public static final String  COL_SUPV_MAIL = "SUPV_MAIL";
    /** Column:ORD_TAKE_MDSE_CD */
    public static final String  COL_ORD_TAKE_MDSE_CD = "ORD_TAKE_MDSE_CD";
    /** Table:MIN_MAX_SHUT_OFF_LOG */
    public static final String TABLE_MIN_MAX_SHUT_OFF_LOG = "MIN_MAX_SHUT_OFF_LOG";
    /** Table:MRP_INFO */
    public static final String TABLE_MRP_INFO = "MRP_INFO";
    /** Empty String */
    public static final String EMPTY = "";
    /** White Space String */
    public static final String WHITESPACE = " ";
    /** Mail FromGroup BusinessId */
    public static final String FROM_BIZID = "FROM0005";
    /** Mail templateId */
    public static final String TEMPLATE_ID = "NPAB1720M001";
    /** Message ID :NPAM1172E/ Failed to insert. [@] */
    public static final String MSG_ID_NPAM1172E = "NPAM1172E";
    /** Message ID :NPAM1171E/ Failed to update. [@] */
    public static final String MSG_ID_NPAM1171E = "NPAM1171E";
    /** Message ID :ZZM9010E/ Date verification error occurred in [@] field (mm/dd/yyyy). */
    public static final String MSG_ID_ZZM9010E = "ZZM9010E";
    /** PARAMETER : techNM */
    public static final String PARAM_TECH_NM = "techNM";
    /** PARAMETER : techWH */
    public static final String PARAM_TECH_WH = "techWH";
    /** PARAMETER : techSWH */
    public static final String PARAM_TECH_SWH = "techSWH";
    /** PARAMETER : message */
    public static final String PARAM_MESSAGE = "message";
    /** PARAMETER : dtFormat */
    public static final String PARAM_DTFORMAT = "dtFormat";
    /** Mail Body Item Number Width */
    public static final int WIDTH_ITEM_NUMBER = 16;
    /** Mail Body Item Description Width */
    public static final int WIDTH_ITEM_DESC = 30;
    /** Mail Body Min Value Width */
    public static final int WIDTH_MIN_VALUE = 10;
    /** Mail Body Max Value Width */
    public static final int WIDTH_MAX_VALUE = 10;
    /** Mail Body On-Hand Qty Width */
    public static final int WIDTH_ONHAND_QTY = 10;
    /** Mail Body Date Item Last Received Width */
    public static final int WIDTH_DATE_ITEM_LAST_RECEIVED = 14;
    /** Mail Body Item Cost Width */
    public static final int WIDTH_ITEM_COST = 10;
    /** Mail Body ShutOff Date/Time Width */
    public static final int WIDTH_SHUTOFF_DATE_TIME = 16;
    /** Mail Body Program Run By Width */
    public static final int WIDTH_PROGRAM_RUN_BY = 8;
    /** CratsByPsnCd Width */
    public static final int WIDTH_CRATS_BY_PSN_CD = 8;
    /** Param use ORD_TAKE_MDSE_CD*/
    public static final String PARAM_ORD_TAKE_MDSE_CD = "ordTakeMdseCd";
    /** Param use currentDt*/
    public static final String PARAM_CURRENT_DT= "currentDt";
    /** Param:PRCH_REQ_REC_TP_CD */
    public static final String PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";
    /** Column:GLBL_CMPY_CD */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** Column:PSN_CD */
    public static final String PARAM_PSN_CD = "psnCd";
    /** Column:INVTY_LOC_CD */
    public static final String PARAM_INVTY_LOC_CD = "invtyLocCd";
    /** Column:LOC_STS_CD */
    public static final String PARAM_LOC_STS_CD = "locStsCd";
    /** Column:STK_STS_CD */
    public static final String PARAM_STK_STS_CD = "stkStsCd";
    /** Column:MDSE_CD */
    public static final String  PARAM_MDSE_CD = "mdseCd";
    
}
