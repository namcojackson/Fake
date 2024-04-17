/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550;

import static business.servlet.NSAL0550.constant.NSAL0550Constant.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
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
 * 2015/12/24   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/22   Hitachi         T.Kanasaka      Update          QC#5562
 * 2016/04/11   Hitachi         Y.Takeno        Update          QC#6032
 * 2016/10/11   Hitachi         Y.Zhang         Update          QC#14740
 *</pre>
 */
public class NSAL0550Scrn00_ViewPaperInvoice extends S21CommonHandler {

    // START 2016/03/22 T.Kanasaka [QC#5562, MOD]
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRowNum_H1)) {
            scrnMsg.setMessageInfo(NSAM0034E);
            // START 2016/10/11 Y.Zhang [QC#14740, MOD]
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(scrnMsg.xxRowNum_H1.getValueInt()).invFtpRqstNum_A1)) {
            // END 2016/10/11 Y.Zhang [QC#14740, MOD]
            scrnMsg.setMessageInfo(NSAM0207E, new String[] {"Paper Invoice" });
        }

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;

        try {
            int index = scrnMsg.xxRowNum_H1.getValueInt();
            // START 2016/10/11 Y.Zhang [QC#14740, MOD]
            BigDecimal eipRptRqstPk = scrnMsg.A.no(index).invFtpRqstNum_A1.getValue();
            // END 2016/10/11 Y.Zhang [QC#14740, MOD]
            // Initialize EIP Printing Service Class
            S21EIPPrintingService eipService = new S21EIPPrintingService();

            // Get Archived Report byte array, input parameter is 'Report Request PK'(Mandatory)
            byte[] reportData = eipService.getArchivedReport(eipRptRqstPk.longValue());
            if (reportData != null) {
                // START 2016/04/11 Y.Takeno [QC#6032, MOD]
                String fileName = String.valueOf(System.currentTimeMillis());
                scrnMsg.xxFileData.setTempFilePath(null, fileName, ".pdf");
                // END   2016/04/11 Y.Takeno [QC#6032, MOD]
                ZYPFileWriter.writeFile(scrnMsg.xxFileData.getTempFilePath(), reportData);

            } else {
                throw new S21AbendException(ABEND_MSG_FAILED_GET_REPORT);
            }

            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
        } catch (S21AbendException e) {
            scrnMsg.setMessageInfo(ZZXM0001E, new String[] {e.getMessage()});
        }

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
    // END 2016/03/22 T.Kanasaka [QC#5562, MOD]
}
