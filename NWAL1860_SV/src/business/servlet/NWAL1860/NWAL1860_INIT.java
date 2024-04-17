/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1860;

import static business.servlet.NWAL1860.constant.NWAL1860Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1860.NWAL1860CMsg;
import business.servlet.NWAL1860.common.NWAL1860CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1860_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1860_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;
        NWAL1860CMsg bizMsg = new NWAL1860CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 8) {
            boolean rc = NWAL1860CommonLogic.setInputParam(scrnMsg, params);
            if (!rc) {
                return null;
            }
        } else {
            scrnMsg.setMessageInfo("NWAM0270E");
            return null;
        }
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;
        NWAL1860CMsg bizMsg = (NWAL1860CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        NWAL1860CommonLogic.initCmnBtnProp(this);
        NWAL1860CommonLogic.controlScreenFields(scrnMsg);
        NWAL1860CommonLogic.initVal(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.ordQty);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;
        scrnMsg.ordQty.setNameForMessage("Each Delivery Qty");
        scrnMsg.shpgIntvlCd.setNameForMessage("Shipping Interval");
        scrnMsg.xxDay.setNameForMessage("Delivery Month Day");
        scrnMsg.xxMthDt_ST.setNameForMessage("Start Deliveries Months");
        scrnMsg.xxYrDt_ST.setNameForMessage("Start Deliveries Year");
        scrnMsg.xxMthDt_EN.setNameForMessage("End Deliveries Months");
        scrnMsg.xxYrDt_EN.setNameForMessage("End Deliveries Year");
        scrnMsg.schdAgmtVldFromDt.setNameForMessage("Valid Date From");
        scrnMsg.schdAgmtVldThruDt.setNameForMessage("Valid Date Through");
        scrnMsg.pddDt.setNameForMessage("Planed Max Date");
    }

}
