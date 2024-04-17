/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3020.NFCL3020CMsg;
//import business.servlet.NFCL3020.constant.NFCL3020Constant;

import business.blap.NFCL3020.NFCL3020CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/22   Fujitsu         H.Ikeda         Create          QC#21737
 *</pre>
 */
public class NFCL3020Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

        NFCL3020CMsg bizMsg = new NFCL3020CMsg();
        bizMsg.setBusinessID("NFCL3020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        NFCL3020CMsg bizMsg  = (NFCL3020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
    }
}
