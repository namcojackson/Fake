/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         S.Takami        Create          S21_NA#2336
 * 2016/04/05   Fujitsu         S.Takami        Update          S21_NA#5519-2
 *</pre>
 */
public final class NWAL1500QueryForDelete extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForDelete MY_INSTANCE = new NWAL1500QueryForDelete();

    /**
     * Constructor.
     */
    private NWAL1500QueryForDelete() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForDelete getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getDsCpoSlsCrList(DS_CPO_CONFIGTMsg configTMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", configTMsg.glblCmpyCd.getValue());
        queryParam.put("dsCpoConfigPk", configTMsg.dsCpoConfigPk.getValue());
        queryParam.put("cpoOrdNum", configTMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", configTMsg.dsOrdPosnNum.getValue());

        return getSsmEZDClient().queryObjectList("getDsCpoSlsCrList", queryParam);
    }

    public S21SsmEZDResult getOrdPrcCalBaseList(CPO_DTLTMsg cpoDtlTMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", cpoDtlTMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", cpoDtlTMsg.cpoOrdNum.getValue());
        queryParam.put("cpoDtlLineNum", cpoDtlTMsg.cpoDtlLineNum.getValue());
        queryParam.put("cpoDtlLineSubNum", cpoDtlTMsg.cpoDtlLineSubNum.getValue());

        return getSsmEZDClient().queryObjectList("getOrdPrcCalBaseList", queryParam);
    }

    public S21SsmEZDResult getCpoRtrnPrcCalBaseList(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", dsCpoRtrnDtlTMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", dsCpoRtrnDtlTMsg.cpoOrdNum.getValue());
        queryParam.put("dsCpoRtrnLineNum", dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue());
        queryParam.put("dsCpoRtrnLineSubNum", dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue());

        return getSsmEZDClient().queryObjectList("getCpoRtrnPrcCalBaseList", queryParam);
    }

	// 2016/04/05 S21_NA#5519-2 Add Start
    public S21SsmEZDResult getSvcMachMstrHist(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);

        return getSsmEZDClient().queryObjectList("getSvcMachMstrHist", queryParam);
    }
    // 2016/04/05 S21_NA#5519-2 Add End
}
