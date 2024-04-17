/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1190.common;

import static business.servlet.NSAL1190.constant.NSAL1190Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1190.NSAL1190BMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;


/**
 *<pre>
 * Counters Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2016/08/23   Hitachi         T.Tomita        Update          QC#8878
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/08/02   Hitachi         T.Kanasaka      Update          QC#18193
 * 2018/02/05   CITS            M.Naito         Update          QC#21184
 *</pre>
 */
public class NSAL1190CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1190BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1190BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        activateButtons(handler, functionList);
    }

    /**
     * controlScreenFieldsForSearch
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1190BMsg
     */
    public static final void controlScreenFieldsForSearch(EZDCommonHandler handler, NSAL1190BMsg scrnMsg) {

        controlScreenFields(handler, scrnMsg);
        setRowColors(scrnMsg);
    }
    /**
     * activateButtons
     * @param handler S21CommonHandler
     * @param functionList  List<String>
     */
    private static void activateButtons(EZDCommonHandler handler, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 0, null);

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            // START 2017/01/20 K.Ochiai [QC#16331,MOD]
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            // END 2017/01/20 K.Ochiai [QC#16331,MOD]
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
    }

    /**
     * controlScreenFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1190BMsg
     */
    public static void controlScreenFields(EZDCommonHandler handler, NSAL1190BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (hasValue(scrnMsg.A.no(i).mtrLbCd_A)) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            }

            controlScreenFieldsForBllgMtrLvlNum(scrnMsg, i);
            controlScreenFieldsForMtrLbCd(handler, scrnMsg, i);
        }
    }

    /**
     * controlScreenFieldsForBllgMtrLvlNum
     * @param scrnMsg NSAL1190BMsg
     * @param index int
     */
    public static void controlScreenFieldsForBllgMtrLvlNum(NSAL1190BMsg scrnMsg, int index) {

        if (MTR_LB_TP.REGULAR_METER.equals(scrnMsg.A.no(index).mtrLbTpCd_A.getValue())) {
            scrnMsg.A.no(index).bllgMtrLvlNum_A.setInputProtected(true);
        } else {
            scrnMsg.A.no(index).bllgMtrLvlNum_A.setInputProtected(false);
        }
    }

    /**
     * controlScreenFieldsForMtrLbCd
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1190BMsg
     * @param index int
     */
    public static void controlScreenFieldsForMtrLbCd(EZDCommonHandler handler, NSAL1190BMsg scrnMsg, int index) {

        // START 2017/08/02 T.Kanasaka [QC#18193,MOD]
//        if (MTR_IDX.FLEET.equals(scrnMsg.A.no(index).mtrIdxCd_A.getValue())
//                || MTR_IDX.AGGREGATE.equals(scrnMsg.A.no(index).mtrIdxCd_A.getValue())) {
//            scrnMsg.A.no(index).mtrLbNm_AF.setInputProtected(true);
//            scrnMsg.A.no(index).mtrLbNm_AG.setInputProtected(true);
//            handler.setButtonEnabled(OPENWIN_FLTCOUNTER, index, false);
//            handler.setButtonEnabled(OPENWIN_AGGCOUNTER, index, false);
//        } else {
            scrnMsg.A.no(index).mtrLbNm_AF.setInputProtected(false);
            scrnMsg.A.no(index).mtrLbNm_AG.setInputProtected(false);
            handler.setButtonEnabled(OPENWIN_FLTCOUNTER, index, true);
            handler.setButtonEnabled(OPENWIN_AGGCOUNTER, index, true);
//        }
        // END 2017/08/02 T.Kanasaka [QC#18193,MOD]
    }

    /**
    * addCheckItemForSearch
    * @param scrnMsg NSAL1190BMsg
    */
   public static void addCheckItemForSearch(NSAL1190BMsg scrnMsg) {

       scrnMsg.addCheckItem(scrnMsg.mtrLbNm_H);
       scrnMsg.addCheckItem(scrnMsg.mtrLbDescTxt_H);
   }

   /**
     * addCheckItem
     * @param scrnMsg NSAL1190BMsg
     */
    public static void addCheckItemForAddLine(NSAL1190BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // START 2017/08/02 T.Kanasaka [QC#18193,DEL]
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbNm_AF);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbNm_AG);
            // END 2017/08/02 T.Kanasaka [QC#18193,DEL]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).actvFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bwMtrFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).colorMtrFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).totMtrFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).corpAdvtgFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrCntrId_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).intgMdseCd_A);
            // START 2018/02/05 M.Naito [QC#21184,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invPrintMtrLbDescTxt_A);
            // END 2018/02/05 M.Naito [QC#21184,ADD]
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL1190BMsg
     */
    public static void addCheckItem(NSAL1190BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrIdxCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgMtrLvlNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbDescTxt_A);
            // START 2017/08/02 T.Kanasaka [QC#18193,DEL]
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbNm_AF);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbNm_AG);
            // END 2017/08/02 T.Kanasaka [QC#18193,DEL]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).actvFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bwMtrFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).colorMtrFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).totMtrFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).corpAdvtgFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrCntrId_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).intgMdseCd_A);
            // START 2018/02/05 M.Naito [QC#21184,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invPrintMtrLbDescTxt_A);
            // END 2018/02/05 M.Naito [QC#21184,ADD]
        }
    }

  /**
    * setRowColors
    * @param scrnMsg NSAL1190BMsg
    */
   private static void setRowColors(NSAL1190BMsg scrnMsg) {

       S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
       tblColor.setAlternateRowsBG("A", scrnMsg.A);
   }

   /**
    * changeMtrIdxCd
    * @param handler EZDCommonHandler
    * @param scrnMsg NSAL1190BMsg
    */
   public static void changeMtrIdxCd(EZDCommonHandler handler, NSAL1190BMsg scrnMsg) {

       int eventLine = scrnMsg.xxNum_EV.getValueInt();
       controlScreenFieldsForMtrLbCd(handler, scrnMsg, eventLine);
       if (scrnMsg.A.no(eventLine).mtrLbNm_AF.isInputProtected()) {
           scrnMsg.A.no(eventLine).fleetMtrLbCd_A.clear();
           scrnMsg.A.no(eventLine).mtrLbNm_AF.clear();
       }
       if (scrnMsg.A.no(eventLine).mtrLbNm_AG.isInputProtected()) {
           scrnMsg.A.no(eventLine).aggrMtrLbCd_A.clear();
           scrnMsg.A.no(eventLine).mtrLbNm_AG.clear();
       }
   }

   /**
    * changeMtrLbTpCd
    * @param scrnMsg NSAL1190BMsg
    */
   public static void changeMtrLbTpCd(NSAL1190BMsg scrnMsg) {

       int eventLine = scrnMsg.xxNum_EV.getValueInt();
       controlScreenFieldsForBllgMtrLvlNum(scrnMsg, eventLine);
       if (scrnMsg.A.no(eventLine).bllgMtrLvlNum_A.isInputProtected()) {
           setValue(scrnMsg.A.no(eventLine).bllgMtrLvlNum_A, BLLG_MTR_LVL_NUM_0);
       }
   }

   /**
     * clearPopupParameter
     * @param scrnMsg NSAL1190BMsg
     */
    public static void clearPopupParameter(NSAL1190BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        ZYPTableUtil.clear(scrnMsg.P);
    }

    // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//    /**
