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
public class NLAL1100_NWAL1130 extends S21CommonHandler {

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
        // QC#17865 start
        int index = getButtonSelectNumber();
        // QC#17865 end

        if (!NLAL1100Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            // QC#17865 start
            // int index = getButtonSelectNumber();
            // QC#17865 end
        	// START QC#18669 Mod.
        	if (NLAL1100Constant.EVENT_NM_OPENWIN_CARR_APPLY.equals(scrnMsg.xxPopPrm_EV.getValue())) {
        		
        		ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_G, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_G, scrnMsg.P.no(1).xxComnScrColValTxt);

                scrnMsg.setFocusItem(scrnMsg.carrCd_G);

        	} else {
        		ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).carrCd_A1, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).locNm_A1, scrnMsg.P.no(1).xxComnScrColValTxt);

                scrnMsg.setFocusItem(scrnMsg.A.no(index).carrCd_A1);
        	}
        	// END QC#18669 Mod.

        } else {
        	// START QC#18669 Mod.
        	if (NLAL1100Constant.EVENT_NM_OPENWIN_CARR_APPLY.equals(scrnMsg.xxPopPrm_EV.getValue())) {
        		scrnMsg.setFocusItem(scrnMsg.carrCd_G);
        	} else {
        		// QC#17865 start
                scrnMsg.setFocusItem(scrnMsg.A.no(index).carrCd_A1);
        	}
            // END QC#18669 Mod.
        }
        // QC#17865 end
    }
}
