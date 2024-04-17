package business.blap.NMAL7120;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7120.NMAL7120CMsg;
import business.blap.NMAL7120.common.NMAL7120CommonLogic;
import business.blap.NMAL7120.constant.NMAL7120Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3006,3008,3284
 * 07/20/2016   FUJITSU         W.Honda         Update          #9690
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 *</pre>
 */
public class NMAL7120BL06 extends S21BusinessHandler implements NMAL7120Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
        NMAL7120CMsg bizMsg = (NMAL7120CMsg) cMsg;
        String scrnAplID = bizMsg.getScreenAplID();
        try {
    		if ("NMAL7120Scrn00_CMN_Submit".equals(scrnAplID)) {
            	NMAL7120Scrn00_CMN_Submit(bizMsg, (NMAL7120SMsg) sMsg);
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
    private void NMAL7120Scrn00_CMN_Submit(NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg) {
        
        try{
        NMAL7120CommonLogic.updateSMsg(bizMsg, sMsg); //S21_NA ADD QC#20206(Sol#269)

    	// #####################
    	// Input Check
    	// #####################
    	// Header
        boolean hasError = checkInput_Header(bizMsg);
    	if (hasError) {
    		return;
    	}
    	hasError = checkInput_Detail(bizMsg, sMsg);
    	if (hasError) {
    		return;
    	}
    	
    	// #####################
        // Update Delete Insert
        // #####################
        // S21_NA MOD START QC#20206(Sol#269)
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!NMAL7120CommonLogic.deleteDetail(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
                return;
            }
        }
        
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).csmpContrPk_A1.getValue())) {
                if(!NMAL7120CommonLogic.insertDetail(getGlobalCompanyCode(), bizMsg, sMsg, i)){
                    return;
                }
            } else {
                if (isChangeLine(sMsg.A.no(i), sMsg.C.no(i))) {
                    if(!NMAL7120CommonLogic.updateDetail(getGlobalCompanyCode(), bizMsg, sMsg, i)){
                        return;
                    }
                }
            }
        }
    	// Detail
