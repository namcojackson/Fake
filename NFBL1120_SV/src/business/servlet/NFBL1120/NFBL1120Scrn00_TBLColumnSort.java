/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL1120.NFBL1120CMsg;
import business.servlet.NFBL1120.constant.NFBL1120Constant;
import business.servlet.NFBL1120.NFBL1120Bean;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/21   Hitachi         J.Kim           Update          QC#5521
 *</pre>
 */
public class NFBL1120Scrn00_TBLColumnSort extends S21CommonHandler implements NFBL1120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        NFBL1120CMsg bizMsg = null;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NFBL1120Bean.A.equals(sortTblNm)) {

            scrnMsg.xxSortTblNm.setValue(sortTblNm);
            scrnMsg.xxSortItemNm.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

            bizMsg = new NFBL1120CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_20);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String sortTblNm = scrnMsg.xxSortTblNm.getValue();
        // Set sort icons.
        if (NFBL1120Bean.A.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
    }
}
