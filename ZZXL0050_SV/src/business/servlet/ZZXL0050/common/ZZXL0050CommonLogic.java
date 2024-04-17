package business.servlet.ZZXL0050.common;

import business.servlet.ZZXL0050.ZZXL0050BMsg;
import business.servlet.ZZXL0050.constant.ZZXL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0050CommonLogic implements ZZXL0050Constant {

    /**
     * 画面の「一覧表：A」の行の背景色を、行単位に反転させます。
     * 
     * @param scrnMsg   ZZXL0050BMsg
     */
    public static void setTableColor( ZZXL0050BMsg scrnMsg ) {
        
        S21TableColorController tblColor = new S21TableColorController( screenId_00, scrnMsg );
        tblColor.setAlternateRowsBG( "A_LeftTBL",  scrnMsg.A );
        tblColor.setAlternateRowsBG( "A_RightTBL", scrnMsg.A );
        
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            if ( OUT_OF_OBJ.equals(scrnMsg.A.no(idx).dtMgtPgmId_A.getValue()) ) {
                scrnMsg.A.no(idx).xxChkBox_A.setInputProtected( true );
            } else {
                scrnMsg.A.no(idx).xxChkBox_A.setInputProtected( false );
            }
        }
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

        handler.setButtonProperties( CMN00_BTN1[0], CMN00_BTN1[1], CMN00_BTN1[2], 0, null );
        handler.setButtonProperties( CMN00_BTN2[0], CMN00_BTN2[1], CMN00_BTN2[2], 0, null );
        handler.setButtonProperties( CMN00_BTN3[0], CMN00_BTN3[1], CMN00_BTN3[2], 0, null );
        handler.setButtonProperties( CMN00_BTN4[0], CMN00_BTN4[1], CMN00_BTN4[2], 0, null );
        handler.setButtonProperties( CMN00_BTN5[0], CMN00_BTN5[1], CMN00_BTN5[2], 0, null );
        handler.setButtonProperties( CMN00_BTN6[0], CMN00_BTN6[1], CMN00_BTN6[2], 0, null );
        handler.setButtonProperties( CMN00_BTN7[0], CMN00_BTN7[1], CMN00_BTN7[2], 0, null );
        handler.setButtonProperties( CMN00_BTN8[0], CMN00_BTN8[1], CMN00_BTN8[2], 1, null );
        handler.setButtonProperties( CMN00_BTN9[0], CMN00_BTN9[1], CMN00_BTN9[2], 0, null );
        handler.setButtonProperties( CMN00_BTN10[0], CMN00_BTN10[1], CMN00_BTN10[2], 1, null );
        
        setButtonConfirmMsg(handler, CMN00_BTN7, true);
        setButtonConfirmMsg(handler, CMN01_BTN10, false);
    }

    /**
     * set screen01 button status
     * @param handler
     */
    public static void setButtonScrn01( S21CommonHandler handler ) {

        handler.setButtonProperties( CMN01_BTN1[0], CMN01_BTN1[1], CMN01_BTN1[2], 0, null );
        handler.setButtonProperties( CMN01_BTN2[0], CMN01_BTN2[1], CMN01_BTN2[2], 1, null );
        handler.setButtonProperties( CMN01_BTN3[0], CMN01_BTN3[1], CMN01_BTN3[2], 0, null );
        handler.setButtonProperties( CMN01_BTN4[0], CMN01_BTN4[1], CMN01_BTN4[2], 0, null );
        handler.setButtonProperties( CMN01_BTN5[0], CMN01_BTN5[1], CMN01_BTN5[2], 0, null );
        handler.setButtonProperties( CMN01_BTN6[0], CMN01_BTN6[1], CMN01_BTN6[2], 0, null );
        handler.setButtonProperties( CMN01_BTN7[0], CMN01_BTN7[1], CMN01_BTN7[2], 0, null );
        handler.setButtonProperties( CMN01_BTN8[0], CMN01_BTN8[1], CMN01_BTN8[2], 0, null );
        handler.setButtonProperties( CMN01_BTN9[0], CMN01_BTN9[1], CMN01_BTN9[2], 0, null );
        handler.setButtonProperties( CMN01_BTN10[0], CMN01_BTN10[1], CMN01_BTN10[2], 1, null );

        setButtonConfirmMsg(handler, CMN01_BTN10, true);
    }

}
