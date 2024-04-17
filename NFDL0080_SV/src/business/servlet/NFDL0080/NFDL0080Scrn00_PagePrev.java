/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0080.NFDL0080CMsg;
import business.servlet.NFDL0080.common.NFDL0080CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NFDL0080Scrn00_PagePrev.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2024/01/19   Hitachi         S.Ikariya       Update          QC#63449
 * </pre>
 */
public class NFDL0080Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        // START 2024/01/19 S.Ikariya [QC#63449, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2024/01/19 S.Ikariya [QC#63449, ADD]
        // Correlative Check
        NFDL0080CommonLogic.checkPagination(scrnMsg, NFDL0080Scrn00_PagePrev.class.getSimpleName());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0080CMsg bizMsg = null;

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        bizMsg = NFDL0080CommonLogic.setRequestData_NFDL0080_Search();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // ScreenItem AddCheckItem
        NFDL0080CommonLogic.transMsgCheck(scrnMsg);

        // Button Initialize
        NFDL0080CommonLogic.initialize(this, scrnMsg);

        // Button CommonBtnControl
        NFDL0080CommonLogic.commonBtnControl(this);

        // ScreenDetail Control
        NFDL0080CommonLogic.setDetailControl(scrnMsg);

        // PeculiarProcessing
        NFDL0080CommonLogic.protectModeNewly(scrnMsg, this);

        scrnMsg.putErrorScreen();

        NFDL0080CommonLogic.setAppFracDigit(scrnMsg);

    }
}
