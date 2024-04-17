/**
 * 
 */
package business.servlet.NSAL1170.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.servlet.NSAL1170.constant.NSAL1170Constant.*;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.common.util.S21Text;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1170.NSAL1170BMsg;
import business.servlet.NSAL1170.NSAL1170Bean;
import business.servlet.NSAL1170.NSAL1170_ABMsg;
import business.servlet.NSAL1170.NSAL1170_INIT;

/**
 *<pre>
 * Model Escalation Rules Maintenance Popup.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27    Hitachi         T.Nishimura    Create          N/A
 * 2016/02/04    Hitachi         T.Nishimura     Update          QC#4121
 * 2018/05/30    CITS            M.Naito         Update          QC#22887
 *</pre>
 */
public class NSAL1170CommonLogic {

    /**
     * initial ControlScreen.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL1170BMsg
     */
    public static void initialControlScreen(EZDCommonHandler scrnAppli, NSAL1170BMsg scrnMsg) {
        boolean updateMode = hasUpdateFuncId();
        initCommonButton(scrnAppli, updateMode);
        controlScreenHeaderFields(scrnMsg);
        controlScreenDetailFields(scrnMsg, updateMode);
        controlScreenDetailButton(scrnAppli, scrnMsg, updateMode);
    }

    /**
     * initial ControlScreen.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL1170BMsg
     */
    public static void initialControlScreendisable(NSAL1170_INIT scrnAppli, NSAL1170BMsg scrnMsg) {
        hasUpdateFuncId();
        initCommonButton(scrnAppli, false);
        controlScreenHeaderFields(scrnMsg);
        controlScreenDetailFields(scrnMsg, false);
        controlScreenDetailButton(scrnAppli, scrnMsg, false);
    }

    private static void initCommonButton(EZDCommonHandler scrnAppli, boolean updateMode) {
        int modeInt = 0;
        if (updateMode) {
            modeInt = 1;
        }
        scrnAppli.setButtonProperties(BTN_CMN_SAVE_BTN_NM, null, BTN_CMN_SAVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_SUBMIT_BTN_NM, BTN_CMN_SUBMIT_EVENT_NM, BTN_CMN_SUBMIT_LABEL, modeInt, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPLY_BTN_NM, null, BTN_CMN_APPLY_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPROVE_BTN_NM, null, BTN_CMN_APPROVE_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_REJECT_BTN_NM, null, BTN_CMN_REJECT_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD_BTN_NM, null, BTN_CMN_DOWNLOAD_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DELETE_BTN_NM, null, BTN_CMN_DELETE_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR_BTN_NM, BTN_CMN_CLEAR_EVENT_NM, BTN_CMN_CLEAR_LABEL, modeInt, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET_BTN_NM, null, BTN_CMN_RESET_LABEL, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_RETURN_BTN_NM, BTN_CMN_RETURN_EVENT_NM, BTN_CMN_RETURN_LABEL, 1, null);
    }

    private static void controlScreenHeaderFields(NSAL1170BMsg scrnMsg) {
        scrnMsg.t_MdlNm.setInputProtected(true);

    }

    private static void controlScreenDetailFields(NSAL1170BMsg scrnMsg, boolean updateMode) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1170_ABMsg aBMsg = scrnMsg.A.no(i);
            // START 2018/05/30 M.Naito [QC#22887, MOD]
//            aBMsg.uplftBasePrcUpRatio.setAppFracDigit(0);
//            aBMsg.uplftMtrPrcUpRatio.setAppFracDigit(0);
            aBMsg.uplftBasePrcUpRatio.setAppFracDigit(2);
            aBMsg.uplftMtrPrcUpRatio.setAppFracDigit(2);
            // END 2018/05/30 M.Naito [QC#22887, MOD]
            if (updateMode) {
                if (!hasValue(aBMsg.mtrLbDescTxt) || S21Text.isNullOrEmpty(aBMsg.mtrLbDescTxt.getValue())) {
                    aBMsg.uplftMtrPrcUpRatio.setInputProtected(true);
                }
            } else {
                aBMsg.uplftBasePrcUpRatio.setInputProtected(true);
                aBMsg.uplftMtrPrcUpRatio.setInputProtected(true);
                aBMsg.effFromDt.setInputProtected(true);
                aBMsg.effThruDt.setInputProtected(true);

            }
        }
    }

    private static void controlScreenDetailButton(EZDCommonHandler scrnAppli, NSAL1170BMsg scrnMsg, boolean updateMode) {
        scrnAppli.setButtonEnabled("DeleteRow", false);
        scrnMsg.xxRadioBtn.setInputProtected(!updateMode);
        if (updateMode) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (scrnMsg.A.no(i).xxRowNum.getValueInt() > 1) {
                    scrnAppli.setButtonEnabled("DeleteRow", true);
                    break;
                }
            }
        } else {
            scrnAppli.setButtonEnabled("InsertRow", false);
        }

    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Actual Counters for Interface (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1170BMsg
     */
    public static void commonInputCheck(NSAL1170BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (BigDecimal.ONE.equals(scrnMsg.A.no(i).xxRowNum_D.getValue())) {
                if (!hasValue(scrnMsg.A.no(i).uplftBasePrcUpRatio)) {
                    scrnMsg.A.no(i).uplftBasePrcUpRatio.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).uplftBasePrcUpRatio.getNameForMessage() });
                }
                if (!hasValue(scrnMsg.A.no(i).effFromDt)) {
                    scrnMsg.A.no(i).effFromDt.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).effFromDt.getNameForMessage() });
                }
            }
            if (hasValue(scrnMsg.A.no(i).mtrLbDescTxt) && S21Text.isNullOrEmpty(scrnMsg.A.no(i).mtrLbDescTxt.getValue()) && !hasValue(scrnMsg.A.no(i).uplftMtrPrcUpRatio)) {
                scrnMsg.A.no(i).uplftMtrPrcUpRatio.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).uplftMtrPrcUpRatio.getNameForMessage() });
            }
        }

    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1170BMsg
     */
    public static void commonAddCheckItem(NSAL1170BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NSAL1170Bean.uplftBasePrcUpRatio, NSAL1170Bean.effFromDt, NSAL1170Bean.effThruDt, NSAL1170Bean.uplftMtrPrcUpRatio }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }
}
