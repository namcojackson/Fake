/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0170;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.servletcommon.EZDApplicationContext;

import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 * 2018/01/15   Fujitsu         H.Ikeda         Update          QC#22759
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFDL0170Scrn00_SelectAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;

        int index = getButtonSelectNumber();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length >= 1) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.A.no(index).billToCustAcctCd_A);
            }
            if (params.length >= 2) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[1], scrnMsg.A.no(index).billToCustAcctNm_A);
            }
            // START 2018/01/15 H.Ikeda [QC#22759,ADD]
            if (params.length >= 3) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.A.no(index).locNum_A);
            }
            // END 2018/01/15 H.Ikeda [QC#22759,ADD]
            // START 2024/02/23 S.Ikariya [QC#63452, ADD]
            if (params.length >= 4) {
                if (ZYPCommonFunc.hasValue(scrnMsg.invNum_SR.getValue())) {

                    AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
                    inMsg.setSQLID("001");
                    inMsg.setConditionValue("glblCmpyCd01", scrnMsg.glblCmpyCd.getValue());
                    inMsg.setConditionValue("arTrxNum01", scrnMsg.invNum_SR.getValue());
                    AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

                    if (outMsg.getValidCount() != 0 && ZYPCommonFunc.hasValue(outMsg.no(0).arCashApplyStsCd.getValue())
                            && (AR_CASH_APPLY_STS.UNAPPLIED.equals(outMsg.no(0).arCashApplyStsCd.getValue())
                                    || AR_CASH_APPLY_STS.PARTIAL.equals(outMsg.no(0).arCashApplyStsCd.getValue()))) {
                        ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[3], scrnMsg.invNum_SR);
                    }
                }
            }
            // END 2024/02/23 S.Ikariya [QC#63452, ADD]
        }
    }
}
