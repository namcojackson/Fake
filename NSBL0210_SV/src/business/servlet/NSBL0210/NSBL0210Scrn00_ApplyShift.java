/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import static business.servlet.NSBL0210.constant.NSBL0210Constant.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0210.NSBL0210CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/23   Hitachi         Y.Takeno        Update          QC#8565
 * 2016/06/06   Hitachi         Y.Takeno        Update          QC#5489
 *</pre>
 */
public class NSBL0210Scrn00_ApplyShift extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        NSBL0210CMsg bizMsg = new NSBL0210CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);

        int selRowIndex = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum_S, BigDecimal.valueOf(selRowIndex));

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        NSBL0210CMsg bizMsg = (NSBL0210CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 05/23/2016 [QC#8565, MOD]
        // START 06/06/2016 [QC#5489, MOD]
        scrnMsg.A.setCheckParam(new String[] {NSBL0210Bean.svcLineBizCd, NSBL0210Bean.svcPrcShiftNum }, 1);
        // END 06/06/2016 [QC#5489, MOD]
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
        // END   05/23/2016 [QC#8565, MOD]
    }
}
