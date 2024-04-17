/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB255001;

import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.ATTACH_MODE_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_AQU_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_ATT_DOC_TP_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_DOC_MGT_ATT_RQST_PK;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_DOC_MGT_ATT_RTRY_RQST_FLG;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_DOC_MGT_BIZ_DOC_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_DOC_MGT_CATG_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_DOC_MGT_CATG_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_DOC_MGT_DOC_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.COL_DOC_MGT_PRNT_DOC_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.CONST_NM_THEREFORE_COND_FLD;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.NWAM0741E;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.NWAM0877E;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.NWAM0878E;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.NWAM0879E;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.NWAM0885E;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.NWAM0886E;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.NWAM0887E;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.ORD_ENTRY_BIZ_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.ORD_ENTRY_BIZ_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.THEREFORE_ATT_BIZ_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NWA.NWAB255001.constant.NWAB255001Constant.ZZZM9025E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.soap.SOAPFaultException;

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryParams;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryResponse;
import net.therefore.schemas.webservices.interop.v0001.types.QueryObject;
import net.therefore.schemas.webservices.interop.v0001.types.WSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.WSQueryResultRow;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DOC_MGT_ATT_RQSTTMsg;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_FLDTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NWZC224001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC224001.NWZC224001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_RQST_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;

/**
 *<pre>
 * NWCB2540:Therefore Order Indexed Attributes Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/24   Fujitsu         W.Honda         Create          N/A
 * 2016/10/03   Fujitsu         W.Honda         Update          QC#14969
 * 2016/10/19   Fujitsu         W.Honda         Update          QC#15194
 * 2017/01/19   Fujitsu         W.Honda         Update          QC#16833
 * 2018/02/08   Fujitsu         W.Honda         Update          QC#24009
 * 2018/04/23   Fujitsu         W.Honda         Update          QC#24244
 * 2018/07/02   Fujitsu         H.Nagashima     Update          QC#26963
 *</pre>
 */

