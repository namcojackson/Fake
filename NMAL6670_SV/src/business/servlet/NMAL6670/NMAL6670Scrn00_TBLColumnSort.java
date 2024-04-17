/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/14   Fujitsu         K.Kimura        Create          WDS#1458 Installment Invoice modification
 *</pre>
 */
package business.servlet.NMAL6670;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6670.NMAL6670CMsg;
import business.servlet.NMAL6670.NMAL6670BMsg;
import business.servlet.NMAL6670.common.NMAL6670CommonLogic;
import business.servlet.NMAL6670.constant.NMAL6670Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class NMAL6670Scrn00_TBLColumnSort extends S21CommonHandler implements NMAL6670Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL6670BMsg scrnMsg = (NMAL6670BMsg) bMsg;
        NMAL6670CommonLogic.checkDetail(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6670BMsg scrnMsg = (NMAL6670BMsg) bMsg;

		// Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        
        if(scrnMsg.A.getValidCount() > 0) {
            
            scrnMsg.xxSortTblNm.setValue(sortTblNm);
            scrnMsg.xxSortItemNm.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
            
    		NMAL6670CMsg bizMsg = new NMAL6670CMsg();
    		bizMsg.setBusinessID("NMAL6670");
    		bizMsg.setFunctionCode("20");
    		EZDMsg.copy(scrnMsg, null, bizMsg, null);

    		return bizMsg;
        } else {
            return null;
        }

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6670BMsg scrnMsg = (NMAL6670BMsg) bMsg;
		NMAL6670CMsg bizMsg  = (NMAL6670CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NMAL6670CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        }

        NMAL6670CommonLogic.addCheckItemForDoProcess(scrnMsg);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.A.no(0).istlPmtTermCd_A1);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
	}

}
