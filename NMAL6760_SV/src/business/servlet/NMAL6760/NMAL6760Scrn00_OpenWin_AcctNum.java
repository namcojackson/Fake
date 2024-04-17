/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6760Scrn00_OpenWin_AcctNum
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         q09083          Create          N/A
 *</pre>
 */
public class NMAL6760Scrn00_OpenWin_AcctNum extends S21CommonHandler {

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

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Account Lookup Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("CUST.SELL_TO_CUST_CD AS DS_ACCT_NUM, ");
        sb.append("CUST.DS_ACCT_NM      AS DS_ACCT_NM, ");
        sb.append("CUST.GLBL_CMPY_CD    AS GLBL_CMPY_CD, ");
        sb.append("CUST.EZCANCELFLAG    AS EZCANCELFLAG ");
        sb.append("FROM ");
        sb.append("SELL_TO_CUST CUST ");
        sb.append("WHERE ");
        sb.append("CUST.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("AND CUST.EZCANCELFLAG = '0' ");
        sb.append("UNION ALL ");
        sb.append("SELECT ");
        sb.append("PROS.DS_ACCT_NUM   AS DS_ACCT_NUM, ");
        sb.append("PROS.DS_ACCT_NM    AS DS_ACCT_NM, ");
        sb.append("PROS.GLBL_CMPY_CD  AS GLBL_CMPY_CD, ");
        sb.append("PROS.EZCANCELFLAG  AS EZCANCELFLAG ");
        sb.append("FROM ");
        sb.append("DS_ACCT_PROS PROS ");
        sb.append("WHERE ");
        sb.append("PROS.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("AND PROS.EZCANCELFLAG = '0' ");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Account Number";
        whereArray0[1] = "DS_ACCT_NUM";
        whereArray0[2] = scrnMsg.dsAcctNum_01.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Account Number";
        columnArray0[1] = "DS_ACCT_NUM";
        columnArray0[2] = BigDecimal.valueOf(28);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Account Name";
        columnArray1[1] = "DS_ACCT_NM";
        columnArray1[2] = BigDecimal.valueOf(60);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_ACCT_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        scrnMsg.I.clear();

        params[6] = scrnMsg.I;

        setArgForSubScreen(params);
    }
}
