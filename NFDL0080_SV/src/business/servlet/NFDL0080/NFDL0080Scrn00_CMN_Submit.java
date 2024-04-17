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
 * NFDL0080Scrn00_CMN_Submit.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2018/04/24   Hitachi         Y.Takeno         Update          QC#20940
 * </pre>
 */
public class NFDL0080Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        // START 2018/04/24 [QC#20940, ADD]
        scrnMsg.addCheckItem(scrnMsg.dtlNoteTxt_H1);
        // END   2018/04/24 [QC#20940, ADD]

        // Correlative Check
        NFDL0080CommonLogic.checkPagination(scrnMsg, NFDL0080Scrn00_CMN_Submit.class.getSimpleName());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0080CMsg bizMsg = null;

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        bizMsg = NFDL0080CommonLogic.setRequestData_NFDL0080_Update();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        if (!"E".equals(bizMsg.getMessageKind())) {

            NFDL0080CommonLogic.setDetailControl(scrnMsg);
            NFDL0080CommonLogic.protectAddDetailLine(scrnMsg, this);

            scrnMsg.putErrorScreen();

            // Button Initialize
            NFDL0080CommonLogic.initialize(this, scrnMsg);

            // Button CommonBtnControl
            NFDL0080CommonLogic.commonBtnControl(this);

            // ScreenDetail Control
            NFDL0080CommonLogic.setDetailControl(scrnMsg);

            NFDL0080CommonLogic.protectModeNewly(scrnMsg, this);

            scrnMsg.putErrorScreen();

        }

        // PeculiarProcessing
        if (bizMsg != null) {

            if ("E".equals(bizMsg.getMessageKind())) {

                NFDL0080CommonLogic.protectModeNewly(scrnMsg, this);

                NFDL0080CommonLogic.transMsgCheck(scrnMsg);
                scrnMsg.putErrorScreen();

            }

        }
        NFDL0080CommonLogic.setButtonConfirmMsgCmnReturn(scrnMsg, this);

    }
}
