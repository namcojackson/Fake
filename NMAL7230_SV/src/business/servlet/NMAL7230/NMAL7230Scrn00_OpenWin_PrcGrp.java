/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_CUSTGRP;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_CUSTGRP_NAME;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_CUSTGRP_NAME_DISPLAY;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_NM_CUSTOMER_GROUP_SEARCH_POPUP;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POPUP_SORT_KEY_ASC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230Scrn00_OpenWin_PrcGrp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Scrn00_OpenWin_PrcGrp extends S21CommonHandler {

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

        int index = getButtonSelectNumber();

        ZYPTableUtil.clear(scrnMsg.P);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = POPUP_NM_CUSTOMER_GROUP_SEARCH_POPUP;
        params[2] = getSelectSQL();
        params[3] = getSearchConditionSetting(scrnMsg.A.no(index).prcGrpNm_A1.getValue());
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVENT_NM_OPENWIN_CUSTGRP);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(String val) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {POPUP_NM_CUSTGRP_NAME_DISPLAY, POPUP_NM_CUSTGRP_NAME, val, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {POPUP_NM_CUSTGRP_NAME_DISPLAY, POPUP_NM_CUSTGRP_NAME, new BigDecimal("50"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {POPUP_NM_CUSTGRP_NAME, POPUP_SORT_KEY_ASC};

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append(" PG.PRC_GRP_NM ");
        sb.append(" , PG.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append(" , PG.EZCANCELFLAG AS EZCANCELFLAG ");
        sb.append(" FROM PRC_GRP PG  ");
        sb.append(" WHERE PG.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND PG.EZCANCELFLAG = '0' ");
        sb.append(" AND PG.PRC_GRP_TP_CD = '").append(PRC_GRP_TP.CUSTOMER_PRICING_GROUP).append("' ");
        String sql = sb.toString();

        return sql;
    }
}
