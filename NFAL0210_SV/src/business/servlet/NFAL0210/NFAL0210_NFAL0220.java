/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NFAL0210.NFAL0210CMsg;
// import business.servlet.NFAL0210.constant.NFAL0210Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/14   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFAL0210_NFAL0220 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFAL0210BMsg scrnMsg = (NFAL0210BMsg) bMsg;
        scrnMsg.setFocusItem(scrnMsg.manJrnlNm);
    }
}