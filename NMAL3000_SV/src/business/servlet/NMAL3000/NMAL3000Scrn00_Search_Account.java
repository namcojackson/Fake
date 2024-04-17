/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import java.math.BigDecimal;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import business.blap.NMAL3000.NMAL3000CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL3000Scrn00_Search_Account
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL3000Scrn00_Search_Account extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctCustNum_A);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg = new NMAL3000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(index));
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg  = (NMAL3000CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
