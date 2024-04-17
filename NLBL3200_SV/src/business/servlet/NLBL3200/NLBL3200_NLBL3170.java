/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import static business.servlet.NLBL3200.constant.NLBL3200Constant.BUSINESS_ID;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.FUNC_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/06/2018   CITS            T.Tokutomi      Create          QC#21913
 *</pre>
 */
public class NLBL3200_NLBL3170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

            NLBL3200CMsg bizMsg = new NLBL3200CMsg();
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(FUNC_SRCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // set focus.
        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        int index = scrnMsg.xxNum.getValueInt();

        scrnMsg.setFocusItem(scrnMsg.A.no(index).proNum_A1);

    }
}
