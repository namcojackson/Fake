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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Hitachi         T.Kanasaka      Create          QC#4806
 * 2018/01/10   Hitachi         T.Tomita        Update          QC#18552
 *</pre>
 */
public class NSAL0300Scrn00_ExpandOverage extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        if (NSAL0300Constant.IMG_OPEN_ARROW.equals(scrnMsg.B.no(rowNum).xxFilePathTxt_BM.getValue())) {
            BigDecimal dsContrDtlPk = scrnMsg.B.no(rowNum).dsContrDtlPk_B.getValue();
            NSAL0300CommonLogic.addCheckItemForOverage(scrnMsg, dsContrDtlPk);
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
        if (NSAL0300Constant.IMG_CLOSE_ARROW.equals(scrnMsg.B.no(rowNum).xxFilePathTxt_BM.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).xxFilePathTxt_BM, NSAL0300Constant.IMG_OPEN_ARROW);
            scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).mtrDplyPerEndDay_B);
            // Add Start 2018/01/10 QC#18552
            BigDecimal dsContrDtlPk = scrnMsg.B.no(rowNum).dsContrDtlPk_B.getValue();
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (scrnMsg.B.no(i).dsContrDtlPk_B.getValue().compareTo(dsContrDtlPk) != 0) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsContrBllgMtrPk_B)) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BC, NSAL0300Constant.IMG_CLOSE_ARROW);
            }
            // Add End 2018/01/10 QC#18552
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).xxFilePathTxt_BM, NSAL0300Constant.IMG_CLOSE_ARROW);
            // Mod Start 2018/01/10 QC#18552
            scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).mtrDplyPerEndDay_B);
            // Mod End 2018/01/10 QC#18552
        }
    }
}
