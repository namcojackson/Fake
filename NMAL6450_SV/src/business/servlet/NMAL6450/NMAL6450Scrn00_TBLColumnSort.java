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
package business.servlet.NMAL6450;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6450.NMAL6450CMsg;
import business.servlet.NMAL6450.common.NMAL6450CommonLogic;
import business.servlet.NMAL6450.constant.NMAL6450Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class NMAL6450Scrn00_TBLColumnSort extends S21CommonHandler implements NMAL6450Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL6450BMsg scrnMsg = (NMAL6450BMsg) bMsg;
        NMAL6450CommonLogic.checkDetail(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6450BMsg scrnMsg = (NMAL6450BMsg) bMsg;

		// Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        
        if(scrnMsg.A.getValidCount() > 0) {
            
            scrnMsg.xxSortTblNm.setValue(sortTblNm);
            scrnMsg.xxSortItemNm.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
            
    		NMAL6450CMsg bizMsg = new NMAL6450CMsg();
    		bizMsg.setBusinessID("NMAL6450");
    		bizMsg.setFunctionCode("20");
    		EZDMsg.copy(scrnMsg, null, bizMsg, null);

    		return bizMsg;
        } else {
            return null;
        }

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6450BMsg scrnMsg = (NMAL6450BMsg) bMsg;
		NMAL6450CMsg bizMsg  = (NMAL6450CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NMAL6450CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        }

        NMAL6450CommonLogic.addCheckItemForDoProcess(scrnMsg);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.A.no(0).pmtTermSortNum_A1);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
	}

}
