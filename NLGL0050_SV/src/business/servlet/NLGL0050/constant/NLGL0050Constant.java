package business.servlet.NLGL0050.constant;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public interface NLGL0050Constant {

    // **************** Buisiness constant value *****************

    /** Business ID */
    String BIZ_ID = "NLGL0050";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLGL0050T020";

    /** Screen ID */
    String SCRN_ID = "NLGL0050Scrn00";

    /** TAB ID List */
    String TAB_ID_LIST = "xxTabCarrierCode_List";

    /** TAB ID Edit */
    String TAB_ID_EDIT = "xxTabCarrierCode_Edit";

    /** Prev */
    String BTN_PREV = "Prev";

    /** Next */
    String BTN_NEXT = "Next";

    // **************** function code definition *****************

    /** Function Code Search */
    String FUNCTION_CODE_SEARCH = "20";

    /** Function Code Update */
    String FUNCTION_CODE_UPDATE = "40";

    // **************** Display character string for message(CARR CD)*****************

    /**
     * Display character string for message(carr cd)
     */
    String NAME_FOR_MESSAGE_CARR_CD = "CARR CD";

    /**
     * Display character string for message(wms trnsp tp cd)
     */
    String NAME_FOR_MESSAGE_WMS_TRNSP_TP_CD = "WMS TRNSP TP CD";

    /**
     * Display character string for message(wms carr cd)
     */
    String NAME_FOR_MESSAGE_WMS_CARR_CD = "WMS Carrier Code";

    /**
     * Display character string for message(wms desc txt)
     */
    String NAME_FOR_MESSAGE_WMS_DESC_TXT = "WMS DESC TXT";

    /**
     * Display character string for message(wms carr nm)
     */
    String NAME_FOR_MESSAGE_WMS_CARR_NM = "WMS CARR NM";

    /**
     * Display character string for message(carr svc txt)
     */
    String NAME_FOR_MESSAGE_CARR_SVS_TXT = "CARR SVC TXT";

    /**
     * Display character string for message(carr scac cd)
     */
    String NAME_FOR_MESSAGE_CARR_SCAC_CD = "CARR SCAC CD";

    /**
     * Display character string for message(fuel upchg tp cd)
     */
    String NAME_FOR_MESSAGE_FUEL_UPCHG_TP_CD = "FUEL UPCHG TP CD";

    /**
     * Display character string for message(fuel upchg amt)
     */
    String NAME_FOR_MESSAGE_FUEL_UPCHG_AMT = "Fuel Upcharge";

    /**
     * Display character string for message(add upchg tp cd)
     */
    String NAME_FOR_MESSAGE_ADD_UPCHG_TP_CD = "ADD UPCHG TP CD";

    /**
     * Display character string for message(add upchg amt)
     */
    String NAME_FOR_MESSAGE_ADD_UPCHG_AMT = "Additional Upcharge";

    // **************** common buttonName definition *****************

    /**
     * Common button 1
     */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Search button
     */
    String[] BTN_SEARCH = {"OnClick_Search", "Search" };

    // **************** Pulldown List *****************
    /**
     * Max setting num of pulldownlist
     */
    int PULLDOWN_LIST_MAX = 99;

    // **************** Error Message ID *****************
    /**
     * This data has been updated by another user.
     */
    String ZZM9004E = "ZZM9004E";

    /**
     * Key Duplication Error. Table Name:  [@], Key Field Name:  [@], Key Value:  [@]
     */
    String NLGM0050E = "NLGM0050E";

    /**
     * Multiple Details cannot be processed at the same time.
     */
    String NLGM0035E = "NLGM0035E";

    /**
     * It has not been selected. Please select.
     */
    String NLGM0036E = "NLGM0036E";

    // **************** Field Value *****************
    /**
     * Field Value : *Blank
     */
    String FLD_VALUE_BLANK = "";

    /**
     * Field Value : *Zero
     */
    int FLD_VALUE_ZERO = 0;

    /**
     * field name check box(List)
     */
    String FIELD_NAME_XXCHKBOX_A1 = "xxChkBox_A1";
}
