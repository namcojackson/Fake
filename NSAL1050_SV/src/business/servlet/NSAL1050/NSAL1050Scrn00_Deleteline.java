/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import static business.servlet.NSAL1050.constant.NSAL1050Constant.NSAM0019E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   CUSA            T.Mizuki         Create          N/A
 *</pre>
 */
public class NSAL1050Scrn00_Deleteline extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;

        boolean chkBox = true;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                chkBox = false;
            }
        }
        if (chkBox) {
            scrnMsg.setMessageInfo(NSAM0019E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        ZYPTableUtil.deleteRows(scrnMsg.A, checkList);
    }
}
