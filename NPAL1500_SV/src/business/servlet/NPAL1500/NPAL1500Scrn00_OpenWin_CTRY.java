/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_0;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_1;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_2;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_3;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_4;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_5;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_6;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_7;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   XXXX            N.Akaishi       Create          n/a
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_CTRY extends S21CommonHandler {

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
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, "OpenWin_CTRY");

        Object[] params = getPopupParam(scrnMsg);
        setArgForSubScreen(params);
    }

    /**
     * Set Supplier Popup param
     * @param scrnMsg NPAL1500BMsg
     * @return Supplier Popup Param (NWAL1130) Object[]
     */
    private Object[] getPopupParam(NPAL1500BMsg scrnMsg) {
        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "State/Country Search";
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("      ST.GLBL_CMPY_CD");
        sb.append("     ,ST.EZCANCELFLAG");
        sb.append("     ,ST.ST_CD");
        sb.append("     ,ST.ST_DESC_TXT");
        sb.append("     ,CT.CTRY_CD");
        sb.append("     ,CT.CTRY_DESC_TXT");
        sb.append(" FROM ");
        sb.append("      ST   ST");
        sb.append("     ,CTRY CT");
        sb.append(" WHERE ");
        sb.append("      ST.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'");
        sb.append("  AND ST.EZCANCELFLAG = '0'");
        sb.append("  AND ST.GLBL_CMPY_CD = CT.GLBL_CMPY_CD");
        sb.append("  AND ST.CTRY_CD      = CT.CTRY_CD");
        sb.append("  AND CT.EZCANCELFLAG = '0'");
        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", scrnMsg.glblCmpyCd.getValue());
        params[IDX_2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "State Code";
        whereArray1[IDX_1] = "ST_CD";
        whereArray1[IDX_2] = scrnMsg.shipToStCd_ST.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State Name";
        whereArray2[IDX_1] = "ST_DESC_TXT";
        whereArray2[IDX_2] = null;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Country Code";
        whereArray3[IDX_1] = "CTRY_CD";
        whereArray3[IDX_2] = scrnMsg.shipToCtryCd_ST.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "Country Name";
        whereArray4[IDX_1] = "CTRY_DESC_TXT";
        whereArray4[IDX_2] = null;
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[0] = "State Code";
        columnArray1[1] = "ST_CD";
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[0] = "State Name";
        columnArray2[1] = "ST_DESC_TXT";
        columnArray2[2] = BigDecimal.valueOf(35);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[0] = "Country Code";
        columnArray3[1] = "CTRY_CD";
        columnArray3[2] = BigDecimal.valueOf(10);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[0] = "Country Name";
        columnArray4[1] = "CTRY_DESC_TXT";
        columnArray4[2] = BigDecimal.valueOf(35);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "CTRY_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);
        params[IDX_5] = sortConditionList;

        params[IDX_6] = scrnMsg.P;

        return params;
    }
}
