/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL1000.NLCL1000CMsg;
//import business.servlet.NLCL1000.constant.NLCL1000Constant;

import business.blap.NLCL1000.NLCL1000CMsg;
import business.servlet.NLCL1000.common.NLCL1000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLCL1000Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;
        NLCL1000CMsg bizMsg = new NLCL1000CMsg();

        bizMsg.setBusinessID("NLCL1000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;
        NLCL1000CMsg bizMsg  = (NLCL1000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1000CommonLogic.initCommonButton(this);
        NLCL1000CommonLogic.initButton(this);
        NLCL1000CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
