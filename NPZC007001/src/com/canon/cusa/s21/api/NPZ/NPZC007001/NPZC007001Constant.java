/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC007001;

/**
 * <pre>
 * Create PO Report API Constant Define
 * Date          Company     Name          Create/Update     Defect No
 * ----------------------------------------------------------------------
 * 05/08/2012    Fujitsu     K.Matsumura   Create            N/A
 * 26/06/2012    Fujitsu     S.Nakai       Update            #405
 * 11/15/2012    Hitachi     T.Tomita      Update            V1.1
 * 02/01/2016    CITS        R.Shimamoto   Update            V1.5
 * 11/28/2016    CITS        Y.Fujii       Update            R350
 * 01/06/2017    CITS        Y.Fujii       Update            QC#17002
 * 10/20/2017    CITS        T.Hakodate    UPDATE            QC#20246
 * 04/23/2018    CITS        K.Kameoka     Update            QC#21354
 * 04/27/2018    CITS        K.Kameoka     Update            QC#25841
 * 05/31/2018    CITS        K.Kameoka     Update            QC#26328
 *</pre>
 */
public class NPZC007001Constant {

    // STR 2012/11/19 T.Tomita [V1.1 MOD]
    // for Debug
    /** CST_DEBUG_MSG_LVL. */
    public static final int CST_DEBUG_MSG_LVL = 10;

    /** REPRINT. */
    public static final String REPRINT = "REPRINT";

    /** TEL. */
    public static final String TEL = "TEL:";

    /** FAX. */
    public static final String FAX = "FAX:";

    /** BLANK_TEL. */
    public static final String BLANK_TEL = "                 ";

    // STR 2016/02/01 R.Shimamoto [V1.5 ADD]
    /** SET Item Blank. */
    public static final String BLANK_SET_ITEM = "   ";
    // END 2016/02/01 R.Shimamoto [V1.5 ADD]

    /** BLANK */
    public static final String BLANK = "";

    /** BLANK_ONE */
    public static final String BLANK_ONE = " ";

    /** COMMA */
    public static final String COMMA = ",";

    /** AMOUNT_SCALE */
    public static final int AMOUNT_SCALE = 2;

    // STR 2012/11/19 T.Tomita [V1.1 ADD]
    /** Global Company Code is required. */
    public static final String NPZM0001E = "NPZM0001E";

    /** Warehouse Code is required. */
    public static final String NPZM0078E = "NPZM0078E";

    /** Vendor Code is required. */
    public static final String NPZM0079E = "NPZM0079E";

    /** PO Number is required. */
    public static final String NPZM0018E = "NPZM0018E";

    /** User ID is required. */
    public static final String NPZM0087E = "NPZM0087E";

    /**
     * The process cannot be executed because the "PO Status" is
     * "Saved".
     */
    public static final String NPZM0080E = "NPZM0080E";

    /**
     * The process cannot be executed because the "PO Status" is
     * "Waiting Approval".
     */
    public static final String NPZM0081E = "NPZM0081E";

    /** Specified PO Information does not exist. */
    public static final String NPZM0082E = "NPZM0082E";

    /** Specified Vnd Information does not exist. */
    public static final String NPZM0083E = "NPZM0083E";

    /** Specified PO DTL Information does not exist. */
    public static final String NPZM0084E = "NPZM0084E";

    /** Specified SHIP_TO_CUST Information does not exist. */
    public static final String NPZM0085E = "NPZM0085E";

    /** It failed to insert the PO_RPT_WRK table. */
    public static final String NPZM0086E = "NPZM0086E";

    /** It's already processed. */
    public static final String NPAM0024E = "NPAM0024E";

    /** "PO Report Information" does not exists. [PO_RPT_INFO_TP_CD=NT] */
    public static final String NPZM0298E = "NPZM0298E";

    /** "PO Report Information" does not exists. [PO_RPT_INFO_TP_CD=TC] */
    public static final String NPZM0299E = "NPZM0299E";

