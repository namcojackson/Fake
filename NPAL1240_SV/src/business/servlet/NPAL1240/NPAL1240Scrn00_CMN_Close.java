/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1240;

import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.INPUT_PARAM_INDEX_ASL_QLFY_RELN_LIST;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.MAX_NUM_INPUT_PARAM;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import parts.transitioncommon.EZDTransition;
import business.blap.NPAL1240.NPAL1240CMsg;
import business.servlet.NPAL1240.common.NPAL1240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NPAL1240 Qualifier Setup
 * Function Name : The business process for CLOSE.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;

        // checks the parameters.
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).aslQlfyRefCd_A);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return copyScrnMsgToBizMsgForSearch((NPAL1240BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;
        EZDMsg.copy((NPAL1240CMsg) cMsg, null, scrnMsg, null);

        // checks the parameters.
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).aslQlfyRefCd_A);
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            setNextTransition(EZDTransition.STAY, null);
            return;
        }

        // sets the output parameters.
        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(index).aslQlfyRelnPk, scrnMsg.A.no(index).aslQlfyRelnPk_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(index).aslQlfyTpCd, scrnMsg.A.no(index).aslQlfyTpCd_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(index).aslQlfyRefCd, scrnMsg.A.no(index).aslQlfyRefCd_A);
        }
        scrnMsg.R.setValidCount(scrnMsg.A.getValidCount());

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            NPAL1240CommonLogic.setOutputParam((Object[]) arg, scrnMsg.R);
        }

    }
}
