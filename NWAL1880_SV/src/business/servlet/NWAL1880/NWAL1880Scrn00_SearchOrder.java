/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1880.NWAL1880CMsg;
import business.servlet.NWAL1880.common.NWAL1880CommonLogic;
import business.servlet.NWAL1880.constant.NWAL1880Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1880Scrn00_SearchOrder extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;
        // 1. check inquiry criteria values on NWAL1880Scrn00.
        NWAL1880CommonLogic.addCheckItemBizLayerErr(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.D);
        ZYPTableUtil.clear(scrnMsg.E);
        ZYPTableUtil.clear(scrnMsg.F);
        ZYPTableUtil.clear(scrnMsg.G);

        NWAL1880CMsg bizMsg = new NWAL1880CMsg();
        bizMsg.setBusinessID("NWAL1880");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;
        NWAL1880CMsg bizMsg  = (NWAL1880CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NWAL1880CommonLogic.hasMsgError(bizMsg)) {
            throw new EZDFieldErrorException();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NWAL1880Constant.ZZM8100I);
        }

        // 1. initialize GUI attribute.
        NWAL1880CommonLogic.initGuiAttrScrnRslt(this, scrnMsg);

        // 2. set alternate rows back-ground color
        NWAL1880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWAL1880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        NWAL1880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        NWAL1880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.D, "D");
        NWAL1880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.E, "E");
        NWAL1880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.F, "F");
        NWAL1880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.G, "G");

    }
}
