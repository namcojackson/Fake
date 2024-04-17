/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2590;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2590.NMAL2590CMsg;
import static business.servlet.NMAL2590.constant.NMAL2590Constant.*;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/10/07   Hitachi         T.Mizuki        Create          CSA-QC#4096
 *</pre>
 */
public class NMAL2590Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NZZM0000E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;

        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        scrnMsg.xxSortTblNm.setValue(sortTblNm);
        scrnMsg.xxSortItemNm.setValue(sortItemNm);
        scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

        NMAL2590CMsg bizMsg = new NMAL2590CMsg();
        bizMsg.setBusinessID("NMAL2590");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;
        NMAL2590CMsg bizMsg  = (NMAL2590CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set sort icons.
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
