/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.COL_SIZE_20;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.COL_SIZE_62;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_0;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_1;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_2;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_3;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_4;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_5;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_6;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_7;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1420Scrn00_OpenWin_Tech extends S21CommonHandler {

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
        String globalCompanyCode = getGlobalCompanyCode();
        String salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT TM.TECH_TOC_CD, TM.TECH_NM, TM.GLBL_CMPY_CD, TM.EZCANCELFLAG");
        sb.append(" FROM TECH_MSTR TM, S21_PSN PSN");
        sb.append(" WHERE TM.GLBL_CMPY_CD = '").append(globalCompanyCode).append("'");
        sb.append(" AND TM.GLBL_CMPY_CD = PSN.GLBL_CMPY_CD");
        sb.append(" AND TM.TECH_TOC_CD = PSN.PSN_CD");
        sb.append(" AND PSN.EFF_FROM_DT <= '").append(salesDate).append("'");
        sb.append(" AND NVL(PSN.EFF_THRU_DT, '99991231') >= '").append(salesDate).append("'");
        sb.append(" AND PSN.DEL_FLG = 'N'");
        sb.append(" AND TM.EZCANCELFLAG = '0'");
        sb.append(" AND PSN.EZCANCELFLAG = '0'");

        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "Technician Search Popup";
        params[IDX_2] = sb.toString();

        // Where list
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Technician Code";
        whereArray1[IDX_1] = "TECH_TOC_CD";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Technician Name";
        whereArray2[IDX_1] = "TECH_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        // Column list
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Technician Code";
        columnArray1[IDX_1] = "TECH_TOC_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(COL_SIZE_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Technician Name";
        columnArray2[IDX_1] = "TECH_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(COL_SIZE_62);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        // Sort list
        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "TECH_TOC_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;
        params[IDX_6] = scrnMsg.R;

        setArgForSubScreen(params);
    }
}
