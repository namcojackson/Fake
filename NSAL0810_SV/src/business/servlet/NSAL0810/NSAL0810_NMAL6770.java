/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0810.NSAL0810CMsg;
//import business.servlet.NSAL0810.constant.NSAL0810Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0810_NMAL6770 extends S21CommonHandler {

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
        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        getArgForSubScreen();

        if (!"CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            String xxPopPrm00 = scrnMsg.xxPopPrm_00.getValue();
            if (xxPopPrm00 != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).ctacPsnPk_A, (new BigDecimal(xxPopPrm00)));
            }
            String ctacPsnNm = null;
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).ctacPsnFirstNm_A, scrnMsg.xxPopPrm_06);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).ctacPsnLastNm_A, scrnMsg.xxPopPrm_07);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_06) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_07)) {
                ctacPsnNm = scrnMsg.xxPopPrm_06.getValue() + " " + scrnMsg.xxPopPrm_07.getValue();
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).ctacPsnNm_A, ctacPsnNm);
        }
    }
}
