/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1370;

import static business.servlet.NPAL1370.constant.NPAL1370Constant.EVENT_CMN_CLOSE;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.EVENT_OPEN_WIN_CPY_FRM_WH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.EVENT_OPEN_WIN_CPY_FRM_SWH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.EVENT_OPEN_WIN_CPY_TO_WH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.EVENT_OPEN_WIN_CPY_TO_SWH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : Return Action from NPAL1010(Location Info popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;

        if (!EVENT_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_OPEN_WIN_CPY_FRM_WH.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_FR, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_FR, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_FR, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_FR, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_FR);

            } else if (EVENT_OPEN_WIN_CPY_FRM_SWH.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_FR, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_FR, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_FR, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_FR, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_FR);

            } else if (EVENT_OPEN_WIN_CPY_TO_WH.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_TO, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_TO, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_TO, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_TO, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_TO);
            } else if (EVENT_OPEN_WIN_CPY_TO_SWH.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_TO, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_TO, scrnMsg.P.no(7).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_TO, scrnMsg.P.no(8).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_TO, scrnMsg.P.no(9).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_TO);
            }

            scrnMsg.xxPopPrm_EV.clear();
        }
    }
}
