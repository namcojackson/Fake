/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB035001;

import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.AMT_FRACTION_DIGIT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.BIGDECIMAL_FORMAT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_CFS_APP_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_CFS_LEASE_PKG_PO_HDR_PK;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_CFS_PO_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_CFS_PO_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_CONSL_BILL_FLG;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_DTL_CNT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_GL_DT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_INV_CPLT_AMT_RATE;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_INV_CPLT_TOT_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_INV_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_INV_PRT_CTRL_PK;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_INV_TOT_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_LEASE_CMPY_USR_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_LEASE_CMPY_USR_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_LEASE_PKG_RPT_WRK_PK;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_PSN_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COL_TOC_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.COVER_REPORT_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.INVOICE_REPORT_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.NULL_BIGDECIMAL_REPLACE_CHAR;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.NULL_STRING_REPLACE_CHAR;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.NWCM0109E;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.NWCM0110E;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.NWCM0112E;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.PDF_EXTENSION;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.RPT_PRM_CFS_APP_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.RPT_PRM_CFS_PO_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.RPT_PRM_CONSL_BILL_FLG;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.RPT_PRM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.RPT_PRM_INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.RPT_PRM_INV_BILL_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.RPT_PRM_INV_PRT_CTRL_PK;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.THEREFORE_ASYNC_PROC_FLG;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.UNDERSCORE;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_CFS_OWN_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_CFS_OWN_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_INV;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_INV_DT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_INV_LIST;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_INV_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_LEASE_PACKAGE;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_ORD_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_PKG_LOC;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_PO_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_ELEMENT_SHIP_TO;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.XML_EXTENSION;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.YYYYMMDD_LENGTH;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.NWCB035001_MAX_PCT;
import static com.canon.cusa.s21.batch.NWC.NWCB035001.constant.NWCB035001Constant.CFS_APP_NUM_TENTATIVE_VAL;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CFS_LEASE_PKG_PO_HDRTMsg;
import business.db.LEASE_PKG_RPT_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 *<pre>
 * NWCB0350:CFS Lease Package Create & Send Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         W.Honda         Create          N/A
 * 2016/09/08   Fujitsu         W.Honda         Update          QC#14068
 * 2016/10/28   Fujitsu         W.Honda         Update          QC#15326
 * 2016/11/10   Fujitsu         W.Honda         Update          Unit Test#201-50
 * 2018/01/09   Fujitsu         W.Honda         Update          QC#21706-2
 * 2018/01/22   Fujitsu         K.Ishizuka      Update          QC#23439
 * 2018/01/30   SRAA            K.Aratani       Update          QC#23439
 * 2018/02/22   Fujitsu         W.Honda         Update          QC#24315
 * 2018/03/02   SRAA            K.Aratani       Update          QC#24318
 * 2019/04/04   Fujitsu         K.Kato          Update          QC#30756
 * 2019/05/21   Fujitsu         M.Ohno          Update          QC#50398
 * 2022/09/30   Hitachi         H.Watanabe      Update          QC#60253
 *</pre>
 */

public class NWCB035001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** output file location path */
    private String fileLocPath;

    /** output file Name(include location path) */
    private String fileName;

    /** normal count */
    private int normalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** SSM Low Level Coding Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client Custom */
    //2022/09/30 QC#60253 Del Start
