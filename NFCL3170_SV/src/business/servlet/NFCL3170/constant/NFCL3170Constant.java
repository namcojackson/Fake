package business.servlet.NFCL3170.constant;

/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 * 05/27/2016   Fujitsu         S.Fujita        Update          QC#8534
 *</pre>
 */
public interface NFCL3170Constant {

    /**
     * BusinessID:"NFCL3170"
     */
    String BUSINESS_ID = "NFCL3170";

    /**
     * CancelBusinessID:"NFCL3120"
     */
    String SEARCH_BUSINESS_ID = "NFCL3120";
    
    /**
     * XX_MODE_CD
     */
    String NEW_BUTTON_ON = "1";

    /**
     * XX_MODE_CD
     */
    String NEW_BUTTON_OFF = "0";
    

    static final String AUTH_READONLY = "NFCL3170T00";

    static final String AUTH_READWRITE = "NFCL3170T10";

    // START 2016/05/27 S.Fujita [QC#8534,ADD]
    /**
     * Click_LinkCity
     */
    public static final String EVENT_CLICK_LINK_CITY = "Click_LinkCity";
    /**
     * Click_LinkCounty
     */
    public static final String EVENT_CLICK_LINK_COUNTY = "Click_LinkCounty";
    /**
     * Click_LinkState
     */
    public static final String EVENT_CLICK_LINK_STATE = "Click_LinkState";
    /**
     * Click_LinkPostal
     */
    public static final String EVENT_CLICK_LINK_POSTAL = "Click_LinkPostal";
    // END   2016/05/27 S.Fujita [QC#8534,ADD]
}
