/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0320;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0320.NSAL0320CMsg;
import business.servlet.NSAL0320.common.NSAL0320CommonLogic;
import business.servlet.NSAL0320.constant.NSAL0320Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0320Scrn00_OnChange_BillingCounter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        NSAL0320CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;

        int rowNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(rowNum));

        NSAL0320CMsg bizMsg = new NSAL0320CMsg();
        bizMsg.setBusinessID(NSAL0320Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0320Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        NSAL0320CMsg bizMsg = (NSAL0320CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0320Constant.BIZ_ID);
        NSAL0320CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0320CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0320CommonLogic.setupScreenItems(scrnMsg, functionList);

        int rowNum = getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.A.no(rowNum).bllgMtrLbCd);
    }
}
