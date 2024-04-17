/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0290;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0290.NSBL0290CMsg;
import business.servlet.NSBL0290.common.NSBL0290CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 * 2016/06/02   Hitachi         K.Kasai         Update          QC#9379
 *</pre>
 */
public class NSBL0290Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {
            Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
            String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
            String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
            String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

            setValue(scrnMsg.xxSortTblNm, sortTblNm);
            setValue(scrnMsg.xxSortItemNm, sortItemNm);
            setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

            NSBL0290CMsg bizMsg = NSBL0290CommonLogic.setRequestData_SearchCommon();

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;
        NSBL0290CMsg bizMsg = (NSBL0290CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
        //add start 2016/06/02 CSA Defect#9379
        NSBL0290CommonLogic.checkBoxControl(scrnMsg);
        //add end 2016/06/02 CSA Defect#9379
    }
}
