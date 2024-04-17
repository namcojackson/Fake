/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZTL0000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.ZZTL0000.ZZTL0000CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZZTL0000Scrn00_EIP_Validation extends S21CommonHandler {
    
    /** BussinessID */
    private static String bussinessId = "ZZTL0000";

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;
        
        // TODO アクセスしてきたユーザの権限チェックを行うこと
        //this.checkBusinessAppGranted(getContextUserInfo().getUserId(), bussinessId);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;

        ZZTL0000CMsg bizMsg = new ZZTL0000CMsg();
        bizMsg.setBusinessID("ZZTL0000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

//        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;
        ZZTL0000CMsg bizMsg  = (ZZTL0000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
//        bizMsg = (ZZTL0000CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, "ZZTL0000", "20");
        
        if (!"E".equals(bizMsg.getMessageKind())) {
        // execute file download
         String tempFilePath = bizMsg.xxFileData.getTempFilePath();
         executeDownloadPdf(tempFilePath, true);
         }

    }
}
