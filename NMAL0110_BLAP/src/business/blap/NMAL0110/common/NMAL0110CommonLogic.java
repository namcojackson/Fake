package business.blap.NMAL0110.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.blap.NMAL0110.NMAL0110Query;
import business.blap.NMAL0110.constant.NMAL0110Constant;
import business.db.AMER_CMPYTMsg;
import business.db.AMER_MSTRTMsg;
import business.db.AMER_MSTRTMsgArray;
import business.db.COA_ACCTTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.CTRYTMsg;
import business.db.CUST_MDSE_XREFTMsg;
import business.db.DFRD_ACCTG_RULETMsg;
import business.db.FRT_CLSTMsg;
import business.db.HAZ_MATTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_CRAT_TMPL_STORE_PKGTMsg;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_HIST_HDRTMsg;
import business.db.MDSE_ITEM_FLIP_SETTMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MDSE_SER_NUM_RNGTMsg;
import business.db.MDSE_SFTY_INFOTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_TERM_COND_OPTTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.SUPDTMsg;
import business.db.SUPD_RELNTMsg;
import business.db.SW_CATGTMsg;
import business.db.SW_LIC_CTRL_TPTMsg;
import business.db.SW_PROD_CATGTMsg;
import business.db.TERM_COND_OPT_TPTMsg;
import business.db.TRFTMsg;
import business.db.PKG_UOM_BOX_EACHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_ACCT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_RQST_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MERC_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMA_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SOFT_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfAttribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfKeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Attribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Event;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.KeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.OPERATIONTYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.REFERENCETYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.wrapper.MasterDataMessagingServiceProxy;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 * 06/01/2015   Fujitsu         K.Fujita        Update          
 * 07/03/2015   Fujitsu         C.Tanaka        Update          
 * 10/02/2015   SRAA            K.Aratani       Update          
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 06/20/2016   SRAA            K.Aratani       Update          QC#10449
 * 06/23/2016   SRAA            K.Aratani       Update          QC#9823, QC#10129
 * 07/27/2016   SRAA            K.Aratani       Update          QC#12451
 * 11/02/2016   Fujitsu         R.Nakamura      Update          QC#2872
 * 01/17/2017   SRAA            K.Aratani       Update          QC#17184
 * 08/24/2017   CITS            T.Kikuhara      Update          QC#18365(L3)
 * 09/25/2017   Fujitsu         T.Aoi           Update          QC#18534(L3#445)
 * 03/14/2018   Fujitsu         H.Nagashima     Update          QC#24556
 * 08/03/2018   Fujitsu         M.Ishii         Update          QC#26046
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 * 2019/10/24   Fujitsu         K.Kato          Update          QC#51967
 * 2019/10/28   Fujitsu         K.Kato          Update          QC#53741
 * 2019/10/31   Fujitsu         K.Kato          Update          QC#51967
 * 2020/01/07   Fujitsu         K.Kato          Update          QC#55220
 * 2020/04/07   Fujitsu         M.Ohno          Update          QC#56017
 * 2020/04/13   CITS            K.Ogino         Update          QC#56494
 * 2022/02/17   Fujitsu         C.Hara          Update          QC#59693
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */ 
public class NMAL0110CommonLogic implements NMAL0110Constant {

