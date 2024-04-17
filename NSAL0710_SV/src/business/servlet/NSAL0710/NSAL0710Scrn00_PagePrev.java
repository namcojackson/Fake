/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0710;

import static business.servlet.NSAL0710.constant.NSAL0710Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0710.NSAL0710CMsg;
//import business.servlet.NSAL0710.constant.NSAL0710Constant;

import business.blap.NSAL0710.NSAL0710CMsg;
import business.servlet.NSAL0710.common.NSAL0710CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/11   Hitachi         T.Mizuki        Create          QC#4210
 *</pre>
 */
public class NSAL0710Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0710BMsg scrnMsg = (NSAL0710BMsg) bMsg;

        NSAL0710CMsg bizMsg = new NSAL0710CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0710BMsg scrnMsg = (NSAL0710BMsg) bMsg;
        NSAL0710CMsg bizMsg = (NSAL0710CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0710CommonLogic.initialControlScreen(this, scrnMsg);
    }
}