//    private S21SsmBatchClientCustom ssmBatchClientCustom = null;
    //2022/09/30 QC#60253 Del End

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Document Builder Factory */
    private DocumentBuilderFactory factory = null;

    /** DocumentBuilder */
    private DocumentBuilder builder = null;

    /** S21EIPPrintingService */
    S21EIPPrintingService service = null;

    /** isAsyncProc flag */
    private boolean isAsyncProc;

    //QC#24318
    /** Maximum Percent String */
    private String maxPctStr;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {

        // Initialization S21BatchMain
        new NWCB035001().executeBatch(NWCB035001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code"});
        }

        // S21SsmBatchClient
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // S21SsmBatchClientCustom
        //2022/09/30 QC#60253 Del Start
//        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
        //2022/09/30 QC#60253 Del End

        /** S21SsmBatchClient */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // 2016/10/28 QC#15326 Mod Start
//        // file Location Path
//        this.fileLocPath = ZYPCodeDataUtil.getVarCharConstValue(THEREFORE_LOC_PATH, getGlobalCompanyCode());
//        if (!ZYPCommonFunc.hasValue(this.fileLocPath)) {
//            throw new S21AbendException(NWAM0811E, new String[] {"VAR_CHAR_CONST KEY=THEREFORE_LOC_PATH"});
//        }
//
//        String crlf = System.getProperty("file.separator");
//        this.fileLocPath = this.fileLocPath + crlf + ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD) + crlf + SUB_SYSTEM;

        //QC#24318
        this.maxPctStr = ZYPCodeDataUtil.getVarCharConstValue(NWCB035001_MAX_PCT, getGlobalCompanyCode());
        
        // Build upload folder path. 
        // ZYPFileUtil.getInvoiceStoredDirectoryPath return /s21_nas /therefore_input
        String rootPath =  ZYPFileUtil.getThereforeSharedDirectoryPath ();
        String subSystemCode = S21BatchUtil.getSubSystemCd();
        String date = ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD);

        StringBuilder uploadFolderPath = new StringBuilder();
        uploadFolderPath.append(rootPath);
        uploadFolderPath.append(File.separator);
        uploadFolderPath.append(date);
        uploadFolderPath.append(File.separator);
        uploadFolderPath.append(subSystemCode);
        uploadFolderPath.append(File.separator);

        this.fileLocPath = uploadFolderPath.toString();
        // 2016/10/28 QC#15326 Mod End

        this.factory = DocumentBuilderFactory.newInstance();

        this.service = new S21EIPPrintingService();

        String thereforAsyncProcFlg = ZYPCodeDataUtil.getVarCharConstValue(THEREFORE_ASYNC_PROC_FLG, getGlobalCompanyCode());
        this.isAsyncProc = ZYPConstant.FLG_ON_Y.equals(thereforAsyncProcFlg);
    }

    @Override
    protected void mainRoutine() {

        try {
            this.builder = this.factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new S21AbendException(NWCM0112E, new String[] {"new DocumentBuilder", "From DocumentBuilderFactory"});
        }

        // work count
        int normalWorkCnt = 0;
        int errorWorkCnt = 0;

        // 2016/11/10 Unit Test#201-50 Del Start
//        // LEASE_PKG_RPT_WRK Delete
//        this.ssmBatchClientCustom.delete("truncateTable", null);
        // 2016/11/10 Unit Test#201-50 Del End

        // S21SsmLowLevelCodintClient Setup
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setMaxRows(0);

        PreparedStatement ps = null;
        ResultSet rs = null;
        BigDecimal prevPoHdrPk = null;
        String prevPoNum = null;
        Document document = null;
        //String leasePackageID = null;  //QC#23439
        List<S21ReportRequestBean> requestList = null;

        // Search Target Invoice for CFS
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);

        try {
            ps = this.ssmLLClient.createPreparedStatement("getCfsLeasePkgPo", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {

                if (!ZYPCommonFunc.hasValue(prevPoNum)) {
                    prevPoNum = rs.getString(COL_CFS_PO_NUM);
                    prevPoHdrPk = rs.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK);
                    if (!this.isAsyncProc) {
                        document = createXmlFileHeader(rs);
                    }
                    //leasePackageID = ZYPExtnNumbering.getUniqueID(this.glblCmpyCd, AUTO_SEQ_KEY); //QC#23439
                    requestList = createCoverRequest(rs, requestList);

                    // 2018/02/22 QC#24315 Add Start
                    deleteLeasePkgPrtWrk(rs.getString(COL_CFS_APP_NUM), rs.getString(COL_CFS_PO_NUM));
                    // 2018/02/22 QC#24315 Add End
                } else if (ZYPCommonFunc.hasValue(prevPoNum)
                        // 2019/04/04 QC#30756 Mod Start
                        //&& !prevPoNum.equals(rs.getString(COL_CFS_PO_NUM))) {
                        && !prevPoNum.equalsIgnoreCase(rs.getString(COL_CFS_PO_NUM))) {
                        // 2019/04/04 QC#30756 Mod End

                    if (errorWorkCnt > 0) {
                        rollback();
                        this.errorCount = this.errorCount + (errorWorkCnt + normalWorkCnt);
                    } else {
                        commit();

                        // XML
                        if (!this.isAsyncProc) {
                            writeXmlDocument(document);
                        }

                        // PDF
                        if (requestList == null || requestList.isEmpty()) {
                            throw new S21AbendException(NWCM0112E, new String[] {"MERGE_REPORT_BYTES_FAILURE", "PO#:" + prevPoNum});
                        }

                        byte[] pdf = this.service.onlineMergeReports(requestList);
                        if (pdf == null) {
                            throw new S21AbendException(NWCM0112E, new String[] {"MERGE_REPORT_BYTES_FAILURE", "PO#:" + prevPoNum});
                        }

                        // 2016/10/28 QC#15326 Mod Start
//                        ZYPFileWriter.writeFile(this.fileName + PDF_EXTENSION, pdf);
                        ZYPFileWriter.uploadThereforeDocument(pdf, this.fileName + PDF_EXTENSION);
                        // 2016/10/28 QC#15326 Mod End

                        updateCfsLeasePkgPoHdr(prevPoHdrPk);
                        commit();
                        this.normalCount = this.normalCount + normalWorkCnt;
                    }
                    errorWorkCnt = 0;
                    normalWorkCnt = 0;
                    prevPoNum = rs.getString(COL_CFS_PO_NUM);
                    prevPoHdrPk = rs.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK);
                    if (!this.isAsyncProc) {
                        document = createXmlFileHeader(rs);
                    }
                    //leasePackageID = ZYPExtnNumbering.getUniqueID(this.glblCmpyCd, AUTO_SEQ_KEY);  //QC#23439

                    requestList = createCoverRequest(rs, requestList);

                    // 2018/02/22 QC#24315 Add Start
                    deleteLeasePkgPrtWrk(rs.getString(COL_CFS_APP_NUM), rs.getString(COL_CFS_PO_NUM));
                    // 2018/02/22 QC#24315 Add End
                } else {
                    if (!this.isAsyncProc) {
                        setXmlInvList(document, rs);
                    }
                    createInvRptRequest(rs, requestList);
                }

                //QC#23439
                //if (!insertLeasePkgPrtWrk(rs, leasePackageID)) { 
                if (!insertLeasePkgPrtWrk(rs)) {
                    errorWorkCnt++;
                } else {
                    normalWorkCnt++;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new S21AbendException("write Document false");
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        // Loop End
        if (errorWorkCnt > 0) {
            rollback();
            this.errorCount = this.errorCount + (errorWorkCnt + normalWorkCnt);
        } else if (this.normalCount > 0 || normalWorkCnt > 0) {
            commit();

            // XML
            if (!this.isAsyncProc) {
                try {
                    writeXmlDocument(document);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new S21AbendException("write XML Document false");
                }
            }

            // PDF
            if (requestList == null || requestList.isEmpty()) {
                throw new S21AbendException(NWCM0112E, new String[] {"MERGE_REPORT_BYTES_FAILURE", "PO#:" + prevPoNum});
            }

            byte[] pdf = this.service.onlineMergeReports(requestList);
            if (pdf == null) {
                throw new S21AbendException(NWCM0112E, new String[] {"MERGE_REPORT_BYTES_FAILURE", "PO#:" + prevPoNum});
            }

            // 2016/10/28 QC#15326 Mod Start
//            ZYPFileWriter.writeFile(this.fileName + PDF_EXTENSION, pdf);
            ZYPFileWriter.uploadThereforeDocument(pdf, this.fileName + PDF_EXTENSION);
            // 2016/10/28 QC#15326 Mod End

            updateCfsLeasePkgPoHdr(prevPoHdrPk);
            commit();
            this.normalCount = this.normalCount + normalWorkCnt;
        }
    }

    @Override
    protected void termRoutine() {

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    // ****************************************************************
    // Main Process
    // ****************************************************************
    //QC#23439
    //private boolean insertLeasePkgPrtWrk(ResultSet rs, String leasePackageID) throws SQLException {
    private boolean insertLeasePkgPrtWrk(ResultSet rs) throws SQLException {

        // 2018/02/22 QC#24315 Del Start
        // 2016/11/10 Unit Test#201-50 Add Start
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("cfsAppNum", rs.getString(COL_CFS_APP_NUM));
//        ssmParam.put("cfsPoNum", rs.getString(COL_CFS_PO_NUM));
//
//        List<Map<String, Object>> resultList = ssmBatchClient.queryObjectList("getLeasePkgRptWrkForDelete", ssmParam);
//        for (Map<String, Object> result : resultList) {
//            LEASE_PKG_RPT_WRKTMsg delRptWrkTMsg = new LEASE_PKG_RPT_WRKTMsg();
//            ZYPEZDItemValueSetter.setValue(delRptWrkTMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(delRptWrkTMsg.leasePkgRptWrkPk, (BigDecimal) result.get(COL_LEASE_PKG_RPT_WRK_PK));
//
//            EZDTBLAccessor.logicalRemove(delRptWrkTMsg);
//        }
        // 2016/11/10 Unit Test#201-50 Add End
        // 2018/02/22 QC#24315 Del End

        // Insert LEASE_PKG_RPT_WRK
        LEASE_PKG_RPT_WRKTMsg rptWrkTMsg = new LEASE_PKG_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.glblCmpyCd, this.glblCmpyCd);

        BigDecimal leasePkgRptWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.LEASE_PKG_RPT_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.leasePkgRptWrkPk, leasePkgRptWrkPk);

        //QC#23439
        //ZYPEZDItemValueSetter.setValue(rptWrkTMsg.leasePkgPrintTxt, leasePackageID);
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.leasePkgPrintTxt, String.valueOf(rs.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK)));

        String sysDt = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cratDtPrintTxt, sysDt);
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cfsPoNum, rs.getString(COL_CFS_PO_NUM));
        // 2022/09/30 QC#60253 Mod Start 
        if(!ZYPCommonFunc.hasValue(rs.getString(COL_CFS_APP_NUM))){
            rptWrkTMsg.cfsAppNum.setValue(CFS_APP_NUM_TENTATIVE_VAL);
        } else {
            ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cfsAppNum, rs.getString(COL_CFS_APP_NUM));
        }
