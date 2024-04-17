/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0910;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * CFS Invoice Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public final class NSAL0910Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0910Query INSTANCE = new NSAL0910Query();

    /**
     * Constructor.
     */
    private NSAL0910Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0910Query
     */
    public static NSAL0910Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0910CMsg
     * @param sMsg NSAL0910SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
    }

    private static Map<String, Object> getSsmParam(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsAcctDlrCd", cMsg.dsAcctDlrCd.getValue());
        params.put("procDtFrom", cMsg.procDt_FR.getValue());
        params.put("procDtTo", cMsg.procDt_TO.getValue());
        params.put("cfsInvProcStsCd", cMsg.cfsInvProcStsCd.getValue());
        params.put("cratDtFrom", cMsg.xxCratDt_FR.getValue());
        params.put("cratDtTo", cMsg.xxCratDt_TO.getValue());
        params.put("csaInvNum", cMsg.csaInvNum.getValue());
        params.put("cfsSerNum", cMsg.cfsSerNum.getValue());
        params.put("csaContrNum", cMsg.csaContrNum.getValue());
        params.put("rowNum", cnt);
        return params;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0910CMsg
     * @param asMsg NSAL0910_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvData(NSAL0910CMsg cMsg, NSAL0910_ASMsg asMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("invNum", asMsg.csaInvNum_A.getValue());
        params.put("dsContrNum", asMsg.csaContrNum_A.getValue());
        return getSsmEZDClient().queryObject("getInvData", params);
    }

    /**
     * getSearchData
     * @param cMsg NSAL0910CMsg
     * @param asMsg NSAL0910_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrData(NSAL0910CMsg cMsg, NSAL0910_ASMsg asMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("serNum", asMsg.cfsSerNum_A.getValue());
        params.put("dsContrNum", asMsg.csaContrNum_A.getValue());
        params.put("dsContrCatgCdIsWty", DS_CONTR_CATG.WARRANTY);
        return getSsmEZDClient().queryObject("getContrData", params);
    }

}
