/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0130;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 *
 * Hold Detail Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/07   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public final class NSBL0130Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSBL0130Query MY_INSTANCE = new NSBL0130Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSBL0130Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSBL0130Query instance.
     * </pre>
     * @return NSBL0130Query instance
     */
    public static NSBL0130Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * 
     * searchList
     * 
     * @param sc Search Condition
     * @param sMsg NSBL0130SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchList(Map<String, Object> sc, NSBL0130SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchList", sc, sMsg.A);
    }
}
