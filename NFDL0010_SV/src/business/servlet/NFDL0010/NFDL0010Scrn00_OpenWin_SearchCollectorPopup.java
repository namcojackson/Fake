/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFDL0010.common.NFDL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2015/06/17   Hitachi         J.Kim           Update          QC#10328
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#10260
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 *</pre>
 */
public class NFDL0010Scrn00_OpenWin_SearchCollectorPopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        NFDL0010CommonLogic.initialize(this, scrnMsg);

        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);
    }

    private Object[] setPopupParameter(NFDL0010BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("OpenWin_SearchCollectorPopup");
        scrnMsg.P.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Collector Search Popup";

        StringBuilder sb = new StringBuilder();
        // START 2016/07/28 K.Kojima [QC#10260,DEL]
        // sb.append(" SELECT ");
        // sb.append("  GLBL_CMPY_CD");
        // sb.append(" ,EZCANCELFLAG");
        // sb.append(" ,CLT_PSN_CD CLT_PSN_CD");
        // sb.append(" ,CLT_PSN_NM CLT_PSN_NM");
        // sb.append(" FROM ");
        // sb.append("    (");
        // sb.append("    SELECT");
        // sb.append("        'ADB' GLBL_CMPY_CD");
        // sb.append("       ,'0'   EZCANCELFLAG");
        // sb.append("       ,CONNECT_BY_ROOT CLT_PSN_CD AS SRCH_USR_NM");
        // sb.append("       ,LEVEL");
        // sb.append("       ,CLT_PSN_CD AS CLT_PSN_CD");
        // sb.append("       ,CLT_PSN_NM AS CLT_PSN_NM");
        // sb.append("        FROM CLT_PTFO");
        // sb.append("        CONNECT BY  REL_CLT_PTFO_PK = PRIOR CLT_PTFO_PK");
        // sb.append("    )");
        // sb.append(" WHERE SRCH_USR_NM = '").append(getContextUserInfo().getUserId()).append("'");
        // END 2016/07/28 K.Kojima [QC#10260,DEL]
        // START 2016/07/28 K.Kojima [QC#10260,ADD]
        if (scrnMsg.cltDispTpCd_H.getValue().equals(CLT_DISP_TP.MYGROUP)) {
            sb.append(" SELECT ");
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            sb.append(" DISTINCT ");
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
            sb.append("      GLBL_CMPY_CD ");
            sb.append("     ,EZCANCELFLAG ");
            sb.append("     ,CLT_PSN_CD ");
            sb.append("     ,CLT_PSN_NM ");
            sb.append(" FROM ");
            sb.append("     ( ");
            sb.append("         SELECT ");
            sb.append("              CP.GLBL_CMPY_CD ");
            sb.append("             ,CP.EZCANCELFLAG ");
            sb.append("             ,CP.CLT_PSN_CD ");
            sb.append("             ,CP.CLT_PSN_NM ");
            sb.append("             ,CONNECT_BY_ROOT CLT_PSN_CD AS SRCH_USR_NM ");
            sb.append("         FROM ");
            sb.append("             CLT_PTFO CP ");
            sb.append("         WHERE ");
            sb.append("                 CP.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
            sb.append("             AND CP.EZCANCELFLAG = '0' ");
            sb.append("         CONNECT BY ");
            sb.append("             CP.REL_CLT_PTFO_PK = PRIOR CP.CLT_PTFO_PK ");
            sb.append("     ) ");
            sb.append(" WHERE ");
            sb.append("     SRCH_USR_NM = '").append(getContextUserInfo().getUserId()).append("' ");
        } else {
            sb.append(" SELECT ");
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            sb.append(" DISTINCT ");
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
            sb.append("      CP.GLBL_CMPY_CD ");
            sb.append("     ,CP.EZCANCELFLAG ");
            sb.append("     ,CP.CLT_PSN_CD ");
            sb.append("     ,CP.CLT_PSN_NM ");
            sb.append(" FROM ");
            sb.append("     CLT_PTFO CP ");
            sb.append(" WHERE ");
            sb.append("         CP.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
            sb.append("     AND CP.EZCANCELFLAG = '0' ");
        }
        // END 2016/07/28 K.Kojima [QC#10260,ADD]

        params[2] = sb.toString();
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Collector Code";
        whereArray1[1] = "CLT_PSN_CD";
        whereArray1[2] = scrnMsg.xxQueryFltrTxt_H1.getValue();
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Collector Name";
        whereArray2[1] = "CLT_PSN_NM";
        whereArray2[2] = "";
        whereArray2[3] = "Y";
        whereList.add(whereArray2);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Collector Code";
        columnArray1[1] = "CLT_PSN_CD";
        // START 2016/06/17 J.Kim [QC#10328,MOD]
        // columnArray1[2] = BigDecimal.valueOf(8);
        // END 2016/06/17 J.Kim [QC#10328,MOD]
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = "Y";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Collector Name";
        columnArray2[1] = "CLT_PSN_NM";
        columnArray2[2] = BigDecimal.valueOf(62);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "CLT_PSN_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        // START 2016/07/28 K.Kojima [QC#10260,ADD]
        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "CLT_PSN_NM";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);
        // END 2016/07/28 K.Kojima [QC#10260,ADD]

        params[5] = sortConditionList;

        params[6] = scrnMsg.P;

        return params;
    }
}
