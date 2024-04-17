/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil.selectAll;
import static com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil.unSelectAll;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0720Scrn00_SelectDtlAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        boolean checkFlg = false;
        if (CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_AL.getValue())) {
            checkFlg = true;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (checkFlg) {
                selectAll(scrnMsg.A, "xxChkBox_A1", CHKBOX_ON_Y);
            } else {
                unSelectAll(scrnMsg.A, "xxChkBox_A1");
            }
        }
    }
}
