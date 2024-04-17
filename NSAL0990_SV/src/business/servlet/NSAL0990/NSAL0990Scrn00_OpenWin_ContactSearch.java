/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/11   Hitachi         K.Kitachi       Create          QC#11642
 *</pre>
 */
public class NSAL0990Scrn00_OpenWin_ContactSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg = (NSAL0990CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0990CommonLogic.clearPopUpParam(scrnMsg);
        setValue(scrnMsg.xxPopPrm_P1, CTAC_TP.DELIVERY_INSTALL);
        setValue(scrnMsg.xxPopPrm_PB, ZYPConstant.FLG_ON_Y);
        setValue(scrnMsg.xxPopPrm_PC, ZYPConstant.FLG_ON_Y);

        Object[] params = new Object[18];

        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.locNum_HD;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.ctacPsnFirstNm;
        params[7] = scrnMsg.ctacPsnLastNm;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.ctacPsnEmlAddr;
        params[10] = scrnMsg.xxPopPrm_PA;
        params[11] = scrnMsg.xxPopPrm_PB;
        params[12] = scrnMsg.xxPopPrm_PC;
        params[13] = scrnMsg.xxPopPrm_PE;
        params[14] = scrnMsg.xxPopPrm_PF;
        params[15] = scrnMsg.dsCtacPntPk_P0;
        params[16] = scrnMsg.dsCtacPntPk_P1;
        params[17] = scrnMsg.dsCtacPntPk_P2;

        setArgForSubScreen(params);
    }
}
