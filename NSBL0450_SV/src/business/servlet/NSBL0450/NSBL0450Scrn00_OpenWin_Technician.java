/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import static business.servlet.NSBL0450.constant.NSBL0450Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSBL0450Scrn00_OpenWin_Technician extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSBL0450BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.X);
        setValue(scrnMsg.xxScrEventNm, OPENWIN_TECH);
        Object[] params = new Object[LENGTH_7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = "Technician Search";
        // Table SQL
        params[i++] = getSqlStr(scrnMsg.glblCmpyCd.getValue());
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

    private static String getSqlStr(String glblCmpyCd) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append("    PSN.GLBL_CMPY_CD");
        sql.append("    ,PSN.EZCANCELFLAG");
        sql.append("    ,PSN.PSN_CD");
        sql.append("    ,PSN.PSN_FIRST_NM");
        sql.append("    || CASE WHEN PSN.PSN_MID_NM IS NOT NULL THEN ' '  || PSN.PSN_MID_NM END");
        sql.append("    || CASE WHEN PSN.PSN_LAST_NM IS NOT NULL THEN ' ' || PSN.PSN_LAST_NM END PSN_NM");
        sql.append(" FROM ");
        sql.append("    S21_PSN PSN");
        sql.append("    ,TECH_MSTR TECH");
        sql.append(" WHERE");
        sql.append("    PSN.PSN_CD            = TECH.TECH_TOC_CD");
        sql.append("    AND PSN.GLBL_CMPY_CD  = TECH.GLBL_CMPY_CD");
        sql.append("    AND TECH.EZCANCELFLAG = '0' ");

        return sql.toString();
    }

    private List<Object[]> getWhereList(NSBL0450BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Technician Code";
        h0[WLIST_OBJ_ID] = "PSN_CD";
        h0[WLIST_OBJ_VALUE] = "";
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Technician Name";
        h1[WLIST_OBJ_ID] = "PSN_NM";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Technician Code";
        c0[CLIST_OBJ_ID] = "PSN_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(PSN_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Technician Name";
        c1[CLIST_OBJ_ID] = "PSN_NM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(PSN_NM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "PSN_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
}
