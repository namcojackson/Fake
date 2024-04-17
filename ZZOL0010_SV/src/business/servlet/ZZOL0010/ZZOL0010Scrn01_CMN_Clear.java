/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0010Scrn01_CMN_Clear extends S21CommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;

        ZZOL0010CommonLogic.initPullDown01(scrnMsg);
        ZZOL0010CommonLogic.initValue01(scrnMsg);
        if (!scrnMsg.ezBusinessID_01.isInputProtected()) {
            scrnMsg.ezBusinessID_01.clear();
        }
        
    }

}