    private static Logger logger = Logger.getLogger(NMAL0110CommonLogic.class);
    @SuppressWarnings("unchecked")
	public static void setInitialItemStsPulldown(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getItemStatusList(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for(Map<String, Object> map : list) {
                if (map != null && map.get("MDSE_ITEM_STS_CD") != null) {
                    bizMsg.mdseItemStsCd_L1.no(i).setValue((String) map.get("MDSE_ITEM_STS_CD"));
                    bizMsg.mdseItemStsNm_L1.no(i).setValue((String) map.get("MDSE_ITEM_STS_NM"));
                    i++;
                }
			}
		}
	}
    
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getItemStatusDefaultList(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getItemStatusDefaultList(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			return map;
		}
		return null;
	}
    
//    @SuppressWarnings("unchecked")
//	public static void setCoaMdseTpPulldown(NMAL0110CMsg bizMsg, String globalCompanyCode) {
//		S21SsmEZDResult result = NMAL0110Query.getInstance().getCoaProjList(bizMsg, globalCompanyCode);
//		if (result.isCodeNormal()) {
//			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//			if (list == null || list.size() <= 0) {
//				return;
//			}
//			int i = 0;
//			for(Map<String, Object> map : list) {
//				if (map != null && map.get("COA_PROJ_CD") != null && ((String) map.get("COA_PROJ_CD")).length() <= 2) {
//					ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpCd_L1.no(i), (String) map.get("COA_PROJ_CD"));
//					ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpNm_L1.no(i), (String) map.get("COA_PROJ_NM"));
//					i++;
//				}
//			}
//		}
//	}
    @SuppressWarnings("unchecked")
	public static void setInitialPlanningGroupPulldown(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getPlanningGroupList(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for(Map<String, Object> map : list) {
				if (map != null && map.get("PRCH_GRP_CD") != null) {
					ZYPEZDItemValueSetter.setValue(bizMsg.prchGrpCd_L1.no(i), (String) map.get("PRCH_GRP_CD"));
					ZYPEZDItemValueSetter.setValue(bizMsg.prchGrpNm_L1.no(i), (String) map.get("PRCH_GRP_NM"));
					i++;
				}
			}
		}
	}
    @SuppressWarnings("unchecked")
	public static void setInitialPkgUomPulldown(NMAL0110CMsg bizMsg, String globalCompanyCode, String pkgUomClsCd) {
		if (globalCompanyCode == null || pkgUomClsCd == null) {
			return;
		}
		S21SsmEZDResult result = NMAL0110Query.getInstance().getPkgUomPullDownList(bizMsg, globalCompanyCode, pkgUomClsCd);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			int i = 0;
			for(Map<String, Object> map : list) {
				if (map != null && map.get("PKG_UOM_CD") != null) {
					ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomCd_BL.no(i), (String) map.get("PKG_UOM_CD"));
					ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomDescTxt_BL.no(i), (String) map.get("PKG_UOM_DESC_TXT"));
					i++;
				}
			}
		}
	}
    @SuppressWarnings("unchecked")
	public static void setProductLevelName(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getProductLevelName(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list == null || list.size() <= 0) {
				return;
			}
			for(Map<String, Object> map : list) {
				if (map != null && map.get("MDSE_STRU_ELMNT_TP_CD") != null) {
					//PLG
					if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
						ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L1, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
					//PL
					} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
						ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L2, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
					//PL2
					} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
						ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L3, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
					//PL3
					} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
						ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L4, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
					//PL4
					} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
						ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L5, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
					}
				}
			}
		}
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getZerothProdHrch(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getProdCtrl(bizMsg.zerothProdCtrlCd_H1.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getFirstProdHrch(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getProdCtrl(bizMsg.firstProdCtrlCd_H1.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LINE, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getScdProdHrch(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getProdCtrl(bizMsg.scdProdCtrlCd_H1.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getThirdProdHrch(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getProdCtrlForThird(bizMsg.thirdProdCtrlCd_H1.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3, null, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getThirdProdHrchReln(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getProdCtrlForThird(bizMsg.thirdProdCtrlCd_H1.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3, bizMsg.scdProdCtrlCd_H1.getValue(), globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getFrthProdHrch(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getProdCtrl(bizMsg.frthProdCtrlCd_H1.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getSlsMatGrp(NMAL0110CMsg bizMsg, String globalCompanyCode, String slsMatGrpCd, String slsMatGrpSqNum) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getSlsMatGrp(bizMsg, globalCompanyCode, slsMatGrpCd, slsMatGrpSqNum);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map != null && map.get("SLS_MAT_GRP_CD") != null) {
				return map;
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getDsCmsnGrp(NMAL0110CMsg bizMsg, String globalCompanyCode, String dsCmsnGrpCd) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getDsCmsnGrp(bizMsg, globalCompanyCode, dsCmsnGrpCd);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map != null && map.get("DS_CMSN_GRP_CD") != null) {
				return map;
			}
		}
		return null;
	}
    @SuppressWarnings("unchecked")
	public static void search(NMAL0110CMsg bizMsg, String globalCompanyCode, String mdseCd, boolean jumpFlg) {

    	if (!ZYPCommonFunc.hasValue(mdseCd)) {
    		//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.mdseCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Item Number"});
    		return;
    	}
		if(!ZYPCommonFunc.hasValue(bizMsg.mdseRgtnTpCd_H1.getValue())){
			MDSETMsg dsMdseInfoTMsg = findDsMdseInfo(globalCompanyCode, bizMsg.mdseCd_H1.getValue());
			if (dsMdseInfoTMsg != null) {
				bizMsg.mdseRgtnTpCd_H1.setValue(dsMdseInfoTMsg.mdseRgtnTpCd.getValue());
			}
		}
		
		S21SsmEZDResult result = NMAL0110Query.getInstance().search(bizMsg, globalCompanyCode, mdseCd);
		if (result.isCodeNormal() && (Map<String, Object>) result.getResultObject() != null) {
			setScreenValueFromMap(bizMsg, (Map<String, Object>) result.getResultObject(), INIT);
			bizMsg.xxModeCd_H1.setValue(MODE_MOD); //Modify
		} else {
			if (!ZYPCommonFunc.hasValue(bizMsg.mdseRgtnTpCd_H1)) {
				//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.mdseRgtnTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Regstration Mode"});
        		return;
			}
	        if (MDSE_RGTN_TP.MERCURY.equals(bizMsg.mdseRgtnTpCd_H1.getValue())) {
		        AMER_MSTRTMsgArray resultAmerMstr = NMAL0110CommonLogic.chkAMERMSTRTMsg(globalCompanyCode, bizMsg.mdseCd_H1.getValue());
		        if (resultAmerMstr == null || resultAmerMstr.length() <= 0) {
	        		//NMAM0201E=0,[@] does not exist in Americas Mercury.
	        		bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0201E", new String[]{"Item Number"});
	        		return;
		        }
		        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
		        S21UserInfo user = profile.getContextUserInfo();
		        AMER_CMPYTMsg amerCmpyTMsgResult = NMAL0110CommonLogic.checkAmerCmpy(bizMsg, bizMsg.mdseCd_H1.getValue(), user, globalCompanyCode);
		        if (amerCmpyTMsgResult == null) {
	        		//NMAM0202E=0,[@] has not been disclosed to @ in Americas Mercury.
	        		bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0202E", new String[]{"Item Number", user.getUserCompanyCode()});
	        		return;
		        }
	            if (!amerCmpyTMsgResult.amerXpndTpCd.getValue().equals("1") && MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue())) {
	            	//NMAM0203E=0,The disclosure process of [@] to @ is not completed.
	                bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0203E", new String[] {"Item Number", user.getUserCompanyCode() });
	        		return;
	            }
	            
	            //Default set
	            Map<String, Object> cusaMdseMap = setScreenValueFromMapForCusaMdse(bizMsg, globalCompanyCode);
		        if (cusaMdseMap != null) {
		    	    //MDSE_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H1, (String) cusaMdseMap.get("MDSE_NM"));
		    	    //MDSE_FML_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescLongTxt_H1, (String) cusaMdseMap.get("MDSE_FML_NM"));
		    	    //COA_PROD_CD
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, (String) cusaMdseMap.get("COA_PROD_CD"));
		    	    //COA_PROD_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, (String) cusaMdseMap.get("COA_PROD_NM"));
		    	    //UPC_CD
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.upcCd_H1, (String) cusaMdseMap.get("UPC_CD"));
		    	    //HAZMAT_FLG
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HM, getFlgValueFromFields((String) cusaMdseMap.get("HAZ_MAT_FLG")));
		    	    //MADE_IN_CTRY_CD
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.madeInCtryCd_H1, (String) cusaMdseMap.get("MADE_IN_CTRY_CD"));
		    	    //MADE_IN_CTRY_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_MI, (String) cusaMdseMap.get("MADE_IN_CTRY_NM"));
		    	    //ASM_IN_CTRY_CD
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.asmInCtryCd_H1, (String) cusaMdseMap.get("ASM_IN_CTRY_CD"));
		    	    //ASM_IN_CTRY_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_AI, (String) cusaMdseMap.get("ASM_IN_CTRY_NM"));
		    	    //HAZ_MAT_CD
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.hazMatCd_H1, (String) cusaMdseMap.get("HAZ_MAT_CD"));
		    	    //HAZ_CLS_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.hazClsNm_H1, (String) cusaMdseMap.get("HAZ_CLS_NM"));
		    	    //HAZ_PRP_SHIP_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.hazPrpShipNm_H1, (String) cusaMdseMap.get("HAZ_PRP_SHIP_NM"));
		    	    //HAZ_ID_NUM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.hazIdNum_H1, (String) cusaMdseMap.get("HAZ_ID_NUM"));
		    	    //IN_INCH_LG
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_EA, (BigDecimal) cusaMdseMap.get("IN_INCH_LG"));
		    	    //IN_INCH_WDT
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_EA, (BigDecimal) cusaMdseMap.get("IN_INCH_WDT"));
		    	    //IN_INCH_HGT
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_EA, (BigDecimal) cusaMdseMap.get("IN_INCH_HGT"));
		    	    //IN_POUND_WT
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_EA, (BigDecimal) cusaMdseMap.get("IN_POUND_WT"));
		    	    // set who Column General Tab (Dimensions(Each))
		    	    // Add Start 2016/11/08 QC#2872
		    	    // Who column
                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G1, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_S"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_S")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G1, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_S"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_S")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G1, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G1, TABLE_MDSE_STORE_PKG);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G3, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_S"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G3, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_S")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G3, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_S"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G3, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_S")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G3, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G3, TABLE_MDSE_STORE_PKG);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G5, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_S"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G5, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_S")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G5, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_S"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G5, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_S")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G5, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G5, TABLE_MDSE_STORE_PKG);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G7, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_S"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G7, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_S")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G7, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_S"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G7, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_S")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G7, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_S"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G7, TABLE_MDSE_STORE_PKG);
                    }
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G2, TABLE_MDSE_STORE_PKG);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G4, TABLE_MDSE_STORE_PKG);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G6, TABLE_MDSE_STORE_PKG);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G8, TABLE_MDSE_STORE_PKG);

                    // set who Column General Tab (Safety)
                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G9, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_SI"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G9, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_SI")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G9, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_SI"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G9, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_SI")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G9, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G9, TABLE_MDSE_SFTY_INFO);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GA, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_SI"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GA, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_SI")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GA, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_SI"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GA, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_SI")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GA, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GA, TABLE_MDSE_SFTY_INFO);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GB, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_SI"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GB, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_SI")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GB, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_SI"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GB, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_SI")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GB, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GB, TABLE_MDSE_SFTY_INFO);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GC, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_SI"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GC, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_SI")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GC, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_SI"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GC, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_SI")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GC, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GC, TABLE_MDSE_SFTY_INFO);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GD, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_SI"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GD, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_SI")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GD, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_SI"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GD, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_SI")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GD, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GD, TABLE_MDSE_SFTY_INFO);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GE, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_SI"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GE, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_SI")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GE, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_SI"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GE, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_SI")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GE, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GE, TABLE_MDSE_SFTY_INFO);
                    }

                    // XX_REC_HIST_CRAT_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GF, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS_SI"));
                    // XX_REC_HIST_CRAT_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GF, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM_SI")));
                    // XX_REC_HIST_UPD_TS
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GF, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS_SI"));
                    // XX_REC_HIST_UPD_BY_NM
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GF, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM_SI")));
                    // XX_REC_HIST_TBL_NM
                    if (ZYPCommonFunc.hasValue((String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GF, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM_SI"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GF, TABLE_MDSE_SFTY_INFO);
                    }
		    	    // Add End 2016/11/08 QC#2872
		    	    
		    	    //QC#12451
		    	    //FRT_CLS_CD
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.frtClsCd_H1, (String) cusaMdseMap.get("FRT_CLS_CD"));
		    	    //FRT_CLS_DESC_TXT
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.frtClsDescTxt_H1, (String) cusaMdseMap.get("FRT_CLS_DESC_TXT"));
		    	    //NMFC_ITEM_NUM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemNum_H1, (String) cusaMdseMap.get("NMFC_ITEM_NUM"));
		    	    //NMFC_ITEM_SUB_NUM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemSubNum_H1, (String) cusaMdseMap.get("NMFC_ITEM_SUB_NUM"));
		    	    //NMFC_CLS_NUM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.nmfcClsNum_H1, (BigDecimal) cusaMdseMap.get("NMFC_CLS_NUM"));

		    	    // set who Column Header
		    	    // Add Start 2016/11/02 QC#2872
		    	    // Who column
		    	    // XX_REC_HIST_CRAT_TS
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs, (String) cusaMdseMap.get("XX_REC_HIST_CRAT_TS"));
		    	    // XX_REC_HIST_CRAT_BY_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_CRAT_BY_NM")));
		    	    // XX_REC_HIST_UPD_TS
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs, (String) cusaMdseMap.get("XX_REC_HIST_UPD_TS"));
		    	    // XX_REC_HIST_UPD_BY_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist((String) cusaMdseMap.get("XX_REC_HIST_UPD_BY_NM")));
		    	    // XX_REC_HIST_TBL_NM
		    	    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm, (String) cusaMdseMap.get("XX_REC_HIST_TBL_NM"));
		    	    // Add End 2016/11/02 QC#2872

		    	    //NMAM8222I=0,The default data is set from CUSA Merchandise Master.
		    	    bizMsg.setMessageInfo( "NMAM8222I" );
		    	    
		        } else {
		    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
					//bizMsg.mdseCd_H1.setErrorInfo(1, "NDAM0007E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
					//bizMsg.mdseRgtnTpCd_H1.setErrorInfo(1, "NDAM0007E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
		        	//NMAM8381E=0,The Item Number is not registered yet ,If you register new item , please input information and click submit.
					bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM8381E");
					bizMsg.mdseRgtnTpCd_H1.setErrorInfo(1, "NMAM8381E");
		            return;
		        }
	            
	        } else if (MDSE_RGTN_TP.S21_PARTS.equals(bizMsg.mdseRgtnTpCd_H1.getValue())) {
	        	
	    		//CUSA PARTS_V
	    		String cusaPartsViewName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_USMM4100", globalCompanyCode);
	        	if (ZYPCommonFunc.hasValue(cusaPartsViewName)) {
		            S21SsmEZDResult ssmResult = NMAL0110Query.getInstance().getS21Parts(globalCompanyCode, bizMsg.mdseCd_H1.getValue(), cusaPartsViewName);
		            if (!ssmResult.isCodeNormal()) {
		                 bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0163E", new String[] {bizMsg.mdseCd_H1.getValue(), "S21 Parts" });
		                 return;
		            }
	        	}
	            //Default set
	            Map<String, Object> cusaPartsMap = setScreenValueFromMapForParts(bizMsg, globalCompanyCode);
		        if (cusaPartsMap != null) {
				    //MDSE_NM
				    ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H1, (String) cusaPartsMap.get("P_DESC_PART"));
				    //COA_PROD_CD
				    //ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, (String) map.get(""));
				    //UPC_CD
				    //ZYPEZDItemValueSetter.setValue(bizMsg.upcCd_H1, (String) map.get(""));
				    //HAZMAT_FLG
				    ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HM, getFlgValueFromFields((String) cusaPartsMap.get("P_FLG_HAZMAT")));
				    //HAZ_MAT_CD
				    //ZYPEZDItemValueSetter.setValue(bizMsg.hazMatCd_H1, (String) map.get(""));
				    //IN_POUND_WT
				    ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_EA, (BigDecimal) cusaPartsMap.get("IN_POUND_WT"));
				    //NMAM8223I=0,The default data is set from CUSA Parts Master.
				    bizMsg.setMessageInfo( "NMAM8223I" );
		        } else {
		    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
					//bizMsg.mdseCd_H1.setErrorInfo(1, "NDAM0007E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
					//bizMsg.mdseRgtnTpCd_H1.setErrorInfo(1, "NDAM0007E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
		        	//NMAM8381E=0,The Item Number is not registered yet ,If you register new item , please input information and click submit.
					bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM8381E");
					bizMsg.mdseRgtnTpCd_H1.setErrorInfo(1, "NMAM8381E");
		            return;
		        }
	            
	        } else if (MDSE_RGTN_TP.MANUAL.equals(bizMsg.mdseRgtnTpCd_H1.getValue())) {
		        AMER_MSTRTMsgArray resultAmerMstr = NMAL0110CommonLogic.chkAMERMSTRTMsg(globalCompanyCode, bizMsg.mdseCd_H1.getValue());
		        if (resultAmerMstr != null && resultAmerMstr.length() > 0) {
	        		//NMAM0834E=0,[@] already exists in [@]
	        		//bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0834E", new String[]{bizMsg.mdseCd_H1.getValue(), "Mercury"});
	        		//NMAM8380E=0,When you register @ ,please select Registration Mode Mercury.[@]
	        		bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM8380E", new String[]{"Mercury Items", bizMsg.mdseCd_H1.getValue()});
	        		return;
		        }
	    		String cusaPartsViewName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_USMM4100", globalCompanyCode);
	        	if (ZYPCommonFunc.hasValue(cusaPartsViewName)) {
		            S21SsmEZDResult ssmResult = NMAL0110Query.getInstance().getS21Parts(globalCompanyCode, bizMsg.mdseCd_H1.getValue(), cusaPartsViewName);
		            if (ssmResult.isCodeNormal()) {
		            	//NMAM0834E=0,[@] already exists in [@]
		                //bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0834E", new String[] {bizMsg.mdseCd_H1.getValue(), "S21 Parts" });
		                //return;
		        		 //NMAM8532E=0,When you register @ ,please select Registration Mode S21 Parts.[@]
		        		 bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM8532E", new String[]{"S21 Parts", bizMsg.mdseCd_H1.getValue()});
		                 return;
		            }
	        	}
	            ORD_TAKE_MDSETMsg duplicateOrdTakeMdseTMsg = NMAL0110CommonLogic.getDuplicateOrdTakeMdseTMsg(globalCompanyCode, bizMsg.mdseCd_H1.getValue());
	            if (duplicateOrdTakeMdseTMsg != null) {
	            	//NMAM0072E=0, @  is duplicated.
	                bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number" });
	        		return;
	            }
	            AMER_MSTRTMsg duplicateAmerMstrTMsg = NMAL0110CommonLogic.getDuplicateAmerMstrTMsg(globalCompanyCode, bizMsg.mdseCd_H1.getValue());
	            if (duplicateAmerMstrTMsg != null) {
	            	//NMAM0072E=0, @  is duplicated.
	                bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number" });
	        		return;
	            }
	            
	        	if (ZYPCommonFunc.hasValue(cusaPartsViewName)) {
	        		S21SsmEZDResult ssmPartsResult = NMAL0110Query.getInstance().getDuplicateS21Parts(globalCompanyCode, bizMsg.mdseCd_H1.getValue(), cusaPartsViewName);
		            if (ssmPartsResult.isCodeNormal()) {
		            	//NMAM0072E=0, @  is duplicated.
		                bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number" });
		        		return;
		            }
	        	}
	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
				//bizMsg.mdseCd_H1.setErrorInfo(1, "NDAM0007E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
				//bizMsg.mdseRgtnTpCd_H1.setErrorInfo(1, "NDAM0007E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
	        	//NMAM8381E=0,The Item Number is not registered yet ,If you register new item , please input information and click submit.
				bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM8381E");
				bizMsg.mdseRgtnTpCd_H1.setErrorInfo(1, "NMAM8381E");
	            return;
	        }
		}
        bizMsg.attrbTmplDescTxt_H1.clear();
	}

    @SuppressWarnings("unchecked")
	public static void setScreenValueFromMapForCopy(NMAL0110CMsg bizMsg, Map<String, Object> map) {
		if (map != null && map.get("GLBL_CMPY_CD") != null) {
			//################
			//Header
			//################
			//Only initial search case, not template search case
			ZYPEZDItemValueSetter.setValue(bizMsg.mnfItemCd_H1, (String) map.get("MNF_ITEM_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.upcCd_H1, (String) map.get("UPC_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_H1, (String) map.get("MDSE_ITEM_STS_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescLongTxt_H1, (String) map.get("MDSE_DESC_LONG_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemActvDt_H1, (String) map.get("MDSE_ITEM_ACTV_DT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseRgtnTpCd_H1, (String) map.get("MDSE_RGTN_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplNm_H1, (String) map.get("MDSE_CRAT_TMPL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplNm_H2, (String) map.get("MDSE_CRAT_TMPL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd_H1, (String) map.get("MDSE_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemClsTpCd_H1, (String) map.get("MDSE_ITEM_CLS_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpCd_H1, (String) map.get("COA_MDSE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, (String) map.get("COA_PROJ_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, (String) map.get("COA_PROD_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, (String) map.get("COA_PROD_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prchGrpCd_H1, (String) map.get("PRCH_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlCd_H1, (String) map.get("MKT_MDL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlNm_H1, (String) map.get("MKT_MDL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegCd_H1, (String) map.get("MKT_MDL_SEG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegNm_H1, (String) map.get("MKT_MDL_SEG_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlCd_H1, (String) map.get("ZEROTH_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlNm_H1, (String) map.get("ZEROTH_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlCd_H1, (String) map.get("FIRST_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlNm_H1, (String) map.get("FIRST_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlCd_H1, (String) map.get("SCD_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlNm_H1, (String) map.get("SCD_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlCd_H1, (String) map.get("THIRD_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlNm_H1, (String) map.get("THIRD_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlCd_H1, (String) map.get("FRTH_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlNm_H1, (String) map.get("FRTH_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplPk_H1, (BigDecimal) map.get("MDSE_CRAT_TMPL_PK"));
            ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_H1, getFlgValueFromFields((String) map.get("THIRD_PTY_ITEM_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsCmsnGrpCd_H1, (String) map.get("DS_CMSN_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsCmsnGrpDescTxt_H1, (String) map.get("DS_CMSN_GRP_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_01, (String) map.get("SLS_MAT_GRP_CD_01"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_01, (String) map.get("SLS_MAT_GRP_DESC_TXT_01"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_02, (String) map.get("SLS_MAT_GRP_CD_02"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_02, (String) map.get("SLS_MAT_GRP_DESC_TXT_02"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_03, (String) map.get("SLS_MAT_GRP_CD_03"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_03, (String) map.get("SLS_MAT_GRP_DESC_TXT_03"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prntCmpySetMdseFlg_H1, (String) map.get("PRNT_CMPY_SET_MDSE_FLG"));

			//################
			//General Tab
			//################
			//Dimensions
			ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_EA, (BigDecimal) map.get("IN_POUND_WT_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_EA, (BigDecimal) map.get("IN_INCH_LG_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_EA, (BigDecimal) map.get("IN_INCH_WDT_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_EA, (BigDecimal) map.get("IN_INCH_HGT_EA"));
			
			ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_UN, (BigDecimal) map.get("IN_POUND_WT_UN"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_UN, (BigDecimal) map.get("IN_INCH_LG_UN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_UN, (BigDecimal) map.get("IN_INCH_WDT_UN"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_UN, (BigDecimal) map.get("IN_INCH_HGT_UN"));
            // 2019/10/28 QC#53741 Add Start
			if (map.get("IN_INCH_LG_UOM_CD_UN") != null && ((String) map.get("IN_INCH_LG_UOM_CD_UN")).length() > 0) {
				ZYPEZDItemValueSetter.setValue(bizMsg.inInchLgUomCd_UN, (String) map.get("IN_INCH_LG_UOM_CD_UN"));
			}
			if (map.get("IN_INCH_WDT_UOM_CD_UN") != null && ((String) map.get("IN_INCH_WDT_UOM_CD_UN")).length() > 0) {
	            ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdtUomCd_UN, (String) map.get("IN_INCH_WDT_UOM_CD_UN"));
			}
			if (map.get("IN_INCH_HGT_UOM_CD_UN") != null && ((String) map.get("IN_INCH_HGT_UOM_CD_UN")).length() > 0) {
				ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgtUomCd_UN, (String) map.get("IN_INCH_HGT_UOM_CD_UN"));
			}
            // 2019/10/28 QC#53741 Add End
			// set who Columun (General Tab Dimensions(Each))
			// Add Start 2016/11/08 QC#2872
            // Who column
            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G1, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G1, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G1, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G1, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G3, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G3, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G3, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G3, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G3, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G3, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G5, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G5, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G5, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G5, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G5, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G5, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G7, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G7, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G7, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G7, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G7, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G7, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G2, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G2, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G2, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G2, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G2, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G2, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G4, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G4, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G4, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G4, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G4, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G4, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G6, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G6, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G6, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G6, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G6, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G6, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G8, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G8, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G8, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G8, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G8, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G8, TABLE_MDSE_STORE_PKG);
            }
            // Add End 2016/11/08 QC#2872

			ZYPEZDItemValueSetter.setValue(bizMsg.prtItemTpCd_H1, (String) map.get("PRT_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtYieldOtptQty_H1, (BigDecimal) map.get("PRT_YIELD_OTPT_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtYieldDaysAot_H1, (BigDecimal) map.get("PRT_YIELD_DAYS_AOT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtSrvyReqFlg_H1, getFlgValueFromFields((String) map.get("PRT_SRVY_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcCovBaseTpCd_H1, (String) map.get("SVC_COV_BASE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swProdCatgCd_H1, (String) map.get("SW_PROD_CATG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swProdCatgDescTxt_H1, (String) map.get("SW_PROD_CATG_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.bdlMaintMdseCd_H1, (String) map.get("BDL_MAINT_MDSE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.maintPopAvalFlg_H1, getFlgValueFromFields((String) map.get("MAINT_POP_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.extMaintPopAvalFlg_H1, getFlgValueFromFields((String) map.get("EXT_MAINT_POP_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.frtClsCd_H1, (String) map.get("FRT_CLS_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frtClsDescTxt_H1, (String) map.get("FRT_CLS_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemNum_H1, (String) map.get("NMFC_ITEM_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemSubNum_H1, (String) map.get("NMFC_ITEM_SUB_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.nmfcClsNum_H1, (BigDecimal) map.get("NMFC_CLS_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.maintItemTermMthNum_H1, (BigDecimal) map.get("MAINT_ITEM_TERM_MTH_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyPvtLbTpCd_H1, (String) map.get("IMG_SPLY_PVT_LB_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsIntgMdseTpCd_H1, (String) map.get("DS_INTG_MDSE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.trfCd_H1, (String) map.get("TRF_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.trfDescTxt_H1, (String) map.get("TRF_DESC_TXT"));

			ZYPTableUtil.clear(bizMsg.K);
			if (ZYPCommonFunc.hasValue((String) map.get("GLBL_CMPY_CD"))) {
				S21SsmEZDResult rsPkgUomList = null;
				rsPkgUomList = NMAL0110Query.getInstance().getPkgUomList((String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
				if (rsPkgUomList.isCodeNormal()) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) rsPkgUomList.getResultObject();
					if (list != null && list.size() > 0) {
						int i = 0;
						for(Map<String, Object> mapPkgUom : list) {
							if (mapPkgUom != null) {
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).pkgUomSortNum_K1, (BigDecimal) mapPkgUom.get("PKG_UOM_SORT_NUM"));
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).pkgUomDescTxt_K1, (String) mapPkgUom.get("PKG_UOM_DESC_TXT"));
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).pkgUomCd_K1, (String) mapPkgUom.get("PKG_UOM_CD"));
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).inEachQty_K1, (BigDecimal) mapPkgUom.get("IN_EACH_QTY"));
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).primPkgUomFlg_K1, (String) mapPkgUom.get("PRIM_PKG_UOM_FLG"));
								if (ZYPCommonFunc.hasValue((String) mapPkgUom.get("PRIM_PKG_UOM_FLG"))
										&& FLG_ON_Y.equals((String) mapPkgUom.get("PRIM_PKG_UOM_FLG"))) {
									ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_K1, new BigDecimal(String.valueOf(i)));
								}
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).basePkgUomCd_K1, (String) mapPkgUom.get("BASE_PKG_UOM_CD"));
								// set who Column General Tab Unit of Measure
								// Add Start 2016/11/08 QC#2872
								// Who column
			                    // XX_REC_HIST_CRAT_TS
			                    ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistCratTs_K1, (String) mapPkgUom.get("XX_REC_HIST_CRAT_TS"));
			                    // XX_REC_HIST_CRAT_BY_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistCratByNm_K1, ZYPRecHistUtil.getFullNameForRecHist((String) mapPkgUom.get("XX_REC_HIST_CRAT_BY_NM")));
			                    // XX_REC_HIST_UPD_TS
			                    ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistUpdTs_K1, (String) mapPkgUom.get("XX_REC_HIST_UPD_TS"));
			                    // XX_REC_HIST_UPD_BY_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistUpdByNm_K1, ZYPRecHistUtil.getFullNameForRecHist((String) mapPkgUom.get("XX_REC_HIST_UPD_BY_NM")));
			                    // XX_REC_HIST_TBL_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistTblNm_K1, (String) mapPkgUom.get("XX_REC_HIST_TBL_NM"));
			                    // Add End 2016/11/08 QC#2872
								i++;
							}
						}
						bizMsg.K.setValidCount(i);
					}
				}
			}
			
			ZYPTableUtil.clear(bizMsg.Q); //Clear Backup
			ZYPTableUtil.clear(bizMsg.N);
			S21SsmEZDResult rsTermCondOptList = null;
			rsTermCondOptList = NMAL0110Query.getInstance().getTermCondOptList((String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
			if (rsTermCondOptList.isCodeNormal()) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) rsTermCondOptList.getResultObject();
				if (list != null && list.size() > 0) {
					int i = 0;
					for(Map<String, Object> mapTermCondOpt : list) {
						if (mapTermCondOpt != null) {
							ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).termCondOptTpCd_N1, (String) mapTermCondOpt.get("TERM_COND_OPT_TP_CD"));
							ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).termCondOptValTxt_N1, (String) mapTermCondOpt.get("TERM_COND_OPT_VAL_TXT"));
						}
						i++;
					}
					bizMsg.N.setValidCount(i);
				}
			}
			

			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_ME, getFlgValueFromFields((String) map.get("MAIN_MACH_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.backOrdImpctTpCd_H1, (String) map.get("BACK_ORD_IMPCT_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RM, getFlgValueFromFields((String) map.get("RE_MNF_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyOemMnfCd_H1, (String) map.get("IMG_SPLY_OEM_MNF_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyOemCd_H1, (String) map.get("IMG_SPLY_OEM_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyTpCd_H1, (String) map.get("IMG_SPLY_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyColorTpCd_H1, (String) map.get("IMG_SPLY_COLOR_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldCnt_H1, (BigDecimal) map.get("IMG_SPLY_YIELD_CNT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldUomCd_H1, (String) map.get("IMG_SPLY_YIELD_UOM_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldTpCd_H1, (String) map.get("IMG_SPLY_YIELD_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.easyPackTpCd_H1, (String) map.get("EASY_PACK_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.areaOfPaperNum_H1, (BigDecimal) map.get("AREA_OF_PAPER_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PD, getFlgValueFromFields((String) map.get("PRT_DROP_SHIP_DSBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B3, getFlgValueFromFields((String) map.get("PRT_DROP_SHIP_DSBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcCovTmplTpCd_H1, (String) map.get("SVC_COV_TMPL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcAllocTpCd_H1, (String) map.get("SVC_ALLOC_TP_CD"));
			// 2017/09/25 QC#18534(L3#445) ADD Start
			ZYPEZDItemValueSetter.setValue(bizMsg.svcPgmTpCd_H1, (String) map.get("SVC_PGM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_OM, (String) map.get("OVRD_MNF_WTY_FLG"));
			// 2017/09/25 QC#18534(L3#445) ADD End
            ZYPEZDItemValueSetter.setValue(bizMsg.svcChrgItemTpCd_H1, (String) map.get("SVC_CHRG_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swVrsnTxt_H1, (String) map.get("SW_VRSN_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatFixQty_H1, (BigDecimal) map.get("SW_LIC_SEAT_FIX_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintCtrlTpCd_H1, (String) map.get("SW_MAINT_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntPerLicQty_H1, (BigDecimal) map.get("ASRN_PNT_PER_LIC_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintTpCd_H1, (String) map.get("SW_MAINT_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintTermYr_H1, (String) map.get("SW_MAINT_TERM_YR"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntMinQty_H1, (BigDecimal) map.get("ASRN_PNT_MIN_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntMaxQty_H1, (BigDecimal) map.get("ASRN_PNT_MAX_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntFixPerOrdQty_H1, (BigDecimal) map.get("ASRN_PNT_FIX_PER_ORD_QTY"));
			
			ZYPEZDItemValueSetter.setValue(bizMsg.swCatgCd_H1, (String) map.get("SW_CATG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swTpCd_H1, (String) map.get("SW_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicCtrlTpCd_H1, (String) map.get("SW_LIC_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatFromQty_H1, (BigDecimal) map.get("SW_LIC_SEAT_FROM_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatToQty_H1, (BigDecimal) map.get("SW_LIC_SEAT_TO_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_EC, getFlgValueFromFields((String) map.get("INTNT_CONN_SW_CTRL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HM, getFlgValueFromFields((String) map.get("HAZ_MAT_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazMatCd_H1, (String) map.get("HAZ_MAT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazClsNm_H1, (String) map.get("HAZ_CLS_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazPrpShipNm_H1, (String) map.get("HAZ_PRP_SHIP_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazIdNum_H1, (String) map.get("HAZ_ID_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.madeInCtryCd_H1, (String) map.get("MADE_IN_CTRY_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_MI, (String) map.get("MADE_IN_CTRY_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asmInCtryCd_H1, (String) map.get("ASM_IN_CTRY_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_AI, (String) map.get("ASM_IN_CTRY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.sowReqFlg_H1, getFlgValueFromFields((String) map.get("SOW_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomClsCd_H1, (String) map.get("PKG_UOM_CLS_CD"));
			// set who Column General Tab (Safety)
			// Add Start 2016/11/08 QC#2872
			// XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G9, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G9, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G9, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G9, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G9, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G9, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GA, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GA, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GA, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GA, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GA, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GA, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GB, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GB, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GB, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GB, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GB, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GB, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GC, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GC, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GC, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GC, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GC, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GC, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GD, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GD, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GD, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GD, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GD, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GD, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GE, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GE, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GE, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GE, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GE, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GE, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GF, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GF, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GF, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GF, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GF, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GF, TABLE_MDSE_SFTY_INFO);
            }
            // Add End 2016/11/08 QC#2872

	        //UOM
	        bizMsg.pkgUomCd_BL.clear();
	        bizMsg.pkgUomDescTxt_BL.clear();
	        NMAL0110CommonLogic.setInitialPkgUomPulldown(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("PKG_UOM_CLS_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomDescTxt_BA, (String) map.get("BASE_PKG_UOM_DESC_TXT"));

			//################
			//Inventory Tab
			//################
			//xxChkBox_IT
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IT, getFlgValueFromFields((String) map.get("INVTY_CTRL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B6, getFlgValueFromFields((String) map.get("INVTY_CTRL_FLG")));
			// 2020/04/13 QC#56494 Add Start
			//xxChkBox_II
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_II, getFlgValueFromFields((String) map.get("ITRL_ITEM_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_BA, getFlgValueFromFields((String) map.get("ITRL_ITEM_FLG")));
			// 2020/04/13 QC#56494 Add End
			//xxChkBox_RA
			if (RMA_REQ_TP.RMA_REQUIRED.equals((String) map.get("RMA_REQ_TP_CD"))) {
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RA, FLG_ON_Y);
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B8, FLG_ON_Y);
			} else {
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RA, FLG_OFF_N);
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B8, FLG_OFF_N);
			}
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RI, getFlgValueFromFields((String) map.get("RMA_INSP_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcWhCd_H1, (String) map.get("DEF_SRC_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_DW, (String) map.get("DEF_SRC_WH_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcSubWhCd_H1, (String) map.get("DEF_SRC_SUB_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_SW, (String) map.get("DEF_SRC_SUB_WH_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcProcrTpCd_H1, (String) map.get("DEF_SRC_PROCR_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.invtyHardAllocTpCd_H1, (String) map.get("INVTY_HARD_ALLOC_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToPrntVndCd_BK, (String) map.get("RTRN_TO_PRNT_VND_CD"));

			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RR, getFlgValueFromFields((String) map.get("RTRN_REQ_PRT_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnCtrlTpCd_H1, (String) map.get("RTRN_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnCtrlTpNm_H1, (String) map.get("RTRN_CTRL_TP_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnDsplTpCd_H1, (String) map.get("RTRN_DSPL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToVndCd_H1, (String) map.get("RTRN_TO_VND_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_V1, (String) map.get("LOC_NM_V1"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToPrntVndCd_H1, (String) map.get("RTRN_TO_PRNT_VND_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_P1, (String) map.get("LOC_NM_P1"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnWhCd_H1, (String) map.get("RTRN_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnCtrlTpVndRelnPk_H1, (BigDecimal) map.get("RTRN_CTRL_TP_VND_RELN_PK"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_W1, (String) map.get("LOC_NM_W1"));
	        
			String rsvSerTakeFlg = getFlgValueFromFields((String) map.get("RCV_SER_TAKE_FLG"));
			String shpgSerTakeFlg = getFlgValueFromFields((String) map.get("SHPG_SER_TAKE_FLG"));
			if (FLG_OFF_N.equals(rsvSerTakeFlg) && FLG_OFF_N.equals(shpgSerTakeFlg)) {
				ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_SS, BigDecimal.ZERO);
				ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_BK, BigDecimal.ZERO);
			} else if (FLG_ON_Y.equals(rsvSerTakeFlg) && FLG_ON_Y.equals(shpgSerTakeFlg)) {
				ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_SS, new BigDecimal("1"));
				ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_BK, new BigDecimal("1"));
			} else if (FLG_OFF_N.equals(rsvSerTakeFlg) && FLG_ON_Y.equals(shpgSerTakeFlg)) {
				ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_SS, new BigDecimal("2"));
				ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_BK, new BigDecimal("2"));
			}
			
			//Serial Range
			ZYPTableUtil.clear(bizMsg.A);
			ZYPTableUtil.clear(bizMsg.F);
			if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
				S21SsmEZDResult rsSerialRange = NMAL0110Query.getInstance().getSerialRange(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
				if (rsSerialRange.isCodeNormal()) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) rsSerialRange.getResultObject();
					if (list != null && list.size() > 0) {
						int i = 0;
						for(Map<String, Object> mapSerialRange : list) {
							if (mapSerialRange != null && mapSerialRange.get("MDSE_CD") != null) {
								ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).fromSerNum_A1, (String) mapSerialRange.get("FROM_SER_NUM"));
								ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).thruSerNum_A1, (String) mapSerialRange.get("THRU_SER_NUM"));
								ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).lgSerNum_A1, (BigDecimal) mapSerialRange.get("LG_SER_NUM"));
								// set who Column Inventory Tab (Serial Number Control)
								// Add Start 2016/11/08 QC#2872
								// Who column
			                    // XX_REC_HIST_CRAT_TS
			                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratTs_A1, (String) mapSerialRange.get("XX_REC_HIST_CRAT_TS"));
			                    // XX_REC_HIST_CRAT_BY_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSerialRange.get("XX_REC_HIST_CRAT_BY_NM")));
			                    // XX_REC_HIST_UPD_TS
			                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdTs_A1, (String) mapSerialRange.get("XX_REC_HIST_UPD_TS"));
			                    // XX_REC_HIST_UPD_BY_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSerialRange.get("XX_REC_HIST_UPD_BY_NM")));
			                    // XX_REC_HIST_TBL_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistTblNm_A1, (String) mapSerialRange.get("XX_REC_HIST_TBL_NM"));
								// Add End 2016/11/08 QC#2872
								i++;
							}
						}
						bizMsg.A.setValidCount(i);
					}
				}
			}
			
			//################
			//Accounting Tab
			//################
			ZYPEZDItemValueSetter.setValue(bizMsg.revCoaAcctCd_H1, (String) map.get("REV_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_RV, (String) map.get("REV_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cogsCoaAcctCd_H1, (String) map.get("COGS_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_CG, (String) map.get("COGS_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.expCoaAcctCd_H1, (String) map.get("EXP_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_EX, (String) map.get("EXP_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.acrlCoaAcctCd_H1, (String) map.get("ACRL_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_AC, (String) map.get("ACRL_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_AC, (String) map.get("ACRL_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_H1, (String) map.get("LINE_BIZ_TP_CD"));
			//xxChkBox_IP
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IP, getFlgValueFromFields((String) map.get("INV_PSBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.dfrdAcctgRuleCd_H1, (String) map.get("DFRD_ACCTG_RULE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dfrdInvRuleCd_H1, (String) map.get("DFRD_INV_RULE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.taxExemTpCd_H1, (String) map.get("TAX_EXEM_TP_CD"));
			
			//################
			//Field Service Tab
			//################
			ZYPEZDItemValueSetter.setValue(bizMsg.svcWtyTpCd_H1, (String) map.get("SVC_WTY_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.wtyDaysAot_H1, (BigDecimal) map.get("WTY_DAYS_AOT"));
			//xxChkBox_MM
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_MM, getFlgValueFromFields((String) map.get("MTR_MACH_FLG")));
			//xxChkBox_IB
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IB, getFlgValueFromFields((String) map.get("INSTL_BASE_CTRL_FLG")));
			//xxChkBox_SC
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_SC, getFlgValueFromFields((String) map.get("SVC_CALL_ENBL_FLG")));
			//xxChkBox_IR
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IR, getFlgValueFromFields((String) map.get("IWR_ENBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.iwrMdlCd_H1, (String) map.get("IWR_MDL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.iwrMdseCd_H1, (String) map.get("IWR_MDSE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_IW, (String) map.get("IWR_MDSE_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemBillTpCd_H1, (String) map.get("MDSE_ITEM_BILL_TP_CD"));
			// START 2023/09/05 K.Watanabe [QC#53408, ADD]
			ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_H1, (String) map.get("SVC_ISTL_RULE_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlCallGrpNum_H1, (String) map.get("SVC_ISTL_CALL_GRP_NUM"));
			// END 2023/09/05 K.Watanabe [QC#53408, ADD]
			// START 2023/12/12 K.Watanabe [QC#61300, ADD]
			ZYPEZDItemValueSetter.setValue(bizMsg.svcDeinsRuleNum_H1, (String) map.get("SVC_DEINS_RULE_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcDeinsCallGrpNum_H1, (String) map.get("SVC_DEINS_CALL_GRP_NUM"));
			// END 2023/12/12 K.Watanabe [QC#61300, ADD]
			
			//################
			//Order Processing Tab
			//################
			//xxChkBox_CO
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CO, getFlgValueFromFields((String) map.get("CUST_ORD_ENBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoMinOrdQty_H1, (BigDecimal) map.get("CPO_MIN_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoMaxOrdQty_H1, (BigDecimal) map.get("CPO_MAX_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoIncrOrdQty_H1, (BigDecimal) map.get("CPO_INCR_ORD_QTY"));

			//xxChkBox_IE
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IE, getFlgValueFromFields((String) map.get("RE_MNF_ITEM_EXST_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CF, getFlgValueFromFields((String) map.get("CONFIG_FLG")));

			//################
			//Sup/Reln Tab
			//################
			ZYPTableUtil.clear(bizMsg.C);
			ZYPTableUtil.clear(bizMsg.H);
			if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
				S21SsmEZDResult rsSupd = NMAL0110Query.getInstance().getSupdReln(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
				if (rsSupd.isCodeNormal()) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) rsSupd.getResultObject();
					if (list != null && list.size() > 0) {
						int i = 0;
						for(Map<String, Object> mapSupd : list) {
							if (mapSupd != null && mapSupd.get("SUPD_FROM_MDSE_CD") != null) {
								ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).supdToMdseCd_C1, (String) mapSupd.get("SUPD_TO_MDSE_CD"));
								ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseDescShortTxt_C1, (String) mapSupd.get("MDSE_DESC_SHORT_TXT"));
								ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).supdCratDt_C1, (String) mapSupd.get("SUPD_CRAT_DT"));
								// set who Column Sup/Relation Tab (Supersession)
								// Add Start 2016/11/02 QC#2872
			                    // Who column
			                    // XX_REC_HIST_CRAT_TS
			                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistCratTs_C1, (String) mapSupd.get("XX_REC_HIST_CRAT_TS"));
			                    // XX_REC_HIST_CRAT_BY_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistCratByNm_C1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSupd.get("XX_REC_HIST_CRAT_BY_NM")));
			                    // XX_REC_HIST_UPD_TS
			                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistUpdTs_C1, (String) mapSupd.get("XX_REC_HIST_UPD_TS"));
			                    // XX_REC_HIST_UPD_BY_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistUpdByNm_C1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSupd.get("XX_REC_HIST_UPD_BY_NM")));
			                    // XX_REC_HIST_TBL_NM
			                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistTblNm_C1, (String) mapSupd.get("XX_REC_HIST_TBL_NM"));
			                    // Add End 2016/11/02 QC#2872
								i++;
							}
						}
						bizMsg.C.setValidCount(i);
					}
				}
			
				ZYPTableUtil.clear(bizMsg.D);
				ZYPTableUtil.clear(bizMsg.I);
				if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
					S21SsmEZDResult rsRelnMdse = NMAL0110Query.getInstance().getRelnMdse(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
					if (rsRelnMdse.isCodeNormal()) {
						List<Map<String, Object>> list = (List<Map<String, Object>>) rsRelnMdse.getResultObject();
						if (list != null && list.size() > 0) {
							int i = 0;
							for(Map<String, Object> mapRelnMdse : list) {
								if (mapRelnMdse != null && mapRelnMdse.get("MDSE_CD") != null) {
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1, (String) mapRelnMdse.get("MDSE_ITEM_RELN_TP_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).relnMdseCd_D1, (String) mapRelnMdse.get("RELN_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).mdseDescShortTxt_D1, (String) mapRelnMdse.get("MDSE_DESC_SHORT_TXT"));
									// set who Column Sup/Relation Tab (Relationships)
									// Add Start 2016/11/02 QC#2872
	                                // Who column
	                                // XX_REC_HIST_CRAT_TS
	                                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistCratTs_D1, (String) mapRelnMdse.get("XX_REC_HIST_CRAT_TS"));
	                                // XX_REC_HIST_CRAT_BY_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistCratByNm_D1, ZYPRecHistUtil.getFullNameForRecHist((String) mapRelnMdse.get("XX_REC_HIST_CRAT_BY_NM")));
	                                // XX_REC_HIST_UPD_TS
	                                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistUpdTs_D1, (String) mapRelnMdse.get("XX_REC_HIST_UPD_TS"));
	                                // XX_REC_HIST_UPD_BY_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistUpdByNm_D1, ZYPRecHistUtil.getFullNameForRecHist((String) mapRelnMdse.get("XX_REC_HIST_UPD_BY_NM")));
	                                // XX_REC_HIST_TBL_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistTblNm_D1, (String) mapRelnMdse.get("XX_REC_HIST_TBL_NM"));
	                                // Add End 2016/11/02 QC#2872
									i++;
								}
							}
							bizMsg.D.setValidCount(i);
						}
					}
				}
			}
			
			//################
			//################
			//Cust Ref Tab
			//################
			//################
			//if (INIT.equals(event)) {
				ZYPTableUtil.clear(bizMsg.E);
				ZYPTableUtil.clear(bizMsg.J);
				if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
					S21SsmEZDResult rsCustRef = NMAL0110Query.getInstance().getCustRef(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
					if (rsCustRef.isCodeNormal()) {
						List<Map<String, Object>> list = (List<Map<String, Object>>) rsCustRef.getResultObject();
						if (list != null && list.size() > 0) {
							int i = 0;
							for(Map<String, Object> mapCustRef : list) {
								if (mapCustRef != null && mapCustRef.get("MDSE_CD") != null) {
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).dsAcctNum_E1, (String) mapCustRef.get("DS_ACCT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).dsAcctNm_E1, (String) mapCustRef.get("DS_ACCT_NM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).custMdseCd_E1, (String) mapCustRef.get("CUST_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).custMdseNm_E1, (String) mapCustRef.get("CUST_MDSE_NM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).effFromDt_E1, (String) mapCustRef.get("EFF_FROM_DT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxChkBox_EN, getFlgValueFromFields((String) mapCustRef.get("CUST_MDSE_XREF_ENBL_FLG")));
									// set who Column Cust Ref Tab (Customer Item Reference)
									// Add Start 2016/11/02 QC#2872
                                    // Who column
                                    // XX_REC_HIST_CRAT_TS
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistCratTs_E1, (String) mapCustRef.get("XX_REC_HIST_CRAT_TS"));
                                    // XX_REC_HIST_CRAT_BY_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistCratByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) mapCustRef.get("XX_REC_HIST_CRAT_BY_NM")));
                                    // XX_REC_HIST_UPD_TS
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistUpdTs_E1, (String) mapCustRef.get("XX_REC_HIST_UPD_TS"));
                                    // XX_REC_HIST_UPD_BY_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistUpdByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) mapCustRef.get("XX_REC_HIST_UPD_BY_NM")));
                                    // XX_REC_HIST_TBL_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistTblNm_E1, (String) mapCustRef.get("XX_REC_HIST_TBL_NM"));
                                    // Add End 2016/11/02 QC#2872
									i++;
								}
							}
							bizMsg.E.setValidCount(i);
						}
					}
				}
			//}
		}
	}

    @SuppressWarnings("unchecked")
	private static void setScreenValueFromMap(NMAL0110CMsg bizMsg, Map<String, Object> map, String event) {
		if (map != null && map.get("GLBL_CMPY_CD") != null) {
			//################
			//Header
			//################
			//Only initial search case, not template search case
			if (INIT.equals(event)) {
			    ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_H1, (String) map.get("MDSE_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H1, (String) map.get("MDSE_DESC_SHORT_TXT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_BK, (String) map.get("MDSE_DESC_SHORT_TXT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemMnfCd_H1, (String) map.get("MDSE_ITEM_MNF_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemMnfCd_BK, (String) map.get("MDSE_ITEM_MNF_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemMnfNm_H1, (String) map.get("MDSE_ITEM_MNF_NM"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mnfItemCd_H1, (String) map.get("MNF_ITEM_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mnfItemCd_BK, (String) map.get("MNF_ITEM_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.upcCd_H1, (String) map.get("UPC_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.upcCd_BK, (String) map.get("UPC_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_H1, (String) map.get("MDSE_ITEM_STS_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_BK, (String) map.get("MDSE_ITEM_STS_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescLongTxt_H1, (String) map.get("MDSE_DESC_LONG_TXT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescLongTxt_BK, (String) map.get("MDSE_DESC_LONG_TXT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemActvDt_H1, (String) map.get("MDSE_ITEM_ACTV_DT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemActvDt_BK, (String) map.get("MDSE_ITEM_ACTV_DT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseRgtnTpCd_H1, (String) map.get("MDSE_RGTN_TP_CD"));
			}
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplNm_H1, (String) map.get("MDSE_CRAT_TMPL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplNm_H2, (String) map.get("MDSE_CRAT_TMPL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplNm_BK, (String) map.get("MDSE_CRAT_TMPL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplCratDt_H1, (String) map.get("MDSE_CRAT_TMPL_CRAT_DT"));
			
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd_H1, (String) map.get("MDSE_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd_BK, (String) map.get("MDSE_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemClsTpCd_H1, (String) map.get("MDSE_ITEM_CLS_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemClsTpCd_BK, (String) map.get("MDSE_ITEM_CLS_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpCd_H1, (String) map.get("COA_MDSE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, (String) map.get("COA_PROJ_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpCd_BK, (String) map.get("COA_MDSE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, (String) map.get("COA_PROD_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_BK, (String) map.get("COA_PROD_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, (String) map.get("COA_PROD_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prchGrpCd_H1, (String) map.get("PRCH_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prchGrpCd_BK, (String) map.get("PRCH_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdsePrcGrpCd_H1, (String) map.get("MDSE_PRC_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdsePrcGrpCd_BK, (String) map.get("MDSE_PRC_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlCd_H1, (String) map.get("MKT_MDL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlCd_BK, (String) map.get("MKT_MDL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlNm_H1, (String) map.get("MKT_MDL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegCd_H1, (String) map.get("MKT_MDL_SEG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegCd_BK, (String) map.get("MKT_MDL_SEG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegNm_H1, (String) map.get("MKT_MDL_SEG_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlCd_H1, (String) map.get("ZEROTH_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlCd_BK, (String) map.get("ZEROTH_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlNm_H1, (String) map.get("ZEROTH_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlCd_H1, (String) map.get("FIRST_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlCd_BK, (String) map.get("FIRST_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlNm_H1, (String) map.get("FIRST_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlCd_H1, (String) map.get("SCD_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlCd_BK, (String) map.get("SCD_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlNm_H1, (String) map.get("SCD_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlCd_H1, (String) map.get("THIRD_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlCd_BK, (String) map.get("THIRD_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlNm_H1, (String) map.get("THIRD_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlCd_H1, (String) map.get("FRTH_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlCd_BK, (String) map.get("FRTH_PROD_CTRL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlNm_H1, (String) map.get("FRTH_PROD_CTRL_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplPk_H1, (BigDecimal) map.get("MDSE_CRAT_TMPL_PK"));
            ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_H1, getFlgValueFromFields((String) map.get("THIRD_PTY_ITEM_FLG")));
            ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_BK, getFlgValueFromFields((String) map.get("THIRD_PTY_ITEM_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsCmsnGrpCd_H1, (String) map.get("DS_CMSN_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsCmsnGrpCd_BK, (String) map.get("DS_CMSN_GRP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsCmsnGrpDescTxt_H1, (String) map.get("DS_CMSN_GRP_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_01, (String) map.get("SLS_MAT_GRP_CD_01"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_B1, (String) map.get("SLS_MAT_GRP_CD_01"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_01, (String) map.get("SLS_MAT_GRP_DESC_TXT_01"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_02, (String) map.get("SLS_MAT_GRP_CD_02"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_B2, (String) map.get("SLS_MAT_GRP_CD_02"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_02, (String) map.get("SLS_MAT_GRP_DESC_TXT_02"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_03, (String) map.get("SLS_MAT_GRP_CD_03"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpCd_B3, (String) map.get("SLS_MAT_GRP_CD_03"));
			ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_03, (String) map.get("SLS_MAT_GRP_DESC_TXT_03"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prntCmpySetMdseFlg_H1, (String) map.get("PRNT_CMPY_SET_MDSE_FLG"));
			
			//Only initial search case, not template search case
			if (INIT.equals(event)) {
				//MDSE
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_M1, (String) map.get("EZUPTIME_M1"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_M1, (String) map.get("EZUPTIMEZONE_M1"));
				//MDSE
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_M2, (String) map.get("EZUPTIME_M2"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_M2, (String) map.get("EZUPTIMEZONE_M2"));
			}

			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_M3, (String) map.get("EZUPTIME_M3"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_M3, (String) map.get("EZUPTIMEZONE_M3"));
			//Only initial search case, not template search case
			if (INIT.equals(event)) {
				//MDSE_SFTY_INFO
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_M4, (String) map.get("EZUPTIME_M4"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_M4, (String) map.get("EZUPTIMEZONE_M4"));
				//MDSE_STORE_PKG UNBOX
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_M6, (String) map.get("EZUPTIME_M6"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_M6, (String) map.get("EZUPTIMEZONE_M6"));
				//MDSE_STORE_PKG EACH
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_M7, (String) map.get("EZUPTIME_M7"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_M7, (String) map.get("EZUPTIMEZONE_M7"));
				//MDSE_STORE_PKG UNBOX
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_MB, (String) map.get("EZUPTIME_MB"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_MB, (String) map.get("EZUPTIMEZONE_MB"));
				//MDSE_STORE_PKG EACH
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_MC, (String) map.get("EZUPTIME_MC"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_MC, (String) map.get("EZUPTIMEZONE_MC"));
				//SUPD
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_MF, (String) map.get("EZUPTIME_MF"));
				ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_MF, (String) map.get("EZUPTIMEZONE_MF"));
			}
			//MDSE_CRAT_TMPL_STORE_PKG EACH
			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_MG, (String) map.get("EZUPTIME_MG"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_MG, (String) map.get("EZUPTIMEZONE_MG"));
			//MDSE_CRAT_TMPL_STORE_PKG UNBOX
			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_MI, (String) map.get("EZUPTIME_MI"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_MI, (String) map.get("EZUPTIMEZONE_MI"));
			//ORD_TAKE_MDSE
			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_OM, (String) map.get("EZUPTIME_OM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_OM, (String) map.get("EZUPTIMEZONE_OM"));

			// Add Start 2016/11/02 QC#2872
            // Who column Header
            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs, (String) map.get("XX_REC_HIST_CRAT_TS"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs, (String) map.get("XX_REC_HIST_UPD_TS"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM")));
            // XX_REC_HIST_TBL_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm, (String) map.get("XX_REC_HIST_TBL_NM"));
            // Add End 2016/11/02 QC#2872

			//################
			//General Tab
			//################
			//Dimensions
			ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_EA, (BigDecimal) map.get("IN_POUND_WT_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_EA, (BigDecimal) map.get("IN_INCH_LG_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_EA, (BigDecimal) map.get("IN_INCH_WDT_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_EA, (BigDecimal) map.get("IN_INCH_HGT_EA"));
			//BK
			ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_BE, (BigDecimal) map.get("IN_POUND_WT_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_BE, (BigDecimal) map.get("IN_INCH_LG_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_BE, (BigDecimal) map.get("IN_INCH_WDT_EA"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_BE, (BigDecimal) map.get("IN_INCH_HGT_EA"));
			
			ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_UN, (BigDecimal) map.get("IN_POUND_WT_UN"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_UN, (BigDecimal) map.get("IN_INCH_LG_UN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_UN, (BigDecimal) map.get("IN_INCH_WDT_UN"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_UN, (BigDecimal) map.get("IN_INCH_HGT_UN"));
			//BK
			ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_BU, (BigDecimal) map.get("IN_POUND_WT_UN"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_BU, (BigDecimal) map.get("IN_INCH_LG_UN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_BU, (BigDecimal) map.get("IN_INCH_WDT_UN"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_BU, (BigDecimal) map.get("IN_INCH_HGT_UN"));
            // 2019/10/28 QC#53741 Add Start
			if (map.get("IN_INCH_LG_UOM_CD_UN") != null && ((String) map.get("IN_INCH_LG_UOM_CD_UN")).length() > 0) {
				ZYPEZDItemValueSetter.setValue(bizMsg.inInchLgUomCd_UN, (String) map.get("IN_INCH_LG_UOM_CD_UN"));
			}
			if (map.get("IN_INCH_WDT_UOM_CD_UN") != null && ((String) map.get("IN_INCH_WDT_UOM_CD_UN")).length() > 0) {
	            ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdtUomCd_UN, (String) map.get("IN_INCH_WDT_UOM_CD_UN"));
			}
			if (map.get("IN_INCH_HGT_UOM_CD_UN") != null && ((String) map.get("IN_INCH_HGT_UOM_CD_UN")).length() > 0) {
				ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgtUomCd_UN, (String) map.get("IN_INCH_HGT_UOM_CD_UN"));
			}
			//BK
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchLgUomCd_BU, (String) map.get("IN_INCH_LG_UOM_CD_UN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdtUomCd_BU, (String) map.get("IN_INCH_WDT_UOM_CD_UN"));
			ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgtUomCd_BU, (String) map.get("IN_INCH_HGT_UOM_CD_UN"));
            // 2019/10/28 QC#53741 Add End
			// set who Column General Tab (Dimensions(Each))
			// Add Start 2016/11/08 QC#2872
            // Who column
            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G1, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G1, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G1, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G1, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G3, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G3, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G3, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G3, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G3, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G3, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G5, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G5, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G5, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G5, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G5, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G5, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G7, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_EA"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G7, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_EA")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G7, (String) map.get("XX_REC_HIST_UPD_TS_MSP_EA"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G7, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_EA")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G7, (String) map.get("XX_REC_HIST_TBL_NM_MSP_EA"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G7, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G2, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G2, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G2, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G2, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G2, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G2, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G4, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G4, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G4, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G4, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G4, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G4, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G6, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G6, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G6, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G6, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G6, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G6, TABLE_MDSE_STORE_PKG);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G8, (String) map.get("XX_REC_HIST_CRAT_TS_MSP_UN"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G8, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSP_UN")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G8, (String) map.get("XX_REC_HIST_UPD_TS_MSP_UN"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G8, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSP_UN")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G8, (String) map.get("XX_REC_HIST_TBL_NM_MSP_UN"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G8, TABLE_MDSE_STORE_PKG);
            }
            // Add End 2016/11/08 QC#2872
			
			ZYPEZDItemValueSetter.setValue(bizMsg.prtItemTpCd_H1, (String) map.get("PRT_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtItemTpCd_BK, (String) map.get("PRT_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtYieldOtptQty_H1, (BigDecimal) map.get("PRT_YIELD_OTPT_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtYieldOtptQty_BK, (BigDecimal) map.get("PRT_YIELD_OTPT_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtYieldDaysAot_H1, (BigDecimal) map.get("PRT_YIELD_DAYS_AOT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtYieldDaysAot_BK, (BigDecimal) map.get("PRT_YIELD_DAYS_AOT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtSrvyReqFlg_H1, getFlgValueFromFields((String) map.get("PRT_SRVY_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.prtSrvyReqFlg_BK, getFlgValueFromFields((String) map.get("PRT_SRVY_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcCovBaseTpCd_H1, (String) map.get("SVC_COV_BASE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcCovBaseTpCd_BK, (String) map.get("SVC_COV_BASE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swProdCatgCd_H1, (String) map.get("SW_PROD_CATG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swProdCatgCd_BK, (String) map.get("SW_PROD_CATG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swProdCatgDescTxt_H1, (String) map.get("SW_PROD_CATG_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.bdlMaintMdseCd_H1, (String) map.get("BDL_MAINT_MDSE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.bdlMaintMdseCd_BK, (String) map.get("BDL_MAINT_MDSE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.maintPopAvalFlg_H1, getFlgValueFromFields((String) map.get("MAINT_POP_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.maintPopAvalFlg_BK, getFlgValueFromFields((String) map.get("MAINT_POP_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.extMaintPopAvalFlg_H1, getFlgValueFromFields((String) map.get("EXT_MAINT_POP_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.extMaintPopAvalFlg_BK, getFlgValueFromFields((String) map.get("EXT_MAINT_POP_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.frtClsCd_H1, (String) map.get("FRT_CLS_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frtClsCd_BK, (String) map.get("FRT_CLS_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.frtClsDescTxt_H1, (String) map.get("FRT_CLS_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemNum_H1, (String) map.get("NMFC_ITEM_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemSubNum_H1, (String) map.get("NMFC_ITEM_SUB_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.nmfcClsNum_H1, (BigDecimal) map.get("NMFC_CLS_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.maintItemTermMthNum_BK, (BigDecimal) map.get("MAINT_ITEM_TERM_MTH_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.maintItemTermMthNum_H1, (BigDecimal) map.get("MAINT_ITEM_TERM_MTH_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyPvtLbTpCd_H1, (String) map.get("IMG_SPLY_PVT_LB_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyPvtLbTpCd_BK, (String) map.get("IMG_SPLY_PVT_LB_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.trfCd_H1, (String) map.get("TRF_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.trfCd_BK, (String) map.get("TRF_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.trfDescTxt_H1, (String) map.get("TRF_DESC_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsIntgMdseTpCd_H1, (String) map.get("DS_INTG_MDSE_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dsIntgMdseTpCd_BK, (String) map.get("DS_INTG_MDSE_TP_CD"));

            // 2020/04/07 QC#56017 Add Start
            getOrdTakeMdseForMdseCd((String) map.get("GLBL_CMPY_CD"), bizMsg.mdseCd_H1.getValue(), bizMsg);
            // 2020/04/07 QC#56017 Add End

			//Units of Measure
			//Only initial search case, not template search case
			if (INIT.equals(event)) {
				ZYPTableUtil.clear(bizMsg.L); //Clear Backup
			}
			ZYPTableUtil.clear(bizMsg.K);
			if (ZYPCommonFunc.hasValue((String) map.get("GLBL_CMPY_CD"))) {
				S21SsmEZDResult rsPkgUomList = null;
				//Initial search case
				if (INIT.equals(event)) {
					rsPkgUomList = NMAL0110Query.getInstance().getPkgUomList((String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
				//Template search case
				} else {
					rsPkgUomList = NMAL0110Query.getInstance().getPkgUomTmplList((String) map.get("GLBL_CMPY_CD"), bizMsg.mdseCratTmplPk_H1.getValue());
				}
				if (rsPkgUomList.isCodeNormal()) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) rsPkgUomList.getResultObject();
					if (list != null && list.size() > 0) {
						int i = 0;
						for(Map<String, Object> mapPkgUom : list) {
							if (mapPkgUom != null) {
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).pkgUomSortNum_K1, (BigDecimal) mapPkgUom.get("PKG_UOM_SORT_NUM"));
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).pkgUomDescTxt_K1, (String) mapPkgUom.get("PKG_UOM_DESC_TXT"));
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).pkgUomCd_K1, (String) mapPkgUom.get("PKG_UOM_CD"));
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).inEachQty_K1, (BigDecimal) mapPkgUom.get("IN_EACH_QTY"));
								if (INIT.equals(event)) {
									ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTime_K1, (String) mapPkgUom.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTimeZone_K1, (String) mapPkgUom.get("EZUPTIMEZONE"));
									ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTime_KS, (String) mapPkgUom.get("EZUPTIME_KS"));
									ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTimeZone_KS, (String) mapPkgUom.get("EZUPTIMEZONE_KS"));
									ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTime_KT, (String) mapPkgUom.get("EZUPTIME_KT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTimeZone_KT, (String) mapPkgUom.get("EZUPTIMEZONE_KT"));
								}
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).primPkgUomFlg_K1, (String) mapPkgUom.get("PRIM_PKG_UOM_FLG"));
								// set who Column General Tab (Units of Measure)
								// Add Start 2016/11/08 QC#2872
                                // Who column
                                // XX_REC_HIST_CRAT_TS
                                ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistCratTs_K1, (String) mapPkgUom.get("XX_REC_HIST_CRAT_TS"));
                                // XX_REC_HIST_CRAT_BY_NM
                                ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistCratByNm_K1, ZYPRecHistUtil.getFullNameForRecHist((String) mapPkgUom.get("XX_REC_HIST_CRAT_BY_NM")));
                                // XX_REC_HIST_UPD_TS
                                ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistUpdTs_K1, (String) mapPkgUom.get("XX_REC_HIST_UPD_TS"));
                                // XX_REC_HIST_UPD_BY_NM
                                ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistUpdByNm_K1, ZYPRecHistUtil.getFullNameForRecHist((String) mapPkgUom.get("XX_REC_HIST_UPD_BY_NM")));
                                // XX_REC_HIST_TBL_NM
                                ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).xxRecHistTblNm_K1, (String) mapPkgUom.get("XX_REC_HIST_TBL_NM"));
                                // Add End 2016/11/08 QC#2872
								
								if (ZYPCommonFunc.hasValue((String) mapPkgUom.get("PRIM_PKG_UOM_FLG"))
										&& FLG_ON_Y.equals((String) mapPkgUom.get("PRIM_PKG_UOM_FLG"))) {
									ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_K1, new BigDecimal(String.valueOf(i)));
								}
								ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).basePkgUomCd_K1, (String) mapPkgUom.get("BASE_PKG_UOM_CD"));
								//Initial search case
								if (INIT.equals(event)) {
									// Backup
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).pkgUomSortNum_L1, (BigDecimal) mapPkgUom.get("PKG_UOM_SORT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).pkgUomDescTxt_L1, (String) mapPkgUom.get("PKG_UOM_DESC_TXT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).pkgUomCd_L1, (String) mapPkgUom.get("PKG_UOM_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).inEachQty_L1, (BigDecimal) mapPkgUom.get("IN_EACH_QTY"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTime_L1, (String) mapPkgUom.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTimeZone_L1, (String) mapPkgUom.get("EZUPTIMEZONE"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTime_LS, (String) mapPkgUom.get("EZUPTIME_KS"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTimeZone_LS, (String) mapPkgUom.get("EZUPTIMEZONE_KS"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTime_LT, (String) mapPkgUom.get("EZUPTIME_KT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTimeZone_LT, (String) mapPkgUom.get("EZUPTIMEZONE_KT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).primPkgUomFlg_L1, (String) mapPkgUom.get("PRIM_PKG_UOM_FLG"));
									ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).basePkgUomCd_L1, (String) mapPkgUom.get("BASE_PKG_UOM_CD"));
								//Template search case
								} else {
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).pkgUomSortNum_M1, (BigDecimal) mapPkgUom.get("PKG_UOM_SORT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).pkgUomDescTxt_M1, (String) mapPkgUom.get("PKG_UOM_DESC_TXT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).pkgUomCd_M1, (String) mapPkgUom.get("PKG_UOM_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).inEachQty_M1, (BigDecimal) mapPkgUom.get("IN_EACH_QTY"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).ezUpTime_MT, (String) mapPkgUom.get("EZUPTIME_KT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).ezUpTimeZone_MT, (String) mapPkgUom.get("EZUPTIMEZONE_KT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).primPkgUomFlg_M1, (String) mapPkgUom.get("PRIM_PKG_UOM_FLG"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).basePkgUomCd_M1, (String) mapPkgUom.get("BASE_PKG_UOM_CD"));
								}
								i++;
							}
						}
						bizMsg.K.setValidCount(i);
						//Only initial search case, not template search case
						if (INIT.equals(event)) {
							bizMsg.L.setValidCount(i); //Backup
						}
					}
				}
				
				//Only initial search case and Modification Mode, not template search case
				if (INIT.equals(event) && ZYPCommonFunc.hasValue(bizMsg.mdseCratTmplPk_H1)) {
					ZYPTableUtil.clear(bizMsg.M);
					rsPkgUomList = NMAL0110Query.getInstance().getPkgUomTmplList((String) map.get("GLBL_CMPY_CD"), bizMsg.mdseCratTmplPk_H1.getValue());
					if (rsPkgUomList.isCodeNormal()) {
						List<Map<String, Object>> list = (List<Map<String, Object>>) rsPkgUomList.getResultObject();
						if (list != null && list.size() > 0) {
							int i = 0;
							for(Map<String, Object> mapPkgUom : list) {
								if (mapPkgUom != null) {
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).pkgUomSortNum_M1, (BigDecimal) mapPkgUom.get("PKG_UOM_SORT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).pkgUomDescTxt_M1, (String) mapPkgUom.get("PKG_UOM_DESC_TXT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).pkgUomCd_M1, (String) mapPkgUom.get("PKG_UOM_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).inEachQty_M1, (BigDecimal) mapPkgUom.get("IN_EACH_QTY"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).ezUpTime_MT, (String) mapPkgUom.get("EZUPTIME_KT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).ezUpTimeZone_MT, (String) mapPkgUom.get("EZUPTIMEZONE_KT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).primPkgUomFlg_M1, (String) mapPkgUom.get("PRIM_PKG_UOM_FLG"));
									ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).basePkgUomCd_M1, (String) mapPkgUom.get("BASE_PKG_UOM_CD"));
									i++;
								}
							}
							bizMsg.M.setValidCount(i);
						}
					}
				}
			}
			
			if (INIT.equals(event)) {
				ZYPTableUtil.clear(bizMsg.Q); //Clear Backup
			}
			ZYPTableUtil.clear(bizMsg.N);
			S21SsmEZDResult rsTermCondOptList = null;
			if (INIT.equals(event)) {
				rsTermCondOptList = NMAL0110Query.getInstance().getTermCondOptList((String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
                if (rsTermCondOptList.isCodeNormal()) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) rsTermCondOptList.getResultObject();
                    if (list != null && list.size() > 0) {
                        int i = 0;
                        for(Map<String, Object> mapTermCondOpt : list) {
                            if (mapTermCondOpt != null) {
                                ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).mdseTermCondOptPk_N1, (BigDecimal) mapTermCondOpt.get("MDSE_TERM_COND_OPT_PK"));
                                ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).termCondOptTpCd_N1, (String) mapTermCondOpt.get("TERM_COND_OPT_TP_CD"));
                                ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).termCondOptValTxt_N1, (String) mapTermCondOpt.get("TERM_COND_OPT_VAL_TXT"));
                            }
                            if (INIT.equals(event)) {
                                ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).ezUpTime_N1, (String) mapTermCondOpt.get("EZUPTIME"));
                                ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).ezUpTimeZone_N1, (String) mapTermCondOpt.get("EZUPTIMEZONE"));
                                
                                ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).mdseTermCondOptPk_Q1, (BigDecimal) mapTermCondOpt.get("MDSE_TERM_COND_OPT_PK"));
                                ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).termCondOptTpCd_Q1, (String) mapTermCondOpt.get("TERM_COND_OPT_TP_CD"));
                                ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).termCondOptValTxt_Q1, (String) mapTermCondOpt.get("TERM_COND_OPT_VAL_TXT"));
                                ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).ezUpTime_Q1, (String) mapTermCondOpt.get("EZUPTIME"));
                                ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).ezUpTimeZone_Q1, (String) mapTermCondOpt.get("EZUPTIMEZONE"));
                                
                            } else {
                                bizMsg.N.no(i).ezUpTime_N1.clear();
                                bizMsg.N.no(i).ezUpTimeZone_N1.clear();
                            }
                            i++;
                        }
                        bizMsg.N.setValidCount(i);
                        //Only initial search case, not template search case
                        if (INIT.equals(event)) {
                            bizMsg.Q.setValidCount(i); //Backup
                        }
                    }
                }
            }
			if (rsTermCondOptList.isCodeNormal()) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) rsTermCondOptList.getResultObject();
				if (list != null && list.size() > 0) {
					int i = 0;
					for(Map<String, Object> mapTermCondOpt : list) {
						if (mapTermCondOpt != null) {
							ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).mdseTermCondOptPk_N1, (BigDecimal) mapTermCondOpt.get("MDSE_TERM_COND_OPT_PK"));
							ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).termCondOptTpCd_N1, (String) mapTermCondOpt.get("TERM_COND_OPT_TP_CD"));
							ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).termCondOptValTxt_N1, (String) mapTermCondOpt.get("TERM_COND_OPT_VAL_TXT"));
						}
						if (INIT.equals(event)) {
							ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).ezUpTime_N1, (String) mapTermCondOpt.get("EZUPTIME"));
							ZYPEZDItemValueSetter.setValue(bizMsg.N.no(i).ezUpTimeZone_N1, (String) mapTermCondOpt.get("EZUPTIMEZONE"));
							
							ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).mdseTermCondOptPk_Q1, (BigDecimal) mapTermCondOpt.get("MDSE_TERM_COND_OPT_PK"));
							ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).termCondOptTpCd_Q1, (String) mapTermCondOpt.get("TERM_COND_OPT_TP_CD"));
							ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).termCondOptValTxt_Q1, (String) mapTermCondOpt.get("TERM_COND_OPT_VAL_TXT"));
							ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).ezUpTime_Q1, (String) mapTermCondOpt.get("EZUPTIME"));
							ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).ezUpTimeZone_Q1, (String) mapTermCondOpt.get("EZUPTIMEZONE"));
							
						} else {
							bizMsg.N.no(i).ezUpTime_N1.clear();
							bizMsg.N.no(i).ezUpTimeZone_N1.clear();
						}
						i++;
					}
					bizMsg.N.setValidCount(i);
					//Only initial search case, not template search case
					if (INIT.equals(event)) {
						bizMsg.Q.setValidCount(i); //Backup
					}
				}
			}
			
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_ME, getFlgValueFromFields((String) map.get("MAIN_MACH_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B1, getFlgValueFromFields((String) map.get("MAIN_MACH_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.backOrdImpctTpCd_H1, (String) map.get("BACK_ORD_IMPCT_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.backOrdImpctTpCd_BK, (String) map.get("BACK_ORD_IMPCT_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RM, getFlgValueFromFields((String) map.get("RE_MNF_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B2, getFlgValueFromFields((String) map.get("RE_MNF_AVAL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyOemMnfCd_H1, (String) map.get("IMG_SPLY_OEM_MNF_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyOemMnfCd_BK, (String) map.get("IMG_SPLY_OEM_MNF_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyOemCd_BK, (String) map.get("IMG_SPLY_OEM_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyOemCd_H1, (String) map.get("IMG_SPLY_OEM_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyTpCd_BK, (String) map.get("IMG_SPLY_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyTpCd_H1, (String) map.get("IMG_SPLY_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyColorTpCd_H1, (String) map.get("IMG_SPLY_COLOR_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyColorTpCd_BK, (String) map.get("IMG_SPLY_COLOR_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldCnt_H1, (BigDecimal) map.get("IMG_SPLY_YIELD_CNT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldCnt_BK, (BigDecimal) map.get("IMG_SPLY_YIELD_CNT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldUomCd_H1, (String) map.get("IMG_SPLY_YIELD_UOM_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldUomCd_BK, (String) map.get("IMG_SPLY_YIELD_UOM_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldTpCd_H1, (String) map.get("IMG_SPLY_YIELD_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.imgSplyYieldTpCd_BK, (String) map.get("IMG_SPLY_YIELD_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.easyPackTpCd_H1, (String) map.get("EASY_PACK_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.easyPackTpCd_BK, (String) map.get("EASY_PACK_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.areaOfPaperNum_H1, (BigDecimal) map.get("AREA_OF_PAPER_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.areaOfPaperNum_BK, (BigDecimal) map.get("AREA_OF_PAPER_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PD, getFlgValueFromFields((String) map.get("PRT_DROP_SHIP_DSBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B3, getFlgValueFromFields((String) map.get("PRT_DROP_SHIP_DSBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcCovTmplTpCd_H1, (String) map.get("SVC_COV_TMPL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcCovTmplTpCd_BK, (String) map.get("SVC_COV_TMPL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcAllocTpCd_H1, (String) map.get("SVC_ALLOC_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcAllocTpCd_BK, (String) map.get("SVC_ALLOC_TP_CD"));
			// 2017/09/25 QC#18534(L3#445) ADD Start
			ZYPEZDItemValueSetter.setValue(bizMsg.svcPgmTpCd_H1, (String) map.get("SVC_PGM_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcPgmTpCd_BK, (String) map.get("SVC_PGM_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_OM, getFlgValueFromFields((String) map.get("OVRD_MNF_WTY_FLG")));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B7, getFlgValueFromFields((String) map.get("OVRD_MNF_WTY_FLG")));
            ZYPEZDItemValueSetter.setValue(bizMsg.ovrdMnfWtyFlg_H1, (String) map.get("OVRD_MNF_WTY_AVAL_FLG"));
			// 2017/09/25 QC#18534(L3#445) ADD End
            ZYPEZDItemValueSetter.setValue(bizMsg.svcChrgItemTpCd_H1, (String) map.get("SVC_CHRG_ITEM_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcChrgItemTpCd_BK, (String) map.get("SVC_CHRG_ITEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swVrsnTxt_H1, (String) map.get("SW_VRSN_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swVrsnTxt_BK, (String) map.get("SW_VRSN_TXT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatFixQty_H1, (BigDecimal) map.get("SW_LIC_SEAT_FIX_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatFixQty_BK, (BigDecimal) map.get("SW_LIC_SEAT_FIX_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintCtrlTpCd_H1, (String) map.get("SW_MAINT_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintCtrlTpCd_BK, (String) map.get("SW_MAINT_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntPerLicQty_H1, (BigDecimal) map.get("ASRN_PNT_PER_LIC_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntPerLicQty_BK, (BigDecimal) map.get("ASRN_PNT_PER_LIC_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintTpCd_H1, (String) map.get("SW_MAINT_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintTpCd_BK, (String) map.get("SW_MAINT_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintTermYr_H1, (String) map.get("SW_MAINT_TERM_YR"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swMaintTermYr_BK, (String) map.get("SW_MAINT_TERM_YR"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntMinQty_H1, (BigDecimal) map.get("ASRN_PNT_MIN_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntMinQty_BK, (BigDecimal) map.get("ASRN_PNT_MIN_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntMaxQty_H1, (BigDecimal) map.get("ASRN_PNT_MAX_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntMaxQty_BK, (BigDecimal) map.get("ASRN_PNT_MAX_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntFixPerOrdQty_H1, (BigDecimal) map.get("ASRN_PNT_FIX_PER_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asrnPntFixPerOrdQty_BK, (BigDecimal) map.get("ASRN_PNT_FIX_PER_ORD_QTY"));
			
			ZYPEZDItemValueSetter.setValue(bizMsg.swCatgCd_H1, (String) map.get("SW_CATG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swCatgCd_BK, (String) map.get("SW_CATG_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swTpCd_H1, (String) map.get("SW_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swTpCd_BK, (String) map.get("SW_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicCtrlTpCd_H1, (String) map.get("SW_LIC_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicCtrlTpCd_BK, (String) map.get("SW_LIC_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatFromQty_H1, (BigDecimal) map.get("SW_LIC_SEAT_FROM_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatFromQty_BK, (BigDecimal) map.get("SW_LIC_SEAT_FROM_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatToQty_H1, (BigDecimal) map.get("SW_LIC_SEAT_TO_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.swLicSeatToQty_BK, (BigDecimal) map.get("SW_LIC_SEAT_TO_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_EC, getFlgValueFromFields((String) map.get("INTNT_CONN_SW_CTRL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B4, getFlgValueFromFields((String) map.get("INTNT_CONN_SW_CTRL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HM, getFlgValueFromFields((String) map.get("HAZ_MAT_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B5, getFlgValueFromFields((String) map.get("HAZ_MAT_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazMatCd_H1, (String) map.get("HAZ_MAT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazMatCd_BK, (String) map.get("HAZ_MAT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazClsNm_H1, (String) map.get("HAZ_CLS_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazPrpShipNm_H1, (String) map.get("HAZ_PRP_SHIP_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.hazIdNum_H1, (String) map.get("HAZ_ID_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.madeInCtryCd_H1, (String) map.get("MADE_IN_CTRY_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.madeInCtryCd_BK, (String) map.get("MADE_IN_CTRY_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_MI, (String) map.get("MADE_IN_CTRY_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asmInCtryCd_H1, (String) map.get("ASM_IN_CTRY_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.asmInCtryCd_BK, (String) map.get("ASM_IN_CTRY_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_AI, (String) map.get("ASM_IN_CTRY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.sowReqFlg_H1, getFlgValueFromFields((String) map.get("SOW_REQ_FLG")));
            ZYPEZDItemValueSetter.setValue(bizMsg.sowReqFlg_BK, getFlgValueFromFields((String) map.get("SOW_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomClsCd_H1, (String) map.get("PKG_UOM_CLS_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomClsCd_BK, (String) map.get("PKG_UOM_CLS_CD"));
			// set who Column General Tab (Safety)
			// Add Start 2016/11/08 QC#2872
			// XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_G9, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_G9, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_G9, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_G9, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G9, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_G9, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GA, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GA, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GA, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GA, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GA, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GA, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GB, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GB, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GB, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GB, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GB, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GB, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GC, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GC, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GC, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GC, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GC, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GC, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GD, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GD, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GD, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GD, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GD, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GD, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GE, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GE, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GE, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GE, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GE, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GE, TABLE_MDSE_SFTY_INFO);
            }

            // XX_REC_HIST_CRAT_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_GF, (String) map.get("XX_REC_HIST_CRAT_TS_MSI"));
            // XX_REC_HIST_CRAT_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_GF, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM_MSI")));
            // XX_REC_HIST_UPD_TS
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_GF, (String) map.get("XX_REC_HIST_UPD_TS_MSI"));
            // XX_REC_HIST_UPD_BY_NM
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_GF, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM_MSI")));
            // XX_REC_HIST_TBL_NM
            if (ZYPCommonFunc.hasValue((String) map.get("XX_REC_HIST_TBL_NM_MSI"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GF, (String) map.get("XX_REC_HIST_TBL_NM_MSI"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_GF, TABLE_MDSE_SFTY_INFO);
            }
            // Add End 2016/11/08 QC#2872

	        //UOM
	        bizMsg.pkgUomCd_BL.clear();
	        bizMsg.pkgUomDescTxt_BL.clear();
	        NMAL0110CommonLogic.setInitialPkgUomPulldown(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("PKG_UOM_CLS_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomDescTxt_BA, (String) map.get("BASE_PKG_UOM_DESC_TXT"));

			//################
			//Inventory Tab
			//################
			//xxChkBox_IT
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IT, getFlgValueFromFields((String) map.get("INVTY_CTRL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B6, getFlgValueFromFields((String) map.get("INVTY_CTRL_FLG")));
			// 2020/04/13 QC#56494 Add Start
            //xxChkBox_II
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_II, getFlgValueFromFields((String) map.get("ITRL_ITEM_FLG")));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_BA, getFlgValueFromFields((String) map.get("ITRL_ITEM_FLG")));
            // 2020/04/13 QC#56494 Add End
			//xxChkBox_RA
			if (RMA_REQ_TP.RMA_REQUIRED.equals((String) map.get("RMA_REQ_TP_CD"))) {
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RA, FLG_ON_Y);
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B8, FLG_ON_Y);
			} else {
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RA, FLG_OFF_N);
				ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B8, FLG_OFF_N);
			}
			//xxChkBox_RI
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RI, getFlgValueFromFields((String) map.get("RMA_INSP_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B9, getFlgValueFromFields((String) map.get("RMA_INSP_REQ_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcWhCd_H1, (String) map.get("DEF_SRC_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcWhCd_BK, (String) map.get("DEF_SRC_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_DW, (String) map.get("DEF_SRC_WH_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcSubWhCd_H1, (String) map.get("DEF_SRC_SUB_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcSubWhCd_BK, (String) map.get("DEF_SRC_SUB_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_SW, (String) map.get("DEF_SRC_SUB_WH_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcProcrTpCd_H1, (String) map.get("DEF_SRC_PROCR_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.defSrcProcrTpCd_BK, (String) map.get("DEF_SRC_PROCR_TP_CD"));
			if (INIT.equals(event)) {
				ZYPEZDItemValueSetter.setValue(bizMsg.invtyHardAllocTpCd_BK, (String) map.get("INVTY_HARD_ALLOC_TP_CD"));
				ZYPEZDItemValueSetter.setValue(bizMsg.invtyHardAllocTpCd_H1, (String) map.get("INVTY_HARD_ALLOC_TP_CD"));
			}
			ZYPEZDItemValueSetter.setValue(bizMsg.dropRtrnVndCd_H1, (String) map.get("DROP_RTRN_VND_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dropRtrnVndCd_BK, (String) map.get("DROP_RTRN_VND_CD"));
			//xxChkBox_RR
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RR, getFlgValueFromFields((String) map.get("RTRN_REQ_PRT_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R1, getFlgValueFromFields((String) map.get("RTRN_REQ_PRT_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnCtrlTpCd_H1, (String) map.get("RTRN_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnCtrlTpCd_BK, (String) map.get("RTRN_CTRL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnCtrlTpNm_H1, (String) map.get("RTRN_CTRL_TP_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnDsplTpCd_H1, (String) map.get("RTRN_DSPL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnDsplTpCd_BK, (String) map.get("RTRN_DSPL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToVndCd_H1, (String) map.get("RTRN_TO_VND_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToVndCd_BK, (String) map.get("RTRN_TO_VND_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_V1, (String) map.get("LOC_NM_V1"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToPrntVndCd_H1, (String) map.get("RTRN_TO_PRNT_VND_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToPrntVndCd_BK, (String) map.get("RTRN_TO_PRNT_VND_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_P1, (String) map.get("LOC_NM_P1"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnWhCd_H1, (String) map.get("RTRN_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnWhCd_BK, (String) map.get("RTRN_WH_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.rtrnCtrlTpVndRelnPk_H1, (BigDecimal) map.get("RTRN_CTRL_TP_VND_RELN_PK"));
			ZYPEZDItemValueSetter.setValue(bizMsg.locNm_W1, (String) map.get("LOC_NM_W1"));
	        //NMAL0110CommonLogic.setRtrnVndPulldown(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("RTRN_CTRL_TP_CD"));
			
			if (INIT.equals(event)) {
				String rsvSerTakeFlg = getFlgValueFromFields((String) map.get("RCV_SER_TAKE_FLG"));
				String shpgSerTakeFlg = getFlgValueFromFields((String) map.get("SHPG_SER_TAKE_FLG"));
				if (FLG_OFF_N.equals(rsvSerTakeFlg) && FLG_OFF_N.equals(shpgSerTakeFlg)) {
					ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_SS, BigDecimal.ZERO);
					ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_BK, BigDecimal.ZERO);
				} else if (FLG_ON_Y.equals(rsvSerTakeFlg) && FLG_ON_Y.equals(shpgSerTakeFlg)) {
					ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_SS, new BigDecimal("1"));
					ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_BK, new BigDecimal("1"));
				} else if (FLG_OFF_N.equals(rsvSerTakeFlg) && FLG_ON_Y.equals(shpgSerTakeFlg)) {
					ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_SS, new BigDecimal("2"));
					ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_BK, new BigDecimal("2"));
				}
			}
			
			//Only initial search case, not template search case
			if (INIT.equals(event)) {
				//Serial Range
				ZYPTableUtil.clear(bizMsg.A);
				ZYPTableUtil.clear(bizMsg.F);
				if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
					S21SsmEZDResult rsSerialRange = NMAL0110Query.getInstance().getSerialRange(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
					if (rsSerialRange.isCodeNormal()) {
						List<Map<String, Object>> list = (List<Map<String, Object>>) rsSerialRange.getResultObject();
						if (list != null && list.size() > 0) {
							int i = 0;
							for(Map<String, Object> mapSerialRange : list) {
								if (mapSerialRange != null && mapSerialRange.get("MDSE_CD") != null) {
									ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).fromSerNum_A1, (String) mapSerialRange.get("FROM_SER_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).thruSerNum_A1, (String) mapSerialRange.get("THRU_SER_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).lgSerNum_A1, (BigDecimal) mapSerialRange.get("LG_SER_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTime_A1, (String) mapSerialRange.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTimeZone_A1, (String) mapSerialRange.get("EZUPTIMEZONE"));
									ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseSerNumRngPk_A1, (BigDecimal) mapSerialRange.get("MDSE_SER_NUM_RNG_PK"));
									// set who Column Inventory Tab (Serial Number Control)
									// Add Start 2016/11/08 QC#2872
	                                // Who column
	                                // XX_REC_HIST_CRAT_TS
	                                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratTs_A1, (String) mapSerialRange.get("XX_REC_HIST_CRAT_TS"));
	                                // XX_REC_HIST_CRAT_BY_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSerialRange.get("XX_REC_HIST_CRAT_BY_NM")));
	                                // XX_REC_HIST_UPD_TS
	                                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdTs_A1, (String) mapSerialRange.get("XX_REC_HIST_UPD_TS"));
	                                // XX_REC_HIST_UPD_BY_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSerialRange.get("XX_REC_HIST_UPD_BY_NM")));
	                                // XX_REC_HIST_TBL_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistTblNm_A1, (String) mapSerialRange.get("XX_REC_HIST_TBL_NM"));
	                                // Add End 2016/11/08 QC#2872
									
									// Backup
									ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).fromSerNum_F1, (String) mapSerialRange.get("FROM_SER_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).thruSerNum_F1, (String) mapSerialRange.get("THRU_SER_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).lgSerNum_F1, (BigDecimal) mapSerialRange.get("LG_SER_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).ezUpTime_F1, (String) mapSerialRange.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).ezUpTimeZone_F1, (String) mapSerialRange.get("EZUPTIMEZONE"));
									ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).mdseSerNumRngPk_F1, (BigDecimal) mapSerialRange.get("MDSE_SER_NUM_RNG_PK"));
									i++;
								}
							}
							bizMsg.A.setValidCount(i);
							bizMsg.F.setValidCount(i);
						}
					}
				}
			}
			
			//################
			//Accounting Tab
			//################
			if (INIT.equals(event)) {
				ZYPEZDItemValueSetter.setValue(bizMsg.thisMthTotStdCostAmt_H1, (BigDecimal) map.get("THIS_MTH_TOT_STD_COST_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.assetRecovCostAmt_H1, (BigDecimal) map.get("ASSET_RECOV_COST_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.assetRecovCostAmt_HB, (BigDecimal) map.get("ASSET_RECOV_COST_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.assetRecovCostAmtUpdDt_H1, (String) map.get("ASSET_RECOV_COST_AMT_UPD_DT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.prevAssetRecovCostAmt_H1, (BigDecimal) map.get("PREV_ASSET_RECOV_COST_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.thisMthTotStdCostAmt_HB, (BigDecimal) map.get("THIS_MTH_TOT_STD_COST_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdDt_H1, (String) map.get("MDSE_CST_UPD_DT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.lastMthTotStdCostAmt_H1, (BigDecimal) map.get("LAST_MTH_TOT_STD_COST_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.origFobAmt_H1, (BigDecimal) map.get("ORIG_FOB_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.origFobAmt_BK, (BigDecimal) map.get("ORIG_FOB_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.origFobAmt_HB, (BigDecimal) map.get("ORIG_FOB_AMT"));
				ZYPEZDItemValueSetter.setValue(bizMsg.origFobAmtUpdDt_H1, (String) map.get("ORIG_FOB_AMT_UPD_DT"));
			}
			ZYPEZDItemValueSetter.setValue(bizMsg.revCoaAcctCd_H1, (String) map.get("REV_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.revCoaAcctCd_BK, (String) map.get("REV_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_RV, (String) map.get("REV_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cogsCoaAcctCd_H1, (String) map.get("COGS_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cogsCoaAcctCd_BK, (String) map.get("COGS_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_CG, (String) map.get("COGS_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.expCoaAcctCd_H1, (String) map.get("EXP_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.expCoaAcctCd_BK, (String) map.get("EXP_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_EX, (String) map.get("EXP_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.acrlCoaAcctCd_H1, (String) map.get("ACRL_COA_ACCT_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_AC, (String) map.get("ACRL_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_AC, (String) map.get("ACRL_COA_ACCT_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_H1, (String) map.get("LINE_BIZ_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_BK, (String) map.get("LINE_BIZ_TP_CD"));
			//xxChkBox_IP
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IP, getFlgValueFromFields((String) map.get("INV_PSBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R2, getFlgValueFromFields((String) map.get("INV_PSBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.dfrdAcctgRuleCd_H1, (String) map.get("DFRD_ACCTG_RULE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dfrdAcctgRuleCd_BK, (String) map.get("DFRD_ACCTG_RULE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dfrdInvRuleCd_H1, (String) map.get("DFRD_INV_RULE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.dfrdInvRuleCd_BK, (String) map.get("DFRD_INV_RULE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.taxExemTpCd_H1, (String) map.get("TAX_EXEM_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.taxExemTpCd_BK, (String) map.get("TAX_EXEM_TP_CD"));
			
			//################
			//Field Service Tab
			//################
			ZYPEZDItemValueSetter.setValue(bizMsg.svcWtyTpCd_H1, (String) map.get("SVC_WTY_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcWtyTpCd_BK, (String) map.get("SVC_WTY_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.wtyDaysAot_H1, (BigDecimal) map.get("WTY_DAYS_AOT"));
			ZYPEZDItemValueSetter.setValue(bizMsg.wtyDaysAot_BK, (BigDecimal) map.get("WTY_DAYS_AOT"));
			//xxChkBox_MM
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_MM, getFlgValueFromFields((String) map.get("MTR_MACH_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R3, getFlgValueFromFields((String) map.get("MTR_MACH_FLG")));
			//xxChkBox_IB
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IB, getFlgValueFromFields((String) map.get("INSTL_BASE_CTRL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R4, getFlgValueFromFields((String) map.get("INSTL_BASE_CTRL_FLG")));
			//xxChkBox_SC
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_SC, getFlgValueFromFields((String) map.get("SVC_CALL_ENBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R6, getFlgValueFromFields((String) map.get("SVC_CALL_ENBL_FLG")));
			//xxChkBox_IR
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IR, getFlgValueFromFields((String) map.get("IWR_ENBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R7, getFlgValueFromFields((String) map.get("IWR_ENBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.iwrMdlCd_H1, (String) map.get("IWR_MDL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.iwrMdlCd_BK, (String) map.get("IWR_MDL_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.iwrMdseCd_H1, (String) map.get("IWR_MDSE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.iwrMdseCd_BK, (String) map.get("IWR_MDSE_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_IW, (String) map.get("IWR_MDSE_NM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemBillTpCd_H1, (String) map.get("MDSE_ITEM_BILL_TP_CD"));
			ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemBillTpCd_BK, (String) map.get("MDSE_ITEM_BILL_TP_CD"));
			// START 2023/09/05 K.Watanabe [QC#53408, ADD]
			ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_H1, (String) map.get("SVC_ISTL_RULE_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_BK, (String) map.get("SVC_ISTL_RULE_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlCallGrpNum_H1, (String) map.get("SVC_ISTL_CALL_GRP_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlCallGrpNum_BK, (String) map.get("SVC_ISTL_CALL_GRP_NUM"));
			// END 2023/09/05 K.Watanabe [QC#53408, ADD]
			// START 2023/12/12 K.Watanabe [QC#61300, ADD]
			ZYPEZDItemValueSetter.setValue(bizMsg.svcDeinsRuleNum_H1, (String) map.get("SVC_DEINS_RULE_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcDeinsRuleNum_BK, (String) map.get("SVC_DEINS_RULE_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcDeinsCallGrpNum_H1, (String) map.get("SVC_DEINS_CALL_GRP_NUM"));
			ZYPEZDItemValueSetter.setValue(bizMsg.svcDeinsCallGrpNum_BK, (String) map.get("SVC_DEINS_CALL_GRP_NUM"));
			// END 2023/12/12 K.Watanabe [QC#61300, ADD]
			
			//################
			//Order Processing Tab
			//################
			//xxChkBox_CO
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CO, getFlgValueFromFields((String) map.get("CUST_ORD_ENBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R8, getFlgValueFromFields((String) map.get("CUST_ORD_ENBL_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoMinOrdQty_H1, (BigDecimal) map.get("CPO_MIN_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoMinOrdQty_BK, (BigDecimal) map.get("CPO_MIN_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoMaxOrdQty_H1, (BigDecimal) map.get("CPO_MAX_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoMaxOrdQty_BK, (BigDecimal) map.get("CPO_MAX_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoIncrOrdQty_H1, (BigDecimal) map.get("CPO_INCR_ORD_QTY"));
			ZYPEZDItemValueSetter.setValue(bizMsg.cpoIncrOrdQty_BK, (BigDecimal) map.get("CPO_INCR_ORD_QTY"));

			//xxChkBox_IE
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IE, getFlgValueFromFields((String) map.get("RE_MNF_ITEM_EXST_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_R9, getFlgValueFromFields((String) map.get("RE_MNF_ITEM_EXST_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CF, getFlgValueFromFields((String) map.get("CONFIG_FLG")));
			ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_S1, getFlgValueFromFields((String) map.get("CONFIG_FLG")));

			//################
			//Sup/Reln Tab
			//################
			if (INIT.equals(event)) {
				ZYPTableUtil.clear(bizMsg.C);
				ZYPTableUtil.clear(bizMsg.H);
				if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
					S21SsmEZDResult rsSupd = NMAL0110Query.getInstance().getSupdReln(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
					if (rsSupd.isCodeNormal()) {
						List<Map<String, Object>> list = (List<Map<String, Object>>) rsSupd.getResultObject();
						if (list != null && list.size() > 0) {
							int i = 0;
							for(Map<String, Object> mapSupd : list) {
								if (mapSupd != null && mapSupd.get("SUPD_FROM_MDSE_CD") != null) {
									ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).supdToMdseCd_C1, (String) mapSupd.get("SUPD_TO_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseDescShortTxt_C1, (String) mapSupd.get("MDSE_DESC_SHORT_TXT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).supdRelnPk_C1, (BigDecimal) mapSupd.get("SUPD_RELN_PK"));
									ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).supdCratDt_C1, (String) mapSupd.get("SUPD_CRAT_DT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ezUpTime_C1, (String) mapSupd.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ezUpTimeZone_C1, (String) mapSupd.get("EZUPTIMEZONE"));
									// set who Column Sup/Relation Tab (Supersession)
									// Add Start 2016/11/02 QC#2872
	                                // Who column
	                                // XX_REC_HIST_CRAT_TS
	                                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistCratTs_C1, (String) mapSupd.get("XX_REC_HIST_CRAT_TS"));
	                                // XX_REC_HIST_CRAT_BY_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistCratByNm_C1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSupd.get("XX_REC_HIST_CRAT_BY_NM")));
	                                // XX_REC_HIST_UPD_TS
	                                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistUpdTs_C1, (String) mapSupd.get("XX_REC_HIST_UPD_TS"));
	                                // XX_REC_HIST_UPD_BY_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistUpdByNm_C1, ZYPRecHistUtil.getFullNameForRecHist((String) mapSupd.get("XX_REC_HIST_UPD_BY_NM")));
	                                // XX_REC_HIST_TBL_NM
	                                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistTblNm_C1, (String) mapSupd.get("XX_REC_HIST_TBL_NM"));
	                                // Add End 2016/11/02 QC#2872
									
									// Backup
									ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).supdToMdseCd_H1, (String) mapSupd.get("SUPD_TO_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).mdseDescShortTxt_HD, (String) mapSupd.get("MDSE_DESC_SHORT_TXT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).supdRelnPk_H1, (BigDecimal) mapSupd.get("SUPD_RELN_PK"));
									ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).supdCratDt_H1, (String) mapSupd.get("SUPD_CRAT_DT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).ezUpTime_H1, (String) mapSupd.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).ezUpTimeZone_H1, (String) mapSupd.get("EZUPTIMEZONE"));
									
									i++;
								}
							}
							bizMsg.C.setValidCount(i);
							bizMsg.H.setValidCount(i);
						}
					}
				}
			
				ZYPTableUtil.clear(bizMsg.D);
				ZYPTableUtil.clear(bizMsg.I);
				if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
					S21SsmEZDResult rsRelnMdse = NMAL0110Query.getInstance().getRelnMdse(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
					if (rsRelnMdse.isCodeNormal()) {
						List<Map<String, Object>> list = (List<Map<String, Object>>) rsRelnMdse.getResultObject();
						if (list != null && list.size() > 0) {
							int i = 0;
							for(Map<String, Object> mapRelnMdse : list) {
								if (mapRelnMdse != null && mapRelnMdse.get("MDSE_CD") != null) {
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).relnMdseCd_DB, (String) mapRelnMdse.get("RELN_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1, (String) mapRelnMdse.get("MDSE_ITEM_RELN_TP_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).relnMdseCd_D1, (String) mapRelnMdse.get("RELN_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).mdseDescShortTxt_D1, (String) mapRelnMdse.get("MDSE_DESC_SHORT_TXT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).ezUpTime_D1, (String) mapRelnMdse.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).ezUpTimeZone_D1, (String) mapRelnMdse.get("EZUPTIMEZONE"));
									// set who Column Sup/Relation Tab (Relationships)
									// Add Start 2016/11/02 QC#2872
                                    // Who column
                                    // XX_REC_HIST_CRAT_TS
                                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistCratTs_D1, (String) mapRelnMdse.get("XX_REC_HIST_CRAT_TS"));
                                    // XX_REC_HIST_CRAT_BY_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistCratByNm_D1, ZYPRecHistUtil.getFullNameForRecHist((String) mapRelnMdse.get("XX_REC_HIST_CRAT_BY_NM")));
                                    // XX_REC_HIST_UPD_TS
                                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistUpdTs_D1, (String) mapRelnMdse.get("XX_REC_HIST_UPD_TS"));
                                    // XX_REC_HIST_UPD_BY_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistUpdByNm_D1, ZYPRecHistUtil.getFullNameForRecHist((String) mapRelnMdse.get("XX_REC_HIST_UPD_BY_NM")));
                                    // XX_REC_HIST_TBL_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).xxRecHistTblNm_D1, (String) mapRelnMdse.get("XX_REC_HIST_TBL_NM"));
                                    // Add End 2016/11/02 QC#2872

									// Backup
									ZYPEZDItemValueSetter.setValue(bizMsg.I.no(i).relnMdseCd_IB, (String) mapRelnMdse.get("RELN_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.I.no(i).mdseItemRelnTpCd_I1, (String) mapRelnMdse.get("MDSE_ITEM_RELN_TP_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.I.no(i).relnMdseCd_I1, (String) mapRelnMdse.get("RELN_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.I.no(i).mdseDescShortTxt_I1, (String) mapRelnMdse.get("MDSE_DESC_SHORT_TXT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.I.no(i).ezUpTime_I1, (String) mapRelnMdse.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.I.no(i).ezUpTimeZone_I1, (String) mapRelnMdse.get("EZUPTIMEZONE"));
									
									i++;
								}
							}
							bizMsg.D.setValidCount(i);
							bizMsg.I.setValidCount(i);
						}
					}
				}
			}
			
			//################
			//################
			//Cust Ref Tab
			//################
			//################
			if (INIT.equals(event)) {
				ZYPTableUtil.clear(bizMsg.E);
				ZYPTableUtil.clear(bizMsg.J);
				if (ZYPCommonFunc.hasValue((String) map.get("MDSE_CD"))) {
					S21SsmEZDResult rsCustRef = NMAL0110Query.getInstance().getCustRef(bizMsg, (String) map.get("GLBL_CMPY_CD"), (String) map.get("MDSE_CD"));
					if (rsCustRef.isCodeNormal()) {
						List<Map<String, Object>> list = (List<Map<String, Object>>) rsCustRef.getResultObject();
						if (list != null && list.size() > 0) {
							int i = 0;
							for(Map<String, Object> mapCustRef : list) {
								if (mapCustRef != null && mapCustRef.get("MDSE_CD") != null) {
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).dsAcctNum_EB, (String) mapCustRef.get("DS_ACCT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).dsAcctNum_E1, (String) mapCustRef.get("DS_ACCT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).dsAcctNm_E1, (String) mapCustRef.get("DS_ACCT_NM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).custMdseCd_EB, (String) mapCustRef.get("CUST_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).custMdseCd_E1, (String) mapCustRef.get("CUST_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).custMdseNm_E1, (String) mapCustRef.get("CUST_MDSE_NM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).effFromDt_E1, (String) mapCustRef.get("EFF_FROM_DT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxChkBox_EN, getFlgValueFromFields((String) mapCustRef.get("CUST_MDSE_XREF_ENBL_FLG")));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).ezUpTime_E1, (String) mapCustRef.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).ezUpTimeZone_E1, (String) mapCustRef.get("EZUPTIMEZONE"));
									// set who Column Cust Ref Tab (Customer Item Reference)
									// Add Start 2016/11/02 QC#2872
                                    // Who column
                                    // XX_REC_HIST_CRAT_TS
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistCratTs_E1, (String) mapCustRef.get("XX_REC_HIST_CRAT_TS"));
                                    // XX_REC_HIST_CRAT_BY_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistCratByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) mapCustRef.get("XX_REC_HIST_CRAT_BY_NM")));
                                    // XX_REC_HIST_UPD_TS
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistUpdTs_E1, (String) mapCustRef.get("XX_REC_HIST_UPD_TS"));
                                    // XX_REC_HIST_UPD_BY_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistUpdByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) mapCustRef.get("XX_REC_HIST_UPD_BY_NM")));
                                    // XX_REC_HIST_TBL_NM
                                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistTblNm_E1, (String) mapCustRef.get("XX_REC_HIST_TBL_NM"));
                                    // Add End 2016/11/02 QC#2872
		
									// Backup
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).dsAcctNum_JB, (String) mapCustRef.get("DS_ACCT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).dsAcctNum_J1, (String) mapCustRef.get("DS_ACCT_NUM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).dsAcctNm_J1, (String) mapCustRef.get("DS_ACCT_NM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).custMdseCd_JB, (String) mapCustRef.get("CUST_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).custMdseCd_J1, (String) mapCustRef.get("CUST_MDSE_CD"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).custMdseNm_J1, (String) mapCustRef.get("CUST_MDSE_NM"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).effFromDt_J1, (String) mapCustRef.get("EFF_FROM_DT"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).xxChkBox_JN, getFlgValueFromFields((String) mapCustRef.get("CUST_MDSE_XREF_ENBL_FLG")));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).ezUpTime_J1, (String) mapCustRef.get("EZUPTIME"));
									ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).ezUpTimeZone_J1, (String) mapCustRef.get("EZUPTIMEZONE"));
									
									i++;
								}
							}
							bizMsg.E.setValidCount(i);
							bizMsg.J.setValidCount(i);
						}
					}
				}
			}
		}
	}	
    public static MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {
    	
    	MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        
    }
	
    public static MDSETMsg findDsMdseInfo(String glblCmpyCd, String mdseCd) {
    	
    	MDSETMsg dsMdseInfoTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21CacheTBLAccessor.findByKey(dsMdseInfoTMsg);
        
    }
		
    public static COA_PRODTMsg findCoaProd(String glblCmpyCd, String coaProd) {
    	
    	COA_PRODTMsg coaProdTMsg = new COA_PRODTMsg();
        ZYPEZDItemValueSetter.setValue(coaProdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaProdTMsg.coaProdCd, coaProd);
        return (COA_PRODTMsg) S21CacheTBLAccessor.findByKey(coaProdTMsg);
        
    }
	
    public static CTRYTMsg findCtry(String glblCmpyCd, String ctryCd) {
    	
    	CTRYTMsg ctryTMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(ctryTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ctryTMsg.ctryCd, ctryCd);
        return (CTRYTMsg) S21CacheTBLAccessor.findByKey(ctryTMsg);
        
    }
	
    public static SW_PROD_CATGTMsg findSwProdCatg(String glblCmpyCd, String swProdCatgCd) {
    	
    	SW_PROD_CATGTMsg swProdCatgTMsg = new SW_PROD_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(swProdCatgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(swProdCatgTMsg.swProdCatgCd, swProdCatgCd);
        return (SW_PROD_CATGTMsg) S21CacheTBLAccessor.findByKey(swProdCatgTMsg);
        
    }
	
    public static FRT_CLSTMsg findFrtCls(String glblCmpyCd, String frtClsCd) {
    	
    	FRT_CLSTMsg frtClsTMsg = new FRT_CLSTMsg();
        ZYPEZDItemValueSetter.setValue(frtClsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(frtClsTMsg.frtClsCd, frtClsCd);
        return (FRT_CLSTMsg) S21CacheTBLAccessor.findByKey(frtClsTMsg);
        
    }
	
    public static TRFTMsg findTrf(String glblCmpyCd, String trfCd) {
    	
    	TRFTMsg trfTMsg = new TRFTMsg();
        ZYPEZDItemValueSetter.setValue(trfTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(trfTMsg.trfCd, trfCd);
        return (TRFTMsg) S21CacheTBLAccessor.findByKey(trfTMsg);
        
    }
	
    //QC#10449
    //public static RTL_WHTMsg findRTL_WH(String glblCmpyCd, String rtlWhCd) {
    //	
    //    RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
    //    ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
    //    ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);
    //    rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);
    //    return rtlWhTMsg;
    //    
    //}
	
    public static COA_ACCTTMsg findCoaAcct(String glblCmpyCd, String coaAcctCd) {
    	
    	COA_ACCTTMsg coaAcctTMsg = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(coaAcctTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaAcctTMsg.coaAcctCd, coaAcctCd);
        return (COA_ACCTTMsg) S21CacheTBLAccessor.findByKey(coaAcctTMsg);
        
    }
	
    public static HAZ_MATTMsg findHazMat(String glblCmpyCd, String hazMatCd) {
    	
    	HAZ_MATTMsg hazMatTMsg = new HAZ_MATTMsg();
        ZYPEZDItemValueSetter.setValue(hazMatTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hazMatTMsg.hazMatCd, hazMatCd);
        return (HAZ_MATTMsg) S21CacheTBLAccessor.findByKey(hazMatTMsg);
        
    }
	
    public static ORD_TAKE_MDSETMsg findOrdTakeMdse(String glblCmpyCd, String mdseCd) {
    	
    	ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
        return (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);
        
    }
	
    public static SUPDTMsg findSupd(String glblCmpyCd, String mdseCd) {
    	
    	SUPDTMsg supdTMsg = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(supdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(supdTMsg.mdseCd, mdseCd);
        return (SUPDTMsg) S21CacheTBLAccessor.findByKey(supdTMsg);
        
    }
    
    public static MDSE_ITEM_TPTMsg findMdseItemTp(String glblCmpyCd, String mdseItemTpCd) {
    	
    	MDSE_ITEM_TPTMsg mdseItemTpTMsg = new MDSE_ITEM_TPTMsg();
        ZYPEZDItemValueSetter.setValue(mdseItemTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseItemTpTMsg.mdseItemTpCd, mdseItemTpCd);
        return (MDSE_ITEM_TPTMsg) S21CacheTBLAccessor.findByKey(mdseItemTpTMsg);
        
    }

    public static COA_PROJTMsg findCoaProj(String glblCmpyCd, String coaMdseTpCd) {
    	
    	COA_PROJTMsg coaProjTMsg = new COA_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(coaProjTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaProjTMsg.coaProjCd, coaMdseTpCd);
        return (COA_PROJTMsg) S21CacheTBLAccessor.findByKey(coaProjTMsg);
        
    }
    
    public static SW_LIC_CTRL_TPTMsg findSwLicCtrlTp(String glblCmpyCd, String swLicCtrlTpCd) {
    	
    	SW_LIC_CTRL_TPTMsg swLicCtrlTpTMsg = new SW_LIC_CTRL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.swLicCtrlTpCd, swLicCtrlTpCd);
        return (SW_LIC_CTRL_TPTMsg) S21CacheTBLAccessor.findByKey(swLicCtrlTpTMsg);
        
    }
    
    public static SW_CATGTMsg findSwCatg(String glblCmpyCd, String swCatgCd) {
    	
    	SW_CATGTMsg swCatgTMsg = new SW_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(swCatgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(swCatgTMsg.swCatgCd, swCatgCd);
        return (SW_CATGTMsg) S21CacheTBLAccessor.findByKey(swCatgTMsg);
        
    }
    
    
    public static TERM_COND_OPT_TPTMsg findTermCondOptTp(String glblCmpyCd, String termCondOptTpCd) {
    	
    	TERM_COND_OPT_TPTMsg termCondOptTpTMsg = new TERM_COND_OPT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(termCondOptTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(termCondOptTpTMsg.termCondOptTpCd, termCondOptTpCd);
        return (TERM_COND_OPT_TPTMsg) S21CacheTBLAccessor.findByKey(termCondOptTpTMsg);
        
    }
    
    
    public static boolean deleteMdseSerNumRng(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

        MDSE_SER_NUM_RNGTMsg serNumRngRqst = new MDSE_SER_NUM_RNGTMsg();
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseSerNumRngPk, bizMsg.F.no(i).mdseSerNumRngPk_F1);
        serNumRngRqst = (MDSE_SER_NUM_RNGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(serNumRngRqst);
        if (serNumRngRqst != null) {
            String tmpTimeZone = serNumRngRqst.ezUpTimeZone.getValue();
            String tmpTime = serNumRngRqst.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.F.no(i).ezUpTime_F1.getValue(), bizMsg.F.no(i).ezUpTimeZone_F1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_SER_NUM_RNG", "MDSE_CD,MDSE_SER_NUM_RNG_PK", bizMsg.mdseCd_H1.getValue()+","+String.valueOf(bizMsg.F.no(i).mdseSerNumRngPk_F1.getValue())});
                return false;
            }
        }
        serNumRngRqst = new MDSE_SER_NUM_RNGTMsg();
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseSerNumRngPk, bizMsg.F.no(i).mdseSerNumRngPk_F1);
        //EZDTBLAccessor.logicalRemove(serNumRngRqst);
        int delCnt = S21FastTBLAccessor.removeLogical(new MDSE_SER_NUM_RNGTMsg[]{ serNumRngRqst });
        if (delCnt != 1) {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_SER_NUM_RNG", "MDSE_CD,MDSE_SER_NUM_RNG_PK", bizMsg.mdseCd_H1.getValue()+","+String.valueOf(bizMsg.F.no(i).mdseSerNumRngPk_F1.getValue())});
            return false;

        }
        return true;

    }
    
    public static void updateMdseSerNumRng(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {
    	
        MDSE_SER_NUM_RNGTMsg serNumRngRqst = new MDSE_SER_NUM_RNGTMsg();
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseSerNumRngPk, bizMsg.A.no(i).mdseSerNumRngPk_A1);
        serNumRngRqst = (MDSE_SER_NUM_RNGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(serNumRngRqst);
        if (serNumRngRqst != null) {
            String tmpTimeZone = serNumRngRqst.ezUpTimeZone.getValue();
            String tmpTime = serNumRngRqst.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A1.getValue(), bizMsg.A.no(i).ezUpTimeZone_A1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_SER_NUM_RNG", "MDSE_CD,MDSE_SER_NUM_RNG_PK", bizMsg.mdseCd_H1.getValue()+","+String.valueOf(serNumRngRqst.mdseSerNumRngPk.getValue())});
                return;
            }
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1)
        		&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)
        		&& ZYPCommonFunc.hasValue(bizMsg.A.no(i).lgSerNum_A1)) {
        	int size = bizMsg.A.no(i).lgSerNum_A1.getValueInt();
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.fromSerNum, paddingSerNumRng("0", size));
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.thruSerNum, paddingSerNumRng("Z", size));
        } else {
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.fromSerNum, bizMsg.A.no(i).fromSerNum_A1);
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.thruSerNum, bizMsg.A.no(i).thruSerNum_A1);
        }
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.lgSerNum, bizMsg.A.no(i).lgSerNum_A1);
        if (i==0) {
	        ZYPEZDItemValueSetter.setValue(serNumRngRqst.serNumDefFlg, FLG_ON_Y);
        } else {
	        ZYPEZDItemValueSetter.setValue(serNumRngRqst.serNumDefFlg, FLG_OFF_N);
        }
        S21FastTBLAccessor.update(serNumRngRqst);
        if (!RETURN_CD_NORMAL.equals(serNumRngRqst.getReturnCode())) {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_SER_NUM_RNG", "MDSE_CD,MDSE_SER_NUM_RNG_PK", bizMsg.mdseCd_H1.getValue()+","+String.valueOf(serNumRngRqst.mdseSerNumRngPk.getValue())});
            return;
        }
        
    }
    
    private static String paddingSerNumRng(String paddingStr, int size) {
    	
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(paddingStr);
		}
		return sb.toString();
		
	}
    
    public static boolean chkDetailListExists(NMAL0110CMsg bizMsg) {
    	
		boolean detailListChkFlg = false;
		for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) 
					&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
				continue;
			}
			detailListChkFlg = true;
		}
		return detailListChkFlg;
		
	}
	
    public static boolean insertMdseSerNumRng(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

        MDSE_SER_NUM_RNGTMsg serNumRngRqst = new MDSE_SER_NUM_RNGTMsg();
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseSerNumRngPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_SER_NUM_RNG_SQ));
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.mdseCd, bizMsg.mdseCd_H1);
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1)
        		&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)
        		&& ZYPCommonFunc.hasValue(bizMsg.A.no(i).lgSerNum_A1)) {
        	int size = bizMsg.A.no(i).lgSerNum_A1.getValueInt();
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.fromSerNum, paddingSerNumRng("0", size));
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.thruSerNum, paddingSerNumRng("Z", size));
        } else {
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.fromSerNum, bizMsg.A.no(i).fromSerNum_A1);
        	ZYPEZDItemValueSetter.setValue(serNumRngRqst.thruSerNum, bizMsg.A.no(i).thruSerNum_A1);
        }
        ZYPEZDItemValueSetter.setValue(serNumRngRqst.lgSerNum, bizMsg.A.no(i).lgSerNum_A1);
        if (i == 0) {
	        ZYPEZDItemValueSetter.setValue(serNumRngRqst.serNumDefFlg, FLG_ON_Y);
        } else {
	        ZYPEZDItemValueSetter.setValue(serNumRngRqst.serNumDefFlg, FLG_OFF_N);
        }
        //EZDTBLAccessor.create(serNumRngRqst);
        S21FastTBLAccessor.insert(serNumRngRqst);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(serNumRngRqst.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_SER_NUM_RNG", "MDSE_CD,MDSE_SER_NUM_RNG_PK", bizMsg.mdseCd_H1.getValue()+","+String.valueOf(serNumRngRqst.mdseSerNumRngPk.getValue())});
            return false;
        }
        return true;
        
    }

    public static boolean deleteSupdReln(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	SUPD_RELNTMsg supdReln = new SUPD_RELNTMsg();
    	ZYPEZDItemValueSetter.setValue(supdReln.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(supdReln.supdRelnPk, bizMsg.H.no(i).supdRelnPk_H1);
    	supdReln = (SUPD_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(supdReln);
        if (supdReln != null) {
            String tmpTimeZone = supdReln.ezUpTimeZone.getValue();
            String tmpTime = supdReln.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.H.no(i).ezUpTime_H1.getValue(), bizMsg.H.no(i).ezUpTimeZone_H1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"SUPD_RELN", "SUPD_RELN_PK", String.valueOf(supdReln.supdRelnPk.getValueInt())});
                return false;
            }
        }
        supdReln = new SUPD_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(supdReln.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(supdReln.supdRelnPk, bizMsg.H.no(i).supdRelnPk_H1);
        //EZDTBLAccessor.logicalRemove(supdReln);
        int delCnt = S21FastTBLAccessor.removeLogical(new SUPD_RELNTMsg[]{ supdReln });
        if (delCnt != 1) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"SUPD_RELN", "SUPD_RELN_PK", String.valueOf(supdReln.supdRelnPk.getValueInt())});
            return false;
        }
        return true;

    }
    
    public static boolean deleteSupd(String globalCompanyCode, NMAL0110CMsg bizMsg) {

    	SUPDTMsg supd = new SUPDTMsg();
    	ZYPEZDItemValueSetter.setValue(supd.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(supd.mdseCd, bizMsg.mdseCd_H1);
    	supd = (SUPDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(supd);
        if (supd != null) {
            String tmpTimeZone = supd.ezUpTimeZone.getValue();
            String tmpTime = supd.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_MF.getValue(), bizMsg.ezUpTimeZone_M1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
                return false;
            }
        }
        supd = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(supd.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(supd.mdseCd, bizMsg.mdseCd_H1);
        //EZDTBLAccessor.logicalRemove(supd);
        int delCnt = S21FastTBLAccessor.removeLogical(new SUPDTMsg[]{ supd });
        if (delCnt != 1) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
            return false;
        }
        return true;

    }
	
    public static boolean deleteSupdTo(String globalCompanyCode, NMAL0110CMsg bizMsg, String mdseCd) {

    	SUPDTMsg supd = new SUPDTMsg();
    	ZYPEZDItemValueSetter.setValue(supd.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(supd.mdseCd, mdseCd);
        //EZDTBLAccessor.logicalRemove(supd);
    	int delCnt = S21FastTBLAccessor.removeLogical(new SUPDTMsg[]{ supd });
        if (delCnt != 1) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
            return false;
        }
        return true;

    }
	
    public static void updateSupdReln(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {
    	
    	SUPD_RELNTMsg supdReln = new SUPD_RELNTMsg();
    	ZYPEZDItemValueSetter.setValue(supdReln.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(supdReln.supdRelnPk, bizMsg.C.no(i).supdRelnPk_C1);
    	supdReln = (SUPD_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(supdReln);
        if (supdReln != null) {
            String tmpTimeZone = supdReln.ezUpTimeZone.getValue();
            String tmpTime = supdReln.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.C.no(i).ezUpTime_C1.getValue(), bizMsg.C.no(i).ezUpTimeZone_C1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"SUPD_RELN", "SUPD_RELN_PK", String.valueOf(supdReln.supdRelnPk.getValueInt())});
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(supdReln.supdToMdseCd, bizMsg.C.no(i).supdToMdseCd_C1);
        ZYPEZDItemValueSetter.setValue(supdReln.supdCratDt, bizMsg.C.no(i).supdCratDt_C1);
        S21FastTBLAccessor.update(supdReln);
        if (!RETURN_CD_NORMAL.equals(supdReln.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"SUPD_RELN", "SUPD_RELN_PK", String.valueOf(supdReln.supdRelnPk.getValueInt())});
            return;
        }
        
    }
	
    public static void updateSupd(String globalCompanyCode, NMAL0110CMsg bizMsg) {

    	SUPDTMsg supd = new SUPDTMsg();
    	ZYPEZDItemValueSetter.setValue(supd.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(supd.mdseCd, bizMsg.mdseCd_H1);
    	supd = (SUPDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(supd);
        if (supd != null) {
            String tmpTimeZone = supd.ezUpTimeZone.getValue();
            String tmpTime = supd.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_MF.getValue(), bizMsg.ezUpTimeZone_MF.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
                return;
            }
        }
        S21FastTBLAccessor.update(supd);
        if (!RETURN_CD_NORMAL.equals(supd.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
            return;
        }
        
    }
    
    public static void updateSupdTo(String globalCompanyCode, NMAL0110CMsg bizMsg, String mdseCd) {

    	SUPDTMsg supd = new SUPDTMsg();
    	ZYPEZDItemValueSetter.setValue(supd.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(supd.mdseCd, mdseCd);
    	supd = (SUPDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(supd);
        S21FastTBLAccessor.update(supd);
        if (!RETURN_CD_NORMAL.equals(supd.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
            return;
        }
        
    }
    
    public static boolean chkSupdExists(NMAL0110CMsg bizMsg) {
    	
		boolean supdChkFlg = false;
		for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1) 
					&& !ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdCratDt_C1)) {
				continue;
			}
			supdChkFlg = true;
		}
		return supdChkFlg;
		
	}
	
    public static boolean insertSupdReln(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

        SUPD_RELNTMsg supdReln = new SUPD_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(supdReln.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(supdReln.supdFromMdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(supdReln.supdRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SUPD_RELN_SQ));
        ZYPEZDItemValueSetter.setValue(supdReln.supdToMdseCd, bizMsg.C.no(i).supdToMdseCd_C1);
        ZYPEZDItemValueSetter.setValue(supdReln.supdCratDt, bizMsg.C.no(i).supdCratDt_C1);
        //EZDTBLAccessor.create(supdReln);
        S21FastTBLAccessor.insert(supdReln);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(supdReln.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"SUPD_RELN", "SUPD_RELN_PK", String.valueOf(supdReln.supdRelnPk.getValueInt())});
            return false;
        }
        return true;
        
    }
	
    public static boolean insertSupd(String globalCompanyCode, NMAL0110CMsg bizMsg) {

        SUPDTMsg supd = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(supd.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(supd.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(supd.incgFlg, FLG_OFF_N);
        //EZDTBLAccessor.create(supd);
        S21FastTBLAccessor.insert(supd);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(supd.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
            return false;
        }
        return true;
        
    }
    
    public static boolean insertSupdTo(String globalCompanyCode, NMAL0110CMsg bizMsg, String mdseCd) {

        SUPDTMsg supd = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(supd.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(supd.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(supd.incgFlg, FLG_OFF_N);
        //EZDTBLAccessor.create(supd);
        S21FastTBLAccessor.insert(supd);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(supd.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"SUPD", "MDSE_CD", supd.mdseCd.getValue()});
            return false;
        }
        return true;
        
    }
    
    
    public static boolean deleteMdseItemFlipSet(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	MDSE_ITEM_FLIP_SETTMsg mdseItemFlipSet = new MDSE_ITEM_FLIP_SETTMsg();
    	ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.mdseCd, bizMsg.mdseCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.relnMdseCd, bizMsg.I.no(i).relnMdseCd_I1);
    	mdseItemFlipSet = (MDSE_ITEM_FLIP_SETTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseItemFlipSet);
        if (mdseItemFlipSet != null) {
            String tmpTimeZone = mdseItemFlipSet.ezUpTimeZone.getValue();
            String tmpTime = mdseItemFlipSet.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.I.no(i).ezUpTime_I1.getValue(), bizMsg.I.no(i).ezUpTimeZone_I1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_ITEM_FLIP_SET", "MDSE_CD,RELN_MDSE_CD", mdseItemFlipSet.mdseCd.getValue()+","+mdseItemFlipSet.relnMdseCd.getValue()});
                return false;
            }
        }
        mdseItemFlipSet = new MDSE_ITEM_FLIP_SETTMsg();
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.relnMdseCd, bizMsg.I.no(i).relnMdseCd_I1);
        //EZDTBLAccessor.remove(mdseItemFlipSet);
        int delCnt = S21FastTBLAccessor.removeLogical(new MDSE_ITEM_FLIP_SETTMsg[]{mdseItemFlipSet});
        if (delCnt != 1) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_ITEM_FLIP_SET", "MDSE_CD,RELN_MDSE_CD", mdseItemFlipSet.mdseCd.getValue()+","+mdseItemFlipSet.relnMdseCd.getValue()});
            return false;
        }
        return true;

    }
    
    public static boolean updateMdseItemFlipSet(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	MDSE_ITEM_FLIP_SETTMsg mdseItemFlipSet = new MDSE_ITEM_FLIP_SETTMsg();
    	ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.mdseCd, bizMsg.mdseCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.relnMdseCd, bizMsg.D.no(i).relnMdseCd_D1);
    	mdseItemFlipSet = (MDSE_ITEM_FLIP_SETTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseItemFlipSet);
        if (mdseItemFlipSet != null) {
            String tmpTimeZone = mdseItemFlipSet.ezUpTimeZone.getValue();
            String tmpTime = mdseItemFlipSet.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.D.no(i).ezUpTime_D1.getValue(), bizMsg.D.no(i).ezUpTimeZone_D1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_ITEM_FLIP_SET", "MDSE_CD,RELN_MDSE_CD", mdseItemFlipSet.mdseCd.getValue()+","+mdseItemFlipSet.relnMdseCd.getValue()});
                return false;
            }
        }
        mdseItemFlipSet = new MDSE_ITEM_FLIP_SETTMsg();
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.relnMdseCd, bizMsg.D.no(i).relnMdseCd_D1);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.mdseItemRelnTpCd, bizMsg.D.no(i).mdseItemRelnTpCd_D1);
        S21FastTBLAccessor.update(mdseItemFlipSet);
        if (!RETURN_CD_NORMAL.equals(mdseItemFlipSet.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_ITEM_FLIP_SET", "MDSE_CD,RELN_MDSE_CD", mdseItemFlipSet.mdseCd.getValue()+","+mdseItemFlipSet.relnMdseCd.getValue()});
            return false;
        }
        return true;

    }
    
    public static boolean chkMdseItemFlipSetExists(NMAL0110CMsg bizMsg) {
    	
		boolean mdseItemFlipSetChkFlg = false;
		for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1) 
					&& !ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1)) {
				continue;
			}
			mdseItemFlipSetChkFlg = true;
		}
		return mdseItemFlipSetChkFlg;
		
	}
	
    public static boolean insertMdseItemFlipSet(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	MDSE_ITEM_FLIP_SETTMsg mdseItemFlipSet = new MDSE_ITEM_FLIP_SETTMsg();
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.relnMdseCd, bizMsg.D.no(i).relnMdseCd_D1);
        ZYPEZDItemValueSetter.setValue(mdseItemFlipSet.mdseItemRelnTpCd, bizMsg.D.no(i).mdseItemRelnTpCd_D1);
        S21FastTBLAccessor.removePhysical(new MDSE_ITEM_FLIP_SETTMsg[]{mdseItemFlipSet});
        //EZDTBLAccessor.create(mdseItemFlipSet);
        S21FastTBLAccessor.insert(mdseItemFlipSet);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseItemFlipSet.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_ITEM_FLIP_SET", "MDSE_CD,RELN_MDSE_CD", mdseItemFlipSet.mdseCd.getValue()+","+mdseItemFlipSet.relnMdseCd.getValue()});
            return false;
        }
        return true;
        
    }
	
	
    
    public static boolean deleteCustMdseXref(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	CUST_MDSE_XREFTMsg custMdseXref = new CUST_MDSE_XREFTMsg();
    	ZYPEZDItemValueSetter.setValue(custMdseXref.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(custMdseXref.mdseCd, bizMsg.mdseCd_H1);
    	ZYPEZDItemValueSetter.setValue(custMdseXref.dsAcctNum, bizMsg.J.no(i).dsAcctNum_J1);
    	ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseCd, bizMsg.J.no(i).custMdseCd_J1);
    	custMdseXref = (CUST_MDSE_XREFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(custMdseXref);
        if (custMdseXref != null) {
            String tmpTimeZone = custMdseXref.ezUpTimeZone.getValue();
            String tmpTime = custMdseXref.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.J.no(i).ezUpTime_J1.getValue(), bizMsg.J.no(i).ezUpTimeZone_J1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"CUST_MDSE_XREF", "MDSE_CD,DS_ACCT_NUM,CUST_MDSE_CD", custMdseXref.mdseCd.getValue()+","+custMdseXref.dsAcctNum.getValue()+","+custMdseXref.custMdseCd.getValue()});
                return false;
            }
        }
        custMdseXref = new CUST_MDSE_XREFTMsg();
        ZYPEZDItemValueSetter.setValue(custMdseXref.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(custMdseXref.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.dsAcctNum, bizMsg.J.no(i).dsAcctNum_J1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseCd, bizMsg.J.no(i).custMdseCd_J1);
        //EZDTBLAccessor.remove(custMdseXref);
        int delCnt = S21FastTBLAccessor.removeLogical(new CUST_MDSE_XREFTMsg[]{custMdseXref});
        if (delCnt != 1) {
            bizMsg.setMessageInfo("NZZM0003E");
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"CUST_MDSE_XREF", "MDSE_CD,DS_ACCT_NUM,CUST_MDSE_CD", custMdseXref.mdseCd.getValue()+","+custMdseXref.dsAcctNum.getValue()+","+custMdseXref.custMdseCd.getValue()});
        }
        return true;

    }
    
	
    public static void updateCustMdseXref(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	CUST_MDSE_XREFTMsg custMdseXref = new CUST_MDSE_XREFTMsg();
    	ZYPEZDItemValueSetter.setValue(custMdseXref.glblCmpyCd, globalCompanyCode);
    	ZYPEZDItemValueSetter.setValue(custMdseXref.mdseCd, bizMsg.mdseCd_H1);
    	ZYPEZDItemValueSetter.setValue(custMdseXref.dsAcctNum, bizMsg.E.no(i).dsAcctNum_EB);
    	ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseCd, bizMsg.E.no(i).custMdseCd_EB);
    	custMdseXref = (CUST_MDSE_XREFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(custMdseXref);
        if (custMdseXref != null) {
            String tmpTimeZone = custMdseXref.ezUpTimeZone.getValue();
            String tmpTime = custMdseXref.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.E.no(i).ezUpTime_E1.getValue(), bizMsg.E.no(i).ezUpTimeZone_E1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"CUST_MDSE_XREF", "MDSE_CD,DS_ACCT_NUM,CUST_MDSE_CD", custMdseXref.mdseCd.getValue()+","+custMdseXref.dsAcctNum.getValue()+","+custMdseXref.custMdseCd.getValue()});
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(custMdseXref.effFromDt, bizMsg.E.no(i).effFromDt_E1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseNm, bizMsg.E.no(i).custMdseNm_E1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseXrefEnblFlg, getFlgValueFromFields(bizMsg.E.no(i).xxChkBox_EN.getValue()));
        S21FastTBLAccessor.update(custMdseXref);
        if (!RETURN_CD_NORMAL.equals(custMdseXref.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"CUST_MDSE_XREF", "MDSE_CD,DS_ACCT_NUM,CUST_MDSE_CD", custMdseXref.mdseCd.getValue()+","+custMdseXref.dsAcctNum.getValue()+","+custMdseXref.custMdseCd.getValue()});
            return;
        }
        
    }
	
    public static boolean chkCustMdseXrefExists(NMAL0110CMsg bizMsg) {
    	
		boolean custMdseXrefChkFlg = false;
		for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1) 
					&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseCd_E1)
					&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseNm_E1)
					&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).effFromDt_E1)
					&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).xxChkBox_EN)
					) {
				continue;
			}
			custMdseXrefChkFlg = true;
		}
		return custMdseXrefChkFlg;
		
	}
	
    public static boolean insertCustMdseXref(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	CUST_MDSE_XREFTMsg custMdseXref = new CUST_MDSE_XREFTMsg();
        ZYPEZDItemValueSetter.setValue(custMdseXref.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(custMdseXref.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.dsAcctNum, bizMsg.E.no(i).dsAcctNum_E1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseCd, bizMsg.E.no(i).custMdseCd_E1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseNm, bizMsg.E.no(i).custMdseNm_E1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.effFromDt, bizMsg.E.no(i).effFromDt_E1);
        ZYPEZDItemValueSetter.setValue(custMdseXref.custMdseXrefEnblFlg, getFlgValueFromFields(bizMsg.E.no(i).xxChkBox_EN.getValue()));
        S21FastTBLAccessor.removePhysical(new CUST_MDSE_XREFTMsg[]{custMdseXref});
        //EZDTBLAccessor.create(custMdseXref);
        S21FastTBLAccessor.insert(custMdseXref);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(custMdseXref.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"CUST_MDSE_XREF", "MDSE_CD,DS_ACCT_NUM,CUST_MDSE_CD", custMdseXref.mdseCd.getValue()+","+custMdseXref.dsAcctNum.getValue()+","+custMdseXref.custMdseCd.getValue()});
            return false;
        }
        return true;
        
    }
	
    public static String getFlgValueFromFields(String flg) {
    	
    	return !ZYPCommonFunc.hasValue(flg) ? FLG_OFF_N : flg;    	
    	
    }
    
    //public static boolean updateMdse(String globalCompanyCode, NMAL0110CMsg bizMsg, MDSE_ITEM_STSTMsg mdseItemStsTMsg, Map<String, Object> mapIPCC, boolean mdse8digitPossibleFlg) {
    public static boolean updateMdse(String globalCompanyCode, NMAL0110CMsg bizMsg, MDSE_ITEM_STSTMsg mdseItemStsTMsg, 
    		Map<String, Object> mapIPCC, boolean mdse8digitPossibleFlg, MDSETMsg mdseTMsg, boolean isExistsMdse) {
    	
    	// ----------------------
    	// Auto
    	// ----------------------
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseTpCd, nvlString((String) mapIPCC.get("MDSE_TP_CD")));
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.cstmReqFlg, FLG_OFF_N);
    	if (mdse8digitPossibleFlg) {
	    	mdseTMsg.mercClsCd.setValue(MERC_CLS.MASS_PRODUCTION_GOODS);
    	} else {
	    	mdseTMsg.mercClsCd.clear();
    	}
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thirdPtyVndDropFlg, FLG_OFF_N);
    	mdseTMsg.etaDaysAot.clear();
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.invtyDistTpCd, DIST_TP.NONE);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.invtySoftAllocTpCd, SOFT_ALLOC_TP.NONE);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.showEtaFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.prodAuthReqFlg, FLG_OFF_N);
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.thisMthFobAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thisMthFobAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.thisMthInlndFrtAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thisMthInlndFrtAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.thisMthIntlFrtAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thisMthIntlFrtAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.thisMthImptDtyAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thisMthImptDtyAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.thisMthInsAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thisMthInsAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.nextMthFobAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.nextMthFobAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.nextMthInlndFrtAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.nextMthInlndFrtAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.nextMthIntlFrtAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.nextMthIntlFrtAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.nextMthImptDtyAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.nextMthImptDtyAmt, BigDecimal.ZERO);
    	}
    	if (!ZYPCommonFunc.hasValue(mdseTMsg.nextMthInsAmt)) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.nextMthInsAmt, BigDecimal.ZERO);
    	}
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.costCcyCd, CCY.US_DOLLAR);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.wrtDownFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.fobReCalcExclFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.carryChrgCalcManFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.dsctnFlg, mdseItemStsTMsg.dsctnFlg);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.sellHldFlg, mdseItemStsTMsg.sellHldFlg);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.basePrcAmt, BigDecimal.ZERO);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.sellBySelfFlg, FLG_ON_Y);
    	if (MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd_H1.getValue())) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.setMdseShipCpltFlg, FLG_ON_Y);
    	} else {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.setMdseShipCpltFlg, FLG_OFF_N);
    	}
    	String daysPriAllocNum = ZYPCodeDataUtil.getVarCharConstValue("DEF_DAYS_PRI_ALLOC_NUM", globalCompanyCode);
    	if (ZYPCommonFunc.hasValue(daysPriAllocNum)) {
    		BigDecimal defDaysPriAllocNum = null;
    		try {
    			defDaysPriAllocNum = new BigDecimal(daysPriAllocNum);
    		} catch (Exception e) {
    			defDaysPriAllocNum = DEF_DAYS_PRI_ALLOC_NUM;
    		}
	       	ZYPEZDItemValueSetter.setValue(mdseTMsg.daysPriAllocNum, defDaysPriAllocNum);
    	} else {
	       	ZYPEZDItemValueSetter.setValue(mdseTMsg.daysPriAllocNum, DEF_DAYS_PRI_ALLOC_NUM);
    	}
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.shpgOrdHldFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.prcCcyCd, CCY.US_DOLLAR);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.soAuthReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.expItemFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.distByWhFlg, FLG_OFF_N);
    	//mdseTMsg.trfCd.clear();
    	mdseTMsg.fifthProdCtrlCd.clear();
    	mdseTMsg.fifthProdCtrlNm.clear();
    	mdseTMsg.sixthProdCtrlCd.clear();
    	mdseTMsg.sixthProdCtrlNm.clear();
    	mdseTMsg.svnthProdCtrlCd.clear();
    	mdseTMsg.svnthProdCtrlNm.clear();
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.manPrcAllwOnOrdFlg, FLG_OFF_N);
    	mdseTMsg.trfApvlStsCd.clear();
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.autoLicAllocFlg, FLG_OFF_N);
    	if(!MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd_H1.getValue()) 
    			&& !MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd_H1.getValue())){
    		//change to rgtnStsCd on MDSE_ITEM_STS
    		ZYPEZDItemValueSetter.setValue(mdseTMsg.rgtnStsCd, mdseItemStsTMsg.rgtnStsCd);
        	//if (MDSE_ITEM_STS.INACTIVE.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
    	    //	ZYPEZDItemValueSetter.setValue(mdseTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
        	//} else {
    	    //	ZYPEZDItemValueSetter.setValue(mdseTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        	//}
    	}
    	
    	// ----------------------
    	// Header
    	// ----------------------
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseNm, getMdseNm(bizMsg.mdseDescShortTxt_H1.getValue()));
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.upcCd, bizMsg.upcCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseFmlNm, getMdseFmlNm(bizMsg.mdseDescLongTxt_H1.getValue()));
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.coaProdCd, bizMsg.coaProdCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.zerothProdCtrlCd, bizMsg.zerothProdCtrlCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.zerothProdCtrlNm, bizMsg.zerothProdCtrlNm_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.firstProdCtrlCd, bizMsg.firstProdCtrlCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.firstProdCtrlNm, bizMsg.firstProdCtrlNm_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.scdProdCtrlCd, bizMsg.scdProdCtrlCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.scdProdCtrlNm, bizMsg.scdProdCtrlNm_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thirdProdCtrlCd, bizMsg.thirdProdCtrlCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thirdProdCtrlNm, bizMsg.thirdProdCtrlNm_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.frthProdCtrlCd, bizMsg.frthProdCtrlCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.frthProdCtrlNm, bizMsg.frthProdCtrlNm_H1);

    	// ----------------------
    	// General Tab
    	// ----------------------
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.intgProdCatgCd, nvlString((String) mapIPCC.get("INTG_PROD_CATG_CD")));
    			
		BigDecimal serTakeFlg = nvlBigDecimal(bizMsg.xxRadioBtn_SS.getValue());
    	if (BigDecimal.ZERO.compareTo(serTakeFlg) == 0 ) {
    		ZYPEZDItemValueSetter.setValue(mdseTMsg.rcvSerTakeFlg, FLG_OFF_N);
    		ZYPEZDItemValueSetter.setValue(mdseTMsg.shpgSerTakeFlg, FLG_OFF_N);
    	} else if ((new BigDecimal("1")).compareTo(serTakeFlg) == 0 ) {
    		ZYPEZDItemValueSetter.setValue(mdseTMsg.rcvSerTakeFlg, FLG_ON_Y);
    		ZYPEZDItemValueSetter.setValue(mdseTMsg.shpgSerTakeFlg, FLG_ON_Y);
    	} else if ((new BigDecimal("2")).compareTo(serTakeFlg) == 0 ) {
    		ZYPEZDItemValueSetter.setValue(mdseTMsg.rcvSerTakeFlg, FLG_OFF_N);
    		ZYPEZDItemValueSetter.setValue(mdseTMsg.shpgSerTakeFlg, FLG_ON_Y);
    	}
		ZYPEZDItemValueSetter.setValue(mdseTMsg.frtClsCd, bizMsg.frtClsCd_H1);
		ZYPEZDItemValueSetter.setValue(mdseTMsg.trfCd, bizMsg.trfCd_H1);
		ZYPEZDItemValueSetter.setValue(mdseTMsg.trfApvlStsCd, APVL_STS.APPROVED);
    	
    	// ----------------------
    	// Inventory Tab
    	// ----------------------
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.invtyCtrlFlg, getFlgValueFromFields(bizMsg.xxChkBox_IT.getValue()));
    	// 2020/04/13 QC#56494 Add Start
        ZYPEZDItemValueSetter.setValue(mdseTMsg.itrlItemFlg, getFlgValueFromFields(bizMsg.xxChkBox_II.getValue()));
        // 2020/04/13 QC#56494 Add End
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.invtyValFlg, getFlgValueFromFields(bizMsg.xxChkBox_IT.getValue()));
    	if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_RA) && FLG_ON_Y.equals(bizMsg.xxChkBox_RA.getValue())) {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.rmaReqTpCd, RMA_REQ_TP.RMA_REQUIRED);
    	} else {
	    	ZYPEZDItemValueSetter.setValue(mdseTMsg.rmaReqTpCd, RMA_REQ_TP.NOT_RETURNABLE);
    	}
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.invtyHardAllocTpCd, bizMsg.invtyHardAllocTpCd_H1);
        //QC#18365 ADD START
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.dropRtrnVndCd, bizMsg.dropRtrnVndCd_H1);
        //QC#18365 ADD END
    	
    	// ----------------------
    	// Accounting Tab
    	// ----------------------
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.thisMthTotStdCostAmt, bizMsg.thisMthTotStdCostAmt_H1);
    	
    	// ----------------------
    	// Order Processing
    	// ----------------------
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.cpoMinOrdQty, bizMsg.cpoMinOrdQty_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.cpoMaxOrdQty, bizMsg.cpoMaxOrdQty_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTMsg.cpoIncrOrdQty, bizMsg.cpoIncrOrdQty_H1);

    	String yyyyMMdd = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");

        // ----------------------
        // Auto
        // ----------------------
        ZYPEZDItemValueSetter.setValue(mdseTMsg.custIstlFlg, FLG_OFF_N);
        mdseTMsg.dsCmsnGrpCd.clear();
        mdseTMsg.easyPackTpCd.clear();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.vndRtrnFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseRgtnTpCd, MDSE_RGTN_TP.MANUAL);
        mdseTMsg.prchUseLifeMthNum.clear();
        mdseTMsg.rntlUseLifeMthNum.clear();
        mdseTMsg.configLtDayNum.clear();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCatgCd, nvlString((String) mapIPCC.get("MDSE_CATG_CD")));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.assetMgtFlg, FLG_OFF_N);
        mdseTMsg.rsdlValAmt.clear();
        mdseTMsg.mdseFrtClsCd.clear();
        mdseTMsg.daysPriPoCratNum.clear();
        mdseTMsg.actlExchRate.clear();

        // ----------------------
        // Header
        // ----------------------
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemMnfCd, bizMsg.mdseItemMnfCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mnfItemCd, bizMsg.mnfItemCd_H1);
        // CUST_ORD_ENTRY_AVAL_FLG = Y
        if (FLG_ON_Y.equals(mdseItemStsTMsg.custOrdEntryAvalFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(mdseTMsg.mdseItemActvDt)) {
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemActvDt, yyyyMMdd);
            } else {
                //Changing from PreLaunch, PreLaunch Approved
                //change to Prelaunch Flag = Y
                if (FLG_ON_Y.equals(mdseItemStsTMsg.prevLnchFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemActvDt, yyyyMMdd);
                }
                //if (MDSE_ITEM_STS.PRELAUNCH.equals(mdseTMsg.mdseItemStsCd.getValue())
                //      || MDSE_ITEM_STS.PRELAUNCH_APPROVED.equals(mdseTMsg.mdseItemStsCd.getValue())) {
                //  ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemActvDt, yyyyMMdd);
                //}
            }
        }
        if (FLG_ON_Y.equals(bizMsg.thirdPtyItemFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.thirdPtyItemFlg, FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.thirdPtyItemFlg, FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseRgtnTpCd, bizMsg.mdseRgtnTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemStsCd, bizMsg.mdseItemStsCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemTpCd, bizMsg.mdseItemTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemClsTpCd, bizMsg.mdseItemClsTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.coaMdseTpCd, bizMsg.coaMdseTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.prchGrpCd, bizMsg.prchGrpCd_H1);
        //ZYPEZDItemValueSetter.setValue(mdseTMsg.mdsePrcGrpCd, bizMsg.mdsePrcGrpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mktMdlCd, bizMsg.mktMdlCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mktMdlSegCd, bizMsg.mktMdlSegCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseDescShortTxt, bizMsg.mdseDescShortTxt_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseDescLongTxt, bizMsg.mdseDescLongTxt_H1);
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCratTmplPk_H1)) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCratTmplPk, bizMsg.mdseCratTmplPk_H1); //TODO
        }
        
        ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_01, bizMsg.slsMatGrpCd_01);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_02, bizMsg.slsMatGrpCd_02);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_03, bizMsg.slsMatGrpCd_03);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.dsCmsnGrpCd, bizMsg.dsCmsnGrpCd_H1);
        
        // ----------------------
        // General Tab
        // ----------------------
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mainMachFlg, getFlgValueFromFields(bizMsg.xxChkBox_ME.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.backOrdImpctTpCd, bizMsg.backOrdImpctTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.reMnfAvalFlg, getFlgValueFromFields(bizMsg.xxChkBox_RM.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyOemMnfCd, bizMsg.imgSplyOemMnfCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyOemCd, bizMsg.imgSplyOemCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyTpCd, bizMsg.imgSplyTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyColorTpCd, bizMsg.imgSplyColorTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyYieldCnt, bizMsg.imgSplyYieldCnt_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyYieldUomCd, bizMsg.imgSplyYieldUomCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyYieldTpCd, bizMsg.imgSplyYieldTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.easyPackTpCd, bizMsg.easyPackTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.areaOfPaperNum, bizMsg.areaOfPaperNum_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.prtDropShipDsblFlg, getFlgValueFromFields(bizMsg.xxChkBox_PD.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcCovTmplTpCd, bizMsg.svcCovTmplTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcAllocTpCd, bizMsg.svcAllocTpCd_H1);
        // 2017/09/25 QC#18534(L3#445) ADD Start
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcPgmTpCd, bizMsg.svcPgmTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.ovrdMnfWtyFlg, getFlgValueFromFields(bizMsg.xxChkBox_OM.getValue()));
        // 2017/09/25 QC#18534(L3#445) ADD End
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swCatgCd, bizMsg.swCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swTpCd, bizMsg.swTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swVrsnTxt, bizMsg.swVrsnTxt_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swLicCtrlTpCd, bizMsg.swLicCtrlTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swLicSeatFromQty, bizMsg.swLicSeatFromQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swLicSeatToQty, bizMsg.swLicSeatToQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swLicSeatFixQty, bizMsg.swLicSeatFixQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swMaintCtrlTpCd, bizMsg.swMaintCtrlTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.asrnPntPerLicQty, bizMsg.asrnPntPerLicQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swMaintTpCd, bizMsg.swMaintTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swMaintTermYr, bizMsg.swMaintTermYr_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.asrnPntMinQty, bizMsg.asrnPntMinQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.asrnPntMaxQty, bizMsg.asrnPntMaxQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.asrnPntFixPerOrdQty, bizMsg.asrnPntFixPerOrdQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.intntConnSwCtrlFlg, getFlgValueFromFields(bizMsg.xxChkBox_EC.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.dsIntgMdseTpCd, bizMsg.dsIntgMdseTpCd_H1);
        //QC#7875
        if (isExistsMdse == false) {
            String cusaMdseTpCd = getCusaMdseTpCd(globalCompanyCode, bizMsg.mdseCd_H1.getValue());
            if (ZYPCommonFunc.hasValue(cusaMdseTpCd) && MDSE_TP.SET.equals(cusaMdseTpCd)) {
                ZYPEZDItemValueSetter.setValue(mdseTMsg.prntCmpySetMdseFlg, FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(mdseTMsg.prntCmpySetMdseFlg, FLG_OFF_N);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.prntCmpySetMdseFlg, getFlgValueFromFields(bizMsg.prntCmpySetMdseFlg_H1.getValue()));
        }
        if(FLG_ON_Y.equals(bizMsg.sowReqFlg_H1.getValue())){
            ZYPEZDItemValueSetter.setValue(mdseTMsg.sowReqFlg, FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.sowReqFlg, FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(mdseTMsg.pkgUomClsCd, bizMsg.pkgUomClsCd_H1);
        

        ZYPEZDItemValueSetter.setValue(mdseTMsg.prtItemTpCd, bizMsg.prtItemTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.prtYieldOtptQty, bizMsg.prtYieldOtptQty_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.prtYieldDaysAot, bizMsg.prtYieldDaysAot_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.prtSrvyReqFlg, getFlgValueFromFields(bizMsg.prtSrvyReqFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcCovBaseTpCd, bizMsg.svcCovBaseTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.swProdCatgCd, bizMsg.swProdCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.bdlMaintMdseCd, bizMsg.bdlMaintMdseCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.maintPopAvalFlg, getFlgValueFromFields(bizMsg.maintPopAvalFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.extMaintPopAvalFlg, getFlgValueFromFields(bizMsg.extMaintPopAvalFlg_H1.getValue()));
        //ZYPEZDItemValueSetter.setValue(mdseTMsg.nmfcClsTpCd, bizMsg.nmfcClsTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.maintItemTermMthNum, bizMsg.maintItemTermMthNum_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.imgSplyPvtLbTpCd, bizMsg.imgSplyPvtLbTpCd_H1);
        
        
        // ----------------------
        // Inventory Tab
        // ----------------------
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rmaInspReqFlg, getFlgValueFromFields(bizMsg.xxChkBox_RI.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.defSrcWhCd, bizMsg.defSrcWhCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.defSrcSubWhCd, bizMsg.defSrcSubWhCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.defSrcProcrTpCd, bizMsg.defSrcProcrTpCd_H1);
        
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rtrnReqPrtFlg, getFlgValueFromFields(bizMsg.xxChkBox_RR.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rtrnCtrlTpCd, bizMsg.rtrnCtrlTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rtrnDsplTpCd, bizMsg.rtrnDsplTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rtrnToVndCd, bizMsg.rtrnToVndCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rtrnToPrntVndCd, bizMsg.rtrnToPrntVndCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rtrnWhCd, bizMsg.rtrnWhCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.rtrnCtrlTpVndRelnPk, bizMsg.rtrnCtrlTpVndRelnPk_H1);

        // ----------------------
        // Accounting Tab
        // ----------------------
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdDt_H1)
                && nvlBigDecimal(bizMsg.thisMthTotStdCostAmt_H1.getValue()).compareTo(nvlBigDecimal(bizMsg.thisMthTotStdCostAmt_HB.getValue())) != 0) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCstUpdDt, yyyyMMdd);
        } else if (!ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdDt_H1) && ZYPCommonFunc.hasValue(bizMsg.thisMthTotStdCostAmt_H1)) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCstUpdDt, yyyyMMdd);
        } else {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCstUpdDt, bizMsg.mdseCstUpdDt_H1);
        }
        ZYPEZDItemValueSetter.setValue(mdseTMsg.origFobAmt, bizMsg.origFobAmt_H1);
        if (ZYPCommonFunc.hasValue(bizMsg.origFobAmtUpdDt_H1)
                && nvlBigDecimal(bizMsg.origFobAmt_H1.getValue()).compareTo(nvlBigDecimal(bizMsg.origFobAmt_HB.getValue())) != 0) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.origFobAmtUpdDt, yyyyMMdd);
        } else if (!ZYPCommonFunc.hasValue(bizMsg.origFobAmtUpdDt_H1) && ZYPCommonFunc.hasValue(bizMsg.origFobAmt_H1)) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.origFobAmtUpdDt, yyyyMMdd);
        } else {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.origFobAmtUpdDt, bizMsg.origFobAmtUpdDt_H1);
        }
        ZYPEZDItemValueSetter.setValue(mdseTMsg.assetRecovCostAmt, bizMsg.assetRecovCostAmt_H1);
        if (ZYPCommonFunc.hasValue(bizMsg.assetRecovCostAmtUpdDt_H1)
                && nvlBigDecimal(bizMsg.assetRecovCostAmt_H1.getValue()).compareTo(nvlBigDecimal(bizMsg.assetRecovCostAmt_HB.getValue())) != 0) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.assetRecovCostAmtUpdDt, yyyyMMdd);
        } else if (!ZYPCommonFunc.hasValue(bizMsg.assetRecovCostAmtUpdDt_H1) && ZYPCommonFunc.hasValue(bizMsg.assetRecovCostAmt_H1)) {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.assetRecovCostAmtUpdDt, yyyyMMdd);
        } else {
            ZYPEZDItemValueSetter.setValue(mdseTMsg.assetRecovCostAmtUpdDt, bizMsg.assetRecovCostAmtUpdDt_H1);
        }
        ZYPEZDItemValueSetter.setValue(mdseTMsg.revCoaAcctCd, bizMsg.revCoaAcctCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.cogsCoaAcctCd, bizMsg.cogsCoaAcctCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.expCoaAcctCd, bizMsg.expCoaAcctCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.invPsblFlg, getFlgValueFromFields(bizMsg.xxChkBox_IP.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.dfrdAcctgRuleCd, bizMsg.dfrdAcctgRuleCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.dfrdInvRuleCd, bizMsg.dfrdInvRuleCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.taxExemTpCd, bizMsg.taxExemTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.lineBizTpCd, bizMsg.lineBizTpCd_H1);
        mdseTMsg.coaCcCd.clear();
        
        // ----------------------
        // Field Service
        // ----------------------
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcWtyTpCd, bizMsg.svcWtyTpCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.wtyDaysAot, bizMsg.wtyDaysAot_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mtrMachFlg, getFlgValueFromFields(bizMsg.xxChkBox_MM.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.instlBaseCtrlFlg, getFlgValueFromFields(bizMsg.xxChkBox_IB.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcCallEnblFlg, getFlgValueFromFields(bizMsg.xxChkBox_SC.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.iwrEnblFlg, getFlgValueFromFields(bizMsg.xxChkBox_IR.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.iwrMdlCd, bizMsg.iwrMdlCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.iwrMdseCd, bizMsg.iwrMdseCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseItemBillTpCd, bizMsg.mdseItemBillTpCd_H1);
        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcIstlRuleNum, bizMsg.svcIstlRuleNum_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcIstlCallGrpNum, bizMsg.svcIstlCallGrpNum_H1);
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
        // START 2023/12/12 K.Watanabe [QC#61300, ADD]
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcDeinsRuleNum, bizMsg.svcDeinsRuleNum_H1);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.svcDeinsCallGrpNum, bizMsg.svcDeinsCallGrpNum_H1);
        // END 2023/12/12 K.Watanabe [QC#61300, ADD]
        
        // ----------------------
        // Order Processing
        // ----------------------
        ZYPEZDItemValueSetter.setValue(mdseTMsg.custOrdEnblFlg, getFlgValueFromFields(bizMsg.xxChkBox_CO.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.reMnfItemExstFlg, getFlgValueFromFields(bizMsg.xxChkBox_IE.getValue()));
        ZYPEZDItemValueSetter.setValue(mdseTMsg.configFlg, getFlgValueFromFields(bizMsg.xxChkBox_CF.getValue()));
        
    	if (isExistsMdse == false) {
            if(MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd_H1.getValue()) 
            		|| MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd_H1.getValue())){
                ZYPEZDItemValueSetter.setValue(mdseTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.dsctnFlg, FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.sellHldFlg, FLG_ON_Y);
                //NMAM8201I=0,Please BOM registration if you want to register a sales order.
                bizMsg.setMessageInfo("NMAM8201I");
            }

            ZYPEZDItemValueSetter.setValue(mdseTMsg.siteSrvyReqFlg, FLG_OFF_N);

            //EZDTBLAccessor.create(mdseTMsg);
            S21FastTBLAccessor.insert(mdseTMsg);
            if (!RETURN_CD_NORMAL.equals(mdseTMsg.getReturnCode())) {
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
                return false;
            }
        // Update
    	} else {
    		
            String tmpTimeZone = mdseTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_M1.getValue(), bizMsg.ezUpTimeZone_M1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
    		    return false;
            }
            S21FastTBLAccessor.update(mdseTMsg);
    		if (!RETURN_CD_NORMAL.equals(mdseTMsg.getReturnCode())) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
    		    return false;
    		}
    	}

    	return true;
    	
    }
    
    public static  String nvlString(String arg) {
    	
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
    public static String convFromBigDecimalToString(BigDecimal arg) {
    	
    	if (ZYPCommonFunc.hasValue(arg)) {
    		return String.valueOf(arg);
    	}
    	return "";
    	
    }

    public static boolean updateOrdTakeMdse(String globalCompanyCode, NMAL0110CMsg bizMsg) {
    	
    	ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
    	ordTakeMdseTMsg.glblCmpyCd.setValue(globalCompanyCode);
    	ordTakeMdseTMsg.ordTakeMdseCd.setValue(bizMsg.mdseCd_H1.getValue().substring(0, 8));
    	ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordTakeMdseTMsg);
    	boolean isExistsOrdTakeMdse = (ordTakeMdseTMsg != null);
    	// Only Insert
    	if (isExistsOrdTakeMdse == false) {
    		ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        	ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, globalCompanyCode);
        	ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, bizMsg.mdseCd_H1.getValue().substring(0, 8));
        	ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.mdseCd, bizMsg.mdseCd_H1);
            //EZDTBLAccessor.create(ordTakeMdseTMsg);
            S21FastTBLAccessor.insert(ordTakeMdseTMsg);
            if (!RETURN_CD_NORMAL.equals(ordTakeMdseTMsg.getReturnCode())) {
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"ORD_TAKE_MDSE", "ORD_TAKE_MDSE_CD", bizMsg.mdseCd_H1.getValue().substring(0, 8)});
                return false;
            }
    	} else {
    		ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.mdseCd, bizMsg.mdseCd_H1);
            S21FastTBLAccessor.update(ordTakeMdseTMsg);
    		if (!RETURN_CD_NORMAL.equals(ordTakeMdseTMsg.getReturnCode())) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"ORD_TAKE_MDSE", "MDSE_CD,ORD_TAKE_MDSE_CD", bizMsg.mdseCd_H1.getValue()});
                return false;
    		}
    	}
    	return true;
    	
    }
    
    public static boolean updateMdseStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, String pkgUomCd) {
    	
    	MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
    	mdseStorePkgTMsg.glblCmpyCd.setValue(globalCompanyCode);
    	mdseStorePkgTMsg.mdseCd.setValue(bizMsg.mdseCd_H1.getValue());
    	mdseStorePkgTMsg.pkgUomCd.setValue(pkgUomCd);
    	mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseStorePkgTMsg);
    	boolean isExistsMdseStorePkg = (mdseStorePkgTMsg != null);
    	if (isExistsMdseStorePkg == false) {
    		mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, globalCompanyCode);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, bizMsg.mdseCd_H1);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, pkgUomCd);
    	}
 
    	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)
    			&& !ZYPCommonFunc.hasValue(bizMsg.inInchHgt_UN) 
    			&& !ZYPCommonFunc.hasValue(bizMsg.inInchLg_UN)
    			&& !ZYPCommonFunc.hasValue(bizMsg.inInchWdt_UN)
    			&& !ZYPCommonFunc.hasValue(bizMsg.inPoundWt_UN)
    			) {
    		
    		if (isExistsMdseStorePkg) {
	            String tmpTime = mdseStorePkgTMsg.ezUpTime.getValue();
	            String tmpTimeZone = mdseStorePkgTMsg.ezUpTimeZone.getValue();
	            String ezUptime = "";
	            String ezTimeZone = "";
	            ezUptime = bizMsg.ezUpTime_M6.getValue();
	            ezTimeZone = bizMsg.ezUpTimeZone_M6.getValue();
	            if (!ZYPDateUtil.isSameTimeStamp(ezUptime, ezTimeZone, tmpTime, tmpTimeZone)) {
	            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
	                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+pkgUomCd});
	                return false;
	            }
	            //EZDTBLAccessor.remove(mdseStorePkgTMsg);
	            int delCnt = S21FastTBLAccessor.removeLogical(new MDSE_STORE_PKGTMsg[]{mdseStorePkgTMsg});
	    		if (delCnt != 1) {
	    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
	    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+pkgUomCd});
	                return false;
	    		}
    		}
    		return true;
    	}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.qtyPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.qtyPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.dmnPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.dmnPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.adminPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.adminPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		mdseStorePkgTMsg.inInchVol.setValue(BigDecimal.ZERO);
    	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inEachQty, BigDecimal.ONE);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchHgt, bizMsg.inInchHgt_UN);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchLg, bizMsg.inInchLg_UN);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchWdt, bizMsg.inInchWdt_UN);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inPoundWt, bizMsg.inPoundWt_UN);
            // 2019/10/28 QC#53741 Add Start
        	if (ZYPCommonFunc.hasValue(bizMsg.inInchHgt_UN)) {
            	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchHgtUomCd, bizMsg.inInchHgtUomCd_UN);
        	} else {
        		mdseStorePkgTMsg.inInchHgtUomCd.clear();
        	}
        	if (ZYPCommonFunc.hasValue(bizMsg.inInchLg_UN)) {
            	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchLgUomCd, bizMsg.inInchLgUomCd_UN);
        	} else {
        		mdseStorePkgTMsg.inInchLgUomCd.clear();
        	}
        	if (ZYPCommonFunc.hasValue(bizMsg.inInchWdt_UN)) {
            	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchWdtUomCd, bizMsg.inInchWdtUomCd_UN);
        	} else {
        		mdseStorePkgTMsg.inInchWdtUomCd.clear();
        	}
        	if (ZYPCommonFunc.hasValue(bizMsg.inPoundWt_UN)) {
            	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inPoundWtUomCd, bizMsg.inPoundWtUomCd_UN);
        	} else {
        		mdseStorePkgTMsg.inPoundWtUomCd.clear();
        	}
            // 2019/10/28 QC#53741 Add End
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.upcOrScc14LbFlg, FLG_OFF_N);
    	} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchHgt, bizMsg.inInchHgt_EA);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchLg, bizMsg.inInchLg_EA);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inInchWdt, bizMsg.inInchWdt_EA);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inPoundWt, bizMsg.inPoundWt_EA);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.upcOrScc14LbFlg, FLG_OFF_N);
    	}

        // QC#24556 add Start
        if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.primPkgUomFlg)) {
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.primPkgUomFlg, FLG_OFF_N);
        }
        // QC#24556 add End

    	if (isExistsMdseStorePkg == false) {
            S21FastTBLAccessor.removePhysical(new MDSE_STORE_PKGTMsg[]{mdseStorePkgTMsg});
            //EZDTBLAccessor.create(mdseStorePkgTMsg);
            S21FastTBLAccessor.insert(mdseStorePkgTMsg);
            if (!RETURN_CD_NORMAL.equals(mdseStorePkgTMsg.getReturnCode())) {
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+pkgUomCd});
                return false;
            }
    	} else {
    		
            String tmpTime = mdseStorePkgTMsg.ezUpTime.getValue();
            String tmpTimeZone = mdseStorePkgTMsg.ezUpTimeZone.getValue();
            String ezUptime = "";
            String ezTimeZone = "";
        	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
                ezUptime = bizMsg.ezUpTime_M6.getValue();
                ezTimeZone = bizMsg.ezUpTimeZone_M6.getValue();
        	} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
                ezUptime = bizMsg.ezUpTime_M7.getValue();
                ezTimeZone = bizMsg.ezUpTimeZone_M7.getValue();
        	}
            if (!ZYPDateUtil.isSameTimeStamp(ezUptime, ezTimeZone, tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+pkgUomCd});
                return false;
            }
            S21FastTBLAccessor.update(mdseStorePkgTMsg);
    		if (!RETURN_CD_NORMAL.equals(mdseStorePkgTMsg.getReturnCode())) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+pkgUomCd});
                return false;
    		}
    	}
    	
		//mdseStorePkgTMsg.areaOfPaperNum.clear();
