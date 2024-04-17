/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_OpenWin_UserName
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         Q09079          Create          N/A
 * 2018/10/10   Fujitsu         Q10627          Update          QC#28705
 *</pre>
 */
public class NYEL8810Scrn00_OpenWin_UserName extends S21CommonHandler {

    /** parameter count of Common BIG Popup */
    private static final int PRM_CNT = 7;

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

        params[i++] = "";
        params[i++] = "UserName Search";
        if (NYXC880002.isAdministrator(this.getContextUserInfo().getUserId())){
            params[i++] = getSelectSQLForAdmin(scrnMsg);
        } else {
            params[i++] = getSelectSQL(scrnMsg);
        }
        params[i++] = getSearchConditionSetting(scrnMsg);
        params[i++] = getDisplayColumnSetting(scrnMsg);
        params[i++] = getSortSetting(scrnMsg);
        params[i] = scrnMsg.Q;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NYEL8810BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"User Id", "USR_ID", "%", ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Last Name", "LAST_NM", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"First Name", "FIRST_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NYEL8810BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"User Id", "USR_ID", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Last Name", "LAST_NM", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {"First Name", "FIRST_NM", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);

        return colList;
    }

    private List<Object> getSortSetting(NYEL8810BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"USR_ID", "ASC" };

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQLForAdmin(NYEL8810BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT");
        sb.append("          USR_NM USR_ID");
        sb.append("        , LAST_NM");
        sb.append("        , FIRST_NM");
        sb.append("        , GLBL_CMPY_CD");
        sb.append("        , EZCANCELFLAG");
        sb.append("    FROM");
        sb.append("        AUTH_PSN");
        /*
        sb.append("SELECT");
        sb.append("  U.WF_USR_ID USR_ID");
        sb.append("  , P.LAST_NM");
        sb.append("  , P.FIRST_NM");
        sb.append("  , U.GLBL_CMPY_CD");
        sb.append("  , U.EZCANCELFLAG ");
        sb.append("FROM");
        sb.append("  WF_USR U");
        sb.append("  , AUTH_PSN P ");
        sb.append("WHERE");
        sb.append("  U.WF_USR_ID = P.USR_NM ");
        sb.append("  AND U.GLBL_CMPY_CD = P.GLBL_CMPY_CD ");
        sb.append("  AND U.EZCANCELFLAG = P.EZCANCELFLAG ");
        sb.append("GROUP BY");
        sb.append("  U.WF_USR_ID");
        sb.append("  , P.LAST_NM");
        sb.append("  , P.FIRST_NM");
        sb.append("  , U.GLBL_CMPY_CD");
        sb.append("  , U.EZCANCELFLAG ");
        sb.append("ORDER BY");
        sb.append("  U.WF_USR_ID");
        */

        String sql = sb.toString();

        return sql;
    }
    
    private String getSelectSQL(NYEL8810BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();

// QC#28705 MOD START 2018/10/10
//        sb.append("  SELECT");
//        sb.append("    UG1.USR_ID");
//        sb.append("    , P.LAST_NM");
//        sb.append("    , P.FIRST_NM ");
//        sb.append("    , P.GLBL_CMPY_CD ");
//        sb.append("    , P.EZCANCELFLAG ");
//        sb.append("  FROM");
//        sb.append("    WF_UTIL_USR_GRP UG1");
//        sb.append("    , AUTH_PSN P ");
//        sb.append("  WHERE");
//        sb.append(String.format("    UG1.GLBL_CMPY_CD = '%s'", this.getGlobalCompanyCode()));
//        sb.append("    AND P.GLBL_CMPY_CD = UG1.GLBL_CMPY_CD ");
//        sb.append("    AND UG1.EZCANCELFLAG = '0' ");
//        sb.append("    AND P.EZCANCELFLAG = '0' ");
//        sb.append("    AND P.USR_NM = UG1.USR_ID ");
//        sb.append("    AND (");
//        sb.append(String.format("       UG1.USR_ID = '%s' ", this.getContextUserInfo().getUserId()));
//        sb.append("    OR UG1.WF_USR_LVL_CD < ( ");
//        sb.append("      SELECT");
//        sb.append("        UG2.WF_USR_LVL_CD ");
//        sb.append("      FROM");
//        sb.append("        WF_UTIL_USR_GRP UG2 ");
//        sb.append("      WHERE");
//        sb.append(String.format("        UG2.GLBL_CMPY_CD = '%s' ", this.getGlobalCompanyCode()));
//        sb.append("        AND UG2.EZCANCELFLAG = '0' ");
//        sb.append(String.format("        AND UG2.USR_ID = '%s' ", this.getContextUserInfo().getUserId()));
//        sb.append("        AND ROWNUM = 1");
//        sb.append("     )");
//        sb.append("    ) ");
//        sb.append("  GROUP BY");
//        sb.append("    UG1.USR_ID");
//        sb.append("    , P.FIRST_NM");
//        sb.append("    , P.LAST_NM ");
//        sb.append("    , P.GLBL_CMPY_CD ");
//        sb.append("    , P.EZCANCELFLAG ");
//        sb.append("  ORDER BY");
//        sb.append("    UG1.USR_ID");
        sb.append("  SELECT");
        sb.append("    P.USR_NM USR_ID");
        sb.append("    , P.LAST_NM");
        sb.append("    , P.FIRST_NM ");
        sb.append("    , P.GLBL_CMPY_CD ");
        sb.append("    , P.EZCANCELFLAG ");
        sb.append("  FROM");
        sb.append("    AUTH_PSN P ");
        sb.append("  WHERE");
        sb.append(String.format("        P.GLBL_CMPY_CD = '%s' ", this.getGlobalCompanyCode()));
        sb.append(String.format("    AND P.USR_NM = '%s' ", this.getContextUserInfo().getUserId()));
        sb.append("    AND P.EZCANCELFLAG = '0' ");
// QC#28705 MOD END   2018/10/10

        String sql = sb.toString();

        return sql;
    }
}
