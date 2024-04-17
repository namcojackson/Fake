/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0860;

import static business.servlet.NFCL0860.constant.NFCL0860Constant.BIZ_ID;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0860.NFCL0860CMsg;
import business.servlet.NFCL0860.common.NFCL0860CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * NFCL0860Scrn00_TBLColumnSort
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public class NFCL0860Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;
        NFCL0860CMsg bizMsg  = null;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NFCL0860Bean.A.equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                bizMsg = new NFCL0860CMsg();
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode(FUNC_CD_SRCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
            }
        }
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;
        NFCL0860CMsg bizMsg  = (NFCL0860CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //---- start 2016/05/16
        NFCL0860CommonLogic.setGuiAttr(this, scrnMsg);
        //---- end 2016/05/16
        
        String sortTblNm = scrnMsg.xxSortTblNm.getValue();
        // Set sort icons.
        if (NFCL0860Bean.A.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
        
       
    }
}
