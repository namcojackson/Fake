/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2120;

import static business.servlet.NFBL2120.constant.NFBL2120Constant.BIZ_ID;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.EVENT_NM_TBL_SORT;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2120.NFBL2120CMsg;
import business.servlet.NFBL2120.common.NFBL2120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NFBL2120Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0) {
            return null;
        }

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        scrnMsg.xxSortTblNm.setValue(sortTblNm);
        scrnMsg.xxSortItemNm.setValue(sortItemNm);
        scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

        NFBL2120CMsg bizMsg = new NFBL2120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;
        NFBL2120CMsg bizMsg  = (NFBL2120CMsg) cMsg;

        if (scrnMsg.A.getValidCount() != 0) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NFBL2120CommonLogic.controlScreenFields(this, scrnMsg);
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVENT_NM_TBL_SORT);
    }
}
