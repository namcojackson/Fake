/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.BIZ_APP_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SRCH_FUNCTION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0090.NLBL0090CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_SellToButton extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_H2)) {
            NLBL0090CMsg bizMsg = new NLBL0090CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(SRCH_FUNCTION_ID);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else {
            return null;
        }

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = (NLBL0090CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_H2)) {
            scrnMsg.dsAcctNm_H2.clear();
        }

        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H2);
        scrnMsg.putErrorScreen();

        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_H2)) {
            if (!scrnMsg.sellToCustCd_H2.isError()) {
                scrnMsg.setFocusItem(scrnMsg.shipToCustCd_H2);
            }
        }

    }

}
