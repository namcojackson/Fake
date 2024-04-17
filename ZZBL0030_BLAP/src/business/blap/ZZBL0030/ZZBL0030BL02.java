/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0030;

import java.text.DecimalFormat;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDSMsg;
import business.blap.ZZBL0030.common.ZZBL0030CommonLogic;
import business.blap.ZZBL0030.constant.ZZBL0030Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;

public class ZZBL0030BL02 extends S21BusinessHandler implements ZZBL0030Constant {

    /**
     * Do process.
     * @param cMsg the c msg
     * @param sMsg the s msg
     * @see parts.ejbcommon.EZDBusinessHandler#doProcess(parts.common.EZDCMsg,
     * parts.common.EZDSMsg)
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZBL0030_INIT".equals(screenAplID)) {
                doProcess_ZZBL0030_INIT(cMsg, sMsg);
            } else if ("ZZBL0030Scrn00_Search".equals(screenAplID)) { 
                doProcess_ZZBL0030Scrn00_Search(cMsg, sMsg);
            } else if ("ZZBL0030Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZBL0030Scrn00_Next(cMsg, sMsg);                
            } else if ("ZZBL0030Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZBL0030Scrn00_Prev(cMsg, sMsg);         
            } else if ("ZZBL0030Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZBL0030Scrn00_Sort(cMsg, sMsg);    
            } else {                
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_ZZBL0030_INIT
     * <dd>The method explanation: Business processing for Init.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_ZZBL0030_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
    	ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg; 
    	
    	// Set up Status pulldown 
    	setupStatusPulldown(bizMsg.ezReqJobStatus_DP, bizMsg.xxBatProcTrmNm_DP);

    	setupTimePulldown(bizMsg.ezReqInputTime_P1, bizMsg.xxHrsMn_P1);
    	setupTimePulldown(bizMsg.ezReqInputTime_P2, bizMsg.xxHrsMn_P2);
    	setupTimePulldown(bizMsg.ezReqJobStartTime_P3, bizMsg.xxHrsMn_P3);
    	setupTimePulldown(bizMsg.ezReqJobEndTime_P4, bizMsg.xxHrsMn_P4);
    }        

    /**
     * Method name: doProcess_ZZBL0030Scrn00_Search
     * <dd>The method explanation: Business processing for
     * Search.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_ZZBL0030Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        
    	ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;  
    	ZZBL0030SMsg sesMsg = (ZZBL0030SMsg) sMsg;
    	
       	// Copy changed part on screen to S Msg
        ZZBL0030CommonLogic.copyCMsgtoSMsg(cMsg, sMsg);   
        
        ZZBL0030CommonLogic.getAot10(sesMsg, bizMsg);
    }


    
    /**
     * Do process_ zzs l1002 scrn00_ next.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void doProcess_ZZBL0030Scrn00_Next(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
    	ZZBL0030CommonLogic.copyCMsgtoSMsg(cMsg, sMsg);   
    	
    	ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;  
    	
		// copy data from SMsg onto CMsg
		int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() + bizMsg.A.length();
		int rowCounter = ZZBL0030CommonLogic.copySMsgToCMsg(sMsg, cMsg, pagenationFrom -1);
		// set value to pagenation items
		bizMsg.xxPageShowFromNum.setValue( pagenationFrom);
		bizMsg.xxPageShowToNum.setValue( pagenationFrom + rowCounter -1 );
    }

    /**
     * Do process_ zzs l1002 scrn00_ prev.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void doProcess_ZZBL0030Scrn00_Prev(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
    	ZZBL0030CommonLogic.copyCMsgtoSMsg(cMsg, sMsg);   
    	
    	ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;  
    	
    	// copy data from SMsg onto CMsg
		int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length();
		int rowCounter = ZZBL0030CommonLogic.copySMsgToCMsg(sMsg, cMsg, pagenationFrom-1);
   		bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
		bizMsg.xxPageShowToNum.setValue( pagenationFrom + rowCounter -1 );
    }
    
    /**
     * Sort Processing logic
     * @param cMsg the C msg
     * @param sMsg the S msg
     */
    private void doProcess_ZZBL0030Scrn00_Sort(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
    	ZZBL0030CommonLogic.copyCMsgtoSMsg(cMsg, sMsg);   
    	
        ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;

        String sortTblNm   = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.SORT_TABLE_NAME );
        String sortItemNm  = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.SORT_ITEM_NAME );
        String sortOrderBy = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.ORDER_BY );
        
        if( "A".equals( sortTblNm ) ) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget( ((ZZBL0030SMsg)sMsg).A, ((ZZBL0030SMsg)sMsg).A.no(0).getBaseContents() );
            S21SortKey sortKey = new S21SortKey();
            sortKey.add( sortItemNm, sortOrderBy );
            S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, ((ZZBL0030SMsg)sMsg).A.getValidCount() );
        }

        int rowCounter = ZZBL0030CommonLogic.copySMsgToCMsg(sMsg, cMsg, bizMsg.xxPageShowFromNum.getValueInt()-1);
        bizMsg.xxPageShowToNum.setValue( bizMsg.xxPageShowFromNum.getValueInt() + rowCounter -1 );
    }
    

    
