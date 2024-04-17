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
 * 2018/01/10   Hitachi         T.Tomita        Update          QC#18552
 *</pre>
 */
public class NSAL0300Scrn00_ExpandMachine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        if (NSAL0300Constant.IMG_OPEN_MACHINE_RED.equals(scrnMsg.A.no(rowNum).xxFilePathTxt_A.getValue()) || NSAL0300Constant.IMG_OPEN_MACHINE_GREEN.equals(scrnMsg.A.no(rowNum).xxFilePathTxt_A.getValue())) {
            BigDecimal dsContrDtlPk = scrnMsg.A.no(rowNum).dsContrDtlPk_A.getValue();
            NSAL0300CommonLogic.addCheckItemForLine(scrnMsg, dsContrDtlPk);
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
        if (NSAL0300Constant.IMG_CLOSE_MACHINE_RED.equals(scrnMsg.A.no(rowNum).xxFilePathTxt_A.getValue()) || NSAL0300Constant.IMG_CLOSE_MACHINE_GREEN.equals(scrnMsg.A.no(rowNum).xxFilePathTxt_A.getValue())) {
            if (NSAL0300Constant.IMG_CLOSE_MACHINE_RED.equals(scrnMsg.A.no(rowNum).xxFilePathTxt_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(rowNum).xxFilePathTxt_A, NSAL0300Constant.IMG_OPEN_MACHINE_RED);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(rowNum).xxFilePathTxt_A, NSAL0300Constant.IMG_OPEN_MACHINE_GREEN);
            }

            BigDecimal dsContrDtlPk = scrnMsg.A.no(rowNum).dsContrDtlPk_A.getValue();
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (scrnMsg.B.no(i).dsContrDtlPk_B.getValue().compareTo(dsContrDtlPk) == 0) {
                    // Mod Start 2018/01/10 QC#18552
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BB, NSAL0300Constant.IMG_CLOSE_ARROW);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BM, NSAL0300Constant.IMG_OPEN_ARROW);
                    scrnMsg.setFocusItem(scrnMsg.B.no(i).rnwEffFromDt_B);
                    // Mod End 2018/01/10 QC#18552
                    break;
                }
            }
        } else {
            if (NSAL0300Constant.IMG_OPEN_MACHINE_RED.equals(scrnMsg.A.no(rowNum).xxFilePathTxt_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(rowNum).xxFilePathTxt_A, NSAL0300Constant.IMG_CLOSE_MACHINE_RED);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(rowNum).xxFilePathTxt_A, NSAL0300Constant.IMG_CLOSE_MACHINE_GREEN);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(rowNum).sqId_A);
        }

        // Machine List Header Open/Close
        NSAL0300CommonLogic.setMachineListAllIcon(scrnMsg);
    }
}
