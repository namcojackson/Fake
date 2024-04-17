/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0120;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0120.ZZOL0120CMsg;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZOL0120Scrn02_TBLColumnSort extends S21CommonHandler implements ZZOL0120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue( S21TableColumnSortConstant.SORT_TABLE_NAME );
        String sortItemNm  = param.getSingleValue( S21TableColumnSortConstant.SORT_ITEM_NAME );
        String sortOrderBy = param.getSingleValue( S21TableColumnSortConstant.ORDER_BY );

        // Table:C
        if( "C".equals( sortTblNm ) ) {
            if( scrnMsg.C.getValidCount() > 0 ) {
                
                scrnMsg.xxSortTblNm_C1.setValue( sortTblNm );
                scrnMsg.xxSortItemNm_C1.setValue( sortItemNm );
                scrnMsg.xxSortOrdByTxt_C1.setValue( sortOrderBy );
                
                ZZOL0120CMsg bizMsg = new ZZOL0120CMsg();
                bizMsg.setBusinessID("ZZOL0120");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;
        ZZOL0120CMsg bizMsg  = (ZZOL0120CMsg) cMsg;

        if( bizMsg != null ) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME_02, scrnMsg);
        tblColor.setAlternateRowsBG("C", scrnMsg.C);

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.C.no(0).getBaseContents() );

	}

}
