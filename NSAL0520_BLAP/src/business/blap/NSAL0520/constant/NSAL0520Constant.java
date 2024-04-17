/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0520.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2023/05/19   Hitachi         R.Takau         Update          QC#61409
 *</pre>
 */
public class NSAL0520Constant {

    /**
     * Number of results exceeds allowed maximum of @ records.
     */
    public static final String ZZSM4102W = "ZZSM4102W";

    /**
     * Input parameter "@" is a mandatory field.
     */
    public static final String NSAM0362E = "NSAM0362E";

    // START 2023/03/16 R.Takau [ADD,QC#61409]
    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0520";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** down-load limit */
    public static final int LIMIT_DOWNLOAD = 65000;
    // END 2023/03/16 R.Takau [ADD,QC#61409]

}
