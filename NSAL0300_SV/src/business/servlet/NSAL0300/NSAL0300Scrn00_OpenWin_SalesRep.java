/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/03/29   Hitachi         M.Gotou         Update          QC5248
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#4668
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_SalesRep extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // START 2016/04/26 T.Tomita [QC#4668, MOD]
        setArgForSubScreen(NSAL0300CommonLogic.setSalesRepCommonPopUpParam(scrnMsg, getGlobalCompanyCode(), scrnMsg.tocNm.getValue()));
        // END 2016/04/26 T.Tomita [QC#4668, MOD]
    }

    // START 2016/04/26 T.Tomita [QC#4668, DEL]
//    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0300BMsg scrnMsg) {
//        scrnMsg.xxScrEventNm.setValue("OpenWin_SalesRep");
//        ZYPTableUtil.clear(scrnMsg.R);
//        Object[] params = new Object[7];
//
//        int i = 0;
//        // Return value suffix
//        params[i++] = "";
//        // Window title
//        params[i++] = "Sales Rep Popup";
//        // Table SQL
//        // mod start 2016/03/29 CSA Defect#5248
//        //params[i++] = "S21_ORG";
//        params[i++] = getSelectSQL(scrnMsg);
//        // mod end 2016/03/29 CSA Defect#5248
//        // Where List
//        params[i++] = getWhereList(scrnMsg);
//        // Column List
//        List<Object[]> columnList = getColumnList();
//        params[i++] = columnList;
//        // Sort Condition List
//        params[i++] = getSortConditionList();
//        // outPut List
//        params[i++] = scrnMsg.R;
//
//        return params;
//    }
//
//// add start 2016/03/29 CSA Defect#5248
//    private String getSelectSQL(NSAL0300BMsg scrnMsg) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("SELECT");
//        sb.append("      A.GLBL_CMPY_CD    AS GLBL_CMPY_CD");
//        sb.append("     ,A.EZCANCELFLAG    AS EZCANCELFLAG");
//        sb.append("     ,A.TOC_CD          AS TOC_CD");
//        sb.append("     ,A.TOC_NM          AS TOC_NM");
//        sb.append(" FROM");
//        sb.append("      S21_ORG    A");
//        sb.append(" WHERE");
//        sb.append("     A.GLBL_CMPY_CD     = '#glblCmpyCd#'");
//        sb.append(" AND A.EZCANCELFLAG     = '0'");
//        sb.append(" AND A.RGTN_STS_CD      = '#rgtnSts#'");
//
//        String sql = sb.toString();
//        sql = sql.replaceAll("#glblCmpyCd#", getGlobalCompanyCode());
//        sql = sql.replaceAll("#rgtnSts#", RGTN_STS.READY_FOR_ORDER_TAKING);
//        return sql;
//    }
//// add end 2016/03/29 CSA Defect#5248
//
//    // START 2016/02/24 T.Kanasaka [QC4079, MOD]
//    private List<Object[]> getWhereList(NSAL0300BMsg scrnMsg) {
//        String tocCd = scrnMsg.tocCd.getValue();
//        String tocNm = scrnMsg.tocNm.getValue();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
//        h0[WLIST_DSP_OBJ_NM] = "Sales Rep Code";
//        h0[WLIST_OBJ_ID] = "TOC_CD";
//        h0[WLIST_OBJ_VALUE] = tocCd;
//        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h0);
//
//        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
//        h1[WLIST_DSP_OBJ_NM] = "Sales Rep Name";
//        h1[WLIST_OBJ_ID] = "TOC_NM";
//        h1[WLIST_OBJ_VALUE] = tocNm;
//        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h1);
//
//        return whereList;
//    }
//
//    private List<Object[]> getColumnList() {
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//
//        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
//        c0[CLIST_DSP_OBJ_NM] = "Sales Rep Code";
//        c0[CLIST_OBJ_ID] = "TOC_CD";
//        c0[CLIST_OBJ_LENGTH] = new BigDecimal(TOC_CD_LENGTH);
//        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
//        columnList.add(c0);
//
//        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
//        c1[CLIST_DSP_OBJ_NM] = "Sales Rep Name";
//        c1[CLIST_OBJ_ID] = "TOC_NM";
//        c1[CLIST_OBJ_LENGTH] = new BigDecimal(TOC_NM_LENGTH);
//        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
//        columnList.add(c1);
//
//        return columnList;
//    }
//    // END 2016/02/24 T.Kanasaka [QC4079, MOD]
//
//    private List<Object[]> getSortConditionList() {
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray = new Object[2];
//        sortConditionArray[0] = "TOC_CD";
//        sortConditionArray[1] = "ASC";
//        sortConditionList.add(sortConditionArray);
//
//        return sortConditionList;
//    }
    // END 2016/04/26 T.Tomita [QC#4668, DEL]
}