//    private void copyCMsgtoSMsg(EZDCMsg cMsg, EZDSMsg sMsg) {
//    	
//    	ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;  
//    	ZZBL0030SMsg sesMsg = (ZZBL0030SMsg) sMsg;
//    	
//    	for (int i=0; i < bizMsg.A.getValidCount(); i++) {
//    		
//    		// Those fields are PK keys. 
//    		String ezReqBusinessName = bizMsg.A.no(i).ezReqBusinessName_A.getValue();
//    		String ezReqInputDeptCode = bizMsg.A.no(i).ezReqInputDeptCode_A.getValue();
//    		String ezReqInputUserID = bizMsg.A.no(i).ezReqInputUserID_A.getValue();
//    		String ezReqInputDate = bizMsg.A.no(i).ezReqInputDate_A.getValue();
//    		String ezReqInputTime = bizMsg.A.no(i).ezReqInputTime_A.getValue();
//    		String ezReqUserKeyItem = bizMsg.A.no(i).ezReqUserKeyItem_A.getValue(); 
//    		String ezReqJobStatus = bizMsg.A.no(i).ezReqJobStatus_A.getValue();
//    		
//    		// Search the specific record.
//    		for (int j=0; j < sesMsg.A.getValidCount(); j++) {
//    			ZZBL0030_ASMsg currentRow = sesMsg.A.no(j);
//    			if (currentRow.ezReqBusinessName_A.getValue().equals(ezReqBusinessName) &&
//    			    currentRow.ezReqInputDeptCode_A.getValue().equals(ezReqInputDeptCode) &&
//       			    currentRow.ezReqInputUserID_A.getValue().equals(ezReqInputUserID) &&
//       			    currentRow.ezReqInputDate_A.getValue().equals(ezReqInputDate) &&
//       			    currentRow.ezReqInputTime_A.getValue().equals(ezReqInputTime) &&
//       			    currentRow.ezReqUserKeyItem_A.getValue().equals(ezReqUserKeyItem) &&
//      			    !currentRow.ezReqJobStatus_A.getValue().equals(ezReqJobStatus)) {
//    				
//    					currentRow.ezReqJobStatus_A.setValue(ezReqJobStatus);
//    					break;
//    			}
//    		}
//    	}
//    }
    
    
    /**
     * 
     * @param cMsgCode
     * @param cMsgName
     */
    private void setupStatusPulldown(EZDCStringItemArray cMsgCode, EZDCStringItemArray cMsgName)
    {
    	
   		// Need to be filled by Code table.
    	cMsgCode.no(0).setValue("5");
    	cMsgName.no(0).setValue("All");
    	cMsgCode.no(1).setValue("0");
    	cMsgName.no(1).setValue("Not Started");
		cMsgCode.no(2).setValue("1");
		cMsgName.no(2).setValue("In Process");
		cMsgCode.no(3).setValue("2");
		cMsgName.no(3).setValue("Normal End");
		cMsgCode.no(4).setValue("3");
		cMsgName.no(4).setValue("Abnormal End");
		cMsgCode.no(5).setValue("4");
		cMsgName.no(5).setValue("Warning End");    		
    }
    
    /**
     * 
     * @param cMsgCode
     * @param cMsgName
     */
    private void setupTimePulldown(EZDCStringItemArray cMsgCode, EZDCStringItemArray cMsgName)
    {
    	DecimalFormat twoDigits = new DecimalFormat("00");
    	for(int i=0; i<24 ; i++){
    		cMsgCode.no(i).setValue(twoDigits.format(i)+"0000000");
    		cMsgName.no(i).setValue(twoDigits.format(i)+":00");
    	}
    }
    

}