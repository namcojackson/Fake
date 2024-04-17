/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0743E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_CUSTOMER;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1770Scrn00_OpenWin_CheckContract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            scrnMsg.sellToCustCd.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.sellToCustCd.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
            scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
            scrnMsg.putErrorScreen();
        }

        boolean isExistLine = false;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).mdseCd_B)) {
                isExistLine = true;
            }
        }

        if (!isExistLine) {
            scrnMsg.setMessageInfo(NWAM0743E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        Object[] params = NWAL1770CommonLogic.getParamNWAL1800(scrnMsg);
        setArgForSubScreen(params);
    }
}
