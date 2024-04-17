/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/11/25   Fujitsu         T,Murai         Update          QC#13259
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public class NFCL2650Constant {
    /** Business ID */
    public static final String BIZ_ID = "NFCL2650";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFCL2650Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    /** >> Button(From) */
    public static final String[] BTN_GET_CUST_NM_FROM = {"GetCustNmFrom", "Get_CustNmFrom", ">>" };

    /** >> Button(To) */
    public static final String[] BTN_GET_CUST_NM_TO = {"GetCustNmTo", "Get_CustNmTo", ">>" };

    /** >> Button(BillToCust) */
    public static final String[] BTN_GET_BILL_TO_CUST = {"GetBillToCust", "Get_BillToCust", ">>" };

    /** From Code */
    public static final String FROM_CD = "FromCd";

    /** To Code */
    public static final String TO_CD = "ToCd";

    /** From Name */
    public static final String FROM_NM = "FromNm";

    /** To Name */
    public static final String TO_NM = "ToNm";

    /** From */
    public static final String FROM = "From";

    /** To */
    public static final String TO = "To";

    /** Location */
    public static final String LOCATION = "Location"; // Mod 2016/11/25 [QC#13259]

    /** Screen ID 00 */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    // --------------------------------
    // Message
    // --------------------------------

    /**
     * The value in the 'To' field has to be equal to or greater than
     * the 'From' field.
     */
    public static final String NFCM0023E = "NFCM0023E";

    /** Please enter the value. */
    public static final String NFCM0038E = "NFCM0038E";

    /**
     * Please enter Customer Number From and To, Customer Name From and To or Location.
     */
    public static final String NFCM0808E = "NFCM0808E";

    // Start 2016/12/22 H.Ikeda [QC#12865,ADD]
    /** Function ID NFCL2650T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFCL2650T020";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/22 H.Ikeda [QC#12865,ADD]

}
