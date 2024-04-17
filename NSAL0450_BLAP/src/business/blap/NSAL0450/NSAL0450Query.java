/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0450;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Hitachi         J.Kim           Create          N/A
 * 2016/02/23   Hitachi         T.Tsuchida      Update          QC#1797
 * 2019/01/21   Fujitsu         R.Nakamura      Update          QC#29782
 *</pre>
 */
public final class NSAL0450Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSAL0450Query INSTANCE = new NSAL0450Query();

    /**
     * Private constructor
     */
    private NSAL0450Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0450Query
     */
    public static NSAL0450Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDSContractInfo
     * @param bizMsg NSAL0450CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDSContractInfo(NSAL0450CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk_H);
        return getSsmEZDClient().queryObjectList("getDSContractInfo", params);
    }

    /**
     * getDSContractInfo
     * @param bizMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     * @param searchLimitCount int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchNonFleetMachineInfo(NSAL0450CMsg bizMsg, NSAL0450SMsg sMsg, int searchLimitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk_H.getValue());
        params.put("svcInvChrgTpCdIsBc", SVC_INV_CHRG_TP.BASE_CHARGE);
        params.put("svcInvChrgTpCdIsMc", SVC_INV_CHRG_TP.METER_CHARGE);
        params.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        params.put("limitCount", searchLimitCount);
        // Add Start 2019/01/21 QC#29782
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // Add End 2019/01/21 QC#29782
        return getSsmEZDClient().queryObjectList("getNonFleetMachineInfo", params);
    }

    /**
     * getDSContractInfo
     * @param bizMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     * @param searchLimitCount int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchFleetMachineInfo(NSAL0450CMsg bizMsg, NSAL0450SMsg sMsg, int searchLimitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk_H.getValue());
        params.put("svcInvChrgTpCdIsBc", SVC_INV_CHRG_TP.BASE_CHARGE);
        params.put("svcInvChrgTpCdIsMc", SVC_INV_CHRG_TP.METER_CHARGE);
        params.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        params.put("dsContrDtlTpCdIsFleet", DS_CONTR_DTL_TP.FLEET);
        params.put("limitCount", searchLimitCount);
        return getSsmEZDClient().queryObjectList("getFleetMachineInfo", params);
    }

    /**
     * getDSContractInfo
     * @param bizMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     * @param searchLimitCount int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchAggregateMachineInfo(NSAL0450CMsg bizMsg, NSAL0450SMsg sMsg, int searchLimitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk_H.getValue());
        params.put("svcInvChrgTpCdIsBc", SVC_INV_CHRG_TP.BASE_CHARGE);
        params.put("svcInvChrgTpCdIsMc", SVC_INV_CHRG_TP.METER_CHARGE);
        params.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        params.put("dsContrDtlTpCdIsAgg", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("limitCount", searchLimitCount);
        // Add Start 2019/01/21 QC#29782
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // Add End 2019/01/21 QC#29782
        return getSsmEZDClient().queryObjectList("getAggregateMachineInfo", params);
    }
}
