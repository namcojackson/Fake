/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1810;

import static business.servlet.NWAL1810.constant.NWAL1810Constant.BIZ_ID;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.TAB_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1810.NWAL1810CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1810Scrn00_TAB_Detail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1810Scrn00_TAB_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;

        NWAL1810CMsg bizMsg = new NWAL1810CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;
        NWAL1810CMsg bizMsg = (NWAL1810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController("NWAL1810Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
    }
}
