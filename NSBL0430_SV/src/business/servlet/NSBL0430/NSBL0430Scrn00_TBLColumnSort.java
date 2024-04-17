/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0430;

import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.addCheckItemDtl;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.setFocus;
import static business.servlet.NSBL0430.constant.NSBL0430Constant.BIZ_ID;
import static business.servlet.NSBL0430.constant.NSBL0430Constant.EZD_FUNC_CD_INQ;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0430.NSBL0430CMsg;
import business.servlet.NSBL0430.common.NSBL0430CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11685
 *</pre>
 */
public class NSBL0430Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
   }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;

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

                NSBL0430CMsg bizMsg = new NSBL0430CMsg();
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

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;
        NSBL0430CMsg bizMsg  = (NSBL0430CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            addCheckItemDtl(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        NSBL0430CommonLogic.controlScreenFields(scrnMsg);
        setFocus(scrnMsg);

        // control image file of sort column
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
