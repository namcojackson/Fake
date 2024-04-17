/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2090;

import static business.servlet.NWAL2090.constant.NWAL2090Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2090.constant.NWAL2090Constant.NWAM0700E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2090Scrn00_CMN_OK
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2090Scrn00_CMN_OK extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2090BMsg scrnMsg = (NWAL2090BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.chngRsnTpCd);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt);

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrColValTxt)) {
            scrnMsg.setMessageInfo(NWAM0700E);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2090BMsg scrnMsg = (NWAL2090BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Set Parameter
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[1], scrnMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.chngRsnTpCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[3], scrnMsg.xxComnScrColValTxt);
        }
    }
}
