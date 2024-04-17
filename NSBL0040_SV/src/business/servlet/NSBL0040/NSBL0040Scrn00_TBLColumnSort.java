/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0040.NSBL0040CMsg;
import business.servlet.NSBL0040.common.NSBL0040CommonLogic;
import business.servlet.NSBL0040.constant.NSBL0040Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 *</pre>
 */
public class NSBL0040Scrn00_TBLColumnSort extends S21CommonHandler implements NSBL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {

            NSBL0040CMsg bizMsg = new NSBL0040CMsg();

            NSBL0040CommonLogic.setTableColumnSortParameters(ctx, scrnMsg);

            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(SEARCH_FUNCTION);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
        NSBL0040CMsg bizMsg = (NSBL0040CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSBL0040CommonLogic.alternateTableRowColors(scrnMsg);
    }
}