//		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.primPkgUomFlg)) {
//	    	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.primPkgUomFlg, FLG_OFF_N);
//		}
    	
    	return true;
    	
    }
    public static boolean updateMdseSftyInfo(String globalCompanyCode, NMAL0110CMsg bizMsg) {
    	
    	MDSE_SFTY_INFOTMsg mdseSftyInfoTMsg = new MDSE_SFTY_INFOTMsg();
    	mdseSftyInfoTMsg.glblCmpyCd.setValue(globalCompanyCode);
    	mdseSftyInfoTMsg.mdseCd.setValue(bizMsg.mdseCd_H1.getValue());
    	mdseSftyInfoTMsg = (MDSE_SFTY_INFOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseSftyInfoTMsg);
    	boolean isExistsMdseSftyInfo = (mdseSftyInfoTMsg != null);
    	if (isExistsMdseSftyInfo == false) {
    		mdseSftyInfoTMsg = new MDSE_SFTY_INFOTMsg();
        	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.glblCmpyCd, globalCompanyCode);
        	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.mdseCd, bizMsg.mdseCd_H1);
    	}
    	// ----------------------
    	// Auto
    	// ----------------------
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fccReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fccPrt15over18thReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fccVerFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fccCrtifFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fccDocFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fccPrt68ReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fdaReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fdaRdlgProdFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.fdaMedcDvcFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.tscaReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.ulReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.cfcReqFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.msdsReqFlg, FLG_OFF_N);
    	mdseSftyInfoTMsg.dsplTpCd.clear();
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.mercCntnFlg, FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.odcTaxFlg, FLG_OFF_N);
    	mdseSftyInfoTMsg.cstmSftyApvlStsCd.clear();
    	mdseSftyInfoTMsg.ordTakeSftyApvlStsCd.clear();
    	mdseSftyInfoTMsg.mnfStCd.clear();
    	
    	// ----------------------
    	// General Tab
    	// ----------------------
    	if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_HM) && FLG_ON_Y.equals(bizMsg.xxChkBox_HM.getValue())) {
        	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.hazMatFlg, FLG_ON_Y);
        	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.hazMatCd, bizMsg.hazMatCd_H1);
    	} else {
        	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.hazMatFlg, FLG_OFF_N);
        	mdseSftyInfoTMsg.hazMatCd.clear();
    	}
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.madeInCtryCd, bizMsg.madeInCtryCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseSftyInfoTMsg.asmInCtryCd, bizMsg.asmInCtryCd_H1);
    	
    	if (isExistsMdseSftyInfo == false) {
    		
            //EZDTBLAccessor.create(mdseSftyInfoTMsg);
            S21FastTBLAccessor.insert(mdseSftyInfoTMsg);
            String returnCd = mdseSftyInfoTMsg.getReturnCode();

            if (!RETURN_CD_NORMAL.equals(returnCd)) {
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_SFTY_INFO", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
                return false;
            }
    	} else {
    		
            String tmpTimeZone = mdseSftyInfoTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseSftyInfoTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_M4.getValue(), bizMsg.ezUpTimeZone_M4.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_SFTY_INFO", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
    		    return false;
            }
            S21FastTBLAccessor.update(mdseSftyInfoTMsg);
    		String returnCd = mdseSftyInfoTMsg.getReturnCode();
    		if (!RETURN_CD_NORMAL.equals(returnCd)) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_SFTY_INFO", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
    		    return false;
    		}
    	}
    	return true;
    	
    }
    public static boolean updateMdseCstUpdHist(String globalCompanyCode, NMAL0110CMsg bizMsg, String mdseCstUpdTpCd) {
    	
		String yyyyMMddHHmmss = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
		String yyyyMMdd = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = profile.getContextUserInfo();
		MDSE_CST_UPD_HIST_HDRTMsg mcuhTMsg = new MDSE_CST_UPD_HIST_HDRTMsg();
		ZYPEZDItemValueSetter.setValue(mcuhTMsg.glblCmpyCd, globalCompanyCode);
		BigDecimal mdseCstUpdHistHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_CST_UPD_HIST_HDR_SQ);
		ZYPEZDItemValueSetter.setValue(mcuhTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
		ZYPEZDItemValueSetter.setValue(mcuhTMsg.mdseCstUpdGrpTxt, "MANUAL ENTRY" + yyyyMMddHHmmss);
		ZYPEZDItemValueSetter.setValue(mcuhTMsg.mdseCstUpdTpCd, mdseCstUpdTpCd);
		ZYPEZDItemValueSetter.setValue(mcuhTMsg.mdseCstUpdDescTxt, "MANUAL ENTRY" + yyyyMMddHHmmss);
		mcuhTMsg.mdseCstUpdRefTxt.clear();
		mcuhTMsg.mdseCstUpdRefId.clear();
		ZYPEZDItemValueSetter.setValue(mcuhTMsg.mdseCstUpdCratPsnCd, userInfo.getUserId());
		ZYPEZDItemValueSetter.setValue(mcuhTMsg.mdseCstUpdCratDt, yyyyMMdd);
        //EZDTBLAccessor.create(mcuhTMsg);
        S21FastTBLAccessor.insert(mcuhTMsg);
        String returnCd = mcuhTMsg.getReturnCode();
        if (!RETURN_CD_NORMAL.equals(returnCd)) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_CST_UPD_HIST_HDR", "MDSE_CST_UPD_HIST_HDR_PK", String.valueOf(mcuhTMsg.mdseCstUpdHistHdrPk.getValueInt())});
            return false;
        }

    	// #####################
    	// MDSE_CST_UPD_HIST_DTL
    	// #####################
		MDSE_CST_UPD_HIST_DTLTMsg mcudTMsg = new MDSE_CST_UPD_HIST_DTLTMsg();
		ZYPEZDItemValueSetter.setValue(mcudTMsg.glblCmpyCd, globalCompanyCode);
		ZYPEZDItemValueSetter.setValue(mcudTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
		ZYPEZDItemValueSetter.setValue(mcudTMsg.mdseCd, bizMsg.mdseCd_H1);
		if (MDSE_CST_UPD_TP.STANDARD_COST.equals(mdseCstUpdTpCd)) {
			ZYPEZDItemValueSetter.setValue(mcudTMsg.rqstTotStdCostAmt, bizMsg.thisMthTotStdCostAmt_H1);
		} else {
			ZYPEZDItemValueSetter.setValue(mcudTMsg.rqstTotStdCostAmt, bizMsg.assetRecovCostAmt_H1);
		}
		ZYPEZDItemValueSetter.setValue(mcudTMsg.pkgUomCd, PKG_UOM.EACH);
		ZYPEZDItemValueSetter.setValue(mcudTMsg.mdseCstUpdEffFromDt, yyyyMMdd);
		ZYPEZDItemValueSetter.setValue(mcudTMsg.mdseCstUpdStsCd, MDSE_CST_UPD_RQST_STS.ACTIVE);
        //EZDTBLAccessor.create(mcudTMsg);
        S21FastTBLAccessor.insert(mcudTMsg);
        returnCd = mcudTMsg.getReturnCode();
        if (!RETURN_CD_NORMAL.equals(returnCd)) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD", String.valueOf(mcuhTMsg.mdseCstUpdHistHdrPk.getValueInt())+mcudTMsg.mdseCd.getValue()});
            return false;
        }
    	return true;
    	
    }
    
    public static void setDefaultValue(NMAL0110CMsg bizMsg, String globalCompanyCode) {
    	
    	ZYPEZDItemValueSetter.setValue(bizMsg.mdseRgtnTpCd_H1, MDSE_RGTN_TP.MANUAL);
    	//change to Default Flag = Y
    	Map<String, Object> map = getItemStatusDefaultList(bizMsg, globalCompanyCode);
    	if (map != null && map.get("MDSE_ITEM_STS_CD") != null) {
	    	ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_H1, (String) map.get("MDSE_ITEM_STS_CD"));
    	}
    	//ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_H1, MDSE_ITEM_STS.CURRENT);
		String yyyyMMdd = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
    	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdDt_H1, yyyyMMdd);
    	ZYPEZDItemValueSetter.setValue(bizMsg.assetRecovCostAmtUpdDt_H1, yyyyMMdd);
    	ZYPEZDItemValueSetter.setValue(bizMsg.cpoMinOrdQty_H1, new BigDecimal("1"));
    	ZYPEZDItemValueSetter.setValue(bizMsg.cpoMaxOrdQty_H1, new BigDecimal("999999999"));
    	ZYPEZDItemValueSetter.setValue(bizMsg.cpoIncrOrdQty_H1, new BigDecimal("1"));
    	ZYPEZDItemValueSetter.setValue(bizMsg.invtyHardAllocTpCd_H1, HARD_ALLOC_TP.AUTO); //Auto
    	ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_EA, BigDecimal.ONE);
    	ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_EA, BigDecimal.ZERO);
    	ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_EA, BigDecimal.ZERO);
    	ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_EA, BigDecimal.ZERO);
    	
    }

    public static AMER_MSTRTMsgArray chkAMERMSTRTMsg(String globalCompanyCode, String mdseCd) {

        AMER_MSTRTMsg amerMdseTMsg = new AMER_MSTRTMsg();
        amerMdseTMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        amerMdseTMsg.setConditionValue("amerMdseCd01", mdseCd);
        amerMdseTMsg.setSQLID("001");
        AMER_MSTRTMsgArray resultAmerMdseTMsg = (AMER_MSTRTMsgArray) EZDTBLAccessor.findByCondition(amerMdseTMsg);
        return resultAmerMdseTMsg;

    }

	public static AMER_CMPYTMsg checkAmerCmpy(NMAL0110CMsg bizMsg, String mdseCd, S21UserInfo userInfo, String globalCompanyCode) {

		AMER_CMPYTMsg amerCmpyTMsg = new AMER_CMPYTMsg();
        amerCmpyTMsg.glblCmpyCd.setValue(globalCompanyCode);
        amerCmpyTMsg.amerCmpyCd.setValue(userInfo.getUserCompanyCode());
        amerCmpyTMsg.amerMdseCd.setValue(mdseCd);
        AMER_CMPYTMsg amerCmpyTMsgResult = (AMER_CMPYTMsg) S21FastTBLAccessor.findByKey(amerCmpyTMsg);
        return amerCmpyTMsgResult;
        
	}

	    
    public static boolean deleteMdseStorePkgAndDsMdseStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	String pkgUomCd = bizMsg.L.no(i).pkgUomCd_L1.getValue();
    	if (ZYPCommonFunc.hasValue(bizMsg.L.no(i).basePkgUomCd_L1) && bizMsg.L.no(i).basePkgUomCd_L1.getValue().equals(pkgUomCd)) {
    		pkgUomCd = PKG_UOM.EACH;
    	}
    	MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, pkgUomCd);
        mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseStorePkgTMsg);
        if (mdseStorePkgTMsg != null) {
            String tmpTimeZone = mdseStorePkgTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseStorePkgTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.L.no(i).ezUpTime_L1.getValue(), bizMsg.L.no(i).ezUpTimeZone_L1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+mdseStorePkgTMsg.pkgUomCd.getValue()});
                return false;
            }
        } else {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+bizMsg.L.no(i).pkgUomCd_L1.getValue()});
            return false;
        }
        if (PKG_UOM.EACH.equals(pkgUomCd)) {
            mdseStorePkgTMsg.inEachQty.setValue(BigDecimal.ONE);
            S21FastTBLAccessor.update(mdseStorePkgTMsg);
            if (!RETURN_CD_NORMAL.equals(mdseStorePkgTMsg.getReturnCode())) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+mdseStorePkgTMsg.pkgUomCd.getValue()});
                return false;
            }
        } else {
            //EZDTBLAccessor.remove(mdseStorePkgTMsg);
            int delCnt = S21FastTBLAccessor.removeLogical(new MDSE_STORE_PKGTMsg[]{mdseStorePkgTMsg});
            if (delCnt != 1) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+mdseStorePkgTMsg.pkgUomCd.getValue()});
                return false;
            }
        }
        return true;

    }
    
    public static boolean updateMdseStorePkgAndDsMdseStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {
    	
    	String pkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
    	//QC#17184
    	//String prePkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
    	//if (ZYPCommonFunc.hasValue(bizMsg.K.no(i).basePkgUomCd_K1) && bizMsg.K.no(i).basePkgUomCd_K1.getValue().equals(pkgUomCd)) {
    	//	pkgUomCd = bizMsg.K.no(i).basePkgUomCd_K1.getValue();
    	//}
    	MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, pkgUomCd);
        mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseStorePkgTMsg);
        if (mdseStorePkgTMsg != null) {
            String tmpTimeZone = mdseStorePkgTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseStorePkgTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.K.no(i).ezUpTime_K1.getValue(), bizMsg.K.no(i).ezUpTimeZone_K1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+mdseStorePkgTMsg.pkgUomCd.getValue()});
                return false;
            }
        } else {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+bizMsg.K.no(i).pkgUomCd_K1.getValue()});
            return false;
        }
		mdseStorePkgTMsg.inInchVol.setValue(BigDecimal.ZERO);
		if (!PKG_UOM.EACH.equals(pkgUomCd)) {
			mdseStorePkgTMsg.inInchLg.setValue(BigDecimal.ZERO);
			mdseStorePkgTMsg.inInchWdt.setValue(BigDecimal.ZERO);
			mdseStorePkgTMsg.inInchHgt.setValue(BigDecimal.ZERO);
			mdseStorePkgTMsg.inPoundWt.setValue(BigDecimal.ZERO);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.qtyPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.qtyPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.dmnPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.dmnPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.adminPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.adminPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inEachQty, bizMsg.K.no(i).inEachQty_K1.getValue());
    	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.upcOrScc14LbFlg, FLG_OFF_N);
    	
        BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
        int radioIntValueK1 = 9999;
        if (radioValueK1 != null) {
            radioIntValueK1 = radioValueK1.intValue();
        }
        String primPkgUomFlg = (i == radioIntValueK1 ? FLG_ON_Y : FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.primPkgUomFlg, primPkgUomFlg);
        //QC#17184
        //if (ZYPCommonFunc.hasValue(bizMsg.K.no(i).basePkgUomCd_K1) && bizMsg.K.no(i).basePkgUomCd_K1.getValue().equals(prePkgUomCd)) {
        if (PKG_UOM.EACH.equals(pkgUomCd)) {
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.basePkgUomCd, bizMsg.K.no(i).basePkgUomCd_K1);
        } else {
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.basePkgUomCd, "");
        }
        //dsMdseStorePkgTMsg.areaOfPaperNum.setValue(null);
        S21FastTBLAccessor.update(mdseStorePkgTMsg);
        if (!RETURN_CD_NORMAL.equals(mdseStorePkgTMsg.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+mdseStorePkgTMsg.pkgUomCd.getValue()});
            return false;
        }
    	//M7
        if (PKG_UOM.EACH.equals(pkgUomCd)) {
	    	bizMsg.ezUpTime_M7.setValue(mdseStorePkgTMsg.ezUpTime.getValue());
	    	bizMsg.ezUpTimeZone_M7.setValue(mdseStorePkgTMsg.ezUpTimeZone.getValue());
        }
        //MC
        if (PKG_UOM.EACH.equals(pkgUomCd)) {
            bizMsg.ezUpTime_MC.setValue(mdseStorePkgTMsg.ezUpTime.getValue());
            bizMsg.ezUpTimeZone_MC.setValue(mdseStorePkgTMsg.ezUpTimeZone.getValue());
        }
        return true;
        
    }
    
    public static boolean chkMdseStorePkgExists(NMAL0110CMsg bizMsg) {
    	
		boolean detailListChkFlg = false;
		for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(bizMsg.K.no(i).pkgUomCd_K1) 
					&& !ZYPCommonFunc.hasValue(bizMsg.K.no(i).inEachQty_K1)) {
				continue;
			}
			detailListChkFlg = true;
		}
		return detailListChkFlg;

    }
	
    public static boolean insertMdseStorePkgAndDsMdseStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	String pkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
        //QC#17184
    	//String prePkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
    	//if (ZYPCommonFunc.hasValue(bizMsg.K.no(i).basePkgUomCd_K1) && bizMsg.K.no(i).basePkgUomCd_K1.getValue().equals(pkgUomCd)) {
    	//	pkgUomCd = PKG_UOM.EACH;
    	//}
    	MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
    	mdseStorePkgTMsg.glblCmpyCd.setValue(globalCompanyCode);
    	mdseStorePkgTMsg.mdseCd.setValue(bizMsg.mdseCd_H1.getValue());
    	mdseStorePkgTMsg.pkgUomCd.setValue(pkgUomCd);
    	mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseStorePkgTMsg);
    	boolean isExistsMdseStorePkg = (mdseStorePkgTMsg != null);
    	if (isExistsMdseStorePkg == false) {
    		mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, globalCompanyCode);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, bizMsg.mdseCd_H1);
        	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, pkgUomCd);
    	}
		mdseStorePkgTMsg.inInchVol.setValue(BigDecimal.ZERO);
		if (!PKG_UOM.EACH.equals(pkgUomCd)) {
			mdseStorePkgTMsg.inInchLg.setValue(BigDecimal.ZERO);
			mdseStorePkgTMsg.inInchWdt.setValue(BigDecimal.ZERO);
			mdseStorePkgTMsg.inInchHgt.setValue(BigDecimal.ZERO);
			mdseStorePkgTMsg.inPoundWt.setValue(BigDecimal.ZERO);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.qtyPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.qtyPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.dmnPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.dmnPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		if (!ZYPCommonFunc.hasValue(mdseStorePkgTMsg.adminPkgApvlStsCd)) {
			ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.adminPkgApvlStsCd, APVL_STS.SUBMITTED);
		}
		ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.inEachQty, bizMsg.K.no(i).inEachQty_K1);
    	ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.upcOrScc14LbFlg, FLG_OFF_N);

        BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
        int radioIntValueK1 = 9999;
        if (radioValueK1 != null) {
            radioIntValueK1 = radioValueK1.intValue();
        }
        String primPkgUomFlg = (i == radioIntValueK1 ? FLG_ON_Y : FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.primPkgUomFlg, primPkgUomFlg);
        //QC#17184
        //if (ZYPCommonFunc.hasValue(bizMsg.K.no(i).basePkgUomCd_K1) && bizMsg.K.no(i).basePkgUomCd_K1.getValue().equals(prePkgUomCd)) {
        if (PKG_UOM.EACH.equals(pkgUomCd)) {
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.basePkgUomCd, bizMsg.K.no(i).basePkgUomCd_K1);
        } else {
            ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.basePkgUomCd, "");
        }
    	
    	if (isExistsMdseStorePkg) {
            S21FastTBLAccessor.update(mdseStorePkgTMsg);
            if (!RETURN_CD_NORMAL.equals(mdseStorePkgTMsg.getReturnCode())) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+mdseStorePkgTMsg.pkgUomCd.getValue()});
                return false;
            }
    	} else {
            S21FastTBLAccessor.removePhysical(new MDSE_STORE_PKGTMsg[]{mdseStorePkgTMsg});
            //EZDTBLAccessor.create(mdseStorePkgTMsg);
            S21FastTBLAccessor.insert(mdseStorePkgTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseStorePkgTMsg.getReturnCode())) {
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_STORE_PKG", "MDSE_CD,PKG_UOM_CD", bizMsg.mdseCd_H1.getValue()+","+mdseStorePkgTMsg.pkgUomCd.getValue()});
                return false;
            }
    	}
    	//M7
    	if (PKG_UOM.EACH.equals(pkgUomCd)) {
	    	bizMsg.ezUpTime_M7.setValue(mdseStorePkgTMsg.ezUpTime.getValue());
	    	bizMsg.ezUpTimeZone_M7.setValue(mdseStorePkgTMsg.ezUpTimeZone.getValue());
    	}

    	//MC
        if (PKG_UOM.EACH.equals(pkgUomCd)) {
	    	bizMsg.ezUpTime_MC.setValue(mdseStorePkgTMsg.ezUpTime.getValue());
	    	bizMsg.ezUpTimeZone_MC.setValue(mdseStorePkgTMsg.ezUpTimeZone.getValue());
        }
        return true;
        
    }
    public static boolean updateMdseCratTmplStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, String pkgUomCd, boolean updFlg) {

    	MDSE_CRAT_TMPL_STORE_PKGTMsg mdseCratTmplStorePkgTMsg = null;
    	if (updFlg) {
	    	mdseCratTmplStorePkgTMsg = new MDSE_CRAT_TMPL_STORE_PKGTMsg();
	    	mdseCratTmplStorePkgTMsg.glblCmpyCd.setValue(globalCompanyCode);
	    	mdseCratTmplStorePkgTMsg.mdseCratTmplPk.setValue(bizMsg.mdseCratTmplPk_H1.getValue());
	    	mdseCratTmplStorePkgTMsg.pkgUomCd.setValue(pkgUomCd);
	    	mdseCratTmplStorePkgTMsg = (MDSE_CRAT_TMPL_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseCratTmplStorePkgTMsg);
	    	boolean isExistsMdseStorePkg = (mdseCratTmplStorePkgTMsg != null);
	    	if (isExistsMdseStorePkg == false) {
		    	mdseCratTmplStorePkgTMsg = new MDSE_CRAT_TMPL_STORE_PKGTMsg();
		    	mdseCratTmplStorePkgTMsg.glblCmpyCd.setValue(globalCompanyCode);
		    	mdseCratTmplStorePkgTMsg.mdseCratTmplPk.setValue(bizMsg.mdseCratTmplPk_H1.getValue());
		    	mdseCratTmplStorePkgTMsg.pkgUomCd.setValue(pkgUomCd);
		    	mdseCratTmplStorePkgTMsg.primPkgUomFlg.setValue(FLG_OFF_N);
		    	updFlg = false;
	    	}
    	} else {
	    	mdseCratTmplStorePkgTMsg = new MDSE_CRAT_TMPL_STORE_PKGTMsg();
	    	mdseCratTmplStorePkgTMsg.glblCmpyCd.setValue(globalCompanyCode);
	    	mdseCratTmplStorePkgTMsg.mdseCratTmplPk.setValue(bizMsg.mdseCratTmplPk_H1.getValue());
	    	mdseCratTmplStorePkgTMsg.pkgUomCd.setValue(pkgUomCd);
	    	mdseCratTmplStorePkgTMsg.primPkgUomFlg.setValue(FLG_OFF_N);
    	}
    	
    	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inInchHgt, bizMsg.inInchHgt_UN);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inInchLg, bizMsg.inInchLg_UN);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inInchWdt, bizMsg.inInchWdt_UN);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inPoundWt, bizMsg.inPoundWt_UN);
    	} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
    		
    		if (ZYPCommonFunc.hasValue(mdseCratTmplStorePkgTMsg.inEachQty)) {
    			ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inEachQty, BigDecimal.ONE);
    		}
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inInchHgt, bizMsg.inInchHgt_EA);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inInchLg, bizMsg.inInchLg_EA);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inInchWdt, bizMsg.inInchWdt_EA);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inPoundWt, bizMsg.inPoundWt_EA);
    	}
    	
    	if (updFlg) {
	        String tmpTime = mdseCratTmplStorePkgTMsg.ezUpTime.getValue();
	        String tmpTimeZone = mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue();
	        String ezUptime = "";
	        String ezTimeZone = "";
	    	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
	            ezUptime = bizMsg.ezUpTime_MI.getValue();
	            ezTimeZone = bizMsg.ezUpTimeZone_MI.getValue();
	    	} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
	            ezUptime = bizMsg.ezUpTime_MG.getValue();
	            ezTimeZone = bizMsg.ezUpTimeZone_MG.getValue();
	    	}
	        if (!ZYPDateUtil.isSameTimeStamp(ezUptime, ezTimeZone, tmpTime, tmpTimeZone)) {
	        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
	            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+pkgUomCd});
	            return false;
	        }
	        S21FastTBLAccessor.update(mdseCratTmplStorePkgTMsg);
			if (!RETURN_CD_NORMAL.equals(mdseCratTmplStorePkgTMsg.getReturnCode())) {
				//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
			    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+pkgUomCd});
	            return false;
			}
    	} else {
	        //S21FastTBLAccessor.create(mdseCratTmplStorePkgTMsg);
	        S21FastTBLAccessor.insert(mdseCratTmplStorePkgTMsg);
			if (!RETURN_CD_NORMAL.equals(mdseCratTmplStorePkgTMsg.getReturnCode())) {
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+pkgUomCd});
	            return false;
			}
    	}

