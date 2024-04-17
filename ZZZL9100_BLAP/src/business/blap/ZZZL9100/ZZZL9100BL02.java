package business.blap.ZZZL9100;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.parts.ZZZQ910000Join;
import business.parts.ZZZQ910000PMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL9100BL02 extends S21BusinessHandler {

	@Override
	protected void doProcess( EZDCMsg cMsg, EZDSMsg sMsg ) {
		super.preDoProcess( cMsg, sMsg );
		
		try {
			String screenAplID = cMsg.getScreenAplID();
			
			if( "ZZZL9100Scrn00_Search".equals( screenAplID ) ) {
				doProcess_ZZZL9100Scrn00_Search( (ZZZL9100CMsg)cMsg, sMsg );
			} else {
				throw new S21AbendException( "It's illegal ScreenAplID : " + screenAplID );
			}

		} finally {
			super.postDoProcess( cMsg, sMsg );
		}
	}

	private void doProcess_ZZZL9100Scrn00_Search( ZZZL9100CMsg bizMsg, EZDSMsg sMsg ) {
		
		// create IF of ZZZQ910000Join Join Component
		ZZZQ910000PMsg joinMsg = new ZZZQ910000PMsg();
		joinMsg.ezBusinessID.setValue( bizMsg.ezBusinessID.getValue() );
		
		// execute ZZZQ910000Join Join Component
		ZZZQ910000Join.getInstance().exec( joinMsg );
		
		// get retunCode of executed ZZZQ910000Join Join Component
		String retCd = joinMsg.getReturnCode();
		
		// [Information]
		if( RTNCD_NORMAL.equals( retCd ) ) {
//			bizMsg.setMessageInfo( "99999999I" ); // TODO message ID hasn't decided yet...
			EZDMsg.copy( joinMsg.A, null, bizMsg.A, null );
			
		// [Error]NOT FOUND
		} else if( RTNCD_NOT_FOUND.equals( retCd ) ) {
			bizMsg.A.setValidCount( 0 );
//			bizMsg.setMessageInfo( "99999999E" ); // TODO message ID hasn't decided yet...
			
		// [Warning]RESULT MAX OVER
		} if( RTNCD_RESULT_MAX_OVER.equals( retCd ) ) {
//			bizMsg.setMessageInfo( "99999999W" ); // TODO message ID hasn't decided yet...
			
		} else {
		}
		
	}

}