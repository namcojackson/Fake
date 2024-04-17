/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/26   Hitachi         K.Kojima        Create          QC#23630
 * 2018/06/05   Hitachi         K.Kojima        Update          QC#21974
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_FreeCopyRollOverHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.xxScrEventNm.setValue("OpenWin_Serial");
        Object[] prm = new Object[7];
        prm[0] = "";
        prm[1] = "Free Copy / Roll Over History";
        prm[2] = getSelectSQL(scrnMsg);
        prm[3] = getSearchConditionSetting(scrnMsg);
        prm[4] = getDisplayColumnSetting(scrnMsg);
        prm[5] = getSortSetting(scrnMsg);
        prm[6] = scrnMsg.R;
        setArgForSubScreen(prm);
    }

    private List<Object> getSearchConditionSetting(NSAL0300BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();
        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NSAL0300BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Invoice#", "SVC_INV_NUM", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {"Invoice Date", "INV_DT", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {"From Date", "BLLG_SCHD_FROM_DT", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {"Thru Date", "BLLG_SCHD_THRU_DT", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {"Free Copy", "ORG_FREE_COPY", new BigDecimal("12"), ZYPConstant.FLG_OFF_N };
        Object[] colObj6 = {"Used Free Copy", "USG_FREE_COPY_CNT", new BigDecimal("12"), ZYPConstant.FLG_OFF_N };
        Object[] colObj7 = {"Roll Over %", "ROLL_OVER_RATIO", new BigDecimal("12"), ZYPConstant.FLG_OFF_N };
        Object[] colObj8 = {"Roll Over Count", "ROLL_OVER_CNT", new BigDecimal("12"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);
        colList.add(colObj6);
        colList.add(colObj7);
        colList.add(colObj8);
        return colList;
    }

    private List<Object> getSortSetting(NSAL0300BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"BLLG_SCHD_FROM_DT_SORT", "ASC" };
        Object[] sortObj2 = {"BLLG_SCHD_THRU_DT_SORT", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);
        return sortList;
    }

    private String getSelectSQL(NSAL0300BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        // START 2018/06/05 K.Kojima [QC#21974,MOD]
        // sb.append("     C.SVC_INV_NUM                                                                AS SVC_INV_NUM            ");
        // sb.append("    ,TO_CHAR(TO_DATE(D.INV_DT,'YYYYMMDD'),'MM/DD/YYYY')                           AS INV_DT                 ");
        sb.append("     NVL(C.SVC_INV_NUM, E.SVC_INV_NUM)                                            AS SVC_INV_NUM            ");
        sb.append("    ,TO_CHAR(TO_DATE(NVL(D.INV_DT, E.INV_DT),'YYYYMMDD'),'MM/DD/YYYY')            AS INV_DT                 ");
        // END 2018/06/05 K.Kojima [QC#21974,MOD]
        sb.append("    ,TO_CHAR(TO_DATE(B.BLLG_SCHD_FROM_DT,'YYYYMMDD'),'MM/DD/YYYY')                AS BLLG_SCHD_FROM_DT      ");
        sb.append("    ,TO_CHAR(TO_DATE(B.BLLG_SCHD_THRU_DT,'YYYYMMDD'),'MM/DD/YYYY')                AS BLLG_SCHD_THRU_DT      ");
        sb.append("    ,TO_CHAR(NVL(B.FREE_COPY_CNT,0)+NVL(B.USG_FREE_COPY_CNT,0),'999,999,999,999') AS ORG_FREE_COPY          ");
        sb.append("    ,TO_CHAR(B.USG_FREE_COPY_CNT,'999,999,999,999')                               AS USG_FREE_COPY_CNT      ");
        sb.append("    ,TO_CHAR(B.ROLL_OVER_RATIO,'999,999,999,999')                                 AS ROLL_OVER_RATIO        ");
        sb.append("    ,TO_CHAR(B.ROLL_OVER_CNT,'999,999,999,999')                                   AS ROLL_OVER_CNT          ");
        sb.append("    ,B.BLLG_SCHD_FROM_DT                                                          AS BLLG_SCHD_FROM_DT_SORT ");
        sb.append("    ,B.BLLG_SCHD_THRU_DT                                                          AS BLLG_SCHD_THRU_DT_SORT ");
        sb.append("    ,B.GLBL_CMPY_CD                                                                                         ");
        sb.append("    ,B.EZCANCELFLAG                                                                                         ");
        sb.append("FROM                                                                                                        ");
        sb.append("     DS_CONTR_BLLG_MTR      A ");
        sb.append("    ,DS_CONTR_BLLG_MTR_USED B ");
        sb.append("    ,SVC_INV_LINE_MTR       C ");
        sb.append("    ,SVC_INV                D ");
        // START 2018/06/05 K.Kojima [QC#21974,ADD]
        sb.append("    ,DS_CONTR_BLLG_SCHD     E ");
        // END 2018/06/05 K.Kojima [QC#21974,ADD]
        sb.append("WHERE                                                                                                       ");
        sb.append("    A.GLBL_CMPY_CD         = '#GLBL_CMPY_CD#' ");
        sb.append("AND A.DS_CONTR_BLLG_MTR_PK = #DS_CONTR_BLLG_MTR_PK# ");
        sb.append("AND A.EZCANCELFLAG         = '0' ");
        sb.append("AND A.GLBL_CMPY_CD         = B.GLBL_CMPY_CD ");
        sb.append("AND A.DS_CONTR_BLLG_MTR_PK = B.DS_CONTR_BLLG_MTR_PK ");
        sb.append("AND B.EZCANCELFLAG         = '0' ");
        sb.append("AND B.GLBL_CMPY_CD         = C.GLBL_CMPY_CD(+) ");
        sb.append("AND B.SVC_INV_LINE_MTR_PK  = C.SVC_INV_LINE_MTR_PK(+) ");
        sb.append("AND C.EZCANCELFLAG(+)      = '0' ");
        sb.append("AND C.GLBL_CMPY_CD         = D.GLBL_CMPY_CD(+) ");
        sb.append("AND C.SVC_INV_NUM          = D.SVC_INV_NUM(+) ");
        sb.append("AND D.EZCANCELFLAG(+)      = '0' ");
        // START 2018/06/05 K.Kojima [QC#21974,ADD]
        sb.append("AND B.GLBL_CMPY_CD          = E.GLBL_CMPY_CD(+) ");
        sb.append("AND B.DS_CONTR_BLLG_SCHD_PK = E.DS_CONTR_BLLG_SCHD_PK(+) ");
        sb.append("AND E.EZCANCELFLAG(+)       = '0' ");
        // END 2018/06/05 K.Kojima [QC#21974,ADD]
        sb.append("AND EXISTS( ");
        sb.append("    SELECT ");
        sb.append("        1 ");
        sb.append("    FROM ");
        sb.append("         DS_CONTR_BLLG_SCHD E ");
        sb.append("        ,DS_CONTR_PRC_EFF   F ");
        sb.append("    WHERE ");
        sb.append("            B.GLBL_CMPY_CD          = E.GLBL_CMPY_CD ");
        sb.append("        AND B.DS_CONTR_BLLG_SCHD_PK = E.DS_CONTR_BLLG_SCHD_PK ");
        sb.append("        AND E.EZCANCELFLAG          = '0' ");
        sb.append("        AND E.GLBL_CMPY_CD          = F.GLBL_CMPY_CD ");
        sb.append("        AND E.DS_CONTR_PRC_EFF_PK   = F.DS_CONTR_PRC_EFF_PK ");
        sb.append("        AND F.EZCANCELFLAG          = '0' ");
        sb.append("    )                                                                                                       ");

        int selectIdx = getButtonSelectNumber();
        BigDecimal dsContrBllgMtrPk = scrnMsg.B.no(selectIdx).dsContrBllgMtrPk_B.getValue();

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());
        sql = sql.replaceAll("#DS_CONTR_BLLG_MTR_PK#", dsContrBllgMtrPk.toString());
        return sql;
    }

}
