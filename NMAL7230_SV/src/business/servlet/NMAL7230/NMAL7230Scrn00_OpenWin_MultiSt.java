/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_MULTIST;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_CODE;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_CODE_DISPLAY;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_NAME;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_NAME_DISPLAY;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_STATE_SEARCH_POPUP;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_SORT_KEY_ASC;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_ST_TABLE_NM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230Scrn00_OpenWin_MultiSt
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Scrn00_OpenWin_MultiSt extends S21CommonHandler {

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

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = POPUP_NM_STATE_SEARCH_POPUP;
        params[2] = POPUP_ST_TABLE_NM;
        params[3] = getSearchConditionSetting();
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        String[] stNmList = scrnMsg.xxDsMultMsgDplyTxt_HS.getValue().split(",", 0);
        for (int i = 0; i < stNmList.length; i++) {
            scrnMsg.P.no(i).xxComnScrColValTxt_0.setValue(stNmList[i]);
        }
        scrnMsg.P.setValidCount(stNmList.length);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVENT_NM_OPENWIN_MULTIST);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting() {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {POPUP_NM_STATE_CODE_DISPLAY, POPUP_NM_STATE_CODE, null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);

        Object[] whereObj2 = {POPUP_NM_STATE_NAME_DISPLAY, POPUP_NM_STATE_NAME, null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {POPUP_NM_STATE_CODE_DISPLAY, POPUP_NM_STATE_CODE, new BigDecimal("10"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);

        Object[] colObj2 = {POPUP_NM_STATE_NAME_DISPLAY, POPUP_NM_STATE_NAME, new BigDecimal("50"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {POPUP_NM_STATE_CODE, POPUP_SORT_KEY_ASC};

        sortList.add(sortObj1);

        return sortList;
    }
}
