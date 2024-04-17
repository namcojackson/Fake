/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6550.NMAL6550CMsg;
import business.servlet.NMAL6550.common.NMAL6550CommonLogic;
import business.servlet.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class NMAL6550Scrn00_TBLColumnSort extends S21CommonHandler implements NMAL6550Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;
        NMAL6550CommonLogic.checkDetail(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

 	    NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;

        // Parameters for Table Column Sort.
 	    Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
 	    String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
 	    String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
 	    String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if(scrnMsg.A.getValidCount() > 0) {

            scrnMsg.xxSortTblNm.setValue(sortTblNm);
            scrnMsg.xxSortItemNm.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

            NMAL6550CMsg bizMsg = NMAL6550CommonLogic.setBizFunction("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else {
            return null;
        }
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;
		NMAL6550CMsg bizMsg  = (NMAL6550CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6550CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        
        scrnMsg.setFocusItem(scrnMsg.A.no(0).glblCmpySortNum_A1);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
	}

}
