/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3080.NLBL3080CMsg;
import business.servlet.NLBL3080.common.NLBL3080CommonLogic;
import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 *</pre>
 */
public class NLBL3080Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        boolean doRequest = false;

        if (NLBL3080Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.A.getValidCount()) {

            scrnMsg.xxSortTblNm_A.setValue(sortTblNm);
            scrnMsg.xxSortItemNm_A.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt_A.setValue(sortOrderBy);
            doRequest = true;

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.B.getValidCount()) {

            scrnMsg.xxSortTblNm_B.setValue(sortTblNm);
            scrnMsg.xxSortItemNm_B.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt_B.setValue(sortOrderBy);
            doRequest = true;
        }

        if (doRequest) {

            NLBL3080CMsg bizMsg = new NLBL3080CMsg();
            bizMsg.setBusinessID(NLBL3080Constant.BUSINESS_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;

        } else {

            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        NLBL3080CMsg bizMsg  = (NLBL3080CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NLBL3080CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        }
    }
}
