/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/04/25   Fujitsu         S.Takami        Create          QC#50078
 *</pre>
 */
public class NFCL3000Scrn00_Select_SvcInvChrgTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL3000_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.serNum_A1.clearErrorInfo();
            lineMsg.dsContrNum_A1.clearErrorInfo();
            lineMsg.mdlNm_A1.clearErrorInfo();
            lineMsg.bllgPerFromDt_A1.clearErrorInfo();
            lineMsg.bllgPerThruDt_A1.clearErrorInfo();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    }
}
