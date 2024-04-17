/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8810;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8810Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public final class NYEL8810Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8810Query MY_INSTANCE = new NYEL8810Query();

    /**
     * Private constructor
     */
    private NYEL8810Query() {
        super();
    }

    /**
     * Get the NYEL8810Query instance.
     * @return NYEL8810Query instance
     */
    public static NYEL8810Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NYEL8810CMsg
     * @param glblMsg NYEL8810SMsg
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult search(NYEL8810CMsg bizMsg, NYEL8810SMsg
    // glblMsg) {
    // Map<String, Object> params = new HashMap<String, Object>();
    // TODO [Template] set ssm parameters.
    // params.put("glblCmpyCd", getGlobalCompanyCode());
    // return getSsmEZDClient().queryEZDMsgArray("getSampleToSMsg",
    // params, glblMsg.A);
    // }
    // [Template] sample logic. result data receive to Map.
    // /**
    // * getSampleList
    // * @return S21SsmEZDResult
    // */
    // public S21SsmEZDResult getSampleList() {
    // Map<String, Object> params = new HashMap<String, Object>();
    // params.put("glblCmpyCd", getGlobalCompanyCode());
    //
    // return getSsmEZDClient().queryObjectList("getSampleList",
    // params);
    // }
    // [Template] sample method for search. result data receive to
    // sMsg.
    // /**
    // * getSampleToSMsg
    // * @param bizMsg NYEL8810CMsg
    // * @param glblMsg NYEL8810SMsg
    // * @return S21SsmEZDResult
    // */
    // public S21SsmEZDResult getSampleToSMsg(NYEL8810CMsg bizMsg,
    // NYEL8810SMsg glblMsg) {
    // Map<String, Object> params = new HashMap<String, Object>();
    // params.put("glblCmpyCd", getGlobalCompanyCode());
    // params.put("limitRownum", glblMsg.A.length());
    //
    // return getSsmEZDClient().queryEZDMsgArray("getSampleToSMsg",
    // params, glblMsg.A);
    //
    // }
}
