/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6790;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL6790Scrn00_Select extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.B.no(i).xxChkBox_B1.setValue(ZYPConstant.FLG_ON_Y);

            if (scrnMsg.B.no(i).xxChkBox_B1.isInputProtected()) {
                scrnMsg.B.no(i).xxChkBox_B1.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }
}
