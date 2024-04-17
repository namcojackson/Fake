/*
 * <Pre>Copyright(c)2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1890.constant;

/**
 *<pre>
 *  Order Line Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/10   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890Constant {

    // --------------------------------
    // MODE
    // --------------------------------
    /** Line Config Mode */
    public static final String LINE_CONFIG_MODE = "01";

    /** RMA Mode */
    public static final String RMA_MODE = "02";

    // --------------------------------
    // Status
    // --------------------------------
    /** Cancelled */
    public static final String CANCELLED = "CANCELLED";

    /** Closed */
    public static final String CLOSED = "CLOSED";

    //--------------------------------
    // Message ID
    //--------------------------------

    /** Please set at least one search criteria. */
    public static final String NWAM0001E = "NWAM0001E";

    /** The entered [@] does not exist. */
    public static final String NWAM0507E = "NWAM0507E";

    /** [@] is not exists in Order. */
    public static final String NWAM0938E = "NWAM0938E";
}