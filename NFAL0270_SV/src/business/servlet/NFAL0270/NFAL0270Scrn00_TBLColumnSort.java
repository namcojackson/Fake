/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0270.NFAL0270CMsg;
import static business.servlet.NFAL0270.constant.NFAL0270Constant.*;
import static business.servlet.NFAL0270.common.NFAL0270CommonLogic.*;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;

        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm_B1.setValue(sortTblNm);
                scrnMsg.xxSortItemNm_B1.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt_B1.setValue(sortOrderBy);

                NFAL0270CMsg bizMsg = new NFAL0270CMsg();
                bizMsg.setBusinessID(BUSINESS_APPL_ID);
                bizMsg.setFunctionCode(FUNC_SRCH);

                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
        NFAL0270CMsg bizMsg  = (NFAL0270CMsg) cMsg;

        String userID = ""; 
        if (bizMsg != null) {
           EZDMsg.copy(bizMsg, null, scrnMsg, null);
           userID = bizMsg.getUserID();
        } else {
           userID = getContextUserInfo().getUserId();
        }

        // Screen Protect Ctrl
        initialControlScreen(this, scrnMsg, userID);
        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        //scrnMsg.putErrorScreen();
    }
}
