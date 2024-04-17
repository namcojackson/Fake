/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */

package business.blap.NPAL1140.constant;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 * 07/10/2013   Hitachi         K.Kishimoto     Update          1233
 * 04/28/2014   Hitachi         T.Kawazu        Update          ITG#506831
 *</pre>
 */
public class NPAL1140Constant {

    /**
     * NPAM1173E:The field of [@] is not input.
     */
    public static final String MSG_ID_NPAM1173E = "NPAM1173E";

    /**
     * @ could not be obtained. Click, "Return".
     */
    public static final String MSG_ID_NPAM1304E = "NPAM1304E";

    /**
     * NPAM0085W:The search results exceeds the maximum display lines
     * number (@ results).
     */
    public static final String MSG_ID_NPAM0085W = "NPAM0085W";

    /**
     * NPAM1281W:The MDSE Code of PO Detail does not match the ACK of     * MDSE Code
     */
    public static final String MSG_ID_NPAM1281W = "NPAM1281W";

    /**
     * NPAM1303W: The MDSE Code of PO Detail does not match the ACK of
     * MDSE Code. If 'yes', click again.
     */
    public static final String MSG_ID_NPAM1303W = "NPAM1303W";

    /**
     * NPAM1313W: The relation between EDI PO# and PO# will be updated.
     */
    public static final String MSG_ID_NPAM1313W = "NPAM1313W";

    /**
     * NPAM1314W: The relation between EDI PO# and PO# will be updated. Do you still want to execute this? If 'yes', click again.
     */
    public static final String MSG_ID_NPAM1314W = "NPAM1314W";

    /**
     * NPAM1315W: The MDSE Code of PO Detail does not match with the MDSE Code of ACK. And the relation between EDI PO# and PO# will be updated. Do you still want to execute this? If 'yes', click again.
     */
    public static final String MSG_ID_NPAM1315W = "NPAM1315W";

    /**
     * NPAM0002E:No search results found.
     */
    public static final String MSG_ID_NPAM0002E = "NPAM0002E";

    /**
     * NPAM1285E:The corresponding PO has been canceled
     */
    public static final String MSG_ID_NPAM1285E = "NPAM1285E";

    /**
     * NPAM0225E:The chronological sequence on the dates for [@] is
     * wrong.
     */
    public static final String MSG_ID_NPAM0225E = "NPAM0225E";

    /**
     * NPAM1287E:The corresponding PO does not exist. 
     */
    public static final String MSG_ID_NPAM1287E = "NPAM1287E";

    /**
     * NPAM1297E:This data has been updated by another user.
     */
    public static final String MSG_ID_NPAM1297E = "NPAM1297E";

    /**
     * ZZZM9013E:Data update failure.(ReturnCode = [@])
     */
    public static final String MSG_ID_ZZZM9013E = "ZZZM9013E";

    /**
     * NPAM0005I:The process has been successfully completed.
     */
    public static final String MSG_ID_NPAM0005I = "NPAM0005I";

    /**
     * NPAM1242E:Enter a number other than 0 in [@].
     */
    public static final String MSG_ID_NPAM1242E = "NPAM1242E";
    /**
     * NLAM0023E:Have not been selected. Please select.
     */
    public static final String MSG_ID_NLAM0023E = "NLAM0023E";

    /**
     * NPAM1322E:[@] of [@], is different from [@] of [@].
     */
    public static final String NPAM1322E = "NPAM1322E";

    /**
     * NPAM1322E_PRM1 = "Vendor Code"
     */
    public static final String NPAM1322E_PRM1 = "Vendor Code";

    /**
     * NPAM1322E_PRM2 = "PO"
     */
    public static final String NPAM1322E_PRM2 = "PO";

    /**
     * NPAM1322E_PRM3 = "Vendor Code"
     */
    public static final String NPAM1322E_PRM3 = "Vendor Code";

    /**
     * NPAM1322E_PRM4 = "Interface Data"
     */
    public static final String NPAM1322E_PRM4 = "Interface Data";

    /**
     * PO_ORD_NUM_LENGTH
     */
    public static final int PO_ORD_NUM_LENGTH = 8;

    /**
     * DISP_PO_ORD_LINE_NUM_LENGTH
     */
    public static final int DISP_PO_ORD_LINE_NUM_LENGTH = 8;

    /**
     * LINE_NUM_LENGTH
     */
    public static final int LINE_NUM_LENGTH = 3;

    /**
     * padding character ZERO
     */
    public static final String PADDING_ZERO = "0";

    /**
     * HEADER_TAB
     */
    public static final String TAB_HEADER = "H";

    /**
     * TAB_DETAIL
     */
    public static final String TAB_DETAIL = "D";

    /**
     * display Item name: EDI Receive Date
     */
    public static final String DISP_ITEM_NAME_EDI_RCV_DATE = "EDI Rcv Date";

    /**
     * display Item name: PO#
     */
    public static final String DISP_ITEM_NAME_PO_ORD_NUM = "PO#";

    /**
     * display Item name: PO Line#
     */
    public static final String DISP_ITEM_NAME_PO_LINE_NUM = "PO Line#";

    /**
     * REGEX:NUMBER
     */
    public static final String REGEX_NUMBER = "^[0-9\\.0-9]+$";
    /**
     * Delimiter: -
     */
    public static final String DELIMITER_HYPHEN = "-";

    /**
     * String Asterisk
     */
    public static final String STR_ASTERISK = "*";

    /**
     * String End Time
     */
    public static final String STR_END_TIME = "2359599999";

    /**
     * SELECT_MAX_ROW_COUNT
     */
    public static final int SELECT_MAX_ROW_COUNT = 1000;

    /**
     * INTFC_ID_HEADER "INTFC_ID_"
     */
    public static final String INTFC_ID_HEADER = "INTFC_ID_";

    /** Search Type: 1(Search) */
    public static final String SEARCH_TYPE_SEARCH = "1";
    
    /** Search Type: 2(Re-Search) */
    public static final String SEARCH_TYPE_RESEARCH = "2";
}
