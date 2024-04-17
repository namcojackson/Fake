/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0100.NFDL0100CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NFDL0100Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        // Detail None Check
        if (scrnMsg.A.getValidCount() <= 0) {
            scrnMsg.setMessageInfo("NFCM0095E");
            throw new EZDFieldErrorException();
        } else {
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100CMsg bizMsg = new NFDL0100CMsg();
        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;

        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        }
    }
}
