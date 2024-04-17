/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2650;

import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_ACCT_FR;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_ACCT_TO;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_CD;
import static business.blap.NFCL2650.constant.NFCL2650Constant.SELL_TO_CUST;
import static business.blap.NFCL2650.constant.NFCL2650Constant.NFCM0807E;

import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL2650.common.NFCL2650CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/04/22   Fujitsu         C.Naito         Update          QC#7069
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 *</pre>
 */
public class NFCL2650BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL2650CMsg bizMsg = (NFCL2650CMsg) cMsg;
            NFCL2650SMsg glblMsg = (NFCL2650SMsg) sMsg;

            if ("NFCL2650_INIT".equals(screenAplID)) {
                doProcess_NFCL2650_INIT(bizMsg, glblMsg);
            } else if ("NFCL2650Scrn00_Get_CustNmFrom".equals(screenAplID)) {
                doProcess_NFCL2650Scrn00_Get_CustNmFrom(bizMsg, glblMsg);
            } else if ("NFCL2650Scrn00_Get_CustNmTo".equals(screenAplID)) {
                doProcess_NFCL2650Scrn00_Get_CustNmTo(bizMsg, glblMsg);
            } else if ("NFCL2650Scrn00_Get_BillToCust".equals(screenAplID)) {
                doProcess_NFCL2650Scrn00_Get_BillToCust(bizMsg, glblMsg);
            } else if ("NFCL2650Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL2650Scrn00_CMN_Clea(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFCL2650_INIT(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.acctDt, NFCL2650CommonLogic.findArAcctDtInfoForAcctDt(getGlobalCompanyCode()));

    }

    private void doProcess_NFCL2650Scrn00_Get_CustNmFrom(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        bizMsg.dsAcctNm_F1.clear();
        if (bizMsg.dsAcctNum_FR.isClear()) {
            return;
        }
        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getAcctNmFrom(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            if (ssmResult.isCodeNotFound()) {
                bizMsg.dsAcctNum_FR.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_FR, bizMsg.dsAcctNum_FR.getValue(), SELL_TO_CUST });
                return;
            }
        } else {
            bizMsg.dsAcctNum_FR.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_FR, bizMsg.dsAcctNum_FR.getValue(), SELL_TO_CUST });
            return;
        }
        String acctNm = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_F1, acctNm);

    }

    private void doProcess_NFCL2650Scrn00_Get_CustNmTo(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        bizMsg.dsAcctNm_T1.clear();
        if (bizMsg.dsAcctNum_TO.isClear()) {
            return;
        }
        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getAcctNmTo(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            if (ssmResult.isCodeNotFound()) {
                bizMsg.dsAcctNum_TO.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_TO, bizMsg.dsAcctNum_TO.getValue(), SELL_TO_CUST });
                return;
            }
        } else {
            bizMsg.dsAcctNum_TO.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_TO, bizMsg.dsAcctNum_TO.getValue(), SELL_TO_CUST });
            return;
        }
        String acctNm = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_T1, acctNm);
    }

    private void doProcess_NFCL2650Scrn00_Get_BillToCust(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        // START 2016/11/25 [QC#13259, MOD]
        // bizMsg.dsAcctNm_BL.clear();
        // if (bizMsg.billToCustCd.isClear()) {
        // return;
        // }
        bizMsg.locNm.clear();
        if (bizMsg.locNum.isClear()) {
            return;
        }
        // END 2016/11/25 [QC#13259, MOD]

        // [QC#7069] UPDATE start
        S21SsmEZDResult ssmResult = null;
        // START 2016/11/25 [QC#13259, MOD]
        // ssmResult = NFCL2650Query.getInstance().getBillToCustNm(bizMsg, glblMsg);
        ssmResult = NFCL2650Query.getInstance().getLocNm(bizMsg.locNum.getValue());
        // END   2016/11/25 [QC#13259, MOD]
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> billToCustNmResultMap = (Map<String, Object>) ssmResult.getResultObject();

            if (billToCustNmResultMap == null) {
                bizMsg.locNum.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_CD, bizMsg.locNum.getValue(), BILL_TO_CUST }); // MOD 2016/11/25 [QC#13259, MOD]
                return;
            }

            // if BillToLocNm(DS_LOC_NM) is blank(NULL) return blank.
            String billToCusttNm = (String) billToCustNmResultMap.get("DS_LOC_NM");
            if (billToCusttNm == null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.locNm, ""); // MOD 2016/11/25 [QC#13259, MOD] dsAcctNm_BL -> locNm
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.locNm, billToCusttNm); // MOD 2016/11/25 [QC#13259, MOD] dsAcctNm_BL -> locNm
            }

        } else {
            bizMsg.locNum.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_CD, bizMsg.locNum.getValue(), BILL_TO_CUST }); // MOD 2016/11/25 [QC#13259, MOD]
        }

//        //S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getBillToCustNm(bizMsg, glblMsg);
//        if (ssmResult.isCodeNormal()) {
//            if (ssmResult.isCodeNotFound()) {
//                bizMsg.billToCustCd.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_CD, bizMsg.billToCustCd.getValue(), BILL_TO_CUST });
//                return;
//            }
//        } else {
//            bizMsg.billToCustCd.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_CD, bizMsg.billToCustCd.getValue(), BILL_TO_CUST });
//            return;
//        }
        // [QC#7069] UPDATE end 
    }

    private void doProcess_NFCL2650Scrn00_CMN_Clea(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        bizMsg.dsAcctNum_FR.clear();
        bizMsg.dsAcctNum_TO.clear();
        bizMsg.acctDt.clear();
        bizMsg.dsAcctNm_F1.clear();
        bizMsg.dsAcctNm_T1.clear();
        bizMsg.dsAcctNm_F2.clear();
        bizMsg.dsAcctNm_T2.clear();
        // START 2016/11/25 [QC#13259, MOD]
        // bizMsg.billToCustCd.clear();
        // bizMsg.dsAcctNm_BL.clear();
        bizMsg.locNum.clear();
        bizMsg.locNm.clear();
        // START 2016/11/25 [QC#13259, MOD]
        doProcess_NFCL2650_INIT(bizMsg, glblMsg);
    }

}
