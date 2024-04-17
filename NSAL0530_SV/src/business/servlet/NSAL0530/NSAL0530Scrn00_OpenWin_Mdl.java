/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import static business.servlet.NSAL0530.constant.NSAL0530Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NSAL0530Scrn00_OpenWin_Mdl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        NSAL0530CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    /**
     * set common pop up NWAL1130
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSAL0530BMsg
     * @return object list for parameter for NWAL1130 pop up
     */
    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0530BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.B);
        setValue(scrnMsg.xxScrEventNm, OPENWIN_MDL);
        Object[] params = new Object[NWAL1130_PRM_LENGTH];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = NWAL1130_WIN_TITLE_MDL;
        // Table SQL
        params[i++] = getSqlStr();
        // Where List
        params[i++] = getWhereList(scrnMsg);
        // Column List
        List<Object[]> columnList = getColumnList();
        params[i++] = columnList;
        // Sort Condition List
        params[i++] = getSortConditionList();
        // outPut List
        params[i++] = scrnMsg.B;

        return params;
    }

    private static String getSqlStr() {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT ");
        sql.append("      A.T_GLBL_CMPY_CD AS GLBL_CMPY_CD");
        sql.append("     ,A.EZCANCELFLAG ");
        sql.append("     ,A.T_MDL_ID");
        sql.append("     ,A.T_MDL_NM");
        sql.append(" FROM ");
        sql.append("     MDL_NM A ");

        return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL0530BMsg scrnMsg) {
        String mdlNm = scrnMsg.mdlNm_H.getValue();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Model ID";
        h0[WLIST_OBJ_ID] = "T_MDL_ID";
        h0[WLIST_OBJ_VALUE] = "";
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Model Name";
        h1[WLIST_OBJ_ID] = "T_MDL_NM";
        h1[WLIST_OBJ_VALUE] = mdlNm;
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_WHERE_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Model ID";
        c0[CLIST_OBJ_ID] = "T_MDL_ID";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(T_MDL_ID_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_WHERE_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Model Name";
        c1[CLIST_OBJ_ID] = "T_MDL_NM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(T_MDL_NM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "T_MDL_ID";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
}
