/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2610.NFCL2610CMsg;
import business.servlet.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/11   Hitachi         Y.Takeno        Create          QC#26989
 * 2021/09/09   CITS            G.Delgado       Update          QC#58793
 *</pre>
 */
public class NFCL2610Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);

        if ("A".equals(sortTblNm) && scrnMsg.A.getValidCount() > 0) {
            String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
            String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, sortTblNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, sortItemNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

            NFCL2610CMsg bizMsg = NFCL2610CommonLogic.setRequestData_SearchCommon();
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        NFCL2610CMsg bizMsg  = (NFCL2610CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        Parameters param = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);

        // Table : A
        if ("A".equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
            // START 2021/09/09 G.Delgado [QC#58793, ADD]
            NFCL2610CommonLogic.setEnabledRowsTblA(this, scrnMsg);
            // END 2021/09/09 G.Delgado [QC#58793, ADD]
        }

        // Table : B
        if ("B".equals(sortTblNm)) {
            String sortItemNm  = param.getSingleValue( S21TableColumnSortConstant.SORT_ITEM_NAME );
            String sortOrderBy = param.getSingleValue( S21TableColumnSortConstant.ORDER_BY );

            S21SortTarget sortTarget = new S21SortTarget(scrnMsg.B, scrnMsg.B.no(0).getBaseContents() );
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrderBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, scrnMsg.B.getValidCount());

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        }
    }
}
