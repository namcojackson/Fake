/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2400;

import static business.servlet.NWAL2400.constant.NWAL2400Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.EVENT_NM_COA_BR;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.EVENT_NM_ORDER_CATEGORY;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.EVENT_NM_ORDER_REASON;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/08/02   CITS            S.Tanikawa      Update          QC#10537
 *</pre>
 */
public class NWAL2400_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            int selectNum = getButtonSelectNumber();

            if (EVENT_NM_ORDER_CATEGORY.equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).dsOrdCatgDescTxt_A, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectNum).dsOrdCatgDescTxt_A);
            } else if (EVENT_NM_ORDER_REASON.equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).dsOrdTpDescTxt_A, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectNum).dsOrdTpDescTxt_A);
            } else if (EVENT_NM_COA_BR.equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).coaBrCd_A, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).coaBrDescTxt_A, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectNum).coaBrCd_A);
            }
        }
    }
}
