package business.blap.NMAL0110;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0110.common.NMAL0110CommonLogic;
import business.blap.NMAL0110.constant.NMAL0110Constant;
import business.db.AMER_CMPYTMsg;
import business.db.AMER_MSTRTMsg;
import business.db.AMER_MSTRTMsgArray;
import business.db.COA_ACCTTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.CTRYTMsg;
import business.db.DFRD_ACCTG_RULETMsg;
import business.db.FRT_CLSTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MKT_MDLTMsg;
import business.db.MKT_MDL_SEGTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.SUPDTMsg;
import business.db.SW_CATGTMsg;
import business.db.SW_LIC_CTRL_TPTMsg;
import business.db.SW_PROD_CATGTMsg;
import business.db.TERM_COND_OPT_TPTMsg;
import business.db.TRFTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_INV_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_LIC_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.workflow.core.context.S21WfHumanTaskExecutionContext;
import com.canon.cusa.s21.framework.workflow.ezd.business.S21WfBusinessHandlerBL06Support;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 * 07/03/2015   Fujitsu         C.Tanaka        Update          
 * 10/02/2015   SRAA            K.Aratani       Update
 * 03/16/2016   SRAA            K.Aratani       Update          QC#5580
 * 06/13/2016   SRAA            K.Aratani       Update          QC#9891
 * 06/13/2016   SRAA            K.Aratani       Update          QC#9346
 * 06/13/2016   SRAA            K.Aratani       Update          QC#9631
 * 06/13/2016   SRAA            K.Aratani       Update          QC#9678
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 06/20/2016   SRAA            K.Aratani       Update          QC#10449
 * 06/23/2016   SRAA            K.Aratani       Update          QC#10653
 * 06/23/2016   SRAA            K.Aratani       Update          QC#9823, QC#10129
 * 07/05/2016   SRAA            K.Aratani       Update          QC#11172
 * 08/18/2016   SRAA            K.Aratani       Update          QC#10748
 * 09/20/2016   SRAA            K.Aratani       Update          QC#14441
 * 12/15/2016   SRAA            K.Aratani       Update          QC#16671
 * 01/04/2017   Fujitsu         R.Nakamura      Update          QC#16926
 * 01/17/2017   SRAA            K.Aratani       Update          QC#16609
 * 2017/06/22   Hitachi         J.Kim           Update          QC#10580
 * 08/29/2017   Fujitsu         K.Ishizuka      Update          QC#17149(Sol#259)
 * 09/11/2017   CITS            T.Kikuhara      Update          QC#18365(L3)
 * 09/25/2017   Fujitsu         T.Aoi           Update          QC#18534(L3#445)
 * 2018/08/03   Fujitsu         M.Yamada        Update          QC#25789
 * 2018/08/03   Fujitsu         M.Ishii         Update          QC#26046
 * 2019/06/10   Fujitsu         M.Ohno          Update          QC#50763
 * 2019/10/15   Fujitsu         S.Kosaka        Update          QC#53602
 * 2019/10/23   Fujitsu         S.Kosaka        Update          QC#51964
 * 2019/10/24   Fujitsu         K.Kato          Update          QC#51967
 * 2019/10/28   Fujitsu         K.Kato          Update          QC#53741
 * 2019/10/31   Fujitsu         K.Kato          Update          QC#51967
 * 2019/12/13   Fujitsu         S.Kosaka        Update          QC#54621
 * 2020/01/07   Fujitsu         K.Kato          Update          QC#55220
 * 2020/04/07   Fujitsu         M.Ohno          Update          QC#56017
 * 2020/04/13   CITS            K.Ogino         Update          QC#56494
 * 2020/09/04   CITS            J.Evangelista   Update          QC#57603, QC#57588
 * 2022/02/17   Fujitsu         C.Hara          Update          QC#59693
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NMAL0110BL06 extends S21WfBusinessHandlerBL06Support implements NMAL0110Constant {

	private boolean mdse8digitPossibleFlg = false;
	MDSE_ITEM_STSTMsg mdseItemStsTMsg = null;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg, S21WfHumanTaskExecutionContext wfCtx) {
        super.preDoProcess(cMsg, sMsg);

        try {
            NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
            String scrnAplID = bizMsg.getScreenAplID();
            if ("NMAL0110Scrn00_CMN_Submit".equals(scrnAplID)) {
            	NMAL0110Scrn00_CMN_Submit(bizMsg, sMsg);
//            } else if ("NMAL0110Scrn00_Save_Tmpl".equals(scrnAplID)) {
//                NMAL0110Scrn00_Save_Tmpl(bizMsg, sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);

        }
    }
    @SuppressWarnings("unchecked")
    private void NMAL0110Scrn00_CMN_Submit(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {

    	//Store pulldown code name
    	boolean hasError = storeName(bizMsg, sMsg);
    	if (hasError) {
    		return;
    	}
    	
    	// #####################
    	// Input Check
    	// #####################
    	// Header
        hasError = checkInput_Header(bizMsg, sMsg);
    	if (hasError) {
    		return;
    	}
    	// Get MDSE_ITEM_STS
    	mdseItemStsTMsg = new MDSE_ITEM_STSTMsg();
    	mdseItemStsTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
    	mdseItemStsTMsg.mdseItemStsCd.setValue(bizMsg.mdseItemStsCd_H1.getValue());
    	mdseItemStsTMsg = (MDSE_ITEM_STSTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseItemStsTMsg);
    	if (mdseItemStsTMsg == null) {
    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
            bizMsg.setMessageInfo("NDAM0007E", new String[]{"MDSE_ITEM_STS", "MDSE_ITEM_STS_CD", bizMsg.mdseItemStsCd_H1.getValue()});
            return;
    	}
    	mdse8digitPossibleFlg = judgeMdse8DigitPossible(bizMsg);
    	
    	// General
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo user = profile.getContextUserInfo();
        boolean authGeneralRead = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_READ_GENERAL);
        boolean authGeneralEdit = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_EDIT_GENERAL);
        if (authGeneralRead || authGeneralEdit) {
            hasError = checkInput_TAB_General(bizMsg, sMsg);
        	if (hasError) {
        		bizMsg.xxDplyTab_H1.setValue(TAB_GENERAL);
        		return;
        	}
        }
    	// Inventory
        boolean authInventoryRead = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_READ_INVENTORY);
        boolean authInventoryEdit = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_EDIT_INVENTORY);
        if (authInventoryRead || authInventoryEdit) {
            hasError = checkInput_TAB_Inventory(bizMsg, sMsg);
        	if (hasError) {
        		bizMsg.xxDplyTab_H1.setValue(TAB_INVENTORY);
        		return;
        	}
        }
    	// Accounting
        boolean authAccountingRead = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_READ_ACCOUNTING);
        boolean authAccountingEdit = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_EDIT_ACCOUNTING);
        if (authAccountingRead || authAccountingEdit) {
            hasError = checkInput_TAB_Accounting(bizMsg, sMsg);
        	if (hasError) {
        		bizMsg.xxDplyTab_H1.setValue(TAB_ACCOUNTING);
        		return;
        	}
        }
    	// Field Service
        boolean authFieldServiceRead = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_READ_FIELD_SERVICE);
        boolean authFieldServiceEdit = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_EDIT_FIELD_SERVICE);
        if (authFieldServiceRead || authFieldServiceEdit) {
            hasError = checkInput_TAB_Field_Service(bizMsg, sMsg);
        	if (hasError) {
        		bizMsg.xxDplyTab_H1.setValue(TAB_FIELD_SERVICE);
        		return;
        	}
        }
        
    	// Order Processing
        boolean authOrderProcessingRead = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_READ_ORDER_PROCESSING);
        boolean authOrderProcessingEdit = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_EDIT_ORDER_PROCESSING);
        if (authOrderProcessingRead || authOrderProcessingEdit) {
            hasError = checkInput_TAB_Order_Processing(bizMsg, sMsg);
        	if (hasError) {
        		bizMsg.xxDplyTab_H1.setValue(TAB_ORDER_PROCESSING);
        		return;
        	}
        }

    	// Sup / Relation
        boolean authSupdRead = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_READ_SUPD);
        boolean authSupdEdit = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_EDIT_SUPD);
        if (authSupdRead || authSupdEdit) {
            hasError = checkInput_TAB_Supd(bizMsg, sMsg);
        	if (hasError) {
        		bizMsg.xxDplyTab_H1.setValue(TAB_SUPD);
        		return;
        	}
        }
        
    	// Customer Reference
        boolean authCustRefRead = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_READ_CUST_REF);
        boolean authCustRefEdit = profile.isFunctionGranted(user.getUserId(), NMAL0110Constant.FUNC_EDIT_CUST_REF);
        if (authCustRefRead || authCustRefEdit) {
            hasError = checkInput_TAB_Cust_Ref(bizMsg, sMsg);
        	if (hasError) {
        		bizMsg.xxDplyTab_H1.setValue(TAB_CUST_REF);
        		return;
        	}
        }
    	
    	// Final Check (Status & Warning)
        hasError = checkInput_Final(bizMsg, sMsg, mdseItemStsTMsg);
    	if (hasError) {
    		return;
    	}
    	
    	// #####################
        // Create and Update
    	// #####################
    	// Get INTG_PROD_CATG_CONV
    	Map<String, Object> mapIPCC = null;
    	boolean intgErrorFlg = false;
		S21SsmEZDResult rsIntgProdCatgConv = NMAL0110Query.getInstance().getIntgProdCatgConvPriorityList(bizMsg, getGlobalCompanyCode());
		//S21SsmEZDResult rsIntgProdCatgConv = NMAL0110Query.getInstance().getIntgProdCatgConv(bizMsg, getGlobalCompanyCode());
		if (rsIntgProdCatgConv.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) rsIntgProdCatgConv.getResultObject();
			if (list == null || list.size() <= 0) {
				intgErrorFlg = true;
			}
			mapIPCC = (Map<String, Object>) list.get(0);
			if (mapIPCC == null) {
				intgErrorFlg = true;
			}
		} else {
			intgErrorFlg = true;
		}
		
		if (intgErrorFlg) {
			//NMAM8358E=0,Given item type and mdse type combination is not accepted.Please review code table (INTG_PROD_CATG_CONV).
            bizMsg.mdseItemTpCd_H1.setErrorInfo(1, "NMAM8358E");
            bizMsg.coaMdseTpCd_H1.setErrorInfo(1, "NMAM8358E");
			if (MDSE_ITEM_TP.SOFTWARE.equals(bizMsg.mdseItemTpCd_H1.getValue())
					|| MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(bizMsg.mdseItemTpCd_H1.getValue())) {
	            bizMsg.swLicCtrlTpCd_H1.setErrorInfo(1, "NMAM8358E");
	            bizMsg.swCatgCd_H1.setErrorInfo(1, "NMAM8358E");
	            bizMsg.xxChkBox_EC.setErrorInfo(1, "NMAM8358E");
	            bizMsg.xxDplyTab_H1.setValue(TAB_GENERAL);
			}
			return;
		}
		
		
		//Change check
		if (changeFieldsCheck(bizMsg)) {
			//No change has been made.
			bizMsg.setMessageInfo("NMAM8333I");
			return;
		}
		
		//QC#10653
        MDSETMsg mdseTMsg = new MDSETMsg();
    	mdseTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
    	mdseTMsg.mdseCd.setValue(bizMsg.mdseCd_H1.getValue());
    	mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseTMsg);
    	boolean isExistsMdse = (mdseTMsg != null);
    	if (isExistsMdse == false) {
    		mdseTMsg = new MDSETMsg();
        	ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, getGlobalCompanyCode());
        	ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, bizMsg.mdseCd_H1);
    	}
		
    	// Update MDSE
    	//if (!NMAL0110CommonLogic.updateMdse(getGlobalCompanyCode(), bizMsg, mdseItemStsTMsg, mapIPCC, mdse8digitPossibleFlg)) {
    	if (!NMAL0110CommonLogic.updateMdse(getGlobalCompanyCode(), bizMsg, mdseItemStsTMsg, mapIPCC, mdse8digitPossibleFlg, mdseTMsg, isExistsMdse)) {
            return;
    	}

    	// Update ORD_TAKE_MDSE
    	// Condition
    	//   MDSE_RGTN_TP_CD = 1:Mercury
    	//   AMER_MSTR.Class Type=A
    	//   Customer Order Allowed=Y
        if (mdse8digitPossibleFlg && MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue())) {
	    	if (!NMAL0110CommonLogic.updateOrdTakeMdse(getGlobalCompanyCode(), bizMsg)) {
	            return;
	    	}
        // 2020/01/07 QC#55220 Add Start
        } else if (mdse8digitPossibleFlg && MODE_MOD.equals(bizMsg.xxModeCd_H1.getValue())) {
            if (isNotEquals(bizMsg.mdseItemStsCd_H1.getValue(), bizMsg.mdseItemStsCd_BK.getValue())) {
                if (!NMAL0110CommonLogic.updateOrdTakeMdseStsChange(getGlobalCompanyCode(), bizMsg)) {
                    return;
                }
            }
        // 2020/01/07 QC#55220 Add End
        }

        // 2020/04/07 QC#56017 Add Stat
        if (isNotEquals(bizMsg.xxChkBox_RP.getValue(), bizMsg.xxChkBox_BR.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_RP.getValue())) {
            if (!NMAL0110CommonLogic.updateOrdTakeMdse(getGlobalCompanyCode(), bizMsg)) {
                return;
            }
        }
        // 2020/04/07 QC#56017 Add End

		//Is changed? MDSE_STORE_PKG
        
    	// Units of Measure
    	BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
    	int radioIntValueK1 = 99999;
    	if (radioValueK1 != null) {
    		radioIntValueK1 = radioValueK1.intValue();
    	}
   		for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
   			boolean isFound = false;
   			String primPkgUomFlgL1 = bizMsg.L.no(i).primPkgUomFlg_L1.getValue();
   			boolean bPrimPkgUomFlgL1 = FLG_ON_Y.equals(primPkgUomFlgL1);
   		    for (int j = 0; j < bizMsg.K.getValidCount(); j++) {
   		    	boolean bPrimPkgUomFlgK1 = false;
   		    	if (j == radioIntValueK1) {
   		    		bPrimPkgUomFlgK1 = true;
   		    	}
   		        if (bizMsg.L.no(i).pkgUomCd_L1.getValue().equals(bizMsg.K.no(j).pkgUomCd_K1.getValue())) {
   		        	isFound = true;
   		        	BigDecimal inEachQty_L1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.L.no(i).inEachQty_L1.getValue());
   		        	BigDecimal inEachQty_K1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.K.no(j).inEachQty_K1.getValue());
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.K.no(j).pkgUomCd_K1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.K.no(j).inEachQty_K1)) {
   		                if(!NMAL0110CommonLogic.deleteMdseStorePkgAndDsMdseStorePkg(getGlobalCompanyCode(), bizMsg, j)) {
   		   		    	    return;
   		                }
   		        	} else if (!bizMsg.L.no(i).pkgUomCd_L1.getValue().equals(bizMsg.K.no(j).pkgUomCd_K1.getValue()) 
   		        			||inEachQty_L1.compareTo(inEachQty_K1) != 0 
   		        			|| bPrimPkgUomFlgL1 != bPrimPkgUomFlgK1) {
   		                // update.
   		        		NMAL0110CommonLogic.updateMdseStorePkgAndDsMdseStorePkg(getGlobalCompanyCode(), bizMsg, j);
   		            }
   		        }
   		    }
   		    if (isFound == false) {
   		    	if(!NMAL0110CommonLogic.deleteMdseStorePkgAndDsMdseStorePkg(getGlobalCompanyCode(), bizMsg, i)) {
   		    	    return;
   		    	}
   		    }
   		}
   		boolean chkpkgUomExists = NMAL0110CommonLogic.chkMdseStorePkgExists(bizMsg);
   		if (chkpkgUomExists) {
   			for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
   				boolean exisisFlg = false;
       			for (int j = 0; j < bizMsg.L.getValidCount(); j++) {
       				if (bizMsg.K.no(i).pkgUomCd_K1.getValue().equals(bizMsg.L.no(j).pkgUomCd_L1.getValue())) {
           				exisisFlg= true;
       				}
       			}
   				if (!exisisFlg) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.K.no(i).inEachQty_K1)) {
   						continue;
   					}
   					if (!NMAL0110CommonLogic.insertMdseStorePkgAndDsMdseStorePkg(getGlobalCompanyCode(), bizMsg, i)) {
   	   		    	    return;
   		            }
   		        }
   			}
   		}
    	// UPdate MDSE_STORE_PKG
    	// Dimensions(Each)
    	if (!NMAL0110CommonLogic.updateMdseStorePkg(getGlobalCompanyCode(), bizMsg, PKG_UOM.EACH)) {
    		return;
    	}
    	// Dimensions(UnBox)
    	if (!NMAL0110CommonLogic.updateMdseStorePkg(getGlobalCompanyCode(), bizMsg, PKG_UOM.EACH_UNBOX)) {
    		return;
    	}
    	
		//Is changed? MDSE_SFTY_INFO
    	
    	// Update MDSE_SFTY_INFO
    	if (!NMAL0110CommonLogic.updateMdseSftyInfo(getGlobalCompanyCode(), bizMsg)) {
            return;
    	}
    	
		//Is changed? MDSE_TERM_COND_OPT
    	if (MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(bizMsg.mdseItemTpCd_H1.getValue())) {
	    	// Update T&C Option
	   		for (int i = 0; i < bizMsg.Q.getValidCount(); i++) {
	   			boolean isFound = false;
	   		    for (int j = 0; j < bizMsg.N.getValidCount(); j++) {
	   		    	BigDecimal mdseTermCondOptPk_Q1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.Q.no(i).mdseTermCondOptPk_Q1.getValue());
	   		    	BigDecimal mdseTermCondOptPk_N1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.N.no(j).mdseTermCondOptPk_N1.getValue());
	   		        if (mdseTermCondOptPk_Q1.compareTo(mdseTermCondOptPk_N1) == 0) {
	   		        	isFound = true;
	   		        	if (!ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptTpCd_N1) && 
	   		        		!ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptValTxt_N1)) {
	   		                if(!NMAL0110CommonLogic.deleteMdseTermCondOpt(getGlobalCompanyCode(), bizMsg, j)) {
	   		   		    	    return;
	   		                }
	   		        	} else if (!bizMsg.Q.no(i).termCondOptTpCd_Q1.getValue().equals(bizMsg.N.no(j).termCondOptTpCd_N1.getValue()) 
	   		        			|| !bizMsg.Q.no(i).termCondOptValTxt_Q1.getValue().equals(bizMsg.N.no(j).termCondOptValTxt_N1.getValue())) {
	   		                // update.
	   		        		NMAL0110CommonLogic.updateMdseTermCondOpt(getGlobalCompanyCode(), bizMsg, j);
	   		            }
	   		        }
	   		    }
	   		    if (isFound == false) {
	   		    	if(!NMAL0110CommonLogic.deleteMdseTermCondOpt(getGlobalCompanyCode(), bizMsg, i)) {
	   		    	    return;
	   		    	}
	   		    }
	   		}
	   		boolean chkMdseTermCondOpt = NMAL0110CommonLogic.chkMdseTermCondOpt(bizMsg);
	   		if (chkMdseTermCondOpt) {
	   			for (int i = 0; i < bizMsg.N.getValidCount(); i++) {
	   		        if (!ZYPCommonFunc.hasValue(bizMsg.N.no(i).mdseTermCondOptPk_N1.getValue())) {
	   					if (!ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptTpCd_N1) 
	   							&& !ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptValTxt_N1)) {
	   						continue;
	   					}
	   					if (!NMAL0110CommonLogic.insertMdseTermCondOpt(getGlobalCompanyCode(), bizMsg, i)) {
	   	   		    	    return;
	   		            }
	   		        }
	   			}
	   		}
    	}
    	
		//Is changed? MDSE_CST_UPD_HIST_HDR and MDSE_CST_UPD_HIST_DTL
    	// Update MDSE_CST_UPD_HIST_HDR
    	// Update MDSE_CST_UPD_HIST_DTL
    	boolean changeStdCostFlg = true;
    	if (ZYPCommonFunc.hasValue(bizMsg.thisMthTotStdCostAmt_H1) && ZYPCommonFunc.hasValue(bizMsg.thisMthTotStdCostAmt_HB)) {
        	BigDecimal stdCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.thisMthTotStdCostAmt_H1.getValue());
        	BigDecimal preStdCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.thisMthTotStdCostAmt_HB.getValue());
        	changeStdCostFlg = (stdCostAmt.compareTo(preStdCostAmt) != 0);
    	}
    	//BigDecimal stdCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.thisMthTotStdCostAmt_H1.getValue());
    	//BigDecimal preStdCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.thisMthTotStdCostAmt_HB.getValue());
    	//boolean changeStdCostFlg = (stdCostAmt.compareTo(preStdCostAmt) != 0);
    	boolean changeAssetCostFlg = true;
    	if (ZYPCommonFunc.hasValue(bizMsg.assetRecovCostAmt_H1) && ZYPCommonFunc.hasValue(bizMsg.assetRecovCostAmt_HB)) {
        	BigDecimal assetCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.assetRecovCostAmt_H1.getValue());
        	BigDecimal preAssetCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.assetRecovCostAmt_HB.getValue());
        	changeAssetCostFlg = (assetCostAmt.compareTo(preAssetCostAmt) != 0);
    	}
    	//BigDecimal assetCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.assetRecovCostAmt_H1.getValue());
    	//BigDecimal preAssetCostAmt = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.assetRecovCostAmt_HB.getValue());
    	//boolean changeAssetCostFlg = (assetCostAmt.compareTo(preAssetCostAmt) != 0);
    	// Only change amount
   		if (changeStdCostFlg) {
   	    	if (!NMAL0110CommonLogic.updateMdseCstUpdHist(getGlobalCompanyCode(), bizMsg, MDSE_CST_UPD_TP.STANDARD_COST)) {
   	            return;
   	    	}
   		}
   		if (changeAssetCostFlg) {
   	    	if (!NMAL0110CommonLogic.updateMdseCstUpdHist(getGlobalCompanyCode(), bizMsg, MDSE_CST_UPD_TP.ARV_COST)) {
   	            return;
   	    	}
   		}
   		
		//Is changed? MDSE_SER_NUM_RNG
    	// Update MDSE_SER_NUM_RNG
   		for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
   		    	BigDecimal mdseSerNumRngPk_F1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.F.no(i).mdseSerNumRngPk_F1.getValue());
   		    	BigDecimal mdseSerNumRngPk_A1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.A.no(j).mdseSerNumRngPk_A1.getValue());
   		        if (mdseSerNumRngPk_F1.compareTo(mdseSerNumRngPk_A1) == 0) {
   		        	isFound = true;
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
   		                if(!NMAL0110CommonLogic.deleteMdseSerNumRng(getGlobalCompanyCode(), bizMsg, j)) {
   		   		    	    return;
   		                }
   		        	} else if (!bizMsg.F.no(i).fromSerNum_F1.getValue().equals(bizMsg.A.no(j).fromSerNum_A1.getValue()) 
   		        			|| !bizMsg.F.no(i).thruSerNum_F1.getValue().equals(bizMsg.A.no(j).thruSerNum_A1.getValue())) {
   		                // update.
   		        		NMAL0110CommonLogic.updateMdseSerNumRng(getGlobalCompanyCode(), bizMsg, j);
   		            }
   		        }
   		    }
   		    if (isFound == false) {
   		    	if(!NMAL0110CommonLogic.deleteMdseSerNumRng(getGlobalCompanyCode(), bizMsg, i)) {
   		    	    return;
   		    	}
   		    }
   		}
   		boolean chkDetailListExists = NMAL0110CommonLogic.chkDetailListExists(bizMsg);
   		if (chkDetailListExists) {
   			for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
   		        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseSerNumRngPk_A1.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
   						continue;
   					}
   					if (!NMAL0110CommonLogic.insertMdseSerNumRng(getGlobalCompanyCode(), bizMsg, i)) {
   	   		    	    return;
   		            }
   		        }
   			}
   		}

		//Is changed? SUPD_RELN
    	// Update SUPD_RELN
   		for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
   		    	BigDecimal supdRelnPk_H1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.H.no(i).supdRelnPk_H1.getValue());
   		    	BigDecimal supdRelnPk_C1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.C.no(j).supdRelnPk_C1.getValue());
   		        if (supdRelnPk_H1.compareTo(supdRelnPk_C1) == 0) {
   		        	isFound = true;
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdCratDt_C1)) {
   		                if(!NMAL0110CommonLogic.deleteSupdReln(getGlobalCompanyCode(), bizMsg, j)) {
   		   		    	    return;
   		                }
   	   					//Supd (To)
   						SUPDTMsg supd = NMAL0110CommonLogic.findSupd(getGlobalCompanyCode(), bizMsg.H.no(i).supdToMdseCd_H1.getValue());
   						if (supd != null) {
   							NMAL0110CommonLogic.deleteSupdTo(getGlobalCompanyCode(), bizMsg, bizMsg.C.no(i).supdToMdseCd_C1.getValue());
   						}
   		                
   		        	} else if (!bizMsg.H.no(i).supdToMdseCd_H1.getValue().equals(bizMsg.C.no(j).supdToMdseCd_C1.getValue()) 
   		        			|| !bizMsg.H.no(i).supdCratDt_H1.getValue().equals(bizMsg.C.no(j).supdCratDt_C1.getValue())) {
   		                // update.
   		        		NMAL0110CommonLogic.updateSupdReln(getGlobalCompanyCode(), bizMsg, j);
   	   					//Supd (To)
   						SUPDTMsg supd = NMAL0110CommonLogic.findSupd(getGlobalCompanyCode(), bizMsg.C.no(i).supdToMdseCd_C1.getValue());
   						if (supd == null) {
   							if (!NMAL0110CommonLogic.insertSupdTo(getGlobalCompanyCode(), bizMsg, bizMsg.C.no(i).supdToMdseCd_C1.getValue())) {
   			   		    	    return;
   				            }
   						} else {
   							NMAL0110CommonLogic.updateSupdTo(getGlobalCompanyCode(), bizMsg, bizMsg.C.no(i).supdToMdseCd_C1.getValue());
   						}
   		            }
   		        }
   		    }
   		    if (isFound == false) {
   		    	if(!NMAL0110CommonLogic.deleteSupdReln(getGlobalCompanyCode(), bizMsg, i)) {
   		    	    return;
   		    	}
   		    }
   		}
   		boolean chkSupdExists = NMAL0110CommonLogic.chkSupdExists(bizMsg);
   		if (chkSupdExists) {
   			for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
   		        if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdRelnPk_C1.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdCratDt_C1)) {
   						continue;
   					}
   					if (!NMAL0110CommonLogic.insertSupdReln(getGlobalCompanyCode(), bizMsg, i)) {
   	   		    	    return;
   		            }
   					//Supd (To)
					SUPDTMsg supd = NMAL0110CommonLogic.findSupd(getGlobalCompanyCode(), bizMsg.C.no(i).supdToMdseCd_C1.getValue());
					if (supd == null) {
						if (!NMAL0110CommonLogic.insertSupdTo(getGlobalCompanyCode(), bizMsg, bizMsg.C.no(i).supdToMdseCd_C1.getValue())) {
		   		    	    return;
			            }
					} else {
						NMAL0110CommonLogic.updateSupdTo(getGlobalCompanyCode(), bizMsg, bizMsg.C.no(i).supdToMdseCd_C1.getValue());
					}
   		        }
   			}
   		}
   		
   	    //QC#5580 Loop Check
   		for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
	        if (ZYPCommonFunc.hasValue(bizMsg.C.no(j).supdToMdseCd_C1)) {
	            S21SsmEZDResult result = NMAL0110Query.getInstance().getSupdReln(bizMsg.C.no(j).supdToMdseCd_C1.getValue());
	            if (result.isCodeNormal()) {
	                List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
	                if (list != null && list.size() > 0) {
	                    for (Map<String, String> map : list) {
	                        if (bizMsg.C.no(j).supdToMdseCd_C1.getValue().equals(map.get("SUPD_FROM_MDSE_CD"))) {
	                        	//NMAM8378E=0,Please avoid supersede circulation setting
	                    		bizMsg.C.no(j).supdToMdseCd_C1.setErrorInfo(1, "NMAM8378E");
	                    		return;
	                        }
	                    }
	                }
	            }
	        }
   		}

		//Is changed? SUPD
    	// Update SUPD (From)
		S21SsmEZDResult rsSupd = NMAL0110Query.getInstance().getSupdReln(bizMsg, getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
		if (rsSupd.isCodeNormal()) {
			List<Map<String, String>> list = (List<Map<String, String>>) rsSupd.getResultObject();
			if (list != null && list.size() > 0) {
				// Create and Update
				SUPDTMsg supd = NMAL0110CommonLogic.findSupd(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
				if (supd == null) {
					if (!NMAL0110CommonLogic.insertSupd(getGlobalCompanyCode(), bizMsg)) {
	   		    	    return;
		            }
				} else {
					NMAL0110CommonLogic.updateSupd(getGlobalCompanyCode(), bizMsg);
				}
			} else {
				// Delete
				if (!NMAL0110CommonLogic.deleteSupd(getGlobalCompanyCode(), bizMsg)) {
   		    	    return;
	            }
			}
		} else if (bizMsg.C.getValidCount() > 0) {
			// Create and Update
			SUPDTMsg supd = NMAL0110CommonLogic.findSupd(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
			if (supd == null) {
				if (!NMAL0110CommonLogic.insertSupd(getGlobalCompanyCode(), bizMsg)) {
   		    	    return;
	            }
			} else {
				NMAL0110CommonLogic.updateSupd(getGlobalCompanyCode(), bizMsg);
			}
		}

		
		//Is changed? MDSE_ITEM_FLIP_SET
    	// Update MDSE_ITEM_FLIP_SET
   		for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.D.getValidCount(); j++) {
   		    	String keyI = NMAL0110CommonLogic.nvlString(bizMsg.I.no(i).relnMdseCd_IB.getValue());
   		    	String keyD = NMAL0110CommonLogic.nvlString(bizMsg.D.no(j).relnMdseCd_DB.getValue());
   		        if (keyI.equals(keyD)) {
   		        	isFound = true;
   		        	// No Key
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1)) {
   		                if(!NMAL0110CommonLogic.deleteMdseItemFlipSet(getGlobalCompanyCode(), bizMsg, j)) {
   		   		    	    return;
   		                }
   		            // Key Change
   		        	} else if (!bizMsg.I.no(i).relnMdseCd_I1.getValue().equals(bizMsg.D.no(j).relnMdseCd_D1.getValue()) 
   		        			|| !bizMsg.I.no(i).mdseItemRelnTpCd_I1.getValue().equals(bizMsg.D.no(j).mdseItemRelnTpCd_D1.getValue())) {
   		                // delete and insert
   		                if(!NMAL0110CommonLogic.updateMdseItemFlipSet(getGlobalCompanyCode(), bizMsg, j)) {
   		   		    	    return;
   		                }
   		            }
   		        }
   		    }
   		    if (isFound == false) {
   		    	// Delete Line
   		    	if(!NMAL0110CommonLogic.deleteMdseItemFlipSet(getGlobalCompanyCode(), bizMsg, i)) {
   		    	    return;
   		    	}
   		    }
   		}
   		boolean chkMdseItemFlipSetExists = NMAL0110CommonLogic.chkMdseItemFlipSetExists(bizMsg);
   		if (chkMdseItemFlipSetExists) {
   			for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
   		        if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_DB.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1)) {
   						continue;
   					}
   					// Add Line
   					if (!NMAL0110CommonLogic.insertMdseItemFlipSet(getGlobalCompanyCode(), bizMsg, i)) {
   	   		    	    return;
   		            }
   		        }
   			}
   		}
    	
		//Is changed? CUST_MDSE_XREF
    	// Update CUST_MDSE_XREF
   		for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
   		    	String keyJ = NMAL0110CommonLogic.nvlString(bizMsg.J.no(i).dsAcctNum_JB.getValue()) + NMAL0110CommonLogic.nvlString(bizMsg.J.no(i).custMdseCd_JB.getValue());
   		    	String keyE = NMAL0110CommonLogic.nvlString(bizMsg.E.no(j).dsAcctNum_EB.getValue()) + NMAL0110CommonLogic.nvlString(bizMsg.E.no(j).custMdseCd_EB.getValue());
   		        if (keyJ.equals(keyE)) {
   		        	isFound = true;
   		        	// No Key
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseCd_E1)) {
   		                if(!NMAL0110CommonLogic.deleteCustMdseXref(getGlobalCompanyCode(), bizMsg, j)) {
   		   		    	    return;
   		                }
   		                
   		            // Key Change
   		        	} else if (!bizMsg.J.no(i).dsAcctNum_J1.getValue().equals(bizMsg.E.no(j).dsAcctNum_E1.getValue()) 
   		        			|| !bizMsg.J.no(i).custMdseCd_J1.getValue().equals(bizMsg.E.no(j).custMdseCd_E1.getValue())) {
   		                if(!NMAL0110CommonLogic.deleteCustMdseXref(getGlobalCompanyCode(), bizMsg, j)) {
   		   		    	    return;
   		                }
   	   					if (!NMAL0110CommonLogic.insertCustMdseXref(getGlobalCompanyCode(), bizMsg, i)) {
   	   	   		    	    return;
   	   		            }

   	   				// Mod Start 2017/01/04 QC#16926
   	   				// No Key Change
//   		        	} else if (!bizMsg.J.no(i).effFromDt_J1.getValue().equals(bizMsg.E.no(j).effFromDt_E1.getValue())
//   		        			|| !bizMsg.J.no(i).xxChkBox_JN.getValue().equals(bizMsg.E.no(j).xxChkBox_EN.getValue())) {
   		        	} else if (!bizMsg.J.no(i).custMdseNm_J1.getValue().equals(bizMsg.E.no(j).custMdseNm_E1.getValue())
   		        	        || !bizMsg.J.no(i).effFromDt_J1.getValue().equals(bizMsg.E.no(j).effFromDt_E1.getValue())
                            || !bizMsg.J.no(i).xxChkBox_JN.getValue().equals(bizMsg.E.no(j).xxChkBox_EN.getValue())) {
   		                // update.
   		        		NMAL0110CommonLogic.updateCustMdseXref(getGlobalCompanyCode(), bizMsg, j);
   		            }
   		        	// Mod End 2017/01/04 QC#16926
   		        }
   		    }
   		    // Delete Line
   		    if (isFound == false) {
   		    	if(!NMAL0110CommonLogic.deleteCustMdseXref(getGlobalCompanyCode(), bizMsg, i)) {
   		    	    return;
   		    	}
   		    }
   		}
   		boolean chkCustMdseXrefExists = NMAL0110CommonLogic.chkCustMdseXrefExists(bizMsg);
   		if (chkCustMdseXrefExists) {
   			for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
   				// Add Line
   		        if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_EB.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseCd_E1)
   							&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).effFromDt_E1)
   							&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).xxChkBox_EN)
   							) {
   						continue;
   					}
   					if (!NMAL0110CommonLogic.insertCustMdseXref(getGlobalCompanyCode(), bizMsg, i)) {
   	   		    	    return;
   		            }
   		        }
   			}
   		}

   		//QC#10653
    	// invoke Master Data Messaging
        NMAL0110CommonLogic.invokeMasterDataMessaging(
                isExistsMdse
               ,bizMsg.mdseCd_H1.getValue());
   		
   		//to Modify mode
   		ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_SC, bizMsg.mdseCd_H1);

        // 2019/10/28 QC#53741 Add Start
        NMAL0110CommonLogic.setDimensionsUOM(bizMsg, getGlobalCompanyCode());
        // 2019/10/28 QC#53741 Add End

   		// search again
        NMAL0110CommonLogic.search(bizMsg, getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), true);
        if (!bizMsg.getMessageCode().endsWith("I")) {
            // NMAM8182I=0,[@] process ended normally.
            bizMsg.setMessageInfo("NMAM8182I", new String[] {"Merchandise Master Regstration" });
        }

    }
    
    private boolean storeName(NMAL0110CMsg bizMsg, EZDSMsg msg) {
    	
        boolean errorFlg = false;
        
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1)) {
        	MDSE_ITEM_TPTMsg mdseItemTpTMsg = NMAL0110CommonLogic.findMdseItemTp(getGlobalCompanyCode(), bizMsg.mdseItemTpCd_H1.getValue());
        	if (mdseItemTpTMsg != null) {
        		ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpDescTxt_H1, mdseItemTpTMsg.mdseItemTpDescTxt);
        	}
        }
        
        if (ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1)) {
        	COA_PROJTMsg coaProjTMsg = NMAL0110CommonLogic.findCoaProj(getGlobalCompanyCode(), bizMsg.coaMdseTpCd_H1.getValue());
        	if (coaProjTMsg != null) {
        		ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, coaProjTMsg.coaProjDescTxt);
        	}
        }
        
        if (ZYPCommonFunc.hasValue(bizMsg.swLicCtrlTpCd_H1)) {
        	SW_LIC_CTRL_TPTMsg swLicCtrlTpTMsg = NMAL0110CommonLogic.findSwLicCtrlTp(getGlobalCompanyCode(), bizMsg.swLicCtrlTpCd_H1.getValue());
        	if (swLicCtrlTpTMsg != null) {
        		ZYPEZDItemValueSetter.setValue(bizMsg.swLicCtrlTpDescTxt_H1, swLicCtrlTpTMsg.swLicCtrlTpDescTxt);
        	}
        }
        
        if (ZYPCommonFunc.hasValue(bizMsg.swCatgCd_H1)) {
        	SW_CATGTMsg swCatgTMsg = NMAL0110CommonLogic.findSwCatg(getGlobalCompanyCode(), bizMsg.swCatgCd_H1.getValue());
        	if (swCatgTMsg != null) {
        		ZYPEZDItemValueSetter.setValue(bizMsg.swCatgDescTxt_H1, swCatgTMsg.swCatgDescTxt);
        	}
        }
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
        
    }
    
    private boolean checkInput_Header(NMAL0110CMsg bizMsg, EZDSMsg msg) {
    	
        boolean errorFlg = false;
        
        // #################
        // Exists Check
        // #################
        // ITEM NUMBER
        // when mode=New, check MDSE no exists.
        if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue())) {
        	MDSETMsg mdseTmsg = NMAL0110CommonLogic.findMdse(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
        	if (mdseTmsg != null) {
        		// ZZPM0027E=0,Corresponding data already exists.
        		bizMsg.mdseCd_H1.setErrorInfo(1, "ZZPM0027E");
        		errorFlg = true;
        	}
        } else {
        	MDSETMsg mdseTmsg = NMAL0110CommonLogic.findMdse(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
        	if (mdseTmsg == null) {
        		// ZZMM0014E=0,Data select failure.  [ TableName = @ , key = @, value = @ ]
        		bizMsg.mdseCd_H1.setErrorInfo(1, "ZZMM0014E", new String[]{"MDSE", "MDSE_CD", bizMsg.mdseCd_H1.getValue()});
        		errorFlg = true;
        	}
        }

        //QC#16671
        //Disclosure Check is only new registration check case
        if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue())) {
            //Registration Mode Check
            if (MDSE_RGTN_TP.MERCURY.equals(bizMsg.mdseRgtnTpCd_H1.getValue())) {
    	        AMER_MSTRTMsgArray resultAmerMstr = NMAL0110CommonLogic.chkAMERMSTRTMsg(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
    	        if (resultAmerMstr == null || resultAmerMstr.length() <= 0) {
    	        	//NMAM0201E=0,[@] does not exist in Americas Mercury.
    	        	bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0201E", new String[]{"Item Number"});
            		errorFlg = true;
    	        }
    	        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
    	        S21UserInfo user = profile.getContextUserInfo();
    	        AMER_CMPYTMsg amerCmpyTMsgResult = NMAL0110CommonLogic.checkAmerCmpy(bizMsg, bizMsg.mdseCd_H1.getValue(), user, getGlobalCompanyCode());
    	        if (amerCmpyTMsgResult == null) {
            		//NMAM0202E=0,[@] has not been disclosed to @ in Americas Mercury.
            		bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0202E", new String[]{"Item Number", user.getUserCompanyCode()});
            		errorFlg = true;
    	        }
    	        //The disclosure process of [@] to @ is not completed.
                if (amerCmpyTMsgResult != null && !amerCmpyTMsgResult.amerXpndTpCd.getValue().equals("1") && MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue())) {
                	//NMAM0203E=0,The disclosure process of [@] to @ is not completed.
                    bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0203E", new String[] {"Item Number", user.getUserCompanyCode() });
                    errorFlg = true;
                }
            	
            } else if (MDSE_RGTN_TP.S21_PARTS.equals(bizMsg.mdseRgtnTpCd_H1.getValue())) {
            	
        		//CUSA PARTS_V
        		String cusaPartsViewName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_USMM4100", getGlobalCompanyCode());
            	if (ZYPCommonFunc.hasValue(cusaPartsViewName)) {
    	            S21SsmEZDResult ssmResult = NMAL0110Query.getInstance().getS21Parts(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), cusaPartsViewName);
    	            if (!ssmResult.isCodeNormal()) {
    	                 bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0163E", new String[] {bizMsg.mdseCd_H1.getValue(), "S21 Parts" });
    	                 errorFlg = true;
    	            }
            	}
            } else {
                // 2018/08/03 QC#26046 Add Start
                // To avoid Fake Mercury Code registration
                if (NMAL0110CommonLogic.chk8digitExistInOrdTakeMdse(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue())) {
                    bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM8687E");
                    errorFlg = true;
                }
                // 2018/08/03 QC#26046 Add End

    	        AMER_MSTRTMsgArray resultAmerMstr = NMAL0110CommonLogic.chkAMERMSTRTMsg(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
    	        if (resultAmerMstr != null && resultAmerMstr.length() > 0) {
            		//NMAM0834E=0,[@] already exists in [@]
            		bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0834E", new String[]{bizMsg.mdseCd_H1.getValue(), "Mercury"});
            		errorFlg = true;
    	        }
        		//CUSA PARTS_V
        		String cusaPartsViewName = ZYPCodeDataUtil.getVarCharConstValue("CUSA_USMM4100", getGlobalCompanyCode());
            	if (ZYPCommonFunc.hasValue(cusaPartsViewName)) {
    	            S21SsmEZDResult ssmResult = NMAL0110Query.getInstance().getS21Parts(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), cusaPartsViewName);
    	            if (ssmResult.isCodeNormal()) {
    	            	//NMAM0834E=0,[@] already exists in [@]
    	                 bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0834E", new String[] {bizMsg.mdseCd_H1.getValue(), "S21 Parts" });
    	         		errorFlg = true;
    	            }
            	}
                ORD_TAKE_MDSETMsg duplicateOrdTakeMdseTMsg = NMAL0110CommonLogic.getDuplicateOrdTakeMdseTMsg(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
                if (duplicateOrdTakeMdseTMsg != null) {
                	//NMAM0072E=0, @  is duplicated.
                    bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number" });
            		errorFlg = true;
                }

                AMER_MSTRTMsg duplicateAmerMstrTMsg = NMAL0110CommonLogic.getDuplicateAmerMstrTMsg(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
                if (duplicateAmerMstrTMsg != null) {
                	//NMAM0072E=0, @  is duplicated.
                    bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number" });
            		errorFlg = true;
                }
                
            	if (ZYPCommonFunc.hasValue(cusaPartsViewName)) {
            		S21SsmEZDResult ssmPartsResult = NMAL0110Query.getInstance().getDuplicateS21Parts(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), cusaPartsViewName);
    	            if (ssmPartsResult.isCodeNormal()) {
    	            	//NMAM0072E=0, @  is duplicated.
    	                bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number" });
    	        		errorFlg = true;
    	            }
            	}
            }
        }
        
        // MERCHANDISE TYPE check COA_PROJ exists
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1)) {
        	MDSE_ITEM_TPTMsg mdseItemTmsg = NMAL0110CommonLogic.findMdseItemTp(getGlobalCompanyCode(), bizMsg.mdseItemTpCd_H1.getValue());
        	if (mdseItemTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.mdseItemTpCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Item Type", "Item Type Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpDescTxt_H1, mdseItemTmsg.mdseItemTpDescTxt);
        	}
        } else {
    		bizMsg.mdseItemTpDescTxt_H1.clear();
        }

        //MERCHANDISE TYPE exists
