/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0730.common.NSAL0730CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 *</pre>
 */
public class NSAL0730Scrn00_SelectAllSerial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

        if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_H2.getValue())) {
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            ZYPTableUtil.selectAll(scrnMsg.A, "xxChkBox_D2", ZYPConstant.CHKBOX_ON_Y);
            NSAL0730CommonLogic.selectAllSerial(scrnMsg);
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        } else {
            ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_D2");
        }
    }
}
