/**
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFDL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0070.NFDL0070CMsg;
import business.servlet.NFDL0070.common.NFDL0070CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFDL0070Scrn00_PageJump.
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NFDL0070Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0070CMsg bizMsg = null;

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;

        bizMsg = NFDL0070CommonLogic.setRequestData_NFDL0070Scrn00_PageNext(scrnMsg);

        int pagenationFrom = scrnMsg.xxPageShowFromNum.getValueInt();
        scrnMsg.xxPageShowFromNum_H1.setValue(pagenationFrom);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

        bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt());
        bizMsg.xxPageShowToNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        bizMsg.xxPageShowOfNum.setValue(scrnMsg.xxPageShowOfNum.getValueInt());
        bizMsg.xxPageShowCurNum.setValue(scrnMsg.xxPageShowCurNum.getValueInt());
        bizMsg.xxPageShowTotNum.setValue(scrnMsg.xxPageShowTotNum.getValueInt());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0070CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

    }
}
