/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBL0210Scrn00_OnChangeLOB extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;

        int selIndex = this.getButtonSelectNumber();
        scrnMsg.A.no(selIndex).svcPrcShiftNum.clear();
        scrnMsg.A.no(selIndex).svcPrcShiftDescTxt.clear();
    }
}
