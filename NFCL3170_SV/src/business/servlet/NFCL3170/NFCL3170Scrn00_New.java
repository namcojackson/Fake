/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3170.NFCL3170CMsg;
//import business.servlet.NFCL3170.constant.NFCL3170Constant;

import business.blap.NFCL3170.NFCL3170CMsg;
import business.servlet.NFCL3170.common.NFCL3170CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 *</pre>
 */
public class NFCL3170Scrn00_New extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
//
//        scrnMsg.addCheckItem(scrnMsg.dsBankCd_H1);
//        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
//
//        NFCL3170CMsg bizMsg = new NFCL3170CMsg();
//        bizMsg.setBusinessID("NFCL3170");
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
//        NFCL3170CMsg bizMsg  = (NFCL3170CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NFCL3170CommonLogic.setProtect_NewBranch(this, scrnMsg);
    }
}
