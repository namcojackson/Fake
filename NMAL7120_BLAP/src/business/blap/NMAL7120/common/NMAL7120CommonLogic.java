package business.blap.NMAL7120.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7120.NMAL7120SMsg;
import business.blap.NMAL7120.NMAL7120CMsg;
import business.blap.NMAL7120.NMAL7120Query;
import business.blap.NMAL7120.constant.NMAL7120Constant;
import business.db.CSMP_CONTRTMsg;
import business.db.NUM_CONSTTMsg;
import business.db.NUM_CONSTTMsgArray;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3006,3008
 * 07/20/2016   FUJITSU         W.Honda         Update          #9690
 * 09/06/2017   Fujitsu         T.Aoi           Update          QC#20206(L3#269)
 * 07/11/2018   Fujitsu         M.Ishii         Update          QC#25557
 *</pre>
 */
public class NMAL7120CommonLogic implements NMAL7120Constant {

	public static void setInitialStatusPulldown(NMAL7120CMsg bizMsg, String globalCompanyCode) {
		
		int i = 0;
    	bizMsg.csmpContrActvFlg_L1.no(i).setValue("Y");
    	bizMsg.xxWfProcStsNm_L1.no(i).setValue("Active");
        i++;
        bizMsg.csmpContrActvFlg_L1.no(i).setValue("N");
        bizMsg.xxWfProcStsNm_L1.no(i).setValue("Inactive");
        
        i++;
        bizMsg.csmpContrActvFlg_L1.no(i).setValue(EXPIERD);
        bizMsg.xxWfProcStsNm_L1.no(i).setValue("Expired");

        i = 0;
        bizMsg.csmpContrActvFlg_L2.no(i).setValue("Y");
        bizMsg.xxWfProcStsNm_L2.no(i).setValue("Active");
        i++;
        bizMsg.csmpContrActvFlg_L2.no(i).setValue("N");
        bizMsg.xxWfProcStsNm_L2.no(i).setValue("Inactive");
	}

    public static BigDecimal getSrchMaxSize(NMAL7120CMsg bizMsg, String glblCmpyCd) {

        NUM_CONSTTMsg numConstMsg = new NUM_CONSTTMsg();
        numConstMsg.setSQLID("001");
        numConstMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        numConstMsg.setConditionValue("numConstNm01", NMAL7120_MAX_SRCH_ROW_CNT);
        
        NUM_CONSTTMsgArray numConstArray = (NUM_CONSTTMsgArray) EZDTBLAccessor.findByCondition(numConstMsg);
        
        if (numConstArray == null || numConstArray.length() <= 0) {
            return BigDecimal.ONE;
        }
        
        numConstMsg = numConstArray.no(0);
        return numConstMsg.numConstVal.getValue();
    }
    
    public static BigDecimal getDplyMaxSize(NMAL7120CMsg bizMsg, String glblCmpyCd) {

        NUM_CONSTTMsg numConstMsg = new NUM_CONSTTMsg();
        numConstMsg.setSQLID("001");
        numConstMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        numConstMsg.setConditionValue("numConstNm01", NMAL7120_MAX_DPLY_ROW_CNT);
        
        NUM_CONSTTMsgArray numConstArray = (NUM_CONSTTMsgArray) EZDTBLAccessor.findByCondition(numConstMsg);
        
        if (numConstArray == null || numConstArray.length() <= 0) {
            return BigDecimal.ONE;
        }
        
        numConstMsg = numConstArray.no(0);
        return numConstMsg.numConstVal.getValue();
    }

