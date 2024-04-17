/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550;


import parts.common.*;
import parts.servletcommon.*;

import business.blap.NMAL6550.NMAL6550CMsg;
import business.servlet.NMAL6550.common.NMAL6550CommonLogic;
import business.servlet.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6550_NMAL6050 extends S21CommonHandler implements NMAL6550Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;
        NMAL6550CMsg bizMsg  = (NMAL6550CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        String prevEventNm = scrnMsg.xxScrEventNm.getValue();

        EZDBStringItem codeItem = null;
        EZDBStringItem nameItem = null;
        // CTRY
        if (NMAL6550Scrn00_OpenWin_CTRY.class.getSimpleName().equals(prevEventNm)) {
            int eventOccuredLine = scrnMsg.xxNum.getValueInt();
            codeItem = scrnMsg.A.no(eventOccuredLine).ctryCd_A1;

            if (!NMAL6550CommonLogic.isClosedEvent(getLastGuard())) {
                codeItem.setValue(scrnMsg.xxCondCd_PP.getValue());
            }
            scrnMsg.setFocusItem(codeItem);

        // CCY
        } else if (NMAL6550Scrn00_OpenWin_CCY.class.getSimpleName().equals(prevEventNm)) {
            int eventOccuredLine = scrnMsg.xxNum.getValueInt();
            codeItem = scrnMsg.A.no(eventOccuredLine).stdCcyCd_A1;
            nameItem = scrnMsg.A.no(eventOccuredLine).stdCcyNm_A1;

            if (!NMAL6550CommonLogic.isClosedEvent(getLastGuard())) {
                codeItem.setValue(scrnMsg.xxCondCd_PP.getValue());
                nameItem.setValue(scrnMsg.xxCondNm_PP.getValue());
            }
            scrnMsg.setFocusItem(codeItem);

        // COA_AFFL
        } else if (NMAL6550Scrn00_OpenWin_COA_AFFL.class.getSimpleName().equals(prevEventNm)) {
            int eventOccuredLine = scrnMsg.xxNum.getValueInt();
            codeItem = scrnMsg.A.no(eventOccuredLine).coaAfflCd_A1;
            nameItem = scrnMsg.A.no(eventOccuredLine).coaAfflNm_A1;

            if (!NMAL6550CommonLogic.isClosedEvent(getLastGuard())) {
                codeItem.setValue(scrnMsg.xxCondCd_PP.getValue());
                nameItem.setValue(scrnMsg.xxCondNm_PP.getValue());
            }
            scrnMsg.setFocusItem(codeItem);

        // ACCT
        } else if (NMAL6550Scrn00_OpenWin_ACCT.class.getSimpleName().equals(prevEventNm)) {
            int eventOccuredLine = scrnMsg.xxNum.getValueInt();
            codeItem = scrnMsg.A.no(eventOccuredLine).acctCd_A1;
            nameItem = scrnMsg.A.no(eventOccuredLine).imptInvCnsgnNm_A1;

            if (!NMAL6550CommonLogic.isClosedEvent(getLastGuard())) {
                codeItem.setValue(scrnMsg.xxCondCd_PP.getValue());
                nameItem.setValue(scrnMsg.xxCondNm_PP.getValue());
            }
            scrnMsg.setFocusItem(codeItem);

        } else {
            // nothing to do.
        }
	}

}
