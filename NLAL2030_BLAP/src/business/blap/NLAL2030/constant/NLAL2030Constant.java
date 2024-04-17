/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2030.constant;

/**
 *<pre>
 * NLAL2030Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 02/02/2017   CITS            M.Naito         Update          QC#12673
 * 06/08/2017   CITS            M.Naito         Update          QC#18382
 * 06/20/2017   CITS            M.Naito         Update          QC#19269
 * 08/09/2017   CITS            S.Katsuma       Update          QC#19232
 * 02/06/2018   CITS            T.Tokutomi      Update          QC#18461-Sol#085
 * 12/14/2020   CITS            A.Marte         Update          QC#58069
 * 11/11/2021   CITS            A.Marte         Update          QC#59350
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 * 11/23/2022   CITS            R.Azucena       Update          QC#60835
 * 02/22/2023   Hitachi         TZ.Win          Update          QC#61161
 *</pre>
 */
public class NLAL2030Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NLAL2030";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NLAL2030Scrn00";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** "@" ended abnormally. */
    public static final String NLAM0043E = "NLAM0043E";

    /** No search results found.  Please modify the search criteria. */
    public static final String NLAM0049E = "NLAM0049E";

    /** The selected RWS cannot be cancelled. */
    public static final String NLAM0085E = "NLAM0085E";

    /** The selected RWS has already been sent to WMS. Manual cancel is needed from within WMS. Press button again to continue. */
    public static final String NLAM1278W = "NLAM1278W";

    /** Please select at least one row. */
    public static final String NLAM1288E = "NLAM1288E";

    /** RWS cannot be creates, because the Source Document Type is not a target for processing. */
    public static final String NLAM1322E = "NLAM1322E";

    /** RWS cannot be creates, Select the same Source Document# and WH code. */
    public static final String NLAM1323E = "NLAM1323E";

    /** You cannot cancel RWS, because already received in. */
    public static final String NLAM1325E = "NLAM1325E";

    /** You cannot cancel RWS, because received line exists. */
    public static final String NLAM1326E = "NLAM1326E";

    /** You cannot cancel RWS, because shipping order is not shipped or canceled. */
    public static final String NLAM1327E = "NLAM1327E";

    /** The selected RWS has already been sent request to carrier. Press button again to continue. */
    public static final String NLAM1328W = "NLAM1328W";

    /** It is being updated by another user.  Please start again from a search. */
    public static final String NLBM0009E = "NLBM0009E";

    /** It failed to register @. */
    public static final String NLBM1295E = "NLBM1295E";

    /** This Shipment Number has already existed in @.  */
    public static final String NLAM1337E = "NLAM1337E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NLZM2273E = "NLZM2273E";

    /** [@] is not selected. */
    public static final String NLZM2274E = "NLZM2274E";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** It failed to generate a report instance. */
    public static final String NLZM1041E = "NLZM1041E";

    /** It failed to update the RWS HDR print results. */
    public static final String NLZM1042E = "NLZM1042E";

    // START 2017/08/10 S.Katsuma [QC#19232,ADD]
    /** Receiving Worksheet scheduled to be received has already existed. (Src Doc Line: [@]) */
    public static final String NLAM1342E = "NLAM1342E";
    // END 2017/08/10 S.Katsuma [QC#19232,ADD]

    // QC#18461-Sol#085 Add.
    /** NLZM2313E : You don't have the permission for the specified Warehouse. */
    public static final String NLZM2313E = "NLZM2313E";

    /** NLAM1343W : Please confirm that you can execute Submit processing. If there is ok, please click the Submit button again. */
    public static final String NLAM1343W = "NLAM1343W";

    /** NLAM1344E : This line can not be updated. RWS status is not [@]. */
    public static final String NLAM1344E = "NLAM1344E";

    /** NLAM1345E : There are records with different [@].*/
    public static final String NLAM1345E = "NLAM1345E";

    /** NLCM0123E : The target data for the update does not exist.*/
    public static final String NLCM0123E = "NLCM0123E";

    /** NMAM0269E : In case of "@", "@" cannot be selected.*/
    public static final String NMAM0269E = "NMAM0269E";

    /** TBD: The RWS is not canceled from WMS yet. Please hit Receive again if force to cancel only on S21 side. */
    public static final String NLAM1355W = "NLAM1355W";

    // START 2021/11/11 A.Marte [QC#59350, ADD]
    /** NLAM1357E : There is no valid SWH combination. [@] */
    public static final String NLAM1357E = "NLAM1357E";
    // END 2021/11/11 A.Marte [QC#58069, ADD]

    // START 2022/10/26 M.Kikushima [QC#60413,ADD]
    /** NLAM1358W: There are open transactions. Please confirm to AP team. If you still want to proceed, please click [@] button again. */
    public static final String NLAM1358W = "NLAM1358W";
    // END 2022/10/26 M.Kikushima [QC#60413,ADD]

    // START 2022/11/23 R.Azucena [QC#60835, ADD]
    /** NLAM1359E : Cannot find next RWS_REF_NUM for [@]. */
    public static final String NLAM1359E = "NLAM1359E";
    // END 2022/11/23 R.Azucena [QC#60835, ADD]

    // --------------------------------
    // Tab ID
    // --------------------------------
    /** Tab ID: Order */
    public static final String TAB_ID_ORD = "Tab_Ord";

    /** Tab ID: RWS List */
    public static final String TAB_ID_RWS = "Tab_Rws";

    // --------------------------------
    // SERCH MODE
    // --------------------------------
    /** SERCH MODE */
    public static final int SEARCH_MODE_HDR = 0;

    /** SERCH MODE */
    public static final int SEARCH_MODE_RESEARCH = 1;

    /** SERCH MODE */
    public static final int SEARCH_MODE_TAB = 2;

    // --------------------------------
    // RWS_CREATE_MODE
    // --------------------------------
    /** RWS_CREATE_MODE: PO */
    public static final String RWS_CREATE_MODE_PO = "1";

    /** RWS_CREATE_MODE: Return */
    public static final String RWS_CREATE_MODE_RTRN = "2";

    // --------------------------------
    // Source Doc Type
    // --------------------------------
    /** Source Doc Type : UNKITTING_CANCEL */
    public static final String UNKITTING_CANCEL = "KU";

    /** DS_COND_CONST : UNKITTING_CANCEL */
    public static final String DCC_UNKITTING_CANCEL = "UC";

    /** DS_COND_CONST : KITTING_CANCEL */
    public static final String DCC_KITTING_CANCEL = "KC";

    // QC#19269
    /** SEARCH_RWS_TP_PO */
    public static final String SEARCH_RWS_TP_PO = "PO";

    /** SEARCH_RWS_TP_CPO */
    public static final String SEARCH_RWS_TP_CPO = "CPO";

    /** SEARCH_RWS_TP_SO */
    public static final String SEARCH_RWS_TP_SO = "SO";

    /** SEARCH_RWS_TP_PR */
    public static final String SEARCH_RWS_TP_PR = "PR";

    /** SEARCH_RWS_TP_KT */
    public static final String SEARCH_RWS_TP_KT = "KT";

    /** SEARCH_RWS_TP_KU */
    public static final String SEARCH_RWS_TP_KU = "KU";

    /** SEARCH_RWS_TP_UC */
    public static final String SEARCH_RWS_TP_UC = "UC";

    /** SEARCH_RWS_TP_KC */
    public static final String SEARCH_RWS_TP_KC = "KC";

    /** SEARCH_RWS_TP_RMN */
    public static final String SEARCH_RWS_TP_RMN = "RMN";

    /** DS_COND_CONST_GRP_ID : NLAL2030_RWS_CTRL */
    public static final String NLAL2030_RWS_CTRL = "NLAL2030_RWS_CTRL";

    // --------------------------------
    // CSV DownLoad
    // --------------------------------
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV File Name : Order */
    public static final String CSV_FILE_NAME_ORD = "NLAL2030_ReceivingOrdersLookupOrder";

    /** CSV File Name : RWS List */
    public static final String CSV_FILE_NAME_RWS = "NLAL2030_ReceivingOrdersLookupRWSList";

    /** CSV File Name : RWS List */
    public static final int LIMIT_DL_ROWNUM = 65000;

    /** CSV Header Order List */
    public static final String[] CSV_HDR_ORD = new String[] {
          "CheckBox"
        , "CheckBox"
        , "Source Doc Type"
        , "Source Document#"
        , "Src Doc Line"
        , "Party Site"
        , "Party"
        , "Warehouse/Tech"
        , "Config ID"
        , "Item Number"
        , "Alternate Item"
        , "Item Description"
        , "Request Qty"
        , "Open Qty"
        , "Serial"
        , "Open RWS"
        , "MT"
        , "Supplier Item"
        , "Sub Warehouse"
    };

    /** CSV Header Order List */
    public static final String[] CSV_HDR_RWS = new String[] {
          "CheckBox"
        , "RWS Status"
        , "RWS Number"
        // START 2023/02/22 TZ.Win [QC#61161, ADD]
        , "ASN Creation Date"
        // END 2023/02/22 TZ.Win [QC#61161, ADD]
        , "RWS Line"
        , "Warehouse/Tech"
        , "Shipment Number"
        , "RMA Disposition"
        , "Tracking Number"
        , "Source Document Type"
        , "Source Document#"
        , "Src Doc Line"
        , "Party Site"
        , "Party"
        , "Config ID"
        , "Item Number"
        , "Alternate Item"
        , "Item Description"
        , "Request Qty"
        , "Open Qty"
        , "Serial"
        , "Sub Warehouse"
        , "Received WH"
    };

    // QC#18461-Sol#085 Add.
    // ROLE FUNCTION
    /** ROLE_ALL_WH_PERMISSION */
    public static final String ROLE_ALL_WH_PERMISSION = "NLAL2030T030";

    // START 2020/12/14 A.Marte [QC#58069, MOD]
    // --------------------------------
    // WMS Task Code
    // --------------------------------
    /** PO cancel */
    public static final String WMS_TASK_CD_PO_CANCEL = "PDLT";
    // END 2020/12/14 A.Marte [QC#58069, MOD]

    // START 2022/11/23 R.Azucena [QC#60835, ADD]
    /** MAX_REVISION_DIGITS_NUM */
    public static final int MAX_REVISION_DIGITS_NUM = 2;
    // END 2022/11/23 R.Azucena [QC#60835, ADD]
}
