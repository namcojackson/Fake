/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2650;

import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_ACCT_FR;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_ACCT_NM_FR;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_ACCT_NM_TO;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_ACCT_TO;
import static business.blap.NFCL2650.constant.NFCL2650Constant.BILL_TO_CUST_CD;
import static business.blap.NFCL2650.constant.NFCL2650Constant.DRAFT_CRAT_MAX_CNT_CD;
import static business.blap.NFCL2650.constant.NFCL2650Constant.SELL_TO_CUST;
import static business.blap.NFCL2650.constant.NFCL2650Constant.NFCM0798E;
import static business.blap.NFCL2650.constant.NFCL2650Constant.NFCM0803E;
import static business.blap.NFCL2650.constant.NFCL2650Constant.NFCM0807E;
import static business.blap.NFCL2650.constant.NFCL2650Constant.NFCM0831E;
import static business.blap.NFCL2650.constant.NFCL2650Constant.NZZM0002I;
import static business.blap.NFCL2650.constant.NFCL2650Constant.NFCM0910E;
import static business.blap.NFCL2650.constant.NFCL2650Constant.COMMA;
import static business.blap.NFCL2650.constant.NFCL2650Constant.PROC_MODE_CD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NFZC203001PMsg;

import com.canon.cusa.s21.api.NFZ.NFZC203001.NFZC203001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/03/28   Fujitsu         C.Naito         Update          QC#6110
 * 2016/04/22   Fujitsu         C.Naito         Update          QC#7069 
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 * 2018/05/15   Fujitsu         Y.Matsui       Update          QC#24329
 * 2020/01/16   Fujitsu         M.Ishii           Update          QC#55216
 *</pre>
 */
