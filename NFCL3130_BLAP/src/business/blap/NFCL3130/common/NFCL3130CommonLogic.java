package business.blap.NFCL3130.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3130.NFCL3130CMsg;
import business.blap.NFCL3130.NFCL3130Query;
import business.blap.NFCL3130.NFCL3130SMsg;
import business.blap.NFCL3130.constant.NFCL3130Constant;
import business.db.AR_RCPT_SRCTMsg;
import business.db.DS_BANK_ACCTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/11   Hitachi         K.Kojima        Update          QC#11576
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11576
 *</pre>
 */
public class NFCL3130CommonLogic implements NFCL3130Constant {


    /**
     * 
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static boolean isVal( NFCL3130CMsg bizMsg, NFCL3130SMsg globalMsg, String glblCmpyCd ) {

        //Check for duplication of receipt source code
        
        AR_RCPT_SRCTMsg myTMsg = new AR_RCPT_SRCTMsg();

        DS_BANK_ACCTTMsg bankAcctTMsg = new DS_BANK_ACCTTMsg();

        ZYPEZDItemValueSetter.setValue(bankAcctTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bankAcctTMsg.dsBankAcctPk, bizMsg.dsBankAcctPk);

        bankAcctTMsg = (DS_BANK_ACCTTMsg) EZDTBLAccessor.findByKey(bankAcctTMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.arRcptSrcCd)) {
            //Update
           if ( bizMsg.ezCancelFlag.toString().equals("Y") && bankAcctTMsg != null ) {
                // START 2016/07/11 K.Kojima [QC#11576,MOD]
                // if (
                // ZYPCommonFunc.hasValue(bankAcctTMsg.arRcptSrcCd) &&
                // bankAcctTMsg.arRcptSrcCd.getValue().equals(bizMsg.arRcptSrcCd.getValue())
                // ) {
                if (ZYPCommonFunc.hasValue(bankAcctTMsg.arRcptSrcCd) && !bankAcctTMsg.arRcptSrcCd.getValue().equals(bizMsg.arRcptSrcCd.getValue())) {
                    // END 2016/07/11 K.Kojima [QC#11576,MOD]
                    bizMsg.setMessageInfo("NTAM0012E", new String[]{"BANK ACCOUNT"});
                    return false;
               }
            }
        } else {
            //Insert
            bankAcctTMsg = (DS_BANK_ACCTTMsg) EZDTBLAccessor.findByKey(bankAcctTMsg);
            if ( bankAcctTMsg != null  ) {
                if ( ZYPCommonFunc.hasValue(bankAcctTMsg.arRcptSrcCd) ) {
                     bizMsg.setMessageInfo("NTAM0012E", new String[]{"BANK ACCOUNT"});
                     return false;
                }
            }
        }


        return true;
    }

    /**
     * 
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static BigDecimal findMaxCd( NFCL3130CMsg bizMsg, NFCL3130SMsg globalMsg, String glblCmpyCd ) {
        
        S21SsmEZDResult ssmResult = new S21SsmEZDResult();
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);

        ssmResult = NFCL3130Query.getInstance().findMaxCd(bizMsg, ssmMap);

        if (!ssmResult.isCodeNormal()) {
             bizMsg.setMessageInfo("NFDM0034I");
             return BigDecimal.ZERO;
        }
        
        //
        List<Map> rslt = (List<Map>) ssmResult.getResultObject();
        
        if ( rslt.equals(null) || rslt.size() == 0 ) {
            return BigDecimal.ONE;
        }
        
        return new BigDecimal( rslt.get(0).get("AR_RCPT_SRC_CD").toString() ).add(BigDecimal.ONE);
    }

    // START 2016/07/11 K.Kojima [QC#11576,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsBankAcctPk BigDecimal
     * @param arRcptSrcCd String
     * @param updFlag boolean
     */
    public static boolean updateDsBankAcct(String glblCmpyCd, BigDecimal dsBankAcctPk, String arRcptSrcCd, boolean updFlag) {
        DS_BANK_ACCTTMsg dsBankAcctTMsg = new DS_BANK_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctPk, dsBankAcctPk);
        dsBankAcctTMsg = (DS_BANK_ACCTTMsg) EZDTBLAccessor.findByKey(dsBankAcctTMsg);
        if (dsBankAcctTMsg != null && !arRcptSrcCd.equals(dsBankAcctTMsg.arRcptSrcCd.getValue())) {
            // START 2016/07/12 K.Kojima [QC#11576,ADD]
            if (updFlag == true) {
                clearDsBankAcct(glblCmpyCd, arRcptSrcCd);
            }
            // END 2016/07/12 K.Kojima [QC#11576,ADD]
            ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.arRcptSrcCd, arRcptSrcCd);
            EZDTBLAccessor.update(dsBankAcctTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsBankAcctTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    // END 2016/07/11 K.Kojima [QC#11576,ADD]

    // START 2016/07/12 K.Kojima [QC#11576,ADD]
    /**
     * @param glblCmpyCd String
     * @param arRcptSrcCd String
     */
    public static boolean clearDsBankAcct(String glblCmpyCd, String arRcptSrcCd) {
        S21SsmEZDResult ssmResult = new S21SsmEZDResult();
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("arRcptSrcCd", arRcptSrcCd);
        ssmResult = NFCL3130Query.getInstance().findBankAcctByRcptSrc(ssmMap);
        List<Map<String, Object>> rslt = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (rslt == null || rslt.size() == 0) {
            return true;
        }
        for (int i = 0; i < rslt.size(); i++) {
            Map<String, Object> rsltData = rslt.get(i);
            BigDecimal dsBankAcctPk = (BigDecimal) rsltData.get("DS_BANK_ACCT_PK");
            DS_BANK_ACCTTMsg dsBankAcctTMsg = new DS_BANK_ACCTTMsg();
            ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctPk, dsBankAcctPk);
            dsBankAcctTMsg = (DS_BANK_ACCTTMsg) EZDTBLAccessor.findByKey(dsBankAcctTMsg);
            if (dsBankAcctTMsg != null) {
                ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.arRcptSrcCd, (String) null);
                EZDTBLAccessor.update(dsBankAcctTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsBankAcctTMsg.getReturnCode())) {
                    return false;
                }
            }
        }
        return true;
    }
    // END 2016/07/12 K.Kojima [QC#11576,ADD]
}
