/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#5771
 *</pre>
 */
public class NLBL3200_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_RW, scrnMsg.P.no(6).xxComnScrColValTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, NLBL3200CommonLogic.chkMultipleLocNm(scrnMsg.P.no(7).xxComnScrColValTxt.getValue()));

        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(8).xxComnScrColValTxt)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_SW, scrnMsg.P.no(8).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, NLBL3200CommonLogic.chkMultipleLocNm(scrnMsg.P.no(9).xxComnScrColValTxt.getValue()));
        }

        // Set Focus
        if (NLBL3200Constant.EVENT_NM_OPENWIN_LOCINFO_RTLWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.xxRtrnInvtyLocSrchTxt_RW);

        } else if (NLBL3200Constant.EVENT_NM_OPENWIN_LOCINFO_RTLSWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.xxRtrnInvtyLocSrchTxt_SW);
        }
    }
}
