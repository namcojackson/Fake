package business.blap.NFCL0510;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL0510.common.NFCL0510CommonLogic;
import business.blap.NFCL0510.constant.NFCL0510Constant;
import business.db.AR_RCPT_RCV_INTFCTMsg;
import business.db.AR_RCPT_RCV_INTFCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOCK_BOX_NTFY_STS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 11/30/2016   Fujitsu         S.Fujita        Update          QC#16255
 * 12/21/2016   Fujitsu         T.Murai         Update          QC#16710
 * 06/11/2018   Hitachi         Y.Takeno        Create          QC#19723
 * 06/15/2018   Hitachi         E.Kameishi      Create          QC#25696
 * 2018/07/27   Fujitsu         H.Ikeda         Update          QC#25918
 * 2018/11/16   Fujitsu         S.Ohki          Update          QC#29278
 *</pre>
 */
public class NFCL0510BL06 extends S21BusinessHandler implements NFCL0510Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL0510Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_CMN_Submit================================START", this);

        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        if (!NFCL0510CommonLogic.copyPage(bizMsg, globalMsg)) {
            NFCL0510CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
            return;
        }

        // START 2016/11/30 S.Fujita [QC#16255,DEL]
//        Map<String, String> updStsMap = new HashMap<String, String>();
//
//        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
////            if (!globalMsg.A.no(i).arLockBoxStsCd_A.getValue().equals(globalMsg.A.no(i).arLockBoxStsCd_AB.getValue())) {
//            if (!updStsMap.containsKey(globalMsg.A.no(i).arLockBoxFileNm_A.getValue())) {
//                updStsMap.put(globalMsg.A.no(i).arLockBoxFileNm_A.getValue(), globalMsg.A.no(i).arLockBoxStsCd_A.getValue());
//                Map<String, Object> ssmParam = new HashMap<String, Object>();
//                ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
//                ssmParam.put("arLockBoxFileNm", String.valueOf(globalMsg.A.no(i).arLockBoxFileNm_A.getValue()));
//                S21SsmEZDResult maxUpTime = NFCL0510Query.getInstance().getMaxUptime(bizMsg, ssmParam);
//
//                if (maxUpTime.isCodeNormal()) {
//                    Map<String, Object> map = (Map<String, Object>) maxUpTime.getResultObject();
//                    String ezUpTime = (String) map.get("EZUPTIME");
//                    String ezUpTimeZone = (String) map.get("EZUPTIMEZONE");
//                    if (!ezUpTime.equals(globalMsg.A.no(i).ezUpTime_A.getValue())) {
//                        bizMsg.setMessageInfo("ZZZM9004E");
//                        return;
//                    } else if (!ezUpTimeZone.equals(globalMsg.A.no(i).ezUpTimeZone_A.getValue())) {
//                        bizMsg.setMessageInfo("ZZZM9004E");
//                        return;
//                    }
//                } else {
//                    bizMsg.setMessageInfo("ZZZM9004E");
//                    return;
//                }
//            }
//        }
        // END   2016/11/30 S.Fujita [QC#16255,DEL]

        // START 2016/12/21 T.Murai [QC#16255,ADD]
        // START 2018/06/11 [QC#19723, MOD]
        // Map<String, String> updStsMap = new HashMap<String, String>();
        // for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
        //     if (!updStsMap.containsKey(globalMsg.A.no(i).arLockBoxFileNm_A.getValue())) {
        //         updStsMap.put(globalMsg.A.no(i).arLockBoxFileNm_A.getValue(), globalMsg.A.no(i).arLockBoxStsCd_A.getValue());
        //     }
        // }
        Map<String, Map<String, Object>> updStsMap = createUpdateStatusMap(globalMsg);
        // END   2018/06/11 [QC#19723, MOD]
        // END   2016/12/21 T.Murai [QC#16255,ADD]

        List<AR_RCPT_RCV_INTFCTMsg> updList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

        Map<String, String> updStsMapB = new HashMap<String, String>();
        String preArBatRcptNm = "";

        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            // START 2016/11/30 S.Fujita [QC#16255,DEL]
