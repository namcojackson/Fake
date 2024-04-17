/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import static business.servlet.NSAL0310.constant.NSAL0310Constant.*;

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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 *</pre>
 */
public class NSAL0310Scrn00_OpenWin_Model extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2015/10/08 T.Tomita [N/A, MOD]
//        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
//        NSAL0310CommonLogic.clearPopupParameter(scrnMsg);
//        scrnMsg.xxPopPrm_00.setValue("MDL_NM_V");
//        scrnMsg.xxPopPrm_01.setValue("T_MDL_NM");
//        scrnMsg.xxPopPrm_02.setValue("T_MDL_ID");
//        scrnMsg.xxPopPrm_03.setValue("T_MDL_NM");
//        scrnMsg.xxPopPrm_04.setValue("Model Search Popup");
//        scrnMsg.xxPopPrm_05.setValue("Model Name");
//        scrnMsg.xxPopPrm_06.setValue("Model ID");
//        scrnMsg.xxPopPrm_07.setValue("Model Name");
//        scrnMsg.xxPopPrm_08.setValue("Model ID");
//        Object[] prm = new Object[11];
//        prm[0] = scrnMsg.xxPopPrm_00; // 1.TBL_NM
//        prm[1] = scrnMsg.xxPopPrm_01; // 2.TBL_CD_COLUMN_CD
//        prm[2] = scrnMsg.xxPopPrm_02; // 3.TBL_CD_COLUMN_NM
//        prm[3] = scrnMsg.xxPopPrm_03; // 4.TBL_SORT_NUM_COLUMN_NM
//        prm[4] = scrnMsg.xxPopPrm_04; // 5.SCR_NM
//        prm[5] = scrnMsg.xxPopPrm_05; // 6.HDR_CD_LABEL
//        prm[6] = scrnMsg.xxPopPrm_06; // 7.HDR_NM_LABEL
//        prm[7] = scrnMsg.xxPopPrm_07; // 8.DTL_CD_LABEL
//        prm[8] = scrnMsg.xxPopPrm_08; // 9.DTL_NM_LABEL
//        prm[9] = scrnMsg.mdlNm; // 10.CONDITION_CD
//        prm[10] = scrnMsg.xxPopPrm_10; // 11.CONDITION_NM
//        setArgForSubScreen(prm);

        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
        // END 2015/10/08 T.Tomita [N/A, MOD]
    }

    // START 2015/10/08 T.Tomita [N/A, ADD]
    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0310BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.B);
        Object[] params = new Object[7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = "Model Search Popup";
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
        sql.append("      A.GLBL_CMPY_CD");
        sql.append("     ,A.EZCANCELFLAG ");
        sql.append("     ,A.MDL_ID");
        sql.append("     ,B.T_MDL_NM");
        sql.append(" FROM ");
        sql.append("      DS_MDL A ");
        sql.append("     ,MDL_NM B ");
        sql.append(" WHERE ");
        sql.append("        A.EZCANCELFLAG = '0' ");
        sql.append("    AND A.GLBL_CMPY_CD = B.T_GLBL_CMPY_CD ");
        sql.append("    AND A.MDL_ID       = B.T_MDL_ID ");
        sql.append("    AND B.EZCANCELFLAG = '0' ");

        return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL0310BMsg scrnMsg) {
        String mdlNm = scrnMsg.mdlNm.getValue();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Model ID";
        h0[WLIST_OBJ_ID] = "MDL_ID";
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
        c0[CLIST_OBJ_ID] = "MDL_ID";
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
        sortConditionArray[0] = "MDL_ID";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
    // END 2015/10/08 T.Tomita [N/A, ADD]
}
