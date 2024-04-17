/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230.common;

import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_APPLY;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_APPROVE;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_BLANK6;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_BLANK7;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_CLEAR;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_REJECT;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_RESET;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_SAVE;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_NM_ADD;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_NM_DELETE;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_NM_SELECT_ALL;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_NM_SELECT_SEGMENT;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BTN_NM_UNSELECT_ALL;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.BUSINESS_ID;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.FUNCTION_ID_NSAL1230T020;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.MODE_UPDATE;
import static business.servlet.NSAL1230.constant.NSAL1230Constant.SCREEN_ID;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1230.NSAL1230BMsg;
import business.servlet.NSAL1230.constant.NSAL1230Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/10   Hitachi         Y.Takeno        Create          N/A
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 *</pre>
 */
public class NSAL1230CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1230BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1230BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenButton(handler, scrnMsg);
        controlInputFields(handler, scrnMsg);

        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    private static final void initCommonButton(EZDCommonHandler handler, NSAL1230BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        List<String> funcCodeList = getFunctionCodeList();
        if (funcCodeList.contains(FUNCTION_ID_NSAL1230T020) && MODE_UPDATE.equals(scrnMsg.xxModeCd.getValue())) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
            handler.setButtonEnabled(BTN_CMN_RESET[0], true);

        } else {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            handler.setButtonEnabled(BTN_CMN_RESET[0], false);

        }
    }

    private static final void controlScreenButton(EZDCommonHandler handler, NSAL1230BMsg scrnMsg) {
        List<String> funcCodeList = getFunctionCodeList();

        if (funcCodeList.contains(FUNCTION_ID_NSAL1230T020) && MODE_UPDATE.equals(scrnMsg.xxModeCd.getValue())) {
            handler.setButtonEnabled(BTN_NM_SELECT_ALL, true);
            handler.setButtonEnabled(BTN_NM_UNSELECT_ALL, true);
            handler.setButtonEnabled(BTN_NM_ADD, true);
            handler.setButtonEnabled(BTN_NM_DELETE, true);

            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                handler.setButtonEnabled(BTN_NM_SELECT_SEGMENT, true);
            }

        } else {
            handler.setButtonEnabled(BTN_NM_SELECT_ALL, false);
            handler.setButtonEnabled(BTN_NM_UNSELECT_ALL, false);
            handler.setButtonEnabled(BTN_NM_ADD, false);
            handler.setButtonEnabled(BTN_NM_DELETE, false);

            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                handler.setButtonEnabled(BTN_NM_SELECT_SEGMENT, false);
            }
        }
    }

    private static final void controlInputFields(EZDCommonHandler handler, NSAL1230BMsg scrnMsg) {
        List<String> funcCodeList = getFunctionCodeList();

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            if (funcCodeList.contains(FUNCTION_ID_NSAL1230T020) && MODE_UPDATE.equals(scrnMsg.xxModeCd.getValue())) {
                scrnMsg.A.no(index).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(index).coaAfflAcctNm_A1.setInputProtected(false);
                scrnMsg.A.no(index).prcAllocRate_A1.setInputProtected(false);
                // QC#23378(Sol#320) Mod Start
                if (ZYPCommonFunc.hasValue(scrnMsg.svcInvChrgTpCd) && SVC_INV_CHRG_TP.BASE_CHARGE.equals(scrnMsg.svcInvChrgTpCd.getValue())) {
                    scrnMsg.A.no(index).prcAllocAmt_A1.setInputProtected(false);
                } else {
                    scrnMsg.A.no(index).prcAllocAmt_A1.setInputProtected(true);
                    scrnMsg.A.no(index).prcAllocRate_A1.setIndispensable(true);
                }
                // QC#23378(Sol#320) Mod End
            } else {
                scrnMsg.A.no(index).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(index).coaAfflAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(index).prcAllocRate_A1.setInputProtected(true);
                scrnMsg.A.no(index).prcAllocAmt_A1.setInputProtected(true);    // QC#23378(Sol#320)
            }

            scrnMsg.A.no(index).prcAllocRate_A1.setAppFracDigit(2);
            scrnMsg.A.no(index).prcAllocAmt_A1.setAppFracDigit(2);    // QC#23378(Sol#320)
        }
        // QC#23378(Sol#320) Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.svcInvChrgTpCd) && SVC_INV_CHRG_TP.BASE_CHARGE.equals(scrnMsg.svcInvChrgTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.basePrcDealAmtTxt, NSAL1230Constant.LBL_BASE_CHARGE );
        } else {
            scrnMsg.basePrcDealAmtTxt.clear();
        }

        scrnMsg.basePrcDealAmt.setInputProtected(true);

        // QC#23378(Sol#320) Add End

    }

    private static final List<String> getFunctionCodeList() {
        S21UserProfileService userProfileService = getUserProfileService();
        return userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
    }

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }
}
