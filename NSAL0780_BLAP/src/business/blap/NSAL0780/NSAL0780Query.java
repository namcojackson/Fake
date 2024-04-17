/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0780;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FLEET_CALC_PROC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         A.Kohinata      Create          N/A
 * 2016/04/07   Hitachi         T.Kanasaka      Update          QC#6656
 *</pre>
 */
public final class NSAL0780Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0780Query INSTANCE = new NSAL0780Query();

    /**
     * Constructor.
     */
    private NSAL0780Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0780Query
     */
    public static NSAL0780Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsAcctNm", cMsg.dsAcctNm_H.getValue());
        params.put("dsAcctNum", cMsg.dsAcctNum_H.getValue());
        params.put("serNum", cMsg.serNum_H.getValue());
        params.put("dsContrNum", cMsg.dsContrNum_H.getValue());
        params.put("bllgFromDt", cMsg.bllgFromDt_H.getValue());
        params.put("bllgThruDt", cMsg.bllgThruDt_H.getValue());
        params.put("notComp", cMsg.xxChkBox_H1.getValue());
        params.put("errOnly", cMsg.xxChkBox_H2.getValue());
        params.put("rowNum", cnt);
        // START 2016/04/07 T.Kanasaka [QC#6656, ADD]
        params.put("fleetCalcProcCd_InComplete", FLEET_CALC_PROC.INCOMPLETE);
        params.put("fleetCalcProcCd_ReBillInComplete", FLEET_CALC_PROC.REBILL_INCOMPLETE);
        // END 2016/04/07 T.Kanasaka [QC#6656, ADD]
        return params;
    }
}
