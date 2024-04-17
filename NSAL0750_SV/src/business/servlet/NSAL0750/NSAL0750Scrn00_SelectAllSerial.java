/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0750;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 *  2015/11/26   Hitachi        J.Kim           Create          N/A
 *</pre>
 */
public class NSAL0750Scrn00_SelectAllSerial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0750BMsg scrnMsg = (NSAL0750BMsg) bMsg;

        if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_H2.getValue())) {
            ZYPTableUtil.selectAll(scrnMsg.A, "xxChkBox_D2", ZYPConstant.CHKBOX_ON_Y);
        } else {
            ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_D2");
        }
    }
}
