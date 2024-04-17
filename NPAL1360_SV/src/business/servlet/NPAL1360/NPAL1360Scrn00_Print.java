/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.BIZ_APP_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.FUNCTION_CD_PRINT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1360.NPAL1360CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Print Report
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 * 11/07/2017   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 *</pre>
 */
public class NPAL1360Scrn00_Print extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        NPAL1360CMsg bizMsg = new NPAL1360CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_PRINT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        NPAL1360CMsg bizMsg  = (NPAL1360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]
//        bizMsg = (NPAL1360CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_APP_ID, FUNCTION_CD_PRINT);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]

        if (scrnMsg.wrkOrdNum.isError()) {
            scrnMsg.addCheckItem(scrnMsg.wrkOrdNum);
            scrnMsg.putErrorScreen();
            return;
        }

        if (!"E".equals(bizMsg.getMessageKind())) {
            String tempFilePath = bizMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
        }

        scrnMsg.putErrorScreen();

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);

    }
}
