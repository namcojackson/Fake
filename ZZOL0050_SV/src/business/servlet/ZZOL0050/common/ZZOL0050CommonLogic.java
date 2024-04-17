package business.servlet.ZZOL0050.common;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.servlet.ZZOL0050.ZZOL0050BMsg;
import business.servlet.ZZOL0050.constant.ZZOL0050Constant;

public class ZZOL0050CommonLogic implements ZZOL0050Constant {

    /**
     * 画面の「一覧表：A」の行の背景色を、行単位に反転させます。
     * 
     * @param scrnMsg   ZZOL0050BMsg
     */
    public static void setTableColor( ZZOL0050BMsg scrnMsg ) {
        
        S21TableColorController tblColor = new S21TableColorController( screenId, scrnMsg );
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
        
        setButtonConfirmMsg(handler, CMN_BTN7, true);
    }

    /**
     * set chip message
     * 
     * @param scrnMsg   ZZOL0050BMsg
     */
    public static void setNameForMessage( ZZOL0050BMsg scrnMsg ) {

        scrnMsg.glblCmpyCd.setNameForMessage("Global Company CD");
        scrnMsg.upldCsvId.setNameForMessage("Upload CSV ID");
        scrnMsg.upldCsvNm.setNameForMessage("Upload CSV Name");
    }
}
