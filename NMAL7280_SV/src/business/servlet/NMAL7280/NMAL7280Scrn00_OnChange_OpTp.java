/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7280Scrn00_OnChange_OpTp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7280Scrn00_OnChange_OpTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        int index = getButtonSelectNumber();
        String selectValue = scrnMsg.A.no(index).prcRuleOpTpCd_A1.getValue();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A2, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A3, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A4, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A5, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A6, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A7, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A8, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_A9, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_AA, selectValue);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleOpTpCd_AB, selectValue);
    }
}
