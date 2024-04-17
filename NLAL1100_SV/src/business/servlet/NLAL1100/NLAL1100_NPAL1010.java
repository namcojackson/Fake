/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CSA             M.Ito           Create          N/A
 *</pre>
 */
public class NLAL1100_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        if (!NLAL1100Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.P.no(6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.P.no(7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_H1, scrnMsg.P.no(8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, scrnMsg.P.no(9).xxPopPrm);

            if (NLAL1100Constant.EVENT_NM_OPENWIN_LOCINFO_FOR_WH.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);

            } else if (NLAL1100Constant.EVENT_NM_OPENWIN_LOCINFO_FOR_SWH.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_H1);
            }
        }
    }
}
