/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3080.NLBL3080CMsg;
import business.servlet.NLBL3080.common.NLBL3080CommonLogic;
import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2017/06/23   CITS            R.Shimamoto     Update          QC#19518
 *</pre>
 */
public class NLBL3080Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;

        if (NLBL3080Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.addCheckItemOrder(scrnMsg, true);

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(scrnMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.addCheckItemOrderLine(scrnMsg, true);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;

        NLBL3080CMsg bizMsg = new NLBL3080CMsg();
        bizMsg.setBusinessID(NLBL3080Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        NLBL3080CMsg bizMsg  = (NLBL3080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3080CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        if (NLBL3080Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.addCheckItemOrder(scrnMsg, true);

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(scrnMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.addCheckItemOrderLine(scrnMsg, true);
        }
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
    }
}
