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

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_Search_Order
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/28   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/23   Fujitsu         M.suzuki        Update          S21_NA#1975
 *</pre>
 */
public class NWAL1530Scrn00_Search_Order extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;

        NWAL1530CMsg bizMsg = new NWAL1530CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;
        NWAL1530CMsg bizMsg = (NWAL1530CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
        scrnMsg.xxDplyTab.setValue(TAB_SHIPPING_DETAIL);
    }
}