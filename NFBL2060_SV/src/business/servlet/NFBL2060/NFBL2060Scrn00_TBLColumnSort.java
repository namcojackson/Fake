/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import static business.servlet.NFBL2060.constant.NFBL2060Constant.BIZ_ID;
import static business.servlet.NFBL2060.constant.NFBL2060Constant.FUNC_CD_20;
import static business.servlet.NFBL2060.constant.NFBL2060Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2060.NFBL2060CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * NFBL2060Scrn00_TBLColumnSort
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/30   Fujitsu         C.Tanaka        Create          QC#5521
 *</pre>
 */
public class NFBL2060Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        NFBL2060CMsg bizMsg = null;
        boolean sort = false;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NFBL2060Bean.D.equals(sortTblNm)) {
            if (scrnMsg.D.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                sort = true;
            }
        } else if (NFBL2060Bean.S.equals(sortTblNm)) {
            if (scrnMsg.S.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                sort = true;
            }
        }

        if (sort) {
            bizMsg = new NFBL2060CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_20);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String sortTblNm = scrnMsg.xxSortTblNm.getValue();
        // Set sort icons.
        if (NFBL2060Bean.D.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.D.no(0).getBaseContents());
        } else if (NFBL2060Bean.S.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.S.no(0).getBaseContents());
        }
    }

}
