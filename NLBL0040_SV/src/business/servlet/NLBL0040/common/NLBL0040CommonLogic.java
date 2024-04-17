package business.servlet.NLBL0040.common;

import java.util.List;

import parts.common.EZDDebugOutput;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

import business.servlet.NLBL0040.NLBL0040BMsg;
import business.servlet.NLBL0040.constant.NLBL0040Constant;

/**
 * <pre>
 * It is common processing in the screen application program of BusinessID NLBL0040. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/28   Fujitsu         D.Fukaya        Create          N/A
 * 2011/01/27   Fujitsu         K.Tajima        Update          1169
 * </pre>
 */
public class NLBL0040CommonLogic implements NLBL0040Constant {

    /**
     * The display control of the screen item.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0040BMsg
     */
    public static void cntrlDispScrnItem(EZDCommonHandler handler, NLBL0040BMsg scrnMsg) {
        EZDDebugOutput.println(1, "NLBL0040CommonLogic.cntrlScrnItemProtected================================START", handler);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        
        // set table background color
        setTableBGColor(scrnMsg);

        // ======================================================================================
        //  control display by user function
        // ======================================================================================
        boolean referenceFunctionExistFlg = false;
        boolean updateFunctionExistFlg = false;
        
        // Function that the user maintains is acquired. 
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {
            
            if (FUNCTION_REFERENCE.equals((String) functionList.get(i))) {
                referenceFunctionExistFlg = true;
            }
            
            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {
                updateFunctionExistFlg = true;
            }
        }
        
        // When the authority necessary for the screen is not maintained, it is assumed the system error. 
        if (!referenceFunctionExistFlg && !updateFunctionExistFlg) {
            // The system error is caused for an impossible case. 
            throw new S21AbendException("The user doesn't have functionID for NLBL0040.");
        }
        
        if (updateFunctionExistFlg) {
        	cntrlDispScrnItemForUpdateFunction(handler, scrnMsg);
         
        } else {
        	cntrlDispScrnItemForReferenceFunction(handler, scrnMsg);
        }

        EZDDebugOutput.println(1, "NLBL0040CommonLogic.cntrlScrnItemProtected================================END", handler);
    }

    /**
     * The display control of the screen item when the user has Function[Update].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0040BMsg
     */
    private static void cntrlDispScrnItemForUpdateFunction(EZDCommonHandler handler, NLBL0040BMsg scrnMsg) {
    	
    	cntrlDispEffPerForUpdateFunction(handler, scrnMsg);
    	cntrlDispTrnspLTTblForUpdateFunction(handler, scrnMsg);
    	cntrlDispDeleteInsertRowButtonForUpdateFunction(handler, scrnMsg);
    	
    	handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        
        
        if (scrnMsg.A.getValidCount() == 0) {

    		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DETAIL, false);
    		
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            
    	} else {
    		
    		if (ST_LIST_ALL_VALUE.equals(scrnMsg.stCd_G1.getValue())) {
    			
        		for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        			
        			scrnMsg.A.no(i).delyLeadAot_A1.setInputProtected(false);
        		}
        		
        	} else {
        		
        		for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        			
        			scrnMsg.A.no(i).delyLeadAot_A1.setInputProtected(true);
        		}
        	}
    		
    		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DETAIL, true);
