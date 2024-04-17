/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1430;

import static business.servlet.NPAL1430.constant.NPAL1430Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NPAL1430_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;
            for (int i = 0; i < scrnMsg.R.length(); i++) {
                if ("T_MDL_NM".equals(scrnMsg.R.no(i).xxComnScrQueryColNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm, scrnMsg.R.no(i).xxComnScrColValTxt);
                }
            }
            scrnMsg.setFocusItem(scrnMsg.t_MdlNm);
        }
    }
}
