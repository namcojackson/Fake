/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC223001;

import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM1938E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM1939E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM1941E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM1999E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM2000E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM2002E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM2006E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWZM2038E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.NWAM0546E;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.ORD_ENTRY_BIZ_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.ORD_ENTRY_BIZ_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant.VAR_CHAR_CONST_NWZC2230_THEREFORE_ASYNC_FLG;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_CATGTMsgArray;
import business.db.DOC_MGT_ATT_RQSTTMsg;
import business.db.DOC_MGT_ATT_RQSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NWZC223001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC223001.constant.NWZC223001Constant;
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
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Auto Document Attachment API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         Kamide          Create
 * 2016/08/25   Fujitsu         S.Iidaka        Update          Unit Test#201
 * 2018/04/23   Fujitsu         W.Honda         Update          QC#24244
 * </pre>
 */
public class NWZC223001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** SSM client */
    private S21SsmBatchClient ssmBatchClient;

    /** CPO Order Number Array*/
    private List<String> cpoOrdNumList;

    /** Document Management Category Code */
    private String docMgtCatgCd;

    /** Attached Document Type Code */
    private String attDocTpCd;

    /**
     * Constructor
     */
    public NWZC223001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Auto Document Attachment API (List)
     * </pre>
     * @param pMsgList Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NWZC223001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NWZC223001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * <pre>
     * Auto Document Attachment API
     * </pre>
     * @param pMsg Input parameter.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final NWZC223001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        // Create Message Map
        msgMap = new S21ApiMessageMap(pMsg);

        try {
            // Parameter Check
            vldMandatoryCheck();
            if (hasErr()) {
                return;
            }

            if (isAsyncProc(pMsg)) {
                insertDocMgtAttRqst(pMsg);

            } else {
                executeAutoDocAtt(pMsg);
            }

            // locked by another user
        } catch (EZDDBRecordLockedException e) {
            throw e;

        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * Check has error or not.(error exist then true) 
     * </pre>
     * @return result
     */
    private boolean hasErr() {

        NWZC223001PMsg pMsg = (NWZC223001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Validate Mandatory Parameters Check
     * </pre>
     * @param
     */
    private void vldMandatoryCheck() {

        NWZC223001PMsg pMsg = (NWZC223001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NWZM0188E);
        }

        if (!hasValue(pMsg.docMgtDocId)) {
            setErrMsg(pMsg, NWZM1938E);
        }

        if (!hasValue(pMsg.docMgtCatgNum)) {
            setErrMsg(pMsg, NWZM1939E);
        }

        if (!hasValue(pMsg.docMgtBizDocNum)) {
            setErrMsg(pMsg, NWZM1941E);
        }

        return;
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg NWZC223001PMsg
     * @param msgId String
     */
    private void setErrMsg(NWZC223001PMsg pMsg, String msgId) {
        setErrMsg(pMsg, msgId, null);
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg NWZC223001PMsg
     * @param msgId String
     * @param msgPrms String[]
     */
    private void setErrMsg(NWZC223001PMsg pMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    /**
     * <pre>
     * Cut Error Message
     * </pre>
     * @param msg String
     * @return Error Message String within MAX_MSG_LEN
     */
    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > NWZC223001Constant.MAX_MSG_LEN) {
            return msg.substring(0, NWZC223001Constant.MAX_MSG_LEN);
        }
        return msg;
    }

    /**
     * <pre>
     * Get Business Document
     * </pre>
     * @param pMsg NWZC223001PMsg
     * @return Business Document
     */
    private List<Map<String, Object>> getBizDoc(NWZC223001PMsg pMsg) {
        getCpoOrdNum(pMsg);

        if (cpoOrdNumList == null || cpoOrdNumList.isEmpty() || cpoOrdNumList.size() == 0) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("cpoOrdNumList", cpoOrdNumList);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBizDoc", param);
    }

    /**
     * <pre>
     * Get Index Attribute Info
     * </pre>
     * @param pMsg NWZC223001PMsg
     * @return Index Attribute Info List
     */
    private List<Map<String, Object>> getIdxAttrbInfo(NWZC223001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("docMgtCatgNum", pMsg.docMgtCatgNum.getValue());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getIdxAttrbInfo", param);
    }

    /**
     * <pre>
     * Execute Auto Document Attach Process
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void executeAutoDocAtt(NWZC223001PMsg pMsg) {
        List<Map<String, Object>> bizDocList = getBizDoc(pMsg);
        if (bizDocList == null || bizDocList.isEmpty() || bizDocList.size() == 0) {
            setErrMsg(pMsg, NWZM1999E);
            return;
        }
        List<Map<String, Object>> idxAttrbList = getIdxAttrbInfo(pMsg);

        for (int i = 0; i < idxAttrbList.size(); i++) {
            Map<String, Object> idxAttrbMap = idxAttrbList.get(i);

            String colNm = idxAttrbMap.get("IDX_ATTRB_TRGT_COL_NM").toString();
            if (ZYPCommonFunc.hasValue(colNm)) {
                BigDecimal docMgtFldNum = (BigDecimal) idxAttrbMap.get("DOC_MGT_FLD_NUM");
                String docMgtFldValTxt = null;
                if (bizDocList.get(0).get(colNm) != null) {
                    docMgtFldValTxt = bizDocList.get(0).get(colNm).toString();
                }

                ZYPEZDItemValueSetter.setValue(pMsg.xxAttrbList.no(i).docMgtFldNum, docMgtFldNum);
                ZYPEZDItemValueSetter.setValue(pMsg.xxAttrbList.no(i).docMgtFldValTxt, docMgtFldValTxt);

                pMsg.xxAttrbList.setValidCount(pMsg.xxAttrbList.getValidCount() + 1);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));

        // Call Therefore Doc Attach API
        callAPI(pMsg);
    }

    /**
     * <pre>
     * Call Therefore Doc Attach API
     * </pre>
     * @param pMsg NWZC223001PMsg
     */
    private void callAPI(NWZC223001PMsg pMsg) {
        // Create API Param
        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        params.setBusinessId(ORD_ENTRY_BIZ_ID);
//        params.setAttDataGrp(cpoOrdNumList.get(0));
        params.setBusinessNm(ORD_ENTRY_BIZ_NM);
        params.setAttDocTpCd(attDocTpCd);
        params.setThereforeDocId(pMsg.docMgtDocId.getValue().toString());
        params.setAttDataKeyNm(pMsg.docMgtBizDocNum.getValue().toString());

        // Call Therefore Doc Attach API
        for (int i = 0; i < cpoOrdNumList.size(); i++) {
            params.setAttDataGrp(cpoOrdNumList.get(i));
            int attDataKey = ZYPFileUpDown.uploadTherefore(params);

            // error handling
            if (attDataKey == -1) {
                setErrMsg(pMsg, NWZM2002E);
            }
        }

    }

    /**
     * <pre>
     * get CPO Order Number
     * </pre>
     * @param pMsg NWZC223001PMsg
     */

    private void getCpoOrdNum(NWZC223001PMsg pMsg) {

        // get DOC_MGT_CATG_CD/ATT_DOC_TP_CD from DOC_MGT_CATG_NUM
        DOC_MGT_CATGTMsg docMgtCatgTMsg = new DOC_MGT_CATGTMsg();
        docMgtCatgTMsg.setSQLID("001");
        docMgtCatgTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        docMgtCatgTMsg.setConditionValue("docMgtCatgNum01", pMsg.docMgtCatgNum.getValue());
        DOC_MGT_CATGTMsgArray docMgtCatgTMsgs = (DOC_MGT_CATGTMsgArray) findByCondition(docMgtCatgTMsg);

        if (docMgtCatgTMsgs.getValidCount() == 0 || docMgtCatgTMsgs.length() == 0 || !hasValue(docMgtCatgTMsgs.no(0).docMgtCatgCd.getValue())) {
            setErrMsg(pMsg, NWZM2000E);
        } else {
            docMgtCatgCd = docMgtCatgTMsgs.no(0).docMgtCatgCd.getValue();
            attDocTpCd = docMgtCatgTMsgs.no(0).attDocTpCd.getValue();
        }

        if (hasValue(pMsg.docMgtPrntDocNum)) {
            cpoOrdNumList = Arrays.asList(pMsg.docMgtPrntDocNum.getValue());

        } else if (DOC_MGT_CATG.SALES_ORDER.equals(docMgtCatgCd) || DOC_MGT_CATG.RMA.equals(docMgtCatgCd)) {
            cpoOrdNumList = Arrays.asList(pMsg.docMgtBizDocNum.getValue());

        } else if (DOC_MGT_CATG.PROOF_OF_DELIVERY.equals(docMgtCatgCd)) {
            getCpoOrdNumBySoNum(pMsg);

        } else if (DOC_MGT_CATG.CFS_LEASE_PACKAGE.equals(docMgtCatgCd)) {
            getCpoOrdNumByCfsLeasePkgNum(pMsg);
        }
    }

    private void getCpoOrdNumBySoNum(NWZC223001PMsg pMsg) {
        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.setSQLID("037");
        shpgPlnTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        shpgPlnTMsg.setConditionValue("soNum01", pMsg.docMgtBizDocNum.getValue());
        SHPG_PLNTMsgArray shpgPlnTMsgs = (SHPG_PLNTMsgArray) findByCondition(shpgPlnTMsg);

        if (shpgPlnTMsgs.getValidCount() == 0 || shpgPlnTMsgs.length() == 0 || !hasValue(shpgPlnTMsgs.no(0).trxHdrNum.getValue())) {
            setErrMsg(pMsg, NWZM2006E);
        } else {
            cpoOrdNumList = Arrays.asList(shpgPlnTMsgs.no(0).trxHdrNum.getValue());
        }
    }

    private void getCpoOrdNumByCfsLeasePkgNum(NWZC223001PMsg pMsg) {
        String cfsLeasePkgNum = pMsg.docMgtBizDocNum.getValue();
        String[] cfsAppPoNum = null;

        if (cfsLeasePkgNum.matches(".*" + "/" + ".*")) {
            cfsAppPoNum = cfsLeasePkgNum.split("/", 0);
        }
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("cfsAppNum", cfsAppPoNum[0]);
        param.put("cfsPoNum", cfsAppPoNum[1]);

        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getCpoOrdNumByCfsLeasePkgNum", param);
        if (rsltList != null && rsltList.size() > 0) {
            cpoOrdNumList = new ArrayList<String>();

            for (int i = 0; i < rsltList.size(); i++) {
                cpoOrdNumList.add(rsltList.get(i).get("CPO_ORD_NUM"));
            }
        } else {
            setErrMsg(pMsg, NWZM2038E);
        }
    }

    private boolean isAsyncProc(NWZC223001PMsg pMsg) {
        String thereforAsyncProcFlg = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NWZC2230_THEREFORE_ASYNC_FLG, pMsg.glblCmpyCd.getValue());
        return (ZYPConstant.FLG_ON_Y.equals(thereforAsyncProcFlg));
    }


    private void insertDocMgtAttRqst(NWZC223001PMsg pMsg) {
        DOC_MGT_ATT_RQSTTMsg tMsg = new DOC_MGT_ATT_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtAttRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_ATT_RQST_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtAttRqstProcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtBizDocNum, pMsg.docMgtBizDocNum.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtCatgNum, pMsg.docMgtCatgNum.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtDocId, pMsg.docMgtDocId.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.NEW);
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtPrntDocNum, pMsg.docMgtPrntDocNum.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtRqstStsCd, DOC_MGT_RQST_STS.NEW);
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtRqstTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        //QC#24244 ADD START
        ZYPEZDItemValueSetter.setValue(tMsg.docMgtAttRtryRqstFlg, ZYPConstant.FLG_OFF_N);
        //QC#24244 ADD END

        EZDTBLAccessor.create(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            setErrMsg(pMsg, NWAM0546E, new String[] {"DOC_MGT_ATT_RQST" });
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        }
    }

}
