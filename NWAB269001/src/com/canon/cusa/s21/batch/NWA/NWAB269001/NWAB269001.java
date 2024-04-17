package com.canon.cusa.s21.batch.NWA.NWAB269001;

import static com.canon.cusa.s21.batch.NWA.NWAB269001.constant.NWAB269001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NWA.NWAB269001.constant.NWAB269001Constant.PROGRAM_ID;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryParams;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryResponse;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataResponse;
import net.therefore.schemas.webservices.interop.v0001.types.QueryObject;
import net.therefore.schemas.webservices.interop.v0001.types.WSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataDate;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataInt;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.WSQueryResultRow;
import parts.common.EZDSystemEnv;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_FLDTMsg;
import business.db.DOC_MGT_SCAN_TRXTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB269001.constant.NWAB269001Constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;

/**
 * <pre>
 * Therefore Create Relation For Document and Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 03/19/2018   Fujitsu         R.Nakamura      Create          QC#23991
 *</pre>
 */

public class NWAB269001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** success count */
    private int successCnt = 0;

    /** error count */
    private int errorCnt = 0;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB269001().executeBatch(NWAB269001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        // Global company code
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            // [@] is mandatory.
            throw new S21AbendException("ZZZM9025E", new String[] {"Global Company Code" });
        }
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, successCnt, errorCnt, successCnt + errorCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", CPO_SRC_TP.SOM);
        ssmParam.put("imptStsCdList", new String[] {IMPT_STS.NOT_PROCESSED, IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED, IMPT_STS.ACCEPTED });

        List<String> aquNumList = (List<String>) this.ssmBatchClient.queryObjectList("getSearchAquNum", ssmParam);

        if (null != aquNumList && aquNumList.size() > 0) {
            for (String aquNum : aquNumList) {
                insertDocMgtScanTrx(aquNum);
            }
        }
    }

    /**
     * insertDocMgtScanTrx
     * @param aquNum String
     */
    private void insertDocMgtScanTrx(String aquNum) {

        writeStartLogLn("thereforeDocumentAttach", aquNum);
        try {

            String thereforeConnAvalFlg = ZYPCodeDataUtil.getVarCharConstValue("THEREFORE_CONN_AVAL_FLG", this.glblCmpyCd);
            if (!ZYPConstant.FLG_ON_Y.equals(thereforeConnAvalFlg)) {

                S21InfoLogOutput.println("NWAB269001 Therefore process was skipped. THEREFORE_CONN_AVAL_FLG is 'N'.");
                return;
            }

            Integer thereforeDocId;
            IThereforeService thereforeWebService = S21ThereforeWebServiceProxy.getInstance().getThereforeService();

            if (ZYPCommonFunc.hasValue(aquNum)) {

                thereforeDocId = callThereforeWebService(thereforeWebService, aquNum);

                if (thereforeDocId != null) {
                    writeLogLn("therefore Doc Id : %d", thereforeDocId);
                    String scanTime = "";
                    BigDecimal leaseDocId = null; // Lease Document ID
                    Map<String, Object> thereforeValMap = getScanTimeFromTherefore(thereforeWebService, thereforeDocId);
                    if (thereforeValMap != null) {
                        if (thereforeValMap.get("SCANNED_DATE") != null) {
                            scanTime = (String) thereforeValMap.get("SCANNED_DATE");
                        }
                        if (thereforeValMap.get("LEASE_DOC_ID") != null) {
                            leaseDocId = (BigDecimal) thereforeValMap.get("LEASE_DOC_ID");
                        }
                    }

                    DOC_MGT_SCAN_TRXTMsg docMgtScanTrxTMsg = new DOC_MGT_SCAN_TRXTMsg();

                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.glblCmpyCd, this.glblCmpyCd);
                    BigDecimal docMgtScanTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_SCAN_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtScanTrxPk, docMgtScanTrxPk);
                    // docMgtScanTrxTMsg.cpoOrdNum is not set.
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtDocId, new BigDecimal(thereforeDocId));
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtScanTs, scanTime);
                    /** Therefore URL */
                    String thereforeUrl = EZDSystemEnv.getString("S21.therfore.attachment.url");
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.othSysUrl, thereforeUrl + thereforeDocId);
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
                    // docMgtScanTrxTMsg.docMgtBizDocNum is not set.
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.aquNum, aquNum);

                    if (ZYPCommonFunc.hasValue(leaseDocId)) {
                        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.poRvwDocId, leaseDocId);
                        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.poRvwSysUrl, thereforeUrl + leaseDocId);
                    }

                    EZDTBLAccessor.insert(docMgtScanTrxTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(docMgtScanTrxTMsg.getReturnCode())) {
                        this.termCd = TERM_CD.ABNORMAL_END;
                        throw new S21AbendException(MSG_ID.NWBM0118E.toString(), new String[] {"DOC_MGT_SCAN_TRX" });
                    }

                    commit();
                    this.successCnt++;
                } else {
                    writeLogLn("DOC_MGT_SCAN_TRX is not create because THEREFORE Document ID can't get");
                }
            }

        } finally {

            if (null != this.termCd) {
                this.termCd = TERM_CD.NORMAL_END;
            }
            writeEndLogLn("thereforeDocumentAttach", aquNum);
        }
    }

    /**
     * callThereforeWebService
     * @param thereforWebSerive IThereforeService
     * @param aquNum String
     * @return Integer
     */
    private Integer callThereforeWebService(IThereforeService thereforWebSerive, String aquNum) {

        writeStartLogLn("callThereforeWebService", aquNum);

        try {

            // call Therefore Web Service
            Integer thereforeDocId = null;

            DOC_MGT_CATGTMsg catg = new DOC_MGT_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(catg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(catg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
            catg = (DOC_MGT_CATGTMsg) S21FastTBLAccessor.findByKey(catg);
            if (catg == null || !ZYPCommonFunc.hasValue(catg.docMgtCatgNum)) {

                // null return
                return thereforeDocId;
            }

            String searchCondFldCd = ZYPCodeDataUtil.getVarCharConstValue("THEREFORE_COND_FLD", this.glblCmpyCd);
            if (!ZYPCommonFunc.hasValue(searchCondFldCd)) {

                // null return
                return thereforeDocId;
            }

            DOC_MGT_FLDTMsg fld = new DOC_MGT_FLDTMsg();
            ZYPEZDItemValueSetter.setValue(fld.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(fld.docMgtFldCd, searchCondFldCd);
            fld = (DOC_MGT_FLDTMsg) S21FastTBLAccessor.findByKey(fld);
            if (fld == null || !ZYPCommonFunc.hasValue(fld.docMgtFldNum)) {

                // null return
                return thereforeDocId;
            }

            // Set parameters
            ExecuteSingleQueryParams params = new ExecuteSingleQueryParams();
            // category
            QueryObject queryObject = new QueryObject();
            ArrayOfWSCondition conditions = new ArrayOfWSCondition();
            queryObject.setConditions(conditions);
            queryObject.setCategoryNo(catg.docMgtCatgNum.getValueInt());
            params.setQuery(queryObject);

            // condition
            WSCondition condition = new WSCondition();
            condition.setCondition(aquNum);

            condition.setFieldNoOrName(fld.docMgtFldNum.getValue().toPlainString());
            conditions.getWSCondition().add(condition);

            // Call Web Service
            ExecuteSingleQueryResponse response = thereforWebSerive.executeSingleQuery(params);

            java.util.List<WSQueryResultRow> rows = response.getQueryResult().getResultRows().getWSQueryResultRow();
            if (rows.size() > 0) {

                thereforeDocId = rows.get(0).getDocNo();
            }

            // null return
            return thereforeDocId;
        } finally {

            writeEndLogLn("callThereforeWebService", aquNum);
        }
    }

    /**
     * getScanTimeFromTherefore
     * @param thereforeWebService IThereforeService
     * @param docMgtDocId int
     * @return Map<String, Object>
     */
    private Map<String, Object> getScanTimeFromTherefore(IThereforeService thereforeWebService, int docMgtDocId) {
        GetDocumentIndexDataParams docParam = new GetDocumentIndexDataParams();
        docParam.setDocNo(docMgtDocId);

        GetDocumentIndexDataResponse docRes = thereforeWebService.getDocumentIndexData(docParam);
        ArrayOfWSIndexDataItem arrayItems = docRes.getIndexData().getIndexDataItems();
        List<WSIndexDataItem> itemList = arrayItems.getWSIndexDataItem();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("docMgtCatgCd", DOC_MGT_CATG.SALES_ORDER);
        ssmParam.put("docMgtFldDescTxtList", new String[] {"Scan Date", "Scanned Date", "Lease Document ID" });

        List<Map<String, Object>> resultSsmResult = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSearchDocMgtFld", ssmParam);
        if (resultSsmResult == null || resultSsmResult.isEmpty()) {
            return null;
        }

        Map<String, Object> retMap = new HashMap<String, Object>();
        String scanTime = null;
        for (Map<String, Object> result : resultSsmResult) {
            for (WSIndexDataItem item : itemList) {
                if ("Lease Document ID".equals(result.get("DOC_MGT_FLD_DESC_TXT"))) {
                    WSIndexDataInt leaseDocIdData = item.getIntIndexData();
                    if (leaseDocIdData == null) {
                        continue;
                    }
                    int leaseDocIdFldNum = leaseDocIdData.getFieldNo();
                    int defFldNum = Integer.valueOf(result.get("DOC_MGT_FLD_NUM").toString());
                    if (leaseDocIdFldNum != defFldNum) {
                        continue;
                    }
                    if (leaseDocIdData.getDataValue() != null) {
                        BigDecimal val = new BigDecimal(leaseDocIdData.getDataValue().toString());
                        retMap.put("LEASE_DOC_ID", val);
                    }
                } else if ("Scan Date".equals(result.get("DOC_MGT_FLD_DESC_TXT")) || "Scanned Date".equals(result.get("DOC_MGT_FLD_DESC_TXT"))) {
                    WSIndexDataDate date = item.getDateIndexData();
                    if (date == null) {
                        continue;
                    }

                    int dateFldNum = date.getFieldNo();
                    int defFldNum = Integer.valueOf(result.get("DOC_MGT_FLD_NUM").toString());
                    if (dateFldNum != defFldNum) {
                        continue;
                    }

                    XMLGregorianCalendar greCalDate = date.getDataValue();
                    if (greCalDate == null) {
                        continue;
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getYear()), 4, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getMonth()), 2, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getDay()), 2, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getHour()), 2, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getMinute()), 2, "0"));
                    sb.append(ZYPCommonFunc.rightPad(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getSecond()), 2, "0"), 5, "0"));
                    scanTime = sb.toString();
                    retMap.put("SCANNED_DATE", scanTime);
                }
            }
        }

        return retMap;
    }

    /**
     * writeStartLogLn
     * @param methodNm String
     * @param target Object
     */
    private static void writeStartLogLn(String methodNm, Object target) {
        // writeLogLn("[START] %s(%s)", methodNm);
    }

    /**
     * writeEndLogLn
     * @param methodNm String
     * @param target Object
     */
    private static void writeEndLogLn(String methodNm, Object target) {
        // writeLogLn("[END] %s(%s)\r\n", methodNm);
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private static void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", PROGRAM_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }
}
