package business.blap.NWAL2040;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2040.common.NWAL2040CommonLogic;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.blap.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2016/03/23   Hitachi         K.Kojima        Update          S21 NA Unit Test #56  Add Upload Function
 * 04/05/2015   SRAA            K.Aratani       Update          QC#6258
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#21369
 * 2018/07/10   Fujitsu         M.Ishii         Update          QC#26147
 *</pre>
 */
public class NWAL2040BL06 extends S21BusinessHandler implements NWAL2040Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
        NWAL2040CMsg bizMsg = (NWAL2040CMsg) cMsg;
        NWAL2040SMsg shareMsg = (NWAL2040SMsg) sMsg;
        //cMsg.setCommitSMsg(true);
        String scrnAplID = bizMsg.getScreenAplID();
        try {
    		if ("NWAL2040Scrn00_CMN_Submit".equals(scrnAplID)) {
            	NWAL2040Scrn00_CMN_Submit(bizMsg, shareMsg);
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
    private void NWAL2040Scrn00_CMN_Submit(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

		//Save from cMsg to sMsg
		if (TAB_SRC_CATG.equals(bizMsg.xxDplyTab_H1.getValue())) {
			NWAL2040CommonLogic.copyFromCMsgToSMsgForSrcCatg(bizMsg, sMsg, false);
        // 2017/09/12 QC#19805 Mod Start
//		} else {
        } else if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Mod End
			NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmpl(bizMsg, sMsg, false);
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {
            NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmplRMA(bizMsg, sMsg, false);
        // 2017/09/12 QC#19805 Add End
		}
		
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
    	// Update
    	// #####################    	
    	// Detail
		if (TAB_SRC_CATG.equals(bizMsg.xxDplyTab_H1.getValue())) {
	   		for (int i = 0; i < sMsg.D.getValidCount(); i++) {
	   			boolean isFound = false;
	   		    for (int j = 0; j < sMsg.B.getValidCount(); j++) {
	   		    	BigDecimal backupPK = NWAL2040CommonLogic.nvlBigDecimal(sMsg.D.no(i).ordCatgBizCtxPk_D1.getValue());
	   		    	BigDecimal screenPK = NWAL2040CommonLogic.nvlBigDecimal(sMsg.B.no(j).ordCatgBizCtxPk_B1.getValue());
	   		        if (backupPK.compareTo(screenPK) == 0) {
	   		        	isFound = true;
	   		        	if (!ZYPCommonFunc.hasValue(sMsg.B.no(j).dsOrdCatgCd_B1) && 
	   		        		!ZYPCommonFunc.hasValue(sMsg.B.no(j).dsOrdTpCd_B1) &&
	   		        		!ZYPCommonFunc.hasValue(sMsg.B.no(j).firstBizCtxAttrbTxt_B1) &&
	   		        		!ZYPCommonFunc.hasValue(sMsg.B.no(j).scdBizCtxAttrbTxt_B1) &&
	   		        		!ZYPCommonFunc.hasValue(sMsg.B.no(j).effFromDt_B1)
	   		        		) {
	   		                if(!NWAL2040CommonLogic.deleteOrdCatgBizCtx(getGlobalCompanyCode(), bizMsg, sMsg, j)) {
	   		   		    	    return;
	   		                }
	   		                
	   		        	} else if (!sMsg.D.no(i).dsOrdCatgCd_D1.getValue().equals(sMsg.B.no(j).dsOrdCatgCd_B1.getValue()) 
	   		        			|| !sMsg.D.no(i).dsOrdTpCd_D1.getValue().equals(sMsg.B.no(j).dsOrdTpCd_B1.getValue())
	   		        			|| !sMsg.D.no(i).firstBizCtxAttrbTxt_D1.getValue().equals(sMsg.B.no(j).firstBizCtxAttrbTxt_B1.getValue())
	   		        			// 2018/07/10 QC#26147 Add Start 
	   		        			|| !sMsg.D.no(i).scdBizCtxAttrbTxt_D1.getValue().equals(sMsg.B.no(j).scdBizCtxAttrbTxt_B1.getValue())
	   		        			// 2018/07/10 QC#26147 Add End
	   		        			|| !sMsg.D.no(i).effFromDt_D1.getValue().equals(sMsg.B.no(j).effFromDt_B1.getValue())
	   		        			|| !sMsg.D.no(i).effThruDt_D1.getValue().equals(sMsg.B.no(j).effThruDt_B1.getValue())
	   		        			) {
//	   		        	} else {
	   		                // update.
	   		        		NWAL2040CommonLogic.updateOrdCatgBizCtx(getGlobalCompanyCode(), bizMsg, sMsg, j);
	   		            }
	   		        }
	   		    }
	   		    if (isFound == false) {
	   		    	if(!NWAL2040CommonLogic.deleteOrdCatgBizCtx(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
	   		    	    return;
	   		    	}
	   		    }
	   		}
	   		boolean chkSupdExists = NWAL2040CommonLogic.chkOrdCatgBizCtx(sMsg);
	   		if (chkSupdExists) {
	   			for (int i = 0; i < sMsg.B.getValidCount(); i++) {
	   		        if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).ordCatgBizCtxPk_B1.getValue())) {
	   					if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdCatgCd_B1) 
	   							&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdTpCd_B1)
	   							&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).firstBizCtxAttrbTxt_B1)
	   							&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).scdBizCtxAttrbTxt_B1)
	   							&& !ZYPCommonFunc.hasValue(sMsg.B.no(i).effFromDt_B1)
	   							) {
	   						continue;
	   					}
	   					if (!NWAL2040CommonLogic.insertOrdCatgBizCtx(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
	   	   		    	    return;
	   		            }
	   		        }
	   			}
	   		}
	   		
	        //Search
	        NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), true);
	        
	        //NMAM8182I=0,[@] process ended normally.
			bizMsg.setMessageInfo("NMAM8182I", new String[]{"Sourcing Category"});
			
        // 2017/09/12 QC#19805 Mod Start
