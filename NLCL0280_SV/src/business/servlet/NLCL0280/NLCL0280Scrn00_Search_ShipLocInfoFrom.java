/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import static business.servlet.NLCL0280.constant.NLCL0280Constant.BUSINESS_ID;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.FUNC_SRCH;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.ZZM9000E;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.ZZM9037E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0280.NLCL0280CMsg;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLCL0280Scrn00_Search_ShipLocInfoFrom Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280Scrn00_Search_ShipLocInfoFrom extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // null check
        if (!ZYPCommonFunc.hasValue(scrnMsg.shipFromLocCustCd_H1)) {
            scrnMsg.shipFromLocCustCd_H1.setErrorInfo(1, ZZM9000E, new String[] {"Ship From Location" });
            scrnMsg.setMessageInfo(ZZM9037E);
        }

        scrnMsg.addCheckItem(scrnMsg.shipFromLocCustCd_H1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        NLCL0280CMsg bizMsg = new NLCL0280CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;
        NLCL0280CMsg bizMsg = (NLCL0280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Screen Protect Ctrl
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);

        // Error check
        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.addCheckItem(scrnMsg.shipFromLocCustCd_H1);
            scrnMsg.putErrorScreen();
        }

        scrnMsg.setFocusItem(scrnMsg.shipFromLocCustCd_H1);

    }
}
