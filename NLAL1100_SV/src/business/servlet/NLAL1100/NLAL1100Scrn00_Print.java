/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLAL1100;

import static business.servlet.NLAL1100.constant.NLAL1100Constant.BIZ_APP_ID;
import static business.servlet.NLAL1100.constant.NLAL1100Constant.FUNCTION_CD_PRINT;
import static business.servlet.NLAL1100.constant.NLAL1100Constant.FUNCTION_CD_UPDATE;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL1100.NLAL1100CMsg;
import business.servlet.NLAL1100.common.NLAL1100CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/22/2016   CITS            Y.Fujii          Create          R362
 *</pre>
 */
public class NLAL1100Scrn00_Print extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        NLAL1100CMsg bizMsg = new NLAL1100CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
        NLAL1100CMsg bizMsg = (NLAL1100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            NLAL1100CommonLogic.addCheckItemDetail(scrnMsg);
        } else {
            bizMsg = (NLAL1100CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_APP_ID, FUNCTION_CD_PRINT);

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            if (!"E".equals(bizMsg.getMessageKind())) {
                // execute file download
                String tempFilePath = bizMsg.xxFileData.getTempFilePath();
                executeDownloadPdf(tempFilePath, true);
            }
        }

        NLAL1100CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.rmaNum_H1);
    }
}
