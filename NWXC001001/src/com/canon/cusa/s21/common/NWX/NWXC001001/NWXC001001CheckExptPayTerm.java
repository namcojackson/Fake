/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/27/2011   Fujitsu         S.Takami        Create          2069(PRD)
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import business.db.AR_EXPT_BILL_MAPTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;

//import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

public class NWXC001001CheckExptPayTerm {

    /**
     * isTopStopMdse, check the mdse is target of TOP STOP or not.
     * @param glblCmpyCd Global Company Code
     * @param pmtTermCashDiscCd payment term cash disc code
     * @param billToCustCd bill to cust code
     * @return true: the merchandise is target of TOP STOP false: the merchandise is not target of TOP STOP
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidExprtPayTerm(String glblCmpyCd, String pmtTermCashDiscCd, String billToCustCd) {
    	PMT_TERM_CASH_DISCTMsg pmtTermCashDiscCond = new PMT_TERM_CASH_DISCTMsg();
    	pmtTermCashDiscCond.glblCmpyCd.setValue(glblCmpyCd);
    	pmtTermCashDiscCond.pmtTermCashDiscCd.setValue(pmtTermCashDiscCd);

    	PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg)S21CacheTBLAccessor.findByKey(pmtTermCashDiscCond);
    	return isValidExprtPayTerm(glblCmpyCd, pmtTermCashDiscTMsg, billToCustCd);
    }
    
    public static boolean isValidExprtPayTerm(String glblCmpyCd, PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg, String billToCustCd) {
        if (!hasValue(glblCmpyCd) || !hasValue(billToCustCd)) {
            return false;
        }

        if (pmtTermCashDiscTMsg == null) {
            return false;
        }
        if (!hasValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd)) {
            return false;
        }
        // 1. Is cash discount?
    	if (!hasValue(pmtTermCashDiscTMsg.cashDiscTermCd)) {
    		return true;
    	}
        // 2. Is export?
        boolean isExptFlg = isExpt(glblCmpyCd, billToCustCd);
        if (!isExptFlg) {
        	return true;
        }
        return false;
    	
    }
    @SuppressWarnings("unchecked")
    private static boolean isExpt(String glblCmpyCd, String billToCustCd) {
    	boolean retFlg = false;
    	AR_EXPT_BILL_MAPTMsg arExptBillMapCond = new AR_EXPT_BILL_MAPTMsg();

    	arExptBillMapCond.glblCmpyCd.setValue(glblCmpyCd);
    	arExptBillMapCond.arExptBillMapCd.setValue(billToCustCd);

    	AR_EXPT_BILL_MAPTMsg arExptBillMap = (AR_EXPT_BILL_MAPTMsg)S21CacheTBLAccessor.findByKey(arExptBillMapCond);
    	if (arExptBillMap != null) {
    		retFlg = true;
    	}

        return retFlg;
    }
}
