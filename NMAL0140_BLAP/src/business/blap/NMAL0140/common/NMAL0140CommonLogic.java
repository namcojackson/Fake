package business.blap.NMAL0140.common;

import static business.blap.NSAL0480.constant.NSAL0480Constant.APL_ID;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NMAL0140.NMAL0140CMsg;
import business.blap.NMAL0140.NMAL0140Query;
import business.blap.NMAL0140.NMAL0140SMsg;
import business.blap.NMAL0140.constant.NMAL0140Constant;
import business.db.COA_PRODTMsg;
import business.db.RTL_WHTMsg;
import business.parts.NSZC033001PMsg;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0140CommonLogic implements NMAL0140Constant {
	
    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
    
	@SuppressWarnings("unchecked")
	public static void setInitialCostTypePulldown(NMAL0140CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0140Query.getInstance().getCostTypeList(bizMsg, globalCompanyCode);
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
	@SuppressWarnings("unchecked")
	public static void search(NMAL0140CMsg bizMsg, NMAL0140SMsg sMsg, String glblCmpyCd) {
		
		// PRODUCT NAME
		if (ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1)) {
	        COA_PRODTMsg coaProdMsg = new COA_PRODTMsg();
	        ZYPEZDItemValueSetter.setValue(coaProdMsg.glblCmpyCd, glblCmpyCd);
	        ZYPEZDItemValueSetter.setValue(coaProdMsg.coaProdCd, bizMsg.coaProdCd_H1);
	        coaProdMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(coaProdMsg);
	        if (coaProdMsg != null) {
	        	ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, coaProdMsg.coaProdNm);
	        } else {
				bizMsg.coaProdNm_H1.clear();
	        }
		} else {
			bizMsg.coaProdNm_H1.clear();
		}
		
		// WH
		if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_H1)) {
	        RTL_WHTMsg rtlWhMsg = new RTL_WHTMsg();
	        ZYPEZDItemValueSetter.setValue(rtlWhMsg.glblCmpyCd, glblCmpyCd);
	        ZYPEZDItemValueSetter.setValue(rtlWhMsg.rtlWhCd, bizMsg.rtlWhCd_H1);
	        rtlWhMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhMsg);
	    	if ( rtlWhMsg != null ) {
	    		ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H1, rtlWhMsg.rtlWhNm);
	    	} else {
	    		bizMsg.rtlWhNm_H1.clear();
	    	}
		} else {
			bizMsg.rtlWhNm_H1.clear();
		}

		// Sub WH
		if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd_H1)) {
			S21SsmEZDResult rsSwh = NMAL0140Query.getInstance().getSubWhList(glblCmpyCd, bizMsg.rtlSwhCd_H1.getValue());
			if (rsSwh.isCodeNormal()) {
				Map<String, Object> map = (Map<String, Object>) rsSwh.getResultObject();
				if (map != null) {
		    		ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhNm_H1, (String) map.get("RTL_SWH_NM"));
				} else {
		    		bizMsg.rtlSwhNm_H1.clear();
				}
			} else {
	    		bizMsg.rtlSwhNm_H1.clear();
			}
		} else {
    		bizMsg.rtlSwhNm_H1.clear();
		}
		
		ZYPTableUtil.clear(bizMsg.A);
		ZYPTableUtil.clear(sMsg.A);
		bizMsg.xxRadioBtn_H1.clear();
		//Search
		S21SsmEZDResult result = NMAL0140Query.getInstance().search(bizMsg, sMsg, glblCmpyCd);
		if (result.isCodeNormal()) {
			
			int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
            	//NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
            	bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }
            
			int i = 0;
			for( ; i < queryResCnt; i++ ) {
				if( i == bizMsg.A.length() ) {
					break;
				}
				EZDMsg.copy( sMsg.A.no( i ), null, bizMsg.A.no( i ), null );
			}
			bizMsg.A.setValidCount( i );
    		
            // Set page number
			bizMsg.xxPageShowFromNum_10.setValue( 1 );
			bizMsg.xxPageShowToNum_10.setValue( bizMsg.A.getValidCount() );
			bizMsg.xxPageShowOfNum_10.setValue( queryResCnt );
			//ZZM8100I=0,Process ended normally.
			bizMsg.setMessageInfo("ZZM8100I");
		} else {
			bizMsg.xxPageShowFromNum_10.clear();
			bizMsg.xxPageShowToNum_10.clear();
			bizMsg.xxPageShowOfNum_10.clear();
			//NZZM0000E=0,No search results found.
			bizMsg.setMessageInfo("NZZM0000E");
		}
	}
    public static boolean isExistSaveSearchName(NMAL0140CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void callNszc0330forDeleteSearch(NMAL0140CMsg bizMsg, NMAL0140SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Delete Search") });
        }
    }

    private static boolean callNszc0330(NMAL0140CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL0100CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL0140CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL0140Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NMAL0100CMsg
     * @param sMsg NMAL0100SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL0140CMsg bizMsg, NMAL0140SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdTpCd_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdGrpTxt_H1, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdRefTxt_H1, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdRefId_H1, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_H1, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd_H1, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpCd_H1, pMsg.srchOptTxt_07);
        
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H1, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd_H1, pMsg.srchOptTxt_10);
        
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_11.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseCstUpdEffFromDt_H1, pMsg.srchOptTxt_11.getValue());
        } else {
            bizMsg.mdseCstUpdEffFromDt_H1.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CC, pMsg.srchOptTxt_12);
    }
    
    public static void callNszc0330forSaveSearch(NMAL0140CMsg bizMsg, NMAL0140SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.mdseCstUpdTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.mdseCstUpdGrpTxt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.mdseCstUpdRefTxt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.mdseCstUpdRefId_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.mdseItemTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.coaMdseTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.coaProdCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.rtlSwhCd_H1);
        
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCstUpdEffFromDt_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.mdseCstUpdEffFromDt_H1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.xxChkBox_CC);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Save Search") });
        }
    }

    private static boolean isSameSaveSearchName(NMAL0140CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL0100CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL0140CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

}
