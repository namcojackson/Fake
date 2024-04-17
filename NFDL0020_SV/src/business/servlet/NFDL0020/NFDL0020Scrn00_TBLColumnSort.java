/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

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
 * 2016/07/19   Hitachi         K.Kojima        Create          QC#11478
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/05/21   CITS            S.Katsuma       Update          QC#24793
 *</pre>
 */
public class NFDL0020Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //if (sortTblNm.equals("B") && scrnMsg.B.getValidCount() > 0) {
        if (sortTblNm.equals("A") && scrnMsg.A.getValidCount() > 0
                // START 2018/03/15 J.Kim [QC#20945,ADD]
                || sortTblNm.equals("H") && scrnMsg.H.getValidCount() > 0
                // END 2018/03/15 J.Kim [QC#20945,ADD]
                // START 2018/05/16 [QC#24329,ADD]
                || sortTblNm.equals("J") && scrnMsg.J.getValidCount() > 0
                // END 2018/05/16 [QC#24329,ADD]
                || sortTblNm.equals("B") && scrnMsg.B.getValidCount() > 0
            // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
                // START 2018/05/21 S.Katsuma [QC#24793,ADD]
                || sortTblNm.equals("F") && scrnMsg.F.getValidCount() > 0
                || sortTblNm.equals("G") && scrnMsg.G.getValidCount() > 0
                || sortTblNm.equals("C") && scrnMsg.C.getValidCount() > 0) {
                // END 2018/05/21 S.Katsuma [QC#24793,ADD]
            String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
            String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, sortTblNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, sortItemNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

            NFDL0020CMsg bizMsg = new NFDL0020CMsg();
            bizMsg.setBusinessID("NFDL0020");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
        if (scrnMsg.xxSortTblNm.getValue().equals("A")) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
        // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
        if (scrnMsg.xxSortTblNm.getValue().equals("B")) {
            NFDL0020CommonLogic.setInputProtected_B(scrnMsg);
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        }
        // START 2018/03/15 J.Kim [QC#20945,ADD]
        if (scrnMsg.xxSortTblNm.getValue().equals("H")) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.H.no(0).getBaseContents());
        }
        // END 2018/03/15 J.Kim [QC#20945,ADD]
        // START 2018/05/16 [QC#24329,ADD]
        if (scrnMsg.xxSortTblNm.getValue().equals("J")) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.J.no(0).getBaseContents());
            NFDL0020CommonLogic.initializeStatementTab(scrnMsg);
        }
        // END 2018/05/16 [QC#24329,ADD]
        // START 2018/05/21 S.Katsuma [QC#24793,ADD]
        if (scrnMsg.xxSortTblNm.getValue().equals("F")) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.F.no(0).getBaseContents());
        }
        if (scrnMsg.xxSortTblNm.getValue().equals("G")) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.G.no(0).getBaseContents());
        }
        if (scrnMsg.xxSortTblNm.getValue().equals("C")) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.C.no(0).getBaseContents());
        }
        // END 2018/05/21 S.Katsuma [QC#24793,ADD]
    }
}
