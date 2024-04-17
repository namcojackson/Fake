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
 * 2018/01/12   Hitachi         T.Tomita        Update          QC#18552
 *</pre>
 */
public class NSAL0300Scrn00_ExpandBase extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        if (NSAL0300Constant.IMG_OPEN_ARROW.equals(scrnMsg.B.no(rowNum).xxFilePathTxt_BB.getValue())) {
            BigDecimal dsContrDtlPk = scrnMsg.B.no(rowNum).dsContrDtlPk_B.getValue();
            NSAL0300CommonLogic.addCheckItemForBase(scrnMsg, dsContrDtlPk);
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
        if (NSAL0300Constant.IMG_CLOSE_ARROW.equals(scrnMsg.B.no(rowNum).xxFilePathTxt_BB.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).xxFilePathTxt_BB, NSAL0300Constant.IMG_OPEN_ARROW);
            // Mod Start 2018/01/12 QC#18552
            scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).baseBllgTmgCd_B);
            // Mod End 2018/01/12 QC#18552
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).xxFilePathTxt_BB, NSAL0300Constant.IMG_CLOSE_ARROW);
            // Mod Start 2018/01/12 QC#18552
            scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).baseBllgTmgCd_B);
            // Mod End 2018/01/12 QC#18552
        }
    }
}
