/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC056001.constant;

/**
 * <pre>
 * Running Toner Count Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/23/2015   Hitachi         K.Yamada        Create          New
 * 09/29/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 06/01/2018   Fujitsu         Nagashima       Update          QC#25966
 * 03/07/2019   Hitachi         S.Kitamura      Update          QC#30661
 * </pre>
 */
public final class NWZC056001Constant {
    /**
     * "Global Company Code" is required.
     */
    public static final String NWZM0188E = "NWZM0188E";

    /**
     * Sales Date is required.
     */
    public static final String NWZM0346E = "NWZM0346E";

    /**
     * Contract Number of the parameter is not set.
     */
    public static final String NWZM1143E = "NWZM1143E";

    /**
     * "Merchandise Code" for the entered parameter is not set.
     */
    public static final String NWZM0996E = "NWZM0996E";

    /**
     * The entered "Merchandise Code" does not exist in the Master.
     */
    public static final String NWZM0598E = "NWZM0598E";

    /**
     * The process abended.
     */
    public static final String NWZM0818E = "NWZM0818E";

    /**
     * The quantity is incorrect number. Please re-examine the values.
     */
    public static final String NWZM1563E = "NWZM1563E";

    /**
     * Failed to update service terms and conditions.
     */
    public static final String NWZM1564E = "NWZM1564E";

    /**
     * Contract does not exist.
     */
    public static final String NWZM1302E = "NWZM1302E";

    /**
     * Running Toner Count Information is not set.
     */
    public static final String NWZM1569E = "NWZM1569E";

    // START 2016/09/29 A.Kohinata [QC#12898, DEL]
//    /**
//     * Service Machine Master Pk is mandatory.
//     */
//    public static final String NWZM1570E = "NWZM1570E";
    // END 2016/09/29 A.Kohinata [QC#12898, DEL]

    /**
     * Update Quantity is mandatory.
     */
    public static final String NWZM1571E = "NWZM1571E";

    /**
     * Cap Running information is not registered in T&C.
     */
    public static final String NWZM1572E = "NWZM1572E";

    /**
     * It failed to register in DS_ACPO.
     */
    public static final String NWZM1573E = "NWZM1573E";

    /**
     * It failed to register in DS_ACPO_TRGT_DTL.
     */
    public static final String NWZM1574E = "NWZM1574E";

    /**
     * Black/White cap running
     */
    public static final String TERM_COND_CAP_BW_RUN_ATTRB_NM = "TERM_COND_CAP_BW_RUN_ATTRB_NM";

    /**
     * Color cap running
     */
    public static final String TERM_COND_CAP_CLR_RUN_ATTRB_NM = "TERM_COND_CAP_CLR_RUN_ATTRB_NM";

    /**
     * Total cap running
     */
    public static final String TERM_COND_CAP_TOT_RUN_ATTRB_NM = "TERM_COND_CAP_TOT_RUN_ATTRB_NM";
    // START 2019/03/07 S.Kitamura [QC#30661, ADD]
    /**
     * Black/White cap Original
     */
    public static final String TERM_COND_CAP_BW_ORG_ATTRB_NM = "TERM_COND_CAP_BW_ORG_ATTRB_NM";

    /**
     * Color cap Original
     */
    public static final String TERM_COND_CAP_CLR_ORG_ATTRB_NM = "TERM_COND_CAP_CLR_ORG_ATTRB_NM";

    /**
     * Total cap Original
     */
    public static final String TERM_COND_CAP_TOT_ORG_ATTRB_NM = "TERM_COND_CAP_TOT_ORG_ATTRB_NM";
    // END 2019/03/07 S.Kitamura [QC#30661, ADD]

    /**
     * Total cap running
     */
    public static final String DS_ACPO_NUM_SEQ = "QC#";

    // START 2018/06/01 H.Nagashima [QC#25966, ADD]
    /** Var Char Const NWZC0560_TRGT_IMG_SPLY_TP */
    public static final String CONST_TRGT_IMG_SPLY_TP = "NWZC0560_TRGT_IMG_SPLY_TP";
    // END 2018/06/01 H.Nagashima [QC#25966, ADD]


}
