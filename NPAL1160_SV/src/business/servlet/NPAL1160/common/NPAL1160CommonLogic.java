/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160.common;

import static business.servlet.NPAL1160.constant.NPAL1160Constant.BIZ_APP_ID;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_COPY;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_DEL_ROW;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_INS_ROW;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_LOC_UPLOAD;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_MEM_UPLOAD;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_OPENWIN_MEMBER;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.BTN_XX_FILE_DATA_UP;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_APVLLIMIT_TABLE_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_BTN_OPENWIN_TEAM;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_LOCATION_TABLE_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_MEMBER_TABLE_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_APVLLIMIT;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_LOCATION;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_MEMBER;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TEAM;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TECHMIN;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TECHTHRHD;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TRANSACTION;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TEAM_TABLE_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TECHMIN_TABLE_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TECHTHRESHOLD_TABLE_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TRANSACTION_TABLE_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.FUNCTION_UPDATE;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_0;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_1;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_11;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_2;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_20;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_3;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_30;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_4;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_5;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_6;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_60;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.IDX_7;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.NAMM0027E;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.NPAM1216E;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.PARAM_NM_TEAM_DESCRIPTION_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.SCRN_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1160.NPAL1160BMsg;
import business.servlet.NPAL1160.NPAL1160Bean;
import business.servlet.NPAL1160.NPAL1160_ABMsg;
import business.servlet.NPAL1160.NPAL1160_BBMsg;
import business.servlet.NPAL1160.NPAL1160_CBMsg;
import business.servlet.NPAL1160.NPAL1160_DBMsg;
import business.servlet.NPAL1160.NPAL1160_EBMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HRCH_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenance
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 03/23/2016   CITS            K.Ogino         Update          QC#5273
 * 03/23/2016   CITS            K.Ogino         Update          QC#5277
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8415
 * 01/11/2017   CITS            R.Shimamoto     Update          QC#17059
 * 11/14/2017   CITS            T.Tokutomi      Create          QC#18689-Sol#303
 * 02/23/2018   CITS            M.Naito         Update          QC#22601
 * 05/17/2023   Hitachi         T.Kuroda        Update          QC#61211
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public class NPAL1160CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1160BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Search Condition(TeamTab)
        scrnMsg.apvlTeamNm_TT.setInputProtected(false);
        scrnMsg.apvlTeamDescTxt_TT.setInputProtected(false);
        scrnMsg.apvlHrchTpCd_TC.setInputProtected(false);
        scrnMsg.apvlHrchTpCd_TS.setInputProtected(false);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * Table Column Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     */
    public static void setTableScreenA(EZDCommonHandler handler, NPAL1160BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(i).apvlTeamPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).apvlTeamNm_A1.setInputProtected(false);
            scrnMsg.A.no(i).apvlTeamDescTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).apvlHrchTpCd_AC.setInputProtected(false);
            scrnMsg.A.no(i).apvlHrchTpCd_AS.setInputProtected(false);

        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Table Column Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     */
    public static void setTableScreenB(EZDCommonHandler handler, NPAL1160BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).apvlTeamMbrPk_B1)) {
                if (scrnMsg.B.no(i).apvlTeamMbrPk_B1.getValueInt() != 0) {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, false);
                } else {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
                }
            } else {
                handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
            }

            scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
            scrnMsg.B.no(i).apvlTeamMbrPk_B1.setInputProtected(true);
            scrnMsg.B.no(i).apvlTeamPk_B1.setInputProtected(true);
            scrnMsg.B.no(i).apvlTeamNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).apvlTeamPosnTpCd_BC.setInputProtected(false);
            scrnMsg.B.no(i).apvlTeamPosnTpCd_BS.setInputProtected(false);
            scrnMsg.B.no(i).apvlTeamPosnTpDescTxt_BD.setInputProtected(false);
            scrnMsg.B.no(i).fullPsnNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).psnCd_B1.setInputProtected(false);
            handler.setButtonEnabled(BTN_OPENWIN_MEMBER, i, true);
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Table Column Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     */
    public static void setTableScreenC(EZDCommonHandler handler, NPAL1160BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).apvlTeamTrxPk_C1)) {
                if (scrnMsg.C.no(i).apvlTeamTrxPk_C1.getValueInt() != 0) {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, false);
                } else {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
                }
            } else {
                handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
            }

            scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(false);
            scrnMsg.C.no(i).apvlTeamTrxPk_C1.setInputProtected(true);
            scrnMsg.C.no(i).apvlTeamPk_C1.setInputProtected(true);
            scrnMsg.C.no(i).apvlTeamNm_C1.setInputProtected(true);

            scrnMsg.C.no(i).prchGrpCd_CC.setInputProtected(false);
            scrnMsg.C.no(i).prchGrpDescTxt_CD.setInputProtected(false);
            scrnMsg.C.no(i).prchGrpCd_CS.setInputProtected(false);

            scrnMsg.C.no(i).mdseItemTpCd_CC.setInputProtected(false);
            scrnMsg.C.no(i).mdseItemTpDescTxt_CD.setInputProtected(false);
            scrnMsg.C.no(i).mdseItemTpCd_CS.setInputProtected(false);

            scrnMsg.C.no(i).apvlHistSrcTpCd_CC.setInputProtected(false);
            scrnMsg.C.no(i).apvlHistSrcTpDescTxt_CD.setInputProtected(false);
            scrnMsg.C.no(i).apvlHistSrcTpCd_CS.setInputProtected(false);

        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Table Column Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     */
    public static void setTableScreenD(EZDCommonHandler handler, NPAL1160BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).apvlTeamLocPk_D1)) {
                if (scrnMsg.D.no(i).apvlTeamLocPk_D1.getValueInt() != 0) {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, false);
                } else {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
                }
            } else {
                handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
            }

            scrnMsg.D.no(i).xxChkBox_D1.setInputProtected(false);
            scrnMsg.D.no(i).apvlTeamLocPk_D1.setInputProtected(true);
            scrnMsg.D.no(i).apvlTeamPk_D1.setInputProtected(true);
            scrnMsg.D.no(i).apvlTeamNm_D1.setInputProtected(true);
            scrnMsg.D.no(i).rtlWhCd_D1.setInputProtected(false);
            scrnMsg.D.no(i).rtlWhNm_D1.setInputProtected(true);

        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Table Column Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     */
    public static void setTableScreenE(EZDCommonHandler handler, NPAL1160BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);

        for (int i = 0; i < scrnMsg.E.length(); i++) {

            scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(false);
            scrnMsg.E.no(i).apvlLimitPk_E1.setInputProtected(true);

            scrnMsg.E.no(i).apvlHrchTpCd_ES.setInputProtected(false);

            if (APVL_HRCH_TP.POSITIONAL.equals(scrnMsg.E.no(i).apvlHrchTpCd_ES.getValue())) {
                scrnMsg.E.no(i).apvlTeamPosnTpCd_ES.setInputProtected(false);
                handler.setButtonEnabled(BTN_OPENWIN_MEMBER, i, false);
                scrnMsg.E.no(i).psnCd_E1.setInputProtected(true);
                scrnMsg.E.no(i).psnCd_E1.clear();
                scrnMsg.E.no(i).fullPsnNm_E1.clear();
            } else {
                scrnMsg.E.no(i).apvlTeamPosnTpCd_ES.clear();
                scrnMsg.E.no(i).apvlTeamPosnTpCd_ES.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_MEMBER, i, true);
                scrnMsg.E.no(i).psnCd_E1.setInputProtected(false);
            }
            scrnMsg.E.no(i).fullPsnNm_E1.setInputProtected(true);

            scrnMsg.E.no(i).prchGrpCd_EC.setInputProtected(false);
            scrnMsg.E.no(i).prchGrpDescTxt_ED.setInputProtected(false);
            scrnMsg.E.no(i).prchGrpCd_ES.setInputProtected(false);

            scrnMsg.E.no(i).apvlHistSrcTpCd_EC.setInputProtected(false);
            scrnMsg.E.no(i).apvlHistSrcTpDescTxt_ED.setInputProtected(false);
            scrnMsg.E.no(i).apvlHistSrcTpCd_ES.setInputProtected(false);

            // START 2023/08/29 M.Kikushima [QC#61590, ADD]
            if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(scrnMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())
                || APVL_HIST_SRC_TP.PO_REQUISITION.equals(scrnMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())) {

                scrnMsg.E.no(i).prchReqTpCd_EC.setInputProtected(false);
                scrnMsg.E.no(i).prchReqTpNm_ED.setInputProtected(false);
                scrnMsg.E.no(i).prchReqTpCd_ES.setInputProtected(false);
            } else {
                scrnMsg.E.no(i).prchReqTpCd_EC.setInputProtected(true);
                scrnMsg.E.no(i).prchReqTpNm_ED.setInputProtected(true);
                scrnMsg.E.no(i).prchReqTpCd_ES.clear();
                scrnMsg.E.no(i).prchReqTpCd_ES.setInputProtected(true);
            }
            // END 2023/08/29 M.Kikushima [QC#61590, ADD]

            scrnMsg.E.no(i).apvlLimitAmt_E1.setAppFracDigit(IDX_2);
            scrnMsg.E.no(i).apvlLimitAmt_E1.setInputProtected(false);
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Table Column Protect
     *  ADD QC#18689-Sol#303
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     */
    public static void setTableScreenK(EZDCommonHandler handler, NPAL1160BMsg scrnMsg, List<String> functionList) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DISPLAY_TECHTHRESHOLD_TABLE_NAME, scrnMsg.K);

        // Detail
        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {

            scrnMsg.K.no(i).lineBizTpDescTxt_K1.setInputProtected(true);
            scrnMsg.K.no(i).techApvlThrhdAmt_K1.setAppFracDigit(2);
            if (functionList.contains(FUNCTION_UPDATE)) {
                scrnMsg.K.no(i).techApvlThrhdAmt_K1.setInputProtected(false);
            } else {
                scrnMsg.K.no(i).techApvlThrhdAmt_K1.setInputProtected(true);
            }
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * Table Column Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     */
    public static void setTableScreenL(EZDCommonHandler handler, NPAL1160BMsg scrnMsg, List<String> functionList) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);

        // Detail
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

            scrnMsg.L.no(i).lineBizTpDescTxt_L1.setInputProtected(true);
            scrnMsg.L.no(i).techApvlMinAmt_L1.setAppFracDigit(2);
            if (functionList.contains(FUNCTION_UPDATE)) {
                scrnMsg.L.no(i).techApvlMinAmt_L1.setInputProtected(false);
            } else {
                scrnMsg.L.no(i).techApvlMinAmt_L1.setInputProtected(true);
            }
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1160BMsg
     * @param functionList List<String>
     */
    public static void setAuthorityProtect(EZDCommonHandler handler, NPAL1160BMsg scrnMsg, List<String> functionList) {

        // Button
        if (functionList.contains(FUNCTION_UPDATE)) {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            setBtnEnabled(handler, true);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            setBtnEnabled(handler, false);
        }

        if (DISPLAY_TAB_NM_TEAM.equals(scrnMsg.xxDplyTab.getValue())) {

            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);

            // Detail
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).apvlTeamNm_A1.setInputProtected(false);
                scrnMsg.A.no(i).apvlTeamDescTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).apvlHrchTpCd_AS.setInputProtected(false);
            }

        } else if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {

            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);

            // Detail
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
                scrnMsg.B.no(i).apvlTeamNm_B1.setInputProtected(true);
                scrnMsg.B.no(i).apvlTeamPosnTpCd_BS.setInputProtected(false);
                scrnMsg.B.no(i).psnCd_B1.setInputProtected(false);
                scrnMsg.B.no(i).fullPsnNm_B1.setInputProtected(true);
                if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).apvlTeamPk_B1)) {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, false);
                } else {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
                }
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(scrnMsg.xxDplyTab.getValue())) {

            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);

            // Detail
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

                scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(false);
                scrnMsg.C.no(i).apvlTeamNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).prchGrpCd_CS.setInputProtected(false);
                scrnMsg.C.no(i).mdseItemTpCd_CS.setInputProtected(false);
                scrnMsg.C.no(i).apvlHistSrcTpCd_CS.setInputProtected(false);
                if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).apvlTeamPk_C1)) {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, false);
                } else {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
                }
            }

        } else if (DISPLAY_TAB_NM_LOCATION.equals(scrnMsg.xxDplyTab.getValue())) {

            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);

            // Detail
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {

                scrnMsg.D.no(i).xxChkBox_D1.setInputProtected(false);
                scrnMsg.D.no(i).apvlTeamNm_D1.setInputProtected(true);
                scrnMsg.D.no(i).rtlWhCd_D1.setInputProtected(false);
                scrnMsg.D.no(i).rtlWhNm_D1.setInputProtected(true);
                if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).apvlTeamPk_D1)) {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, false);
                } else {
                    handler.setButtonEnabled(DISPLAY_BTN_OPENWIN_TEAM, i, true);
                }
            }

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(scrnMsg.xxDplyTab.getValue())) {

            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);

            // Detail
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {

                scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(false);
                scrnMsg.E.no(i).apvlHrchTpCd_ES.setInputProtected(false);
                scrnMsg.E.no(i).apvlTeamPosnTpCd_ES.setInputProtected(false);
                handler.setButtonEnabled(BTN_OPENWIN_MEMBER, i, false);
                scrnMsg.E.no(i).psnCd_E1.setInputProtected(false);
                scrnMsg.E.no(i).fullPsnNm_E1.setInputProtected(true);
                scrnMsg.E.no(i).prchGrpCd_ES.setInputProtected(false);
                scrnMsg.E.no(i).apvlHistSrcTpCd_ES.setInputProtected(false);
                // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(scrnMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())
                    || APVL_HIST_SRC_TP.PO_REQUISITION.equals(scrnMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())) {
                    scrnMsg.E.no(i).prchReqTpCd_ES.setInputProtected(false);
                } else {
                    scrnMsg.E.no(i).prchReqTpCd_ES.setInputProtected(true);
                }
                // END 2023/08/29 M.Kikushima [QC#61590, ADD]
                scrnMsg.E.no(i).apvlLimitAmt_E1.setInputProtected(false);
            }
        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(scrnMsg.xxDplyTab.getValue())) {

            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.K);

            // Detail
            for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {

                scrnMsg.K.no(i).lineBizTpDescTxt_K1.setInputProtected(true);
                scrnMsg.K.no(i).techApvlThrhdAmt_K1.setAppFracDigit(2);
                if (functionList.contains(FUNCTION_UPDATE)) {
                    scrnMsg.K.no(i).techApvlThrhdAmt_K1.setInputProtected(false);
                } else {
                    scrnMsg.K.no(i).techApvlThrhdAmt_K1.setInputProtected(true);
                }
            }
        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(scrnMsg.xxDplyTab.getValue())) {

            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);

            // Detail
            for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

                scrnMsg.L.no(i).lineBizTpDescTxt_L1.setInputProtected(true);
                scrnMsg.L.no(i).techApvlMinAmt_L1.setAppFracDigit(2);
                if (functionList.contains(FUNCTION_UPDATE)) {
                    scrnMsg.L.no(i).techApvlMinAmt_L1.setInputProtected(false);
                } else {
                    scrnMsg.L.no(i).techApvlMinAmt_L1.setInputProtected(true);
                }
            }
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]
    }

    /**
     * Button Enabled
     * @param handler EZDCommonHandler
     * @param enabled boolean
     */
    private static void setBtnEnabled(EZDCommonHandler handler, boolean enabled) {
        handler.setButtonEnabled(BTN_XX_FILE_DATA_UP, enabled);
        handler.setButtonEnabled(BTN_INS_ROW, enabled);
        handler.setButtonEnabled(BTN_DEL_ROW, enabled);
        handler.setButtonEnabled(BTN_MEM_UPLOAD, enabled);
        handler.setButtonEnabled(BTN_LOC_UPLOAD, enabled);
        handler.setButtonEnabled(BTN_COPY, enabled);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @return Object[]
     */
    public static Object[] setTeamPopupParamForMemberHeader(NPAL1160BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Team");

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Team Search Popup";
        params[IDX_2] = "APVL_TEAM";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Team Name";
        whereArray1[IDX_1] = "APVL_TEAM_NM";

        if (ZYPCommonFunc.hasValue(scrnMsg.apvlTeamNm_MT)) {
            whereArray1[IDX_2] = scrnMsg.apvlTeamNm_MT.getValue();
        }

        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        whereArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Team Name";
        columnArray1[IDX_1] = "APVL_TEAM_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        columnArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "APVL_TEAM_NM";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @return Object[]
     */
    public static Object[] setTeamPopupParamForMemberDetail(NPAL1160BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Team");

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Team Search Popup";
        params[IDX_2] = "APVL_TEAM";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Team Name";
        whereArray1[IDX_1] = "APVL_TEAM_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        whereArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Team Name";
        columnArray1[IDX_1] = "APVL_TEAM_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        columnArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "APVL_TEAM_NM";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;

    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @return Object[]
     */
    public static Object[] setTeamPopupParamForTransactionHeader(NPAL1160BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Team");

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Team Search Popup";
        params[IDX_2] = "APVL_TEAM";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Team Name";
        whereArray1[IDX_1] = "APVL_TEAM_NM";

        if (ZYPCommonFunc.hasValue(scrnMsg.apvlTeamNm_ST)) {
            whereArray1[IDX_2] = scrnMsg.apvlTeamNm_ST.getValue();
        }

        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        whereArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Team Name";
        columnArray1[IDX_1] = "APVL_TEAM_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        columnArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "APVL_TEAM_NM";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @return Object[]
     */
    public static Object[] setTeamPopupParamForTransactionDetail(NPAL1160BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Team");

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Team Search Popup";
        params[IDX_2] = "APVL_TEAM";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Team Name";
        whereArray1[IDX_1] = "APVL_TEAM_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        whereArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Team Name";
        columnArray1[IDX_1] = "APVL_TEAM_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        columnArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "APVL_TEAM_NM";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @return Object[]
     */
    public static Object[] setTeamPopupParamForLocationHeader(NPAL1160BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Team");

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Team Search Popup";
        params[IDX_2] = "APVL_TEAM";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Team Name";
        whereArray1[IDX_1] = "APVL_TEAM_NM";

        if (ZYPCommonFunc.hasValue(scrnMsg.apvlTeamNm_LT)) {
            whereArray1[IDX_2] = scrnMsg.apvlTeamNm_LT.getValue();
        }

        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        whereArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Team Name";
        columnArray1[IDX_1] = "APVL_TEAM_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        columnArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "APVL_TEAM_NM";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @return Object[]
     */
    public static Object[] setTeamPopupParamForLocationDetail(NPAL1160BMsg scrnMsg) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Team");

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Team Search Popup";
        params[IDX_2] = "APVL_TEAM";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Team Name";
        whereArray1[IDX_1] = "APVL_TEAM_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        whereArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Team Name";
        columnArray1[IDX_1] = "APVL_TEAM_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = PARAM_NM_TEAM_DESCRIPTION_NAME;
        columnArray2[IDX_1] = "APVL_TEAM_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "APVL_TEAM_NM";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setMemberPopupParamForMemberHeader(NPAL1160BMsg scrnMsg, String glblCmpyCd) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Member");
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Member Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,V.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,V.USR_NM AS PSN_CD");
        // START 2018/02/23 M.Naito [QC#22601,MOD]
//        sb.append("    ,TRIM (V.LAST_NM) || ' ' || TRIM (V.FIRST_NM) AS FULL_PSN_NM");
        sb.append("    ,TRIM (V.FIRST_NM) || ' ' || TRIM (V.LAST_NM) AS FULL_PSN_NM");
        // END 2018/02/23 M.Naito [QC#22601,MOD]
        sb.append(" FROM");
        sb.append("    AUTH_PSN V");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        // START 2020/05/18 [QC#56549,MOD]
        sb.append(" UNION ALL");
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,SSUL.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,SSUL.PSN_CD AS PSN_CD");
        sb.append("    ,SSUL.FULL_PSN_NM AS FULL_PSN_NM");
        sb.append(" FROM");
        sb.append("    SCE_SYS_USR_LIST SSUL");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND SSUL.EZCANCELFLAG = '0'");
        // END 2020/05/18 [QC#56549,MOD]
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Member Id";
        whereArray1[IDX_1] = "PSN_CD";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Member Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = scrnMsg.fullPsnNm_MT.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Member Id";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Member Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @param glblCmpyCd String
     * @param rownum int
     * @return Object[]
     */
    public static Object[] setMemberPopupParamForMemberDetail(NPAL1160BMsg scrnMsg, String glblCmpyCd, int rownum) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Member");
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Member Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,V.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,V.USR_NM AS PSN_CD");
        // START 2018/02/23 M.Naito [QC#22601,MOD]
//        sb.append("    ,TRIM (V.LAST_NM) || ' ' || TRIM (V.FIRST_NM) AS FULL_PSN_NM");
        sb.append("    ,TRIM (V.FIRST_NM) || ' ' || TRIM (V.LAST_NM) AS FULL_PSN_NM");
        // END 2018/02/23 M.Naito [QC#22601,MOD]
        sb.append(" FROM");
        sb.append("    AUTH_PSN V");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        // START 2020/05/18 [QC#56549,MOD]
        sb.append(" UNION ALL");
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,SSUL.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,SSUL.PSN_CD AS PSN_CD");
        sb.append("    ,SSUL.FULL_PSN_NM AS FULL_PSN_NM");
        sb.append(" FROM");
        sb.append("    SCE_SYS_USR_LIST SSUL");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND SSUL.EZCANCELFLAG = '0'");
        // END 2020/05/18 [QC#56549,MOD]
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Member Id";
        whereArray1[IDX_1] = "PSN_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(rownum).psnCd_B1)) {
            whereArray1[IDX_2] = scrnMsg.B.no(rownum).psnCd_B1.getValue();
        } else {
            whereArray1[IDX_2] = "";
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Member Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Member Id";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Member Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @param glblCmpyCd String
     * @param rownum int
     * @return Object[]
     */
    public static Object[] setMemberPopupParamForApprovalLimitDetail(NPAL1160BMsg scrnMsg, String glblCmpyCd, int rownum) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Member");
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Member Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,V.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,V.USR_NM AS PSN_CD");
        // START 2018/02/23 M.Naito [QC#22601,MOD]
//        sb.append("    ,TRIM (V.LAST_NM) || ' ' || TRIM (V.FIRST_NM) AS FULL_PSN_NM");
        sb.append("    ,TRIM (V.FIRST_NM) || ' ' || TRIM (V.LAST_NM) AS FULL_PSN_NM");
        // END 2018/02/23 M.Naito [QC#22601,MOD]
        sb.append(" FROM");
        sb.append("    AUTH_PSN V");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        // START 2020/05/18 [QC#56549,MOD]
        sb.append(" UNION ALL");
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,SSUL.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,SSUL.PSN_CD AS PSN_CD");
        sb.append("    ,SSUL.FULL_PSN_NM AS FULL_PSN_NM");
        sb.append(" FROM");
        sb.append("    SCE_SYS_USR_LIST SSUL");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND SSUL.EZCANCELFLAG = '0'");
        // END 2020/05/18 [QC#56549,MOD]
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Member Id";
        whereArray1[IDX_1] = "PSN_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.E.no(rownum).psnCd_E1)) {
            whereArray1[IDX_2] = scrnMsg.E.no(rownum).psnCd_E1.getValue();
        } else {
            whereArray1[IDX_2] = "";
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Member Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Member Id";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Member Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /** QC#17059 Add.
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1160BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setMemberPopupParamForApprovalLimitHeader(NPAL1160BMsg scrnMsg, String glblCmpyCd) {

        scrnMsg.xxScrEventNm_P1.clear();
        scrnMsg.xxScrEventNm_P1.setValue("OpenWin_Member");
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Employee Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,V.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,V.USR_NM AS PSN_CD");
        // START 2018/02/23 M.Naito [QC#22601,MOD]
//        sb.append("    ,TRIM (V.LAST_NM) || ' ' || TRIM (V.FIRST_NM) AS FULL_PSN_NM");
        sb.append("    ,TRIM (V.FIRST_NM) || ' ' || TRIM (V.LAST_NM) AS FULL_PSN_NM");
        // END 2018/02/23 M.Naito [QC#22601,MOD]
        sb.append(" FROM");
        sb.append("    AUTH_PSN V");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        // START 2020/05/18 [QC#56549,MOD]
        sb.append(" UNION ALL");
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,SSUL.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,SSUL.PSN_CD AS PSN_CD");
        sb.append("    ,SSUL.FULL_PSN_NM AS FULL_PSN_NM");
        sb.append(" FROM");
        sb.append("    SCE_SYS_USR_LIST SSUL");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND SSUL.EZCANCELFLAG = '0'");
        // END 2020/05/18 [QC#56549,MOD]
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Employee Id";
        whereArray1[IDX_1] = "PSN_CD";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Employee Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = scrnMsg.fullPsnNm_AT.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Employee Id";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Employee Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * Set Warehouse Popup param
     * @param scrnMsg NPAL1160BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setWarehousePopupParamForHeader(NPAL1160BMsg scrnMsg, String glblCmpyCd) {

        String locRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BIZ_APP_ID);

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, locRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, "01");

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm_LT)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm_LT.getValue().toUpperCase());
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm_LT);
        }

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * Set Warehouse Popup param
     * @param scrnMsg NPAL1160BMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @return Object[]
     */
    public static Object[] setWarehousePopupParamForDetail(NPAL1160BMsg scrnMsg, String glblCmpyCd, int rowNum) {

        String locRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BIZ_APP_ID);

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, locRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, "01");

        if (ZYPCommonFunc.hasValue(scrnMsg.D.no(rowNum).rtlWhCd_D1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.D.no(rowNum).rtlWhCd_D1.getValue().toUpperCase());
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.D.no(rowNum).rtlWhCd_D1);
        }

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     */
    public static void controlItemsAddNewLineTeam(EZDCommonHandler handler, NPAL1160BMsg bMsg) {
        int index = bMsg.A.getValidCount() - 1;
        bMsg.A.no(index).xxChkBox_A1.setInputProtected(false);
        bMsg.A.no(index).apvlTeamNm_A1.setInputProtected(false);
        bMsg.A.no(index).apvlTeamDescTxt_A1.setInputProtected(false);
        bMsg.A.no(index).apvlHrchTpCd_AS.setInputProtected(false);

    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     * @param rowNum BigDecimal
     */
    public static void controlItemsCopyLineTeam(EZDCommonHandler handler, NPAL1160BMsg bMsg, BigDecimal rowNum) {
        int index = 0;
        int count = 1;
        for (int i = 0; i < rowNum.intValue(); i++) {
            index = bMsg.A.getValidCount() - count;
            bMsg.A.no(index).xxChkBox_A1.setInputProtected(false);
            bMsg.A.no(index).apvlTeamNm_A1.setInputProtected(false);
            bMsg.A.no(index).apvlTeamDescTxt_A1.setInputProtected(false);
            bMsg.A.no(index).apvlHrchTpCd_AS.setInputProtected(false);
            count++;
        }

    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     */
    public static void controlItemsAddNewLineMember(EZDCommonHandler handler, NPAL1160BMsg bMsg) {
        int index = bMsg.B.getValidCount() - 1;
        bMsg.B.no(index).xxChkBox_B1.setInputProtected(false);
        handler.setButtonEnabled("OpenWin_Team", index, true);
        bMsg.B.no(index).apvlTeamNm_B1.setInputProtected(true);
        bMsg.B.no(index).apvlTeamPosnTpCd_BS.setInputProtected(false);
        bMsg.B.no(index).psnCd_B1.setInputProtected(false);
        handler.setButtonEnabled("OpenWin_Member", index, true);
        bMsg.B.no(index).fullPsnNm_B1.setInputProtected(true);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     * @param rowNum BigDecimal
     */
    public static void controlItemsCopyLineMember(EZDCommonHandler handler, NPAL1160BMsg bMsg, BigDecimal rowNum) {
        int index = 0;
        int count = 1;
        for (int i = 0; i < rowNum.intValue(); i++) {
            index = bMsg.B.getValidCount() - count;

            bMsg.B.no(index).xxChkBox_B1.setInputProtected(false);
            handler.setButtonEnabled("OpenWin_Team", index, true);
            bMsg.B.no(index).apvlTeamNm_B1.setInputProtected(true);
            bMsg.B.no(index).apvlTeamPosnTpCd_BS.setInputProtected(false);
            bMsg.B.no(index).psnCd_B1.setInputProtected(false);
            handler.setButtonEnabled("OpenWin_Member", index, true);
            bMsg.B.no(index).fullPsnNm_B1.setInputProtected(true);
            count++;
        }

    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     */
    public static void controlItemsAddNewLineTransaction(EZDCommonHandler handler, NPAL1160BMsg bMsg) {
        int index = bMsg.C.getValidCount() - 1;
        bMsg.C.no(index).xxChkBox_C1.setInputProtected(false);
        handler.setButtonEnabled("OpenWin_Team", index, true);
        bMsg.C.no(index).apvlTeamNm_C1.setInputProtected(true);
        bMsg.C.no(index).prchGrpCd_CS.setInputProtected(false);
        bMsg.C.no(index).mdseItemTpCd_CS.setInputProtected(false);
        bMsg.C.no(index).apvlHistSrcTpCd_CS.setInputProtected(false);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     * @param rowNum BigDecimal
     */
    public static void controlItemsCopyLineTransaction(EZDCommonHandler handler, NPAL1160BMsg bMsg, BigDecimal rowNum) {
        int index = 0;
        int count = 1;
        for (int i = 0; i < rowNum.intValue(); i++) {
            index = bMsg.C.getValidCount() - count;

            bMsg.C.no(index).xxChkBox_C1.setInputProtected(false);
            handler.setButtonEnabled("OpenWin_Team", index, true);
            bMsg.C.no(index).apvlTeamNm_C1.setInputProtected(true);
            bMsg.C.no(index).prchGrpCd_CS.setInputProtected(false);
            bMsg.C.no(index).mdseItemTpCd_CS.setInputProtected(false);
            bMsg.C.no(index).apvlHistSrcTpCd_CS.setInputProtected(false);
            count++;
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     */
    public static void controlItemsAddNewLineLocation(EZDCommonHandler handler, NPAL1160BMsg bMsg) {
        int index = bMsg.D.getValidCount() - 1;
        bMsg.D.no(index).xxChkBox_D1.setInputProtected(false);
        handler.setButtonEnabled("OpenWin_Team", index, true);
        bMsg.D.no(index).apvlTeamNm_D1.setInputProtected(true);
        handler.setButtonEnabled("OpenWin_WH", index, true);
        bMsg.D.no(index).rtlWhCd_D1.setInputProtected(false);
        bMsg.D.no(index).rtlWhNm_D1.setInputProtected(true);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     * @param rowNum BigDecimal
     */
    public static void controlItemsCopyLineLocation(EZDCommonHandler handler, NPAL1160BMsg bMsg, BigDecimal rowNum) {
        int index = 0;
        int count = 1;
        for (int i = 0; i < rowNum.intValue(); i++) {
            index = bMsg.D.getValidCount() - count;
            bMsg.D.no(index).xxChkBox_D1.setInputProtected(false);
            handler.setButtonEnabled("OpenWin_Team", index, true);
            bMsg.D.no(index).apvlTeamNm_D1.setInputProtected(true);
            handler.setButtonEnabled("OpenWin_WH", index, true);
            bMsg.D.no(index).rtlWhCd_D1.setInputProtected(false);
            bMsg.D.no(index).rtlWhNm_D1.setInputProtected(true);
            count++;
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     */
    public static void controlItemsAddNewLineApvlLimit(EZDCommonHandler handler, NPAL1160BMsg bMsg) {
        int index = bMsg.E.getValidCount() - 1;
        bMsg.E.no(index).xxChkBox_E1.setInputProtected(false);
        bMsg.E.no(index).apvlHrchTpCd_ES.setInputProtected(false);
        bMsg.E.no(index).apvlTeamPosnTpCd_ES.setInputProtected(false);
        handler.setButtonEnabled("OpenWin_Member", index, true);
        bMsg.E.no(index).psnCd_E1.setInputProtected(false);
        bMsg.E.no(index).fullPsnNm_E1.setInputProtected(true);
        bMsg.E.no(index).prchGrpCd_ES.setInputProtected(false);
        bMsg.E.no(index).apvlHistSrcTpCd_ES.setInputProtected(false);
        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
        bMsg.E.no(index).prchReqTpCd_ES.setInputProtected(false);
        // END 2023/08/29 M.Kikushima [QC#61590, ADD]
        bMsg.E.no(index).apvlLimitAmt_E1.setInputProtected(false);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1160BMsg
     * @param rowNum BigDecimal
     */
    public static void controlItemsCopyLineApvlLimit(EZDCommonHandler handler, NPAL1160BMsg bMsg, BigDecimal rowNum) {
        int index = 0;
        int count = 1;
        for (int i = 0; i < rowNum.intValue(); i++) {
            index = bMsg.E.getValidCount() - count;
            bMsg.E.no(index).xxChkBox_E1.setInputProtected(false);
            bMsg.E.no(index).apvlHrchTpCd_ES.setInputProtected(false);
            bMsg.E.no(index).apvlTeamPosnTpCd_ES.setInputProtected(false);
            handler.setButtonEnabled("OpenWin_Member", index, true);
            bMsg.E.no(index).psnCd_E1.setInputProtected(false);
            bMsg.E.no(index).fullPsnNm_E1.setInputProtected(true);
            bMsg.E.no(index).prchGrpCd_ES.setInputProtected(false);
            bMsg.E.no(index).apvlHistSrcTpCd_ES.setInputProtected(false);
            // START 2023/08/29 M.Kikushima [QC#61590, ADD]
            if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(bMsg.E.no(index).apvlHistSrcTpCd_ES.getValue())
                || APVL_HIST_SRC_TP.PO_REQUISITION.equals(bMsg.E.no(index).apvlHistSrcTpCd_ES.getValue())) {
                bMsg.E.no(index).prchReqTpCd_ES.setInputProtected(false);
            } else {
                bMsg.E.no(index).prchReqTpCd_ES.setInputProtected(true);
            }
            // END 2023/08/29 M.Kikushima [QC#61590, ADD]
            bMsg.E.no(index).apvlLimitAmt_E1.setInputProtected(false);
            count++;
        }

    }

    /**
     * checkInput_arrayItem
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkInput_arrayItemA(NPAL1160BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[] {NPAL1160Bean.xxChkBox_A1, NPAL1160Bean.apvlTeamNm_A1, NPAL1160Bean.apvlTeamDescTxt_A1, NPAL1160Bean.apvlHistSrcTpCd_AS }, 1);
        scrnMsg.putErrorScreen();
    }

    /**
     * checkInput_arrayItem
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkInput_arrayItemB(NPAL1160BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.B);
        scrnMsg.B.setCheckParam(new String[] {NPAL1160Bean.xxChkBox_B1, NPAL1160Bean.apvlTeamNm_B1, NPAL1160Bean.apvlTeamPosnTpCd_AS, NPAL1160Bean.psnCd_B1, NPAL1160Bean.fullPsnNm_B1 }, 1);
        scrnMsg.putErrorScreen();
    }

    /**
     * checkInput_arrayItem
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkInput_arrayItemC(NPAL1160BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.C);
        scrnMsg.C.setCheckParam(new String[] {NPAL1160Bean.xxChkBox_C1, NPAL1160Bean.apvlTeamNm_C1, NPAL1160Bean.prchGrpCd_CS, NPAL1160Bean.mdseItemTpCd_CS, NPAL1160Bean.apvlHrchTpCd_LS }, 1);
        scrnMsg.putErrorScreen();
    }

    /**
     * checkInput_arrayItem
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkInput_arrayItemD(NPAL1160BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.D);
        scrnMsg.D.setCheckParam(new String[] {NPAL1160Bean.xxChkBox_D1, NPAL1160Bean.apvlTeamNm_D1, NPAL1160Bean.rtlWhCd_D1, NPAL1160Bean.rtlWhNm_D1 }, 1);
        scrnMsg.putErrorScreen();
    }

    /**
     * checkInput_arrayItem
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkInput_arrayItemE(NPAL1160BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.E);
        scrnMsg.E.setCheckParam(new String[] {NPAL1160Bean.apvlHrchTpCd_ES, NPAL1160Bean.apvlTeamPosnTpCd_ES, NPAL1160Bean.psnCd_E1, NPAL1160Bean.fullPsnNm_E1, NPAL1160Bean.prchGrpCd_ES, NPAL1160Bean.apvlHistSrcTpCd_ES,
                NPAL1160Bean.apvlLimitAmt_E1 }, 1);
        scrnMsg.putErrorScreen();
    }

    /**
     * checkSubmit
     * @param scrnMsg NPAL1160BMsg
     * @param glblCmpyCd String
     */
    public static void checkSubmit(NPAL1160BMsg scrnMsg, String glblCmpyCd) {

        if (DISPLAY_TAB_NM_TEAM.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apvlTeamNm_A1))) {
                    scrnMsg.A.no(i).apvlTeamNm_A1.setErrorInfo(1, NAMM0027E, new String[] {"Team Name" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).apvlTeamNm_A1);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apvlHrchTpCd_AS))) {
                    scrnMsg.A.no(i).apvlHrchTpCd_AS.setErrorInfo(1, NAMM0027E, new String[] {"Hierarchy Type" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).apvlHrchTpCd_AS);
                }
                scrnMsg.addCheckItem(scrnMsg.A.no(i).apvlTeamDescTxt_A1);
            }

        } else if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
            // Input Attribute Check Area
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                if (!(ZYPCommonFunc.hasValue(scrnMsg.B.no(i).apvlTeamNm_B1))) {
                    scrnMsg.B.no(i).apvlTeamNm_B1.setErrorInfo(1, NAMM0027E, new String[] {"Team Name" });
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).apvlTeamNm_B1);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.B.no(i).apvlTeamPosnTpCd_BS))) {
                    scrnMsg.B.no(i).apvlTeamPosnTpCd_BS.setErrorInfo(1, NAMM0027E, new String[] {"Position" });
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).apvlTeamPosnTpCd_BS);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.B.no(i).psnCd_B1))) {
                    scrnMsg.B.no(i).psnCd_B1.setErrorInfo(1, NAMM0027E, new String[] {"Member Code" });
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).psnCd_B1);
                }
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(scrnMsg.xxDplyTab.getValue())) {
            // Input Attribute Check Area
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

                if (!(ZYPCommonFunc.hasValue(scrnMsg.C.no(i).apvlTeamNm_C1))) {
                    scrnMsg.C.no(i).apvlTeamNm_C1.setErrorInfo(1, NAMM0027E, new String[] {"Team Name" });
                    scrnMsg.addCheckItem(scrnMsg.C.no(i).apvlTeamNm_C1);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.C.no(i).apvlHistSrcTpCd_CS))) {
                    scrnMsg.C.no(i).apvlHistSrcTpCd_CS.setErrorInfo(1, NAMM0027E, new String[] {"Transactions" });
                    scrnMsg.addCheckItem(scrnMsg.C.no(i).apvlHistSrcTpCd_CS);
                }

            }

        } else if (DISPLAY_TAB_NM_LOCATION.equals(scrnMsg.xxDplyTab.getValue())) {
            // Input Attribute Check Area
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {

                if (!(ZYPCommonFunc.hasValue(scrnMsg.D.no(i).apvlTeamNm_D1))) {
                    scrnMsg.D.no(i).apvlTeamNm_D1.setErrorInfo(1, NAMM0027E, new String[] {"Team Name" });
                    scrnMsg.addCheckItem(scrnMsg.D.no(i).apvlTeamNm_D1);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.D.no(i).rtlWhCd_D1))) {
                    scrnMsg.D.no(i).rtlWhCd_D1.setErrorInfo(1, NAMM0027E, new String[] {"WH Code" });
                    scrnMsg.addCheckItem(scrnMsg.D.no(i).rtlWhCd_D1);
                }

            }

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(scrnMsg.xxDplyTab.getValue())) {
            // Input Attribute Check Area
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {

                if (!(ZYPCommonFunc.hasValue(scrnMsg.E.no(i).apvlHrchTpCd_ES))) {
                    scrnMsg.E.no(i).apvlHrchTpCd_ES.setErrorInfo(1, NAMM0027E, new String[] {"Hierarchy Type" });
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlHrchTpCd_ES);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.E.no(i).apvlHistSrcTpCd_ES))) {
                    scrnMsg.E.no(i).apvlHistSrcTpCd_ES.setErrorInfo(1, NAMM0027E, new String[] {"Transactions" });
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlHistSrcTpCd_ES);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.E.no(i).apvlLimitAmt_E1))) {
                    scrnMsg.E.no(i).apvlLimitAmt_E1.setErrorInfo(1, NAMM0027E, new String[] {"$ Limit" });
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlLimitAmt_E1);
                }

            }
            // QC#18689-Sol#303
        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(scrnMsg.xxDplyTab.getValue())) {
            // Input Attribute Check Area
            for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
                if (!(ZYPCommonFunc.hasValue(scrnMsg.K.no(i).techApvlThrhdAmt_K1))) {
                    scrnMsg.K.no(i).techApvlThrhdAmt_K1.setErrorInfo(1, NAMM0027E, new String[] {"Threshold Amount" });
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).techApvlThrhdAmt_K1);
                }
            }

        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(scrnMsg.xxDplyTab.getValue())) {
            // Input Attribute Check Area
            for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
                if (!(ZYPCommonFunc.hasValue(scrnMsg.L.no(i).techApvlMinAmt_L1))) {
                    scrnMsg.L.no(i).techApvlMinAmt_L1.setErrorInfo(1, NAMM0027E, new String[] {"$ Premium Rush Minimum" });
                    scrnMsg.addCheckItem(scrnMsg.L.no(i).techApvlMinAmt_L1);
                }
            }

        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

        scrnMsg.putErrorScreen();
    }

    /**
     * Check Detail for each Lower Tab
     * @param scrnMsg NPAL1160BMsg
     */
    public static void addCheckItemForDetail(NPAL1160BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.apvlTeamNm_TT);
        scrnMsg.addCheckItem(scrnMsg.apvlTeamDescTxt_TT);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apvlTeamNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apvlTeamDescTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apvlHrchTpCd_AS);
        }

        scrnMsg.addCheckItem(scrnMsg.apvlTeamNm_MT);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm_MT);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).apvlTeamNm_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).apvlTeamPosnTpCd_BS);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).psnCd_B1);
        }

        scrnMsg.addCheckItem(scrnMsg.apvlTeamNm_ST);
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).apvlTeamNm_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).prchGrpCd_CS);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).mdseItemTpCd_CS);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).apvlHistSrcTpCd_CS);
        }

        scrnMsg.addCheckItem(scrnMsg.apvlTeamNm_LT);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_LT);
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).apvlTeamNm_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).rtlWhCd_D1);
        }

        // Input Attribute Check Area
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlHrchTpCd_ES);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlTeamPosnTpCd_ES);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).psnCd_E1);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).prchGrpCd_ES);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlHistSrcTpCd_ES);
            // START 2023/08/29 M.Kikushima [QC#61590, ADD]
            scrnMsg.addCheckItem(scrnMsg.E.no(i).prchReqTpCd_ES);
            // END 2023/08/29 M.Kikushima [QC#61590, ADD]
            scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlLimitPk_E1);
            // QC#QC#18689-Sol#303
            scrnMsg.addCheckItem(scrnMsg.E.no(i).apvlLimitAmt_E1);
        }

        // QC#18689-Sol#303
        // Technician Threshold
        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.K.no(i).techApvlThrhdAmt_K1);
        }

        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        // Technician Minimum
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.L.no(i).techApvlMinAmt_L1);
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]
    }

    /**
     * checkSelectedLineA
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkSelectedLineA(NPAL1160BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1160_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(abMsg.xxChkBox_A1.getValue())) {
                return;
            }
        }

        scrnMsg.setMessageInfo(NPAM1216E);
        throw new EZDFieldErrorException();
    }

    /**
     * checkSelectedLineB
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkSelectedLineB(NPAL1160BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NPAL1160_BBMsg abMsg = scrnMsg.B.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(abMsg.xxChkBox_B1.getValue())) {
                return;
            }
        }

        scrnMsg.setMessageInfo(NPAM1216E);
        throw new EZDFieldErrorException();
    }

    /**
     * checkSelectedLineC
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkSelectedLineC(NPAL1160BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NPAL1160_CBMsg abMsg = scrnMsg.C.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(abMsg.xxChkBox_C1.getValue())) {
                return;
            }
        }

        scrnMsg.setMessageInfo(NPAM1216E);
        throw new EZDFieldErrorException();
    }

    /**
     * checkSelectedLineD
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkSelectedLineD(NPAL1160BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NPAL1160_DBMsg abMsg = scrnMsg.D.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(abMsg.xxChkBox_D1.getValue())) {
                return;
            }
        }

        scrnMsg.setMessageInfo(NPAM1216E);
        throw new EZDFieldErrorException();
    }

    /**
     * checkSelectedLineE
     * @param scrnMsg NPAL1160BMsg
     */
    public static void checkSelectedLineE(NPAL1160BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            NPAL1160_EBMsg abMsg = scrnMsg.E.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(abMsg.xxChkBox_E1.getValue())) {
                return;
            }
        }

        scrnMsg.setMessageInfo(NPAM1216E);
        throw new EZDFieldErrorException();
    }

    /**
     * @param scrnMsg NPAL1160BMsg
     * @param tabName String
     */
    public static void clearTable(NPAL1160BMsg scrnMsg, String tabName) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        if (DISPLAY_TAB_NM_TEAM.equals(tabName)) {

            scrnMsg.B.clear();
            ZYPTableUtil.clear(scrnMsg.B);
            scrnMsg.C.clear();
            ZYPTableUtil.clear(scrnMsg.C);
            scrnMsg.D.clear();
            ZYPTableUtil.clear(scrnMsg.D);
            scrnMsg.E.clear();
            ZYPTableUtil.clear(scrnMsg.E);
            scrnMsg.K.clear();
            ZYPTableUtil.clear(scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            scrnMsg.L.clear();
            ZYPTableUtil.clear(scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]

            scrnMsg.apvlTeamNm_MT.clear();
            scrnMsg.apvlTeamNm_ST.clear();
            scrnMsg.apvlTeamNm_LT.clear();

            tblColor.clearRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);
            tblColor.clearRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);
            tblColor.clearRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);
            tblColor.clearRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);
            tblColor.clearRowsBG(DISPLAY_TECHTHRESHOLD_TABLE_NAME, scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            tblColor.clearRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]
        } else if (DISPLAY_TAB_NM_MEMBER.equals(tabName)) {

            scrnMsg.C.clear();
            ZYPTableUtil.clear(scrnMsg.C);
            scrnMsg.D.clear();
            ZYPTableUtil.clear(scrnMsg.D);
            scrnMsg.E.clear();
            ZYPTableUtil.clear(scrnMsg.E);
            scrnMsg.K.clear();
            ZYPTableUtil.clear(scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            scrnMsg.L.clear();
            ZYPTableUtil.clear(scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]

            scrnMsg.apvlTeamNm_TT.clear();
            scrnMsg.apvlTeamNm_ST.clear();
            scrnMsg.apvlTeamNm_LT.clear();

            tblColor.clearRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);
            tblColor.clearRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);
            tblColor.clearRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);
            tblColor.clearRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);
            tblColor.clearRowsBG(DISPLAY_TECHTHRESHOLD_TABLE_NAME, scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            tblColor.clearRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]
        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(tabName)) {

            scrnMsg.B.clear();
            ZYPTableUtil.clear(scrnMsg.B);
            scrnMsg.D.clear();
            ZYPTableUtil.clear(scrnMsg.D);
            scrnMsg.E.clear();
            ZYPTableUtil.clear(scrnMsg.E);
            scrnMsg.K.clear();
            ZYPTableUtil.clear(scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            scrnMsg.L.clear();
            ZYPTableUtil.clear(scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]

            scrnMsg.apvlTeamNm_TT.clear();
            scrnMsg.apvlTeamNm_MT.clear();
            scrnMsg.apvlTeamNm_LT.clear();

            tblColor.clearRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);
            tblColor.clearRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);
            tblColor.clearRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);
            tblColor.clearRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);
            tblColor.clearRowsBG(DISPLAY_TECHTHRESHOLD_TABLE_NAME, scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            tblColor.clearRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]
        } else if (DISPLAY_TAB_NM_LOCATION.equals(tabName)) {

            scrnMsg.B.clear();
            ZYPTableUtil.clear(scrnMsg.B);
            scrnMsg.C.clear();
            ZYPTableUtil.clear(scrnMsg.C);
            scrnMsg.E.clear();
            ZYPTableUtil.clear(scrnMsg.E);
            scrnMsg.K.clear();
            ZYPTableUtil.clear(scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            scrnMsg.L.clear();
            ZYPTableUtil.clear(scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]

            scrnMsg.apvlTeamNm_TT.clear();
            scrnMsg.apvlTeamNm_MT.clear();
            scrnMsg.apvlTeamNm_ST.clear();

            tblColor.clearRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);
            tblColor.clearRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);
            tblColor.clearRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);
            tblColor.clearRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);
            tblColor.clearRowsBG(DISPLAY_TECHTHRESHOLD_TABLE_NAME, scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            tblColor.clearRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]
        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(tabName)) {

            scrnMsg.B.clear();
            ZYPTableUtil.clear(scrnMsg.B);
            scrnMsg.C.clear();
            ZYPTableUtil.clear(scrnMsg.C);
            scrnMsg.D.clear();
            ZYPTableUtil.clear(scrnMsg.D);
            scrnMsg.K.clear();
            ZYPTableUtil.clear(scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            scrnMsg.L.clear();
            ZYPTableUtil.clear(scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]

            scrnMsg.apvlTeamNm_TT.clear();
            scrnMsg.apvlTeamNm_MT.clear();
            scrnMsg.apvlTeamNm_ST.clear();
            scrnMsg.apvlTeamNm_LT.clear();

            tblColor.clearRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);
            tblColor.clearRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);
            tblColor.clearRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);
            tblColor.clearRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);
            tblColor.clearRowsBG(DISPLAY_TECHTHRESHOLD_TABLE_NAME, scrnMsg.K);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            tblColor.clearRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]
        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(tabName)) {

            scrnMsg.B.clear();
            ZYPTableUtil.clear(scrnMsg.B);
            scrnMsg.C.clear();
            ZYPTableUtil.clear(scrnMsg.C);
            scrnMsg.D.clear();
            ZYPTableUtil.clear(scrnMsg.D);
            scrnMsg.E.clear();
            ZYPTableUtil.clear(scrnMsg.E);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            scrnMsg.L.clear();
            ZYPTableUtil.clear(scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]

            scrnMsg.apvlTeamNm_TT.clear();
            scrnMsg.apvlTeamNm_MT.clear();
            scrnMsg.apvlTeamNm_ST.clear();
            scrnMsg.apvlTeamNm_LT.clear();

            tblColor.clearRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);
            tblColor.clearRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);
            tblColor.clearRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);
            tblColor.clearRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);
            tblColor.clearRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);
            // START 2023/05/17 T.Kuroda [QC#61211, ADD]
            tblColor.clearRowsBG(DISPLAY_TECHMIN_TABLE_NAME, scrnMsg.L);
            // END   2023/05/17 T.Kuroda [QC#61211, ADD]
        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(tabName)) {

            scrnMsg.B.clear();
            ZYPTableUtil.clear(scrnMsg.B);
            scrnMsg.C.clear();
            ZYPTableUtil.clear(scrnMsg.C);
            scrnMsg.D.clear();
            ZYPTableUtil.clear(scrnMsg.D);
            scrnMsg.E.clear();
            ZYPTableUtil.clear(scrnMsg.E);
            scrnMsg.K.clear();
            ZYPTableUtil.clear(scrnMsg.K);

            scrnMsg.apvlTeamNm_TT.clear();
            scrnMsg.apvlTeamNm_MT.clear();
            scrnMsg.apvlTeamNm_ST.clear();
            scrnMsg.apvlTeamNm_LT.clear();

            tblColor.clearRowsBG(DISPLAY_TEAM_TABLE_NAME, scrnMsg.A);
            tblColor.clearRowsBG(DISPLAY_MEMBER_TABLE_NAME, scrnMsg.B);
            tblColor.clearRowsBG(DISPLAY_TRANSACTION_TABLE_NAME, scrnMsg.C);
            tblColor.clearRowsBG(DISPLAY_LOCATION_TABLE_NAME, scrnMsg.D);
            tblColor.clearRowsBG(DISPLAY_APVLLIMIT_TABLE_NAME, scrnMsg.E);
            tblColor.clearRowsBG(DISPLAY_TECHTHRESHOLD_TABLE_NAME, scrnMsg.K);
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]
    }
}
