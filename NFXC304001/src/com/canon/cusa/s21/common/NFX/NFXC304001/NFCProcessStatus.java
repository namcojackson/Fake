/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC304001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_RCPT_RCV_HISTTMsg;
import business.db.AR_RCPT_RCV_WRKTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * ProcessStatus Update Component
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/18/2009   Canon           Y.Kondo         Create          N/A
 * 12/18/2015   CSAI            K.Uramori       Update          remove AR_RCPT_RCV_HIST update code. CSA version won't create AR_RCPT_RCV_HIST.
 * 2018/11/13   Fujitsu         H.Ikeda         Update          QC#28062
 *</pre>
 */
public class NFCProcessStatus {

    /** Table Column Name : AR_RCPT_RCV_WRK.GLBL_CMPY_CD */
    private static final String ARRW_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_RCPT_RCV_WRK.RCV_SQ_PK */
    private static final String ARRW_RCV_SQ_PK = "rcvSqPk";

    /** Table Column Name : AR_RCPT_RCV_WRK.RCV_HDR_NUM */
    private static final String ARRW_RCV_HDR_NUM = "rcvHdrNum";

    /** Table Column Name : AR_RCPT_IN_PROC_WRK.GLBL_CMPY_CD */
    private static final String ARIPW_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_RCPT_IN_PROC_WRK.RCPT_IN_PROC_SQ_PK */
    private static final String ARIPW_RCPT_IN_PROC_SQ_PK = "rcptInProcSqPk";

    /** Table Column Name : AR_RCPT_IN_PROC_WRK.RCV_HDR_NUM */
    private static final String ARIPW_RCV_HDR_NUM = "rcvHdrNum";

    /** DateFormat : hour */
    private static final String HHMMSS = "HHmmss";

    /** DateIndex : date start */
    private static final int DATA_START = 0;

    /** DateIndex : date end */
    private static final int DATA_END = 8;

    /** DateIndex : hour start */
    private static final int HOUR_START = 8;

    /** DateIndex : hour end */
    private static final int HOUR_END = 14;

    /**
     * <pre>
     * ProcessStatus Update Component Wrapper
     * </pre>
     * 
     * @param glblCmpyCd GlobalCompanyCode
     * @param rcvSqPk ReceivedSequencePrimaryKey
     * @param rcvHdrNum ReceiptHeaderNumber
     * @param procSts ProcessStatus
     * @param cratMethTp CreateMethodTypeCode
     * @param userId UserID
     * @return returnCd
     */
    public String setProcessStatus(String glblCmpyCd, BigDecimal rcvSqPk, String rcvHdrNum, String procSts, String cratMethTp, String userId) {

        return setProcessStatus(glblCmpyCd, rcvSqPk, rcvHdrNum, procSts, cratMethTp, userId, NFCConst.CST_DB_INIT_VAL_STR);
    }

