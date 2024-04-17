/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFZ.NFZC505001;

import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.APP_ID;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.ATTACH_MODE_CD;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.CHECKIN_COMMNT;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.COL_DOC_MGT_FLD_CD;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.COL_DOC_MGT_FLD_DESC_TXT;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.COL_DOC_MGT_FLD_NUM;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.COL_DOC_MGT_FLD_TP_CD;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.COL_IDX_ATTRB_TRGT_COL_NM;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.DATE_LENGTH;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.INV_STS;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0240E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0241E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0242E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0243E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0244E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0245E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0246E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0247E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0248E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0250E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0251E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFBM0252E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFZM0029E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NFZM0030E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.NZZM0003E;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.PARAM_VALUE_AP_VND_INV_SQ_NUM;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.VAR_CHAR_CONST_KEY_THEREFORE_CONN_AVAL_FLG;
import static com.canon.cusa.s21.api.NFZ.NFZC505001.constant.NFZC505001Constant.VAR_CHAR_CONST_KEY_THEREFORE_ERR_INVALID_DOC;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSIndexDataItemQuick;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSTableFieldDataRow;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSTableFieldDataRowItem;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSWFInstanceInfo;
import net.therefore.schemas.webservices.interop.v0001.types.ClaimWorkflowInstanceParams;
import net.therefore.schemas.webservices.interop.v0001.types.ClaimWorkflowInstanceResponse;
import net.therefore.schemas.webservices.interop.v0001.types.DeleteWorkflowInstanceParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataResponse;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentResponse;
import net.therefore.schemas.webservices.interop.v0001.types.GetLinkedWorkflowsForDocParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetLinkedWorkflowsForDocResponse;
import net.therefore.schemas.webservices.interop.v0001.types.SaveDocumentIndexDataParams;
import net.therefore.schemas.webservices.interop.v0001.types.SaveDocumentIndexDataQuickParams;
import net.therefore.schemas.webservices.interop.v0001.types.SaveDocumentIndexDataQuickResponse;
import net.therefore.schemas.webservices.interop.v0001.types.SaveDocumentIndexDataResponse;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataDate;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataInt;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataItemQuick;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataLogical;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataMoney;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataString;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataTable;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataToGet;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataToPut;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataToPutQuick;
import net.therefore.schemas.webservices.interop.v0001.types.WSTableFieldDataRow;
import net.therefore.schemas.webservices.interop.v0001.types.WSTableFieldDataRowItem;
import net.therefore.schemas.webservices.interop.v0001.types.WSWFDocLinkType;
import net.therefore.schemas.webservices.interop.v0001.types.WSWFInstanceInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_INTFC_TRXTMsg;
import business.parts.NFZC505001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_FLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;

/**
 *<pre>
 * NFZC5050:Therefore Indexed Attribute Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/12   Fujitsu         W.Honda         Create          N/A
 * 2016/10/25   Fujitsu         W.Honda         Update          QC#15550
 * 2016/10/25   Fujitsu         W.Honda         Update          QC#15114
 * 2016/10/31   Fujitsu         W.Honda         Update          QC#15115
 * 2016/12/13   Fujitsu         W.Honda         Update          QC#16277
 * 2016/12/22   Fujitsu         W.Honda         Update          QC#12865
 * 2017/03/09   Fujitsu         T.Aoi           Update          QC#17544
 *</pre>
 */


