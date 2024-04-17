/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC230001;

import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.CHECKIN_COMMNT;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.COL_DOC_MGT_CATG_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.COL_DOC_MGT_FLD_DESC_TXT;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.COL_DOC_MGT_FLD_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.COL_DOC_MGT_FLD_TP_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.COL_DOC_MGT_ORG_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.COL_DOC_MGT_SCAN_BR_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.COL_IDX_ATTRB_TRGT_COL_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.DATE_LENGTH;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.DELETE_MODE;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.GET_SEARCH_CONDITION_FLD_NM_LIST;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM1939E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM1941E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM1981E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM2001E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM2216E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM2217E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.NWZM2218E;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.THEREFORE_CONN_AVAL_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC230001.constant.NWZC230001Constant.UPDATE_MODE;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSIndexDataItemQuick;
import net.therefore.schemas.webservices.interop.v0001.types.CreateDocumentParams;
import net.therefore.schemas.webservices.interop.v0001.types.CreateDocumentResponse;
import net.therefore.schemas.webservices.interop.v0001.types.DeleteDocumentParams;
import net.therefore.schemas.webservices.interop.v0001.types.DeleteDocumentResponse;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryParams;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryResponse;
import net.therefore.schemas.webservices.interop.v0001.types.QueryObject;
import net.therefore.schemas.webservices.interop.v0001.types.SaveDocumentIndexDataQuickParams;
import net.therefore.schemas.webservices.interop.v0001.types.SaveDocumentIndexDataQuickResponse;
import net.therefore.schemas.webservices.interop.v0001.types.WSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataDate;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataInt;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataItemQuick;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataLogical;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataMoney;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataString;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataToPut;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataToPutQuick;
import net.therefore.schemas.webservices.interop.v0001.types.WSQueryResultRow;
import parts.dbcommon.EZDDBRecordLockedException;
import business.parts.NWZC230001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_FLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;

/**
 * <pre>
 * Therefore Master Document Maintenance API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/11   Fujitsu         T.Aoi           Create          QC#16740
 *</pre>
 */
