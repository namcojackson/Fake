/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1040;

import static business.servlet.NLCL1040.constant.NLCL1040Constant.BIZ_APP_ID;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1040.NLCL1040CMsg;
import business.servlet.NLCL1040.common.NLCL1040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040Scrn00_AddTag
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040Scrn00_AddTag extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;

        NLCL1040CMsg bizMsg = new NLCL1040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;
        NLCL1040CMsg bizMsg = (NLCL1040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1040CommonLogic.ctrlScrnItemDisplay(this, scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            scrnMsg.putErrorScreen();
            return;
        }

        // set focus
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).mdseCd_A);

    }
}
