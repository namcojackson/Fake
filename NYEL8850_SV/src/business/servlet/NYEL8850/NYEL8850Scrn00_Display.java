/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8850;

import static business.servlet.NYEL8850.constant.NYEL8850Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
                                                  // business.blap.NYEL8850.NYEL8850CMsg;
// import business.servlet.NYEL8850.constant.NYEL8850Constant;

import business.blap.NYEL8850.NYEL8850CMsg;
import business.servlet.NYEL8850.common.NYEL8850CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8850Scrn00_Display
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8850Scrn00_Display extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NYEL8850BMsg scrnMsg = (NYEL8850BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8850BMsg scrnMsg = (NYEL8850BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.H);
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);

        int btnIndex = this.getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(btnIndex);

        NYEL8850CMsg bizMsg = new NYEL8850CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8850BMsg scrnMsg = (NYEL8850BMsg) bMsg;
        NYEL8850CMsg bizMsg = (NYEL8850CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8850CommonLogic.initRowCtrlProp(scrnMsg);
        NYEL8850CommonLogic.initCmnBtnProp(this);

        NYEL8850CommonLogic.setRowsBGWithClearForHead(scrnMsg, scrnMsg.H, "H");
        NYEL8850CommonLogic.setRowsBGWithClearForSts(scrnMsg, scrnMsg.A, "A");
        NYEL8850CommonLogic.setRowsBGWithClearForHist(scrnMsg, scrnMsg.B, "B");

        if (scrnMsg.getMessageCode().endsWith("E")) {
            return;
        }

        //scrnMsg.setFocusItem(scrnMsg.wfProcDocId);

    }
}
