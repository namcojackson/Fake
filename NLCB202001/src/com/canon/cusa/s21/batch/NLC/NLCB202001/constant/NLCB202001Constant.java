/**
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB202001.constant;

/**
 * <pre>
 * Batch Program Class for Demand Master to Baxter
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/07/2021   CITS            A.Marte         Create          QC#58267
 * 05/25/2022   CITS            F.Fadriquela    Update          QC#60051
 * 06/16/2022   CITS            F.Fadriquela    Update          QC#60241
 * 09/20/2023   CITS            F.Komaki        Update          QC#61856
 * 11/06/2023   CITS            K.Ikeda         Update          QC#61856
 *</pre>
 */
public class NLCB202001Constant {
    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLCB2020";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /**
     * Message ID: ZZM9000E [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Message ID: NLAM1295E Failed to register [@]
     */
    public static final String NLAM1295E = "NLAM1295E";

    // *********************************************************
    // Constants: Message Parameter
    // *********************************************************

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PARAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PARAM_NM_INTERFACE_ID = "Interface ID";

    /** Parameter Name: VAR_USER1 */
    public static final String PARAM_NM_VAR_USER1 = "VAR_USER1";

    /** Parameter Name: VAR_USER2 */
    public static final String PARAM_NM_VAR_USER2 = "VAR_USER2";

    // *********************************************************
    // Constants: Format pattern
    // *********************************************************

    /** Datetime format : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMdd_HHmmss";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** DB Table NLCI2020_01 */
    public static final String TBL_NLCI2020_01 = "NLCI2020_01";

    /** DB Table NLCI2021_01 */
    public static final String TBL_NLCI2021_01 = "NLCI2021_01";

    /** IF Name NLCI2020 */
    public static final String IF_NLCI2020 = "NLCI2020";

    /** IF Name NLCI2021 */
    public static final String IF_NLCI2021 = "NLCI2021";

    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column name of MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column name of WH_RG_CD */
    public static final String WH_RG_CD = "WH_RG_CD";

    /** Column name of INP_SRC_CD */
    public static final String INP_SRC_CD = "INP_SRC_CD";

    /** Column name of MAT_ORD_QTY */
    public static final String MAT_ORD_QTY = "MAT_ORD_QTY";

    /** Column name of MAT_ORD_DT */
    public static final String MAT_ORD_DT = "MAT_ORD_DT";

    /** Column name of MAT_ORD_TM */
    public static final String MAT_ORD_TM = "MAT_ORD_TM";

    /** Column name of MAT_SHIP_QTY */
    public static final String MAT_SHIP_QTY = "MAT_SHIP_QTY";

    /** Column name of MAT_SHIP_DT */
    public static final String MAT_SHIP_DT = "MAT_SHIP_DT";

    /** Column name of MAT_SHIP_TM */
    public static final String MAT_SHIP_TM = "MAT_SHIP_TM";

    /** Column name of MAT_REQ_DT */
    public static final String MAT_REQ_DT = "MAT_REQ_DT";

    /** Column name of MAT_REQ_TM */
    public static final String MAT_REQ_TM = "MAT_REQ_TM";

    /** Column name of MAT_RCV_DT */
    public static final String MAT_RCV_DT = "MAT_RCV_DT";

    /** Column name of MAT_RCV_TM */
    public static final String MAT_RCV_TM = "MAT_RCV_TM";

    /** Column name of DMND_INFO_ID */
    public static final String DMND_INFO_ID = "DMND_INFO_ID";

    /** Column name of CUST_ORD_ID */
    public static final String CUST_ORD_ID = "CUST_ORD_ID";

    /** Column name of SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** Column name of DELY_POST_CD */
    public static final String DELY_POST_CD = "DELY_POST_CD";

    /** Column name of DELY_CTRY_CD */
    public static final String DELY_CTRY_CD = "DELY_CTRY_CD";

    /** Column name of DELY_CTY_ADDR */
    public static final String DELY_CTY_ADDR = "DELY_CTY_ADDR";

    /** Column name of DELY_ST_CD */
    public static final String DELY_ST_CD = "DELY_ST_CD";

    /** Column name of DELY_CUST_NM */
    public static final String DELY_CUST_NM = "DELY_CUST_NM";

    /** Column name of TRX_TP_CD */
    public static final String TRX_TP_CD = "TRX_TP_CD";

    /** Column name of DMND_RSP_COMIT_CD */
    public static final String DMND_RSP_COMIT_CD = "DMND_RSP_COMIT_CD";

    /** Column name of COV_DAYS_NUM */
    public static final String COV_DAYS_NUM = "COV_DAYS_NUM";

    /** Column name of COV_HOURS_NUM */
    public static final String COV_HOURS_NUM = "COV_HOURS_NUM";

    /** Column name of MAJ_ACCT_ID */
    public static final String MAJ_ACCT_ID = "MAJ_ACCT_ID";

    /** Column name of DMND_PRTY_CD */
    public static final String DMND_PRTY_CD = "DMND_PRTY_CD";

    /** Column name of DMND_PROD_CD */
    public static final String DMND_PROD_CD = "DMND_PROD_CD";

    /** Column name of SPEC_RG_CD */
    public static final String SPEC_RG_CD = "SPEC_RG_CD";

    /** Column name of SPEC_SITE_CD */
    public static final String SPEC_SITE_CD = "SPEC_SITE_CD";

    /** Column name of SHIP_FROM_RG_CD */
    public static final String SHIP_FROM_RG_CD = "SHIP_FROM_RG_CD";

    /** Column name of SHIP_FROM_SITE_CD */
    public static final String SHIP_FROM_SITE_CD = "SHIP_FROM_SITE_CD";

    /** Column name of SHIP_TO_RG_CD */
    public static final String SHIP_TO_RG_CD = "SHIP_TO_RG_CD";

    /** Column name of SHIP_TO_SITE_CD */
    public static final String SHIP_TO_SITE_CD = "SHIP_TO_SITE_CD";

    /** Column name of NTWK_CONFIG_NM */
    public static final String NTWK_CONFIG_NM = "NTWK_CONFIG_NM";

    /** Column name of CUST_RPT_HIT_FLG */
    public static final String CUST_RPT_HIT_FLG = "CUST_RPT_HIT_FLG";

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** */
    public static final String S21 = "S21";

    /** */
    public static final String VAL_HYPHEN = "-";

    /** */
    public static final String VAL_SPACE = " ";

    /** */
    public static final String VAL_SO = "SO";

    /** */
    public static final String VAL_SR = "SR";

    /** */
    public static final String VAL_US_CTRY_CD = "US";

    /** */
    public static final String VAL_DS_ORD_TP_INVENTORY_REQUEST = "0069";

    /** */
    public static final String VAL_SCE_ORD_TP_CD = "TR";

    /** */
    public static final String VAL_WH_RG_CD = "PPS-DC-US";

    /** */
    public static final String VAL_RTL_WH_CD = "DS";

    /** */
    public static final String VAL_RTL_SWH_CD = "NEW";

    // 2023/09/20 QC#61856 START
    /** */
    // public static final String VAL_PRT_USED_BY_TECH_CD = "0010001";

    /** */
    // public static final String VAL_SVC_BR_CD = "99";
    // 2023/09/20 QC#61856 END

    /** */
    public static final Integer VAL_SVC_TERM_COND_ATTRB_PK = 53;

    /** */
    public static final String INCLUDE_DS_SO_LINE_STS_CD_LIST = "7,99";

    // START 2022/05/25 F.Fadriquela [QC#60051, ADD]
    // 2023/09/20 QC#61856 START
    /** FSR_STS_CD_COMPLETED - 99*/
    // public static final String FSR_STS_CD_COMPLETED = "99";

    /** FSR_STS_CD_CANCELLED - 99*/
    // public static final String FSR_STS_CD_CANCELLED = "99";

    /** FSR_STS_CD_CLOSED - 95*/
    // public static final String FSR_STS_CD_CLOSED = "95";

    // 2023/11/06 K.Ikeda QC#61856 START
    /** FSR_VISIT_STS_CD_COMPLETED - 90*/
    public static final String FSR_VISIT_STS_CD_COMPLETED = "90";
    // 2023/11/06 K.Ikeda QC#61856 END

    /** FSR_VISIT_STS_CD_CLOSED - 95*/
    public static final String FSR_VISIT_STS_CD_CLOSED = "95";
    // 2023/09/20 QC#61856 END
    // END 2022/05/25 F.Fadriquela [QC#60051, ADD]

    // START 2022/06/16 F.Fadriquela [QC#60241, ADD]
    /** VAL_PPS_TECH_TM - PPS-TECH-TM*/
    public static final String VAL_PPS_TECH_TM = "PPS-TECH-TM";

    // 2023/11/06 K.Ikeda QC#61856 START
    /** VAL_PPS - PPS*/
    // public static final String VAL_PPS = "PPS";
    // 2023/11/06 K.Ikeda QC#61856 END

    /** VAL_TOOL_EXPENSE - TOOL EXPENSE*/
    public static final String VAL_TOOL_EXPENSE = "TOOL EXPENSE";

    /** VAL_FIELD_ADJUSTMENT - FIELD ADJUSTMENT*/
    public static final String VAL_FIELD_ADJUSTMENT = "FIELD ADJUSTMENT";

    /** RTL_SWH_CD_GOOD_PARTS - G*/
    public static final String RTL_SWH_CD_GOOD_PARTS = "G";
    // END 2022/06/16 F.Fadriquela [QC#60241, ADD]
}
