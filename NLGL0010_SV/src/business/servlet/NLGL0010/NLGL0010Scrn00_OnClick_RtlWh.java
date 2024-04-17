/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010;

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
 * 2017/05/26   CITS            T.Kikuhara      Create          RS#3168
 *</pre>
 */
public class NLGL0010Scrn00_OnClick_RtlWh extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;

        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);

    }

    private Object[] setPopupParameter(NLGL0010BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("OpenWin_Warehouse");
        scrnMsg.W.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "WH Popup test";
        params[2] = getSqlStr();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "WH Code";
        whereArray0[1] = "RTL_WH_CD";
        whereArray0[2] = scrnMsg.rtlWhCd_01.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "WH Name";
        whereArray1[1] = "RTL_WH_DESC_TXT";
        whereArray1[2] = scrnMsg.rtlWhNm_01.getValue();
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "WMS WH Code";
        whereArray2[1] = "WMS_WH_CD";
        whereArray2[2] = scrnMsg.whCd_02.getValue();
        whereArray2[3] = "Y";
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Owner";
        whereArray3[1] = "INVTY_OWNR_CD";
        whereArray3[2] = scrnMsg.invtyOwnrCd_01.getValue();
        whereArray3[3] = "Y";
        whereList.add(whereArray3);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "WH Code";
        columnArray0[1] = "RTL_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "WH Name";
        columnArray1[1] = "RTL_WH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "WH Owner";
        columnArray2[1] = "WH_OWNR_DESC_TXT";
        columnArray2[2] = BigDecimal.valueOf(50);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Owner";
        columnArray3[1] = "INVTY_OWNR_CD";
        columnArray3[2] = BigDecimal.valueOf(3);
        columnArray3[3] = "N";
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "WMS WH Code";
        columnArray4[1] = "WMS_WH_CD";
        columnArray4[2] = BigDecimal.valueOf(20);
        columnArray4[3] = "N";
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "WMS WH Name";
        columnArray5[1] = "WMS_WH_NM";
        columnArray5[2] = BigDecimal.valueOf(60);
        columnArray5[3] = "N";
        columnList.add(columnArray5);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "WMS_WH_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "RTL_WH_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;

        params[6] = scrnMsg.W;

        return params;
    }

    private String getSqlStr() {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("     RW.GLBL_CMPY_CD");
        sql.append("    ,RW.EZCANCELFLAG");
        sql.append("    ,RW.RTL_WH_CD");
        sql.append("    ,RW.RTL_WH_DESC_TXT");
        sql.append("    ,WO.WH_OWNR_DESC_TXT");
        sql.append("    ,RW.INVTY_OWNR_CD");
        sql.append("    ,WW.WH_CD        AS WMS_WH_CD");
        sql.append("    ,WW.WMS_DESC_NM  AS WMS_WH_NM");
        sql.append(" FROM");
        sql.append("     WMS_WH  WW");
        sql.append("    ,RTL_WH  RW");
        sql.append("    ,WH_OWNR WO");
        sql.append(" WHERE");
        sql.append("     WW.GLBL_CMPY_CD = 'ADB'");
        sql.append(" AND WW.EZCANCELFLAG = '0'");
        sql.append(" AND WW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD");
        sql.append(" AND WW.WH_CD        = RW.WMS_WH_CD");
        sql.append(" AND RW.EZCANCELFLAG = '0'");
        sql.append(" AND RW.GLBL_CMPY_CD = WO.GLBL_CMPY_CD");
        sql.append(" AND RW.WH_OWNR_CD   = WO.WH_OWNR_CD");
        sql.append(" AND WO.EZCANCELFLAG = '0'");
        sql.append(" UNION");
        sql.append(" SELECT DISTINCT");
        sql.append("     RW.GLBL_CMPY_CD");
        sql.append("    ,RW.EZCANCELFLAG");
        sql.append("    ,RW.RTL_WH_CD");
        sql.append("    ,RW.RTL_WH_DESC_TXT");
        sql.append("    ,WO.WH_OWNR_DESC_TXT");
        sql.append("    ,RW.INVTY_OWNR_CD");
        sql.append("    ,TL.WH_OWNR_CD        AS WMS_WH_CD");
        sql.append("    ,WO.WH_OWNR_DESC_TXT  AS WMS_WH_NM");
        sql.append(" FROM");
        sql.append("     TPL_LOC TL");
        sql.append("    ,WH_OWNR WO");
        sql.append("    ,RTL_WH  RW");
        sql.append(" WHERE");
        sql.append("     TL.GLBL_CMPY_CD = 'ADB'");
        sql.append(" AND TL.EZCANCELFLAG = '0'");
        sql.append(" AND TL.GLBL_CMPY_CD = WO.GLBL_CMPY_CD");
        sql.append(" AND TL.WH_OWNR_CD   = WO.WH_OWNR_CD");
        sql.append(" AND WO.EZCANCELFLAG = '0'");
        sql.append(" AND TL.GLBL_CMPY_CD = RW.GLBL_CMPY_CD");
        sql.append(" AND TL.TPL_LOC_CD   = RW.RTL_WH_CD");
        sql.append(" AND RW.EZCANCELFLAG = '0'");
        sql.append(" AND NOT EXISTS (SELECT");
        sql.append("                     1");
        sql.append("                 FROM");
        sql.append("                     RTL_WH RL");
        sql.append("                    ,WMS_WH WW");
        sql.append("                 WHERE");
        sql.append("                     RL.GLBL_CMPY_CD = TL.GLBL_CMPY_CD");
        sql.append("                 AND RL.RTL_WH_CD    = TL.TPL_LOC_CD");
        sql.append("                 AND RL.EZCANCELFLAG = '0'");
        sql.append("                 AND RL.GLBL_CMPY_CD = WW.GLBL_CMPY_CD");
        sql.append("                 AND RL.WMS_WH_CD    = WW.WH_CD");
        sql.append("                 AND WW.EZCANCELFLAG = '0'");
        sql.append("                )");

        return sql.toString();
    }
}
