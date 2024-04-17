/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import static business.blap.NWAL1770.constant.NWAL1770Constant.PDF;
import static business.blap.NWAL1770.constant.NWAL1770Constant.UNDER_LINE;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0788E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForReport;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         T.Yoshida       Create          N/A
 * </pre>
 */
public class NWAL1770BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;

            if ("NWAL1770_NWAL1790".equals(screenAplID)) {
                doProcess_NWAL1770_NWAL1790(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (NWAL1770_NWAL1790)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NWAL1790(NWAL1770CMsg bizMsg) {

        byte[] reportData = NWAL1770CommonLogicForReport.executeEipProcessForPrint(bizMsg);
        if (reportData == null) {
            bizMsg.setMessageInfo(NWAM0788E);
            return;
        }

        StringBuilder fileName = new StringBuilder();
        fileName.append(NWAL1770CommonLogicForReport.getReportFileName(bizMsg, false));
        fileName.append(UNDER_LINE);
        fileName.append(String.valueOf(System.currentTimeMillis()));

        bizMsg.xxFileData.setTempFilePath(null, fileName.toString(), PDF);
        ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), reportData);
    }
}
