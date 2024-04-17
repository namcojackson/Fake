/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3030.NFCL3030CMsg;
//import business.servlet.NFCL3030.constant.NFCL3030Constant;

import business.servlet.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3030Scrn00_TAB_Customer extends S21CommonHandler implements NFCL3030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        //NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        //bizMsg.setBusinessID("NFCL3030");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        //NFCL3030CMsg bizMsg  = (NFCL3030CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_Customer);

    }
}
