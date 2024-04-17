/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0340;

import static business.blap.NSBL0340.constant.NSBL0340Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSBL0340.common.NSBL0340CommonLogic;
import business.file.NSBL0340F00FMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Service Task Summary
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/21/2016   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0340BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSBL0340CMsg cMsg = (NSBL0340CMsg) arg0;
        NSBL0340SMsg sMsg = (NSBL0340SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0340_INIT".equals(screenAplID)) {
                doProcess_NSBL0340_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSBL0340Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0340Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSBL0340Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0340Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0340Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0340Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0340_INIT(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg) {

        if (!NSBL0340CommonLogic.checkMandatory(cMsg, sMsg)) {
            return;
        }

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        clearSearchResult(cMsg, sMsg);

        HashMap<String, Object> params = setOpenSvcTaskDtlParam(cMsg, sMsg, sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NSBL0340Query.getInstance().getOpenSvcTaskDtlInfoList(params, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
        }

        NSBL0340CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NSBL0340Scrn00_CMN_Download(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSBL0340Query query = NSBL0340Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE_MAX);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            // Create Csv File
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            HashMap<String, Object> params = setOpenSvcTaskDtlParam(cMsg, sMsg, DOWNLOAD_LIMIT_COUNT + 1);
            stmtSelect = ssmLLClient.createPreparedStatement("getOpenSvcTaskDtlInfoList", params, execParam);
            rs = stmtSelect.executeQuery();
            NSBL0340F00FMsg fMsg = new NSBL0340F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            writeCsvFile(cMsg, rs, fMsg, csvOutFile);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private void doProcess_NSBL0340Scrn00_PageNext(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg) {
        int pageFrom = 0;
        pageFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0340CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    private void doProcess_NSBL0340Scrn00_PagePrev(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg) {
        int pageFrom = 0;
        pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0340CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    private HashMap<String, Object> setOpenSvcTaskDtlParam(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg, int limitCount) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        NSBL0340CommonLogic.getSvcRqstDownTpInfo(cMsg, sMsg);
        params.put("bizMsg", cMsg);
        params.put("dateFormatPre", DATE_FORMAT_PRE);
        params.put("dateFormat", DATE_FORMAT);
        params.put("timeFormatPre", TIME_FORMAT_PRE);
        params.put("timeFormat", TIME_FORMAT);
        params.put("pad00", PAD00);
        params.put("asterisk", ASTERISK);
        params.put("limitCount", limitCount);
        return params;
    }

    private void clearSearchResult(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        cMsg.xxPageShowFromNum.setValue(0);
        cMsg.xxPageShowToNum.setValue(0);
        cMsg.xxPageShowOfNum.setValue(0);
    }

    private void writeCsvFile(NSBL0340CMsg cMsg, ResultSet rs, NSBL0340F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // Write Header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        // Write contents
        int outCount = 0;
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_COUNT) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            setValue(fMsg.fsrNum, rs.getString("FSR_NUM"));
            setValue(fMsg.svcTaskNum, rs.getString("SVC_TASK_NUM"));
            setValue(fMsg.ittNum, rs.getString("ITT_NUM"));
            setValue(fMsg.svcTaskStsDescTxt_S, rs.getString("SVC_TASK_STS_DESC_TXT_FSR"));
            setValue(fMsg.svcTaskStsDescTxt_V, rs.getString("SVC_TASK_STS_DESC_TXT_VISIT"));
            setValue(fMsg.serNum, rs.getString("SER_NUM"));
            setValue(fMsg.t_MdlNm, rs.getString("T_MDL_NM"));
            setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            setValue(fMsg.shipToFullAddr, rs.getString("FILLER_L272_IF"));
            setValue(fMsg.custTelNum, rs.getString("CUST_TEL_NUM"));
            setValue(fMsg.xxScrItem30Txt, rs.getString("SVC_AVAL_TXT"));
            setValue(fMsg.rqstDtTmTsTxt_A, rs.getString("SVC_TASK_RCV_DT_TM"));
            setValue(fMsg.techNm, rs.getString("TECH_NM"));
            setValue(fMsg.xxDsRtrnRsnNm_A, rs.getString("XX_DS_RTRN_RSN_NM_A"));
            setValue(fMsg.xxDsRtrnRsnNm_B, rs.getString("XX_DS_RTRN_RSN_NM_B"));
            setValue(fMsg.svcCmntTxt, rs.getString("SVC_CMNT_TXT"));
            setValue(fMsg.rqstDtTmTsTxt_B, rs.getString("SVC_TASK_CPLT_DT_TM"));
            setValue(fMsg.xxRcvTm, rs.getString("XX_RCV_TM"));
            setValue(fMsg.xxScrItem54Txt_A, rs.getString("XX_SCR_ITEM_54_TXT_A"));
            setValue(fMsg.xxScrItem54Txt_B, rs.getString("XX_SCR_ITEM_54_TXT_B"));
            setValue(fMsg.xxDsRtrnRsnNm_C, rs.getString("XX_DS_RTRN_RSN_NM_C"));
            setValue(fMsg.xxDsRtrnRsnNm_D, rs.getString("XX_DS_RTRN_RSN_NM_D"));
            setValue(fMsg.fullPsnNm, rs.getString("TO_CUST_NM"));

            csvOutFile.write();
            outCount++;
        }

        if (outCount == 0) {
            cMsg.setMessageInfo(NZZM0000E, null);
        }

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0340F00FMsg fMsg, NSBL0340CMsg cMsg) {

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {labelConv.convLabel2i18nLabel(SCRN_ID, "SR#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Task#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Vendor SR#"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "SR Status"), labelConv.convLabel2i18nLabel(SCRN_ID, "Task Status"), labelConv.convLabel2i18nLabel(SCRN_ID, "Serial#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Model"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Customer"), labelConv.convLabel2i18nLabel(SCRN_ID, "Address"), labelConv.convLabel2i18nLabel(SCRN_ID, "Phone"), labelConv.convLabel2i18nLabel(SCRN_ID, "Available"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Receive Date"), labelConv.convLabel2i18nLabel(SCRN_ID, "Assignee"), labelConv.convLabel2i18nLabel(SCRN_ID, "SR Type"), labelConv.convLabel2i18nLabel(SCRN_ID, "Bill Code"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Summary"), labelConv.convLabel2i18nLabel(SCRN_ID, "Complete"), labelConv.convLabel2i18nLabel(SCRN_ID, "Response Time"), labelConv.convLabel2i18nLabel(SCRN_ID, "Problem"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Location"), labelConv.convLabel2i18nLabel(SCRN_ID, "Resolution"), labelConv.convLabel2i18nLabel(SCRN_ID, "Correction"), labelConv.convLabel2i18nLabel(SCRN_ID, "Collector") };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }
}
