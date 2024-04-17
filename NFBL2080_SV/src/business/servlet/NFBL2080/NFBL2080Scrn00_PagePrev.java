/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2080.NFBL2080CMsg;
//import business.servlet.NFBL2080.constant.NFBL2080Constant;

import business.blap.NFBL2080.NFBL2080CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
public class NFBL2080Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_P1.setValue( scrnMsg.xxPageShowFromNum_P1.getValueInt() - scrnMsg.A.length() - 1 );
        scrnMsg.xxPageShowToNum_P1.clear();


        NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        bizMsg.setBusinessID("NFBL2080");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        NFBL2080CMsg bizMsg  = (NFBL2080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
