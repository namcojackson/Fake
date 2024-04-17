/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLBL2020.NLBL2020CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/06/2018   CITS            T.Tokutomi      Create          QC#21913
 *</pre>
 */
public class NLBL2020_NLBL3170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

            NLBL2020CMsg bizMsg = new NLBL2020CMsg();
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
        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        int index = scrnMsg.xxNum.getValueInt();

        scrnMsg.setFocusItem(scrnMsg.A.no(index).proNum_A1);

    }
}