public class NFCL2650BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL2650CMsg bizMsg = (NFCL2650CMsg) cMsg;
            NFCL2650SMsg glblMsg = (NFCL2650SMsg) sMsg;

            if ("NFCL2650Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL2650Scrn00_CMN_Submit(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL2650Scrn00_CMN_Submit(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        // START 2018/05/15 [QC#24329,ADD]
        ZYPTableUtil.clear(bizMsg.X);
        // END   2018/05/15 [QC#24329,ADD]

        if (!bizMsg.locNum.isClear()) { // Mod 2016/11/25 [QC#13259, MOD]
            searchBillToCust(bizMsg, glblMsg);

        } else {
            searchCustomerAccount(bizMsg, glblMsg);
        }
    }

    private void searchCustomerAccount(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        if (!bizMsg.dsAcctNum_FR.isClear() && !bizMsg.dsAcctNum_TO.isClear()) {
            S21SsmEZDResult ssmResultFrom = NFCL2650Query.getInstance().getDsAcctCustFromNum(bizMsg, glblMsg);
            if (ssmResultFrom.isCodeNotFound()) {
                bizMsg.dsAcctNum_FR.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_FR, bizMsg.dsAcctNum_FR.getValue(), SELL_TO_CUST });
                return;
            }

            S21SsmEZDResult ssmResultTo = NFCL2650Query.getInstance().getDsAcctCustToNum(bizMsg, glblMsg);
            if (ssmResultTo.isCodeNotFound()) {
                bizMsg.dsAcctNum_TO.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_TO, bizMsg.dsAcctNum_TO.getValue(), SELL_TO_CUST });
                return;
            }
        } else if (!bizMsg.dsAcctNm_F2.isClear() && !bizMsg.dsAcctNm_T2.isClear()) {
            S21SsmEZDResult ssmResultFrom = NFCL2650Query.getInstance().getDsAcctCustFromNm(bizMsg, glblMsg);
            if (ssmResultFrom.isCodeNotFound()) {
                bizMsg.dsAcctNm_F2.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_NM_FR, bizMsg.dsAcctNm_F2.getValue(), SELL_TO_CUST });
                return;
            }

            S21SsmEZDResult ssmResultTo = NFCL2650Query.getInstance().getDsAcctCustToNm(bizMsg, glblMsg);
            if (ssmResultTo.isCodeNotFound()) {
                bizMsg.dsAcctNm_T2.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_ACCT_NM_TO, bizMsg.dsAcctNm_T2.getValue(), SELL_TO_CUST });
                return;
            }
        }
        BigDecimal createMaxCount = ZYPCodeDataUtil.getNumConstValue(DRAFT_CRAT_MAX_CNT_CD, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(createMaxCount)) {
            createMaxCount = new BigDecimal("10");
        }
        
        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getBetweenAcctNum(bizMsg, createMaxCount.intValue());
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NFCM0803E, new String[] {});
            return;
        }
        // [QC#6110] UPDATE start
        if (ssmResult.getQueryResultCount() > createMaxCount.intValue()) {
            bizMsg.setMessageInfo(NFCM0798E, new String[] {createMaxCount.toString() });
            return;
        }
        // [QC#6110] UPDATE end
        NFZC203001 nfzc2030Api = new NFZC203001();
        NFZC203001PMsg pMsg = new NFZC203001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.acctDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.arStmtDt, bizMsg.acctDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.procModeCd, PROC_MODE_CD);
        // START   2020/01/16 [QC#55216,MOD]
        // List<String> acctNumList = (List<String>) ssmResult.getResultObject();
        List<String> acctNumList = getStmtNeedAcct((List<String>) ssmResult.getResultObject());
        if (acctNumList.isEmpty()) {
            bizMsg.setMessageInfo(NFCM0831E);
            return;
        }
        String invalidAcctNum = getCustomerWithInvalidCollector(acctNumList);
        if (ZYPCommonFunc.hasValue(invalidAcctNum)) {
            bizMsg.setMessageInfo(NFCM0910E, new String[] {invalidAcctNum });
            return;
        }
        // END   2020/01/16 [QC#55216,MOD]
        for (String acctNum : acctNumList) {
            ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, acctNum);
            nfzc2030Api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                bizMsg.setMessageInfo(msgId);
                EZDConnectionMgr.getInstance().rollback();
                return;
            }

            // START 2018/05/15 [QC#24329,ADD]
            if (!addArStmt(bizMsg, pMsg)) {
                return;
            }
            // END   2018/05/15 [QC#24329,ADD]
        }

        // START 2018/05/15 [QC#24329,ADD]
        if (bizMsg.X.getValidCount() == 0) {
            bizMsg.setMessageInfo(NFCM0831E);
            return;
        }
        // END   2018/05/15 [QC#24329,ADD]

        bizMsg.setMessageInfo(NZZM0002I);
    }

    private void searchBillToCust(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        // [QC#7069] UPDATE start 
        S21SsmEZDResult ssmResult = null;
        ssmResult = NFCL2650Query.getInstance().getLocNm(bizMsg.locNum.getValue()); // Mod 2016/11/25 [QC#13259] getBillToCustNm -> getLocNm
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> billToCustNmResultMap = (Map<String, Object>) ssmResult.getResultObject();

            if (billToCustNmResultMap == null) {
                bizMsg.locNum.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_CD, bizMsg.locNum.getValue(), BILL_TO_CUST }); // MOD 2016/11/25 [QC#13259]
                return;
            }
            // START   2020/01/16 [QC#55216,ADD]
            if (!checkStmtNeedAcctFromBillTo((String) billToCustNmResultMap.get("BILL_TO_CUST_CD"))) {
                bizMsg.setMessageInfo(NFCM0831E);
                return;
            }
            String invalidAcctNum = getCustomerWithInvalidCollectorFromBillTo((String) billToCustNmResultMap.get("BILL_TO_CUST_CD"));
            if (ZYPCommonFunc.hasValue(invalidAcctNum)) {
                bizMsg.setMessageInfo(NFCM0910E, new String[] {invalidAcctNum });
                return;
            }
            // END   2020/01/16 [QC#55216,ADD]
            // START 2016/11/25 [QC#13259, ADD]
            NFZC203001 nfzc2030Api = new NFZC203001();
            NFZC203001PMsg pMsg = new NFZC203001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.acctDt.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.arStmtDt, bizMsg.acctDt.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.procModeCd, PROC_MODE_CD);
            ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, (String) billToCustNmResultMap.get("BILL_TO_CUST_CD"));

            nfzc2030Api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                bizMsg.setMessageInfo(msgId);
                EZDConnectionMgr.getInstance().rollback();
                return;
            }

            // START 2018/05/15 [QC#24329,ADD]
            addArStmt(bizMsg, pMsg);

            if (bizMsg.X.getValidCount() == 0) {
                bizMsg.setMessageInfo(NFCM0831E);
                return;
            }
            // END   2018/05/15 [QC#24329,ADD]

            bizMsg.setMessageInfo(NZZM0002I);
            // END 2016/11/25 [QC#13259, ADD]
        } else {
            bizMsg.locNum.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_CD, bizMsg.locNum.getValue(), BILL_TO_CUST }); // MOD 2016/11/25 [QC#13259]
            return;
        }
