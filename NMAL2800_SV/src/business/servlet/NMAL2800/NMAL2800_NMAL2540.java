/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800_NMAL2540
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800_NMAL2540 extends S21CommonHandler {

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

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        int index = getButtonSelectNumber();
        NMAL2800_ABMsg aBMsg = scrnMsg.A.no(index);

        if (!ZYPCommonFunc.hasValue(aBMsg.prosRvwStsCd_AB)) {
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocFirstLineAddr_A1, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocScdLineAddr_A1, scrnMsg.P.no(1).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocThirdLineAddr_A1, scrnMsg.P.no(2).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocFrthLineAddr_A1, scrnMsg.P.no(3).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocCtyAddr_A1, scrnMsg.P.no(4).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocStCd_A1, scrnMsg.P.no(5).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.cntyNm_A1, scrnMsg.P.no(7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocPostCd_A1, scrnMsg.P.no(8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(aBMsg.aftCtryCd_A1, scrnMsg.P.no(9).xxPopPrm);

            scrnMsg.setFocusItem(aBMsg.aftLocFirstLineAddr_A1);
        }
    }
}
