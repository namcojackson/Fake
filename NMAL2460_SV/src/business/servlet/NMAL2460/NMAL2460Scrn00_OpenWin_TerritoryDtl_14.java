/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASG_TRTY_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         O.Okuma         Create          N/A
 * 2017/11/28   Fujitsu         Hd.Sugawara     Update          QC#21044
 *</pre>
 */
public class NMAL2460Scrn00_OpenWin_TerritoryDtl_14 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        int eventLine = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_EV, BigDecimal.valueOf(eventLine));

        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(eventLine).orgNm_BE)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(eventLine).orgNm_BE);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.asgTrtyAttrbCd, ASG_TRTY_ATTRB.ATTRIBUTE14_ID);

        NMAL2460CommonLogic.clearParam(scrnMsg);
        // Mod Start 2017/11/28 QC#21044
        //Object[] params = NMAL2460CommonLogic.setParamForTerritoryPopupDtl(scrnMsg, scrnMsg.B.no(scrnMsg.xxNum_EV.getValueInt()).orgNm_BE);
        Object[] params = NMAL2460CommonLogic.setParamForTerritoryPopupDtl(scrnMsg, //
                scrnMsg.B.no(scrnMsg.xxNum_EV.getValueInt()).orgNm_BE, scrnMsg.slsCrQuotFlg_14);
        // Mod End 2017/11/28 QC#21044
        setArgForSubScreen(params);
    }
}
