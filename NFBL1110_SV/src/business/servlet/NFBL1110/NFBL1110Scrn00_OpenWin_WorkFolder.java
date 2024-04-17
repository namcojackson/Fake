/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import static business.servlet.NFBL1110.constant.NFBL1110Constant.CONST_NM_NFBL1110_THEREFORE_CATG_LIST;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFBL1110Scrn00_OpenWin_WorkFolder
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/21   Fujitsu         W.Honda         Create          Unit Test#201
 *</pre>
 */
public class NFBL1110Scrn00_OpenWin_WorkFolder extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.Z);

        // Set Value
        String thereforeCatg = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NFBL1110_THEREFORE_CATG_LIST, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(0).xxComnScrColValTxt, thereforeCatg);

        // Set Params
        Object[] params = new Object[1];
        params[0] = scrnMsg.Z.no(0).xxComnScrColValTxt;

        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
