/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3110.NLBL3110CMsg;
import business.servlet.NLBL3110.common.NLBL3110CommonLogic;
import business.servlet.NLBL3110.constant.NLBL3110Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 *</pre>
 */
public class NLBL3110Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3110BMsg scrnMsg = (NLBL3110BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum_A.setValue(scrnMsg.xxPageShowToNum_A.getValueInt());
        scrnMsg.xxPageShowToNum_A.clear();

        NLBL3110CMsg bizMsg = new NLBL3110CMsg();
        bizMsg.setBusinessID(NLBL3110Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3110BMsg scrnMsg = (NLBL3110BMsg) bMsg;
        NLBL3110CMsg bizMsg = (NLBL3110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3110CommonLogic.controlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.cpoNum_H1);
    }
}