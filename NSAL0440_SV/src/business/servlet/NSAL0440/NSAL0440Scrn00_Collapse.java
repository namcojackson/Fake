/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440;

import static business.servlet.NSAL0440.constant.NSAL0440Constant.BUSINESS_ID;
import static business.servlet.NSAL0440.constant.NSAL0440Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0440.NSAL0440CMsg;
import business.servlet.NSAL0440.common.NSAL0440CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSAL0440Scrn00_Collapse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;
        NSAL0440CommonLogic.checkInputForTable(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;

        int eventLine = getButtonSelectNumber();
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(eventLine).xxBtnFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventLine).xxBtnFlg_A, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventLine).xxBtnFlg_A, ZYPConstant.FLG_ON_Y);
        }

        NSAL0440CMsg bizMsg = new NSAL0440CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;
        NSAL0440CMsg bizMsg = (NSAL0440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0440CommonLogic.initialControlScreen(this, scrnMsg);

    }
}
