/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE_DETAIL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SOURCE_WAREHOUSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SUB_WAREHOUSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_WAREHOUSE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : NPAL1010 Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1070_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_OPEN_WIN_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_W1, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_S1, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

            } else if (EVENT_OPEN_WIN_SUB_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_W1, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_S1, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtlSwhCd);

            } else if (EVENT_OPEN_WIN_SOURCE_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.srcRtlWhCd, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_W2, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.srcRtlSwhCd, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_S2, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.srcRtlWhCd);

            } else if (EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.srcRtlWhCd, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_W2, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.srcRtlSwhCd, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_S2, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.srcRtlSwhCd);

            } else if (EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE_DETAIL.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                int eventRowIndex = getButtonSelectNumber();

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlWhNm_A2, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).srcRtlSwhCd_A1, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlSwhNm_A2, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1);
            }
            scrnMsg.xxPopPrm_EV.clear();
        }

    }
}