    public static boolean deleteDetail(String globalCompanyCode, NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg, int i) {

    	CSMP_CONTRTMsg detail = new CSMP_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.csmpContrPk, sMsg.B.no(i).csmpContrPk_B1);
        detail = (CSMP_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(detail);
        if (detail != null) {
            String tmpTimeZone = detail.ezUpTimeZone.getValue();
            String tmpTime = detail.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.B.no(i).ezUpTime_B1.getValue(), sMsg.B.no(i).ezUpTimeZone_B1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"CSMP_CONTR", "CSMP_CONTR_PK", String.valueOf(sMsg.B.no(i).csmpContrPk_B1.getValueInt())});
                return false;
            }
        }
        detail = new CSMP_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.csmpContrPk, sMsg.B.no(i).csmpContrPk_B1);
        EZDTBLAccessor.logicalRemove(detail);
        if (!RETURN_CD_NORMAL.equals(detail.getReturnCode())) {
        	//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0015E", new String[]{"CSMP_CONTR", "CSMP_CONTR_PK", String.valueOf(sMsg.B.no(i).csmpContrPk_B1.getValueInt())});
            return false;
        }
        
        return true;

    }
    
    public static boolean updateDetail(String globalCompanyCode, NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg, int i) {
    	CSMP_CONTRTMsg detail = new CSMP_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.csmpContrPk, sMsg.A.no(i).csmpContrPk_A1);

        detail = (CSMP_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(detail);
        if (detail != null) {
            String tmpTimeZone = detail.ezUpTimeZone.getValue();
            String tmpTime = detail.ezUpTime.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue(), tmpTime, tmpTimeZone)) {
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM8175E", new String[]{"CSMP_CONTR", "CSMP_CONTR_PK", String.valueOf(sMsg.A.no(i).csmpContrPk_A1.getValueInt())});
                return false;
            }
        }
        ZYPEZDItemValueSetter.setValue(detail.dsAcctNum, sMsg.A.no(i).dsAcctNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.dsAcctNm, sMsg.A.no(i).dsAcctNm_A1);
        ZYPEZDItemValueSetter.setValue(detail.csmpNum, sMsg.A.no(i).csmpNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.dlrRefNum, sMsg.A.no(i).dlrRefNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.prcCatgCd, sMsg.A.no(i).prcCatgCd_A1);
        ZYPEZDItemValueSetter.setValue(detail.prcContrNum, sMsg.A.no(i).prcContrNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.origCoaBrCd, sMsg.A.no(i).origCoaBrCd_A1);
        ZYPEZDItemValueSetter.setValue(detail.rtlDlrCd, sMsg.A.no(i).rtlDlrCd_A1);
        ZYPEZDItemValueSetter.setValue(detail.effFromDt, sMsg.A.no(i).effFromDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.effThruDt, sMsg.A.no(i).effThruDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.cusaRejDt, sMsg.A.no(i).cusaRejDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.erlTrmnDt, sMsg.A.no(i).erlTrmnDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.rnwCsmpNum, sMsg.A.no(i).rnwCsmpNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.uplftEquipRatio, sMsg.A.no(i).uplftEquipRatio_A1);
        ZYPEZDItemValueSetter.setValue(detail.uplftAsryRatio, sMsg.A.no(i).uplftAsryRatio_A1);
        ZYPEZDItemValueSetter.setValue(detail.csmpNumCmntTxt, sMsg.A.no(i).csmpNumCmntTxt_A1);
        // 2016/07/20 CSA-QC#9690 Add Start
        ZYPEZDItemValueSetter.setValue(detail.custMbrId, sMsg.A.no(i).custMbrId_A1);
        // 2016/07/20 CSA-QC#9690 Add end
        ZYPEZDItemValueSetter.setValue(detail.csmpContrActvFlg, getFlgValueFromFields(sMsg.A.no(i).csmpContrActvFlg_A1.getValue()));

        S21FastTBLAccessor.update(detail);
        if (!RETURN_CD_NORMAL.equals(detail.getReturnCode())) {
            bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "ZZMM0015E", new String[]{"CSMP_CONTR", "CSMP_CONTR_PK", String.valueOf(bizMsg.A.no(i).csmpContrPk_A1.getValueInt())});
            return false;

        }
        return true;
        
    }
    	
    public static boolean insertDetail(String globalCompanyCode, NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg, int i) {
    	CSMP_CONTRTMsg detail = new CSMP_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.csmpContrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_CONTR_SQ));
        ZYPEZDItemValueSetter.setValue(detail.dsAcctNum, sMsg.A.no(i).dsAcctNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.dsAcctNm, sMsg.A.no(i).dsAcctNm_A1);
        ZYPEZDItemValueSetter.setValue(detail.csmpNum, sMsg.A.no(i).csmpNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.dlrRefNum, sMsg.A.no(i).dlrRefNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.prcCatgCd, sMsg.A.no(i).prcCatgCd_A1);
        ZYPEZDItemValueSetter.setValue(detail.prcContrNum, sMsg.A.no(i).prcContrNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.origCoaBrCd, sMsg.A.no(i).origCoaBrCd_A1);
        ZYPEZDItemValueSetter.setValue(detail.rtlDlrCd, sMsg.A.no(i).rtlDlrCd_A1);
        ZYPEZDItemValueSetter.setValue(detail.effFromDt, sMsg.A.no(i).effFromDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.effThruDt, sMsg.A.no(i).effThruDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.cusaRejDt, sMsg.A.no(i).cusaRejDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.erlTrmnDt, sMsg.A.no(i).erlTrmnDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.rnwCsmpNum, sMsg.A.no(i).rnwCsmpNum_A1);
        ZYPEZDItemValueSetter.setValue(detail.uplftEquipRatio, sMsg.A.no(i).uplftEquipRatio_A1);
        ZYPEZDItemValueSetter.setValue(detail.uplftAsryRatio, sMsg.A.no(i).uplftAsryRatio_A1);
        ZYPEZDItemValueSetter.setValue(detail.csmpNumCmntTxt, sMsg.A.no(i).csmpNumCmntTxt_A1);
        // 2016/07/20 CSA-QC#9690 Add Start
        ZYPEZDItemValueSetter.setValue(detail.custMbrId, sMsg.A.no(i).custMbrId_A1);
        // 2016/07/20 CSA-QC#9690 Add end
        ZYPEZDItemValueSetter.setValue(detail.csmpContrActvFlg, getFlgValueFromFields(sMsg.A.no(i).csmpContrActvFlg_A1.getValue()));
        EZDTBLAccessor.create(detail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detail.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "ZZMM0001E", new String[]{"CSMP_CONTR", "CSMP_CONTR_PK", String.valueOf(detail.csmpContrPk.getValueInt())});
            return false;
        }
        return true;
        
    }
    
    public static void search(NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg, String globalCompanyCode) {
        
        bizMsg.xxChkBox_AL.clear();
        bizMsg.xxMsgTxt.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);

        if (EXPIERD.equals(bizMsg.csmpContrActvFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeNm13Txt_H1, EXPIERD_TXT);
            bizMsg.csmpContrActvFlg_H1.clear();
        }

        Integer totCount = 0;
		S21SsmEZDResult rs = NMAL7120Query.getInstance().searchTotCnt(bizMsg, globalCompanyCode);
        if (rs.isCodeNormal()) {
            totCount = (Integer) rs.getResultObject();
        }
        if (totCount > 0) {
        
        
        S21SsmEZDResult result = NMAL7120Query.getInstance().search(bizMsg, sMsg, globalCompanyCode);

        if (result.isCodeNormal()) {

            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > bizMsg.xxDtlCnt_H1.getValueInt()) {
                
                // NMAM8351I=0 First [@] rows are currently displayed. Please use the filter to narrow down the search results.
                //bizMsg.setMessageInfo("NMAM8351I", new String[] {String.valueOf(bizMsg.xxDtlCnt_H1.getValueInt())} );NZZM0001W
                bizMsg.setMessageInfo("NZZM0001W");

                //queryResCnt = sMsg.A.length();
                queryResCnt = bizMsg.xxDtlCnt_H1.getValueInt();
                sMsg.A.no(bizMsg.xxDtlCnt_H1.getValueInt()).clear();
                bizMsg.xxPageShowOfNum_10.setValue(bizMsg.xxDtlCnt_H1.getValueInt());
                sMsg.A.setValidCount(bizMsg.xxDtlCnt_H1.getValueInt());
            } else {
                //ZZM8100I=0,Process ended normally.
                bizMsg.setMessageInfo("ZZM8100I");
                bizMsg.xxPageShowOfNum_10.setValue(queryResCnt);
            }
            
            int i = 0;
            int bzNum = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i < bizMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                EZDMsg.copy(sMsg.A.no(i), "A1", sMsg.C.no(i), "C1");
            }
            if (i > bizMsg.A.length()) {
                bzNum = bizMsg.A.length();
            } else {
                bzNum = i;
            }
            
            bizMsg.A.setValidCount(bzNum);
            sMsg.A.setValidCount(sMsg.A.getValidCount());
            
         // Set page number
            bizMsg.xxPageShowFromNum_10.setValue(1);
            bizMsg.xxPageShowToNum_10.setValue(bizMsg.A.getValidCount());

          //S21_NA DEL START QC#20206(Sol#269)
