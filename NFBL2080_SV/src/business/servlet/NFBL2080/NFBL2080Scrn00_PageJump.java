/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2080.NFBL2080CMsg;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 *</pre>
 */
public class NFBL2080Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

    	NFBL2080CMsg bizMsg = null;

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        bizMsg = new NFBL2080CMsg();
        bizMsg.setBusinessID("NFBL2080");
        bizMsg.setFunctionCode("20");

        int pagenationFrom = scrnMsg.xxPageShowFromNum_P1.getValueInt();
        scrnMsg.xxPageShowFromNum_P1.setValue(pagenationFrom);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_P1, scrnMsg.xxPageShowToNum_P1, scrnMsg.xxPageShowOfNum_P1, scrnMsg.xxPageShowCurNum_P1, scrnMsg.xxPageShowTotNum_P1);

        bizMsg.xxPageShowFromNum_P1.setValue(scrnMsg.xxPageShowFromNum_P1.getValueInt());
        bizMsg.xxPageShowToNum_P1.setValue(scrnMsg.xxPageShowToNum_P1.getValueInt());
        bizMsg.xxPageShowOfNum_P1.setValue(scrnMsg.xxPageShowOfNum_P1.getValueInt());
        bizMsg.xxPageShowCurNum_P1.setValue(scrnMsg.xxPageShowCurNum_P1.getValueInt());
        bizMsg.xxPageShowTotNum_P1.setValue(scrnMsg.xxPageShowTotNum_P1.getValueInt());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        NFBL2080CMsg bizMsg  = (NFBL2080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
