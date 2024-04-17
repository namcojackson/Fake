/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL6870;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Multi Selection Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         S.Yoshida         Create          N/A
 *</pre>
 */
public final class NMAL6870Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6870Query MYINSTANCE = new NMAL6870Query();

    /**
     * Constructor.
     */
    private NMAL6870Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6870Query
     */
    public static NMAL6870Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * NMAL6870Query.xml=search
     * @param ssmParam Map<String, Object>
     * @param cMsg NMAL6870CMsg
     * @return S21SsmEZDResult
     */
    @SuppressWarnings("boxing")
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NMAL6870CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, 0, cMsg.A.length(), cMsg.A);
    }
}
