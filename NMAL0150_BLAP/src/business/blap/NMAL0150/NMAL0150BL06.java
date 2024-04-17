package business.blap.NMAL0150;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.blap.NMAL0150.common.NMAL0150CommonLogic;
import business.blap.NMAL0150.constant.NMAL0150Constant;
import business.db.MDSETMsg;
import business.db.MDSE_CST_UPD_HIST_HDRTMsg;
import business.db.MDSE_CST_UPD_TPTMsg;
import business.file.NMAL0150F00FMsg;
import parts.common.EZDCommonFunc;
import parts.common.EZDCSVInFile;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 11/21/2016   Fujitsu         R.Nakamura      Update          QC#16082
 * 2022/05/30   Hitachi         A.Kohinata      Update          QC#55970
 *</pre>
 */
public class NMAL0150BL06 extends S21BusinessHandler implements NMAL0150Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
        NMAL0150CMsg bizMsg = (NMAL0150CMsg) cMsg;
        // add start 2022/05/30 QC#55970
        NMAL0150SMsg glblMsg = (NMAL0150SMsg) sMsg;
        // add end 2022/05/30 QC#55970
        String scrnAplID = bizMsg.getScreenAplID();
        try {
    		if ("NMAL0150Scrn00_CMN_Submit".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
            	//NMAL0150Scrn00_CMN_Submit(bizMsg);
                NMAL0150Scrn00_CMN_Submit(bizMsg, glblMsg);
                // mod end 2022/05/30 QC#55970
    		} else if ("NMAL0150Scrn00_Upload".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
        		//doUpload_UPLOAD(bizMsg);
                doUpload_UPLOAD(bizMsg, glblMsg);
                // mod end 2022/05/30 QC#55970
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
    // mod start 2022/05/30 QC#55970
    //private void NMAL0150Scrn00_CMN_Submit(NMAL0150CMsg bizMsg) {
    private void NMAL0150Scrn00_CMN_Submit(NMAL0150CMsg bizMsg, NMAL0150SMsg glblMsg) {
    // mod end 2022/05/30 QC#55970

        // add start 2022/05/30 QC#55970
        NMAL0150CommonLogic.copyCMsgToSMsg(bizMsg, glblMsg);
        bizMsg.setCommitSMsg(true);
        // add end 2022/05/30 QC#55970

        // #####################
    	// Input Check
    	// #####################
    	// Header
        boolean hasError = checkInput_Header(bizMsg);
    	if (hasError) {
    		return;
    	}
        // mod start 2022/05/30 QC#55970
        //hasError = checkInput_Detail(bizMsg);
        hasError = checkInput_Detail(bizMsg, glblMsg);
        // mod end 2022/05/30 QC#55970
    	if (hasError) {
            // add start 2022/05/30 QC#55970
            int errIndex = NMAL0150CommonLogic.getFirstErrorIndex(bizMsg, glblMsg);
            NMAL0150CommonLogic.pagenation(bizMsg, glblMsg, errIndex);
            // add end 2022/05/30 QC#55970
    		return;
    	}
    	
    	// #####################
    	// Update
    	// #####################
        //Search Header
        // mod start 2022/05/30 QC#55970
//    	MDSE_CST_UPD_HIST_HDRTMsg headerTMsg = null;
//    	boolean isExists = false;
//    	if (ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdHistHdrPk_H1)) {
//        	headerTMsg = NMAL0150CommonLogic.findHeader(getGlobalCompanyCode(), bizMsg.mdseCstUpdHistHdrPk_H1.getValue());
//	        if (headerTMsg == null) {
//	        	headerTMsg = new MDSE_CST_UPD_HIST_HDRTMsg();
//	        	ZYPEZDItemValueSetter.setValue(headerTMsg.glblCmpyCd, getGlobalCompanyCode());
//	        	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdHistHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_CST_UPD_HIST_HDR_SQ));
//	        } else {
//	        	isExists = true;
//	        }
//    	} else {
//        	headerTMsg = new MDSE_CST_UPD_HIST_HDRTMsg();
//        	ZYPEZDItemValueSetter.setValue(headerTMsg.glblCmpyCd, getGlobalCompanyCode());
//        	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdHistHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_CST_UPD_HIST_HDR_SQ));
//    	}
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdHistHdrPk_H1)) {
            return;
        }
        MDSE_CST_UPD_HIST_HDRTMsg headerTMsg = new MDSE_CST_UPD_HIST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(headerTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdHistHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_CST_UPD_HIST_HDR_SQ));
        // mod end 2022/05/30 QC#55970

        ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdTpCd, bizMsg.mdseCstUpdTpCd_H1);
    	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdGrpTxt, bizMsg.mdseCstUpdGrpTxt_H1);
    	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdDescTxt, bizMsg.mdseCstUpdDescTxt_H1);
    	ZYPEZDItemValueSetter.setValue(headerTMsg.origMdseCstUpdTpCd, bizMsg.mdseCstUpdTpCd_H1);
    	if (ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdCratPsnCd_H1)) {
	    	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdCratPsnCd, bizMsg.mdseCstUpdCratPsnCd_H1);
    	} else {
    		S21UserProfileService profile = getUserProfileService();
	    	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdCratPsnCd, profile.getContextUserInfo().getUserId());
    	}
    	if (ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdCratDt_H1)) {
	    	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdCratDt, bizMsg.mdseCstUpdCratDt_H1);
    	} else {
	    	ZYPEZDItemValueSetter.setValue(headerTMsg.mdseCstUpdCratDt, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
    	}
    	
        // mod start 2022/05/30 QC#55970
//    	if (isExists) {
//            String tmpTimeZone = headerTMsg.ezUpTimeZone.getValue();
//            String tmpTime = headerTMsg.ezUpTime.getValue();
//            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), tmpTime, tmpTimeZone)) {
//            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
//                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CST_UPD_HIST_HDR", "MDSE_CST_UPD_HIST_HDR_PK", String.valueOf(headerTMsg.mdseCstUpdHistHdrPk.getValue())});
//    		    return;
//            }
//            S21FastTBLAccessor.update(headerTMsg);
//    		if (!RETURN_CD_NORMAL.equals(headerTMsg.getReturnCode())) {
//    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
//    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_CST_UPD_HIST_HDR", "MDSE_CST_UPD_HIST_HDR_PK", String.valueOf(headerTMsg.mdseCstUpdHistHdrPk.getValue())});
//    		    return;
//    		}
//    	} else {
//            EZDTBLAccessor.create(headerTMsg);
//            if (!RETURN_CD_NORMAL.equals(headerTMsg.getReturnCode())) {
//            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
//                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_CST_UPD_HIST_HDR", "MDSE_CST_UPD_HIST_HDR_PK", String.valueOf(headerTMsg.mdseCstUpdHistHdrPk.getValue())});
//                return;
//            }
//    	}
        EZDTBLAccessor.create(headerTMsg);
        if (!RETURN_CD_NORMAL.equals(headerTMsg.getReturnCode())) {
            // ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_CST_UPD_HIST_HDR", "MDSE_CST_UPD_HIST_HDR_PK", String.valueOf(headerTMsg.mdseCstUpdHistHdrPk.getValue())});
            return;
        }
        // mod end 2022/05/30 QC#55970
    	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdHistHdrPk_H1, headerTMsg.mdseCstUpdHistHdrPk);
    	
    	// Detail
        // del start 2022/05/30 QC#55970
