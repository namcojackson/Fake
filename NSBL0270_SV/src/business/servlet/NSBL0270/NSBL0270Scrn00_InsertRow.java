/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0270;

import static business.servlet.NSBL0270.constant.NSBL0270Constant.BIZ_ID;
import static business.servlet.NSBL0270.constant.NSBL0270Constant.SCR_ID_00;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0270.NSBL0270CMsg;
import business.servlet.NSBL0270.common.NSBL0270CommonLogic;
import business.servlet.NSBL0270.constant.NSBL0270Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSBL0270Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NSBL0270Constant.NSAM0320E, new String[]{"Insert line", String.valueOf(scrnMsg.A.length())});
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;

        NSBL0270CMsg bizMsg = new NSBL0270CMsg();
        bizMsg.setBusinessID("NSBL0270");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        NSBL0270CMsg bizMsg  = (NSBL0270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        scrnMsg.clearAllGUIAttribute(SCR_ID_00);
        NSBL0270CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSBL0270CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSBL0270CommonLogic.alternateTableRowColor(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).xxChkBox_A1);
        NSBL0270CommonLogic.formatItem(scrnMsg);
        NSBL0270CommonLogic.setSplitTblFocus(scrnMsg);

    }
}
