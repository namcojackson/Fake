package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;

import java.math.BigDecimal;
import java.util.List;

import business.parts.NSZC047010PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2017   Hitachi         T.Mizuki        Create          N/A
 * 10/03/2017   Hitachi         E.Kameishi      Update          QC#18636
 * 2020/07/03   CITS            T.Sakurai       Update          QC#57297
 * 2020/09/24   Hitachi         K.Kitachi       Update          QC#57667
 *</pre>
 */
public class M10DeleteProcess implements ZYPConstant {

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        cancelProcess(msgMap);
    }

    private void cancelProcess(S21ApiMessageMap msgMap) {

        NSZC047010PMsg pMsg = (NSZC047010PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String delFlg = pMsg.delFlg.getValue();
        String dsContrDtlTp = NSZC0470Query.getInstance().getDsContrDtlTp(glblCmpyCd, dsContrDtlPk);
        // START 2020/09/24 K.Kitachi [QC#57667, ADD]
        BigDecimal dsContrBllgMtrPk = pMsg.dsContrBllgMtrPk.getValue();
        // END 2020/09/24 K.Kitachi [QC#57667, ADD]

        // QC#57297 Add Start
        // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//        List<BigDecimal> preEffMtrPkList = NSZC0470Query.getInstance().getPrcEffMtrForCancel(glblCmpyCd, dsContrDtlPk, slsDt, delFlg);
        List<BigDecimal> preEffMtrPkList = NSZC0470Query.getInstance().getPrcEffMtrForCancel(glblCmpyCd, dsContrDtlPk, slsDt, delFlg, dsContrBllgMtrPk);
        // END 2020/09/24 K.Kitachi [QC#57667, MOD]
        for (BigDecimal preEffMtrPk : preEffMtrPkList) {
            // delete  DS Contract Price Effective Meter
            NSZC0470Query.getInstance().removeContrPrcEffMtr(glblCmpyCd, preEffMtrPk);
        }
        // QC#57297 Add End

        // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//        List<BigDecimal> preEffPkList = NSZC0470Query.getInstance().getPrcEffForCancel(glblCmpyCd, dsContrDtlPk, slsDt, delFlg);
        List<BigDecimal> preEffPkList = NSZC0470Query.getInstance().getPrcEffForCancel(glblCmpyCd, dsContrDtlPk, slsDt, delFlg, dsContrBllgMtrPk);
        // END 2020/09/24 K.Kitachi [QC#57667, MOD]
        for (BigDecimal preEffPk : preEffPkList) {
            // delete  DS Contract Pricing Effectivity 
            NSZC0470Query.getInstance().removeContrPrcEff(glblCmpyCd, preEffPk);
        }

        // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//        List<BigDecimal> dsContrBllgSchdSmryPkList = NSZC0470Query.getInstance().getDsContrBllgSchdSmryForCancel(glblCmpyCd, dsContrDtlPk, delFlg);
        List<BigDecimal> dsContrBllgSchdSmryPkList = NSZC0470Query.getInstance().getDsContrBllgSchdSmryForCancel(glblCmpyCd, dsContrDtlPk, delFlg, dsContrBllgMtrPk);
        // END 2020/09/24 K.Kitachi [QC#57667, MOD]
        for (BigDecimal dsContrBllgSchdSmryPk : dsContrBllgSchdSmryPkList) {
            // delete DS Contract Billing Schedule Summary
            NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, dsContrBllgSchdSmryPk);
            List<BigDecimal> dsContrBllgSchdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, dsContrBllgSchdSmryPk);
            for (BigDecimal dsContrBllgSchdMtrPk : dsContrBllgSchdMtrPkList) {
                // delete DS Contract Billing Schedule Meter
                NSZC0470Query.getInstance().removeSchdMeter(glblCmpyCd, dsContrBllgSchdMtrPk);
            }
        }

        // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//        List<BigDecimal> dsContrBllgSchdPkList = NSZC0470Query.getInstance().getDsContrBllgSchdForCancel(glblCmpyCd, dsContrDtlPk, delFlg);
        List<BigDecimal> dsContrBllgSchdPkList = NSZC0470Query.getInstance().getDsContrBllgSchdForCancel(glblCmpyCd, dsContrDtlPk, delFlg, dsContrBllgMtrPk);
        // END 2020/09/24 K.Kitachi [QC#57667, MOD]
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

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047010PMsg pMsg = (NSZC047010PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[] {"Global Company Code" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[] {"Salse Date" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[] {"DS Contract Detail PK" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.delFlg, ZZZM9007E, new String[] {"Delete Flag" });
    }
}
