/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0960;

import static business.servlet.NSAL0960.constant.NSAL0960Constant.BUSINESS_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0960.NSAL0960CMsg;
import business.servlet.NSAL0960.common.NSAL0960CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0960Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;
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

                    NSAL0960CMsg bizMsg = new NSAL0960CMsg();
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

        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;
        NSAL0960CMsg bizMsg  = (NSAL0960CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NSAL0960CommonLogic.initialControlScreen(this, scrnMsg);

            // control image file of sort column (Gif file. ASC or DESC.)
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
    }
}
