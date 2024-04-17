/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0400;

import static business.servlet.NSAL0400.constant.NSAL0400Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0400.NSAL0400CMsg;
import business.servlet.NSAL0400.common.NSAL0400CommonLogic;
import business.servlet.NSAL0400.constant.NSAL0400Constant.FUNC;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NSAL0400_NSBL0160 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;
        NSAL0400CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;

        NSAL0400CMsg bizMsg = new NSAL0400CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;
        NSAL0400CMsg bizMsg = (NSAL0400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
