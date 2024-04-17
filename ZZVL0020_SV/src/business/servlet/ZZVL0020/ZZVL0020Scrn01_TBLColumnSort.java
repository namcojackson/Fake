/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZVL0020.ZZVL0020CMsg;
import business.servlet.ZZVL0020.constant.ZZVL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020Scrn01_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm_A1, sortTblNm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm_A1, sortItemNm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt_A1, sortOrderBy);

                ZZVL0020CMsg bizMsg = new ZZVL0020CMsg();
                bizMsg.setBusinessID("ZZVL0020");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        // Table:B
        if ("B".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(scrnMsg.B, scrnMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrderBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, scrnMsg.B.getValidCount());

            // control image file of sort column (Gif file. ASC or DESC.)
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());

            S21TableColorController tblColor = new S21TableColorController(ZZVL0020Constant.SCREEN_NAME1, scrnMsg);
            tblColor.setAlternateRowsBG("B", scrnMsg.B);


            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                String xxErrFlg = scrnMsg.B.no(i).xxErrFlg_B1.getValue();
                if (xxErrFlg.equals("1")) {
                    tblColor.setRowStyle("B", i, "pErr");
                }
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;
        ZZVL0020CMsg bizMsg  = (ZZVL0020CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }
}
