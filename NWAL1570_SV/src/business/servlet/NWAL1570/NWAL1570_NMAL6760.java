/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.BILL_EVENT_LIST;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SHIP_EVENT_LIST;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SOLD_EVENT_LIST;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import business.blap.NWAL1570.NWAL1570CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1570_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (BILL_EVENT_LIST.contains(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToLocCdSrchTxt, scrnMsg.xxPopPrm_P7);
        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctCdSrchTxt, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToLocCdSrchTxt, scrnMsg.xxPopPrm_P7);
        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctCdSrchTxt, scrnMsg.xxPopPrm_P6);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToLocCdSrchTxt, scrnMsg.xxPopPrm_P7);
        }

        NWAL1570CMsg bizMsg = new NWAL1570CMsg();
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

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg = (NWAL1570CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        // Set Focus
        if ("OpenWin_SoldToName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxSoldToAcctNmSrchTxt);
        } else if ("OpenWin_SoldToAcctNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxSoldToAcctCdSrchTxt);
        } else if ("OpenWin_SoldToLocNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxSoldToLocCdSrchTxt);
        } else if ("OpenWin_ShipToName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxShipToAcctNmSrchTxt);
        } else if ("OpenWin_ShipToAcctNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxShipToAcctCdSrchTxt);
        } else if ("OpenWin_ShipToLoctNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxShipToLocCdSrchTxt);
        } else if ("OpenWin_BillToName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxBillToAcctNmSrchTxt);
        } else if ("OpenWin_BillToAcctNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxBillToAcctCdSrchTxt);
        } else if ("OpenWin_BillToLocNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxBillToLocCdSrchTxt);
        }
    }
}
