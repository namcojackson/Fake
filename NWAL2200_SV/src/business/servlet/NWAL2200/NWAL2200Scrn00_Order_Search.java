/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.ZZM9000E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForError;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;
import business.servlet.NWAL2200.constant.NWAL2200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_Order_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2018/11/29   Fujitsu         Y.Kanefusa      Update          S21_NA#29282
 *</pre>
 */
public class NWAL2200Scrn00_Order_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.ordSrcRefNum.getNameForMessage())) {
            scrnMsg.ordSrcRefNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.ordSrcRefNum.getNameForMessage() });
        }
        scrnMsg.addCheckItem(scrnMsg.ordSrcRefNum);
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
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.ordSrcRefNum);
        scrnMsg.putErrorScreen();

        NWAL2200CommonLogicForScreenFields.setConditionForHeader(this, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setConditionForHeaderTab(this, scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, NWAL2200Constant.TAB_HEADER);

        NWAL2200CommonLogicForError.setErrorItem(scrnMsg, null);

        // QC#29282 2018/11/29 Add Start
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200CommonLogicForScreenFields.setConditionForConfigTab(this, scrnMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200CommonLogicForScreenFields.setConditionForRmaTab(this, scrnMsg);
        } else {
            NWAL2200CommonLogicForScreenFields.setConditionForHeaderTab(this, scrnMsg);
        }
        // QC#29282 2018/11/29 Add End

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, null, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.ordSrcRefNum);
    }
}
