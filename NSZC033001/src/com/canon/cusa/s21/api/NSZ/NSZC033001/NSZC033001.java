/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC033001;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SAVE_SRCH_OPTTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant.MESSAGE_ID;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 *<pre>
 * Search Option API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/11   Fujitsu         H.Nakajima      Create          
 *</pre>
 */
public class NSZC033001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC033001() {
        super();
    }

    /**
     * execute Search Option API(List).
     * @param pMsgList List<NSZC033001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC033001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC033001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute Search Option API.
     * @param param NSZC033001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC033001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        ZYPTableUtil.clear(param.xxMsgIdList);

        if (!checkInputParam(msgMap)) {
            return;
        }

        String procMd = param.xxProcMd.getValue();

        if (NSZC033001Constant.PROCESS_MODE_SEARCH.equals(procMd)) {
            executeForSearch(msgMap);

        } else if (NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE.equals(procMd)) {
            executeForInsertUpdate(msgMap);

        } else if (NSZC033001Constant.PROCESS_MODE_DELETE.equals(procMd)) {
            executeForDelete(msgMap);

        }

        msgMap.flush();
    }

    /**
     * Check Input Parameter.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkInputParam(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0001E.toString());
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxProcMd)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0003E.toString());
        }
        if (!ZYPCommonFunc.hasValue(pMsg.srchOptAplId)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0533E.toString());
        }

        msgMap.flush();

        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }

        return false;
    }

    /**
     * execute Search Mode.
     * @param msgMap S21ApiMessageMap
     */
    private void executeForSearch(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        if (!checkParamForSearch(msgMap)) {
            return;
        }

        SAVE_SRCH_OPTTMsg tMsg = findSaveSrchOptByKey(pMsg.glblCmpyCd.getValue(), pMsg.srchOptPk.getValue());

        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, pMsg.xxProcMd);

            EZDMsg.copy(tMsg, null, pMsg, null);
        }
    }

    /**
     * Check Input Parameter for Search Mode.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkParamForSearch(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.srchOptPk)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0534E.toString());

        } else {

            SAVE_SRCH_OPTTMsg tMsg = findSaveSrchOptByKey(pMsg.glblCmpyCd.getValue(), pMsg.srchOptPk.getValue());

            if (tMsg == null) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0529E.toString());
            }
        }

        msgMap.flush();

        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }

        return false;
    }

    /**
     * execute Insert/Update Mode.
     * @param msgMap S21ApiMessageMap
     */
    private void executeForInsertUpdate(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        if (!checkParamForInsertUpdate(msgMap)) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.srchOptPk)) {
            insert(msgMap);

        } else {
            update(msgMap);
        }
    }

    /**
     * Check Input Parameter for Insert/Update Mode.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkParamForInsertUpdate(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.srchOptNm)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0530E.toString());
        }

        if (ZYPCommonFunc.hasValue(pMsg.srchOptPk)) {
            SAVE_SRCH_OPTTMsg tMsg = findSaveSrchOptByKey(pMsg.glblCmpyCd.getValue(), pMsg.srchOptPk.getValue());

            if (tMsg == null) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0529E.toString());
            }

        } else {
            BigDecimal maxCount = ZYPCodeDataUtil.getNumConstValue(NSZC033001Constant.NUM_CONST_KEY_SRCH_OPT_MAX_NUM, pMsg.glblCmpyCd.getValue());

            if (maxCount != null) {
                int count = countSaveSrchOpt(pMsg.glblCmpyCd.getValue(), pMsg.srchOptAplId.getValue(), pMsg.srchOptUsrId.getValue());

                if (count >= maxCount.intValue()) {
                    msgMap.addXxMsgId(MESSAGE_ID.NSZM0535E.toString());
                }
            }
        }

        msgMap.flush();

        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }

        return false;
    }

    /**
     * Update SAVE_SRCH_OPT.
     * @param msgMap S21ApiMessageMap
     */
    private void update(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        SAVE_SRCH_OPTTMsg tMsg = findSaveSrchOptByKeyForupdate(pMsg.glblCmpyCd.getValue(), pMsg.srchOptPk.getValue());

        EZDMsg.copy(pMsg, null, tMsg, null);

        S21ApiTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0537E.toString());
        }
    }

    /**
     * Insert SAVE_SRCH_OPT.
     * @param msgMap S21ApiMessageMap
     * @param tMsg SAVE_SRCH_OPTTMsg
     */
    private void insert(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        SAVE_SRCH_OPTTMsg tMsg = new SAVE_SRCH_OPTTMsg();

        pMsg.srchOptPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SAVE_SRCH_OPT_SQ));

        EZDMsg.copy(pMsg, null, tMsg, null);

        S21ApiTBLAccessor.create(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0536E.toString());
        }
    }

    /**
     * execute Delete Mode.
     * @param msgMap S21ApiMessageMap
     */
    private void executeForDelete(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        if (!checkParamForDelete(msgMap)) {
            return;
        }

        SAVE_SRCH_OPTTMsg tMsg = findSaveSrchOptByKeyForupdate(pMsg.glblCmpyCd.getValue(), pMsg.srchOptPk.getValue());

        if (tMsg != null) {
            S21ApiTBLAccessor.remove(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0538E.toString());
            }
        }
    }

    /**
     * Check Input Parameter for Delete Mode.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkParamForDelete(S21ApiMessageMap msgMap) {
        NSZC033001PMsg pMsg = (NSZC033001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.srchOptPk)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0534E.toString());

        } else {

            SAVE_SRCH_OPTTMsg tMsg = findSaveSrchOptByKey(pMsg.glblCmpyCd.getValue(), pMsg.srchOptPk.getValue());

            if (tMsg == null) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0529E.toString());
            }
        }

        msgMap.flush();

        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }

        return false;
    }

    /**
     * Find SAVE_SRCH_OPT.
     * @param glblCmpyCd Global Company Code
     * @param srchOptPk Search Option Key
     * @return SAVE_SRCH_OPT
     */
    private static SAVE_SRCH_OPTTMsg findSaveSrchOptByKey(String glblCmpyCd, BigDecimal srchOptPk) {
        SAVE_SRCH_OPTTMsg inMsg = new SAVE_SRCH_OPTTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.srchOptPk.setValue(srchOptPk);

        return (SAVE_SRCH_OPTTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * Find SAVE_SRCH_OPT for Update.
     * @param glblCmpyCd Global Company Code
     * @param srchOptPk Search Option Key
     * @return SAVE_SRCH_OPT
     */
    private static SAVE_SRCH_OPTTMsg findSaveSrchOptByKeyForupdate(String glblCmpyCd, BigDecimal srchOptPk) {
        SAVE_SRCH_OPTTMsg inMsg = new SAVE_SRCH_OPTTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.srchOptPk.setValue(srchOptPk);

        return (SAVE_SRCH_OPTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * Count SAVE_SRCH_OPT by Application ID and User ID.
     * @param glblCmpyCd Global Company Code
     * @param srchOptAplId Search Option Application ID
     * @param srchOptUsrId Search Option USER ID
     * @return count
     */
    private static int countSaveSrchOpt(String glblCmpyCd, String srchOptAplId, String srchOptUsrId) {
        SAVE_SRCH_OPTTMsg inMsg = new SAVE_SRCH_OPTTMsg();

        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("srchOptAplId01", srchOptAplId);
        inMsg.setConditionValue("srchOptUsrId01", srchOptUsrId);

        inMsg.setSQLID("001");

        return S21ApiTBLAccessor.count(inMsg);
    }
}
