/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050.common;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3050.NLBL3050BMsg;
import business.servlet.NLBL3050.NLBL3050Bean;
import business.servlet.NLBL3050.NLBL3050_ABMsg;
import business.servlet.NLBL3050.NLBL3050_BBMsg;
import business.servlet.NLBL3050.NLBL3050_CBMsg;
import business.servlet.NLBL3050.NLBL3050_DBMsg;
import business.servlet.NLBL3050.NLBL3050_FBMsg;
import business.servlet.NLBL3050.constant.NLBL3050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/19/2015   Fujitsu         W.Honda         Create          N/A
 * 01/21/2016   CSAI            Y.Imazu         Update          QC#2048
 * 10/26/2016   CSAI            Y.Imazu         Update          QC#9760
 *</pre>
 */
public class NLBL3050CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3050BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NLBL3050BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(NLBL3050Constant.SCREEN_ID);

        controlScreenFields(scrnMsg);
        initCommonButton(handler);
        initButton(handler, scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param scrnMsg NLBL3050BMsg
     */
    public static void controlScreenFields(NLBL3050BMsg scrnMsg) {

        // Header
        if (ZYPCommonFunc.hasValue(scrnMsg.asgTaskFlg)) {

            scrnMsg.cpoNum_H.setInputProtected(false);
            scrnMsg.xxRtlWhSrchTxt_H.setInputProtected(false);
            scrnMsg.xxLinkAncr_WH.setInputProtected(false);

            if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.asgTaskFlg.getValue())){

                scrnMsg.xxLinkAncr_CP.setInputProtected(true);
                scrnMsg.psnCd_H.setInputProtected(true);

            } else {

                scrnMsg.xxLinkAncr_CP.setInputProtected(false);
                scrnMsg.psnCd_H.setInputProtected(false);
            }

        } else {

            scrnMsg.cpoNum_H.setInputProtected(true);
            scrnMsg.xxRtlWhSrchTxt_H.setInputProtected(true);
            scrnMsg.xxLinkAncr_WH.setInputProtected(true);
            scrnMsg.xxLinkAncr_CP.setInputProtected(true);
            scrnMsg.psnCd_H.setInputProtected(true);
        }

        scrnMsg.xxPsnFirstMidLastNm_H.setInputProtected(true);

        // Detail
        scrnMsg.xxTotCnt_A.setInputProtected(true);
        scrnMsg.xxTotCnt_OA.setInputProtected(true);
        scrnMsg.xxTotCnt_IA.setInputProtected(true);
        scrnMsg.xxTotCnt_B.setInputProtected(true);
        scrnMsg.xxTotCnt_OB.setInputProtected(true);
        scrnMsg.xxTotCnt_IB.setInputProtected(true);

        // Table
        controlScreenFieldsA(scrnMsg);
        controlScreenFieldsB(scrnMsg);
        controlScreenFieldsC(scrnMsg);
        controlScreenFieldsD(scrnMsg);
        controlScreenFieldsF(scrnMsg);
    }

    /**
     * Method name: searchTodayButton <dd>The method explanation: Search_Today
     * @param handler EZ Common Handler
     * @param scrnMsg NLBL3050BMsg
     */
    public static void searchTodayButton(EZDCommonHandler handler, NLBL3050BMsg scrnMsg) {

        if (scrnMsg.L.getValidCount() > 0) {

            handler.setButtonProperties(NLBL3050Constant.BTN_ALL_TASK[0], NLBL3050Constant.BTN_ALL_TASK[1], NLBL3050Constant.BTN_ALL_TASK[2], 1, null);

        } else {

            handler.setButtonProperties(NLBL3050Constant.BTN_ALL_TASK[0], NLBL3050Constant.BTN_ALL_TASK[1], NLBL3050Constant.BTN_ALL_TASK[2], 0, null);
        }

        handler.setButtonProperties(NLBL3050Constant.BTN_TODAY_TASK[0], NLBL3050Constant.BTN_TODAY_TASK[1], NLBL3050Constant.BTN_TODAY_TASK[2], 0, null);
    }

    /**
     * Method name: checkInputSearch
     * @param scrnMsg NLBL3050BMsg
     */
    public static void checkInputSearch(NLBL3050BMsg scrnMsg) {

        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    /**
     * Method name: AddCheckItemHeader
     * @param scrnMsg NLBL3050BMsg
     */
    public static void addCheckItemHeader(NLBL3050BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.cpoNum_H);
        scrnMsg.addCheckItem(scrnMsg.xxRtlWhSrchTxt_H);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H);
    }

    /**
     * Method name: tblColumnSort
     * @param scrnMsg NLBL3050BMsg
     * @param msgArray EZDBMsgArray
     * @param baseContents String[][]
     * @param param Parameters
     * @param ctx EZDApplicationContext
     */
    public static void tblColumnSort(NLBL3050BMsg scrnMsg, EZDBMsgArray msgArray, String[][] baseContents, Parameters param, EZDApplicationContext ctx) {

        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        S21SortTarget sortTarget = new S21SortTarget(msgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrderBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, msgArray.getValidCount());

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, baseContents);
    }

    /**
     * Method name: controlScreenFieldsA
     * @param scrnMsg NLBL3050BMsg
     */
    private static void controlScreenFieldsA(NLBL3050BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRuleA = new ZYPGUITableFocusRule(NLBL3050Constant.SCREEN_ID, NLBL3050Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRuleA);

        addRuleNext(tblFocusRuleA, NLBL3050Bean.trxHdrNum_A1, NLBL3050Bean.xxOrdTs_A1);
        addRulePrev(tblFocusRuleA, NLBL3050Bean.trxHdrNum_A1, NLBL3050Bean.xxOrdTs_A1);

        if (0 < scrnMsg.A.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLBL3050Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_A_LEFT, scrnMsg.A);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_A_RIGHT, scrnMsg.A);

            addRuleNext(tblFocusRuleA, NLBL3050Bean.xxTotCnt_AI, NLBL3050Bean.trxHdrNum_A1 + 0);
            addRulePrev(tblFocusRuleA, NLBL3050Bean.xxTotCnt_AI, NLBL3050Bean.trxHdrNum_A1 + 0);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                NLBL3050_ABMsg scrnMsgALine = scrnMsg.A.no(i);

                scrnMsgALine.shipToAcctNm_A1.setInputProtected(true);
                scrnMsgALine.xxTotCnt_AI.setInputProtected(true);
                scrnMsgALine.xxTotCnt_AO.setInputProtected(true);
                scrnMsgALine.xxOrdTs_A1.setInputProtected(true);
                scrnMsgALine.delyCoordStsDescTxt_A1.setInputProtected(true);
                scrnMsgALine.shipToAddr_A1.setInputProtected(true);
                scrnMsgALine.shipToCtyAddr_A1.setInputProtected(true);
                scrnMsgALine.shipToStCd_A1.setInputProtected(true);

                if ((scrnMsg.A.getValidCount() - 1) > i) {

                    addRuleNext(tblFocusRuleA, NLBL3050Bean.trxHdrNum_A1 + i, NLBL3050Bean.trxHdrNum_A1 + (i + 1));
                    addRulePrev(tblFocusRuleA, NLBL3050Bean.trxHdrNum_A1 + i, NLBL3050Bean.trxHdrNum_A1 + (i + 1));

                } else {

                    addRuleNext(tblFocusRuleA, NLBL3050Bean.trxHdrNum_A1 + i, NLBL3050Bean.xxTotCnt_OB);
                    addRulePrev(tblFocusRuleA, NLBL3050Bean.trxHdrNum_A1 + i, NLBL3050Bean.xxTotCnt_OB);
                }
            }

        } else {

            addRuleNext(tblFocusRuleA, NLBL3050Bean.xxTotCnt_AI, NLBL3050Bean.xxTotCnt_OB);
            addRulePrev(tblFocusRuleA, NLBL3050Bean.xxTotCnt_AI, NLBL3050Bean.xxTotCnt_OB);
        }
    }

    /**
     * Method name: controlScreenFieldsB
     * @param scrnMsg NLBL3050BMsg
     */
    private static void controlScreenFieldsB(NLBL3050BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRuleB = new ZYPGUITableFocusRule(NLBL3050Constant.SCREEN_ID, NLBL3050Bean.B);
        scrnMsg.addGUIAttribute(tblFocusRuleB);

        addRuleNext(tblFocusRuleB, NLBL3050Bean.trxHdrNum_B1, NLBL3050Bean.xxOrdTs_B1);
        addRulePrev(tblFocusRuleB, NLBL3050Bean.trxHdrNum_B1, NLBL3050Bean.xxOrdTs_B1);

        if (0 < scrnMsg.B.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLBL3050Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_B_LEFT, scrnMsg.B);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_B_RIGHT, scrnMsg.B);

            addRuleNext(tblFocusRuleB, NLBL3050Bean.xxTotCnt_BI, NLBL3050Bean.trxHdrNum_B1 + 0);
            addRulePrev(tblFocusRuleB, NLBL3050Bean.xxTotCnt_BI, NLBL3050Bean.trxHdrNum_B1 + 0);

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                NLBL3050_BBMsg scrnMsgBLine = scrnMsg.B.no(i);

                scrnMsgBLine.shipToAcctNm_B1.setInputProtected(true);
                scrnMsgBLine.delyCoordStsDescTxt_B1.setInputProtected(true);
                scrnMsgBLine.xxTotCnt_BI.setInputProtected(true);
                scrnMsgBLine.xxTotCnt_BO.setInputProtected(true);
                scrnMsgBLine.xxOrdTs_B1.setInputProtected(true);
                scrnMsgBLine.shipToAddr_B1.setInputProtected(true);
                scrnMsgBLine.shipToCtyAddr_B1.setInputProtected(true);
                scrnMsgBLine.shipToStCd_B1.setInputProtected(true);

                if ((scrnMsg.B.getValidCount() - 1) > i) {

                    addRuleNext(tblFocusRuleB, NLBL3050Bean.trxHdrNum_B1 + i, NLBL3050Bean.trxHdrNum_B1 + (i + 1));
                    addRulePrev(tblFocusRuleB, NLBL3050Bean.trxHdrNum_B1 + i, NLBL3050Bean.trxHdrNum_B1 + (i + 1));

                } else {

                    addRuleNext(tblFocusRuleB, NLBL3050Bean.trxHdrNum_B1 + i, NLBL3050Bean.trxHdrNum_C1);
                    addRulePrev(tblFocusRuleB, NLBL3050Bean.trxHdrNum_B1 + i, NLBL3050Bean.trxHdrNum_C1);
                }

                // QC#15650
                // Switch text as anchor/label.
                // Controlling logic is moved to JSP file.
                /*
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsgBLine.xxAncrCtrlFlg_B1.getValue())) {
                    scrnMsgBLine.xxAncrCtrlFlg_B1.setInputProtected(true);
                    EZDGUIAttribute ancrAttr = new EZDGUIAttribute(NLBL3050Constant.SCREEN_ID, NLBL3050Bean.trxHdrNum_B1 + i);
                    ancrAttr.setStyleAttribute("color", "black");
                    ancrAttr.setStyleAttribute("text-decoration", "none");
                    ancrAttr.setStyleAttribute("cursor", "text");
                    scrnMsg.addGUIAttribute(ancrAttr);
                }
                */
            }

        } else {

            addRuleNext(tblFocusRuleB, NLBL3050Bean.xxTotCnt_BI, NLBL3050Bean.trxHdrNum_C1);
            addRulePrev(tblFocusRuleB, NLBL3050Bean.xxTotCnt_BI, NLBL3050Bean.trxHdrNum_C1);
        }
    }

    /**
     * Method name: controlScreenFieldsC
     * @param scrnMsg NLBL3050BMsg
     */
    private static void controlScreenFieldsC(NLBL3050BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRuleC = new ZYPGUITableFocusRule(NLBL3050Constant.SCREEN_ID, NLBL3050Bean.C);
        scrnMsg.addGUIAttribute(tblFocusRuleC);

        addRuleNext(tblFocusRuleC, NLBL3050Bean.trxHdrNum_C1, NLBL3050Bean.shipToAcctNm_C1);
        addRulePrev(tblFocusRuleC, NLBL3050Bean.trxHdrNum_C1, NLBL3050Bean.shipToAcctNm_C1);

        if (0 < scrnMsg.C.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLBL3050Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_C_LEFT, scrnMsg.C);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_C_RIGHT, scrnMsg.C);

            addRuleNext(tblFocusRuleC, NLBL3050Bean.carrNm_C1, NLBL3050Bean.trxHdrNum_C1 + 0);
            addRulePrev(tblFocusRuleC, NLBL3050Bean.carrNm_C1, NLBL3050Bean.trxHdrNum_C1 + 0);

            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

                NLBL3050_CBMsg scrnMsgCLine = scrnMsg.C.no(i);

                scrnMsgCLine.shipToAcctNm_C1.setInputProtected(true);
                scrnMsgCLine.xxTotCnt_CO.setInputProtected(true);
                scrnMsgCLine.shipToCtyAddr_C1.setInputProtected(true);
                scrnMsgCLine.shipToStCd_C1.setInputProtected(true);
                scrnMsgCLine.mdlNm_C1.setInputProtected(true);
                scrnMsgCLine.xxDt10Dt_C1.setInputProtected(true);
                scrnMsgCLine.carrNm_C1.setInputProtected(true);

                if ((scrnMsg.C.getValidCount() - 1) > i) {

                    addRuleNext(tblFocusRuleC, NLBL3050Bean.trxHdrNum_C1 + i, NLBL3050Bean.trxHdrNum_C1 + (i + 1));
                    addRulePrev(tblFocusRuleC, NLBL3050Bean.trxHdrNum_C1 + i, NLBL3050Bean.trxHdrNum_C1 + (i + 1));

                } else {

                    addRuleNext(tblFocusRuleC, NLBL3050Bean.trxHdrNum_C1 + i, NLBL3050Bean.trxHdrNum_D1);
                    addRulePrev(tblFocusRuleC, NLBL3050Bean.trxHdrNum_C1 + i, NLBL3050Bean.trxHdrNum_D1);
                }
            }

        } else {

            addRuleNext(tblFocusRuleC, NLBL3050Bean.carrNm_C1, NLBL3050Bean.trxHdrNum_D1);
            addRulePrev(tblFocusRuleC, NLBL3050Bean.carrNm_C1, NLBL3050Bean.trxHdrNum_D1);
        }
    }

    /**
     * Method name: controlScreenFieldsD
     * @param scrnMsg NLBL3050BMsg
     */
    private static void controlScreenFieldsD(NLBL3050BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRuleD = new ZYPGUITableFocusRule(NLBL3050Constant.SCREEN_ID, NLBL3050Bean.D);
        scrnMsg.addGUIAttribute(tblFocusRuleD);

        addRuleNext(tblFocusRuleD, NLBL3050Bean.trxHdrNum_D1, NLBL3050Bean.shipToAcctNm_D1);
        addRulePrev(tblFocusRuleD, NLBL3050Bean.trxHdrNum_D1, NLBL3050Bean.shipToAcctNm_D1);

        if (0 < scrnMsg.D.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLBL3050Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_D_LEFT, scrnMsg.D);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_D_RIGHT, scrnMsg.D);

            addRuleNext(tblFocusRuleD, NLBL3050Bean.carrNm_D1, NLBL3050Bean.trxHdrNum_D1 + 0);
            addRulePrev(tblFocusRuleD, NLBL3050Bean.carrNm_D1, NLBL3050Bean.trxHdrNum_D1 + 0);

            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {

                NLBL3050_DBMsg scrnMsgDLine = scrnMsg.D.no(i);

                scrnMsgDLine.shipToAcctNm_D1.setInputProtected(true);
                scrnMsgDLine.xxTotCnt_DO.setInputProtected(true);
                scrnMsgDLine.shipToCtyAddr_D1.setInputProtected(true);
                scrnMsgDLine.shipToStCd_D1.setInputProtected(true);
                scrnMsgDLine.mdlNm_D1.setInputProtected(true);
                scrnMsgDLine.xxDt10Dt_D1.setInputProtected(true);
                scrnMsgDLine.carrNm_D1.setInputProtected(true);

                if ((scrnMsg.D.getValidCount() - 1) > i) {

                    addRuleNext(tblFocusRuleD, NLBL3050Bean.trxHdrNum_D1 + i, NLBL3050Bean.trxHdrNum_D1 + (i + 1));
                    addRulePrev(tblFocusRuleD, NLBL3050Bean.trxHdrNum_D1 + i, NLBL3050Bean.trxHdrNum_D1 + (i + 1));

                } else {

                    addRuleNext(tblFocusRuleD, NLBL3050Bean.trxHdrNum_D1 + i, NLBL3050Bean.trxHdrNum_F1);
                    addRulePrev(tblFocusRuleD, NLBL3050Bean.trxHdrNum_D1 + i, NLBL3050Bean.trxHdrNum_F1);
                }
            }

        } else {

            addRuleNext(tblFocusRuleD, NLBL3050Bean.carrNm_D1, NLBL3050Bean.trxHdrNum_F1);
            addRulePrev(tblFocusRuleD, NLBL3050Bean.carrNm_D1, NLBL3050Bean.trxHdrNum_F1);
        }
    }

    /**
     * Method name: controlScreenFieldsF
     * @param scrnMsg NLBL3050BMsg
     */
    private static void controlScreenFieldsF(NLBL3050BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRuleF = new ZYPGUITableFocusRule(NLBL3050Constant.SCREEN_ID, NLBL3050Bean.F);
        scrnMsg.addGUIAttribute(tblFocusRuleF);

        addRuleNext(tblFocusRuleF, NLBL3050Bean.trxHdrNum_F1, NLBL3050Bean.shipToAcctNm_F1);
        addRulePrev(tblFocusRuleF, NLBL3050Bean.trxHdrNum_F1, NLBL3050Bean.shipToAcctNm_F1);

        if (0 < scrnMsg.F.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLBL3050Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_F_LEFT, scrnMsg.F);
            tblColor.setAlternateRowsBG(NLBL3050Constant.TABLE_F_RIGHT, scrnMsg.F);

            addRuleNext(tblFocusRuleF, NLBL3050Bean.carrNm_F1, NLBL3050Bean.trxHdrNum_F1 + 0);
            addRulePrev(tblFocusRuleF, NLBL3050Bean.carrNm_F1, NLBL3050Bean.trxHdrNum_F1 + 0);

            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {

                NLBL3050_FBMsg scrnMsgFLine = scrnMsg.F.no(i);

                scrnMsgFLine.shipToAcctNm_F1.setInputProtected(true);
                scrnMsgFLine.xxTotCnt_FI.setInputProtected(true);
                scrnMsgFLine.shipToCtyAddr_F1.setInputProtected(true);
                scrnMsgFLine.shipToStCd_F1.setInputProtected(true);
                scrnMsgFLine.mdlNm_F1.setInputProtected(true);
                scrnMsgFLine.xxDt10Dt_F1.setInputProtected(true);
                scrnMsgFLine.carrNm_F1.setInputProtected(true);

                if ((scrnMsg.F.getValidCount() - 1) > i) {

                    addRuleNext(tblFocusRuleF, NLBL3050Bean.trxHdrNum_F1 + i, NLBL3050Bean.trxHdrNum_F1 + (i + 1));
                    addRulePrev(tblFocusRuleF, NLBL3050Bean.trxHdrNum_F1 + i, NLBL3050Bean.trxHdrNum_F1 + (i + 1));

                } else {

                    addRuleNext(tblFocusRuleF, NLBL3050Bean.trxHdrNum_F1 + i, NLBL3050Constant.BTN_CMN_CLEAR[0]);
                    addRulePrev(tblFocusRuleF, NLBL3050Bean.trxHdrNum_F1 + i, NLBL3050Constant.BTN_CMN_CLEAR[0]);
                }
            }

        } else {

            addRuleNext(tblFocusRuleF, NLBL3050Bean.carrNm_F1, NLBL3050Constant.BTN_CMN_CLEAR[0]);
            addRulePrev(tblFocusRuleF, NLBL3050Bean.carrNm_F1, NLBL3050Constant.BTN_CMN_CLEAR[0]);
        }
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     */
    private static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_SAVE[0], NLBL3050Constant.BTN_CMN_SAVE[1], NLBL3050Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_SUBMIT[0], NLBL3050Constant.BTN_CMN_SUBMIT[1], NLBL3050Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_APPLY[0], NLBL3050Constant.BTN_CMN_APPLY[1], NLBL3050Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_APPROVE[0], NLBL3050Constant.BTN_CMN_APPROVE[1], NLBL3050Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_REJECT[0], NLBL3050Constant.BTN_CMN_REJECT[1], NLBL3050Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_DOWNLOAD[0], NLBL3050Constant.BTN_CMN_DOWNLOAD[1], NLBL3050Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_DETELE[0], NLBL3050Constant.BTN_CMN_DETELE[1], NLBL3050Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_CLEAR[0], NLBL3050Constant.BTN_CMN_CLEAR[1], NLBL3050Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_RESET[0], NLBL3050Constant.BTN_CMN_RESET[1], NLBL3050Constant.BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(NLBL3050Constant.BTN_CMN_RETURN[0], NLBL3050Constant.BTN_CMN_RETURN[1], NLBL3050Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Method name: initButton <dd>The method explanation: init
     * @param handler EZ Common Handler
     * @param scrnMsg NLBL3050BMsg
     */
    private static void initButton(EZDCommonHandler handler, NLBL3050BMsg scrnMsg) {

        // Header
        if(!ZYPCommonFunc.hasValue(scrnMsg.asgTaskFlg)) {

            handler.setButtonProperties(NLBL3050Constant.BTN_SEARCH[0], NLBL3050Constant.BTN_SEARCH[1], NLBL3050Constant.BTN_SEARCH[2], 0,  null);

        } else {

            handler.setButtonProperties(NLBL3050Constant.BTN_SEARCH[0], NLBL3050Constant.BTN_SEARCH[1], NLBL3050Constant.BTN_SEARCH[2], 1,  null);
        }

        if(ZYPCommonFunc.hasValue(scrnMsg.asgTaskFlg) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.asgTaskFlg.getValue())){

            handler.setButtonProperties(NLBL3050Constant.BTN_SEARCH_CRDNTR_PSN[0], NLBL3050Constant.BTN_SEARCH_CRDNTR_PSN[1], NLBL3050Constant.BTN_SEARCH_CRDNTR_PSN[2], 1,  null);

        } else {

            handler.setButtonProperties(NLBL3050Constant.BTN_SEARCH_CRDNTR_PSN[0], NLBL3050Constant.BTN_SEARCH_CRDNTR_PSN[1], NLBL3050Constant.BTN_SEARCH_CRDNTR_PSN[2], 0,  null);
        }

        // Detail
        if (scrnMsg.T.getValidCount() > 0) {

            handler.setButtonProperties(NLBL3050Constant.BTN_TODAY_TASK[0], NLBL3050Constant.BTN_TODAY_TASK[1], NLBL3050Constant.BTN_TODAY_TASK[2], 1, null);

        } else {

            handler.setButtonProperties(NLBL3050Constant.BTN_TODAY_TASK[0], NLBL3050Constant.BTN_TODAY_TASK[1], NLBL3050Constant.BTN_TODAY_TASK[2], 0, null);
        }

        handler.setButtonProperties(NLBL3050Constant.BTN_ALL_TASK[0], NLBL3050Constant.BTN_ALL_TASK[1], NLBL3050Constant.BTN_ALL_TASK[2], 0, null);
    }

    /**
     * Method name: addRuleNext
     * @param tblFcsRule ZYPGUITableFocusRule
     * @param item String
     * @param nextItem String
     */
    private static void addRuleNext(ZYPGUITableFocusRule tblFcsRule, String item, String nextItem) {

        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(item);
        fRule.setNextId(nextItem);
        tblFcsRule.addRule(fRule);
    }

    /**
     * Method name: addRulePrev
     * @param tblFcsRule ZYPGUITableFocusRule
     * @param prevItem String
     * @param item String
     */
    private static void addRulePrev(ZYPGUITableFocusRule tblFcsRule, String prevItem, String item) {

        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(item);
        fRule.setPrevId(prevItem);
        tblFcsRule.addRule(fRule);
    }
}
