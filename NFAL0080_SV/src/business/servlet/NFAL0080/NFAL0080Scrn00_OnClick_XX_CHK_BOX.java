/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0080.constant.NFAL0080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0080Scrn00_OnClick_XX_CHK_BOX
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080Scrn00_OnClick_XX_CHK_BOX extends S21CommonHandler implements NFAL0080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        // NFAL0080CMsg bizMsg = new NFAL0080CMsg();
        // bizMsg.setBusinessID("NFAL0080");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;
        // NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                if (scrnMsg.A.no(i).xxChkBox_OR.isClear()) {
                    scrnMsg.A.no(i).xxChkBox_OR.setValue(CHECKED);

                    scrnMsg.eligCoaSegPtrnCd.setValue(scrnMsg.A.no(i).eligCoaSegPtrnCd_A.getValue());
                    scrnMsg.coaSegLkupTpCd_3.setValue(scrnMsg.A.no(i).coaSegLkupTpCd_A3.getValue());
                    scrnMsg.setFocusItem(scrnMsg.A.no(i).coaSegLkupTpCd_A3);
                }
            } else {
                scrnMsg.A.no(i).xxChkBox_OR.clear();
            }
        }
    }

}
