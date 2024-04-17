/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/25   Hitachi         A.Kohinata      Create          QC#17369
 *</pre>
 */
public class NSAL1310Scrn00_OpenWin_DefValue extends S21CommonHandler {

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
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        int selectNumber = getButtonSelectNumber();

        Object[] params = new Object[7];
        params[0] = "";

        StringBuilder sb = new StringBuilder();
        sb.append(scrnMsg.A.no(selectNumber).logicMaintTrgtTblNm_A1.getValue()).append(" Search");
        params[1] = sb.toString();

        sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     GLBL_CMPY_CD ");
        sb.append("    ,EZCANCELFLAG ");
        sb.append("    ,").append(scrnMsg.A.no(selectNumber).physMaintTrgtColNm_A1.getValue()).append(" ");
        if (!scrnMsg.A.no(selectNumber).physMaintTrgtColNm_A1.getValue().equals(scrnMsg.A.no(selectNumber).physDplyTrgtColNm_A1.getValue())) {
            sb.append("    ,").append(scrnMsg.A.no(selectNumber).physDplyTrgtColNm_A1.getValue()).append(" ");
        }
        sb.append("FROM ");
        sb.append("    ").append(scrnMsg.A.no(selectNumber).physMaintTrgtTblNm_A1.getValue()).append(" ");
        sb.append("WHERE ");
        sb.append("        GLBL_CMPY_CD  = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("    AND EZCANCELFLAG  = '0' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        if (scrnMsg.A.no(selectNumber).logicDplyTrgtColNm_A1.getValue().length() > 20) {
            whereArray0[0] = scrnMsg.A.no(selectNumber).logicDplyTrgtColNm_A1.getValue().substring(0, 20);
        } else {
            whereArray0[0] = scrnMsg.A.no(selectNumber).logicDplyTrgtColNm_A1.getValue();
        }
        whereArray0[1] = scrnMsg.A.no(selectNumber).physDplyTrgtColNm_A1.getValue();
        whereArray0[2] = null;
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        if (scrnMsg.A.no(selectNumber).logicMaintTrgtColNm_A1.getValue().length() > 30) {
            columnArray0[0] = scrnMsg.A.no(selectNumber).logicMaintTrgtColNm_A1.getValue().substring(0, 30);
        } else {
            columnArray0[0] = scrnMsg.A.no(selectNumber).logicMaintTrgtColNm_A1.getValue();
        }
        columnArray0[1] = scrnMsg.A.no(selectNumber).physMaintTrgtColNm_A1.getValue();
        columnArray0[2] = BigDecimal.valueOf(45);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        if (!scrnMsg.A.no(selectNumber).physMaintTrgtColNm_A1.getValue().equals(scrnMsg.A.no(selectNumber).physDplyTrgtColNm_A1.getValue())) {
            Object[] columnArray1 = new Object[4];
            if (scrnMsg.A.no(selectNumber).logicDplyTrgtColNm_A1.getValue().length() > 30) {
                columnArray1[0] = scrnMsg.A.no(selectNumber).logicDplyTrgtColNm_A1.getValue().substring(0, 30);
            } else {
                columnArray1[0] = scrnMsg.A.no(selectNumber).logicDplyTrgtColNm_A1.getValue();
            }
            columnArray1[1] = scrnMsg.A.no(selectNumber).physDplyTrgtColNm_A1.getValue();
            columnArray1[2] = BigDecimal.valueOf(45);
            columnArray1[3] = "N";
            columnList.add(columnArray1);
        }

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = scrnMsg.A.no(selectNumber).physMaintTrgtColNm_A1.getValue();
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}
