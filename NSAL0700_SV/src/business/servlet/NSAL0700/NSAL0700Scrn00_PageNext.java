/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0700;

import static business.servlet.NSAL0700.common.NSAL0700CommonLogic.initialControlScreen;
import static business.servlet.NSAL0700.constant.NSAL0700Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0700.NSAL0700CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/28   Hitachi         T.Mizuki        Create          QC#4210
 *</pre>
 */
public class NSAL0700Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;

        NSAL0700CMsg bizMsg = new NSAL0700CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;
        NSAL0700CMsg bizMsg = (NSAL0700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        initialControlScreen(this, scrnMsg);
    }
}
