/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.BIZ_APP_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3200.NMAL3200CMsg;
import business.servlet.NMAL3200.common.NMAL3200CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : TBLColumnSort
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        scrnMsg.xxSortTblNm.setValue(sortTblNm);
        scrnMsg.xxSortItemNm.setValue(sortItemNm);
        scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
        NMAL3200CMsg bizMsg = new NMAL3200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        NMAL3200CMsg bizMsg = (NMAL3200CMsg) cMsg;

        if (scrnMsg.A.getValidCount() != 0) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NMAL3200CommonLogic.setAttr(this, scrnMsg);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
