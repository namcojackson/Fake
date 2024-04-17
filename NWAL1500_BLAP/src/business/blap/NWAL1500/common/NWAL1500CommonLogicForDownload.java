/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_FILE_NAME_FOR_INBOUND;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_FILE_NAME_FOR_OUTBOUND;
//import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_HEADER_FOR_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_HEADER_FOR_INBOUND;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_HEADER_FOR_OUTBOUND;
import static business.blap.NWAL1500.constant.NWAL1500Constant.EXTENSION_CSV;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_HEADER_FOR_ORDER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_HEADER_FOR_OUTBOUND_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DOWNLOAD_CSV_HEADER_FOR_INBOUND_HEADER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSStringItemArray;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForCustomer;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.file.NWAL1500F00FMsg;
import business.file.NWAL1500F01FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016   Fujitsu         T.ishii         Create          S21_NA#1623
 * 09/21/2017   Fujitsu         T.Murai         Update          S21_NA#16346(Sol#373)
 * 2018/03/06   Fujitsu         S.Takami        Update          S21_NS#19808 (bizMsg.A, B, C, D -> glblMsg.A, B, C, D without Comments)
 * 2018/05/22   Fujitsu         A.Kosai         Update          S21_NA#23523
 * 2024/03/08   CITS            T.Miki          Update          QC#63654
 * </pre>
 */
public class NWAL1500CommonLogicForDownload {

    /**
     * downloadForOutbound
     * @param bizMsg NWAL1500CMsg
     */
    public static void downloadForOutbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500F00FMsg fMsg = new NWAL1500F00FMsg();

        // exclude items -> convert to xxDtTxt(MM/DD/YYYY)
        fMsg.addExclusionItem("ordDt");
        fMsg.addExclusionItem("custIssPoDt");
        fMsg.addExclusionItem("ordSrcImptDt");
        fMsg.addExclusionItem("addRddDt");
        fMsg.addExclusionItem("ordSgnDt");
        fMsg.addExclusionItem("rddDt_LL");
        fMsg.addExclusionItem("prcBaseDt_LL");
        fMsg.addExclusionItem("custIssPoDt_LL");

        final String csvFileName = ZYPCSVOutFile.createCSVOutFileNm(DOWNLOAD_CSV_FILE_NAME_FOR_OUTBOUND);
        bizMsg.xxFileData.setTempFilePath(null, csvFileName, EXTENSION_CSV);
        final ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        List<String> headerArray = new ArrayList<String>();
        // 2024/03/08 CITS QC#63654 Add Start
        headerArray.addAll(Arrays.asList(DOWNLOAD_CSV_HEADER_FOR_ORDER));
        headerArray.addAll(Arrays.asList(DOWNLOAD_CSV_HEADER_FOR_OUTBOUND));
        headerArray.addAll(Arrays.asList(DOWNLOAD_CSV_HEADER_FOR_OUTBOUND_HEADER));
        fileWriter.writeHeader(headerArray.toArray(new String[0]));
        // 2024/03/08 CITS QC#63654 Add End

        // set header date
        copyHeader(bizMsg, fMsg);

        int lineIndex = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NWAL1500_ASMsg config = glblMsg.A.no(i);

            // set configuration date
            copyConfig(bizMsg, config, fMsg);

