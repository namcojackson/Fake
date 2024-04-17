/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.initialControlScreen_Add;
import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.setFocus;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0410.NSBL0410CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Hitachi         M.Gotou         Create          N/A
 * 2016/07/11   Hitachi         O.Okuma         Update          QC#11683
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 *</pre>
 */
public class NSBL0410Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                NSBL0410CMsg bizMsg = new NSBL0410CMsg();
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        NSBL0410CMsg bizMsg  = (NSBL0410CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // add start 2016/07/11 CSA Defect#11683
        initialControlScreen_Add(this, scrnMsg);
        // add end 2016/07/11 CSA Defect#11683

        // control image file of sort column
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        setFocus(scrnMsg);
    }
}
