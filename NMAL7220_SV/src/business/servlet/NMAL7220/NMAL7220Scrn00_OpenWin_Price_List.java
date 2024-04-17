/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7220;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import static business.servlet.NMAL7220.constant.NMAL7220Constant.CMN_PRM_WHERE_NUM;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.CMN_SORT_CONDITION_NUM;

/**
 *<pre>
 * NMAL7220Scrn00_OpenWin_Price_List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220Scrn00_OpenWin_Price_List extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.R.clear();

        // 0:Suffix
        String suffixP0 = "";
        // 1:Window Title
        String scrnNmP1 = "";
        // 2:Select Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Where List
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column List
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort Condition List
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(popResultParams)
        Object[] param = new Object[7];

        // Data Set
        suffixP0 = null;

        scrnNmP1 = "Price List(Equipment)";

        tblNmP2.append(" SELECT");
        tblNmP2.append(" PC.GLBL_CMPY_CD");
        tblNmP2.append(",PC.EZCANCELFLAG");
        tblNmP2.append(",PC.PRC_CATG_CD");
        tblNmP2.append(",PC.PRC_CATG_NM");
        tblNmP2.append(",TO_CHAR(TO_DATE(PC.EFF_FROM_DT, '").append(ZYPDateUtil.TYPE_YYYYMMDD).append("'), '").append(ZYPDateUtil.getDateFormatString(true)).append("') EFF_FROM_DT ");
        tblNmP2.append(",TO_CHAR(TO_DATE(PC.EFF_THRU_DT, '").append(ZYPDateUtil.TYPE_YYYYMMDD).append("'), '").append(ZYPDateUtil.getDateFormatString(true)).append("') EFF_THRU_DT ");
        tblNmP2.append(",PC.ACTV_FLG");
        tblNmP2.append(" FROM PRC_CATG PC");
        tblNmP2.append(",PRC_LIST_TP PLT");
        tblNmP2.append(",PRC_LIST_STRU_TP PLST");
        tblNmP2.append(" WHERE");
        tblNmP2.append(" PC.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("'");
        tblNmP2.append(" AND PC.EZCANCELFLAG    = '").append("0").append("'");
        tblNmP2.append(" AND PC.PRC_LIST_TP_CD  = PLT.PRC_LIST_TP_CD");
        tblNmP2.append(" AND PC.GLBL_CMPY_CD    = PLT.GLBL_CMPY_CD");
        tblNmP2.append(" AND PC.EZCANCELFLAG    = PLT.EZCANCELFLAG");
        tblNmP2.append(" AND PLT.PRC_LIST_STRU_TP_CD = PLST.PRC_LIST_STRU_TP_CD");
        tblNmP2.append(" AND PLT.GLBL_CMPY_CD   = PLST.GLBL_CMPY_CD");
        tblNmP2.append(" AND PLT.EZCANCELFLAG   = PLST.EZCANCELFLAG");
        tblNmP2.append(" AND PLST.PRC_LIST_STRU_TP_CD = '").append(PRC_LIST_STRU_TP.EQUIPMENT).append("'");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "Price List ID";
        whereArray0[1] = "PRC_CATG_CD";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
        whereArray1[0] = "Price List Name";
        whereArray1[1] = "PRC_CATG_NM";
        whereArray1[2] = scrnMsg.A.no(idx).prcCatgNm_A1.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
        whereArray2[0] = "Active";
        whereArray2[1] = "ACTV_FLG";
        whereArray2[2] = null;
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray2);

        Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
        whereArray3[0] = "Effective Date From";
        whereArray3[1] = "EFF_FROM_DT";
        whereArray3[2] = null;
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray3);

        Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
        whereArray4[0] = "Effective Date To";
        whereArray4[1] = "EFF_THRU_DT";
        whereArray4[2] = null;
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray4);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "Price List ID";
        columnArray0[1] = "PRC_CATG_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "Price List Name";
        columnArray1[1] = "PRC_CATG_NM";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnListP4.add(columnArray1);

        Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray2[0] = "Active";
        columnArray2[1] = "ACTV_FLG";
        columnArray2[2] = BigDecimal.valueOf(5);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray2);

        Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray3[0] = "Effective Date From";
        columnArray3[1] = "EFF_FROM_DT";
        columnArray3[2] = BigDecimal.valueOf(12);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray3);

        Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray4[0] = "Effective Date To";
        columnArray4[1] = "EFF_THRU_DT";
        columnArray4[2] = BigDecimal.valueOf(12);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray4);

        Object[] sortConditionArray0 = new Object[CMN_SORT_CONDITION_NUM];
        sortConditionArray0[0] = "PRC_CATG_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionListP5.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[CMN_SORT_CONDITION_NUM];
        sortConditionArray1[0] = "PRC_CATG_NM";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[CMN_SORT_CONDITION_NUM];
        sortConditionArray2[0] = "EFF_FROM_DT";
        sortConditionArray2[1] = "ASC";
        sortConditionListP5.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[CMN_SORT_CONDITION_NUM];
        sortConditionArray3[0] = "EFF_THRU_DT";
        sortConditionArray3[1] = "ASC";
        sortConditionListP5.add(sortConditionArray3);

        // Table Information
        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        setArgForSubScreen(param);
    }
}
