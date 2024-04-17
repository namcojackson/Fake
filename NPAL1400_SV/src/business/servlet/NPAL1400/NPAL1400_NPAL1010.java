/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1400;

import static business.servlet.NPAL1400.constant.NPAL1400Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.EVENT_OPEN_WIN_SUB_WAREHOUSE;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.EVENT_OPEN_WIN_WAREHOUSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * Function Name : OpenWin_Warehouse
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NPAL1400_NPAL1010 extends S21CommonHandler {

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

        NPAL1400BMsg scrnMsg = (NPAL1400BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_OPEN_WIN_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.xxPopPrm_P8);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.xxPopPrm_P9);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

            } else if (EVENT_OPEN_WIN_SUB_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.xxPopPrm_P8);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.xxPopPrm_P9);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.rtlSwhCd);

            }
            scrnMsg.xxPopPrm_EV.clear();
        }
    }
}
