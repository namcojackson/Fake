/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_VND_CODE;

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
 * NLEL0060Scrn00_OpenWin_VndCode
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_VndCode extends S21CommonHandler {

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

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        scrnMsg.P.clear();
        scrnMsg.Q.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, OPENWIN_VND_CODE);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Supplier/Supplier Site Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    PV.EZCANCELFLAG ");
        sb.append("    , PV.GLBL_CMPY_CD ");
        sb.append("    , PV.PRNT_VND_CD ");
        sb.append("    , PV.PRNT_VND_NM ");
        sb.append("FROM ");
        sb.append("    PRNT_VND  PV ");
        sb.append("WHERE ");
        sb.append("        PV.EZCANCELFLAG ='0' ");
        sb.append("    AND PV.GLBL_CMPY_CD = '" + getGlobalCompanyCode() + "' ");
        sb.append("    AND PV.INAC_DT >= TO_CHAR (SYSDATE, 'YYYYMMDD') ");
        params[2] = sb.toString();

        String vndCd = null;
        int idx = getButtonSelectNumber();
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).vndCd_A1)) {
            vndCd = scrnMsg.A.no(idx).vndCd_A1.getValue();
        }

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = {"Supplier Code", "PRNT_VND_CD", vndCd, ZYPConstant.FLG_ON_Y };
        Object[] whereArray1 = {"Supplier Name", "PRNT_VND_NM", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereArray0);
        whereList.add(whereArray1);
        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = {"Supplier Code", "PRNT_VND_CD", BigDecimal.valueOf(30), ZYPConstant.FLG_ON_Y };
        Object[] columnArray1 = {"Supplier Name", "PRNT_VND_NM", BigDecimal.valueOf(60), ZYPConstant.FLG_OFF_N };
        columnList.add(columnArray0);
        columnList.add(columnArray1);
        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = {"PRNT_VND_CD", "ASC" };
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        params[6] = scrnMsg.Q;

        setArgForSubScreen(params);
    }
}
