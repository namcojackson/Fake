/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLGL0030.NLGL0030CMsg;
import business.servlet.NLGL0030.common.NLGL0030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * ForcedItem Master download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            M.Shimamura     Create          MW Replace Initial
 *</pre>
 */
public class NLGL0030Scrn00_OnClick_DnldSrch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLGL0030BMsg scrnMsg = (NLGL0030BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.whCd_P1);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0030BMsg scrnMsg = (NLGL0030BMsg) bMsg;
        NLGL0030CMsg bizMsg = NLGL0030CommonLogic.setRequestData_NLGL0030Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0030BMsg scrnMsg = (NLGL0030BMsg) bMsg;
        NLGL0030CMsg bizMsg = (NLGL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.B.setInputProtected(true);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, "xxDownload");
        NLGL0030CommonLogic.commonBtnControl_NLGL0030Scrn00_DNLD_SEARCH(this);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.putErrorScreen();
    }
}
