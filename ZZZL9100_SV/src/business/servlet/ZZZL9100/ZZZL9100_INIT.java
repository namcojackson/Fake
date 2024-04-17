package business.servlet.ZZZL9100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL9100.ZZZL9100BMsg;
import business.servlet.ZZZL9100.common.ZZZL9100CommonLogic;
import business.servlet.ZZZL9100.constant.ZZZL9100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL9100_INIT extends S21INITCommonHandler implements ZZZL9100Constant {

	@Override
	protected void checkInput( EZDApplicationContext ctx, EZDBMsg bMsg ) {
		
		//ZZZL9100BMsg scrnMsg = (ZZZL9100BMsg)bMsg;
		
		
	}

 	@Override
	protected EZDCMsg setRequestData( EZDApplicationContext ctx, EZDBMsg bMsg ) {
 		
		//ZZZL9100BMsg scrnMsg = (ZZZL9100BMsg)bMsg;

		//ZZZL9100CMsg bizMsg = new ZZZL9100CMsg();
		//bizMsg.setBusinessID( "ZZZL9100" );
		//bizMsg.setFunctionCode( "20" );
		//EZDMsg.copy( scrnMsg, null, bizMsg, null );

 		//return bizMsg;
 		
 		return null;
	}

	@Override
	protected void doProcess( EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg ) {
		
		ZZZL9100BMsg scrnMsg = (ZZZL9100BMsg)bMsg;
		//ZZZL9100CMsg bizMsg  = (ZZZL9100CMsg)cMsg;
		
		//EZDMsg.copy( bizMsg, null, scrnMsg, null );
		
		ZZZL9100CommonLogic.setButtonProperties_INIT( this );
		scrnMsg.setFocusItem( scrnMsg.ezBusinessID );
	}

	@Override
	protected void setNameForMessage(EZDBMsg arg0) {
	}
	
}