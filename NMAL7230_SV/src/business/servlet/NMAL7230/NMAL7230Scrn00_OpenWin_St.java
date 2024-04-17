/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_ST;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NAML6050_PRM_CODE_NUM;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NAML6050_PRM_NAME_NUM;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NAML6050_PRM_NUM;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_CODE;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_CODE_DISPLAY;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_NAME;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_NAME_DISPLAY;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_SEARCH_POPUP;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_SORT;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_ST_TABLE_NM;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230Scrn00_OpenWin_St
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Scrn00_OpenWin_St extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        NMAL7230CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7230CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);

        int index = getButtonSelectNumber();

        Object[] params = new Object[NAML6050_PRM_NUM];
        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_ST_TABLE_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_CODE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_NAME);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_SORT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_SEARCH_POPUP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_CODE_DISPLAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_NAME_DISPLAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_CODE_DISPLAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_STATE_NAME_DISPLAY);
        scrnMsg.P.setValidCount(i);
        params[NAML6050_PRM_CODE_NUM] = scrnMsg.A.no(index).shipToStCd_A1;
        params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;

        for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVENT_NM_OPENWIN_ST);
        setArgForSubScreen(params);
    }
}
