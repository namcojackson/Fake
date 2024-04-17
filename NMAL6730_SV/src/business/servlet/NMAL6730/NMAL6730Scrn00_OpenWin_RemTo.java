/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6730.NMAL6730CMsg;
//import business.servlet.NMAL6730.constant.NMAL6730Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6730Scrn00_OpenWin_RemTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        //NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        //bizMsg.setBusinessID("NMAL6730");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Rem To Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("R.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append(", R.EZCANCELFLAG AS EZCANCELFLAG ");
        sb.append(", R.REM_ID AS REM_ID ");
        sb.append(", R.LOC_NM AS LOC_NM ");
        sb.append(", R.FIRST_LINE_ADDR AS FIRST_LINE_ADDR ");
        sb.append(", R.CTY_ADDR AS CTY_ADDR ");
        sb.append(", S.ST_NM AS ST_NM ");
        sb.append("FROM ");
        sb.append("REM_TO R, ST S ");
        sb.append("WHERE ");
        sb.append("R.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("AND R.EZCANCELFLAG = '0' ");
        sb.append("AND R.GLBL_CMPY_CD = S.GLBL_CMPY_CD ");
        sb.append("AND R.ST_CD = S.ST_CD ");
        sb.append("AND S.EZCANCELFLAG = '0' ");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Rem ID";
        whereArray0[1] = "REM_ID";
        whereArray0[2] = scrnMsg.remId_F1.getValue();
        whereArray0[3] = "N";
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Rem ID";
        columnArray0[1] = "REM_ID";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Location Name";
        columnArray1[1] = "LOC_NM";
        columnArray1[2] = BigDecimal.valueOf(60);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "First Line Address";
        columnArray2[1] = "FIRST_LINE_ADDR";
        columnArray2[2] = BigDecimal.valueOf(60);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "City Name";
        columnArray3[1] = "CTY_ADDR";
        columnArray3[2] = BigDecimal.valueOf(25);
        columnArray3[3] = "N";
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "State";
        columnArray4[1] = "ST_NM";
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = "N";
        columnList.add(columnArray4);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "REM_ID";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        scrnMsg.L.clear();

        params[6] = scrnMsg.L;

        setArgForSubScreen(params);

    }
}
