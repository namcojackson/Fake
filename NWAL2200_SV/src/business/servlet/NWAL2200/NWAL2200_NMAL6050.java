/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2017/02/24   Fujitsu         Y.Kanefusa      Update          S21_NA#13688
 *</pre>
 */
public class NWAL2200_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // return null;
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg  = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_FreightTerms".equals(scrEventNm)) {
            scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
            NWAL2200CommonLogicForScreenFields.setConditionForAddlTab(this, scrnMsg); // QC#13688 2017/02/24 Add
            scrnMsg.putErrorScreen();
        } else if ("OnBlur_DeriveFromFreightTerms".equals(scrEventNm)) {// QC#13688 2017/02/24 Add
            NWAL2200CommonLogicForScreenFields.setConditionForAddlTab(this, scrnMsg);
        } else if ("OpenWin_PaymentTerms".equals(scrEventNm)) {
            scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscDescTxt);
            scrnMsg.putErrorScreen();
        }

        if ("OpenWin_FreightTerms".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.frtCondDescTxt);
        } else if ("OpenWin_PaymentTerms".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.pmtTermCashDiscDescTxt);
        }
    }
}
