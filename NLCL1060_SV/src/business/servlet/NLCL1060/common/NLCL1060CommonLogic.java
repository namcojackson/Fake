/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1060.common;

import java.util.ArrayList;
import java.util.List;

import business.servlet.NLCL1060.NLCL1060BMsg;
import business.servlet.NLCL1060.NLCL1060_PBMsgArray;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.SCREEN_ID;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.BTN_CMN_CLEAR;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.BTN_SEARCH;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.BTN_CMN_CLOSE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.SCR_NM_VND;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.TBL_NM_VND;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_DISP_NM_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_SQL_NM_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_WIDTH_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.SORT_COLUMN_NAME_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.SORT_CONDITION_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.SORT_CONDITION_FOR_SUPPLIER_SITE_CODE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060CommonLogic {

    /**
     * @param handler S21CommonHandler
     * @param profile S21UserProfileService
     * @param scrnMsg NLCL1060BMsg
     */
    public static void initButton(S21CommonHandler handler, S21UserProfileService profile, NLCL1060BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.xxChkBox.setInputProtected(false);
        scrnMsg.mdseCd_H.setInputProtected(false);
        scrnMsg.vndCd.setInputProtected(false);
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);

    }

    /**
     * <pre>
     * Delete Sort icon
     * Set Screen display
     * Set Focus
     * 
     * </pre>
     * 
     * @param scrnMsg NLCL1060BMsg
     * @param baseContents String[][]
     */
    public static void initScrn(NLCL1060BMsg scrnMsg, String[][] baseContents) {

        // Delete Sort icon
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);

        // Has Table data
        if (scrnMsg.A.getValidCount() > 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).vndCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).invtyQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).locStsDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).stkStsDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).locTpDescTxt_A1.setInputProtected(true);
            }
        }
        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.mdseCd_H);
    }

    /**
     * toArray_popup
     * @param p NLCL1060_PBMsgArray
     * @param size int
     * @return params
     */
    public static Object[] toArray_popup(NLCL1060_PBMsgArray p, int size) {

        Object[] param = new Object[size];

        for (int i = 0; i < size; i++) {

            param[i] = p.no(i).srchOptTxt_P;
        }

        return param;
    }

    /**
     * setSupplierInfoParam
     * @param scrnMsg NLCL1060BMsg
     * @return Common Code Pop Up Parameter (Supplier Code)
     */
    public static Object[] setSupplierInfoParam(NLCL1060BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.R.clear();
        scrnMsg.Q.clear();

        // Ctrl Data
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(0).xxPopPrm, SCR_NM_VND);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = SCR_NM_VND;

        param[2] = TBL_NM_VND;
        param[3] = getSearchConditionForSupplier(scrnMsg);
        param[4] = getDisplayColumnForSupplier(scrnMsg);
        param[5] = getSortForSupplier(scrnMsg);
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * getSearchConditionForSupplier
     * @param scrnMsg NLCL1060BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForSupplier(NLCL1060BMsg scrnMsg) {

        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE, scrnMsg.vndCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME, null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForSupplier
     * @param scrnMsg NLCL1060BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForSupplier(NLCL1060BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {COLUMN_DISP_NM_FOR_SUPPLIER_CODE, COLUMN_SQL_NM_FOR_SUPPLIER_CODE, COLUMN_WIDTH_FOR_SUPPLIER_CODE, ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE, COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE, ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME, COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME, ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);

        return colList;
    }

    /**
     * getSortForSupplier
     * @param scrnMsg NLCL1060BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForSupplier(NLCL1060BMsg scrnMsg) {

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_SUPPLIER_CODE, SORT_CONDITION_FOR_SUPPLIER_CODE };
        Object[] sortObj2 = {SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE, SORT_CONDITION_FOR_SUPPLIER_SITE_CODE };

        sortList.add(sortObj1);
        sortList.add(sortObj2);

        return sortList;
    }

}
