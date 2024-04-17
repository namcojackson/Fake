/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1200.common;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_IDX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NSAL1200.NSAL1200BMsg;
import business.servlet.NSAL1200.NSAL1200Bean;
import parts.servletcommon.EZDCommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/26   Hitachi         Y.Takeno        Update          QC#6700
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8877, 8878
 * 2016/07/15   Hitachi         T.Tomita        Update          QC#11811
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193
 * 2017/09/04   Hitachi         T.Kanasaka      Update          QC#15134
 *</pre>
 */
public class NSAL1200CommonLogic {
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1200BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1200BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL1200BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL1200BMsg scrnMsg) {
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
     * @param scrnMsg NSAL1200BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL1200BMsg scrnMsg) {
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
     * @param scrnMsg NSAL1200BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL1200BMsg scrnMsg) {

        if (hasValue(scrnMsg.mtrGrpPk_H)) {
            scrnMsg.mtrGrpNm_H.setInputProtected(true);
        } else {
            scrnMsg.mtrGrpNm_H.setInputProtected(false);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_NM_OPEN_WIN_NSAL0480, true);
            handler.setButtonEnabled(BTN_NM_DELETE_LINE, true);
        } else {
            handler.setButtonEnabled(BTN_NM_OPEN_WIN_NSAL0480, false);
            handler.setButtonEnabled(BTN_NM_DELETE_LINE, false);
        }

        if (!hasUpdateFuncId()) {
            handler.setButtonEnabled(BTN_NM_ADD_LINE, false);
            handler.setButtonEnabled(BTN_NM_DELETE_LINE, false);
        }
    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSAL1200BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSAL1200BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // START 2016/07/15 T.Tomita [QC#11811, MOD]
//            if (hasValue(scrnMsg.A.no(i).dsMdlMtrPk_A)) {
//                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
//
//            } else {
//                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
//            }
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            // END 2016/07/15 T.Tomita [QC#11811, MOD]

            // START 2017/08/03 T.Kanasaka [QC#18193,MOD]
            if (hasUpdateFuncId()) {
                if (!hasValue(scrnMsg.A.no(i).mdlMtrLbCd_A)) {
//                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_DEFAULT_BILLING, i, false);
//                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_FLT_COUNTER, i, false);
//                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_AGG_COUNTER, i, false);
                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL1_COUNTER, i, false);
                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL2_COUNTER, i, false);
                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL3_COUNTER, i, false);

//                    scrnMsg.A.no(i).mtrLbDescTxt_DB.setInputProtected(true);
//                    scrnMsg.A.no(i).mtrLbDescTxt_FL.setInputProtected(true);
//                    scrnMsg.A.no(i).mtrLbDescTxt_AG.setInputProtected(true);
                    scrnMsg.A.no(i).mtrLbDescTxt_L1.setInputProtected(true);
                    scrnMsg.A.no(i).mtrLbDescTxt_L2.setInputProtected(true);
                    scrnMsg.A.no(i).mtrLbDescTxt_L3.setInputProtected(true);
                } else {
//                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_DEFAULT_BILLING, i, true);
//                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_FLT_COUNTER, i, true);
//                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_AGG_COUNTER, i, true);
                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL1_COUNTER, i, true);
                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL2_COUNTER, i, true);
                    handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL3_COUNTER, i, true);

