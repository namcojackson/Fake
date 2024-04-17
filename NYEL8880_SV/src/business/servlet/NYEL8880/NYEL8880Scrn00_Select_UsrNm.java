/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NYEL8880;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NYEL8880.common.NYEL8880CommonLogic;
import business.servlet.NYEL8880.constant.NYEL8880Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Common PopUp NWAL1130Scrn00_Select_Column
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/09   Fujitsu         M.Ugaki         Create          N/A
 *</pre>
 */
public class NYEL8880Scrn00_Select_UsrNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[2], scrnMsg.A.no(selectIdx).usrNm_A);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[3], scrnMsg.A.no(selectIdx).lastNm_A);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[4], scrnMsg.A.no(selectIdx).firstNm_A);
        }
    }
}