//        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) && ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1)) {
//			String coaMdseTp = ZYPCommonFunc.hasValue(bizMsg.coaProjDescTxt_H1) ? bizMsg.coaProjDescTxt_H1.getValue() : bizMsg.coaMdseTpCd_H1.getValue();
//			String mdseItemTp = ZYPCommonFunc.hasValue(bizMsg.mdseItemTpDescTxt_H1) ? bizMsg.mdseItemTpDescTxt_H1.getValue() : bizMsg.mdseItemTpCd_H1.getValue();
//    		S21SsmEZDResult rsIntgProdCatgConv = NMAL0110Query.getInstance().getIntgProdCatgConvList(getGlobalCompanyCode(), bizMsg.mdseItemTpCd_H1.getValue(), bizMsg.coaMdseTpCd_H1.getValue());
//    		if (rsIntgProdCatgConv.isCodeNormal()) {
//    			List<Map<String, Object>> list = (List<Map<String, Object>>) rsIntgProdCatgConv.getResultObject();
//    			if (list == null || list.size() <= 0) {
//    				//NMAM8198E=0,Can not set MERCHANDISE TYPE[@] in case of ITEM TYPE[@]
//            		bizMsg.coaMdseTpCd_H1.setErrorInfo(1, "NMAM8198E", new String[]{coaMdseTp, mdseItemTp});
//            		errorFlg = true;
//    			} else {
//    				boolean exists = false;
//    				for (Map<String, Object> map : list) {
//		    			if (map == null || map.get("COA_MDSE_TP_CD") == null) {
//		    				//NMAM8198E=0,Can not set MERCHANDISE TYPE[@] in case of ITEM TYPE[@]
//		            		bizMsg.coaMdseTpCd_H1.setErrorInfo(1, "NMAM8198E", new String[]{coaMdseTp, mdseItemTp});
//		            		errorFlg = true;
//		    			} else {
//		    				if (bizMsg.coaMdseTpCd_H1.getValue().equals((String)map.get("COA_MDSE_TP_CD"))
//		    						|| "*".equals((String)map.get("COA_MDSE_TP_CD"))) {
//		    					exists = true;
//		    				}
//		    			}
//    				}
//    				if (!exists) {
//        				//NMAM8198E=0,Can not set MERCHANDISE TYPE[@] in case of ITEM TYPE[@]
//                		bizMsg.coaMdseTpCd_H1.setErrorInfo(1, "NMAM8198E", new String[]{coaMdseTp, mdseItemTp});
//                		errorFlg = true;
//    				}
//    			}
//    		} else {
//				//NMAM8198E=0,Can not set MERCHANDISE TYPE[@] in case of ITEM TYPE[@]
//        		bizMsg.coaMdseTpCd_H1.setErrorInfo(1, "NMAM8198E", new String[]{coaMdseTp, mdseItemTp});
//        		errorFlg = true;
//    		}
//        }
        
        // MERCHANDISE TYPE check COA_PROJ exists
        if (ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1)) {
        	COA_PROJTMsg coaProjTmsg = NMAL0110CommonLogic.findCoaProj(getGlobalCompanyCode(), bizMsg.coaMdseTpCd_H1.getValue());
        	if (coaProjTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.coaMdseTpCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Merchandise Type", "Coa Project Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, coaProjTmsg.coaProjDescTxt);
        	}

            // 2019/10/23 QC#51964 Add Start
            if (MODE_MOD.equals(bizMsg.xxModeCd_H1.getValue())
                    && ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_BK)
                    && !bizMsg.coaMdseTpCd_H1.getValue().equals(bizMsg.coaMdseTpCd_BK.getValue())) {
                if (checkMdseTp(bizMsg)) {
                    bizMsg.coaMdseTpCd_H1.setErrorInfo(1, "NMAM8707E", null);
                    errorFlg = true;
                }
            }
            // 2019/10/23 QC#51964 Add End
        } else {
    		bizMsg.coaProjDescTxt_H1.clear();
        }
        
        // PRODUCT CODE check COA_PROD exists
        // set Product Name
        if (ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1)) {
        	COA_PRODTMsg coaProdTmsg = NMAL0110CommonLogic.findCoaProd(getGlobalCompanyCode(), bizMsg.coaProdCd_H1.getValue());
        	if (coaProdTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.coaProdCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Product Code", "Coa Product Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, coaProdTmsg.coaProdNm);
        	}
        } else {
    		bizMsg.coaProdNm_H1.clear();
        }
        
		// MARKETING MODEL NAME
		if (ZYPCommonFunc.hasValue(bizMsg.mktMdlCd_H1)) {
			MKT_MDLTMsg mktMdlTMsg = new MKT_MDLTMsg();
	        ZYPEZDItemValueSetter.setValue(mktMdlTMsg.glblCmpyCd, getGlobalCompanyCode());
	        ZYPEZDItemValueSetter.setValue(mktMdlTMsg.mktMdlCd, bizMsg.mktMdlCd_H1);
	        mktMdlTMsg = (MKT_MDLTMsg) EZDTBLAccessor.findByKey(mktMdlTMsg);
	        if (mktMdlTMsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.mktMdlCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Marketing Model", "Mkt Model Master"});
        		errorFlg = true;
	        } else {
				ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlNm_H1, mktMdlTMsg.mktMdlNm);
	        }
		} else {
			bizMsg.mktMdlNm_H1.clear();
		}
		
		// MARKETING SEGMENT NAME
		if (ZYPCommonFunc.hasValue(bizMsg.mktMdlSegCd_H1)) {
			MKT_MDL_SEGTMsg mktMdlSegTMsg = new MKT_MDL_SEGTMsg();
	        ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.glblCmpyCd, getGlobalCompanyCode());
	        ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.mktMdlSegCd, bizMsg.mktMdlSegCd_H1);
	        mktMdlSegTMsg = (MKT_MDL_SEGTMsg) EZDTBLAccessor.findByKey(mktMdlSegTMsg);
	        if (mktMdlSegTMsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.mktMdlSegCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Marketing Segment", "Mkt Seg Master"});
        		errorFlg = true;
	        } else {
	        	ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegNm_H1, mktMdlSegTMsg.mktMdlSegNm);
	        }
		} else {
			bizMsg.mktMdlSegNm_H1.clear();
		}
        
        if (MDSE_ITEM_TP.MACHINE.equals(bizMsg.mdseItemTpCd_H1.getValue())
        		|| (MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd_H1.getValue())
        				&& COA_MDSE_TP.MACHINE.equals(bizMsg.coaMdseTpCd_H1.getValue()))
        	){
        	if (!ZYPCommonFunc.hasValue(bizMsg.mktMdlCd_H1)) {
        		//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.mktMdlCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Marketing Model"});
        		errorFlg = true;
        	}
        	if (!ZYPCommonFunc.hasValue(bizMsg.mktMdlSegCd_H1)) {
        		//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.mktMdlSegCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Marketing Segment"});
        		errorFlg = true;
        	}
        }
        
        // Product Level 1 - 5 check PROD_CTRL exists
        if (ZYPCommonFunc.hasValue(bizMsg.zerothProdCtrlCd_H1)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getZerothProdHrch(bizMsg, getGlobalCompanyCode());
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.zerothProdCtrlCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.zerothProdCtrlCd_H1.getValue(), "Product Level 1 Master"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlNm_H1, (String) map.get("PROD_CTRL_DESC_TXT"));
        	}
        }
        if (ZYPCommonFunc.hasValue(bizMsg.firstProdCtrlCd_H1)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getFirstProdHrch(bizMsg, getGlobalCompanyCode());
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.firstProdCtrlCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.firstProdCtrlCd_H1.getValue(), "Product Level 2 Master"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlNm_H1, (String) map.get("PROD_CTRL_DESC_TXT"));
        	}
        }
        if (ZYPCommonFunc.hasValue(bizMsg.scdProdCtrlCd_H1)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getScdProdHrch(bizMsg, getGlobalCompanyCode());
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.scdProdCtrlCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.scdProdCtrlCd_H1.getValue(), "Product Level 3 Master"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlNm_H1, (String) map.get("PROD_CTRL_DESC_TXT"));
        	}
        }
        if (ZYPCommonFunc.hasValue(bizMsg.thirdProdCtrlCd_H1)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getThirdProdHrch(bizMsg, getGlobalCompanyCode());
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.thirdProdCtrlCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.thirdProdCtrlCd_H1.getValue(), "Product Level 4 Master"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlNm_H1, (String) map.get("PROD_CTRL_DESC_TXT"));
        	}
        }
        if (ZYPCommonFunc.hasValue(bizMsg.scdProdCtrlCd_H1) && ZYPCommonFunc.hasValue(bizMsg.thirdProdCtrlCd_H1)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getThirdProdHrchReln(bizMsg, getGlobalCompanyCode());
        	if (map == null) {
        		// NMAM0306E=0,The combination between [@] and [@] is not correct.
        		bizMsg.scdProdCtrlCd_H1.setErrorInfo(1, "NMAM0306E", new String[]{"Product Level 3", "Product Level 4"});
        		bizMsg.thirdProdCtrlCd_H1.setErrorInfo(1, "NMAM0306E", new String[]{"Product Level 3", "Product Level 4"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlNm_H1, (String) map.get("PROD_CTRL_DESC_TXT"));
        	}
        }
        
        if (ZYPCommonFunc.hasValue(bizMsg.frthProdCtrlCd_H1)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getFrthProdHrch(bizMsg, getGlobalCompanyCode());
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.frthProdCtrlCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.frthProdCtrlCd_H1.getValue(), "Product Level 5 Master"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlNm_H1, (String) map.get("PROD_CTRL_DESC_TXT"));
        	}
        }
        
        if (ZYPCommonFunc.hasValue(bizMsg.slsMatGrpCd_01)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getSlsMatGrp(bizMsg, getGlobalCompanyCode(), bizMsg.slsMatGrpCd_01.getValue(), "1");
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.slsMatGrpCd_01.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.slsMatGrpCd_01.getValue(), "Material Group Level 1"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_01, (String) map.get("SLS_MAT_GRP_DESC_TXT"));
        	}
        }

        if (ZYPCommonFunc.hasValue(bizMsg.slsMatGrpCd_02)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getSlsMatGrp(bizMsg, getGlobalCompanyCode(), bizMsg.slsMatGrpCd_02.getValue(), "2");
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.slsMatGrpCd_02.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.slsMatGrpCd_02.getValue(), "Material Group Level 2"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_02, (String) map.get("SLS_MAT_GRP_DESC_TXT"));
        	}
        }

        if (ZYPCommonFunc.hasValue(bizMsg.slsMatGrpCd_03)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getSlsMatGrp(bizMsg, getGlobalCompanyCode(), bizMsg.slsMatGrpCd_03.getValue(), "3");
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.slsMatGrpCd_03.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.slsMatGrpCd_03.getValue(), "Material Group Level 3"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_03, (String) map.get("SLS_MAT_GRP_DESC_TXT"));
        	}
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsCmsnGrpCd_H1)) {
        	Map<String, Object> map = NMAL0110CommonLogic.getDsCmsnGrp(bizMsg, getGlobalCompanyCode(), bizMsg.dsCmsnGrpCd_H1.getValue());
        	if (map == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.dsCmsnGrpCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{bizMsg.dsCmsnGrpCd_H1.getValue(), "Commision Group"});
        		errorFlg = true;
        	} else {
    	        ZYPEZDItemValueSetter.setValue(bizMsg.dsCmsnGrpDescTxt_H1, (String) map.get("DS_CMSN_GRP_DESC_TXT"));
        	}
        }

        // 2022/02/17 QC#59693 Add Start
        // Default Source Procure Type check 
        if (ZYPCommonFunc.hasValue(bizMsg.defSrcWhCd_H1)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.defSrcProcrTpCd_H1)) {
                // NMAM0849E=0,If [@] is set, [@] must be entered.
                bizMsg.defSrcProcrTpCd_H1.setErrorInfo(1, "NMAM0849E", new String[]{"Default Source Warehouse", "Default Source Type"});
                errorFlg = true;
            } else {
                String supplierWh = ZYPCodeDataUtil.getVarCharConstValue("NMAL0110_SUP_WH", getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(supplierWh)) {
                    String[] supplierWhList = supplierWh.split(",");
                    if (Arrays.asList(supplierWhList).contains(bizMsg.defSrcWhCd_H1.getValue())) {
                        if (!PROCR_TP.SUPPLIER.equals(bizMsg.defSrcProcrTpCd_H1.getValue())) {
                            // NMAM8712E=0,If Default Source WH is [@], Default Source Procurement Type is [@].
                            bizMsg.defSrcProcrTpCd_H1.setErrorInfo(1, "NMAM8712E", new String[]{bizMsg.defSrcWhCd_H1.getValue(), "Supplier"});
                            errorFlg = true;
                        }
                    } else {
                        if (!PROCR_TP.WAREHOUSE.equals(bizMsg.defSrcProcrTpCd_H1.getValue())) {
                            // NMAM8712E=0,If Default Source WH is [@], Default Source Procurement Type is [@].
                            bizMsg.defSrcProcrTpCd_H1.setErrorInfo(1, "NMAM8712E", new String[]{bizMsg.defSrcWhCd_H1.getValue(), "Warehouse"});
                            errorFlg = true;
                        }
                    }
                }
            }
        } else {
            bizMsg.defSrcProcrTpCd_H1.clear();
        }
        // 2022/02/17 QC#59693 Add End

        // #################
        // Relation Check
        // #################
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }
    
    private boolean checkInput_TAB_General(NMAL0110CMsg bizMsg, EZDSMsg msg) {

        boolean errorFlg = false;

        // #################
        // Mandatory Check
        // #################
        if (checkInput_TAB_General_Mandatory(bizMsg)) {
        	return true;
        }
        
        // #################
        // Exists Check
        // #################
        // MANUFACTURED COUNTRY check CTRY exists
        if (ZYPCommonFunc.hasValue(bizMsg.madeInCtryCd_H1)) {
        	CTRYTMsg ctryTmsg = NMAL0110CommonLogic.findCtry(getGlobalCompanyCode(), bizMsg.madeInCtryCd_H1.getValue());
        	if (ctryTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.madeInCtryCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Manufactured Country", "Country Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_MI, ctryTmsg.ctryNm);
        	}
        }
        
        // ASSEMBLED COUNTRY check CTRY exists
        if (ZYPCommonFunc.hasValue(bizMsg.asmInCtryCd_H1)) {
        	CTRYTMsg ctryTmsg = NMAL0110CommonLogic.findCtry(getGlobalCompanyCode(), bizMsg.asmInCtryCd_H1.getValue());
        	if (ctryTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.asmInCtryCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Assembled Country", "Country Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_AI, ctryTmsg.ctryNm);
        	}
        }
        
        // Software Production Category exists
        if (ZYPCommonFunc.hasValue(bizMsg.swProdCatgCd_H1)) {
        	SW_PROD_CATGTMsg swProdCatgTmsg = NMAL0110CommonLogic.findSwProdCatg(getGlobalCompanyCode(), bizMsg.swProdCatgCd_H1.getValue());
        	if (swProdCatgTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		//QC#9891
        		//bizMsg.asmInCtryCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Software Production Category", "Software Production Category Master"});
        		bizMsg.swProdCatgCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Software Production Category", "Software Production Category Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.swProdCatgDescTxt_H1, swProdCatgTmsg.swProdCatgDescTxt);
        	}
        }
        
        //Freight Class
        if (ZYPCommonFunc.hasValue(bizMsg.frtClsCd_H1)) {
        	FRT_CLSTMsg frtClsTmsg = NMAL0110CommonLogic.findFrtCls(getGlobalCompanyCode(), bizMsg.frtClsCd_H1.getValue());
        	if (frtClsTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		//QC#9891
        		//bizMsg.asmInCtryCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Software Production Category", "Software Production Category Master"});
        		bizMsg.frtClsCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Freight Class", "Freight Class Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.frtClsDescTxt_H1, frtClsTmsg.frtClsDescTxt);
        		ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemNum_H1, frtClsTmsg.nmfcItemNum);
        		ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemSubNum_H1, frtClsTmsg.nmfcItemSubNum);
        		ZYPEZDItemValueSetter.setValue(bizMsg.nmfcClsNum_H1, frtClsTmsg.nmfcClsNum);
        	}
        }
        // Tariff
        if (ZYPCommonFunc.hasValue(bizMsg.trfCd_H1)) {
        	TRFTMsg trfTmsg = NMAL0110CommonLogic.findTrf(getGlobalCompanyCode(), bizMsg.trfCd_H1.getValue());
        	if (trfTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.trfCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Tariff", "Tariff Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.trfDescTxt_H1, trfTmsg.trfDescTxt);
        	}
        }        
        
        if (bizMsg.N.getValidCount() > 0) {
        	int cntRspTm = 0;
        	int errIdx = 0;
        	List<String> chkList = new ArrayList<String>();
        	for (int i = 0; i < bizMsg.N.getValidCount(); i++) {
        		if (chkList.contains(bizMsg.N.no(i).termCondOptTpCd_N1.getValue())) {
        			//Same T&C Option should be selected only one.
            		bizMsg.N.no(i).termCondOptTpCd_N1.setErrorInfo(1, "NMAM8336E");
            		errorFlg = true;
            		break;
        		}
        		TERM_COND_OPT_TPTMsg termCondOptTpTMsg = NMAL0110CommonLogic.findTermCondOptTp(getGlobalCompanyCode(), bizMsg.N.no(i).termCondOptTpCd_N1.getValue());
        		if (termCondOptTpTMsg != null && "Y".equals(termCondOptTpTMsg.rspTmTpFlg.getValue())) {
        			cntRspTm++;
        			errIdx = i;
        		}
        		chkList.add(bizMsg.N.no(i).termCondOptTpCd_N1.getValue());
        	}
        	if (cntRspTm > 1) {
        		//NMAM8325E=0,T&C Option should be selected only one Response Time.
        		bizMsg.N.no(errIdx).termCondOptTpCd_N1.setErrorInfo(1, "NMAM8325E");
        		errorFlg = true;
        	}
        }
        
        // Bundle Mdse Code
        if (ZYPCommonFunc.hasValue(bizMsg.bdlMaintMdseCd_H1)) {
        	if (!NMAL0110CommonLogic.existsAllMdseV(getGlobalCompanyCode(), bizMsg.bdlMaintMdseCd_H1.getValue())) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.bdlMaintMdseCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Bundled Maintenance Item", "Item Master"});
        		errorFlg = true;
        	}
        }
     // S21_NA Mod Start QC#17149(Sol#259)
        //QC#14441
        // Area of Paper
        if ((MDSE_ITEM_TP.SUPPLY.equals(bizMsg.mdseItemTpCd_H1.getValue()) || MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd_H1.getValue())) //
                && ZYPCommonFunc.hasValue(bizMsg.easyPackTpCd_H1) && "01".equals(bizMsg.easyPackTpCd_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.areaOfPaperNum_H1) || BigDecimal.ZERO.compareTo(bizMsg.areaOfPaperNum_H1.getValue()) >= 0) {
                //NMZM0334E=0,In case of Easy PAC I, Please set valid value in Area of Paper (Square foot).
                bizMsg.areaOfPaperNum_H1.setErrorInfo(1, "NMZM0334E");
                errorFlg = true;
            }
        }
        // S21_NA Mod End QC#17149(Sol#259)
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }
    private boolean checkInput_TAB_General_Mandatory(NMAL0110CMsg bizMsg) {
    	boolean errorFlg = false;
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_HM) && FLG_ON_Y.equals(bizMsg.xxChkBox_HM.getValue())) {
            // HAZARDOUS NUMBER
            // hazMatCd_H1
        	if (!ZYPCommonFunc.hasValue(bizMsg.hazMatCd_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.hazMatCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Hazardous Number"});
        		errorFlg = true;
        	}
            // MANUFACTURED COUNTRY
            // madeInCtryCd_H1
            if (!ZYPCommonFunc.hasValue(bizMsg.madeInCtryCd_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
            	bizMsg.madeInCtryCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Manufactured Country"});
        		errorFlg = true;
            }
        }

        //Dimensions(Each)
        if (!ZYPCommonFunc.hasValue(bizMsg.inPoundWt_EA)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.inPoundWt_EA.setErrorInfo(1, "ZZM9000E", new String[]{"Weight(pounds) - Boxed"});
    		errorFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.inInchLg_EA)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.inInchLg_EA.setErrorInfo(1, "ZZM9000E", new String[]{"Length(inches) - Boxed"});
    		errorFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.inInchWdt_EA)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.inInchWdt_EA.setErrorInfo(1, "ZZM9000E", new String[]{"Depth(inches) - Boxed"});
    		errorFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.inInchHgt_EA)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.inInchHgt_EA.setErrorInfo(1, "ZZM9000E", new String[]{"Height(inches) - Boxed"});
    		errorFlg = true;
        }
        
    	// PKG_UOM Table
        if (!ZYPCommonFunc.hasValue(bizMsg.pkgUomClsCd_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.pkgUomClsCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Unit of Measure Class"});
    		errorFlg = true;
        }
        if (!errorFlg && bizMsg.K.getValidCount() == 0) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.pkgUomClsCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Unit of Measure Class"});
    		errorFlg = true;
        }
        BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
        if (!errorFlg && radioValueK1 ==null) {
        	//ZZZM9000E=0,[@] field is mandatory.
    		bizMsg.xxRadioBtn_K1.setErrorInfo(1, "ZZM9000E", new String[]{"Prim"});
    		errorFlg = true;
        }
        if (bizMsg.K.getValidCount() != 0) {
            for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.K.no(i).pkgUomCd_K1)) {
                    continue;
                }
                // #################
                // Mandatory Check
                // #################
            	if (!ZYPCommonFunc.hasValue(bizMsg.K.no(i).inEachQty_K1)) {
					//ZZZM9000E=0,[@] field is mandatory.
            		bizMsg.K.no(i).inEachQty_K1.setErrorInfo(1, "ZZM9000E", new String[]{"Qty Inside"});
            		errorFlg = true;
            	}
            }
	    }
        
        // Service Allocation Type
        // 05 : Maintenance, 12 : Maintenance Charge
        // In case of MDSE_TP_VAL_SET.MDSE_TP_CTX_TP_CD = ?
        //if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) 
        //		&& (MDSE_ITEM_TP.MAINTENANCE.equals(bizMsg.mdseItemTpCd_H1.getValue())
        //				|| MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(bizMsg.mdseItemTpCd_H1.getValue()))) {
        //	List<Map<String, Object>> list = NMAL0110CommonLogic.getMdseTpValSetTMsg(getGlobalCompanyCode(), "SVC_ALLOC_TP_REQ_ITEM");
        //	if (list != null && list.size() > 0) {
        //		for (int i = 0; i < list.size(); i++) {
        //			Map<String, Object> map = list.get(i);
        //			if (bizMsg.coaMdseTpCd_H1.getValue().equals((String) map.get("COA_MDSE_TP_CD"))) {
        //				if (!ZYPCommonFunc.hasValue(bizMsg.svcAllocTpCd_H1)) {
        //					//ZZZM9000E=0,[@] field is mandatory.
        //					bizMsg.svcAllocTpCd_H1.setErrorInfo(1, "ZZZM9000E", new String[]{"Service Allocation Type"});
        //            		errorFlg = true;
        //				}
        //			}
        //		}
        //	}
        //}
        
        //QC#9346
    	List<Map<String, Object>> list = NMAL0110CommonLogic.getMdseTpValSetTMsg(getGlobalCompanyCode(), "SVC_ALLOC_TP_REQ_ITEM", bizMsg.mdseItemTpCd_H1.getValue(), bizMsg.coaMdseTpCd_H1.getValue());
    	if (list != null && list.size() > 0) {
			if (!ZYPCommonFunc.hasValue(bizMsg.svcAllocTpCd_H1)) {
				//ZZZM9000E=0,[@] field is mandatory.
				bizMsg.svcAllocTpCd_H1.setErrorInfo(1, "ZZZM9000E", new String[]{"Service Allocation Type"});
        		errorFlg = true;
			}
    	}

        // START 2017/06/22 J.Kim [QC#10580,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) && MDSE_ITEM_TP.MAINTENANCE.equals(bizMsg.mdseItemTpCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(bizMsg.svcCovTmplTpCd_H1) && !ZYPCommonFunc.hasValue(bizMsg.svcAllocTpCd_H1)) {
                // ZZZM9000E=0,[@] field is mandatory.
                bizMsg.svcAllocTpCd_H1.setErrorInfo(1, "ZZZM9000E", new String[] {"Service Allocation Type" });
                errorFlg = true;
            }
        }
        // END 2017/06/22 J.Kim [QC#10580,ADD]

    	if (errorFlg) {
        	return true;
        }
        
        return errorFlg;
    }
    @SuppressWarnings("unchecked")
    private boolean checkInput_TAB_Inventory(NMAL0110CMsg bizMsg, EZDSMsg msg) {
    	
    	boolean errorFlg = false;
    	
        // #################
        // Mandatory Check
        // #################
    	if (checkInput_TAB_Inventory_Mandatory(bizMsg)) {
    		return true;
    	}
    	
        // #################
        // Exists Check
        // #################
        // DEFAULT SOURCE WAREHOUSE check WH exists
        if (ZYPCommonFunc.hasValue(bizMsg.defSrcWhCd_H1)) {
        	
        	//QC#10449
        	//RTL_WHTMsg rtlWhTmsg = NMAL0110CommonLogic.findRTL_WH(getGlobalCompanyCode(), bizMsg.defSrcWhCd_H1.getValue());
        	//if (rtlWhTmsg == null) {
        	//	// NMAM0163E=0,The entered [@] does not exist in [@].
        	//	bizMsg.defSrcWhCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Default Source Warehouse", "Retail WH Master"});
        	//	errorFlg = true;
        	//} else {
        	//	ZYPEZDItemValueSetter.setValue(bizMsg.locNm_DW, rtlWhTmsg.rtlWhNm);
        	//}
        	S21SsmEZDResult rsRtlWh = NMAL0110Query.getInstance().getRtlWh(getGlobalCompanyCode(), bizMsg.defSrcWhCd_H1.getValue(), ZYPDateUtil.getSalesDate());
        	if (rsRtlWh.isCodeNormal()) {
        		Map<String, Object> map = (Map<String, Object>) rsRtlWh.getResultObject();
    			if (map == null || map.get("RTL_WH_CD") == null) {
            		// NMAM0163E=0,The entered [@] does not exist in [@].
            		bizMsg.defSrcWhCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Default Source Warehouse", "Retail WH Master"});
            		errorFlg = true;
    			} else {
            		ZYPEZDItemValueSetter.setValue(bizMsg.locNm_DW, (String) map.get("RTL_WH_NM"));
    			}
        	} else {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.defSrcWhCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Default Source Warehouse", "Retail WH Master"});
        		errorFlg = true;
        	}
        }
        
        // DEFAULT SOURCE SUB WAREHOUSE check WH exists
        if (ZYPCommonFunc.hasValue(bizMsg.defSrcSubWhCd_H1)) {
            //QC#10449
    		S21SsmEZDResult rsRtlSwh = NMAL0110Query.getInstance().getRetailSubWhName(getGlobalCompanyCode(), 
    				bizMsg.defSrcSubWhCd_H1.getValue(), ZYPDateUtil.getSalesDate());
    		if (rsRtlSwh.isCodeNormal()) {
    			Map<String, Object> map = (Map<String, Object>) rsRtlSwh.getResultObject();
    			if (map == null || map.get("RTL_SWH_CD") == null) {
            		// NMAM0163E=0,The entered [@] does not exist in [@].
            		bizMsg.defSrcSubWhCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Default Source Sub Warehouse", "Retail Sub WH Master"});
            		errorFlg = true;
    			} else {
	        		ZYPEZDItemValueSetter.setValue(bizMsg.locNm_SW, (String) map.get("RTL_SWH_NM"));
    			}
    		} else {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.defSrcSubWhCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Default Source Sub Warehouse", "Retail Sub WH Master"});
        		errorFlg = true;
    		}
    		if (!ZYPCommonFunc.hasValue(bizMsg.defSrcWhCd_H1)) {
    			//ZZZM9000E=0,[@] field is mandatory.
    			bizMsg.defSrcWhCd_H1.setErrorInfo(1, "ZZZM9000E", new String[]{"Default Source Warehouse"});
        		errorFlg = true;
    		} else {
    		    //QC#10449
        		S21SsmEZDResult rsRtlWhSwh = NMAL0110Query.getInstance().getRetailSubWh(getGlobalCompanyCode(), 
        				bizMsg.defSrcWhCd_H1.getValue(), bizMsg.defSrcSubWhCd_H1.getValue(), ZYPDateUtil.getSalesDate());
        		if (rsRtlWhSwh.isCodeNormal()) {
	    			Map<String, Object> map = (Map<String, Object>) rsRtlWhSwh.getResultObject();
	    			if (map == null || map.get("RTL_SWH_CD") == null) {
	            		// NMAM0163E=0,The entered [@] does not exist in [@].
	            		bizMsg.defSrcSubWhCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Default Source Warehouse and Sub Warehouse", "Retail Sub WH Master"});
	            		errorFlg = true;
	    			}
        		} else {
            		// NMAM0163E=0,The entered [@] does not exist in [@].
            		bizMsg.defSrcSubWhCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Default Source Warehouse and Sub Warehouse", "Retail Sub WH Master"});
            		errorFlg = true;
    			}
    		}
        }
    	//if INVENTORY TRACKABLE has been changed from ON to OFF or from OFF to ON, Check the transaction data on CPO and PO.
        if (!NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B6.getValue()).equals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IT.getValue()))) {
	        //CPO, PO Check
    		S21SsmEZDResult rs = NMAL0110Query.getInstance().getTransactionDataCPOAndPO(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
            if (rs.isCodeNormal()) {
                Integer count = (Integer) rs.getResultObject();
                if (count > 0) {
                	// NMAM8207E=0,Can not change INVENTORY TRACKABLE because this item has some transactions in CPO, PO.
                    bizMsg.xxChkBox_IT.setErrorInfo(1, "NMAM8207E");
                    errorFlg = true;
                }
            }
        }
        
        //In case of IB TRACKABLE = Y, ON RECEIPT&SHIPMENT is mandatory.
        //if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_IB) && FLG_ON_Y.equals(bizMsg.xxChkBox_IB.getValue())) {
        //	if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_SS)) {
        //        BigDecimal cur = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.xxRadioBtn_SS.getValue());
        //		if ((cur.compareTo(new BigDecimal("1")) != 0)) {
        //    		//NMAM8208E=0,RECEIPT&SHIPMENT is mandatory in case of IB TRACKABLE.
        //    		bizMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM8208E");
        //        	errorFlg = true;
        //		}
        //	} else {
        //		//NMAM8208E=0,RECEIPT&SHIPMENT is mandatory in case of IB TRACKABLE.
        //		bizMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM8208E");
        //    	errorFlg = true;
        //	}
        //}
        
        // If No --> Yes, Check Inventory(LocSts=WH, SS=Good)
        if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_SS) && ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_BK)) {
            BigDecimal pre = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.xxRadioBtn_BK.getValue());
            BigDecimal cur = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.xxRadioBtn_SS.getValue());
            // START 2020/09/04 [QC#57603, MOD]
