package business.blap.NMAL0150.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.blap.NMAL0150.NMAL0150Query;
import business.blap.NMAL0150.NMAL0150SMsg;
import business.blap.NMAL0150.constant.NMAL0150Constant;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_HIST_HDRTMsg;
import business.db.PKG_UOMTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 11/09/2016   Fujitsu         R.Nakamura      Update          QC#2872
 * 2022/05/30   Hitachi         A.Kohinata      Update          QC#55970
 *</pre>
 */
public class NMAL0150CommonLogic implements NMAL0150Constant {

	@SuppressWarnings("unchecked")
	public static void setInitialCostTypePulldown(NMAL0150CMsg bizMsg, String globalCompanyCode) {
		
		S21SsmEZDResult result = NMAL0150Query.getInstance().getCostTypeList(bizMsg, globalCompanyCode);
		
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for (Map<String, Object> map : list) {
				if (map != null && map.get("MDSE_CST_UPD_TP_CD") != null) {
			    	bizMsg.mdseCstUpdTpCd_L1.no(i).setValue((String) map.get("MDSE_CST_UPD_TP_CD"));
			    	bizMsg.mdseCstUpdTpNm_L1.no(i).setValue((String) map.get("MDSE_CST_UPD_TP_NM"));
			    	i++;
				}
			}
		}
	}