    /** VAR_CHAR_CONST Key : NPAF0010_GLBL_CMPY_NM */
    public static final String NPAF0010_GLBL_CMPY_NM = "NPAF0010_GLBL_CMPY_NM";

    /** VAR_CHAR_CONST Key : NPAF0010_GLBL_FULL_CMPY_ADDR */
    public static final String NPAF0010_GLBL_FULL_CMPY_ADDR = "NPAF0010_GLBL_FULL_CMPY_ADDR";

    /** VAR_CHAR_CONST Key : NPAF0010_GLBL_CMPY_TEL_NUM */
    public static final String NPAF0010_GLBL_CMPY_TEL_NUM = "NPAF0010_GLBL_CMPY_TEL_NUM";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_NM */
    public static final String NPAF0010_BILL_TO_NM = "NPAF0010_BILL_TO_NM";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ATTN_NM */
    public static final String NPAF0010_BILL_TO_ATTN_NM = "NPAF0010_BILL_TO_ATTN_NM";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR1 */
    public static final String NPAF0010_BILL_TO_ADDR1 = "NPAF0010_BILL_TO_ADDR1";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR2 */
    public static final String NPAF0010_BILL_TO_ADDR2 = "NPAF0010_BILL_TO_ADDR2";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR3 */
    public static final String NPAF0010_BILL_TO_ADDR3 = "NPAF0010_BILL_TO_ADDR3";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR4 */
    public static final String NPAF0010_BILL_TO_ADDR4 = "NPAF0010_BILL_TO_ADDR4";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR5 */
    public static final String NPAF0010_BILL_TO_ADDR5 = "NPAF0010_BILL_TO_ADDR5";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR6 */
    public static final String NPAF0010_BILL_TO_ADDR6 = "NPAF0010_BILL_TO_ADDR6";

    /** VAR_CHAR_CONST Key : NPAF0010_CMPY_ADDR1 */
    public static final String NPAF0010_CMPY_ADDR1 = "NPAF0010_CMPY_ADDR1";

    /** VAR_CHAR_CONST Key : NPAF0010_CMPY_ADDR2 */
    public static final String NPAF0010_CMPY_ADDR2 = "NPAF0010_CMPY_ADDR2";

    /** VAR_CHAR_CONST Key : NPAF0010_CMPY_ADDR3 */
    public static final String NPAF0010_CMPY_ADDR3 = "NPAF0010_CMPY_ADDR3";

    /** VAR_CHAR_CONST Key : NPAF0010_CMPY_ADDR4 */
    public static final String NPAF0010_CMPY_ADDR4 = "NPAF0010_CMPY_ADDR4";

    /** VAR_CHAR_CONST Key : NPAF0010_CMPY_ADDR5 */
    public static final String NPAF0010_CMPY_ADDR5 = "NPAF0010_CMPY_ADDR5";

    /** Pattern Value : 1 */
    public static final int PATTERN_1 = 1;

    /** Pattern Value : 2 */
    public static final int PATTERN_2 = 2;

    /** Pattern Value : 3 */
    public static final int PATTERN_3 = 3;

    /** Pattern Value : 4 */
    public static final int PATTERN_4 = 4;

    // END 2012/11/19 T.Tomita [V1.1 ADD]

    // STR 2016/02/01 R.Shimamoto [V1.5 ADD]
    /** PO Comment Length Start : 0 */
    public static final int PO_COMMENT_LENGTH_STR = 0;

    /** PO Comment Length End : 120 */
    public static final int PO_COMMENT_LENGTH_END = 120;

    /** PO Report Merchandise Code Text Length Max : 70 */
    public static final int PO_RPT_MDSE_CD_TXT_LENGTH_MAX = 70;

    // QC#20246 MOD START 2017/10/20 START
    /** First Line Addr Length Max : 60 */
    public static final int FIRST_LINE_ADDR_LENGTH_MAX = 60;
    // QC#20246 MOD START 2017/10/20 END
    
