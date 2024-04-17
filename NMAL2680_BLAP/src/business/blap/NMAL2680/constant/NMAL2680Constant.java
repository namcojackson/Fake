/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2680.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 * 2016/08/03   Fujitsu         C.Yokoi         Update          CSA-QC#12679
 *</pre>
 */
public class NMAL2680Constant {

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data */
    public static final String NZZM0001W = "NZZM0001W";

    // 2016/08/03 CSA-QC#12679 Add Start
    /** [@] can not be obtained. */
    public static final String NMAM8489E = "NMAM8489E";
    // 2016/08/03 CSA-QC#12679 Add End

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** CSV File Name */
    public static final String CSV_FILE_NAME = "TerritoryRules";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** Territory */
    public static final String TERRITORY = "Territory";
}
