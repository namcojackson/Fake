/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0200;

import static business.servlet.NSBL0200.constant.NSBL0200Constant.BUSINESS_APPLICATION_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0200.NSBL0200CMsg;
import business.servlet.NSBL0200.common.NSBL0200CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 * 2013/08/30   WDS Team        K.Aratani          Update          QC1457
 * 2015/11/25   Hitachi         T.Harada           Update          CSA
 *</pre>
 */
public class NSBL0200Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {
            Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
            String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
            String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
            String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

            setValue(scrnMsg.xxSortTblNm, sortTblNm);
            setValue(scrnMsg.xxSortItemNm, sortItemNm);
            setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

            NSBL0200CMsg bizMsg = new NSBL0200CMsg();
            bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;
        NSBL0200CMsg bizMsg  = (NSBL0200CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }

        NSBL0200CommonLogic.setProtected(scrnMsg);
        //QC1457
        // START 2015/11/25 [CSA,CHANGE]
        //scrnMsg.setFocusItem(scrnMsg.orgLayerNum_TC);
        scrnMsg.setFocusItem(scrnMsg.fldSvcBrCd_SC);
        // END 2015/11/25 [CSA,CHANGE]
    }
}
