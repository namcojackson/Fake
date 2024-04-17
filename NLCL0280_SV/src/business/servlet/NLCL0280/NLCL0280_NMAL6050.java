/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import static business.servlet.NLCL0280.constant.NLCL0280Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.TBL_NM_COA_PROD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.TBL_NM_VND;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLCL0280_NMAL6050 extends S21CommonHandler {

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

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (TBL_NM_COA_PROD.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_H1, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdDescTxt_H1, scrnMsg.P.no(10).xxPopPrm);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.coaProdCd_H1);
            } else if (TBL_NM_VND.equals(scrnMsg.P.no(0).xxPopPrm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd_H1, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, scrnMsg.P.no(10).xxPopPrm);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.vndCd_H1);
            }
        }
    }
}
