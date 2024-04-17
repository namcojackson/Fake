/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0110;


import parts.common.*;
import parts.servletcommon.*;
//import business.blap.ZZIL0110.ZZIL0110CMsg;

import business.blap.ZZIL0110.ZZIL0110CMsg;
import business.servlet.ZZIL0110.common.ZZIL0110CommonLogic;
import business.servlet.ZZIL0110.constant.ZZIL0110Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZIL0110Scrn00_TBLColumnSort extends S21CommonHandler {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;


	}

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;

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

                ZZIL0110CMsg bizMsg = new ZZIL0110CMsg();
                bizMsg.setBusinessID(ZZIL0110Constant.BUSINESS_ID);
                bizMsg.setFunctionCode(ZZIL0110Constant.ReadCode);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;
        ZZIL0110CMsg bizMsg = (ZZIL0110CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZIL0110CommonLogic.setTableColorA(scrnMsg, bizMsg);
        ZZIL0110CommonLogic.checkCommonInputScrn00(scrnMsg);
    }
}
