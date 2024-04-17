/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC066001.constant;

/**
 * <pre>
 * NSZC066001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/06/2015   Hitachi         J.Kim           Create
 * 03/17/2016   Hitachi         A.Kohinata      Update          QC#5647
 * 09/28/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 2018/08/14   Hitachi         K.Kojima        Update          QC#27604
 * 08/16/2018   CITS            M.Naito         Update          QC#27250
 * </pre>
 */
public final class NSZC066001Constant {

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * No search results found.
     */
    public static final String NSZM0505E = "NSZM0505E";

    /**
     * Input parameter "CPO.CPO_ORD_TP_CD" is not Supply Order.
     */
    public static final String NSZM0608E = "NSZM0608E";

    // START 2018/08/16 M.Naito [QC#27250, ADD]
    /**
     * The target data does not exist in Contract.
     */
    public static final String NSZM0617E = "NSZM0617E";
    // END 2018/08/16 M.Naito [QC#27250, ADD]

    /**
     * TERM_COND_CAP LENGTH
     */
    public static final int TERM_COND_CAP_LENGTH = 3;

    /**
     * Cap - B/W Toner Original
     */
    public static final String TERM_COND_CAP_BW_ORG_ATTRB_NM = "TERM_COND_CAP_BW_ORG_ATTRB_NM";

    /**
     * Cap - Color Toner Original
     */
    public static final String TERM_COND_CAP_CLR_ORG_ATTRB_NM = "TERM_COND_CAP_CLR_ORG_ATTRB_NM";

    /**
     * Cap - Total Toner Original
     */
    public static final String TERM_COND_CAP_TOT_ORG_ATTRB_NM = "TERM_COND_CAP_TOT_ORG_ATTRB_NM";

    /**
     * Cap - Cap - Total Toner Running
     */
    public static final String TERM_COND_CAP_TOT_RUN_ATTRB_NM = "TERM_COND_CAP_TOT_RUN_ATTRB_NM";

    /**
     * Cap - B/W Toner Running
     */
    public static final String TERM_COND_CAP_BW_RUN_ATTRB_NM = "TERM_COND_CAP_BW_RUN_ATTRB_NM";

    /**
     * Cap - Color Toner Running
     */
    public static final String TERM_COND_CAP_CLR_RUN_ATTRB_NM = "TERM_COND_CAP_CLR_RUN_ATTRB_NM";

    /** Failed to register to the @ table. */
    public static final String NSZM0398E = "NSZM0398E";

    /** Failed to update the @ table.[@] */
    public static final String NSZM0399E = "NSZM0399E";

    // START 2016/09/28 A.Kohinata [QC#12898, ADD]
    /**
     * Input parameter "DS Contract Number" is a mandatory field.
     */
    public static final String NSZM0271E = "NSZM0271E";

    /**
     * Input parameter "CPO_DTL.SVC_MACH_MSTR_PK" is a mandatory field.
     */
    public static final String NSZM0615E = "NSZM0615E";
    // END 2016/09/28 A.Kohinata [QC#12898, ADD]

    // START 2018/08/14 K.Kojima [QC#27604,ADD]
    /** Var Char Const NSAL0990_TRGT_IMG_SPLY_TP */
    public static final String CONST_TRGT_IMG_SPLY_TP = "NSAL0990_TRGT_IMG_SPLY_TP";
    // END 2018/08/14 K.Kojima [QC#27604,ADD]
}
