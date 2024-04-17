/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0040.constant;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 01/17/2017     CITS            T.Kikuhara        Update             QC#16256
 *</pre>
 */
public interface NLGL0040Constant {

    // **************** Buisiness constant value *****************

    /** Business ID */
    String BIZ_ID = "NLGL0040";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLGL0040T020";

    /** Screen ID */
    String SCRN_ID = "NLGL0040Scrn00";

    /** TAB ID List */
    String TAB_ID_LIST = "xxTabShipVia_List";

    /** TAB ID Edit */
    String TAB_ID_EDIT = "xxTabShipVia_Edit";

    /** Prev */
    String BTN_PREV = "Prev";

    /** Next */
    String BTN_NEXT = "Next";

    // **************** function code definition *****************
    /** Function Code Search */
    String FUNCTION_CODE_SEARCH = "20";

    /** Function Code Update */
    String FUNCTION_CODE_UPDATE = "40";

    // **************** Display character string *****************
    /**
     * Display character string for message(WH)
     */
    String NAME_FOR_MESSAGE_WH = "WH";

    /**
     * Display character string for message(shipViaCd)
     */
    String NAME_FOR_MESSAGE_SHIPVIACD = "Ship Via Code";

    /**
     * Display character string for message(wmsDescTxt)
     */
    String NAME_FOR_MESSAGE_WMSDESCTXT = "Description";

    /**
     * Display character string for message(mdBreakTpCd)
     */
    String NAME_FOR_MESSAGE_MDBREAKTPCD = "Mode Break Type";

    /**
     * Display character string for message(rteGuideNum)
     */
    String NAME_FOR_MESSAGE_RTEGUIDENUM = "Rte Guide#";

    /**
     * Display character string for message(pclCarrCd)
     */
    String NAME_FOR_MESSAGE_PCLCARRCD = "PCL Carrier Code";

    /**
     * Display character string for message(pclMaxCapNum)
     */
    String NAME_FOR_MESSAGE_PCLMAXCAPNUM = "PCL Max Capacity";

    /**
     * Display character string for message(pclPrtyCd)
     */
    String NAME_FOR_MESSAGE_PCLPRTYCD = "PCL Priority Code";

    /**
     * Display character string for message(ltlCarrCd)
     */
    String NAME_FOR_MESSAGE_LTLCARRCD = "LTL Carrier";

    /**
     * Display character string for message(ltlMaxCapNum)
     */
    String NAME_FOR_MESSAGE_LTLMAXCAPNUM = "LTL Max Capacity";

    /**
     * Display character string for message(ltlPrtyCd)
     */
    String NAME_FOR_MESSAGE_LTLPRTYCD = "LTL Priority Code";

    /**
     * Display character string for message(tlCarrCd)
     */
    String NAME_FOR_MESSAGE_TLCARRCD = "FTL Carrier";

    /**
     * Display character string for message(tlMaxCapNum)
     */
    String NAME_FOR_MESSAGE_TLMAXCAPNUM = "FTL Max Capacity";

    /**
     * Display character string for message(tlPrtyCd)
     */
    String NAME_FOR_MESSAGE_TLPRTYCD = "FTL Priority Code";

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
     * button Search
     */
    String[] BTN_SEARCH = {"OnClick_Search", "Search" };

    // **************** Pulldown List *****************
    /**
     * Maximum row count with Pull down list
     */
    int PULLDOWN_LIST_MAX = 99;

    /**
     * DB column For create Pulldown List : WH
     */
    String[] WH_DBCOLUMN = {"WH_CD", "LOC_NM" };

    // **************** Error Message ID *****************
    /**
     * This data has been updated by another user.
     */
    String ZZM9004E = "ZZM9004E";

    /**
     * Key Duplication Error. Table Name: [@], Key Field Name: [@],
     * Key Value: [@]
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
    String FIELD_NAME_XXCHKBOX_D1 = "xxChkBox_D1";
}
