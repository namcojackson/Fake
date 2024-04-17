/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.servlet.NLCL0320.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0320.NLCL0320BMsg;
import business.servlet.NLCL0320.NLCL0320_ABMsg;
import business.servlet.NLCL0320.constant.NLCL0320Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 *</pre>
 */
public class NLCL0320CommonLogic implements NLCL0320Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * 
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NLCL0320BMsg scrnMsg, String userId) {

        initCommonButton(handler);
        setTblBackColor(scrnMsg);
        controlScreenDetailFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, userId, scrnMsg);
    }

    /**
     * <pre>
     * The screen item is set.
     * </pre>
     * 
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void setControlScreen(EZDCommonHandler handler, NLCL0320BMsg scrnMsg, String userId) {

        setTblBackColor(scrnMsg);
        controlScreenFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, userId, scrnMsg);
    }

    /**
     * Method name: initCommonButton
     * <dd>The method explanation: Initialize Common Button Control.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Buttons's activity set by user authority
     * @param handler
     * @param scrnMsg
     */
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NLCL0320BMsg scrnMsg) {

        if (!hasUpdateAuthority(userId)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
        } else {
            handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
        }
    }

    /**
     * Control screen fields
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NLCL0320BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }

    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NLCL0320BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        tblColor.setAlternateRowsBG(TABLE_ID_A, scrnMsg.A);

    }

    /**
     * Return true if userId have update authority.
     * @param userId
     * @return
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE })) {
            return true;
        }
        return false;
    }

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NLCL0320BMsg scrnMsg, String userId) {

        String slsDt = scrnMsg.slsDt.getValue();
        scrnMsg.rtlWhNm_H.setInputProtected(true);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.rtlWhCd_H.setInputProtected(true);
            scrnMsg.xxChkBox_H.setInputProtected(true);
            handler.setButtonEnabled(BTN_SEARCH[0], false);
        } else {
            scrnMsg.rtlWhCd_H.setInputProtected(false);
            scrnMsg.xxChkBox_H.setInputProtected(false);
            handler.setButtonEnabled(BTN_SEARCH[0], true);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NLCL0320_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                detail.xxScrItem130Txt_A.setInputProtected(false);
                if (hasValue(detail.adjAcctAliasNm_A) && hasValue(detail.ezUpTime_A)) {
                    detail.rtlWhCd_A.setInputProtected(true);
                    detail.adjAcctAliasNm_A.setInputProtected(true);
                    detail.effFromDt_A.setInputProtected(true);
                    if (detail.effThruDt_A0.getValue().compareTo(slsDt) < 0) {
                        detail.effThruDt_A.setInputProtected(true);
                        detail.adjAcctAliasDescTxt_A.setInputProtected(true);
                        handler.setButtonEnabled(BTN_ACCOUNT[0], i, false);
                    } else {
                        detail.effThruDt_A.setInputProtected(false);
                        detail.adjAcctAliasDescTxt_A.setInputProtected(false);
                        handler.setButtonEnabled(BTN_ACCOUNT[0], i, true);
                    }
                    detail.xxChkBox_A.setInputProtected(true);

                } else { // new
                    detail.rtlWhCd_A.setInputProtected(false);
                    detail.adjAcctAliasNm_A.setInputProtected(false);
                    detail.adjAcctAliasDescTxt_A.setInputProtected(false);
                    detail.effFromDt_A.setInputProtected(false);
                    detail.effThruDt_A.setInputProtected(false);
                    detail.xxChkBox_A.setInputProtected(false);
                    handler.setButtonEnabled(BTN_ACCOUNT[0], i, true);
                }
            } else {
                detail.xxScrItem130Txt_A.setInputProtected(true);
                detail.rtlWhCd_A.setInputProtected(true);
                detail.adjAcctAliasNm_A.setInputProtected(true);
                detail.adjAcctAliasDescTxt_A.setInputProtected(true);
                detail.effFromDt_A.setInputProtected(true);
                detail.effThruDt_A.setInputProtected(true);
                detail.xxChkBox_A.setInputProtected(true);
                handler.setButtonEnabled(BTN_ACCOUNT[0], i, false);
            }
        }
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NLCL0320BMsg
     * @param idx Integer
     */
    public static Object[] setParamForAccountPopup(NLCL0320BMsg scrnMsg, int idx) {

        ZYPTableUtil.clear(scrnMsg.X);

        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(0).xxPopPrm, "NLCL0320");
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(1).xxPopPrm, scrnMsg.A.no(idx).coaCmpyCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(2).xxPopPrm, scrnMsg.A.no(idx).coaAfflCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(3).xxPopPrm, scrnMsg.A.no(idx).coaBrCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(4).xxPopPrm, scrnMsg.A.no(idx).coaCcCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(5).xxPopPrm, scrnMsg.A.no(idx).coaAcctCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(6).xxPopPrm, scrnMsg.A.no(idx).coaProdCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(7).xxPopPrm, scrnMsg.A.no(idx).coaChCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(8).xxPopPrm, scrnMsg.A.no(idx).coaProjCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(9).xxPopPrm, scrnMsg.A.no(idx).coaExtnCd_A);

        Object[] params = new Object[10];
        params[0] = scrnMsg.X.no(0).xxPopPrm;
        params[1] = scrnMsg.X.no(1).xxPopPrm;
        params[2] = scrnMsg.X.no(2).xxPopPrm;
        params[3] = scrnMsg.X.no(3).xxPopPrm;
        params[4] = scrnMsg.X.no(4).xxPopPrm;
        params[5] = scrnMsg.X.no(5).xxPopPrm;
        params[6] = scrnMsg.X.no(6).xxPopPrm;
        params[7] = scrnMsg.X.no(7).xxPopPrm;
        params[8] = scrnMsg.X.no(8).xxPopPrm;
        params[9] = scrnMsg.X.no(9).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call Pop up.
     * @param scrnMsg NLCL0320BMsg
     * @param idx Integer
     */
    public static void setAccountFromParameter(NLCL0320BMsg scrnMsg) {
        int idx = scrnMsg.xxNum.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaCmpyCd_A ,scrnMsg.X.no(1).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaAfflCd_A ,scrnMsg.X.no(2).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaBrCd_A ,scrnMsg.X.no(3).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaCcCd_A ,scrnMsg.X.no(4).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaAcctCd_A ,scrnMsg.X.no(5).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaProdCd_A ,scrnMsg.X.no(6).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaChCd_A ,scrnMsg.X.no(7).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaProjCd_A ,scrnMsg.X.no(8).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaExtnCd_A ,scrnMsg.X.no(9).xxPopPrm);
        StringBuilder acctNum = new StringBuilder();
        // Company
        acctNum.append(scrnMsg.X.no(1).xxPopPrm.getValue());
        acctNum.append(".");
        // Branch
        acctNum.append(scrnMsg.X.no(3).xxPopPrm.getValue());
        acctNum.append(".");
        // Cost Center
        acctNum.append(scrnMsg.X.no(4).xxPopPrm.getValue());
        acctNum.append(".");
        // Account
        acctNum.append(scrnMsg.X.no(5).xxPopPrm.getValue());
        acctNum.append(".");
        // Product
        acctNum.append(scrnMsg.X.no(6).xxPopPrm.getValue());
        acctNum.append(".");
        // Channel
        acctNum.append(scrnMsg.X.no(7).xxPopPrm.getValue());
        acctNum.append(".");
        // Affiliate
        acctNum.append(scrnMsg.X.no(2).xxPopPrm.getValue());
        acctNum.append(".");
        // Merchandise
        acctNum.append(scrnMsg.X.no(8).xxPopPrm.getValue());
        acctNum.append(".");
        // Business Unit
        acctNum.append(scrnMsg.X.no(9).xxPopPrm.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxScrItem130Txt_A, acctNum.toString());
    }
}
