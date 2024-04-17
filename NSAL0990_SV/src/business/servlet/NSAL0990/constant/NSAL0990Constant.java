/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         K.Kasai         Create          N/A
 * 2016/03/09   Hitachi         A.Kohinata      Update          QC#5196
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5354
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC#5790
 * 2016/10/13   Hitachi         A.Kohinata      Update          QC#9885
 * 2017/09/15   Hitachi         U.Kim           Update          QC#18726
 * 2018/07/12   Hitachi         K.Kim           Update          QC#27009
 * 2018/07/30   Hitachi         T.Tomita        Update          QC#14307
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 *</pre>
 */
public class NSAL0990Constant {

    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0990";

    /**
     * Screen Id
     */
    public static final String SCR_ID_00 = "NSAL0990Scrn00";

    /**
     * Screen Mode: SplyOrdMode
     */
    public static final String MODE_1 = "SplyOrdMode";

    /**
     * Screen Mode: SplyOrdEditMode
     */
    public static final String MODE_2 = "SplyOrdEditMode";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Button name attribute [BillTo]
     */
    public static final String BTN_BILL_TO = "Search_BillTo";

    /**
     * Button name attribute [Search_ShipTo]
     */
    public static final String BTN_SHIP_TO = "Search_ShipTo";

    /**
     * Button name attribute [MoveWin_CreditCard]
     */
    public static final String BTN_CREDIT_CARD = "MoveWin_CreditCard";

    /**
     * Button name attribute [Search_MdseNm]
     */
    public static final String BTN_MDSE_NM = "Search_MdseNm";

    /**
     * Button name attribute [DeleteLine]
     */
    public static final String BTN_DELETE_LINE = "DeleteLine";

    /**
     * Button name attribute [MoveWin_MemoEntry]
     */
    public static final String BTN_MEMO = "MoveWin_MemoEntry";

    /**
     * Button name attribute [OpenWin_SupplyOrderSer]
     */
    public static final String BTN_SUP_ORD_SER = "OpenWin_SupplyOrderSer";

    /**
     * Button name attribute [Calcu]
     */
    public static final String BTN_CALCU = "Calcu";

    /**
     * Button name attribute [Disp_SupplyOrderEdit]
     */
    public static final String BTN_EDIT = "Disp_SupplyOrderEdit";

    /**
     * Button name attribute [DeleteQuote]
     */
    public static final String BTN_DELETE_QUOTE = "DeleteQuote";

    /**
     * Button name attribute [Add]
     */
    public static final String BTN_ADD = "Add_Mdse";

    // add start 2016/10/13 CSA Defect#9885
    /**
     * Button name attribute [FilterByModel]
     */
    public static final String BTN_GO = "FilterByModel";
    // add end 2016/10/13 CSA Defect#9885

    // START 2017/09/15 U.Kim [QC#18726, ADD]
    /**
     * Button name attribute [Inventory]
     */
    public static final String BTN_INVENTORY = "OpenWin_InventoryStockOverview";
    // END 2017/09/15 U.Kim [QC#18726, ADD]

    // Add Start 2018/07/30 QC#14307
    /**
     * Button name attribute [Special Instruction]
     */
    public static final String BTN_SPCL_INSTRUCTION = "OpenWin_SpecialInstruction";
    // Add End 2018/07/30 QC#14307

    // add start 2019/01/21 QC#27304
    /** Button name attribute [Editing List] */
    public static final String BTN_EDIT_LIST = "OpenWin_EditingList";
    // add end 2019/01/21 QC#27304

    /**
     * Function Id: Inquiry
     */
    public static final String FUNC_ID_INQ = "NSAL0990T010";

    /**
     * Function Id: Update
     */
    public static final String FUNC_ID_UPD = "NSAL0990T020";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Parameter "@" is not set. */
    public static final String NSAM0131E = "NSAM0131E";

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * Do you want to execute [Return]? Please click once to execute.
     */
    public static final String ZZZM9009W = "ZZZM9009W";

    /** Display Hierarchy Accounts Code: 02 */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    /** Parameter Memo Entry: 23 */
    public static final int PRM_NSBL0160 = 23;

    // Mod Start 2018/07/30 QC#14307
    /** Parameter Account Search: 42 */
    public static final int PRM_NMAL6760 = 42;
    // Mod End 2018/07/30 QC#14307

    // START 2018/07/12 K.Kim [QC#27009, MOD]
    /** Parameter Supply Order Serial# Search: 7 */
    public static final int PRM_NSAL1020 = 7;
    // END 2018/07/12 K.Kim [QC#27009, MOD]

    /** OpenWin_FreightTerms */
    public static final String OPENWIN_FREIGHT_TERMS = "OpenWin_FreightTerms";

    /** OnBlur_DeriveFromFreightTerms */
    public static final String ONBLUR_DERIVEFROM_FREIGHT_TERMS = "OnBlur_DeriveFromFreightTerms";

    /** OpenWin_CarrierServiceLevel */
    public static final String OPENWIN_CARRIER_SERVICE_LEVEL = "OpenWin_CarrierServiceLevel";

    /** OnBlur_DeriveFromCarrierServiceLevel */
    public static final String ONBLUR_DERIVEFROM_CARRIER_SERVICE_LEVEL = "OnBlur_DeriveFromCarrierServiceLevel";

    /** OpenWin_Merchandise */
    public static final String OPENWIN_MERCHANDISE = "OpenWin_Merchandise";

    // START 2017/09/15 U.Kim [QC#18726, ADD]
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

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** RTL_WH_CD : DS */
    public static final String RTL_WH_CD_DS = "DS";

    // END 2017/09/15 U.Kim [QC#18726, ADD]
    /** Percent */
    public static final String PERCENT = "%"; // S21_NA#8393 Add

}
