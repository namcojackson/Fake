/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1670;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 * 2016/05/16   Hitachi         O.Okuma         Update          S21_NA#7104
 *</pre>
 */
public class NWAL1670_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1670BMsg scrnMsg = (NWAL1670BMsg) bMsg;

        int index = scrnMsg.xxNum_PO.getValueInt();
        scrnMsg.setFocusItem(scrnMsg.C.no(index).ordTeamAttrbValTxt_C);

        if (!"CMN_Close".equals(getLastGuard()) && hasValue(scrnMsg.xxPopPrm_10)) {
            setValue(scrnMsg.C.no(index).ordTeamAttrbValTxt_C, scrnMsg.xxPopPrm_10);
            setValue(scrnMsg.C.no(index).dsAcctNm_C, scrnMsg.xxPopPrm_11);
        }
        // 2016/05/16 S21_NA#7104 Mod Start
        scrnMsg.setFocusItem(scrnMsg.C.no(index).ordTeamAttrbValTxt_C);
        // 2016/05/16 S21_NA#7104 Mod End
    }
}
