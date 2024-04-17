/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0280.NLCL0280CMsg;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;
import business.servlet.NLCL0280.constant.NLCL0280Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   CITS            T.Tokutomi      Create          N/A
 * 05/25/2016   CSAI            Y.Imazu         Update          QC#6864
 *</pre>
 */
public class NLCL0280Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // Clear
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.L);
        scrnMsg.clear();

        // Parameter Set
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            NLCL0280CommonLogic.setInitParam(scrnMsg, params);
        }

        NLCL0280CMsg bizMsg = new NLCL0280CMsg();
        bizMsg.setBusinessID(NLCL0280Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;
        NLCL0280CMsg bizMsg = (NLCL0280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!NLCL0280CommonLogic.chkInitParam(scrnMsg)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.trxDt_H2, ZYPDateUtil.getSalesDate());
        }

        NLCL0280CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind())) {

            NLCL0280CommonLogic.addCheckAllHeaderItem(scrnMsg);
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.trxDt_H1);
    }
}
