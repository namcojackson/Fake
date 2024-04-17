package business.servlet.ZZML0071.common;

import business.servlet.ZZML0071.ZZML0071BMsg;
import business.servlet.ZZML0071.ZZML0071Bean;
import business.servlet.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0071Scrn00CommonLogic implements ZZML0071Constant  {

    /** button */
    public static final String BTN_ADD_USER = "Add User";
    /** button */
    public static final String BTN_ADD_GROUP = "Add Group";

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
        setButtonProperties(handler, CMN_BTN.SUBMIT, BTN_COND.ENABLE);
        setButtonProperties(handler, CMN_BTN.APPLY, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.APPROVE, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.REJECT, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.DOWNLOAD, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.DELETE, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.CLEAR, BTN_COND.DISABLE);
        setButtonProperties(handler, CMN_BTN.RESET, BTN_COND.ENABLE);
        setButtonProperties(handler, CMN_BTN.RETURN, BTN_COND.ENABLE);

        setButtonConfirmMsg(handler, CMN_BTN.SUBMIT, null);
        setButtonConfirmMsg(handler, CMN_BTN.RETURN, "ZZM8102I");
    }
    
    /**
     * @param handler S21CommonHandler
     */
    public static void setDisableSubmitButton(S21CommonHandler handler) {
        setButtonProperties(handler, CMN_BTN.SUBMIT, BTN_COND.DISABLE); 
    }

    /**
     * Set Table Focus Rule
     * @param scrnMsg
     */
    public static void setTabFocusRule(ZZML0071BMsg scrnMsg) {
        
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule( "ZZML0071Scrn00", ZZML0071Bean.A );
        scrnMsg.addGUIAttribute( tblFocusRule );

        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule( ZZML0071Bean.mlGrpId_A );
        fRule.setNextId( ZZML0071Bean.mlKeyFirstCd_A + "#0" );
        tblFocusRule.addRule( fRule );

        int i = 0;
        for( ; i < scrnMsg.A.getValidCount(); i++ ) {

            // mlGrpId_A
            fRule = new ZYPGUIFocusRule( ZZML0071Bean.mlGrpId_A + "#" + i );
            if( i > 0 ) {
                fRule.setPrevId( ZZML0071Bean.mlKeyThirdCd_A + "#" + (i-1) );
            }
            fRule.setNextId( BTN_MLGRPLKUP + "#" + i );
            tblFocusRule.addRule( fRule );
            
            //mail group button
            fRule = new ZYPGUIFocusRule( BTN_MLGRPLKUP + "#" + i );
            fRule.setPrevId( ZZML0071Bean.mlGrpId_A + "#" + i );
            fRule.setNextId( ZZML0071Bean.mlKeyFirstCd_A + "#" + i );
            tblFocusRule.addRule( fRule );

            // mlKeyFirstCd_A
            fRule = new ZYPGUIFocusRule( ZZML0071Bean.mlKeyFirstCd_A + "#" + i );
            fRule.setPrevId( BTN_MLGRPLKUP + "#" + i );
            fRule.setNextId( ZZML0071Bean.mlKeyScdCd_A + "#" + i );
            tblFocusRule.addRule( fRule );

            // mlKeyScdCd_A
            fRule = new ZYPGUIFocusRule( ZZML0071Bean.mlKeyScdCd_A + "#" + i );
            fRule.setPrevId( ZZML0071Bean.mlKeyFirstCd_A + "#" + i );
            fRule.setNextId( ZZML0071Bean.mlKeyThirdCd_A + "#" + i );
            tblFocusRule.addRule( fRule );

            // mlKeyThirdCd_A
            fRule = new ZYPGUIFocusRule( ZZML0071Bean.mlKeyThirdCd_A + "#" + i );
            fRule.setPrevId( ZZML0071Bean.mlKeyScdCd_A + "#" + i );
            if( i < scrnMsg.A.getValidCount() - 1 ) {
                fRule.setNextId( ZZML0071Bean.mlGrpId_A + "#" + (i+1) );
            } else {
                fRule.setNextId( CMN_BTN_DEF[1][1] );//CMN_Submit
            }
            tblFocusRule.addRule( fRule );            
        }
        
        fRule = new ZYPGUIFocusRule( CMN_BTN_DEF[1][1] );
        fRule.setPrevId( ZZML0071Bean.mlKeyThirdCd_A + "#" + (i - 1) );
        tblFocusRule.addRule( fRule );

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
