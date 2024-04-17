/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0270;

import static business.servlet.NSBL0270.constant.NSBL0270Constant.BIZ_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0270.NSBL0270CMsg;
import business.servlet.NSBL0270.common.NSBL0270CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcLineBizCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcPrcShiftDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A8);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A8);
            NSBL0270CommonLogic.checkFormatTm(scrnMsg.A.no(i));
            NSBL0270CommonLogic.formatFlg(scrnMsg.A.no(i));
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;

        NSBL0270CMsg bizMsg = new NSBL0270CMsg();
        bizMsg.setBusinessID("NSBL0270");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        NSBL0270CMsg bizMsg  = (NSBL0270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcLineBizCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcPrcShiftDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A8);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A8);
        }
        scrnMsg.putErrorScreen();
        NSBL0270CommonLogic.focusItem(scrnMsg);
        NSBL0270CommonLogic.formatItem(scrnMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSBL0270CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSBL0270CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSBL0270CommonLogic.alternateTableRowColor(scrnMsg);
        NSBL0270CommonLogic.setSplitTblFocus(scrnMsg);
    }
}
