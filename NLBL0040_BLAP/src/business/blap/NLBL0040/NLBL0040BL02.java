package business.blap.NLBL0040;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL0040.common.NLBL0040CommonLogic;
import business.blap.NLBL0040.constant.NLBL0040Constant;
import business.db.RTL_WHTMsg;
import business.file.NLBL0040F00FMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 * This class does search business process of BusinessID NLBL0040.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/20   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/13   Fujitsu         H.Mizutani       Update          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0040BL02 extends S21BusinessHandler implements
		NLBL0040Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            sMsg.clearErrorInfo();
            
            if (EVENT_NM_NLBL0040_INIT.equals(screenAplID)) {
                doProcess_NLBL0040_INIT((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);

            // 2013/05/21 R-OM025 Inventory Transaction Delete Start
            //} else if (EVENT_NM_NLBL0040SCRN00_ONCHANGE_WH.equals(screenAplID)) {
            //    doProcess_NLBL0040Scrn00_OnChange_WH((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            // 2013/05/21 R-OM025 Inventory Transaction Delete End

            // 2013/05/21 R-OM025 Inventory Transaction Add Start
            } else if (EVENT_NM_NLBL0040SCRN00_ONCLICK_REFLESH.equals(screenAplID)) {
                doProcess_NLBL0040Scrn00_OnClick_Reflesh((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            // 2013/05/21 R-OM025 Inventory Transaction Add End
                
            } else if (EVENT_NM_NLBL0040SCRN00_ONCLICK_SEARCH.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_OnClick_Search((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            	
            } else if (EVENT_NM_NLBL0040SCRN00_ONCLICK_DETAIL.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_OnClick_Detail((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            	
            } else if (EVENT_NM_NLBL0040SCRN00_PAGEPREV.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_PagePrev((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            	
            } else if (EVENT_NM_NLBL0040SCRN00_PAGENEXT.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_PageNext((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            	
            } else if (EVENT_NM_NLBL0040SCRN00_ONCLICK_DELETEROW.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_OnClick_DeleteRow((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);

            } else if (EVENT_NM_NLBL0040SCRN00_ONCLICK_INSERTROW.equals(screenAplID)) {
                doProcess_NLBL0040Scrn00_OnClick_InsertRow((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
                
            } else if (EVENT_NM_NLBL0040SCRN00_ZYPL0210.equals(screenAplID)) {
                doProcess_NLBL0040Scrn00_ZYPL0210((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
                
            } else if (EVENT_NM_NLBL0040SCRN00_CMN_SUBMIT.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_CMN_Submit((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            	
            } else if (EVENT_NM_NLBL0040SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_CMN_Download((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            	
            } else if (EVENT_NM_NLBL0040SCRN00_CMN_RESET.equals(screenAplID)) {
            	doProcess_NLBL0040Scrn00_CMN_Reset((NLBL0040CMsg) cMsg, (NLBL0040SMsg) sMsg);
            	
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when the initial display event is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040_INIT(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLBL0040_INIT================================START", this);

        cMsg.clear();
        sMsg.clear();
        
        cMsg.glblCmpyCd_G1.setValue(getGlobalCompanyCode());
        sMsg.glblCmpyCd_G1.setValue(getGlobalCompanyCode());
        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        // Set Location Role Type Code List
        NLBL0040CMsg bizMsg = (NLBL0040CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_P2,
                NLBL0040CommonLogic.getLocRoleTpForPopup(getGlobalCompanyCode()));
        // 2013/05/21 R-OM025 Inventory Transaction Add End

        NLBL0040CommonLogic.init(cMsg, sMsg);
        
        EZDDebugOutput.println(1, "doProcess_NLBL0040_INIT================================END", this);
    }
    

    /**
     * The method explanation: It is a method of the execution when the event[OnChange_WH] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_OnClick_Reflesh(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnChange_WH================================START", this);
        

        cMsg.effFromDt_H1.clear();
        cMsg.xxEdtCdNm_H2.clear();
        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        // Location Check
        // NLBL0040CMsg bizMsg = (NLBL0040CMsg) cMsg;
        // 10/06/2015 mod start
        /*
        if (!isLocCdCheck(bizMsg)) {
            return;
        }
        */
        // Refresh location name
        refreshHeaderWhName(cMsg, sMsg);
        // 10/06/2015 mod end
        // 2013/05/21 R-OM025 Inventory Transaction Add End
        
        // =============================================
        // Making of Effective Period pull-down list
        // =============================================
        NLBL0040CommonLogic.setEffPerList(cMsg);

        EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnChange_WH================================END", this);
    }
    

    /**
     * The method explanation: It is a method of the execution when the event[OnClick_Seach] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_OnClick_Search(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_Seach================================START", this);
    	
    	// 2013/05/21 R-OM025 Inventory Transaction Add Start
        // Location Check
        // NLBL0040CMsg bizMsg = (NLBL0040CMsg) cMsg;
        // 10/06/2015 mod start
        /*
        if (!isLocCdCheck(bizMsg)) {
            return;
        }
        */
        // Refresh location name
        refreshHeaderWhName(cMsg, sMsg);
        // 10/06/2015 mod end
        // 2013/05/21 R-OM025 Inventory Transaction Add End
    	
    	
    	cMsg.xxRadioBtn_A1.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        
        cMsg.xxPageShowFromNum_A1.setValue(0);
        cMsg.xxPageShowToNum_A1.setValue(0);
        cMsg.xxPageShowOfNum_A1.setValue(0);
        cMsg.xxPageShowFromNum_B1.setValue(0);
        cMsg.xxPageShowToNum_B1.setValue(0);
        cMsg.xxPageShowOfNum_B1.setValue(0);
        
        cMsg.stNm_R1.clear();
        cMsg.shpgModeNm_R1.clear();
        cMsg.delyLeadAot_R1.clear();
        
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
    	sMsg.S.clear();
        sMsg.S.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
                
        EZDMsg.copy(cMsg, null, sMsg, null);
        
        // keep search condition data
        ZYPEZDItemValueSetter.setValue(cMsg.whCd_G1, cMsg.whCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_G1, cMsg.effFromDt_H2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.stCd_G1, cMsg.stCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.whCd_G1, cMsg.whCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.effFromDt_G1, cMsg.effFromDt_H2.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.stCd_G1, cMsg.stCd_H2.getValue());
        NLBL0040CommonLogic.setEffPerListFromCMsgToSMsg(cMsg, sMsg);
        
        S21SsmEZDResult ssmResult;
        // search for display
        if (ZYPCommonFunc.hasValue(sMsg.effFromDt_G1)) {
        	
        	ssmResult = NLBL0040Query.getInstance().getAreaLeadTmList(sMsg);
        	
        } else {
        	
        	ssmResult = NLBL0040Query.getInstance().getAreaLeadTmListNoValue(sMsg);
        }

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
                queryResCnt = sMsg.A.length();

                // Setting of message when the maximum acquisition number is exceeded
                cMsg.setMessageInfo(NZZM0001W);
            }

            int i = 0;
            boolean effPerSetAlreadyFlg = false;
            
            for (; i < sMsg.A.getValidCount(); i++) {
            	
                if (i == cMsg.A.length()) {
                    break;
                }
                
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                
                if (effPerSetAlreadyFlg == false && ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
            		
                	// set data for display
            		cMsg.effFromDt_L1.setValue(sMsg.A.no(i).effFromDt_A1.getValue());
                	cMsg.effThruDt_L1.setValue(sMsg.A.no(i).effThruDt_A1.getValue());
                	sMsg.effFromDt_L1.setValue(sMsg.A.no(i).effFromDt_A1.getValue());
                	sMsg.effThruDt_L1.setValue(sMsg.A.no(i).effThruDt_A1.getValue());
                	
                	// keep search condition data(to be used when onclick_detail)
                	cMsg.effThruDt_G1.setValue(sMsg.A.no(i).effThruDt_A1.getValue());
                	sMsg.effThruDt_G1.setValue(sMsg.A.no(i).effThruDt_A1.getValue());
                	
                	effPerSetAlreadyFlg = true;
            	}
            }
            
            cMsg.A.setValidCount(i);
            cMsg.xxPageShowFromNum_A1.setValue(1);
            cMsg.xxPageShowToNum_A1.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A1.setValue(queryResCnt);

            // search snapshot data (This data is used when submit event is generated.)
            NLBL0040Query.getInstance().getEffFromToListForSearchSnapshot(sMsg);
            NLBL0040Query.getInstance().getAreaLeadTmListForSearchSnapshot(sMsg);

            cMsg.setMessageInfo(NZZM0002I);
            
        } else {
			// When there is no retrieval result
            cMsg.setMessageInfo(NZZM0000E);
        }
        
        EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_Seach================================END", this);
    }
    

    /**
     * The method explanation: It is a method of the execution when the event[OnClick_Detail] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_OnClick_Detail(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_Detail================================START", this);
    	
    	cMsg.xxPageShowFromNum_B1.setValue(0);
        cMsg.xxPageShowToNum_B1.setValue(0);
        cMsg.xxPageShowOfNum_B1.setValue(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        
        sMsg.xxPageShowFromNum_B1.setValue(0);
        sMsg.xxPageShowToNum_B1.setValue(0);
        sMsg.xxPageShowOfNum_B1.setValue(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        
        int index = cMsg.xxRadioBtn_A1.getValueInt();
        
        // keep search condition data
        sMsg.stCd_G2.setValue(cMsg.A.no(index).stCd_A1.getValue());
        sMsg.shpgModeCd_G2.setValue(cMsg.A.no(index).shpgModeCd_A1.getValue());
        
        // set value for search condition
        NLBL0040CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        
        // set display data
        cMsg.stNm_R1.setValue(sMsg.A.no(index + cMsg.xxPageShowFromNum_A1.getValueInt() - 1).stNm_A1.getValue());
        cMsg.shpgModeNm_R1.setValue(sMsg.A.no(index + cMsg.xxPageShowFromNum_A1.getValueInt() - 1).shpgModeNm_A1.getValue());
        cMsg.delyLeadAot_R1.setValue(sMsg.A.no(index + cMsg.xxPageShowFromNum_A1.getValueInt() - 1).delyLeadAot_A1.getValue());
        
        // search data for display
        if (ZYPCommonFunc.hasValue(sMsg.effFromDt_G1)) {
        	
        	S21SsmEZDResult ssmResult = NLBL0040Query.getInstance().getTrnspLtList(sMsg);
        	
        	if (ssmResult.isCodeNormal()) {

                int queryResCnt = ssmResult.getQueryResultCount();

                if (ssmResult.getQueryResultCount() > sMsg.B.length()) {
                    queryResCnt = sMsg.B.length();

                    // Setting of message when the maximum acquisition number is exceeded
                    cMsg.setMessageInfo(NZZM0001W);
                }

                int j = 0;
                for (; j < sMsg.B.getValidCount(); j++) {
                	
                    if (j == cMsg.B.length()) {
                    	
                        break;
                    }
                    
                    EZDMsg.copy(sMsg.B.no(j), null, cMsg.B.no(j), null);
                }
                
                cMsg.B.setValidCount(j);
                cMsg.xxPageShowFromNum_B1.setValue(1);
                cMsg.xxPageShowToNum_B1.setValue(cMsg.B.getValidCount());
                cMsg.xxPageShowOfNum_B1.setValue(queryResCnt);
                
                cMsg.setMessageInfo(NZZM0002I);
                
            } else {
    			// When there is no retrieval result
                cMsg.setMessageInfo(NLBM0066W);
            }
        } else {
        	
        	// There is no processing.
        }
        
        // search snapshot data (This data is used when submit event is generated.)
        NLBL0040Query.getInstance().getTrnspLtListForDetailSnapshot(sMsg);
        
        EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_Detail================================END", this);
    }
    

    /**
     * The method explanation: It is a method of the execution when the event[PagePrev] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_PagePrev(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	
        // set value for search condition
        NLBL0040CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        
    	if (LEFT_TABLE_NAME.equals(cMsg.xxTblNm.getValue())) {
            
    		NLBL0040CommonLogic.copyAreaLeadTimeTblDataFromCMsgToSMsg(cMsg, sMsg);

            int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();

            int prevPagenationFrom = pagenationFrom - cMsg.A.length();
            int prevPagenationTo = pagenationFrom - 1;

            for (int j = prevPagenationFrom; j < prevPagenationFrom + cMsg.A.length(); j++) {
                EZDMsg.copy(sMsg.A.no(j-1), null, cMsg.A.no(j - prevPagenationFrom), null);
            }

            cMsg.A.setValidCount(cMsg.A.length());

            cMsg.xxPageShowFromNum_A1.setValue(prevPagenationFrom);
            cMsg.xxPageShowToNum_A1.setValue(prevPagenationTo);
            
    	} else {
            
    		NLBL0040CommonLogic.copyTrnspLtTblDataFromCMsgToSMsg(cMsg, sMsg);
            
            int pagenationFrom = cMsg.xxPageShowFromNum_B1.getValueInt();
            
            int prevPagenationFrom = pagenationFrom - cMsg.B.length();
            int prevPagenationTo = pagenationFrom - 1;

            for (int j = prevPagenationFrom; j < prevPagenationFrom + cMsg.B.length(); j++) {
                EZDMsg.copy(sMsg.B.no(j-1), null, cMsg.B.no(j - prevPagenationFrom), null);
            }

            cMsg.B.setValidCount(cMsg.B.length());

            cMsg.xxPageShowFromNum_B1.setValue(prevPagenationFrom);
            cMsg.xxPageShowToNum_B1.setValue(prevPagenationTo);
    	}
    	
    }
    
    /**
     * The method explanation: It is a method of the execution when the event[PageNext] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_PageNext(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        // set value for search condition
        NLBL0040CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        
    	if (LEFT_TABLE_NAME.equals(cMsg.xxTblNm.getValue())) {

    		NLBL0040CommonLogic.copyAreaLeadTimeTblDataFromCMsgToSMsg(cMsg, sMsg);

            int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
            int nextPagenationFrom = pagenationFrom + cMsg.A.length();

            int validCount = 0;
            for (int j = nextPagenationFrom; j < nextPagenationFrom + cMsg.A.length(); j++) {
                
                EZDMsg.copy(sMsg.A.no(j-1), null, cMsg.A.no(j - nextPagenationFrom), null);

                validCount++;
                
    			if (j == sMsg.A.getValidCount()) {
    				break;
    			} 
            }

            cMsg.A.setValidCount(validCount);

            cMsg.xxPageShowFromNum_A1.setValue(nextPagenationFrom);
            cMsg.xxPageShowToNum_A1.setValue(nextPagenationFrom + validCount -1);
            
    	} else {

    		NLBL0040CommonLogic.copyTrnspLtTblDataFromCMsgToSMsg(cMsg, sMsg);
            
            int pagenationFrom = cMsg.xxPageShowFromNum_B1.getValueInt();
                        
            int nextPagenationFrom = pagenationFrom + cMsg.B.length();

            int validCount = 0;
            for (int j = nextPagenationFrom; j < nextPagenationFrom + cMsg.B.length(); j++) {
                
                EZDMsg.copy(sMsg.B.no(j-1), null, cMsg.B.no(j - nextPagenationFrom), null);

                validCount++;
                
    			if (j == sMsg.B.getValidCount()) {
    				break;
    			} 
            }

            cMsg.B.setValidCount(validCount);

            cMsg.xxPageShowFromNum_B1.setValue(nextPagenationFrom);
            cMsg.xxPageShowToNum_B1.setValue(nextPagenationFrom + validCount -1);
    	}
    	
    }
    
	/**
     * The method explanation: It is a method of the execution when the event[OnClick_DeleteRow] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_OnClick_DeleteRow(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_DeleteRow================================START", this);

        // set value for search condition
        NLBL0040CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        
        NLBL0040CommonLogic.copyTrnspLtTblDataFromCMsgToSMsg(cMsg, sMsg);
        
        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(sMsg.B, HTML_NAME_VALUE_CHKBOX, ZYPConstant.CHKBOX_ON_Y);
        
        for (int i = 0; i < chkYList.size(); i++) {
        	
        	int rowIndex = chkYList.get(i).intValue();
      		sMsg.B.no(rowIndex).ezCancelFlag_B1.setValue(ZYPConstant.FLG_ON_1);
        }
      
        int newRecordCount = 0;
        for (int oldRecordCount = 0; oldRecordCount < sMsg.B.getValidCount(); oldRecordCount++) {
        	
        	if ((ZYPConstant.FLG_OFF_0.equals(sMsg.B.no(oldRecordCount).ezCancelFlag_B1.getValue()))) {
        		
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).xxChkBox_Z1,sMsg.B.no(oldRecordCount).xxChkBox_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).fromZipCd_Z1,sMsg.B.no(oldRecordCount).fromZipCd_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).toZipCd_Z1,sMsg.B.no(oldRecordCount).toZipCd_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).trnspLtAot_Z1,sMsg.B.no(oldRecordCount).trnspLtAot_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).ezCancelFlag_Z1,sMsg.B.no(oldRecordCount).ezCancelFlag_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).stCd_Z1,sMsg.B.no(oldRecordCount).stCd_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).shpgModeCd_Z1,sMsg.B.no(oldRecordCount).shpgModeCd_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).zipSqNum_Z1,sMsg.B.no(oldRecordCount).zipSqNum_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).effFromDt_Z1,sMsg.B.no(oldRecordCount).effFromDt_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).ezUpTime_Z1,sMsg.B.no(oldRecordCount).ezUpTime_B1);
        		ZYPEZDItemValueSetter.setValue(sMsg.Z.no(newRecordCount).ezUpTimeZone_Z1,sMsg.B.no(oldRecordCount).ezUpTimeZone_B1);
        		
        		newRecordCount++;
        	}
        }
        sMsg.Z.setValidCount(newRecordCount);

        sMsg.B.clear();
        cMsg.B.clear();
        
        for (int i = 0; i < sMsg.Z.getValidCount(); i++) {
        		
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B1,sMsg.Z.no(i).xxChkBox_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).fromZipCd_B1,sMsg.Z.no(i).fromZipCd_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).toZipCd_B1,sMsg.Z.no(i).toZipCd_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).trnspLtAot_B1,sMsg.Z.no(i).trnspLtAot_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezCancelFlag_B1,sMsg.Z.no(i).ezCancelFlag_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).stCd_B1,sMsg.Z.no(i).stCd_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).shpgModeCd_B1,sMsg.Z.no(i).shpgModeCd_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).zipSqNum_B1,sMsg.Z.no(i).zipSqNum_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).effFromDt_B1,sMsg.Z.no(i).effFromDt_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTime_B1,sMsg.Z.no(i).ezUpTime_Z1);
    		ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTimeZone_B1,sMsg.Z.no(i).ezUpTimeZone_Z1);
        }
        sMsg.B.setValidCount(sMsg.Z.getValidCount());

        sMsg.Z.clear();
        
        if (newRecordCount == 0) {
        	
        	cMsg.B.setValidCount(0);
        	cMsg.xxPageShowFromNum_B1.setValue(0);
            cMsg.xxPageShowToNum_B1.setValue(0);
            cMsg.xxPageShowOfNum_B1.setValue(0);
            
        } else {
        	
        	int pagenationFrom;
        	
        	if (newRecordCount < cMsg.xxPageShowFromNum_B1.getValueInt()) {
        		
        		pagenationFrom = cMsg.xxPageShowFromNum_B1.getValueInt() - cMsg.B.length();
        		
        	} else {
        		
        		pagenationFrom = cMsg.xxPageShowFromNum_B1.getValueInt();
        	}
        	
        	int validCountScreen = 0;
        	
            for (int i = pagenationFrom; i < pagenationFrom + cMsg.B.length(); i++) {
                
                EZDMsg.copy(sMsg.B.no(i-1), null, cMsg.B.no(i - pagenationFrom), null);

                validCountScreen++;
                
				if (i == sMsg.B.getValidCount()) {
					break;
				} 
            }

            cMsg.B.setValidCount(validCountScreen);
            cMsg.xxPageShowFromNum_B1.setValue(pagenationFrom);
            cMsg.xxPageShowToNum_B1.setValue(pagenationFrom + validCountScreen -1);
            cMsg.xxPageShowOfNum_B1.setValue(newRecordCount);            
        }
        
    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_DeleteRow================================END", this);
    }

    /**
     * The method explanation: It is a method of the execution when the event[OnClick_InsertRow] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_OnClick_InsertRow(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_InsertRow================================START", this);

        // set value for search condition
        NLBL0040CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        
    	NLBL0040CommonLogic.copyTrnspLtTblDataFromCMsgToSMsg(cMsg, sMsg);
    	
    	// Numbering of ZIP_SQ_NUM
    	int maxZipSqNum = 0;
    	int tmpZipSqNum;
    	for (int i = 0; i < sMsg.B.getValidCount(); i++) {
    		
    		tmpZipSqNum = Integer.parseInt(sMsg.B.no(i).zipSqNum_B1.getValue());
    		
    		if (maxZipSqNum < tmpZipSqNum) {
    			
    			maxZipSqNum = tmpZipSqNum;
    		}
    	}
    	String newZipSqNum = Integer.toString(maxZipSqNum + 1);
    	
    	// setting of new record
    	int newRecordCount = sMsg.B.getValidCount() + 1;
    	sMsg.B.setValidCount(newRecordCount);
    	sMsg.B.no(newRecordCount - 1).zipSqNum_B1.setValue(newZipSqNum);
    	sMsg.B.no(newRecordCount - 1).ezCancelFlag_B1.setValue(ZYPConstant.FLG_OFF_0);
    	
    	// set last page data to BMsg
    	cMsg.B.clear();
    	int lastPageNum = (newRecordCount - 1) / cMsg.B.length();
    	int pagenationFrom = cMsg.B.length() * lastPageNum + 1;
        int validCount = 0;
        
        for (int i = pagenationFrom; i < pagenationFrom + cMsg.B.length(); i++) {
            
            EZDMsg.copy(sMsg.B.no(i-1), null, cMsg.B.no(i - pagenationFrom), null);

            validCount++;
            
			if (i == sMsg.B.getValidCount()) {
				break;
			} 
        }

    	// setting pagenation data
        cMsg.B.setValidCount(validCount);
        cMsg.xxPageShowFromNum_B1.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_B1.setValue(pagenationFrom + validCount -1);
        cMsg.xxPageShowOfNum_B1.setValue(newRecordCount);

    	EZDDebugOutput.println(1, "doProcess_NLBL0040Scrn00_OnClick_InsertRow================================END", this);
    }
    
	
    /**
     * The method explanation: It is a method of the execution when the event return from
     * popup[ZYPL0210] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_ZYPL0210(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

    	if (sMsg.A.getValidCount() > 0) {

            // set value for search condition
            NLBL0040CommonLogic.setSearchConditionItemValue(cMsg, sMsg);	
    	}
    }
    
    
    /**
     * The method explanation: It is a method of the execution when the event[OnClick_Submit] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_CMN_Submit(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	
    	cMsg.effFromDt_H1.clear();
    	cMsg.xxEdtCdNm_H2.clear();
    	cMsg.effFromDt_H2.clear();
    	
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        
        cMsg.xxPageShowFromNum_A1.setValue(0);
        cMsg.xxPageShowToNum_A1.setValue(0);
        cMsg.xxPageShowOfNum_A1.setValue(0);
        cMsg.xxPageShowFromNum_B1.setValue(0);
        cMsg.xxPageShowToNum_B1.setValue(0);
        cMsg.xxPageShowOfNum_B1.setValue(0);
        
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
    	sMsg.S.clear();
        sMsg.S.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        
        sMsg.xxPageShowFromNum_A1.setValue(0);
        sMsg.xxPageShowToNum_A1.setValue(0);
        sMsg.xxPageShowOfNum_A1.setValue(0);
        sMsg.xxPageShowFromNum_B1.setValue(0);
        sMsg.xxPageShowToNum_B1.setValue(0);
        sMsg.xxPageShowOfNum_B1.setValue(0);

    	// set data for display
        NLBL0040CommonLogic.setEffPerList(cMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.whCd_H2, sMsg.whCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_H2, sMsg.effFromDt_L1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.stCd_H2, sMsg.stCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_L1, sMsg.effFromDt_L1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_L1, sMsg.effThruDt_L1.getValue());
        
    	// keep search condition data(to be used when onclick_detail)
    	ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_G1, sMsg.effFromDt_L1.getValue());
    	ZYPEZDItemValueSetter.setValue(sMsg.effFromDt_G1, sMsg.effFromDt_L1.getValue());
    	ZYPEZDItemValueSetter.setValue(sMsg.effThruDt_G1, sMsg.effThruDt_L1.getValue());
    	NLBL0040CommonLogic.setEffPerListFromCMsgToSMsg(cMsg, sMsg);
    	
        // ======================================================================================
        //  Search : AREA_LEAD_TM 
        // ======================================================================================
        S21SsmEZDResult ssmResult = NLBL0040Query.getInstance().getAreaLeadTmList(sMsg);
        
        if (ssmResult.isCodeNormal()) {
        	            
            int queryResCnt = ssmResult.getQueryResultCount();

            if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
            	
                queryResCnt = sMsg.A.length();
            }

            int i = 0;
            
            for (; i < sMsg.A.getValidCount(); i++) {
            	
                if (i == cMsg.A.length()) {
                    break;
                }
                
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);                
            }
            
            cMsg.A.setValidCount(i);
            cMsg.xxPageShowFromNum_A1.setValue(1);
            cMsg.xxPageShowToNum_A1.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A1.setValue(queryResCnt);
            
            // search snapshot data (This data is used when submit event is generated.)
            NLBL0040Query.getInstance().getEffFromToListForSearchSnapshot(sMsg);
            NLBL0040Query.getInstance().getAreaLeadTmListForSearchSnapshot(sMsg);
            
            
            if (ZYPCommonFunc.hasValue(sMsg.stCd_G2.getValue())) {

            	// set display data of area lead time
            	for (int k = 0; k < sMsg.A.getValidCount(); k++) {
            		
            		if (sMsg.stCd_G2.getValue().equals(sMsg.A.no(k).stCd_A1.getValue()) &&
            			sMsg.shpgModeCd_G2.getValue().equals(sMsg.A.no(k).shpgModeCd_A1.getValue())) {
            			
            			cMsg.delyLeadAot_R1.setValue(sMsg.A.no(k).delyLeadAot_A1.getValueInt());
            			sMsg.delyLeadAot_R1.setValue(sMsg.A.no(k).delyLeadAot_A1.getValueInt());
            			break;
            		}
            	}
                
                // ======================================================================================
                //  Search : TRNSP_LT
                // ======================================================================================
            	S21SsmEZDResult ssmResultForGetTrnspLtList = NLBL0040Query.getInstance().getTrnspLtList(sMsg);        

                if (ssmResultForGetTrnspLtList.isCodeNormal()) {

                    int queryResCntForGetTrnspLtList = ssmResultForGetTrnspLtList.getQueryResultCount();

                    if (ssmResultForGetTrnspLtList.getQueryResultCount() > sMsg.B.length()) {
                    	
                    	queryResCntForGetTrnspLtList = sMsg.B.length();
                    }

                    int j = 0;
                    for (; j < sMsg.B.getValidCount(); j++) {
                    	
                        if (j == cMsg.B.length()) {
                            break;
                        }
                        
                        EZDMsg.copy(sMsg.B.no(j), null, cMsg.B.no(j), null);
                    }
                    
                    cMsg.B.setValidCount(j);
                    cMsg.xxPageShowFromNum_B1.setValue(1);
                    cMsg.xxPageShowToNum_B1.setValue(cMsg.B.getValidCount());
                    cMsg.xxPageShowOfNum_B1.setValue(queryResCntForGetTrnspLtList);
                    
                    
                } else {
        			// When there is no retrieval result
                	// There is no processing.
                }
                
                // search snapshot data (This data is used when submit event is generated.)
                NLBL0040Query.getInstance().getTrnspLtListForDetailSnapshot(sMsg);
                NLBL0040Query.getInstance().getEffFromToListForSearchSnapshot(sMsg);
            }
            
        } else {
			// When there is no retrieval result
        	// There is no processing.
        }        
    }
    
	
    /**
     * The method explanation: It is a method of the execution when the event[CMN_Download] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_CMN_Download(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        // set value for search condition
        NLBL0040CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        
    	cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENTION);

    	S21SsmEZDResult ssmResult;
    	
    	if (ZYPCommonFunc.hasValue(sMsg.effFromDt_G1)) {
    	
    		ssmResult = NLBL0040Query.getInstance().getCSVDownloadData(sMsg);
    		
    	} else {
    		
    		ssmResult = NLBL0040Query.getInstance().getCSVDownloadDataNoValue(sMsg);
    	}
    	
    	if (!ssmResult.isCodeNormal()) {
    		
    		cMsg.setMessageInfo(NZZM0000E);
    		return;
    		
    	}

    	if (MAX_COUNT_OF_CSV_DOWNLOAD < ssmResult.getQueryResultCount()) {
    		
            cMsg.setMessageInfo(NZZM0007E);
            return;
        }

        NLBL0040F00FMsg fMsg = new NLBL0040F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        List<String> csvHeaderList = new ArrayList<String>();
        csvHeaderList.add(CSV_HEADER_WH);
        csvHeaderList.add(CSV_HEADER_DESTINATION_STATE_CODE);
        csvHeaderList.add(CSV_HEADER_SHIPPING_MODE_CODE);
        csvHeaderList.add(CSV_HEADER_EFFECTIVE_PERIOD_FROM);
        csvHeaderList.add(CSV_HEADER_EFFECTIVE_PERIOD_TO);
        csvHeaderList.add(CSV_HEADER_STATE_LEAD_TIME);
        csvHeaderList.add(CSV_HEADER_POST_CODE_FROM);
        csvHeaderList.add(CSV_HEADER_POST_CODE_TO);
        csvHeaderList.add(CSV_HEADER_ZIP_LEAD_TIME);

        String[] csvHeader = (String[]) csvHeaderList.toArray(new String[0]);

        csvOutFile.writeHeader(csvHeader);
                
        List<NLBL0040F00FMsg> ssmResList = (List<NLBL0040F00FMsg>)ssmResult.getResultObject();
        
        for (int i = 0; i < ssmResList.size(); i++) {
        	
        	fMsg.clear();
        	
            EZDMsg.copy(ssmResList.get(i), null, fMsg, null);
            
            if (ZYPCommonFunc.hasValue(ssmResList.get(i).moveEffFromDtTxt)) {
            	
            	ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt, ZYPDateUtil.DateFormatter(ssmResList.get(i).moveEffFromDtTxt.getValue(),
            									DATE_FORMATT_FOR_DB, DATE_FORMATT_FOR_CSV));
            }
            
            if (ZYPCommonFunc.hasValue(ssmResList.get(i).moveEffThruDtTxt)) {
            	
            	ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt, ZYPDateUtil.DateFormatter(ssmResList.get(i).moveEffThruDtTxt.getValue(),
            									DATE_FORMATT_FOR_DB, DATE_FORMATT_FOR_CSV));	
            }
            
            csvOutFile.write();
        }

        csvOutFile.close();
    }
    
	
    /**
     * The method explanation: It is a method of the execution when the event[CMN_Reset] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0040Scrn00_CMN_Reset(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
    	
    	cMsg.clear();
    	ZYPTableUtil.clear(cMsg.A);
    	ZYPTableUtil.clear(cMsg.B);
                
    	sMsg.clear();
    	ZYPTableUtil.clear(sMsg.C);
    	ZYPTableUtil.clear(sMsg.S);
    	ZYPTableUtil.clear(sMsg.T);
    	ZYPTableUtil.clear(sMsg.X);
    	ZYPTableUtil.clear(sMsg.Y);
    	ZYPTableUtil.clear(sMsg.Z);
        
        cMsg.glblCmpyCd_G1.setValue(getGlobalCompanyCode());
        sMsg.glblCmpyCd_G1.setValue(getGlobalCompanyCode());
        
        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        // Set Location Role Type Code List
        NLBL0040CMsg bizMsg = (NLBL0040CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_P2,
                NLBL0040CommonLogic.getLocRoleTpForPopup(getGlobalCompanyCode()));
        // 2013/05/21 R-OM025 Inventory Transaction Add End
        NLBL0040CommonLogic.init(cMsg, sMsg);
    }
    
    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    /**
     * Check Location Code
     * @param bizMsg NLCL0110CMsg
     * @return OK(true) or NG(false)
     */
    private boolean isLocCdCheck(NLBL0040CMsg bizMsg) {
        // Get Security Attribute
        List<S21DataSecurityAttributeData> securityAttr = getUserProfileService().getDataSecurityAttributeDataListFor(
                                                                getContextUserInfo().getUserId(),
                                                                NLBL0040Constant.BUSINESS_ID,
                                                                S21DataSecurityAttributeData.NAME_WAREHOUSE);
        // Get Location Info
        NMXC100001EnableWHData locInfo = NLBL0040CommonLogic.getInvtyLocInfo(bizMsg, securityAttr,getGlobalCompanyCode());

        // NG LocCd
        if (locInfo == null) {
            bizMsg.whCd_H2.setErrorInfo(1, "NLCM0004E");
            return false;
        } else if (ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
            bizMsg.whCd_H2.setErrorInfo(1, locInfo.getXxMsgId());
            return false;
        }

        // Set Location Name
        ZYPEZDItemValueSetter.setValue(bizMsg.locNm_H2, locInfo.getInvtyLocNm());

        return true;
    }
    // 2013/05/21 R-OM025 Inventory Transaction Add End

    // 10/06/2015 add start
    /**
     * Refresh WH Name
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     */
    private void refreshHeaderWhName(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {
        // Header
        if (ZYPCommonFunc.hasValue(cMsg.whCd_H2.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.locNm_H2, getWhName(cMsg.whCd_H2.getValue()));
        }
    }

    /**
     * Get WH Name
     * @param rtlWhCd  String
     * @return rtlWhNm String
     */
    private String getWhName(String rtlWhCd) {

        RTL_WHTMsg rtlWh = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, rtlWhCd);
        rtlWh = (RTL_WHTMsg) S21FastTBLAccessor.findByKey(rtlWh);
        if (rtlWh == null) {
            return BLANK;
        } else {
            return rtlWh.rtlWhNm.getValue();
        }
    }
    // 10/06/2015 add end
}
