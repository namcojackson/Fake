/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Hitachi         T.Kanasaka      Create          N/A
 *</pre>
 */
public class NSAL0300Scrn00_Search_BillTo_Meter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.B.no(idx).bllgMtrBillToCustCd_B);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(idx));

        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        int idx = getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).bllgMtrBillToCustCd_B);
    }
}
