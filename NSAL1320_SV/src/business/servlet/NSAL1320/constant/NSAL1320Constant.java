/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320.constant;

import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NSAL1320Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/15   Hitachi         Y.Takeno        Update          QC#20378-2
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1320Constant {

    /** Business ID */
    public static final String BIZ_ID = "NSAL1320";

    /** Order Entry Business ID */
    public static final String BIZ_ID_ORD_ENTRY = "NWAL1500";

    /** Import Form Business ID */
    public static final String BIZ_ID_IMPT_FORM = "NWAL2200";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NSAL1320Scrn00";

    // - ROLE -
    /** ROLE_UPD = "NSAL1320T010" */
    public static final String ROLE_UPD = "NSAL1320T010";

    /** ROLE_REF = "NSAL1320T020" */
    public static final String ROLE_REF = "NSAL1320T020";

    /** */
    public static enum XX_PAGE {
        PAGE_SHELL("S", "Order Number") //
        , PAGE_IMPT("I", "Source Reference#");

        private final String code;

        private final String name;

        private XX_PAGE(final String code, final String name) {
            this.code = code;
            this.name = name;
        }

        /** */
        public String getCode() {
            return code;
        }

        /** */
        public String getName() {
            return name;
        }
    }

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    /** SearchMaintenance */
    public static final String[] BTN_BIZ_SEARCH = {"SearchMaintenance", "SearchMaintenance", "Search" };

    /** AddMaintenanceShell(OpenWin_ShellConfigSelection) */
    public static final String[] BTN_BIZ_ADD_MNT_SHELL = {"OpenWin_ShellConfigSelection", "OpenWin_ShellConfigSelection", "+" };

    /** DelMaintenanceShell */
    public static final String[] BTN_BIZ_DEL_MNT_SHELL = {"DelMaintenanceShell", "DelMaintenanceShell", "-" };

    /** SerchShellItem */
    public static final String[] BTN_BIZ_SEARCH_SHELL_ITEM = {"SearchShellItem", "SearchShellItem", ">>" };

    /** OpenWin_ServicePricingDetailPopup */
    // 2018/05/07 QC#22482 Mod Start
//    public static final String[] BTN_BIZ_OPEN_WIN_SVC_PRC = {"OpenWin_ServicePricing", "OpenWin_ServicePricing", "$" };
    public static final String[] BTN_BIZ_OPEN_WIN_SVC_PRC = {"OpenWin_SvcPricing", "OpenWin_SvcPricing", "$" };
    // 2018/05/07 QC#22482 Mod End

    /** OpenWin_MassApply */
    public static final String[] BTN_BIZ_OPEN_WIN_MASS_APPLY = {"OpenWin_MassApply", "OpenWin_MassApply", "Mass Apply" };

    /** <pre>AddMaintenanceShellDetail(OpenWin_ShellConfigSelectionForDetail)</pre> */
    public static final String[] BTN_BIZ_ADD_MNT_SHELL_DTL = {"OpenWin_ShellConfigSelectionForDetail", "OpenWin_ShellConfigSelectionForDetail", "+" };

    /** DelMaintenanceShellDetail */
    public static final String[] BTN_BIZ_DEL_MNT_SHELL_DTL = {"DelDetail", "DelDetail", "-" };

    /** ResetPriceInfo */
    public static final String[] BTN_BIZ_RESET_PRC = {"ResetPriceInfo", "ResetPriceInfo", "Reset" };

    /** BTN_STS_ACTIVE = 1 */
    public static final int BTN_STS_ACTIVE = 1;

    /** BTN_STS_INACTIVE = 0 */
    public static final int BTN_STS_INACTIVE = 0;

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field has too many digits entered. */
    public static final String ZZM9001E = "ZZM9001E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** NSAM0639E : Require at least one item in the details. */
    public static final String NSAM0639E = "NSAM0639E";

    /** <pre>NSAM0629E : The number of detail lines has reached the maximum.  Cannot add anymore.</pre> */
    public static final String NSAM0629E = "NSAM0629E";

    /** <pre>NSAM0019E : Please check at least 1 checkbox.</pre> */
    public static final String NSAM0019E = "NSAM0019E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** <pre>Contract Indicator change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String NSAM0637I = "NSAM0637I";

    /** <pre>Please input @ when you input the value to @.</pre> */
    public static final String NSAM0638E = "NSAM0638E";

// START 2017/08/15 [QC#20378, ADD]
    /** <pre>Contract Type change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String NSAM0692I = "NSAM0692I";

    /** <pre>Plan Type change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String NSAM0693I = "NSAM0693I";

    /** <pre>Customer change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String NSAM0694I = "NSAM0694I";
// END   2017/08/15 [QC#20378, ADD]

    // 2018/05/07 QC#22482 Add Start
    /** <pre>In the case of Manual Override is 'Yes', @ is required.</pre> */
    public static final String NSAM0654E = "NSAM0654E";
    // 2018/05/07 QC#22482 Add End

    /** <pre>Contract Indicator change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String ALERT_MSG = S21MessageFunc.clspGetMessage(NSAM0637I).substring(NSAM0637I.length() + 1);

// START 2017/08/15 [QC#20378, ADD]
    /** <pre>Contract Type change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String ALERT_MSG_02 = S21MessageFunc.clspGetMessage(NSAM0692I).substring(NSAM0692I.length() + 1);

    /** <pre>Plan Type change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String ALERT_MSG_03 = S21MessageFunc.clspGetMessage(NSAM0693I).substring(NSAM0693I.length() + 1);

    /** <pre>Customer change requires removal of Pricing Information. Are you sure to proceed ?</pre> */
    public static final String ALERT_MSG_04 = S21MessageFunc.clspGetMessage(NSAM0694I).substring(NSAM0694I.length() + 1);
// END   2017/08/15 [QC#20378, ADD]

    //-------------
    /** parameter count of Common BIG PopUp */
    public static final int PRM_CNT = 7;

    /** parameter count of Mass Apply PopUp */
    // 2018/04/16 QC#20162 Mod Start
//    public static final int PRM_CNT_MASS_APPLY = 3;
    public static final int PRM_CNT_MASS_APPLY = 4;
    // 2018/04/16 QC#20162 Mod End

    /** line separator for SQL */
    public static final String NEWLINE = System.getProperty("line.separator");

    /** IX_POP_PRM_FIRST_LINE_ADDR = 7 */
    public static final int IX_POP_PRM_FIRST_LINE_ADDR = 7;

    /** IX_POP_PRM_SCD_LINE_ADDR = 8 */
    public static final int IX_POP_PRM_SCD_LINE_ADDR = 8;

    /** IX_POP_PRM_THIRD_LINE_ADDR = 9 */
    public static final int IX_POP_PRM_THIRD_LINE_ADDR = 9;

    /** IX_POP_PRM_FRTH_LINE_ADDR = 10 */
    public static final int IX_POP_PRM_FRTH_LINE_ADDR = 10;

    /** IX_POP_PRM_CTY_ADDR = 11 */
    public static final int IX_POP_PRM_CTY_ADDR = 11;

    /** IX_POP_PRM_POST_CD = 15 */
    public static final int IX_POP_PRM_POST_CD = 15;

    /** IX_POP_PRM_ST_CD = 14 */
    public static final int IX_POP_PRM_ST_CD = 14;

    /** USAGE_CD_BILL_TO_ONLY = "52" */
    public static final String USAGE_CD_BILL_TO_ONLY = "52";

    /** Status Code for Active in Account Search Popup */
    public static final String STATUS_CD_ACTIVE = "01";

    /** Display for Active in Account Search Popup */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

}
