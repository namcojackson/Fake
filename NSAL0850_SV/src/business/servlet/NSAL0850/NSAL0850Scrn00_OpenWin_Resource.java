/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0850;

import static business.servlet.NSAL0850.constant.NSAL0850Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Sales Credit for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Create          QC#5452
 *</pre>
 */
public class NSAL0850Scrn00_OpenWin_Resource extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0850BMsg scrnMsg = (NSAL0850BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0850BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.X);
        Object[] params = new Object[LENGTH_7];

        int i = 0;
        // Global company code 
        params[i++] = "";
        // Window title
        params[i++] = "Resource Popup";
        // Table SQL
        params[i++] = getSqlStr(RGTN_STS.READY_FOR_ORDER_TAKING);
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

    private static String getSqlStr(String rgtnStsCd) {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("      T.GLBL_CMPY_CD");
        sql.append("     ,T.EZCANCELFLAG");
        sql.append("     ,T.TOC_CD");
        sql.append("     ,T.TOC_NM");
        sql.append(" FROM");
        sql.append("      S21_ORG T");
        sql.append(" WHERE");
        sql.append("      T.RGTN_STS_CD  = '" + rgtnStsCd + "'");

        return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL0850BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Rep Code";
        h0[WLIST_OBJ_ID] = "TOC_CD";
        h0[WLIST_OBJ_VALUE] = "";
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Rep Name";
        h1[WLIST_OBJ_ID] = "TOC_NM";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Rep Code";
        c0[CLIST_OBJ_ID] = "TOC_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(TOC_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Rep Name";
        c1[CLIST_OBJ_ID] = "TOC_NM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(TOC_NM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "TOC_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
}
