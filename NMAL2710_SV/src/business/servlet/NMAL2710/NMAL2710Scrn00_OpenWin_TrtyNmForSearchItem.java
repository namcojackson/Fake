/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.OPENWIN_TRTYNMFORSEARCHITEM;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NMAL2630_POPUP_PRM_NUM;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2710.common.NMAL2710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2710Scrn00_OpenWin_TrtyNmForSearchItem
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL2710Scrn00_OpenWin_TrtyNmForSearchItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL2710CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        Object[] params = new Object[NMAL2630_POPUP_PRM_NUM];
        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        scrnMsg.P.no(i++).clear(); // Business Area
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.orgNm_H); // Territory Name
        scrnMsg.P.no(i++).clear(); // Parent Territory Name
        scrnMsg.P.no(i++).clear(); // Resource Name
        scrnMsg.P.no(i++).clear(); // Employee ID
        scrnMsg.P.no(i++).clear(); // Rank
        scrnMsg.P.no(i++).clear(); // Line of Business
        scrnMsg.P.no(i++).clear(); // Organization Name
        scrnMsg.P.setValidCount(i);

        for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, OPENWIN_TRTYNMFORSEARCHITEM);
        setArgForSubScreen(params);
    }
}