//	@SuppressWarnings("unchecked")
//	public static void setInitialPkgUomPulldown(NMAL0150CMsg bizMsg, String globalCompanyCode) {
//		S21SsmEZDResult result = NMAL0150Query.getInstance().getPkgUomPullDownList(bizMsg, globalCompanyCode);
//		if (result.isCodeNormal()) {
//			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//			if (list == null || list.size() <= 0) {
//				return;
//			}
//			int i = 0;
//			for (Map<String, Object> map : list) {
//				if (map != null && map.get("PKG_UOM_CD") != null) {
//					ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomCd_BL.no(i), (String) map.get("PKG_UOM_CD"));
//					ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomNm_BL.no(i), (String) map.get("PKG_UOM_NM"));
//					i++;
//				}
//			}
//		}
//	}
    public static MDSE_CST_UPD_HIST_HDRTMsg findHeader(String glblCmpyCd, BigDecimal mdseCstUpdHistHdrPk) {
    	
    	MDSE_CST_UPD_HIST_HDRTMsg mdseCstUpdHistHdrTMsg = new MDSE_CST_UPD_HIST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
        return (MDSE_CST_UPD_HIST_HDRTMsg) S21CacheTBLAccessor.findByKey(mdseCstUpdHistHdrTMsg);
        
    }
    
    public static AUTH_PSNTMsgArray findAuthPsn(String psnCd) {

    	AUTH_PSNTMsg authPsnTMsg = new AUTH_PSNTMsg();
    	authPsnTMsg.setConditionValue("usrNm01", psnCd);
    	authPsnTMsg.setSQLID("052");
    	return (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(authPsnTMsg);

    }
    @SuppressWarnings("unchecked")
	public static void getMdseInfoForBomTable(NMAL0150CMsg bizMsg, String globalCompanyCode, String mdseCd, int row) {

		S21SsmEZDResult result = NMAL0150Query.getInstance().getMdseInfoForBomTable(globalCompanyCode, mdseCd);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map != null && map.get("MDSE_CD") != null) {
				ZYPEZDItemValueSetter.setValue(bizMsg.A.no(row).mdseDescShortTxt_A1, (String) map.get("MDSE_DESC_SHORT_TXT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.A.no(row).mdseItemTpNm_A1, (String) map.get("MDSE_ITEM_TP_NM"));
				ZYPEZDItemValueSetter.setValue(bizMsg.A.no(row).xxScrItem54Txt_A1, (String) map.get("COA_PROJ_DESC_TXT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.A.no(row).coaProdCd_A1, (String) map.get("COA_PROD_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.A.no(row).pkgUomCd_A1, (String) map.get("BASE_PKG_UOM_CD"));
			}
		}

	}

    // add start 2022/05/30 QC#55970
    public static void getMdseInfoForBomTable(NMAL0150SMsg glblMsg, String globalCompanyCode, String mdseCd, int row) {
        S21SsmEZDResult result = NMAL0150Query.getInstance().getMdseInfoForBomTable(globalCompanyCode, mdseCd);
        if (result.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) result.getResultObject();
            if (map != null && map.get("MDSE_CD") != null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(row).mdseDescShortTxt_A1, (String) map.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(row).mdseItemTpNm_A1, (String) map.get("MDSE_ITEM_TP_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(row).xxScrItem54Txt_A1, (String) map.get("COA_PROJ_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(row).coaProdCd_A1, (String) map.get("COA_PROD_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(row).pkgUomCd_A1, (String) map.get("BASE_PKG_UOM_CD"));
            }
        }
    }
    // add end 2022/05/30 QC#55970

    public static boolean deleteDetail(String globalCompanyCode, NMAL0150CMsg bizMsg, int i) {

    	MDSE_CST_UPD_HIST_DTLTMsg detail = new MDSE_CST_UPD_HIST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdHistHdrPk, bizMsg.mdseCstUpdHistHdrPk_H1);
        ZYPEZDItemValueSetter.setValue(detail.mdseCd, bizMsg.B.no(i).mdseCd_B1.getValue());
        detail = (MDSE_CST_UPD_HIST_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(detail);
        if (detail != null) {
            String tmpTimeZone = detail.ezUpTimeZone.getValue();
            String tmpTime = detail.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.B.no(i).ezUpTime_B1.getValue(), bizMsg.B.no(i).ezUpTimeZone_B1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD", String.valueOf(bizMsg.mdseCstUpdHistHdrPk_H1)+","+bizMsg.B.no(i).mdseCd_B1.getValue()});
                return false;
            }
        }
        detail = new MDSE_CST_UPD_HIST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdHistHdrPk, bizMsg.mdseCstUpdHistHdrPk_H1);
        ZYPEZDItemValueSetter.setValue(detail.mdseCd, bizMsg.B.no(i).mdseCd_B1.getValue());
        EZDTBLAccessor.remove(detail);
        if (!RETURN_CD_NORMAL.equals(detail.getReturnCode())) {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD", String.valueOf(bizMsg.mdseCstUpdHistHdrPk_H1)+","+bizMsg.B.no(i).mdseCd_B1.getValue()});
            return false;

        }
        
        return true;

    }
    
    public static boolean updateDetail(String globalCompanyCode, NMAL0150CMsg bizMsg, int i) {

    	MDSE_CST_UPD_HIST_DTLTMsg detail = new MDSE_CST_UPD_HIST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdHistHdrPk, bizMsg.mdseCstUpdHistHdrPk_H1);
        ZYPEZDItemValueSetter.setValue(detail.mdseCd, bizMsg.B.no(i).mdseCd_B1.getValue());
        detail = (MDSE_CST_UPD_HIST_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(detail);
        if (detail != null) {
            String tmpTimeZone = detail.ezUpTimeZone.getValue();
            String tmpTime = detail.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A1.getValue(), bizMsg.A.no(i).ezUpTimeZone_A1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD", String.valueOf(bizMsg.mdseCstUpdHistHdrPk_H1)+","+bizMsg.B.no(i).mdseCd_B1.getValue()});
                return false;
            }
        }

    	ZYPEZDItemValueSetter.setValue(detail.rqstTotStdCostAmt, bizMsg.A.no(i).rqstTotStdCostAmt_A1);
    	//ZYPEZDItemValueSetter.setValue(detail.pkgUomCd, bizMsg.A.no(i).pkgUomCd_A1);
    	ZYPEZDItemValueSetter.setValue(detail.pkgUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdEffFromDt, bizMsg.A.no(i).mdseCstUpdEffFromDt_A1);
        S21FastTBLAccessor.update(detail);
        if (!RETURN_CD_NORMAL.equals(detail.getReturnCode())) {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD", String.valueOf(bizMsg.mdseCstUpdHistHdrPk_H1)+","+bizMsg.B.no(i).mdseCd_B1.getValue()});
            return false;

        }
        return true;
        
    }
    
    // mod start 2022/05/30 QC#55970