//            if (!globalMsg.B.no(i).custRcptNum_B.getValue().equals(globalMsg.B.no(i).custRcptNum_BB.getValue())) {
            // END   2016/11/30 S.Fujita [QC#16255,DEL]

            boolean hasUpdData = false;
            AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvIntfcPk, globalMsg.B.no(i).arRcptRcvIntfcPk_B.getValue());
            AR_RCPT_RCV_INTFCTMsg updMsg = (AR_RCPT_RCV_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            // START 2016/12/21 [QC#16710,DEL]
//            if(!updMsg.custRcptNum.getValue().equals(globalMsg.B.no(i).custRcptNum_B.getValue())) {
//                ZYPEZDItemValueSetter.setValue(updMsg.custRcptNum, globalMsg.B.no(i).custRcptNum_B.getValue());
//                hasUpdData = true;
//            }
//            if (updMsg.custRcptAmt.getValue().compareTo(globalMsg.B.no(i).custRcptAmt_B.getValue()) != 0) {
//                ZYPEZDItemValueSetter.setValue(updMsg.custRcptAmt, globalMsg.B.no(i).custRcptAmt_B.getValue());
//                hasUpdData = true;
//            }
//            if(!updMsg.custBankRteNum.getValue().equals(globalMsg.B.no(i).custBankRteNum_B.getValue())) {
//                ZYPEZDItemValueSetter.setValue(updMsg.custBankRteNum, globalMsg.B.no(i).custBankRteNum_B.getValue());
//                hasUpdData = true;
//            }
//            if(!updMsg.custDsBankAcctNum.getValue().equals(globalMsg.B.no(i).custDsBankAcctNum_B.getValue())) {
//                ZYPEZDItemValueSetter.setValue(updMsg.custDsBankAcctNum, globalMsg.B.no(i).custDsBankAcctNum_B.getValue());
//                hasUpdData = true;
//            }
//            if(!updMsg.payerCustCd.getValue().equals(globalMsg.B.no(i).payerCustCd_B.getValue())) {
//                ZYPEZDItemValueSetter.setValue(updMsg.payerCustCd, globalMsg.B.no(i).payerCustCd_B.getValue());
//                hasUpdData = true;
//            }
//            if(!updMsg.custInvNum.getValue().equals(globalMsg.B.no(i).custInvNum_B.getValue())) {
//                ZYPEZDItemValueSetter.setValue(updMsg.custInvNum, globalMsg.B.no(i).custInvNum_B.getValue());
//                hasUpdData = true;
//            }
//            if (updMsg.custInvAmt.getValue().compareTo(globalMsg.B.no(i).custInvAmt_B.getValue()) != 0) {
//                ZYPEZDItemValueSetter.setValue(updMsg.custInvAmt, globalMsg.B.no(i).custInvAmt_B.getValue());
//                hasUpdData = true;
//            }
            // END   2016/12/21 [QC#16710,DEL]

            // START 2016/12/21 [QC#16710,DEL]
            ZYPEZDItemValueSetter.setValue(updMsg.custRcptNum, globalMsg.B.no(i).custRcptNum_B.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.custRcptAmt, globalMsg.B.no(i).custRcptAmt_B.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.custBankRteNum, globalMsg.B.no(i).custBankRteNum_B.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.custDsBankAcctNum, globalMsg.B.no(i).custDsBankAcctNum_B.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.payerCustCd, globalMsg.B.no(i).payerCustCd_B.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.custInvNum, globalMsg.B.no(i).custInvNum_B.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.custInvAmt, globalMsg.B.no(i).custInvAmt_B.getValue());
            // END 2016/12/21 [QC#16710,DEL]

            if (!updMsg.ezUpTime.getValue().equals(globalMsg.B.no(i).ezUpTime_B.getValue())) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return;
            } else if (!updMsg.ezUpTimeZone.getValue().equals(globalMsg.B.no(i).ezUpTimeZone_B.getValue())) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return;
            }

//            if (hasUpdData) { // DEL 2016/12/21 [QC#19710]
            //START 2018/06/15 E.Kameishi [QC#25696,MOD]
            if (!AR_LOCK_BOX_STS.RECEIPT_WORK_GENERATED.equals(updMsg.arLockBoxStsCd)) {
                updList.add(updMsg);
            }
            //END 2018/06/15 E.Kameishi [QC#25696,MOD]
                // START 2016/12/21 T.Murai [QC#16710,DEL]
                // START 2016/12/05 S.Fujita [QC#16255,ADD]
//                if (!preArBatRcptNm.equals(globalMsg.B.no(i).arBatRcptNm_B.getValue())) {
//                     updStsMapB.put(globalMsg.B.no(i).arBatRcptNm_B.getValue(), AR_LOCK_BOX_STS.REPROCESS);
//                    preArBatRcptNm = globalMsg.B.no(i).arBatRcptNm_B.getValue();
//                }
                // END   2016/12/05 S.Fujita [QC#16255,ADD]
//            }
                // END   2016/12/21 T.Murai [QC#16710,DEL]
        }

        // START 2016/12/05 S.Fujita [QC#16255,DEL]
