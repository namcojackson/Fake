/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.BIZ_ID;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.TAB_SHIPPING_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1530.NWAL1530CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1530Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;
        NWAL1530CMsg bizMsg = new NWAL1530CMsg();

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_BK)) {

            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else {

            scrnMsg.clear();
            scrnMsg.A.setValidCount(0);
            scrnMsg.B.setValidCount(0);
            scrnMsg.C.setValidCount(0);
            scrnMsg.D.setValidCount(0);
            scrnMsg.E.setValidCount(0);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;
        NWAL1530CMsg bizMsg = (NWAL1530CMsg) cMsg;

        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_BK)) {
            setButtonEnabled("Search_Order", false);
        }

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
        scrnMsg.xxDplyTab.setValue(TAB_SHIPPING_DETAIL);
    }
}
