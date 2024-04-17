/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1150.constant;

/**
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/05/30   Hitachi         T.Kawazu        Update          1233
 * 2013/06/13   Hitachi         T.Tomita        Update          1233
 * 04/28/2014   Hitachi         T.Kawazu        Update          ITG#506831
 *</pre>
 */

public class NPAL1150Constant {
    /**
     * Message Process Successed.
     */
    public static final String NPAM0005I = "NPAM0005I";

    /**
     * Message There are too many search results, there is data that
     * cannot be displayed.
     */
    public static final String ZZZM9002W = "ZZZM9002W";

    /**
     * Message No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Message The details of the process target have not been
     * selected..
     */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * Message This data has been updated by another user.
     */
    public static final String NPAM1297E = "NPAM1297E";

    /**
     * Message Data update failure.
     */
    public static final String ZZZM9013E = "ZZZM9013E";

    /**
     * Message The chronological sequence on the dates for [@] is
     * wrong.
     */
    public static final String NPAM0225E = "NPAM0225E";

    /**
     * Message [@] is invalid value.
     */
    public static final String ZZZM9026E = "ZZZM9026E";

    /**
     * Message Enter a number other than 0 in [@].
     */
    public static final String NPAM1242E = "NPAM1242E";

    /**
     * Message The field of [@] is not input.
     */
    public static final String NPAM1173E = "NPAM1173E";

    /**
     * Message The MDSE Code of PO Detail does not match with the MDSE
     * Code of ASN. If 'yes', click again.
     */
    public static final String NPAM1308W = "NPAM1308W";

    /**
     * Message The PO has been canceled.
     */
    public static final String NPAM1285E = "NPAM1285E";

    /**
     * Message The MDSE Code of PO Detail does not match with the MDSE
     * Code of ASN.
     */
    public static final String NPAM1309W = "NPAM1309W";

    /**
     * Message The PO Qty of PO Detail does not match with the Order
     * Qty of ASN.
     */
    public static final String NPAM1310W = "NPAM1310W";

    /**
     * Message The PO does not exist.
     */
    public static final String NPAM1287E = "NPAM1287E";

    /**
     * Message It failed to update the data.
     */
    public static final String NPAM1299E = "NPAM1299E";

    /**
     * Message [@] of [@], is different from [@] of [@].
     */
    public static final String NPAM1322E = "NPAM1322E";

    /**
     * NPAM1322E_PRM1 = "Vender Code"
     */
    public static final String NPAM1322E_PRM1 = "Vender Code";

    /**
     * NPAM1322E_PRM2 = "PO"
     */
    public static final String NPAM1322E_PRM2 = "PO";

    /**
     * NPAM1322E_PRM3 = "Vender Code"
     */
    public static final String NPAM1322E_PRM3 = "Vender Code";

    /**
     * NPAM1322E_PRM4 = "Interface Data"
     */
    public static final String NPAM1322E_PRM4 = "Interface Data";

    /**
     * FromTine
     */
    public static final String FROMTIME = "000000";

    /**
     * ToTime
     */
    public static final String TOTIME = "999999";

    /**
     * DATE_FORMAT
     */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * DATE_DISP_FORMAT
     */
    public static final String DATE_DISP_FORMAT = "yyyy/MM/dd";

    /**
     * DATE_LENGTH
     */
    public static final int DATE_LENGTH = 8;

    /**
     * TS_FORMAT
     */
    public static final String TS_FORMAT = "yyyyMMddHHmmss";

    /**
     * TS_DISP_FORMAT
     */
    public static final String TS_DISP_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /**
     * TS_LENGTH
     */
    public static final int TS_LENGTH = 14;

    /**
     * MAX_ERROR_COUNT
     */
    public static final int MAX_ERROR_COUNT = 10;

    // START ITG#506831 ADD
    /**
     * PO_ORD_NUM_LENGTH
     */
    public static final int PO_ORD_NUM_LENGTH = 8;

    // END ITG#506831 ADD

    /**
     * LINE NUM LENGTH = 3
     */
    public static final int LINE_NUM_LENGTH = 8;

    /**
     * EDI_PO_ORD_DTL_LINE_NUM_LENGTH = 6 ADD QC1233
     */
    public static final int EDI_PO_ORD_DTL_LINE_NUM_LENGTH = 6;

    /**
     * padding character ZERO
     */
    public static final String PADDING_ZERO = "0";

    /**
     * display Item name: Rcv Date From
     */
    public static final String DISP_ITEM_NAME_RCV_DATE_FROM = "Rcv Date From";

    /**
     * display Item name: Rcv Date To
     */
    public static final String DISP_ITEM_NAME_RCV_DATE_TO = "Rcv Date To";

    /**
     * display Item name: PO Line Num
     */
    public static final String DISP_ITEM_NAME_PO_LINE_NUM = "PO Line#";

    // 2013/06/13 QC1233 T.Tomita Add Start
    /**
     * display Item name: PO Num
     */
    public static final String DISP_ITEM_NAME_PO_NUM = "PO#";

    /**
     * LOWTAB_HEADER H
     */
    public static final String UNDER_TAB_HEADER = "H";

    /**
     * LOWTAB_DETAIL D
     */
    public static final String UNDER_TAB_DETAIL = "D";

    /**
     * Asterisk
     */
    public static final String ASTERISK = "*";

    // 2013/06/13 QC1233 T.Tomita Add End

    /**
     * COMMIT_VALUE "1"
     */
    public static final String COMMIT_VALUE = "1";

    /**
     * NOT_LINE_NUM "000"
     */
    public static final String NOT_LINE_NUM = "000";

    /**
     * NAME_XX_CHECK_BOX "xxChkBox_B1"
     */
    public static final String NAME_XX_CHECK_BOX = "xxChkBox_B1";

    /**
     * ITFC_ID_HEADER "ITFC_ID_"
     */
    public static final String ITFC_ID_HEADER = "ITFC_ID_";

    /**
     * REGEX:NUMBER
     */
    public static final String REGEX_NUMBER = "^[0-9]{1,3}\\.[0-9]{1,2}$";

}
