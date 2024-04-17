/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0410;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NSAL0410.constant.NSAL0410Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 * 2015/12/02   Hitachi         T.Tomita        Update          QC#1244
 * 2015/12/02   Hitachi         T.Tomita        Update          QC#1319
 * 2016/02/16   Hitachi         K.Kasai         Update          QC#3021
 * 2016/05/24   Hitachi         Y.Takeno        Update          QC#447
 * 2016/09/26   Hitachi         K.Kishimoto     Update          QC#14428
 * 2018/05/24   Hitachi         K.Kitachi       Update          QC#26223
 *</pre>
 */
public final class NSAL0410Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSAL0410Query MY_INSTANCE = new NSAL0410Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSAL0410Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSAL0410Query instance.
     * </pre>
     * @return NSAL0410Query instance
     */
    public static NSAL0410Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getHeader
     * @param cMsg NSAL0410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeader(NSAL0410CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        ssmParam.put("effThruDt", NSAL0410Constant.MAX_DT_VAL);
        ssmParam.put("slsDt", cMsg.slsDt_P.getValue());

        return getSsmEZDClient().queryEZDMsg("getHeader", ssmParam, cMsg);
    }

    /**
     * Search DS_CONTR_ADDL_CHRG
     * @param cMsg NSAL0410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrAddlChrg(NSAL0410CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
            ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk_P.getValue());
        }
        // add start 2016/02/15 CSA Defect#3021
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        // add end 2016/02/15 CSA Defect#3021

        return getSsmEZDClient().queryEZDMsgArray("getDsContrAddlChrg", ssmParam, cMsg.A);
    }

    /**
     * getDefaultBllgCycle
     * @param cMsg NSAL0410CMsg
     * @return defBllgCycle String
     */
    public S21SsmEZDResult getDefaultBllgCycle(NSAL0410CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
            ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk_P.getValue());
        }
        return getSsmEZDClient().queryEZDMsg("getDefaultBllgCycle", ssmParam, cMsg);
    }

    /**
     * getSerNum
     * @param cMsg NSAL0410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNum(NSAL0410CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
            ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk_P.getValue());
        }
        return getSsmEZDClient().queryObjectList("getSerNum", ssmParam);
    }

    // add start 2016/02/15 CSA Defect#3021
    /**
     * getFleetLine
     * @param cMsg NSAL0410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFleetLine(NSAL0410CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        return getSsmEZDClient().queryObjectList("getFleetLine", ssmParam);
    }
    // add end 2016/02/15 CSA Defect#3021

    /**
     * Search this CONTR ADDL_CHRG_TP
     * @param cMsg NSAL0410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getThisContrEffAddlChrgTp(NSAL0410CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
            ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk_P.getValue());
        }

        return getSsmEZDClient().queryObjectList("getThisContrEffAddlChrgTp", ssmParam);
    }

    /**
     * get SVC_BILL_BY_TP Code table;
     * @param cMsg NSAL0410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcBillByTpTable(NSAL0410CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryEZDMsgArray("getSvcBillByTpTable", ssmParam, cMsg.T);
    }

    /**
     * getBaseLeaseCmpyCnt
     * @param cMsg NSAL0410CMsg
     * @param row int
     * @return BigDecimal
     */
    public BigDecimal getBaseLeaseCmpyCnt(NSAL0410CMsg cMsg, int row) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.A.no(row).dsContrDtlPk_A)) {
            ssmParam.put("dsContrDtlPk", cMsg.A.no(row).dsContrDtlPk_A.getValue());
        }

        return (BigDecimal) getSsmEZDClient().queryObject("getBaseLeaseCmpyCnt", ssmParam).getResultObject();
    }

    /**
     * getUsageLeaseCmpyCnt
     * @param cMsg NSAL0410CMsg
     * @param row int
     * @return BigDecimal
     */
    public BigDecimal getUsageLeaseCmpyCnt(NSAL0410CMsg cMsg, int row) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.A.no(row).dsContrDtlPk_A)) {
            ssmParam.put("dsContrDtlPk", cMsg.A.no(row).dsContrDtlPk_A.getValue());
        }

        return (BigDecimal) getSsmEZDClient().queryObject("getUsageLeaseCmpyCnt", ssmParam).getResultObject();
    }

    /**
     * getAddlChrgInv
     * @param cMsg NSAL0410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddlChrgInv(NSAL0410CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
            ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk_P.getValue());
        }
        // START 2015/12/02 T.Tomita [QC#1244, ADD]
        ssmParam.put("svcBillByTpCd", SVC_BILL_BY_TP.USAGE);
        // END 2015/12/02 T.Tomita [QC#1244, ADD]
        ssmParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("rowNum", cMsg.B.length());

        return getSsmEZDClient().queryEZDMsgArray("getAddlChrgInv", ssmParam, cMsg.B);
    }

    // START 2015/12/02 T.Tomita [QC#1319, ADD]
    /**
     * getMachSvcBillByTp
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getMachSvcBillByTp() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("svcBillByTpCd", SVC_BILL_BY_TP.CONTRACT);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getMachSvcBillByTp", ssmParam).getResultObject();
    }
    // END 2015/12/02 T.Tomita [QC#1319, ADD]

    // START 2016/05/24 [QC#447, ADD]
    /**
     * getSlaTime
     * @param addlChrgTpCd addlChrgTpCd
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSlaTime(String addlChrgTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("addlChrgTpCd", addlChrgTpCd);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSlaTime", ssmParam).getResultObject();
    }
    // END   2016/05/24 [QC#447, ADD]

    // Add Start 2016/09/26 <QC#14428>
    /**
     * getAddlChrgTpV
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getAddlChrgTpV() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAddlChrgTpV", ssmParam).getResultObject();
    }
    // Add End   2016/09/26 <QC#14428>

    // START 2017/12/26 K.Kojima [QC#18562,ADD]
    /**
     * getBllgCycleForBase
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return String
     */
    public String getBllgCycleForBase(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        }
        return (String) getSsmEZDClient().queryObject("getBllgCycleForBase", ssmParam).getResultObject();
    }

    /**
     * getBllgCycleForUsage
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return String
     */
    public String getBllgCycleForUsage(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        }
        return (String) getSsmEZDClient().queryObject("getBllgCycleForUsage", ssmParam).getResultObject();
    }

    // END 2017/12/26 K.Kojima [QC#18562,ADD]

    // START 2018/05/23 K.Kitachi [QC#26223, ADD]
    /**
     * countInvSchd
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countInvSchd(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        }
        return (BigDecimal) getSsmEZDClient().queryObject("countInvSchd", ssmParam).getResultObject();
    }
    // END 2018/05/23 K.Kitachi [QC#26223, ADD]
}
