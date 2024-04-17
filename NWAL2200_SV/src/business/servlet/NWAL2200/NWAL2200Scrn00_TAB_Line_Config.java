/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

// import static
// business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NWAL2200.NWAL2200CMsg;
// import business.servlet.NWAL2200.constant.NWAL2200Constant;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForError;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;
import business.servlet.NWAL2200.constant.NWAL2200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_TAB_Line_Config
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2018/08/31   Fujitsu         K.Ishizuka      Update          S21_NA#26022
 *</pre>
 */
public class NWAL2200Scrn00_TAB_Line_Config extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, true, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        // 2018/08/31 S21_NA#26022 Add Start
        NWAL2200CMsg bizMsg  = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/08/31 S21_NA#26022 Add End

        NWAL2200CommonLogicForScreenFields.setConditionForHeader(this, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setConditionForConfigTab(this, scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, NWAL2200Constant.TAB_LINE_CONFIG);

        NWAL2200CommonLogicForError.setErrorItem(scrnMsg, scrnMsg.xxDplyTab.getValue());

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, null, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

    }
}