public class NWZC230001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** SSM client */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * Constructor
     */
    public NWZC230001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Therefore Master Document Maintenance API (List)
     * </pre>
     * @param pMsgList Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NWZC230001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NWZC230001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * <pre>
     * Therefore Master Document Maintenance API
     * </pre>
     * @param pMsg Input parameter.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(NWZC230001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        this.msgMap = new S21ApiMessageMap(pMsg);

        try {

            if (!checkIdxAttrbMandatory()) {
                return;
            }

            String thereforeConnAvalFlg = ZYPCodeDataUtil.getVarCharConstValue(THEREFORE_CONN_AVAL_FLG, pMsg.glblCmpyCd.getValue());
            if (!ZYPConstant.FLG_ON_Y.equals(thereforeConnAvalFlg)) {
                pMsg.docMgtIntfcStsCd.setValue(DOC_MGT_INTFC_STS.ERROR);
                return;
            }

            Map<String, Object> docMgtBrResult = getDocMgtBr(pMsg);
            if (docMgtBrResult == null) {
                this.msgMap.addXxMsgId(NWZM2217E);
                return;
            }

            List<Map<String, Object>> idxAttrbResultList = getIdxAttrbInfo(pMsg);
            if (idxAttrbResultList == null || idxAttrbResultList.size() == 0) {
                this.msgMap.addXxMsgId(NWZM2216E); //
                return;
            }

            // Process Mode
            if (UPDATE_MODE.equals(pMsg.procModeCd.getValue())) {
                saveDocumentProcess(pMsg, docMgtBrResult, idxAttrbResultList);
            } else if (DELETE_MODE.equals(pMsg.procModeCd.getValue())) {
                deleteDocumentProcess(pMsg, docMgtBrResult, idxAttrbResultList);
            } else {
                createDocumentProcess(pMsg, docMgtBrResult, idxAttrbResultList);
            }

        } catch (EZDDBRecordLockedException e) {
            throw e;
        } catch (SOAPFaultException e) {
            StackTraceElement[] steAry = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (StackTraceElement ste : steAry) {
                sb.append(ste.toString());
                sb.append("\n");
            }
            S21InfoLogOutput.println(sb.toString());
        } catch (Exception e) {
            throw new S21AbendException(e);
        } finally {
            // Flush Message Map.
            this.msgMap.flush();
        }
    }

    /**
     * <pre>
     * SaveDocument
     * </pre>
     * @param pMsg NWZC230001PMsg
     * @param docMgtBrResult Document Management Branch Result
     * @param idxAttrbResultList Index Attribute Result List
     * @throws Exception
     */
    private void saveDocumentProcess(NWZC230001PMsg pMsg, Map<String, Object> docMgtBrResult, List<Map<String, Object>> idxAttrbResultList) throws Exception {

        // Set parameters
        Integer thereforeDocId = callThereforeApiForSearch(pMsg, idxAttrbResultList, docMgtBrResult);
        if (thereforeDocId == null) {
            this.msgMap.addXxMsgId(NWZM2218E);
            return;
        }

        SaveDocumentIndexDataQuickParams param = new SaveDocumentIndexDataQuickParams();
        param.setCheckInComments(CHECKIN_COMMNT);
        param.setDocNo(thereforeDocId);

        WSIndexDataToPutQuick additional = new WSIndexDataToPutQuick();
        ArrayOfWSIndexDataItemQuick arrayItems = new ArrayOfWSIndexDataItemQuick();
        additional.setIndexDataItems(arrayItems);

        for (Map<String, Object> idxAttrbResult : idxAttrbResultList) {
            String valTxt = "";
            if (docMgtBrResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null) {
                valTxt = docMgtBrResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
            }

            WSIndexDataItemQuick item = new WSIndexDataItemQuick();

            String docMgtFldTpCd = idxAttrbResult.get(COL_DOC_MGT_FLD_TP_CD).toString();
            int docMgtFldNum = Integer.valueOf(idxAttrbResult.get(COL_DOC_MGT_FLD_NUM).toString());

            if (DOC_MGT_FLD_TP.STRING.equals(docMgtFldTpCd)) {
                WSIndexDataString data = new WSIndexDataString();
                data.setFieldNo(docMgtFldNum);
                data.setDataValue(valTxt);
                item.setStringIndexData(data);
            } else if (DOC_MGT_FLD_TP.INTEGER.equals(docMgtFldTpCd)) {
                WSIndexDataInt data = new WSIndexDataInt();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    data.setDataValue(new Integer(valTxt));
                } else {
                    data.setDataValue(null);
                }
                item.setIntIndexData(data);
            } else if (DOC_MGT_FLD_TP.MONEY.equals(docMgtFldTpCd)) {
                WSIndexDataMoney data = new WSIndexDataMoney();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    data.setDataValue(new Double(valTxt));
                } else {
                    data.setDataValue(null);
                }
                item.setMoneyIndexData(data);
            } else if (DOC_MGT_FLD_TP.DATE.equals(docMgtFldTpCd)) {
                WSIndexDataDate data = new WSIndexDataDate();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    valTxt = formatDate(ZYPDateUtil.addDays(valTxt, 1));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTime(sdf.parse(valTxt));
                    data.setDataValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                            c.get(Calendar.SECOND), c.get(Calendar.MILLISECOND), 0));
                    S21InfoLogOutput.println("NWZC230001 Updating Date is " + data.getDataValue().toString() + "(" + docMgtFldNum + ").");
                } else {
                    data.setDataValue(null);
                }
                item.setDateIndexData(data);
            } else if (DOC_MGT_FLD_TP.LOGICAL.equals(docMgtFldTpCd)) {
                WSIndexDataLogical data = new WSIndexDataLogical();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    data.setDataValue(Boolean.valueOf(valTxt));
                } else {
                    data.setDataValue(null);
                }
                item.setLogicalIndexData(data);
            }
            arrayItems.getWSIndexDataItemQuick().add(item);
        }
        param.setIndexData(additional);

        // WebService Normal End
        SaveDocumentIndexDataQuickResponse response = null;

        try {
            // Invoke Therefore web service
            response = S21ThereforeWebServiceProxy.getInstance().getThereforeService().saveDocumentIndexDataQuick(param);
            pMsg.docMgtIntfcStsCd.setValue(DOC_MGT_INTFC_STS.NEW);

        } catch (SOAPFaultException e) {
            S21InfoLogOutput.println(e.getMessage());
            pMsg.docMgtIntfcStsCd.setValue(DOC_MGT_INTFC_STS.ERROR);

        } catch (Exception e) {
            S21InfoLogOutput.println(e.getMessage());
            throw new S21AbendException(e);

        } finally {

            if (response != null) {
                XMLGregorianCalendar xmlGCal = response.getLastChangeTime();
                pMsg.docMgtIntfcProcTs.setValue(convertDt(xmlGCal));
            }
        }
    }

    /**
     * <pre>
     * DeleteDocument
     * </pre>
     * @param pMsg NWZC230001PMsg
     * @param docMgtBrResult Document Management Branch Result
     * @param idxAttrbResultList Index Attribute Result List
     */
    private void deleteDocumentProcess(NWZC230001PMsg pMsg, Map<String, Object> docMgtBrResult, List<Map<String, Object>> idxAttrbResultList) {

        // Set parameters
        Integer thereforeDocId = callThereforeApiForSearch(pMsg, idxAttrbResultList, docMgtBrResult);
        if (thereforeDocId == null) {
            this.msgMap.addXxMsgId(NWZM2218E);
            return;
        }

        DeleteDocumentParams param = new DeleteDocumentParams();
        param.setDocNo(thereforeDocId);

        // Invoke Therefore web service
        DeleteDocumentResponse response = null;
        try {

            // Invoke Therefore web service
            response = S21ThereforeWebServiceProxy.getInstance().getThereforeService().deleteDocument(param);
            pMsg.docMgtIntfcStsCd.setValue(DOC_MGT_INTFC_STS.NEW);

        } catch (SOAPFaultException e) {
            S21InfoLogOutput.println(e.getMessage());
            pMsg.docMgtIntfcStsCd.setValue(DOC_MGT_INTFC_STS.ERROR);

        } catch (Exception e) {
            S21InfoLogOutput.println(e.getMessage());
            throw new S21AbendException(e);
        }
    }

    /**
     * <pre>
     * CreateDocument
     * </pre>
     * @param pMsg NWZC230001PMsg
     * @param docMgtBrResult Document Management Branch Result
     * @param idxAttrbResultList Index Attribute Result List
     * @throws Exception
     */
    private void createDocumentProcess(NWZC230001PMsg pMsg, Map<String, Object> docMgtBrResult, List<Map<String, Object>> idxAttrbResultList) throws Exception {

        // Set parameters
        CreateDocumentParams param = new CreateDocumentParams();
        param.setCheckInComments(CHECKIN_COMMNT);
        BigDecimal categoryNo = (BigDecimal) idxAttrbResultList.get(0).get(COL_DOC_MGT_CATG_NUM);
        param.setCategoryNo(categoryNo.intValue());

        WSIndexDataToPut additional = new WSIndexDataToPut();
        ArrayOfWSIndexDataItem arrayItems = new ArrayOfWSIndexDataItem();
        additional.setIndexDataItems(arrayItems);

        for (Map<String, Object> idxAttrbResult : idxAttrbResultList) {
            String valTxt = "";
            if (docMgtBrResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null) {
                valTxt = docMgtBrResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
            }

            WSIndexDataItem item = new WSIndexDataItem();

            String docMgtFldTpCd = idxAttrbResult.get(COL_DOC_MGT_FLD_TP_CD).toString();
            int docMgtFldNum = Integer.valueOf(idxAttrbResult.get(COL_DOC_MGT_FLD_NUM).toString());

            if (DOC_MGT_FLD_TP.STRING.equals(docMgtFldTpCd)) {
                WSIndexDataString data = new WSIndexDataString();
                data.setFieldNo(docMgtFldNum);
                data.setDataValue(valTxt);
                item.setStringIndexData(data);
            } else if (DOC_MGT_FLD_TP.INTEGER.equals(docMgtFldTpCd)) {
                WSIndexDataInt data = new WSIndexDataInt();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    data.setDataValue(new Integer(valTxt));
                } else {
                    data.setDataValue(null);
                }
                item.setIntIndexData(data);
            } else if (DOC_MGT_FLD_TP.MONEY.equals(docMgtFldTpCd)) {
                WSIndexDataMoney data = new WSIndexDataMoney();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    data.setDataValue(new Double(valTxt));
                } else {
                    data.setDataValue(null);
                }
                item.setMoneyIndexData(data);
            } else if (DOC_MGT_FLD_TP.DATE.equals(docMgtFldTpCd)) {
                WSIndexDataDate data = new WSIndexDataDate();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    valTxt = formatDate(ZYPDateUtil.addDays(valTxt, 1));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTime(sdf.parse(valTxt));
                    data.setDataValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                            c.get(Calendar.SECOND), c.get(Calendar.MILLISECOND), 0));
                    S21InfoLogOutput.println("NWZC230001 Updating Date is " + data.getDataValue().toString() + "(" + docMgtFldNum + ").");
                } else {
                    data.setDataValue(null);
                }
                item.setDateIndexData(data);
            } else if (DOC_MGT_FLD_TP.LOGICAL.equals(docMgtFldTpCd)) {
                WSIndexDataLogical data = new WSIndexDataLogical();
                data.setFieldNo(docMgtFldNum);
                if (ZYPCommonFunc.hasValue(valTxt)) {
                    data.setDataValue(Boolean.valueOf(valTxt));
                } else {
                    data.setDataValue(null);
                }
                item.setLogicalIndexData(data);
            }
            arrayItems.getWSIndexDataItem().add(item);
        }
        param.setIndexDataItems(arrayItems);

        // Invoke Therefore web service
        CreateDocumentResponse response = null;
        try {

            // Invoke Therefore web service
            response = S21ThereforeWebServiceProxy.getInstance().getThereforeService().createDocument(param);
            pMsg.docMgtIntfcStsCd.setValue(DOC_MGT_INTFC_STS.NEW);

        } catch (SOAPFaultException e) {
            S21InfoLogOutput.println(e.getMessage());
            pMsg.docMgtIntfcStsCd.setValue(DOC_MGT_INTFC_STS.ERROR);

        } catch (Exception e) {
            S21InfoLogOutput.println(e.getMessage());
            throw new S21AbendException(e);

        } finally {

            if (response != null) {
                XMLGregorianCalendar xmlGCal = response.getLastChangeTime();
                pMsg.docMgtIntfcProcTs.setValue(convertDt(xmlGCal));
            }
        }
    }

    /**
     * search
     * @param pMsg NWZC230001PMsg
     * @param idxAttrbResultList Index Attribute Result List
     * @param docMgtBrResult Document Management Branch Result
     * @return Therefore Doc ID
     */
    private Integer callThereforeApiForSearch(NWZC230001PMsg pMsg, List<Map<String, Object>> idxAttrbResultList, Map<String, Object> docMgtBrResult) {

        Integer thereforeDocId = null;

        BigDecimal thereforeCategoryNo = (BigDecimal) idxAttrbResultList.get(0).get(COL_DOC_MGT_CATG_NUM);

        // Get Therefore web service port instance
        IThereforeService thereforWebSerive = S21ThereforeWebServiceProxy.getInstance().getThereforeService();

        try {
            // Set parameters
            ExecuteSingleQueryParams params = new ExecuteSingleQueryParams();
            QueryObject queryObject = new QueryObject();
            ArrayOfWSCondition conditions = new ArrayOfWSCondition();
            queryObject.setConditions(conditions);
            queryObject.setCategoryNo(thereforeCategoryNo.intValue());

            for (Map<String, Object> searchConditionFld : idxAttrbResultList) {
                String fieldNo = String.valueOf((BigDecimal) searchConditionFld.get(COL_DOC_MGT_FLD_NUM));

                // Line of Business
                if (GET_SEARCH_CONDITION_FLD_NM_LIST[0].equals((String) searchConditionFld.get(COL_DOC_MGT_FLD_DESC_TXT))) {
                    String docMgtOrgDescTxt = (String) docMgtBrResult.get(COL_DOC_MGT_ORG_CD);

                    WSCondition condition = new WSCondition();
                    condition.setCondition(docMgtOrgDescTxt);
                    condition.setFieldNoOrName(fieldNo);
                    conditions.getWSCondition().add(condition);
                }

                // Branch GL Code
                if (GET_SEARCH_CONDITION_FLD_NM_LIST[1].equals((String) searchConditionFld.get(COL_DOC_MGT_FLD_DESC_TXT))) {
                    String docMgtScanBrNm = (String) docMgtBrResult.get(COL_DOC_MGT_SCAN_BR_CD);

                    WSCondition condition = new WSCondition();
                    condition.setCondition(docMgtScanBrNm);
                    condition.setFieldNoOrName(String.valueOf(fieldNo));
                    conditions.getWSCondition().add(condition);
                }
            }

            params.setQuery(queryObject);

            // Invoke Therefore web service
            ExecuteSingleQueryResponse response = thereforWebSerive.executeSingleQuery(params);
            java.util.List<WSQueryResultRow> rows = response.getQueryResult().getResultRows().getWSQueryResultRow();
            if (rows.size() > 0) {
                thereforeDocId = rows.get(0).getDocNo();
            }

        } catch (SOAPFaultException e) { // Most of error from
                                         // Therefore side include
                                         // data error falls in here
            S21InfoLogOutput.println(e.getMessage());

        } catch (Exception e) {
            S21InfoLogOutput.println(e.getMessage());
            throw new S21AbendException(e);
        }
        return thereforeDocId;
    }

    /**
     * <pre>
     * Parameter Mandantory Check
     * </pre>
     */
    private boolean checkIdxAttrbMandatory() {
        NWZC230001PMsg pMsg = (NWZC230001PMsg) this.msgMap.getPmsg();

        boolean errFlg = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            errFlg = false;
            this.msgMap.addXxMsgId(NWZM0188E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.docMgtCatgNum)) {
            errFlg = false;
            this.msgMap.addXxMsgId(NWZM1939E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.docMgtBizDocNum)) {
            errFlg = false;
            this.msgMap.addXxMsgId(NWZM1941E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.docMgtPrntDocNum)) {
            errFlg = false;
            this.msgMap.addXxMsgId(NWZM1981E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.procModeCd)) {
            errFlg = false;
            this.msgMap.addXxMsgId(NWZM2001E);
        }
        return errFlg;
    }

    /**
     * <pre>
     * Get Business Document
     * </pre>
     * @param pMsg NWZC230001PMsg
     * @return Business Document
     */
    private Map<String, Object> getDocMgtBr(NWZC230001PMsg pMsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("docMgtOrdCd", pMsg.docMgtPrntDocNum.getValue());
        param.put("docMgtScanBrCd", pMsg.docMgtBizDocNum.getValue());

        Map<String, Object> bizDocList = (Map<String, Object>) this.ssmBatchClient.queryObject("getDocMgtBr", param);
        if (bizDocList == null || bizDocList.isEmpty()) {
            return null;
        }

        return bizDocList;

    }

    /**
     * <pre>
     * Get Index Attribute Info
     * </pre>
     * @param pMsg NWZC230001PMsg
     * @return Index Attribute Info List
     */
    private List<Map<String, Object>> getIdxAttrbInfo(NWZC230001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("docMgtCatgCd", pMsg.docMgtCatgNum.getValue());

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getIdxAttrbInfo", param);
    }

    /**
     * <pre>
     * Format Date
     * </pre>
     * @return dateVal
     */
    private String formatDate(String dateVal) {
        String convertVal = new String();
        if (dateVal.length() < DATE_LENGTH) {
            convertVal = ZYPCommonFunc.rightPad(dateVal, DATE_LENGTH, "0");
        } else {
            convertVal = dateVal.substring(0, DATE_LENGTH);
        }

        return convertVal;
    }

    /**
     * <pre>
     * Convert Date
     * </pre>
     */
    private String convertDt(XMLGregorianCalendar xmlGCal) {

        StringBuilder sb = new StringBuilder();
        sb.append(ZYPCommonFunc.leftPad(String.valueOf(xmlGCal.getYear()), 4, "0"));
        sb.append(ZYPCommonFunc.leftPad(String.valueOf(xmlGCal.getMonth()), 2, "0"));
        sb.append(ZYPCommonFunc.leftPad(String.valueOf(xmlGCal.getDay()), 2, "0"));
        sb.append(ZYPCommonFunc.leftPad(String.valueOf(xmlGCal.getHour()), 2, "0"));
        sb.append(ZYPCommonFunc.leftPad(String.valueOf(xmlGCal.getMinute()), 2, "0"));
        sb.append(ZYPCommonFunc.leftPad(String.valueOf(xmlGCal.getSecond()), 2, "0"));
        sb.append(ZYPCommonFunc.leftPad(String.valueOf(xmlGCal.getMillisecond()), 3, "0"));
        return sb.toString();
    }
}
