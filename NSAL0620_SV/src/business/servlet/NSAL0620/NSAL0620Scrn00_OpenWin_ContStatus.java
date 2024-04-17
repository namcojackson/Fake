/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

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
 * 2022/10/13   Hitachi         M.Komatsu       Create          QC#60078
 *</pre>
 */
public class NSAL0620Scrn00_OpenWin_ContStatus extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        setArgForSubScreen(setPopupParameter(scrnMsg, getGlobalCompanyCode()));
    }

    private static Object[] setPopupParameter(NSAL0620BMsg scrnMsg, String glblCmpyCd) {
        Object[] param = new Object[7];
        // 0:Suffix (not used)
        String suffix = "";
        // 1:Screen Name
        String screenName = "Contract Status Popup";
        // 2:Table Name
        StringBuilder tableName = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereList = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnList = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)
        scrnMsg.R.clear();

        tableName.append(" SELECT");
        tableName.append("      DS_CONTR_CTRL_STS_NM");
        tableName.append("     ,DS_CONTR_CTRL_STS_CD");
        tableName.append("     ,GLBL_CMPY_CD");
        tableName.append("     ,EZCANCELFLAG");
        tableName.append(" FROM");
        tableName.append("     DS_CONTR_CTRL_STS");
        tableName.append(" WHERE");
        tableName.append("         CONTR_MACH_AVAL_FLG  =  'Y'");
        tableName.append("     AND DS_CONTR_CTRL_STS_CD <> 'RNPS'");
        tableName.append("     AND DS_CONTR_CTRL_STS_CD <> 'RNWS'");
        tableName.append("     AND GLBL_CMPY_CD         =  '").append(glblCmpyCd).append("'");
        tableName.append("     AND EZCANCELFLAG         =  '0'");
        tableName.append(" UNION");
        tableName.append("     SELECT");
        tableName.append("         'Pending Installation' AS DS_CONTR_DTL_STS_NM,");
        tableName.append("         'ZZZX' AS DS_CONTR_DTL_STS_CD,");
        tableName.append("         '").append(glblCmpyCd).append("' AS GLBL_CMPY_CD,");
        tableName.append("         '0' AS EZCANCELFLAG");
        tableName.append("     FROM");
        tableName.append("         DUAL");
        tableName.append(" UNION");
        tableName.append("     SELECT");
        tableName.append("         'Active(Pending PO)' AS DS_CONTR_DTL_STS_NM,");
        tableName.append("         'ZZZY' AS DS_CONTR_DTL_STS_CD,");
        tableName.append("         '").append(glblCmpyCd).append("' AS GLBL_CMPY_CD,");
        tableName.append("         '0' AS EZCANCELFLAG");
        tableName.append("     FROM");
        tableName.append("         DUAL");
        tableName.append(" UNION");
        tableName.append("     SELECT");
        tableName.append("         'Active(Renewal Hold)' AS DS_CONTR_DTL_STS_NM,");
        tableName.append("         'ZZZZ' AS DS_CONTR_DTL_STS_CD,");
        tableName.append("         '").append(glblCmpyCd).append("' AS GLBL_CMPY_CD,");
        tableName.append("         '0' AS EZCANCELFLAG");
        tableName.append("     FROM");
        tableName.append("          DUAL");

        Object[] whereArray = new Object[4];
        whereArray[0] = "Contract Status";
        whereArray[1] = "DS_CONTR_CTRL_STS_NM";
        whereArray[2] = ""; // not used
        whereArray[3] = "Y"; // allow LIKE
        whereList.add(whereArray);

        Object[] columnArray = new Object[4];
        columnArray[0] = "Contract Status";
        columnArray[1] = "DS_CONTR_CTRL_STS_NM";
        columnArray[2] = BigDecimal.valueOf(25); // table column width
        columnArray[3] = ""; // not used
        columnList.add(columnArray);

        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_CONTR_CTRL_STS_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        String[] valueList = scrnMsg.xxComnScrColValTxt_H2.getValue().split(";");
        for (int i = 0; i < valueList.length; i++) {
            scrnMsg.R.no(i).xxComnScrColValTxt_0.setValue(valueList[i]);
        }
        scrnMsg.R.setValidCount(valueList.length);

        param[0] = suffix;
        param[1] = screenName;
        param[2] = tableName.toString();
        param[3] = whereList;
        param[4] = columnList;
        param[5] = sortConditionList;
        param[6] = scrnMsg.R;

        return param;
    }
}
