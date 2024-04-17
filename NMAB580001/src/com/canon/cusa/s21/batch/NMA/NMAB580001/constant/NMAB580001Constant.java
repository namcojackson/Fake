/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB580001.constant;

/**
 * <pre>
 * Send Supplier Info TO ARCS Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/28/2016   CITS            T.Wada          Create 
 *</pre>
 */
public class NMAB580001Constant {

    /**
     * Message ID : ZZM9000E  
     * [@] is mandatory.
     */
    public static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /**
     * Message ID : ZZZM9012E 
     * Data insert failure.(ReturnCode = [@])
     */
    public static final String MSG_ID_ZZZM9012E = "ZZZM9012E";

    /**
     * Message ID : ZZZM9013E
     * Data update failure.(ReturnCode = [@])
     * 
     */
    public static final String MSG_ID_ZZZM9013E = "ZZZM9013E";

    /**
     * Message ID : ZZZM9014E
     * Data delete failure.(ReturnCode = [@])
     * 
     */
    public static final String MSG_ID_ZZZM9014E = "ZZZM9014E";

    /**
     * Message ID : ZZBM0009I
     */
    public static final String MSG_ID_ZZBM0009I = "ZZBM0009I";

    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Global Company Code */
    public static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAB5800";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NMAB580001:";

    /**
     * DB COLUMN : PRNT_VND_CD
     */
    public static final String COL_PRNT_VND_CD = "PRNT_VND_CD";

    /**
     * DB COLUMN : PRNT_VND_NM
     */
    public static final String COL_PRNT_VND_NM = "PRNT_VND_NM";

    /**
     * DB COLUMN : TAX_PAYER_ID
     */
    public static final String COL_TAX_PAYER_ID = "TAX_PAYER_ID";

    /**
     * DB COLUMN : ARCS_VND_TP_LKUP_TXT
     */
    public static final String COL_ARCS_VND_TP_LKUP_TXT = "ARCS_VND_TP_LKUP_TXT";

    /**
     * DB COLUMN : VND_PK
     */
    public static final String COL_VND_PK = "VND_PK";

    /**
     * DB COLUMN : VND_CD
     */
    public static final String COL_VND_CD = "VND_CD";

    /**
     * DB COLUMN : LOC_NM
     */
    public static final String COL_LOC_NM = "LOC_NM";

    /**
     * DB COLUMN : EFF_FROM_DT
     */
    public static final String COL_EFF_FROM_DT = "EFF_FROM_DT";

    /**
     * DB COLUMN : CTRY_CD
     */
    public static final String COL_CTRY_CD = "CTRY_CD";

    /**
     * DB COLUMN : FIRST_LINE_ADDR
     */
    public static final String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * DB COLUMN : SCD_LINE_ADDR
     */
    public static final String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * DB COLUMN : THIRD_LINE_ADDR
     */
    public static final String COL_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * DB COLUMN : FRTH_LINE_ADDR
     */
    public static final String COL_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * DB COLUMN : CTY_ADDR
     */
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /**
     * DB COLUMN : ST_CD
     */
    public static final String COL_ST_CD = "ST_CD";

    /**
     * DB COLUMN : POST_CD
     */
    public static final String COL_POST_CD = "POST_CD";

    /**
     * DB COLUMN : ARCS_PAY_GRP_LKUP_TXT
     */
    public static final String COL_ARCS_PAY_GRP_LKUP_TXT = "ARCS_PAY_GRP_LKUP_TXT";

    /**
     * MAX Length Of POST_CD
     */
    public static final int LEN_MAX_POST_CD = 10;
}
