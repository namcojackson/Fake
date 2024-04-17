/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/07   Fujitsu         A.Kosai         Create          QC#22482
 *</pre>
 */
public class NSAL1320Scrn00_OpenWin_Override_Reason extends S21CommonHandler {

    /** parameter count of Common BIG Popup */
    private static final int PRM_CNT = 7;

    /** line separator for SQL */
    private static final String NEWLINE = System.getProperty("line.separator");

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        scrnMsg.O.clear();

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
        params[i++] = "Override Reason Search Popup";
        params[i++] = getSelectSQL();
        params[i++] = getSearchConditionSetting();
        params[i++] = getDisplayColumnSetting();
        params[i++] = getSortSetting();
        params[i] = scrnMsg.O;

        setArgForSubScreen(params);
    }

    private String getSelectSQL() {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT");
        sb.append(NEWLINE).append("          R.GLBL_CMPY_CD");
        sb.append(NEWLINE).append("        , R.SVC_MEMO_RSN_CD");
        sb.append(NEWLINE).append("        , R.SVC_MEMO_RSN_DESC_TXT");
        sb.append(NEWLINE).append("        , R.SVC_MEMO_RSN_SORT_NUM");
        sb.append(NEWLINE).append("        , R.EZCANCELFLAG");
        sb.append(NEWLINE).append("    FROM");
        sb.append(NEWLINE).append("        SVC_MEMO_RSN R");
        sb.append(NEWLINE).append("    WHERE");
        sb.append(NEWLINE).append("        R.GLBL_CMPY_CD         = '#GLBL_CMPY_CD#'");
        sb.append(NEWLINE).append("        AND R.SVC_MEMO_TP_CD   = '#SVC_MEMO_TP_CD#'");
        sb.append(NEWLINE).append("        AND R.EZCANCELFLAG     = '0'");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());
        sql = sql.replaceAll("#SVC_MEMO_TP_CD#", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);

        return sql;
    }

    private List<Object> getSearchConditionSetting() {

        List<Object> whereList = new ArrayList<Object>();

        // set the search condition parameters.
        // {"display name", "column name(physical)", value, use LIKE flag}
        whereList.add(new Object[] {"Reason Code", "SVC_MEMO_RSN_CD", "%", ZYPConstant.FLG_ON_Y});
        whereList.add(new Object[] {"Reason Name", "SVC_MEMO_RSN_DESC_TXT", null, ZYPConstant.FLG_ON_Y});

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {

        List<Object> colList = new ArrayList<Object>();

        // set the search results parameters.
        // {"display name", "column name(physical)", column size, selectable flag}
        colList.add(new Object[] {"Reason Code", "SVC_MEMO_RSN_CD", new BigDecimal("30"), ZYPConstant.FLG_OFF_N});
        colList.add(new Object[] {"Reason Name", "SVC_MEMO_RSN_DESC_TXT", new BigDecimal("30"), ZYPConstant.FLG_ON_Y});

        return colList;
    }

    private List<Object> getSortSetting() {

        List<Object> sortList = new ArrayList<Object>();

        // set the sort parameters.
        // {"column name(physical)", sort order(ASC/DESC)}
        sortList.add(new Object[] {"SVC_MEMO_RSN_SORT_NUM", "ASC"});

        return sortList;
    }
}
