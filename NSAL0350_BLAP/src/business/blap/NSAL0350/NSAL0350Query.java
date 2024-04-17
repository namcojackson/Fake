/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0350;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0350.constant.NSAL0350Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Hitachi         A.Kohinata      Update          N/A
 * 2017/07/05   Hitachi         A.Kohinata      Update          QC#18349
 * 2017/08/07   Hitachi         K.Kitachi       Update          QC#20048
 * 2017/09/08   Hitachi         U.Kim           Update          QC#20071
 * 2017/09/14   Hitachi         K.Kojima        Update          QC#20835
 * 2017/10/17   Hitachi         U.Kim           Update          QC#21699
 * 2018/08/02   Hitachi         K.Kojima        Update          QC#27487
 *</pre>
 */
public final class NSAL0350Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0350Query INSTANCE = new NSAL0350Query();

    /**
     * Constructor.
     */
    private NSAL0350Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0350Query
     */
    public static NSAL0350Query getInstance() {
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
     * getBaseScheduleInfoList
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @param bllgSchdFromDt String
     * @param bllgSchdThruDt String
     * @param dsBllgSchdStsCd String
     * @param skipRecovTpCd String
     * @param rowNum int
     * @param dsContrDtlTpCd
     * @return S21SsmEZDResult
     */
    // START 2017/10/17 U.Kim [QC#21699, MOD]
    // START 2017/08/07 K.Kitachi [QC#20048, MOD]
//    public S21SsmEZDResult getBaseScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, int rowNum) {
    // START 2017/09/12 U.Kim [QC#20071, MOD]
    // public S21SsmEZDResult getBaseScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, String bllgSchdFromDt, String bllgSchdThruDt, String dsBllgSchdStsCd, String skipRecovTpCd, int rowNum) {
    //public S21SsmEZDResult getBaseScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, String bllgSchdFromDt, String bllgSchdThruDt, String dsBllgSchdStsCd, String skipRecovTpCd, int rowNum, String dsContrDtlTpCd) {
    public S21SsmEZDResult getBaseScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, String bllgSchdFromDt, String bllgSchdThruDt, String dsBllgSchdStsCd, String skipRecovTpCd, int rowNum, String dsContrDtlTpCd, String xxChkBox) {
    // START 2017/09/12 U.Kim [QC#20071, MOD]
    // END 2017/08/07 K.Kitachi [QC#20048, MOD]
    // END 2017/10/17 U.Kim [QC#21699, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2015/10/15 [N/A, ADD]
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        // END 2015/10/15 [N/A, ADD]
        // add start 2017/07/05 QC#18349
        ssmParam.put("creditMemo", INV_TP.CREDIT_MEMO);
        // add end 2017/07/05 QC#18349
        // START 2017/09/08 U.Kim [QC#20071, ADD]
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        ssmParam.put("sysSrcCd", SYS_SRC.S21_ORDER);
        ssmParam.put("billWithEq", ZYPCodeDataUtil.getVarCharConstValue(NSAL0350Constant.BILL_WITH_EQUIP_INV_STS_NM, glblCmpyCd));
        String aggLineFlg = ZYPConstant.FLG_OFF_N;
        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            aggLineFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("aggLineFlg", aggLineFlg);
        // END 2017/09/08 U.Kim [QC#20071, ADD]
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        ssmParam.put("bllgSchdFromDt", bllgSchdFromDt);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        ssmParam.put("dsBllgSchdStsCd", dsBllgSchdStsCd);
        ssmParam.put("skipRecovTpCd", skipRecovTpCd);
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        ssmParam.put("rowNum", rowNum);
        // START 2017/10/17 U.Kim [QC#21699, ADD]
        ssmParam.put("xxChkBox", xxChkBox);
        ssmParam.put("dsBllgSchdTpCdReg", DS_BLLG_SCHD_TP.REGULAR);
        ssmParam.put("dsBllgSchdTpCdRebil", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        // END 2017/10/17 U.Kim [QC#21699, ADD]
        return getSsmEZDClient().queryObjectList("getBaseScheduleInfoList", ssmParam);
    }

    /**
     * getUsageScheduleInfoList
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param bllgSchdFromDt String
     * @param bllgSchdThruDt String
     * @param dsBllgSchdStsCd String
     * @param skipRecovTpCd String
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    // START 2017/10/17 U.Kim [QC#21699, MOD]
    // START 2017/08/07 K.Kitachi [QC#20048, MOD]
//    public S21SsmEZDResult getUsageScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk, int rowNum) {
    //public S21SsmEZDResult getUsageScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk, String bllgSchdFromDt, String bllgSchdThruDt, String dsBllgSchdStsCd, String skipRecovTpCd, int rowNum) {
    // START 2018/08/02 K.Kojima [QC#27487,MOD]
    // public S21SsmEZDResult getUsageScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk, String bllgSchdFromDt, String bllgSchdThruDt, String dsBllgSchdStsCd, String skipRecovTpCd, int rowNum, String xxChkBox) {
    public S21SsmEZDResult getUsageScheduleInfoList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk,
            BigDecimal dsContrBllgMtrPk, String bllgSchdFromDt, String bllgSchdThruDt, String dsBllgSchdStsCd, String skipRecovTpCd,
            int rowNum, String xxChkBox, String dsContrDtlTpCd) {
    // END 2018/08/02 K.Kojima [QC#27487,MOD]
    // END 2017/08/07 K.Kitachi [QC#20048, MOD]
    // END 2017/10/17 U.Kim [QC#21699, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2015/10/15 [N/A, ADD]
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        // END 2015/10/15 [N/A, ADD]
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        ssmParam.put("bllgSchdFromDt", bllgSchdFromDt);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        ssmParam.put("dsBllgSchdStsCd", dsBllgSchdStsCd);
        ssmParam.put("skipRecovTpCd", skipRecovTpCd);
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        // START 2017/09/14 K.Kojima [QC#20835,ADD]
        ssmParam.put("creditMemo", INV_TP.CREDIT_MEMO);
        // END 2017/09/14 K.Kojima [QC#20835,ADD]
        ssmParam.put("rowNum", rowNum);
        // START 2017/10/17 U.Kim [QC#21699, ADD]
        ssmParam.put("xxChkBox", xxChkBox);
        ssmParam.put("dsBllgSchdTpCdReg", DS_BLLG_SCHD_TP.REGULAR);
        ssmParam.put("dsBllgSchdTpCdRebil", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        // END 2017/10/17 U.Kim [QC#21699, ADD]
        // START 2018/08/02 K.Kojima [QC#27487,ADD]
        String aggLineFlg = ZYPConstant.FLG_OFF_N;
        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            aggLineFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("aggLineFlg", aggLineFlg);
        // END 2018/08/02 K.Kojima [QC#27487,ADD]
        return getSsmEZDClient().queryObjectList("getUsageScheduleInfoList", ssmParam);
    }

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

}
