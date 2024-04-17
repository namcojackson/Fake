package business.blap.NWCL0130.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWCL0130.NWCL0130CMsg;
import business.blap.NWCL0130.NWCL0130SMsg;
import business.blap.NWCL0130.NWCL0130Query;
import business.blap.NWCL0130.NWCL0130_ACMsg;
import business.blap.NWCL0130.constant.NWCL0130Constant;
import business.db.CONSL_BILL_RGNRTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_InvoiceRuleListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_INV_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3006,3008
 * 11/16/2018   Fujitsu         K.Kato          Update          QC#27954
 *</pre>
 */
public class NWCL0130CommonLogic implements NWCL0130Constant {

    public static void transferLastPage(NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg) {
       	//current page # = (Error line # + showable line # - 1) / showable line #
       	int totPage = (sMsg.A.getValidCount() + bizMsg.A.length() - 1) / bizMsg.A.length();
       	//Copy from SMsg to CMsg
       	int s = (totPage - 1) * bizMsg.A.length();
       	int start = s;
       	int e = totPage * bizMsg.A.length() - 1;
		ZYPTableUtil.clear(bizMsg.A);
       	int k = 0;
       	for ( ; s <= e; s++) {
       		if (s < sMsg.A.getValidCount()) {
       			EZDMsg.copy( sMsg.A.no( s ), null, bizMsg.A.no( k ), null );
       			k++;
       		} else {
       			break;
       		}
       	}
       	bizMsg.A.setValidCount( k );
       	
       	//set Page
	    bizMsg.xxPageShowFromNum_10.setValue( start + 1 );
        bizMsg.xxPageShowToNum_10.setValue( bizMsg.A.getValidCount() );
        bizMsg.xxPageShowOfNum_10.setValue( sMsg.A.getValidCount() );
        bizMsg.xxPageShowCurNum_10.setValue( totPage );
        bizMsg.xxPageShowTotNum_10.setValue( totPage );
    
    }
    
