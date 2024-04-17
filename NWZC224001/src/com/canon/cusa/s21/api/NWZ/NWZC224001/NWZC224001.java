/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC224001;

import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.ATTACH_MODE_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.ATT_DATA_TP_THEREFORE;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.CHECKIN_COMMNT;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.COL_AQU_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.COL_CFS_LEASE_PKG_PO_HDR_PK;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.COL_DOC_MGT_FLD_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.COL_DOC_MGT_FLD_DESC_TXT;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.COL_DOC_MGT_FLD_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.COL_DOC_MGT_FLD_TP_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.COL_IDX_ATTRB_TRGT_COL_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.DATE_LENGTH;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.EZDSYS_KEY_THEREFORE_URL;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.GET_SEARCH_RESULT_FLD_NM_LIST;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.LEASE_DOC_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWAM0889E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWAM0891E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM1938E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM1981E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2000E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2001E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2003E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2009E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2013E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2037E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2052E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2053E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.NWZM2259E;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.SCANNED_DATE;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.SCAN_DATE;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.THEREFORE_CONN_AVAL_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.THEREFORE_ERR_INVALID_DOC;
import static com.canon.cusa.s21.api.NWZ.NWZC224001.constant.NWZC224001Constant.TIME_STAMP_FORMAT;

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
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataResponse;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentResponse;
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
import parts.common.EZDSystemEnv;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ATT_DATATMsg;
import business.db.DOC_MGT_ATT_RQSTTMsg;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_CATGTMsgArray;
import business.db.DOC_MGT_INTFC_TRXTMsg;
import business.db.DOC_MGT_SCAN_TRXTMsg;
import business.parts.NWZC224001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ATT_DATA_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_FLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_RQST_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWZC2240:Therefore Indexed Attribute Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/25   Fujitsu         W.Honda         Create          N/A
 * 2016/09/16   Fujitsu         S.Iidaka        Update          QC#14569
 * 2016/10/03   Fujitsu         W.Honda         Update          QC#14969
 * 2016/10/18   Fujitsu         W.Honda         Update          QC#14927,15194
 * 2016/10/31   Fujitsu         W.Honda         Update          QC#15115(#15114 expanse)
 * 2016/12/12   Fujitsu         W.Honda         Update          QC#16277
 * 2017/02/02   Fujitsu         M.Ohno          Update          QC#17392
 * 2017/02/03   Fujitsu         M.Ohno          Update          QC#17392-2
 * 2017/02/14   Fujitsu         T.Aoi           Update          QC#16277-2
 * 2017/02/15   Fujitsu         T.Aoi           Update          QC#17286
 * 2018/01/19   Fujitsu         W.Honda         Update          QC#23598
 * 2018/04/23   Fujitsu         W.Honda         Update          QC#24244
 * 2019/08/29   Fujitsu         C.Hara          Update          QC#52938
 * 2019/09/20   Fujitsu         C.Hara          Update          QC#52938-1
 *</pre>
 */

