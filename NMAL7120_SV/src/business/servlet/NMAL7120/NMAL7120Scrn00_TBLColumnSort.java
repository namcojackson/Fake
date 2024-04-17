package business.servlet.NMAL7120;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.NMAL7120.NMAL7120CMsg;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import business.servlet.NMAL7120.NMAL7120BMsg;
import business.servlet.NMAL7120.common.NMAL7120CommonLogic;
import business.servlet.NMAL7120.constant.NMAL7120Constant;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL7120Scrn00_TBLColumnSort extends S21CommonHandler implements NMAL7120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
		
		//Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        
        //Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                // Set sort information
                scrnMsg.xxSortTblNm_01.setValue(sortTblNm);
                scrnMsg.xxSortItemNm_01.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt_01.setValue(sortOrderBy);

                NMAL7120CMsg bizMsg = new NMAL7120CMsg();
                bizMsg.setBusinessID(BUSINESS_ID);
                bizMsg.setFunctionCode(FUNC_CD_SRCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
		NMAL7120CMsg bizMsg  = (NMAL7120CMsg) cMsg;

		if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
            //control image file of sort column (Gif file. ASC or DESC.)
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
            
    		NMAL7120CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
    		scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H1);
		}
		
	}

}
