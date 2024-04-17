/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210.common;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0210.NSBL0210BMsg;
import business.servlet.NSBL0210.NSBL0210Bean;
import business.servlet.NSBL0210.constant.NSBL0210Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Labor Charge Table Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 * 2017/01/24   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSBL0210CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0210BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSBL0210BMsg scrnMsg, String userId) {
        initCommonButton(userProfileService, handler);
        controlScreenFields(userProfileService, handler, scrnMsg);
        controlDetailScreenFields(userProfileService, handler, scrnMsg);
    }

    /**
     * initCommonButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_SAVE[0], NSBL0210Constant.BTN_CMN_SAVE[1], NSBL0210Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_SUBMIT[0], NSBL0210Constant.BTN_CMN_SUBMIT[1], NSBL0210Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_APPLY[0], NSBL0210Constant.BTN_CMN_APPLY[1], NSBL0210Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_APPROVE[0], NSBL0210Constant.BTN_CMN_APPROVE[1], NSBL0210Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_REJECT[0], NSBL0210Constant.BTN_CMN_REJECT[1], NSBL0210Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_BLANK6[0], NSBL0210Constant.BTN_CMN_BLANK6[1], NSBL0210Constant.BTN_CMN_BLANK6[2], 1, null);
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_BLANK7[0], NSBL0210Constant.BTN_CMN_BLANK7[1], NSBL0210Constant.BTN_CMN_BLANK7[2], 0, null);
        // START 2017/01/24 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_CLEAR[0], NSBL0210Constant.BTN_CMN_CLEAR[1], NSBL0210Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_RESET[0], NSBL0210Constant.BTN_CMN_RESET[1], NSBL0210Constant.BTN_CMN_RESET[2], 0, null);
        // END 2017/01/24 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(NSBL0210Constant.BTN_CMN_RETURN[0], NSBL0210Constant.BTN_CMN_RETURN[1], NSBL0210Constant.BTN_CMN_RETURN[2], 1, null);

        if (hasUpdateFuncId()) {
            handler.setButtonProperties(NSBL0210Constant.BTN_CMN_SUBMIT[0], NSBL0210Constant.BTN_CMN_SUBMIT[1], NSBL0210Constant.BTN_CMN_SUBMIT[2], 1, null);
        }
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0210BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSBL0210BMsg scrnMsg) {

        handler.setButtonEnabled(NSBL0210Constant.BTN_SEARCH[0], true);
        handler.setButtonEnabled(NSBL0210Constant.BTN_SELECT_ALL[0], false);
        handler.setButtonEnabled(NSBL0210Constant.BTN_UNSELECT_ALL[0], false);
        handler.setButtonEnabled(NSBL0210Constant.BTN_UPLOAD[0], false);
        handler.setButtonEnabled(NSBL0210Constant.BTN_INSERT_ROW[0], false);
        handler.setButtonEnabled(NSBL0210Constant.BTN_DELETE_ROW[0], false);

        if (hasUpdateFuncId()) {
            handler.setButtonEnabled(NSBL0210Constant.BTN_SELECT_ALL[0], true);
            handler.setButtonEnabled(NSBL0210Constant.BTN_UNSELECT_ALL[0], true);
            handler.setButtonEnabled(NSBL0210Constant.BTN_UPLOAD[0], true);
            handler.setButtonEnabled(NSBL0210Constant.BTN_INSERT_ROW[0], true);
            handler.setButtonEnabled(NSBL0210Constant.BTN_DELETE_ROW[0], true);
        }

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(NSBL0210Constant.BTN_DELETE_ROW[0], false);
        }

        if (scrnMsg.xxPageShowOfNum.getValueInt() == NSBL0210Constant.LIMIT_NUM_SEARCH) {
            handler.setButtonEnabled(NSBL0210Constant.BTN_INSERT_ROW[0], false);
        }
    }

    /**
     * controlDetailScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0210BMsg
     */
    public static void controlDetailScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSBL0210BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(NSBL0210Constant.BTN_OPEN_WIN_MODEL_GROUP_DETAIL[0], i, false);
            handler.setButtonEnabled(NSBL0210Constant.BTN_APPLY_MODEL_GROUP[0], i, false);
            handler.setButtonEnabled(NSBL0210Constant.BTN_OPEN_WIN_SHIFT_POPUP[0], i, false);
            handler.setButtonEnabled(NSBL0210Constant.BTN_APPLY_SHIFT[0], i, false);
            handler.setButtonEnabled(NSBL0210Constant.BTN_OPEN_WIN_INTG_ITEM_POPUP[0], i, false);
            handler.setButtonEnabled(NSBL0210Constant.BTN_APPLY_INTG_ITEM[0], i, false);

            scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            scrnMsg.A.no(i).mdlGrpNm.setInputProtected(true);
            scrnMsg.A.no(i).mdlGrpDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).svcLineBizCd.setInputProtected(true);
            scrnMsg.A.no(i).svcPrcTrvlChrgFlg.setInputProtected(true);
            scrnMsg.A.no(i).svcPrcShiftNum.setInputProtected(true);
            scrnMsg.A.no(i).svcPrcShiftDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).svcLborUnitAmt.setInputProtected(true);
            scrnMsg.A.no(i).svcMinChrgHrsAot.setInputProtected(true);
            scrnMsg.A.no(i).intgMdseCd.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt.setInputProtected(true);

            scrnMsg.A.no(i).svcLborUnitAmt.setAppFracDigit(2);
            scrnMsg.A.no(i).svcMinChrgHrsAot.setAppFracDigit(0);

            if (hasUpdateFuncId()) {
                handler.setButtonEnabled(NSBL0210Constant.BTN_OPEN_WIN_MODEL_GROUP_DETAIL[0], i, true);
                handler.setButtonEnabled(NSBL0210Constant.BTN_APPLY_MODEL_GROUP[0], i, true);
                handler.setButtonEnabled(NSBL0210Constant.BTN_OPEN_WIN_SHIFT_POPUP[0], i, true);
                handler.setButtonEnabled(NSBL0210Constant.BTN_APPLY_SHIFT[0], i, true);
                handler.setButtonEnabled(NSBL0210Constant.BTN_OPEN_WIN_INTG_ITEM_POPUP[0], i, true);
                handler.setButtonEnabled(NSBL0210Constant.BTN_APPLY_INTG_ITEM[0], i, true);

                scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                scrnMsg.A.no(i).mdlGrpNm.setInputProtected(false);
                scrnMsg.A.no(i).svcLineBizCd.setInputProtected(false);
                scrnMsg.A.no(i).svcPrcTrvlChrgFlg.setInputProtected(false);
                scrnMsg.A.no(i).svcPrcShiftNum.setInputProtected(false);
                scrnMsg.A.no(i).svcLborUnitAmt.setInputProtected(false);
                scrnMsg.A.no(i).svcMinChrgHrsAot.setInputProtected(false);
                scrnMsg.A.no(i).intgMdseCd.setInputProtected(false);
            }

        }

        S21TableColorController control = new S21TableColorController(NSBL0210Constant.SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NSBL0210Constant.BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Labor Charge Maintenance (" + NSBL0210Constant.BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (NSBL0210Constant.FUNC_ID_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * addCheckItemsHeader
     * @param scrnMsg NSBL0210BMsg
     */
    public static final void addCheckItemsHeader(NSBL0210BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mdlGrpNm_H);
        scrnMsg.addCheckItem(scrnMsg.svcLineBizCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcPrcShiftDescTxt_H);
    }

    /**
     * addCheckItemsDetail
     * @param scrnMsg NSBL0210BMsg
     */
    public static final void addCheckItemsDetail(NSBL0210BMsg scrnMsg) {
        String[] itemNames = new String[] {NSBL0210Bean.mdlGrpNm, NSBL0210Bean.svcLineBizCd, NSBL0210Bean.svcPrcShiftNum, NSBL0210Bean.svcLborUnitAmt, NSBL0210Bean.svcMinChrgHrsAot, NSBL0210Bean.intgMdseCd };
        scrnMsg.A.setCheckParam(itemNames, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * setNameForMessage
     * @param scrnMsg NSBL0210BMsg
     */
    public static final void setNameForMessage(NSBL0210BMsg scrnMsg) {
        scrnMsg.mdlGrpNm_H.setNameForMessage(NSBL0210Constant.LBL_NM_MDL_GRP);
        scrnMsg.svcLineBizCd_H.setNameForMessage(NSBL0210Constant.LBL_NM_LOB);
        scrnMsg.svcPrcShiftDescTxt_H.setNameForMessage(NSBL0210Constant.LBL_NM_SHIFT_DESC_TXT);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdlGrpNm.setNameForMessage(NSBL0210Constant.LBL_NM_MDL_GRP);
            scrnMsg.A.no(i).mdlGrpDescTxt.setNameForMessage(NSBL0210Constant.LBL_NM_MDL_GRP_DESC_TXT);
            scrnMsg.A.no(i).svcLineBizCd.setNameForMessage(NSBL0210Constant.LBL_NM_LOB);
            scrnMsg.A.no(i).svcPrcTrvlChrgFlg.setNameForMessage(NSBL0210Constant.LBL_NM_TRVL_CHRG_FLG);
            scrnMsg.A.no(i).svcPrcShiftNum.setNameForMessage(NSBL0210Constant.LBL_NM_SHIFT_NUM);
            scrnMsg.A.no(i).svcPrcShiftDescTxt.setNameForMessage(NSBL0210Constant.LBL_NM_SHIFT_DESC_TXT);
            scrnMsg.A.no(i).svcLborUnitAmt.setNameForMessage(NSBL0210Constant.LBL_NM_LBOR_UNIT_AMT);
            scrnMsg.A.no(i).svcMinChrgHrsAot.setNameForMessage(NSBL0210Constant.LBL_NM_MIN_CHRG_HRS_AOT);
            scrnMsg.A.no(i).intgMdseCd.setNameForMessage(NSBL0210Constant.LBL_NM_DEF_INTG_ITEM);
            scrnMsg.A.no(i).mdseDescShortTxt.setNameForMessage(NSBL0210Constant.LBL_NM_DEF_INTG_ITEM_DESC);
        }
    }

    /**
     * createItemSearchPopupParameter
     * @param scrnMsg NSBL0210BMsg
     * @param mdseCd mdseCd
     * @param mdseDescShortTxt mdseDescShortTxt
     * @param mdseItemTpCd mdseItemTpCd
     * @param mdseItemBillTpCd mdseItemTpCd
     * @return Object[]
     */
    public static Object[] createItemSearchPopupParameter(NSBL0210BMsg scrnMsg, String mdseCd, String mdseDescShortTxt, String mdseItemTpCd, String mdseItemBillTpCd) {
        Object[] params = new Object[NSBL0210Constant.PRM_LENGTH_NWAL1130];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "Item Search Popup";
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getItemSearchPopupSqlStr(scrnMsg.glblCmpyCd.getValue(), mdseItemTpCd, mdseItemBillTpCd);
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getItemSearchPopupWhereList(mdseCd, mdseDescShortTxt);
        // 4 : Lv2 : Column List (List: Search result columns)
        params[i++] = getItemSearchPopupColumnList();
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getItemSearchPopupSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.P;
        return params;
    }

    private static String getItemSearchPopupSqlStr(String glblCmpyCd, String mdseItemTpCd, String mdseItemBillTpCd) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ");
        sb.append("      M.GLBL_CMPY_CD ");
        sb.append("     ,M.EZCANCELFLAG ");
        sb.append("     ,M.MDSE_CD ");
        sb.append("     ,M.MDSE_DESC_SHORT_TXT ");
        sb.append(" FROM ");
        sb.append("     MDSE M ");
        sb.append(" WHERE ");
        sb.append("         M.GLBL_CMPY_CD         = 'glblCmpyCd' ");
        sb.append("     AND M.MDSE_ITEM_TP_CD      = 'mdseItemTpCd' ");
        sb.append("     AND M.MDSE_ITEM_BILL_TP_CD = 'mdseItemBillTpCd' ");
        sb.append("     AND M.EZCANCELFLAG  = '0' ");

        String sql = sb.toString();
        sql = sql.replaceAll("glblCmpyCd", glblCmpyCd);
        sql = sql.replaceAll("mdseItemTpCd", mdseItemTpCd);
        sql = sql.replaceAll("mdseItemBillTpCd", mdseItemBillTpCd);

        return sql;
    }

    private static List<Object[]> getItemSearchPopupWhereList(String mdseCd, String mdseDescShortTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        // Item#
        whereList.add(createAttributeNameWhereList("Item#", "MDSE_CD", mdseCd, ZYPConstant.FLG_ON_Y));

        // Item Description
        whereList.add(createAttributeNameWhereList("Item Description", "MDSE_DESC_SHORT_TXT", mdseDescShortTxt, ZYPConstant.FLG_ON_Y));

        return whereList;
    }

    private static List<Object[]> getItemSearchPopupColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        // Item#
        columnList.add(createAttributeNameColumnList("Item#", "MDSE_CD", NSBL0210Constant.MDSE_CD_LENGTH, ZYPConstant.FLG_ON_Y));
        // Counter
        // Item Description
        columnList.add(createAttributeNameColumnList("Item Description", "MDSE_DESC_SHORT_TXT", NSBL0210Constant.MDSE_DESC_SHORT_TXT_LENGTH, ZYPConstant.FLG_ON_Y));

        return columnList;
    }

    private static List<Object[]> getItemSearchPopupSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        sortConditionList.add(createAttributeNameSortList("MDSE_CD", "ASC"));
        return sortConditionList;
    }

    /**
     * createShiftSearchPopupParameter
     * @param scrnMsg NSBL0210BMsg
     * @param svcPrcShiftNum svcPrcShiftNum
     * @param svcPrcShiftDescTxt svcPrcShiftDescTxt
     * @param lineBizCd lineBizCd
     * @return Object[]
     */
    public static Object[] createShiftSearchPopupParameter(NSBL0210BMsg scrnMsg, String svcPrcShiftNum, String svcPrcShiftDescTxt, String lineBizCd) {
        Object[] params = new Object[NSBL0210Constant.PRM_LENGTH_NWAL1130];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Service Price Shift Search Popup)
        params[i++] = "Service Price Shift Search Popup";
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getShiftSearchPopupSqlStr(scrnMsg.glblCmpyCd.getValue());
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getShiftSearchPopupWhereList(svcPrcShiftNum, svcPrcShiftDescTxt, lineBizCd);
        // 4 : Lv2 : Column List (List: Search result columns)
        params[i++] = getShiftSearchPopupColumnList();
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getShiftSearchPopupSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.P;
        return params;
    }

    private static String getShiftSearchPopupSqlStr(String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ");
        sb.append("      M.GLBL_CMPY_CD ");
        sb.append("     ,M.EZCANCELFLAG ");
        sb.append("     ,M.SVC_PRC_SHIFT_NUM ");
        sb.append("     ,M.SVC_LINE_BIZ_CD ");
        sb.append("     ,M.SVC_PRC_SHIFT_DESC_TXT ");
        sb.append(" FROM ");
        sb.append("     SVC_PRC_SHIFT M ");
        sb.append(" WHERE ");
        sb.append("         M.GLBL_CMPY_CD           = 'glblCmpyCd' ");
        sb.append("     AND M.SVC_PRC_SHIFT_ACTV_FLG = 'Y' ");
        sb.append("     AND M.EZCANCELFLAG           = '0' ");

        String sql = sb.toString();
        sql = sql.replaceAll("glblCmpyCd", glblCmpyCd);

        return sql;
    }

    private static List<Object[]> getShiftSearchPopupWhereList(String svcPrcShiftNum, String svcPrcShiftDescTxt, String lineBizCd) {
        List<Object[]> whereList = new ArrayList<Object[]>();
        // Shift#
        whereList.add(createAttributeNameWhereList("Shift#", "SVC_PRC_SHIFT_NUM", svcPrcShiftNum, ZYPConstant.FLG_ON_Y));
        // Line of Business
        whereList.add(createAttributeNameWhereList("Line of Business", "SVC_LINE_BIZ_CD", lineBizCd, ZYPConstant.FLG_ON_Y));
        // Description
        whereList.add(createAttributeNameWhereList("Shift Description", "SVC_PRC_SHIFT_DESC_TXT", svcPrcShiftDescTxt, ZYPConstant.FLG_ON_Y));

        return whereList;
    }

    private static List<Object[]> getShiftSearchPopupColumnList() {
        List<Object[]> columnList = new ArrayList<Object[]>();
        // Shift#
        columnList.add(createAttributeNameColumnList("Shift#", "SVC_PRC_SHIFT_NUM", NSBL0210Constant.SVC_PRC_SHIFT_NUM_LENGTH, ZYPConstant.FLG_ON_Y));
        // Description
        columnList.add(createAttributeNameColumnList("Shift Description", "SVC_PRC_SHIFT_DESC_TXT", NSBL0210Constant.SVC_PRC_SHIFT_DESC_TXT_LENGTH, ZYPConstant.FLG_ON_Y));
        // Line of Business
        columnList.add(createAttributeNameColumnList("Line of Business", "SVC_LINE_BIZ_CD", NSBL0210Constant.LINE_BIZ_CD_LENGTH, ZYPConstant.FLG_ON_Y));

        return columnList;
    }

    private static List<Object[]> getShiftSearchPopupSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        sortConditionList.add(createAttributeNameSortList("SVC_PRC_SHIFT_NUM", "ASC"));
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
