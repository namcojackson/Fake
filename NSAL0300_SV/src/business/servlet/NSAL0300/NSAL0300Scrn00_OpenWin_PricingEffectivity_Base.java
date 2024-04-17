/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
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
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Hitachi         T.Kanasaka      Create          N/A
 * 2015/12/02   Hitachi         T.Kanasaka      Update          QC#1381
 * 2016/02/17   Hitachi         T.Aoyagi        Update          QC2954
 * 2018/05/11   Hitachi         K.Kim           Update          QC#25897
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_PricingEffectivity_Base extends S21CommonHandler {

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
        Object[] prm = new Object[2];
        // START 2015/12/02 T.Kanasaka [QC#1381, MOD]
        // prm[0] = scrnMsg.xxModeCd_FU;
        // prm[1] = scrnMsg.B.no(rowNum).dsContrDtlPk_B;
        prm[0] = scrnMsg.B.no(rowNum).dsContrDtlPk_B;
        // START 2016/02/17 T.Aoyagi [QC2954, MOD]
        // START 2018/05/11 K.Kim [QC#25897, MOD]
        prm[1] = scrnMsg.xxModeCd_FU;
        // if (NSAL0300Constant.DISPLAY_MODE_FREEZE.equals(scrnMsg.xxModeCd_FU.getValue())) {
        //     scrnMsg.xxPopPrm_00.setValue(NSAL0300Constant.DISPLAY_MODE_FREEZE);
        // } else {
        //     if (NSAL0300CommonLogic.isAggMachine(scrnMsg, rowNum)) {
        //         scrnMsg.xxPopPrm_00.setValue(NSAL0300Constant.DISPLAY_MODE_FREEZE);
        //     } else {
        //         scrnMsg.xxPopPrm_00.setValue(NSAL0300Constant.DISPLAY_MODE_UPDATE);
        //     }
        // }
        // prm[1] = scrnMsg.xxPopPrm_00;
        // END 2018/05/11 K.Kim [QC#25897, MOD]
        // END 2016/02/17 T.Aoyagi [QC2954, MOD]
        // END 2015/12/02 T.Kanasaka [QC#1381, MOD]

        setArgForSubScreen(prm);
    }
}
