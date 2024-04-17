/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1290.common;

import static business.servlet.NSAL1290.constant.NSAL1290Constant.AUTH_UPDATE;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_APPLY;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_APPROVE;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_CLEAR;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_DELETE;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_REJECT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_RESET;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_SAVE;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_NM_ADD_LINE;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_NM_DELETE_LINE;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_NM_OPEN_WIN_BLLG_COUNTER;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_NM_OPEN_WIN_REG_COUNTER;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_NM_SEARCH;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BUSINESS_ID;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_DISP_NM_BLLG_CNT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_DISP_NM_MTR_LB_DESC_TXT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_DISP_NM_REG_CNT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_IDX_CD;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_LB_CD;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_LB_DESC_TXT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_LB_NM;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_LB_TP_CD;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_WIDTH_MTR_LB_DESC_TXT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_WIDTH_MTR_LB_NM;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.PRM_LENGTH_NWAL1130;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.SCREEN_NM;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.SCRN_ID;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.WINDOW_TITLE_NWAL1130_BLLG;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.WINDOW_TITLE_NWAL1130_REG;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1290.NSAL1290BMsg;
import business.servlet.NSAL1290.NSAL1290Bean;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Billing Counter Mapping Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL1290CommonLogic {
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1290BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1290BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL1290BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL1290BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        // START 2017/01/20 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        // END 2017/01/20 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1290BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL1290BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenTableFields(handler, scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate " + SCREEN_NM + " (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1290BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL1290BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_NM_OPEN_WIN_REG_COUNTER, true);
        scrnMsg.mtrLbNm_H.setInputProtected(false);
        handler.setButtonEnabled(BTN_NM_SEARCH, true);
        handler.setButtonEnabled(BTN_NM_ADD_LINE, false);
        handler.setButtonEnabled(BTN_NM_DELETE_LINE, false);
        if (hasUpdateFuncId() && hasValue(scrnMsg.mtrIdxCd_H)) {
            scrnMsg.mtrLbNm_H.setInputProtected(true);
            handler.setButtonEnabled(BTN_NM_ADD_LINE, true);
            handler.setButtonEnabled(BTN_NM_DELETE_LINE, true);
        }

    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSAL1290BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSAL1290BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (hasValue(scrnMsg.A.no(i).bllgMtrMapPk_A)) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(i).mtrLbNm_BC.setInputProtected(true);
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_BLLG_COUNTER, i, false);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).mtrLbNm_BC.setInputProtected(false);
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_BLLG_COUNTER, i, true);
            }

            if (hasUpdateFuncId()) {
                scrnMsg.A.no(i).bllgMtrMapLvlNum_A.setInputProtected(false);
                scrnMsg.A.no(i).actvFlg_A.setInputProtected(false);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).mtrLbNm_BC.setInputProtected(true);
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_BLLG_COUNTER, i, false);
                scrnMsg.A.no(i).bllgMtrMapLvlNum_A.setInputProtected(true);
                scrnMsg.A.no(i).actvFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            }
        }
        S21TableColorController control = new S21TableColorController(SCRN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * addCheckItem (header)
     * 
     * @param scrnMsg NSAL1290BMsg
     */
    public static void addCheckItemHeader(NSAL1290BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mtrLbNm_H);
    }

    /**
     * addCheckItem (detail list)
     * 
     * @param scrnMsg NSAL1290BMsg
     */
    public static void addCheckItemDetailList(NSAL1290BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NSAL1290Bean.mtrLbNm_BC, NSAL1290Bean.bllgMtrMapLvlNum_A, NSAL1290Bean.actvFlg_A, NSAL1290Bean.effFromDt_A, NSAL1290Bean.effThruDt_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * createMtrLbListPopupParameter
     * 
     * @param scrnMsg NSAL1290BMsg
     * @param mtrLbNm mtrLbNm
     * @param mtrLbDescTxt mtrLbDescTxt
     * @param mtrLbTpCd mtrLbTpCd
     * @return Object[]
     */
    public static Object[] createMtrLbListPopupParameter(NSAL1290BMsg scrnMsg, String mtrLbNm, String mtrLbDescTxt, String mtrLbTpCd) {
        ZYPTableUtil.clear(scrnMsg.P);
        Object[] params = new Object[PRM_LENGTH_NWAL1130];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = WINDOW_TITLE_NWAL1130_REG;
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getMtrLbListSqlStr(scrnMsg.glblCmpyCd.getValue(), scrnMsg.slsDt.getValue(), mtrLbTpCd);
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getMtrLbListWhereList(mtrLbNm, mtrLbDescTxt, NWAL1130_DISP_NM_REG_CNT);
        // 4 : Lv2 : Column List (List: Search result columns)
        params[i++] = getMtrLbListColumnList(NWAL1130_DISP_NM_REG_CNT);
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getMtrLbListSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.P;
        return params;
    }

    /**
     * createBllgMtrListPopupParameter
     * 
     * @param scrnMsg NSAL1290BMsg
     * @param mtrLbNm mtrLbNm
     * @param mtrLbDescTxt mtrLbDescTxt
     * @param mtrLbTpCd mtrLbTpCd
     * @param mtrIdxCd mtrIdxCd
     * @return Object[]
     */
    public static Object[] createBllgMtrListPopupParameter(NSAL1290BMsg scrnMsg, String mtrLbNm, String mtrLbDescTxt, String mtrLbTpCd, String mtrIdxCd) {
        Object[] params = new Object[PRM_LENGTH_NWAL1130];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = WINDOW_TITLE_NWAL1130_BLLG;
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getBllgMtrListSqlStr(scrnMsg.glblCmpyCd.getValue(), scrnMsg.slsDt.getValue(), mtrLbTpCd, mtrIdxCd);
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getMtrLbListWhereList(mtrLbNm, mtrLbDescTxt, NWAL1130_DISP_NM_BLLG_CNT);
        // 4 : Lv2 : Column List (List: Search result columns)
        params[i++] = getMtrLbListColumnList(NWAL1130_DISP_NM_BLLG_CNT);
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getMtrLbListSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.P;
        return params;
    }

    private static String getMtrLbListSqlStr(String glblCmpyCd, String slsDt, String mtrLbTpCd) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ");
        sb.append("     T.GLBL_CMPY_CD");
        sb.append("    ,T.EZCANCELFLAG");
        sb.append("    ,T.MTR_LB_CD");
        sb.append("    ,T.MTR_LB_NM");
        sb.append("    ,T.MTR_LB_SORT_NUM");
        sb.append("    ,T.MTR_LB_DESC_TXT");
        sb.append("    ,T.MTR_LB_TP_CD");
        sb.append("    ,T.FLEET_MTR_LB_CD");
        sb.append("    ,T.AGGR_MTR_LB_CD");
        sb.append("    ,T.MTR_IDX_CD");
        sb.append("    ,T.BLLG_MTR_LVL_NUM");
        sb.append("    ,T.ACTV_FLG");
        sb.append("    ,T.EFF_FROM_DT");
        sb.append("    ,T.EFF_THRU_DT");
        sb.append("    ,T.BW_MTR_FLG");
        sb.append("    ,T.COLOR_MTR_FLG");
        sb.append("    ,T.MTR_CNTR_ID");
        sb.append("    ,T.CORP_ADVTG_FLG");
        sb.append("    ,T.TOT_MTR_FLG");
        sb.append("    ,T.DEF_START_MTR_CNT");
        sb.append("    ,T.INTG_MDSE_CD");
        sb.append(" FROM MTR_LB T");
        sb.append(" WHERE");
        sb.append("         T.GLBL_CMPY_CD  = 'glblCmpyCd' ");
        sb.append("     AND T.EFF_FROM_DT  <= 'slsDt' ");
        sb.append("     AND (T.EFF_THRU_DT >= 'slsDt' ");
        sb.append("       OR T.EFF_THRU_DT IS NULL) ");
        sb.append("     AND T.ACTV_FLG      = 'Y' ");
        sb.append("     AND T.MTR_LB_TP_CD  = 'mtrLbTpCd'");
        sb.append("     AND T.EZCANCELFLAG  = '0' ");

        String sql = sb.toString();
        sql = sql.replaceAll("glblCmpyCd", glblCmpyCd);
        sql = sql.replaceAll("slsDt", slsDt);
        sql = sql.replaceAll("mtrLbTpCd", mtrLbTpCd);

        return sql;
    }

    private static String getBllgMtrListSqlStr(String glblCmpyCd, String slsDt, String mtrLbTpCd, String mtrIdxCd) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ");
        sb.append("     T.GLBL_CMPY_CD");
        sb.append("    ,T.EZCANCELFLAG");
        sb.append("    ,T.MTR_LB_CD");
        sb.append("    ,T.MTR_LB_NM");
        sb.append("    ,T.MTR_LB_SORT_NUM");
        sb.append("    ,T.MTR_LB_DESC_TXT");
        sb.append("    ,T.MTR_LB_TP_CD");
        sb.append("    ,T.FLEET_MTR_LB_CD");
        sb.append("    ,T.AGGR_MTR_LB_CD");
        sb.append("    ,T.MTR_IDX_CD");
        sb.append("    ,T.BLLG_MTR_LVL_NUM");
        sb.append("    ,T.ACTV_FLG");
        sb.append("    ,T.EFF_FROM_DT");
        sb.append("    ,T.EFF_THRU_DT");
        sb.append("    ,T.BW_MTR_FLG");
        sb.append("    ,T.COLOR_MTR_FLG");
        sb.append("    ,T.MTR_CNTR_ID");
        sb.append("    ,T.CORP_ADVTG_FLG");
        sb.append("    ,T.TOT_MTR_FLG");
        sb.append("    ,T.DEF_START_MTR_CNT");
        sb.append("    ,T.INTG_MDSE_CD");
        sb.append(" FROM MTR_LB T");
        sb.append(" WHERE");
        sb.append("         T.GLBL_CMPY_CD  = 'glblCmpyCd' ");
        sb.append("     AND T.EFF_FROM_DT  <= 'slsDt' ");
        sb.append("     AND (T.EFF_THRU_DT >= 'slsDt' ");
        sb.append("       OR T.EFF_THRU_DT IS NULL) ");
        sb.append("     AND T.ACTV_FLG      = 'Y' ");
        sb.append("     AND T.MTR_LB_TP_CD  = 'mtrLbTpCd'");
        sb.append("     AND T.MTR_IDX_CD    = 'mtrIdxCd' ");
        sb.append("     AND T.EZCANCELFLAG  = '0' ");

        String sql = sb.toString();
        sql = sql.replaceAll("glblCmpyCd", glblCmpyCd);
        sql = sql.replaceAll("slsDt", slsDt);
        sql = sql.replaceAll("mtrLbTpCd", mtrLbTpCd);
        sql = sql.replaceAll("mtrIdxCd", mtrIdxCd);

        return sql;
    }

    private static List<Object[]> getMtrLbListWhereList(String mtrLbNm, String mtrLbDescTxt, String dispNm) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        // Regular Counter
        whereList.add(createAttributeNameWhereList(dispNm, NWAL1130_SQL_NM_MTR_LB_NM, mtrLbNm, ZYPConstant.FLG_ON_Y));

        // Counter Description
        whereList.add(createAttributeNameWhereList(NWAL1130_DISP_NM_MTR_LB_DESC_TXT, NWAL1130_SQL_NM_MTR_LB_DESC_TXT, mtrLbDescTxt, ZYPConstant.FLG_ON_Y));

        return whereList;
    }

    private static List<Object[]> getMtrLbListColumnList(String dispNm) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        // Regular Counter
        columnList.add(createAttributeNameColumnList(dispNm, NWAL1130_SQL_NM_MTR_LB_NM, NWAL1130_WIDTH_MTR_LB_NM, ZYPConstant.FLG_ON_Y));

        // Counter Description
        columnList.add(createAttributeNameColumnList(NWAL1130_DISP_NM_MTR_LB_DESC_TXT, NWAL1130_SQL_NM_MTR_LB_DESC_TXT, NWAL1130_WIDTH_MTR_LB_DESC_TXT, ZYPConstant.FLG_OFF_N));

        // MTR_LB_CD (hidden)
        columnList.add(createAttributeNameColumnList(NWAL1130_SQL_NM_MTR_LB_CD, NWAL1130_SQL_NM_MTR_LB_CD, 0, ZYPConstant.FLG_OFF_N));

        // MTR_LB_TP_CD (hidden)
        columnList.add(createAttributeNameColumnList(NWAL1130_SQL_NM_MTR_LB_TP_CD, NWAL1130_SQL_NM_MTR_LB_TP_CD, 0, ZYPConstant.FLG_OFF_N));

        // MTR_IDX_CD (hidden)
        columnList.add(createAttributeNameColumnList(NWAL1130_SQL_NM_MTR_IDX_CD, NWAL1130_SQL_NM_MTR_IDX_CD, 0, ZYPConstant.FLG_OFF_N));

       return columnList;
    }

    private static List<Object[]> getMtrLbListSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        sortConditionList.add(createAttributeNameSortList("MTR_LB_SORT_NUM", "ASC"));
        return sortConditionList;
    }

    private static Object[] createAttributeNameWhereList(String objNm, String objId, String objValue, String whereFlag) {
        List<Object> list = new ArrayList<Object>();
        list.add(objNm);
        list.add(objId);
        list.add(objValue);
        list.add(whereFlag);

        return list.toArray(new Object[list.size()]);
    }

    private static Object[] createAttributeNameColumnList(String objNm, String objId, int objLength, String whereFlag) {
        List<Object> list = new ArrayList<Object>();
        list.add(objNm);
        list.add(objId);
        list.add(BigDecimal.valueOf(objLength));
        list.add(whereFlag);

        return list.toArray(new Object[list.size()]);
    }

    private static Object[] createAttributeNameSortList(String objNm, String cond) {
        List<Object> list = new ArrayList<Object>();
        list.add(objNm);
        list.add(cond);

        return list.toArray(new Object[list.size()]);
    }
}
