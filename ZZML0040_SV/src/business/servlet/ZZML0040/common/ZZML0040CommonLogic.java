package business.servlet.ZZML0040.common;

import business.servlet.ZZML0040.ZZML0040BMsg;
import business.servlet.ZZML0040.constant.ZZML0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZML0040CommonLogic implements ZZML0040Constant {
    
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
     * 画面の「一覧表：A」の行の背景色を、行単位に反転させます。
     * 
     * @param scrnMsg   ZZML0040BMsg
     */
    public static void setTableColor( ZZML0040BMsg scrnMsg ) {
        
        S21TableColorController tblColor = new S21TableColorController( screenId_00, scrnMsg );
        tblColor.setAlternateRowsBG( "A_LeftTBL",  scrnMsg.A );
        tblColor.setAlternateRowsBG( "A_RightTBL", scrnMsg.A );
    }

    private static void setButtonConfirmMsg( S21CommonHandler handler, String[] btnDef, boolean msgFlg ) {

        if ( msgFlg ) {
            handler.setButtonConfirmMsg(btnDef[1], "ZZM8102I", new String[] { btnDef[2] }, 0);
        } else {
            handler.setButtonConfirmMsg(btnDef[1], null, null, 0);
        }
    }

    /**
     * set chip message
     * 
     * @param scrnMsg   ZZML0040BMsg
     */
    public static void setNameForMessage( ZZML0040BMsg scrnMsg ) {

        // ZZML0040Scrn00
        scrnMsg.glblCmpyCd.setNameForMessage(    "Global Company CD");
        scrnMsg.mlTmplId.setNameForMessage(      "Mail Template ID");
        scrnMsg.mlSubjTmplTxt.setNameForMessage( "Subject");
        
        // ZZML0040Scrn01
        scrnMsg.glblCmpyCd_01.setNameForMessage(    "Global Company CD");
        scrnMsg.mlTmplId_01.setNameForMessage(      "Mail Template ID");
        // START 2013/08/15 M.Sumida Mod from language only to locale(lang + country)
        // scrnMsg.mlLocId_01.setNameForMessage(       "Language");
        scrnMsg.mlLocId_01.setNameForMessage(       "Locale");
        // END   2013/08/15 M.Sumida Mod from language only to locale(lang + country)
        scrnMsg.mlSubjTmplTxt_01.setNameForMessage( "Subject");
        scrnMsg.xxMlBodyTxt_01.setNameForMessage(   "Body");
        
    }

    /**
     * set screen00 button status
     * @param handler
     */
    public static void setButtonScrn00( S21CommonHandler handler ) {

        handler.setButtonProperties( CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null );
        handler.setButtonProperties( CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null );
        handler.setButtonProperties( CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null );
        handler.setButtonProperties( CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null );
        handler.setButtonProperties( CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null );
        handler.setButtonProperties( CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null );
        handler.setButtonProperties( CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null );
        handler.setButtonProperties( CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null );
        handler.setButtonProperties( CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null );
        handler.setButtonProperties( CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null );
        
        setButtonConfirmMsg(handler, CMN_BTN7, true);
        setButtonConfirmMsg(handler, CMN_BTN10, false);
    }

    /**
     * set screen01 button status
     * @param handler
     */
    public static void setButtonScrn01( S21CommonHandler handler ) {

        handler.setButtonProperties( CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null );
        handler.setButtonProperties( CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null );
        handler.setButtonProperties( CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null );
        handler.setButtonProperties( CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null );
        handler.setButtonProperties( CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null );
        handler.setButtonProperties( CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null );
        handler.setButtonProperties( CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null );
        handler.setButtonProperties( CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null );
        handler.setButtonProperties( CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null );
        handler.setButtonProperties( CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null );

        setButtonConfirmMsg(handler, CMN_BTN10, true);
    }
    
    /**
     * @param handler S21CommonHandler
     */
    public static void setButtonPropertiesSearchNotFound(S21CommonHandler handler) {
        setButtonProperties(handler, CMN_BTN.DELETE, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.ENABLE);
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
}
