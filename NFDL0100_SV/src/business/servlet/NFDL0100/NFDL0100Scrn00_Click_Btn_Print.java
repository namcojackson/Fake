/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0100.NFDL0100CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;


/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/04/01   Fujitsu         H.Mizukami      Create          QC#56235
 *</pre>
 */
public class NFDL0100Scrn00_Click_Btn_Print extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;

        NFDL0100CMsg bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR || scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
            scrnMsg.A.setCheckParam(new String[] {NFDL0100Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxDplyCtrlFlg_EM.clear();

        S21EIPPrintingService eipService = new S21EIPPrintingService();
        byte[] report = null;
        try {
            List<Long> rptRequestList = new ArrayList<Long>();

            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    BigDecimal rqstNum = bizMsg.B.no(i).eipRptRqstPk_B.getValue();
                    if (rqstNum == null) {
                        scrnMsg.setMessageInfo("NWCM0107E");
                    }
                    rptRequestList.add(rqstNum.longValue());
            }

            if(rptRequestList != null && rptRequestList.size() > 0) {
                report = eipService.getArchivedReport(rptRequestList);
                if (report == null) {
                    scrnMsg.setMessageInfo("NWCM0107E");
                }
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                scrnMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(scrnMsg.xxFileData.getTempFilePath(), report);
            }

        } catch (S21AbendException e) {
            scrnMsg.setMessageInfo("ZZXM0001E", new String[] {e.getMessage()});
            
        }

        if (report != null) {
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
        }
    }
}
