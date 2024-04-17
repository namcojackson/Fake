/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2020.constant;


/**
 *<pre>
 * NLAL2020Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 2016/08/25   CITS            S.Endo          Update          QC#13142
 * 2017/05/09   CITS            R.Shimamoto     Update          QC#18427
 * 2017/06/13   CITS            S.Endo          Update          QC#19049
 * 04/24/2018   CITS            K.Ogino         Update          QC#19154
 * 08/23/2018   CITS            T.Kikuhara      Update          QC#27890
 * 11/06/2018   CITS            M.Naito         Update          QC#28967
 * 04/03/2019   Fujitsu         T.Ogura         Update          QC#31000
 * 05/20/2020   Fujitsu         T.Ogura         Update          QC#56650
 * 12/16/2020   CITS            A.Marte         Update          QC#58070
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 *</pre>
 */
public class NLAL2020Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NLAL2020";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NLAL2020Scrn00";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Please enter "@". */
    public static final String NLAM0173E = "NLAM0173E";

    /** Error occurred due to a process unable to complete.  Please contact IT Dept. */
    public static final String NLAM1077E = "NLAM1077E";

    /** There is not a meter information. */
    public static final String NLAM1317E = "NLAM1317E";

    /** Meter information cannot be uniquely specified. */
    public static final String NLAM1318E = "NLAM1318E";

    /** Please enter serial numbers in Serial Entry Popup if the received qty is larger than 1. */
    public static final String NLAM1335E = "NLAM1335E";

    /** The received qty and the serial qty which is entered by popup do not match. */
    public static final String NLAM1336E = "NLAM1336E";

    /** Details require more than 1 row.  Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /** It is being updated by another user.  Please start again from a search. */
    public static final String NLBM0009E = "NLBM0009E";

    /** Effective Asset does not exist. */
    public static final String NLBM1290E = "NLBM1290E";

    /** It failed to register @. */
    public static final String NLBM1295E = "NLBM1295E";

    /** The entered "Serial Number" is duplicated in lines you selected.  */
    public static final String NLBM1313E = "NLBM1313E";

    // START 2018/11/06 M.Naito [QC#28967,DEL]
//    /** Please cancel RWS. Because there is not a received line. */
//    public static final String NLBM1323E = "NLBM1323E";
    // END 2018/11/06 M.Naito [QC#28967,DEL]

    /** Since physical inventory is in progress, cannot be processed. */
    public static final String NLBM1347E = "NLBM1347E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NLZM2273E = "NLZM2273E";

    /** [@] is not selected. */
    public static final String NLZM2274E = "NLZM2274E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NLZM2277E = "NLZM2277E";

    /** The entered [@] does not exist in master. */
    public static final String NLZM2278E = "NLZM2278E";

    /** The combination of [@] and [@] does not exist in master. */
    public static final String NLZM2279E = "NLZM2279E";

    /** The value for [@] must be the same as [@]. */
    public static final String NLZM2280E = "NLZM2280E";

    /** To ignore the warning hit the [@] button again. To correct the data hit the Apply button. */
    public static final String NLZM2282W = "NLZM2282W";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** You don't have the permission for the specified Warehouse. */
    public static final String NLZM2313E = "NLZM2313E";

    /** This shipping order is not allowed to @ partially. Please select all lines of this order. */
    public static final String NLZM2315E = "NLZM2315E";

    /** The value for [@] must be [@] or less. */
    public static final String NLZM2316E = "NLZM2316E";

    /** In case of Receive Qty 1, please specify one serial#. */
    public static final String NLZM2520E = "NLZM2520E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    // QC#18427 Add Start.
    /** This Serial Number was already received in same Receiving Worksheet. */
    public static final String NLAM1338E = "NLAM1338E";
    // QC#18427 Add End.

    /** Service Machine Master cannot be obtained. */
    public static final String NLZM2433E = "NLZM2433E";

    /** To proceed it, please click "RWS Close" button again. */
    public static final String NLAM1352W = "NLAM1352W";    // 2019/04/03 T.Ogura [QC#31000,ADD]

    /** This RWS Line item has not completed the shipping process and cannot be received. Please complete the shipping process. */
    public static final String NLAM1354E = "NLAM1354E";    // 05/20/2020 T.Ogura [QC#56650,ADD]

    // START 2020/12/16 A.Marte [QC#58070, ADD]
    /** NLAM1356W: The RWS is for WMS. Please hit Receive again if force to Receive only on S21 side. */
    public static final String NLAM1356W = "NLAM1356W";
    // END 2020/12/16 A.Marte [QC#58070, ADD]

    // START 2022/10/26 M.Kikushima [QC#60413,ADD]
    /** NLAM1358W: There are open transactions. Please confirm to AP team. If you still want to proceed, please click [@] button again. */
    public static final String NLAM1358W = "NLAM1358W";
    // END 2022/10/26 M.Kikushima [QC#60413,ADD]

    // --------------------------------
    // Function ID
    // --------------------------------
    /** Function ID: Inquiry. */
    public static final String FUNC_ID_INQUIRY_CHK_PMSN = "NLAL2020T010";

    /** Function ID: Update. */
    public static final String FUNC_ID_UPDATE = "NLAL2020T020";

    /** Function ID: All Wh Permission. */
    public static final String FUNC_ID_ALL_WH_PERMISSION = "NLAL2020T030";

    //--------------------------------
    // FLG
    //--------------------------------
    /** FLG_OFF. */
    public static final String NON_CHK = "-";

    /** Receiving Completion.*/
    public static final String MODE_ID_RECEIVING_COMPLETION = "ReceivingCompletion";
    /** Closed.*/
    public static final String MODE_ID_CLOSED = "Closed";
    /** Approval.*/
    public static final String MODE_ID_APPROVAL = "Approval";
    /** Specified PO Information is invalid or does not exist. */
    public static final String NLAM1341E = "NLAM1341E";

    /** Service Machine Master Process Status Incompleted. cannot be processed under this status.  */
    public static final String NLAM1347E = "NLAM1347E";
}
