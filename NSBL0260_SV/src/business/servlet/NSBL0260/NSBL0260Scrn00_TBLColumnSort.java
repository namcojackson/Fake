/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0260;

import static business.servlet.NSBL0260.constant.NSBL0260Constant.BUSINESS_ID;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0260.NSBL0260CMsg;
import business.servlet.NSBL0260.common.NSBL0260CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSBL0260Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {
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

                    NSBL0260CMsg bizMsg = new NSBL0260CMsg();
                    bizMsg.setBusinessID(BUSINESS_ID);
                    bizMsg.setFunctionCode(FUNCTION_SEARCH);
                    EZDMsg.copy(scrnMsg, null, bizMsg, null);

                    return bizMsg;
                }
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
        NSBL0260CMsg bizMsg = (NSBL0260CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NSBL0260CommonLogic.initialControlScreen(this, scrnMsg);

            // control image file of sort column (Gif file. ASC or DESC.)
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
    }
}
