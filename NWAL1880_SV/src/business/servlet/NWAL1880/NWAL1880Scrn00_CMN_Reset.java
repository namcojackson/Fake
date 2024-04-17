/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1880.NWAL1880CMsg;
//import business.servlet.NWAL1880.constant.NWAL1880Constant;

import business.blap.NWAL1880.NWAL1880CMsg;
import business.servlet.NWAL1880.common.NWAL1880CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1880Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

        // 1. initialize GUI value.
        NWAL1880CommonLogic.initGuiValueScrn00(scrnMsg);

        NWAL1880CMsg bizMsg = new NWAL1880CMsg();
        bizMsg.setBusinessID("NWAL1880");

        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;
        NWAL1880CMsg bizMsg = (NWAL1880CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 1. initialize GUI attribute.
        NWAL1880CommonLogic.initCmnBtnProp(this);

        // 2. setProtectByAuthority
        NWAL1880CommonLogic.setProtectByAuthority(this, scrnMsg);

        // 3. set focus.
        scrnMsg.setFocusItem(scrnMsg.xxOrdTeamSrchTxt);

        // 4. set digit.
        NWAL1880CommonLogic.setAppFracDigit(scrnMsg);

    }
}
