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
 * 2019/01/28   Fujitsu         Nagshima        Update          QC#28632
 *</pre>
 */
public class NSAL0300Scrn00_ExpandFleetAggregate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (NSAL0300Constant.IMG_OPEN_ARROW.equals(scrnMsg.xxFilePathTxt_FA.getValue())) {
            NSAL0300CommonLogic.addCheckItemForFleetAggregateLine(scrnMsg);
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

        if (NSAL0300Constant.IMG_CLOSE_ARROW.equals(scrnMsg.xxFilePathTxt_FA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_FA, NSAL0300Constant.IMG_OPEN_ARROW);
            scrnMsg.setFocusItem(scrnMsg.B.no(0).contrEffFromDt_B);

            // Add Start 2018/01/10 QC#18552
            BigDecimal dsContrDtlPk = scrnMsg.B.no(0).dsContrDtlPk_B.getValue();
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
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_FA, NSAL0300Constant.IMG_CLOSE_ARROW);
//            scrnMsg.setFocusItem(scrnMsg.dsContrNum);
        }
    }
}
