/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0010E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_OpenWin_CmnBigAssignee
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/01   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_OpenWin_CmnBigAssignee extends S21CommonHandler {

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
        
        
        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        //TODO [Template]
        scrnMsg.Q.clear(); // parameter area for Common BIG Popup

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
        params[i] = scrnMsg.Q;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NYEL8810BMsg scrnMsg, String asgCd) {
        List<Object> whereList = new ArrayList<Object>();

        //TODO [Template] set the search condition parameters.
        //TODO [Template] {"display name", "column name(physical)", value, use LIKE flag}
        Object[] whereObj1;
        if (S21StringUtil.isEmpty(asgCd)){
            whereObj1 = new Object[]{"Assignee Code", "USR_NM", "%", ZYPConstant.FLG_ON_Y };    
        } else {
            whereObj1 = new Object[]{"Assignee Code", "USR_NM", asgCd, ZYPConstant.FLG_ON_Y };
        }

        Object[] whereObj2 = {"Last Name", "LAST_NM", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"First Name", "FIRST_NM", null, ZYPConstant.FLG_ON_Y };
        //Object[] whereObj2 = {"Last Name", "LAST_NM", null, ZYPConstant.FLG_ON_Y };
        //Object[] whereObj3 = {"First Name", "FIRST_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NYEL8810BMsg scrnMsg) {
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

    private List<Object> getSortSetting(NYEL8810BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        //TODO [Template] set the sort parameters.
        //TODO [Template] {"column name(physical)", sort order(ASC/DESC)}
        Object[] sortObj1 = {"USR_NM", "ASC" };

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NYEL8810BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();
        String wrkItemPk = getWorkItemPk(scrnMsg);

        //TODO [Template] create the query statement
        //sb.append("WITH RECV_REASG_LIST AS (");
        sb.append("SELECT");
        sb.append(NEWLINE).append("          P.USR_NM");
        sb.append(NEWLINE).append("        , P.LAST_NM");
        sb.append(NEWLINE).append("        , P.FIRST_NM");
        sb.append(NEWLINE).append("        , P.GLBL_CMPY_CD");
        sb.append(NEWLINE).append("        , P.EZCANCELFLAG");
        sb.append(NEWLINE).append("FROM");
        sb.append(NEWLINE).append("          WF_USR U");
        sb.append(NEWLINE).append("        , AUTH_PSN P");
        sb.append(NEWLINE).append("WHERE");
        sb.append(NEWLINE).append("          U.WF_USR_ID=P.USR_NM");
        sb.append(NEWLINE).append("      AND U.WF_USR_ACCS_RCV_RASG_FLG='Y'");
        if (S21StringUtil.isNotEmpty(wrkItemPk)){
            sb.append(NEWLINE).append(String.format("AND        U.WF_WRK_ITEM_PK IN (%s)", wrkItemPk));
        }
        sb.append(NEWLINE).append("      AND U.EZCANCELFLAG='0'");
        sb.append(NEWLINE).append("      AND P.EZCANCELFLAG='0'");
        sb.append(NEWLINE).append(String.format("    AND U.GLBL_CMPY_CD='%s'", getGlobalCompanyCode()));
        sb.append(NEWLINE).append(String.format("    AND P.GLBL_CMPY_CD='%s'", getGlobalCompanyCode()));
        sb.append(NEWLINE).append("      GROUP BY");
        sb.append(NEWLINE).append("            P.USR_NM");
        sb.append(NEWLINE).append("            , P.LAST_NM");
        sb.append(NEWLINE).append("            , P.FIRST_NM");
        sb.append(NEWLINE).append("            , P.GLBL_CMPY_CD");
        sb.append(NEWLINE).append("            , P.EZCANCELFLAG");
        sb.append(NEWLINE).append("      ORDER BY P.USR_NM");
        /*
        sb.append(NEWLINE).append(")");
        sb.append(NEWLINE).append("SELECT");
        sb.append(NEWLINE).append("          USR_NM");
        sb.append(NEWLINE).append("        , LAST_NM");
        sb.append(NEWLINE).append("        , FIRST_NM");
        sb.append(NEWLINE).append("        , GLBL_CMPY_CD");
        sb.append(NEWLINE).append("        , EZCANCELFLAG");
        sb.append(NEWLINE).append("    FROM");
        sb.append(NEWLINE).append("        RECV_REASG_LIST");
        */
//        sb.append(NEWLINE).append("    WHERE");
//        sb.append(NEWLINE).append("        GLBL_CMPY_CD          = '#GLBL_CMPY_CD#'");
//        sb.append(NEWLINE).append("        AND EZCANCELFLAG      = '0'");
        //TODO [Template] if necessary, add the search criteria

        String sql = sb.toString();
//        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());

        return sql;
    }
    
    private String getWorkItemPk(NYEL8810BMsg scrnMsg){
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = scrnMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                scrnMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return "";
        }

        StringBuilder sb = new StringBuilder();
        
        for (int index : selectedRows){
            if (sb.length() > 0){
                sb.append(",");
            }
            sb.append(scrnMsg.A.no(index).wfWrkItemPk_A.getValue().toPlainString());
        }
        
        return sb.toString();
    }
}
