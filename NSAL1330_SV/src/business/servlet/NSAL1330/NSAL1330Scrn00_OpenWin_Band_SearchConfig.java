/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.ZZZM9025E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OpenWin_Band_SearchConfig
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Band_SearchConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int ixU = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_Z, new BigDecimal(ixU));
        int ixR = NSAL1330CommonLogic.getPrcDtlIndexConfig(scrnMsg, ixU);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(ixR));

        if (!ZYPCommonFunc.hasValue(scrnMsg.R.no(ixR).prcCatgNm_R)) {
            scrnMsg.R.no(ixR).prcCatgNm_R.setErrorInfo(1, ZZZM9025E, new String[] {"Service Price List" });
            scrnMsg.addCheckItem(scrnMsg.R.no(ixR).prcCatgNm_R);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        Object[] param = NSAL1330CommonLogic.getNSAL1340ParamConfig(scrnMsg);
        setArgForSubScreen(param);
    }
}
