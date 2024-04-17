/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0960;

import static business.servlet.NSAL0960.constant.NSAL0960Constant.ATTR_NUM_CLMN_LIST;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.ATTR_NUM_WHERE_LIST;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.CLIST_DSP_OBJ_NM;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.CLIST_LINK_FLG;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.CLIST_OBJ_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.CLIST_OBJ_LENGTH;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.DATE_LENGTH;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.DS_CONTR_VLD_CATG_DESC_TXT_LENGTH;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.DS_CONTR_VLD_DESC_TXT_LENGTH;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.DS_CONTR_VLD_NM_LENGTH;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.LENGTH_7;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.SELECT_POPUP_DETAIL;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.WLIST_DSP_OBJ_NM;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.WLIST_OBJ_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.WLIST_OBJ_VALUE;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.WLIST_WHERE_FLG;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0960Scrn00_OpenWin_VldCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;
        setValue(scrnMsg.xxPopPrm_SE, SELECT_POPUP_DETAIL);
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0960BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.X);
        Object[] params = new Object[LENGTH_7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = "Validation Category Search";
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

    private static String getSqlStr(NSAL0960BMsg scrnMsg) {
        StringBuilder sql = new StringBuilder();
        String dateFormat = ZYPDateUtil.getDateFormatString(true);
        sql.append(" SELECT");
        sql.append("      DCV.GLBL_CMPY_CD                 AS GLBL_CMPY_CD");
        sql.append("     ,DCV.DS_CONTR_VLD_PK              AS DS_CONTR_VLD_PK");
        sql.append("     ,DCVC.DS_CONTR_VLD_CATG_DESC_TXT  AS DS_CONTR_VLD_CATG_DESC_TXT");
        sql.append("     ,DCV.DS_CONTR_VLD_NM              AS DS_CONTR_VLD_NM");
        sql.append("     ,DCV.DS_CONTR_VLD_DESC_TXT        AS DS_CONTR_VLD_DESC_TXT");
        sql.append("     ,TO_CHAR(TO_DATE(DCV.EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
        sql.append("     ,TO_CHAR(TO_DATE(DCV.EFF_TO_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_TO_DT");
        sql.append("     ,DCV.EZCANCELFLAG                 AS EZCANCELFLAG");
        sql.append(" FROM");
        sql.append("      DS_CONTR_VLD         DCV");
        sql.append("     ,DS_CONTR_VLD_CATG    DCVC");
        sql.append(" WHERE");
        sql.append("         DCV.EZCANCELFLAG          = '0'");
        sql.append("     AND DCV.GLBL_CMPY_CD          = '").append(scrnMsg.glblCmpyCd_H.getValue()).append("' ");
        sql.append("     AND DCVC.EZCANCELFLAG         = '0'");
        sql.append("     AND DCV.GLBL_CMPY_CD          = DCVC.GLBL_CMPY_CD");
        sql.append("     AND DCV.DS_CONTR_VLD_CATG_CD  = DCVC.DS_CONTR_VLD_CATG_CD");

        return sql.toString();
    }

    private static List<Object[]> getWhereList(NSAL0960BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Validation Category";
        h0[WLIST_OBJ_ID] = "DS_CONTR_VLD_CATG_DESC_TXT";
        h0[WLIST_OBJ_VALUE] = "";
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Validation Name";
        h1[WLIST_OBJ_ID] = "DS_CONTR_VLD_NM";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private static List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Validation Category";
        c0[CLIST_OBJ_ID] = "DS_CONTR_VLD_CATG_DESC_TXT";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_VLD_CATG_DESC_TXT_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Validation Name";
        c1[CLIST_OBJ_ID] = "DS_CONTR_VLD_NM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_VLD_NM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        c2[CLIST_DSP_OBJ_NM] = "Description";
        c2[CLIST_OBJ_ID] = "DS_CONTR_VLD_DESC_TXT";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_VLD_DESC_TXT_LENGTH);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c2);

        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
        c3[CLIST_DSP_OBJ_NM] = "Start Date";
        c3[CLIST_OBJ_ID] = "EFF_FROM_DT";
        c3[CLIST_OBJ_LENGTH] = new BigDecimal(DATE_LENGTH);
        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c3);

        Object[] c4 = new Object[ATTR_NUM_CLMN_LIST];
        c4[CLIST_DSP_OBJ_NM] = "End Date";
        c4[CLIST_OBJ_ID] = "EFF_TO_DT";
        c4[CLIST_OBJ_LENGTH] = new BigDecimal(DATE_LENGTH);
        c4[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c4);

        Object[] c5 = new Object[ATTR_NUM_CLMN_LIST];
        c5[CLIST_OBJ_ID] = "DS_CONTR_VLD_PK";
        columnList.add(c5);

        return columnList;
    }

    private static List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_CONTR_VLD_CATG_DESC_TXT";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_CONTR_VLD_NM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
}
