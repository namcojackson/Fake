/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/13/2010   Fujitsu         S.Yamamoto      Create          N/A
 * 11/23/2010   Fujitsu         S.Yamamoto      Update          540(All2)
 * 03/16/2011   Fujitsu         N.Aoyama        Update          1481(Prod)
 * 05/02/2011   Fujitsu         S.Yamamoto      Update          1819(Prod)
 *</pre>
 */
package business.blap.NWAL0330;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CR_RISK_CLSTMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_PRFLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NWAL0330BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL0330_INIT".equals(screenAplID)) {
                doProcess_NWAL0330_INIT(cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL0330_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "doProcess_NWAL0330_INIT================================START", this);
        }
        
        NWAL0330CMsg bizMsg = (NWAL0330CMsg) cMsg;
        
        BILL_TO_CUSTTMsg billToCustMsg = new BILL_TO_CUSTTMsg();
        billToCustMsg.setSQLID("023");
        billToCustMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        billToCustMsg.setConditionValue("billToCustCd01", bizMsg.billToCustCd.getValue());
        BILL_TO_CUSTTMsgArray billToCustMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billToCustMsg);
        if (billToCustMsgArray.getValidCount() > 0) {
            bizMsg.locNm.setValue(billToCustMsgArray.no(0).locNm.getValue());
        }
        
        CUST_CR_PRFLTMsg custCrPrflMsg = new CUST_CR_PRFLTMsg();
        custCrPrflMsg.setSQLID("006");
        custCrPrflMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        custCrPrflMsg.setConditionValue("billToCustCd01", bizMsg.billToCustCd.getValue());
        CUST_CR_PRFLTMsgArray custCrPrflMsgArray = (CUST_CR_PRFLTMsgArray) EZDTBLAccessor.findByCondition(custCrPrflMsg);
        String crRiskClsCd = null;
        if (custCrPrflMsgArray.getValidCount() > 0) {
            bizMsg.crLimitAmt.setValue(custCrPrflMsgArray.no(0).crLimitAmt.getValue());
            crRiskClsCd = custCrPrflMsgArray.no(0).crRiskClsCd.getValue();
            bizMsg.crChkReqTpCd.setValue(custCrPrflMsgArray.no(0).crChkReqTpCd.getValue());
            bizMsg.crLimitChngDt.setValue(custCrPrflMsgArray.no(0).crLimitChngDt.getValue());
            bizMsg.lastCrRvwDt.setValue(custCrPrflMsgArray.no(0).lastCrRvwDt.getValue());
            bizMsg.nextCrRvwDueDt.setValue(custCrPrflMsgArray.no(0).nextCrRvwDueDt.getValue());
            bizMsg.crRvwDtChkReqFlg.setValue(custCrPrflMsgArray.no(0).crRvwDtChkReqFlg.getValue());
            bizMsg.nextRevnDt.setValue(custCrPrflMsgArray.no(0).nextRevnDt.getValue());
            bizMsg.ucc1RevnDt.setValue(custCrPrflMsgArray.no(0).ucc1RevnDt.getValue());
            bizMsg.psnGtdFlg.setValue(custCrPrflMsgArray.no(0).psnGtdFlg.getValue());
            bizMsg.arBalAmt.setValue(custCrPrflMsgArray.no(0).arBalAmt.getValue());
            
            // On Credit Hold Amount
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", getGlobalCompanyCode());
            queryParam.put("billToCustCd", bizMsg.billToCustCd.getValue());
            queryParam.put("cpoOrdTp_TS", CPO_ORD_TP.TRIAL_TO_SALES);
            queryParam.put("cpoOrdTp_LS", CPO_ORD_TP.LOAN_TO_SALES);
            queryParam.put("hldTp_CH", HLD_TP.CREDIT_HOLD);
            
            S21SsmEZDResult ssmResult = getQuery().getOnCreditHoldAmt(queryParam);
            
            if (ssmResult.isCodeNormal()) {
                
                List resList = (List) ssmResult.getResultObject();
                Map resMap = (Map) resList.get(0);
                
                bizMsg.inProcAmt_CR.setValue((BigDecimal)resMap.get("HOLD_AMOUNT"));
            }
            
            // Def#1819
            queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", getGlobalCompanyCode());
            queryParam.put("billToCustCd", bizMsg.billToCustCd.getValue());
            queryParam.put("salesDate", ZYPDateUtil.getSalesDate());
            queryParam.put("invTp_Credit", INV_TP.CREDIT_MEMO);
            queryParam.put("trxSrcTp_Return", TRX_SRC_TP.WHOLE_SALES_RETURN);
            queryParam.put("trxSrcTp_Promo", TRX_SRC_TP.PROMOTION);
            queryParam.put("sysSrc_SandR", SYS_SRC.S21_SERVICE_AND_REPAIR);
            queryParam.put("sysSrc_Ross", SYS_SRC.ROSS);
            
            ssmResult = getQuery().getApprovedCredits(queryParam);
            
            if (ssmResult.isCodeNormal()) {
                
                bizMsg.inProcAmt_AC.setValue((BigDecimal) ssmResult.getResultObject());
            }
            
            bizMsg.inProcAmt.setValue(custCrPrflMsgArray.no(0).inProcAmt.getValue());
            bizMsg.crBalAmt.setValue(custCrPrflMsgArray.no(0).crBalAmt.getValue());
            bizMsg.lastInvDt.setValue(custCrPrflMsgArray.no(0).lastInvDt.getValue());
            bizMsg.invDtChkReqFlg.setValue(custCrPrflMsgArray.no(0).invDtChkReqFlg.getValue());
        }
        
        if (ZYPCommonFunc.hasValue(crRiskClsCd)) {
            CR_RISK_CLSTMsg crRiskClsMsg = new CR_RISK_CLSTMsg();
            crRiskClsMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            crRiskClsMsg.crRiskClsCd.setValue(crRiskClsCd);
            crRiskClsMsg = (CR_RISK_CLSTMsg) EZDTBLAccessor.findByKey(crRiskClsMsg);
            if (crRiskClsMsg != null) {
                bizMsg.crRiskClsNm.setValue(crRiskClsMsg.crRiskClsNm.getValue());
            }
        }
        
        // Amount
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("billToCustCd", bizMsg.billToCustCd.getValue());

        S21SsmEZDResult ssmResult = getQuery().getAgingAmt(queryParam);
        
        if (ssmResult.isCodeNormal()) {
            
            List resList = (List) ssmResult.getResultObject();
            Map resMap = (Map) resList.get(0);
            
            bizMsg.func01AgingAmt_01.setValue((BigDecimal)resMap.get("AMT07"));
            bizMsg.func01AgingAmt_02.setValue((BigDecimal)resMap.get("AMT06"));
            bizMsg.func01AgingAmt_03.setValue((BigDecimal)resMap.get("AMT05"));
            bizMsg.func01AgingAmt_04.setValue((BigDecimal)resMap.get("AMT04"));
            bizMsg.func01AgingAmt_05.setValue((BigDecimal)resMap.get("AMT00"));
        }
        
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "doProcess_NWAL0330_INIT================================END", this);
        }
    }

    private static NWAL0330Query getQuery() {
        return NWAL0330Query.getInstance();
        
    }

}
