/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/11/19   CUSA            H.Suganuma      Create          N/A
 * 07/08/2010   Fujitsu         R.Mori          Update          7161 Mail Sender
 * 04/23/2013   Hitachi         T.Kawazu        Update          QC1057
 * 2013/05/17   Hitachi         T.Aoyagi        Update          QC1034
 *</pre>
 */
package business.blap.NPAL1340.constant;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 2020/06/25   CITS            M.Furugoori     Update          QC#56979
 *</pre>
 */
public interface NPAL1340Constant {

    String DEF_DROP_SHIP_PO_QLFY = "DS";

    String DEF_DROP_SHIP_SER_OWNR_ID = "08";

    // START 2020/06/25 [QC#56979,ADD]
    /**
     * Replace Character at CARR_URL.CARR_TRK_URL.
     */
    public static final String REPLACE_CHAR = "\\$\\$";
    // END   2020/06/25 [QC#56979,ADD]
}
