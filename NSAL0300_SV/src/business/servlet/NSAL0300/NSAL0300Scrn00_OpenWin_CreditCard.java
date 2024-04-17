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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Hitachi         T.Kanasaka      Create          N/A
 * 2015/12/04   Hitachi         A.Kohinata      Update          QC1471
 * 2016/06/09   Hitachi         T.Kanasaka      Update          QC#9708
 * 2016/07/01   Hitachi         T.Kanasaka      Update          QC#11279
 * 2016/09/06   Hitachi         A.Kohinata      Update          QC#11243
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_CreditCard extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // START 2016/07/01 Kanasaka [QC#11279, ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsContrPk)) {
            scrnMsg.setMessageInfo(NSAL0300Constant.NSAM0552E);
            throw new EZDFieldErrorException();
        }
        // END 2016/07/01 Kanasaka [QC#11279, ADD]
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

        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        Object[] prm = new Object[13];
        // mod start 2016/09/06 CSA Defect#11243
        // START 2016/06/09 Kanasaka [QC#9708, MOD]
//        prm[0] = scrnMsg.altPayerCustCd;
//        prm[0] = scrnMsg.dsAcctNum;
        prm[0] = scrnMsg.sellToCustCd;
        // END 2016/06/09 Kanasaka [QC#9708, MOD]
        // mod end 2016/09/06 CSA Defect#11243
        scrnMsg.xxPopPrm_01.setValue(CR_CARD_TRX_TP.CONTRACT_HEADER);
        prm[1] = scrnMsg.xxPopPrm_01;
        prm[2] = scrnMsg.xxPopPrm_02;
        prm[3] = scrnMsg.xxPopPrm_03;
        prm[4] = scrnMsg.xxPopPrm_04;
        prm[5] = scrnMsg.xxPopPrm_05;
        prm[6] = scrnMsg.xxPopPrm_06;
        // START 2016/07/01 Kanasaka [QC#11279, MOD]
//        prm[7] = scrnMsg.dsContrDtlPk_P;
        prm[7] = scrnMsg.dsContrPk;
        // END 2016/07/01 Kanasaka [QC#11279, MOD]
        prm[8] = scrnMsg.dsContrDtlPk_P;
        prm[9] = scrnMsg.dsContrDtlPk_P;
        prm[10] = scrnMsg.dsContrDtlPk_P;
        prm[11] = scrnMsg.dsContrDtlPk_P;
        prm[12] = scrnMsg.dsCrCardPk;

        setArgForSubScreen(prm);
    }
}
