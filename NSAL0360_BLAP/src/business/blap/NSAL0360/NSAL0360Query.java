/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0360;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_CONTR_PRC_EFFTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2016/05/13   Hitachi         T.Kanasaka      Update          QC#7916
 * 2017/12/12   Hitachi         U.Kim           Update          QC#18779
 *</pre>
 */
public final class NSAL0360Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0360Query INSTANCE = new NSAL0360Query();

    /**
     * Constructor.
     */
    private NSAL0360Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0360Query
     */
    public static NSAL0360Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsContrDtlInfo
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlInfo(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getDsContrDtlInfo", ssmParam);
    }

    /**
     * getSvcMemo
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMemo(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("svcMemoTpCd", SVC_MEMO_TP.CHANGE_VIEW_PRICING);
        return getSsmEZDClient().queryObjectList("getSvcMemo", ssmParam);
    }

    // START 2016/05/13 T.Kanasaka [QC#7916, MOD]
    /**
     * getScheduleInfoList
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return getSsmEZDClient().queryObjectList("getScheduleInfoList", ssmParam);
    }
    // END 2016/05/13 T.Kanasaka [QC#7916, MOD]

    /**
     * getMtrInfoList
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getMtrInfoList", ssmParam);
    }

    // START 2016/05/13 T.Kanasaka [QC#7916, MOD]
    /**
     * getContrXsCopyList
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrXsCopyList(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return getSsmEZDClient().queryObjectList("getContrXsCopyList", ssmParam);
    }
    // END 2016/05/13 T.Kanasaka [QC#7916, MOD]

    // START 2016/05/13 T.Kanasaka [QC#7916, ADD]
    /**
     * getDsContrPrcEff
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsg
     */
    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // END 2016/05/13 T.Kanasaka [QC#7916, ADD]
 // START 2017/12/11 U.Kim [QC#18779, ADD]
    /**
     * getScheduleInfoList
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgDays(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("flgOn", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getBllgDays", ssmParam);
    }

    // END 2017/12/11 U.Kim [QC#18779, ADD]
}
