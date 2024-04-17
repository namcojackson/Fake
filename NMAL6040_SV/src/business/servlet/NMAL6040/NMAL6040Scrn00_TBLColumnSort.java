/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6040.constant.NMAL6040Constant;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 *</pre>
 */
public class NMAL6040Scrn00_TBLColumnSort extends S21CommonHandler implements NMAL6040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Operation
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Operation
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6040BMsg scrnMsg = (NMAL6040BMsg) bMsg;

        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        S21SortTarget sortTarget = new S21SortTarget(scrnMsg.A, scrnMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrderBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, scrnMsg.A.getValidCount());

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }

}
