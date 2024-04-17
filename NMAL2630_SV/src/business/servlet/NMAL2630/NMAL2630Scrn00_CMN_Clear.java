/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL2630.NMAL2630CMsg;
import business.servlet.NMAL2630.common.NMAL2630CommonLogic;
import business.servlet.NMAL2630.constant.NMAL2630Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL2630Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        NMAL2630CMsg bizMsg = new NMAL2630CMsg();
        bizMsg.setBusinessID("NMAL2630");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;
        NMAL2630CMsg bizMsg  = (NMAL2630CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2630CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL2630Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.orgStruTpCd_H1);

        setButtonEnabled(NMAL2630Constant.BTN_INSERT, true);
        setButtonEnabled(NMAL2630Constant.BTN_DELETE, true);

    }
}
