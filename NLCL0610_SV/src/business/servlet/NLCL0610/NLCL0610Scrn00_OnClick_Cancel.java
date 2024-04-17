/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0610;

import static business.servlet.NLCL0610.constant.NLCL0610Constant.NZZM0011E;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0610.NLCL0610CMsg;
import business.servlet.NLCL0610.common.NLCL0610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610Scrn00_OnClick_Cancel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;

        // checks if the radio button is selected.
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_A)) {
            scrnMsg.setMessageInfo(NZZM0011E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;

        return NLCL0610CommonLogic.copyScrnMsgToBizMsgForUpdate(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;
        NLCL0610CMsg bizMsg  = (NLCL0610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        NLCL0610CommonLogic.setRadioButtonAttribute(scrnMsg);

        NLCL0610CommonLogic.setTableScreen(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            NLCL0610CommonLogic.editButtonProperties(this);
        } else {
            NLCL0610CommonLogic.disableEditButtonProperties(this);
        }
    }
}
