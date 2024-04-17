/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0240.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;

import business.servlet.NSBL0240.NSBL0240BMsg;
import business.servlet.NSBL0240.NSBL0240Bean;
import business.servlet.NSBL0240.NSBL0240_ABMsg;
import business.servlet.NSBL0240.constant.NSBL0240Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Travel Charge Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         Y.Takeno        Create          N/A
 * 2017/01/24   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSBL0240CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0240BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSBL0240BMsg scrnMsg, String userId) {
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
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_SAVE[0], NSBL0240Constant.BTN_CMN_SAVE[1], NSBL0240Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_SUBMIT[0], NSBL0240Constant.BTN_CMN_SUBMIT[1], NSBL0240Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_APPLY[0], NSBL0240Constant.BTN_CMN_APPLY[1], NSBL0240Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_APPROVE[0], NSBL0240Constant.BTN_CMN_APPROVE[1], NSBL0240Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_REJECT[0], NSBL0240Constant.BTN_CMN_REJECT[1], NSBL0240Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_BLANK6[0], NSBL0240Constant.BTN_CMN_BLANK6[1], NSBL0240Constant.BTN_CMN_BLANK6[2], 1, null);
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_BLANK7[0], NSBL0240Constant.BTN_CMN_BLANK7[1], NSBL0240Constant.BTN_CMN_BLANK7[2], 0, null);
        // START 2017/01/24 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_CLEAR[0], NSBL0240Constant.BTN_CMN_CLEAR[1], NSBL0240Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_RESET[0], NSBL0240Constant.BTN_CMN_RESET[1], NSBL0240Constant.BTN_CMN_RESET[2], 0, null);
        // END 2017/01/24 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(NSBL0240Constant.BTN_CMN_RETURN[0], NSBL0240Constant.BTN_CMN_RETURN[1], NSBL0240Constant.BTN_CMN_RETURN[2], 1, null);

        if (hasUpdateFuncId()) {
            handler.setButtonProperties(NSBL0240Constant.BTN_CMN_SUBMIT[0], NSBL0240Constant.BTN_CMN_SUBMIT[1], NSBL0240Constant.BTN_CMN_SUBMIT[2], 1, null);
        }
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0240BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSBL0240BMsg scrnMsg) {

        handler.setButtonEnabled(NSBL0240Constant.BTN_SEARCH[0], true);
        handler.setButtonEnabled(NSBL0240Constant.BTN_SELECT_ALL[0], false);
        handler.setButtonEnabled(NSBL0240Constant.BTN_UNSELECT_ALL[0], false);
        handler.setButtonEnabled(NSBL0240Constant.BTN_UPLOAD[0], false);
        handler.setButtonEnabled(NSBL0240Constant.BTN_INSERT_ROW[0], false);
        handler.setButtonEnabled(NSBL0240Constant.BTN_DELETE_ROW[0], false);

        if (hasUpdateFuncId()) {
            handler.setButtonEnabled(NSBL0240Constant.BTN_SELECT_ALL[0], true);
            handler.setButtonEnabled(NSBL0240Constant.BTN_UNSELECT_ALL[0], true);
            handler.setButtonEnabled(NSBL0240Constant.BTN_UPLOAD[0], true);
            handler.setButtonEnabled(NSBL0240Constant.BTN_INSERT_ROW[0], true);
            handler.setButtonEnabled(NSBL0240Constant.BTN_DELETE_ROW[0], true);
        }

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(NSBL0240Constant.BTN_DELETE_ROW[0], false);
        }

        if (scrnMsg.xxPageShowOfNum.getValueInt() == NSBL0240Constant.LIMIT_NUM_SEARCH) {
            handler.setButtonEnabled(NSBL0240Constant.BTN_INSERT_ROW[0], false);
        }
    }

    /**
     * controlDetailScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0240BMsg
     */
    public static void controlDetailScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSBL0240BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(NSBL0240Constant.BTN_OPEN_WIN_MODEL_GROUP_DETAIL[0], i, false);
            handler.setButtonEnabled(NSBL0240Constant.BTN_APPLY_MODEL_GROUP[0], i, false);
            handler.setButtonEnabled(NSBL0240Constant.BTN_OPEN_WIN_INTG_ITEM_POPUP[0], i, false);
            handler.setButtonEnabled(NSBL0240Constant.BTN_APPLY_INTG_ITEM[0], i, false);

            scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            scrnMsg.A.no(i).mdlGrpNm.setInputProtected(true);
            scrnMsg.A.no(i).mdlGrpDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).svcLineBizCd.setInputProtected(true);
            scrnMsg.A.no(i).intgMdseCd.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt.setInputProtected(true);

            if (hasUpdateFuncId()) {
                handler.setButtonEnabled(NSBL0240Constant.BTN_OPEN_WIN_MODEL_GROUP_DETAIL[0], i, true);
                handler.setButtonEnabled(NSBL0240Constant.BTN_APPLY_MODEL_GROUP[0], i, true);
                handler.setButtonEnabled(NSBL0240Constant.BTN_OPEN_WIN_INTG_ITEM_POPUP[0], i, true);
                handler.setButtonEnabled(NSBL0240Constant.BTN_APPLY_INTG_ITEM[0], i, true);

                scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                scrnMsg.A.no(i).mdlGrpNm.setInputProtected(false);
                scrnMsg.A.no(i).svcLineBizCd.setInputProtected(false);
                scrnMsg.A.no(i).intgMdseCd.setInputProtected(false);
            }

            for (int j = 0; j < scrnMsg.C.getValidCount(); j++) {
                EZDBStringItem svcTrvlChrgTpCd = (EZDBStringItem) getBItem(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_CHRG_TP_CD, scrnMsg.A.no(i), j);
                EZDBBigDecimalItem svcTrvlUnitAmt = (EZDBBigDecimalItem) getBItem(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_UNIT_AMT, scrnMsg.A.no(i), j);

                svcTrvlUnitAmt.setAppFracDigit(2);

                svcTrvlChrgTpCd.setInputProtected(true);
                svcTrvlUnitAmt.setInputProtected(true);

                if (hasUpdateFuncId()) {
                    svcTrvlChrgTpCd.setInputProtected(false);
                    svcTrvlUnitAmt.setInputProtected(false);
                }
            }
        }

        setFocusRule(userProfileService, handler, scrnMsg);

        S21TableColorController control = new S21TableColorController(NSBL0240Constant.SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A1", scrnMsg.A);
        control.setAlternateRowsBG("A2", scrnMsg.A);
    }

    private static String getZoneColumnId(String baseId, int rowIndex, int colIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(baseId);
        builder.append(String.format("%02d", colIndex));
        builder.append("#");
        builder.append(rowIndex);
        return builder.toString();
    }

    private static void setFocusRule(S21UserProfileService userProfileService, EZDCommonHandler handler, NSBL0240BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(NSBL0240Constant.SCREEN_ID, NSBL0240Bean.A );
        scrnMsg.addGUIAttribute( tblFocusRule );

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // xxChkBox
            ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(NSBL0240Bean.xxChkBox + "#" + i);
            if (i > 0) {
                fRule.setPrevId(getZoneColumnId(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_CHRG_TP_CD, i - 1, scrnMsg.C.getValidCount() - 1));
                tblFocusRule.addRule(fRule);
            }

            // ApplyIntgItem
            fRule = new ZYPGUIFocusRule("ApplyIntgItem#" + i);
            fRule.setNextId(getZoneColumnId(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_UNIT_AMT, i, 0));
            tblFocusRule.addRule(fRule);

            for (int j = 0; j < scrnMsg.C.getValidCount(); j++) {
                // svcTrvlUnitAmt_XX
                fRule = new ZYPGUIFocusRule(getZoneColumnId(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_UNIT_AMT, i, j));
                fRule.setNextId(getZoneColumnId(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_CHRG_TP_CD, i, j));
                tblFocusRule.addRule(fRule);

                // svcTrvlChrgTpCd_XX
                fRule = new ZYPGUIFocusRule(getZoneColumnId(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_CHRG_TP_CD, i, j));
                if (j < scrnMsg.C.getValidCount() - 1) {
                    fRule.setNextId(getZoneColumnId(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_UNIT_AMT, i, j + 1));
                    tblFocusRule.addRule(fRule);
                } else {
                    if (i < scrnMsg.A.getValidCount() - 1) {
                        fRule.setNextId(NSBL0240Bean.xxChkBox + "#" + i + 1);
                        tblFocusRule.addRule(fRule);
                    }
                }
            }
        }
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NSBL0240Constant.BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Travel Charge Maintenance (" + NSBL0240Constant.BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (NSBL0240Constant.FUNC_ID_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * addCheckItemsHeader
     * @param scrnMsg NSBL0240BMsg
     */
    public static final void addCheckItemsHeader(NSBL0240BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mdlGrpNm_H);
        scrnMsg.addCheckItem(scrnMsg.svcLineBizCd_H);
    }

    /**
     * addCheckItemsDetail
     * @param scrnMsg NSBL0240BMsg
     */
    public static final void addCheckItemsDetail(NSBL0240BMsg scrnMsg) {
        String[] itemNames = getCheckParamItemNames(scrnMsg);
        scrnMsg.A.setCheckParam(itemNames, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    private static String[] getCheckParamItemNames(NSBL0240BMsg scrnMsg) {
        List<String> itemNameList = new ArrayList<String>();
        itemNameList.add(NSBL0240Bean.mdlGrpNm);
        itemNameList.add(NSBL0240Bean.svcLineBizCd);
        itemNameList.add(NSBL0240Bean.intgMdseCd);
        itemNameList.addAll(getZoneFieldNames(scrnMsg));

        return itemNameList.toArray(new String[itemNameList.size()]);
    }

    /**
     * setNameForMessage
     * @param scrnMsg NSBL0240BMsg
     */
    public static final void setNameForMessage(NSBL0240BMsg scrnMsg) {
        scrnMsg.mdlGrpNm_H.setNameForMessage(NSBL0240Constant.LBL_NM_MDL_GRP);
        scrnMsg.svcLineBizCd_H.setNameForMessage(NSBL0240Constant.LBL_NM_LOB);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdlGrpNm.setNameForMessage(NSBL0240Constant.LBL_NM_MDL_GRP);
            scrnMsg.A.no(i).mdlGrpDescTxt.setNameForMessage(NSBL0240Constant.LBL_NM_DESC);
            scrnMsg.A.no(i).svcLineBizCd.setNameForMessage(NSBL0240Constant.LBL_NM_LOB);
            scrnMsg.A.no(i).intgMdseCd.setNameForMessage(NSBL0240Constant.LBL_NM_DEF_INTG_ITEM);
            scrnMsg.A.no(i).mdseDescShortTxt.setNameForMessage(NSBL0240Constant.LBL_NM_DEF_INTG_ITEM_DESC);

            for (int j = 0; j < scrnMsg.C.length(); j++) {
                String columnName = scrnMsg.C.no(i).xxScrItem50Txt.getValue();
                setZoneInfoColumnName(scrnMsg.A.no(i), columnName, j);
            }
        }
    }

    private static List<String> getZoneFieldNames(NSBL0240BMsg scrnMsg) {
        List<String> fieledNames = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            fieledNames.add(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_CHRG_TP_CD + String.format("%02d", i));
            fieledNames.add(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_UNIT_AMT + String.format("%02d", i));
        }

        return fieledNames;
    }

    private static void setZoneInfoColumnName(NSBL0240_ABMsg abMsg, String columnName, int colIndex) {
        EZDBBigDecimalItem svcTrvlUnitAmt = (EZDBBigDecimalItem) getBItem(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_UNIT_AMT, abMsg, colIndex);
        EZDBStringItem svcTrvlChrgTpCd = (EZDBStringItem) getBItem(NSBL0240Constant.FLD_BASE_NM_SVC_TRVL_CHRG_TP_CD, abMsg, colIndex);

        svcTrvlUnitAmt.setNameForMessage(NSBL0240Constant.LBL_NM_RATE);
        svcTrvlChrgTpCd.setNameForMessage(NSBL0240Constant.LBL_NM_UOM);
    }

    /**
     * createItemSearchPopupParameter
     * @param scrnMsg NSBL0240BMsg
     * @param mdseCd mdseCd
     * @param mdseDescShortTxt mdseDescShortTxt
     * @param mdseItemTpCd mdseItemTpCd
     * @param mdseItemBillTpCd mdseItemTpCd
     * @return Object[]
     */
    public static Object[] createItemSearchPopupParameter(NSBL0240BMsg scrnMsg, String mdseCd, String mdseDescShortTxt, String mdseItemTpCd, String mdseItemBillTpCd) {
        Object[] params = new Object[NSBL0240Constant.PRM_LENGTH_NWAL1130];
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
        columnList.add(createAttributeNameColumnList("Item#", "MDSE_CD", NSBL0240Constant.MDSE_CD_LENGTH, ZYPConstant.FLG_ON_Y));
        // Counter
        // Item Description
        columnList.add(createAttributeNameColumnList("Item Description", "MDSE_DESC_SHORT_TXT", NSBL0240Constant.MDSE_DESC_SHORT_TXT_LENGTH, ZYPConstant.FLG_ON_Y));

        return columnList;
    }

    private static List<Object[]> getItemSearchPopupSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        sortConditionList.add(createAttributeNameSortList("MDSE_CD", "ASC"));
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

    private static final EZDBItem getBItem(String fieldBaseName, EZDBMsg sMsg, int colIndex) {
        EZDBItem item = null;
        try {
            String fieldName = fieldBaseName + String.format("%02d", colIndex);
            Field fld = sMsg.getClass().getField(fieldName);
            Object fldObj = fld.get(sMsg);
            item = (EZDBItem) fldObj;

        } catch (NoSuchFieldException nsfe) {
            throw new S21AbendException(nsfe);

        } catch (IllegalAccessException iae) {
            throw new S21AbendException(iae);
        }

        return item;
    }
}
