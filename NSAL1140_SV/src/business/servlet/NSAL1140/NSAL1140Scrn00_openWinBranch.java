/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1140;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1140.common.NSAL1140CommonLogic;
import static business.servlet.NSAL1140.constant.NSAL1140Constant.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/10/21   Hitachi         T.Tomita        Update          QC#15196
 *</pre>
 */
public class NSAL1140Scrn00_openWinBranch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        NSAL1140CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));

    }

    /**
     * set common pop up NWAL1130
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSAL1140BMsg
     * @return object list for parameter for NWAL1130 pop up
     */
    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL1140BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.X);
        setValue(scrnMsg.xxScrEventNm, "Branch Popup");
        Object[] params = new Object[NWAL1130_PRM_LENGTH];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = NWAL1130_WIN_TITLE_BRANCH;
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

    private String getSqlStr(NSAL1140BMsg scrnMsg) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ");
        // START 2016/10/21 T.Tomita [QC#15196, MOD]
//        sb.append("      A.SVC_CONTR_BR_CD");
//        sb.append("     ,A.SVC_BR_DESC_TXT ");
        sb.append("      A.GLBL_CMPY_CD ");
        sb.append("     ,A.EZCANCELFLAG ");
        sb.append("     ,A.SVC_CONTR_BR_CD ");
        sb.append("     ,A.SVC_CONTR_BR_DESC_TXT ");
        // END 2016/10/21 T.Tomita [QC#15196, MOD]
        sb.append(" FROM ");
        sb.append("     SVC_CONTR_BR A ");
        sb.append(" WHERE A.GLBL_CMPY_CD   = '#GLBL_CMPY_CD#' ");
        sb.append("   AND A.EZCANCELFLAG   = '0' ");
        // START 2016/10/21 T.Tomita [QC#15196, MOD]
//        sb.append("   AND A.SVC_CONTR_BR_ACTF_FLG = 'Y' ");
        sb.append("   AND A.SVC_CONTR_BR_ACTV_FLG = 'Y' ");
        // END 2016/10/21 T.Tomita [QC#15196, MOD]
        sb.append("   AND A.EFF_FROM_DT <= '#SLS_DT#' ");
        sb.append("   AND (A.EFF_THRU_DT >= '#SLS_DT#' ");
        sb.append("     OR A.EFF_THRU_DT IS NULL ) ");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());
        sql = sql.replaceAll("#SLS_DT#", ZYPDateUtil.getSalesDate());

        return sql;
    }

    private List<Object[]> getWhereList(NSAL1140BMsg scrnMsg) {
        String branch = scrnMsg.svcContrBrCd.getValue();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Branch Code";
        h0[WLIST_OBJ_ID] = "SVC_CONTR_BR_CD";
        // START 2016/10/21 T.Tomita [QC#15196, MOD]
//        h0[WLIST_OBJ_VALUE] = "";
        h0[WLIST_OBJ_VALUE] = branch;
        // END 2016/10/21 T.Tomita [QC#15196, MOD]
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Branch Name";
        // START 2016/10/21 T.Tomita [QC#15196, MOD]
//        h1[WLIST_OBJ_ID] = "SVC_BR_DESC_TXT";
//        h1[WLIST_OBJ_VALUE] = branch;
        h1[WLIST_OBJ_ID] = "SVC_CONTR_BR_DESC_TXT";
        h1[WLIST_OBJ_VALUE] = "";
        // END 2016/10/21 T.Tomita [QC#15196, MOD]
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_WHERE_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Branch Code";
        c0[CLIST_OBJ_ID] = "SVC_CONTR_BR_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_CONTR_BR_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_WHERE_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Branch Name";
        // START 2016/10/21 T.Tomita [QC#15196, MOD]
//        c1[CLIST_OBJ_ID] = "SVC_BR_DESC_TXT";
        c1[CLIST_OBJ_ID] = "SVC_CONTR_BR_DESC_TXT";
        // END 2016/10/21 T.Tomita [QC#15196, MOD]
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_BR_DESC_TXT_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "SVC_CONTR_BR_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
}
