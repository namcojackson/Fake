/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/10/2010   Fujitsu         K.Tajima        Create          N/A
 * 08/05/2010   Fujitsu         R.Nakamura      Update          QC#9078
 *</pre>
 */
package business.servlet.NWXL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWXL0010.constant.NWXL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWXL0010Scrn00_CMN_Return extends S21CommonHandler implements NWXL0010Constant {

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
        // nothing to do.
    }

}
