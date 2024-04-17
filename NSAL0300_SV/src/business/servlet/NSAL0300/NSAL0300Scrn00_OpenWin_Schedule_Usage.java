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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/12/03   Hitachi         T.Kanasaka      Update          QC#1419
 * 2016/02/17   Hitachi         T.Aoyagi        Update          QC2954
 * 2017/07/19   Hitachi         K.Kim           Update          QC#19992
 * 2017/08/25   Hitachi         U.Kim           Update          QC#20728
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Schedule_Usage extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        
        // START 2017/08/25 U.Kim [QC#20728, ADD]
        // check radio selection / default selection for closed meter
        int buttonRowNum = getButtonSelectNumber();
        // Mod Start 2018/01/21 QC#18552
        // set default selection
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum_BX, new BigDecimal(buttonRowNum));
//        if (NSAL0300Constant.IMG_CLOSE_ARROW.equals(scrnMsg.B.no(buttonRowNum).xxFilePathTxt_BM.getValue())) {
//            // set default selection
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum_BX, new BigDecimal(buttonRowNum));
//        } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxRowNum_BX) || scrnMsg.xxRowNum_BX.getValueInt() < 0) {
//            if (NSAL0300CommonLogic.getBillingMeterCount(scrnMsg, buttonRowNum) == 1) {
//                // set default selection
//                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum_BX, new BigDecimal(buttonRowNum));
//            } else {
//                // no selection error
//                scrnMsg.setMessageInfo(NSAL0300Constant.NSAM0034E);
//            }
//        } else {
//            BigDecimal dsContrDtlPk = scrnMsg.B.no(scrnMsg.xxRowNum_BX.getValueInt()).dsContrDtlPk_B.getValue();
//            if (scrnMsg.B.no(buttonRowNum).dsContrDtlPk_B.getValue().compareTo(dsContrDtlPk) != 0) {
//                // different selection error
//                scrnMsg.setMessageInfo(NSAL0300Constant.NSAM0034E);
//            }
//        }
        // Mod End 2018/01/21 QC#18552
        // END 2017/08/25 U.Kim [QC#20728, ADD]
        NSAL0300CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;

        // START 2017/07/19 K.Kim [QC#19992, ADD]
        int rowNum = scrnMsg.xxRowNum_BX.getValueInt();
        // END 2017/07/19 K.Kim [QC#19992, ADD]
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // START 2017/07/19 K.Kim [QC#19992, DEL]
        // int rowNum = getButtonSelectNumber();
        // END 2017/07/19 K.Kim [QC#19992, DEL]
        Object[] prm = new Object[3];
        // START 2015/12/03 T.Kanasaka [QC#1419, MOD]
        // prm[0] = scrnMsg.xxModeCd_FU;
        // prm[1] = scrnMsg.dsContrPk;
        // prm[2] = scrnMsg.B.no(rowNum).dsContrPrcEffPk_BM;
        prm[0] = scrnMsg.B.no(rowNum).dsContrDtlPk_B;
        prm[1] = scrnMsg.B.no(rowNum).dsContrPrcEffPk_BM;
        // START 2016/02/17 T.Aoyagi [QC2954, MOD]
        // Mod Start 2018/01/18 QC#18552
////        prm[2] = scrnMsg.xxModeCd_FU;
//        if (NSAL0300Constant.DISPLAY_MODE_FREEZE.equals(scrnMsg.xxModeCd_FU.getValue())) {
//            scrnMsg.xxPopPrm_00.setValue(NSAL0300Constant.DISPLAY_MODE_FREEZE);
//        } else {
//            if (NSAL0300CommonLogic.isAggMachine(scrnMsg, rowNum)) {
//                scrnMsg.xxPopPrm_00.setValue(NSAL0300Constant.DISPLAY_MODE_FREEZE);
//            } else {
//                scrnMsg.xxPopPrm_00.setValue(NSAL0300Constant.DISPLAY_MODE_UPDATE);
//            }
//        }
        scrnMsg.xxPopPrm_00.setValue(SVC_INV_CHRG_TP.METER_CHARGE);
        // Mod End 2018/01/18 QC#18552
        prm[2] = scrnMsg.xxPopPrm_00;
        // END 2016/02/17 T.Aoyagi [QC2954, MOD]
        // END 2015/12/03 T.Kanasaka [QC#1419, MOD]

        setArgForSubScreen(prm);
    }
}
