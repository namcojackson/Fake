/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7050.constant.NMAL7050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050Scrn00_Link_MDL extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Service Model Search";
        params[2] = getSelectSQL(scrnMsg);
        params[3] = getSearchConditionSetting(scrnMsg);
        params[4] = getDisplayColumnSetting(scrnMsg);
        params[5] = getSortSetting(scrnMsg);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NMAL7050Constant.EVENT_NM_LINK_MDL);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NMAL7050BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"Model Name", "T_MDL_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NMAL7050BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Model Name", "T_MDL_NM", new BigDecimal("50"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);

        return colList;
    }

    private List<Object> getSortSetting(NMAL7050BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"T_MDL_NM", "ASC"};

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NMAL7050BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("SELECT                                             ").append("\n");
        sb.append("       MN.T_MDL_NM                                 ").append("\n");
        sb.append("     , MN.T_GLBL_CMPY_CD AS GLBL_CMPY_CD           ").append("\n");
        sb.append("     , MN.EZCANCELFLAG                             ").append("\n");
        sb.append("  FROM MDL_NM MN                                   ").append("\n");
        sb.append(" WHERE MN.T_GLBL_CMPY_CD   = '#GLBL_CMPY_CD#'        ").append("\n");
        sb.append("   AND MN.EZCANCELFLAG   = '0'                     ").append("\n");
        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());

        return sql;
    }
}
