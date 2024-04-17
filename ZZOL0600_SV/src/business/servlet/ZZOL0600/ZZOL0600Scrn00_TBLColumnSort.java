/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0600.ZZOL0600CMsg;
import business.servlet.ZZOL0600.common.ZZOL0600CommonLogic;
import business.servlet.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZOL0600Scrn00_TBLColumnSort extends S21CommonHandler implements ZZOL0600Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if ("xxDtTm_FR".equals(sortItemNm)) {
            sortItemNm = "ezInTime_TR";
        }

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                ZZOL0600CMsg bizMsg = new ZZOL0600CMsg();
                bizMsg.setBusinessID("ZZOL0600");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(ZZOL0600Constant.pageID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZOL0600Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        ZZOL0600CommonLogic.convertTimeToDisplay(scrnMsg, bizMsg);
        scrnMsg.A.setInputProtected(true);

    }

}
