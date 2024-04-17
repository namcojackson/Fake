/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0420;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
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
public class NSAL0420Scrn00_BranchLink extends S21CommonHandler {

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
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            int i = 0;
            EZDBStringItem param0 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param0, scrnMsg.A.no(index).svcRgNm_A);

            EZDBStringItem param1 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param1, scrnMsg.A.no(index).lineBizTpDescTxt_A);

            EZDBStringItem param2 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param2, scrnMsg.A.no(index).svcContrBrCd_A);

            EZDBStringItem param3 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param3, scrnMsg.A.no(index).svcContrBrDescTxt_A);

            EZDBStringItem param4 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param4, scrnMsg.A.no(index).xxGenlFldAreaTxt_A);

            EZDBStringItem param5 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param5, scrnMsg.A.no(index).orgFuncRoleTpNm_A);

            EZDBBigDecimalItem param6 = (EZDBBigDecimalItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param6, scrnMsg.A.no(index).svcRgPk_A);

            EZDBStringItem param7 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param7, scrnMsg.A.no(index).psnCd_A);

            EZDBStringItem param8 = (EZDBStringItem) params[i++];
            ZYPEZDItemValueSetter.setValue(param8, scrnMsg.A.no(index).lineBizTpCd_A);
        }
        return;
    }
}
