/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.POPUP_COLUMN_DISP_NAME_DIST_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.POPUP_COLUMN_DISP_NAME_DIST_NM;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.POPUP_COLUMN_NAME_DIST_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.POPUP_COLUMN_NAME_DIST_NM;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.POPUP_SORT_KEY_ASC;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.POPUP_WIN_TITLE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_OpenWin_DistList
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_OpenWin_DistList extends S21CommonHandler {

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

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = POPUP_WIN_TITLE;
        params[2] = getSearchSql();
        params[3] = getSearchConditionSetting();
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        String[] distListNmList = scrnMsg.ntfyDistListNmListTxt.getValue().split(",", 0);
        for (int i = 0; i < distListNmList.length; i++) {
            scrnMsg.P.no(i).xxComnScrColValTxt_1.setValue(distListNmList[i]);
        }
        scrnMsg.P.setValidCount(distListNmList.length);
        params[6] = scrnMsg.P;

        setArgForSubScreen(params);
    }

    private String getSearchSql() {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("     NDL.GLBL_CMPY_CD ");
        sql.append("    ,NDL.NTFY_DIST_LIST_ID ");
        sql.append("    ,NDL.NTFY_DIST_LIST_NM ");
        sql.append("    ,NDL.EZCANCELFLAG ");
        sql.append("FROM ");
        sql.append("    NTFY_DIST_LIST NDL ");
        sql.append("WHERE ");
        sql.append("        NDL.GLBL_CMPY_CD  = '");
        sql.append(getGlobalCompanyCode());
        sql.append("'");
        sql.append("    AND NDL.NTFY_DIST_LIST_ACTV_FLG       = '");
        sql.append(ZYPConstant.FLG_ON_Y);
        sql.append("'");
        sql.append("    AND NDL.EFF_FROM_DT                   <= '");
        sql.append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        sql.append("'");
        sql.append("    AND NVL(NDL.EFF_THRU_DT, '99991231')  >= '");
        sql.append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        sql.append("'");
        sql.append("    AND NDL.EZCANCELFLAG  = '0'");

        return sql.toString();
    }

    private List<Object> getSearchConditionSetting() {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {POPUP_COLUMN_DISP_NAME_DIST_ID, POPUP_COLUMN_NAME_DIST_ID, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);

        Object[] whereObj2 = {POPUP_COLUMN_DISP_NAME_DIST_NM, POPUP_COLUMN_NAME_DIST_NM, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {POPUP_COLUMN_DISP_NAME_DIST_ID, POPUP_COLUMN_NAME_DIST_ID, new BigDecimal("15"), ZYPConstant.FLG_ON_Y };
        colList.add(colObj1);

        Object[] colObj2 = {POPUP_COLUMN_DISP_NAME_DIST_NM, POPUP_COLUMN_NAME_DIST_NM, new BigDecimal("50"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {POPUP_COLUMN_NAME_DIST_ID, POPUP_SORT_KEY_ASC };
        sortList.add(sortObj1);

        return sortList;
    }
}
