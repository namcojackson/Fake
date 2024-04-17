/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0810.NSAL0810CMsg;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;

import business.servlet.NSAL0810.common.NSAL0810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0810Scrn00_OpenWin_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        NSAL0810CommonLogic.clearPopupDataForScrnMsg(scrnMsg);
        Object[] params = new Object[PARAM_LENGTH_NMAL6770];
        int index = getButtonSelectNumber();
        BigDecimal ctacPsnPk = scrnMsg.A.no(index).ctacPsnPk_A.getValue();
        if (ctacPsnPk != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, ctacPsnPk.toString());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, scrnMsg.A.no(index).ctacPsnFirstNm_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.A.no(index).ctacPsnLastNm_A);

            int i = 0;
            params[i++] = scrnMsg.xxPopPrm_00;
            params[i++] = scrnMsg.xxPopPrm_01;
            params[i++] = scrnMsg.xxPopPrm_02;
            params[i++] = scrnMsg.xxPopPrm_03;
            params[i++] = scrnMsg.xxPopPrm_04;
            params[i++] = scrnMsg.xxPopPrm_05;
            params[i++] = scrnMsg.xxPopPrm_06;
            params[i++] = scrnMsg.xxPopPrm_07;
            params[i++] = scrnMsg.xxPopPrm_08;
            params[i++] = scrnMsg.xxPopPrm_09;
            params[i++] = scrnMsg.xxPopPrm_10;
            params[i++] = scrnMsg.xxPopPrm_11;
            params[i++] = scrnMsg.xxPopPrm_12;
            params[i++] = scrnMsg.xxPopPrm_13;
            params[i++] = scrnMsg.xxPopPrm_14;
            params[i++] = scrnMsg.dsCtacPntPk_PA;
            params[i++] = scrnMsg.dsCtacPntPk_PB;
            params[i++] = scrnMsg.dsCtacPntPk_PC;

            setArgForSubScreen(params);
        }
    }
}
