/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

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
public class NLBL2020_NPAL1010 extends S21CommonHandler {

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

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_RW, scrnMsg.P.no(6).xxComnScrColValTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, NLBL2020CommonLogic.chkMultipleLocNm(scrnMsg.P.no(7).xxComnScrColValTxt.getValue()));

        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(8).xxComnScrColValTxt)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_SW, scrnMsg.P.no(8).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, NLBL2020CommonLogic.chkMultipleLocNm(scrnMsg.P.no(9).xxComnScrColValTxt.getValue()));
        }

        // Set Focus
        if (NLBL2020Constant.EVENT_NM_OPENWIN_LOCINFO_RTLWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.xxRtrnInvtyLocSrchTxt_RW);

        } else if (NLBL2020Constant.EVENT_NM_OPENWIN_LOCINFO_RTLSWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.xxRtrnInvtyLocSrchTxt_SW);
        }
    }
}
