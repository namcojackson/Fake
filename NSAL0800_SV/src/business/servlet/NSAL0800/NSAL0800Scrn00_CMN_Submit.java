/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0800.NSAL0800CMsg;
import static business.servlet.NSAL0800.constant.NSAL0800Constant.*;

import business.servlet.NSAL0800.common.NSAL0800CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2018/05/31   CITS            M.Naito         Update          QC#22887
 *</pre>
 */
public class NSAL0800Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;
        NSAL0800CommonLogic.checkItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;

        NSAL0800CMsg bizMsg = new NSAL0800CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;
        NSAL0800CMsg bizMsg  = (NSAL0800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.leaseCmpyCd_A1);
        // START 2018/05/31 M.Naito [QC#22887, ADD]
        NSAL0800CommonLogic.controlScreenFields(scrnMsg);
        // END 2018/05/31 M.Naito [QC#22887, ADD]
        scrnMsg.putErrorScreen();
    }
}
