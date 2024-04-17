/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB130001.constant;

/**
 * <pre>
 * NPAB1300 Outstanding PO
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/20   CITS            R.Shimamoto     Create          N/A
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 02/04/2020   Fujitsu         S.Iidaka        Update          QC#55572
 *</pre>
 */
public class NPAB130001Constant {

    // -- Error Message Code --------------------
    /** [@] is mandatory. */
    public static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Data insert failure. */
    public static final String MSG_ID_NPAM1341E = "NPAM1341E";

    /** Data delete failure. (table name is [@]). */
    public static final String MSG_ID_NPAM1342E = "NPAM1342E";

    /** No search results found of [@]. */
    public static final String MSG_ID_NPAM0020E = "NPAM0020E";

    /** Error of API [@]. */
    public static final String MSG_ID_NPAM1178E = "NPAM1178E";

    /**
     * This cannot be converted to "Trading Partner Carrier Code".Key
     * Value [@].
     */
    public static final String MSG_ID_NPAM1343E = "NPAM1343E";

    // -- Const --------------
    /** Insert Count */
    public static final int INS_UNI_CNT = 1000;

    /** Commit Count */
    public static final int COMMIT_CNT = 1000;

    /** CINC_GLBL_WH_CD ="N/A" */
    public static final String CINC_GLBL_WH_CD_NA = "N/A";

    /** CINC_CO_NUM ="N/A" */
    public static final String CINC_CO_NUM_NA = "N/A";

    /** CARR_CD ="*" */
    public static final String CARR_CD = "*";

    /** CINC_PO_GLBL_CATG_CD_A1 ="A1" */
    public static final String CINC_PO_GLBL_CATG_CD_A1 = "A1";

    //QC# 26966 Add.
    /** CINC_PO_GLBL_CATG_CD_AZ ="AZ" */
    public static final String CINC_PO_GLBL_CATG_CD_AZ = "AZ";

    /** PRT_CHRG_IND_C ="C" */
    public static final String PRT_CHRG_IND_C = "C";

    /** DELETE_DATS ="-2" */
    public static final int DELETE_DAYS = -2;

    /** Variable Character Const Key (Cinc Vendor Code) */
    public static final String VAR_CHAR_CONST_KEY_CINC_VND_CD = "SCUBE_IF_CINC_VND_CD";

    /** Variable Character Const Key (Include Tech Flag) */
    public static final String VAR_CHAR_CONST_KEY_INCL_TECH_FLG = "PRT_INCL_TECH_INVTY_CINC_FLG";

    /** Variable Character Const Key (Exclude Location Code) */
    public static final String VAR_CHAR_CONST_KEY_EXCL_LOC_CD = "PRT_EXCL_INVTY_LOC_CD_CINC";

    /** Variable Character Const Key (Exclude Shipping Method Other) */
    public static final String VAR_CHAR_CONST_KEY_SHPG_METH_OTHER = "SCUBE_IF_SHPG_METH_OTHER";

    // QC#26966 Add.
    /** Variable Character Const Key (CINC_GLBL_WH_CD_CLMBUS) */
    public static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS = "CINC_GLBL_WH_CD_CLMBUS";

    // QC#30964 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST = "SCUBE_EXCL_MDSE_CD_LIST";

    // QC#55572 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST = "SCUBE_EXCL_SWH_CD_LIST";

    // QC#26966 Add.
    /** Canon inc global warehouse code of CLUMBUS */
    public static final String CINC_GLBL_WH_CD_CLMBUS_DEFAULT = "CLMBS";

    /** INTFC_PO_RMN_WRK TableName */
    public static final String TABLE_INTFC_PO_RMN_WRK = "Table: INTFC_PO_RMN_WRK";

    // STR 05/09/2016 R.Shimamoto [V2.0 ADD]
    /** EFF_FROM_DT_DEFALUT */
    public static final String EFF_FROM_DT_DEFALUT = "99991231";

    /** DATE_TRIM_START_IDX */
    public static final int DATE_TRIM_START_IDX = 1;

    /** DATE_TRIM_END_IDX */
    public static final int DATE_TRIM_END_IDX = 8;

    /** RTL_WH_CD_AST */
    public static final String RTL_WH_CD_AST = "*";

    /** WH_OWNR_CD_AST */
    public static final String WH_OWNR_CD_AST = "*";

    /** LOC_TP_CD_AST */
    public static final String LOC_TP_CD_AST = "*";

    /** FRT_COND_CD */
    public static final String FRT_COND_CD = "*";

    /** FROM_RTL_WH_CD_AST */
    public static final String FROM_RTL_WH_CD_AST = "*";

    /** FROM_WH_OWNR_CD_AST */
    public static final String FROM_WH_OWNR_CD_AST = "*";

    /** FROM_LOC_TP_CD_AST */
    public static final String FROM_LOC_TP_CD_AST = "*";

    /** TO_RTL_WH_CD_AST */
    public static final String TO_RTL_WH_CD_AST = "*";

    /** TO_WH_OWNR_CD_AST */
    public static final String TO_WH_OWNR_CD_AST = "*";

    /** TO_LOC_TP_CD_AST */
    public static final String TO_LOC_TP_CD_AST = "*";

    /** Variable Character Const Key (SCUBE_IF_CUSA_VND_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD = "SCUBE_IF_CUSA_VND_CD";

    /** Variable Character Const Key (SCUBE_VND_SYS_TP_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD = "SCUBE_VND_SYS_TP_CD";

    /** QTY_ZERO */
    public static final int QTY_ZERO = 0;

    /** NUM:1 */
    public static final int NUM_ONE = 1;
    // END 05/09/2016 R.Shimamoto [V2.0 ADD]
}
