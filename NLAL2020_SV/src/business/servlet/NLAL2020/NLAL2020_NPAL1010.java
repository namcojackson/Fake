/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020_NPAL1010
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2020_NPAL1010 extends S21CommonHandler {

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

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        if (!NLAL2020Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NLAL2020Constant.EVENT_NM_OPEN_WIN_LOC_INFO.equals(scrnMsg.xxMntEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToRtlWhCd_H, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H, scrnMsg.P.no(7).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.shipToRtlWhCd_H);

            } else if (NLAL2020Constant.EVENT_NM_OPEN_WIN_RCV_LOC_INFO.equals(scrnMsg.xxMntEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmaSlsWhNm_H, scrnMsg.P.no(7).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H);

            } else if (NLAL2020Constant.EVENT_NM_OPEN_WIN_RCV_RTL_WH_INFO.equals(scrnMsg.xxMntEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).rtlWhCd_A1, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).rmaSlsWhNm_A1, scrnMsg.P.no(7).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).rtlWhCd_A1);
            }
        }
    }
}
