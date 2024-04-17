/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/05   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
package business.blap.NLCL0230;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NLCL0230Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NLCL0230Query myInstance = new NLCL0230Query();


    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NLCL0230Query() {
        super();
    }

    /**
     * <pre>
     * Get the ALCL230Query instance.
     * </pre>
     * @return NLCL0230Query instance
     */
    public static NLCL0230Query getInstance() {
        return myInstance;
    }

    /**
     * <pre>
     * Find the Inventory Location View. 
     * </pre>
     * @param  bizMsg NLCL0230CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvty(NLCL0230CMsg bizMsg) {
        return getSsmEZDClient().queryEZDMsgArray("findInvty", bizMsg, bizMsg.A);
    }

    /**
     * <pre>
     * Find the Inventory Location View with Merchandise. 
     * </pre>
     * @param  bizMsg NLCL0230CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvtyWithMdse(NLCL0230CMsg bizMsg) {
        return getSsmEZDClient().queryEZDMsgArray("findInvtyWithMdse", bizMsg, bizMsg.A);
    }
}
