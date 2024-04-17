package business.blap.NWAL2040.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;

import business.blap.NWAL2040.NWAL2040CMsg;
import business.blap.NWAL2040.NWAL2040_BCMsg;
import business.blap.NWAL2040.NWAL2040Query;
import business.blap.NWAL2040.NWAL2040SMsg;
import business.blap.NWAL2040.constant.NWAL2040Constant;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_DEF_WHTMsg;
import business.db.ORD_RMA_DEF_WHTMsg;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2016/03/23   Hitachi         K.Kojima        Update          S21 NA Unit Test #56  Add Upload Function
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 * 2017/09/25   Fujitsu         T.Ogura         Update          QC#21369, 21375
 *</pre>
 */
public class NWAL2040CommonLogic implements NWAL2040Constant {
	@SuppressWarnings("unchecked")
	public static void setInitialDefWhMapTmplPulldown(NWAL2040CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().setInitialDefWhMapTmplPulldown(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for (Map<String, Object> map : list) {
				if (map != null && map.get("DEF_WH_MAP_TMPL_CD") != null) {
			    	bizMsg.defWhMapTmplCd_L1.no(i).setValue((String) map.get("DEF_WH_MAP_TMPL_CD"));
			    	bizMsg.defWhMapTmplNm_L1.no(i).setValue((String) map.get("DEF_WH_MAP_TMPL_NM"));
			    	i++;
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void setInitialOrdLineSrcPulldownInternal(NWAL2040CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().setInitialOrdLineSrcPulldown(bizMsg, globalCompanyCode, ORD_LINE_SRC_CATG.INTERNAL);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for (Map<String, Object> map : list) {
				if (map != null && map.get("ORD_LINE_SRC_CD") != null) {
			    	bizMsg.ordLineSrcCd_L1.no(i).setValue((String) map.get("ORD_LINE_SRC_CD"));
			    	bizMsg.ordLineSrcNm_L1.no(i).setValue((String) map.get("ORD_LINE_SRC_NM"));
			    	i++;
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void setInitialMdseItemClsTpPulldown(NWAL2040CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().setInitialMdseItemClsTpPulldown(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 1;
	    	bizMsg.mdseItemClsTpCd_L1.no(0).setValue("*");
	    	bizMsg.mdseItemClsTpNm_L1.no(0).setValue("*");
			for (Map<String, Object> map : list) {
				if (map != null && map.get("MDSE_ITEM_CLS_TP_CD") != null) {
			    	bizMsg.mdseItemClsTpCd_L1.no(i).setValue((String) map.get("MDSE_ITEM_CLS_TP_CD"));
			    	bizMsg.mdseItemClsTpNm_L1.no(i).setValue((String) map.get("MDSE_ITEM_CLS_TP_NM"));
			    	i++;
				}
			}
		}
	}

	// 2017/09/12 QC#19805 Del Start
//	@SuppressWarnings("unchecked")
//	public static void setInitialOrdLineSrcPulldownReturn(NWAL2040CMsg bizMsg, String globalCompanyCode) {
//		S21SsmEZDResult result = NWAL2040Query.getInstance().setInitialOrdLineSrcPulldown(bizMsg, globalCompanyCode, ORD_LINE_SRC_CATG.RETURN);
//		if (result.isCodeNormal()) {
//			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//			if (list == null || list.size() <= 0) {
//				return;
//			}
//			int i = 0;
//			for (Map<String, Object> map : list) {
//				if (map != null && map.get("ORD_LINE_SRC_CD") != null) {
//			    	bizMsg.ordLineSrcCd_R1.no(i).setValue((String) map.get("ORD_LINE_SRC_CD"));
//			    	bizMsg.ordLineSrcNm_R1.no(i).setValue((String) map.get("ORD_LINE_SRC_NM"));
//			    	i++;
//				}
//			}
//		}
//	}
    // 2017/09/12 QC#19805 Del End

	@SuppressWarnings("unchecked")
	public static void setInitialOrdLineSrcPulldownIE(NWAL2040CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().setInitialOrdLineSrcPulldownIE(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for (Map<String, Object> map : list) {
				if (map != null && map.get("ORD_LINE_SRC_CD") != null) {
			    	bizMsg.ordLineSrcCd_D1.no(i).setValue((String) map.get("ORD_LINE_SRC_CD"));
			    	bizMsg.ordLineSrcNm_D1.no(i).setValue((String) map.get("ORD_LINE_SRC_NM"));
			    	i++;
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getDsOrdCatgAndDsOrdTp(String globalCompanyCode, String dsOrdCatgCd, String dsOrdTpCd) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().getDsOrdCatgAndDsOrdTp(globalCompanyCode, dsOrdCatgCd, dsOrdTpCd);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map == null || map.get("DS_ORD_CATG_CD") == null) {
				return null;
			}
			return map;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getDsOrdCatg(String globalCompanyCode, String dsOrdTpCd) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().getDsOrdCatg(globalCompanyCode, dsOrdTpCd);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map == null || map.get("DS_ORD_CATG_CD") == null) {
				return null;
			}
			return map;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getPhysWh(String globalCompanyCode, String physWhCd) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().getPhysWh(globalCompanyCode, physWhCd);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map == null || map.get("PHYS_WH_CD") == null) {
				return null;
			}
			return map;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getRtlSwhFromName(String globalCompanyCode, String rtlSwhNm) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().getRtlSwhFromName(globalCompanyCode, rtlSwhNm);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map == null || map.get("RTL_SWH_CD") == null) {
				return null;
			}
			return map;
		}
		return null;
	}
	
	
	public static void search(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, String glblCmpyCd, boolean afterUpdFlg) {

		//Tab
		if (!ZYPCommonFunc.hasValue(bizMsg.xxDplyTab_H1)) {
			return;
		} else if (TAB_SRC_CATG.equals(bizMsg.xxDplyTab_H1.getValue())) {
			ZYPTableUtil.clear(bizMsg.B);
			ZYPTableUtil.clear(bizMsg.D);
			ZYPTableUtil.clear(sMsg.B);
			ZYPTableUtil.clear(sMsg.D);
			//Search
			S21SsmEZDResult result = NWAL2040Query.getInstance().searchMapSrcCatg(bizMsg, sMsg, glblCmpyCd);
			if (result.isCodeNormal()) {
				
				int queryResCnt = result.getQueryResultCount();
	            if (queryResCnt > sMsg.B.length()) {
	            	//NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
	            	bizMsg.setMessageInfo("NZZM0001W");
	                queryResCnt = sMsg.B.length();
	            }
	            
				int i = 0;
				for( ; i < queryResCnt; i++ ) {
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).ordCatgBizCtxPk_D1, sMsg.B.no(i).ordCatgBizCtxPk_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).dsOrdCatgCd_D1, sMsg.B.no(i).dsOrdCatgCd_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).dsOrdTpCd_D1, sMsg.B.no(i).dsOrdTpCd_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).firstBizCtxAttrbTxt_D1, sMsg.B.no(i).firstBizCtxAttrbTxt_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).scdBizCtxAttrbTxt_D1, sMsg.B.no(i).scdBizCtxAttrbTxt_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).effFromDt_D1, sMsg.B.no(i).effFromDt_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).effThruDt_D1, sMsg.B.no(i).effThruDt_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).ezUpTime_D1, sMsg.B.no(i).ezUpTime_B1);
					ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).ezUpTimeZone_D1, sMsg.B.no(i).ezUpTimeZone_B1);
				}
				sMsg.D.setValidCount( i );
	            
				i = 0;
				for( ; i < queryResCnt; i++ ) {
					if( i == bizMsg.B.length() ) {
						break;
					}
					EZDMsg.copy( sMsg.B.no( i ), null, bizMsg.B.no( i ), null );
					setDsOrdTpPulldown(bizMsg.B.no( i ), glblCmpyCd, bizMsg.B.no( i ).dsOrdCatgCd_B1.getValue());
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).ordCatgBizCtxPk_D1, sMsg.B.no(i).ordCatgBizCtxPk_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dsOrdCatgCd_D1, sMsg.B.no(i).dsOrdCatgCd_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dsOrdTpCd_D1, sMsg.B.no(i).dsOrdTpCd_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).firstBizCtxAttrbTxt_D1, sMsg.B.no(i).firstBizCtxAttrbTxt_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).scdBizCtxAttrbTxt_D1, sMsg.B.no(i).scdBizCtxAttrbTxt_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).effFromDt_D1, sMsg.B.no(i).effFromDt_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).effThruDt_D1, sMsg.B.no(i).effThruDt_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).ezUpTime_D1, sMsg.B.no(i).ezUpTime_B1);
					ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).ezUpTimeZone_D1, sMsg.B.no(i).ezUpTimeZone_B1);

				}
				bizMsg.B.setValidCount( i );
				bizMsg.D.setValidCount( i );
	    		
	            // Set page number
				bizMsg.xxPageShowFromNum_10.setValue( 1 );
				bizMsg.xxPageShowToNum_10.setValue( bizMsg.B.getValidCount() );
				bizMsg.xxPageShowOfNum_10.setValue( queryResCnt );
				if (!afterUpdFlg) {
					//ZZM8100I=0,Process ended normally.
					bizMsg.setMessageInfo("ZZM8100I");
				}
			} else {
				bizMsg.xxPageShowFromNum_10.clear();
				bizMsg.xxPageShowToNum_10.clear();
				bizMsg.xxPageShowOfNum_10.clear();
				if (!afterUpdFlg) {
					//NZZM0000E=0,No search results found.
					bizMsg.setMessageInfo("NZZM0000E");
				}
			}
		} else if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
			ZYPTableUtil.clear(bizMsg.A);
			ZYPTableUtil.clear(bizMsg.C);
			ZYPTableUtil.clear(sMsg.A);
			ZYPTableUtil.clear(sMsg.C);
			//Search
			S21SsmEZDResult result = NWAL2040Query.getInstance().searchMapTmplQlfy(bizMsg, sMsg, glblCmpyCd);
			if (result.isCodeNormal()) {
				
				int queryResCnt = result.getQueryResultCount();
	            if (queryResCnt > sMsg.A.length()) {
	            	//NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
	            	bizMsg.setMessageInfo("NZZM0001W");
	                queryResCnt = sMsg.A.length();
	            }
	            
				int i = 0;
				for( ; i < queryResCnt; i++ ) {
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).defWhMapTmplCd_C1, sMsg.A.no(i).defWhMapTmplCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).mdseItemClsTpCd_C1, sMsg.A.no(i).mdseItemClsTpCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).fromPostCd_C1, sMsg.A.no(i).fromPostCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).toPostCd_C1, sMsg.A.no(i).toPostCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).onHndChkFlg_C1, sMsg.A.no(i).onHndChkFlg_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdPrimOnHndChkWhCd_C1, sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).rtlSwhNm_01, sMsg.A.no(i).rtlSwhNm_01);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdPrimOnHndChkSwhCd_C1, sMsg.A.no(i).otbdPrimOnHndChkSwhCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdPrimOnHndLinSrcCd_C1, sMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdScdOnHndChkWhCd_C1, sMsg.A.no(i).otbdScdOnHndChkWhCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).rtlSwhNm_02, sMsg.A.no(i).rtlSwhNm_02);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdScdOnHndChkSwhCd_C1, sMsg.A.no(i).otbdScdOnHndChkSwhCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdScdOnHndLineSrcCd_C1, sMsg.A.no(i).otbdScdOnHndLineSrcCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdDefWhCd_C1, sMsg.A.no(i).otbdDefWhCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).rtlSwhNm_03, sMsg.A.no(i).rtlSwhNm_03);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdDefSwhCd_C1, sMsg.A.no(i).otbdDefSwhCd_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).otbdDefLineSrcCd_C1, sMsg.A.no(i).otbdDefLineSrcCd_A1);
                    // 2017/09/12 QC#19805 Del Start
