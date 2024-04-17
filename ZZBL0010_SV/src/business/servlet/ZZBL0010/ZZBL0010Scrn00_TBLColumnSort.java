/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZBL0010.ZZBL0010CMsg;
import business.servlet.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZBL0010Scrn00_TBLColumnSort extends S21CommonHandler implements ZZBL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;

		// Parameters for Table Column Sort.
		Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
		String sortTblNm   = param.getSingleValue( S21TableColumnSortConstant.SORT_TABLE_NAME );
		String sortItemNm  = param.getSingleValue( S21TableColumnSortConstant.SORT_ITEM_NAME );
		String sortOrderBy = param.getSingleValue( S21TableColumnSortConstant.ORDER_BY );

		// Table:A
		if( "A".equals( sortTblNm ) ) {
			if( scrnMsg.A.getValidCount() > 0 ) {
				
		 		scrnMsg.xxSortTblNm.setValue( sortTblNm );
		 		scrnMsg.xxSortItemNm.setValue( sortItemNm );
		 		scrnMsg.xxSortOrdByTxt.setValue( sortOrderBy );
				ZZBL0010CMsg bizMsg = new ZZBL0010CMsg();
				bizMsg.setBusinessID("ZZBL0010");
				bizMsg.setFunctionCode("20");
				EZDMsg.copy(scrnMsg, null, bizMsg, null);

				return bizMsg;
			}
		}
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;
		ZZBL0010CMsg bizMsg  = (ZZBL0010CMsg) cMsg;

		if( bizMsg != null ) {
			EZDMsg.copy(bizMsg, null, scrnMsg, null);
		}

		// control image file of sort column (Gif file. ASC or DESC.)
		S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents() );
	}
}