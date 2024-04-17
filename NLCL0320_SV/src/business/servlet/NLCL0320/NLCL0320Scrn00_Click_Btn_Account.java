/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0320.NLCL0320CMsg;
import business.servlet.NLCL0320.common.NLCL0320CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0320Scrn00_Click_Btn_Account extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        // set index
        scrnMsg.xxNum.setValue(getButtonSelectNumber());

        NLCL0320CMsg bizMsg = new NLCL0320CMsg();
        bizMsg.setBusinessID("NLCL0320");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // AddCheck Item
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_A);
        }
        scrnMsg.putErrorScreen();

        Object[] params = NLCL0320CommonLogic.setParamForAccountPopup(scrnMsg, scrnMsg.xxNum.getValueInt());

        setArgForSubScreen(params);
    }
}