    /** Merchandise Name Length Max : 30 */
    public static final int MDSE_NM_LENGTH_MAX = 30;

    /** Date Length 0 :  */
    public static final int DATE_LENGTH_0 = 0;

    /** Date Length 8 :  */
    public static final int DATE_LENGTH_8 = 8;

    /** DB_PARAM_GLBL_CMPY_CD : glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB_PARAM_PO_ORD_NUM : poOrdNum */
    public static final String DB_PARAM_PO_ORD_NUM = "poOrdNum";

    /** DB_PARAM_PO_STS_CD : poStsCd */
    public static final String DB_PARAM_PO_STS_CD = "poStsCd";

    /** DB_PARAM_USR_ID : usrId */
    public static final String DB_PARAM_USR_ID = "usrId";

    /** DB_PARAM_RCV_RPT_TS : rcvRptTs */
    public static final String DB_PARAM_RCV_RPT_TS = "rcvRptTs";

    /** DB_PARAM_PO_RPT_INFO_TP_CD : poRptInfoTpCd */
    public static final String DB_PARAM_PO_RPT_INFO_TP_CD = "poRptInfoTpCd";

    /** PROC_DT : procDt */
    public static final String DB_PARAM_PROC_DT = "procDt";

    /** DB_PARAM_ORG_STRU_TP_CD : orgStruTpCd */
    public static final String DB_PARAM_ORG_STRU_TP_CD = "orgStruTpCd";

    /** DB_PARAM_GNRN_TP_CD_01 : gnrnTpCd_01 */
    public static final String DB_PARAM_GNRN_TP_CD_01 = "gnrnTpCd_01";

    /** DB_PARAM_GNRN_TP_CD_02 : gnrnTpCd_02 */
    public static final String DB_PARAM_GNRN_TP_CD_02 = "gnrnTpCd_02";

    /** DB_PARAM_PSN_CD : psnCd */
    public static final String DB_PARAM_PSN_CD = "psnCd";

    /** PO_ORD_TERM_TXT_01_SUFIX : */
    public static final String PO_ORD_TERM_TXT_01_SUFIX = "Ship Via:";

    /** PO_ORD_TERM_TXT_02_SUFIX : */
    public static final String PO_ORD_TERM_TXT_02_SUFIX = "Freight Terms:";

    /** PO_ORD_TERM_TXT_03_SUFIX : */
    public static final String PO_ORD_TERM_TXT_03_SUFIX = "Payment Terms:";

    /** DB Column Name: P_PO_STS_CD */
    public static final String DB_COLUMN_P_PO_STS_CD = "P_PO_STS_CD";

    /** DB Column Name: P_PO_PRINT_FLG */
    public static final String DB_COLUMN_P_PO_PRINT_FLG = "P_PO_PRINT_FLG";

    /** DB Column Name: P_PO_SUBMT_PSN_CD */
    public static final String DB_COLUMN_P_PO_SUBMT_PSN_CD = "P_PO_SUBMT_PSN_CD";

    /** DB Column Name: P_PO_APVL_DT */
    public static final String DB_COLUMN_P_PO_APVL_DT = "P_PO_APVL_DT";

    /** DB Column Name: DP_VND_PMT_TERM_DESC_TXT */
    public static final String DB_COLUMN_DP_VND_PMT_TERM_DESC_TXT = "DP_VND_PMT_TERM_DESC_TXT";

    /** DB Column Name: DP_RQST_TECH_TOC_CD */
    public static final String DB_COLUMN_DP_RQST_TECH_TOC_CD = "DP_RQST_TECH_TOC_CD";

    /** DB Column Name: DP_PRNT_VND_CD */
    public static final String DB_COLUMN_DP_PRNT_VND_CD = "DP_PRNT_VND_CD";
    // END 2016/02/01 R.Shimamoto [V1.5 ADD]

    /** DB Column Name: DP_PRCH_GRP_CD */
    public static final String DB_COLUMN_DP_PRCH_GRP_CD = "DP_PRCH_GRP_CD";

