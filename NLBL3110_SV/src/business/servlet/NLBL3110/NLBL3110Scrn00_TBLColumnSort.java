/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3110.NLBL3110CMsg;
import business.servlet.NLBL3110.common.NLBL3110CommonLogic;
import business.servlet.NLBL3110.constant.NLBL3110Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 *</pre>
 */
public class NLBL3110Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3110BMsg scrnMsg = (NLBL3110BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if ("A".equals(sortTblNm)) {

            if (0 < scrnMsg.A.getValidCount()) {

                scrnMsg.xxSortTblNm_A.setValue(sortTblNm);
                scrnMsg.xxSortItemNm_A.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt_A.setValue(sortOrderBy);

                NLBL3110CMsg bizMsg = new NLBL3110CMsg();
                bizMsg.setBusinessID(NLBL3110Constant.BUSINESS_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3110BMsg scrnMsg = (NLBL3110BMsg) bMsg;
        NLBL3110CMsg bizMsg  = (NLBL3110CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            //control image file of sort column (Gif file. ASC or DESC.)
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

            NLBL3110CommonLogic.controlScreen(getUserProfileService(), this, scrnMsg);
        }

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.cpoNum_H1);
    }
}