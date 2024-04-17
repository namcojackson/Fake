/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2060.NFBL2060CMsg;
//import business.servlet.NFBL2060.constant.NFBL2060Constant;

import business.servlet.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 *</pre>
 */
public class NFBL2060Scrn00_OnClick_New extends S21CommonHandler implements NFBL2060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        //NFBL2060CMsg bizMsg = new NFBL2060CMsg();
        //bizMsg.setBusinessID("NFBL2060");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        //NFBL2060CMsg bizMsg  = (NFBL2060CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
