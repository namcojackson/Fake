/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0300Scrn00_OpenWin_Model extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);

    }

    private Object[] setPopupParameter(NLCL0300BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("OpenWin_Model");
        scrnMsg.P.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Model Search Popup";
        params[2] = getSqlStr();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Model ID";
        whereArray0[1] = "T_MDL_ID";
        whereArray0[2] = scrnMsg.mdlId_H.getValue();
        whereArray0[3] = "N";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Model Name";
        whereArray1[1] = "T_MDL_NM";
        whereArray1[2] = "";
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Model ID";
        columnArray0[1] = "T_MDL_ID";
        columnArray0[2] = BigDecimal.valueOf(22);
        columnArray0[3] = "N";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Model Name";
        columnArray1[1] = "T_MDL_NM";
        columnArray1[2] = BigDecimal.valueOf(90);
        columnArray1[3] = "Y";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "T_MDL_NM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        params[6] = scrnMsg.W;

        return params;
    }

    private String getSqlStr() {
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
}
