/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7190Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7190CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CMsg bizMsg = new NMAL7190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg  = (NMAL7190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).prcGrpTrgtAttrbCd_A1);

    }
}
