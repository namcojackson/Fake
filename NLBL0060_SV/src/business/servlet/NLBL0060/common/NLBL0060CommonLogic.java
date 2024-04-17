/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060.common;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NLBL0060.NLBL0060BMsg;
import business.servlet.NLBL0060.constant.NLBL0060Constant;

/**
 * <pre>
 * It is common processing in the screen application program of BusinessID NLBL0060. 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/02   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/21   CUSA            Mizutani         Update          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0060CommonLogic implements NLBL0060Constant {

    /**
     * The initial valuel of the screen item.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0060BMsg
     */
    public static void setInitValue(NLBL0060BMsg scrnMsg) {

        String salesDate = ZYPDateUtil.getSalesDate();
        scrnMsg.effFromDt_H1.setValue(salesDate);
        scrnMsg.effThruDt_H1.setValue(salesDate);
    }

    /**
     * The display control of the screen item.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0060BMsg
     */
    public static void cntrlDispScrnItem(EZDCommonHandler handler, NLBL0060BMsg scrnMsg) {
        EZDDebugOutput.println(1, "NLBL0060CommonLogic.cntrlDispScrnItem================================START", handler);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.locNm_H2.setInputProtected(true);
        cntrlTblDisp(scrnMsg);
        cntrlTblBackgroundColor(scrnMsg);

        boolean referenceFunctionExistFlg = false;
        boolean updateFunctionExistFlg = false;

        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {

            if (FUNCTION_REFERENCE.equals((String) functionList.get(i))) {
                referenceFunctionExistFlg = true;
            }

            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {
                updateFunctionExistFlg = true;
            }
        }

        if (!referenceFunctionExistFlg && !updateFunctionExistFlg) {

            throw new S21AbendException("The user doesn't have functionID for NLBL0060.");
        }

        if (updateFunctionExistFlg) {

            cntrlDispScrnItemForUpdateFunction(handler, scrnMsg);

        } else {

            cntrlDispScrnItemForReferenceFunction(handler, scrnMsg);
        }

        EZDDebugOutput.println(1, "NLBL0060CommonLogic.cntrlDispScrnItem================================END", handler);
    }

    /**
     * The method explanation: The display control in the table is
     * executed.
     * @param scrnMsg NLBL0060BMsg
     */
    private static void cntrlTblDisp(NLBL0060BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() == 0) {

            return;
        }

        int prevRecNewRowNum = 0;
        String prevRecWhCd = "";
        String prevRecEffFromDt = "";
        String prevRecShpgModeCd = "";

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (prevRecNewRowNum == scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt()) {

                if (prevRecWhCd.equals(scrnMsg.A.no(i).whCd_A9.getValue())) {

                    // 2013/05/21 R-OM025 Inventory Transaction Add Start
                    EZDGUIAttribute whCd = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_CD + Integer.toString(i));
                    whCd.setVisibility(false);
                    scrnMsg.addGUIAttribute(whCd);
                    EZDGUIAttribute whNm = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_NM + Integer.toString(i));
                    whNm.setVisibility(false);
                    scrnMsg.addGUIAttribute(whNm);
                    EZDGUIAttribute whLink = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_LINK + Integer.toString(i));
                    whLink.setVisibility(false);
                    scrnMsg.addGUIAttribute(whLink);
                    // 2013/05/21 R-OM025 Inventory Transaction Add End

                    if (prevRecEffFromDt.equals(scrnMsg.A.no(i).effFromDt_A9.getValue())) {

                        EZDGUIAttribute radio = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_RADIO + Integer.toString(i));
                        EZDGUIAttribute effPer = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_EFF_PERIOD + Integer.toString(i));
                        radio.setVisibility(false);
                        effPer.setVisibility(false);
                        scrnMsg.addGUIAttribute(radio);
                        scrnMsg.addGUIAttribute(effPer);

                        if (prevRecShpgModeCd.equals(scrnMsg.A.no(i).shpgModeCd_A9.getValue())) {

                            EZDGUIAttribute shpgMode = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_SHPG_MODE_NM + Integer.toString(i));
                            shpgMode.setVisibility(false);
                            scrnMsg.addGUIAttribute(shpgMode);

                        } else {

                            prevRecShpgModeCd = scrnMsg.A.no(i).shpgModeCd_A9.getValue();
                        }

                    } else {

                        prevRecEffFromDt = scrnMsg.A.no(i).effFromDt_A9.getValue();
                        prevRecShpgModeCd = scrnMsg.A.no(i).shpgModeCd_A9.getValue();
                    }

                } else {

                    prevRecWhCd = scrnMsg.A.no(i).whCd_A9.getValue();
                    prevRecEffFromDt = scrnMsg.A.no(i).effFromDt_A9.getValue();
                    prevRecShpgModeCd = scrnMsg.A.no(i).shpgModeCd_A9.getValue();


                    // 2013/05/21 R-OM025 Inventory Transaction Add Start
                    scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                    // 2013/05/21 R-OM025 Inventory Transaction Add End

                    // 2013/05/21 R-OM025 Inventory Transaction Modify Start
                    // if (!WH_LIST_ALL_VALUE.equals(scrnMsg.whCd_S1.getValue()) && i != 0) {
                        if (ZYPCommonFunc.hasValue(scrnMsg.whCd_S1.getValue()) && i != 0) {
                       // 2013/05/21 R-OM025 Inventory Transaction Modify end

                        // 2013/05/21 R-OM025 Inventory Transaction
                        // Add Start
                        EZDGUIAttribute whCd = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_CD + Integer.toString(i));
                        whCd.setVisibility(false);
                        scrnMsg.addGUIAttribute(whCd);
                        EZDGUIAttribute whNm = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_NM + Integer.toString(i));
                        whNm.setVisibility(false);
                        scrnMsg.addGUIAttribute(whNm);
                        EZDGUIAttribute whLink = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_LINK + Integer.toString(i));
                        whLink.setVisibility(false);
                        scrnMsg.addGUIAttribute(whLink);
                        // 2013/05/21 R-OM025 Inventory Transaction
                        // Add End
                    }
                }

            } else {

                prevRecNewRowNum = scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt();
                prevRecWhCd = scrnMsg.A.no(i).whCd_A9.getValue();
                prevRecEffFromDt = scrnMsg.A.no(i).effFromDt_A9.getValue();
                prevRecShpgModeCd = scrnMsg.A.no(i).shpgModeCd_A9.getValue();


                // 2013/05/21 R-OM025 Inventory Transaction Add Start
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // 2013/05/21 R-OM025 Inventory Transaction Add End
                // if (!WH_LIST_ALL_VALUE.equals(scrnMsg.whCd_S1.getValue()) && i != 0) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.whCd_S1.getValue()) && i != 0) {
                     // 2013/05/21 R-OM025 Inventory Transaction
                        // Add Start
                        EZDGUIAttribute whCd = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_CD + Integer.toString(i));
                        whCd.setVisibility(false);
                        scrnMsg.addGUIAttribute(whCd);
                        EZDGUIAttribute whNm = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_NM + Integer.toString(i));
                        whNm.setVisibility(false);
                        scrnMsg.addGUIAttribute(whNm);
                        EZDGUIAttribute whLink = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_WH_LINK + Integer.toString(i));
                        whLink.setVisibility(false);
                        scrnMsg.addGUIAttribute(whLink);
                        // 2013/05/21 R-OM025 Inventory Transaction
                        // Add End
                }
            }
        }
    }

    /**
     * The method explanation: The background color control in the
     * table is executed.
     * @param scrnMsg NLBL0060BMsg
     */
    private static void cntrlTblBackgroundColor(NLBL0060BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() == 0) {

            return;
        }

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        int newRowNumCurrent = 0;
        String whCdCurrent = "";
        String effFromDtCurrent = "";

        boolean evenNumRecFlg = true;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (newRowNumCurrent == scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt()) {

                if (whCdCurrent.equals(scrnMsg.A.no(i).whCd_A9.getValue())) {

                    if (effFromDtCurrent.equals(scrnMsg.A.no(i).effFromDt_A9.getValue())) {

                        // There is no processing.

                    } else {

                        evenNumRecFlg = !evenNumRecFlg;
                        effFromDtCurrent = scrnMsg.A.no(i).effFromDt_A9.getValue();
                    }

                } else {

                    evenNumRecFlg = !evenNumRecFlg;
                    whCdCurrent = scrnMsg.A.no(i).whCd_A9.getValue();
                    effFromDtCurrent = scrnMsg.A.no(i).effFromDt_A9.getValue();
                }

            } else {

                evenNumRecFlg = !evenNumRecFlg;
                newRowNumCurrent = scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt();
                whCdCurrent = scrnMsg.A.no(i).whCd_A9.getValue();
                effFromDtCurrent = scrnMsg.A.no(i).effFromDt_A9.getValue();
            }

            if (evenNumRecFlg) {

                tblColor.setRowStyle(HTML_ID_VALUE_TABLE, i, HTML_STYLE_NAME_FOR_EVEN_NUM_BG);
            }
        }
    }

    /**
     * The display control of the screen item when the user has
     * Function[Update].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0060BMsg
     */
    private static void cntrlDispScrnItemForUpdateFunction(EZDCommonHandler handler, NLBL0060BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() == 0) {

            handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, false);
            handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, false);

            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);

        } else {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // 2013/05/21 R-OM025 Inventory Transaction Modify Start
                scrnMsg.A.no(i).whCd_A1.setInputProtected(false);
                // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            }

            if (scrnMsg.A.getValidCount() == 0) {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, false);

            } else {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, true);
            }

            if (scrnMsg.xxPageShowOfNum_A1.getValue().compareTo(new BigDecimal(MAX_NUM_OF_TABLE)) == -1) {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, true);

            } else {

                handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, false);
            }

            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        }

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * The display control of the screen item when the user has only
     * Function[Reference].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0060BMsg
     */
    private static void cntrlDispScrnItemForReferenceFunction(EZDCommonHandler handler, NLBL0060BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() == 0) {

            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);

        } else {

            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                EZDGUIAttribute radio = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_RADIO + Integer.toString(i));
                radio.setVisibility(false);
                scrnMsg.addGUIAttribute(radio);
                // 2013/05/21 R-OM025 Inventory Transaction Modify Start
                scrnMsg.A.no(i).whCd_A1.setInputProtected(true);
                // 2013/05/21 R-OM025 Inventory Transaction Modify Start
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).shpgCloTmTs_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDplyLeadTmDaysAot_A1.setInputProtected(true);
            }
        }

        EZDGUIAttribute btnDeleteRow = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_BTN_DELETE_ROW);
        EZDGUIAttribute btnInsertRow = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_BTN_INSERT_ROW);
        btnDeleteRow.setVisibility(false);
        btnInsertRow.setVisibility(false);
        scrnMsg.addGUIAttribute(btnDeleteRow);
        scrnMsg.addGUIAttribute(btnInsertRow);

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    public static void checkSingleUnitAttribution(NLBL0060BMsg scrnMsg) {

        String whCdCurrentKey = "";
        String effFromCurrentKey = "";
        String effThruCurrentKey = "";
        int newRowNumCurrentKey = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).shpgCloTmTs_A1)) {

                scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgCloTmTs_A1);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxDplyLeadTmDaysAot_A1)) {

                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDplyLeadTmDaysAot_A1);
            }

            if (whCdCurrentKey.equals(scrnMsg.A.no(i).whCd_A9.getValue()) && effFromCurrentKey.equals(scrnMsg.A.no(i).effFromDt_A9.getValue()) && effThruCurrentKey.equals(scrnMsg.A.no(i).effThruDt_A9.getValue())
                    && newRowNumCurrentKey == scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt()) {

                continue;

            } else {

                whCdCurrentKey = scrnMsg.A.no(i).whCd_A9.getValue();
                effFromCurrentKey = scrnMsg.A.no(i).effFromDt_A9.getValue();
                effThruCurrentKey = scrnMsg.A.no(i).effThruDt_A9.getValue();
                newRowNumCurrentKey = scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt();

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)) {

                    scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)) {

                    scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
                }
            }
        }
    }

    public static void checkSingleUnitAttributionForSubmit(NLBL0060BMsg scrnMsg) {

        String whCdCurrentKey = "";
        String effFromCurrentKey = "";
        String effThruCurrentKey = "";
        int newRowNumCurrentKey = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (whCdCurrentKey.equals(scrnMsg.A.no(i).whCd_A9.getValue()) && effFromCurrentKey.equals(scrnMsg.A.no(i).effFromDt_A9.getValue()) && effThruCurrentKey.equals(scrnMsg.A.no(i).effThruDt_A9.getValue())
                    && newRowNumCurrentKey == scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt()) {

                // There is no processing.

            } else {

                whCdCurrentKey = scrnMsg.A.no(i).whCd_A9.getValue();
                effFromCurrentKey = scrnMsg.A.no(i).effFromDt_A9.getValue();
                effThruCurrentKey = scrnMsg.A.no(i).effThruDt_A9.getValue();
                newRowNumCurrentKey = scrnMsg.A.no(i).xxNewRowNum_A9.getValueInt();

                // 2013/05/21 R-OM025 Inventory Transaction Modify Start
                scrnMsg.addCheckItem(scrnMsg.A.no(i).whCd_A1);
                // 2013/05/21 R-OM025 Inventory Transaction Modify Start
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgCloTmTs_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDplyLeadTmDaysAot_A1);
        }
    }

    /**
     * When the Search button is pressed, it is confirmed whether the
     * display of the confirming message is necessary.
     * @param scrnMsg NLBL0060BMsg
     * @return boolean
     */
    public static boolean checkSearchButtonConfirmMsgNecessary(NLBL0060BMsg scrnMsg) {

        boolean updateFunctionExistFlg = false;

        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {

            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {

                updateFunctionExistFlg = true;
            }
        }

        if (updateFunctionExistFlg && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxConfMsgAlrdyDplyFlg_G1.getValue()) && scrnMsg.A.getValidCount() > 0) {

            return true;

        } else {

            return false;
        }
    }

    /**
     * The focus control after the event that processes to table.
     * @param scrnMsg NLBL0060BMsg
     * @return boolean
     */
    public static void setFocusForTbl(NLBL0060BMsg scrnMsg) {

        boolean updateFunctionExistFlg = false;

        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {

            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {

                updateFunctionExistFlg = true;
            }
        }

        if (updateFunctionExistFlg) {

            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A1);

            } else {

                scrnMsg.setFocusItem(scrnMsg.whCd_H2);
            }

        } else {

            scrnMsg.setFocusItem(scrnMsg.whCd_H2);
        }
    }
}
