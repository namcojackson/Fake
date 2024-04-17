/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;

import business.blap.NFDL0080.NFDL0080CMsg;
import business.servlet.NFDL0080.constant.NFDL0080Constant;
import business.servlet.NFDL0080.common.NFDL0080CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2024/01/19   Hitachi         S.Ikariya       Update          QC#63449
 *</pre>
 */
public class NFDL0080Scrn00_Refresh extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;
        // START 2024/01/19 S.Ikariya [QC#63449, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2024/01/19 S.Ikariya [QC#63449, ADD]

        NFDL0080CMsg bizMsg = new NFDL0080CMsg();
        bizMsg = NFDL0080CommonLogic.setRequestData_NFDL0080_Search();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;
        NFDL0080CMsg bizMsg  = (NFDL0080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // ScreenItem AddCheckItem
        NFDL0080CommonLogic.transMsgCheck(scrnMsg);

        // Button Initialize
        NFDL0080CommonLogic.initialize(this, scrnMsg);

        // Button CommonBtnControl
        NFDL0080CommonLogic.commonBtnControl(this);

        // ScreenDetail Control
        NFDL0080CommonLogic.setDetailControl(scrnMsg);

        NFDL0080CommonLogic.protectModeNewly(scrnMsg, this);

        scrnMsg.putErrorScreen();

        NFDL0080CommonLogic.setAppFracDigit(scrnMsg);

        S21SortColumnIMGController.clearIMG(NFDL0080Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
