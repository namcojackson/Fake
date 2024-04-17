/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6790;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL6790Scrn00_TBLColumnSortDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        S21SortTarget sortTarget = new S21SortTarget(scrnMsg.B, scrnMsg.B.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrderBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, scrnMsg.B.getValidCount());

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());

    }
}
