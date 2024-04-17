package business.blap.ZZIL0110.constant;

/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
public class ZZIL0110Constant {
    /* sort items for tbl A */
    public static final String[] sortItems_A = {"itrlIntfcId_A", "xxIntfcInterCntNum_A", "xxDtTm_AC", "xxDtTm_AU" };

    /* sort items for tbl B */
    public static final String[] sortItems_B = {"itrlIntfcTblId_B", "xxDtTm_BC", "xxDtTm_BU" };

    /* Duplicate Key. */
    public static final String ZZXL0001E = "ZZXL0001E";

    /* Not found delete record. */
    public static final String ZZXL0005E = "ZZXL0005E";

    /* Illegal argument found. [@ : @ ] */
    public static final String ZZIM0012E = "ZZIM0012E";
    
    /* No search results found. */
    public static final String ZZZM9005W = "ZZZM9005W";
    
    /* Too many search results.  Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";
    
    public static final String mstrPrefix = "_MSTR_";

    public static final String selfMstrTbl = "ITRL_MSTR_";
}
