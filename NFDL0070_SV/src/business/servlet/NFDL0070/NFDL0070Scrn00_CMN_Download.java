/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0070.NFDL0070CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFDL0070Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;

        NFDL0070CMsg bizMsg = new NFDL0070CMsg();
        bizMsg.setBusinessID("NFDL0070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
    }
}
