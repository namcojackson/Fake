package com.canon.cusa.s21.batch.NLC.NLCB117001;

/**
 * <pre>
 * Adjust Physical Inventory from Tech PI
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/10/2018   CITS            Y.Iwasaki       Create          QC#10572
 * 05/07/2019   CITS            T.Tokutomi      Update          QC#50029
 * </pre>
 */ 

public class NLCB117001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLCB1170";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int DEBUG_MSG_LVL = 1;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************

    /** Message ID : ZZBM0009I The  (@)  was (@) . ResultCount = (@)  */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** Message ID : ZZM9000E The field of [@] is not input. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : ZZM9004E The value which is not numerical was input to the field of [@]. */
    public static final String ZZM9004E = "ZZM9004E";

    /**
     * Message ID: NLGM0045E The record cannot be registered.
     * Registration Table Name: [@], Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    // QC#50029 Add.
    /**
     * Failed to update "PHYS_INVTY_CTRL".
     */
    public static final String NLCM0163E = "NLCM0163E";

    // QC#50029 Add.
    /**
     * Failed to register PHYS_INVTY_ADJ.
     */
    public static final String NLZM2453E = "NLZM2453E";

    // QC#50029 Add.
    /**
     * Failed to update PHYS_INVTY_CNT_HDR.
     */
    public static final String NLZM2454E = "NLZM2454E";

    // *********************************************************
    // Constants: Message Parameter
    // *********************************************************

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PARAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PARAM_NM_INTERFACE_ID = "Interface ID";

    /** Parameter Name : VAR_USER1 */
    public static final String PARAM_NM_VAR_USER1 = "XXX(VAR_USER1)";

    /** Parameter Name : VAR_USER2 */
    public static final String PARAM_NM_VAR_USER2 = "XXX(VAR_USER2)";

    /** Parameter Name : VAR_USER3 */
    public static final String PARAM_NM_VAR_USER3 = "XXX(VAR_USER3)";

    /** TDATE_BEGININDEXWO. */
    public static final int DATE_BEGININDEX = 0;

    /** DATE_ENDINDEX. */
    public static final int DATE_ENDINDEX = 8;


    // *********************************************************
    // Constants: Format pattern
    // *********************************************************

    /** Format: yyyyMMdd */
    public static final String FMT_YYYYMMDD = "yyyyMMdd";

    /** Format: yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Format: yyyyMMddHHmmssSSS */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    // *********************************************************
    // Constants: VAR_CHAR_CONST Name
    // *********************************************************

    /** VAR_CHAR_CONST: XXXX */
    //public static final String VAR_CHAR_CONST_NM_XXXX = "XXXX";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** Table: XXXX */
    //public static final String TBL_XXXX = "XXXX";

    /** Table: PHYS_INVTY_CTRL */
    public static final String TBL_PHYS_INVTY_CTRL = "PHYS_INVTY_CTRL";

    /** Table: PHYS_INVTY_CNT_HDR */
    public static final String TBL_PHYS_INVTY_CNT_HDR = "PHYS_INVTY_CNT_HDR";

    /** Table: PHYS_INVTY_CNT_DTL */
    public static final String TBL_PHYS_INVTY_CNT_DTL = "PHYS_INVTY_CNT_DTL";

    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column: XXXX */
    //public static final String COL_XXXX = "XXXX";

    /** Column: GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column: PHYS_INVTY_CTRL_PK */
    public static final String COL_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";

    /** Column: WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column: RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** Column: RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column: LOC_TP_CD */
    public static final String COL_LOC_TP_CD = "LOC_TP_CD";

    /** Column: PHYS_INVTY_CTRL_NM */
    public static final String COL_PHYS_INVTY_CTRL_NM = "PHYS_INVTY_CTRL_NM";

    /** Column: PHYS_INVTY_CTRL_DESC_NM */
    public static final String COL_PHYS_INVTY_CTRL_DESC_NM = "PHYS_INVTY_CTRL_DESC_NM";

    /** Column: PHYS_INVTY_DT */
    public static final String COL_PHYS_INVTY_DT = "PHYS_INVTY_DT";

    /** Column: PHYS_INVTY_STS_CD */
    public static final String COL_PHYS_INVTY_STS_CD = "PHYS_INVTY_STS_CD";

    /** Column: PHYS_INVTY_STS_NM */
    public static final String COL_PHYS_INVTY_STS_NM = "PHYS_INVTY_STS_NM";

    /** Column: PHYS_INVTY_NUM */
    public static final String COL_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";

    /** Column: PHYS_INVTY_START_TS */
    public static final String COL_PHYS_INVTY_START_TS = "PHYS_INVTY_START_TS";

    /** Column: ADJ_SUBMT_TS */
    public static final String COL_ADJ_SUBMT_TS = "ADJ_SUBMT_TS";

    /** Column: TECH_TOC_CD */
    public static final String COL_TECH_TOC_CD = "TECH_TOC_CD";

    /** Column: TECH_NM */
    public static final String COL_TECH_NM = "TECH_NM";

    /** Column: PHYS_INVTY_CNT_STS_CD */
    public static final String COL_PHYS_INVTY_CNT_STS_CD = "PHYS_INVTY_CNT_STS_CD";

    /** Column: ADJ_GRS_AMT */
    public static final String COL_ADJ_GRS_AMT = "ADJ_GRS_AMT";

    /** Column: ADJ_NET_AMT */
    public static final String COL_ADJ_NET_AMT = "ADJ_NET_AMT";

    /** Column: PHYS_INVTY_CNT_HDR_PK */
    public static final String COL_PHYS_INVTY_CNT_HDR_PK = "PHYS_INVTY_CNT_HDR_PK";

    /** Column: MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column: STK_STS_CD */
    public static final String COL_STK_STS_CD = "STK_STS_CD";

    /** Column: TECH_LOC_TP_CD */
    public static final String COL_TECH_LOC_TP_CD = "TECH_LOC_TP_CD";

    /** Column: SYS_CNT_REGD_FLG */
    public static final String COL_SYS_CNT_REGD_FLG = "SYS_CNT_REGD_FLG";

    /** Column: FIRST_CNT_INP_QTY */
    public static final String COL_FIRST_CNT_INP_QTY = "FIRST_CNT_INP_QTY";

    /** Column: FIRST_CNT_INP_TS */
    public static final String COL_FIRST_CNT_INP_TS = "FIRST_CNT_INP_TS";

    /** Column: SCD_CNT_INP_QTY */
    public static final String COL_SCD_CNT_INP_QTY = "SCD_CNT_INP_QTY";

    /** Column: SCD_CNT_INP_TS */
    public static final String COL_SCD_CNT_INP_TS = "SCD_CNT_INP_TS";

    /** Column: LTST_CNT_INP_QTY */
    public static final String COL_LTST_CNT_INP_QTY = "LTST_CNT_INP_QTY";

    /** Column: LTST_CNT_INP_TS */
    public static final String COL_LTST_CNT_INP_TS = "LTST_CNT_INP_TS";

    /** Column: INVTY_AVAL_QTY */
    public static final String COL_INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /** Column: INVTY_REGD_TS */
    public static final String COL_INVTY_REGD_TS = "INVTY_REGD_TS";

    /** Column: ADJ_VAR_FLG */
    public static final String COL_ADJ_VAR_FLG = "ADJ_VAR_FLG";

    /** Column: ADJ_VAR_QTY */
    public static final String COL_ADJ_VAR_QTY = "ADJ_VAR_QTY";

    /** Column: ADJ_VAR_AMT */
    public static final String COL_ADJ_VAR_AMT = "ADJ_VAR_AMT";

    /** Column: INVTY_ORD_NUM */
    public static final String COL_INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** Column: PHYS_INVTY_ADJ_NM */
    public static final String COL_PHYS_INVTY_ADJ_NM = "PHYS_INVTY_ADJ_NM";

    /** Column: PHYS_INVTY_CNT_DTL_PK */
    public static final String COL_PHYS_INVTY_CNT_DTL_PK = "PHYS_INVTY_CNT_DTL_PK";

    /** Column: SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** Column: CNT_INP_QTY */
    public static final String COL_CNT_INP_QTY = "CNT_INP_QTY";

    /** Column: PHYS_INVTY_ADJ_STS_CD */
    public static final String COL_PHYS_INVTY_ADJ_STS_CD = "PHYS_INVTY_ADJ_STS_CD";

    /** Column: PHYS_INVTY_ADJ_MSG_TXT */
    public static final String COL_PHYS_INVTY_ADJ_MSG_TXT = "PHYS_INVTY_ADJ_MSG_TXT";

    /** Column: SVC_CONFIG_MSTR_PK */
    public static final String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** Column: TAG_NO */
    public static final String COL_TAG_NO = "TAG_NO";

    /** Column: CNT_COMP_FLG */
    public static final String COL_CNT_COMP_FLG = "CNT_COMP_FLG";

    /** Column: RCV_SER_TAKE_FLG */
    public static final String COL_RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /** Column: SVC_MACH_MSTR_AVAL_FLG */
    public static final String COL_SVC_MACH_MSTR_AVAL_FLG = "SVC_MACH_MSTR_AVAL_FLG";

    /** Column: CNT_COMP_Y_CNT */
    public static final String COL_CNT_COMP_Y_CNT = "CNT_COMP_Y_CNT";

    /** Column: CNT_COMP_N_CNT */
    public static final String COL_CNT_COMP_N_CNT = "CNT_COMP_N_CNT";

    /** Column: ADJ_VAR_Y_CNT */
    public static final String COL_ADJ_VAR_Y_CNT = "ADJ_VAR_Y_CNT";

    /** Column: LOC_STS_CD */
    public static final String COL_LOC_STS_CD = "LOC_STS_CD";

    /** Column: SVC_MACH_MSTR_STS_CD */
    public static final String COL_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";

    /** Column: PHYS_INVTY_CNT_HDR_NEW_PK */
    public static final String COL_PHYS_INVTY_CNT_HDR_NEW_PK = "PHYS_INVTY_CNT_HDR_NEW_PK";

    /** Column: PHYS_INVTY_CNT_DTL_NEW_PK */
    public static final String COL_PHYS_INVTY_CNT_DTL_NEW_PK = "PHYS_INVTY_CNT_DTL_NEW_PK";

    /** Column: INVTY_AVAL_DTL_QTY */
    public static final String COL_INVTY_AVAL_DTL_QTY = "INVTY_AVAL_DTL_QTY";

    // QC#50029 Add.
    /** Column: dcStock */
    public static final String COL_SUB_TOT_ADJ_VAR_QTY = "SUB_TOT_ADJ_VAR_QTY";
    
    // QC#50029 Add.
    /** Column: dcStock */
    public static final String COL_SUB_TOT_ADJ_VAR_AMT = "SUB_TOT_ADJ_VAR_AMT";
    
    // QC#50029 Add.
    /** Column: dcStock */
    public static final String COL_TOT_ADJ_VAR_GRS_AMT = "TOT_ADJ_VAR_GRS_AMT";
    
    // QC#50029 Add.
    /** Column: dcStock */
    public static final String COL_TOT_ADJ_VAR_NET_AMT = "TOT_ADJ_VAR_NET_AMT";

    // QC#50029 Add.
    /** Column: dcStock */
    public static final String COL_DC_STOCK = "DC_STOCK";

    // QC#50029 Add.
    /** Column: flgOnY */
    public static final String COL_FLG_ON_Y = "FLG_ON_Y";

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** Variable: XXXX */
    //public static final String VAR_XXXX = "XXXX";

    /** Variable: CREATE_MODE */
    public static final String VAR_CREATE_MODE = "01";

    // QC#50029 Add.
    /** Column: MAX_NM_LENGHT */
    public static final int MAX_NM_LENGTH = 64;

}
