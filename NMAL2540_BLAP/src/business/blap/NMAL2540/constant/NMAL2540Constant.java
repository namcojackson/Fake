/*
 * <pre>Copyright (c) 2014 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2540.constant;

/**
 * <pre>
 * Invoice Creation and Document Send Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NMAL2540Constant {

    /** FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "Address1";

    /** FIRST_LINE_ADDR */
    public static final String SCD_LINE_ADDR = "Address2";

    /** FIRST_LINE_ADDR */
    public static final String THIRD_LINE_ADDR = "Address3";

    /** FIRST_LINE_ADDR */
    public static final String FRTH_LINE_ADDR = "Address4";

    /** FIRST_LINE_ADDR */
    public static final String CTY_ADDR = "City";

    /** FIRST_LINE_ADDR */
    public static final String ST_CD = "State";

    /** FIRST_LINE_ADDR */
    public static final String PROV_NM = "Province";

    /** FIRST_LINE_ADDR */
    public static final String CNTY_NM = "County";

    /** FIRST_LINE_ADDR */
    public static final String POST_CD = "Postal Code";

    /** FIRST_LINE_ADDR */
    public static final String CTRY_CD = "Country";

    /** NO_ERROR */
    public static final String NMZC0030_NO_ERROR = "0";

    /** NO_ERROR */
    public static final String NMZC0030_SUGGESTED = "1";

    /** NO_ERROR */
    public static final String NMZC0030_ERROR = "9";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * We found a different address from the one you entered your
     * address has been modified. Please confirm and click submit.
     */
    public static final String NMAM8360W = "NMAM8360W";

    /**
     * You have entered an invalid address. Please enter a valid
     * address.
     */
    public static final String NMAM8359E = "NMAM8359E";
    
    // QC#4505
    /** Enter Code directly, if it is non-USA. */
    public static final String NMAM0147I = "NMAM0147I";
    
    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";

}
