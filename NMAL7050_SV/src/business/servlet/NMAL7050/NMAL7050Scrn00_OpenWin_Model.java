/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050;

import static business.servlet.NMAL7050.constant.NMAL7050Constant.POPUP_NM_MODEL_NAME;
import static business.servlet.NMAL7050.constant.NMAL7050Constant.POPUP_NM_MODEL_NAME_DISPLAY;
import static business.servlet.NMAL7050.constant.NMAL7050Constant.POPUP_NM_SERVICE_MODEL_SEARCH;
import static business.servlet.NMAL7050.constant.NMAL7050Constant.POPUP_SORT_KEY_ASC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7050.constant.NMAL7050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7050Scrn00_OpenWin_Model
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050Scrn00_OpenWin_Model extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = POPUP_NM_SERVICE_MODEL_SEARCH;
        params[2] = getSelectSQL();
        params[3] = getSearchConditionSetting();
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        String[] mdlNmList = scrnMsg.xxDsMultMsgDplyTxt.getValue().split(",", 0);
        for (int i = 0; i < mdlNmList.length; i++) {
            scrnMsg.P.no(i).xxComnScrColValTxt_0.setValue(mdlNmList[i]);
        }
        scrnMsg.P.setValidCount(mdlNmList.length);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NMAL7050Constant.EVENT_NM_LINK_MDL);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting() {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {POPUP_NM_MODEL_NAME_DISPLAY, POPUP_NM_MODEL_NAME, null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {POPUP_NM_MODEL_NAME_DISPLAY, POPUP_NM_MODEL_NAME, new BigDecimal("50"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {POPUP_NM_MODEL_NAME, POPUP_SORT_KEY_ASC};

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append(" MN.T_MDL_NM ");
        sb.append(" , MN.T_GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append(" , MN.EZCANCELFLAG ");
        sb.append(" FROM MDL_NM MN ");
        sb.append(" WHERE MN.T_GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND MN.EZCANCELFLAG = '0' ");
        String sql = sb.toString();

        return sql;
    }
}
