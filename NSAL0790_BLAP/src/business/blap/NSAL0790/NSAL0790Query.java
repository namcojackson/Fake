/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0790;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FLEET_CALC_PROC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/10   Hitachi         K.Kitachi       Update          QC#17752
 * 2017/06/06   Hitachi         A.Kohinata      Update          QC#18770
 *</pre>
 */
public final class NSAL0790Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0790Query INSTANCE = new NSAL0790Query();

    /**
     * Constructor.
     */
    private NSAL0790Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0790Query
     */
    public static NSAL0790Query getInstance() {
        return INSTANCE;
    }

    /**
     * getHeaderInfo
     * @param cMsg NSAL0790CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfo(NSAL0790CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // START 2017/03/10 K.Kitachi [QC#17752, MOD]
//        params.put("fleetReadRollUpPk", cMsg.fleetReadRollUpPk_H.getValue());
        params.put("fleetReadRollUpPkList", getFleetReadRollUpPkList(cMsg));
        params.put("fleetCalcProcIsInComplete", FLEET_CALC_PROC.INCOMPLETE);
        params.put("fleetCalcProcIsReBillInComplete", FLEET_CALC_PROC.REBILL_INCOMPLETE);
        params.put("fleetCalcProcIsReBillComplete", FLEET_CALC_PROC.REBILL_COMPLETE);
        params.put("fleetCalcProcIsComplete", FLEET_CALC_PROC.COMPLETE);
        // END 2017/03/10 K.Kitachi [QC#17752, MOD]

        return getSsmEZDClient().queryEZDMsg("getHeaderInfo", params, cMsg);
    }

    /**
     * getDetailInfo
     * @param cMsg NSAL0790CMsg
     * @param sMsg NSAL0790SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailInfo(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // START 2017/03/10 K.Kitachi [QC#17752, MOD]
//        params.put("fleetReadRollUpPk", cMsg.fleetReadRollUpPk_H.getValue());
        params.put("fleetReadRollUpPkList", getFleetReadRollUpPkList(cMsg));
        // END 2017/03/10 K.Kitachi [QC#17752, MOD]
        params.put("rowNum", cnt);

        return getSsmEZDClient().queryEZDMsgArray("getDetailInfo", params, sMsg.A);
    }

    // START 2017/03/10 K.Kitachi [QC#17752, ADD]
    private List<BigDecimal> getFleetReadRollUpPkList(NSAL0790CMsg cMsg) {
        List<BigDecimal> fleetReadRollUpPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            fleetReadRollUpPkList.add(cMsg.P.no(i).fleetReadRollUpPk_P.getValue());
        }
        return fleetReadRollUpPkList;
    }
    // END 2017/03/10 K.Kitachi [QC#17752, ADD]

    // add start 2017/06/06 CSA Defect#18770
    /**
     * getNewFleetReadRollUpPk
     * @param cMsg NSAL0790CMsg
     * @param fleetReadRollUpPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getNewFleetReadRollUpPk(NSAL0790CMsg cMsg, BigDecimal fleetReadRollUpPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("fleetReadRollUpPk", fleetReadRollUpPk);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getNewFleetReadRollUpPk", params);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * getNotEntryCnt
     * @param cMsg NSAL0790CMsg
     * @return BigDecimal
     */
    public BigDecimal getNotEntryCnt(NSAL0790CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("fleetReadRollUpPkList", getFleetReadRollUpPkList(cMsg));
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getNotEntryCnt", params);
        return (BigDecimal) result.getResultObject();
    }
    // add end 2017/06/06 CSA Defect#18770
}
