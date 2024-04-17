/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC003001.constant;

/**
 *<pre>
 * Customer Information Get API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         N.Sugiura       Create          N/A
 * 2016/05/09   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NMZC003001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NMZM0009E = "NMZM0009E";

    /**
     * Input Parameter First Line Address is mandatory field.
     */
    public static final String NMZM0035E = "NMZM0035E";

    /**
     * Input Parameter City Address is mandatory field.
     */
    public static final String NMZM0036E = "NMZM0036E";

    /**
     * Input Parameter State Code is mandatory field.
     */
    public static final String NMZM0037E = "NMZM0037E";

    /**
     * Input Parameter Post Code is mandatory field.
     */
    public static final String NMZM0038E = "NMZM0038E";

    /**
     * Input Parameter Country Code is mandatory field.
     */
    public static final String NMZM0039E = "NMZM0039E";

    /**
     * Country Code is not found.
     */
    public static final String NMZM0064E = "NMZM0064E";

    /**
     * State Code is not found.
     */
    public static final String NMZM0065E = "NMZM0065E";

    /**
     * County is not found.
     */
    public static final String NMZM0066E = "NMZM0066E";

    /**
     * Country is not "US".
     */
    public static final String NMZM0184E = "NMZM0184E";

    /**
     * VertexProxyAPI ended abnormally.
     */
    public static final String NMAM8357E = "NMAM8357E";
    
    // QC#4505
    /**
     * Post, City, and State relation is not found.
     */
    public static final String NMAM8499E = "NMAM8499E";

    /**
     * Post and County relation is not found.
     */
    public static final String NMAM8500E = "NMAM8500E";

    /**
     * More than 2 county names found. Please select county name and try again.
     */
    public static final String NMAM8501E = "NMAM8501E";

    /** Return Code / No Error **/
    public static final String RTRN_CD_NO_ERR = "0";
    /** Return Code / Suggested **/
    public static final String RTRN_CD_SUGGEST = "1";
    /** Return Code / Error**/
    public static final String RTRN_CD_ERROR = "9";

    /** hyphen for Post Code*/
    public static final String HYPHEN = "-";

    /** FaultOrStatusCode */
    public enum FAULT_CD {
        E101,
        E212,
        E213,
        E214,
        E216,
        E302,
        E412,
        E413,
        E420,
        E421,
        E422,
        E423,
        E425,
        E427,
        E428,
        E429,
        E430,
        E431,
        E439,
        E500,
        E501,
        E502,
        E503,
        E504,
        E505,
        E600,
        E601,
    }
}
