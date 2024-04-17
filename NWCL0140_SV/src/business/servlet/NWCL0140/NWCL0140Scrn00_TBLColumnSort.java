/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.BIZ_APP_ID;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.TABLE_A;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.TABLE_B;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0140.NWCL0140CMsg;
import business.servlet.NWCL0140.common.NWCL0140CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : NWCL0140Scrn00_TBLColumnSort
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0140Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // TABLE A
        if (TABLE_A.equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                NWCL0140CMsg bizMsg = new NWCL0140CMsg();
                bizMsg.setBusinessID(BIZ_APP_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        } else if (TABLE_B.equals(sortTblNm)) {
            // TABLE B
            if (scrnMsg.B.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                NWCL0140CMsg bizMsg = new NWCL0140CMsg();
                bizMsg.setBusinessID(BIZ_APP_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        NWCL0140CMsg bizMsg = (NWCL0140CMsg) cMsg;

        if (scrnMsg.A.getValidCount() != 0) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NWCL0140CommonLogic.setTableColumnAttr(scrnMsg);
        if (TABLE_A.equals(scrnMsg.xxSortTblNm.getValue())) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        } else if (TABLE_B.equals(scrnMsg.xxSortTblNm.getValue())) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        }
    }
}
