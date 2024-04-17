/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/15   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
package business.servlet.NWAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL0300.constant.NWAL0300Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NWAL0300Scrn00_CMN_Clear
 */
public class NWAL0300Scrn00_CMN_Clear extends S21CommonHandler implements NWAL0300Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0300BMsg scrnMsg = (NWAL0300BMsg) bMsg;
        scrnMsg.rvwMemoTxt.clear();
    }

}
