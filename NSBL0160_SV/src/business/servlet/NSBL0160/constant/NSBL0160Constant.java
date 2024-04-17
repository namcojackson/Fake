/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0160.constant;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi         Create          N/A
 * 2015/10/29   Hitachi         T.Tomita           Update          N/A
 * 2016/02/19   Hitachi         K.Kasai            Update          QC#3689
 *</pre>
 */
public class NSBL0160Constant {

    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NSBL0160";


    /**
     * Screen ID (NSBL0160Scrn00)
     */
    public static final String SCREEN_ID = "NSBL0160Scrn00";

    /**
     * Function ID (NSBL0160T010) Read Only
     */
    public static final String FUNCTION_ID_READ = "NSBL0160T010";

    /**
     * Function ID (NSBL0160T020) Update
     */
    public static final String FUNCTION_ID_UPD = "NSBL0160T020";

    /**
     * BUTTON_NAME : btn1
     */
    public static final String BUTTON_NAME_SAVE = "btn1";

    /**
     * BUTTON_NAME : btn2
     */
    public static final String BUTTON_NAME_SUBMIT = "btn2";

    /**
     * BUTTON_NAME : btn3
     */
    public static final String BUTTON_NAME_APPLY = "btn3";

    /**
     * BUTTON_NAME : btn4
     */
    public static final String BUTTON_NAME_APPROVE = "btn4";

    /**
     * BUTTON_NAME : btn5
     */
    public static final String BUTTON_NAME_REJECT = "btn5";

    /**
     * BUTTON_NAME : btn6
     */
    public static final String BUTTON_NAME_DOWNLOAD = "btn6";

    /**
     * BUTTON_NAME : btn7
     */
    public static final String BUTTON_NAME_DELETE = "btn7";

    /**
     * BUTTON_NAME : btn8
     */
    public static final String BUTTON_NAME_CLEAR = "btn8";

    /**
     * BUTTON_NAME : btn9
     */
    public static final String BUTTON_NAME_RESET = "btn9";

    /**
     * BUTTON_NAME : btn10
     */
    public static final String BUTTON_NAME_RETURN = "btn10";

    // START 2015/10/29 T.Tomita [N/A, MOD]
    /**
     * BUTTON_NAME : Search
     */
    public static final String BUTTON_NAME_SEARCH = "Search";
    // END 2015/10/29 T.Tomita [N/A, MOD]

    /**
     * BUTTON_NAME : Add
     */
    public static final String BUTTON_NAME_ADD = "Add_Cmnt";

    /**
     * BUTTON_GUARD : btn1
     */
    public static final String BUTTON_GUARD_SAVE = "CMN_Save";

    /**
     * BUTTON_GUARD : btn2
     */
    public static final String BUTTON_GUARD_SUBMIT = "CMN_Submit";

    /**
     * BUTTON_GUARD : btn3
     */
    public static final String BUTTON_GUARD_APPLY = "CMN_Apply";

    /**
     * BUTTON_GUARD : btn4
     */
    public static final String BUTTON_GUARD_APPROVE = "CMN_Approve";

    /**
     * BUTTON_GUARD : btn5
     */
    public static final String BUTTON_GUARD_REJECT = "CMN_Reject";

    /**
     * BUTTON_GUARD : btn6
     */
    public static final String BUTTON_GUARD_DOWNLOAD = "CMN_Download";

    /**
     * BUTTON_GUARD : btn7
     */
    public static final String BUTTON_GUARD_DELETE = "CMN_Delete";

    /**
     * BUTTON_GUARD : btn8
     */
    public static final String BUTTON_GUARD_CLEAR = "CMN_Clear";

    /**
     * BUTTON_GUARD : btn9
     */
    public static final String BUTTON_GUARD_RESET = "CMN_Reset";

    /**
     * BUTTON_GUARD : btn10
     */
    public static final String BUTTON_GUARD_RETURN = "CMN_Return";

    /**
     * BUTTON_LABEL : btn1
     */
    public static final String BUTTON_LABEL_SAVE = "Save";

    /**
     * BUTTON_LABEL : btn2
     */
    public static final String BUTTON_LABEL_SUBMIT = "Submit";

    /**
     * BUTTON_LABEL : btn3
     */
    public static final String BUTTON_LABEL_APPLY = "Apply";

    /**
     * BUTTON_LABEL : btn4
     */
    public static final String BUTTON_LABEL_APPROVE = "Approve";

