/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0430;

import static business.servlet.NSBL0430.constant.NSBL0430Constant.BIZ_ID;
import static business.servlet.NSBL0430.constant.NSBL0430Constant.EZD_FUNC_CD_INQ;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.addCheckItem;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.controlScreenFields;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0430.NSBL0430CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11685
 *</pre>
 */
public class NSBL0430Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;

        NSBL0430CMsg bizMsg = new NSBL0430CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;
        NSBL0430CMsg bizMsg  = (NSBL0430CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        controlScreenFields(scrnMsg);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).svcModFromSerNum_A);
    }
}
