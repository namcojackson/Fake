/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0160;

import static business.blap.NSBL0160.constant.NSBL0160Constant.NZZM0003E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0160.common.NSBL0160CommonLogic;
import business.db.FSRTMsg;
import business.db.FSR_EVENTTMsg;
import business.db.SVC_DISPT_EVENTTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi      Create          N/A
 * 2013/08/09   SRAA            H.Yanada        Update          QC1644
 * 2014/01/07   Fujitsu         T.Yoshida       Update          Mobile IF
 * 2015/10/29   Hitach          T.Tomita        Update          N/A
 * 2015/11/20   Hitach          T.Tomita        Update          QC#949
 * 2016/02/26   Hitach          T.Tomita        Update          QC#4645
 * 2016/06/16   Hitach          A.Kohinata      Update          QC#9677
 * 2017/08/09   Hitach          U.Kim           Update          QC#18151
 *</pre>
 */
public class NSBL0160BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NSBL0160Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_CMN_Save((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START 2015/10/29 T.Tomita [N/A, MOD]
    private void doProcess_NSBL0160Scrn00_CMN_Save(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {

        NSBL0160CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (hasValue(sMsg.B.no(i).svcMemoPk_B)) {
                // Update
// START 2017/08/09 U.Kim [QC#18151, DEL]               
//                if (sMsg.B.no(i).svcCmntTxt_B.getValue().equals(sMsg.B.no(i).svcCmntTxt_BK.getValue())) {
//                    // same data
//                    continue;
//                }
//                SVC_MEMOTMsg updMsg = lockSvcMemo(getGlobalCompanyCode(), sMsg.B.no(i));
//                if (updMsg == null) {
//                    cMsg.setMessageInfo(NZZM0003E);
//                    return;
//                }
//                setValue(updMsg.svcCmntTxt, sMsg.B.no(i).svcCmntTxt_B);
//                setValue(updMsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
//                setValue(updMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
//                EZDTBLAccessor.update(updMsg);
// END 2017/08/09 U.Kim [QC#18151, DEL] 
            } else {
                // Insert
                if (!hasValue(sMsg.B.no(i).svcCmntTxt_B)) {
                    continue;
                }
                insertSvcMemo(sMsg.B.no(i), cMsg);
            }
        }

        if (SVC_MEMO_CATG.DISPATCH_MEMO.equals(cMsg.svcMemoCatgCd_HD.getValue())) {
            insertFsrEvent(cMsg);
            // add start 2016/06/16 QC#9677
            callSendTaskInfoAPI(cMsg);
            // add end 2016/06/16 QC#9677
        }
    }

// START 2017/08/09 U.Kim [QC#18151, DEL] 
//    private SVC_MEMOTMsg lockSvcMemo(String glblCmpyCd, NSBL0160_BSMsg bsMsg) {
//        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
//        setValue(inMsg.glblCmpyCd, glblCmpyCd);
//        setValue(inMsg.svcMemoPk, bsMsg.svcMemoPk_B);
//        SVC_MEMOTMsg outMsg = (SVC_MEMOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
//        if (outMsg == null) {
//            return null;
//        }
//        String preUpTime = bsMsg.ezUpTime_B.getValue();
//        String preTimeZone = bsMsg.ezUpTimeZone_B.getValue();
//        String currUpTime = outMsg.ezUpTime.getValue();
//        String currTimeZone = outMsg.ezUpTimeZone.getValue();
//        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
//            return null;
//        }
//        return outMsg;
//    }
// END 2017/08/09 U.Kim [QC#18151, DEL] 

    private void insertSvcMemo(NSBL0160_BSMsg bcMsg, NSBL0160CMsg cMsg) {
        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(inMsg.svcCmntTxt, bcMsg.svcCmntTxt_B);
        setValue(inMsg.svcMemoCatgCd, bcMsg.svcMemoCatgCd_B);
        setValue(inMsg.svcMemoTpCd, bcMsg.svcMemoTpCd_B);
        setValue(inMsg.svcMemoRsnCd, bcMsg.svcMemoRsnCd_B);
        setValue(inMsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
        setValue(inMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));

        for (int j = 0; j < cMsg.C.getValidCount(); j++) {
            String columnName = cMsg.C.no(j).xxComnScrColLbTxt_CL.getValue();
            String value = cMsg.C.no(j).xxComnScrColLbTxt_SC.getValue();
            if (inMsg.getDBColumnName("svcTaskNum").equals(columnName)) {
                setValue(inMsg.svcTaskNum, value);
                // for sort
                setValue(bcMsg.svcTaskNum_B, value);
                continue;
            }
            if (inMsg.getDBColumnName("fsrNum").equals(columnName)) {
                setValue(inMsg.fsrNum, value);
                continue;
            }
            if (inMsg.getDBColumnName("svcMachMstrPk").equals(columnName)) {
                setValue(inMsg.svcMachMstrPk, new BigDecimal(value));
                continue;
            }
            if (inMsg.getDBColumnName("dsContrPk").equals(columnName)) {
                setValue(inMsg.dsContrPk, new BigDecimal(value));
                continue;
            }
            if (inMsg.getDBColumnName("dsContrDtlPk").equals(columnName)) {
                setValue(inMsg.dsContrDtlPk, new BigDecimal(value));
                continue;
            }
            if (inMsg.getDBColumnName("svcInvPk").equals(columnName)) {
                setValue(inMsg.svcInvPk, new BigDecimal(value));
                continue;
            }
            if (inMsg.getDBColumnName("svcContrBllgPk").equals(columnName)) {
                setValue(inMsg.svcContrBllgPk, new BigDecimal(value));
                continue;
            }
            // START 2015/11/20 T.Tomita [QC#949, ADD]
            if (inMsg.getDBColumnName("svcMemoTrxNum").equals(columnName)) {
                setValue(inMsg.svcMemoTrxNum, value);
                continue;
            }
            if (inMsg.getDBColumnName("svcMemoTrxNm").equals(columnName)) {
                setValue(inMsg.svcMemoTrxNm, value);
                continue;
            }
            // END 2015/11/20 T.Tomita [QC#949, ADD]
        }
        EZDTBLAccessor.insert(inMsg);
    }
    // END 2015/10/29 T.Tomita [N/A, MOD]

    // add start 2016/02/26 CSA Defect#4645
    private void insertFsrEvent(NSBL0160CMsg cMsg) {

        String fsrNum = getCondition(cMsg, "fsrNum");
        String svcTaskNum = getCondition(cMsg, "svcTaskNum");

        SVC_DISPT_EVENTTMsg svcDisptEventTMsg = new SVC_DISPT_EVENTTMsg();
        setValue(svcDisptEventTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(svcDisptEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UPDATE_NOTES);
        SVC_DISPT_EVENTTMsg resultTMsg = (SVC_DISPT_EVENTTMsg) ZYPCodeDataUtil.findByKey(svcDisptEventTMsg);

        FSR_EVENTTMsg inMsg = new FSR_EVENTTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_EVENT_SQ));
        setValue(inMsg.svcDisptEventCd, resultTMsg.svcDisptEventCd);
        setValue(inMsg.techCd, getTechCd(fsrNum, svcTaskNum));
        setValue(inMsg.svcTaskNum, svcTaskNum);
        setValue(inMsg.fsrNum, fsrNum);
        setValue(inMsg.fsrEventExeUsrId, getUserProfileService().getContextUserInfo().getUserId());
        setValue(inMsg.fsrEventExeTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
        setValue(inMsg.mblIntfcProcCd, MBL_INTFC_PROC.NOT_PROCESSED);
        setValue(inMsg.mblIntfcFlg, resultTMsg.mblIntfcFlg);

        EZDTBLAccessor.insert(inMsg);
    }
    // add end 2016/02/26 CSA Defect#4645

    private String getCondition(NSBL0160CMsg cMsg, String targetName) {

        FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();

        for (int j = 0; j < cMsg.C.getValidCount(); j++) {
            String columnName = cMsg.C.no(j).xxComnScrColLbTxt_CL.getValue();
            if (fsrEventTMsg.getDBColumnName(targetName).equals(columnName)) {
                return cMsg.C.no(j).xxComnScrColLbTxt_SC.getValue();
            }
        }

        return null;
    }

    private String getTechCd(String fsrNum, String svcTaskNum) {

        if (ZYPCommonFunc.hasValue(fsrNum)) {
            FSRTMsg fsrTMsg = new FSRTMsg();
            ZYPEZDItemValueSetter.setValue(fsrTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrNum, fsrNum);
            fsrTMsg = (FSRTMsg) EZDTBLAccessor.findByKey(fsrTMsg);

            if (fsrTMsg != null) {
                return fsrTMsg.techCd.getValue();
            }

        } else {
            SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
            svcTaskTMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKey(svcTaskTMsg);

            if (svcTaskTMsg != null) {
                return svcTaskTMsg.techCd.getValue();
            }
        }

        return null;
    }

    // add start 2016/06/16 QC#9677
    private void callSendTaskInfoAPI(NSBL0160CMsg cMsg) {

        String svcTaskNum = getCondition(cMsg, "svcTaskNum");
        if (!hasValue(svcTaskNum)) {
            return;
        }

        // Call Send Task Info to Click Software
        NSZC107001PMsg pMsg = new NSZC107001PMsg();
        setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        setValue(pMsg.svcTaskNum, svcTaskNum);
        NSZC107001 api = new NSZC107001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
        }
    }
    // add end 2016/06/16 QC#9677
}
