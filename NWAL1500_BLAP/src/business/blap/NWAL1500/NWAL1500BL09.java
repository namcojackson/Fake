/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import static business.blap.NWAL1500.constant.NWAL1500Constant.PDF;
import static business.blap.NWAL1500.constant.NWAL1500Constant.UNDER_LINE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0788E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NWAL1500.common.NWAL1500CommonLogicForReport;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_RPT_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/11   Fujitsu         T.Aoi           Create          S21_NA#22139
 * </pre>
 */
public class NWAL1500BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

            if ("NWAL1500_NWAL1790".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1790(bizMsg);
            } else if ("NWAL1500_NWAL2420".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL2420(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (NWAL1500_NWAL1790)
     * @param bizMsg NWAL1500CMsg
     */
    private void doProcess_NWAL1500_NWAL1790(NWAL1500CMsg bizMsg) {

        reportPrint(bizMsg, SPLY_QUOTE_RPT_TP.ORDER_CONF);
    }

    /**
     * do Process (NWAL1500_NWAL2420)
     * @param bizMsg NWAL1500CMsg
     */
    private void doProcess_NWAL1500_NWAL2420(NWAL1500CMsg bizMsg) {

        reportPrint(bizMsg, SPLY_QUOTE_RPT_TP.RETURN_AUTH);
    }

    /**
     * Report Print Process
     * @param bizMsg NWAL1500CMsg
     * @param splyQuoteRptTpCd String
     */
    private void reportPrint(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd) {

        byte[] reportData = NWAL1500CommonLogicForReport.executeEipProcessForPrint(bizMsg, splyQuoteRptTpCd);
        if (reportData == null) {
            bizMsg.setMessageInfo(NWAM0788E);
            return;
        }

        StringBuilder fileName = new StringBuilder();
        fileName.append(NWAL1500CommonLogicForReport.getReportFileName(bizMsg, splyQuoteRptTpCd, false));
        fileName.append(UNDER_LINE);
        fileName.append(String.valueOf(System.currentTimeMillis()));

        bizMsg.xxFileData.setTempFilePath(null, fileName.toString(), PDF);
        ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), reportData);
    }
}