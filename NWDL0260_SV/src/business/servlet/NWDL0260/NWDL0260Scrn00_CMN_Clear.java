/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260;

import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.setGuiAttr;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWDL0260Scrn00_CMN_Clear extends S21CommonHandler implements NWDL0260Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        // set GUI attributes.
        setGuiAttr(this, scrnMsg, ScrnId.Scrn00);
        
        // set focus.
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }

}