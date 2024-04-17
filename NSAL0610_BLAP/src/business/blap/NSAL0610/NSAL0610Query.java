/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0610;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static business.blap.NSAL0610.constant.NSAL0610Constant.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto         Create          N/A
 * 2016/05/11   Hitachi         T.Iwamoto         Update          QC#4425
 * 2017/06/19   Hitachi         Y.Osawa           Update          QC#19256
 *</pre>
 */
public final class NSAL0610Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0610Query INSTANCE = new NSAL0610Query();

    /**
     * Private constructor
     */
    private NSAL0610Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0610Query singleton instance
     */
    public static NSAL0610Query getInstance() {
        return INSTANCE;
    }

    /**
     * getMainData
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMainData(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
        params.put("limitNum", sMsg.B.length() + 1);
        params.put("dsContrDtlTpCd_Acc", DS_CONTR_DTL_TP.ACCESSORIES);
        params.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCd_Agg", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("mtrLb_Base", "Base");
        params.put("level_1", LEVEL_1);
        params.put("level_2", LEVEL_2);
        params.put("level_3", LEVEL_3);
        params.put("level_4", LEVEL_4);
        params.put("level_5", LEVEL_5);
        params.put("flgOffN", ZYPConstant.FLG_OFF_N);
        // START 2017/06/19 Y.Osawa [QC#19256,ADD]
        params.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 Y.Osawa [QC#19256,ADD]
        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.B);
    }
//
//    /**
//     * getTodayMaxSeq
//     * @param slsDt String
//     * @return maxSeq +1
//     */
//    public Integer getTodayMaxSeq(String slsDt) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("slsDt", slsDt);
//        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getTodayMaxSeq", params);
//        if (ssmResult.getQueryResultCount() < 1) {
//            return 1;
//        }
//        return (Integer) ssmResult.getResultObject() + 1;
//    }
//
//    /**
//     * getRegularData
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610SMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getContractIfData(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
//        params.put("dsContrDtlPk", (BigDecimal) osMsg.dsContrDtlPk_N.getValue());
//        params.put("ifDtlTpBase", CONTR_INTFC_DTL_TP.BASE);
//        params.put("ifDtlTpUsage", CONTR_INTFC_DTL_TP.USAGE);
//        params.put("level3", LEVEL_3);
//        params.put("base", BASE);
//        params.put("overage", OVERAGE);
//        if (DS_CONTR_DTL_TP.FLEET.equals(osMsg.dsContrDtlTpCd_N.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(osMsg.dsContrDtlTpCd_N.getValue())) {
//            params.put("dsContrDtlTp", (String) osMsg.dsContrDtlTpCd_N.getValue());
//        }
//
//        return getSsmEZDClient().queryObjectList("getContractIfData", params);
//    }
//
//    /**
//     * getAccessoriesData
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610SMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getAccessoriesData(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
//        params.put("dsContrDtlPk", (BigDecimal) osMsg.dsContrDtlPk_N.getValue());
//        params.put("dsContrDtlTp", DS_CONTR_DTL_TP.ACCESSORIES);
//        params.put("ifDtlTpBase", CONTR_INTFC_DTL_TP.BASE);
//        params.put("level2", LEVEL_2);
//
//        return getSsmEZDClient().queryObjectList("getAccessoriesData", params);
//    }
//
//    /**
//     * getAdditionalChargeForContract
//     * @param cMsg NSAL0610CMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getAdditionalChargeForContract(NSAL0610CMsg cMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
//
//        return getSsmEZDClient().queryObjectList("getAdditionalChargeForContract", params);
//    }
//
//    /**
//     * getAdditionalChargeForDetail
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getAdditionalChargeForDetail(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
//        params.put("dsContrDtlPk", (BigDecimal) osMsg.dsContrDtlPk_N.getValue());
//
//        return getSsmEZDClient().queryObjectList("getAdditionalChargeForDetail", params);
//    }
//
//    /**
//     * getPhysicalBillingMeterRelation
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getPhysicalBillingMeterRelation(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
//        params.put("dsContrDtlPk", (BigDecimal) osMsg.dsContrDtlPk_N.getValue());
//
//        return getSsmEZDClient().queryObjectList("getPhysicalBillingMeterRelation", params);
//    }
//
//    /**
//     * getExcessCopy
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getExcessCopy(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
//        params.put("dsContrDtlPk", (BigDecimal) osMsg.dsContrDtlPk_N.getValue());
//
//        return getSsmEZDClient().queryObjectList("getExcessCopy", params);
//    }

    /**
     * getDetailInfo
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailInfo(BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", dsContrPk);

        return getSsmEZDClient().queryObjectList("getDetailInfo", params);
    }

    /**
     * getNextThruDt
     * @param nextFromDt String
     * @param oldFromDt String
     * @param oldThruDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNextThruDt(String nextFromDt, String oldFromDt, String oldThruDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nextFromDt", nextFromDt);
        params.put("oldFromDt", oldFromDt);
        params.put("oldThruDt", oldThruDt);
        params.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);

        return getSsmEZDClient().queryObject("getNextThruDt", params);
    }
}
