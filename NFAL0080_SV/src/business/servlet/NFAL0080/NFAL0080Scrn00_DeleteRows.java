/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0080;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0080.NFAL0080CMsg;
import business.servlet.NFAL0080.common.NFAL0080CommonLogic;
import business.servlet.NFAL0080.constant.NFAL0080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0080Scrn00_DeleteRows
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080Scrn00_DeleteRows extends S21CommonHandler implements NFAL0080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        int chkCnt = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                chkCnt++;
            }
        }

        if (chkCnt == 0) {
            scrnMsg.setMessageInfo(NFAM0075E);
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        NFAL0080CMsg bizMsg = new NFAL0080CMsg();
        bizMsg.setBusinessID("NFAL0080");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;
        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0080CommonLogic.initFocusItem(scrnMsg);

        if (MESSAGE_KIND_I.equals(bizMsg.getMessageKind())) {
            NFAL0080CommonLogic.initButton(scrnMsg, this);
            NFAL0080CommonLogic.setNameForMessageCommon(scrnMsg);
            NFAL0080CommonLogic.setProtectEligCoaSetPtrnCdList(scrnMsg);
        } else {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).eligCoaSegPtrnCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).coaSegLkupTpCd_A3);
            }
            scrnMsg.putErrorScreen();
        }

    }

}
