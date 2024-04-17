/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.EVENT_NM_OPENWIN_MODEL;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.MESSAGE_KIND_ERROR;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *NMAL7060_NWAL1130
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         W.Honda         Create          N/A
 * 2018/08/20   Fujitsu         W.Honda         Update          QC#24307
 *</pre>
 */
public class NMAL7060_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        String eventNm = scrnMsg.xxMntEventNm.getValue();

        // QC#24307 2018/08/20 Mod start
//        if (!EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR.equals(eventNm)) {
//            return null;
//        }
        int index = getButtonSelectNumber();
        if (EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).bllgMtrLvlNum_B1, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).mtrLbCd_B1, scrnMsg.P.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).mtrLbDescTxt_B1, scrnMsg.P.no(2).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).mtrLbNm_B1, scrnMsg.P.no(3).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).mdseCd_B1, scrnMsg.P.no(4).xxComnScrColValTxt.getValue());

            if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_B1)
                    || index != scrnMsg.xxRadioBtn_B1.getValueInt()) {
                return null;
            }
        } else if (EVENT_NM_OPENWIN_MODEL.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).xxInsUpdDelFlg_A1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).mdlId_A1, new BigDecimal(scrnMsg.P.no(0).xxComnScrColValTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).mdlNm_A1, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());
        } else {
            return null;
        }
        // QC#24307 2018/08/20 Mod end

        NMAL7060CMsg bizMsg = new NMAL7060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        int btnidx = getButtonSelectNumber();

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR.equals(scrnMsg.xxMntEventNm.getValue())
                    && ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_B1)
                    && btnidx == scrnMsg.xxRadioBtn_B1.getValueInt()) {

                NMAL7060CMsg bizMsg  = (NMAL7060CMsg) cMsg;

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                    throw new EZDFieldErrorException();
                }

                NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
                NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
                NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
            } else if (EVENT_NM_OPENWIN_MODEL.equals(scrnMsg.xxMntEventNm.getValue())) {
                // QC#24307 2018/08/20 Mod start
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(btnidx).mdlId_A1, new BigDecimal(scrnMsg.P.no(0).xxComnScrColValTxt.getValue()));
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(btnidx).mdlNm_A1, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());
                NMAL7060CMsg bizMsg  = (NMAL7060CMsg) cMsg;

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                    throw new EZDFieldErrorException();
                }

                scrnMsg.A.no(btnidx).xxInsUpdDelFlg_A1.clear();

                NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
                NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
                NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
                // QC#24307 2018/08/20 Mod end
            }
        }
        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
    }
}
