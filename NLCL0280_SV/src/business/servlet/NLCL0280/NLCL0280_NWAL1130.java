/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;
import business.servlet.NLCL0280.constant.NLCL0280Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLCL0280_NWAL1130 Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // Screen Protect Ctrl
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);

        if (!NLCL0280Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NLCL0280Constant.SCRN_NM_SHIP_FROM.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.shipFromLocCustCd_H1, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.R.no(1).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.shipFromLocCustCd_H1);

            } else if (NLCL0280Constant.SCRN_NM_SHIP_TO.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocCustCd_H1, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H2, scrnMsg.R.no(1).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.shipToLocCustCd_H1);

            } else if (NLCL0280Constant.SCR_NM_VND.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd_H1, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, scrnMsg.R.no(2).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.vndCd_H1);
            }
        }
    }
}
