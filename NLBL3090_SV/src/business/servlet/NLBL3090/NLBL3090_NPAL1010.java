/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3090.NLBL3090CMsg;
import business.servlet.NLBL3090.common.NLBL3090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_CMN_CLOSE;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/12/2016   CSAI            D.Fukaya        Create          QC#6088
 *</pre>
 */
public class NLBL3090_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;

            NLBL3090CMsg bizMsg = new NLBL3090CMsg();
            bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
            bizMsg.setFunctionCode("20");

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
            NLBL3090CMsg bizMsg = (NLBL3090CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if ("E".equals(bizMsg.getMessageKind())) {
                NLBL3090CommonLogic.addCheckSearchItem(scrnMsg);
                scrnMsg.putErrorScreen();
            }
        }
    }
}
