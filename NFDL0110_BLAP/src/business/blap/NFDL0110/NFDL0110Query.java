/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0110;

import java.util.Map;

import business.blap.NFDL0110.constant.NFDL0110Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Customer Care
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2016   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0110Query extends S21SsmEZDQuerySupport implements NFDL0110Constant {

    /**
     * Singleton instance.
     */
    private static final NFDL0110Query myInstance = new NFDL0110Query();

    /**
     * Constructor
     */
    public NFDL0110Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0110Query
     */
    public static NFDL0110Query getInstance() {
        return myInstance;
    }

    /**
     * @param map Map
     * @param sMsg NFDL0110SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustomerCareList(Map map, NFDL0110SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getCustomerCareList", map, sMsg.A);
    }

    /**
     * @param map Map
     * @param sMsg NFDL0110SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustName(Map map) {
        return getSsmEZDClient().queryObject("getBillToCustName", map);
    }
}