    /**
     * BUTTON_LABEL : btn5
     */
    public static final String BUTTON_LABEL_REJECT = "Reject";

    /**
     * BUTTON_LABEL : btn6
     */
    public static final String BUTTON_LABEL_DOWNLOAD = "Download";

    /**
     * BUTTON_LABEL : btn7
     */
    public static final String BUTTON_LABEL_DELETE = "Delete";

    /**
     * BUTTON_LABEL : btn8
     */
    public static final String BUTTON_LABEL_CLEAR = "Clear";

    /**
     * BUTTON_LABEL : btn9
     */
    public static final String BUTTON_LABEL_RESET = "Reset";

    /**
     * BUTTON_LABEL : btn10
     */
    public static final String BUTTON_LABEL_RETURN = "Return";


    /**
     * Screen arguments(Service Memo Category Code)
     */
    public static final int ARGS_SVC_MEMO_CATG_CD = 0;

    /**
     * Screen arguments(Service Memo Type Code)
     */
    public static final int ARGS_SVC_MEMO_TP_CD = 1;

    /**
     * Screen arguments(Service Memo First Column Label Name)
     */
    public static final int ARGS_CLMN_HD_1 = 2;

    /**
     * Screen arguments(Service Memo First Column Label Code)
     */
    public static final int ARGS_CLMN_LB_1 = 3;

    /**
     * Screen arguments(Service Memo Second Column Label Name)
     */
    public static final int ARGS_CLMN_HD_2 = 4;

    /**
     * Screen arguments(Service Memo Second Column Label Code)
     */
    public static final int ARGS_CLMN_LB_2 = 5;

    /**
     * Screen arguments(Service Memo Third Column Label Name)
     */
    public static final int ARGS_CLMN_HD_3 = 6;

    /**
     * Screen arguments(Service Memo Third Column Label Code)
     */
    public static final int ARGS_CLMN_LB_3 = 7;

    /**
     * Screen arguments(Service Memo Fourth Column Label Name)
     */
    public static final int ARGS_CLMN_HD_4 = 8;

    /**
     * Screen arguments(Service Memo Fourth Column Label Code)
     */
    public static final int ARGS_CLMN_LB_4 = 9;

    /**
     * Screen arguments(Service Memo Fifth Column Label Name)
     */
    public static final int ARGS_CLMN_HD_5 = 10;

    /**
     * Screen arguments(Service Memo Fifth Column Label Code)
     */
    public static final int ARGS_CLMN_LB_5 = 11;

    /**
     * Screen arguments(Service Memo First Condition Name)
     */
    public static final int ARGS_COND_NM_1 = 12;

    /**
     * Screen arguments(Service Memo First Condition Code)
     */
    public static final int ARGS_COND_CD_1 = 13;

    /**
     * Screen arguments(Service Memo Second Condition Name)
     */
    public static final int ARGS_COND_NM_2 = 14;

    /**
     * Screen arguments(Service Memo Second Condition Code)
     */
    public static final int ARGS_COND_CD_2 = 15;

    /**
     * Screen arguments(Service Memo Third Condition Name)
     */
    public static final int ARGS_COND_NM_3 = 16;

    /**
     * Screen arguments(Service Memo Third Condition Code)
     */
    public static final int ARGS_COND_CD_3 = 17;

    /**
     * Screen arguments(Service Memo Fourth Condition Name)
     */
    public static final int ARGS_COND_NM_4 = 18;

    /**
     * Screen arguments(Service Memo Fourth Condition Code)
     */
    public static final int ARGS_COND_CD_4 = 19;

    /**
     * Screen arguments(Service Memo Fifth Condition Name)
     */
    public static final int ARGS_COND_NM_5 = 20;

    /**
     * Screen arguments(Service Memo Fifth Condition Code)
     */
    public static final int ARGS_COND_CD_5 = 21;

    /**
     * Screen arguments(Service Memo Read Only Flg)
     */
    public static final int ARGS_READ_ONLY_FLG = 22;

    /**
     * Table ID
     */
    public static final String TBL_ID = "B";

    //mod start 2016/02/19 CSA Defect#3689
    // START 2015/10/29 T.Tomita [N/A, MOD]
    /**
     * Table Rows Group
     */
    public static final int TBL_ROW_SPAN = 1;
    // END 2015/10/29 T.Tomita [N/A, MOD]
    //mod end 2016/02/19 CSA Defect#3689

    /**
     * MSG (NZZM0002I)
     */
    public static final String NZZM0002I = "NZZM0002I";

}
