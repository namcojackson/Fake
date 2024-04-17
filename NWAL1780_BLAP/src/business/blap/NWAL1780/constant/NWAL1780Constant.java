/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1780.constant;

/**
 * Supply Quote Search
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         T.Murai         Create          N/A
 * 2016/09/15   Hitachi         T.Mizuki        Update          QC#14249
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 *</pre>
 */
public class NWAL1780Constant {

    /** Quote Business ID */
    public static final String QUOTE_BIZ_ID = "NWAL1770";

    /** Business Application ID */
    public static final String BIZ_ID = "NWAL1780";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NWAL1780Scrn00";

    /** Header ID (Items) */
    public static final String ITEMS_HDR_ID = "AHEAD";

    // mod start 2016/09/15 CSA QC#14249
    /** LIMIT_DOWNLOAD:65000 */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    // mod end 2016/09/15 CSA QC#14249
    // ----------- Message ID -----------
    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Search ended normally but not all data is displayed since the result exceeded the maximum. */
    public static final String NWAM0007W = "NWAM0007W";

    /** It is incorrect magnitude relation of Date. */
    public static final String NWAM0746E = "NWAM0746E";

    /** Please enter at least one search criteria. */
    public static final String NWAM0754E = "NWAM0754E";

    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    // ----------- Message Parameter -----------
    /** Category */
    public static final String MSG_PARAM_CATG = "Category";
    
    /** Max Fetch Size : 1000 */
    public static final int FETCH_SIZE_MAX = 1000; // ADD S21_NA QC#13856
    
}
