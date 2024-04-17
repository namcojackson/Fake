package business.servlet.ZZBL0010.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZBL0010.ZZBL0010BMsg;
import business.servlet.ZZBL0010.ZZBL0010Bean;
import business.servlet.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZBL0010CommonLogic implements ZZBL0010Constant {
	
	static public void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

/**
 * 
 * @param scrnMsg
 */
	public static void setTableColor( ZZBL0010BMsg scrnMsg ) {
		
		S21TableColorController tblColor = new S21TableColorController( "ZZBL0010Scrn00", scrnMsg );
		tblColor.clearRowsBG( ZZBL0010Bean.A, scrnMsg.A );
		
		if( scrnMsg.A.getValidCount() > 0 ) {
			
			String styleClass   = "";
			
			for( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
				styleClass = i%2 == 0  ? "pEvenNumberBGcolor" : "" ;
				tblColor.setRowStyle( ZZBL0010Bean.A, i, styleClass );
			}
		}	
	}
}
