package business.servlet.NMAL7030.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   SRA             E.Inada         Create          QC#2174
 *</pre>
 */
public interface NMAL7030Constant {

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /* ------ Parameter Number ------ */
    int INPUT_AFT_DECL_PNT_DIGIT_NUM = 0;

    int INPUT_PRC_QLFY_TP_CD_LIST = 1;

    int INPUT_PRC_QLFY_TP_DESC_TXT_LIST = 2;

    int INPUT_PRC_QLFY_TP_CD = 3;

    int INPUT_PRC_QLFY_VAL_TXT = 4;

    int INPUT_PROD_CTRL_NM = 5;

    int INPUT_PRC_LIST_EQUIP_PK = 6;

    int INPUT_LIST = 7;

    int MAX_INPUT_PARAM_NUM = INPUT_LIST;

    /* ------ Error Message ------ */
    /** Details cannot be added because the number will exceed [@]. */
    String NMAM0050E = "NMAM0050E";

    /** Please select item(s). */
    String NMAM8054E = "NMAM8054E";

    /** @ is duplicated. */
    String NMAM0072E = "NMAM0072E";

    /* ------ Other ------- */
    String SCRN_ID_00 = "NMAL7030Scrn00";

    String STS_ACTIVE = "Active";

    String STS_DELETED = "Deleted";

    String ATTB_CHECK_BOX = "xxChkBox_A";

    int DEF_DIGIT_NUM = 2;
}
