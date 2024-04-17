/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0420;

import static business.servlet.NSAL0420.constant.NSAL0420Constant.NSAL1180_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0420Scrn00_OpenWin_BranchHierarchy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0420BMsg scrnMsg = (NSAL0420BMsg) bMsg;
        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcRgPk_P, scrnMsg.A.no(index).svcRgPk_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd_P, scrnMsg.A.no(index).svcContrBrCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_P, scrnMsg.A.no(index).psnCd_A);

        Object[] params = new Object[NSAL1180_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.svcRgPk_P;
        params[i++] = scrnMsg.svcContrBrCd_P;
        params[i++] = scrnMsg.psnCd_P;

        setArgForSubScreen(params);
    }
}
