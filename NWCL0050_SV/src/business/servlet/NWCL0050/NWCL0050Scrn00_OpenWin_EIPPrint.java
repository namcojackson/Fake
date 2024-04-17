/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import java.math.BigDecimal;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Fujitsu         H.Nagashima     Update          N/A
 * 2024/03/07   CITS            S.Kim           Update          QC#63675
 *</pre>
 */
public class NWCL0050Scrn00_OpenWin_EIPPrint extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        // QC#63675 2024/03/07 Start
        int idx = 0;
        // QC#63675 2024/03/07 End
        byte[] reportData = null;
        try {
            // QC#63675 2024/03/07 Start
            // int idx = getButtonSelectNumber();
            idx = getButtonSelectNumber();
            // QC#63675 2024/03/07 End
            BigDecimal eipRptRqstPk = scrnMsg.A.no(idx).eipRptRqstPk_A.getValue();
            
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
            // QC#63675 2024/03/07 Start
            String invoiceNumber = scrnMsg.A.no(idx).invNum_A.getValue();
            // execute file download
            //String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            //executeDownloadPdf(tempFilePath, true);
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownload(tempFilePath, true, invoiceNumber + ".pdf");
            // QC#63675 2024/03/07 End
        }

    }
}
