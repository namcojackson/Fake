/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0280;

import static business.servlet.NSBL0280.constant.NSBL0280Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0280.NSBL0280CMsg;
import business.servlet.NSBL0280.common.NSBL0280CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0280Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;

        int validCount = 0;
        if (SKILL_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            validCount = scrnMsg.B.getValidCount();
        } else {
            validCount = scrnMsg.A.getValidCount();
        }

        if (validCount > 0) {
            Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
            String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
            String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
            String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

            setValue(scrnMsg.xxSortTblNm, sortTblNm);
            setValue(scrnMsg.xxSortItemNm, sortItemNm);
            setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

            NSBL0280CMsg bizMsg = NSBL0280CommonLogic.setRequestData_SearchCommon();

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;
        NSBL0280CMsg bizMsg = (NSBL0280CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (SKILL_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
                S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
            } else {
                S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
            }
        }
    }
}
