/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_BILL_TO_SEARCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_BILL_TO_SEARCH_TRX;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_SHIP_TO_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 *</pre>
 */
public class NMAL6720_NMAL6780 extends S21CommonHandler {

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

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        if (EVENT_BILL_TO_SEARCH_TRX.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            int index = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(index).dsDefBillToCd_D1, scrnMsg.P.no(1).xxPopPrm_P);
            scrnMsg.setFocusItem(scrnMsg.D.no(index).dsDefBillToCd_D1);
        } else if (EVENT_SHIP_TO_SEARCH.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            int index = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(getButtonSelectNumber()).dsDefShipToCd_D1, scrnMsg.P.no(2).xxPopPrm_P);
            scrnMsg.setFocusItem(scrnMsg.D.no(index).dsDefShipToCd_D1);
        } else if (EVENT_BILL_TO_SEARCH.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_SH, scrnMsg.P.no(1).xxPopPrm_P);
            scrnMsg.setFocusItem(scrnMsg.billToCustCd_SH);
        }
    }
}
