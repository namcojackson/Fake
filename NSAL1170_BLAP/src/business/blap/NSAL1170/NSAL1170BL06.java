package business.blap.NSAL1170;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import static business.blap.NSAL1170.constant.NSAL1170Constant.*;
import business.blap.NSAL1170.common.NSAL1170CommonLogic;
import business.db.DS_MDL_ESCL_BLLG_MTRTMsg;
import business.db.DS_MDL_ESCL_RULETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Model Escalation Rules Maintenance Popup.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Hitachi         T.Nishimura        Create          N/A
 *</pre>
 */
public class NSAL1170BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        String screenAplId = cMsg.getScreenAplID();
        if ("NSAL1170Scrn00_CMN_Submit".equals(screenAplId)) {
            doProcess_NSAL1170_CmnSubmit((NSAL1170CMsg) cMsg, (NSAL1170SMsg) sMsg);
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
        }
        return;

    }

    private void doProcess_NSAL1170_CmnSubmit(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        NSAL1170CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
        if (!NSAL1170CommonLogic.validationCheck(cMsg, sMsg)) {
            return;
        }

        List<NSAL1170_ASMsg> aSMsgListIns = new ArrayList<NSAL1170_ASMsg>();
        List<DS_MDL_ESCL_RULETMsg> dsMdlEsclRuleListUpd = new ArrayList<DS_MDL_ESCL_RULETMsg>();
        List<DS_MDL_ESCL_RULETMsg> dsMdlEsclRuleListDel = new ArrayList<DS_MDL_ESCL_RULETMsg>();

        List<DS_MDL_ESCL_BLLG_MTRTMsg> dsMdlEsclBllgMtrListUpd = new ArrayList<DS_MDL_ESCL_BLLG_MTRTMsg>();
        List<DS_MDL_ESCL_BLLG_MTRTMsg> dsMdlEsclBllgMtrListDel = new ArrayList<DS_MDL_ESCL_BLLG_MTRTMsg>();

        Map<BigDecimal, DS_MDL_ESCL_RULETMsg> dsMdlEsclRuleMap = new HashMap<BigDecimal, DS_MDL_ESCL_RULETMsg>();

        // delete
        for (int i = 0; i < sMsg.X.getValidCount(); i++) {
            NSAL1170_XSMsg xSMsg = sMsg.X.no(i);
            if (!dsMdlEsclRuleMap.containsKey(xSMsg.dsMdlEsclRulePk_X.getValue())) {
                DS_MDL_ESCL_RULETMsg dsMdlEsclRule = checkDsMdlEsclRuleDel(cMsg, xSMsg);
                if (dsMdlEsclRule == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                dsMdlEsclRuleMap.put(xSMsg.dsMdlEsclRulePk_X.getValue(), dsMdlEsclRule);
                dsMdlEsclRuleListDel.add(dsMdlEsclRule);
            }
            if (hasValue(xSMsg.dsMdlEsclBllgMtrPk_X)) {
                DS_MDL_ESCL_BLLG_MTRTMsg dsMdlEsclBllgMtr = checkDsMdlEsclBllgMtrDel(cMsg, xSMsg);
                if (dsMdlEsclBllgMtr == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                dsMdlEsclBllgMtrListDel.add(dsMdlEsclBllgMtr);
            }
        }

        // insert,update
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1170_ASMsg aSMsg = sMsg.A.no(i);
            if (!hasValue(aSMsg.dsMdlEsclBllgMtrPk)) {
                if (BigDecimal.ONE.equals(aSMsg.xxRowNum_D.getValue()) && ZYPConstant.FLG_OFF_N.equals(aSMsg.mtrReqMdlFlg.getValue()) && hasValue(aSMsg.dsMdlEsclRulePk)) {
                    DS_MDL_ESCL_RULETMsg dsMdlEsclRule = checkDsMdlEsclRuleUpd(cMsg, aSMsg);
                    if (dsMdlEsclRule == null) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    dsMdlEsclRuleListUpd.add(dsMdlEsclRule);
                } else {
                    aSMsgListIns.add(aSMsg);
                }
            } else {
                if (BigDecimal.ONE.equals(aSMsg.xxRowNum_D.getValue())) {
                    DS_MDL_ESCL_RULETMsg dsMdlEsclRule = checkDsMdlEsclRuleUpd(cMsg, aSMsg);
                    if (dsMdlEsclRule == null) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    dsMdlEsclRuleListUpd.add(dsMdlEsclRule);
                }
                DS_MDL_ESCL_BLLG_MTRTMsg dsMdlEsclBllgMtr = checkDsMdlEsclBllgMtrUpd(cMsg, aSMsg);
                if (dsMdlEsclBllgMtr == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                dsMdlEsclBllgMtrListUpd.add(dsMdlEsclBllgMtr);
            }
        }
        BigDecimal dsMdlEsclRulePk = null;
        for (NSAL1170_ASMsg aSMsg : aSMsgListIns) {
            if (BigDecimal.ONE.equals(aSMsg.xxRowNum_D.getValue())) {
                dsMdlEsclRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MDL_ESCL_RULE_SQ);
                NSAL1170CommonLogic.insertDsMdlEsclRule(sMsg, cMsg, aSMsg, dsMdlEsclRulePk);

            }
            if (ZYPConstant.FLG_ON_Y.equals(aSMsg.mtrReqMdlFlg.getValue())) {
                NSAL1170CommonLogic.insertDsMdlEsclBllgMtr(sMsg, cMsg, aSMsg, dsMdlEsclRulePk);
            }

        }
        for (DS_MDL_ESCL_RULETMsg msg : dsMdlEsclRuleListUpd) {
            EZDTBLAccessor.update(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS_MDL_ESCL_RULE" });
                return;
            }
        }
        for (DS_MDL_ESCL_RULETMsg msg : dsMdlEsclRuleListDel) {
            EZDTBLAccessor.logicalRemove(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS_MDL_ESCL_RULE" });
                return;
            }
        }

        for (DS_MDL_ESCL_BLLG_MTRTMsg msg : dsMdlEsclBllgMtrListUpd) {
            EZDTBLAccessor.update(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS_MDL_ESCL_BLLG_MTR_PK" });
                return;
            }
        }
        for (DS_MDL_ESCL_BLLG_MTRTMsg msg : dsMdlEsclBllgMtrListDel) {
            EZDTBLAccessor.logicalRemove(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS_MDL_ESCL_BLLG_MTR_PK" });
                return;
            }
        }
//        NSAL1170CommonLogic.getAddRowData(cMsg, sMsg);
//
//        NSAL1170CommonLogic.getSearchData(cMsg, sMsg);
//        NSAL1170CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
//        NSAL1170CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
//
//        NSAL1170CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());

    }

    private DS_MDL_ESCL_RULETMsg checkDsMdlEsclRuleDel(NSAL1170CMsg cMsg, NSAL1170_XSMsg aSMsg) {
        DS_MDL_ESCL_RULETMsg tMsg = NSAL1170CommonLogic.getDsMdlEsclRule(cMsg.glblCmpyCd.getValue(), aSMsg.dsMdlEsclRulePk_X.getValue());
        if (tMsg == null) {
            return null;
        }
        if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime_X.getValue(), aSMsg.ezUpTimeZone_X.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            return null;
        }
        return tMsg;
    }

    private DS_MDL_ESCL_RULETMsg checkDsMdlEsclRuleUpd(NSAL1170CMsg cMsg, NSAL1170_ASMsg aSMsg) {
        DS_MDL_ESCL_RULETMsg tMsg = NSAL1170CommonLogic.getDsMdlEsclRule(cMsg.glblCmpyCd.getValue(), aSMsg.dsMdlEsclRulePk.getValue());
        if (tMsg == null) {
            return null;
        }
        if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime.getValue(), aSMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            return null;
        }
        setValue(tMsg.uplftBasePrcUpRatio, aSMsg.uplftBasePrcUpRatio);
        setValue(tMsg.effFromDt, aSMsg.effFromDt);
        setValue(tMsg.effThruDt, aSMsg.effThruDt);

        return tMsg;
    }

    private DS_MDL_ESCL_BLLG_MTRTMsg checkDsMdlEsclBllgMtrDel(NSAL1170CMsg cMsg, NSAL1170_XSMsg aSMsg) {
        DS_MDL_ESCL_BLLG_MTRTMsg tMsg = NSAL1170CommonLogic.getDsMdlEsclBllgMtr(cMsg.glblCmpyCd.getValue(), aSMsg.dsMdlEsclRulePk_X.getValue(), aSMsg.dsMdlEsclBllgMtrPk_X.getValue());
        if (tMsg == null) {
            return null;
        }
        if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime_XD.getValue(), aSMsg.ezUpTimeZone_XD.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            return null;
        }
        return tMsg;
    }

    private DS_MDL_ESCL_BLLG_MTRTMsg checkDsMdlEsclBllgMtrUpd(NSAL1170CMsg cMsg, NSAL1170_ASMsg aSMsg) {
        DS_MDL_ESCL_BLLG_MTRTMsg tMsg = NSAL1170CommonLogic.getDsMdlEsclBllgMtr(cMsg.glblCmpyCd.getValue(), aSMsg.dsMdlEsclRulePk.getValue(), aSMsg.dsMdlEsclBllgMtrPk.getValue());
        if (tMsg == null) {
            return null;
        }
        if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime_D.getValue(), aSMsg.ezUpTimeZone_D.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            return null;
        }
        setValue(tMsg.uplftMtrPrcUpRatio, aSMsg.uplftMtrPrcUpRatio);
        return tMsg;
    }
}