//		} else {
		} else if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Mod End
	   		for (int i = 0; i < sMsg.C.getValidCount(); i++) {
	   			boolean isFound = false;
	   		    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
	   		    	String backupkey = sMsg.C.no(i).defWhMapTmplCd_C1.getValue() + sMsg.C.no(i).mdseItemClsTpCd_C1.getValue() 
	   		    	    + getFlgValueFromFields(sMsg.C.no(i).thirdPtyItemFlg_C1.getValue())
	   		    	    + sMsg.C.no(i).fromPostCd_C1.getValue() + sMsg.C.no(i).toPostCd_C1.getValue();
	   		    	String screenkey = sMsg.A.no(j).defWhMapTmplCd_A1.getValue() + sMsg.A.no(j).mdseItemClsTpCd_A1.getValue() 
	   		    	    + getFlgValueFromFields(sMsg.A.no(j).thirdPtyItemFlg_A1.getValue())
   		    	        + sMsg.A.no(j).fromPostCd_A1.getValue() + sMsg.A.no(j).toPostCd_A1.getValue();
	   		        if (backupkey.equals(screenkey)) {
	   		        	isFound = true;
	   		        	if (!ZYPCommonFunc.hasValue(sMsg.A.no(j).defWhMapTmplCd_A1) && 
	   		        		!ZYPCommonFunc.hasValue(sMsg.A.no(j).mdseItemClsTpCd_A1) &&
	   		        		!ZYPCommonFunc.hasValue(getFlgValueFromFields(sMsg.A.no(j).thirdPtyItemFlg_A1.getValue())) &&
	   		        		!ZYPCommonFunc.hasValue(sMsg.A.no(j).fromPostCd_A1) &&
	   		        		!ZYPCommonFunc.hasValue(sMsg.A.no(j).toPostCd_A1)
	   		        		) {
	   		                if(!NWAL2040CommonLogic.deleteOrdDefWh(getGlobalCompanyCode(), bizMsg, sMsg, j)) {
	   		   		    	    return;
	   		                }
	   		                
	   		        	} else if (!nullToBlank(sMsg.C.no(i).defWhMapTmplCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).defWhMapTmplCd_A1.getValue())) 
	   		        			|| !nullToBlank(sMsg.C.no(i).mdseItemClsTpCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).mdseItemClsTpCd_A1.getValue()))
	   		        			|| !getFlgValueFromFields(sMsg.C.no(i).thirdPtyItemFlg_C1.getValue()).equals(getFlgValueFromFields(sMsg.A.no(j).thirdPtyItemFlg_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).fromPostCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).fromPostCd_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).toPostCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).toPostCd_A1.getValue()))
	   		        			|| !getFlgValueFromFields(sMsg.C.no(i).onHndChkFlg_C1.getValue()).equals(getFlgValueFromFields(sMsg.A.no(j).onHndChkFlg_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).otbdPrimOnHndChkWhCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).otbdPrimOnHndChkWhCd_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).rtlSwhNm_01.getValue()).equals(nullToBlank(sMsg.A.no(j).rtlSwhNm_01.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).otbdPrimOnHndLinSrcCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).otbdPrimOnHndLinSrcCd_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).otbdScdOnHndChkWhCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).otbdScdOnHndChkWhCd_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).rtlSwhNm_02.getValue()).equals(nullToBlank(sMsg.A.no(j).rtlSwhNm_02.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).otbdScdOnHndLineSrcCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).otbdScdOnHndLineSrcCd_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).otbdDefWhCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).otbdDefWhCd_A1.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).rtlSwhNm_03.getValue()).equals(nullToBlank(sMsg.A.no(j).rtlSwhNm_03.getValue()))
	   		        			|| !nullToBlank(sMsg.C.no(i).otbdDefLineSrcCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).otbdDefLineSrcCd_A1.getValue()))
                                // 2017/09/12 QC#19805 Del Start
