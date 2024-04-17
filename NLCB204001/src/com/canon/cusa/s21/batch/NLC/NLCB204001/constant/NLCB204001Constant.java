/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB204001.constant;

/**
 * <pre>
 * NLCB204001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/03/06   Hitachi         S.Moriai        Create          QC#63475
 * </pre>
 */
public final class NLCB204001Constant {
    /** Business Application ID */
    public static final String BIZ_APP_ID = "NLCB2040";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = "NLCL1020T020";

    /** LOC_STS_CD */
    public static final String LOC_STS_CD = "03";

    /***/
    public static final int MAXLENGTH = 3;

    /***/
    public static final String PADDING_STR = "0";

    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** There is no parameter in [@]. */
    public static final String NLCM0246E = "NLCM0246E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is invalid. */
    public static final String NLCM0247E = "NLCM0247E";

    /**
     * Since the record corresponding to the Upload CSV Request Table
     * (UPLD_CSV_RQST) does not exist, it will be abended.
     */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** Processing record count has gone over the limit of max [@]. */
    public static final String NLCM0248E = "NLCM0248E";

    /** Processing record count is 0. */
    public static final String NLCM0249E = "NLCM0249E";

    /** CSV upload user don't have the authorization to process. */
    public static final String NLCM0250E = "NLCM0250E";

    /** Entered "Merchandise Code" does not exist. */
    public static final String NLCM0002E = "NLCM0002E";

    /** Entered "Location Code" does not exist. */
    public static final String NLCM0004E = "NLCM0004E";

    /** The same Code cannot be selected for @ and @. */
    public static final String NLCM0035E = "NLCM0035E";

    /** Please enter the same value for "From Sub Warehouse" and "To Sub Warehouse". */
    public static final String NLZM2302E = "NLZM2302E";

    /** Tech WH/SWH[@] can not be used because Tech PI is being executed. */
    public static final String NLCM0232E = "NLCM0232E";

    /** Entered @ cannot handle the target inventory. */
    public static final String NLCM0067E = "NLCM0067E";

    /** Inventory with the entered Key does not exist. */
    public static final String NLCM0021E = "NLCM0021E";

    /** "Current Available" has a negative value. */
    public static final String NLCM0032E = "NLCM0032E";

    /** Entered @ exceeded @. */
    public static final String NLCM0016E = "NLCM0016E";

    /** Upload failed.(@) */
    public static final String NLCM0251E = "NLCM0251E";

    /** @ */
    public static final String NLCM0252E = "NLCM0252E";

    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
}
