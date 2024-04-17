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
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 *</pre>
 */
public class NMAL6730Scrn00_OpenWin_Template extends S21CommonHandler {

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
        params[1] = "Customer Account Receivable Template Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("TMPL.GLBL_CMPY_CD ");
        sb.append(", TMPL.EZCANCELFLAG ");
        sb.append(", TMPL.DS_CUST_AR_TMPL_PK ");
        sb.append(", TMPL.DS_CUST_AR_TMPL_NM ");
        sb.append(", TMPL.DS_DEF_TMPL_FLG ");
        sb.append("FROM ");
        sb.append("DS_CUST_AR_TMPL TMPL ");
        sb.append("WHERE ");
        sb.append("TMPL.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("AND TMPL.EZCANCELFLAG = '0' ");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Template Name";
        whereArray0[1] = "DS_CUST_AR_TMPL_NM";
        whereArray0[2] = scrnMsg.dsCustArTmplNm_F1.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Template Primary Key";
        columnArray0[1] = "DS_CUST_AR_TMPL_PK";
        columnArray0[2] = BigDecimal.valueOf(28);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Template Name";
        columnArray1[1] = "DS_CUST_AR_TMPL_NM";
        columnArray1[2] = BigDecimal.valueOf(28);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Default Flag";
        columnArray2[1] = "DS_DEF_TMPL_FLG";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_CUST_AR_TMPL_PK";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        scrnMsg.L.clear();

        params[6] = scrnMsg.L;

        setArgForSubScreen(params);

    }
}
