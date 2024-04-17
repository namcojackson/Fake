/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1250;

import static business.servlet.NSAL1250.constant.NSAL1250Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1250.NSAL1250CMsg;
import business.servlet.NSAL1250.common.NSAL1250CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL1250_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1250BMsg scrnMsg = (NSAL1250BMsg) bMsg;

        BigDecimal svcContrBllgPk = null;
        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] params = (Object[]) ser;
            if (params != null) {
                if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                    svcContrBllgPk = ((EZDBBigDecimalItem) params[0]).getValue();
                }
            }
        }
        setValue(scrnMsg.svcContrBllgPk, svcContrBllgPk);

        NSAL1250CMsg bizMsg = new NSAL1250CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1250BMsg scrnMsg = (NSAL1250BMsg) bMsg;
        NSAL1250CMsg bizMsg  = (NSAL1250CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1250CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
    }
}
