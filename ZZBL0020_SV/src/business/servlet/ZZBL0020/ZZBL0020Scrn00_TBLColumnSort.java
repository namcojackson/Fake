/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0020;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZBL0020.ZZBL0020CMsg;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;
import business.servlet.ZZBL0020.ZZBL0020BMsg;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZBL0020Scrn00_TBLColumnSort extends S21CommonHandler implements ZZBL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		
 		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;
		
	    Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
	    String sortTblNm   = param.getSingleValue( S21TableColumnSortConstant.SORT_TABLE_NAME );
	    String sortItemNm  = param.getSingleValue( S21TableColumnSortConstant.SORT_ITEM_NAME );
	    String sortOrderBy = param.getSingleValue( S21TableColumnSortConstant.ORDER_BY );
	    
		ZZBL0020CMsg bizMsg = new ZZBL0020CMsg();
		
        // Pass scrren attributes to BLAP
        bizMsg.setCustomAttribute(S21TableColumnSortConstant.SORT_TABLE_NAME, sortTblNm);
        bizMsg.setCustomAttribute(S21TableColumnSortConstant.SORT_ITEM_NAME, sortItemNm);
        bizMsg.setCustomAttribute(S21TableColumnSortConstant.ORDER_BY, sortOrderBy);
        
		bizMsg.setBusinessID("ZZBL0020");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;
		ZZBL0020CMsg bizMsg  = (ZZBL0020CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
		S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents() );
	}
}