//    		handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
    		
    		if (ST_LIST_ALL_VALUE.equals(scrnMsg.stCd_G1.getValue())) {
    		
    			handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
    			
    		} else {
    			
    			handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
    		}
    	}
    }
    
    /**
     * The display control of the Screen item[Effective Period] when the user has Function[Update].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0040BMsg
     */
    private static void cntrlDispEffPerForUpdateFunction(EZDCommonHandler handler, NLBL0040BMsg scrnMsg) {
        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_H2.setInputProtected(true);
        // 2013/05/21 R-OM025 Inventory Transaction Add End
        if (scrnMsg.A.getValidCount() == 0) {

            scrnMsg.effFromDt_L1.setInputProtected(true);
    		scrnMsg.effThruDt_L1.setInputProtected(true);
            
    	} else {
    		
    		if (ST_LIST_ALL_VALUE.equals(scrnMsg.stCd_G1.getValue())) {
    			
    			scrnMsg.effFromDt_L1.setInputProtected(false);
    			scrnMsg.effThruDt_L1.setInputProtected(false);
        		
    		} else {
    			
    			scrnMsg.effFromDt_L1.setInputProtected(true);
        		scrnMsg.effThruDt_L1.setInputProtected(true);
    		}
    	}
    }
        

    /**
     * The display control of table[TrnspLT] when the user has Function[Update].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0040BMsg
     */
    private static void cntrlDispTrnspLTTblForUpdateFunction(EZDCommonHandler handler, NLBL0040BMsg scrnMsg) {
    	
    	if (ST_LIST_ALL_VALUE.equals(scrnMsg.stCd_G1.getValue())) {
			
    		for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
    			
    			scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
    			scrnMsg.B.no(i).fromZipCd_B1.setInputProtected(false);
    			scrnMsg.B.no(i).toZipCd_B1.setInputProtected(false);
    			scrnMsg.B.no(i).trnspLtAot_B1.setInputProtected(false);
    		}
            
		} else {
			
			for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
				
				scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
    			scrnMsg.B.no(i).fromZipCd_B1.setInputProtected(true);
    			scrnMsg.B.no(i).toZipCd_B1.setInputProtected(true);
    			scrnMsg.B.no(i).trnspLtAot_B1.setInputProtected(true);
    		}
		}
    }
    
    /**
     * The display control of button[Delete Row],[Insert Row] when the user has Function[Update].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0040BMsg
     */
    private static void cntrlDispDeleteInsertRowButtonForUpdateFunction(EZDCommonHandler handler, NLBL0040BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() == 0) {
    		
    		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, false);
    		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, false);
    		
    	} else {

    		if (ZYPCommonFunc.hasValue(scrnMsg.stNm_R1)) {
    		
        		if (ST_LIST_ALL_VALUE.equals(scrnMsg.stCd_G1.getValue())) {
        			
    				if (scrnMsg.B.getValidCount() > 0) {
    					
    					handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, true);
    					
    				} else {
    					
    					handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, false);
    				}
    				
    				
    				if (scrnMsg.xxPageShowOfNum_B1.getValueInt() < LIST_MAX_NUM) {

    					handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, true);
                		
        			} else {
        				
        				handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, false);
        			}

        		} else {
        			
        			handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, false);
            		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, false);
        		}
    			
    		} else {
    			
    			handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DELETE_ROW, false);
	    		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_INSERT_ROW, false);
    		}
    	}
    }
    
    /**
     * The display control of the screen item when the user has only Function[Reference].
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL0040BMsg
     */
    private static void cntrlDispScrnItemForReferenceFunction(EZDCommonHandler handler, NLBL0040BMsg scrnMsg) {
    	
    	EZDGUIAttribute btnDeleteRow = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_BTN_DELETE_ROW);
        EZDGUIAttribute btnInsertRow = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_BTN_INSERT_ROW);
        EZDGUIAttribute btnUpload = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_BTN_UPLOAD);

        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        scrnMsg.locNm_H2.setInputProtected(true);
        // 2013/05/21 R-OM025 Inventory Transaction Add End
        scrnMsg.effFromDt_L1.setInputProtected(true);
		scrnMsg.effThruDt_L1.setInputProtected(true);
        
		btnDeleteRow.setVisibility(false);
		btnInsertRow.setVisibility(false);
		btnUpload.setVisibility(false);
		scrnMsg.addGUIAttribute(btnDeleteRow);
		scrnMsg.addGUIAttribute(btnInsertRow);
		scrnMsg.addGUIAttribute(btnUpload);
		
		handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        
    	if (scrnMsg.A.getValidCount() == 0) {
    		
    		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DETAIL, false);
    		
    		handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
    		
    	} else {
    		
    		for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
    			
    			scrnMsg.A.no(i).delyLeadAot_A1.setInputProtected(true);
    		}
    		
    		handler.setButtonEnabled(HTML_NAME_VALUE_BTN_DETAIL, true);
    		
