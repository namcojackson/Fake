/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0090.NFDL0090CMsg;
import business.servlet.NFDL0090.common.NFDL0090CommonLogic;

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
 * 2018/07/17   Hitachi         Y.Takeno        Create          QC#26989
 * 2019/08/26   Fujitsu         T.Murai         Update          QC#52973
 *</pre>
 */
public class NFDL0090Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, sortTblNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, sortItemNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

        NFDL0090CMsg bizMsg = new NFDL0090CMsg();
        bizMsg.setBusinessID("NFDL0090");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg  = (NFDL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // ADD START 2019/08/26 QC#52973
        NFDL0090CommonLogic.protectHeader(scrnMsg);
        NFDL0090CommonLogic.protectDetail(scrnMsg);
        // ADD END 2019/08/26 QC#52973
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
