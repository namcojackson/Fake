/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0020;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZBL0020.common.ZZBL0020CommonLogic;
import business.blap.ZZBL0020.constant.ZZBL0020Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;

/**
 * 
 * @author Q02870
 *
 */
public class ZZBL0020BL02 extends  S21BusinessHandler implements ZZBL0020Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
	        
	    super.preDoProcess(cMsg, sMsg);
	
	    try {
	        // +++++ [START] : Programming Area
	        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	        String screenAplID = cMsg.getScreenAplID();
	
	        if ("ZZBL0020_INIT".equals(screenAplID)) {
	            doProcess_ZZBL0020_INIT(cMsg, sMsg);
	        } else if ("ZZBL0020Scrn00_Search".equals(screenAplID)) { 
	            doProcess_ZZBL0020Scrn00_Search(cMsg, sMsg);
	        } else if ("ZZBL0020Scrn00_PageNext".equals(screenAplID)) {
	            doProcess_ZZBL0020Scrn00_Next(cMsg, sMsg);                
	        } else if ("ZZBL0020Scrn00_PagePrev".equals(screenAplID)) {
	            doProcess_ZZBL0020Scrn00_Prev(cMsg, sMsg);         
	        } else if ("ZZBL0020Scrn00_TBLColumnSort".equals(screenAplID)) {
	            doProcess_ZZBL0020Scrn00_Sort(cMsg, sMsg); 
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
	* Method name: doProcess_ZZBL0020_INIT
	* <dd>The method explanation: Business processing for Init.
	* @param cMsg Business Component Interface Message
	* @param sMsg Global area information
	*/
	private void doProcess_ZZBL0020_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
		
		ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
		ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;		
		// Concurrency
		// 0 to 9
		for(Integer i=0; i < 9; i++) {
			bizMsg.ezReqJobConcurrency_P1.no(i).setValue(new Integer(i+1).toString());
			bizMsg.xxJobCncrCd_P1.no(i).setValue(bizMsg.ezReqJobConcurrency_P1.no(i).getValue());
		}
		
		// A to Z
		int cA = 65; // 'A' : ASCII Code 
		for (int i=0; i < 26; i++) {
			bizMsg.ezReqJobConcurrency_P1.no(i+9).setValue( new Character((char)(i+cA)).toString());
			bizMsg.xxJobCncrCd_P1.no(i+9).setValue(bizMsg.ezReqJobConcurrency_P1.no(i+9).getValue());
		}
		
		//  Job Blocking Flag
		bizMsg.ezReqJobStopFlag_P2.no(0).setValue("0");
		bizMsg.xxJobBlockFlgNm_P2.no(0).setValue("No");
		bizMsg.ezReqJobStopFlag_P2.no(1).setValue("1");
		bizMsg.xxJobBlockFlgNm_P2.no(1).setValue("Yes");
		
		//  Error Flag
		bizMsg.ezReqJobErrorCtlFlag_P3.no(1).setValue("0");
		bizMsg.xxJobErrCtrlFlgNm_P3.no(1).setValue("Abort");
		bizMsg.ezReqJobErrorCtlFlag_P3.no(0).setValue("1");
		bizMsg.xxJobErrCtrlFlgNm_P3.no(0).setValue("Continue");

		// Max Counter
		sesMsg.A.clear();
		sesMsg.A.setValidCount(0);
		
	}        

   /**
    * Method name: doProcess_ZZBL0020Scrn00_Search
    * <dd>The method explanation: Business processing for
    * Search.
    * @param cMsg Business Component Interface Message
    * @param sMsg Global area information
    */
	private void doProcess_ZZBL0020Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
	       
		ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
		ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
		
        ZZBL0020CommonLogic.getAom10(sesMsg, bizMsg);
        
	}
   

   /**
    * Do process_ zzs l1002 scrn00_ next.
    * @param cMsg the c msg
    * @param sMsg the s msg
    */
   private void doProcess_ZZBL0020Scrn00_Next(EZDCMsg cMsg, EZDSMsg sMsg) {
   	
	   	// Copy check marks for deletion to S Msg
	   ZZBL0020CommonLogic.copyCMsgToSMsg(cMsg, sMsg);   
	   	
	   	ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
	   	
		// copy data from SMsg onto CMsg
		int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() + bizMsg.A.length();
		int rowCounter = ZZBL0020CommonLogic.copySMsgToCMsg(sMsg, cMsg, pagenationFrom -1);
		// set value to pagenation items
		bizMsg.xxPageShowFromNum.setValue( pagenationFrom);
		bizMsg.xxPageShowToNum.setValue( pagenationFrom + rowCounter -1 );
   }

   /**
    * Do process_ zzs l1002 scrn00_ prev.
    * @param cMsg the c msg
    * @param sMsg the s msg
    */
   private void doProcess_ZZBL0020Scrn00_Prev(EZDCMsg cMsg, EZDSMsg sMsg) {
   	
	   	// Copy check marks for deletion to S Msg
	   ZZBL0020CommonLogic.copyCMsgToSMsg(cMsg, sMsg);   
	   	
		ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
		
		// copy data from SMsg onto CMsg
		int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length();
		int rowCounter = ZZBL0020CommonLogic.copySMsgToCMsg(sMsg, cMsg, pagenationFrom-1);
		bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
		bizMsg.xxPageShowToNum.setValue( pagenationFrom + rowCounter -1 );
   }
   
   /**
    * Sort Processing logic
    * @param cMsg the C msg
    * @param sMsg the S msg
    */
   private void doProcess_ZZBL0020Scrn00_Sort(EZDCMsg cMsg, EZDSMsg sMsg) {
   // public static void doProcess_ZZBL0020Scrn00_Sort(EZDCMsg cMsg, EZDSMsg sMsg) {  	
	   
	   // Copy check marks for deletion to S Msg
	   ZZBL0020CommonLogic.copyCMsgToSMsg(cMsg, sMsg);   
   	
       ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;

       String sortTblNm   = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.SORT_TABLE_NAME );
       String sortItemNm  = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.SORT_ITEM_NAME );
       String sortOrderBy = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.ORDER_BY );
       
       if( "A".equals( sortTblNm ) ) {
           // execute column sort function
           S21SortTarget sortTarget = new S21SortTarget( ((ZZBL0020SMsg)sMsg).A, ((ZZBL0020SMsg)sMsg).A.no(0).getBaseContents() );
           S21SortKey sortKey = new S21SortKey();
           sortKey.add( sortItemNm, sortOrderBy );
           S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, ((ZZBL0020SMsg)sMsg).A.getValidCount() );
       }

       int rowCounter = ZZBL0020CommonLogic.copySMsgToCMsg(sMsg, cMsg, bizMsg.xxPageShowFromNum.getValueInt()-1);
       bizMsg.xxPageShowToNum.setValue( bizMsg.xxPageShowFromNum.getValueInt() + rowCounter -1 );
   }
}