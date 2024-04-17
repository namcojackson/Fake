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
 * 2017/01/27   Hitachi         Y.Takeno        Update          QC#17278
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_ReportGrp extends S21CommonHandler {

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
        // START 2017/01/27 [QC#17278, MOD]
        // setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
        setArgForSubScreen(NSAL0300CommonLogic.setReportGrpCommonPopUpParam(scrnMsg));
        // END   2017/01/27 [QC#17278, MOD]
    }

// START 2017/01/27 [QC#17278, DEL]
//    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0300BMsg scrnMsg) {
//        scrnMsg.xxScrEventNm.setValue("OpenWin_ReportGrp");
//        ZYPTableUtil.clear(scrnMsg.R);
//        Object[] params = new Object[7];
//
//        int i = 0;
//        // Return value suffix
//        params[i++] = "";
//        // Window title
//        params[i++] = "Report Group Search Popup";
//        // Table SQL
//        params[i++] = "DS_CONTR_RPT_GRP";
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
//    private List<Object[]> getWhereList(NSAL0300BMsg scrnMsg) {
//        String dsContrRptGrpNum = scrnMsg.dsContrRptGrpNum.getValue();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
//        h0[WLIST_DSP_OBJ_NM] = "Report Group#";
//        h0[WLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_NUM";
//        h0[WLIST_OBJ_VALUE] = dsContrRptGrpNum;
//        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h0);
//
//        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
//        h1[WLIST_DSP_OBJ_NM] = "Description";
//        h1[WLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_DESC_TXT";
//        h1[WLIST_OBJ_VALUE] = "";
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
//        c0[CLIST_DSP_OBJ_NM] = "Report Group#";
//        c0[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_NUM";
//        c0[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_NUM_LENGTH);
//        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
//        columnList.add(c0);
//
//        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
//        c1[CLIST_DSP_OBJ_NM] = "Description";
//        c1[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_DESC_TXT";
//        c1[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_DESC_TXT_LENGTH);
//        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c1);
//
//        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
//        c2[CLIST_DSP_OBJ_NM] = "Start Date";
//        c2[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_START_DT";
//        c2[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_START_DT_LENGTH);
//        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c2);
//
//        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
//        c3[CLIST_DSP_OBJ_NM] = "End Date";
//        c3[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_END_DT";
//        c3[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_END_DT_LENGTH);
//        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c3);
//
//        return columnList;
//    }
//
//    private List<Object[]> getSortConditionList() {
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray = new Object[2];
//        sortConditionArray[0] = "DS_CONTR_RPT_GRP_NUM";
//        sortConditionArray[1] = "ASC";
//        sortConditionList.add(sortConditionArray);
//
//        return sortConditionList;
//    }
// DEL   2017/01/27 [QC#17278, DEL]
}
