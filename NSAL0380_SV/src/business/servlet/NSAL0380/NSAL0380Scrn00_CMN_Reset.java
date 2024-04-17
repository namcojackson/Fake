/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0380.NSAL0380CMsg;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/12   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL0380Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

        NSAL0380CMsg bizMsg = new NSAL0380CMsg();
        bizMsg.setBusinessID(NSAL0380Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0380Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        NSAL0380CMsg bizMsg = (NSAL0380CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0380CommonLogic.controlDtl(this, scrnMsg);
    }
}
