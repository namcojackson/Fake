/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6760.NMAL6760CMsg;

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
 * 2018/05/15   Fujitsu         M.Ohno          Update          QC#22715
 *</pre>
 */
public class NMAL6760Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2018/05/15 QC#22715 add start
        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        NMAL6760CMsg bizMsg = null;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:C
        if ("C".equals(sortTblNm)) {
            if (scrnMsg.C.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                bizMsg = new NMAL6760CMsg();
                bizMsg.setBusinessID("NMAL6760");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
            }
        }
        return bizMsg;
        // 2018/05/15 QC#22715 add end
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        // 2018/05/15 QC#22715 add start
        NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/15 QC#22715 add end

        // 2018/05/15 QC#22715 del start
//        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
//        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
//        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
//
//        S21SortTarget sortTarget = new S21SortTarget(scrnMsg.C, scrnMsg.C.no(0).getBaseContents());
//        S21SortKey sortKey = new S21SortKey();
//        sortKey.add(sortItemNm, sortOrderBy);
//        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, scrnMsg.C.getValidCount());
        // 2018/05/15 QC#22715 del end

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.C.no(0).getBaseContents());
    }
}