    /**
     * <pre>
     * ProcessStatus Update Component
     * </pre>
     * 
     * @param glblCmpyCd GlobalCompanyCode
     * @param rcvSqPk ReceivedSequencePrimaryKey
     * @param rcvHdrNum ReceiptHeaderNumber
     * @param procSts ProcessStatus
     * @param cratMethTp CreateMethodTypeCode
     * @param userId UserID
     * @param rcptNum rcptNum
     * @return returnCd
     */
    public String setProcessStatus(String glblCmpyCd, BigDecimal rcvSqPk, String rcvHdrNum, String procSts, String cratMethTp, String userId, String rcptNum) {

        debugLog("updateProcessStatus start");

        if (checkParam(glblCmpyCd, rcvSqPk, rcvHdrNum, procSts, cratMethTp, userId)) {

            debugLog("checkParam : ParameterNG");
            return NFCConst.CST_RTN_CD_ERR;
        }

        if (NFCConst.CST_PROC_STS_RCV.equals(procSts)) {

            AR_RCPT_RCV_WRKTMsg rcptRcvWrkT = new AR_RCPT_RCV_WRKTMsg();
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(ARRW_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(ARRW_RCV_SQ_PK, rcvSqPk);
            queryParam.put(ARRW_RCV_HDR_NUM, rcvHdrNum);
            S21SsmEZDResult result = NFXC304001Query.getInstance().getRcvFuncId(queryParam, rcptRcvWrkT);

            if (result.getQueryResultCount() <= 0) {

                debugLog("AR_RCPT_RCV_WRK : NO DATA");
                debugLog("GLBL_CMPY_CD : " + glblCmpyCd);
                debugLog("RCV_SQ_PK : " + rcvSqPk);
                debugLog("RCV_HDR_NUM : " + rcvHdrNum);
                return NFCConst.CST_RTN_CD_ERR;
            }
            AR_RCPT_RCV_HISTTMsg rcptRcvHistT = new AR_RCPT_RCV_HISTTMsg();
            rcptRcvHistT.glblCmpyCd.setValue(glblCmpyCd);
            rcptRcvHistT.rcvHistSqPk.setValue(rcvSqPk);
            rcptRcvHistT.rcvHdrNum.setValue(rcvHdrNum);
            rcptRcvHistT.rcvDt.setValue(rcptRcvWrkT.ezInTime.getValue().substring(DATA_START, DATA_END));
            rcptRcvHistT.rcvFuncId.setValue(rcptRcvWrkT.rcvFuncId.getValue());
            rcptRcvHistT.startDt.setValue(rcptRcvWrkT.ezInTime.getValue().substring(DATA_START, DATA_END));
            rcptRcvHistT.startTm.setValue(rcptRcvWrkT.ezInTime.getValue().substring(HOUR_START, HOUR_END));
            rcptRcvHistT.rcptDepChkFlg.setValue(NFCConst.CST_FLAG_ON);
            rcptRcvHistT.cashAppChkFlg.setValue(NFCConst.CST_FLAG_OFF);
            rcptRcvHistT.cashAppHdlgFlg.setValue(NFCConst.CST_FLAG_OFF);
            rcptRcvHistT.cratMethTpCd.setValue(cratMethTp);
            rcptRcvHistT.upldUserId.setValue(userId);
            rcptRcvHistT.arEdiSendBankCd.setValue(NFCCmnMethod.convertDbString(rcptRcvWrkT.acctMaCd.getValue()));

            S21ApiTBLAccessor.insert(rcptRcvHistT);
            debugLog("AR_RCPT_RCV_HIST : INSERT RETURN CODE : " + rcptRcvHistT.getReturnCode());
        } else if (NFCConst.CST_PROC_STS_CHK.equals(procSts)) {
            // START 2018/11/13 H.Ikeda [QC#28062, DEL]
//            AR_RCPT_IN_PROC_WRKTMsg rcptInProcWrkT = new AR_RCPT_IN_PROC_WRKTMsg();
//            Map<String, Object> queryParam = new HashMap<String, Object>();
//            queryParam.put(ARIPW_GLBL_CMPY_CD, glblCmpyCd);
//            queryParam.put(ARIPW_RCPT_IN_PROC_SQ_PK, rcvSqPk);
//            queryParam.put(ARIPW_RCV_HDR_NUM, rcvHdrNum);
//            S21SsmEZDResult result = NFXC304001Query.getInstance().getEdiSendBankCd(queryParam, rcptInProcWrkT);
//
//            if (result.getQueryResultCount() <= 0) {
//
//                debugLog("AR_RCPT_IN_PROC_WRK : NO DATA");
//                debugLog("GLBL_CMPY_CD : " + glblCmpyCd);
//                debugLog("RCPT_IN_PROC_SQ_PK : " + rcvSqPk);
//                debugLog("RCV_HDR_NUM : " + rcvHdrNum);
//                return NFCConst.CST_RTN_CD_ERR;
//            }
            // END   2018/11/13 H.Ikeda [QC#28062, DEL]
            // 2015/12/18 remove
            /*AR_RCPT_RCV_HISTTMsg rcptRcvHistT = rcptRcvHistForUpdate(glblCmpyCd, rcvSqPk, rcvHdrNum);
            rcptRcvHistT.rcptNum.setValue(rcptNum);
            rcptRcvHistT.cashAppChkFlg.setValue(NFCConst.CST_FLAG_ON);
            
            S21ApiTBLAccessor.update(rcptRcvHistT);
            debugLog("AR_RCPT_RCV_HIST : UPDATE RETURN CODE : " + rcptRcvHistT.getReturnCode());
            */
            
         // end 2015/12/18

        } else if (NFCConst.CST_PROC_STS_APPLY.equals(procSts)) {

            // 2015/12/18 remove
            /*
            AR_RCPT_RCV_HISTTMsg rcptRcvHistT = rcptRcvHistForUpdate(glblCmpyCd, rcvSqPk, rcvHdrNum);
            rcptRcvHistT.endDt.setValue(ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
            rcptRcvHistT.endTm.setValue(ZYPDateUtil.getCurrentSystemTime(HHMMSS));
            rcptRcvHistT.cashAppHdlgFlg.setValue(NFCConst.CST_FLAG_ON);

            S21ApiTBLAccessor.update(rcptRcvHistT);
            debugLog("AR_RCPT_RCV_HIST : UPDATE RETURN CODE : " + rcptRcvHistT.getReturnCode());
            */
            // end 2015/12/18
        }
        debugLog("updateProcessStatus end");

        return NFCConst.CST_RTN_CD_NORM;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param glblCmpyCd GlobalCompanyCode
     * @param rcvSqPk ReceivedSequencePrimaryKey
     * @param rcvHdrNum ReceiptHeaderNumber
     * @param procSts ProcessStatus
     * @param cratMethTp CreateMethodTypeCode
     * @param userId UserID
     * @return false : ParameterOK, true : ParameterNG
     */
    private boolean checkParam(String glblCmpyCd, BigDecimal rcvSqPk, String rcvHdrNum, String procSts, String cratMethTp, String userId) {

        debugLog("checkParam start");

        if (S21StringUtil.isEmpty(glblCmpyCd)) {
            debugLog("GlobalCompanyCode : " + glblCmpyCd);
            return true;
        }
        if (rcvSqPk == null) {
            debugLog("ReceivedSequencePrimaryKey : " + rcvSqPk);
            return true;
        }
        if (S21StringUtil.isEmpty(rcvHdrNum)) {
            debugLog("ReceiptHeaderNumber : " + rcvHdrNum);
            return true;
        }
        if (S21StringUtil.isEmpty(procSts) || !(NFCConst.CST_PROC_STS_RCV.equals(procSts) || NFCConst.CST_PROC_STS_CHK.equals(procSts) || NFCConst.CST_PROC_STS_APPLY.equals(procSts))) {

            debugLog("ProcessStatus : " + procSts);
            return true;
        }
        if (NFCConst.CST_PROC_STS_RCV.equals(procSts)) {

            if (S21StringUtil.isEmpty(cratMethTp) || !(NFCConst.CST_CREATE_METH_TP_CD_AUTO.equals(cratMethTp) || NFCConst.CST_CREATE_METH_TP_CD_CSV.equals(cratMethTp))) {

                debugLog("CreateMethodTypeCode : " + cratMethTp);
                return true;
            }
        }
        if (NFCConst.CST_CREATE_METH_TP_CD_CSV.equals(cratMethTp)) {

            if (S21StringUtil.isEmpty(userId)) {
                debugLog("UserID : " + userId);
                return true;
            }
        }
        debugLog("checkParam end");
        return false;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param glblCmpyCd GlobalCompanyCode
     * @param rcvSqPk ReceivedSequencePrimaryKey
     * @param rcvHdrNum ReceiptHeaderNumber
     * @return AR_RCPT_RCV_HISTTMsg
     */
    private AR_RCPT_RCV_HISTTMsg rcptRcvHistForUpdate(String glblCmpyCd, BigDecimal rcvSqPk, String rcvHdrNum) {

        debugLog("rcptRcvHistForUpdate start");

        AR_RCPT_RCV_HISTTMsg rcptRcvHistT = new AR_RCPT_RCV_HISTTMsg();
        rcptRcvHistT.glblCmpyCd.setValue(glblCmpyCd);
        rcptRcvHistT.rcvHistSqPk.setValue(rcvSqPk);
        rcptRcvHistT.rcvHdrNum.setValue(rcvHdrNum);
        rcptRcvHistT = (AR_RCPT_RCV_HISTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rcptRcvHistT);

        debugLog("rcptRcvHistForUpdate end");
        return rcptRcvHistT;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
