/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2080.NFBL2080CMsg;
//import business.servlet.NFBL2080.constant.NFBL2080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/05/20   Hitachi         H.Umeda         Create          N/A
 *</pre>
 */
public class NFBL2080_NPBL0020 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        //NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        //bizMsg.setBusinessID("NFBL2080");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        //NFBL2080CMsg bizMsg  = (NFBL2080CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
