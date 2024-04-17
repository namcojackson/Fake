/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3050.NLBL3050CMsg;
import business.servlet.NLBL3050.common.NLBL3050CommonLogic;
import business.servlet.NLBL3050.constant.NLBL3050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu         W.Honda         Create          N/A
 * 01/21/2016   CSAI            Y.Imazu         Update          QC#2048
 *</pre>
 */
public class NLBL3050Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        scrnMsg.xxListNum_A1.clear();
        scrnMsg.xxListNum_B1.clear();
        scrnMsg.xxListNum_C1.clear();
        scrnMsg.xxListNum_D1.clear();
        scrnMsg.xxListNum_F1.clear();
        scrnMsg.xxListNum_H1.clear();

        NLBL3050CMsg bizMsg = new NLBL3050CMsg();

        bizMsg.setBusinessID(NLBL3050Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;
        NLBL3050CMsg bizMsg  = (NLBL3050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3050CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoNum_H);
    }
}