    //QC#25841 MOD START
    /** DB Column Name: DB_PARAM_PO_LINE_STS_CD */
    public static final String DB_PARAM_PO_LINE_STS_CD = "poLineStsCd";
    //QC#25841 MOD END

    /** NUM_CONST Key: NPAF0010_PURGE_DT */
    public static final String NPAF0010_PURGE_DT = "NPAF0010_PURGE_DT";

    /** NUM_CONST Key: NPAF0010_LINE_NUM_OF_1ST_PAGE */
    public static final String NPAF0010_LINE_NUM_OF_1ST_PAGE = "NPAF0010_LINE_NUM_OF_1ST_PAGE";

    /** NUM_CONST Key: NPAF0010_LINE_NUM_OF_2ND_PAGE */
    public static final String NPAF0010_LINE_NUM_OF_2ND_PAGE = "NPAF0010_LINE_NUM_OF_2ND_PAGE";

    /** NUM_CONST Key: NPAF0010_NUM_OF_DESC_LTR */
    public static final String NPAF0010_NUM_OF_DESC_LTR = "NPAF0010_NUM_OF_DESC_LTR";
    
    /** DB Column Name: VND_ISS_ORD_NUM */
    public static final String DB_COLUMN_VND_ISS_ORD_NUM = "VND_ISS_ORD_NUM";
    
    /** DB Column Name: PO_SUBMT_PSN_CD */
    public static final String DB_COLUMN_PO_SUBMT_PSN_CD = "PO_SUBMT_PSN_CD";
    
    /** DB Column Name: PRCH_GRP_CD_ESS */
    public static final String PRCH_GRP_CD_ESS = "ESS";
    
    //QC#26328 Add Start
    /** DB Column Name: CUST_ISS_ORD_NUM */
    public static final String DB_COLUMN_CUST_ISS_ORD_NUM = "CUST_ISS_ORD_NUM";
    //QC#26328 Add End
    
    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_NM */
    public static final String BILL_TO_NM_ESS = "BILL_TO_NM_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ATTN_NM */
    public static final String BILL_TO_ATTN_NM_ESS = "BILL_TO_ATTN_NM_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR1 */
    public static final String BILL_TO_ADDR1_ESS = "BILL_TO_ADDR1_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR2 */
    public static final String BILL_TO_ADDR2_ESS = "BILL_TO_ADDR2_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR3 */
    public static final String BILL_TO_ADDR3_ESS = "BILL_TO_ADDR3_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR4 */
    public static final String BILL_TO_ADDR4_ESS = "BILL_TO_ADDR4_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR5 */
    public static final String BILL_TO_ADDR5_ESS = "BILL_TO_ADDR5_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR6 */
    public static final String BILL_TO_ADDR6_ESS = "BILL_TO_ADDR6_ESS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_NM */
    public static final String BILL_TO_NM_LFS_PPS = "BILL_TO_NM_LFS_PPS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ATTN_NM */
    public static final String BILL_TO_ATTN_NM_LFS_PPS = "BILL_TO_ATTN_NM_LFS_PPS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR1 */
    public static final String BILL_TO_ADDR1_LFS_PPS = "BILL_TO_ADDR1_LFS_PPS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR2 */
    public static final String BILL_TO_ADDR2_LFS_PPS = "BILL_TO_ADDR2_LFS_PPS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR3 */
    public static final String BILL_TO_ADDR3_LFS_PPS = "BILL_TO_ADDR3_LFS_PPS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR4 */
    public static final String BILL_TO_ADDR4_LFS_PPS = "BILL_TO_ADDR4_LFS_PPS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR5 */
    public static final String BILL_TO_ADDR5_LFS_PPS = "BILL_TO_ADDR5_LFS_PPS";

    /** VAR_CHAR_CONST Key : NPAF0010_BILL_TO_ADDR6 */
    public static final String BILL_TO_ADDR6_LFS_PPS = "BILL_TO_ADDR6_LFS_PPS";
}
