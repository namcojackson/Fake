/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Kanasaka      Create          QC3273
 * 2016/08/05   Hitachi         T.Kanasaka      Update          QC#4806
 *</pre>
 */
public class NSAL0300Scrn00_ChangeFreeCopyCnt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(rowNum).bllgFreeCopyCnt_B)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(rowNum).bllgFreeCopyCnt_B);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        int rowNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum_B, BigDecimal.valueOf(rowNum));

        // sync value
        BigDecimal dsContrBllgMtrPk = scrnMsg.B.no(rowNum).dsContrBllgMtrPk_B.getValue();
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue(), dsContrBllgMtrPk)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).bllgFreeCopyCnt_B, scrnMsg.B.no(rowNum).bllgFreeCopyCnt_B);
            }
        }

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

        scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxRowNum_B.getValue().intValue()).bllgMinCnt_B);
    }
}
