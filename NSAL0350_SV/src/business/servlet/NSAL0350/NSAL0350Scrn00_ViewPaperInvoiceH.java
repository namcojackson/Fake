/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0350;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0350.NSAL0350CMsg;
//import business.servlet.NSAL0350.constant.NSAL0350Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/06   Hitachi         U.Kim           Create          QC#99999
 *</pre>
 */
public class NSAL0350Scrn00_ViewPaperInvoiceH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0350BMsg scrnMsg = (NSAL0350BMsg) bMsg;

        byte[] reportData = null;
        try {
            int idx = getButtonSelectNumber();
            BigDecimal eipRptRqstPk = scrnMsg.H.no(idx).eipRptRqstPk_H1.getValue();

            // Initialize EIP Printing Service Class
            S21EIPPrintingService eipService = new S21EIPPrintingService();
            reportData = eipService.getArchivedReport(eipRptRqstPk.longValue());

            if (reportData != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                scrnMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(scrnMsg.xxFileData.getTempFilePath(), reportData);
            } else {
                scrnMsg.setMessageInfo("NWCM0107E");
            }

        } catch (S21AbendException e) {
            scrnMsg.setMessageInfo("ZZXM0001E", new String[] {e.getMessage() });

        }

        if (reportData != null) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
        }
    }
}
