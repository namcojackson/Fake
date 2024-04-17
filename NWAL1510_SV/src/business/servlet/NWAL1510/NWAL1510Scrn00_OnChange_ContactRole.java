/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1510.common.NWAL1510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510Scrn00_OnChange_ContactRole
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/08   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
public class NWAL1510Scrn00_OnChange_ContactRole extends S21CommonHandler {

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

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        int row = getButtonSelectNumber();

        String ctacPsnTpCd = scrnMsg.C.no(row).ctacPsnTpCd_C0.getValue();
        if (!ZYPCommonFunc.hasValue(ctacPsnTpCd)) {
            return;
        }

        NWAL1510CommonLogic.setTabProtect(this, scrnMsg);
        // set default
        if (!scrnMsg.C.no(row).ctacCustRefTpCd_C0.isInputProtected()) {
            String ctacCustRefCd = null;
            for (int i = 0; i < scrnMsg.ctacPsnTpCd_L0.length(); i++) {
                if (ctacPsnTpCd.equals(scrnMsg.ctacPsnTpCd_L0.no(i).getValue())) {
                    ctacCustRefCd = scrnMsg.L.no(i).ctacCustRefTpCd_L.getValue();
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(row).ctacCustRefTpCd_C0, ctacCustRefCd);
                    break;
                }
            }
        }

        scrnMsg.setFocusItem(scrnMsg.C.no(row).ctacPsnTpCd_C0);

    }
}
