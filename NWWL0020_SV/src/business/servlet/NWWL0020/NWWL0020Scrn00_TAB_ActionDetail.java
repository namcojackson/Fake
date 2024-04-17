/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWWL0020.NWWL0020CMsg;
//import business.servlet.NWWL0020.constant.NWWL0020Constant;

import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_TAB_ActionDetail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_TAB_ActionDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;

        scrnMsg.xxDplyTab.setValue(TAB_NAME_ACTION_DETAIL);
        NWWL0020CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWWL0020CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        NWWL0020CommonLogic.setControlFieldsHdrNm(scrnMsg);
        NWWL0020CommonLogic.setControlFieldsActDtlTabSubmitBtn(scrnMsg, this);
        scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A0);
    }
}
