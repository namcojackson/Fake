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
 * 03/02/2017   CITS            K.Kameoka       Update          QC#17865
 * 09/28/2017   CITS            R.Shimamoto     Update          QC#18669
 *</pre>
 */
public class NLAL1100_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();

        if (!NLAL1100Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NLAL1100Constant.EVENT_NM_OPENWIN_CARR.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_H1, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_H1, scrnMsg.P.no(10).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.carrCd_H1);

            } else if (NLAL1100Constant.EVENT_NM_OPENWIN_SLS_REP.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepBrCd_H1, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.tocNm_H1, scrnMsg.P.no(10).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.slsRepBrCd_H1);

            } else if (NLAL1100Constant.EVENT_NM_OPENWIN_NTFY_GRP.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtrnTrkNtfyGrpCd_A1, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtrnTrkNtfyGrpDescTxt_A1, scrnMsg.P.no(10).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtrnTrkNtfyGrpCd_A1);

            // START QC#18669 Add.
            } else if (NLAL1100Constant.EVENT_NM_OPENWIN_NTFY_GRP_APPLY.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnTrkNtfyGrpCd_G, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnTrkNtfyGrpDescTxt_G, scrnMsg.P.no(10).xxPopPrm);

                scrnMsg.setFocusItem(scrnMsg.rtrnTrkNtfyGrpCd_G);
            // END QC#18669 Add.
            } else {

                scrnMsg.setFocusItem(scrnMsg.rmaNum_H1);
            }
        } else {
            // QC#17865 start
            if (NLAL1100Constant.EVENT_NM_OPENWIN_NTFY_GRP.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtrnTrkNtfyGrpCd_A1);
            }
        }
        // QC#17865 end

    }
}
