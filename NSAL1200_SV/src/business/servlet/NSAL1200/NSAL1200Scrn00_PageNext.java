/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1200.NSAL1200CMsg;
import business.servlet.NSAL1200.common.NSAL1200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/27   Hitachi         T.Tomita        Update          QC#7648
 *</pre>
 */
public class NSAL1200Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // del start 2016/04/27 CSA Defect#7648
//        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
//        NSAL1200CommonLogic.addCheckItemDetailList(scrnMsg);
//        scrnMsg.putErrorScreen();
        // del end 2016/04/27 CSA Defect#7648
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;

        NSAL1200CMsg bizMsg = new NSAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        NSAL1200CMsg bizMsg = (NSAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1200CommonLogic.addCheckItemDetailList(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
