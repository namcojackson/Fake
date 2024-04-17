/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0240;

import static business.servlet.NSBL0240.constant.NSBL0240Constant.BUSINESS_ID;
import static business.servlet.NSBL0240.constant.NSBL0240Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0240.NSBL0240CMsg;
import business.servlet.NSBL0240.common.NSBL0240CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Travel Charge Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/24   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSBL0240Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;
        NSBL0240CMsg bizMsg = new NSBL0240CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;
        NSBL0240CMsg bizMsg = (NSBL0240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0240CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());

    }
}
