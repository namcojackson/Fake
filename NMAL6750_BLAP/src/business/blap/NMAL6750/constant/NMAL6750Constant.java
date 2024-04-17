/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6750.constant;

/**
 *<pre>
 * NMAL6750Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/05   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/05   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#4345
 * 2016/02/25   Fujitsu         C.Tanaka        Update          QC#4337
 * 2017/08/14   Fujitsu         H.Nagashima     Update          QC#8598
 * 2017/10/26   Fujitsu         M.Ohno          Update          QC#21996
 *</pre>
 */
public class NMAL6750Constant {

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The target data for the update does not exist. */
    public static final String NMAM8105E = "NMAM8105E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /**
     * Other data is set to Primary. To continue the process to change
     * primary, click submit.
     */
    public static final String NMAM8261W = "NMAM8261W";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** It failed to delete [@]. */
    public static final String NMAM0208E = "NMAM0208E";

    /**
     * You can't delete the record because this Contact Point is
     * already used by IB.
     */
    public static final String NMAM8354E = "NMAM8354E";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /** [@] should be selected. */
    public static final String NMAM8352E = "NMAM8352E";

    /** Please input @. */
    public static final String NMAM8368E = "NMAM8368E";

    // 2017/10/26 QC#21996 add start
    /** This Contact already exists. Please select Contact from Contact button. */
    public static final String NMAM8673E = "NMAM8673E";
    // 2017/10/26 QC#21996 add end

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** Detail Max Row Number */
    public static final String MAX_ROW = "200";

    /** CTAC_TP */
    public static final String CTAC_TP = "CTAC_TP";

    /** DS_CTAC_PSN_RELN */
    public static final String DS_CTAC_PSN_RELN = "DS_CTAC_PSN_RELN";

    /** DS_CTAC_PNT */
    public static final String DS_CTAC_PNT = "DS_CTAC_PNT";

    //QC#8598 add Start
    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** Contact Point Format US */
    public static final String CTAC_PNT_FMT_US_CD = "US";

    /** Contact Point Format US */
    public static final String CTAC_PNT_FMT_US_NM = "US";

    /** Contact Point Format International */
    public static final String CTAC_PNT_FMT_INTL_CD = "INTL";

    /** Contact Point Format International */
    public static final String CTAC_PNT_FMT_INTL_NM = "Intl";
    //QC#8598 add End

}
