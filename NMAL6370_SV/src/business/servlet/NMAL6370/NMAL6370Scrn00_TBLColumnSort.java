/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/02   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NMAL6370;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6370.NMAL6370CMsg;
import business.servlet.NMAL6370.common.NMAL6370CommonLogic;
import business.servlet.NMAL6370.constant.NMAL6370Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class NMAL6370Scrn00_TBLColumnSort extends S21CommonHandler implements NMAL6370Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL6370BMsg scrnMsg = (NMAL6370BMsg) bMsg;
        NMAL6370CommonLogic.checkDetail(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6370BMsg scrnMsg = (NMAL6370BMsg) bMsg;

		// Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        
        if(scrnMsg.A.getValidCount() > 0) {
            
            scrnMsg.xxSortTblNm.setValue(sortTblNm);
            scrnMsg.xxSortItemNm.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
            
    		NMAL6370CMsg bizMsg = new NMAL6370CMsg();
    		bizMsg.setBusinessID("NMAL6370");
    		bizMsg.setFunctionCode("20");
    		EZDMsg.copy(scrnMsg, null, bizMsg, null);

    		return bizMsg;
        } else {
            return null;
        }

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6370BMsg scrnMsg = (NMAL6370BMsg) bMsg;
		NMAL6370CMsg bizMsg  = (NMAL6370CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6370CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        NMAL6370CommonLogic.addCheckItemForDoProcess(scrnMsg);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.A.no(0).ctacTpSortNum_A1);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
	}

}
