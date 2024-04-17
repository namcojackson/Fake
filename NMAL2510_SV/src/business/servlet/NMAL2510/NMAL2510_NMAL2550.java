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
public class NMAL2510_NMAL2550 extends S21CommonHandler {

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

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).coaCmpyCd_C2, scrnMsg.xxPopPrm_1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).coaBrCd_C2, scrnMsg.xxPopPrm_3);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_4)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).coaCcCd_C2, scrnMsg.xxPopPrm_4);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_9)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).coaExtnCd_C2, scrnMsg.xxPopPrm_9);
        }
    }
}