//    	//replace ezUpTime, ezUpTimeZone
//   		for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
//   			if (pkgUomCd.equals(bizMsg.K.no(i).pkgUomCd_K1.getValue())) {
//	   			ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTime_KT, mdseCratTmplStorePkgTMsg.ezUpTime);
//	   			ZYPEZDItemValueSetter.setValue(bizMsg.K.no(i).ezUpTimeZone_KT, mdseCratTmplStorePkgTMsg.ezUpTimeZone);
//   			}
//   		}
//   		for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
//   			if (pkgUomCd.equals(bizMsg.L.no(i).pkgUomCd_L1.getValue())) {
//	   			ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTime_LT, mdseCratTmplStorePkgTMsg.ezUpTime);
//	   			ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ezUpTimeZone_LT, mdseCratTmplStorePkgTMsg.ezUpTimeZone);
//   			}
//   		}
//   		for (int i = 0; i < bizMsg.M.getValidCount(); i++) {
//   			if (pkgUomCd.equals(bizMsg.M.no(i).pkgUomCd_M1.getValue())) {
//	   			ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).ezUpTime_MT, mdseCratTmplStorePkgTMsg.ezUpTime);
//	   			ZYPEZDItemValueSetter.setValue(bizMsg.M.no(i).ezUpTimeZone_MT, mdseCratTmplStorePkgTMsg.ezUpTimeZone);
//   			}
//   		}
    	return true;
    	
    }

    public static boolean deleteMdseCratTmplStorePkgAndDsMdseStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	String pkgUomCd = bizMsg.M.no(i).pkgUomCd_M1.getValue();
    	if (ZYPCommonFunc.hasValue(bizMsg.M.no(i).basePkgUomCd_M1) && bizMsg.M.no(i).basePkgUomCd_M1.getValue().equals(pkgUomCd)) {
    		pkgUomCd = PKG_UOM.EACH;
    	}
    	
    	MDSE_CRAT_TMPL_STORE_PKGTMsg mdseCratTmplStorePkgTMsg = new MDSE_CRAT_TMPL_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.mdseCratTmplPk, bizMsg.mdseCratTmplPk_H1);
        ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.pkgUomCd, bizMsg.M.no(i).pkgUomCd_M1);
        mdseCratTmplStorePkgTMsg = (MDSE_CRAT_TMPL_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseCratTmplStorePkgTMsg);
        if (mdseCratTmplStorePkgTMsg != null) {
            String tmpTimeZone = mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseCratTmplStorePkgTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.M.no(i).ezUpTime_MT.getValue(), bizMsg.M.no(i).ezUpTimeZone_MT.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.M.no(i).pkgUomCd_M1.getValue()});
                return false;
            }
        } else {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.M.no(i).pkgUomCd_M1.getValue()});
            return false;
        }
        if (ZYPCommonFunc.hasValue(mdseCratTmplStorePkgTMsg.inInchHgt)
        		|| ZYPCommonFunc.hasValue(mdseCratTmplStorePkgTMsg.inInchLg)
        		|| ZYPCommonFunc.hasValue(mdseCratTmplStorePkgTMsg.inInchWdt)
        		|| ZYPCommonFunc.hasValue(mdseCratTmplStorePkgTMsg.inPoundWt)) {
        	mdseCratTmplStorePkgTMsg.inEachQty.setValue(null);
        	mdseCratTmplStorePkgTMsg.primPkgUomFlg.setValue(FLG_OFF_N);
        	S21FastTBLAccessor.update(mdseCratTmplStorePkgTMsg);
            if (!RETURN_CD_NORMAL.equals(mdseCratTmplStorePkgTMsg.getReturnCode())) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.M.no(i).pkgUomCd_M1.getValue()});
                return false;
            }
        	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
                bizMsg.ezUpTime_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
                bizMsg.ezUpTimeZone_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
        	} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
                bizMsg.ezUpTime_MG.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
                bizMsg.ezUpTimeZone_MG.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
        	}
        } else {
            //EZDTBLAccessor.remove(mdseCratTmplStorePkgTMsg);
            int delCnt = S21FastTBLAccessor.removeLogical(new MDSE_CRAT_TMPL_STORE_PKGTMsg[]{mdseCratTmplStorePkgTMsg});
            if (delCnt != 1) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.M.no(i).pkgUomCd_M1.getValue()});
                return false;
            }
        }
        return true;

    }
    
    public static boolean updateMdseCratTmplStorePkgAndDsMdseStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {
    	
    	String pkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
    	if (ZYPCommonFunc.hasValue(bizMsg.K.no(i).basePkgUomCd_K1) && bizMsg.K.no(i).basePkgUomCd_K1.getValue().equals(pkgUomCd)) {
    		pkgUomCd = PKG_UOM.EACH;
    	}
    	
    	MDSE_CRAT_TMPL_STORE_PKGTMsg mdseCratTmplStorePkgTMsg = new MDSE_CRAT_TMPL_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.mdseCratTmplPk, bizMsg.mdseCratTmplPk_H1);
        ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.pkgUomCd, bizMsg.K.no(i).pkgUomCd_K1);
        mdseCratTmplStorePkgTMsg = (MDSE_CRAT_TMPL_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseCratTmplStorePkgTMsg);
        if (mdseCratTmplStorePkgTMsg != null) {
            String tmpTimeZone = mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseCratTmplStorePkgTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.K.no(i).ezUpTime_KT.getValue(), bizMsg.K.no(i).ezUpTimeZone_KT.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.K.no(i).pkgUomCd_K1.getValue()});
                return false;
            }
        } else {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.K.no(i).pkgUomCd_K1.getValue()});
            return false;
        }
		ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inEachQty, bizMsg.K.no(i).inEachQty_K1);
    	BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
    	int radioIntValueK1 = 9999;
    	if (radioValueK1 != null) {
        	radioIntValueK1 = radioValueK1.intValue();
    	}
    	String primPkgUomFlg = (i == radioIntValueK1 ? FLG_ON_Y : FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.primPkgUomFlg, primPkgUomFlg);
        S21FastTBLAccessor.update(mdseCratTmplStorePkgTMsg);
        if (!RETURN_CD_NORMAL.equals(mdseCratTmplStorePkgTMsg.getReturnCode())) {
			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.K.no(i).pkgUomCd_K1.getValue()});
            return false;
        }
    	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
            bizMsg.ezUpTime_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
            bizMsg.ezUpTimeZone_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
    	} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
            bizMsg.ezUpTime_MG.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
            bizMsg.ezUpTimeZone_MG.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
    	}
