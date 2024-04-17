/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.BIZ_ID;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NWAL1130_POPUP_PRM_NUM;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.OPENWIN_RQSTHIST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2710.common.NMAL2710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2710Scrn00_OpenWin_RqstHist
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2016/07/13   Fujitsu         R.Nakamura      Update          QC#11069
 *</pre>
 */
public class NMAL2710Scrn00_OpenWin_RqstHist extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL2710CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        // Mod Start 2016/07/13 QC#11069
        String[] params = new String[1];
//        params[POPUP_PRM_NUM_0] = "";
//        params[POPUP_PRM_NUM_1] = POPUP_NM_RQST_HIST_SEARCH_POPUP;
//        params[POPUP_PRM_NUM_2] = getSelectSQL();
//        params[POPUP_PRM_NUM_3] = getSearchConditionSetting();
//        params[POPUP_PRM_NUM_4] = getDisplayColumnSetting();
//        params[POPUP_PRM_NUM_5] = getSortSetting();
//        params[POPUP_PRM_NUM_6] = scrnMsg.P;
        params[0] = BIZ_ID;
        // Mod End 2016/07/13 QC#11069

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, OPENWIN_RQSTHIST);
        setArgForSubScreen(params);
    }

    // Del Start 2016/07/13 QC#11069
//    private List<Object> getSearchConditionSetting() {
//        List<Object> whereList = new ArrayList<Object>();
//
//        // Request ID
//        Object[] whereObj1 = {POPUP_LABEL_NM_RQST_ID, POPUP_COL_NM_RQST_ID, null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj1);
//
//        // Employee ID
//        Object[] whereObj2 = {POPUP_LABEL_NM_EMP_ID, POPUP_COL_NM_EMP_ID, null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj2);
//
//        // Employee Name
//        Object[] whereObj3 = {POPUP_LABEL_NM_EMP_NM, POPUP_COL_NM_EMP_NM, null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj3);
//
//        // Request Date
//        Object[] whereObj4 = {POPUP_LABEL_NM_RQST_DATE, POPUP_COL_NM_RQST_DATE, null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj4);
//
//        // Request Status
//        Object[] whereObj5 = {POPUP_LABEL_NM_RQST_STS, POPUP_COL_NM_RQST_STS, null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj5);
//
//        // Request Type
//        Object[] whereObj6 = {POPUP_LABEL_NM_RQST_TP, POPUP_COL_NM_RQST_TP, null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj6);
//
//        // Mass Update Reason
//        Object[] whereObj7 = {POPUP_LABEL_NM_MASS_UPD_RSN, POPUP_COL_NM_MASS_UPD_RSN, null, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj7);
//
//        return whereList;
//    }
//
//    private List<Object> getDisplayColumnSetting() {
//        List<Object> colList = new ArrayList<Object>();
//
//        // Request ID
//        Object[] colObj1 = {POPUP_LABEL_NM_RQST_ID, POPUP_COL_NM_RQST_ID, BigDecimal.valueOf(8), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj1);
//
//        // Employee ID
//        Object[] colObj2 = {POPUP_LABEL_NM_EMP_ID, POPUP_COL_NM_EMP_ID, BigDecimal.valueOf(8), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj2);
//
//        // Employee Name
//        Object[] colObj3 = {POPUP_LABEL_NM_EMP_NM, POPUP_COL_NM_EMP_NM, BigDecimal.valueOf(21), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj3);
//
//        // Request Date
//        Object[] colObj4 = {POPUP_LABEL_NM_RQST_DATE, POPUP_COL_NM_RQST_DATE, BigDecimal.valueOf(14), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj4);
//
//        // Request Status
//        Object[] colObj5 = {POPUP_LABEL_NM_RQST_STS, POPUP_COL_NM_RQST_STS, BigDecimal.valueOf(10), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj5);
//
//        // Request Type
//        Object[] colObj6 = {POPUP_LABEL_NM_RQST_TP, POPUP_COL_NM_RQST_TP, BigDecimal.valueOf(9), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj6);
//
//        // Mass Update Reason
//        Object[] colObj7 = {POPUP_LABEL_NM_MASS_UPD_RSN, POPUP_COL_NM_MASS_UPD_RSN, BigDecimal.valueOf(22), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj7);
//
//        return colList;
//    }
//
//    private List<Object> getSortSetting() {
//        List<Object> sortList = new ArrayList<Object>();
//
//        Object[] sortObj1 = {POPUP_COL_NM_RQST_DATE, POPUP_SORT_KEY_DESC};
//
//        sortList.add(sortObj1);
//
//        return sortList;
//    }
//
//    private String getSelectSQL() {
//        StringBuffer sb = new StringBuffer();
//        sb.append(" SELECT ");
//        sb.append("    RH.POST_UPD_RQST_HDR_PK AS POST_UPD_RQST_HDR_PK");
//        sb.append("    ,RH.RQST_USR_ID         AS RQST_USR_ID");
//        sb.append("    ,AP.FIRST_NM || ' ' || AP.LAST_NM AS RQST_USR_NM ");
//        sb.append("    ,TO_CHAR( TO_TIMESTAMP(RH.RQST_CRAT_TS, 'YYYYMMDDHH24MISSFF3'),'MM/DD/YYYY HH24:MI:SS') AS RQST_CRAT_TS");
//        sb.append("    ,RH.RQST_RSLT_CMNT_TXT ");
//        sb.append("    ,RH.GLBL_CMPY_CD ");
//        sb.append("    ,RH.EZCANCELFLAG ");
//        sb.append("    ,RT.RQST_RSLT_TP_DESC_TXT ");
//        sb.append("    ,ST.RQST_CRAT_SYS_TP_DESC_TXT ");
//        sb.append(" FROM ");
//        sb.append("     POST_UPD_RQST_HDR RH ");
//        sb.append("    ,AUTH_PSN          AP ");
//        sb.append("    ,RQST_RSLT_TP      RT ");
//        sb.append("    ,RQST_CRAT_SYS_TP  ST ");
//        sb.append(" WHERE  ");
//        sb.append("     RH.GLBL_CMPY_CD        = '").append(getGlobalCompanyCode()).append("'");
//        sb.append(" AND RH.GLBL_CMPY_CD        = AP.GLBL_CMPY_CD(+)");
//        sb.append(" AND RH.EZCANCELFLAG        = '0'");
//        sb.append(" AND AP.EZCANCELFLAG(+)     = '0'");
//        sb.append(" AND RH.RQST_USR_ID         = AP.USR_NM(+)");
//        sb.append(" AND RH.GLBL_CMPY_CD        = RT.GLBL_CMPY_CD(+)");
//        sb.append(" AND RT.EZCANCELFLAG(+)     = '0'");
//        sb.append(" AND RH.RQST_RSLT_TP_CD     = RT.RQST_RSLT_TP_CD(+)");
//        sb.append(" AND RH.GLBL_CMPY_CD        = ST.GLBL_CMPY_CD(+)");
//        sb.append(" AND ST.EZCANCELFLAG(+)     = '0'");
//        sb.append(" AND RH.RQST_CRAT_SYS_TP_CD = ST.RQST_CRAT_SYS_TP_CD(+)");
//        String sql = sb.toString();
//        return sql;
//    }
    // Del End 2016/07/13 QC#11069
}
