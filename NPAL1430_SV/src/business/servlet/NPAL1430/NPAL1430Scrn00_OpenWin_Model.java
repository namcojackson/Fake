/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1430;

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
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : Open Return to Model Search Popup(NMAL6050)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 *</pre>
 */

public class NPAL1430Scrn00_OpenWin_Model extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.R);

        String globalCompanyCode = getGlobalCompanyCode();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT M.T_MDL_ID, M.T_MDL_NM, M.T_GLBL_CMPY_CD AS GLBL_CMPY_CD, M.EZCANCELFLAG");
        sb.append(" FROM MDL_NM M");
        sb.append(" WHERE M.T_GLBL_CMPY_CD = '").append(globalCompanyCode).append("'");
        sb.append(" AND M.EZCANCELFLAG = '0'");
        Object[] params = new Object[7];

        params[0] = "";
        params[1] = "Model Search Popup";
        params[2] = sb.toString();

        // Where list
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Model Code";
        whereArray1[1] = "T_MDL_ID";
        whereArray1[2] = "";
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Model Name";
        whereArray2[1] = "T_MDL_NM";
        whereArray2[2] = scrnMsg.t_MdlNm.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[3] = whereList;

        // Column list
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Model Code";
        columnArray1[1] = "T_MDL_ID";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Model Name";
        columnArray2[1] = "T_MDL_NM";
        columnArray2[2] = BigDecimal.valueOf(50);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[4] = columnList;

        // Sort list
        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "T_MDL_NM";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;
        params[6] = scrnMsg.R;

        setArgForSubScreen(params);
    }
}
