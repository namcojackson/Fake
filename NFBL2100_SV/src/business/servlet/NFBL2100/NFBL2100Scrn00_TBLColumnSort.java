/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2100;

import static business.servlet.NFBL2100.constant.NFBL2100Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2100.NFBL2100CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Lease Buyout Approve List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFBL2100Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {
            Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
            String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
            String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
            String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

            setValue(scrnMsg.xxSortTblNm, sortTblNm);
            setValue(scrnMsg.xxSortItemNm, sortItemNm);
            setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

            NFBL2100CMsg bizMsg = new NFBL2100CMsg();
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;
        NFBL2100CMsg bizMsg = (NFBL2100CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
