/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0590.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         J.Kim           Create          N/A
 * 2016/03/25   Hitachi         M.Gotou         Update          QC#4919
 *</pre>
 */
public class NSAL0590Constant {

    /**
     * No search results found.
     */
    public static final String NSAM0194I = "NSAM0194I";

    /**
     * More than @ records have been retrieved. Please change search
     * conditions and try again.
     */
    public static final String NSAM0006I = "NSAM0006I";

    /**
     * The corresponding data does not exist.
     * <TABLE: [@], Data Key: [@]>
     */
    public static final String NSAM0004E = "NSAM0004E";

    /**
     * Please enter [&param]. <&param = Description or Start Date>
     */
    public static final String NSAM0212E = "NSAM0212E";

    /**
     * You can not enter [&param1] earlier than [&param2]. <&param1 =
     * End Date/&param2 = Start Date
     */
    public static final String NSAM0346E = "NSAM0346E";

    /**
     * Cannot add anymore details.
     */
    public static final String NSAM0112E = "NSAM0112E";

    /**
     *This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * SEARCH_LIMIT_COUNT
     */
    public static final int SEARCH_LIMIT_COUNT = 2000;

    // START 2016/03/25 M.Gotou [QC#4919, ADD]
    /**
     * END_DT : 99991231
     */
    public static final String END_DT = "99991231";
    // END 2016/03/25 M.Gotou [QC#4919, ADD]
}