    public static boolean deleteDetail(String globalCompanyCode, NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg, int i) {

    	CONSL_BILL_RGNRTMsg detail = new CONSL_BILL_RGNRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.conslBillRgnrPk, sMsg.B.no(i).conslBillRgnrPk_A1);
        detail = (CONSL_BILL_RGNRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(detail);
        if (detail != null) {
            String tmpTimeZone = detail.ezUpTimeZone.getValue();
            String tmpTime = detail.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.B.no(i).ezUpTime_A1.getValue(), sMsg.B.no(i).ezUpTimeZone_A1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"CONSL_BILL_RGNR", "CONSL_BILL_RGNR_PK", String.valueOf(sMsg.B.no(i).conslBillRgnrPk_A1.getValueInt())});
                return false;
            }
        }
        detail = new CONSL_BILL_RGNRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.conslBillRgnrPk, sMsg.B.no(i).conslBillRgnrPk_A1);
        EZDTBLAccessor.logicalRemove(detail);
        if (!RETURN_CD_NORMAL.equals(detail.getReturnCode())) {
        	//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0015E", new String[]{"CONSL_BILL_RGNR", "CONSL_BILL_RGNR_PK", String.valueOf(sMsg.B.no(i).conslBillRgnrPk_A1.getValueInt())});
            return false;
        }
        
        return true;

    }
    
    public static boolean updateDetail(String globalCompanyCode, NWCL0130CMsg bizMsg, int i) {

    	CONSL_BILL_RGNRTMsg detail = new CONSL_BILL_RGNRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.conslBillRgnrPk, bizMsg.A.no(i).conslBillRgnrPk_A1);

        detail = (CONSL_BILL_RGNRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(detail);
        if (detail != null) {
            String tmpTimeZone = detail.ezUpTimeZone.getValue();
            String tmpTime = detail.ezUpTime.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A1.getValue(), bizMsg.A.no(i).ezUpTimeZone_A1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM8175E", new String[]{"CONSL_BILL_RGNR", "CONSL_BILL_RGNR_PK", String.valueOf(bizMsg.A.no(i).conslBillRgnrPk_A1.getValueInt())});
                return false;
            }
        }

        S21FastTBLAccessor.update(detail);
        if (!RETURN_CD_NORMAL.equals(detail.getReturnCode())) {
        	//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "ZZMM0015E", new String[]{"CONSL_BILL_RGNR", "CONSL_BILL_RGNR_PK", String.valueOf(bizMsg.A.no(i).conslBillRgnrPk_A1.getValueInt())});
            return false;

        }
        return true;
        
    }
    	
    public static boolean insertDetail(String globalCompanyCode, NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg, int i) {
    	CONSL_BILL_RGNRTMsg detail = new CONSL_BILL_RGNRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.conslBillRgnrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONSL_BILL_RGNR_SQ));
        ZYPEZDItemValueSetter.setValue(detail.invNum, sMsg.A.no(i).invNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.trgtConslBillPk, new BigDecimal(sMsg.A.no(i).conslBillNum_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(detail.conslRgnrProcCd, CONSL_RGNR_PROC.UN_PROCESSED);
        ZYPEZDItemValueSetter.setValue(detail.conslRgnrActTpCd, sMsg.A.no(i).conslRgnrActTpCd_A1);
        EZDTBLAccessor.create(detail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detail.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "ZZMM0001E", new String[]{"CONSL_BILL_RGNR", "CONSL_BILL_RGNR_PK", String.valueOf(detail.conslBillRgnrPk.getValueInt())});
            return false;
        }
        return true;
        
    }
    
    public static void search(NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg, String globalCompanyCode) {
        
        bizMsg.xxChkBox_AL.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        Integer totCount = 0;
		S21SsmEZDResult rs = NWCL0130Query.getInstance().searchTotCnt(bizMsg, globalCompanyCode);
        if (rs.isCodeNormal()) {
            totCount = (Integer) rs.getResultObject();
        }
        if (totCount > 0) {
	        S21SsmEZDResult result = NWCL0130Query.getInstance().search(bizMsg, sMsg, globalCompanyCode);
	        if (result.isCodeNormal()) {
	            int queryResCnt = result.getQueryResultCount();
	            if (queryResCnt > sMsg.A.length()) {
	                // NMAM8351I=0 First [@] rows are currently displayed. Please use the filter to narrow down the search results.
	                bizMsg.setMessageInfo("NMAM8351I", new String[] {String.valueOf(sMsg.A.length())} );
	                bizMsg.setMessageInfo("NZZM0001W");
	                queryResCnt = sMsg.A.length();
	            }
	            // Copy from SMsg to cMsg
	            int i = 0;
	            
                EZDMsg.copy(sMsg.A, null, sMsg.B, null);
	            sMsg.B.setValidCount(sMsg.A.getValidCount());
                
	            for (; i < queryResCnt; i++) {
	                if (i == bizMsg.A.length()) {
	                    break;
	                }
	                EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);
	            }
	            bizMsg.A.setValidCount(i);
	            // Set page number
	            
	            bizMsg.xxPageShowFromNum_10.setValue(1);
	            bizMsg.xxPageShowToNum_10.setValue(bizMsg.A.getValidCount());
	            bizMsg.xxPageShowOfNum_10.setValue(queryResCnt);
	            //ZZM8100I=0,Process ended normally.
	            bizMsg.setMessageInfo("ZZM8100I");
	        } else {
	            bizMsg.xxPageShowFromNum_10.clear();
	            bizMsg.xxPageShowToNum_10.clear();
	            bizMsg.xxPageShowOfNum_10.clear();
	            //ZZZM9005W=0,No search results found.
	            bizMsg.setMessageInfo("ZZZM9005W");
	        }
        } else {
            bizMsg.xxPageShowFromNum_10.clear();
            bizMsg.xxPageShowToNum_10.clear();
            bizMsg.xxPageShowOfNum_10.clear();
        	//ZZZM9005W=0,No search results found.
        	bizMsg.setMessageInfo("ZZZM9005W");
        }
    }
    
    public static String getFlgValueFromFields(String flg) {
    	
    	return !ZYPCommonFunc.hasValue(flg) ? FLG_OFF_N : flg;    	
    	
    }
    
    public static String getDuplicatedKeyString(NWCL0130_ACMsg sMsg) {
    	String key = "";
    	//key = sMsg.conslRgnrActTpNm_A1.getValue() + sMsg.invNum_A1.getValue() + sMsg.conslBillNum_A1.getValue();
    	key = sMsg.invNum_A1.getValue();
        return key;
    }
    
    public static void copyFromCMsgToSMsg(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg, boolean flg) {
    	int fromNum = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no( i ) , null, sMsg.A.no( fromNum + i ), null );
    	}
        if (flg) {
    		ZYPTableUtil.clear(cMsg.A);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static boolean checAddDropTransmit(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg, String glblCmpyCd, String conslRgnrActTpCd) {
    	
    	//Table Count = Limit Detail Count
        if (cMsg.A.getValidCount() == sMsg.A.length()) {
        	//NMAM0050E=0,Details cannot be added because the number will exceed [@].
            cMsg.setMessageInfo("NMAM0050E", new String[] { String.valueOf(sMsg.A.length()) });
			return true;
		}

        //Bill# Exists Check
        S21SsmEZDResult result = NWCL0130Query.getInstance().getConslBill(glblCmpyCd, cMsg.conslBillNum_H1.getValue());
        if (!result.isCodeNormal()) {
        	//NMAM0163E=0,The entered [@] does not exist in [@].
            cMsg.conslBillNum_H1.setErrorInfo(1, "NMAM0163E", new String[] { cMsg.conslBillNum_H1.getValue(), "CONSL_BILL" });
			return true;
        }
        Map<String, Object> conslBillMap = (Map<String, Object>) result.getResultObject();
        if (conslBillMap == null || conslBillMap.get("CONSL_BILL_NUM") == null) {
        	//NMAM0163E=0,The entered [@] does not exist in [@].
            cMsg.invNum_H1.setErrorInfo(1, "NMAM0163E", new String[] { cMsg.conslBillNum_H1.getValue(), "CONSL_BILL" });
			return true;
        }
        
        //Inv# Exists Check
        result = NWCL0130Query.getInstance().getInvInfo(glblCmpyCd, cMsg.invNum_H1.getValue());
        if (!result.isCodeNormal()) {
        	//NMAM0163E=0,The entered [@] does not exist in [@].
            cMsg.invNum_H1.setErrorInfo(1, "NMAM0163E", new String[] { cMsg.invNum_H1.getValue(), "INV" });
			return true;
        }
        Map<String, Object> invMap = (Map<String, Object>) result.getResultObject();
        if (invMap == null || invMap.get("INV_NUM") == null) {
        	//NMAM0163E=0,The entered [@] does not exist in [@].
            cMsg.invNum_H1.setErrorInfo(1, "NMAM0163E", new String[] { cMsg.invNum_H1.getValue(), "INV" });
			return true;
        }

        //Inv# Should be Special Billing
        result = NWCL0130Query.getInstance().getInvInfoSpecialBill(glblCmpyCd, cMsg.invNum_H1.getValue());
        if (!result.isCodeNormal()) {
        	//NWCM0123E=0,The entered [@] does not Special Billing Invoice.
            cMsg.invNum_H1.setErrorInfo(1, "NWCM0123E", new String[] { cMsg.invNum_H1.getValue() });
			return true;
        }
        
        //Bill# Should be processed Special Billing
        result = NWCL0130Query.getInstance().getProcessedBillInfoSpecialBill(glblCmpyCd, cMsg.conslBillNum_H1.getValue());
        if (!result.isCodeNormal()) {
        	//NWCM0153E=0,The entered [@] is not processed Special Billing.
            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0153E", new String[] { cMsg.conslBillNum_H1.getValue() });
			return true;
        }
        
        //Inv# Should be processed Special Billing
        result = NWCL0130Query.getInstance().getProcessedInvInfoSpecialBill(glblCmpyCd, cMsg.invNum_H1.getValue(), conslRgnrActTpCd);
        if (!result.isCodeNormal()) {
        	//NWCM0154E=0,The entered [@] is not processed Special Billing Invoice.
            cMsg.invNum_H1.setErrorInfo(1, "NWCM0154E", new String[] { cMsg.invNum_H1.getValue() });
			return true;
        }
        
        if (CONSL_RGNR_ACT_TP.ADD.equals(conslRgnrActTpCd)) {
	        //Bill# - Invoice# relation check
	        result = NWCL0130Query.getInstance().getBillInvReln(glblCmpyCd, cMsg.conslBillNum_H1.getValue(), cMsg.invNum_H1.getValue());
	        if (result.isCodeNormal()) {
	        	//NWCM0119E=0,The entered [@] already exists in [@].
	            cMsg.invNum_H1.setErrorInfo(1, "NWCM0119E", new String[] { cMsg.invNum_H1.getValue(), cMsg.conslBillNum_H1.getValue() });
	            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0119E", new String[] { cMsg.invNum_H1.getValue(), cMsg.conslBillNum_H1.getValue() });
				return true;
	        }
        } else {
	        //Bill# - Invoice# relation check
	        result = NWCL0130Query.getInstance().getBillInvReln(glblCmpyCd, cMsg.conslBillNum_H1.getValue(), cMsg.invNum_H1.getValue());
	        if (!result.isCodeNormal()) {
	        	//NWCM0119E=0,The entered [@] already exists in [@].
	            cMsg.invNum_H1.setErrorInfo(1, "NWCM0119E", new String[] { cMsg.invNum_H1.getValue(), cMsg.conslBillNum_H1.getValue() });
	            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0119E", new String[] { cMsg.invNum_H1.getValue(), cMsg.conslBillNum_H1.getValue() });
				return true;
	        }
        }
        
        if (CONSL_RGNR_ACT_TP.ADD.equals(conslRgnrActTpCd)) {
	        //if Invoice# is from easy pack order, Bill# should be easy pack Bill#.
	        result = NWCL0130Query.getInstance().getEasyPackInvoice(glblCmpyCd, cMsg.invNum_H1.getValue());
	        if (result.isCodeNormal()) {
	        	//Add to Bill# should be easy pack Bill#.
	        	result = NWCL0130Query.getInstance().getEasyPackBill(glblCmpyCd, cMsg.conslBillNum_H1.getValue());
	            if (!result.isCodeNormal()) {
	            	//NWCM0121E=0,Bill#{@} should be Easy Pack. (Invoice{@} is Easy Pack).
	                cMsg.invNum_H1.setErrorInfo(1, "NWCM0121E", new String[] { cMsg.conslBillNum_H1.getValue(), cMsg.invNum_H1.getValue()  });
	                cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0121E", new String[] { cMsg.conslBillNum_H1.getValue(), cMsg.invNum_H1.getValue() });
	    			return true;
	            }
	        } else {
	        	//Add to Bill# should not be easy pack Bill#.
	        	result = NWCL0130Query.getInstance().getEasyPackBill(glblCmpyCd, cMsg.conslBillNum_H1.getValue());
	            if (result.isCodeNormal()) {
	            	//NWCM0122E=0,Bill#{@} should not be Easy Pack. (Invoice{@} is not Easy Pack).
	                cMsg.invNum_H1.setErrorInfo(1, "NWCM0122E", new String[] { cMsg.conslBillNum_H1.getValue(), cMsg.invNum_H1.getValue() });
	                cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0122E", new String[] { cMsg.conslBillNum_H1.getValue(), cMsg.invNum_H1.getValue() });
	    			return true;
	            }
	        }
	        
	        //Bill# - Bill To Customer Code = Invoice# - Bill To Customer Code
	        result = NWCL0130Query.getInstance().getBillInvBillToCust(glblCmpyCd, cMsg.conslBillNum_H1.getValue(), cMsg.invNum_H1.getValue());
	        if (!result.isCodeNormal()) {
	        	//NWCM0120E=0,The entered [@] should be same Bill To Customer Code with [@].
	            cMsg.invNum_H1.setErrorInfo(1, "NWCM0120E", new String[] { cMsg.invNum_H1.getValue(), cMsg.conslBillNum_H1.getValue() });
	            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0120E", new String[] { cMsg.invNum_H1.getValue(), cMsg.conslBillNum_H1.getValue() });
				return true;
	        }
	        
	        
	        //Same Source - Group Code or Same Group#
	        NMZC610001PMsg pMsg = new NMZC610001PMsg();
	        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd,     glblCmpyCd);
	        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd,       NMZC610001Constant.PROCESS_MODE_INVOICE);
	        if (DS_BIZ_AREA.EQUIPMENT.equals((String) invMap.get("DS_BIZ_AREA_CD"))) {
	            ZYPEZDItemValueSetter.setValue(pMsg.custInvSrcCd,  CUST_INV_SRC.EQUIPMENT);
	        } else if (DS_BIZ_AREA.SUPPLIES.equals((String) invMap.get("DS_BIZ_AREA_CD"))) {
	            ZYPEZDItemValueSetter.setValue(pMsg.custInvSrcCd,  CUST_INV_SRC.SUPPLIES);
	        } else if (DS_BIZ_AREA.CONTRACTS.equals((String) invMap.get("DS_BIZ_AREA_CD"))) {
	            ZYPEZDItemValueSetter.setValue(pMsg.custInvSrcCd,  CUST_INV_SRC.SERVICE_CONTRACT);
	        } else if (DS_BIZ_AREA.FIELD_SERVICE.equals((String) invMap.get("DS_BIZ_AREA_CD"))) {
	            ZYPEZDItemValueSetter.setValue(pMsg.custInvSrcCd,  CUST_INV_SRC.SERVICE_CALL);
            // 2018/11/16 S21_NA#27954 Add Start
	        } else if (DS_BIZ_AREA.PARTS.equals((String) invMap.get("DS_BIZ_AREA_CD"))) {
	            ZYPEZDItemValueSetter.setValue(pMsg.custInvSrcCd,  CUST_INV_SRC.PARTS);
            // 2018/11/16 S21_NA#27954 Add End
	        }
	        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd,   (String) invMap.get("BILL_TO_CUST_CD"));

	        NMZC610001 customerInfoGetApi = new NMZC610001();
	        customerInfoGetApi.execute(pMsg, ONBATCH_TYPE.BATCH);
	        if (pMsg.xxMsgIdList.getValidCount() > 0) {
	            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
	                String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
	                S21InfoLogOutput.println(msgId);
		            cMsg.invNum_H1.setErrorInfo(1, msgId);
		            cMsg.conslBillNum_H1.setErrorInfo(1, msgId);
					return true;
	            }
	        }

	        if (pMsg.InvoiceRuleList.getValidCount() == 0) {
	            String msgId = "NWCM0115E";
	            cMsg.invNum_H1.setErrorInfo(1, msgId, new String[]{"Invoice Rule"});
	            cMsg.conslBillNum_H1.setErrorInfo(1, msgId, new String[]{"Invoice Rule"});
				return true;
	        }
            NMZC610001_InvoiceRuleListPMsg invoiceRule = pMsg.InvoiceRuleList.no(0);
            String conslCustInvSrcCd = (String) conslBillMap.get("CUST_INV_SRC_CD");
            String conslInvGrpNum = (String) conslBillMap.get("INV_GRP_NUM");
            if (ZYPCommonFunc.hasValue(conslInvGrpNum)) {
            	if (!conslInvGrpNum.equals(invoiceRule.invGrpNum.getValue())) {
    	        	//NWCM0147E=0,Consolidated Bill's Group#{@} is different from Invoice's Group#{@}.
    	            cMsg.invNum_H1.setErrorInfo(1, "NWCM0147E", new String[] { conslInvGrpNum, invoiceRule.invGrpNum.getValue() });
    	            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0147E", new String[] { conslInvGrpNum, invoiceRule.invGrpNum.getValue() });
            		return true;
            	}
                result = NWCL0130Query.getInstance().getConslBillGrp(glblCmpyCd, cMsg.conslBillNum_H1.getValue());
                if (result.isCodeNormal()) {
                	List<Map<String, Object>> conslBillGrpList = (List<Map<String, Object>>) result.getResultObject();
                	if (conslBillGrpList != null && conslBillGrpList.size() > 0) {
                		for (Map<String, Object> conslBillGrpMap : conslBillGrpList) {
                            String defInvGrpCd = (String) conslBillGrpMap.get("DEF_INV_GRP_CD");
                            String defInvGrpTxt = (String) conslBillGrpMap.get("DEF_INV_GRP_TXT") == null ? "" : (String) conslBillGrpMap.get("DEF_INV_GRP_TXT");
                            String grpValTxt = "";
                            if (DEF_INV_GRP.PO_NUM.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("CUST_ISS_PO_NUM") == null ? "" : (String) invMap.get("CUST_ISS_PO_NUM");
                            } else if (DEF_INV_GRP.CONTRACT_NUM.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("DS_CONTR_NUM") == null ? "" : (String) invMap.get("DS_CONTR_NUM");
                            } else if (DEF_INV_GRP.BILLING_PERIOD.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("BLLG_PER") == null ? "" : (String) invMap.get("BLLG_PER");
                            } else if (DEF_INV_GRP.SERIAL_NUM.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("SER_NUM") == null ? "" : (String) invMap.get("SER_NUM");
                            } else if (DEF_INV_GRP.MODEL.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("MDL_NM") == null ? "" : (String) invMap.get("MDL_NM");
                            }
                            if (!grpValTxt.equals(defInvGrpTxt)) {
                	        	//NWCM0148E=0,Consolidated Bill's Group Code Value{@=@} is different from Invoice's Group Code Value{@=@}.
                            	String defInvGrpNm = "";
                            	if (DEF_INV_GRP.PO_NUM.equals(defInvGrpCd)) {
                            		defInvGrpNm = "PO#";
                            	} else if (DEF_INV_GRP.CONTRACT_NUM.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Contract#";
                            	} else if (DEF_INV_GRP.BILLING_PERIOD.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Billing Period";
                            	} else if (DEF_INV_GRP.SERIAL_NUM.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Serial#";
                            	} else if (DEF_INV_GRP.MODEL.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Model";
                            	}
                	            cMsg.invNum_H1.setErrorInfo(1, "NWCM0148E", new String[] { defInvGrpNm, grpValTxt, defInvGrpNm, defInvGrpTxt });
                	            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0148E", new String[] { defInvGrpNm, grpValTxt, defInvGrpNm, defInvGrpTxt });
                                return true;
                            }
                		}
                	}
                }
            } else {
                if (!pMsg.custInvSrcCd.getValue().equals(conslCustInvSrcCd)) {
    	        	//NWCM0149E=0,Consolidated Bill's Source Code{@} is different from Invoice's Source Code{@}.
    	            cMsg.invNum_H1.setErrorInfo(1, "NWCM0149E", new String[] { conslCustInvSrcCd, pMsg.custInvSrcCd.getValue() });
    	            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0149E", new String[] { conslCustInvSrcCd, pMsg.custInvSrcCd.getValue() });
                    return true;
                }
                result = NWCL0130Query.getInstance().getConslBillGrp(glblCmpyCd, cMsg.conslBillNum_H1.getValue());
                if (result.isCodeNormal()) {
                	List<Map<String, Object>> conslBillGrpList = (List<Map<String, Object>>) result.getResultObject();
                	if (conslBillGrpList != null && conslBillGrpList.size() > 0) {
                		for (Map<String, Object> conslBillGrpMap : conslBillGrpList) {
                            String defInvGrpCd = (String) conslBillGrpMap.get("DEF_INV_GRP_CD");
                            if (!ZYPCommonFunc.hasValue(defInvGrpCd)) {
                                continue;
                            }
                            String defInvGrpTxt = (String) conslBillGrpMap.get("DEF_INV_GRP_TXT") == null ? "" : (String) conslBillGrpMap.get("DEF_INV_GRP_TXT");
                            String grpValTxt = "";
                            if (DEF_INV_GRP.PO_NUM.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("CUST_ISS_PO_NUM") == null ? "" : (String) invMap.get("CUST_ISS_PO_NUM");
                            } else if (DEF_INV_GRP.CONTRACT_NUM.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("DS_CONTR_NUM") == null ? "" : (String) invMap.get("DS_CONTR_NUM");
                            } else if (DEF_INV_GRP.BILLING_PERIOD.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("BLLG_PER") == null ? "" : (String) invMap.get("BLLG_PER");
                            } else if (DEF_INV_GRP.SERIAL_NUM.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("SER_NUM") == null ? "" : (String) invMap.get("SER_NUM");
                            } else if (DEF_INV_GRP.MODEL.equals(defInvGrpCd)) {
                                grpValTxt = (String) invMap.get("MDL_NM") == null ? "" : (String) invMap.get("MDL_NM");
                            }
                            if (!grpValTxt.equals(defInvGrpTxt)) {
                	        	//NWCM0148E=0,Consolidated Bill's Group Code Value{@=@} is different from Invoice's Group Code Value{@=@}.
                            	String defInvGrpNm = "";
                            	if (DEF_INV_GRP.PO_NUM.equals(defInvGrpCd)) {
                            		defInvGrpNm = "PO#";
                            	} else if (DEF_INV_GRP.CONTRACT_NUM.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Contract#";
                            	} else if (DEF_INV_GRP.BILLING_PERIOD.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Billing Period";
                            	} else if (DEF_INV_GRP.SERIAL_NUM.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Serial#";
                            	} else if (DEF_INV_GRP.MODEL.equals(defInvGrpCd)) {
                            		defInvGrpNm = "Model";
                            	}
                	            cMsg.invNum_H1.setErrorInfo(1, "NWCM0148E", new String[] { defInvGrpNm, grpValTxt, defInvGrpNm, defInvGrpTxt });
                	            cMsg.conslBillNum_H1.setErrorInfo(1, "NWCM0148E", new String[] { defInvGrpNm, grpValTxt, defInvGrpNm, defInvGrpTxt });
                                return true;
                            }
                		}
                	}
                }
            }
        }
        
        String entInvNum = cMsg.invNum_H1.getValue();
        //Same Inv#
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
        	String invNumOnTbl = sMsg.A.no(i).invNum_A1.getValue();
        	if (entInvNum.equals(invNumOnTbl)) {
        		//NMAM8344E=0,Can't add the line because duplicated @ Inv#{@}. Please delete the line.
                cMsg.invNum_H1.setErrorInfo(1, "NMAM8344E", new String[] { "", cMsg.invNum_H1.getValue() });
    			return true;
        	}
        }
    	
    	
    	return false;
    }
}
