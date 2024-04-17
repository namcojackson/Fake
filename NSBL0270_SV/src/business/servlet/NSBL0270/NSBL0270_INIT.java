/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0270;

import static business.servlet.NSBL0270.constant.NSBL0270Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0270.NSBL0270CMsg;
import business.servlet.NSBL0270.common.NSBL0270CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;

        NSBL0270CMsg bizMsg = new NSBL0270CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        NSBL0270CMsg bizMsg  = (NSBL0270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSBL0270CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSBL0270CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSBL0270CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSBL0270CommonLogic.alternateTableRowColor(scrnMsg);
        NSBL0270CommonLogic.focusItem(scrnMsg);
        NSBL0270CommonLogic.formatItem(scrnMsg);
        NSBL0270CommonLogic.setSplitTblFocus(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        scrnMsg.xxFileData.setNameForMessage("file path");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcLineBizCd_A.setNameForMessage("Line of Business");
            scrnMsg.A.no(i).svcPrcShiftDescTxt_A.setNameForMessage("Description");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A1.setNameForMessage("Monday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A1.setNameForMessage("Monday End");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A2.setNameForMessage("Tuesday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A2.setNameForMessage("Tuesday End");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A3.setNameForMessage("Wednesday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A3.setNameForMessage("Wednesday End");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A4.setNameForMessage("Thursday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A4.setNameForMessage("Thursday End");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A5.setNameForMessage("Friday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A5.setNameForMessage("Friday End");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A6.setNameForMessage("Saturday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A6.setNameForMessage("Saturday End");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A7.setNameForMessage("Sunday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A7.setNameForMessage("Sunday End");
            scrnMsg.A.no(i).xxStartDplyTmTxt_A8.setNameForMessage("Holiday Start");
            scrnMsg.A.no(i).xxEndDplyTmTxt_A8.setNameForMessage("Holiday End");
        }
    }
}