//    	bizMsg.ezUpTime_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
//    	bizMsg.ezUpTimeZone_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
        return true;
        
    }
    public static boolean insertMdseCratTmplStorePkgAndDsMdseStorePkg(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	String pkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
    	if (ZYPCommonFunc.hasValue(bizMsg.K.no(i).basePkgUomCd_K1) && bizMsg.K.no(i).basePkgUomCd_K1.getValue().equals(pkgUomCd)) {
    		pkgUomCd = PKG_UOM.EACH;
    	}
    	MDSE_CRAT_TMPL_STORE_PKGTMsg mdseCratTmplStorePkgTMsg = new MDSE_CRAT_TMPL_STORE_PKGTMsg();
    	mdseCratTmplStorePkgTMsg.glblCmpyCd.setValue(globalCompanyCode);
    	mdseCratTmplStorePkgTMsg.mdseCratTmplPk.setValue(bizMsg.mdseCratTmplPk_H1.getValue());
    	mdseCratTmplStorePkgTMsg.pkgUomCd.setValue(bizMsg.K.no(i).pkgUomCd_K1.getValue());
    	mdseCratTmplStorePkgTMsg = (MDSE_CRAT_TMPL_STORE_PKGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseCratTmplStorePkgTMsg);
    	boolean isExistsMdseStorePkg = (mdseCratTmplStorePkgTMsg != null);
    	if (isExistsMdseStorePkg == false) {
    		mdseCratTmplStorePkgTMsg = new MDSE_CRAT_TMPL_STORE_PKGTMsg();
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.glblCmpyCd, globalCompanyCode);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.mdseCratTmplPk, bizMsg.mdseCratTmplPk_H1);
        	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.pkgUomCd, bizMsg.K.no(i).pkgUomCd_K1);
    	}

		ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.inEachQty, bizMsg.K.no(i).inEachQty_K1);
    	BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
    	int radioIntValueK1 = 9999;
    	if (radioValueK1 != null) {
        	radioIntValueK1 = radioValueK1.intValue();
    	}
    	String primPkgUomFlg = (i == radioIntValueK1 ? FLG_ON_Y : FLG_OFF_N);
    	ZYPEZDItemValueSetter.setValue(mdseCratTmplStorePkgTMsg.primPkgUomFlg, primPkgUomFlg);

    	if (isExistsMdseStorePkg) {
            S21FastTBLAccessor.update(mdseCratTmplStorePkgTMsg);
            if (!RETURN_CD_NORMAL.equals(mdseCratTmplStorePkgTMsg.getReturnCode())) {
    			//ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
    		    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.K.no(i).pkgUomCd_K1.getValue()});
                return false;
            }
    	} else {
            //EZDTBLAccessor.create(mdseCratTmplStorePkgTMsg);
            S21FastTBLAccessor.insert(mdseCratTmplStorePkgTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseCratTmplStorePkgTMsg.getReturnCode())) {
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_CRAT_TMPL_STORE_PKG", "MDSE_CRAT_TMPL_PK,PKG_UOM_CD", NMAL0110CommonLogic.convFromBigDecimalToString(bizMsg.mdseCratTmplPk_H1.getValue())+","+bizMsg.K.no(i).pkgUomCd_K1.getValue()});
                return false;
            }
    	}
    	//replace ezUpTime, ezUpTimeZone
    	if (PKG_UOM.EACH_UNBOX.equals(pkgUomCd)) {
            bizMsg.ezUpTime_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
            bizMsg.ezUpTimeZone_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
    	} else if (PKG_UOM.EACH.equals(pkgUomCd)) {
            bizMsg.ezUpTime_MG.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
            bizMsg.ezUpTimeZone_MG.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
    	}
    	
