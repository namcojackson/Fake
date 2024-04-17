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

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Hitachi         T.Kanasaka      Create          QC#4806
 * 2018/01/10   Hitachi         T.Tomita        Update          QC#18552
 * 2022/10/05   Hitachi         N.Takatsu       Update          QC#60071
 *</pre>
 */
public class NSAL0300Scrn00_ExpandMachineAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (NSAL0300Constant.IMG_OPEN_MACHINE_ALL.equals(scrnMsg.xxFilePathTxt_M.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                BigDecimal dsContrDtlPk = scrnMsg.A.no(i).dsContrDtlPk_A.getValue();
                NSAL0300CommonLogic.addCheckItemForLine(scrnMsg, dsContrDtlPk);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2022/10/05 N.Takatsu[QC#60072, MOD]
        // return null;
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // END 2022/10/05 N.Takatsu[QC#60072, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        // START 2022/10/05 N.Takatsu[QC#60072, MOD]
//        if (NSAL0300Constant.IMG_CLOSE_MACHINE_ALL.equals(scrnMsg.xxFilePathTxt_M.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_M, NSAL0300Constant.IMG_OPEN_MACHINE_ALL);
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                if (NSAL0300Constant.IMG_CLOSE_MACHINE_RED.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFilePathTxt_A, NSAL0300Constant.IMG_OPEN_MACHINE_RED);
//                } else if (NSAL0300Constant.IMG_CLOSE_MACHINE_GREEN.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFilePathTxt_A, NSAL0300Constant.IMG_OPEN_MACHINE_GREEN);
//                }
//                // Add Start 2018/01/10 QC#18552
//                BigDecimal dsContrDtlPk = scrnMsg.A.no(i).dsContrDtlPk_A.getValue();
//                for (int bIdx = 0; bIdx < scrnMsg.B.getValidCount(); bIdx++) {
//                    if (scrnMsg.B.no(bIdx).dsContrDtlPk_B.getValue().compareTo(dsContrDtlPk) == 0) {
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(bIdx).xxFilePathTxt_BB, NSAL0300Constant.IMG_CLOSE_ARROW);
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(bIdx).xxFilePathTxt_BM, NSAL0300Constant.IMG_OPEN_ARROW);
//                    }
//                }
//                // Add End 2018/01/10 QC#18552
//            }
//        } else {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_M, NSAL0300Constant.IMG_CLOSE_MACHINE_ALL);
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                if (NSAL0300Constant.IMG_OPEN_MACHINE_RED.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFilePathTxt_A, NSAL0300Constant.IMG_CLOSE_MACHINE_RED);
//                } else if (NSAL0300Constant.IMG_OPEN_MACHINE_GREEN.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFilePathTxt_A, NSAL0300Constant.IMG_CLOSE_MACHINE_GREEN);
//                }
//            }
//        }

        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2022/10/05 N.Takatsu[QC#60072, MOD]

        scrnMsg.setFocusItem(scrnMsg.A.no(0).sqId_A);
    }
}
