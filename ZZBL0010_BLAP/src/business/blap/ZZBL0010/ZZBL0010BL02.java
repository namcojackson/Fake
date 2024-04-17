package business.blap.ZZBL0010;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import business.blap.ZZBL0010.common.ZZBL0010CommonLogic;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;


public class ZZBL0010BL02 extends S21BusinessHandler {

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

            if ("ZZBL0010_INIT".equals(screenAplID)) {
                doProcess_ZZBL0010_INIT(cMsg, sMsg);
            } else if ("ZZBL0010Scrn00_Search".equals(screenAplID)) { 
                doProcess_ZZBL0010Scrn00_Search(cMsg, sMsg);
            } else if ("ZZBL0010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZBL0010Scrn00_Next(cMsg, sMsg);                
            } else if ("ZZBL0010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZBL0010Scrn00_Prev(cMsg, sMsg);         
            } else if ("ZZBL0010Scrn00_Download".equals(screenAplID)) {
                doProcess_ZZBL0010Scrn00_Download(cMsg, sMsg);         
            } else if ("ZZBL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZBL0010Scrn00_Sort(cMsg, sMsg);                
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
         * Method name: doProcess_ZZBL0010_INIT
         * <dd>The method explanation: Business processing for Init.
         * @param cMsg Business Component Interface Message
         * @param sMsg Global area information
         */
        private void doProcess_ZZBL0010_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        	// Set up Status list box contents
            ZZBL0010CMsg bizMsg = (ZZBL0010CMsg) cMsg;
            
            bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

            try {
            	bizMsg.batProcTrmCd_DP.no(0).setValue("-1");
                bizMsg.xxBatProcTrmNm_DP.no(0).setValue("All");
                bizMsg.batProcTrmCd_DP.no(1).setValue("0");
                bizMsg.xxBatProcTrmNm_DP.no(1).setValue(S21BatchMain.TERM_CD.NORMAL_END.toString());
                bizMsg.batProcTrmCd_DP.no(2).setValue("99");
                bizMsg.xxBatProcTrmNm_DP.no(2).setValue(S21BatchMain.TERM_CD.ABNORMAL_END.toString());
                bizMsg.batProcTrmCd_DP.no(3).setValue("10");
                bizMsg.xxBatProcTrmNm_DP.no(3).setValue(S21BatchMain.TERM_CD.WARNING_END.toString());
            } catch (Exception e) {
            	EZDDebugOutput.println(1,e.getMessage(), null);
            }
        }        
    
        /**
         * Method name: doProcess_ZZBL0010Scrn00_Search
         * <dd>The method explanation: Business processing for
         * Search.
         * @param cMsg Business Component Interface Message
         * @param sMsg Global area information
         */
        private void doProcess_ZZBL0010Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
            
        	ZZBL0010CMsg bizMsg = (ZZBL0010CMsg) cMsg;  
            
