/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2220.common.NWAL2220CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NWAL2220_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        getArgForSubScreen();

        if (!NWAL2220CommonLogic.isClosedEvent(getLastGuard())) {
            if (EVNT_NM_BILL_TO_CUST.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.billToCustAcctCd, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm_BT, scrnMsg.xxPopPrm_01);
                setValue(scrnMsg.billToCustCd, scrnMsg.xxPopPrm_15);
                scrnMsg.setFocusItem(scrnMsg.billToCustAcctCd);
            }
            if (EVNT_NM_SHIP_TO_CUST.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.shipToCustAcctCd, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm_HT, scrnMsg.xxPopPrm_01);
                setValue(scrnMsg.shipToCustCd, scrnMsg.xxPopPrm_16);
                scrnMsg.setFocusItem(scrnMsg.shipToCustAcctCd);
            }
            if (EVNT_NM_SOLD_TO_CUST.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.sellToCustCd, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm_OT, scrnMsg.xxPopPrm_01);
                setValue(scrnMsg.soldToCustLocCd, scrnMsg.xxPopPrm_15);
                scrnMsg.setFocusItem(scrnMsg.sellToCustCd);
            }
        }
    }
}
