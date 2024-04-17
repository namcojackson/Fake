/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1610.constant;

/**
 *<pre>
 * NWAL1610Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         C.Yokoi         Create          N/A
 * 2019/12/20   Fujitsu         S.Kosaka        Update          QC#54999
 *</pre>
 */
public class NWAL1610Constant {


    //--------------------------------
    // Message ID
    //--------------------------------
    /** The search returned no results. */
    public static final String NWZM0819E = "NWZM0819E";

    /** Entered '@' does not exist in the Master. */
    public static final String NWZM1108E = "NWZM1108E";

    /** Available Order line category did not found. */
    public static final String NWZM1583E = "NWZM1583E";

    /** Available Order line Source did not found. */
    public static final String NWAM8250E = "NWAM8250E";

    // --------------------------------
    // Valiable
    // --------------------------------
    /** Parameter Mode : Config */
    public static final String CONFIG_MODE = "01";

    /** Parameter Mode : Line */
    public static final String LINE_MODE = "02";

    /** Parameter Mode : RMA */
    public static final String RMA_MODE = "03";

    // 2019/12/20 QC#54999 Add Start
    /** Business ID -- NWAL1500 */
    public static final String BUSINESS_ID_NWAL1500 = "NWAL1500";
    // 2019/12/20 QC#54999 Add End
}