//        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cfsAppNum, rs.getString(COL_CFS_APP_NUM));
        // 2022/09/30 QC#60253 Mod End 
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.dsAcctNm, rs.getString(COL_DS_ACCT_NM));
        // ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cfsRepTocTxt, rs.getString(COL_SLS_REP_TOC_CD));
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cfsRepTocTxt, rs.getString(COL_TOC_NM)); // 2018/01/22 s21_NA#23439 Mod
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cfsPoAmtPrintTxt, amountFormatter(rs.getBigDecimal(COL_CFS_PO_AMT)));
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.invTotAmtPrintTxt, amountFormatter(rs.getBigDecimal(COL_INV_CPLT_TOT_DEAL_NET_AMT)));
        // 2022/09/30 QC#60253 Mod Start
        //QC#24318
//        String invPctPrintTxt = amountFormatter(rs.getBigDecimal(COL_INV_CPLT_AMT_RATE));
        String invPctPrintTxt = null;
        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(COL_CFS_PO_AMT))) {
            invPctPrintTxt = amountFormatter(rs.getBigDecimal(COL_INV_CPLT_AMT_RATE));
        }
        // 2022/09/30 QC#60253 Mod End
        if (ZYPCommonFunc.hasValue(invPctPrintTxt) && invPctPrintTxt.length() > 7) {
            invPctPrintTxt = this.maxPctStr;
        }
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.invPctPrintTxt, invPctPrintTxt);
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.cfsOwnrTxt, rs.getString(COL_LEASE_CMPY_USR_NM));
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.csaOrdProcTxt, rs.getString(COL_PSN_NM));
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.invCntPrintTxt, rs.getString(COL_DTL_CNT));
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.invNum, rs.getString(COL_INV_NUM));
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.invAmtPrintTxt, amountFormatter(rs.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT)));
        ZYPEZDItemValueSetter.setValue(rptWrkTMsg.glDtPrintTxt, formatDt(rs.getString(COL_GL_DT)));

        EZDTBLAccessor.insert(rptWrkTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rptWrkTMsg.getReturnCode())) {
            String[] paramArray = new String[] {"LEASE_PKG_RPT_WRK", String.format("PO_NUM = %s, INV_NUM = '%s'", rs.getString(COL_CFS_PO_NUM), rs.getString(COL_INV_NUM))};
            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        return true;
    }

    /**
     * updateCfsLeasePkgPoHdr
     * @param poHdrPk BigDecimal
     */
    private boolean updateCfsLeasePkgPoHdr(BigDecimal poHdrPk) {

        // update CFS_LEASE_PKG_PO_HDR
        CFS_LEASE_PKG_PO_HDRTMsg cfsLeasePkgPoHdrTMsg = new CFS_LEASE_PKG_PO_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(cfsLeasePkgPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cfsLeasePkgPoHdrTMsg.cfsLeasePkgPoHdrPk, poHdrPk);

        cfsLeasePkgPoHdrTMsg = (CFS_LEASE_PKG_PO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(cfsLeasePkgPoHdrTMsg);
        if (cfsLeasePkgPoHdrTMsg == null) {
            String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", nvlBigDecimalToString(poHdrPk).toString())};
            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        // 2018/01/09 QC#21706 Mod Start
        //ZYPEZDItemValueSetter.setValue(cfsLeasePkgPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cfsLeasePkgPoHdrTMsg.leasePkgCratFlg, ZYPConstant.FLG_ON_Y);
        // 2018/01/09 QC#21706 Mod End
        EZDTBLAccessor.update(cfsLeasePkgPoHdrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePkgPoHdrTMsg.getReturnCode())) {
            String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", nvlBigDecimalToString(poHdrPk).toString())};
            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        return true;
    }

    /**
     * writeLogLn
     * @param format String
     * @param param Object...
     */
    private static void writeLogLn(String format, Object... param) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", BIZ_APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    /**
     * amountFormatter
     * @param val BigDecimal
     * @return String
     */
    private static String amountFormatter(BigDecimal val) {

        if (!ZYPCommonFunc.hasValue(val)) {
            return new String();
        }

        DecimalFormat format = new DecimalFormat(BIGDECIMAL_FORMAT);

        format.setMinimumFractionDigits(AMT_FRACTION_DIGIT);
        format.setMaximumFractionDigits(AMT_FRACTION_DIGIT);

        return format.format(val);
    }

    /**
     * nvlBigDecimalToString
     * @param val BigDecimal
     * @return String
     */
    private static String nvlBigDecimalToString(BigDecimal val) {

        if (!ZYPCommonFunc.hasValue(val)) {
            return NULL_BIGDECIMAL_REPLACE_CHAR;
        }

        return val.toString();
    }

    /**
     * nvlString
     * @param val String
     * @return String
     */
    private static String nvlString(String val) {

        if (!ZYPCommonFunc.hasValue(val)) {
            return NULL_STRING_REPLACE_CHAR;
        }

        return val;
    }

    /**
     * formatDt
     * @param dt String
     * @return String formated
     */
    private static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }

    private Document createXmlFileHeader(ResultSet rs) throws SQLException {
        // 2016/09/08 QC#15326 Del Start
//        String crlf = System.getProperty("file.separator");
//
//        this.fileName = this.fileLocPath + crlf + rs.getString(COL_CFS_APP_NUM) + HYPHEN + rs.getString(COL_CFS_PO_NUM) + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        // 2016/09/08 QC#15326 Del End

        Document document = builder.newDocument();

        // Lease Package
        Element rootElement = document.createElement(XML_ELEMENT_LEASE_PACKAGE);
        document.appendChild(rootElement);

        // Package Location
        Element element = document.createElement(XML_ELEMENT_PKG_LOC);
        rootElement.appendChild(element);

        // Package Location Value
        Text childtextContents1 = document.createTextNode(this.fileLocPath);
        element.appendChild(childtextContents1);

        // PoNumber
        Element poNumElement = document.createElement(XML_ELEMENT_PO_NUM);
        rootElement.appendChild(poNumElement);

        // Po Number Value
        Text poNumtext = document.createTextNode(nvlString(rs.getString(COL_CFS_PO_NUM)));
        poNumElement.appendChild(poNumtext);

        // Ship To
        Element shipToElement = document.createElement(XML_ELEMENT_SHIP_TO);
        rootElement.appendChild(shipToElement);

        // Ship To Value
        Text shipTotext = document.createTextNode(nvlString(rs.getString(COL_DS_ACCT_NM)));
        shipToElement.appendChild(shipTotext);

        // Cfs Owner Name
        Element cfsOwnNmElement = document.createElement(XML_ELEMENT_CFS_OWN_NM);
        rootElement.appendChild(cfsOwnNmElement);

        // Cfs Owner Name Value
        Text cfsOwnNmtext = document.createTextNode(nvlString(rs.getString(COL_LEASE_CMPY_USR_NM)));
        cfsOwnNmElement.appendChild(cfsOwnNmtext);

        // Cfs Owner Id
        Element cfsOwnIdElement = document.createElement(XML_ELEMENT_CFS_OWN_ID);
        rootElement.appendChild(cfsOwnIdElement);

        // Cfs Owner Id Value
        Text cfsOwnIdtext = document.createTextNode(nvlString(rs.getString(COL_LEASE_CMPY_USR_ID)));
        cfsOwnIdElement.appendChild(cfsOwnIdtext);

        // Invoice List
        Element invListElement = document.createElement(XML_ELEMENT_INV_LIST);
        rootElement.appendChild(invListElement);

        document = setXmlInvList(document, rs);

        return document;
    }

    private Document setXmlInvList(Document document, ResultSet rs) throws SQLException {

        // Lease Package Element
        Element leasePkgElement = (Element) document.getFirstChild();
        // Invoice List Element
        Element invListElement = (Element) leasePkgElement.getLastChild();

        // Invoice
        Element invElement = document.createElement(XML_ELEMENT_INV);
        invListElement.appendChild(invElement);

        // Order Number
        Element ordNumElement = document.createElement(XML_ELEMENT_ORD_NUM);
        invElement.appendChild(ordNumElement);

        // Order Number Value
        Text ordNumText = document.createTextNode(nvlString(rs.getString(COL_CPO_ORD_NUM)));
        ordNumElement.appendChild(ordNumText);

        // Invoice Number
        Element invNumElement = document.createElement(XML_ELEMENT_INV_NUM);
        invElement.appendChild(invNumElement);

        // Invoice Number Value
        Text invNumText = document.createTextNode(nvlString(rs.getString(COL_INV_NUM)));
        invNumElement.appendChild(invNumText);

        // Invoice Date
        Element invDtElement = document.createElement(XML_ELEMENT_INV_DT);
        invElement.appendChild(invDtElement);

        // Invoice Date Value
        String invDt = ZYPDateUtil.convertFormat(nvlString(rs.getString(COL_GL_DT)), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, HYPHEN);
        Text invDtText = document.createTextNode(invDt);
        invDtElement.appendChild(invDtText);

        // Amount
        Element amtElement = document.createElement(XML_ELEMENT_AMT);
        invElement.appendChild(amtElement);

        // Amount Value
        Text amtText = document.createTextNode(nvlBigDecimalToString(rs.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT)));
        amtElement.appendChild(amtText);

        return document;
    }

    private void writeXmlDocument(Document document) throws Exception {

        File xmlFile = new File(this.fileName + XML_EXTENSION);
        FileOutputStream fos = new FileOutputStream(xmlFile);
        StreamResult result = new StreamResult(fos);

        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();

        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty("standalone", "no");
        transformer.setOutputProperty("indent", "yes");
        // 2016/09/08 QC#14068 Del Start
//        transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "4");
        // 2016/09/08 QC#14068 Del End

        DOMSource source = new DOMSource(document);

        transformer.transform(source, result);

        fos.close();
    }

    private List<S21ReportRequestBean> createCoverRequest(ResultSet rs, List<S21ReportRequestBean> requestList) throws SQLException {
        // 2016/09/08 QC#15326 Add Start
//        String crlf = System.getProperty("file.separator");
        
        // 2022/09/30 QC#60253 Add Start
        //check APP_NUM
        String checkedAppNum = null;
        if (ZYPCommonFunc.hasValue(rs.getString(COL_CFS_APP_NUM))) {
            checkedAppNum = checkPoNumFormat(rs.getString(COL_CFS_APP_NUM));
        }
        //check PO_NUM
        String checkedPoNum = checkPoNumFormat(rs.getString(COL_CFS_PO_NUM));
        // 2022/09/30 QC#60253 Add End

        // 2022/09/30 QC#60253 Mod Start
        if (!ZYPCommonFunc.hasValue(rs.getString(COL_CFS_APP_NUM))) {
            this.fileName = checkedPoNum + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        } else {
            this.fileName = checkedAppNum + HYPHEN + checkedPoNum + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        }
//        this.fileName = rs.getString(COL_CFS_APP_NUM) + HYPHEN + rs.getString(COL_CFS_PO_NUM) + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
//        this.fileName = rs.getString(COL_CFS_APP_NUM) + HYPHEN + checkedPoNum + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        // 2022/09/30 QC#60253 Mod End
        
        // 2016/09/08 QC#15326 Add End
        requestList = new ArrayList<S21ReportRequestBean>();

        // Cover
        S21ReportRequestBean coverRequest = new S21ReportRequestBean(COVER_REPORT_ID);
        coverRequest.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        // 2019/05/21 QC#50398 Mod Start
        coverRequest.setRptArcFlg(true);
//        coverRequest.setRptArcFlg(false);
        // 2019/05/21 QC#50398 Mod End
        // 2022/09/30 QC#60253 Mod Start
        String rptTtl;
        if (!ZYPCommonFunc.hasValue(rs.getString(COL_CFS_APP_NUM))) {
            rptTtl = rs.getString(COL_CFS_PO_NUM) + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        } else {
            rptTtl = rs.getString(COL_CFS_APP_NUM) + HYPHEN + rs.getString(COL_CFS_PO_NUM) + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        }
        //String rptTtl = rs.getString(COL_CFS_APP_NUM) + HYPHEN + rs.getString(COL_CFS_PO_NUM) + UNDERSCORE + ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        coverRequest.setRptTtlNm(rptTtl);
        S21InputParameter coverInputParam = coverRequest.getInputParamBeanInstance();
        coverInputParam.addReportParameter(RPT_PRM_GLBL_CMPY_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(rs.getString(COL_CFS_APP_NUM))) {
            coverInputParam.addReportParameter(RPT_PRM_CFS_APP_NUM, CFS_APP_NUM_TENTATIVE_VAL);
        } else {
            coverInputParam.addReportParameter(RPT_PRM_CFS_APP_NUM, rs.getString(COL_CFS_APP_NUM));
        }
//        coverInputParam.addReportParameter(RPT_PRM_CFS_APP_NUM, rs.getString(COL_CFS_APP_NUM));
        // 2022/09/30 QC#60253 Mod End
        coverInputParam.addReportParameter(RPT_PRM_CFS_PO_NUM, rs.getString(COL_CFS_PO_NUM));
        coverInputParam.addReportParameter(RPT_PRM_INTL_LANG_VAL_COL_NM, coverInputParam.getSystemDefaultLanguage());

        coverRequest.setInputParamBean(coverInputParam);
        coverRequest.setSubsysCd("NWC");

        requestList.add(coverRequest);

        requestList = createInvRptRequest(rs, requestList);

        return requestList;
    }

    private List<S21ReportRequestBean> createInvRptRequest(ResultSet rs, List<S21ReportRequestBean> requestList) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("invNum", rs.getString(COL_INV_NUM));

        List<Map<String, Object>> resultList = ssmBatchClient.queryObjectList("getInvRptCtrl", ssmParam);

        S21ReportRequestBean invRptRequest = null;
        S21InputParameter invRptInputParam = null;
        for (Map<String, Object> result : resultList) {
            invRptRequest = new S21ReportRequestBean(INVOICE_REPORT_ID);
            invRptRequest.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
            invRptRequest.setRptArcFlg(false);

            invRptInputParam = invRptRequest.getInputParamBeanInstance();
            invRptInputParam.addReportParameter(RPT_PRM_GLBL_CMPY_CD, this.glblCmpyCd);
            invRptInputParam.addReportParameter(RPT_PRM_INV_BILL_NUM, nvlString((String) result.get(COL_INV_NUM)));
            invRptInputParam.addReportParameter(RPT_PRM_INTL_LANG_VAL_COL_NM, invRptInputParam.getSystemDefaultLanguage());
            invRptInputParam.addReportParameter(RPT_PRM_CONSL_BILL_FLG, nvlString((String) result.get(COL_CONSL_BILL_FLG)));
            invRptInputParam.addReportParameter(RPT_PRM_INV_PRT_CTRL_PK, nvlBigDecimalToString((BigDecimal) result.get(COL_INV_PRT_CTRL_PK)));

            invRptRequest.setInputParamBean(invRptInputParam);
            invRptRequest.setSubsysCd("NWC");

            requestList.add(invRptRequest);
        }

        return requestList;
    }

    private void deleteLeasePkgPrtWrk(String cfsAppNum, String cfsPoNum) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cfsAppNum", cfsAppNum);
        ssmParam.put("cfsPoNum", cfsPoNum);

        List<Map<String, Object>> resultList = ssmBatchClient.queryObjectList("getLeasePkgRptWrkForDelete", ssmParam);
        for (Map<String, Object> result : resultList) {
            LEASE_PKG_RPT_WRKTMsg delRptWrkTMsg = new LEASE_PKG_RPT_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(delRptWrkTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(delRptWrkTMsg.leasePkgRptWrkPk, (BigDecimal) result.get(COL_LEASE_PKG_RPT_WRK_PK));

            EZDTBLAccessor.logicalRemove(delRptWrkTMsg);
        }
        // 2022/09/30 QC#60253 Add Start
        LEASE_PKG_RPT_WRKTMsg delRptWrkTMsg = new LEASE_PKG_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(delRptWrkTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(delRptWrkTMsg.leasePkgRptWrkPk, (BigDecimal) null);
        EZDTBLAccessor.logicalRemove(delRptWrkTMsg);
        // 2022/09/30 QC#60253 Add End
    }

    // 2022/09/30 QC#60253 Add Start
    private String checkPoNumFormat(String poNum) {
        poNum = poNum.replace("\\", "-");
        poNum = poNum.replace("/", "-");
        poNum = poNum.replace(":", "-");
        poNum = poNum.replace("*", "-");
        poNum = poNum.replace("?", "-");
        poNum = poNum.replace("\"", "-");
        poNum = poNum.replace("<", "-");
        poNum = poNum.replace(">", "-");
        poNum = poNum.replace("|", "-");
        return poNum;
    }
    // 2022/09/30 QC#60253 Add End
}
