/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0630;

import static business.servlet.NSAL0630.constant.NSAL0630Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0630.NSAL0630CMsg;
import business.servlet.NSAL0630.common.NSAL0630CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Hitachi         T.Tomita        Create          QC#4210
 *</pre>
 */
public class NSAL0630Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0630BMsg scrnMsg = (NSAL0630BMsg) bMsg;
        NSAL0630CMsg bizMsg = new NSAL0630CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0630BMsg scrnMsg = (NSAL0630BMsg) bMsg;
        NSAL0630CMsg bizMsg = (NSAL0630CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0630CommonLogic.initialControlScreen(this, scrnMsg);
    }
}
