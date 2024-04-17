/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB021001.constant;

/**
 * <pre>
 * NSAB0210Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Harada        Create
 * 2017/08/04   Hitachi         A.Kohinata      Update          QC#18799
 * 2022/09/20   Hitachi         D.Yoshida       Update          QC#60070
 * </pre>
 */
public final class NSAB021001Constant {
    /** A value is not set in the required parameter field. Field Name:  [@] */
    public static final String NSZM0589E = "NSZM0589E";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    // del start 2017/08/04 QC#18799
//    /** BLLG_MTR_READ_WINDOW_BEF_DAYS */
//    public static final String BLLG_MTR_READ_WINDOW_BEF_DAYS = "BLLG_MTR_READ_WINDOW_BEF_DAYS";
    // del end 2017/08/04 QC#18799

    // add start 2017/08/04 QC#18799
    /** DATE FORMAT: yyyymmdd */
    public static final String DATE_FORMAT = "yyyymmdd";

    /** DEF_WINDOW_BEF_DAYS */
    // START 2022/09/20 [QC#60070,MOD]
//    public static final String DEF_WINDOW_BEF_DAYS = "0";
    public static final int DEF_WINDOW_BEF_DAYS = 0;
    // add end 2017/08/04 QC#18799
    // START 2022/09/20 [QC#60070,MOD]
}
