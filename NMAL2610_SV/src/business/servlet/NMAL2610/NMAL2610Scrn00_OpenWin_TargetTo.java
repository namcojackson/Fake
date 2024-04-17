/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL2610.NMAL2610CMsg;
//import business.servlet.NMAL2610.constant.NMAL2610Constant;

import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/03/08   Fujitsu         R.Nakamura      Create          QC#15878
 *</pre>
 */
public class NMAL2610Scrn00_OpenWin_TargetTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_ERR)) {
            NMAL2610CommonLogic.addCheckItem(scrnMsg, true);
            scrnMsg.putErrorScreen();
            scrnMsg.xxDplyTab.setValue(scrnMsg.xxDplyTab_BK.getValue());
            throw new EZDFieldErrorException();
        }

        // 0:From 1:To
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg, NMAL2610Constant.POPUP_BTN_FLG_TO);

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        Object[] params = NMAL2610CommonLogic.createPopPrm(scrnMsg, idx, getGlobalCompanyCode());
        setResult(scrnMsg.xxScrEventNm.getValue());
        setArgForSubScreen(params);
    }
}