//                    scrnMsg.A.no(i).mtrLbDescTxt_DB.setInputProtected(false);
//                    scrnMsg.A.no(i).mtrLbDescTxt_FL.setInputProtected(false);
//                    scrnMsg.A.no(i).mtrLbDescTxt_AG.setInputProtected(false);
                    scrnMsg.A.no(i).mtrLbDescTxt_L1.setInputProtected(false);
                    scrnMsg.A.no(i).mtrLbDescTxt_L2.setInputProtected(false);
                    scrnMsg.A.no(i).mtrLbDescTxt_L3.setInputProtected(false);
                }

            } else {
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_COUNTER, i, false);
//                handler.setButtonEnabled(BTN_NM_OPEN_WIN_DEFAULT_BILLING, i, false);
//                handler.setButtonEnabled(BTN_NM_OPEN_WIN_FLT_COUNTER, i, false);
//                handler.setButtonEnabled(BTN_NM_OPEN_WIN_AGG_COUNTER, i, false);
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL1_COUNTER, i, false);
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL2_COUNTER, i, false);
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_LVL3_COUNTER, i, false);

                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(i).mtrEntryMndFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).mtrMultRate_A.setInputProtected(true);
                // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
                scrnMsg.A.no(i).cntrDigitNum_A.setInputProtected(true);
                // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
                scrnMsg.A.no(i).techReadMndFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).actvFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            }
            // END 2017/08/03 T.Kanasaka [QC#18193,MOD]

            scrnMsg.A.no(i).mtrMultRate_A.setAppFracDigit(2);
        }
        S21TableColorController control = new S21TableColorController(SCRN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * addCheckItem (header)
     * 
     * @param scrnMsg NSAL1200BMsg
     */
    public static void addCheckItemHeader(NSAL1200BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mtrGrpNm_H);
        scrnMsg.addCheckItem(scrnMsg.mtrGrpTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.mtrGrpDescTxt_H);
    }

    /**
     * addCheckItem (detail list)
     * 
     * @param scrnMsg NSAL1200BMsg
     */
    public static void addCheckItemDetailList(NSAL1200BMsg scrnMsg) {
        // START 2017/08/03 T.Kanasaka [QC#18193,MOD]
//        scrnMsg.A.setCheckParam(new String[] {NSAL1200Bean.mdlMtrLbCd_A, NSAL1200Bean.mtrLbDescTxt_CN, NSAL1200Bean.bllgMtrLbCd_A, NSAL1200Bean.mtrLbDescTxt_DB, NSAL1200Bean.mtrEntryMndFlg_A, NSAL1200Bean.mtrMultRate_A,
//                NSAL1200Bean.fleetMtrLbCd_A, NSAL1200Bean.mtrLbDescTxt_FL, NSAL1200Bean.aggrMtrLbCd_A, NSAL1200Bean.mtrLbDescTxt_AG, NSAL1200Bean.actvFlg_A, NSAL1200Bean.effFromDt_A, NSAL1200Bean.effThruDt_A }, 1);
        // START 2017/09/04 T.Kanasaka [QC#15134,MOD]
//        scrnMsg.A.setCheckParam(new String[] {NSAL1200Bean.mdlMtrLbCd_A, NSAL1200Bean.mtrLbDescTxt_CN, NSAL1200Bean.bllgMtrLbCd_L1, NSAL1200Bean.mtrLbDescTxt_L1, NSAL1200Bean.mtrEntryMndFlg_A, NSAL1200Bean.mtrMultRate_A,
//                NSAL1200Bean.bllgMtrLbCd_L2, NSAL1200Bean.mtrLbDescTxt_L2, NSAL1200Bean.bllgMtrLbCd_L3, NSAL1200Bean.mtrLbDescTxt_L3, NSAL1200Bean.actvFlg_A, NSAL1200Bean.effFromDt_A, NSAL1200Bean.effThruDt_A }, 1);
        scrnMsg.A.setCheckParam(new String[] {NSAL1200Bean.mdlMtrLbCd_A, NSAL1200Bean.mtrLbDescTxt_CN, NSAL1200Bean.bllgMtrLbCd_L1, NSAL1200Bean.mtrLbDescTxt_L1, NSAL1200Bean.mtrEntryMndFlg_A, NSAL1200Bean.mtrMultRate_A,
                NSAL1200Bean.bllgMtrLbCd_L2, NSAL1200Bean.mtrLbDescTxt_L2, NSAL1200Bean.bllgMtrLbCd_L3, NSAL1200Bean.mtrLbDescTxt_L3, NSAL1200Bean.cntrDigitNum_A, NSAL1200Bean.actvFlg_A, NSAL1200Bean.effFromDt_A, NSAL1200Bean.effThruDt_A }, 1);
        // END 2017/09/04 T.Kanasaka [QC#15134,MOD]
        // END 2017/08/03 T.Kanasaka [QC#18193,MOD]
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * createMtrLbListPopupParameter
     * 
     * @param scrnMsg NSAL1200BMsg
     * @param mtrLbTpCd mtrLbTpCd
     * @param mtrIdxCd mtrIdxCd
     * @param mdlMtrLbCd mdlMtrLbCd
     * @param mtrLbDescTxt mtrLbDescTxt
     * @return Object[]
     */
    public static Object[] createMtrLbListPopupParameter(NSAL1200BMsg scrnMsg, String mtrLbTpCd, String mtrIdxCd, String mdlMtrLbCd, String mtrLbDescTxt) {
        Object[] params = new Object[PRM_LENGTH_NWAL1130];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "Counter Pop Up";
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getMtrLbListSqlStr(scrnMsg.glblCmpyCd.getValue(), scrnMsg.slsDt.getValue(), mtrLbTpCd, mtrIdxCd);
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getMtrLbListWhereList(mdlMtrLbCd, mtrLbDescTxt);
        // 4 : Lv2 : Column List (List: Search result columns)
        params[i++] = getMtrLbListColumnList();
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getMtrLbListSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.P;
        return params;
    }

    /**
     * createBllgMtrListPopupParameter
     * 
     * @param scrnMsg NSAL1200BMsg
     * @param mdlMtrLbCd mdlMtrLbCd
     * @param bllgMtrLbCd bllgMtrLbCd
     * @param mtrLbDescTxt mtrLbDescTxt
     * @return Object[]
     */
    public static Object[] createBllgMtrListPopupParameter(NSAL1200BMsg scrnMsg, String mdlMtrLbCd, String bllgMtrLbCd, String mtrLbDescTxt) {
        Object[] params = new Object[PRM_LENGTH_NWAL1130];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "Counter Pop Up";
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getBllgMtrListSqlStr(scrnMsg.glblCmpyCd.getValue(), scrnMsg.slsDt.getValue(), mdlMtrLbCd);
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getMtrLbListWhereList(bllgMtrLbCd, mtrLbDescTxt);
        // 4 : Lv2 : Column List (List: Search result columns)
        // START 2017/08/03 T.Kanasaka [QC#18193,MOD]
//        params[i++] = getMtrLbListColumnList();
        params[i++] = getBllgMtrLbListColumnList();
        // END 2017/08/03 T.Kanasaka [QC#18193,MOD]
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getMtrLbListSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.P;
        return params;
    }


    private static String getMtrLbListSqlStr(String glblCmpyCd, String slsDt, String mtrLbTpCd, String mtrIdxCd) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ");
        sb.append("      M.GLBL_CMPY_CD ");
        sb.append("     ,M.EZCANCELFLAG ");
        sb.append("     ,M.MTR_LB_CD ");
        sb.append("     ,M.MTR_LB_NM ");
        sb.append("     ,M.MTR_LB_DESC_TXT ");
        sb.append("     ,M.MTR_LB_TP_CD ");
        sb.append("     ,M.BLLG_MTR_LVL_NUM ");
        sb.append("     ,M.MTR_LB_SORT_NUM ");
        sb.append(" FROM ");
        sb.append("     MTR_LB M ");
        sb.append(" WHERE ");
        sb.append("         M.GLBL_CMPY_CD  = 'glblCmpyCd' ");
        sb.append("     AND M.EFF_FROM_DT  <= 'slsDt' ");
        sb.append("     AND (M.EFF_THRU_DT >= 'slsDt' ");
        sb.append("       OR M.EFF_THRU_DT IS NULL) ");
        sb.append("     AND M.ACTV_FLG      = 'Y' ");
        sb.append("     AND M.MTR_LB_TP_CD  = 'mtrLbTpCd' ");
        sb.append("     AND M.MTR_IDX_CD    = 'mtrIdxCd' ");
        sb.append("     AND M.EZCANCELFLAG  = '0' ");

        String sql = sb.toString();
        sql = sql.replaceAll("glblCmpyCd", glblCmpyCd);
        sql = sql.replaceAll("slsDt", slsDt);
        sql = sql.replaceAll("mtrLbTpCd", mtrLbTpCd);
        sql = sql.replaceAll("mtrIdxCd", mtrIdxCd);

        return sql;
    }

    // START 2017/08/03 T.Kanasaka [QC#18193,MOD]
    private static String getBllgMtrListSqlStr(String glblCmpyCd, String slsDt, String mdlMtrLbCd) {
        StringBuilder sb = new StringBuilder();
        // mod start 2016/05/26 CSA Defect#8877, 8878
        sb.append(" SELECT ");
        sb.append("      MLB.GLBL_CMPY_CD ");
        sb.append("     ,MLB.EZCANCELFLAG ");
        sb.append("     ,MLB.MTR_LB_CD ");
        sb.append("     ,MLB.MTR_LB_NM ");
        sb.append("     ,MLB.MTR_LB_SORT_NUM ");
        sb.append("     ,MLB.MTR_LB_DESC_TXT ");
        sb.append("     ,MLB.MTR_LB_TP_CD ");
        sb.append("     ,MLB.BLLG_MTR_LVL_NUM ");
        sb.append(" FROM     MTR_LB MLB ");
//        sb.append("         ,BLLG_MTR_MAP MAP ");
        sb.append("         WHERE MLB.GLBL_CMPY_CD    = 'glblCmpyCd' ");
//        sb.append("         AND   MAP.MDL_MTR_LB_CD   = 'mdlMtrLbCd' ");
//        sb.append("         AND   MLB.GLBL_CMPY_CD    = MAP.GLBL_CMPY_CD ");
//        sb.append("         AND   MLB.MTR_LB_CD       = MAP.BLLG_MTR_LB_CD ");
        sb.append("         AND MLB.EFF_FROM_DT      <= 'slsDt' ");
        sb.append("         AND (MLB.EFF_THRU_DT     >= 'slsDt' ");
        sb.append("           OR MLB.EFF_THRU_DT     IS NULL) ");
        sb.append("         AND MLB.ACTV_FLG          = 'Y' ");
        sb.append("         AND MLB.MTR_IDX_CD        = 'mtrIdxCd' ");
        sb.append("         AND MLB.MTR_LB_TP_CD      = 'mtrLbTpCd' ");
//        sb.append("         AND   MLB.EZCANCELFLAG    = MAP.EZCANCELFLAG ");
        sb.append("         AND   MLB.EZCANCELFLAG    = '0' ");
//        sb.append("         AND   MAP.EZCANCELFLAG    = '0' ");

        String sql = sb.toString();
        sql = sql.replaceAll("glblCmpyCd", glblCmpyCd);
//        sql = sql.replaceAll("mdlMtrLbCd", mdlMtrLbCd);
        sql = sql.replaceAll("slsDt", slsDt);
        sql = sql.replaceAll("mtrIdxCd", MTR_IDX.NON_FLEET);
        // mod end 2016/05/26 CSA Defect#8877, 8878
        sql = sql.replaceAll("mtrLbTpCd", MTR_LB_TP.BILLING_METER);
        return sql;
    }
    // END 2017/08/03 T.Kanasaka [QC#18193,MOD]

    private static List<Object[]> getMtrLbListWhereList(String mtrLbTpCd, String mtrLbDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        // Counter Code
        whereList.add(createAttributeNameWhereList("Counter Code", "MTR_LB_CD", mtrLbTpCd, ZYPConstant.FLG_ON_Y));

        // Description
        whereList.add(createAttributeNameWhereList("Description", "MTR_LB_DESC_TXT", mtrLbDescTxt, ZYPConstant.FLG_ON_Y));

        return whereList;
    }

    private static List<Object[]> getMtrLbListColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        // Counter Code
        columnList.add(createAttributeNameColumnList("Counter Code", "MTR_LB_CD", MTR_LB_CD_LENGTH, ZYPConstant.FLG_ON_Y));
        // Counter
        columnList.add(createAttributeNameColumnList("Counter", "MTR_LB_NM", MTR_LB_NM_LENGTH, ZYPConstant.FLG_ON_Y));
        // Description
        columnList.add(createAttributeNameColumnList("Description", "MTR_LB_DESC_TXT", MTR_LB_DESC_TXT_LENGTH, ZYPConstant.FLG_ON_Y));
        // Type
        columnList.add(createAttributeNameColumnList("Type", "MTR_LB_TP_CD", MTR_LB_TP_CD_LENGTH, ZYPConstant.FLG_ON_Y));
        // Level
        columnList.add(createAttributeNameColumnList("Level", "BLLG_MTR_LVL_NUM", BLLG_MTR_LVL_NUM_LENGTH, ZYPConstant.FLG_ON_Y));

        return columnList;
    }

    // START 2017/08/03 T.Kanasaka [QC#18193,ADD]
    private static List<Object[]> getBllgMtrLbListColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        // Counter Code
        columnList.add(createAttributeNameColumnList("Counter Code", "MTR_LB_CD", MTR_LB_CD_LENGTH, ZYPConstant.FLG_ON_Y));
        // Counter
        columnList.add(createAttributeNameColumnList("Counter", "MTR_LB_NM", MTR_LB_NM_LENGTH, ZYPConstant.FLG_ON_Y));
        // Description
        columnList.add(createAttributeNameColumnList("Description", "MTR_LB_DESC_TXT", MTR_LB_DESC_TXT_LENGTH, ZYPConstant.FLG_ON_Y));
        // Type (hidden)
        columnList.add(createAttributeNameColumnList("Type", "MTR_LB_TP_CD", 0, ZYPConstant.FLG_ON_Y));
        // Level (hidden)
        columnList.add(createAttributeNameColumnList("Level", "BLLG_MTR_LVL_NUM", 0, ZYPConstant.FLG_ON_Y));

        return columnList;
    }
    // END 2017/08/03 T.Kanasaka [QC#18193,ADD]

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

        // START 2016/04/26 [QC#6700, MOD]
        return list.toArray(new Object[list.size()]);
        // END   2016/04/26 [QC#6700, MOD]
    }

    private static Object[] createAttributeNameColumnList(String objNm, String objId, int objLength, String whereFlag) {
        List<Object> list = new ArrayList<Object>();
        list.add(objNm);
        list.add(objId);
        list.add(BigDecimal.valueOf(objLength));
        list.add(whereFlag);

        // START 2016/04/26 [QC#6700, MOD]
        return list.toArray(new Object[list.size()]);
        // END   2016/04/26 [QC#6700, MOD]
    }

    private static Object[] createAttributeNameSortList(String objNm, String cond) {
        List<Object> list = new ArrayList<Object>();
        list.add(objNm);
        list.add(cond);

        // START 2016/04/26 [QC#6700, MOD]
        return list.toArray(new Object[list.size()]);
        // END   2016/04/26 [QC#6700, MOD]
    }
}
