/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_CUSTOMER;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1840Scrn00_OpenWin_PriceList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            scrnMsg.sellToCustCd.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.sellToCustCd.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
            scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        Object[] params = NWAL1840CommonLogic.getParamNWAL1760(scrnMsg);
        setArgForSubScreen(params);

    }
}
