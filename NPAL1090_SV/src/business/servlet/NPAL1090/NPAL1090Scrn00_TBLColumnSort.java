/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NPAL1090 Service Parts Request Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/11   CSAI            K.Lee           Create          N/A
 * 2015/03/17   Fujitsu         T.Nishikawa     Update          CSA
 *</pre>
 */
public class NPAL1090Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        //
        // // Parameters for Table Column Sort.
        // Parameters param = ((HttpDispatchContext)
        // ctx.getDispatchContext()).getParameters();
        // String sortTblNm =
        // param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        // String sortItemNm =
        // param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        // String sortOrderBy =
        // param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        //
        // if ("A".equals(sortTblNm) && scrnMsg.A.getValidCount() > 0)
        // {
        //
        // scrnMsg.xxSortTblNm.setValue(sortTblNm);
        // scrnMsg.xxSortItemNm.setValue(sortItemNm);
        // scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);
        //
        // NPAL1090CMsg bizMsg = new NPAL1090CMsg();
        // bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;
        // }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;
        //
        // if (bizMsg != null) {
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // // Start Mod 2015/03/17 CSA T.Nishikawa
        //
        // // //Set Link Visibility
        // // NPAL1090CommonLogic.setLinkVisiblity(scrnMsg, bizMsg);
        // //
        // // //Set focus.
        // // scrnMsg.setFocusItem(scrnMsg.A.no(0).toInvtyLocCd_D1);
        // //
        // // //control image file of sort column (Gif file. ASC or
        // DESC.)
        // // S21SortColumnIMGController.controlIMG(ctx, scrnMsg,
        // scrnMsg.A.no(0).getBaseContents());
        //            
        // // control image file of sort column (Gif file. ASC or
        // DESC.)
        // S21SortColumnIMGController.controlIMG(ctx, scrnMsg,
        // scrnMsg.A.no(0).getBaseContents());
        // NPAL1090CommonLogic.resetTabFocusRule(scrnMsg);
        // End Mod 2015/03/17 CSA T.Nishikawa
        // }
    }
}
