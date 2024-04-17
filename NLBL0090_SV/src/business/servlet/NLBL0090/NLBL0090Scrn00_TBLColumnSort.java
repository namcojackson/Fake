/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.BIZ_APP_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SCRN_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SRCH_FUNCTION_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TBL_NAME_A;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TBL_NAME_B;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0090.NLBL0090CMsg;
import business.servlet.NLBL0090.common.NLBL0090CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // return bizMsg;
        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.B.no(0).getBaseContents());

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:A or B
        if (TBL_NAME_A.equals(sortTblNm) && scrnMsg.A.getValidCount() > 0 || TBL_NAME_B.equals(sortTblNm) && scrnMsg.B.getValidCount() > 0) {

            scrnMsg.xxSortTblNm.setValue(sortTblNm);
            scrnMsg.xxSortItemNm.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

            NLBL0090CMsg bizMsg = new NLBL0090CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(SRCH_FUNCTION_ID);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = (NLBL0090CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NLBL0090CommonLogic.setTblBackColorB(scrnMsg);

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.bolNum_H1);

    }

}
