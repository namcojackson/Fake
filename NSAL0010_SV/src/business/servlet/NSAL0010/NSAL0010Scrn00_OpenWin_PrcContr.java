/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/05   Hitachi         T.Tomita        Create          QC#10464
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_PrcContr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CommonLogic.clearPopupParameter(scrnMsg);

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = getParamNWAL1130ForAssnProgram(scrnMsg);
        setArgForSubScreen(params);
    }

    private static Object[] getParamNWAL1130ForAssnProgram(NSAL0010BMsg scrnMsg) {

        String dateFormat = ZYPDateUtil.getDateFormatString(true);

        Object[] params = new Object[PARAM_LENGTH_NWAL1130];
        int paramsIdx = 0;
        params[paramsIdx++] = "";
        params[paramsIdx++] = "Association Program Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     PC.GLBL_CMPY_CD");
        sb.append("    ,PC.EZCANCELFLAG");
        sb.append("    ,PC.PRC_CONTR_NUM");
        sb.append("    ,PC.PRC_CONTR_NM ");
        sb.append("    ,DECODE(PC.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG");
        sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
        sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");
        sb.append("FROM");
        sb.append("    PRC_CONTR PC ");
        sb.append("WHERE");
        sb.append("    PC.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND PC.EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (PC.EFF_THRU_DT  >= '").append(scrnMsg.slsDt.getValue()).append("' OR PC.EFF_THRU_DT IS NULL)");
        sb.append("    AND PC.EZCANCELFLAG   = 0");

        params[paramsIdx++] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[ATTR_NUM_WHERE_LIST];
        whereArray0[WLIST_DSP_OBJ_NM] = "Price Contract #";
        whereArray0[WLIST_OBJ_ID] = "PRC_CONTR_NUM";

        if (hasValue(scrnMsg.prcContrNum_D)) {
            whereArray0[WLIST_OBJ_VALUE] = scrnMsg.prcContrNum_D.getValue();
        } else {
            whereArray0[WLIST_OBJ_VALUE] = PERCENT;
        }

        whereArray0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[ATTR_NUM_WHERE_LIST];
        whereArray1[WLIST_DSP_OBJ_NM] = "Price Contract Name";
        whereArray1[WLIST_OBJ_ID] = "PRC_CONTR_NM";
        whereArray1[WLIST_OBJ_VALUE] = "";
        whereArray1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[paramsIdx++] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[ATTR_NUM_CLMN_LIST];
        columnArray0[CLIST_DSP_OBJ_NM] = "Price Contract #";
        columnArray0[CLIST_OBJ_ID] = "PRC_CONTR_NUM";
        columnArray0[CLIST_OBJ_LENGTH] = BigDecimal.valueOf(PRC_CONTR_NUM_LENGTH);
        columnArray0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[ATTR_NUM_CLMN_LIST];
        columnArray1[CLIST_DSP_OBJ_NM] = "Price Contract Name";
        columnArray1[CLIST_OBJ_ID] = "PRC_CONTR_NM";
        columnArray1[CLIST_OBJ_LENGTH] = BigDecimal.valueOf(PRC_CONTR_NM_LENGTH);
        columnArray1[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[ATTR_NUM_CLMN_LIST];
        columnArray2[CLIST_DSP_OBJ_NM] = "Status";
        columnArray2[CLIST_OBJ_ID] = "ACTV_FLG";
        columnArray2[CLIST_OBJ_LENGTH] = BigDecimal.valueOf(ACTV_FLG_LENGTH);
        columnArray2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[ATTR_NUM_CLMN_LIST];
        columnArray3[CLIST_DSP_OBJ_NM] = "Eff From Date";
        columnArray3[CLIST_OBJ_ID] = "EFF_FROM_DT";
        columnArray3[CLIST_OBJ_LENGTH] = BigDecimal.valueOf(EFF_FROM_DT_LENGTH);
        columnArray3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[ATTR_NUM_CLMN_LIST];
        columnArray4[CLIST_DSP_OBJ_NM] = "Eff Thru Date";
        columnArray4[CLIST_OBJ_ID] = "EFF_THRU_DT";
        columnArray4[CLIST_OBJ_LENGTH] = BigDecimal.valueOf(EFF_THRU_DT_LENGTH);
        columnArray4[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[paramsIdx++] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[ATTR_NUM_SORT_LIST];
        sortConditionArray0[SLIST_OBJ_ID] = "PRC_CONTR_NUM";
        sortConditionArray0[SLIST_ORD_BY] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[ATTR_NUM_SORT_LIST];
        sortConditionArray1[SLIST_OBJ_ID] = "PRC_CONTR_NM";
        sortConditionArray1[SLIST_ORD_BY] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[paramsIdx++] = sortConditionList;

        params[paramsIdx++] = scrnMsg.Z;

        return params;
    }
}
