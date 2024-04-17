/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2600.NMAL2600CMsg;
import business.servlet.NMAL2600.constant.NMAL2600Constant;
//import business.servlet.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2600Scrn00_OnChangeSavedSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        NMAL2600CMsg bizMsg = new NMAL2600CMsg();
        bizMsg.setBusinessID("NMAL2600");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;
        NMAL2600CMsg bizMsg  = (NMAL2600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();

        S21SortColumnIMGController.clearIMG(NMAL2600Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if (scrnMsg.A.getValidCount() < scrnMsg.A.length()) {
            setButtonEnabled(NMAL2600Constant.BTN_INSERT, true);
        } else {
            setButtonEnabled(NMAL2600Constant.BTN_INSERT, false);
        }
        if (scrnMsg.A.getValidCount() > 0) {
            setButtonEnabled(NMAL2600Constant.BTN_DELETE, true);
        } else {
            setButtonEnabled(NMAL2600Constant.BTN_DELETE, false);
        }

    }
}
