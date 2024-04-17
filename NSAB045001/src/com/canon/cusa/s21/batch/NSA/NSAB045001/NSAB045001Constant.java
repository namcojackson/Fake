/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB045001;
/**
 * <pre>    
 * Batch Program Class for Send Meter Read To Sys85 Batch
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 06/17/2016   CITS            S.Endo      Create          
 * 02/16/2018   CITS            M.Naito         Update          QC#23855
 * 
 *</pre>
 */
public class NSAB045001Constant {
    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0600";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NLBB060001:";

    /** Message ID :ZZZM9025E/ [@] is mandatory.*/
    public static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Message ID :NMAM0176E/ It failed to register [@]*/
    public static final String MSG_ID_NMAM0176E = "NMAM0176E";

    /** Message ID :NMAM0038I/ The data does not exist.  [@]*/
    public static final String MSG_ID_NMAM0038I = "NMAM0038I";

    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Interface ID */
    public static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Padding String(Space)*/
    public static final String PADDING_LETTER_SPACE = " ";

    /** Value Length(SER_NUM) */
    public static final int VALUE_LENGTH_SER_NUM = 12;

    /** Value Length(MTR_CNTR_ID) */
    public static final int VALUE_LENGTH_MTR_CNTR_ID = 3;

    /** Value Length(ORG_NM) */
    public static final int VALUE_LENGTH_ORG_NM = 30;

    /** DB COLUMN : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN : SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** DB COLUMN : MTR_READ_DT */
    public static final String COL_MTR_READ_DT = "MTR_READ_DT";

    /** DB COLUMN : READ_MTR_CNT */
    public static final String COL_READ_MTR_CNT = "READ_MTR_CNT";

    /** DB COLUMN : MTR_CNTR_ID */
    public static final String COL_MTR_CNTR_ID = "MTR_CNTR_ID";

    /** DB COLUMN : TEST_MTR_CNT */
    public static final String COL_TEST_MTR_CNT = "TEST_MTR_CNT";

    /** DB COLUMN : ORG_NM */
    public static final String COL_ORG_NM = "ORG_NM";

    /** DB COLUMN : SVC_PHYS_MTR_READ_PK */
    public static final String COL_SVC_PHYS_MTR_READ_PK = "SVC_PHYS_MTR_READ_PK";

    // START 2018/02/16 M.Naito [QC#23855, ADD]
    /** DB COLUMN : MTR_GRP_TP_CD */
    public static final String COL_MTR_GRP_TP_CD = "MTR_GRP_TP_CD";

    /** DB COLUMN : BW_MTR_FLG */
    public static final String COL_BW_MTR_FLG = "BW_MTR_FLG";

    // END 2018/02/16 M.Naito [QC#23855, ADD]

    /** DB COLUMN : ORG_NM Value*/
    public static final String ORG_NM = "CBS";
}
