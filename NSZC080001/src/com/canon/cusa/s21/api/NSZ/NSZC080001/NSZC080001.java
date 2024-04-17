/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC080001;

import static com.canon.cusa.s21.api.NSZ.NSZC080001.constant.NSZC080001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDPItem;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_PRC_ALLOCTMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsgArray;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.parts.NFZC102001PMsg;
import business.parts.NSZC080001PMsg;
import business.parts.NSZC080001_segmentsListPMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * Contract Revenue Distribution API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/22/2015   Hitachi         T.Nishimura       Create          NA
 * 07/18/2017   CITS            K.Ogino           Update          QC#19433
 * 04/09/2018   Hitachi         U.Kim             Update          QC#23378(Sol320)
 * 05/18/2018   Hitachi         U.Kim             Update          QC#25896
 * 06/05/2018   Hitachi         U.Kim             Update          QC#25896-1
 * 11/26/2018   Hitachi         E.Kameishi        Update          QC#29400
 * </pre>
 */
public class NSZC080001 extends S21ApiCommonBase {

    /** onBatchType. */
    private ONBATCH_TYPE onBatchType = null;

    /**
     * Constructor.
     */
    public NSZC080001() {
        super();
    }

    /**
     * execute.
     * @param param NSZC080001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC080001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {
            doProcess(msgMap, param);
        }

        msgMap.flush();
    }

    /**
     * check Parameters.
     * @param msgMap S21ApiMessageMap
     * @return result
     */
    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC080001PMsg pMsg = (NSZC080001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0401E, ITEM_NAME_GLBL_CMPY_CD);
        mandatoryCheck(msgMap, pMsg.slsDt, NSZM0401E, ITEM_NAME_SLS_DT);
        mandatoryCheck(msgMap, pMsg.dsContrPk, NSZM0401E, ITEM_NAME_DS_CONTR_PK);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    /**
     * mandatoryCheck.
     * @param msgMap
     * @param targetItem
     * @param messageId
     * @param itemName
     */
    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId, String itemName) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgIdWithPrm(messageId, new String[] {itemName, NSZM0401E_02 });
        }
    }

    /**
     * main process.
     * @param msgMap S21ApiMessageMap
     * @param pMsg NSZC080001PMsg
     */
    private void doProcess(S21ApiMessageMap msgMap, NSZC080001PMsg pMsg) {

        // add start 2016/10/05 CSA Defect#13815
        if (!registerDsContrSegAlloc(msgMap, pMsg)) {
            return;
        }
        // add end 2016/10/05 CSA Defect#13815
        // START 2018/06/05 U.Kim [QC#25896-1, ADD]
        if (!deleteDsCntrPrcAlloc(msgMap, pMsg)) {
            return;
        }
        // END 2018/06/05 U.Kim [QC#25896-1, ADD]

        // START 2018/05/18 U.Kim [QC#25896, ADD]
        if (pMsg.segmentsList.getValidCount() == 0) {
            return;
        }
        // END 2018/05/18 U.Kim [QC#25896, ADD]

        List<DS_CONTR_PRC_ALLOCTMsg> dsCntrPrcAllocList = new ArrayList<DS_CONTR_PRC_ALLOCTMsg>();
        for (int i = 0; i < pMsg.segmentsList.getValidCount(); i++) {
            NSZC080001_segmentsListPMsg segment = pMsg.segmentsList.no(i);
            DS_CONTR_PRC_ALLOCTMsg msg = new DS_CONTR_PRC_ALLOCTMsg();
            setValue(msg.glblCmpyCd, pMsg.glblCmpyCd);
            // DS_CONTR_PK
            setValue(msg.dsContrPk, pMsg.dsContrPk);
            // DS_CONTR_DTL_PK
            setValue(msg.dsContrDtlPk, pMsg.dsContrDtlPk);
            // SVC_INV_CHRG_TP_CD
            setValue(msg.svcInvChrgTpCd, pMsg.svcInvChrgTpCd);
            // COA_CMPY_CD
            setValue(msg.coaCmpyCd, segment.coaCmpyCd);
            // COA_AFFL_CD
            setValue(msg.coaAfflCd, segment.coaAfflCd);
            // COA_BR_CD
            setValue(msg.coaBrCd, segment.coaBrCd);
            // COA_CH_CD
            setValue(msg.coaChCd, segment.coaChCd);
            // COA_CC_CD
            setValue(msg.coaCcCd, segment.coaCcCd);
            // COA_ACCT_CD
            setValue(msg.coaAcctCd, segment.coaAcctCd);
            // COA_PROD_CD
            setValue(msg.coaProdCd, segment.coaProdCd);
            // COA_PROJ_CD
            setValue(msg.coaProjCd, segment.coaProjCd);
            // COA_EXTN_CD
            setValue(msg.coaExtnCd, segment.coaExtnCd);
            // PRC_ALLOC_RATE
            setValue(msg.prcAllocRate, segment.prcAllocRate);
            // START 2018/04/09 U.Kim [QC#23378(Sol320), ADD]
            // PRC_ALLOC_AMT
            setValue(msg.prcAllocAmt, segment.prcAllocAmt);
            // END 2018/04/09 U.Kim [QC#23378(Sol320), ADD]
            dsCntrPrcAllocList.add(msg);
        }

        NFZC102001PMsg glCodeCombinationCheckApiMsg = callGlCodeCombinationCheckAPI(dsCntrPrcAllocList, pMsg);
        setOutputParam(msgMap, pMsg, glCodeCombinationCheckApiMsg, dsCntrPrcAllocList);

    }

    /**
     * output result.
     * @param msgMap S21ApiMessageMap
     * @param pMsg NSZC080001PMsg
     * @param apiPMsg NFZC102001PMsg
     * @param dsCntrPrcAllocList List < DS_CONTR_PRC_ALLOCTMsg>
     */
    private void setOutputParam(final S21ApiMessageMap msgMap, final NSZC080001PMsg msg, final NFZC102001PMsg apiPMsg, final List<DS_CONTR_PRC_ALLOCTMsg> dsCntrPrcAllocList) {
        if (apiPMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgIdWithPrm(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue(), new String[] {apiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue() });
            }
            return;
        }
        // START 2018/06/05 U.Kim [QC#25896-1, DEL]
        // if (msg.xxMsgIdList.getValidCount() == 0) {
        //     deleteDsCntrPrcAlloc(msgMap, msg);
        // } else {
        //     return;
        // }
        // END 2018/06/05 U.Kim [QC#25896-1, DEL]

        if (!S21ApiUtil.isXxMsgId(msg)) {
            insertDsCntrPrcAlloc(msgMap, dsCntrPrcAllocList);
        } else {
            return;
        }
    }

    /**
     * insert DS_CONTR_PRC_ALLOC records.
     * @param msgMap S21ApiMessageMap
     * @param dsCntrPrcAllocList List < DS_CONTR_PRC_ALLOCTMsg>
     */
    private void insertDsCntrPrcAlloc(S21ApiMessageMap msgMap, List<DS_CONTR_PRC_ALLOCTMsg> dsCntrPrcAllocList) {
        for (DS_CONTR_PRC_ALLOCTMsg tMsg : dsCntrPrcAllocList) {
            setValue(tMsg.dsContrPrcAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_ALLOC_SQ));
            S21FastTBLAccessor.insert(tMsg);
            String rtnCd = tMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {DS_CONTR_PRC_ALLOC });
                return;
            }
        }

    }

    /**
     * delete DS_CONTR_PRC_ALLOC records.
     * @param msgMap S21ApiMessageMap
     * @param pMsg NSZC080001PMsg
     */
    // START 2018/06/05 U.Kim [QC#25896-1, MOD]
    // private void deleteDsCntrPrcAlloc(S21ApiMessageMap msgMap, NSZC080001PMsg pMsg) {
    private boolean deleteDsCntrPrcAlloc(S21ApiMessageMap msgMap, NSZC080001PMsg pMsg) {
    // END 2018/06/05 U.Kim [QC#25896-1, MOD]
        DS_CONTR_PRC_ALLOCTMsg delTMsg = new DS_CONTR_PRC_ALLOCTMsg();
        if (hasValue(pMsg.svcInvChrgTpCd)) {
            delTMsg.setSQLID("002");
        } else if (hasValue(pMsg.dsContrDtlPk)) {
            delTMsg.setSQLID("003");
        } else {
            delTMsg.setSQLID("004");
        }
        delTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        delTMsg.setConditionValue("dsContrPk01", pMsg.dsContrPk.getValue());
        delTMsg.setConditionValue("dsContrDtlPk01", pMsg.dsContrDtlPk.getValue());
        delTMsg.setConditionValue("svcInvChrgTpCd01", pMsg.svcInvChrgTpCd.getValue());

        DS_CONTR_PRC_ALLOCTMsgArray delTMsgArray = (DS_CONTR_PRC_ALLOCTMsgArray) S21ApiTBLAccessor.findByCondition(delTMsg);
        DS_CONTR_PRC_ALLOCTMsg[] delCondArray = new DS_CONTR_PRC_ALLOCTMsg[delTMsgArray.getValidCount()];
        for (int i = 0; i < delTMsgArray.getValidCount(); i++) {
            delCondArray[i] = delTMsgArray.no(i);
        }
        if (delCondArray.length > 0) {
            if (S21FastTBLAccessor.removePhysical(delCondArray) == 0) {
                msgMap.addXxMsgIdWithPrm(NSZM0637E, new String[] {DS_CONTR_PRC_ALLOC });
                // START 2018/06/05 U.Kim [QC#25896-1, ADD]
                return false;
                // END 2018/06/05 U.Kim [QC#25896-1, ADD]
            }
        }
        // START 2018/06/05 U.Kim [QC#25896-1, ADD]
        return true;
        // END 2018/06/05 U.Kim [QC#25896-1, ADD]
    }

    /**
     * get DS_CONTR_BR_ALLOC records.
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_BR_ALLOCTMsgArray
     */
    private DS_CONTR_BR_ALLOCTMsgArray getDsCntrBrAllocXref(NSZC080001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrPk = pMsg.dsContrPk.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String svcInvChrgTpCd = pMsg.svcInvChrgTpCd.getValue(); // DS_CONTR_BR_ALLOC
        DS_CONTR_BR_ALLOCTMsg paramTMsg = new DS_CONTR_BR_ALLOCTMsg();

        if (hasValue(pMsg.svcInvChrgTpCd)) {
            paramTMsg.setSQLID("001");
        } else if (hasValue(pMsg.dsContrDtlPk)) {
            paramTMsg.setSQLID("002");
        } else {
            paramTMsg.setSQLID("003");
        }
        paramTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        paramTMsg.setConditionValue("dsContrPk01", dsContrPk);
        paramTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        paramTMsg.setConditionValue("svcInvChrgTpCd01", svcInvChrgTpCd);
        return (DS_CONTR_BR_ALLOCTMsgArray) S21ApiTBLAccessor.findByCondition(paramTMsg);
    }

    /**
     * get DS_CONTR_SEG_ALLOC records.
     * @param glblCmpyCd
     * @param dsContrPk
     * @param dsContrDtlPk
     * @return DS_CONTR_SEG_ALLOCTMsgArray
     */
    private DS_CONTR_SEG_ALLOCTMsgArray getDsCntrSegAllocXref(NSZC080001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrPk = pMsg.dsContrPk.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String svcInvChrgTpCd = pMsg.svcInvChrgTpCd.getValue();
        // DS_CONTR_SEG_ALLOC
        DS_CONTR_SEG_ALLOCTMsg paramTMsg = new DS_CONTR_SEG_ALLOCTMsg();
        if (hasValue(pMsg.svcInvChrgTpCd)) {
            paramTMsg.setSQLID("001");
        } else if (hasValue(pMsg.dsContrDtlPk)) {
            paramTMsg.setSQLID("002");
        } else {
            paramTMsg.setSQLID("003");
        }
        paramTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        paramTMsg.setConditionValue("dsContrPk01", dsContrPk);
        paramTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        paramTMsg.setConditionValue("svcInvChrgTpCd01", svcInvChrgTpCd);

        return (DS_CONTR_SEG_ALLOCTMsgArray) S21ApiTBLAccessor.findByCondition(paramTMsg);
    }

    /**
     * call API (NFZC1020).
     * @param dsCntrPrcAllocList List < DS_CONTR_PRC_ALLOCTMsg>
     * @param pMsg NSZC080001PMsg
     * @return NFZC102001PMsg
     */
    private NFZC102001PMsg callGlCodeCombinationCheckAPI(List<DS_CONTR_PRC_ALLOCTMsg> dsCntrPrcAllocList, NSZC080001PMsg pMsg) {
        NFZC102001PMsg apiPMsg = null;
        for (DS_CONTR_PRC_ALLOCTMsg dsCntrPrcAllocTMsg : dsCntrPrcAllocList) {
            apiPMsg = new NFZC102001PMsg();
            setValue(apiPMsg.glblCmpyCd, dsCntrPrcAllocTMsg.glblCmpyCd);
            setValue(apiPMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
            setValue(apiPMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);
            // START 2018/11/26 E.Kameishi [QC#29400,MOD]
            //setValue(apiPMsg.xxArcsApiChkFlg, ZYPConstant.FLG_OFF_N);
            setValue(apiPMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
            // END 2018/11/26 E.Kameishi [QC#29400,MOD]
            setValue(apiPMsg.coaCmpyCd, dsCntrPrcAllocTMsg.coaCmpyCd);
            setValue(apiPMsg.coaBrCd, dsCntrPrcAllocTMsg.coaBrCd);
            setValue(apiPMsg.coaCcCd, dsCntrPrcAllocTMsg.coaCcCd);
            setValue(apiPMsg.coaAcctCd, dsCntrPrcAllocTMsg.coaAcctCd);
            setValue(apiPMsg.coaProdCd, dsCntrPrcAllocTMsg.coaProdCd);
            setValue(apiPMsg.coaChCd, dsCntrPrcAllocTMsg.coaChCd);
            setValue(apiPMsg.coaAfflCd, dsCntrPrcAllocTMsg.coaAfflCd);
            setValue(apiPMsg.coaProjCd, dsCntrPrcAllocTMsg.coaProjCd);
            setValue(apiPMsg.coaExtnCd, dsCntrPrcAllocTMsg.coaExtnCd);
            // QC#19433 Start
            setValue(apiPMsg.resrcObjNm, pMsg.resrcObjNm);
            // QC#19433 End
            NFZC102001 api = new NFZC102001();
            api.execute(apiPMsg, this.onBatchType);

            if (apiPMsg.xxMsgIdList.getValidCount() != 0) {
                // return the NFZC102001PMsg with error.
                return apiPMsg;
            }
        }
        // return the last NFZC102001PMsg.
        return apiPMsg;
    }

    // add start 2016/10/05 CSA Defect#13815
    private boolean registerDsContrSegAlloc(S21ApiMessageMap msgMap, NSZC080001PMsg pMsg) {

        DS_CONTR_SEG_ALLOCTMsg findKeyTMsg = new DS_CONTR_SEG_ALLOCTMsg();

        if (ZYPCommonFunc.hasValue(pMsg.dsContrDtlPk) && ZYPCommonFunc.hasValue(pMsg.svcInvChrgTpCd)) {
            findKeyTMsg.setSQLID("001");
            findKeyTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            findKeyTMsg.setConditionValue("dsContrPk01", pMsg.dsContrPk.getValue());
            findKeyTMsg.setConditionValue("dsContrDtlPk01", pMsg.dsContrDtlPk.getValue());
            findKeyTMsg.setConditionValue("svcInvChrgTpCd01", pMsg.svcInvChrgTpCd.getValue());

        } else if (ZYPCommonFunc.hasValue(pMsg.dsContrDtlPk)) {
            findKeyTMsg.setSQLID("002");
            findKeyTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            findKeyTMsg.setConditionValue("dsContrPk01", pMsg.dsContrPk.getValue());
            findKeyTMsg.setConditionValue("dsContrDtlPk01", pMsg.dsContrDtlPk.getValue());

        } else {
            findKeyTMsg.setSQLID("003");
            findKeyTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            findKeyTMsg.setConditionValue("dsContrPk01", pMsg.dsContrPk.getValue());
        }

        DS_CONTR_SEG_ALLOCTMsgArray tMsgArray = (DS_CONTR_SEG_ALLOCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(findKeyTMsg);
        if (tMsgArray != null && tMsgArray.length() > 0) {
            for (int index = 0; index < tMsgArray.length(); index++) {
                S21ApiTBLAccessor.remove(tMsgArray.no(index));
            }
        }

        for (int index = 0; index < pMsg.segmentsList.getValidCount(); index++) {
            DS_CONTR_SEG_ALLOCTMsg inMsg = new DS_CONTR_SEG_ALLOCTMsg();
            NSZC080001_segmentsListPMsg segment = pMsg.segmentsList.no(index);

            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.dsContrSegAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SEG_ALLOC_SQ));
            setValue(inMsg.dsContrPk, pMsg.dsContrPk);
            setValue(inMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
            setValue(inMsg.svcInvChrgTpCd, pMsg.svcInvChrgTpCd);
            setValue(inMsg.coaCmpyCd, segment.coaCmpyCd);
            setValue(inMsg.coaAfflCd, segment.coaAfflCd);
            setValue(inMsg.coaBrCd, segment.coaBrCd);
            setValue(inMsg.coaChCd, segment.coaChCd);
            setValue(inMsg.coaCcCd, segment.coaCcCd);
            setValue(inMsg.coaAcctCd, segment.coaAcctCd);
            setValue(inMsg.coaProdCd, segment.coaProdCd);
            setValue(inMsg.coaProjCd, segment.coaProjCd);
            setValue(inMsg.coaExtnCd, segment.coaExtnCd);
            setValue(inMsg.prcAllocRate, segment.prcAllocRate);
            // START 2018/04/09 U.Kim [QC#23378(Sol320), ADD]
            setValue(inMsg.prcAllocAmt, segment.prcAllocAmt);
            // END 2018/04/09 U.Kim [QC#23378(Sol320), ADD]
            S21ApiTBLAccessor.insert(inMsg);
            String rtnCd = inMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {DS_CONTR_SEG_ALLOC });
                return false;
            }
        }

        return true;
    }
    // add end 2016/10/05 CSA Defect#13815
}