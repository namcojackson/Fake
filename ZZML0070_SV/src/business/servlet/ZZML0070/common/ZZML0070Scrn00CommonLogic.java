package business.servlet.ZZML0070.common;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0070Scrn00CommonLogic {

    /** button */
    private static final String BTN_SEARCH = "Search";
    /** button */
    private static final String BTN_ADD = "Add";

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
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.ENABLE);
        setButtonProperties(handler, CMN_BTN.RESET, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.RETURN, BTN_COND.ENABLE);
        setButtonConfirmMsg(handler, CMN_BTN.DELETE, "ZZM8102I");
        //setButtonConfirmMsg(handler, CMN_BTN.RETURN, "ZZM8101I");
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesReturn(S21CommonHandler handler, boolean deleteable) {
        setButtonPropertiesInit(handler);

        if (deleteable) {
            setButtonProperties(handler, CMN_BTN.DELETE, BTN_COND.ENABLE);
        } else {
            setButtonProperties(handler, CMN_BTN.DELETE, BTN_COND.DISABLE);
        }
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesSearchFound(S21CommonHandler handler) {
        setButtonProperties(handler, CMN_BTN.DELETE, BTN_COND.ENABLE);
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.ENABLE);
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesSearchNotFound(S21CommonHandler handler) {
        setButtonProperties(handler, CMN_BTN.DELETE, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.ENABLE);
    }

    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesReset(S21CommonHandler handler) {
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
}
