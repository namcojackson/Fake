package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.parts.NSZC047009PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/10/2017   Hitachi         T.Mizuki        Update          QC#16399
 * 10/03/2017   Hitachi         E.Kameishi      Update          QC#18636
 *</pre>
 */
public class M09CancelProcess implements ZYPConstant {

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        cancelProcess(msgMap);
    }

    private void cancelProcess(S21ApiMessageMap msgMap) {

        NSZC047009PMsg pMsg = (NSZC047009PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String delFlg = pMsg.delFlg.getValue();
        String dsContrDtlTp = NSZC0470Query.getInstance().getDsContrDtlTp(glblCmpyCd, dsContrDtlPk);

        List<BigDecimal> preEffPkList = NSZC0470Query.getInstance().getPrcEffForCancel(glblCmpyCd, dsContrDtlPk, slsDt, delFlg);
        for (BigDecimal preEffPk : preEffPkList) {
            updatePrcEff(msgMap, glblCmpyCd, preEffPk);
        }

        // mod start 2017/01/10 CSA QC#16399
        List<BigDecimal> dsContrBllgSchdSmryPkList = NSZC0470Query.getInstance().getDsContrBllgSchdSmryForCancel(glblCmpyCd, dsContrDtlPk, delFlg);
        // mod end 2017/01/10 CSA QC#16399
        for (BigDecimal dsContrBllgSchdSmryPk : dsContrBllgSchdSmryPkList) {
            // delete DS Contract Billing Schedule Summary
            NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, dsContrBllgSchdSmryPk);
            List<BigDecimal> dsContrBllgSchdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, dsContrBllgSchdSmryPk);
            for (BigDecimal dsContrBllgSchdMtrPk : dsContrBllgSchdMtrPkList) {
                // delete DS Contract Billing Schedule Meter
                NSZC0470Query.getInstance().removeSchdMeter(glblCmpyCd, dsContrBllgSchdMtrPk);
            }
        }

        // mod start 2017/01/10 CSA QC#16399
        List<BigDecimal> dsContrBllgSchdPkList = NSZC0470Query.getInstance().getDsContrBllgSchdForCancel(glblCmpyCd, dsContrDtlPk, delFlg);
        // mod end 2017/01/10 CSA QC#16399
        for (BigDecimal dsContrBllgSchdPk : dsContrBllgSchdPkList) {
            if (FLG_ON_Y.equals(delFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
                List<BigDecimal> childDsContrBllgSchdPkList = NSZC0470Query.getInstance().getChildDsContrBllgSchdForCancel(glblCmpyCd, dsContrBllgSchdPk);
                for (BigDecimal childDsContrBllgSchdPk : childDsContrBllgSchdPkList) {
                    NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childDsContrBllgSchdPk);
                    NSZC0470Query.getInstance().removeSchd(glblCmpyCd, childDsContrBllgSchdPk);
                    // START 2017/10/03 E.Kameishi [QC18636, ADD]
                    NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, childDsContrBllgSchdPk);
                    // END 2017/10/03 E.Kameishi [QC18636, ADD]
                }
            }
            // delete Service Contract Billing Information
            NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk);
            // delete DS Contract Billing Schedule
            NSZC0470Query.getInstance().removeSchd(glblCmpyCd, dsContrBllgSchdPk);
        }
    }

    private boolean updatePrcEff(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal preEffPk) {

        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffPk, preEffPk);
        inTMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (inTMsg == null) {
            return false;
        }
        setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.CANCELLED);

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_PRC_EFF" });
            return false;
        }
        return true;
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047009PMsg pMsg = (NSZC047009PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[] {"Global Company Code" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[] {"Salse Date" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[] {"DS Contract Detail PK" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.delFlg, ZZZM9007E, new String[] {"Delete Flag" });
    }
}