//	   		        			|| !nullToBlank(sMsg.C.no(i).rmaDefWhCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).rmaDefWhCd_A1.getValue()))
//	   		        			|| !nullToBlank(sMsg.C.no(i).rtlSwhNm_04.getValue()).equals(nullToBlank(sMsg.A.no(j).rtlSwhNm_04.getValue()))
//	   		        			|| !nullToBlank(sMsg.C.no(i).rmaDefLineSrcCd_C1.getValue()).equals(nullToBlank(sMsg.A.no(j).rmaDefLineSrcCd_A1.getValue()))
                                // 2017/09/12 QC#19805 Del End
	   		        			) {
//	   		        	} else {
	   		                // update.
	   		        		NWAL2040CommonLogic.updateOrdDefWh(getGlobalCompanyCode(), bizMsg, sMsg, j);
	   		            }
	   		        }
	   		    }
	   		    if (isFound == false) {
	   		    	if(!NWAL2040CommonLogic.deleteOrdDefWh(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
	   		    	    return;
	   		    	}
	   		    }
	   		}
	   		boolean chkSupdExists = NWAL2040CommonLogic.chkOrdDefWh(sMsg);
	   		if (chkSupdExists) {
	   			for (int i = 0; i < sMsg.A.getValidCount(); i++) {
	   				
	   				boolean exisisFlg = false;
	       			for (int j = 0; j < sMsg.C.getValidCount(); j++) {
	       				if (sMsg.A.no(i).defWhMapTmplCd_A1.getValue().equals(sMsg.C.no(j).defWhMapTmplCd_C1.getValue())
	       						&& sMsg.A.no(i).mdseItemClsTpCd_A1.getValue().equals(sMsg.C.no(j).mdseItemClsTpCd_C1.getValue())
	       						&& getFlgValueFromFields(sMsg.A.no(i).thirdPtyItemFlg_A1.getValue()).equals(getFlgValueFromFields(sMsg.C.no(j).thirdPtyItemFlg_C1.getValue()))
	    	       				&& sMsg.A.no(i).fromPostCd_A1.getValue().equals(sMsg.C.no(j).fromPostCd_C1.getValue())
	    	    	       		&& sMsg.A.no(i).toPostCd_A1.getValue().equals(sMsg.C.no(j).toPostCd_C1.getValue())
	       						) {
	           				exisisFlg= true;
	           				break;
	       				}
	       			}
	   				if (!exisisFlg) {
	   					if (!NWAL2040CommonLogic.insertOrdDefWh(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
	   	   		    	    return;
	   		            }
	   		        }
	   			}
	   		}
	   		
	        //Search
	        NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), true);
	        
	        //NMAM8182I=0,[@] process ended normally.
			bizMsg.setMessageInfo("NMAM8182I", new String[]{"Mapping Templates Qualifies"});

        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {
            for (int i = 0; i < sMsg.F.getValidCount(); i++) {
                boolean isFound = false;
                String backupkey = sMsg.F.no(i).defWhMapTmplCd_F1.getValue() + " - " + sMsg.F.no(i).mdseItemClsTpCd_F1.getValue() 
                    + " - " + getFlgValueFromFields(sMsg.F.no(i).thirdPtyItemFlg_F1.getValue())
                    + " - " + sMsg.F.no(i).fromPostCd_F1.getValue() + " - " + sMsg.F.no(i).toPostCd_F1.getValue()
                    + " - " + sMsg.F.no(i).thirdPtyDspTpCd_F1.getValue() + " - " + sMsg.F.no(i).dropRtrnVndCd_F1.getValue();
                for (int j = 0; j < sMsg.E.getValidCount(); j++) {
                    String screenkey = sMsg.E.no(j).defWhMapTmplCd_E1.getValue() + " - " + sMsg.E.no(j).mdseItemClsTpCd_E1.getValue() 
                        + " - " + getFlgValueFromFields(sMsg.E.no(j).thirdPtyItemFlg_E1.getValue())
                        + " - " + sMsg.E.no(j).fromPostCd_E1.getValue() + " - " + sMsg.E.no(j).toPostCd_E1.getValue()
                        + " - " + sMsg.E.no(j).thirdPtyDspTpCd_E1.getValue() + " - " + sMsg.E.no(j).dropRtrnVndCd_E1.getValue();
                    if (ZYPCommonFunc.hasValue(sMsg.E.no(j).ordRmaDefWhPk_E1) && backupkey.equals(screenkey)) {
                        isFound = true;
                        if (!getFlgValueFromFields(sMsg.F.no(i).onHndChkFlg_F1.getValue()).equals(getFlgValueFromFields(sMsg.E.no(j).onHndChkFlg_E1.getValue()))
                                || !nullToBlank(sMsg.F.no(i).rmaDefWhCd_F1.getValue()).equals(nullToBlank(sMsg.E.no(j).rmaDefWhCd_E1.getValue()))
                                ) {
                            // update.
                            NWAL2040CommonLogic.updateOrdRmaDefWh(getGlobalCompanyCode(), bizMsg, sMsg, j);
                        }
                        break;
                    }
                }
                if (isFound == false) {
                    if(!NWAL2040CommonLogic.deleteOrdRmaDefWh(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
                        return;
                    }
                }
            }

            for (int i = 0; i < sMsg.E.getValidCount(); i++) {
                boolean exisisFlg = false;
                for (int j = 0; j < sMsg.F.getValidCount(); j++) {
                    if (ZYPCommonFunc.hasValue(sMsg.E.no(i).ordRmaDefWhPk_E1)
                            && sMsg.E.no(i).defWhMapTmplCd_E1.getValue().equals(sMsg.F.no(j).defWhMapTmplCd_F1.getValue())
                            && sMsg.E.no(i).mdseItemClsTpCd_E1.getValue().equals(sMsg.F.no(j).mdseItemClsTpCd_F1.getValue())
                            && getFlgValueFromFields(sMsg.E.no(i).thirdPtyItemFlg_E1.getValue()).equals(getFlgValueFromFields(sMsg.F.no(j).thirdPtyItemFlg_F1.getValue()))
                            && sMsg.E.no(i).fromPostCd_E1.getValue().equals(sMsg.F.no(j).fromPostCd_F1.getValue())
                            && sMsg.E.no(i).toPostCd_E1.getValue().equals(sMsg.F.no(j).toPostCd_F1.getValue())
                            && nullToBlank(sMsg.E.no(i).thirdPtyDspTpCd_E1.getValue()).equals(nullToBlank(sMsg.F.no(j).thirdPtyDspTpCd_F1.getValue()))
                            && nullToBlank(sMsg.E.no(i).dropRtrnVndCd_E1.getValue()).equals(nullToBlank(sMsg.F.no(j).dropRtrnVndCd_F1.getValue()))
                            ) {
                        exisisFlg= true;
                        break;
                    }
                }
                if (!exisisFlg) {
                    if (!NWAL2040CommonLogic.insertOrdRmaDefWh(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
                        return;
                    }
                }
            }

            //Search
            NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), true);

            //NMAM8182I=0,[@] process ended normally.
            bizMsg.setMessageInfo("NMAM8182I", new String[]{"Mapping Templates Qualifies(RMA)"});
        // 2017/09/12 QC#19805 Add End
		}
    }
    
    private boolean checkInput_Header(NWAL2040CMsg bizMsg) {
    	
        boolean errorFlg = false;
                
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }

    private boolean checkInput_Detail(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
    	
        boolean errorFlg = false;

        if (!ZYPCommonFunc.hasValue(bizMsg.xxDplyTab_H1)) {
            errorFlg = false;
        } else if (TAB_SRC_CATG.equals(bizMsg.xxDplyTab_H1.getValue())) {
            //Category - Reason
       		for (int i = 0; i < sMsg.B.getValidCount(); i++) {
       			//Mandatory Check
       			if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdCatgCd_B1)) {
       				//ZZM9000E=0,[@] field is mandatory.
                	bizMsg.B.no(i).dsOrdCatgCd_B1.setErrorInfo(1, "ZZM9000E", new String[]{"Order Category"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdTpCd_B1)) {
       				//ZZM9000E=0,[@] field is mandatory.
                	bizMsg.B.no(i).dsOrdTpCd_B1.setErrorInfo(1, "ZZM9000E", new String[]{"Order Reason Code"});
   					return true;
       			}
       			
       			String uniqueKey = sMsg.B.no(i).dsOrdCatgCd_B1.getValue() + sMsg.B.no(i).dsOrdTpCd_B1.getValue();
       			
       			if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).firstBizCtxAttrbTxt_B1)) {
       				//ZZM9000E=0,[@] field is mandatory.
                	bizMsg.B.no(i).firstBizCtxAttrbTxt_B1.setErrorInfo(1, "ZZM9000E", new String[]{"Mapping Template Name"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).scdBizCtxAttrbTxt_B1)) {
       				//ZZM9000E=0,[@] field is mandatory.
                	bizMsg.B.no(i).scdBizCtxAttrbTxt_B1.setErrorInfo(1, "ZZM9000E", new String[]{"Inventory Owner"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).effFromDt_B1)) {
       				//ZZM9000E=0,[@] field is mandatory.
                	bizMsg.B.no(i).effFromDt_B1.setErrorInfo(1, "ZZM9000E", new String[]{"Effective Date"});
   					return true;
       			}
       			
       			int count = 0;
       			java.util.List<Integer> list = new java.util.ArrayList<Integer>();
       			for (int j = 0; j < sMsg.B.getValidCount(); j++) {
       				String dsOrdCatgCd = ZYPCommonFunc.hasValue(sMsg.B.no(j).dsOrdCatgCd_B1) ? sMsg.B.no(j).dsOrdCatgCd_B1.getValue() : "";
       				String dsOrdTpCd = ZYPCommonFunc.hasValue(sMsg.B.no(j).dsOrdTpCd_B1) ? sMsg.B.no(j).dsOrdTpCd_B1.getValue() : "";
           			String uniqueKeyComp = dsOrdCatgCd + dsOrdTpCd;
           			if (uniqueKey.equals(uniqueKeyComp)) {
           				count++;
           				list.add(new Integer(j));
           			}
       				if (count > 1) {
       					for (int k = 0; k < list.size(); k ++) {
       						Integer iErrorLine = (Integer) list.get(k);
           					//NMAM0072E=0, @  is duplicated.
       	                	bizMsg.B.no(iErrorLine.intValue()).dsOrdCatgCd_B1.setErrorInfo(1, "NMAM0072E", new String[]{"Order Category, Reason"});
       	                	bizMsg.B.no(iErrorLine.intValue()).dsOrdTpCd_B1.setErrorInfo(1, "NMAM0072E", new String[]{"Order Category, Reason"});
       					}
   	   					return true;
       				}
       			}
       			
       			if (ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdCatgCd_B1) && ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdTpCd_B1)) {
       				Map<String, Object> map =  NWAL2040CommonLogic.getDsOrdCatgAndDsOrdTp(getGlobalCompanyCode(), sMsg.B.no(i).dsOrdCatgCd_B1.getValue(), sMsg.B.no(i).dsOrdTpCd_B1.getValue());
       				if (map == null || map.get("DS_ORD_CATG_CD") == null) {
   	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
   	                	bizMsg.B.no(i).dsOrdCatgCd_B1.setErrorInfo(1, "NDAM0007E", new String[]{"DS_ORD_TP", "DS_ORD_CATG_CD,DS_ORD_TP_CD", sMsg.B.no(i).dsOrdCatgCd_B1.getValue()+","+sMsg.B.no(i).dsOrdTpCd_B1.getValue()});
   	                	bizMsg.B.no(i).dsOrdTpCd_B1.setErrorInfo(1, "NDAM0007E", new String[]{"DS_ORD_TP", "DS_ORD_CATG_CD,DS_ORD_TP_CD", sMsg.B.no(i).dsOrdCatgCd_B1.getValue()+","+sMsg.B.no(i).dsOrdTpCd_B1.getValue()});
   	   					return true;
       				}
       			} else if (ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdCatgCd_B1) && !ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdTpCd_B1)) {
       				Map<String, Object> map =  NWAL2040CommonLogic.getDsOrdCatg(getGlobalCompanyCode(), sMsg.B.no(i).dsOrdCatgCd_B1.getValue());
       				if (map == null || map.get("DS_ORD_CATG_CD") == null) {
   	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
   	                	bizMsg.B.no(i).dsOrdCatgCd_B1.setErrorInfo(1, "NDAM0007E", new String[]{"DS_ORD_CATG", "DS_ORD_CATG_CD", sMsg.B.no(i).dsOrdCatgCd_B1.getValue()});
   	   					return true;
       				}
       			}
       		}
            
        // 2017/09/12 QC#19805 Mod Start
