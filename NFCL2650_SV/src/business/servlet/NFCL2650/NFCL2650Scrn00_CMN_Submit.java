/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650;

import static business.servlet.NFCL2650.constant.NFCL2650Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2650.NFCL2650CMsg;
import business.servlet.NFCL2650.common.NFCL2650CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 * 2018/05/15   Fujitsu         Y.Matsui        Update          QC#24329
 *</pre>
 */
public class NFCL2650Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        NFCL2650CommonLogic.submitCheck(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;

        NFCL2650CMsg bizMsg = new NFCL2650CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        NFCL2650CMsg bizMsg = (NFCL2650CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_FR);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_TO);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_F2);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_T2);
        scrnMsg.addCheckItem(scrnMsg.locNum); // MOD 2016/11/25 T.Murai [QC#13259] billToCustCd -> locNum

        scrnMsg.putErrorScreen();

        // START 2018/05/15 [QC#24329,ADD]
        bizMsg = (NFCL2650CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_ID, "70");
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        String tempFilePath = bizMsg.xxFileData.getTempFilePath();
        executeDownloadPdf(tempFilePath, true);
        // END   2018/05/15 [QC#24329,ADD]
    }
}
