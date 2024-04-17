/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0080.common.NFDL0080CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NFDL0080Scrn00_CMN_Clear.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2018/07/24   Hitachi         Y.Takeno         Update          QC#26989-1
 * 2019/07/31   Fujitsu         M.Ishii          Update          QC#52217
 * </pre>
 */
public class NFDL0080Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        scrnMsg.xxChkBox_H1.clear();
        scrnMsg.dsAcctNum_H2.clear();
        // 2019/07/31 QC#52217 Mod Start
//        scrnMsg.xxTrxCdSrchTxt_H1.clear();
        scrnMsg.xxTrxNumSrchTxt_H1.clear();
        // 2019/07/31 QC#52217 Mod End
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.clear();
        }
        // START 2018/04/24 [QC#20940, ADD]
        scrnMsg.dtlNoteTxt_H1.clear();
        // END   2018/04/24 [QC#20940, ADD]
        // START 2018/07/24 [QC#26989-1, ADD]
        scrnMsg.custIssPoNum_H2.clear();
        // END   2018/07/24 [QC#26989-1, ADD]

        // ScreenItem AddCheckItem
        NFDL0080CommonLogic.transMsgCheck(scrnMsg);

        // Button Initialize
        NFDL0080CommonLogic.initialize(this, scrnMsg);

        // Button CommonBtnControl
        NFDL0080CommonLogic.commonBtnControl(this);

        // ScreenDetail Control
        NFDL0080CommonLogic.setDetailControl(scrnMsg);
    }

}
