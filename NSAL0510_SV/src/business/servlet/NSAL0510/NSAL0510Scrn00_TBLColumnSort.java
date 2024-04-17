/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510;

import static business.servlet.NSAL0510.constant.NSAL0510Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0510.NSAL0510CMsg;
import business.servlet.NSAL0510.constant.NSAL0510Constant.FUNC;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0510Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                NSAL0510CMsg bizMsg = new NSAL0510CMsg();
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;
        NSAL0510CMsg bizMsg  = (NSAL0510CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        //NSAL0510CommonLogic.doSearchResultListUIControl(this, scrnMsg);
        ////NSAL0510CommonLogic.setDefaultFocus(scrnMsg);
   }
}
