/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740.common;

import static business.servlet.NWAL1740.constant.NWAL1740Constant.BIZ_ID;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_APL;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_APR;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_RST;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_OPEN_WIN_LINE;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_OPEN_WIN_MDSE;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_OPEN_WIN_SWH;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_OPEN_WIN_WH;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.DB_FLAG_INSERT;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.NWAM8450E;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.NWAM8451E;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.NWAM8452E;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.PARAM_SIZE_4;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.SCRN_ID_00;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.ZZM9000E;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.FUNC_ID_UPDATE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1740.NWAL1740BMsg;
import business.servlet.NWAL1740.NWAL1740_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NWAL1740CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/15   Fujitsu         M.Suzuki        Update          S21_NA#6288
 * 2016/05/11   Fujitsu         M.Suzuki        Update          S21_NA#7304
 *</pre>
 */
public class NWAL1740CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param userProfileService S21UserProfileService
     */
    public static void initCmnBtnProp(S21CommonHandler handler, S21UserProfileService userProfileService) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);

        if (NWAL1740CommonLogic.isUpdateUser(userProfileService)) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }

        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1740BMsg
     * @param scrnAMsgAry NWAL1740_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1740BMsg scrnMsg, NWAL1740_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1740BMsg
     * @param scrnAMsgAry NWAL1740_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1740BMsg scrnMsg, NWAL1740_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL1740BMsg
     * @param scrnAMsgAry NWAL1740_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1740BMsg scrnMsg, NWAL1740_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemWHRules
     * @param scrnMsg NWAL1740BMsg
     */
    public static void addCheckItemWHRules(NWAL1740BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).rtlWhNm_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).rtlSwhNm_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).actvFlg_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C);

        }
    }

    /**
     * addCheckItemLineCatgRules
     * @param scrnMsg NWAL1740BMsg
     */
    public static void addCheckItemLineCatgRules(NWAL1740BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsOrdLineCatgNm_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsOrdLineCatgDescTxt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).actvFlg_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B);
        }
    }

    /**
     * addCheckItemMdseTpRules
     * @param scrnMsg NWAL1740BMsg
     */
    public static void addCheckItemMdseTpRules(NWAL1740BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).coaMdseTpCd_A);
        }
    }

    /**
     * getSortSetting
     * @return sortList
     */
    public static List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = new Object[PARAM_SIZE_4];
        sortObj1[0] = "RTL_WH_NM";
        sortObj1[1] = "ASC";
        sortObj1[2] = "RTL_SWH_NM";
        sortObj1[3] = "ASC";
        sortList.add(sortObj1);
        return sortList;
    }
    /**
     * getDisplayColumnSetting
     * @return DisplayColumnSetting
     */
    public static List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[PARAM_SIZE_4];
        //2016/04/04 S21_NA#7304 MOD Start --------------
        colObj1[0] = "Warehouse";
        //2016/04/04 S21_NA#7304 MOD Start --------------
        colObj1[1] = "RTL_WH_NM";
        colObj1[2] = new BigDecimal("30");
        colObj1[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj1);

        Object[] colObj2 = new Object[4];
        colObj2[0] = "Sub Warehouse";
        colObj2[1] = "RTL_SWH_NM";
        colObj2[2] = new BigDecimal("30");
        colObj2[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj2);

        return colList;
    }

    /**
     * getSearchConditionSetting
     * @param scrnMsg NWAL1740BMsg
     * @param index int
     * @return SearchConditionSetting
     */
    public static List<Object> getSearchConditionSetting(NWAL1740BMsg scrnMsg, int index) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[PARAM_SIZE_4];
        whereObj1[0] = "Warehouse";
        whereObj1[1] = "RTL_WH_NM";
        whereObj1[2] = scrnMsg.C.no(index).rtlWhNm_C.getValue();
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);
        Object[] whereObj2 = new Object[PARAM_SIZE_4];
        whereObj2[0] = "Sub Warehouse";
        whereObj2[1] = "RTL_SWH_NM";
        whereObj2[2] = scrnMsg.C.no(index).rtlSwhNm_C.getValue();
        whereObj2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getSql 
     * @param globalCompanyCd String
     * @return sql
     */
    public static String getSql(String globalCompanyCd) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT");
        sql.append(" WH.RTL_WH_NM");
        sql.append(",SWH.RTL_SWH_NM");
        sql.append(",WH.GLBL_CMPY_CD");
        sql.append(",WH.EZCANCELFLAG");
        sql.append(" FROM");
        sql.append(" RTL_WH WH");
        sql.append(",RTL_SWH SWH");
        sql.append(" WHERE");
        sql.append(" WH.GLBL_CMPY_CD = '");
        sql.append(globalCompanyCd);
        sql.append("'");
        sql.append(" AND WH.EZCANCELFLAG = '0'");
        sql.append(" AND WH.RTL_WH_CD = SWH.RTL_WH_CD");
        sql.append(" AND WH.GLBL_CMPY_CD = SWH.GLBL_CMPY_CD");
        sql.append(" AND SWH.EZCANCELFLAG = '0'");
        sql.append(" ORDER BY");
        sql.append(" WH.RTL_WH_NM");
        sql.append(" ,SWH.RTL_SWH_NM");

        return sql.toString();
    }

    /**
     * setOpenBtnProtected
     * @param scrnMsg NWAL1740BMsg
     * @param handle EZDCommonHandler
     */
    public static void setOpenBtnProtected(NWAL1740BMsg scrnMsg, EZDCommonHandler handle) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            String rowId = scrnMsg.A.no(i).xxRowId_A.getValue();
            if (ZYPCommonFunc.hasValue(rowId) && !DB_FLAG_INSERT.equals(rowId)) {
                handle.setButtonEnabled(BTN_OPEN_WIN_MDSE, i, false);
            } else {
                handle.setButtonEnabled(BTN_OPEN_WIN_MDSE, i, true);
            }
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            String rowId = scrnMsg.B.no(i).xxRowId_B.getValue();
            if (ZYPCommonFunc.hasValue(rowId) && !DB_FLAG_INSERT.equals(rowId)) {
                handle.setButtonEnabled(BTN_OPEN_WIN_LINE, i, false);
            } else {
                handle.setButtonEnabled(BTN_OPEN_WIN_LINE, i, true);
            }
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            String rowId = scrnMsg.C.no(i).xxRowId_C.getValue();
            if (ZYPCommonFunc.hasValue(rowId) && !DB_FLAG_INSERT.equals(rowId)) {
                handle.setButtonEnabled(BTN_OPEN_WIN_WH, i, false);
                handle.setButtonEnabled(BTN_OPEN_WIN_SWH, i, false);
            } else {
                handle.setButtonEnabled(BTN_OPEN_WIN_WH, i, true);
                handle.setButtonEnabled(BTN_OPEN_WIN_SWH, i, true);
            }
        }
    }
    /**
     * checkWH
     * @param scrnMsg NWAL1740BMsg
     */
    public static void checkWH(NWAL1740BMsg scrnMsg) {
        ArrayList<String> whList = new ArrayList<String>();
        ArrayList<String> whErrList = new ArrayList<String>();

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (scrnMsg.C.no(i).rtlWhNm_C.isInputProtected()) {
                scrnMsg.C.no(i).rtlWhNm_C.clearErrorInfo();
            }

            if (scrnMsg.C.no(i).rtlSwhNm_C.isInputProtected()) {
                scrnMsg.C.no(i).rtlSwhNm_C.clearErrorInfo();
            }

            //2016/04/04 S21_NA#6288 MOD Start ----------
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).effFromDt_C) && ZYPCommonFunc.hasValue(scrnMsg.C.no(i).effThruDt_C)) {

                if (ZYPDateUtil.compare(scrnMsg.C.no(i).effThruDt_C.getValue(), scrnMsg.C.no(i).effFromDt_C.getValue()) < 0) {
                    scrnMsg.C.no(i).effFromDt_C.setErrorInfo(1, NWAM8452E);
                    scrnMsg.C.no(i).effThruDt_C.setErrorInfo(1, NWAM8452E);
                }
            }

            boolean errFlag = false;
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).rtlWhNm_C)) {
                scrnMsg.C.no(i).rtlWhNm_C.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.C.no(i).rtlWhNm_C.getNameForMessage() });
                errFlag = true;
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).rtlSwhNm_C)) {
                scrnMsg.C.no(i).rtlSwhNm_C.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.C.no(i).rtlSwhNm_C.getNameForMessage() });
                errFlag = true;
            }

            if (errFlag) {
                continue;
            }

            //String whNm = scrnMsg.C.no(i).rtlWhNm_C.getValue();
            //String whSNm = scrnMsg.C.no(i).rtlSwhNm_C.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(scrnMsg.C.no(i).rtlWhNm_C.getValue());
            //2016/04/21 S21_NA#6288 MOD Start ------------
            sb.append("\n");
            //2016/04/21 S21_NA#6288 MOD End ------------
            sb.append(scrnMsg.C.no(i).rtlSwhNm_C.getValue());

            if (!whList.contains(sb.toString())) {
                whList.add(sb.toString());
            } else {
                if (!whErrList.contains(sb.toString())) {
                    whErrList.add(sb.toString());
                }
            }

        }

        for (String errNm : whErrList) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                //String whNm = scrnMsg.C.no(i).rtlWhNm_C.getValue();
                //String whSNm = scrnMsg.C.no(i).rtlSwhNm_C.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(scrnMsg.C.no(i).rtlWhNm_C.getValue());
                //2016/04/21 S21_NA#6288 MOD Start ------------
                sb.append("\n");
                //2016/04/21 S21_NA#6288 MOD End ------------
                sb.append(scrnMsg.C.no(i).rtlSwhNm_C.getValue());
                if (errNm.equals(sb.toString())) {
                    scrnMsg.C.no(i).rtlWhNm_C.setErrorInfo(1, NWAM8451E, new String[] {scrnMsg.C.no(i).rtlWhNm_C.getNameForMessage(), scrnMsg.C.no(i).rtlWhNm_C.getValue() });
                    scrnMsg.C.no(i).rtlSwhNm_C.setErrorInfo(1, NWAM8451E, new String[] {scrnMsg.C.no(i).rtlSwhNm_C.getNameForMessage(), scrnMsg.C.no(i).rtlSwhNm_C.getValue() });
                }
            }
        }
        //2016/04/04 S21_NA#6288 MOD End ----------
    }

    /**
     * checkLineCatgNm
     * @param scrnMsg NWAL1740BMsg
     */
    public static void checkLineCatgNm(NWAL1740BMsg scrnMsg) {
        ArrayList<String> lineCatgNmList = new ArrayList<String>();
        ArrayList<String> lineCatgNmErrList = new ArrayList<String>();

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (scrnMsg.B.no(i).dsOrdLineCatgNm_B.isInputProtected()) {
                scrnMsg.B.no(i).dsOrdLineCatgNm_B.clearErrorInfo();
            }

            //2016/04/04 S21_NA#6288 MOD Start ----------
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).effFromDt_B) && ZYPCommonFunc.hasValue(scrnMsg.B.no(i).effThruDt_B)) {

                if (ZYPDateUtil.compare(scrnMsg.B.no(i).effThruDt_B.getValue(), scrnMsg.B.no(i).effFromDt_B.getValue()) < 0) {
                    scrnMsg.B.no(i).effFromDt_B.setErrorInfo(1, NWAM8452E);
                    scrnMsg.B.no(i).effThruDt_B.setErrorInfo(1, NWAM8452E);
                }
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsOrdLineCatgNm_B)) {
                scrnMsg.B.no(i).dsOrdLineCatgNm_B.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(i).dsOrdLineCatgNm_B.getNameForMessage() });
                continue;
            }

            //String lineCatgNm = scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue();
            if (!lineCatgNmList.contains(scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue())) {
                lineCatgNmList.add(scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue());
            } else {
                if (!lineCatgNmErrList.contains(scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue())) {
                    lineCatgNmErrList.add(scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue());
                }
            }
        }

        for (String errNm : lineCatgNmErrList) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                //String lineCatgNm = scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue();
                if (errNm.equals(scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue())) {
                    scrnMsg.B.no(i).dsOrdLineCatgNm_B.setErrorInfo(1, NWAM8450E, new String[] {scrnMsg.B.no(i).dsOrdLineCatgNm_B.getNameForMessage(), scrnMsg.B.no(i).dsOrdLineCatgNm_B.getValue() });
                }
            }
        }
        //2016/04/04 S21_NA#6288 MOD End ------------
    }

    /**
     * checkMdseTpCode
     * @param scrnMsg NWAL1740BMsg
     */
    public static void checkMdseTpCode(NWAL1740BMsg scrnMsg) {
        ArrayList<String> mdsetypeList = new ArrayList<String>();
        ArrayList<String> mdsetypeErrList = new ArrayList<String>();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).coaMdseTpCd_A.isInputProtected()) {
                scrnMsg.A.no(i).coaMdseTpCd_A.clearErrorInfo();
            }
            //2016/04/04 S21_NA#6288 MOD Start ------------
            String mdseTpCd = scrnMsg.A.no(i).coaMdseTpCd_A.getValue();
            if (!ZYPCommonFunc.hasValue(mdseTpCd)) {
                scrnMsg.A.no(i).coaMdseTpCd_A.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).coaMdseTpCd_A.getNameForMessage() });
                continue;
            }
            //2016/04/04 S21_NA#6288 MOD End --------------
            if (!mdsetypeList.contains(mdseTpCd)) {
                mdsetypeList.add(mdseTpCd);
            } else {
                if (!mdsetypeErrList.contains(mdseTpCd)) {
                    mdsetypeErrList.add(mdseTpCd);
                }
            }
        }

        for (String errcd : mdsetypeErrList) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                String mdseTpcode = scrnMsg.A.no(i).coaMdseTpCd_A.getValue();
                if (errcd.equals(mdseTpcode)) {
                    scrnMsg.A.no(i).coaMdseTpCd_A.setErrorInfo(1, NWAM8450E, new String[] {scrnMsg.A.no(i).coaMdseTpCd_A.getNameForMessage(), mdseTpcode });
                }
            }
        }
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNC_ID_UPDATE);
    }
}
