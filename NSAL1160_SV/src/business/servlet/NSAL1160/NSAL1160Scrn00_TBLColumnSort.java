/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1160;

import static business.servlet.NSAL1160.constant.NSAL1160Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1160.NSAL1160CMsg;
import business.servlet.NSAL1160.common.NSAL1160CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL1160Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        setValue(scrnMsg.xxSortTblNm, sortTblNm);
        setValue(scrnMsg.xxSortItemNm, sortItemNm);
        setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

        NSAL1160CMsg bizMsg = NSAL1160CommonLogic.setRequestData_SearchCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;
        NSAL1160CMsg bizMsg = (NSAL1160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (TABLE_A.equals(bizMsg.xxSortTblNm.getValue())) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        } else if (TABLE_B.equals(bizMsg.xxSortTblNm.getValue())) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        } else {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.C.no(0).getBaseContents());
        }
    }
}
