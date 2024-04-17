/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZVL0020.ZZVL0020CMsg;
import business.servlet.ZZVL0020.common.ZZVL0020CommonLogic;
//import business.servlet.ZZVL0020.constant.ZZVL0020Constant;

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
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020Scrn00_TBLColumnSort extends S21CommonHandler {

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

        // Table:C
        if ("C".equals(sortTblNm)) {
            if (scrnMsg.C.getValidCount() > 0) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm_1, sortTblNm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm_1, sortItemNm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt_1, sortOrderBy);

                ZZVL0020CMsg bizMsg = new ZZVL0020CMsg();
                bizMsg.setBusinessID("ZZVL0020");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
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
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.C.no(0).getBaseContents());

        ZZVL0020CommonLogic.dspScrn00(scrnMsg, this);

    }
}
