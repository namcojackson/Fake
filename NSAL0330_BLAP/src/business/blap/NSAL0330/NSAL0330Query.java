/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0330;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/21   Hitachi         T.Tomita        Update          N/A
 * 2016/11/17   Hitachi         T.Tomita        Update          QC#15942
 * 2017/10/25   Hitachi         K.Ochiai        Update          QC#22076
 * 2017/12/11   Hitachi         U.Kim           Update          QC#18779
 *</pre>
 */
public final class NSAL0330Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0330Query INSTANCE = new NSAL0330Query();

    /**
     * Constructor.
     */
    private NSAL0330Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0330Query
     */
    public static NSAL0330Query getInstance() {
        return INSTANCE;
    }

    // START 2015/10/21 T.Tomita [N/A, MOD]
    /**
     * getDsContrDtlInfo
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlInfo(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return getSsmEZDClient().queryObjectList("getDsContrDtlInfo", ssmParam);
    }
    // END 2015/10/21 T.Tomita [N/A, MOD]

    /**
     * getSvcMemo
     * @param glblCmpyCd String
     * @param dsContrDtlPk String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMemo(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("svcMemoTpCd", SVC_MEMO_TP.CHANGE_VIEW_PRICING);
        return getSsmEZDClient().queryObjectList("getSvcMemo", ssmParam);
    }

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
        // START 2017/10/25 K.Ochiai [QC#22076, DEL]
//        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REGULAR);
        // END 2017/10/25 K.Ochiai [QC#22076, DEL]
        return getSsmEZDClient().queryObjectList("getScheduleInfoList", ssmParam);
    }

    // START 2016/11/17 T.Tomita [QC#15942, ADD]
    /**
     * getDsContrDtlPkListByAggMach
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlPkListByAggMach(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        List<String> dsContrDtlStsCdList = new ArrayList<String>();
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return getSsmEZDClient().queryObjectList("getDsContrDtlPkListByAggMach", ssmParam);
    }

    /**
     * getDsContrDtlPkListByAgg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlPkListByAgg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("dsContrDtlTpCdIsAgg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        List<String> dsContrDtlTpCdList = new ArrayList<String>();
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.AGGREGATE);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.ACCESSORIES);
        ssmParam.put("dsContrDtlTpCdList", dsContrDtlTpCdList);
        List<String> dsContrDtlStsCdList = new ArrayList<String>();
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return getSsmEZDClient().queryObjectList("getDsContrDtlPkListByAgg", ssmParam);
    }
    // END 2016/11/17 T.Tomita [QC#15942, ADD]
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