//					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).rmaDefWhCd_C1, sMsg.A.no(i).rmaDefWhCd_A1);
//					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).rtlSwhNm_04, sMsg.A.no(i).rtlSwhNm_04);
//					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).rmaDefSwhCd_C1, sMsg.A.no(i).rmaDefSwhCd_A1);
//					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).rmaDefLineSrcCd_C1, sMsg.A.no(i).rmaDefLineSrcCd_A1);
                    // 2017/09/12 QC#19805 Del End
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).thirdPtyItemFlg_C1, sMsg.A.no(i).thirdPtyItemFlg_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).ezUpTime_C1, sMsg.A.no(i).ezUpTime_A1);
					ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).ezUpTimeZone_C1, sMsg.A.no(i).ezUpTimeZone_A1);
				}
				sMsg.C.setValidCount( i );
				
				i = 0;
				for( ; i < queryResCnt; i++ ) {
					if( i == bizMsg.A.length() ) {
						break;
					}
					EZDMsg.copy( sMsg.A.no( i ), null, bizMsg.A.no( i ), null );
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).defWhMapTmplCd_C1, sMsg.A.no(i).defWhMapTmplCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseItemClsTpCd_C1, sMsg.A.no(i).mdseItemClsTpCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).fromPostCd_C1, sMsg.A.no(i).fromPostCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).toPostCd_C1, sMsg.A.no(i).toPostCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).onHndChkFlg_C1, sMsg.A.no(i).onHndChkFlg_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdPrimOnHndChkWhCd_C1, sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).rtlSwhNm_01, sMsg.A.no(i).rtlSwhNm_01);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdPrimOnHndChkSwhCd_C1, sMsg.A.no(i).otbdPrimOnHndChkSwhCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdPrimOnHndLinSrcCd_C1, sMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdScdOnHndChkWhCd_C1, sMsg.A.no(i).otbdScdOnHndChkWhCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).rtlSwhNm_02, sMsg.A.no(i).rtlSwhNm_02);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdScdOnHndChkSwhCd_C1, sMsg.A.no(i).otbdScdOnHndChkSwhCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdScdOnHndLineSrcCd_C1, sMsg.A.no(i).otbdScdOnHndLineSrcCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdDefWhCd_C1, sMsg.A.no(i).otbdDefWhCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).rtlSwhNm_03, sMsg.A.no(i).rtlSwhNm_03);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdDefSwhCd_C1, sMsg.A.no(i).otbdDefSwhCd_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).otbdDefLineSrcCd_C1, sMsg.A.no(i).otbdDefLineSrcCd_A1);
                    // 2017/09/12 QC#19805 Del Start
