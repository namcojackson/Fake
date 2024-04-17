/**
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.blap.ZZIL0100.constant;

/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
public class ZZIL0100Constant {
    /* sort items for tbl A */
    public static final String[] sortItems_A = {
            "transactionId_A",
            "processedFlag_A",
            "itrlIntfcRecCnt_A",
            "xxDtTm_AC",
            "xxBizAppId_AC",
            "xxDtTm_AU",
            "xxBizAppId_AU"};
    
    /* Illegal argument found. [@ : @ ] */
    public static final String ZZIM0012E = "ZZIM0012E";
    
    /* No search results found. */
    public static final String ZZZM9005W = "ZZZM9005W";
    
    /* Too many search results.  Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";
}
