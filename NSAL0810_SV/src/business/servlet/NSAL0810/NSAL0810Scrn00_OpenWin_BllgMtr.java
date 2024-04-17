/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Hitachi         T.Tomita        Create          QC#8898
 *</pre>
 */
public class NSAL0810Scrn00_OpenWin_BllgMtr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0810BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.X);
        Object[] params = new Object[LENGTH_7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = "Billing Meter Search Popup";
        // Table SQL
        params[i++] = getSqlStr(scrnMsg);
        // Where List
        params[i++] = getWhereList(scrnMsg);
        // Column List
        List<Object[]> columnList = getColumnList();
        params[i++] = columnList;
        // Sort Condition List
        params[i++] = getSortConditionList();
        // outPut List
        params[i++] = scrnMsg.X;

        return params;
    }

    private static String getSqlStr(NSAL0810BMsg scrnMsg) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT");
        sb.append("      MLB.GLBL_CMPY_CD");
        sb.append("     ,MLB.EZCANCELFLAG");
        sb.append("     ,MLB.MTR_LB_CD");
        sb.append("     ,MLB.MTR_LB_DESC_TXT");
        sb.append("     ,MLB.MTR_LB_SORT_NUM");
        sb.append(" FROM");
        sb.append("     MTR_LB MLB");
        sb.append(" WHERE");
        sb.append("         MLB.GLBL_CMPY_CD    =   '#glblCmpyCd#'");
        sb.append("     AND MLB.EZCANCELFLAG    =   '0'");
        sb.append("     AND MLB.MTR_LB_TP_CD    =   '#mtrLbTpCd#'");
        sb.append("     AND MLB.ACTV_FLG        =   'Y'");
        sb.append("     AND MLB.EFF_FROM_DT     <=  '#slsDt#'");
        sb.append("     AND (   MLB.EFF_THRU_DT >=  '#slsDt#'");
        sb.append("         OR  MLB.EFF_THRU_DT IS  NULL)");
        sb.append(" ORDER BY");
        sb.append("     MLB.MTR_LB_SORT_NUM");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", scrnMsg.glblCmpyCd.getValue());
        sql = sql.replaceAll("#mtrLbTpCd#", MTR_LB_TP.BILLING_METER);
        sql = sql.replaceAll("#slsDt#", scrnMsg.slsDt.getValue());
        return sql;
    }

    private List<Object[]> getWhereList(NSAL0810BMsg scrnMsg) {

        int idx = getButtonSelectNumber();
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Counter ID";
        h0[WLIST_OBJ_ID] = "MTR_LB_CD";
        h0[WLIST_OBJ_VALUE] = scrnMsg.A.no(idx).bllgMtrLbCd_A.getValue();
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Counter Name";
        h1[WLIST_OBJ_ID] = "MTR_LB_DESC_TXT";
        h1[WLIST_OBJ_VALUE] = scrnMsg.A.no(idx).mtrLbDescTxt_A.getValue();
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Counter ID";
        c0[CLIST_OBJ_ID] = "MTR_LB_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(MTR_LB_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Counter Name";
        c1[CLIST_OBJ_ID] = "MTR_LB_DESC_TXT";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(MTR_LB_DESC_TXT_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private List<Object[]> getSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] s1 = new Object[ATTR_NUM_SORT_LIST];
        s1[SLIST_CLMN_NM] = "MTR_LB_SORT_NUM";
        s1[SLIST_ORD_COND] = "ASC";
        sortConditionList.add(s1);

        return sortConditionList;
    }
}
