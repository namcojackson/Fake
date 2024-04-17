/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0610;

import static business.servlet.NSAL0610.common.NSAL0610CommonLogic.initialControlScreen;
import static business.servlet.NSAL0610.constant.NSAL0610Constant.BUSINESS_ID;
import static business.servlet.NSAL0610.constant.NSAL0610Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0610.NSAL0610CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSAL0610Scrn00_OrigCollapse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0610BMsg scrnMsg = (NSAL0610BMsg) bMsg;
        int eventLine = getButtonSelectNumber();
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(eventLine).xxBtnFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventLine).xxBtnFlg_A, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventLine).xxBtnFlg_A, ZYPConstant.FLG_ON_Y);
        }

        NSAL0610CMsg bizMsg = new NSAL0610CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0610BMsg scrnMsg = (NSAL0610BMsg) bMsg;
        NSAL0610CMsg bizMsg = (NSAL0610CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);

    }
}
