/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext; // import

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0730Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // do nothing.
    }
}