//   		for (int i = 0; i < sMsg.B.getValidCount(); i++) {
//   			boolean isFound = false;
//            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
//                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).csmpContrPk_A1) && sMsg.B.no(i).csmpContrPk_B1.getValue().equals(bizMsg.A.no(j).csmpContrPk_A1.getValue())) {
//   		        	isFound = true;
//   		        	
//                    if (isChangeLine(bizMsg.A.no(j), sMsg.B.no(i))) {
//                        // update.
//   		        		NMAL7120CommonLogic.updateDetail(getGlobalCompanyCode(), bizMsg, j);
//   		            }
//   		        }
//   		    }
//   		    if (isFound == false) {
//   		    	if(!NMAL7120CommonLogic.deleteDetail(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
//   		    	    return;
//   		    	}
//   		    }
//   		}
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).csmpContrPk_A1.getValue())) {
//                if (!NMAL7120CommonLogic.insertDetail(getGlobalCompanyCode(), bizMsg, i)) {
//   		    	    return;
//	            }
//	        }
//		}
        // S21_NA MOD END QC#20206(Sol#269)
   		
        //Search
        NMAL7120CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());
        
        //NMAM8182I=0,[@] process ended normally.
		bizMsg.setMessageInfo("NMAM8182I", new String[]{"CSMP Update"});
		
        }finally{
            NMAL7120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sMsg, sMsg.A); // S21_NA ADD QC#20206(Sol#269)
        }
   		
    }
    private boolean isChangeLine(NMAL7120_ASMsg A, NMAL7120_CSMsg C){

       	String cfDsAcctNum = NMAL7120CommonLogic.nvlString(C.dsAcctNum_C1.getValue());
       	String cfCsmpNum = NMAL7120CommonLogic.nvlString(C.csmpNum_C1.getValue());
       	String cfDlrRefNum = NMAL7120CommonLogic.nvlString(C.dlrRefNum_C1.getValue());
       	String cfPrcCatgNm = NMAL7120CommonLogic.nvlString(C.prcCatgNm_C1.getValue());
       	String cfPrcContrNum = NMAL7120CommonLogic.nvlString(C.prcContrNum_C1.getValue());
       	String cfOrigCoaBrCd = NMAL7120CommonLogic.nvlString(C.origCoaBrCd_C1.getValue());
       	String cfRtlDlrCd = NMAL7120CommonLogic.nvlString(C.rtlDlrCd_C1.getValue());
       	String cfEffFromDt = NMAL7120CommonLogic.nvlString(C.effFromDt_C1.getValue());
       	String cfEffThruDt = NMAL7120CommonLogic.nvlString(C.effThruDt_C1.getValue());
       	String cfCusaRejDt = NMAL7120CommonLogic.nvlString(C.cusaRejDt_C1.getValue());
       	String cfErlTrmnDt = NMAL7120CommonLogic.nvlString(C.erlTrmnDt_C1.getValue());
       	String cfRnwCsmpNum = NMAL7120CommonLogic.nvlString(C.rnwCsmpNum_C1.getValue());
       	BigDecimal cfUplftEquipRatio = NMAL7120CommonLogic.nvlBigDecimal(C.uplftEquipRatio_C1.getValue());
       	BigDecimal cfUplftAsryRatio = NMAL7120CommonLogic.nvlBigDecimal(C.uplftAsryRatio_C1.getValue());
       	String cfCsmpNumCmntTxt = NMAL7120CommonLogic.nvlString(C.csmpNumCmntTxt_C1.getValue());
       	String cfCsmpContrActvFlg = NMAL7120CommonLogic.nvlString(C.csmpContrActvFlg_C1.getValue());
       	
       	String afDsAcctNum = NMAL7120CommonLogic.nvlString(A.dsAcctNum_A1.getValue());
       	String afCsmpNum = NMAL7120CommonLogic.nvlString(A.csmpNum_A1.getValue());
       	String afDlrRefNum = NMAL7120CommonLogic.nvlString(A.dlrRefNum_A1.getValue());
       	String afPrcCatgNm = NMAL7120CommonLogic.nvlString(A.prcCatgNm_A1.getValue());
       	String afPrcContrNum = NMAL7120CommonLogic.nvlString(A.prcContrNum_A1.getValue());
       	String afOrigCoaBrCd = NMAL7120CommonLogic.nvlString(A.origCoaBrCd_A1.getValue());
       	String afRtlDlrCd = NMAL7120CommonLogic.nvlString(A.rtlDlrCd_A1.getValue());
       	String afEffFromDt = NMAL7120CommonLogic.nvlString(A.effFromDt_A1.getValue());
       	String afEffThruDt = NMAL7120CommonLogic.nvlString(A.effThruDt_A1.getValue());
       	String afCusaRejDt = NMAL7120CommonLogic.nvlString(A.cusaRejDt_A1.getValue());
       	String afErlTrmnDt = NMAL7120CommonLogic.nvlString(A.erlTrmnDt_A1.getValue());
       	String afRnwCsmpNum = NMAL7120CommonLogic.nvlString(A.rnwCsmpNum_A1.getValue());
       	BigDecimal afUplftEquipRatio = NMAL7120CommonLogic.nvlBigDecimal(A.uplftEquipRatio_A1.getValue());
       	BigDecimal afUplftAsryRatio = NMAL7120CommonLogic.nvlBigDecimal(A.uplftAsryRatio_A1.getValue());
       	String afCsmpNumCmntTxt = NMAL7120CommonLogic.nvlString(A.csmpNumCmntTxt_A1.getValue());
        // 2016/07/20 CSA-QC#9690 Add Start
        String custMbrId = NMAL7120CommonLogic.nvlString(A.custMbrId_A1.getValue());
        // 2016/07/20 CSA-QC#9690 Add end
       	String afCsmpContrActvFlg = NMAL7120CommonLogic.nvlFlg(A.csmpContrActvFlg_A1.getValue());
       	
       	if (!cfDsAcctNum.equals(afDsAcctNum) 
       			|| !cfCsmpNum.equals(afCsmpNum) 
       			|| !cfDlrRefNum.equals(afDlrRefNum) 
       			|| !cfPrcCatgNm.equals(afPrcCatgNm) 
       			|| !cfPrcContrNum.equals(afPrcContrNum) 
       			|| !cfOrigCoaBrCd.equals(afOrigCoaBrCd) 
       			|| !cfRtlDlrCd.equals(afRtlDlrCd) 
       			|| !cfEffFromDt.equals(afEffFromDt) 
       			|| !cfEffThruDt.equals(afEffThruDt) 
       			|| !cfCusaRejDt.equals(afCusaRejDt) 
       			|| !cfErlTrmnDt.equals(afErlTrmnDt) 
       			|| !cfRnwCsmpNum.equals(afRnwCsmpNum) 
       			|| cfUplftEquipRatio.compareTo(afUplftEquipRatio) != 0 
       			|| cfUplftAsryRatio.compareTo(afUplftAsryRatio) != 0
       			|| !cfCsmpNumCmntTxt.equals(afCsmpNumCmntTxt) 
       	        // 2016/07/20 CSA-QC#9690 Add Start
                || !cfCsmpNumCmntTxt.equals(custMbrId) 
                // 2016/07/20 CSA-QC#9690 Add end
       			|| !cfCsmpContrActvFlg.equals(afCsmpContrActvFlg) 
       			) {
        	return true;
       	}
    	return false;
    }
    
    private boolean checkInput_Header(NMAL7120CMsg bizMsg) {
    	
        boolean errorFlg = false;
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean checkInput_Detail(NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg) {
     // S21_NA MOD bizMsg→sMsg QC#20206(Sol#269)
        boolean validationFlg = false;
        
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            
        	boolean isLineChangeFlg = false;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).csmpContrPk_A1)) { 
             // S21_NA MOD END QC#20206(Sol#269)
                if (isChangeLine(sMsg.A.no(i), sMsg.C.no(i))) {
                    isLineChangeFlg = true;
                }
//                for (int j = 0; j < sMsg.B.getValidCount(); j++) {
//                    if (sMsg.B.no(j).csmpContrPk_B1.getValue().equals(bizMsg.A.no(i).csmpContrPk_A1.getValue())) {
//                        if (isChangeLine(bizMsg.A.no(i), sMsg.B.no(j))) {
//                            isLineChangeFlg = true;
//                            break;
//                        }
//                    }
//                }
             // S21_NA MOD END QC#20206(Sol#269)
            } else {
                isLineChangeFlg = true;
            }
        	
        	if (!isLineChangeFlg) {
        		continue;
        	}
        	//Exists Check Account Number
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).dsAcctNum_A1)) { 
                S21SsmEZDResult result = NMAL7120Query.getInstance().getDsAcctCust(getGlobalCompanyCode(), sMsg.A.no(i).dsAcctNum_A1.getValue()); 
                
	    		if (result.isCodeNormal()) {
	    			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
	    			if (list == null || list.size() <= 0) {
	    				// NMAM0163E=0,The entered [@] does not exist in [@].
	    			    sMsg.A.no(i).dsAcctNum_A1.setErrorInfo(1, "NMAM0163E", new String[]{"Account Number", "Account Master"});
	    			    validationFlg = true;
	    			}
	    			if (list.get(0).get("DS_ACCT_NM") != null) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsAcctNm_A1, (String) list.get(0).get("DS_ACCT_NM")); 
	    			}
	    		} else {
	    			// NMAM0163E=0,The entered [@] does not exist in [@].
                    sMsg.A.no(i).dsAcctNum_A1.setErrorInfo(1, "NMAM0163E", new String[] {"Account Number", "Account Master" }); 
                    validationFlg = true;
	    		}
        	} else {
        		//ZZM9000E=0,[@] field is mandatory.
        	    sMsg.A.no(i).dsAcctNum_A1.setErrorInfo(1, "ZZM9000E", new String[]{"Account Number"}); 
                validationFlg = true;
        	}
        	
        	//Exists Check CSMP Number
        	//Exists Check CSA Number
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).csmpNum_A1) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).dlrRefNum_A1)) { 
        		//ZZM9000E=0,[@] field is mandatory.
                sMsg.A.no(i).csmpNum_A1.setErrorInfo(1, "ZZM9000E", new String[]{"CSMP Number or CSA Number"});
                sMsg.A.no(i).dlrRefNum_A1.setErrorInfo(1, "ZZM9000E", new String[]{"CSMP Number or CSA Number"});
                validationFlg = true;
        	}
        	
        	//Exists Check CSMP Level(Price List)
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prcCatgNm_A1)) {
                S21SsmEZDResult result = NMAL7120Query.getInstance().getPrcCatg(getGlobalCompanyCode(), sMsg.A.no(i).prcCatgNm_A1.getValue());
                
	    		if (result.isCodeNormal()) {
	    			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
	    			if (list == null || list.size() <= 0) {
	    				// NMAM0163E=0,The entered [@] does not exist in [@].
	    			    sMsg.A.no(i).prcCatgNm_A1.setErrorInfo(1, "NMAM0163E", new String[]{"CSMP Level", "Price List"});
                        validationFlg = true;
	    			}
	    			if (list.get(0).get("PRC_CATG_CD") != null) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prcCatgCd_A1, (String) list.get(0).get("PRC_CATG_CD"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prcCatgNm_A1, (String) list.get(0).get("PRC_CATG_NM"));
	    			}
	    		} else {
	    			// NMAM0163E=0,The entered [@] does not exist in [@].
	    		    sMsg.A.no(i).prcCatgNm_A1.setErrorInfo(1, "NMAM0163E", new String[]{"CSMP Level", "Price List"});
                    validationFlg = true;
	    		}
        	}
        	
            //S21_NA DEL START QC#20206(Sol#269)
        	//Exists Check Contract Number
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcContrNum_A1)) {
//                S21SsmEZDResult result = NMAL7120Query.getInstance().getPrcContract(getGlobalCompanyCode(), bizMsg.A.no(i).prcContrNum_A1.getValue());
//	    		if (result.isCodeNormal()) {
//	    			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//	    			if (list == null || list.size() <= 0) {
//	    				// NMAM0163E=0,The entered [@] does not exist in [@].
//                        bizMsg.A.no(i).prcContrNum_A1.setErrorInfo(1, "NMAM0163E", new String[]{"Contract Number", "Price Contract Master"});
//                        validationFlg = true;
//	    			}
//	    		} else {
//	    			// NMAM0163E=0,The entered [@] does not exist in [@].
//                    bizMsg.A.no(i).prcContrNum_A1.setErrorInfo(1, "NMAM0163E", new String[]{"Contract Number", "Price Contract Master"});
//                    validationFlg = true;
//	    		}
//        	}
          //S21_NA DEL END QC#20206(Sol#269)
        	
        	//Exists Check Originating GL Branch Code
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).origCoaBrCd_A1)) {
                S21SsmEZDResult result = NMAL7120Query.getInstance().getCoaBr(getGlobalCompanyCode(), sMsg.A.no(i).origCoaBrCd_A1.getValue());
	    		if (result.isCodeNormal()) {
	    			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
	    			if (list == null || list.size() <= 0) {
	    				// NMAM0163E=0,The entered [@] does not exist in [@].
	    			    sMsg.A.no(i).origCoaBrCd_A1.setErrorInfo(1, "NMAM0163E", new String[]{"Originating GL Branch Code", "COA Branch Master"});
                        validationFlg = true;
	    			}
	    		} else {
	    			// NMAM0163E=0,The entered [@] does not exist in [@].
	    		    sMsg.A.no(i).origCoaBrCd_A1.setErrorInfo(1, "NMAM0163E", new String[]{"Originating GL Branch Code", "COA Branch Master"});
                    validationFlg = true;
	    		}
        	}
        	
        	//Exists Check Origin Dealer Code
        	
        	//Effective Date
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
                //ZZM9000E=0,[@] field is mandatory.
                sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, "ZZM9000E", new String[]{"Effective Date"});
                validationFlg = true;
        	}
        	//Expiration Date
        	//From - Thru Check\
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {
                if (ZYPDateUtil.compare(sMsg.A.no(i).effFromDt_A1.getValue(), sMsg.A.no(i).effThruDt_A1.getValue()) >= 0) {
            		//NMAM8213E=0,Effective date is out of range. Please check the Effective date.
                    sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, "NMAM8213E");
                    sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, "NMAM8213E");
                    validationFlg = true;
        	    }
        	}
        }
        
        //Create Target Delete List
        List<BigDecimal> targetDeleteList = new java.util.ArrayList<BigDecimal>();
   		for (int i = 0; i < sMsg.B.getValidCount(); i++) {
   		    targetDeleteList.add(sMsg.B.no(i).csmpContrPk_B1.getValue());
//   			boolean isFound = false;
//            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
//                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).csmpContrPk_A1) && sMsg.B.no(i).csmpContrPk_B1.getValue().equals(bizMsg.A.no(j).csmpContrPk_A1.getValue())) {
//   		        	isFound = true;
//   		        	break;
//   		        }
//   		    }
//   		    if (isFound == false) {
//   		    	//add to delete list
//   		    	targetDeleteList.add(sMsg.B.no(i).csmpContrPk_B1.getValue());
//   		    }
   		}
        
        //Duplicated Check
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String key = NMAL7120CommonLogic.getDuplicatedKeyString(sMsg, i);
            String keyWithoutDate = NMAL7120CommonLogic.getDuplicatedKeyStringWithoutDate(sMsg, i);
            
            //exclude line showing on screen.
            List<BigDecimal> excludeScreenMsgList = new java.util.ArrayList<BigDecimal>();
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                String keyWithoutDate2 = NMAL7120CommonLogic.getDuplicatedKeyStringWithoutDate(sMsg, j);
                BigDecimal pk = sMsg.A.no(j).csmpContrPk_A1.getValue();
                if (keyWithoutDate.equals(keyWithoutDate2) && pk != null) {
                	excludeScreenMsgList.add(pk);
                }
            }
            
            //DB duplicated check without screen.
            Map<String, Object> map = NMAL7120CommonLogic.getDBDuplicated(sMsg, i, getGlobalCompanyCode(), targetDeleteList, excludeScreenMsgList);
            if (map != null && map.get("CSMP_CONTR_PK") != null) {
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Line"});
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Line"});
                validationFlg = true;
                break;
            }
            
    		String fromDate = sMsg.A.no(i).effFromDt_A1.getValue();
    		String thruDate = sMsg.A.no(i).effThruDt_A1.getValue();
    		if (!ZYPCommonFunc.hasValue(thruDate)) thruDate = "99991231";
            //Duplicated Check on SMsg
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                String key2 = NMAL7120CommonLogic.getDuplicatedKeyString(sMsg, j);
                //Key duplicated check
            	if (key.equals(key2) && i != j) {
	            	//NMAM0072E=0, @  is duplicated.
            	    sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Line"});
            	    sMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Line"});
                    validationFlg = true;
                    break;
            	}
            	//Date duplicated check
                String keyWithoutDate2 = NMAL7120CommonLogic.getDuplicatedKeyStringWithoutDate(sMsg, j);
            	if (keyWithoutDate.equals(keyWithoutDate2) && i != j) {
            		String fromDate2 = sMsg.A.no(j).effFromDt_A1.getValue();
            		String thruDate2 = sMsg.A.no(j).effThruDt_A1.getValue();
            		if (!ZYPCommonFunc.hasValue(thruDate2)) thruDate2 = "99991231";
            		/*
     ((CC.EFF_FROM_DT <= '20061110' AND NVL(CC.EFF_THRU_DT, '99991231') >= '20061110')
        OR
     (CC.EFF_FROM_DT <= '20110310' AND NVL(CC.EFF_THRU_DT, '99991231') >= '20110310')
        OR
     (CC.EFF_FROM_DT >= '20061110' AND NVL(CC.EFF_THRU_DT, '99991231') <= '20110310')
            		 */
            		if ((ZYPDateUtil.compare(fromDate2, fromDate) <= 0 && ZYPDateUtil.compare(thruDate2, fromDate) >= 0)
            				|| (ZYPDateUtil.compare(fromDate2, thruDate) <= 0 && ZYPDateUtil.compare(thruDate2, thruDate) >= 0)
            				|| (ZYPDateUtil.compare(fromDate2, fromDate) >= 0 && ZYPDateUtil.compare(thruDate2, thruDate) <= 0)
            			) {
    	            	//NMAM0072E=0, @  is duplicated.
            		    sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Effective From - Thru Date"});
            		    sMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Effective From - Thru Date"});
                        validationFlg = true;
                        break;
            		}
            		
            	}
            	
            }
            if (validationFlg) break; 
        }
        
        return validationFlg;
    }
}