//   		for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//   			boolean isFound = false;
//   		    for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
//   		        if (bizMsg.B.no(i).mdseCd_BB.getValue().equals(bizMsg.A.no(j).mdseCd_AB.getValue())) {
//   		        	isFound = true;
//   		        	BigDecimal amtA1 = NMAL0150CommonLogic.nvlBigDecimal(bizMsg.A.no(i).rqstTotStdCostAmt_A1.getValue());
//   		        	BigDecimal amtB1 = NMAL0150CommonLogic.nvlBigDecimal(bizMsg.B.no(i).rqstTotStdCostAmt_B1.getValue());
//   		        	if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCd_A1) && 
//   		        		!ZYPCommonFunc.hasValue(bizMsg.A.no(i).rqstTotStdCostAmt_A1) &&
//   		        		!ZYPCommonFunc.hasValue(bizMsg.A.no(i).pkgUomCd_A1) &&
//   		        		!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCstUpdEffFromDt_A1)
//   		        		) {
//   		                if(!NMAL0150CommonLogic.deleteDetail(getGlobalCompanyCode(), bizMsg, j)) {
//   		   		    	    return;
//   		                }
//   		        	} else if (!bizMsg.B.no(i).mdseCd_B1.getValue().equals(bizMsg.A.no(j).mdseCd_A1.getValue()) 
//   		        			|| amtB1.compareTo(amtA1) != 0
//   		        			|| !bizMsg.B.no(i).pkgUomCd_B1.getValue().equals(bizMsg.A.no(j).pkgUomCd_A1.getValue())
//   		        			|| !bizMsg.B.no(i).mdseCstUpdEffFromDt_B1.getValue().equals(bizMsg.A.no(j).mdseCstUpdEffFromDt_A1.getValue())
//   		        			) {
//   		                // update.
//   		        		NMAL0150CommonLogic.updateDetail(getGlobalCompanyCode(), bizMsg, j);
//   		            }
//   		        }
//   		    }
//   		    if (isFound == false) {
//   		    	if(!NMAL0150CommonLogic.deleteDetail(getGlobalCompanyCode(), bizMsg, i)) {
//   		    	    return;
//   		    	}
//   		    }
//   		}
        // del end 2022/05/30 QC#55970
        // mod start 2022/05/30 QC#55970
