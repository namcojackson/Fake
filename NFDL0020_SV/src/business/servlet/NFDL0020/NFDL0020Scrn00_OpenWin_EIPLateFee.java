/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

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
 * 2018/06/05   Hitachi         Y.Takeno        Create          QC#26107
 *</pre>
 */
public class NFDL0020Scrn00_OpenWin_EIPLateFee extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        byte[] reportData = null;
        try {
            int idx = getButtonSelectNumber();
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
                scrnMsg.setMessageInfo("NFDM0008E", new String[] {"EIP Report"});
            }

        } catch (S21AbendException e) {
            scrnMsg.setMessageInfo("ZZXM0001E", new String[] {e.getMessage()});
            
        }

        if (reportData != null) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
        }
    }
}
