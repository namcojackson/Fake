/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC127001.constant;

/**
 * <pre>
 * Reman Task Update API
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS            S.Tanikawa      Create          N/A
 * 08/02/2016   CSAI            Y.Imazu         Update          QC#12826
 * 01/29/2018   CITS            T.Wada          Update          QC#23072
 * </pre>
 */
public class NPZC127001Constant {
    /**
     * Mode 1:CREATE
     */
    public static final String MODE_CREATE = "1";

    /**
     * Mode 2:UPDATE
     */
    public static final String MODE_UPDATE = "2";

    /**
     * Mode 3:DELETE
     */
    public static final String MODE_DELETE = "3";

    /**
     * Mode 4:COMPLETION
     */
    public static final String MODE_COMPLETION = "4";

    /**
     * Mode 5:SUBMIT
     */
    public static final String MODE_SUBMIT = "5";
    /**
     * DB Column: INVTY_AVAL_QTY
     */
    public static final String INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /**
     * DB Column: RTRN_REQ_PRT_FLG
     */
    public static final String RTRN_REQ_PRT_FLG = "RTRN_REQ_PRT_FLG";

    /**
     * DB Column: RTRN_DSPL_TP_CD
     */
    public static final String RTRN_DSPL_TP_CD = "RTRN_DSPL_TP_CD";

    /** . */
    public static final int TASK_NUM_LEN = 3;

    // =================================================
    // Message Code
    // =================================================
    /**
     * mandatory check: GLBL_CMPY_CD
     */
    public static final String NPZM0179E = "NPZM0179E";

    /**
     * Input parameter "Mode Code" is a mandatory field.
     */
    public static final String NPZM0219E = "NPZM0219E";

    /**
     * Input parameter "Reman Order Number" is a mandatory field.
     */
    public static final String NPZM0220E = "NPZM0220E";

    /**
     * Input parameter "Reman Task Number" is a mandatory field.
     */
    public static final String NPZM0221E = "NPZM0221E";

    /**
     * Input parameter "Technician Code" is a mandatory field.
     */
    public static final String NPZM0222E = "NPZM0222E";

    /**
     * Input parameter "Reman Labor Hours" is a mandatory field.
     */
    public static final String NPZM0223E = "NPZM0223E";

    /**
     * Input parameter "Reman Task Start DATE" is a mandatory field.
     */
    public static final String NPZM0224E = "NPZM0224E";

    /**
     * Input parameter "Reman Task End DATE" is a mandatory field.
     */
    public static final String NPZM0225E = "NPZM0225E";

    /**
     * There is no Open Reman Order.
     */
    public static final String NPAM1371E = "NPAM1371E";

    /**
     * Reman Task Start Date should be the same day of Reman Order
     * Start Date or later date.
     */
    public static final String NPAM1372E = "NPAM1372E";

    /**
     * There is no Reman Task.
     */
    public static final String NPAM1373E = "NPAM1373E";

    /**
     * Failed to create a Reman Task.
     */
    public static final String NPZM0226E = "NPZM0226E";

    /**
     * Failed to update a Reman Task.
     */
    public static final String NPZM0227E = "NPZM0227E";

    /**
     * Failed to create a Reman Parts Usage.
     */
    public static final String NPZM0228E = "NPZM0228E";

    /**
     * Failed to update a Reman Parts Usage.
     */
    public static final String NPZM0229E = "NPZM0229E";

    /**
     * The quantity entered is exceeding the inventory balance.
     */
    public static final String NLCM0081E = "NLCM0081E";

    /**
     * Input parameter "Reman Task Description" is a mandatory field.
     */
    public static final String NPZM0269E = "NPZM0269E";

}
