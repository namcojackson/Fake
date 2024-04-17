/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.NLBL3200Bean;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLBL3200Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NLBL3200Constant.TAB_SO_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            if (NLBL3200Bean.A.equals(sortTblNm)) {

                if (scrnMsg.A.getValidCount() > 0) {

                    scrnMsg.xxSortTblNm.setValue(sortTblNm);
                    scrnMsg.xxSortItemNm.setValue(sortItemNm);
                    scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                    NLBL3200CMsg bizMsg = new NLBL3200CMsg();
                    bizMsg.setBusinessID(NLBL3200Constant.BUSINESS_ID);
                    bizMsg.setFunctionCode("20");
                    EZDMsg.copy(scrnMsg, null, bizMsg, null);

                    return bizMsg;
                }
            }

        } else {

            if (NLBL3200Bean.B.equals(sortTblNm)) {

                if (scrnMsg.B.getValidCount() > 0) {

                    scrnMsg.xxSortTblNm.setValue(sortTblNm);
                    scrnMsg.xxSortItemNm.setValue(sortItemNm);
                    scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                    NLBL3200CMsg bizMsg = new NLBL3200CMsg();
                    bizMsg.setBusinessID(NLBL3200Constant.BUSINESS_ID);
                    bizMsg.setFunctionCode("20");
                    EZDMsg.copy(scrnMsg, null, bizMsg, null);

                    return bizMsg;
                }
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        if (NLBL3200Constant.TAB_SO_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        } else {

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        }

        // screen ctrl
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3200Constant.BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItem(scrnMsg, this, funcList);
    }
}
