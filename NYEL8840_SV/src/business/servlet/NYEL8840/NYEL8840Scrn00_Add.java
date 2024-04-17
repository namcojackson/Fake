/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840;

import static business.servlet.NYEL8840.constant.NYEL8840Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8840.NYEL8840CMsg;
import business.servlet.NYEL8840.common.NYEL8840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8840Scrn00_Add
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.xxWfAsgCd_F);
        scrnMsg.addCheckItem(scrnMsg.xxWfAsgCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.wfDescTxt);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;

        NYEL8840CMsg bizMsg = new NYEL8840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode("40");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        NYEL8840CMsg bizMsg = (NYEL8840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.xxWfAsgCd_F);
        scrnMsg.addCheckItem(scrnMsg.xxWfAsgCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NYEL8840CommonLogic.buttonCtrl(this, scrnMsg);
////        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
//        if (scrnMsg.xxPageShowOfNum.getValueInt() == scrnMsg.xxMaxSrchCnt.getValueInt()) {
//            setButtonEnabled("Add", false);
//        } else {
//            setButtonEnabled("Add", true);
//        }
//
//        if (scrnMsg.A.getValidCount() > 0) {
//            setButtonEnabled("Delete", true);
//        }

        NYEL8840CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        scrnMsg.setFocusItem(scrnMsg.xxWfAsgCd);

    }
}