//					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).rmaDefWhCd_C1, sMsg.A.no(i).rmaDefWhCd_A1);
//					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).rtlSwhNm_04, sMsg.A.no(i).rtlSwhNm_04);
//					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).rmaDefSwhCd_C1, sMsg.A.no(i).rmaDefSwhCd_A1);
//					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).rmaDefLineSrcCd_C1, sMsg.A.no(i).rmaDefLineSrcCd_A1);
                    // 2017/09/12 QC#19805 Del End
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).thirdPtyItemFlg_C1, sMsg.A.no(i).thirdPtyItemFlg_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ezUpTime_C1, sMsg.A.no(i).ezUpTime_A1);
					ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ezUpTimeZone_C1, sMsg.A.no(i).ezUpTimeZone_A1);
				}
				bizMsg.A.setValidCount( i );
				bizMsg.C.setValidCount( i );
	    		
	            // Set page number
				bizMsg.xxPageShowFromNum_10.setValue( 1 );
				bizMsg.xxPageShowToNum_10.setValue( bizMsg.A.getValidCount() );
				bizMsg.xxPageShowOfNum_10.setValue( queryResCnt );
				if (!afterUpdFlg) {
					//ZZM8100I=0,Process ended normally.
					bizMsg.setMessageInfo("ZZM8100I");
				}
			} else {
				bizMsg.xxPageShowFromNum_10.clear();
				bizMsg.xxPageShowToNum_10.clear();
				bizMsg.xxPageShowOfNum_10.clear();
				if (!afterUpdFlg) {
					//ZZZM9005W=0,No search results found.
					bizMsg.setMessageInfo("ZZZM9005W");
				}
			}
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {
            ZYPTableUtil.clear(bizMsg.E);
            ZYPTableUtil.clear(bizMsg.F);
            ZYPTableUtil.clear(sMsg.E);
            ZYPTableUtil.clear(sMsg.F);
            // 2017/09/25 QC#21369 Add Start
            ZYPTableUtil.clear(sMsg.G);

            //Search(Non Filter)
            S21SsmEZDResult result = NWAL2040Query.getInstance().searchMapTmplQlfyRMA(bizMsg, sMsg, glblCmpyCd, false);
            if (result.isCodeNormal()) {
                int queryResCnt = result.getQueryResultCount();
                if (queryResCnt > sMsg.E.length()) {
                    queryResCnt = sMsg.E.length();
                }
                //sMsg.E --> sMsg.G
                EZDMsg.copy( sMsg.E, "E1", sMsg.G, "G1" );
                sMsg.G.setValidCount( queryResCnt );
            }
            // 2017/09/25 QC#21369 Add End

            //Search
            // 2017/09/25 QC#21369 Mod Start
//            result = NWAL2040Query.getInstance().searchMapTmplQlfyRMA(bizMsg, sMsg, glblCmpyCd);
            result = NWAL2040Query.getInstance().searchMapTmplQlfyRMA(bizMsg, sMsg, glblCmpyCd, true);
            // 2017/09/25 QC#21369 Mod End
            if (result.isCodeNormal()) {

                int queryResCnt = result.getQueryResultCount();
                if (queryResCnt > sMsg.E.length()) {
                    //NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
                    bizMsg.setMessageInfo("NZZM0001W");
                    queryResCnt = sMsg.E.length();
                }

                //sMsg.E --> sMsg.F
                EZDMsg.copy( sMsg.E, "E1", sMsg.F, "F1" );
                sMsg.F.setValidCount( queryResCnt );

                int i = 0;
                for( ; i < queryResCnt; i++ ) {
                    if( i == bizMsg.E.length() ) {
                        break;
                    }
                    //sMsg.E --> bizMsg.E
                    EZDMsg.copy( sMsg.E.no( i ), null, bizMsg.E.no( i ), null );
                    //sMsg.E --> bizMsg.F
                    EZDMsg.copy( sMsg.E.no( i ), "E1", bizMsg.F.no( i ), "F1" );
                }

                bizMsg.E.setValidCount( i );
                bizMsg.F.setValidCount( i );

                // Set page number
                bizMsg.xxPageShowFromNum_10.setValue( 1 );
                bizMsg.xxPageShowToNum_10.setValue( bizMsg.E.getValidCount() );
                bizMsg.xxPageShowOfNum_10.setValue( queryResCnt );
                if (!afterUpdFlg) {
                    //ZZM8100I=0,Process ended normally.
                    bizMsg.setMessageInfo("ZZM8100I");
                }
            } else {
                bizMsg.xxPageShowFromNum_10.clear();
                bizMsg.xxPageShowToNum_10.clear();
                bizMsg.xxPageShowOfNum_10.clear();
                if (!afterUpdFlg) {
                    //ZZZM9005W=0,No search results found.
                    bizMsg.setMessageInfo("ZZZM9005W");
                }
            }
         // 2017/09/12 QC#19805 Add End
		}
		//
	}
