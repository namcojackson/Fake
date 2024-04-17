/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1130.NFBL1130CMsg;
//import business.servlet.NFBL1130.constant.NFBL1130Constant;

import business.blap.NFBL1130.NFBL1130CMsg;
import business.servlet.NFBL1130.common.NFBL1130CommonLogic;
import business.servlet.NFBL1130.constant.NFBL1130Constant;
import business.servlet.NFBL1130.NFBL1130BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 *</pre>
 */
public class NFBL1130Scrn00_PageNext extends S21CommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg = new NFBL1130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg  = (NFBL1130CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        /** Initialize button control */ 
        NFBL1130CommonLogic.initControl(this, scrnMsg);
        /** Set alternate rows background color */
        NFBL1130CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.poNum);

    }
}
