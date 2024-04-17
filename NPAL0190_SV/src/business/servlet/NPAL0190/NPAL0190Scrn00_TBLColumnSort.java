/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0190;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL0190.NPAL0190CMsg;
import business.servlet.NPAL0190.common.NPAL0190CommonLogic;
import business.servlet.NPAL0190.constant.NPAL0190Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2012   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 * 01/25/2016   CITS            K.Ogino         Update          CSA
 *</pre>
 */
public class NPAL0190Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL0190BMsg scrnMsg = (NPAL0190BMsg) bMsg;

        if (scrnMsg.A.getValidCount() <= 0) {
            return null;
        }

        NPAL0190CMsg bizMsg = new NPAL0190CMsg();

        NPAL0190CommonLogic.setTableColumnSortParameters(ctx, scrnMsg);

        bizMsg.setBusinessID(NPAL0190Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NPAL0190Constant.FUNCTION_CODE_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL0190BMsg scrnMsg = (NPAL0190BMsg) bMsg;
        NPAL0190CMsg bizMsg = (NPAL0190CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NPAL0190CommonLogic.setInputProtected(scrnMsg);
        NPAL0190CommonLogic.formatTs(scrnMsg);
    }

}
