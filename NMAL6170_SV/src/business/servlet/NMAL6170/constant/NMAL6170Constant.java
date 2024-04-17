/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6170.constant;

/**
 *<pre>
 * Relationships Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/05   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NMAL6170Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL6170";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /* ------ Parameter Number ------ */
    /** NMAL6170 Status Code: 01 */
    public static final String STATUS_CD_ACTIVE = "01";

    /** Button Filter Location */
    public static final String[] BTN_OPENWIN_ADDR_SRCH = {"OpenWin_AddressSearch", "OpenWin_AddressSearch" };

    /** LENGTH */
    public static final int LENGTH = 10;

    /** DS_ACCT_RELN_TP_CD */
    public static final int INPUT_DS_ACCT_RELN_TP_CD = 0;

    /** DS_ACCT_NUM  */
    public static final int INPUT_DS_ACCT_NUM = 1;

    /** DS_ACCT_NM */
    public static final int INPUT_DS_ACCT_NM = 2;

    /** XX_CHK_BOX_CB */
    public static final int INPUT_XX_CHK_BOX_CB = 3;

    /** XX_CHK_BOX_CS */
    public static final int INPUT_XX_CHK_BOX_CS = 4;

    /** XX_CHK_BOX_CR */
    public static final int INPUT_XX_CHK_BOX_CR = 5;

    /** EFF_FROM_DT From */
    public static final int INPUT_EFF_FROM_DT_F = 6;

    /** EFF_FROM_DT To */
    public static final int INPUT_EFF_FROM_DT_T = 7;

    /** EFF_THRU_DT From */
    public static final int INPUT_EFF_THRU_DT_F = 8;

    /** EFF_THRU_DT To */
    public static final int INPUT_EFF_THRU_DT_T = 9;

    /** ScrEventNm */
    public static final int INPUT_SCR_EVENT_NM = 10;

    /** [@] is invalid value */
    public static final String NMAM8121E = "NMAM8121E";

}
