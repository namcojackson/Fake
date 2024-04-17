/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1710;

import static business.servlet.NWAL1710.constant.NWAL1710Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1710.NWAL1710CMsg;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * NWAL1710Scrn00_TBLColumnSort
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1710Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CMsg bizMsg = null;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        scrnMsg.xxSortTblNm_A.setValue(sortTblNm);
        scrnMsg.xxSortItemNm_A.setValue(sortItemNm);
        scrnMsg.xxSortOrdByTxt_A.setValue(sortOrderBy);
        bizMsg = new NWAL1710CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CMsg bizMsg = (NWAL1710CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        scrnMsg.setFocusItem(scrnMsg.dsOrdCatgNm);
    }

}
