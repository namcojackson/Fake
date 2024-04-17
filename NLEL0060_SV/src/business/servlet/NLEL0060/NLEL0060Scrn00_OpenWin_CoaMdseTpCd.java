/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_COA_MDSE_TP_CD;

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
 * NLEL0060Scrn00_OpenWin_CoaMdseTpCd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/13   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_CoaMdseTpCd extends S21CommonHandler {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, OPENWIN_COA_MDSE_TP_CD);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "COA Project Search";

        String coaProjCd = "%";
        int idx = getButtonSelectNumber();
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).coaMdseTpCd_A1)) {
            coaProjCd = scrnMsg.A.no(idx).coaMdseTpCd_A1.getValue();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("      CP.EZCANCELFLAG ");
        sb.append("    , CP.GLBL_CMPY_CD ");
        sb.append("    , CP.COA_PROJ_CD ");
        sb.append("    , CP.COA_PROJ_NM ");
        sb.append("FROM ");
        sb.append("      COA_PROJ        CP ");
        // START 2017/03/09 E.Kameishi [QC#17987, DELL]
        //sb.append("    , MDSE_TP_VAL_SET MTV ");
        // END 2017/03/09 E.Kameishi [QC#17987, DELL]
        sb.append("WHERE ");
        sb.append("    CP.EZCANCELFLAG ='0' ");
        sb.append("AND CP.GLBL_CMPY_CD = '" + getGlobalCompanyCode() + "' ");
        // START 2017/03/09 E.Kameishi [QC#17987, DELL]
        //sb.append("AND MTV.EZCANCELFLAG ='0' ");
        //sb.append("AND CP.GLBL_CMPY_CD      = MTV.GLBL_CMPY_CD ");
        //sb.append("AND CP.COA_PROJ_CD       = MTV.COA_MDSE_TP_CD ");
        //sb.append("AND MTV.MDSE_TP_CTX_TP_CD = 'ASSET_CRAT_TRGT' ");
        // END 2017/03/09 E.Kameishi [QC#17987, DELL]
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = {"COA Project Code", "COA_PROJ_CD", coaProjCd, ZYPConstant.FLG_ON_Y };
        Object[] whereArray1 = {"COA Project Name", "COA_PROJ_NM", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereArray0);
        whereList.add(whereArray1);
        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = {"COA Project Code", "COA_PROJ_CD", BigDecimal.valueOf(30), ZYPConstant.FLG_ON_Y };
        Object[] columnArray1 = {"COA Project Name", "COA_PROJ_NM", BigDecimal.valueOf(60), ZYPConstant.FLG_OFF_N };
        columnList.add(columnArray0);
        columnList.add(columnArray1);
        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = {"COA_PROJ_CD", "ASC" };
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        params[6] = scrnMsg.Q;

        setArgForSubScreen(params);
    }
}