//            if (pre.compareTo(BigDecimal.ZERO) == 0
//                    && (cur.compareTo(new BigDecimal("1")) == 0
//                    || cur.compareTo(new BigDecimal("2")) == 0)) {
            boolean isSerializeOp = pre.compareTo(BigDecimal.ZERO) == 0
                    && (cur.compareTo(new BigDecimal("1")) == 0
                    || cur.compareTo(new BigDecimal("2")) == 0);
            boolean isDeserializeOp = cur.compareTo(BigDecimal.ZERO) == 0
                    && (pre.compareTo(new BigDecimal("1")) == 0
                    || pre.compareTo(new BigDecimal("2")) == 0);
            if (isSerializeOp || isDeserializeOp) {
            // END 2020/09/04 [QC#57603, MOD]
                // START 2020/09/04 [QC#57588, MOD]
//                S21SsmEZDResult rs = NMAL0110Query.getInstance().getInventoryCountForSerial(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), false);
                S21SsmEZDResult rs = NMAL0110Query.getInstance().getInventoryCountForSerial(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), true);
                // END 2020/09/04 [QC#57588, MOD]
                if (rs.isCodeNormal()) {
                    Integer count = (Integer) rs.getResultObject();
                    if (count > 0) {
                        //NMAM8189E=0,Cannot change Serial Number Control because this item is used on Inventory.
                        bizMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM8189E");
                        errorFlg = true;
                    }
                }
            }
        }

        // If Item Type = Set, Inventory Control Flag = false
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1)
        		&& (MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd_H1.getValue()))
        		&& (FLG_ON_Y.equals(bizMsg.xxChkBox_IT.getValue()))) {
			//ZZZM9000E=0,[@] field is mandatory.
//			bizMsg.xxChkBox_IT.setErrorInfo(1, "ZZZM9000E", new String[]{"When Item Type is Set, Inventory Trackable"});
            bizMsg.xxChkBox_IT.setErrorInfo(1, "NMAM8686E", new String[] {bizMsg.mdseItemTpDescTxt_H1.getValue(), "OFF" });
    		errorFlg = true;
        }

        // QC#25789 If Item Type = Maintenance or Maintenance Charges, Inventory Control Flag = off
        if (NMAL0110CommonLogic.isServiceItem(bizMsg.mdseItemTpCd_H1.getValue()) //
                && S21StringUtil.isEquals(FLG_ON_Y, bizMsg.xxChkBox_IT.getValue())) {
            // If Item Type is @, then Inventory Trackable must be "@".
            bizMsg.xxChkBox_IT.setErrorInfo(1, "NMAM8686E", new String[] {bizMsg.mdseItemTpDescTxt_H1.getValue(), "OFF" });
            errorFlg = true;
        }