//   		boolean chkDetailListExists = NMAL0150CommonLogic.chkDetailListExists(bizMsg);
//   		if (chkDetailListExists) {
//   			for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//   		        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCd_AB.getValue())) {
//   					if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCd_A1) 
//   							&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).rqstTotStdCostAmt_A1)
//   							&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).pkgUomCd_A1)
//   							&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCstUpdEffFromDt_A1)
//   							) {
//   						continue;
//   					}
//   					if (!NMAL0150CommonLogic.insertDetail(getGlobalCompanyCode(), bizMsg, i)) {
//   	   		    	    return;
//   		            }
//   		        }
//   			}
//   		}
        boolean chkDetailListExists = NMAL0150CommonLogic.chkDetailListExists(glblMsg);
        if (chkDetailListExists) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_AB.getValue())) {
                    if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).rqstTotStdCostAmt_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
                        continue;
                    }
                    if (!NMAL0150CommonLogic.insertDetail(getGlobalCompanyCode(), bizMsg, glblMsg, i)) {
                        return;
                    }
                }
            }
        }
        // mod end 2022/05/30 QC#55970
   		
        //Search
        // mod start 2022/05/30 QC#55970
        //NMAL0150CommonLogic.search(bizMsg, getGlobalCompanyCode());
        NMAL0150CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode());
        // mod end 2022/05/30 QC#55970
        
        //NMAM8182I=0,[@] process ended normally.
		bizMsg.setMessageInfo("NMAM8182I", new String[]{"Cost History Update"});
   		
    }
    
    private boolean checkInput_Header(NMAL0150CMsg bizMsg) {
    	
        boolean errorFlg = false;
        
        // Cost Update Group Duplicated Check
		S21SsmEZDResult rs = NMAL0150Query.getInstance().getCostUpdateGroupCount(getGlobalCompanyCode(), bizMsg.mdseCstUpdGrpTxt_H1.getValue(), bizMsg.mdseCstUpdHistHdrPk_H1.getValue());
        if (rs.isCodeNormal()) {
            Integer count = (Integer) rs.getResultObject();
            if (count > 0) {
            	errorFlg = true;
        		// ZZPM0027E=0,Corresponding data already exists.
        		bizMsg.mdseCstUpdGrpTxt_H1.setErrorInfo(1, "ZZPM0027E");
            }
        }
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }

    // mod start 2022/05/30 QC#55970
    //private boolean checkInput_Detail(NMAL0150CMsg bizMsg) {
    private boolean checkInput_Detail(NMAL0150CMsg bizMsg, NMAL0150SMsg glblMsg) {
    // mod end 2022/05/30 QC#55970
    	
        boolean errorFlg = false;
        
        MDSE_CST_UPD_TPTMsg mdseCstUpdTpTMsg = new MDSE_CST_UPD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdTpTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(mdseCstUpdTpTMsg.mdseCstUpdTpCd, bizMsg.mdseCstUpdTpCd_H1);
        mdseCstUpdTpTMsg = (MDSE_CST_UPD_TPTMsg) S21CacheTBLAccessor.findByKey(mdseCstUpdTpTMsg);
        if (mdseCstUpdTpTMsg == null) {
    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
           	bizMsg.setMessageInfo("NDAM0007E", new String[]{"MDSE_CST_UPD_TP", "MDSE_CST_UPD_TP_CD", bizMsg.mdseCstUpdTpCd_H1.getValue()});
           	return true;
        }
        
        // add start 2022/05/30 QC#55970
        if (glblMsg.A.getValidCount() <= 0) {
            // NMAM8190E=0,Detail requires at least one line. Please enter.
            bizMsg.setMessageInfo("NMAM8190E");
            return true;
        }

        List<String> dupChkList = new ArrayList<String>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1)) {
                // ZZM9000E=0,[@] field is mandatory.
                glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Item Number" });
                errorFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).rqstTotStdCostAmt_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).pkgUomCd_A1)
                    && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).rqstTotStdCostAmt_A1)) {
                // ZZM9000E=0,[@] field is mandatory.
                glblMsg.A.no(i).rqstTotStdCostAmt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Unit Cost" });
                errorFlg = true;
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).rqstTotStdCostAmt_A1)) {
                if (MAXIMUM_AMOUNT.compareTo(glblMsg.A.no(i).rqstTotStdCostAmt_A1.getValue()) < 0 || (glblMsg.A.no(i).rqstTotStdCostAmt_A1.getValue().scale() > 2)) {
                    // ZZM9002E=0,[@] field exceeded maximum value.
                    glblMsg.A.no(i).rqstTotStdCostAmt_A1.setErrorInfo(1, "ZZM9002E", new String[] {"Unit Cost" });
                    errorFlg = true;
                }

            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
                // ZZM9000E=0,[@] field is mandatory.
                glblMsg.A.no(i).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Effective Date" });
                errorFlg = true;
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
                String d = glblMsg.A.no(i).mdseCstUpdEffFromDt_A1.getValue();
                if (d != null && d.length() == 8) {
                    String year = d.substring(0, 4);
                    String month = d.substring(4, 6);
                    String day = d.substring(6, 8);
                    if (EZDCommonFunc.checkDate(year, month, day) == false) {
                        // NMAM8192E=0,[@] field incorrect date.
                        glblMsg.A.no(i).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8192E", new String[] {"Effective Date" });
                        errorFlg = true;
                    }
                } else {
                    // NMAM8192E=0,[@] field incorrect date.
                    glblMsg.A.no(i).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8192E", new String[] {"Effective Date" });
                    errorFlg = true;
                }
            }

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1)) {
                if (dupChkList.contains(glblMsg.A.no(i).mdseCd_A1.getValue())) {
                    // NMAM0072E=0, @ is duplicated.
                    glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number" });
                    errorFlg = true;
                } else {
                    dupChkList.add(glblMsg.A.no(i).mdseCd_A1.getValue());
                }
            }
        }
        if (errorFlg) {
            return true;
        }
        // add end 2022/05/30 QC#55970

        // MDSE exists check
        // mod start 2022/05/30 QC#55970
