/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_PRNT;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_UPD;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.ZZZM9003I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/16   CITS            T.Tokutomi      Create          QC#18367
 * 
 *</pre>
 */
public class NLBL2020Scrn00_CustomDocPrint extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {

            NLBL2020CommonLogic.addCheckTableItem(scrnMsg);
            scrnMsg.putErrorScreen();

        } else {

            bizMsg = (NLBL2020CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BUSINESS_ID, FUNC_PRNT);

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
                // execute file download
                String tempFilePath = bizMsg.xxFileData.getTempFilePath();
                executeDownloadPdf(tempFilePath, true);

                scrnMsg.setMessageInfo(ZZZM9003I, new String[] {"Print" });
                scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
            }
        }
    }
}
