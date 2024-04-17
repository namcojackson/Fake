/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1000;

import java.io.Serializable;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1000.constant.NLCL1000Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 *</pre>
 */
public class NLCL1000Scrn00_ChoiceLoc extends S21CommonHandler implements NLCL1000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing to do.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing to do.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;

         int index = getButtonSelectNumber();
         // TODO start
//         String locCd = scrnMsg.A.no(index).locCd_RE.getValue();
//         String locNm = scrnMsg.A.no(index).locNm.getValue();
         // TODO end

         Serializable arg = getArgForSubScreen();
         if (arg instanceof Object[]) {
             Object[] params = (Object[]) arg;
             EZDBStringItem param1 = (EZDBStringItem) params[1];
             EZDBStringItem param4 = (EZDBStringItem) params[4];

             // TODO start
//             param1.setValue(locCd);
//             param4.setValue(locNm);
             // TODO end
         }
    }

}
