/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830;

import static business.servlet.NWAL1830.constant.NWAL1830Constant.BIZ_ID;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import business.blap.NWAL1830.NWAL1830CMsg;
import business.servlet.NWAL1830.common.NWAL1830CommonLogic;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1830_NWAL1500
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1830_NWAL1500 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;

        NWAL1830CMsg bizMsg = new NWAL1830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        NWAL1830CMsg bizMsg  = (NWAL1830CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        NWAL1830CommonLogic.initCmnBtnProp(this, scrnMsg);
        NWAL1830CommonLogic.setControlField(this, scrnMsg);

        if (scrnMsg.getMessageType() == 1 || MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.dsOrdLineCatgCd_OL);
    }
}