//    	bizMsg.ezUpTime_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTime.getValue());
//    	bizMsg.ezUpTimeZone_MI.setValue(mdseCratTmplStorePkgTMsg.ezUpTimeZone.getValue());
        return true;
        
    }

    //QC#10653
    public static void invokeMasterDataMessaging(boolean isExistsMdse,String mdseCd) {

        try {

            logger.debug("**************Deal Config Send Mdse Code is " + mdseCd + ".************");
        	
            Event event = new Event();
            event.setReferencedEntity(NMAL0110Constant.REF_ENTITY_ITEM);

            if(isExistsMdse){
                event.setOperationType(OPERATIONTYPES.UPDATE);
            }else{
                event.setOperationType(OPERATIONTYPES.CREATE);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
            //Date formatDate = sdf.parse(ezUpTime);
            Date formatDate = sdf.parse(sdf.format(new Date()));
            
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(formatDate);
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            event.setCreateStamp(date2);

            event.setId(1);

            KeyReference kr = new KeyReference();
            kr.setType(REFERENCETYPES.TECHNICAL_MNEMONICAL);

            ArrayOfAttribute aa = new ArrayOfAttribute();

            //Set MDSE Code
            Attribute a = new Attribute();
            a.setName(NMAL0110Constant.MODE_CD_KEY_NAME);
            a.setValue(mdseCd);
            
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ArrayOfKeyReference ak = new ArrayOfKeyReference();
            ak.getKeyReference().add(kr);
            event.setKeyReferences(ak);

            ArrayOfEvent ae = new ArrayOfEvent();
            ae.getEvent().add(event);

            printRequest(ae);

            logger.debug("Proxy invocation starting");

            // Invoke the proxy
            new MasterDataMessagingServiceProxy().synchronizationMessage(ae);

            logger.debug("Proxy invocation complete");

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void printRequest(ArrayOfEvent ae) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfEvent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        QName qName = new QName("com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent", "ArrayOfEvent");
        JAXBElement<ArrayOfEvent> root = new JAXBElement<ArrayOfEvent>(qName, ArrayOfEvent.class, ae);

        jaxbMarshaller.marshal(root, stringWriter);

        String result = stringWriter.toString();

        logger.debug(result);
    }
    
    public static COA_CCTMsg findCoaCc(String glblCmpyCd, String coaCcCd) {
    	
    	COA_CCTMsg coaCcTMsg = new COA_CCTMsg();
        ZYPEZDItemValueSetter.setValue(coaCcTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaCcTMsg.coaCcCd, coaCcCd);
        return (COA_CCTMsg) S21CacheTBLAccessor.findByKey(coaCcTMsg);
        
    }

    public static DFRD_ACCTG_RULETMsg findDfrdAcctgRule(String glblCmpyCd, String dfrdAcctgRuleCd) {
    	
    	DFRD_ACCTG_RULETMsg dfrdAcctgRuleTmsg = new DFRD_ACCTG_RULETMsg();
        ZYPEZDItemValueSetter.setValue(dfrdAcctgRuleTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dfrdAcctgRuleTmsg.dfrdAcctgRuleCd, dfrdAcctgRuleCd);
        return (DFRD_ACCTG_RULETMsg) S21CacheTBLAccessor.findByKey(dfrdAcctgRuleTmsg);
        
    }

    /**
     * get ORD_TAKE_MDSETMsg
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return mdseTMsg MDSETMsg
     */
    public static ORD_TAKE_MDSETMsg getDuplicateOrdTakeMdseTMsg(String glblCmpyCd, String mdseCd) {
        List<ORD_TAKE_MDSETMsg> list = NMAL0110Query.getInstance().getDuplicateOrdTakeMdse(glblCmpyCd, mdseCd);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
    /**
     * get AMER_MSTRTMsg
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return mdseTMsg MDSETMsg
     */
    public static AMER_MSTRTMsg getDuplicateAmerMstrTMsg(String glblCmpyCd, String mdseCd) {
        List<AMER_MSTRTMsg> list = NMAL0110Query.getInstance().getDuplicateAmerMstr(glblCmpyCd, mdseCd);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    //QC#9346
    public static List<Map<String, Object>> getMdseTpValSetTMsg(String globalCompanyCode, String mdseTpCtxTpCd, String mdseItemTpCd, String coaMdseTpCd) {

    	if (!ZYPCommonFunc.hasValue(mdseItemTpCd) || !ZYPCommonFunc.hasValue(coaMdseTpCd)) {
    		return null;
    	}
    	
        List<Map<String, Object>> list = NMAL0110Query.getInstance().getMdseTpValSet(globalCompanyCode, mdseTpCtxTpCd, mdseItemTpCd, coaMdseTpCd);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return null;
        
    }

	private static Map<String, Object> setScreenValueFromMapForCusaMdse(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		
		if (!ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1)) {
			return null;
		}
		//CUSA MDSE
		String cusaMdseTableName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_MDSE", globalCompanyCode);
    	if (!ZYPCommonFunc.hasValue(cusaMdseTableName)) {
    		return null;
    	}
		//CUSA MDSE_STORE_PKG
		String cusaMdseStorePkgTableName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_MDSE_STORE_PKG", globalCompanyCode);
    	if (!ZYPCommonFunc.hasValue(cusaMdseStorePkgTableName)) {
    		return null;
    	}
		//CUSA MDSE_SFTY_INFO
		String cusaMdseSftyInfoTableName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_MDSE_SFTY_INFO", globalCompanyCode);
    	if (!ZYPCommonFunc.hasValue(cusaMdseSftyInfoTableName)) {
    		return null;
    	}
    	try {
	        return NMAL0110Query.getInstance().getCusaMdseView(globalCompanyCode, bizMsg.mdseCd_H1.getValue(), cusaMdseTableName, cusaMdseStorePkgTableName, cusaMdseSftyInfoTableName);
    	} catch (Exception e) {
    		return null;
    	}
	}
	private static Map<String, Object> setScreenValueFromMapForParts(NMAL0110CMsg bizMsg, String globalCompanyCode) {
		if (!ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1)) {
			return null;
		}
		//CUSA PARTS_V
		String cusaPartsViewName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_USMM4100", globalCompanyCode);
    	if (!ZYPCommonFunc.hasValue(cusaPartsViewName)) {
    		return null;
    	}
    	try {
	        return NMAL0110Query.getInstance().getCusaPartsView(bizMsg.mdseCd_H1.getValue(), cusaPartsViewName);
    	} catch (Exception e) {
    		return null;
    	}
	}
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getBasePkgUomNm(String globalCompanyCode, String pkgUomClsCd) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getBasePkgUomNm(globalCompanyCode, pkgUomClsCd);
		if (result.isCodeNormal()) {
			return (Map<String, Object>) result.getResultObject();
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getBasePkgUom(String globalCompanyCode, String pkgUomClsCd) {
		S21SsmEZDResult result = NMAL0110Query.getInstance().getBasePkgUom(globalCompanyCode, pkgUomClsCd);
		if (result.isCodeNormal()) {
			return (Map<String, Object>) result.getResultObject();
		}
		return null;
	}

	private static String getMdseNm(String mdseDescShortTxt) {
		if (ZYPCommonFunc.hasValue(mdseDescShortTxt) && mdseDescShortTxt.length() > 30) {
			return mdseDescShortTxt.substring(0, 30);
		} else {
			return mdseDescShortTxt;
		}
	}

	private static String getMdseFmlNm(String mdseDescShortTxt) {
		if (ZYPCommonFunc.hasValue(mdseDescShortTxt) && mdseDescShortTxt.length() > 90) {
			return mdseDescShortTxt.substring(0, 90);
		} else {
			return mdseDescShortTxt;
		}
	}
    public static boolean deleteMdseTermCondOpt(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	MDSE_TERM_COND_OPTTMsg mdseTermCondOptTMsg = new MDSE_TERM_COND_OPTTMsg();
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.mdseTermCondOptPk, bizMsg.Q.no(i).mdseTermCondOptPk_Q1);
        mdseTermCondOptTMsg = (MDSE_TERM_COND_OPTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseTermCondOptTMsg);
        if (mdseTermCondOptTMsg != null) {
            String tmpTimeZone = mdseTermCondOptTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseTermCondOptTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.Q.no(i).ezUpTime_Q1.getValue(), bizMsg.Q.no(i).ezUpTimeZone_Q1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_TERM_COND_OPT", "MDSE_TERM_COND_OPT_PK", String.valueOf(bizMsg.Q.no(i).mdseTermCondOptPk_Q1.getValue())});
                return false;
            }
        }
        mdseTermCondOptTMsg = new MDSE_TERM_COND_OPTTMsg();
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.mdseTermCondOptPk, bizMsg.Q.no(i).mdseTermCondOptPk_Q1);
        //EZDTBLAccessor.logicalRemove(mdseTermCondOptTMsg);
        int delCnt = S21FastTBLAccessor.removeLogical(new MDSE_TERM_COND_OPTTMsg[]{ mdseTermCondOptTMsg });
        if (delCnt != 1) {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_TERM_COND_OPT", "MDSE_TERM_COND_OPT_PK", String.valueOf(bizMsg.Q.no(i).mdseTermCondOptPk_Q1.getValue())});
            return false;

        }
        return true;

    }
    public static void updateMdseTermCondOpt(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {
    	
    	MDSE_TERM_COND_OPTTMsg mdseTermCondOptTMsg = new MDSE_TERM_COND_OPTTMsg();
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.mdseTermCondOptPk, bizMsg.N.no(i).mdseTermCondOptPk_N1);
        mdseTermCondOptTMsg = (MDSE_TERM_COND_OPTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseTermCondOptTMsg);
        if (mdseTermCondOptTMsg != null) {
            String tmpTimeZone = mdseTermCondOptTMsg.ezUpTimeZone.getValue();
            String tmpTime = mdseTermCondOptTMsg.ezUpTime.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.N.no(i).ezUpTime_N1.getValue(), bizMsg.N.no(i).ezUpTimeZone_N1.getValue(), tmpTime, tmpTimeZone)) {
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_TERM_COND_OPT", "MDSE_TERM_COND_OPT_PK", String.valueOf(mdseTermCondOptTMsg.mdseTermCondOptPk.getValue())});
                return;
            }
        }
    	ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.termCondOptTpCd, bizMsg.N.no(i).termCondOptTpCd_N1);
    	ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.termCondOptValTxt, bizMsg.N.no(i).termCondOptValTxt_N1);
        S21FastTBLAccessor.update(mdseTermCondOptTMsg);
        if (!RETURN_CD_NORMAL.equals(mdseTermCondOptTMsg.getReturnCode())) {
        	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("NMAM8175E", new String[]{"MDSE_TERM_COND_OPT", "MDSE_TERM_COND_OPT_PK", String.valueOf(mdseTermCondOptTMsg.mdseTermCondOptPk.getValue())});
            return;
        }
        
    }
    
    public static boolean chkMdseTermCondOpt(NMAL0110CMsg bizMsg) {
    	
		boolean detailListChkFlg = false;
		for (int i = 0; i < bizMsg.N.getValidCount(); i++) {
			if (!ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptTpCd_N1) 
					&& !ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptValTxt_N1)) {
				continue;
			}
			detailListChkFlg = true;
		}
		return detailListChkFlg;
		
	}
	
    public static boolean insertMdseTermCondOpt(String globalCompanyCode, NMAL0110CMsg bizMsg, int i) {

    	MDSE_TERM_COND_OPTTMsg mdseTermCondOptTMsg = new MDSE_TERM_COND_OPTTMsg();
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.mdseTermCondOptPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDSE_TERM_COND_OPT_SQ));
        ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.mdseCd, bizMsg.mdseCd_H1);
    	ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.termCondOptTpCd, bizMsg.N.no(i).termCondOptTpCd_N1);
    	ZYPEZDItemValueSetter.setValue(mdseTermCondOptTMsg.termCondOptValTxt, bizMsg.N.no(i).termCondOptValTxt_N1);
        //EZDTBLAccessor.create(mdseTermCondOptTMsg);
        S21FastTBLAccessor.insert(mdseTermCondOptTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTermCondOptTMsg.getReturnCode())) {
        	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0001E", new String[]{"MDSE_TERM_COND_OPT", "MDSE_TERM_COND_OPT_PK", String.valueOf(mdseTermCondOptTMsg.mdseTermCondOptPk.getValue())});
            return false;
        }
        return true;
        
    }

