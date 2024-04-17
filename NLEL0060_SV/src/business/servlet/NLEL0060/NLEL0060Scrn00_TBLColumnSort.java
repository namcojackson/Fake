/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/07   Hitachi         J.Kim           Create          QC#5521
 *</pre>
 */
public class NLEL0060Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = null;
        boolean sort = false;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NLEL0060Bean.A.equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                sort = true;
            }
        } else if (NLEL0060Bean.B.equals(sortTblNm)) {
            if (scrnMsg.B.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                sort = true;
            }
        } else if (NLEL0060Bean.C.equals(sortTblNm)) {
            if (scrnMsg.C.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                sort = true;
            }
        } else if (NLEL0060Bean.D.equals(sortTblNm)) {
            if (scrnMsg.D.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                sort = true;
            }
        } else if (NLEL0060Bean.E.equals(sortTblNm)) {
            if (scrnMsg.E.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                sort = true;
            }
        } else if (NLEL0060Bean.F.equals(sortTblNm)) {
            if (scrnMsg.F.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
                sort = true;
            }
        }

        if (sort) {
            bizMsg = new NLEL0060CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_SRCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String sortTblNm = scrnMsg.xxSortTblNm.getValue();
        // Set sort icons.
        if (NLEL0060Bean.A.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        } else if (NLEL0060Bean.B.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        } else if (NLEL0060Bean.C.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.C.no(0).getBaseContents());
        } else if (NLEL0060Bean.D.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.D.no(0).getBaseContents());
        } else if (NLEL0060Bean.E.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.E.no(0).getBaseContents());
        } else if (NLEL0060Bean.F.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.F.no(0).getBaseContents());
        }

        NLEL0060CommonLogic.ctrlCmnSubBtnProp(this, scrnMsg);
    }
}
