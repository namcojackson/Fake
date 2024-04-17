/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1650;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         Y.Kanefusa      Create          #1309
 *</pre>
 */
public class NWAL1650Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1650BMsg scrnMsg = (NWAL1650BMsg) bMsg;
        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Set Parameter
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[5], scrnMsg.dsAcctNum_P);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[6], scrnMsg.bllgAttrbNm_1);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[7], scrnMsg.bllgAttrbValTxt_1);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[8], scrnMsg.bllgAttrbNm_2);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[9], scrnMsg.bllgAttrbValTxt_2);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[10], scrnMsg.bllgAttrbNm_3);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[11], scrnMsg.bllgAttrbValTxt_3);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[12], scrnMsg.bllgAttrbNm_4);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[13], scrnMsg.bllgAttrbValTxt_4);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[14], scrnMsg.bllgAttrbNm_5);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[15], scrnMsg.bllgAttrbValTxt_5);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[16], scrnMsg.bllgAttrbNm_6);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[17], scrnMsg.bllgAttrbValTxt_6);
        }

    }
}
