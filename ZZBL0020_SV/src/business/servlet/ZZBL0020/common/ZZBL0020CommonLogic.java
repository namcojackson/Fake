/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0020.common;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZBL0020.ZZBL0020CMsg;
import business.blap.ZZBL0020.ZZBL0020SMsg;
import business.blap.ZZBL0020.ZZBL0020_ASMsg;
import business.servlet.ZZBL0020.ZZBL0020BMsg;
import business.servlet.ZZBL0020.ZZBL0020Bean;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZBL0020CommonLogic  implements ZZBL0020Constant {
	
    static public void initCommonButtonForScrn00(EZDCommonHandler handler, ZZBL0020BMsg scrnMsg) {
        
    	handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        }
        handler.setButtonConfirmMsg(BTN_CMN_DELETE[1], "ZZM8102I" , new String[] {"Delete"}, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], null, null, 0);
    }
    
    static public void initCommonButtonForScrn01(EZDCommonHandler handler) {
    	
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        handler.setButtonConfirmMsg(BTN_CMN_DELETE[1], null, null, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], "ZZM8101I", new String[] {"Return"}, 0);
    }
    /**
     * 
     * @param scrnMsg
     */
	public static void setTableColor( ZZBL0020BMsg scrnMsg ) {
		
		S21TableColorController tblColor = new S21TableColorController( "ZZBL0020Scrn00", scrnMsg );
		tblColor.clearRowsBG( ZZBL0020Bean.A, scrnMsg.A );
		
		if( scrnMsg.A.getValidCount() > 0 ) {
			
			String styleClass   = "";
			
			for( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
				styleClass = i%2 == 0  ? "pEvenNumberBGcolor" : "" ;
				tblColor.setRowStyle( ZZBL0020Bean.A, i, styleClass );
			}
		}	
	}
	
	/**
	 * Copy S Msg records w/o deletions to B Msg.
	 * @param sMsg
	 * @param cMsg
	 * @param offset 
	*/
    public static int copySMsgToCMsg(EZDSMsg sMsg, EZDCMsg cMsg, int offsetTop) {
	       	
			ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
			ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;

			// Clear C Msg array.
			bizMsg.A.clear();
			bizMsg.A.setValidCount(0);
			
			int pageSize = bizMsg.A.length();
//			int nextPageOffset = offset + pageSize;
//			int lastRow = nextPageOffset > sesMsg.A.getValidCount() ? sesMsg.A.getValidCount() : nextPageOffset;
			
			// Find out real offset number. Skip all deleted rows before offset
			int offset = 0;
			for(int i=0; i < sesMsg.A.getValidCount(); i++) {
				if (!sesMsg.A.no(i).actvFlg_A.getValue().equals(DELETED)) {
	        		if(offset == offsetTop) { 
	        			offset = i;
	        			break;
	        		} 
	        		offset++;
	        	}
			}

			// Copy all non-deleted records from offset to Biz Meg.
			// offset variable is an index of S Msg. ( b/w offset and (offset + 40 or last record of S Msg )) 
			// rowCounter variable is an actual index of C Msg. ( b/w 0 and 40 )
			int rowCounter = 0;
			while (rowCounter < pageSize && offset < sesMsg.A.getValidCount()) {
				if (!sesMsg.A.no(offset).actvFlg_A.getValue().equals(DELETED)) {
	        		EZDMsg.copy(sesMsg.A.get(offset), null, bizMsg.A.get(rowCounter), null);
	        		bizMsg.A.no(rowCounter).xxRowNum_A.setValue(rowCounter+offsetTop+1);
	        		rowCounter++;
	        	}
				offset++;
			}
			
	        bizMsg.A.setValidCount(rowCounter);
	        return rowCounter;
	    }
	   	
	    
    /**
     *  Set a deletion mark on S MSG from B Msg
     * @param sMsg
     * @param cMsg
     * @param offset
     */
     public static void copyCMsgToSMsg(EZDCMsg cMsg, EZDSMsg sMsg) {
	        	
 		ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
 		ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
 		
 		for(int i=0; i < bizMsg.A.getValidCount() ; i++ ){
 			String ezReqBusinessID = bizMsg.A.no(i).ezReqBusinessID_A.getValue();  // This is a PK.
 			// Search this record inside S Msg. 
 			for(int j=0; j < sesMsg.A.getValidCount(); j++) {
 				ZZBL0020_ASMsg currentRow = sesMsg.A.no(j);
	  			if (currentRow.ezReqBusinessID_A.getValue().equals(ezReqBusinessID)) {
	  				if (bizMsg.A.no(i).xxYesNoCd_A.getValue().equals(DELETED)) {
	  					 currentRow.xxYesNoCd_A.setValue(bizMsg.A.no(i).xxYesNoCd_A.getValue());
	  				} else {
	  					currentRow.xxYesNoCd_A.setValue(bizMsg.A.no(i).xxYesNoCd_A.getValue());
	  				}
	  				break;
	  			}
 			}
 		}
     }
	     
    /**
     * This function counts a number of valid records on screen.
     * It means that it skips to count the deleted reows.
     * @param sMsg
     * @return
     */
    public static int getCurrentRowNumber(EZDSMsg sMsg) {
   	
	  	ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
	  	
	  	int hitCount =0;
	  	for (int i=0; i < sesMsg.A.getValidCount(); i++) {
	  		if (!sesMsg.A.no(i).actvFlg_A.getValue().equals(DELETED)) {
	  			hitCount++;
	  		}
	  	}
	  	
	  	return hitCount;
    }
}