            String dsOrdPosnNum = config.dsOrdPosnNum_LC.getValue();
            for (; lineIndex < glblMsg.B.getValidCount(); lineIndex++) {

                NWAL1500_BSMsg line = glblMsg.B.no(lineIndex);
                if (!S21StringUtil.isEquals(dsOrdPosnNum, line.dsOrdPosnNum_LL.getValue())) {

                    break;
                } else {

                    // set line date
                    copyLine(bizMsg, line, fMsg);

                    // write a line.
                    fileWriter.write();
                }
            }
        }

        // close file writer
        fileWriter.close();

        // 2018/05/23 S21_NA#23523 Add Start
        ZYPExcelUtil.csvFileToExcel(bizMsg.xxFileData.getTempFilePath(), fMsg);
        bizMsg.xxFileData.setTempFilePath(null, csvFileName, ZYPExcelUtil.EXCEL_EXTENSION_DEFAULT);
        // 2018/05/23 S21_NA#23523 Add End
    }

    /**
     * downloadForInbound
     * @param bizMsg NWAL1500CMsg
     */
    public static void downloadForInbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500F01FMsg fMsg = new NWAL1500F01FMsg();

        // exclude items -> convert to xxDtTxt(MM/DD/YYYY)
        fMsg.addExclusionItem("ordDt");
        fMsg.addExclusionItem("custIssPoDt");
        fMsg.addExclusionItem("ordSrcImptDt");
        fMsg.addExclusionItem("addRddDt");
        fMsg.addExclusionItem("ordSgnDt");
        fMsg.addExclusionItem("rqstPickUpDt_RL");
        fMsg.addExclusionItem("prcBaseDt_RL");
        fMsg.addExclusionItem("custIssPoDt_RL");

        final String csvFileName = ZYPCSVOutFile.createCSVOutFileNm(DOWNLOAD_CSV_FILE_NAME_FOR_INBOUND);
        bizMsg.xxFileData.setTempFilePath(null, csvFileName, EXTENSION_CSV);
        final ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        List<String> headerArray = new ArrayList<String>();
        // 2024/03/08 CITS QC#63654 Add Start
        headerArray.addAll(Arrays.asList(DOWNLOAD_CSV_HEADER_FOR_ORDER));
        headerArray.addAll(Arrays.asList(DOWNLOAD_CSV_HEADER_FOR_INBOUND));
        headerArray.addAll(Arrays.asList(DOWNLOAD_CSV_HEADER_FOR_INBOUND_HEADER));
        // 2024/03/08 CITS QC#63654 Add End
        fileWriter.writeHeader(headerArray.toArray(new String[0]));

        // set header data
        copyHeader(bizMsg, fMsg);

        int lineIndex = 0;
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

            NWAL1500_CSMsg config = glblMsg.C.no(i);

            // set configuration data
            copyConfig(bizMsg, config, fMsg);

            String dsOrdPosnNum = config.dsOrdPosnNum_RC.getValue();
            for (; lineIndex < glblMsg.D.getValidCount(); lineIndex++) {

                NWAL1500_DSMsg line = glblMsg.D.no(lineIndex);
                if (!S21StringUtil.isEquals(dsOrdPosnNum, line.dsOrdPosnNum_RL.getValue())) {

                    break;
                } else {

                    // set line data
                    copyLine(bizMsg, line, fMsg);

                    // write a line
                    fileWriter.write();
                }
            }
        }

        // close file writer
        fileWriter.close();

        // 2018/05/23 S21_NA#23523 Add Start
        ZYPExcelUtil.csvFileToExcel(bizMsg.xxFileData.getTempFilePath(), fMsg);
        bizMsg.xxFileData.setTempFilePath(null, csvFileName, ZYPExcelUtil.EXCEL_EXTENSION_DEFAULT);
        // 2018/05/23 S21_NA#23523 Add End
    }

    // /////////////////////////
    // for outbound
    // /////////////////////////

    private static void copyHeader(NWAL1500CMsg bizMsg, NWAL1500F00FMsg fMsg) {

        EZDMsg.copy(bizMsg, null, fMsg, null);

        // drop down
        // Sub Reason
        ZYPEZDItemValueSetter.setValue(fMsg.dsOrdRsnDescTxt, getNameFromDropDownList(bizMsg.dsOrdRsnCd_CD, bizMsg.dsOrdRsnDescTxt_NM, bizMsg.dsOrdRsnCd.getValue()));
        // Service Level
        ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt, getNameFromDropDownList(bizMsg.shpgSvcLvlCd_CD, bizMsg.shpgSvcLvlDescTxt_NM, bizMsg.shpgSvcLvlCd.getValue()));
        // Special Handling
        ZYPEZDItemValueSetter.setValue(fMsg.spclHdlgTpDescTxt, getNameFromDropDownList(bizMsg.spclHdlgTpCd_CD, bizMsg.spclHdlgTpDescTxt_NM, bizMsg.spclHdlgTpCd.getValue()));
        // Payment Method
        ZYPEZDItemValueSetter.setValue(fMsg.dsPmtMethDescTxt, getNameFromDropDownList(bizMsg.dsPmtMethCd_CD, bizMsg.dsPmtMethDescTxt_NM, bizMsg.dsPmtMethCd.getValue()));
        // Prepayment Type
        ZYPEZDItemValueSetter.setValue(fMsg.prePmtTpDescTxt, getNameFromDropDownList(bizMsg.prePmtTpCd_CD, bizMsg.prePmtTpDescTxt_NM, bizMsg.prePmtTpCd.getValue()));
        // End of Term Purchase Option
        ZYPEZDItemValueSetter.setValue(fMsg.leasePrchOptDescTxt, getNameFromDropDownList(bizMsg.leasePrchOptCd_CD, bizMsg.leasePrchOptDescTxt_NM, bizMsg.leasePrchOptCd.getValue()));
        // Payment Frequency
        ZYPEZDItemValueSetter.setValue(fMsg.leasePmtFreqDescTxt, getNameFromDropDownList(bizMsg.leasePmtFreqCd_CD, bizMsg.leasePmtFreqDescTxt_NM, bizMsg.leasePmtFreqCd.getValue()));

        // bill to address
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, bizMsg.billToCustCd.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.billToFirstLineAddr, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToScdLineAddr, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToThirdLineAddr, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToFrthLineAddr, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToCtyAddr, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToStCd, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToPostCd, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }

        // ship to address
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, bizMsg.shipToCustCd.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFirstLineAddr, billToAddrMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToScdLineAddr, billToAddrMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToThirdLineAddr, billToAddrMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFrthLineAddr, billToAddrMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCtyAddr, billToAddrMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToStCd, billToAddrMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToPostCd, billToAddrMap.get("POST_CD"));
            }
        }

        // sold to address
        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, bizMsg.soldToCustLocCd.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.stCd, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.postCd, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }

        // date format (EZ to MM/DD/YYYY)

        // Order Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H0, ZYPDateUtil.formatEzd8ToDisp(fMsg.ordDt.getValue()));
        // Customer PO Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, ZYPDateUtil.formatEzd8ToDisp(fMsg.custIssPoDt.getValue()));
        // Import Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, ZYPDateUtil.formatEzd8ToDisp(fMsg.ordSrcImptDt.getValue()));
        // Requested Delivery Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H3, ZYPDateUtil.formatEzd8ToDisp(fMsg.addRddDt.getValue()));
        // Customer Signed Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H4, ZYPDateUtil.formatEzd8ToDisp(fMsg.ordSgnDt.getValue()));
    }

    private static void copyConfig(NWAL1500CMsg bizMsg, NWAL1500_ASMsg config, NWAL1500F00FMsg fMsg) {

        EZDMsg.copy(config, null, fMsg, null);

        // drop down
        // Config Type
        ZYPEZDItemValueSetter.setValue(fMsg.configTpDescTxt_LC, getNameFromDropDownList(bizMsg.configTpCd_LD, bizMsg.configTpDescTxt_LD, config.configTpCd_LC.getValue()));

        // bill to address
        if (ZYPCommonFunc.hasValue(config.billToCustCd_LC)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, config.billToCustCd_LC.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.billToFirstLineAddr_LC, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToScdLineAddr_LC, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToThirdLineAddr_LC, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToFrthLineAddr_LC, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToCtyAddr_LC, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToStCd_LC, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToPostCd_LC, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }

        // ship to address
        if (ZYPCommonFunc.hasValue(config.shipToCustCd_LC)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, config.shipToCustCd_LC.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFirstLineAddr_LC, billToAddrMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToScdLineAddr_LC, billToAddrMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToThirdLineAddr_LC, billToAddrMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFrthLineAddr_LC, billToAddrMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCtyAddr_LC, billToAddrMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToStCd_LC, billToAddrMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToPostCd_LC, billToAddrMap.get("POST_CD"));
            }
        }

        // sold to address
        if (ZYPCommonFunc.hasValue(config.soldToCustLocCd_LC)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, config.soldToCustLocCd_LC.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr_LC, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr_LC, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr_LC, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr_LC, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr_LC, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.stCd_LC, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.postCd_LC, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }
    }

    private static void copyLine(NWAL1500CMsg bizMsg, NWAL1500_BSMsg line, NWAL1500F00FMsg fMsg) {

        EZDMsg.copy(line, null, fMsg, null);

        // drop down
        // Line Category
        ZYPEZDItemValueSetter.setValue(fMsg.dsOrdLineCatgDescTxt_LL, getNameFromDropDownList(bizMsg.dsOrdLineCatgCd_CD, bizMsg.dsOrdLineCatgDescTxt_NM, line.dsOrdLineCatgCd_LL.getValue()));
        // Source
        ZYPEZDItemValueSetter.setValue(fMsg.ordLineSrcNm_LL, getNameFromDropDownList(bizMsg.ordLineSrcCd_CD, bizMsg.ordLineSrcNm_NM, line.ordLineSrcCd_LL.getValue()));
        // UOM
        ZYPEZDItemValueSetter.setValue(fMsg.pkgUomDescTxt_LL, getNameFromDropDownList(line.pkgUomCd_CD, line.pkgUomDescTxt_NM, line.custUomCd_LL.getValue()));

        // creation date
        ZYPEZDItemValueSetter.setValue(fMsg.cratTsDplyTxt_L2, fMsg.cratTsDplyTxt_LL);

        // date format (EZ to MM/DD/YYYY)

        // Req. Delivery Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_L0, ZYPDateUtil.formatEzd8ToDisp(fMsg.rddDt_LL.getValue()));
        // Pricing Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_L1, ZYPDateUtil.formatEzd8ToDisp(fMsg.prcBaseDt_LL.getValue()));
        // Customer PO Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_L2, ZYPDateUtil.formatEzd8ToDisp(fMsg.custIssPoDt_LL.getValue()));
    }

    // /////////////////////////
    // for inbound
    // /////////////////////////

    private static void copyHeader(NWAL1500CMsg bizMsg, NWAL1500F01FMsg fMsg) {

        EZDMsg.copy(bizMsg, null, fMsg, null);

        // drop down
        // Sub Reason
        ZYPEZDItemValueSetter.setValue(fMsg.dsOrdRsnDescTxt, getNameFromDropDownList(bizMsg.dsOrdRsnCd_CD, bizMsg.dsOrdRsnDescTxt_NM, bizMsg.dsOrdRsnCd.getValue()));
        // Service Level
        ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt, getNameFromDropDownList(bizMsg.shpgSvcLvlCd_CD, bizMsg.shpgSvcLvlDescTxt_NM, bizMsg.shpgSvcLvlCd.getValue()));
        // Special Handling
        ZYPEZDItemValueSetter.setValue(fMsg.spclHdlgTpDescTxt, getNameFromDropDownList(bizMsg.spclHdlgTpCd_CD, bizMsg.spclHdlgTpDescTxt_NM, bizMsg.spclHdlgTpCd.getValue()));
        // Payment Method
        ZYPEZDItemValueSetter.setValue(fMsg.dsPmtMethDescTxt, getNameFromDropDownList(bizMsg.dsPmtMethCd_CD, bizMsg.dsPmtMethDescTxt_NM, bizMsg.dsPmtMethCd.getValue()));
        // Prepayment Type
        ZYPEZDItemValueSetter.setValue(fMsg.prePmtTpDescTxt, getNameFromDropDownList(bizMsg.prePmtTpCd_CD, bizMsg.prePmtTpDescTxt_NM, bizMsg.prePmtTpCd.getValue()));
        // End of Term Purchase Option
        ZYPEZDItemValueSetter.setValue(fMsg.leasePrchOptDescTxt, getNameFromDropDownList(bizMsg.leasePrchOptCd_CD, bizMsg.leasePrchOptDescTxt_NM, bizMsg.leasePrchOptCd.getValue()));
        // Payment Frequency
        ZYPEZDItemValueSetter.setValue(fMsg.leasePmtFreqDescTxt, getNameFromDropDownList(bizMsg.leasePmtFreqCd_CD, bizMsg.leasePmtFreqDescTxt_NM, bizMsg.leasePmtFreqCd.getValue()));

        // bill to address
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, bizMsg.billToCustCd.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.billToFirstLineAddr, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToScdLineAddr, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToThirdLineAddr, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToFrthLineAddr, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToCtyAddr, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToStCd, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToPostCd, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }

        // ship to address
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, bizMsg.shipToCustCd.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFirstLineAddr, billToAddrMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToScdLineAddr, billToAddrMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToThirdLineAddr, billToAddrMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFrthLineAddr, billToAddrMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCtyAddr, billToAddrMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToStCd, billToAddrMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToPostCd, billToAddrMap.get("POST_CD"));
            }
        }

        // sold to address
        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, bizMsg.soldToCustLocCd.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.stCd, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.postCd, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }

        // date format (EZ to MM/DD/YYYY)

        // Order Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H0, ZYPDateUtil.formatEzd8ToDisp(fMsg.ordDt.getValue()));
        // Customer PO Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, ZYPDateUtil.formatEzd8ToDisp(fMsg.custIssPoDt.getValue()));
        // Import Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, ZYPDateUtil.formatEzd8ToDisp(fMsg.ordSrcImptDt.getValue()));
        // Requested Delivery Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H3, ZYPDateUtil.formatEzd8ToDisp(fMsg.addRddDt.getValue()));
        // Customer Signed Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H4, ZYPDateUtil.formatEzd8ToDisp(fMsg.ordSgnDt.getValue()));
    }

    private static void copyConfig(NWAL1500CMsg bizMsg, NWAL1500_CSMsg config, NWAL1500F01FMsg fMsg) {

        EZDMsg.copy(config, null, fMsg, null);

        // Config Type
        ZYPEZDItemValueSetter.setValue(fMsg.configTpDescTxt_RC, getNameFromDropDownList(bizMsg.configTpCd_RD, bizMsg.configTpDescTxt_RD, config.configTpCd_RC.getValue()));

        // bill to address
        if (ZYPCommonFunc.hasValue(config.billToCustCd_RC)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, config.billToCustCd_RC.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.billToFirstLineAddr_RC, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToScdLineAddr_RC, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToThirdLineAddr_RC, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToFrthLineAddr_RC, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToCtyAddr_RC, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToStCd_RC, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToPostCd_RC, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }

        // ship to address
        if (ZYPCommonFunc.hasValue(config.shipToCustCd_RC)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, config.shipToCustCd_RC.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFirstLineAddr_RC, billToAddrMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToScdLineAddr_RC, billToAddrMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToThirdLineAddr_RC, billToAddrMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToFrthLineAddr_RC, billToAddrMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCtyAddr_RC, billToAddrMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToStCd_RC, billToAddrMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToPostCd_RC, billToAddrMap.get("POST_CD"));
            }
        }

        // sold to address
        if (ZYPCommonFunc.hasValue(config.soldToCustLocCd_RC)) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, config.soldToCustLocCd_RC.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr_RC, billToAddrMap.get("BILL_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr_RC, billToAddrMap.get("BILL_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr_RC, billToAddrMap.get("BILL_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr_RC, billToAddrMap.get("BILL_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr_RC, billToAddrMap.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.stCd_RC, billToAddrMap.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.postCd_RC, billToAddrMap.get("BILL_TO_POST_CD"));
            }
        }
    }

    private static void copyLine(NWAL1500CMsg bizMsg, NWAL1500_DSMsg line, NWAL1500F01FMsg fMsg) {

        EZDMsg.copy(line, null, fMsg, null);

        // drop down
        // Return reason
        ZYPEZDItemValueSetter.setValue(fMsg.rtrnRsnDescTxt_RL, getNameFromDropDownList(bizMsg.rtrnRsnCd_CD, bizMsg.rtrnRsnDescTxt_NM, line.rtrnRsnCd_RL.getValue()));
        // Line Category

        // Mod Start 2017/09/21 S21_NA#16346
        //ZYPEZDItemValueSetter.setValue(fMsg.dsOrdLineCatgDescTxt_RL, getNameFromDropDownList(bizMsg.dsOrdLineCatgCd_CR, bizMsg.dsOrdLineCatgDescTxt_NR, line.dsOrdLineCatgCd_RL.getValue()));
        ZYPEZDItemValueSetter.setValue(fMsg.dsOrdLineCatgDescTxt_RL, getNameFromDropDownList(line.dsOrdLineCatgCd_CR, line.dsOrdLineCatgDescTxt_NR, line.dsOrdLineCatgCd_RL.getValue()));
        // Mod End 2017/09/21 S21_NA#16346

        // UOM
        ZYPEZDItemValueSetter.setValue(fMsg.pkgUomDescTxt_RL, getNameFromDropDownList(line.pkgUomCd_CR, line.pkgUomDescTxt_CN, line.custUomCd_RL.getValue()));
        // HDD Removal
        ZYPEZDItemValueSetter.setValue(fMsg.hddRmvDescTxt_RL, getNameFromDropDownList(bizMsg.hddRmvCd_CD, bizMsg.hddRmvDescTxt_NM, line.hddRmvCd_RL.getValue()));

        // creation date
        ZYPEZDItemValueSetter.setValue(fMsg.cratTsDplyTxt_R2, fMsg.cratTsDplyTxt_RL);

        // date format (EZ to MM/DD/YYYY)

        // Req. Pick Up Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_L0, ZYPDateUtil.formatEzd8ToDisp(fMsg.rqstPickUpDt_RL.getValue()));
        // Pricing Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_L1, ZYPDateUtil.formatEzd8ToDisp(fMsg.prcBaseDt_RL.getValue()));
        // Customer PO Date
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_L2, ZYPDateUtil.formatEzd8ToDisp(fMsg.custIssPoDt_RL.getValue()));
    }

    private static String getNameFromDropDownList(EZDCStringItemArray codeArray, EZDCStringItemArray nameArray, String code) {

        if (S21StringUtil.isEmpty(code)) {
            return null;
        }

        for (int i = 0; i < codeArray.length(); i++) {
            if (i > nameArray.length() - 1) {
                return null;
            }
            if (S21StringUtil.isEquals(codeArray.no(i).getValue(), code)) {
                return nameArray.no(i).getValue();
            }
        }

        return null;
    }

    private static String getNameFromDropDownList(EZDSStringItemArray codeArray, EZDSStringItemArray nameArray, String code) {

        if (S21StringUtil.isEmpty(code)) {
            return null;
        }

        for (int i = 0; i < codeArray.length(); i++) {
            if (i > nameArray.length() - 1) {
                return null;
            }
            if (S21StringUtil.isEquals(codeArray.no(i).getValue(), code)) {
                return nameArray.no(i).getValue();
            }
        }

        return null;
    }
}
