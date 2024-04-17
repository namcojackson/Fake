/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8830;

import static business.servlet.NYEL8830.constant.NYEL8830Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NYEL8830.NYEL8830CMsg;
import business.servlet.NYEL8830.common.NYEL8830CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8830Scrn00_Refresh
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/12   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8830Scrn00_Refresh extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);

        NYEL8830CMsg bizMsg = new NYEL8830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;
        NYEL8830CMsg bizMsg = (NYEL8830CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8830CommonLogic.initRowCtrlProp(scrnMsg);
        NYEL8830CommonLogic.initCmnBtnProp(this);

        NYEL8830CommonLogic.setRowsBGWithClearForSts(scrnMsg, scrnMsg.A, "A");
        NYEL8830CommonLogic.setRowsBGWithClearForHist(scrnMsg, scrnMsg.B, "B");

        if (scrnMsg.getMessageCode().endsWith("E")) {
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.wfProcNm);
    }
}