//      for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//      // mod end 2022/05/30 QC#55970
//        MDSETMsg mdseTmsg = NMAL0150CommonLogic.findMdse(getGlobalCompanyCode(), bizMsg.A.no(i).mdseCd_A1.getValue());
//        if (mdseTmsg == null) {
//            // NMAM0163E=0,The entered [@] does not exist in [@].
//            bizMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM0163E", new String[]{"Item Number", "Merchandise Master"});
//            errorFlg = true;
//        }
//        
//        //Standard Cost Exists Check
//        S21SsmEZDResult rs = NMAL0150Query.getInstance().getStdCostExistsCount(getGlobalCompanyCode(), 
//                bizMsg.A.no(i).mdseCd_A1.getValue(), bizMsg.mdseCstUpdHistHdrPk_H1.getValue());
//        if (rs.isCodeNormal()) {
//            Integer count = (Integer) rs.getResultObject();
//            if (count <= 0) {
//                //NMAM8194E=0,Standard Cost does not exist. Please entry Standard Cost at first.
//                bizMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM8194E");
//                errorFlg = true;
//            }
//        }
//        
//        rs = NMAL0150Query.getInstance().getItemCostStatusExistsCount(getGlobalCompanyCode(), 
//                bizMsg.A.no(i).mdseCd_A1.getValue(), bizMsg.mdseCstUpdHistHdrPk_H1.getValue(), mdseCstUpdTpTMsg.stdCostRelnFlg.getValue()); 
//        if (rs.isCodeNormal()) {
//            Integer count = (Integer) rs.getResultObject();
//            if (count > 0) {
//                //NMAM8196E=0,The request of approved or pending status exists. Please verify status of request of item number[@].
//                bizMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM8196E", new String[]{bizMsg.A.no(i).mdseCd_A1.getValue()});
//                errorFlg = true;
//            }
//        }
//      }
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            MDSETMsg mdseTmsg = NMAL0150CommonLogic.findMdse(getGlobalCompanyCode(), glblMsg.A.no(i).mdseCd_A1.getValue());
            if (mdseTmsg == null) {
                // NMAM0163E=0,The entered [@] does not exist in [@].
                glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM0163E", new String[] {"Item Number", "Merchandise Master" });
                errorFlg = true;
            }

            // PKG_UOM_CLS Exists Check
            S21SsmEZDResult result = NMAL0150Query.getInstance().getMdseInfoForBomTable(getGlobalCompanyCode(), glblMsg.A.no(i).mdseCd_A1.getValue());
            if (!result.isCodeNormal()) {
                // NMAM0163E=0,The entered [@] does not exist in [@].
                glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM0163E", new String[] {"Item Number", "Merchandise Master" });
                errorFlg = true;
            }

            // Standard Cost Exists Check
            S21SsmEZDResult rs = NMAL0150Query.getInstance().getStdCostExistsCount(getGlobalCompanyCode(), glblMsg.A.no(i).mdseCd_A1.getValue(), bizMsg.mdseCstUpdHistHdrPk_H1.getValue());
            if (rs.isCodeNormal()) {
                Integer count = (Integer) rs.getResultObject();
                if (count <= 0) {
                    //NMAM8194E=0,Standard Cost does not exist. Please entry Standard Cost at first.
                    glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM8194E");
                    errorFlg = true;
                }
            }

            rs = NMAL0150Query.getInstance().getItemCostStatusExistsCount(getGlobalCompanyCode(), glblMsg.A.no(i).mdseCd_A1.getValue(), bizMsg.mdseCstUpdHistHdrPk_H1.getValue(), mdseCstUpdTpTMsg.stdCostRelnFlg.getValue());
            if (rs.isCodeNormal()) {
                Integer count = (Integer) rs.getResultObject();
                if (count > 0) {
                    //NMAM8196E=0,The request of approved or pending status exists. Please verify status of request of item number[@].
                    glblMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM8196E", new String[] {glblMsg.A.no(i).mdseCd_A1.getValue() });
                    errorFlg = true;
                }
            }
        }
        // mod end 2022/05/30 QC#55970
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }

    // mod start 2022/05/30 QC#55970
	//private void doUpload_UPLOAD(NMAL0150CMsg bizMsg) {
	private void doUpload_UPLOAD(NMAL0150CMsg bizMsg, NMAL0150SMsg glblMsg) {
	// mod end 2022/05/30 QC#55970
		
		NMAL0150F00FMsg fMsg = new NMAL0150F00FMsg();
		int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
		// Mod Start 2016/11/21 QC#16082
		String filePath = bizMsg.xxFileData_H1.getTempFilePath();
		String csvFilePath = ZYPExcelUtil.excelToCsvFile(filePath);
//		EZDCSVInFile mappedFile = new EZDCSVInFile(bizMsg.xxFileData_H1.getTempFilePath(), fMsg, option);
		EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);
		// Mod End 2016/11/21 QC#16082
		try {
			int status = -1;
		    // mod start 2022/05/30 QC#55970
			//int totCount = bizMsg.A.getValidCount();
            int totCount = glblMsg.A.getValidCount();
		    // mod end 2022/05/30 QC#55970
			int uploadCount = 0;
			while ((status = mappedFile.read()) != 1) {
				totCount++;
				uploadCount++;
	            // mod start 2022/05/30 QC#55970
				//boolean isError = validateAndCopyToSMsg_UPLOAD(bizMsg, status, totCount, fMsg, uploadCount);
                boolean isError = validateAndCopyToSMsg_UPLOAD(bizMsg, glblMsg, status, totCount, fMsg, uploadCount);
	            // mod end 2022/05/30 QC#55970
				if (isError) {
					return ;
				}
				fMsg.clear();
			}
			if (uploadCount == 0) {
				//NMAM8193E=0,The Upload File is empty. Please confirm the content of the Upload file.
				bizMsg.setMessageInfo("NMAM8193E");
				return ;
			}
            // mod start 2022/05/30 QC#55970
			//bizMsg.A.setValidCount(totCount);
            glblMsg.A.setValidCount(totCount);
            NMAL0150CommonLogic.pagenation(bizMsg, glblMsg, 0);
            // mod end 2022/05/30 QC#55970
						
		} finally {
			mappedFile.close();
			bizMsg.xxFileData_H1.deleteTempFile();
			// Add Start 2016/11/21 QC#16082
			ZYPExcelUtil.deleteFile(csvFilePath);
			// Add End 2016/11/21 QC#16082
		}
	}

    // mod start 2022/05/30 QC#55970
	//private boolean validateAndCopyToSMsg_UPLOAD(NMAL0150CMsg bizMsg, int status, int totCount, NMAL0150F00FMsg fMsg, int uploadCount) {
	private boolean validateAndCopyToSMsg_UPLOAD(NMAL0150CMsg bizMsg, NMAL0150SMsg glblMsg, int status, int totCount, NMAL0150F00FMsg fMsg, int uploadCount) {
    // mod end 2022/05/30 QC#55970
        // mod start 2022/05/30 QC#55970
		//if (totCount > bizMsg.A.length()) {
        if (totCount > glblMsg.A.length()) {
        // mod end 2022/05/30 QC#55970
			//NMAM8197E=0,There are too many search results, there is data that cannot be uploaded.
    		bizMsg.setMessageInfo("NMAM8197E");
			return true;
		}
		int row = totCount-1;
		if (status == 1000) {
			//NMAM8191E=0,Error has occurred while uploading. Please verify the upload file format. (@)
    		bizMsg.setMessageInfo("NMAM8191E", new String[]{"Line="+String.valueOf(uploadCount)});
			return true;
		}
		int errCol1 = status - 1000;
		int errCol2 = status - 2000;
		// Item Number
		if (errCol1 == 1 || errCol2 == 1) {
			//NMAM8191E=0,Error has occurred while uploading. Please verify the upload file format. (@)
    		bizMsg.setMessageInfo("NMAM8191E", new String[]{"Item Number(Line="+String.valueOf(uploadCount)+")"});
			return true;
		// Unit Cost
		} else if (errCol1 == 2 || errCol2 == 2) {
			//NMAM8191E=0,Error has occurred while uploading. Please verify the upload file format. (@)
    		bizMsg.setMessageInfo("NMAM8191E", new String[]{"Unit Cost(Line="+String.valueOf(uploadCount)+")"});
			return true;
		// UOM
		} else if (errCol1 == 3 || errCol2 == 3) {
			//NMAM8191E=0,Error has occurred while uploading. Please verify the upload file format. (@)
    		bizMsg.setMessageInfo("NMAM8191E", new String[]{"UOM(Line="+String.valueOf(uploadCount)+")"});
			return true;
		// Effective Date
		} else if (errCol1 == 4 || errCol2 == 4) {
			//NMAM8191E=0,Error has occurred while uploading. Please verify the upload file format. (@)
    		bizMsg.setMessageInfo("NMAM8191E", new String[]{"Effective Date(Line="+String.valueOf(uploadCount)+")"});
			return true;
		}
		
        // mod start 2022/05/30 QC#55970
		//bizMsg.A.no(row).mdseCd_A1.setValue(fMsg.MDSE_CD_F1.getValue());
		glblMsg.A.no(row).mdseCd_A1.setValue(fMsg.MDSE_CD_F1.getValue());
        // mod end 2022/05/30 QC#55970
		if (ZYPCommonFunc.hasValue(fMsg.MDSE_CD_F1)) {
            // mod start 2022/05/30 QC#55970
			//NMAL0150CommonLogic.getMdseInfoForBomTable(bizMsg, getGlobalCompanyCode(), fMsg.MDSE_CD_F1.getValue(), row);
            NMAL0150CommonLogic.getMdseInfoForBomTable(glblMsg, getGlobalCompanyCode(), fMsg.MDSE_CD_F1.getValue(), row);
            // mod end 2022/05/30 QC#55970
		}
		//bizMsg.A.no(row).pkgUomCd_A1.setValue(fMsg.PKG_UOM_CD_F1.getValue());

		String d = fMsg.MDSE_CST_UPD_EFF_FROM_DT_F1.getValue();
		if (d != null && d.length() == 8) {
			String year = d.substring(0, 4);
			String month = d.substring(4, 6);
			String day = d.substring(6, 8);
			
			if (EZDCommonFunc.checkDate(year, month, day) == false) {
				//NMAM8192E=0,[@] field incorrect date.
	    		bizMsg.setMessageInfo("NMAM8192E", new String[]{"Effective Date(Line="+String.valueOf(uploadCount)+")"});
				return true;
			} else {
		        // mod start 2022/05/30 QC#55970
				//bizMsg.A.no(row).mdseCstUpdEffFromDt_A1.setValue(d);
                glblMsg.A.no(row).mdseCstUpdEffFromDt_A1.setValue(d);
		        // mod end 2022/05/30 QC#55970
			}
		}

		if (ZYPCommonFunc.hasValue(fMsg.RQST_TOT_STD_COST_AMT_F1)) {
			if (MAXIMUM_AMOUNT.compareTo(fMsg.RQST_TOT_STD_COST_AMT_F1.getValue()) < 0
					|| (fMsg.RQST_TOT_STD_COST_AMT_F1.getValue() != null && fMsg.RQST_TOT_STD_COST_AMT_F1.getValue().scale() > 2)) {
				//ZZM9002E=0,[@] field exceeded maximum value.
	    		bizMsg.setMessageInfo("ZZM9002E", new String[]{"Unit Cost(Line="+String.valueOf(uploadCount)+")"});
				return true;
			} else {
                // mod start 2022/05/30 QC#55970
				//bizMsg.A.no(row).rqstTotStdCostAmt_A1.setValue(fMsg.RQST_TOT_STD_COST_AMT_F1.getValue());
			    glblMsg.A.no(row).rqstTotStdCostAmt_A1.setValue(fMsg.RQST_TOT_STD_COST_AMT_F1.getValue());
                // mod end 2022/05/30 QC#55970
			}
		}
		
		return false;
	}
}
