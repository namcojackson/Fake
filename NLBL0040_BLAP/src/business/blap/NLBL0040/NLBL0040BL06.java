package business.blap.NLBL0040;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL0040.NLBL0040CMsg;
import business.blap.NLBL0040.NLBL0040SMsg;
import business.blap.NLBL0040.common.NLBL0040CommonLogic;
import business.blap.NLBL0040.constant.NLBL0040Constant;
import business.db.AREA_LEAD_TMTMsg;
import business.db.TRNSP_LTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * This class does update business process of BusinessID NLBL0040.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/20/2009   Fujitsu         D.Fukaya        Create          N/A
 * 09/09/2010   CSAI            D.Fukaya        Update          360
 *</pre>
 */
public class NLBL0040BL06 extends S21BusinessHandler implements
		NLBL0040Constant {

	@Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLBL0040SCRN00_CMN_SUBMIT.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_CMN_Submit((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
	
    /**
     * The method explanation: It is a method of the execution when the event[CMN_Submit] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_CMN_Submit(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_CMN_Submit================================START", this);
    	
    	cMsg.setCommitSMsg(true);
    	
    	// set screen data to sMsg
    	ZYPEZDItemValueSetter.setValue(sMsg.effFromDt_L1, cMsg.effFromDt_L1);
    	ZYPEZDItemValueSetter.setValue(sMsg.effThruDt_L1, cMsg.effThruDt_L1);
    	NLBL0040CommonLogic.copyAreaLeadTimeTblDataFromCMsgToSMsg(cMsg, sMsg);
    	
    	if (ZYPCommonFunc.hasValue(sMsg.stCd_G2)) {
    		
    		NLBL0040CommonLogic.copyTrnspLtTblDataFromCMsgToSMsg(cMsg, sMsg);
    	}
    	
	    // =============================================================
	    //  check all page data of Area Lead Time table
	    // ============================================================= 
	  	boolean errorFlag1 = false;
	  	boolean errorAlreadySetFlag1 = false;
	  	int firstErrorRowNum1 = 0;
	  	
	  	// check lead time mandatory, range 
	  	for (int i = 0; i < sMsg.A.getValidCount(); i++) {
	  		
	  		if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).delyLeadAot_A1)) {
	  			
	  			sMsg.A.no(i).delyLeadAot_A1.setErrorInfo(1, ZZM9000E, new String[]{NAME_FOR_MESSAGE_LEAD_TM});
	  			errorFlag1 = true;
	  			
	  		} else if (sMsg.A.no(i).delyLeadAot_A1.getValueInt() < 0 || 30 < sMsg.A.no(i).delyLeadAot_A1.getValueInt()) {
	  			
	  			sMsg.A.no(i).delyLeadAot_A1.setErrorInfo(1, NLBM1097E);
	  			errorFlag1 = true;
	  		}
	  		
	  		if (errorAlreadySetFlag1 == false && errorFlag1 == true) {
	  			errorAlreadySetFlag1 = true;
	  			firstErrorRowNum1 = i;
	  		}
	  	}
	  	
	  	if (errorAlreadySetFlag1) {
	  		
			NLBL0040CommonLogic.copyErrorPageDataOfAreaLeadTmTblFromSMsgToCMsg(firstErrorRowNum1, cMsg, sMsg);
		}
  	
	  	if (ZYPCommonFunc.hasValue(sMsg.stCd_G2)) {

		    // =============================================================
		    //  check all page data of trnspLT table
		    // ============================================================= 
		  	boolean errorFlag2 = false;
		  	boolean errorAlreadySetFlag2 = false;
		  	int firstErrorRowNum2 = 0;
		  	
		  	for (int i = 0; i < sMsg.B.getValidCount(); i++) {
		  		
			    // =============================================================
			    //  check zip code
			    // ============================================================= 
		  		boolean zipCodeMandatoryNumericCheckerrorFlag = false;
		  		
		  		if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).fromZipCd_B1)) {
		  			
		  			sMsg.B.no(i).fromZipCd_B1.setErrorInfo(1, ZZM9000E, new String[]{NAME_FOR_MESSAGE_FROM_POST_CD});
		  			errorFlag2 = true;
		  			zipCodeMandatoryNumericCheckerrorFlag = true;
		  			
		  		} else if (!EZDCommonFunc.isNumeric(sMsg.B.no(i).fromZipCd_B1.getValue())) {
		  			
		  			sMsg.B.no(i).fromZipCd_B1.setErrorInfo(1, NLBM0067E);
		  			errorFlag2 = true;
		  			zipCodeMandatoryNumericCheckerrorFlag = true;
		  		} 
		  		
		  		if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).toZipCd_B1)) {
		  			
		  			sMsg.B.no(i).toZipCd_B1.setErrorInfo(1, ZZM9000E, new String[]{NAME_FOR_MESSAGE_TO_POST_CD});
		  			errorFlag2 = true;
		  			zipCodeMandatoryNumericCheckerrorFlag = true;
		  			
		  		} else if (!EZDCommonFunc.isNumeric(sMsg.B.no(i).toZipCd_B1.getValue())) {
		  			
		  			sMsg.B.no(i).toZipCd_B1.setErrorInfo(1, NLBM0067E);
		  			errorFlag2 = true;
		  			zipCodeMandatoryNumericCheckerrorFlag = true;
		  		}
		  		
		  		if (!zipCodeMandatoryNumericCheckerrorFlag) {
		  			
		  			int fromZip = Integer.parseInt(sMsg.B.no(i).fromZipCd_B1.getValue());
		  			int toZip = Integer.parseInt(sMsg.B.no(i).toZipCd_B1.getValue());
		  			
		  			if (toZip < fromZip) {
		  				
		  				sMsg.B.no(i).fromZipCd_B1.setErrorInfo(1, NLBM0068E);
		  				sMsg.B.no(i).toZipCd_B1.setErrorInfo(1, NLBM0068E);
		  				errorFlag2 = true;
		  			}
		  		}

			    // =============================================================
			    //  check trnsp lead time
			    // ============================================================= 
		  		if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).trnspLtAot_B1)) {
		  			
		  			sMsg.B.no(i).trnspLtAot_B1.setErrorInfo(1, ZZM9000E, new String[]{NAME_FOR_MESSAGE_LEAD_TM});
		  			errorFlag2 = true;
		  			
		  		} else if (sMsg.B.no(i).trnspLtAot_B1.getValueInt() < 0 || 30 < sMsg.B.no(i).trnspLtAot_B1.getValueInt()) {
		  			
		  			sMsg.B.no(i).trnspLtAot_B1.setErrorInfo(1, NLBM1097E);
		  			errorFlag2 = true;
		  		}
		  		
		  		if (errorAlreadySetFlag2 == false && errorFlag2 == true) {
		  			
		  			errorAlreadySetFlag2 = true;
		  			firstErrorRowNum2 = i;
		  		}
		  	}

		  	if (errorAlreadySetFlag2) {
		  		
				NLBL0040CommonLogic.copyErrorPageDataOfTrnspLtTblFromSMsgToCMsg(firstErrorRowNum2, cMsg, sMsg);
				return;
			}
		  	
			// zip code range duplication check
			for (int i = 0; i < sMsg.B.getValidCount(); i++) {
	  		
		  		int fromZipBase = Integer.parseInt(sMsg.B.no(i).fromZipCd_B1.getValue());
		  		int toZipBase = Integer.parseInt(sMsg.B.no(i).toZipCd_B1.getValue());
		  		boolean zipCodeRangeDuplicationCheckErrorFlag2 = false;
		  		
		  		for (int j = 0; j < sMsg.B.getValidCount(); j++) {
		  			
		  			if (i == j) {
		  				continue;
		  			}
		  			
		  			int fromZip = Integer.parseInt(sMsg.B.no(j).fromZipCd_B1.getValue());
		  			int toZip = Integer.parseInt(sMsg.B.no(j).toZipCd_B1.getValue());
		  			
		  			if (fromZip <= fromZipBase && fromZipBase <= toZip) {
		  				zipCodeRangeDuplicationCheckErrorFlag2 = true;
		  			}
		
		  			if (fromZip <= toZipBase && toZipBase <= toZip) {
		  				zipCodeRangeDuplicationCheckErrorFlag2 = true;
		  			} 
		
		  			if (fromZipBase < fromZip && toZip < toZipBase) {
		  				zipCodeRangeDuplicationCheckErrorFlag2 = true;
		  			}
		  		}
		  		
		  		if (zipCodeRangeDuplicationCheckErrorFlag2) {
		  			
		  			sMsg.B.no(i).fromZipCd_B1.setErrorInfo(1, NLBM0069E);
					sMsg.B.no(i).toZipCd_B1.setErrorInfo(1, NLBM0069E);
					errorFlag2 = true;
		  		}
		  		
		  		if (errorAlreadySetFlag2 == false && errorFlag2 == true) {
		  			errorAlreadySetFlag2 = true;
		  			firstErrorRowNum2 = i;
		  		}
			}
			
			if (errorAlreadySetFlag2) {
				
				NLBL0040CommonLogic.copyErrorPageDataOfTrnspLtTblFromSMsgToCMsg(firstErrorRowNum2, cMsg, sMsg);
				return;
			}
	  	}

	  	if (errorAlreadySetFlag1) {
	  		
	  		return;
		}
	  	
        // ======================================================================================
        //  check if data has been updated by other users after search event was generated.
        // ======================================================================================
        NLBL0040Query.getInstance().getAreaLeadTmListForSubmitSnapshot(sMsg);
        
    	// check if data has not been changed. 
        if (sMsg.S.getValidCount() != sMsg.T.getValidCount()) {
        	
        	// show message "This data has been updated by another user."
        	cMsg.setMessageInfo(NLBM0009E);
            return;
        }

        for (int i = 0; i < sMsg.S.getValidCount(); i++) {
        	
        	if (sMsg.S.no(i).ezUpTime_S1.getValue().equals(sMsg.T.no(i).ezUpTime_T1.getValue()) &&
        			sMsg.S.no(i).ezUpTimeZone_S1.getValue().equals(sMsg.T.no(i).ezUpTimeZone_T1.getValue())) {
        		
        		continue;
        		
        	} else {
        		
            	// show message "This data has been updated by another user."
            	cMsg.setMessageInfo(NLBM0009E);
                return;
        	}
        }
        
        if (ZYPCommonFunc.hasValue(sMsg.stCd_G2.getValue())) {
        	
        	NLBL0040Query.getInstance().getTrnspLtListForSubmitSnapshot(sMsg);
        	
        	if (sMsg.X.getValidCount() != sMsg.Y.getValidCount()) {
            	
            	// show message "This data has been updated by another user."
            	cMsg.setMessageInfo(NLBM0009E);
                return;
            }
        	
        	for (int i = 0; i < sMsg.X.getValidCount(); i++) {

            	if (sMsg.X.no(i).ezUpTime_X1.getValue().equals(sMsg.Y.no(i).ezUpTime_Y1.getValue()) &&
            			sMsg.X.no(i).ezUpTimeZone_X1.getValue().equals(sMsg.Y.no(i).ezUpTimeZone_Y1.getValue())) {
            		
            		continue;
            		
            	} else {
            		
                	// show message "This data has been updated by another user."
                	cMsg.setMessageInfo(NLBM0009E);
                    return;
            	}
            }
        }
	  	
        // ======================================================================================
        //  updating data processing 
        // ======================================================================================
	  	if (ZYPCommonFunc.hasValue(sMsg.effFromDt_G1)) {
	  		
// 09/09/2010 D.Fukaya delete start
//	        //check if effective period is changed.
//	        boolean effPerUpdatedFlag = true;
//	        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
//	        	
//	        	if (sMsg.C.no(i).effFromDt_C1.getValue().equals(sMsg.effFromDt_L1.getValue()) &&
//	        			sMsg.C.no(i).effThruDt_C1.getValue().equals(sMsg.effThruDt_L1.getValue())) {
//	        		
//	        		effPerUpdatedFlag = false;
//	        		break;
//	        	}
//	        }
//	        
//	        if (effPerUpdatedFlag == false) {
//	        	
//	            // ======================================================================================
//	            //  Update : AREA_LEAD_TM 
//	            // ======================================================================================
//	        	for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//	        		
//	        		AREA_LEAD_TMTMsg searchParamAreaLeadTmTMsg = new AREA_LEAD_TMTMsg();
//	        		searchParamAreaLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
//	        		searchParamAreaLeadTmTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
//	        		searchParamAreaLeadTmTMsg.stCd.setValue(sMsg.A.no(i).stCd_A1.getValue());
//	        		searchParamAreaLeadTmTMsg.shpgModeCd.setValue(sMsg.A.no(i).shpgModeCd_A1.getValue());
//	        		searchParamAreaLeadTmTMsg.effFromDt.setValue(sMsg.effFromDt_L1.getValue());
//	                
//	        		AREA_LEAD_TMTMsg searchResultAreaLeadTmTMsg = (AREA_LEAD_TMTMsg) EZDTBLAccessor.findByKey(searchParamAreaLeadTmTMsg);
//	
//	        		if (searchResultAreaLeadTmTMsg == null) {
//	        			
//	        			searchParamAreaLeadTmTMsg.effThruDt.setValue(sMsg.effThruDt_L1.getValue());
//	        			searchParamAreaLeadTmTMsg.delyLeadAot.setValue(sMsg.A.no(i).delyLeadAot_A1.getValueInt() * 24);
//	                    
//	        			EZDTBLAccessor.create(searchParamAreaLeadTmTMsg);
//	        			
//	        			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(searchParamAreaLeadTmTMsg.getReturnCode())) {
//	        				cMsg.setMessageInfo(NLBM0024E);
//	        				return;
//	        			}
//	        			
//	        		} else {
//	        			
//	        			searchResultAreaLeadTmTMsg.delyLeadAot.setValue(sMsg.A.no(i).delyLeadAot_A1.getValueInt() * 24);
//	        			
//	        			EZDTBLAccessor.update(searchResultAreaLeadTmTMsg);
//	        			
//	        			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(searchResultAreaLeadTmTMsg.getReturnCode())) {
//	        				cMsg.setMessageInfo(NLBM0024E);
//	        				return;
//	        			}
//	        		}
//	        	}
//	        	
//	        	if (ZYPCommonFunc.hasValue(sMsg.stCd_G2)) {
//	            	
//	                // ======================================================================================
//	                //  Update : TRNSP_LT
//	                // ======================================================================================
//	            	TRNSP_LTTMsg deleteParamTrnspLtTMsg = new TRNSP_LTTMsg();
//	            	deleteParamTrnspLtTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
//	            	deleteParamTrnspLtTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
//	            	deleteParamTrnspLtTMsg.stCd.setValue(sMsg.stCd_G2.getValue());
//	            	deleteParamTrnspLtTMsg.shpgModeCd.setValue(sMsg.shpgModeCd_G2.getValue());
//	            	deleteParamTrnspLtTMsg.effFromDt.setValue(sMsg.effFromDt_L1.getValue());
//	                
//	        		EZDTBLAccessor.removeByPartialKey(deleteParamTrnspLtTMsg);
//	        		
//	            	for (int i = 0; i < sMsg.B.getValidCount(); i++) {
//	            		
//	            		String zipSqNum = ZYPCommonFunc.leftPad(new Integer(i+1).toString(), ZIP_SQ_NUM_MAX_LENGTH, ZIP_SQ_NUM_LEFT_PADDING_STRING);
//	            		
//	            		TRNSP_LTTMsg createParamTrnspLtTMsg = new TRNSP_LTTMsg();
//	            		createParamTrnspLtTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
//	            		createParamTrnspLtTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
//	            		createParamTrnspLtTMsg.stCd.setValue(sMsg.stCd_G2.getValue());
//	            		createParamTrnspLtTMsg.shpgModeCd.setValue(sMsg.shpgModeCd_G2.getValue());
//	            		createParamTrnspLtTMsg.effFromDt.setValue(sMsg.effFromDt_L1.getValue());
//	            		createParamTrnspLtTMsg.zipSqNum.setValue(zipSqNum);
//	            		createParamTrnspLtTMsg.trnspLtAot.setValue(sMsg.B.no(i).trnspLtAot_B1.getValueInt() * 24);
//	            		createParamTrnspLtTMsg.effThruDt.setValue(sMsg.effThruDt_L1.getValue());
//	            		createParamTrnspLtTMsg.fromZipCd.setValue(sMsg.B.no(i).fromZipCd_B1.getValue());
//	            		createParamTrnspLtTMsg.toZipCd.setValue(sMsg.B.no(i).toZipCd_B1.getValue());
//	            		
//	            		EZDTBLAccessor.create(createParamTrnspLtTMsg);
//	            		
//	            		if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(createParamTrnspLtTMsg.getReturnCode())) {
//	        				cMsg.setMessageInfo(NLBM0024E);
//	        				return;
//	        			}
//	            	}
//	        	}
//	        	
//	        } else {
// 09/09/2010 D.Fukaya delete end
                // 09/09/2010 D.Fukaya append start
                // ======================================================================================
                //  get transLT data from old effective period
                // ======================================================================================
                List<TRNSP_LTTMsg> transpLtTMsg = getTranspLtFromOldEffPeriod(cMsg, sMsg);
                // 09/09/2010 D.Fukaya append end

	            // ======================================================================================
	            //  update current data's effective period
	            // ======================================================================================
	        	String effFromNew = sMsg.effFromDt_L1.getValue();
	    		String effThruNew = sMsg.effThruDt_L1.getValue();
	    		
	    		// for roop starts here
	        	for (int i = 0; i < sMsg.C.getValidCount(); i++) {
	        		
	        		String effFromCurrent = sMsg.C.no(i).effFromDt_C1.getValue();
	        		String effThruCurrent = sMsg.C.no(i).effThruDt_C1.getValue();
	
	        		if (ZYPDateUtil.compare(effFromNew, effFromCurrent) == -1) {
	        			// effFromNew < effFromCurrent
	        			
	        			if (ZYPDateUtil.compare(effThruNew, effFromCurrent) == -1) {
	        				// effThruNew < effFromCurrent
	        				
	        				// there is no processing.
	        				
	        			} else if (ZYPDateUtil.compare(effThruNew, effFromCurrent) == 0) {
	        				// effThruNew == effFromCurrent
	        				
	        				if(currnetDataDeleteInsert(effFromCurrent, effThruCurrent, cMsg, sMsg) == false) {
	        					return;
	        				}
	        				
	        			} else if (ZYPDateUtil.compare(effThruNew, effFromCurrent) == 1 &&
	        					ZYPDateUtil.compare(effThruNew, effThruCurrent) == -1) {
	        				// effThruNew > effFromCurrent and effThruNew < effThruCurrent
	        				
	        				if(currnetDataDeleteInsert(effFromCurrent, effThruCurrent, cMsg, sMsg) == false) {
	        					return;
	        				}
	        				
	        			} else if (ZYPDateUtil.compare(effThruNew, effThruCurrent) == 0
	        					|| ZYPDateUtil.compare(effThruNew, effThruCurrent) == 1) {
	        				// effThruNew == effThruCurrent or effThruNew > effThruCurrent
	        				
	        				
	        				if(currentDataDelete(effFromCurrent, cMsg, sMsg) == false) {
	        					return;
	        				}
	        			
	        			} else {
	        				// Process doesn't come here. 
	        			}
	        		
	        		} else if (ZYPDateUtil.compare(effFromNew, effFromCurrent) == 0) {
	        			// effFromNew == effFromCurrent
	        			
	        			if (ZYPDateUtil.compare(effThruNew, effFromCurrent) == 0 ||
	        				(ZYPDateUtil.compare(effThruNew, effFromCurrent) == 1 &&
	                		 ZYPDateUtil.compare(effThruNew, effThruCurrent) == -1)) {
	        				// effThruNew == effFromCurrent or (effThruNew > effFromCurrent and effThruNew < effThruCurrent)
	        				
	
	        				if(currnetDataDeleteInsert(effFromCurrent, effThruCurrent, cMsg, sMsg) == false) {
	        					return;
	        				}
	        				        				
	        			} else if (ZYPDateUtil.compare(effThruNew, effThruCurrent) == 0
	        					|| ZYPDateUtil.compare(effThruNew, effThruCurrent) == 1) {
	        				// effThruNew == effThruCurrent or effThruNew > effThruCurrent
	        				
	        				if(currentDataDelete(effFromCurrent, cMsg, sMsg) == false) {
	        					return;
	        				}
	        			
	        			} else {
	        				// Process doesn't come here. 
	        			}
	        			
	        			
	        		} else if ((ZYPDateUtil.compare(effFromNew, effFromCurrent) == 1
	        				&& ZYPDateUtil.compare(effFromNew, effThruCurrent) == -1)
	        				|| ZYPDateUtil.compare(effFromNew, effThruCurrent) == 0) {
	        			// (effFromNew == effFromCurrent and effFromNew < effThruCurrent) or effFromNew == effThruCurrent
	        			
	        			
	        			if (ZYPDateUtil.compare(effThruNew, effFromCurrent) == 1
	        				&& ZYPDateUtil.compare(effThruNew, effThruCurrent) == -1) {
	        				// effThruNew > effFromCurrent and effThruNew < effThruCurrent
	        				
	        				if(currnetDataUpdateInsert(effFromCurrent, effThruCurrent, cMsg, sMsg) == false) {
	        					return;
	        				}
	        				
	        			} else if (ZYPDateUtil.compare(effThruNew, effThruCurrent) == 0
	        				|| ZYPDateUtil.compare(effThruNew, effThruCurrent) == 1) {
	        				// effThruNew == effThruCurrent or effThruNew == effThruCurrent
	        				
	        				if(currentDataUpdate(effFromCurrent, effThruCurrent, cMsg, sMsg) == false) {
	        					return;
	        				}
	        				
	        			} else {
	        				
	        				// Process doesn't come here. 
	        			}
	
	        		} else if (ZYPDateUtil.compare(effFromNew, effThruCurrent) == 1) {
	        			// effFromNew > effThruCurrent
	
	    				// there is no processing.
	    				
	        		} else {
	        			// Process doesn't come here. 
	        		}
	        	}
	        	// for roop ends here

	            // ======================================================================================
	            //  Insert new data processing 
	            // ======================================================================================
	        	if (insertNewData(cMsg, sMsg) == false) {
	        		return;
	        	}
                // 09/09/2010 D.Fukaya append start
                if (insertTranspLTFromOldEffPeriod(cMsg, transpLtTMsg) == false) {
                    return;
                }
                // 09/09/2010 D.Fukaya append end
// 09/09/2010 D.Fukaya delete start
//	        }
// 09/09/2010 D.Fukaya delete end
	  	} else {
	  		
            // ======================================================================================
            //  Insert new data processing 
            // ======================================================================================
	  		if (insertNewData(cMsg, sMsg) == false) {
        		return;
        	}
	  	}

	  	cMsg.setMessageInfo(ZZM8100I);

    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_CMN_Submit================================END", this);
    }
    

    private boolean currnetDataDeleteInsert(String effFromCurrent, String effThruCurrent, NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	
    	boolean currentDataInsertSuccessFlag = currentDataInsert(effFromCurrent, effThruCurrent, cMsg, sMsg);
		
    	if (currentDataInsertSuccessFlag == false) {
    		return false;
    	}
    	
    	boolean currentDataDeleteSuccessFlag = currentDataDelete(effFromCurrent, cMsg, sMsg);
    	
    	if (currentDataDeleteSuccessFlag == false) {
    		return false;
    	}
    	

		return true;
    }
    
    private boolean currnetDataUpdateInsert(String effFromCurrent, String effThruCurrent, NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	
    	boolean currentDataUpdateSuccessFlag = currentDataUpdate(effFromCurrent, effThruCurrent, cMsg, sMsg);
    	if (currentDataUpdateSuccessFlag == false) {
    		return false;
    	}
    	
    	boolean currentDataInsertSuccessFlag = currentDataInsert(effFromCurrent, effThruCurrent, cMsg, sMsg);
    	if (currentDataInsertSuccessFlag == false) {
    		return false;
    	}
    	
    	
		return true;
    }

    private boolean currentDataDelete(String effFromCurrent, NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	
    	// ======================================================================================
        //  Delete : AREA_LEAD_TM 
        // ======================================================================================
		AREA_LEAD_TMTMsg deleteParamAreaLeadTmTMsg = new AREA_LEAD_TMTMsg();
		deleteParamAreaLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
		deleteParamAreaLeadTmTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
		deleteParamAreaLeadTmTMsg.effFromDt.setValue(effFromCurrent);
		EZDTBLAccessor.removeByPartialKey(deleteParamAreaLeadTmTMsg);
		
		if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(deleteParamAreaLeadTmTMsg.getReturnCode())) {
			cMsg.setMessageInfo(NLBM0024E);
			return false;
		}
		
        // ======================================================================================
        //  Delete : TRNSP_LT
        // ======================================================================================
		TRNSP_LTTMsg deleteParamTrnspLtTMsg = new TRNSP_LTTMsg();
		deleteParamTrnspLtTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
		deleteParamTrnspLtTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
		deleteParamTrnspLtTMsg.effFromDt.setValue(effFromCurrent);

		S21SsmEZDResult ssmResultForGetTrnspLtListByEffFrom = NLBL0040Query.getInstance().getTrnspLtListByEffFrom(deleteParamTrnspLtTMsg);
		List<TRNSP_LTTMsg> trnspLtTMsgtListForDelete = (List<TRNSP_LTTMsg>)ssmResultForGetTrnspLtListByEffFrom.getResultObject();
		
		if (trnspLtTMsgtListForDelete.size() > 0) {

			EZDTBLAccessor.removeByPartialKey(deleteParamTrnspLtTMsg);
			
			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(deleteParamTrnspLtTMsg.getReturnCode())) {
				cMsg.setMessageInfo(NLBM0024E);
				return false;
			}
		}	
		
		return true;
	}

	private boolean currentDataInsert(String effFromCurrent, String effThruCurrent, NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
   	
       // ======================================================================================
       //  Insert : AREA_LEAD_TM 
       // ======================================================================================
		AREA_LEAD_TMTMsg ssmParamAreaLeadTmTMsg = new AREA_LEAD_TMTMsg();
		ssmParamAreaLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
		ssmParamAreaLeadTmTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
		ssmParamAreaLeadTmTMsg.effFromDt.setValue(effFromCurrent);
		
		S21SsmEZDResult ssmResultForGetAreaLeadTmListByWHAndEffFrom = NLBL0040Query.getInstance().getAreaLeadTmListByWHAndEffFrom(ssmParamAreaLeadTmTMsg);
		List<AREA_LEAD_TMTMsg> areaLeadTmTMsgListForInsert = (List<AREA_LEAD_TMTMsg>)ssmResultForGetAreaLeadTmListByWHAndEffFrom.getResultObject();
		
		for (int i = 0; i < areaLeadTmTMsgListForInsert.size(); i++) {
			
			AREA_LEAD_TMTMsg areaLeadTmTMsgForInsert = areaLeadTmTMsgListForInsert.get(i);
			areaLeadTmTMsgForInsert.effFromDt.setValue(ZYPDateUtil.addDays(sMsg.effThruDt_L1.getValue(), 1));
			areaLeadTmTMsgForInsert.effThruDt.setValue(effThruCurrent);
			
			EZDTBLAccessor.create(areaLeadTmTMsgForInsert);
			
			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(areaLeadTmTMsgForInsert.getReturnCode())) {
				cMsg.setMessageInfo(NLBM0024E);
				return false;
			}
		}
		
		// ======================================================================================
		//  Insert : TRNSP_LT
		// ======================================================================================
		TRNSP_LTTMsg ssmParamtrnspLtTMsg = new TRNSP_LTTMsg();
		ssmParamtrnspLtTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
		ssmParamtrnspLtTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
		ssmParamtrnspLtTMsg.effFromDt.setValue(effFromCurrent);

		S21SsmEZDResult ssmResultForGetTrnspLtListByEffFrom = NLBL0040Query.getInstance().getTrnspLtListByEffFrom(ssmParamtrnspLtTMsg);
		List<TRNSP_LTTMsg> trnspLtTMsgtListForInsert = (List<TRNSP_LTTMsg>)ssmResultForGetTrnspLtListByEffFrom.getResultObject();
		
		for (int i = 0; i < trnspLtTMsgtListForInsert.size(); i++) {
			
			TRNSP_LTTMsg trnspLtTMsgForInsert = trnspLtTMsgtListForInsert.get(i);
			trnspLtTMsgForInsert.effFromDt.setValue(ZYPDateUtil.addDays(sMsg.effThruDt_L1.getValue(), 1));
			trnspLtTMsgForInsert.effThruDt.setValue(effThruCurrent);
			
			EZDTBLAccessor.create(trnspLtTMsgForInsert);
			
			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(trnspLtTMsgForInsert.getReturnCode())) {
				cMsg.setMessageInfo(NLBM0024E);
				return false;
			}
		}			

		return true;
    }
   
    private boolean currentDataUpdate(String effFromCurrent, String effThruCurrent, NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

		// ======================================================================================
		//  Update : AREA_LEAD_TM 
		// ======================================================================================
		AREA_LEAD_TMTMsg ssmParamAreaLeadTmTMsg = new AREA_LEAD_TMTMsg();
		ssmParamAreaLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
		ssmParamAreaLeadTmTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
		ssmParamAreaLeadTmTMsg.effFromDt.setValue(effFromCurrent);
		
		S21SsmEZDResult ssmResultForGetAreaLeadTmListByWHAndEffFrom = NLBL0040Query.getInstance().getAreaLeadTmListByWHAndEffFrom(ssmParamAreaLeadTmTMsg);
		List<AREA_LEAD_TMTMsg> areaLeadTmTMsgListForUpdate = (List<AREA_LEAD_TMTMsg>)ssmResultForGetAreaLeadTmListByWHAndEffFrom.getResultObject();
		
		for (int i = 0; i < areaLeadTmTMsgListForUpdate.size(); i++) {
			
			AREA_LEAD_TMTMsg areaLeadTmTMsgForUpdate = areaLeadTmTMsgListForUpdate.get(i);
			areaLeadTmTMsgForUpdate.effThruDt.setValue(ZYPDateUtil.addDays(sMsg.effFromDt_L1.getValue(), -1));
			
			EZDTBLAccessor.update(areaLeadTmTMsgForUpdate);
			
			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(areaLeadTmTMsgForUpdate.getReturnCode())) {
				cMsg.setMessageInfo(NLBM0024E);
				return false;
			}
		}

		// ======================================================================================
		//  Update : TRNSP_LT
		// ======================================================================================
		TRNSP_LTTMsg ssmParamtrnspLtTMsg = new TRNSP_LTTMsg();
		ssmParamtrnspLtTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
		ssmParamtrnspLtTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
		ssmParamtrnspLtTMsg.effFromDt.setValue(effFromCurrent);

		S21SsmEZDResult ssmResultForGetTrnspLtListByEffFrom = NLBL0040Query.getInstance().getTrnspLtListByEffFrom(ssmParamtrnspLtTMsg);
		List<TRNSP_LTTMsg> trnspLtTMsgtListForUpdate = (List<TRNSP_LTTMsg>)ssmResultForGetTrnspLtListByEffFrom.getResultObject();
		
		for (int i = 0; i < trnspLtTMsgtListForUpdate.size(); i++) {
			
			TRNSP_LTTMsg trnspLtTMsgForUpdate = trnspLtTMsgtListForUpdate.get(i);
			trnspLtTMsgForUpdate.effThruDt.setValue(ZYPDateUtil.addDays(sMsg.effFromDt_L1.getValue(), -1));
			
			EZDTBLAccessor.update(trnspLtTMsgForUpdate);
			
			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(trnspLtTMsgForUpdate.getReturnCode())) {
				cMsg.setMessageInfo(NLBM0024E);
				return false;
			}
		}	
		
		return true;
    }
   
    private boolean insertNewData(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

		// ======================================================================================
		//  Insert : AREA_LEAD_TM 
		// ======================================================================================
		for (int i = 0; i < sMsg.A.getValidCount(); i++) {
			
			AREA_LEAD_TMTMsg insertParamAreaLeadTmTMsg = new AREA_LEAD_TMTMsg();
			insertParamAreaLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
			insertParamAreaLeadTmTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
			insertParamAreaLeadTmTMsg.stCd.setValue(sMsg.A.no(i).stCd_A1.getValue());
			insertParamAreaLeadTmTMsg.shpgModeCd.setValue(sMsg.A.no(i).shpgModeCd_A1.getValue());
			insertParamAreaLeadTmTMsg.effFromDt.setValue(sMsg.effFromDt_L1.getValue());
			insertParamAreaLeadTmTMsg.delyLeadAot.setValue(sMsg.A.no(i).delyLeadAot_A1.getValueInt() * 24);
			insertParamAreaLeadTmTMsg.effThruDt.setValue(sMsg.effThruDt_L1.getValue());
			
			EZDTBLAccessor.create(insertParamAreaLeadTmTMsg);
			
			if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(insertParamAreaLeadTmTMsg.getReturnCode())) {
				cMsg.setMessageInfo(NLBM0024E);
				return false;
			}
		}

		if (ZYPCommonFunc.hasValue(sMsg.stCd_G2)) {

		    // ======================================================================================
		    //  Insert : TRNSP_LT
		    // ======================================================================================
		    for (int i = 0; i < sMsg.B.getValidCount(); i++) {
		    	
		    	TRNSP_LTTMsg insertParamTrnspLtTMsg = new TRNSP_LTTMsg();
		    	insertParamTrnspLtTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
		    	insertParamTrnspLtTMsg.whCd.setValue(sMsg.whCd_G1.getValue());
		    	insertParamTrnspLtTMsg.stCd.setValue(sMsg.stCd_G2.getValue());
		    	insertParamTrnspLtTMsg.shpgModeCd.setValue(sMsg.shpgModeCd_G2.getValue());
		    	insertParamTrnspLtTMsg.effFromDt.setValue(sMsg.effFromDt_L1.getValue());
		    	insertParamTrnspLtTMsg.zipSqNum.setValue(Integer.toString(i+1));
		    	insertParamTrnspLtTMsg.trnspLtAot.setValue(sMsg.B.no(i).trnspLtAot_B1.getValueInt() * 24);
		    	insertParamTrnspLtTMsg.effThruDt.setValue(sMsg.effThruDt_L1.getValue());
		    	insertParamTrnspLtTMsg.fromZipCd.setValue(sMsg.B.no(i).fromZipCd_B1.getValue());
		    	insertParamTrnspLtTMsg.toZipCd.setValue(sMsg.B.no(i).toZipCd_B1.getValue());
		    	
		    	EZDTBLAccessor.create(insertParamTrnspLtTMsg);
		    	
		    	if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(insertParamTrnspLtTMsg.getReturnCode())) {
					cMsg.setMessageInfo(NLBM0024E);
					return false;
				}
		   	}
		}
		
		return true;
    }

    // 09/09/2010 D.Fukaya append start
    private List<TRNSP_LTTMsg> getTranspLtFromOldEffPeriod(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        S21SsmEZDResult ssmResult = NLBL0040Query.getInstance().getTrnspLtListByPartialKeyForInsert(sMsg);
        List<TRNSP_LTTMsg> trnspLtTMsg = (List<TRNSP_LTTMsg>)ssmResult.getResultObject();

        return trnspLtTMsg;
    }
    
    private boolean insertTranspLTFromOldEffPeriod(NLBL0040CMsg cMsg, List<TRNSP_LTTMsg> trnspLtTMsg) {

        if (trnspLtTMsg.size() > 0) {

            TRNSP_LTTMsg[] TrnspLtTMsgArray = new TRNSP_LTTMsg[trnspLtTMsg.size()];
            
            for (int i = 0; i < trnspLtTMsg.size(); i++) {
                
                TrnspLtTMsgArray[i] = trnspLtTMsg.get(i);
            }
            
            int retCnt = S21FastTBLAccessor.insert(TrnspLtTMsgArray);

            if (retCnt != trnspLtTMsg.size()) {
                cMsg.setMessageInfo(NLBM0024E);
                return false;
            }
        }

        return true;
    }
    // 09/09/2010 D.Fukaya append end
   
}
