/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1060;

import static business.servlet.NLCL1060.constant.NLCL1060Constant.FUNCTION_ID_SEARCH;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.MY_BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1060.NLCL1060CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing to do.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;
        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {
                // Set sort information
                scrnMsg.xxSortTblNm_A1.setValue(sortTblNm);
                scrnMsg.xxSortItemNm_A1.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt_A1.setValue(sortOrderBy);
                NLCL1060CMsg bizMsg = new NLCL1060CMsg();
                bizMsg.setBusinessID(MY_BUSINESS_ID);
                bizMsg.setFunctionCode(FUNCTION_ID_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;

         NLCL1060CMsg bizMsg = (NLCL1060CMsg) cMsg;
         if (bizMsg != null) {
             EZDMsg.copy(bizMsg, null, scrnMsg, null);
             // control image file of sort column (Gif file. ASC or
             // DESC.)
             S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
             scrnMsg.setFocusItem(scrnMsg.mdseCd_H);
         }
    }

}
