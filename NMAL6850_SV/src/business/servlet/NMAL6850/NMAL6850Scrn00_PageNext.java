/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6850.NMAL6850CMsg;
import business.servlet.NMAL6850.common.NMAL6850CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6850 Supplier Search
 * Function Name : The business process for PageNext.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/24   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NMAL6850Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        scrnMsg.xxPageShowToNum.clear();

        return NMAL6850CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;
        
        EZDMsg.copy((NMAL6850CMsg) cMsg, null, (NMAL6850BMsg) bMsg, null);
        
        NMAL6850CommonLogic.setTableScreen(scrnMsg);
    }
}
