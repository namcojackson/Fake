/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFBL2040Scrn00_OpenWin_WorkingFolder
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/26   Fujitsu         W.Honda         Create          Unit Test#201
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_WorkingFolder extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.Z);

        // Set Value
        String thereforeCatg = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NFBL2040_THEREFORE_CATG_LIST, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(0).xxComnScrColValTxt, thereforeCatg);

        // Set Params
        Object[] params = new Object[1];
        params[0] = scrnMsg.Z.no(0).xxComnScrColValTxt;

        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
