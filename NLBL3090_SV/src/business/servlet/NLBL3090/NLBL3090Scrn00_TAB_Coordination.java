/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3090;

import static business.servlet.NLBL3090.constant.NLBL3090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.TAB_ASSIGN;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.TAB_COORDINATION;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3090.NLBL3090CMsg;
import business.servlet.NLBL3090.common.NLBL3090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * Function Name : Button Action to Coordination Tab
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 *</pre>
 */
public class NLBL3090Scrn00_TAB_Coordination extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        NLBL3090CMsg bizMsg = null;
        if (TAB_ASSIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.xxDplyTab.setValue(TAB_COORDINATION);
            if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)) {
                scrnMsg.xxDplyTab_O.setValue(TAB_ASSIGN);
                bizMsg = new NLBL3090CMsg();
                bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
            } else {
                scrnMsg.xxDplyTab_O.clear();
            }
        } else {
            scrnMsg.xxDplyTab_O.clear();
        }
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        NLBL3090CMsg bizMsg = (NLBL3090CMsg) cMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_O)) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NLBL3090CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLBL3090CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.B.no(0).getBaseContents());
        NLBL3090CommonLogic.control(this, scrnMsg);

        if (bizMsg != null && "E".equals(bizMsg.getMessageKind())) {
            NLBL3090CommonLogic.addCheckSearchItem(scrnMsg);
            scrnMsg.putErrorScreen();
        }
    }
}
