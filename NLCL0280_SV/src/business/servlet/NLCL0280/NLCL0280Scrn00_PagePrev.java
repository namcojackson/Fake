/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import static business.servlet.NLCL0280.constant.NLCL0280Constant.BUSINESS_ID;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.FUNC_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLCL0280.NLCL0280CMsg;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   CITS            T.Tokutomi      Create          N/A
 * 2017/06/14   CITS            K.Ogino         Update          QC#19114
 *</pre>
 */
public class NLCL0280Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
        scrnMsg.xxPageShowToNum.clear();

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
        NLCL0280CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);
    }
}