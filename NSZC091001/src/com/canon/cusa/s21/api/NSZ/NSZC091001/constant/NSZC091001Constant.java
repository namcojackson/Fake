/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC091001.constant;

/**
 * <pre>
 * Create Service Call API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Hitachi         T.Aoyagi        Create          N/A
 * 2017/10/10   CITS            M.Naito         Update          QC#21428
 * 2017/11/06   Hitachi         M.Kidokoro      Update          QC#21991
 * </pre>
 */
public final class NSZC091001Constant {

    /**
     * Process Mode is not valid.
     */
    public static final String NSZM0039E = "NSZM0039E";

    /**
     * Input Parameter [Service Action Text] is mandatory.
     */
    public static final String NSZM0944E = "NSZM0944E";

    /**
     * Input Parameter [Service Task Number] is mandatory.
     */
    public static final String NSZM0945E = "NSZM0945E";

    /**
     * Input Parameter [Service Config Master PK] is mandatory.
     */
    public static final String NSZM0946E = "NSZM0946E";

    /**
     * Input Parameter [Contact Person First Name] is mandatory.
     */
    public static final String NSZM0947E = "NSZM0947E";

    /**
     * Input Parameter [Contact Person Last Name] is mandatory.
     */
    public static final String NSZM0948E = "NSZM0948E";

    /**
     * Input Parameter [Contact Person Email Address] is mandatory.
     */
    public static final String NSZM0949E = "NSZM0949E";

    /**
     * Input Parameter [Contact Person Cellular Phone Number] is mandatory.
     */
    public static final String NSZM0950E = "NSZM0950E";

    /**
     * Input Parameter [Service Customer Attention Text] is mandatory.
     */
    public static final String NSZM0951E = "NSZM0951E";

    /**
     * Input Parameter [DS Service Call Type Code] is mandatory.
     */
    public static final String NSZM0952E = "NSZM0952E";

    /**
     * Input Parameter [Service Task Received Date] is mandatory.
     */
    public static final String NSZM0953E = "NSZM0953E";

    // START 2017/11/06 M.Kidokoro [QC#21991, ADD]
    /**
     * IInput Parameter [Service Problem Type Code] is mandatory.
     */
    public static final String NSZM1313E = "NSZM1313E";
    // END 2017/11/06 M.Kidokoro [QC#21991, ADD]

    /**
     * Input Parameter [Service Task Number] is not existed.
     */
    public static final String NSZM0954E = "NSZM0954E";

    /**
     * Input Parameter [Service Config Master PK] is not existed.
     */
    public static final String NSZM0955E = "NSZM0955E";

    /**
     * Input Parameter [DS Service Call Type Code] is not existed.
     */
    public static final String NSZM0956E = "NSZM0956E";

    /**
     * Input Parameter [Technician Code] is not existed.
     */
    public static final String NSZM0957E = "NSZM0957E";

    /** Default Global Company Code : ADB */
    public static final String DEF_GLBL_CMPY_CD = "ADB";

    /** String Zero : 0 */
    public static final String STR_ZERO = "0";

    /** String One : 1 */
    public static final String STR_ONE = "1";

    /** String Comma : , */
    public static final String STR_COMMA = ",";

    /** String Under Bar : _ */
    public static final String STR_UNDER_BAR = "_";

    /** String Space :   */
    public static final String STR_SPACE = " ";

    /** Length : 60 */
    public static final int LENGTH_60 = 60;

    // START 2017/10/10 M.Naito [QC#21428, DEL]
//    /** Service Task Received Time Value : 000000 */
//    public static final String SVC_TASK_RCV_TM_VAL = "000000";
    // START 2017/10/10 M.Naito [QC#21428, DEL]

    // START 2017/10/10 M.Naito [QC#21428, ADD]
    /** Time Format : HHmmss */
    public static final String TM_FORMAT_HHMMSS = "HHmmss";
    // START 2017/10/10 M.Naito [QC#21428, ADD]

    /** Variable Character Constant Name : OOS_SVC_PBLM_TP_CD */
    public static final String OOS_SVC_PBLM_TP_CD = "OOS_SVC_PBLM_TP_CD";

    /** Variable Character Constant Name : OOS_SVC_MEMO_TP_CD */
    public static final String OOS_SVC_MEMO_TP_CD = "OOS_SVC_MEMO_TP_CD";
}
