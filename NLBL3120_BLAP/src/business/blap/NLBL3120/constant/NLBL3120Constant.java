/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3120.constant;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 * 02/02/2017   CITS            M.Naito         Update          QC#12673
 * 2023/08/01   Hitachi         M.Kikushima     Update          QC#61677
 *</pre>
 */
public class NLBL3120Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLBL3120";

    /** Screen ID */
    public static final String SCRN_ID = "NLBL3120Scrn00";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NLBL3120_WorkloadBalancingAndMonitor";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV_HDR */
    public static final String[] CSV_HDR = new String[] {
        "Sales Order#" //
        , "Order Category" //
        , "Order Reason" //
        , "E/C" //
        , "Shipping Order#" //
        , "RWS#" //
        , "WMS Drop" //
        , "Config Count/ID" //
        , "Model" //
        , "Pick Config Id" //
        , "Warehouse " //
        , "Coordinator Code" //
        , "Coordinator Name" //
        , "Customer Code" //
        , "Customer Name" //
        , "Customer Address" //
        , "City" //
        , "State" //
        , "Scheduling Status" //
        , "SO/RWS Status" //
        , "Shipping/Pickup Service Level" //
        , "Carrier Code" //
        , "Carrier Name" //
        , "Carrie Contact/Phone" //
        , "Carrier Account#" //
        , "Sales Rep" //
        , "Request Date" //
        , "Scheduled Date" //
        , "Ship Conf/RWS Close Date" };

    /** The entered [@] does not exist in master. */
    public static final String NLZM2278E = "NLZM2278E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NLZM2273E = "NLZM2273E";

    /** [@] is not selected. */
    public static final String NLZM2274E = "NLZM2274E";

    /** [@] and [@] are not consistent. */
    public static final String NLBM1308E = "NLBM1308E";

    /** [@] and [@] are not consistent. */
    public static final String NLBM1328W = "NLBM1328W";

    /** Please enter today or future date on [@]. */
    public static final String NLBM1231E = "NLBM1231E";

    /** Details require more than 1 row. Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /**
     * Not open status line(s) are included in selected Shipping
     * Order.
     */
    public static final String NLBM1307E = "NLBM1307E";

    /** The Shipping Order has ready to drop to WMS. */
    public static final String NLBM1310E = "NLBM1310E";

    /**
     * To ignore the warning hit the [@] button again. To correct the
     * data hit the Apply button.
     */
    public static final String NLZM2282W = "NLZM2282W";

    /**
     * Please execute again after checking the warning field.
     */
    public static final String NATM0001W = "NATM0001W";

    /**
     * It is being updated by another user. Please start again from a
     * search.
     */
    public static final String NLBM0009E = "NLBM0009E";

    /** [@] has not been changed. */
    public static final String NLBM1303E = "NLBM1303E";

    /**
     * Already sent the request to the carrier. Do you need to update
     * carrier code?
     */
    public static final String NLBM1327W = "NLBM1327W";

    /** @ is different from the one previously @. */
    public static final String NLBM1301W = "NLBM1301W";

    /** It failed to register @. */
    public static final String NLBM1295E = "NLBM1295E";

    /** @ field has not been entered. */
    public static final String NLBM0052E = "NLBM0052E";

    /**
     * You cannot select this Coordinator, because you do not have
     * rights to it.
     */
    public static final String NLBM1328E = "NLBM1328E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Please select only one Check Box. */
    public static final String NZZM0009E = "NZZM0009E";

    // START 2023/08/01 M.Kikushima [QC#61677, ADD]
    /** The entered status is not allowed to apply for ‘RWS’. */
    public static final String NLBM1381E = "NLBM1381E";
    // END 2023/08/01 M.Kikushima [QC#61677, ADD]

    // ----------------------------
    // Event Name
    // ----------------------------
    /** Event Name: OpenWin_CoordSearchCode */
    public static final String EVENT_NM_OPENWIN_COORD_SEARCH_CD = "OpenWin_CoordSearchCode";

    /** Event Name: OpenWin_CoordSearchInfo */
    public static final String EVENT_NM_OPENWIN_COORD_SEARCH_INFO = "OpenWin_CoordSearchInfo";

    /** Event Name: OpenWin_AplyCoordSearchCode */
    public static final String EVENT_NM_OPENWIN_APLY_COORD_SEARCH = "OpenWin_AplyCoordSearchCode";

    // ----------------------------
    // Search Condition
    // ----------------------------

    /**
     * Search Condition SO
     */
    public static final int SEARCH_SO = 2;

    /**
     * Search Condition RWS
     */
    public static final int SEARCH_RWS = 1;

    /**
     * Search Condition RWS and SO
     */
    public static final int SEARCH_RWS_AND_SO = 3;

    // ----------------------------
    // etc
    // ----------------------------

    /**
     * ArrayList first record.
     */
    public static final int FIRST_RECORD = 0;
}
