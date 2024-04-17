/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL4500;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.LINK_OPENWIN_CNTY_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL4500.common.NMAL4500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Endo          Create          QC#10840
 *</pre>
 */
public class NMAL4500Scrn00_OpenWin_Cnty extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, LINK_OPENWIN_CNTY_EVENT_NM);
        Object[] params = NMAL4500CommonLogic.getAddressPopupParam(scrnMsg, getGlobalCompanyCode());
        setArgForSubScreen(params);

    }
}
