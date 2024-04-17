/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1130;

import java.util.Map;

import business.blap.NWAL1130.constant.NWAL1130Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *  Common Popup NWAL1130Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2012   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public final class NWAL1130Query extends S21SsmEZDQuerySupport implements NWAL1130Constant {

    /**
     * Singleton instance.
     */
    private static final NWAL1130Query MYINSTANCE = new NWAL1130Query();

    /**
     * Constructor.
     */
    private NWAL1130Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1130Query
     */
    public static NWAL1130Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * NWAL1130Query.xml=search
     * @param ssmParam Map<String, Object>
     * @param sMsg NWAL1130SMsg
     * @return S21SsmEZDResult
     */
    @SuppressWarnings("boxing")
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NWAL1130SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, 0, sMsg.A.length(), sMsg.A);
    }
}
