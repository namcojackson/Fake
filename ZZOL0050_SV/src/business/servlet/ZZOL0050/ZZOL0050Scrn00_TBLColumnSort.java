/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0050;


import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZOL0050.ZZOL0050CMsg;
import business.servlet.ZZOL0050.common.ZZOL0050CommonLogic;
import business.servlet.ZZOL0050.constant.ZZOL0050Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZOL0050Scrn00_TBLColumnSort extends S21CommonHandler implements ZZOL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.upldCsvId );
        scrnMsg.addCheckItem( scrnMsg.upldCsvNm );
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                ZZOL0050CMsg bizMsg = new ZZOL0050CMsg();
                bizMsg.setBusinessID("ZZOL0050");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;
		ZZOL0050CMsg bizMsg  = (ZZOL0050CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZOL0050CommonLogic.setTableColor( scrnMsg );

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.upldCsvId );
        scrnMsg.addCheckItem( scrnMsg.upldCsvNm );
        scrnMsg.putErrorScreen();
	}

}
