package business.servlet.ZZML0010.common;

import business.blap.ZZML0010.constant.ZZML0010Constant;
import business.servlet.ZZML0010.ZZML0010BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0010CommonLogic {

    /** button */
    private static final String BTN_SEARCH = "Search";

    /** command button enum */
    private enum CMN_BTN { SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN };

    /** command button enum */
    private enum BTN_COND { NONE, ENABLE, DISABLE };

    /** command button name and event name definition */
    private static final String[][] CMN_BTN_DEF = {
            {"btn1", "CMN_Save", "Save"},
            {"btn2", "CMN_Submit", "Submit"},
            {"btn3", "CMN_Apply", "Apply"},
            {"btn4", "CMN_Approve", "Approve"},
            {"btn5", "CMN_Reject", "Reject"},
            {"btn6", "CMN_Download", "Download"},
            {"btn7", "CMN_Delete", "Delete"},
            {"btn8", "CMN_Clear", "Clear"},
            {"btn9", "CMN_Reset", "Reset"},
            {"btn10", "CMN_Return", "Return"},
    };

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesInit(S21CommonHandler handler) {
        setButtonProperties(handler, CMN_BTN.SAVE, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.SUBMIT, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.APPLY, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.APPROVE, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.REJECT, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.DOWNLOAD, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.DELETE, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.RESET, BTN_COND.ENABLE);
        setButtonProperties(handler, CMN_BTN.RETURN, BTN_COND.ENABLE);

        setButtonConfirmMsg(handler, CMN_BTN.RETURN, "ZZM8101I");
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesSearchFound(S21CommonHandler handler) {
        setButtonProperties(handler, CMN_BTN.SUBMIT, BTN_COND.ENABLE);
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.RESET, BTN_COND.ENABLE);
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesSearchNotFound(S21CommonHandler handler) {
        setButtonProperties(handler, CMN_BTN.SUBMIT, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.RESET, BTN_COND.ENABLE);
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesReset(S21CommonHandler handler) {
        setButtonPropertiesInit(handler);
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesClear(S21CommonHandler handler) {
        setButtonPropertiesInit(handler);
    }

    private static void setButtonProperties(S21CommonHandler handler, CMN_BTN cmdBtn, BTN_COND condition) {
        int i = cmdBtn.ordinal();
        if (condition == BTN_COND.NONE) {
            handler.setButtonProperties(CMN_BTN_DEF[i][0], "", "", 0, null);
        } else {
            int cond = 0;
            if (condition == BTN_COND.ENABLE) { cond = 1; }
            handler.setButtonProperties(CMN_BTN_DEF[i][0], CMN_BTN_DEF[i][1], CMN_BTN_DEF[i][2], cond, null);
        }
    }

    private static void setButtonConfirmMsg(S21CommonHandler handler, CMN_BTN cmdBtn, String messageId) {
        int i = cmdBtn.ordinal();
        handler.setButtonConfirmMsg(CMN_BTN_DEF[i][1], messageId, new String[] {CMN_BTN_DEF[i][2]}, 0);
    }

    /**
     * @param scrnMsg EZDBMsg
     * @param protect true:protect
     */
    public static void setFormControleProtectCondition(ZZML0010BMsg scrnMsg, boolean protect) {
        scrnMsg.mlSmtpHostNm.setInputProtected(protect);
        scrnMsg.mlSmtpPortNum.setInputProtected(protect);
        scrnMsg.mlSmtpUsrNm.setInputProtected(protect);
        scrnMsg.mlSmtpPassTxt.setInputProtected(protect);

        scrnMsg.mlDefFromNm.setInputProtected(protect);
        scrnMsg.mlDefRpyToNm_01.setInputProtected(protect);
        scrnMsg.mlDefRpyToNm_02.setInputProtected(protect);
        scrnMsg.mlDefRpyToNm_03.setInputProtected(protect);
        scrnMsg.mlDefRpyToNm_04.setInputProtected(protect);
        scrnMsg.mlDefRpyToNm_05.setInputProtected(protect);

        scrnMsg.mlCharSetNm.setInputProtected(protect);
        scrnMsg.mlRtryNum.setInputProtected(protect);

        // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        // scrnMsg.langCd.setInputProtected(protect);
        // scrnMsg.langCd_L1.setInputProtected(protect);
        scrnMsg.mlLocId.setInputProtected(protect);
        scrnMsg.mlLocId_L1.setInputProtected(protect);
        // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        scrnMsg.langNm_L1.setInputProtected(protect);

        scrnMsg.mlSendMaxNum.setInputProtected(protect);
        scrnMsg.mlDefDtFmtTxt.setInputProtected(protect);
        scrnMsg.mlDefNumFmtTxt.setInputProtected(protect);

        scrnMsg.mlSysStopFlg.setInputProtected(protect);
    }

    /**
     * @param scrnMsg EZDBMsg
     */
    public static void clearFormControle(ZZML0010BMsg scrnMsg) {
        scrnMsg.mlSmtpHostNm.clear();
        scrnMsg.mlSmtpPortNum.clear();
        scrnMsg.mlSmtpUsrNm.clear();
        scrnMsg.mlSmtpPassTxt.clear();

        scrnMsg.mlDefFromNm.clear();
        scrnMsg.mlDefRpyToNm_01.clear();
        scrnMsg.mlDefRpyToNm_02.clear();
        scrnMsg.mlDefRpyToNm_03.clear();
        scrnMsg.mlDefRpyToNm_04.clear();
        scrnMsg.mlDefRpyToNm_05.clear();

        scrnMsg.mlCharSetNm.clear();
        scrnMsg.mlRtryNum.clear();

        //scrnMsg.langCd.clear();
        // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        // scrnMsg.langCd.clear();
        // scrnMsg.langCd_L1.clear();
        scrnMsg.mlLocId.clear();
        scrnMsg.mlLocId_L1.clear();
        // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)

        scrnMsg.mlSendMaxNum.clear();
        scrnMsg.mlDefDtFmtTxt.clear();
        scrnMsg.mlDefNumFmtTxt.clear();

        scrnMsg.mlSysStopFlg.clear();

        scrnMsg.ezUpTime.clear();
        scrnMsg.ezUpTimeZone.clear();
    }
    
    public static void setLanguageList(ZZML0010BMsg bMsg) {
        int j = 0;
        for (ZZML0010Constant.Language langCd : ZZML0010Constant.Language.values()) {
            // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            // bMsg.langCd_L1.no(j).setValue(langCd.toString());
            bMsg.mlLocId_L1.no(j).setValue(langCd.toString());
            // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            bMsg.langNm_L1.no(j).setValue(langCd.getLangName());
            j++;
        }
    }
}
