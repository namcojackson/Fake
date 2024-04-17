/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2009   Fujitsu         I.Kondo         Create          N/A
 * 2011/10/27   CSAI            N.Sasaki        Update          362045
 *</pre>
 */
package business.servlet.NPAL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL0100.NPAL0100CMsg;
import business.servlet.NPAL0100.common.NPAL0100CommonLogic;
import business.servlet.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NPAL0100Scrn00_PagePrev extends S21CommonHandler implements NPAL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        NPAL0100CommonLogic.checkInputForApply(this, scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        NPAL0100CMsg bizMsg = NPAL0100CommonLogic.setRequestDataToBizMsg();

        NPAL0100CommonLogic.setRequestDataForApply(this, scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
        }
        scrnMsg.putErrorScreen();

        if (ZYPCommonFunc.hasValue(scrnMsg.xxErrFlg) && ERROR_FLG_ON.equals(scrnMsg.xxErrFlg.getValue())) {
            throw new EZDFieldErrorException();
        }

        NPAL0100CommonLogic.initialize(this, scrnMsg);
        // Def# 362045 TODO
        if( scrnMsg.eventId.getValue().equals(SUBMIT)) {
            NPAL0100CommonLogic.afterSubmit(this, scrnMsg);
        }
    }

}
