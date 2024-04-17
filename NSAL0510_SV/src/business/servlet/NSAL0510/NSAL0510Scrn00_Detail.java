/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510;

import static business.servlet.NSAL0510.constant.NSAL0510Constant.NSAL0500_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0510Scrn00_Detail extends S21CommonHandler {

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

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_P, scrnMsg.A.no(getButtonSelectNumber()).dsContrDtlPk_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsSubContrPk_P, scrnMsg.A.no(getButtonSelectNumber()).dsSubContrPk_A.getValue());

        Object[] params = new Object[NSAL0500_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.dsContrDtlPk_P;
        params[i++] = scrnMsg.dsSubContrPk_P;

        setArgForSubScreen(params);
    }
}
