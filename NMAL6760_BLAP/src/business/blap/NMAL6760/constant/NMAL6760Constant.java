/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6760.constant;

/**
 *<pre>
 * NMAL6760Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   SRAA            Y.Chen          Update          QC#13340
 * 2018/02/23   Fujitsu         Hd.Sugawara     Update          QC#22570
 *</pre>
 */
public class NMAL6760Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    // Mod Start 2018/02/23 QC#22570
    ///**
    // * There are too many search results, there is data that cannot be
    // * displayed.
    // */
    //public static final String NZZM0001W = "NZZM0001W";
    /**
     * Maximum number of records that can be retrieved is @. Please
     * refine your search.
     */
    public static final String NMAM8676W = "NMAM8676W";
    // Mod End 2018/02/23 QC#22570

    // --------------------------------
    // Pull down
    // --------------------------------
    /** Search Mode Code: 02 */
    public static final String SEARCH_MODE_CD_QUICK = "02";

    /** Search Mode Name: 02 */
    public static final String SEARCH_MODE_NM_QUICK = "Quick Lookup";

    /** Status Code: 01 */
    public static final String STATUS_CD_ACTIVE = "01";

    /** Status Code: 02 */
    public static final String STATUS_CD_ALL = "02";

    /** Status Name: 01 */
    public static final String STATUS_NM_ACTIVE = "Active Only";

    /** Status Name: 02 */
    public static final String STATUS_NM_ALL = "Active & Inactive";

    /** Display Hierarchy Accounts Code: 01 */
    public static final String DISP_HRCH_ACCT_CD_ALL = "01";

    /** Display Hierarchy Accounts Code: 02 */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    /** Display Hierarchy Accounts Name: 01 */
    public static final String DISP_HRCH_ACCT_NM_ALL = "All";

    /** Display Hierarchy Accounts Name: 02 */
    public static final String DISP_HRCH_ACCT_NM_BILL = "Bill To's Only";

    /** Display Hierarchy Accounts Name: 03 */
    public static final String DISP_HRCH_ACCT_NM_SHIP = "Ship To's Only";

    /** Display Related Accounts Code: 01 */
    public static final String DISP_RELN_ACCT_CD_ACCT = "01";

    /** Display Related Accounts Code: 02 */
    public static final String DISP_RELN_ACCT_CD_ACCT_ADDR = "02";

    /** Display Related Accounts Code: 03 */
    public static final String DISP_RELN_ACCT_CD_BILL = "03";

    /** Display Related Accounts Code: 04 */
    public static final String DISP_RELN_ACCT_CD_SHIP = "04";

    /** Display Related Accounts Code: 05 */
    public static final String DISP_RELN_ACCT_CD_LEASE_ACCT = "05";

    /** Display Related Accounts Code: 06 */
    public static final String DISP_RELN_ACCT_CD_LEASE_BILL = "06";

    /** Display Related Accounts Name: 01 */
    public static final String DISP_RELN_ACCT_NM_ACCT = "Accts Only";

    /** Display Related Accounts Name: 02 */
    public static final String DISP_RELN_ACCT_NM_ACCT_ADDR = "Accts & Addresses";

    /** Display Related Accounts Name: 03 */
    public static final String DISP_RELN_ACCT_NM_BILL = "Bill To's Only";

    /** Display Related Accounts Name: 04 */
    public static final String DISP_RELN_ACCT_NM_SHIP = "Ship To's Only";

    /** Display Related Accounts Name: 05 */
    public static final String DISP_RELN_ACCT_NM_LEASE_ACCT = "Lease Accts & Addresses";

    /** Display Related Accounts Name: 06 */
    public static final String DISP_RELN_ACCT_NM_LEASE_BILL = "Lease Bill To's Only";

    /** Internal */
    public static final String INTERNAL = "INTERNAL";

    /** External */
    public static final String EXTERNAL = "EXTERNAL";

    /** Direct Sales Account Relationship: Hierarchy */
    public static final String DS_ACCT_RELN_TP_HRCH = "Hierarchy";

    /** Direct Sales Account Relationship: Related */
    public static final String DS_ACCT_RELN_TP_RELN = "Related";

    /** Direct Sales Account Relationship: Lease */
    public static final String DS_ACCT_RELN_TP_LEASE = "Lease";

    /** Relationship(s): Bill To */
    public static final String RELN_BILL_ONLY = "Bill To";

    /** Relationship(s): Ship To */
    public static final String RELN_SHIP_ONLY = "Ship To";

    /** Relationship(s): Bill To, Ship To */
    public static final String RELN_BILL_SHIP = "Bill To, Ship To";

    /** Status : Active */
    public static final String STS_ACTV = "Active";

    /** Status : Inactive */
    public static final String STS_INCTV = "Inactive";

    /** Char : Percent */
    public static final String CHAR_PERCENT = "%";
}
