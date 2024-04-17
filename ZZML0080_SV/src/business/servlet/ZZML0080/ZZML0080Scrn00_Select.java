/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0080;


import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZML0080.constant.ZZML0080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0080Scrn00_Select extends S21CommonHandler implements ZZML0080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0080BMsg scrnMsg = (ZZML0080BMsg) bMsg;

        int selectNo = getButtonSelectNumber();

        Serializable arg = getArgForSubScreen();

        if (arg != null && arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem mlGrpId = (EZDBStringItem) params[2];
            mlGrpId.setValue(scrnMsg.A.no(selectNo).mlGrpId_A.getValue());
        }
    }
}
