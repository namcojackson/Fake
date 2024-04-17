/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3120.constant.NLBL3120Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120Scrn00_OpenWin_AplyCarrName extends S21CommonHandler {

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

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Popup";
        params[2] = getSelectSQL(scrnMsg);
        params[3] = getSearchConditionSetting(scrnMsg);
        params[4] = getDisplayColumnSetting(scrnMsg);
        params[5] = getSortSetting(scrnMsg);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLBL3120Constant.EVENT_NM_OPENWIN_CARR_APLY);
        setArgForSubScreen(params);

    }

    private List<Object> getSearchConditionSetting(NLBL3120BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"Carrier Code", "CARR_CD", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Carrier Name", "CARR_NM", scrnMsg.carrNm_BT.getValue(), ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NLBL3120BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Carrier Code", "CARR_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Carrier Name", "CARR_NM", new BigDecimal("60"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting(NLBL3120BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"CARR_CD", "ASC"};

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NLBL3120BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT                                    ").append("\n");
        sb.append("    V.GLBL_CMPY_CD                        ").append("\n");
        sb.append("   ,V.VND_CD AS CARR_CD                   ").append("\n");
        sb.append("   ,V.LOC_NM AS CARR_NM                   ").append("\n");
        sb.append("   ,V.EZCANCELFLAG                        ").append("\n");
        sb.append("FROM                                      ").append("\n");
        sb.append("    VND         V                         ").append("\n");
        sb.append("   ,VND_TP_RELN VT                        ").append("\n");
        sb.append("WHERE                                     ").append("\n");
        sb.append("        V.GLBL_CMPY_CD  = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("    AND V.RGTN_STS_CD   = '#RGTN_STS_CD#' ").append("\n");
        sb.append("    AND V.EFF_FROM_DT  <= '#SLS_DT#'      ").append("\n");
        sb.append("    AND (V.EFF_THRU_DT >= '#SLS_DT#'      ").append("\n");
        sb.append("     OR  V.EFF_THRU_DT IS NULL)           ").append("\n");
        sb.append("    AND V.EZCANCELFLAG  = '0'             ").append("\n");
        sb.append("    AND VT.GLBL_CMPY_CD = V.GLBL_CMPY_CD  ").append("\n");
        sb.append("    AND VT.VND_CD       = V.VND_CD        ").append("\n");
        sb.append("    AND VT.VND_TP_CD    = '#VND_TP#'      ").append("\n");
        sb.append("    AND VT.EZCANCELFLAG = '0'             ").append("\n");

        if (ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_BT)) {
            sb.append("    AND EXISTS (SELECT                                          ").append("\n");
            sb.append("                    *                                           ").append("\n");
            sb.append("                FROM                                            ").append("\n");
            sb.append("                    SHPG_SVC_LVL_CARR_RELN CR                   ").append("\n");
            sb.append("                WHERE                                           ").append("\n");
            sb.append("                        CR.GLBL_CMPY_CD    = V.GLBL_CMPY_CD     ").append("\n");
            sb.append("                    AND CR.CARR_CD         = V.VND_CD           ").append("\n");
            sb.append("                    AND CR.SHPG_SVC_LVL_CD = '#SHPG_SVC_LVL_CD#'").append("\n");
            sb.append("                    AND CR.EZCANCELFLAG    = '0')               ").append("\n");
        }

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());
        sql = sql.replaceAll("#RGTN_STS_CD#", RGTN_STS.READY_FOR_ORDER_TAKING);
        sql = sql.replaceAll("#SLS_DT#", ZYPDateUtil.getSalesDate());
        sql = sql.replaceAll("#VND_TP#", VND_TP.OUTBOUND_CARRIER);
        sql = sql.replaceAll("#SHPG_SVC_LVL_CD#", scrnMsg.shpgSvcLvlCd_BT.getValue());

        return sql;
    }
}

