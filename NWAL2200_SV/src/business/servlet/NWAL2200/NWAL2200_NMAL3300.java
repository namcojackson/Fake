/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/07/25   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 *</pre>
 */
public class NWAL2200_NMAL3300 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;

        // Add Start 2018/07/25 S21_NA#14307
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            NWAL2200CommonLogicForScreenFields.setProtectByOrdCatgBizCtx(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);
            return;

        } else if ("OnChange_OrderReason".equals(scrEventNm)) {
            NWAL2200CommonLogicForScreenFields.setProtectByOrdCatgBizCtx(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.dsOrdRsnCd);
            return;

        } else if ("OnBlur_DeriveFromBillToAccount".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
            return;

        } else if ("OnBlur_DeriveFromBillToLocation".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
            return;

        } else if ("OnBlur_DeriveFromBillToName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
            return;

        } else if ("OnBlur_DeriveFromShipToAccount".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
            return;

        } else if ("OnBlur_DeriveFromShipToLocation".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
            return;

        } else if ("OnBlur_DeriveFromShipToName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
            return;

        } else if ("OnBlur_DeriveFromSoldToAccount".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.negoDealAmt);
            return;

        } else if ("OnBlur_DeriveFromSoldToLocation".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.negoDealAmt);
            return;

        } else if ("OnBlur_DeriveFromSoldToName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.negoDealAmt);
            return;

        } 
        // Add End 2018/07/25 S21_NA#14307
    }
}
