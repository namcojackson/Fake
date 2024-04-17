/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 *</pre>
 */
public class NLBL3070_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;

        if (NLBL3070Constant.EVENT_NM_OPENWIN_CARR_APLY.equals(scrnMsg.xxMntEventNm.getValue())) {

            if (!NLBL3070Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

                if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_AP, scrnMsg.P.no(0).xxComnScrColValTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_AP, scrnMsg.P.no(1).xxComnScrColValTxt);
                    scrnMsg.setFocusItem(scrnMsg.carrNm_AP);

                } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_BP, scrnMsg.P.no(0).xxComnScrColValTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_BP, scrnMsg.P.no(1).xxComnScrColValTxt);
                    scrnMsg.setFocusItem(scrnMsg.carrNm_BP);
                }
            }

            scrnMsg.setFocusItem(scrnMsg.carrNm_AP);

        } else if (NLBL3070Constant.EVENT_NM_OPENWIN_CARR_LINE.equals(scrnMsg.xxMntEventNm.getValue())) {

            if (!NLBL3070Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

                int index = getButtonSelectNumber();

                if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).carrCd_A1, scrnMsg.P.no(0).xxComnScrColValTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).carrNm_A1, scrnMsg.P.no(1).xxComnScrColValTxt);
                    scrnMsg.setFocusItem(scrnMsg.A.no(index).carrNm_A1);

                } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).carrCd_B1, scrnMsg.P.no(0).xxComnScrColValTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).carrNm_B1, scrnMsg.P.no(1).xxComnScrColValTxt);
                    scrnMsg.setFocusItem(scrnMsg.B.no(index).carrNm_B1);
                }
            }
        }
    }
}
