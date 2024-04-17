/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0050;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWWL0050Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NWWL0050Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWWL0050Query MY_INSTANCE = new NWWL0050Query();

    /**
     * Private constructor
     */
    private NWWL0050Query() {
        super();
    }

    /**
     * Get the NWWL0050Query instance.
     * @return NWWL0050Query instance
     */
    public static NWWL0050Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getNtfyDistList
     * @param bizMsg NWWL0050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfyDistList(NWWL0050CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyDistListId", bizMsg.ntfyDistListId.getValue());

        return getSsmEZDClient().queryObject("getNtfyDistList", params);
    }

    /**
     * getNtfyDistListAsg
     * @param bizMsg NWWL0050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfyDistListAsg(NWWL0050CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyDistListPk", bizMsg.ntfyDistListPk.getValue());

        return getSsmEZDClient().queryObjectList("getNtfyDistListAsg", params);
    }

    /**
     * getNtfyHdr
     * @param bizMsg NWWL0050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfyHdr(NWWL0050CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyDistListPk", bizMsg.ntfyDistListPk.getValue());
        params.put("rowNum", bizMsg.B.length() + 1);

        return getSsmEZDClient().queryObjectList("getNtfyHdr", params);
    }

    /**
     * getNtfyHdr
     * @param bizMsg NWWL0050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDupDistList(NWWL0050CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyDistListPk", bizMsg.ntfyDistListPk.getValue());
        params.put("ntfyDistListNm", bizMsg.ntfyDistListNm.getValue());
        params.put("rowNum", 1);

        return getSsmEZDClient().queryObject("getDupDistList", params);
    }

}
