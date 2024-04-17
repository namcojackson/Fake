package business.servlet.ZZZL9100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL9100.ZZZL9100BMsg;
import business.servlet.ZZZL9100.constant.ZZZL9100Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZZL9100Scrn00_TBLColumnSort extends S21CommonHandler implements ZZZL9100Constant {

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
		
		Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
		String sortTblNm   = param.getSingleValue( S21TableColumnSortConstant.SORT_TABLE_NAME );
		String sortItemNm  = param.getSingleValue( S21TableColumnSortConstant.SORT_ITEM_NAME );
		String sortOrderBy = param.getSingleValue( S21TableColumnSortConstant.ORDER_BY );
	
		// Table:A
		if( "A".equals( sortTblNm ) ) {
			// execute column sort function
			S21SortTarget sortTarget = new S21SortTarget( scrnMsg.A, scrnMsg.A.no(0).getBaseContents() );
			S21SortKey sortKey = new S21SortKey();
			sortKey.add( sortItemNm, sortOrderBy );
			S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, scrnMsg.A.getValidCount() );
			
			// control image file of sort column (Gif file. ASC or DESC.)
			S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents() );
		}
	}
	
}