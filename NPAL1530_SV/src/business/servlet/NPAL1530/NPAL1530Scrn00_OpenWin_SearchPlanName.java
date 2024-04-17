/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/16/2018   CITS            T.Tokutomi      Create          QC#21879
 *</pre>
 */
public class NPAL1530Scrn00_OpenWin_SearchPlanName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;

        String glblCmpyCd = getGlobalCompanyCode();

        Object[] params = setParam(scrnMsg, glblCmpyCd);
        setArgForSubScreen(params);
    }

    private static Object[] setParam(NPAL1530BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[7];
        scrnMsg.P.clear();

        params[0] = "";
        params[1] = "Plan Name Search Popup";
        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT DISTINCT");
        baseSql.append("  MI.GLBL_CMPY_CD");
        baseSql.append(" ,MI.EZCANCELFLAG");
        baseSql.append(" ,MI.MRP_PLN_NM");
        baseSql.append(" ,RW.RTL_WH_CD");
        baseSql.append(" ,RW.RTL_WH_DESC_TXT");
        baseSql.append(" ,LT.LOC_TP_CD");
        baseSql.append(" ,LT.LOC_TP_DESC_TXT");
        baseSql.append(" ,SP.PSN_CD");
        baseSql.append(" ,SP.PSN_FIRST_NM || ' ' || SP.PSN_LAST_NM AS MGR_NM");
        baseSql.append(" FROM ");
        baseSql.append(" MRP_INFO MI");
        baseSql.append(" ,RTL_WH RW");
        baseSql.append(" ,LOC_TP LT");
        baseSql.append(" ,S21_PSN SP");
        baseSql.append(" WHERE ");
        baseSql.append(" MI.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append(" AND MI.EZCANCELFLAG = '0'");
        baseSql.append(" AND MI.GLBL_CMPY_CD = RW.GLBL_CMPY_CD");
        baseSql.append(" AND MI.RTL_WH_CD = RW.RTL_WH_CD");
        baseSql.append(" AND RW.EZCANCELFLAG = '0'");
        baseSql.append(" AND RW.GLBL_CMPY_CD = LT.GLBL_CMPY_CD");
        baseSql.append(" AND RW.LOC_TP_CD = LT.LOC_TP_CD");
        baseSql.append(" AND LT.EZCANCELFLAG = '0'");
        baseSql.append(" AND RW.GLBL_CMPY_CD = SP.GLBL_CMPY_CD(+)");
        baseSql.append(" AND RW.WH_MGR_PSN_CD = SP.PSN_CD(+)");
        baseSql.append(" AND SP.EZCANCELFLAG(+) = '0'");
        String sql = baseSql.toString();
        params[2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Plan Name";
        whereArray1[1] = "MRP_PLN_NM";
        whereArray1[2] = scrnMsg.mrpPlnNm.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Warehouse Code";
        whereArray2[1] = "RTL_WH_CD";
        whereArray2[2] = scrnMsg.rtlWhCd.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Warehouse Type Name";
        whereArray3[1] = "LOC_TP_DESC_TXT";
        whereArray3[2] = "";
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Manager Name";
        whereArray4[1] = "MGR_NM";
        whereArray4[2] = "";
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Plan Name";
        columnArray1[1] = "MRP_PLN_NM";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Warehouse Code";
        columnArray2[1] = "RTL_WH_CD";
        columnArray2[2] = BigDecimal.valueOf(12);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Warehouse Name";
        columnArray3[1] = "RTL_WH_DESC_TXT";
        columnArray3[2] = BigDecimal.valueOf(20);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Manager Name";
        columnArray4[1] = "MGR_NM";
        columnArray4[2] = BigDecimal.valueOf(25);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "MRP_PLN_NM";
        sortConditionArray1[1] = "ASC";

        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "RTL_WH_CD";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[2];
        sortConditionArray3[0] = "MGR_NM";
        sortConditionArray3[1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        params[5] = sortConditionList;
        params[6] = scrnMsg.P;

        return params;
    }
}
