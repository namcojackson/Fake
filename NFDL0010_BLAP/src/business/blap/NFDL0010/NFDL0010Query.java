package business.blap.NFDL0010;

import java.util.Map;

import business.blap.NFDL0010.constant.NFDL0010Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0010Query extends S21SsmEZDQuerySupport implements NFDL0010Constant {

    /**
     * Singleton instance.
     */
    private static final NFDL0010Query myInstance = new NFDL0010Query();

    /**
     * Constructor
     */
    public NFDL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFDL0010Query getInstance() {
        return myInstance;
    }

    /**
     * @param map Map
     * @param sMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionSummaryList(Map map, NFDL0010SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getCollectionSummaryList", map, sMsg.A);
    }
}
