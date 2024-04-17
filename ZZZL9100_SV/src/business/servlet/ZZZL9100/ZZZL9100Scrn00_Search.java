package business.servlet.ZZZL9100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL9100.ZZZL9100CMsg;
import business.servlet.ZZZL9100.constant.ZZZL9100Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZZL9100Scrn00_Search extends S21CommonHandler implements ZZZL9100Constant {

	@Override
	protected void checkInput( EZDApplicationContext ctx, EZDBMsg bMsg ) {
		
		ZZZL9100BMsg scrnMsg = (ZZZL9100BMsg)bMsg;
		
		scrnMsg.addCheckItem( scrnMsg.ezBusinessID );
		scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData( EZDApplicationContext ctx, EZDBMsg bMsg ) {
 		
		ZZZL9100BMsg scrnMsg = (ZZZL9100BMsg)bMsg;

		ZZZL9100CMsg bizMsg = new ZZZL9100CMsg();
		bizMsg.setBusinessID( "ZZZL9100" );
		bizMsg.setFunctionCode( "20" );
		EZDMsg.copy( scrnMsg, null, bizMsg, null );
		
 		return bizMsg;
	}

	@Override
	protected void doProcess( EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg ) {
		
		ZZZL9100BMsg scrnMsg = (ZZZL9100BMsg)bMsg;
		ZZZL9100CMsg bizMsg  = (ZZZL9100CMsg)cMsg;
		
		EZDMsg.copy( bizMsg, null, scrnMsg, null );
		
		// clear image file of sort columns (Gif file. ASC or DESC.)
		S21SortColumnIMGController.clearIMG( "ZZZL9100Scrn00", scrnMsg, scrnMsg.A.no( 0 ).getBaseContents() );
		
		if( "E".equals( bizMsg.getMessageKind() ) ) {
			throw new EZDFieldErrorException();
		}
		
		for( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
			scrnMsg.A.no( i ).xxNo_A1.setValue( i+1 );
		}
		
		// set alternate rows back-ground color 
		S21TableColorController tblColor = new S21TableColorController( getSubmitedScrnId( ctx ), scrnMsg );
		tblColor.setAlternateRowsBG( "A", scrnMsg.A );
	}
	
}