//            //Backup
//            EZDMsg.copy( bizMsg.A, "A1", sMsg.B, "B1" );
//            bizMsg.A.setValidCount(queryResCnt);
//
//            //set message
//            if (totCount != queryResCnt) {
//	            String msg = "The first " + queryResCnt + " out of " + totCount + " rows are currently displayed. Please use Filter to reduce the number of rows.";
//	            bizMsg.xxMsgTxt.setValue(msg);
//            } else {
//	            String msg = queryResCnt + " out of " + totCount + " rows are currently displayed.";
//	            bizMsg.xxMsgTxt.setValue(msg);
//            }
          //S21_NA DEL END QC#20206(Sol#269)
            
            
        } else {
            
            //ZZZM9005W=0,No search results found.
            bizMsg.setMessageInfo("ZZZM9005W");
        }
        } else {
        	//ZZZM9005W=0,No search results found.
        	bizMsg.setMessageInfo("ZZZM9005W");
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxModeNm13Txt_H1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrActvFlg_H1, EXPIERD);
            bizMsg.xxModeNm13Txt_H1.clear();
        }
        
    }
    public static String nvlString(String arg) {
        
        if (ZYPCommonFunc.hasValue(arg)) {
            return arg;
        }
        return "";
        
    }
    
    public static String nvlFlg(String arg) {
        
        if (ZYPCommonFunc.hasValue(arg)) {
            return arg;
        }
        return "N";
        
    }
    
    public static BigDecimal nvlBigDecimal(BigDecimal arg) {

    	if (ZYPCommonFunc.hasValue(arg)) {
    		return arg;
    	}
    	return BigDecimal.ZERO;
    	
    }
    
    public static String getFlgValueFromFields(String flg) {
    	
    	return !ZYPCommonFunc.hasValue(flg) ? FLG_OFF_N : flg;    	
    	
    }
    
    public static String getDuplicatedKeyString(NMAL7120SMsg sMsg, int i) {
        String key = 
            NMAL7120CommonLogic.nvlString(sMsg.A.no(i).dsAcctNum_A1.getValue())
           +NMAL7120CommonLogic.nvlString(sMsg.A.no(i).csmpNum_A1.getValue())
           +NMAL7120CommonLogic.nvlString(sMsg.A.no(i).dlrRefNum_A1.getValue())
           +NMAL7120CommonLogic.nvlString(sMsg.A.no(i).effFromDt_A1.getValue())
           ;
        return key;
    }
    
    public static String getDuplicatedKeyStringWithoutDate(NMAL7120SMsg sMsg, int i) {
        String key = 
            NMAL7120CommonLogic.nvlString(sMsg.A.no(i).dsAcctNum_A1.getValue())
           +NMAL7120CommonLogic.nvlString(sMsg.A.no(i).csmpNum_A1.getValue())
           +NMAL7120CommonLogic.nvlString(sMsg.A.no(i).dlrRefNum_A1.getValue())
           ;
        return key;
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getDBDuplicated(NMAL7120SMsg cMsg, int i, String glblCmpyCd, List<BigDecimal> targetDeleteList, List<BigDecimal> excludeScreenMsgList) {
    	
     	S21SsmEZDResult result = NMAL7120Query.getInstance().getDBDuplicated(glblCmpyCd, cMsg.A.no(i), targetDeleteList, excludeScreenMsgList);
 		if (result.isCodeNormal()) {
 			return (Map<String, Object>) result.getResultObject();
 		}
 		return null;
    }
    
  //S21_NA ADD QC#20206(Sol#269)
    /**
     * update S message
     * @param cMsg NMAL7120CMsg
     * @param sMsg NMAL7120SMsg
     */
    public static void updateSMsg(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
        int ixG = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(ixG + i), null);
        }
    }

    /**
     * control pagenation
     * @param cMsg NMAL7120CMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg NMAL7120SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NMAL7120CMsg cMsg, EZDCMsgArray cMsgArray, NMAL7120SMsg sMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        cMsg.xxPageShowToNum_10.clear();
        cMsg.xxPageShowOfNum_10.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (cMsg.xxPageShowFromNum_10.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        cMsg.xxPageShowFromNum_10.setValue(startIndex + 1);
        cMsg.xxPageShowToNum_10.setValue(startIndex + cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum_10.setValue(sMsgArray.getValidCount());
    }
  //S21_NA END QC#20206(Sol#269)
    
    // 2018/07/11 QC#25557 Add Start
    public static String convertDateFormat(String dt) {
        return dt == null ? null : dt.replaceAll(NMAL7120Constant.DT_CONV_FORMAT[0], NMAL7120Constant.DT_CONV_FORMAT[1]);
    }
    // 2018/07/11 QC#25557 Add End
}
