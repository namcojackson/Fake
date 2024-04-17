/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1010.NLCL1010CMsg;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2014/03/28   CSAI            Y.Imazu         Update          ITG#511207
 *</pre>
 */
public class NLCL1010Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:A
        if (NLCL1010Constant.TABLE_A.equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                NLCL1010CMsg bizMsg = new NLCL1010CMsg();
                bizMsg.setBusinessID(NLCL1010Constant.BUSINESS_ID);
                bizMsg.setFunctionCode(NLCL1010Constant.FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        NLCL1010CMsg bizMsg = (NLCL1010CMsg) cMsg;

        /* 03/28/2014 CSAI Y.Imazu Add ITG#511207 START */
        if (bizMsg != null) {
        /* 03/28/2014 CSAI Y.Imazu Add ITG#511207 END */

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            // control image file of sort column (ASC or DESC.)
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        /* 03/28/2014 CSAI Y.Imazu Add ITG#511207 START */
        }
        /* 03/28/2014 CSAI Y.Imazu Add ITG#511207 END */
    }

}
