/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0310.NSAL0310CMsg;
import business.servlet.NSAL0310.common.NSAL0310CommonLogic;
import business.servlet.NSAL0310.constant.NSAL0310Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0310Scrn00_Collapse_Machines extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;

        int rowNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(rowNum));

        NSAL0310CMsg bizMsg = new NSAL0310CMsg();
        bizMsg.setBusinessID(NSAL0310Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0310Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CMsg bizMsg = (NSAL0310CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0310Constant.BIZ_ID);
        NSAL0310CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0310CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0310CommonLogic.setupScreenItems(scrnMsg, functionList);
        NSAL0310CommonLogic.alternateTableRowColor(scrnMsg);

        int rowNum = getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.A.no(rowNum).xxChkBox_AM);
    }
}
