package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import business.parts.NSZC047021PMsg;
import business.parts.NSZC047022PMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2016   Hitachi         K.Kishimoto     Create          QC#4961
 * </pre>
 */
public class M22ResetSvcContrBllgProcess implements ZYPConstant {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTp;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        resetSvcContrBllgProc(msgMap);
    }

    private void resetSvcContrBllgProc(S21ApiMessageMap msgMap) {

        NSZC047022PMsg pMsg = (NSZC047022PMsg) msgMap.getPmsg();
        String dsContrCatgCd = pMsg.dsContrCatgCd.getValue();
        BigDecimal dsContrBllgSchdPk = pMsg.dsContrBllgSchdPk.getValue();
        BigDecimal prntDsContrBllgSchdPk = pMsg.prntDsContrBllgSchdPk.getValue();

        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            NSZC047001CommonLogic.resetSvcContrBllgInfo(msgMap, pMsg.glblCmpyCd.getValue(), dsContrBllgSchdPk, PREFIX_DOC_ID_REG);
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            NSZC047001CommonLogic.resetSvcContrBllgInfoFlt(msgMap, pMsg.glblCmpyCd.getValue(), prntDsContrBllgSchdPk);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            NSZC047001CommonLogic.resetSvcContrBllgInfoAgg(msgMap, pMsg.glblCmpyCd.getValue(), prntDsContrBllgSchdPk);
        }
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047022PMsg pMsg = (NSZC047022PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[] {"Global Company Code" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[] {"Salse Date" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrBllgSchdPk, ZZZM9007E, new String[] {"DS Contract Billing Schedule PK" });
    }

}