//        } else {
        } else if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Mod End
       		for (int i = 0; i < sMsg.A.getValidCount(); i++) {
       			//Mandatory Check
       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).defWhMapTmplCd_A1)) {
       				//ZZM9000E=0,[@] field is mandatory.
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                	bizMsg.A.no(errorLineNo).defWhMapTmplCd_A1.setErrorInfo(1, "ZZM9000E", new String[]{"Mapping Template Name"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseItemClsTpCd_A1)) {
       				//ZZM9000E=0,[@] field is mandatory.
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                    // 2017/09/12 QC#19805 Mod Start
//                	bizMsg.A.no(errorLineNo).mdseItemClsTpCd_A1.setErrorInfo(1, "ZZM9000E", new String[]{"Item Sourcing Classfication: Qualifer"});
                    bizMsg.A.no(errorLineNo).mdseItemClsTpCd_A1.setErrorInfo(1, "ZZM9000E", new String[]{"Item Sourcing Classification: Qualifier"});
                    // 2017/09/12 QC#19805 Mod End
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).fromPostCd_A1)) {
       				//ZZM9000E=0,[@] field is mandatory.
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                	bizMsg.A.no(errorLineNo).fromPostCd_A1.setErrorInfo(1, "ZZM9000E", new String[]{"Postal Code From"});
   					return true;
       			} else if (sMsg.A.no(i).fromPostCd_A1.getValue().length() != 5) {
       				//NWAM0840E=0,[@] should be [@].
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                	bizMsg.A.no(errorLineNo).fromPostCd_A1.setErrorInfo(1, "NWAM0840E", new String[]{"Postal Code From", "5 Digits"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).toPostCd_A1)) {
       				//ZZM9000E=0,[@] field is mandatory.
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                	bizMsg.A.no(errorLineNo).toPostCd_A1.setErrorInfo(1, "ZZM9000E", new String[]{"Postal Code To"});
   					return true;
       			} else if (sMsg.A.no(i).toPostCd_A1.getValue().length() != 5) {
       				//NWAM0840E=0,[@] should be [@].
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                	bizMsg.A.no(errorLineNo).toPostCd_A1.setErrorInfo(1, "NWAM0840E", new String[]{"Postal Code To", "5 Digits"});
   					return true;
       			}
       			
       			int duplicatedCnt = 0;
       			int dupLineIdx = 0;
       			for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                    // START 2016/03/24 K.Kojima [UT#56,MOD]
                    // String screenkey =
                    // sMsg.A.no(i).defWhMapTmplCd_A1.getValue() +
                    // sMsg.A.no(i).mdseItemClsTpCd_A1.getValue()
                    // +
                    // getFlgValueFromFields(sMsg.A.no(i).thirdPtyItemFlg_A1.getValue())
                    // + sMsg.A.no(i).fromPostCd_A1.getValue() +
                    // sMsg.A.no(i).toPostCd_A1.getValue();
                    // String screenkey2 =
                    // sMsg.A.no(j).defWhMapTmplCd_A1.getValue() +
                    // sMsg.A.no(j).mdseItemClsTpCd_A1.getValue()
                    // +
                    // getFlgValueFromFields(sMsg.A.no(j).thirdPtyItemFlg_A1.getValue())
                    // + sMsg.A.no(j).fromPostCd_A1.getValue() +
                    // sMsg.A.no(j).toPostCd_A1.getValue();
                    // if (screenkey.equals(screenkey2)) {
                    // duplicatedCnt ++;
                    // dupLineIdx = j;
                    // }
                    String screenkey = sMsg.A.no(i).defWhMapTmplCd_A1.getValue() + sMsg.A.no(i).mdseItemClsTpCd_A1.getValue() + getFlgValueFromFields(sMsg.A.no(i).thirdPtyItemFlg_A1.getValue());
                    String screenkey2 = sMsg.A.no(j).defWhMapTmplCd_A1.getValue() + sMsg.A.no(j).mdseItemClsTpCd_A1.getValue() + getFlgValueFromFields(sMsg.A.no(j).thirdPtyItemFlg_A1.getValue());
                    if (screenkey.equals(screenkey2)
                            && !NWAL2040CommonLogic.checkPostCdDuplicated(sMsg.A.no(i).fromPostCd_A1.getValue(), sMsg.A.no(i).toPostCd_A1.getValue(), sMsg.A.no(j).fromPostCd_A1.getValue(), sMsg.A.no(j).toPostCd_A1.getValue())) {
                        duplicatedCnt++;
                        dupLineIdx = j;
                    }
                    // END 2016/03/24 K.Kojima [UT#56,MOD]
       			}
       			if (duplicatedCnt > 1) {
       				//NMAM0072E=0, @  is duplicated.
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, dupLineIdx);
                	bizMsg.A.no(errorLineNo).defWhMapTmplCd_A1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                	bizMsg.A.no(errorLineNo).mdseItemClsTpCd_A1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                	bizMsg.A.no(errorLineNo).thirdPtyItemFlg_A1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                	bizMsg.A.no(errorLineNo).fromPostCd_A1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                	bizMsg.A.no(errorLineNo).toPostCd_A1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
   					return true;
       			}
       			
       			
                //Mapping Template Avail Check
       			//QC#6258
	    		//String dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", getGlobalCompanyCode());
	    		String dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_PHYS_WH_CD", getGlobalCompanyCode());
                //WH-Sub WH Avail Check
       			//Primary
       			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1)) {
       				Map<String, Object> map = NWAL2040CommonLogic.getPhysWh(getGlobalCompanyCode(), sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1.getValue());
       				if (map == null || map.get("PHYS_WH_CD") == null) {
       					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
   	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
   	                	bizMsg.A.no(errorLineNo).otbdPrimOnHndChkWhCd_A1.setErrorInfo(1, "NDAM0007E", new String[]{"RTL_WH", "PHYS_WH_CD", sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1.getValue()});
   	   					return true;
       				}
       				
       				//In case of DROP SHIP WH(VAR_CHAR_CONST = "DROP_SHIP_RTL_WH_CD"), I: Internal is Error.
    	    		String ordLineSrcCatgCd = "";
    	    		if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1)) {
    	    			Map<String, Object> mapOrdLineSrc = NWAL2040CommonLogic.getInternalOrderLineSource(getGlobalCompanyCode(), sMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1.getValue());
    	    			if (mapOrdLineSrc != null) {
    	    				ordLineSrcCatgCd = (String) mapOrdLineSrc.get("ORD_LINE_SRC_CATG_CD");
    	    			}
    	    		}
    	    		if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1)
    	    				&& sMsg.A.no(i).otbdPrimOnHndChkWhCd_A1.getValue().equals(dropShipWhCd)
    	    				&& ORD_LINE_SRC_CATG.INTERNAL.equals(ordLineSrcCatgCd)) {
       					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       					//NWAM8251E=0,Can not use Drop Ship WH when Line Source Category is Internal.
   	                	bizMsg.A.no(errorLineNo).otbdPrimOnHndChkWhCd_A1.setErrorInfo(1, "NWAM8251E");
   	   					return true;
    	    		}
           			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_01)) {
           				Map<String, Object> mapSwh = NWAL2040CommonLogic.getRtlSwhFromName(getGlobalCompanyCode(), sMsg.A.no(i).rtlSwhNm_01.getValue());
           				if (mapSwh == null || mapSwh.get("RTL_SWH_CD") == null) {
           					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
       	                	bizMsg.A.no(errorLineNo).rtlSwhNm_01.setErrorInfo(1, "NDAM0007E", new String[]{"SWH", "RTL_SWH_NM", sMsg.A.no(i).rtlSwhNm_01.getValue()});
       	   					return true;
           				} else {
           					if (i < bizMsg.A.length()) {
	           					bizMsg.A.no(i).otbdPrimOnHndChkSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
           					}
           					sMsg.A.no(i).otbdPrimOnHndChkSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
           				}
           			}
       			} else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_01)) {
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       				//NLAM0127E=0,If the "@" field is entered, please enter the "@" field as well.
                	bizMsg.A.no(errorLineNo).otbdPrimOnHndChkWhCd_A1.setErrorInfo(1, "NLAM0127E", new String[]{"Sub Warehouse", "Warehouse"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_01)) {
       				sMsg.A.no(i).otbdPrimOnHndChkSwhCd_A1.clear();
       			}
       			
       			//Secondary
       			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdScdOnHndChkWhCd_A1)) {
       				Map<String, Object> map = NWAL2040CommonLogic.getPhysWh(getGlobalCompanyCode(), sMsg.A.no(i).otbdScdOnHndChkWhCd_A1.getValue());
       				if (map == null || map.get("PHYS_WH_CD") == null) {
       					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
   	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
   	                	bizMsg.A.no(errorLineNo).otbdScdOnHndChkWhCd_A1.setErrorInfo(1, "NDAM0007E", new String[]{"RTL_WH", "PHYS_WH_CD", sMsg.A.no(i).otbdScdOnHndChkWhCd_A1.getValue()});
   	   					return true;
       				}
       				
       				//In case of DROP SHIP WH(VAR_CHAR_CONST = "DROP_SHIP_RTL_WH_CD"), I: Internal is Error.
    	    		String ordLineSrcCatgCd = "";
    	    		if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdScdOnHndLineSrcCd_A1)) {
    	    			Map<String, Object> mapOrdLineSrc = NWAL2040CommonLogic.getInternalOrderLineSource(getGlobalCompanyCode(), sMsg.A.no(i).otbdScdOnHndLineSrcCd_A1.getValue());
    	    			if (mapOrdLineSrc != null) {
    	    				ordLineSrcCatgCd = (String) mapOrdLineSrc.get("ORD_LINE_SRC_CATG_CD");
    	    			}
    	    		}
    	    		if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdScdOnHndChkWhCd_A1)
    	    				&& sMsg.A.no(i).otbdScdOnHndChkWhCd_A1.getValue().equals(dropShipWhCd)
    	    				&& ORD_LINE_SRC_CATG.INTERNAL.equals(ordLineSrcCatgCd)) {
       					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       					//NWAM8251E=0,Can not use Drop Ship WH when Line Source Category is Internal.
   	                	bizMsg.A.no(errorLineNo).otbdScdOnHndChkWhCd_A1.setErrorInfo(1, "NWAM8251E");
   	   					return true;
    	    		}
    	    		
           			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_02)) {
           				Map<String, Object> mapSwh = NWAL2040CommonLogic.getRtlSwhFromName(getGlobalCompanyCode(), sMsg.A.no(i).rtlSwhNm_02.getValue());
           				if (mapSwh == null || mapSwh.get("RTL_SWH_CD") == null) {
           					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
       	                	bizMsg.A.no(errorLineNo).rtlSwhNm_02.setErrorInfo(1, "NDAM0007E", new String[]{"SWH", "RTL_SWH_NM", sMsg.A.no(i).rtlSwhNm_02.getValue()});
       	   					return true;
           				} else {
           					if (i < bizMsg.A.length()) {
	           					bizMsg.A.no(i).otbdScdOnHndChkSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
           					}
           					sMsg.A.no(i).otbdScdOnHndChkSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
           				}
           			}
       			} else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_02)) {
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       				//NLAM0127E=0,If the "@" field is entered, please enter the "@" field as well.
                	bizMsg.A.no(errorLineNo).otbdScdOnHndChkWhCd_A1.setErrorInfo(1, "NLAM0127E", new String[]{"Sub Warehouse", "Warehouse"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_02)) {
       				sMsg.A.no(i).otbdScdOnHndChkSwhCd_A1.clear();
       			}
       			
       			//Default
       			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdDefWhCd_A1)) {
       				Map<String, Object> map = NWAL2040CommonLogic.getPhysWh(getGlobalCompanyCode(), sMsg.A.no(i).otbdDefWhCd_A1.getValue());
       				if (map == null || map.get("PHYS_WH_CD") == null) {
       					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
   	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
   	                	bizMsg.A.no(errorLineNo).otbdDefWhCd_A1.setErrorInfo(1, "NDAM0007E", new String[]{"RTL_WH", "PHYS_WH_CD", sMsg.A.no(i).otbdDefWhCd_A1.getValue()});
   	   					return true;
       				}
       				
       				//In case of DROP SHIP WH(VAR_CHAR_CONST = "DROP_SHIP_RTL_WH_CD"), I: Internal is Error.
    	    		String ordLineSrcCatgCd = "";
    	    		if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdDefLineSrcCd_A1)) {
    	    			Map<String, Object> mapOrdLineSrc = NWAL2040CommonLogic.getInternalOrderLineSource(getGlobalCompanyCode(), sMsg.A.no(i).otbdDefLineSrcCd_A1.getValue());
    	    			if (mapOrdLineSrc != null) {
    	    				ordLineSrcCatgCd = (String) mapOrdLineSrc.get("ORD_LINE_SRC_CATG_CD");
    	    			}
    	    		}
    	    		if (ZYPCommonFunc.hasValue(sMsg.A.no(i).otbdDefWhCd_A1)
    	    				&& sMsg.A.no(i).otbdDefWhCd_A1.getValue().equals(dropShipWhCd)
    	    				&& ORD_LINE_SRC_CATG.INTERNAL.equals(ordLineSrcCatgCd)) {
       					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       					//NWAM8251E=0,Can not use Drop Ship WH when Line Source Category is Internal.
   	                	bizMsg.A.no(errorLineNo).otbdDefWhCd_A1.setErrorInfo(1, "NWAM8251E");
   	   					return true;
    	    		}
       				
           			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_03)) {
           				Map<String, Object> mapSwh = NWAL2040CommonLogic.getRtlSwhFromName(getGlobalCompanyCode(), sMsg.A.no(i).rtlSwhNm_03.getValue());
           				if (mapSwh == null || mapSwh.get("RTL_SWH_CD") == null) {
           					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
       	                	bizMsg.A.no(errorLineNo).rtlSwhNm_03.setErrorInfo(1, "NDAM0007E", new String[]{"SWH", "RTL_SWH_NM", sMsg.A.no(i).rtlSwhNm_03.getValue()});
       	   					return true;
           				} else {
           					if (i < bizMsg.A.length()) {
	           					bizMsg.A.no(i).otbdDefSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
           					}
           					sMsg.A.no(i).otbdDefSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
           				}
           			}
       			} else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_03)) {
   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
       				//NLAM0127E=0,If the "@" field is entered, please enter the "@" field as well.
                	bizMsg.A.no(errorLineNo).otbdDefWhCd_A1.setErrorInfo(1, "NLAM0127E", new String[]{"Sub Warehouse", "Warehouse"});
   					return true;
       			}
       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_03)) {
       				sMsg.A.no(i).otbdDefSwhCd_A1.clear();
       			}
       			
                // 2017/09/12 QC#19805 Del Start
       			//RMA
