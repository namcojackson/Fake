package business.servlet.ZZOL0052.common;

import business.servlet.ZZOL0052.ZZOL0052BMsg;
import business.servlet.ZZOL0052.constant.ZZOL0052Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0052CommonLogic implements ZZOL0052Constant {

    /**
     * Of the screen "a table"： I reverse the background color of the line of A to a line unit.
     * 
     * @param scrnMsg   ZZOL0052BMsg
     */
    public static void setTableColor( ZZOL0052BMsg scrnMsg ) {
        
        S21TableColorController tblColor = new S21TableColorController( screenId, scrnMsg );
        tblColor.setAlternateRowsBG( "A_TBL",  scrnMsg.A );
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
        handler.setButtonProperties( CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null );
        handler.setButtonProperties( CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null );
        handler.setButtonProperties( CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null );
        handler.setButtonProperties( CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null );
        
    }

}
