/**
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC302001;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2015   CITS            Hisashi         Create          N/A
 * 11/25/2015   Fujitsu         Y.Taoka         Update          Add  Error Status Code
 *</pre>
 */
public class NLZC302001Constant {

    /** Message description substring length */
    public static final int MSG_SUBSTRING_LENGTH = 10;

    /** "Global Company Code" must be entered. */
    public static final String NLZM2052E = "NLZM2052E";

    /** Serial # is not set. */
    public static final String NLZM2091E = "NLZM2091E";

    /** Merchandise Code is not entered. */
    public static final String NLZM0005E = "NLZM0005E";

    /** Serial Transaction Timestamp is not set. */
    public static final String NLZM2169E = "NLZM2169E";

    /** Serial Transaction Event Code is not set. */
    public static final String NLZM2170E = "NLZM2170E";

    /** Manual Creation Flag is not set. */
    public static final String NLZM2171E = "NLZM2171E";

    /** Cannot retrieve Serial Transaction information. */
    public static final String NLZM2172E = "NLZM2172E";

    /** Failed to register SER_TRX. */
    public static final String NLZM2173E = "NLZM2173E";

    /** Failed to update SER_TRX. */
    public static final String NLZM2174E = "NLZM2174E";

    /** To Stock Status Code is not set. */
    public static final String NLZM2270E = "NLZM2270E";

    /** Error Status Code for Serial Number Length */
    public static final String LEN_ERR = "400";

    /** Error Status Code for Serial Number Range */
    public static final String RNG_ERR = "500";

    /** Error Status Code for Duplication Error */
    public static final String DUP_ERR = "600";

    /** Error Status Code for Duplication Error(Returned Item) */
    public static final String DUP_ERR_RTRN_ITEM = "700";

    /** Error Status Code for Manual Deletion */
    public static final String MAN_DEL_ERR = "800";

    /** Error Status Code for Not Scheduled */
    public static final String NOT_SCHD_ERR = "900";
}
