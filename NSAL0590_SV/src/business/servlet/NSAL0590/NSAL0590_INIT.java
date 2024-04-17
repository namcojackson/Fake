/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0590;

import static business.servlet.NSAL0590.constant.NSAL0590Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0590.NSAL0590CMsg;
import business.servlet.NSAL0590.common.NSAL0590CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         J.Kim           Create          N/A
 * 2015/12/01   Hitachi         T.Kanasaka      Update          QC#1264
 * 2016/03/25   Hitachi         M.Gotou         Update          QC#4918
 *</pre>
 */
public class NSAL0590_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;

        NSAL0590CMsg bizMsg = new NSAL0590CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;
        NSAL0590CMsg bizMsg = (NSAL0590CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/03/25 M.Gotou [QC#4918, MOD]
//        initialize(this, scrnMsg);
        NSAL0590CommonLogic.initialize(this, scrnMsg);

//        if (scrnMsg.A.getValidCount() > 0) {
//            scrnMsg.setFocusItem(scrnMsg.A.no(0).dsContrRptGrpDescTxt);
//        } else {
//            scrnMsg.setFocusItem(scrnMsg.dsContrRptGrpNum_H);
//        }
        scrnMsg.setFocusItem(scrnMsg.dsContrRptGrpNum_H);
        // END 2016/03/25 M.Gotou [QC#4918, MOD]

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

    }

    // START 2016/03/25 M.Gotou [QC#4918, DEL]
//    /**
//     * Initialize the items and buttons on the screen.
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NSAL0590BMsg
//     */
//    public static void initialize(EZDCommonHandler handler, NSAL0590BMsg scrnMsg) {
//
//        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
//        scrnMsg.setInputProtected(false);
//        scrnMsg.A.setInputProtected(true);
//
//        // set button property
//        // common button
//        handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
//        handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_ACTIVE, null);
//        handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);
//
//        // optional button
//        handler.setButtonEnabled("SEARCH", true);
//        // START 2015/12/01 T.Kanasaka [QC#1264, MOD]
////        handler.setButtonEnabled("InsertRow", false);
//        handler.setButtonEnabled(INSERTROW[0], true);
//        // END 2015/12/01 T.Kanasaka [QC#1264, MOD]
//    }
    // END 2016/03/25 M.Gotou [QC#4918, DEL]

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;

        scrnMsg.dsContrRptGrpNum_H.setNameForMessage("Report Group#");
        scrnMsg.dsContrRptGrpDescTxt_H.setNameForMessage("Description");
        scrnMsg.dsContrRptGrpStartDt_H.setNameForMessage("Start From Date");
        scrnMsg.dsContrRptGrpStartDt_HE.setNameForMessage("Start To Date");
        scrnMsg.dsContrRptGrpEndDt_H.setNameForMessage("End From Date");
        scrnMsg.dsContrRptGrpEndDt_HE.setNameForMessage("End To Date");

        scrnMsg.xxPageShowFromNum.setNameForMessage("Showing From Number");
        scrnMsg.xxPageShowToNum.setNameForMessage("Showing To Number");
        scrnMsg.xxPageShowOfNum.setNameForMessage("Showing Of Number");
    }
}
