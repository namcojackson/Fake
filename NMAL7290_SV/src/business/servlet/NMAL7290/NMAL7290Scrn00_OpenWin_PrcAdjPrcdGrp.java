/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290Scrn00_OpenWin_PrcAdjPrcdGrp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290Scrn00_OpenWin_PrcAdjPrcdGrp extends S21CommonHandler {

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

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        Object[] params = new Object[7];
        params[0] = "R";
        params[1] = "Price Adjustment Precedence Group Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  P.GLBL_CMPY_CD ");
        sb.append(", P.EZCANCELFLAG ");
        //sb.append(", TO_CHAR(P.PRC_RULE_PRCD_GRP_NUM) PRC_RULE_PRCD_GRP_NUM ");
        sb.append(", TO_CHAR(P.PRC_RULE_PRCD_PK) PRC_RULE_PRCD_PK ");
        sb.append(", P.PRC_RULE_PRCD_GRP_NM ");
        sb.append(", TO_CHAR(D.PRC_RULE_HDR_PK) PRC_RULE_HDR_PK ");
        sb.append(", H.PRC_RULE_NM ");
        //sb.append(", P.PRC_RULE_PRCD_GRP_NUM  PRCD_GRP_NUM_SORT ");
        sb.append(", P.PRC_RULE_PRCD_PK  PRCD_GRP_NUM_SORT ");
        sb.append("FROM ");
        sb.append("  PRC_RULE_PRCD P ");
        sb.append(", PRC_RULE_PRCD_DTL D ");
        sb.append(", PRC_RULE_HDR H ");
        sb.append("WHERE ");
        sb.append("    P.GLBL_CMPY_CD = '" + getGlobalCompanyCode() + "' ");
        sb.append("AND P.EZCANCELFLAG = '0' ");
        sb.append("AND P.GLBL_CMPY_CD = D.GLBL_CMPY_CD ");
        sb.append("AND P.EZCANCELFLAG = D.EZCANCELFLAG ");
        sb.append("AND P.PRC_RULE_PRCD_PK = D.PRC_RULE_PRCD_PK ");
        sb.append("AND D.GLBL_CMPY_CD = H.GLBL_CMPY_CD ");
        sb.append("AND D.EZCANCELFLAG = H.EZCANCELFLAG ");
        sb.append("AND D.PRC_RULE_HDR_PK = H.PRC_RULE_HDR_PK ");
        params[2] = sb.toString();

        String prcRulePrcdGrpNum = null;
        String prcRulePrcdGrpNm = null;
        //if (ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdGrpNum)) {
        //    prcRulePrcdGrpNum = scrnMsg.prcRulePrcdGrpNum.getValue().toString();
        //}
        if (ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdPk)) {
            prcRulePrcdGrpNum = scrnMsg.prcRulePrcdPk.getValue().toString();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdGrpNm)) {
            prcRulePrcdGrpNm = scrnMsg.prcRulePrcdGrpNm.getValue();
        }

        List<Object[]> whereList = new ArrayList<Object[]>();
        //Object[] whereArray0 = {"Prc Adj Prcd Grp #", "PRC_RULE_PRCD_GRP_NUM", prcRulePrcdGrpNum, ZYPConstant.FLG_OFF_N };
        Object[] whereArray0 = {"Prc Adj Prcd Grp #", "PRC_RULE_PRCD_PK", prcRulePrcdGrpNum, ZYPConstant.FLG_OFF_N };
        Object[] whereArray1 = {"Prc Adj Prcd Grp Nm", "PRC_RULE_PRCD_GRP_NM", prcRulePrcdGrpNm, ZYPConstant.FLG_ON_Y };
        Object[] whereArray2 = {"ID", "PRC_RULE_HDR_PK", null, ZYPConstant.FLG_OFF_N };
        Object[] whereArray3 = {"Prc Rule/Tbl Nm", "PRC_RULE_NM", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereArray0);
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        //Object[] columnArray0 = {"Prc Adj Prcd Grp #", "PRC_RULE_PRCD_GRP_NUM", BigDecimal.valueOf(15), ZYPConstant.FLG_ON_Y };
        Object[] columnArray0 = {"Prc Adj Prcd Grp #", "PRC_RULE_PRCD_PK", BigDecimal.valueOf(28), ZYPConstant.FLG_ON_Y };
        Object[] columnArray1 = {"Prc Adj Prcd Grp Nm", "PRC_RULE_PRCD_GRP_NM", BigDecimal.valueOf(33), ZYPConstant.FLG_OFF_N };
        Object[] columnArray2 = {"ID", "PRC_RULE_HDR_PK", BigDecimal.valueOf(10), ZYPConstant.FLG_OFF_N };
        Object[] columnArray3 = {"Prc Rule/Tbl Nm", "PRC_RULE_NM", BigDecimal.valueOf(33), ZYPConstant.FLG_OFF_N };
        columnList.add(columnArray0);
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = {"PRCD_GRP_NUM_SORT", "ASC" };
        Object[] sortConditionArray1 = {"PRC_RULE_HDR_PK", "ASC" };
        sortConditionList.add(sortConditionArray0);
        sortConditionList.add(sortConditionArray1);
        params[5] = sortConditionList;

        scrnMsg.R.clear();
        params[6] = scrnMsg.R;

        setArgForSubScreen(params);
    }
}