public class NFZC505001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** SSM client */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * Constructor
     */
    public NFZC505001() {
        super();

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Therefore Indexed Attribute Update API (List)
     * </pre>
     * @param pMsgList    Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NFZC505001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NFZC505001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * <pre>
     * Therefore Indexed Attribute Update API
     * </pre>
     * @param pMsg    Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final NFZC505001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        if (!ZYPCommonFunc.hasValue(pMsg.procModeCd)) {
            this.msgMap.addXxMsgId(NFBM0246E);
            return;
        }

        if (ATTACH_MODE_CD.equals(pMsg.procModeCd.getValue())) {
            execute(pMsg, onBatchType, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE);
        } else {
            execute(pMsg, onBatchType, ZYPFileUpDownConst.BIZAPI_TYPE_DELETE);
        }
    }

    /**
     * <pre>
     * Therefore Indexed Attribute Update API (List)
     * </pre>
     * @param pMsgList    Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     * @param bizApiType Business API type
     */
    public void execute(final List<NFZC505001PMsg> pMsgList, final ONBATCH_TYPE onBatchType, String bizApiType) {
        for (NFZC505001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType, bizApiType);
        }
    }

    /**
     * <pre>
     * Therefore Indexed Attribute Update API
     * </pre>
     * @param pMsg    Input parameter.
     * @param onBatchType Kind of Online or Batch.
     * @param bizApiType Business API type
     */
    public void execute(NFZC505001PMsg pMsg, final ONBATCH_TYPE onBatchType, String bizApiType) {
        boolean attachFlg = false;
        if (ZYPFileUpDownConst.BIZAPI_TYPE_CREATE.equals(bizApiType)) {
            attachFlg = true;
        }

        // Create Message Map
        this.msgMap = new S21ApiMessageMap(pMsg);

        // Insert TMsg List
        List<DOC_MGT_INTFC_TRXTMsg> trxTMsgList = new ArrayList<DOC_MGT_INTFC_TRXTMsg>();

        try {
            checkIdxAttrbMandatory();
            if (hasErr()) {
                return;
            }

            // Check Therefore connection is available
            if (!isThereforeConnectionAvailable(pMsg)) {
                return;
            }

            // Get Therefore web service port instance
            IThereforeService thereforeWebService = S21ThereforeWebServiceProxy.getInstance().getThereforeService();

            if (!checkDocMgtCatg(attachFlg)) {
                return;
            }

            //QC#16277 START
            // check Therefore Document ID
            // if occur exception, Therefore Document is invalid
            GetDocumentIndexDataParams docParam = new GetDocumentIndexDataParams();
            docParam.setDocNo(pMsg.docMgtDocId.getValueInt());
            thereforeWebService.getDocumentIndexData(docParam);
            //QC#16277 END

            // 2017/03/09 S21_NA#17544 Add Start
            // Check Already Therefore Workflows Claimed
            List<WSWFInstanceInfo> arrayInstanceInfo = null;
            if (!attachFlg) {
                arrayInstanceInfo = checkAlreadyWorkflowsClaimed(pMsg, thereforeWebService);
            }
            // 2017/03/09 S21_NA#17544 Add End

            // 2016/10/31 S21_NA#15115 Add Start
            if (!DOC_MGT_CATG.PO_INVOICE.equals(pMsg.docMgtCatgCd.getValue())
                    && !DOC_MGT_CATG.NON_PO_INVOICE.equals(pMsg.docMgtCatgCd.getValue())
                    && !DOC_MGT_CATG.PRE_APPROVED_INVOICE.equals(pMsg.docMgtCatgCd.getValue())) {
            // 2016/10/31 S21_NA#15115 Add End
                Map<String, Object> bizDocResult = getBizDoc(pMsg);
                if (bizDocResult == null) {
                    this.msgMap.addXxMsgId(NFBM0242E);
                    return;
                }
                List<Map<String, Object>> idxAttrbResultList = getIdxAttrbInfo(pMsg, null);
                if (idxAttrbResultList == null || idxAttrbResultList.size() == 0) {
                    this.msgMap.addXxMsgId(NFBM0241E);
                    return;
                }

                // Set parameters
                SaveDocumentIndexDataQuickParams param = new SaveDocumentIndexDataQuickParams();
                param.setCheckInComments(CHECKIN_COMMNT);
                param.setDocNo(pMsg.docMgtDocId.getValueInt());

                WSIndexDataToPutQuick additional = new WSIndexDataToPutQuick();
                ArrayOfWSIndexDataItemQuick arrayItems = new ArrayOfWSIndexDataItemQuick();
                additional.setIndexDataItems(arrayItems);

                for (Map<String, Object> idxAttrbResult : idxAttrbResultList) {
                    String valTxt = "";
                    // 2017/03/09 S21_NA#17544 Mod Start
                    if (bizDocResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null
                            && (attachFlg || INV_STS.equals(idxAttrbResult.get(COL_DOC_MGT_FLD_DESC_TXT)))) {
                        valTxt = bizDocResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
                    }
                    // 2017/03/09 S21_NA#17544 Mod End
                    if (bizDocResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null
                            && attachFlg) {
                        valTxt = bizDocResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
                    }

                    DOC_MGT_INTFC_TRXTMsg docMgtIntfcTrxTMsg = new DOC_MGT_INTFC_TRXTMsg();

                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    BigDecimal docMgtIntfcTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_INTFC_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtIntfcTrxPk, docMgtIntfcTrxPk);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtDocId, pMsg.docMgtDocId);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtCatgCd, pMsg.docMgtCatgCd);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtBizDocNum, pMsg.apVndCd);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtPrntDocNum, pMsg.apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtFldCd, idxAttrbResult.get(COL_DOC_MGT_FLD_CD).toString());
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtFldValTxt, valTxt);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));

                    trxTMsgList.add(docMgtIntfcTrxTMsg);
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
                            // 2016/10/25 S21_NA#15114 Mod Start
                            valTxt = formatDate(ZYPDateUtil.addDays(valTxt, 1));
                            // 2016/10/25 S21_NA#15114 Mod End
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
                            GregorianCalendar c = new GregorianCalendar();
                            c.setTime(sdf.parse(valTxt));
                            // 2016/10/25 S21_NA#15114 Mod Start
                            XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR)
                                                                                                              , c.get(Calendar.MONTH) + 1     // 2016/10/24 S21_NA#15114
                                                                                                              , c.get(Calendar.DAY_OF_MONTH)
                                                                                                              , c.get(Calendar.HOUR_OF_DAY)
                                                                                                              , c.get(Calendar.MINUTE)
                                                                                                              , c.get(Calendar.SECOND)
                                                                                                              , c.get(Calendar.MILLISECOND)
                                                                                                              , 0);
                            data.setDataValue(xmlgc);
                            // 2016/10/25 S21_NA#15114 Mod End
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

                // Invoke Therefore web service
                SaveDocumentIndexDataQuickResponse response = thereforeWebService.saveDocumentIndexDataQuick(param);

                // 2017/03/09 S21_NA#17544 Add Start
                if (!attachFlg) {
                    deleteThereforeWorkflows(pMsg, thereforeWebService, arrayInstanceInfo);
                }
                // 2017/03/09 S21_NA#17544 Add End

                // WebService Normal End
                for (DOC_MGT_INTFC_TRXTMsg tMsg : trxTMsgList) {
                    ZYPEZDItemValueSetter.setValue(tMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
                }

            // 2016/10/31 S21_NA#15115 Add Start
            } else {
                Map<String, Object> bizDocResult = getBizDoc(pMsg);
                if (bizDocResult == null) {
                    this.msgMap.addXxMsgId(NFBM0242E);
                    return;
                }

                List<Map<String, Object>> idxAttrbResultList = getIdxAttrbInfo(pMsg, null);
                if (idxAttrbResultList == null || idxAttrbResultList.size() == 0) {
                    return;
                }

                // Fetch the latest version of the document
                GetDocumentParams getDocParams = new GetDocumentParams();
                getDocParams.setDocNo(pMsg.docMgtDocId.getValueInt());
                GetDocumentResponse getDocResponse = S21ThereforeWebServiceProxy.getInstance().getThereforeService().getDocument(getDocParams); // QC#14569 MOD
                WSIndexDataToGet getDocIndexData = getDocResponse.getIndexData();
                XMLGregorianCalendar lastChangeTime = getDocIndexData.getLastChangeTime();

                // Set parameters
                SaveDocumentIndexDataParams param = new SaveDocumentIndexDataParams();
                param.setCheckInComments(CHECKIN_COMMNT);
                param.setDocNo(pMsg.docMgtDocId.getValueInt());

                WSIndexDataToPut additional = new WSIndexDataToPut();
                ArrayOfWSIndexDataItem arrayItems = new ArrayOfWSIndexDataItem();
                additional.setIndexDataItems(arrayItems);
                additional.setLastChangeTime(lastChangeTime);

                for (Map<String, Object> idxAttrbResult : idxAttrbResultList) {
                    String valTxt = "";

                    // 2017/03/09 S21_NA#17544 Mod Start
                    if (bizDocResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null
                            && (attachFlg || INV_STS.equals(idxAttrbResult.get(COL_DOC_MGT_FLD_DESC_TXT)))) {
                        valTxt = bizDocResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
                    }
                    // 2017/03/09 S21_NA#17544 Mod End

                    DOC_MGT_INTFC_TRXTMsg docMgtIntfcTrxTMsg = new DOC_MGT_INTFC_TRXTMsg();

                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    BigDecimal docMgtIntfcTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_INTFC_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtIntfcTrxPk, docMgtIntfcTrxPk);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtDocId, pMsg.docMgtDocId);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtCatgCd, pMsg.docMgtCatgCd);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtBizDocNum, pMsg.apVndCd);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtPrntDocNum, pMsg.apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtFldCd, idxAttrbResult.get(COL_DOC_MGT_FLD_CD).toString());
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtFldValTxt, valTxt);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));

                    trxTMsgList.add(docMgtIntfcTrxTMsg);
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
                            // 2016/10/25 S21_NA#15115(#15114 expanse) Mod Start
                            valTxt = formatDate(ZYPDateUtil.addDays(valTxt, 1));
                            // 2016/10/25 S21_NA#15115(#15114 expanse) Mod End
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                            GregorianCalendar c = new GregorianCalendar();
                            c.setTime(sdf.parse(valTxt));
                            data.setDataValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR)
                                                                                                   , c.get(Calendar.MONTH) + 1     // 2016/10/24 S21_NA#15115(#15114 expanse)
                                                                                                   , c.get(Calendar.DAY_OF_MONTH)
                                                                                                   , c.get(Calendar.HOUR_OF_DAY)
                                                                                                   , c.get(Calendar.MINUTE)
                                                                                                   , c.get(Calendar.SECOND)
                                                                                                   , c.get(Calendar.MILLISECOND)
                                                                                                   , 0));
                            S21InfoLogOutput.println("NFZC505001 Updating Date is " + data.getDataValue().toString() + "(" + docMgtFldNum + ").");
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
                    } else if (DOC_MGT_FLD_TP.TABLE.equals(docMgtFldTpCd)) {
                        WSIndexDataTable data = new WSIndexDataTable();
                        data.setFieldNo(docMgtFldNum);
                        if (!ZYPCommonFunc.hasValue(valTxt)) {
                            ArrayOfWSTableFieldDataRow arrayDataRow = getArrayOfDataRow(pMsg, (String) idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM), S21ThereforeWebServiceProxy.getInstance().getThereforeService(), false);
                            data.setDataValue(arrayDataRow);
                        } else {
                            ArrayOfWSTableFieldDataRow arrayDataRow = getArrayOfDataRow(pMsg, (String) idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM), S21ThereforeWebServiceProxy.getInstance().getThereforeService(), true);
                            data.setDataValue(arrayDataRow);
                        }
                        item.setTableIndexData(data);
                    }
                    arrayItems.getWSIndexDataItem().add(item);
                }
                param.setIndexData(additional);

                // Invoke Therefore web service
                SaveDocumentIndexDataResponse response = S21ThereforeWebServiceProxy.getInstance().getThereforeService().saveDocumentIndexData(param); //QC#14569 MOD

                // 2017/03/09 S21_NA#17544 Add Start
                if (!attachFlg) {
                    deleteThereforeWorkflows(pMsg, thereforeWebService, arrayInstanceInfo);
                }
                // 2017/03/09 S21_NA#17544 Add End

                // WebService Normal End
                for (DOC_MGT_INTFC_TRXTMsg tMsg : trxTMsgList) {
                    ZYPEZDItemValueSetter.setValue(tMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
                }
            }
            // 2016/10/31 S21_NA#15115 Add End

        } catch (SOAPFaultException e) {

            StackTraceElement[] steAry = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (StackTraceElement ste : steAry) {
                sb.append(ste.toString());
                sb.append("\n");
            }
            S21InfoLogOutput.println(sb.toString());

            // WebService Error End
            //QC#16277 START
            String docIdErrCode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_THEREFORE_ERR_INVALID_DOC, pMsg.glblCmpyCd.getValue());
            String errMsg = e.getMessage();
            if (ZYPCommonFunc.hasValue(errMsg)
                    && errMsg.contains(docIdErrCode)) {
                // invalid Therefore Document ID Error
                this.msgMap.addXxMsgId(NFZM0029E);
            } else {
                // WebService Error
                this.msgMap.addXxMsgId(NFZM0030E);
            }
            //QC#16277 END
            for (DOC_MGT_INTFC_TRXTMsg tMsg : trxTMsgList) {
                ZYPEZDItemValueSetter.setValue(tMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.ERROR);
            }
        } catch (Exception e) {
            throw new S21AbendException(e);
        } finally {
            // Flush Message Map.
            this.msgMap.flush();
        }

        if (trxTMsgList.size() > 0) {
            insDocMgtIntfcTrx(trxTMsgList);
        }

        // Flush Message Map.
        this.msgMap.flush();
    }

    /**
     * <pre>
     * Error Check
     * </pre>
     */
    private boolean hasErr() {
        NFZC505001PMsg pMsg = (NFZC505001PMsg) this.msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Parameter Mandatory Check
     * </pre>
     */
    private void checkIdxAttrbMandatory() {
        NFZC505001PMsg param = (NFZC505001PMsg) this.msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            this.msgMap.addXxMsgId(NFBM0243E);
        }

        if (!ZYPCommonFunc.hasValue(param.docMgtDocId)) {
            this.msgMap.addXxMsgId(NFBM0244E);
        }

        if (!ZYPCommonFunc.hasValue(param.docMgtCatgCd)) {
            this.msgMap.addXxMsgId(NFBM0245E);
        }

        if (!ZYPCommonFunc.hasValue(param.apVndCd)) {
            this.msgMap.addXxMsgId(NFBM0247E);
        }

        if (!ZYPCommonFunc.hasValue(param.apVndInvNum)) {
            this.msgMap.addXxMsgId(NFBM0248E);
        }

        return;
    }

    /**
     * <pre>
     * Document Management Category Flag & bizApiType Check
     * </pre>
     * @param attachFlg    boolean
     * @return boolean
     */
    private boolean checkDocMgtCatg(boolean attachFlg) {
        NFZC505001PMsg param = (NFZC505001PMsg) this.msgMap.getPmsg();

        DOC_MGT_CATGTMsg docMgtCatg = new DOC_MGT_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(docMgtCatg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(docMgtCatg.docMgtCatgCd, param.docMgtCatgCd);
        docMgtCatg = (DOC_MGT_CATGTMsg) EZDTBLAccessor.findByKey(docMgtCatg);
        if (docMgtCatg == null) {
            String[] paramArray = new String[] {param.docMgtCatgCd.getValue()};
            String errMsgText = S21MessageFunc.clspGetMessage(NFBM0250E, paramArray);
            writeLogLn(errMsgText);
            this.msgMap.addXxMsgId(NFBM0251E);
            return false;
        }

        if (attachFlg) {
            if (ZYPConstant.FLG_OFF_N.equals(docMgtCatg.updIdxAttrbOnAttFlg.getValue())) {
                this.msgMap.addXxMsgId(NFBM0240E);
                return false;
            }
        } else {
            if (ZYPConstant.FLG_OFF_N.equals(docMgtCatg.updIdxAttrbOnDelFlg.getValue())) {
                this.msgMap.addXxMsgId(NFBM0240E);
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * Get Business Document
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return Business Document
     */
    private Map<String, Object> getBizDoc(NFZC505001PMsg pMsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("apVndCd", pMsg.apVndCd.getValue());
        param.put("apVndInvNum", pMsg.apVndInvNum.getValue());
        param.put("apVndInvSqNum", PARAM_VALUE_AP_VND_INV_SQ_NUM);
        // 2016/10/25 QC#15550 ADD START
        param.put("pmtHldCdTherefore", PMT_HLD.THEREFORE);
        param.put("pmtHldRsnCdTherefore", PMT_HLD_RSN.THEREFORE);
        // 2016/10/25 QC#15550 ADD END

        List<Map<String, Object>> bizDocList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBizDoc", param);
        if (bizDocList == null
                || bizDocList.isEmpty()) {
            return null;
        }

        if (bizDocList.size() > 0) {
            return bizDocList.get(0);
        }

        return null;
    }

    /**
     * <pre>
     * Get Index Attribute Info
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return Index Attribute Info List
     */
    private List<Map<String, Object>> getIdxAttrbInfo(NFZC505001PMsg pMsg, String trgtTblNm) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("docMgtCatgCd", pMsg.docMgtCatgCd.getValue());
        if (ZYPCommonFunc.hasValue(trgtTblNm)) {
            param.put("trgtTblNm", trgtTblNm);
        }

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getIdxAttrbInfo", param);
    }

    /**
     * Insert Document Management Interface Transaction
     * @param insIntfcTMsgLsit Insert Interface TMsg List
     */
    private void insDocMgtIntfcTrx(List<DOC_MGT_INTFC_TRXTMsg> insTMsgLsit) {
        int insCnt = insTMsgLsit.size();
        int resCnt = S21FastTBLAccessor.insert((DOC_MGT_INTFC_TRXTMsg[]) insTMsgLsit.toArray(new DOC_MGT_INTFC_TRXTMsg[0]));

        if (resCnt != insCnt) {
            msgMap.addXxMsgId(NFBM0252E);
        }
    }

    /**
     * writeLogLn
     * @param format String
     * @param param Object...
     */
    private static void writeLogLn(String format, Object... param) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    /**
     * <pre>
     * formatDate
     * </pre>
     * @param dateVal      String
     * @return CFS_LEASE_PKG_PO_HDR
     */
    private String formatDate(String dateVal) {
        String convertVal;
        if (dateVal.length() < DATE_LENGTH) {
            convertVal = ZYPCommonFunc.rightPad(dateVal, DATE_LENGTH, "0");
        } else {
            convertVal = dateVal.substring(0, DATE_LENGTH);
        }

        return convertVal;
    }

    /**
     * <pre>
     * check Therefore connection is available.
     * </pre>
     * @param pMsg NFZC505001PMsg
     * @return boolean
     */
    private boolean isThereforeConnectionAvailable(NFZC505001PMsg pMsg) {
        String value = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_THEREFORE_CONN_AVAL_FLG, pMsg.glblCmpyCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(value)) {
            return true;
        }
        return false;
    }

    // 2016/10/25 S21_NA#15115 Mod Start
    /**
     * <pre>
     * Get CFS_LEASE_PKG_PO_HDR
     * </pre>
     * @param pMsg      NFZC505001PMsg
     * @return CFS_LEASE_PKG_PO_HDR
     */
    private ArrayOfWSTableFieldDataRow getArrayOfDataRow(NFZC505001PMsg     pMsg
                                                         , String            trgtTblNM
                                                         , IThereforeService thereforeWebService
                                                         , Boolean           isAttach)
                                                          throws Exception {
        List<Map<String, Object>> bizDocDtlList = getBizDocDtl(pMsg);

        if (bizDocDtlList == null) {
            return null;
        }

        List<Map<String, Object>> idxAttrbList = getIdxAttrbInfo(pMsg, trgtTblNM);

        if (idxAttrbList == null || idxAttrbList.size() == 0) {
            return null;
        }

        ArrayOfWSTableFieldDataRow arrayDataRow = new ArrayOfWSTableFieldDataRow();
        int cnt = 0;
        int existCnt = getExistTableRowCnt(thereforeWebService, pMsg.docMgtDocId.getValueInt());

        for (Map<String, Object> bizDocDtl : bizDocDtlList) {
            WSTableFieldDataRow dataRow = new WSTableFieldDataRow();
            if (cnt < existCnt) {
                dataRow.setRowNo(cnt);
            }

            ArrayOfWSTableFieldDataRowItem arrayDataRowItems = new ArrayOfWSTableFieldDataRowItem();
            for (Map<String, Object> idxAttrb : idxAttrbList) {
                String valTxt = "";
                if (bizDocDtl.get(idxAttrb.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null
                        && isAttach) {
                    valTxt = bizDocDtl.get(idxAttrb.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
                }

                WSTableFieldDataRowItem dataRowItem = new WSTableFieldDataRowItem();

                String docMgtFldTpCd = idxAttrb.get(COL_DOC_MGT_FLD_TP_CD).toString();
                int docMgtFldNum = Integer.valueOf(idxAttrb.get(COL_DOC_MGT_FLD_NUM).toString());

                if (DOC_MGT_FLD_TP.STRING.equals(docMgtFldTpCd)) {
                    WSIndexDataString data = new WSIndexDataString();
                    data.setFieldNo(docMgtFldNum);
                    data.setDataValue(valTxt);
                    dataRowItem.setStringIndexData(data);
                } else if (DOC_MGT_FLD_TP.INTEGER.equals(docMgtFldTpCd)) {
                    WSIndexDataInt data = new WSIndexDataInt();
                    data.setFieldNo(docMgtFldNum);
                    if (ZYPCommonFunc.hasValue(valTxt)) {
                        data.setDataValue(new Integer(valTxt));
                    } else {
                        data.setDataValue(null);
                    }
                    dataRowItem.setIntIndexData(data);
                } else if (DOC_MGT_FLD_TP.MONEY.equals(docMgtFldTpCd)) {
                    WSIndexDataMoney data = new WSIndexDataMoney();
                    data.setFieldNo(docMgtFldNum);
                    if (ZYPCommonFunc.hasValue(valTxt)) {
                        data.setDataValue(new Double(valTxt));
                    } else {
                        data.setDataValue(null);
                    }
                    dataRowItem.setMoneyIndexData(data);
                } else if (DOC_MGT_FLD_TP.DATE.equals(docMgtFldTpCd)) {
                    WSIndexDataDate data = new WSIndexDataDate();
                    data.setFieldNo(docMgtFldNum);
                    if (ZYPCommonFunc.hasValue(valTxt)) {
                        valTxt = formatDate(ZYPDateUtil.addDays(valTxt, 1));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        GregorianCalendar c = new GregorianCalendar();
                        c.setTime(sdf.parse(valTxt));
                        data.setDataValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR)
                                                                                               , c.get(Calendar.MONTH) + 1
                                                                                               , c.get(Calendar.DAY_OF_MONTH)
                                                                                               , c.get(Calendar.HOUR_OF_DAY)
                                                                                               , c.get(Calendar.MINUTE)
                                                                                               , c.get(Calendar.SECOND)
                                                                                               , c.get(Calendar.MILLISECOND)
                                                                                               , 0));
                    } else {
                        data.setDataValue(null);
                    }
                    dataRowItem.setDateIndexData(data);
                } else if (DOC_MGT_FLD_TP.LOGICAL.equals(docMgtFldTpCd)) {
                    WSIndexDataLogical data = new WSIndexDataLogical();
                    data.setFieldNo(docMgtFldNum);
                    if (ZYPCommonFunc.hasValue(valTxt)) {
                        data.setDataValue(Boolean.valueOf(valTxt));
                    } else {
                        data.setDataValue(null);
                    }
                    dataRowItem.setLogicalIndexData(data);
                }
                arrayDataRowItems.getWSTableFieldDataRowItem().add(dataRowItem);
            }
            dataRow.setDataRowItems(arrayDataRowItems);

            arrayDataRow.getWSTableFieldDataRow().add(dataRow);
            cnt++;
        }

        return arrayDataRow;
    }

    /**
     * <pre>
     * Get Detail
     * </pre>
     * @param pMsg      NFZC505001PMsg
     * @return Detail
     */
    private List<Map<String, Object>> getBizDocDtl(NFZC505001PMsg pMsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("apVndCd", pMsg.apVndCd.getValue());
        param.put("apVndInvNum", pMsg.apVndInvNum.getValue());
        param.put("apLineTpCdVariance", AP_LINE_TP.VARIANCE);

        List<Map<String, Object>> bizDocDtlList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBizDocDtl", param);
        if (bizDocDtlList == null || bizDocDtlList.isEmpty()) {
            return null;
        }

        return bizDocDtlList;
    }

    /**
     * <pre>
     * Get CFS_LEASE_PKG_PO_HDR
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return CFS_LEASE_PKG_PO_HDR
     */
    private int getExistTableRowCnt(IThereforeService thereforeWebService, int docId) {
        GetDocumentIndexDataParams getDocIndexParams = new GetDocumentIndexDataParams();
        getDocIndexParams.setDocNo(docId);
        GetDocumentIndexDataResponse getDocResponse = thereforeWebService.getDocumentIndexData(getDocIndexParams);

        List<WSIndexDataItem> itemList = getDocResponse.getIndexData().getIndexDataItems().getWSIndexDataItem();
        for (WSIndexDataItem item : itemList) {
            if (item.getTableIndexData() != null) {
                WSIndexDataTable table = item.getTableIndexData();
                return table.getDataValue().getWSTableFieldDataRow().size();
            }
        }

        return 0;
    }
    // 2016/10/25 S21_NA#15115 Mod End

    // 2017/03/09 S21_NA#17544 Add Start
    /**
     * <pre>
     * Delete  Therefore Workflow Instance
     * </pre>
     * @param pMsg
     * @param thereforeWebService
     */
    private void deleteThereforeWorkflows(NFZC505001PMsg pMsg, IThereforeService thereforeWebService, List<WSWFInstanceInfo> arrayInstanceInfo) {

        for (WSWFInstanceInfo info : arrayInstanceInfo) {
            int instanceNo = info.getInstanceNo();

            DeleteWorkflowInstanceParams delWorkflowsParams = new DeleteWorkflowInstanceParams();
            delWorkflowsParams.setInstanceNo(instanceNo);
            delWorkflowsParams.setTokenNo(0);

            thereforeWebService.deleteWorkflowInstance(delWorkflowsParams);
        }
    }

    /**
     * <pre>
     * Check Workflows Already Claimed
     * </pre>
     */
    private List<WSWFInstanceInfo> checkAlreadyWorkflowsClaimed(NFZC505001PMsg pMsg, IThereforeService thereforeWebService){

        GetLinkedWorkflowsForDocParams workflowParam = new GetLinkedWorkflowsForDocParams();
        workflowParam.setDocNo(pMsg.docMgtDocId.getValueInt());
        workflowParam.setWFDocLinkType(WSWFDocLinkType.ANY);

        GetLinkedWorkflowsForDocResponse workflowsResponse = thereforeWebService.getLinkedWorkflowsForDoc(workflowParam);
        ArrayOfWSWFInstanceInfo instanceInfo = workflowsResponse.getInfoLists();
        List<WSWFInstanceInfo> arrayInstanceInfo = instanceInfo.getWSWFInstanceInfo();
        for (WSWFInstanceInfo info : arrayInstanceInfo) {
            int instanceNo = info.getInstanceNo();

            ClaimWorkflowInstanceParams claimParam = new ClaimWorkflowInstanceParams();
            claimParam.setInstanceNo(instanceNo);
            claimParam.setTokenNo(0);

            ClaimWorkflowInstanceResponse  claimResponse = thereforeWebService.claimWorkflowInstance(claimParam);

            if(claimResponse.isIsAlreadyClaimed()) {
                this.msgMap.addXxMsgId(NZZM0003E);
            }
        }
        return arrayInstanceInfo;
    }
    // 2017/03/09 S21_NA#17544 Add End
}