//    public static void copyFromSMsgToCMsgForA(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, boolean flg) {
//    	int currentPage = cMsg.xxPageShowCurNum_10.getValueInt();
//        int s              = (currentPage - 1) * cMsg.A.length();
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            EZDMsg.copy(cMsg.A.no( i ) , null, sMsg.A.no( s + i ), null );
//    	}
//        if (flg) {
//    		ZYPTableUtil.clear(cMsg.A);
//        }
//    }
//    public static void copyFromSMsgToCMsgForB(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, boolean flg) {
//    	int currentPage = cMsg.xxPageShowCurNum_10.getValueInt();
//        int s              = (currentPage - 1) * cMsg.B.length();
//        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//            EZDMsg.copy(cMsg.B.no( i ) , null, sMsg.B.no( s + i ), null );
//    	}
//        if (flg) {
//    		ZYPTableUtil.clear(cMsg.B);
//        }
//    }
    public static BigDecimal nvlBigDecimal(BigDecimal arg) {
    	
    	if (ZYPCommonFunc.hasValue(arg)) {
    		return arg;
    	}
    	return BigDecimal.ZERO;
    	
    }
	
    public static boolean deleteOrdCatgBizCtx(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {

    	ORD_CATG_BIZ_CTXTMsg ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();
    	ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.ordCatgBizCtxPk, sMsg.D.no(i).ordCatgBizCtxPk_D1);
    	ordCatgBizCtxTMsg = (ORD_CATG_BIZ_CTXTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordCatgBizCtxTMsg);
        if (ordCatgBizCtxTMsg != null) {
            String tmpTimeZone = ordCatgBizCtxTMsg.ezUpTimeZone.getValue();
            String tmpTime = ordCatgBizCtxTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.D.no(i).ezUpTime_D1.getValue(), sMsg.D.no(i).ezUpTimeZone_D1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_CATG_BIZ_CTX", "ORD_CATG_BIZ_CTX_PK", String.valueOf(ordCatgBizCtxTMsg.ordCatgBizCtxPk.getValueInt())});
                return false;
            }
        }
        ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.ordCatgBizCtxPk, sMsg.D.no(i).ordCatgBizCtxPk_D1);
        EZDTBLAccessor.logicalRemove(ordCatgBizCtxTMsg);
        if (!RETURN_CD_NORMAL.equals(ordCatgBizCtxTMsg.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_CATG_BIZ_CTX", "ORD_CATG_BIZ_CTX_PK", String.valueOf(ordCatgBizCtxTMsg.ordCatgBizCtxPk.getValueInt())});
            return false;
        }
        return true;

    }
    public static void updateOrdCatgBizCtx(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {
    	
    	ORD_CATG_BIZ_CTXTMsg ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();
    	ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.ordCatgBizCtxPk, sMsg.B.no(i).ordCatgBizCtxPk_B1);
    	ordCatgBizCtxTMsg = (ORD_CATG_BIZ_CTXTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordCatgBizCtxTMsg);
        if (ordCatgBizCtxTMsg != null) {
            String tmpTimeZone = ordCatgBizCtxTMsg.ezUpTimeZone.getValue();
            String tmpTime = ordCatgBizCtxTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.B.no(i).ezUpTime_B1.getValue(), sMsg.B.no(i).ezUpTimeZone_B1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_CATG_BIZ_CTX", "ORD_CATG_BIZ_CTX_PK", String.valueOf(ordCatgBizCtxTMsg.ordCatgBizCtxPk.getValueInt())});
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.dsOrdCatgCd, sMsg.B.no(i).dsOrdCatgCd_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.dsOrdTpCd, sMsg.B.no(i).dsOrdTpCd_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.firstBizCtxAttrbTxt, sMsg.B.no(i).firstBizCtxAttrbTxt_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.scdBizCtxAttrbTxt, sMsg.B.no(i).scdBizCtxAttrbTxt_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.thirdBizCtxAttrbTxt, sMsg.B.no(i).effFromDt_B1.getValue());
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.frthBizCtxAttrbTxt, sMsg.B.no(i).effThruDt_B1.getValue());
        S21FastTBLAccessor.update(ordCatgBizCtxTMsg);
        if (!RETURN_CD_NORMAL.equals(ordCatgBizCtxTMsg.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_CATG_BIZ_CTX", "ORD_CATG_BIZ_CTX_PK", String.valueOf(ordCatgBizCtxTMsg.ordCatgBizCtxPk.getValueInt())});
            return;
        }
        
    }
    public static boolean chkOrdCatgBizCtx(NWAL2040SMsg sMsg) {
    	
		boolean ordCatgBizCtxChkFlg = false;
		for (int i = 0; i < sMsg.B.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdCatgCd_B1)
					&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdTpCd_B1)
					&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).firstBizCtxAttrbTxt_B1)
					&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).scdBizCtxAttrbTxt_B1)
					&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).effFromDt_B1)
					) {
				continue;
			}
			ordCatgBizCtxChkFlg = true;
		}
		return ordCatgBizCtxChkFlg;
		
	}
    public static boolean insertOrdCatgBizCtx(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {

    	ORD_CATG_BIZ_CTXTMsg ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.ordCatgBizCtxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_CATG_BIZ_CTX_SQ));
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.ordCatgCtxTpCd, "DEFAULT_WH_TMPL");
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.dsOrdCatgCd, sMsg.B.no(i).dsOrdCatgCd_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.dsOrdTpCd, sMsg.B.no(i).dsOrdTpCd_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.firstBizCtxAttrbTxt, sMsg.B.no(i).firstBizCtxAttrbTxt_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.scdBizCtxAttrbTxt, sMsg.B.no(i).scdBizCtxAttrbTxt_B1);
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.thirdBizCtxAttrbTxt, sMsg.B.no(i).effFromDt_B1.getValue());
        ZYPEZDItemValueSetter.setValue(ordCatgBizCtxTMsg.frthBizCtxAttrbTxt, sMsg.B.no(i).effThruDt_B1.getValue());
        EZDTBLAccessor.create(ordCatgBizCtxTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordCatgBizCtxTMsg.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, "ZZMM0001E", new String[]{"ORD_CATG_BIZ_CTX", "ORD_CATG_BIZ_CTX_PK", String.valueOf(ordCatgBizCtxTMsg.ordCatgBizCtxPk.getValueInt())});
            return false;
        }
        return true;
        
    }
    public static boolean deleteOrdDefWh(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {

    	ORD_DEF_WHTMsg ordDefWhTMsg = new ORD_DEF_WHTMsg();
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.defWhMapTmplCd, sMsg.C.no(i).defWhMapTmplCd_C1);
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.mdseItemClsTpCd, sMsg.C.no(i).mdseItemClsTpCd_C1);
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.thirdPtyItemFlg, getFlgValueFromFields(sMsg.C.no(i).thirdPtyItemFlg_C1.getValue()));
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.fromPostCd, sMsg.C.no(i).fromPostCd_C1);
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.toPostCd, sMsg.C.no(i).toPostCd_C1);
    	ordDefWhTMsg = (ORD_DEF_WHTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordDefWhTMsg);
        if (ordDefWhTMsg != null) {
            String tmpTimeZone = ordDefWhTMsg.ezUpTimeZone.getValue();
            String tmpTime = ordDefWhTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.C.no(i).ezUpTime_C1.getValue(), sMsg.C.no(i).ezUpTimeZone_C1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
				int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                bizMsg.A.no(errorLineNo).xxChkBox_A1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD"
                		, ordDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordDefWhTMsg.mdseItemClsTpCd.getValue()
                		 + ", " + ordDefWhTMsg.fromPostCd.getValue() + ", " + ordDefWhTMsg.toPostCd.getValue()});
                return false;
            }
        }
        ordDefWhTMsg = new ORD_DEF_WHTMsg();
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.defWhMapTmplCd, sMsg.C.no(i).defWhMapTmplCd_C1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.mdseItemClsTpCd, sMsg.C.no(i).mdseItemClsTpCd_C1);
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.thirdPtyItemFlg, getFlgValueFromFields(sMsg.C.no(i).thirdPtyItemFlg_C1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.fromPostCd, sMsg.C.no(i).fromPostCd_C1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.toPostCd, sMsg.C.no(i).toPostCd_C1);
        EZDTBLAccessor.logicalRemove(ordDefWhTMsg);
        if (!RETURN_CD_NORMAL.equals(ordDefWhTMsg.getReturnCode())) {
			int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.A.no(errorLineNo).xxChkBox_A1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD"
            		, ordDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordDefWhTMsg.mdseItemClsTpCd.getValue()
            		 + ", " + ordDefWhTMsg.fromPostCd.getValue() + ", " + ordDefWhTMsg.toPostCd.getValue()});
            return false;
        }
        return true;

    }

    // 2017/09/12 QC#19805 Add Start
    public static boolean deleteOrdRmaDefWh(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {

        ORD_RMA_DEF_WHTMsg ordRmaDefWhTMsg = new ORD_RMA_DEF_WHTMsg();
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.ordRmaDefWhPk, sMsg.F.no(i).ordRmaDefWhPk_F1);
        ordRmaDefWhTMsg = (ORD_RMA_DEF_WHTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordRmaDefWhTMsg);
        if (ordRmaDefWhTMsg != null) {
            String tmpTimeZone = ordRmaDefWhTMsg.ezUpTimeZone.getValue();
            String tmpTime = ordRmaDefWhTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.F.no(i).ezUpTime_F1.getValue(), sMsg.F.no(i).ezUpTimeZone_F1.getValue(), tmpTime, tmpTimeZone)) {
                //NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                if (ZYPCommonFunc.hasValue(sMsg.F.no(i).thirdPtyDspTpCd_F1)) {
                    bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_RMA_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, THIRD_PTY_DSP_TP_CD"
                            , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                             + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                             + ", " + ordRmaDefWhTMsg.thirdPtyDspTpCd.getValue()});
                }
                if (ZYPCommonFunc.hasValue(sMsg.F.no(i).dropRtrnVndCd_F1)) {
                    bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_RMA_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, DROP_RTRN_VND_CD"
                            , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                             + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                             + ", " + ordRmaDefWhTMsg.dropRtrnVndCd.getValue()});
                }
                return false;
            }
        }
        EZDTBLAccessor.logicalRemove(ordRmaDefWhTMsg);
        if (!RETURN_CD_NORMAL.equals(ordRmaDefWhTMsg.getReturnCode())) {
            int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
            //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            if (ZYPCommonFunc.hasValue(sMsg.F.no(i).thirdPtyDspTpCd_F1)) {
                bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, THIRD_PTY_DSP_TP_CD"
                        , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                         + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                         + ", " + ordRmaDefWhTMsg.thirdPtyDspTpCd.getValue()});
            }
            if (ZYPCommonFunc.hasValue(sMsg.F.no(i).dropRtrnVndCd_F1)) {
                bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, DROP_RTRN_VND_CD"
                        , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                         + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                         + ", " + ordRmaDefWhTMsg.dropRtrnVndCd.getValue()});
            }
            return false;
        }
        return true;
    }
    // 2017/09/12 QC#19805 Add End

    public static void updateOrdDefWh(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {
    	
    	ORD_DEF_WHTMsg ordDefWhTMsg = new ORD_DEF_WHTMsg();
    	ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.defWhMapTmplCd, sMsg.A.no(i).defWhMapTmplCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.mdseItemClsTpCd, sMsg.A.no(i).mdseItemClsTpCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.thirdPtyItemFlg, getFlgValueFromFields(sMsg.A.no(i).thirdPtyItemFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.fromPostCd, sMsg.A.no(i).fromPostCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.toPostCd, sMsg.A.no(i).toPostCd_A1);
        ordDefWhTMsg = (ORD_DEF_WHTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordDefWhTMsg);
        if (ordDefWhTMsg != null) {
            String tmpTimeZone = ordDefWhTMsg.ezUpTimeZone.getValue();
            String tmpTime = ordDefWhTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue(), tmpTime, tmpTimeZone)) {
    			int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.A.no(errorLineNo).xxChkBox_A1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD"
                		, ordDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordDefWhTMsg.mdseItemClsTpCd.getValue()
                		 + ", " + ordDefWhTMsg.fromPostCd.getValue() + ", " + ordDefWhTMsg.toPostCd.getValue()});
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.onHndChkFlg, getFlgValueFromFields(sMsg.A.no(i).onHndChkFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdPrimOnHndChkWhCd, sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdPrimOnHndChkSwhCd, sMsg.A.no(i).otbdPrimOnHndChkSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdPrimOnHndLinSrcCd, sMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdScdOnHndChkWhCd, sMsg.A.no(i).otbdScdOnHndChkWhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdScdOnHndChkSwhCd, sMsg.A.no(i).otbdScdOnHndChkSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdScdOnHndLineSrcCd, sMsg.A.no(i).otbdScdOnHndLineSrcCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdDefWhCd, sMsg.A.no(i).otbdDefWhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdDefSwhCd, sMsg.A.no(i).otbdDefSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdDefLineSrcCd, sMsg.A.no(i).otbdDefLineSrcCd_A1);
        // 2017/09/12 QC#19805 Del Start
