/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300.common;

import static business.blap.NWAL2300.constant.NWAL2300Constant.ZZM9000E;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.AUTHORITY_NWAL2300T020;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_APPLY;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_APPLY_SUMMARY;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_APL;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_APR;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_RST;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_CMN_SUB;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BTN_SEARCH;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.LINE_STS_OPEN;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.NWAM0186E;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.NWAM0634E;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.servlet.NWAL2300.NWAL2300BMsg;
import business.servlet.NWAL2300.NWAL2300_BBMsg;
import business.servlet.NWAL2300.NWAL2300_CBMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL2300CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         H.Ikeda         Create          N/A
 * 2016/06/13   Fujitsu         S.Takami        Update          S21_NA#9078
 * 2018/04/06   Fujitsu         T.Aoi           Update          S21_NA#22122
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 */
public class NWAL2300CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Submit Common Button properties.
     * @param handler Event Handler
     */
    public static void submitCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * initBtnProp
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2300BMsg
     */
    public static void initBtnProp(S21CommonHandler handler, NWAL2300BMsg scrnMsg) {
        // Apply
        handler.setButtonEnabled(BTN_APPLY, false);
        // Search
        handler.setButtonEnabled(BTN_SEARCH, true);
        // 2018/04/06 QC#22122 Add Start
        // Apply(Invoice Summary)
        handler.setButtonEnabled(BTN_APPLY_SUMMARY, false);
        // 2018/04/06 QC#22122 Add End
    }

    /**
     * btnProp
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2300BMsg
     */
    public static void btnProp(S21CommonHandler handler, NWAL2300BMsg scrnMsg) {
        // Apply
        handler.setButtonEnabled(BTN_APPLY, false);
        // Search
        handler.setButtonEnabled(BTN_SEARCH, true);
        // 2018/04/06 QC#22122 Add Start
        // Apply(Invoice Summary)
        handler.setButtonEnabled(BTN_APPLY_SUMMARY, false);
        // 2018/04/06 QC#22122 Add End
    }

    /**
     * Set Screen Protect
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2300BMsg
     * @param clearFlg boolean
     */
    public static boolean setProtect(S21CommonHandler handler, NWAL2300BMsg scrnMsg) {
        boolean submitProtectFlag = false;
        // All items should be inactive.
        scrnMsg.setInputProtected(true);
        handler.setButtonEnabled(BTN_SEARCH, false);
        // 2018/04/06 QC#22122 Add Start
        handler.setButtonEnabled(BTN_APPLY_SUMMARY, false);
        // 2018/04/06 QC#22122 Add End
        handler.setButtonEnabled(BTN_APPLY, false);
        boolean editable = isEditable(scrnMsg);

        // already processed
        // 2018/04/06 QC#22122 Add Start
        boolean partialCrRblCompFlg = true;
        boolean initFlg = true;
        boolean radioButtonInvFlg = false;
        List<String> invNumList = new ArrayList<String>();
        List<String> crCpoOrdNumList = new ArrayList<String>();

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cpoOrdNum_B1) || ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cpoOrdNum_B2)) {
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
                scrnMsg.B.no(i).xxTpCd_B1.setInputProtected(true);
            } else {
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
                scrnMsg.B.no(i).xxTpCd_B1.setInputProtected(false);
                invNumList.add(scrnMsg.B.no(i).invNum_B1.getValue());
                partialCrRblCompFlg = false;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cpoOrdNum_B1)) {
                scrnMsg.B.no(i).cpoOrdNum_L4.setValue(ZYPConstant.FLG_ON_Y);
                scrnMsg.B.no(i).cpoOrdNum_L4.setInputProtected(false);
                scrnMsg.B.no(i).cpoOrdNum_B1.setInputProtected(false);
                crCpoOrdNumList.add(scrnMsg.B.no(i).cpoOrdNum_B1.getValue());
            } else {
                scrnMsg.B.no(i).cpoOrdNum_L4.setValue(ZYPConstant.FLG_OFF_N);
                scrnMsg.B.no(i).cpoOrdNum_L4.setInputProtected(true);
                scrnMsg.B.no(i).cpoOrdNum_L4.clear();
                scrnMsg.B.no(i).cpoOrdNum_B1.setInputProtected(true);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cpoOrdNum_B2)) {
                scrnMsg.B.no(i).cpoOrdNum_L5.setValue(ZYPConstant.FLG_ON_Y);
                scrnMsg.B.no(i).cpoOrdNum_L5.setInputProtected(false);
                scrnMsg.B.no(i).cpoOrdNum_B2.setInputProtected(false);
            } else {
                scrnMsg.B.no(i).cpoOrdNum_L5.setValue(ZYPConstant.FLG_OFF_N);
                scrnMsg.B.no(i).cpoOrdNum_L5.setInputProtected(true);
                scrnMsg.B.no(i).cpoOrdNum_L5.clear();
                scrnMsg.B.no(i).cpoOrdNum_B2.setInputProtected(true);
            }
            if ((ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cpoOrdNum_B1) || ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cpoOrdNum_B2)) && initFlg) {
                initFlg = false;
            }
        }
        if (invNumList.size() > 0) {
            handler.setButtonEnabled(BTN_APPLY_SUMMARY, true);
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                for (String invNum : invNumList) {
                    if (!invNum.equals(scrnMsg.C.no(i).invNum_C1.getValue())) {
                        continue;
                    }
                    scrnMsg.C.no(i).xxTpCd_C1.setInputProtected(false);
                }
            }
        } else {
            submitProtectFlag = true;
        }

        for (String crCpoOrdNum : crCpoOrdNumList) {
            if (!crCpoOrdNum.equals(scrnMsg.cpoOrdNum_H2.getValue()) || (crCpoOrdNumList.size() > 0 && (crCpoOrdNumList.size() != scrnMsg.B.getValidCount()))) {
                radioButtonInvFlg = true;
                scrnMsg.xxRadioBtn.setValue(BigDecimal.valueOf(2));
            } else {
                scrnMsg.xxRadioBtn.setValue(BigDecimal.ONE);
            }
        }

        if (!partialCrRblCompFlg || radioButtonInvFlg) {
            scrnMsg.cpoOrdNum_H2.clear();
            scrnMsg.cpoOrdNum_H3.clear();
            scrnMsg.cpoOrdNum_L2.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.cpoOrdNum_L3.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.cpoOrdNum_L2.setInputProtected(true);
            scrnMsg.cpoOrdNum_L3.setInputProtected(true);
            scrnMsg.cpoOrdNum_L2.clear();
            scrnMsg.cpoOrdNum_L3.clear();
        } else {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).cpoOrdNum_L4.setValue(ZYPConstant.FLG_OFF_N);
                scrnMsg.B.no(i).cpoOrdNum_L5.setValue(ZYPConstant.FLG_OFF_N);
                scrnMsg.B.no(i).cpoOrdNum_L4.setInputProtected(true);
                scrnMsg.B.no(i).cpoOrdNum_L5.setInputProtected(true);
                scrnMsg.B.no(i).cpoOrdNum_L4.clear();
                scrnMsg.B.no(i).cpoOrdNum_L5.clear();
                scrnMsg.B.no(i).cpoOrdNum_B1.setInputProtected(true);
                scrnMsg.B.no(i).cpoOrdNum_B2.setInputProtected(true);
            }
        }
        // 2018/04/06 QC#22122 Add End

        // 2018/04/06 QC#22122 Mod Start
        //if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H2) || ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H3) {
        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H2) || ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H3) || !initFlg) {
        // 2018/04/06 QC#22122 Mod End
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H2)) {
                scrnMsg.cpoOrdNum_L2.setValue(ZYPConstant.FLG_ON_Y);
                scrnMsg.cpoOrdNum_L2.setInputProtected(false);
                scrnMsg.cpoOrdNum_H2.setInputProtected(false);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H3)) {
                scrnMsg.cpoOrdNum_L3.setValue(ZYPConstant.FLG_ON_Y);
                scrnMsg.cpoOrdNum_L3.setInputProtected(false);
                scrnMsg.cpoOrdNum_H3.setInputProtected(false);
            }
            // 2018/04/06 QC#22122 Add End
            return submitProtectFlag;

            // no data found
        } else if (0 == scrnMsg.C.getValidCount()) {
            scrnMsg.cpoOrdNum_L1.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.cpoOrdNum_L1.setInputProtected(false);
            scrnMsg.cpoOrdNum_H1.setInputProtected(false);
            // Search Button
            handler.setButtonEnabled(BTN_SEARCH, true);
            // 2018/04/06 QC#22122 Add Start
            scrnMsg.cpoOrdNum_L2.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.cpoOrdNum_L3.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.cpoOrdNum_L2.setInputProtected(true);
            scrnMsg.cpoOrdNum_L3.setInputProtected(true);
            scrnMsg.cpoOrdNum_L2.clear();
            scrnMsg.cpoOrdNum_L3.clear();
            scrnMsg.cpoOrdNum_H2.setInputProtected(true);
            scrnMsg.cpoOrdNum_H3.setInputProtected(true);
            // 2018/04/06 QC#22122 Add End
            return submitProtectFlag;

            // valid data found
        } else {
            if (!editable) {
                return submitProtectFlag;
            }

            // Order Num link, text box
            scrnMsg.cpoOrdNum_L1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.cpoOrdNum_L1.setInputProtected(true);
            scrnMsg.cpoOrdNum_L1.clear();
            scrnMsg.cpoOrdNum_H1.setInputProtected(true);

            // 2018/04/06 QC#22122 Add Start
            if (partialCrRblCompFlg) {
                return submitProtectFlag;
            }
            // 2018/04/06 QC#22122 Add End

            // Reason Code Check box
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            }
            // Comment
            scrnMsg.xxOrdMemoTxt_H1.setInputProtected(false);

            // 2018/04/06 QC#22122 Add Start
            // Radio button(Invoice Summary)
            scrnMsg.xxRadioBtn.setInputProtected(false);
            // 2018/04/06 QC#22122 Add End

            // Radio button
            scrnMsg.xxRadioBtn_H1.setInputProtected(false);
            // Apply Button
            handler.setButtonEnabled(BTN_APPLY, true);

            // Action / Cancel Check Box
            for (int j = 0; j < scrnMsg.C.getValidCount(); j++) {
                NWAL2300_CBMsg detail = scrnMsg.C.no(j);

                setAction(handler, scrnMsg, detail);
                setCheckBox(handler, scrnMsg, detail);
            }

            // 2018/04/06 QC#22122 Add Start
            // Invoice Summary
            setProtectInvoiceSummary(handler, scrnMsg);
            // 2018/04/06 QC#22122 Add End
        }
        return submitProtectFlag;
    }

    // 2018/04/06 QC#22122 Add Start
    /**
     * Set Screen Protect Invoice Summary
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2300BMsg
     */
    public static void setProtectInvoiceSummary(S21CommonHandler handler, NWAL2300BMsg scrnMsg) {

        if (BigDecimal.ONE.equals(scrnMsg.xxRadioBtn.getValue())) {
            handler.setButtonEnabled(BTN_APPLY_SUMMARY, false);
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).xxChkBox_B1.clear();
                scrnMsg.B.no(i).xxTpCd_B1.clear();
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
                scrnMsg.B.no(i).xxTpCd_B1.setInputProtected(true);
            }
        } else if (BigDecimal.valueOf(2).equals(scrnMsg.xxRadioBtn.getValue())) {
            handler.setButtonEnabled(BTN_APPLY_SUMMARY, true);
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
                scrnMsg.B.no(i).xxTpCd_B1.setInputProtected(false);
            }
        }
    }
    // 2018/04/06 QC#22122 Add End

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL2300BMsg
     */
    public static void setRowsBGWithClear(NWAL2300BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG("C", scrnMsg.C);

        // Color on
        tblColor.setAlternateRowsBG("C", scrnMsg.C, 1);
    }

    /**
     * submitErrorCheck
     * @param scrnMsg NWAL2300BMsg
     */
    public static void submitErrorCheck(NWAL2300BMsg scrnMsg) {
        boolean errFlg = true;
        // Reason Code
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                errFlg = false;
                break;
            }
        }
        if (errFlg) {
            scrnMsg.A.no(0).xxChkBox_A1.setErrorInfo(1, NWAM0634E, new String[] {"Reason Code" });
            scrnMsg.addCheckItem(scrnMsg.A.no(0).xxChkBox_A1);
        }

        scrnMsg.addCheckItem(scrnMsg.xxOrdMemoTxt_H1);

        // 2018/04/06 QC#22122 Add Start
        if (BigDecimal.valueOf(2).equals(scrnMsg.xxRadioBtn.getValue())) {
            errorCheckForPartialCreditRebill(scrnMsg);
        }
        // 2018/04/06 QC#22122 Add End
    }

    /**
     * setAuthorityFlg
     * @param scrnMsg NWAL2300BMsg
     * @return authorityFlg boolean
     */
    public static boolean isEditable(NWAL2300BMsg scrnMsg) {
        boolean authorityFlg = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrEdtTpCd) //
                && AUTHORITY_NWAL2300T020.equals(scrnMsg.xxScrEdtTpCd.getValue())) {
            authorityFlg = true;
        }

        return authorityFlg;
    }

    /**
     * setAction
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2300BMsg
     * @param editable boolean
     * @param i int
     */
    public static void setAction(S21CommonHandler handler, NWAL2300BMsg scrnMsg, NWAL2300_CBMsg detail) {
        // Inbound
        if (DS_ORD_LINE_DRCTN.INBOUND.equals(detail.dsOrdLineDrctnCd_C1.getValue())) {
            return;

            // Outbound Open
        } else if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(detail.dsOrdLineDrctnCd_C1.getValue()) //
                && LINE_STS_OPEN.equals(detail.xxDplyStsNm_C1.getValue())) {
            return;
        } else if (isSetCompnent(scrnMsg, detail)) {
            // Set Compornent
            return;
        } else {
            detail.xxTpCd_C1.setInputProtected(false);
        }
    }

    /**
     * setCheckBox
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2300BMsg
     * @param editable boolean
     * @param i int
     */
    public static void setCheckBox(S21CommonHandler handler, NWAL2300BMsg scrnMsg, NWAL2300_CBMsg detail) {
        // Inboud & Open
        if (DS_ORD_LINE_DRCTN.INBOUND.equals(detail.dsOrdLineDrctnCd_C1.getValue()) //
                && LINE_STS_OPEN.equals(detail.xxDplyStsNm_C1.getValue())) {
            if (!isSetCompnent(scrnMsg, detail)) {
                detail.xxChkBox_C1.setInputProtected(false);
            }
        }
    }

    /**
     * Set Control Fields For Digit
     * @param scrnMsg NWAL2300BMsg
     */
    public static void setControlFieldsForDigit(NWAL2300BMsg scrnMsg) {

        // Header
        scrnMsg.invTotDealNetAmt_H1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.grsPrftPct_H1.setAppFracDigit(2);
        scrnMsg.funcGrsPrftAmt_H1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.funcNegoDealAmt_H1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.totFuncFinalFlAmt_H1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.totFuncRepRevAmt_H1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());

        // Invoice Summary
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL2300_BBMsg invSumMsg = scrnMsg.B.no(i);
            invSumMsg.invTotDealNetAmt_B1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            invSumMsg.dealRmngBalGrsAmt_B1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }

        // Invoice Line
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL2300_CBMsg invLineMsg = scrnMsg.C.no(i);
            invLineMsg.dealNetUnitPrcAmt_C1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            invLineMsg.spTotDealNetAmt_C1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            invLineMsg.invAmt_C1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NWAL2300BMsg
     */
    public static void addCheckItem(NWAL2300BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H1);
        scrnMsg.addCheckItem(scrnMsg.xxOrdMemoTxt_H1);
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL2300_CBMsg invLineMsg = scrnMsg.C.no(i);
            scrnMsg.addCheckItem(invLineMsg.xxTpCd_C1);
            scrnMsg.addCheckItem(invLineMsg.xxChkBox_C1);
        }
        scrnMsg.putErrorScreen();
    }
    
    /**
     * addCheckItem
     * @param scrnMsg NWAL2300BMsg
     */
    public static boolean isSetCompnent(NWAL2300BMsg scrnMsg, NWAL2300_CBMsg detail) {
        if ("000".equals(detail.cpoDtlLineSubNum_C1.getValue())) {
            return false;
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL2300_CBMsg detailInLoop = scrnMsg.C.no(i);
            if (!detail.dsOrdLineDrctnCd_C1.getValue().equals(detailInLoop.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }
            if (detail.cpoDtlLineNum_C1.getValue().equals(detailInLoop.cpoDtlLineNum_C1.getValue()) && detail.cpoDtlLineSubNum_C1.getValue().equals(detailInLoop.cpoDtlLineSubNum_C1.getValue())) {
                // It's mine.
                continue;
            }
            if (detail.cpoDtlLineNum_C1.getValue().equals(detailInLoop.cpoDtlLineNum_C1.getValue()) && "000".equals(detailInLoop.cpoDtlLineSubNum_C1.getValue())) {
                return true;
            }
        }
        return false;
    }

    // 2018/04/06 QC#22122 Add Start
    /**
     * Error Check For Invoice
     * @param scrnMsg NWAL2300BMsg
     */
    public static void errorCheckForPartialCreditRebill(NWAL2300BMsg scrnMsg) {
        boolean chkFlg = false;
        int rowNum = -1;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cpoOrdNum_B1)) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_B1.getValue())) {
                chkFlg = true;

                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).xxTpCd_B1)) {
                    scrnMsg.B.no(i).xxTpCd_B1.setErrorInfo(1, ZZM9000E, new String[] {"Action" });
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).xxTpCd_B1);
                }
            } else {
                if (rowNum < 0) {
                    rowNum = i;
                }
            }
        }

        if (!chkFlg) {
            scrnMsg.B.no(rowNum).xxChkBox_B1.setErrorInfo(1, NWAM0186E);
            scrnMsg.addCheckItem(scrnMsg.B.no(rowNum).xxChkBox_B1);
        }
    }
    // 2018/04/06 QC#22122 Add End
}
