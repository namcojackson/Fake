/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
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

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/12/03   Hitachi         K.Yamada        Update          CSA QC#950
 * 2016/07/22   Hitachi         T.Tomita        Update          QC#11161
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_FieldServiceBranch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

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

        // START 2016/07/22 T.Tomita [QC#11161, MOD]
//        // Open NMAL6050
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, "SVC_CONTR_BR");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, "SVC_CONTR_BR_CD");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, "SVC_CONTR_BR_DESC_TXT");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, "SVC_CONTR_BR_CD");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, "Contract Branch Pop Up");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, "Contract Branch Code");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, "Contract Branch Name");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, "Contract Branch Code");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, "Contract Branch Name");
//        scrnMsg.xxCondNm_P.clear();

//        Object[] param = new Object[11];
        Object[] param = getParamNWAL1130ForContrBr(scrnMsg);

//        int i = 0;
//        param[i++] = scrnMsg.xxTblNm_P;
//        param[i++] = scrnMsg.xxTblCdColNm_P;
//        param[i++] = scrnMsg.xxTblNmColNm_P;
//        param[i++] = scrnMsg.xxTblSortColNm_P;
//        param[i++] = scrnMsg.xxScrNm_P;
//        param[i++] = scrnMsg.xxHdrCdLbNm_P;
//        param[i++] = scrnMsg.xxHdrNmLbNm_P;
//        param[i++] = scrnMsg.xxDtlCdLbNm_P;
//        param[i++] = scrnMsg.xxDtlNmLbNm_P;
//        // mod start 2015/12/03 CSA Defect#950
//        param[i++] = scrnMsg.xxCondCd_P;
//        // mod end 2015/12/03 CSA Defect#950
//        param[i++] = scrnMsg.xxCondNm_P;
        // END 2016/07/22 T.Tomita [QC#11161, MOD]

        setArgForSubScreen(param);

    }

    // START 2016/07/22 T.Tomita [QC#11161, ADD]
    private static Object[] getParamNWAL1130ForContrBr(NSAL0010BMsg scrnMsg) {

        Object[] params = new Object[PARAM_LENGTH_NWAL1130];
        int paramsIdx = 0;
        params[paramsIdx++] = "";
        params[paramsIdx++] = "Field Service Branch Pop Up";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("     X.GLBL_CMPY_CD");
        sb.append("    ,X.EZCANCELFLAG");
        sb.append("    ,X.SVC_BR_CD");
        sb.append("    ,X.SVC_BR_CD_DESC_TXT ");
        sb.append("FROM");
        sb.append("    SVC_BR_POST_XREF X ");
        sb.append("WHERE");
        sb.append("        X.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND X.EZCANCELFLAG = '0'");

        params[paramsIdx++] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[ATTR_NUM_WHERE_LIST];
        whereArray0[WLIST_DSP_OBJ_NM] = "Service Branch Code";
        whereArray0[WLIST_OBJ_ID] = "SVC_BR_CD";

        if (hasValue(scrnMsg.svcBrCd_D)) {
            whereArray0[WLIST_OBJ_VALUE] = scrnMsg.svcBrCd_D.getValue();
        } else {
            whereArray0[WLIST_OBJ_VALUE] = PERCENT;
        }

        whereArray0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[ATTR_NUM_WHERE_LIST];
        whereArray1[WLIST_DSP_OBJ_NM] = "Service Branch Name";
        whereArray1[WLIST_OBJ_ID] = "SVC_BR_CD_DESC_TXT";
        whereArray1[WLIST_OBJ_VALUE] = "";
        whereArray1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[paramsIdx++] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[ATTR_NUM_CLMN_LIST];
        columnArray0[CLIST_DSP_OBJ_NM] = "Service Branch Code";
        columnArray0[CLIST_OBJ_ID] = "SVC_BR_CD";
        columnArray0[CLIST_OBJ_LENGTH] = BigDecimal.valueOf(SVC_BR_CD_LENGTH);
        columnArray0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[ATTR_NUM_CLMN_LIST];
        columnArray1[CLIST_DSP_OBJ_NM] = "Service Branch Name";
        columnArray1[CLIST_OBJ_ID] = "SVC_BR_CD_DESC_TXT";
        columnArray1[CLIST_OBJ_LENGTH] = BigDecimal.valueOf(SVC_BR_CD_DESC_TXT_LENGTH);
        columnArray1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[paramsIdx++] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[ATTR_NUM_SORT_LIST];
        sortConditionArray0[SLIST_OBJ_ID] = "SVC_BR_CD";
        sortConditionArray0[SLIST_ORD_BY] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[paramsIdx++] = sortConditionList;

        params[paramsIdx++] = scrnMsg.Z;

        return params;
    }
    // END 2016/07/22 T.Tomita [QC#11161, ADD]
}
