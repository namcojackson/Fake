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

// import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0010_ZZSL1110 extends S21CommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    }

}
