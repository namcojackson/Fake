/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610.constant;

/**
 *<pre>
 * NWAL1610Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/16   Fujitsu         M.Yamada        Update          S21 CSA QC#6148
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2019/12/23   Fujitsu         S.Kosaka        Update          QC#54999
 *</pre>
 */
public class NWAL1610Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1610";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1610Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** CLOSE */
    public static final String BTN_CMN_CLOSE = "CMN_Close";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** Input Parameter Global Company Code is mandatory field. */
    public static final String NMZM0009E = "NMZM0009E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Valiable
    // --------------------------------
    /** Parameter Mode : Config */
    public static final String CONFIG_MODE = "01";

    /** Parameter Mode : Line */
    public static final String LINE_MODE = "02";

    /** Parameter Mode : RMA */
    public static final String RMA_MODE = "03";

    /** OpenWin_FloorPriceList */
    public static final String OPENWIN_FLR_PRC_LIST = "OpenWin_FloorPriceList";

    /** OpenWin_PriceList */
    public static final String OPENWIN_PRC_LIST = "OpenWin_PriceList";

    /** OpenWin_ShipTo */
    public static final String OPENWIN_SHIP_TO = "OpenWin_ShipTo";

    /** OpenWin_BillTo */
    public static final String OPENWIN_BILL_TO = "OpenWin_BillTo";

    /** OpenWin_SubstituteItem */
    public static final String OPENWIN_SBST_ITEM = "OpenWin_SubstituteItem";

    /** OpenWin_Warehouse */
    public static final String OPENWIN_WH = "OpenWin_Warehouse";

    /** OpenWin_Warehouse 2016/08/30 S21_NA#9806 Add */
    public static final String OPENWIN_CSMP_NUM = "OpenWin_CSMPNumber";

    // --------------------------------
    // Warehouse Pop Up Param
    // --------------------------------
    /** Screen Title */
    public static final String WH_SCRN_TITLE = "Ship From Warehouse Search";

    /** Screen Title */
    public static final String WH_CD_SQL_NM = "RTL_WH_CD";

    /** Screen Title */
    public static final String SWH_CD_SQL_NM = "RTL_SWH_CD";

    /** Screen Title */
    public static final String WH_NM_SQL_NM = "RTL_WH_NM";

    /** Screen Title */
    public static final String SWH_NM_SQL_NM = "RTL_SWH_NM";

    /** Screen Title */
    public static final String WH_CD_LB = "Warehouse Code";

    /** Screen Title */
    public static final String WH_NM_LB = "Warehouse Name";

    /** Screen Title */
    public static final String SWH_CD_LB = "Sub Warehouse Code";

    /** Screen Title */
    public static final String SWH_NM_LB = "Sub Warehouse Name";

    // --------------------------------
    // Substitute Item Pop Up Param
    // --------------------------------
    /** Screen Title */
    public static final String SI_SCRN_TITLE = "Sup/Relation Item Search";

    /** Screen Title */
    public static final String MDSE_CD_SQL_NM = "MDSE_CD";

    /** Screen Title */
    // Mod Start 2016/09/14 QC#11614
    // public static final String MDSE_NM_SQL_NM = "MDSE_NM";
    public static final String MDSE_NM_SQL_NM = "MDSE_DESC_SHORT_TXT";

    // Mod End 2016/09/14 QC#11614

    /** Screen Title */
    public static final String SI_CD_LB = "Subst Item Code";

    /** Screen Title */
    public static final String SI_NM_LB = "Subst Item Name";

    // --------------------------------
    // Account Search Pop Up Param
    // --------------------------------
    /** NWAL6760 Calling Mode: Status Active */
    public static final String NMAL6760_STATUS_CD_ACTIVE = "01";

    /** NWAL6760 Calling Mode: Bill To */
    public static final String NMAL6760_DISP_HRCH_ACCT_CD_BILL = "02";

    /** NWAL6760 Calling Mode: Ship To */
    public static final String NMAL6760_DISP_HRCH_ACCT_CD_SHIP = "03";

    // 2016/08/30 S21_NA#9806 Add Start
    /** No Selected Detail Line */
    public static final int NO_SLCT_DTL = -1;

    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 50 */
    public static final int IDX_50 = 50;

    /** Percent */
    public static final String PERCENT = "%";
    // 2016/08/30 S21_NA#9806 Add And
    // 2019/12/23 QC#54999 Add Start
    /** Modify Authority (Logistics) */
    public static final String MOD_LOGISTICS = "NWAL1500T080";

    /** OK Button Event Name */
    public static final String BTN_OK_EVENT_NM = "CMN_OK";
    // 2019/12/23 QC#54999 Add And
}
