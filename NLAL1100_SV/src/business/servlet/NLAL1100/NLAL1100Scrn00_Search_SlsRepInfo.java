/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL1100.NLAL1100CMsg;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLAL1100Scrn00_Search_SlsRepInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.slsRepBrCd_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        NLAL1100CMsg bizMsg = new NLAL1100CMsg();
        bizMsg.setBusinessID(NLAL1100Constant.BIZ_APP_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
        NLAL1100CMsg bizMsg = (NLAL1100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.slsRepBrCd_H1);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.slsRepBrCd_H1);
    }
}
