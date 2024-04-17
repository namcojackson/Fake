/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 * 2016/06/08   Hitachi         T.Tsuchida      Delete          QC#9637
 *</pre>
 */
public class NLEL0070Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;
//
//        NLEL0070CommonLogic.addCheckItems(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;
//
//        NLEL0070CMsg bizMsg = new NLEL0070CMsg();
//        bizMsg.setBusinessID(NLEL0070Constant.BUSINESS_APPLICATION_ID);
//        bizMsg.setFunctionCode("40");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;
//        NLEL0070CMsg bizMsg = (NLEL0070CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NLEL0070CommonLogic.addCheckItems(scrnMsg);
//        
//        // has error
//        if (scrnMsg.getMessageCode().endsWith("E")) {
//            throw new EZDFieldErrorException();
//        }
//
//        // Protected.
//        NLEL0070CommonLogic.protectedInitEv(scrnMsg, this);
//
//        // Focus.
//        scrnMsg.setFocusItem(scrnMsg.assetTpCd_H1);
//
//        // normal end Message.
//        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            scrnMsg.setMessageInfo(NLEL0070Constant.NZZM0002I);
//        }
    }
}