//       			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rmaDefWhCd_A1)) {
//       				Map<String, Object> map = NWAL2040CommonLogic.getPhysWh(getGlobalCompanyCode(), sMsg.A.no(i).rmaDefWhCd_A1.getValue());
//       				if (map == null || map.get("PHYS_WH_CD") == null) {
//       					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
//   	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
//   	                	bizMsg.A.no(errorLineNo).rmaDefWhCd_A1.setErrorInfo(1, "NDAM0007E", new String[]{"RTL_WH", "PHYS_WH_CD", sMsg.A.no(i).rmaDefWhCd_A1.getValue()});
//   	   					return true;
//       				}
//       				
//           			if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_04)) {
//           				Map<String, Object> mapSwh = NWAL2040CommonLogic.getRtlSwhFromName(getGlobalCompanyCode(), sMsg.A.no(i).rtlSwhNm_04.getValue());
//           				if (mapSwh == null || mapSwh.get("RTL_SWH_CD") == null) {
//           					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
//       	     	    		//NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
//       	                	bizMsg.A.no(errorLineNo).rtlSwhNm_04.setErrorInfo(1, "NDAM0007E", new String[]{"SWH", "RTL_SWH_NM", sMsg.A.no(i).rtlSwhNm_04.getValue()});
//       	   					return true;
//           				} else {
//           					if (i < bizMsg.A.length()) {
//	           					bizMsg.A.no(i).rmaDefSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
//           					}
//           					sMsg.A.no(i).rmaDefSwhCd_A1.setValue((String) mapSwh.get("RTL_SWH_CD"));
//           				}
//           			}
//       			} else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_04)) {
//   					int errorLineNo = NWAL2040CommonLogic.transferErrorPage(bizMsg, sMsg, i);
//       				//NLAM0127E=0,If the "@" field is entered, please enter the "@" field as well.
//                	bizMsg.A.no(errorLineNo).rmaDefWhCd_A1.setErrorInfo(1, "NLAM0127E", new String[]{"Sub Warehouse", "Warehouse"});
//   					return true;
//       			}
//       			if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_04)) {
//       				sMsg.A.no(i).rmaDefSwhCd_A1.clear();
//       			}
                // 2017/09/12 QC#19805 Del End
       			
                //WH-Source Avail Check
       			//In case of Drop, it can be selected source of Internal and External.
       			//else it can be selected source of External
                //Useful Source by Return
       			
       		}
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {

            for (int i = 0; i < sMsg.E.getValidCount(); i++) {
                //Mandatory Check
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).defWhMapTmplCd_E1)) {
                    //ZZM9000E=0,[@] field is mandatory.
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                    bizMsg.E.no(errorLineNo).defWhMapTmplCd_E1.setErrorInfo(1, "ZZM9000E", new String[]{"Mapping Template Name"});
                    return true;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).mdseItemClsTpCd_E1)) {
                    //ZZM9000E=0,[@] field is mandatory.
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                    bizMsg.E.no(errorLineNo).mdseItemClsTpCd_E1.setErrorInfo(1, "ZZM9000E", new String[]{"Item Sourcing Classification: Qualifier"});
                    return true;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).fromPostCd_E1)) {
                    //ZZM9000E=0,[@] field is mandatory.
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                    bizMsg.E.no(errorLineNo).fromPostCd_E1.setErrorInfo(1, "ZZM9000E", new String[]{"Postal Code From"});
                    return true;
                } else if (sMsg.E.no(i).fromPostCd_E1.getValue().length() != 5) {
                    //NWAM0840E=0,[@] should be [@].
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                    bizMsg.E.no(errorLineNo).fromPostCd_E1.setErrorInfo(1, "NWAM0840E", new String[]{"Postal Code From", "5 Digits"});
                    return true;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).toPostCd_E1)) {
                    //ZZM9000E=0,[@] field is mandatory.
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                    bizMsg.E.no(errorLineNo).toPostCd_E1.setErrorInfo(1, "ZZM9000E", new String[]{"Postal Code To"});
                    return true;
                } else if (sMsg.E.no(i).toPostCd_E1.getValue().length() != 5) {
                    //NWAM0840E=0,[@] should be [@].
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                    bizMsg.E.no(errorLineNo).toPostCd_E1.setErrorInfo(1, "NWAM0840E", new String[]{"Postal Code To", "5 Digits"});
                    return true;
                }

                //Conditional Mandatory Check
                if (ZYPCommonFunc.hasValue(sMsg.E.no(i).thirdPtyDspTpCd_E1) && ZYPCommonFunc.hasValue(sMsg.E.no(i).dropRtrnVndCd_E1)) {
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                    //NWAM0940E=0,Only one of RMA Disposition and Supplier can be set.
                    bizMsg.E.no(errorLineNo).thirdPtyDspTpCd_E1.setErrorInfo(1, "NWAM0940E");
                    bizMsg.E.no(errorLineNo).dropRtrnVndCd_E1.setErrorInfo(1, "NWAM0940E");
                    return true;
                }

                //Duplicated Check
                int duplicatedCnt = 0;
                int dupLineIdx = 0;
                for (int j = 0; j < sMsg.E.getValidCount(); j++) {
                    String screenkey = sMsg.E.no(i).defWhMapTmplCd_E1.getValue() + " - " + sMsg.E.no(i).mdseItemClsTpCd_E1.getValue() + " - " + getFlgValueFromFields(sMsg.E.no(i).thirdPtyItemFlg_E1.getValue())
                                        + " - " + sMsg.E.no(i).thirdPtyDspTpCd_E1.getValue() + " - " + sMsg.E.no(i).dropRtrnVndCd_E1.getValue();
                    String screenkey2 = sMsg.E.no(j).defWhMapTmplCd_E1.getValue() + " - " + sMsg.E.no(j).mdseItemClsTpCd_E1.getValue() + " - " + getFlgValueFromFields(sMsg.E.no(j).thirdPtyItemFlg_E1.getValue())
                                         + " - " + sMsg.E.no(j).thirdPtyDspTpCd_E1.getValue() + " - " + sMsg.E.no(j).dropRtrnVndCd_E1.getValue();
                    if (screenkey.equals(screenkey2)
                            && !NWAL2040CommonLogic.checkPostCdDuplicated(sMsg.E.no(i).fromPostCd_E1.getValue(), sMsg.E.no(i).toPostCd_E1.getValue(), sMsg.E.no(j).fromPostCd_E1.getValue(), sMsg.E.no(j).toPostCd_E1.getValue())) {
                        duplicatedCnt++;
                        dupLineIdx = j;
                    }
                }

                // 2017/09/25 QC#21369 Add Start
                //Duplicated Check(sMsg.G - Non Filter Records)
                int duplicatedCnt2 = 0;
                if (duplicatedCnt < 2) {
                    for (int j = 0; j < sMsg.G.getValidCount(); j++) {
                        String screenkey = sMsg.E.no(i).defWhMapTmplCd_E1.getValue() + " - " + sMsg.E.no(i).mdseItemClsTpCd_E1.getValue() + " - " + getFlgValueFromFields(sMsg.E.no(i).thirdPtyItemFlg_E1.getValue())
                                            + " - " + sMsg.E.no(i).thirdPtyDspTpCd_E1.getValue() + " - " + sMsg.E.no(i).dropRtrnVndCd_E1.getValue();
                        String screenkey2 = sMsg.G.no(j).defWhMapTmplCd_G1.getValue() + " - " + sMsg.G.no(j).mdseItemClsTpCd_G1.getValue() + " - " + getFlgValueFromFields(sMsg.G.no(j).thirdPtyItemFlg_G1.getValue())
                                             + " - " + sMsg.G.no(j).thirdPtyDspTpCd_G1.getValue() + " - " + sMsg.G.no(j).dropRtrnVndCd_G1.getValue();
                        if (screenkey.equals(screenkey2)
                                && !NWAL2040CommonLogic.checkPostCdDuplicated(sMsg.E.no(i).fromPostCd_E1.getValue(), sMsg.E.no(i).toPostCd_E1.getValue(), sMsg.G.no(j).fromPostCd_G1.getValue(), sMsg.G.no(j).toPostCd_G1.getValue())) {
                            if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).ordRmaDefWhPk_E1)
                                    || sMsg.E.no(i).ordRmaDefWhPk_E1.getValue().compareTo(sMsg.G.no(j).ordRmaDefWhPk_G1.getValue()) != 0) {
                                duplicatedCnt2++;
                                dupLineIdx = i;
                            }
                        }
                    }
                }
                // 2017/09/25 QC#21369 Add End

                // 2017/09/25 QC#21369 Mod Start
