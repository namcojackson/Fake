/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.MSG_PARAM_LOC;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.NMAM8461E;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.SCRN_TRANS_COND_NMAL6720;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SRCH_LYOT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460Scrn00_MoveWin_ViewAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        if (SRCH_LYOT_TP.LAYOUT_1.equals(scrnMsg.srchLyotTpCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_A)) {
                scrnMsg.setMessageInfo(NMAM8461E, new String[] {MSG_PARAM_LOC });
                throw new EZDFieldErrorException();
            }
        } else if (SRCH_LYOT_TP.LAYOUT_2.equals(scrnMsg.srchLyotTpCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_B)) {
                scrnMsg.setMessageInfo(NMAM8461E, new String[] {MSG_PARAM_LOC });
                throw new EZDFieldErrorException();
            }
        } else {
            scrnMsg.setMessageInfo(NMAM8461E, new String[] {MSG_PARAM_LOC });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getLastGuard());
        NMAL2460CommonLogic.clearParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, SCRN_TRANS_COND_NMAL6720);
        if (SRCH_LYOT_TP.LAYOUT_1.equals(scrnMsg.srchLyotTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.A.no(scrnMsg.xxRadioBtn_A.getValueInt()).dsAcctNum_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.A.no(scrnMsg.xxRadioBtn_A.getValueInt()).locNum_A);
        } else if (SRCH_LYOT_TP.LAYOUT_2.equals(scrnMsg.srchLyotTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.B.no(scrnMsg.xxRadioBtn_B.getValueInt()).dsAcctNum_B);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.B.no(scrnMsg.xxRadioBtn_B.getValueInt()).locNum_B);
        }

        Object[] params = new Object[3];
        params[0] = scrnMsg.xxPopPrm_01;
        params[1] = scrnMsg.xxPopPrm_02;
        params[2] = scrnMsg.xxPopPrm_03;
        setArgForSubScreen(params);
    }
}
