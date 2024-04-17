/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import static business.servlet.NSAL0480.constant.NSAL0480Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0480.NSAL0480CMsg;
import business.servlet.NSAL0480.constant.NSAL0480Constant.FUNC;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/10   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NSAL0480Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;
        NSAL0480CMsg bizMsg = new NSAL0480CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;
        if (scrnMsg.xxRadioBtn.isClear()) {
            return;
        }
        int n = scrnMsg.xxRadioBtn.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_P, scrnMsg.A.no(n).t_MdlNm_A);

        Object[] arg = (Object[]) getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            param0.setValue(scrnMsg.A.no(n).t_MdlNm_A.getValue());
        }
        return;
    }
}