//    public static boolean chkDetailListExists(NMAL0150CMsg bizMsg) {
//    	
//		boolean detailListChkFlg = false;
//		for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//			if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCd_A1) 
//					&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).rqstTotStdCostAmt_A1)
//					&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).pkgUomCd_A1)
//					&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCstUpdEffFromDt_A1)
//					) {
//				continue;
//			}
//			detailListChkFlg = true;
//		}
//		return detailListChkFlg;
//		
//	}
    public static boolean chkDetailListExists(NMAL0150SMsg glblMsg) {

        boolean detailListChkFlg = false;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCd_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).rqstTotStdCostAmt_A1) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
                continue;
            }
            detailListChkFlg = true;
        }
        return detailListChkFlg;
    }
    // mod end 2022/05/30 QC#55970
	
    // mod start 2022/05/30 QC#55970
//    public static boolean insertDetail(String globalCompanyCode, NMAL0150CMsg bizMsg, int i) {
//
//        MDSE_CST_UPD_HIST_DTLTMsg detail = new MDSE_CST_UPD_HIST_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
//        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdHistHdrPk, bizMsg.mdseCstUpdHistHdrPk_H1);
//        ZYPEZDItemValueSetter.setValue(detail.mdseCd, bizMsg.A.no(i).mdseCd_A1);
//        ZYPEZDItemValueSetter.setValue(detail.rqstTotStdCostAmt, bizMsg.A.no(i).rqstTotStdCostAmt_A1);
//        //ZYPEZDItemValueSetter.setValue(detail.pkgUomCd, bizMsg.A.no(i).pkgUomCd_A1);
//        ZYPEZDItemValueSetter.setValue(detail.pkgUomCd, PKG_UOM.EACH);
//        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdEffFromDt, bizMsg.A.no(i).mdseCstUpdEffFromDt_A1);
//        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdStsCd, MDSE_CST_UPD_STS.PENDING);
//        EZDTBLAccessor.create(detail);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detail.getReturnCode())) {
//        // ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
//            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD", String.valueOf(bizMsg.mdseCstUpdHistHdrPk_H1)+","+bizMsg.B.no(i).mdseCd_B1.getValue()});
//            return false;
//        }
//        return true;
//        
//    }
    public static boolean insertDetail(String globalCompanyCode, NMAL0150CMsg bizMsg, NMAL0150SMsg glblMsg, int i) {

        MDSE_CST_UPD_HIST_DTLTMsg detail = new MDSE_CST_UPD_HIST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(detail.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdHistHdrPk, bizMsg.mdseCstUpdHistHdrPk_H1);
        ZYPEZDItemValueSetter.setValue(detail.mdseCd, glblMsg.A.no(i).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(detail.rqstTotStdCostAmt, glblMsg.A.no(i).rqstTotStdCostAmt_A1);
        ZYPEZDItemValueSetter.setValue(detail.pkgUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdEffFromDt, glblMsg.A.no(i).mdseCstUpdEffFromDt_A1);
        ZYPEZDItemValueSetter.setValue(detail.mdseCstUpdStsCd, MDSE_CST_UPD_STS.PENDING);
        EZDTBLAccessor.create(detail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detail.getReturnCode())) {
            // ZZMM0001E=0,Data insert failure. [
            // TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[] {"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD", String.valueOf(bizMsg.mdseCstUpdHistHdrPk_H1) + "," + glblMsg.A.no(i).mdseCd_A1.getValue() });
            return false;
        }
        return true;
    }
    // mod end 2022/05/30 QC#55970

    @SuppressWarnings("unchecked")
    // mod start 2022/05/30 QC#55970
    //public static void search(NMAL0150CMsg bizMsg, String globalCompanyCode) {
    public static void search(NMAL0150CMsg bizMsg, NMAL0150SMsg glblMsg, String globalCompanyCode) {
    // mod end 2022/05/30 QC#55970
    	
    	if (!ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdHistHdrPk_H1)) {
    		return;
    	}
        MDSE_CST_UPD_HIST_HDRTMsg headerTMsg = NMAL0150CommonLogic.findHeader(globalCompanyCode, bizMsg.mdseCstUpdHistHdrPk_H1.getValue());
        if (headerTMsg != null) {
			// set Header
        	ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H1, headerTMsg.ezUpTime);
        	ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H1, headerTMsg.ezUpTimeZone);
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdTpCd_H1, headerTMsg.mdseCstUpdTpCd);
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdGrpTxt_H1, headerTMsg.mdseCstUpdGrpTxt);
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdDescTxt_H1, headerTMsg.mdseCstUpdDescTxt);
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdRefTxt_H1, headerTMsg.mdseCstUpdRefTxt);
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdRefId_H1, headerTMsg.mdseCstUpdRefId);
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdCratPsnCd_H1, headerTMsg.mdseCstUpdCratPsnCd);
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdCratDt_H1, headerTMsg.mdseCstUpdCratDt);
        	// Add Start 2016/11/09 QC#2872
        	// set who Column
        	ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs, headerTMsg.ezInTime);
        	ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(headerTMsg.ezInUserID.getValue()));
        	ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs, headerTMsg.ezUpTime);
        	ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(headerTMsg.ezUpUserID.getValue()));
        	ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm, headerTMsg.ezTableID);
        	// Add End 2016/11/09 QC#2872
        }
        if (ZYPCommonFunc.hasValue(headerTMsg.mdseCstUpdCratPsnCd)) {
	        AUTH_PSNTMsgArray resultAuthPsnTMsgArray = NMAL0150CommonLogic.findAuthPsn(headerTMsg.mdseCstUpdCratPsnCd.getValue());
	        if (resultAuthPsnTMsgArray != null && resultAuthPsnTMsgArray.length() > 0) {
	        	AUTH_PSNTMsg authPsnTMsg = resultAuthPsnTMsgArray.no(0);
	        	if (authPsnTMsg != null) {
	            	ZYPEZDItemValueSetter.setValue(bizMsg.xxFullNm_H1, 
	            			headerTMsg.mdseCstUpdCratPsnCd.getValue() 
	            			+ " : " 
	            			+ nvlString(authPsnTMsg.firstNm.getValue()) 
	            			+ " " 
	            			+ nvlString(authPsnTMsg.lastNm.getValue()));
	        	}
	        }
        }
        
		ZYPTableUtil.clear(bizMsg.A);
		ZYPTableUtil.clear(bizMsg.B);
        // add start 2022/05/30 QC#55970
        ZYPTableUtil.clear(glblMsg.A);
        bizMsg.setCommitSMsg(true);
        // add end 2022/05/30 QC#55970
        // mod start 2022/05/30 QC#55970