//        if (ZYPCommonFunc.hasValue(bizMsg.rtrnToVndCd_H1)) {
//    		S21SsmEZDResult rs = NMAL0110Query.getInstance().getRtrnParntVnd(getGlobalCompanyCode(), bizMsg.rtrnToVndCd_H1.getValue());
//            if (rs.isCodeNormal()) {
//            	String rtrnToPrntVndCd = (String) rs.getResultObject();
//            	if (!ZYPCommonFunc.hasValue(rtrnToPrntVndCd)) {
//            		// NMAM0163E=0,The entered [@] does not exist in [@].
//            		bizMsg.rtrnToVndCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Vendor Parent Code", "PO Vendor View"});
//            		errorFlg = true;
//            	} else {
//	        		ZYPEZDItemValueSetter.setValue(bizMsg.rtrnToPrntVndCd_H1, rtrnToPrntVndCd);
//            	}
//            }
//        }
        
        
        final BigDecimal decimal30 = new BigDecimal("30");
        if (bizMsg.A.getValidCount() != 0) {
	        if (chkDetailListNotEmpty_SERIAL_NO_RG(bizMsg)) {
	            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
	                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) && !ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
	                    continue;
	                }
	                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) || ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
	                    if (bizMsg.A.no(i).fromSerNum_A1.getValue().length() != bizMsg.A.no(i).thruSerNum_A1.getValue().length()) {
	                        // The value you entered is incorrect.
	                    	bizMsg.A.no(i).fromSerNum_A1.setErrorInfo(1, "NMAM0046E", new String[] {"From Length", "To Length" });
	                		errorFlg = true;
	                     }
	                    bizMsg.A.no(i).lgSerNum_A1.setValue(bizMsg.A.no(i).fromSerNum_A1.getValue().length());
	                 }
	                 if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1)) {
	                     if (decimal30.compareTo(new BigDecimal(String.valueOf(bizMsg.A.no(i).fromSerNum_A1.getValue().length()))) < 0) {
	                         bizMsg.A.no(i).fromSerNum_A1.setErrorInfo(1, "NMAM0190E");
	                		 errorFlg = true;
	                     }
	                 }
	             }
            } else {
	            //if (emptyCheck) {
	            //    scrnMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM0011E", new String[] {"Serial Number Range" });
	            //}
	        }
	    } else {
	        //if (emptyCheck) {
	        //    scrnMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM0011E", new String[] {"Serial Number Range" });
	        //}
	    }
        
        //QC4507
        if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_SS)) {
        	int radioVal = bizMsg.xxRadioBtn_SS.getValue().intValue();
        	//On Receipt&Shipment or On Shipment
        	if (radioVal == 1 || radioVal == 2) {
        		if ("N".equals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IB.getValue()))) {
        			//NMAM8379E=0,Install Base Trackable is mandatory when On Receipt&Shipment or On Shipment is ON.
                    bizMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM8379E");
	                errorFlg = true;
        		}
        	}
        }
        
        //QC#9678
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) && 
        		(MDSE_ITEM_TP.SOFTWARE.equals(bizMsg.mdseItemTpCd_H1.getValue()) 
        				|| MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(bizMsg.mdseItemTpCd_H1.getValue()))) {
        	if (ZYPCommonFunc.hasValue(bizMsg.swLicCtrlTpCd_H1)) {
        		SW_LIC_CTRL_TPTMsg swLicCtrlTpTMsg = new SW_LIC_CTRL_TPTMsg();
    	        ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.glblCmpyCd, getGlobalCompanyCode());
    	        ZYPEZDItemValueSetter.setValue(swLicCtrlTpTMsg.swLicCtrlTpCd, bizMsg.swLicCtrlTpCd_H1);
    	        swLicCtrlTpTMsg = (SW_LIC_CTRL_TPTMsg) EZDTBLAccessor.findByKey(swLicCtrlTpTMsg);
    	        String swLicReqFlg = FLG_OFF_N;
    	        if (swLicCtrlTpTMsg != null) {
    	        	swLicReqFlg = swLicCtrlTpTMsg.swLicReqFlg.getValue();
    	        }
    	        if (FLG_ON_Y.equals(swLicReqFlg)) {
    	        	int radioVal = bizMsg.xxRadioBtn_SS.getValue().intValue();
    	        	//Not Controlled
    	        	if (radioVal == 0) {
            			//NMAM8591E=0,On Receipt&Shipment or On Shipment should be On when License Control is Yes.
                        bizMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM8591E");
    	                errorFlg = true;
    	        	}
    	        }
        	}
        }
        
        //
        if (ZYPCommonFunc.hasValue(bizMsg.rtrnCtrlTpCd_H1) || ZYPCommonFunc.hasValue(bizMsg.rtrnToVndCd_H1) || ZYPCommonFunc.hasValue(bizMsg.rtrnWhCd_H1)) {
			Map<String, Object> map = NMAL0110Query.getInstance().getRtrnCtrlTpVndReln(getGlobalCompanyCode(), 
					bizMsg.rtrnCtrlTpCd_H1.getValue(), bizMsg.rtrnToVndCd_H1.getValue(), 
					bizMsg.rtrnWhCd_H1.getValue(), ZYPDateUtil.getSalesDate());  //QC#10449
			if (map == null || map.get("RTRN_CTRL_TP_VND_RELN_PK") == null) {
                bizMsg.rtrnCtrlTpNm_H1.setErrorInfo(1, "NMZM0154E", new String[]{"Return Control Type, Return To Vendor Code, Return Warehouse"});
                bizMsg.locNm_V1.setErrorInfo(1, "NMZM0154E", new String[]{"Return Control Type, Return To Vendor Code, Return Warehouse"});
                bizMsg.locNm_W1.setErrorInfo(1, "NMZM0154E", new String[]{"Return Control Type, Return To Vendor Code, Return Warehouse"});
                errorFlg = true;
			}
        }
        //Check PO_VND_V for rtrnToVndCd_D, rtrnToPrntVndCd_D
        if (ZYPCommonFunc.hasValue(bizMsg.rtrnToVndCd_H1) || ZYPCommonFunc.hasValue(bizMsg.rtrnToPrntVndCd_H1)) {
			Map<String, Object> map = NMAL0110Query.getInstance().getPoVndV(getGlobalCompanyCode(), 
					bizMsg.rtrnToVndCd_H1.getValue(), bizMsg.rtrnToPrntVndCd_H1.getValue());
            if (map == null || map.get("VND_CD") == null) {
                bizMsg.locNm_V1.setErrorInfo(1, "NMZM0154E", new String[]{"Return To Vendor Code, Return To Parent Vendor Code"});
                bizMsg.locNm_P1.setErrorInfo(1, "NMZM0154E", new String[]{"Return To Vendor Code, Return To Parent Vendor Code"});
                errorFlg = true;
            }
        }
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }
    
    public static boolean chkDetailListNotEmpty_SERIAL_NO_RG(NMAL0110CMsg bizMsg) {

        boolean detailListChkFlg = false;
        for (int i = 0, length = bizMsg.A.getValidCount(); i < length; i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) && !ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
                continue;
            }
            detailListChkFlg = true;
        }
        return detailListChkFlg;
    }
    
    private boolean checkInput_TAB_Inventory_Mandatory(NMAL0110CMsg bizMsg) {
    	boolean errorFlg = false;
    	
    	// RETURN CONTROL TYPE
    	// RETURN DISPOSITION
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_RR) && FLG_ON_Y.equals(bizMsg.xxChkBox_RR.getValue())) {
        	if (!ZYPCommonFunc.hasValue(bizMsg.rtrnCtrlTpCd_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.rtrnCtrlTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Return Control Type"});
        		errorFlg = true;
        	}
        	if (!ZYPCommonFunc.hasValue(bizMsg.rtrnDsplTpCd_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.rtrnDsplTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Return Sub Warehouse"});
        		errorFlg = true;
        	}
        }
        // Capture Serial Number upon shipping
        if (!ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_SS)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.xxRadioBtn_SS.setErrorInfo(1, "ZZM9000E", new String[]{"Capture Serial Number upon shipping"});
    		errorFlg = true;
        }
        // In Case of Lic requried on Software, INVENTORY TRACKABLE = Y is mandatory.
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1)
        		&& (MDSE_ITEM_TP.SOFTWARE.equals(bizMsg.mdseItemTpCd_H1.getValue()))) {
        	if (ZYPCommonFunc.hasValue(bizMsg.swLicCtrlTpCd_H1) 
        			&& !SW_LIC_CTRL_TP.NO.equals(bizMsg.swLicCtrlTpCd_H1.getValue())) {  //Not Controlled
        		
        		if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_IT) || !FLG_ON_Y.equals(bizMsg.xxChkBox_IT.getValue())) {
					//ZZZM9000E=0,[@] field is mandatory.
					bizMsg.xxChkBox_IT.setErrorInfo(1, "ZZZM9000E", new String[]{"Inventory Trackable"});
            		errorFlg = true;
        		}
        	}
        }
        
        //QC#9631
        //In case of KIT, should be inventory control flag = Y.
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) && (MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd_H1.getValue()))) {
        	if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_IT) || !FLG_ON_Y.equals(bizMsg.xxChkBox_IT.getValue())) {
				//ZZZM9000E=0,[@] field is mandatory.
				bizMsg.xxChkBox_IT.setErrorInfo(1, "ZZZM9000E", new String[]{"Inventory Trackable"});
        		errorFlg = true;
    		}
        }
        
    	return errorFlg;
    }
    
    private boolean checkInput_TAB_Accounting(NMAL0110CMsg bizMsg, EZDSMsg msg) {

    	boolean errorFlg = false;
    	
        // #################
        // Mandatory Check
        // #################
    	if (checkInput_TAB_Accounting_Mandatory(bizMsg)) {
    		return true;
    	}
    	
        // #################
        // Exists Check
        // #################
        // REVENUE check COA_ACCT exists
        if (ZYPCommonFunc.hasValue(bizMsg.revCoaAcctCd_H1)) {
        	COA_ACCTTMsg coaAcctTmsg = NMAL0110CommonLogic.findCoaAcct(getGlobalCompanyCode(), bizMsg.revCoaAcctCd_H1.getValue());
        	if (coaAcctTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.revCoaAcctCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Revenue", "COA Account Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_RV, coaAcctTmsg.coaAcctNm);
        	}
        }
        // COST OF GOODS check COA_ACCT exists
        if (ZYPCommonFunc.hasValue(bizMsg.cogsCoaAcctCd_H1)) {
        	COA_ACCTTMsg coaAcctTmsg = NMAL0110CommonLogic.findCoaAcct(getGlobalCompanyCode(), bizMsg.cogsCoaAcctCd_H1.getValue());
        	if (coaAcctTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.cogsCoaAcctCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Cost of Goods", "COA Account Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_CG, coaAcctTmsg.coaAcctNm);
        	}
        }
        // EXPENSE check COA_ACCT exists
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaAcctCd_H1)) {
        	COA_ACCTTMsg coaAcctTmsg = NMAL0110CommonLogic.findCoaAcct(getGlobalCompanyCode(), bizMsg.expCoaAcctCd_H1.getValue());
        	if (coaAcctTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.expCoaAcctCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Expense", "COA Account Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctNm_EX, coaAcctTmsg.coaAcctNm);
        	}
        }
        
    	// Accounting Rule - Invoicing Rule
    	// IM:Immidiate–AR:Arrears
    	// DE:Deferred-AD:Advance
        if (ZYPCommonFunc.hasValue(bizMsg.dfrdAcctgRuleCd_H1)) {
        	DFRD_ACCTG_RULETMsg dfrdAcctgRuleTmsg = NMAL0110CommonLogic.findDfrdAcctgRule(getGlobalCompanyCode(), bizMsg.dfrdAcctgRuleCd_H1.getValue());
        	if (dfrdAcctgRuleTmsg != null) {
        		if (FLG_ON_Y.equals(dfrdAcctgRuleTmsg.dfrdRevFlg.getValue())) {
        			//Advance Only
        			if (!DFRD_INV_RULE.ADVANCED.equals(bizMsg.dfrdInvRuleCd_H1.getValue())) {
        				//NMAM8211E=0,@ should be @.
        				bizMsg.dfrdAcctgRuleCd_H1.setErrorInfo(1, "NMAM8211E", new String[]{"Accounting Rules and Invoicing Rules", "Deferred - Advance"});
        				bizMsg.dfrdInvRuleCd_H1.setErrorInfo(1, "NMAM8211E", new String[]{"Accounting Rules and Invoicing Rules", "Deferred - Advance"});
        	    		errorFlg = true;
        			}
        		} else {
        			//Arrears Only
        			if (!DFRD_INV_RULE.ARREARS.equals(bizMsg.dfrdInvRuleCd_H1.getValue())) {
        				//NMAM8211E=0,@ should be @.
        				bizMsg.dfrdAcctgRuleCd_H1.setErrorInfo(1, "NMAM8211E", new String[]{"Accounting Rules and Invoicing Rules", "Immidiate – Arrears"});
        				bizMsg.dfrdInvRuleCd_H1.setErrorInfo(1, "NMAM8211E", new String[]{"Accounting Rules and Invoicing Rules", "Immidiate – Arrears"});
        	    		errorFlg = true;
        			}
        		}
        	}
        }

        // 2019/06/10 QC#50763 Add Start
        // Product Code - Revenue Account Check
        // ZZ:ADMINISTRATION - 4%:Sales
        if (ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1) && ZYPCommonFunc.hasValue(bizMsg.revCoaAcctCd_H1)) {
            String revCoaAcctCd = bizMsg.revCoaAcctCd_H1.getValue();
            if (COA_PROD.ADMINISTRATION.equals(bizMsg.coaProdCd_H1.getValue()) && revCoaAcctCd.startsWith(REV_ACCT_START_WITH_CD)) {
                bizMsg.revCoaAcctCd_H1.setErrorInfo(1, "NMAM0086E", new String[]{"Product Code:ZZ(ADMINISTRATION)", "because entered Sales Account in Revenue."});
                bizMsg.coaProdCd_H1.setErrorInfo(1, "NMAM0086E", new String[]{"Product Code:ZZ(ADMINISTRATION)", "because entered Sales Account in Revenue."});
                errorFlg = true;
            }
        }
        // 2019/06/10 QC#50763 Add End

        if (errorFlg) {
        	return true;
        }
        return false;
    }
    
    private boolean checkInput_TAB_Accounting_Mandatory(NMAL0110CMsg bizMsg) {
    	boolean errorFlg = false;
    	
    	// STANDARD COST
        if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) && !ZYPCommonFunc.hasValue(bizMsg.thisMthTotStdCostAmt_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.thisMthTotStdCostAmt_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Standard Cost"});
    		errorFlg = true;
        } else if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) 
        		&& BigDecimal.ZERO.compareTo(bizMsg.thisMthTotStdCostAmt_H1.getValue()) > 0) {
        	//ZZZM9026E=0,[@] is invalid value
    		bizMsg.thisMthTotStdCostAmt_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"Standard Cost"});
    		errorFlg = true;
    	//QC#10748
        //} else if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) 
        //		&& FLG_ON_Y.equals(bizMsg.xxChkBox_IT.getValue())
        //		&& BigDecimal.ZERO.compareTo(bizMsg.thisMthTotStdCostAmt_H1.getValue()) == 0) {
    	//	//NMAM8180E=0,When @ is @, @ is required.
    	//	bizMsg.thisMthTotStdCostAmt_H1.setErrorInfo(1, "NMAM8180E", new String[]{"Inventory Trackable","On","Standard Cost"});
    	//	errorFlg = true;
        }

    	// ARV COST
        if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) && !ZYPCommonFunc.hasValue(bizMsg.assetRecovCostAmt_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.assetRecovCostAmt_H1.setErrorInfo(1, "ZZM9000E", new String[]{"ARV Cost"});
    		errorFlg = true;
        } else if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) 
        		&& BigDecimal.ZERO.compareTo(bizMsg.assetRecovCostAmt_H1.getValue()) > 0) {
        	//ZZZM9026E=0,[@] is invalid value
    		bizMsg.assetRecovCostAmt_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"ARV Cost"});
    		errorFlg = true;
        }
    	    	
    	// REVENUE
        if (!ZYPCommonFunc.hasValue(bizMsg.revCoaAcctCd_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.revCoaAcctCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Revenue"});
    		errorFlg = true;
        }
    	
    	// COST OF GOODS
        if (!ZYPCommonFunc.hasValue(bizMsg.cogsCoaAcctCd_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.cogsCoaAcctCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Cost of Goods"});
    		errorFlg = true;
        }
    	
    	// EXPENSE
        if (!ZYPCommonFunc.hasValue(bizMsg.expCoaAcctCd_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.expCoaAcctCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Expense"});
    		errorFlg = true;
        }
    	
    	// LINE OF BUSINESS
        if (!ZYPCommonFunc.hasValue(bizMsg.lineBizTpCd_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.lineBizTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Line of Business"});
    		errorFlg = true;
        }
        
    	// ACCOUNTING RULES
        if (!ZYPCommonFunc.hasValue(bizMsg.dfrdAcctgRuleCd_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.dfrdAcctgRuleCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Accounting Rules"});
    		errorFlg = true;
        }
    	
    	// INVOICING RULES
        if (!ZYPCommonFunc.hasValue(bizMsg.dfrdInvRuleCd_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.dfrdInvRuleCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Invoicing Rules"});
    		errorFlg = true;
        }
        
    	return errorFlg;
    }
    
    private boolean checkInput_TAB_Field_Service(NMAL0110CMsg bizMsg, EZDSMsg msg) {
    	
    	boolean errorFlg = false;
    	
        // #################
        // Mandatory Check
        // #################
    	if (checkInput_TAB_Field_Service_Mandatory(bizMsg)) {
    		return true;
    	}
        
        // #################
        // Exists Check
        // #################        
    	//On --> Off, Check Machine Master
    	// 2019/10/15 QC#53602 Mod Start
        //if (FLG_ON_Y.equals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_BB.getValue())) 
    	if (FLG_ON_Y.equals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R4.getValue())) 
        		&& FLG_OFF_N.equals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IB.getValue()))) {
	    // 2019/10/15 QC#53602 Mod End
    	    //Master Check
    		S21SsmEZDResult rs = NMAL0110Query.getInstance().getMachineMaster(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
            if (rs.isCodeNormal()) {
                Integer count = (Integer) rs.getResultObject();
                if (count > 0) {
                	// NMAM8209E=0,Can not change INSTALL BASE TRACKABLE because this item has some transactions in Machine Master.
                    bizMsg.xxChkBox_IB.setErrorInfo(1, "NMAM8209E");
                    errorFlg = true;
                }
            }

        }
    	
        // IMAGEWARE REMOTE ITEM check MDSE exists
        if (ZYPCommonFunc.hasValue(bizMsg.iwrMdseCd_H1)) {
        	MDSETMsg dsMdseInfoTmsg = NMAL0110CommonLogic.findDsMdseInfo(getGlobalCompanyCode(), bizMsg.iwrMdseCd_H1.getValue());
        	if (dsMdseInfoTmsg == null) {
        		// NMAM0163E=0,The entered [@] does not exist in [@].
        		bizMsg.iwrMdseCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Imageware Remote Item", "Merchandise Master"});
        		errorFlg = true;
        	} else {
        		ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_IW, dsMdseInfoTmsg.mdseDescShortTxt);
        	}
        }
        
        //QC4507
        if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_SS)) {
        	int radioVal = bizMsg.xxRadioBtn_SS.getValue().intValue();
        	//On Receipt&Shipment or On Shipment
        	if (radioVal == 1 || radioVal == 2) {
        		if ("N".equals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IB.getValue()))) {
        			//NMAM8379E=0,Install Base Trackable is mandatory when On Receipt&Shipment or On Shipment is ON.
                    bizMsg.xxChkBox_IB.setErrorInfo(1, "NMAM8379E");
	                errorFlg = true;
        		}
        	}
        }
        
        if (errorFlg) {
        	return true;
        }
        return false;
    }
    
    private boolean checkInput_TAB_Field_Service_Mandatory(NMAL0110CMsg bizMsg) {
    	boolean errorFlg = false;
    	
//    	// WARRANTY
//        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) 
//        		&& (MDSE_ITEM_TP.MACHINE.equals(bizMsg.mdseItemTpCd_H1.getValue()) 
//        		|| MDSE_ITEM_TP.ACCESSORY.equals(bizMsg.mdseItemTpCd_H1.getValue()))) {
//        	if (!ZYPCommonFunc.hasValue(bizMsg.svcWtyTpCd_H1)) {
//            	//ZZM9000E=0,[@] field is mandatory.
//        		bizMsg.svcWtyTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Warranty"});
//        		errorFlg = true;
//        	}
//        }
        
        // WARRANTY PERIOD( days )
        if (ZYPCommonFunc.hasValue(bizMsg.svcWtyTpCd_H1) && !ZYPCommonFunc.hasValue(bizMsg.wtyDaysAot_H1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.wtyDaysAot_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Warranty Period( days )"});
    		errorFlg = true;
        }

        // If Service Call Enabled = Y, Install Base Trackable should be Y.
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_SC) && FLG_ON_Y.equals(bizMsg.xxChkBox_SC.getValue())
        		&& (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_IB) || FLG_OFF_N.equals(bizMsg.xxChkBox_IB.getValue())) ) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.xxChkBox_SC.setErrorInfo(1, "ZZM9000E", new String[]{"If Service Call Enabled is checked, Install Base Trackable"});
    		bizMsg.xxChkBox_IB.setErrorInfo(1, "ZZM9000E", new String[]{"If Service Call Enabled is checked, Install Base Trackable"});
    		errorFlg = true;
        }

        //If Warranty is set, Install Base Trackable is mandatory.
        if (ZYPCommonFunc.hasValue(bizMsg.svcWtyTpCd_H1)) {
        	if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_IB) || FLG_OFF_N.equals(bizMsg.xxChkBox_IB.getValue())) {
        		bizMsg.svcWtyTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"If Warranty is selected, Install Base Trackable"});
        		bizMsg.xxChkBox_IB.setErrorInfo(1, "ZZM9000E", new String[]{"If Warranty is selected, Install Base Trackable"});
        		errorFlg = true;
        	}
        }
        
        // IMAGEWARE REMOTE MODEL
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_IR) && FLG_ON_Y.equals(bizMsg.xxChkBox_IR.getValue())) {
        	if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_IB) || FLG_OFF_N.equals(bizMsg.xxChkBox_IB.getValue())) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.xxChkBox_IR.setErrorInfo(1, "ZZM9000E", new String[]{"If Imageware Remote Enabled is checked, Install Base Trackable"});
        		bizMsg.xxChkBox_IB.setErrorInfo(1, "ZZM9000E", new String[]{"If Imageware Remote Enabled is checked, Install Base Trackable"});
        		errorFlg = true;
        	}
        	if (!ZYPCommonFunc.hasValue(bizMsg.iwrMdlCd_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.iwrMdlCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Imageware Remote Model"});
        		errorFlg = true;
        	}
        }
    	return errorFlg;
    }
    
    private boolean checkInput_TAB_Order_Processing(NMAL0110CMsg bizMsg, EZDSMsg msg) {

    	boolean errorFlg = false;

        // #################
        // Mandatory Check
        // #################
    	if (checkInput_TAB_Order_Processing_Mandatory(bizMsg)) {
        	errorFlg = true;
    	}
    	
    	if (errorFlg) {
        	return true;
        }

        return false;
    }
    
    private boolean checkInput_TAB_Order_Processing_Mandatory(NMAL0110CMsg bizMsg) {
    	boolean errorFlg = false;
    	
    	// MINIMUM ORDER QUANITTY
    	// MAXIMUM ORDER QUANITTY
    	// ORDER INCREMENTS
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_CO) && FLG_ON_Y.equals(bizMsg.xxChkBox_CO.getValue())) {
        	if (!ZYPCommonFunc.hasValue(bizMsg.cpoMinOrdQty_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.cpoMinOrdQty_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Minimum Order Quantity"});
        		errorFlg = true;
        	}
        	if (!ZYPCommonFunc.hasValue(bizMsg.cpoMaxOrdQty_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.cpoMaxOrdQty_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Maximum Order Quantity"});
        		errorFlg = true;
        	}
        	if (!ZYPCommonFunc.hasValue(bizMsg.cpoIncrOrdQty_H1)) {
            	//ZZM9000E=0,[@] field is mandatory.
        		bizMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Order Increments"});
        		errorFlg = true;
        	}
        }
        if (ZYPCommonFunc.hasValue(bizMsg.cpoMinOrdQty_H1) && BigDecimal.ZERO.compareTo(bizMsg.cpoMinOrdQty_H1.getValue()) > 0) {
        	//ZZZM9026E=0,[@] is invalid value
        	bizMsg.cpoMinOrdQty_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"Minimum Order Quantity"});
        	errorFlg = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.cpoMaxOrdQty_H1) && BigDecimal.ZERO.compareTo(bizMsg.cpoMaxOrdQty_H1.getValue()) > 0) {
        	//ZZZM9026E=0,[@] is invalid value
        	bizMsg.cpoMaxOrdQty_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"Maximum Order Quantity"});
    		errorFlg = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.cpoIncrOrdQty_H1) && BigDecimal.ZERO.compareTo(bizMsg.cpoIncrOrdQty_H1.getValue()) > 0) {
        	//ZZZM9026E=0,[@] is invalid value
        	bizMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"Order Increments"});
    		errorFlg = true;
        }
        if (!errorFlg) {
	        if (ZYPCommonFunc.hasValue(bizMsg.cpoMinOrdQty_H1) 
	        		&& ZYPCommonFunc.hasValue(bizMsg.cpoMaxOrdQty_H1)) {
	            if (bizMsg.cpoMinOrdQty_H1.getValue().compareTo(bizMsg.cpoMaxOrdQty_H1.getValue()) > 0) {
	            	//NMAM0042E=0,The value for [@] must be over [@].  
	            	bizMsg.cpoMaxOrdQty_H1.setErrorInfo(1, "NMAM0042E", new String[] {"Maximum Order Quantity", "Minimum Order Quantity" });
	        		errorFlg = true;
	            }
	        }
	        if (ZYPCommonFunc.hasValue(bizMsg.cpoMinOrdQty_H1) 
	        		&& ZYPCommonFunc.hasValue(bizMsg.cpoMaxOrdQty_H1) 
	        		&& ZYPCommonFunc.hasValue(bizMsg.cpoIncrOrdQty_H1)) {
	            if (bizMsg.cpoIncrOrdQty_H1.getValue().compareTo(bizMsg.cpoMaxOrdQty_H1.getValue().subtract(bizMsg.cpoMinOrdQty_H1.getValue())) > 0) {
	            	//NMAM0045E=0,The value for [@] must be below [@].
	            	bizMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "NMAM0045E", new String[] {"Order Increments", "(Maximum Order Quantity - Minimum Order Quantity)" });
	        		errorFlg = true;
	            }
	        }
	        if (ZYPCommonFunc.hasValue(bizMsg.cpoMaxOrdQty_H1) 
	        		&& ZYPCommonFunc.hasValue(bizMsg.cpoIncrOrdQty_H1)) {
	            if (bizMsg.cpoIncrOrdQty_H1.getValue().compareTo(bizMsg.cpoMaxOrdQty_H1.getValue()) > 0) {
	            	//NMAM0045E=0,The value for [@] must be below [@].
	            	bizMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "NMAM0045E", new String[] {"Order Increments", "Maximum Order Quantity" });
	        		errorFlg = true;
	            }
	        }
        }
        return errorFlg;
    }

    private boolean checkInput_TAB_Supd(NMAL0110CMsg bizMsg, EZDSMsg msg) {

    	boolean errorFlg = false;
    	
        if (bizMsg.C.getValidCount() != 0) {
        	
        	if (bizMsg.C.getValidCount() > 1) {
        		for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            		//NWAM0748E=0,Superseeded By can be set only one.
            		bizMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, "NWAM0748E");
        		}
            	errorFlg = true;
        	}
        	//QC#5580
        	if (errorFlg) {
            	return true;
            }
        	
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.C.no(i).mdseDescShortTxt_C1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdCratDt_C1)) {
                    continue;
                }
                // #################
                // Mandatory Check
                // #################
                if (checkInput_TAB_Supd_Mandatory(bizMsg, i)) {
                	errorFlg = true;
                }
                // #################
                // Exists Check
                // #################
            	// SUPERSEEDED BY check MDSE exists
                if (ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1)) {
                	MDSETMsg mdseTmsg = NMAL0110CommonLogic.findMdse(getGlobalCompanyCode(), bizMsg.C.no(i).supdToMdseCd_C1.getValue());
                	if (mdseTmsg == null) {
                		// NMAM0163E=0,The entered [@] does not exist in [@].
                		bizMsg.C.no(i).supdToMdseCd_C1.setErrorInfo(1, "NMAM0163E", new String[]{"Superseeded By", "Merchandise Master"});
                		errorFlg = true;
                	} else {
                		ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseDescShortTxt_C1, mdseTmsg.mdseNm);
                	}
                }

                //QC#16609
                //If Other From Mdse Code exists
                //if (ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1)) {
            	//	S21SsmEZDResult rsSupd = NMAL0110Query.getInstance().getSupdRelnFrom(bizMsg, getGlobalCompanyCode(), bizMsg.C.no(i).supdToMdseCd_C1.getValue());
            	//	if (rsSupd.isCodeNormal()) {
            	//		List<Map<String, String>> list = (List<Map<String, String>>) rsSupd.getResultObject();
            	//		if (list != null && list.size() > 0) {
            	//			for (int j = 0; j < list.size(); j++) {
            	//				Map<String, String> map = (Map<String, String>) list.get(j);
            	//				if (map != null && map.get("SUPD_FROM_MDSE_CD") != null && !bizMsg.mdseCd_H1.getValue().equals((String) map.get("SUPD_FROM_MDSE_CD"))) {
                //                    //NWAM0633E=0,[@] cannot be added because it has already been registered in Supersede From.
                //            		bizMsg.C.no(i).supdToMdseCd_C1.setErrorInfo(1, "NWAM0633E", new String[]{"Superseeded By"});
                //            		errorFlg = true;
                //            		break;
            	//				}
            	//			}
            	//		}
            	//	}
                //}
                
                //When this item code is possible 8 digits merchandise, then supd mdse code of 8 digits is same with this item of 8 digits.
                if (mdse8digitPossibleFlg) {
                	String supd8DigitMdseCd = "";
                	if (ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1) && bizMsg.C.no(i).supdToMdseCd_C1.getValue().length() > 8) {
                		supd8DigitMdseCd = bizMsg.C.no(i).supdToMdseCd_C1.getValue().substring(0, 8);
                	}
                	String mdse8DigitMdseCd = "";
                	if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1) && bizMsg.mdseCd_H1.getValue().length() > 8) {
                		mdse8DigitMdseCd = bizMsg.mdseCd_H1.getValue().substring(0, 8);
                	}
                	if (!"".equals(supd8DigitMdseCd) && supd8DigitMdseCd.equals(mdse8DigitMdseCd)) {
                		//NMAM8365E=0,You can't register same 8 digit code between Item Number and Superseded by.
                		bizMsg.C.no(i).supdToMdseCd_C1.setErrorInfo(1, "NMAM8365E");
                		errorFlg = true;
                	}
                }
                if (bizMsg.mdseCd_H1.getValue().equals(bizMsg.C.no(i).supdToMdseCd_C1.getValue())) {
            		//NMAM8366E=0,You can't register same code between Item Number and Superseded by.
            		bizMsg.C.no(i).supdToMdseCd_C1.setErrorInfo(1, "NMAM8366E");
            		errorFlg = true;
                }
            }
	    }
    	if (errorFlg) {
        	return true;
        }
        if (bizMsg.D.getValidCount() != 0) {
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseDescShortTxt_D1)) {
                    continue;
                }
                // #################
                // Mandatory Check
                // #################
                if (checkInput_TAB_Mdse_Reln_Mandatory(bizMsg, i)) {
                	errorFlg = true;
                }
                
                // #################
                // Exists Check
                // #################
            	// RELATED ITEM check MDSE exists
                if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1)) {
                	MDSETMsg mdseTmsg = NMAL0110CommonLogic.findMdse(getGlobalCompanyCode(), bizMsg.D.no(i).relnMdseCd_D1.getValue());
                	if (mdseTmsg == null) {
                		// NMAM0163E=0,The entered [@] does not exist in [@].
                		bizMsg.D.no(i).relnMdseCd_D1.setErrorInfo(1, "NMAM0163E", new String[]{"Related Item", "Merchandise Master"});
                		errorFlg = true;
                	} else {
                		ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).mdseDescShortTxt_D1, mdseTmsg.mdseNm);
                	}
                }
            }
	    }
    	if (errorFlg) {
        	return true;
        }
    	
        return false;
    }
    
    private boolean checkInput_TAB_Supd_Mandatory(NMAL0110CMsg bizMsg, int i) {
    	boolean errorFlg = false;
    	
    	if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.C.no(i).supdToMdseCd_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Superseeded By"});
    		errorFlg = true;
    	}
    	if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdCratDt_C1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.C.no(i).supdCratDt_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Date"});
    		errorFlg = true;
    	}
    	
    	return errorFlg;
    }
    
    private boolean checkInput_TAB_Mdse_Reln_Mandatory(NMAL0110CMsg bizMsg, int i) {
    	boolean errorFlg = false;
    	
    	if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.D.no(i).mdseItemRelnTpCd_D1.setErrorInfo(1, "ZZM9000E", new String[]{"Relationship"});
    		errorFlg = true;
    	}
    	if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.D.no(i).relnMdseCd_D1.setErrorInfo(1, "ZZM9000E", new String[]{"Related Item"});
    		errorFlg = true;
    	}
    	
    	return errorFlg;
    }
    @SuppressWarnings("unchecked")
    private boolean checkInput_TAB_Cust_Ref(NMAL0110CMsg bizMsg, EZDSMsg msg) {

    	boolean errorFlg = false;
    	
        if (bizMsg.E.getValidCount() != 0) {
            for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNm_E1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseCd_E1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseNm_E1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).effFromDt_E1) 
                		&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).xxChkBox_EN)) {
                    continue;
                }
                // #################
                // Mandatory Check
                // #################
                if (checkInput_TAB_Cust_Ref_Mandatory(bizMsg, i)) {
                	errorFlg = true;
                }
                
                // #################
                // Exists Check
                // #################
            	// CUSTOMER NUMBER check SELL_TO_CUST exists
                if (ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1)) {
            		S21SsmEZDResult rsDsAcct = NMAL0110Query.getInstance().getDsAcctCust(getGlobalCompanyCode(), bizMsg.E.no(i).dsAcctNum_E1.getValue());
            		if (rsDsAcct.isCodeNormal()) {
            			List<Map<String, String>> list = (List<Map<String, String>>) rsDsAcct.getResultObject();
            			if (list == null || list.size() <= 0) {
                    		// NMAM0163E=0,The entered [@] does not exist in [@].
                    		bizMsg.E.no(i).dsAcctNum_E1.setErrorInfo(1, "NMAM0163E", new String[]{"Customer Number", "Account Master"});
                    		errorFlg = true;
            			} else {
            				Map<String, String> map = (Map<String, String>) list.get(0);
            				if (map != null && map.get("DS_ACCT_NM") != null) {
                        		ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).dsAcctNm_E1, (String) map.get("DS_ACCT_NM"));
            				}
            			}
            		}
                }
            }
	    }
    	if (errorFlg) {
        	return true;
        }
    	
        return false;
    }
    
    private boolean checkInput_TAB_Cust_Ref_Mandatory(NMAL0110CMsg bizMsg, int i) {
    	boolean errorFlg = false;
    	
    	if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.E.no(i).dsAcctNum_E1.setErrorInfo(1, "ZZM9000E", new String[]{"Customer Number"});
    		errorFlg = true;
    	}
    	if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseCd_E1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.E.no(i).custMdseCd_E1.setErrorInfo(1, "ZZM9000E", new String[]{"Customer Item"});
    		errorFlg = true;
    	}
    	if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).effFromDt_E1)) {
        	//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.E.no(i).effFromDt_E1.setErrorInfo(1, "ZZM9000E", new String[]{"Date"});
    		errorFlg = true;
    	}
    	return errorFlg;
    }
    @SuppressWarnings("unchecked")
    private boolean checkInput_Final(NMAL0110CMsg bizMsg, EZDSMsg msg, MDSE_ITEM_STSTMsg mdseItemStsTMsg) {
    
    	//QC#9823, QC#10129
    	//If Set and Kit, they don't have any component, should be Pre Launch.
    	if (!MDSE_ITEM_STS.PRELAUNCH.equals(mdseItemStsTMsg.mdseItemStsCd.getValue())
    			&& (MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd_H1.getValue())
    			|| MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd_H1.getValue()))
    			&& !NMAL0110CommonLogic.existsCmpsn(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue())) {
    		// NMAM8614E=0,When Merchandise Type is Set or Kit without any components, The Item Status should be PreLaunch.
    		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8614E");
    		return true;
    	}
    	
    	//QC#12578
    	//In case of Inactive
    	if (ZYPCommonFunc.hasValue(bizMsg.mdseItemStsCd_BK) 
    			&& !bizMsg.mdseItemStsCd_H1.getValue().equals(bizMsg.mdseItemStsCd_BK.getValue())
    		    && RGTN_STS.TERMINATED.equals(mdseItemStsTMsg.rgtnStsCd.getValue())) {
    		
    		S21SsmEZDResult rs = NMAL0110Query.getInstance().getInventoryCountForSerial(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), true);
            if (rs.isCodeNormal()) {
                Integer count = (Integer) rs.getResultObject();
                if (count > 0) {
                	//NMAM8639E=0,Status cannot change to Inactive because this item is used on @.
            		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8639E", new String[]{"Inventory"});
            		return true;
                }
            }
            
    		S21SsmEZDResult rsMm = NMAL0110Query.getInstance().getMachineMaster(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
            if (rsMm.isCodeNormal()) {
                Integer count = (Integer) rsMm.getResultObject();
                if (count > 0) {
                	//NMAM8639E=0,Status cannot change to Inactive because this item is used on @.
                    bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8639E", new String[]{"IB"});
                    return true;
                }
            }
    	}
    	
    	
    	//change to tab Mandatory Flag
    	if (FLG_ON_Y.equals(mdseItemStsTMsg.genlTabMndFlg.getValue())) {
        	boolean mandatoryNoImputFlg = checkInput_TAB_General_Mandatory(bizMsg);
        	if (mandatoryNoImputFlg) {
        		// NMAM8365E=0,When Status is {@}, {@} Tab Mandatory Fields Entry is necessary.
        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8365E", new String[]{mdseItemStsTMsg.mdseItemStsDescTxt.getValue(), TAB_GENERAL});
        		return true;
        	}
    	}
    	if (FLG_ON_Y.equals(mdseItemStsTMsg.invtyTabMndFlg.getValue())) {
        	boolean mandatoryNoImputFlg = checkInput_TAB_Inventory_Mandatory(bizMsg);
        	if (mandatoryNoImputFlg) {
        		// NMAM8365E=0,When Status is {@}, {@} Tab Mandatory Fields Entry is necessary.
        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8365E", new String[]{mdseItemStsTMsg.mdseItemStsDescTxt.getValue(), TAB_INVENTORY});
        		return true;
        	}
    	}
    	if (FLG_ON_Y.equals(mdseItemStsTMsg.acctTabMndFlg.getValue())) {
        	boolean mandatoryNoImputFlg = checkInput_TAB_Accounting_Mandatory(bizMsg);
        	if (mandatoryNoImputFlg) {
        		// NMAM8365E=0,When Status is {@}, {@} Tab Mandatory Fields Entry is necessary.
        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8365E", new String[]{mdseItemStsTMsg.mdseItemStsDescTxt.getValue(), TAB_ACCOUNTING});
        		return true;
        	}
    	}
    	if (FLG_ON_Y.equals(mdseItemStsTMsg.svcTabMndFlg.getValue())) {
        	boolean mandatoryNoImputFlg = checkInput_TAB_Field_Service_Mandatory(bizMsg);
        	if (mandatoryNoImputFlg) {
        		// NMAM8365E=0,When Status is {@}, {@} Tab Mandatory Fields Entry is necessary.
        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8365E", new String[]{mdseItemStsTMsg.mdseItemStsDescTxt.getValue(), TAB_FIELD_SERVICE});
        		return true;
        	}
    	}
    	if (FLG_ON_Y.equals(mdseItemStsTMsg.ordTabMndFlg.getValue())) {
        	boolean mandatoryNoImputFlg = checkInput_TAB_Order_Processing_Mandatory(bizMsg);
        	if (mandatoryNoImputFlg) {
        		// NMAM8365E=0,When Status is {@}, {@} Tab Mandatory Fields Entry is necessary.
        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8365E", new String[]{mdseItemStsTMsg.mdseItemStsDescTxt.getValue(), TAB_ORDER_PROCESSING});
        		return true;
        	}
    	}
    	
    	
