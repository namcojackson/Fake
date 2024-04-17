/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_COMPLETION_SWH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_SUPPLY_SWH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_WH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_6;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_7;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_8;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Return Location Info Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1360_NPAL1010 extends S21CommonHandler {

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

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_OPEN_WIN_WH.equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(INDEX_6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.P.no(INDEX_7).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

            } else if (EVENT_OPEN_WIN_COMPLETION_SWH.equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(INDEX_6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.P.no(INDEX_7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpltRtlSwhCd, scrnMsg.P.no(INDEX_8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.P.no(INDEX_9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.cpltRtlSwhCd);

            } else if (EVENT_OPEN_WIN_SUPPLY_SWH.equals(scrnMsg.eventNm.getValue())) {
                int eventRowIndex = getButtonSelectNumber();

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyRtlSwhCd_A1, scrnMsg.P.no(INDEX_8).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).splyRtlSwhCd_A1);
            }
        }

    }
}