public class NWZC224001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** SSM client */
    private S21SsmBatchClient ssmBatchClient;

    /** Therefore Connection Available Flag */
    private String thereforeConnAvalFlg = "N";

    //QC#14927,15194 START
    /** Therefore URL */
    private String thereforeUrl = EZDSystemEnv.getString(EZDSYS_KEY_THEREFORE_URL);
    //QC#14927,15194 END

    //QC#14969 START
    /** Acquisition Number */
    private String aquNum = null;
    //QC#14969 END
    /**
     * Constructor
     */
    public NWZC224001() {
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
    public void execute(final List<NWZC224001PMsg> pMsgList, final ONBATCH_TYPE onBatchType, String bizApiType) {
        for (NWZC224001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType, bizApiType);
        }
    }

    /**
     * <pre>
     * Therefore Indexed Attribute Update API (List)
     * </pre>
     * @param pMsgList    Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NWZC224001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NWZC224001PMsg pMsg : pMsgList) {
            if (!ZYPCommonFunc.hasValue(pMsg.procModeCd)) {
                 // Create Message Map
                this.msgMap = new S21ApiMessageMap(pMsg); // 2017/02/03 S21_NA#17392-2 Add
                this.msgMap.addXxMsgId(NWZM2001E);
                this.msgMap.flush(); // 2017/02/03 S21_NA#17392-2 Add
                continue;
            }

            if (ATTACH_MODE_CD.equals(pMsg.procModeCd.getValue())) {
                execute(pMsg, onBatchType, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE);
            } else {
                execute(pMsg, onBatchType, ZYPFileUpDownConst.BIZAPI_TYPE_DELETE);
            }
        }
    }

    /**
     * <pre>
     * Therefore Indexed Attribute Update API (List)
     * </pre>
     * @param pMsgList    Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final NWZC224001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        if (!ZYPCommonFunc.hasValue(pMsg.procModeCd)) {
            // Create Message Map
            this.msgMap = new S21ApiMessageMap(pMsg); // 2017/02/03 S21_NA#17392-2 Add
            this.msgMap.addXxMsgId(NWZM2001E);
            this.msgMap.flush(); // 2017/02/03 S21_NA#17392-2 Add
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
     * Therefore Indexed Attribute Update API for ZYPL0310BL06
     * </pre>
     * @param attDataPk  ATT_DATA primary key
     * @param onBatchType Kind of Online or Batch.
     * @param bizApiType create or delete
     */
    public NWZC224001PMsg execute(int attDataPk, final ONBATCH_TYPE onBatchType, String bizApiType) { // 2017/02/14 S21_NA#16277-2 Mod
        NWZC224001PMsg pMsg = new NWZC224001PMsg();
        ATT_DATATMsg attDataTMsg = new ATT_DATATMsg();
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        attDataTMsg.glblCmpyCd.setValue(glblCmpyCd);
        attDataTMsg.attDataPk.setValue(attDataPk);

        // findByKey [ATT_DATA]
        attDataTMsg = (ATT_DATATMsg) EZDTBLAccessor.findByKey(attDataTMsg);

        // set parameter to NWZC224001PMsg
        if (attDataTMsg != null) {
            pMsg.glblCmpyCd.setValue(glblCmpyCd);
            pMsg.docMgtDocId.setValue(new BigDecimal(attDataTMsg.attDataNm.getValue()));
            pMsg.docMgtPrntDocNum.setValue(attDataTMsg.attDataGrpTxt.getValue());
            String attDocTpCd = attDataTMsg.attDocTpCd.getValue();

            // findByCondition [DOC_MGT_CATG]
            DOC_MGT_CATGTMsg docMgtCatgTMsg = new DOC_MGT_CATGTMsg();
            docMgtCatgTMsg.setSQLID("002");
            docMgtCatgTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            docMgtCatgTMsg.setConditionValue("attDocTpCd01", attDocTpCd);
            DOC_MGT_CATGTMsgArray docMgtCatgTMsgs = (DOC_MGT_CATGTMsgArray) S21ApiTBLAccessor.findByCondition(docMgtCatgTMsg);

            if (docMgtCatgTMsgs.getValidCount() == 0 || docMgtCatgTMsgs.length() == 0 || !ZYPCommonFunc.hasValue(docMgtCatgTMsgs.no(0).docMgtCatgCd.getValue())) {
                // Create Message Map
                this.msgMap = new S21ApiMessageMap(pMsg); // 2017/02/03 S21_NA#17392-2 Add
                this.msgMap.addXxMsgId(NWZM2013E);
                this.msgMap.flush(); // 2017/02/03 S21_NA#17392-2 Add
                //return; // 2017/02/03 S21_NA#17392-2 Add // 2017/02/14 S21_NA#16277-2 Del
            } else {
                pMsg.docMgtCatgCd.setValue(docMgtCatgTMsgs.no(0).docMgtCatgCd.getValue());
                execute(pMsg, onBatchType, bizApiType); // 2017/02/14 S21_NA#16277-2 Add
            }
        }
        //execute(pMsg, onBatchType, bizApiType); // 2017/02/14 S21_NA#16277-2 Del

        return pMsg; // 2017/02/14 S21_NA#16277-2 Add
    }


    /**
     * <pre>
     * Therefore Indexed Attribute Update API
     * </pre>
     * @param pMsg    Input parameter.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(NWZC224001PMsg pMsg, final ONBATCH_TYPE onBatchType, String bizApiType) {
        boolean attachFlg = false;
        if (ZYPFileUpDownConst.BIZAPI_TYPE_CREATE.equals(bizApiType)) {
            attachFlg = true;
        }

        // Create Message Map
        this.msgMap = new S21ApiMessageMap(pMsg);

        // Get Therefore web service port instance
        //QC#14569 START
        //IThereforeService thereforeWebService = S21ThereforeWebServiceProxy.getInstance().getThereforeService();
        //QC#14569 END

        // Insert TMsg List
        List<DOC_MGT_INTFC_TRXTMsg> trxTMsgList = new ArrayList<DOC_MGT_INTFC_TRXTMsg>();

        try {
            checkIdxAttrbMandatory();
            if (hasErr()) {
                return;
            }

            if (!checkDocMgtCatg(attachFlg)) {
                return;
            }

            //QC#14569 START
            thereforeConnAvalFlg = ZYPCodeDataUtil.getVarCharConstValue(THEREFORE_CONN_AVAL_FLG, pMsg.glblCmpyCd.getValue());
            if(!ZYPConstant.FLG_ON_Y.equals(thereforeConnAvalFlg)) {
                S21InfoLogOutput.println("NWZC224001 Updating index attributes process was skipped. THEREFORE_CONN_AVAL_FLG is 'N'.");
                return;
            }
            //QC#14569 END

            //QC#16277 START
            // check Therefore Document ID
            // if occur exception, Therefore Document is invalid
            GetDocumentIndexDataParams docParam = new GetDocumentIndexDataParams();
            docParam.setDocNo(pMsg.docMgtDocId.getValueInt());
            S21ThereforeWebServiceProxy.getInstance().getThereforeService().getDocumentIndexData(docParam);
            //QC#16277 END

            if (!DOC_MGT_CATG.CFS_LEASE_PACKAGE.equals(pMsg.docMgtCatgCd.getValue())) {
                Map<String, Object> bizDocResult = getBizDoc(pMsg);
                if (bizDocResult == null) {
                    this.msgMap.addXxMsgId(NWAM0891E);
                    return;
                }
                List<Map<String, Object>> idxAttrbResultList = getIdxAttrbInfo(pMsg, null);
                if (idxAttrbResultList == null || idxAttrbResultList.size() == 0) {
                    //QC#14969 START
//                    this.msgMap.addXxMsgId(NWAM0890E);
                    //QC#14969 END
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
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtBizDocNum, pMsg.docMgtPrntDocNum);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtPrntDocNum, pMsg.docMgtPrntDocNum);
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
                            // 2016/10/25 S21_NA#15115(#15114 expanse) Mod Start
                            // 2019/08/29 QC#52938 Mod Start
                            // valTxt = formatDate(ZYPDateUtil.addDays(valTxt, 1)); 
                            valTxt = formatDate(valTxt); 
                            // 2019/08/29 QC#52938 Mod End
                            // 2016/10/25 S21_NA#15115(#15114 expanse) Mod End
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                            GregorianCalendar c = new GregorianCalendar();
                            c.setTime(sdf.parse(valTxt));
                            data.setDataValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR)
                                                                                                   ,c.get(Calendar.MONTH) + 1     // 2016/10/24 S21_NA#15115(#15114 expanse)
                                                                                                   ,c.get(Calendar.DAY_OF_MONTH)
                                                                                                   ,c.get(Calendar.HOUR_OF_DAY)
                                                                                                   ,c.get(Calendar.MINUTE)
                                                                                                   ,c.get(Calendar.SECOND)
                                                                                                   ,c.get(Calendar.MILLISECOND)
                                                                                                   ,0));
                            S21InfoLogOutput.println("NWZC224001 Updating Date is " + data.getDataValue().toString() +"(" + docMgtFldNum + ").");
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
                SaveDocumentIndexDataQuickResponse response = S21ThereforeWebServiceProxy.getInstance().getThereforeService().saveDocumentIndexDataQuick(param);//QC#14569 MOD
                // Confirm Response result
                System.out.println(response.getLastChangeTime());

                //QC#23598 START
                this.aquNum = (String) bizDocResult.get(COL_AQU_NUM);
                //QC#23598 END

                // WebService Normal End
                for (DOC_MGT_INTFC_TRXTMsg tMsg : trxTMsgList) {
                    ZYPEZDItemValueSetter.setValue(tMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
                }
            } else {
                Map<String, Object> pkgPoHdrResult = getPkgPoHdr(pMsg);
                if (pkgPoHdrResult == null) {
                    this.msgMap.addXxMsgId(NWAM0891E);
                    return;
                }

                List<Map<String, Object>> idxAttrbResultList = getIdxAttrbInfo(pMsg, null);
                if (idxAttrbResultList == null || idxAttrbResultList.size() == 0) {
                    //QC#14969 START
//                    this.msgMap.addXxMsgId(NWAM0890E);
                    //QC#14969 END
                    return;
                }

                // Fetch the latest version of the document
                GetDocumentParams getDocParams = new GetDocumentParams();
                getDocParams.setDocNo(pMsg.docMgtDocId.getValueInt());
                GetDocumentResponse getDocResponse = S21ThereforeWebServiceProxy.getInstance().getThereforeService().getDocument(getDocParams);//QC#14569 MOD
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
                    if (pkgPoHdrResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null
                            && attachFlg) {
                        valTxt = pkgPoHdrResult.get(idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
                    }

                    DOC_MGT_INTFC_TRXTMsg docMgtIntfcTrxTMsg = new DOC_MGT_INTFC_TRXTMsg();

                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    BigDecimal docMgtIntfcTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_INTFC_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtIntfcTrxPk, docMgtIntfcTrxPk);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtDocId, pMsg.docMgtDocId);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtCatgCd, pMsg.docMgtCatgCd);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtBizDocNum, pMsg.docMgtPrntDocNum);
                    ZYPEZDItemValueSetter.setValue(docMgtIntfcTrxTMsg.docMgtPrntDocNum, pMsg.docMgtPrntDocNum);
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
                            // 2019/08/29 QC#52938 Mod Start
                            // valTxt = formatDate(ZYPDateUtil.addDays(valTxt, 1)); 
                            valTxt = formatDate(valTxt); 
                            // 2019/08/29 QC#52938 Mod End
                            // 2016/10/25 S21_NA#15115(#15114 expanse) Mod End
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                            GregorianCalendar c = new GregorianCalendar();
                            c.setTime(sdf.parse(valTxt));
                            data.setDataValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR)
                                                                                                   ,c.get(Calendar.MONTH) + 1     // 2016/10/24 S21_NA#15115(#15114 expanse)
                                                                                                   ,c.get(Calendar.DAY_OF_MONTH)
                                                                                                   ,c.get(Calendar.HOUR_OF_DAY)
                                                                                                   ,c.get(Calendar.MINUTE)
                                                                                                   ,c.get(Calendar.SECOND)
                                                                                                   ,c.get(Calendar.MILLISECOND)
                                                                                                   ,0));
                            S21InfoLogOutput.println("NWZC224001 Updating Date is " + data.getDataValue().toString() +"(" + docMgtFldNum + ").");
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
                        if (!ZYPCommonFunc.hasValue((BigDecimal) pkgPoHdrResult.get(COL_CFS_LEASE_PKG_PO_HDR_PK))) {
                            data.setDataValue(null);
                        } else if (!ZYPCommonFunc.hasValue(valTxt)){
                            ArrayOfWSTableFieldDataRow arrayDataRow = getArrayOfDataRow(pMsg, (BigDecimal) pkgPoHdrResult.get(COL_CFS_LEASE_PKG_PO_HDR_PK), (String) idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM), S21ThereforeWebServiceProxy.getInstance().getThereforeService(), false);
                            data.setDataValue(arrayDataRow);
                        } else {
                            ArrayOfWSTableFieldDataRow arrayDataRow = getArrayOfDataRow(pMsg, (BigDecimal) pkgPoHdrResult.get(COL_CFS_LEASE_PKG_PO_HDR_PK), (String) idxAttrbResult.get(COL_IDX_ATTRB_TRGT_COL_NM), S21ThereforeWebServiceProxy.getInstance().getThereforeService(), true);
                            data.setDataValue(arrayDataRow);
                        }
                        item.setTableIndexData(data);
                    }
                    arrayItems.getWSIndexDataItem().add(item);
                }
                param.setIndexData(additional);

                // Invoke Therefore web service
                SaveDocumentIndexDataResponse response = S21ThereforeWebServiceProxy.getInstance().getThereforeService().saveDocumentIndexData(param);//QC#14569 MOD
                // Confirm Response result
                System.out.println(response.getLastChangeTime());

                // WebService Normal End
                for (DOC_MGT_INTFC_TRXTMsg tMsg : trxTMsgList) {
                    ZYPEZDItemValueSetter.setValue(tMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
                }
            }

            //QC#14927,15194 START
            // DOC_MGT_SCAN_TRX
            insDocMgtScanTrx(S21ThereforeWebServiceProxy.getInstance().getThereforeService(), pMsg);
            //QC#14927,15194 END

        // locked by another user
        } catch (EZDDBRecordLockedException e) {
            throw e;
        } catch (SOAPFaultException e) {
            e.printStackTrace();

            // WebService Error End
            //QC#16277 START
            String docIdErrCode = ZYPCodeDataUtil.getVarCharConstValue(THEREFORE_ERR_INVALID_DOC, pMsg.glblCmpyCd.getValue());
            String errMsg = e.getMessage();
            if (ZYPCommonFunc.hasValue(errMsg)
                    && errMsg.contains(docIdErrCode)) {
                // invalid Therefore Document ID Error
                this.msgMap.addXxMsgId(NWZM2052E);
            } else {
                // WebService Error
                this.msgMap.addXxMsgId(NWZM2053E);
                //QC#24244 ADD START
                insDocMgtAttRqst(pMsg);
                //QC#24244 ADD END
            }
            //QC#16277 END
            for (DOC_MGT_INTFC_TRXTMsg tMsg : trxTMsgList) {
                ZYPEZDItemValueSetter.setValue(tMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new S21AbendException(e);
        } finally {
            // Flush Message Map.
            this.msgMap.flush();
        }

        if (trxTMsgList.size() > 0) {
            insDocMgtIntfcTrx(trxTMsgList);
        }
    }

    /**
     * <pre>
     * Error Check
     * </pre>
     */
    private boolean hasErr() {
        NWZC224001PMsg pMsg = (NWZC224001PMsg) this.msgMap.getPmsg();
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
        NWZC224001PMsg param = (NWZC224001PMsg) this.msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            this.msgMap.addXxMsgId(NWZM0188E);
        }

        if (!ZYPCommonFunc.hasValue(param.docMgtDocId)) {
            this.msgMap.addXxMsgId(NWZM1938E);
        }

        if (!ZYPCommonFunc.hasValue(param.docMgtCatgCd)) {
            this.msgMap.addXxMsgId(NWZM2000E);
        }

        if (!ZYPCommonFunc.hasValue(param.docMgtPrntDocNum)) {
            this.msgMap.addXxMsgId(NWZM1981E);
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
        NWZC224001PMsg param = (NWZC224001PMsg) this.msgMap.getPmsg();

        DOC_MGT_CATGTMsg docMgtCatg = new DOC_MGT_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(docMgtCatg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(docMgtCatg.docMgtCatgCd, param.docMgtCatgCd);
        docMgtCatg = (DOC_MGT_CATGTMsg) EZDTBLAccessor.findByKey(docMgtCatg);
        if (docMgtCatg == null) {
            this.msgMap.addXxMsgId(NWZM2003E);
            return false;
        }

        if (attachFlg) {
            if (ZYPConstant.FLG_OFF_N.equals(docMgtCatg.updIdxAttrbOnAttFlg.getValue())) {
                this.msgMap.addXxMsgId(NWAM0889E);
                return false;
            }
        } else {
            if (ZYPConstant.FLG_OFF_N.equals(docMgtCatg.updIdxAttrbOnDelFlg.getValue())) {
                this.msgMap.addXxMsgId(NWAM0889E);
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
    private Map<String, Object> getBizDoc(NWZC224001PMsg pMsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("docMgtPrntDocNum", pMsg.docMgtPrntDocNum.getValue());
        param.put("attDataTpTherefore", ATT_DATA_TP_THEREFORE);
        param.put("attDocTpThrefore", ATT_DATA_DOC_TP.SALES_ORDER_THEREFORE);

        List<Map<String, Object>> bizDocList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBizDoc", param);
        if (bizDocList == null
                || bizDocList.isEmpty()) {
            return null;
        }

        if (bizDocList.size() == 1) {
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
    private List<Map<String, Object>> getIdxAttrbInfo(NWZC224001PMsg pMsg, String trgtTblNm) {
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
    private void insDocMgtIntfcTrx(List<DOC_MGT_INTFC_TRXTMsg> insTMsgList) {
        int insCnt = insTMsgList.size();
        int resCnt = S21FastTBLAccessor.insert((DOC_MGT_INTFC_TRXTMsg[]) insTMsgList.toArray(new DOC_MGT_INTFC_TRXTMsg[0]));

        if (resCnt != insCnt) {
            msgMap.addXxMsgId(NWZM2009E);
        }
    }

    /**
     * Insert Document Management Scan Transaction
     * @param insDocMgtScanTrx Document Management Scan Transaction List
     */
    private void insDocMgtScanTrx(IThereforeService thereforeWebService, NWZC224001PMsg pMsg) {
        String bizDocNum = null;
        if (ZYPCommonFunc.hasValue(pMsg.docMgtBizDocNum)) {
            bizDocNum = pMsg.docMgtBizDocNum.getValue();
        } else {
            bizDocNum = pMsg.docMgtPrntDocNum.getValue();
        }

        //QC#23439
        //String scanTime = getScanTimeFromTherefore(thereforeWebService, pMsg);
        String scanTime = "";
        BigDecimal leaseDocId = null;  //Lease Document ID
        Map<String, Object> therforeValMap = getScanTimeFromTherefore(thereforeWebService, pMsg);
        if (therforeValMap != null) {
            if (therforeValMap.get(SCANNED_DATE) != null) {
                scanTime = (String) therforeValMap.get(SCANNED_DATE);
            }
            if (therforeValMap.get(LEASE_DOC_ID) != null) {
                leaseDocId = (BigDecimal) therforeValMap.get(LEASE_DOC_ID);
            }
        }
System.out.println("Out:" + SCANNED_DATE + " : " + scanTime);
System.out.println("Out:" + LEASE_DOC_ID + " : " + leaseDocId);
        
        DOC_MGT_SCAN_TRXTMsg docMgtScanTrxTMsg = new DOC_MGT_SCAN_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal docMgtScanTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_SCAN_TRX_SQ);
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtScanTrxPk, docMgtScanTrxPk);
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.cpoOrdNum, pMsg.docMgtPrntDocNum);
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtDocId, pMsg.docMgtDocId);
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtScanTs, scanTime);
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.othSysUrl, this.thereforeUrl + pMsg.docMgtDocId.getValue());
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtCatgCd, pMsg.docMgtCatgCd);
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtBizDocNum, bizDocNum);
        //QC#23598 START
        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.aquNum, this.aquNum);
        //QC#23598 END

        //QC#23439 Waiting for DA Release
        if (ZYPCommonFunc.hasValue(leaseDocId)) {
            ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.poRvwDocId, leaseDocId);
            ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.poRvwSysUrl, this.thereforeUrl + leaseDocId);
        }

        EZDTBLAccessor.insert(docMgtScanTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(docMgtScanTrxTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM2037E);
        }
    }

    //QC#24244 ADD START
    /**
     * Insert Document Management Attachment Request For Retry
     * @param insDocMgtScanTrx Document Management Scan Transaction List
     */
    private void insDocMgtAttRqst(NWZC224001PMsg pMsg) {

        DOC_MGT_CATGTMsg docMgtCatg = new DOC_MGT_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(docMgtCatg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(docMgtCatg.docMgtCatgCd, pMsg.docMgtCatgCd);
        docMgtCatg = (DOC_MGT_CATGTMsg) EZDTBLAccessor.findByKey(docMgtCatg);

        DOC_MGT_ATT_RQSTTMsg docMgtAttRqstTMsg = new DOC_MGT_ATT_RQSTTMsg();

        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtAttRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_ATT_RQST_SQ));
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtDocId, pMsg.docMgtDocId);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtCatgNum, docMgtCatg.docMgtCatgNum);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtPrntDocNum, pMsg.docMgtPrntDocNum);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtBizDocNum, pMsg.docMgtBizDocNum);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtRqstTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtRqstStsCd, DOC_MGT_RQST_STS.NEW);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtAttRqstProcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(docMgtAttRqstTMsg.docMgtAttRtryRqstFlg, ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.insert(docMgtAttRqstTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(docMgtAttRqstTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM2259E);
        }
    }
    //QC#24244 ADD END

    /**
     * <pre>
     * Get CFS_LEASE_PKG_PO_HDR
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return CFS_LEASE_PKG_PO_HDR
     */
    private Map<String, Object> getPkgPoHdr(NWZC224001PMsg pMsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("docMgtPrntDocNum", pMsg.docMgtPrntDocNum.getValue());

        List<Map<String, Object>> pkgPoHdrList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPkgPoHdr", param);
        if (pkgPoHdrList == null || pkgPoHdrList.isEmpty()) {
            return null;
        }

        //if (pkgPoHdrList.size() == 1) { // 2017/02/15 S21_NA#17286 Del
        return pkgPoHdrList.get(0);
        //} // 2017/02/15 S21_NA#17286 Del

        //return null; // 2017/02/15 S21_NA#17286 Del
    }

    /**
     * <pre>
     * Get CFS_LEASE_PKG_PO_HDR
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return CFS_LEASE_PKG_PO_HDR
     */
    private ArrayOfWSTableFieldDataRow getArrayOfDataRow(NWZC224001PMsg    pMsg
                                                         ,BigDecimal        pkgPoHdrPk
                                                         ,String            trgtTblNM
                                                         ,IThereforeService thereforeWebService
                                                         ,Boolean           isAttach)
                                                          throws Exception{
        if (!ZYPCommonFunc.hasValue(pkgPoHdrPk)) {
            return null;
        }

        List<Map<String, Object>> pkgPoDtlList = getPkgPoDtl(pMsg.glblCmpyCd.getValue(), pkgPoHdrPk.toString());

        if (pkgPoDtlList == null) {
            return null;
        }

        List<Map<String, Object>> idxAttrbList = getIdxAttrbInfo(pMsg, trgtTblNM);

        if (idxAttrbList == null || idxAttrbList.size() == 0) {
            return null;
        }

        ArrayOfWSTableFieldDataRow arrayDataRow = new ArrayOfWSTableFieldDataRow();
        int cnt = 0;
        int existCnt = getExistTableRowCnt(thereforeWebService, pMsg.docMgtDocId.getValueInt());

        for (Map<String, Object> pkgPoDtl : pkgPoDtlList) {
            WSTableFieldDataRow dataRow = new WSTableFieldDataRow();
            if (cnt < existCnt) {
                dataRow.setRowNo(cnt);
            }

            ArrayOfWSTableFieldDataRowItem arrayDataRowItems = new ArrayOfWSTableFieldDataRowItem();
            for (Map<String, Object> idxAttrb : idxAttrbList) {
                String valTxt = "";
                if (pkgPoDtl.get(idxAttrb.get(COL_IDX_ATTRB_TRGT_COL_NM)) != null
                        && isAttach) {
                    valTxt = pkgPoDtl.get(idxAttrb.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
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
                        valTxt = formatDate(valTxt);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        GregorianCalendar c = new GregorianCalendar();
                        c.setTime(sdf.parse(valTxt));
                        data.setDataValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR)
                                                                                               ,c.get(Calendar.MONTH) + 1     // QC#23598 S21_NA#15115(#15114 expanse)
                                                                                               ,c.get(Calendar.DAY_OF_MONTH)
                                                                                               ,c.get(Calendar.HOUR_OF_DAY)
                                                                                               ,c.get(Calendar.MINUTE)
                                                                                               ,c.get(Calendar.SECOND)
                                                                                               ,c.get(Calendar.MILLISECOND)
                                                                                               ,0));
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
     * Get CFS_LEASE_PKG_PO_HDR
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return CFS_LEASE_PKG_PO_HDR
     */
    private List<Map<String, Object>> getPkgPoDtl(String glblCmpyCd, String pkgPoHdrPk) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("pkgPoHdrPk", pkgPoHdrPk);

        List<Map<String, Object>> pkgPoDtlList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPkgPoDtl", param);
        if (pkgPoDtlList == null || pkgPoDtlList.isEmpty()) {
            return null;
        }

        return pkgPoDtlList;
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

    /**
     * <pre>
     * Get CFS_LEASE_PKG_PO_HDR
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return CFS_LEASE_PKG_PO_HDR
     */
    // 2019/09/20 QC#52938-1 Mod Start
    // 2019/08/29 QC#52938 Mod Start
    private String formatDate(String dateVal) {
        String convertVal;
        if (dateVal.length() < DATE_LENGTH) {
            convertVal = ZYPCommonFunc.rightPad(dateVal, DATE_LENGTH, "0");
        } else {
            convertVal = dateVal.substring(0, DATE_LENGTH);
        }

        return convertVal;
    }
//    private String formatDate(String dateVal) {
//        String convertVal;
//        if (dateVal.length() == DATE_LENGTH_NO_TIME) {
//            convertVal = ZYPCommonFunc.rightPad(dateVal, DATE_LENGTH, "0");
//        } else if (dateVal.length() < DATE_LENGTH) {
//            convertVal = ZYPLocalTimeUtil.convertTimeSys2UTC(ZYPCommonFunc.rightPad(dateVal, DATE_LENGTH, "0"));
//        } else {
//            convertVal = ZYPLocalTimeUtil.convertTimeSys2UTC(dateVal.substring(0, DATE_LENGTH));
//        }
//
//        return convertVal;
//    }
      // 2019/08/29 QC#52938 Mod End
      // 2019/09/20 QC#52938-1 Mod End

    /**
     * <pre>
     * Get CFS_LEASE_PKG_PO_HDR
     * </pre>
     * @param pMsg      NWZC223001PMsg
     * @return CFS_LEASE_PKG_PO_HDR
     */
    //private String getScanTimeFromTherefore(IThereforeService thereforeWebService, NWZC224001PMsg pMsg) {
    private Map<String, Object> getScanTimeFromTherefore(IThereforeService thereforeWebService, NWZC224001PMsg pMsg) {
        GetDocumentIndexDataParams docParam = new GetDocumentIndexDataParams();
        docParam.setDocNo(pMsg.docMgtDocId.getValueInt());

        GetDocumentIndexDataResponse docRes = thereforeWebService.getDocumentIndexData(docParam);
        ArrayOfWSIndexDataItem arrayItems = docRes.getIndexData().getIndexDataItems();
        List<WSIndexDataItem> ItemList = arrayItems.getWSIndexDataItem();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("docMgtCatgCd", pMsg.docMgtCatgCd.getValue());
        ssmParam.put("docMgtFldDescTxtList", GET_SEARCH_RESULT_FLD_NM_LIST);

        List<Map<String, Object>> resultSsmResult = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSearchDocMgtFld", ssmParam);
        if (resultSsmResult == null || resultSsmResult.isEmpty()) {
            return null;
        }

        Map<String, Object> retMap = new HashMap<String, Object>();
        String scanTime = null;
        for (Map<String, Object> result : resultSsmResult) {
            for (WSIndexDataItem item : ItemList) {
                //QC#23439
                if (LEASE_DOC_ID.equals(result.get(COL_DOC_MGT_FLD_DESC_TXT))) {
                    WSIndexDataInt leaseDocIdData = item.getIntIndexData();
                    if (leaseDocIdData == null) {
                        continue;
                    }
                    int leaseDocIdFldNum = leaseDocIdData.getFieldNo();
                    int defFldNum  = Integer.valueOf(result.get(COL_DOC_MGT_FLD_NUM).toString());
                    if (leaseDocIdFldNum != defFldNum) {
                        continue;
                    }
                    if (leaseDocIdData.getDataValue() != null) {
                        BigDecimal val = new BigDecimal(leaseDocIdData.getDataValue().toString());
System.out.println("Lease Doc ID[" + val + "]");
                        retMap.put(LEASE_DOC_ID, val);
                    } else {
System.out.println("Lease Doc ID[No Data]");
                    }
                } else if (SCANNED_DATE.equals(result.get(COL_DOC_MGT_FLD_DESC_TXT)) || SCAN_DATE.equals(result.get(COL_DOC_MGT_FLD_DESC_TXT))) {
                    WSIndexDataDate date = item.getDateIndexData();
                    if (date == null) {
                        continue;
                    }

                    int dateFldNum = date.getFieldNo();
                    int defFldNum  = Integer.valueOf(result.get(COL_DOC_MGT_FLD_NUM).toString());
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
System.out.println("Scan Date[" + scanTime + "]");
                    retMap.put(SCANNED_DATE, scanTime);
                }

            }
        }

        //return scanTime;
        return retMap;
    }

    /**
     * nvl BigDecimal
     * @param val BigDecimal
     * @return int
     */
    public static int nvlBigDecimal(BigDecimal val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val.intValue();
        }
        return 0;
    }
}
