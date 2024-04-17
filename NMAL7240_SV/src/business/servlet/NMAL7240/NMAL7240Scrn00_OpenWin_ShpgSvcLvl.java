/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240;

import static business.servlet.NMAL7240.constant.NMAL7240Constant.*;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.NAML6050_PRM_CODE_NUM;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.NAML6050_PRM_NAME_NUM;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.NAML6050_PRM_NUM;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.POPUP_NM_SHPGSVCLVL_CODE;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.POPUP_NM_SHPGSVCLVL_CODE_DISPLAY;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.POPUP_NM_SHPGSVCLVL_NAME;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.POPUP_NM_SHPGSVCLVL_NAME_DISPLAY;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.POPUP_NM_SHPGSVCLVL_SEARCH_POPUP;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.POPUP_NM_SHPGSVCLVL_SORT;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.POPUP_SHPGSVCLVL_TABLE_NM;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7240.common.NMAL7240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7240Scrn00_OpenWin_ShpgSvcLvl
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/06   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7240Scrn00_OpenWin_ShpgSvcLvl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        NMAL7240CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7240CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7240CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);

        int index = getButtonSelectNumber();

        Object[] params = new Object[NAML6050_PRM_NUM];
        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_SHPGSVCLVL_TABLE_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_CODE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_NAME);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_SORT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_SEARCH_POPUP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_CODE_DISPLAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_NAME_DISPLAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_CODE_DISPLAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, POPUP_NM_SHPGSVCLVL_NAME_DISPLAY);
        scrnMsg.P.setValidCount(i);
        params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
        params[NAML6050_PRM_NAME_NUM] = scrnMsg.A.no(index).shpgSvcLvlDescTxt_A1;

        for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVENT_NM_OPENWIN_SHPGSVCLVL);
        setArgForSubScreen(params);
    }
}
