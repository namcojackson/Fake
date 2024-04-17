/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720;

import static business.servlet.NSAL0720.constant.NSAL0720Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0720.NSAL0720CMsg;
//import business.servlet.NSAL0720.constant.NSAL0720Constant;

import business.blap.NSAL0720.NSAL0720CMsg;
import business.servlet.NSAL0720.common.NSAL0720CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/11   Hitachi         T.Mizuki        Create          QC#4210
 *</pre>
 */
public class NSAL0720Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;

        NSAL0720CMsg bizMsg = new NSAL0720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        NSAL0720CMsg bizMsg = (NSAL0720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0720CommonLogic.initialControlScreen(this, scrnMsg);
    }
}