//    		handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
    		
    		if (scrnMsg.B.getValidCount() == 0) {
    			// There is no processing.
        		
    		} else {
    			
        		for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
        			
        			EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, HTML_ID_VALUE_CHKBOX + Integer.toString(i));
                    guiAttr.setVisibility(false);
                    scrnMsg.addGUIAttribute(guiAttr);
                    
        			scrnMsg.B.no(i).fromZipCd_B1.setInputProtected(true);
        			scrnMsg.B.no(i).toZipCd_B1.setInputProtected(true);
        			scrnMsg.B.no(i).trnspLtAot_B1.setInputProtected(true);
        		}
    		}
    	}
    }
    
    /**
     * The display control in the table of Area Lead Time. 
     * @param scrnMsg NLBL0040BMsg
     */
    public static void cntrlDispAreaLeadTimeTable(NLBL0040BMsg scrnMsg) {

        String prevRecordSTCd = "";
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (prevRecordSTCd.equals(scrnMsg.A.no(i).stCd_A1.getValue())) {
            	
            	scrnMsg.A.no(i).stNm_A1.clear();
            	
            } else {
            	
            	prevRecordSTCd = scrnMsg.A.no(i).stCd_A1.getValue();
            }
        }
    }
    
    /**
     * set table background color
     * @param scrnMsg NLBL0040BMsg
     */
    private static void setTableBGColor(NLBL0040BMsg scrnMsg) {
    	
    	if (scrnMsg.A.getValidCount() > 0) {
    		
    		S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
    		tblColor.setAlternateRowsBG(HTML_ID_VALUE_LEFT_TABLE, scrnMsg.A);
    	}
    	
    	if (scrnMsg.B.getValidCount() > 0) {
    		
    		S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
    		tblColor.setAlternateRowsBG(HTML_ID_VALUE_RIGHT_TABLE, scrnMsg.B);
    	}
    }
    
    /**
     * get pagenation table name and set to BMsg.
     * @param scrnMsg NLBL0040BMsg
     * @param ctx EZDApplicationContext
     */
    public static void getPagenationTableNm(NLBL0040BMsg scrnMsg, EZDApplicationContext ctx) {

        HttpDispatchContext httpCtx = (HttpDispatchContext) ctx.getDispatchContext();
        Parameters params = httpCtx.getParameters();
        String xxPagenationTableNm = params.getSingleValue(PAGING_TABLE_NAME);
        scrnMsg.xxTblNm.setValue(xxPagenationTableNm);
    }
    
    /**
     * left table attribute check proccess.
     * @param scrnMsg NLBL0040BMsg
     */
    public static void checkAttributeOfLeftTable(NLBL0040BMsg scrnMsg) {
    	
    	for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
			
			scrnMsg.addCheckItem(scrnMsg.A.no(i).delyLeadAot_A1);
		}
    }
    
    /**
     * right table attribute check proccess.
     * @param scrnMsg NLBL0040BMsg
     */
    public static void checkAttributeOfRightTable(NLBL0040BMsg scrnMsg) {
    	
    	for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
			
			scrnMsg.addCheckItem(scrnMsg.B.no(i).fromZipCd_B1);
			scrnMsg.addCheckItem(scrnMsg.B.no(i).toZipCd_B1);
			scrnMsg.addCheckItem(scrnMsg.B.no(i).trnspLtAot_B1);
		}
    }
    
    /**
     * When the Search button is pressed, 
     * it is confirmed whether the display of the confirming message is necessary. 
     * @param scrnMsg NLBL0040BMsg
     * @return boolean
     */
    public static boolean checkSearchButtonConfirmMsgNecessary(NLBL0040BMsg scrnMsg) {
    	
    	boolean updateFunctionExistFlg = false;
        
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {
            
            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {
            	
                updateFunctionExistFlg = true;
            }
        }
        
    	if (updateFunctionExistFlg &&
    			ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxConfMsgAlrdyDplyFlg_G1.getValue()) &&
				scrnMsg.A.getValidCount() > 0 &&
				ST_LIST_ALL_VALUE.equals(scrnMsg.stCd_G1.getValue())) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    /**
     * When the Detail button is pressed, 
     * it is confirmed whether the display of the confirming message is necessary. 
     * @param scrnMsg NLBL0040BMsg
     * @return boolean
     */
    public static boolean checkDetailButtonConfirmMsgNecessary(NLBL0040BMsg scrnMsg) {
    	
    	boolean updateFunctionExistFlg = false;
        
        // Function that the user maintains is acquired. 
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {
            
            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {
                updateFunctionExistFlg = true;
            }
        }
        
    	if (updateFunctionExistFlg &&
    			ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxConfMsgAlrdyDplyFlg_G2.getValue()) &&
    			ZYPCommonFunc.hasValue(scrnMsg.stNm_R1) &&
    			ST_LIST_ALL_VALUE.equals(scrnMsg.stCd_G1.getValue())) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    /**
     * The focus control after the event that processes to TrnspLTTbl. 
     * @param scrnMsg NLBL0040BMsg
     * @return boolean
     */
    public static void setFocusForTrnspLTTbl(NLBL0040BMsg scrnMsg) {
    	
		boolean updateFunctionExistFlg = false;
        
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getFunctionCodeListForBizAppId(BIZ_APP_ID);

        for (int i = 0; i < functionList.size(); i++) {
            
            if (FUNCTION_UPDATE.equals((String) functionList.get(i))) {
            	
                updateFunctionExistFlg = true;
            }
        }
        
        if (updateFunctionExistFlg) {

        	if (scrnMsg.B.getValidCount() > 0) {
            	
            	scrnMsg.setFocusItem(scrnMsg.B.no(0).xxChkBox_B1);
            	
            } else {
            	
            	scrnMsg.setFocusItem(scrnMsg.whCd_H2);
            }
         
        } else {
        	
        	scrnMsg.setFocusItem(scrnMsg.whCd_H2);
        }
	}
}
