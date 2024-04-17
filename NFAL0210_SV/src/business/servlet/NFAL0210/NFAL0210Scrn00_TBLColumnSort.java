/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0210;

import static business.servlet.NFAL0210.constant.NFAL0210Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0210.NFAL0210CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/14   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFAL0210Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFAL0210BMsg scrnMsg = (NFAL0210BMsg) bMsg;
        if (scrnMsg.A.getValidCount() > 0) {
            Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
            String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
            String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
            String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, sortTblNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, sortItemNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

            NFAL0210CMsg bizMsg = new NFAL0210CMsg();
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFAL0210BMsg scrnMsg = (NFAL0210BMsg) bMsg;
        NFAL0210CMsg bizMsg = (NFAL0210CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
