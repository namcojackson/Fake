/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0200.constant;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 * 2015/11/25   Hitachi         T.Harada           Update          CSA
 *</pre>
 */
public class NSBL0200Constant {

    /** BUSSINESS_ID (NSBL0200) */
    public static final String BUSINESS_APPLICATION_ID = "NSBL0200";

    /** Screen ID (NSBL0200Scrn00) */
    public static final String SCREEN_ID = "NSBL0200Scrn00";

    /**
     * BUTTON_NAME : btn8
     */
    public static final String BUTTON_NAME_CLEAR = "btn8";

    /**
     * BUTTON_NAME : btn10
     */
    public static final String BUTTON_NAME_RETURN = "btn10";

    /**
     * BUTTON_GUARD : btn8
     */
    public static final String BUTTON_GUARD_CLEAR = "CMN_Clear";

    /**
     * BUTTON_GUARD : btn10
     */
    public static final String BUTTON_GUARD_CLOSE = "CMN_Close";

    /**
     * BUTTON_LABEL : btn8
     */
    public static final String BUTTON_LABEL_CLEAR = "Clear";

    /**
     * BUTTON_LABEL : btn10
     */
    public static final String BUTTON_LABEL_CLOSE = "Close";

    /**
     * Screen arguments(Model Name)
     */
    public static final int ARGS_MODEL_NAME = 0;

    /**
     * Screen arguments(MDSE Code)
     */
    public static final int ARGS_MDSE_CODE = 1;

    // START 2015/11/25 [CSA,CHANGE]
    /**
     * Screen arguments(Service Machine Master PK)
     */
    public static final int ARGS_SVC_MACH_MSTR_PK = 2;

    ///**
    // * Screen arguments(Layer)
    // */
    ////public static final int ARGS_ORG_LAYER_NUM = 2;

    /**
     * Screen arguments(Serial Number)
     */
    public static final int ARGS_SER_NUM = 3;

    ///**
    // * Screen arguments(Org Cd)
    // */
    //public static final int ARGS_ORG_CD = 3;
    // END 2015/11/25 [CSA,CHANGE]

    /**
     * Screen arguments(Tech Code)
     */
    public static final int ARGS_TECH_CODE = 4;



    /**
     * Table ID
     */
    public static final String TBL_ID = "A";


    /** format */
    public static final String FORMAT_TM = "^([0-1][0-9]|2[0-3]):([0-5][0-9])$";

    /** format */
    public static final String FORMAT_TM_DB = "$1$200";

    /**
     * Message ID (NSBM0004E)
     */
    public static final String NSBM0004E = "NSBM0004E";

    /**
     * Message ID (NSBM0051E)
     */
    public static final String NSBM0051E = "NSBM0051E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** CLOSE */
    public static final String CLOSE = "CMN_Close";
}
