/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.common.NSAL0640CommonLogic.controlScreenFields;
import static business.servlet.NSAL0640.common.NSAL0640CommonLogic.controlDetailButton;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0640.NSAL0640CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/06   Hitachi         T.Mizuki        Create          QC#4210
 *</pre>
 */
public class NSAL0640Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        NSAL0640CMsg bizMsg = new NSAL0640CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        NSAL0640CMsg bizMsg = (NSAL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        controlScreenFields(this, scrnMsg);
        controlDetailButton(this, scrnMsg);
    }
}