//                if (duplicatedCnt > 1) {
                if (duplicatedCnt > 1 || duplicatedCnt2 > 0) {
                // 2017/09/25 QC#21369 Mod End
                    //NMAM0072E=0, @  is duplicated.
                    int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, dupLineIdx);
                    bizMsg.E.no(errorLineNo).defWhMapTmplCd_E1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                    bizMsg.E.no(errorLineNo).mdseItemClsTpCd_E1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                    bizMsg.E.no(errorLineNo).thirdPtyItemFlg_E1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                    bizMsg.E.no(errorLineNo).fromPostCd_E1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                    bizMsg.E.no(errorLineNo).toPostCd_E1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                    bizMsg.E.no(errorLineNo).thirdPtyDspTpCd_E1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                    bizMsg.E.no(errorLineNo).dropRtrnVndCd_E1.setErrorInfo(1, "NMAM0072E", new String[]{"This line"});
                    return true;
                }

                //Master Check
                if (ZYPCommonFunc.hasValue(sMsg.E.no(i).rmaDefWhCd_E1)) {
                    Map<String, Object> map = NWAL2040CommonLogic.getPhysWh(getGlobalCompanyCode(), sMsg.E.no(i).rmaDefWhCd_E1.getValue());
                    if (map == null || map.get("PHYS_WH_CD") == null) {
                        int errorLineNo = NWAL2040CommonLogic.transferErrorPageRMA(bizMsg, sMsg, i);
                        //NDAM0007E=0,The corresponding data does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@]
                        bizMsg.E.no(errorLineNo).rmaDefWhCd_E1.setErrorInfo(1, "NDAM0007E", new String[]{"RTL_WH", "PHYS_WH_CD", sMsg.E.no(i).rmaDefWhCd_E1.getValue()});
                        return true;
                    }
                }
            }
        // 2017/09/12 QC#19805 Add End
        }
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }

    public static String getFlgValueFromFields(String flg) {
    	
    	return !ZYPCommonFunc.hasValue(flg) ? FLG_OFF_N : flg;
    	
    }
    
    public static String nullToBlank(String val) {
    	
    	if (val == null) {
    		return "";
    	}
    	return val;
    }
    
}
