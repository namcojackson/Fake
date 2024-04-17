/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1350;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1350Scrn00_OpenWin_CmnBigShellItem
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL1350Scrn00_OpenWin_CmnBigShellItem extends S21CommonHandler {

    /** parameter count of Common BIG Popup */
    private static final int PRM_CNT = 7;

    /** line separator for SQL */
    private static final String NEWLINE = System.getProperty("line.separator");

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

        NSAL1350BMsg scrnMsg = (NSAL1350BMsg) bMsg;

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

        params[i++] = "";
        params[i++] = "Shell Item Popup";
        params[i++] = getSelectSQL(scrnMsg);
        params[i++] = getSearchConditionSetting(scrnMsg.mdseCd.getValue());
        params[i++] = getDisplayColumnSetting();
        params[i++] = getSortSetting();
        params[i] = scrnMsg.P;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(String mdseCd) {
        List<Object> whereList = new ArrayList<Object>(2);
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            mdseCd = "%";
        }

        // {"display name", "column name(physical)", value, use LIKE flag}
        Object[] whereObj1 = {"Shell Item", "MDSE_CD", mdseCd, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Description", "MDSE_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>(2);

        // {"display name", "column name(physical)", column size, selectable flag}
        Object[] colObj1 = {"Shell Item", "MDSE_CD", new BigDecimal("16"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Description", "MDSE_NM", new BigDecimal("50"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>(1);

        // {"column name(physical)", sort order(ASC/DESC)}
        Object[] sortObj1 = {"MDSE_CD", "ASC" };

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NSAL1350BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT DISTINCT");
        sb.append(NEWLINE).append("     CDV.EZCANCELFLAG                                          ");
        sb.append(NEWLINE).append("    ,CDV.GLBL_CMPY_CD                                          ");
        sb.append(NEWLINE).append("    ,CDV.MDSE_CD                                               ");
        sb.append(NEWLINE).append("    ,CDV.MDSE_NM                                               ");
        sb.append(NEWLINE).append("FROM                                                           ");
        sb.append(NEWLINE).append("     CPO_V           CPV                                       ");
        sb.append(NEWLINE).append("    ,DS_CPO_CONFIG   DCC                                       ");
        sb.append(NEWLINE).append("    ,CPO_DTL_V       CDV                                       ");
        sb.append(NEWLINE).append("WHERE                                                          ");
        sb.append(NEWLINE).append("        CPV.GLBL_CMPY_CD      = 'glblCmpyCd'                   ");
        sb.append(NEWLINE).append("    AND CPV.CPO_ORD_NUM       = 'cpoOrdNum'                    ");
        sb.append(NEWLINE).append("    AND CPV.ORD_HDR_STS_CD    IN ('saved', 'validated')        ");
        sb.append(NEWLINE).append("    AND CPV.EZCANCELFLAG      = '0'                            ");
        sb.append(NEWLINE).append("    AND CPV.GLBL_CMPY_CD      = DCC.GLBL_CMPY_CD               ");
        sb.append(NEWLINE).append("    AND CPV.CPO_ORD_NUM       = DCC.CPO_ORD_NUM                ");
        sb.append(NEWLINE).append("    AND DCC.EZCANCELFLAG     = '0'                             ");
        sb.append(NEWLINE).append("    AND DCC.DCLN_SVC_CD      <> '1'                            ");
        sb.append(NEWLINE).append("    AND DCC.GLBL_CMPY_CD     = CDV.GLBL_CMPY_CD                ");
        sb.append(NEWLINE).append("    AND DCC.DS_CPO_CONFIG_PK = CDV.DS_CPO_CONFIG_PK            ");
        sb.append(NEWLINE).append("    AND CDV.BASE_CMPT_FLG    = 'Y'                             ");
        sb.append(NEWLINE).append("    AND CDV.CPO_DTL_CANC_FLG = 'N'                             ");
        sb.append(NEWLINE).append("    AND CDV.EZCANCELFLAG     = '0'                             ");
        sb.append(NEWLINE).append("    AND NOT EXISTS (                                           ");
        sb.append(NEWLINE).append("        SELECT                                                 ");
        sb.append(NEWLINE).append("            '1'                                                ");
        sb.append(NEWLINE).append("        FROM                                                   ");
        sb.append(NEWLINE).append("            SHPG_PLN    SP                                     ");
        sb.append(NEWLINE).append("        WHERE                                                  ");
        sb.append(NEWLINE).append("                SP.GLBL_CMPY_CD     = CDV.GLBL_CMPY_CD         ");
        sb.append(NEWLINE).append("            AND SP.TRX_HDR_NUM      = CDV.CPO_ORD_NUM          ");
        sb.append(NEWLINE).append("            AND SP.TRX_LINE_NUM     = CDV.CPO_DTL_LINE_NUM     ");
        sb.append(NEWLINE).append("            AND SP.TRX_LINE_SUB_NUM = CDV.CPO_DTL_LINE_SUB_NUM ");
        sb.append(NEWLINE).append("            AND SP.SHPG_STS_CD      >= 'shpgStsArrived'        ");
        sb.append(NEWLINE).append("            AND SP.EZCANCELFLAG     = '0' )                    ");

        String query = sb.toString();
        query = query.replaceFirst("glblCmpyCd", getGlobalCompanyCode());
        query = query.replaceFirst("cpoOrdNum", scrnMsg.cpoOrdNum_I.getValue());
        query = query.replaceFirst("saved", ORD_HDR_STS.SAVED);
        query = query.replaceFirst("validated", ORD_HDR_STS.VALIDATED);
        query = query.replaceFirst("shpgStsArrived", SHPG_STS.ARRIVED);
        return query;
    }
}
