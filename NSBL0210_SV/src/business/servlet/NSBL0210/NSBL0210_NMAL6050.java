/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import java.math.BigDecimal;

import static business.servlet.NSBL0210.constant.NSBL0210Constant.*;

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
 *</pre>
 */
public class NSBL0210_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;

        if (!BTN_NM_OPEN_WIN_MDL_GRP_DTL.equals(scrnMsg.xxScrEventNm.getValue()) || NMAL6050_EVENT_NM_CLOSE.equals(getLastGuard())) {
            return null;
        }

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

        if (!BTN_NM_OPEN_WIN_MDL_GRP_DTL.equals(scrnMsg.xxScrEventNm.getValue()) || NMAL6050_EVENT_NM_CLOSE.equals(getLastGuard())) {
            return;
        }

        NSBL0210CMsg bizMsg = (NSBL0210CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
