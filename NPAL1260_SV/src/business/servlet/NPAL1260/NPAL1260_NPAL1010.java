/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Return Action from NPAL1010(Location Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1260_NPAL1010 extends S21CommonHandler {

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
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if ("OpenWin_SrcWhSwh".equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_H1, scrnMsg.xxPopPrm_P8);
                scrnMsg.setFocusItem(scrnMsg.prntVndNm_H1);
            } else if ("OpenWin_DestWhSwh".equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlWhCd_H1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd_H1, scrnMsg.xxPopPrm_P8);
                scrnMsg.setFocusItem(scrnMsg.techNm_H1);
            }
        }
    }
}
