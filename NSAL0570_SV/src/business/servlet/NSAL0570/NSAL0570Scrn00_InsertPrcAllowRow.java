/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import static business.servlet.NSAL0570.common.NSAL0570CommonLogic.initialControlScreen;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0570.NSAL0570CMsg;
//import business.servlet.NSAL0570.constant.NSAL0570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0570Scrn00_InsertPrcAllowRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        NSAL0570CMsg bizMsg = new NSAL0570CMsg();
        setValue(scrnMsg.rowSqNum_A, BigDecimal.valueOf(getButtonSelectNumber()));
        bizMsg.setBusinessID("NSAL0570");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        NSAL0570CMsg bizMsg  = (NSAL0570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, new BigDecimal(scrnMsg.A.getValidCount() - 1));

    }
}
