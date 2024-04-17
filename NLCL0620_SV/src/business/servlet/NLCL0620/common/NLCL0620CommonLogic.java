/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0620.common;

import static business.servlet.NLCL0620.constant.NLCL0620Constant.BIZ_APP_ID;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_1;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_2;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_3;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_4;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_5;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_6;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_7;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_8;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_CMN_BTN_9;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.BTN_SEARCH;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.COLUMN_DISP_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.COLUMN_DISP_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.COLUMN_SQL_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.COLUMN_SQL_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.COLUMN_WIDTH_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.COLUMN_WIDTH_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.DECIMAL_POINT_USD;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.DISPLAY_NM_PHYS_INVTY_CTRL_NM;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.DISPLAY_NM_PHYS_INVTY_DT;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.DISPLAY_NM_TECH_NM;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.DISPLAY_NM_TECH_TOC_CD;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.FUNC_EDIT;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.FUNC_ID_ALL_USERS;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.SCRN_ID;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.SORT_COLUMN_NAME_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.SORT_CONDITION_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.WHERE_DISP_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.WHERE_DISP_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.WHERE_SQL_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.WHERE_SQL_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.WINDOW_TITLE_LOCATION_SEARCH;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.*;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0620.NLCL0620BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   CITS            Makoto Okigami  Create          N/A
 * 12/06/2018   Fujitsu         S.Ohki          Update          QC#28128
 * 12/11/2018   Fujitsu         S.Ohki          Update          QC#28755
 * 01/08/2019   Fujitsu         T.Ogura         Update          QC#29765
 * 01/18/2019   Fujitsu         T.Ogura         Update          QC#29970
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 *</pre>
 */
public class NLCL0620CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0620BMsg
     * @param functionList List<String>
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NLCL0620BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible

        // Header
        scrnMsg.techTocCd.setInputProtected(false);
        scrnMsg.techNm.setInputProtected(true);
        scrnMsg.physInvtyDt.setInputProtected(false);
        scrnMsg.physInvtyCtrlNm.setInputProtected(false);
        scrnMsg.shipDt.setInputProtected(true);
        scrnMsg.adjGrsAmt.setInputProtected(true);
        scrnMsg.adjNetAmt.setInputProtected(true);
        // START 2018/12/12 S.Ohki [QC#28755,ADD]
        scrnMsg.rtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm.setInputProtected(true);
        // END 2018/12/12 S.Ohki [QC#28755,ADD]

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, false);

        scrnMsg.adjGrsAmt.setAppFracDigit(DECIMAL_POINT_USD);
        scrnMsg.adjNetAmt.setAppFracDigit(DECIMAL_POINT_USD);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        // START 2019/12/17 T.Ogura [QC#54986,MOD]
//        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        // END   2019/12/17 T.Ogura [QC#54986,MOD]
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        setAuthorityProtect(handler, scrnMsg, functionList);

    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0620BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NLCL0620BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_SEARCH, true);

            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            // START 2019/01/18 T.Ogura [QC#29970,MOD]
//            if (FUNC_EDIT.equals(function)) {
            if (FUNC_EDIT.equals(function) || FUNC_ID_ALL_USERS.equals(function)) {
            // END   2019/01/18 T.Ogura [QC#29970,MOD]
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NLCL0620BMsg
     */
    public static void setNameForMessage(NLCL0620BMsg scrnMsg) {

        // Header
        scrnMsg.techTocCd.setNameForMessage(DISPLAY_NM_TECH_TOC_CD);
        scrnMsg.techNm.setNameForMessage(DISPLAY_NM_TECH_NM);
        scrnMsg.physInvtyDt.setNameForMessage(DISPLAY_NM_PHYS_INVTY_DT);
        scrnMsg.physInvtyCtrlNm.setNameForMessage(DISPLAY_NM_PHYS_INVTY_CTRL_NM);
        // START 2018/12/12 S.Ohki [QC#28755,ADD]
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlWhNm.setNameForMessage(DISPLAY_NM_RTL_WH_NM);
        // END 2018/12/12 S.Ohki [QC#28755,ADD]
    }

    /**
     * Table Column Protect
     * @param scrnMsg NLCL0620BMsg
     */
    public static void setTableScreen(NLCL0620BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxScrItem61Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgStsDescTxt_A1.setInputProtected(true);
        }
    }

    /**
     * Set Technician Search Param
     * @param scrnMsg NLCL0620BMsg
     * @param glblCmpyCd String
     * @return Technician Search Parameter
     */
    public static Object[] setTechnicianSearchParam(NLCL0620BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = WINDOW_TITLE_TECHNICIAN_SEARCH;
        param[2] = getSelectSQLForTechnician(scrnMsg, glblCmpyCd);
        param[3] = getSearchConditionForTechnician(scrnMsg);
        param[4] = getDisplayColumnForTechnician(scrnMsg);
        param[5] = getSortForTechnician(scrnMsg);
        param[6] = scrnMsg.R;

        return param;

    }

    /**
     * getSelectSQLForTechnician
     * @param scrnMsg NLCL0620BMsg
     * @param glblCmpyCd String
     * @return Select SQL
     */
    private static String getSelectSQLForTechnician(NLCL0620BMsg scrnMsg, String glblCmpyCd) {

        // START 2018/12/06 S.Ohki [QC#28128,ADD]
        String userId = null;
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        }
        // END 2018/12/06 S.Ohki [QC#28128,ADD]

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT                                                           ").append("\n");
        sb.append("    TM.TECH_TOC_CD,                                              ").append("\n");
        sb.append("    TM.TECH_NM,                                                  ").append("\n");
        sb.append("    TM.GLBL_CMPY_CD,                                             ").append("\n");
        sb.append("    TM.EZCANCELFLAG                                              ").append("\n");
        sb.append("FROM                                                             ").append("\n");
        sb.append("    TECH_MSTR TM,                                                ").append("\n");
        sb.append("    S21_PSN PSN                                                  ").append("\n");
        sb.append("WHERE                                                            ").append("\n");
        sb.append("        TM.GLBL_CMPY_CD                     =  '#glblCmpyCd#'    ").append("\n");
        sb.append("    AND TM.EZCANCELFLAG                     =  '0'               ").append("\n");
        sb.append("    AND TM.TECH_TOC_CD                      =  PSN.PSN_CD        ").append("\n");
        sb.append("    AND TM.GLBL_CMPY_CD                     =  PSN.GLBL_CMPY_CD  ").append("\n");
        // START 2018/12/06 S.Ohki [QC#28128,MOD]
