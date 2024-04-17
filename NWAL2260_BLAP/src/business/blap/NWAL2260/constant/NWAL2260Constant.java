/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2260.constant;

/**
 *<pre>
 * Import Attribute Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public final class NWAL2260Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NWAL2260";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9005W = "ZZZM9005W";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** 
     * Value is missing in the parameter's required field.[@]
     */
    public static final String NWZM1107E = "NWZM1107E";

    /** 
     * Failed to insert the @.
     */
    public static final String NWAM0790E = "NWAM0790E";

    /** 
     * Failed to update the @.
     */
    public static final String NWAM0791E = "NWAM0791E";

    /**
     *  Param Name : DS_IMPT_ORD_PK
     */
    public static final String PRM_NM_DS_IMPT_ORD_PK = "DS_IMPT_ORD_PK";

    /**
     *  Param Name : ORD_SRC_REF_NUM
     */
    public static final String PRM_NM_ORD_SRC_REF_NUM = "ORD_SRC_REF_NUM";

    /**
     *  Param Name : CPO_SRC_TP_CD
     */
    public static final String PRM_NM_CPO_SRC_TP_CD = "CPO_SRC_TP_CD";

    /**
     *  Param Name : ORD_SRC_REF_LINE_NUM
     */
    public static final String PRM_NM_ORD_SRC_REF_LINE_NUM = "ORD_SRC_REF_LINE_NUM";

    /**
     * Param Name :  ORD_SRC_REF_LINE_SUB_NUM
     */
    public static final String PRM_NM_ORD_SRC_REF_LINE_SUB_NUM = "ORD_SRC_REF_LINE_SUB_NUM";

    /**
     * Default scrLbNm for header mode
     */
    public static final String SCR_LB_NM_HEADER = "Import Attribute Text ";

    /**
     * Default scrLbNm for detail mode
     */
    public static final String SCR_LB_NM_DETAIL = "Import Detail Attribute Text ";

    /**
     * Number Length : 2
     */
    public static final int NUM_LEN_2 = 2;

    /**
     * String : 0
     */
    public static final String STR_ZERO = "0";

    /**
     *  Period
     */
    public static final String STR_PERIOD = ".";

    /**
     *  Table Name : DS_IMPT_EXT_ATTRB
     */
    public static final String DS_IMPT_EXT_ATTRB = "DS_IMPT_EXT_ATTRB";

    /**
     *  Table Name : DS_IMPT_DTL_EXT_ATTRB
     */
    public static final String DS_IMPT_DTL_EXT_ATTRB = "DS_IMPT_DTL_EXT_ATTRB";

    /**
     * VAR_CHAR_CONST:NWAL2260_ATTRB_CTRL_ID
     */
    public static final String NWAL2260_ATTRB_CTRL_ID = "NWAL2260_ATTRB_CTRL_ID";
}
