/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1050;

import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_0;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_1;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_2;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_3;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_4;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_5;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_6;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.IDX_7;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.NUM_ABC_ANLS_CLS_NM_WIDTH;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/01   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050Scrn00_OpenWin_ABCClass extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, "OpenWin_ABCClass");

        Object[] params = getPopupParam(scrnMsg);
        setArgForSubScreen(params);

    }

    private Object[] getPopupParam(NLCL1050BMsg scrnMsg) {
        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "ABC Class Search";
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("SELECT DISTINCT");
        sb.append("      AAC.GLBL_CMPY_CD");
        sb.append("     ,AAC.EZCANCELFLAG");
        sb.append("     ,AAC.ABC_ANLS_CLS_NM");
        sb.append(" FROM ");
        sb.append("      ABC_ANLS_CLS   AAC");
        sb.append(" WHERE ");
        sb.append("      AAC.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'");
        sb.append("  AND AAC.EZCANCELFLAG = '0'");
        sb.append(")");
        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", scrnMsg.glblCmpyCd.getValue());
        params[IDX_2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "ABC Class Name";
        whereArray1[IDX_1] = "ABC_ANLS_CLS_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.abcAnlsClsNm)) {
            whereArray1[IDX_2] = scrnMsg.abcAnlsClsNm.getValue();
        } else {
            whereArray1[IDX_2] = null;
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[0] = "ABC Class Name";
        columnArray1[IDX_1] = "ABC_ANLS_CLS_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(NUM_ABC_ANLS_CLS_NM_WIDTH);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "ABC_ANLS_CLS_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);
        params[IDX_5] = sortConditionList;

        params[IDX_6] = scrnMsg.P;

        return params;
    }

}