            // Check Global Copmany Code is exist or not.
            if (!ZZBL0010CommonLogic.checkExistGlbCmpCd(bizMsg)) {
                bizMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code"});
                return;
            }
            try {
                int hitCount = ZZBL0010Query.getInstance().exec(cMsg, sMsg);
                int rowCounter = copyPartialArray(((ZZBL0010SMsg)sMsg).A, ((ZZBL0010CMsg)cMsg).A, 0, bizMsg.A.length());
    			bizMsg.xxPageShowFromNum.setValue( 1 );
    			bizMsg.xxPageShowToNum.setValue(rowCounter );
    			bizMsg.xxPageShowOfNum.setValue( hitCount );
    			
            } catch (Exception e) {
                EZDDebugOutput.println(1,e.getMessage(), null);
            }
        }
   
         
        /**
         * Do process_ zzs l1002 scrn00_ next.
         * @param cMsg the c msg
         * @param sMsg the s msg
         */
        private void doProcess_ZZBL0010Scrn00_Next(EZDCMsg cMsg, EZDSMsg sMsg) {
        	
        	ZZBL0010CMsg bizMsg = (ZZBL0010CMsg) cMsg;  
        	
    		// copy data from SMsg onto CMsg
    		int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() + bizMsg.A.length();
    		int rowCounter = copyPartialArray(((ZZBL0010SMsg)sMsg).A, ((ZZBL0010CMsg)cMsg).A, pagenationFrom -1, bizMsg.A.length());
    		// set value to pagenation items
    		bizMsg.xxPageShowFromNum.setValue( pagenationFrom);
    		bizMsg.xxPageShowToNum.setValue( pagenationFrom + rowCounter -1 );
        }
  
        /**
         * Do process_ zzs l1002 scrn00_ prev.
         * @param cMsg the c msg
         * @param sMsg the s msg
         */
        private void doProcess_ZZBL0010Scrn00_Prev(EZDCMsg cMsg, EZDSMsg sMsg) {
        	
        	ZZBL0010CMsg bizMsg = (ZZBL0010CMsg) cMsg;  
        	
        	// copy data from SMsg onto CMsg
    		int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length();
    		int rowCounter = copyPartialArray(((ZZBL0010SMsg)sMsg).A, ((ZZBL0010CMsg)cMsg).A, pagenationFrom-1, bizMsg.A.length());
       		bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
    		bizMsg.xxPageShowToNum.setValue( pagenationFrom + rowCounter -1 );
        }
        

        /**
         * Do process_ zzs l1002 scrn00_ download.
         * @param cMsg the c msg
         * @param sMsg the s msg
         */
        private void doProcess_ZZBL0010Scrn00_Download(EZDCMsg cMsg, EZDSMsg sMsg) {

//            ZZSC100001PMsg pMsg = new ZZSC100001PMsg();
//            pMsg.xxFuncId.setValue(ZZSC100001.USER_TYPE);
//            
//            ZZBL0010SMsg sesMsg = (ZZBL0010SMsg)sMsg;
//            int validCount = sesMsg.A.getValidCount();
//            for (int i = 0; i < validCount; i++) {
//                pMsg.A.no(i).xxEntyId.setValue(sesMsg.A.no(i).authPsnId_A.getValue());
//            }
//            pMsg.A.setValidCount(validCount);
//            ZZSC100001PMsg retMsg = ZZSC100001.getInstance().save(pMsg);
//            
//            ZZBL0010CMsg bizMsg = (ZZBL0010CMsg)cMsg;
//            bizMsg.ezDLTempFilePath.setValue(retMsg.ezDLTempFilePath.getValue());
            
        }
        
        /**
         * Sort Processing logic
         * @param cMsg the C msg
         * @param sMsg the S msg
         */
        private void doProcess_ZZBL0010Scrn00_Sort(EZDCMsg cMsg, EZDSMsg sMsg) {
        	
            ZZBL0010CMsg bizMsg = (ZZBL0010CMsg) cMsg;

//            String sortTblNm   = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.SORT_TABLE_NAME );
//            String sortItemNm  = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.SORT_ITEM_NAME );
//            String sortOrderBy = (String)cMsg.getCustomAttribute( S21TableColumnSortConstant.ORDER_BY );
            
            String sortTblNm   = bizMsg.xxSortTblNm.getValue();
            String sortItemNm  = bizMsg.xxSortItemNm.getValue();
            String sortOrderBy = bizMsg.xxSortOrdByTxt.getValue();
            
            if( "A".equals( sortTblNm ) ) {
                // execute column sort function
                S21SortTarget sortTarget = new S21SortTarget( ((ZZBL0010SMsg)sMsg).A, ((ZZBL0010SMsg)sMsg).A.no(0).getBaseContents() );
                S21SortKey sortKey = new S21SortKey();
                sortKey.add( sortItemNm, sortOrderBy );
                S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, ((ZZBL0010SMsg)sMsg).A.getValidCount() );
            }

            // Resort row numbers
            int vc = ((ZZBL0010SMsg)sMsg).A.getValidCount();
            for (int i = 0; i < vc; i++) {
                ((ZZBL0010SMsg)sMsg).A.no(i).xxRowNum_A.setValue(i+1);
            }
            
//            int rowCounter = copyPartialArray(((ZZBL0010SMsg)sMsg).A, ((ZZBL0010CMsg)cMsg).A, (bizMsg.xxPageShowFromNum.getValueInt()-1)* bizMsg.A.length(), bizMsg.A.length());
//            bizMsg.xxPageShowToNum.setValue( bizMsg.xxPageShowFromNum.getValueInt() + rowCounter -1 );
            
            int rowCounter = copyPartialArray(((ZZBL0010SMsg)sMsg).A, ((ZZBL0010CMsg)cMsg).A, 0, bizMsg.A.length());
            bizMsg.xxPageShowFromNum.setValue( 1 );
			bizMsg.xxPageShowToNum.setValue(rowCounter );
            
        }
        
        /**
         * Copy partial array.
         * @param sMsg the S msg
         * @param cMsg the C msg
         * @param pageNum the page number
         * @param pageSize the page size
         * @return the int
         */
        public int copyPartialArray(EZDSMsgArray sMsg, EZDCMsgArray cMsg, int offset, int pageSize) {

            cMsg.clear();

            int nextPageOffset = offset + pageSize;
            int lastRow = nextPageOffset > sMsg.getValidCount() ? sMsg.getValidCount() : nextPageOffset;

            int rowCounter = 0;
            for (int i = offset; i < lastRow; i++) {
                EZDMsg.copy(sMsg.get(i), null, cMsg.get(rowCounter++), null);
            }
            cMsg.setValidCount(rowCounter);

            return rowCounter;
        }
}
