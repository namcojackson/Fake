/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1340.NPAL1340CMsg;
import business.servlet.NPAL1340.common.NPAL1340CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NPAL1340Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.vndInvNum_H2);
        scrnMsg.addCheckItem(scrnMsg.invDt_H2);
        scrnMsg.addCheckItem(scrnMsg.spTotFuncFrtAmt_H2);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poRcvQty_A1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        NPAL1340CMsg bizMsg = new NPAL1340CMsg();
        bizMsg.setBusinessID("NPAL1340");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        NPAL1340CMsg bizMsg  = (NPAL1340CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.vndInvNum_H2);
        scrnMsg.addCheckItem(scrnMsg.invDt_H2);
        scrnMsg.addCheckItem(scrnMsg.spTotFuncFrtAmt_H2);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poRcvQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).carrNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem20Txt_A1);
        }

        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageInfo() != null && scrnMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }
        NPAL1340CommonLogic.initialize(this, scrnMsg);
    }
}
 