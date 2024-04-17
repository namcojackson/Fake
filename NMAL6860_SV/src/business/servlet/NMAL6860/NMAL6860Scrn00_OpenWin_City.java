/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.LINK_OPENWIN_CTY_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6860.NMAL6860CMsg;
//import business.servlet.NMAL6860.constant.NMAL6860Constant;

import business.servlet.NMAL6860.common.NMAL6860CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/09/2016   CITS            S.Endo          Create          QC#10839
 *</pre>
 */
public class NMAL6860Scrn00_OpenWin_City extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_AD, LINK_OPENWIN_CTY_EVENT_NM);
        int idx = this.getButtonSelectNumber();
        Object[]params = NMAL6860CommonLogic.getAddressPopupParam(scrnMsg, getGlobalCompanyCode(),idx);
        setArgForSubScreen(params);


    }
}
