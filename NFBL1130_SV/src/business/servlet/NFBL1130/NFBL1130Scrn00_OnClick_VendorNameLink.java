/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1130.NFBL1130CMsg;
//import business.servlet.NFBL1130.constant.NFBL1130Constant;

import business.servlet.NFBL1130.constant.NFBL1130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 *</pre>
 */
public class NFBL1130Scrn00_OnClick_VendorNameLink extends S21CommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        //NFBL1130CMsg bizMsg = new NFBL1130CMsg();
        //bizMsg.setBusinessID("NFBL1130");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        //NFBL1130CMsg bizMsg  = (NFBL1130CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
