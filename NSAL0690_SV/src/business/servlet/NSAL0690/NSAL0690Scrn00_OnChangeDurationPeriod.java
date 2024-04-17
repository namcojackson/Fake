/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0690;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0690.NSAL0690CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0690Scrn00_OnChangeDurationPeriod extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        int lineNumber = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(lineNumber).xxNum_A1)
                || !ZYPCommonFunc.hasValue(scrnMsg.A.no(lineNumber).bllgCycleUomCd_A3)) {
            return null;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.rowSqNum_H1, BigDecimal.valueOf(lineNumber));

        NSAL0690CMsg bizMsg = new NSAL0690CMsg();
        bizMsg.setBusinessID("NSAL0690");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        NSAL0690CMsg bizMsg  = (NSAL0690CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
    }
}
