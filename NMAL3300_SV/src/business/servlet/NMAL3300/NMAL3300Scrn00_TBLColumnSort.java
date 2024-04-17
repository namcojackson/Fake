/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL3300.NMAL3300CMsg;
import business.servlet.NMAL3300.common.NMAL3300CommonLogic;

import static business.servlet.NMAL3300.constant.NMAL3300Constant.BUSINESS_ID;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.FUNCTION_CODE_SEARCH;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.TABLE_D;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public class NMAL3300Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;

        //NMAL3300CMsg bizMsg = new NMAL3300CMsg();
        //bizMsg.setBusinessID("NMAL3300");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        // Del Start 2018/11/12 QC#28683
        //return null;
        // Del End 2018/11/12 QC#28683

        // Add Start 2018/11/12 QC#28683
        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        NMAL3300CMsg bizMsg = null;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:D
        if (TABLE_D.equals(sortTblNm)) {
            if (scrnMsg.D.getValidCount() > 0) {
                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                bizMsg = new NMAL3300CMsg();
                bizMsg.setBusinessID(BUSINESS_ID);
                bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
            }
        }

        return bizMsg;
        // Add End 2018/11/12 QC#28683

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        // Add Start 2018/11/12 QC#28683
        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
        // Add End 2018/11/12 QC#28683

        // Mod Start 2018/11/12 QC#28683
        //Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        //String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        //String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        //
        //S21SortTarget sortTarget = new S21SortTarget(scrnMsg.A, scrnMsg.A.no(0).getBaseContents());
        //S21SortKey sortKey = new S21SortKey();
        //sortKey.add(sortItemNm, sortOrderBy);
        //S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, scrnMsg.A.getValidCount());
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.D.no(0).getBaseContents());
        // Mod End 2018/11/12 QC#28683

        NMAL3300CommonLogic.protectScrn(this, scrnMsg);
        NMAL3300CommonLogic.setBgColor(scrnMsg);

        scrnMsg.putErrorScreen();

    }
}
