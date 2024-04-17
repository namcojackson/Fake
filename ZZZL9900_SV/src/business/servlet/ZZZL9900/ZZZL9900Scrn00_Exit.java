package business.servlet.ZZZL9900;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL9900.constant.ZZZL9900Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL9900Scrn00_Exit extends S21CommonHandler implements ZZZL9900Constant {

	@Override
	protected void checkInput( EZDApplicationContext ctx, EZDBMsg bMsg ) {
		
		//ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg)bMsg;
		
		
	}

 	@Override
	protected EZDCMsg setRequestData( EZDApplicationContext ctx, EZDBMsg bMsg ) {
 		
		//ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg)bMsg;

		//ZZZL9900CMsg bizMsg = new ZZZL9900CMsg();
		//bizMsg.setBusinessID( "ZZZL9900" );
		//bizMsg.setFunctionCode( "20" );
		//EZDMsg.copy( scrnMsg, null, bizMsg, null );

 		//return bizMsg;
 		
 		return null;
	}

	@Override
	protected void doProcess( EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg ) {
		
		//ZZZL9900BMsg scrnMsg = (ZZZL9900BMsg)bMsg;
		//ZZZL9900CMsg bizMsg  = (ZZZL9900CMsg)cMsg;
		
		//EZDMsg.copy( bizMsg, null, scrnMsg, null );
		
		
	}
	
}