/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   CUSA            Y.Aikawa        Create          N/A
 *</pre>
 */
public class NLBL0060Scrn00_OpenWin_RetailWarehouse_For_List extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
        int selectedRow = this.getButtonSelectNumber();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Warehouse Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  WH.EZCANCELFLAG        AS EZCANCELFLAG ");
        sb.append(", WH.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
        sb.append(", WH.RTL_WH_CD           AS RTL_WH_CD ");
        sb.append(", WH.RTL_WH_NM           AS RTL_WH_NM ");
        sb.append("FROM ");
        sb.append("  RTL_WH  WH ");
        sb.append("WHERE ");
        sb.append("    WH.EZCANCELFLAG   = '0' ");
        sb.append("AND WH.GLBL_CMPY_CD   = '").append(getGlobalCompanyCode()).append("' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Warehouse Code";
        whereArray0[1] = "RTL_WH_CD";
        whereArray0[2] = scrnMsg.A.no(selectedRow).whCd_A1.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Warehouse Name";
        whereArray1[1] = "RTL_WH_NM";
        whereArray1[2] = scrnMsg.A.no(selectedRow).locNm_A1.getValue();
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Warehouse Code";
        columnArray0[1] = "RTL_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Warehouse Name";
        columnArray1[1] = "RTL_WH_NM";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "RTL_WH_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}
