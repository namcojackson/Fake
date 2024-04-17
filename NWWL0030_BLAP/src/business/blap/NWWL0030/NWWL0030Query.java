/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0030;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWWL0030Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NWWL0030Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWWL0030Query MY_INSTANCE = new NWWL0030Query();

    /**
     * Private constructor
     */
    private NWWL0030Query() {
        super();
    }

    /**
     * Get the NWWL0030Query instance.
     * @return NWWL0030Query instance
     */
    public static NWWL0030Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getNtfyHist
     * @param bizMsg NWWL0030CMsg
     * @param glblMsg NWWL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfyHist(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyRunJobId", bizMsg.ntfyRunJobId.getValue());
        params.put("effFromDt", bizMsg.effFromDt.getValue());
        params.put("effThruDt", bizMsg.effThruDt.getValue());
        params.put("ntfyHdrNm", bizMsg.ntfyHdrNm.getValue());
        params.put("ntfyHdrDescTxt", bizMsg.ntfyHdrDescTxt.getValue());
        params.put("ntfyBizAreaTpCd", bizMsg.ntfyBizAreaTpCd.getValue());
        params.put("ntfySubAreaTpCd", bizMsg.ntfySubAreaTpCd.getValue());
        params.put("rowNum", glblMsg.A.length());

        return getSsmEZDClient().queryObjectList("getNtfyHist", params);
    }

    /**
     * getActioResult
     * @param bizAMsg NWWL0030_ACMsg
     * @param glblMsg NWWL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActRslt(NWWL0030_ACMsg bizAMsg, NWWL0030SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyHdrId", bizAMsg.ntfyHdrId_A0.getValue());
        params.put("ntfyRunJobId", bizAMsg.ntfyRunJobId_A0.getValue());
        params.put("rowNum", glblMsg.B.length());

        return getSsmEZDClient().queryObjectList("getActRslt", params);
    }

    /**
     * getNtfyActDtl
     * @param bizBMsg NWWL0030_BCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfyActDtl(NWWL0030_BCMsg bizBMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyActDtlHistId", bizBMsg.ntfyActDtlHistId_B0.getValue());

        return getSsmEZDClient().queryObject("getNtfyActDtl", params);
    }

}
