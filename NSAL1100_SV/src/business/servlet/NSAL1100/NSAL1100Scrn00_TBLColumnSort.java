/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1100;

import static business.servlet.NSAL1100.constant.NSAL1100Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1100.NSAL1100CMsg;
import business.servlet.NSAL1100.common.NSAL1100CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1100Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1100BMsg scrnMsg = (NSAL1100BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                NSAL1100CMsg bizMsg = new NSAL1100CMsg();
                bizMsg.setBusinessID(BUSINESS_ID);
                bizMsg.setFunctionCode(FUNC_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1100BMsg scrnMsg = (NSAL1100BMsg) bMsg;
        NSAL1100CMsg bizMsg = (NSAL1100CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        NSAL1100CommonLogic.initialControlScreen(this, scrnMsg);
        // control image file of sort column
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
