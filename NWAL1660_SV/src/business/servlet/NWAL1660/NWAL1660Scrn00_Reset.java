/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1660;

//import static business.servlet.NWAL1660.constant.NWAL1660Constant.BIZ_ID;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1660.NWAL1660CMsg;
//import business.servlet.NWAL1660.constant.NWAL1660Constant;

import business.blap.NWAL1660.NWAL1660CMsg;
import business.servlet.NWAL1660.common.NWAL1660CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1660Scrn00_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL1660Scrn00_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1660BMsg scrnMsg = (NWAL1660BMsg) bMsg;
        NWAL1660CMsg bizMsg = new NWAL1660CMsg();

        Object[] params = (Object[]) getArgForSubScreen();
        //
        if (params != null) {
            NWAL1660CommonLogic.setInputParam(scrnMsg, (Object[]) params);
        }
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1660BMsg scrnMsg = (NWAL1660BMsg) bMsg;
        NWAL1660CMsg bizMsg = (NWAL1660CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1660CommonLogic.initCmnBtnProp(this);
        NWAL1660CommonLogic.setScreenItemCondition(this, scrnMsg);
        NWAL1660CommonLogic.setScreenItemCondition(this, scrnMsg);
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A1");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A2");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B1");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B2");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        NWAL1660CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
