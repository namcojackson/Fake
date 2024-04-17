/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;
import business.servlet.NMAL7010.constant.NMAL7010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   SRA             E.Inada         Create          QC#4582
 * 2017/02/24   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7010Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = new NMAL7010CMsg();

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (hasData(scrnMsg, sortTblNm)) {

            scrnMsg.xxSortTblNm.setValue(sortTblNm);
            scrnMsg.xxSortItemNm.setValue(sortItemNm);
            scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

            bizMsg.setBusinessID(NMAL7010Constant.BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Mod Start 2017/02/24 QC#16011
//        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/24 QC#16011

        String sortTblNm = scrnMsg.xxSortTblNm.getValue();

        if (NMAL7010Bean.A.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        } else if (NMAL7010Bean.B.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        } else if (NMAL7010Bean.C.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.C.no(0).getBaseContents());
        } else if (NMAL7010Bean.D.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.D.no(0).getBaseContents());
        } else if (NMAL7010Bean.E.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.E.no(0).getBaseContents());
        } else if (NMAL7010Bean.F.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.F.no(0).getBaseContents());
        } else if (NMAL7010Bean.G.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.G.no(0).getBaseContents());
        } else if (NMAL7010Bean.H.equals(sortTblNm)) {
            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.H.no(0).getBaseContents());
        }
    }

    private boolean hasData(NMAL7010BMsg scrnMsg, String sortTblNm) {
        if (NMAL7010Bean.A.equals(sortTblNm) && scrnMsg.A.getValidCount() > 0) {
            return true;
        } else if (NMAL7010Bean.B.equals(sortTblNm) && scrnMsg.B.getValidCount() > 0) {
            return true;
        } else if (NMAL7010Bean.C.equals(sortTblNm) && scrnMsg.C.getValidCount() > 0) {
            return true;
        } else if (NMAL7010Bean.D.equals(sortTblNm) && scrnMsg.D.getValidCount() > 0) {
            return true;
        } else if (NMAL7010Bean.E.equals(sortTblNm) && scrnMsg.E.getValidCount() > 0) {
            return true;
        } else if (NMAL7010Bean.F.equals(sortTblNm) && scrnMsg.F.getValidCount() > 0) {
            return true;
        } else if (NMAL7010Bean.G.equals(sortTblNm) && scrnMsg.G.getValidCount() > 0) {
            return true;
        } else if (NMAL7010Bean.H.equals(sortTblNm) && scrnMsg.H.getValidCount() > 0) {
            return true;
        }
        return false;
    }
}
