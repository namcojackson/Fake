/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3160.NLBL3160CMsg;
import business.servlet.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLBL3160Scrn00_Get_Customer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum)) {
            scrnMsg.dsAcctNum.setErrorInfo(1, "ZZM9000E", new String[] {"Customer" });
        }

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;

        NLBL3160CMsg bizMsg = new NLBL3160CMsg();
        bizMsg.setBusinessID(NLBL3160Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CMsg bizMsg = (NLBL3160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.dsAcctNum);
    }
}
