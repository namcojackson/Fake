/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8840Scrn00_OpenWin_CmnBigAssignee
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/02   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840Scrn00_OpenWin_CmnBigAssignee extends S21CommonHandler {

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

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        //TODO [Template]
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
        //TODO [Template] set the values
        params[i++] = "";
        params[i++] = "Assignee Search";
        params[i++] = getSelectSQL(scrnMsg);
        params[i++] = getSearchConditionSetting(scrnMsg, scrnMsg.xxWfAsgCd.getValue());
        params[i++] = getDisplayColumnSetting(scrnMsg);
        params[i++] = getSortSetting(scrnMsg);
        params[i] = scrnMsg.P;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NYEL8840BMsg scrnMsg, String asgCd) {
        List<Object> whereList = new ArrayList<Object>();

        //TODO [Template] set the search condition parameters.
        //TODO [Template] {"display name", "column name(physical)", value, use LIKE flag}
        Object[] whereObj1 = {"Assignee Code", "USR_NM", asgCd, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Last Name", "LAST_NM", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"First Name", "FIRST_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NYEL8840BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        //TODO [Template] set the search results parameters.
        //TODO [Template] {"display name", "column name(physical)", column size, selectable flag}
        Object[] colObj1 = {"Assignee Code", "USR_NM", new BigDecimal("16"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Last Name", "LAST_NM", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {"First Name", "FIRST_NM", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);

        return colList;
    }

    private List<Object> getSortSetting(NYEL8840BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        //TODO [Template] set the sort parameters.
        //TODO [Template] {"column name(physical)", sort order(ASC/DESC)}
        Object[] sortObj1 = {"USR_NM", "ASC" };

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NYEL8840BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();

        //TODO [Template] create the query statement
        sb.append("SELECT");
        sb.append(NEWLINE).append("          USR_NM");
        sb.append(NEWLINE).append("        , LAST_NM");
        sb.append(NEWLINE).append("        , FIRST_NM");
        sb.append(NEWLINE).append("        , GLBL_CMPY_CD");
        sb.append(NEWLINE).append("        , EZCANCELFLAG");
        sb.append(NEWLINE).append("    FROM");
        sb.append(NEWLINE).append("        AUTH_PSN");
        //TODO [Template] if necessary, add the search criteria

        String sql = sb.toString();

        return sql;
    }
}
