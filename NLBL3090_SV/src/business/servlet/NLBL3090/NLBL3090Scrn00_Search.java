/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3090;

import static business.servlet.NLBL3090.constant.NLBL3090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.*;
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
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 *</pre>
 */
public class NLBL3090Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)) {
            scrnMsg.rtlWhCd_H1.setErrorInfo(1, NLAM0001E);
        }
        if ((ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) && (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1))) {
            if (scrnMsg.effThruDt_H1.getValue().compareTo(scrnMsg.effFromDt_H1.getValue()) < 0) {
                scrnMsg.effFromDt_H1.setErrorInfo(1, ZZSM4100E);
                scrnMsg.effThruDt_H1.setErrorInfo(1, ZZSM4100E);
            }
        }
        NLBL3090CommonLogic.addCheckSearchItem(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        NLBL3090CMsg bizMsg = new NLBL3090CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        NLBL3090CMsg bizMsg = (NLBL3090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab_O.clear();
        NLBL3090CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLBL3090CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.B.no(0).getBaseContents());
        NLBL3090CommonLogic.control(this, scrnMsg);

        if ("E".equals(bizMsg.getMessageKind())) {
            NLBL3090CommonLogic.addCheckSearchItem(scrnMsg);
            scrnMsg.putErrorScreen();
        }
    }
}
