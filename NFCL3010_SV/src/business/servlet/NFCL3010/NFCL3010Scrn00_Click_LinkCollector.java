/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFCL3010Scrn00_Click_LinkCollector extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);
    }

    private Object[] setPopupParameter(NFCL3010BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("Click_LinkCollector");
        scrnMsg.P.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Credit Collector Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ");
        sb.append("  PTFO.GLBL_CMPY_CD");
        sb.append(" ,PTFO.EZCANCELFLAG");
        sb.append(" ,PTFO.CLT_PSN_CD");
        sb.append(" ,UPPER(PTFO.CLT_PSN_NM) CLT_PSN_NM");
        sb.append(" ,PTFO.CLT_PTFO_CD");
        sb.append(" ,PTFO.CLT_PTFO_NM CLT_PTFO_NM");
        sb.append(" FROM ");
        sb.append(" CLT_PTFO    PTFO");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Portfolio Code";
        whereArray1[1] = "CLT_PTFO_CD";
        whereArray1[2] = "";
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Portfolio Name";
        whereArray2[1] = "CLT_PTFO_NM";
        whereArray2[2] = "";
        whereArray2[3] = "Y";
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Collector Code";
        whereArray3[1] = "CLT_PSN_CD";
        whereArray3[2] = scrnMsg.psnCd_H.getValue();
        whereArray3[3] = "Y";
        whereList.add(whereArray3);

        Object[] whereArray4= new Object[4];
        whereArray4[0] = "Collector Name";
        whereArray4[1] = "CLT_PSN_NM";
        whereArray4[2] = "";
        whereArray4[3] = "Y";
        whereList.add(whereArray4);


        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Portfolio Code";
        columnArray1[1] = "CLT_PTFO_CD";
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Portfolio Name";
        columnArray2[1] = "CLT_PTFO_NM";
        columnArray2[2] = BigDecimal.valueOf(35);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Collector Code";
        columnArray3[1] = "CLT_PSN_CD";
        columnArray3[2] = BigDecimal.valueOf(10);
        columnArray3[3] = "Y";
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Collector Name";
        columnArray4[1] = "CLT_PSN_NM";
        columnArray4[2] = BigDecimal.valueOf(35);
        columnArray4[3] = "N";
        columnList.add(columnArray4);


        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "CLT_PTFO_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);
        params[5] = sortConditionList;

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "CLT_PTFO_NM";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);
        params[5] = sortConditionList;

        Object[] sortConditionArray3 = new Object[2];
        sortConditionArray3[0] = "CLT_PSN_CD";
        sortConditionArray3[1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        Object[] sortConditionArray4 = new Object[2];
        sortConditionArray4[0] = "CLT_PSN_NM";
        sortConditionArray4[1] = "ASC";
        sortConditionList.add(sortConditionArray4);

        params[6] = scrnMsg.P;

        return params;
    }
}
