/*
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLBL3070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.servlet.NLBL3070.common.NLBL3070CommonLogic;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLBL3070Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        boolean doRequest = false;

        if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.A.getValidCount()) {

            scrnMsg.xxSortTblNm_A.setValue(sortTblNm);
            scrnMsg.xxSortItemNm_A.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt_A.setValue(sortOrderBy);
            doRequest = true;

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue()) && 0 < scrnMsg.B.getValidCount()) {

            scrnMsg.xxSortTblNm_B.setValue(sortTblNm);
            scrnMsg.xxSortItemNm_B.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt_B.setValue(sortOrderBy);
            doRequest = true;
        }

        if (doRequest) {

            NLBL3070CMsg bizMsg = new NLBL3070CMsg();
            bizMsg.setBusinessID(NLBL3070Constant.BUSINESS_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;

        } else {

            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg  = (NLBL3070CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NLBL3070CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);

            if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

                if (NLBL3070Bean.A.equals(scrnMsg.xxSortTblNm_A.getValue())) {

                    S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
                }

            } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

                if (NLBL3070Bean.B.equals(scrnMsg.xxSortTblNm_B.getValue())) {

                    S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
                }
            }
        }
    }
}
