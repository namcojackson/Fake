/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/12/02   Hitachi         T.Kanasaka      Update          QC#1385
 * 2016/02/17   Hitachi         T.Aoyagi        Update          QC2954
 * 2018/01/18   Hitachi         T.Tomita        Update          QC#18552
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Schedule_Base extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
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

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        int rowNum = getButtonSelectNumber();
        Object[] prm = new Object[3];
        // START 2015/12/02 T.Kanasaka [QC#1385, MOD]
        // prm[0] = scrnMsg.xxModeCd_FU;
        // prm[1] = scrnMsg.B.no(rowNum).dsContrDtlPk_B;
        // prm[2] = scrnMsg.B.no(rowNum).dsContrPrcEffPk_BB;
        prm[0] = scrnMsg.B.no(rowNum).dsContrDtlPk_B;
        prm[1] = scrnMsg.B.no(rowNum).dsContrPrcEffPk_BB;
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
        scrnMsg.xxPopPrm_00.setValue(SVC_INV_CHRG_TP.BASE_CHARGE);
        // Mod End 2018/01/18 QC#18552
        prm[2] = scrnMsg.xxPopPrm_00;
        // END 2016/02/17 T.Aoyagi [QC2954, MOD]
        // END 2015/12/02 T.Kanasaka [QC#1385, MOD]

        setArgForSubScreen(prm);
    }
}
