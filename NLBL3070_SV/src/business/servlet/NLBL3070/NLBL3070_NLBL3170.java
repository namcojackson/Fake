/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/07/2018   CITS            T.Tokutomi      Create          QC#21913
 *</pre>
 */
public class NLBL3070_NLBL3170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (NLBL3070Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
            NLBL3070CMsg bizMsg = new NLBL3070CMsg();
            bizMsg.setBusinessID(NLBL3070Constant.BUSINESS_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            bizMsg.xxNum.setValue(getButtonSelectNumber());

            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        int index = scrnMsg.xxNum.getValueInt();

        scrnMsg.setFocusItem(scrnMsg.B.no(index).proNum_B1);

    }
}