//        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.rmaDefWhCd, sMsg.A.no(i).rmaDefWhCd_A1);
//        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.rmaDefSwhCd, sMsg.A.no(i).rmaDefSwhCd_A1);
//        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.rmaDefLineSrcCd, sMsg.A.no(i).rmaDefLineSrcCd_A1);
        // 2017/09/12 QC#19805 Del End
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.thirdPtyItemFlg, getFlgValueFromFields(sMsg.A.no(i).thirdPtyItemFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.actvFlg, "Y");
        S21FastTBLAccessor.update(ordDefWhTMsg);
        if (!RETURN_CD_NORMAL.equals(ordDefWhTMsg.getReturnCode())) {
			int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.A.no(errorLineNo).xxChkBox_A1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD"
            		, ordDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordDefWhTMsg.mdseItemClsTpCd.getValue()
            		 + ", " + ordDefWhTMsg.fromPostCd.getValue() + ", " + ordDefWhTMsg.toPostCd.getValue()});
            return;
        }
        
    }

    // 2017/09/12 QC#19805 Add Start
    public static void updateOrdRmaDefWh(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {

        ORD_RMA_DEF_WHTMsg ordRmaDefWhTMsg = new ORD_RMA_DEF_WHTMsg();
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.ordRmaDefWhPk, sMsg.E.no(i).ordRmaDefWhPk_E1);
        ordRmaDefWhTMsg = (ORD_RMA_DEF_WHTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordRmaDefWhTMsg);
        if (ordRmaDefWhTMsg != null) {
            String tmpTimeZone = ordRmaDefWhTMsg.ezUpTimeZone.getValue();
            String tmpTime = ordRmaDefWhTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.E.no(i).ezUpTime_E1.getValue(), sMsg.E.no(i).ezUpTimeZone_E1.getValue(), tmpTime, tmpTimeZone)) {
                int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                //NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                if (ZYPCommonFunc.hasValue(sMsg.E.no(i).thirdPtyDspTpCd_E1)) {
                    bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_RMA_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, THIRD_PTY_DSP_TP_CD"
                            , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                             + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                             + ", " + ordRmaDefWhTMsg.thirdPtyDspTpCd.getValue()});
                }
                if (ZYPCommonFunc.hasValue(sMsg.E.no(i).dropRtrnVndCd_E1)) {
                    bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "NMAM8175E", new String[]{"ORD_RMA_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, DROP_RTRN_VND_CD"
                            , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                             + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                             + ", " + ordRmaDefWhTMsg.dropRtrnVndCd.getValue()});
                }
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.onHndChkFlg, getFlgValueFromFields(sMsg.E.no(i).onHndChkFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.rmaDefWhCd, sMsg.E.no(i).rmaDefWhCd_E1);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.actvFlg, "Y");
        S21FastTBLAccessor.update(ordRmaDefWhTMsg);
        if (!RETURN_CD_NORMAL.equals(ordRmaDefWhTMsg.getReturnCode())) {
            int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
            //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            if (ZYPCommonFunc.hasValue(sMsg.E.no(i).thirdPtyDspTpCd_E1)) {
                bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, THIRD_PTY_DSP_TP_CD"
                        , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                         + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                         + ", " + ordRmaDefWhTMsg.thirdPtyDspTpCd.getValue()});
            }
            if (ZYPCommonFunc.hasValue(sMsg.E.no(i).dropRtrnVndCd_E1)) {
                bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "ZZMM0015E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, DROP_RTRN_VND_CD"
                        , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                         + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                         + ", " + ordRmaDefWhTMsg.dropRtrnVndCd.getValue()});
            }
            return;
        }
    }
    // 2017/09/12 QC#19805 Add End

    public static boolean chkOrdDefWh(NWAL2040SMsg sMsg) {
    	
		boolean ordDefWhChkFlg = false;
		for (int i = 0; i < sMsg.A.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).defWhMapTmplCd_A1)
					&& !ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseItemClsTpCd_A1)
					&& !ZYPCommonFunc.hasValue(sMsg.A.no(i).fromPostCd_A1)
					&& !ZYPCommonFunc.hasValue(sMsg.A.no(i).toPostCd_A1)
					) {
				continue;
			}
			ordDefWhChkFlg = true;
		}
		return ordDefWhChkFlg;
		
	}
    public static boolean insertOrdDefWh(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {

    	ORD_DEF_WHTMsg ordDefWhTMsg = new ORD_DEF_WHTMsg();
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.defWhMapTmplCd, sMsg.A.no(i).defWhMapTmplCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.mdseItemClsTpCd, sMsg.A.no(i).mdseItemClsTpCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.thirdPtyItemFlg, getFlgValueFromFields(sMsg.A.no(i).thirdPtyItemFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.fromPostCd, sMsg.A.no(i).fromPostCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.toPostCd, sMsg.A.no(i).toPostCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.onHndChkFlg, getFlgValueFromFields(sMsg.A.no(i).onHndChkFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdPrimOnHndChkWhCd, sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdPrimOnHndChkSwhCd, sMsg.A.no(i).otbdPrimOnHndChkSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdPrimOnHndLinSrcCd, sMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1);
        
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdScdOnHndChkWhCd, sMsg.A.no(i).otbdScdOnHndChkWhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdScdOnHndChkSwhCd, sMsg.A.no(i).otbdScdOnHndChkSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdScdOnHndLineSrcCd, sMsg.A.no(i).otbdScdOnHndLineSrcCd_A1);
        
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdDefWhCd, sMsg.A.no(i).otbdDefWhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdDefSwhCd, sMsg.A.no(i).otbdDefSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.otbdDefLineSrcCd, sMsg.A.no(i).otbdDefLineSrcCd_A1);
        
        // 2017/09/12 QC#19805 Del Start
//        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.rmaDefWhCd, sMsg.A.no(i).rmaDefWhCd_A1);
//        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.rmaDefSwhCd, sMsg.A.no(i).rmaDefSwhCd_A1);
//        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.rmaDefLineSrcCd, sMsg.A.no(i).rmaDefLineSrcCd_A1);
        // 2017/09/12 QC#19805 Del End
        ZYPEZDItemValueSetter.setValue(ordDefWhTMsg.actvFlg, "Y");
        EZDTBLAccessor.create(ordDefWhTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordDefWhTMsg.getReturnCode())) {
			int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.A.no(errorLineNo).xxChkBox_A1.setErrorInfo(1, "ZZMM0001E", new String[]{"ORD_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD"
            		, ordDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordDefWhTMsg.mdseItemClsTpCd.getValue()
            		 + ", " + ordDefWhTMsg.fromPostCd.getValue() + ", " + ordDefWhTMsg.toPostCd.getValue()});
            return false;
        }
        return true;
        
    }

    // 2017/09/12 QC#19805 Add Start
    public static boolean insertOrdRmaDefWh(String globalCompanyCode, NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {

        ORD_RMA_DEF_WHTMsg ordRmaDefWhTMsg = new ORD_RMA_DEF_WHTMsg();
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.ordRmaDefWhPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_RMA_DEF_WH_SQ));
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.defWhMapTmplCd, sMsg.E.no(i).defWhMapTmplCd_E1);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.mdseItemClsTpCd, sMsg.E.no(i).mdseItemClsTpCd_E1);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.thirdPtyItemFlg, getFlgValueFromFields(sMsg.E.no(i).thirdPtyItemFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.fromPostCd, sMsg.E.no(i).fromPostCd_E1);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.toPostCd, sMsg.E.no(i).toPostCd_E1);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.onHndChkFlg, getFlgValueFromFields(sMsg.E.no(i).onHndChkFlg_E1.getValue()));
        if (ZYPCommonFunc.hasValue(sMsg.E.no(i).thirdPtyDspTpCd_E1)) {
            ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.thirdPtyDspTpCd, sMsg.E.no(i).thirdPtyDspTpCd_E1);
        }
        if (ZYPCommonFunc.hasValue(sMsg.E.no(i).dropRtrnVndCd_E1)) {
            ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.dropRtrnVndCd, sMsg.E.no(i).dropRtrnVndCd_E1);
        }
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.rmaDefWhCd, sMsg.E.no(i).rmaDefWhCd_E1);
        ZYPEZDItemValueSetter.setValue(ordRmaDefWhTMsg.actvFlg, "Y");
        EZDTBLAccessor.create(ordRmaDefWhTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordRmaDefWhTMsg.getReturnCode())) {
            int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
            // ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            if (ZYPCommonFunc.hasValue(sMsg.E.no(i).thirdPtyDspTpCd_E1)) {
                bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "ZZMM0001E", new String[]{"ORD_RMA_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, THIRD_PTY_DSP_TP_CD"
                        , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                         + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                         + ", " + ordRmaDefWhTMsg.thirdPtyDspTpCd.getValue()});
            }
            if (ZYPCommonFunc.hasValue(sMsg.E.no(i).dropRtrnVndCd_E1)) {
                bizMsg.E.no(errorLineNo).xxChkBox_E1.setErrorInfo(1, "ZZMM0001E", new String[]{"ORD_RMA_DEF_WH", "DEF_WH_MAP_TMPL_CD, MDSE_ITEM_CLS_TP_CD, FROM_POST_CD, TO_POST_CD, DROP_RTRN_VND_CD"
                        , ordRmaDefWhTMsg.defWhMapTmplCd.getValue() + ", " + ordRmaDefWhTMsg.mdseItemClsTpCd.getValue()
                         + ", " + ordRmaDefWhTMsg.fromPostCd.getValue() + ", " + ordRmaDefWhTMsg.toPostCd.getValue()
                         + ", " + ordRmaDefWhTMsg.dropRtrnVndCd.getValue()});
            }
            return false;
        }
        return true;
    }
    // 2017/09/12 QC#19805 Add End

    public static String getFlgValueFromFields(String flg) {
    	
    	return !ZYPCommonFunc.hasValue(flg) ? FLG_OFF_N : flg;    	
    	
    }
    public static int transferErrorPage(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {
       	//current page # = (Error line # + showable line # - 1) / showable line #
       	//int transferPage = (i + bizMsg.A.length() - 1) / bizMsg.A.length();
       	int transferPage = (i + bizMsg.A.length() + 1) / (bizMsg.A.length() - 0);
       	if (transferPage == 0) transferPage = 1;
       	int totPage = (sMsg.A.getValidCount() + bizMsg.A.length() - 1) / bizMsg.A.length();
       	//Copy from SMsg to CMsg
       	int s = (transferPage - 1) * bizMsg.A.length();
       	int start = s;
       	int e = transferPage * bizMsg.A.length() - 1;
		ZYPTableUtil.clear(bizMsg.A);
       	int k = 0;
       	int errorLineNo = 0;
       	for ( ; s <= e; s++) {
       		if (s < sMsg.A.getValidCount()) {
       			if (s == i) {
       				errorLineNo = k;
       			}
       			EZDMsg.copy( sMsg.A.no( s ), null, bizMsg.A.no( k ), null );
       			k++;
       		} else {
       			break;
       		}
       	}
       	bizMsg.A.setValidCount( k );
       	
       	//set Page
	    bizMsg.xxPageShowFromNum_10.setValue( start + 1 );
        //bizMsg.xxPageShowToNum_10.setValue( e + 1 );
        bizMsg.xxPageShowToNum_10.setValue( bizMsg.A.getValidCount() + (transferPage - 1) * bizMsg.A.length() );
        bizMsg.xxPageShowOfNum_10.setValue( sMsg.A.getValidCount() );
        bizMsg.xxPageShowCurNum_10.setValue( transferPage );
        bizMsg.xxPageShowTotNum_10.setValue( totPage );

        /*
         * 1 page
		0,,xxPageShowFromNum_10,1
		0,,xxPageShowToNum_10,15
		0,,xxPageShowOfNum_10,37
		0,,xxPageShowCurNum_10,1
		0,,xxPageShowTotNum_10,3
		
		 * 2 page
		0,,xxPageShowFromNum_10,16
		0,,xxPageShowToNum_10,30
		0,,xxPageShowOfNum_10,37
		0,,xxPageShowCurNum_10,2
		0,,xxPageShowTotNum_10,3
		
         
         * 3 page
        0,,xxPageShowFromNum_10,31-->16
        0,,xxPageShowToNum_10,37-->30
        0,,xxPageShowOfNum_10,37-->37
        0,,xxPageShowCurNum_10,3-->2
        0,,xxPageShowTotNum_10,3-->3
		*/
        
        return errorLineNo;
    	
    }

    // 2017/09/12 QC#19805 Add Start
    public static int transferErrorPageRMA(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg, int i) {
        // 2017/09/25 QC#21375 Mod Start
//        //current page # = (Error line # + showable line # - 1) / showable line #
//        int transferPage = (i + bizMsg.E.length() + 1) / (bizMsg.E.length() - 0);
//        if (transferPage == 0) transferPage = 1;
//        int totPage = (sMsg.E.getValidCount() + bizMsg.E.length() - 1) / bizMsg.E.length();
//        //Copy from SMsg to CMsg
//        int s = (transferPage - 1) * bizMsg.E.length();
//        int start = s;
//        int e = transferPage * bizMsg.E.length() - 1;
//        ZYPTableUtil.clear(bizMsg.E);
//        int k = 0;
//        int errorLineNo = 0;
//        for ( ; s <= e; s++) {
//            if (s < sMsg.E.getValidCount()) {
//                if (s == i) {
//                    errorLineNo = k;
//                }
//                EZDMsg.copy( sMsg.E.no( s ), null, bizMsg.E.no( k ), null );
//                k++;
//            } else {
//                break;
//            }
//        }
//        bizMsg.E.setValidCount( k );
//        
//        //set Page
//        bizMsg.xxPageShowFromNum_10.setValue( start + 1 );
//        bizMsg.xxPageShowToNum_10.setValue( bizMsg.E.getValidCount() + (transferPage - 1) * bizMsg.E.length() );
//        bizMsg.xxPageShowOfNum_10.setValue( sMsg.E.getValidCount() );
//        bizMsg.xxPageShowCurNum_10.setValue( transferPage );
//        bizMsg.xxPageShowTotNum_10.setValue( totPage );
//
//        return errorLineNo;
        int pageCnt = i / bizMsg.E.length();
        int pageIndx = i % bizMsg.E.length();

        int pageFrom = pageCnt * bizMsg.E.length();
        int j = pageFrom;
        for (; j < pageFrom + bizMsg.E.length(); j++) {
            if (j < sMsg.E.getValidCount()) {
                EZDMsg.copy(sMsg.E.no(j), null, bizMsg.E.no(j - pageFrom), null);
            } else {
                break;
            }
        }
        bizMsg.E.setValidCount(j - pageFrom);

        bizMsg.xxPageShowFromNum_10.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum_10.setValue(pageFrom + bizMsg.E.getValidCount());

        return pageIndx;
        // 2017/09/25 QC#21375 Mod End
    }
    // 2017/09/12 QC#19805 Add End

    public static void transferLastPage(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
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
//        bizMsg.xxPageShowToNum_10.setValue( e + 1 );
        bizMsg.xxPageShowToNum_10.setValue( bizMsg.A.getValidCount() );
        bizMsg.xxPageShowOfNum_10.setValue( sMsg.A.getValidCount() );
        bizMsg.xxPageShowCurNum_10.setValue( totPage );
        bizMsg.xxPageShowTotNum_10.setValue( totPage );
    
    }

    // 2017/09/12 QC#19805 Add Start
    public static void transferLastPageRMA(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
        //current page # = (Error line # + showable line # - 1) / showable line #
        int totPage = (sMsg.E.getValidCount() + bizMsg.E.length() - 1) / bizMsg.E.length();
        //Copy from SMsg to CMsg
        int s = (totPage - 1) * bizMsg.E.length();
        int start = s;
        int e = totPage * bizMsg.E.length() - 1;
        ZYPTableUtil.clear(bizMsg.E);
        int k = 0;
        for ( ; s <= e; s++) {
            if (s < sMsg.E.getValidCount()) {
                EZDMsg.copy( sMsg.E.no( s ), null, bizMsg.E.no( k ), null );
                k++;
            } else {
                break;
            }
        }
        bizMsg.E.setValidCount( k );

        //set Page
        bizMsg.xxPageShowFromNum_10.setValue( start + 1 );
        bizMsg.xxPageShowToNum_10.setValue( bizMsg.E.getValidCount() );
        bizMsg.xxPageShowOfNum_10.setValue( sMsg.E.getValidCount() );
        bizMsg.xxPageShowCurNum_10.setValue( totPage );
        bizMsg.xxPageShowTotNum_10.setValue( totPage );
    }
    // 2017/09/12 QC#19805 Add End

    public static void copyFromCMsgToSMsgForMapTmpl(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, boolean flg) {
    	int fromNum = cMsg.xxPageShowFromNum_10.getValueInt();
//    	int currentPage = cMsg.xxPageShowCurNum_10.getValueInt();
//        int s              = (currentPage - 1) * cMsg.A.length();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            EZDMsg.copy(cMsg.A.no( i ) , null, sMsg.A.no( s + i ), null );
            EZDMsg.copy(cMsg.A.no( i ) , null, sMsg.A.no( fromNum - 1 + i ), null );
    	}
        if (flg) {
    		ZYPTableUtil.clear(cMsg.A);
        }
    }

    // 2017/09/12 QC#19805 Add Start
    public static void copyFromCMsgToSMsgForMapTmplRMA(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, boolean flg) {
        int fromNum = cMsg.xxPageShowFromNum_10.getValueInt();
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            EZDMsg.copy(cMsg.E.no( i ) , null, sMsg.E.no( fromNum - 1 + i ), null );
        }
        if (flg) {
            ZYPTableUtil.clear(cMsg.E);
        }
    }
    // 2017/09/12 QC#19805 Add End

    public static void copyFromCMsgToSMsgForSrcCatg(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, boolean flg) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            EZDMsg.copy(cMsg.B.no( i ) , null, sMsg.B.no( i ), null );
    	}
        if (flg) {
    		ZYPTableUtil.clear(cMsg.B);
        }
    }
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getInternalOrderLineSource(String globalCompanyCode, String ordLineSrcCd) {
		S21SsmEZDResult result = NWAL2040Query.getInstance().getInternalOrderLineSource(globalCompanyCode, ordLineSrcCd);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map == null || map.get("ORD_LINE_SRC_CATG_CD") == null) {
				return null;
			}
			return map;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public static void setDsOrdTpPulldown(NWAL2040_BCMsg bCMsg, String globalCompanyCode, String dsOrdCatgCd) {
		bCMsg.dsOrdTpCd_BL.clear();
		bCMsg.dsOrdTpNm_BL.clear();
		S21SsmEZDResult result = NWAL2040Query.getInstance().setDsOrdTpPulldown(globalCompanyCode, dsOrdCatgCd);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for (Map<String, Object> map : list) {
				if (map != null && map.get("DS_ORD_TP_CD") != null) {
					bCMsg.dsOrdTpCd_BL.no(i).setValue((String) map.get("DS_ORD_TP_CD"));
					bCMsg.dsOrdTpNm_BL.no(i).setValue((String) map.get("DS_ORD_TP_NM"));
			    	i++;
				}
			}
		}
	}

    // START 2016/03/23 K.Kojima [UT#56,ADD]
    /**
     * checkPostCdDuplicated
     * @param from1 String
     * @param to1 String
     * @param from2 String
     * @param to2 String
     * @return boolean
     */
	public static boolean checkPostCdDuplicated(String from1, String to1, String from2, String to2) {
        if (from1 == null || to1 == null || from2 == null || to2 == null) {
            return true;
        }
        from1 = formatPostCd(from1);
        to1 = formatPostCd(to1);
        from2 = formatPostCd(from2);
        to2 = formatPostCd(to2);
        if (from1.compareTo(to2) <= 0 && from2.compareTo(to1) <= 0) {
            return false;
        }
        return true;
    }

    private static String formatPostCd(String postCd) {
        String[] str = postCd.split("-");
        if (str.length == 2) {
            return ZYPCommonFunc.leftPad(str[0], 15, "0") + "-" + ZYPCommonFunc.rightPad(str[1], 15, "0");
        } else {
            return ZYPCommonFunc.leftPad(postCd, 15, "0") + "-" + ZYPCommonFunc.rightPad("", 15, "0");
        }
    }
    // END 2016/03/23 K.Kojima [UT#56,ADD]

}
