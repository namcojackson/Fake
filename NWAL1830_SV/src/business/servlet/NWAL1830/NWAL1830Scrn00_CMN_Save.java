/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830;

import static business.servlet.NWAL1830.constant.NWAL1830Constant.BIZ_ID;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1830.NWAL1830CMsg;

import business.servlet.NWAL1830.common.NWAL1830CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1830Scrn00_CMN_Save
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1830Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        NWAL1830CommonLogic.inputCheckConvEntry(scrnMsg, true);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;

        NWAL1830CMsg bizMsg = new NWAL1830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        NWAL1830CMsg bizMsg  = (NWAL1830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1830CommonLogic.initCmnBtnProp(this, scrnMsg);
        NWAL1830CommonLogic.setControlField(this, scrnMsg);

        NWAL1830CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == 1 || MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        scrnMsg.setFocusItem(scrnMsg.dsOrdLineCatgCd_OL);

    }
}
