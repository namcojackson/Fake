/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Hitachi         T.Kanasaka      Create          QC#4806
 *</pre>
 */
public class NSAL0300Scrn00_ExpandBillingCounter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        if (NSAL0300Constant.IMG_OPEN_ARROW.equals(scrnMsg.B.no(rowNum).xxFilePathTxt_BC.getValue())) {
            BigDecimal dsContrBllgMtrPk = scrnMsg.B.no(rowNum).dsContrBllgMtrPk_B.getValue();
            NSAL0300CommonLogic.addCheckItemForBillingCounter(scrnMsg, dsContrBllgMtrPk);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        int rowNum = getButtonSelectNumber();
        BigDecimal dsContrBllgMtrPk = scrnMsg.B.no(rowNum).dsContrBllgMtrPk_B.getValue();
        for (int i = rowNum; i < scrnMsg.B.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue(), dsContrBllgMtrPk)) {
                if (NSAL0300Constant.IMG_CLOSE_ARROW.equals(scrnMsg.B.no(i).xxFilePathTxt_BC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BC, NSAL0300Constant.IMG_OPEN_ARROW);
                    scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).bllgFreeCopyCnt_B);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BC, NSAL0300Constant.IMG_CLOSE_ARROW);
                    scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).mtrLbDescTxt_B);
                }
            } else {
                return;
            }
        }
    }
}