//        sb.append("    AND (PSN.HR_SUPV_ID                     =  '#userId#'        ").append("\n");
//        sb.append("        OR PSN.PSN_CD                       =  '#userId#')       ").append("\n");
        if (ZYPCommonFunc.hasValue(userId)) {
            sb.append("    AND (PSN.HR_SUPV_ID                     =  '#userId#'        ").append("\n");
            sb.append("        OR PSN.PSN_CD                       =  '#userId#')       ").append("\n");
        }
        // END 2018/12/06 S.Ohki [QC#28128,MOD]
        sb.append("    AND PSN.EFF_FROM_DT                    <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(PSN.EFF_THRU_DT, '99991231')   >=  '#salesDate#'     ").append("\n");
        sb.append("    AND PSN.EZCANCELFLAG                    =  '0'               ").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        // START 2018/12/06 S.Ohki [QC#28128,MOD]
//        sql = sql.replaceAll("#userId#", S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        if (ZYPCommonFunc.hasValue(userId)) {
            sql = sql.replaceAll("#userId#", userId);
        }
        // END 2018/12/06 S.Ohki [QC#28128,MOD]
        sql = sql.replaceAll("#salesDate#", ZYPDateUtil.getSalesDate(glblCmpyCd));

        return sql;
    }

    /**
     * getSearchConditionForTechnician
     * @param scrnMsg NLCL0620BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForTechnician(NLCL0620BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        // START 2018/12/12 S.Ohki [QC#28755,MOD]
//        Object[] whereObj1 = {WHERE_DISP_NM_FOR_TECHNICIAN_CODE, WHERE_SQL_NM_FOR_TECHNICIAN_CODE, scrnMsg.techTocCd.getValue(), ZYPConstant.FLG_ON_Y };
    	String techTocCd = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.techTocCd)) {
        	techTocCd = scrnMsg.techTocCd.getValue();
        } else {
        	techTocCd = PERCENT;
        }
        Object[] whereObj1 = {WHERE_DISP_NM_FOR_TECHNICIAN_CODE, WHERE_SQL_NM_FOR_TECHNICIAN_CODE, techTocCd, ZYPConstant.FLG_ON_Y };
        // END 2018/12/12 S.Ohki [QC#28755,MOD]
        // START 2019/01/08 T.Ogura [QC#29765,MOD]
//        Object[] whereObj2 = {WHERE_DISP_NM_FOR_TECHNICIAN_NAME, WHERE_SQL_NM_FOR_TECHNICIAN_NAME, scrnMsg.techNm.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_TECHNICIAN_NAME, WHERE_SQL_NM_FOR_TECHNICIAN_NAME, "", ZYPConstant.FLG_ON_Y };
        // END   2019/01/08 T.Ogura [QC#29765,MOD]

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForTechnician
     * @param scrnMsg NLCL0620BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForTechnician(NLCL0620BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_TECHNICIAN_CODE, COLUMN_SQL_NM_FOR_TECHNICIAN_CODE, COLUMN_WIDTH_FOR_TECHNICIAN_CODE, ZYPConstant.FLG_ON_Y  };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_TECHNICIAN_NAME, COLUMN_SQL_NM_FOR_TECHNICIAN_NAME, COLUMN_WIDTH_FOR_TECHNICIAN_NAME, ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    /**
     * getSortForTechnician
     * @param scrnMsg NLCL0620BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForTechnician(NLCL0620BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_TECHNICIAN_CODE, SORT_CONDITION_FOR_TECHNICIAN_CODE };

        sortList.add(sortObj1);

        return sortList;
    }

    // START 2018/12/12 S.Ohki [QC#28755,ADD]
    /**
     * Set Location Search Param
     * @param scrnMsg NLCL0620BMsg
     * @param glblCmpyCd String
     * @return Location Search Parameter
     */
    public static Object[] setLocationSearchParam(NLCL0620BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = WINDOW_TITLE_LOCATION_SEARCH;
        param[2] = getSelectSQLForLocation(scrnMsg, glblCmpyCd);
        param[3] = getSearchConditionForLocation(scrnMsg);
        param[4] = getDisplayColumnForLocation(scrnMsg);
        param[5] = getSortForLocation(scrnMsg);
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * getSelectSQLForLocation
     * @param scrnMsg NLCL0620BMsg
     * @param glblCmpyCd String
     * @return Select SQL
     */
    private static String getSelectSQLForLocation(NLCL0620BMsg scrnMsg, String glblCmpyCd) {

        String userId = null;
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        }

        String techTocCd = scrnMsg.techTocCd.getValue();
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT                                                           ").append("\n");
        sb.append("    RW.RTL_WH_CD,                                                ").append("\n");
        sb.append("    RW.RTL_WH_NM,                                                ").append("\n");
        sb.append("    RW.GLBL_CMPY_CD,                                             ").append("\n");
        sb.append("    RW.EZCANCELFLAG                                              ").append("\n");
        sb.append("FROM                                                             ").append("\n");
        sb.append("    RTL_WH    RW,                                                ").append("\n");
        sb.append("    S21_PSN   PSN                                                ").append("\n");
        sb.append("WHERE                                                            ").append("\n");
        sb.append("        RW.GLBL_CMPY_CD                     =  '#glblCmpyCd#'    ").append("\n");
        sb.append("    AND RW.EZCANCELFLAG                     =  '0'               ").append("\n");
        sb.append("    AND RW.TECH_TOC_CD                      =  PSN.PSN_CD        ").append("\n");
        sb.append("    AND RW.GLBL_CMPY_CD                     =  PSN.GLBL_CMPY_CD  ").append("\n");
        sb.append("    AND RW.EFF_FROM_DT                     <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(RW.EFF_THRU_DT, '99991231')    >=  '#salesDate#'     ").append("\n");

        if (ZYPCommonFunc.hasValue(techTocCd)) {
            sb.append("    AND RW.TECH_TOC_CD                      =  '#techTocCd#'     ").append("\n");
        }

        if (ZYPCommonFunc.hasValue(userId)) {
            sb.append("    AND (PSN.HR_SUPV_ID                     =  '#userId#'        ").append("\n");
            sb.append("        OR PSN.PSN_CD                       =  '#userId#')       ").append("\n");
        }

        sb.append("    AND PSN.EFF_FROM_DT                    <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(PSN.EFF_THRU_DT, '99991231')   >=  '#salesDate#'     ").append("\n");
        sb.append("    AND PSN.EZCANCELFLAG                    =  '0'               ").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(userId)) {
            sql = sql.replaceAll("#userId#", userId);
        }
        if (ZYPCommonFunc.hasValue(techTocCd)) {
            sql = sql.replaceAll("#techTocCd#", techTocCd);
        }
        sql = sql.replaceAll("#salesDate#", ZYPDateUtil.getSalesDate(glblCmpyCd));

        return sql;
    }

    /**
     * getSearchConditionForLocation
     * @param scrnMsg NLCL0620BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForLocation(NLCL0620BMsg scrnMsg) {

    	String rtlWhCd = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
        	rtlWhCd = scrnMsg.rtlWhCd.getValue();
        } else {
        	rtlWhCd = PERCENT;
        }
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {WHERE_DISP_NM_FOR_LOCATION_CODE, WHERE_SQL_NM_FOR_LOCATION_CODE, rtlWhCd, ZYPConstant.FLG_ON_Y };
        // START 2019/01/08 T.Ogura [QC#29765,MOD]
//        Object[] whereObj2 = {WHERE_DISP_NM_FOR_LOCATION_NAME, WHERE_SQL_NM_FOR_LOCATION_NAME, scrnMsg.rtlWhNm.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_LOCATION_NAME, WHERE_SQL_NM_FOR_LOCATION_NAME, "", ZYPConstant.FLG_ON_Y };
        // END   2019/01/08 T.Ogura [QC#29765,MOD]

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForLocation
     * @param scrnMsg NLCL0620BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForLocation(NLCL0620BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_LOCATION_CODE, COLUMN_SQL_NM_FOR_LOCATION_CODE, COLUMN_WIDTH_FOR_LOCATION_CODE, ZYPConstant.FLG_ON_Y  };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_LOCATION_NAME, COLUMN_SQL_NM_FOR_LOCATION_NAME, COLUMN_WIDTH_FOR_LOCATION_NAME, ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    /**
     * getSortForLocation
     * @param scrnMsg NLCL0620BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForLocation(NLCL0620BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_LOCATION_CODE, SORT_CONDITION_FOR_LOCATION_CODE };

        sortList.add(sortObj1);

        return sortList;
    }
    // END 2018/12/12 S.Ohki [QC#28755,ADD]

}
