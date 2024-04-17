/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.LINK_LOCATION;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0655E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OpenWin_LocationMeter
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_LocationMeter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue()) || NSAL1330CommonLogic.isImport(scrnMsg)) {
            scrnMsg.setMessageInfo(NSAM0655E, new String[] {LINK_LOCATION, LINK_LOCATION });
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxNum_Z.setValue(getButtonSelectNumber());

        Object[] params = NSAL1330CommonLogic.getParamNMAL6760ForBillTo(scrnMsg, false);
        setArgForSubScreen(params);
    }
}
