/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.EVENT_NM_OPENWIN_MODEL;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_MODEL_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_MODEL_ID_DISPLAY;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_MODEL_NAME;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_MODEL_NAME_DISPLAY;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_SERVICE_MODEL_SEARCH;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_SORT_KEY_ASC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7060Scrn00_OpenWin_Model
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7060Scrn00_OpenWin_Model extends S21CommonHandler {

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

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        int index = getButtonSelectNumber();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = POPUP_NM_SERVICE_MODEL_SEARCH;
        params[2] = getSelectSQL();
        params[3] = getSearchConditionSetting(scrnMsg, index);
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();

        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, EVENT_NM_OPENWIN_MODEL);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NMAL7060BMsg scrnMsg, int index) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[4];
        whereObj1[0] = POPUP_NM_MODEL_ID_DISPLAY;
        whereObj1[1] = POPUP_NM_MODEL_ID;
        String mdlId = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).mdlId_A1)) {
            mdlId = scrnMsg.A.no(index).mdlId_A1.getValue().toString();
        }
        whereObj1[2] = mdlId;
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);

        Object[] whereObj2 = new Object[4];
        whereObj2[0] = POPUP_NM_MODEL_NAME_DISPLAY;
        whereObj2[1] = POPUP_NM_MODEL_NAME;
        whereObj2[2] = scrnMsg.A.no(index).mdlNm_A1.getValue();
        whereObj2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[4];
        colObj1[0] = POPUP_NM_MODEL_ID_DISPLAY;
        colObj1[1] = POPUP_NM_MODEL_ID;
        colObj1[2] = new BigDecimal("22");
        colObj1[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj1);

        Object[] colObj2 = new Object[4];
        colObj2[0] = POPUP_NM_MODEL_NAME_DISPLAY;
        colObj2[1] = POPUP_NM_MODEL_NAME;
        colObj2[2] = new BigDecimal("50");
        colObj2[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = new Object[2];
        sortObj1[0] = POPUP_NM_MODEL_NAME;
        sortObj1[1] = POPUP_SORT_KEY_ASC;
        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append(" MN.T_MDL_NM ");
        sb.append(" , MN.T_MDL_ID ");
        sb.append(" , MN.T_GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append(" , MN.EZCANCELFLAG ");
        sb.append(" FROM MDL_NM MN ");
        sb.append("     ,DS_MDL DM ");
        sb.append(" WHERE MN.T_GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND MN.EZCANCELFLAG = '0' ");
        sb.append(" AND MN.T_GLBL_CMPY_CD = DM.GLBL_CMPY_CD ");
        sb.append(" AND MN.T_MDL_ID = DM.MDL_ID ");
        sb.append(" AND DM.MDL_ACTV_FLG = '").append(ZYPConstant.FLG_ON_Y).append("' ");
        sb.append(" AND DM.EZCANCELFLAG = '0' ");
        String sql = sb.toString();

        return sql;
    }
}
