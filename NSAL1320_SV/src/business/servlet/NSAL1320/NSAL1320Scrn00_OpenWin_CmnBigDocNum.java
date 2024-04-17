/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1320.NSAL1320BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_OpenWin_CmnBigDocNum
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1320Scrn00_OpenWin_CmnBigDocNum extends S21CommonHandler {

    /** parameter count of Common BIG Popup */
    private static final int PRM_CNT = 7;

    /** line separator for SQL */
    private static final String NEWLINE = System.getProperty("line.separator");

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        scrnMsg.O.clear(); // parameter area for Common BIG Popup

        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Search Statement (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        // 6 : Lv2 : Output
        int i = 0;
        Object[] params = new Object[PRM_CNT];
        // set the values
        params[i++] = "";
        params[i++] = "Document Number Search Popup";
        params[i++] = getSelectSQL();
        params[i++] = getSearchConditionSetting();
        params[i++] = getDisplayColumnSetting();
        params[i++] = getSortSetting();
        params[i] = scrnMsg.O;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting() {
        List<Object> whereList = new ArrayList<Object>();

        // set the search condition parameters.
        // {"display name", "column name(physical)", value, use LIKE flag}
        Object[] whereObj1 = {"Document#", "MAINT_AGMT_VER_NUM", "%", ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        // set the search results parameters.
        // {"display name", "column name(physical)", column size, selectable flag}
        Object[] colObj1 = {"#", "ROWNUM", new BigDecimal("5"), ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {"Document#", "MAINT_AGMT_VER_NUM", new BigDecimal("50"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        // set the sort parameters.
        // {"column name(physical)", sort order(ASC/DESC)}
        Object[] sortObj1 = {"MAINT_AGMT_VER_NUM", "ASC" };

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL() {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT");
        sb.append(NEWLINE).append("          ROWNUM");
        sb.append(NEWLINE).append("        , AV.MAINT_AGMT_VER_NUM");
        sb.append(NEWLINE).append("        , GLBL_CMPY_CD");
        sb.append(NEWLINE).append("        , EZCANCELFLAG");
        sb.append(NEWLINE).append("    FROM");
        sb.append(NEWLINE).append("        MAINT_AGMT_VER AV");
        sb.append(NEWLINE).append("    WHERE");
        sb.append(NEWLINE).append("        AV.GLBL_CMPY_CD          = '#GLBL_CMPY_CD#'");
        sb.append(NEWLINE).append("        AND AV.EZCANCELFLAG      = '0'");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());

        return sql;
    }
}
