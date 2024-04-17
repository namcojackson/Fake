/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8899;

import static business.servlet.NYEL8899.constant.NYEL8899Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8899.NYEL8899CMsg;
//import business.servlet.NYEL8899.constant.NYEL8899Constant;

import business.servlet.NYEL8899.common.NYEL8899CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8899Scrn00_CMN_Approve
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899Scrn00_CMN_Approve extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;
        
        NYEL8899CMsg bizMsg = new NYEL8899CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;
        NYEL8899CMsg bizMsg  = (NYEL8899CMsg) cMsg;
        //
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        scrnMsg.setFocusItem(scrnMsg.wfProcNm);
        NYEL8899CommonLogic.initCmnBtnProp(scrnMsg, this);
    }
}
