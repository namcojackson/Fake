/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC131001;

import static com.canon.cusa.s21.api.NSZ.NSZC131001.constant.NSZC131001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC131001.constant.NSZC131001Constant.NSZM1391E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_TASK_RQST_ORIGTMsg;
import business.parts.NSZC131001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * CSA Walmart Service Call Request API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/20   Hitachi         H.Watanabe      Create          QC#60012
 *</pre>
 */
public class NSZC131001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC131001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC131001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(List<NSZC131001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC131001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC131001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(NSZC131001PMsg pMsg, final ONBATCH_TYPE onBatchTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        registrationCallRequest(msgMap);
        msgMap.flush();
    }

    private void registrationCallRequest(S21ApiMessageMap msgMap) {
        NSZC131001PMsg pMsg = (NSZC131001PMsg) msgMap.getPmsg();
        SVC_TASK_RQST_ORIGTMsg svcTaskRqstOrigTMsg = new SVC_TASK_RQST_ORIGTMsg();
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.glblCmpyCd, GLBL_CMPY_CD);
        }

        setValue(svcTaskRqstOrigTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcTaskRqstOrigTMsg.svcTaskRqstOrigPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_RQST_ORIG_SQ));
        setValue(svcTaskRqstOrigTMsg.rqstModeTxt, pMsg.rqstModeTxt);
        setValue(svcTaskRqstOrigTMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.WALMART);
        setValue(svcTaskRqstOrigTMsg.cratTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
        setValue(svcTaskRqstOrigTMsg.serNumLongTxt, pMsg.serNumLongTxt);
        setValue(svcTaskRqstOrigTMsg.rqstModeDescTxt, pMsg.rqstModeDescTxt);
        setValue(svcTaskRqstOrigTMsg.fsrLongTxt, pMsg.fsrLongTxt);
        setValue(svcTaskRqstOrigTMsg.ctacPsnEmlAddr, pMsg.ctacPsnEmlAddr);
        setValue(svcTaskRqstOrigTMsg.ctacPsnCellPhoTxt, pMsg.ctacPsnCellPhoTxt);
        setValue(svcTaskRqstOrigTMsg.svcCustAttnTxt, pMsg.svcCustAttnTxt);
        setValue(svcTaskRqstOrigTMsg.svcCmntTxt, pMsg.svcCmntTxt);
        setValue(svcTaskRqstOrigTMsg.custCloseCatgTxt, pMsg.custCloseCatgTxt);
        setValue(svcTaskRqstOrigTMsg.custCloseDescTxt, pMsg.custCloseDescTxt);
        setValue(svcTaskRqstOrigTMsg.custMemoTxt, pMsg.custMemoTxt);
        setValue(svcTaskRqstOrigTMsg.rcvTmDescTxt, pMsg.rcvTmDescTxt);
        setValue(svcTaskRqstOrigTMsg.custSysId, pMsg.custSysId);
        setValue(svcTaskRqstOrigTMsg.custCallId, pMsg.custCallId);

        S21ApiTBLAccessor.insert(svcTaskRqstOrigTMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskRqstOrigTMsg.getReturnCode()) == false) {
            msgMap.addXxMsgId(NSZM1391E);
        }
    }
}
