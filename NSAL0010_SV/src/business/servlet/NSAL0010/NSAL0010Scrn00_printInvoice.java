/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/04/07   Hitachi         K.Yamada        Update          CSA QC#6726
 *</pre>
 */
public class NSAL0010Scrn00_printInvoice extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // add start 2016/04/07 CSA Defect#6726
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        byte[] reportData = null;
        try {
            int idx = getButtonSelectNumber();
            BigDecimal eipRptRqstPk = scrnMsg.L.no(idx).eipRptRqstPk_L.getValue();

            // Initialize EIP Printing Service Class
            S21EIPPrintingService eipService = new S21EIPPrintingService();
            // Get Archived Report byte array, input parameter is 'Report Request PK'(Mandatory)
            reportData = eipService.getArchivedReport(eipRptRqstPk.longValue());

            if (reportData != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                scrnMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(scrnMsg.xxFileData.getTempFilePath(), reportData);
            } else {
                scrnMsg.setMessageInfo("NWCM0107E");
//                throw new S21AbendException("get report bytes failure");
            }

        } catch (S21AbendException e) {
            scrnMsg.setMessageInfo("ZZXM0001E", new String[] {e.getMessage()});
            
        }

        if (reportData != null) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
        }
        // add end 2016/04/07 CSA Defect#6726

    }
}
