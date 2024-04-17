/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2810.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NMAL2810Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2810Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2810";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2810Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** Function Button 8 Button Name */
    public static final String BTN_CMN_CLR_BTN_NM = "btn8";

    /** Function Button 8 Event Name */
    public static final String BTN_CMN_CLR_EVENT_NM = "CMN_Clear";

    /** Function Button 8 Label */
    public static final String BTN_CMN_CLR_LABEL = "Clear";

    /** Function Button 10 Button Name */
    public static final String BTN_CMN_CLS_BTN_NM = "btn10";

    /** Function Button 10 Event Name */
    public static final String BTN_CMN_CLS_EVENT_NM = "CMN_Close";

    /** Function Button 10 Label */
    public static final String BTN_CMN_CLS_LABEL = "Close";

    // --------------------------------
    // Business button
    // --------------------------------
    /** Button Prospect Select All */
    public static final String BTN_PROS_ALL = "ProspectSelectAll";

    /** Button Merge To Select All */
    public static final String BTN_MRG_ALL = "MergeToSelectAll";

    /** Button Address Validation */
    public static final String BTN_ADR_VAL = "AddressValidation";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** Post, City, and State relation is not found. */
    public static final String NMAM8499E = "NMAM8499E";

    /** [@] cannot be set because of [@]. */
    public static final String NMAM0179E = "NMAM0179E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Other
    // --------------------------------
    /** Mode : Reference(For Merge to Location ID) */
    public static final String MODE_REF_LOC = "01";

    /** Mode : Reference(For Merge to Prospect#(For SFDC# / OASIS)) */
    public static final String MODE_REF_PROS = "02";

    /** Mode : Set(For Merge to Location ID) */
    public static final String MODE_SET_LOC = "03";

    /** Mode : Set(For Merge to Prospect#(For SFDC# / OASIS)) */
    public static final String MODE_SET_PROS = "04";

    /** Radio : Prospect */
    public static final BigDecimal RADIO_PROS = BigDecimal.valueOf(0);

    /** Radio : Merge to */
    public static final BigDecimal RADIO_MRG_TO = BigDecimal.valueOf(1);

    /** tableName : A */
    public static final String TABLE_NAME_A = "table_A";

    /** Background Color（Hightlight/Suggested） **/
    public static final String BG_COLOR_HIGHTLIGHT = "pHightlightBGcolor";

    /** Background Color（Error） **/
    public static final String BG_COLOR_ERROR = "pErrorBGcolor";

    /** NMZC003001 : Return Cd(Error) */
    public static final String NMZC003001_RTRN_E = "E";

    /** NMZC003001 : Return Cd(Suggested) */
    public static final String NMZC003001_RTRN_S = "S";

    /** Input Parameters : length */
    public static final int IN_PARAM_LEN = 63;
}
