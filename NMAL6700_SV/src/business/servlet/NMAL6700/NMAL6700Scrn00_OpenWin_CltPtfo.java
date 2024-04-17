/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
//import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Create          N/A
 * 2018/07/13   Fujitsu         M.Ishii         Update          QC#26613
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_CltPtfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Collection Portfolio Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("PTFO.CLT_PTFO_CD AS CLT_PTFO_CD, ");
        // 2018/07/13 QC#26613 Mod Start
//        sb.append("PTFO.CLT_PTFO_NM  AS CLT_PTFO_NM, ");
        sb.append("PTFO.CLT_PTFO_DESC_TXT  AS CLT_PTFO_DESC_TXT, ");
        // 2018/07/13 QC#26613 Mod End
        sb.append("PTFO.CLT_PSN_CD  AS CLT_PSN_CD, ");
        sb.append("PTFO.CLT_PSN_NM  AS CLT_PSN_NM, ");
        sb.append("PTFO.CLT_PTFO_PK  AS CLT_PTFO_PK, ");
        sb.append("PTFO.GLBL_CMPY_CD  AS GLBL_CMPY_CD, ");
        sb.append("PTFO.EZCANCELFLAG  AS EZCANCELFLAG ");
        sb.append("FROM ");
        sb.append("CLT_PTFO PTFO ");
        sb.append("WHERE ");
        sb.append("PTFO.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("AND PTFO.CLT_PTFO_STS_FLG = 'Y' ");
        sb.append("AND PTFO.EZCANCELFLAG = '0' ");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Collection Prtf Code";
        whereArray0[1] = "CLT_PTFO_CD";
        whereArray0[2] = scrnMsg.cltPtfoCd_U1.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        //test
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Collection Prtf Name";
        whereArray1[1] = "CLT_PTFO_DESC_TXT";
        
        whereArray1[2] = scrnMsg.cltPtfoDescTxt_U1.getValue();
        
        whereArray1[3] = "Y";
        whereList.add(whereArray1);
        //test

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Collection Prtf Code";
        columnArray0[1] = "CLT_PTFO_CD";
        columnArray0[2] = BigDecimal.valueOf(12);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Collection Prtf Name";
        // 2018/07/13 QC#26613 Mod Start
//        columnArray1[1] = "CLT_PTFO_NM";
//      columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[1] = "CLT_PTFO_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        // 2018/07/13 QC#26613 Mod End
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Collection Psn Code";
        columnArray2[1] = "CLT_PSN_CD";
        columnArray2[2] = BigDecimal.valueOf(12);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Collection Psn Name";
        columnArray3[1] = "CLT_PSN_NM";
        // 2018/07/13 QC#26613 Mod Start
//        columnArray3[2] = BigDecimal.valueOf(150);
        columnArray3[2] = BigDecimal.valueOf(30);
        // 2018/07/13 QC#26613 Mod End
        columnArray3[3] = "N";
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Collection Prtf Pk";
        columnArray4[1] = "CLT_PTFO_PK";
        columnArray4[2] = BigDecimal.valueOf(28);
        columnArray4[3] = "N";
        columnList.add(columnArray4);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "CLT_PTFO_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        scrnMsg.L.clear();

        params[6] = scrnMsg.L;

        setArgForSubScreen(params);

    }
}