//		S21SsmEZDResult result = NMAL0150Query.getInstance().getMdseCstUpdDetail(bizMsg, globalCompanyCode);
//		if (result.isCodeNormal()) {
//			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//			if (list != null && list.size() > 0) {
//				int queryResCnt = list.size();
//				if (queryResCnt > bizMsg.A.length()) {
//					//NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
//	            	bizMsg.setMessageInfo("NZZM0001W");
//	                queryResCnt = bizMsg.A.length();
//				}
//				int i = 0;
//				for ( ; i < queryResCnt; i++) {
//					Map<String, Object> map = (Map<String, Object>) list.get(i);
//					if (map != null && map.get("MDSE_CST_UPD_HIST_HDR_PK") != null) {
//						// set Detail
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseCd_A1, (String) map.get("MDSE_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseCd_AB, (String) map.get("MDSE_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseDescShortTxt_A1, (String) map.get("MDSE_DESC_SHORT_TXT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseItemTpNm_A1, (String) map.get("MDSE_ITEM_TP_NM"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxScrItem54Txt_A1, (String) map.get("COA_PROJ_DESC_TXT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaProdCd_A1, (String) map.get("COA_PROD_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).rqstTotStdCostAmt_A1, (BigDecimal) map.get("RQST_TOT_STD_COST_AMT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).pkgUomCd_A1, (String) map.get("PKG_UOM_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseCstUpdEffFromDt_A1, (String) map.get("MDSE_CST_UPD_EFF_FROM_DT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseCstUpdStsNm_A1, (String) map.get("MDSE_CST_UPD_STS_NM"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTime_A1, (String) map.get("EZUPTIME"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTimeZone_A1, (String) map.get("EZUPTIMEZONE"));
//						// Add Start 2016/11/09 QC#2872
//			            // set who Column
//			            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratTs_A1, (String) map.get("XX_REC_HIST_CRAT_TS"));
//			            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM")));
//			            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdTs_A1, (String) map.get("XX_REC_HIST_UPD_TS"));
//			            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM")));
//			            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistTblNm_A1, (String) map.get("XX_REC_HIST_TBL_NM"));
//			            // Add End 2016/11/09 QC#2872
//						
//						// set Detail Backup
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).mdseCd_B1, (String) map.get("MDSE_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).mdseCd_BB, (String) map.get("MDSE_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).mdseDescShortTxt_B1, (String) map.get("MDSE_DESC_SHORT_TXT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).mdseItemTpNm_B1, (String) map.get("MDSE_ITEM_TP_NM"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxScrItem54Txt_B1, (String) map.get("COA_PROJ_DESC_TXT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).coaProdCd_B1, (String) map.get("COA_PROD_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rqstTotStdCostAmt_B1, (BigDecimal) map.get("RQST_TOT_STD_COST_AMT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).pkgUomCd_B1, (String) map.get("PKG_UOM_CD"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).mdseCstUpdEffFromDt_B1, (String) map.get("MDSE_CST_UPD_EFF_FROM_DT"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).mdseCstUpdStsNm_B1, (String) map.get("MDSE_CST_UPD_STS_NM"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ezUpTime_B1, (String) map.get("EZUPTIME"));
//						ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ezUpTimeZone_B1, (String) map.get("EZUPTIMEZONE"));
//
//					}
//				}
//				bizMsg.A.setValidCount(i);
//				bizMsg.B.setValidCount(i);
//				
//				bizMsg.xxPageShowTotNum_10.setValue(queryResCnt);
//			}
//			bizMsg.xxPageShowTotNum_10.setValue(0);
//		}
        S21SsmEZDResult result = NMAL0150Query.getInstance().getMdseCstUpdDetail(bizMsg, glblMsg, globalCompanyCode);
        if (result.isCodeNormal()) {

            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                // NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = glblMsg.A.length();
            }

            int i = 0;
            for (; i < queryResCnt; i++) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(glblMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(glblMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
            }
            NMAL0150CommonLogic.pagenation(bizMsg, glblMsg, 0);
            // ZZM8100I=0,Process ended normally.
            bizMsg.setMessageInfo("ZZM8100I");
        } else {
            bizMsg.xxPageShowFromNum_10.clear();
            bizMsg.xxPageShowFromNum_BK.clear();
            bizMsg.xxPageShowToNum_10.clear();
            bizMsg.xxPageShowOfNum_10.clear();
            // NZZM0000E=0,No search results found.
            bizMsg.setMessageInfo("NZZM0000E");
        }
        // mod end 2022/05/30 QC#55970
    }
    public static String nvlString(String arg) {
    	
    	if (ZYPCommonFunc.hasValue(arg)) {
    		return arg;
    	}
    	return "";
    	
    }
    public static BigDecimal nvlBigDecimal(BigDecimal arg) {

    	if (ZYPCommonFunc.hasValue(arg)) {
    		return arg;
    	}
    	return BigDecimal.ZERO;
    	
    }
    public static MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {

    	MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        
    }
    public static PKG_UOMTMsg findPkgUom(String glblCmpyCd, String pkgUomCd) {

    	PKG_UOMTMsg pkgUomTMsg = new PKG_UOMTMsg();
        ZYPEZDItemValueSetter.setValue(pkgUomTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pkgUomTMsg.pkgUomCd, pkgUomCd);
        return (PKG_UOMTMsg) S21CacheTBLAccessor.findByKey(pkgUomTMsg);
        
    }

    // add start 2022/05/30 QC#55970
    public static void copyCMsgToSMsg(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        int rowIndex = cMsg.xxPageShowFromNum_BK.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(rowIndex + i), null);
        }
    }

    public static void pagenation(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg, int rowIndex) {
        int startIndex = (rowIndex / cMsg.A.length()) * cMsg.A.length();
        int cMsgIndex = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (cMsgIndex >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(cMsgIndex), null);
            cMsgIndex++;
        }
        cMsg.A.setValidCount(cMsgIndex);
        cMsg.xxPageShowFromNum_10.setValue(startIndex + 1);
        cMsg.xxPageShowFromNum_BK.setValue(startIndex + 1);
        cMsg.xxPageShowToNum_10.setValue(startIndex + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_10.setValue(sMsg.A.getValidCount());
    }

    public static final int getFirstErrorIndex(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        int errIndex = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (sMsg.A.no(i).mdseCd_A1.isError()) {
                errIndex = i;
                break;
            }
        }
        return errIndex;
    }
    // add end 2022/05/30 QC#55970
}
