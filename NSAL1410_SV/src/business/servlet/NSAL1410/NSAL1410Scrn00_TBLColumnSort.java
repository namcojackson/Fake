/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410;

import static business.servlet.NSAL1410.common.NSAL1410CommonLogic.*;
import static business.servlet.NSAL1410.constant.NSAL1410Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1410.NSAL1410CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;

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

                NSAL1410CMsg bizMsg = new NSAL1410CMsg();
                bizMsg.setBusinessID(BUSINESS_ID);
                bizMsg.setFunctionCode(FUNCTION_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;
        NSAL1410CMsg bizMsg = (NSAL1410CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        initialControlScreen(this, scrnMsg);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
