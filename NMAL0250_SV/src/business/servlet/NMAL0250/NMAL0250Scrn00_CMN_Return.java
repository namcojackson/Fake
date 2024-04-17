/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0250;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0250.common.NMAL0250CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL0250Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0250BMsg scrnMsg = (NMAL0250BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A0);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A1);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A2);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A3);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A4);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A5);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A6);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A7);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A8);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_A9);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_AA);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_AB);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_AC);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_AD);
        scrnMsg.addCheckItem(scrnMsg.cmpsnMsgTxt_AE);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

         return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0250BMsg scrnMsg = (NMAL0250BMsg) bMsg;
        NMAL0250CommonLogic.setResult(scrnMsg, scrnMsg.R);
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            NMAL0250CommonLogic.setOutputParam((Object[]) arg, scrnMsg.R);
        }
    }
}
