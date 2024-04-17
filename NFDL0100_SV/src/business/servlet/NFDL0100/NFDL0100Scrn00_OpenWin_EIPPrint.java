/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

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
 * 2023/02/03   Hitachi         E.Watabe        Create          QC#61091
 *</pre>
 */
public class NFDL0100Scrn00_OpenWin_EIPPrint extends S21CommonHandler {

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

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;

        byte[] reportData = null;
        try {
            int idx = getButtonSelectNumber();
            BigDecimal eipRptRqstPk = scrnMsg.A.no(idx).eipRptRqstPk_AI.getValue();
            
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
                throw new S21AbendException("get report bytes failure");
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
