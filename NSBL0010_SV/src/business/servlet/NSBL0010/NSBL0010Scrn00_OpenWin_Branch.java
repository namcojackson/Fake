/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import static business.servlet.NSBL0010.constant.NSBL0010Constant.ATTR_NUM_CLMN_LIST;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.ATTR_NUM_SORT_LIST;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.ATTR_NUM_WHERE_LIST;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.CLIST_DSP_OBJ_NM;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.CLIST_LINK_FLG;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.CLIST_OBJ_ID;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.CLIST_OBJ_LENGTH;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.PERCENT;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.SLIST_OBJ_ID;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.SLIST_ORD_BY;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.SVC_BR_CD_DESC_TXT_LENGTH;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.SVC_BR_CD_LENGTH;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.WLIST_DSP_OBJ_NM;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.WLIST_OBJ_ID;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.WLIST_OBJ_VALUE;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.WLIST_WHERE_FLG;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0010.common.NSBL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/19   Hitachi         N.Arai          Create          QC#13901
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_Branch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

     // START 2017/01/05 N.Arai [QC#13901-2, MOD]
        // set param
        NSBL0010CommonLogic.clearPopupParameter(scrnMsg);
        // NSBL0010CommonLogic.setParamBranch(scrnMsg);
        // setArgForSubScreen(NSBL0010CommonLogic.getParamNMAL6050(scrnMsg));
        Object[] param = getParamNWAL1130ForContrBr(scrnMsg);
        setArgForSubScreen(param);
    }
    private static Object[] getParamNWAL1130ForContrBr(NSBL0010BMsg scrnMsg) {

        Object[] params = new Object[7];
        int paramsIdx = 0;
        params[paramsIdx++] = "";
        params[paramsIdx++] = "Service Branch Popup";

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

        if (hasValue(scrnMsg.svcContrBrCd)) {
            whereArray0[WLIST_OBJ_VALUE] = scrnMsg.svcContrBrCd.getValue();
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
 // END 2017/01/05 N.Arai [QC#13901-2, MOD]
}
