/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NYEL0020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL0020.NYEL0020CMsg;
import business.servlet.NYEL0020.constant.NYEL0020Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class NYEL0020Scrn00_TBLColumnSort extends S21CommonHandler implements NYEL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL0020BMsg scrnMsg = (NYEL0020BMsg) bMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
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
                
                NYEL0020CMsg bizMsg = new NYEL0020CMsg();
                bizMsg.setBusinessID("NYEL0020");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        // Table:B
        if( "B".equals( sortTblNm ) ) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget( scrnMsg.B, scrnMsg.B.no(0).getBaseContents() );
            S21SortKey sortKey = new S21SortKey();
            sortKey.add( sortItemNm, sortOrderBy );
            S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, scrnMsg.B.getValidCount() );
            
            // control image file of sort column (Gif file. ASC or DESC.)
            S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents() );
            
            S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME, scrnMsg);
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
            

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                String xxErrFlg = scrnMsg.B.no(i).xxErrFlg_B1.getValue();
                if (xxErrFlg.equals("1")) {
                    tblColor.setRowStyle("B", i, "pErr");
                }
            }

        }
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL0020BMsg scrnMsg = (NYEL0020BMsg) bMsg;
        NYEL0020CMsg bizMsg  = (NYEL0020CMsg) cMsg;

        if( bizMsg != null ) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        
        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents() );

	}

}
