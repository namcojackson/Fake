package business.servlet.ZZZL9100.common;

import business.servlet.ZZZL9100.constant.ZZZL9100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL9100CommonLogic implements ZZZL9100Constant {

	public static void setButtonProperties_INIT( S21CommonHandler handler ) {
		handler.setButtonProperties( BTN_CMN_BTN1[0],	BTN_CMN_BTN1[1],	BTN_CMN_BTN1[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_BTN2[0],	BTN_CMN_BTN2[1],	BTN_CMN_BTN2[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_BTN3[0],	BTN_CMN_BTN3[1],	BTN_CMN_BTN3[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_BTN4[0],	BTN_CMN_BTN4[1],	BTN_CMN_BTN4[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_BTN5[0],	BTN_CMN_BTN5[1],	BTN_CMN_BTN5[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_BTN6[0],	BTN_CMN_BTN6[1],	BTN_CMN_BTN6[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_BTN7[0],	BTN_CMN_BTN7[1],	BTN_CMN_BTN7[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_CLEAR[0],	BTN_CMN_CLEAR[1],	BTN_CMN_CLEAR[2],	1,	null );
		handler.setButtonProperties( BTN_CMN_BTN9[0],	BTN_CMN_BTN9[1],	BTN_CMN_BTN9[2],	0,	null );
		handler.setButtonProperties( BTN_CMN_RETURN[0],	BTN_CMN_RETURN[1],	BTN_CMN_RETURN[2],	1,	null );
	}
	
}
