/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_ADD_ACTIVE_METER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OpenWin_CmnBigAddActiveMeter
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_CmnBigAddActiveMeter extends S21CommonHandler {

    private static final int SB_SIZE_QUERY = 1280;

    private static final int SB_SIZE_ACTIVE_METER = 16;

    /** parameter count of Common BIG Popup */
    private static final int PRM_CNT = 7;

    /** line separator for SQL */
    private static final String NEWLINE = System.getProperty("line.separator");

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(getButtonSelectNumber()));
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_ADD_ACTIVE_METER);

        scrnMsg.P.clear(); // parameter area for Common BIG Popup

        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Search Statement (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        // 6 : Lv2 : Output
        int i = 0;
        Object[] params = new Object[PRM_CNT];
        params[i++] = "P";
        params[i++] = "Add Active Meter Popup";
        params[i++] = getSelectSQL(scrnMsg);
        params[i++] = getSearchConditionSetting();
        params[i++] = getDisplayColumnSetting();
        params[i++] = getSortSetting();
        params[i] = scrnMsg.P;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting() {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"Billing Counter Code", "BILLING_COUNTER_CD", null, ZYPConstant.FLG_OFF_N };
        Object[] whereObj2 = {"Billing Counter Name", "BILLING_COUNTER_NM", "%", ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Billing Counter Code", "BILLING_COUNTER_CD", new BigDecimal("16"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Billing Counter Name", "BILLING_COUNTER_NM", new BigDecimal("25"), ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {"Usage Item Code", "USAGE_ITEM_CD", new BigDecimal("16"), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {"Usage Item Name", "USAGE_ITEM_NM", new BigDecimal("25"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"BILLING_COUNTER_CD", "ASC" };
        Object[] sortObj2 = {"BILLING_COUNTER_NM", "ASC" };

        sortList.add(sortObj1);
        sortList.add(sortObj2);

        return sortList;
    }

    private String getSelectSQL(NSAL1330BMsg scrnMsg) {
        StringBuilder sb = new StringBuilder(SB_SIZE_QUERY);

        sb.append("SELECT");
        sb.append(NEWLINE).append("          V.GLBL_CMPY_CD");
        sb.append(NEWLINE).append("        , V.EZCANCELFLAG");
        sb.append(NEWLINE).append("        , V.BILLING_COUNTER_CD");
        sb.append(NEWLINE).append("        , MB.MTR_LB_DESC_TXT     BILLING_COUNTER_NM");
        sb.append(NEWLINE).append("        , V.USAGE_ITEM_CD");
        sb.append(NEWLINE).append("        , V.USAGE_ITEM_NM");
        sb.append(NEWLINE).append("        , V.BLLG_MTR_LB_SORT_NUM");
        sb.append(NEWLINE).append("    FROM");
        sb.append(NEWLINE).append("        (");
        sb.append(NEWLINE).append("            SELECT DISTINCT");
        sb.append(NEWLINE).append("                      MS.GLBL_CMPY_CD");
        sb.append(NEWLINE).append("                    , MS.EZCANCELFLAG");
        sb.append(NEWLINE).append("                    , MS.BLLG_MTR_LB_CD AS BILLING_COUNTER_CD");
        sb.append(NEWLINE).append("                    , MS.BLLG_MTR_LB_NM AS BILLING_COUNTER_NM");
        sb.append(NEWLINE).append("                    , MS.INTG_MDSE_CD   AS USAGE_ITEM_CD");
        sb.append(NEWLINE).append("                    , MS.INTG_MDSE_NM   AS USAGE_ITEM_NM");
        sb.append(NEWLINE).append("                    , MS.BLLG_MTR_LB_SORT_NUM");
        sb.append(NEWLINE).append("                FROM");
        sb.append(NEWLINE).append("                    PRC_MTR_PKG_MTR_STRU_V   MS");
        sb.append(NEWLINE).append("                WHERE");
        sb.append(NEWLINE).append("                    MS.GLBL_CMPY_CD                   = '#glblCmpyCd#'");
        sb.append(NEWLINE).append("                    AND MS.MDL_ID                     = #mdlId#");
        sb.append(NEWLINE).append("                    AND MS.PRC_MTR_PKG_PK             = #prcMtrPkgPk#");
        sb.append(NEWLINE).append("                    AND MS.ACTV_FLG                   = 'Y'");
        sb.append(NEWLINE).append("                    AND MS.BLLBL_FLG                  = 'Y'");
        sb.append(NEWLINE).append("                    AND MS.MDL_MTR_EFF_FROM_DT       <= '#slsDt#'");
        sb.append(NEWLINE).append("                    AND (");
        sb.append(NEWLINE).append("                        MS.MDL_MTR_EFF_THRU_DT       >= '#slsDt#'");
        sb.append(NEWLINE).append("                        OR MS.MDL_MTR_EFF_THRU_DT    IS NULL");
        sb.append(NEWLINE).append("                    )");
        sb.append(NEWLINE).append("                    AND MS.BLLG_MTR_MAP_EFF_FROM_DT  <= '#slsDt#'");
        sb.append(NEWLINE).append("                    AND (");
        sb.append(NEWLINE).append("                        MS.BLLG_MTR_MAP_EFF_THRU_DT  >= '#slsDt#'");
        sb.append(NEWLINE).append("                        OR MS.BLLG_MTR_MAP_EFF_THRU_DT IS NULL");
        sb.append(NEWLINE).append("                    )");
        sb.append(NEWLINE).append("                    AND MS.MTR_PKG_EFF_FROM_DT       <= '#slsDt#'");
        sb.append(NEWLINE).append("                    AND (");
        sb.append(NEWLINE).append("                        MS.MTR_PKG_EFF_THRU_DT       >= '#slsDt#'");
        sb.append(NEWLINE).append("                        OR MS.MTR_PKG_EFF_THRU_DT    IS NULL");
        sb.append(NEWLINE).append("                    )");
        sb.append(NEWLINE).append("                    AND MS.MTR_PKG_MDL_EFF_FROM_DT   <= '#slsDt#'");
        sb.append(NEWLINE).append("                    AND (");
        sb.append(NEWLINE).append("                        MS.MTR_PKG_MDL_EFF_THRU_DT   >= '#slsDt#'");
        sb.append(NEWLINE).append("                        OR MS.MTR_PKG_MDL_EFF_THRU_DT IS NULL");
        sb.append(NEWLINE).append("                    )");
        sb.append(NEWLINE).append("                    AND MS.EZCANCELFLAG  = '0'");
        sb.append(NEWLINE).append("        ) V");
        sb.append(NEWLINE).append("        , MTR_LB     MB");
        sb.append(NEWLINE).append("    WHERE");
        sb.append(NEWLINE).append("        MB.GLBL_CMPY_CD      = V.GLBL_CMPY_CD");
        sb.append(NEWLINE).append("        AND MB.MTR_LB_CD     = V.BILLING_COUNTER_CD");
        sb.append(NEWLINE).append("        AND MB.EZCANCELFLAG  = '0'");
        sb.append(NEWLINE).append("        AND V.BILLING_COUNTER_CD NOT IN (#activeMeter#)");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", getGlobalCompanyCode());
        sql = sql.replaceAll("#slsDt#", ZYPDateUtil.getSalesDate());

        NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(getButtonSelectNumber());
        sql = sql.replaceAll("#mdlId#", rScrnMsg.mdlId_R.getValue().toPlainString());
        sql = sql.replaceAll("#prcMtrPkgPk#", rScrnMsg.prcMtrPkgPk_R.getValue().toPlainString());
        sql = sql.replaceAll("#activeMeter#", getActiveMeter(rScrnMsg, scrnMsg));

        return sql;
    }

    private String getActiveMeter(NSAL1330_RBMsg rScrnMsg, NSAL1330BMsg scrnMsg) {
        StringBuilder activeMeter = new StringBuilder(SB_SIZE_ACTIVE_METER);
        activeMeter.append("' '");

        List<String> activeMeterList = new ArrayList<String>(scrnMsg.U.getValidCount());
        for (int ixU = 0; ixU < scrnMsg.U.getValidCount(); ixU++) {
            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
//            if (rScrnMsg.mdlId_R.getValue().compareTo(uScrnMsg.mdlId_U.getValue()) != 0 //
//                    || rScrnMsg.cpoSvcConfigRefPk_R.getValue().compareTo(uScrnMsg.cpoSvcConfigRefPk_U.getValue()) != 0) {
//                continue;
//            }
            if (rScrnMsg.mdlId_R.getValue().compareTo(uScrnMsg.mdlId_U.getValue()) != 0 //
                    || rScrnMsg.dsContrDtlPk_R.getValue().compareTo(uScrnMsg.dsContrDtlPk_U.getValue()) != 0) {
                continue;
            }
            if (ZYPConstant.FLG_OFF_N.equals(uScrnMsg.actvFlg_U.getValue())) {
                continue;
            }
            if (activeMeterList.contains(uScrnMsg.bllgMtrLbCd_U.getValue())) {
                continue;
            }
            activeMeterList.add(uScrnMsg.bllgMtrLbCd_U.getValue());
        }

        for (String am : activeMeterList) {
            activeMeter.append(",").append("'").append(am).append("'");
        }
        return activeMeter.toString();
    }
}