public class NWAB255001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** normal count */
    private int normalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NWAB255001().executeBatch(NWAB255001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code"});
        }

        // S21SsmBatchClient
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        List<Map<String, Object>> attRqstResultList = getAttRqst();

        int workNormalCnt = 0;
        int workErrorCnt = 0;

        for (Map<String, Object> attRqstResult : attRqstResultList) {
            boolean isErr = false;
            if (!ZYPCommonFunc.hasValue((String) attRqstResult.get(COL_DOC_MGT_CATG_CD))) {
                String[] paramArray = new String[] {(String) attRqstResult.get(COL_DOC_MGT_CATG_CD)};
                String errMsgText = S21MessageFunc.clspGetMessage(NWAM0878E, paramArray);
                writeLogLn(errMsgText);
                isErr = true;
                updateAttRqst((BigDecimal) attRqstResult.get(COL_DOC_MGT_ATT_RQST_PK), isErr);
            } else if (!ZYPCommonFunc.hasValue((String) attRqstResult.get(COL_ATT_DOC_TP_CD))) {
                String[] paramArray = new String[] {(String) attRqstResult.get(COL_DOC_MGT_CATG_CD)};
                String errMsgText = S21MessageFunc.clspGetMessage(NWAM0879E, paramArray);
                writeLogLn(errMsgText);
                isErr = true;
                updateAttRqst((BigDecimal) attRqstResult.get(COL_DOC_MGT_ATT_RQST_PK), isErr);
            } else {
                List<Map<String, Object>> bizDocList = getBizDoc(attRqstResult);
                if (bizDocList == null || bizDocList.isEmpty()) {
                    String[] paramArray = new String[] {"DS_ORD_V"
                                                        , String.format("DOC_MGT_CATG_NUM = %s, DOC_MGT_PRNT_DOC_NUM = %s, DOC_MGT_PRNT_DOC_NUM = %s"
                                                        , nvlBigDecimal((BigDecimal) attRqstResult.get(COL_DOC_MGT_CATG_NUM))
                                                        , (String) attRqstResult.get(COL_DOC_MGT_PRNT_DOC_NUM)
                                                        , (String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM))};
                    String errMsgText = S21MessageFunc.clspGetMessage(NWAM0877E, paramArray);
                    writeLogLn(errMsgText);
                    isErr = true;
                    updateAttRqst((BigDecimal) attRqstResult.get(COL_DOC_MGT_ATT_RQST_PK), isErr);
                //QC#14969 START
//                }
                } else {
                //QC#14969 END

                    int cnt = 0;
                    for (Map<String, Object> bizDoc : bizDocList) {

                        //QC#24244 START
                        if (ZYPConstant.FLG_OFF_N.equals((String) attRqstResult.get(COL_DOC_MGT_ATT_RTRY_RQST_FLG))) {
                        //QC#24244 END
                            // Create API Parameter
                            ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
                            params.setBusinessId(ORD_ENTRY_BIZ_ID);
                            params.setAttDataGrp((String) bizDoc.get(COL_CPO_ORD_NUM));
                            params.setBusinessNm(ORD_ENTRY_BIZ_NM);
                            params.setAttDocTpCd((String) attRqstResult.get(COL_ATT_DOC_TP_CD));
                            params.setAttDataKeyNm((String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM));
                            params.setThereforeDocId(nvlBigDecimal((BigDecimal) attRqstResult.get(COL_DOC_MGT_DOC_ID)));

                            NWZC224001PMsg pMsg = new NWZC224001PMsg();
                            if (cnt == 0) {
                                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtDocId, (BigDecimal) attRqstResult.get(COL_DOC_MGT_DOC_ID));
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtCatgCd, (String) attRqstResult.get(COL_DOC_MGT_CATG_CD));
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtPrntDocNum, (String) bizDoc.get(COL_CPO_ORD_NUM));
                                //QC#15194 START
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtBizDocNum, (String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM));
                                //QC#15194 END

                                params.setBizApiIdTherefore(THEREFORE_ATT_BIZ_ID);
                                params.setArgsForBizApiTherefore(new Object[] {pMsg, ONBATCH_TYPE.BATCH, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE});
                            }

                            // Call Therefore Doc Attach API
                            int attDataKey = ZYPFileUpDown.uploadTherefore(params);

                            // error handling
                            if (attDataKey == -1) {
                                String[] paramArray = new String[] {};
                                String errMsgText = S21MessageFunc.clspGetMessage(NWAM0887E, paramArray);
                                writeLogLn(errMsgText);
                                isErr = true;
                                continue;
                            }

                            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                            for (int i = 0; i < msgList.size(); i++) {
                                S21ApiMessage msg = msgList.get(i);
                                String msgId = msg.getXxMsgid();
                                String errMsgText = S21MessageFunc.clspGetMessage(msgId);
                                writeLogLn(errMsgText);
                                isErr = true;
                            }

                            cnt++;
                        //QC#24244 START
                        } else {
                            // QC#26963 2018/07/02 mod Start
//                            BigDecimal docId = (BigDecimal) bizDoc.get(COL_DOC_MGT_DOC_ID);
                            BigDecimal docId = (BigDecimal) attRqstResult.get(COL_DOC_MGT_DOC_ID);
                            // QC#26963 2018/07/02 mod End

                            boolean haveDocIdFlg = true;
                            if (!ZYPCommonFunc.hasValue(docId)) {
                                haveDocIdFlg = false;
                                Integer thereforeDocId = null;
                                try {
                                    thereforeDocId = getDocId(bizDoc);
                                } catch (SOAPFaultException e) {
                                    String[] paramArray = new String[] {"THEREFORE"
                                            , String.format("DOC_MGT_DOC_ID, DOC_MGT_CATG_NUM = %s, DOC_MGT_PRNT_DOC_NUM = %s, DOC_MGT_PRNT_DOC_NUM = %s"
                                            , nvlBigDecimal((BigDecimal) attRqstResult.get(COL_DOC_MGT_CATG_NUM))
                                            , (String) attRqstResult.get(COL_DOC_MGT_PRNT_DOC_NUM)
                                            , (String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM))};
                                    String errMsgText = S21MessageFunc.clspGetMessage(NWAM0877E, paramArray);
                                    writeLogLn(errMsgText);
                                    isErr = true;

                                    DOC_MGT_ATT_RQSTTMsg rqstTMsg = new DOC_MGT_ATT_RQSTTMsg();
                                    ZYPEZDItemValueSetter.setValue(rqstTMsg.glblCmpyCd, getGlobalCompanyCode());
                                    ZYPEZDItemValueSetter.setValue(rqstTMsg.docMgtAttRqstPk, (BigDecimal) attRqstResult.get(COL_DOC_MGT_ATT_RQST_PK));
                                    rqstTMsg = (DOC_MGT_ATT_RQSTTMsg) S21ApiTBLAccessor.findByKey(rqstTMsg);

                                    ZYPEZDItemValueSetter.setValue(rqstTMsg.docMgtAttRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_ATT_RQST_SQ));
                                    EZDTBLAccessor.insert(rqstTMsg);

                                    continue;
                                }

                                docId = BigDecimal.valueOf(thereforeDocId);
                            }

                            if (haveDocIdFlg) {
                                NWZC224001PMsg pMsg = new NWZC224001PMsg();

                                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtDocId, docId);
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtCatgCd, (String) attRqstResult.get(COL_DOC_MGT_CATG_CD));
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtPrntDocNum, (String) bizDoc.get(COL_CPO_ORD_NUM));
                                ZYPEZDItemValueSetter.setValue(pMsg.docMgtBizDocNum, (String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM));
                                ZYPEZDItemValueSetter.setValue(pMsg.procModeCd, ATTACH_MODE_CD);

                                NWZC224001 api = new NWZC224001();
                                api.execute(pMsg, ONBATCH_TYPE.BATCH);

                                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                                for (int i = 0; i < msgList.size(); i++) {
                                    S21ApiMessage msg = msgList.get(i);
                                    String msgId = msg.getXxMsgid();
                                    String errMsgText = S21MessageFunc.clspGetMessage(msgId);
                                    writeLogLn(errMsgText);
                                    isErr = true;
                                }
                            } else {
                                // Create API Parameter
                                ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
                                params.setBusinessId(ORD_ENTRY_BIZ_ID);
                                params.setAttDataGrp((String) bizDoc.get(COL_CPO_ORD_NUM));
                                params.setBusinessNm(ORD_ENTRY_BIZ_NM);
                                params.setAttDocTpCd((String) attRqstResult.get(COL_ATT_DOC_TP_CD));
                                params.setAttDataKeyNm((String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM));
                                params.setThereforeDocId(nvlBigDecimal(docId));

                                NWZC224001PMsg pMsg = new NWZC224001PMsg();
                                if (cnt == 0) {
                                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                                    ZYPEZDItemValueSetter.setValue(pMsg.docMgtDocId, docId);
                                    ZYPEZDItemValueSetter.setValue(pMsg.docMgtCatgCd, (String) attRqstResult.get(COL_DOC_MGT_CATG_CD));
                                    ZYPEZDItemValueSetter.setValue(pMsg.docMgtPrntDocNum, (String) bizDoc.get(COL_CPO_ORD_NUM));
                                    //QC#15194 START
                                    ZYPEZDItemValueSetter.setValue(pMsg.docMgtBizDocNum, (String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM));
                                    //QC#15194 END

                                    params.setBizApiIdTherefore(THEREFORE_ATT_BIZ_ID);
                                    params.setArgsForBizApiTherefore(new Object[] {pMsg, ONBATCH_TYPE.BATCH, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE});
                                }

                                // Call Therefore Doc Attach API
                                int attDataKey = ZYPFileUpDown.uploadTherefore(params);

                                // error handling
                                if (attDataKey == -1) {
                                    String[] paramArray = new String[] {};
                                    String errMsgText = S21MessageFunc.clspGetMessage(NWAM0887E, paramArray);
                                    writeLogLn(errMsgText);
                                    isErr = true;
                                    continue;
                                }

                                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                                for (int i = 0; i < msgList.size(); i++) {
                                    S21ApiMessage msg = msgList.get(i);
                                    String msgId = msg.getXxMsgid();
                                    String errMsgText = S21MessageFunc.clspGetMessage(msgId);
                                    writeLogLn(errMsgText);
                                    isErr = true;
                                }
                            }
                        }
                        //QC#24244 END
                    }
                //QC#14969 START
                }
                //QC#14969 END
            }


            if (isErr) {
                workErrorCnt = workErrorCnt + 1;
            } else {
                workNormalCnt = workNormalCnt + 1;
            }
            updateAttRqst((BigDecimal) attRqstResult.get(COL_DOC_MGT_ATT_RQST_PK), isErr);
        }

        this.normalCount = workNormalCnt;
        this.errorCount = workErrorCnt;
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * <pre>
     * Get Attach Request
     * </pre>
     * @return Attach Request
     */
    private List<Map<String, Object>> getAttRqst() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("rqstStsCd", DOC_MGT_RQST_STS.NEW);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAttRqst", param);
    }

    /**
     * <pre>
     * Get Business Document
     * </pre>
     * @param pMsg NWZC223001PMsg
     * @return Business Document
     */
    private List<Map<String, Object>> getBizDoc(Map<String, Object> attRqstResult) {
        List<String> cpoOrdNumList = getCpoOrdNum(attRqstResult);

        if (cpoOrdNumList == null || cpoOrdNumList.isEmpty() || cpoOrdNumList.size() == 0) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("cpoOrdNumList", cpoOrdNumList);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBizDoc", param);
    }

    /**
     * <pre>
     * get CPO Order Number
     * </pre>
     * @param pMsg NWZC223001PMsg
     */

    private List<String> getCpoOrdNum(Map<String, Object> attRqstResult) {
        List<String> cpoOrdNumList = new ArrayList<String>();
        String prntDocNum = (String) attRqstResult.get(COL_DOC_MGT_PRNT_DOC_NUM);
        String docMgtCatgCd = (String) attRqstResult.get(COL_DOC_MGT_CATG_CD);
        String bizDocNum = (String) attRqstResult.get(COL_DOC_MGT_BIZ_DOC_NUM);

        if (hasValue(prntDocNum)) {
            cpoOrdNumList.add(prntDocNum);
            return cpoOrdNumList;

        } else if (DOC_MGT_CATG.SALES_ORDER.equals(docMgtCatgCd)) {// QC#16833 MOD
            cpoOrdNumList.add(bizDocNum);
            return cpoOrdNumList;

        } else if (DOC_MGT_CATG.PROOF_OF_DELIVERY.equals(docMgtCatgCd)) {
            cpoOrdNumList = getCpoOrdNumBySoNum(bizDocNum);

        } else if (DOC_MGT_CATG.CFS_LEASE_PACKAGE.equals(docMgtCatgCd)) {
            cpoOrdNumList = getCpoOrdNumByCfsLeasePkgNum(bizDocNum);

        // QC#16833 START
        } else if (DOC_MGT_CATG.RMA.equals(docMgtCatgCd)) {
            cpoOrdNumList = getCpoOrdNumByRwsRefNum(bizDocNum);
        // QC#16833 END

        }

        return cpoOrdNumList;
    }

    private List<String> getCpoOrdNumBySoNum(String bizDocNum) {
        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.setSQLID("037");
        shpgPlnTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        shpgPlnTMsg.setConditionValue("soNum01", bizDocNum);
        SHPG_PLNTMsgArray shpgPlnTMsgs = (SHPG_PLNTMsgArray) findByCondition(shpgPlnTMsg);

        if (shpgPlnTMsgs.getValidCount() == 0 || shpgPlnTMsgs.length() == 0 || !hasValue(shpgPlnTMsgs.no(0).trxHdrNum.getValue())) {
            String[] paramArray = new String[] {};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0885E, paramArray);
            writeLogLn(errMsgText);
            return null;
        } else {
            return Arrays.asList(shpgPlnTMsgs.no(0).trxHdrNum.getValue());
        }
    }

    private List<String> getCpoOrdNumByCfsLeasePkgNum(String bizDocNum) {
        String cfsLeasePkgNum = bizDocNum;
        String[] cfsAppPoNum = null;

        if (cfsLeasePkgNum.matches(".*" + "/" + ".*")) {
            cfsAppPoNum = cfsLeasePkgNum.split("/", 0);
        // QC#24009 START
//        }
        } else {
            String[] paramArray = new String[] {};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0886E, paramArray);
            writeLogLn(errMsgText);
            return null;
        }
        // QC#24009 END
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("cfsAppNum", cfsAppPoNum[0]);
        param.put("cfsPoNum", cfsAppPoNum[1]);

        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getCpoOrdNumByCfsLeasePkgNum", param);
        if (rsltList != null && rsltList.size() > 0) {
            List<String> cpoOrdNumList = new ArrayList<String>();

            for (int i = 0; i < rsltList.size(); i++) {
                cpoOrdNumList.add(rsltList.get(i).get("CPO_ORD_NUM"));
            }
            return cpoOrdNumList;
        } else {
            String[] paramArray = new String[] {};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0886E, paramArray);
            writeLogLn(errMsgText);
            return null;
        }
    }

    // QC#16833 START
    private List<String> getCpoOrdNumByRwsRefNum(String bizDocNum) {
        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
        rwsHdrTMsg.setSQLID("017");
        rwsHdrTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        rwsHdrTMsg.setConditionValue("rwsRefNum01", bizDocNum);
        RWS_HDRTMsgArray rwsHdrTMsgs = (RWS_HDRTMsgArray) findByCondition(rwsHdrTMsg);

        if (rwsHdrTMsgs.getValidCount() == 0 || rwsHdrTMsgs.length() == 0 || !hasValue(rwsHdrTMsgs.no(0).trxOrdNum.getValue())) {
            String[] paramArray = new String[] {};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0885E, paramArray);
            writeLogLn(errMsgText);
            return null;
        } else {
            return Arrays.asList(rwsHdrTMsgs.no(0).trxOrdNum.getValue());
        }
    }
    // QC#16833 END

    /**
     * updateCfsLeasePkgPoHdr
     * @param poHdrPk BigDecimal
     */
    private void updateAttRqst(BigDecimal attRqstPk, boolean isErr) {

        // update CFS_LEASE_PKG_PO_HDR
        DOC_MGT_ATT_RQSTTMsg attRqst = new DOC_MGT_ATT_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(attRqst.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(attRqst.docMgtAttRqstPk, attRqstPk);

        attRqst = (DOC_MGT_ATT_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(attRqst);
        if (attRqst == null) {
            String[] paramArray = new String[] {"DOC_MGT_ATT_RQST", String.format("DOC_MGT_ATT_RQST_PK = %s", nvlBigDecimal(attRqstPk))};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0877E, paramArray);
            writeLogLn(errMsgText);
            return;
        }

        ZYPEZDItemValueSetter.setValue(attRqst.docMgtAttRqstProcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(attRqst.docMgtProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        if (isErr) {
            ZYPEZDItemValueSetter.setValue(attRqst.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(attRqst.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
        }

        EZDTBLAccessor.update(attRqst);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(attRqst.getReturnCode())) {
            String[] paramArray = new String[] {"DOC_MGT_ATT_RQST", String.format("DOC_MGT_ATT_RQST_PK = %s", nvlBigDecimal(attRqstPk).toString())};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0741E, paramArray);
            writeLogLn(errMsgText);
            return;
        }

        return;
    }

    //QC#24244 START
    /**
     * getDocId
     * @param bizDoc Map<String, Object>
     */
    private Integer getDocId(Map<String, Object> bizDoc) {

        // call Therefore Web Service
        Integer thereforeDocId = null;

        IThereforeService thereforWebSerive = S21ThereforeWebServiceProxy.getInstance().getThereforeService();

        DOC_MGT_CATGTMsg catg = new DOC_MGT_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(catg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(catg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
        catg = (DOC_MGT_CATGTMsg) S21ApiTBLAccessor.findByKey(catg);
        if (catg == null || !ZYPCommonFunc.hasValue(catg.docMgtCatgNum)) {

            return null;
        }

        String searchCondFldCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_THEREFORE_COND_FLD, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(searchCondFldCd)) {

            return null;
        }

        DOC_MGT_FLDTMsg fld = new DOC_MGT_FLDTMsg();
        ZYPEZDItemValueSetter.setValue(fld.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(fld.docMgtFldCd, searchCondFldCd);
        fld = (DOC_MGT_FLDTMsg) S21ApiTBLAccessor.findByKey(fld);
        if (fld == null || !ZYPCommonFunc.hasValue(fld.docMgtFldNum)) {
            return null;
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

        condition.setCondition((String) bizDoc.get(COL_AQU_NUM));

        condition.setFieldNoOrName(fld.docMgtFldNum.getValue().toPlainString());
        conditions.getWSCondition().add(condition);

        // Invoke Therefore web service
        String startDt = ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm:ss:SSS");
        writeLogLn("Invoke Therefore web service Start Time : %s", startDt);

        // Call Web Service
        ExecuteSingleQueryResponse response = thereforWebSerive.executeSingleQuery(params);

        String endDt = ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm:ss:SSS");
        writeLogLn("Invoke Therefore web service End Time : %s", endDt);
        java.util.List<WSQueryResultRow> rows = response.getQueryResult().getResultRows().getWSQueryResultRow();
        if (rows.size() > 0) {

            thereforeDocId = rows.get(0).getDocNo();
        }

        return thereforeDocId;
    }
    //QC#24244 END

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
     * writeLogLn
     * @param format String
     * @param param Object...
     */
    private static String nvlBigDecimal(BigDecimal val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val.toString();
        }

        return null;
    }
}
