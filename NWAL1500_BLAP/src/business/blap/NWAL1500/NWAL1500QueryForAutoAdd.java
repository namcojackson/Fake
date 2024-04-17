/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import static business.blap.NWAL1500.constant.NWAL1500Constant.FORCE_DUMMY_WH;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.MDL_NMTMsg;
import business.db.RTL_SWHTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         S.Takami        Create          S21_NA#3398
 * 2017/10/19   Fujitsu         A.Kosai         Update          S21_NA#21843
 * 2018/05/17   Fujitsu         S.Takami        Update          S21_NA#22988
 * 2018/10/16   Fujitsu         Y.Kanefusa      Update          S21_NA#28772
 *</pre>
 */
public final class NWAL1500QueryForAutoAdd extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForAutoAdd MY_INSTANCE = new NWAL1500QueryForAutoAdd();

    /**
     * Constructor.
     */
    private NWAL1500QueryForAutoAdd() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForAutoAdd getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * Get One RWH
     * @param glblCmpyCd
     * @param invtyLocCd
     * @return
     * </pre>
     */
    public RTL_SWHTMsg getRtlSwhRecByInvtyLocCd(String glblCmpyCd, String invtyLocCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getRtlSwhRecByInvtyLocCd", queryParam);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(rslt.getResultCode())) {
            return (RTL_SWHTMsg) rslt.getResultObject();
        } else {
            return null;
        }
    }

    // 2017/10/19 S21_NA#21843 Add Start
    public MDL_NMTMsg getMdlInfoBySvcConfigMstrPk(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getMdlInfoBySvcConfigMstrPk", queryParam);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(rslt.getResultCode())) {
            return (MDL_NMTMsg) rslt.getResultObject();
        } else {
            return null;
        }
    }
    // 2017/10/19 S21_NA#21843 Add End

    // 2018/05/17 S21_NA#22988 Add Start
    /**
     * <pre>
     * Get Record Count from Line Category Value Set.
     * @param glblCmpyCd Global Company Code
     * @param rtlWhCd Retail Warehouese Code
     * @param dsOrdLineCatgCd DS_Ord_LINE_CATG_CD
     * @return
     */
    public BigDecimal isAvalLineCatgForDefWh(String glblCmpyCd, String rtlWhCd, String dsOrdLineCatgCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("forceDummyWh", FORCE_DUMMY_WH);
        queryParam.put("rtlWhCd", rtlWhCd);
        queryParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("isAvalLineCatgForDefWh", queryParam);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(rslt.getResultCode())) {
            return (BigDecimal) rslt.getResultObject();
        } else {
            return BigDecimal.ZERO;
        }
    }
    // 2018/05/17 S21_NA#22988 Add End
    // QC#28772 2018/10/16 Add Start
    public S21SsmEZDResult getSvcConfig(String glblCmpyCd, BigDecimal svcConfigMstrPk, String slsDate){
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("dsContrDtlStsCanc", DS_CONTR_DTL_STS.CANCELLED);
        queryParam.put("warranty", DS_CONTR_CATG.WARRANTY);
        queryParam.put("slsDate", slsDate);
        return getSsmEZDClient().queryObjectList("getSvcConfig", queryParam);
    }
    // QC#28772 2018/10/16 Add End
}
