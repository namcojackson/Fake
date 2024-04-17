/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2200.common.NWAL2200CommonLogicForError;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;
import business.servlet.NWAL2200.constant.NWAL2200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_TAB_Addl_Header
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200Scrn00_TAB_Addl_Header extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, true, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.setConditionForHeader(this, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setConditionForAddlTab(this, scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, NWAL2200Constant.TAB_ADDL_HEADER);

        NWAL2200CommonLogicForError.setErrorItem(scrnMsg, scrnMsg.xxDplyTab.getValue());

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, null, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }
}
