/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0400;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Mods Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         M.Gotou         Create          N/A
 * 2018/05/24   Hitachi         U.Kim           Update          QC#22393
 *</pre>
 */
public final class NSBL0400Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0400Query INSTANCE = new NSBL0400Query();

    /**
     * Constructor.
     */
    private NSBL0400Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0400Query
     */
    public static NSBL0400Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSBL0400CMsg
     * @param sMsg NSBL0400SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NSBL0400CMsg
     * @param sMsg NSBL0400SMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcModYr", cMsg.svcModYr.getValue());
        params.put("svcModMth", cMsg.svcModMth.getValue());
        params.put("svcModDay", cMsg.svcModDay.getValue());
        params.put("svcMnfCd", cMsg.svcMnfCd.getValue());
        params.put("svcModSqNum", cMsg.svcModSqNum.getValue());
        params.put("svcModNm", cMsg.svcModNm.getValue());
        params.put("mdseCd", cMsg.mdseCd.getValue());
        params.put("svcMnfModNum", cMsg.svcMnfModNum.getValue());
        // START 2018/05/24 U.Kim [QC#, ADD]
        params.put("serNum", cMsg.serNum.getValue());
        params.put("svcMachTpCdMach", SVC_MACH_TP.MACHINE);
        params.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);
        // END 2018/05/24 U.Kim [QC#, ADD]
        params.put("limitCnt", sMsg.A.length() + 1);

        return params;
    }

}
