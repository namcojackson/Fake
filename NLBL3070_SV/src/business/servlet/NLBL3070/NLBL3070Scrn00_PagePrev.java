/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
 */
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/06/25   Fujitsu         N.Sakamoto      Create          N/A
 *</pre>
 */
package business.servlet.NLBL3070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.servlet.NLBL3070.common.NLBL3070CommonLogic;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLBL3070Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {
            NLBL3070CommonLogic.addCheckItemScheduling(scrnMsg);
        } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {
            NLBL3070CommonLogic.addCheckItemDeliveries(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg = new NLBL3070CMsg();
        bizMsg.setBusinessID(NLBL3070Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg = (NLBL3070CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3070CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        NLBL3070CommonLogic.addCheckItemScheduling(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.trxHdrNum);
    }

}