//        if (!hasUpdData) {
//            bizMsg.setMessageInfo("NFCM0763E");
//            return;
//        }
        // END   2016/12/05 S.Fujita [QC#16255,DEL]

        if (updList.size() > 0) {
            int iRet = EZDFastTBLAccessor.update((AR_RCPT_RCV_INTFCTMsg[]) updList.toArray(new AR_RCPT_RCV_INTFCTMsg[updList.size()]));
            if (iRet <= 0) {
                bizMsg.setMessageInfo("NFCM0032E");
                return;
            }
        }

        // START 2018/06/11 [QC#19723, MOD]
        // for (Map.Entry<String, String> e : updStsMap.entrySet()) {
        for (Map.Entry<String, Map<String, Object>> e : updStsMap.entrySet()) {
        // END   2018/06/11 [QC#19723, MOD]

            //START 2018/06/15 E.Kameishi [QC#25696,MOD]
            //AR_RCPT_RCV_INTFCTMsg updateCondTMsg = new AR_RCPT_RCV_INTFCTMsg();
            //ZYPEZDItemValueSetter.setValue(updateCondTMsg.glblCmpyCd, getGlobalCompanyCode());
            // START 2018/06/11 [QC#19723, MOD]
            Map<String, Object> elm = e.getValue();
            String arLockBoxFileNm = (String) elm.get(AR_LOCK_BOX_FILE_NM);
            String arLockBoxBatNm  = (String) elm.get(AR_LOCK_BOX_BAT_NUM);
            String arLockBoxStsCd  = (String) elm.get(AR_LOCK_BOX_STS_CD);
            // ZYPEZDItemValueSetter.setValue(updateCondTMsg.arLockBoxFileNm, e.getKey());
            //ZYPEZDItemValueSetter.setValue(updateCondTMsg.arLockBoxFileNm, arLockBoxFileNm);
            //ZYPEZDItemValueSetter.setValue(updateCondTMsg.arLockBoxBatNum, arLockBoxBatNm);
            // END   2018/06/11 [QC#19723, MOD]

            // Value for update
            //AR_RCPT_RCV_INTFCTMsg updateValTMsg = new AR_RCPT_RCV_INTFCTMsg();
            // START 2018/06/11 [QC#19723, MOD]
            // ZYPEZDItemValueSetter.setValue(updateValTMsg.arLockBoxStsCd, e.getValue());
            //ZYPEZDItemValueSetter.setValue(updateValTMsg.arLockBoxStsCd, arLockBoxStsCd);
            // END   2018/06/11 [QC#19723, MOD]

            AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();

            inMsg.setConditionValue("arLockBoxFileNm01", arLockBoxFileNm);
            inMsg.setConditionValue("arLockBoxBatNum01", arLockBoxBatNm);
            inMsg.setConditionValue("arLockBoxStsCd01", AR_LOCK_BOX_STS.RECEIPT_WORK_GENERATED);
            inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            inMsg.setSQLID("003");

            AR_RCPT_RCV_INTFCTMsgArray tMsgArray = (AR_RCPT_RCV_INTFCTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
            
            for (int i= 0; i < tMsgArray.getValidCount(); i++) {
                AR_RCPT_RCV_INTFCTMsg updTMsg = tMsgArray.no(i);
                ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxStsCd, arLockBoxStsCd);
                // START 2018/07/27 [QC#25918, MOD]
                // START 2018/11/16 [QC#29278, MOD]
//                ZYPEZDItemValueSetter.setValue(updTMsg.lockBoxNtfyStsCd, LOCK_BOX_NTFY_STS.NO_SEND);
                if (AR_LOCK_BOX_STS.REPROCESS.equals(arLockBoxStsCd)) {
                    ZYPEZDItemValueSetter.setValue(updTMsg.lockBoxNtfyStsCd, LOCK_BOX_NTFY_STS.NO_SEND);
                }
                // END   2018/11/16 [QC#29278, MOD]
                // END   2018/07/27 [QC#25918, MOD]
                EZDTBLAccessor.update(updTMsg);
                
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0032E");
                    return;
                }
            }

            // START 2018/06/11 [QC#19723, MOD]
            // int iRet = S21FastTBLAccessor.updateByPartialValue(updateCondTMsg, new String[] {"glblCmpyCd", "arLockBoxFileNm" }, updateValTMsg, new String[] {"arLockBoxStsCd" });
            //int iRet = S21FastTBLAccessor.updateByPartialValue(updateCondTMsg, new String[] {"glblCmpyCd", "arLockBoxFileNm", "arLockBoxBatNum" }, updateValTMsg, new String[] {"arLockBoxStsCd" });
            // END   2018/06/11 [QC#19723, MOD]

            //if (iRet <= 0) {
            //    bizMsg.setMessageInfo("NFCM0032E");
            //    return;
            //}
            //END 2018/06/15 E.Kameishi [QC#25696,MOD]
        }
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_CMN_Submit================================END", this);
    }

    // START 2018/06/11 [QC#19723, ADD]
    private Map<String, Map<String, Object>> createUpdateStatusMap(NFCL0510SMsg globalMsg) {
        Map<String, Map<String, Object>> updStsMap = new HashMap<String, Map<String, Object>>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            String mapKey = ZYPCommonFunc.concatString(globalMsg.A.no(i).arLockBoxFileNm_A.getValue(), "|", globalMsg.A.no(i).arLockBoxBatNum_A.getValue());
            if (!updStsMap.containsKey(mapKey)) {
                Map<String, Object> elm = new HashMap<String, Object>();
                elm.put(AR_LOCK_BOX_FILE_NM, globalMsg.A.no(i).arLockBoxFileNm_A.getValue());
                elm.put(AR_LOCK_BOX_BAT_NUM, globalMsg.A.no(i).arLockBoxBatNum_A.getValue());
                elm.put(AR_LOCK_BOX_STS_CD , globalMsg.A.no(i).arLockBoxStsCd_A.getValue());
                updStsMap.put(mapKey, elm);
            }
        }
        return updStsMap;
    }
    // END   2018/06/11 [QC#19723, ADD]
}
