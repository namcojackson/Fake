/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7110.NMAL7110CMsg;
import business.servlet.NMAL7110.constant.NMAL7110Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 * 02/20/2017   Fujitsu         R.Nakamura      Update          QC#17675
 *</pre>
 */
public class NMAL7110Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;
        NMAL7110CMsg bizMsg = new NMAL7110CMsg();

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NMAL7110Bean.A.equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                bizMsg.setBusinessID(NMAL7110Constant.BIZ_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                // Add Start 2017/02/20 QC#17675
                return bizMsg;
                // Add End 2017/02/20 QC#17675
            }
        }

        // Mod Start 2017/02/20 QC#17675
//        return bizMsg;
        return null;
        // Mod End 2017/02/20 QC#17675
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;
        NMAL7110CMsg bizMsg  = (NMAL7110CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String sortTblNm = scrnMsg.xxSortTblNm.getValue();

        if (NMAL7110Bean.A.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
    }
}
