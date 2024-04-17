/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0760;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2017/08/23   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/05/31   Fujitsu         S.Ohki          Update          QC#24747
 *</pre>
 */
public final class NFCL0760Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL0760Query INSTANCE = new NFCL0760Query();

    /**
     * Constructor.
     */
    private NFCL0760Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL0760Query
     */
    public static NFCL0760Query getInstance() {
        return INSTANCE;
    }

    /**
     * getHeaderData
     * @param cMsg NFCL0760CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderData(NFCL0760CMsg cMsg) {
    	// START 2018/05/31 S.Ohki [QC#24747,MOD]
//    	return getSsmEZDClient().queryEZDMsg("getHeaderData", cMsg, cMsg);
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", cMsg);
        return getSsmEZDClient().queryEZDMsg("getHeaderData", params, cMsg);
        // END 2018/05/31 S.Ohki [QC#24747,MOD]
    }

    /**
     * getDetailData
     * @param cMsg NFCL0760CMsg
     * @param sMsg NFCL0760SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailData(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getDetailData", getSsmParam(cMsg, cnt), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NFCL0760CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NFCL0760CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", cMsg);
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        params.put("arWrtOffRqstTpCdIsSystem", AR_WRT_OFF_RQST_TP.SYSTEM);
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        params.put("limitCnt", cnt);
        return params;
    }

}
