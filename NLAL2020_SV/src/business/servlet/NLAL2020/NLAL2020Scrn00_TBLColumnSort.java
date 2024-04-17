/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * NLAL2020Scrn00_TBLColumnSort
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2020Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg  = null;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NLAL2020Bean.A.equals(sortTblNm)) {

            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                bizMsg = new NLAL2020CMsg();
                bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
            }
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg  = (NLAL2020CMsg) cMsg;

        if (bizMsg == null) {

            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL2020CommonLogic.initCmnBtnProp(this);
        NLAL2020CommonLogic.controlScreenFields(this, scrnMsg);
        NLAL2020CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NLAL2020Bean.A);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        String sortTblNm = scrnMsg.xxSortTblNm.getValue();

        // Set sort icons.
        if (NLAL2020Bean.A.equals(sortTblNm)) {

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
    }
}