//        // Status mdseItemStsCd_H1
//        if (MDSE_ITEM_STS.PRELAUNCH.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
//        	boolean mandatoryNoImputFlg = checkInput_TAB_Field_Service_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"PreLaunch", "Field Service"});
//        		return true;
//        	}
//        	
//        } else if (MDSE_ITEM_STS.PRELAUNCH_APPROVED.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
//        	boolean mandatoryNoImputFlg = checkInput_TAB_Inventory_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"PreLaunch Approved", "Inventory"});
//        		return true;
//        	}
//        	
//        	mandatoryNoImputFlg = checkInput_TAB_Field_Service_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"PreLaunch Approved", "Field Service"});
//        		return true;
//        	}
//        	
//        	mandatoryNoImputFlg = checkInput_TAB_Accounting_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"PreLaunch Approved", "Accounting"});
//        		return true;
//        	}
//        	
//        	
//        } else if (MDSE_ITEM_STS.CURRENT.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
//        	boolean mandatoryNoImputFlg = false;
//        	mandatoryNoImputFlg = checkInput_TAB_Inventory_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current", "Inventory"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Accounting_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current", "Accounting"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Field_Service_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current", "Field Service"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Order_Processing_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current", "Order Processing"});
//        		return true;
//        	}
//        	
//        } else if (MDSE_ITEM_STS.CURRENT_AT_RISK.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
//        	boolean mandatoryNoImputFlg = false;
//        	mandatoryNoImputFlg = checkInput_TAB_Inventory_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current Risk", "Inventory"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Accounting_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current Risk", "Accounting"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Field_Service_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current Risk", "Field Service"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Order_Processing_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Current Risk", "Order Processing"});
//        		return true;
//        	}
//        } else if (MDSE_ITEM_STS.OBSOLETE.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
//        	boolean mandatoryNoImputFlg = false;
//        	mandatoryNoImputFlg = checkInput_TAB_Inventory_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Obsolete", "Inventory"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Field_Service_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Obsolete", "Field Service"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Order_Processing_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"Obsolete", "Order Processing"});
//        		return true;
//        	}
//        } else if (MDSE_ITEM_STS.DESUPPORTED.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
//        	boolean mandatoryNoImputFlg = false;
//        	mandatoryNoImputFlg = checkInput_TAB_Inventory_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"DeSupported", "Inventory"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Field_Service_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"DeSupported", "Field Service"});
//        		return true;
//        	}
//        	mandatoryNoImputFlg = checkInput_TAB_Order_Processing_Mandatory(bizMsg);
//        	if (mandatoryNoImputFlg) {
//        		// NMAM8171E=0,You can not change the Status to @ because there are no entry of mandatory fields in @.
//        		bizMsg.mdseItemStsCd_H1.setErrorInfo(1, "NMAM8171E", new String[]{"DeSupported", "Order Processing"});
//        		return true;
//        	}
//        } else if (MDSE_ITEM_STS.INACTIVE.equals(bizMsg.mdseItemStsCd_H1.getValue())) {
//        	// NONE
//        }
        
        // MANUFACTURER ITEM # check MDSE exists Warning
        if (ZYPCommonFunc.hasValue(bizMsg.mnfItemCd_H1) && !ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H1) && !ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H2)) {
    		S21SsmEZDResult rs = NMAL0110Query.getInstance().getMnfItemCd(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), bizMsg.mnfItemCd_H1.getValue());
    		if (rs.isCodeNormal()) {
    			List<Map<String, String>> list = (List<Map<String, String>>) rs.getResultObject();
    			if (list != null && list.size() > 0) {
            		//NMAM8176W=0, Item # @ already exists with for this Manufacturers Item number.  Please validate before continuing. Save : Submit Button again.
            		bizMsg.setMessageInfo("NMAM8176W", new String[]{bizMsg.mnfItemCd_H1.getValue()});
            		bizMsg.xxRsltFlg_H1.setValue(FLG_ON_Y);
            		return true;
    			}
    		}
        } else {
        	bizMsg.xxRsltFlg_H1.clear();
        }

        // 2019/10/24 QC#51967 Add Start
        // REVENUE/COST OF GOODS check COA_ACCT / Merchandise Type Relation
        if (ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1)) {
            // 2019/10/31 QC#51967 Modd Start
            // REVENUE Check
            if (ZYPCommonFunc.hasValue(bizMsg.revCoaAcctCd_H1) && !ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H4)) {
                if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) || !bizMsg.revCoaAcctCd_BK.getValue().equals(bizMsg.revCoaAcctCd_H1.getValue()) || !bizMsg.coaMdseTpCd_BK.getValue().endsWith(bizMsg.coaMdseTpCd_H1.getValue())) {
                    NMAL0110CommonLogic.chkRevenueAcct(getGlobalCompanyCode(), bizMsg);
                }
            } else {
                bizMsg.xxRsltFlg_H4.clear();
            }

            // COST OF GOODS Check
            if (ZYPCommonFunc.hasValue(bizMsg.cogsCoaAcctCd_H1) && !ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H5)) {
                if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) || !bizMsg.cogsCoaAcctCd_BK.getValue().equals(bizMsg.cogsCoaAcctCd_H1.getValue()) || !bizMsg.coaMdseTpCd_BK.getValue().endsWith(bizMsg.coaMdseTpCd_H1.getValue())) {
                    NMAL0110CommonLogic.chkCosAcct(getGlobalCompanyCode(), bizMsg);
                }
            } else {
                bizMsg.xxRsltFlg_H5.clear();
            }

            if (ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H4) || ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H5)) {
                bizMsg.setMessageInfo("NMAM8704W");
                bizMsg.xxDplyTab_H1.setValue(TAB_ACCOUNTING);
                return true;
            }

