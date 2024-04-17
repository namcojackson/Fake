package business.servlet.NMAL0100;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0100.NMAL0100CMsg;
import business.servlet.NMAL0100.common.NMAL0100CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0100Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_10, scrnMsg.xxPageShowToNum_10, scrnMsg.xxPageShowOfNum_10, scrnMsg.xxPageShowCurNum_10, scrnMsg.xxPageShowTotNum_10);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        S21SequentialSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_10, scrnMsg.xxPageShowToNum_10, scrnMsg.xxPageShowOfNum_10, scrnMsg.xxPageShowCurNum_10, scrnMsg.xxPageShowTotNum_10);
        NMAL0100CMsg bizMsg = new NMAL0100CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        NMAL0100CMsg bizMsg = (NMAL0100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL0100CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);

    }

}
