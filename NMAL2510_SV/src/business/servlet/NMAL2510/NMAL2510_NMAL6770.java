/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2510_NMAL6770 extends S21CommonHandler {

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

        StringBuilder fullName = new StringBuilder();

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacTpCd_D1, scrnMsg.xxPopPrm_1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_6)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnFirstNm_D1, scrnMsg.xxPopPrm_6);
            fullName.append(scrnMsg.ctacPsnFirstNm_D1.getValue());
            fullName.append(NMAL2510Constant.SPACE);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_7)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnLastNm_D1, scrnMsg.xxPopPrm_7);
            fullName.append(scrnMsg.ctacPsnLastNm_D1.getValue());
        }

        // Supplier Contact Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_D1, fullName.toString());
    }
}
