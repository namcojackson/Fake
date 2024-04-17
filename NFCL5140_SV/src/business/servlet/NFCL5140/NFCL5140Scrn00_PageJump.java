/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 11/04/2010   Fujitsu         I.Kondo         Update          DefID:M15
 * </pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5140.NFCL5140CMsg;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140Scrn00_PageJump class.
 */
public class NFCL5140Scrn00_PageJump extends S21CommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
        NFCL5140CMsg bizMsg = new NFCL5140CMsg();
        bizMsg.setBusinessID("NFCL5140");
        bizMsg.setFunctionCode("20");

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
        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL5140CommonLogic.protectDetailCmnt(scrnMsg);

        NFCL5140CommonLogic.setRowBg(scrnMsg);

        NFCL5140CommonLogic.setAppFracDigit(scrnMsg);
    }

}
