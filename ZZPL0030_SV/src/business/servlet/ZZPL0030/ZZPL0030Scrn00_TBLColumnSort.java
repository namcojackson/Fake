/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZPL0030.ZZPL0030CMsg;
import business.servlet.ZZPL0030.common.ZZPL0030CommonLogic;
import business.servlet.ZZPL0030.constant.ZZPL0030Constant;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm_0.setValue(sortTblNm);
                scrnMsg.xxSortItemNm_0.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt_0.setValue(sortOrderBy);

                ZZPL0030CMsg bizMsg = new ZZPL0030CMsg();
                bizMsg.setBusinessID(ZZPL0030Constant.BUSINESS_ID);
                bizMsg.setFunctionCode(ZZPL0030Constant.FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;
        ZZPL0030CMsg bizMsg = (ZZPL0030CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        scrnMsg.setFocusItem(scrnMsg.xxRptNm_0);

        // set table background color
        ZZPL0030CommonLogic.setTableRowColor(scrnMsg, ZZPL0030Constant.SCREENID_SCRN00);

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }
}