//            COA_MDSE_TP_ACCT_RELNTMsg coaMdseTpAcctCdRelTmsg = NMAL0110CommonLogic.findCoaMdseTpAcctReln(getGlobalCompanyCode(), bizMsg.coaMdseTpCd_H1.getValue());
//
//            if (coaMdseTpAcctCdRelTmsg != null) {
//                // REVENUE Check
//                if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) || !bizMsg.revCoaAcctCd_BK.getValue().equals(bizMsg.revCoaAcctCd_H1.getValue()) || !bizMsg.coaMdseTpCd_BK.getValue().endsWith(bizMsg.coaMdseTpCd_H1.getValue())) {
//                    if (!ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H4) && ZYPCommonFunc.hasValue(bizMsg.revCoaAcctCd_H1) && ZYPCommonFunc.hasValue(coaMdseTpAcctCdRelTmsg.revCoaAcctCd) && !bizMsg.revCoaAcctCd_H1.getValue().equals(coaMdseTpAcctCdRelTmsg.revCoaAcctCd.getValue())) {
//                        bizMsg.revCoaAcctCd_H1.setErrorInfo(2, "NMAM8705W");
//                        bizMsg.xxRsltFlg_H4.setValue(FLG_ON_Y);
//                    } else {
//                        bizMsg.xxRsltFlg_H4.clear();
//                    }
//                }
//                // COST OF GOODS Check
//                if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue()) || !bizMsg.cogsCoaAcctCd_BK.getValue().equals(bizMsg.cogsCoaAcctCd_H1.getValue()) || !bizMsg.coaMdseTpCd_BK.getValue().endsWith(bizMsg.coaMdseTpCd_H1.getValue())) {
//                    if (!ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H5) && ZYPCommonFunc.hasValue(bizMsg.cogsCoaAcctCd_H1) && ZYPCommonFunc.hasValue(coaMdseTpAcctCdRelTmsg.cogsCoaAcctCd) && !bizMsg.cogsCoaAcctCd_H1.getValue().equals(coaMdseTpAcctCdRelTmsg.cogsCoaAcctCd.getValue())) {
//                        bizMsg.cogsCoaAcctCd_H1.setErrorInfo(2, "NMAM8706W");
//                        bizMsg.xxRsltFlg_H5.setValue(FLG_ON_Y);
//                    } else {
//                        bizMsg.xxRsltFlg_H5.clear();
//                    }
//                }
//
//                if (ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H4) || ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_H5)) {
//                    bizMsg.setMessageInfo("NMAM8704W");
//            		bizMsg.xxDplyTab_H1.setValue(TAB_ACCOUNTING);
//                    return true;
//                }
//            }
            // 2019/10/31 QC#51967 Mod End
        }
        // 2019/10/24 QC#51967 Add End
        
        return false;
    }
    
    private boolean judgeMdse8DigitPossible(NMAL0110CMsg bizMsg) {
    	
    	//QC#11172
    	if (!ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1) || bizMsg.mdseCd_H1.getValue().length() < 8) {
    		return false;
    	}
    	
    	//QC#16671
        if (MODE_NEW.equals(bizMsg.xxModeCd_H1.getValue())) {
        	// Condition
        	//   MDSE_RGTN_TP_CD = 1:Mercury
        	//   AMER_MSTR.Class Type=A
        	//   Customer Order Allowed=Y
            if (MDSE_RGTN_TP.MERCURY.equals(bizMsg.mdseRgtnTpCd_H1.getValue())) {
    	        AMER_MSTRTMsgArray resultAmerMstr = NMAL0110CommonLogic.chkAMERMSTRTMsg(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
    	        if (resultAmerMstr == null || resultAmerMstr.length() <= 0) {
            		// NMAM0163E=0,The entered [@] does not exist in [@].
            		bizMsg.mdseCd_H1.setErrorInfo(1, "NMAM0163E", new String[]{"Item Number", "Americas Merchandise Master"});
            		return false;
    	        }
    	        AMER_MSTRTMsg amerMstrTmsg = resultAmerMstr.no(0);
    	        if ("A".equals(amerMstrTmsg.amerMdseClsTpCd.getValue())) {
//    	        if ("A".equals(amerMstrTmsg.amerMdseClsTpCd.getValue())
//    	        		&& FLG_ON_Y.equals(mdseItemStsTMsg.custOrdEntryAvalFlg.getValue())) {
    	        	return true;
    	        }
            }
        } else {
        	ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        	ordTakeMdseTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        	ordTakeMdseTMsg.ordTakeMdseCd.setValue(bizMsg.mdseCd_H1.getValue().substring(0, 8));
        	ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ordTakeMdseTMsg);
        	if (ordTakeMdseTMsg != null) {
        		return true;
        	}
        }
		return false;
    }
    
    private boolean changeFieldsCheck(NMAL0110CMsg bizMsg) {
    	
    	if (
    	//Header Left
    		isNotEquals(bizMsg.mdseDescShortTxt_H1.getValue(), bizMsg.mdseDescShortTxt_BK.getValue())  //Item Description
    	 || isNotEquals(bizMsg.mdseItemMnfCd_H1.getValue(), bizMsg.mdseItemMnfCd_BK.getValue())  //Manufacturer
    	 || isNotEquals(bizMsg.mnfItemCd_H1.getValue(), bizMsg.mnfItemCd_BK.getValue())  //Manufacturer Item #
    	 || isNotEquals(bizMsg.upcCd_H1.getValue(), bizMsg.upcCd_BK.getValue())  //UPC Code
    	 || isNotEquals(bizMsg.mdseItemStsCd_H1.getValue(), bizMsg.mdseItemStsCd_BK.getValue())  //Status
    	 || isNotEquals(bizMsg.mdseDescLongTxt_H1.getValue(), bizMsg.mdseDescLongTxt_BK.getValue())  //Long Description
    	 //Header Center
    	 || isNotEquals(bizMsg.mdseItemTpCd_H1.getValue(), bizMsg.mdseItemTpCd_BK.getValue())  //Item Type
    	 || isNotEquals(bizMsg.mdseItemClsTpCd_H1.getValue(), bizMsg.mdseItemClsTpCd_BK.getValue()) //Item Classification
    	 || isNotEquals(bizMsg.coaMdseTpCd_H1.getValue(), bizMsg.coaMdseTpCd_BK.getValue())  //Merchandise Type
    	 || isNotEquals(bizMsg.coaProdCd_H1.getValue(), bizMsg.coaProdCd_BK.getValue())  //Product Code
    	 || isNotEquals(bizMsg.prchGrpCd_H1.getValue(), bizMsg.prchGrpCd_BK.getValue())  //Planning Group
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.thirdPtyItemFlg_H1.getValue()), 
    			 NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.thirdPtyItemFlg_BK.getValue()))  //Third Party
    	 || isNotEquals(bizMsg.mktMdlCd_H1.getValue(), bizMsg.mktMdlCd_BK.getValue())  //Marketing Model
    	 || isNotEquals(bizMsg.mktMdlSegCd_H1.getValue(), bizMsg.mktMdlSegCd_BK.getValue())
    	 || isNotEquals(bizMsg.mdseCratTmplNm_H1.getValue(), bizMsg.mdseCratTmplNm_BK.getValue())
    	 // 2020/04/07 QC#56017 Add Start
    	 || isNotEquals(bizMsg.xxChkBox_RP.getValue(), bizMsg.xxChkBox_BR.getValue())
    	 // 2020/04/07 QC#56017 Add End
    	 //Header Right
    	 || isNotEquals(bizMsg.zerothProdCtrlCd_H1.getValue(), bizMsg.zerothProdCtrlCd_BK.getValue())
    	 || isNotEquals(bizMsg.firstProdCtrlCd_H1.getValue(), bizMsg.firstProdCtrlCd_BK.getValue())
    	 || isNotEquals(bizMsg.scdProdCtrlCd_H1.getValue(), bizMsg.scdProdCtrlCd_BK.getValue())
    	 || isNotEquals(bizMsg.thirdProdCtrlCd_H1.getValue(), bizMsg.thirdProdCtrlCd_BK.getValue())
    	 || isNotEquals(bizMsg.frthProdCtrlCd_H1.getValue(), bizMsg.frthProdCtrlCd_BK.getValue())
    	 || isNotEquals(bizMsg.slsMatGrpCd_01.getValue(), bizMsg.slsMatGrpCd_B1.getValue())
    	 || isNotEquals(bizMsg.slsMatGrpCd_02.getValue(), bizMsg.slsMatGrpCd_B2.getValue())
    	 || isNotEquals(bizMsg.slsMatGrpCd_03.getValue(), bizMsg.slsMatGrpCd_B3.getValue())
    	 || isNotEquals(bizMsg.dsCmsnGrpCd_H1.getValue(), bizMsg.dsCmsnGrpCd_BK.getValue())
    	 //General Tab (Dimensions(Each))
    	 || isNotEquals(bizMsg.inPoundWt_EA.getValue(), bizMsg.inPoundWt_BE.getValue())
    	 || isNotEquals(bizMsg.inInchLg_EA.getValue(), bizMsg.inInchLg_BE.getValue())
    	 || isNotEquals(bizMsg.inInchWdt_EA.getValue(), bizMsg.inInchWdt_BE.getValue())
    	 || isNotEquals(bizMsg.inInchHgt_EA.getValue(), bizMsg.inInchHgt_BE.getValue())
    	 || isNotEquals(bizMsg.inPoundWt_UN.getValue(), bizMsg.inPoundWt_BU.getValue())
    	 || isNotEquals(bizMsg.inInchLg_UN.getValue(), bizMsg.inInchLg_BU.getValue())
    	 || isNotEquals(bizMsg.inInchWdt_UN.getValue(), bizMsg.inInchWdt_BU.getValue())
    	 || isNotEquals(bizMsg.inInchHgt_UN.getValue(), bizMsg.inInchHgt_BU.getValue())
         // 2019/10/28 QC#53741 Add Start
    	 || (ZYPCommonFunc.hasValue(bizMsg.inInchLg_UN) && isNotEquals(bizMsg.inInchLgUomCd_UN.getValue(), bizMsg.inInchLgUomCd_BU.getValue()))
    	 || (ZYPCommonFunc.hasValue(bizMsg.inInchWdt_UN) && isNotEquals(bizMsg.inInchWdtUomCd_UN.getValue(), bizMsg.inInchWdtUomCd_BU.getValue()))
    	 || (ZYPCommonFunc.hasValue(bizMsg.inInchHgt_UN) && isNotEquals(bizMsg.inInchHgtUomCd_UN.getValue(), bizMsg.inInchHgtUomCd_BU.getValue()))
    	 // 2019/10/28 QC#53741 Add End
    	 //General Tab (Units of Measure)
    	 || isNotEquals(bizMsg.pkgUomClsCd_H1.getValue(), bizMsg.pkgUomClsCd_BK.getValue())
    	 || isNotEquals(bizMsg.pkgUomCd_H1.getValue(), bizMsg.pkgUomCd_BK.getValue())
    	 //General Tab (Attributes(Item Type Specific))
    	 //Machine, Accessory
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_ME.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B1.getValue()))
    	 || isNotEquals(bizMsg.backOrdImpctTpCd_H1.getValue(), bizMsg.backOrdImpctTpCd_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_RM.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B2.getValue()))
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.sowReqFlg_H1.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.sowReqFlg_BK.getValue()))
    	 || isNotEquals(bizMsg.svcChrgItemTpCd_H1.getValue(), bizMsg.svcChrgItemTpCd_BK.getValue())
    	 //Supply
    	 || isNotEquals(bizMsg.imgSplyOemMnfCd_H1.getValue(), bizMsg.imgSplyOemMnfCd_BK.getValue())
    	 || isNotEquals(bizMsg.imgSplyOemCd_H1.getValue(), bizMsg.imgSplyOemCd_BK.getValue())
    	 || isNotEquals(bizMsg.imgSplyTpCd_H1.getValue(), bizMsg.imgSplyTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.imgSplyColorTpCd_H1.getValue(), bizMsg.imgSplyColorTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.imgSplyYieldCnt_H1.getValue(), bizMsg.imgSplyYieldCnt_BK.getValue())
    	 || isNotEquals(bizMsg.imgSplyYieldUomCd_H1.getValue(), bizMsg.imgSplyYieldUomCd_BK.getValue())
    	 || isNotEquals(bizMsg.imgSplyYieldTpCd_H1.getValue(), bizMsg.imgSplyYieldTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.imgSplyPvtLbTpCd_H1.getValue(), bizMsg.imgSplyPvtLbTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.easyPackTpCd_H1.getValue(), bizMsg.easyPackTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.areaOfPaperNum_H1.getValue(), bizMsg.areaOfPaperNum_BK.getValue())
    	 //Parts
    	 || isNotEquals(bizMsg.prtItemTpCd_H1.getValue(), bizMsg.prtItemTpCd_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_PD.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B3.getValue()))
    	 || isNotEquals(bizMsg.prtYieldOtptQty_H1.getValue(), bizMsg.prtYieldOtptQty_BK.getValue())
    	 || isNotEquals(bizMsg.prtYieldDaysAot_H1.getValue(), bizMsg.prtYieldDaysAot_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.prtSrvyReqFlg_H1.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.prtSrvyReqFlg_BK.getValue()))
    	 //Maintenance, Maintenance Charge, Maintenance Overages
    	 || isNotEquals(bizMsg.svcCovBaseTpCd_H1.getValue(), bizMsg.svcCovBaseTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.svcCovTmplTpCd_H1.getValue(), bizMsg.svcCovTmplTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.svcAllocTpCd_H1.getValue(), bizMsg.svcAllocTpCd_BK.getValue())
    	 // 2017/09/25 QC#18534(L3#445) ADD Start
    	 || isNotEquals(bizMsg.svcPgmTpCd_H1.getValue(), bizMsg.svcPgmTpCd_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_OM.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B7.getValue()))
    	 // 2017/09/25 QC#18534(L3#445) ADD End
    	 //Software, Software License, Software Maintenance
    	 || isNotEquals(bizMsg.swCatgCd_H1.getValue(), bizMsg.swCatgCd_BK.getValue())
    	 //|| isNotEquals(bizMsg.swTpCd_H1.getValue(), bizMsg.swTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.swVrsnTxt_H1.getValue(), bizMsg.swVrsnTxt_BK.getValue())
    	 || isNotEquals(bizMsg.swProdCatgCd_H1.getValue(), bizMsg.swProdCatgCd_BK.getValue())
    	 || isNotEquals(bizMsg.swLicCtrlTpCd_H1.getValue(), bizMsg.swLicCtrlTpCd_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_EC.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B4.getValue()))
    	 || isNotEquals(bizMsg.swLicSeatFromQty_H1.getValue(), bizMsg.swLicSeatFromQty_BK.getValue())
    	 || isNotEquals(bizMsg.swLicSeatToQty_H1.getValue(), bizMsg.swLicSeatToQty_BK.getValue())
    	 || isNotEquals(bizMsg.swLicSeatFixQty_H1.getValue(), bizMsg.swLicSeatFixQty_BK.getValue())
    	 || isNotEquals(bizMsg.swMaintCtrlTpCd_H1.getValue(), bizMsg.swMaintCtrlTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.asrnPntPerLicQty_H1.getValue(), bizMsg.asrnPntPerLicQty_BK.getValue())
    	 || isNotEquals(bizMsg.bdlMaintMdseCd_H1.getValue(), bizMsg.bdlMaintMdseCd_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.maintPopAvalFlg_H1.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.maintPopAvalFlg_BK.getValue()))
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.extMaintPopAvalFlg_H1.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.extMaintPopAvalFlg_BK.getValue()))
    	 || isNotEquals(bizMsg.swMaintTpCd_H1.getValue(), bizMsg.swMaintTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.maintItemTermMthNum_H1.getValue(), bizMsg.maintItemTermMthNum_BK.getValue())
    	 || isNotEquals(bizMsg.asrnPntMinQty_H1.getValue(), bizMsg.asrnPntMinQty_BK.getValue())
    	 || isNotEquals(bizMsg.asrnPntMaxQty_H1.getValue(), bizMsg.asrnPntMaxQty_BK.getValue())
    	 || isNotEquals(bizMsg.asrnPntFixPerOrdQty_H1.getValue(), bizMsg.asrnPntFixPerOrdQty_BK.getValue())
    	 //|| isNotEquals(bizMsg.swMaintTermYr_H1.getValue(), bizMsg.swMaintTermYr_BK.getValue())
    	 //|| isNotEquals(bizMsg.mdsePrcGrpCd_H1.getValue(), bizMsg.mdsePrcGrpCd_BK.getValue())
    	 || isNotEquals(bizMsg.dsIntgMdseTpCd_H1.getValue(), bizMsg.dsIntgMdseTpCd_BK.getValue())
    	 //General Tab (Safety)
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_HM.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B5.getValue()))
    	 || isNotEquals(bizMsg.hazMatCd_H1.getValue(), bizMsg.hazMatCd_BK.getValue())
    	 || isNotEquals(bizMsg.madeInCtryCd_H1.getValue(), bizMsg.madeInCtryCd_BK.getValue())
    	 || isNotEquals(bizMsg.asmInCtryCd_H1.getValue(), bizMsg.asmInCtryCd_BK.getValue())
    	 //General Tab (Logistics)
    	 || isNotEquals(bizMsg.frtClsCd_H1.getValue(), bizMsg.frtClsCd_BK.getValue())
    	 || isNotEquals(bizMsg.trfCd_H1.getValue(), bizMsg.trfCd_BK.getValue())
    	 //Inventory Tab (Inventory Attributes)
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IT.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B6.getValue()))
    	 // 2020/04/13 QC#56494 Add Start
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_II.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_BA.getValue()))
         // 2020/04/13 QC#56494 Add End
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_RA.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B8.getValue()))
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_RI.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_B9.getValue()))
    	 || isNotEquals(bizMsg.defSrcWhCd_H1.getValue(), bizMsg.defSrcWhCd_BK.getValue())
    	 || isNotEquals(bizMsg.defSrcSubWhCd_H1.getValue(), bizMsg.defSrcSubWhCd_BK.getValue())
    	 || isNotEquals(bizMsg.defSrcProcrTpCd_H1.getValue(), bizMsg.defSrcProcrTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.invtyHardAllocTpCd_H1.getValue(), bizMsg.invtyHardAllocTpCd_BK.getValue())
        //QC#18365 ADD START
    	 || isNotEquals(bizMsg.dropRtrnVndCd_H1.getValue(), bizMsg.dropRtrnVndCd_BK.getValue())
        //QC#18365 ADD END
    	 //Inventory Tab (Parts Return Control)
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_RR.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R1.getValue()))
    	 || isNotEquals(bizMsg.rtrnCtrlTpCd_H1.getValue(), bizMsg.rtrnCtrlTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.rtrnDsplTpCd_H1.getValue(), bizMsg.rtrnDsplTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.rtrnToVndCd_H1.getValue(), bizMsg.rtrnToVndCd_BK.getValue())
    	 || isNotEquals(bizMsg.rtrnToPrntVndCd_H1.getValue(), bizMsg.rtrnToPrntVndCd_BK.getValue())
    	 || isNotEquals(bizMsg.rtrnWhCd_H1.getValue(), bizMsg.rtrnWhCd_BK.getValue())
    	 //Inventory Tab (Serial)
    	 || isNotEquals(bizMsg.xxRadioBtn_SS.getValue(), bizMsg.xxRadioBtn_BK.getValue())
    	 //Accounting Tab (Costing)
    	 || isNotEquals(bizMsg.origFobAmt_H1.getValue(), bizMsg.origFobAmt_BK.getValue())
    	 //Accounting Tab (Costing)
    	 || isNotEquals(bizMsg.revCoaAcctCd_H1.getValue(), bizMsg.revCoaAcctCd_BK.getValue())
    	 || isNotEquals(bizMsg.cogsCoaAcctCd_H1.getValue(), bizMsg.cogsCoaAcctCd_BK.getValue())
    	 || isNotEquals(bizMsg.expCoaAcctCd_H1.getValue(), bizMsg.expCoaAcctCd_BK.getValue())
    	 || isNotEquals(bizMsg.lineBizTpCd_H1.getValue(), bizMsg.lineBizTpCd_BK.getValue())
    	 //Accounting Tab (Accounting Rules)
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IP.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R2.getValue()))
    	 || isNotEquals(bizMsg.dfrdAcctgRuleCd_H1.getValue(), bizMsg.dfrdAcctgRuleCd_BK.getValue())
    	 || isNotEquals(bizMsg.dfrdInvRuleCd_H1.getValue(), bizMsg.dfrdInvRuleCd_BK.getValue())
    	 || isNotEquals(bizMsg.taxExemTpCd_H1.getValue(), bizMsg.taxExemTpCd_BK.getValue())
    	 //Fields Service Tab
    	 || isNotEquals(bizMsg.svcWtyTpCd_H1.getValue(), bizMsg.svcWtyTpCd_BK.getValue())
    	 || isNotEquals(bizMsg.wtyDaysAot_H1.getValue(), bizMsg.wtyDaysAot_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_MM.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R3.getValue()))
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IB.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R4.getValue()))
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_SC.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R6.getValue()))
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IR.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R7.getValue()))
    	 || isNotEquals(bizMsg.iwrMdlCd_H1.getValue(), bizMsg.iwrMdlCd_BK.getValue())
    	 || isNotEquals(bizMsg.iwrMdseCd_H1.getValue(), bizMsg.iwrMdseCd_BK.getValue())
    	 || isNotEquals(bizMsg.mdseItemBillTpCd_H1.getValue(), bizMsg.mdseItemBillTpCd_BK.getValue())
    	 // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    	 || isNotEquals(bizMsg.svcIstlRuleNum_H1.getValue(), bizMsg.svcIstlRuleNum_BK.getValue())
    	 || isNotEquals(bizMsg.svcIstlCallGrpNum_H1.getValue(), bizMsg.svcIstlCallGrpNum_BK.getValue())
    	 // END   2023/09/05 K.Watanabe [QC#53408, ADD]
         // START 2023/12/12 K.Watanabe [QC#61300, ADD]
         || isNotEquals(bizMsg.svcDeinsRuleNum_H1.getValue(), bizMsg.svcDeinsRuleNum_BK.getValue())
         || isNotEquals(bizMsg.svcDeinsCallGrpNum_H1.getValue(), bizMsg.svcDeinsCallGrpNum_BK.getValue())
         // END   2023/12/12 K.Watanabe [QC#61300, ADD]
    	 //Order Processing Tab
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_CO.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R8.getValue()))
    	 || isNotEquals(bizMsg.cpoMinOrdQty_H1.getValue(), bizMsg.cpoMinOrdQty_BK.getValue())
    	 || isNotEquals(bizMsg.cpoMaxOrdQty_H1.getValue(), bizMsg.cpoMaxOrdQty_BK.getValue())
    	 || isNotEquals(bizMsg.cpoIncrOrdQty_H1.getValue(), bizMsg.cpoIncrOrdQty_BK.getValue())
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_IE.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_R9.getValue()))
    	 || isNotEquals(NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_CF.getValue()), NMAL0110CommonLogic.getFlgValueFromFields(bizMsg.xxChkBox_S1.getValue()))
    	) {
    		//Changed
    		return false;
    	}
    	
    	//UOM Table
    	BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
    	int radioIntValueK1 = 99999;
    	if (radioValueK1 != null) {
    		radioIntValueK1 = radioValueK1.intValue();
    	}
   		for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
   			boolean isFound = false;
   			String primPkgUomFlgL1 = bizMsg.L.no(i).primPkgUomFlg_L1.getValue();
   			boolean bPrimPkgUomFlgL1 = FLG_ON_Y.equals(primPkgUomFlgL1);
   		    for (int j = 0; j < bizMsg.K.getValidCount(); j++) {
   		    	boolean bPrimPkgUomFlgK1 = false;
   		    	if (j == radioIntValueK1) {
   		    		bPrimPkgUomFlgK1 = true;
   		    	}
   		        if (bizMsg.L.no(i).pkgUomCd_L1.getValue().equals(bizMsg.K.no(j).pkgUomCd_K1.getValue())) {
   		        	isFound = true;
   		        	BigDecimal inEachQty_L1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.L.no(i).inEachQty_L1.getValue());
   		        	BigDecimal inEachQty_K1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.K.no(j).inEachQty_K1.getValue());
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.K.no(j).pkgUomCd_K1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.K.no(j).inEachQty_K1)) {
   		        		//Changed
   		                return false;
   		        	} else if (!bizMsg.L.no(i).pkgUomCd_L1.getValue().equals(bizMsg.K.no(j).pkgUomCd_K1.getValue()) 
   		        			||inEachQty_L1.compareTo(inEachQty_K1) != 0 
   		        			|| bPrimPkgUomFlgL1 != bPrimPkgUomFlgK1) {
   		        		//Changed
   		                return false;
   		            }
   		        }
   		    }
   		    if (isFound == false) {
        		//Changed
                return false;
   		    }
   		}
   		boolean chkpkgUomExists = NMAL0110CommonLogic.chkMdseStorePkgExists(bizMsg);
   		if (chkpkgUomExists) {
   			for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
   				boolean exisisFlg = false;
       			for (int j = 0; j < bizMsg.L.getValidCount(); j++) {
       				if (bizMsg.K.no(i).pkgUomCd_K1.getValue().equals(bizMsg.L.no(j).pkgUomCd_L1.getValue())) {
           				exisisFlg= true;
       				}
       			}
   				if (!exisisFlg) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.K.no(i).inEachQty_K1)) {
   						continue;
   					}
	        		//Changed
	                return false;
   		        }
   			}
   		}
    	
   		//T&C Option Table
    	// Update T&C Option
   		for (int i = 0; i < bizMsg.Q.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.N.getValidCount(); j++) {
   		    	BigDecimal mdseTermCondOptPk_Q1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.Q.no(i).mdseTermCondOptPk_Q1.getValue());
   		    	BigDecimal mdseTermCondOptPk_N1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.N.no(j).mdseTermCondOptPk_N1.getValue());
   		        if (mdseTermCondOptPk_Q1.compareTo(mdseTermCondOptPk_N1) == 0) {
   		        	isFound = true;
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptTpCd_N1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptValTxt_N1)) {
   		        		//Changed
   		        		return false;
   		        	} else if (!bizMsg.Q.no(i).termCondOptTpCd_Q1.getValue().equals(bizMsg.N.no(j).termCondOptTpCd_N1.getValue()) 
   		        			|| !bizMsg.Q.no(i).termCondOptValTxt_Q1.getValue().equals(bizMsg.N.no(j).termCondOptValTxt_N1.getValue())) {
   		        		//Changed
   		        		return false;
   		            }
   		        }
   		    }
   		    if (isFound == false) {
	    		//Changed
	    		return false;
   		    }
   		}
   		boolean chkMdseTermCondOpt = NMAL0110CommonLogic.chkMdseTermCondOpt(bizMsg);
   		if (chkMdseTermCondOpt) {
   			for (int i = 0; i < bizMsg.N.getValidCount(); i++) {
   		        if (!ZYPCommonFunc.hasValue(bizMsg.N.no(i).mdseTermCondOptPk_N1.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptTpCd_N1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.N.no(i).termCondOptValTxt_N1)) {
   						continue;
   					}
	        		//Changed
	        		return false;
   		        }
   			}
   		}
   		
   		//Serial
   		for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
   		    	BigDecimal mdseSerNumRngPk_F1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.F.no(i).mdseSerNumRngPk_F1.getValue());
   		    	BigDecimal mdseSerNumRngPk_A1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.A.no(j).mdseSerNumRngPk_A1.getValue());
   		        if (mdseSerNumRngPk_F1.compareTo(mdseSerNumRngPk_A1) == 0) {
   		        	isFound = true;
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
   			    		//Changed
   			    		return false;
   		        	} else if (!bizMsg.F.no(i).fromSerNum_F1.getValue().equals(bizMsg.A.no(j).fromSerNum_A1.getValue()) 
   		        			|| !bizMsg.F.no(i).thruSerNum_F1.getValue().equals(bizMsg.A.no(j).thruSerNum_A1.getValue())) {
   			    		//Changed
   			    		return false;
   		            }
   		        }
   		    }
   		    if (isFound == false) {
	    		//Changed
	    		return false;
   		    }
   		}
   		boolean chkDetailListExists = NMAL0110CommonLogic.chkDetailListExists(bizMsg);
   		if (chkDetailListExists) {
   			for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
   		        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseSerNumRngPk_A1.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).fromSerNum_A1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.A.no(i).thruSerNum_A1)) {
   						continue;
   					}
   		    		//Changed
   		    		return false;
   		        }
   			}
   		}
   		
   		//Supd
  		for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
   		    	BigDecimal supdRelnPk_H1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.H.no(i).supdRelnPk_H1.getValue());
   		    	BigDecimal supdRelnPk_C1 = NMAL0110CommonLogic.nvlBigDecimal(bizMsg.C.no(j).supdRelnPk_C1.getValue());
   		        if (supdRelnPk_H1.compareTo(supdRelnPk_C1) == 0) {
   		        	isFound = true;
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdCratDt_C1)) {
   	   		    		//Changed
   	   		    		return false;
   		                
   		        	} else if (!bizMsg.H.no(i).supdToMdseCd_H1.getValue().equals(bizMsg.C.no(j).supdToMdseCd_C1.getValue()) 
   		        			|| !bizMsg.H.no(i).supdCratDt_H1.getValue().equals(bizMsg.C.no(j).supdCratDt_C1.getValue())) {
   	   		    		//Changed
   	   		    		return false;
   		            }
   		        }
   		    }
   		    if (isFound == false) {
	    		//Changed
	    		return false;
   		    }
   		}
   		boolean chkSupdExists = NMAL0110CommonLogic.chkSupdExists(bizMsg);
   		if (chkSupdExists) {
   			for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
   		        if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdRelnPk_C1.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdToMdseCd_C1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.C.no(i).supdCratDt_C1)) {
   						continue;
   					}
   		    		//Changed
   		    		return false;
   		        }
   			}
   		}
   		
   		//Relation
   		for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.D.getValidCount(); j++) {
   		    	String keyI = NMAL0110CommonLogic.nvlString(bizMsg.I.no(i).relnMdseCd_IB.getValue());
   		    	String keyD = NMAL0110CommonLogic.nvlString(bizMsg.D.no(j).relnMdseCd_DB.getValue());
   		        if (keyI.equals(keyD)) {
   		        	isFound = true;
   		        	// No Key
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1)) {
   	   		    		//Changed
   	   		    		return false;
   		            // Key Change
   		        	} else if (!bizMsg.I.no(i).relnMdseCd_I1.getValue().equals(bizMsg.D.no(j).relnMdseCd_D1.getValue()) 
   		        			|| !bizMsg.I.no(i).mdseItemRelnTpCd_I1.getValue().equals(bizMsg.D.no(j).mdseItemRelnTpCd_D1.getValue())) {
   	   		    		//Changed
   	   		    		return false;
   		            }
   		        }
   		    }
   		    if (isFound == false) {
	    		//Changed
	    		return false;
   		    }
   		}
   		boolean chkMdseItemFlipSetExists = NMAL0110CommonLogic.chkMdseItemFlipSetExists(bizMsg);
   		if (chkMdseItemFlipSetExists) {
   			for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
   		        if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_DB.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).relnMdseCd_D1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.D.no(i).mdseItemRelnTpCd_D1)) {
   						continue;
   					}
   		    		//Changed
   		    		return false;
   		        }
   			}
   		}
    	
    	//Cust Mdse
   		for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
   			boolean isFound = false;
   		    for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
   		    	String keyJ = NMAL0110CommonLogic.nvlString(bizMsg.J.no(i).dsAcctNum_JB.getValue()) + NMAL0110CommonLogic.nvlString(bizMsg.J.no(i).custMdseCd_JB.getValue());
   		    	String keyE = NMAL0110CommonLogic.nvlString(bizMsg.E.no(j).dsAcctNum_EB.getValue()) + NMAL0110CommonLogic.nvlString(bizMsg.E.no(j).custMdseCd_EB.getValue());
   		        if (keyJ.equals(keyE)) {
   		        	isFound = true;
   		        	// No Key
   		        	if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1) && 
   		        		!ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseCd_E1)) {
   	   		    		//Changed
   	   		    		return false;
   		            // Key Change
   		        	} else if (!bizMsg.J.no(i).dsAcctNum_J1.getValue().equals(bizMsg.E.no(j).dsAcctNum_E1.getValue()) 
   		        			|| !bizMsg.J.no(i).custMdseCd_J1.getValue().equals(bizMsg.E.no(j).custMdseCd_E1.getValue())) {
   	   		    		//Changed
   	   		    		return false;
   	   		    	// Mod Start 2017/01/04 QC#16926
   	   				// No Key Change
//   		        	} else if (!bizMsg.J.no(i).effFromDt_J1.getValue().equals(bizMsg.E.no(j).effFromDt_E1.getValue())
//   		        			|| !bizMsg.J.no(i).xxChkBox_JN.getValue().equals(bizMsg.E.no(j).xxChkBox_EN.getValue())) {
   		        	} else if (!bizMsg.J.no(i).custMdseNm_J1.getValue().equals(bizMsg.E.no(j).custMdseNm_E1.getValue())
   		        	        || !bizMsg.J.no(i).effFromDt_J1.getValue().equals(bizMsg.E.no(j).effFromDt_E1.getValue())
                            || !bizMsg.J.no(i).xxChkBox_JN.getValue().equals(bizMsg.E.no(j).xxChkBox_EN.getValue())) {
   	   		    		//Changed
   	   		    		return false;
   		            }
   		        	// Mod End 2017/01/04 QC#16926
   		        }
   		    }
   		    // Delete Line
   		    if (isFound == false) {
	    		//Changed
	    		return false;
   		    }
   		}
   		boolean chkCustMdseXrefExists = NMAL0110CommonLogic.chkCustMdseXrefExists(bizMsg);
   		if (chkCustMdseXrefExists) {
   			for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
   				// Add Line
   		        if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_EB.getValue())) {
   					if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).dsAcctNum_E1) 
   							&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).custMdseCd_E1)
   							&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).effFromDt_E1)
   							&& !ZYPCommonFunc.hasValue(bizMsg.E.no(i).xxChkBox_EN)
   							) {
   						continue;
   					}
   		    		//Changed
   		    		return false;
   		        }
   			}
   		}
   		
    	//No Changed
    	return true;
    }
    
    private boolean isNotEquals(String orig, String backUp) {
    	if (!nvl(orig).equals(nvl(backUp))) {
    		return true;
    	}
    	return false;
    }
    
    private boolean isNotEquals(BigDecimal orig, BigDecimal backUp) {
    	if (orig == null) {
    		if (backUp == null) {
    			return false;
    		} else {
    			return true;
    		}
    	} else {
    		if (backUp == null) {
    			return true;
    		}
    	}
    	if (orig.compareTo(backUp) != 0) {
    		return true;
    	}
    	return false;
    }
    
    private String nvl(String val) {
    	if (!ZYPCommonFunc.hasValue(val)) {
    		return "";
    	}
    	return val;
    }

    // 2019/10/23 QC#51964 Add Start
    private boolean checkMdseTp(NMAL0110CMsg bizMsg) {
        // Open transaction?
        S21SsmEZDResult rsTran = NMAL0110Query.getInstance().getTransactionDataCPOAndPO(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
        // 2019/12/13 QC#54621 Mod Start
        BigDecimal countInvty = NMAL0110Query.getInstance().getInventoryCount(bizMsg.mdseCd_H1.getValue());
        Integer countTran = new Integer(0);
        if (rsTran.isCodeNormal()) {
            //Integer countTran = (Integer) rsTran.getResultObject();
            countTran = (Integer) rsTran.getResultObject();
        }
        //if (countTran > 0) {
        if (countTran.compareTo(new Integer(0)) > 0 || countInvty.compareTo(BigDecimal.ZERO) > 0) {
            List<Map<String, String>> coaAcctAfMapList = NMAL0110Query.getInstance().getCoaAcct(bizMsg.coaMdseTpCd_H1.getValue());
            List<Map<String, String>> coaAcctBfMapList = NMAL0110Query.getInstance().getCoaAcct(bizMsg.coaMdseTpCd_BK.getValue());
            if (coaAcctAfMapList != null && coaAcctBfMapList != null && coaAcctAfMapList.size() == coaAcctBfMapList.size()) {
                for (int i = 0; i < coaAcctBfMapList.size(); i++) {
                    Map<String, String> coaAcctAfMap = coaAcctAfMapList.get(i);
                    Map<String, String> coaAcctBfMap = coaAcctBfMapList.get(i);
                    if (!(ZYPCommonFunc.hasValue(coaAcctBfMap.get("COA_PROJ_ACCT_TP_CD"))
                            && ZYPCommonFunc.hasValue(coaAcctBfMap.get("COA_ACCT_CD"))
                            && coaAcctBfMap.get("COA_PROJ_ACCT_TP_CD").equals(coaAcctAfMap.get("COA_PROJ_ACCT_TP_CD"))
                            && coaAcctBfMap.get("COA_ACCT_CD").equals(coaAcctAfMap.get("COA_ACCT_CD")))) {
                        return true;
                    }
                }
            } else {
                // Different size or not get
                return true;
            }
        }
        //}
        // 2019/12/13 QC#54621 Mod End
        return false;
    }
    // 2019/10/23 QC#51964 Add End
}
