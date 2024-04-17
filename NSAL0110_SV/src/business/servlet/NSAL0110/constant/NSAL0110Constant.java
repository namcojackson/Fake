/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0110.constant;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi         Create          N/A
 * 2015/11/02   Hitachi         K.Kasai            Update          N/A
 * 2016/06/27   Hitachi         O.Okuma            Update          QC#10886
 *</pre>
 */
public class NSAL0110Constant {

    /** BUSSINESS_ID (NSAL0110) */
    public static final String BUSINESS_APPLICATION_ID = "NSAL0110";

    /** Screen ID (NSAL0110Scrn00) */
    public static final String SCREEN_ID = "NSAL0110Scrn00";

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
     * Screen arguments(Contract #)
     */
    public static final int ARGS_CONTRACT_NUM = 0;

    /**
     * Screen arguments(Contract Status Code)
     */
    public static final int ARGS_CONTRACT_STATUS = 1;

    /**
     * Screen arguments(Contract Name)
     */
    public static final int ARGS_CONTRACT_NAME = 2;

    /**
     * Screen arguments(Contract Category Code)
     */
    public static final int ARGS_CONTRACT_CATEGORY = 3;

    /**
     * Screen arguments(Account Code)
     */
    public static final int ARGS_ACCOUNT_CODE = 4;

    /**
     * Screen arguments(Contract Detail Type Code)
     */
    public static final int ARGS_CONTRACT_DTL_TYPE = 5;

    /**
     * Screen arguments(Contract Detail Status Code)
     */
    public static final int ARGS_CONTRACT_DTL_STATUS = 6;

    /**
     * Screen arguments(Serial #)
     */
    public static final int ARGS_SERIAL_NUM = 7;

    /**
     * Screen arguments(Model Name)
     */
    public static final int ARGS_MODEL_NAME = 8;

    /**
     * Screen arguments(Desplay Mode)
     */
    public static final int ARGS_DISPLAY_MODE = 9;

    /**
     * Screen arguments(DS Contract PK)
     */
    public static final int ARGS_DS_CONTRACT_PK = 10;

    /**
     * Screen arguments(DS Contract Detail PK)
     */
    public static final int ARGS_DS_CONTRACT_DETAIL_PK = 11;

    /**
     * JSP Search Result Left Table Id
     */
    public static final String TBL_ID_LEFT = "L";

    /**
     * JSP Search Result Right Table Id
     */
    public static final String TBL_ID_RIGHT = "R";

    /**
     * Focus Group 01(xxScrItem40Txt_RS)
     */
    public static final String FOCUS_GROUP_01 = "focusGrp_01#";

    /**
     * Focus Group 02(dsContrNm_RS)
     */
    public static final String FOCUS_GROUP_02 = "focusGrp_02#";

    /**
     * Focus Group 03(dsAcctNm)
     */
    public static final String FOCUS_GROUP_03 = "focusGrp_03#";

    /**
     * Focus Group 04(mdlNm_RS)
     */
    public static final String FOCUS_GROUP_04 = "focusGrp_04#";


    /**
     * Display Mode (Summary)
     */
    public static final String DISPLAY_MODE_SUMMARY = "Summary";

    /**
     * Display Mode (Detail)
     */
    public static final String DISPLAY_MODE_DETAIL = "Detail";
}