//QC#4203
//    @SuppressWarnings("unchecked")
//	public static void setRtrnVndPulldown(NMAL0110CMsg bizMsg, String globalCompanyCode, String rtrnCtrlTpCd) {
//    	if (!ZYPCommonFunc.hasValue(rtrnCtrlTpCd)) {
//    		return;
//    	}
//		S21SsmEZDResult result = NMAL0110Query.getInstance().setRtrnVndList(bizMsg, globalCompanyCode, rtrnCtrlTpCd);
//		if (result.isCodeNormal()) {
//			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//			if (list == null || list.size() <= 0) {
//				return;
//			}
//			int i = 0;
//			for(Map<String, Object> map : list) {
//				if (map != null && map.get("VND_CD") != null) {
//					ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToVndCd_L1.no(i), (String) map.get("VND_CD"));
//					ZYPEZDItemValueSetter.setValue(bizMsg.vndNm_L1.no(i), (String) map.get("VND_NM"));
//					i++;
//				}
//			}
//		}
//	}
    
    @SuppressWarnings("unchecked")
    public static boolean existsAllMdseV(String glblCmpyCd, String mdseCd) {
    	
		S21SsmEZDResult result = NMAL0110Query.getInstance().existsAllMdseV(glblCmpyCd, mdseCd);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			if (map == null || map.get("MDSE_CD") == null) {
				return false;
			}
		} else {
			return false;
		}
		return true;
        
    }
	
    public static void clearValue(String glblCmpyCd, S21UserProfileService profile, NMAL0110CMsg bizMsg, boolean mdseCdClearFlg) {
        String user = profile.getContextUserInfo().getUserId();
        
    	clearValue_Header(bizMsg, mdseCdClearFlg, glblCmpyCd);
    	
        boolean authGeneralEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_GENERAL);
        if (authGeneralEdit) {
        	clearValue_General(bizMsg);
        }
        
        boolean authInventoryEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_INVENTORY);
        if (authInventoryEdit) {
        	clearValue_Inventory(bizMsg);
        }
        
        boolean authAccountingEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_ACCOUNTING);
        if (authAccountingEdit) {
        	clearValue_Accounting(bizMsg);
        }
        
        boolean authFieldServiceEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_FIELD_SERVICE);
        if (authFieldServiceEdit) {
        	clearValue_Field_Service(bizMsg);
        }
        
        boolean authOrderProessEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_ORDER_PROCESSING);
        if (authOrderProessEdit) {
        	clearValue_Order_processing(bizMsg);
        }

        boolean authSupdEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_SUPD);
        if (authSupdEdit) {
        	clearValue_Supd(bizMsg);
        }
        
        boolean authCustRefEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_CUST_REF);
        if (authCustRefEdit) {
        	clearValue_Cust_Reff(bizMsg);
        }

        // 2019/10/28 QC#53741 Add Start
        NMAL0110CommonLogic.setDimensionsUOM(bizMsg, glblCmpyCd);
        // 2019/10/28 QC#53741 Add End
    }
    
    //mdseCdClearFlg = true is all clear on screen mode
    //mdseCdClearFlg = false is search button mode
    public static void clearValue_Header(NMAL0110CMsg bizMsg, boolean mdseCdClearFlg, String glblCmpyCd) {
    	//Modify Mode
    	if (mdseCdClearFlg) {
    		bizMsg.mdseCd_H1.clear();
    	}
    	bizMsg.mdseCratTmplNm_H1.clear();
    	bizMsg.mdseCratTmplPk_H1.clear();
    	bizMsg.mdseCd_SC.clear();
    	bizMsg.mdseDescShortTxt_H1.clear();
    	bizMsg.mdseItemMnfCd_H1.clear();
    	bizMsg.mdseItemMnfNm_H1.clear(); // 2019/10/24 QC#53815 Add
    	bizMsg.mnfItemCd_H1.clear();
    	bizMsg.upcCd_H1.clear();
    	bizMsg.mdseItemStsCd_H1.clear();
    	bizMsg.mdseDescLongTxt_H1.clear();
    	bizMsg.mdseItemActvDt_H1.clear();
    	if (mdseCdClearFlg) {
    		ZYPEZDItemValueSetter.setValue(bizMsg.mdseRgtnTpCd_H1, MDSE_RGTN_TP.MANUAL);
    	}
    	bizMsg.mdseItemTpCd_H1.clear();
    	bizMsg.mdseItemClsTpCd_H1.clear();
    	bizMsg.coaMdseTpCd_H1.clear();
    	bizMsg.coaProdCd_H1.clear();
    	bizMsg.coaProjDescTxt_H1.clear();
    	bizMsg.coaProdNm_H1.clear();
    	bizMsg.prchGrpCd_H1.clear();
    	bizMsg.mktMdlCd_H1.clear();
    	bizMsg.mktMdlNm_H1.clear();
    	bizMsg.mktMdlSegCd_H1.clear();
    	bizMsg.mktMdlSegNm_H1.clear();
    	bizMsg.zerothProdCtrlCd_H1.clear();
    	bizMsg.zerothProdCtrlNm_H1.clear();
    	bizMsg.firstProdCtrlCd_H1.clear();
    	bizMsg.firstProdCtrlNm_H1.clear();
    	bizMsg.scdProdCtrlCd_H1.clear();
    	bizMsg.scdProdCtrlNm_H1.clear();
    	bizMsg.thirdProdCtrlCd_H1.clear();
    	bizMsg.thirdProdCtrlNm_H1.clear();
    	bizMsg.frthProdCtrlCd_H1.clear();
    	bizMsg.frthProdCtrlNm_H1.clear();
    	bizMsg.mdseCratTmplNm_H2.clear();
    	bizMsg.mdseCratTmplCratDt_H1.clear();
    	bizMsg.attrbTmplDescTxt_H1.clear();
    	bizMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
    	bizMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
    	bizMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
        // 2019/10/24 QC#51967 Add Start
    	bizMsg.xxRsltFlg_H4.clear(); // Revenue COA_ACCT Warning Flag
    	bizMsg.xxRsltFlg_H5.clear(); // Cost of goods COA_ACCT Warning Flag
        // 2019/10/24 QC#51967 Add End
    	bizMsg.slsMatGrpCd_01.clear();
    	bizMsg.slsMatGrpDescTxt_01.clear();
    	bizMsg.slsMatGrpCd_02.clear();
    	bizMsg.slsMatGrpDescTxt_02.clear();
    	bizMsg.slsMatGrpCd_03.clear();
    	bizMsg.slsMatGrpDescTxt_03.clear();
    	bizMsg.dsCmsnGrpCd_H1.clear();
    	bizMsg.dsCmsnGrpDescTxt_H1.clear();
    	bizMsg.prntCmpySetMdseFlg_H1.clear();
    	bizMsg.mdseCd_C1.clear();
    	// set who Column Header
    	// Add Start 2016/11/07 QC#2872
    	bizMsg.xxRecHistCratTs.clear();
        bizMsg.xxRecHistCratByNm.clear();
        bizMsg.xxRecHistUpdTs.clear();
        bizMsg.xxRecHistUpdByNm.clear();
        bizMsg.xxRecHistTblNm.clear();
        // Add End 2016/11/07 QC#2872
        
        // 2020/04/07 QC#56017 Add Start
        bizMsg.xxChkBox_RP.clear();
        bizMsg.xxChkBox_BR.clear();
        bizMsg.xxDplyCtrlFlg_RP.clear();
        // 2020/04/07 QC#56017 Add End
    	
    	//change to Default Flag = Y
    	Map<String, Object> map = getItemStatusDefaultList(bizMsg, glblCmpyCd);
    	if (map != null && map.get("MDSE_ITEM_STS_CD") != null) {
	    	ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_H1, (String) map.get("MDSE_ITEM_STS_CD"));
    	}
    	//ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_H1, MDSE_ITEM_STS.CURRENT);
    }
    public static void clearValue_General(NMAL0110CMsg bizMsg) {
    	//
    	bizMsg.inPoundWt_EA.clear();
    	bizMsg.inPoundWt_UN.clear();
    	bizMsg.inInchLg_EA.clear();
    	bizMsg.inInchLg_UN.clear();
    	bizMsg.inInchWdt_EA.clear();
    	bizMsg.inInchWdt_UN.clear();
    	bizMsg.inInchHgt_EA.clear();
    	bizMsg.inInchHgt_UN.clear();
    	bizMsg.pkgUomCd_H1.clear();
    	// set who column General Tab (Dimensions(Each))
    	// Add Start 2016/11/08 QC#2872
    	bizMsg.xxRecHistCratTs_G1.clear();
        bizMsg.xxRecHistCratByNm_G1.clear();
        bizMsg.xxRecHistUpdTs_G1.clear();
        bizMsg.xxRecHistUpdByNm_G1.clear();
        bizMsg.xxRecHistTblNm_G1.clear();

        bizMsg.xxRecHistCratTs_G2.clear();
        bizMsg.xxRecHistCratByNm_G2.clear();
        bizMsg.xxRecHistUpdTs_G2.clear();
        bizMsg.xxRecHistUpdByNm_G2.clear();
        bizMsg.xxRecHistTblNm_G2.clear();

        bizMsg.xxRecHistCratTs_G3.clear();
        bizMsg.xxRecHistCratByNm_G3.clear();
        bizMsg.xxRecHistUpdTs_G3.clear();
        bizMsg.xxRecHistUpdByNm_G3.clear();
        bizMsg.xxRecHistTblNm_G3.clear();

        bizMsg.xxRecHistCratTs_G4.clear();
        bizMsg.xxRecHistCratByNm_G4.clear();
        bizMsg.xxRecHistUpdTs_G4.clear();
        bizMsg.xxRecHistUpdByNm_G4.clear();
        bizMsg.xxRecHistTblNm_G4.clear();

        bizMsg.xxRecHistCratTs_G5.clear();
        bizMsg.xxRecHistCratByNm_G5.clear();
        bizMsg.xxRecHistUpdTs_G5.clear();
        bizMsg.xxRecHistUpdByNm_G5.clear();
        bizMsg.xxRecHistTblNm_G5.clear();

        bizMsg.xxRecHistCratTs_G6.clear();
        bizMsg.xxRecHistCratByNm_G6.clear();
        bizMsg.xxRecHistUpdTs_G6.clear();
        bizMsg.xxRecHistUpdByNm_G6.clear();
        bizMsg.xxRecHistTblNm_G6.clear();

        bizMsg.xxRecHistCratTs_G7.clear();
        bizMsg.xxRecHistCratByNm_G7.clear();
        bizMsg.xxRecHistUpdTs_G7.clear();
        bizMsg.xxRecHistUpdByNm_G7.clear();
        bizMsg.xxRecHistTblNm_G7.clear();

        bizMsg.xxRecHistCratTs_G8.clear();
        bizMsg.xxRecHistCratByNm_G8.clear();
        bizMsg.xxRecHistUpdTs_G8.clear();
        bizMsg.xxRecHistUpdByNm_G8.clear();
        bizMsg.xxRecHistTblNm_G8.clear();
    	// Add End 2016/11/08 QC#2872
		ZYPTableUtil.clear(bizMsg.K);
		ZYPTableUtil.clear(bizMsg.L);
		
		//Attribute
		clearValue_General_Attribute(bizMsg);
		
		bizMsg.xxChkBox_HM.clear();
		bizMsg.hazMatCd_H1.clear();
		//bizMsg.hazMatCd_H1.setInputProtected(true);
		bizMsg.hazClsNm_H1.clear();
		bizMsg.hazPrpShipNm_H1.clear();
		bizMsg.hazIdNum_H1.clear();
		bizMsg.madeInCtryCd_H1.clear();
		//bizMsg.madeInCtryCd_H1.setInputProtected(true);
		bizMsg.ctryNm_MI.clear();
		bizMsg.asmInCtryCd_H1.clear();
		//bizMsg.asmInCtryCd_H1.setInputProtected(true);
		// set who Column General Tab (Safety)
		// Add Start 2016/11/08 QC#2872
		bizMsg.xxRecHistCratTs_G9.clear();
        bizMsg.xxRecHistCratByNm_G9.clear();
        bizMsg.xxRecHistUpdTs_G9.clear();
        bizMsg.xxRecHistUpdByNm_G9.clear();
        bizMsg.xxRecHistTblNm_G9.clear();

        bizMsg.xxRecHistCratTs_GA.clear();
        bizMsg.xxRecHistCratByNm_GA.clear();
        bizMsg.xxRecHistUpdTs_GA.clear();
        bizMsg.xxRecHistUpdByNm_GA.clear();
        bizMsg.xxRecHistTblNm_GA.clear();

        bizMsg.xxRecHistCratTs_GB.clear();
        bizMsg.xxRecHistCratByNm_GB.clear();
        bizMsg.xxRecHistUpdTs_GB.clear();
        bizMsg.xxRecHistUpdByNm_GB.clear();
        bizMsg.xxRecHistTblNm_GB.clear();

        bizMsg.xxRecHistCratTs_GC.clear();
        bizMsg.xxRecHistCratByNm_GC.clear();
        bizMsg.xxRecHistUpdTs_GC.clear();
        bizMsg.xxRecHistUpdByNm_GC.clear();
        bizMsg.xxRecHistTblNm_GC.clear();

        bizMsg.xxRecHistCratTs_GD.clear();
        bizMsg.xxRecHistCratByNm_GD.clear();
        bizMsg.xxRecHistUpdTs_GD.clear();
        bizMsg.xxRecHistUpdByNm_GD.clear();
        bizMsg.xxRecHistTblNm_GD.clear();

        bizMsg.xxRecHistCratTs_GE.clear();
        bizMsg.xxRecHistCratByNm_GE.clear();
        bizMsg.xxRecHistUpdTs_GE.clear();
        bizMsg.xxRecHistUpdByNm_GE.clear();
        bizMsg.xxRecHistTblNm_GE.clear();

        bizMsg.xxRecHistCratTs_GF.clear();
        bizMsg.xxRecHistCratByNm_GF.clear();
        bizMsg.xxRecHistUpdTs_GF.clear();
        bizMsg.xxRecHistUpdByNm_GF.clear();
        bizMsg.xxRecHistTblNm_GF.clear();
        // Add End 2016/11/08 QC#2872

		bizMsg.ctryNm_AI.clear();
		bizMsg.pkgUomCd_BL.clear();
		bizMsg.pkgUomDescTxt_BL.clear();
		bizMsg.pkgUomClsCd_H1.clear();
		bizMsg.pkgUomDescTxt_BA.clear();
		//bizMsg.pkgUomDescTxt_BA.setInputProtected(true);
    	ZYPEZDItemValueSetter.setValue(bizMsg.inPoundWt_EA, BigDecimal.ONE);
    	ZYPEZDItemValueSetter.setValue(bizMsg.inInchLg_EA, BigDecimal.ZERO);
    	ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdt_EA, BigDecimal.ZERO);
    	ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgt_EA, BigDecimal.ZERO);
    	bizMsg.frtClsCd_H1.clear();
    	bizMsg.frtClsDescTxt_H1.clear();
    	bizMsg.nmfcItemNum_H1.clear();
    	bizMsg.nmfcItemSubNum_H1.clear();
    	bizMsg.nmfcClsNum_H1.clear();
    	//bizMsg.mdsePrcGrpCd_H1.clear();
    	bizMsg.trfCd_H1.clear();
    	bizMsg.trfDescTxt_H1.clear();
    }
    
    public static void clearValue_General_Attribute(NMAL0110CMsg bizMsg) {
    	
		//Machine
		bizMsg.xxChkBox_ME.clear();
		bizMsg.backOrdImpctTpCd_H1.clear();
		bizMsg.xxChkBox_RM.clear();
		bizMsg.sowReqFlg_H1.clear();
		bizMsg.svcChrgItemTpCd_H1.clear();
    	//Accessory
		bizMsg.backOrdImpctTpCd_H1.clear();
		bizMsg.xxChkBox_RM.clear();
		bizMsg.sowReqFlg_H1.clear();
    	//Supply
		bizMsg.imgSplyOemMnfCd_H1.clear();
		bizMsg.imgSplyOemCd_H1.clear();
		bizMsg.imgSplyTpCd_H1.clear();
		bizMsg.imgSplyColorTpCd_H1.clear();
		bizMsg.imgSplyYieldCnt_H1.clear();
		bizMsg.imgSplyYieldUomCd_H1.clear();
		bizMsg.imgSplyYieldTpCd_H1.clear();
		bizMsg.easyPackTpCd_H1.clear();
		bizMsg.areaOfPaperNum_H1.clear();
    	//Parts
		bizMsg.prtItemTpCd_H1.clear();
		bizMsg.prtYieldOtptQty_H1.clear();
		bizMsg.prtYieldDaysAot_H1.clear();
		bizMsg.xxChkBox_PD.clear();
		bizMsg.prtSrvyReqFlg_H1.clear();
    	//Maintenance
		bizMsg.svcCovBaseTpCd_H1.clear();
		bizMsg.svcCovTmplTpCd_H1.clear();
		bizMsg.svcAllocTpCd_H1.clear();
		// 2017/09/25 QC#18534(L3#445) ADD Start
		bizMsg.svcPgmTpCd_H1.clear();
		bizMsg.xxChkBox_OM.clear();
		// 2017/09/25 QC#18534(L3#445) ADD End
    	//Maintenance Charges
		bizMsg.svcAllocTpCd_H1.clear();
    	bizMsg.svcChrgItemTpCd_H1.clear();
    	bizMsg.N.clear();
    	//Overages
    	bizMsg.svcAllocTpCd_H1.clear();
    	//Software
    	bizMsg.swCatgCd_H1.clear();
    	bizMsg.swTpCd_H1.clear();
    	bizMsg.swVrsnTxt_H1.clear();
    	bizMsg.swProdCatgCd_H1.clear();
    	bizMsg.swProdCatgDescTxt_H1.clear();
    	bizMsg.swLicCtrlTpCd_H1.clear();
    	bizMsg.xxChkBox_EC.clear();
    	bizMsg.swLicSeatFromQty_H1.clear();
    	bizMsg.swLicSeatToQty_H1.clear();
    	bizMsg.swLicSeatFixQty_H1.clear();
    	bizMsg.swMaintCtrlTpCd_H1.clear();
    	bizMsg.asrnPntPerLicQty_H1.clear();
    	bizMsg.bdlMaintMdseCd_H1.clear();
    	bizMsg.maintPopAvalFlg_H1.clear();
    	bizMsg.extMaintPopAvalFlg_H1.clear();
    	bizMsg.sowReqFlg_H1.clear();
        //Software License
    	bizMsg.swCatgCd_H1.clear();
    	bizMsg.swTpCd_H1.clear();
    	bizMsg.swVrsnTxt_H1.clear();
    	bizMsg.swProdCatgCd_H1.clear();
    	bizMsg.swProdCatgDescTxt_H1.clear();
    	bizMsg.swLicCtrlTpCd_H1.clear();
    	bizMsg.xxChkBox_EC.clear();
    	bizMsg.swLicSeatFromQty_H1.clear();
    	bizMsg.swLicSeatToQty_H1.clear();
    	bizMsg.swLicSeatFixQty_H1.clear();
    	bizMsg.swMaintCtrlTpCd_H1.clear();
    	bizMsg.asrnPntPerLicQty_H1.clear();
    	bizMsg.bdlMaintMdseCd_H1.clear();
    	bizMsg.maintPopAvalFlg_H1.clear();
    	bizMsg.extMaintPopAvalFlg_H1.clear();
    	bizMsg.sowReqFlg_H1.clear();
    	//Software Maintenance
    	bizMsg.swCatgCd_H1.clear();
    	bizMsg.swVrsnTxt_H1.clear();
    	bizMsg.swProdCatgCd_H1.clear();
    	bizMsg.swProdCatgDescTxt_H1.clear();
    	bizMsg.swLicSeatFromQty_H1.clear();
    	bizMsg.swLicSeatToQty_H1.clear();
    	bizMsg.swLicSeatFixQty_H1.clear();
    	bizMsg.swMaintTpCd_H1.clear();
    	bizMsg.maintItemTermMthNum_H1.clear();
    	bizMsg.asrnPntMinQty_H1.clear();
    	bizMsg.asrnPntMaxQty_H1.clear();
    	bizMsg.asrnPntFixPerOrdQty_H1.clear();
    	bizMsg.sowReqFlg_H1.clear();
        //Intangible
    	bizMsg.dsIntgMdseTpCd_H1.clear();
        //Set
    	bizMsg.sowReqFlg_H1.clear();
        //Kit
    	bizMsg.sowReqFlg_H1.clear();
        //Professional Service
    	bizMsg.sowReqFlg_H1.clear();		
    }
    
    public static void clearValue_Inventory(NMAL0110CMsg bizMsg) {
    	//
    	bizMsg.xxChkBox_IT.clear();
        // 2020/04/13 QC#56494 Add Start
    	bizMsg.xxChkBox_II.clear();
        // 2020/04/13 QC#56494 Add End
    	bizMsg.xxChkBox_RA.clear();
    	bizMsg.xxChkBox_RI.clear();
    	bizMsg.defSrcWhCd_H1.clear();
    	bizMsg.locNm_DW.clear();
    	bizMsg.defSrcSubWhCd_H1.clear();
    	bizMsg.locNm_SW.clear();
    	bizMsg.defSrcProcrTpCd_H1.clear();
    	bizMsg.invtyHardAllocTpCd_H1.clear();
        //QC#18365 ADD START
    	bizMsg.dropRtrnVndCd_H1.clear();
        //QC#18365 ADD END
    	bizMsg.xxChkBox_RR.clear();
    	bizMsg.xxChkBox_R1.clear();
    	bizMsg.rtrnCtrlTpCd_H1.clear();
    	bizMsg.rtrnCtrlTpNm_H1.clear();
    	bizMsg.rtrnDsplTpCd_H1.clear();
    	bizMsg.rtrnToVndCd_H1.clear();
    	bizMsg.locNm_V1.clear();
    	bizMsg.rtrnToPrntVndCd_H1.clear();
    	bizMsg.locNm_P1.clear();
    	bizMsg.rtrnWhCd_H1.clear();
    	bizMsg.rtrnCtrlTpVndRelnPk_H1.clear();
    	bizMsg.locNm_W1.clear();
    	
    	bizMsg.assetRecovCostAmt_H1.clear();
    	bizMsg.xxRadioBtn_SS.clear();
		ZYPTableUtil.clear(bizMsg.A);
		ZYPTableUtil.clear(bizMsg.F);
		bizMsg.invtyHardAllocTpCd_H1.setValue(HARD_ALLOC_TP.AUTO);
    }
    public static void clearValue_Accounting(NMAL0110CMsg bizMsg) {
    	//
    	bizMsg.thisMthTotStdCostAmt_H1.clear();
    	bizMsg.mdseCstUpdDt_H1.setValue(ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
    	bizMsg.lastMthTotStdCostAmt_H1.clear();
    	bizMsg.origFobAmt_H1.clear();
    	
    	bizMsg.assetRecovCostAmt_H1.clear();
    	bizMsg.assetRecovCostAmtUpdDt_H1.setValue(ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
    	bizMsg.prevAssetRecovCostAmt_H1.clear();
    	
    	bizMsg.origFobAmtUpdDt_H1.clear();
    	bizMsg.revCoaAcctCd_H1.clear();
    	bizMsg.coaAcctNm_RV.clear();
    	bizMsg.cogsCoaAcctCd_H1.clear();
    	bizMsg.coaAcctNm_CG.clear();
    	bizMsg.expCoaAcctCd_H1.clear();
    	bizMsg.coaAcctNm_EX.clear();
    	bizMsg.coaAcctNm_AC.clear();
    	bizMsg.lineBizTpCd_H1.clear();
    	bizMsg.xxChkBox_IP.clear();
    	bizMsg.dfrdAcctgRuleCd_H1.clear();
    	bizMsg.dfrdInvRuleCd_H1.clear();
    	bizMsg.taxExemTpCd_H1.clear();
		String yyyyMMdd = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
    	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdDt_H1, yyyyMMdd);
    	ZYPEZDItemValueSetter.setValue(bizMsg.assetRecovCostAmtUpdDt_H1, yyyyMMdd);
    }
    public static void clearValue_Field_Service(NMAL0110CMsg bizMsg) {
    	//
    	bizMsg.svcWtyTpCd_H1.clear();
    	bizMsg.wtyDaysAot_H1.clear();
    	bizMsg.xxChkBox_MM.clear();
    	bizMsg.xxChkBox_IB.clear();
    	bizMsg.xxChkBox_BB.clear();
    	bizMsg.xxChkBox_SC.clear();
    	bizMsg.xxChkBox_IR.clear();
    	bizMsg.iwrMdlCd_H1.clear();
    	bizMsg.iwrMdseCd_H1.clear();
    	bizMsg.mdseItemBillTpCd_H1.clear();
    	// START 2023/09/05 K.Watanabe [QC#53408, ADD]
    	bizMsg.svcIstlRuleNum_H1.clear();
    	bizMsg.svcIstlCallGrpNum_H1.clear();
    	// END 2023/09/05 K.Watanabe [QC#53408, ADD]
    	// START 2023/12/12 K.Watanabe [QC#61300, ADD]
    	bizMsg.svcDeinsRuleNum_H1.clear();
    	bizMsg.svcDeinsCallGrpNum_H1.clear();
    	// END 2023/12/12 K.Watanabe [QC#61300, ADD]
    	bizMsg.mdseDescShortTxt_IW.clear();
    }
    public static void clearValue_Order_processing(NMAL0110CMsg bizMsg) {
    	//
    	bizMsg.xxChkBox_CO.clear();
    	bizMsg.cpoMinOrdQty_H1.clear();
    	bizMsg.cpoMaxOrdQty_H1.clear();
    	bizMsg.cpoIncrOrdQty_H1.clear();
    	bizMsg.xxChkBox_IE.clear();
    	bizMsg.xxChkBox_CF.clear();
    	ZYPEZDItemValueSetter.setValue(bizMsg.cpoMinOrdQty_H1, new BigDecimal("1"));
    	ZYPEZDItemValueSetter.setValue(bizMsg.cpoMaxOrdQty_H1, new BigDecimal("999999999"));
    	ZYPEZDItemValueSetter.setValue(bizMsg.cpoIncrOrdQty_H1, new BigDecimal("1"));
    }

    public static void clearValue_Supd(NMAL0110CMsg bizMsg) {
    	//
		ZYPTableUtil.clear(bizMsg.C);
		ZYPTableUtil.clear(bizMsg.H);
		ZYPTableUtil.clear(bizMsg.D);
		ZYPTableUtil.clear(bizMsg.I);
    }
    public static void clearValue_Cust_Reff(NMAL0110CMsg bizMsg) {
    	//
		ZYPTableUtil.clear(bizMsg.E);
		ZYPTableUtil.clear(bizMsg.J);
    }
    //QC#7875
    private static String getCusaMdseTpCd(String globalCompanyCode, String mdseCd) {
		//CUSA MDSE
		String cusaMdseTableName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_MDSE", globalCompanyCode);
    	if (!ZYPCommonFunc.hasValue(cusaMdseTableName)) {
    		return null;
    	}
        Map<String, Object> map = NMAL0110Query.getInstance().getCusaMdseTp(mdseCd, cusaMdseTableName);
        if (map != null && !map.isEmpty()) {
            return (String) map.get("MDSE_TP_CD");
        } else {
            return null;
        }
    	
    }
    //QC#9823, QC#10129
    public static boolean existsCmpsn(String glblCmpyCd, String mdseCd) {
    	boolean ret = true;
        S21SsmEZDResult r = NMAL0110Query.getInstance().getCmpsn(glblCmpyCd, mdseCd);
        if (!r.isCodeNormal()) {
        	ret = false;
        }
        return ret;
    }

    // QC#25789
    public static boolean isServiceItem(String mdseItemTpCd) {
        String svcItemFlg = NMAL0110Query.getInstance().getSvcItemFlg(mdseItemTpCd);
        return S21StringUtil.isEquals(FLG_ON_Y, svcItemFlg);
    }
    // 2018/08/03 QC#26046 Add Start
    public static boolean chk8digitExistInOrdTakeMdse(String globalCompanyCode, String mdseCd) {
        if (mdseCd.length() > 7) {
            String eightDigitMdseCd = mdseCd.substring(0, 8);
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(globalCompanyCode, eightDigitMdseCd);
            if (mdseTMsg != null) {
                return true;
            }
        }
        return false;
    }
    // 2018/08/03 QC#26046 Add End

    // 2019/10/31 QC#51967 Add Start
    @SuppressWarnings("unchecked")
    public static void chkRevenueAcct(String glblCmpyCd, NMAL0110CMsg bizMsg) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().findCoaMdseTpAcctReln(glblCmpyCd, bizMsg.coaMdseTpCd_H1.getValue(), COA_ACCT_CATG.REVENUE, null);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                bizMsg.xxRsltFlg_H4.clear();
                return;
            }

            result = NMAL0110Query.getInstance().findCoaMdseTpAcctReln(glblCmpyCd, bizMsg.coaMdseTpCd_H1.getValue(), COA_ACCT_CATG.REVENUE, bizMsg.revCoaAcctCd_H1.getValue());
            if (result.isCodeNotFound()) {
                bizMsg.revCoaAcctCd_H1.setErrorInfo(2, "NMAM8705W");
                bizMsg.xxRsltFlg_H4.setValue(FLG_ON_Y);
                return;
            }
        }
        bizMsg.xxRsltFlg_H4.clear();
    }

    @SuppressWarnings("unchecked")
    public static void chkCosAcct(String glblCmpyCd, NMAL0110CMsg bizMsg) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().findCoaMdseTpAcctReln(glblCmpyCd, bizMsg.coaMdseTpCd_H1.getValue(), COA_ACCT_CATG.COS, null);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                bizMsg.xxRsltFlg_H5.clear();
                return;
            }

            result = NMAL0110Query.getInstance().findCoaMdseTpAcctReln(glblCmpyCd, bizMsg.coaMdseTpCd_H1.getValue(), COA_ACCT_CATG.COS, bizMsg.cogsCoaAcctCd_H1.getValue());
            if (result.isCodeNotFound()) {
                bizMsg.cogsCoaAcctCd_H1.setErrorInfo(2, "NMAM8706W");
                bizMsg.xxRsltFlg_H5.setValue(FLG_ON_Y);
                return;
            }
        }
        bizMsg.xxRsltFlg_H5.clear();
    }
    // 2019/10/31 QC#51967 Add End

    // 2019/10/31 QC#51967 Del Satart
    //// 2019/10/24 QC#51967 Add Start
    //public static COA_MDSE_TP_ACCT_RELNTMsg findCoaMdseTpAcctReln(String glblCmpyCd, String coaMdseTpCd, String coaAcctCatgCd) {
    //    COA_MDSE_TP_ACCT_RELNTMsg coaMdseTpAcctCdRelnTMsg = new COA_MDSE_TP_ACCT_RELNTMsg();
    //    ZYPEZDItemValueSetter.setValue(coaMdseTpAcctCdRelnTMsg.glblCmpyCd, glblCmpyCd);
    //    ZYPEZDItemValueSetter.setValue(coaMdseTpAcctCdRelnTMsg.coaMdseTpCd, coaMdseTpCd);
    //    return (COA_MDSE_TP_ACCT_RELNTMsg) S21CacheTBLAccessor.findByKey(coaMdseTpAcctCdRelnTMsg);
    //    
    //}
    // 2019/10/24 QC#51967 Add End
    // 2019/10/31 QC#51967 Del End

    // 2019/10/28 QC#53741 Add Start
    public static void setDimensionsUOM(NMAL0110CMsg bizMsg, String GlobalCompanyCode) {
        //Un Boxed(Each) Length
        setUbInInchLgUomCd(bizMsg, GlobalCompanyCode);

        //Un Boxed(Each) Width
        setUbInInchWdtUomCd(bizMsg, GlobalCompanyCode);

        //Un Boxed(Each) Height
        setUbInInchHgtUomCd(bizMsg, GlobalCompanyCode);

        //Un Boxed(Each) Weight
        setUbInPoundWtUomCd(bizMsg, GlobalCompanyCode);

        //Boxed(Each) Length
        setEaInInchLgUomCd(bizMsg, GlobalCompanyCode);

        //Boxed(Each) Width
        setEaInInchWdtUomCd(bizMsg, GlobalCompanyCode);

        //Boxed(Each) Height
        setEaInInchHgtUomCd(bizMsg, GlobalCompanyCode);

        //(Each) Weight
        setEaInPoundWtUomCd(bizMsg, GlobalCompanyCode);
    }

    @SuppressWarnings("unchecked")
    public static void setUbInInchLgUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().getUbInInchLgUomCd(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            String defUom = "";
            for(Map<String, Object> map : list) {
                ZYPEZDItemValueSetter.setValue(bizMsg.inInchLgUomCd_L1.no(i), (String) map.get("PKG_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxInInchLgUomDescTxt_L1.no(i), (String) map.get("PKG_UOM_DESC_TXT"));
                if (ZYPConstant.FLG_ON_Y.equals((String) map.get("IN_INCH_LG_DEF_FLG"))) {
                    defUom = (String) map.get("PKG_UOM_CD");
                }
                i++;
            }

            if (defUom.length() > 0) {
                bizMsg.inInchLgUomCd_UN.setValue(defUom);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void setUbInInchWdtUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().getUbInInchWdtUomCd(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            String defUom = "";
            for(Map<String, Object> map : list) {
                ZYPEZDItemValueSetter.setValue(bizMsg.inInchWdtUomCd_L1.no(i), (String) map.get("PKG_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxInInchWdtUomDescTxt_L1.no(i), (String) map.get("PKG_UOM_DESC_TXT"));
                if (ZYPConstant.FLG_ON_Y.equals((String) map.get("IN_INCH_WDT_DEF_FLG"))) {
                    defUom = (String) map.get("PKG_UOM_CD");
                }
                i++;
            }

            if (defUom.length() > 0) {
                bizMsg.inInchWdtUomCd_UN.setValue(defUom);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void setUbInInchHgtUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().getUbInInchHgtUomCd(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            String defUom = "";
            for(Map<String, Object> map : list) {
                ZYPEZDItemValueSetter.setValue(bizMsg.inInchHgtUomCd_L1.no(i), (String) map.get("PKG_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxInInchHgtUomDescTxt_L1.no(i), (String) map.get("PKG_UOM_DESC_TXT"));
                if (ZYPConstant.FLG_ON_Y.equals((String) map.get("IN_INCH_HGT_DEF_FLG"))) {
                    defUom = (String) map.get("PKG_UOM_CD");
                }
                i++;
            }

            if (defUom.length() > 0) {
                bizMsg.inInchHgtUomCd_UN.setValue(defUom);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void setUbInPoundWtUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().getUbInPoundWtUomCd(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            for(Map<String, Object> map : list) {
                if (ZYPConstant.FLG_ON_Y.equals((String) map.get("IN_POUND_WT_DEF_FLG"))) {
                    bizMsg.inPoundWtUomCd_UN.setValue((String) map.get("PKG_UOM_CD"));
                    bizMsg.xxInPoundWtUomDescTxt_UN.setValue((String) map.get("PKG_UOM_DESC_TXT"));
                    break;
                }
            }
        }
    }

    public static void setEaInInchLgUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        PKG_UOM_BOX_EACHTMsg tMsg = NMAL0110Query.getInstance().getEaDefUomCd(bizMsg, globalCompanyCode, NMAL0110Constant.PKG_UOM_INCH);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxInInchLgUomDescTxt_EA, tMsg.pkgUomDescTxt.getValue());
        }
    }

    public static void setEaInInchWdtUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        PKG_UOM_BOX_EACHTMsg tMsg = NMAL0110Query.getInstance().getEaDefUomCd(bizMsg, globalCompanyCode, NMAL0110Constant.PKG_UOM_INCH);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxInInchWdtUomDescTxt_EA, tMsg.pkgUomDescTxt.getValue());
        }
    }

    public static void setEaInInchHgtUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        PKG_UOM_BOX_EACHTMsg tMsg = NMAL0110Query.getInstance().getEaDefUomCd(bizMsg, globalCompanyCode, NMAL0110Constant.PKG_UOM_INCH);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxInInchHgtUomDescTxt_EA, tMsg.pkgUomDescTxt.getValue());
        }
    }

    public static void setEaInPoundWtUomCd(NMAL0110CMsg bizMsg, String globalCompanyCode) {
        PKG_UOM_BOX_EACHTMsg tMsg = NMAL0110Query.getInstance().getEaDefUomCd(bizMsg, globalCompanyCode, NMAL0110Constant.PKG_UOM_POUND);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxInPoundWtUomDescTxt_EA, tMsg.pkgUomDescTxt.getValue());
        }
    }
