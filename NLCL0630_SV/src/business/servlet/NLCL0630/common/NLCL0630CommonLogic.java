/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630.common;

import static business.servlet.NLCL0630.constant.NLCL0630Constant.BIZ_APP_ID;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_BEGIN;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CANCEL;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_1;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_2;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_3;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_4;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_5;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_6;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_7;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_8;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_CMN_BTN_9;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.BTN_SEARCH;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_DISP_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_DISP_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_DISP_NM_FOR_TECH_PHYSICAL_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_SQL_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_SQL_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_SQL_NM_FOR_TECH_PHYSICAL_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_WIDTH_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_WIDTH_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_WIDTH_FOR_TECH_PHYSICAL_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DECIMAL_POINT_USD;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_COA_BR_CD;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_COA_BR_NM;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_PHYS_INVTY_CTRL_NM;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_PHYS_INVTY_DT_DF;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_PHYS_INVTY_DT_DT;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_TECH_NM;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_TECH_TOC_CD;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.DISPLAY_NM_RTL_WH_NM;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.FUNC_ID_ALL_USERS;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.HTML_ID_RADIO_BUTTON;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SCRN_ID;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SORT_COLUMN_NAME_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SORT_COLUMN_NAME_FOR_TECH_PHYSICAL_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SORT_CONDITION_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SORT_CONDITION_FOR_TECH_PHYSICAL_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_DISP_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_DISP_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_DISP_NM_FOR_TECH_PHYSICAL_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_SQL_NM_FOR_TECHNICIAN_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_SQL_NM_FOR_TECHNICIAN_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_SQL_NM_FOR_TECH_PHYSICAL_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WINDOW_TITLE_TECHNICIAN_SEARCH;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WINDOW_TITLE_TECH_PHYSICAL_SEARCH;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WINDOW_TITLE_LOCATION_SEARCH;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_DISP_NM_FOR_LOCATION_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_SQL_NM_FOR_LOCATION_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_DISP_NM_FOR_LOCATION_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WHERE_SQL_NM_FOR_LOCATION_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_DISP_NM_FOR_LOCATION_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_SQL_NM_FOR_LOCATION_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_WIDTH_FOR_LOCATION_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_DISP_NM_FOR_LOCATION_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_SQL_NM_FOR_LOCATION_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.COLUMN_WIDTH_FOR_LOCATION_NAME;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SORT_COLUMN_NAME_FOR_LOCATION_CODE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SORT_CONDITION_FOR_LOCATION_CODE;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0630.NLCL0630BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS            Makoto Okigami  Create          N/A
 * 01/16/2017   CITS            T.Kikuhara      Update          QC#16256
 * 08/07/2018   CITS            Y.Iwasaki       Update          QC#27010
 * 09/12/2018   CITS            M.Naito         Update          QC#28190
 * 2018/09/21   CITS            K.Ogino         Update          QC#28191
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public class NLCL0630CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0630BMsg
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NLCL0630BMsg scrnMsg) {

        // column input protection
        // true : block entry
        // false : input possible

        // Header
        scrnMsg.techTocCd.setInputProtected(false);
        scrnMsg.techNm.setInputProtected(true);
        scrnMsg.physInvtyDt_DF.setInputProtected(false);
        scrnMsg.physInvtyDt_DT.setInputProtected(false);
        scrnMsg.physInvtyCtrlNm.setInputProtected(false);
        scrnMsg.coaBrCd.setInputProtected(false);
        scrnMsg.coaBrNm.setInputProtected(true);
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        scrnMsg.rtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm.setInputProtected(true);
        // END   2018/12/11 T.Ogura [QC#28755,ADD]

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_CANCEL, true);
        handler.setButtonEnabled(BTN_BEGIN, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        }
        // QC#16256 add end

    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NLCL0630BMsg
     */
    public static void setNameForMessage(NLCL0630BMsg scrnMsg) {

        // Header
        scrnMsg.techTocCd.setNameForMessage(DISPLAY_NM_TECH_TOC_CD);
        scrnMsg.techNm.setNameForMessage(DISPLAY_NM_TECH_NM);
        scrnMsg.physInvtyDt_DF.setNameForMessage(DISPLAY_NM_PHYS_INVTY_DT_DF);
        scrnMsg.physInvtyDt_DT.setNameForMessage(DISPLAY_NM_PHYS_INVTY_DT_DT);
        scrnMsg.physInvtyCtrlNm.setNameForMessage(DISPLAY_NM_PHYS_INVTY_CTRL_NM);
        scrnMsg.coaBrCd.setNameForMessage(DISPLAY_NM_COA_BR_CD);
        scrnMsg.coaBrNm.setNameForMessage(DISPLAY_NM_COA_BR_NM);
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlWhNm.setNameForMessage(DISPLAY_NM_RTL_WH_NM);
        // END   2018/12/11 T.Ogura [QC#28755,ADD]

    }

    /**
     * Table Column Protect
     * @param scrnMsg NLCL0630BMsg
     */
    public static void setTableScreen(NLCL0630BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).physInvtyNum_A1)) {
                // QC#28191
                if (PHYS_INVTY_STS.CLOSE.equals(scrnMsg.A.no(i).physInvtyStsCd_A1.getValue()) || PHYS_INVTY_STS.CANCELLED.equals(scrnMsg.A.no(i).physInvtyStsCd_A1.getValue())) {
                    EZDGUIAttribute radioButton = new EZDGUIAttribute(SCRN_ID, HTML_ID_RADIO_BUTTON + i);
                    radioButton.setVisibility(false);
                    scrnMsg.addGUIAttribute(radioButton);
                }
            } else {
                EZDGUIAttribute radioButton = new EZDGUIAttribute(SCRN_ID, HTML_ID_RADIO_BUTTON + i);
                radioButton.setVisibility(false);
                scrnMsg.addGUIAttribute(radioButton);
            }
            scrnMsg.A.no(i).techNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).physInvtyCtrlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).whCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).physInvtyStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).adjGrsAmt_A1.setAppFracDigit(DECIMAL_POINT_USD);
            scrnMsg.A.no(i).adjNetAmt_A1.setAppFracDigit(DECIMAL_POINT_USD);
        }
    }

    /**
     * Set Technician Search Param
     * @param scrnMsg NLCL0630BMsg
     * @param glblCmpyCd String
     * @return Technician Search Parameter
     */
    public static Object[] setTechnicianSearchParam(NLCL0630BMsg scrnMsg, String glblCmpyCd) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, WINDOW_TITLE_TECHNICIAN_SEARCH);

        return param;

    }

    /**
     * Set Tech PI Count Param
     * @param scrnMsg NLCL0630BMsg
     * @param glblCmpyCd String
     * @return Technician Search Parameter
     */
    public static Object[] setTechPiCountParam(NLCL0630BMsg scrnMsg, String glblCmpyCd) {

        int rowNum = scrnMsg.xxRadioBtn.getValueInt();

        // Paramter Set
        // START 2018/12/11 T.Ogura [QC#28755,MOD]
//        Object[] param = new Object[5];
        Object[] param = new Object[7];
        // END   2018/12/11 T.Ogura [QC#28755,MOD]
        param[0] = scrnMsg.A.no(rowNum).techTocCd_A1.getValue();
        param[1] = scrnMsg.A.no(rowNum).techNm_A1.getValue();
        param[2] = scrnMsg.A.no(rowNum).physInvtyDt_A1.getValue();
        param[3] = scrnMsg.A.no(rowNum).physInvtyCtrlNm_A1.getValue();
        param[4] = scrnMsg.A.no(rowNum).physInvtyNum_A1.getValue();
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        param[5] = scrnMsg.A.no(rowNum).rtlWhCd_A1.getValue();
        param[6] = scrnMsg.A.no(rowNum).rtlWhNm_A1.getValue();
        // END   2018/12/11 T.Ogura [QC#28755,ADD]

        return param;

    }

    /**
     * getSelectSQLForTechnician
     * @param scrnMsg NLCL0630BMsg
     * @param glblCmpyCd String
     * @return Select SQL
     */
    private static String getSelectSQLForTechnician(NLCL0630BMsg scrnMsg, String glblCmpyCd) {

        String userId = null;
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        }

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
        if (ZYPCommonFunc.hasValue(userId)) {
            sb.append("    AND (PSN.HR_SUPV_ID                     =  '#userId#'        ").append("\n");
            sb.append("        OR PSN.PSN_CD                       =  '#userId#')       ").append("\n");
        }
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        sb.append("    AND PSN.EFF_FROM_DT                    <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(PSN.EFF_THRU_DT, '99991231')   >=  '#salesDate#'     ").append("\n");
        // END   2018/12/11 T.Ogura [QC#28755,ADD]
        sb.append("    AND PSN.EZCANCELFLAG                    =  '0'               ").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(userId)) {
            sql = sql.replaceAll("#userId#", userId);
        }
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        sql = sql.replaceAll("#salesDate#", ZYPDateUtil.getSalesDate(glblCmpyCd));
        // END   2018/12/11 T.Ogura [QC#28755,ADD]

        return sql;
    }

    /**
     * getSearchConditionForTechnician
     * @param scrnMsg NLCL0630BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForTechnician(NLCL0630BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        // START 2018/12/11 T.Ogura [QC#28755,MOD]
//        Object[] whereObj1 = {WHERE_DISP_NM_FOR_TECHNICIAN_CODE, WHERE_SQL_NM_FOR_TECHNICIAN_CODE, scrnMsg.techTocCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj1 = new Object[4];
        whereObj1[0] = WHERE_DISP_NM_FOR_TECHNICIAN_CODE;
        whereObj1[1] = WHERE_SQL_NM_FOR_TECHNICIAN_CODE;
        if (ZYPCommonFunc.hasValue(scrnMsg.techTocCd)) {
            whereObj1[2] = scrnMsg.techTocCd.getValue();
        } else {
            whereObj1[2] = "%";
        }
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        // END   2018/12/11 T.Ogura [QC#28755,MOD]
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_TECHNICIAN_NAME, WHERE_SQL_NM_FOR_TECHNICIAN_NAME, scrnMsg.techNm.getValue(), ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForTechnician
     * @param scrnMsg NLCL0630BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForTechnician(NLCL0630BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_TECHNICIAN_CODE, COLUMN_SQL_NM_FOR_TECHNICIAN_CODE, COLUMN_WIDTH_FOR_TECHNICIAN_CODE, ZYPConstant.FLG_ON_Y  };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_TECHNICIAN_NAME, COLUMN_SQL_NM_FOR_TECHNICIAN_NAME, COLUMN_WIDTH_FOR_TECHNICIAN_NAME, ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    /**
     * getSortForTechnician
     * @param scrnMsg NLCL0630BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForTechnician(NLCL0630BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_TECHNICIAN_CODE, SORT_CONDITION_FOR_TECHNICIAN_CODE };

        sortList.add(sortObj1);

        return sortList;
    }

    /**
     * Set Tech Physical Search Param
     * @param scrnMsg NLCL0630BMsg
     * @param glblCmpyCd String
     * @return Technician Search Parameter
     */
    public static Object[] setTechPhysicalSearchParam(NLCL0630BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = WINDOW_TITLE_TECH_PHYSICAL_SEARCH;
        param[2] = getSelectSQLForTechPhysical(scrnMsg, glblCmpyCd);
        param[3] = getSearchConditionForTechPhysical(scrnMsg);
        param[4] = getDisplayColumnForTechPhysical(scrnMsg);
        param[5] = getSortForTechPhysical(scrnMsg);
        param[6] = scrnMsg.R;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, WINDOW_TITLE_TECH_PHYSICAL_SEARCH);

        return param;

    }

    /**
     * getSelectSQLForTechPhysical
     * @param scrnMsg NLCL0630BMsg
     * @param glblCmpyCd String
     * @return Select SQL
     */
    private static String getSelectSQLForTechPhysical(NLCL0630BMsg scrnMsg, String glblCmpyCd) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT                                                           ").append("\n");
        sb.append("    DISTINCT PIC.PHYS_INVTY_CTRL_NM,                             ").append("\n");
        sb.append("    PIC.GLBL_CMPY_CD,                                            ").append("\n");
        sb.append("    PIC.EZCANCELFLAG                                             ").append("\n");
        sb.append("FROM                                                             ").append("\n");
        sb.append("    PHYS_INVTY_CTRL PIC                                          ").append("\n");
        sb.append("WHERE                                                            ").append("\n");
        sb.append("        PIC.GLBL_CMPY_CD                    =  '#glblCmpyCd#'    ").append("\n");
        sb.append("    AND PIC.EZCANCELFLAG                    =  '0'               ").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);

        return sql;
    }

    /**
     * getSearchConditionForTechPhysical
     * @param scrnMsg NLCL0630BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForTechPhysical(NLCL0630BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        // START 2018/12/11 T.Ogura [QC#28755,ADD]
//        Object[] whereObj1 = {WHERE_DISP_NM_FOR_TECH_PHYSICAL_NAME, WHERE_SQL_NM_FOR_TECH_PHYSICAL_NAME, scrnMsg.physInvtyCtrlNm.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj1 = new Object[4];
        whereObj1[0] = WHERE_DISP_NM_FOR_TECH_PHYSICAL_NAME;
        whereObj1[1] = WHERE_SQL_NM_FOR_TECH_PHYSICAL_NAME;
        if (ZYPCommonFunc.hasValue(scrnMsg.physInvtyCtrlNm)) {
            whereObj1[2] = scrnMsg.physInvtyCtrlNm.getValue();
        } else {
            whereObj1[2] = "%";
        }
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        // END   2018/12/11 T.Ogura [QC#28755,ADD]

        whereList.add(whereObj1);

        return whereList;
    }

    /**
     * getDisplayColumnForTechPhysical
     * @param scrnMsg NLCL0630BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForTechPhysical(NLCL0630BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_TECH_PHYSICAL_NAME, COLUMN_SQL_NM_FOR_TECH_PHYSICAL_NAME, COLUMN_WIDTH_FOR_TECH_PHYSICAL_NAME, ZYPConstant.FLG_ON_Y  };

        colList.add(colObj1);

        return colList;
    }

    /**
     * getSortForTechPhysical
     * @param scrnMsg NLCL0630BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForTechPhysical(NLCL0630BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_TECH_PHYSICAL_NAME, SORT_CONDITION_FOR_TECH_PHYSICAL_NAME };

        sortList.add(sortObj1);

        return sortList;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList.contains(FUNC_ID_ALL_USERS);
    }
    // QC#16256 add end

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Set Location Search Param
     * @param scrnMsg NLCL0630BMsg
     * @param glblCmpyCd String
     * @return Location Search Parameter
     */
    public static Object[] setLocationSearchParam(NLCL0630BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = WINDOW_TITLE_LOCATION_SEARCH;
        param[2] = getSelectSQLForLocation(scrnMsg, glblCmpyCd, scrnMsg.techTocCd.getValue());
        param[3] = getSearchConditionForLocation(scrnMsg);
        param[4] = getDisplayColumnForLocation(scrnMsg);
        param[5] = getSortForLocation(scrnMsg);
        param[6] = scrnMsg.R;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, WINDOW_TITLE_LOCATION_SEARCH);

        return param;

    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * getSelectSQLForLocation
     * @param scrnMsg NLCL0630BMsg
     * @param glblCmpyCd String
     * @param techTocCd String
     * @return Select SQL
     */
    private static String getSelectSQLForLocation(NLCL0630BMsg scrnMsg, String glblCmpyCd, String techTocCd) {

        String userId = null;
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        }

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT                                                           ").append("\n");
        sb.append("    RW.RTL_WH_CD,                                                ").append("\n");
        sb.append("    RW.RTL_WH_NM,                                                ").append("\n");
        sb.append("    RW.GLBL_CMPY_CD,                                             ").append("\n");
        sb.append("    RW.EZCANCELFLAG                                              ").append("\n");
        sb.append("FROM                                                             ").append("\n");
        sb.append("    RTL_WH RW,                                                   ").append("\n");
        sb.append("    S21_PSN PSN                                                  ").append("\n");
        sb.append("WHERE                                                            ").append("\n");
        sb.append("        RW.GLBL_CMPY_CD                     =  '#glblCmpyCd#'    ").append("\n");
        if (ZYPCommonFunc.hasValue(techTocCd)) {
            sb.append("    AND RW.TECH_TOC_CD                      =  '#techTocCd#'     ").append("\n");
        }
        sb.append("    AND RW.EFF_FROM_DT                     <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(RW.EFF_THRU_DT, '99991231')    >=  '#salesDate#'     ").append("\n");
        sb.append("    AND RW.EZCANCELFLAG                     =  '0'               ").append("\n");
        sb.append("    AND RW.GLBL_CMPY_CD                     =  PSN.GLBL_CMPY_CD  ").append("\n");
        sb.append("    AND RW.TECH_TOC_CD                      =  PSN.PSN_CD        ").append("\n");
        if (ZYPCommonFunc.hasValue(userId)) {
            sb.append("    AND (PSN.HR_SUPV_ID                     =  '#userId#'        ").append("\n");
            sb.append("        OR PSN.PSN_CD                       =  '#userId#')       ").append("\n");
        }
        sb.append("    AND PSN.EFF_FROM_DT                    <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(PSN.EFF_THRU_DT, '99991231')   >=  '#salesDate#'     ").append("\n");
        sb.append("    AND PSN.EZCANCELFLAG                    =  '0'               ").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(techTocCd)) {
            sql = sql.replaceAll("#techTocCd#", techTocCd);
        }
        if (ZYPCommonFunc.hasValue(userId)) {
            sql = sql.replaceAll("#userId#", userId);
        }
        sql = sql.replaceAll("#salesDate#", ZYPDateUtil.getSalesDate(glblCmpyCd));

        return sql;
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * getSearchConditionForLocation
     * @param scrnMsg NLCL0630BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForLocation(NLCL0630BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[4];
        whereObj1[0] = WHERE_DISP_NM_FOR_LOCATION_CODE;
        whereObj1[1] = WHERE_SQL_NM_FOR_LOCATION_CODE;
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            whereObj1[2] = scrnMsg.rtlWhCd.getValue();
        } else {
            whereObj1[2] = "%";
        }
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_LOCATION_NAME, WHERE_SQL_NM_FOR_LOCATION_NAME, scrnMsg.rtlWhNm.getValue(), ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * getDisplayColumnForLocation
     * @param scrnMsg NLCL0630BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForLocation(NLCL0630BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_LOCATION_CODE, COLUMN_SQL_NM_FOR_LOCATION_CODE, COLUMN_WIDTH_FOR_LOCATION_CODE, ZYPConstant.FLG_ON_Y  };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_LOCATION_NAME, COLUMN_SQL_NM_FOR_LOCATION_NAME, COLUMN_WIDTH_FOR_LOCATION_NAME, ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * getSortForLocation
     * @param scrnMsg NLCL0630BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForLocation(NLCL0630BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_LOCATION_CODE, SORT_CONDITION_FOR_LOCATION_CODE };

        sortList.add(sortObj1);

        return sortList;
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

}
