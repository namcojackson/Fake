/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1090.constant;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 * 2023/07/06   CITS            T.Aizawa        Update          QC#59538
 *</pre>
 */
public final class NSAL1090Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Parameter "@" is not set.
     */
    public static final String NSAM0131E = "NSAM0131E";

    /**
     * There are Current or Future PE that will be updated or rolled back. Please record the PE info and correct it manually after CI approval - if needed
     */
    public static final String NSAM0754W = "NSAM0754W";

    // START 2023/07/06 t.aizawa [QC#59538,ADD]
    /**
     * System cannot perform C&R for Invoice with same Invoice#, same Detail, same Charge Type, and more than one Billing Period.
     */
    public static final String NSAM0780E = "NSAM0780E";

    /**
     * Please execute it again after correcting the error field.
     */
    public static final String ZZM9037E = "ZZM9037E";
    // END   2023/07/06 t.aizawa [QC#59538,ADD]

}
