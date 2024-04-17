/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410;

import static business.servlet.NWAL2410.constant.NWAL2410Constant.BIZ_ID;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2410.NWAL2410CMsg;
import business.servlet.NWAL2410.common.NWAL2410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2410Scrn00_PageJump
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 * 2017/02/21   Fujitsu         N.Aoyama        Update          QC#17676
 *</pre>
 */
public class NWAL2410Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(//
                scrnMsg //
                , scrnMsg.A //
                , scrnMsg.xxPageShowFromNum //
                , scrnMsg.xxPageShowToNum //
                , scrnMsg.xxPageShowOfNum //
                , scrnMsg.xxPageShowCurNum //
                , scrnMsg.xxPageShowTotNum);

        NWAL2410CommonLogic.addCheckDetailsItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;

        NWAL2410CMsg bizMsg = new NWAL2410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        NWAL2410CMsg bizMsg = (NWAL2410CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2017/02/21 QC#17676 DEL START
        NWAL2410CommonLogic.addCheckDetailsItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
        // 2017/02/21 QC#17676 DEL E N D
        NWAL2410CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWAL2410CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(0).ezUpTime_A)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).docMgtScanBrNm_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).docMgtOrgCd_A);
        }

    }
}
