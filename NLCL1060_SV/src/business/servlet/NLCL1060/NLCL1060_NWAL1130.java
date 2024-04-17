/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1060.constant.NLCL1060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060_NWAL1130 extends S21CommonHandler {

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


        NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;


        if (!NLCL1060Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NLCL1060Constant.SCR_NM_VND.equals(scrnMsg.Q.no(0).xxPopPrm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.R.no(1).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.vndCd);
            }
        }

    }
}