//        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getBillToCustNm(bizMsg, glblMsg);
//        if (ssmResult.isCodeNotFound()) {
//            bizMsg.billToCustCd.setErrorInfo(1, NFCM0807E, new String[] {BILL_TO_CUST_CD, bizMsg.billToCustCd.getValue(), BILL_TO_CUST });
//            return;
//        }
        // [QC#7069] UPDATE end 

        // START 2016/11/25 [QC#13259, DEL]
//        NFZC203001 nfzc2030Api = new NFZC203001();
//        NFZC203001PMsg pMsg = new NFZC203001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.acctDt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.arStmtDt, bizMsg.acctDt.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.procModeCd, PROC_MODE_CD);
//        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.locNum);
//        nfzc2030Api.execute(pMsg, ONBATCH_TYPE.ONLINE);
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
//            String msgId = msg.getXxMsgid();
//            bizMsg.setMessageInfo(msgId);
//            EZDConnectionMgr.getInstance().rollback();
//        }
//        bizMsg.setMessageInfo(NZZM0002I);
        // START 2016/11/25 [QC#13259, DEL]
    }

    // START 2018/05/15 [QC#24329,ADD]
    private boolean addArStmt(NFCL2650CMsg bizMsg, NFZC203001PMsg pMsg) {
        for (int i = 0; i < pMsg.xxArStmtInfoList.getValidCount(); i++) {
            int stmtIdx = bizMsg.X.getValidCount();
            if (stmtIdx == bizMsg.X.length()) {
                bizMsg.setMessageInfo(NFCM0798E, new String[] {String.valueOf(bizMsg.X.length()) });
                return false;
            }
            EZDMsg.copy(pMsg.xxArStmtInfoList.no(i), "", bizMsg.X.no(stmtIdx), "X");
            bizMsg.X.setValidCount(stmtIdx + 1);
        }
        ZYPTableUtil.clear(pMsg.xxArStmtInfoList);
        return true;
    }
    // END   2018/05/15 [QC#24329,ADD]
    // START   2020/01/16 [QC#55216,ADD]
    private List<String> getStmtNeedAcct(List<String> acctNumList) {
        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getStmtNeedAcct(acctNumList);
        return (List<String>) ssmResult.getResultObject(); 
    }

    private String getCustomerWithInvalidCollector(List<String> acctNumList) {
        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getCustomerWithInvalidCollector(acctNumList);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        StringBuilder invalidCustomerList = new StringBuilder();
        List<String> rsList = (List<String>) ssmResult.getResultObject();
        for (int i = 0; i < rsList.size(); i++) {
            invalidCustomerList.append(rsList.get(i));
            if (i != rsList.size() - 1) {
                invalidCustomerList.append(COMMA);
            }
        }
        return invalidCustomerList.toString();
    }

    private String getCustomerWithInvalidCollectorFromBillTo(String billToCustCd) {
        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getCustomerWithInvalidCollectorFromBillTo(billToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }
    private boolean checkStmtNeedAcctFromBillTo(String billToCustCd) {
        S21SsmEZDResult ssmResult = NFCL2650Query.getInstance().getStmtNeedAcctFromBillTo(billToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }
        return true;
    }
    // END   2020/01/16 [QC#55216,ADD]
}
