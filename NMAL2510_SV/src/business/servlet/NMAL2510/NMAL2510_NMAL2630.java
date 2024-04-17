/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 *</pre>
 */
public class NMAL2510_NMAL2630 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        int index = getButtonSelectNumber();

        // Territory Code
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).orgCd_B2, scrnMsg.xxPopPrm_0);
        }

        // Territory Name
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).orgNm_B2, scrnMsg.xxPopPrm_1);
        }

    }
}