//     * createPopupParameter
//     * @param scrnMsg NSAL1190BMsg
//     * @param mtrIdxCd String
//     * @param idx int
//     * @return Object[]
//     */
//    public static Object[] createPopupParameter(NSAL1190BMsg scrnMsg, String mtrIdxCd, int idx) {
//        Object[] params = new Object[PARAM_LENGTH_NWAL1130];
//        int i = 0;
//        params[i++] = "";
//        // 1 : Lv1 : Window Title (String: Popup Title)
//        params[i++] = "Counter Pop Up";
//        // 2 : Lv1 : Select Table Name (String: Search SQL)
//        // START 2016/08/23 T.Tomita [QC#8878, MOD]
//        params[i++] = getSqlStr(scrnMsg, mtrIdxCd, idx);
//        // END 2016/08/23 T.Tomita [QC#8878, MOD]
//        // 3 : Lv2 : Where List (List: Search condition columns)
//        String mtrLbNm = null;
//        if (MTR_IDX.FLEET.equals(mtrIdxCd)) {
//            mtrLbNm = scrnMsg.A.no(idx).mtrLbNm_AF.getValue();
//        } else if (MTR_IDX.AGGREGATE.equals(mtrIdxCd)) {
//            mtrLbNm = scrnMsg.A.no(idx).mtrLbNm_AG.getValue();
//        }
//        params[i++] = getWhereList(mtrLbNm);
//        // 4 : Lv2 : Column List (List: Search result columns)
//        params[i++] = getColumnList();
//        // 5 : Lv2 : Sort Condition (List: Sort columns)
//        params[i++] = getSortConditionList();
//        // 6 : Lv2 : Output
//        params[i++] = scrnMsg.P;
//        return params;
//    }
//
//    // START 2016/08/23 T.Tomita [QC#8878, MOD]
//    private static String getSqlStr(NSAL1190BMsg scrnMsg, String mtrIdxCd, int idx) {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(" SELECT ");
//        sb.append("      M.GLBL_CMPY_CD ");
//        sb.append("     ,M.EZCANCELFLAG ");
//        sb.append("     ,M.MTR_LB_CD ");
//        sb.append("     ,M.MTR_LB_NM ");
//        sb.append("     ,M.MTR_LB_DESC_TXT ");
//        sb.append("     ,M.MTR_LB_TP_CD ");
//        sb.append("     ,M.BLLG_MTR_LVL_NUM ");
//        sb.append("     ,M.MTR_LB_SORT_NUM ");
//        sb.append(" FROM ");
//        sb.append("     MTR_LB M ");
//        sb.append(" WHERE ");
//        sb.append("         M.GLBL_CMPY_CD  = 'glblCmpyCd' ");
//        sb.append("     AND M.EFF_FROM_DT  <= 'slsDt' ");
//        sb.append("     AND (M.EFF_THRU_DT >= 'slsDt' ");
//        sb.append("       OR M.EFF_THRU_DT IS NULL) ");
//        sb.append("     AND M.ACTV_FLG      = 'Y' ");
//        sb.append("     AND M.MTR_IDX_CD    = 'mtrIdxCd' ");
//        sb.append("     AND M.MTR_LB_TP_CD  = 'mtrLbTpCd' ");
//        sb.append("     AND M.EZCANCELFLAG  = '0' ");
//
//        String sql = sb.toString();
//        sql = sql.replaceAll("glblCmpyCd", scrnMsg.glblCmpyCd.getValue());
//        sql = sql.replaceAll("slsDt", scrnMsg.slsDt.getValue());
//        sql = sql.replaceAll("mtrIdxCd", mtrIdxCd);
//        sql = sql.replaceAll("mtrLbTpCd", scrnMsg.A.no(idx).mtrLbTpCd_A.getValue());
//
//        return sql;
//    }
//    // END 2016/08/23 T.Tomita [QC#8878, MOD]
//
//    private static List<Object[]> getWhereList(String mtrLbNm) {
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
//        h0[WLIST_DSP_OBJ_NM] = "Counter";
//        h0[WLIST_OBJ_ID] = "MTR_LB_NM";
//        h0[WLIST_OBJ_VALUE] = mtrLbNm;
//        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h0);
//
//        return whereList;
//    }
//
//    private static List<Object[]> getColumnList() {
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//
//        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
//        c0[CLIST_DSP_OBJ_NM] = "Counter Code";
//        c0[CLIST_OBJ_ID] = "MTR_LB_CD";
//        c0[CLIST_OBJ_LENGTH] = new BigDecimal(MTR_LB_CD_LENGTH);
//        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c0);
//
//        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
//        c1[CLIST_DSP_OBJ_NM] = "Counter";
//        c1[CLIST_OBJ_ID] = "MTR_LB_NM";
//        c1[CLIST_OBJ_LENGTH] = new BigDecimal(MTR_LB_NM_LENGTH);
//        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
//        columnList.add(c1);
//
//        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
//        c2[CLIST_DSP_OBJ_NM] = "Description";
//        c2[CLIST_OBJ_ID] = "MTR_LB_DESC_TXT";
//        c2[CLIST_OBJ_LENGTH] = new BigDecimal(MTR_LB_DESC_TXT_LENGTH);
//        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c2);
//
//        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
//        c3[CLIST_DSP_OBJ_NM] = "Type";
//        c3[CLIST_OBJ_ID] = "MTR_LB_TP_CD";
//        c3[CLIST_OBJ_LENGTH] = new BigDecimal(MTR_LB_TP_CD_LENGTH);
//        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c3);
//
//        Object[] c4 = new Object[ATTR_NUM_CLMN_LIST];
//        c4[CLIST_DSP_OBJ_NM] = "Level";
//        c4[CLIST_OBJ_ID] = "BLLG_MTR_LVL_NUM";
//        c4[CLIST_OBJ_LENGTH] = new BigDecimal(BLLG_MTR_LVL_NUM_LENGTH);
//        c4[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c4);
//
//        return columnList;
//    }
//
//    private static List<Object[]> getSortConditionList() {
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//
//        Object[] sortConditionArray1 = new Object[ATTR_NUM_SORT_LIST];
//        sortConditionArray1[SLIST_DSP_OBJ_NM] = "MTR_LB_SORT_NUM";
//        sortConditionArray1[SLIST_ORD_COND] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        return sortConditionList;
//    }
    // END 2017/08/03 T.Kanasaka [QC#18193,DEL]
}
