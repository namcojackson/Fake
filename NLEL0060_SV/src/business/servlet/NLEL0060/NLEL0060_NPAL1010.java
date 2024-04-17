/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_RTRN_WH_CD;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_WH_CODE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060_NPAL1010
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/15   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NLEL0060_NPAL1010 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        String rtnWhCd = scrnMsg.P.no(6).xxPopPrm.getValue();
        String rtlWhNm = scrnMsg.P.no(7).xxPopPrm.getValue();

        if (OPENWIN_WH_CODE.equals(scrnMsg.P.no(12).xxPopPrm.getValue())) {
            if (ZYPCommonFunc.hasValue(rtnWhCd)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtnWhCd_H1, rtnWhCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, rtlWhNm);
            } else {
                scrnMsg.rtnWhCd_H1.clear();
                scrnMsg.rtlWhNm_H1.clear();
            }
            scrnMsg.setFocusItem(scrnMsg.rtnWhCd_H1);
        } else if (OPENWIN_RTRN_WH_CD.equals(scrnMsg.P.no(12).xxPopPrm.getValue())) {
            int idx = getButtonSelectNumber();
            if (ZYPCommonFunc.hasValue(rtnWhCd)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).xxScrItem10Txt_B1, rtnWhCd);
            } else {
                scrnMsg.B.no(idx).xxScrItem10Txt_B1.clear();
            }
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).xxScrItem10Txt_B1);
        }
    }
}
