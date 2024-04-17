/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0340;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/09   Fujitsu         T.Yoshida       Create          N/A
 * 2017/09/13   Hitachi         K.Kim           Update          QC#19938
 * 2018/03/09   Hitachi         K.Kojima        Update          QC#23600
 *</pre>
 */
public final class NSAL0340Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0340Query INSTANCE = new NSAL0340Query();

    /**
     * Private constructor
     */
    private NSAL0340Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0340Query singleton instance
     */
    public static NSAL0340Query getInstance() {
        return INSTANCE;
    }

    /**
     * check exist SVC_INV_CHRG_TP
     * @param cMsg NSAL0340CMsg
     * @return true: exist SVC_INV_CHRG_TP
     */
    public boolean isExsistSvcInvChrgTp(NSAL0340CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcInvChrgTpCd", cMsg.svcInvChrgTpCd.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExsistSvcInvChrgTp", params);

        return (Integer) result.getResultObject() > 0;
    }

    /**
     * get Header Data
     * @param cMsg NSAL0340CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHdrData(NSAL0340CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());

        String svcInvChrgTpCd = cMsg.svcInvChrgTpCd.getValue();
        if (ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            params.put("svcInvChrgTpCd", svcInvChrgTpCd);
        } else {
            params.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        }

        return getSsmEZDClient().queryEZDMsg("getHdrData", params, cMsg);
    }

    /**
     * get Detail Data
     * @param cMsg NSAL0340CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDtlData(NSAL0340CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        params.put("none", SKIP_RECOV_TP.NONE);

        String svcInvChrgTpCd = cMsg.svcInvChrgTpCd.getValue();
        if (ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            params.put("svcInvChrgTpCd", svcInvChrgTpCd);
        } else {
            params.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        }

        return getSsmEZDClient().queryEZDMsgArray("getDtlData", params, cMsg.A);
    }

    /**
     * check exist DS_CONTR_SKIP_RECOV
     * @param cMsg NSAL0340CMsg
     * @param acMsg NSAL0340_ACMsg
     * @param svcInvChrgTpCd Service Invoice Charge Type Code
     * @return true: exist
     */
    public boolean isExistSkipRecovInfo(NSAL0340CMsg cMsg, NSAL0340_ACMsg acMsg, String svcInvChrgTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        params.put("skipRecovMth", acMsg.skipRecovMth_A0.getValue());
        params.put("svcInvChrgTpCd", svcInvChrgTpCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistSkipRecovInfo", params);

        return (Integer) result.getResultObject() > 0;
    }

    // START 2017/09/13 K.Kim [QC#19938, ADD]
    /**
     * get Bill With Equipment Flg DS_CONTR_DTL
     * @param cMsg NSAL0340CMsg
     * @return String
     */
    public String getBillWithEquipmentFlg(NSAL0340CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getBillWithEquipmentFlg", params);

        return (String) result.getResultObject();
    }

    /**
     * check Billing Shedule Status Close DS_CONTR_BLLG_SCHD
     * @param cMsg NSAL0340CMsg
     * @param svcInvChrgTpCd Service Invoice Charge Type Code
     * @return true: closed
     */
    public boolean isBillSchdStsClsInfo(NSAL0340CMsg cMsg, String svcInvChrgTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        params.put("dsBllgSchdStsCdCl", DS_BLLG_SCHD_STS.CLOSE);
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
            params.put("BaseChargeFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("BaseChargeFlg", ZYPConstant.FLG_OFF_N);
        }
        S21SsmEZDResult result = getSsmEZDClient().queryObject("isBillSchdStsClsInfo", params);

        return (Integer) result.getResultObject() == 0;
    }
    // END 2017/09/13 K.Kim [QC#19938, ADD]

    // START 2018/03/09 K.Kojima [QC#23600,ADD]
    /**
     * check Billing Shedule Status Close DS_CONTR_BLLG_SCHD
     * @param cMsg NSAL0340CMsg
     * @param svcInvChrgTpCd Service Invoice Charge Type Code
     * @return true: closed
     */
    public List<BigDecimal> getChildDsContrDtlPkForAgg(NSAL0340CMsg cMsg, String svcInvChrgTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        params.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
            params.put("BaseChargeFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("BaseChargeFlg", ZYPConstant.FLG_OFF_N);
        }
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getChildDsContrDtlPkForAgg", params);
        if (result.isCodeNormal()) {
            return (List<BigDecimal>) result.getResultObject();
        } else {
            return new ArrayList<BigDecimal>();
        }
    }
    // END 2018/03/09 K.Kojima [QC#23600,ADD]
}