// 2019/10/28 QC#53741 Add End

    // 2020/01/07 QC#55220 Add Start
    public static boolean updateOrdTakeMdseStsChange(String globalCompanyCode, NMAL0110CMsg bizMsg) {

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ordTakeMdseTMsg.glblCmpyCd.setValue(globalCompanyCode);
        ordTakeMdseTMsg.ordTakeMdseCd.setValue(bizMsg.mdseCd_H1.getValue().substring(0, 8));
        ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordTakeMdseTMsg);
        boolean isExistsOrdTakeMdse = (ordTakeMdseTMsg != null);

        if (isExistsOrdTakeMdse == false) {
            //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
            bizMsg.setMessageInfo("ZZMM0015E", new String[]{"ORD_TAKE_MDSE", "ORD_TAKE_MDSE_CD", bizMsg.mdseCd_H1.getValue().substring(0, 8)});
            return false;
        } else {
            if (MDSE_ITEM_STS.INACTIVE.equals(bizMsg.mdseItemStsCd_H1.getValue())
             || MDSE_ITEM_STS.PRELAUNCH.equals(bizMsg.mdseItemStsCd_H1.getValue()) 
             || MDSE_ITEM_STS.PRELAUNCH_APPROVED.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
                if (ZYPCommonFunc.hasValue(ordTakeMdseTMsg.mdseCd) && !ordTakeMdseTMsg.mdseCd.getValue().equals(bizMsg.mdseCd_H1.getValue())) {
                    return true;
                }
            }

            String mdseCd = NMAL0110Query.getInstance().getActiveMdse(globalCompanyCode, bizMsg.mdseCd_H1.getValue());
            if (mdseCd == null) {
                return true;
            } else {
                if (mdseCd.equals(ordTakeMdseTMsg.mdseCd.getValue())) {
                    return true;
                }
                ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.mdseCd, mdseCd);
                S21FastTBLAccessor.update(ordTakeMdseTMsg);
                if (!RETURN_CD_NORMAL.equals(ordTakeMdseTMsg.getReturnCode())) {
                    //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
                    bizMsg.setMessageInfo("ZZMM0015E", new String[]{"ORD_TAKE_MDSE", "ORD_TAKE_MDSE_CD, MDSE_CD", bizMsg.mdseCd_H1.getValue().substring(0, 8) + ", " + mdseCd});
                    return false;
                }
            }
        }

        return true;
        
    }
    // 2020/01/07 QC#55220 Add End

    // 2020/04/07 QC#56017 Add Start
    public static void getOrdTakeMdseForMdseCd(String globalCompanyCode, String mdseCd, NMAL0110CMsg bizMsg) {
        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        tMsg.setConditionValue("mdseCd01", mdseCd);
        ORD_TAKE_MDSETMsgArray tMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArray.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RP, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_BR, ZYPConstant.FLG_ON_Y);
        }
 
        if (!ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1.getValue()) || bizMsg.mdseCd_H1.getValue().length() < 8) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_RP, ZYPConstant.FLG_ON_Y);
            return ;
        }

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ordTakeMdseTMsg.glblCmpyCd.setValue(globalCompanyCode);
        ordTakeMdseTMsg.ordTakeMdseCd.setValue(bizMsg.mdseCd_H1.getValue().substring(0, 8));
        ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordTakeMdseTMsg);
        if (ordTakeMdseTMsg == null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_RP, ZYPConstant.FLG_ON_Y);
        }
    }
    // 2020/04/07 QC#56017 Add End

    // 2022/02/17 QC#59693 Add Start
    /**
     * Get PulldownData Source Type
     * @param cMsg NMAL0110CMsg
     * @param glblCmpyCd String
     */
    public static void getPullDownDataSourceType(NMAL0110CMsg cMsg, String glblCmpyCd) {

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // Execute
        S21SsmEZDResult result = NMAL0110Query.getInstance().getSourceTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_L1.no(i), (String) recode.get("PROCR_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.procrTpDescTxt_L1.no(i), (String) recode.get("PROCR_TP_DESC_TXT"));

                if (i >= cMsg.procrTpCd_L1.length()) {
                    break;
                }
            }
        }
    }
    // 2022/02/17 QC#59693 Add End

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /**
     * Set Service Install Rule
     * @param bizMsg NMAL0110CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcIstlRulePulldown(NMAL0110CMsg bizMsg, String glblCmpyCd) {
        // START 2023/12/12 K.Watanabe [QC#61300, MOD]
        String svcIstlRuleFlg = ZYPConstant.FLG_ON_Y;
        //S21SsmEZDResult result = NMAL0110Query.getInstance().getSvcIstlRuleList(bizMsg, glblCmpyCd);
        S21SsmEZDResult result = NMAL0110Query.getInstance().getSvcIstlRuleList(bizMsg, glblCmpyCd, svcIstlRuleFlg);
        // END 2023/12/12 K.Watanabe [QC#61300, MOD]
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("SVC_ISTL_RULE_NUM") != null) {
                    bizMsg.svcIstlRuleNum_L1.no(i).setValue((String) map.get("SVC_ISTL_RULE_NUM"));
                    bizMsg.svcIstlRuleNm_L1.no(i).setValue((String) map.get("SVC_ISTL_RULE_NM"));
                    i++;
                }
            }
        }
    }

    /**
     * Set Service Install Call Group
     * @param bizMsg NMAL0110CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcIstlCallGrpPulldown(NMAL0110CMsg bizMsg, String glblCmpyCd) {
        // START 2023/12/12 K.Watanabe [QC#61300, MOD]
        String svcIstlCallGrpFlg = ZYPConstant.FLG_ON_Y;
        //S21SsmEZDResult result = NMAL0110Query.getInstance().getSvcIstlCallGrpList(bizMsg, glblCmpyCd);
        S21SsmEZDResult result = NMAL0110Query.getInstance().getSvcIstlCallGrpList(bizMsg, glblCmpyCd, svcIstlCallGrpFlg);
        // END 2023/12/12 K.Watanabe [QC#61300, MOD]
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("SVC_ISTL_CALL_GRP_NUM") != null) {
                    bizMsg.svcIstlCallGrpNum_L1.no(i).setValue((String) map.get("SVC_ISTL_CALL_GRP_NUM"));
                    bizMsg.svcIstlCallGrpNm_L1.no(i).setValue((String) map.get("SVC_ISTL_CALL_GRP_NM"));
                    i++;
                }
            }
        }
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Set Service Install Rule
     * @param bizMsg NMAL0110CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcDeinsRulePulldown(NMAL0110CMsg bizMsg, String glblCmpyCd) {
        String svcIstlRuleFlg = ZYPConstant.FLG_OFF_N;
        S21SsmEZDResult result = NMAL0110Query.getInstance().getSvcIstlRuleList(bizMsg, glblCmpyCd, svcIstlRuleFlg);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("SVC_ISTL_RULE_NUM") != null) {
                    bizMsg.svcDeinsRuleNum_L1.no(i).setValue((String) map.get("SVC_ISTL_RULE_NUM"));
                    bizMsg.xxSvcDeinsRuleNm_L1.no(i).setValue((String) map.get("SVC_ISTL_RULE_NM"));
                    i++;
                }
            }
        }
    }

    /**
     * Set Service Install Call Group
     * @param bizMsg NMAL0110CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcDeinsCallGrpPulldown(NMAL0110CMsg bizMsg, String glblCmpyCd) {
        String svcIstlCallGrpFlg = ZYPConstant.FLG_OFF_N;
        S21SsmEZDResult result = NMAL0110Query.getInstance().getSvcIstlCallGrpList(bizMsg, glblCmpyCd, svcIstlCallGrpFlg);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("SVC_ISTL_CALL_GRP_NUM") != null) {
                    bizMsg.svcDeinsCallGrpNum_L1.no(i).setValue((String) map.get("SVC_ISTL_CALL_GRP_NUM"));
                    bizMsg.xxSvcDeinsCallGrpNm_L1.no(i).setValue((String) map.get("SVC_ISTL_CALL_GRP_NM"));
                    i++;
                }
            }
        }
    }
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]
}
