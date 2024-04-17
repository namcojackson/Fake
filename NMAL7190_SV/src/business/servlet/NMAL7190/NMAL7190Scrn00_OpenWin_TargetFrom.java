/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_BTN_FLG_FROM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190Scrn00_OpenWin_TargetFrom
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7190Scrn00_OpenWin_TargetFrom extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7190CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        int index = getButtonSelectNumber();
        // 0:From 1:To
        scrnMsg.xxBtnFlg.setValue(POPUP_BTN_FLG_FROM);

        Object[] params = NMAL7190CommonLogic.createPopPrm(scrnMsg, scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.A.no(index).prcGrpFromTxt_CD, index, getGlobalCompanyCode());
        setResult(scrnMsg.xxScrEventNm.getValue());
        setArgForSubScreen(params);
    }
}
