/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_TECH_POPUP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 10/18/2016  CITS       T.Wada               Update          QC#14002
 *</pre>
 */
public class NPAL1410Scrn00_OpenWin_Tech extends S21CommonHandler {

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

        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        scrnMsg.eventNm.setValue(EVENT_NAME_TECH_POPUP);

        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[7];

        params[0] = "";
        params[1] = "Technician Search Popup";
        params[2] = sb.toString();

        // Where list
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Technician Code";
        whereArray1[1] = "TECH_TOC_CD";
        // QC#14002
//        whereArray1[2] = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.ownrTechTocCd_H1)) {
            whereArray1[2] = scrnMsg.ownrTechTocCd_H1.getValue();
        }
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Technician Name";
        whereArray2[1] = "TECH_NM";
        // QC#14002
//        whereArray2[2] = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.techNm_H1)) {
            whereArray2[2] = scrnMsg.techNm_H1.getValue();
        }
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[3] = whereList;

        // Column list
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Technician Code";
        columnArray1[1] = "TECH_TOC_CD";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Technician Name";
        columnArray2[1] = "TECH_NM";
        columnArray2[2] = BigDecimal.valueOf(62);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[4] = columnList;

        // Sort list
        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "TECH_TOC_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;
        params[6] = scrnMsg.R;

        setArgForSubScreen(params);
    }
}
