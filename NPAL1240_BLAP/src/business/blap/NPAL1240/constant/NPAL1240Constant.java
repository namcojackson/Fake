/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1240.constant;

/**
 * Business ID : NPAL1240 Qualifier Setup
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240Constant {

    // ------------------- Registration Status -------------------

    /**
     * Registration Status. (READY_FOR_ORDER_TAKING : P20)
     */
    public static final String RGTN_STS_READY_FOR_ORDER_TAKING = "P20";

    // ------------------- ASL Qualifier Type -------------------

    /**
     * ASL Qualifier Type. (CUSTOMER_SPECIFIC : 01)
     */
    public static final String ASL_QLFY_TP_CUSTOMER_SPECIFIC = "01";

    /**
     * ASL Qualifier Type. (BIG_DEAL_SPECIFIC : 02)
     */
    public static final String ASL_QLFY_TP_BIG_DEAL_SPECIFIC = "02";

    // ------------------- Event Name -------------------
    /**
     * The event name of "INIT".
     */
    public static final String EVENT_NM_INIT = "NPAL1240_INIT";

    /**
     * The event name of "OnClick_InsertRow".
     */
    public static final String EVENT_NM_ON_CLICK_INSERT_ROW = "NPAL1240Scrn00_OnClick_InsertRow";

    /**
     * The event name of "OnClick_DeleteRow".
     */
    public static final String EVENT_NM_ON_CLICK_DELETE_ROW = "NPAL1240Scrn00_OnClick_DeleteRow";

    /**
     * The event name of "OnClick_RefNum".
     */
    public static final String EVENT_NM_NPAL1240_NWAL1130 = "NPAL1240_NWAL1130";

    /**
     * The event name of "CMN_Close".
     */
    public static final String EVENT_NM_CMN_CLOSE = "NPAL1240Scrn00_CMN_Close";

    // ------------------- Item Name -------------------
    /**
     * The item name of check box.
     */
    public static final String CHECK_BOX_ITEM_NM = "xxChkBox_A";

    // ------------------- Message Code -------------------
    /**
     * Details cannot be added because the number will exceed [@].
     */
    public static final String MESSAGE_CD_NPAM1191E = "NPAM1191E";

    /**
     * The process has been successfully completed.
     */
    public static final String MESSAGE_CD_NZZM0002I = "NZZM0002I";

    /**
     * Please check at least 1 checkbox.
     */
    public static final String MESSAGE_CD_NZZM0011E = "NZZM0011E";

    /**
     * The format of [@] is incorrect.
     */
    public static final String MESSAGE_CD_NPAM1193E = "NPAM1193E";

    /**
     * [@] does not exist in Master.
     */
    public static final String MESSAGE_CD_NPAM0076E = "NPAM0076E";

    // ------------------- Message Parameter -------------------
    /**
     * The message parameter. : Input Parameters.
     */
    public static final String MESSAGE_PARAM_INPUT_PARAMS = "Input Parameters";

    /**
     * The message parameter. : ASL Qualifier Reference#.
     */
    public static final String MESSAGE_PARAM_ASL_QLFY_REF_NUM = "Qualifier Reference#";
}
